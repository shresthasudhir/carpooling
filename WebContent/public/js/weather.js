"use strict"
$(document).ready(function(){
var bboxd;
var map;
var geoJSON;
var request;
var gettingData = false;
var openWeatherMapKey = "7b7d426c1101e51bc6efca49d79e376b";
var myLatLng = { lat: 41.0086111, lng: -91.9625 };
var marker = null;
var address="Fairfield,IA";
var directionsService = new google.maps.DirectionsService;
var directionsDisplay = new google.maps.DirectionsRenderer;
function initialize() {
    var mapOptions = {
        zoom: 10,
        center: myLatLng,
        mapTypeId: 'roadmap'
    };
    if($('#usercity').val()!="" && $("#userstate").val()!=""){
    	address= $('#usercity').val()+", "+$('#userstate').val();;
    }
    else if($('#userzip').val()!=""){
    	address=$('#userzip').val();
    }
    
    var geocoder = new google.maps.Geocoder();
    geocoder.geocode({ 'address': address + ', us' }, function (results, status) {
        if (status == google.maps.GeocoderStatus.OK) {
            marker.setPosition(results[0].geometry.location);
            map.setCenter(results[0].geometry.location);                           
        } else {
            console.log("Something got wrong " + status);
        }
    });
    map = new google.maps.Map(document.getElementById('map-canvas'),
        mapOptions);
  //set map for showing trips
	directionsDisplay.setMap(map);
    //add marker    
    marker = new google.maps.Marker({
        position: myLatLng,
        map: map,
        title: address
    });

    // Add interaction listeners to make weather requests
    google.maps.event.addListener(map, 'idle', checkIfDataRequested);
    // Sets up and populates the info window with details
    map.data.addListener('click', function (event) {
        infowindow.setContent(
            "<img src=" + event.feature.getProperty("icon") + ">"
            + "<br /><strong>" + event.feature.getProperty("city") + "</strong>"
            + "<br />" + event.feature.getProperty("temperature") + "&deg;C"
            + "<br />" + event.feature.getProperty("weather")
            // + "<br />5 days Data" + getWeatherJsonData(event.feature.getProperty("city"))
        );
        infowindow.setOptions({
            position: {
                lat: event.latLng.lat(),
                lng: event.latLng.lng()
            },
            pixelOffset: {
                width: 0,
                height: -15
            }
        });
        infowindow.open(map);
    });
    getFiveDaysForecast();
}
var checkIfDataRequested = function () {
    // Stop extra requests being sent
    while (gettingData === true) {
        request.abort();
        gettingData = false;
    }
    getCoords();
};
// Get the coordinates from the Map bounds
var getCoords = function () {
    var bounds = map.getBounds();
    var NE = bounds.getNorthEast();
    var SW = bounds.getSouthWest();
    getWeather(NE.lat(), NE.lng(), SW.lat(), SW.lng());
};
// Make the weather request
var getWeather = function (northLat, eastLng, southLat, westLng) {
    gettingData = true;
    bboxd="N:"+northLat+"E:"+eastLng+"S:"+southLat+"W:"+westLng;    
    var requestString = "http://api.openweathermap.org/data/2.5/box/city?bbox="
        + westLng + "," + northLat + "," //left top
        + eastLng + "," + southLat + "," //right bottom
        + map.getZoom()
        + "&cluster=yes&format=json"
        + "&APPID=" + openWeatherMapKey;
    request = new XMLHttpRequest();
    request.onload = proccessResults;
    request.open("get", requestString, true);
    request.send();
};
// Take the JSON results and proccess them
var proccessResults = function () {
    var results = JSON.parse(this.responseText);
    if (results.list.length > 0) {
        resetData();
        for (var i = 0; i < results.list.length; i++) {
            geoJSON.features.push(jsonToGeoJson(results.list[i]));
        }
        drawIcons(geoJSON);
    }
};
var infowindow = new google.maps.InfoWindow();
// For each result that comes back, convert the data to geoJSON
var jsonToGeoJson = function (weatherItem) {
    var feature = {
        type: "Feature",
        properties: {
            city: weatherItem.name,
            weather: weatherItem.weather[0].main,
            temperature: weatherItem.main.temp,
            min: weatherItem.main.temp_min,
            max: weatherItem.main.temp_max,
            humidity: weatherItem.main.humidity,
            pressure: weatherItem.main.pressure,
            windSpeed: weatherItem.wind.speed,
            windDegrees: weatherItem.wind.deg,
            windGust: weatherItem.wind.gust,
            icon: "http://openweathermap.org/img/w/"
            + weatherItem.weather[0].icon + ".png",
            coordinates: [weatherItem.coord.lon, weatherItem.coord.lat]
        },
        geometry: {
            type: "Point",
            coordinates: [weatherItem.coord.lon, weatherItem.coord.lat]
        }
    };
    // Set the custom marker icon
    map.data.setStyle(function (feature) {
        return {
            icon: {
                url: feature.getProperty('icon'),
                anchor: new google.maps.Point(25, 25)
            }
        };
    });
    // returns object
    return feature;
};
// Add the markers to the map
var drawIcons = function (weather) {
    map.data.addGeoJson(geoJSON);
    // Set the flag to finished    
    gettingData = false;
};
// Clear data layer and geoJSON
var resetData = function () {
    geoJSON = {
        type: "FeatureCollection",
        features: []
    };
    map.data.forEach(function (feature) {
        map.data.remove(feature);
    });
};
google.maps.event.addDomListener(window, 'load', initialize);
//Search Option
/*$("#submit").click(getFiveDaysForecast);*/
$("#city").focusout(getFiveDaysForecast);
$("#city").keypress(function(e){
	if(e.which==13){
		getFiveDaysForecast();
	}
});
function getFiveDaysForecast(){
    $("#weather").empty();
    var city = "";
    city = $('#city').val();
    var url = "";
    var currentLon = "";
    var currentLat = "";
    var api = "http://api.openweathermap.org/data/2.5/forecast?q=";
    var countryCode = ",us";
    var apiKey = "&appid=7b7d426c1101e51bc6efca49d79e376b";
    var units = "&units=metric&mode=json";
    var options = {
        enableHighAccuracy: true,
        timeout: 5000,
        maximumAge: 0
    };
    if (city != "") {
        var geocoder = new google.maps.Geocoder();
        geocoder.geocode({ 'address': city + ', us' }, function (results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
                marker.setPosition(results[0].geometry.location);
                map.setCenter(results[0].geometry.location); 
                marker.title = city;

            } else {
                console.log("Something got wrong " + status);
            }
        });
        url += api + city + countryCode + units + apiKey;
        $.get(url).done(function (data) {
            $("#weather").append(getContentString(data));
        })
            .fail(function (xhr, status, exception) {
                console.log(exception);
            })
    }
    else if(address!=""){
    	if($('#usercity').val()!="" && $("#userstate").val()!=""){
        	address= $('#usercity').val()+", "+$('#userstate').val();;
        }
        else if($('#userzip').val()!=""){
        	address=$('#userzip').val();
        }
    	var geocoder = new google.maps.Geocoder();
        geocoder.geocode({ 'address': address + ', us' }, function (results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
                marker.setPosition(results[0].geometry.location);
                map.setCenter(results[0].geometry.location); 
                marker.title = address;

            } else {
                console.log("Something got wrong " + status);
            }
        });
        url += api + address + countryCode + units + apiKey;
        $.get(url).done(function (data) {
            $("#weather").append(getContentString(data));
        })
            .fail(function (xhr, status, exception) {
                console.log(exception);
            })
    }
    else {
        function success(position) {
            var crd = position.coords;
            currentLon = crd.longitude;
            currentLat = crd.latitude;
            url += "http://api.openweathermap.org/data/2.5/forecast?lat=" + currentLat + "&lon=" + currentLon
                + units + "&appid=3e92aea39f6f9dd75d65ec5d77b6a5b4";
            $.get(url).done(function (data) {
                $("#weather").append(getContentString(data));
            })
                .fail(function (xhr, status, exception) {
                    console.log(exception);
                })
        }
        function fail(msg) {
            console.log(msg.code + msg.message); // Log the error
        }
        navigator.geolocation.getCurrentPosition(success, fail, options);
    }
}
//get jsonData
function getWeatherJsonData(place) {
    var url = "";
    var currentLon = "";
    var currentLat = "";
    var api = "http://api.openweathermap.org/data/2.5/forecast?q=";
    var countryCode = ",us";
    var apiKey = "&appid=3e92aea39f6f9dd75d65ec5d77b6a5b4";
    var units = "&units=metric&mode=json";
    url += api + place + countryCode + units + apiKey;
    var fivedaysData = null;    
    $.get(url)
        .done(function (data) {          
            // fivedaysData = getContentString(data);
        }).fail(function (xhr, status, exception) {
            console.log(exception);
        })
}
//returns string for presenting data to html
function getContentString(data) {
    //Displaying day 
    var days = new Array(7);
    days[0] = "Sunday";
    days[1] = "Monday";
    days[2] = "Tuesday";
    days[3] = "Wednesday";
    days[4] = "Thursday";
    days[5] = "Friday";
    days[6] = "Saturday";
    var weatherdata = "";
    weatherdata += "<table class='table table-sm'>"
        + "<tr><td colspan='5'>"
        + "City :" + data.city.name + ", " + data.city.country
        + "&nbspToday(" + days[new Date(data.list[0].dt_txt).getDay()] + ")&nbsp<img   src='http://openweathermap.org/img/w/" + data.list[0].weather[0].icon + ".png'/>"
        + data.list[0].main.temp + "&#8451&nbsp"
        + data.list[0].weather[0].description + "</td></tr>"
        + "<tr><td>"
        + days[new Date(data.list[3].dt_txt).getDay()] + "</td><td>"
        + days[new Date(data.list[11].dt_txt).getDay()] + "</td><td>"
        + days[new Date(data.list[19].dt_txt).getDay()] + "</td><td>"
        + days[new Date(data.list[27].dt_txt).getDay()] + "</td><td>"
        + days[new Date(data.list[35].dt_txt).getDay()] + "</td><td></tr>"
        + "<tr><td>"
        + "<img src='http://openweathermap.org/img/w/" + data.list[3].weather[0].icon + ".png'/>"
        + data.list[3].main.temp + "&#8451&nbsp"
        + data.list[3].weather[0].description + "</td><td>"
        + "<img src='http://openweathermap.org/img/w/" + data.list[11].weather[0].icon + ".png'/>"
        + data.list[11].main.temp + "&#8451&nbsp"
        + data.list[11].weather[0].description + "</td><td>"
        + "<img src='http://openweathermap.org/img/w/" + data.list[19].weather[0].icon + ".png'/>"
        + data.list[19].main.temp + "&#8451&nbsp"
        + data.list[19].weather[0].description + "</td><td>"
        + "<img src='http://openweathermap.org/img/w/" + data.list[27].weather[0].icon + ".png'/>"
        + data.list[27].main.temp + "&#8451&nbsp"
        + data.list[27].weather[0].description + "</td><td>"
        + "<img src='http://openweathermap.org/img/w/" + data.list[35].weather[0].icon + ".png'/>"
        + data.list[35].main.temp + "&#8451&nbsp"
        + data.list[35].weather[0].description
        + "</td></tr></table>";
    return weatherdata;
}
//search box
//Create the search box and link it to the UI element.
var input = document.getElementById('city');
var searchBox = new google.maps.places.SearchBox(input);
var input1 = document.getElementById('origin');
var searchBox = new google.maps.places.SearchBox(input1);
//Information for route

