

<%
try {
%>
<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" src="script/common/attachment.js"></script>

<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>

<fmt:setBundle basename="resources.ess.quarter.QuarterAllocationLables" var="QtrLablesForEmp" scope="request" />

<fmt:setBundle basename="resources.ess.quarter.QuarterAllocPwdLables" var="QtrLables" scope="request"/>
<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="tableBGColor" value="#FFFFFF"></c:set>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="FamilylstObj" value="${resValue.FamilylstObj}"></c:set>
<c:set var="hrEssQtrpwdTxn" value="${resValue.hrEssQtrpwdTxn}"></c:set>
<c:set var="qtrCustVo" value="${resValue.qtrCustVo}"></c:set>
<c:set var="EmpDetVO"  value="${resValue.EmpDet}"/>
<c:set var="preferredLocList" value="${resValue.preferredLocList}"></c:set>
<c:set var="OfficeName" value="${resValue.strOfficeName}"></c:set>
<c:set var="officeAddress" value="${resValue.strOfficeAddress}"></c:set>
<c:set var="strQuaterDesc" value="${resValue.strQuaterDesc}"></c:set> 
<hdiits:form name="QuarterAllocPwdFrm" validate="true" method="POST" encType="multipart/form-data">

	<div class="tabcontentstyle" style="height: 100%" id="tab1">		
	  
	<%@ include file="//WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>
	
	<hdiits:fieldGroup titleCaptionId="qtr.currentAccDtl" bundle="${QtrLables}"   mandatory="true" id="currentAccDtl">
	<table width="100%">
 
	<tr>
    <td width="20%"> 
       <hdiits:caption captionid="qtr.typeOfAccDtl" bundle="${QtrLables}" />
       </td>
 
       <td width="80%">
       <hdiits:radio name="typeRdo"  id="typeRdo" value="S" readonly="true"  captionid="qtr.self" bundle="${QtrLables}" onclick="selfAccoDtls()"/>
       <hdiits:radio name="typeRdo" id="typeRdo" value="R" readonly="true" captionid="qtr.onRent" bundle="${QtrLables}" onclick="rentAccDtls()"/>
       <hdiits:radio name="typeRdo" id="typeRdo"  value="G" readonly="true" captionid="qtr.govAcc" bundle="${QtrLables}" onclick="govAccDtls()"/>
      </td> 
      <br>
	</tr>
 
</table>
<table id="selfDtlTbl" style="display:none" width="100%">
 <tr>
   <td width="25%"><hdiits:caption captionid="qtr.sizeAcc" bundle="${QtrLables}" /></td>
   <td width="25%"><hdiits:text  name="sizeOfAccTxt" readonly="true" id="sizeOfAccTxt"  /></td>
   <td width="25%"><hdiits:caption captionid="qtr.owner" bundle="${QtrLables}" /></td>
   <td width="25%"><hdiits:select name="ownerHouseCmb" id="ownerHouseCmb" readonly="true" onchange="getOtherDtls()" sort="false">
     	<hdiits:option value="0" ><hdiits:caption captionid="qtr.select" bundle="${QtrLables}" />s</hdiits:option>
	     <c:forEach var="Familylst" items="${FamilylstObj}">
			<hdiits:option  value="${Familylst.familyId}"><c:out value="${Familylst.firstname} ${Familylst.lastname} (${Familylst.relaionNm})"/></hdiits:option>
 		 
		</c:forEach>
	   <hdiits:option  value="1" >Others</hdiits:option>
	   </hdiits:select></td>
   <br>
   </tr>
 
</table>
 <table id="otherDtlTbl" style="display:none" width="100%">
<tr>
 
			<td>
			 <hdiits:caption captionid="qtr.lastName" bundle="${QtrLables}"></hdiits:caption> 
			</td>
			<td>
				<hdiits:text name="lastNameTxt" id="lastNameTxt" readonly="true" maxlength="20"   mandatory="true"/>
			</td>
			<td>
				 <hdiits:caption captionid="qtr.firstName" bundle="${QtrLables}"></hdiits:caption> 
			</td>
			<td>
			   <hdiits:text name="firstNameTxt" id="firstNameTxt" readonly="true"  maxlength="20"   mandatory="true"/>
			</td>
			
			<td>
				 <hdiits:caption captionid="qtr.middleName" bundle="${QtrLables}"></hdiits:caption> 
			</td>
			<td>
				<hdiits:text name="middleNameTxt" id="middleNameTxt" readonly="true" maxlength="20" />
			</td>
		</tr>
	<tr>
		 <td><hdiits:caption captionid="qtr.otherRel" bundle="${QtrLables}"/> </td>
		 <td>
				<hdiits:text name="otherRelTxt" id="otherRelTxt" readonly="true" maxlength="20" />
			</td> 
		
		</tr>	
		<tr><td>&nbsp;</td></tr>   
 
</table>
<table id="onRentTbl" style="display:none" width="100%">
  <tr>
      <td width="25%"><hdiits:caption captionid="qtr.landlordNm" bundle="${QtrLables}" /></td>
      <td width="25%"><hdiits:text name="landLordNmTxt" id="landLordNmTxt" readonly="true"/></td>
      <td width="25%">&nbsp;</td>
      <td width="25%">&nbsp;</td>
           
  </tr>
  <tr>
   </table>
   <table id="addressTbl" style="display:none" width="100%">
    <tr>
     <td><hdiits:fmtMessage key="qtr.landLordAddress" bundle="${QtrLables}" var="landLordAddress" ></hdiits:fmtMessage>
	
				<jsp:include page="//WEB-INF/jsp/common/address.jsp">
				<jsp:param name="addrName" value="landlordAddress" />
				<jsp:param name="addressTitle" value="${landLordAddress}" />
				<jsp:param name="addrLookupName" value="Permanent Address" />
				 	</jsp:include>
					</td>
      
  </tr>
 
</table >
<table id="govAccTble" style="display:none" width="50%">
<tr>
  <td width="25%"><hdiits:caption captionid="qtr.accDtl" bundle="${QtrLables}" /></td>
  <td width="25%"><hdiits:textarea name="accDtlsTxtArea" readonly="true" id="accDtlsTxtArea"></hdiits:textarea></td>
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
           <td width="25%">
           <c:if  test="${hrEssQtrpwdTxn.isHra eq 89}">
		 	  <fmt:message key="qtr.yes" bundle="${QtrLables}"/>  
		</c:if>
		<c:if  test="${hrEssQtrpwdTxn.isHra  eq 78}">
		 	 <fmt:message key="qtr.no" bundle="${QtrLables}"/> 
		</c:if>
		
          
            </td>
     	
	    </tr>
	    
	     <tr>
           <td width="25%"><hdiits:caption captionid="qtr.reason"  bundle="${QtrLables}" /></td>
           <td width="25%"><hdiits:textarea captionid="qtr.reason"  bundle="${QtrLables}" readonly="true" caption="reason" id="reason" name="reason" rows="3" cols="30" validation="txt.isrequired" mandatory="true" maxlength="200" /></td>
           <td width="25%"><hdiits:caption captionid="qtr.preferredArea"  bundle="${QtrLables}" /></td>
           <td width="25%"><hdiits:textarea captionid="qtr.preferredArea"  bundle="${QtrLables}" readonly="true" caption="preferredArea" id="preferredArea" name="preferredArea" rows="3" cols="30" validation="txt.isrequired" mandatory="true" maxlength="200"></hdiits:textarea></td>
         </tr>
         <tr>
           <td width="25%"><hdiits:caption captionid="qtr.preferredLoc"  bundle="${QtrLables}" />
           </td>
           <td width="25%">
           <hdiits:select name="preferedLocation" id="preferedLocation" readonly="true" validation="sel.isrequired" captionid="qtr.preferredLoc"  bundle="${QtrLables}" sort="false">
           <hdiits:option value="0"><fmt:message key="qtr.select" bundle="${QtrLables}" /></hdiits:option>
				<c:forEach var="preferredLocList" items="${preferredLocList}">
					<hdiits:option value="${preferredLocList.lookupName}">${preferredLocList.lookupDesc}</hdiits:option>
				
				</c:forEach>
           </hdiits:select>
           </td>
           <td width="25%">&nbsp;</td>
           <td width="25%">&nbsp;</td>
         </tr>
	
	</table>
	<table id="eligibleQtrTable" width="100%">

<tr><td>
	<font color="red"><fmt:message key="qtr.UserShd" bundle="${QtrLables}"></fmt:message>
	<c:out value="${strQuaterDesc }"></c:out>
    <fmt:message key="qtr.typeQtr" bundle="${QtrLables}"></fmt:message>  </font>

</td>
</tr>
</table>

</hdiits:fieldGroup>
<hdiits:fieldGroup titleCaptionId="qtr.familyDtl" bundle="${QtrLables}"   mandatory="true" id="familyDtl" collapseOnLoad="true">


	<table id="fmlyDtl"  border="1" borderColor="black" align="center" width="100%" cellpadding="1" cellspacing="1" style="border-collapse: collapse;background-color: ${tableBGColor}">
	  <tr   align="center">
	 
	     <td  align="center" bgcolor="${tdBGColor}"><hdiits:caption captionid="qtr.srNo" bundle="${QtrLables}" /></td>
	     <td  align="center" bgcolor="${tdBGColor}" ><hdiits:caption captionid="qtr.Name" bundle="${QtrLables}" /></td>
	     <td  align="center" bgcolor="${tdBGColor}" ><hdiits:caption captionid="qtr.age" bundle="${QtrLables}" /></td>
	     <td  align="center" bgcolor="${tdBGColor}"><hdiits:caption captionid="qtr.relation" bundle="${QtrLables}" /></td>
	     <td  align="center" bgcolor="${tdBGColor}"><hdiits:caption captionid="qtr.resideWithEmp" bundle="${QtrLables}" /></td>
	     <td  align="center" bgcolor="${tdBGColor}"><hdiits:caption captionid="qtr.memEarning" bundle="${QtrLables}" /></td>
	  
	  </tr>
	<hdiits:hidden name="noOfRows" id="noOfRows"/>
	  <tr align="center"> 
	  <c:if test="${not empty FamilylstObj }">
	  <c:set var="x" value="1"></c:set>
	  <c:forEach var="FamilylstObjVO" items="${FamilylstObj}">
	 <tr>

	    <td align="center" width="5%">${x} </td>    
		<td align="center" width="25%">${FamilylstObjVO.firstname} ${FamilylstObjVO.middlename} ${FamilylstObjVO.lastname}</td>
	     <td align="center" width="10%">${FamilylstObjVO.age} 
		<td align="center" width="20%">${FamilylstObjVO.relaionNm}</td>
		<c:if  test="${FamilylstObjVO.residingWithEmp eq 89}">
		 	<td align="center" width="20%"><fmt:message key="qtr.yes" bundle="${QtrLables}"/> </td>
		</c:if>
		<c:if  test="${FamilylstObjVO.residingWithEmp  eq 78}">
		 	<td align="center" width="20%"><fmt:message key="qtr.no" bundle="${QtrLables}"/></td>
		</c:if>
		<c:if test="${FamilylstObjVO.memberEarning eq 88}">
							<td align="center" width="20%">-</td>
						</c:if>
		<c:if  test="${FamilylstObjVO.memberEarning eq 89}">
		 	<td align="center" width="20%"><fmt:message key="qtr.yes" bundle="${QtrLables}"/> </td>
		</c:if>
		<c:if  test="${FamilylstObjVO.memberEarning  eq 78}">
		 	<td align="center" width="20%"><fmt:message key="qtr.no" bundle="${QtrLables}"/></td>
		</c:if>
  </tr>
  	<c:set var="x" value="${x+1}"></c:set>
  	</c:forEach>			
  	</c:if>
 </tr> 
	</table>
	</hdiits:fieldGroup>
	

<hdiits:fieldGroup titleCaptionId="qtr.proof" bundle="${QtrLables}"   mandatory="true" id="proofAttchmnt" collapseOnLoad="true">	
	
	
	<TABLE  id="attachTbl" width="100%" align="center" >
  	
	 
	 
			<tr>
				<td>
					<!-- For attachment : Start-->	
						<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp" flush="true">
						<jsp:param name="attachmentName" value="QuarterPwdAttachment" />
						<jsp:param name="formName" value="QuarterAllocPwdFrm" />
						<jsp:param name="attachmentType" value="Document" />
						<jsp:param name="multiple" value="N" />    
                       <jsp:param name="mandatory" value="Y" />
                        </jsp:include>
					<!-- For attachment : End-->
				</td>
			</tr>
		 
		 

</TABLE>	
</hdiits:fieldGroup>
<hdiits:fieldGroup titleCaptionId="qtr.decalre" bundle="${QtrLables}"    id="declaration" mandatory="true" collapseOnLoad="true">	

<table width="100%">
		
		<tr>
			<jsp:include page="/WEB-INF/jsp/hrms/common/declaration.jsp" />
		</tr>
	</table>
	</hdiits:fieldGroup>
	
	</div>

	
	  
<script>
var typeOfPoss = '${hrEssQtrpwdTxn.typeOfPossesion}';
var typeOfPossesion=document.QuarterAllocPwdFrm.typeRdo;

if(typeOfPoss=='S')
{
	typeOfPossesion[0].checked='true';
    typeOfPossesion[1].disabled='true';
    typeOfPossesion[2].disabled='true';
    document.getElementById('sizeOfAccTxt').value='${hrEssQtrpwdTxn.sizeOfAcmdtn}';
    document.getElementById('selfDtlTbl').style.display='';
  
   	var cmbBox ='${hrEssQtrpwdTxn.hrEisFamilyDtl}';
   	var try1='${hrEssQtrpwdTxn.hrEisFamilyDtl.memberId}';
 
 	if(cmbBox!="")
 	{
   		 document.getElementById('ownerHouseCmb').value=try1;
    } 
    else  
    { 
  	   document.getElementById('otherDtlTbl').style.display='';
       document.getElementById('ownerHouseCmb').value=1;
       document.getElementById('lastNameTxt').value='${hrEssQtrpwdTxn.lastName}';
       document.getElementById('firstNameTxt').value='${hrEssQtrpwdTxn.firstName}';
       document.getElementById('middleNameTxt').value='${hrEssQtrpwdTxn.middleName}';
       document.getElementById('otherRelTxt').value='${hrEssQtrpwdTxn.otherRelation}';
      
    }
      
}
else if(typeOfPoss=='R')
{
    
	typeOfPossesion[1].checked='true';
	typeOfPossesion[0].disabled='true';
    typeOfPossesion[2].disabled='true';
    
    document.getElementById('onRentTbl').style.display='';
	document.getElementById('addressTbl').style.display='';  
	makeReadOnly("landlordAddress");
    
    document.getElementById('landLordNmTxt').value='${hrEssQtrpwdTxn.landlordName}';
   
 
 }

else if(typeOfPoss=='G')
{

	typeOfPossesion[2].checked='true';
	typeOfPossesion[0].disabled='true';
    typeOfPossesion[1].disabled='true';
    document.getElementById('accDtlsTxtArea').value='${hrEssQtrpwdTxn.acmdtnDetail}';     
    document.getElementById('govAccTble').style.display='';
} 


	document.getElementById('reason').value='${hrEssQtrpwdTxn.reason}';
	document.getElementById('preferredArea').value='${hrEssQtrpwdTxn.preferredArea}';
	document.getElementById('preferedLocation').value='${hrEssQtrpwdTxn.cmnLookupMst.lookupName}';
	
	document.getElementById('target_uploadQuarterPwdAttachment').style.display='none';
	document.getElementById('formTable1QuarterPwdAttachment').firstChild.firstChild.style.display='none';
	
	for(var i = 0 ;i<decValidationArr.length;i++){
  		
  			 document.getElementById(decValidationArr[i]).checked=true;
  			 document.getElementById(decValidationArr[i]).disabled=true;
				 
  		}
	
  
			
</script>

		<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
</hdiits:form>	

<%
}
catch(Exception e){
	e.printStackTrace();
}
%>	