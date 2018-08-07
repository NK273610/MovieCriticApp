$(document).ready(function(){
   // var movieid = sessionStorage.getItem("movieid");

   var username = sessionStorage.getItem("username");
    //fetchusername();
    showingUserReviews()
//showUser();

});

function fetchusername(){
    $.ajax({
        type : 'GET',
        url : "/getUsername",
        dataType : 'json',
        success : function(data){
            console.log(data)
            document.getElementById("username1").textContent = data.firstname + " " + data.lastname;
        }
    });
}

function showingUserReviews(){
    var userid=sessionStorage.getItem("username");
    var movieid=sessionStorage.getItem("movieid");
    //alert("In showingreview function");
    document.getElementById("username1").textContent = userid;
    $.ajax({
        type : 'GET',
        url : "/fetchUserReviews",
        data:{id :userid, movieid1:movieid},
        dataType : 'json',
        success : function(data){
            console.log(data);
            var reviewTemplate = document.getElementsByTagName("template")[0];
            item = reviewTemplate.content.querySelector("div");

            for (i = 0; i < data.length; i++) {
                firstDiv = document.importNode(item, true);
                // reviews =     firstDiv.children[1];
                // movie_name = firstDiv.children[0];
                // rating = firstDiv.children[2];

                reviews = firstDiv.children[2];
                movie_name = firstDiv.children[0];
                rating = firstDiv.children[1];

                reviews.innerText = data[i].reviews;

                rating.innerText = data[i].rating;

                movie_name.innerHTML = data[i].movieName;

                document.getElementById('reviewresult').appendChild(firstDiv);

            }

        }
    });
}