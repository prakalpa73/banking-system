<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="java.util.ArrayList,java.util.Iterator, co.db.DisplayTransaction" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>statement</title>
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
 <h3 align=center>*** Display Transactions Page ***</h3>
<form action="co.controller.Display" method="post">
<table border=0.7 align=center>
<% String status= (String)request.getAttribute("no_rcds");
	if(status!=null)
		out.print("<tr><td colspan=2 align=center>"+status+"</td></tr>");
%>
<tr>
<td>From </td>
<td><input type="date" name="date_from" min="2000-01-02"></td>
</tr>
<tr>
<td>To</td>
<td><input type="date" name="date_to" min="2000-01-02"></td>
</tr>
<tr>
<td></td>
<td><input type="submit" value="Display"/></td>
</tr>
</table>
<br>
<br>
</form>
</body>
</html>