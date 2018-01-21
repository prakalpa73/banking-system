<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forgot Password</title>
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
<ul>
<p align=center>$ Odisha Bank $</p>
</ul>
<h3 align=center>*** Forgot Password Page ***</h3>
<form action="co.controller.ForgotPassword" method="post">
<table align="center">
<% String status= (String)request.getAttribute("info");
	if(status!=null)
		out.print("<tr><td colspan=2 align=center>"+status+"</td></tr>");
%>
<tr>
<td>
Email
</td>
<td>
<input type="text" name="email" required placeholder="Enter Email Id." size=30/>
</td>
</tr>
<tr>
<td></td>
<td>
<input type="submit" value="Forgot Password">&nbsp;&nbsp;&nbsp;<a href="default.jsp"> Go to Login Page </a>
</td>
</tr>
</table>
</form>
</body>
</html>