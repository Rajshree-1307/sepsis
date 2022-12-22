package JavaBeans;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

public class Features {
private String feature,comparison,userid,dt,range;
private double val;
private int result;
private List<Features> lstfeature = new ArrayList<Features>();
Connection con;
CallableStatement csmt;
ResultSet rs;
  
public String getRange() {
	return range;
}
public void setRange(String range) {
	this.range = range;
}
public String getFeature() {
	return feature;
}
public void setFeature(String feature) {
	this.feature = feature;
}
public String getComparison() {
	return comparison;
}
public void setComparison(String comparison) {
	this.comparison = comparison;
}
public String getUserid() {
	return userid;
}
public void setUserid(String userid) {
	this.userid = userid;
}
public String getDt() {
	return dt;
}
public void setDt(String dt) {
	this.dt = dt;
}
public double getVal() {
	return val;
}
public void setVal(double val) {
	this.val = val;
}
public int getResult() {
	return result;
}
public void setResult(int result) {
	this.result = result;
}
public List<Features> getLstfeature() {
	return lstfeature;
}
public void setLstfeature(List<Features> lstfeature) {
	this.lstfeature = lstfeature;
}
public Features()
{
	
}
public Features(ResultSet rs)
{
	try
	{
	feature=rs.getString("feature").toString().trim();
	comparison=rs.getString("comparison").toString().trim();
	range=rs.getString("range1").toString().trim();
	val=Double.parseDouble(rs.getString("val").toString().trim());
	result=rs.getInt("result");
	 
	}
	catch (Exception e) {
		// TODO: handle exception
	}
}
public void getFeatures()
{
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call getFeatures()}");
       
         csmt.execute();
         rs=csmt.getResultSet();
                     
        while(rs.next())
        { System.out.println("true");
        lstfeature.add(new Features(rs));
              
        }
    }
       
     
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
}
public boolean registration(String userid,String patientUid)
{
    try
    {
    	  
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call insertSepsisReadings(?,?,?,?,?,?,?)}");
        Date d=new Date();
        dt= d.getDate()+"/"+(d.getMonth()+1)+"/"+(d.getYear()+1900);
        csmt.setString(1, patientUid);
        csmt.setString(2, userid);
        csmt.setString(3, feature);
        csmt.setDouble(4, val);
        csmt.setInt(5, result);
        csmt.setString(6, "NA");
        csmt.setString(7, dt);
        
         
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
