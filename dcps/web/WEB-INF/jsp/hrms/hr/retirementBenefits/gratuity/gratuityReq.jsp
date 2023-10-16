 <%
try {
%>
<%@ include file="/WEB-INF/jsp/core/include.jsp"%>   

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

	<script type="text/javascript" src="<c:url value="/script/common/statusbar.js"/>"></script>	
	<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
	<script type="text/javascript" src="<c:url value="/script/hrms/hr/retirementbenefits/RetirementBenefitsCommon.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/script/hrms/hr/retirementbenefits/GraLeaveEnCommon.js"/>"></script>
	
	<fmt:setBundle basename="resources.hr.gratuity.gratuity" var="commonLables" scope="request"/>	

	<script language="javascript">
		var reFlag=0;
		var subFlag=1;
		var alertGraNLeaveEN=new Array();
		alertGraNLeaveEN[0]='<fmt:message key="HRMS.entValidNo" bundle="${commonLables}"/>';
		alertGraNLeaveEN[1]='<fmt:message key="HRMS.enterUseramt" bundle="${commonLables}"/>';
		alertGraNLeaveEN[2]='<fmt:message key="HRMS.enterRemarks" bundle="${commonLables}"/>';
	</script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="amt" value="${resValue.sys_Cal_Amt}"/>
<c:set var="userId" value="${resValue.userId}"/>
<c:set var="elbal" value="${resValue.elbal}"/>
<c:set var="retirementTypeDesc" value="${resValue.retirementType}"/>
<c:set var="retirementTypeDesc" value="${resValue.retirementTypeDesc}"/>
<c:set var="dateOfSrvcExp" value="${resValue.dateOfSrvcExp}"/>
<c:set var="dateOfJoin" value="${resValue.dateOfJoin}"/>

<hdiits:form name="gratuity" validate="true" method="post">

<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>
<br>

<%@ include file="retiredtl.jsp"%> 
	
<hdiits:fieldGroup bundle="${commonLables}" expandable="true" id="graAmt" titleCaptionId="HRMS.GratuityAmt" >	
<table class="tabtable" width="100%" border=0>
	
	
	
	<tr>
	
		<td width="40%">
		<strong>
		<hdiits:caption bundle="${commonLables}" captionid="HRMS.optSelected" />
		
		</strong>
		
		</td>
		
		
		<td align="left">
		
		<hdiits:radio name="showGraAmt" validation="sel.isradio" tabindex="1" value="1" onclick="showComputedAmt(this);" bundle="${commonLables}" captionid="Gra.SysCalAmt" default="1"  />
		<hdiits:radio name="showGraAmt" validation="sel.isradio" tabindex="2" value="2" onclick="showComputedAmt(this);" bundle="${commonLables}" captionid="Gra.UserCalAmt"  />
		</td>	
	
		</tr>
		
</table>
 </hdiits:fieldGroup>

<div id="HdUserGraCompAmt" style="display:none">
<hdiits:fieldGroup bundle="${commonLables}"  expandable="true" id="HdUserGraCompAmtGrp" titleCaptionId="HRMS.usercomputed">
<table class="tabtable">   
		
		
	<tr style="display:none" id="UserGraCompAmt">
 		<td>
 			
 				<hdiits:caption bundle="${commonLables}" captionid="HRMS.userenteredamt" />
 				
 			
 		</td>
    
    	<td>
      		<hdiits:number captionid ="HRMS.usercompamt" tabindex="3" name="usercompamt" id="usercompamt" mandatory="true" validation="txt.isrequired" condition="checkCalculateButton()" maxlength="10" />
   		</td> 
   </tr>
</table>
</hdiits:fieldGroup>
</div>

<hdiits:fieldGroup  bundle="${commonLables}"  expandable="true" id="HdSysGraCompAmt" titleCaptionId="HRMS.syscomputed" mandatory="true">
<table class="tabtable">	
	
		
	<tr  id="SysGraCompAmt">
 		<td>
			
				<hdiits:caption bundle="${commonLables}" captionid="HRMS.sysenteredamt" />
				
			
 		</td>
	
	   	<td>
      		<hdiits:number style="background-color: lightblue;" default="${amt}"  captionid ="HRMS.syscompamt" name="syscompamt"   id="syscompamt" mandatory="true" validation="txt.isrequired" readonly="true" />

   		</td> 
	</tr>
</table>

<table class="tabtable">
 	<tr>
 		
    	<td>
      		<hdiits:hidden  name="status" caption="status" default="0" />
   		</td> 
   </tr>
 
 	

	<tr>      
 		<td>
 			
 				<hdiits:caption bundle="${commonLables}" captionid="HRMS.remarks" />
 			
 		</td>

		<td>
			<hdiits:textarea mandatory="true" rows="2" cols="30"  
                       name="remarks" tabindex="4" id="c_remarks" 
                       validation ="txt.isrequired"  bundle="${commonLables}" captionid="HRMS.remarks"  maxlength="2000"/>

			<hdiits:hidden caption="userid" name="userId" default="${userId}"/>
		</td>
	</tr> 
 </table>
</hdiits:fieldGroup>

<table class="tabtable"> 
	 <tr>
 		<td colspan ="4" align= "center">	
 		<hdiits:button   name="Submit" id="Submit" tabindex="5" onclick="SubmitRequest();" type="button" captionid="HRMS.submit" bundle="${commonLables}"/>
		<hdiits:button type="button" name="back"  value="Back" captionid="HRMS.back" tabindex="6" bundle="${commonLables}" onclick="goBackToGratuity();"/>
		<hdiits:button type="button" name="close" tabindex="7" value="Close" captionid="HRMS.close"  bundle="${commonLables}" onclick="goToHomePage();"/>
			
		</td>
	 </tr>
 
</table>

<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />

</hdiits:form>

  <%
}
   	catch(Exception e)
   	{
		e.printStackTrace();
	}

%>
 
   		  
	 