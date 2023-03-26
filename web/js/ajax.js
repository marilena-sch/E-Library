
function createTableFromJSON(data) {
    var html = "<table><tr><th>Category</th><th>Value</th></tr>";
    for (const x in data) {
        var category = x;
        var value = data[x];
        html += "<tr><td>" + category + "</td><td>" + value + "</td></tr>";
    }
    html += "</table>";
    return html;
}

function createTableLibrariesFromJSON(data) {
    var html = "<table><tr><th>Category</th><th>Value</th></tr>";
    for (const x in data) {
        var category = x;
        var value = data[x];
        html += "<tr><td>" + category + "</td><td>" + value + "</td></tr>";
    }
    html += "<button class='select-btn' id='" + data['library_id'] + "' onclick='sendBorrowRequest(" + data['library_id'] + ")'> Borrow from here </button>";
    html += "</table>";
    return html;

}

function createTableFromJSON_new(data,len) {
    
    var html="<table><tr>";
    let data_cat = JSON.parse(data[0].toString());
    for (const x in data_cat) {
        var category = x;
        console.log(category);
        html += "<th>"+category+"</th>";
    }
        
    html += "</tr>";
    let data_v;
    for(let i=0; i<len-1; i++){
        html += "<tr>";
        data_v = JSON.parse(data[i].toString());
        for(const y in data_v){
            var value = data_v[y];
            html += "<td>"+value+"</td>";    
        }
        html += "<td><button class='accept-btn' id='" + data_v['borrowing_id'] + "' onclick='AcceptRequested(" + data_v['borrowing_id'] + ")'>Accept Request</button></td>";
        html += "</tr>";
    }
    html += "</table>";
    html += "<br>";
    return html;

}

function createReturnTableFromJSON_new(data,len) {
    
    var html="<table><tr>";
    let data_cat = JSON.parse(data[0].toString());
    for (const x in data_cat) {
        var category = x;
        console.log(category);
        html += "<th>"+category+"</th>";
    }
        
    html += "</tr>";
    let data_v;
    for(let i=0; i<len-1; i++){
        html += "<tr>";
        data_v = JSON.parse(data[i].toString());
        for(const y in data_v){
            var value = data_v[y];
            html += "<td>"+value+"</td>";    
        }
        html += "<td><button class='accept-btn' id='" + data_v['borrowing_id'] + "' onclick='AcceptReturned(" + data_v['borrowing_id'] + ")'>Accept Request</button></td>";
        html += "</tr>";
    }
    html += "</table>";
    html += "<br>";
    return html;

}

function createTableRequestedFromJSON(data) {
    var html = "<table><tr><th>Category</th><th>Value</th></tr>";
    for (const x in data) {
        var category = x;
        var value = data[x];
        html += "<tr><td>" + category + "</td><td>" + value + "</td></tr>";
    }
    html += "<button class='accept-btn' onclick='AcceptRequested()'>Accept Request</button>";
    html += "</table>";
    return html;

}

function createTableReturnedFromJSON(data) {
    var html = "<table><tr><th>Category</th><th>Value</th></tr>";
    for (const x in data) {
        var category = x;
        var value = data[x];
        html += "<tr><td>" + category + "</td><td>" + value + "</td></tr>";
    }
    html += "<button class='accept-btn' onclick='AcceptReturned()'>Accept Request</button>";
    html += "</table>";
    return html;

}

function createBooksFromJSON(data) {
    var html = "<table><tr><th>Category</th><th>Value</th></tr>";
    for (const x in data) {
        var category = x;
        var value = data[x];
        html += "<tr><td>" + category + "</td><td>" + value + "</td></tr>";
    }
    html += "<button class='select-btn' id='" + data['library_id'] + "' onclick='sendBorrowRequest(" + data['library_id'] + ")'> Borrow from here</button>";
    html += "</table>";
    return html;

}

var u_lon, u_lat, user_id;
var lib_name="";
function getUser() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
//            createTableFromJSON(JSON.parse(xhr.responseText));
            $("#result").html("Successful Login.");
            $("#sign_in").hide();
            $("#admin_div").html("");
            $("#sign_div").html("");
            $("#register_div").html("");
            $("#guest_div").html("");
            $("#library_div").html("");
            if (xhr.responseText.includes('lib')){
                lib_name = document.getElementById("user_username").value;
                $("#ajaxContent").load("librarian.html"); 
            }
            else{
                json = JSON.parse(xhr.responseText);
                u_lat = json['lat'];
                u_lon = json['lon'];
                user_id = json['user_id'];
               $("#ajaxContent").load("student.html");
            }
        } else if (xhr.status !== 200) {
             $("#ajaxContent").html("User not exists or incorrect password");
        }
    };
    var data = $('#loginForm').serialize();
    xhr.open('GET', 'UserSignIn?'+data);
    xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    xhr.send();
}

