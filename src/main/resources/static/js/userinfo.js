function changepassword(){
	var password = document.getElementById("newpassword").value;

	 $.ajax({
        type : 'POST',
        url : "/changePassword",
        data: {password: password},
        dataType : 'json',
        success : function(data){  
        	console.log(data);
        },
        error: function(data){
        	console.log(data)        
        }
    });

}