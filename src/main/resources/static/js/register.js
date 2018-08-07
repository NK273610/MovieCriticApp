function validate() {
	var firstname = document.getElementById("firstname").value
	var lastname = document.getElementById("lastname").value
	var password = document.getElementById("password").value
	var email = document.getElementById("email").value
	var username = document.getElementById("username").value
	var confirmpassword = document.getElementById("confirmpassword").value;

	var successFlag = true;
	$.ajax({
		type : 'GET',
		url : "/registrationconfig",
		dataType : 'json',
		success : function(data) {
			if (data["name"] != null) {
				var pattern = new RegExp(data["name"]);
				regex = pattern.test(firstname);
				if (regex == false) {
					successFlag = false
					alert("Firstname is not valid!")
				}
			}

			if (data["name"] != null) {
				var pattern = new RegExp(data["name"]);
				regex = pattern.test(lastname);
				if (regex == false) {
					successFlag = false
					alert("Lastname is not valid!")
				}
			}

			if (data["username"] != null) {
				var pattern = new RegExp(data["username"]);
				regex = pattern.test(username);
				if (regex == false) {
					successFlag = false
					alert("Username is not valid!")
				}
			}

			if (data["email"] != null) {
				var pattern = new RegExp(data["email"]);
				regex = pattern.test(email);
				if (regex == false) {
					successFlag = false
					alert("Email is not valid!")
				}
			}

			if (data["password"] != null) {
				var pattern = new RegExp(data["password"]);
				regex = pattern.test(password);
				if (regex == false) {
					successFlag = false
					alert("password is not valid!")
				}
			}
			if (password != confirmpassword) {
				successFlag = false
				alert("Passwords do not match.");
			}
			
			if(successFlag == true){
				// alert("Successfull")
				var person = {firstname:firstname, lastname:lastname, userpass:password, useremail:email, username:username};
				console.log(person);
				check(person);
		}

		},
		error : function(data) {
			console.log(data);
		}
	});

}

function check(person){
    $.ajax({
        type : "POST",
        url : "/addUser",
        contentType : 'application/json; charset=utf-8',
        data: JSON.stringify(person),
        dataType : 'json',
        success : function(data){
            if(data){
                window.location.replace("index.html");
            }else{
                alert("Couldn't register because Username/Email found in the database.");
                window.location.href("register.html");
            }
        },error: function(data){
            window.location.href("error.html");
        }
    });
}