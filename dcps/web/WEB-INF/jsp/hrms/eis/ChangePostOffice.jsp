<%
try{
%>
<%@page import="java.util.List"%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="resources.eis.eis_common_lables" var="enLables" scope="page" />
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/Address.js"/>"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/OuterPaybillPara.js"/>"></script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="currentOffList" value="${resultValue.currentOffList}"></c:set>
<c:set var="newOffList" value="${resultValue.newOffList}"></c:set>

<c:set var="employeeListForJSP" value="${resultValue.employeeListForJSP}"></c:set>
<c:set var="employeeListSize" value="${resultValue.employeeListSize}"></c:set>
<c:set var="currentOffId" value="${resultValue.currentOffId}"></c:set>
<c:set var="newOffId" value="${resultValue.newOffId}"></c:set>
<c:set var="msg" value="${resultValue.msg}"></c:set>
<c:set var="counter" value="${0}"></c:set>
<%
List newOffList = (List) pageContext.getAttribute("newOffList");
int size = 0;
if(newOffList != null)
{
	size = newOffList.size();
}

pageContext.setAttribute("newOffListSize",size);

%>
<script type="text/javascript">
function fillAllSelectedData()
{
	if('${currentOffId}' != '')
	{
		document.ChangePostOfficeFrm.currentOffCmb.value = '${currentOffId}';
	}
	if('${newOffId}' != '')
	{
		document.ChangePostOfficeFrm.newOffCmb.value = '${newOffId}';
	}
	if("${msg}" != '')
	{
		alert('${msg}');
		url='./hrms.htm?actionFlag=getEmployeeList&currentOffId='+'${currentOffId}'+'&newOffId='+'${newOffId}'+'&elementId=90002576';
		document.ChangePostOfficeFrm.action = url;
		document.ChangePostOfficeFrm.submit();
		showProgressbar("Please wait...");
	}
}
function onclosefunction()
{
	window.location="hrms.htm?actionFlag=validateLogin";
}

function getEmployees()
{
	var index = document.ChangePostOfficeFrm.currentOffCmb.selectedIndex;
	var currentOffId = document.ChangePostOfficeFrm.currentOffCmb.options[index].value;

	var dsgn_index = document.ChangePostOfficeFrm.newOffCmb.selectedIndex;
	var newOffId = document.ChangePostOfficeFrm.newOffCmb.options[dsgn_index].value;

	if(currentOffId == -1)
	{
		alert("Please select Current Office");
		document.getElementById("currentOffCmb").focus();
		return false;
	}
	
	url='./hrms.htm?actionFlag=getEmployeeList&currentOffId='+currentOffId+'&newOffId='+newOffId+'&elementId=90002576';
	document.ChangePostOfficeFrm.action = url;
	document.ChangePostOfficeFrm.submit();
	showProgressbar("Please wait...");
}

function selDeselectAllowances(obj)
{
	var chkBoxArr = document.getElementsByName("selcheckBoxAllow");
	var chkLength = chkBoxArr.length;
	var chkAllParent = document.getElementsByName("selcheckBoxAllowSelectAll");
	if(chkAllParent[0].checked)
  	{
		for(var i=0; i < chkLength; i++)
		{
			chkBoxArr[i].checked = true;
		}
	}
	else
	{
		for(var j=0; j < chkLength; j++)
		{
			chkBoxArr[j].checked = false;
		}
	}
}

function getPostId()
{
	
	var chkBoxArr = document.getElementsByName("selcheckBoxAllow");
	var chkLength = chkBoxArr.length;
	var counter = 0 ;
	for(var j = 0; j<chkLength ; j++)
	{
		if(!chkBoxArr[j].checked)
		{
			counter++;
		}
	}
	document.getElementById("allPostId").value = "";
	if(chkLength == counter)
	{
		alert("Please select Employee");
		return false;
	}
	for(var i=1; i <= chkLength; i++)
	{
		if(chkBoxArr[i-1].checked)
		{
			var postIdHdn = document.getElementById("allPostId").value;
			document.getElementById("allPostId").value = postIdHdn + document.getElementById("postId"+i).value + ",";
		}
	}
	return true;
}

function submitForm()
{
	if(getPostId())
	{
		var index = document.ChangePostOfficeFrm.currentOffCmb.selectedIndex;
		var currentOffId = document.ChangePostOfficeFrm.currentOffCmb.options[index].value;
	
		var dsgn_index = document.ChangePostOfficeFrm.newOffCmb.selectedIndex;
		var newOffId = document.ChangePostOfficeFrm.newOffCmb.options[dsgn_index].value;
	
		var postId = document.getElementById("allPostId").value ; 
	
		if(currentOffId == -1)
		{
			alert("Please select Current Office");
			document.getElementById("currentOffCmb").focus();
			return false;
		}
	
		if(newOffId == -1)
		{
			alert("Please select New Office");
			document.getElementById("newOffCmb").focus();
			return false;
		}
		
		url='./hrms.htm?actionFlag=saveOfficeData&currentOffId='+currentOffId+'&newOffId='+newOffId+'&elementId=90002576';
		document.ChangePostOfficeFrm.action = url;
		document.ChangePostOfficeFrm.submit();
		showProgressbar("Please wait...");
	}
}


</script>
<body>
<hdiits:form name="ChangePostOfficeFrm" validate="true" method="POST"	encType="multipart/form-data">
<input type="hidden" name="allPostId" id="allPostId" value="" />
<hdiits:hidden default="${newOffList}" id="newOffList" name="newOffList"></hdiits:hidden>
<hdiits:hidden default="${newOffListSize}" id="newOffListSize" name="newOffListSize"></hdiits:hidden>
	<table width="85%" align="center" border="0">
		<tr>
			<td width="25%">
				Current Office
			</td>
			<td width="25%">
				<hdiits:select name="currentOffCmb" size="1" sort="false" id="currentOffCmb" onchange="" mandatory="true"> 
	   				<hdiits:option value="-1"> --Select-- </hdiits:option>
	 				<c:forEach items="${currentOffList}" var="currentOffList">
 	     				<hdiits:option value="${currentOffList.dcpsDdoOfficeIdPk}"> ${currentOffList.dcpsDdoOfficeName} </hdiits:option>
	    			</c:forEach>
	   			</hdiits:select>
			</td>
			<td width="25%">
				New Office
			</td>
			<td width="25%">
				<hdiits:select name="newOffCmb" size="1" sort="false" id="newOffCmb" onchange="" mandatory="true"> 
	   				<hdiits:option value="-1"> --Select-- </hdiits:option>
	 				<c:forEach items="${newOffList}" var="newOffList">
 	     				<hdiits:option value="${newOffList.dcpsDdoOfficeIdPk}"> ${newOffList.dcpsDdoOfficeName} </hdiits:option>
	    			</c:forEach>
	   			</hdiits:select>
			</td>
		</tr>
	</table>
	<div align="center">
		<hdiits:button name="Save" style="text-align:center;" id="Go"	type="button" caption="Go" onclick="getEmployees()" /> 
		<hdiits:button name="btnClose1" style="text-align:center;" type="button" caption="Close" onclick="onclosefunction()" />
	</div>
	<table width="85%" height="30%" align="center" cellpadding="0" cellspacing="0" style="border-collapse: separate;" >
		<tr>
	 		<td height="30%" width="100%"  valign="top">
	 			<div style="height: 300px;width: 100%;overflow: auto;margin: 0px;padding: 0px;">
	 				<table style="width: 98%;border:single;overflow-y: auto;overflow-x: hidden; " border="1" bordercolor="black" align="center">
						<tr>
							<th style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold" width="35%" align="center"><b>Employee Name<b></th>
							<th style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold;" width="5%" align="center">
								<b><font color="black">
									<hdiits:checkbox id="selcheckBoxAllowSelectAll"	name="selcheckBoxAllowSelectAll" value="1" onclick="selDeselectAllowances(this)"/> 
									</font></b>
							</th>
						</tr>
						<c:set var="srNoDeduction" value="0"></c:set>
						<c:forEach var="row" items="${employeeListForJSP}">
							<c:set var="counter" value="${counter+1}"></c:set>
							<tr>
								<td title="Employee Name">
									<input type="hidden" name="postId${counter}" id="postId${counter}" value="${row.postId}" />
									<c:out value="${row.empFullName}"></c:out>
								</td>
								<td title="Select" align="center">
									<hdiits:checkbox id="selcheckBoxAllow${counter}" name="selcheckBoxAllow" value="1" default="" readonly="" onclick="" />
								</td>
							</tr>
						</c:forEach>
					</table>
	 			</div>
	 		</td>
	 	</tr>
	 </table>
	<div align="center">
		<hdiits:button name="save" style="text-align:center;" id="save"	type="button" caption="Save" onclick="submitForm();" /> 
	</div>

</hdiits:form>
<script type="text/javascript">
fillAllSelectedData();
</script>
</body>
<%
}
catch(Exception e)
{
	//System.out.println("There is some error:-");
  	e.printStackTrace();
}
%>