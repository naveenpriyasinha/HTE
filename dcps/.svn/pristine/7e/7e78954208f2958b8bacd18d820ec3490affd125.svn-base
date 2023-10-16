<%
try {
%>
 
<%@ include file="../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>


<fmt:setBundle basename="resources.common.CmnLocationMst" var="CommonLables" scope="request" />
<fmt:setBundle basename="resources.common.MstScrLables" var="mstScrLables" scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="cmnLocationMstList" value="${resValue.cmnLocationMstList}"></c:set>
<c:set var="multiLang" value="${resValue.multiLang}"></c:set>
<c:set var="searchKey" value="${resValue.searchKey}"></c:set>
<c:set var="searchBy" value="${resValue.searchBy}"></c:set>
<html>
<head>
<script type="text/javascript" src="script/common/cmnLocationAdmin.js"></script>
<script type="text/javascript" src="script/common/cmnMstScr.js"></script>
<script>
	var selectCheckBoxalert = "<fmt:message bundle='${CommonLables}' key='LOC.CHECKBOX'/>";
	var confirmLocationDelete = "<fmt:message key='MstScr.confirmDelete' bundle='${mstScrLables}' />";
</script>
</head>
<body onkeypress="return checkSpecialCharacter(event)">
<c:set value="1" var="i"></c:set>

<hdiits:form name="frmAdminCrtLocation" validate="true" method="post" encType="multipart/form-data">
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>
			<fmt:message key="LOC.VIEWLOCATION" bundle="${CommonLables}"></fmt:message>
		</b></a></li>		
	</ul>
	</div>
	<div id="createLocation" name="createLocation" class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">
		
		<table width="50%" align="center">
		<tr>
			<td align="right">
				<hdiits:caption captionid="MstScr.searchBy" bundle="${mstScrLables}"/>
			</td>
			<td align="center">
				<hdiits:select id="searchBy" name="searchBy" captionid="MstScr.searchBy" bundle="${mstScrLables}" default="${searchBy}" sort="false">
					<hdiits:option value="loc_name"><fmt:message key="LOC.locationName" bundle="${CommonLables}"></fmt:message></hdiits:option>
					<hdiits:option value="loc_shortnm"><fmt:message key="LOC.locationShortName" bundle="${CommonLables}"></fmt:message></hdiits:option>
					<hdiits:option value="loc_parentLoc"><fmt:message key="LOC.parentLocation" bundle="${CommonLables}"></fmt:message></hdiits:option>
					<hdiits:option value="loc_deptnm"><fmt:message key="LOC.Department" bundle="${CommonLables}"></fmt:message></hdiits:option>
				</hdiits:select>
			</td>

			<td align="center">
				<hdiits:text id="txtLocationName" name="txtLocationName" captionid="MstScr.searchBy" bundle="${mstScrLables}" default="${searchKey}"/>
			</td>
		</tr>
	</table>
		
	<table align="center">
		<tr>
			<td align="center">
				<hdiits:button name="btnSearch" id="btnSearch" type="button" captionid="LOC.Search" bundle="${CommonLables}" onclick="searchLocation()"/>
			</td>
		</tr>
	</table>
	
	<br>
	
	<c:set var="checkAll">
    	<hdiits:checkbox name="allbox" captionid="MstScr.selectClear" value="" bundle="${mstScrLables}" onclick="selectAllClearAll(this)" />
	</c:set>
		
	<display:table pagesize="15" name="${cmnLocationMstList}" id="row"  export="false" offset="1" requestURI="hrms.htm?actionFlag=showAdminLocationMst" style="width:100%" decorator="com.tcs.sgv.common.decorator.AdminCmnLocationMstDecorator">
			
		<display:setProperty name="paging.banner.placement" value="bottom"/>
		
		<display:column style="text-align: center;width:15%" class="tablecelltext" title="${checkAll}" headerClass="datatableheader" property="checkBox">	
		</display:column>
		
		<display:column style="text-align: center;" class="tablecelltext" property="link2" titleKey= "LOC.locationName" headerClass="datatableheader"/>
		
		<display:column style="text-align: center;" class="tablecelltext" titleKey= "LOC.locationShortName" headerClass="datatableheader" value="${row.column[2]}"/>
	 	
	 	<display:column style="text-align: center;" class="tablecelltext"  titleKey= "LOC.parentLocation" headerClass="datatableheader" value="${row.column[3]}"/>	 	
	 	<display:column style="text-align: center;" class="tablecelltext"  titleKey= "LOC.Department" headerClass="datatableheader" value="${row.column[4]}"/>
	    <c:choose>
				<c:when test="${row.column[5] == '1'}">
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
				<hdiits:button name="addNewEntry_button" captionid="LOC.AddRec" bundle="${CommonLables}" onclick="addNewEntry()" type="button"/>
				<hdiits:button captionid="LOC.Delete" bundle="${CommonLables}" onclick="deleteData()" name="cmdDel2" type="button"/>
				<hdiits:button captionid="MstScr.close" bundle="${mstScrLables}" onclick="closeWindow()" name="btnClose" type="button"/>
			</td>
		</tr>
	</table>

	</div>
	</div>
	
	<hdiits:validate locale="${locale}" controlNames="" />
	<script type="text/javascript">	
		initializetabcontent("maintab");
		document.getElementById("tcontent1").focus();
	</script>
	
	<hdiits:hidden id="multiLang" name="multiLang" default="${multiLang}"/>
	<hdiits:hidden id="multiLangHdn" name="multiLangHdn" default="${multiLang}"/>	
</hdiits:form>
</body>
</html>

<%
}catch(Exception e) {e.printStackTrace();}
%>