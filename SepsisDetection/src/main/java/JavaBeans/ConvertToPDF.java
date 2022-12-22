package JavaBeans;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletResponse;
 
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
public class ConvertToPDF {
	public void generate(HttpServletResponse response,String userid) throws DocumentException, IOException {

		// Creating the Object of Document
		Document document = new Document(PageSize.A4);
		double bilirubin,Creatinine,Platelet=0;
		// Getting instance of PdfWriter
		PdfWriter.getInstance(document, response.getOutputStream());

		// Opening the created document to modify it
		document.open();

		// Creating font
		// Setting font style and size
		Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTiltle.setSize(20);

		// Creating paragraph
		Paragraph paragraph = new Paragraph("Sepsis Test Report", fontTiltle);

		// Aligning the paragraph in document
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);

		// Adding the created paragraph in document
		document.add(paragraph);

		PdfPTable table1 = new PdfPTable(2);
		table1.setWidthPercentage(100f);
		table1.setWidths(new int[] { 1, 5 });
		table1.setSpacingBefore(5);
		PdfPCell cell = new PdfPCell();

		// Setting the background color and padding
		cell.setBackgroundColor(CMYKColor.WHITE);
		cell.setPadding(5);

		// Creating font
		// Setting font style and size
		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setColor(CMYKColor.BLACK);
		JavaFuns jf=new JavaFuns();
		Vector vp=jf.getValue("select userName,mobile,email,addr,dob,concat(weight,' kg'),concat(height,' inches') as height from userdetails where userid='"+userid.trim()+"'" , 7);
		String qr="select val from sepsis_readings where feature='Platelet count' and userid='"+userid.trim()+"'";
		String qr1="select val from sepsis_readings where feature='total bilirubin' and userid='"+userid.trim()+"'";
		String qr2="select val from sepsis_readings where feature='Creatinine' and userid='"+userid.trim()+"'";
		Vector v12=jf.getValue(qr,1);
		Platelet=Double.parseDouble(v12.elementAt(0).toString().trim());
		 v12=jf.getValue(qr1,1);
		bilirubin=Double.parseDouble(v12.elementAt(0).toString().trim());
		 v12=jf.getValue(qr2,1);
		 Creatinine=Double.parseDouble(v12.elementAt(0).toString().trim());
		int score=0;
		if(bilirubin<1.2 && Platelet>150000 && Creatinine<1.2)
		{
			score=0;
		}
		else if((bilirubin>1.2 && bilirubin<1.9) && Platelet<150000 && (Creatinine>1.2 && Creatinine<1.9))
		{
			score=1;
		}
		else if((bilirubin>2 && bilirubin<5.9) && Platelet<100000 && (Creatinine>2 && Creatinine<3.4))
		{
			score=2;
		}
		else if((bilirubin>6.0 && bilirubin<11.9) && Platelet<=50000 && (Creatinine>3.5 && Creatinine<4.9))
		{
			score=3;
		}
		else if((bilirubin>12.0) && Platelet<20000 && (Creatinine>5.0))
		{
			score=4;
		}
		System.out.println("score="+score+" "+bilirubin+" "+Creatinine+" "+Platelet);
		// Adding headings in the created table cell/ header
		// Adding Cell to table
		cell.setPhrase(new Phrase("Name ", font));
		table1.addCell(cell);
		cell.setPhrase(new Phrase(vp.elementAt(0).toString().trim(), font));
		table1.addCell(cell);
		cell.setPhrase(new Phrase("Mobile ", font));
		table1.addCell(cell);
		cell.setPhrase(new Phrase(vp.elementAt(1).toString().trim(), font));
		table1.addCell(cell);
		cell.setPhrase(new Phrase("Email ", font));
		table1.addCell(cell);
		cell.setPhrase(new Phrase(vp.elementAt(2).toString().trim(), font));
		table1.addCell(cell);
		cell.setPhrase(new Phrase("Address ", font));
		table1.addCell(cell);
		cell.setPhrase(new Phrase(vp.elementAt(3).toString().trim(), font));
		table1.addCell(cell);
		cell.setPhrase(new Phrase("DOB ", font));
		table1.addCell(cell);
		cell.setPhrase(new Phrase(vp.elementAt(4).toString().trim(), font));
		table1.addCell(cell);
		cell.setPhrase(new Phrase("Weight ", font));
		table1.addCell(cell);
		cell.setPhrase(new Phrase(vp.elementAt(5).toString().trim(), font));
		table1.addCell(cell);
		cell.setPhrase(new Phrase("Height", font));
		table1.addCell(cell);
		cell.setPhrase(new Phrase(vp.elementAt(6).toString().trim(), font));
		table1.addCell(cell);
		cell.setPhrase(new Phrase("SOFA Score ", font));
		table1.addCell(cell);
		cell.setPhrase(new Phrase(String.valueOf(score), font));
		table1.addCell(cell);
		
		
		
		
		