function RegisterButton(){
    const username=document.getElementById('username').value;
    const email=document.getElementById('email').value;
    const password=document.getElementById('password').value;
    const user=document.getElementById("user_type").value;
    if(user === "student"){
            user_type="student";
    }else{
            user_type="librarian";

    }
    const firstname=document.getElementById('firstname').value;
    const lastname=document.getElementById('lastname').value;
    const birthdate=document.getElementById('birthdate').value;
    if(document.getElementById("female").checked){
            gender="female";
    }else if(document.getElementById("male").checked){
            gender="male";
    }else{
            gender="other";
    }
    const country=document.getElementById('country').value;
    const city=document.getElementById('city').value;
    const address=document.getElementById('address').value;
    const student_type=document.getElementById('student_type').value;
    const student_id=document.getElementById('student_id').value;
    const student_id_from_date=document.getElementById('student_id_from_date').value;
    const student_id_to_date=document.getElementById('student_id_to_date').value;
    const university=document.getElementById('university').value;
    const department=document.getElementById('department').value;
    let lon = window.sessionStorage.getItem("lon");
    let lat = window.sessionStorage.getItem("lat");
    const libraryname=document.getElementById('libraryname').value;
    const libraryinfo=document.getElementById('libraryinfo').value;
    const telephone=document.getElementById('telephone').value;
    const personalpage=document.getElementById('personalpage').value;
//    var register_message = document.getElementById('register_message');
    
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
        $("#result").html("Successful Registration");
        $("#sign_div").html("");
        $("#register_div").html("");
        $("#guest_div").html("");
        $("#library_div").html("");
        $("#not_registered").hide();
        $("#ajaxContent").append("<button id='sign' class='sign' onclick='sign_in_btn()'>Sign In</button><br>");
    } else if (xhr.status !== 200) {
       $("#result").html("User already exists.");
       $("#not_registered").hide();
    }
    };
    xhr.open("POST","UserRegister?username=" + username + 
                                    "&email=" + email +
                                    "&password="+password +
                                    "&user_type="+user_type +
                                    "&firstname="+firstname +
                                    "&lastname="+lastname +
                                    "&birthdate="+birthdate +
                                    "&gender="+gender +
                                    "&country="+country +
                                    "&city="+city +
                                    "&address="+address +
                                    "&student_type="+student_type +
                                    "&student_id="+student_id +
                                    "&student_id_from_date="+student_id_from_date +
                                    "&student_id_to_date="+student_id_to_date +
                                    "&university="+university +
                                    "&department="+department +
                                    "&lat="+lat +
                                    "&lon="+lon +
                                    "&telephone="+telephone +
                                    "&personalpage="+personalpage +
                                    "&libraryname="+libraryname +
                                    "&libraryinfo="+libraryinfo ,true);
    xhr.send();
}

function LogoutButton(){
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.body.innerHTML = xhr.responseText;
            $('#ajaxContent').load("index.html");
            $("#result").html("Successful Logout");
            $("#ajaxData").html("");
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.open('POST', 'UserLogout');
    xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    xhr.send();
}

function admin_sign_in_btn(){
    document.getElementById("show_guest_books").style.display="none";
    document.getElementById("div_search_guest").style.display="none";
    document.getElementById("search_guest").style.display="none";
    $("#sign_div").html("");
    $("#register_div").html("");
    $("#guest_div").html("");
    $("#admin_div").html("");
    $("#ajaxContent").html("");
    $("#result").html("");
    $("#ajaxData").html("");
    $("#guestresult").html("");
    $("#ajaxContent").load("admin_login.html");
    $("#ajaxRest").html("");
};

function sign_in_btn(){
    document.getElementById("show_guest_books").style.display="none";
    document.getElementById("div_search_guest").style.display="none";
    document.getElementById("search_guest").style.display="none";
    $("#ajaxContent").html("");
    $("#result").html("");
    $("#ajaxData").html("");
    $("#guestresult").html("");
    $("#ajaxContent").load("sign_in.html");
    $("#ajaxRest").html("");
};

function register_btn(){
    document.getElementById("show_guest_books").style.display="none";
    document.getElementById("search_guest").style.display="none";
    document.getElementById("div_search_guest").style.display="none";
    $("#ajaxContent").html("");
    $("#result").html("");
    $("#guestresult").html("");
    $("#ajaxContent").load("registration_form.html");
    $("#ajaxRest").html("");
};

function guest_btn(){
    document.getElementById("show_guest_books").style.display="block";
    document.getElementById("search_guest").style.display="block";
    $("#ajaxContent").html("");
    $("#result").html("");
    $("#ajaxData").html("");
    $("#ajaxRest").html("");
};

function open_guest_search(){
    document.getElementById("show_guest_books").style.display="block";
    document.getElementById("div_search_guest").style.display="block";
    $("#ajaxContent").html("");
    $("#result").html("");
    $("#ajaxData").html("");
    $("#ajaxRest").html("");
};

function getBooksByGenreOnly(){
    const xhr = new XMLHttpRequest();
    xhr.withCredentials = false;
    
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
           
            const obj = JSON.parse(xhr.responseText);
            var i=1;
            var count= Object.keys(obj).length;
            document.getElementById("ajaxRest").innerHTML="<h3>"+count+" Books</h3>";
            for(id in obj){
                document.getElementById("ajaxRest").innerHTML+=createBookTableFromJSON(obj[id],i);
                i++;
            }
            
        } else if (xhr.status !== 200) {
            document.getElementById('ajaxRest')
                    .innerHTML = 'Request failed. Returned status of ' + xhr.status + "<br>"
					+JSON.stringify(xhr.responseText);
        }
    };

    var genre=document.getElementById("genre_id").value;
    var URL="http://localhost:8080/project_hy359/library/elibrary/books/"+genre;
    xhr.open("GET", URL);
    xhr.setRequestHeader("Accept", "application/json");
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send();
}

function update_btn(){
    document.getElementById("update_div").style.display="block";
    document.getElementById("search_div").style.display="none";
    document.getElementById("select_book").style.display="none";
    document.getElementById("return_book").style.display="none";
    document.getElementById("review_book").style.display="none";
    $("#result").html("");
    $("#ajaxRest").html("");
    $("#results").html("");
};

function search_btn(){
    document.getElementById("update_div").style.display="none";
    document.getElementById("search_div").style.display="block";
    document.getElementById("select_book").style.display="none";
    document.getElementById("return_book").style.display="none";
    document.getElementById("review_book").style.display="none";
    $("#result").html("");
    $("#ajaxRest").html("");
    $("#results").html("");
};

function back_btn(){
    $("#ajaxContent").load("student.html");
    $("#ajaxContent").html("");
    $("#results").html("");
};

function back_librarian_btn(){
    $("#ajaxContent").load("librarian.html");
    $("#ajaxContent").html("");
};

function return_btn(){
    document.getElementById("update_div").style.display="none";
    document.getElementById("search_div").style.display="none";
    document.getElementById("select_book").style.display="none";
    document.getElementById("return_book").style.display="block";
    document.getElementById("review_book").style.display="none";
    $("#result").html("");
    $("#ajaxRest").html("");
    $("#results").html("");
};

function select_btn(){
    document.getElementById("update_div").style.display="none";
    document.getElementById("search_div").style.display="none";
    document.getElementById("select_book").style.display="block";
    document.getElementById("return_book").style.display="none";
    document.getElementById("review_book").style.display="none";
    $("#result").html("");
    $("#ajaxRest").html("");
    $("#results").html("");
};

