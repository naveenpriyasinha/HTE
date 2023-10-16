<%
/*
* Filename	  : rpttools.jsp
* Purpose	    : For different command buttons.
* @VERSION    : 1.0
* @AUTHOR     : Jitesh Panchal, Abhishek Aggarwal
* Date        : 14th March 2005
* 
* REV. HISTORY :
*
*-----------------------------------------------------------------------------
* DATE        AUTHOR                DESCRIPTION
* 5-Oct-2005  Jitesh Panchal        Language supprt added.
*
*-----------------------------------------------------------------------------
*/
%>
<%@ page import = "java.util.Locale,
                   java.util.ResourceBundle,
                   com.tcs.sgv.common.util.reports.IReportConstants,
                   com.tcs.sgv.common.valuebeans.reports.*,
                   com.tcs.sgv.common.util.reports.*" %>
                   

<%
  Locale rptToolsUserLocale = null;
  if (session.getAttribute("LocaleID") != null && !(session.getAttribute("LocaleID").equals("null")) )
    rptToolsUserLocale = (Locale) session.getAttribute("LocaleID") ;
  else
    rptToolsUserLocale = request.getLocale();

  ResourceBundle rptToolsLocalBundle = ResourceBundle.getBundle("resources/reports/SGVLabelsReports", rptToolsUserLocale);
  //changes made by common team
  ReportVO rvot = (ReportVO)session.getAttribute( "reportVO"+request.getParameter("reportCode") );
  //end common team
  
  int expFormat = rvot.getExportFormat();
  %>
 
  <script>
  function doRptToolsHref(urlStr)
  {
  	document.forms[0].action = urlStr;
  	document.forms[0].submit();
  }
  </script>
  <center>
 
