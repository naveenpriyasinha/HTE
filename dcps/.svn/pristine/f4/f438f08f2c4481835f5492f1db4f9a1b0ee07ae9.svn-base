<%
try {
%>

<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
 
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" 	src="<c:url value="/script/hrms/ess/quarter/QuarterPWDMaster.js"/>"></script>
<c:set var="tdBGColor" value="#C9DFFF"></c:set>	
<c:set var="tableBGColor" value="#F0F4FB"></c:set>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="qtrTypeList" value="${resValue.qtrTypeList}"></c:set>
<c:set var="qtrId" value="${resValue.qtrId}"></c:set>
<c:set var="allocPwdCustVo" value="${resValue.allocPwdCustVo}"></c:set>
<c:set var="allocDate" value="${resValue.allocDate}"></c:set>
<c:set var="hrEssQtrMst" value="${resValue.hrEssQtrMst}"></c:set>
<c:set var="lookupRate" value="${resValue.lookupRate}"></c:set>

<fmt:setBundle basename="resources.ess.quarter.QuarterAllocPwdLables" var="QtrLables" scope="request" />
<script type="text/javascript">
	var empArray = new Array();
 	var temp = '${qtrId}';
	var date='${allocDate}';
	var alertValidation = new Array();
	alertValidation[0]='<fmt:message key="qtr.dateValidation" bundle="${QtrLables}" />';
	
</script>
<hdiits:form name="QtrPwdMaster" validate="true" method="post">
    
 
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
     <c:if test="${qtrId eq 0}">
     	<li class="selected"><a href="#" rel="tcontent1" bgColor="#386CB7"><b><fmt:message key="qtr.allocatePwdQtr" bundle="${QtrLables}"></fmt:message></b></a></li>
	  </c:if>
	  <c:if test="${qtrId ne 0}">
     	<li class="selected"><a href="#" rel="tcontent1" bgColor="#386CB7"><b><fmt:message key="qtr.vacatePwdQtr" bundle="${QtrLables}"></fmt:message></b></a></li>
	  </c:if>
	</div>

<div class="tabcontentstyle" style="height:100%" id="tabC2">
<div id="tcontent1" class="tabcontent">
 
   <hdiits:fieldGroup titleCaptionId="qtr.empDtls" bundle="${QtrLables}"   mandatory="true" >
    <table align="center" id="empSearch">
		<tr>
			<td align="center"><b><hdiits:caption captionid="qtr.searchForEmployee" bundle="${QtrLables}"/></b></td>
			<td><hdiits:search name="txtEmployeeName" id="txtEmployeeName" validation="txt.isrequired" url="hrms.htm?actionFlag=getEmpSearchSelData&multiple=false" captionid="qtr.searchForEmployee" bundle="${QtrLables}" readonly="true" size="50" mandatory="true"  /></td>
		</tr>
	</table>
  
  <table id='empIno' name="empIno" border="1" borderColor="black" align="center" width="100%" cellpadding="1" cellspacing="1" style="border-collapse: collapse;background-color: ${tableBGColor};display:none;" >
	<tr>		
		<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="qtr.empName" bundle="${QtrLables}"/></b></td>
		<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="qtr.Dept" bundle="${QtrLables}"/></b></td>
		<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="qtr.Loctn" bundle="${QtrLables}"/></b></td>
		<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="qtr.designation" bundle="${QtrLables}"/></b></td>
		<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="qtr.Post" bundle="${QtrLables}"/></b></td>
	</tr>
	<tr>		
		<td align="center" id="empName"><label></label></td>
		<td align="center" id="department"><label></label></td>
		<td align="center" id="location"><label></label></td>
		<td align="center" id="designation"><label></label></td>
		<td align="center" id="post"><label></label></td>
	</tr>	
</table>
</hdiits:fieldGroup>
<hdiits:fieldGroup titleCaptionId="qtr.quarterDtls" bundle="${QtrLables}"   mandatory="true" >
	
   	<table id="qtrDtsTbl"  width="100%">
		<tr>
           <td width="25%"><hdiits:caption captionid="qtr.quarterType"  bundle="${QtrLables}" /></td>
           <td width="25%"><hdiits:select name="selQuarterType" id="selQuarterType" validation="sel.isrequired" captionid="qtr.quarterType" sort="false"  bundle="${QtrLables}" mandatory="true">
            <hdiits:option value="0"><fmt:message key="qtr.select" 	bundle="${QtrLables}" /></hdiits:option>
					<c:forEach var="qtrTypeList" items="${qtrTypeList}">
							<hdiits:option value="${qtrTypeList.qtrCode}">${qtrTypeList.quaType}</hdiits:option>
				</c:forEach>
           </hdiits:select>
			  </td>
	</tr>
	<tr>
	  <td width="25%"><hdiits:caption captionid="qtr.QuarterName"  bundle="${QtrLables}" /></td>
	  <td width="25%"><hdiits:text  name="txtQtrName" id="txtQtrName" captionid="qtr.QuarterName"  bundle="${QtrLables}" mandatory="true" validation="txt.isrequired"></hdiits:text></td>

 	  <td width="25%"><hdiits:caption captionid="qtr.RefNo"  bundle="${QtrLables}" /></td>
	  <td width="25%"><hdiits:text  name="txtRefNo" id="txtRefNo" captionid="qtr.RefNo"  bundle="${QtrLables}" mandatory="true" validation="txt.isrequired"></hdiits:text></td>

	</tr>
	
	</table>
	
	<table id="addressTbl"  width="100%">
		<tr>
			<td><hdiits:fmtMessage key="qtr.quarterAddress" bundle="${QtrLables}" var="quarterAddress" ></hdiits:fmtMessage>
			
			<jsp:include page="//WEB-INF/jsp/common/address.jsp">
				<jsp:param name="addrName" value="quarterAddress" />
				<jsp:param name="addressTitle" value="${quarterAddress}"/>
				<jsp:param name="addrLookupName" value="Permanent Address" />
				<jsp:param name="mandatory" value="Y" />
				</jsp:include>
			</td>
		</tr>
	</table>
	
	<table width="100%" align="center" id="table1">
	<tr>
		<td width="25%"><hdiits:caption  captionid="qtr.OccupDate" bundle="${QtrLables}"/></td>
		<td width="25%"><hdiits:dateTime  name="alloStartDt" validation="txt.isrequired" mandatory="true" caption="AlloStartDt" captionid="qtr.OccupDate" bundle="${QtrLables}"  maxvalue="31/12/9999"></hdiits:dateTime></td>
	 	 <td width="25%"><hdiits:caption captionid="qtr.lookupRate" bundle="${QtrLables}" /></td>
		 <td width="25%"><hdiits:select  captionid="qtr.lookupRate" bundle="${QtrLables}" validation="sel.isrequired" id="lookupRate" name="lookupRate" size ="1" sort="false" mandatory="true">
						<hdiits:option value="0" ><fmt:message key="qtr.select" bundle="${QtrLables}" /></hdiits:option>
								<c:forEach var="lookupValues" items="${lookupRate}">
								<hdiits:option  value="${lookupValues.lookupName}" ><c:out value="${lookupValues.lookupDesc}"/></hdiits:option>
						</c:forEach>
						</hdiits:select>
		</td>
			
	</tr>
	
	</table>
	<table id="tblDate" width="100%" style="display: none">
	<tr>
		<td width="25%"><hdiits:caption  captionid="qtr.OccupDate" bundle="${QtrLables}"/></td>
		<td width="25%" align="center" id="allocatedDate"><label></label></td>
		<td width="25%"><hdiits:caption captionid="qtr.lookupRate" bundle="${QtrLables}" /></td>
		<td width="25%" align="center" id="rateTypeLbl"><label></label></td>
			
		</tr>
		<tr>	
		<td width="25%" ><hdiits:caption  captionid="qtr.allocateEndDate" bundle="${QtrLables}"/></td>
		<td width="25%" ><hdiits:dateTime name="allocEndDate"  validation="txt.isrequired" mandatory="true" caption="allocEndDate" captionid="qtr.allocateEndDate" afterDateSelect="verifyDate()" bundle="${QtrLables}"  maxvalue="31/12/9999"></hdiits:dateTime></td>
		<td width="25%">&nbsp;</td>
		<td width="25%">&nbsp;</td>
	</tr>
	</table>
	
	</hdiits:fieldGroup>
<br>
	<table width="100%">
	
	<tr><td colspan="4" align="center">
		<hdiits:button type="button" name="btnSubmit"  captionid="qtr.submit" bundle="${QtrLables}" caption="Submit" onclick="submitPWD()"/>
		<hdiits:button type="button" name="btnBack" captionid="qtr.back" bundle="${QtrLables}" caption="Close" onclick="back()"/>
		<hdiits:button type="button" name="btnClose"  captionid="qtr.close" bundle="${QtrLables}" caption="Close" onclick="ClosePage()"/>
	</td></tr>
	</table>

	<hdiits:hidden name="hdnUserId" id="hdnUserId"/>
	<hdiits:hidden name="quarterId" id="quarterId" default="${qtrId}"/>  
	<script>
	
	if("${qtrId}"!="0")
	{
	  var temp ='${hrEssQtrMst.orgUserMstByCreatedBy.userId}';
	  document.getElementById('empSearch').style.display='none';	
	  document.getElementById('selQuarterType').value='${hrEssQtrMst.hrQuaterTypeMst.qtrCode}';
	  document.getElementById('selQuarterType').disabled="true";
	  document.getElementById('txtQtrName').disabled="true";
	  document.getElementById('txtRefNo').disabled="true";
	  
	  document.getElementById('table1').style.display='none';	
	  document.getElementById('tblDate').style.display="";
	  document.getElementById('txtQtrName').value ='${hrEssQtrMst.quarterName}';
	  document.getElementById('txtRefNo').value ='${hrEssQtrMst.refNo}';
	  document.getElementById('allocatedDate').innerHTML ='${allocDate}';
      document.getElementById('rateTypeLbl').innerHTML ='${hrEssQtrMst.rateTypeLookup.lookupDesc}';
	  //document.getElementById('name_txtEmployeeName').diabled="true";
	  makeReadOnly("quarterAddress");
	
	  document.getElementById("empIno").style.display= "";
	  //document.getElementById("name_txtEmployeeName").value = '${allocPwdCustVo.empName}';
	  document.getElementById("empName").innerHTML = '${allocPwdCustVo.empName}';
	  document.getElementById("department").innerHTML = '${allocPwdCustVo.departmentName}';
	  document.getElementById("location").innerHTML = '${allocPwdCustVo.locationName}';
	  document.getElementById("designation").innerHTML = '${allocPwdCustVo.dsgnName}';
	  document.getElementById("post").innerHTML = '${allocPwdCustVo.postName}';
	}
	</script>
		<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
</div>
</div>

</hdiits:form>
<script type="text/javascript">		
	initializetabcontent("maintab");
</script>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>