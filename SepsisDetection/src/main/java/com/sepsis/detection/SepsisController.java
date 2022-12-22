package com.sepsis.detection;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.lowagie.text.DocumentException;

import JavaBeans.Communication;
import JavaBeans.ConvertToPDF;
import JavaBeans.Diseases;
import JavaBeans.Doctor;
import JavaBeans.Documents;
import JavaBeans.ExistingDiseases;
import JavaBeans.Features;
import JavaBeans.HeartReadings;
import JavaBeans.HospAdmin;
import JavaBeans.JavaFuns;
import JavaBeans.Login;
import JavaBeans.Mail;
import JavaBeans.Pass;
import JavaBeans.HospAdmin;
import JavaBeans.RandomString;
import JavaBeans.Readings;
import JavaBeans.UserReg; 
import services.Base64Decoder;
import services.Base64Encoder;
 
import services.GetURL;

@Controller
public class SepsisController {
	@RequestMapping("/home")
	public String myspring()
	{
		return "index.jsp";
	}
	@RequestMapping("/Registration")
	public String Registration()
	{
		return "Registration.jsp";
	}
	@RequestMapping("/LoginForm")
	public String LoginForm()
	{
		return "Login.jsp";
	}
	@RequestMapping("/Cities")
	public String cities()
	{
		return "Cities.jsp";
	}
	@RequestMapping("/process")
	public ModelAndView process(HttpServletRequest request,Communication obj,HttpSession ses)
	{
		 String fileName="NA";
		 
		 try {
		 MultipartFile file=obj.getFile();
		  
		 String filepath=request.getServletContext().getRealPath("/")+"/CommAttach/";
		  
		 System.out.println("path="+filepath);
		 File f=new File(filepath);
		 f.mkdir();
		 filepath+="/"+ses.getAttribute("userid").toString().trim();
		 f=new File(filepath);
		 f.mkdir();
		   
			 int mx=obj.getCommId();
			 fileName=mx+"Reply."+ file.getOriginalFilename().split("\\.")[1];
			 file.transferTo(new File(filepath+"/"+fileName));
		 }
		 catch (Exception e) {
			// TODO: handle exception
		}
		 obj.setAttach2(fileName);
		 obj.updateComm();
		ModelAndView mv=new ModelAndView();
		mv.setViewName("Success.jsp?type=CommReply");
		 return mv;
	}	
	@RequestMapping("ExpertCommReg")
	public String ExpertCommReg(Communication obj,HttpServletRequest request,HttpSession ses)
	{
		 try
		 {
			 String fileName="NA";
			 
			 try {
			 MultipartFile file=obj.getFile();
			  
			 String filepath=request.getServletContext().getRealPath("/")+"/CommAttach/";
			  
			 System.out.println("path="+filepath);
			 File f=new File(filepath);
			 f.mkdir();
			 filepath+="/"+ses.getAttribute("userid").toString().trim();
			 f=new File(filepath);
			 f.mkdir();
			  
				 obj.generateCommId();
				 int mx=obj.getCommId();
				 fileName=mx+"Query."+ file.getOriginalFilename().split("\\.")[1];
				 file.transferTo(new File(filepath+"/"+fileName));
			 }
			 catch (Exception e) {
				// TODO: handle exception
			}
			 obj.setAttach1(fileName);
			 if(obj.registration() )
			 {
				 
				 return "Success.jsp?type=RegComm";
			 }
			 else
			 { 
				 return "Failure.jsp?type=RegComm";
			 }
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 System.out.println("err="+e.getMessage());
			 return("Failure.jsp?type=RegUser");
		}
	}
	@RequestMapping("/ProcessQuery")
	public ModelAndView ProcessQuery(HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("processQuery.jsp");
		mv.addObject("commId",request.getParameter("commId").trim());
		return mv;
	}	
	@RequestMapping("/viewComm")
	public ModelAndView viewComm(HttpSession ses)
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("Inbox.jsp");
		Communication comm=new Communication();
		  
		
		comm.getMessages(ses.getAttribute("userid").toString().trim());
		List<Communication> lst=comm.getLstcomm();
		 