function review_btn(){
    document.getElementById("update_div").style.display="none";
    document.getElementById("search_div").style.display="none";
    document.getElementById("select_book").style.display="none";
    document.getElementById("return_book").style.display="none";
    document.getElementById("review_book").style.display="block";
    $("#result").html("");
    $("#ajaxRest").html("");
    $("#results").html("");
};

function getUserData(){
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#ajaxContent").html("");
            $("#result").html("");
            $("#ajaxData").html("");
           $("#ajaxContent").html(xhr.responseText);
           $("#ajaxData").append("<button onclick='getBookList()'  id='logout-btn'>See All Books</button><br>");
           $("#ajaxData").append("<button onclick='update_btn()'  id='logout-btn'>Update Data</button><br>");
           $("#ajaxData").append("<button onclick='LogoutButton()'  id='logout-btn'>Logout</button><br>");
        } else if (xhr.status !== 200) {
            $("#result").html("No data found");
        }
    };
    xhr.open('GET', 'UserData');
    xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    xhr.send();
}

function Updates(field){
    var value;
    if(field === "gender"){
        if(document.getElementById("male").checked){
            value="male";
        }else if(document.getElementById("female").checked){
            value="female";
        }else{
            value="other";
        }
    }
    else if(field === "address"){
        value=document.getElementById("address").value;
        let lat = window.sessionStorage.getItem("lat");
        let lon = window.sessionStorage.getItem("lon");
        value+=","+lat+","+lon;
    }
    else{
        value=document.getElementById(field).value;
    }
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#result").html("Successful Update.");
        } 
    };

    xhr.open("GET","UpdateUserInfo?&field="+field+"&value="+value,true);

   xhr.send();
}

function getBookList(){
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#ajaxContent").html("");
            $("#result").html("");
            $("#ajaxData").html("");
           $("#ajaxContent").html(xhr.responseText);
           $("#ajaxData").append("<button onclick='getUserData()'  id='logout-btn'>See Data</button><br>");
           $("#ajaxData").append("<button onclick='update_btn()'  id='logout-btn'>Update Data</button><br>");
           $("#ajaxData").append("<button onclick='LogoutButton()'  id='logout-btn'>Logout</button><br>");
        } else if (xhr.status !== 200) {
            $("#result").html("No data found");
        }
    };
    xhr.open('GET', 'GetBookList');
    xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    xhr.send();
}

var logged_user=null;
window.onload=function (){

    logged_user=getCookie("usercookies");
    if(logged_user!==""){
        console.log("User "+ logged_user+" is logged in.");
        var request = new XMLHttpRequest();
  
        request.onreadystatechange = function () {
            if (request.readyState === 4 && request.status === 200) {
                document.body.innerHTML = request.responseText;
            }
        };
        request.open("GET", "StayLoggedIn", true);
        request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        request.send();
        }
    else{
        console.log("No logged in user");
    }
};

function getCookie(cookieName) {
  var user_cookie = cookieName + "=";
  var decodedCookie = decodeURIComponent(document.cookie);
  var cookieArray = decodedCookie.split(';');
  
  for(var i = 0; i <cookieArray.length; i++) {
    var cur_cookie = cookieArray[i];
    
    while (cur_cookie.charAt(0) ===' ') {
      cur_cookie = cur_cookie.substring(1);
    }
    
    if (cur_cookie.indexOf(user_cookie) === 0) {
      return cur_cookie.substring(user_cookie.length, cur_cookie.length);
    }
  }
  
  return "";
}

function newhtml_btn(){
    $("#ajaxContent").html("");
    $("#result").html("");
    $("#ajaxData").html("");
    $("#ajaxContent").load("attack.html");
};

function library_btn(){
    $("#ajaxContent").html("");
    $("#result").html("");
    $("#ajaxData").html("");
    $("#ajaxContent").load("library.html");
};

var is_admin =0;
function admin(){
    is_admin=0;
    var adm_name = document.getElementById("user_username").value;
    var adm_pass = document.getElementById("user_password").value;
    
    if (adm_name === "admin" && adm_pass === "admin12*")
        is_admin = 1;
}

function call_admin(){
    $("#h2").html("");
    $("#sign_in_admin").html("");
    $("#ajaxContent").html("");
    $("#result").html("");
    $("#ajaxData").html("");
    $("#ajaxContent").load("admin.html");
};

function CheckUser(){
    admin();
    if(is_admin === 0){
        $("#ajaxContent").html("Not correct credentials for admin.");
    }
    else if(is_admin === 1){
        call_admin();
    }
}

function get_students(){
    var xhr = new XMLHttpRequest();
    $("#admin_out").html("");
    xhr.onload = function () {
        let res = xhr.responseText.split("\n");
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById("studs").style.display="block";
           $("#admin_out").html(createTableFromJSON_users(res,res.length));
        } else if (xhr.status !== 200) {
           $("#admin_out").html("omg");
        }
    };
    xhr.open('GET', 'get_all_students?');
    xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    xhr.send();
}

function get_librarians(){
    document.getElementById("deletion").style.display="none";
    document.getElementById("stat_buttons").style.display="none";
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        let res = xhr.responseText.split("\n");
        if (xhr.readyState === 4 && xhr.status === 200) {
           document.getElementById("libs").style.display="block";
           $("#admin_out_lib").html(createTableFromJSON_users(res,res.length));
        } else if (xhr.status !== 200) {
           $("#admin_out_lib").html("omg");
        }
    };
    xhr.open('GET', 'get_all_librarians?');
    xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    xhr.send();
}

function createTableFromJSON_users(data,len) {
    
    var html="<table><tr>";
    let data_cat = JSON.parse(data[0].toString());
    for (const x in data_cat) {
        var category = x;
        html += "<th>"+category+"</th>";
    }
        
    html += "</tr>";
    let data_v;
    for(let i=0; i<len-1; i++){
        html += "<tr>";
        data_v = JSON.parse(data[i].toString());
        for(const y in data_v){
            var value = data_v[y];
            html += "<td>"+value+"</td>";    
        }
        html += "</tr>";
    }
    html += "</table>";
    return html;

}

