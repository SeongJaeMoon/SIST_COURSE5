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
	//-> 반환받은 Member 객체를 이용해서 테이블 태그 동적 생성
	MemberDAO dao = new MemberDAO();
	List<Member> list = dao.list("mid_", mid_);
	if (list.size() > 0) {
		sb.append("<table class=\"table\"><tbody>");
		for (int i = 0; i < list.size(); ++i) {
			Member m = list.get(i);
			sb.append(String.format(
					"<tr><td colspan=\"2\" style=\"text-align:center\"><img src=\"%s\" style=\"width:40%%\"></td></tr>",
					contextRoot + "/resources/picture/avatar.png"));
			sb.append(String.format("<tr><td><strong>mid_</strong></td><td>%s</td></tr>", m.getMid_()));
			sb.append(String.format("<tr><td><strong>name_</strong></td><td>%s</td></tr>", m.getName_()));
			sb.append(String.format("<tr><td><strong>phone</strong></td><td>%s</td></tr>", m.getPhone()));
			sb.append(String.format("<tr><td><strong>email</strong></td><td>%s</td></tr>", m.getEmail()));
			sb.append(String.format("<tr><td><strong>regDate</strong></td><td>%s</td></tr>", m.getRegDate()));
			sb.append(String.format("<tr><td><strong>deptName</strong></td><td>%s</td></tr>", m.getDeptName()));

		}
		sb.append("</tbody></table>");
	}

	//Ajax 응답 처리
	out.println(sb.toString());
%>