$(document).ready(function() {
	if (window.location.href.indexOf("error") > -1) {
		$("#error").show();
	}
});


function forgotPassword(){
var emailId = $('#userEmail').val();
	$.ajax({
           type : 'GET',
           url : "/forgotpassword?email=" + emailId,
           dataType : 'text',
           success : function(data){ 
           $('#feedback').html(data);
           },
           error : function(e) {
           $('#feedback').html("Something went wrong");
           }
       });

}