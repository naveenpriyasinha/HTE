<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<script type="text/javascript" src="script/common/calendar.js"></script>

<fmt:setBundle basename="resources.trng.TrainerMstLables" var="trnrSearchLable" scope="request"/>

<c:set var="resultObj" value="${result}" />
<c:set var="resultMap" value="${resultObj.resultValue}" />						 
<c:set var="empList" value="${resultMap.empList}" />
<c:set var="isRecordFound" value="${resultMap.isRecordFound}" />
<c:set var="counter" value="${resultMap.counter}" />
<c:set var="prevSeldEmpls" value="${empMap.seldEmployeeArray}" />

<fmt:message var="confirmMsg" key="TR.ALERT_MSG" bundle="${trnrSearchLable}" />
<fmt:message var="alertMsg" key="TR.SEARCH_CRITERIA_ALERT" bundle="${trnrSearchLable}" />

<script language="javascript">


var selectedEmplArray = new Array();
var oldSelEmps
if(window.opener.parent.document.getElementById('hidden_employeeSelArray') != null)
{
	oldSelEmps =  window.opener.parent.document.getElementById('hidden_employeeSelArray').value;
}
else
{
	oldSelEmps = parent.window.opener.document.getElementById('hidden_employeeSelArray').value;
}


if(oldSelEmps.length>0)
{
	selectedEmplArray = oldSelEmps.split(',');
}


var noOfEmp = 0;
var counter=${counter};


function editValueCheckBox(chkBox)
{
	var curSelChkBxValue = chkBox.value ;

	if(chkBox.checked)
	{
		var curSelSectCode = chkBox.value ;
		var tempArraySel = new Array(curSelChkBxValue);
		selectedEmplArray = selectedEmplArray.concat(tempArraySel);
		
		if(window.opener.parent.document.getElementById('hidden_employeeSelArray') != null)
		{
			window.opener.parent.document.getElementById('hidden_employeeSelArray').value = selectedEmplArray;
		}
		else
		{
			parent.window.opener.document.getElementById('hidden_employeeSelArray').value = selectedEmplArray;
		}
	}
	else
	{
			var tempEmplArray = new Array();
			for(var arrCtr = 0 ; arrCtr < selectedEmplArray.length ; arrCtr++)
			{
				if(selectedEmplArray[arrCtr]==curSelChkBxValue);
				else
				{
					var tempArrayDel = new Array(selectedEmplArray[arrCtr]);
					tempEmplArray = tempEmplArray.concat(tempArrayDel);
				}
			}
			selectedEmplArray = tempEmplArray;
			
			
			if(window.opener.parent.document.getElementById('hidden_employeeSelArray') != null)
			{
				window.opener.parent.document.getElementById('hidden_employeeSelArray').value = selectedEmplArray;
			}
			else
			{
				parent.window.opener.document.getElementById('hidden_employeeSelArray').value = selectedEmplArray;
			}
	}
}


function submitForm()
{
	var oldCounter = counter;
	for(var arrCtr = 0 ; arrCtr < selectedEmplArray.length ; arrCtr++)
	{
		var content = selectedEmplArray[arrCtr];
		var empArr  = content.split("*");
		var empId = empArr[0];
		var empName = empArr[1];
		var desi = empArr[2];
		var quali = empArr[3];
		var addStatus = true;
	
		for(var checkExistance = 1; checkExistance <= counter; checkExistance++)
		{
			if(window.opener.parent.document.getElementById("empId_"+checkExistance) != null)
			{
				if (window.opener.parent.document.getElementById("empId_"+checkExistance).value == empId)
				{
						addStatus = false;
						window.opener.parent.document.getElementById("NomNo"+checkExistance).style.display='';
						window.opener.parent.document.getElementById("chkbx_"+checkExistance).value="W";
				}
			}
			else
			{
				if (parent.window.opener.document.getElementById("empId_"+checkExistance).value == empId)
				{
					addStatus = false;
					parent.window.opener.document.getElementById("NomNo"+checkExistance).style.display='';
					parent.window.opener.document.getElementById("chkbx_"+checkExistance).value="W";
				}
			}
		}
	
		if(addStatus)
		{
			counter = counter + 1;
		
			if(window.opener.parent.document.getElementById("nomTable") != null)
			{
				var trow=window.opener.parent.document.getElementById("nomTable").insertRow();
			}
			else
			{
				var trow = parent.window.opener.document.getElementById("nomTable").insertRow();
			}
			trow.id = "NomNo"+counter;
		
			trow.insertCell(0).innerHTML = "<input type='hidden' name='Id_"+counter+"'  value='0' />"+counter;
			trow.insertCell(1).innerHTML = "<input type='hidden' name='empId_"+counter+"'  value='"+empId+"' />"+empName;
			trow.insertCell(2).innerHTML = "<input type='hidden' name='flag_"+counter+"'  value='A' />"+desi;
			trow.insertCell(3).innerHTML = quali;
			trow.insertCell(4).innerHTML =	"N/A";
			trow.insertCell(5).innerHTML =	"N/A";
			trow.insertCell(6).innerHTML =	"<textarea name='remarks_"+counter+"'  class='textareatag' ></textarea>"
			trow.insertCell(7).innerHTML =	"<input type='checkbox' name='reserved_"+counter+"' value='Y'  class='checkboxtag' />"
			trow.insertCell(8).innerHTML = "<input type='hidden' name='rowId"+counter+"' default='"+counter+"'/> <input type='hidden' id='chkbx_"+counter+"' name='chkbx_"+counter+"' value='W'/><a href='javascript:deleteNomRow("+counter+")'>delete</a> "
		
			if(window.opener.parent.document.forms[0].counter != null)
			{
				window.opener.parent.document.forms[0].counter.value = counter;
			}
			else
			{
				parent.window.opener.document.forms[0].counter.value = counter;
			}
		
		}
	}
	
	if(window.opener.parent.document.getElementById('hidden_employeeSelArray') != null)
	{
		window.opener.parent.document.getElementById('hidden_employeeSelArray').value = '';
	}
	else
	{
		parent.window.opener.document.getElementById('hidden_employeeSelArray').value = '';
	}
	window.close();
			
}    
          

