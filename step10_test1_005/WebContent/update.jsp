<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" import="model.*" %>

<% //view.jsp���� ���ǿ� �����  ��ü ȹ���ؼ� update�� ���� ȭ�鿡 ����Ÿ ���

%>


<html>
<head>
<title>���� ���� ����</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
</head>
<body>
<%
   CustomerVo cvo = (CustomerVo)session.getAttribute("cvo");
%>
<br>
<center>
<h3>���� ���� �����ϱ�</h3>
<br>

<form action="CustomerServlet" method="post">
	  <!-- update Form  -->	
	  <table border="1" cellspacing="1" width="60%">
		  <tr>
			<td width=30%>����� ���̵�</td>
			<td width=70%>	
				<%= cvo.getId() %>			
			</td>
		  </tr>
		  <tr>
			<td width="30%">�̸�</td>
			<td width="70%">	
				<%= cvo.getName() %>
			</td>
		  </tr>
		  <tr>
			<td width="30%">��й�ȣ ����</td>
			<td width="70%">
				<input type="password" name="password" value="<%= cvo.getPassword() %>">
			</td>
		  </tr>			  
		
		  <tr>
		
			<td width="30%">�̸��� �ּ�</td>
			<td width="70%">
				<input type="type" name="email" value="<%= cvo.getEmail() %>">
			</td>		  
		  <tr>				  
	</table>  
	<p/>

	<%-- hidden tag�ϼ� �ϼ���
			 value������ �����ؾ� �ϴ� ����? 
	--%>
	<input type="hidden" value="update"  name="command">

	<input type="submit" value="����" > &nbsp;
	<input type="reset" value="���">&nbsp;
	<input type="button" value="��κ���" Onclick="location.href='CustomerServlet?command=allView'">
</form>

</body>
</html>
