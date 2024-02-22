var data = [0,0,0];
const svg = d3.select("svg");
const svgWidth = 250, svgHeight = 250;
const barWidth = svgWidth / data.length;

// Can't open a JS console, so use a label for diagnostic output
function consoleOutput(text) {
    document.getElementById('consoleLog').textContent = text;
}

svg.selectAll("rect")
    .data(data)
    .enter().append("rect")
    .attr("x", (d, i) => i * barWidth)
    .attr("y", d => svgHeight - 20 - d * 10) // Scale the bar height
    .attr("width", barWidth - 1)
    .attr("height", d => d * 10)
    .attr("fill", "steelblue");

function setBarHeight(voteUpdate) {
    console.log('answer: ' + voteUpdate)
    const voteCount = voteUpdate.voteCount;
    svg.selectAll(`rect:nth-child(${voteUpdate.answerIndex})`)
        .attr("height", function (d) {
            return voteCount * 10;
        })
        .attr("y", function (d) {
            return svgHeight - (voteCount * 10) - 20;
        });
}

function setColumnLabels(labelsText) {
    svg.selectAll("text")
        .data(labelsText)
        .enter()
        .append("text")
        .text(d => d) // Set text content based on data
        .attr("text-anchor", "middle") // Center-align the text
        .attr("x", (d, i) => i * barWidth + barWidth / 2) // Set x position based on data
        .attr("y", svgHeight - 2); // Set y position
}

