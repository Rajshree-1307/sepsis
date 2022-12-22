<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="JavaBeans.States"%>
<%@page import="java.util.List"%>
<%@page import="JavaBeans.GetStateNCities"%>
<!DOCTYPE html>
<html>
<head>
<title>Early prediction of lifestyle diseases</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Medicinal Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, Sony Ericsson, Motorola web design" />
<script type="applisalonion/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
<!-- Custom Theme files -->
<link href="css/style.css" rel='stylesheet' type='text/css' />	
<link rel="stylesheet" href="css/slider.css">
<script src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<!--/web-font-->
<link href='//fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
<link href='//fonts.googleapis.com/css?family=Open+Sans:400,300,600,700' rel='stylesheet' type='text/css'>
<!--/script-->
<script type="text/javascript">
			jQuery(document).ready(function($) {
				$(".scroll").click(function(event){		
					event.preventDefault();
					$('html,body').animate({scrollTop:$(this.hash).offset().top},900);
				});
			});
</script>

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
   
    http.open('get', 'WCities?state=' + st);
    http.onreadystatechange = processResponse1;
    http.send(null);
}

function processResponse1() {
    if(http.readyState == 4){
        var response = http.responseText;
        document.getElementById('wcities').innerHTML = response;
    }
}
 
</script>
</head>
<body>
<!--start-home-->
 <jsp:include page="DefaultTop.jsp"></jsp:include>
		<!-- Services -->
		<div class="services" id="services">
			<div class="container">

				<div class="inner-w3">
					    <div class="sub-hd">
						<h3 class="tittle"> User<span>Registration</span></h3>
					</div>
					</div>
				 
				<div class="inner_tabs">
				  	<div class="bs-example bs-example-tabs" role="tabpanel" data-example-id="togglable-tabs">
						 
						<div id="myTabContent" class="tab-content">
							<div role="tabpanel" class="tab-pane fade in active" id="expeditions" aria-labelledby="expeditions-tab">
								 
								<div class="col-md-12 col-sm-12 tab-info">
									 <form id="frm" action="RegUser" method="post">
									 <table class="tblform"><tr>
									 <td>
									  <table class="tblform">
									 <tr><td>UserName</td>
                <td><input type="text" class="form-control"  name="name" required></td></tr>
            <tr><td>UserID</td>
                <td><input type="text"  class="form-control"  name="userid" required></td></tr>
                       
             <tr><td>Password</td>
                <td><input type="password"  class="form-control"  name="pass" required></td></tr>
            
			<tr>
				<td>Mobile Number</td>
				<td>
				<input type="text"  class="form-control"  name="mobile" pattern="^\d{10}$"  >
				</td>
			</tr>
			
                <tr><td>Email ID:</td>
                    <td><input type="text" class="form-control" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"  name="email" required></td>
                </tr>
                      
            <tr>
	            <td>Address</td>
		            <td>
		            <textarea rows="5" cols="25" name="address" class="form-control" ></textarea>          
		            </td>
            </tr>
            
            <tr>
<td>Approximate Water Intake in liters </td>
<td>
<input type="text" name="waterIntake" required class="form-control"/>
</td>
</tr>
            </table></td><td><table class="tblform">
            <tr>
									 <td>Working Enviornment
									 </td>
									 <td> 
									 <select required name="wenv" class="form-control">
									 <option value=""><--select--></option>
									 <option value="AC">Air Conditioned</option>
									 <option value="Non AC">Non Air Conditioned</option>
									 <option value="Outside">Outside</option>
									  
									 </select>
									 </td>
									 </tr>
									  <tr>
									 <td>Total Working Hrs
									 </td>
									 <td> 
									 <input required type="number" max="12" min="0" name="workingHrs" class="form-control"/>
									 </td>
									 </tr>
									 <%
									 GetStateNCities obj=new GetStateNCities();
									 obj.getStates();
									 List<States> lst=obj.getLststate();
									 %>
									  <tr>
									 <td>State
									 </td>
									 <td> 
									 <select required name="state" class="form-control" onchange="makeGetRequest(this.value)">
									 <option value=""><--select--></option>
										<%for(int i=0;i<lst.size();i++)
											{%>
									 <option value="<%=lst.get(i).getState() %>"><%=lst.get(i).getState() %></option>											
											<%}%>															  
									 </select>
									 </td>
									 </tr>
									   <tr>
									 <td>City
									 </td>
									 <td> 
									<div id="cities"></div>
									 </td>
									 </tr>
									  <tr>
									 <td>Profession
									 </td>
									 <td> 
									 <input required type="text" name="prof" class="form-control"/>
									 </td>
									 </tr>
									  <tr>
	            <td>Working Address</td>
		            <td>
		            <textarea rows="5" cols="25" name="waddr" class="form-control" ></textarea>          
		            </td>
            </tr>
            <tr>
