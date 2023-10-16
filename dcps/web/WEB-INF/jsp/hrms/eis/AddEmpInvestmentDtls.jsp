<%

try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page session="true" %>
<script type="text/javascript"  
    src="common/script/commonfunctions.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript"   
	src="common/script/tagLibValidation.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>

<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
	
<script type="text/javascript" src="script/hrms/eis/employeeInvestmentDetails.js"></script>
<script type="text/javascript" src="script/hrms/eis/PayTabNavigation.js"></script>
	

<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>
<c:set var="lstInvestTypes" value="${resValue.lstInvestTypes}" > </c:set>
<c:set var="msg" value="${resValue.msg}" ></c:set>

<script>
function chkKey(e){	
	if(e.keyCode=="13") {
		return false;
	}
	else {		
		return true;
	}
}

function checkAvailability()
{
	empId = document.getElementById("Employee_ID_investSearch").value;
	if(empId!='') {
		try {   
				xmlHttp=new XMLHttpRequest();
   		}
		catch(e){    // Internet Explorer    
			try {
	     		xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");   
    	 	}
	    	catch (e) {
		    	try {
	            	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
    	   		}
			    catch (e) {
			        alert("Your browser does not support AJAX!");        
			        return false;        
			    }
			}
		}
		}
		var url = "hrms.htm?actionFlag=checkEmpAvailability&empId="+empId;  	
   var available=0;	
    xmlHttp.onreadystatechange = function() {		
		if (xmlHttp.readyState == 4) {     			
			if (xmlHttp.status == 200) {			
				var XMLDocForAjax=xmlHttp.responseXML.documentElement;
				var empIdMapping = XMLDocForAjax.getElementsByTagName('empIdMapping');	
				var flag="true";
							
				if(empIdMapping.length != 0) {		
						if(empIdMapping[0].childNodes[0].text==flag){
							available=1;				
							//return true;
						}	
						else {
						    alert("Record no found");
						    
							//return false;	
						}
				}				
			}
		}
	}
	
	xmlHttp.open("POST", encodeURI(url) , false);    
	xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
	xmlHttp.send(encodeURIComponent(null));	
	if(available==1)
		return true;
	else
		return false;	
	}


