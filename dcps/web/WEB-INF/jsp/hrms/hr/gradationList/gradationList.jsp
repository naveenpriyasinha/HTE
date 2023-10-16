
<%
try {
%>

<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript" src="script/common/address.js"></script>
<script type="text/javascript" src="/script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="script/common/prototype-1.3.1.js"></script>
<script type="text/javascript" src="script/common/ajaxtags-1.1.5.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/base64.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/hrms/hr/gradation/gradation.js"/>"></script>

<fmt:setBundle basename="resources.hr.gradationList.gradationListLables"
	var="gradationLables" scope="request" />
<fmt:setBundle basename="resources.hr.gradationList.gradationListAlerts"
	var="gradationalerts" scope="request" />


<style> 
 .odd{background-color: silver;} 
 .even{background-color: white;} 
</style>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="departListv" value="${resValue.departList}"></c:set>
<c:set var="dsgnList" value="${resValue.dsgnList}"></c:set>
<c:set var="locationList" value="${resValue.locList}"></c:set>
<c:set var="customList" value="${resValue.customList}"></c:set>
<c:set var="xmlKey" value="${resValue.xmlKey}"></c:set>
<c:set var="flag" value="${resValue.flag}"></c:set>
<c:set var="dsgnId" value="${resValue.dsgnId}"></c:set>
<c:set var="moreData" value="${resValue.moreData}"></c:set>
<c:set var="designation" value="${resValue.designation}"></c:set>

<script type="text/javascript">

	function submitData()
	{		
		var checkedDragDrop=new Array();
		var checkedArray=new Array();
		var Cnt=0;
		for (var lCntr = 0; lCntr < document.gradationList.elements.length; lCntr++)
		{ 
		     if((document.gradationList.elements[lCntr].type == "checkbox") && (document.gradationList.elements[lCntr].checked == true ) ) 
		     {
		     	
			       var id1=document.gradationList.elements[lCntr].id;
		    	   var subId=id1.substring(6,id1.length);
			       checkedDragDrop[Cnt]='hiddenRowId'+subId;
			   	   checkedArray[Cnt]=subId;
			       Cnt++;
		      }
		}
		
		document.getElementById('hidChkBoxArray').value=checkedArray;  
		document.getElementById('dragDrop').value=checkedDragDrop;
		var urlstyle="hdiits.htm?actionFlag=saveGLData";	
		document.gradationList.action=urlstyle;		
		if(checkSubmit())
		{
		
			var flagStr='${flag}';
			if(flagStr.length!=0){
				submitMe();
			}else{
				document.gradationList.submit();  
			}
		}else
		{
			return;
		}	
	}	
	
	function closeWindow()
	{
			var flagStr='${flag}';
			if(flagStr.length!=0){
				window.close();
			}else{
			
				var urlstyle="hdiits.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
				document.gradationList.action=urlstyle;
				document.gradationList.submit();
			}
	}
function submitMe()
{
	if(confirm('Do You want to submit form?')==true)
	{
		 
		 
		var fieldArray = new Array();
		for (var lCntr = 0; lCntr < document.gradationList.elements.length; lCntr++)
		{
			 fieldArray[lCntr]=document.gradationList.elements[lCntr].id;
		}
				
		xmlHttp=GetXmlHttpObject();
		if (xmlHttp==null) {
		  alert ("Your browser does not support AJAX!");
		  return;
		} 
		 
		var reqBody = getRequestBodyValue(fieldArray);
	
		methodName="processResponse";
		var url='actionFlag=saveGLData&' + reqBody;
		methodName = methodName + "()";
		xmlHttp.onreadystatechange=function() { eval(methodName);}
		xmlHttp.open('POST', 'hdiits.htm', false);
      	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
      	xmlHttp.setRequestHeader("Content-length", url.length);
      	xmlHttp.setRequestHeader("Connection", "close");
      	xmlHttp.send(url);
	
	 
	}
}
function processResponse(){
	if(xmlHttp.readyState == 4) {
		 window.opener.displayGradList();
		 window.close();	
	}
}
function getRequestBodyValue(fieldArray)
{
		var aParams = new Array();
		for(var i = 0; i < fieldArray.length; i++)
		{
			if(fieldArray[i]!=''){
			
				var sParam = document.getElementById(fieldArray[i]).name;
				sParam += "=";
				
				if(document.getElementById(fieldArray[i]).type == 'radio') 
				{
					var rdo = document.getElementsByName(fieldArray[i]);
					for(var j = 0; j < rdo.length; j++) 
					{
						sParam += rdo[j].value;	
						aParams.push(sParam);							
					}			
				}
				else if(document.getElementById(fieldArray[i]).type == 'select-multiple')
				{
					var lstbox = document.getElementById(fieldArray[i]);
					for(var j=0; j<lstbox.length; j++)
					{
						if(lstbox[j].selected)
						{
							var a = sParam;
							a += lstbox[j].value;
							aParams.push(a);		
						}
					}
				}
				else 
				{				
					var elementArr = document.getElementsByName(fieldArray[i]);				
					for(var j = 0; j < elementArr.length; j++) 
					{					
						var temp = elementArr[j].name+"=";
						temp += elementArr[j].value;
						aParams.push(temp);
					}			
	
					//sParam += document.getElementById(fieldArray[i]).value;
					//aParams.push(sParam);		
				}
			}
			 
		}
		return aParams.join("&");
}
     function checkEmpNo(){
		var flag=0;
		
		if(document.getElementById('cmbOffice').value== "-1"){
	 		alert("	<fmt:message bundle="${gradationalerts}" key="HRMS.SELECTOFFICE"></fmt:message>");
			return false;
	 	}else if(document.getElementById('cmbDsgn').value== "-1"){
	 		alert("	<fmt:message bundle="${gradationalerts}" key="HRMS.SELECTDSGN"></fmt:message>");
			return false;
	 	}
		
		if(document.getElementById('empNO').value==""){
			alert("	<fmt:message bundle="${gradationalerts}" key="HRMS.ENTEREMPNO"></fmt:message>");
			document.getElementById('empNO').focus();
			flag=-1;
		}else if(parseInt(document.getElementById('empNO').value)<3){
			alert("	<fmt:message bundle="${gradationalerts}" key="HRMS.ENTEREMPNO3"></fmt:message>");
			document.getElementById('empNO').value="";
			document.getElementById('empNO').focus();
			flag=-1;
		} 
		
	 	if(flag==0)
		{
			document.gradationList.genList.disabled='';
			document.getElementById('genList').focus();
			return true;
		}
	}
	
	function checkSubmit()
	{
		var lCnt=0;
		for (var lCntr = 0; lCntr < document.gradationList.elements.length; lCntr++)
		{ 
		     if((document.gradationList.elements[lCntr].type == "checkbox") && (document.gradationList.elements[lCntr].checked == true ) ) 
		     {
		        id=document.gradationList.elements[lCntr].id;		   
		        lCnt++;
		     }
		}
		if(lCnt<=0)
		{
			alert("	<fmt:message bundle="${gradationalerts}" key="HRMS.SELECTROW"></fmt:message>");
		      return false;
		}
		return true;
	}
	
	
	 function enableEmpNoTrue(form){
	     	 if(form.value=='-1')
				{
					document.getElementById('empNo').readOnly='true';			
				}else
				{
					var urlstyle="hdiits.htm?actionFlag=getGradationUserList";	
					document.gradationList.action=urlstyle;	
					document.gradationList.submit();	
				}
      }
	
</script>

<hdiits:form name="gradationList" validate="true" method="POST"
	encType="multipart/form-data">
	<hdiits:hidden name="designationId" id="designationId" />
	<br>
	
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><hdiits:caption captionid="HRMS.GRADATION" bundle="${gradationLables}" captionLang="single"/></a></li>
	</ul>
	</div>

	<div class="tabcontentstyle" style="height: 100%">
	<div id="tcontent1" class="tabcontent" tabno="0"><br>

	<hdiits:fieldGroup titleCaptionId="HRMS.PREPARE" bundle="${gradationLables}" expandable="true"></hdiits:fieldGroup>

	<table width="100%" id="gradationListTab1">
		<tr>
			
			<td width="20%"><hdiits:caption captionid="HRMS.OFFICE" bundle="${gradationLables}"/></td>
			<td width="25%"><hdiits:hidden name="cmbOffice" id="cmbOffice"
				default="${departListv.departmentId}" /><strong><c:out
				value="${departListv.depName}"></c:out> </strong></td>

			<td width="20%"><hdiits:caption captionid="HRMS.LOCATION" bundle="${gradationLables}"/></td>
			<td width="25%" valign="top">
				<c:forEach var="locationList" items="${locationList}">
						<hdiits:hidden name="locationId" id="locationId" default="${locationList.locId}" />
						<b><c:out value="${locationList.locName}"></c:out></b> 
				</c:forEach>
			</td>
		</tr>

		<tr>
			<td width="20%"><hdiits:caption captionid="HRMS.DESIGNATION" bundle="${gradationLables}"/></td>
			<td width="25%">

				<hdiits:select name="cmbDsgn" id="cmbDsgn" style="width:200px"
					default="${dsgnId}" onchange="enableEmpNoTrue(this)" tabindex="3">
					<hdiits:option value="-1">
						<fmt:message key="HRMS.SELECT" />
					</hdiits:option>
					<c:forEach var="dsgnList1" items="${dsgnList}">
						<hdiits:option value="${dsgnList1.dsgnCode}">${dsgnList1.dsgnName}</hdiits:option>
					</c:forEach>
				</hdiits:select>

			</td>
			<td width="20%"><hdiits:caption captionid="HRMS.EMPNO" bundle="${gradationLables}"/></td>
			<td width="25%"><hdiits:number name="empNo" id="empNo"
				readonly="false" tabindex="4"  default="${moreData}" /></td>
		</tr>
	</table>

	<jsp:useBean id="now" class="java.util.Date" /> <c:if
		test="${empty customList}">
		<br>
		<CENTER><hdiits:button name="genList" type="button"
			id="genList"  onclick="addDataWithOutAJAX();"
			tabindex="5"  captionid="HRMS.GENERATE" bundle="${gradationLables}"/></CENTER>
		<br>

		<hdiits:fieldGroup titleCaptionId="HRMS.GRADATION" bundle="${gradationLables}" expandable="true">${dat}</hdiits:fieldGroup>

		<br>

		<table id="lstEmployeeHeader" border="1">
			<tr class="datatableheader">
				<td class="fieldLabel" width="5%" align="center"><b><hdiits:caption captionid="HRMS.SRNO" bundle="${gradationLables}"/></b></td>
				<td class="fieldLabel" width="21%" align="center"><b><hdiits:caption captionid="HRMS.NAME" bundle="${gradationLables}"/></b></td>
				<td class="fieldLabel" width="11%" align="center"><b><hdiits:caption captionid="HRMS.DSGN" bundle="${gradationLables}"/></b></td>
				<td class="fieldLabel" width="8%" align="center"><b><hdiits:caption captionid="HRMS.DOJ" bundle="${gradationLables}"/></b></td>
				<td class="fieldLabel" width="8%" align="center"><b><hdiits:caption captionid="HRMS.RELIGION" bundle="${gradationLables}"/></b></td>
				<td class="fieldLabel" width="8%" align="center"><b><hdiits:caption captionid="HRMS.CASTE" bundle="${gradationLables}"/></b></td>
				<td class="fieldLabel" width="11%" align="center"><b><hdiits:caption captionid="HRMS.POP" bundle="${gradationLables}"/></b></td>
				<td class="fieldLabel" width="8%" align="center"><b><hdiits:caption captionid="HRMS.DOR" bundle="${gradationLables}"/></b></td>
			</tr>
			<tr>
				<td colspan="8" align="center"><b><hdiits:caption captionid="HRMS.NORECORDFOUND" bundle="${gradationLables}"/></b></td>
			</tr>
		</table>

	</c:if> <c:if test="${not empty customList}">

		<br>
		<CENTER><hdiits:button name="genList" type="button"
			id="genList"  
			onclick="addDataWithOutAJAX();" captionid="HRMS.GENERATE" bundle="${gradationLables}"/></CENTER>
		<br>
		<hdiits:fieldGroup titleCaptionId="HRMS.GRADATION" bundle="${gradationLables}" expandable="true">${dat}</hdiits:fieldGroup>
		<br>

		<table id="lstEmployeeHeader" border="1">
			<tr class="datatableheader">
				<td class="fieldLabel" width="5%" align="center"><b><hdiits:caption captionid="HRMS.SRNO" bundle="${gradationLables}"/></b></td>
				<td class="fieldLabel" width="21%" align="center"><b><hdiits:caption captionid="HRMS.NAME" bundle="${gradationLables}"/></b></td>
				<td class="fieldLabel" width="11%" align="center"><b><hdiits:caption captionid="HRMS.DSGN" bundle="${gradationLables}"/></b></td>
				<td class="fieldLabel" width="8%" align="center"><b><hdiits:caption captionid="HRMS.DOJ" bundle="${gradationLables}"/></b></td>
				<td class="fieldLabel" width="8%" align="center"><b><hdiits:caption captionid="HRMS.RELIGION" bundle="${gradationLables}"/></b></td>
				<td class="fieldLabel" width="8%" align="center"><b><hdiits:caption captionid="HRMS.CASTE" bundle="${gradationLables}"/></b></td>
				<td class="fieldLabel" width="11%" align="center"><b><hdiits:caption captionid="HRMS.POP" bundle="${gradationLables}"/></b></td>
				<td class="fieldLabel" width="8%" align="center"><b><hdiits:caption captionid="HRMS.DOR" bundle="${gradationLables}"/></b></td>
			</tr>
		</table>

		<div style="height: 60%;overflow:scroll;" id="GLDisplay"><span
			id="cursorStyle">
		<table id="lstEmployee" style="height: 50%" border="1"
			onmouseover="handleOver();" width="100%">
			<script>
			 	window.onload = init; 
			</script>

			<tbody id="Gradation">

				<c:forEach var="clist" items="${customList}">
					<hdiits:hidden name="locIds" id="locIds" default="${clist.locId}" />
					<%-- 	Added below hidden name for drag n drop		--%>
					<hdiits:hidden name="hiddenRowId${clist.srno}"
						id="hiddenRowId${clist.srno}" default="chkBox${clist.srno}" />

					<tr id="${clist.srno}">
						<td align="center" width="5%"><b><input type="checkbox"
							name="chkBox" id="chkBox${clist.srno}"> ${clist.srno} </td>
						<td align="left" width="21%"><b>${clist.name}</td>
						<td align="center" width="11%"><b>${clist.designation}</td>
						<fmt:formatDate var="doj" value="${clist.doj}"
							pattern="dd/MM/yyyy" />
						<td align="center" width="8%"><b>${doj}</td>
						<td align="center" width="8%"><b>${clist.religion}</td>
						<td align="center" width="8%"><b>${clist.caste}</td>
						<td align="center" width="11%"><b>${clist.pop}</td>
						<fmt:formatDate var="dor" value="${clist.dor}"
							pattern="dd/MM/yyyy" />
						<td align="center" width="8%"><b>${dor}</td>
					</tr>

					<script>
					var table1 = document.getElementById('lstEmployee');   
				 	var rows = table1.getElementsByTagName("tr");   
					for(i = 0; i <rows.length; i++){           
								 
				    	 if(i % 2 == 0){ 
				    	   rows[i].className = "even"; 
				   	  	 }else{ 
				   		    rows[i].className = "odd"; 
				   		 }       				
				   	}
				</script>
				</c:forEach>
			</tbody>
		</table></div>
		</span>

		<br>

		<table width="100%" align="center">
			<tr>
				<td align="center">
				<center><hdiits:button name="savaGLst" type="button"
					id="savaGLst"  onclick="submitData()" captionid="HRMS.SUBMITLIST" bundle="${gradationLables}"/> <hdiits:button
					name="close" type="button" id="close" value="Close"
					onclick="closeWindow()" captionid="HRMS.CLOSE" bundle="${gradationLables}" /> <hdiits:button name="btnReset"
					id="btnReset" type="button" value="Reset"
					onclick="resetGradationJSP()" captionid="HRMS.RESET" bundle="${gradationLables}"/></center>
				</td>
			</tr>
		</table>
	</c:if> <br>

	</div>
	</div>

	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
	</script>

	<hdiits:hidden name="srno" id="srno" />
	<hdiits:hidden name="name" id="name" />
	<hdiits:hidden name="designation" id="designation" />
	<hdiits:hidden name="doj" id="doj" />
	<hdiits:hidden name="religion" id="religion" />
	<hdiits:hidden name="caste" id="caste" />
	<hdiits:hidden name="pop" id="pop" />
	<hdiits:hidden name="flag" id="flag" default="${flag}" />
	<hdiits:hidden name="dor" id="dor" />
	<hdiits:hidden name="remarks" id="remarks" />
	<hdiits:hidden name="hidPath" id="hidPath" />
	<hdiits:hidden name="hidChkBoxArray" id="hidChkBoxArray" />
	<hdiits:hidden name="hidxmlKey" id="hidxmlKey" default="${xmlKey}" />
	<hdiits:hidden name="dragDrop" id="dragDrop" />
	
	
	<hdiits:hidden name="currtime" id="currtime" default="${dat}" />
	<hdiits:validate controlNames=""
		locale='<%=(String)session.getAttribute("locale")%>' />

</hdiits:form>


<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
