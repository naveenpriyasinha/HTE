<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<fmt:setBundle basename="resources.hr.payincrement.PayInc" var="EPAYINLables" scope="request" />

<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/hr/payincrement/EmpPayIncrement.js"/>"></script>

<script type="text/javascript">
	var empNextPayIncrAlert = new Array();
	empNextPayIncrAlert[0]='<fmt:message  bundle="${EPAYINLables}" key="NOTN_BASICP_ALERT"/>';
	empNextPayIncrAlert[1]='<fmt:message  bundle="${EPAYINLables}" key="NOTN_NDATE_ALERT"/>';
	empNextPayIncrAlert[2]='<fmt:message  bundle="${EPAYINLables}" key="VALID_COUNT_RECORD"/>';
	empNextPayIncrAlert[3]='<fmt:message  bundle="${EPAYINLables}" key="VALID_BASICPAY_ALERT"/>';
</script>

<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="resultObj" value="${result}" />
<c:set var="resValue" value="${resultObj.resultValue}" />
<c:set var="hrPayfixMstForCurrent" value="${resValue.hrPayfixMstForCurrent}" />
<c:set var="hrPayfixMstPayScale" value="${resValue.hrPayfixMstPayScale}" />
<c:set var="PayFixXMLFileLst" value="${resValue.PayFixXMLFileLst}" />
<c:set var="EmpAllPayDtlsLst" value="${resValue.EmpAllPayDtlsLst}" />
<c:set var="SelectedUserId" value="${resValue.SelectedUserId}" />
<c:set var="empPayIncrVOCus" value="${resValue.empPayIncrVOCus}" />

<fmt:formatDate var="lastIncrDate" pattern="dd/MM/yyyy" value="${resValue.lastIncrDate}" type="date"/>
<fmt:formatDate var="DueDate" pattern="dd/MM/yyyy" value="${empPayIncrVOCus.nextIncrDate}" type="date"/>	
<fmt:formatDate var="EffectiveDate" pattern="dd/MM/yyyy" value="${empPayIncrVOCus.effectiveDate}" type="date"/>	


<hdiits:form name="frmEmpNextPayIncr" validate="true" method="POST">

	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected">
				<a href="#" rel="tcontent1"><b><fmt:message key="EMP_PAY_INCR" bundle="${EPAYINLables}"/></b></a>
			</li>
		</ul>
	</div>
	
<div class="tabcontentstyle">
    <div id="tcontent1" class="tabcontent" tabno="0">

	<!--<table bgcolor="#386CB7" align="center"  width="100%">
		<tr align="center">
			<td>
				<font class="Label3" align="center" ><u><font class="Label3" align="center" color="white"><b><fmt:message key="CURRENT_PAY_INCR" bundle="${EPAYINLables}"/></b></u><span class="UserText" lang="en-us"></span></font>
			</td>
		</tr> 
	</table>-->
	
	<hdiits:fieldGroup id="currentPayIncFieldGroup" titleCaptionId="CURRENT_PAY_INCR" bundle="${EPAYINLables}">
	
	<table width="100%">
		<tr>
			<td width="25%">
				<b><hdiits:caption captionid="EMP_NAME" bundle="${EPAYINLables}"/></b>
			</td>
			<td align="left" width="25%">
				<c:out value="${empPayIncrVOCus.empName}"/>
			</td>
			<td align="left" width="25%">
				<b><hdiits:caption captionid="EMP_DSGN" bundle="${EPAYINLables}"/></b>
			</td>
			<td align="left" width="25%">
				<c:out value="${empPayIncrVOCus.ASE}"/>
			</td>
		</tr>
		<tr>
			<td width="25%">
				<b><hdiits:caption captionid="PRESENT_PAY_SCALE" bundle="${EPAYINLables}"/></b>
			</td>
			<td align="left" width="25%">
				<c:out value="${hrPayfixMstPayScale}"/>
			</td>
			<td align="left" width="25%">
				<b><hdiits:caption captionid="PRESENT_PAY" bundle="${EPAYINLables}"/></b>
			</td>
			<td align="left" width="25%">
				<c:out value="${hrPayfixMstForCurrent.revPay}"/>
			</td>
		</tr>
		<tr>
			<td width="25%">
				<b><hdiits:caption captionid="LAST_INCR_DATE" bundle="${EPAYINLables}"/></b>
			</td>
			<td align="left" width="25%">
       			${lastIncrDate}
			</td>
			<td align="left" width="25%">
				<b><hdiits:caption captionid="DUE_DATE_INCR" bundle="${EPAYINLables}"/></b>
			</td>
			<td align="left" width="25%">
				<fmt:formatDate pattern="dd/MM/yyyy" value="${hrPayfixMstForCurrent.nxtIncrDate}" var="doincr"/>
				${doincr}
			</td>
		</tr>
	</table>
