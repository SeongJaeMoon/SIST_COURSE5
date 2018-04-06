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
		<h1>Google Maps - InfoWindow</h1>

		<!-- 맵 출력 -->
		<div id="googleMap" style="width: 100%; height: 600px;"></div>

		<script>
			function myMap() {

				//위도, 경도 
				var myCenter = new google.maps.LatLng(37.499362, 127.033202);

				//맵 정보
				var mapProp = {
					center : myCenter,
					zoom : 17,
				};
				//맵 요청
				var map = new google.maps.Map(document
						.getElementById("googleMap"), mapProp);

				//마커 표시
				var marker = new google.maps.Marker({
					position : myCenter
				});
				marker.setMap(map);

				//InfoWindow
				var infowindow = new google.maps.InfoWindow({
					content : "<div style=\"text-align:center;\"><strong>한독약품빌딩</strong><br>서울특별시 강남구 역삼1동 735<br><img src=\"handok_small.png\"></div>"
				});
				infowindow.open(map, marker);

			}
		</script>

		<script src="https://maps.googleapis.com/maps/api/js?callback=myMap"></script>

	</div>

</body>
</html>