function show_del_user(){
    document.getElementById("deletion").style.display="block";
    document.getElementById("stat_buttons").style.display="none";
    document.getElementById("studs").style.display="none";
    document.getElementById("libs").style.display="none";
    $("#admin_out_lib").html("");
    $("#admin_out").html("");
}

function get_stat(){
    document.getElementById("stat_buttons").style.display="block";
    document.getElementById("deletion").style.display="none";
    document.getElementById("studs").style.display="none";
    document.getElementById("libs").style.display="none";
    $("#admin_out_lib").html("");
    $("#admin_out").html("");
}

function delete_user(){
    
    var xhr = new XMLHttpRequest();
    $("#admin_out").html("");
    $("#admin_out_lib").html("");
    xhr.onload = function () {
        
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#admin_out").html("found!");
            get_students();
            get_librarians();
            document.getElementById("deletion").style.display="none";
        } else if (xhr.status !== 200) {
           $("#admin_out").html("User not found!");
        }
    };
    var data = $('#deldel').serialize();
    xhr.open('GET', 'DeleteUser?'+data);
    xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    xhr.send();
}

function print_stat(data){
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            if(data === 'bk_lb'){
               const responseData = JSON.parse(xhr.responseText);
               $('#stat_result').html("<h3>Books per Library</h3>");
               createBarGraphics_new(responseData); 
            }else if(data === 'bk_gn'){
                const responseData = JSON.parse(xhr.responseText);
                $('#stat_result').html("<h3>Books per Genre</h3>");
                createBarGraphics(responseData);
            }else if (data === 'stud'){
               const responseData = JSON.parse(xhr.responseText);
               $('#stat_result').html("<h3>Users per Type</h3>");
               createPieGraphics(responseData); 
            }
        } else if (xhr.status !== 200) {
           $("#stat_result").html("omg");
        }
    };
    xhr.open('GET', 'GetStatistics?type='+data);
    xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    xhr.send();
 }
 
 function createBarGraphics(jsonData) {
    $('#stat_result').append("<div  id='piechart_3d'></div>");
   google.charts.load("current", {packages: ['corechart', 'bar']});
    const labels = new Array();
    const values = new Array();
     for (const x in jsonData) {
         labels.push(x);
         values.push(parseInt(jsonData[x]));        
     }
   
    google.charts.setOnLoadCallback(function () {
        drawBarChart(labels, values);
    });   
}

function drawBarChart(column1,column2) {
    var dataVis = new google.visualization.DataTable();
    dataVis.addColumn('string', 'category');
    dataVis.addColumn('number', 'value');
    for (let i = 0; i < column1.length; i++) {
        dataVis.addRow([column1[i], column2[i]]);
    }
    var options = {
        title: 'Books per Gernes',
        'width': 500, 'height': 200,
        hAxis: {
            title: 'Number per Gerne',
            minValue: 0,
            textStyle: {
                bold: true,
                fontSize: 12,
                color: '#000000'
            },
            titleTextStyle: {
                bold: true,
                fontSize: 18,
                color: '#000000'
            }
        },
        vAxis: {
            title: 'Genres',
            textStyle: {
                fontSize: 12,
                bold: false,
                color: '#000000'
            },
            titleTextStyle: {
                fontSize: 18,
                bold: true,
                color: '#000000'
            }
        },
        isStacked: 'true',
        bar: {groupWidth: '35%'}
    };
    var chart = new google.visualization.BarChart(document.getElementById('piechart_3d'));
    chart.draw(dataVis, options);
}

function createBarGraphics_new(jsonData) {
    $('#stat_result').append("<div  id='piechart_3d'></div>");
   google.charts.load("current", {packages: ['corechart', 'bar']});
    const labels = new Array();
    const values = new Array();
     for (const x in jsonData) {
         labels.push(x);
         values.push(parseInt(jsonData[x]));        
     }
   
    google.charts.setOnLoadCallback(function () {
        drawBarChart_new(labels, values);
    });   
}

function drawBarChart_new(column1,column2) {
    var dataVis = new google.visualization.DataTable();
    dataVis.addColumn('string', 'category');
    dataVis.addColumn('number', 'value');
    for (let i = 0; i < column1.length; i++) {
        dataVis.addRow([column1[i], column2[i]]);
    }
    var options = {
        title: 'Books per Library',
        'width': 500, 'height': 200,
        hAxis: {
            title: 'Number per Library',
            minValue: 0,
            textStyle: {
                bold: true,
                fontSize: 12,
                color: '#000000'
            },
            titleTextStyle: {
                bold: true,
                fontSize: 18,
                color: '#000000'
            }
        },
        vAxis: {
            title: 'Genres',
            textStyle: {
                fontSize: 12,
                bold: false,
                color: '#000000'
            },
            titleTextStyle: {
                fontSize: 18,
                bold: true,
                color: '#000000'
            }
        },
        isStacked: 'true',
        bar: {groupWidth: '35%'}
    };
    var chart = new google.visualization.BarChart(document.getElementById('piechart_3d'));
    chart.draw(dataVis, options);
}

function createPieGraphics(jsonData) {
   $('#stat_result').append("<div  id='piechart_3d'></div>");
   google.charts.load("current", {packages: ["corechart"]});
    const labels = new Array();
    const values = new Array();
     for (const x in jsonData) {
         labels.push(x);
         values.push(parseInt(jsonData[x]));        
     }

  google.charts.setOnLoadCallback(function () {
       drawPieChart(labels, values);
   });
}


function drawPieChart(column1, column2) { 
    var dataVis = new google.visualization.DataTable();
    dataVis.addColumn('string', 'category');
    dataVis.addColumn('number', 'value');
    for (let i = 0; i < column1.length; i++) {
        dataVis.addRow([column1[i], column2[i]]);
    }

    var options = {
        title: 'Users',
        'height': 200,
        is3D: true,
    };

    var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
    chart.draw(dataVis, options);
}

