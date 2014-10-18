<%@ page language="java" contentType="text/html; charset=EUC-KR"  pageEncoding="EUC-KR" import="model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	session.setAttribute("cvo", request.getAttribute("cvo"));
%>
<html> 
<head>
<title>회원정보 보기</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
</head>
<body>
<center>
	<h3>개인 회원 정보 보기</h3>
	<br>
	  <!-- view Form  -->
	 <form action="CustomerServlet" method="post">
	 	<!-- 개인 정보 수정하는 update.jsp에서 활용될 데이타를 hidden tag로 설정 -->
	  
	  <table border="1" cellspacing="1" width="60%">
		  <tr>
			<td width=30%>사용자 아이디</td>
			<td width=70%>
			   	${requestScope.cvo.id}
			</td>
		  </tr>
		  <tr>
			<td width="30%">비밀번호</td>
			<td width="70%">
			  	${requestScope.cvo.password}	
			</td>
		  </tr>
		  <tr>
			<td width="30%">이름</td>
			<td width="70%">
			  	${requestScope.cvo.name}
			</td>
		  </tr>
		  <tr>
			<td width="30%">이메일 주소</td>
			<td width="70%">
			  	${requestScope.cvo.email}
			</td>
		  </tr>		  
	  </table>	
	  <br><br>	  

		<input type="button" value="update"	Onclick="location.href='update.jsp'">&nbsp;
		<input type="submit" value="allView">
		<input type="hidden" name="command" value="allView">
	 </form>
</center>	  
</body>
</html>
