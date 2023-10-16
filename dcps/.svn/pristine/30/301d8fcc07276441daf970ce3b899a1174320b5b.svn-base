 <%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!-- For Taglib -->
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%> 
<fmt:setBundle basename="resources.expacct.expacct" var="expacctLabels" scope="request"/>

<script type="text/javascript" src="script/exprcpt/Common.js"> </script> 
<script> keyPreview =1 ;
</script>
<script type="text/javascript">
function search(cmbSearch)
{
		if(document.forms[0].txtSearch.value=="")
		{
			alert("Please enter search value");
			document.getElementById("id_txtSearch").focus();
		}
		else if(document.forms[0].cmbSearch.value==0)
		{
			alert("Select criteria for search");
			document.getElementById("id_cmbSearch").focus();
		}
		else
		{
			var search=(cmbSearch.options[cmbSearch.selectedIndex].text);
			document.forms[0].action ='ifms.htm?actionFlag=searchVoucherForPensionList&searchby='+search;
			document.getElementById('btnSearch').disabled = true;
			document.forms[0].submit();
		}
}
</script>  
<%
HttpSession hs = request.getSession();
%>
<hdiits:form name="frmSearch" validate="true" method="post">
	<% if(request.getParameter("viewPage")==null) 
	{
		%>
		<input type="hidden" name="viewPage" value='<%=hs.getAttribute("PAGE")%>' />
	<% 
	}
	else 
	{ 
		%>
		<input type="hidden" name="viewPage" value='<%=request.getParameter("viewPage")%>'/>
	<% } %>
	<table align="center" width="90%">
			<tr>
				<td width="65%">
				</td>
				<td width="8%" align="right" class=Label2>
					Search :
				</td>
				<td width="*">
						<select name="cmbSearch" id="id_cmbSearch" tabindex="1">
							<option value="0">-----Select-----</option>
							<option value="1">
								Token No
							</option>
							<option value="2">
								Major Head
							</option>	
						</select>
				</td>
				<td width="3%">
				</td>
				<td width="18%">
					<hdiits:text name="txtSearch" id="id_txtSearch" size="15" default="${param.txtSearch}" tabindex="2"/>
					<script>
						 var cmbSearch = document.getElementById("id_cmbSearch");
						 var valSearch = '${param.searchby}'
						 if(valSearch !=null && valSearch != '')
						 {
							 for(var i=0;i<cmbSearch.length;i++)
							 {
							 	if(valSearch == cmbSearch.options[i].text)
							 	{
							 		cmbSearch.value = cmbSearch.options[i].value;
							 		break;
							 	}
							 } 
						}    	
						
					</script>
				</td>
				<td width="20%">
					<input type="button" class="searchButton" name="btnSearch" onclick="search(cmbSearch)" tabindex="3"/>
					<%-- <img src="common/images/search.gif" align="right" height="16" width="16" onclick="search(cmbSearch)" />--%>
				</td>
				</tr>
	</table>
	</hdiits:form>
	<style>
	.searchButton
			{
			border:none;
			border-color:blue;
			background-image:url(images/search_button.gif);
			height: 26px;
			width: 66px;
	</style>
	<script language="Javascript">
		document.getElementById("id_txtSearch").focus();
	</script>