		mv.addObject("lst",lst);
		return mv;
	}
	@RequestMapping("/ExpertComm")
	public ModelAndView ExpertComm()
	{
		ModelAndView mv=new ModelAndView();
	   try {
		UserReg user=new UserReg();
		List<UserReg>lst=new ArrayList<UserReg>();
		user.setUtype("doctor");
		user.getUsers1();
		lst=user.getLstuser();
		mv.addObject("lst",lst);
		mv.setViewName("Communication.jsp");
		System.out.println("lst="+lst.size());
	   }
	   catch (Exception e) {
		// TODO: handle exception
		   System.out.println("errr="+e.getMessage());
	}
	   return mv;
	}
	
	@RequestMapping("/sentComm")
	public ModelAndView sentComm(HttpSession ses)
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("SentItems.jsp");
		JavaBeans.Communication comm=new Communication();
		  
		
		comm.getMessagesSent(ses.getAttribute("userid").toString().trim());
		List<Communication> lst=comm.getLstcomm();
		 
		mv.addObject("lst",lst);
		return mv;
	}
	@RequestMapping("/regDisease")
	public ModelAndView regDisease(HttpSession ses)
	{
		List<String> lst = new ArrayList<String>();
		Diseases obj=new Diseases();
		 obj.getFactors(); 
		 lst=obj.getLstfactors();

		ModelAndView mv = new ModelAndView();

		mv.setViewName("RegDiseases.jsp");
		mv.addObject("lst", lst);

		return mv;
		 
	}
	
	@RequestMapping("/ViewHeartReadings")
	public ModelAndView ViewHeartReadings(HttpSession ses,HttpServletRequest request)
	{
		List<HeartReadings> lst = new ArrayList<HeartReadings>();
		HeartReadings obj=new HeartReadings();
		
		 obj.getReadings(ses.getAttribute("userid").toString().trim());
		 lst=obj.getLstreadings();

		ModelAndView mv = new ModelAndView();

		mv.setViewName("ViewHeartReadings.jsp");
		mv.addObject("lst", lst);
		//mv.addObject("pathUserid",request.getParameter("pathUid").toString().trim());

		return mv;
		 
	}
	@RequestMapping("/sepsisReg")
	public ModelAndView sepsisReg(HttpServletRequest request,HttpSession ses)
	{
		ModelAndView mv=new ModelAndView();
		String str="";
		//System.out.println("docs="+request.getParameter("chkdoc").toString().trim());
		try {
		    Enumeration  e=request.getParameterNames();
		    String  patientUid="";
		    Vector v=new Vector();
		    Vector v1=new Vector();
		    Vector val=new Vector();
		    while(e.hasMoreElements())
		    {
		    String Chknm=(String)e.nextElement();
		      if(Chknm.trim().equals("patientUid"))
		      {
		    	  patientUid=request.getParameter("patientUid").toString().trim();
		      } 
		      else
		      {
		    	  v.addElement(request.getParameter(Chknm.trim()));
		    	  v1.addElement((Chknm.trim()));
		      }
		       
		    }
		   Features obj=new Features();
		    JavaFuns jf=new JavaFuns();
		    if(jf.execute("delete from sepsis_readings where userid='"+patientUid+"'")) {}
		     	for(int i=0;i<v.size();i++)
			      {
		     		 obj.setVal(Double.parseDouble(v.elementAt(i).toString().trim()));
		     		 obj.setFeature(v1.elementAt(i).toString().trim());
		     		 val=jf.getValue("select val,comparison from features where feature='"+v1.elementAt(i).toString().trim()+"'", 2);
		     		 double  val1=Double.parseDouble(val.elementAt(0).toString().trim());
		     		String comparison= (val.elementAt(1).toString().trim());
		     		 System.out.println("values="+v.elementAt(i).toString().trim()+" "+val1);
						
		     		if(comparison.equals("greaterthan"))
					 {
						 if(Double.parseDouble(v.elementAt(i).toString().trim())>val1)
						 {
							 obj.setResult(1);
						 }
						 else
							 obj.setResult(0);
					 }
					 else if(comparison.equals("lessthan"))
					 {
						 if(Double.parseDouble(v.elementAt(i).toString().trim())<val1)
						 {
							 obj.setResult(1);
						 }
						 else
							 obj.setResult(0);
					 }
					 else
					 {
						 if((v.elementAt(i).toString().trim()).equals("1"))
						 {
							 obj.setResult(1);
						 }
						 else
							 obj.setResult(0);
					 }
		     		obj.registration(ses.getAttribute("userid").toString().trim(), patientUid);
			      }
				 
				mv.setViewName("Success.jsp?type=sreadingInserted");
				
				mv.addObject("activity","dataInserted");
		    }
		     
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("err="+e.getMessage());
		}
		return mv;
	}
	@RequestMapping("/generatePDF")
	public ModelAndView generatePDF(HttpSession ses,HttpServletRequest request,HttpServletResponse response) throws DocumentException, IOException {
		ModelAndView mv=new ModelAndView();
		try
		{
		response.setContentType("application/pdf");
		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
		String currentDateTime = dateFormat.format(new Date());
		String headerkey = "Content-Disposition";
		String headervalue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
		response.setHeader(headerkey, headervalue);
		 
		ConvertToPDF generator = new ConvertToPDF();
		 
		generator.generate(response,request.getParameter("userid").toString().trim());
		 
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		mv.setViewName("Success.jsp");
		mv.addObject("activity","pdf");
		return(mv);
	}
	@SessionScope
	@RequestMapping("/RegDocs")
	public ModelAndView RegDocs(JavaBeans.Documents eobj,ServletRequest request,HttpSession ses)
	{
		ModelAndView mv=new ModelAndView();
		 
		 try
		 {MultipartFile file=eobj.getFile();
		 String filepath=request.getServletContext().getRealPath("/")+"/Photos/";
		  
		 System.out.println("path="+filepath);
		 File f=new File(filepath);
		 f.mkdir();
		 f=new File(filepath);
		 f.mkdir();
		 try {
			 eobj.getId();
			 int mx=eobj.getDocId();
			 String fileName=mx+"."+ file.getOriginalFilename().split("\\.")[1];
			 file.transferTo(new File(filepath+"/"+fileName));
			  
			 eobj.setDocpath(fileName);
			  if(eobj.registration() )
			 { 
				mv.setViewName("Success.jsp?type=DocumentReg");
				mv.addObject("activity","DocumentReg");
			 }
			 else
			 { 
				 mv.setViewName("Failure.jsp?type=DocumentReg");
				 mv.addObject("activity","DocumentReg");
			 }
			 } catch (IOException e) {
				 
			 }
		 
			 
		// mv.setViewName("Success.jsp?type=DocumentReg");
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 System.out.println("err="+e.getMessage());
			 mv.setViewName("Failure.jsp?type=DocumentReg");
		}
		 return mv;
	}
	@RequestMapping("/ViewDocs")
	public ModelAndView viewDocs(HttpSession ses,HttpServletRequest request)
	{
		
		List<Documents> lst = new ArrayList<Documents>();
		Documents obj=new Documents();
		  obj.getDocs();
		 lst=obj.getLstdocs();
System.out.println("lstsize="+lst.size());
		ModelAndView mv = new ModelAndView();

		mv.setViewName("viewDocs.jsp");
		mv.addObject("lst", lst); 
		return mv;
		 
	}
	@RequestMapping("/regSepsisReadings")
	public ModelAndView regSepsisReadings(HttpSession ses,HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView();
		 
		 try {
			  
			 // String st=stu.addNewReq();
			 String st="success";
			 Features feature=new Features();
			 feature.getFeatures();
			  mv.addObject("lst",feature.getLstfeature());
			 mv.setViewName("RegSepsisReadings.jsp");
			  mv.addObject("patientUid",request.getParameter("patientUid").toString().trim());
			  
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 mv.setViewName("Failure.jsp");
		} 
		 mv.addObject("activity","RegSepsisReadings");
		 return mv;
		
	}
	
	@RequestMapping("/regHeartReadings1")
	public ModelAndView regHeartReadings1(HttpSession ses,HttpServletRequest request)
	{
		 
		ModelAndView mv = new ModelAndView();

		mv.setViewName("RegHeartReadings1.jsp"); 
		mv.addObject("pathUserid",request.getParameter("pathUid").toString().trim());
		mv.addObject("userid",request.getParameter("userid").toString().trim());

		return mv;
		 
	}
	@RequestMapping("/regReadings")
	public ModelAndView regReadings(HttpSession ses)
	{
		List<Readings> lst = new ArrayList<Readings>();
		Readings obj=new Readings();
		 obj.getReadings(ses.getAttribute("userid").toString().trim());
		 lst=obj.getLstreadings();
		 System.out.println("lst="+lst.size());
		ModelAndView mv = new ModelAndView();

		mv.setViewName("RegReadings.jsp");
		mv.addObject("lst", lst);

		return mv;
		 
	}
	@RequestMapping("/regReadings1")
	public ModelAndView regReadings1(HttpSession ses,HttpServletRequest request)
	{
		List<Readings> lst = new ArrayList<Readings>();
		Readings obj=new Readings();
		 obj.getReadings(request.getParameter("userid").toString().trim());
		 lst=obj.getLstreadings();

		ModelAndView mv = new ModelAndView();

		mv.setViewName("RegReadings1.jsp");
		mv.addObject("userid", request.getParameter("userid").toString().trim());
		mv.addObject("lst", lst);

		return mv;
		 
	}
	@RequestMapping("/SepsisOutput")
	public ModelAndView SepsisOutput(HttpServletRequest request)
	{
		 
		ModelAndView mv = new ModelAndView();
		String result="Sepsis";
		if(request.getParameter("param").toString().trim().equals("0"))
		{
			result="noSepsis";
		}
		mv.setViewName("Success.jsp?userid="+request.getParameter("userid").toString().trim()+"&type="+result);
		

		return mv;
		 
	}
	@SessionScope
	@RequestMapping("/viewPathAdmin")
	public ModelAndView viewPathAdmin(HttpSession ses)
	{
		List<HospAdmin> lst = new ArrayList<HospAdmin>();
		HospAdmin obj=new HospAdmin();
		 obj.getPathAdminDetails(ses.getAttribute("userid").toString().trim());
		 lst=obj.getLstexpert();

		ModelAndView mv = new ModelAndView();

		mv.setViewName("viewPathAdmin.jsp");
		mv.addObject("lst", lst);

		return mv;
		 
	}
	@SessionScope
	@RequestMapping("/viewPathAdminUser")
	public ModelAndView viewPathAdminUser(HttpSession ses)
	{
		List<HospAdmin> lst = new ArrayList<HospAdmin>();
		HospAdmin obj=new HospAdmin();
		 obj.getPathAdminDetails(ses.getAttribute("userid").toString().trim());
		 lst=obj.getLstexpert();

		ModelAndView mv = new ModelAndView();

		mv.setViewName("viewPathAdminUser.jsp");
		mv.addObject("lst", lst);

		return mv;
		 
	}
	
	@SessionScope
	@RequestMapping("/viewDoctor")
	public ModelAndView viewDoctor(HttpSession ses)
	{
		List<Doctor> lst = new ArrayList<Doctor>();
		Doctor obj=new Doctor();
		 obj.getDoctors(ses.getAttribute("userid").toString().trim());
		 lst=obj.getLstexpert();

		ModelAndView mv = new ModelAndView();

		mv.setViewName("viewDoctor.jsp");
		mv.addObject("lst", lst);

		return mv;
		 
	}
	@SessionScope
	@RequestMapping("/viewUsers")
	public ModelAndView viewUsers(HttpSession ses)
	{
		List<UserReg> lst = new ArrayList<UserReg>();
		UserReg obj=new UserReg();
		 obj.getUsers();
		 lst=obj.getLstuser();

		ModelAndView mv = new ModelAndView();

		mv.setViewName("viewUsers.jsp");
		mv.addObject("lst", lst);

		return mv;
		 
	}
	@SessionScope
	@RequestMapping("/viewPatients")
	public ModelAndView viewPatients(HttpSession ses)
	{
		List<UserReg> lst = new ArrayList<UserReg>();
		UserReg obj=new UserReg();
		 obj.getUsersPer(ses.getAttribute("userid").toString().trim());
		 lst=obj.getLstuser();

		ModelAndView mv = new ModelAndView();

		mv.setViewName("viewPatients.jsp");
		mv.addObject("lst", lst);

		return mv;
		 
	}
	@SessionScope
	@RequestMapping("/viewPatients1")
	public ModelAndView viewPatients1(HttpSession ses)
	{
		List<UserReg> lst = new ArrayList<UserReg>();
		UserReg obj=new UserReg();
		 obj.getUsersPerDoc(ses.getAttribute("userid").toString().trim());
		 lst=obj.getLstuser();

		ModelAndView mv = new ModelAndView();

		mv.setViewName("viewPatients.jsp");
		mv.addObject("lst", lst);

		return mv;
		 
	}
	@RequestMapping("/forgot")
	public String forgot()
	{
		return "Forgot.jsp";
	}
	@RequestMapping("/ChangePass")
	public String ChangePass()
	{
		return "ChangePass.jsp";
	}
	@RequestMapping("/WCities")
	public String Wcities()
	{
		return "WCities.jsp";
	}
	@RequestMapping("/PathAdminReg")
	public String PathAdminReg()
	{
		return "RegHospAdmin.jsp";
	}
	@RequestMapping("/DoctorReg")
	public String DoctorReg()
	{
		return "RegDoctor.jsp";
	}
	
	@RequestMapping("/adminHome")
	public String adminHome()
	{
		return "admin.jsp";
	}
	 
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
        session.invalidate();
		return "Logout.jsp";
	}
	@RequestMapping("userHome")
	public String userHome()
	{
		//return "getPredictedDiseases";
		return "user.jsp";
	}
	@RequestMapping("pathAdminHome")
	public String pathAdminHome()
	{
		return "pathAdmin.jsp";
	}
	@RequestMapping("doctorHome")
	public String doctorHome()
	{
		return "doctor.jsp";
	}
	@RequestMapping("/passRecoveryOTPAuth")
	public ModelAndView passRecoveryOTPAuth(UserReg user)
	{
		ModelAndView mv=new ModelAndView();
		try {
			if(user.getSentOTP().equals(user.getOtp()))
			{
				String pass=RandomString.getAlphaNumericString(8);
				user.setPass(pass);
				if(user.updatePass())
				{
					
				}
				
				
			    mv.setViewName("Success.jsp?type=passEmail");
			    
			    Mail mail=new Mail();
			    String msg="Dear "+user.getName()+" \n Your password has been reset to "+pass;
			    System.out.println("pass="+pass);
			    try
			    {
			    	if(mail.sendMail(msg,user.getEmail(), "New password"))
			    	{
			    		
			    	}
			    }
			    catch (Exception e) {
					// TODO: handle exception
				}
			}
			else
			{
				mv.setViewName("Failure.jsp?type=passEmail");
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
	    return mv;
	}
	@RequestMapping("/OTPAuth")
	public ModelAndView OTPAuth(UserReg user,HttpSession ses)
	{
		ModelAndView mv=new ModelAndView();
		try {
			if(user.getSentOTP().equals(user.getOtp()))
			{
				 
				
			    mv.setViewName(ses.getAttribute("utype").toString().trim()+"Home");
			    
			    
			}
			else
			{
				mv.setViewName("Failure.jsp?type=Auth");
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
	    return mv;
	}
	@RequestMapping("/passRecovery")
	public ModelAndView passRecovery(UserReg user)
	{
		ModelAndView mv=new ModelAndView();
		try {
			if(user.useridAuth())
			{
				String otp=RandomString.getAlphaNumericString(4);
				
			    mv.setViewName("ForgotOTP.jsp");
			    mv.addObject("userid",user.getUserid());
			    mv.addObject("otp",otp);
			    mv.addObject("email",user.getEmail());
			   JavaBeans.Mail mail=new Mail();
			    String msg="Dear "+user.getName()+" \n Your one time password is "+otp;
			    System.out.println("otp="+otp);
			    try
			    {
			    	if(mail.sendMail(msg,user.getEmail(), "One Time Password"))
			    	{
			    		
			    	}
			    }
			    catch (Exception e) {
				}
			}
			else
			{
				mv.setViewName("Failure.jsp?type=Auth");
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
	    return mv;
	}
	
	@RequestMapping("/SVMOutputErr")
	public String SVMOutputErr()
	{
		 return "Failure.jsp?type=ReadingsNA";
	}
	@RequestMapping("/ChangePassService")
	public String ChangePassService(Pass eobj,HttpSession ses)
	{
		 
		 try
		 {
			 
			 eobj.setUserid(ses.getAttribute("userid").toString().trim());
			 if(eobj.changePassword())
			 {
				 
				 
				 return "Success.jsp?type=ChangePass";
			 }
			 else
			 { 
				 return "Failure.jsp?type=ChangePass";
			 }
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 System.out.println("err="+e.getMessage());
			 return("Failure.jsp?type=Auth");
		}
		 
	}
	@RequestMapping("/login")
	public String login(HttpServletRequest request)
	{
		Login obj=new Login();
		 try
		 {
			 javax.servlet.http.HttpSession ses=request.getSession(true);
			 
			 if(obj.chkAuthentication(request.getParameter("txtuserid").trim(), request.getParameter("txtpass").trim()))
			 {
				 ses.setAttribute("userid", obj.getUserid());
				 System.out.println("userid="+obj.getUserid());
				 System.out.println("userid="+obj.getuType());
				 System.out.println("userid="+obj.getUserName());
				 ses.setAttribute("utype", obj.getuType());
				 ses.setAttribute("username", obj.getUserName());
				 System.out.println("utype="+obj.getuType());
				 /*if(obj.getuType().equals("user"))
				 {
					 return "getPredictedDiseases";
				 }
				 else*/
				 return obj.getuType()+".jsp";
			 }
			 else
			 { 
				 return "Failure.jsp?type=Auth";
			 }
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 System.out.println("err="+e.getMessage());
			 return("Failure.jsp?type=Auth");
		}
		 
	}
	@RequestMapping("/RegHospAdmin")
	public String RegHospAdmin(HospAdmin eobj)
	{ 
		 try
		 { 
			 if(eobj.Registration())
			 { 
				 return "Success.jsp?type=PathAdmin";
			 }
			 else
			 { 
				 return "Failure.jsp?type=PathAdmin";
			 }
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 System.out.println("err="+e.getMessage());
			 return("Failure.jsp?type=PathAdmin");
		}
		 
	}
	@RequestMapping("/RegDoctor")
	public String RegDoctor(Doctor eobj)
	{
		 
		 try
		 {
			 
			 
			 if(eobj.Registration())
			 {
				 
				 
				 return "Success.jsp?type=Doctor";
			 }
			 else
			 { 
				 return "Failure.jsp?type=Doctor";
			 }
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 System.out.println("err="+e.getMessage());
			 return("Failure.jsp?type=Doctor");
		}
		 
	}
	@RequestMapping("/RegUserDisease")
	public String RegUserDisease(ExistingDiseases eobj)
	{
		 
		 try
		 {
			   
			  
			 if(eobj.registration())
			 {
				 
				 
				 return "Success.jsp?type=RegUserDisease";
			 }
			 else
			 { 
				 return "Failure.jsp?type=RegUserDisease";
			 }
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 System.out.println("err="+e.getMessage());
			 return("Failure.jsp?type=RegUserDisease");
		}
		 
	}
	@RequestMapping("/RegUserHeartReading1")
	public String RegUserHeartReading1(HeartReadings eobj,HttpSession ses )
	{
		 
		 try
		 {
			 Date d=new Date();
			   String dt=d.getDate()+"/"+(d.getMonth()+1)+"/"+(d.getYear()+1900);
			  //eobj.setUserid(ses.getAttribute("userid").toString().trim());
			  eobj.setDt(dt);
			 if(eobj.registration())
			 {
				 
				 
				 return "Success.jsp?type=RegUserHeartReading";
			 }
			 else
			 { 
				 return "Failure.jsp?type=RegUserHeartReading";
			 }
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 System.out.println("err="+e.getMessage());
			 return("Failure.jsp?type=RegUserDisease");
		}
		 
	}
	@RequestMapping("/RegUserHeartReading")
	public String RegUserHeartReading(HeartReadings eobj,HttpSession ses)
	{
		 
		 try
		 {
			 Date d=new Date();
			   String dt=d.getDate()+"/"+(d.getMonth()+1)+"/"+(d.getYear()+1900);
			  eobj.setUserid(ses.getAttribute("userid").toString().trim());
			  eobj.setDt(dt);
			 if(eobj.registration())
			 {
				 
				 
				 return "Success.jsp?type=RegUserHeartReading";
			 }
			 else
			 { 
				 return "Failure.jsp?type=RegUserHeartReading";
			 }
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 System.out.println("err="+e.getMessage());
			 return("Failure.jsp?type=RegUserDisease");
		}
		 
	}
	@RequestMapping("/GrantPer")
	public String GrantPermission(HttpServletRequest request,HttpSession ses)
	{
		 
		 try
		 {
			 Date d=new Date();
			 HospAdmin obj=new HospAdmin();
			 String userid=ses.getAttribute("userid").toString().trim();
			 String unm=ses.getAttribute("username").toString().trim();
			 String pathUserid=request.getParameter("pathUserid").trim();
			 if(obj.grantReadings(userid, unm, pathUserid))
			 { 
				 return "Success.jsp?type=PermissionGranted";
			 }
			 else
			 { 
				 return "Failure.jsp?type=PermissionGranted";
			 }
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 System.out.println("err="+e.getMessage());
			 return("Failure.jsp?type=RegUserDisease");
		}
		 
	}
	@SessionScope
	@RequestMapping("/predictHeartDisease")
	public ModelAndView findPerformance(HttpSession ses,HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView();
		 try
		 {    Base64Encoder encoder=new Base64Encoder();
				 mv.setViewName("gotoPython.jsp");
				 String param=request.getParameter("userid").trim() ;
				 System.out.println("param="+param);
				 param=encoder.encode(param.getBytes());
				 mv.addObject("path",GetURL.getPythonServerURL()+"HeartDiseasePrediction1.py?param="+param);
				 
			 
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 System.out.println("err="+e.getMessage());
			 mv.setViewName("Failure.jsp?type=ProfileReq");
		}
		 return mv;
	}
	@RequestMapping("/RegUserReading")
	public String RegUserReading(Readings eobj,HttpSession ses)
	{
		 
		 try
		 {
			 Date d=new Date();
			   String dt=d.getDate()+"/"+(d.getMonth()+1)+"/"+(d.getYear()+1900);
			  eobj.setUserid(ses.getAttribute("userid").toString().trim());
			  eobj.setDt(dt);
			 if(eobj.registration())
			 {
				 
				 
				 return "Success.jsp?type=RegUserReading";
			 }
			 else
			 { 
				 return "Failure.jsp?type=RegUserReading";
			 }
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 System.out.println("err="+e.getMessage());
			 return("Failure.jsp?type=RegUserDisease");
		}
		 
	}
	@RequestMapping("/RegUser")
	public String RegUser(UserReg eobj)
	{
		 
		 try
		 { 
			 if(eobj.registration())
			 { 
				 
				 return "Success.jsp?type=UserReg";
			 }
			 else
			 { 
				 return "Failure.jsp?type=UserReg";
			 }
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 System.out.println("err="+e.getMessage());
			 return("Failure.jsp?type=UserReg");
		}
		 
	}

 
}