</hdiits:fieldGroup>
	<br>
	<!--<table bgcolor="#386CB7" align="center"  width="100%">
		<tr align="center">
			<td>
				<font class="Label3" align="center" ><u><font class="Label3" align="center" color="white"><b><fmt:message key="PAYINCR_DTLS" bundle="${EPAYINLables}"/></b></u><span class="UserText" lang="en-us"></span></font>
			</td>
		</tr> 
	</table>-->
	<hdiits:fieldGroup id="payIncrDtlsFieldGroupId" titleCaptionId="PAYINCR_DTLS" bundle="${EPAYINLables}">
	
	<table width="100%">
		<tr>
			<td align="left" width="25%">
				<b><hdiits:caption captionid="TYPE_OF_INC" bundle="${EPAYINLables}"></hdiits:caption></b>
			</td>
			<td align="left" width="25%">
				<hdiits:radio name="rdoTypeOfInc" id="rdoTypeOfInc" value="Y" onclick="setDeferedType()" captionid="YES"  bundle="${EPAYINLables}"   />
				<hdiits:radio name="rdoTypeOfInc" id="rdoTypeOfInc" value="N"  onclick="setDeferedType()" captionid="NO" bundle="${EPAYINLables}"  />
			</td>
			<td width="25%"></td>
			<td width="25%"></td>
		</tr>
	</table>
	
	<table width="100%">
		<tr>
			<td align="left" width="25%">
				<b><hdiits:caption	captionid="DUE_NEXT_INCR_DATE" bundle="${EPAYINLables}"></hdiits:caption></b>
			</td>
			<td align="left" width="25%">
				<hdiits:dateTime name="NextIncrDate"  captionid="DUE_NEXT_INCR_DATE" bundle="${EPAYINLables}" mandatory="true" validation="txt.isdt,txt.isrequired" maxvalue="31/12/2099"/>
			</td>
			<td align="left" width="50%">
				<table id="basicPayAndNextIncr" style="">
					<tr>
						<td align="left" width="25%">
							<b><hdiits:caption captionid="NEW_BASIC_PAY" bundle="${EPAYINLables}"/></b>
						</td>
						<td align="left" width="25%">
							<hdiits:text name="newBasicPay" id="newBasicPay" captionid="NEW_BASIC_PAY" bundle="${EPAYINLables}" validation="txt.isrequired" mandatory="true" onkeypress="return checkNumberOnly(false)" style="text-align: right"/>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	
	<table width="100%">
		<tr>
			<td align="left" width="25%">
				<b><hdiits:caption captionid="REMARKS" bundle="${EPAYINLables}"></hdiits:caption></b>
			</td>
			<td align="left" width="25%">
				<hdiits:textarea name="remark" id="remark" captionid="REMARKS" bundle="${EPAYINLables}" validation="txt.isrequired" />
			</td>
			<td width="25%"></td>
			<td width="25%"></td>
		</tr>
	</table>
		
	
	<table align="center">	
		<tr>
			<td align="center">
				<br></br><hdiits:button name="add" type="button" captionid="ADD" bundle="${EPAYINLables}" onclick="javascript:addOrUpdatePayIncrDtls('Add')"></hdiits:button>
			</td>
			<td align="center">
				<br></br><hdiits:button name="update" type="button" captionid="UPDATE" bundle="${EPAYINLables}" onclick="javascript:addOrUpdatePayIncrDtls('Update')" readonly="true"></hdiits:button>
			</td>
			<td align="center">
				<br></br><hdiits:button name="reset" type="button" captionid="RESET" bundle="${EPAYINLables}" onclick="resetData()"></hdiits:button>
			</td>
		</tr>
	</table>
	
	<br>

	<hdiits:fieldGroup id="payIncrFieldGroupId" titleCaptionId="PAYINCR_DTLS" bundle="${EPAYINLables}" collapseOnLoad="false">
	
	<table id='txnPayIncr' name="txnPayIncr" border="1" borderColor="black" align="center" width="100%" cellpadding="1"
		cellspacing="1" style="border-collapse: collapse">
		<tr>
			<td style="display:none">&nbsp;</td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NEW_BASIC_PAY" bundle="${EPAYINLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="DUE_NEXT_INCR_DATE" bundle="${EPAYINLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="REMARKS" bundle="${EPAYINLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="EDT_DEL" bundle="${EPAYINLables}" /></label></b></td>
		</tr>
	</table>
	
	<c:forEach items="${EmpAllPayDtlsLst}" var="PayFixVO" varStatus="x">
		<c:set var="currentXMLFileE" value="${PayFixXMLFileLst[x.index]}"></c:set>
		<c:set var="NewBasicScale" value="${PayFixVO.revPay}" />
		<fmt:formatDate var="NextIncDate" pattern="dd/MM/yyyy" value="${PayFixVO.nxtIncrDate}" type="date" />
		<c:set var="NextIncDate" value="${NextIncDate}" />
		<c:set var="Remarks" value="${PayFixVO.remarks}" />
		<script type="text/javascript">
			var remark='${Remarks}';
			if(remark=='' || remark==null)
			{
				remark='-';
			}
			var xmlFileNameE = '${currentXMLFileE}';
			var displayFieldArray1  = new Array('${NewBasicScale}','${NextIncDate}',remark);
			addDBDataInTable('txnPayIncr','encXMLpayIncr',displayFieldArray1,xmlFileNameE, 'editEmpPayIncrRecord','deleteDBEmpPayIncrRecord');
		</script>
    </c:forEach>
	</hdiits:fieldGroup>
	</hdiits:fieldGroup>
	<br>
	<table align="center">
	<tr>
		<td>
			<hdiits:button name="Submit" type="button" captionid="SUBMIT" bundle="${EPAYINLables}" onclick="submitInDb()"/>
		</td>
		<td>
			<hdiits:button name="Close" type="button" captionid="CLOSE" bundle="${EPAYINLables}" onclick="closeWindow()"></hdiits:button>
		</td>
		</tr>
	</table>
<script type="text/javascript">
	var crntPayScale='${hrPayfixMstPayScale}';
	var defType='';
	var lwp='${empPayIncrVOCus.LWP}';
	var DueDate = '${DueDate}';
	var EffectiveDate = '${EffectiveDate}';
	var newBasicPay = '${empPayIncrVOCus.currentPay}';  
	var checkDefRec=document.frmEmpNextPayIncr.rdoTypeOfInc;
	onLoadPayIncrement();
	initializetabcontent("maintab");
	setWindowName(window, document.frmEmpNextPayIncr);
</script>

	<hdiits:hidden name="hdnPayIncrId" id="hdnPayIncrId" />
	<hdiits:hidden name="hdnUserId" id="hdnUserId" default="${SelectedUserId}"/>

</div></div>
</hdiits:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
