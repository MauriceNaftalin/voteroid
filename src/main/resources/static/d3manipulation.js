var svg;
var chartHeight;
var barWidth;

function showQuestionName(text) {
    document.getElementById('questionNameLabel').textContent = text;
}

function setUpSvg(svgElement, svgWidth, svgHeight) {
    svgElement.setAttribute("width", svgWidth);
    svgElement.setAttribute("height", svgHeight);
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
