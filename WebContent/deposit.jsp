<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>deposit</title>
<style>
body{
background-color:orange;
font-type:verdana;
font-size:20;
}
table{
background-color:#333;
width:500px;
pading:24px;
color:white;
font-size:18px;
border-radius:10px;
}
tr{
height:45px;
}
a{
    color:white;
    font-size:15px;
    text-decoration:none;
    cursor:pointer;
   }
   ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    background-color: #333;
}

p{
color:white;
font-size:20px;
}
li {
    float: left;
}

li a {
    display: block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}

li a:hover {
    background-color: #111;
}
</style>
</head>
<body>
<%
if(session.getAttribute("uid")==null)
	response.sendRedirect("default.jsp");
%>
<jsp:include page="topmenu.jsp"></jsp:include>
<h3 align=center>*** Money Deposit Page ***</h3>
<form action="co.controller.Deposit" method="post">
<table align="center">
<% String status= (String)request.getAttribute("Deposited");
	if(status!=null)
		out.print("<tr><td colspan=2 align=center>"+status+"</td></tr>");
 String st1= (String)request.getAttribute("depo_failed");
	if(st1!=null)
		out.print("<tr><td colspan=2 align=center>"+st1+"</td></tr>");
%>
<tr>
<td>
Enter Amount
</td>
<td>
<input type="text" name="depo_amount" required placeholder="Enter Amount" size=30/>
</td>
</tr>
<tr>
<td></td>
<td>
<input type="submit" value="Deposit">
</td>
</tr>
</table>
</form>
</body>
</html>