package JavaBeans;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

public class Readings {
private String testname,specification,userid,result,dt,rid;
private double val;
private List<Readings> lstreadings = new ArrayList<Readings>();
Connection con;
CallableStatement csmt;
ResultSet rs;
 
public String getTestname() {
	return testname;
}
public void setTestname(String testname) {
	this.testname = testname;
}
public String getSpecification() {
	return specification;
}
public void setSpecification(String specification) {
	this.specification = specification;
}
public String getUserid() {
	return userid;
}
public void setUserid(String userid) {
	this.userid = userid;
}
public String getResult() {
	return result;
}
public void setResult(String result) {
	this.result = result;
}
public String getDt() {
	return dt;
}
public void setDt(String dt) {
	this.dt = dt;
}
public String getRid() {
	return rid;
}
public void setRid(String rid) {
	this.rid = rid;
}
public double getVal() {
	return val;
}
public void setVal(double val) {
	this.val = val;
}
public List<Readings> getLstreadings() {
	return lstreadings;
}
public void setLstreadings(List<Readings> lstreadings) {
	this.lstreadings = lstreadings;
}
public Readings()
{
	
}
public Readings(ResultSet rs)
{
	try
	{
	testname=rs.getString("testName").toString().trim();
	specification=rs.getString("specification").toString().trim();
	val=Double.parseDouble(rs.getString("val").toString().trim());
	result=rs.getString("result").toString().trim();
	userid=rs.getString("userid").toString().trim();
	dt=rs.getString("dt").toString().trim();
	rid=rs.getString("rid").toString().trim();
	}
	catch (Exception e) {
		// TODO: handle exception
	}
}
public void getReadings(String userid1)
{
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call getUserReadings(?)}");
       csmt.setString(1, userid1);
         csmt.execute();
         rs=csmt.getResultSet();
                     
        while(rs.next())
        { System.out.println("true");
        lstreadings.add(new Readings(rs));
              
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
        csmt=con.prepareCall("{call insertReadings(?,?,?,?,?,?)}");
        
        csmt.setString(1, testname);
        csmt.setString(2, specification);
        csmt.setDouble(3, val);
        csmt.setString(4, result);
        csmt.setString(5, userid);
        csmt.setString(6, dt);
         
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