</script>
<body>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="enLables" scope="page"/>


 
<hdiits:form name="frmEmpInvestDtls" validate="true" method="POST"
	action="./hrms.htm?actionFlag=insertUpdateEmpInvestDtls&edit=N" encType="text/form-data">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs" compact="compact">
			<li class="selected"><a href="#" rel="tcontent1"><font size="2"><b><hdiits:caption captionid="EMPINVEST.empInvestAdd" bundle="${enLables}"></hdiits:caption></b></a></li>
		</ul>
	</div>
	<div class="exhalftabcontentstyle">
	
	<div id="tcontent1" class="halftabcontent" tabno="0">
	
	    <br>
	    <table align="center">
	    <tr>
		<TD class="fieldLabel" colspan="2">
					<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
						<jsp:param name="searchEmployeeTitle" value="Search Employee"/>
						<jsp:param name="SearchEmployee" value="investSearch"/>
						<jsp:param name="formName" value="frmEmpInvestDtls"/>
						<jsp:param name="mandatory" value="Yes"/>
						
					</jsp:include>
		 </td>
		 <td>
        <hdiits:hidden id="empId" name = "empId"  />
	    </td>
	    </tr>
	    </table>
	    <table align="center">
		<tr>
			<td width="5%"></td>	
			<td width="20%"><font size="2"><b><hdiits:caption captionid="EMPINVEST.investType" bundle="${enLables}"></hdiits:caption> </b> </td>
			<td width="20%"><hdiits:select name="investType"
			    captionid="EMPINVEST.investType"
			    mandatory="true"  
			    validation="sel.isrequired">
			    <hdiits:option value="-1">---------------Select----------------</hdiits:option>
			    <c:forEach items="${lstInvestTypes}" var="investTypes">
			    	<hdiits:option value="${investTypes.investTypeId}">${investTypes.investName}</hdiits:option>
			    </c:forEach>
			    </hdiits:select></td>
			    <td width="10%">&nbsp;</td>
			<td width="20%"><font size="2"><b><hdiits:caption captionid="NGD.amount" bundle="${enLables}"></hdiits:caption> </b></font> </td>
			<td width="20%"><hdiits:number name="investAmount" default=""style="text-align:right" size="25" caption="NGD.amount" maxlength="100" validation="txt.isrequired,txt.isnumber" mandatory="true"/></td>
			<td width="5%"></td>
		</tr>
		<tr></tr>
		<tr>

		     <td width="5%"></td>
			<td width="20%"><font size="2"><b><font size="2"><b><hdiits:caption captionid="EMPINVEST.proofSubmition" bundle="${enLables}"></hdiits:caption> </b> </font></td>
			<td width="20%"><hdiits:checkbox name="isProofSubmit" id="isProofSubmit" value="Y"  bundle="${enLables}" />
			
			
			</td>
			<td></td>
			<td width="20%"> <font size="2"><b><hdiits:caption captionid="EMPINVEST.investDate" bundle="${enLables}"></hdiits:caption> </b> </font></td>
			<td width="20%"> <hdiits:dateTime name="investDate" caption="Investment Date" bundle="${enLables}" mandatory="true" validation="txt.isrequired,txt.isdt" minvalue=""/> </td>
			<td width="5%"></td>
		</tr>
		<tr>
		<tr> </tr> <tr> </tr>
		<tr>
			<td width="5%"></td>			
			<td width="20%"><font size="2"><b><hdiits:caption captionid="EMPINVEST.investPolicyNo" bundle="${enLables}"></hdiits:caption> </b></font> </td>
			<td><hdiits:text name="policyNo" size="25" caption="Policy No" maxlength="100"  /></td>	
			<td width="10%"></td>
			<td width="20%"><font size="2"><b><font size="2"><b><hdiits:caption captionid="EMPINVEST.proofApproval" bundle="${enLables}"></hdiits:caption></b> </font></td>
			<td width="20%"><hdiits:checkbox name="isProofApproved" id="isProofApproved" value="Y"  bundle="${enLables}" /></td>
			<td width="5%"></td>
			 <td><hdiits:hidden name="recCounter" captionid="recCounter" default="0"></hdiits:hidden> </td>
		</tr>
		
		
		</table>
 	</div>  
 	<br>
	
	<table id="btnAddInvest" class="tabtable" border=0 >
					<tr>
						<TD class="fieldLabel" align="center" colspan="4"><hdiits:button type="button" name="addInvest" id="multiInvestAdd" captionid="EMPINVEST.addInvDets" bundle="${enLables}" onclick="addInvestDetails('addInvestRecord')" /></TD>
					</tr>
	</table>
	
	
	
	<br><font size="2"><b>
	<table id="tblEmpInvestDtls" align="center" border="1"  class="datatable" bgcolor="ffffff" bordercolor="aaaaaa" >
	
 			<tr>
 				
 				<td width="20%" align="center">Employee Name</td>
 				<td width="15%" align="center">Type</td>
 				<td width="10%" align="center">Amount</td>
 				<td width="20%" align="center">Proof Submittion</td>
 				<td width="15%" align="center">Date</td> 				
 				<td width="15%" align="center">Policy No.</td>
 				<td width="20%" align="center">Proof Approved</td> 				
 				<td width="10%" align="center"></td> 				
 			</tr>
 		</table>         
	
 	</font>
 	<br>
 <fmt:setBundle basename="resources.eis.eis_common_lables" var="Lables" scope="page"/>	
 	<jsp:include page="../../core/PayTabnavigation.jsp" />
 	</div>
<hdiits:hidden name="ProofApproved" id="ProofApproved"/>
<hdiits:hidden name="ProofSubmit" id="ProofSubmit"/>
<hdiits:jsField name="checkInvestDetails" jsFunction="checkInvestDetails()"/>
<hdiits:hidden name="jsfieldCheckInvestLabel" captionid="EMPINVEST.jsfieldCheckInvestLabel" bundle="${enLables}"/>
		
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		if("${msg}"!=null&&"${msg}"!='')
		{
			alert("${msg}");
			var url="./hrms.htm?actionFlag=getEmpInvestmentData";
			document.frmEmpInvestDtls.action=url;
			document.frmEmpInvestDtls.submit();
		}
		</script>
	<hdiits:validate controlNames="investType,investDate,investAmount,Employee_Name_investSearch"
		locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>
</body>
<%
	} catch (Exception e) {
		e.printStackTrace();
	}
%>