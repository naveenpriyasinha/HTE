<html>

<% 
	response.setHeader("Cache-Control","no-cache"); 	//HTTP 1.1 
	response.setHeader("Pragma","no-cache"); 	//HTTP 1.0 
	response.setHeader ("Expires", "0"); 	 //prevents caching at the proxy server
 %>


<%@ page language="java" import="java.util.ArrayList, java.util.Hashtable,java.io.File,
java.io.PrintStream,
java.sql.*"
%>                                


<head>
  <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
  <title>IFMS COnnectivity Page </title>
 
</head>

<body>

<br>
   <h3> 
    <center> 
     <font color="red"> 
      <b> <u> Test IFMS Connectivity </u> </b> <BR>
      <b> <u> Test IFMS Portlets Folder Creation </u> </b> 
     </font> 
    </center> 
  </h3>
  

<%

Connection lCon = null;
Statement lStmt = null;
ResultSet lRs = null;


/*


try
  {
      //get connection  of IFMS database and create statement--------------------------------------
      out.println("\n---------------------------------------------------\n"); 
      out.println(" Check for Connection : 10.102.21.44:1521:ifms");
      lCon = getConnection_1(out);
        lStmt = lCon.createStatement();
        
        String lStrQuery = " select count (*) cnt from org_ddo_mst ";
        out.println("The query is :- " + lStrQuery);
        
        lRs = lStmt.executeQuery( lStrQuery.toString(  ) );

        if ( lRs.next(  ) )
        {
              out.println("Record found : count is :" + lRs.getString("cnt"));
        }
        
        
        
  }
  catch(Exception e)
  {
    out.print(":Exception in Getting Data  from IFMS table:" + e);
  }
  
  finally
  {
    try
    {
      lRs.close();
      lStmt.close();
      lCon.close();
    }
    catch(Exception e)
    {
      out.print("Coming in Excption of Closing COnnection :" + e);
    }
  }
  


  
  
  
  try
  {
      //get connection  of IFMS database and create statement--------------------------------------
      out.println("\n-----------------------------------------------\n");
      out.println(" Check for Connection : 10.102.21.47:1521:ifms");
        lCon = getConnection_2(out);
        lStmt = lCon.createStatement();
        
        String lStrQuery = " select count (*) cnt from org_ddo_mst ";
        out.println("The query is :- " + lStrQuery);
        
        lRs = lStmt.executeQuery( lStrQuery.toString(  ) );

        if ( lRs.next(  ) )
        {
              out.println("Record found : count is :" + lRs.getString("cnt"));
        }
        
        
        
  }
  catch(Exception e)
  {
    out.print(":Exception in Getting Data  from IFMS table:" + e);
  }
  
  finally
  {
    try
    {
      lRs.close();
      lStmt.close();
      lCon.close();
    }
    catch(Exception e)
    {
      out.print("Coming in Excption of Closing COnnection :" + e);
    }
  }
  

  try
  {
      //get connection  of IWDMS database and create statement--------------------------------------
      out.println("\n-----------------------------------------------\n");
      out.println(" Check for Connection : 10.10.127.71:1522:iwdms1");
        lCon = getConnectionIWDMS_3(out);
        lStmt = lCon.createStatement();
        
        String lStrQuery = " select count (*) cnt from sgva_grant_header ";
        out.println("The query is :- " + lStrQuery);
        
        lRs = lStmt.executeQuery( lStrQuery.toString(  ) );

        if ( lRs.next(  ) )
        {
              out.println("Record found : count is :" + lRs.getString("cnt"));
        }
        
        
        
  }
  catch(Exception e)
  {
    out.print(":Exception in Getting Data  from IWDMS table:" + e);
  }
  
  finally
  {
    try
    {
      lRs.close();
      lStmt.close();
      lCon.close();
    }
    catch(Exception e)
    {
      out.print("Coming in Excption of Closing COnnection :" + e);
    }
  }

  */ 
  out.println("Hello welcome to Test IFMS jsp for context root <BR>");
  String realPath =	request.getSession().getServletContext().getRealPath("");
  String contextpath = request.getContextPath();
  out.println("<BR> here realPath ==="+realPath);
  out.println(" <BR> here contextpath ==="+contextpath);
  String filename = "";
  String filename1 = "";
  File file = null;   
  File file2 = null;
  File folder = null;

  try
  {
	  folder = new File(realPath + "/" +"abc" );
	  out.println("<BR> Goning to create folder abc at real path \n");
	  if( !folder.exists(  ) )
	  {
	      folder.mkdir(  );
	      out.println("<BR> Folder abc created succssfully");
	  }
	  else
	  {
		  out.println(" <BR> Folder abc already exist..................................No need to create");
	  }
	  File oldHtml = new File( folder,"old.html" );
	  oldHtml.createNewFile();
	  File oldpng = new File( folder,"old.png" );
	  oldpng.createNewFile();
	  out.println(" <BR> ............ old html and png files are created..............which will be renamed as xyz or pqr");
	  
	  file  =  new File( folder,"pqr.png" );
	  out.println("<BR> pqr.png file is created in abc folder \n");
	  file2  = new File( folder,"xyz.html" );
	  out.println("<BR>xyz.html file is created in abc folder \n");
	   
	  if( file != null )
	  {
	      filename = file.getName(  );
	      out.println("<BR> PNG filename==="+filename);
	      oldpng.renameTo(file);
	  }
	  if( file2 != null )
	  {
	      filename1 = file2.getName(  );
	      out.println("<BR> HTML filename==="+filename1);
	      oldHtml.renameTo(file2);
	  }
	 
  }
  catch(Exception e)
  {
	  out.println("<BR> Excepetion occur"+e.getMessage());
  }
  finally
  {
	  out.println("<BR> <BR> testing complete");
  }

