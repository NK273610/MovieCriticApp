$(document).ready(function(){
	var movieid = sessionStorage.getItem("movieid");

	fetchMoviedetails(movieid);
    showingreviews(movieid);
    getWordCloud(movieid);
    getRating(movieid);
    getPieChart(movieid);
    getBarData(movieid);

    //getReviewData(movieid);
    
	
});

function checkUserReview() {

    var movieid = sessionStorage.getItem("movieid");
    $.ajax({
        type : 'GET',
        url : "/getUsername",
        dataType : 'json',
        success : function(data){

            var username=data.username;
            $.ajax({
                type : 'GET',
                url : "/fetchUserReviews",
                dataType : 'json',
                data :{id:username,movieid1:movieid},
                success : function(data){
                    if(data.length==0)
                    {
                        window.location.replace("addreviewpage.html");
                    }
                    else {
                        var z=0;
                        for(var i=0;i<data.length;i++)
                        {
                            if(data[i].movie_id==movieid)
                            {
                               z=1;
                            }
                        }
                        if(z==1)
                        {
                            alert("Movie has already been reviewed by the user");
                        }
                        else
                        {
                            window.location.replace("addreviewpage.html");
                        }

                    }
                }
            });
        }
    });


}

function getPieChart(movieid){
    $.ajax({
        type : 'GET',
        url : "/getAgegrp",
        data: {id: movieid},
        dataType : 'json',
        success : function(data){
            var dataset = [];
            for(var prop in data) {
                dataset.push({label: prop, value: data[prop]});

            }
            var width = 300,
                height = 300,
                radius = Math.min(width, height) / 2;
            var donutWidth = 75;
            var legendRectSize = 18;
            var legendSpacing = 4;
            var colorScheme = ["#E57373","#BA68C8","#7986CB","#A1887F","#90A4AE","#AED581","#9575CD","#FF8A65","#4DB6AC","#FFF176","#64B5F6","#00E676"];
            dataset.forEach(function(item){
                item.enabled = true;
            });
            var color = d3.scale.ordinal()
                .range(colorScheme);

            var svg = d3.select("#piechart")
                .append("svg")
                .attr("width", width)
                .attr("height", height)
                .append("g")
                .attr("transform", "translate(" + width / 2 + "," + height / 2 + ")");

            var arc = d3.svg.arc()
                .outerRadius(radius - 10)
                .innerRadius(radius - donutWidth);

            var pie = d3.layout.pie()
                .sort(null)
                .value(function(d) { return d.value; });

            var tooltip = d3.select("#piechart")
                .append('div')
                .attr('class', 'tooltip');

            tooltip.append('div')
                .attr('class', 'label');

            tooltip.append('div')
                .attr('class', 'count');

            tooltip.append('div')
                .attr('class', 'percent');

            var path = svg.selectAll('path')
                .data(pie(dataset))
                .enter()
                .append('path')
                .attr('d', arc)
                .attr('fill', function(d, i) {
                    return color(d.data.label);
                })
                .each(function(d) { this._current = d; });


            path.on('mouseover', function(d) {
                var total = d3.sum(dataset.map(function(d) {
                    return (d.enabled) ? d.value : 0;
                }));

                var percent = Math.round(1000 * d.data.value / total) / 10;
                tooltip.select('.label').html(d.data.label.toUpperCase()).style('color','black');
                tooltip.select('.count').html(d.data.value);
                tooltip.select('.percent').html(percent + '%');

                tooltip.style('display', 'block');
                tooltip.style('opacity',2);
            });

            path.on('mousemove', function(d) {
                tooltip.style('top', (d3.event.layerY + 10) + 'px')
                    .style('left', (d3.event.layerX - 25) + 'px');
            });

            path.on('mouseout', function() {
                tooltip.style('display', 'none');
                tooltip.style('opacity',0);
            });

            var legend = svg.selectAll('.legend')
                .data(color.domain())
                .enter()
                .append('g')
                .attr('class', 'legend')
                .attr('transform', function(d, i) {
                    var height = legendRectSize + legendSpacing;
                    var offset =  height * color.domain().length / 2;
                    var horz = -2 * legendRectSize;
                    var vert = i * height - offset;
                    return 'translate(' + horz + ',' + vert + ')';
                });

            legend.append('rect')
                .attr('width', legendRectSize)
                .attr('height', legendRectSize)
                .style('fill', color)
                .style('stroke', color)
                .on('click', function(label) {
                    var rect = d3.select(this);
                    var enabled = true;
                    var totalEnabled = d3.sum(dataset.map(function(d) {
                        return (d.enabled) ? 1 : 0;
                    }));

                    if (rect.attr('class') === 'disabled') {
                        rect.attr('class', '');
                    } else {
                        if (totalEnabled < 2) return;
                        rect.attr('class', 'disabled');
                        enabled = false;
                    }

                    pie.value(function(d) {
                        if (d.label === label) d.enabled = enabled;
                        return (d.enabled) ? d.value : 0;
                    });

                    path = path.data(pie(dataset));

                    path.transition()
                        .duration(750)
                        .attrTween('d', function(d) {
                            var interpolate = d3.interpolate(this._current, d);
                            this._current = interpolate(0);
                            return function(t) {
                                return arc(interpolate(t));
                            };
                        });
                });


            legend.append('text')
                .attr('x', legendRectSize + legendSpacing)
                .attr('y', legendRectSize - legendSpacing)
                .text(function(d) { return d; })
        }
    });
}