<td>Sleeping Hours </td>
<td>
<input type="text" name="txtsleepHrs" required class="form-control"/>
</td>
</tr>
								 
									 </table></td><td><table class="tblform">
									   <tr>
									 <td>Working Position
									 </td>
									 <td> 
									 <select required name="workingPos" class="form-control">
									 <option value=""><--select--></option>
									 <option value="Standing">Standing</option>
									 <option value="Sitting">Sitting</option>
									 <option value="Walking">Walking</option>
									  
									 </select>
									 </td>
									 </tr>
									   <tr>
									 <td>Working State
									 </td>
									 <td> 
									 <select required name="wstate" class="form-control"  onchange="makeGetRequest1(this.value)">
									 <option value=""><--select--></option>
										<%for(int i=0;i<lst.size();i++)
											{%>
									 <option value="<%=lst.get(i).getState() %>"><%=lst.get(i).getState() %></option>											
											<%}%>															  
									 </select>
									 </td>
									 </tr>
									   <tr>
									 <td>Working City
									 </td>
									 <td> 
									<div id="wcities"></div>
									 </td>
									 </tr>
									   <tr>
									 <td>Working City Type
									 </td>
									 <td> 
									 <select required name="wcityType" class="form-control">
									 <option value=""><--select--></option>
									 <option value="Metro">Metropolitan</option>	
									  <option value="NonMetro">Non Metropolitan</option>					  
									 </select>
									 </td>
									 </tr>
									  
<tr>
<td>DOB</td>
<td>
<input type="date" name="dob" required class="form-control"/>
</td>
</tr>
<tr>
<td>Gender</td>
<td>
<input type="radio" name="gender" value="Male"   checked="true" required >Male</input>
<input type="radio" name="gender" value="Female"  required>Female</input>
</td>
</tr>
<tr>
<td>Height </td>
<td>
<input type="text" name="height" required class="form-control"/>
</td>
</tr>
<tr>
<td>Weight </td>
<td>
<input type="text" name="weight" required class="form-control"/>
</td>
</tr>
<tr>
<td>Daily Work Out Hours </td>
<td>
<input type="text" name="workout" required class="form-control"/>
</td>
</tr>
<tr>
<td>Job Type</td>
<td>
<input type="radio" name="jobType" value="yes"   checked="true" required >Computer Dependent</input>
<input type="radio" name="jobType" value="no"  required>Not dependent on Computer</input>
</td>
</tr>


</table></tr>

									 <tr>
									 <td colspan="2"><input type="submit" value="Submit" class="btn btn-primary"/>
									 </td></tr>
									 </table>
									 </form>
								</div>
								<div class="clearfix"></div>
							</div>
							</div></div></div></div></div>
							   
							 
						</div>
					</div>
				</div>

				
			</div>
		</div>
		<!-- //Services -->
    <!--footer-->
	
	
	
	
  
	<!--/start-footer-section-->
			<div class="footer-section">
				<div class="container">
					<div class="footer-grids wow bounceIn animated" data-wow-delay="0.4s">
						 
					 
						<div class="clearfix"></div>
					</div>
			</div>
		</div>
	<!--//end-footer-section-->
	<!--//footer-->
	<div class="footer-bottom">
	<div id="register"></div>
		<div class="container">
			<p>© 2019-2020 Early . All rights reserved | Design by <a href="http://w3layouts.com">W3layouts</a></p>
		</div>
	</div>
		<!--start-smooth-scrolling-->
						<script type="text/javascript">
									$(document).ready(function() {
										/*
										var defaults = {
								  			containerID: 'toTop', // fading element id
											containerHoverID: 'toTopHover', // fading element hover id
											scrollSpeed: 1200,
											easingType: 'linear' 
								 		};
										*/
										
										$().UItoTop({ easingType: 'easeOutQuart' });
										
									});
								</script>
								<!--end-smooth-scrolling-->
		<a href="#house" id="toTop" class="scroll" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>
	<script src="js/bootstrap.js"></script>

</body>
</html>