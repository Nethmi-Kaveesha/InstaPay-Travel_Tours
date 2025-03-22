document.addEventListener("DOMContentLoaded", () => {
    const chatInput = document.querySelector(".chat-input textarea");
    const sendChatBtn = document.querySelector(".chat-input span");
    const chatbox = document.querySelector(".chatbox");

    let userMessage;
    const API_KEY = "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";
    const createChatLi = (message, className) => {
        const chatLi = document.createElement("li");
        chatLi.classList.add("chat", className);
        let chatContent = className === "outgoing"
            ? `<p>${message}</p>`
            : `<span class="material-symbols-outlined">smart_toy</span><p>${message}</p>`;
        chatLi.innerHTML = chatContent;
        return chatLi;
    };

    const generateResponse = (incomingChatLi) => {
        const API_URL = "https://api.openai.com/v1/chat/completions";
        const messageElement = incomingChatLi.querySelector("p");

        const requestOptions = {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${API_KEY}`
            },
            body: JSON.stringify({
                model: "gpt-3.5-turbo",
                messages: [{ role: "user", content: userMessage }]
            })
        };

        const retryRequest = (retryCount = 0) => {
            fetch(API_URL, requestOptions)
                .then((res) => {
                    if (res.status === 429) {
                        if (retryCount < 3) {  // Retry a maximum of 3 times
                            setTimeout(() => retryRequest(retryCount + 1), 2000); // Retry after 2 seconds
                        } else {
                            messageElement.textContent = "Too many requests. Please try again later.";
                        }
                        return;
                    }
                    return res.json();
                })
                .then((data) => {
                    if (data) {
                        messageElement.textContent = data.choices[0].message.content;
                    }
                })
                .catch((error) => {
                    messageElement.textContent = "Oops! Something went wrong. Please try again.";
                });
        };

        retryRequest();
    };

    let debounceTimeout;
    const handleChat = () => {
        const userMessage = chatInput.value.trim();
        if (!userMessage) return; // Prevent empty messages

        chatbox.appendChild(createChatLi(userMessage, "outgoing"));

        clearTimeout(debounceTimeout);
        debounceTimeout = setTimeout(() => {
            const incomingChatLi = createChatLi("Thinking...", "incoming");
            chatbox.appendChild(incomingChatLi);
            generateResponse(incomingChatLi);
        }, 1000); // Delay sending request for 1 second after user stops typing
        chatInput.value = ""; // Clear input field after sending
        chatbox.scrollTop = chatbox.scrollHeight; // Scroll to the bottom
    };


    sendChatBtn.addEventListener("click", handleChat);
});