%>





<%!

public Connection getConnection_1(JspWriter out)
{
        Connection lCon = null;
        
        try
        {
          //Hashtable ht = (Hashtable) NewGrantBO.getConfigurationHash();
          String driver = "oracle.jdbc.driver.OracleDriver" ; //(String)ht.get("driver");
          String  dburl =  "jdbc:oracle:thin:@10.102.21.44:1521:ifms" ; // (String)ht.get("dburl");
          String  dbuser = "ifms_admin" ;//(String)ht.get("dbuser");
          String  password = "sunifms10" ; //(String)ht.get("password");
           
          Class.forName(driver);
          lCon = DriverManager.getConnection(dburl, dbuser, password);
          
          out.println("Got Connection 1");
        }
        catch(Throwable ex)
        {
            try
            {
              out.println(ex);
            }
            catch(Exception e)
            {
            }
            System.out.println(ex);
        }
        return lCon;
        
} // end fun




public Connection getConnection_2(JspWriter out)
{
        Connection lCon = null;
        
        try
        {
           //Hashtable ht = (Hashtable) NewGrantBO.getConfigurationHash();
          String driver = "oracle.jdbc.driver.OracleDriver" ; //(String)ht.get("driver");
          String  dburl =  "jdbc:oracle:thin:@10.102.21.47:1521:ifms" ; // (String)ht.get("dburl");
          String  dbuser = "ifms_admin" ;//(String)ht.get("dbuser");
          String  password = "sunifms10" ; //(String)ht.get("password");
           
          Class.forName(driver);
          lCon = DriverManager.getConnection(dburl, dbuser, password);
          
          out.println(" <BR> <BR> GOT Connection 10.102.21.47:1521:ifms Successfully");
        }
        catch(Throwable ex)
        {
            try
            {
              out.println(" <BR> " + "<BR >" + ex);
            }
            catch(Exception e)
            {
            }
            System.out.println(ex);
        }
        return lCon;
        
} // end fun



public Connection getConnectionIWDMS_3(JspWriter out)
{
        Connection lCon = null;
        
        try
        {
           //Hashtable ht = (Hashtable) NewGrantBO.getConfigurationHash();
          String driver = "oracle.jdbc.driver.OracleDriver" ; //(String)ht.get("driver");
          String  dburl =  "jdbc:oracle:thin:@(DESCRIPTION=(LOAD_BALANCE=on)(ADDRESS=(PROTOCOL=TCP)(HOST=10.10.127.71)(PORT=1522))(ADDRESS=(PROTOCOL=TCP)(HOST=10.10.127.75)(PORT=1522))(CONNECT_DATA=(SERVICE_NAME=iwdms)))" ; // (String)ht.get("dburl");
          String  dbuser = "ifms_admin" ;//(String)ht.get("dbuser");
          String  password = "ifms_tcs" ; //(String)ht.get("password");
           
          Class.forName(driver);
          lCon = DriverManager.getConnection(dburl, dbuser, password);
          
          out.println(" <BR> <BR> GOT Connection 10.10.127.71:1522:iwdms1 Successfully");
        }
        catch(Throwable ex)
        {
            try
            {
              out.println(" <BR> " + "<BR >" + ex);
            }
            catch(Exception e)
            {
            }
            System.out.println(ex);
        }
        return lCon;
        
} // end fun




%>



</body>
</HTML>
