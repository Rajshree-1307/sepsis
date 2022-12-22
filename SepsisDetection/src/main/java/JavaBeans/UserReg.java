package JavaBeans;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
 
 
public class UserReg {
	Connection con;
    CallableStatement csmt;
    ResultSet rs;
    private String name,mobile,utype,email,city,address, prof,gender, state, userid,pass,dob ,sentOTP,otp;
     private List<UserReg> lstuser;
     private double weight,height;
     
     
public String getUtype() {
		return utype;
	}
	public void setUtype(String utype) {
		this.utype = utype;
	}
public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
public List<UserReg> getLstuser() {
		return lstuser;
	}
	public void setLstuser(List<UserReg> lstuser) {
		this.lstuser = lstuser;
	}
public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getProf() {
		return prof;
	}
	public void setProf(String prof) {
		this.prof = prof;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getSentOTP() {
		return sentOTP;
	}
	public void setSentOTP(String sentOTP) {
		this.sentOTP = sentOTP;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
public boolean useridAuth()
{
	boolean flag=false;
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call useridAuth(?)}");
         csmt.setString(1, userid);
         
         csmt.execute();
         rs=csmt.getResultSet();
                     
        while(rs.next())
        { System.out.println("true");
        email=rs.getString("email").trim();
        name=rs.getString("username").trim();
        flag=true;
              
        }
    }
       
     
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
    return flag;
}
public boolean updatePass()
{
    try
    { 
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call updatePassword(?,?)}");
        csmt.setString(1, userid);
        csmt.setString(2, pass);
        
         int n=csmt.executeUpdate();
         
                    
        
        if(n>0)
        {
            try{con.close();}catch(Exception ex){}
            System.out.println("true");
            return true;
        }
        else
            return false;

        }
       
     
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
        return false;
    }
}
	/*public void getId()
	    {
	        try
	        {
	             DBConnector obj=new  DBConnector();
	            con=obj.connect();
	            csmt=con.prepareCall("{call getMaxIdUsers()}");
	           
	             csmt.execute();
	             rs=csmt.getResultSet();
	                        
	            boolean auth=false;
	            while(rs.next())
	            { System.out.println("true");
	                auth=true;
	                
	                mxid=rs.getInt("mxid");
	                  
	            }
	        }
	           
	         
	        catch(Exception ex)
	        {
	            System.out.println("err="+ex.getMessage());
	             
	        }
	    }*/
	public UserReg()
	{
		
	}
	public UserReg(ResultSet rs)
	{
		try
		{
		name=rs.getString("username").toString().trim();
		userid=rs.getString("userid").toString().trim();
		state=rs.getString("state").toString().trim();
		city=rs.getString("city").toString().trim();
		email=rs.getString("email").toString().trim();
		mobile=rs.getString("mobile").toString().trim();
		gender=rs.getString("gender").toString().trim();
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("err="+e.getMessage());
		}
	}
	public void getUsersPer(String userid1)
	{
	    try
	    {
	         DBConnector obj=new  DBConnector();
	        con=obj.connect();
	        csmt=con.prepareCall("{call getUsersPer(?)}");
	        csmt.setString(1, userid1);
	        lstuser=new ArrayList<UserReg>();
	         csmt.execute();
	         rs=csmt.getResultSet();
	                     
	        while(rs.next())
	        { System.out.println("true");
	        lstuser.add(new UserReg(rs));
	              
	        }
	    }
	       
	     
	    catch(Exception ex)
	    {
	        System.out.println("err="+ex.getMessage());
	         
	    }
	}
	public void getUsersPerDoc(String userid1)
	{
	    try
	    {
	         DBConnector obj=new  DBConnector();
	        con=obj.connect();
	        csmt=con.prepareCall("{call getUsersPerDoc(?)}");
	        csmt.setString(1, userid1);
	        lstuser=new ArrayList<UserReg>();
	         csmt.execute();
	         rs=csmt.getResultSet();
	                     
	        while(rs.next())
	        { System.out.println("true");
	        lstuser.add(new UserReg(rs));
	              
	        }
	    }
	       
	     
	    catch(Exception ex)
	    {
	        System.out.println("err="+ex.getMessage());
	         
	    }
	}
	public void getUsers()
	{
	    try
	    {
	         DBConnector obj=new  DBConnector();
	        con=obj.connect();
	        csmt=con.prepareCall("{call getUsers()}");
	        lstuser=new ArrayList<UserReg>();
	         csmt.execute();
	         rs=csmt.getResultSet();
	                     
	        while(rs.next())
	        { System.out.println("true");
	        lstuser.add(new UserReg(rs));
	              
	        }
	    }
	       
	     
	    catch(Exception ex)
	    {
	        System.out.println("err="+ex.getMessage());
	         
	    }
	}
	public void getUsers1()
	{
	    try
	    {
	         DBConnector obj=new  DBConnector();
	        con=obj.connect();
	        csmt=con.prepareCall("{call getUsers1(?)}");
	        csmt.setString(1, utype);
	         
	        lstuser=new ArrayList<UserReg>();
	         
	         csmt.execute();
	         rs=csmt.getResultSet();
	                     
	        while(rs.next())
	        { System.out.println("true");
	        lstuser.add(new UserReg(rs));
	              
	        }
	    }
	       
	     
	    catch(Exception ex)
	    {
	        System.out.println("err="+ex.getMessage());
	         
	    }
	}
	public boolean registration()
	    {
	        try
	        {
	        	String bodycond="NA";
	             DBConnector obj=new  DBConnector();
	            con=obj.connect();
	            csmt=con.prepareCall("{call insertUser(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
	            csmt.setString(1, userid);
	            csmt.setString(2, pass);
	            csmt.setString(3, name);
	            csmt.setString(4, mobile);
	            csmt.setString(5, email);
	         
	            csmt.setString(6, prof);
	            csmt.setString(7, state);
	            csmt.setString(8, city); 
	            csmt.setString(9, address); 
	            csmt.setString(10, gender);
	            csmt.setString(11, dob);
	            csmt.setDouble(12, weight);
	            csmt.setDouble(13, height); 
	            
	             int n=csmt.executeUpdate();
	             
	                        
	            
	            if(n>0)
	            {
	            	  
	                return true;
	            }
	            else
	                return false;

	            }
	           
	         
	        catch(Exception ex)
	        {
	            System.out.println("err="+ex.getMessage());
	            return false;
	        }
	    }

}
