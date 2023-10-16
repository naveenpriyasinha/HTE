<%@ page language="java" import="com.tcs.sgv.common.util.reports.*,
                                 java.util.Locale,
                                 java.util.ResourceBundle,
                                 com.tcs.sgv.common.valuebeans.reports.*,
                                 java.util.ArrayList" %>

<%@page import="javax.swing.tree.DefaultMutableTreeNode"%>
<jsp:useBean id="rb" class="com.tcs.sgv.common.util.reports.ReportsBean" scope="request"/>
<jsp:setProperty name="rb" property="rootHTMLPath" value='<%=session.getServletContext().getRealPath( "" )%>' />
<jsp:setProperty name="rb" property="reportImages" value="${rimg}" />
<script type="text/javascript" src='<c:url value="script/reports/prmt_validations.js"/>'></script>
<script type="text/javascript" src='<c:url value="script/reports/treetable.js"/>'></script>

    <style type="text/css" media="print"> 
    .imgtagtable
    {
        background: transparent;
    }
    
    .zoomlinktd{
    {
        visibility: hidden;
    }
    </style>       
    
<%

	String defaultNavigationAlignment = "left";
    boolean showSubReport = true;
    Locale userLocale = null;
    Object data = null;
    String strData = null;
    // added by prachi shah for title of main report on 29-May-2006
    String strMainTitle = null;
    if( session.getAttribute( "LocaleID" ) != null && !(session.getAttribute( "LocaleID" ).equals( "null" )) )
        userLocale = (Locale)session.getAttribute( "LocaleID" );
    else
        userLocale = request.getLocale();

    ResourceBundle localStringsBundle = ResourceBundle.getBundle( "resources/reports/SGVLabelsReports", userLocale );
    request.setAttribute("localStringsBundleNav",localStringsBundle);
    //changes by common team
    ReportVO rvo = new ReportVO();
    ReportData rdata = null;
    
    ReportData rMainData = (ReportData)session.getAttribute( "reportData"+request.getParameter("reportCode") );
	//Added by jeekesh
    String printPage = (String)session.getAttribute("printReport"+request.getParameter("reportCode"));
    if ( printPage != null && printPage.equals("true") )
    {
    	rMainData = (ReportData)session.getAttribute( "printReportData"+request.getParameter("reportCode") );
    }
    //end changes common team
    
    //NOTE:
    //use ReportVO from ReportData as there was problem when using ReportVO
    //instance from session as the ReportVO instance was not reflected with the
    //column tree initialized in the session ReportVO instance
    
    rvo = rMainData.getReportVO();
    SubReportVO[] subReptList = rvo.getSubReportList();
    // to get the title of main report
    data = rvo.getReportName();
    if( data != null && data instanceof String )
    {
        strMainTitle = (String) data;
        strMainTitle = strMainTitle.replaceAll(System.getProperty("line.separator"), "<br>");
       // data = strMainData;
    }
    Object objHdrList = rb.getReportParameterTable(rvo,rMainData);
    printPage = (printPage != null) ? printPage.trim() : "false";
    boolean printSubReport = true;
    
    StyleVO displaySubRpt = rvo.getStyle( IReportConstants.DISPLAY_SUB_REPORTS_IN_JSP );
    if(displaySubRpt!= null && displaySubRpt.getStyleValue() != null && displaySubRpt.getStyleValue().equalsIgnoreCase("NO"))
    {
        showSubReport = false;
    }
    
    ArrayList reptList = new ArrayList();
    ArrayList rptTitleList = new ArrayList();
    StyleVO styleSubRptTitle  = null;    	
	boolean showSubrptTitle = false;
	boolean emptyTitleList= true;
	
	ArrayList pgBreakSubRptList = new ArrayList();
	boolean noPageBreak = true;
	
    if(subReptList != null && subReptList.length > 0 && showSubReport)
    {
        for(int sCnt=0;sCnt < subReptList.length; sCnt++)
        {
          SubReportVO objSubReptVO = new SubReportVO();
          objSubReptVO = subReptList[sCnt];
          if(objSubReptVO.getLocation()==IReportConstants.LOCATION_HEADER)
          {
            reptList.add(objSubReptVO);
            	styleSubRptTitle  = objSubReptVO.getStyle(IReportConstants.SHOW_SUBRPT_NAME_AS_MAIN);
	            showSubrptTitle = (styleSubRptTitle != null && styleSubRptTitle.getStyleValue() != null &&
	        		  			styleSubRptTitle.getStyleValue().equalsIgnoreCase("yes")?true:false );
	  	        if(showSubrptTitle) { rptTitleList.add(objSubReptVO.getReportName()); emptyTitleList= false; }
	    	    else rptTitleList.add("");
	  	        
	  	        StyleVO styleBgBkHdr = objSubReptVO.getStyle( IReportConstants.PAGE_BREAK_BRFORE_SUBREPORT);
	  	        if(styleBgBkHdr != null && styleBgBkHdr.getStyleValue() != null &&
	  	        		styleBgBkHdr.getStyleValue().equalsIgnoreCase("yes"))
	  	        	{ pgBreakSubRptList.add("yes"); noPageBreak = false; }
	  	        else
	  	        	pgBreakSubRptList.add("no");
          }
	          
        }
        reptList.add(rvo);
        rptTitleList.add(strMainTitle);	

        StyleVO styleBgBkMain = rvo.getStyle( IReportConstants.PAGE_BREAK_BRFORE_SUBREPORT);
	        if(styleBgBkMain != null && styleBgBkMain.getStyleValue() != null &&
	        		styleBgBkMain.getStyleValue().equalsIgnoreCase("yes"))
	        	{pgBreakSubRptList.add("yes");noPageBreak = false; }
	        else
	        	pgBreakSubRptList.add("no");
        
        for(int sCnt=0;sCnt < subReptList.length; sCnt++)
        {
          SubReportVO objSubReptVO = new SubReportVO();
          objSubReptVO = subReptList[sCnt];
          if(objSubReptVO.getLocation()==IReportConstants.LOCATION_FOOTER)
          {
            reptList.add(objSubReptVO);
              styleSubRptTitle  = objSubReptVO.getStyle(IReportConstants.SHOW_SUBRPT_NAME_AS_MAIN);
	          showSubrptTitle = (styleSubRptTitle != null && styleSubRptTitle.getStyleValue() != null &&
	        		  styleSubRptTitle.getStyleValue().equalsIgnoreCase("yes")?true:false );
	          if(showSubrptTitle) { rptTitleList.add(objSubReptVO.getReportName()); emptyTitleList= false; }
	          else rptTitleList.add("");

	  	        StyleVO styleBgBkHdr = objSubReptVO.getStyle( IReportConstants.PAGE_BREAK_BRFORE_SUBREPORT);
	  	        if(styleBgBkHdr != null && styleBgBkHdr.getStyleValue() != null &&
	  	        		styleBgBkHdr.getStyleValue().equalsIgnoreCase("yes"))
	  	        	{ pgBreakSubRptList.add("yes"); noPageBreak = false; }
	  	        else
	  	        	pgBreakSubRptList.add("no");

	      }
        }
      
      }
      else
      {
        reptList.add(rvo);
      }
    
    if(emptyTitleList) rptTitleList=null;
%>

<center>
    <table width="95%" border="0" cellspacing="0" class="padded-table">
     <%
     if(noPageBreak){
     %><tr><td class = "reportHeader"><%@include file="rptheader.jsp"%></td></tr> <%
     }
     %> 
        <%
        if(reptList != null && reptList.size() > 0)
        {

          for(int rCnt=0;rCnt<reptList.size();rCnt++)
          {
        	  /** Added for PageBreak on Group By **/
        	  rvo = (ReportVO)reptList.get(rCnt);
        	  StyleVO pageBreakGrp = rvo.getStyle( IReportConstants.PAGE_BREAK_BRFORE_GROUPBY); 
        	  boolean pageBreakBeforeGrp = pageBreakGrp!=null && pageBreakGrp.getStyleValue() != null && pageBreakGrp.getStyleValue().equalsIgnoreCase("YES")?true:false;
        	  
        	  StyleVO pageBreak = rvo.getStyle( IReportConstants.PAGE_BREAK_BRFORE_SUBREPORT); 
        	  boolean pageBreakBefore = pageBreak!=null && pageBreak.getStyleValue() != null && pageBreak.getStyleValue().equalsIgnoreCase("YES")?true:false;
        	  StyleVO styleSubRptHeader =rvo.getStyle(IReportConstants.SHOW_SUB_REPORT_HEADER);
    	   	  StyleVO styleSubRptFooter = rvo.getStyle(IReportConstants.SHOW_SUB_REPORT_FOOTER);
             
              StyleVO prntSubRpt = rvo.getStyle( IReportConstants.PRINT_SUB_REPORT);
              printSubReport = (prntSubRpt != null && prntSubRpt.getStyleValue() != null && prntSubRpt.getStyleValue().equalsIgnoreCase("No"))?false:true;    
              if(rvo instanceof SubReportVO && printPage.equalsIgnoreCase("true") && !printSubReport)
              {
                 continue;
              }
              if(rvo instanceof SubReportVO )
              {
                
                rdata = (ReportData) rMainData.getSubReportData("reportData"+rvo.getReportCode());
               
              }
              else
              {
                
                rdata =(ReportData)session.getAttribute( "reportData"+rvo.getReportCode()) ;
				
                //Added by jeekesh
                if ( printPage.equals("true") )
                {
              	    rdata =(ReportData)session.getAttribute( "printReportData"+rvo.getReportCode()) ;
                }
                //End added by jeekesh
                
              }
              
              rvo =rdata.getReportVO();
              int totalRecords = rdata.getRowCount();
              boolean showGrandTotal = true;
	          StyleVO tempStyleShowReport = rvo.getStyle( IReportConstants.SHOW_REPORT_WHEN_NO_DATA );
        	  if(tempStyleShowReport != null && tempStyleShowReport.getStyleValue() != null && 
        		  	tempStyleShowReport.getStyleValue().equalsIgnoreCase("no") && totalRecords ==0)
        	  {
        			showGrandTotal = false;
        	  }

              if(rCnt > 0 && showGrandTotal)
              {
            	  if(pageBreakBefore && pageBreak != null)
                  {
		              %>
		              <tr style="page-break-before: always;"><td>&nbsp;</td></tr>
		              <%
                  }
            	  else
                  {
                	  %>
                      <tr><td>&nbsp;</td></tr>
                      <%
                  }
              }

              if(pageBreakBefore && pageBreak != null && showGrandTotal)
              {
	       	   		if(!(styleSubRptHeader == null && styleSubRptFooter == null) && 
	       	   				rvo != null && rvo instanceof SubReportVO )
	       	   		{
	       	   			boolean showSubRptHeader = (styleSubRptHeader != null && styleSubRptHeader.getStyleValue() != null &&
					  				styleSubRptHeader.getStyleValue().equalsIgnoreCase("yes")?true:false);
	       	   			if(showSubRptHeader) 
	       	   			{
	         	   			request.setAttribute("subReportCode",rvo.getReportCode());		
	    		            %><tr><td class = "reportHeader"><%@include file="rptheader.jsp"%></td></tr><%
	       	   			}
	       	   		}
	       	   		else
	       	   		{
	       	   		%><tr><td class = "reportHeader"><%@include file="rptheader.jsp"%></td></tr><%
	       	   		}
              }
              else
              {
            	  	if( showGrandTotal && (!noPageBreak && (rCnt==0 || pgBreakSubRptList.get(rCnt).equals("yes") ) )  )
              		{
              			%><tr><td class = "reportHeader"><%@include file="rptheader.jsp"%></td></tr><%
              		}
              }  
              
              // changed here on 26th May 2006
             
				if(rptTitleList != null)
				{
					strMainTitle =(String) rptTitleList.get(rCnt);
					strMainTitle = strMainTitle.replaceAll(System.getProperty("line.separator"), "<br>");
					if(rvo instanceof SubReportVO)
	          	  	{
	          		  	objHdrList = null;
	          	  	}
	          	  	else
	          	  	{
	          		  	objHdrList = rb.getReportParameterTable(rvo,rMainData);
	          	  	}
				}
              //Added by Jeekesh  for template css include
             
              if(rvo.getReportTemplate() != null && rvo.getReportTemplate().get(IReportConstants.TMPLT_PAGE) != null)
	          {
		        	StyleVO [] pageStyleList = rvo.getReportTemplate().get(IReportConstants.TMPLT_PAGE);
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
            
            StyleVO pagination = rvo.getStyle( IReportConstants.REPORT_PAGINATION );
            boolean showPagination = true;
            if(pagination!= null && pagination.getStyleValue() != null && pagination.getStyleValue().equalsIgnoreCase("NO"))
            {
                showPagination = false;
            }
            
            if(rvo != null && rvo instanceof SubReportVO)
            {
              showPagination = false;
            }
            /* Added by Prachi Shah to add word Total in the first column
            String lStrDisplayTotalLineLabel = (String)localStringsBundle.getString("Report.label.totalLineLabel");*/
            //Code removed by samir to include all evaluations on 2-June-2006
            String totalLineLabel = "";
            
            String cPage = (String)session.getAttribute( "currPage"+rvo.getReportCode() );
            cPage = (cPage != null) ? cPage.trim() : "0";
            
            int pgSize = rvo.getPageSize();
            pgSize = (printPage.equalsIgnoreCase("true") || !showPagination) ? totalRecords : pgSize;
            int currPage = Integer.parseInt( cPage );
            int startIndex = (printPage.equalsIgnoreCase("true")) ? 0 : (pgSize * currPage);
            int stopIndex = Math.min(startIndex + pgSize,totalRecords );
            int maxPage = (pgSize > 0)?(totalRecords/pgSize):0;    
            maxPage = (pgSize > 0)?(((totalRecords%pgSize)==0) ? (maxPage-1) : maxPage):0;
            
            request.setAttribute("currPageNav",currPage);
            request.setAttribute("totRecordsNav",totalRecords);
            request.setAttribute("pgSizeNav",pgSize);
            
            int actualRowCnt = rdata.getActualRowCount();
            int actulaStartIndex = rdata.getActualStartIndex(currPage);
            int actualStopIndex = 	rdata.getActualStopIndex(currPage);
            String navtext = "";
            
            if(currPage == maxPage) actualStopIndex = actualRowCnt;
            
			String langID = rvo.getLangId();
			String dispTextNav = localStringsBundle.getString("ReportNav.label.dispText").trim();
			String fromNav = localStringsBundle.getString("ReportNav.label.from").trim();
			String toNav = localStringsBundle.getString("ReportNav.label.to").trim();
			String recordsTextNav = localStringsBundle.getString("ReportNav.label.records").trim();
			
			if(langID.equalsIgnoreCase("gu"))
			{
				navtext = 	actualRowCnt + " " + recordsTextNav + " " + actulaStartIndex
							+ " " + toNav + " " + actualStopIndex + " " + dispTextNav;
			}
			else
			{
				navtext = dispTextNav + " " + actulaStartIndex + " " + toNav 
						  + " " + actualStopIndex + " " + fromNav + " " 
						  + actualRowCnt + " " + recordsTextNav;	
			}
			
            session.setAttribute( "maxPage"+rvo.getReportCode(), ""+maxPage );
            session.removeAttribute("printReport"+rvo.getReportCode());
            request.setAttribute("enableFirst", new Boolean( currPage!=0 ) );
            request.setAttribute("enableLast", new Boolean( currPage!=maxPage ) );
            request.setAttribute("navtext",navtext);

            // Adding Additional Header in report.
            String addlHdrAlign = "left";
            Object objAddlHdr = null;
           	boolean hdrLocBelow = true;
        	if(rvo != null && showGrandTotal)
            {
        		objAddlHdr = rvo.getAdditionalHeader();
        		if(objAddlHdr != null)
        		{
            		StyleVO styleHdrLoc = null;
            		if(objAddlHdr instanceof StyledData)
            		{
            			styleHdrLoc = ((StyledData) objAddlHdr).getStyle(IReportConstants.ADDL_HEADER_LOCATION);
            			hdrLocBelow = (styleHdrLoc != null && styleHdrLoc.getStyleValue() != null && 
            					       styleHdrLoc.getStyleValue().equalsIgnoreCase("above"))?false:true;
            			StyleVO sAddlHdrAlign = ((StyledData) objAddlHdr).getStyle(IReportConstants.STYLE_FONT_ALIGNMENT);
            	        addlHdrAlign =  sAddlHdrAlign!=null && sAddlHdrAlign.getStyleValue() != null 
            	                        ?sAddlHdrAlign.getStyleValue():addlHdrAlign;
            		}
        			if(!hdrLocBelow) out.println("<tr><td align='"+addlHdrAlign+"' style='"+rb.getDataStyleString(rvo,objAddlHdr)+"' > " +rb.getDataValue(objAddlHdr)+ "</td></tr>");
        		}
            }
        	
          // Added By Dharmesh Gohil  to display title Conditionallly
          StyleVO tStyle = rvo.getStyle( IReportConstants.SHOW_REPORT_NAME );
          boolean showTitle = tStyle!=null && tStyle.getStyleValue() != null && tStyle.getStyleValue().equalsIgnoreCase("No")?false:true;

           // to display the title of main report in the beginning
           if(showTitle && strMainTitle != null && !strMainTitle.equals("") && showGrandTotal)
           {
           %>
               <tr><td style="padding: 5px;"><center><FONT class=titles style="<%=rb.getReportTitleStyleString(rvo)%>" ><%=strMainTitle%></font></center></td></tr>
           <%
              //strMainTitle = null;
              data = "";
           } // end of if (strMainData != null)
           // Added by Prachi Shah for displaying parameters of main reports along with the title
           else
           {
        	   strMainTitle = null;   
           }
                   
           if (objHdrList != null && showGrandTotal)
           {	
          	   out.print("<tr><td><center> " + objHdrList + " </center></td></tr>");
           } //end of if (lSubHdrList != null ) 
    // End of Added by Prachi Shah for parameters on 29th May 2006
    
     // Adding additional header below the report title and parameters.
     if(objAddlHdr != null && hdrLocBelow && showGrandTotal)
     {
    	 out.println("<tr><td align='"+addlHdrAlign+"' style='"+rb.getDataStyleString(rvo,objAddlHdr)+"' > " +rb.getDataValue(objAddlHdr)+ "</td></tr>");
	 }

        %>
<%-- Added By Pinkal on 28th Feb 2006 - Start --%>          
        <%
        if(rvo != null && rvo instanceof SubReportVO && showGrandTotal)
        {
        	Object objSubHdrList = rb.getReportParameterTable(rvo,rdata);
            if (objSubHdrList != null )
            {	
           	   out.print("<tr><td><center> " + objSubHdrList + " </center></td></tr>");
            }
        } //end of if (lSubHdrList != null )  %>        
<%-- Added By Pinkal on 28th Feb 2006 - end --%>    
        <center>
    <%-- Include navigation jsp only if it is not opened for print --%>
    <%

    if( (totalRecords>0) && (!printPage.equalsIgnoreCase("true") && showPagination) )
    {
      //Added by samir for navigation bar alignment
      String lstrNvgAlign = defaultNavigationAlignment;
      StyleVO nvgStyle = rvo.getStyle( IReportConstants.NAVIGATION_ALIGNMENT );
      if(nvgStyle != null && nvgStyle.getStyleValue() != null)
      {
        int liNvgAlign = Integer.parseInt(nvgStyle.getStyleValue());
        if( (liNvgAlign & IReportConstants.ALIGN_LEFT ) == IReportConstants.ALIGN_LEFT )
        {
            lstrNvgAlign = "left";
            request.setAttribute("UpperBar","0%");
            request.setAttribute("LowerBar","100%");
        }    
        else if( ( liNvgAlign & IReportConstants.ALIGN_RIGHT) == IReportConstants.ALIGN_RIGHT )
        {
            lstrNvgAlign = "right";
            request.setAttribute("UpperBar","100%");
            request.setAttribute("LowerBar","0%");
        }    
        else if( (liNvgAlign & IReportConstants.ALIGN_CENTER )== IReportConstants.ALIGN_CENTER )  
        {
            lstrNvgAlign = "middle";   
            request.setAttribute("UpperBar","50%");
            request.setAttribute("LowerBar","50%");
        }    
      } 
      else
      {
          request.setAttribute("UpperBar","0%");
          request.setAttribute("LowerBar","100%");
      }
      //End-Added by samir for navigation bar alignment
      request.setAttribute("navLoc","header");
    %>
    <tr><td align="<%=lstrNvgAlign%>" ><%@include file="navigation.jsp"%></td></tr>
    <%
    }  
         // Added By Dharmesh Gohil to make border Conditionally disable   
          StyleVO bStyle = rvo.getStyle( IReportConstants.BORDER );
          boolean showBorder = bStyle!=null && bStyle.getStyleValue() != null && bStyle.getStyleValue().equalsIgnoreCase("No")?false:true;
          int borderWidht =  showBorder ? 1:0;
    %>
    <tr><td valign=top>
    <table id="dataTable<%=rCnt%>" border="<%=borderWidht%>" cellspacing="0" bgcolor='white' <%if(showBorder){%> class="TableBorderLTRBN padded-table" style="border-collapse: collapse" <%}%>  width="100%">
        <%

        if( rdata != null )
        {
            ReportColumnVO[] clms = rvo.getColumnsToDisplay();
            int totalColumns = (clms != null) ? clms.length : 0;
            
        %>
          <%-- Start to make column width Effective  Added By Dharmesh Gohil --%>
          <tr>
          <%for(int k=0;k<totalColumns;k++){%>
           <td width ="<%=clms[k].getColumnWidth()%>%" ></td>
          <%}%>
          </tr>
           <%-- End  to make column width Effective --%>
        <%  

        	int totalRows = rdata.getRowCount();
            int rowBkCount = 0;
            String className = "", tdclass = "";
            Object gclm = null;
            Object rgclm = null;
            boolean addGrpRow = false, addTotalRow=false;
            ReportColumnVO grpclm = rvo.getGroupByColumn();
            ReportColumnVO[] grpclms = rvo.getGroupByColumns();
            int grpClm = (grpclms != null && grpclms[0] != null) ? rdata.getColumnIndex(grpclms[0].getColumnName()) : -1;
            //int grpClm = (grpclm != null) ? rdata.getColumnIndex(grpclm.getColumnName()) : -1;
            int[] arrGrpClm = null;
            int[] arrSortType = null;
            if(grpclms != null && grpclms.length > 0)
            {
               arrGrpClm = new int[grpclms.length];
               arrSortType = new int[grpclms.length];
               for(int i=0;i<grpclms.length;i++)
               {
                  arrGrpClm[i] = (grpclms[i] != null) ? rdata.getColumnIndex(grpclms[i].getColumnName()) : -1;
                  arrSortType[i] = 0;
               }
            }
            boolean groupingEnabled = (grpClm >= 0);
            if( groupingEnabled )
            {
                rdata.getSortingHelper().sort(arrGrpClm,arrSortType);
                
            }
           
            %>
            <%
         
            boolean grpFlag=false;
            if(grpclm!=null )
            {
             if(grpclm.getURL()!=null && grpclm.getURL().length()>0 )
             {
               grpFlag=true;
             }
            } 
            String lStrDisplayLineLabel = (String)localStringsBundle.getString("NoDataFoung");
            //if( totalRecords == 0 ) out.println("<tr><td colspan=" + totalColumns +">"+lStrDisplayLineLabel+"</td></tr>");
            if(totalRecords == 0 && !showGrandTotal) 
            {
						%>
								<script type="text/javascript" >
								document.getElementById("dataTable<%=rCnt%>").style.display = 'none';
								</script>
						<%
            }
            if( totalRecords == 0 && showGrandTotal) 
            {
	            	StyleVO tempStyle = rvo.getStyle( IReportConstants.SHOW_REPORT_NAME );
	                boolean tempShowTitle = tempStyle!=null && tempStyle.getStyleValue() != null && tempStyle.getStyleValue().equalsIgnoreCase("No")?false:true;
	                String strTempTitle = rvo.getReportName();
	                
	                StyleVO styleTblHdr = rvo.getStyle( IReportConstants.SHOW_COLUMN_HEADER_WHEN_NODATA);
	                boolean showTblColHdr = styleTblHdr != null && styleTblHdr.getStyleValue() != null &&
							                styleTblHdr.getStyleValue().equalsIgnoreCase("yes") ? true : false;
	                
	                if( rvo instanceof SubReportVO && tempShowTitle && strTempTitle != null && !strTempTitle.equals("") &&
	                		rptTitleList != null && rptTitleList.get(rCnt) != null && rptTitleList.get(rCnt).equals("") && !showTblColHdr)
	                {
	                	out.print("<tr><td colspan=" + totalColumns +"><center><FONT class='HLabel'  style='font-weight : bold ;'>"+strTempTitle+"</font></center></td></tr>");
	                }
	                if(showTblColHdr)
	                {
	                	out.println(rvo.getTableColumnHTMLFromTreeModel());
	                }
	            	out.println("<tr><td colspan=" + totalColumns +">"+lStrDisplayLineLabel+"</td></tr>");	
            }
            
            SortingHelper sortingHelper = rdata.getSortingHelper();
            int index = 0;
            
            StyleVO styleFetchData =  rvo.getStyle(IReportConstants.FETCHDATA_ON_PAGE_REQUEST); 
            boolean fechDataOnPgReq = (styleFetchData != null && styleFetchData.getStyleValue() != null && 
            						   styleFetchData.getStyleValue().equalsIgnoreCase("yes")?true:false);
            int rdataSize = rdata.getRows().size();
            if(fechDataOnPgReq)
            {
                startIndex = 0;
                stopIndex=rdataSize;
            }
            if(currPage>0 && (!printPage.equalsIgnoreCase("true") && showPagination) )
            {
            	for(int istart=0;istart<startIndex;istart++)
            	{
            		if (rdata.getValueAt(istart,0) instanceof PageBreak )
            		{
            			startIndex++;
            			stopIndex++;
            		}
            	}
            	for(int istop=startIndex;istop<startIndex;istop++)
            	{
            		if (rdata.getValueAt(istop,0) instanceof PageBreak )
            		{
            			stopIndex++;
            		}
            	}
            	if(currPage == maxPage) stopIndex=rdataSize;
            }
            for( int i = startIndex; i < stopIndex; i++ )
            {
            		if(i >= rdataSize) break;
            		//Added by Manish Vyas to enter page-break at particular location
	            	if (rdata.getValueAt(i,0) instanceof PageBreak ) {
	            		
	            	 if (printPage.equalsIgnoreCase("true")) // Print Page Starts...
	            	 {
	            		out.println("</table></td></tr>");
						// Adding report footer ...
	            		%><tr><td class = 'reportHeader'><jsp:include page="rptfooter.jsp" /></td></tr><%
	            		out.println("<tr><td><table border='0'>");
	            		out.println("<tr bordercolor='white' style='page-break-before: always;'><td></td></tr>");
	            		out.println("</table></td></tr>");
	            		// Adding report header ...
	            		%><tr><td class = 'reportHeader'><jsp:include page="rptheader.jsp" /></td></tr><%
	            		StyleVO sAddlHdrEachPage = rvo.getStyle(IReportConstants.ADDL_HEADER_ON_EACH_PAGE);
	            		boolean showAddlHdr =  sAddlHdrEachPage!=null && sAddlHdrEachPage.getStyleValue() != null 
	            		                       && sAddlHdrEachPage.getStyleValue().equalsIgnoreCase("yes")?true:false;            		
	            		// Adding additional header above the report title.
	            		if(objAddlHdr != null && showAddlHdr && !hdrLocBelow)
	            		{
	            			out.println("<tr><td align='"+addlHdrAlign+"' style='"+rb.getDataStyleString(rvo,objAddlHdr)+"' > " +rb.getDataValue(objAddlHdr)+ "</td></tr>");
	            		}
	            		// Adding report title on each page.
	            		StyleVO sTitleEachPage = rvo.getStyle(IReportConstants.TITLE_ON_EACH_PAGE);
	            		boolean showRptTitle =  sTitleEachPage!=null && sTitleEachPage.getStyleValue() != null && sTitleEachPage.getStyleValue().equalsIgnoreCase("yes")?true:false;
	            		if(showRptTitle)
	            		{
	            			String tmpRptTile = (strMainTitle != null)?strMainTitle:"";
	            			out.println("<tr><td style='padding: 5px;'><center><FONT class=titles>"+tmpRptTile+"</font></center></td></tr>");
	            		}
	            		// Adding report parameters on each page.
	            		StyleVO sParamEachPage = rvo.getStyle(IReportConstants.PARAMETERS_ON_EACH_PAGE);
	            		boolean showRptParams =  sParamEachPage!=null && sParamEachPage.getStyleValue() != null && sParamEachPage.getStyleValue().equalsIgnoreCase("yes")?true:false;
	            		if(showRptParams)
	            		{
	            			out.print("<tr><td><center> " + rb.getReportParameterTable(rvo, rMainData) + "</center></td></tr>");
	            		}
	            		// Adding additional header below the report title.
	            		if(objAddlHdr != null && showAddlHdr && hdrLocBelow)
	            		{
	            			out.println("<tr><td align='"+addlHdrAlign+"' style='"+rb.getDataStyleString(rvo,objAddlHdr)+"' > " +rb.getDataValue(objAddlHdr)+ "</td></tr>");
	            		}
	            	%>
	            	<tr><td valign="top">
		            <table border="<%=borderWidht%>" cellspacing="0" bgcolor='white' <%if(showBorder){%> class="TableBorderLTRBN padded-table" style="border-collapse: collapse" <%}%>  width="100%">
			            <tr>
				          	<%for(int k=0;k<totalColumns;k++){%>
	           				<td width ="<%=clms[k].getColumnWidth()%>%" ></td>
	    	      			<%}%>
	          			</tr>
	          			
	          			
	          			<%
	          			String lStrClmnHdr = "";
                	    StyleVO sClmHdrOnEachPage = rvo.getStyle(IReportConstants.COLUMN_HEADER_ON_EACH_PAGE);
	            		boolean showClmHdrOnEachPage =  sClmHdrOnEachPage!=null && sClmHdrOnEachPage.getStyleValue() != null && sClmHdrOnEachPage.getStyleValue().equalsIgnoreCase("yes")?true:false;
	            		if(showClmHdrOnEachPage)
	            			lStrClmnHdr = "<thead class='ColumnHeaderOnEachPage'>"+rvo.getTableColumnHTMLFromTreeModel()+"</thead>";
	            		else
	            			lStrClmnHdr = rvo.getTableColumnHTMLFromTreeModel();	
                		%>
                	<%=lStrClmnHdr%>
	          		<%--rvo.getTableColumnHTMLFromTreeModel()--%> 	
                 
	          			
                    <%
	            	 }//....Print Page end ..

	            	 if(!printPage.equalsIgnoreCase("true") && showPagination)
	            	 {
		            	 stopIndex++;
			             if(i==startIndex)	 out.println(rvo.getTableColumnHTMLFromTreeModel());
	            	 }
                    i++ ;
	            }
            	
            	
            	
                String columnUrl = null;
                if( groupingEnabled )
                {
                    // Added By amit 
                   
                    // Added by Niteshwar on 27-11-2007 for grpHeader alignment
                    int liHdrAlign = grpclm.getAlignment();
                         
                    String lstrHdrAlign = rb.getHorizontalAlignment(liHdrAlign);
                    String lstrHdrvAlign = rb.getVerticalAlignment(liHdrAlign);
                    // End 
                    
                    
                    //rgclm = rdata.getValueAt(i,grpClm);
                    index = i;
            		if(sortingHelper != null)
                	{
                     	index = (sortingHelper.isSorted()) ? sortingHelper.getOriginalIndex(i) : i;	
                	}
                    ArrayList tempList = (ArrayList)rdata.getRow(index);
                    if(tempList != null && tempList.size()>0)
                	{
                    	Object treeObj = tempList.get(0);
                    	if(treeObj instanceof TreeData)
                		{
                    		DefaultMutableTreeNode root = ((TreeData)treeObj).getTreeRoot();
                    		Object userObj = root.getUserObject();
                    		ArrayList treeRootNodeList = (ArrayList)userObj;
                    		
                    		rgclm = treeRootNodeList.get(grpClm);
                		}
                    	else
                    	{
                    		rgclm = rdata.getValueAt(i,grpClm);
                    	}
                	}
                    gclm = (i==startIndex)?rgclm:gclm;
                    
                    addGrpRow = (rgclm != null && gclm!=null) ? ((rgclm.equals(gclm) && (i != startIndex)) ? false :true) : false;
                    
                    String tdRgclm = (String) rb.getDataValue(rgclm,grpClm,rdata);
                    
                    if( addGrpRow )
                    {
                      rowBkCount=0;
                      if(grpFlag)
                       {
                         if((rvo.getReportType() & IReportConstants.GU) == IReportConstants.GU && !printPage.equalsIgnoreCase("true"))
                        {  
                        columnUrl = rb.getColumnURL(rdata, grpClm,i);

                        if(columnUrl!=null)
                        {
							// added by 202414 for not open link on other page like attachment 
                        	boolean isAttachmentColumnDataType = false;
							if(grpclm.getDataType()== IReportConstants.ATTACHMENT)
							{
								isAttachmentColumnDataType = true;
							}
							if( i!=startIndex )
							{
            			%>
                        <%-- <tr><td colspan="<%=totalColumns%>" class="TableBorderBN TableRowFieldRBN">&nbsp;</td></tr> --%>
                        
                        
                        <tr style='<%=(i!=startIndex && pageBreakBeforeGrp) ? "page-break-before: always;":""%>'><td colspan="<%=totalColumns%>" class="TableBorderBN TableRowFieldRBN">&nbsp;</td></tr>
                       <%
							}
							if(i!=startIndex && pageBreakBeforeGrp)
							{
								out.println(rvo.getTableColumnHTMLFromTreeModel());
							}
                        %>
                        <tr bgcolor=#EFEFEF>
                        <td align='<%=lstrHdrAlign%>' valign='<%=lstrHdrvAlign%>' colspan="<%=totalColumns%>" class="HLabel TableBorderBN TableRowFieldRBN" style="<%=rb.getDataStyleString(rvo,rgclm)%>">
						<%
						if(isAttachmentColumnDataType)
						{
						%>
							<a href="<%=columnUrl%>" >
						<%
						}else{
						%>
							<a onclick="javascript:openWindow( '<%=columnUrl%>' )" href="#" >
						<%
						}
						%>
                        		<%-- =rb.getDataValue(rgclm)--%>
                        		<%= tdRgclm %>
                        	</a>
                        </td>
                        </tr>
                        <%// end of : 202414
                        }
                       } 
                       else
                       {
							if( i!=startIndex )
							{
                         %>
                         <%--  <tr><td colspan="<%=totalColumns%>" class="TableBorderBN TableRowFieldRBN">&nbsp;</td></tr> --%>
                         <tr style='<%=(i!=startIndex && pageBreakBeforeGrp) ? "page-break-before: always;":""%>'><td colspan="<%=totalColumns%>" class="TableBorderBN TableRowFieldRBN">&nbsp;</td></tr>
                       <%
							}
                       if(i!=startIndex && pageBreakBeforeGrp)
							{
							out.println(rvo.getTableColumnHTMLFromTreeModel());
							}
                        %>
                        <tr bgcolor=#EFEFEF>
                        <td align='<%=lstrHdrAlign%>' valign='<%=lstrHdrvAlign%>' colspan="<%=totalColumns%>" class="HLabel TableBorderBN TableRowFieldRBN" style="<%=rb.getDataStyleString(rvo,rgclm)%>">
                        <%-- =rb.getDataValue(rgclm) --%>
                        <%= tdRgclm %>
                        </td>
                        </tr>
                      <%
                        
                        }
                      }  
                       else
                       { 
							if( i!=startIndex )
							{
                         		if( pageBreakBeforeGrp && printPage.equalsIgnoreCase("true") )
                         		{
									out.println("<tr><td colspan="+totalColumns+" class='HLabel TableBorderBN TableRowFieldRBN'> &nbsp; </td></tr>");
									out.println("</table></td></tr>");
									out.println("<tr style='border: 0px solid white;'><td><table border='0'>");
									out.println("<tr style='page-break-before: always;'><td colspan="+totalColumns+" class='TableBorderBN TableRowFieldRBN'></td></tr>");
		                        	 out.println("</table></td></tr>");
                         		}
                         		else
                         		{
                         			%><tr style='<%=(i!=startIndex && pageBreakBeforeGrp) ? "page-break-before: always;":""%>'><td colspan="<%=totalColumns%>" class="TableBorderBN TableRowFieldRBN">&nbsp;</td></tr><%
                         		}
							}
                         %>
                        
                         <%--  <tr><td colspan="<%=totalColumns%>" class="TableBorderBN TableRowFieldRBN">&nbsp;</td></tr> --%>
                        
                        <%-- change by brijesh start--%>
                        <% if(pageBreakBeforeGrp) {
                        
                        		if( i!=startIndex && printPage.equalsIgnoreCase("true") ){
                        %>
                        			<tr><td valign="top">
						            <table border="<%=borderWidht%>" cellspacing="0" bgcolor='white' <%if(showBorder){%> class="TableBorderLTRBN padded-table" style="border-collapse: collapse" <%}%>  width="100%">
			    				        <tr>
								          	<%for(int k=0;k<totalColumns;k++){%>
	           								<td width ="<%=clms[k].getColumnWidth()%>%" ></td>
					    	      			<%}%>
	          							</tr>
	          						<% } %>				
                        <tr bgcolor=#EFEFEF>
                        <td align='<%=lstrHdrAlign%>' valign='<%=lstrHdrvAlign%>' colspan="<%=totalColumns%>" class="HLabel TableBorderBN TableRowFieldRBN" style="<%=rb.getDataStyleString(rvo,rgclm)%>">
                        <%-- =rb.getDataValue(rgclm) --%>
                        <%= tdRgclm %>
                        </td>
                        </tr>
                        <%} %>
                        <%
                        if( i==startIndex || pageBreakBeforeGrp )
                        {
                        	out.println(rvo.getTableColumnHTMLFromTreeModel());
						}
                        %>
                        <% if(!pageBreakBeforeGrp) {%>
                        
                        	<%if(i==startIndex)
                        	{
                        		%><tr><td colspan="<%=totalColumns%>" class="HLabel TableBorderBN TableRowFieldRBN"> &nbsp; </td></tr><%
                        	} 
                        	%>
                        <tr bgcolor=#EFEFEF>
                        <td align='<%=lstrHdrAlign%>' valign='<%=lstrHdrvAlign%>' colspan="<%=totalColumns%>" class="HLabel TableBorderBN TableRowFieldRBN" style="<%=rb.getDataStyleString(rvo,rgclm)%>">
                        <%-- =rb.getDataValue(rgclm) --%>
                        <%= tdRgclm %>
                        </td>
                        </tr>
                        <%} %>
                        <%-- change by brijesh end--%>
                      <% }
                    }
                    else
                    {
                    	%>
                    	<%=((i == startIndex)?rvo.getTableColumnHTMLFromTreeModel():"")%>
                    	<%
                    }
                }
                else
                {
                	
                	String lStrClmnHdr = "";
                	if(i == startIndex)
                	{	
	                	StyleVO sClmHdrOnEachPage = rvo.getStyle(IReportConstants.COLUMN_HEADER_ON_EACH_PAGE);
	            		boolean showClmHdrOnEachPage =  sClmHdrOnEachPage!=null && sClmHdrOnEachPage.getStyleValue() != null && sClmHdrOnEachPage.getStyleValue().equalsIgnoreCase("yes")?true:false;
	            		if(showClmHdrOnEachPage)
	            			lStrClmnHdr = "<thead class='ColumnHeaderOnEachPage'>"+rvo.getTableColumnHTMLFromTreeModel()+"</thead>";
	            		else
	            			lStrClmnHdr = rvo.getTableColumnHTMLFromTreeModel();	
                	}
                	
                	%>
                	<%=lStrClmnHdr%>
                	<%--=((i == startIndex)?rvo.getTableColumnHTMLFromTreeModel():"")--%>
                	<%
                }
                
                if( rvo instanceof SubReportVO )
                {
                className = "TablerowBG1";
                
                }
                else
                {
                className = "TablerowBG" + ((rowBkCount%2)+1);
                }
                rowBkCount++;
                
                // Added by Dharmesh Gohil to disable row Backgroupd
                StyleVO cStyle = rvo.getStyle( IReportConstants.ROW_HIGH_LIGHT );
                boolean highLightRow = cStyle!=null && cStyle.getStyleValue() != null && cStyle.getStyleValue().equalsIgnoreCase("No")?false:true;
                className = highLightRow ? className:"";
                
                int tmpTotalColumns = 0;
                boolean  isTreeData = false;
                if(rdata.getValueAt(i,0) instanceof TreeData)
            	{
            		Object tempTreeData = rdata.getValueAt(i,0);
            		String treeDataTable = (String) rb.getTreeData(tempTreeData,rvo,rdata,i,totalRecords,printPage);
            		out.print(treeDataTable);
            		
            		tmpTotalColumns = totalColumns;
            		totalColumns = 0;
            		isTreeData = true;
            		//continue;
            	}
                else {
                %>
                <tr class="<%=className%>">
                <%
                }
                for( int j = 0; j < totalColumns; j++ )
                {
                	int colSpan = 1;
                					
                    ReportColumnVO clm = clms[j];
                    //code modification by kalyan starts here
                    String hidden=(String)clm.getHidden();
                    String uRLValue=(String)clm.getURLValueColumn();
                    String uRLConCol=(String)clm.getURLCondColumn();
                    int liColAlign=clm.getAlignment();
                 
                        
                    String lstrColAlign = rb.getHorizontalAlignment(liColAlign);
                    String lstrColvAlign = rb.getVerticalAlignment(liColAlign);
                    data = rdata.getValueAt(i,clm.getColumnName());
                    if( data instanceof StyledData)
                    {
                    	colSpan = ( (StyledData) data ).getColspan();
                    	j = j + (colSpan-1);
                    }
                  
                    tdclass = ((i+1)==totalRecords) ? "TableBorderBN TableRowFieldRBN" : "TableRowFieldBBN TableRowFieldRBN";                    
                    tdclass = highLightRow ? tdclass:"";
                	String urlStyleValue = rb.getStyleValue(IReportConstants.STYLE_URL, data);
                   
               		String tdData = (String) rb.getDataValue( data,rdata.getColumnIndex((clm.getColumnName())),rdata );
				    
                    if((rvo.getReportType() & IReportConstants.GU) == IReportConstants.GU && !printPage.equalsIgnoreCase("true"))
                    {
                        columnUrl = rb.getColumnURL(rdata, j, i);                    
                        if(columnUrl != null)
                        {
	                       	// START : 202414 used  for not open in the another window
                        	boolean isAttachmentColumnDataType = false;
	                        if(clm.getDataType()== IReportConstants.ATTACHMENT)
	                       	{
	                        	isAttachmentColumnDataType = true;
	                       	}
                   %>
                   <td colspan="<%=colSpan%>" align='<%=lstrColAlign%>' valign='<%=lstrColvAlign%>' class="<%=tdclass%> Label2"  
                   							style="<%=rb.getDataStyleString(rvo,data)%>">
	                        <%
	         	             if(isAttachmentColumnDataType)
	                        {
	                        %>
	                        	<a  href="<%=columnUrl%>" >
	                        <%
	         	             }else
	                        {
	                        %>
		                        <a onclick="javascript:openWindow( '<%=columnUrl%>' )" href="#" >
	                        <%
	                        }
	                        %>
	                        		<%-- =rb.getDataValue(data) --%>
	                        		<%= tdData %>
	                        	</a> 
                        </font>
                   </td>
                        <%// END OF : 202414
                        }
                        else
                        {
                        %>
                        <td colspan="<%=colSpan%>" align='<%=lstrColAlign%>' valign='<%=lstrColvAlign%>' class="<%=tdclass%> Label2"  style="<%=rb.getDataStyleString(rvo,data)%>">
                        <%
                        	//check if there is STYLE_URL is configured.
                        	if(urlStyleValue!=null)
                        	{
                        	    out.println("<a onclick='javascript:openWindow( \""+urlStyleValue+"\" )' href='#'>");
                        	}
                        %>
                        <%-- =rb.getDataValue(data) --%>
                        <%= tdData %>
                        <%
	                    	//if there is STYLE_URL is configured then close 'a'.
	                        if(urlStyleValue!=null)
	                    	{
	                    	    out.println("</a>");
	                    	}
                        %>
                        </td>
                        <%
                        }
                    }
                    else
                    {
                    	
                    %>
                    <td colspan="<%=colSpan%>" align='<%=lstrColAlign%>' valign='<%=lstrColvAlign%>' class="<%=tdclass%> Label2"  style="<%=rb.getDataStyleString(rvo,data)%>">
                        <%
                        	//check if there is STYLE_URL is configured.
                        	if(urlStyleValue!=null)
                        	{
                        	    out.println("<a onclick='javascript:openWindow( \""+urlStyleValue+"\" )' href='#'>");
                        	}
                        %>
                        <%-- =rb.getDataValue(data) --%>
                        <%= tdData %>
                        <%
	                    	//if there is STYLE_URL is configured then close 'a'.
	                        if(urlStyleValue!=null)
	                    	{
	                    	    out.println("</a>");
	                    	}
                        %>
                    </td>
                    <%   
                    }
                      data = "";
                    //}
                    //}
                } //        for j=...loop end...
                
                if(isTreeData) totalColumns = tmpTotalColumns;
                else {
		                %>
		                </tr>
		                <%
                }
                
                boolean addGrpRowTrue = ( groupingEnabled && (((!addGrpRow) && (i<(totalRecords-1))) || ((i == startIndex) && (i<(totalRecords-1)))));
                boolean addGrpRowFalse = ( groupingEnabled && ((addGrpRow && (i<(totalRecords-1))) || ((i == startIndex) && (i<(totalRecords-1)))));
                Object rgclm1 = null;
                if(addGrpRowTrue || addGrpRowFalse)
                {
                	int tempVal = i+1;	
                	if(fechDataOnPgReq) 
            		{
            			tempVal = (tempVal>=stopIndex?i:tempVal);
            		}
                	index = tempVal;
                	if(sortingHelper != null)
                	{
                     	index = (sortingHelper.isSorted()) ? sortingHelper.getOriginalIndex(tempVal) : tempVal;	
                	}
                    ArrayList tempList = (ArrayList)rdata.getRow(index);
                    if(tempList != null && tempList.size()>0)
                	{
                    	Object treeObj = tempList.get(0);
                    	if(treeObj instanceof TreeData)
                		{
                    		DefaultMutableTreeNode root = ((TreeData)treeObj).getTreeRoot();
                    		Object userObj = root.getUserObject();
                    		ArrayList treeRootNodeList = (ArrayList)userObj;
                    		
                    		rgclm1 = treeRootNodeList.get(grpClm);
                		}
                    	else
                    	{
                    		rgclm1 = rdata.getValueAt(tempVal,grpClm);
                    	}
                	}
                }
               	if(addGrpRowTrue)
                {
                    addTotalRow = (rgclm1 != null && gclm!=null) ? ((rgclm1.equals(gclm)) ? false :true) : false;
                }
                else if(addGrpRowFalse)
                {
                    addTotalRow = (rgclm1 != null && rgclm!=null) ? ((rgclm1.equals(rgclm)) ? false :true) : false;
                    if( addTotalRow ) gclm = rgclm;
                }
                else
                {
                    addTotalRow=false;
                    if( (groupingEnabled && (i==(totalRecords-1))) )  gclm = rgclm;
                }
                if(addTotalRow || (groupingEnabled && (i==(totalRecords-1))))
                {
                	if( (rdata.showEvaluationRows() & IReportConstants.GROUP_ROW_COUNT) ==IReportConstants.GROUP_ROW_COUNT)
                    {
                    	%>
                        <%=rb.getGroupRowCountHTML(rdata,0,grpClm,gclm,totalLineLabel,localStringsBundle.getString("Report.label.totalGroupRows"))%>
                        <%
                    }
                	
                    if( (rdata.showEvaluationRows() & IReportConstants.SUMMATION) ==IReportConstants.SUMMATION)
                    {
                    	%>
                        <%=rb.getTotalRowHTML(rdata,0,grpClm,gclm,totalLineLabel,localStringsBundle.getString("Report.label.totalLineLabel"))%>
                        <%
                    }
                    if( (rdata.showEvaluationRows() & IReportConstants.AVERAGE) ==IReportConstants.AVERAGE)
                    {
                        %>
                        <%=rb.getAverageRowHTML(rdata,0,grpClm,gclm,totalLineLabel,localStringsBundle.getString("Report.label.averageLineLabel"))%>
                        <%
                    }
                    if( (rdata.showEvaluationRows() & IReportConstants.MAXIMUM) ==IReportConstants.MAXIMUM)
                    {
                        %>
                        <%=rb.getMaximumRowHTML(rdata,0,grpClm,gclm,totalLineLabel,localStringsBundle.getString("Report.label.maximumLineLabel"))%>
                        <%
                    }
                    if( (rdata.showEvaluationRows() & IReportConstants.MINIMUM) ==IReportConstants.MINIMUM)
                    {
                        %>
                        <%=rb.getMinimumRowHTML(rdata,0,grpClm,gclm,totalLineLabel,localStringsBundle.getString("Report.label.minimumLineLabel"))%>
                        <%
                    }
                    if( !addTotalRow )
                    {
                    %>
                    <tr><td colspan="<%=totalColumns%>" class="TableBorderBN">&nbsp;</td></tr>
                    <%
                    }
                }
                if( addGrpRow )
                {
                    gclm = rgclm;
                }
                if(isTreeData) continue;
            }
         if(showGrandTotal) 
         {
            if( (printPage.equalsIgnoreCase("true")) ? ((rdata.showEvaluationRows() & IReportConstants.SUMMATION) ==IReportConstants.SUMMATION) : ((currPage==maxPage) && ((rdata.showEvaluationRows() & IReportConstants.SUMMATION) ==IReportConstants.SUMMATION)))
            { 
                
                %>
                <tr bgcolor=#EFEFEF>
                <%  
                String lstrTotal = "";
                for( int j = 0; j < totalColumns; j++ )
                {          
                    ReportColumnVO clm = rvo.getColumnsToDisplay()[j];
                    int liColAlign=clm.getAlignment();
           
                    String lstrColAlign = rb.getHorizontalAlignment(liColAlign);
 					String lstrColvAlign = rb.getVerticalAlignment(liColAlign);
  
                    if((clm.getDisplayTotal() & IReportConstants.SUMMATION) == IReportConstants.SUMMATION)                
                    {
                    	lstrTotal=rdata.getEvaluationResults(rdata.getColumnIndex(clm.getColumnName()),IReportConstants.SUMMATION);
                        %>
                          <td align='<%=lstrColAlign%>' valign='<%=lstrColvAlign%>'  class="TableBorderBN TableRowFieldRBN" style='border:<%=borderWidht%>px;'><font class="HLabel"><%=lstrTotal%> </font></td>
                        <%       
                    }
                    else
                    {
                       // Added by Prachi Shah to display word total in first column
                       //Changed by samir for user defined evaluation labels on 2-June-2006
                        if(j==0)
                        {
                            totalLineLabel = rvo.getLabelHashTable(0,IReportConstants.SUMMATION);
                            if(totalLineLabel != null && !totalLineLabel.equalsIgnoreCase(""))
                            {
                              %>
                                <td align='<%=lstrColAlign%>' valign='<%=lstrColvAlign%>'  class="TableBorderBN TableRowFieldRBN" style='border:<%=borderWidht%>px;'><font class="HLabel"><%=totalLineLabel%> </font></td>
                                <%   
                                
                            }
                            else
                            {
                                %>
                                <td align='<%=lstrColAlign%>' valign='<%=lstrColvAlign%>' class="TableBorderBN TableRowFieldRBN" style='border:<%=borderWidht%>px;'><font class="HLabel"><%=localStringsBundle.getString("Report.label.totalLineLabel")%> </font></td>
                                <%  
                            }
                        }
                        else
                        {
                          %>
                          <td class="TableBorderBN TableRowFieldRBN" style='border:<%=borderWidht%>px;'>&nbsp;</td>
                          <%
                        }
                    }
                }
                %>  
                </tr>
                <%
                
            }   
            
            if( (printPage.equalsIgnoreCase("true")) ? ((rdata.showEvaluationRows() & IReportConstants.AVERAGE) ==IReportConstants.AVERAGE) : ((currPage==maxPage) && ((rdata.showEvaluationRows() & IReportConstants.AVERAGE) ==IReportConstants.AVERAGE)))
            {
                %>
                <tr bgcolor=#EFEFEF>
                <%  
                String lstrTotal = "";
                for( int j = 0; j < totalColumns; j++ )
                {          
                    ReportColumnVO clm = rvo.getColumnsToDisplay()[j];
                    int liColAlign=clm.getAlignment();
        
                     String lstrColAlign = rb.getHorizontalAlignment(liColAlign);
					 String lstrColvAlign = rb.getVerticalAlignment(liColAlign);  
                    
                    if((clm.getDisplayTotal() & IReportConstants.AVERAGE) == IReportConstants.AVERAGE)                
                    {
                        lstrTotal=rdata.getEvaluationResults(rdata.getColumnIndex(clm.getColumnName()),IReportConstants.AVERAGE);
                        %>
                        <td align='<%=lstrColAlign%>' valign='<%=lstrColvAlign%>' class="TableBorderBN TableRowFieldRBN" style='border:<%=borderWidht%>px;'><font class="HLabel"><%=lstrTotal%> </font></td>
                        <%       
                    }
                    else
                    {
                         //Changed by samir for user defined evaluation labels on 2-June-2006
                        if(j==0)
                        {
                            totalLineLabel = rvo.getLabelHashTable(0,IReportConstants.AVERAGE);
                            if(totalLineLabel != null && !totalLineLabel.equalsIgnoreCase(""))
                            {
                              %>
                                <td align='<%=lstrColAlign%>' valign='<%=lstrColvAlign%>' class="TableBorderBN TableRowFieldRBN" style='border:<%=borderWidht%>px;'><font class="HLabel"><%=totalLineLabel%> </font></td>
                                <%   
                                
                            }
                            else
                            {
                                %>
                                <td align='<%=lstrColAlign%>' valign='<%=lstrColvAlign%>' class="TableBorderBN TableRowFieldRBN" style='border:<%=borderWidht%>px;'><font class="HLabel"><%=localStringsBundle.getString("Report.label.averageLineLabel")%> </font></td>
                                <%  
                            }
                            
                        }
                        else
                        {
                          %>
                          <td class="TableBorderBN TableRowFieldRBN" style='border:<%=borderWidht%>px;'>&nbsp;</td>
                          <%
                        }
                    }
                }
                %>  
                </tr>
                <%
            }    
            
            if( (printPage.equalsIgnoreCase("true")) ? ((rdata.showEvaluationRows() & IReportConstants.MAXIMUM) ==IReportConstants.MAXIMUM) : ((currPage==maxPage) && ((rdata.showEvaluationRows() & IReportConstants.MAXIMUM) ==IReportConstants.MAXIMUM)))
            {
                %>
                <tr bgcolor=#EFEFEF>
                <%  
                String lstrTotal = "";
                for( int j = 0; j < totalColumns; j++ )
                {          
                    ReportColumnVO clm = rvo.getColumnsToDisplay()[j];
                    int liColAlign=clm.getAlignment();
            
                      String lstrColAlign = rb.getHorizontalAlignment(liColAlign);
					  String lstrColvAlign = rb.getVerticalAlignment(liColAlign);
                    
                    if((clm.getDisplayTotal() & IReportConstants.MAXIMUM) == IReportConstants.MAXIMUM)                
                    {
                        lstrTotal=rdata.getEvaluationResults(rdata.getColumnIndex(clm.getColumnName()),IReportConstants.MAXIMUM);
                        %>
                        <td align='<%=lstrColAlign%>' valign='<%=lstrColvAlign%>' class="TableBorderBN TableRowFieldRBN" style='border:<%=borderWidht%>px;'><font class="HLabel"><%=lstrTotal%> </font></td>
                        <%       
                    }
                    else
                    {
                        //Changed by samir for user defined evaluation labels on 2-June-2006
                        if(j==0)
                        {
                            totalLineLabel = rvo.getLabelHashTable(0,IReportConstants.MAXIMUM);
                            if(totalLineLabel != null && !totalLineLabel.equalsIgnoreCase(""))
                            {
                              %>
                                <td align='<%=lstrColAlign%>' valign='<%=lstrColvAlign%>' class="TableBorderBN TableRowFieldRBN" style='border:<%=borderWidht%>px;'><font class="HLabel"><%=totalLineLabel%> </font></td>
                                <%   
                                
                            }
                            else
                            {
                                %>
                                <td align='<%=lstrColAlign%>' valign='<%=lstrColvAlign%>'  class="TableBorderBN TableRowFieldRBN" style='border:<%=borderWidht%>px;'><font class="HLabel"><%=localStringsBundle.getString("Report.label.maximumLineLabel")%> </font></td>
                                <%  
                            } 
                            
                        }
                        else
                        {
                          %>
                          <td class="TableBorderBN TableRowFieldRBN" style='border:<%=borderWidht%>px;'></td>
                          <%
                        }
                    }
                }
                %>  
                </tr>
                <%
            }        
            
            if( (printPage.equalsIgnoreCase("true")) ? ((rdata.showEvaluationRows() & IReportConstants.MINIMUM) ==IReportConstants.MINIMUM) : ((currPage==maxPage) && ((rdata.showEvaluationRows() & IReportConstants.MINIMUM) ==IReportConstants.MINIMUM)))
            {
                %>
                <tr bgcolor=#EFEFEF>
                <%  
                String lstrTotal = "";
                for( int j = 0; j < totalColumns; j++ )
                {          
                    ReportColumnVO clm = rvo.getColumnsToDisplay()[j];
                    int liColAlign=clm.getAlignment();
                    
                    String lstrColAlign = rb.getHorizontalAlignment(liColAlign);
                    String lstrColvAlign = rb.getVerticalAlignment(liColAlign);
                    
                    if((clm.getDisplayTotal() & IReportConstants.MINIMUM) == IReportConstants.MINIMUM)                
                    {
                        lstrTotal=rdata.getEvaluationResults(rdata.getColumnIndex(clm.getColumnName()),IReportConstants.MINIMUM);
                        %>
                        <td align='<%=lstrColAlign%>' valign='<%=lstrColvAlign%>' class="TableBorderBN TableRowFieldRBN" style='border:<%=borderWidht%>px;'><font class="HLabel"><%=lstrTotal%> </font></td>
                        <%       
                    }
                    else
                    {
                        if(j==0)
                        {
                            //Changed by samir for user defined evaluation labels on 2-June-2006
                            totalLineLabel = rvo.getLabelHashTable(0,IReportConstants.MINIMUM);
                            if(totalLineLabel != null && !totalLineLabel.equalsIgnoreCase(""))
                            {
                              %>
                                <td align='<%=lstrColAlign%>' class="TableBorderBN TableRowFieldRBN" style='border:<%=borderWidht%>px;'><font class="HLabel"><%=totalLineLabel%> </font></td>
                                <%   
                                
                            }
                            else
                            {
                                %>
                                <td align='<%=lstrColAlign%>' class="TableBorderBN TableRowFieldRBN" style='border:<%=borderWidht%>px;'><font class="HLabel"><%=localStringsBundle.getString("Report.label.minimumLineLabel")%> </font></td>
                                <%  
                            }  
                            
                        }
                        else
                        {
                          %>
                          <td class="TableBorderBN TableRowFieldRBN" style='border:<%=borderWidht%>px;'>&nbsp;</td>
                          <%
                        }
                    }
                }
                %>  
                </tr>
                <%
            }
            }
        }
        else
        {
            %>Null ReportData<%
        }
        %>
    </table>
    </center>
    </td></tr>
    <%-- added by jeekesh for in total words --%>
    <%
    if ( (totalRecords>0) && rvo.getGrandTotalTemplate() != null)
    {
    	if ( printPage.equalsIgnoreCase("true") || currPage == maxPage )
    	{
	    	TabularData tabularData = rvo.getGrandTotalTemplate();
	    	String  lStrGrandTotalTemplateHTML = rb.getHTMLForTotalInWords(tabularData, rdata);
	    	%><tr><td><% 
	    	out.println(lStrGrandTotalTemplateHTML);
	    	%></td></tr><% 
    	}

    }	
    %>
	<%--End added by jeekesh for in total words --%>
	
    <%-- Include navigation jsp only if it is not opened for print --%>
    <%
    if( (totalRecords>0) && (!printPage.equalsIgnoreCase("true") && showPagination) )
    {
       //Added by samir for Navigation Bar alignment
       String lstrNvgAlign = defaultNavigationAlignment;
       StyleVO nvgStyle = rvo.getStyle( IReportConstants.NAVIGATION_ALIGNMENT );
       if(nvgStyle != null && nvgStyle.getStyleValue() != null)
       {
         int liNvgAlign = Integer.parseInt(nvgStyle.getStyleValue());
         if( ( liNvgAlign & IReportConstants.ALIGN_LEFT ) == IReportConstants.ALIGN_LEFT )
         {
            lstrNvgAlign = "left";
            request.setAttribute("UpperBar","0%");
            request.setAttribute("LowerBar","100%");
         }    
         else if( ( liNvgAlign & IReportConstants.ALIGN_RIGHT) == IReportConstants.ALIGN_RIGHT )
         {
            lstrNvgAlign = "right";
            request.setAttribute("UpperBar","100%");
            request.setAttribute("LowerBar","0%");
         }    
         else if( ( liNvgAlign & IReportConstants.ALIGN_CENTER ) == IReportConstants.ALIGN_CENTER )  
         {
            lstrNvgAlign = "middle";   
            request.setAttribute("UpperBar","50%");
            request.setAttribute("LowerBar","50%");            
         }    
       } 
       else
       {
           request.setAttribute("UpperBar","0%");
           request.setAttribute("LowerBar","100%");
       }
       //End-Added by samir for Navigation Bar alignment
       request.setAttribute("navLoc","footer");
    %>
    <tr><td align="<%=lstrNvgAlign%>"  ><%@include file="navigation.jsp"%></td></tr>
    <%
        }
    	strMainTitle = null;
    	data = "";
    	objHdrList = null;
    	
    	if(pageBreakBefore && pageBreak != null && showGrandTotal)
    	{
    		if(!(styleSubRptHeader == null && styleSubRptFooter == null) &&
    				rvo != null && rvo instanceof SubReportVO )
   	   		{
    			boolean showSubRptFooter = (styleSubRptFooter != null && styleSubRptFooter.getStyleValue() != null &&
        	  			styleSubRptFooter.getStyleValue().equalsIgnoreCase("yes")?true:false);
    			if(showSubRptFooter) 
    			{
       	   			request.setAttribute("subReportCode",rvo.getReportCode());	
	       	   		%><tr><td align="left" style="padding: 5px;"><%@include file="rptfooter.jsp"%></td></tr><%
    			}
   	   		}
    		else if (rCnt < (reptList.size()-1) && pgBreakSubRptList.get(rCnt+1).equals("no")) ;
    		else
    		{
    			%><tr><td align="left" style="padding: 5px;"><%@include file="rptfooter.jsp"%></td></tr><%
    		}
    	}
    	else
    	{
    			if( showGrandTotal && (!noPageBreak &&(pgBreakSubRptList.get((rCnt+1 != reptList.size())?rCnt+1:rCnt).equals("yes") || (rCnt == reptList.size()-1)  ) )  )
    			{
    				%><tr><td align="left" style="padding: 5px;"><%@include file="rptfooter.jsp"%></td></tr><%
    			}
    	}
    	
      }
     }
    %>
    	<%
    	if(noPageBreak){
    		%><tr><td align="left" style="padding: 5px;"><%@include file="rptfooter.jsp"%></td></tr> <%
    	}
    	%>
    </table>
</center>

<script type="text/javascript">
var imagePath = "${rimg}";
collapseAllRowsRpt();
</script>
