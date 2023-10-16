<%
try {
%>
<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.hr.acr.acr_AlertMessages" var="commonLables1" scope="request"/>

<script type="text/javascript"   
	src="common/script/tagLibValidation.js">
</script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript"  
    src="script/common/commonfunctions.js">
</script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript">
function closeFunction()
{
	document.IniateRequest.method="POST";
	document.IniateRequest.action="./hrms.htm?actionFlag=getDocListOfWorkList";
	document.IniateRequest.submit();
}
function submit2(obj,btnName)
{
	obj.disable=true;
	document.getElementsByName(btnName).disable=true;
	showProgressbar("Please Wait...");	
	document.IniateRequest.method="POST";
	document.IniateRequest.action="./hrms.htm?actionFlag=ACRHiChangeNotAccepted&flag=Y";	
	document.IniateRequest.submit();	
}
function submit1(obj,btnName)
{
	obj.disable=true;
	document.getElementsByName(btnName).disable=true;
	showProgressbar("Please Wait...");		
	document.IniateRequest.method="POST";
	document.IniateRequest.action="./hrms.htm?actionFlag=ACRHiChangeAccepted&flag=N";
	document.IniateRequest.submit();
}
function showCursorAsHand(elem)
{
	elem.style.cursor='hand';
}
</script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	


<c:set var="parentDataMap" value="${resValue.parentDataMap}"></c:set>
<c:set var="parentDataList" value="${resValue.parentDataList}"></c:set>
<c:set var="allRoleList" value="${resValue.allRoleList}"></c:set>
<c:set var="pkVal" value="${resValue.pkVal}"></c:set>
<c:set var="selfFlag" value="${resValue.selfFlag}"></c:set>
<c:set var="requestList" value="${resValue.requestList}"></c:set>
<c:set var="reason" value="${resValue.reason}"></c:set>
<c:set var="readOnly" value="${resValue.readOnly}"></c:set>

<fmt:setBundle basename="resources.hr.acr.acr" var="commonLables" scope="request"/>
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b> 
			<fmt:message key="HR.ACR.ACR" bundle="${commonLables}"/>
		</b></a></li>
	</ul>
</div>
<div class="tabcontentstyle">
	 <div id="tcontent1" class="tabcontent"  tabno="0">		
<hdiits:form name="IniateRequest" method="POST" validate="true"  encType="multipart/form-data">
		<jsp:include page="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"></jsp:include>		
		<BR>
			<c:if test="${readOnly eq 'Y'}">										
					<table id="recordInserted" align="center" border="1" bgcolor="white" bordercolor="black" width="100%">
					<tr>
						<td colspan="10" align="center">
						<font color="red"><b>
							<fmt:message key="HR.ACR.ReqApp" bundle="${commonLables}" />							
						</b></font>				
						</td>
					</tr>
					</table>
					<BR><BR>
			</c:if>
			<hdiits:fieldGroup collapseOnLoad="false" id="hie_change_field1" titleCaptionId="HR.ACR.Initiate" bundle="${commonLables}">							
				<c:forEach items="${allRoleList}" var="parent" varStatus="x">
				<c:set value="${parent.lookupName}" var="flag"></c:set>
				<table  border="1" bgcolor="white" bordercolor="black" id="default" width="100%">							
				<c:if test="${parent.lookupName ne 'acrRoleSelf'}">
					<tr bgcolor="B0C4DE">
						<td colspan="10" align="center">						
								<b><c:out value="${parent.lookupDesc}"></c:out></b>
						</td>																				
					</tr>
					<tr>
						<td width="5%" align="center">
							<b><hdiits:caption captionid="HR.ACR.Name" bundle="${commonLables}" id="Name1"/></b>
						</td>
						<td width="5%" align="center">
							<b><hdiits:caption captionid="HR.ACR.District" bundle="${commonLables}" id="District1"/></b>
						</td>
						<td width="5%" align="center">
							<b><hdiits:caption captionid="HR.ACR.Designation" bundle="${commonLables}" id="Designation1"/></b>
						</td>
						<td width="5%" align="center">
							<b><hdiits:caption captionid="HR.ACR.Time" bundle="${commonLables}"/></b>
						</td>
					</tr>
				</c:if>			
					<c:forEach items="${parentDataList}" var="parentObj" varStatus="x">					
						<c:if test="${flag eq parentObj.cmnLookupObj.lookupName}">																
							<c:choose>
								<c:when test="${parentObj.name ne 'NO^ACR'}">
								<tr>
									<td width="10%" align="left">
										<c:out value="${parentObj.name}"></c:out>
									</td>
									<td width="10%" align="left">
										<c:out value="${parentObj.district}"></c:out>
									</td>
									<td width="10%" align="left">
										<c:out value="${parentObj.desgn}"></c:out>      
									</td>							
									<td width="10%" align="left">
										<c:out value="${parentObj.time}"></c:out>      
									</td>
								</tr>
								</c:when>
								<c:otherwise>
								<tr>
									<td width="10%" align="left">NO ACR</td>
									<td width="10%" align="left">NO ACR</td>
									<td width="10%" align="left">NO ACR</td>							
									<td width="10%" align="left">
										<c:out value="${parentObj.time}"></c:out>      
									</td>
								</tr>
								</c:otherwise>
							</c:choose>	
						</c:if>											
					</c:forEach>
					<c:if test="${empty parentDataList}">
						<c:if test="${parent.lookupName ne 'acrRoleSelf'}">
							<tr><td colspan="10" align="center">
							<b><fmt:message key="HR.ACR.NORECORD" bundle="${commonLables}"/> </b></td></tr>
						</c:if>
					</c:if>
				</table>
			</c:forEach>										
			<table>
				<tr>
					<td>
						<c:if test="${empty parentDataList}">
							<c:if test="${parent.lookupName ne 'acrRoleSelf'}">
								<b><hdiits:caption captionid="HR.ACR.NoDefaultHierarchy" bundle="${commonLables}"/></b>
							</c:if>
						</c:if>
					</td>
				</tr>
			</table>
			</hdiits:fieldGroup>
			<br><br>	
			<hdiits:fieldGroup collapseOnLoad="false" id="hie_change_field2" titleCaptionId="HR.ACR.RequestedHierarchy" bundle="${commonLables}">							
				<c:forEach items="${allRoleList}" var="parent" varStatus="x">
				<c:set value="${parent.lookupName}" var="flag"></c:set>
				<table  border="1" bgcolor="white" bordercolor="black" id="default" width="100%">							
					<c:if test="${parent.lookupName ne 'acrRoleSelf'}">
						<tr bgcolor="B0C4DE">
							<td colspan="10" align="center">						
									<b><c:out value="${parent.lookupDesc}"></c:out></b>
							</td>																				
						</tr>
						<tr>
							<td width="5%" align="center">
								<b><hdiits:caption captionid="HR.ACR.Name" bundle="${commonLables}" id="Name1"/></b>
							</td>
							<td width="5%" align="center">
								<b><hdiits:caption captionid="HR.ACR.District" bundle="${commonLables}" id="District1"/></b>
							</td>
							<td width="5%" align="center">
								<b><hdiits:caption captionid="HR.ACR.Designation" bundle="${commonLables}" id="Designation1"/></b>
							</td>
							<td width="5%" align="center">
								<b><hdiits:caption captionid="HR.ACR.Time" bundle="${commonLables}"/></b>
							</td>						
						</tr>	
					</c:if>				
					<c:forEach items="${requestList}" var="parentObj" varStatus="x">					
						<c:if test="${flag eq parentObj.cmnLookupObj.lookupName}">																
							<c:choose>
								<c:when test="${parentObj.name ne 'NO^ACR'}">
								<tr>
									<td width="10%" align="left">
										<c:out value="${parentObj.name}"></c:out>
									</td>
									<td width="10%" align="left">
										<c:out value="${parentObj.district}"></c:out>
									</td>
									<td width="10%" align="left">
										<c:out value="${parentObj.desgn}"></c:out>      
									</td>							
									<td width="10%" align="left">
										<c:out value="${parentObj.time}"></c:out>      
									</td>
								</tr>
								</c:when>
								<c:otherwise>
								<tr>
									<td width="10%" align="left">NO ACR</td>
									<td width="10%" align="left">NO ACR</td>
									<td width="10%" align="left">NO ACR</td>							
									<td width="10%" align="left">
										<c:out value="${parentObj.time}"></c:out>      
									</td>
								</tr>
								</c:otherwise>
							</c:choose>	
						</c:if>											
					</c:forEach>
					<c:if test="${empty requestList}">
						<c:if test="${parent.lookupName ne 'acrRoleSelf'}">
							<tr><td colspan="10" align="center">
							<b><fmt:message key="HR.ACR.NORECORD" bundle="${commonLables}"/> </b></td></tr>
						</c:if>
					</c:if>
				</table>
				</c:forEach>							
			<br>
				<table width="100%" align="center">
				<tr>
					<td colspan="10" align="center">
						<c:if test="${empty requestList}">
							<c:if test="${parent.lookupName ne 'acrRoleSelf'}">
								<b><hdiits:caption captionid="HR.ACR.NoRequestHierarchy" bundle="${commonLables}"/></b>
							</c:if>
						</c:if>
					</td>
				</tr>
				</table>	
			</hdiits:fieldGroup>
			<br><br>
			
			<table id="reason">
			<tr colspan="2">
				<td>
						<b><hdiits:caption captionid="HR.ACR.Reason" bundle="${commonLables}" id="Reason"/>:</b>
						
				</td>
				<td>
					<hdiits:textarea default="${reason}" rows="3" cols="100" name="reason" tabindex="7" id="reason" 
                     captionid="HR.ACR.Reason" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color:lightblue;"
                     bundle="${commonLables}" readonly="true"/>
				</td>
				</tr>
			</table>
			<br><br>
			<c:if test="${readOnly ne 'Y'}">
			<table align="center">
			<tr align="center">
				<td colspan="10">
					<hdiits:button captionid="HR.ACR.Set" bundle="${commonLables}" name="Set" type="button" onclick="submit2(this,'AcceptChange');" />
					<hdiits:button captionid="HR.ACR.AcceptChange" bundle="${commonLables}" name="AcceptChange" type="button" onclick="submit1(this,'Set');"  />
				</td>
			</tr>
			</table>
			</c:if>
<hdiits:hidden name="pkVal" id="pkVal" default="${pkVal}"/>			
</hdiits:form>
<script type="text/javascript">
	initializetabcontent("maintab")
</script>
<hdiits:validate locale="${locale}" controlNames="" /></div>
</div>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

	