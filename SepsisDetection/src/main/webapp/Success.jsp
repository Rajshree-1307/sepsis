<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="DefaultTop.jsp"></jsp:include>
<div class="container"><br/><br/>
<%
if(request.getParameter("type").toString().trim().equals("Reg"))
{
	%><h2 class="h2">Your Registration Done Successfully....</h2>
	<br/>
	<a href="home">Home</a>
<%}

if(request.getParameter("type").toString().trim().equals("UserReg"))
{
	%><h2 class="h2">Your Registration Done Successfully....</h2>
	<br/>
	<a href="home">Home</a>
<%}
 

else if(request.getParameter("type").toString().trim().equals("DocumentReg"))
{
	%><h2 class="h2">Patient Photo Uploaded Successfully....</h2>
	<br/>
	<a href="adminHome">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("pdf"))
{
	%><h2 class="h2">Report Generated Successfully....</h2>
	<br/>
	<a href="pathAdminHome">Home</a>
<%
}

else if(request.getParameter("type").toString().trim().equals("sreadingInserted"))
{
	%><h2 class="h2">Reading Inserted Successfully....</h2>
	<br/>
	<a href="pathAdminHome">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("CommReply"))
{
	%><h2 class="h2">Query Processed Successfully....</h2>
	<br/>
	<a href="doctorHome">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("Sepsis"))
{
	%><h2 class="h2">Sepsis Detected</h2>
	<br/>
	<a target="_blank" href="generatePDF?userid=<%=request.getParameter("userid").toString().trim() %>">Generate Report</a> | <a href="pathAdminHome">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("noSepsis"))
{
	%><h2 class="h2">Sepsis Not Detected</h2>
	<br/>
	<a target="_blank" href="generatePDF?userid=<%=request.getParameter("userid").toString().trim() %>">Generate Report</a>|
	<a href="pathAdminHome">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("RegComm"))
{
	%><h2 class="h2">Query Sent to Doctor Successfully....</h2>
	<br/>
	<a href="userHome">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("PathAdmin"))
{
	%><h2 class="h2">Hospital Admin Registration Done Successfully....</h2>
	<br/>
	<a href="adminHome">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("Doctor"))
{
	%><h2 class="h2">Doctor Registration Done Successfully....</h2>
	<br/>
	<a href="adminHome">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("habit"))
{
	%><h2 class="h2">Eating Habit Registration Done Successfully....</h2>
	<br/>
	<a href="adminHome">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("RegUserDisease"))
{
	%><h2 class="h2">Disease Registered Successfully....</h2>
	<br/>
	<a href="/userHome">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("RegUserReading"))
{
	%><h2 class="h2">Readings Registered Successfully....</h2>
	<br/>
	<a href="/<%=session.getAttribute("utype").toString().trim() %>Home">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("RegUserHeartReading"))
{
	%><h2 class="h2">Heart Test Readings Registered Successfully....</h2>
	<br/>
	<a href="/<%=session.getAttribute("utype").toString().trim() %>Home">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("PermissionGranted"))
{
	%><h2 class="h2">Permission Granted to Submit Readings Successfully....</h2>
	<br/>
	<a href="/<%=session.getAttribute("utype").toString().trim() %>Home">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("DiseaseDataSet"))
{
	%><h2 class="h2">New Disease Registered Successfully....</h2>
	<br/>
	<a href="ViewJobs.jsp">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("ChangePass"))
{
	%><h2 class="h2">Password Changed Successfully....</h2>
	<br/>
	<a href="<%=session.getAttribute("utype").toString().trim() %>Home">continue...</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("passEmail"))
{
	%><h2 class="h2">New Password has been sent on your registered email id  Successfully....</h2>
	<br/>
	<a href="home">continue...</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("Prev"))
{
	%><h2 class="h2">Preventive Measures Registered  Successfully....</h2>
	<br/>
	<a href="adminHome">continue...</a>
<%
}
%>
</div>
</body>
</html>