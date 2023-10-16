
<%
/*
* Filename	  : ReportParameter.jsp
* Purpose	  : 
* @VERSION    : 1.0
* @AUTHOR     : Jitesh Panchal, Abhishek Aggarwal
* Date        : 14-May-2005
* 
* REV. HISTORY:
*
*-----------------------------------------------------------------------------
* DATE        AUTHOR                    DESCRIPTION
* 22-sep-2005 Jitesh Panchal            Added input validation code
* 05-Oct-2005 Jitesh Panchal            Language Support Added
* 24-Oct-2005 Samir Vadariya            Code for dependent combos added
* 08-Nov-2005 Jitesh Panchal            Column grouping related changes.
* 10-Feb-2006 Jitesh Panchal            Wide dropdown related changes.
*-----------------------------------------------------------------------------
*/
%>

<%@include file="rptcommon.jsp"%>
<%@page language="java"
	import="com.tcs.sgv.common.util.reports.*,com.tcs.sgv.common.valuebeans.reports.*,java.util.Collection,java.util.Map,java.util.Set,java.util.Locale,java.util.ResourceBundle,java.util.Iterator,java.util.Enumeration,java.util.ArrayList,java.util.Hashtable,java.text.SimpleDateFormat,java.util.Arrays,java.util.List,java.util.Date"%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%--  Added BY Niteshwar on 16-08-2007 for Proper Message using bundle to be displayed in DateTime Tag --%>