function postBook(){
    document.getElementById("post_book").style.display="block";
    document.getElementById("isbn_avail").style.display="none";
    document.getElementById("lib_updates").style.display="none";
    document.getElementById("buttons").style.display="none";
    $("#result").html("");
    $("#pdfContent").html("");
    $("#available_output").html("");
    $("#update_output").html("");
    $("#lib_output").html("");
    $("#request_output").html("");
}

function show_buttons(){
    document.getElementById("post_book").style.display="none";
    document.getElementById("isbn_avail").style.display="none";
    document.getElementById("lib_updates").style.display="none";
    document.getElementById("buttons").style.display="block";
    $("#result").html("");
    $("#pdfContent").html("");
    $("#available_output").html("");
    $("#update_output").html("");
    $("#lib_output").html("");
    $("#request_output").html("");
};

function makeAvailable(){
    document.getElementById("post_book").style.display="none";
    document.getElementById("lib_updates").style.display="none";
    document.getElementById("isbn_avail").style.display="block";
    document.getElementById("buttons").style.display="none";
    $("#result").html("");
    $("#pdfContent").html("");
    $("#available_output").html("");
    $("#update_output").html("");
    $("#lib_output").html("");
    $("#request_output").html("");
}

function Update_Librarian(){
    document.getElementById("post_book").style.display="none";
    document.getElementById("lib_updates").style.display="block";
    document.getElementById("isbn_avail").style.display="none";
    document.getElementById("buttons").style.display="none";
    $("#result").html("");
    $("#pdfContent").html("");
    $("#available_output").html("");
    $("#update_output").html("");
    $("#lib_output").html("");
    $("#request_output").html("");
}

function make_available_book(){
    var isbn = document.getElementById("isbn_av").value;
    var data = 'username=' + lib_name +'&isbn=' +isbn;
    
    var xhr = new XMLHttpRequest();
    $("#available_output").html("");
    
    xhr.onload = function () {
       
        if (xhr.readyState === 4 && xhr.status === 200) {
           $("#available_output").html("Book with isbn: "+isbn+" is now available!");
        } else if (xhr.status !== 200) {
           $("#available_output").html("Book already available!");
        }
    };
    
    xhr.open('GET', 'BookAvailable?'+data);
    xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    xhr.send();
}

function UpdateLibrarian(field){
    
    var value;
    if(field === "gender"){
        if(document.getElementById("male").checked){
            value="male";
        }else if(document.getElementById("female").checked){
            value="female";
        }else{
            value="other";
        }
    }
    else if(field === "address"){
        value=document.getElementById("address").value;
        let lat = window.sessionStorage.getItem("lat");
        let lon = window.sessionStorage.getItem("lon");
        value+=","+lat+","+lon;
    }
    else{
        value=document.getElementById(field).value;
    }
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#update_output").html("Successful Update.");
        } 
    };

    xhr.open("GET","UpdateLibrarian?&field="+field+"&value="+value,true);

   xhr.send();
}

function get_requests(){
    var xhr = new XMLHttpRequest();
    $("#lib_output").html("");
    
    xhr.onload = function () {
        let res = xhr.responseText.split("\n");
        if (xhr.readyState === 4 && xhr.status === 200) {
           $("#lib_output").html(createTableFromJSON_users(res,res.length));
//           $("#lib_output").append("<br><button id=conf_req style=border-radius: 8 px; margin: auto onclick=show_before_log()>Log Out</button>");
        } else if (xhr.status !== 200) {
           $("#lib_output").html("Thre are no borrow requests");
        }
    };
    
    xhr.open('GET', 'GetStatusRequests?');
    xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    xhr.send();
}

function createBookLibraryFromJSON(data,i) {
    var html = "<h4>Library "+i+"</h4><table><tr><th></th><th></th></tr>";
    for (const x in data) {
        var category=x;
        var value=data[x];
        html += "<tr><td>" + category + "</td><td>" + value + "</td></tr>";
    }
//    html += "<button class='select-btn' id='" + data['isbn'] + "' onclick='seeReviews(" + data['isbn'] + ")'>Reviews/<br>Scores</button>";
    html += "</table><br>";
    return html;

}

var libraries, global_isbn;
var lib_lat = [];
var lib_lon = [];
var destinations = "";
function getLibraryList(){
    var isbn = document.getElementById("select_isbn").value;
    var data = 'isbn=' +isbn;
    global_isbn=isbn;
    
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#ajaxContent").html("");
            $("#result").html("");
            $("#ajaxData").html("");
//            $("#ajaxContent").html(createTableFromJSON(JSON.parse(xhr.responseText)));
            const obj = JSON.parse(xhr.responseText);

            var i=1;
            var count= Object.keys(obj).length;
            $("#results").html("<h3>"+count+" Libraries</h3>");
//            document.getElementById("ajaxRest").innerHTML="<h3>"+count+" Libraries</h3>";
            for(id in obj){
//                $("#ajaxContent").html(createBookTableFromJSON(obj[id],i));
                document.getElementById("ajaxContent").innerHTML+=createBookLibraryFromJSON(obj[id],i);
                i++;
            }
//            $("#ajaxContent").html(xhr.responseText);
//            const responseData = JSON.parse(xhr.responseText);
            libraries = obj;
//            $("#ajaxContent").html(responseData);
            console.log(libraries);
            for (let library in obj) {
                for (let x in obj[library]) {

                    if (x === 'lat') {
                        lib_lat.push(obj[library][x]);
                    }

                    if (x === 'lon') {
                        lib_lon.push(obj[library][x]);
                    }
                }
            }
            for (let i in lib_lat) {
                destinations += lib_lat[i] + "," + lib_lon[i] + ";";
            }
            
           $("#ajaxContent").append("<button onclick='getDistanceSortedList()' id='sort-btn'>Sort by Distance</button><br>");
           $("#ajaxContent").append("<button onclick='getDistanceSortedbyDurationList()'  id='sort-btn'>Sort by Car Distance</button><br>");
        } else if (xhr.status !== 200) {
            $("#result").html("No data found");
        }
    };
    xhr.open('GET', 'FindLibraryDistance?'+data);
    xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    xhr.send();
//    document.getElementById("distance_btn").style.display="block";
}

