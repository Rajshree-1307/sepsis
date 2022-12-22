
<%@page import="JavaBeans.Features"%>
<%@page import="java.util.ArrayList"%>
 
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
<title> </title>
<script language="Javascript" type="text/javascript">
<!--

function createRequestObject() {
    var tmpXmlHttpObject;
    if (window.XMLHttpRequest) {
            tmpXmlHttpObject = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        tmpXmlHttpObject = new ActiveXObject("Microsoft.XMLHTTP");
    }

    return tmpXmlHttpObject;
}


var http = createRequestObject();

function makeGetRequest(st) {
     groupName=document.frmSearch.groupName.value;
    http.open('get', 'GetUsers?searchText=' + st+'&groupName='+groupName);
    http.onreadystatechange = processResponse;
    http.send(null);
}

function processResponse() {
    if(http.readyState == 4){
        var response = http.responseText;
        document.getElementById('users').innerHTML = response;
    }
}
-->
</script>

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

<div class="col-md-12"> <h2>Reading Insertion</h2>
    <%
List<Features> lst=(ArrayList<Features>)request.getAttribute("lst");
if(lst.size()>0)
{
%>
<form  method="post" action="sepsisReg">
<table class="table table-bordered">
<tr>
<th>Feature</th>
<th>Normal Range</th>
<th>Values</th>
<th>Sepsis Range
</tr>
<c:forEach var="userdsc" items="${lst}">
		<tr>  	 <td>${userdsc.feature}
  <input type="hidden" name="patientUid" value="<%=request.getAttribute("patientUid").toString().trim() %>"/>
   		 </td>
   		 <td>
   		 ${userdsc.range}
   		 </td>
		 <td>
		 <c:choose>
    <c:when test="${userdsc.val==-1}">
       <select name="${userdsc.feature}" class="form-control">
       <option value="1">Yes</option>
       <option value="2">No</option>
       </select>
    </c:when>    
    <c:otherwise>
        <input type="text" name="${userdsc.feature}" class="form-control"/>
		
    </c:otherwise>
</c:choose>
		 
		 </td>
		  <th> 
		  
		   <c:choose>
		  <c:when test="${userdsc.comparison=='greaterthan'}">
        > ${userdsc.val}  
    </c:when> 
     <c:when test="${userdsc.comparison=='lessthan'}">
        < ${userdsc.val}  
    </c:when>    
    <c:otherwise> 
    Yes   
    </c:otherwise>
</c:choose>
		 
   		 
   		 </th>
			</tr> 
		</c:forEach></table>
		<input type="submit" value="Submit" class="btn btn-primary"/>
</form>
<%}else{ %>
<h7>No Record Found!!</h7>
<%} %>
</div>
</div>
<%}
catch(Exception ex)
{
	
} %>

</div>
</body>
</html>