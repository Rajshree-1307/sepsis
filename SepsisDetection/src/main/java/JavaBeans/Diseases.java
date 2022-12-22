package JavaBeans;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

public class Diseases {
private String diseaseName,diseaseDesc,dependentDiseases,factor,highRiskDiseases;
private double workOutHrs;
Connection con;
CallableStatement csmt;
ResultSet rs;

private List<String> lstfactors = new ArrayList<String>();
public List<String> getLstfactors() {
	return lstfactors;
}

public void setLstfactors(List<String> lstfactors) {
	this.lstfactors = lstfactors;
}
private List<Diseases> lstDisease = new ArrayList<Diseases>();



public String getDiseaseDesc() {
	return diseaseDesc;
}

public void setDiseaseDesc(String diseaseDesc) {
	this.diseaseDesc = diseaseDesc;
}

public String getDependentDiseases() {
	return dependentDiseases;
}

public void setDependentDiseases(String dependentDiseases) {
	this.dependentDiseases = dependentDiseases;
}

public String getFactor() {
	return factor;
}

public void setFactor(String factor) {
	this.factor = factor;
}

public String getHighRiskDiseases() {
	return highRiskDiseases;
}

public void setHighRiskDiseases(String highRiskDiseases) {
	this.highRiskDiseases = highRiskDiseases;
}

public double getWorkOutHrs() {
	return workOutHrs;
}

public void setWorkOutHrs(double workOutHrs) {
	this.workOutHrs = workOutHrs;
}

public String getDiseaseName() {
	return diseaseName;
}
 

public void setDiseaseName(String diseaseName) {
	this.diseaseName = diseaseName;
}
 
public List<Diseases> getLstDisease() {
	return lstDisease;
}

public void setLstDisease(List<Diseases> lstDisease) {
	this.lstDisease = lstDisease;
}
public Diseases()
{
	
}
public Diseases(ResultSet rs)
{
	try
	{
	diseaseName=rs.getString("diseaseName").toString().trim();
	diseaseDesc=rs.getString("diseaseDesc").toString().trim();
	dependentDiseases=rs.getString("dependentDiseses").toString().trim();
	highRiskDiseases=rs.getString("high_risk_diseases").toString().trim();
	factor=rs.getString("factor").toString().trim();
	workOutHrs=rs.getDouble("preventiveWorkOut");
	}
	catch (Exception e) {
		// TODO: handle exception
	}
}
public void getDataSet()
{
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        PreparedStatement pst=con.prepareStatement("select * from lifestylediseases order by lid desc");
      
         pst.execute();
         rs=pst.getResultSet();
                     
        while(rs.next())
        { System.out.println("true");
        lstDisease.add(new Diseases(rs));
              
        }
    }
       
     
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
}
public void getFactors()
{
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        PreparedStatement pst=con.prepareStatement("select factor from earlylifestylefactors");
      
        pst.execute();
         rs=pst.getResultSet();
                     
        while(rs.next())
        { System.out.println("true");
        lstfactors.add(rs.getString("factor").trim());
              
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
        csmt=con.prepareCall("{call insertDiseasesDataSet(?,?,?,?,?,?)}");
        csmt.setString(1, diseaseName);
        csmt.setString(2, diseaseDesc);
        csmt.setString(3, dependentDiseases);
        csmt.setString(4, highRiskDiseases);
        csmt.setDouble(5, workOutHrs);
        csmt.setString(6, factor);
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
