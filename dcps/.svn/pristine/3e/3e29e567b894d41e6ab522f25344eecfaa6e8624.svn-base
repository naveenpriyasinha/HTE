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
<c:set var="DeptInqrDtls" value="${resValue.DeptInqrDtls}"/>

<script>

 function dispDeptEnq()
		{
			document.getElementById('penInqrTab').style.display='';
			getFieldGroupObj(document.getElementById('dummy'));
			document.getElementById('dummy').focus();
		}
		
</script>
<hdiits:fieldGroup bundle="${commonLables}" titleCaptionId="Gra.RetType"  id="retirementType"  expandable="true" mandatory="true">
<table width="100%">
	<tr>
	
		<td align="left" >
			${resValue.retirementTypeDesc}
			<hdiits:hidden  name="retirementType" caption="retirementType" default="${resValue.retirementType}" />
		</td>
		
		<td align="left" >
			<strong>
			<hdiits:caption bundle="${commonLables}" captionid="Gra.AppDate" /> 
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
			<hdiits:caption bundle="${commonLables}" captionid ="Gra.YrService" /> 
			</strong>
		</td>
		<td align="left" >
			${totalService}
		</td>
		-->
		<td align="left" >
			<strong>
			<hdiits:caption bundle="${commonLables}" captionid ="Gra.DptInq" /> 
			</strong>
		</td>
		
		<td align="left" >
		<c:if test="${not empty DeptInqrDtls}">
			<a href="#" tabindex="5" onclick="dispDeptEnq();" ><fmt:message   key="Gra.yes"/></a>
		</c:if>
		<c:if test="${empty DeptInqrDtls}">
			<fmt:message   key="Gra.no"/> 
		</c:if>
		</td>
	
		
	

	
	<!--
	<td align="left" >
			<strong>
			<hdiits:caption bundle="${commonLables}" captionid ="Gra.SuspDuration" /> 
			</strong>
			
		</td>
		<td align="left" >
			<hdiits:text name="suspensionDuration" default="0" captionid="Gra.SuspensionDuration" mandatory="true" />
		</td>
		
		
		
	<!--<td align="left" >
			<strong>
			<hdiits:caption bundle="${commonLables}" captionid ="Gra.EoLeave" /> 
			</strong>
		</td>
		<td align="left" >
			<hdiits:text name="extraOrdinaryLeave" default="${resValue.eolbal}" captionid="Gra.ExOLeave" mandatory="true" />
		</td>
		
	</tr>
	
	<tr>
		<td align="left" >
			<strong>
			<hdiits:caption bundle="${commonLables}" captionid ="Gra.LwPay" /> 
			</strong>
		</td>
		<td align="left" >
			<hdiits:text name="leaveWithoutPay" default="${resValue.lwpbal}" captionid="Gra.LeaveWithoutPay" mandatory="true" />
		</td>
		
		--> 
		<td align="left" >
			<strong>
			<hdiits:caption bundle="${commonLables}" captionid ="Gra.EleaveBal" /> 
			</strong>
		</td>
		
		<td align="left" >
			 <hdiits:text  style="background-color: lightblue;" readonly="true" default="${resValue.bal}" name="earnedLeaveBalance" captionid="Gra.EarnedLeaveBalance" mandatory="true" />
			 
			
		</td>
	
	</tr>			
	
</table>
</hdiits:fieldGroup>


<div id="penInqrTab" style="display:none">
<hdiits:fieldGroup bundle="${commonLables}" titleCaptionId="HRMS.DeptInq" id="DeptDtlGrp"  expandable="true">
<label id="dummy" name="dummy"></label>
		<table class="TableBorderLTRBN" border="1" cellpadding="0" cellspacing="0" BGCOLOR="lightblue" align="center" width="85%">
			
		<tr>
		 <%@ include file="/WEB-INF/jsp/hrms/hr/retirementBenefits/commonSearch/departmentalEnq.jsp"%>
		</tr>	
		</table>
	</hdiits:fieldGroup>	
	</div>	

<%}
catch(Exception e){
	e.printStackTrace();
}
%>