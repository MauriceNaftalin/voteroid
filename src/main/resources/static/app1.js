var data = [0, 0, 0];
const svg = d3.select("svg");
const svgWidth = 250, svgHeight = 250;
const barWidth = svgWidth / data.length;

// Can't open a JS console, so use a label for diagnostic output
function consoleOutput(text) {
    document.getElementById('consoleLog').textContent = text;
}

// Set up the bar chart
svg.selectAll("rect")
    .data(data)
    .enter().append("rect")
    .attr("x", (d, i) => i * barWidth)
    .attr("y", d => svgHeight - d * 10) // Scale the bar height
    .attr("width", barWidth - 1)
    .attr("height", d => d * 10)
    .attr("fill", "steelblue");

function increaseBarHeight(answerNumber) {
    console.log('answew: ' + answerNumber)
    const additionalHeight = 10; // Height increase each click

    svg.selectAll(`rect:nth-child(${answerNumber})`)
        .attr("height", function(d) {
            return parseFloat(d3.select(this).attr("height")) + additionalHeight;
        })
        .attr("y", function(d) {
            return parseFloat(d3.select(this).attr("y")) - additionalHeight;
        });
}

document.addEventListener("DOMContentLoaded", function() {
    stompClient.activate();
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
    consoleOutput(window.location.host);
};

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};
