<%
/*
* Filename	  : rptheader.jsp
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

<%@ page import = "java.util.Locale,
                   java.util.ResourceBundle,
                   com.tcs.sgv.common.valuebeans.reports.*,
                   com.tcs.sgv.common.util.reports.*" %>
<%
  Locale rptHeaderUserLocale = null;
  if (session.getAttribute("LocaleID") != null && !(session.getAttribute("LocaleID").equals("null")) )
    rptHeaderUserLocale = (Locale) session.getAttribute("LocaleID") ;
  else
    rptHeaderUserLocale = request.getLocale();

  ResourceBundle rptHeaderLocalBundle = ResourceBundle.getBundle("resources/reports/SGVLabelsReports", rptHeaderUserLocale) ;
  
  StringBuffer lsbLeftHeader=new StringBuffer("");
  StringBuffer lsbCentreHeader=new StringBuffer("");
  StringBuffer lsbRightHeader=new StringBuffer("");
  
  //changes made by common team
   ReportData rptHeaderData = (ReportData) session.getAttribute("reportData" + request.getParameter("reportCode"));
   Object objSubRptCodeHeader = request.getAttribute("subReportCode");
   String subRptCodeHeader = (objSubRptCodeHeader!=null ? objSubRptCodeHeader.toString():null);
   ReportVO reportVO = null;
   if(subRptCodeHeader != null)
   {
	   ReportData rdataHeader = (ReportData) rptHeaderData.getSubReportData("reportData"+subRptCodeHeader);
	   reportVO = rdataHeader.getReportVO();
	   request.removeAttribute("subReportCode");
   }
   else
   {
	   reportVO = (ReportVO)session.getAttribute("reportVO"+request.getParameter("reportCode"));
   }
  //end changes
  boolean showHdr = true;
  if(reportVO != null)
  {
	  StyleVO styleNoHdr = reportVO.getStyle(IReportConstants.NO_HEADER);
	  showHdr = (styleNoHdr != null && styleNoHdr.getStyleValue() != null && 
			             styleNoHdr.getStyleValue().equalsIgnoreCase("yes") ? false : true );
  }
  
  if(showHdr)
  {
	  ReportAttributeVO[] reportAttributes=( ReportAttributeVO[])reportVO.getReportAttributes();
	  
	  for(int li=0; li<reportAttributes.length; li++)
	  {
	    if(reportAttributes[li].getLocation()==IReportConstants.LOCATION_HEADER)
	    {
	      String lstrTmp="";
	      if(reportAttributes[li].getAttributeType()==IReportConstants.ATTRIB_CURRENT_DATE)
	      {
	    	  lstrTmp=rptHeaderLocalBundle.getString("Report.label.date")+":"+ ((rptHeaderData != null)?rptHeaderData.getFormattedDate():"");
	      }
	      else if(reportAttributes[li].getAttributeType()==IReportConstants.ATTRIB_CURRENT_TIME)
	      {
	    	  lstrTmp = (rptHeaderData != null)?rptHeaderData.getFormattedTime():"";
	      }
	      else if( reportAttributes[li].getAttributeType()==IReportConstants.ATTRIB_USER )
	      {
	          lstrTmp=(String)request.getAttribute("reportUserName");
	      }
	      else if( reportAttributes[li].getAttributeType()==IReportConstants.ATTRIB_HOST_NAME )
	      {
	          lstrTmp=(String)request.getAttribute("reportHostName");
	      }
	      else if( reportAttributes[li].getAttributeType()==IReportConstants.ATTRIB_HOST_IP )
	      {
	          lstrTmp=(String)request.getAttribute("reportHostIP");
	      }
	      else if( reportAttributes[li].getAttributeType()==IReportConstants.ATTRIB_OTHER )
	      {
	          lstrTmp=reportAttributes[li].getAttributeValue().toString();
	      }
	      if(reportAttributes[li].getAlignment()==IReportConstants.ALIGN_LEFT)
	      {
	        lsbLeftHeader.append(((li>0)? " " : "")).append(lstrTmp);
	      }
	      else if(reportAttributes[li].getAlignment()==IReportConstants.ALIGN_CENTER)
	      {
	        lsbCentreHeader.append(((li>0)? " " : "")).append(lstrTmp);
	      }
	      else if(reportAttributes[li].getAlignment()==IReportConstants.ALIGN_RIGHT)
	      {
	        lsbRightHeader.append(((li>0)? " " : "")).append(lstrTmp);
	      }
	    }
	  } 
	  lsbLeftHeader = (lsbLeftHeader.toString().trim().equals( "" )) ? new StringBuffer("&nbsp") : lsbLeftHeader;
	  lsbCentreHeader = (lsbCentreHeader.toString().trim().equals( "" )) ? new StringBuffer("&nbsp") : lsbCentreHeader;
	  lsbRightHeader = (lsbRightHeader.toString().trim().equals( "" )) ? new StringBuffer("&nbsp") : lsbRightHeader;
	%>
	<table border=0 cellpadding=0 cellspacing=0 width="100%">
	<tr>
		<td valign="bottom" align=left class="Label3" width="33%"><%=lsbLeftHeader%></td>
		<td valign="bottom" align=center class="Label3" width="33%"><%=lsbCentreHeader%></td>
		<td valign="bottom" align=right class="Label3" width="33%"><%=lsbRightHeader%></td>
	</tr>
	</table>

 <% } %>
