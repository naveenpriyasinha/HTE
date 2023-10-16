<%
try {
	

%>

<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="<c:url value="script/common/tabcontent.js"/>"></script>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<script>

var size='${fn:length(resValue.draftLst)}';

if(size=='0')
{
	 alert('<fmt:message key="WF.AltMsgDraftApp" bundle="${fmsLables}"></fmt:message>');
		window.close();
		
}

var empIdLst = '';


var empIdArry='';

function getEmployees()
{
	empIdLst = document.forms[0].Sel_Employee_POST_Array_EMPLOYEESEARCH.value;

	//"empIdLst" will give u array of post ids of selected employees
	
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
</script>
<hdiits:form name="FileDraft"  method="POST" action="./hdiits.htm" validate="true">
<hdiits:hidden name="attIdList" id="attIdList_hid"/>
<hdiits:hidden name="fileId" default="${resValue.fileId}"/>
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
		           		<li class="selected"><a href="#" rel="tcontent1">Send Draft as Communique</a></li>
		</ul>
	</div>
	
	<div class="tabcontentstyle">
		<div id="tcontent1" class="tabcontent" tabno="0">
		
		<display:table list="${resValue.draftLst}" pagesize="12" requestURI="" id="row"  defaultsort="1" style="width:100%">
			<display:column  titleKey="WF.Drafts" headerClass="datatableheader" sortable="true">${row.attName}</display:column>
			<display:column  titleKey="WF.View" headerClass="datatableheader" sortable="true"><a style="cursor:hand;" href='${row.attchUrl}'><font color="blue">View</font></a></display:column>
			<display:column titleKey="Select" headerClass="datatableheader" media="HTML"><input type="checkbox"  id="${row.refNo}" value="${row.refNo}"  onclick="addDrafts(this)"> </display:column>
		</display:table>
		
		<br>
		<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
			<jsp:param name="SearchEmployee" value="EMPLOYEESEARCH"/>
			<jsp:param name="formName" value="testing"/>
			<jsp:param name="mandatory" value="No"/>
			<jsp:param name="multiEmployeeSel" value="Yes"/>
			<jsp:param name="mandatory" value="Yes"/>
			<jsp:param name="functionName" value="getEmployees"/>
			<jsp:param name="searchEmployeeTitle" value="Select Employee to Send"/>
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
		<center>
		<hdiits:button name="button" type="button"  captionid="WF.Send" bundle="${fmsLables}" onclick="sendDraftAsCorr()"/>
		<hdiits:button name="closebtn" captionid="WF.Close" bundle="${fmsLables}" type="button" onclick="self.close()"/>
		</center>
		
			<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>		
	 </div>
	</div>
	
	<script type="text/javascript"> 
			//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		
		var attchArr = new Array();
		function addDrafts(attachObj){
			
			if(document.getElementById(attachObj.value).checked){
				attchArr.push(attachObj.value);
			}
			else{
				for(var cnt=0;cnt<attchArr.length;cnt++){
					if(attchArr[cnt]==attachObj.value){
						attchArr.splice(cnt,1);
					}
				}
			}
			
		}
		function sendDraftAsCorr(){
			//alert(attchArr);
			
			if(attchArr.length==0)
			{
				alert('<fmt:message key="WF.AltSelOneDft" bundle="${fmsLables}"></fmt:message>');
			}
			else if(empIdArry=='')
			{
				alert('<fmt:message key="WF.SelectEmp" bundle="${fmsLables}"></fmt:message>');
			}
			else
			{
			document.getElementById('attIdList_hid').value=	attchArr;
			document.forms[0].method="post";
			document.forms[0].action="${contextPath}/hdiits.htm?actionFlag=FMS_sendDraftAsCommunique";
			document.forms[0].submit();
			}
			
		}
		</script> 
		<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>	
</hdiits:form>

	
<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>