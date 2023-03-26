'use strict';

var hasMatch =false;
var nlat=0;
var nlon=0;
var display_name;
var map;
var flag=0;

function checkAddress(){
	var country=document.getElementById('country').value;
	var city= document.getElementById('city').value; 
	var address= document.getElementById('address').value;
	var final_address=address+" " +city+" "+country; 
	var text = document.getElementById("valid");
	var crete = document.getElementById("crete");

	const data = null;

	const xhr = new XMLHttpRequest();
	xhr.withCredentials = false;

	xhr.addEventListener("readystatechange", function () {
		if (this.readyState === this.DONE) {
			console.log(this.responseText);
		}
	});

	xhr.open("GET", "https://forward-reverse-geocoding.p.rapidapi.com/v1/search?q="+final_address+"&accept-language=en&polygon_threshold=0.0");
	xhr.setRequestHeader("X-RapidAPI-Key", "4d8221b0ccmsh6ff8c12af8d4515p13e360jsn122f42a699fe");
	xhr.setRequestHeader("X-RapidAPI-Host", "forward-reverse-geocoding.p.rapidapi.com");

	xhr.addEventListener("readystatechange", function () {
		if (this.readyState === this.DONE) {
			if( this.responseText === '{}'){
				console.log("No coordinations found");
				nlat=null;
				nlon=null;
			}
			else{
				var tmp = JSON.parse(xhr.responseText);
				nlat=tmp[0].lat;
				nlon=tmp[0].lon;
				display_name=tmp[0].display_name;
                                
                                window.sessionStorage.setItem("lon",nlon);
		                window.sessionStorage.setItem("lat",nlat);

				if(display_name.includes('Crete')){
					hasMatch=true;
				}
				else{
					hasMatch=false;
				}
				if(hasMatch === true){
					crete.style.color='green';
					crete.innerHTML = "Service available.";
				}
				else{
					crete.style.color='red';
					crete.innerHTML = "Service isn't available in regions outside of Crete.";
				}
			}
		}
	});

	xhr.send(data);

	xhr.addEventListener("readystatechange", function () {
		if (xhr.readyState === xhr.DONE) {	
			if (JSON.parse(xhr.responseText).length > 0) {
				text.innerHTML = "This address is valid.";
				document.getElementById('country').style.color = 'green';
				document.getElementById('address').style.color = 'green';
				document.getElementById('city').style.color = 'green';
				text.style.color = 'green';
			}
			else {

				text.innerHTML = "This address is invalid.";
				document.getElementById('country').style.color = 'red';
				document.getElementById('address').style.color = 'red';
				document.getElementById('city').style.color = 'red';
				text.style.color = 'red';	
			}
		}	
	});	
}

function InitMap(latitude, longitude) {
	map = new OpenLayers.Map("showMap");
	map.addLayer(new OpenLayers.Layer.OSM());
	document.getElementById('showMap').style.height = "400px";
	document.getElementById('showMap').style.width = "700px";
	document.getElementById('showMap').style.position="relative";
	document.getElementById('showMap').style.left="-200px";
	var fromProjection = new OpenLayers.Projection("EPSG:4326");  
	var toProjection   = new OpenLayers.Projection("EPSG:900913");
	flag = 1;
	
	var position = new OpenLayers.LonLat(longitude, latitude).transform( fromProjection, toProjection);
 
	 var markers = new OpenLayers.Layer.Markers( "Markers" );
	 map.addLayer(markers);
	 markers.addMarker(new OpenLayers.Marker(position));
		 
	 const zoom = 10;
	 map.setCenter(position, zoom);
 
	//  return position;
 }

/*Erwtima 2b*/
function SendReq() {
	if(flag === 1){
		document.getElementById('showMap').innerHTML = "";
		flag = 0;
	}
	if (nlat === null && nlon === null){
		document.getElementById('showMap').innerHTML = "";
		flag = 0;
		console.log("Coordinations not found");
	}
	else{
		InitMap(nlat,nlon);
	}
}