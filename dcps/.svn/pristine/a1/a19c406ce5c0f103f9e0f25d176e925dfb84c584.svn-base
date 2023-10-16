   <%
try {
%>
<%@ include file="/WEB-INF/jsp/core/include.jsp"%>   

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
	<script type="text/javascript" src="script/leave/ajax_saveData.js"></script>
	<script type="text/javascript" src="script/leave/DateVal.js"></script>
	<script type="text/javascript" src="script/common/calendar.js"></script>
	<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
	<script type="text/javascript" src="common/script/tabcontent.js"></script>
	<script type="text/javascript" src="script/common/CalendarPopup.js"></script>

	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<fmt:setBundle basename="resources.hr.gratuity.gratuity" var="commonLables" scope="request"/>	

	<script language="javascript">
	var reFlag=0;
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
	
	
	
	
	function submitReq(){
		
		
		if(reFlag==1 && document.getElementById("usercompamt").value=='')
		{
			alert('<fmt:message key="HRMS.enterUseramt" bundle="${commonLables}"/>');
			document.getElementById("usercompamt").focus();
			return;
		}
		if(document.getElementById("c_remarks").value==''){
			alert('<fmt:message key="HRMS.enterRemarks" bundle="${commonLables}"/>');
			document.getElementById("c_remarks").focus();
			return;
		}
		
		else{
			
			document.gratuity.action="./hrms.htm?actionFlag=insertGratuity";
			if(validateFloat(document.forms[0].usercompamt))
			{
				document.getElementById("Submit").disabled=true;
				document.gratuity.submit();
			}
		}
		
	}
	
	
	function goBack()
	{
		history.go(-1);
	}
	
	function checkCalculateButton(){

	if(document.gratuity.showGraAmt[0].status)
	{
	return false;
	}
	else if(document.gratuity.showGraAmt[1].status)
	{
	return true;
	}
	}
	
	function showGraComputedAmt(re)
	{
		if(re.value==1)
		{
			document.getElementById('HdSysGraCompAmt').style.display='';
			document.getElementById('SysGraCompAmt').style.display='';
			document.getElementById('HdUserGraCompAmt').style.display='none';
			document.getElementById('UserGraCompAmt').style.display='none';
			document.getElementById('usercompamt').value='';
			reFlag=0;
		}
		else if(re.value==2)
		{
			document.getElementById('HdSysGraCompAmt').style.display='';
			document.getElementById('SysGraCompAmt').style.display='';
			document.getElementById('HdUserGraCompAmt').style.display='';
			document.getElementById('UserGraCompAmt').style.display='';
			reFlag=1;
		}
	}
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
	
	
<table class="tabtable" width="100%" border=0>
	
	<tr bgcolor="#386CB7" >
		<td class="fieldLabel" colspan="5" align="center">
			<font color="#ffffff">
				<strong>
					<u>
						<fmt:message key="HRMS.GratuityAmt"/>
					</u>
				</strong>
			</font>
		</td>
	</tr>
	
	
	<tr>
	
		<td width="30%">
		<strong>
		<fmt:message key="HRMS.optSelected"/>
		
		</strong>
		
		</td>
		
		
		<td align="left">
		
		<b><hdiits:radio name="showGraAmt" validation="sel.isradio" tabindex="1" value="1" onclick="showGraComputedAmt(this);" bundle="${commonLables}" captionid="Gra.SysCalAmt" default="1"  /></b>
		<b><hdiits:radio name="showGraAmt" validation="sel.isradio" tabindex="2" value="2" onclick="showGraComputedAmt(this);" bundle="${commonLables}" captionid="Gra.UserCalAmt"  /></b>
		</td>	
	
		</tr>
	<tr bgcolor="#386CB7"  id="HdSysGraCompAmt">
		<td class="fieldLabel" colspan="5" align="center">
			<font color="#ffffff">
				<strong>
					<u>
						<fmt:message key="HRMS.syscomputed"/>
					</u>
				</strong>
			</font>
		</td>
	</tr>
	
		
	<tr  id="SysGraCompAmt">
 		<td>
			<b>
				<fmt:message key="HRMS.sysenteredamt"></fmt:message>
				
			</b>
 		</td>
	
	   	<td>
      		<hdiits:number style="background-color: lightblue;" default="${amt}"  captionid ="HRMS.syscompamt" name="syscompamt"   id="syscompamt" mandatory="true" validation="txt.isrequired" readonly="true" />

   		</td> 
	</tr>
   
	<tr bgcolor="#386CB7" style="display:none" id="HdUserGraCompAmt">
		<td class="fieldLabel" colspan="5" align="center">
			<font color="#ffffff">
				<strong>
					<u>
						<fmt:message key="HRMS.usercomputed"/>
					</u>
				</strong>
			</font>
		</td>
	</tr>
		
		
	<tr style="display:none" id="UserGraCompAmt">
 		<td>
 			<b>
 				<fmt:message key="HRMS.userenteredamt"></fmt:message>
 				
 			</b>
 		</td>
    
    	<td>
      		<hdiits:number captionid ="HRMS.usercompamt" tabindex="3" name="usercompamt" id="usercompamt" mandatory="true" validation="txt.isrequired" condition="checkCalculateButton()" maxlength="10" />
   		</td> 
   </tr>
 
 	<tr>
 		
    	<td>
      		<hdiits:hidden  name="status" caption="status" default="0" />
   		</td> 
   </tr>
 
 	

	<tr>      
 		<td>
 			<b>
 				<fmt:message key="HRMS.remarks"></fmt:message>
 			</b>
 		</td>

		<td>
			<hdiits:textarea mandatory="true" rows="2" cols="30"  
                       name="remarks" tabindex="4" id="c_remarks" 
                       validation ="txt.isrequired"  bundle="${commonLables}" captionid="HRMS.remarks"  maxlength="2000"/>

			<hdiits:hidden caption="userid" name="userId" default="${userId}"/>
		</td>
	</tr> 
   
	 <tr>
 		<td colspan ="4" align= "center">	
 		<hdiits:button   name="Submit" id="Submit" tabindex="5" onclick="submitReq();" type="button" captionid="HRMS.submit" bundle="${commonLables}"/>
		<hdiits:button type="button" name="close" tabindex="6" value="Close" captionid="HRMS.close"  bundle="${commonLables}" onclick="goBack();"/>
			<!-- <a href="hdiits.htm?actionFlag=getgratuity&gratuity_encash_id=1000100020">View Gratuity</a>  -->
		</td>
	 </tr>
 
</table>
   
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>"'/>

</hdiits:form>

  <%
}
   	catch(Exception e)
   	{
		e.printStackTrace();
	}

%>
 
   		  
	 