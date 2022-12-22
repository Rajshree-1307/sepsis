
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

<div class="col-md-12"> <h2>Heart Test Readings Registration</h2>
    
 <table class="table table-bordered">
 <tr>
 <th>Reading Submitted By</th>
 <th>Rest ECG</th>
 <th>Thalach</th>
 <th>Exang</th>
 <th>Oldpeak</th>
 <th>Slope</th>
 <th>Thal</th>
 <th>CA</th>
 <th>ChestPain</th>
 <th>Cholesterol</th>
 </tr>
 <c:forEach var="userdsc" items="${lst}">
		<tr><td>${userdsc.getEcg()}</td>  
			<td>${userdsc.getThalach()}</td>
			<td>${userdsc.getExang()}</td>
			<td>${userdsc.getOldpeak()}</td>
			<td>${userdsc.getSlope()}</td>
			<td>${userdsc.getThal()}</td>
			<td>${userdsc.getCa()}</td>
			<td>${userdsc.getCp()}</td>
			<td>${userdsc.getChol()}</td>
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