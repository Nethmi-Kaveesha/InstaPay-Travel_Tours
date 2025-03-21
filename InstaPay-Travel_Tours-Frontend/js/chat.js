'use strict';

var usernamePage = document.querySelector("#username-page");
var chatPage = document.querySelector("#chat-page");
var usernameForm = document.querySelector("#usernameForm");
var messageForm = document.querySelector("#messageForm");
var messageInput = document.querySelector("#message");
var messageArea = document.querySelector("#messageArea");
var connectingElement = document.querySelector(".connecting")

var stompClient = null;
var username = null;

var colors = [
    "#FF5733", "#33FF57", "#3357FF", "#FF33A1",
    "#A133FF", "#FFD700", "#00FFFF", "#FF4500"
];

function connect(event){
    username = document.querySelector('#name').value.trim();
    if (username){
        usernamePage.classList.add('hidden')
        chatPage.classList.remove('hidden')

        var socket = new SocketJS('/ws');
        stompClient = Stomp.over(socket);

        stompClient.connect({},onConnected,onerror)
    }

    event.preventDefault();
}

function onConnected(){
    stompClient.subscribe('/topic/public',onMessageReceived);
    stompClient.send('/app/chat.addUser',
        {},
        JSON.stringify({sender:username,type:'JSON'})
    );
    connectingElement.classList.add('hidden');
}

function onError(){
    connectingElement.textContent = 'Could not connect to websocket server.Please refresh this page and try!';
    connectingElement.style.color = 'red';
}

function onMessageReceived(){
    var message = JSON.parse(payload.body);

    var messageElement = document.createElement('li');

    if (message.type === 'JOIN'){
        messageElement.classList.add('event-message');
        message.content = message.sender + 'joined';

    }else if (message.type == 'LEAVER'){
        messageElement.classList.add('event-message');
        message.content = message.sender + 'left';

    }else {
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

    messageElement.appendChild(textElement);

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}

function sendMessage(){
    var messageContent = messageInput.value.trim();

    if (messageContent && stompClient){
        var chatMessage ={
            sender: username,
            content: messageContent,
            type: 'CHAT'
        };

        stompClient.send(
            '/app/chat.sendMessage',
            {},
            JSON.stringify(chatMessage)
        );

        messageInput.content = '';
    }

        event.preventDefault();
}

function getAvatarColor(messageSender){
   var hash = 0;
   for (var i=0; i < messageSender.length; i++){
       hash = 31 * hash + messageSender.charCode(i);
   }

   var index = Math.abs(hash % colors.length);
   return colors[index];
}

usernameForm.addEventListener('submit',connect,true);
messageForm.addEventListener('submit',sendMessage,true);

