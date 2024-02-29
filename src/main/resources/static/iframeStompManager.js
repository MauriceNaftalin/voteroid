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
    const params = new URLSearchParams(window.location.search);
    const question = params.get('question');
    stompClient.subscribe('/topic/votes', (voteUpdate) => {
        const content = JSON.parse(voteUpdate.body).content;
        if (content.question === question) {
            setVoteCount(content);
        }
    });
};

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};
