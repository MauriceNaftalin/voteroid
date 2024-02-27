function showQuestionName(text) {
    document.getElementById('questionName').textContent = text;
}

function setUpSvg(svgWidth, svgHeight) {
    var data = [0, 0, 0];
    const svg = d3.select("svg");
    var barWidth = svgWidth / data.length;

    svg.selectAll("rect")
        .data(data)
        .enter().append("rect")
        .attr("x", (d, i) => i * barWidth)
        .attr("y", d => svgHeight - d * 10) // Scale the bar height
        .attr("width", barWidth - 1)
        .attr("height", d => d * 10)
        .attr("fill", "steelblue")
    ;
}

function setVoteCount(voteUpdate) {
    console.log('answer: ' + voteUpdate)
    const voteCount = voteUpdate.voteCount;
    d3.select("svg").selectAll(`rect:nth-child(${voteUpdate.answerIndex})`)
        .attr("height", function (d) {
            return voteCount * 10;
        })
        .attr("y", function (d) {
            return chartHeight - (voteCount * 10);
        });
}

function setColumnLabels(labelsText) {
    d3svg.selectAll("text")
        .data(labelsText)
        .enter()
        .append("text")
        .text(d => d) // Set text content based on data
        .attr("text-anchor", "middle") // Center-align the text
        .attr("x", (d, i) => i * barWidth + barWidth / 2) // Set x position based on data
        .attr("y", svgHeight - 2); // Set y position
}

