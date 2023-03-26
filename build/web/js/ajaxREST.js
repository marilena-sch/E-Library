/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function createBookTableFromJSON(data,i) {
    var html = "<h4>Book "+i+"</h4><table><tr><th></th><th></th></tr>";
    for (const x in data) {
        var category=x;
        var value=data[x];
        html += "<tr><td>" + category + "</td><td>" + value + "</td></tr>";
    }
//    html += "<button class='select-btn' id='" + data['isbn'] + "' onclick='seeReviews(" + data['isbn'] + ")'>Reviews/<br>Scores</button>";
    html += "</table><br>";
    return html;

}

function createSearchTableFromJSON(data,i) {
    var html = "<h4>Book "+i+"</h4><table><tr><th></th><th></th></tr>";
    for (const x in data) {
        var category=x;
        var value=data[x];
        html += "<tr><td>" + category + "</td><td>" + value + "</td></tr>";
    }
    html += "<button class='select-btn' id='" + data['isbn'] + "' onclick='seeReviews(" + data['isbn'] + ")'>Reviews/<br>Scores</button>";
    html += "</table><br>";
    return html;

}

function updateBookPages() {
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = false;
    
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
           document.getElementById('ajaxRest').innerHTML=JSON.stringify(xhr.responseText);
            
        } else if (xhr.status !== 200) {
            document.getElementById('ajaxRest')
                    .innerHTML = 'Request failed. Returned status of ' + xhr.status + "<br>"+
					JSON.stringify(xhr.responseText);
 
        }
    };
    
    var isbn1=document.getElementById("isbn1").value;
    var pages1=document.getElementById("pages1").value;
    xhr.open('PUT', 'http://localhost:8080/project_hy359/library/elibrary/bookpages/'+isbn1+"/"+pages1);
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send();
}

function deleteBookByISBN() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
           document.getElementById('ajaxRest').innerHTML=JSON.stringify(xhr.responseText);
            
        } else if (xhr.status !== 200) {
            document.getElementById('ajaxRest')
                    .innerHTML = 'Request failed. Returned status of ' + xhr.status + "<br>"+
					JSON.stringify(xhr.responseText);
 
        }
    };
    
    var isbn2=document.getElementById("isbn2").value;
    xhr.open('DELETE', 'http://localhost:8080/project_hy359/library/elibrary/bookdeletion/'+isbn2);
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send();
}

function search_book_api(){

        var xhr = new XMLHttpRequest();
        
        xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
           
            const obj = JSON.parse(xhr.responseText);
            var i=1;
            var count= Object.keys(obj).length;
            document.getElementById("ajaxRest").innerHTML="<h3>"+count+" Books</h3>";
            for(id in obj){
                document.getElementById("ajaxRest").innerHTML+=createSearchTableFromJSON(obj[id],i);
                i++;
            }
            
        } else if (xhr.status !== 200) {
            document.getElementById('ajaxRest')
                    .innerHTML = 'Request failed. Returned status of ' + xhr.status + "<br>"
					+JSON.stringify(xhr.responseText);
        }
    };

        var genre = document.getElementById("genre_").value;
        var pyearf=document.getElementById("publicationyearfrom").value;
        var pyeart = document.getElementById("publicationyearto").value;

        var pageto = document.getElementById("pageto").value;
        var pagefrom = document.getElementById("pagefrom").value;
        var title = document.getElementById("title_").value;
        var author = document.getElementById("author_").value;

        var URL="http://localhost:8080/project_hy359/library/elibrary/books/"+genre;
        var goto_nxt =0;

        if(pyearf!=="" && pyeart!==""){
            goto_nxt =1;
            URL+="?fromYear="+pyearf+"&toYear="+pyeart;
        }else{
            if(pyearf!==""){
                goto_nxt =1;
                URL+="?fromYear="+pyearf;
            }
            if(pyeart!==""){
                goto_nxt =1;
                URL+="?toYear="+pyeart;
            }
        }
        if (goto_nxt === 0){
            if(pagefrom!=="" && pageto!==""){
                goto_nxt =1;
                URL+="?fromPageNumber="+pagefrom+"&toPageNumber="+pageto;
            }else{
                if(pagefrom!==""){
                    goto_nxt =1;
                    URL+="?fromPageNumber="+pagefrom;
                }
                if(pageto!==""){
                    goto_nxt =1;
                    URL+="?toPageNumber="+pageto;
                }
            }
        }else{
            if(pagefrom!=="" && pageto!==""){
                goto_nxt =1;
                URL+="&fromPageNumber="+pagefrom+"&toPageNumber="+pageto;
            }else{             
                if(pagefrom!==""){
                    goto_nxt =1;
                    URL+="&fromPageNumber="+pagefrom;
                }
                if(pageto!==""){
                    goto_nxt =1;
                    URL+="&toPageNumber="+pageto;
                }
            }
        }
        
        if(title!==""){
            if (goto_nxt === 1 )
                URL+="&title="+title;
            else{
                URL+="?title="+title;
                goto_nxt =1;
            }
        }
        if(author!==""){
            if (goto_nxt === 1 )
                URL+="&authors="+author;
            else
                URL+="?authors="+author;
        }

        xhr.open('GET', URL);
        xhr.setRequestHeader("Accept", "application/json");
        xhr.setRequestHeader("Content-type", "application/json");
        xhr.send();
}