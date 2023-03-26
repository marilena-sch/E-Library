'use strict';

var confirmation = function() {
  if (document.getElementById("password").value ==
    document.getElementById("confirm_password").value) {
    document.getElementById("confirm_password").style.color = 'green';
    document.getElementById("message").style.color = 'green';
    document.getElementById('message').innerHTML= 'Confirmed';
  } 
  else {
	  document.getElementById("confirm_password").style.color = 'red';
    document.getElementById("message").style.color = 'red';
    document.getElementById("message").innerHTML = 'Different password';
  }
};

var visibility = function() {
    var x = document.getElementById("password");
    if (x.type === "password") {
      x.type = "text";
    } else {
      x.type = "password";
    }
};