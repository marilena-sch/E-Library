'use strict';

function findTotalCount(str) {
    var count = 0;
  
    for (var ch of str) {
      if (ch >= "0" && ch <= "9") {
        count++;
      }
    }
    return count;
}

function hasUpperCase(str) {
    return str !== str.toLowerCase();
}

function hasLowerCase(str) {
  return str !== str.toUpperCase();
}

function containsSpecialChars(str) {
  const specialChars = /[`!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?~]/;
    var count = 0;
  
    for (var ch of str) {
      if (specialChars.test(ch)==true) {
        count++;
      }
      else {
          count=count;
      }
    }
    return count;
}

function check_Strong(str) {
  
  if(hasLowerCase(str)==true && hasUpperCase(str)==true &&
  containsSpecialChars(str)>=2){
      return true;
  }
  else{
      return false;
  }
}

var checker = function() {
    var text=document.getElementById("password").value;
    var s_checker=document.getElementById("strenght_checker");
    var submitbtn=document.getElementById("submitbtn");
    var message=document.getElementById("msg");

    // for(var i=0; i<text.length;i++){
    //     console.log(text.charAt(i));
    // }
    
    var digits = findTotalCount(text);

    var medium = (text.length)/2;

    if (s_checker.checked == false){
      message.style.display = "none";
    } else {
      message.style.display = "block";
    }

    if(check_Strong(text)==true && digits<medium){
      message.style.color = 'green';
      message.innerHTML = 'Strong';
    }
    else if (digits>medium){
      message.style.color = 'red';
      message.innerHTML = 'Weak';
      submitbtn.disabled=true;
    }
    else {
      message.style.color = 'orange';
      message.innerHTML = 'Medium';
    }
};

var uni_checker = function() {
  var not_allowed=document.getElementById("not_allowed");
  var text=document.getElementById("password").value;

  if(text.includes('helmepa') || text.includes('uoc') || text.includes('tuc')){
    not_allowed.style.display='block';
    not_allowed.style.color = 'red';
    not_allowed.innerHTML = 'Not allowed';
  }
  else{
    not_allowed.style.display='none';
  }
};
