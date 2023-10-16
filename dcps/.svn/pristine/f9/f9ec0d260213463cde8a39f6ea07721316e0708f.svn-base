<%
try {
%>
<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<fmt:setBundle basename="resources.ess.UserPost" var="userPostLables" scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="arUserPostMappingData" value="${resultValue.arUserPostMappingData}"></c:set>

<c:set var="tdBGColor" value="#76A9E2"></c:set>
<c:set var="tableBGColor" value="#F0F4FB"></c:set>

<script type="text/javascript">
	function addNewEntry()
	{
		document.forms[0].action='hdiits.htm?actionFlag=getUserPostMapping&userPostRltId=0';
		document.forms[0].submit();
	}
	function editRecord(userPostRltId)
	{
		document.forms[0].action="hdiits.htm?actionFlag=getUserPostMapping&userPostRltId="+userPostRltId;
		document.forms[0].submit();		
	}
	
	function deleteData()
	{	
		var isChecked = false;
	
		for (var i = 0; i < document.forms[0].deletedata.length; i++) 
		{
	   			if (document.forms[0].deletedata[i].checked) 
	   			{
	     			isChecked = true;
	  			}
		}
		if(isChecked )
		{
			var answer=confirm("Are you sure want to delete the selected data?");
			if(answer)
			{
				document.forms[0].action='hdiits.htm?actionFlag=deleteUserPostMapping';
				document.forms[0].submit();
			}
		}
		else
		{
			alert("Please select the checkbox");
		}
	}
	
	function closeWindow()
	{
		document.frmUserPostMpg.action = "hrms.htm?actionFlag=getHomePage";
	   	document.frmUserPostMpg.submit();
	}
</script>

<hdiits:form name="frmUserPostMpg" validate="true" method="post">
	<div id="tabmenu">
			<ul id="maintab" class="shadetabs">
				<li class="selected">
					<a href="#" rel="tcontent1"><b>User Post Mapping</b></a>
				</li>
			</ul>
	</div>
	<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">
		
		<c:set var="i" value="1" />		
			<display:table list="${arUserPostMappingData}" id="row" requestURI="" pagesize="10"  style="width:100%" offset="1" export="false" decorator="com.tcs.sgv.ess.decorator.UserPostMappinglDecorator">
				<display:setProperty name="paging.banner.placement" value="bottom"/>
							
				<display:column class="tablecelltext" titleKey="srNumber" headerClass="datatableheader" style="text-align: center" sortable="true" >${i}</display:column>
				
				<display:column class="tablecelltext" titleKey="USER_NAME"  headerClass="datatableheader" style="text-align: center" sortable="true" >${row[0].orgUserMst.userName}</display:column>
				
				<display:column class="tablecelltext" titleKey="POST" headerClass="datatableheader" style="text-align: center;" sortable="true" >${row[1].postName}</display:column>
				
				<display:column property="link2" class="tablecelltext" media="html" titleKey="Actions" style="text-align: center;" headerClass="datatableheader"></display:column>
				
				<c:set var="i" value="${i+1}" />
				<display:footer media="html"></display:footer>		
				
			</display:table>
		
		<br><br>
		<table align="center">
		<tr>
			<td>
				<hdiits:button name="addNewEntry_button" captionid="AddRec" bundle="${userPostLables}"  onclick="addNewEntry()" type="button"/>
			</td>
			<td>
				<hdiits:button captionid="delete" bundle="${userPostLables}" onclick="deleteData()" name="delete" type="button"/>
			</td>
			<td>
				<hdiits:button name="btnClose" type="button" captionid="close" onclick="closeWindow();" bundle="${userPostLables}" />
			</td>
		</tr>
		</table>
		</div>	
	</div>
</hdiits:form>	
<script type="text/javascript">
			initializetabcontent("maintab")
		</script>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>