$("#nearbytrips").click(function(){
	
	var origin = $("#origin").val();
	var destination = $("#city").val();
	
	if(origin == "" || destination == ""){
		alert("Source or Destination Not Provided");
		e.preventDefault();
	}else{
		
		var geocoder = new google.maps.Geocoder();
	    geocoder.geocode({ 'address': destination + ', us' }, function (results, status) {

	        	var destinationLatitude = results[0].geometry.location.lat();
	        	var destinationLongitude = results[0].geometry.location.lng();
	        	
	        	$.post("GetUserNearByTrips",{destinationLatitude:destinationLatitude,destinationLongitude:destinationLongitude})
	        	.done(function(result){	
	  
	          		//var items = ["Chicago", "Washington,IA", "Burlington,IA"];
	        	    var waypoints = [];
	        	    for (var i = 0; i < result.JSONDATA.length; i++) {
	        	        var address = result.JSONDATA[i].destination;
	        	        if (address !== "") {
	        	            waypoints.push({
	        	                location: address,
	        	                stopover: true
	        	            });
	        	        }
	        	    }  
	        	    console.log(waypoints[0].toString());
	        	    
	        	    directionsService.route({
	        	        origin: $("#origin").val(),
	        	        destination: $("#city").val(),
	        	        waypoints:waypoints,
	        	        optimizeWaypoints: true,
	        	        travelMode: 'DRIVING'
	        	    }, function (response, status) {
	        	        if (status === 'OK') {
	        	            directionsDisplay.setDirections(response);

	        	        }
	        	        else {
	        	            window.alert('Directions request failed due to ' + status);
	        	        }
	        	    });
	    			
	    			}).fail(function(xhr,status,exception){
	    				alert(exception);
	    				console.log(xhr);
	    		});
	        	
	    		                
	        
	    });
		
		
		
		
	}//endofelse
	

});


$("#alltrips").click(function(){
		alert("Hey, How do u do?");

    	$.post("GetAllTrips",{})
        	.done(function(result){
  
    	    var waypoints = [];
    	    for (var i = 0; i < result.JSONDATA.length; i++) {
    	        var address = result.JSONDATA[i].destination;
    	        if (address !== "") {
    	            waypoints.push({
    	                location: address,
    	                stopover: true
    	            });
    	        }
    	    }  
    	    console.log(waypoints[0].toString());
    	    
    	    directionsService.route({
    	        origin: $("#origin").val(),
    	        destination: $("#city").val(),
    	        waypoints:waypoints,
    	        optimizeWaypoints: true,
    	        travelMode: 'DRIVING'
    	    }, function (response, status) {
    	        if (status === 'OK') {
    	            directionsDisplay.setDirections(response);
    	        }
    	        else {
    	            window.alert('Directions request failed due to ' + status);
    	        }
    	    });
			
			}).fail(function(xhr,status,exception){
				alert(exception);
				console.log(xhr);
		});
	        	
	    		                
	        
	    
		
});

});//end of document ready






