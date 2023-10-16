<%@page import="java.util.ResourceBundle"%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<%
String reportURI = (String) request.getAttribute("reportURI");
String rptCode = request.getParameter("reportCode");
String pageType = (String) request.getAttribute("pageType");
ResourceBundle rptToolsLocalBundle = (ResourceBundle) request.getAttribute("localStringsBundleNav");
		
		int currentPage = Integer.parseInt(request.getAttribute("currPageNav").toString() );
		int totRowsNav = Integer.parseInt(request.getAttribute("totRecordsNav").toString() );
		int pageSize = Integer.parseInt(request.getAttribute("pgSizeNav").toString() );
		int totPages = (int) Math.ceil(((float)totRowsNav/(float)pageSize));
		
		int mid = 0;
		int start = 0;
		int tmpEnd = 0;
		int end = 0;
		String firstPageNo = "1&nbsp;" ;
		String lastPageNo = "&nbsp;"+ totPages;
		int nvColSpan = 8;
		
		if(totPages < 9)
		{
			start = 1;
			end = totPages;
			firstPageNo = "";
			lastPageNo = "";
			nvColSpan = 7;
		}
		else if(currentPage == 0)
		{
			start = 1;
			end = start + 8;
		}
		else if( ((currentPage) == totPages)  || ((currentPage+4) > totPages))
		{
			end = totPages;
			start = totPages-8;
		}
		else
		{
				mid = (currentPage-3);
				start = ( (mid<=0) ? 1 : mid );
				tmpEnd = (start + 8);
				end = ( (tmpEnd>totPages) ? totPages : tmpEnd ) ;
		}

String navLoc = request.getAttribute("navLoc").toString();
String formName = "navForm" + navLoc ;
String txtEnterPage = "txtEnterPage" ;
String btnGO = "btnGo" + navLoc ;
String gotoFun = "goToPage" + navLoc;
String totPagesLength = (""+(""+totPages).length());
%>

<script type="text/javascript" >

function <%=gotoFun%>(obj)
{
	var tID = obj.id;
	var pageNo = "";
	var vTotalPages = <%=totPages%>;

	if(tID.indexOf("header") != -1)
	{
		pageNo = document.getElementById("<%= txtEnterPage %>header").value-1;
	}
	else 
	{
		pageNo = document.getElementById("<%= txtEnterPage %>footer").value-1;
	}
	
	if(pageNo != -1)
	{
		if(pageNo > vTotalPages ) pageNo = vTotalPages-1;
		document.forms[0].action = "<%=reportURI%>&reportCode=<%=rptCode%>&action=gotoPage&pageType=<%=pageType%>&gotoPageNO="+pageNo;
		document.forms[0].submit();
	}
}
function doPagination(lStrUrl)
{
	document.forms[0].action = lStrUrl;
	document.forms[0].submit();
}
</script>

<table id="navTable" width="100%" cellpadding="0" cellspacing="0" class="rptNavigationTable">
<hdiits:form method="POST" name="<%=formName%>" validate="true">
	
	<tr>
			<td width='<%=request.getAttribute("UpperBar")%>'></td>
			<td valign="middle" class="Label3" colspan="<%=nvColSpan%>">
            <%= ((String)request.getAttribute("navtext")).replaceAll(" ", "&nbsp") %>
    		</td>
	</tr>
	
	<tr>
    		<td width='<%=request.getAttribute("UpperBar")%>'></td>
    		<td valign="middle" width="75">
    			  <%= rptToolsLocalBundle.getString("ReportNav.label.Pages") %>&nbsp;	
    		</td>
    		<%
    		String lStrUrlfirstPage = reportURI+"&reportCode="+rptCode+"&action=firstPage&pageType="+pageType;
       		String lStrUrlpreviousPage = reportURI+"&reportCode="+rptCode+"&action=previousPage&pageType="+pageType;
    		%>
		    <td width="25" valign="middle">
			      <a class='rptNavigationAnch' href="#" onclick="doPagination('<%=lStrUrlfirstPage%>')"><%=firstPageNo%><img src="${rimg}/first.gif" alt="first" border="0"></a>
		    </td>
		    <td width="12" valign="middle">
			      <a class='rptNavigationAnch' href="#" onclick="doPagination('<%=lStrUrlpreviousPage%>')"><img src="${rimg}/previous.gif" alt="previous" border="0"></a>
		    </td>
    
    <%-- 
    <td valign="middle" align="center" class="Label3">
      <%= ((String)request.getAttribute("navtext")).replaceAll(" ", "&nbsp") %>
    </td>
    --%>
    
     <%
	     out.print("<td align='center' class='Label3' valign='middle' style='white-space: nowrap;'>");
    		for(int x=start;x<=end;x++)
    		{
    			if(x!=start) out.print("&nbsp;");
	    		if(  x== (currentPage+1) )
    			{
    				out.print("<font class='rptNavigationSelected'>"+ x +"</font>");
    			}
	    		else
    			{
	    			String lStrUrlCurrPage = reportURI+"&reportCode="+rptCode+"&action=gotoPage&pageType="+pageType+"&gotoPageNO="+(x-1);
	    			out.print("<a class='rptNavigationAnch' href='#' onclick=doPagination('"+lStrUrlCurrPage+"') >" +(x)+ "</a>");
    			}
	    	}
   		out.print("</td>");	
   		String lStrUrlnextPage = reportURI+"&reportCode="+rptCode+"&action=nextPage&pageType="+pageType;
   		String lStrUrllastPage = reportURI+"&reportCode="+rptCode+"&action=lastPage&pageType="+pageType;
    %>
    
	    <td width="12" valign="middle">
		      <a class='rptNavigationAnch' href="#" onclick="doPagination('<%=lStrUrlnextPage%>')"><img src="${rimg}/next.gif" alt="next" border="0"></a>
    	</td>
	    <td width="12" valign="middle">
    		  <a class='rptNavigationAnch' href="#" onclick="doPagination('<%=lStrUrllastPage%>')"><img src="${rimg}/last.gif" alt="last" border="0"><%=lastPageNo%></a>
	    </td>
    
     <%
  		if(totPages>9)
  		{
  			%>
  				<td valign="middle">&nbsp;
  					 <nobr><%= rptToolsLocalBundle.getString("ReportNav.label.pagenoInput") %>
					 <hdiits:text id='<%= (txtEnterPage+navLoc) %>' name='<%= (txtEnterPage+navLoc) %>' onkeypress="return checkNumber()" size="5" maxlength='<%=totPagesLength%>'  />
  				     <hdiits:button type="button" id='<%=btnGO%>' name='<%=btnGO%>' caption='<%= rptToolsLocalBundle.getString("Report.button.go") %>' onclick='<%= (gotoFun+"(this);") %>' ></hdiits:button>
  					 </nobr>
  				</td>
  			<%
  		}
  %>
  
  <td width='<%=request.getAttribute("LowerBar")%>' ></td>
</tr>
</hdiits:form>
</table>
