<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ page import="java.util.StringTokenizer" %>

<% try { %>

<fmt:setBundle basename="resources.FullTextSearchLables" var="searchLabels" scope="request"/>

<script type="text/javascript" src="<c:url value="/script/common/fullTextSearch.js"/>">
</script>

<script language="javascript">	
	
	function generateReport()
	{
		/*if(!validateForm_frmFullTextSearch()) {
			return false;
		}*/
		
		var cboxes = document.getElementsByTagName('input');		
		var countCB = 0;
		var mulCB = false;
		var cbChecked = false;
		
		for(var i=0; i<cboxes.length; i++) {				
			if(cboxes[i].type == 'checkbox' && cboxes[i].checked) {
				cbChecked = true;
				if(cboxes[i].name != 'fts_All') {
					if(++countCB == 2) {
						mulCB = true;
					}
				}
			}
		}
		
		if(!cbChecked) {
			alert('<fmt:message bundle="${searchLabels}" key="FTSEARCH.SELECTTYPE"/>');
			return false;
		}		

		// Search TextBox Validation
		var searchTxtAllObj = document.getElementById('txtSearchCritAllWord');
		var searchTxtAll = searchTxtAllObj.value;
		var searchTxtAnyObj = document.getElementById('txtSearchCritAnyWord');
		var searchTxtAny = searchTxtAnyObj.value;		
		
		if(isFieldValueNull(searchTxtAll) && isFieldValueNull(searchTxtAny)) {
			alert('<fmt:message bundle="${searchLabels}" key="FTSEARCH.SEARCHTXT.REQUIRED"/>');
			searchTxtAllObj.focus();
			return false;			
		}		
		
		var subUrl;
		var cVal;
		
		if(mulCB) {
			cVal = document.getElementById("fts_All").value;
				
			subUrl = "${pageContext.request.contextPath}/ifms.htm?actionFlag=reportService&reportCode=" + cVal + "&action=generateReport&FromParaPage=TRUE";				
			for(var i=0; i<cboxes.length; i++) {				
				if(cboxes[i].type == 'checkbox' && cboxes[i].checked && cboxes[i].name != 'fts_All') {	
					subUrl = subUrl + "&subReportCode=" + cboxes[i].value + "&subReportType=" + cboxes[i].name.substring(4); 
				}				
			}
			subUrl = subUrl + "&searchAll=true&advanceSearch=Y";
		}		
		else {
			for(var i=0; i<cboxes.length; i++) {				
				if(cboxes[i].type == 'checkbox' && cboxes[i].checked) {
					cVal = cboxes[i].value;	
					var hiddenObj = document.getElementById("custUrl_" + cboxes[i].name);
					//alert('hiddenObj : ' + hiddenObj);
					// Check if custUrl hidden filed is present or not.
					if(hiddenObj == null) {
						subUrl = "${pageContext.request.contextPath}/ifms.htm?actionFlag=reportService&reportCode=" + cVal 
							 	+ "&action=generateReport&FromParaPage=TRUE&advanceSearch=Y";
					}
					else {
						subUrl = "${pageContext.request.contextPath}/" + hiddenObj.value
								 + "&fromFTSearch=Y&ftSearchReportCode=" + cVal + "&advanceSearch=Y";					
					}					
					break;
				}
			}
		}
		
		//alert("Final URL :" + subUrl);
		showProgressbar();		
		document.forms['frmFullTextSearch'].action = subUrl;
		document.forms['frmFullTextSearch'].submit();
	}
	
</script>

<hdiits:form name="frmFullTextSearch" validate="true" method="POST">
<TABLE class="tabtable" border="0" style="width: 70%;" align="center">
<tr> <td>
<hdiits:fieldGroup id="fullTextSearch" bundle="${searchLabels}" titleCaptionId="FTSEARCH.LEGEND" expandable="false">
<TABLE class="tabtable" border="0" style="width: 30%;">
	<tr>
		<% 
			String val = "1032";
			String isAllSearch = request.getParameter("isAllSearch");
			if(isAllSearch != null) {
			    StringTokenizer tok = new StringTokenizer(isAllSearch, ",");
			    if(tok.nextToken().trim().equalsIgnoreCase("Yes")) {			        
			        if(tok.hasMoreTokens()) {
				        val = tok.nextToken();
			        }
	    %>
			<TD class="fieldLabel">
				<hdiits:checkbox name="fts_All" value="<%=val %>" captionid="FTSEARCH.ALL" bundle="${searchLabels}" onclick="allCbClicked(this);"/>
			</TD>				        
		   		 
		<%  } } 
			else {
		%>

		<TD class="fieldLabel">
			<hdiits:checkbox name="fts_All" value="<%=val %>" style="display:none;"/>
		</TD>	
		
		<%
			}
			String[] aSearchOn = request.getParameterValues("ftSearchOn");
			StringTokenizer tok = null;
			for(String searchOn : aSearchOn) {
				tok = new StringTokenizer(searchOn, ",");	    
				String name = tok.nextToken().trim();		
				String reportCode = tok.nextToken().trim();
				String custUrl = null;
				if(tok.hasMoreTokens()) 
				    custUrl = tok.nextToken();
		%>
		<TD class="fieldLabel">
			<hdiits:checkbox name='<%= "fts_" + name %>' value="<%=reportCode %>" caption="<%=name %>" onclick="indiCbClicked()"/>
		</TD>
		<%	
			if(custUrl != null ) {
		%>
				<hdiits:hidden name='<%= "custUrl_fts_" + name %>' default="<%=custUrl %>"/>
		<%
			}
			}
		%>
	</tr>		
</TABLE>
<hr style="border-width: 1px; border-style: dotted; border-color: #666666;"/>

<TABLE class="tabtable" border="0" style="width: 97%;" align="center">
	<tr>
		<TD class="fieldLabel">	
			<hdiits:radio value="Exact"	name="rdoSearchType" default="Exact" captionid="FTSEARCH.SEARCH.EXACT" bundle="${searchLabels}"/>
			<hdiits:radio value="Like" name="rdoSearchType" captionid="FTSEARCH.SEARCH.LIKE" bundle="${searchLabels}"/>
		</TD>
	</tr>
</TABLE>

<TABLE class="tabtable" border="0" style="width: 95%;" align="center">
	<tr>
		<TD class="fieldLabel">	
			<b> <fmt:message bundle="${searchLabels}" key="FTSEARCH.FIND"/> </b>
		</TD>
	</tr>
</TABLE>

<TABLE class="tabtable" border="0" style="width: 90%;" align="center">
	<tr>
		<TD class="fieldLabel" width="35%">	
			<hdiits:caption captionid="FTSEARCH.SEARCH.ALLWORDS" bundle="${searchLabels}" />
		</TD>
		<TD class="fieldLabel">
			<hdiits:text name="txtSearchCritAllWord" captionid="FTSEARCH.SEARCHBOX" bundle="${searchLabels}" size="50"/>
		</TD>
	</tr>
	<tr>
		<TD class="fieldLabel" width="35%">	
			<hdiits:caption captionid="FTSEARCH.SEARCH.ANYWORD" bundle="${searchLabels}" />
		</TD>
		<TD class="fieldLabel">
			<hdiits:text name="txtSearchCritAnyWord" captionid="FTSEARCH.SEARCHBOX" bundle="${searchLabels}" size="50"/>
		</TD>
	</tr>	
</TABLE>

<TABLE class="tabtable" border="0" style="width: 95%;" align="center">
	<tr>
		<TD class="fieldLabel">	
			<b> <fmt:message bundle="${searchLabels}" key="FTSEARCH.NOTFIND"/> </b>&nbsp;
		</TD>
	</tr>
</TABLE>

<TABLE class="tabtable" border="0" style="width: 90%;" align="center">
	<tr>
		<TD class="fieldLabel" width="35%">	
			<hdiits:caption captionid="FTSEARCH.SEARCH.NOWORD" bundle="${searchLabels}" />
		</TD>
		<TD class="fieldLabel">
			<hdiits:text name="txtSearchCritNoWord" captionid="FTSEARCH.SEARCHBOX" bundle="${searchLabels}" size="50"/>
		</TD>
	</tr>
</TABLE>

<TABLE class="tabtable" border="0" style="width: 80%;" align="center">
	<tr align="center">
		<TD class="fieldLabel">
			<hdiits:button type="button" name="btnSearch" onclick="generateReport();" captionid="FTSEARCH.SEARCH" bundle="${searchLabels}"/>&nbsp;
			<!-- <hdiits:button type="button" name="btnLuckySearch" onclick="notImpl();" captionid="FTSEARCH.LUCKYSEARCH" bundle="${searchLabels}"/> -->	
		</TD>		
	</tr>
</TABLE>

</hdiits:fieldGroup>
</td> </tr>
</TABLE>

<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
<script type="text/javascript">
	document.getElementById("txtSearchCritAllWord").focus();
</script>
</hdiits:form>
<%
}
catch(Exception e) {
    e.printStackTrace();
}
%>
	