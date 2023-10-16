<%
try
{ 
%>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>

<hdiits:form name="parallelFileForm" validate="true" method="POST" action="./hdiits.htm" encType="multipart/form-data">
<hdiits:hidden name="postIdArray" />
	<br />
	<br />
	<div id="employeeSearchId">
		<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
			<jsp:param name="SearchEmployee" value="EMPLOYEESEARCH"/>
			<jsp:param name="formName" value="testing"/>
			<jsp:param name="mandatory" value="No"/>
			<jsp:param name="multiEmployeeSel" value="Yes"/>
			<jsp:param name="functionName" value="getEmployees"/>
			<jsp:param name="searchEmployeeTitle" value="Select Employees to send Parallel File"/>
		</jsp:include>
		<br>
		<table id="newEmpTable" class="datatable" style="display: none">
			<tr>
				<td class="datatableheader" >		
					Employee
				</td>
				<td class="datatableheader" >		
					Designation
				</td>	
			</tr>
		</table>
		<br>
		<table class="tabtable">
			<tr>
				<td class="fieldLabel" colspan="1" align="center">		
					<hdiits:button name="SubmitEmp" id="SubmitEmp" type="button" onclick="submitEmployees()" value="Send As Parallel File"></hdiits:button>		
				</td>
			</tr>
		</table>
	</div>
</hdiits:form>

<script type="text/javascript">
	var empIdLst = document.forms[0].Sel_Employee_POST_Array_EMPLOYEESEARCH.value;
	var empIdArry='';

	function getEmployees()
	{
		empIdLst = document.forms[0].Sel_Employee_POST_Array_EMPLOYEESEARCH.value;

		if((empIdLst=='EMPLOYEESEARCH') || (empIdLst==''))
		{
			alert("Please select some employees");
			return false;
		}
		else
		{
			var empNameLst = document.forms[0].Sel_Employee_NAME_Array_EMPLOYEESEARCH.value;
			var empDsgnLst = document.forms[0].Sel_Employee_DESIG_Array_EMPLOYEESEARCH.value;
			var empTable = document.getElementById('newEmpTable');
			empTable.style.display='';
			empNameArry = empNameLst.split(',');
			empDsgnArry = empDsgnLst.split(',');
			empIdArry = empIdLst.split(',');
			var rowNo = empTable.rows.length;
			for(var i=1;i<rowNo;i++)
			{
				empTable.deleteRow(1);
			}
			for(i=0;i<empNameArry.length;i++)
			{
				var row = empTable.insertRow();		
				
				var cell1=row.insertCell(0);
				var cell2=row.insertCell(1);
				
				cell1.innerHTML=empNameArry[i];
				cell2.innerHTML=empDsgnArry[i];
			}
			return true;
		}
	}

	function submitEmployees()
	{
		empIdLst = document.forms[0].Sel_Employee_POST_Array_EMPLOYEESEARCH.value;
		
		if((empIdLst=='EMPLOYEESEARCH') || (empIdLst==''))
		{
			alert("Please select some employees");
			return;
		}
		else
		{
			document.getElementById('postIdArray').value = empIdLst;
			var url = "hdiits.htm?actionFlag=sendFileAsParallel&fileId=${param.fileId}&docId=${param.docId}"; 
			document.forms[0].action=url;
			document.forms[0].submit();
		}
	}
</script>
<%
}
catch(Exception e)
{
	e.printStackTrace();
} 
%>