<%
try{
%>
<%@ include file="../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="searchKey" value="${resValue.searchKey}"></c:set>
<c:set var="searchBy" value="${resValue.searchBy}"></c:set>
<c:set var="paginatelist" value="${resValue.paginatelist}"></c:set>
<c:set var="dataList" value="${resValue.dataList}"></c:set>

<fmt:setBundle basename="resources.common.MstScrLables" var="mstScrLables" scope="request" />

<html>
<head>	
<script type="text/javascript" src="script/common/cmnMstScr.js"></script>
<script type="text/javascript">

function setDeleteFlag(){


	var isChecked = false;
	if(document.showVillage.deletedata != null)//added by uzma
	{
		for (var i = 0; i < document.showVillage.deletedata.length; i++) 
		{
	   			if (document.showVillage.deletedata[i].checked) 
	   			{
	     			isChecked = true;
	  			}
		}
		if(document.showVillage.deletedata.checked)
		{	
			isChecked = true;
		}
		if(isChecked )
		{
			var answer = confirm("<fmt:message key="MstScr.confirmDelete" bundle='${mstScrLables}' />");
			if(answer)
			{
				showProgressbar();
				document.showVillage.action='hdiits.htm?actionFlag=setVillageDeleteFlag';
				document.showVillage.submit();
	
			}
		}
		else
		{
			alert("<fmt:message key="MstScr.plsChkBox" bundle='${mstScrLables}' />");
		}
	}
	
}

function getCmnVillageMstList(){
	showProgressbar();
	document.showVillage.action='hdiits.htm?actionFlag=getCmnVillageMstList';
	document.showVillage.submit();
}

function editVillageRecord(villageId, activateFlag)
{
	showProgressbar();
	document.getElementById('flag').value = 'edit';
	document.getElementById('villageId').value = villageId;
	document.showVillage.action='hdiits.htm?actionFlag=getCreateVillage';
	document.showVillage.submit();
}

function createNewVillage(){

	showProgressbar();
	document.showVillage.action='hdiits.htm?actionFlag=getCreateVillage';
	document.showVillage.submit();
}
</script>

</head>
<body onkeypress="return checkSpecialCharacter(event)">
<c:set value="1" var="i"></c:set>
<hdiits:form name="showVillage" action="" validate="true" method="post" encType="multipart/form-data">
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected">
			<a href="#" rel="tcontent1">
				<b><fmt:message bundle="${mstScrLables}" key="MstScr.villageMaster"/></b>
			</a>
		</li>		
	</ul>
	</div>
		<div class="tabcontentstyle" style="height: 100%">
	
		
		<div id="tcontent1" class="tabcontent" tabno="0" >
	<table align="center" id="villageSearchTable" width="50%">
	<tr>
	<td align="right">
		<hdiits:caption captionid="MstScr.searchBy" bundle="${mstScrLables}"/>
	</td>
	<td align="center">
		<hdiits:select id="searchBy" name="searchBy" captionid="MstScr.searchBy" bundle="${mstScrLables}" default="${searchBy}" sort="false">
			<hdiits:option value="vill_name"><fmt:message key="MstScr.villageName" bundle="${mstScrLables}"></fmt:message></hdiits:option>
			<hdiits:option value="vill_talukanm"><fmt:message key="MstScr.talukaName" bundle="${mstScrLables}"></fmt:message></hdiits:option>
			<hdiits:option value="vill_districtnm"><fmt:message key="MstScr.districtName" bundle="${mstScrLables}"></fmt:message></hdiits:option>
		</hdiits:select>
	</td>
	<td align="center"><hdiits:text name="villageName" id="villageName" default="${searchKey}" /></td>
	</tr>
	</table>
	<table  align="center">
	<tr>
	<td align="center"><hdiits:button type="button" name="searchButton" captionid="MstScr.search" bundle="${mstScrLables}" onclick="getCmnVillageMstList()"/></td>
	</tr>
	</table>
	<br>
	<c:set var="checkAll">
    	<hdiits:checkbox name="allbox" captionid="MstScr.selectClear" value="" bundle="${mstScrLables}" onclick="selectAllClearAll(this)" />
	</c:set>
	<display:table pagesize="15" name="${paginatelist}" id="row"  export="false" offset="1" style="width:100%" requestURI="hrms.htm?actionFlag=getCmnVillageMstList&count=${paginatelist.fullListSize}" decorator="com.tcs.sgv.common.decorator.VillageMstDecorator">
			
				<display:setProperty name="paging.banner.placement" value="bottom"/>
		
				<display:column style="text-align: center;width:15%" class="tablecelltext" title="${checkAll}" headerClass="datatableheader" property="checkBox">
				</display:column>
		
			 	   <display:column style="text-align: center;" class="tablecelltext" property="villageEditLink" titleKey="MstScr.villageName" headerClass="datatableheader">${row.column[0]}</display:column>
				   <display:column style="text-align: center;" class="tablecelltext"  titleKey= "MstScr.talukaName" headerClass="datatableheader">${row.column[1]} </display:column>
				   <display:column style="text-align: center;" class="tablecelltext"  titleKey= "MstScr.districtName" headerClass="datatableheader">${row.column[2]}</display:column>
				<c:choose>
					<c:when test="${row.column[4] == '1'}">
						<display:column style="text-align: center;" class="tablecelltext"  titleKey= "MstScr.Status" headerClass="datatableheader">
							<fmt:message key="MstScr.Active" bundle="${mstScrLables}"></fmt:message>
						</display:column>
					</c:when>
					<c:otherwise>
						<display:column style="text-align: center;" class="tablecelltext" titleKey= "MstScr.Status" headerClass="datatableheader">
							<fmt:message key="MstScr.Deactive" bundle="${mstScrLables}"></fmt:message>
						</display:column>
					</c:otherwise>
				</c:choose> 
				<display:setProperty name="export.pdf" value="true" />
				<c:set var="i" value="${i+1}"></c:set>
	</display:table>
	
	<table align="center">
	<tr>
		<td><hdiits:button name="AddVillage" type="button" captionid="MstScr.addNew" bundle="${mstScrLables}"  onclick="createNewVillage()"/></td>
		<td><hdiits:button name="DeleteVillage" type="button" captionid="MstScr.delete" bundle="${mstScrLables}" onclick="setDeleteFlag()" /></td>
		<td><hdiits:button captionid="MstScr.close" bundle="${mstScrLables}" onclick="closeWindow()" name="btnClose" type="button"/></td>
	</tr>
	</table>
	
	
	</div>
	</div>
	
	
	
	<script type="text/javascript">		
		initializetabcontent("maintab");
		document.getElementById("tcontent1").focus();
	</script>
	
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
	<hdiits:hidden id="multiLang" name="multiLang"/>
	<hdiits:hidden name="flag" id="flag" default="create"/>
	<hdiits:hidden name="villageId" id="villageId" default="0"/>


</hdiits:form>	
</body>
</html>
	
	
<%	
}catch(Exception e){
	
	out.println(e);
	e.printStackTrace();
}
%>