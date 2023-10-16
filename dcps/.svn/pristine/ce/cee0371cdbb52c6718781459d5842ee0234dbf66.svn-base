<%
try {
%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" 	src="<c:url value="/script/hrms/ess/quarter/PWD.js"/>"></script>

<script type="text/javascript" src="script/common/commonfunctions.js"></script>

<c:set var="tdBGColor" value="#C9DFFF"></c:set>	
<c:set var="tableBGColor" value="#F0F4FB"></c:set>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="qtrTypeList" value="${resValue.qtrTypeList}"></c:set>
<c:set var="lstQtrDtls" value="${resValue.lstQtrDtls}"></c:set>
<c:set var="empName" value="${resValue.empName}"></c:set>
<c:set var="qtrTypeCode" value="${resValue.qtrTypeCode}"></c:set>
<c:set var="qtrName" value="${resValue.qtrName}"></c:set>
<c:set var="strRefNo" value="${resValue.strRefNo}"></c:set>


<fmt:setBundle basename="resources.ess.quarter.QuarterAllocPwdLables" var="QtrLables" scope="request" />
<script type="text/javascript">
	
	var empArray = new Array();
	</script>
<hdiits:form name="Pwd" validate="true" method="post">
    <div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1" bgColor="#386CB7"><b><fmt:message key="qtr.PWDMaster" bundle="${QtrLables}"></fmt:message></b></a></li>
	 
	</ul>
	</div>

<div class="tabcontentstyle" style="height:100%" id="tabC2">
<div id="tcontent1" class="tabcontent">
  <table width="100%" align="center">
		<tr>
			<td width="25%"><hdiits:caption captionid="qtr.empName" bundle="${QtrLables}"/></td>
			<td width="25%"><hdiits:search name="txtEmployeeName1" id="txtEmployeeName1"  url="hrms.htm?actionFlag=getEmpSearchSelData&multiple=false"  size="20" default="${empName}"/></td>
		 
		 	<td width="25%"><hdiits:caption captionid="qtr.quarterType"  bundle="${QtrLables}" /></td>
           	<td width="25%"><hdiits:select name="selQuarterType" id="selQuarterType" validation="sel.isrequired" sort="false" captionid="qtr.quarterType"  bundle="${QtrLables}" default="${qtrTypeCode}">
   					 <hdiits:option value="0"><fmt:message key="qtr.select" bundle="${QtrLables}" /></hdiits:option>
						 <c:forEach var="qtrTypeList" items="${qtrTypeList}">
							<hdiits:option value="${qtrTypeList.qtrCode}">${qtrTypeList.quaType}</hdiits:option>
						</c:forEach>
           </hdiits:select></td>
		</tr>
		<tr>
			 <td width="25%"><hdiits:caption captionid="qtr.QuarterName"  bundle="${QtrLables}" /></td>
			 <td width="25%"><hdiits:text  name="txtQtrName" id="txtQtrName" captionid="qtr.QuarterName" maxlength="49" bundle="${QtrLables}" default="${qtrName}"></hdiits:text></td>
			 <td width="25%"><hdiits:caption captionid="qtr.RefNo"  bundle="${QtrLables}" /></td> 
			 <td width="25%"><hdiits:text  name="txtRefNo" id="txtRefNo" captionid="qtr.RefNo"  bundle="${QtrLables}" maxlength="19" default="${strRefNo}"></hdiits:text></td>
		</tr>
		
		
		
	</table>
	<table width="100%" align="center">
		<tr align="center"><td>
			<hdiits:button name="btnSearch" type="button" captionid="qtr.search" bundle="${QtrLables}" onclick="searchData();"/>
			<hdiits:button name="btnReset" type="button" captionid="qtr.reset" bundle="${QtrLables}" onclick="resetData();"/>
		</td></tr>
	</table>



<fieldset class="tabstyle">
<c:set var="i" value="1" />
	<legend id="headingMsg"><hdiits:caption captionid="qtr.allocQtrLst" bundle="${QtrLables}"/></legend>

	<display:table list="${lstQtrDtls}" id="row" requestURI="" pagesize="10"  style="width:100%" offset="1" decorator="com.tcs.sgv.ess.quarter.decorator.QuarterPWDMstDecorator">
		<display:setProperty name="paging.banner.placement" value="bottom"/>
		
		<fmt:formatDate var="AllocationDate" pattern="dd/MM/yyyy" value="${row.qtrAllocationDate}" type="date"/>				
		
		<display:column class="tablecelltext" titleKey="qtr.SrNo" headerClass="datatableheader" style="text-align: center" sortable="true" >${i}</display:column>
		
		<display:column class="tablecelltext" titleKey="qtr.empName" headerClass="datatableheader" style="text-align: center;" sortable="true" >${row.empName}</display:column>
					
		<display:column class="tablecelltext" titleKey="qtr.designation" headerClass="datatableheader" style="text-align: center;" sortable="true" >${row.dsgnName}</display:column>
		
		<display:column class="tablecelltext" titleKey="qtr.RefNo" headerClass="datatableheader" style="text-align: center;" sortable="true" >${row.refNo}</display:column>
					
		<display:column class="tablecelltext" titleKey="qtr.quarterType" headerClass="datatableheader" style="text-align: center" sortable="true" >${row.quarterType}</display:column>
		
		<display:column class="tablecelltext" titleKey="qtr.QuarterName" headerClass="datatableheader" style="text-align: center;" sortable="true" >${row.quarterName}</display:column>
		
		<display:column class="tablecelltext" titleKey="qtr.OccupDate" headerClass="datatableheader" style="text-align: center;" sortable="true">${AllocationDate}</display:column>	
		
		<display:column property="editLink" class="tablecelltext" media="html" title="Actions"  style="text-align: center;" headerClass="datatableheader"></display:column>
		
		<c:set var="i" value="${i+1}" />
		<display:footer media="html"></display:footer>		
		
	</display:table>
</fieldset>

  
<table width="100%">
	
	<tr><br><td colspan="4" align="center">
		<hdiits:button type="button" name="btnSubmit"  captionid="qtr.allocateQtr" bundle="${QtrLables}" onclick="AddPWD()"/>
		<hdiits:button type="button" name="btnClose"  captionid="qtr.close" bundle="${QtrLables}" caption="Close" onclick="ClosePage()"/>
	</td></tr>
</table>
</div></div>
</hdiits:form>
<script type="text/javascript">		
	initializetabcontent("maintab");
	/*if('${empName}' != "")
		document.Pwd.name_txtEmployeeName1.value = '${empName}';*/

</script>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>