var svg;
var chartHeight;
var barWidth;

function showQuestionName(text) {
    document.getElementById('questionNameLabel').textContent = text;
}

function setUpSvg(svgWidth, svgHeight) {
    svg = d3.select("svg");
    chartHeight = svgHeight;
    var data = [0, 0, 0];
    barWidth = svgWidth / data.length;

    svg.selectAll("rect")
        .data(data)
        .enter().append("rect")
        .attr("x", (d, i) => i * barWidth)
        .attr("y", d => svgHeight - d * 10) // Scale the bar height
        .attr("width", barWidth - 1)
        .attr("height", d => d * 10)
        .attr("fill", "steelblue");
}

function setVoteCount(voteUpdate) {
    console.log('answer: ' + voteUpdate)
    const voteCount = voteUpdate.voteCount;
    svg.selectAll(`rect:nth-child(${voteUpdate.answerIndex})`)
        .attr("height", function (d) {
            return voteCount * 10;
        })
        .attr("y", function (d) {
            return chartHeight - (voteCount * 10);
        });
}

/*
function setColumnLabels(labelsText) {
    svg.selectAll("text")
        .data(labelsText)
        .enter()
        .append("text")
        .text(d => d) // Set text content based on data
        .attr("text-anchor", "middle") // Center-align the text
        .attr("x", (d, i) => i * barWidth + barWidth / 2) // Set x position based on data
        .attr("y", chartHeight - 2); // Set y position
}
*/

