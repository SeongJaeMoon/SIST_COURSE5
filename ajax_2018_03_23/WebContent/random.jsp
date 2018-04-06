<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%-- 주의) Ajax 응답 페이지 구성시 불필요한 반환값을 작성하지 않는다. --%>
<%
	//JSP code
	request.setCharacterEncoding("UTF-8");
	String contextRoot = request.getContextPath();
	StringBuilder sb = new StringBuilder();

	//메시지를 여러개 준비(배열 or 컬렉션)하고, 요청시마다 다른(난수) 메시지 반환. 
	String [] array = {"Hello", "Hi", "Good", "Bye", "You"};
	
	int random = (int) (Math.random() * 6);
	
	out.println(array[random]);
	
	//out.println("Hello, Ajax World!");
%>