<%try{%>
<%@ include file="/WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<fmt:setBundle basename="resources.hr.gratuityLeaveencashment.gratuityLeaveencashment" var="commonLables" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	

<c:set var="userId" value="${resValue.userId}"></c:set>
<c:set var="bal" value="${resValue.bal}"></c:set>
<c:set var="retirementTypeDesc" value="${resValue.retirementType}"/>
<c:set var="retirementTypeDesc" value="${resValue.retirementTypeDesc}"/>
<c:set var="applicationDate" value="${resValue.applicationDate}"/>




<table width="100%" >
	<tr width="100%" bgcolor="#386CB7">
	<td  class="fieldLabel" colspan="5"  align="center">	
		<font color="white">
		<strong><u><fmt:message  key="Gra.RetDetail"/></u></strong>
		</font>
	</td>
	</tr>
	<tr>
	<td align="left" >
			<strong>
			<fmt:message   key="Gra.RetType"/> 
			</strong>
		</td>
		<td align="left" >
			${resValue.retirementTypeDesc}
			<hdiits:hidden  name="retirementType" caption="retirementType" default="${resValue.retirementType}" />
		</td>
		
		<td align="left" >
			<strong>
			<fmt:message   key="Gra.ApplicationDate"/> 
			</strong>
		</td>
		<td align="left" >
			<fmt:formatDate value="${list.createdDate}" pattern="dd/MM/yyyy" var="createdDate"/>
			${createdDate}
			<fmt:formatDate value="${resValue.applicationDate}" pattern="dd/MM/yyyy" var="applicationDate"/>
			${applicationDate}
		</td>
	</tr>
	
	<tr>
	<!--	
<td align="left" >
			<strong>
			<fmt:message   key="Gra.YrService"/> 
			</strong>
		</td>
		<td align="left" >
			${totalService}
		</td>
		-->
		<td align="left" >
			<strong>
			<fmt:message   key="Gra.DptInq"/> 
			</strong>
		</td>
		<td align="left" >
			<fmt:message   key="Gra.no"/> 
		</td>
	
		
	

	
	<!--
	<td align="left" >
			<strong>
			<fmt:message   key="Gra.SuspDuration"/> 
			</strong>
			
		</td>
		<td align="left" >
			<hdiits:text name="suspensionDuration" default="0" captionid="Gra.SuspensionDuration" mandatory="true" />
		</td>
		
		
		
	<!--<td align="left" >
			<strong>
			<fmt:message   key="Gra.EoLeave"/> 
			</strong>
		</td>
		<td align="left" >
			<hdiits:text name="extraOrdinaryLeave" default="${resValue.eolbal}" captionid="Gra.ExOLeave" mandatory="true" />
		</td>
		
	</tr>
	
	<tr>
		<td align="left" >
			<strong>
			<fmt:message   key="Gra.LwPay"/> 
			</strong>
		</td>
		<td align="left" >
			<hdiits:text name="leaveWithoutPay" default="${resValue.lwpbal}" captionid="Gra.LeaveWithoutPay" mandatory="true" />
		</td>
		
		--> 
		<td align="left" >
			<strong>
			<fmt:message   key="Gra.EleaveBal"/> 
			</strong>
		</td>
		
		<td align="left" >
			 <hdiits:text  style="background-color: lightblue;" readonly="true" default="${resValue.bal}" name="earnedLeaveBalance" captionid="Gra.EarnedLeaveBalance" mandatory="true" />
			 
			
		</td>
	
	</tr>			


	
	</table>

<%}
catch(Exception e){
	e.printStackTrace();
}
%>