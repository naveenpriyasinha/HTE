<%
/*
* Filename	  : rptfooter.jsp
* Purpose	    : 
* @VERSION    : 1.0
* @AUTHOR     : Jitesh Panchal, Abhishek Aggarwal
* Date        : 14th March 2005
* 
* REV. HISTORY :
*
*-----------------------------------------------------------------------------
* DATE        AUTHOR                DESCRIPTION
* 05-Oct-2005 Jitesh Panchal        Language support changes
*
*-----------------------------------------------------------------------------
*/
%>
<%@ page import = "java.util.Locale,
					java.util.Date,
					java.text.DateFormat,
					java.text.SimpleDateFormat,
                   java.util.ResourceBundle,
                   com.tcs.sgv.common.valuebeans.reports.*,
                   com.tcs.sgv.common.util.reports.*" %>
<%
  Locale rptFooterUserLocale = null;
  if (session.getAttribute("LocaleID") != null && !(session.getAttribute("LocaleID").equals("null")) )
    rptFooterUserLocale = (Locale) session.getAttribute("LocaleID") ;
  else
    rptFooterUserLocale = request.getLocale();

  ResourceBundle rptFooterLocalBundle = ResourceBundle.getBundle("resources/reports/SGVLabelsReports", rptFooterUserLocale) ;
  
  StringBuffer lsbLeftFooter=new StringBuffer("");
  StringBuffer lsbCentreFooter=new StringBuffer("");
  StringBuffer lsbRightFooter=new StringBuffer("");
  
  //changes made by common
  Object objSubRptCodeFooter = (request.getAttribute("subReportCode"));
  String subRptCodeFooter = ( objSubRptCodeFooter != null ? objSubRptCodeFooter.toString():null);
  ReportVO reportFooterVO = null;
  ReportData rptFooterData = (ReportData) session.getAttribute("reportData" + request.getParameter("reportCode"));
  if(subRptCodeFooter != null)
  {
	  ReportData rdataFooter = (ReportData) rptFooterData.getSubReportData("reportData"+subRptCodeFooter);
	  if(rdataFooter != null) reportFooterVO = rdataFooter.getReportVO();
      request.removeAttribute("subReportCode");
  }
  else
  {
	  reportFooterVO = (ReportVO)session.getAttribute("reportVO"+request.getParameter("reportCode"));
  }
  //end changes 
  boolean showFtr = true;
  if(reportFooterVO != null)
  {
	  StyleVO styleNoFtr = reportFooterVO.getStyle(IReportConstants.NO_FOOTER);
	  showFtr = styleNoFtr != null && styleNoFtr.getStyleValue() != null &&
	  			styleNoFtr.getStyleValue().equalsIgnoreCase("yes") ? false : true;	
  }
  
  if(showFtr)
  {
	  ReportAttributeVO[] reportAttributesFooter=( ReportAttributeVO[])reportFooterVO.getReportAttributes();
	  for(int li=0; li<reportAttributesFooter.length; li++)
	  {
	    if(reportAttributesFooter[li].getLocation()==IReportConstants.LOCATION_FOOTER)
	    {
	      String lstrTmp="";
	      if(reportAttributesFooter[li].getAttributeType()==IReportConstants.ATTRIB_CURRENT_DATE)
	      {
	    	  lstrTmp=rptFooterLocalBundle.getString("Report.label.date")+":"+ ((rptFooterData != null)?rptFooterData.getFormattedDate():"");
	      }
	      else if(reportAttributesFooter[li].getAttributeType()==IReportConstants.ATTRIB_CURRENT_TIME)
	      {
	    	  lstrTmp = (rptFooterData != null)?rptFooterData.getFormattedTime():"";
	      }      
	      else if( reportAttributesFooter[li].getAttributeType()==IReportConstants.ATTRIB_USER )
	      {
	          lstrTmp=(String)request.getAttribute("reportUserName");
	      }
	      else if( reportAttributesFooter[li].getAttributeType()==IReportConstants.ATTRIB_HOST_NAME )
	      {
	    	  lstrTmp=(String)request.getAttribute("reportHostName");
	      }
	      else if( reportAttributesFooter[li].getAttributeType()==IReportConstants.ATTRIB_HOST_IP)
	      {
	    	  lstrTmp=(String)request.getAttribute("reportHostIP");
	      }
	      else if( reportAttributesFooter[li].getAttributeType()==IReportConstants.ATTRIB_OTHER)
	      {
	    	  lstrTmp=(String)reportAttributesFooter[li].getAttributeValue().toString();
	      }
	      if(reportAttributesFooter[li].getAlignment()==IReportConstants.ALIGN_LEFT)
	      {
	        lsbLeftFooter.append(((li>0)? " " : "")).append(lstrTmp);
	      }
	      else if(reportAttributesFooter[li].getAlignment()==IReportConstants.ALIGN_CENTER)
	      {
	        lsbCentreFooter.append(((li>0)? " " : "")).append(lstrTmp);
	      }
	      else if(reportAttributesFooter[li].getAlignment()==IReportConstants.ALIGN_RIGHT)
	      {
	        lsbRightFooter.append(((li>0)? " " : "")).append(lstrTmp);
	      }
	    }
	  } 
	  
	  lsbLeftFooter = (lsbLeftFooter.toString().trim().equals( "" )) ? new StringBuffer("&nbsp") : lsbLeftFooter;
	  lsbCentreFooter = (lsbCentreFooter.toString().trim().equals( "" )) ? new StringBuffer("&nbsp") : lsbCentreFooter;
	  lsbRightFooter = (lsbRightFooter.toString().trim().equals( "" )) ? new StringBuffer("&nbsp") : lsbRightFooter;
	%>
	
	<table border=0 cellpadding=0 cellspacing=0 width="100%">
		<tr>
			<td valign="top" align=left class="Label3" width="33%"><%=lsbLeftFooter%></td>
			<td valign="top" align=center class="Label3" width="33%"><%=lsbCentreFooter%></td>
			<td valign="top" align=right class="Label3" width="33%"><%=lsbRightFooter%></td>
		</tr>
		<tr><td>&nbsp;</td></tr>
	</table>
	
	<%
		String strFooter = "";
		StyleVO report_footer = reportFooterVO.getStyle(IReportConstants.REPORT_FOOTER);
		
		if(report_footer != null && (!(report_footer.equals(""))) )
		{
			
			//DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");			
			//String formattedDate = dateFormat1.format(new Date());
			 Date now = new Date();
			  SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
			 
			%>
			<b>Verification Time:-</b><%= format.format(now) %>


<%
			strFooter = report_footer.getStyleValue();
		}
		else
		{
			 Date now = new Date();
			  SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");%>
			<b>Verification Time:-</b><%=format.format(now)%>
<%
			strFooter = rptFooterLocalBundle.getString("MIS.ReportFooter");
			
		}
	%>
	<font class=Label2>
	  <div align='left'><%=strFooter%></div>
	</font>
	
	
 <% } %> 

