function changepassword(){
	var password = document.getElementById("newpassword").value;

	 $.ajax({
        type : 'POST',
        url : "/changePassword",
        data: {password: password},
        dataType : 'json',
        success : function(data){  
        	window.location.replace("index.html");
        	alert("password changed successfully")
        },
        error: function(data){
        	alert("Password cannot be changed. Please try again later")    
        }
    });

}