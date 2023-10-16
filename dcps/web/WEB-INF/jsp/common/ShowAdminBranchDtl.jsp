<%
	try {
%> 
<%@ include file="../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<fmt:setBundle basename="resources.common.MstScrLables" var="mstScrLables" scope="request" />
<fmt:setBundle basename="resources.common.BranchMasterLables" var="cmnBranchLables" scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="branchName" value="${resValue.branchName}"></c:set>
<c:set var="cmnBranchDtlList" value="${resValue.cmnBranchDtlList}"></c:set>
<c:set var="searchTypeValue" value="${resValue.searchType}"></c:set>
<html>
<head>
<script type="text/javascript" src="script/common/cmnMstScr.js"></script>
<script type="text/javascript" src="script/common/cmnBranchAdmin.js"></script>
<script type="text/javascript">
var showBranchAlertArray=new Array();
showBranchAlertArray[0]="<fmt:message key='MstScr.confirmDelete' bundle='${mstScrLables}' />";
showBranchAlertArray[1]="<fmt:message bundle='${mstScrLables}' key='MstScr.plsChkBox'/>";
</script>
</head>
<body onkeypress="return checkSpecialCharacter(event)">
<c:set value="1" var="i"></c:set>
<hdiits:form name="frmAdminCrtBranch" action="" validate="true" method="post" encType="multipart/form-data">
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>
			<fmt:message key="BRANCH.BranchMaster" bundle="${cmnBranchLables}"></fmt:message>
		</b></a></li>		
	</ul>
	</div>
	<div id="createBranch" name="createBranch" class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">	
	<table width="50%" align="center">
		<tr>
			<td align="right"><hdiits:caption  captionid="MstScr.searchBy" bundle="${mstScrLables}"/>
			</td>
			<td align="center">
				<hdiits:select id="searchType" name="searchType" captionid="MstScr.searchBy" bundle="${mstScrLables}" sort="false" default="${searchTypeValue}">
					<hdiits:option value="branchName"><fmt:message key="BRANCH.BranchName" bundle="${cmnBranchLables}"></fmt:message></hdiits:option>
					<hdiits:option value="branchDesc"><fmt:message key="BRANCH.BranchDesc" bundle="${cmnBranchLables}"></fmt:message></hdiits:option>
				</hdiits:select>
			</td>
			<td align="center">
				<hdiits:text id="txtBranchNm" name="txtBranchNm" captionid="BRANCH.BranchName" bundle="${cmnBranchLables}" default="${branchName}"/>
			</td>
		</tr>
	</table>
	<table align="center">
		<tr>
			<td align="center">
				<hdiits:button name="btnSearch" id="btnSearch" type="button" captionid="MstScr.search" bundle="${mstScrLables}" onclick="searchBranch();"/>
			</td>
		</tr>
	</table>	
	<br>
			<c:set var="checkAll">
    			<hdiits:checkbox name="allbox" captionid="MstScr.selectClear" value="" bundle="${mstScrLables}" onclick="selectAllClearAll(this)" />
			</c:set>
			
			<display:table pagesize="15" name="${cmnBranchDtlList}" id="row"  export="false" offset="1" requestURI="hrms.htm?actionFlag=showAdminBranchDtl" style="width:100%" decorator="com.tcs.sgv.common.decorator.AdminCmnBranchMstDecorator">
			
				<display:setProperty name="paging.banner.placement" value="bottom"/>
				
				<display:column style="text-align: center;width:15%" class="tablecelltext" title="${checkAll}" headerClass="datatableheader" property="checkBox">	
				</display:column>
				
			 	<display:column style="text-align: center;" property="editLink" class="tablecelltext"  titleKey= "BRANCH.BranchName"  headerClass="datatableheader" value="${row.branchName}"/>
			 	<display:column style="text-align: center;" class="tablecelltext" titleKey= "BRANCH.BranchDesc" headerClass="datatableheader" value="${row.branchDesc}"/>
				<c:choose>
					<c:when test="${row.activateFlag == '1'}">
						<display:column style="text-align: center;" class="tablecelltext"  titleKey= "MstScr.Status" headerClass="datatableheader">
							<fmt:message key="MstScr.Active" bundle="${mstScrLables}"></fmt:message>
						</display:column>
					</c:when>
					<c:otherwise>
						<display:column style="text-align: center;" class="tablecelltext"  titleKey= "MstScr.Status" headerClass="datatableheader">
							<fmt:message key="MstScr.Deactive" bundle="${mstScrLables}"></fmt:message>
						</display:column>
					</c:otherwise>
				</c:choose>	
				<display:setProperty name="export.pdf" value="true" />
				<c:set var="i" value="${i+1}"></c:set>
			</display:table>
		
		<table width="100%">
			<tr align="center" width="100%">
				<td align="center" width="100%">
					<hdiits:button name="btnAddNew" captionid="MstScr.addNew" bundle="${mstScrLables}" onclick="addNewEntry()" type="button" />
					<hdiits:button captionid="MstScr.delete" bundle="${mstScrLables}" onclick="deleteData()" name="btnDelete" type="button"/>
					<hdiits:button captionid="MstScr.close" bundle="${mstScrLables}" onclick="closeWindow()" name="btnClose" type="button"/>
				</td>
			</tr>
		</table>
	</div>
	</div>
<script type="text/javascript">	
	initializetabcontent("maintab");
	document.getElementById("tcontent1").focus();
</script>
</hdiits:form>
</body>
</html>
<%
	}
	catch(Exception e) {
		e.printStackTrace();
	}
%>