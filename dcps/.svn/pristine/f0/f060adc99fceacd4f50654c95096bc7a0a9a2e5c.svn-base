
<c:set var="typeOfPunishment" value="${resValue.typeOfPunishment}" > </c:set>
<c:set var="noOfEmp" value="${resValue.noOfEmp}" > </c:set>
<c:set var="adviceVC" value="${resValue.adviceVC}" > </c:set>

<c:set var="objHrPrelimEnqDtls" value="${resValue.objHrPrelimEnqDtls}" > </c:set>
<c:set var="objHrPrelimVcDtls" value="${resValue.objHrPrelimVcDtls}" > </c:set>
<c:set var="listDelinDtls" value="${resValue.listDelinDtls}" > </c:set> 
<c:set var="noOfEmpSelected" value="${resValue.noOfEmpSelected}" > </c:set>
<c:set var="objHrPrelimEnqCmnPerson" value="${resValue.objHrPrelimEnqCmnPerson}" > </c:set>
<c:set var="objHrPrelimEnqCmnContact" value="${resValue.objHrPrelimEnqCmnContact}" > </c:set>

<table width=100% id="enqTable">

<tr><td colspan="4">
	<hdiits:fieldGroup titleCaptionId="dept.inqOffDtls" bundle="${deptLables}">	</hdiits:fieldGroup>
	</td></tr>
	
