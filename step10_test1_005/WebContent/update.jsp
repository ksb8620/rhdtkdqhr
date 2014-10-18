<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" import="model.*" %>

<% //view.jsp에서 세션에 저장된  객체 획득해서 update를 위한 화면에 데이타 출력

%>


<html>
<head>
<title>개인 정보 수정</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
</head>
<body>
<%
   CustomerVo cvo = (CustomerVo)session.getAttribute("cvo");
%>
<br>
<center>
<h3>개인 정보 수정하기</h3>
<br>

<form action="CustomerServlet" method="post">
	  <!-- update Form  -->	
	  <table border="1" cellspacing="1" width="60%">
		  <tr>
			<td width=30%>사용자 아이디</td>
			<td width=70%>	
				<%= cvo.getId() %>			
			</td>
		  </tr>
		  <tr>
			<td width="30%">이름</td>
			<td width="70%">	
				<%= cvo.getName() %>
			</td>
		  </tr>
		  <tr>
			<td width="30%">비밀번호 수정</td>
			<td width="70%">
				<input type="password" name="password" value="<%= cvo.getPassword() %>">
			</td>
		  </tr>			  
		
		  <tr>
		
			<td width="30%">이메일 주소</td>
			<td width="70%">
				<input type="type" name="email" value="<%= cvo.getEmail() %>">
			</td>		  
		  <tr>				  
	</table>  
	<p/>

	<%-- hidden tag완성 하세요
			 value값으로 설정해야 하는 값은? 
	--%>
	<input type="hidden" value="update"  name="command">

	<input type="submit" value="수정" > &nbsp;
	<input type="reset" value="취소">&nbsp;
	<input type="button" value="모두보기" Onclick="location.href='CustomerServlet?command=allView'">
</form>

</body>
</html>
