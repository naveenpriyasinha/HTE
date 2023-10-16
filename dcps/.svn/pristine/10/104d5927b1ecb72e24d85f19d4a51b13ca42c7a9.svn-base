<%
try {
%>
<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.hr.acr.acr_AlertMessages" var="commonLables1" scope="request"/>

<script type="text/javascript" src="common/script/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>

<script type="text/javascript">
var textAreaValidation = new Array();
var reTextAreaValidation = new Array();
var validateFlag=true;
var disableAllObjFlag=false;
function disableAcrPage()
{
	var totalElements= document.forms[0].elements.length
	for(i=0;i<totalElements;i++)
	{
		if(document.forms[0].elements[i].type=='text' || document.forms[0].elements[i].type=='radio'
			|| document.forms[0].elements[i].type=='select-multiple' || document.forms[0].elements[i].type=='button'
			|| document.forms[0].elements[i].type=='textarea' || document.forms[0].elements[i].type=='checkbox'
			|| document.forms[0].elements[i].type=='select' || document.forms[0].elements[i].type=='select-one')
		{
			document.forms[0].elements[i].disabled=true
		}
	}
}
function createCorr(hieDtlId)
{
	if(confirm('<fmt:message  bundle="${commonLables1}" key="HR.ACR.CreateCorr"/>'))
	{
		validateFlag=false;
		document.frmAdverseRemark.method="POST";
		document.frmAdverseRemark.action="./hrms.htm?actionFlag=ACRSubmittedAdverseRemarks&flag="+hieDtlId;	
		document.frmAdverseRemark.submit();
	}
}

function createCorrInUserBox(hieDtlId)
{
	if(confirm('<fmt:message  bundle="${commonLables1}" key="HR.ACR.CreateCorr"/>'))
	{
		validateFlag=false;
		document.frmAdverseRemark.method="POST";
		document.frmAdverseRemark.action="./hdiits.htm?actionFlag=ACRSubmittedAdverseRemarks&applicant=1&flag="+hieDtlId;	
		document.frmAdverseRemark.submit();
	}
}
function generateTextArea(btn,no)
{	
	document.getElementById(btn).disabled=true;
	var counter = document.getElementById('count'+no).value;
	var tableId='remarks'+no;
	var srNo=0;
	document.getElementById(tableId).style.display='';
	//document.getElementById('remarksBtn'+no).style.display='';	
	for(var i=0;i<=counter;i++)
	{			
		var chkBox= document.getElementById('chkBox'+no+'_'+i).value;
		var point = document.getElementById('point'+no+'_'+i).value;
		if(document.getElementById('chkBox'+no+'_'+i).checked==true)
		{
			var trow=document.getElementById(tableId).insertRow();		
			trow.insertCell(0).innerHTML = srNo+1; 
			trow.insertCell(1).innerHTML = point;
			trow.insertCell(2).innerHTML = "<textarea name='Value"+no+"_"+i+"' id='Value"+no+"_"+i+"' cols='60' maxlength='4000' ></textarea><label class='mandatoryindicator'>*</label>"
										+"<input type='hidden' name='pointCode"+no+"_"+i+"' id='pointCode"+no+"_"+i+"' value='"+chkBox+"'/>";
			textAreaValidation.push(no+"_"+i);
			srNo++;					
		}
		document.getElementById('chkBox'+no+'_'+i).disabled=true;		
	}
}
function checkBoxSelect(chkBox,objName,selfRead,div)
{									
	if(selfRead=='Y' || selfRead=='A')
	{		
		try 			
		{				
			for(i=0;i<document.frmAdverseRemark.elements.length;i++)
			{
				if(document.frmAdverseRemark.elements[i].type == 'textarea' && document.frmAdverseRemark.elements[i].id.search(/Value/)==0) 
				{
					document.frmAdverseRemark.elements[i].readOnly=true;
				}
				var counter = document.getElementById('count'+div).value;					
			}
			for(var i=0;i<=counter;i++)
			{			
				try 
				{				
					var chkBoxObj=document.getElementById('chkBox'+div+'_'+i);
					if(chkBoxObj.value==chkBox)
					{
						chkBoxObj.checked=true;
					}
					chkBoxObj.disabled=true;
				}catch(exception){}
			}
		}catch(ex){}
	}
	else
	{
		try 	
		{
			document.getElementById(objName).disabled=true;			
			var counter = document.getElementById('count'+div).value;
			for(var i=0;i<=counter;i++)
			{			
				try {				
					var chkBoxObj=document.getElementById('chkBox'+div+'_'+i);
					if(chkBoxObj.value==chkBox)
					{
						chkBoxObj.checked=true;
					}
					chkBoxObj.disabled=true;
				}catch(exception){}
			}
		}catch(ex){}
	}
}
function validateMe()
{
	if(validateFlag==true)
	{
		document.getElementById('acrKey').value=textAreaValidation;		
		document.getElementById('reAcrKey').value=reTextAreaValidation;
		for(var i=0;i<textAreaValidation.length;i++)
		{
			if(document.getElementById("Value"+textAreaValidation[i]).value=='')
			{
				alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.Mandatory"/>');			
				return false;
			}
		}
		for(var i=0;i<reTextAreaValidation.length;i++)
		{
			if(document.getElementById("ReValue"+reTextAreaValidation[i]).value=='')
			{
				alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.Mandatory"/>');			
				return false;
			}
		}
	}
	return true;
}
function closeButtonHandler()
{
	document.frmAdverseRemark.method="POST";
	document.frmAdverseRemark.action="./hrms.htm?actionFlag=getDocListOfWorkList";	
	document.frmAdverseRemark.submit();
	
}
</script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	

<c:set var="corrId" value="${resValue.corrId}"></c:set>
<c:set var="userMap" value="${resValue.userMap}"></c:set>
<c:set var="pointDtlMap" value="${resValue.pointDtlMap}"></c:set>
<c:set var="pointValueMap" value="${resValue.pointValueMap}"></c:set>
<c:set var="allKeyList" value="${resValue.allKeyList}"></c:set>
<c:set var="rolePointValue" value="${resValue.rolePointValue}"></c:set>
<c:set var="selectedYear" value="${resValue.year}"></c:set>
<c:set var="pointList" value="${resValue.pointList}"></c:set>
<c:set var="selfAppPoint" value="${resValue.selfPoint}"></c:set>
<c:set var="selfPointValue" value="${resValue.selfPointValue}"></c:set>
<c:set var="ratingLst" value="${resValue.ratingLst}"></c:set>
<c:set var="reportingOff" value="${resValue.reportingOff}"></c:set>
<c:set var="reportingOffPoint" value="${resValue.reportingOffPoint}"></c:set>
<c:set var="reportingOffPointValue" value="${resValue.reportingOffPointValue}"></c:set>
<c:set var="adverseRemarkPoint" value="${resValue.adversePoint}"></c:set>
<c:set var="adverseRemarkPointValue" value="${resValue.adversePointValue}"></c:set>
<c:set var="reAdverseRemarkValueLst" value="${resValue.reAdverseRemarkValue}"></c:set>
<c:set var="showForGrpDtlId" value="${resValue.showForGrpDtlId}"></c:set>
<c:set var="selfRead" value="${resValue.selfRead}"></c:set>
<c:set var="reAdverseRemarkUserMap" value="${resValue.reAdverseRemarkUserMap}"></c:set>
<c:set var="reAdverseRemarkPointMap" value="${resValue.reAdverseRemarkPointMap}"></c:set>
<c:set var="reAdverseRemarkValueMap" value="${resValue.reAdverseRemarkPointValueMap}"></c:set>
<c:set var="showNTimeForEmp" value="${resValue.showNTimeForEmp}"></c:set>
<c:set var="showNTimeLst" value="${resValue.showNTimeLst}"></c:set>
<c:set var="allAdverseKeyList" value="${resValue.allAdverseKeyList}"></c:set>
<c:set var="adveseRemarksrHieUser" value="${resValue.adveseRemarksrHieUser}"></c:set>
<c:set var="corrIsCreatedInUserInbox" value="${resValue.corrIsCreatedInUserInbox}"></c:set>
<c:set var="disableAllMsg" value="${resValue.disableAllMsg}"></c:set>
<c:set var="disableAll" value="${resValue.disableAll}"></c:set>
<c:set var="fileCorrOpen" value="${resValue.fileCorrOpen}"></c:set>
<c:set var="cmnLookupOrder" value="${resValue.cmnLookupOrder}"></c:set>
<fmt:setBundle basename="resources.hr.acr.acr" var="commonLables" scope="request"/>
<hdiits:form name="frmAdverseRemark" action="./hrms.htm?actionFlag=ACRSubmittedAdverseRemarks" method="POST" validate="true"  encType="multipart/form-data" >
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="HR.ACR.ACRForYear" bundle="${commonLables}"/> : <c:out value="${selectedYear} - ${selectedYear+1}"/>
			</b></a></li>
		<c:set var="x" value="2"></c:set>
		<c:set var="selfAppCheck" value="${reportingOff[0]}"></c:set>		
		<c:set var="begin" value="0"></c:set>
		<c:if test="${selfAppCheck.selfApp eq 'Y'}">
			<c:set var="begin" value="1"></c:set>		
		</c:if>
		<c:forEach items="${reportingOff}" var="office" begin="${begin}">
			<li class="selected"><a href="#" rel="tcontent${x}">
			<b>
				<c:out value="${office.name} ${office.time}"></c:out>
				<c:set var="x" value="${x+1}"></c:set>
			</b></a></li>	
		</c:forEach>
	</ul>	
</div>
<div id="acr" name="acr">
	 <div id="tcontent1"  tabno="0" class="tabcontent">	 	
 		<jsp:include page="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"></jsp:include>
 		<c:if test="${fileCorrOpen eq '1'}">
 			<script type="text/javascript">
				disableAllObjFlag=true;
			</script>
 		</c:if>
		<c:if test="${disableAll eq '1'}">
			<script type="text/javascript">
				disableAllObjFlag=true;
			</script>
			<table id="recordInserted" align="center" border="1" bgcolor="white" bordercolor="black" width="100%">
			<tr>
				<td colspan="10" align="center" >
				<font color="red"><b>
					<c:out value="${disableAllMsg}"></c:out>						
				</b></font>				
				</td>
			</tr>
			</table>
		</c:if>
		<br><br>
		<jsp:include page="wfCommonInclude.jsp"></jsp:include>	
		<br><br>						
			<hdiits:hidden id="divCount" name="divCount" default="-1"/>						
		<br><br>
		<table width="100%">
		<tr>
		<td align="center" colspan="4">
			<c:if test="${showCorr eq 'Y'}">			
				<hdiits:submitbutton type="button" name="btnCorr" captionid="HR.ACR.BtnCreateCorr" bundle="${commonLables}" onclick="validateForm();" />
			</c:if>
		</td>
		</tr>
		</table>				
	</div>	
	<c:set var="textAreaCounter" value="0"></c:set>
	<hdiits:hidden name="acrKey" id="acrKey" default=""></hdiits:hidden>
	<hdiits:hidden name="reAcrKey" id="reAcrKey" default=""></hdiits:hidden>
	<hdiits:hidden name="corrId" id="corrId" default="${resValue.corrId}"/>	
	<hdiits:hidden name="selectedYear" id="selectedYear" default="${resValue.year}"/>
	<c:set var="showButton" value="0"></c:set>
	<c:set var="counterNextTextId" value="1"></c:set>
	<c:set var="begin" value="0"></c:set>
	<c:set var="divC" value="0"></c:set>
	<c:if test="${selfAppCheck.selfApp eq 'Y'}">
		<c:set var="begin" value="1"></c:set>		
	</c:if>
	<c:forEach items="${reportingOff}" var="office" varStatus="offStatus" begin="${begin}">	
		<c:set var="divCount" value="${office.hieGrpId}"></c:set>		
		<div id="tcontent${divC+2}" class="tabcontent" tabno="${divC+1}">						
			<BR><BR>
			<c:forEach items="${adveseRemarksrHieUser}" var="ad">
				<c:if test="${ad eq divCount}">
					<c:set var="showButton" value="${showButton+1}"></c:set>		
				</c:if>
			</c:forEach>
			<c:set var="showBtn" value="1"></c:set>
			<c:forEach items="${corrIsCreatedInUserInbox}" var="varBtn">
				<c:if test="${varBtn eq divCount}">
					<c:set var="showBtn" value="2"></c:set>		
				</c:if>
			</c:forEach>	
			<hdiits:hidden id="count${divCount}" name="count${divCount}" default="-1"/>
			<%--
				This Code is used to only check box table.
			 --%>																											
			<hdiits:fieldGroup collapseOnLoad="false" id="acr_advRem_field4" titleCaption="${office.name}  ${office.time}">	
				<table width="100%" id="poitTable${divC}" name="poitTable${divC}" style="border-collapse: collapse;" borderColor="BLACK"  border="1">				
				<tr bgcolor="#B0C4DE">
					<td align="center" class="fieldLabel">
						<b><hdiits:caption captionid="HR.ACR.Action" bundle="${commonLables}"/></b>		
					</td>
					<td align="center" class="fieldLabel">
						<b><hdiits:caption captionid="HR.ACR.index" bundle="${commonLables}"/></b>		
					</td>			
					<td align="center" class="fieldLabel">
						<b><hdiits:caption captionid="HR.ACR.Goal" bundle="${commonLables}"/></b>		
					</td>			
					<td align="center" class="fieldLabel">
						<b><hdiits:caption captionid="HR.ACR.Remark" bundle="${commonLables}"/></b>		
					</td>	
				</tr>
					<c:set var="x" value="0"></c:set>
					<c:if test="${office.selfApp eq 'Y' or office.selfApp eq 'U'}">
						<c:set var="adverseRemarkValueLst1" value="${adverseRemarkPointValue[0]}"></c:set>						
						<c:set var="adverseRemarkLst1" value="${adverseRemarkPoint[0]}"></c:set>		
					</c:if>					
					<c:set var="adverseRemarkValueLst" value="${adverseRemarkPointValue[offStatus.index]}"></c:set>
					<c:set var="adverseRemarkLst" value="${adverseRemarkPoint[offStatus.index]}"></c:set>					
					<c:set var="repOffValueLst" value="${reportingOffPointValue[offStatus.index]}"></c:set>
					<c:set var="repOffPointLst" value="${reportingOffPoint[offStatus.index]}"></c:set>
					<c:forEach items="${repOffPointLst}" varStatus="pointSta" var="points">
						<tr>
							<td align="center" class="fieldLabel">
								<hdiits:checkbox value="${points.pointCode}" name="chkBox${divCount}_${x}" id="chkBox${divCount}_${x}" />
							</td>
							<td class="fieldLabel">
								<c:out value="${x+1}"></c:out>								
							</td>						
							<td class="fieldLabel">
								<c:out value="${points.point}"></c:out>	
								<hdiits:hidden name="point${divCount}_${x}" id="point${divCount}_${x}" default="${points.point}" />
							</td>
							<td  class="fieldLabel">															
								<hdiits:textarea readonly="true" name="myTextAreadata${textAreaCounter}" cols="60" mandatory="true" default="${repOffValueLst[pointSta.index].pointValue}"></hdiits:textarea>
								<c:set var="textAreaCounter" value="${textAreaCounter+1}"></c:set>
							</td>												
							<script type="text/javascript">
								document.getElementById('count${divCount}').value='${x}';								
							</script>
							<c:set var="x" value="${x+1}"></c:set>
						</tr>						
					</c:forEach>
					<c:if test="${empty repOffPointLst}">
						<tr align="center"><td colspan="10" class="fieldLabel" align="center">					
							<b><fmt:message key="HR.ACR.NORECORD" bundle="${commonLables}"/></b>
						</td></tr>
					</c:if>						
				</table>
			</hdiits:fieldGroup>
			<%--
				This Code is end of check box table.
				Below Code is used to show a button for create corrr, write adverse remark point.
			 --%>	
			<BR><BR>
			<c:if test="${fileCorrOpen ne 1 and cmnLookupOrder ne 1}">
				<c:choose>									
				<c:when test="${showBtn eq '2' and cmnLookupOrder gt 1}">
					<CENTER>
						<hdiits:caption captionid="HR.ACR.CorrCreated" bundle="${commonLables}"/>
					</CENTER>
				</c:when>
				<c:otherwise>				
					<c:choose>					
						<c:when test="${not empty repOffPointLst and selfRead ne 'Y' and selfRead ne 'A' and cmnLookupOrder lt 1}">									
							<CENTER>
								<hdiits:button name="Btn${divC}" id="Btn${divC}" type="button" captionid="HR.ACR.BtnRemarks" bundle="${commonLables}" onclick="generateTextArea('Btn${divC}',${divCount});"/>
							</CENTER>
						</c:when>
						<c:otherwise>
							<c:if test="${selfRead ne 'A' and office.selfApp ne 'U'}">
								<CENTER>								
									<hdiits:button name="CreateCorr${divC}" id="CreateCorr${divC}" type="button" captionid="HR.ACR.BtnCreateCorrOff" bundle="${commonLables}" onclick="createCorr(${divCount});"/>
								</CENTER>
							</c:if>
							<c:if test="${selfRead eq 'Y' and office.selfApp eq 'U'}">
								<CENTER>
									<hdiits:button name="CreateUserCorr${divC}" id="CreateUserCorr${divC}" type="button" captionid="HR.ACR.BtnCreateCorr" bundle="${commonLables}" onclick="createCorrInUserBox(${divCount});"/>
								</CENTER>
							</c:if>
						</c:otherwise>
					</c:choose>
					</c:otherwise>
				</c:choose>
			</c:if>		
				
			<script type="text/javascript">
				document.getElementById('divCount').value='${divCount}';
			</script>	
			<BR><BR>
			<%-- 
				This code is used for entering a Self App data.
			--%>
			
			<hdiits:fieldGroup collapseOnLoad="false" id="acr_advRem_field5" titleCaptionId="HR.ACR.SelfAppraisalData" bundle="${commonLables}">	
			<table class="tabtable" id="remarks${divCount}" name="remarks${divCount}" style="border-collapse: collapse;" borderColor="BLACK"  border="1">			
				<tr bgcolor="#B0C4DE">
					<td align="center" class="fieldLabel">
						<b><hdiits:caption captionid="HR.ACR.index" bundle="${commonLables}"/></b>		
					</td>			
					<td align="center" class="fieldLabel">
						<b><hdiits:caption captionid="HR.ACR.Goal" bundle="${commonLables}"/></b>		
					</td>			
					<td align="center" class="fieldLabel">
						<b><hdiits:caption captionid="HR.ACR.Remark" bundle="${commonLables}"/></b>		
					</td>	
			</tr>
			<c:set var="adverCount" value="1"></c:set>			
			<c:if test="${selfAppCheck.selfApp eq 'Y'}">
				<c:set var="adverseRemarkValueLst1" value="${adverseRemarkPointValue[0]}"></c:set>
				<c:set var="adverseRemarkLst1" value="${adverseRemarkPoint[0]}"></c:set>				
				<c:forEach items="${adverseRemarkLst1}" var="adverPoint" varStatus="adverSta">					
					<c:if test="${adverseRemarkValueLst1[adverSta.index].hrAcrHierarchyGrpDtl.groupDtlId eq divCount}">
					<tr>
						<td class="fieldLabel"><c:out value="${adverCount}"></c:out></td>
						<td class="fieldLabel"><c:out value="${adverPoint.point}"></c:out></td>
						<td class="fieldLabel" colspan="2">
							<hdiits:textarea name="Value${divCount}_${adverCount}" id="Value${divCount}_${adverCount}" cols="60" mandatory="true"
							maxlength="4000" validation="txt.isrequired"
							captionid="HR.ACR.Remark" bundle="${commonLables}"
							default="${adverseRemarkValueLst1[adverSta.index].pointValue}"></hdiits:textarea>
							<hdiits:hidden name="pointCode${divCount}_${adverCount}" id="pointCode${divCount}_${adverCount}}" default="${adverPoint.pointCode}" /> 
							<script type="text/javascript">
								textAreaValidation.push(${divCount}+'_'+${adverCount});
								checkBoxSelect(${adverPoint.pointCode},'Btn${divC}','${selfRead}',${divCount});
							</script>
					</td></tr>	
					<c:set var="adverCount" value="${adverCount+1}"></c:set>				
					</c:if>
				</c:forEach>
				</c:if>
				<c:forEach items="${adverseRemarkLst}" var="adverPoint" varStatus="adverSta">
					<tr>
						<td class="fieldLabel">
							<c:out value="${adverCount}"></c:out>
						</td>			
						<td class="fieldLabel">
							<c:out value="${adverPoint.point}"></c:out>
						</td>			
						<td class="fieldLabel" colspan="2">
							<hdiits:textarea name="Value${divCount}_${adverCount}" id="Value${divCount}_${adverCount}" cols="60" mandatory="true" maxlength="4000" validation="txt.isrequired" captionid="HR.ACR.Remark" bundle="${commonLables}" default="${adverseRemarkValueLst[adverSta.index].pointValue}"></hdiits:textarea>
							<hdiits:hidden name="pointCode${divCount}_${adverCount}" id="pointCode${divCount}_${adverCount}}" default="${adverPoint.pointCode}"/>
							<script type="text/javascript">														
								textAreaValidation.push(${divCount}+'_'+${adverCount});
								checkBoxSelect(${adverPoint.pointCode},'Btn${divC}','${selfRead}',${divCount});
							</script>
						</td>
					</tr>
					<c:set var="adverCount" value="${adverCount+1}"></c:set>
				</c:forEach>
				</table>
				</hdiits:fieldGroup>
			<BR><BR>
			<c:if test="${not empty allAdverseKeyList}">
			<hdiits:fieldGroup collapseOnLoad="false" id="acr_advRem_field7" titleCaptionId="HR.ACR.ACRFill" bundle="${commonLables}">	
				<c:forEach items="${allAdverseKeyList}" var="key" varStatus="countKeySta">
				<c:set var="firstRow" value="1"></c:set>
				<table class="tabtable" id="newPoitTable" name="newPoitTable" style="border-collapse: collapse;" borderColor="BLACK"  border="1">										
					<c:set var="valueList" value="${reAdverseRemarkValueMap[key]}"></c:set>	
					<c:set var="pointRoleList" value="${reAdverseRemarkPointMap[key]}"></c:set>					
					<c:set var="userList" value="${reAdverseRemarkUserMap[key]}"></c:set>	
					<c:forEach items="${userList}" var="user">										
					<c:if test="${divCount eq user.hieGrpId}">
						<c:if test="${not empty pointRoleList}">				
							<c:choose>
							<c:when test="${firstRow ne 1}">
								<tr background="white" bordercolor="white" height="20px"><td colspan="10"></td></tr>
							</c:when>
							<c:otherwise>
								<c:set var="firstRow" value="2"></c:set>
							</c:otherwise>	
						</c:choose>							
							<tr bgcolor="#817679">
								<td colspan="10" class="fieldLabel" align="center">
									<font class="Label3" color="white">
									<u>
									<b>		
									<c:out value="${user.cmnLookupObj.lookupDesc}"></c:out>
									</b></u></font>
								</td>
							</tr>						
							<tr>
								<td bgcolor="#E0FFFF" width="25%">
									<b><hdiits:caption captionid="HR.ACR.Name" bundle="${commonLables}"/></b>		
								</td>			
								<td width="25%">
									<c:out value="${user.name}"></c:out>
								</td>
								<td bgcolor="#E0FFFF" width="25%">
									<b><hdiits:caption captionid="HR.ACR.Designation" bundle="${commonLables}"/></b>		
								</td>
								<td width="25%">
									<c:out value="${user.desgn}"></c:out>
								</td>					
							</tr>
						</c:if>	
					</c:if>								
					<c:if test="${divCount eq user.hieGrpId}">			
					<c:if test="${not empty pointRoleList}">
						<tr bgcolor="#B0C4DE">
							<td class="fieldLabel" align="center">
								<b><hdiits:caption captionid="HR.ACR.index" bundle="${commonLables}"/></b>		
							</td>			
							<td class="fieldLabel" align="center" colspan="2">
								<b><hdiits:caption captionid="HR.ACR.Goal" bundle="${commonLables}"/></b>		
							</td>			
							<td colspan="3" class="fieldLabel" align="center">
								<b><hdiits:caption captionid="HR.ACR.Remark" bundle="${commonLables}" /></b>		
							</td>	
						</tr>												
						<c:set value="1" var="counterText"></c:set>
						<c:forEach items="${pointRoleList}" var="points" varStatus="i1">
							<tr>
								<td class="fieldLabel">
									<c:out value="${counterText}"></c:out>
									<c:set var="counterText" value="${counterText+1}"></c:set>								
								</td>						
								<td class="fieldLabel" colspan="2">
									<c:out value="${points.point}"></c:out>
								</td>
								<td class="fieldLabel" colspan="3">
									<hdiits:textarea cols="60" mandatory="true"
									maxlength="4000"  name="i1Text${divCount}_${counterNextTextId}"
									captionid="HR.ACR.Remark" bundle="${commonLables}"
									default="${valueList[i1.index].pointValue}" readonly="true"></hdiits:textarea>								
									<c:set var="counterNextTextId" value="${counterNextTextId+1}"></c:set>
								</td>
							</tr>
						</c:forEach>
					</c:if>	
					</c:if>	
					</c:forEach>
					</table>										
				</c:forEach>
			</hdiits:fieldGroup>	
			</c:if>
			<BR><BR>
			<c:set var="reAdverCount" value="1"></c:set>	
			<c:if test="${selfRead eq 'A'}">
			<c:if test="${divCount eq showForGrpDtlId}">
				<hdiits:fieldGroup collapseOnLoad="false" id="acr_advRem_field6" titleCaptionId="HR.ACR.YourData" bundle="${commonLables}">	
				<table width="100%" id="remarks${divCount}" name="remarks${divCount}" style="border-collapse: collapse;" borderColor="BLACK"  border="1">				
				<tr bgcolor="#B0C4DE">
						<td align="center" class="fieldLabel">
							<b><hdiits:caption captionid="HR.ACR.index" bundle="${commonLables}"/></b>		
						</td>			
						<td align="center" class="fieldLabel">
							<b><hdiits:caption captionid="HR.ACR.Goal" bundle="${commonLables}"/></b>		
						</td>			
						<td align="center" class="fieldLabel" colspan="2">
							<b><hdiits:caption captionid="HR.ACR.Remark" bundle="${commonLables}"/></b>		
						</td>	
				</tr>
				<c:if test="${selfAppCheck.selfApp eq 'Y'}">
					<c:set var="adverseRemarkLst1" value="${adverseRemarkPoint[0]}"></c:set>
					<c:set var="reAdverseRemarkValueLst1" value="${reAdverseRemarkValueLst[0]}"></c:set>					
					<c:forEach items="${adverseRemarkLst1}" var="adverPoint" varStatus="adverSta">
						<c:if test="${divCount eq showForGrpDtlId}">											
						<c:if test="${adverseRemarkValueLst1[adverSta.index].hrAcrHierarchyGrpDtl.groupDtlId eq divCount}">
						<tr>
							<td class="fieldLabel"><c:out value="${reAdverCount}"></c:out></td>
							<td class="fieldLabel"><c:out value="${adverPoint.point}"></c:out></td>
							<td class="fieldLabel" colspan="2">
								<hdiits:textarea name="ReValue${divCount}_${reAdverCount}" id="ReValue${divCount}_${reAdverCount}" cols="60" mandatory="true"
								maxlength="4000" validation="txt.isrequired"
								captionid="HR.ACR.Remark" bundle="${commonLables}"
								default="${reAdverseRemarkValueLst1[reAdverCount-1].pointValue}"></hdiits:textarea>
								<hdiits:hidden name="rePointCode${divCount}_${reAdverCount}" id="rePointCode${divCount}_${reAdverCount}}" default="${adverPoint.pointCode}" /> 
								<script type="text/javascript">
									reTextAreaValidation.push(${divCount}+'_'+${reAdverCount});									
								</script>
						</td></tr>	
						<c:set var="reAdverCount" value="${reAdverCount+1}"></c:set>				
						</c:if>
						</c:if>
					</c:forEach>
				</c:if>	
				</table>
				</hdiits:fieldGroup>			
			</c:if>
			</c:if>			
			<c:if test="${showNTimeForEmp eq 1}"> 
				<c:forEach items="${showNTimeLst}" var="show">
				<c:if test="${divCount eq show}">			
					<table class="tabtable" id="myremarks${divCount}" name="myremarks${divCount}" style="border-collapse: collapse;" borderColor="BLACK"  border="1">
					<tr bgcolor="#817679">
						<td align="center" colspan="10">
							<font class="Label3" align="center" color="white"><b><u>
								<fmt:message key="HR.ACR.SelfAppraisalData" bundle="${commonLables}"/>
							</u></b></font>	
						</td>
					</tr>
					<tr bgcolor="#B0C4DE">
							<td align="center" class="fieldLabel">
								<b><hdiits:caption captionid="HR.ACR.index" bundle="${commonLables}"/></b>		
							</td>			
							<td align="center" class="fieldLabel">
								<b><hdiits:caption captionid="HR.ACR.Goal" bundle="${commonLables}"/></b>		
							</td>			
							<td align="center" class="fieldLabel" colspan="2">
								<b><hdiits:caption captionid="HR.ACR.Remark" bundle="${commonLables}"/></b>		
							</td>	
					</tr>
					<c:set var="myDataC" value="1"></c:set>	
					<c:set var="adverseRemarkLst1" value="${adverseRemarkPoint[0]}"></c:set>
					<c:set var="reAdverseRemarkValueLst1" value="${reAdverseRemarkValueLst[0]}"></c:set>
					<c:forEach items="${adverseRemarkLst1}" var="adverPoint" varStatus="adverSta">															
						<c:if test="${adverseRemarkValueLst1[adverSta.index].hrAcrHierarchyGrpDtl.groupDtlId eq divCount}">					
						<tr>
							<td class="fieldLabel"><c:out value="${myDataC}"></c:out></td>
							<td class="fieldLabel"><c:out value="${adverPoint.point}"></c:out></td>
							<td class="fieldLabel" colspan="2">
								<hdiits:textarea name="ReValue${divCount}_${reAdverCount}" id="ReValue${divCount}_${reAdverCount}" cols="60" mandatory="true"
								maxlength="4000" validation="txt.isrequired"
								captionid="HR.ACR.Remark" bundle="${commonLables}"
								default="${reAdverseRemarkValueLst1[myDataC-1].pointValue}"></hdiits:textarea>
								<hdiits:hidden name="rePointCode${divCount}_${reAdverCount}" id="rePointCode${divCount}_${reAdverCount}}" default="${adverPoint.pointCode}" /> 
								<script type="text/javascript">
									reTextAreaValidation.push(${divCount}+'_'+${reAdverCount});									
								</script>
						</td></tr>	
						<c:set var="myDataC" value="${myDataC+1}"></c:set>				
						<c:set var="reAdverCount" value="${reAdverCount+1}"></c:set>	
						</c:if>
					</c:forEach>
					</table>
				</c:if>
			</c:forEach>	
			</c:if>
			<c:set var="divC" value="${divC+1}"></c:set>									
		</div>
	</c:forEach>	
</div>
<jsp:include page="../../../core/tabnavigation.jsp" />					
<hdiits:jsField name="validateMe" jsFunction="validateMe()"/>
<hdiits:validate locale="${locale}" controlNames="" />
</hdiits:form>	
<script type="text/javascript">
			if('${resValue.disableChkBox}'=='1')			
			{
				for(i=0;i<document.frmAdverseRemark.elements.length;i++)
				{
					if(document.frmAdverseRemark.elements[i].type == 'checkbox' && document.frmAdverseRemark.elements[i].disabled==false)   
					{
						document.frmAdverseRemark.elements[i].disabled=true;
					}
				}
			}
			initializetabcontent("maintab")									
			if(disableAllObjFlag==true)
			{
				disableAcrPage();
			}	
			if('{resValue.acrReportFlag}'=='1'){disableAcrPage();}
</script>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
