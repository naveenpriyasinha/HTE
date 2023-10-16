<%
try {
%>

<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="/script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="script/common/ajaxtags-1.1.5.js"></script>
 
 
<script type="text/javascript"
	src="<c:url value="/script/common/base64.js"/>">
</script>
	
	
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="depList" value="${resValue.depList}"></c:set>
<c:set var="dsgnList" value="${resValue.dsgnList}"></c:set>
<c:set var="xmlPath" value="${resValue.ajaxKey}"></c:set>
<c:set var="submitflag" value="${resValue.submitflag}"></c:set>

<fmt:setBundle basename="resources.eis.vacancy.vacancyReportLabels" var="vacancyReportLabels" scope="request"/>

<script>
	var rowCounter = 1;

	function getLocationCombo()
	{
   		var locCmb=document.getElementById('cmbLoc');
    	var len=locCmb.options.length;
		for(var i=0;i<=len;i++)
		{
			locCmb.remove(1);
		}
		addOrUpdateRecord('addLocationCombo','getLocationforVacancy',new Array('cmbDept'));		
	}
	
	function addLocationCombo()
	{
		if (xmlHttp.readyState == 4) 
		{
			var encXML=xmlHttp.responseText;
			if(encXML!="error")
			{
				document.getElementById('cmbLoc').disabled='';	
				var LocList=encXML.split("$");
				for (var i=0; i < LocList.length;++i){
					var keyValPair=LocList[i].split("/");
					//alert('key '+keyValPair[1]);
					//alert('value '+keyValPair[0])
					addOption(document.getElementById('cmbLoc'), keyValPair[1], keyValPair[0]);
				}
				
			}else
			{
				var locCmb=document.getElementById('cmbLoc');
				for(var i=0;i<=locCmb.options.length;i++)
				{
					locCmb.remove(1);
				}
				document.getElementById('cmbLoc').disabled="true";
			}	
		}
	}
	
	function addOption(selectbox,text,value )
	{
		var optn = document.createElement("OPTION");
		optn.text = text;
		optn.value = value;
		selectbox.options.add(optn);
	}   
	
	function addRecord()
	{
		var submitFlag = false;  
		var fieldArray = new Array('cmbDept','cmbLoc','cmbDsgn');
		var submitFlag = validateSpecificFormFields(fieldArray); 
		if(submitFlag)
		{
			if(xmlHttp.readyState == 4)
			{
				if (xmlHttp.status == 200) 
				{
					var displayFieldArray =  new Array("cmbDsgn");
					addVacancyDataInTable('ajxtable','encXML',displayFieldArray);
					document.getElementById('btnSave').style.display='';
					//document.getElementById('btnClose').style.display='';
					resetPage();
				}
			}		
		}
	}
	
	function sendAjaxRequestForEdiForVacancy(methodName, xmlFileName) 
	{
		xmlHttpEdit=GetXmlHttpObject();
		if (xmlHttpEdit==null) 
		{
		  alert ("Your browser does not support AJAX!");
		  return;
		} 		
		
		var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + xmlFileName;
		methodName = methodName + "()";
		
		xmlHttpEdit.onreadystatechange = function() {
			if(xmlHttpEdit.readyState == 4) {
				eval(methodName);
			}
		 }
		
		xmlHttpEdit.open("POST",encodeURI(url),false);
		xmlHttpEdit.send(null);	
	}
	
	function addVacancyDataInTable(tableId, hiddenField, displayFieldArray, editMethodName, deleteMethodName, viewMethodName) 
	{
		//alert ("addVacancyDataInTable called...");
		//alert(hiddenField);
		if(deleteMethodName == undefined) 
		{
			deleteMethodName = '';
		}
		if(editMethodName == undefined) 
		{
			editMethodName = '';
		}
		if(viewMethodName == undefined) 
		{
			viewMethodName = '';
		}
		
		//alert(xmlHttp.responseText);
		document.getElementById('hidxmlKey').value = xmlHttp.responseText;
		
		
		document.getElementById(tableId).style.display='';
		var trow=document.getElementById(tableId).insertRow();
		trow.id = 'row' + hiddenField + rowCounter;
		trow.insertCell(0).innerHTML = "<INPUT type='hidden' name='hiddenXMLPath" + rowCounter + "' id='hiddenXMLPath" + rowCounter + "' value='" + xmlHttp.responseText + "'/>";				
		trow.cells[0].style.display = 'none';
		
		var len = displayFieldArray.length;
		for(var i = 0; i < len; i++)
		{
			var field = document.getElementById(displayFieldArray[i]);
			
			if(field.type == 'select-one')
			{
				/* Code added by Tarun Trehan to check "Select" value in drop down
				for multiple add case. */
				if(field.options[field.selectedIndex].value == '-1')
				{
					trow.insertCell(i+1).innerHTML = "";
				}
				else
				{
					trow.insertCell(i+1).innerHTML = field.options[field.selectedIndex].text;						
				}
			}else{		
			//alert(field.type);
				trow.insertCell(i+1).innerHTML = field.value;	
			}
		}	
		
		trow.insertCell(len+1).innerHTML = "<INPUT type='text' name='sanctionNumber" + rowCounter+ "' id='sanctionNumber" + rowCounter + "'/>";				
	 	document.getElementById('rowNumber').value=rowCounter;
	 	sendAjaxRequestForEdiForVacancy('editSanctionNumber',xmlHttp.responseText);
		rowCounter++;	
	}
	
	function  editSanctionNumber(){
		
		if(xmlHttpEdit.readyState == 4)
		{
			if (xmlHttpEdit.status == 200) 
			{
				var xmlValue= xmlHttpEdit.responseText;
				var xmlDOM = getDOMFromXML(xmlValue);
				if(getXPathValueFromDOM(xmlDOM, 'srno')!=0){
					document.getElementById('sanctionNumber'+document.getElementById('rowNumber').value).value=getXPathValueFromDOM(xmlDOM, 'sanctionedStrength');
				}
				removeSelectedOption();
			}
		}		
	}
	
	function resetPage()
	{
		document.getElementById('cmbDept').selectedindex=0;
		document.getElementById('cmbLoc').selectedindex=0;
		document.getElementById('cmbDsgn').selectedindex=0;
	}
	
	function CloseApplication()
	{
		/*
		var urlstyle="hdiits.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
		document.vacacyAdmin.action=urlstyle;
		document.vacacyAdmin.submit();
		*/
		window.close();
	}
	
	function removeSelectedOption(){
		//alert('removeSelectedOption');
		var selectbox = document.getElementById('cmbDsgn');
		selectbox.remove(selectbox.selectedIndex);
	}
	
	function saveData()
	{
		for (var lCntr = 0; lCntr < document.vacacyAdmin.elements.length; lCntr++)
		{ 
		     if((document.vacacyAdmin.elements[lCntr].type == "text")) 
		     {
		     	if(document.vacacyAdmin.elements[lCntr].value == "")
		     	{
		     		alert('Please Enter proper value for sanctioned post');
		     		return;
		     	}
		        var id1=document.vacacyAdmin.elements[lCntr].value;
		        document.getElementById('hiddentxtBox').value=id1;  
		      }
		}
		var urlstyle="hdiits.htm?actionFlag=saveVacancyAdminData&tableSize="+document.getElementById('ajxtable').rows.length;
		document.vacacyAdmin.action=urlstyle;
	 
		document.vacacyAdmin.submit();
	}

</script>
	
<hdiits:form name="vacacyAdmin" validate="true" method="POST">
	
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><hdiits:caption captionid="HRMS.Sanction" bundle="${vacancyReportLabels}" captionLang="single"/></a></li>
	</ul>
	</div>
	
	<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">
		
		<br><br>
		<table width="100%">
			<tr>
				<td><hdiits:caption captionid="HRMS.Dept" bundle="${vacancyReportLabels}"/></td>
				<td>
					<hdiits:select name="cmbDept" id="cmbDept" onchange="getLocationCombo()" validation="sel.isrequired" captionid="HRMS.Dept" bundle="${vacancyReportLabels}">
						<hdiits:option value="-1"><hdiits:caption captionid="HRMS.Select" bundle="${vacancyReportLabels}"/></hdiits:option>
						<c:forEach var="depList" items="${depList}">
							<hdiits:option value="${depList.departmentId}">${depList.depName}</hdiits:option>
						</c:forEach>
					</hdiits:select> 
				</td>
				<td><hdiits:caption captionid="HRMS.Location" bundle="${vacancyReportLabels}"/></td>
				<td>
					<hdiits:select name="cmbLoc" id="cmbLoc" validation="sel.isrequired" caption="Location">
						<hdiits:option value="-1"><hdiits:caption captionid="HRMS.Select" bundle="${vacancyReportLabels}"/></hdiits:option>
					</hdiits:select> 
				 </td>
			</tr>
			<tr>
				<td><hdiits:caption captionid="HRMS.Designation" bundle="${vacancyReportLabels}"/></td>
				<td>
					<hdiits:select name="cmbDsgn" id="cmbDsgn" validation="sel.isrequired" caption="Designation" onchange="javascript:addOrUpdateRecord('addRecord', 'addDsgnVacancyData', new Array('cmbDept','cmbLoc','cmbDsgn'));">
						<hdiits:option value="-1"><hdiits:caption captionid="HRMS.Select" bundle="${vacancyReportLabels}"/></hdiits:option>
						<c:forEach var="dsgnList" items="${dsgnList}">
							<hdiits:option value="${dsgnList.dsgnId}">${dsgnList.dsgnName}</hdiits:option>
						</c:forEach>
					</hdiits:select> 
				</td>
			</tr>
			<tr></tr>
			<tr></tr>
			
		</table>
		
		<br><br>
		<center>
			<table id="ajxtable" style="display:none" width="50%" border="1" bordercolor="black" style="border-collapse: collapse">
			<tr bgcolor="#C6DEFF" >
				<td width="25%"><center><b><hdiits:caption captionid="HRMS.PostName" bundle="${vacancyReportLabels}"/></b></center></td>
				<td width="25%"><center><b><hdiits:caption captionid="HRMS.SanctionNumber" bundle="${vacancyReportLabels}"/></b></center></td>
			</tr>
		</table>
		</center>
		<br><br>
		
		<center>
			<table width="100%" >
				<tr>
					<td colspan="4">
						<hdiits:button name="btnSave" type="button" caption="Save" style="display:none" onclick="saveData()" captionid="HRMS.Save" bundle="${vacancyReportLabels}"/> 
						<hdiits:button name="btnClose" type="button" caption="Close" onclick="CloseApplication()" captionid="HRMS.Close" bundle="${vacancyReportLabels}"/>
					</td>
				</tr>
			</table>
		</center>
	</div>
	</div>
	
	<script type="text/javascript">
			initializetabcontent("maintab");
	</script>
	
	<hdiits:validate locale="${locale}" controlNames="cmbDept,cmbLoc,cmbDsgn" /> 
	<hdiits:hidden name="rowNumber"  id="rowNumber" />
	<hdiits:hidden name="hidxmlKey" id="hidxmlKey" default="${xmlPath}" />
	<hdiits:hidden name="hiddentxtBox" id="hiddentxtBox" default="0" />
</hdiits:form>	
			
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
	

	
