<%
/*
* Filename	  : ExportReport.jsp
* Purpose	    : 
* @VERSION    : 1.0
* @AUTHOR     : Abhishek Aggarwal ,JITESH PANCHAL
* Date        : 14th March 2005
* 
* REV. HISTORY :
*
*-----------------------------------------------------------------------------
*     DATE              AUTHOR               DESCRIPTION
*
*
*-----------------------------------------------------------------------------
*/
%>
<%@ page import="java.io.OutputStream, 
                 java.io.IOException, 
                 org.apache.log4j.Logger" 
         isErrorPage = "false" 
         errorPage = "reporterror.jsp" %>

    <%  
        Logger lLogger= Logger.getLogger( this.getClass() );
        lLogger.info("In ExportReport.jsp");
        OutputStream lOutStream = response.getOutputStream();      
        byte[] allBytesInBlob = (byte[])request.getAttribute( "ExportedReportBytesArray" ); 
        try
        {
            lOutStream.write( allBytesInBlob );
            lOutStream.flush();
        }
        catch( IOException ex )
        {
            lLogger.error( ex.getMessage(), ex );
        }
    %>
