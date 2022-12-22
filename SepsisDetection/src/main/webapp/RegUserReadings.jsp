
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

<div class="col-md-5"> <h2>Medical Test Readings Registration</h2>
   <div class="form-group"> 
    <form method="post" name="frm" action="RegUserReading">
<table class="tblform">
  <tr><td>Test Name</td>
                    <td><select name="testname" required class="form-control">
                    <option value=""><--select--></option>
                    <option value="CBC">CBC</option>
                    <option value="Sugar">Blood Sugar </option>
                    <option value="BP">BP</option>
                    <option value="Thyroid">Thyroid </option>
                    </select></td>
                </tr>
<tr>
<td>Specifications</td>
<td>
<input type="text"  name="specification" required class="form-control"></input>
</td>
</tr>
 <tr>
<td>Value</td>
<td>
<input type="text"  name="val" required class="form-control"></input>
</td>
</tr>
   <tr>
<td>Result</td>
<td>
<select name="result" required class="form-control">
                    <option value=""><--select--></option>
                    <option value="CBC">Normal</option>
                    <option value="High">High</option>
                    <option value="Low">Low</option> 
                    </select>
</td>
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
 <th>Test Name</th>
 <th>Specifications</th>
 <th>Value</th>
 <th>Result</th>
 <th>Date</th>
 </tr>
 <c:forEach var="userdsc" items="${lst}">
		<tr><td>${userdsc.getTestname()}</td>  
			<td>${userdsc.getSpecification()}</td>
			<td>${userdsc.getVal()}</td>
			<td>${userdsc.getResult}</td>
			<td>${userdsc.getDt()}</td>
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