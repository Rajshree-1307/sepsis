
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
   

       
<div class="row">
 <div class="col-md-6">
 <img src="images/heartTest.jpg" class="img-responsive img-thumbnail"/>
 </div>
<div class="col-md-6">

<br/>
<%
  if(request.getAttribute("output").toString().trim().equals("1"))
  {
	  %><h5>According to the readings, it seems that you are on the risk of having heart disease.. Please consult with any specialist doctor!!</h5><%
  }
  else
  {
	  %><h5>According to the readings, it seems that your heart is healthy..</h5><%
  }
}

catch(Exception ex)
{
	
} 
 %>  
</div>
</div>
 


</div>
</body>
</html>