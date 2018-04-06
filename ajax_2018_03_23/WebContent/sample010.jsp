<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="java.util.*, com.test.*"%>
<%
	//JSP code
	request.setCharacterEncoding("UTF-8");
	String contextRoot = request.getContextPath();
	StringBuilder sb = new StringBuilder();

	//데이터베이스에 존재하는 회원명단을 읽어와서 동적으로 <option> 태그를 생성한다.
	//->결과를 동적 출력
	MemberDAO dao = new MemberDAO();
	List<Member> nameList = dao.nameList();

	for (Member m : nameList) {
		sb.append(
				String.format("<option value=\"%s\">%s/%s</option>", m.getMid_(), m.getName_(), m.getPhone()));
	}
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

<!-- Google Map API -->
<script src="https://maps.googleapis.com/maps/api/js"></script>

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
		<h1>Ajax Test</h1>

		<div class="form-group">
			<label for="sel1">Select list:</label> <select class="form-control"
				id="mid_" name="mid_" onchange="showUser(this.value)">
				<option value="">Select a person:</option>
				<!-- 				
				<option value="번호">이름/전화번호</option>
				<option>2</option>
				<option>3</option>
				<option>4</option>
				 -->
				<%=sb.toString()%>
			</select>
		</div>

		<div id="txtHint">
			<b>Person info will be listed here...</b>
		</div>


	</div>
	<script>
  	function showUser(str) {
  	 if (str.length == 0) {
 	   document.getElementById("txtHint").innerHTML = "";
	    return;
	   } else {
	    var xmlhttp = new XMLHttpRequest();
	    xmlhttp.onreadystatechange = function() {
	     if (this.readyState == 4 && this.status == 200) {
   	   	//서버로부터 Ajax 응답받은 JSON 문서에 대한 파싱 및 동적 테이블 생성
   	   	var json = this.responseText;
	        var obj = JSON.parse(json);
	        var txt = "";

      		for (m in obj.members) {
		       txt += "<table class=\"table table-striped\"><tbody>";
		       txt += "<tr><td colspan=\"2\" style=\" text-align:center\"><img src=\"<%=contextRoot%>/resources/img/"+obj.members[m].picture+"\" style=\"width:20%\">"+"</td></tr>";
		       txt += "<tr><td>mid_</td><td>"+obj.members[m].mid_+"</td></tr>";
		       txt += "<tr><td>name_</td><td>"+obj.members[m].name_+"</td></tr>";
		       txt += "<tr><td>phone</td><td>"+obj.members[m].phone+"</td></tr>";
		       txt += "<tr><td>email</td><td>"+obj.members[m].email+"</td></tr>";
		       txt += "<tr><td>regDate</td><td>"+obj.members[m].regDate+"</td></tr>";
		       txt += "<tr><td>deptName</td><td>"+obj.members[m].deptName+"</td></tr>";
		       txt += "</tbody></table>";
		      }
		 document.getElementById("txtHint").innerHTML = txt;
	     }
	    };
	    xmlhttp.open("GET", "getUserJSON.jsp?mid_=" + str, true);
	    xmlhttp.send();
   	    }
  	}
	 </script>
</body>
</html>
