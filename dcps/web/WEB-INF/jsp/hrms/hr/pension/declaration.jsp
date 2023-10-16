<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.hr.pension.Pension" var="pensionDecLabel" scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="decLabel" value="${resValue.decLab}"></c:set>
<c:set var="decComp" value="${resValue.decComp}"></c:set>
<c:set var="ComponentSelectedValue" value="${resValue.ComponentSelectedValue}"></c:set>
<c:set value="radio" var="radio"></c:set>
<c:set var ="flagId" value="-1"></c:set>
<hdiits:hidden name="declarationArr" id="declarationArr" default=""/>
<script>
function addMeIntoDeclarationArray(addMe)
{
	var str = document.getElementById('declarationArr').value;
	if(str!='')
		str=str+'~'+addMe;
	else
		str=addMe;	
	document.getElementById('declarationArr').value=str;
}
</script>
<c:if test="${not empty decLabel}">
<table class="tabtable">
<c:forEach items="${decLabel}" var="hrDeclarationFlagMst">
	<tr><td  class="fieldLabel">
	<c:out value="${hrDeclarationFlagMst.description}"></c:out>
	</td>
	<td  class="fieldLabel">
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
							<hdiits:radio name="dec${hrDeclarationFlagMst.declarationId}" value="${hrDeclarationFlagDtlsData.flagDtlId}" caption="${hrDeclarationFlagDtlsData.label}"  default="${flagId}" readonly="true" mandatory="true" errCaption="Declaration"/>
						</c:if>
					</c:when>	
					<c:otherwise>
						<c:if test="${hrDeclarationFlagMst.component eq radio}">						
							<hdiits:radio name="dec${hrDeclarationFlagMst.declarationId}" value="${hrDeclarationFlagDtlsData.flagDtlId}" caption="${hrDeclarationFlagDtlsData.label}" mandatory="true" validation="sel.isradio" errCaption="Declaration"/>					
						</c:if>	
					</c:otherwise>			
				</c:choose>				
		</c:if>					
	</c:forEach>
	<script>addMeIntoDeclarationArray('${hrDeclarationFlagMst.declarationId}');</script>
	</td></tr>
</c:forEach>
</table>
</c:if>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>