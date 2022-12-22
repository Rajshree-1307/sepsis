package JavaBeans;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.springframework.web.multipart.MultipartFile;
   
public class Documents {
	Connection con;
    CallableStatement csmt;
    ResultSet rs;
private String docpath;
private int docId ;
private List<Documents> lstdocs;
private MultipartFile file;

   
public String getDocpath() {
	return docpath;
}

public void setDocpath(String docpath) {
	this.docpath = docpath;
}

public int getDocId() {
	return docId;
}

public void setDocId(int docId) {
	this.docId = docId;
}

public List<Documents> getLstdocs() {
	return lstdocs;
}

public void setLstdocs(List<Documents> lstdocs) {
	this.lstdocs = lstdocs;
}

public MultipartFile getFile() {
	return file;
}

public void setFile(MultipartFile file) {
	this.file = file;
}

public void getId()
{
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call getMaxIdDocuments()}");
       
         csmt.execute();
         rs=csmt.getResultSet();
                    
        boolean auth=false;
        while(rs.next())
        { System.out.println("true");
            auth=true;
            
            docId=rs.getInt("mxid");
            if(docId==0)
            	docId=1001;
            else
            	docId+=1;
              
        }
    }
       
     
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
}

public Documents()
{
	
}
public Documents(ResultSet rs)
{
	try
	{
		docpath=rs.getString("docPath").toString().trim();
		docId=rs.getInt("docId");
		 
	}
	catch (Exception e) {
		// TODO: handle exception
		System.out.println("err="+e.getMessage());
	}
} 
 
public void getDocs()
{
    try
    {
    	DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call getDocs()}");
        lstdocs=new ArrayList<Documents>();
          
         csmt.execute();
         rs=csmt.getResultSet();
                     
        while(rs.next())
        { System.out.println("true");
        lstdocs.add(new Documents(rs)); 
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
        java.util.Date d=new java.util.Date();
        String dt1=(d.getDate()+"/"+(d.getMonth()+1)+"/"+(d.getYear()+1900));
        String tm1=d.getHours()+":"+d.getMinutes();
        csmt=con.prepareCall("{call insertDoc(?,?)}");
        csmt.setInt(1, docId);
      
        csmt.setString(2, docpath); 
       
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
