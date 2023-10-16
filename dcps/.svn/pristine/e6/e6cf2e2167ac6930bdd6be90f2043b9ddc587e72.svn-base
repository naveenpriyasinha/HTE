<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<c:set var="tdBGColor" value="#C9DFFF"></c:set>	
<c:set var="tableBGColor" value="#F0F4FB"></c:set>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>

<fmt:setBundle basename="resources.eis.eisLables" var="empSearchLables" scope="request" />
<fmt:setBundle basename="resources.hr.dept.deptLables" var="deptEnqLables" scope="request" />
<fmt:setBundle basename="resources.eis.empPreEmplLables" var="ProfDtlsLables" scope="request" />

<html>
	<head>
		<STYLE TYPE="text/css">
		  <!--
		  	#links a:link {
			  color: blue;
			  text-decoration: none;
			}
			
			#links a:visited {
				color: blue;
				 text-decoration: none;
			}
			
			#links a:hover {
			  color: red;
			  text-decoration: none;
			}
		  -->
  </STYLE>
		
		
    	<script language="javascript">
    	
    		var empArray = new Array();
    		
			function empSearch(from)
			{
				if (from.length == 0)
			    {
			    	alert('Select atleast one record');
			    	return false;
			    }
			    else if (from.length > 1)
			    {
				    alert('Only one record can be selected');
			    	return false;
			    }		       
			    
			    document.getElementById("hdnUserId").value = "";
				
				for(var i=0; i<from.length; i++)
				{	
					empArray[i] = from[i].split("~"); 	
				}
				
				var empRecord = empArray[0]; 
				
				document.frmEmployeeAppHome.txtEmployeeName.value = empRecord[0]; // Employee Id
				document.frmEmployeeAppHome.name_txtEmployeeName.value = empRecord[1]; // Employee Full Name
				document.frmEmployeeAppHome.hdnUserId.value = empRecord[2]; // user id
				
				document.getElementById("empName").innerHTML = empRecord[1]; // Employee Full Name
				document.getElementById("userName").innerHTML = empRecord[3]; // User Name
				document.getElementById("department").innerHTML = empRecord[11]; // Department Name
				document.getElementById("location").innerHTML = empRecord[9]; // Office Name
				document.getElementById("designation").innerHTML = empRecord[7]; // Designation
				document.getElementById("post").innerHTML = empRecord[5]; // Post
				
				document.getElementById("employeeLinks").style.display= "block";
			}
			
			function openAppWindow(actionFlag)
			{
				var userId = document.getElementById("hdnUserId").value;
				var empId = document.frmEmployeeAppHome.txtEmployeeName.value;
				
				var href = "hrms.htm?actionFlag="+  actionFlag + "&userId="+ userId + "&empId="+empId;
				
				objChildWindow = window.open(href,"Country","toolbar=no, location=no, directories=no,status=yes, menubar=no, scrollbars=yes, resizable=yes, top=10, left=10, width=1000, height=680, copyhistory=no");			
			    
			    // displayModalDialog (url ,title ,"toolbar=no, location=no, directories=no,status=yes, menubar=no, scrollbars=yes, resizable=yes, top=50, left=100, width="+ width +", height="+ height +", copyhistory=no");
			}
			
			function newUserCreation(actionFlag)
			{
				var href = "hrms.htm?actionFlag="+  actionFlag + "&userId=0";
				
				objChildWindow1 = window.open(href,"UserCreation","toolbar=no, location=no, directories=no,status=yes, menubar=no, scrollbars=yes, resizable=yes, top=10, left=10, width=1000, height=680, copyhistory=no");			
			    
			    // displayModalDialog (url ,title ,"toolbar=no, location=no, directories=no,status=yes, menubar=no, scrollbars=yes, resizable=yes, top=50, left=100, width="+ width +", height="+ height +", copyhistory=no");
			}
			
	    </script>
		
	</head>

	<body style="font-family: Verdana;">
		<hdiits:form name="frmEmployeeAppHome" validate="true" method="POST" action="./hrms.htm?actionFlag=submitData"> 
			<br>
			
			<div id="tabmenu">
				<ul id="maintab" class="shadetabs">
					<li class="selected" style="width: 220px;text-align:center;"><a href="#" rel="tcontent1" style="width: 210px;text-align:center;"><fmt:message key="EIS.EmpSearechInfo" bundle="${empSearchLables}"/></a></li>
				</ul>
			</div>
			
			<div class="tabcontentstyle" style="height: 100%">
				
				<div id="tcontent1" class="tabcontent" tabno="0" >
					
					<br><br>
					
					<table align="center">
						<tr>
							<td align="center"><b><hdiits:caption captionid="EIS.searchForEmployee" bundle="${empSearchLables}"/></b></td>
							<td><hdiits:search name="txtEmployeeName" url="hrms.htm?actionFlag=getEmpSearchSelData&multiple=false" readonly="true" size="50" /></td>
						</tr>
					</table>
					
					<br>
					
					<div id="links">
						<table align="center" cellspacing="10">
							<tr>
								<td align="center"><a href="#" onclick="javascript:newUserCreation('showEmpBasicInfo&workFlowEnabled=false')"><fmt:message  bundle="${empSearchLables}" key="EIS.NewUserCreation"/></a></td>
								<td align="center"><a href="#" onclick="javascript:openAppWindow('getVacancyAdminPage')">Sanction Post</a></td>	
								<td align="center"><a href="#" onclick="javascript:openAppWindow('showIncrEmpList&workFlowEnabled=false')"><fmt:message key="EIS.PayIncrement" bundle="${empSearchLables}"/></a></td>	
							</tr>
							<tr>		
								<td align="center"><a href="#" onclick="javascript:openAppWindow('showAdminPostDtl&multiLang=false')"><fmt:message key="EIS.NewPostCreation" bundle="${ProfDtlsLables}"/></a></td>	
								<td align="center"><a href="#" onclick="javascript:openAppWindow('reportService&reportCode=300230&action=generateReport&FromParaPage=TRUE')">Vacancy Report</a></td>	
								<td align="center"><a href="#" onclick="javascript:openAppWindow('viewEmpDtlForEnq')"><fmt:message key="dept.deptInq" bundle="${deptEnqLables}"/></a></td>	
							</tr>
						</table>
					</div>
					
					<br><br>
					
					<table id='empIno' name="empIno" border=1 borderColor="black" align="center" width="100%" cellpadding="1" cellspacing="1" style="border-collapse: collapse;background-color: ${tableBGColor}" >
						<tr>		
							<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="EIS.EmployeeName" bundle="${empSearchLables}"/></b></td>
							<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="EIS.UsrNm" bundle="${empSearchLables}"/></b></td>
							<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="EIS.Dept" bundle="${empSearchLables}"/></b></td>
							<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="EIS.Loctn" bundle="${empSearchLables}"/></b></td>
							<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="EIS.Designation" bundle="${empSearchLables}"/></b></td>
							<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="EIS.Post" bundle="${empSearchLables}"/></b></td>
						</tr>
						<tr>		
							<td align="center" id="empName"><label></label></td>
							<td align="center" id="userName"><label></label></td>
							<td align="center" id="department"><label></label></td>
							<td align="center" id="location"><label></label></td>
							<td align="center" id="designation"><label></label></td>
							<td align="center" id="post"><label></label></td>
						</tr>	
					</table>
					
					<br><br><br><br>
					
					<div id="links">
						<table id="employeeLinks" border=0 borderColor="black" align="center" width="70%" cellpadding="1" cellspacing="5" style="border-collapse: collapse;display:NONE" >
							<tr>		
								<td align="center" valign="top">
									
									<table id='empIno' name="empIno" border=1 borderColor="black" align="center" width="100%" cellpadding="1" cellspacing="1" style="border-collapse: collapse;background-color: ${tableBGColor}" >
										<tr>		
											<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="EIS.PersonalInfmation" bundle="${empSearchLables}"/></b></td>
										</tr>
										<tr>		
											<td align="center" id="empName"><a href="#" onclick="javascript:openAppWindow('addEditEmpInfo&flag=getComboDetails&workFlowEnabled=false')"><fmt:message  bundle="${empSearchLables}" key="EIS.PersonalDtls"/></a></td>
										</tr>	
										<tr>
											<td align="center" id="empName"><a href="#" onclick="javascript:openAppWindow('EducationDtls&flag=getComboDetails&workFlowEnabled=false')"><fmt:message  bundle="${empSearchLables}" key="EIS.EducatnDtls"/></a></td>
										</tr>	
										<tr>	
											<td align="center" id="empName"><a href="#" onclick="javascript:openAppWindow('FamilyDetails&flag=getComboDetails&workFlowEnabled=false')"><fmt:message key="EIS.FamilyDtls" bundle="${empSearchLables}"/></a></td>
										</tr>	
										<tr>	
											<td align="center" id="empName"><label><a href="#" onclick="javascript:openAppWindow('CoCurricularDetails&flag=getComboDetails&workFlowEnabled=false')"><fmt:message key="eis.extraCoCurricularDtls" bundle="${empSearchLables}"/></a></label></td>
										</tr>	
										<tr>	
											<td align="center" id="empName"><a href="#" onclick="javascript:openAppWindow('NomineeDetails&flag=getComboDetails&workFlowEnabled=false')"><fmt:message key="EIS.NomineeDtls" bundle="${empSearchLables}"/></a></td>	
										</tr>	
										<tr>	
											<td align="center" id="empName"><a href="#" onclick="javascript:openAppWindow('getEmpMiscellenousDtls&workFlowEnabled=false')"><fmt:message key="EIS.MiscDtls" bundle="${empSearchLables}"/></a></td>	
										</tr>
									</table>
									
								</td>
								<td align="center" valign="top">
									
									<table id='empIno' name="empIno" border=1 borderColor="black" align="center" width="100%" cellpadding="1" cellspacing="1" style="border-collapse: collapse;background-color: ${tableBGColor}" >
										<tr>		
											<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="EIS.ProfInfmation" bundle="${empSearchLables}"/></b></td>
										</tr>
										<tr>		
											<td align="center" id="empName"><a href="#" onclick="javascript:openAppWindow('getEmpPreEmplDtls&workFlowEnabled=false')"><fmt:message key="EIS.PrevEmplDtl" bundle="${ProfDtlsLables}"/></a></td>	
										</tr>
										<tr>		
											<td align="center" id="empName"><a href="#" onclick="javascript:openAppWindow('getEmpExamDetails&workFlowEnabled=false')"><fmt:message key="EIS.DeptSrvcExamnatnDetl" bundle="${empSearchLables}"/></a></td>	
										</tr>
									</table>
									
								</td>
								
								<td align="center" valign="top">
									
									<table id='empIno' name="empIno" border=1 borderColor="black" align="center" width="100%" cellpadding="1" cellspacing="1" style="border-collapse: collapse;background-color: ${tableBGColor}" >
										<tr>		
											<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="EIS.InServcDtls" bundle="${empSearchLables}"/></b></td>
										</tr>
										<tr>		
											<td align="center" id="empName"><a href="#" onclick="javascript:openAppWindow('showEmpBasicInfo&workFlowEnabled=false')"><fmt:message key="Join_Details" bundle="${empSearchLables}"/></a></td>	
										</tr> 
										<tr>		
											<td align="center" id="empName"><a href="#" onclick="javascript:openAppWindow('getEmpPrePstngDtls&workFlowEnabled=false')"><fmt:message key="EIS.PrsntEmplmntPosngDtls" bundle="${empSearchLables}"/></a></td>	
										</tr>
										<tr>		
											<td align="center" id="empName"><a href="#" onclick="javascript:openAppWindow('getEmpAdditnalChrgDtls&workFlowEnabled=false')"><fmt:message key="EIS.AdditionalCharge" bundle="${empSearchLables}"/></a></td>	
										</tr>
										<tr>		
											<td align="center" id="empName"><a href="#" onclick="javascript:openAppWindow('showEmpPayfixation&workFlowEnabled=false')"><fmt:message key="EIS.PayFixation" bundle="${empSearchLables}"/></a></td>	
										</tr>
									</table>
									
								</td>
							</tr>
						</table>
					</div>	
				
				</div>
			
			</div>
			
			<hdiits:hidden name="hdnUserId" id="hdnUserId" />
			
		</hdiits:form>
	</body>
</html>	

<script type="text/javascript">		
	initializetabcontent("maintab");
</script>
	
<%
	}

	catch (Exception e) {
		e.printStackTrace();
	}
%>
		