
<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.core.lables" var="lables" scope="request"/>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script> 

<fmt:setBundle basename="resources.posting.postingLabels" var="empEditListCommonLables" scope="request" />

<script type="text/javascript">

		
	
	function retdate()
	{
		var doj=document.frmEmpBasicInfo.DOB.value;
		var cls = document.frmEmpBasicInfo.selClass.value;
		var splitDate=doj.split("/");							
		var jday=splitDate[0];
		var jmo=splitDate[1];
		var jyr=parseInt(splitDate[2]);
		// Added by Rajan
		if(cls=="AIS")
		jyr=jyr+60;
		else
		jyr=jyr+58;
		jday = LastDayOfMonth(jyr,jmo);
		//Ended by Rajan
		var dor=jday+'/'+jmo+'/'+jyr;
		document.frmEmpBasicInfo.DOR.value=dor;
	}
	
	function LastDayOfMonth(Year, Month)
		{
        	//return(new Date((new Date(Year, Month+1,1))-1)).getDate();
        	var dd = new Date(Year, Month, 0);
			return dd.getDate();
        	
		}
	
	function saveInfo()
	{
		var statusSpecific = validateSpecificForm();
		if(statusSpecific)
		{
			var fieldArray = new Array('Salutation','emp_first_name_eng','','emp_last_name_eng','emp_first_name_guj','','emp_last_name_guj','selClass','DOB','DOJ');
			var statusValidation = validateSpecificFormFields(fieldArray); 
		}
		
		if(statusValidation)
		{
			document.frmEmpBasicInfo.action="hrms.htm?actionFlag=saveEmployeeEntry";
			document.frmEmpBasicInfo.emp_middle_name_eng.value = document.frmEmpBasicInfo.emp_middle_name_eng.value + ' ';
			document.frmEmpBasicInfo.emp_middle_name_guj.value=document.frmEmpBasicInfo.emp_middle_name_eng.value + ' ';
			//alert('value is' + document.frmEmpBasicInfo.emp_middle_name_eng.value +'abc');
			document.frmEmpBasicInfo.submit();
		}
	}
	
	
	
	function validateSpecificForm()
	{
		var name_rule_num="qwertyuiopasdfghjklzxcvbnm ASDFGHJKLPOIUYTREWQZXCVBNM";
		var statusFlag = true;
		
		var first_name_eng=document.getElementById("emp_first_name_eng").value;
		if(first_name_eng!="")
	   	{
	   		for(var i=0;i<first_name_eng.length;i++)
			{
        		var cchar=first_name_eng.charAt(i);
		        if(name_rule_num.indexOf(cchar)==-1)
        		{
        			//alert(""+cchar);
        			alert('<fmt:message  bundle="${empEditListCommonLables}" key="ValidFNameAlert"/>');
        			document.getElementById("emp_first_name_eng").value='';
        			document.getElementById("emp_first_name_eng").focus();
					//alert("Enter Valid Characters in First Name Field");
	      	  	 	return false;
	      	  	 	break;
        		}
	    	} 
	   	}
	   	
	   	/*var middle_name_eng=document.getElementById("emp_middle_name_eng").value;
		if(middle_name_eng!="")
	   	{
	   		for(var i=0;i<middle_name_eng.length;i++)
			{
        		var cchar=middle_name_eng.charAt(i);
		        if(name_rule_num.indexOf(cchar)==-1)
        		{
        			alert('<fmt:message  bundle="${empEditListCommonLables}" key="ValidMNameAlert"/>');
        			document.getElementById("emp_middle_name_eng").value='';
        			document.getElementById("emp_middle_name_eng").focus();
					//alert("Enter Valid Characters in Middle Name Field");
	      	  	 	return false;
	      	  	 	break;
        		}
	    	} 
	   	}*/
	   	
	   	var last_name_eng=document.getElementById("emp_last_name_eng").value;
		if(last_name_eng!="")
	   	{
	   		for(var i=0;i<last_name_eng.length;i++)
			{
        		var cchar=last_name_eng.charAt(i);
		        if(name_rule_num.indexOf(cchar)==-1)
        		{
        			alert('<fmt:message  bundle="${empEditListCommonLables}" key="ValidLNameAlert"/>');
        			document.getElementById("emp_last_name_eng").value='';
        			document.getElementById("emp_last_name_eng").focus();
					//alert("Enter Valid Characters in Last Name Field");
	      	  	 	return false;
	      	  	 	break;
        		}
	    	} 
	   	}
	   	
/*	   	var first_name_guj=document.getElementById("emp_first_name_guj").value;
		if(first_name_guj!="")
	   	{
	   		for(var i=0;i<first_name_guj.length;i++)
			{
        		var cchar=first_name_guj.charAt(i);
		        if(name_rule_num.indexOf(cchar)==-1)
        		{
        			alert('<fmt:message  bundle="${empEditListCommonLables}" key="ValidFNameAlert"/>');
					//alert("Enter Valid Characters in First Name Field");
	      	  	 	statusFlag=false;
	      	  	 	break;
        		}
	    	} 
	   	}
	   	
	   	var middle_name_guj=document.getElementById("emp_middle_name_guj").value;
		if(middle_name_guj!="")
	   	{
	   		for(var i=0;i<middle_name_guj.length;i++)
			{
        		var cchar=middle_name_guj.charAt(i);
		        if(name_rule_num.indexOf(cchar)==-1)
        		{
					alert('<fmt:message  bundle="${empEditListCommonLables}" key="ValidMNameAlert"/>');
					//alert("Enter Valid Characters in Middle Name Field");
	      	  	 	statusFlag=false;
	      	  	 	break;
        		}
	    	} 
	   	}
	   	
	   	var last_name_guj=document.getElementById("emp_last_name_guj").value;
		if(last_name_guj!="")
	   	{
	   		for(var i=0;i<last_name_guj.length;i++)
			{
        		var cchar=last_name_guj.charAt(i);
		        if(name_rule_num.indexOf(cchar)==-1)
        		{
        			alert('<fmt:message  bundle="${empEditListCommonLables}" key="ValidLNameAlert"/>');
					//alert("Enter Valid Characters in Last Name Field");
	      	  	 	statusFlag=false;
	      	  	 	break;
        		}
	    	} 
	   	}*/
	   	return true;
	}
	
	
	function GoToPage(){
	
	document.frmEmpBasicInfo.action="./hrms.htm?actionFlag=employeeEntryView";
		document.frmEmpBasicInfo.submit();
	}
	
	function resetData()
	{
		document.frmEmpBasicInfo.Salutation.value='';
		document.frmEmpBasicInfo.emp_first_name_eng.value='';
		document.frmEmpBasicInfo.emp_middle_name_eng.value='';
		document.frmEmpBasicInfo.emp_last_name_eng.value='';
		document.frmEmpBasicInfo.emp_first_name_guj.value='';
		document.frmEmpBasicInfo.emp_middle_name_guj.value='';
		document.frmEmpBasicInfo.emp_last_name_guj.value='';
		document.frmEmpBasicInfo.DOB.value='';
		document.frmEmpBasicInfo.DOJ.value='';
		document.frmEmpBasicInfo.DOR.value='';
		document.frmEmpBasicInfo.selClass.value='0';
	}
	
	
	  function resetForm()
			  {
			  	if(confirm("<fmt:message key="TABNAV.RESETCONFIRM" bundle="${lables}"></fmt:message>") == true)
			  	{
			  		document.frmEmpBasicInfo.reset();
			  		<c:if test="${param.afterReset gt ''}">
						${param.afterReset};
					</c:if>
					window.location.reload();
			  	}
			  				  	
			  }
	
	function copyToGujFname()
	{
    var fName = document.frmEmpBasicInfo.emp_first_name_eng.value;
    document.frmEmpBasicInfo.emp_first_name_guj.value = fName;
    }
	
	function copyToGujMname()
	{
	 var mName = document.frmEmpBasicInfo.emp_middle_name_eng.value;
    document.frmEmpBasicInfo.emp_middle_name_guj.value = mName;
    }
	
	
	function copyToGujLname()
	{     var lName = document.frmEmpBasicInfo.emp_last_name_eng.value;
    document.frmEmpBasicInfo.emp_last_name_guj.value = lName;
	}
	
	function checkDate()
	{
		dob=document.frmEmpBasicInfo.DOB.value;
		doj=document.frmEmpBasicInfo.DOJ.value;

	if(dob!='' && doj!='' && compareDate(dob,doj) < 0)
	{
	alert('Date of Birth Should not be Greater than Date of Joining');
	document.frmEmpBasicInfo.DOB.value='';
	document.frmEmpBasicInfo.DOJ.value='';
	document.frmEmpBasicInfo.DOR.value='';
	return false
	}

	return true;
	}
	

	function closeWindow()
	{
		window.close();
	}
