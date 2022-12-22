
 
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

<div class="col-md-12"> <h2>Upload Patient Photos</h2>
   <div class="form-group"> 
    <form method="post" name="frm" action="/RegDocs" enctype="multipart/form-data">
<table class="tblform">

 <tr><td>File</td><td>
    <input type="file" name="file" class="form-control"  ></input>
 </td></tr>
  
 <tr><td colspan="2">
   
    <input type="submit" value="Submit" />
    </td></tr>
 
</table></form>
 
</div></div>
 
</div>
</div>
<%}
catch(Exception ex)
{
	
} %>

</div>
</body>
</html>