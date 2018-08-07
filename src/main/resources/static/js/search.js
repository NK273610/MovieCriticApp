$(document).ready(function() {
	var searchmoviename = sessionStorage.getItem("searchmoviename");
	searchresults(searchmoviename);
});

function displayMovie(event){
	sessionStorage.removeItem("movieid");
	sessionStorage.setItem("movieid", event.id);
}

function searchresults(searchmoviename) {
	$.ajax({
		type : 'GET',
		url : "/getsearchedmovies",
		data : {
			searchmoviename : searchmoviename
		},
		dataType : 'json',
		success : function(data) {
			var imgTemplate = document.getElementsByTagName("template")[0];
			item = imgTemplate.content.querySelector("div");
			var labelTemplate = document.getElementsByTagName("template")[1];
			labelitem = labelTemplate.content.querySelector("div");
			if(data.length === 0) {
				document.getElementById('movieresult').innerText = "No movies with name" + searchmoviename;
				document.getElementById('movieresult').style.marginTop = "10%";
			}
			
			for (i = 0; i < data.length; i++) {
					a = document.importNode(item, true);
					a.id = data[i].id;
					aTag = a.firstElementChild;
					aTag.src = data[i].imageURL;

					labelDiv = document.importNode(labelitem, true);
					
					moviename = labelDiv.children[0];
					moviename.innerText = data[i].title;
					
					moviedescription = labelDiv.children[1];
					moviedescription.innerText = data[i].overview;
					
					viewbutton = labelDiv.children[2];
					viewbutton.id = data[i].id;
					
					
					
					document.getElementById('movieresult').appendChild(a);
					document.getElementById('movieresult').appendChild(labelDiv);
				}
		}, error: function(data){
			document.getElementById('movieresult').innerText = "No movies found";
			document.getElementById('movieresult').style.marginTop = "10%";
		}
	});
}