'use strict';

var CheckboxChecker = function() {
      // Get the checkbox
  var checkBox = document.getElementById("terms");
  // Get the output text
  var text = document.getElementById("txt");

  // If the checkbox is checked, display the output text
  if (checkBox.checked == false){
    text.style.display = "block";
  } else {
    text.style.display = "none";
  }
};