 
<%@page import="java.util.ArrayList"%>
<%@page import="JavaBeans.UserReg"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script language="Javascript" type="text/javascript">
 

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
   // st=document.frm.state.value;
   
    http.open('get', 'Cities?state=' + st);
    http.onreadystatechange = processResponse;
    http.send(null);
}

function processResponse() {
    if(http.readyState == 4){
        var response = http.responseText;
        document.getElementById('cities').innerHTML = response;
    }
}
 
</script>

</head>
<body>
<jsp:include page="Top.jsp"></jsp:include>
<%  response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setDateHeader("Expires", -1);%>
<div class="container">
<div class="row">
<%try{ %>
<div class="col-md-6">
<img src="images/Registration.png" width="100%"/>
</div>
<div class="col-md-6">
<div ><center><h2>Send Query to Expert</h2></center><br/>
 <form id="frm" action="ExpertCommReg" method="post"  enctype="multipart/form-data">
									 
									  <table class="tblform">
									  
									   <%
									 
									 List<UserReg> lst=(ArrayList<UserReg>)request.getAttribute("lst");
									 %>
									  <tr>
									 <td>Doctor Name
									 </td>
									 <td> 
									 <select required name="expertUserid" class="form-control" >
									 <option value=""><--select--></option>
										<%for(int i=0;i<lst.size();i++)
											{%>
									 <option value="<%=lst.get(i).getUserid() %>"><%=lst.get(i).getName() %></option>											
											<%}%>															  
									 </select>
									 </td>
									 </tr>
									  
									  
	            <td>  Query</td>
		            <td>
		            <textarea rows="5" cols="25" name="msg" class="form-control" ></textarea>          
		             </td>
            </tr>
								 
	  <tr> <td>  Attachment(if any)</td>
		            <td>
		             <input type="file" name="file" class="form-control" ></input>
 
		            <input type="hidden" name="userid" value="<%=session.getAttribute("userid").toString().trim() %>"/>
		            <input type="hidden" name="username" value="<%=session.getAttribute("username").toString().trim() %>"/>
		            </td>
            </tr>

									 <tr>
									 <td colspan="2"><input type="submit" value="Submit" class="btn btn-primary"/>
									 </td></tr>
									 </table>
									 </form>
</div>
</div>

</div>
<%}catch(Exception ex) {
System.out.println("err="+ex.getMessage());
}%>
</div>
</body>
</html>