<%
try { 
%>
<%@ include file="../../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 

<fmt:setBundle basename="resources.hr.award.award_AlertMessages" var="awdalert" scope="request"/>
<fmt:setBundle basename="resources.hr.award.award" var="awdlbl" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="listAwardMst" value="${resultValue.listAwardMst}"></c:set>


<script type="text/javascript">
	function addNewEntry()
	{
		document.forms[0].action='hdiits.htm?actionFlag=getAwardDtlsForNewCategory&awardId=0';
		document.forms[0].submit();
	}
	function editRecord(awardId)
	{
		//alert("inside----");
		//alert("awardId----"+awardId);
		document.forms[0].action="hdiits.htm?actionFlag=getRecordOnEdit&awardId="+awardId;
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
			var answer=confirm('<fmt:message key="ess.deleteAlert" bundle="${awdalert}"/>');
			if(answer)
			{
				document.forms[0].action='hdiits.htm?actionFlag=getRecordsToDelete';
				document.forms[0].submit();
			}
		}
		else
		{
			alert('<fmt:message key="ess.selCheckBox" bundle="${awdalert}"/>');
		}
	}
	
	function closeWindow()
	{
		document.frmAwardMst.action = "hrms.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
	   	document.frmAwardMst.submit();
	}
</script>

<hdiits:form name="frmAwardMst" validate="true" method="post">
	<div id="tabmenu">
			<ul id="maintab" class="shadetabs">
				<li class="selected">
					<a href="#" rel="tcontent1"><b><fmt:message key="awd.awdMaster" bundle="${awdlbl}"></fmt:message> </b></a>
				</li>
			</ul>
	</div>
	<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">
		
		<c:set var="i" value="1" />		
			<display:table list="${listAwardMst}" id="row" requestURI="" pagesize="10"  style="width:100%" offset="1" export="false" decorator="com.tcs.sgv.hr.award.decorator.AwardDecorator">
				<display:setProperty name="paging.banner.placement" value="bottom"/>
							
				<display:column class="tablecelltext" titleKey="awd.srNo" headerClass="datatableheader" style="text-align: center" sortable="true" >${i}</display:column>
				
				<display:column class="tablecelltext" titleKey="ess.awdCat"  headerClass="datatableheader" style="text-align: center" sortable="true" >${row.awardCategory}</display:column>
				
				<display:column class="tablecelltext" titleKey="ess.awdType" headerClass="datatableheader" style="text-align: center;" sortable="true" >${row.awardType}</display:column>
				
				<display:column class="tablecelltext" titleKey="ess.awdName" headerClass="datatableheader" style="text-align: center;" sortable="true" >${row.awardName}</display:column>
				
				<display:column class="tablecelltext" titleKey="ess.awdedFor" headerClass="datatableheader" style="text-align: center;" sortable="true" >${row.awardedFor}</display:column>
				
				<display:column property="link2" class="tablecelltext" media="html" titleKey="awd.actions" style="text-align: center;" headerClass="datatableheader"></display:column>
				
				<c:set var="i" value="${i+1}" />
				<display:footer media="html"></display:footer>		
				
			</display:table>
		
		<br><br>
		<table align="center">
		<tr>
			<td>
				<hdiits:button name="addNewEntry_button" captionid="ess.Other" bundle="${awdlbl}" onclick="addNewEntry()" type="button"/>
			</td>
			<td>
				<hdiits:button captionid="awd.delete" bundle="${awdlbl}" onclick="deleteData()" name="delete" type="button"/>
			</td>
			<td>
				<hdiits:button name="btnClose" type="button" captionid="ess.Close" bundle="${awdlbl}" onclick="closeWindow();" />
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