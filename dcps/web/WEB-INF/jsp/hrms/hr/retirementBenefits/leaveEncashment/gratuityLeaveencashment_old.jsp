   <%
try {
%>
<%@ include file="/WEB-INF/jsp/core/include.jsp"%>   
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
	 <script type="text/javascript" src="script/leave/DateDifference.js"></script> 
 <script type="text/javascript" src="<c:url value="/script/leave/leavevalidation.js"/>"></script>
	  <script type="text/javascript" src="<c:url value="/script/leave/DateVal.js"/>"></script>
	<script type="text/javascript" src="script/leave/ajax_saveData.js"></script>
		<script type="text/javascript" src="script/leave/DateVal.js"></script>
	<script type="text/javascript" src="script/common/calendar.js"></script>
	<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
	<script type="text/javascript" src="common/script/tabcontent.js"></script>
	<script type="text/javascript" src="script/common/CalendarPopup.js"></script>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.hr.gratuityLeaveencashment.gratuityLeaveencashment" var="commonLables" scope="request"/>
	
	
	<script language="javascript">
	
	function validateFloat(txtUserComputed){
	if(isNaN(txtUserComputed.value)){
		alert('<fmt:message key="HRMS.entValidNo" />');
		document.forms[0].usercompamt.value='';
		document.forms[0].usercompamt.focus();
		return false;
	}
	else{
		return true;	
	}
}
	
	
	
	
	function SubmitRequest(){
	
		if(document.getElementById("c_remarks").value==''){
			alert('<fmt:message key="HRMS.enterRemarks" bundle="${commonLables}"/>');
			return;
		}
	else{
			
			
			
			document.gratuityLeaveencashment.action="./hrms.htm?actionFlag=leavencash";
			if(validateFloat(document.forms[0].usercompamt))
			{
				document.getElementById("subButton").disabled=true;
				document.gratuityLeaveencashment.submit();
			}
		}
			
	}
	
	function goBack()
	{
		history.go(-1);
	}
	
	function checkCalculateButton(){
 		 
	if(document.gratuityLeaveencashment.showLeaveAmt[0].status){
	
	return false;
	}
	else if(document.gratuityLeaveencashment.showLeaveAmt[1].status)
	{
	return true;
	}
	}
	function showLeaveComputedAmt(re)
	{
		if(re.value==1)
		{
			document.getElementById('HdSysLeaveCompAmt').style.display='';
			document.getElementById('SysLeaveCompAmt').style.display='';
			document.getElementById('HdUserLeaveCompAmt').style.display='none';
			document.getElementById('UserLeaveCompAmt').style.display='none';
			document.getElementById('usercompamt').value='';
		}
		else if(re.value==2)
		{
			document.getElementById('HdSysLeaveCompAmt').style.display='';
			document.getElementById('SysLeaveCompAmt').style.display='';
			document.getElementById('HdUserLeaveCompAmt').style.display='';
			document.getElementById('UserLeaveCompAmt').style.display='';
		}
	}
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


<table class="tabtable">


	<tr bgcolor="#386CB7">
		<td class="fieldLabel" colspan="5" align="center">
			<font color="#ffffff">
			<strong><u><b><fmt:message bundle="${commonLables}" key="HRMS.LeavEncashmentAmt"/></b></u></strong>
			</font>
		</td>
	</tr>
	
	<tr >
		<td>
		<strong>
		<hdiits:caption bundle="${commonLables}" captionid="Gra.optSelected" captionLang="multi"/>
		</strong>
		</td>
		<td align="left" colspan="4" >
		
		<b><hdiits:radio name="showLeaveAmt" validation="sel.isradio" value="1" tabindex="1" onclick="showLeaveComputedAmt(this);" bundle="${commonLables}" captionid="Gra.SysCalAmt" default="1"/></b>
		<b><hdiits:radio name="showLeaveAmt" validation="sel.isradio" value="2" tabindex="2" onclick="showLeaveComputedAmt(this);" bundle="${commonLables}" captionid="Gra.UserCalAmt"  /></b>
		
		</td>	
	
	</tr>
	
	<tr bgcolor="#386CB7"   id="HdSysLeaveCompAmt">
		<td class="fieldLabel" colspan="5" align="center">
			<font color="#ffffff">
				<strong>
					<u>
						<b><fmt:message bundle="${commonLables}" key="HRMS.syscomputed"/></b>
					</u>
				</strong>
			</font>
		</td>
	</tr>
	
	
		
	<tr   id="SysLeaveCompAmt">
 		<td>
			<b>
				<hdiits:caption bundle="${commonLables}" captionid="HRMS.sysenteredamt" captionLang="multi"/>
				
			</b>
 		</td>
	
 		
    	<td>
      		<hdiits:text style="background-color: lightblue;" default="${amt}" captionid ="HRMS.syscompamt" name="syscompamt"   id="syscompamt" mandatory="true" validation="txt.isrequired" readonly="true"/>
   		</td> 
   </tr>
   
   <tr bgcolor="#386CB7" style="display:none" id="HdUserLeaveCompAmt">
		<td class="fieldLabel" colspan="5" align="center">
			<font color="#ffffff">
			<strong><u><b><fmt:message bundle="${commonLables}" key="HRMS.usercomputed"/></b></u></strong>
			</font>
		</td>
	</tr>
		
		
	<tr style="display:none" id="UserLeaveCompAmt">
 		<td>
 			<b>
 				<hdiits:caption bundle="${commonLables}" captionid="HRMS.userenteredamt" captionLang="multi"/>
 				
 			</b>
 		</td>
    
    	<td>
      		<hdiits:number captionid ="HRMS.usercompamt" name="usercompamt" id="usercompamt" tabindex="3" mandatory="true" validation="txt.isrequired" condition="checkCalculateButton()" maxlength="10"/>
   		</td> 
   </tr>
 
 <tr>
 		
    	<td>
      		<hdiits:hidden  name="status" caption="status" default="0" />
   		</td> 
   </tr>


 <tr>      
 <td><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.remarks" captionLang="multi"/></b></td>
<td>
<hdiits:textarea mandatory="true" rows="2" cols="30"  
                                    name="remarks" tabindex="4" id="c_remarks" 
                                    validation ="txt.isrequired"   caption="remarks" maxlength="2000"/>

			<hdiits:hidden caption="userid" name="userId" default="${userId}"/>
</td>


  </tr> 
   
 <tr>
 	<td colspan ="4" align= "center">	
 		<hdiits:button id="subButton" type="button" name="Submit" captionid="HRMS.submit" tabindex="5" bundle="${commonLables}" onclick="SubmitRequest();" />
 		<hdiits:button type="button" name="close"  value="Close" captionid="HRMS.close" tabindex="6" bundle="${commonLables}" onclick="goBack();"/>
		<!-- <a href="hdiits.htm?actionFlag=getleaveEncash&gratuity_encash_id=1000100020">View Leaveencashment</a>-->
	</td>
 </tr>
 
 </table>

<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
 </hdiits:form>
 
  <%
}catch(Exception e){
	e.printStackTrace();
}

%>
 
   		  
 