<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="java.util.ArrayList,java.util.Iterator, co.db.UserProfile" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Profile</title>
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
<h3 align=center>*** My Profile Page ***</h3>
<% String status= (String)request.getAttribute("profileNotFound");
	if(status!=null)
		out.print("<tr><td colspan=2 align=center>"+status+"</td></tr>");
%>
<form>
<table align=center>
<%
    ArrayList<UserProfile> details=(ArrayList)request.getAttribute("userProfile");
        Iterator it=details.iterator();
        while(it.hasNext())
        {
        	UserProfile up=(UserProfile)it.next();
        	
%>
<tr>
<td>Username </td>
<td><%=up.username%></td>
</tr>
<tr>
<td>Account No. </td>
<td><%=up.acc_no%></td>
</tr>
<tr>
<td>Account Type </td>
<td><%=up.acc_type%></td>
</tr>
<tr>
<td>Name </td>
<td><%=up.name%></td>
</tr>
<tr>
<td>DOB </td>
<td><%=up.dob%></td>
</tr>
<tr>
<td>E-mail </td>
<td><%=up.email%></td>
</tr>
<tr>
<td>Phone </td>
<td><%=up.phone%></td>
</tr>
<tr>
<td>Address </td>
<td><%=up.address%></td>
</tr>
<tr>
<td>City </td>
<td><%=up.city%></td>
</tr>
<tr>
<td>District </td>
<td><%=up.district%></td>
</tr>
<tr>
<td>State </td>
<td><%=up.state%></td>
</tr>
<tr>
<td>Pincode </td>
<td><%=up.pincode%></td>
</tr>
<%
        }
    %>
</table>
</form>
</body>
</html>