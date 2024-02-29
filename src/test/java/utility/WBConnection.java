package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class WBConnection 
{
	public static Connection con = null;
	public static Statement stm = null;
	public static ResultSet rs = null;
	
	public static Object[][] makeWBConnection(String wbname,String sheetname) throws Exception
	  {
		
		Object data[][] = {}; 
		
		Class.forName("com.googlecode.sqlsheet.Driver"); // register driver
	  	String wbpath = System.getProperty("user.dir")+"\\src\\test\\java\\excel\\"+wbname+".xlsx";
	  	con = DriverManager.getConnection("jdbc:xls:file:"+wbpath);
	  	stm = con.createStatement();
	  	rs = stm.executeQuery("select * from "+sheetname);
	  	
	  	if(rs != null)
		   { 
			   ResultSetMetaData rsmt=rs.getMetaData();
			   int columncount=rsmt.getColumnCount();

				rs.last(); // place record pointer on last record
				int rowcount=rs.getRow(); // fetch position of last record

				  //System.out.println(columncount+" , "+rowcount);
				rs.beforeFirst(); // reset

				data = new Object[rowcount][columncount]; //-> size of array 
				  			
				  for(int rowNum = 1 ; rowNum <= rowcount ; rowNum++)
				     { 
				  				
				  for(int colNum=1 ; colNum<= columncount; colNum++)
				        {
				    rs.absolute(rowNum); // point to row  
				  	Object data1= rs.getObject(colNum); // getting values from excel by col index
				  	
				  	data[rowNum-1][colNum-1]= data1 ; //adding table data in  array , array starts from 0
				  		}
				  			}
                }
return data;
}
	
}
