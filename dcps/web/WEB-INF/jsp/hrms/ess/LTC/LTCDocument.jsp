<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%
try {
%>


<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
<script ="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/addRecord.js"></script>
<script type="text/javascript"></script>
<fmt:setBundle basename="resources.ess.ltc.Constants" var="constants"
	scope="request" />
<fmt:setBundle basename="resources.ess.ltc.AlertMessages"
	var="alertLables" scope="request" />
<fmt:setBundle basename="resources.ess.ltc.LtcCaption" var="ltcCaption"
	scope="request" />
	
<style type="text/css">

/* All form elements are within the definition list for this example */
.tablecell{
   border-bottom: solid 1px #DEDEDE;
	border:1;
	border-color:black;
	font-size: 11px;
	font-style: normal;
	font-weight: normal;
	background: white;
	text-align: left;
	padding: 1px;
	
}
.tablerow{
  border-top: solid 1px #333333;
	border-left: solid 1px #666666;
	border-right: solid 0px #888888;
	border-bottom: solid 1px #666666; 
	font-size: 11px;
	font-style: normal;
	font-weight: bold;
	text-align: center;
	background: #C9DFFF;
	color: black;
	padding: 1px;
	
	
}
</style>
<script language="javascript">

function Age(birthDate)
		{					
			if(birthDate!=null)
			{	
				var splitDate=birthDate.split("-");				
				var bday=parseInt(splitDate[1]);
				var bmo=(parseInt(splitDate[2])-1);
				var byr=parseInt(splitDate[0]);				
				var byr;
				var age;
				var now = new Date();
				tday=now.getDate();
				tmo=(now.getMonth());
				tyr=(now.getFullYear());								
				if((tmo > bmo)||(tmo==bmo & tday>=bday)) {age=byr}				
				else  {age=byr+1}
				return (tyr-age);						
			}
		}
</script>

<hdiits:form name="frmltcpage" validate="true" method="post"
	action="./hrms.htm?actionFlag=saveLTCInboxPage"
	encType="text/form-data">

	<c:set var="resultObj" value="${result}"></c:set>
	<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
	<c:set var="requestData" value="${resultValue.ltcRequestData}"></c:set>
	<c:set var="ltcStatusFlag" value="${resultValue.ltcStatusFlag}"></c:set>
	<c:set var="EmpDetVO" value="${resultValue.EmpDet}" />
	<c:set var="lstLtcDtl" value="${resultValue.lstLtcDtl}"></c:set>

	<c:set var="LtcMsg" value="${resultValue.LtcMsg}"></c:set>
	<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="tableBGColor" value="white"></c:set>

	<br>
	<c:forEach var="reqData" items="${requestData}">
		<!-- Emp Info Code Start		-->

		<jsp:include page="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp">
			<jsp:param name="empImage" value="N" />
		</jsp:include>


<hdiits:fieldGroup id="ltcDtls" titleCaptionId="HRMS.LTCDetails" bundle="${ltcCaption}" collapseOnLoad="false">
		<table class="tabtable">
			<tr>
				<td width="25%"><b><hdiits:caption captionid="HRMS.LTCReqType"
					bundle="${ltcCaption}" /></b></td>
				<td width="25%"><c:if test="${reqData.reqType=='1'}">
					<fmt:message key="HRMS.Advance" bundle="${ltcCaption}" />
				</c:if> <c:if test="${reqData.reqType=='0'}">
					<fmt:message key="HRMS.Reimbursement" bundle="${ltcCaption}" />
				</c:if></td>
				<td width="25%"><b><hdiits:caption captionid="HRMS.Journey"
					bundle="${ltcCaption}" /></b></td>
				<td width="25%"><c:if test="${reqData.journey=='1'}">
					<fmt:message key="HRMS.Self" bundle="${ltcCaption}" />
				</c:if> <c:if test="${reqData.journey=='0'}">
					<fmt:message key="HRMS.FamilyOnly" bundle="${ltcCaption}" />
				</c:if></td>
			</tr>
			<tr>
				<td width="25%" width="25%"><b><hdiits:caption captionid="HRMS.DateOfJourney"
					bundle="${ltcCaption}" /></b></td>
				<td width="25%"><fmt:formatDate type="date" pattern="dd-MM-yyyy"
					value="${reqData.fromDate}"></fmt:formatDate></td>
				<td width="25%"><b><hdiits:caption captionid="HRMS.DateOfReturnJourney"
					bundle="${ltcCaption}" /></td>
				<td width="25%"><fmt:formatDate type="date" pattern="dd-MM-yyyy"
					value="${reqData.toDate}"></fmt:formatDate></td>
			</tr>
			<tr>
				<td width="25%"><b><hdiits:caption captionid="HRMS.VisitingPlace"
					bundle="${ltcCaption}" /></b></td>
				<td width="25%"><c:if
					test="${reqData.cmnLookupMstByVisitingPlace.lookupId==300167}">
					<fmt:message bundle="${alertLables}" key="HR.Home_Town" />
				</c:if> <c:if
					test="${reqData.cmnLookupMstByVisitingPlace.lookupId==300168}">
					<fmt:message bundle="${alertLables}" key="HR.LTC" />
				</c:if></td>
				<td width="25%"><b><hdiits:caption captionid="HRMS.PlaceName"
					bundle="${ltcCaption}" /></b></td>
				<td width="25%"><c:out value="${reqData.placeName}"></c:out></td>
			</tr>
			<tr>
				<c:if test="${reqData.reqType=='1'}">
					<td width="25%"><b><hdiits:caption captionid="HRMS.TransportMode"
						bundle="${ltcCaption}" /></b></td>
					<td width="25%"><c:if
						test="${reqData.cmnLookupMstByTravelMode.lookupName=='LTC_Road'}">
						<fmt:message bundle="${alertLables}" key="HR.Bus" />
					</c:if> <c:if
						test="${reqData.cmnLookupMstByTravelMode.lookupName=='LTC_Rail'}">
						<fmt:message bundle="${alertLables}" key="HR.Rail" />
					</c:if> <c:if test="${reqData.cmnLookupMstByTravelMode.lookupName=='Cab'}">
						<fmt:message bundle="${alertLables}" key="HR.Cab" />
					</c:if> <c:if test="${reqData.cmnLookupMstByTravelMode.lookupName=='LTC_Air'}">
						<fmt:message bundle="${alertLables}" key="HR.Air" />
					</c:if></td>
				</c:if>
				<td width="25%"><b><hdiits:caption captionid="HRMS.Entitled"
					bundle="${ltcCaption}" /></b></td>
				<td width="25%" colspan="4"><b><font color="red"> <c:if
					test="${LtcMsg=='Second Sleeper'}">
					<fmt:message bundle="${alertLables}" key="HRMS.SecondSleeper" />
				</c:if> <c:if
					test="${LtcMsg=='First class/II AC III-Tier Sleeper/AC Chair Car'}">
					<fmt:message bundle="${alertLables}" key="HRMS.ChairCar" />
				</c:if> <c:if test="${LtcMsg=='II AC 2-Tier Sleeper'}">
					<fmt:message bundle="${alertLables}" key="HRMS.TwoTierSleeper" />
				</c:if> <c:if test="${LtcMsg=='AC First Class/Air Allowance'}">
					<fmt:message bundle="${alertLables}" key="HRMS.FirstClass" />
				</c:if>
				<c:if test="${LtcMsg=='Air Allowance'}">
					<fmt:message bundle="${alertLables}" key="HRMS.FirstClass" />
				</c:if>
				 </font></b></td>


			</tr>
			<tr>
				<td width="25%"><b><hdiits:caption captionid="HRMS.Block" bundle="${ltcCaption}" /></b></td>
				<td width="25%"><c:out value="${reqData.hrEssLtcblockMst.fromYear}"></c:out>
				- <c:out value="${reqData.hrEssLtcblockMst.toYear}"></c:out></td>
				<td width="25%"><b><hdiits:caption captionid="HRMS.SpouseEmployed"
					bundle="${ltcCaption}" /></b></td>
				<td width="25%"><c:if test="${reqData.spouseEmployed=='1'}">
					<fmt:message bundle="${alertLables}" key="HR.YES" />
				</c:if> <c:if test="${reqData.spouseEmployed=='0'}">
					<fmt:message bundle="${alertLables}" key="HR.NO" />
				</c:if></td>
				
			</tr>
			<tr>
			<c:if test="${reqData.spouseEmployed=='1'}">
					<td width="25%"><b><hdiits:caption captionid="HRMS.SpouseEntitled"
						bundle="${ltcCaption}" /></b></td>
					<td width="25%"><c:if test="${reqData.spouseEntitled=='1'}">
						<fmt:message bundle="${alertLables}" key="HR.YES" />
					</c:if> <c:if test="${reqData.spouseEntitled=='0'}">
						<fmt:message bundle="${alertLables}" key="HR.NO" />
					</c:if></td>
					<td width="25%"></td>
					<td width="25%"></td>
				</c:if>
			</tr>
			
			
			
			<c:if test="${reqData.reqType=='1'}">
				<tr>
				<td width="25%"><b><hdiits:caption captionid="HRMS.Advance"
						bundle="${ltcCaption}" /></b></td>
					<td width="25%"><c:if test="${reqData.advanceReq=='1'}">
						<fmt:message bundle="${alertLables}" key="HR.YES" />
					</c:if> <c:if test="${reqData.advanceReq=='0'}">
						<fmt:message bundle="${alertLables}" key="HR.NO" />
					</c:if></td>
				<td width="25%"><b><hdiits:caption captionid="HRMS.NoOfFares"
						bundle="${ltcCaption}" /></b></td>
					<td width="25%"><c:out value="${reqData.noOfFares}"></c:out></td>
					
				</tr>
				<c:if test="${reqData.advanceReq == '1'}"> 
				<tr>
					<td width="25%"><b><hdiits:caption captionid="HRMS.ReMinFare"
						bundle="${ltcCaption}" /></b></td>
					<td width="25%"><c:out value="${reqData.minFare}"></c:out></td>
					
				</tr>
				<tr>
					<td width="25%"><b><hdiits:caption captionid="HRMS.TotalFare"
						bundle="${ltcCaption}" /></b></td>
					<td width="25%"><c:out value="${reqData.totalFare}"></c:out></td>
					<td width="25%"><b><hdiits:caption captionid="HRMS.EntitledAmount"
						bundle="${ltcCaption}" /></b></td>
					<td width="25%"><c:out value="${reqData.entitledAdvAmt}"></c:out></td>
				</tr>
				<tr>
					<td width="25%"><b><hdiits:caption captionid="HRMS.AdvanceAmount"
						bundle="${ltcCaption}" /></b></td>
					<td width="25%"><c:out value="${reqData.advanceAmtRqst}"></c:out></td>
					<td width="25%"><b><hdiits:caption captionid="HRMS.SanctionedAdvAmt"
						bundle="${ltcCaption}" /></b></td>
					<td width="25%"><hdiits:number name="sanctAdvAmount" mandatory="false"
						caption="Sanctioned Advance Amount" maxlength="6"
						default="${reqData.sanctAdvance}" id="sanctAdvAmount" /></td>
				</tr>
				</c:if>
			</c:if>

			<c:if test="${reqData.reqType=='0'}">
				<tr>


					<td width="25%" colspan="8"><display:table	list="${reqData.hrEssLtcreimbDtls}" id="row" style="width:100%;"   >
						
						<display:column class="tablecell" titleKey="HRMS.Departure"
							style="text-align: center, " sortable="false"
							headerClass="tablerow" >
					${row.fromPlace} : <fmt:formatDate type="date" pattern="dd-MM-yyyy"
								value="${row.fromDate}"></fmt:formatDate>
						</display:column>
						<display:column class="tablecell" titleKey="HRMS.Arrival"
							style="text-align: center" sortable="false"
							headerClass="tablerow">
					${row.toPlace} : <fmt:formatDate type="date" pattern="dd-MM-yyyy"
								value="${row.toDate}"></fmt:formatDate>
						</display:column>
						<display:column class="tablecell" titleKey="HRMS.DistanceInKm"
							style="text-align: center" sortable="false"
							headerClass="tablerow">
					${row.distance}
				</display:column>
						<display:column class="tablecell"
							titleKey="HRMS.TransportMode" style="text-align: center"
							sortable="false" headerClass="tablerow">
														
					${row.cmnLookupMstByTravelMode.lookupDesc}
				</display:column>
						<!-- To display the Class of Travel in RED, if the employee is not entitled to this class : Start -->
					
						
							<display:column class="tablecell" titleKey="HRMS.ClassofAcc"
								style="text-align: center" sortable="false"
								headerClass="tablerow">
					${row.cmnLookupMstByTravelClass.lookupDesc}
				</display:column>
						
						<!-- To display the Class of Travel in RED, if the employee is not entitled to this class : End -->
						<display:column class="tablecell" titleKey="HRMS.NoOfFares"
							style="text-align: center" sortable="false"
							headerClass="tablerow">
					${row.noOfFares}
				</display:column>
						<display:column class="tablecell" titleKey="HRMS.FaresPaid"
							style="text-align: center" sortable="false"
							headerClass="tablerow">
					${row.faresPaid}
				</display:column>
						<display:column class="tablecell" titleKey="HRMS.Remarks"
							style="text-align: center" sortable="false"
							headerClass="tablerow">
					${row.remarks}
				</display:column>
					</display:table></td>
				</tr>
				<tr>
					<td width="25%"><b><hdiits:caption captionid="HRMS.TotalFare"
						bundle="${ltcCaption}" /></b></td>
					<td width="25%"><c:out value="${reqData.totalFare}"></c:out></td>

				</tr>
				<tr>
					<td width="25%"><hdiits:caption captionid="HRMS.ApprAdvanceAmount"
						bundle="${ltcCaption}" /></td>
					<td width="25%">${reqData.sanctAdvance}</td>
					<td width="25%"><hdiits:caption captionid="HRMS.BalanceAmount"
						bundle="${ltcCaption}" /></td>
					<td width="25%"><hdiits:number name="balLTCAmount" mandatory="true"
						caption="Balance LTC Amount" default="${reqData.sanctLtcAmt}"
						id="balLTCAmount"  maxlength="12"/></td>

				</tr>
			</c:if>
			<tr>
				<td><hdiits:caption captionid="HRMS.ruleType" bundle="${ltcCaption}" /></td>
				<td>
					<c:if test="${reqData.ltcRuleType == 0}">
						<fmt:message key="HRMS.stateGovt" bundle="${ltcCaption}" />
					</c:if>	
					<c:if test="${reqData.ltcRuleType == 1}">
						<fmt:message key="HRMS.centralGovt" bundle="${ltcCaption}" />
					</c:if>	
				</td>
			</tr>
	</table>
	
	<c:if test="${reqData.advanceReq == '0'}"> 
				<script type="text/javascript">
				
						
					</script>

		</c:if>
</hdiits:fieldGroup>
			
	<hdiits:fieldGroup id="dependentDtls" titleCaptionId="HRMS.DependentDetails" bundle="${ltcCaption}" collapseOnLoad="false">
				<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse; background-color:${tableBGColor}" border=1 borderColor="black" width="100%">
					<tr style="background-color:#C9DFFF">
						<td width="25%" align="center" width="25%" style="background-color:${tdBGColor}"><b><fmt:message
							key="HRMS.SlNo" bundle="${ltcCaption}" /></b></td>
						<td width="25%" align="center" width="25%"><b><fmt:message
							key="HRMS.Name" bundle="${ltcCaption}" /></b></td>
						<td width="25%" align="center" width="25%"><b><fmt:message
							key="HRMS.Age" bundle="${ltcCaption}" /></b></td>
						<td width="25%" align="center" width="25%"><b><fmt:message
							key="HRMS.Relationship" bundle="${ltcCaption}" /></b></td>
					</tr>
					<c:set var="i" value="1"></c:set>
					<c:forEach var="familyData" items="${reqData.hrEssLtcdpndtDtls}">
						<tr>
							<td width="25%" align="center" width="25%"><c:out value="${i}"></c:out></td>
							<td width="25%" align="center" width="25%"><c:out
								value="${familyData.hrEisFamilyDtl.fmFirstName}"></c:out> <c:out
								value="${familyData.hrEisFamilyDtl.fmMiddleName}"></c:out> <c:out
								value="${familyData.hrEisFamilyDtl.fmLastName}"></c:out></td>
							<td width="25%" align="center" width="25%"><script>var v1=Age("${familyData.hrEisFamilyDtl.fmDateOfBirth}");
						document.write(v1);
					</script></td>
							<td width="25%" align="center" width="25%"><c:out
								value="${familyData.hrEisFamilyDtl.cmnLookupMstByFmRelation.lookupDesc}"></c:out></td>
							<c:set var="i" value="${i+1}"></c:set>
						</tr>

					</c:forEach>
				</table>
	</hdiits:fieldGroup>			
<table width="100%">		
		<input type="hidden" name="requestType" value="${reqData.reqType}">
		<c:set var="status" value="${reqData.approved}"></c:set>
		<c:if test="${(reqData.reqType=='1'&& reqData.spouseEmployed=='1'&& reqData.spouseEntitled=='1')||(reqData.reqType=='0')}">
			<tr id="SpouseAttachment">
				<td width="25%" colspan="8"><!-- For attachment : Start--> <jsp:include
					page="/WEB-INF/jsp/common/attachmentPage.jsp">
					<jsp:param name="attachmentName" value="SpouseAttachment" />
					<jsp:param name="formName" value="frmltcpage" />
					<jsp:param name="attachmentType" value="Document" />
					<jsp:param name="multiple" value="N" />
				</jsp:include> <!-- For attachment : End--></td>
			</tr>
			<script>
				document.getElementById('target_uploadSpouseAttachment').style.display='none';
				document.getElementById('formTable1SpouseAttachment').firstChild.firstChild.style.display='none';
			</script>
		</c:if>
	</c:forEach>
	<hdiits:fieldGroup id="pastLtcDetails" titleCaptionId="HRMS.pastLtcDtl" bundle="${ltcCaption}" collapseOnLoad="true">
		<c:set var="num" value="1" />	
			<center>	
					<display:table list="${lstLtcDtl}" id="row" requestURI="" pagesize="10"  style="width:80%" offset="1" export="false">
						<display:setProperty name="paging.banner.placement" value="bottom"/>
									
						<display:column class="tablecell" titleKey="HRMS.srNo" headerClass="tablerow" style="text-align: center" sortable="false" >${num}</display:column>
						
						<display:column class="tablecell" titleKey="HRMS.constituentYr"  headerClass="tablerow" style="text-align: center" sortable="true" >${row.hrEssLtcblockMst.fromYear} - ${row.hrEssLtcblockMst.toYear}</display:column>
						
						<display:column  class="tablecell" titleKey="HRMS.takenRule" headerClass="tablerow" style="text-align: center;" sortable="false" >
							<c:if test="${row.ltcRuleType == 0}">
								<fmt:message key="HRMS.stateGovt" bundle="${ltcCaption}" />
							</c:if>	
							<c:if test="${row.ltcRuleType == 1}">
								<fmt:message key="HRMS.centralGovt" bundle="${ltcCaption}" />
							</c:if>	
						</display:column>
						<display:footer  media="html" ></display:footer>					
						<c:set var="num" value="${num+1}" />
					</display:table>
				</center>
</hdiits:fieldGroup>

	<br>	
	<c:if test="${ltcStatusFlag ne '0'}">
		<c:if test="${status == '0'}">
			<hdiits:tr align="center">
				
				<hdiits:validate controlNames="tesxt" locale='<%=(String)session.getAttribute("locale")%>' />
				
			</hdiits:tr>
		</c:if>
	</c:if>
	
	
	<hdiits:hidden name="wffileId_hidden" id="wffileId_hidden" />
	<hdiits:hidden name="fileId" id="fileId" default="${resultValue.reqId}" />


</hdiits:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
