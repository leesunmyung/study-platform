<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
	int total = 0;
	for(int i = 1 ; i <= 10; i++){
	total = total+i;
}
%>
1부터 10까지의 합: <%=total %>
</body>
</html>