<tr>
		<td width=25%>
		<hdiits:caption captionid="dept.enqOffInSameDept" bundle="${deptLables}"/>
		</td>

		<td width=25%>
		<hdiits:radio name="radioenqOffPrelim" value="y" bundle="${deptLables}" captionid="dept.y" onclick="enqOffDispPrelim();"/>
		<hdiits:radio name="radioenqOffPrelim" value="n" bundle="${deptLables}" captionid="dept.n" onclick="enqOffDispPrelim();"/>
		</td>
		
		<td width=25%>
		</td>
		
		<td width=25%>
		</td>
	</tr>
	
	<tr id="name1InputPrelim" style="display:none">
		<td width=25%>
		</td>
		<td width=25%>
		<hdiits:caption captionid="dept.lastName" bundle="${deptLables}"/>
		</td>
		<td width=25%>
		<hdiits:caption captionid="dept.firstName" bundle="${deptLables}"/>
		</td>
		<td width=25%>
		<hdiits:caption captionid="dept.midName" bundle="${deptLables}"/>
		</td>
		
	</tr>
	

	
	<tr id="name2InputPrelim" style="display:none">
		<td width=25%>
		<hdiits:caption captionid="dept.Name" bundle="${deptLables}"/>
		</td>
	<td width=25%>
		<hdiits:text name="InqlastNamePrelim" default="${objHrPrelimEnqCmnPerson.lastName}" mandatory="true" onblur="restrictSplChar(this);onlyChars(this);"  maxlength="15"/>
		</td>
		<td width=25%>
		<hdiits:text name="InqfirstNamePrelim" default="${objHrPrelimEnqCmnPerson.firstName}" mandatory="true" onblur="restrictSplChar(this);onlyChars(this);"  maxlength="15"/>
		</td>
		<td width=25%>
		<hdiits:text name="InqmiddleNamePrelim" default="${objHrPrelimEnqCmnPerson.middleName}" onblur="restrictSplChar(this);onlyChars(this);"  maxlength="15"/>
		</td>
		
	</tr>
	
	<tr id="phone1InputPrelim" style="display:none">
	<td width=25%>
		
		</td>

		<td width=25%>
		<hdiits:caption captionid="dept.office" bundle="${deptLables}"/>
		</td>
		<td width=25%>
		<hdiits:caption captionid="dept.residence" bundle="${deptLables}"/>
		</td>
		<td width=25%>
		<hdiits:caption captionid="dept.mobile" bundle="${deptLables}"/>
		</td>
	</tr>
	
	<tr id="phone2InputPrelim" style="display:none">
	<td width=25%>
		<hdiits:caption captionid="dept.phone" bundle="${deptLables}"/>
		</td>

		<td width=25%>
		<hdiits:number name="enqOffPhonePrelim" default="${objHrPrelimEnqCmnContact.officePhone}" mandatory="true" onblur="checkIsNumber(this);checkPhoneLength(this);" maxlength="15" size="20"/>
		</td>
		<td width=25%>
		<hdiits:number name="enqOffPhoneResiPrelim" default="${objHrPrelimEnqCmnContact.residencePhone}" onblur="checkIsNumber(this);checkPhoneLength(this);" maxlength="15" size="20"/>
		</td>
		<td width=25%>
		<hdiits:number name="enqOffPhoneMobilePrelim" default="${objHrPrelimEnqCmnContact.mobile}" onblur="checkIsNumber(this);checkMobileLength(this);" maxlength="10" size="20"/>
		</td>
	</tr>

	<tr id="fax1InputPrelim" style="display:none">
		<td width=25%>
			<hdiits:caption captionid="dept.fax" bundle="${deptLables}"/>
		</td>

		<td width=25%>
		<hdiits:number name="InqfaxPrelim" default="${objHrPrelimEnqCmnContact.fax}" onblur="checkIsNumber(this);checkPhoneLength(this);" maxlength="15" size="20"/>
		</td>
	</tr>
	
	<tr id="userIdInputPrelim" style="display:none">
		<td width=25%>
		<hdiits:caption captionid="dept.userIdEnqOff" bundle="${deptLables}"/>
		</td>
	
		<td width=25%>
		<hdiits:number name="userIdEnqOffPrelim" id="user_id6" mandatory="true" default="${objHrPrelimEnqDtls.orgUserMstByEnqUserId.userId}" onblur="checkIsNumber(this)" readonly="true"/>
		<hdiits:image id="Search6" source="images/search_icon.gif" onclick="SearchEmp(this);"> </hdiits:image>
		</td>
		
		<td width=25%></td>
		<td width=25%></td>
				
	</tr>
	
		<tr>
			<td width=25%>
			<hdiits:caption captionid="dept.inqEntrustDt" bundle="${deptLables}"/>
			 </td>
	
			<td width=25%>
				<hdiits:dateTime name="inqEntrustedDt" maxvalue="31/12/2099 00:00:00" captionid="dept.inqEntrustDt" bundle="${deptLables}"/>
			</td>
	
			<td width=25%> 
			<hdiits:caption captionid="dept.inqRepSubDt" bundle="${deptLables}"/>
			</td>
	
			<td width=25%>
				<hdiits:dateTime name="inqReportSubDt" maxvalue="31/12/2099 00:00:00" captionid="dept.inqRepSubDt" bundle="${deptLables}"/>
			 </td>
		</tr>
	
	
		<tr>
			<td width=25%> 
			<hdiits:caption captionid="dept.decisionReport" bundle="${deptLables}"/> 
			</td>
			
			<td width=25%> 
				<hdiits:radio name="decisionOnReport" value="y" bundle="${deptLables}" captionid="dept.y" onclick="checkDecision(this);"/>
				<hdiits:radio name="decisionOnReport" value="n" bundle="${deptLables}" captionid="dept.n" onclick="checkDecision(this);"/>
			</td>
			
			
			
		</tr>
	
	<tr><td colspan="4">
	<hdiits:fieldGroup titleCaptionId="dept.inqRepDtls" bundle="${deptLables}" id="reportDetTab">	</hdiits:fieldGroup>
	</td></tr>
	
	<tr id="decisionDetails" style="display:none">
			<td width=25%><hdiits:caption captionid="dept.typePunish" bundle="${deptLables}"/> </td>
			<td width=25%> 
					
				
				
			<hdiits:select name="typePunish" sort="false" mandatory="true">	
 				
			<option id="a" value="Select"><fmt:message key="dept.select"/></option>
				<c:forEach var="name" items="${typeOfPunishment}">						
    			<c:choose>
						 <c:when test="${name.lookupName == objHrPrelimEnqDtls.punishmentType.lookupName}">
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

			<td width=25%>	
				<hdiits:caption captionid="dept.dateDecisionMade" bundle="${deptLables}"/>
			</td>
			<td width=25%>
			<hdiits:dateTime name="decisionMadeDt" maxvalue="31/12/2099 00:00:00" captionid="dept.dateDecisionMade" bundle="${deptLables}"/>
			</td>
		</tr>
		
		<tr id="decisionDetails2" style="display:none">
			<td width=25%> 
			  <hdiits:caption captionid="dept.decisionReportDtls" bundle="${deptLables}"/>
			</td>
			
			<td width=25%> 
				<hdiits:textarea name="dtlsDecisionReport" default="${objHrPrelimEnqDtls.decisionDetail}" onblur="restrictSplChar(this);" maxlength="100"  rows="3" cols="25">	</hdiits:textarea>
			</td>
			
			<td width=25%> </td>
			
			<td width=25%> </td>
		</tr>
			
		<tr>
			<td width=25%> 
			<hdiits:caption captionid="dept.refVCReq" bundle="${deptLables}"/> 
			</td>
			
			<td width=25%> 
				<hdiits:radio name="refToVC" id="refToVC" value="y" onclick="checkStatus(this)" bundle="${deptLables}" captionid="dept.y"/>
				<hdiits:radio name="refToVC" id="refToVC" value="n" onclick="checkStatus(this)" bundle="${deptLables}" captionid="dept.n"/>
			</td>
			
			<td width="25%"></td>
			<td width="25%"></td>
		</tr>
		
	</table>
	
	<script type="text/javascript">
			//var status=document.getElementById(refToVC);		
			//alert(status);			
			
			function checkStatus(form)
			{	
				var status=form.value;
				if(status=="y" && "${enqDtl[0].prelimStatus}"=='I'){				
				document.getElementById('VCTable').style.display='';				
				}else{				
				document.getElementById('VCTable').style.display='none';				
				} 
			}		 
	</script>
	
	<hdiits:fieldGroup titleCaptionId="dept.vcdetails" bundle="${deptLables}" id="VCTable">	
	
	<table width=100%>
	<tr>
			<td>
				<table width="100%">
					<tr>		
						<td width=25%> 
						  <hdiits:caption captionid="dept.refVCDt" bundle="${deptLables}"/>
						</td>
						
						<td width=25%> 
							<hdiits:dateTime name="referenceVCDt" maxvalue="31/12/2099 00:00:00" captionid="dept.refVCDt" bundle="${deptLables}"/>
						</td>
					
						<td width=25%> 
						 	<hdiits:caption captionid="dept.vcfolderNo" bundle="${deptLables}"/>
						</td>
						
						<td width=25%> 
							<hdiits:number name="folderVCNo"  caption="V.C Folder No." default="${objHrPrelimVcDtls.folderNo}" onblur="checkIsNumber(this)"/>
						</td>		
					</tr>
					
					<tr>
						<td width=25%> 
						 	<hdiits:caption captionid="dept.queryVCDt" bundle="${deptLables}"/>
						</td>
						
						<td width=25%> 
							<hdiits:dateTime name="queryVCDt" maxvalue="31/12/2099 00:00:00" captionid="dept.queryVCDt" bundle="${deptLables}"/>
						</td>
						
						<td width=25%> 
							<hdiits:caption captionid="dept.complianceVCDt" bundle="${deptLables}"/>
						</td>
						
						<td width=25%> 
							<hdiits:dateTime name="compVCDt" maxvalue="31/12/2099 00:00:00" captionid="dept.complianceVCDt" bundle="${deptLables}"/>
						</td>			
					</tr>
					
					<tr>
						<td width=25%> 
						 	<hdiits:caption captionid="dept.adviceVCDt" bundle="${deptLables}"/>
						</td>
						
						<td width=25%> 
							<hdiits:dateTime name="adviceVCDt" maxvalue="31/12/2099 00:00:00" captionid="dept.adviceVCDt" bundle="${deptLables}"/>
						</td>		
					</tr>
					
					<tr> <td colspan="4" >
					<hdiits:fieldGroup titleCaptionId="dept.adviceVC" bundle="${deptLables}" >	</hdiits:fieldGroup>
					</tr>
					<tr>		
						<td width=25%> 
						 	<hdiits:caption captionid="dept.adviceVC" bundle="${deptLables}"/>
						</td>
						
						<td width=25%> 
						<hdiits:select name="selectVC" id="selectVC" sort="false">
							<option value="Select" ><fmt:message key="dept.select"/></option>
								<c:forEach var="adviceVC" items="${adviceVC}">	
				
								<c:choose>
								 <c:when test="${adviceVC.lookupName == objHrPrelimVcDtls.adviceVc.lookupName}">
								 <option  name="opt" value="<c:out value="${adviceVC.lookupName}"/>"  selected="selected"> 
    							 <c:out value="${adviceVC.lookupDesc}"/></option>
    							 </c:when>
	   			
	   							 <c:otherwise>
	   							 <option  name="opt" value="<c:out value="${adviceVC.lookupName}"/>"> 
    							 <c:out value="${adviceVC.lookupDesc}"/></option>	
	   							 </c:otherwise>
	   							</c:choose>
	      											
								</c:forEach>
	     				</hdiits:select>
						</td>
					
						<td width=25%> 
						 	<hdiits:caption captionid="dept.chargeSheetIssued" bundle="${deptLables}"/>
						</td>
						
						<td width=25%> 
							<hdiits:radio name="chgIssued" value="y"  bundle="${deptLables}" captionid="dept.y" />
							<hdiits:radio name="chgIssued" value="n"  bundle="${deptLables}" captionid="dept.n" />
						</td>		
					</tr>
					
					<tr>
						<td width="25%">
							<hdiits:caption captionid="dept.positionCase" bundle="${deptLables}"/>
						</td>
						<td width="25%">
							<hdiits:textarea name="Position" default="${objHrPrelimVcDtls.latestPosition}" onblur="restrictSplChar(this);"   maxlength="100"></hdiits:textarea>
						</td>
					</tr>
														
					
				</table>				
			</td>
		</tr>

	</table>
	</hdiits:fieldGroup>
	
	
	<hdiits:fieldGroup titleCaptionId="dept.delinqDtls" bundle="${deptLables}" id="noOfEmpTab">	
		
	<table width="100%" id="delinqDtlTable">			
		<tr>
			<td width=25%>
				<hdiits:caption captionid="dept.totNoEmp" bundle="${deptLables}"/>			
			</td>
			
			<td width="25%">
			<hdiits:select name="noOfEmpSel" id="noOfEmpSel" onchange="delinqDtls();" sort="false">
			<option value="Select" ><fmt:message key="dept.select"/></option>
				<c:forEach var="name" items="${noOfEmp}">	
				
				<c:choose>
				 <c:when test="${name.lookupName == noOfEmpSel}">
				 <option  name="opt" value="<c:out value="${name.lookupName}"/>"  selected="selected"> 
    			 <c:out value="${name.lookupDesc}"/></option>
    			
    			 </c:when>
	   			
	   			 <c:otherwise>
	   						
	   			 <option  name="opt" value="<c:out value="${name.lookupName}"/>"> 
    			 <c:out value="${name.lookupDesc}"/></option>	
	   			 </c:otherwise>
	   			</c:choose>
	      								
				</c:forEach>
	     	</hdiits:select>
			</td>
					
			 		
	 		
		<td width=25%></td>
			<td width=25%></td>		
		</tr>

		<tr style="display:none" id="delinq1">
			<td width=25%><hdiits:caption captionid="dept.nameDelinq" bundle="${deptLables}"/></td>
			<td width=25%>
				<hdiits:text name="delinqName1"  maxlength="15" onblur="restrictSplChar(this);onlyChars(this);" />	</td>
			<td width=25%><hdiits:caption captionid="dept.class" bundle="${deptLables}"/></td>
			<td width=25%>
								
				<select name="class1" id="class1">
				<option value="Select" ><fmt:message key="dept.select"/></option>
					<c:forEach var="name" items="${noOfEmp}">	
					
					<c:choose>
						 <c:when test="${name.lookupName == listDelinDtls[0].classDelinq.lookupName}">
						 <option  name="opt" value="<c:out value="${name.lookupName}"/>"  selected="selected"> 
		    			 <c:out value="${name.lookupDesc}"/></option>
		    			 </c:when>
		   			
			   			 <c:otherwise>
			   			 <option  name="opt" value="<c:out value="${name.lookupName}"/>"> 
		    			 <c:out value="${name.lookupDesc}"/></option>	
			   			 </c:otherwise>
		   			</c:choose>
		      								
					</c:forEach>
	     		</select>
			</td>
		</tr>
	
		<tr style="display:none" id="delinq2">
			<td width=25%><hdiits:caption captionid="dept.nameDelinq" bundle="${deptLables}"/></td>
			<td width=25%><hdiits:text name="delinqName2" onblur="restrictSplChar(this);onlyChars(this);"  maxlength="15"/></td>
			<td width=25%><hdiits:caption captionid="dept.class" bundle="${deptLables}"/></td>
			<td width=25%>
				<select name="class2" id="class2">
				<option value="Select" ><fmt:message key="dept.select"/></option>
					<c:forEach var="name" items="${noOfEmp}">	
					
					<c:choose>
						 <c:when test="${name.lookupName == listDelinDtls[1].classDelinq.lookupName}">
						 <option  name="opt" value="<c:out value="${name.lookupName}"/>"  selected="selected"> 
		    			 <c:out value="${name.lookupDesc}"/></option>
		    			 </c:when>
		   			
			   			 <c:otherwise>
			   			 <option  name="opt" value="<c:out value="${name.lookupName}"/>"> 
		    			 <c:out value="${name.lookupDesc}"/></option>	
			   			 </c:otherwise>
		   			</c:choose>
		      								
					</c:forEach>
	     		</select>
			</td>
		</tr>
		
		<tr style="display:none" id="delinq3">
			<td width=25%><hdiits:caption captionid="dept.nameDelinq" bundle="${deptLables}"/></td>
			<td width=25%><hdiits:text name="delinqName3" onblur="restrictSplChar(this);onlyChars(this);"  maxlength="15"/></td>
			<td width=25%><hdiits:caption captionid="dept.class" bundle="${deptLables}"/></td>
			<td width=25%>
				<select name="class3" id="class3">
				<option value="Select" ><fmt:message key="dept.select"/></option>
					<c:forEach var="name" items="${noOfEmp}">	
					
					<c:choose>
						 <c:when test="${name.lookupName == listDelinDtls[2].classDelinq.lookupName}">
						 <option  name="opt" value="<c:out value="${name.lookupName}"/>"  selected="selected"> 
		    			 <c:out value="${name.lookupDesc}"/></option>
		    			 </c:when>
		   			
			   			 <c:otherwise>
			   			 <option  name="opt" value="<c:out value="${name.lookupName}"/>"> 
		    			 <c:out value="${name.lookupDesc}"/></option>	
			   			 </c:otherwise>
		   			</c:choose>
		      								
					</c:forEach>
	     		</select>
			</td>
		</tr>
		
		<tr style="display:none" id="delinq4">
			<td width=25%><hdiits:caption captionid="dept.nameDelinq" bundle="${deptLables}"/></td>
			<td width=25%><hdiits:text name="delinqName4" onblur="restrictSplChar(this);onlyChars(this);"  maxlength="15" /></td>
			<td width=25%><hdiits:caption captionid="dept.class" bundle="${deptLables}"/></td>
			<td width=25%>
				<select name="class4" id="class4">
				<option value="Select" ><fmt:message key="dept.select"/></option>
					<c:forEach var="name" items="${noOfEmp}">	
					
					<c:choose>
						 <c:when test="${name.lookupName == listDelinDtls[3].classDelinq.lookupName}">
						 <option  name="opt" value="<c:out value="${name.lookupName}"/>"  selected="selected"> 
		    			 <c:out value="${name.lookupDesc}"/></option>
		    			 </c:when>
		   			
			   			 <c:otherwise>
			   			 <option  name="opt" value="<c:out value="${name.lookupName}"/>"> 
		    			 <c:out value="${name.lookupDesc}"/></option>	
			   			 </c:otherwise>
		   			</c:choose>
		      								
					</c:forEach>
	     		</select>
			</td>
		</tr>
		<hdiits:hidden name="empSelected" default="${noOfEmpSelected}"></hdiits:hidden>
