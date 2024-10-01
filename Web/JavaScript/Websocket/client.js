const chatBox = document.getElementById('chat');
const messageInput = document.getElementById('message');
const sendButton = document.getElementById('send');

const socket = new WebSocket('ws://localhost:8080');

socket.addEventListener('message', (event) => {
    if (typeof event.data === 'string') {
        displayMessage(event.data);
    } else {
        const reader = new FileReader();
        reader.onload = function() {
            displayMessage(reader.result);
        };
        reader.readAsText(event.data);
    }
});

sendButton.addEventListener('click', () => {
    const message = messageInput.value;
    if (message) {
        socket.send(message);
        messageInput.value = '';
    }
});

messageInput.addEventListener('keypress', (event) => {
    if (event.key === 'Enter') {
        sendButton.click();
    }
});

function displayMessage(message) {
    const messageElement = document.createElement('div');
    messageElement.textContent = message;
    chatBox.appendChild(messageElement);
    chatBox.scrollTop = chatBox.scrollHeight;
}