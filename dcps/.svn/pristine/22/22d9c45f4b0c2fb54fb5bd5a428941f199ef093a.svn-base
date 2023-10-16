
<%
try {
%>
<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>


<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="script/common/attachment.js"></script>
<script type="text/javascript" 	src="<c:url value="/script/hrms/ess/quarter/QuarterAllocPwd.js"/>"></script>

<fmt:setBundle basename="resources.ess.quarter.QuarterAllocPwdLables" var="QtrLables" scope="request" />

<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="tableBGColor" value="#FFFFFF"></c:set>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="FamilylstObj" value="${resValue.FamilylstObj}"></c:set>
<c:set var="preferredLocList" value="${resValue.preferredLocList}"></c:set>
<c:set var="OfficeName" value="${resValue.OfficeName}"></c:set>
<c:set var="officeAddress" value="${resValue.officeAddress}"></c:set>
<script type="text/javascript">
	var QtrPWDAlertMsgArr=new Array();
		QtrPWDAlertMsgArr[0]='<fmt:message bundle="${QtrLables}" key="qtr.decMsg"/>';
		QtrPWDAlertMsgArr[1]='<fmt:message bundle="${QtrLables}" key="qtr.UserShd"/>';
		QtrPWDAlertMsgArr[2]='<fmt:message bundle="${QtrLables}" key="qtr.typeQtr"/>';
		QtrPWDAlertMsgArr[3]='<fmt:message bundle="${QtrLables}" key="qtr.attachmentAlert"/>';
	            
 function printRequest()
 {
  	window.print();
 }
  
</script>

<hdiits:form name="QuarterAllocPwdFrm" validate="true" method="POST" encType="multipart/form-data">


		<%@ include file="//WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>





	<hdiits:fieldGroup titleCaptionId="qtr.currentAccDtl" bundle="${QtrLables}"   mandatory="true"  id="currentAccDtl">
	<table width="100%">
		<tr>
			<td width="20%"><hdiits:caption captionid="qtr.typeOfAccDtl" bundle="${QtrLables}" /></td>
			<td width="80%">
			<hdiits:radio name="typeRdo" value="S" captionid="qtr.self" bundle="${QtrLables}" onclick="selfAccoDtls()" /> 
			<hdiits:radio name="typeRdo" value="R" captionid="qtr.onRent" bundle="${QtrLables}" onclick="rentAccDtls()"/> 
			<hdiits:radio name="typeRdo" value="G" captionid="qtr.govAcc" bundle="${QtrLables}" onclick="govAccDtls()"  />
			</td>
		</tr>
	</table>
 <br>
	<table id="selfDtlTbl" style="display:none" width="100%">
		<tr>
			<td width="25%"><hdiits:caption captionid="qtr.sizeAcc" bundle="${QtrLables}" /></td>
			<td width="25%"><hdiits:text name="sizeOfAccTxt" id="sizeOfAccTxt"   style="text-align: right" onkeypress="return checkNumberForOnlyOneDot(this.value)" /></td>
			<td width="25%"><hdiits:caption captionid="qtr.owner" bundle="${QtrLables}" /></td>
			<td width="25%"><hdiits:select name="ownerHouseCmb" id="ownerHouseCmb" sort="false" onchange="getOtherDtls()" captionid="qtr.owner" bundle="${QtrLables}" validation="sel.isrequired" mandatory="true">
				<hdiits:option value="0"><fmt:message key="qtr.select" bundle="${QtrLables}" /></hdiits:option>
				<c:forEach var="Familylst" items="${FamilylstObj}">
					<hdiits:option value="${Familylst.familyId}">
						<c:out value="${Familylst.firstname} ${Familylst.lastname}  (${Familylst.relaionNm}) " />
					</hdiits:option>
				</c:forEach>
				<hdiits:option value="1">Others</hdiits:option>
			</hdiits:select></td>
			
		</tr>

	</table>
	   
	<table id="otherDtlTbl" style="display:none" width="100%">
		<tr>
			<td><hdiits:caption captionid="qtr.lastName" bundle="${QtrLables}"></hdiits:caption></td>
			<td><hdiits:text name="lastNameTxt" id="lastNameTxt" maxlength="20" captionid="qtr.lastName" bundle="${QtrLables}" validation = "txt.isrequired" mandatory="true"/></td>
			<td><hdiits:caption captionid="qtr.firstName" bundle="${QtrLables}"></hdiits:caption></td>
			<td><hdiits:text name="firstNameTxt" id="firstNameTxt" maxlength="20" captionid="qtr.firstName" bundle="${QtrLables}" validation = "txt.isrequired" mandatory="true"/></td>
			<td><hdiits:caption captionid="qtr.middleName" 	bundle="${QtrLables}"></hdiits:caption></td>
			<td><hdiits:text name="middleNameTxt" id="middleNameTxt" /></td>
		</tr>
		<tr>
			<td><hdiits:caption captionid="qtr.otherRel"  bundle="${QtrLables}"/></td>
			<td><hdiits:text name="otherRelTxt" id="otherRelTxt" maxlength="19" captionid="qtr.otherRel"  bundle="${QtrLables}" validation = "txt.isrequired" mandatory="true"/></td>
			
		</tr>
		<tr></tr>
	</table>
	 
	<table id="onRentTbl" style="display:none" width="100%">
		<tr>
			<td width="25%"><hdiits:caption captionid="qtr.landlordNm"  bundle="${QtrLables}" /></td>
			<td width="25%"><hdiits:text name="lndlrdNmTxt" id="lndlrdNmTxt" captionid="qtr.landlordNm"  bundle="${QtrLables}" validation = "txt.isrequired" mandatory="true" /></td>
			<td width="25%">&nbsp;</td>
			<td width="25%">&nbsp;</td>
		</tr>
	</table>

	<table id="addressTbl" style="display:none" width="100%">
		<tr>
			<td><hdiits:fmtMessage key="qtr.landLordAddress" bundle="${QtrLables}" var="landLordAddress" ></hdiits:fmtMessage>
			
			<jsp:include page="//WEB-INF/jsp/common/address.jsp">
				<jsp:param name="addrName" value="landlordAddress" />
				<jsp:param name="addressTitle" value="${landLordAddress}"/>
				<jsp:param name="addrLookupName" value="Permanent Address" />
				<jsp:param name="mandatory" value="Y" />
				</jsp:include>
			</td>
		</tr>
	</table>


	<table id="govAccTble" style="display:none" width="50%">
		<tr>
			<td width="25%"><hdiits:caption captionid="qtr.accDtl" 	bundle="${QtrLables}" /></td>
			<td width="25%"><hdiits:textarea name="accDtlsTxtArea" id="accDtlsTxtArea"></hdiits:textarea></td>
		</tr>
	</table>
	</hdiits:fieldGroup>
<hdiits:fieldGroup titleCaptionId="qtr.officeDtl" bundle="${QtrLables}"   mandatory="true"  id="officeDtl" collapseOnLoad="true">
	<table id ="officeInfo" width="100%">
         <tr>
           <td width="25%"><hdiits:caption captionid="qtr.officeNm"  bundle="${QtrLables}" /></td>
           <td width="25%"><c:out value="${OfficeName}"></c:out></td>
           <td width="25%"><hdiits:caption captionid="qtr.officeAddress"  bundle="${QtrLables}" /></td>
           <td width="25%"><c:out value="${officeAddress}"></c:out></td>
         </tr>
	    <tr>
	      <td width="25%"><hdiits:caption captionid="qtr.specialPay" bundle="${QtrLables}"/> </td>
	       <td width="25%">--</td>
           <td width="25%"><hdiits:caption captionid="qtr.isHra"  bundle="${QtrLables}" /></td>
           <td width="25%"><input type="radio" name="rdoHRA" checked="checked" value="Y" id="rdoHRA" />
									<fmt:message key="qtr.yes" bundle="${QtrLables}" />
							<input type="radio" name="rdoHRA" value="N" id="rdoHRA" />
									<fmt:message key="qtr.no" bundle="${QtrLables}"/>
           
            </td>
     	
	    </tr>
	    
	     <tr>
           <td width="25%"><hdiits:caption captionid="qtr.reason"  bundle="${QtrLables}" /></td>
           <td width="25%"><hdiits:textarea captionid="qtr.reason"  bundle="${QtrLables}" caption="reason" id="reason" name="reason" rows="3" cols="30" validation="txt.isrequired" mandatory="true" maxlength="200" /></td>
           <td width="25%"><hdiits:caption captionid="qtr.preferredArea"  bundle="${QtrLables}" /></td>
           <td width="25%"><hdiits:textarea captionid="qtr.preferredArea"  bundle="${QtrLables}" caption="preferredArea" id="preferredArea" name="preferredArea" rows="3" cols="30" validation="txt.isrequired" mandatory="true" maxlength="200"></hdiits:textarea></td>
         </tr>
         <tr>
           <td width="25%"><hdiits:caption captionid="qtr.preferredLoc"  bundle="${QtrLables}" />
           </td>
           <td width="25%"><hdiits:select name="preferedLocation" id="preferedLocation" validation="sel.isrequired" captionid="qtr.preferredLoc"  bundle="${QtrLables}" mandatory="true" onchange="getQuarters()" >
           <hdiits:option value="0"><fmt:message key="qtr.select"
				bundle="${QtrLables}" /></hdiits:option>
				<c:forEach var="preferredLocList" items="${preferredLocList}">
					<hdiits:option value="${preferredLocList.lookupName}">
				${preferredLocList.lookupDesc}</hdiits:option>
				</c:forEach>
           </hdiits:select>
           </td>
           <td width="25%">&nbsp;</td>
           <td width="25%">&nbsp;</td>
         </tr>
         
	
	</table>
<table id="eligibleQtrTable" style="display:none" width="100%">


</table>

</hdiits:fieldGroup>
<hdiits:fieldGroup titleCaptionId="qtr.familyDtl" bundle="${QtrLables}"   mandatory="true" collapseOnLoad="true" id="familyDtl">
	<hdiits:hidden name="noOfRows" id="noOfRows" />
		
	<table id="fmlyDtl" border="1" borderColor="black" align="center" width="100%" cellpadding="1" cellspacing="1"
		style="border-collapse: collapse;background-color: ${tableBGColor}">
		<tr align="center">
			
			<td align="center" bgcolor="${tdBGColor}"><hdiits:caption captionid="qtr.srNo" bundle="${QtrLables}" /></td>
			<td align="center" bgcolor="${tdBGColor}"><hdiits:caption captionid="qtr.Name" bundle="${QtrLables}" /></td>
			<td align="center" bgcolor="${tdBGColor}"><hdiits:caption captionid="qtr.age" bundle="${QtrLables}" /></td>
			<td align="center" bgcolor="${tdBGColor}"><hdiits:caption captionid="qtr.relation" bundle="${QtrLables}" /></td>
			<td align="center" bgcolor="${tdBGColor}"><hdiits:caption captionid="qtr.resideWithEmp" bundle="${QtrLables}" /></td>
			<td align="center" bgcolor="${tdBGColor}"><hdiits:caption captionid="qtr.memEarning" bundle="${QtrLables}" /></td>

		</tr>
		
		<tr align="center">
			<c:if test="${not empty FamilylstObj }">
				<c:set var="x" value="1"></c:set>
				<c:forEach var="FamilylstObjVO" items="${FamilylstObj}">
					 
					<tr>
						<td align="center" width="5%">${x}</td>
						<td align="center" width="25%">${FamilylstObjVO.firstname}${FamilylstObjVO.middlename} ${FamilylstObjVO.lastname}</td>
						<td align="center" width="10%">${FamilylstObjVO.age}</td>
						<td align="center" width="20%">${FamilylstObjVO.relaionNm}</td>

						<td align="center" width="20%">
							<input type="radio" name="residingRdo${x}" checked="checked" value="Y" id="residingRdo${x}" />
									<fmt:message key="qtr.yes" bundle="${QtrLables}" />
							<input type="radio" name="residingRdo${x}" value="N" id="residingRdo${x}" />
									<fmt:message key="qtr.no" bundle="${QtrLables}"/>
						</td>						
						<c:if test="${FamilylstObjVO.memberEarning eq 88}">
							<td align="center" width="20%">-</td>
						</c:if>
						<c:if test="${FamilylstObjVO.memberEarning eq 89}">
							<td align="center" width="20%"><fmt:message key="qtr.yes" bundle="${QtrLables}" /></td>
						</c:if>
						<c:if test="${FamilylstObjVO.memberEarning  eq 78}">
							<td align="center" width="20%"><fmt:message key="qtr.no" bundle="${QtrLables}" /></td>
						</c:if>
						<hdiits:hidden default="${FamilylstObjVO.familyId}" name="memberid${x}" id="memberid${x}" />
					</tr>
	 
					<script>
  						document.getElementById('noOfRows').value=${x};
 					 </script>
					<c:set var="x" value="${x+1}"></c:set>
				</c:forEach>
			</c:if>
		</tr>
	</table>
</hdiits:fieldGroup>
 
<hdiits:fieldGroup titleCaptionId="qtr.proof" bundle="${QtrLables}"   mandatory="true" id="proof" collapseOnLoad="true"> 


	<table id="attachTbl" width="80%" align="center">
		
		<tr>
			<td><!-- For attachment : Start--> <jsp:include
				page="/WEB-INF/jsp/common/attachmentPage.jsp">
				<jsp:param name="attachmentName" value="QuarterPwdAttachment" />
				<jsp:param name="formName" value="QuarterAllocPwdFrm" />
				<jsp:param name="attachmentType" value="Document" />
				<jsp:param name="multiple" value="N" />
				<jsp:param name="mandatory" value="Y" />
			</jsp:include> <!-- For attachment : End--></td>
		</tr>

	</table>
	</hdiits:fieldGroup>
<hdiits:fieldGroup titleCaptionId="qtr.decalre" bundle="${QtrLables}"   mandatory="true" id="declare" collapseOnLoad="true"> 
	<table width="100%" id="declareTableId">
		<tr>
			<jsp:include page="/WEB-INF/jsp/hrms/common/declaration.jsp" />
		</tr>
	</table>
</hdiits:fieldGroup>
	<br>
	<table width="100%">
	
		<tr>
			<td align="center">

			       <hdiits:button name="submitBt" type="button" id="submitBt" value="submit" captionid="qtr.submitBtn" 	bundle="${QtrLables}" onclick="submitDtls()" /> 
				   <hdiits:button name="closeBtn" id="closeBtn" type="button" captionid="qtr.close"  bundle="${QtrLables}" onclick="closePage();" />
			       
			</td>
		</tr>
	</table>


	<script>
			var typeOfPossesion=document.QuarterAllocPwdFrm.typeRdo;
			typeOfPossesion[0].checked='true';
      		document.getElementById('selfDtlTbl').style.display='';
          
	</script>

	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />

</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
