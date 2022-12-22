
<%@page import="java.util.List"%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	response.sendRedirect("home");
}
%>
<div class="container">
  <div class="jumbotron"> 

     
<div class="row">

<div class="col-md-5"> <h2>Existing Diseases Registration</h2>
   <div class="form-group"> 
    <form method="post" name="frm" action="RegUserDisease">
<table class="tblform">
<tr>
<td>Disease Name</td>
<td>
<input type="text"  name="diseaseName" required class="form-control"></input>
</td>
</tr>
 
                <tr><td>Disease Status</td>
                    <td><select name="diseaseSts" required>
                    <option value=""><--select--></option>
                    <option value="Undercontrolled">Under Control</option>
                    <option value="Uncontrolled">Giving too much trouble </option>
                    </select></td>
                </tr>
 <tr>
 <td colspan="2">
 <input type="hidden" value="<%=session.getAttribute("userid").toString().trim() %>" name="userid"/>
 <input type="submit" value="Submit" class="btn btn-primary"/>
 </td>
 </tr>
</table></form>
 
</div></div>
 <div class="col-md-6">
 <table class="table table-bordered">
 <tr>
 <th>Disease Name</th>
 <th>Disease Status</th>
 </tr>
 <c:forEach var="userdsc" items="${lst}">
		<tr><td>${userdsc.getDiseaseName()}</td>  
			<td>${userdsc.getDiseaseSts()}</td>
			</tr>
		</c:forEach></table>
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