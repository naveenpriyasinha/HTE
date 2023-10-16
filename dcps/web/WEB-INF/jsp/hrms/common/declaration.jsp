<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="decLabel" value="${resValue.decLab}"></c:set>
<c:set var="decComp" value="${resValue.decComp}"></c:set>
<c:set var="ComponentSelectedValue" value="${resValue.ComponentSelectedValue}"></c:set>
<c:set value="radio" var="radio"></c:set>
<c:set value="checkbox" var="checkbox"></c:set>
<c:set var ="flagId" value="-1"></c:set>
<c:set var ="compType" value="-1"></c:set>
<c:set var ="methodName" value="${resValue.methodName}"></c:set>
<fmt:setBundle basename="resources.common.hrmsCommon" var="commonLables1" scope="request" />
<hdiits:hidden name="declarationArr" id="declarationArr" default=""/>
<script>
var decValidationArr = new Array();
function addMeIntoDeclarationArray(addMe)
{
	var str = document.getElementById('declarationArr').value;
	decValidationArr.push('dec'+addMe);
	if(str!='')
		str=str+'~'+addMe;
	else
		str=addMe;	
	document.getElementById('declarationArr').value=str;
}
</script>
<fmt:message bundle="${commonLables1}" key="HRMS.declaration" var="desc"></fmt:message>
<c:if test="${not empty decLabel}">
<table class="tabtable">
<c:forEach items="${decLabel}" var="hrDeclarationFlagMst">
	<tr>
	<c:forEach items="${decComp}" var="hrDeclarationFlagDtlsData">				
		<c:if test="${hrDeclarationFlagDtlsData.hrDeclarationFlagMst.declarationId eq hrDeclarationFlagMst.declarationId}">			
				<c:if test="${not empty ComponentSelectedValue}">
					<c:set var ="flagId" value="0"></c:set>
					<c:forEach items="${ComponentSelectedValue}" var="componenetValue">				
						<c:if test="${componenetValue.hrDeclarationFlagDtlsData.flagDtlCode eq hrDeclarationFlagDtlsData.flagDtlCode}">							
							<c:set var ="flagId" value="${componenetValue.hrDeclarationFlagDtlsData.flagDtlId}"></c:set>
						</c:if>
					</c:forEach>
				</c:if>	
				<c:choose>
					<c:when test="${flagId ne -1}">
						<c:if test="${hrDeclarationFlagMst.component eq checkbox}">
							<td  class="fieldLabel" width="10%"><hdiits:checkbox name="dec${hrDeclarationFlagMst.declarationId}" value="${hrDeclarationFlagDtlsData.flagDtlId}" caption="${hrDeclarationFlagDtlsData.label}"  default="${flagId}" readonly="true" mandatory="true" errCaption="${desc}"/></td>
							<c:set var ="compType" value="0"></c:set>
						</c:if>
					</c:when>	
					<c:otherwise>
						<c:if test="${hrDeclarationFlagMst.component eq checkbox}">						
							<c:set var ="compType" value="0"></c:set>
							<td  class="fieldLabel" width="10%"><hdiits:checkbox name="dec${hrDeclarationFlagMst.declarationId}" value="${hrDeclarationFlagDtlsData.flagDtlId}" caption="${hrDeclarationFlagDtlsData.label}" mandatory="true" errCaption="${desc}" onclick="${methodName}(this)"/></td>
						</c:if>	
					</c:otherwise>			
				</c:choose>				
		</c:if>					
	</c:forEach>
	<td class="fieldLabel" width="75%">
		<c:out value="${hrDeclarationFlagMst.description}"></c:out>
	</td>
	<c:if test="${compType ne 0}">	
		<td  class="fieldLabel" width="25%">
		<c:forEach items="${decComp}" var="hrDeclarationFlagDtlsData">				
			<c:if test="${hrDeclarationFlagDtlsData.hrDeclarationFlagMst.declarationId eq hrDeclarationFlagMst.declarationId}">			
					<c:if test="${not empty ComponentSelectedValue}">
						<c:set var ="flagId" value="0"></c:set>
						<c:forEach items="${ComponentSelectedValue}" var="componenetValue">				
							<c:if test="${componenetValue.hrDeclarationFlagDtlsData.flagDtlCode eq hrDeclarationFlagDtlsData.flagDtlCode}">							
								<c:set var ="flagId" value="${componenetValue.hrDeclarationFlagDtlsData.flagDtlId}"></c:set>
							</c:if>
						</c:forEach>
					</c:if>	
					<c:choose>
						<c:when test="${flagId ne -1}">
							<c:if test="${hrDeclarationFlagMst.component eq radio}">
								<hdiits:radio name="dec${hrDeclarationFlagMst.declarationId}" value="${hrDeclarationFlagDtlsData.flagDtlId}" caption="${hrDeclarationFlagDtlsData.label}"  default="${flagId}" readonly="true" mandatory="true" errCaption="${desc}"/>
							</c:if>
						</c:when>	
						<c:otherwise>
							<c:if test="${hrDeclarationFlagMst.component eq radio}">						
								<hdiits:radio name="dec${hrDeclarationFlagMst.declarationId}" value="${hrDeclarationFlagDtlsData.flagDtlId}" caption="${hrDeclarationFlagDtlsData.label}" mandatory="true" validation="sel.isradio" errCaption="${desc}" onclick="${methodName}(this)"/>
							</c:if>	
						</c:otherwise>			
					</c:choose>				
			</c:if>							
		</c:forEach>
		</td>
	</c:if>
	<script>addMeIntoDeclarationArray('${hrDeclarationFlagMst.declarationId}');</script>
	</tr>
</c:forEach>
</table>
</c:if>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>