function checkForm()
{
	return true;
}


</script>

<hdiits:form name="findTrainer" validate="true" method="post" action="./hrms.htm?actionFlag=findTrainer">

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
	<li class="selected"><a href="#" rel="tcontent1"><hdiits:caption captionid="TR.SEARCH_TRAINER" bundle="${trnrSearchLable}"/></a></li>
	</ul>
</div>

<div class="tabcontentstyleForPopup">
<!-------------------------------------------------------- tb1 --------------------------------------------------->
<div id="tcontent1" class="tabcontentForPopup" tabno="0" title='<fmt:message bundle="${trnrSearchLable}" key="TR.SEARCH_TRAINER"/>'>

<c:if test="${empList eq null and isRecordFound eq 'none'}">
<table class="tabtable" >
	<tr>
		<td class="fieldLabel" colspan="4" align="center"><b><fmt:message bundle="${trnrSearchLable}" key="TR.TRNOTFOUND" /></b></td>
	</tr>
</table>
</c:if>
<c:if test="${empList ne null}">
<c:set var="localCount" value="0"></c:set>
<display:table pagesize="10" name="${empList}" id="row" requestURI="" style="width:100%" >
	
	<display:column class="tablecelltext" titleKey="TR.SELECT" headerClass="datatableheader" >
		<hdiits:checkbox name="rdoLoc_${localCount}" id="rdoLoc" value="${row.empId}*${row.empName}*${row.designation}*${row.qualification}"  onclick="editValueCheckBox(this)"></hdiits:checkbox>
		<c:set var="localCount" value="${localCount + 1}"></c:set>
	</display:column>
	<display:column class="tablecelltext" value="${empList}" property="empName" sortable="true" titleKey="TR.TrainerFirstName" headerClass="datatableheader"></display:column>
	<display:column class="tablecelltext" value="${empList}" property="designation" sortable="true" titleKey="TR.Desig" headerClass="datatableheader" ></display:column>
	<display:column class="tablecelltext" value="${empList}" property="qualification" sortable="true" titleKey="TR.QUAl" headerClass="datatableheader" ></display:column>

</display:table>
<table align="center"  border="0">
	<tr>
		<td>
			<hdiits:button type="button" name="submitButton" captionid="SUBMIT_BTN" bundle="${trnrSearchLable}" onclick="submitForm()" style="width :5em" />
		</td>
		
	</tr>
</table>
</c:if>
</div>
</div>
<script type="text/javascript">
	initializetabcontent("maintab")
	
</script>  

<script>
for(var i = 0 ; i<${localCount}; i++)
{
	
	for(var j = 0; j<selectedEmplArray.length;j++)
	{

		if(document.getElementById('rdoLoc_'+i).value == selectedEmplArray[j])
			{
			document.getElementById('rdoLoc_'+i).checked='true';
			//alert(document.getElementById('rdoLoc_'+i).value);
			}
	}

}
</script>

</hdiits:form>