function getDistanceSortedList(){
    
    document.getElementById("ajaxContent").innerHTML="";
    const data = null;
    const xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.addEventListener("readystatechange", function () {
	if (this.readyState === this.DONE) {
            console.log(this.responseText);
            const responseData = JSON.parse(xhr.responseText);
            var arr = responseData["distances"][0];
                            
            var lib_list = [];
            for (let library in libraries) {
                for (let x in libraries[library]) {
                    if (x == 'library_id') {
                        lib_list.push(libraries[library][x]);
                    }
                }
            }
            
            for (var i = 0; i < arr.length; i++) {
                for (var j = 0; j < (arr.length - i - 1); j++) {
                    if (arr[j] > arr[j + 1]) {
                        var tmp_lib = arr[j];
                        var lib = lib_list[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = tmp_lib;
                        lib_list[j] = lib_list[j + 1];
                        lib_list[j + 1] = lib;
                    }
                }
            }
            
            for (var i = 0; i < arr.length; i++) {
                var json = {};
                for (var j = 0; j < arr.length; j++) {

                    if (libraries[j]['library_id'] === lib_list[i]) {
                        for (x in libraries[j]) {
                            if (x == 'library_id' || x == 'libraryname' || x == 'address' || x == 'city' || x == 'libraryinfo' || x == 'specialty' || x == 'personalpage') {
                                json[x] = libraries[j][x];
                            }
                        }
                        break;
                    }
                }
                json["Distance (in kilometers)"] = (arr[i] / 1000).toFixed(2);
                document.getElementById("ajaxContent").innerHTML+=createTableLibrariesFromJSON(json);
            }
	}
    });
    $("#ajaxContent").append("<button onclick='getDistanceSortedbyDurationList()'  id='sort-btn'>Sort by Car Distance</button><br>");  
    $("#ajaxContent").append("<button onclick='back_btn()'  id='sort-btn'>Back</button><br>");
    xhr.open("GET", "https://trueway-matrix.p.rapidapi.com/CalculateDrivingMatrix?origins=" + u_lat + "%2C" + u_lon + "&destinations=" + destinations);
    xhr.setRequestHeader("X-RapidAPI-Key", "4d8221b0ccmsh6ff8c12af8d4515p13e360jsn122f42a699fe");
    xhr.setRequestHeader("X-RapidAPI-Host", "trueway-matrix.p.rapidapi.com");
    xhr.send(data);
}

function getDistanceSortedbyDurationList(){
    
    document.getElementById("ajaxContent").innerHTML="";
    const data = null;
    const xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.addEventListener("readystatechange", function () {
	if (this.readyState === this.DONE) {
            console.log(this.responseText);
            const responseData = JSON.parse(xhr.responseText);
            var arr = responseData["durations"][0];
            
            var lib_list = [];
            for (let library in libraries) {
                for (let x in libraries[library]) {
                    if (x == 'library_id') {
                        lib_list.push(libraries[library][x]);
                    }
                }
            }
            
            for (var i = 0; i < arr.length; i++) {
                for (var j = 0; j < (arr.length - i - 1); j++) {
                    if (arr[j] > arr[j + 1]) {
                        var tmp_lib = arr[j];
                        var lib = lib_list[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = tmp_lib;
                        lib_list[j] = lib_list[j + 1];
                        lib_list[j + 1] = lib;
                    }
                }
            }
            
            for (var i = 0; i < arr.length; i++) {
                var json = {};
                for (var j = 0; j < arr.length; j++) {

                    if (libraries[j]['library_id'] === lib_list[i]) {
                        for (x in libraries[j]) {
                            if (x == 'library_id' || x == 'libraryname' || x == 'address' || x == 'city' || x == 'libraryinfo' || x == 'specialty' || x == 'personalpage') {
                                json[x] = libraries[j][x];
                            }
                        }
                        break;
                    }
                }
                json["Distance by car(in minutes)"] = (arr[i] / 60).toFixed(2);
                document.getElementById("ajaxContent").innerHTML+=createTableLibrariesFromJSON(json);
            }
	}
    });
    $("#ajaxContent").append("<button onclick='getDistanceSortedList()'  id='sort-btn'>Sort by Distance</button><br>");
    $("#ajaxContent").append("<button onclick='back_btn()'  id='sort-btn'>Back</button><br>");
    xhr.open("GET", "https://trueway-matrix.p.rapidapi.com/CalculateDrivingMatrix?origins=" + u_lat + "%2C" + u_lon + "&destinations=" + destinations);
    xhr.setRequestHeader("X-RapidAPI-Key", "4d8221b0ccmsh6ff8c12af8d4515p13e360jsn122f42a699fe");
    xhr.setRequestHeader("X-RapidAPI-Host", "trueway-matrix.p.rapidapi.com");
    xhr.send(data);
}

function sendBorrowRequest(id){
    
    var date = new Date();
    var fromdate = new Date();
    var todate = new Date();
    
    let day = date.getDate();
    let month = date.getMonth() + 1;
    var next_month = date.getMonth()+2;
    let year = date.getFullYear();

    fromdate = year + '-' + month + '-' + day;
    todate = year + '-' + next_month + '-' + day;
    var data = 'library_id=' + id +'&isbn=' + global_isbn +'&user_id=' + user_id +'&fromdate=' + fromdate +'&todate=' + todate;
    
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
        $("#result").html("You sent a borrow request.");
//        $("#ajaxContent").append("<button id='sign' class='sign' onclick='sign_in_btn()'>Sign In</button><br>");
    } else if (xhr.status !== 200) {
       $("#result").html("Your request wasn't successful.");
       $("#not_registered").hide();
    }
    };
    xhr.open('GET', 'SelectLibrary?'+data);
    xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    xhr.send();
}

function sendReturnRequest(){    
    var isbn = document.getElementById("return_isbn").value;

    var data = 'isbn=' + isbn +'&user_id=' + user_id;
    
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
        $("#result").html("You successfully returned a book.");
    } else if (xhr.status === 406) {
       $("#result").html("Your request wasn't successful.");
       $("#not_registered").hide();
    } else if (xhr.status === 403) {
       $("#result").html("You haven't borrowed a book with this ISBN");
    }
    };
    xhr.open('GET', 'ReturnBook?'+data);
    xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    xhr.send();
}

