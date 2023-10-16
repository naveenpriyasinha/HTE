
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="actionList" value="${resValue.actionList}" > </c:set>	
<c:set var="prosecSource" value="${resValue.prosecSource}" > </c:set>
<c:set var="objHrProsecutionDtl" value="${resValue.objHrProsecutionDtl}" > </c:set>

<hdiits:fieldGroup titleCaptionId="dept.generalDtls" bundle="${deptLables}" id="prosec1">

<table width=100% id="prosec2" style="display:none">
	<tr>
		<td width=40%><hdiits:caption captionid="dept.orderNoSanction" bundle="${deptLables}" /></td>
		<td width=20%><b> : </b></td>
		<td width=40%> <hdiits:number name="prosecOrderNo"  onblur="checkIsNumber(this);" maxlength="15"/>	</td>		
	</tr>

	<tr>
		<td> <hdiits:caption captionid="dept.causePros" bundle="${deptLables}" /></td>
		<td><b> : </b></td>
 		<td>	
 			<hdiits:select name="causeProsec" sort="false" mandatory="true">	
 				
			<option id="a" value="Select"><fmt:message key="dept.select"/></option>
				<c:forEach var="name" items="${prosecSource}">						
    			<c:choose>
						 <c:when test="${name.lookupName == objHrProsecutionDtl.cmnLookupMst.lookupName}">
						 <option  name="opt" value="<c:out value="${name.lookupName}"/>"  selected="selected"> 
		    			 <c:out value="${name.lookupDesc}"/></option>
		    			 </c:when>
		   			
			   			 <c:otherwise>
    		   			<option id="opt" name="opt" value="<c:out value="${name.lookupName}"/>"  >
    					<c:out value="${name.lookupDesc}"/></option>
    					</c:otherwise>
    			</c:choose>
				</c:forEach>
	
 			</hdiits:select>
 		</td>		
	</tr>

	<tr>
		<td><hdiits:caption captionid="dept.chargeSheetDt" bundle="${deptLables}" /></td>
		<td><b> : </b></td>
		<td> <hdiits:dateTime name="prosecChargeSheetDt" maxvalue="31/12/2099 00:00:00" captionid="dept.chargeSheetDt" bundle="${deptLables}"/>	</td>		
	</tr>

	<tr>
		<td> <hdiits:caption captionid="dept.orderDt" bundle="${deptLables}" /></td>
		<td><b> : </b></td>
		<td><hdiits:dateTime name="prosecOrderDt" maxvalue="31/12/2099 00:00:00" captionid="dept.orderDt" bundle="${deptLables}"/> 
			</td>		
	</tr>

	<tr>
		<td> <hdiits:caption captionid="dept.decisionSancTaken" bundle="${deptLables}" /></td>
		<td><b> : </b></td>
		<td>
			<hdiits:radio name="decisionProsec" value="y" bundle="${deptLables}" captionid="dept.y"/>
			<hdiits:radio name="decisionProsec" value="n" bundle="${deptLables}" captionid="dept.n"/>
		</td>		
	</tr>

	<tr>
		<td> <hdiits:caption captionid="dept.pendCourt" bundle="${deptLables}" /></td>
		<td><b> : </b></td>
		<td>	
			<hdiits:radio name="prosecCasePend" value="y" bundle="${deptLables}" captionid="dept.y"/>
			<hdiits:radio name="prosecCasePend" value="n" bundle="${deptLables}" captionid="dept.n"/>
			
		</td>		
	</tr>

	<tr>
		<td> <hdiits:caption captionid="dept.positionCase" bundle="${deptLables}" />	</td>
		<td><b> : </b></td>
		<td>	
			<hdiits:textarea name="prosecLatestPosition" onblur="restrictSplChar(this);" maxlength="100" rows="3" cols="25"> 
			</hdiits:textarea>
		</td>		
	</tr>

</table>

</hdiits:fieldGroup>

<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
</script>
		

<script>
hideProsec();
makeProsecDtReadOnly();
setDefaultProsec();

function setDefaultProsec()
{
	document.getElementById("prosecOrderNo").value="${objHrProsecutionDtl.orderNo}";
	document.getElementById("prosecLatestPosition").value="${objHrProsecutionDtl.latestPosition}";

	<fmt:formatDate pattern="dd/MM/yyyy" value="${objHrProsecutionDtl.orderDt}" var="orderDt"/>
	<fmt:formatDate pattern="dd/MM/yyyy" value="${objHrProsecutionDtl.chargeSheetDt}" var="chargeSheetDt"/>


	document.getElementById("prosecOrderDt").value="${orderDt}";
	document.getElementById("prosecChargeSheetDt").value="${chargeSheetDt}";

	if("${objHrProsecutionDtl.isCasePending}"=='y')
	{
	document.inquiryCaseTracking.prosecCasePend[0].checked=true;
	}
	
	else if("${objHrProsecutionDtl.isCasePending}"=='n')
	{
	document.inquiryCaseTracking.prosecCasePend[1].checked=true;
	}
	
	if("${objHrProsecutionDtl.isDecisionSanc}"=='y')
	{
	document.inquiryCaseTracking.decisionProsec[0].checked=true;
	}
	
	else if("${objHrProsecutionDtl.isDecisionSanc}"=='n')
	{
	document.inquiryCaseTracking.decisionProsec[1].checked=true;
	}

}

</script>