<fmt:setLocale value='<%=(String)session.getAttribute("locale")%>' />
<fmt:setBundle basename="resources.reports.SGVLabelsReports"
	var="sgvLabelsReports" scope="request" />
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%

		try {
		ResourceBundle constantBundle = ResourceBundle
		.getBundle("resources/reports/SGVConstantsReports");
		String uri = request.getContextPath()
		+ constantBundle.getString("INITIALURL");
		uri += viewmode;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat(
		"yyyy-MM-dd hh:mm:ss");
%>

<%
    		ReportVO rvo = null;
    		Locale userLocale = null;
    		if (session.getAttribute("LocaleID") != null
    		&& !(session.getAttribute("LocaleID").equals("null")))
    			userLocale = (Locale) session.getAttribute("LocaleID");
    		else
    			userLocale = request.getLocale();

    		ResourceBundle localStringsBundle = ResourceBundle.getBundle(
    		"resources/reports/SGVLabelsReports", userLocale);
    		ResourceBundle localMsgBundle = ResourceBundle.getBundle(
    		"resources/reports/SGVAlertMsgReports", userLocale);
    		//changes by common team
    		rvo = (ReportVO) session.getAttribute("reportVO"
    		+ request.getParameter("reportCode"));
    		//end changes
    		StyleVO colRuleStyle = rvo
    		.getStyle(IReportConstants.REPORT_COL_RULES);
    		boolean colRuleEnable = false;
    		if (colRuleStyle != null
    		&& colRuleStyle.getStyleValue() != null
    		&& colRuleStyle.getStyleValue().equalsIgnoreCase("Yes")) {
    			colRuleEnable = true;
    		}
    		
    		

    		// Start Added By Dhaval Modi for Multi Column Parameter Page
    		StyleVO multiColumnStyle = rvo
    		.getStyle(IReportConstants.MULTI_COLUMN_PARA_PAGE);
    		boolean multiColumnEnable = true;
    		if (multiColumnStyle != null
    		&& multiColumnStyle.getStyleValue() != null
    		&& multiColumnStyle.getStyleValue().equalsIgnoreCase(
    				"No")) {
    			multiColumnEnable = false;
    		}
    		// End Added By Shaval Modi for Multi Column Parameter Page    
    		StyleVO srchUrlStyle = rvo
    		.getStyle(IReportConstants.SEARCH_RPT_BTN_URL);
    		StyleVO rptUrlStyle = rvo
    		.getStyle(IReportConstants.GENERATE_RPT_BTN_URL);

    		//*****************************************************************************
    		StyleVO dynamicReport = rvo
    		.getStyle(IReportConstants.DYNAMIC_REPORT);
    		String result = (dynamicReport != null) ? dynamicReport
    		.getStyleValue() : "";
    		boolean dynamicRpt = (result != null) ? result.trim()
    		.equalsIgnoreCase("YES") : false;
    		String dynamicReportstr = (dynamicRpt) ? "&dynamicReport=true"
    		: "";
    		//****************************************************************************

    		// Start Added by Dharmesh for Custom report Page
    		StyleVO custReportPageStyle = rvo
    		.getStyle(IReportConstants.CUSTOM_REPORT_PAGE);
    		// End Added by Dharmesh for Custom report Page
    		
    		// Added by Brijesh Prajapati to populate dependent DropDown box using ajax:select tag.
    		ArrayList populateChilDropDownList = new ArrayList();
    		String jsFile = "";
    %>
<script type="text/javascript"
	src='<c:url value="/script/reports/prmt_validations.js"/>'></script>
<script type="text/javascript"
	src='<c:url value="/script/reports/alertmsgutils.js"/>'></script>
<script type="text/javascript"
	src='<c:url value="/script/common/calendar.js"/>'></script>
<script language="JavaScript">
   btnAction = null ;
   arrOfCombos = new Array();
   arrOfRadioButtons = new Array();
   arrOfCheckBox = new Array();
      
  function formAction()
  {
	showProgressbar();
	
  	var flag = true;
    if( btnAction == "reset" )
    {
        document.forms[0].action ="<%=uri%>&fromReportReset=true&reportCode=<%=rvo.getReportCode()%>&action=parameterPage<%=rvo.getHiddenParametersString()%><%=dynamicReportstr%>";        
        <%-- document.forms[0].action ="FrontServlet?requestType=CommonReport&reportId=<%=rvo.getReportId()%>&action=generateReport"; --%>
    }
    else if( btnAction == "addQuery" )
    {
       if(validateColumnRule())
       {
                  
         document.forms[0].action ="<%=uri%>&reportCode=<%=rvo.getReportCode()%>&action=addQuery";
         
       }
       else
      {
           
         flag = false;
       }
    }    
    else
    {
        if( areParametersValid() )
        {
          if( btnAction == "search" )
          {
            <%
            //Changed by samir to include user defined URL on 20-July-2006
            if(srchUrlStyle != null && srchUrlStyle.getStyleValue() != null) { %>
               document.forms[0].action = "<%=srchUrlStyle.getStyleValue()%>";
            <% } else { %>
                document.forms[0].action = "<%=uri%>&reportCode=<%=rvo.getReportCode()%>&action=searchReport";
            <% } %>
            <%-- //document.forms[0].action ="FrontServlet?requestType=CommonReport&reportId=<%=rvo.getReportId()%>&action=searchReport"; --%>
          }
          else if( btnAction == "report" )
          {
            <%
            //Changed by samir to include user defined URL on 20-July-2006
            if(rptUrlStyle != null && rptUrlStyle.getStyleValue() != null) { %>
               document.forms[0].action = "<%=rptUrlStyle.getStyleValue()%>";
            <% } else if(custReportPageStyle != null && custReportPageStyle.getStyleValue() != null) { %>
                document.forms[0].action ="<%=uri%>&reportCode=<%=rvo.getReportCode()%>&action=generateReport&FromParaPage=TRUE&customReportPage=<%=custReportPageStyle.getStyleValue()%>";
            <% } else { %>
                document.forms[0].action ="<%=uri%>&reportCode=<%=rvo.getReportCode()%>&action=generateReport&FromParaPage=TRUE";
            <% } %>
            
            
            <%-- document.forms[0].action ="FrontServlet?requestType=CommonReport&reportId=<%=rvo.getReportId()%>&action=generateReport"; --%>
          }
        }
        else
        {
        	if( btnAction == "report" )
        	{
            	document.getElementById("rptBtn_GenerateReport").disabled = false;
            	if(document.getElementById("rptBtn_ResetBtn") != null)
            		document.getElementById("rptBtn_ResetBtn").disabled = false;
        	}    
          flag = false;
        }
    }

      if( flag )
      {
      	document.forms[0].submit();
      }
      else
      {
      	hideProgressbar();
      }
  }
  function areParametersValid()
  {
    var fvalue;
    var mandatoryFunArray = new Array();
    <%
      ReportParameterVO[] rprms = (rvo != null) ? rvo.getParameters() : null;
      int ptotal = (rprms != null) ? rprms.length : 0;
      for( int i = 0; i < ptotal; i++ )
      {
        if( rprms[i].isMandatory() )
        {
          if( rprms[i].getParameterType() == IReportConstants.DATE_INPUT 
    		|| rprms[i].getParameterType() == IReportConstants.TIME_INPUT // Added By Niteshwar on 17-08-07 for Time Parameter
    		|| rprms[i].getParameterType() == IReportConstants.SEARCH_INPUT // Added By Niteshwar on 05-01-08 for Search Parameter
            || rprms[i].getParameterType() == IReportConstants.NUMBER_INPUT
            || rprms[i].getParameterType() == IReportConstants.DECIMAL_INPUT
            || rprms[i].getParameterType() == IReportConstants.EMAIL_INPUT
            || rprms[i].getParameterType() == IReportConstants.TEXT_INPUT )
          {
            String msg = localMsgBundle.getString( "MandatoryField" );
            %>
            fvalue = document.forms[0].elements["<%=rprms[i].getParameterName()%>"].value;
            var flds = new Array( "<%=rprms[i].getParameterDisplayName()%>" );
            if( fvalue == "" )
            {
                alert( getAlertMessage( "<%=msg%>", flds) );
              return false;
            }
            <%
          }
          else if( rprms[i].getParameterType() == IReportConstants.DROPDOWN_INPUT ||
                   rprms[i].getParameterType() == IReportConstants.WIDE_DROPDOWN_INPUT ||
                   rprms[i].getParameterType() == IReportConstants.LIST_MULTISELECT_INPUT )
          {
            String msg = localMsgBundle.getString( "DropdownMandatoryField" );
            %>
            fvalue = document.forms[0].elements["<%=rprms[i].getParameterName()%>"].value;
            var flds = new Array( "<%=rprms[i].getParameterDisplayName()%>" );
			if( fvalue == "-1" || fvalue == "")
            {
              alert( getAlertMessage( "<%=msg%>", flds ) );
              return false;
            }
            <%
          }
		  // Added for CheckBox Validation...
          else if(rprms[i].getParameterType() == IReportConstants.CHECKBOX_INPUT )
          {
        	  String msg = localMsgBundle.getString( "DropdownMandatoryField" );
              %>
              	var howMany = 0;
              	var control = document.forms[0].<%=rprms[i].getParameterName()%>
              	
              		for( ri =0; ri<control.length; ri++)	
              		{
              				if(control[ri].checked == true)
              				{
              					howMany++;
              					break;
              				}

              		}
              		var fldsChk = new Array( "<%=rprms[i].getParameterDisplayName()%>" );
              		if(howMany == 0)
              		{
             	     	 alert( getAlertMessage( "<%=msg%>", fldsChk ) );
		                 return false;
        		    }
              		
              <%
          }
//        Added for CheckBox Validation...Collection chkData = (Collection)session.getAttribute( prms[i].getParameterName() + "Data" );
	      else if(rprms[i].getParameterType() == IReportConstants.OPTION_INPUT  || rprms[i].getParameterType() == IReportConstants.YESNO_OPTION_INPUT )
          {
        	  String msg = localMsgBundle.getString( "DropdownMandatoryField" );
              %>
              	var howMany = 0;
              	var control = document.forms[0].<%=rprms[i].getParameterName()%>
              	
              		for( ri =0; ri<control.length; ri++)	
              		{
              				if(control[ri].checked == true)
              				{
              					howMany++;
              					break;
              				}

              		}
              		var fldsRd = new Array( "<%=rprms[i].getParameterDisplayName()%>" );
              		if(howMany == 0)
              		{
             	     	 alert( getAlertMessage( "<%=msg%>", fldsRd ) );
		                 return false;
        		    }
              		
              <%
          }
	      else if( (rprms[i].getParameterType() == IReportConstants.DATE_RANGE_INPUT) || 
        		   (rprms[i].getParameterType() == IReportConstants.TIME_RANGE_INPUT) ||
                   (rprms[i].getParameterType() == IReportConstants.NUMBER_RANGE_INPUT) || 
                   (rprms[i].getParameterType() == IReportConstants.DECIMAL_RANGE_INPUT) )
          {
            String msg = localMsgBundle.getString( "MandatoryField" );
            String prependFromLabel = localStringsBundle.getString("Report.label.prepend.from");
            String appendFromLabel = localStringsBundle.getString("Report.label.append.from");
            String prependToLabel = localStringsBundle.getString("Report.label.prepend.to");
            String appendToLabel = localStringsBundle.getString("Report.label.append.to");
            
            
            
            // Added By Niteshwar on 16-08-2007 for removing extra space in alerts//
            if(prependToLabel != null && !prependToLabel.trim().equals(""))
            {
           		
            	prependToLabel = prependToLabel + " ";
            }
            
            if(appendToLabel != null && !appendToLabel.trim().equals(""))
            {
				    
            	appendToLabel = " " + appendToLabel;
            }
            %>
            <%-- Changed For Proper Alert Message without Blank Spaces --%>
            
           var flds = new Array( "<%=prependFromLabel + rprms[i].getParameterDisplayName() + appendFromLabel%>" );
             
           <%--var flds = new Array( "<%=prependFromLabel + " " + rprms[i].getParameterDisplayName() + " " + appendFromLabel%>" ); --%>
           
            fvalue = document.forms[0].elements['<%=rprms[i].getParameterName()+"from"%>'].value;
            if( fvalue == "" )
            {
              alert( getAlertMessage( "<%=msg%>", flds ) );
              return false;
            }
            fvalue = document.forms[0].elements['<%=rprms[i].getParameterName()+"to"%>'].value;
            
            <%-- Changed For Proper Alert Message without Blank Spaces --%>
             flds = new Array( "<%=prependToLabel + rprms[i].getParameterDisplayName() + appendToLabel%>" );         
            <%--flds = new Array( "<%=prependToLabel + " " + rprms[i].getParameterDisplayName() + " " + appendToLabel%>" ); --%>   
           
            if( fvalue == "" )
            {
              alert( getAlertMessage( "<%=msg%>", flds ) );
              return false;
            }
            <%
          }
          else if( (rprms[i].getParameterType() == IReportConstants.COMPARE_NUMBER) ||
                  (rprms[i].getParameterType() == IReportConstants.COMPARE_DECIMAL) )
          {
            String msg = localMsgBundle.getString( "DropdownMandatoryField" );
            String cmprFieldmsg = localMsgBundle.getString( "ComparisionMandatoryField" );
            %>
            fvalue = document.forms[0].elements["<%=rprms[i].getParameterName()%>"].value;
            var flds = new Array( "<%=rprms[i].getParameterDisplayName()%>" );
            if( fvalue == "-1" )
            {
              alert( getAlertMessage( "<%=msg%>", flds ) );
              return false;
            }
            else
            {
              var svalue = document.forms[0].elements['<%=rprms[i].getParameterName()+"from"%>'].value;
              if( svalue == "" )
              {
              alert( getAlertMessage( "<%=cmprFieldmsg%>", flds ) );
                return false;
              }
              if( fvalue == "4" )
              {
                svalue = document.forms[0].elements['<%=rprms[i].getParameterName()+"to"%>'].value;
                if( svalue == "" )
                {
              alert( getAlertMessage( "<%=cmprFieldmsg%>", flds ) );
                  return false;
                }
              }
            }
            <%
          }
        }
        
        if( rprms[i].getParameterType() == IReportConstants.DATE_RANGE_INPUT )
        {
            String msg = localMsgBundle.getString( "InvalidDateRange" );
            String prependFromLabel = localStringsBundle.getString("Report.label.prepend.from");
            String appendFromLabel = localStringsBundle.getString("Report.label.append.from");
            String prependToLabel = localStringsBundle.getString("Report.label.prepend.to");
            String appendToLabel = localStringsBundle.getString("Report.label.append.to");
           
          //Added For Proper Alert Message without Blank Spaces by Niteshwar on 16-08-2007 // 
           if(prependToLabel != null && !prependToLabel.trim().equals(""))
            {
           		
            	prependToLabel = prependToLabel + " ";
            }
            if(appendToLabel != null && !appendToLabel.trim().equals(""))
            {
				        
            	appendToLabel = " " + appendToLabel;
            }
            %>
	        
	       <%--     Changed For Proper Alert Message without Blank Spaces by Niteshwar on 16-08-2007   --%>
            
           var flds = new Array( "<%=prependFromLabel + rprms[i].getParameterDisplayName() + appendFromLabel%>",
                                "<%=prependToLabel  + rprms[i].getParameterDisplayName() + appendToLabel%>");
                                
           <%--var flds = new Array( "<%=prependFromLabel + " " + rprms[i].getParameterDisplayName() + " " + appendFromLabel%>",
                                "<%=prependToLabel + " " + rprms[i].getParameterDisplayName() + " " + appendToLabel%>");--%>
                                
            fvalue = document.forms[0].elements["<%=rprms[i].getParameterName()+"from"%>"].value;
            var tvalue = document.forms[0].elements['<%=rprms[i].getParameterName()+"to"%>'].value;
            if( fvalue != "" && tvalue != "" )
            {
              if( !compareDates( fvalue, tvalue ) )
              {
                alert( getAlertMessage( "<%=msg%>", flds ) );
                document.forms[0].elements['<%=rprms[i].getParameterName()+"from"%>'].select();
                return false;
              }
            }
            <%
        }
        
     //********************************Added by Niteshwar for Time Range Input on 20-07-2007**********************
        if( rprms[i].getParameterType() == IReportConstants.TIME_RANGE_INPUT )
        {
            String msg = localMsgBundle.getString( "InvalidDateRange" );
            String prependFromLabel = localStringsBundle.getString("Report.label.prepend.from");
            String appendFromLabel = localStringsBundle.getString("Report.label.append.from");
            String prependToLabel = localStringsBundle.getString("Report.label.prepend.to");
            String appendToLabel = localStringsBundle.getString("Report.label.append.to");
            
         //Added For Proper Alert Message without Blank Spaces by Niteshwar on 16-08-2007 // 
            if(prependToLabel != null && !prependToLabel.trim().equals(""))
             {
            		
             	prependToLabel = prependToLabel + " ";
             }
             if(appendToLabel != null && !appendToLabel.trim().equals(""))
             {
 				        
             	appendToLabel = " " + appendToLabel;
             }
             %>
             
             <%-- Changed For Proper Alert Message without Blank Spaces by Niteshwar on 16-08-2007  --%> 
            var flds = new Array( "<%=prependFromLabel + rprms[i].getParameterDisplayName() + appendFromLabel%>",
                                "<%=prependToLabel + rprms[i].getParameterDisplayName()+ appendToLabel%>");
                                
             <%--var flds = new Array( "<%=prependFromLabel + " " + rprms[i].getParameterDisplayName() + " " + appendFromLabel%>",
                                "<%=prependToLabel + " " + rprms[i].getParameterDisplayName() + " " + appendToLabel%>");--%>
                                
            fvalue = document.forms[0].elements['<%=rprms[i].getParameterName()+"from"%>'].value;
            var tvalue = document.forms[0].elements['<%=rprms[i].getParameterName()+"to"%>'].value;
            if( fvalue != "" && tvalue != "" )
            {
              if( !compareDates( fvalue, tvalue ) )
              {
              alert( getAlertMessage( "<%=msg%>", flds ) );
                document.forms[0].elements['<%=rprms[i].getParameterName()+"from"%>'].select();
                return false;
              }
            }
            <%
        }
        //********************end if for time range input******************************************
        
        else if( rprms[i].getParameterType() == IReportConstants.NUMBER_RANGE_INPUT )
        {
            String msg = localMsgBundle.getString( "InvalidNumberRange" );
            String prependFromLabel = localStringsBundle.getString("Report.label.prepend.from");
            String appendFromLabel = localStringsBundle.getString("Report.label.append.from");
            String prependToLabel = localStringsBundle.getString("Report.label.prepend.to");
            String appendToLabel = localStringsBundle.getString("Report.label.append.to");
        %>
            var flds = new Array( "<%=prependFromLabel + " " + rprms[i].getParameterDisplayName() + " " + appendFromLabel%>",
                                "<%=prependToLabel + " " + rprms[i].getParameterDisplayName() + " " + appendToLabel%>");
            var fromvalue = document.forms[0].elements['<%=rprms[i].getParameterName()+"from"%>'].value;
            var tovalue = document.forms[0].elements['<%=rprms[i].getParameterName()+"to"%>'].value;
            if( parseInt(fromvalue) > parseInt(tovalue) )
            {
              alert( getAlertMessage( "<%=msg%>", flds ) );
              document.forms[0].elements['<%=rprms[i].getParameterName()+"from"%>'].select();
              return false;
            }
          <%
        }
        else if( rprms[i].getParameterType() == IReportConstants.DECIMAL_RANGE_INPUT )
        {
            String msg = localMsgBundle.getString( "InvalidNumberRange" );
            String prependFromLabel = localStringsBundle.getString("Report.label.prepend.from");
            String appendFromLabel = localStringsBundle.getString("Report.label.append.from");
            String prependToLabel = localStringsBundle.getString("Report.label.prepend.to");
            String appendToLabel = localStringsBundle.getString("Report.label.append.to");
        %>
            var flds = new Array( "<%=prependFromLabel + " " + rprms[i].getParameterDisplayName() + " " + appendFromLabel%>",
                                "<%=prependToLabel + " " + rprms[i].getParameterDisplayName() + " " + appendToLabel%>");
            var fromvalue = document.forms[0].elements['<%=rprms[i].getParameterName()+"from"%>'].value;
            var tovalue = document.forms[0].elements['<%=rprms[i].getParameterName()+"to"%>'].value;
            if( parseFloat(fromvalue) > parseFloat(tovalue) )
            {
              alert( getAlertMessage( "<%=msg%>", flds ) );
              document.forms[0].elements['<%=rprms[i].getParameterName()+"from"%>'].select();
              return false;
            }
          <%
        }
        else if( rprms[i].getParameterType() == IReportConstants.COMPARE_NUMBER )
        {
            String msg = localMsgBundle.getString( "InvalidNumberRange" );
        %>
            fvalue = document.forms[0].elements["<%=rprms[i].getParameterName()%>"].value;
            if( fvalue == "4" )
            {
            var flds = new Array( "from", "to" );
            var fromvalue = document.forms[0].elements['<%=rprms[i].getParameterName()+"from"%>'].value;
            var tovalue = document.forms[0].elements['<%=rprms[i].getParameterName()+"to"%>'].value;
            if( parseInt(fromvalue) > parseInt(tovalue) )
            {
              alert( getAlertMessage( "<%=msg%>", flds ) );
              document.forms[0].elements['<%=rprms[i].getParameterName()+"from"%>'].select();
              return false;
            }
            }
          <%
        }
        else if( rprms[i].getParameterType() == IReportConstants.COMPARE_DECIMAL )
        {
            String msg = localMsgBundle.getString( "InvalidNumberRange" );
        %>
            fvalue = document.forms[0].elements["<%=rprms[i].getParameterName()%>"].value;
            if( fvalue == "4" )
            {
            var flds = new Array( "from", "to" );
            var fromvalue = document.forms[0].elements['<%=rprms[i].getParameterName()+"from"%>'].value;
            var tovalue = document.forms[0].elements['<%=rprms[i].getParameterName()+"to"%>'].value;
            if( parseFloat(fromvalue) > parseFloat(tovalue) )
            {
              alert( getAlertMessage( "<%=msg%>", flds ) );
              document.forms[0].elements['<%=rprms[i].getParameterName()+"from"%>'].select();
              return false;
            }
            }
          <%
        }
        else if( rprms[i].getParameterType() == IReportConstants.EMAIL_INPUT )
        {
          if( rprms[i].isMandatory() )
          {
            String msg = localMsgBundle.getString( "InvalidEmail" );
          %>
            if( !isValidEmailAddress(document.forms[0].elements["<%=rprms[i].getParameterName()%>"]) )
            {
            var flds = new Array( "<%=rprms[i].getParameterDisplayName()%>" );
              alert( getAlertMessage( "<%=msg%>", flds ) );
              return false;
            }
          <%
          }
        }
        
			  String mandatoryFun = "";
			  String mandatoryFun_name = rprms[i].getColumnName();
			  if(mandatoryFun_name != null && !mandatoryFun_name.trim().equalsIgnoreCase(""))
			  {
				  String tempArray[] = mandatoryFun_name.split(",");
				 	for(int y=0;y<tempArray.length;y++)
					{
						String tempElement = tempArray[y];
						if(tempElement.toLowerCase().contains("m:"))
						{
							mandatoryFun = tempElement.split(":")[1];
						}
					}
				 	if(!"".equalsIgnoreCase(mandatoryFun)){
				 		
				 		%> var ttemp = ""; <%
				 		if(mandatoryFun.indexOf("(") == -1)
				 		{
				 			%>ttemp = eval(<%=mandatoryFun%>() );<%
				 		}
				 		else
				 		{
				 			%>ttemp = eval(<%=mandatoryFun%>);<%
				 		}
				 	%> 
				 	  mandatoryFunArray.push(ttemp)   
				 	<%
				 	}
			  }
      }
      %>
      
      for (var k=0;k<mandatoryFunArray.length;k++)
      {
    	  if(!mandatoryFunArray[k]) return false; 
      }
      
      selectedColumns = 0;
      <%
      if( rvo.isColumnSelectionAllowed() )
      {
        /*Hashtable allchlds = rvo.getDynamicLevels();//use getConfiguredLevels
        ArrayList leafChlds = allchlds.get( new Integer( allchlds.size() ) );
        int total */
        ReportColumnVO[] clms = rvo.getLeafColumns();
        int total = (clms != null) ? clms.length : 0;
        boolean hdn = false;
        for( int i = 0; i < total; i++ )
        {
          hdn = (clms[i].getHidden() == null) ? false : (clms[i].getHidden().trim().equalsIgnoreCase("N") ? false : true);
          if( !hdn )
          {
              if( rvo.isLeaf( clms[i] ) && rvo.shouldDisplayColumn( clms[i]) )
              {
                if( clms[i].isMandatory() )
                {
                  %>
                    selectedColumns = selectedColumns +1;
                  <%
                }
                else
                {
                  %>
                  if( document.forms[0].elements["clm_<%=clms[i].getColumnName()%>"].checked)
                  {
                    selectedColumns = selectedColumns +1;
                  }
                  <%
                }
              }
          }
        }
        String msg = localMsgBundle.getString( "SelectAtleastOneColumn" );
        %>
        if( selectedColumns == 0 )
        {
          alert( "<%=msg%>" );
          return false;
        }
        <%
      }
      %>
    return true;
  }
  
  
  <%-- End of added by samir --%>
  <%-- Added by samir for dependent combos --%>
  function populateChildDropDown(parentParameter, parameterValue)
  {
	 showProgressbar();
     document.forms[0].action ="<%=uri%>&reportCode=<%=rvo.getReportCode()%>&action=populateChildDropDown&ParentParameter="+parentParameter+"&ParameterValue="+parameterValue;
     document.forms[0].method = "post";      
     document.forms[0].submit();
  }
  <%-- End of added by Samir  --%>
  
  function updateCheckBoxes( bFlag )
  {
      <%
      if( rvo.isColumnSelectionAllowed() )
      {
        /*Hashtable allchlds = rvo.getDynamicLevels();//use getConfiguredLevels
        ArrayList leafChlds = allchlds.get( new Integer( allchlds.size() ) );
        int total */
        ReportColumnVO[] clms = rvo.getLeafColumns();
        int total = (clms != null) ? clms.length : 0;
        boolean hdn = false;
        for( int i = 0; i < total; i++ )
        {
          hdn = (clms[i].getHidden() == null) ? false : (clms[i].getHidden().trim().equalsIgnoreCase("N") ? false : true);
          if( !hdn )
          {
              if( rvo.isLeaf( clms[i] ) && rvo.shouldDisplayColumn( clms[i]) )
              {
                if( !clms[i].isMandatory() )
                {
                  %>
                  document.forms[0].elements["clm_<%=clms[i].getColumnName()%>"].checked = bFlag;
                  <%
                }
              }
          }
        }
        }
        %>
  }
  </script>


<hdiits:form name="ReportForm" validate="" method="POST"
	encType="multipart/form-data">
	<%
      			//changes by common team
      			rvo = (ReportVO) session.getAttribute("reportVO"
      			+ request.getParameter("reportCode"));
      			//end changes
      			ReportParameterVO[] prms = (rvo != null) ? rvo
      			.getParameters() : null;
      			boolean isDecimal = false;
      			int total = (prms != null) ? prms.length : 0;
      			int minReqPrms = 0;
      			String dv = "", fromdv = "", todv = "";
      			if (total > 0) {
      %>
	<table border="0" cellspacing="0" cellpadding="5" bgcolor='white'
		class="TableBorderLTRBN" style="border-collapse: collapse"
		width="100%">
		<tr>
			<td colspan=10 align="center" class="titles TableBorderBN"><%=rvo.getReportName()%></td>
		</tr>
		<%-- Start Updation By Dhaval Modi on 10/05/2006 --%>
		<%
        if (multiColumnEnable) {
        %>
		<tr>
			<%
          		}
          		int cnt = 0, k = 0;
          		for (int i = 0; i < total; i++) {
          			minReqPrms = (prms[i].isMandatory()) ? (minReqPrms + 1)
          			: minReqPrms;
   			    	    String tempJsFile = prms[i].getTableName();
              			if( (tempJsFile != null) && (!tempJsFile.trim().equals("")) && 
              					(!tempJsFile.equalsIgnoreCase(jsFile)) && tempJsFile.contains(".js") )
              			{
              				jsFile = tempJsFile;
              				%><script type="text/javascript" src="<%=tempJsFile%>"></script>
			<%
              			}

          			String jsFunctions = prms[i].getColumnName();
        			String onChangeEvent = "";
        			String onClickEvent = "";
        			String onBlurEvent = "";
        			StringBuffer tempAttribText = new StringBuffer("");
        			String jsAttribText = "";
        			String disable  = "";
        			int prmtType = prms[i].getParameterType();
        			boolean dateControl = (prmtType == 0 || prmtType == 2 ) ? true : false;
        			if(jsFunctions != null && !jsFunctions.trim().equalsIgnoreCase(""))
        			{
        				String tArray[] = jsFunctions.split("~");
        				for(int ta=0;ta<tArray.length;ta++)
        				{
        					String ele = tArray[ta];
        					String tempEle[] = ele.split("=");
        					String eleValue = (tempEle.length>=2 ? tempEle[1].trim() : "");
        					if(ele.toLowerCase().contains("onblur"))
        					{
        						onBlurEvent = eleValue;
        					}
        					else if(ele.toLowerCase().contains("onchange"))
        					{
        						onChangeEvent = eleValue;
        					}
        					else if(ele.toLowerCase().contains("onclick"))
        					{
        						onClickEvent = eleValue;
        					}
        					else if(ele.toLowerCase().contains("disable"))
        					{
        						disable = eleValue;
        					}
        					
        					// Added for date control to resolve problem when column_name of SGVC_REPORT_PARA have invalid entry.
        					if(dateControl)
        					{
        						if(ele.contains("maxvalue"))
            					{
            						tempAttribText.append(ele + " ");
            					}
            					else if(ele.contains("minvalue"))
            					{
            						tempAttribText.append(ele + " ");
            					}
            					else if(ele.contains("disabled"))
            					{
            						tempAttribText.append(ele + " ");
            					}
            					else if(ele.contains("afterDateSelect"))
            					{
            						tempAttribText.append(ele + " ");
            					}
            					else if(ele.contains("showCurrentDate"))
            					{
            						tempAttribText.append(ele + " ");
            					}
            					else if(ele.contains("default"))
            					{
            						tempAttribText.append(ele + " ");
            					}
        					}
        					
        				}
        				if(dateControl) jsAttribText = tempAttribText.toString();
        			}
          			if (multiColumnEnable) {
          				if (i == 0) {
          %>
		
		<tr>
			<%
              } else if (k % 2 == 0 || cnt == 1) {
              %>
		</tr>
		<tr>
			<%
              				}
              				if (prms[i].getParameterType() == IReportConstants.DATE_RANGE_INPUT
              				|| prms[i].getParameterType() == IReportConstants.TIME_RANGE_INPUT
              				|| prms[i].getParameterType() == IReportConstants.WIDE_DROPDOWN_INPUT
              				|| prms[i].getParameterType() == IReportConstants.WIDE_DROPDOWN_OPTGRP_INPUT /* Added by Divyesh on May 20, 2009 for OPTGROUP option of Dropdown parameter */
              				|| prms[i].getParameterType() == IReportConstants.NUMBER_RANGE_INPUT
              				|| prms[i].getParameterType() == IReportConstants.COMPARE_NUMBER
              				|| prms[i].getParameterType() == IReportConstants.DECIMAL_RANGE_INPUT
              				|| prms[i].getParameterType() == IReportConstants.COMPARE_DECIMAL
              				|| prms[i].getParameterType() == IReportConstants.OPTION_INPUT
              				|| prms[i].getParameterType() == IReportConstants.CHECKBOX_INPUT
              				|| prms[i].getParameterType() == IReportConstants.DIVISION_BAR
              				|| prms[i].getParameterType() == IReportConstants.INCLUDE_JSP_AS_INPUT) 
              				{
              			cnt = 1;
              			k++;
              			if (k % 2 == 0)
              				k++;
              %>
		</tr>
		<tr>
			<%
                			} else {
                			cnt = 0;
                				}

                				if (prms[i].getParameterType() == IReportConstants.HIDDEN) {
                			k--;
                				}
                			} // End multiColumnEmnable Check
                			else {
                %>
		
		<tr>
			<%
             			}
             			//End Updation By Dhaval Modi on 10/05/2006

             			//Added value to each field to preserve the contents of fields when page gets reloaded after selection of item for dependent combos(Samir)

             			switch (prms[i].getParameterType()) 
             			{
             			case IReportConstants.TEXT_INPUT:
             			case IReportConstants.EMAIL_INPUT:
             %>
			<td>&nbsp;</td>
			<td class="${labelcss}"><%=prms[i].getParameterDisplayName()%> <%
                				out
                				.print(prms[i].isMandatory() ? "<FONT COLOR=RED>*</FONT>"
                				: "");
                %></td>
			<td>&nbsp;:</td>

			<td><input type="text" name="<%=prms[i].getParameterName()%>"
				size="20" maxlength="50"
				value="<%if(rvo.getParameterValue(prms[i].getParameterName())!= null){%><%=rvo.getParameterValue(prms[i].getParameterName())%><%}%>"
				<%if((rvo.getParameterValue(prms[i].getParameterName()) != null) && (!"".equals(rvo.getParameterValue(prms[i].getParameterName()))))
     			{
          			out.print(" readOnly = true ");
       			}
            %>
				class="${textcss}" onchange="<%=onChangeEvent%>"
				onblur="<%=onBlurEvent%>"></td>

			<td>&nbsp;</td>
			<%-- Start Updation By Dhaval Modi --%>
			<%
              if (!multiColumnEnable) {
              %>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<%
               				}
               				//End Updation By Dhaval Modi 
               				break;

               			case IReportConstants.NUMBER_INPUT:
               			case IReportConstants.DECIMAL_INPUT:
               				isDecimal = (prms[i].getParameterType() == IReportConstants.DECIMAL_INPUT);
               %>
			<td>&nbsp;</td>
			<td class="${labelcss}"><%=prms[i].getParameterDisplayName()%> <%
                				out
                				.print(prms[i].isMandatory() ? "<FONT COLOR=RED>*</FONT>"
                				: "");
                %></td>
			<td>&nbsp;:</td>
			<td><input type="text" name="<%=prms[i].getParameterName()%>"
				size="20" maxlength="12"
				value="<%if(rvo.getParameterValue(prms[i].getParameterName())!= null){%><%=rvo.getParameterValue(prms[i].getParameterName())%><%}%>"
				<% 	if((rvo.getParameterValue(prms[i].getParameterName()) != null) && (!"".equals(rvo.getParameterValue(prms[i].getParameterName()))))
     			{
          			out.print(" readOnly = true ");
       			}
            %>
				onKeyPress="return isNumber(this,event,<%=isDecimal%>)"
				class="${textcss}" onblur="<%=onBlurEvent%>"
				onchange="<%=onChangeEvent%>"></td>
			<td>&nbsp;</td>
			<%-- Start Updation By Dhaval Modi --%>
			<%
              if (!multiColumnEnable) {
              %>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<%
               				}
               				//End Updation By Dhaval Modi 
               				break;

               			case IReportConstants.DATE_INPUT:
               %>
			<td>&nbsp;</td>
			<td class="${labelcss}"><%=prms[i].getParameterDisplayName()%> <%
                				out
                				.print(prms[i].isMandatory() ? "<FONT COLOR=RED>*</FONT>"
                				: "");
                %></td>
			<td>&nbsp;:</td>
			<%
            				dv = "";
            				if (rvo.getParameterValue(prms[i]
            				.getParameterName()) != null) {
            			dv = (String) rvo.getParameterValue(prms[i]
            					.getParameterName());
            			dv = (dv != null && !dv.trim().equals("")) ? sdf2
            					.format(sdf.parse(dv))
            					: "";
            				}
            %>
			<td><hdiits:dateTime name="<%=prms[i].getParameterName()%>"
					classcss="${datecss}" default="<%=dv%>"
					caption="<%=prms[i].getParameterDisplayName()%>"
					bundle="${sgvLabelsReports}" attributesText='<%=jsAttribText%>' />
			</td>
			<td>&nbsp;</td>
			<%-- Start Updation By Dhaval Modi --%>
			<%
              if (!multiColumnEnable) {
              %>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<%
               				}
               				//End Updation By Dhaval Modi           
               				break;

               			/*** Added By Niteshwar on 17-08-07 for Time Parameter **/

               			case IReportConstants.TIME_INPUT:
               %>
			<td>&nbsp;</td>
			<td class="${labelcss}"><%=prms[i].getParameterDisplayName()%> <%
                    				out
                    				.print(prms[i].isMandatory() ? "<FONT COLOR=RED>*</FONT>"
                    				: "");
                    %></td>
			<td>&nbsp;:</td>
			<%
                				dv = "";
                				if (rvo.getParameterValue(prms[i]
                				.getParameterName()) != null) {
                			dv = (String) rvo.getParameterValue(prms[i]
                					.getParameterName());
                			//dv = (dv != null && !dv.trim().equals("")) ? sdf2.format( sdf.parse(dv)) : ""; 
                				}
                %>
			<td><hdiits:time name="<%=prms[i].getParameterName()%>"
					classcss="${timecss}" default="<%=dv%>"
					caption="<%=prms[i].getParameterDisplayName()%>"
					bundle="${sgvLabelsReports}" /></td>
			<td>&nbsp;</td>
			<%
                if (!multiColumnEnable) {
                %>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<%
                   				}

                   				break;

                   			/** *****************End Case For Time input **************************************/

               /*** Added By Niteshwar on 05-01-08 for Search Parameter **/

		    case IReportConstants.SEARCH_INPUT:
		     %>
			<td>&nbsp;</td>
			<td class="${labelcss}"><%=prms[i].getParameterDisplayName()%> <%
		    	out	.print(prms[i].isMandatory() ? "<FONT COLOR=RED>*</FONT>" : "");
		    %></td>
			<td>&nbsp;:</td>
			<%
			    dv = "";
			    if (rvo.getParameterValue(prms[i].getParameterName()) != null) 
			    {
			    	dv = (String) rvo.getParameterValue(prms[i].getParameterName());
			    }
		    %>
			<td>
				<%
			   String defSearchValue = "";
			   if(rvo.getParameterValue("name_" + prms[i].getParameterName())!= null)
			   {
				   defSearchValue = (String) rvo.getParameterValue("name_"+ prms[i].getParameterName());
			   }
			   String displayTextId = "";
			   if(rvo.getParameterValue(prms[i].getParameterName())!= null)
			   {
				   displayTextId = (String) rvo.getParameterValue(prms[i].getParameterName());
			   }
			%> <hdiits:search id="nameSearch"
					name="<%=prms[i].getParameterName()%>"
					caption="<%=prms[i].getParameterDisplayName()%>"
					url="<%= prms[i].getMethodArgs()%>" readonly="true" size="35"
					onblur="<%=onBlurEvent%>" onchange="<%=onChangeEvent%>"
					onclick="<%= onClickEvent %>" /> <script language="javascript">
			document.getElementsByName('name_<%=prms[i].getParameterName()%>')[0].value='<%=defSearchValue%>';
			document.getElementById('<%=prms[i].getParameterName()%>').value='<%=displayTextId%>';
			</script>
			</td>
			<td>&nbsp;</td>
			<%
		    	if (!multiColumnEnable) 
		    	{
		     %>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<%
				}
			break;

/** *****************End Case For Search Input **************************************/

                       			case IReportConstants.NUMBER_RANGE_INPUT:
                       			case IReportConstants.DECIMAL_RANGE_INPUT:
                       				isDecimal = (prms[i].getParameterType() == IReportConstants.DECIMAL_RANGE_INPUT);
                       %>
			<td>&nbsp;</td>
			<td class="${labelcss}"><%=prms[i].getParameterDisplayName()%> <%
                				out
                				.print(prms[i].isMandatory() ? "<FONT COLOR=RED>*</FONT>"
                				: "");
                %></td>
			<td>&nbsp;:</td>
			<td class="${labelcss}"><input type="text"
				name="<%=prms[i].getParameterName()%>from" size="20" maxlength="9"
				value='<%if(rvo.getParameterValue(prms[i].getParameterName()+"from")!= null){%><%=rvo.getParameterValue(prms[i].getParameterName()+"from")%><%}%>'
				<% 	if((rvo.getParameterValue(prms[i].getParameterName()+"from") != null) && (!"".equals(rvo.getParameterValue(prms[i].getParameterName()+"from"))))
     			{
          			out.print(" readOnly = true ");
       			}
            %>
				onKeyPress="return isNumber(this,event,<%=isDecimal%>)"
				class="${textcss}" onblur="<%= onBlurEvent %>"
				onchange="<%= onChangeEvent %>"> &nbsp;<%=localStringsBundle.getString("Report.label.append.from")%></td>
			<td>&nbsp;</td>
			<%-- Start Added by Dhaval Modi --%>
			<%
             if (multiColumnEnable) {
             %>
			<td>&nbsp;</td>
			<%
            }
            %>
			<%-- End Added by Dhaval Modi --%>
			<td class="${labelcss}">&nbsp;<%=localStringsBundle
														.getString("Report.label.prepend.to")%> <%
                				out
                				.print(prms[i].isMandatory() ? "<FONT COLOR=RED>*</FONT>"
                				: "");
                %>
			</td>
			<td>&nbsp;:</td>
			<td class="${labelcss}"><input type="text"
				name="<%=prms[i].getParameterName()%>to" size="20" maxlength="9"
				value='<%if(rvo.getParameterValue(prms[i].getParameterName()+"to")!= null){%><%=rvo.getParameterValue(prms[i].getParameterName() +"to")%><%}%>'
				<% 	if((rvo.getParameterValue(prms[i].getParameterName()+"to") != null) && (!"".equals(rvo.getParameterValue(prms[i].getParameterName()+"to"))))
     			{
          			out.print(" readOnly = true ");
       			}
            %>
				onKeyPress="return isNumber(this,event,<%=isDecimal%>)"
				class="${textcss}" onblur="<%= onBlurEvent %>"
				onchange="<%= onChangeEvent %>">&nbsp;<%=localStringsBundle
														.getString("Report.label.append.to")%></td>
			<td>&nbsp;</td>
			<%
            			break;

                       			case IReportConstants.DATE_RANGE_INPUT:
                       	            %>
			<td>&nbsp;</td>
			<%
                       	            				String fromLabel = localStringsBundle
                       	            				.getString("Report.label.prepend.from");
                       	            				fromLabel = (fromLabel != null) ? fromLabel
                       	            				.trim() : "";
                       	            				if (!fromLabel.equals("")) {
                       	            			fromLabel = fromLabel + " ";
                       	            				}
                       	            %>
			<td class="${labelcss}"><%=fromLabel%><%=prms[i].getParameterDisplayName()%>
				<%
                       	                				out
                       	                				.print(prms[i].isMandatory() ? "<FONT COLOR=RED>*</FONT>"
                       	                				: "");
                       	                %></td>
			<td>&nbsp;:</td>
			<td class="${labelcss}">
				<%
                       	            				fromdv = "";
                       	            				if (rvo.getParameterValue(prms[i]
                       	            				.getParameterName()
                       	            				+ "from") != null) {
                       	            			fromdv = (String) rvo
                       	            					.getParameterValue(prms[i]
                       	            					.getParameterName()
                       	            					+ "from");
                       	            			fromdv = (fromdv != null && !fromdv.trim()
                       	            					.equals("")) ? sdf2.format(sdf
                       	            					.parse(fromdv)) : "";
                       	            				}
                       	             boolean disabledDateTime=false;
	                       	         if((session.getAttribute("searchWithInSearch" + request.getParameter("reportCode")) != null) && (session.getAttribute("searchWithInSearch" + request.getParameter("reportCode")).toString().equalsIgnoreCase("true")))
    	                   	         {
	                       	        	disabledDateTime = true;
            	           	         }
	                       	         else
	                       	         {
	                       	        	disabledDateTime = false;
	                       	         }
                       	            %> <hdiits:dateTime
					attributesText="<%=jsAttribText%>" classcss="${datecss}"
					name='<%=prms[i].getParameterName()+"from"%>' default="<%=fromdv%>"
					caption="<%=prms[i].getParameterDisplayName()%>"
					bundle="${sgvLabelsReports}" disabled="<%=disabledDateTime%>"></hdiits:dateTime>&nbsp;<%=localStringsBundle
                       															.getString("Report.label.append.from")%>
			</td>
			<td>&nbsp;</td>
			<%-- Start Added by Dhaval Modi --%>
			<%
                       	              if (multiColumnEnable) {
                       	              %>
			<td>&nbsp;</td>
			<%
                       	            }
                       	            %>
			<%--End Added by Dhaval Modi --%>
			<td class="${labelcss}"><%=localStringsBundle
                       															.getString("Report.label.prepend.to")%><%=prms[i].getParameterDisplayName()%>
				<%
                       	                				out
                       	                				.print(prms[i].isMandatory() ? "<FONT COLOR=RED>*</FONT>"
                       	                				: "");
                       	                %></td>
			<td>&nbsp;:</td>
			<%
                       	            				String toDate = "";
                       	            				if (rvo.getParameterValue(prms[i]
                       	            				.getParameterName()
                       	            				+ "to") != null) {
                       	            			toDate = (String) rvo
                       	            					.getParameterValue(prms[i]
                       	            					.getParameterName()
                       	            					+ "to");
                       	            			todv = (toDate != null && !toDate.trim()
                       	            					.equals("")) ? sdf2.format(sdf
                       	            					.parse(toDate)) : "";
                       	            				} else if (prms[i].isMandatory()) {
                       	            			//initialize toDate with default date if the date range is mandatory
                       	            			toDate = sdf.format(new Date());
                       	            			todv = (toDate != null && !toDate.trim()
                       	            					.equals("")) ? sdf2.format(sdf
                       	            					.parse(toDate)) : "";
                       	            				}
                       	            %>
			<td class="${labelcss}"><hdiits:dateTime
					attributesText="<%=jsAttribText%>" classcss="${datecss}"
					name='<%=prms[i].getParameterName()+"to"%>' default="<%=todv%>"
					caption="<%=prms[i].getParameterDisplayName()%>"
					bundle="${sgvLabelsReports}"></hdiits:dateTime>&nbsp;<%=localStringsBundle
                       															.getString("Report.label.append.to")%>
			</td>
			<td>&nbsp;</td>
			<%
                       	            			break;

            			//***************Added case for TIME_RANGE_INPUT by Niteshwar on 20-07-2007 ************************//

            			case IReportConstants.TIME_RANGE_INPUT:
            %>
			<td>&nbsp;</td>
			<%
                				String fromLabell = localStringsBundle
                				.getString("Report.label.prepend.from");
                				fromLabell = (fromLabell != null) ? fromLabell
                				.trim() : "";
                				if (!fromLabell.equals("")) {
                			fromLabell = fromLabell + " ";
                				}
                %>
			<td class="${labelcss}"><%=fromLabell%><%=prms[i].getParameterDisplayName()%>
				<%
                    				out
                    				.print(prms[i].isMandatory() ? "<FONT COLOR=RED>*</FONT>"
                    				: "");
                    %></td>
			<td>&nbsp;:</td>
			<td class="${labelcss}">
				<%
                				fromdv = "";
                				if (rvo.getParameterValue(prms[i]
                				.getParameterName()
                				+ "from") != null) {
                			fromdv = (String) rvo
                					.getParameterValue(prms[i]
                					.getParameterName()
                					+ "from");
                				}
                %> <hdiits:time
					name='<%=prms[i].getParameterName()+"from"%>' classcss="${timecss}"
					default="<%=fromdv%>" captionid="FIR.Time" /> &nbsp;<%=localStringsBundle
														.getString("Report.label.append.from")%>

			</td>
			<td>&nbsp;</td>
			<%-- Start Added by Dhaval Modi --%>
			<%
                  if (multiColumnEnable) {
                  %>
			<td>&nbsp;</td>
			<%
                }
                %>
			<%-- End Added by Dhaval Modi --%>
			<td class="${labelcss}"><%=localStringsBundle
														.getString("Report.label.prepend.to")%><%=prms[i].getParameterDisplayName()%>
				<%
                    				out
                    				.print(prms[i].isMandatory() ? "<FONT COLOR=RED>*</FONT>"
                    				: "");
                    %></td>
			<td>&nbsp;:</td>
			<%
                				String toTime = "";
                				if (rvo.getParameterValue(prms[i]
                				.getParameterName()
                				+ "to") != null) {
                			toTime = (String) rvo
                					.getParameterValue(prms[i]
                					.getParameterName()
                					+ "to");
                				} else if (prms[i].isMandatory()) {
                				}
                %>
			<td class="${labelcss}"><hdiits:time
					name='<%=prms[i].getParameterName()+"to"%>' classcss="${timecss}"
					default="<%=toTime%>" captionid="FIR.Time"></hdiits:time>&nbsp;<%=localStringsBundle
														.getString("Report.label.append.to")%></td>
			<td>&nbsp;</td>

			<%
                			break;
                			//  ****************************************End Case ********************************************

                			//Added onchange function in case of dependent combos
                			case IReportConstants.DROPDOWN_INPUT:
                			case IReportConstants.WIDE_DROPDOWN_INPUT:
                			case IReportConstants.DROPDOWN_OPTGRP_INPUT:
                			case IReportConstants.WIDE_DROPDOWN_OPTGRP_INPUT: /* Added by Divyesh on May 20, 2009 for OPTGROUP option of Dropdown parameter */	
                				boolean wideCombo = (prms[i].getParameterType() == IReportConstants.WIDE_DROPDOWN_INPUT || 
   								     				 prms[i].getParameterType() == IReportConstants.WIDE_DROPDOWN_OPTGRP_INPUT); /* Added by Divyesh on May 20, 2009 for OPTGROUP option of Dropdown parameter */	                			
                %>
			<td>&nbsp;</td>
			<td class="${labelcss}"><%=prms[i].getParameterDisplayName()%> <%
                				out
                				.print(prms[i].isMandatory() ? "<FONT COLOR=RED>*</FONT>"
                				: "");
                %></td>
			<td>&nbsp;:</td>
			<%-- Start Added By Dhaval Modi --%>
			<%
               if (multiColumnEnable) {
               %>
			<td colspan="<%=((wideCombo) ? 6 : 1) %>">
				<%
               } else {
               %>
			
			<td colspan="<%=((wideCombo) ? 5 : 1)%>">
				<%
              }
//            Added by Brijesh Prajapati to populate dependent DropDown box using ajax:select tag.
              ArrayList childList = rvo.getChildParameters(prms[i].getParameterName());
              if(childList != null)
          	  {
	          		int childListSize = childList.size();
	          		if(childListSize > 0)
	      			{
						String source = prms[i].getParameterName();
						String target = "";
						ReportParameterVO reportParamVO = null;
						String parentList[] = null;
						String parents = "";
						String temp ="";
						String eventType = "change";
						String postFun = "";
							
						for(int childCnt=0;childCnt<childListSize;childCnt++)
						{
							target = childList.get(childCnt).toString();
							temp = "";
							reportParamVO = (ReportParameterVO) childList.get(childCnt);
							parentList = reportParamVO.getParentParameters();
							parents = "";
							String refreshFun = "";

							for(int t=0;t<parentList.length;t++)
							{
								parents = parents + "~" + parentList[t] ;
							}
							for(int x=0;x<prms.length;x++)
		              		{
		              			if(target.equals(prms[x].getParameterName()))
		              			{
		              				if(prms[x].getParameterType() == 21)
		              				{
		              						String method_name = prms[x].getColumnName();
		              						if(method_name != null && !method_name.equalsIgnoreCase(""))
		              						{
					              				String tempArray[] = method_name.split(",");
					              				for(int y=0;y<tempArray.length;y++)
					              				{
					              					String tempElement = tempArray[y];
					              					if(tempElement.toLowerCase().contains("r:")) refreshFun = tempElement.split(":")[1];
					              				}
					              				if(refreshFun.indexOf("(") != -1) refreshFun = refreshFun.substring(0,refreshFun.indexOf("("));
				              					postFun = refreshFun;
				              					target = "rpt_" + target;
				              					%> <input type="hidden" id="rpt_<%=target%>"
				name="<%=target%>"> <%
		              						}
		              				}
		              			}
		              		}
							temp = postFun + "~" + reportParamVO.getParameterType() + "~" + eventType + "~" + source + "~" + target + parents; 
							populateChilDropDownList.add(temp);
						}
						if(onChangeEvent != null && !onChangeEvent.equals(""))
		          		 {
							 if(onChangeEvent.indexOf("(") != -1)
							 onChangeEvent = onChangeEvent.substring(0,onChangeEvent.indexOf("("));
							 postFun = onChangeEvent;
							 parents = "";
		          			 target = "rptOnChange_" +prms[i].getParameterName();
		          			 temp = postFun + "~" + reportParamVO.getParameterType() + "~" + eventType + "~" + source + "~" + target + parents; 
		          			 populateChilDropDownList.add(temp);
		          			 onChangeEvent="";
		          			 %> <input type="hidden" id="<%=target%>"
				name="<%=target%>"> <%
		          		 }
						}
	          	}

              %> <%-- End Added By Dhaval Modi --%> <%-- <SELECT class="${selectcss}" style="width: <%=((wideCombo)? "100%" : "155")%>;" size="1" name="<%=prms[i].getParameterName()%>" id="<%=prms[i].getParameterName()%>" attrTitle="<%=prms[i].getParameterName()%>" type="select-one" <%if(rvo.getChildParameters(prms[i].getParameterName()) != null && rvo.getChildParameters(prms[i].getParameterName()).size() > 0){%> onChange="populateChildDropDown(this.name,this.value)"<%}%> > --%>
				<SELECT class="${selectcss}" size="1"
				name="<%=prms[i].getParameterName()%>"
				id="<%=prms[i].getParameterName()%>"
				attrTitle="<%=prms[i].getParameterName()%>" type="select-one"
				onchange=" <%= onChangeEvent %>" onclick=" <%= onClickEvent %>"
				onblur=" <%= onBlurEvent %>">

					<option value="-1" selected><%=localStringsBundle
														.getString("Report.dropdown.select")%></option>
					<%
              
                	//--------------------------------------------------------------------------------------
                	
                	if (prms[i].getParameterType() == IReportConstants.DROPDOWN_INPUT ||
             			prms[i].getParameterType() == IReportConstants.WIDE_DROPDOWN_INPUT)
             		{			
                				Collection comboData = (Collection) session.getAttribute(prms[i].getParameterName()+ "Data");
                	
                				Iterator itr = (comboData != null) ? comboData
                				.iterator() : null;
                				if (itr != null) {
                			int j = 0;
                			while (itr.hasNext()) {
                				ComboItem ci = (ComboItem) itr.next();
                				if (j == 0) {
                					if (ci
                					.getComboItemId()
                					.equals(
                					rvo
                							.getParameterValue(prms[i]
                							.getParameterName()))) {
                				out.print("<option value=\""
                						+ ci.getComboItemId()
                						+ "\" selected >"
                						+ ci.getComboItemDesc()
                						+ "</option>");
                					} else {
                				out.print("<option value=\""
                						+ ci.getComboItemId()
                						+ "\" >"
                						+ ci.getComboItemDesc()
                						+ "</option>");
                					}
                					j++;
                				} else {
                					if (ci
                					.getComboItemId()
                					.equals(
                					rvo
                							.getParameterValue(prms[i]
                							.getParameterName()))) {
                				out.print("<option value=\""
                						+ ci.getComboItemId()
                						+ "\" selected >"
                						+ ci.getComboItemDesc()
                						+ "</option>");
                					} else {
                				out.print("<option value=\""
                						+ ci.getComboItemId()
                						+ "\" >"
                						+ ci.getComboItemDesc()
                						+ "</option>");
                					}
                				}
                			}
                				} else {
                				}
             		}
            		else if (prms[i].getParameterType() == IReportConstants.DROPDOWN_OPTGRP_INPUT ||
        			   		 prms[i].getParameterType() == IReportConstants.WIDE_DROPDOWN_OPTGRP_INPUT)
					{
						GroupedComboData groupedComboData = (GroupedComboData) session.getAttribute(prms[i].getParameterName()+ "Data");
		            	
		            	if (groupedComboData != null)
		            	{
    			            Map comboMapData = (Map) groupedComboData.getMpGropedComboData();
    			            boolean bAddSuffixInValue = groupedComboData.isAddSuffixInValue();
    			            	
    						if (comboMapData != null)
    						{
    							Set optGroupKeySet = comboMapData.keySet();
        						
        						Iterator groupItr = (optGroupKeySet != null)? optGroupKeySet.iterator() : null;
        						
        						if (groupItr != null) 
        						{
        							int j = 1;
        							
        							Collection comboData = null;
        							Iterator itr = null;
        							
        							while (groupItr.hasNext()) 
        							{
        								String optGroupKey = String.valueOf(groupItr.next());
        								
        								out.print("<optgroup label=\""+ optGroupKey +"\">");
        								
        								comboData = (Collection)comboMapData.get(optGroupKey);
        								
        								itr = (comboData != null) ? comboData
	                            				.iterator() : null;
	                            				if (itr != null) {
			                            			while (itr.hasNext()) 
			                            			{
			                            				ComboItem ci = (ComboItem) itr.next();
			                            				if ((ci.getComboItemId() + (bAddSuffixInValue ? (IReportConstants.OPTGRP_VALUE_SUFFIX + j) : "")).equals(rvo.getParameterValue(prms[i].getParameterName()))) 
			                            				{
				                            				out.print("<option value=\""
				                            						+ ci.getComboItemId() + (bAddSuffixInValue ? (IReportConstants.OPTGRP_VALUE_SUFFIX + j) : "")
				                            						+ "\" selected >"
				                            						+ ci.getComboItemDesc()
				                            						+ "</option>");
		                            					 } else {
					                            				out.print("<option value=\""
					                            						+ ci.getComboItemId() + (bAddSuffixInValue ? (IReportConstants.OPTGRP_VALUE_SUFFIX + j) : "")
					                            						+ "\" >"
					                            						+ ci.getComboItemDesc()
					                            						+ "</option>");
			                            				  }
			                            			}
	                            				} 
	                            				out.print("</optgroup>");
	                            				j++;
        							}
        						}
    						}
		            	}
					}
                %>
			</select>
			</td>
			<% if((session.getAttribute("searchWithInSearch" + request.getParameter("reportCode")) != null) && (session.getAttribute("searchWithInSearch" + request.getParameter("reportCode")).toString().equalsIgnoreCase("true"))) 
           {
        	%>
			<script>
        			if((document.getElementById("<%=prms[i].getParameterName()%>") != null) && (document.getElementById("<%=prms[i].getParameterName()%>").options.selectedIndex > 0))
        			{
        				document.getElementById("<%=prms[i].getParameterName()%>").disabled = true;
        				arrOfCombos.push(document.getElementById("<%=prms[i].getParameterName()%>"));
        			}
        		</script>
			<% 	   
           }
           	if (!wideCombo) {
            			//Start Updation By Dhaval Modi               
            			if (!multiColumnEnable) {
            %>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<%
               			}
               			//End Updation By Dhaval Modi              
               				}
               %>
			<td>&nbsp;</td>
			<%
            			break;
            
            				// Added by Brijesh Prajapati for Radio Control...
                			case IReportConstants.OPTION_INPUT:
                		          
                		          %>
			<td>&nbsp;</td>
			<td valign="top" class="${labelcss}"><%=prms[i].getParameterDisplayName()%>
				<%
                		                out.print( prms[i].isMandatory() ? "<FONT COLOR=RED>*</FONT>" : "" );
                		                %></td>
			<td valign="top">&nbsp;:</td>
			<td valign="top" colspan="6">
				<table border="0" cellspacing="5" cellpadding="5" width="100%">
					<%   
                		        	    String name = prms[i].getParameterName();
                		        	    Collection radioData = (Collection)session.getAttribute( prms[i].getParameterName() + "Data" );
                		    	        Iterator radioItr = (radioData != null) ? radioData.iterator() : null;
                		    	        
                		    	        if( radioItr != null )
                		                {
                		    	        	while( radioItr.hasNext() )
                		                    {
                		                      ComboItem ci = (ComboItem)radioItr.next();
                		                	  String radioID = ci.getComboItemId();
                		                	  String radioValue = (String) rvo.getParameterValue(name);
                		                      out.print("<tr>");		
                		                      if(radioID.equals(radioValue))
                		                      {
                		                    	  out.print("<td valign='top' ><INPUT TYPE=RADIO id='" + name + "'  NAME='"+ name +"' VALUE='" + radioID + "' onclick='"+onClickEvent+"' checked='checked' >" + ci.getComboItemDesc() + "</td>");
                		                      }
                		                      else
                		                      {
                		                    	  out.print("<td valign='top' ><INPUT TYPE=RADIO NAME='"+ name +"' VALUE='" + radioID + "' onclick='"+onClickEvent+"' >" + ci.getComboItemDesc() + "</td>");                		                    	  
                		                      }
                		                      out.print("<td>&nbsp;</td>");
                		                      out.print("<td>&nbsp;</td>");
                		                      if(radioItr.hasNext())
                		                      {
                		                    	  ci = (ComboItem)radioItr.next();
                		                    	  radioID = ci.getComboItemId();
                		                    	  if(radioID.equals(radioValue))
                		                    	  {
                		                    		  out.print("<td valign='top'><INPUT TYPE=RADIO id='"+ name +"' NAME='"+ name +"' VALUE='" + radioID + "' onclick='"+onClickEvent+"' checked='checked' >" + ci.getComboItemDesc() + "</td>");
                		                    	  }
                		                    	  else
                		                    	  {
                		                    		  out.print("<td valign='top'><INPUT TYPE=RADIO NAME='"+ name +"' VALUE='" + radioID + "' onclick='"+onClickEvent+"' >" + ci.getComboItemDesc() + "</td>");  
                		                    	  }
                		                      }
                		                      else out.print("<td>&nbsp;</td>");
                		                      out.print("<td>&nbsp;</td>");
                		                      if(radioItr.hasNext())
                		                      {
                		                    	  ci = (ComboItem)radioItr.next();
                		                    	  radioID = ci.getComboItemId();
                		                    	  if(radioID.equals(radioValue))
                		                    	  {
                		                    		  out.print("<td valign='top'><INPUT TYPE=RADIO id='" + name + "' NAME='"+ name +"' VALUE='" + radioID + "' onclick='"+onClickEvent+"' checked='checked' >" + ci.getComboItemDesc() + "</td>");
                		                    	  }
                		                    	  else
                		                    	  {
                		                    		  out.print("<td valign='top'><INPUT TYPE=RADIO NAME='"+ name +"' VALUE='" + radioID + "' onclick='"+onClickEvent+"' >" + ci.getComboItemDesc() + "</td>");  
                		                    	  }
                		                     	  
                		                      }
                		                      else out.print("<td>&nbsp;</td>");
                		                      out.print("</tr>");		
                		                	}
                		    	        	
   										 if((session.getAttribute("searchWithInSearch" + request.getParameter("reportCode")) != null) && (session.getAttribute("searchWithInSearch" + request.getParameter("reportCode")).toString().equalsIgnoreCase("true"))) 
   	      		                          {
                 		        	 		 %>
					<script>
   	              		        	 		var flagForYN=false;
   	              		        	 		for(var iCnt=0;iCnt<ReportForm.<%=name%>.length;iCnt++)
   	              		        	 		{
   	              		        	 			if(ReportForm.<%=name%>[iCnt].checked)
   	              		        	 			{
   		              		        	 			flagForYN = true;
   		              		        	 			break;
   	              		        	 			}
   	              		        	 		}

   	              		        	 		if(flagForYN)
   	              		        	 		{
   		              		        	 		for(var iCnt=0;iCnt<ReportForm.<%=name%>.length;iCnt++)
   		              		        	 		{
   		              		        	 			ReportForm.<%=name%>[iCnt].disabled = true;
   		              		        	 			arrOfRadioButtons.push(ReportForm.<%=name%>[iCnt]);
   		              		        	 		}
   	              		        	 		}
                 		        	 		 </script>
					<%
         		                          }
   							
             			                 }
                		                    
                		    	        %>
				</table>
			</td>


			<td>&nbsp;</td>
			<% 
                		            	break;
                		    	        
                		// Added by Brijehs Prajapati for Checkbox...
                		
                			case IReportConstants.CHECKBOX_INPUT:
              		          
              		          %>
			<td>&nbsp;</td>
			<td valign="top" class="${labelcss}"><%=prms[i].getParameterDisplayName()%>
				<%
              		                out.print( prms[i].isMandatory() ? "<FONT COLOR=RED>*</FONT>" : "" );
              		                %></td>
			<td valign="top">&nbsp;:</td>
			<td valign="top" colspan="6">
				<table border="0" cellspacing="5" cellpadding="5" width="100%">
					<% 
              		        	    
	              		        	  Object listCheckBoxes = rvo.getParameterValue(prms[i].getParameterName());
	
	              		        	  List listitems_CheckBox = null;
	
	              		        	  if (listCheckBoxes != null) 
	              		        	  {
	              		        		listitems_CheckBox = Arrays.asList((String[]) listCheckBoxes);
	              		        	  } 
	              		        	  else 
	              		        	  {
	              		        		listitems_CheckBox = new ArrayList();
	              		        	  }

              		        	    String nameChk = prms[i].getParameterName();
              		        	    Collection chkData = (Collection)session.getAttribute( prms[i].getParameterName() + "Data" );
              		    	        Iterator chkItr = (chkData != null) ? chkData.iterator() : null;
              		    	        if( chkItr != null )
              		                {
              		    	        	while( chkItr.hasNext() )
              		                    {
              		                      ComboItem ciChk = (ComboItem)chkItr.next();
              		                      String checkID = ciChk.getComboItemId();
              		                      //String[] checkVavlue_1  = (String[]) rvo.getParameterValue(nameChk);
              		                     // System.out.println(" ################3 " + checkVavlue_1.length);
              		                      //String checkVavlue = (String) rvo.getParameterValue(nameChk);
              		                      out.print("<tr>");		
              		                      //if(checkID.equals(checkVavlue))
              		                      if (listitems_CheckBox.contains(checkID)) 
              		                      {
              		                    	out.print("<td valign='top' ><INPUT TYPE=CHECKBOX id='" +nameChk+ "' NAME='"+ nameChk +"' VALUE='" + checkID + "' onclick='"+onClickEvent+"' checked='checked' >" + ciChk.getComboItemDesc() + "</td>");
              		                      }
              		                      else
              		                      {
              		                    	out.print("<td valign='top' ><INPUT TYPE=CHECKBOX NAME='"+ nameChk +"' VALUE='" + checkID + "' onclick='"+onClickEvent+"' >" + ciChk.getComboItemDesc() + "</td>");  
              		                      }
              		                      
              		                      out.print("<td>&nbsp;</td>");
              		                      out.print("<td>&nbsp;</td>");
              		                      if(chkItr.hasNext())
              		                      {
              		                          ciChk = (ComboItem)chkItr.next();
              		                          checkID = ciChk.getComboItemId();
              		                          //if(checkID.equals(checkVavlue))
              		                          if (listitems_CheckBox.contains(checkID)) 
              		                          {
              		                        	out.print("<td valign='top'><INPUT TYPE=CHECKBOX id='" +nameChk+ "' NAME='"+ nameChk +"' VALUE='" + checkID + "' onclick='"+onClickEvent+"' checked='checked' >" + ciChk.getComboItemDesc() + "</td>");
              		                          }
              		                          else
              		                          {
              		                        	out.print("<td valign='top'><INPUT TYPE=CHECKBOX NAME='"+ nameChk +"' VALUE='" + checkID + "' onclick='"+onClickEvent+"' >" + ciChk.getComboItemDesc() + "</td>");
              		                          }
              		                     	  
              		                      }
              		                      else out.print("<td>&nbsp;</td>");
              		                      out.print("<td>&nbsp;</td>");
              		                      if(chkItr.hasNext())
              		                      {
              		                          ciChk = (ComboItem)chkItr.next();
              		                          checkID = ciChk.getComboItemId();
            		                         // if(checkID.equals(checkVavlue))
            		                          if (listitems_CheckBox.contains(checkID)) 
            		                          {
            		                        	  out.print("<td valign='top'><INPUT TYPE=CHECKBOX id='" +nameChk+"' NAME='"+ nameChk +"' VALUE='" + checkID + "' onclick='"+onClickEvent+"' checked='checked' >" + ciChk.getComboItemDesc() + "</td>");
            		                          }
            		                          else
            		                          {
            		                        	  out.print("<td valign='top'><INPUT TYPE=CHECKBOX NAME='"+ nameChk +"' VALUE='" + checkID + "' onclick='"+onClickEvent+"' >" + ciChk.getComboItemDesc() + "</td>");            		                        	  
            		                          }
              		                      }
              		                      else out.print("<td>&nbsp;</td>");
              		                      out.print("</tr>");		
              		                	}
              		    	        	 if((session.getAttribute("searchWithInSearch" + request.getParameter("reportCode")) != null) && (session.getAttribute("searchWithInSearch" + request.getParameter("reportCode")).toString().equalsIgnoreCase("true"))) 
         		                          {
         		                    	%>
					<script>
	              		        	 		var flagForYN=false;
	              		        	 		for(var iCnt=0;iCnt<ReportForm.<%=nameChk%>.length;iCnt++)
	              		        	 		{
	              		        	 			if(ReportForm.<%=nameChk%>[iCnt].checked)
	              		        	 			{
		              		        	 			flagForYN = true;
		              		        	 			break;
	              		        	 			}
	              		        	 		}

	              		        	 		if(flagForYN)
	              		        	 		{
		              		        	 		for(var iCnt=0;iCnt<ReportForm.<%=nameChk%>.length;iCnt++)
		              		        	 		{
		              		        	 			ReportForm.<%=nameChk%>[iCnt].disabled = true;
		              		        	 			arrOfCheckBox.push(ReportForm.<%=nameChk%>[iCnt]);
		              		        	 		}
	              		        	 		}
              		        	 		 </script>
					<%
         		                          }
           			                 }
              		                    
              		    	        %>
				</table>
			</td>


			<td>&nbsp;</td>
			<% 
              		            	break;
              		    	        
                			case IReportConstants.YESNO_OPTION_INPUT:
                				%>
			<td>&nbsp;</td>
			<td valign="top" class="${labelcss}"><%=prms[i].getParameterDisplayName()%>
				<%
              		                out.print( prms[i].isMandatory() ? "<FONT COLOR=RED>*</FONT>" : "" );
              		                %></td>
			<td valign="top">&nbsp;:</td>
			<td>
				<table>
					<%
                						String nameYN = prms[i].getParameterName();
									    String defValue = (String) rvo.getParameterValue(nameYN);
									    String yesValue = IReportConstants.VALUE_YES;
									    String noValue = IReportConstants.VALUE_NO;
              		        	    	String yesCap = localStringsBundle.getString("Report.label.YesInput");
              		        	    	String noCap = localStringsBundle.getString("Report.label.NoInput");
              		        	    	out.print("<tr>");
              		        	    	if(defValue != null && defValue.equals(yesValue))
              		        	    	{
              		        	    		out.print("<td><INPUT TYPE=RADIO id='" +nameYN+ "' NAME='"+ nameYN +"' VALUE='" + yesValue + "' onclick='"+onClickEvent+"' checked='checked'  >" + yesCap + "</td>");
              		        	    	}	 
              		        	    	else
              		        	    	{
              		        	    		out.print("<td><INPUT TYPE=RADIO NAME='"+ nameYN +"' VALUE='" + yesValue + "' onclick='"+onClickEvent+"' >" + yesCap + "</td>");	
              		        	    	}
              		        	    	
              		        	    	if(defValue != null && defValue.equals(noValue))
              		        	    	{
              		        	    		out.print("<td><INPUT TYPE=RADIO id='" +nameYN+ "' NAME='"+ nameYN +"' VALUE='" + noValue + "' onclick='"+onClickEvent+"' checked='checked' >" + noCap + "</td>");
              		        	    	}
              		        	    	else
              		        	    	{
              		        	    		out.print("<td><INPUT TYPE=RADIO NAME='"+ nameYN +"' VALUE='" + noValue + "' onclick='"+onClickEvent+"' >" + noCap + "</td>");              		        	    		
              		        	    	}
	                					out.print("</tr>");
                					%>
				</table>
			</td>
			<%
										 if((session.getAttribute("searchWithInSearch" + request.getParameter("reportCode")) != null) && (session.getAttribute("searchWithInSearch" + request.getParameter("reportCode")).toString().equalsIgnoreCase("true"))) 
	      		                          {
              		        	 		 %>
			<script>
	              		        	 		var flagForYN=false;
	              		        	 		for(var iCnt=0;iCnt<ReportForm.<%=nameYN%>.length;iCnt++)
	              		        	 		{
	              		        	 			if(ReportForm.<%=nameYN%>[iCnt].checked)
	              		        	 			{
		              		        	 			flagForYN = true;
		              		        	 			break;
	              		        	 			}
	              		        	 		}

	              		        	 		if(flagForYN)
	              		        	 		{
		              		        	 		for(var iCnt=0;iCnt<ReportForm.<%=nameYN%>.length;iCnt++)
		              		        	 		{
		              		        	 			ReportForm.<%=nameYN%>[iCnt].disabled = true;
		              		        	 			arrOfRadioButtons.push(ReportForm.<%=nameYN%>[iCnt]);
		              		        	 		}
	              		        	 		}
              		        	 		 </script>
			<%
      		                          }%>

			<td>&nbsp;</td>

			<%
              								if (!multiColumnEnable) {
              							%>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>


			<%
              									}
              		    	      break;  


            			//added by samir to include a COMPARE_NUMBER parameter
            			case IReportConstants.COMPARE_NUMBER:
            			case IReportConstants.COMPARE_DECIMAL:
            				isDecimal = (prms[i].getParameterType() == IReportConstants.COMPARE_DECIMAL);
            %>
			<td>&nbsp;</td>
			<td class="${labelcss}"><%=prms[i].getParameterDisplayName()%> <%
                				out
                				.print(prms[i].isMandatory() ? "<FONT COLOR=RED>*</FONT>"
                				: "");
                %></td>
			<td>&nbsp;:</td>
			<td><SELECT class="${selectcss}" style="width: 155;" size="1"
				id="<%=prms[i].getParameterName()%>"
				name="<%=prms[i].getParameterName()%>"
				onChange="onChangeComboDetails(this.value,this.name)"
				attrTitle="<%=prms[i].getParameterName()%>" type="select-one">
					<option value="-1" selected><%=localStringsBundle
														.getString("Report.dropdown.select")%></option>
					<%
                				if (rvo.getParameterValue(prms[i]
                				.getParameterName()) != null
                				&& rvo.getParameterValue(
                				prms[i].getParameterName())
                				.equals("1")) {
                			out
                					.print("<option value= \"1\" selected>"
                					+ localStringsBundle
                					.getString("Report.compare.equalto")
                					+ "</option>");
                				} else {
                			out
                					.print("<option value= \"1\">"
                					+ localStringsBundle
                					.getString("Report.compare.equalto")
                					+ "</option>");
                				}
                				if (rvo.getParameterValue(prms[i]
                				.getParameterName()) != null
                				&& rvo.getParameterValue(
                				prms[i].getParameterName())
                				.equals("2")) {
                			out
                					.print("<option value= \"2\" selected>"
                					+ localStringsBundle
                					.getString("Report.compare.lessthan")
                					+ "</option>");
                				} else {
                			out
                					.print("<option value= \"2\">"
                					+ localStringsBundle
                					.getString("Report.compare.lessthan")
                					+ "</option>");
                				}
                				if (rvo.getParameterValue(prms[i]
                				.getParameterName()) != null
                				&& rvo.getParameterValue(
                				prms[i].getParameterName())
                				.equals("3")) {
                			out
                					.print("<option value= \"3\" selected>"
                					+ localStringsBundle
                					.getString("Report.compare.greaterthan")
                					+ "</option>");
                				} else {
                			out
                					.print("<option value= \"3\">"
                					+ localStringsBundle
                					.getString("Report.compare.greaterthan")
                					+ "</option>");
                				}
                				if (rvo.getParameterValue(prms[i]
                				.getParameterName()) != null
                				&& rvo.getParameterValue(
                				prms[i].getParameterName())
                				.equals("4")) {
                			out
                					.print("<option value= \"4\" selected>"
                					+ localStringsBundle
                					.getString("Report.compare.between")
                					+ "</option>");
                				} else {
                			out
                					.print("<option value= \"4\">"
                					+ localStringsBundle
                					.getString("Report.compare.between")
                					+ "</option>");
                				}
                %>
			</select></td>
			<% if((session.getAttribute("searchWithInSearch" + request.getParameter("reportCode")) != null) && (session.getAttribute("searchWithInSearch" + request.getParameter("reportCode")).toString().equalsIgnoreCase("true"))) 
           {
        	%>
			<script>
        			if((document.getElementById("<%=prms[i].getParameterName()%>") != null) && (document.getElementById("<%=prms[i].getParameterName()%>").options.selectedIndex > 0))
        			{
        				document.getElementById("<%=prms[i].getParameterName()%>").disabled = true;
        				arrOfCombos.push(document.getElementById("<%=prms[i].getParameterName()%>"));
        			}
        		</script>
			<%-- Start Added By Dhaval Modi --%>
			<% 	   
           }
            
              
              if (multiColumnEnable) {
              %>
			<td>&nbsp;</td>
			<td class="${labelcss}">&nbsp;</td>
			<td><input type="text"
				name="<%=prms[i].getParameterName()%>from"
				style="visibility: hidden" size="20" maxlength="9"
				value='<%if(rvo.getParameterValue(prms[i].getParameterName()+"from")!= null){%><%=rvo.getParameterValue(prms[i].getParameterName()+"from")%><%}%>'
				<% if((rvo.getParameterValue(prms[i].getParameterName()+"from") != null) && (!"".equals(rvo.getParameterValue(prms[i].getParameterName()+"from"))))
     			{
            	 	if((session.getAttribute("searchWithInSearch" + request.getParameter("reportCode")) != null) && (session.getAttribute("searchWithInSearch" + request.getParameter("reportCode")).toString().equalsIgnoreCase("true")))
          				out.print(" readOnly = true ");
       			}
            %>
				onKeyPress="return isNumber(this,event,<%=isDecimal%>)"
				class="${textcss}"></td>
			<td class="${labelcss}">&nbsp;</td>
			<td><input type="text" name="<%=prms[i].getParameterName()%>to"
				style="visibility: hidden" size="20" maxlength="9"
				value='<%if(rvo.getParameterValue(prms[i].getParameterName()+"to")!= null){%><%=rvo.getParameterValue(prms[i].getParameterName()+"to")%><%}%>'
				<% 	if((rvo.getParameterValue(prms[i].getParameterName()+"to") != null) && (!"".equals(rvo.getParameterValue(prms[i].getParameterName()+"to"))))
     			{
            		if((session.getAttribute("searchWithInSearch" + request.getParameter("reportCode")) != null) && (session.getAttribute("searchWithInSearch" + request.getParameter("reportCode")).toString().equalsIgnoreCase("true")))	
            			out.print(" readOnly = true ");
       			}
            %>
				onKeyPress="return isNumber(this,event,<%=isDecimal%>)"
				class="${textcss}"></td>
			<td>&nbsp;</td>
			<%
              } else {
              %>
			<td class="${labelcss}">&nbsp;</td>
			<td><input type="text"
				name="<%=prms[i].getParameterName()%>from"
				style="visibility: hidden" size="20" maxlength="9"
				.
            value='<%if(rvo.getParameterValue(prms[i].getParameterName()+"from")!= null){%><%=rvo.getParameterValue(prms[i].getParameterName()+"from")%><%}%>'
				<% if((rvo.getParameterValue(prms[i].getParameterName()+"from") != null) && (!"".equals(rvo.getParameterValue(prms[i].getParameterName()+"from"))))
     			{
            	 	if((session.getAttribute("searchWithInSearch" + request.getParameter("reportCode")) != null) && (session.getAttribute("searchWithInSearch" + request.getParameter("reportCode")).toString().equalsIgnoreCase("true")))	
            	 		out.print(" readOnly = true ");
       			}
            %>
				onKeyPress="return isNumber(this,event,<%=isDecimal%>)"
				class="${textcss}"></td>
			<td class="${labelcss}">&nbsp;</td>
			<td><input type="text" name="<%=prms[i].getParameterName()%>to"
				style="visibility: hidden" size="20" maxlength="9"
				value='<%if(rvo.getParameterValue(prms[i].getParameterName()+"to")!= null){%><%=rvo.getParameterValue(prms[i].getParameterName()+"to")%><%}%>'
				<% if((rvo.getParameterValue(prms[i].getParameterName()+"to") != null) && (!"".equals(rvo.getParameterValue(prms[i].getParameterName()+"to"))))
     			{
            	 	if((session.getAttribute("searchWithInSearch" + request.getParameter("reportCode")) != null) && (session.getAttribute("searchWithInSearch" + request.getParameter("reportCode")).toString().equalsIgnoreCase("true")))	
            	 		out.print(" readOnly = true ");
       			}
            %>
				onKeyPress="return isNumber(this,event,<%=isDecimal%>)"
				class="${textcss}"></td>
			<td>&nbsp;</td>

			<%
                          }
                          %>
			<%-- End Added By Dhaval Modi --%>
			<%
            			break;

            			//added by samir ends
            			case IReportConstants.HIDDEN:
            %>
			<td style="display: none;"><input type="hidden"
				name="<%=prms[i].getParameterName()%>"
				value="<%=rvo.getParameterValue(prms[i].getParameterName())%>"></td>
			<%
            			break;

            			case IReportConstants.LIST_MULTISELECT_INPUT:
            			case IReportConstants.LIST_MULTISELECT_OPTGRP_INPUT: /* Added by Divyesh on May 20, 2009 for OPTGROUP option of Dropdown parameter */
            %>
			<td>&nbsp;</td>
			<td class="${labelcss}"><%=prms[i].getParameterDisplayName()%> <%
                				out
                				.print(prms[i].isMandatory() ? "<FONT COLOR=RED>*</FONT>"
                				: "");
                %></td>
			<td>&nbsp;:</td>
			<%
            childList = rvo.getChildParameters(prms[i].getParameterName());
        	if(childList != null)
        	{
        		int childListSize = childList.size();
        		if(childListSize > 0)
      			{
					String source = prms[i].getParameterName();
					String target = "";
					ReportParameterVO reportParamVO = null;
					String parentList[] = null;
					String parents = "";
					String temp ="";
					String eventType = "blur";
					String postFun = "";
						
					for(int childCnt=0;childCnt<childListSize;childCnt++)
					{
						target = childList.get(childCnt).toString();
						temp = "";
						reportParamVO = (ReportParameterVO) childList.get(childCnt);
						parentList = reportParamVO.getParentParameters();
						parents = "";
						String refreshFun = "";
						for(int t=0;t<parentList.length;t++)
						{
							parents = parents + "~" + parentList[t];
						}
						for(int x=0;x<prms.length;x++)
	              		{
	              			if(target.equals(prms[x].getParameterName()))
	              			{
	              				if(prms[x].getParameterType() == 21)
	              				{
	              						String method_name = prms[x].getColumnName();
	              						if(method_name != null && !method_name.equalsIgnoreCase(""))
	              						{
				              				String tempArray[] = method_name.split(",");
				              				for(int y=0;y<tempArray.length;y++)
				              				{
				              					String tempElement = tempArray[y];
				              					if(tempElement.toLowerCase().contains("r:")) refreshFun = tempElement.split(":")[1];
				              				}
				              				if(refreshFun.indexOf("(") != -1) refreshFun = refreshFun.substring(0,refreshFun.indexOf("("));
			              					postFun = refreshFun;
			              					target = "rpt_" + target;
			              					%>
			<input type="hidden" id="rpt_<%=target%>" name="<%=target%>">
			<%
	              						}
	              				}
	              			}
	              		}
							temp = postFun + "~" + reportParamVO.getParameterType() + "~"  + eventType + "~" + source + "~" + target + parents;
								
						populateChilDropDownList.add(temp);
					}
					if(onChangeEvent != null && !onChangeEvent.equals(""))
	          		 {
						 if(onChangeEvent.indexOf("(") != -1)
						 onChangeEvent = onChangeEvent.substring(0,onChangeEvent.indexOf("("));
						 postFun = onChangeEvent;
						 parents = "";
	          			 target = "rptOnChange_" +prms[i].getParameterName();
	          			 temp = postFun + "~" + reportParamVO.getParameterType() + "~"  + eventType + "~" + source + "~" + target + parents;
	          			 populateChilDropDownList.add(temp);
	          			 onChangeEvent = "";
	          			 %>
			<input type="hidden" id="<%=target%>" name="<%=target%>">
			<%
	          		 }
				 }
        	}
            %>
			<td colspan="1"><SELECT class="${selectcss}" style="width: 180"
				multiple size="5" name="<%=prms[i].getParameterName()%>"
				id="<%=prms[i].getParameterName()%>"
				attrTitle="<%=prms[i].getParameterName()%>" type="select-multiple"
				onblur=" <%= onBlurEvent %>" onchange=" <%= onChangeEvent %>"
				onclick=" <%= onClickEvent %>">
					<%
                				Object listselections = rvo
                				.getParameterValue(prms[i]
                				.getParameterName());
                %>
					<option value="-1" <%=((listselections!=null)?"":"selected")%>><%=localStringsBundle
														.getString("Report.dropdown.select")%></option>
					<%
            	
                				List listitems = null;
                				if (listselections != null) {
                			listitems = Arrays
                					.asList((String[]) listselections);
                				} else {
                			listitems = new ArrayList();
                				}
           				if (prms[i].getParameterType() == IReportConstants.LIST_MULTISELECT_INPUT)
           				{			
                				Collection comboData = (Collection) session
                				.getAttribute(prms[i]
                				.getParameterName()
                				+ "Data");
                				Iterator itr = (comboData != null) ? comboData
                				.iterator() : null;
                				if (itr != null) {
                			int j = 0;
                			while (itr.hasNext()) {
                				ComboItem ci = (ComboItem) itr.next();
                				if (j == 0) {
                					if (listitems.contains(ci
                					.getComboItemId())) {
                				out.print("<option value=\""
                						+ ci.getComboItemId()
                						+ "\" selected >"
                						+ ci.getComboItemDesc()
                						+ "</option>");
                					} else {
                				out.print("<option value=\""
                						+ ci.getComboItemId()
                						+ "\" >"
                						+ ci.getComboItemDesc()
                						+ "</option>");
                					}
                					j++;
                				} else {
                					if (listitems.contains(ci
                					.getComboItemId())) {
                				out.print("<option value=\""
                						+ ci.getComboItemId()
                						+ "\" selected >"
                						+ ci.getComboItemDesc()
                						+ "</option>");
                					} else {
                				out.print("<option value=\""
                						+ ci.getComboItemId()
                						+ "\" >"
                						+ ci.getComboItemDesc()
                						+ "</option>");
                					}
                				}
                			}
                				} else {
                				}
           				}
           				else if(prms[i].getParameterType() == IReportConstants.LIST_MULTISELECT_OPTGRP_INPUT)  // Divyesh
        				{
        					GroupedComboData groupedComboData = (GroupedComboData) session.getAttribute(prms[i].getParameterName()+ "Data");;
        					
        	            	if (groupedComboData != null)
        	            	{
        	            		boolean bAddSuffixInValue = groupedComboData.isAddSuffixInValue();
        	            		Map comboMapData = (Map) groupedComboData.getMpGropedComboData();
            					
         						if (comboMapData != null)
         						{	
									Set optGroupKeySet = comboMapData.keySet();
             						
             						Iterator groupItr = (optGroupKeySet != null)? optGroupKeySet.iterator() : null;
             						
             						if (groupItr != null) 
             						{
             							int j = 1;
             							
             							Collection comboData = null;
             							Iterator itr = null;
             							
             							while (groupItr.hasNext()) 
             							{
             								String optGroupKey = String.valueOf(groupItr.next());
             								
             								out.print("<optgroup label=\""+ optGroupKey +"\">");
             								
             								comboData = (Collection)comboMapData.get(optGroupKey);
	            						
		            						itr = (comboData != null) ? comboData.iterator() : null;
			                				if (itr != null) 
			                				{
			                					while (itr.hasNext()) 
			                					{
			                						ComboItem ci = (ComboItem) itr.next();
			                						
		                							if (listitems.contains((ci.getComboItemId() + (bAddSuffixInValue ? (IReportConstants.OPTGRP_VALUE_SUFFIX + j) : ""))))
		                							{
		                								out.print("<option value=\""
				                						+ ci.getComboItemId() + (bAddSuffixInValue ? (IReportConstants.OPTGRP_VALUE_SUFFIX + j) : "")
				                						+ "\" selected >"
				                						+ ci.getComboItemDesc()
				                						+ "</option>");
		                							} else 
		                							{
				                						out.print("<option value=\""
				                						+ ci.getComboItemId() + (bAddSuffixInValue ? (IReportConstants.OPTGRP_VALUE_SUFFIX + j) : "")
				                						+ "\" >"
				                						+ ci.getComboItemDesc()
				                						+ "</option>");
		                							}
					                			}
					                		}
			                				out.print("</optgroup>");
			                				j++;
             							}
             						}
         						}
        	            	}
        				}
                %>
			</select></td>
			<% if((session.getAttribute("searchWithInSearch" + request.getParameter("reportCode")) != null) && (session.getAttribute("searchWithInSearch" + request.getParameter("reportCode")).toString().equalsIgnoreCase("true"))) 
           {
        	%>
			<script>
        			if((document.getElementById("<%=prms[i].getParameterName()%>") != null) && (document.getElementById("<%=prms[i].getParameterName()%>").options.selectedIndex > 0))
        			{
        				document.getElementById("<%=prms[i].getParameterName()%>").disabled = true;
        				arrOfCombos.push(document.getElementById("<%=prms[i].getParameterName()%>"));
        			}
        		</script>
			<%-- Start Added By Dhaval Modi --%>
			<% 	   
           }
                if (!multiColumnEnable) {
                %>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<%
                 }
                 %>
			<%-- End Updation By Dhaval Modi --%>
			<td>&nbsp;</td>

			<%
                                       			break;
                                       			////////
                                       			case IReportConstants.DIVISION_BAR:
                                       %>
		</tr>
		<tr class="TableHeaderBG">
			<!--TableHeaderBG -->
			<td align="center" colspan="<%=(multiColumnEnable)?10:9%>"
				style="border: thin 1 solid #bcbcbc"><%=prms[i].getParameterDisplayName()%>
				<input type="hidden" name="<%=prms[i].getParameterName()%>"
				value=" "></td>
		</tr>
		<%
                            			break;
// START OF : 202414 JSP INCLUDE RELATED CODE 
	case IReportConstants.INCLUDE_JSP_AS_INPUT:
	    String methodArgs = prms[i].getMethodArgs() ;
		
	    String temp  = prms[i].getClassName()+"?"+  methodArgs ;
	    pageContext.setAttribute("fullPathOfIncludeJsp",temp);
	%>

		<td>&nbsp</td>
		<td align="center" width="100%" colspan="8"><jsp:include
				page="${fullPathOfIncludeJsp}" /></td>
		<td>&nbsp</td>
		</tr>

		<%    
	break;
// END OF : 202414 JSP INCLUDE RELATED CODE 

                            			default:
                            %>
		<td>&nbsp;</td>
		<td class="${labelcss}"><%=prms[i].getParameterDisplayName()%></td>
		<td>
			<%
              				out
              				.print(prms[i].isMandatory() ? "<FONT COLOR=RED>&nbsp;*</FONT> : "
              				: " : ");
              %>
		</td>
		<td>Invalid Parameter Type: <%=prms[i].getParameterType()%></td>
		<td>&nbsp;</td>
		<%-- Start Updation By Dhaval Modi --%>
		<%
                if (!multiColumnEnable) {
                %>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<%
                 }
                 %>
		<!-- End Updation By Dhaval Modi -->
		<%
               			}
               			if (!multiColumnEnable) {
               				out.print("</tr>");
               			} else {
               				k++;

               			}
               			
              			if(disable != null && !(disable.equals("")) && (disable.equals("true")) )
              			{
              				String controlName = prms[i].getParameterName();
              				%>
		<script type="text/javascript">
              				  document.getElementById("<%=controlName%>").disabled = true;
              				  </script>
		<%
              			}

               		}
               		if (multiColumnEnable) {
               			out.print("</tr>");
               		}
               		if (colRuleEnable) {
               %>
		<tr>
			<%-- Start Updated By Dhaval Modi <td colspan=9> --%>
			<td colspan="<%=multiColumnEnable?10:9%>">
				<%-- End Updated By Dhaval Modi --%> <jsp:include
					page="ReportColumnRules.jsp" />
			</td>
		</tr>
		<%
            		}
            			}

        			if (rvo.isColumnSelectionAllowed()) {
        %>
		<tr>
			<td colspan="<%=multiColumnEnable?10:9%>">
				<table border="1" cellspacing="0" cellpadding="2" bgcolor='white'
					class="TableBorderLTRBN" style="border-collapse: collapse"
					width="100%">
					<tr bgcolor=#EFEFEF>
						<th align="left" colspan="<%=rvo.getLeafColumns().length%>"><%=localStringsBundle
												.getString("Reports.label.columnselect")%>&nbsp;&nbsp;<input
							type=button
							value='<%=localStringsBundle.getString("Report.button.selectall")%>'
							style="width: 95" class="${buttoncss}"
							onClick="updateCheckBoxes(true)">&nbsp <input type=button
							value='<%=localStringsBundle.getString("Report.button.clearall")%>'
							style="width: 95" class="${buttoncss}"
							onClick="updateCheckBoxes(false)"></th>
					</tr>
					<%-- <tr><td colspan="<%=rvo.getLeafColumns().length%>"><input type=button value="<%=localStringsBundle.getString("Report.button.selectall")%>" style="width:95" class="ActionButton" onClick="updateCheckBoxes(true)">&nbsp<input type=button value="<%=localStringsBundle.getString("Report.button.clearall")%>" style="width:95" class="ActionButton" onClick="updateCheckBoxes(false)"></td></tr> --%>
					<%=rvo.getTableColumnHTMLFromTreeModel(true)%>
				</table>
			</td>
		</tr>
		<%
          }

          %>
		<tr bgcolor="#f3f3f3">
			<td colspan="5" style="border-top: solid 1px #333333;">
				<%
		if (minReqPrms > 0) {
            %> <i><b><%=localStringsBundle
										.getString("Reports.label.note")%> </b><%=localStringsBundle
												.getString("Reports.label.note.prependtext")%> '<font
					face="Verdana" color=red>*</font>' <%=localStringsBundle
												.getString("Reports.label.note.appendtext")%></i> <br> <%
        			}else{
        	            %>&nbsp;<%
        			}
        %>
			</td>
			<td colspan="<%=multiColumnEnable?5:4%>" class="TableBorderTN"
				style="padding: 5px; text-align: right">
				<%
        			StyleVO style = rvo
        			.getStyle(IReportConstants.PARAMETER_PAGE_REPORT_BTN);
        			String value = (style != null) ? style.getStyleValue()
        			: "yes";
        			boolean showReport = (value != null) ? value.trim()
        			.equalsIgnoreCase("YES") : true;

        			style = rvo
        			.getStyle(IReportConstants.PARAMETER_PAGE_SEARCH_BTN);
        			value = (style != null) ? style.getStyleValue() : "yes";
        			boolean showSearch = (value != null) ? value.trim()
        			.equalsIgnoreCase("YES") : true;
        			showSearch = false;//by Jitesh.

        			style = rvo
        			.getStyle(IReportConstants.PARAMETER_PAGE_RESET_BTN);
        			value = (style != null) ? style.getStyleValue() : "yes";
        			boolean showReset = (value != null) ? value.trim()
        			.equalsIgnoreCase("YES") : true;

        			style = rvo
        			.getStyle(IReportConstants.GENERATE_RPT_BTN_TEXT);
        			String generateRptLabel = (style != null) ? style
        			.getStyleValue() : null;
        			boolean showGenRptLabel = (generateRptLabel != null) ? true
        			: false;

        			style = rvo.getStyle(IReportConstants.SEARCH_RPT_BTN_TEXT);
        			String searchRptLabel = (style != null) ? style
        			.getStyleValue() : null;
        			boolean showSearchRptLabel = (searchRptLabel != null) ? true
        			: false;

        			style = rvo.getStyle(IReportConstants.RESET_BTN_TEXT);
        			String resetBtnLabel = (style != null) ? style
        			.getStyleValue() : null;
        			boolean showResetBtnLabel = (resetBtnLabel != null) ? true
        			: false;

        			if (showReport) {
        %> <input type="button" name=GenerateReport
				id="rptBtn_GenerateReport"
				value='<%=(showGenRptLabel)?generateRptLabel:localStringsBundle.getString("Report.button.generate")%>'
				style="width: 95" class="bigbutton"
				onClick="generateReportPrmt(this)"> <%
            			}

            			if (showSearch) {
            %> <input type="button" name=GenerateReport
				id="rptBtn_ShowSearch"
				value='<%=(showSearchRptLabel)?searchRptLabel:localStringsBundle.getString("Report.button.search")%>'
				style="width: 95" class="${buttoncss}" onClick="searchReport(this)">
				<%
            			}

            			if (showReset) {
            %> <input type="button" name="ResetParams"
				id="rptBtn_ResetBtn"
				value='<%=(showResetBtnLabel)?resetBtnLabel:localStringsBundle.getString("Report.button.reset")%>'
				style="width: 95" class="${buttoncss}" onClick="resetReport()">
				<%
            }
            %>
			
	</table>
	<br>
	<div align="center" id="progressbar" style="display: none">
		<table border=0 cellpadding=1>
			<tr>
				<td align="right" valign="middle"><img
					src="${rimg}/progressbar.gif">&nbsp;</td>
				<td align="left" valign="middle" class=Label><%=localStringsBundle
									.getString("Report.label.pleasewait")%></td>
			</tr>
		</table>
	</div>
</hdiits:form>

<c:set var="baseURL" scope="page"><%=uri%>&reportCode=<%=rvo.getReportCode()%>&action=populateChildDropDown</c:set>

<script type="text/javascript">
myWinName = window.name;
function hideProgressbarPrmt()
{
	hideProgressbar();
	window.name = myWinName;
}
</script>

<%
	//Added by Brijesh Prajapati to populate dependent DropDown box using ajax:select tag.-----------------
	
	if(populateChilDropDownList != null)
	{
		int listSize = populateChilDropDownList.size();
		if(listSize > 0)
		{
			for(int childCnt=0; childCnt<listSize; childCnt++ )
			{
				String list = populateChilDropDownList.get(childCnt).toString();
				String listArray[] = list.split("~");
				String ajaxPostFun = listArray[0];
				int parameterType = Integer.valueOf(listArray[1]);
				String event = listArray[2];
				String ajaxSource = listArray[3];
				String ajaxTarget = listArray[4];
				String parentList = "";
				
				if("".equalsIgnoreCase(ajaxPostFun)) ajaxPostFun = "hideProgressbarPrmt";
					
				for(int t=3;t<listArray.length;t++)
				{
					parentList = parentList + ","+listArray[t]+ "={" + listArray[t]+ "}" ;
				}
				String ajaxParams = "ParameterValue={"+ajaxSource+"},ParentParameter="+ajaxSource
									+ parentList + ",ChildParameter="+ajaxTarget
									+",ajaxCall=Y";
					%>
<c:set var="event"><%= event %></c:set>
<c:set var="ajaxSource"><%= ajaxSource %></c:set>
<c:set var="ajaxTarget"><%= ajaxTarget %></c:set>
<c:set var="ajaxParams"><%= ajaxParams %></c:set>
<c:set var="ajaxPostFun"><%= ajaxPostFun %></c:set>
<% 		
							if(parameterType == IReportConstants.DROPDOWN_INPUT || 
							   parameterType == IReportConstants.WIDE_DROPDOWN_INPUT ||
							   parameterType == IReportConstants.LIST_MULTISELECT_INPUT )
					        {
					%>
<ajax:select baseUrl="${baseURL}" source="${ajaxSource}"
	target="${ajaxTarget}" parameters="${ajaxParams}"
	preFunction="showProgressbar" postFunction="${ajaxPostFun}"
	eventType="${event}" />
<% 
					        }
							else if(parameterType == IReportConstants.DROPDOWN_OPTGRP_INPUT  ||
									parameterType == IReportConstants.WIDE_DROPDOWN_OPTGRP_INPUT  ||
									parameterType == IReportConstants.LIST_MULTISELECT_OPTGRP_INPUT )
							{
					%>
<hdiits:ajaxSelectOptGrp baseUrl="${baseURL}" source="${ajaxSource}"
	target="${ajaxTarget}" parameters="${ajaxParams}"
	preFunction="showProgressbar" postFunction="${ajaxPostFun}"
	eventType="${event}" />
<%
							}
				}		
		}
	}
	//--------------------------------------------------------------------------------------------------
%>

<%
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
