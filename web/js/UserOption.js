'use strict';

var user_option = function() {
  var user_type = document.getElementById("user_type").value;
  var l_student = document.getElementById("l_student");
  var student_type = document.getElementById("student_type");
  var l_id = document.getElementById("l_id");
  var student_id = document.getElementById("student_id");
  var c_date = document.getElementById("c_date");
  var student_id_from_date = document.getElementById("student_id_from_date");
  var e_date = document.getElementById("e_date");
  var student_id_to_date = document.getElementById("student_id_to_date");
  var l_uni = document.getElementById("l_uni");
  var university = document.getElementById("university");
  var l_dept = document.getElementById("l_dept");
  var department = document.getElementById("department");
  var l_address = document.getElementById("l_address");
  var l_library = document.getElementById("l_library");
  var libraryname = document.getElementById("libraryname");
  var l_info = document.getElementById("l_info");
  var libraryinfo = document.getElementById("libraryinfo");

  if(user_type === "student"){
    l_student.style.display = "block";
    l_student.innerHTML="*Student Type:";
    student_type.style.display = "block";
    student_type.setAttribute('required', '');
    l_id.style.display = "block";
    l_id.innerHTML="*Student ID:";
    student_id.style.display = "block";
    student_id.setAttribute('required', '');
    c_date.style.display = "block";
    c_date.innerHTML="*Student ID's Commencement Date:";
    student_id_from_date.style.display = "block";
    student_id_from_date.setAttribute('required', '');
    e_date.style.display = "block";
    e_date.innerHTML="*Student ID's Expiration Date:";
    student_id_to_date.style.display = "block";
    student_id_to_date.setAttribute('required', 'required');
    l_uni.style.display = "block";
    l_uni.innerHTML="*University:";
    university.style.display = "block";
    university.setAttribute('required', '');
    l_dept.style.display = "block";
    l_dept.innerHTML="*Department:";
    department.style.display = "block";
    department.setAttribute('required', '');
    l_library.style.display="none";
    libraryname.style.display="none";
    libraryname.removeAttribute('required');
    l_info.style.display="none";
    libraryinfo.style.display="none";
    libraryinfo.removeAttribute('required');
    l_address.innerHTML="Home Address";
  }
  else if(user_type === "librarian"){
    l_student.style.display = "none";
    student_type.style.display = "none";
    student_type.removeAttribute('required');
    l_id.style.display = "none";
    student_id.style.display = "none";
    student_id.removeAttribute('required');
    c_date.style.display = "none";
    student_id_from_date.style.display = "none";
    student_id_from_date.removeAttribute('required');
    e_date.style.display = "none";
    student_id_to_date.style.display = "none";
    student_id_to_date.removeAttribute('required');
    l_uni.style.display = "none";
    university.style.display = "none";
    university.removeAttribute('required');
    l_dept.style.display = "none";
    department.style.display = "none";
    department.removeAttribute('required');
    l_library.style.display="block";
    libraryname.style.display="block";
    l_info.style.display="block";
    libraryinfo.style.display="block";
    l_address.innerHTML="Library Address";
  }
};

var checkEmail = function(){
  var text=document.getElementById("email").value;
  var university = document.getElementById("university").value;
  var check_mail = document.getElementById("check_mail");
  var txt1 = text.slice(-6);
  var txt2 = text.slice(-10);

  if(university === "uoc"){
    if(txt1 === 'uoc.gr'){
      check_mail.style.color = 'green';
      check_mail.innerHTML = 'Email address matches university';
    }
    else{
      check_mail.style.color = 'red';
      check_mail.innerHTML = "Email address and university don't match";
    }
  }
  else if(university === "tuc"){
    if(txt1 === 'tuc.gr'){
      check_mail.style.color = 'green';
      check_mail.innerHTML = 'Email address matches university';
    }
    else{
      check_mail.style.color = 'red';
      check_mail.innerHTML = "Email address and university don't match";
    }
  }
  else if(university === "helmepa"){
    if(txt2 === 'helmepa.gr'){
      check_mail.style.color = 'green';
      check_mail.innerHTML = 'Email address matches university';
    }
    else{
      check_mail.style.color = 'red';
      check_mail.innerHTML = "Email address and university don't match";
    }
  }
};

var checkDate = function(){
  var date1=document.getElementById("student_id_from_date").value;
  var date2=document.getElementById("student_id_to_date").value;
  var check_date=document.getElementById("check_date");

  if(date1<date2){
    check_date.style.color = 'green';
    check_date.innerHTML = 'Commencement date before expiration';
  }
  else{
    check_date.style.color = 'red';
    check_date.innerHTML = 'Commencement date after expiration';
  }
};

function padTo2Digits(num) {
  return num.toString().padStart(2, '0');
}

function formatDate(date) {
  const new_date = new Date(date);
  return [
    new_date.getFullYear(),
    padTo2Digits(new_date.getMonth() + 1),
    padTo2Digits(new_date.getDate()),
  ].join('-');
}

var checkDuration = function(){
  var student_type=document.getElementById("student_type").value;
  var date = document.getElementById("student_id_from_date").value;
  var date1=new Date(date);
  var date2=document.getElementById("student_id_to_date").value;
  var check_duration=document.getElementById("check_duration");
  var under_grad = date1.setFullYear(date1.getFullYear() + 6);
  var date_undergrad = formatDate(under_grad);
  var date3=new Date(document.getElementById("student_id_from_date").value);
  var grad = date3.setFullYear(date3.getFullYear() + 2);
  var date_grad = formatDate(grad);
  var date4=new Date(document.getElementById("student_id_from_date").value);
  var doctorate = date4.setFullYear(date4.getFullYear() + 5);
  var date_doctorate = formatDate(doctorate);

  if(!date){
    console.log('NULL');
  }
  else{
    if(student_type === 'undergraduate'){
      if(date_undergrad >= date2){
        check_duration.style.color = 'green';
        check_duration.innerHTML = "Duration doesn't exceed 6 years";
      }
      else{
        check_duration.style.color = 'red';
        check_duration.innerHTML = "Duration exceeds 6 years";
      }
    }
    else if(student_type === 'graduate'){
      if(date_grad >= date2){
        check_duration.style.color = 'green';
        check_duration.innerHTML = "Duration doesn't exceed 2 years";
      }
      else{
        check_duration.style.color = 'red';
        check_duration.innerHTML = "Duration exceeds 2 years";
      }
    }
    else if(student_type === 'doctorate'){
      if(date_doctorate >= date2){
        check_duration.style.color = 'green';
        check_duration.innerHTML = "Duration doesn't exceed 5 years";
      }
      else{
        check_duration.style.color = 'red';
        check_duration.innerHTML = "Duration exceeds 5 years";
      }
    }
  }
};

var back_btn = function(){
    var not_registered = document.getElementById("not_registered");
    var sign_in = document.getElementById("sign_in");
    var sign_div = document.getElementById("sign_div");
    var back = document.getElementById("back");
    
    sign_in.style.display = "none";
    back.style.display = "none";
    not_registered.style.display="block";
    sign_div.style.display="block";
};