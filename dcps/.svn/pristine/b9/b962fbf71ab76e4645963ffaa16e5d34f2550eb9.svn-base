<%@ page import="java.io.OutputStream, 
                 java.io.IOException, 
                 java.io.FileInputStream,
                 org.apache.log4j.Logger" 
         isErrorPage = "false" %>
    <%  
    OutputStream lOutStream = response.getOutputStream();      
    byte[] allBytesInBlob = (byte[])request.getAttribute( "ExportedReportBytesArray" );  
    try
    {
        lOutStream.write( allBytesInBlob );
        lOutStream.flush();
    }
    catch( IOException ex )
    {
       // lLogger.error( ex.getMessage(), ex );
    }
%>