function getWordCloud(movieid){


    $.ajax({
        type : 'GET',
        url : "/getWords",
        data: {id: movieid},
        dataType : 'json',
        success : function(data){
            var wordCountArr = [];

            for(var prop in data) {
                wordCountArr.push({text: prop, size: data[prop]});
            }
            var width = 800;
            var height = 200;

            var typeFace = 'Gorditas';
            var minFontSize = 24;
            var colors = d3.scale.category20b();

            var svg = d3.select('#cloud').append('svg')
                .attr('width', width)
                .attr('height', height)
                .append('g')
                .attr('transform', 'translate('+width/2+', '+height/2+')');

            calculateCloud(wordCountArr);
            function calculateCloud(wordCount) {
                d3.layout.cloud()
                    .size([width, height])
                    .words(wordCount)
                    .rotate(function() { return ~~(Math.random()*2) * 90;}) // 0 or 90deg
                    .font(typeFace)
                    .fontSize(function(d) { return d.size * minFontSize; })
                    .on('end', drawCloud)
                    .start();
            }

            function drawCloud(words) {
                var vis = svg.selectAll('text').data(words);

                vis.enter().append('text')
                    .style('font-size', function(d) { return d.size + 'px'; })
                    .style('font-family', function(d) { return d.font; })
                    .style('fill', function(d, i) { return colors(i); })
                    .attr('text-anchor', 'middle')
                    .attr('transform', function(d) {
                        return 'translate(' + [d.x, d.y] + ')rotate(' + d.rotate + ')';
                    })
                    .text(function(d) { return d.text; });
            }
        }
    });
}

function getBarData(movieid){
    $.ajax({
        type : 'GET',
        url : "/getAgeGroupBar",
        data: {id: movieid},
        dataType : 'json',
        success : function(data){

            var data_age = [];

            for(var prop in data) {
                data_age.push({text: prop, frequency: data[prop]});
            }
            var margin = {top: 40, right: 20, bottom: 30, left: 40},
                width = 300 - margin.left - margin.right,
                height = 300 - margin.top - margin.bottom;

            var x = d3.scale.ordinal()
                .rangeRoundBands([0, width], .1);

            var y = d3.scale.linear()
                .range([height, 0]);

            var xAxis = d3.svg.axis()
                .scale(x)
                .orient("bottom");

            var yAxis = d3.svg.axis()
                .scale(y)
                .orient("left")


            var tip = d3.tip()
                .attr('class', 'd3-tip')
                .offset([-10, 0])
                .html(function(d) {
                    return "<strong>Average Rating:</strong> <span style='color:red'>" + d.frequency + "</span>";
                })

            var svg = d3.select("#bar").append("svg")
                .attr("width", width + margin.left + margin.right)
                .attr("height", height + margin.top + margin.bottom)
                .append("g")
                .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

            svg.call(tip);
            x.domain(data_age.map(function(d) { return d.text; }));
            y.domain([0, d3.max(data_age, function(d) { return d.frequency; })]);

                svg.append("g")
                    .attr("class", "x axis")
                    .attr("transform", "translate(0," + height + ")")
                    .call(xAxis);

                svg.append("g")
                    .attr("class", "y axis")
                    .call(yAxis)
                    .append("text")
                    .attr("transform", "rotate(-90)")
                    .attr("y", 6)
                    .attr("dy", ".71em")
                    .style("text-anchor", "end")

                svg.selectAll(".bar")
                    .data(data_age)
                    .enter().append("rect")
                    .attr("class", "bar")
                    .attr("x", function(d) { return x(d.text); })
                    .attr("width", x.rangeBand())
                    .attr("y", function(d) { return y(d.frequency); })
                    .attr("height", function(d) { return height - y(d.frequency); })
                    .on('mouseover', tip.show)
                    .on('mouseout', tip.hide);

            function type(d) {
                d.frequency = +d.frequency;
                return d;
            }


        }
    });
}

function getRating(movieid){
    $.ajax({
        type : 'GET',
        url : "/getRating",
        data: {id: movieid},
        dataType : 'json',
        success : function(data){
            document.getElementById("vote").innerText=data;
        }
    });
}


function fetchMoviedetails(movieid){
	 $.ajax({
         type : 'GET',
         url : "/getmoviedetails",
         data: {id: movieid},
         dataType : 'json',
         success : function(data){  
        	 document.getElementById("heading").innerText = data[0].title;
         	document.getElementById("picture").src = data[0].imageURL;
        	  	document.getElementById("overview").innerText = data[0].overview;
        	  	document.getElementById("date").innerText = data[0].releaseDate;
         },
         error: function(data){
        	 console.log(data.responseText);
        	 sessionStorage.removeItem("error");
        	 sessionStorage.setItem("error", data.responseText);
        	 window.location.replace("error.html");
         }
     });
}

function showingreviews(movieid){
    $.ajax({
        type : 'GET',
        url : "/fetchReviews",
        data: {id: movieid},
        dataType : 'json',
        success : function(data){

            var reviewTemplate = document.getElementsByTagName("template")[0];
            item = reviewTemplate.content.querySelector("div");

            for (i = 0; i < data.length; i++) {
                firstDiv = document.importNode(item, true);
                // secondDiv = document.importNode(item, true);
                reviews = firstDiv.children[2];
                user_name = firstDiv.children[0];

                rating = firstDiv.children[1];

                reviews.innerText = data[i].reviews;
                user_name.innerText = data[i].user_name;

                rating.innerText = data[i].rating;

                document.getElementById('reviewresult').appendChild(firstDiv);
            }
        }
    });
}

function showUser(event){

    //alert(event.srcElement.innerText);
    //console.log(event)
    sessionStorage.removeItem("username");
    sessionStorage.setItem("username", event.srcElement.innerText);
    //alert(sessionStorage.getItem("username"));
    //document.getElementById("#username1").value=username;
    // userDetails();
    // showingUserReviews();


}