<table  border="1" style="border-width: 1px;" cellpadding=0 cellspacing=0 bordercolor="gray" align="left" width="100%" >
<tr>
<%
	
	
  	if((IReportConstants.PRINT_REPORT&expFormat)==IReportConstants.PRINT_REPORT)
    {
%>
		<td align=left valign=middle style="padding: 0 0 0 2px;" class='rpttooltd' width=5%">
		<table border="0">
			<tr>
				<td>
				<img src="${rimg}/printer.gif" border=0 align="middle" >
				</td>
				<td>
				<a href='<%=request.getAttribute("reportURI")%>&reportCode=<%=rvot.getReportCode()%>&action=PrintReport' target=window.open(newwindow()); ><%=rptToolsLocalBundle.getString("Report.label.print")%>
				</a>
				</td>
			</tr>
		</table>
		</td>
<%
   	}	

  	
	if((expFormat>IReportConstants.PRINT_REPORT && expFormat<IReportConstants.SEND_AS_COMMUNIQUE) || (expFormat>IReportConstants.SEND_AS_COMMUNIQUE+1))
	{
%>		
		<td align=left valign=middle style="padding: 0 0 0 2px;" class='rpttooltd' width="55%">
		     <table border="0">
		     <tr>
					<td>
					<img src="${rimg}/export.gif" border=0 align="middle" style="vertical-align: middle;">
					</td>
					<td>
					<%=rptToolsLocalBundle.getString("Report.label.exportTo")%>
					</td>	
<%

   	if((IReportConstants.EXPORT_TO_EXCEL&expFormat)==IReportConstants.EXPORT_TO_EXCEL)
  	{ 
	  
%>
					<td>
					<img src="${rimg}/excel.gif" border=0 align="middle" style="vertical-align: middle;">
					</td>
					<td>
					<a href='<%=request.getAttribute("reportURI")%>&reportCode=<%=rvot.getReportCode()%>&action=ExportReport&ExportFormat=<%=IReportConstants.EXPORT_TO_EXCEL%>' style="vertical-align: middle;" ><%=rptToolsLocalBundle.getString("Report.label.exportToExcel")%></a>
					</td>
			
<%
    }
	System.out.println("expFormat 33333333333333==>"+expFormat);
   	if((IReportConstants.EXPORT_TO_HTML&expFormat)==IReportConstants.EXPORT_TO_HTML)
  	{
  	  
%>
					<td>
					<img src="${rimg}/html.gif" border=0 style="vertical-align: middle;"/>
					</td>
					<td>
    				<a href='<%=request.getAttribute("reportURI")%>&reportCode=<%=rvot.getReportCode()%>&action=ExportReport&ExportFormat=<%=IReportConstants.EXPORT_TO_HTML%>'  target=window.open(newwindow()); style="vertical-align:middle;"  ><%=rptToolsLocalBundle.getString("Report.label.exportToHtml")%></a>
    				</td>
    		
<% 		  
    }
	
	if((IReportConstants.EXPORT_TO_PDF&expFormat)==IReportConstants.EXPORT_TO_PDF)
  	{
%> 
					<td>
    				<img src="${rimg}/pdf.gif" border=0 align="middle" style="vertical-align: middle;">
    				</td>
    				<td>
    				<a href='<%=request.getAttribute("reportURI")%>&reportCode=<%=rvot.getReportCode()%>&action=ExportReport&ExportFormat=<%=IReportConstants.EXPORT_TO_PDF%>' style="vertical-align: middle;" ><%=rptToolsLocalBundle.getString("Report.label.exportToPDF")%></a>
    				</td>
<% 
    }
    if((IReportConstants.EXPORT_TO_TXT_80C & expFormat)==IReportConstants.EXPORT_TO_TXT_80C)
   	{
%>
					<td>
    				<img src="${rimg}/text.gif" border=0 align="middle" style="vertical-align: middle;">
    				</td>
    				<td>
    				<a href='<%=request.getAttribute("reportURI")%>&reportCode=<%=rvot.getReportCode()%>&action=ExportReport&ExportFormat=<%=IReportConstants.EXPORT_TO_TXT_80C%>' style="vertical-align: middle;"><%=rptToolsLocalBundle.getString("Report.label.exportToTxt")%> (80C)</a>
    				</td>
<%
    } 
   	if((IReportConstants.EXPORT_TO_TXT_132C & expFormat)==IReportConstants.EXPORT_TO_TXT_132C)
   	{
%>	
	    
	    			<td>
    				<img src="${rimg}/text.gif" border=0 align="middle" style="vertical-align: middle;">
    				</td>
    				<td>
    				<a href='<%=request.getAttribute("reportURI")%>&reportCode=<%=rvot.getReportCode()%>&action=ExportReport&ExportFormat=<%=IReportConstants.EXPORT_TO_TXT_132C%>' style="vertical-align: middle;"><%=rptToolsLocalBundle.getString("Report.label.exportToTxt")%> (132C)</a>
    				</td>	
<%
    }
	if((IReportConstants.PIE_CHART_3D&expFormat)==IReportConstants.PIE_CHART_3D ||(IReportConstants.PIE_CHART&expFormat)==IReportConstants.PIE_CHART ||
	 (IReportConstants.BAR_CHART_3D&expFormat)==IReportConstants.BAR_CHART_3D ||(IReportConstants.BAR_CHART&expFormat)==IReportConstants.BAR_CHART ||
	 (IReportConstants.TIME_SERIES_CHART&expFormat)==IReportConstants.TIME_SERIES_CHART)
	{
%>
					<td>
    				<img src="${rimg}/chart.jpg" border=0 align="middle" style="vertical-align: middle;">
    				</td>
    				<td>
    				<a href="#" onclick="doRptToolsHref('<%=request.getAttribute("reportURI")%>&reportCode=<%=rvot.getReportCode()%>&action=generateChartReport&chartRequestSource=link&ExportFormat=<%=expFormat%>')" style="vertical-align: middle;">Chart</a>
    				</td>	
					<%--
					<a href="/IWAS/FrontServlet?requestType=ReportingFramework&reportCode=510&action=generateChartReport" >Chart Report</a>
					--%>  
			
<%
	}
%>	
		</tr>
    	</table>		
    			
	</td>
<%		

	}
  	if((IReportConstants.SEND_AS_COMMUNIQUE&expFormat)==IReportConstants.SEND_AS_COMMUNIQUE)
 	{
  		
%>
		<td align=left valign=middle style="padding: 0 0 0 2px;" class='rpttooltd'  >
		<table border="0" >
			<tr>
				<td>
    			<img src="${rimg}/communique.gif" border=0 align="middle" style="vertical-align: middle;">
    			<%=rptToolsLocalBundle.getString("Report.label.sendascommunique")%>
				</td>
				
				<td>
				<img src="${rimg}/pdf.gif" border=0 align="middle" style="vertical-align: middle;">
				<a href='#' onclick="sendReportAsCommunique('<%=rvot.getReportCode()%>','.pdf')" style="vertical-align: middle;"><%=rptToolsLocalBundle.getString("Report.label.exportToPDF")%></a>
    			</td>
				
				<td>
				<img src="${rimg}/excel.gif" border=0 align="middle" style="vertical-align: middle;">
				<a href='#' onclick="sendReportAsCommunique('<%=rvot.getReportCode()%>','.xls')" style="vertical-align: middle;"><%=rptToolsLocalBundle.getString("Report.label.exportToExcel")%></a>
				</td>
				
			</tr>
		</table>		
		</td>
		
		
<%
 	}
%>
		<%--
		<td width="100%">&nbsp</td>
		
		<td align=left valign=middle>&nbsp;</td>
		<td align=left valign=middle>
		<a href="/IWAS/FrontServlet?requestType=ReportingFramework&reportCode=510&action=generateChartReport" >Chart Report</a>
		</td>
		
		<td>&nbsp;&nbsp;&nbsp;</td>
		--%> 
</tr>

</table>
</center>
