document.addEventListener("DOMContentLoaded", () => {
    const chatInput = document.querySelector(".chat-input textarea");
    const sendChatBtn = document.querySelector(".chat-input span");
    const chatbox = document.querySelector(".chatbox");
    const chatbotToggler = document.querySelector(".chatbot-toggle");
    const chatbot = document.querySelector(".chatbot");
    const closeBtn = document.querySelector(".chatbot header span");
    const chatbotCloseBtn = document.querySelector(".close-btn");


    const API_KEY = "AIzaSyCxs07_m2WjA7TpNAps8XIIHBij8gpcrBo"; // Replace with your actual key
    const API_URL = `https://generativelanguage.googleapis.com/v1/models/gemini-1.5-flash:generateContent?key=${API_KEY}`;
    const inputInitHeight = chatInput.scrollHeight;
    const createChatLi = (message, className) => {
        const chatLi = document.createElement("li");
        chatLi.classList.add("chat", className);
        chatLi.innerHTML = className === "outgoing"
            ? `<p>${message}</p>`
            : `<span class="material-symbols-outlined">smart_toy</span><p>${message}</p>`;
        return chatLi;
    };

    const generateResponse = async (incomingChatLi, userMessage) => {
        const messageElement = incomingChatLi.querySelector("p");

        try {
            const res = await fetch(API_URL, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({
                    contents: [
                        {
                            role: "user",
                            parts: [{ text: userMessage }]
                        }
                    ]
                })
            });

            if (!res.ok) {
                throw new Error(`API Error: ${res.status}`);
            }

            const data = await res.json();
            messageElement.textContent = data.candidates?.[0]?.content?.parts?.[0]?.text || "No response.";
        } catch (error) {
            messageElement.classList.add("error");
            messageElement.textContent = "Oops! Something went wrong. Try again later.";
        }
    };

    const handleChat = () => {
        const userMessage = chatInput.value.trim();
        if (!userMessage) return;

        chatbox.appendChild(createChatLi(userMessage, "outgoing"));
        chatbox.scrollTo(0,chatbox.scrollHeight);

        setTimeout(() => {
            const incomingChatLi = createChatLi("Thinking...", "incoming");
            chatbox.appendChild(incomingChatLi);
            chatbox.scrollTo(0,chatbox.scrollHeight);
            generateResponse(incomingChatLi, userMessage);
        }, 1000);

        chatInput.value = "";
        chatInput.style.height = `${inputInitHeight}px`;
        chatbox.scrollTop = chatbox.scrollHeight;
    };


    chatInput.addEventListener("input", () =>{
        chatInput.style.height = `${inputInitHeight}px`;
        chatInput.style.height = `${chatInput.scrollHeight}px`;
    });

    chatInput.addEventListener("keydown", (e) =>{
       if (e.key === "Enter" && !e.shiftKey && window.innerWidth > 800){
           e.preventDefault();
           handleChat();
       }
    });


    sendChatBtn.addEventListener("click", handleChat);
    chatbotCloseBtn.addEventListener("click", () => document.body.classList.remove("show-chatbot"))
    if (!chatbotToggler) {
        console.error("Chatbot toggler button not found!");
        return;
    }

    chatbotToggler.addEventListener("click", () => {
        document.body.classList.toggle("show-chatbot");
    });

    if (closeBtn) {
        closeBtn.addEventListener("click", () => {
            document.body.classList.remove("show-chatbot");
        });
    }


});
