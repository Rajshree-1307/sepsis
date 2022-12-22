package JavaBeans;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

public class PredictedDiseases {
private String diseaseName,userid,diseaseDesc,dependentDiseases,factor,highRiskDiseases;
private double workOutHrs;
private List<PredictedDiseases> lstDisease = new ArrayList<PredictedDiseases>();
Connection con;
CallableStatement csmt;
ResultSet rs;
public String getDiseaseName() {
	return diseaseName;
}

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

public List<PredictedDiseases> getLstDisease() {
	return lstDisease;
}

public void setLstDisease(List<PredictedDiseases> lstDisease) {
	this.lstDisease = lstDisease;
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
  
public PredictedDiseases()
{
	
}
public PredictedDiseases(ResultSet rs)
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

public PredictedDiseases(ResultSet rs,String flg)
{
	try
	{
	diseaseName=rs.getString("diseaseName").toString().trim();
 
	}
	catch (Exception e) {
		// TODO: handle exception
	}
}
public void getPredictedDiseases(String userid1)
{
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call getPrediction1(?)}");
       csmt.setString(1, userid1);
         csmt.execute();
         rs=csmt.getResultSet();
                     
        while(rs.next())
        { System.out.println("true");
        lstDisease.add(new PredictedDiseases(rs));
              
        }
    }
       
     
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
}
public void getPredictedDiseases1(String userid1)
{
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call getPrediction2(?)}");
       csmt.setString(1, userid1);
         csmt.execute();
         rs=csmt.getResultSet();
                     
        while(rs.next())
        { System.out.println("true");
        lstDisease.add(new PredictedDiseases(rs,"highRisk"));
              
        }
    }
       
     
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
}
 
}
