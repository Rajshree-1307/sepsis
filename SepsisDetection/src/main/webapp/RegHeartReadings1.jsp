
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

<div class="col-md-5"> <h2>Heart Test Readings Registration</h2>
   <div class="form-group"> 
    <form method="post" name="frm" action="RegUserHeartReading1">
<table class="tblform">
  <tr><td>Chest Pain</td>
                    <td><select name="cp" required class="form-control">
                    <option value=""><--select--></option>
                    <option value="0">Typical Angina</option>
                    <option value="1">Atypical Angina </option>
                    <option value="2">Non-Anginal Pain</option>
                    <option value="3">Asymptomatic  </option>
                    </select></td>
                </tr>
<tr>
<td>Cholesterol </td>
<td>
<input type="number"  name="chol" required class="form-control"></input>
</td>
</tr>
<tr>
<td>Oldpeak (ST depression induced by exercise relative to rest )  </td>
<td>
<input type="number"  step=any   name="oldpeak" required class="form-control"></input>
</td>
</tr>
<tr>
<td>ECG Result</td>
<td>
<select name="ecg" required class="form-control">
                    <option value=""><--select--></option>
                    <option value="0">Normal</option>
                    <option value="1">Having ST-T wave abnormality (T wave inversions and/or ST elevation or depression of > 0.05 mV)</option>
                    <option value="2">showing probable or definite left ventricular hypertrophy by Estes' criteria 

</option>
  </select>
</td>
</tr>
 <tr>
<td>Thalach(maximum heart rate achieved)</td>
<td>
<input type="text"  name="thalach" required class="form-control"></input>
</td>
</tr>
<tr>
<td>Exang(exercise induced angina )</td>
<td>
<select name="exang" required class="form-control">
 <option value=""><--select--></option>
  <option value="0">NO</option>
 <option value="1">Yes</option>  
  </select>
</td>
</tr>
<tr>
<td>slope(the slope of the peak exercise ST segment)</td>
<td>
<select name="slope" required class="form-control">
 <option value=""><--select--></option>
  <option value="0">Upsloping</option>
 <option value="1">Flat</option> 
  <option value="2">Downsloping </option>   
  </select>
</td>
</tr>
<tr>
<td>ca(number of major vessels (0-3) colored by flourosopy)</td>
<td>
<select name="ca" required class="form-control">
 <option value=""><--select--></option>
  <option value="0">0</option>
 <option value="1">1</option> 
  <option value="2">2 </option>
  <option value="3">3 </option>   
  </select>
</td>
</tr>
   <tr>
<td>Thal(Thalassemia)</td>
<td>
<select name="thal" required class="form-control">
                    <option value=""><--select--></option>
                    <option value="1">Normal</option>
                    <option value="2">Fixed defect</option>
                    <option value="3">Reversible defect </option>
                     
                    </select>
</td>
</tr>              
 <tr>
 <td colspan="2">
 <input type="hidden" value="<%=request.getAttribute("userid").toString().trim() %>" name="userid"/>
 <input type="hidden" value="<%=request.getAttribute("pathUserid").toString().trim() %>" name="pathUserid"/>
 <input type="submit" value="Submit" class="btn btn-primary"/>
 </td>
 </tr>
</table></form>
 
</div></div>
 <div class="col-md-6">
  <img src="images/heartTest.jpg" class="img-responsive img-thumbnail"/>
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