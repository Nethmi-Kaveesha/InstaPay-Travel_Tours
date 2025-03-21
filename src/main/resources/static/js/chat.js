'use strict';

var usernamePage = document.querySelector("#username-page");
var chatPage = document.querySelector("#chat-page");
var usernameForm = document.querySelector("#usernameForm");
var messageForm = document.querySelector("#messageForm");
var messageInput = document.querySelector("#message");
var messageArea = document.querySelector("#messageArea");
var connectingElement = document.querySelector(".connecting");

var stompClient = null;
var username = null;

var colors = [
    "#FF5733", "#33FF57", "#3357FF", "#FF33A1",
    "#A133FF", "#FFD700", "#00FFFF", "#FF4500"
];

function connect(event) {
    event.preventDefault();  // Prevent the default form submit behavior

    username = document.querySelector('#name').value.trim();
    console.log("Username entered: ", username);  // Debugging username capture

    if (username) {
        // Hide username page and show chat page
        usernamePage.classList.add('hidden');
        chatPage.classList.remove('hidden');
        console.log("Connecting to WebSocket...");

        var socket = new SockJS('/ws');  // Ensure this URL is correct for your server
        stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected, onError);
    } else {
        console.log("No username entered");
    }
}

function onConnected() {
    console.log("Connected to WebSocket");  // Debugging WebSocket connection
    stompClient.subscribe('/topic/public', onMessageReceived);
    stompClient.send('/app/chat.addUser', {}, JSON.stringify({ sender: username, type: 'JOIN' }));
    connectingElement.classList.add('hidden');
}

function onError(error) {
    console.error("Error connecting to WebSocket:", error);
    connectingElement.textContent = 'Could not connect to websocket server. Please refresh this page and try!';
    connectingElement.style.color = 'red';
}

function onMessageReceived(payload) {
    console.log("Message received: ", payload);  // Log received messages for debugging
    var message = JSON.parse(payload.body);

    var messageElement = document.createElement('li');

    if (message.type === 'JOIN') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' joined';
    } else if (message.type === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' left';
    } else {
        messageElement.classList.add('chat-message');

        var avatarElement = document.createElement('i');
        var avatarText = document.createTextNode(message.sender[0]);
        avatarElement.appendChild(avatarText);
        avatarElement.style['backgroundColor'] = getAvatarColor(message.sender);

        messageElement.appendChild(avatarElement);

        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(message.sender);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }

    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);
    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}

function sendMessage(event) {
    event.preventDefault();  // Prevent the default form submit behavior

    var messageContent = messageInput.value.trim();

    if (messageContent && stompClient) {
        var chatMessage = {
            sender: username,
            content: messageContent,
            type: 'CHAT'
        };

        stompClient.send('/app/chat.sendMessage', {}, JSON.stringify(chatMessage));
        messageInput.value = '';  // Clear the input field
    }
}

function getAvatarColor(messageSender) {
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);  // Use charCodeAt instead of charCode
    }

    var index = Math.abs(hash % colors.length);
    return colors[index];
}

// Add event listener to username form
usernameForm.addEventListener('submit', connect, true);
messageForm.addEventListener('submit', sendMessage, true);
