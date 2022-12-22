 
<%@page import="java.util.ArrayList"%>
 
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

function makeGetRequest1(st) {
   // st=document.frm.state.value;
   //alert(st);
    http.open('get', 'Diseases?plantName=' + st);
    http.onreadystatechange = processResponse1;
    http.send(null);
}

function processResponse1() {
    if(http.readyState == 4){
        var response = http.responseText;
        document.getElementById('diseases').innerHTML = response;
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

<div class="col-md-6">
<img src="images/Registration.png" width="100%"/>
</div>
<div class="col-md-6">
<div ><center><h2>Process Query</h2></center><br/>
 <form id="frm" action="process" method="post"  enctype="multipart/form-data">
									 
									  <table class="tblform">
									  
									    
									 <tr><td>Reply</td>
                <td> 
                <textarea name="reply" required class="form-control"></textarea>
                </td></tr>
             
									 </tr>
									  
	           <tr> <td>  Attachment(if any)</td>
		            <td>
		             <input type="file" name="file" class="form-control"  ></input>
 
		            <input type="hidden" name="userid" value="<%=session.getAttribute("userid").toString().trim() %>"/>
		            <input type="hidden" name="commId" value="<%=request.getAttribute("commId").toString().trim() %>"/>
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

</div>
</body>
</html>