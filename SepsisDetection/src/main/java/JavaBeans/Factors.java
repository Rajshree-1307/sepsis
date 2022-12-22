package JavaBeans;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
 

public class Factors {
private String userid;
private String val;
private String factor;
private String id; 
private List<Factors> lstFactors = new ArrayList<Factors>();
Connection con;
CallableStatement csmt;
PreparedStatement psmt;
ResultSet rs;
  
public String getUserid() {
	return userid;
}
public void setUserid(String userid) {
	this.userid = userid;
}
public String getVal() {
	return val;
}
public void setVal(String val) {
	this.val = val;
}
public String getFactor() {
	return factor;
}
public void setFactor(String factor) {
	this.factor = factor;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public List<Factors> getLstFactors() {
	return lstFactors;
}
public void setLstFactors(List<Factors> lstFactors) {
	this.lstFactors = lstFactors;
}
public Factors()
{
	
}
public Factors(ResultSet rs)
{
	try
	{
	factor=rs.getString("factor").toString().trim();
	val=rs.getString("val").toString().trim();
	userid=rs.getString("userid").toString().trim();
	id=rs.getString("id").toString().trim();
	 
	}
	catch (Exception e) {
		// TODO: handle exception
		System.out.println("err="+e.getMessage());
	}
}
public void getFactors(String userid1)
{
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call getExperts()}");
        lstFactors=new ArrayList<Factors>();
         csmt.execute();
         rs=csmt.getResultSet();
                     
        while(rs.next())
        { System.out.println("true");
        lstFactors.add(new Factors(rs));
              
        }
    }
       
     
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
}
public boolean FactorRegistration()
{
    try
    {
    	 
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call insertFactor(?,?,?)}");
        csmt.setString(1, userid);
        csmt.setString(2, factor);
        csmt.setString(3, val);
        
         
         
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
}