</script>



<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<c:set var="resultObj" value="${result}" />
<c:set var="resValue" value="${resultObj.resultValue}" />
<c:set var="empInfoDtlsEng" value="${resValue.empInfoDtlsEng}"/>
<c:set var="empInfoDtlsGuj" value="${resValue.empInfoDtlsGuj}"/>
<c:set var="empInfoDtls" value="${resValue.empInfoDtls}"/>
<c:set var="selectedUserId" value="${resValue.userId}"></c:set>
<c:set var="classDtls" value="${resValue.classDtls}"></c:set>
<c:set var="empMentName" value="${resValue.empMentName}"></c:set>
<c:set var="gradeCode" value="${resValue.gradeCode}"></c:set>
<c:set var="msg" value="${resValue.msg}" ></c:set>

<link rel="stylesheet"
	href="<c:url value="/themes/hdiits/tabcontent.css"/>" type="text/css" />


<hdiits:form name="frmEmpBasicInfo" validate="true" method="POST" action="">

	
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>New User Creation</b></a></li>
	</ul>
	</div>

<div class="halftabcontentstyle">
	<div id="tcontent1" class="halftabcontent" tabno="0">
	
	<table align="center" cellsapcing="2" cellpadding="2">
		
		<tr> 
		<td class="fieldLabel" align="left" colspan="2">
				<b><fmt:message key="Salutation" bundle="${empEditListCommonLables}"/></b>
			</td>
			<td>
				&nbsp;&nbsp;&nbsp;&nbsp;<hdiits:text name="Salutation" id="Salutation"
				maxlength="6" captionid="Salutation" size="3"
				bundle="${empEditListCommonLables}" validation = "txt.isrequired" default="${empInfoDtls.empPrefix}" mandatory="true"/>
			</td>
			
			
			
		</tr>
		<tr></tr><tr></tr>
		<tr>
			<td>
			</td>
			<td></td>
			<td class="fieldLabel" align="center"><b><fmt:message key="FIRST_NAME" bundle="${empEditListCommonLables}"/></b></td>
			<td class="fieldLabel" align="center"><b><fmt:message key="MIDDLE_NAME" bundle="${empEditListCommonLables}"/></b></td>
			<td class="fieldLabel" align="center"><b><fmt:message key="LAST_NAME" bundle="${empEditListCommonLables}"/></b></td>
		</tr>
		
		<tr>
		
			<td class="fieldLabel" align="left" colspan="2">
				<b><fmt:message key="NAME_EN" bundle="${empEditListCommonLables}"/></b>
			</td>
			
			<td align="center">
				&nbsp;&nbsp;&nbsp;&nbsp;<hdiits:text name="emp_first_name_eng" id="emp_first_name_eng" captionid="FIRST_NAME" bundle="${empEditListCommonLables}" maxlength="30" validation="txt.isrequired" default="${empInfoDtlsEng.empFname}" mandatory="true" onblur="copyToGujFname();copyToGujLname()" />
			</td>
			<td align="center">
				<hdiits:text name="emp_middle_name_eng" id="emp_middle_name_eng" captionid="MIDDLE_NAME" bundle="${empEditListCommonLables}" maxlength="30" default="${empInfoDtlsEng.empMname}"  onblur="copyToGujMname()" />
			</td>
			<td align="center">
				<hdiits:text name="emp_last_name_eng" id="emp_last_name_eng" captionid="LAST_NAME" bundle="${empEditListCommonLables}" maxlength="30" validation="txt.isrequired" default="${empInfoDtlsEng.empLname}" mandatory="true" onblur="copyToGujLname()"/>
			</td>
		</tr>
		<tr>
			<td style="display:none">
				<b><hdiits:caption	captionid="NAME_GU" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>
			<td align="center" style="display:none">
				<hdiits:text name="emp_first_name_guj" id="emp_first_name_guj" captionid="FIRST_NAME" bundle="${empEditListCommonLables}" maxlength="30" validation="txt.isrequired" default="${empInfoDtlsGuj.empFname}" />
			</td>
			<td align="center" style="display:none">
				<hdiits:text name="emp_middle_name_guj" id="emp_middle_name_guj" captionid="MIDDLE_NAME" bundle="${empEditListCommonLables}" maxlength="30" validation="txt.isrequired" default="${empInfoDtlsGuj.empMname}"/>
			</td>
			<td align="center" style="display:none">
				<hdiits:text name="emp_last_name_guj" id="emp_last_name_guj" captionid="LAST_NAME" bundle="${empEditListCommonLables}" maxlength="30" validation="txt.isrequired" default="${empInfoDtlsGuj.empLname}" />
			</td>
		</tr>
		<tr>
			<td class="fieldLabel" align="left" colspan="2">
				<b><fmt:message key="CLASS" bundle="${empEditListCommonLables}"/></b>
		</td><td>
					&nbsp;&nbsp;&nbsp;&nbsp;<hdiits:select name="selClass" id="selClass" captionid="CLASS" bundle="${empEditListCommonLables}" mandatory="true" default="${gradeCode}" validation="sel.isrequired">
						<option value="0"><hdiits:caption captionid="SELECT" bundle="${empEditListCommonLables}"/></option>
						<c:forEach var="classes" items="${classDtls}">
							<option value="<c:out value="${classes.gradeCode}"/>">
							<c:out value="${classes.gradeName}" /></option>
						</c:forEach>
					</hdiits:select>
						</td>
		</tr>
		
		<tr>
			<td class="fieldLabel" align="left" colspan="2">
				<b><fmt:message key="DATE_OF_BIRTH" bundle="${empEditListCommonLables}"/></b>
			</td>
			<td >
				&nbsp;&nbsp;&nbsp;&nbsp;<hdiits:dateTime name="DOB" captionid="DATE_OF_BIRTH" bundle="${empEditListCommonLables}" default="${empInfoDtls.empDob}" mandatory="true" afterDateSelect="retdate()" validation="txt.isdt,txt.isrequired" onblur="checkDate()"/>
			</td>
			<td>
			</td>
			<td>
			</td>
		</tr>
		<tr>
			<td class="fieldLabel" align="left" colspan="2">
				<b><fmt:message key="DATE_OF_JOINING" bundle="${empEditListCommonLables}"/></b>
			</td>
			<td >
				&nbsp;&nbsp;&nbsp;&nbsp;<hdiits:dateTime name="DOJ" captionid="DATE_OF_JOINING" bundle="${empEditListCommonLables}"  default="${empInfoDtls.empDoj}" mandatory="true" validation="txt.isdt,txt.isrequired" onblur="checkDate()"/>
			</td>
			<td>
			</td>
			<td>
			</td>
			</tr>
			<tr>
			<td class="fieldLabel" align="left" colspan="2">
				<b><fmt:message key="LEAVE_DATE" bundle="${empEditListCommonLables}"/></b>
			</td>
			<td>
				&nbsp;&nbsp;&nbsp;&nbsp;<hdiits:dateTime name="DOR" captionid="LEAVE_DATE" bundle="${empEditListCommonLables}" default="${empInfoDtls.empSrvcExp}" onblur="checkDate()"/>
			</td>
			<td>
			</td>
			<td>
			</td>
		</tr>
	</table>
	
	<table align="center">	
		<tr>
			<td align="center">
				<c:if test="${empty empInfoDtls}">
					<br></br><hdiits:button name="Submit" type="button" captionid="SUBMIT" bundle="${empEditListCommonLables}" onclick="saveInfo()"></hdiits:button>
				</c:if>
				<c:if test="${not empty empInfoDtls}">
					<br></br><hdiits:button name="Update" type="button" captionid="UPDATE" bundle="${empEditListCommonLables}" onclick="saveInfo()"></hdiits:button>
				</c:if>
			</td>

			<td align="center" >
				<br></br><hdiits:button  name="reset" type="button"  style="display:none" captionid="RESET" bundle="${empEditListCommonLables}" onclick="resetForm()"></hdiits:button>
			</td>
			<td align="center">
				<br></br><hdiits:button name="Close" type="button" captionid="CLOSE" bundle="${empEditListCommonLables}" onclick="GoToPage()"></hdiits:button>
			</td>
		</tr>
	</table>
	

<hdiits:hidden name="userId" id="userId" default="${selectedUserId}"></hdiits:hidden>


<script type="text/javascript">

	initializetabcontent("maintab");
	document.frmEmpBasicInfo.emp_first_name_eng.focus();

	if("${msg}"!=null&&"${msg}"!=''){
	
	if ("${selectedUserId}" != null )
	{
	
		
				alert("${msg}");
			var url="./hrms.htm?actionFlag=employeeEntryView";
			document.frmEmpBasicInfo.action=url;
			document.frmEmpBasicInfo.submit();
	}
	
	else
	{
	
	alert("${msg}");

			var url="./hrms.htm?actionFlag=employeeEntryView";
			document.frmEmpBasicInfo.action=url;
			document.frmEmpBasicInfo.submit();
	
	}
	
	}
		
	
    	
	
</script>

	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' controlNames="Salutation,emp_first_name_eng,emp_middle_name_eng,emp_last_name_eng,emp_first_name_guj,emp_middle_name_guj,emp_last_name_guj,selClass,DOB,DOJ" />
	


</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
