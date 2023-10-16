<!--
* Filename	  : ExportReport.jsp
* Purpose	    : 
* @VERSION    : 1.0
* @AUTHOR     : Pravin Rajput
* Date        : 02th June 2007
* 
* REV. HISTORY :
*
*-----------------------------------------------------------------------------
*     DATE              AUTHOR               DESCRIPTION
*
*
*-----------------------------------------------------------------------------
**--><%@ page import="java.io.OutputStream, 
                 java.io.IOException, 
                 org.apache.log4j.Logger" 
         isErrorPage = "false" 
         errorPage = "reporterror.jsp"%>

<%@page import="com.tcs.sgv.core.valueobject.ResultObject"%>
<%@page import="java.util.Map"%>
<html>
    <head>
        <title>
            View Attachment
        </title>
    </head>
    <body>
    <%  
        Logger lLogger= Logger.getLogger( this.getClass() );
        lLogger.info("Inasaasasasas ExportReport.jsp");
        OutputStream lOutStream = response.getOutputStream();
        
        byte[] allBytesInBlob = new byte[0];
		ResultObject result=(ResultObject)request.getAttribute("result");
		System.out.println("result"+result);
		if(result !=  null)
		{
			Map resultMap=(Map)result.getResultValue();
			allBytesInBlob = (byte[]) resultMap.get("buteArray");
		}		
        
        try
        {
            lOutStream.write( allBytesInBlob );
            lOutStream.flush();
        }
        catch( IOException ex )
        {
            lLogger.error( ex.getMessage(), ex );
        }
        /* Added by Divyesh Jariwala on 04-JUN-09 to solve following error coming on console
         * java.lang.IllegalStateException: getOutputStream() has already been called for this response
         */
        out.clear(); 
        out = pageContext.pushBody();
        /*Ended by Divyesh Jariwala on 04-JUN-09 
        */
    %>
    </body>
</html>