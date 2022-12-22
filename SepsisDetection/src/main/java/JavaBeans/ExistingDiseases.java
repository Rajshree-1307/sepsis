package JavaBeans;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

public class ExistingDiseases {
private String diseaseName,diseaseSts,userid;
private List<ExistingDiseases> lstDisease = new ArrayList<ExistingDiseases>();
Connection con;
CallableStatement csmt;
ResultSet rs;
public String getDiseaseName() {
	return diseaseName;
}

public String getUserid() {
	return userid;
}

public void setUserid(String userid) {
	this.userid = userid;
}

public void setDiseaseName(String diseaseName) {
	this.diseaseName = diseaseName;
}

public String getDiseaseSts() {
	return diseaseSts;
}

public void setDiseaseSts(String diseaseSts) {
	this.diseaseSts = diseaseSts;
}

public List<ExistingDiseases> getLstDisease() {
	return lstDisease;
}

public void setLstDisease(List<ExistingDiseases> lstDisease) {
	this.lstDisease = lstDisease;
}
public ExistingDiseases()
{
	
}
public ExistingDiseases(ResultSet rs)
{
	try
	{
	diseaseName=rs.getString("diseaseName").toString().trim();
	diseaseSts=rs.getString("diseaseSts").toString().trim();
	}
	catch (Exception e) {
		// TODO: handle exception
	}
}
public void getUserExDiseases(String userid1)
{
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call getUserExDiseases(?)}");
       csmt.setString(1, userid1);
         csmt.execute();
         rs=csmt.getResultSet();
                     
        while(rs.next())
        { System.out.println("true");
        lstDisease.add(new ExistingDiseases(rs));
              
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
        csmt=con.prepareCall("{call insertDiseases(?,?,?)}");
        csmt.setString(1, userid);
        csmt.setString(2, diseaseName);
        csmt.setString(3, diseaseSts);
         
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
