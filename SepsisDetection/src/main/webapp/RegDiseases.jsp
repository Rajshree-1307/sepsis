
<%@page import="JavaBeans.Diseases"%>
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

<div class="col-md-6"> <h2>Diseases Registration</h2>
   <div class="form-group"> 
    <form method="post" name="frm" action="/RegDiseases">
<table class="tblform">
<tr>
<td>Disease Name</td>
<td>
<input type="text"  name="diseaseName" required class="form-control"></input>
</td>
</tr>
<tr>
				<td>Disease Description</td>
				<td>
				<input type="text"  class="form-control"  name="diseaseDesc"    >
				</td>
			</tr>
			
                <tr><td>Dependent diseases</td>
                    <td><textarea class="form-control" name="dependentDiseases" required></textarea></td>
                </tr>
                         <tr><td>Factors</td>
                    <td><select  class="form-control"    name="factors" required>
                    <% List<String> lst=(List<String>)request.getAttribute("lst"); %>
                    <% for(int i=0;i<lst.size();i++){ %>
                    <option value='<%=lst.get(i).toString().trim() %>'><%=lst.get(i).toString().trim() %></option>
                    <%} %>
</select>
</td>
                </tr>  
             <tr><td>High Risk Diseases</td>
                    <td><textarea  class="form-control"    name="highRiskDiseases" required></textarea></td>
                </tr>
                      
                  <tr><td>Work out hrs(if the disease can be prevented with exercise) </td>
                    <td><textarea  class="form-control"    name="workoutworkOutHrs" required></textarea></td>
                </tr>
 <tr>
 <td colspan="2">
 <input type="submit" value="Submit" class="btn btn-primary"/>
 </td>
 </tr>
</table></form>
 
</div></div>
 <div class="col-md-6">
 <img src="images/expertReg.jpeg" width="80%"/>
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