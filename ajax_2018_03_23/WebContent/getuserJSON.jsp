<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="java.util.*, com.test.*"%>
<%
 //JSP code
 request.setCharacterEncoding("UTF-8");
 String contextRoot = request.getContextPath();
 StringBuilder sb = new StringBuilder();

 //클라이언트가 전송한 데이터 수신 -> mid_
 String mid_ = request.getParameter("mid_");
 if (mid_ == null) {
  mid_ = "M01";
 }

 //데이터베이스에 질의
 // ->list(String key, String value) 메소드
 // ->반환받은 Member 객체를 이용해서 JSON 문서 동적 생성
 MemberDAO dao = new MemberDAO();
 List<Member> list = dao.list("Mid", mid_);
 sb.append("{\"members\":[");
 if (list.size() > 0) {
  for (int i = 0; i < list.size(); ++i) {
   Member m = list.get(i);
   sb.append(String.format(
     "{\"mid_\":\"%s\",\"name_\":\"%s\", \"phone\":\"%s\", \"email\":\"%s\", \"regDate\":\"%s\", \"deptName\":\"%s\", \"picture\":\"avatar.png\"}",
     m.getMid_(), m.getName_(), m.getPhone(), m.getEmail(), m.getRegDate(), m.getDeptName()));
   if(i<list.size()-1) {
    sb.append(",");
   } else{
    sb.append("]}");
   }
  }
 }
 out.println(sb.toString());
%>
<%--
Use Library
<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="java.util.*, com.test.*, org.json.*"%>
<%
 //JSP code
 request.setCharacterEncoding("UTF-8");
 String contextRoot = request.getContextPath();
 StringBuilder sb = new StringBuilder();

 //클라이언트가 전송한 데이터 수신 -> mid_
 String mid_ = request.getParameter("mid_");
 if (mid_ == null) {
  mid_ = "M01";
 }

 //데이터베이스에 질의
 // ->list(String key, String value) 메소드
 // ->반환받은 Member 객체를 이용해서 JSON 문서 동적 생성 -> JSONObject, JSONArray 클래스 (json-20180130.jar 파일 필요)
 MemberDAO dao = new MemberDAO();
 List<Member> list = dao.list("Mid", mid_);
 
 JSONObject obj = new JSONObject();
 JSONArray jarray = new JSONArray();
 
 
 if (list.size() > 0) {
  for (int i = 0; i < list.size(); ++i) {
   Member m = list.get(i);
   JSONObject temp = new JSONObject();
   temp.put("mid_",m.getMid_());
   temp.put("name_",m.getName_());
   temp.put("phone",m.getPhone());
   temp.put("email",m.getEmail());
   temp.put("regDate",m.getRegDate().toString());
   temp.put("deptName",m.getDeptName());
   temp.put("picture","avatar.png");
   
   jarray.put(temp);
  }
  
  obj.put("members", jarray);
 }
 out.println(obj);
%>
--%>