function notification_btn(){ 
    $("#result").html("");
    $("#ajaxRest").html("");
    $("#results").html("");
    document.getElementById("update_div").style.display="none";
    document.getElementById("search_div").style.display="none";
    document.getElementById("select_book").style.display="none";
    document.getElementById("return_book").style.display="none";
    document.getElementById("review_book").style.display="none";
    var date = new Date();
    var today = new Date();
    
    let day = date.getDate();
    let month = date.getMonth() + 1;
    let year = date.getFullYear();
    let tmp_day = "";
    let tmp_month = "";
//    today = year + '-' + month + '-' + day;
    if(day < 10){
        tmp_day = "0"+day;
    }
    if(month < 10){
        tmp_month = "0"+month;
    }
    if(day<10 && month >=10){
        today = year + '-' + month + '-' + tmp_day;
    }
    else if(day>=10 && month < 10){
        today = year + '-' + tmp_month + '-' + day;
    }
    else if(day < 10 && month < 10){
        today = year + '-' + tmp_month + '-' + tmp_day;
    }
    else{
        today = year + '-' + month + '-' + day;
    }
    var data = 'user_id=' + user_id+'&today=' + today;;
    
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
        $("#results").html(xhr.responseText);
    } else if (xhr.status !== 200) {
       $("#results").html("There are no notifications for you.");
    }
    };
    xhr.open('GET', 'SendNotification?'+data);
    xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    xhr.send();
}

function pdf_create(){
    var xhr = new XMLHttpRequest();
    $("#lib_output").html("");
    
    xhr.onload = function () {
        
        if (xhr.readyState === 4 && xhr.status === 200) {
           $("#lib_output").append("PDF Created");
        } else if (xhr.status !== 200) {
           $("#lib_output").html("Pdf error on creation!");
        }
    };
    
    xhr.open('GET', 'CreatePDF?username='+ lib_name);
    xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    xhr.send();
}

function seeActiveBorrowings(){ 
    document.getElementById("post_book").style.display="none";
    document.getElementById("isbn_avail").style.display="none";
    document.getElementById("lib_updates").style.display="none";
    document.getElementById("buttons").style.display="none";
    $("#pdfContent").html("");
    $("#result").html("");
    $("#available_output").html("");
    $("#update_output").html("");
    $("#lib_output").html("");
    $("#request_output").html("");
    
    var xhr = new XMLHttpRequest();
    $("#lib_output").html("");
    
    xhr.onload = function () {
        
        if (xhr.readyState === 4 && xhr.status === 200) {
           $("#lib_output").html(xhr.responseText);
           $("#pdfContent").append("<button onclick='pdf_create()'  id='sort-btn'>Create PDF</button><br>");  
//           $("#ajaxContent").append("<button onclick='back_librarian_btn()'  id='sort-btn'>Back</button><br>");
        } else if (xhr.status !== 200) {
           $("#lib_output").html("No borrowings found!");
        }
    };
    
    xhr.open('GET', 'SeeActiveBorrowings?username='+ lib_name);
    xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    xhr.send();
}

function sendReview(){    
    var isbn = document.getElementById("review_isbn").value;
    var reviewText = document.getElementById("reviewText").value;
    var reviewScore = document.getElementById("reviewScore").value;

    var data = 'isbn=' + isbn +'&user_id=' + user_id +'&reviewText=' + reviewText +'&reviewScore=' + reviewScore;
    
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
        $("#result").html("You successfully reviewed the book.");
    } else if (xhr.status !== 200) {
       $("#result").html("Your review wasn't successful.");
       $("#not_registered").hide();
    } 
    };
    xhr.open('GET', 'LeaveReview?'+data);
    xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    xhr.send();
}

function seeReviews(isbn){    
    var data = 'isbn=' + isbn;
    
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#ajaxContent").html("");
            $("#result").html("");
            $("#ajaxData").html("");
            $("#ajaxContent").html(xhr.responseText);
            $("#ajaxContent").append("<button onclick='back_btn()'  id='sort-btn'>Back</button><br>");
        } else if (xhr.status !== 200) {
            $("#result").html("There are no reviews for this book yet.");
//            $("#ajaxContent").append("<button onclick='back_btn()'  id='sort-btn'>Back</button><br>");
        }
    };
    xhr.open('GET', 'SeeReviews?'+data);
    xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    xhr.send();
}

function show_all_books_guest(){
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
       
    let res = xhr.responseText.split("\n");
        
        if (xhr.readyState === 4 && xhr.status === 200) {
//           document.getElementById("book_guest").style.display="block";
//           $("#book_guest").html("EO!!!");
           $("#guestresult").html(createTableFromJSON_books(res,res.length));
        } else if (xhr.status !== 200) {
            $("#guestresult").html("No books to show");
        }
    };
    xhr.open('GET', 'GtBooks?');
    xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    xhr.send();
}

function createTableFromJSON_avail(data,len) {
    
    var html="<table><tr>";
    let data_cat = JSON.parse(data[0].toString());
    let cnt_1=0;
    html+="<th>genre</th>";
    for (const x in data_cat) {
        if (cnt_1!==3){
            var category = x;
            html += "<th>"+category+"</th>";
        }
        cnt_1=cnt_1+1;
    }
        
    html += "</tr>";
    
    let dat;
    const data_categ =[];
    for(let i=0; i<len-1; i++){
        dat = JSON.parse(data[i].toString());
        let cnt =0;
        for( const y in dat){
            if(cnt === 3 ){
                data_categ.push(dat[y]);
                
            }
            cnt =cnt+1;;
        }
    }
    
    data_categ.sort();
    
    const uniqueChars = [];
    data_categ.forEach((c) => {
        if (!uniqueChars.includes(c)) {
            uniqueChars.push(c);
        }
    });
    console.log(uniqueChars);
    let data_v;
    for(let ii=0; ii<uniqueChars.length; ii++){
        
        for(let i=0; i<len-1; i++){
            var cnt_res=0;
            var value_gen;
            html += "<tr>";
            data_v = JSON.parse(data[i].toString());
            let cnt=0;
            for(const y in data_v){
                if(cnt === 3){
                    var value = data_v[y];
                        if(value === uniqueChars[ii]){
                            cnt_res=1;
                            value_gen =value;
                            console.log(uniqueChars[ii]);
                        }
                }
                cnt=cnt+1;
            }
            cnt=0;
            if(cnt_res===1){
                html+="<td>"+value_gen+"</td>";
                for(const y in data_v){
                    if(cnt !== 3){
                        var value = data_v[y];
                        html += "<td>"+value+"</td>";
                    }
                    cnt=cnt+1;
                }
            }
            html += "</tr>";
        }
    }
    html += "</table>";
    return html;
}


