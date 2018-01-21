<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>create a/c</title>
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
<h3 align=center>*** Account Creation Page ***</h3>
<% String status= (String)request.getAttribute("Failed");
	if(status!=null)
		out.print("<p align=left font-color=red>"+status+"</p>");
%>
<form action="co.controller.Ac_creation" method="post">
<table align=center>
<tr>
<td>Username </td>
<td><input type=text name=uid required size=30 placeholder="Enter Username"></td>
</tr>
<tr>
<td>Password </td>
<td><input type=password name=pwd required size=30 placeholder="Enter Password"></td>
</tr>
<tr>
<td>Type of a/c</td>
<td>
<select name=slt>
<option> Select </option>
<option value="saving"> Saving Account </option>
<option value="current"> Current Account  </option>
</select>
</td>
</tr>
<tr>
<td>Opening Amount</td>
<td>
<select name=balance>
<option value="1000"> 1000 </option>
<option value="2000"> 2000  </option>
<option value="3000"> 3000  </option>
<option value="4000"> 4000  </option>
<option value="5000"> 5000  </option>
</select>
</td>
</tr>
<tr>
<td>Name </td>
<td><input type=text name=name required size=30 placeholder="Enter Name"></td>
</tr>
<tr>
<td>DOB</td>
<td><input type=text name=dob required size=30 placeholder="yyyy-mm-dd"></td>
</tr>
<tr>
<td>E-mail</td>
<td><input type=email name=email required size=30 placeholder="Enter Email"></td>
</tr>
<tr>
<td>Phone</td>
<td><input type=text name=phone required size=30 placeholder="Enter Phone No."></td>
</tr>
<tr>
<td>Address</td>
<td><textarea row=16 cols=23 name=text placeholder="Enter Address"></textarea></td>
</tr>
<tr>
<td>City</td>
<td><input type=text name=city required size=30 placeholder="Enter City"></td>
</tr>
<tr>
<td>District</td>
<td><input type=text name=district required size=30 placeholder="Enter District"></td>
</tr>
<tr>
<td>State</td>
<td><input type=text name=state required size=30 placeholder="Enter State"></td>
</tr>
<tr>
<td>Pincode</td>
<td><input type=text name=pincode required size=30 placeholder="Enter Pincode"></td>
</tr>
<tr>
<td></td>
<td><input type=submit value="Submit">&nbsp;&nbsp;&nbsp;<a href="default.jsp"> Go to Login Page </a></td>
</tr>
</table>
</form>
</body>
</html>