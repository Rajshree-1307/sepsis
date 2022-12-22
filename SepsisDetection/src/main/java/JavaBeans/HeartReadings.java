package JavaBeans;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
 
import services.Base64Decoder;

public class HeartReadings {
private String  pathUserid,userid,dt,rid;
private double cp,thal,thalach,chol,oldpeak,ecg,exang,slope,ca;
private List<HeartReadings> lstreadings = new ArrayList<HeartReadings>();
Connection con;
CallableStatement csmt;
ResultSet rs;
  
public String getPathUserid() {
	return pathUserid;
}
public void setPathUserid(String pathUserid) {
	this.pathUserid = pathUserid;
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
public String getRid() {
	return rid;
}
public void setRid(String rid) {
	this.rid = rid;
}
public double getCp() {
	return cp;
}
public void setCp(double cp) {
	this.cp = cp;
}
public double getThal() {
	return thal;
}
public void setThal(double thal) {
	this.thal = thal;
}
public double getThalach() {
	return thalach;
}
public void setThalach(double thalach) {
	this.thalach = thalach;
}
public double getChol() {
	return chol;
}
public void setChol(double chol) {
	this.chol = chol;
}
public double getOldpeak() {
	return oldpeak;
}
public void setOldpeak(double oldpeak) {
	this.oldpeak = oldpeak;
}
public double getEcg() {
	return ecg;
}
public void setEcg(double ecg) {
	this.ecg = ecg;
}
public double getExang() {
	return exang;
}
public void setExang(double exang) {
	this.exang = exang;
}
public double getSlope() {
	return slope;
}
public void setSlope(double slope) {
	this.slope = slope;
}
public double getCa() {
	return ca;
}
public void setCa(double ca) {
	this.ca = ca;
}
public List<HeartReadings> getLstreadings() {
	return lstreadings;
}
public void setLstreadings(List<HeartReadings> lstreadings) {
	this.lstreadings = lstreadings;
}
public HeartReadings()
{
	
}
public HeartReadings(ResultSet rs)
{
	try
	{
	ecg=rs.getDouble("restecg");
	thalach=rs.getDouble("thalach");
	thal=rs.getDouble("thal");
	exang=rs.getDouble("exang");
	oldpeak=rs.getDouble("oldpeak");
	slope=rs.getDouble("slope");
	ca=rs.getDouble("ca");
	chol=rs.getDouble("cholesterol");
	cp=rs.getDouble("chestPain");
    userid=rs.getString("userid").toString().trim();
	dt=rs.getString("dt").toString().trim();
	rid=rs.getString("rid").toString().trim();
	}
	catch (Exception e) {
		// TODO: handle exception
		System.out.println("err in const="+e.getMessage());
	}
}
public void getReadings(String userid1)
{
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call getUserHeartReadings(?)}");
       csmt.setString(1, userid1);
         csmt.execute();
         rs=csmt.getResultSet();
        lstreadings=new ArrayList<HeartReadings>();             
        while(rs.next())
        { System.out.println("true");
        lstreadings.add(new HeartReadings(rs));
              
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
    	  
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call insertHeartReadings(?,?,?,?,?,?,?,?,?,?,?,?)}");
        
        csmt.setString(1, userid);
        csmt.setString(2, pathUserid);
        csmt.setDouble(3, ecg);
        csmt.setDouble(4, thalach);
        csmt.setDouble(5, exang);
        csmt.setDouble(6, oldpeak);
        csmt.setDouble(7, slope);
        csmt.setDouble(8, ca);
        csmt.setDouble(9, thal);
        csmt.setString(10, dt);
        csmt.setDouble(11, cp);
        csmt.setDouble(12, chol);
         
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
