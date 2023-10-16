
<%
try {
%>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<c:set var="resultObj" value="${result}" />
<c:set var="resValue" value="${resultObj.resultValue}" />
<c:set var="empInfoDtlsEng" value="${resValue.empInfoDtlsEng}"/>
<c:set var="empInfoDtlsGuj" value="${resValue.empInfoDtlsGuj}"/>
<c:set var="empInfoDtls" value="${resValue.empInfoDtls}"/> 
<c:set var="selectedUserId" value="${resValue.userId}"></c:set>
<c:set var="classDtls_en" value="${resValue.classDtls_en}"></c:set> <!-- changed by divya -->
<c:set var="classDtls_gu" value="${resValue.classDtls_gu}"></c:set> <!-- added by divya -->
<c:set var="empMentName" value="${resValue.empMentName}"></c:set>
<c:set var="gradeCode" value="${resValue.gradeCode}"></c:set>
<c:set var="arEmpSrvcFlag_en" value="${resValue.arEmpSrvcFlag_en}"></c:set><!-- added by divya -->
<c:set var="arStatus_en" value="${resValue.arStatus_en}"></c:set><!-- added by divya -->
<c:set var="arActivateFlag_en" value="${resValue.arActivateFlag_en}"></c:set><!-- added by divya -->
<c:set var="arEmpSrvcFlag_gu" value="${resValue.arEmpSrvcFlag_gu}"></c:set><!-- added by divya -->
<c:set var="arStatus_gu" value="${resValue.arStatus_gu}"></c:set><!-- added by divya -->
<c:set var="arActivateFlag_gu" value="${resValue.arActivateFlag_gu}"></c:set><!-- added by divya -->
<c:set var="strEmpSrvcCode" value="${resValue.strEmpSrvcCode}"></c:set><!-- added by divya -->
<c:set var="lstDsgnEng" value="${resValue.lstDsgnEng}"></c:set><!-- 25 june -->
<c:set var="lstDsgnGuj" value="${resValue.lstDsgnGuj}"></c:set><!-- 25 june -->
<c:set var="dsgnCode" value="${resValue.dsgnCode}"></c:set> <!-- added 25 june -->
<c:set var="flag" value="${resValue.flag}"></c:set> <!-- added 26 june -->
<c:set var="langId" value="${resValue.langId}"></c:set><!-- added by divya -->

<fmt:setBundle basename="resources.ess.UserPost" var="userPostLables" scope="request" />

<html>
<head>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>

<script type="text/javascript">

	function saveInfo()
	{
		//var statusSpecific = validateSpecificForm();
		if('${langId}' == '1')
		{
			copyEngToGuj();
			//if(statusSpecific)
			{
				//var fieldArrayEng = new Array('Salutation','emp_first_name_eng','emp_middle_name_eng','emp_last_name_eng','selClass','selDesignation','DOB','DOJ','dtStartDate','selEmpSrvcStatus','selStatusFlag');
				var fieldArrayEng = new Array('Salutation','emp_first_name_eng','emp_middle_name_eng','emp_last_name_eng','selClass','selDesignation','DOB','DOJ','dtStartDate','selEmpSrvcStatus'); 
				var statusValidationEng = validateSpecificFormFields(fieldArrayEng); 
				
				if(statusValidationEng)
				{
					var stausFlagVal = getCheckedRadioValue("rdoActiveFlag");
					if(stausFlagVal == "")
					{					
						alert("<fmt:message bundle='${userPostLables}' key='selActivate'/>");
						return;
					}
					else
					{
						//var fieldArrayGuj = new Array('Salutation_gu','emp_first_name_guj','emp_middle_name_guj','emp_last_name_guj','selClass_gu','selDesignation_gu','DOB_gu','DOJ_gu','dtStartDate_gu','selEmpSrvcStatus_gu','selStatusFlag_gu');
						var fieldArrayGuj = new Array('Salutation_gu','emp_first_name_guj','emp_middle_name_guj','emp_last_name_guj','selClass_gu','selDesignation_gu','DOB_gu','DOJ_gu','dtStartDate_gu','selEmpSrvcStatus_gu');
						var statusValidationGuj = validateSpecificFormFields(fieldArrayGuj);
						
						if(!statusValidationGuj){}
							//copyEngToGuj();
						else
						{
							if(document.getElementById("selClass_gu").value == "")
							{
								selectRequiredTab("selClass_gu");
								alert('<fmt:message bundle="${userPostLables}" key="selClass"/>');
								return false;
							}
							if(document.getElementById("selDesignation_gu").value == "")
							{
								selectRequiredTab("selDesignation_gu");
								alert('<fmt:message bundle="${userPostLables}" key="selDesignation"/>');
								return false;
							}
							if(document.getElementById("selEmpSrvcStatus_gu").value == "")
							{
								selectRequiredTab("selEmpSrvcStatus_gu");
								alert('<fmt:message bundle="${userPostLables}" key="selEmpServiceSt"/>');
								return false;
							}
						
							var stausFlagVal = getCheckedRadioValue("rdoActiveFlag_gu");
							if(stausFlagVal == "")
							{
								//copyEngToGuj();
								selectRequiredTab("rdoActiveFlag_gu");
								alert("<fmt:message bundle='${userPostLables}' key='selActivate'/>");
								return;
							}
						}
							
					}
				}				
			}
		}
		else if('${langId}' == '2')
		{
			copyGujToEng();
			//if(statusSpecific)
			{
				//var fieldArrayGuj = new Array('Salutation_gu','emp_first_name_guj','emp_middle_name_guj','emp_last_name_guj','selClass_gu','selDesignation_gu','DOB_gu','DOJ_gu','dtStartDate_gu','selEmpSrvcStatus_gu','selActiveFlag_gu','selStatusFlag_gu');
				var fieldArrayGuj = new Array('Salutation_gu','emp_first_name_guj','emp_middle_name_guj','emp_last_name_guj','selClass_gu','selDesignation_gu','DOB_gu','DOJ_gu','dtStartDate_gu','selEmpSrvcStatus_gu','selActiveFlag_gu');
				var statusValidationGuj = validateSpecificFormFields(fieldArrayGuj);				
				
				if(statusValidationGuj)
				{
					var stausFlagVal = getCheckedRadioValue("rdoActiveFlag_gu");
					if(stausFlagVal == "")
					{
						alert("<fmt:message bundle='${userPostLables}' key='selActivate'/>");
						return;
					}
					else
					{
						//var fieldArrayEng = new Array('Salutation','emp_first_name_eng','emp_middle_name_eng','emp_last_name_eng','selClass','selDesignation','DOB','DOJ','dtStartDate','selEmpSrvcStatus','selActiveFlag','selStatusFlag');
						var fieldArrayEng = new Array('Salutation','emp_first_name_eng','emp_middle_name_eng','emp_last_name_eng','selClass','selDesignation','DOB','DOJ','dtStartDate','selEmpSrvcStatus','selActiveFlag');
						var statusValidationEng = validateSpecificFormFields(fieldArrayEng); 
						if(!statusValidationEng){}
							//copyGujToEng();
						else
						{
							if(document.getElementById("selClass").value == "")
							{
								selectRequiredTab("selClass");
								alert('<fmt:message bundle="${userPostLables}" key="selClass"/>');
								return false;
							}
							if(document.getElementById("selDesignation").value == "")
							{
								selectRequiredTab("selDesignation");
								alert('<fmt:message bundle="${userPostLables}" key="selDesignation"/>');
								return false;
							}
							if(document.getElementById("selEmpSrvcStatus").value == "")
							{
								selectRequiredTab("selEmpSrvcStatus");
								alert('<fmt:message bundle="${userPostLables}" key="selEmpServiceSt"/>');
								return false;
							}
							
							var stausFlagVal = getCheckedRadioValue("rdoActiveFlag");
							if(stausFlagVal == "")
							{
								//copyGujToEng();
								selectRequiredTab("rdoActiveFlag");
								alert("<fmt:message bundle='${userPostLables}' key='selActivate'/>");
								return;
							}
						}
					}
				}				
			}
		}
		
		if(statusValidationEng && statusValidationGuj)
		{			
			document.frmEmpBasicInfo.update.disabled = true;
			document.frmEmpBasicInfo.action="hdiits.htm?actionFlag=saveEmployeeEntry";
			document.frmEmpBasicInfo.submit();
		}
	}		
	function resetData()
	{
		document.frmEmpBasicInfo.Salutation.value='';
		document.frmEmpBasicInfo.emp_first_name_eng.value='';
		document.frmEmpBasicInfo.emp_middle_name_eng.value='';
		document.frmEmpBasicInfo.emp_last_name_eng.value='';		
		document.frmEmpBasicInfo.DOB.value='';
		document.frmEmpBasicInfo.DOJ.value='';
		document.frmEmpBasicInfo.DOR.value='';
		document.frmEmpBasicInfo.selClass.value='0';
		document.frmEmpBasicInfo.dtStartDate.value='';
		document.frmEmpBasicInfo.dtEndDate.value='';		
		document.frmEmpBasicInfo.selEmpSrvcStatus.value='0';
		var rdoLength = document.frmEmpBasicInfo.rdoActiveFlag.length;		
		for(var i = 0; i < rdoLength; i++)
		{
			document.frmEmpBasicInfo.rdoActiveFlag[i].checked = false;
		}
		//document.frmEmpBasicInfo.selStatusFlag.value='0';
		
		document.frmEmpBasicInfo.Salutation_gu.value='';
		document.frmEmpBasicInfo.emp_first_name_guj.value='';
		document.frmEmpBasicInfo.emp_middle_name_guj.value='';
		document.frmEmpBasicInfo.emp_last_name_guj.value='';		
		document.frmEmpBasicInfo.DOB_gu.value='';
		document.frmEmpBasicInfo.DOJ_gu.value='';
		document.frmEmpBasicInfo.DOR_gu.value='';
		document.frmEmpBasicInfo.selClass_gu.value='0';
		document.frmEmpBasicInfo.dtStartDate_gu.value='';
		document.frmEmpBasicInfo.dtEndDate_gu.value='';
		document.frmEmpBasicInfo.selEmpSrvcStatus_gu.value='0';
		var rdoLength = document.frmEmpBasicInfo.rdoActiveFlag_gu.length;		
		for(var i = 0; i < rdoLength; i++)
		{
			document.frmEmpBasicInfo.rdoActiveFlag_gu[i].checked = false;
		}
		//document.frmEmpBasicInfo.selStatusFlag_gu.value='0';
	}
	
	function closeWindow()
	{
		document.frmEmpBasicInfo.action="hrms.htm?actionFlag=showUsersList";
		document.frmEmpBasicInfo.submit();		
	}
	
	function copyEngToGuj()
	{
		/* if('${selectedUserId}'=='0') // it means insert mode (add)
		{
			document.getElementById('Salutation_gu').value = document.getElementById('Salutation').value;
			document.getElementById('emp_first_name_guj').value = document.getElementById('emp_first_name_eng').value;
			document.getElementById('emp_middle_name_guj').value = document.getElementById('emp_middle_name_eng').value;
			document.getElementById('emp_last_name_guj').value = document.getElementById('emp_last_name_eng').value;
		}*/	
		document.getElementById('selClass_gu').value = document.getElementById('selClass').value;
		document.frmEmpBasicInfo.selClass_gu.disabled = true;
		
		document.getElementById('DOB_gu').value = document.getElementById('DOB').value;
		document.frmEmpBasicInfo.DOB_gu.disabled = true;
		
		document.getElementById('DOJ_gu').value = document.getElementById('DOJ').value;
		document.frmEmpBasicInfo.DOJ_gu.disabled = true;
		
		document.getElementById('DOR_gu').value = document.getElementById('DOR').value;
		document.frmEmpBasicInfo.DOR_gu.disabled = true;
		
		document.getElementById('dtStartDate_gu').value = document.getElementById('dtStartDate').value;
		document.frmEmpBasicInfo.dtStartDate_gu.disabled = true;
		
		document.getElementById('dtEndDate_gu').value = document.getElementById('dtEndDate').value;
		document.frmEmpBasicInfo.dtEndDate_gu.disabled = true;
		
		document.getElementById('selEmpSrvcStatus_gu').value = document.getElementById('selEmpSrvcStatus').value;
		document.frmEmpBasicInfo.selEmpSrvcStatus_gu.disabled = true;
		
		document.getElementById('selDesignation_gu').value = document.getElementById('selDesignation').value; // 25 June
		document.frmEmpBasicInfo.selDesignation_gu.disabled = true; // 25 June eng & guj dsgn dropdown			

		var stausFlagVal = getCheckedRadioValue("rdoActiveFlag");
		if(stausFlagVal == 1) // active
			document.frmEmpBasicInfo.rdoActiveFlag_gu[0].checked = true;
		if(stausFlagVal == 2) // deactive
			document.frmEmpBasicInfo.rdoActiveFlag_gu[1].checked = true;
		if(stausFlagVal == 3) // delete
			document.frmEmpBasicInfo.rdoActiveFlag_gu[2].checked = true;
			
		document.frmEmpBasicInfo.rdoActiveFlag_gu[0].disabled = true;
		document.frmEmpBasicInfo.rdoActiveFlag_gu[1].disabled = true;
		document.frmEmpBasicInfo.rdoActiveFlag_gu[2].disabled = true;		
				
		//document.getElementById('selStatusFlag_gu').value = document.getElementById('selStatusFlag').value;			
		//document.frmEmpBasicInfo.selStatusFlag_gu.disabled = true;
		
		document.getElementById('txtGpfNo_gu').value = document.getElementById('txtGpfNo').value;
		document.frmEmpBasicInfo.txtGpfNo_gu.disabled = true;
		
	}
	
	function copyGujToEng()
	{	
		/* if('${selectedUserId}'=='0') // it means insert mode (add)
		{
			document.getElementById('Salutation').value = document.getElementById('Salutation_gu').value;
			document.getElementById('emp_first_name_eng').value = document.getElementById('emp_first_name_guj').value;
			document.getElementById('emp_middle_name_eng').value = document.getElementById('emp_middle_name_guj').value;
			document.getElementById('emp_last_name_eng').value = document.getElementById('emp_last_name_guj').value;
		} */	
		document.getElementById('selClass').value = document.getElementById('selClass_gu').value;
		document.frmEmpBasicInfo.selClass.disabled = true;
		
		document.getElementById('DOB').value = document.getElementById('DOB_gu').value;
		document.frmEmpBasicInfo.DOB.disabled = true;
		
		document.getElementById('DOJ').value = document.getElementById('DOJ_gu').value;
		document.frmEmpBasicInfo.DOJ.disabled = true;
		
		document.getElementById('DOR').value = document.getElementById('DOR_gu').value;
		document.frmEmpBasicInfo.DOR.disabled = true;
		
		document.getElementById('dtStartDate').value = document.getElementById('dtStartDate_gu').value;
		document.frmEmpBasicInfo.dtStartDate.disabled = true;
		
		document.getElementById('dtEndDate').value = document.getElementById('dtEndDate_gu').value;
		document.frmEmpBasicInfo.dtEndDate.disabled = true;
		
		document.getElementById('selEmpSrvcStatus').value = document.getElementById('selEmpSrvcStatus_gu').value;
		document.frmEmpBasicInfo.selEmpSrvcStatus.disabled = true;
		
		document.getElementById('selDesignation').value = document.getElementById('selDesignation_gu').value; // 25 June
		document.frmEmpBasicInfo.selDesignation.disabled = true; // 25 June eng & guj dsgn dropdown

		
		var stausFlagVal = getCheckedRadioValue("rdoActiveFlag_gu");
		if(stausFlagVal == 1) // active
			document.frmEmpBasicInfo.rdoActiveFlag[0].checked = true;
		if(stausFlagVal == 2) // deactive
			document.frmEmpBasicInfo.rdoActiveFlag[1].checked = true;
		if(stausFlagVal == 3) // delete
			document.frmEmpBasicInfo.rdoActiveFlag[2].checked = true;	
					
		document.frmEmpBasicInfo.rdoActiveFlag[0].disabled = true;
		document.frmEmpBasicInfo.rdoActiveFlag[1].disabled = true;
		document.frmEmpBasicInfo.rdoActiveFlag[2].disabled = true;	
				
		//document.getElementById('selStatusFlag').value = document.getElementById('selStatusFlag_gu').value;			
		//document.frmEmpBasicInfo.selStatusFlag.disabled = true;
		
		document.getElementById('txtGpfNo').value = document.getElementById('txtGpfNo_gu').value;
		document.frmEmpBasicInfo.txtGpfNo.disabled = true;
		
	}
	
	function getCheckedRadioValue(radioName)
	{
		var radioValue = "";
		
		objRadio = eval("document.getElementsByName(\""+ radioName +"\")");
		
		for (iter=0;iter<objRadio.length;iter++)
		{
			if (objRadio[iter].checked)
			{
				radioValue = objRadio[iter].value;
				break;			
			}
		}
			
		return 	radioValue;
	}
	
	function compareStartDateToEndDate()
	{	
		if('${langId}' == '1')	
		{
			if(document.getElementById("dtStartDate").value!="" && document.getElementById("dtEndDate").value!="")
			{			
				var lFrmDate=document.getElementById("dtStartDate").value;							
				var lToDate=document.getElementById("dtEndDate").value;					
				if(compareDate(lFrmDate,lToDate) < 0 )
				{
					alert("<fmt:message bundle='${userPostLables}' key='ValidEndDate'/>");
					document.getElementById('dtEndDate').value='';					
				} 
			}
		}
		else if('${langId}' == '2')
		{
			if(document.getElementById("dtStartDate_gu").value!="" && document.getElementById("dtEndDate_gu").value!="")
			{			
				var lFrmDate=document.getElementById("dtStartDate_gu").value;							
				var lToDate=document.getElementById("dtEndDate_gu").value;					
				if(compareDate(lFrmDate,lToDate) < 0 )
				{
					alert("<fmt:message bundle='${userPostLables}' key='ValidEndDate'/>");
					document.getElementById('dtEndDate_gu').value='';					
				} 
			}
		}
	}
	
	function compareStartEndDates(startDate, EndDate, flag)
	{
		if(eval("document.getElementById(\""+ startDate +"\").value") !="" && eval("document.getElementById(\""+ EndDate +"\").value") != "")
		{			
			var lFrmDate=eval("document.getElementById(\""+ startDate +"\").value");					
			var lToDate=eval("document.getElementById(\""+ EndDate +"\").value");			
			if(compareDate(lFrmDate,lToDate) < 0 )
			{
				if(flag == 'dobdoj')
				{
					alert("<fmt:message bundle='${userPostLables}' key='DOJgrtDOB'/>");
				}
				if(flag == 'dojdor')
				{
					alert("<fmt:message bundle='${userPostLables}' key='DORgrtDOJ'/>");
				}
				
				var dateObj = eval("document.getElementById(\""+ EndDate +"\")");	
				dateObj.value = "";			
			}
		}
	}
	
	//start change by sunil 
	var dor='';
	var dob='';
	var classType='0';
	var doj='';
	function retdate()
	{
		if('${langId}' == '1')	
		{
			classType= document.frmEmpBasicInfo.selClass.value;
			dob=document.frmEmpBasicInfo.DOB.value;
			doj=document.frmEmpBasicInfo.DOJ.value;
		}
		else if('${langId}' == '2')
		{
			classType= document.frmEmpBasicInfo.selClass_gu.value;
			dob=document.frmEmpBasicInfo.DOB_gu.value;
			doj=document.frmEmpBasicInfo.DOJ_gu.value;
		}
		if(classType!='0')
		{
			if(dob!='')
			{
				var splitDate=dob.split("/");							
				var dobDay=splitDate[0];
				var dobMon=splitDate[1];
				var dobYear=parseInt(splitDate[2]);
				
				if(classType=='Cls4')
				{
					dobYear=dobYear+60;
				}
				else
				{	
					dobYear=dobYear+58;
				}
				dor=dobDay+'/'+dobMon+'/'+dobYear;
				document.frmEmpBasicInfo.hdnDOR.value=dor;
				if(doj!='')
				{
					if('${langId}' == '1')	
					{
						document.frmEmpBasicInfo.DOR.value=dor;
					}
					else if('${langId}' == '2')
					{	
						document.frmEmpBasicInfo.DOR_gu.value=dor;
					}
				}
			}
		}
		else
		{			
			if('${langId}' == '1')	
			{
				document.frmEmpBasicInfo.DOR.value='';
			}
			else if('${langId}' == '2')
			{	
				document.frmEmpBasicInfo.DOR_gu.value='';
			}
		}
	}
	function setRetDate()
	{
		var reDate=document.frmEmpBasicInfo.hdnDOR.value;
		if(reDate!='')
		{
			if('${langId}' == '1')	
			{
				document.frmEmpBasicInfo.DOR.value=reDate;
			}
			else if('${langId}' == '2')
			{	
				document.frmEmpBasicInfo.DOR_gu.value=reDate;
			}
		}
	}
    
    function checkDateWithin()//change by sunil on 03/06/08
	{
		var beginDate,endDate,checkDate,checkStatus;
		
		if('${langId}' == '1')	
		{
			beginDate=document.getElementById("DOB").value;
			endDate=document.getElementById("DOR").value;	
			checkDate=document.getElementById("DOJ").value;	
		}
		else if('${langId}' == '2')
		{	
			beginDate=document.getElementById("DOB_gu").value;
			endDate=document.getElementById("DOR_gu").value;	
			checkDate=document.getElementById("DOJ_gu").value;	
		}
								
	    checkStatus=true;
		if(beginDate!='' && endDate!='' && checkDate!='')
		{
			if(compareDate(beginDate,checkDate) < 0 || compareDate(checkDate,endDate)<0 )		
			{			
				alert("<fmt:message bundle='${userPostLables}' key='DOJbetDOBnDOR'/>");	
				if('${langId}' == '1')	
				{
					document.getElementById("DOJ").value='';
				}
				else if('${langId}' == '2')
				{
					document.getElementById("DOJ_gu").value='';
				}
				return false;	
			}			
		}
	}
	//Ends changes by sunil
</script>
</head>
<body>

<hdiits:form name="frmEmpBasicInfo" validate="true" method="POST" action="">
<hdiits:hidden name="source" default='<%=request.getParameter("source")%>'/> 
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<c:if test="${langId == 1}">
				<li class="selected"><a href="#" rel="tcontent1" ><b><fmt:message key="EIS.English" bundle="${userPostLables}"></fmt:message></b></a></li>
				<li><a href="#" rel="tcontent2" onfocus="copyEngToGuj()"><b><fmt:message key="EIS.Gujrati" bundle="${userPostLables}"></fmt:message></b></a></li>
			</c:if>
			<c:if test="${langId == 2}">
				<li class="selected"><a href="#" rel="tcontent1" ><b><fmt:message key="EIS.Gujrati" bundle="${userPostLables}"></fmt:message></b></a></li>
				<li><a href="#" rel="tcontent2" onfocus="copyGujToEng()"><b><fmt:message key="EIS.English" bundle="${userPostLables}"></fmt:message></b></a></li>
			</c:if>
		</ul>
	</div>
		
	<div id="empEntry" class="tabcontentstyle">
	<c:if test="${langId == 1}">
		<div id="tcontent1" class="tabcontent" tabno="0">
	</c:if>
	<c:if test="${langId == 2}">
		<div id="tcontent2" class="tabcontent" tabno="1">
	</c:if>
		<table width="100%" id="newUser">
				<tr width="100%" align="center"><td><b><fmt:message key="EIS.NewUser" bundle="${userPostLables}"></fmt:message></b></td></tr>
			</table>
			<table width="100%" id="editUser" style="display:none">
				<tr width="100%" align="center"><td><b><fmt:message key="EIS.EditUser" bundle="${userPostLables}"></fmt:message></b></td></tr>
			</table>
		<table class="tabtable" width="100%" >
		
		<tr>
			<td class="fieldLabel" >
				<b><hdiits:caption captionid="eis.EMP_SALUTATION" bundle="${userPostLables}"></hdiits:caption></b>
			</td>
			<td>
				<hdiits:text name="Salutation" id="Salutation"
				maxlength="6" captionid="eis.EMP_SALUTATION" size="2"
				bundle="${userPostLables}" validation = "txt.isrequired" default="${empInfoDtlsEng.empPrefix}" mandatory="true"/>
			</td>
			<td>
			</td>
			<td>
			</td>
		</tr>
		<tr>
			<td width="20%">
			</td>
			<td class="fieldLabel"><b><hdiits:caption captionid="eis.FIRST_NAME" bundle="${userPostLables}"></hdiits:caption></b>
			</td>
			<td class="fieldLabel" ><b><hdiits:caption captionid="eis.MIDDLE_NAME" bundle="${userPostLables}"></hdiits:caption></b></td>
			<td class="fieldLabel" ><b><hdiits:caption captionid="eis.LAST_NAME" bundle="${userPostLables}"></hdiits:caption></b></td>
		</tr>		
		<tr>
			<td>
				<b><hdiits:caption	captionid="NAME_EN" bundle="${userPostLables}"></hdiits:caption></b>
			</td>
			
			<td >
				<hdiits:text name="emp_first_name_eng" id="emp_first_name_eng" captionid="eis.FIRST_NAME" bundle="${userPostLables}" maxlength="30" validation="txt.isrequired" default="${empInfoDtlsEng.empFname}" mandatory="true"/>
			</td>
			<td>
				<hdiits:text name="emp_middle_name_eng" id="emp_middle_name_eng" captionid="eis.MIDDLE_NAME" bundle="${userPostLables}" maxlength="30" validation="txt.isrequired" default="${empInfoDtlsEng.empMname}" mandatory="true"/>
			</td>
			<td>
				<hdiits:text name="emp_last_name_eng" id="emp_last_name_eng" captionid="eis.LAST_NAME" bundle="${userPostLables}" maxlength="30" validation="txt.isrequired" default="${empInfoDtlsEng.empLname}" mandatory="true"/>
			</td>
		</tr>
		</table>
		<table class="tabtable" width="100%">
		<tr id="classRow">
			<td class="fieldLabel" width="20%">
				<b><hdiits:caption captionid="CLASS" bundle="${userPostLables}"/></b>
			</td>
			<td>				
				<hdiits:select name="selClass" id="selClass" captionid="CLASS" bundle="${userPostLables}" mandatory="true" validation="sel.isrequired" onchange="retdate();">
					<option value="0"><fmt:message key="admin.selectEng" bundle="${userPostLables}"/></option>
					
					<c:forEach var="classes_en" items="${classDtls_en}">
						<c:choose>
							<c:when test="${classes_en.gradeCode == empInfoDtlsEng.orgGradeMst.gradeCode}">
									
								<option value="<c:out value="${classes_en.gradeCode}"/>"
									selected="true"><c:out
									value="${classes_en.gradeName}" /></option>
							</c:when>
	
							<c:otherwise>
								<option value="<c:out value="${classes_en.gradeCode}"/>">
							<c:out value="${classes_en.gradeName}" /></option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</hdiits:select>				
			</td>
			<td></td>
			<td></td>
		</tr>
		
		<tr>
			<td class="fieldLabel" width="20%">
				<b><hdiits:caption	captionid="eis.DATE_OF_BIRTH" bundle="${userPostLables}"></hdiits:caption></b>
			</td>
			<td >
				<hdiits:dateTime name="DOB" captionid="eis.DATE_OF_BIRTH" bundle="${userPostLables}" default="${empInfoDtls.empDob}" afterDateSelect="compareStartEndDates('DOB', 'DOJ','dobdoj');retdate();checkDateWithin();" mandatory="true" validation="txt.isdt,txt.isrequired" disabled="${langId == '1' ? false : true}" />
			</td>
			<td ><hdiits:caption captionid="DSGN" bundle="${userPostLables}"></hdiits:caption></td>
			<td><hdiits:select captionid="DSGN" bundle="${userPostLables}" name="selDesignation" id="selDesignation" sort="false" validation="sel.isrequired" mandatory="true">
					<option value="0">
						<fmt:message key="admin.selectEng" bundle="${userPostLables}" />
					</option>					
					<c:forEach var="dsgnEng" items="${lstDsgnEng}">
					<c:choose>
						<c:when test="${dsgnEng.dsgnCode == dsgnCode}">
								
							<option value="<c:out value="${dsgnEng.dsgnCode}"/>"
								selected="selected"><c:out
								value="${dsgnEng.dsgnName}" /></option>
						</c:when>

						<c:otherwise>
							<option value="<c:out value="${dsgnEng.dsgnCode}"/>">
						<c:out value="${dsgnEng.dsgnName}" /></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				</hdiits:select>
			</td>	
		</tr>
		<tr>
			<td class="fieldLabel" width="20%">
				<b><hdiits:caption	captionid="eis.DATE_OF_JOINING" bundle="${userPostLables}"></hdiits:caption></b>
			</td>
			<td>
				<hdiits:dateTime name="DOJ" captionid="eis.DATE_OF_JOINING" bundle="${userPostLables}" afterDateSelect="setRetDate();checkDateWithin();" default="${empInfoDtls.empDoj}" mandatory="true" validation="txt.isdt,txt.isrequired" disabled="${langId == '1' ? false : true}" />
			</td>
			<td class="fieldLabel">
				<b><hdiits:caption	captionid="eis.LEAVE_DATE" bundle="${userPostLables}"></hdiits:caption></b>
			</td>
			<td >
				<hdiits:dateTime name="DOR" captionid="eis.LEAVE_DATE" bundle="${userPostLables}" afterDateSelect="compareStartEndDates('DOJ', 'DOR','dojdor');checkDateWithin();" default="${empInfoDtls.empSrvcExp}" maxvalue="31/12/2099" disabled="${langId == '1' ? false : true}" />
			</td>
		</tr>
		<tr>
			<td class="fieldLabel" width="20%">
				<b><hdiits:caption	captionid="EIS.StartDate" bundle="${userPostLables}"></hdiits:caption></b>
			</td>
			<td>
				<hdiits:dateTime name="dtStartDate" captionid="EIS.StartDate" bundle="${userPostLables}" afterDateSelect="compareStartDateToEndDate();" maxvalue="31/12/2099" default="${empInfoDtls.startDate}" mandatory="true" validation="txt.isdt,txt.isrequired" disabled="${langId == '1' ? false : true}" />
			</td>
			<td class="fieldLabel" >
				<b><hdiits:caption	captionid="EIS.ToDate" bundle="${userPostLables}"></hdiits:caption></b>
			</td>
			<td>
				<hdiits:dateTime name="dtEndDate" captionid="EIS.ToDate" bundle="${userPostLables}" afterDateSelect="compareStartDateToEndDate();" default="${empInfoDtls.endDate}" maxvalue="31/12/2099" disabled="${langId == '1' ? false : true}" />
			</td>
		</tr>
		<tr>
			<td><hdiits:caption	captionid="EIS.EmplSrvcStatus" bundle="${userPostLables}"></hdiits:caption></td>
			<td>
				<hdiits:select name="selEmpSrvcStatus" id="selEmpSrvcStatus" captionid="EIS.EmplSrvcStatus" bundle="${userPostLables}" mandatory="true" validation="sel.isrequired">
					<option value="0"><fmt:message key="admin.selectEng" bundle="${userPostLables}"/></option>
					<c:forEach var="empSrvc_en" items="${arEmpSrvcFlag_en}">
						<option value="<c:out value="${empSrvc_en.lookupName}"/>">
						<c:out value="${empSrvc_en.lookupDesc}" /></option>
					</c:forEach>
				</hdiits:select>				
			</td>
			<td align="left"><hdiits:caption captionid="EIS.ActiveFlag" bundle="${userPostLables}"></hdiits:caption></td>
			<td>				
				<c:forEach var="StatusFlagValue" items="${arActivateFlag_gu}">
					<hdiits:radio name="rdoActiveFlag" value="${StatusFlagValue.lookupId}" />${StatusFlagValue.lookupName}<b><label class="mandatoryindicator">*</label></b><br>										
				</c:forEach>		
			</td>
		</tr>
		<tr>
			<td style="display: none;"><hdiits:caption	captionid="EIS.StatusFlag" bundle="${userPostLables}"></hdiits:caption></td>
			<td style="display: none;">				
				<hdiits:select name="selStatusFlag" id="selStatusFlag" captionid="EIS.StatusFlag" bundle="${userPostLables}" mandatory="true" default="${gradeCode}" validation="sel.isrequired">
					<option value="0"><fmt:message key="admin.selectEng" bundle="${userPostLables}"/></option>
					<c:forEach var="statusFlag_en" items="${arStatus_en}">
						<option value="<c:out value="${statusFlag_en.lookupName}"/>">
						<c:out value="${statusFlag_en.lookupName}" /></option>
					</c:forEach>
				</hdiits:select>				
			</td>
			<td><hdiits:caption	captionid="EIS.GpfNo" bundle="${userPostLables}"></hdiits:caption></td>
			<td><hdiits:text name="txtGpfNo" id="txtGpfNo" default="${empInfoDtlsEng.empGPFnumber}" maxlength="40"/></td>
		</tr>
	</table>
	
	
	</div>
	<hdiits:hidden name="userId" id="userId" default="${selectedUserId}"></hdiits:hidden>
	<c:if test="${langId == 1}">
		<div id="tcontent2" class="tabcontent" tabno="1">
	</c:if>
	<c:if test="${langId == 2}">
		<div id="tcontent1" class="tabcontent" tabno="0">
	</c:if>
	
	<table width="100%" id="newUser_gu">
		<tr width="100%" align="center"><td><b><fmt:message key="EIS.NewUser" bundle="${userPostLables}"></fmt:message></b></td></tr>
	</table>
	<table width="100%" id="editUser_gu" style="display:none">
		<tr width="100%" align="center"><td><b><fmt:message key="EIS.EditUser" bundle="${userPostLables}"></fmt:message></b></td></tr>
	</table>
	<table class="tabtable" width="100%" >		
		<tr>
			<td class="fieldLabel" >
				<b><hdiits:caption captionid="eis.EMP_SALUTATION" bundle="${userPostLables}"></hdiits:caption></b>
			</td>
			<td>
				<hdiits:text name="Salutation_gu" id="Salutation_gu" maxlength="6" captionid="eis.EMP_SALUTATION" size="2"
				bundle="${userPostLables}" validation = "txt.isrequired" default="${empInfoDtlsGuj.empPrefix}" mandatory="true"/>
			</td>
			<td>
			</td>
			<td>
			</td>
		</tr>
		<tr>
			<td width="20%">
			</td>
			<td class="fieldLabel"><b><hdiits:caption captionid="eis.FIRST_NAME" bundle="${userPostLables}"></hdiits:caption></b>
			</td>
			<td class="fieldLabel" ><b><hdiits:caption captionid="eis.MIDDLE_NAME" bundle="${userPostLables}"></hdiits:caption></b></td>
			<td class="fieldLabel" ><b><hdiits:caption captionid="eis.LAST_NAME" bundle="${userPostLables}"></hdiits:caption></b></td>
		</tr>		
		<tr>
			<td>
				<b><hdiits:caption	captionid="NAME_GU" bundle="${userPostLables}"></hdiits:caption></b>
			</td>
			<td >
				<hdiits:text name="emp_first_name_guj" id="emp_first_name_guj" captionid="eis.FIRST_NAME" bundle="${userPostLables}" maxlength="30" validation="txt.isrequired" default="${empInfoDtlsGuj.empFname}" mandatory="true"/>
			</td>
			<td >
				<hdiits:text name="emp_middle_name_guj" id="emp_middle_name_guj" captionid="eis.MIDDLE_NAME" bundle="${userPostLables}" maxlength="30" validation="txt.isrequired" default="${empInfoDtlsGuj.empMname}" mandatory="true"/>
			</td>
			<td >
				<hdiits:text name="emp_last_name_guj" id="emp_last_name_guj" captionid="eis.LAST_NAME" bundle="${userPostLables}" maxlength="30" validation="txt.isrequired" default="${empInfoDtlsGuj.empLname}" mandatory="true"/>
			</td>
		</tr>
	</table>
	<table class="tabtable" width="100%" >	
		<tr id="classRow_gu">
			<td class="fieldLabel" width="20%">
				<b><hdiits:caption captionid="CLASS" bundle="${userPostLables}"/></b>
			</td>
			<td>				
				<hdiits:select name="selClass_gu" id="selClass_gu" captionid="CLASS" bundle="${userPostLables}" mandatory="true" default="${gradeCode}" validation="sel.isrequired" onchange="retdate();">
					<option value="0"><fmt:message key="admin.selectGuj" bundle="${userPostLables}"/></option>
					<c:forEach var="classes_gu" items="${classDtls_gu}">
						<c:choose>
							<c:when test="${classes_gu.gradeCode == empInfoDtls.orgGradeMst.gradeCode}">
									
								<option value="<c:out value="${classes_gu.gradeCode}"/>"
									selected="selected"><c:out
									value="${classes_gu.gradeName}" /></option>
							</c:when>
	
							<c:otherwise>
								<option value="<c:out value="${classes_gu.gradeCode}"/>">
							<c:out value="${classes_gu.gradeName}" /></option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</hdiits:select>				
			</td>
			<td></td>
			<td></td>
		</tr>
		
		<tr>
			<td class="fieldLabel" width="20%">
				<b><hdiits:caption	captionid="eis.DATE_OF_BIRTH" bundle="${userPostLables}"></hdiits:caption></b>
			</td>
			<td >
				<hdiits:dateTime name="DOB_gu" captionid="eis.DATE_OF_BIRTH" bundle="${userPostLables}" default="${empInfoDtls.empDob}" afterDateSelect="compareStartEndDates('DOB_gu', 'DOJ_gu','dobdoj');retdate();checkDateWithin();" mandatory="true" validation="txt.isdt,txt.isrequired" disabled="${langId == '1' ? true : false}" />
			</td>
			<td ><hdiits:caption captionid="DSGN" bundle="${userPostLables}"></hdiits:caption></td>
			<td><hdiits:select captionid="DSGN" bundle="${userPostLables}" name="selDesignation_gu" id="selDesignation_gu" sort="false" default="${dsgnCode}" validation="sel.isrequired" mandatory="true">
					<option value="0">
						<fmt:message key="admin.selectGuj" bundle="${userPostLables}" />
					</option>
					<c:forEach var="dsgnGuj" items="${lstDsgnGuj}">
						<option value="<c:out value="${dsgnGuj.dsgnCode}"/>">
						<c:out value="${dsgnGuj.dsgnName}" /></option>
					</c:forEach>
					<c:forEach var="dsgnGuj" items="${lstDsgnGuj}">
						<c:choose>
							<c:when test="${dsgnGuj.dsgnCode == dsgnCode}">
									
								<option value="<c:out value="${dsgnGuj.dsgnCode}"/>"
									selected="selected"><c:out
									value="${dsgnGuj.dsgnName}" /></option>
							</c:when>
	
							<c:otherwise>
								<option value="<c:out value="${dsgnGuj.dsgnCode}"/>">
							<c:out value="${dsgnGuj.dsgnName}" /></option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</hdiits:select>
			</td>
		</tr>
		<tr>
			<td class="fieldLabel" width="20%">
				<b><hdiits:caption	captionid="eis.DATE_OF_JOINING" bundle="${userPostLables}"></hdiits:caption></b>
			</td>
			<td>
				<hdiits:dateTime name="DOJ_gu" captionid="eis.DATE_OF_JOINING" bundle="${userPostLables}" afterDateSelect="setRetDate();checkDateWithin();" default="${empInfoDtls.empDoj}" mandatory="true" validation="txt.isdt,txt.isrequired" disabled="${langId == '1' ? true : false}"/>
			</td>
			<td class="fieldLabel">
				<b><hdiits:caption	captionid="eis.LEAVE_DATE" bundle="${userPostLables}"></hdiits:caption></b>
			</td>
			<td >
				<hdiits:dateTime name="DOR_gu" captionid="eis.LEAVE_DATE" bundle="${userPostLables}" afterDateSelect="compareStartEndDates('DOJ_gu', 'DOR_gu','dojdor');checkDateWithin();" default="${empInfoDtls.empSrvcExp}" maxvalue="31/12/2099" disabled="${langId == '1' ? true : false}"/>
			</td>
		</tr>
		<tr>
			<td class="fieldLabel" width="20%">
				<b><hdiits:caption	captionid="EIS.StartDate" bundle="${userPostLables}"></hdiits:caption></b>
			</td>
			<td>
				<hdiits:dateTime name="dtStartDate_gu" captionid="EIS.StartDate" bundle="${userPostLables}" afterDateSelect="retdate()" default="${empInfoDtls.startDate}" mandatory="true" validation="txt.isdt,txt.isrequired" maxvalue="31/12/2099" disabled="${langId == '1' ? true : false}"/>
			</td>
			<td class="fieldLabel" >
				<b><hdiits:caption	captionid="EIS.ToDate" bundle="${userPostLables}"></hdiits:caption></b>
			</td>
			<td >
				<hdiits:dateTime name="dtEndDate_gu" captionid="EIS.ToDate" bundle="${userPostLables}" default="${empInfoDtls.endDate}" maxvalue="31/12/2099" disabled="${langId == '1' ? true : false}"/>
			</td>
		</tr>
		<tr>
			<td><hdiits:caption	captionid="EIS.EmplSrvcStatus" bundle="${userPostLables}"></hdiits:caption></td>
			<td>				
				<hdiits:select name="selEmpSrvcStatus_gu" id="selEmpSrvcStatus_gu" captionid="EIS.EmplSrvcStatus" bundle="${userPostLables}" mandatory="true" validation="sel.isrequired">
					<option value="0"><fmt:message key="admin.selectGuj" bundle="${userPostLables}"/></option>
					<c:forEach var="empSrvc_gu" items="${arEmpSrvcFlag_gu}">
						<option value="<c:out value="${empSrvc_gu.lookupName}"/>">
						<c:out value="${empSrvc_gu.lookupDesc}" /></option>
					</c:forEach>
				</hdiits:select>				
			</td>
			<td align="left"><hdiits:caption	captionid="EIS.ActiveFlag" bundle="${userPostLables}"></hdiits:caption></td>
			<td>				
				<c:forEach var="StatusFlagValue" items="${arActivateFlag_gu}">
					<hdiits:radio name="rdoActiveFlag_gu" value="${StatusFlagValue.lookupId}" />${StatusFlagValue.lookupName}<b><label class="mandatoryindicator">*</label></b><br>										
				</c:forEach>				
			</td>
		</tr>
		<tr>
			<td style="display: none;"><hdiits:caption	captionid="EIS.StatusFlag" bundle="${userPostLables}"></hdiits:caption></td>
			<td style="display: none;">				
				<hdiits:select name="selStatusFlag_gu" id="selStatusFlag_gu" captionid="EIS.StatusFlag" bundle="${userPostLables}" mandatory="true" default="${gradeCode}" validation="sel.isrequired">
					<option value="0"><fmt:message key="admin.selectGuj" bundle="${userPostLables}"/></option>
					<c:forEach var="statusFlag_gu" items="${arStatus_gu}">
						<option value="<c:out value="${statusFlag_gu.lookupName}"/>">
						<c:out value="${statusFlag_gu.lookupName}" /></option>
					</c:forEach>
				</hdiits:select>				
			</td>
			<td><hdiits:caption	captionid="EIS.GpfNo" bundle="${userPostLables}"></hdiits:caption></td>
			<td><hdiits:text name="txtGpfNo_gu" id="txtGpfNo_gu" default="${empInfoDtlsGuj.empGPFnumber}" maxlength="40"/></td>
		</tr>
	</table>
	
</div>
<table align="center">	
		<tr>
			<td align="center">
				<c:if test="${empty empInfoDtls}">
					<br></br><hdiits:button name="Submit" id= "update" type="button" captionid="SUBMIT" bundle="${userPostLables}" onclick="saveInfo()"></hdiits:button>
				</c:if>
				<c:if test="${not empty empInfoDtls}">
					<br></br><hdiits:button name="Update" id= "update" type="button" captionid="UPDATE" bundle="${userPostLables}" onclick="saveInfo()"></hdiits:button>
				</c:if>
			</td>
			<td align="center">
				<br></br><hdiits:button name="reset" type="button" captionid="RESET" bundle="${userPostLables}" onclick="resetData()"></hdiits:button>
			</td>
			<td align="center">
				<br></br><hdiits:button name="Close" type="button" captionid="CLOSE" bundle="${userPostLables}" onclick="closeWindow()"></hdiits:button>
			</td>
		</tr>
	</table>
</div>
<hdiits:hidden name="RedirectURL" default="${resValue.RedirectURL}" />
<hdiits:hidden name="candidateId" default="${resValue.candidateId}" />
<hdiits:hidden name="langId" id="langId" default="${langId}" ></hdiits:hidden>
<hdiits:hidden name="hdnDOR" id="hdnDOR"></hdiits:hidden><!--  changes by sunil -->
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'
			controlNames="Salutation,emp_first_name_eng,emp_middle_name_eng,emp_last_name_eng,selClass,DOB,DOJ,dtStartDate,selEmpSrvcStatus,selActiveFlag,emp_first_name_guj,emp_middle_name_guj,emp_last_name_guj,Salutation_gu,selClass_gu,DOB_gu,DOJ_gu,dtStartDate_gu,selEmpSrvcStatus_gu,selActiveFlag_gu" />
	
<script type="text/javascript">
	
	initializetabcontent("maintab");
	
</script>

</hdiits:form>
</body>

<script>
	document.frmEmpBasicInfo.DOB.readOnly = true;
	document.frmEmpBasicInfo.DOJ.readOnly = true;
	document.frmEmpBasicInfo.DOR.readOnly = true;
	document.frmEmpBasicInfo.dtStartDate.readOnly = true;
	document.frmEmpBasicInfo.dtEndDate.readOnly = true;
	
	document.frmEmpBasicInfo.DOB_gu.readOnly = true;
	document.frmEmpBasicInfo.DOJ_gu.readOnly = true;
	document.frmEmpBasicInfo.DOR_gu.readOnly = true;
	document.frmEmpBasicInfo.dtStartDate_gu.readOnly = true;
	document.frmEmpBasicInfo.dtEndDate_gu.readOnly = true;
	
	if('${flag}' == 'other')
	{
		document.getElementById('classRow').style.display = 'none';	
		document.getElementById('classRow_gu').style.display = 'none';
		document.getElementById('selClass').value = 'Cls4';
		document.getElementById('selClass_gu').value = 'Cls4';		
	}
	if('${selectedUserId}' != '0')
	{
		// document.getElementById('selActiveFlag').value='${empInfoDtlsEng.activateFlag}';
		// document.getElementById('selActiveFlag_gu').value='${empInfoDtlsGuj.activateFlag}';	
		
		document.getElementById('editUser').style.display = '';	
		document.getElementById('editUser_gu').style.display = '';
		
		document.getElementById('newUser').style.display = 'none';	
		document.getElementById('newUser_gu').style.display = 'none';
		
		var engActiveStatus = '${empInfoDtlsEng.activateFlag}';
		var gujActiveStatus = '${empInfoDtlsGuj.activateFlag}';
		
		if(engActiveStatus == 1 || gujActiveStatus == 1) // active
		{
			document.frmEmpBasicInfo.rdoActiveFlag[0].checked = true;
			document.frmEmpBasicInfo.rdoActiveFlag_gu[0].checked = true;
		}
		if(engActiveStatus == 2 || gujActiveStatus == 2) // deactive
		{
			document.frmEmpBasicInfo.rdoActiveFlag[1].checked = true;
			document.frmEmpBasicInfo.rdoActiveFlag_gu[1].checked = true;
		}
		if(engActiveStatus == 3 || gujActiveStatus == 3) // delete
		{
			document.frmEmpBasicInfo.rdoActiveFlag[2].checked = true;
			document.frmEmpBasicInfo.rdoActiveFlag_gu[2].checked = true;	
		}
		
		document.getElementById('selEmpSrvcStatus').value='${strEmpSrvcCode}';
		document.getElementById('selEmpSrvcStatus_gu').value='${strEmpSrvcCode}';	
		
		//document.getElementById('selStatusFlag').value='${empInfoDtlsEng.orgUserMst.cmnLookupMst.lookupName}';
		//document.getElementById('selStatusFlag_gu').value='${empInfoDtlsGuj.orgUserMst.cmnLookupMst.lookupName}';
		
		document.frmEmpBasicInfo.selDesignation.value='${dsgnCode}'; // added 25 June
		document.frmEmpBasicInfo.selDesignation_gu.value='${dsgnCode}'; // added 25 June
		
		document.frmEmpBasicInfo.selClass.value='${empInfoDtlsEng.orgGradeMst.gradeCode}'; // added 26 June
		document.frmEmpBasicInfo.selClass_gu.value='${empInfoDtlsEng.orgGradeMst.gradeCode}'; // added 26 June	
		
	}	
	else
	{	
		document.frmEmpBasicInfo.rdoActiveFlag[0].checked = true;
		document.frmEmpBasicInfo.rdoActiveFlag_gu[0].checked = true;
	}
</script>

</html>
<%
	} catch (Exception e) 
	{
		e.printStackTrace();
	}
%>
