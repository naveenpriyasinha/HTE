<%
 /*
 * Pupose    : to Display the Chart Report
 * FILENAME   : ChartReport.jsp
 * @VERSION   : 1.0
 * @AUTHORS   : Dharmesh Gohil
 * DATE       : 03/02/2006
 *
 *REV. HISTORY :
 *-----------------------------------------------------------------------------
 *     DATE              AUTHOR               DESCRIPTION
 *
 *
 *-----------------------------------------------------------------------------
 */
 %>
<%@include file="rptcommon.jsp"%>

<%@ page import="java.util.Locale,
                 java.util.ResourceBundle,
                 java.util.ArrayList,
                 com.tcs.sgv.common.util.reports.IReportConstants,
                 com.tcs.sgv.common.valuebeans.reports.*,
                 com.tcs.sgv.common.util.reports.*,
                 com.tcs.sgv.common.util.reports.DefaultChartReportData" %>
<jsp:useBean id="rb" class="com.tcs.sgv.common.util.reports.ReportsBean" scope="request"/>
<%
  try {
    Locale userLocale = null;

    if (session.getAttribute("LocaleID") != null && !(session.getAttribute("LocaleID").equals("null"))) {
      userLocale = (Locale)session.getAttribute("LocaleID");
    } else {
      userLocale = request.getLocale();
    }

    ResourceBundle constantBundle = ResourceBundle.getBundle("resources/reports/SGVConstantsReports", userLocale);
	  String uri = request.getContextPath() + constantBundle.getString("INITIALURL");
	  uri += viewmode;
		
	String	  rptTemp = constantBundle.getString("REPORTS_TEMP");
    ResourceBundle localStringsBundle = ResourceBundle.getBundle("resources/reports/SGVLabelsReports", userLocale);
    String chartFilename   = (String)session.getAttribute("chartFilename" + request.getParameter("reportCode"));
    String imageMap        = (String)session.getAttribute("imageMap" + request.getParameter("reportCode"));
    String graphURL        = "";
	String useMap   	   = "#chart"+request.getParameter("reportCode");
	
    graphURL = request.getContextPath() + "/" + rptTemp + "/" + chartFilename;   
    ReportVO   rvoMain    = null;//(ReportVO)session.getAttribute("reportVO" + request.getParameter("reportCode"));
    DefaultChartReportData chartData  = (DefaultChartReportData)session.getAttribute("chartReportData" + request.getParameter("reportCode"));
    rvoMain = chartData.getReportVO();
	//Added by Jeekesh  for template css include
    
    if(rvoMain.getReportTemplate() != null && rvoMain.getReportTemplate().get(IReportConstants.TMPLT_PAGE) != null)
    {
      	StyleVO [] pageStyleList = rvoMain.getReportTemplate().get(IReportConstants.TMPLT_PAGE);
          String lStrCSSName = "";
          for(int i=0;i<pageStyleList.length;i++)
          {
          	if(pageStyleList[i].getStyleId() == IReportConstants.STYLE_TMPLT_PAGE_CSS)
          	{
          		lStrCSSName = pageStyleList[i].getStyleValue();
          		break;
          	}
          }	
      	
      	if (lStrCSSName != null && !lStrCSSName.equals("") )
      	{
      		lStrCSSName = lStrCSSName + ".css" ;
      		%>
				<c:set var="CSSName" scope="session"><%=lStrCSSName%></c:set>
				<link rel="stylesheet" href='<c:url value="/themes/${themename}/${CSSName}"/>' type="text/css"> 
      		<%
      	 }
   }
	//End Added by Jeekesh  for template css include
	
    //  Added By Samir Date:10/03/2006 to set user define "OK" button URL
    StyleVO    style      = rvoMain.getStyle(IReportConstants.REPORT_PAGE_BACK_BTN);
    StyleVO    okBtnStyle = rvoMain.getStyle(IReportConstants.REPORT_PAGE_OK_BTN_URL); 
    String strMainTitle = rvoMain.getReportName();
    
    
%>
    <style type="text/css" media="print"> 
    #btnrow, #header, #toolbar, #nav, #currentApplication, #footer
    {
        display: none;
    }
    </style>        
        <form METHOD=POST>
          <table border=0 width="100%" height="100%" cellpadding=0 cellspacing=0 style="background-color: white">
            <tr><td class=titles style="padding: 5px;text-align:center;"><%=strMainTitle%></td></tr>
            <tr><td>&nbsp</td></tr>
         	 <%         
               String lstrSubHdr="";//rdataMain.getParamaterValueString();
               ArrayList lSubHdrList = rb.getParamaterValueList(rvoMain,localStringsBundle);
               if(rvoMain != null && rvoMain instanceof ReportVO) { %>
              <tr><td class="${labelcss}"><center><table  border="0" cellpadding="0" cellspacing="2">
              <%       
              int lenList = lSubHdrList.size();
              boolean isEven = false;
      
              if (lenList%2 == 0) 
              {
                isEven = true;
              }else{
                  lenList = lenList - 1;
                  isEven = false;
              }
              for (int i=0 ;i<lenList;i++)
              {
                  lstrSubHdr = (String)lSubHdrList.get(i);
                  if(lstrSubHdr!=null && !lstrSubHdr.equals(""))
                  {
                      %>
                      <tr>
                        <td align="left"><font class="Label3"><%=lstrSubHdr%></font></td>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                   <%}
                  ++i;
                  lstrSubHdr = (String)lSubHdrList.get(i);
                  if(lstrSubHdr!=null && !lstrSubHdr.equals(""))
                  {             
                   %> 
                        <td align="left" class="${labelcss}"><%=lstrSubHdr%></td>
                      </tr>      
                      <%-- <tr><td>&nbsp;</td></tr> --%>
                      <%
                  }
              }//End of for 
              if (! isEven)
              {%>
                <tr><td class="${labelcss}"><%=(String)lSubHdrList.get(lenList )%></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>&nbsp;</td></tr>      
              <%}
              %>
          </table></center></td></tr>
          <%}%>
             <tr>
             <%-- added by kruti for drilldown of Dashboard on(20/03/08) --%> 
                  <% 
                  if(imageMap != null)
                	  {%>                	 
    	              <td align="center" valign="middle"><img src="<%= graphURL %>" border=0 usemap="<%= useMap%>" >
    	          		 <%=imageMap %>    	          		 
					<%}
                  else
                  {%>                  
	                  <td align="center" valign="middle"><img src="<%= graphURL %>" border=0 >		          		             
		              </td> 
                  <%}
                  %>
           <%-- End added by kruti for drilldown of Dashboard on(20/03/08) --%> 
           </tr>
    <tr><td align="left"><%@include file="rptfooter.jsp"%></td></tr>
            <tr id="btnrow" align="center">
              <td align="center" style="padding: 5px;">
                <input type="button" Value='<%= localStringsBundle.getString("Report.button.print") %>' name="Print" class="${buttoncss} buttonVisible"  onclick="window.print()">
                <input type="button" Value='<%= localStringsBundle.getString("Report.button.ok") %>' name="Ok" class="${buttoncss} buttonVisible" onclick="getOk()">
              </td>
            </tr>
          </table>
        </form>

      <script language=javascript>
        function getOk() {
          <%
          if (okBtnStyle != null && okBtnStyle.getStyleValue() != null) {
%>
          document.forms[0].action = "<%= okBtnStyle.getStyleValue() %>";
          <%
        } else {
%>
        document.forms[0].action = 
                "<%=uri%>&reportCode=<%= rvoMain.getReportCode() %>&action=parameterPage<%=rb.getHiddenParametersString(rvoMain)%>";
        <%
      }
%>
	<%--
    //document.forms[0].action="/IWAS/FrontServlet?requestType=CommonReportRH&reportId=<%= rvoMain.getReportId() %>&action=parameterPage";
    --%>
    document.forms[0].submit();
  }
</script>

<%
  } catch (Exception e) {
    e.printStackTrace();
  }
%>