		// Creating a table of 3 columns
		PdfPTable table = new PdfPTable(3);

		// Setting width of table, its columns and spacing
		table.setWidthPercentage(100f);
		table.setWidths(new int[] { 3, 3,3 });
		table.setSpacingBefore(5);

		// Create Table Cells for table header
		  cell = new PdfPCell();

		// Setting the background color and padding
		cell.setBackgroundColor(CMYKColor.orange);
		cell.setPadding(5);

		// Creating font
		// Setting font style and size
		  font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setColor(CMYKColor.WHITE);

		// Adding headings in the created table cell/ header
		// Adding Cell to table
		cell.setPhrase(new Phrase("Features", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Reading", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Normal Range", font));
		table.addCell(cell);
		//cell.setPhrase(new Phrase("Values", font));
		//table.addCell(cell);
		 
		Vector v=jf.getValue("select feature,val,result,prediction from sepsis_readings where userid='"+userid.trim()+"'" , 4);
		// Iterating over the list of students
		for (int i=0;i<v.size();i=i+4) {
			// Adding student id
			
			if(i==0)
			{
			cell.setPhrase(new Phrase("Result ", font));
			table1.addCell(cell);
			if(v.elementAt(i+3).toString().trim().equals("0"))
			{
			cell.setPhrase(new Phrase("Sepsis Not Detected", font));
			}
			else
				cell.setPhrase(new Phrase("Sepsis Detected", font));
			table1.addCell(cell);
			document.add(table1);
			}
			PdfPCell cell1 = new PdfPCell();
			Vector v1=jf.getValue("select comparison,range1 from features where feature='"+v.elementAt(i).toString().trim()+"'", 2);
			//table.addCell(String.valueOf(v.elementAt(i+1).toString().trim()));
			cell1.setPhrase(new Phrase(String.valueOf(v.elementAt(i).toString().trim())));
			if(v.elementAt(i+2).toString().trim().equals("1"))
			{
			cell1.setBackgroundColor(CMYKColor.CYAN);
			}
			table.addCell(cell1);
			
			cell1 = new PdfPCell();
			if(v1.elementAt(0).toString().trim().equals("equals"))
			{
				if(v.elementAt(i+1).toString().trim().equals("1"))
				{
					cell1.setPhrase(new Phrase("Yes"));	
				}
				else
				{
					cell1.setPhrase(new Phrase("No"));	
				}
					
			}
			else
				cell1.setPhrase(new Phrase(String.valueOf(v.elementAt(i+1).toString().trim())));
			
			if(v.elementAt(i+2).toString().trim().equals("1"))
			{
			cell1.setBackgroundColor(CMYKColor.CYAN);
			}
			table.addCell(cell1);
			cell1 = new PdfPCell();
			cell1.setPhrase(new Phrase(String.valueOf(v1.elementAt(1).toString().trim())));
			if(v.elementAt(i+2).toString().trim().equals("1"))
			{
			cell1.setBackgroundColor(CMYKColor.CYAN);
			}
			table.addCell(cell1);
		}
		// Adding the created table to document
		document.add(table);

		// Closing the document
		document.close();

	}
}