</table>
	
</hdiits:fieldGroup>

	
<script>
document.getElementById("VCTable").style.display='none';
document.getElementById("reportDetTab").style.display='none';

hideAllPrelim();
prelimDateReadonly();
defaultDisplay();

function defaultDisplay()
{
	<fmt:formatDate pattern="dd/MM/yyyy" value="${objHrPrelimVcDtls.queryDt}" var="queryDt"/>
	<fmt:formatDate pattern="dd/MM/yyyy" value="${objHrPrelimVcDtls.complianceDt}" var="complianceDt"/>
	<fmt:formatDate pattern="dd/MM/yyyy" value="${objHrPrelimVcDtls.advReceivedDt}" var="advReceivedDt"/>
	<fmt:formatDate pattern="dd/MM/yyyy" value="${objHrPrelimVcDtls.referenceDt}" var="referenceDt"/>
		
	document.inquiryCaseTracking.queryVCDt.value="${queryDt}";
	document.inquiryCaseTracking.compVCDt.value="${complianceDt}";
	document.inquiryCaseTracking.adviceVCDt.value="${advReceivedDt}";
	document.inquiryCaseTracking.referenceVCDt.value="${referenceDt}";
	
	<fmt:formatDate pattern="dd/MM/yyyy" value="${objHrPrelimEnqDtls.enqEntrustDt}" var="enqEntrustDt"/>
	<fmt:formatDate pattern="dd/MM/yyyy" value="${objHrPrelimEnqDtls.enqReportSubDt}" var="enqReportSubDt"/>
	<fmt:formatDate pattern="dd/MM/yyyy" value="${objHrPrelimEnqDtls.decisionDt}" var="decisionDt"/>
	
	document.inquiryCaseTracking.inqEntrustedDt.value="${enqEntrustDt}";
	document.inquiryCaseTracking.inqReportSubDt.value="${enqReportSubDt}";
	document.inquiryCaseTracking.decisionMadeDt.value="${decisionDt}";
	
	if("${objHrPrelimEnqDtls.isDecisionReport}" =='y')
		{
		document.inquiryCaseTracking.decisionOnReport[0].checked=true;
		document.getElementById('decisionDetails').style.display='';	
		document.getElementById('decisionDetails2').style.display='';	
		document.getElementById('reportDetTab').style.display='';
		}

	else if("${objHrPrelimEnqDtls.isDecisionReport}"=='n')
		{
		document.inquiryCaseTracking.decisionOnReport[1].checked=true;
		}
		
	if("${objHrPrelimEnqDtls.vcId}" !='')
		{
		document.inquiryCaseTracking.refToVC[0].checked=true;
		}
	
	if("${objHrPrelimEnqDtls.vcId}" =='')
		{
		document.inquiryCaseTracking.refToVC[1].checked=true;
		document.getElementById('VCTable').style.display='none';	
		}
	
	
	if("${objHrPrelimVcDtls.isChargesheetIssued}" =='y')
		{
		document.inquiryCaseTracking.chgIssued[0].checked=true;
		}

	else if("${objHrPrelimVcDtls.isChargesheetIssued}"=='n')
		{
		document.inquiryCaseTracking.chgIssued[1].checked=true;
		}				
	
	
	document.inquiryCaseTracking.delinqName1.value="${listDelinDtls[0].nameDelinq}";
	document.inquiryCaseTracking.delinqName2.value="${listDelinDtls[1].nameDelinq}";
	document.inquiryCaseTracking.delinqName3.value="${listDelinDtls[2].nameDelinq}";
	document.inquiryCaseTracking.delinqName4.value="${listDelinDtls[3].nameDelinq}";
	
	var sel=0;
	
	if("${noOfEmpSelected}"==1)
		{
		<c:set var="noOfEmpSel" value="dept_1"></c:set>
		sel="dept_1";
		}
	else if("${noOfEmpSelected}"==2)
		{
		<c:set var="noOfEmpSel" value="dept_2"></c:set>
		sel="dept_2";
		}
	else if("${noOfEmpSelected}"==3)
		{
		<c:set var="noOfEmpSel" value="dept_3"></c:set>
		sel="dept_3";
		}
	else if("${noOfEmpSelected}"==4)
		{
		<c:set var="noOfEmpSel" value="dept_4"></c:set>
		sel="dept_4";
		}
		
		document.inquiryCaseTracking.noOfEmpSel.value=sel;
		
	delinqDtls();	
	
	if("${objHrPrelimEnqDtls.orgUserMstByEnqUserId.userId}"!='')
		{
		document.inquiryCaseTracking.radioenqOffPrelim[0].checked=true;
		}	
	else if("${objHrPrelimEnqDtls.cmnContactMst.srNo}"!='')
		{
		document.inquiryCaseTracking.radioenqOffPrelim[1].checked=true;
		}	
	enqOffDispPrelim();
}


	function displayAllPrelim()
	{
		displayAllPrelim2();
	if("${objHrPrelimEnqDtls.vcId}" =='')
		{
		document.getElementById('VCTable').style.display='none';
		}
	if("${objHrPrelimEnqDtls.vcId}" !='')
		{
		
		document.getElementById('VCTable').style.display='';
		}
	}



	
</script>

