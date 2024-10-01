// server.js
const WebSocket = require('ws');

// Create a WebSocket server
const wss = new WebSocket.Server({ port: 8080 });

let clients = [];

// Broadcast a message to all clients
function broadcast(data) {
  clients.forEach((client) => {
    if (client.readyState === WebSocket.OPEN) {
      client.send(data);
    }
  });
}

wss.on('connection', (ws) => {
  console.log('New client connected');
  clients.push(ws);

  // Broadcast a message to all clients when a new message is received
  ws.on('message', (message) => {
    console.log(`Received: ${message}`);
    broadcast(message);
  });

  // Remove the client when it disconnects
  ws.on('close', () => {
    console.log('Client disconnected');
    clients = clients.filter((client) => client !== ws);
  });
});

console.log('WebSocket server is running on ws://localhost:8080');
