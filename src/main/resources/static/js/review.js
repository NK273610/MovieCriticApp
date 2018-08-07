function addReview(){


    var movieid= sessionStorage.getItem("movieid");
    //var userid=sessionStorage.getItem("username");

    getReviewData(movieid);
    //document.getElementById("movie_id").value = movieid.value;
}
function updateHidden(rating) {
    document.getElementById("stars").value = rating.value;
}
/*function getReviewData(movieid)
{

    //call addReview in controller
    $.ajax({
        type : 'GET',
        url : "/getUsername",
        dataType : 'json',
        success : function(data){
            alert(data.username);
            addReviews(movieid,data.username,rating,likablity,review,agegrp)

        }
    });


}*/

function addReviews(){
    var movieid= sessionStorage.getItem("movieid");
    var rating=document.getElementById("stars").value;
    var likablity=document.getElementById("likeoption").value;
    var review=document.getElementById("movie_review").value;
    var agegrp=document.getElementById("agegrp").value;
    var reviews = {movie_id:movieid, rating:rating, likablity:likablity, reviews:review, age_group:agegrp};
    $.ajax({
        type : 'POST',
        url : "/addReviews",
        data: JSON.stringify(reviews),
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        success : function(data){
            if(data){
                alert("Review added successfully");
                window.location.replace("index.html");
            }else{
                alert("Bad language used! Please correct your review");
                window.location.replace("addreviewpage.html");
            }
        },error: function(data){
            window.location.replace("error.html");
        }
    });
}