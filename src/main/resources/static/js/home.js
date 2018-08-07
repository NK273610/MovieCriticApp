$(document).ready(function(){ 
	upcoming();
	popular();
	fetchusername();
	fetchRecommendedMovies();
	$("#search").click(function(){
	    var moviename = document.getElementById("searchmovie");
	    search(moviename.value)
	});
	
	
});


function upcoming(){
	 $.ajax({
           type : 'GET',
           url : "/getupcomingmovies",
           dataType : 'json',
           success : function(data){  
        	   	var template = document.getElementsByTagName("template")[1];
   				item = template.content.querySelector("div");
   				for (i = 0; i < 10; i++) {
   					a = document.importNode(item, true);
   					aTag = a.firstElementChild;
   					aTag.innerText = data[i].title;
   					aTag.id = data[i].id;

   					document.getElementById('movielist').appendChild(a);
   				}
           },
           error: function(data){
          	 console.log(data.responseText);
          	 sessionStorage.removeItem("error");
          	 sessionStorage.setItem("error", data.responseText);
          	 window.location.replace("error.html");
           }   
       });
}

function popular(){
	 $.ajax({
          type : 'GET',
          url : "/getpopularmovies",
          dataType : 'json',
          success : function(data){  
        	  	var template = document.getElementsByTagName("template")[0];
 				item = template.content.querySelector("a");
 				for (i = 0; i < 3; i++) {
 					a = document.importNode(item, true);
 					a.id = data[i].id;
 					aTag = a.firstElementChild;
 					aTag.src = data[i].imageURL;

 					document.getElementById('movieimage').appendChild(a);
 				}
          },
          error: function(data){
         	 console.log(data.responseText);
         	 sessionStorage.removeItem("error");
         	 sessionStorage.setItem("error", data.responseText);
         	 window.location.replace("error.html");
          }   
      });
}

function fetchusername(){
	 $.ajax({
         type : 'GET',
         url : "/getUsername",
         dataType : 'json',
         success : function(data){  
             console.log(data)
             document.getElementById("username").textContent = data.firstname + " " + data.lastname;
         }   
     });
}

function search(name) {
    sessionStorage.removeItem("searchmoviename");
	sessionStorage.setItem("searchmoviename", name);
}

function displayMovie(event){
	sessionStorage.removeItem("movieid");
	sessionStorage.setItem("movieid", event.id);
}


function getTheatreDetails() {
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/getTheatreDetails",
		data : null,
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {
			$('.theatre').children().remove().end();
			var htmlString = '';
			$.each(data, function(index, value) {
				htmlString += '<button class="accordion" type="button" id="'
						+ index + '">' + index.toUpperCase()
						+ '</button><div class="panel" id="' + index.replace(/ /gi,"-") + '-slot" style="display:none;">';
				$.each(value, function(index, value) {
					htmlString += '<div>' + index
							+ '</div><div class="btn-group">';
					for ( var item in value) {
						htmlString += '<button>' + value[item] + '</button>';
					}
					htmlString += '</div>';
				});
				htmlString += '</div>';
			});
			$('.theatre').append(htmlString);
		},
		error : function(e) {
			alert(e);
		}
	});

}

$(document).on("click", ".accordion", function(e) {
	$('.panel').hide();
	var theatre = $(this).attr('id').replace(/ /gi,'-')+'-slot';
	$('#'+theatre).show();

});


function getUserProfile() {
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/getUserProfile",
		data : null,
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {
			$('#first').children().remove().end();
			$('#last').children().remove().end();
			$('#username').children().remove().end();

			$('#first').val(data.firstname);
			$('#last').val(data.lastname);
			$('#username').val(data.useremail);
			if (data.imagePath != null) {
				$('#myImg1').attr('src', data.imagePath);
			} else {
				$('#myImg1').attr('src', 'upload/dummyPic.jpg');

			}

		},
		error : function(e) {
			alert(e);
		}
	});
}

function updateUserProfile() {
	var form = $('#userProfileForm')[0];
	var data = new FormData(form);
	$("#myButton").prop("disabled", true);
	$.ajax({
		type : "POST",
		enctype : 'multipart/form-data',
		url : "/updateUserProfile",
		data : data,
		processData : false,
		contentType : false,
		cache : false,
		timeout : 1000000,
		success : function(data) {
			alert(data);
			$("#fileUploadForm").prop("disabled", true);
		},
		error : function(e) {
			alert(e);
		}
	});
}

function fetchRecommendedMovies(){
	 $.ajax({
         type : 'GET',
         url : "/fetchRecommendedMovie",
         dataType : 'json',
         success : function(data){  
     	  	var template = document.getElementsByTagName("template")[2];
				item = template.content.querySelector("div");
				for (i = 0; i < 3; i++) {
					a = document.importNode(item, true);
					aTag = a.firstElementChild;
					aTag.id = data[i].id;
					aTag.innerHTML = data[i].title;

					document.getElementById('recommendmovie').appendChild(a);
				}
       },
       error: function(data){
      	 console.log(data.responseText);
      	 sessionStorage.removeItem("error");
      	 sessionStorage.setItem("error", data.responseText);
      	 window.location.replace("error.html");
       }  
     });
}
