
<%@ include file="/WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="advDtlObj" value="${resValue.hrRtaReqDtl}"></c:set>

<c:if test="${not empty reimbReqList}">
<hdiits:fieldGroup bundle="${commonLables}"  expandable="false" collapseOnLoad="false" titleCaptionId="HRMS.Notification" id="rtaReimbDetails">
<table name="reimbReqDtl" id="reimbReqDtl" class="tabtable" border="0" width="100%">
	<tr>
 		<td align="left">
			<b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.Notification"/></b>
		</td>
		<td> 
			<hdiits:select name="reimbReq" size="1" id="reimbReq" caption="reimbReq" mandatory="true" onchange="getReimbReqDtl();">
					<hdiits:option value="0"><fmt:message bundle="${commonLables}"  key="HRMS.select" /></hdiits:option>
			 			<c:forEach var="reimbReqList" items="${reimbReqList}" varStatus="x">
		 					<hdiits:option value="${reimbReqList.rtaId}">
									${corrNoList[x.index]}
							</hdiits:option>
						</c:forEach>
			</hdiits:select>
		</td>
		<td>
			<b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.remarks"/></b>
		</td>
		<td>
			<hdiits:textarea mandatory="false" rows="2" cols="17" readonly="true" name="reimbRemarks" id="reimbRemarks" caption="reimbRemarks"  />
		</td>
	</tr>
</table>
</hdiits:fieldGroup>
<script>
	document.getElementById('reimbReqId').value=-1;
</script>
<br/>
</c:if>

<c:if test="${not empty advDtlObj}">
<hdiits:fieldGroup bundle="${commonLables}"  expandable="true" collapseOnLoad="true" titleCaptionId="HRMS.rtaAdvDtl" id="rtaAdvDetails">
<table name="travelReimbDtl" id="travelReimbDtl" class="tabtable" border="0" width="100%">
	<tr>
 		<td>
 			<b><hdiits:caption bundle="${commonLables}" captionid="HRMS.totalpayablerta" />:</b>
 		</td>
 		
    	<td>
      		<hdiits:text captionid ="HRMS.totalpayablerta" style="background-color: lightblue;" tabindex="1" readonly="true" name="totalPayableAmt" id="totalPayableAmt" default="${advDtlObj.totalPayableAmt}"/>
   		</td>  
   		
   		<td>
			<hdiits:caption bundle="${commonLables}" captionid="HRMS.advReq" />
		</td>
		<td>
				<c:if test="${advDtlObj.status eq 1}">
					<hdiits:caption bundle="${commonLables}" captionid="HRMS.approved" />
				</c:if>
				<c:if test="${advDtlObj.status ne 1}">
					<hdiits:caption bundle="${commonLables}" captionid="HRMS.pending" />
				</c:if>
		</td>
   		
   	</tr>
   	<tr>
   		<td>
   			<hdiits:caption bundle="${commonLables}" captionid="HRMS.sanctionAmt" />
   		</td>
   		
   		<td>
   			<hdiits:number name="sanctionedAmtAdv" tabindex="28" id="sanctionedAmtAdv" readonly="true" 
			default="${advDtlObj.sanctionedAmt}" />
   		</td>
   	
   		<td>
   			<b><hdiits:caption bundle="${commonLables}" captionid="HRMS.remarks" /></b>
   		</td>
		
		<td>
			<hdiits:hidden name="parentId" id="parentId" default="${advDtlObj.rtaId}" />
			<hdiits:hidden name="status" id="status" default="${advDtlObj.status}"/>
			<hdiits:textarea mandatory="false" rows="2" cols="17" default="${advDtlObj.remarks}" readonly="true" name="remarks1" id="remarks1" tabindex="2" caption="remarks"  />
		</td>
		
	</tr>
</table>
</hdiits:fieldGroup>
</c:if>