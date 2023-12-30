var labels1;

document.addEventListener("DOMContentLoaded", function() {
    stompClient.activate();
});
document.addEventListener("DOMContentLoaded", function() {
    fetch('http://localhost:8080/getlabels', {
        method: 'GET',
        headers: {'Content-Type': 'application/text',},
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text();
        })
        .then(data => setColumnLabels([data]))
        .catch(error => {
            console.error('Fetch error:', error);
        });
});

const stompClient = new StompJs.Client({
    // Haven't tested the use of window.location.host on AWS yet, so the lines below are a fallback
// const params = new URLSearchParams(window.location.search);
// const host = params.get('host'); // 'value1'
// const port = params.get('port'); // 'value2'
    // brokerURL: `ws://${host}:${port}/gs-guide-websocket`
    brokerURL: `ws://` + window.location.host + `/gs-guide-websocket`
});

stompClient.onConnect = (frame) => {
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/vote', (vote) => {
        increaseBarHeight(JSON.parse(vote.body).content);
    });
    stompClient.subscribe('/topic/labels', (labels) => {
        labels1 = JSON.parse(labels.body).content;
        increaseBarHeight(1);
        // consoleOutput(labels1);
    });
};
stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};
