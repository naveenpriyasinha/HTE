

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="advDtlObj" value="${resValue.hrRtaReqDtl}"></c:set>


<c:if test="${not empty advDtlObj}">

<table name="travelReimbDtl" id="travelReimbDtl" class="tabtable" border="0" width="100%">

<tr bgcolor="#386CB7">
		<td class="fieldLabel" colspan="5" align="center">
			<font color="#ffffff">
				<strong><u><fmt:message key="HRMS.rtaAdvDtl"/></u></strong>
			</font>
		</td>
	</tr>
	<tr>
 		<td>
 			<b><fmt:message key="HRMS.totalpayablerta"></fmt:message>:</b>
 		</td>
 		
    	<td>
      		<hdiits:text captionid ="HRMS.totalpayablerta" style="background-color: lightblue;" tabindex="1" readonly="true" name="totalPayableAmt" id="totalPayableAmt" default="${advDtlObj.totalPayableAmt}"/>
   		</td>  
   	</tr>
   	<tr>
   		<td>
   			<b><fmt:message key="HRMS.remarks"></fmt:message></b>
   		</td>
		
		<td>
			
			<hdiits:hidden name="parentId" id="parentId" default="${advDtlObj.rtaId}" />
			<hdiits:hidden name="status" id="status" default="${advDtlObj.status}"/>
			<hdiits:textarea mandatory="false" rows="2" cols="17" default="${advDtlObj.remarks}" readonly="true" name="remarks1" id="remarks1" tabindex="2" caption="remarks"  />
		</td>
		
		
	
	</tr>
</table>
</c:if>