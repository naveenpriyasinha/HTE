<%
/*
* Filename	  : Report.jsp
* Purpose	  : 
* @VERSION    : 1.0
* @AUTHOR     : Jitesh Panchal, Abhishek Aggarwal
* Date        : 14th March 2005
* 
* REV. HISTORY :
*
*-----------------------------------------------------------------------------
* DATE          AUTHOR                  DESCRIPTION
* 05-Oct-2005   Jitesh Panchal          Language support changes
* 08-Nov-2005   Jitesh Panchal          Column Grouping Added
* 30-Mar-2006   Samir Vadariya          User Defined Ok button URL added
*-----------------------------------------------------------------------------
*/
%>
<%@include file="rptcommon.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- Changes by Common team --%>
<%@ page import = "java.util.Locale,
                   java.util.ResourceBundle,
                   java.util.StringTokenizer,
                   com.tcs.sgv.common.util.reports.IReportConstants,
                   com.tcs.sgv.common.valuebeans.reports.*,
                   com.tcs.sgv.common.util.reports.*" %>
<%-- End Common team --%>
<%
try
{
    ResourceBundle constantBundle = ResourceBundle.getBundle("resources/reports/SGVConstantsReports");
	String uri = request.getContextPath() + constantBundle.getString("INITIALURL");
	  uri += viewmode;
	request.setAttribute("reportURI", uri);
	
	//changes made by common team
    ReportVO rvoMain = (ReportVO)session.getAttribute( "reportVO"+request.getParameter("reportCode") );
    ReportData rdataMain = (ReportData)session.getAttribute( "reportData"+request.getParameter("reportCode") );
    StyleVO style = rvoMain.getStyle( IReportConstants.REPORT_PAGE_BACK_BTN);
    StyleVO Search_withinSearch_Button = rvoMain.getStyle( IReportConstants.REPORT_PAGE_SEARCH_WITHIN_SEARCH_BTN);
    StyleVO okBtnStyle = rvoMain.getStyle(IReportConstants.REPORT_PAGE_OK_BTN_URL);
    StyleVO okBtnText  = rvoMain.getStyle(IReportConstants.REPORT_PAGE_OK_BTN_TEXT);
    StyleVO additionalBtn = rvoMain.getStyle(IReportConstants.REPORT_PAGE_ADTNL_BTN);
    StyleVO additionalBtnIsNewWindow = rvoMain.getStyle(IReportConstants.REPORT_PAGE_ADTNL_BTN_NEW_WINDOW);
    String strAdditionalBtn=null; 
    String strAdditionalBtnIsNewWindow=null;
    String strAdditionalBtnIsNewWindowPrint=null;
      if(additionalBtnIsNewWindow!= null)
      {
          String styleValue = additionalBtnIsNewWindow.getStyleValue();
          String[] strArrStyles = styleValue.split("\\,");
              if(strArrStyles[0]!= null)
              {
          strAdditionalBtnIsNewWindow = strArrStyles[0].toUpperCase();
              }
              if(strArrStyles[1]!= null)
              {
          strAdditionalBtnIsNewWindowPrint= strArrStyles[1].toUpperCase();
              }
      }
           
      if(additionalBtn!= null)
      {
     strAdditionalBtn = additionalBtn.getStyleValue();
      }
   StringTokenizer st = null;
   String strAdtnlBtnText = null;
   String strAdtnlBtnURL  = null;
      if(strAdditionalBtn!= null)
      {
         st = new StringTokenizer(strAdditionalBtn,",",false);
           
           int tokenCount = 1;
    
  
		      while (st.hasMoreTokens()) {
		        
		        if(tokenCount % 2 != 0)
		       {
		           strAdtnlBtnText = st.nextToken();
		       }else
		       {
		         strAdtnlBtnURL = st.nextToken();
		       }
		        tokenCount++;
		    }
      }

    String okBtnTextValue = null;
    String value = (style != null) ? style.getStyleValue() : "no";
    String value_Search_withinSearch_Button = (Search_withinSearch_Button != null) ? Search_withinSearch_Button.getStyleValue() : "no";
    String strAdtnlBtnValue = (additionalBtn != null) ? additionalBtn.getStyleValue() : null;
    boolean showBackBtn = (value != null) ? value.trim().equalsIgnoreCase("YES") : true;
    boolean showSearchWithinSearchBtn = (value_Search_withinSearch_Button != null) ? value_Search_withinSearch_Button.trim().equalsIgnoreCase("YES") : true;
    boolean showAdtnlBtn = (strAdtnlBtnValue != null &&!strAdtnlBtnValue.trim().equals("")) ? true : false;
    StyleVO dynamicReport=rvoMain.getStyle( IReportConstants.DYNAMIC_REPORT);
    String  result=(dynamicReport != null) ? dynamicReport.getStyleValue() : "";
    boolean dynamicRpt = (result != null) ? result.trim().equalsIgnoreCase("YES") : false;
	String dynamicReportstr = (dynamicRpt) ? "&dynamicReport=true" : "";
  //end common team
%>


    <head>
        <style media="screen,print" type="text/css">
            body {
                width: 100%;
                margin: 5; 
            }
        }
        </style>
    </head>
   
    <form id="rptForm_ID" name="rptForm_Name" METHOD=POST action='<%=uri%>&reportCode=<%=rvoMain.getReportCode()%>&action=parameterPage<%=rdataMain.getHiddenParametersString()%>'>
        <table border=0 width="100%">
        <tr><td class="reportBackground">
        <%@include file="rptbody.jsp"%>        
        </td></tr>
        <tr><td></td></tr>
        <tr><td class="reportTools">
         <%@include file="rpttools.jsp"%> 
        </td></tr>
        <tr><td><center>
            <%
              if(okBtnText != null && (okBtnText.getStyleValue()!= null && !okBtnText.getStyleValue().trim().equals("") ))
              {
                  okBtnTextValue = okBtnText.getStyleValue();
             
            %>
            <input type="button" class="${buttoncss} buttonDisplay" value="<%=okBtnTextValue%>" onClick="getok()">
            
            <%
              }
              else
              {%>
              
            <input type="button" class="${buttoncss} buttonDisplay" value='<%=localStringsBundle.getString("Report.button.ok")%>' onClick="getok()">   
              <% }
            if( showBackBtn )
            {
            %>
            &nbsp;<input type="button" class="${buttoncss}" value=<%=localStringsBundle.getString("Report.button.back")%> onClick="backToParameterPage()">
            <%
            }
            if(showSearchWithinSearchBtn)
            {
            %>
            &nbsp;<input type="button" class="${buttoncss}" value="Search within Search" onClick="backToParameterPageForSearchWithinSearch()">
            <%
            }
            if(showAdtnlBtn)
            {
                if(strAdditionalBtnIsNewWindow!= null)
                {
                    if(strAdditionalBtnIsNewWindow.equalsIgnoreCase("YES"))
                    {  %>
                     &nbsp;<input type="button" class="${buttoncss}" value="<%=strAdtnlBtnText%>" onClick="getAdditionalBtnNewWindow('<%=strAdditionalBtnIsNewWindowPrint%>')">
                        
                  <%  }else if(strAdditionalBtnIsNewWindow.equalsIgnoreCase("NO"))
                    { %>
                         &nbsp;<input type="button" class="${buttoncss}" value="<%=strAdtnlBtnText%>" onClick="getAdditionalBtn()">
                   <% }
                }else
                { %>
                 &nbsp;<input type="button" class="${buttoncss}" value="<%=strAdtnlBtnText%>" onClick="getAdditionalBtn()">
                <% }
            }
            %>
        </center></td></tr>
        </table>
    </form>
    <script language=javascript>
    rptNewWinObj=null;
    function getok()
    {
	 	showProgressbar();
        <%if(okBtnStyle != null && okBtnStyle.getStyleValue() != null){ %>
        document.forms[0].action = "<%=okBtnStyle.getStyleValue()%>";
        <%} else { %>
        document.forms[0].action="<%=uri%>&reportCode=<%=rvoMain.getReportCode()%>&action=parameterPage<%=rdataMain.getHiddenParametersString()%><%=dynamicReportstr%>";
        <% } %>
        <%--
        //document.forms[0].action="/IWAS/FrontServlet?requestType=CommonReportRH&reportId=<%=rvo.getReportId()%>&action=parameterPage";
        //alert(document.forms[0].action);
        --%>
        document.forms[0].submit();
    }
   
    function backToParameterPage()
    {
    	showProgressbar();
    	
        document.forms[0].action ="<%=uri%>&reportCode=<%=rvoMain.getReportCode()%>&action=backToParameterPage<%=rdataMain.getHiddenParametersString()%>";
        document.forms[0].submit();
    }

    function backToParameterPageForSearchWithinSearch()
	{
    	showProgressbar();
    	
        document.forms[0].action ="<%=uri%>&reportCode=<%=rvoMain.getReportCode()%>&action=backToParameterPage<%=rdataMain.getHiddenParametersString()%>&searchWithinSearch=true";
       // alert(document.forms[0].action);
        document.forms[0].submit();
    }
    
    function getAdditionalBtn()
    {
		showProgressbar();
        document.forms[0].action ="<%=strAdtnlBtnURL%>";
        document.forms[0].submit();
    }
    function getAdditionalBtnNewWindow(isPrintEnable)
    {
    showProgressbar();
    
    var urlstyle = 'height=650,width=780,toolbar=no,minimize=no,status=no,menubar=no,location=no,scrollbars=yes,top=50,left=200';
  
    var url="<%=strAdtnlBtnURL%>";
     if(rptNewWinObj == null)
  		 {
   		rptNewWinObj =window.open(url, "Show", urlstyle); 
   		    if(isPrintEnable == 'P')
   		      {
   				rptNewWinObj.print();
   				}
   		}
     	else
     	{
   		rptNewWinObj.close();
   		rptNewWinObj = window.open(url, "Show", urlstyle); 
   		  if(isPrintEnable == 'P')
   		    {
   			rptNewWinObj.print();
   			}
   	    } 
     hideProgressbar();   
    }
    </script>
<%
}
catch( Exception ex )
{
    ex.printStackTrace();
}
%>