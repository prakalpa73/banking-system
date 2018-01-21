<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Banking Management</title>
<style type="text/css">
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
<h3 align=center>*** Login Page ***</h3>
<form action="co.controller.Login" method="post">
<table align=center>
<% String status= (String)request.getAttribute("INVALID");
	if(status!=null)
		out.print("<tr><td colspan=2 align=center>"+status+"</td></tr>");
 String st1= (String)request.getAttribute("Created");
	if(st1!=null)
		out.print("<tr><td colspan=2 align=center>"+st1+"</td></tr>");
%>
<tr>
<td>Username</td>
<td><input type=text name=uid size=30 required placeholder="User-id"></td>
</tr>
<tr>
<td>Password</td>
<td><input type=password name=pwd size=30 required placeholder="Password"></td>
</tr>
<tr>
<td></td>
<td>
<input type=submit value="Log In">&nbsp;&nbsp;&nbsp;<a href="forgotPassword.jsp">forgot password ?</a>
</td>
</tr>
<tr>
<td colspan=2 align=center>don't have an account ? <a href="create_ac.jsp">create account</a>
</td>
</tr>
</table>
</form>
</body>
</html>