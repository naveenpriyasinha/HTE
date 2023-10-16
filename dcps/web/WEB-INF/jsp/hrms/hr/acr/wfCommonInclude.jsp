<%
try {
%>
<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.hr.acr.acr" var="commonLables" scope="request"/>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="pointFlag" value="${resValue.pointFlag}"></c:set>
<c:set var="requestId" value="${resValue.requestId}"></c:set>
<c:set var="pointCount" value="${resValue.pointCount}"></c:set>
<c:set var="previousPointFlag" value="${resValue.previousPointFlag}"></c:set>
<c:set var="previousPointData" value="${resValue.previousPointData}"></c:set>
<c:set var="selfAppraisalFlag" value="${resValue.selfAppraisalFlag}"></c:set>
<c:set var="reportFlag" value="${resValue.reportFlag}"></c:set>
<c:set var="fileId" value="${resValue.fileId}"></c:set>
<c:set var="userMap" value="${resValue.userMap}"></c:set>
<c:set var="pointDtlMap" value="${resValue.pointDtlMap}"></c:set>
<c:set var="pointValueMap" value="${resValue.pointValueMap}"></c:set>
<c:set var="allKeyList" value="${resValue.allKeyList}"></c:set>
<c:set var="rolePointValue" value="${resValue.rolePointValue}"></c:set>
<c:set var="selectedYear" value="${resValue.year}"></c:set>
<c:set var="corrId" value="${resValue.corrId}"></c:set>
<c:set var="authority" value="${resValue.authority}"></c:set>
<c:set var="pointList" value="${resValue.pointList}"></c:set>
<c:set var="selfAppPoint" value="${resValue.selfPoint}"></c:set>
<c:set var="selfPointValue" value="${resValue.selfPointValue}"></c:set>
<c:set var="ratingLst" value="${resValue.ratingLst}"></c:set>
<c:set var="read" value="${resValue.readOnly}"></c:set>
<c:set var="showCorr" value="${resValue.showCreateCorrBtn}"></c:set>
<c:set var="disableAllMsg" value="${resValue.disableAllMsg}"></c:set>
<c:set var="disableAll" value="${resValue.disableAll}"></c:set>
<c:set var="fileCorrOpen" value="${resValue.fileCorrOpen}"></c:set>
<c:set var="allOfficerDataAvilabel" value="0"></c:set>
<c:set var="textAreaCounter" value="0"></c:set>
		<hdiits:fieldGroup collapseOnLoad="false" id="acr_file_field2" titleCaptionId="HR.ACR.SelfAppraisalData" bundle="${commonLables}">							
			<table width="100%" border="1" borderColor="BLACK" style="border-collapse: collapse;">				
				<c:if test="${not empty selfAppPoint}">
				<tr bgcolor="B0C4DE">
					<td class="fieldLabel" align="center">
						<b><hdiits:caption captionid="HR.ACR.index" bundle="${commonLables}"/></b>
					</td>					
					<td class="fieldLabel" align="center">
						<b><hdiits:caption captionid="HR.ACR.Goal" bundle="${commonLables}"/></b>
					</td>
					<td class="fieldLabel" align="center">
						<b><hdiits:caption captionid="HR.ACR.Remark" bundle="${commonLables}"/></b>
					</td>
					<td class="fieldLabel" align="center">
						<b><hdiits:caption captionid="HR.ACR.Time" bundle="${commonLables}"/></b>
					</td>		
				</tr>
				<c:set var="x" value="1"></c:set>
				<c:forEach items="${selfAppPoint}" var="point" varStatus="i">
					<tr>
						<td class="fieldLabel">
							<c:out value="${x}"></c:out>
							<c:set var="x" value="${x+1}"></c:set>
						</td>						
						<td  class="fieldLabel">
							<c:out value="${point.point}"></c:out>
						</td>
						<td  class="fieldLabel">
							<c:out value="${selfPointValue[i.index].pointValue}"></c:out>
						</td>
						<td  class="fieldLabel">
							<fmt:formatDate var="startDate"
									value="${selfPointValue[i.index].hrAcrHierarchyGrpDtl.startDate}"
									pattern="dd/MM/yyyy" />
							<fmt:formatDate var="endDate"
									value="${selfPointValue[i.index].hrAcrHierarchyGrpDtl.endDate}"
									pattern="dd/MM/yyyy" />		
							<c:out value="${startDate} - ${endDate}"></c:out>
						</td>
					</tr>
				</c:forEach>
				</c:if>			
				<c:if test="${empty selfAppPoint}">
				<tr><td colspan="10" align="center">
					<b><hdiits:caption  captionid="HR.ACR.SelfAppNot" bundle="${commonLables}"/></b></td></tr>
				</c:if>		
			</table>
		</hdiits:fieldGroup>	
			
<br><br>		
		<hdiits:fieldGroup collapseOnLoad="false" id="acr_file_field3" titleCaptionId="HR.ACR.ACRFill" bundle="${commonLables}">
			<table class="tabtable" id="poitTable" name="poitTable" border="1" borderColor="BLACK" style="border-collapse: collapse;" >							
				<c:set var="firstRow" value="1"></c:set>
				<c:forEach items="${allKeyList}" var="key">
					<c:set var="roleName" value="${allRoleNameDisp[key]}"></c:set>					
					<c:set var="valueList" value="${pointValueMap[key]}"></c:set>	
					<c:set var="pointRoleList" value="${pointDtlMap[key]}"></c:set>					
					<c:set var="userList" value="${userMap[key]}"></c:set>							
					<c:forEach items="${userList}" var="user">													
						<c:choose>
							<c:when test="${firstRow ne 1}">
								<tr background="white" bordercolor="white" height="25px"><td colspan="10"></td></tr>
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
								<c:out value="${user.cmnLookupObj.lookupDesc}" ></c:out>
								</b></u></font>
							</td>
						</tr>						
						<tr>
							<td class="fieldLabel" bgcolor="#E0FFFF" align="center">
								<b><hdiits:caption captionid="HR.ACR.Name" bundle="${commonLables}"/></b>		
							</td>			
							<td class="fieldLabel">
								<c:out value="${user.name}"></c:out>
							</td>
							<td class="fieldLabel" bgcolor="#E0FFFF" align="center">
								<b><hdiits:caption captionid="HR.ACR.Designation" bundle="${commonLables}"/></b>		
							</td>
							<td class="fieldLabel">
								<c:out value="${user.desgn}"></c:out>
							</td>
							<td class="fieldLabel" bgcolor="#E0FFFF" align="center">
								<b><hdiits:caption captionid="HR.ACR.Rating" bundle="${commonLables}"/></b>		
							</td>
							<td class="fieldLabel">
								<c:out value="${user.rating}"></c:out>
							</td>
							<td class="fieldLabel" colspan="2" align="center" bgcolor="#E0FFFF">
								<b><hdiits:caption captionid="HR.ACR.Time" bundle="${commonLables}"/></b>
							</td>
							<td class="fieldLabel">
								<c:out value="${user.time}"></c:out>
							</td>
						</tr>
					</c:forEach>	
					<c:if test="${not empty pointRoleList}">
						<tr bgcolor="B0C4DE">
							<td class="fieldLabel" align="center">
								<b><hdiits:caption captionid="HR.ACR.index" bundle="${commonLables}"/></b>		
							</td>			
							<td class="fieldLabel" align="center" colspan="2">
								<b><hdiits:caption captionid="HR.ACR.Goal" bundle="${commonLables}"/></b>		
							</td>			
							<td colspan="6" class="fieldLabel" align="center">
								<b><hdiits:caption captionid="HR.ACR.Remark" bundle="${commonLables}" /></b>		
							</td>	
						</tr>												
						<c:set value="1" var="counter"></c:set>
						<c:forEach items="${pointRoleList}" var="points" varStatus="i1">
							<tr>
								<td class="fieldLabel">
									<c:out value="${counter}"></c:out>
									<c:set var="counter" value="${counter+1}"></c:set>
								</td>						
								<td class="fieldLabel" colspan="2">
									<c:out value="${points.point}"></c:out>
								</td>
								<td class="fieldLabel" colspan="6">
									<hdiits:textarea readonly="true" name="showValue${textAreaCounter}" cols="60" mandatory="true" default="${valueList[i1.index].pointValue}"></hdiits:textarea>
									<c:set var="textAreaCounter" value="${textAreaCounter+1}"></c:set>
								</td>												
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty pointRoleList}">
						<tr align="center"><td colspan="10" class="fieldLabel" align="center">			
							<c:set var="allOfficerDataAvilabel" value="1"></c:set>
							<hdiits:caption captionid="HR.ACR.RECORDPENDING" bundle="${commonLables}"/>
						</td></tr>
					</c:if>						
					</c:forEach>
				
			</table>
		</hdiits:fieldGroup>	
			<br><br>			
		<hdiits:hidden id="count" name="count" default="-1"/>
		<c:if test="${not empty pointList}">
		<hdiits:fieldGroup collapseOnLoad="false" id="acr_file_field4" titleCaption="${authority}">
			<table class="tabtable" id="poitTable" name="poitTable" border="1" borderColor="BLACK" style="border-collapse: collapse;">				
				<tr bgcolor="B0C4DE">
					<td align="center">
						<b><hdiits:caption captionid="HR.ACR.index" bundle="${commonLables}"/></b>		
					</td>			
					<td align="center">
						<b><hdiits:caption captionid="HR.ACR.Goal" bundle="${commonLables}"/></b>		
					</td>			
					<td colspan="2" align="center">
						<b><hdiits:caption captionid="HR.ACR.Remark" bundle="${commonLables}"/></b>		
					</td>	
				</tr>										
					<c:set var="x" value="0"></c:set>
					<c:forEach items="${pointList}"  var="point" varStatus="i">
						<tr>
							<td class="fieldLabel">
								<c:out value="${x+1}"></c:out>								
							</td>						
							<td class="fieldLabel">
								<c:out value="${point.point}"></c:out>
							</td>
							<td  class="fieldLabel" colspan="2">
								<hdiits:textarea name="Value${x}" id="Value${x}" cols="60" mandatory="true" maxlength="4000" validation="txt.isrequired" captionid="HR.ACR.Remark" bundle="${commonLables}" default="${rolePointValue[i.index].pointValue}"></hdiits:textarea>
								<hdiits:hidden name="pointCode${x}" id="pointCode${x}" default="${point.pointCode}"/>
							</td>												
							<script type="text/javascript">
								document.getElementById('count').value='${x}';								
							</script>
							<c:set var="x" value="${x+1}"></c:set>
						</tr>
					</c:forEach>						
			</table>
		</hdiits:fieldGroup>
		</c:if>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>		