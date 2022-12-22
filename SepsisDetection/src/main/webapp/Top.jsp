<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
   <!-- Basic -->
   <meta charset="utf-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <!-- Mobile Metas -->
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <meta name="viewport" content="initial-scale=1, maximum-scale=1">
   <!-- Site Metas -->
   <title>Sepsis Detection</title>
   <meta name="keywords" content="">
   <meta name="description" content="">
   <meta name="author" content="">
   <!-- Site Icons -->
   <link rel="stylesheet" href="css/style-starter.css">
   <link rel="shortcut icon" href="images/fevicon.ico.png" type="image/x-icon" />
   <link rel="apple-touch-icon" href="images/apple-touch-icon.png">
   <!-- Bootstrap CSS -->
   <link rel="stylesheet" href="css/bootstrap.min.css">
   <!-- Site CSS -->
   <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="css/bootstrap.css">
   <!-- Colors CSS -->
   <link rel="stylesheet" href="css/colors.css">
   <!-- ALL VERSION CSS -->
   <link rel="stylesheet" href="css/versions.css">
   <!-- Responsive CSS -->
   <link rel="stylesheet" href="css/responsive.css">
   <!-- Custom CSS -->
   <link rel="stylesheet" href="css/custom.css">
   <!-- Modernizer for Portfolio -->
   <script src="js/modernizer.js"></script>
   <!-- [if lt IE 9] -->
   </head><body>
<%
try
{  response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setDateHeader("Expires", -1);
if(session.getAttribute("userid")==null)
{
	response.sendRedirect("home");
}
%>
   <body class="clinic_version">
      <!-- LOADER -->
      <div id="preloader">
         <img class="preloader" src="images/loaders/heart-loading2.gif" alt="">
      </div>
      <!-- END LOADER -->
      <header>
         <div class="header-top wow fadeIn">
            <div class="container">
             <h1 class="logo">    Sepsis Disease Detection </h1>
               <div class="right-header">
                  <div class="header-info">
                       
                  </div>
               </div>
            </div>
         </div>
         <div class="header-bottom wow fadeIn">
            <div class="container">
               <nav class="main-menu">
                  <div class="navbar-header">
                     <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar"><i class="fa fa-bars" aria-hidden="true"></i></button>
                  </div>
				  
                  <div id="navbar" class="navbar-collapse collapse">
                     <ul class="nav navbar-nav">
                        <li><a class="active" href="<%=session.getAttribute("utype").toString().trim() %>Home">Home</a></li>
                        <%if(session.getAttribute("utype").toString().trim().equals("admin"))
                                	{
                                	%>
                                	 <li class="dropdown-submenu"><a class="dropdown-item dropdown-toggle" href="#">Registrations</a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="PathAdminReg">Hospital Admin Registration</a></li>
                                    <li><a class="dropdown-item" href="DoctorReg">Doctor Registration</a></li>
                                    <li><a class="dropdown-item" href="UploadDoc.jsp">Upload Patient Photos</a></li>
                                </ul>
                            </li>
						  <li class="dropdown-submenu"><a class="dropdown-item dropdown-toggle" href="#">Reports</a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="viewPathAdmin">View Hospital Admins</a></li>
                                    <li><a class="dropdown-item" href="viewDoctor">View Doctors</a></li>
                                </ul>
                            </li>
						 
							 
						<%}   else if(session.getAttribute("utype").toString().trim().equals("pathAdmin"))
                            	{ 
                            	%>
                            	 <%} else if(session.getAttribute("utype").toString().trim().equals("user"))
                            	{
                            	%>
                              <li class="nav-item active"><a class="nav-link" href="viewPathAdminUser">Manage Permissions</a></li>
                            	
					  <li class="dropdown-submenu"><a class="dropdown-item dropdown-toggle" href="#">Manage Readings</a>
                                <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="regReadings">Register Blood Test Readings</a></li>
                                 
                                 
                                 
                                </ul>
                            </li>
                            	  
                            	<%} %>
                            	  <li class="nav-item "><a class="nav-link" href="ChangePass">Change Password</a></li>
                            	  <li class="nav-item "><a class="nav-link" href="logout">Logout</a></li>
					 
                            	 
                        </ul>
                  </div>
               </nav>
               
            </div>
         </div>
      </header>
      <div id="home"   style="background-image:url('images/adminbanner.png');background-size:cover; height: 400px">
         <div class="container">
            <div class="row">
               <div class="col-md-12 col-sm-12">
                  <div class="text-contant">
                     <h2>
                        <span class="center"><span class="icon"><img src="images/icon-logo.png" alt="#" /></span></span>
                        <p style="color:white;align:right;float:right;">  Logged in as <%=session.getAttribute("username").toString() %>( <%=session.getAttribute("utype").toString() %>)</p>
         
                        <span class="wrap"></span>
                        
                     </h2>
                  </div>
               </div>
            </div>
            <!-- end row -->
         </div>
         <!-- end container -->
      </div>
      <!-- end section -->
 

  
	  
 
   

 
    <!--// end-smoth-scrolling -->
    <%}catch(Exception ex)
{
    	System.out.println("err="+ex.getMessage());
    	 
}%>
      <!-- end copyrights -->
      <a href="#home" data-scroll class="dmtop global-radius"><i class="fa fa-angle-up"></i></a>
      <!-- all js files -->
      <script src="js/all.js"></script>
      <!-- all plugins -->
      <script src="js/custom.js"></script>
      <!-- map -->
     <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCNUPWkb4Cjd7Wxo-T4uoUldFjoiUA1fJc&callback=myMap"></script>
   </body>
</html>