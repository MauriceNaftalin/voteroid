var data = [0,0,0];
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

    svg.selectAll("text")
        .data(labels1)
        .enter()
        .append("text")
        .text(d => d) // Set text content based on data
        .attr("text-anchor", "middle") // Center-align the text
        .attr("x", (d, i) => i * barWidth + barWidth / 2) // Set x position based on data
        .attr("y", svgHeight - 30); // Set y position
}

