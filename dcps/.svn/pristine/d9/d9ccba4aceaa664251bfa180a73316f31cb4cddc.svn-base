  <%
try {
%>
<%@ include file="/WEB-INF/jsp/core/include.jsp"%>   
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

	<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
	<script type="text/javascript" src="<c:url value="/script/hrms/hr/retirementbenefits/RetirementBenefitsCommon.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/script/hrms/hr/retirementbenefits/GraLeaveEnCommon.js"/>"></script>

<fmt:setBundle basename="resources.hr.gratuityLeaveencashment.gratuityLeaveencashment" var="commonLables" scope="request"/>
	
	
	<script language="javascript">
	var reFlag=0;
	var subFlag=0;
	var alertGraNLeaveEN=new Array();
	alertGraNLeaveEN[0]='<fmt:message key="HRMS.entValidNo" bundle="${commonLables}"/>';
	alertGraNLeaveEN[1]='<fmt:message key="HRMS.enterUseramt" bundle="${commonLables}"/>';
	alertGraNLeaveEN[2]='<fmt:message key="HRMS.enterRemarks" bundle="${commonLables}"/>';
	</script>
	

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="amt" value="${resValue.sys_Cal_Amt}"></c:set>
<c:set var="userId" value="${resValue.userId}"></c:set>
<c:set var="bal" value="${resValue.bal}"></c:set>
<c:set var="retirementTypeDesc" value="${resValue.retirementType}"/>
<c:set var="retirementTypeDesc" value="${resValue.retirementTypeDesc}"/>

<hdiits:form name="gratuityLeaveencashment" validate="true" action="./hrms.htm?actionFlag=leavencash" method="post">


<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>
<br>

<%@ include file="retiredtl.jsp"%> 

<hdiits:fieldGroup bundle="${commonLables}" expandable="true" id="leaveAmt" titleCaptionId="HRMS.LeavEncashmentAmt" >
<table class="tabtable">	
		
	<tr >
		<td>
		<strong>
		<hdiits:caption bundle="${commonLables}" captionid="Gra.optSelected" />
		</strong>
		</td>
		<td align="left" colspan="4" >
		
		<b><hdiits:radio name="showLeaveAmt" validation="sel.isradio" value="1" tabindex="1" onclick="showComputedAmt(this);" bundle="${commonLables}" captionid="Gra.SysCalAmt" default="1"/></b>
		<b><hdiits:radio name="showLeaveAmt" validation="sel.isradio" value="2" tabindex="2" onclick="showComputedAmt(this);" bundle="${commonLables}" captionid="Gra.UserCalAmt"  /></b>
		
		</td>	
	
	</tr>
</table>
 </hdiits:fieldGroup>
	
<div id="HdUserLeaveCompAmt" style="display:none">
<hdiits:fieldGroup bundle="${commonLables}"  expandable="true" id="HdUserLeaveCompAmtGrp" titleCaptionId="HRMS.usercomputed">
<table class="tabtable">
   		
	<tr style="display:none" id="UserLeaveCompAmt">
 		<td>
 			<b>
 				<hdiits:caption bundle="${commonLables}" captionid="HRMS.userenteredamt" />
 				
 			</b>
 		</td>
    
    	<td>
      		<hdiits:number captionid ="HRMS.usercompamt" name="usercompamt" id="usercompamt" tabindex="3" mandatory="true" validation="txt.isrequired" condition="checkCalculateButton()" maxlength="10"/>
   		</td> 
   </tr>
</table>
</hdiits:fieldGroup>
</div>

<hdiits:fieldGroup  bundle="${commonLables}"  expandable="true" id="HdSysLeaveCompAmt" collapseOnLoad="false" titleCaptionId="HRMS.syscomputed" mandatory="true">
<table class="tabtable">		
	<tr   id="SysLeaveCompAmt">
 		<td>
			<b>
				<hdiits:caption bundle="${commonLables}" captionid="HRMS.sysenteredamt" />
				
			</b>
 		</td>
	
 		
    	<td>
      		<hdiits:text style="background-color: lightblue;" default="${amt}" captionid ="HRMS.syscompamt" name="syscompamt"   id="syscompamt" mandatory="true" validation="txt.isrequired" readonly="true"/>
   		</td> 
   </tr>



<table class="tabtable">
 <tr>
 		
    	<td>
      		<hdiits:hidden  name="status" caption="status" default="0" />
   		</td> 
   </tr>


 <tr>      
 <td><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.remarks" /></b></td>
<td>
<hdiits:textarea mandatory="true" rows="2" cols="30"  
                                    name="remarks" tabindex="4" id="c_remarks" 
                                    validation ="txt.isrequired"   caption="remarks" maxlength="2000"/>

			<hdiits:hidden caption="userid" name="userId" default="${userId}"/>
</td>


  </tr> 

</table>

</table>
</hdiits:fieldGroup>

<table class="tabtable">
 <tr>
 	<td colspan ="4" align= "center">	
 		<hdiits:button   name="Submit" id="Submit" tabindex="5" onclick="SubmitRequest();" type="button" captionid="HRMS.submit" bundle="${commonLables}"/>
		<hdiits:button type="button" name="back"  value="Back" captionid="HRMS.back" tabindex="6" bundle="${commonLables}" onclick="goBackToLeaveEncash();"/>
 		<hdiits:button type="button" name="close"  value="Close" captionid="HRMS.close" tabindex="7" bundle="${commonLables}" onclick="goToHomePage();"/>
		<!-- <a href="hdiits.htm?actionFlag=getleaveEncash&gratuity_encash_id=1000100020">View Leaveencashment</a>-->
	</td>
 </tr>
</table>

<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
 </hdiits:form>
 
  <%
}catch(Exception e){
	e.printStackTrace();
}

%>
 
   		  
 