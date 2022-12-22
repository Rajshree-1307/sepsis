package JavaBeans;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

public class LifeStyleFactors {
private String factor,factorDesc,precautionaryMeasures,id,links;
private List<LifeStyleFactors> lsthabits = new ArrayList<LifeStyleFactors>();
Connection con;
CallableStatement csmt;
ResultSet rs;
  
public String getFactor() {
	return factor;
}
public void setFactor(String factor) {
	this.factor = factor;
}
public String getFactorDesc() {
	return factorDesc;
}
public void setFactorDesc(String factorDesc) {
	this.factorDesc = factorDesc;
}
public String getPrecautionaryMeasures() {
	return precautionaryMeasures;
}
public void setPrecautionaryMeasures(String precautionaryMeasures) {
	this.precautionaryMeasures = precautionaryMeasures;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public List<LifeStyleFactors> getLsthabits() {
	return lsthabits;
}
public void setLsthabits(List<LifeStyleFactors> lsthabits) {
	this.lsthabits = lsthabits;
}



public String getLinks() {
	return links;
}
public void setLinks(String links) {
	this.links = links;
}
public LifeStyleFactors()
{
	
}
public LifeStyleFactors(ResultSet rs)
{
	try
	{
	factor=rs.getString("factor").toString().trim();
	factorDesc=rs.getString("factorDesc").toString().trim();
	precautionaryMeasures=rs.getString("precautionary_Measures").toString().trim();
	if(!rs.getString("links").toString().trim().equals("NA"))
		links="<a href='"+rs.getString("links").toString().trim()+"' target='_blank'>Read More...</a>";
	else
		links=rs.getString("links").toString().trim();
	 
	id=rs.getString("id").toString().trim();
	}
	catch (Exception e) {
		// TODO: handle exception
		System.out.println("err="+e.getMessage());
	}
}
public void getLifeStyleFactors()
{
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        PreparedStatement pst=con.prepareStatement("select * from earlylifestylefactors");
        
         pst.execute();
         rs=pst.getResultSet();
                     
        while(rs.next())
        { System.out.println("true");
        lsthabits.add(new LifeStyleFactors(rs));
              
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
        csmt=con.prepareCall("{call insertLifeStyleFactors(?,?,?,?,?)}");
        csmt.setString(1, factor);
        csmt.setString(2, factorDesc);
        csmt.setString(3, precautionaryMeasures);
        csmt.setString(4, links);
        csmt.setString(5, id);
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
