
<%@page import="java.util.List"%>

<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="Top.jsp"></jsp:include>
<% try{ response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setDateHeader("Expires", -1);
if(session.getAttribute("userid")==null)
{
	response.sendRedirect("index.jsp");
}
%>
<div class="container">
  <div class="jumbotron"> 

     
<div class="row">

<div class="col-md-6"> <h2>Doctor Registration</h2>
   <div class="form-group"> 
    <form method="post" name="frm" action="/RegDoctor">
<table class="tblform">
<tr>
<td>Doctor Name</td>
<td>
<input type="text"  name="name" required class="form-control"></input>
</td>
</tr>
<tr>
				<td>Mobile Number</td>
				<td>
				<input type="text"  class="form-control"  name="mobile" pattern="^\d{10}$"  >
				</td>
			</tr>
			
                <tr><td>Email ID:</td>
                    <td><input type="text" class="form-control" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"  name="email" required></td>
                </tr>
                      
             <tr><td>Education Details</td>
                    <td><textarea  class="form-control"    name="eduDetails" required></textarea></td>
                </tr>
                <tr><td>Profession Details</td>
                    <td><textarea  class="form-control"    name="profDetails" required></textarea></td>
                </tr>
 <tr>
 <td colspan="2">
 <input type="submit" value="Submit" class="btn btn-primary"/>
 </td>
 </tr>
</table></form>
 
</div></div>
 <div class="col-md-6">
 <img src="images/doctorReg.png" width="80%"/>
 </div>
</div>
</div>
<%}
catch(Exception ex)
{
	
} %>

</div>
</body>
</html>