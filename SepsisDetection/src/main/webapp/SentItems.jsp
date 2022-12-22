
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title> Plant Disease Detection</title>
</head>
<body>
<jsp:include page="Top.jsp"></jsp:include>
<% try{ response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setDateHeader("Expires", -1);
if(session.getAttribute("userid")==null)
{
	response.sendRedirect("qrhome");
}
%>
<div class="container-fluid">
   

       
<div class="row">
 
<div class="col-md-12">
  <center><h2>Sent Queries</h2></center>
   <table class="table table-bordered">
 <tr>
 <th> Doctor Name</th>
 <th>Query</th>
 <th>Date</th> 
 </tr>
 <c:forEach var="userdsc" items="${lst}">
		 <tr>
		<td>${userdsc.expertName }</td>
		<td>${userdsc.query }</td>
		<td>${userdsc.getDt() }</td>
		<td>${userdsc.reply }</td>
		<td>${userdsc.sts }</td>
		
		<td>
			<c:choose>
    <c:when test="${userdsc.attach2!='NA'}">
        <a href="CommAttach/${userdsc.expertUserid }/${userdsc.attach2 }" target="_blank">View Attachment</a>
		
    </c:when>    
    
</c:choose>
		
		
		</td>
		  
		 </tr>
		</c:forEach> 
		 
		
		</table>
  

<%
}
catch(Exception ex)
{
	System.out.println("err in jsp="+ex.getMessage());
} 
 %>  
</div>
</div>
 


</div>
</body>
</html>