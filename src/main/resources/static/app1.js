var data = [10,15,20];
var labels = ["esdfqxy"];
const svg = d3.select("svg");
const svgWidth = 250, svgHeight = 250;
const barWidth = svgWidth / data.length;

// Can't open a JS console, so use a label for diagnostic output
function consoleOutput(text) {
    document.getElementById('consoleLog').textContent = text;
}

// https://codesandbox.io/p/sandbox/d3-playground-forked-vqh5wr?file=%2Findex.js%3A59%2C27
svg.selectAll("rect")
    .data(data)
    .enter().append("rect")
    .attr("x", (d, i) => i * barWidth)
    .attr("y", d => svgHeight - d * 10) // Scale the bar height
    .attr("width", barWidth - 1)
    .attr("height", d => d * 10)
    .attr("fill", "steelblue");

svg.selectAll("text")
    .data(labels)
    .enter()
    .append("text")
    .text(d => d) // Set text content based on data
    .attr("text-anchor", "middle") // Center-align the text
    .attr("x", (d, i) => i * barWidth + barWidth / 2) // Set x position based on data
    .attr("y", svgHeight - 30); // Set y position

function increaseBarHeight(answerNumber) {
    console.log('answer: ' + answerNumber)
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
    stompClient.subscribe('/topic/labels', (labels) => {
        consoleOutput(JSON.parse(labels.body).content);
    });
    // consoleOutput(window.location.host);
    // consoleOutput(labels);
};

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};