function show_all_books(){
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
       
        let res = xhr.responseText.split("\n");
        
        if (xhr.readyState === 4 && xhr.status === 200) {
           $("#guestresult").html(createTableFromJSON_avail(res,res.length));
        } else if (xhr.status !== 200) {
           $("#guestresult").html("No books to show");
        }
    };
    xhr.open('GET', 'ShowAvailableBooks?');
    xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    xhr.send();
}

function createTableFromJSON_books(data,len) {
    
    var html="<table><tr>";
    let data_cat = JSON.parse(data[0].toString());
    for (const x in data_cat) {
        var category = x;
        console.log(category);
        html += "<th>"+category+"</th>";
    }
        
    html += "</tr>";
    let data_v;
    for(let i=0; i<len-1; i++){
        html += "<tr>";
        data_v = JSON.parse(data[i].toString());
        for(const y in data_v){
            var value = data_v[y];
            html += "<td>"+value+"</td>";    
        }
        html += "</tr>";
    }
    html += "</table>";
    return html;

}

function getRequested(){
    var xhr = new XMLHttpRequest();
    $("#lib_output").html("");
    $("#request_output").html("");
    
    xhr.onload = function () {   
        let res = xhr.responseText.split("\n");
        
        if (xhr.readyState === 4 && xhr.status === 200) {
           $("#request_output").html(createTableFromJSON_new(res,res.length));
        } else if (xhr.status !== 200) {
            $("#request_output").html("No requests to show");
        }
    };
    console.log(lib_name);
    xhr.open('GET', 'GetRequested?username='+ lib_name);
    xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    xhr.send();
}

function getReturned(){
    var xhr = new XMLHttpRequest();
    $("#lib_output").html("");
    $("#request_output").html("");
    
    xhr.onload = function () {   
        let res = xhr.responseText.split("\n");
        
        if (xhr.readyState === 4 && xhr.status === 200) {
           $("#request_output").html(createReturnTableFromJSON_new(res,res.length));
        } else if (xhr.status !== 200) {
            $("#request_output").html("No requests to show");
        }
    };
    
    xhr.open('GET', 'GetReturned?username='+ lib_name);
    xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    xhr.send();
}

function createReviewTableFromJSON(data,i) {
    var html = "<h4>Review "+i+"</h4><table><tr><th></th><th></th></tr>";
    for (const x in data) {
        var category=x;
        var value=data[x];
        html += "<tr><td>" + category + "</td><td>" + value + "</td></tr>";
    }
    html += "<button class='select-btn' id='" + data['isbn'] + "' onclick='seeReviews(" + data['isbn'] + ")'>Reviews/<br>Scores</button>";
    html += "</table><br>";
    return html;

}

function AcceptRequested(id){
        var xhr = new XMLHttpRequest();
    $("#lib_output").html("");
    $("#request_output").html("");
//    console.log(id);
    xhr.onload = function () {   
//        let res = xhr.responseText.split("\n");
        
        if (xhr.readyState === 4 && xhr.status === 200) {
           $("#request_output").html("Book status was changed to borrowed");
        } else if (xhr.status !== 200) {
            $("#request_output").html("Book status wasn't successfully changed");
        }
    };
    
    xhr.open('GET', 'AcceptRequested?borrowing_id='+ id);
    xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    xhr.send();
}

function AcceptReturned(id){
            var xhr = new XMLHttpRequest();
    $("#lib_output").html("");
    $("#request_output").html("");
//    console.log(id);
    xhr.onload = function () {   
//        let res = xhr.responseText.split("\n");
        
        if (xhr.readyState === 4 && xhr.status === 200) {
           $("#request_output").html("Book status was changed to successEnd");
        } else if (xhr.status !== 200) {
            $("#request_output").html("Book status wasn't successfully changed");
        }
    };
    
    xhr.open('GET', 'AcceptReturned?borrowing_id='+ id);
    xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    xhr.send();
}

function add_bookinlibraries(libname,isbn){
    var data = 'username=' + libname +'&isbn=' +isbn;
    
    var xhr = new XMLHttpRequest();
    
    $("#log_out_lib").html("");
    
    xhr.onload = function () {
       
        if (xhr.readyState === 4 && xhr.status === 200) {
           $("#log_out_lib").html("Book with isbn: "+isbn+" is now available!");
        } else if (xhr.status !== 200) {
           $("#log_out_lib").html("Error on posting!");
        }
    };
    console.log(data);
    xhr.open('POST', 'NewAvailable?'+data);
    xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    xhr.send();
}

function addBook() {
    let restForm = document.getElementById('restForm');
    let formData = new FormData(restForm);
    const data = {};
    formData.forEach((value, key) => (data[key] = value));
    var jsonData=JSON.stringify(data);
    var isbnn = document.getElementById('isbn').value;

    var xhr = new XMLHttpRequest();
    xhr.withCredentials = false;
    
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
           document.getElementById('ajaxRest').innerHTML=JSON.stringify(xhr.responseText);
           add_bookinlibraries(lib_name,isbnn);
            
        } else if (xhr.status !== 200) {
            document.getElementById('ajaxRest')
                    .innerHTML = 'Request failed. Returned status of ' + xhr.status + "<br>"+
					JSON.stringify(xhr.responseText);
 
        }
    };
    xhr.open('POST', 'http://localhost:8080/project_hy359/library/elibrary/book');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(jsonData);
}