<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//JSP code
	request.setCharacterEncoding("UTF-8");
	StringBuilder sb = new StringBuilder();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1">

<title>SIST_쌍용교육센터</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<style>
div#input:hover, div#output:hover {
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.19);
}
</style>

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<script>
	$(document).ready(function() {

		// jQuery methods go here...

	});
</script>
</head>
<body>

	<div class="container">
		<h1>Multiple Maps</h1>

		<div id="googleMap1" style="width: 400px; height: 300px;"></div>
		<br>
		<div id="googleMap2" style="width: 400px; height: 300px;"></div>
		<br>
		<div id="googleMap3" style="width: 400px; height: 300px;"></div>
		<br>
		<div id="googleMap4" style="width: 400px; height: 300px;"></div>

		<script>
			function myMap() {
				var mapOptions1 = {
					center : new google.maps.LatLng(51.508742, -0.120850),
					zoom : 9,
					mapTypeId : google.maps.MapTypeId.ROADMAP
				};
				var mapOptions2 = {
					center : new google.maps.LatLng(51.508742, -0.120850),
					zoom : 9,
					mapTypeId : google.maps.MapTypeId.SATELLITE
				};
				var mapOptions3 = {
					center : new google.maps.LatLng(51.508742, -0.120850),
					zoom : 9,
					mapTypeId : google.maps.MapTypeId.HYBRID
				};
				var mapOptions4 = {
					center : new google.maps.LatLng(51.508742, -0.120850),
					zoom : 9,
					mapTypeId : google.maps.MapTypeId.TERRAIN
				};
				var map1 = new google.maps.Map(document
						.getElementById("googleMap1"), mapOptions1);
				var map2 = new google.maps.Map(document
						.getElementById("googleMap2"), mapOptions2);
				var map3 = new google.maps.Map(document
						.getElementById("googleMap3"), mapOptions3);
				var map4 = new google.maps.Map(document
						.getElementById("googleMap4"), mapOptions4);
			}
		</script>

		<script src="https://maps.googleapis.com/maps/api/js?callback=myMap"></script>
	</div>

</body>
</html>