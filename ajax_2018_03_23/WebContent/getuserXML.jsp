<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%-- 주의) Ajax 응답 페이지 구성시 불필요한 반환값을 작성하지 않는다. --%>
<%@ page import="java.util.*, com.test.*"%>
<%
	//JSP code
	request.setCharacterEncoding("UTF-8");
	String contextRoot = request.getContextPath();
	StringBuilder sb = new StringBuilder();

	//클라이언트가 전송한 데이터 수신 -> mid_
	String mid_ = request.getParameter("mid_");

	//데이터베이스에 질의 
	//-> list(String key, String value) 메소드
	//-> 반환받은 Member 객체를 이용해서 XML 문서 동적 생성
	/*
	<?xml version="1.0" encoding="UTF-8"?>
	<members>
		<member>
			<mid_>M001</mid_>
			<name_>Hong</name_>
			<phone>010-1234-1234</phone>
			<email>hong@naver.com</email>
			<regDate>2018-03-15</regDate>
			<deptName>개발부</deptName>
		</member>		
	</members>
	*/
	MemberDAO dao = new MemberDAO();
	List<Member> list = dao.list("mid_", mid_);
	sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
	sb.append("<members>");
	for (int i = 0; i < list.size(); ++i) {
		Member m = list.get(i);
		sb.append("<member>");
		sb.append(String.format("<mid_>%s</mid_>", m.getMid_()));
		sb.append(String.format("<name_>%s</name_>", m.getName_()));
		sb.append(String.format("<phone>%s</phone>", m.getPhone()));
		sb.append(String.format("<email>%s</email>", m.getEmail()));
		sb.append(String.format("<regDate>%s</regDate>", m.getRegDate()));
		sb.append(String.format("<deptName>%s</deptName>", m.getDeptName()));
		sb.append("</member>");
	}
	sb.append("</members>");

	//Ajax 응답 처리
	//주의) ContentType을 text/xml로 설정해야 한다.
	response.setContentType("text/xml; charset=UTF-8");
	response.getWriter().println(sb.toString());
%>