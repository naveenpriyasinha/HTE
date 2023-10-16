<%
	try {
%>
<%@ include file="../../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<fmt:setBundle basename="resources.eis.zp.zpDDOOffice.ZpDDOOfficeLabels_en_US" var="ZpDDOOfficeLabels" scope="request"/>

<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<c:set var="resultObj" value="${result}"/>
<c:set var="resValue" value="${resultObj.resultValue}"/>
<c:set var="updateFlag" value="${resValue.updateFlag}"/>
<c:set var="AdminOfficeList" value="${resValue.AdminOfficeList}"/>
<c:set var="DistOfcList" value="${resValue.DistOfcList}"/>
<c:set var="TOName" value="${resValue.TOName}"/>
<c:set var="TOCode" value="${resValue.TOCode}"/>
<c:set var="ZpDeptsList" value="${resValue.ZpDeptsList}"/>
<c:set var="lstSubTO1" value="${resValue.lstSubTO1}"/>


<c:set var="OfcCode" value="${resValue.OfcCode}"/>


<script type="text/javascript" src='<c:url value="script/common/validation.js"/>'></script>
<script type="text/javascript" src='<c:url value="script/common/tagLibValidation.js"/>'></script>
<script type="text/javascript" src='<c:url value="script/common/commonfunctions.js"/>'></script>
<script type="text/javascript" src='<c:url value="script/common/tabcontent.js"/>'></script>
<script type="text/javascript" src='<c:url value="script/common/script/calendar.js"/>'> </script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>

<script type="text/javascript" src="<c:url value="/script/exptracking/zp_admin_ofc/admin/zpAdminOffice.js"/>"></script>

<hdiits:form name="ZpDDOOffice"  method="post" action="" validate="true" encType="multipart/form-data">
	<hdiits:hidden name="updateFlag"  default = "${updateFlag}"/>

	
	                   
	  <div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1" class="selected"><b>Create Office</b></a></li>
		</ul>
	</div>
	<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >          
	            
	                  
	<table border="0">
		<tr>
			<td><hdiits:caption captionid="ZPDDOOFFICE_ZPADMINOFFICE" bundle="${ZpDDOOfficeLabels}"/></td>
			<td>
				<hdiits:select name="cmbAdminOffice" id="cmbAdminOffice"  bundle="${ZpDDOOfficeLabels}" mandatory="true">
					<hdiits:option value="-1"><fmt:message key="ZPDDOOFFICE_SELECT" bundle="${ZpDDOOfficeLabels}"></fmt:message></hdiits:option>
					<c:forEach items="${AdminOfficeList}" var="AdminOfficeList">
						<option value="<c:out value="${AdminOfficeList[0]}"/>"><c:out value="${AdminOfficeList[1]}" />
						</option>
					</c:forEach>
				</hdiits:select>
			</td>
			
		</tr>
		
		<tr>
			<td><hdiits:caption captionid="ZPDDOOFFICE_ZPDISTRICTOFFICE" bundle="${ZpDDOOfficeLabels}"/></td>
			<td>
				<hdiits:select name="cmbDistOffice" id="cmbDistOffice"  bundle="${ZpDDOOfficeLabels}">
					<hdiits:option value="-1"><fmt:message key="ZPDDOOFFICE_SELECT" bundle="${ZpDDOOfficeLabels}"></fmt:message></hdiits:option>
				</hdiits:select>
			</td>		
		</tr>
		
		
		<tr>
			<td><hdiits:caption captionid="ZPDDOOOFFICE_FINAL_LEVEL" bundle="${ZpDDOOfficeLabels}"/></td>
			<td>				
			<hdiits:radio id="radioFinalLevel2" onclick="checkforHirechy();" value="radioFinalLevel2" name="radioFinalLevel" caption="&nbsp;Level 2" mandatory="true" default="radioFinalLevel2" />
			<%--
			<hdiits:radio id="radioFinalLevel3" onclick="checkforHirechy();"  value="radioFinalLevel3" name="radioFinalLevel" caption="&nbsp;Level 3"/>
			<hdiits:radio id="radioFinalLevel4" onclick="checkforHirechy();" value="radioFinalLevel4" name="radioFinalLevel" caption="&nbsp;Level 4" mandatory="true" default="radioFinalLevel4" />
			--%>
			</td>
			
		</tr>
		
		
		<tr>
			<td><hdiits:caption captionid="ZPDDOOOFFICE_REPDDO_CODE" bundle="${ZpDDOOfficeLabels}"/></td>
			<td>
				<hdiits:number name="txtRepDDOCode" id="txtRepDDOCode" mandatory="true" maxlength="10" captionid="ZPDDOOOFFICE_REPDDO_CODE" bundle="${ZpDDOOfficeLabels}" validation="txt.isrequired" default="" onblur="checkEqualDDOCode();checkDDOCode();"/>
				<%-- <hdiits:select name="cmbRepDDOCode" id="cmbRepDDOCode"  bundle="${ZPDDOOOFFICE_REPDDO_CODE}">
					<hdiits:option value="-1"><fmt:message key="ZPDDOOFFICE_SELECT" bundle="${ZpDDOOfficeLabels}"></fmt:message></hdiits:option>
				</hdiits:select> --%>
			</td> 
		</tr>
		
		<tr style="display: none;">
			<td><hdiits:caption captionid="ZPDDOOOFFICE_FINALDDO_CODE" bundle="${ZpDDOOfficeLabels}"/></td>
			<td>
				<hdiits:number name="txtFinalDDOCode" id="txtFinalDDOCode" mandatory="true" maxlength="10" captionid="ZPDDOOOFFICE_FINALDDO_CODE" bundle="${ZpDDOOfficeLabels}" default="" onblur="checkEqualDDOCode();checkDDOCode();"/>
			</td>
		</tr>
		
		<tr style="display: none;">
	 		<td><hdiits:caption captionid="ZPDDOOOFFICE_SPECIALDDO_CODE" bundle="${ZpDDOOfficeLabels}"/></td>
			<td>
				<hdiits:number name="txtSpecialDDOCode" id="txtSpecialDDOCode" mandatory="true" maxlength="10" captionid="ZPDDOOOFFICE_SPECIALDDO_CODE" bundle="${ZpDDOOfficeLabels}" default=""  onblur="checkEqualDDOCode();checkDDOCode();"/>
				</td>
		</tr>
		<tr>
			<td><hdiits:caption captionid="ZPDDOOOFFICE_EMP_NAME_SALUTATION" bundle="${ZpDDOOfficeLabels}"/></td>
			<td>
			<hdiits:radio id="radioSalutationShri" onclick="addSalutationToName();"  value="radioSalutationShri" name="radioSalutation" caption="&nbsp;Shri" mandatory="true" default="radioSalutationShri"/>
			<hdiits:radio id="radioSalutationSmt" onclick="addSalutationToName();" value="radioSalutationShri" name="radioSalutation" caption="&nbsp;Smt"  />
			</td>
		</tr>
		<tr>
			<td><hdiits:caption captionid="ZPDDOOOFFICE_EMP_NAME" bundle="${ZpDDOOfficeLabels}"/></td>
			<td>
			<hdiits:text name="txtDDOName" id="txtDDOName" mandatory="true" maxlength="100" captionid="ZPDDOOOFFICE_EMP_NAME"  bundle="${ZpDDOOfficeLabels}"  validation="txt.isrequired" default="Shri." onblur="validateDDOName();" />
			</td>
		</tr>
		
		<tr>
			<td><hdiits:caption captionid="ZPDDOOFFICE_ZPGENDER" bundle="${ZpDDOOfficeLabels}"/></td>
			<td>
							Male   <b>:</b>&nbsp;&nbsp;<input type="radio" id="radioMale" name="radioGender" value="M" checked="checked" >
				&nbsp;&nbsp;Female <b>:</b>&nbsp;&nbsp;<input type="radio" id="radioFemale" name="radioGender" value="F">	
				&nbsp;&nbsp;Trans Gender <b>:</b>&nbsp;&nbsp;<input type="radio" id="radioTransGender" name="radioGender" value="T">		
			</td>
		</tr>
		
		<tr >
			<td><hdiits:caption captionid="ZPDDOOOFFICE_TREASURY" bundle="${ZpDDOOfficeLabels}"/></td>
			<td>
			<hdiits:text name="txtTreasuryName" id="txtTreasuryName" readonly="true" maxlength="50" captionid="ZPDDOOOFFICE_TREASURY"  bundle="${ZpDDOOfficeLabels}"  default="" />
			</td>
			
		</tr>
		
		<tr>
			
			<td><hdiits:caption captionid="ZPDDOOFFICE_DDO_TREASURY_CODE" bundle="${ZpDDOOfficeLabels}"/></td>
			<td>
			<hdiits:text name="txtTreasuryCode" id="txtTreasuryCode" readonly="true" maxlength="4" captionid="ZPDDOOFFICE_DDO_TREASURY_CODE"  bundle="${ZpDDOOfficeLabels}"  default="" />
			</td>
		</tr>
		
		<tr>
			<td><hdiits:caption captionid="ZPDDOOOFFICE_SUB_TREASURY" bundle="${ZpDDOOfficeLabels}"/></td>
			<td>
			<%-- <hdiits:text name="txtSubTreasuryName" id="txtSubTreasuryName" mandatory="true" maxlength="50" captionid="ZPDDOOOFFICE_TREASURY"  bundle="${ZpDDOOfficeLabels}"  validation="txt.isrequired" default="" /> --%>
			<hdiits:select name="cmbSubTreasury" id="cmbSubTreasury"  bundle="${ZpDDOOfficeLabels}" onchange="generateDDOCode();" mandatory="true">
					<hdiits:option value="-1"><fmt:message key="ZPDDOOFFICE_SELECT" bundle="${ZpDDOOfficeLabels}"></fmt:message></hdiits:option>
					<c:forEach items="${lstSubTO1}" var="lstSubTO1">
						<option value="<c:out value="${lstSubTO1[0]}"/>"><c:out value="${lstSubTO1[1]}" />
						</option>
					</c:forEach>
				</hdiits:select>
			</td>
		</tr>
		
		<tr>
			<td><hdiits:caption captionid="ZPDDOOOFFICE_DDO_Dsgn" bundle="${ZpDDOOfficeLabels}"/></td>
			<td>
				<hdiits:text name="txtDDODsgn" id="txtDDODsgn" mandatory="true" maxlength="50" captionid="ZPDDOOOFFICE_DDO_Dsgn"  bundle="${ZpDDOOfficeLabels}"  validation="txt.isrequired" default="" />
				<span id="roleIndicatorRegion" style="display: none"> <img
				src="./images/busy-indicator.gif" /></span> 
				
				<table>
					<tr>
						<td align="left"
						style="font-size: smaller; font-style: italic"><fmt:message
						key="MSG.DESIGNATION" bundle="${ZpDDOOfficeLabels}"></fmt:message></td>
					</tr>
				</table>
			</td>
		</tr>
		
		
		
		<tr>
			<td><hdiits:caption captionid="ZPDDOOOFFICE_OFFICE_NAME" bundle="${ZpDDOOfficeLabels}"/></td>
			<td>
			<hdiits:text name="txtOfficeName" id="txtOfficeName" mandatory="true" maxlength="500" captionid="ZPDDOOOFFICE_OFFICE_NAME"  bundle="${ZpDDOOfficeLabels}"  validation="txt.isrequired" default="" />
			</td>
		</tr>
		
		<tr>
			<td><hdiits:caption captionid="ZPDDOOOFFICE_DDO_CODE" bundle="${ZpDDOOfficeLabels}"/></td>
			<td>
			<hdiits:text name="txtDDOCode" id="txtDDOCode" maxlength="50" captionid="ZPDDOOOFFICE_DDO_CODE" readonly="true" bundle="${ZpDDOOfficeLabels}"  default="" />
			</td>
		</tr>
		
		<tr>
			<td><hdiits:caption captionid="ZPDDOOOFFICE_MOBILE_NO" bundle="${ZpDDOOfficeLabels}"/></td>
			<td>
			<hdiits:text name="txtMobileNo" id="txtMobileNo" maxlength="10" mandatory="true" captionid="ZPDDOOOFFICE_MOBILE_NO"  bundle="${ZpDDOOfficeLabels}"  default=""  validation="txt.isrequired"  onblur="validateMobileNo();"/>
			</td>
		</tr>
		
		<tr>
			<td><hdiits:caption captionid="ZPDDOOOFFICE_EMAIL_ID" bundle="${ZpDDOOfficeLabels}"/></td>
			<td>
			<hdiits:text name="txtEmailId" id="txtEmailId" maxlength="100" mandatory="true" captionid="ZPDDOOOFFICE_EMAIL_ID"  bundle="${ZpDDOOfficeLabels}"  default=""  validation="txt.isrequired"  onblur="validateEmailId();"/>
			</td>
		</tr>
		
		<tr>
			<td>&nbsp;</td>
		<tr>
		
		<tr>
			<td  colspan="2" align="center">
				<hdiits:button name="btnSave" type="button" captionid="ZPADMINOFFICE_SAVE" bundle="${ZpDDOOfficeLabels}" onclick="checkEqualDDOCode();confirmPage();"></hdiits:button>
				<hdiits:button name="btnCancel" type="button" captionid="ZPADMINOFFICE_CANCEL" bundle="${ZpDDOOfficeLabels}" onclick="canceal();"></hdiits:button>
			</td>
		</tr>
	</table>
		
	</div>
	

	</hdiits:form>	
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />

<script type="text/javascript">
var hidName = "";
var isMode = "";
initializetabcontent("mainTab");
function Check(){
	//alert("Hello");
}

function generateDDOCode()
{
	//alert("Hemal");	
	document.getElementById("txtDDOCode").value="";
	var SubTOCode=document.getElementById("cmbSubTreasury").value;
	var AdminOfc=document.getElementById("cmbAdminOffice").value;
	var url="";
	//alert("SubTOCode"+SubTOCode+"-"+"SubTOCode"+SubTOCode);
	var uri = "ifms.htm?actionFlag=generateDDOCode&SubTOCode="+SubTOCode+"&AdminOfc="+AdminOfc;
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: true,
		        parameters:url,
		        onSuccess: function (myAjax) {
		        	getGeneratedCode(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
}
function getGeneratedCode(myAjax){
	var XMLDoc = myAjax.responseXML.documentElement;
	var item = XMLDoc.getElementsByTagName('value');
	var GenrCode = item[0].firstChild.nodeValue;
	document.getElementById("txtDDOCode").value=GenrCode;
}
function fnZpAdminOfficeClose()
{
	var url;
	showProgressbar();
	url = "ifms.htm?viewName=view-searchZpAdminOffice&elementId=100041";
	document.forms[0].method = 'post';
	document.forms[0].action = url;
	document.forms[0].submit();
}

function checkDDOCode()
{
	 var DDOCODE=document.getElementById("txtRepDDOCode").value;
	// alert("HH"+DDOCODE); 
	 var AdminOfc=document.getElementById("cmbAdminOffice").value;
	 //alert("HHHHH"+AdminOfc); 
	// alert('txtFinalDDOCode'+txtFinalDDOCode);
	 var txtFinalDDOCode=document.getElementById("txtFinalDDOCode").value;
	 var txtSpecialDDOCode=document.getElementById("txtSpecialDDOCode").value;
	// alert("Final"+txtFinalDDOCode);
	 //alert("Special"+txtSpecialDDOCode);
	 var uri = 'ifms.htm?actionFlag=checkRepoDDOCode&repoDDOCode='+DDOCODE+'&AdminOfc='+AdminOfc+'&txtFinalDDOCode='+txtFinalDDOCode+'&txtSpecialDDOCode='+txtSpecialDDOCode;
		//alert('uri'+uri);
		var url = "";
		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: true,
			        parameters:url,
			        onSuccess: function (myAjax) {
			        	getSaveMsg(myAjax);
					},
			        onFailure: function(){ alert('Something went wrong...');} 
			          } );
		
}
function canceal()
{
	var url;
	url = "ifms.htm?actionFlag=loadDdoOfficeData";
	document.forms[0].method = 'post';
	document.forms[0].action = url;
	document.forms[0].submit();
}
function listSubTo(XMLDoc)
{
	//XMLDoc = myAjax.responseXML.documentElement;
	//var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	//alert("Start");
	var lstSubTOId= XMLDoc.getElementsByTagName('id');
	var lstSubTOName= XMLDoc.getElementsByTagName('name');
	var cmbSubTreasury = document.getElementById('cmbSubTreasury');
	cmbSubTreasury.options.length = 0;
	var optn = document.createElement("OPTION");
	optn.text = "-- Select --";
	optn.value = "-1";
	cmbSubTreasury.options.add(optn);

	var optnSubTO =lstSubTOId.length;
	var optnSubTre ;

		var i=0,j=0;
		for(i=1,j=0;j<optnSubTO;i++,j++)
		{
			optnSubTre = document.createElement("OPTION");
			optnSubTre.text = lstSubTOName[i].firstChild.nodeValue;
			//optnSubTre.title= llstSubTOName[i].firstChild.nodeValue;
			optnSubTre.value = lstSubTOId[j].firstChild.nodeValue;
			document.getElementById('cmbSubTreasury').options.add(optnSubTre);
		}
}
function getSaveMsg(myAjax)
{
	//alert("success");
	var XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	//alert(XmlHiddenValues[0].childNodes[0].firstChild.nodeValue);
	//alert(TOCode);
	var TOCode = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	var TOName = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;
	//var CreatedDDOCode=XmlHiddenValues[0].childNodes[2].firstChild.nodeValue;
	//var strFinalDDO=XmlHiddenValues[0].childNodes[3].firstChild.nodeValue;
	//var strSpecialDDO=XmlHiddenValues[0].childNodes[4].firstChild.nodeValue;
	var lstSubTO= XMLDoc.getElementsByTagName('lstSubTO');
	listSubTo(XMLDoc);
//	alert("End");
	 if (TOName!= "" && TOCode!= "") {
		//alert('Data Saved Successfully');		
		document.getElementById("txtTreasuryName").readOnly = true;
		document.getElementById("txtTreasuryCode").readOnly = true;
		document.getElementById("txtTreasuryName").value=TOCode;
		document.getElementById("txtTreasuryCode").value=TOName;
		document.getElementById("txtDDOCode").readOnly = true;
		//document.getElementById("txtDDOCode").value=CreatedDDOCode;
		
	}
	else{
		//alert("Data Not Saved");
		document.getElementById("txtTreasuryName").readOnly = false;
		document.getElementById("txtTreasuryCode").readOnly = false;
	} 
	 
	/*  if(strFinalDDO!= "" && strFinalDDO=='false')
		{
		alert("Invalid Final DDO Code");
		}
	/* else{
		alert("Valid");
		} */
	//alert("_______"+strSpecialDDO);
	/*if(strSpecialDDO!= "" && strSpecialDDO=='false'){
		alert("Invalid Special DDO Code");
		}*/
	/* else{
		alert("Valid");
		} */	    
}

function fnZpDDOSave()
{	 
	 var AdminOfc=document.getElementById("cmbAdminOffice").value;
	 var DistOfc=document.getElementById("cmbDistOffice").value;
	 var DeptCode=document.getElementById("cmbDept").value;
	 var RepoDDOCode=document.getElementById("txtRepDDOCode").value;
	 var DDOName=document.getElementById("txtDDOName").value;
	 var Tname=document.getElementById("txtTreasuryName").value;
	 var TCode=document.getElementById("txtTreasuryCode").value;
	 var SubTCode=document.getElementById("txtSubTreasuryName").value;
	 var DDODsgn=document.getElementById("txtDDODsgn").value;
	 var OfficeName=document.getElementById("txtOfficeName").value;
	 var DDOCode=document.getElementById("txtDDOCode").value;
	 
	/* var validateRowStatus = false;
	var validateArray = new Array('txtTreasuryName','txtTreasuryCode','cmbAdminOffice');
	var validateStatus = validateSpecificFormFields(validateArray);
	if(validateStatus)
	{
	validateRowStatus = true;
		if(validateRowStatus)
		{ */
			var url;
			url = "ifms.htm?actionFlag=saveZpDDODtls";
			document.forms[0].method = 'post';
			document.forms[0].action = url;
			document.forms[0].submit();
			return true;


	return false;
}
function getDept()
{
	if(document.getElementById(cmbAdminOffice).value=='ZP'){
		var url;
		url = "ifms.htm?actionFlag=retirveDept";
		document.forms[0].method = 'post';
		document.forms[0].action = url;
		document.forms[0].submit();
	}
}

function checkforHirechy(){
	//var check=document.getElementById('radioFinalLevel2').value;
	if(document.getElementById('radioFinalLevel2').checked){
		document.getElementById('txtFinalDDOCode').value="";
		document.getElementById('txtSpecialDDOCode').value="";
		document.getElementById('txtFinalDDOCode').readOnly = true;
		document.getElementById('txtSpecialDDOCode').readOnly = true;
	}
	if(document.getElementById('radioFinalLevel3').checked){
		
		document.getElementById('txtSpecialDDOCode').value="";
		document.getElementById('txtFinalDDOCode').readOnly = false;
		document.getElementById('txtSpecialDDOCode').readOnly = true;
	}
	if(document.getElementById('radioFinalLevel4').checked){
		document.getElementById('txtRepDDOCode').readOnly = false;
		document.getElementById('txtFinalDDOCode').readOnly = false;
		document.getElementById('txtSpecialDDOCode').readOnly = false;
	}
}


function checkEqualDDOCode() {
		var rep = document.getElementById('txtRepDDOCode').value;
		var finald = document.getElementById('txtFinalDDOCode').value;
		var spl = document.getElementById('txtSpecialDDOCode').value;
		if ((rep == finald && rep != "" && finald != "")
				|| (rep == spl && rep != "" && spl != "")
				|| (finald == spl && finald != "" && spl != "")) {
			document.getElementById('txtRepDDOCode').value = "";
			document.getElementById('txtFinalDDOCode').value = "";
			document.getElementById('txtSpecialDDOCode').value = "";
			return false;
		}
		return true;
}
	
function confirmPage() {

	if (validateForm_ZpDDOOffice()) {
		
		showProgressbar();
		//alert("Hi");
		//window.open("/dcps/web/WEB-INF/jsp/hrms/eis/zp/DDO Office/zpHirechyDDOOffice.jsp","childPage",width="750");
		var cw = window
				.open(
						"",
						"",
						"status=yes,toolbar=no,menubar=no,location=no,scrollbars=yes,resizable=yes,width=750,height=200");
		cw.document.write('<style media="print">');
		cw.document.write('@FONT-FACE { font-family : Arial;}');
		cw.document.write('@media print {BODY { font-size: 10pt }}');
		cw.document.write('@media screen {BODY { font-size: 10pt } }');
		cw.document
				.write('@media screen, print { BODY { line-height: 1.2 }}');
		cw.document.write('@page cheque{ size 12in 12in; margin: 0.5cm }');
		cw.document.write('DIV {page: cheque; page-break-after: left;  }');
		cw.document.write('</style>');
		cw.document.write('<body>');
		cw.document
				.write('<TABLE border="1" width="100%" id="tbl" align="center">');

		cw.document.write('<TH>');
		cw.document.write('Sr.No.');
		cw.document.write('</TH>');
		cw.document.write('<TH>');
		cw.document.write('Hierarchy Level');
		cw.document.write('</TH>');

		if (document.getElementById("radioFinalLevel2").checked) {
			cw.document.write('<TR>');
			cw.document.write('<TD align="center">');
			cw.document.write('1');
			cw.document.write('</TD>');
			cw.document.write('<TD align="center">');
			cw.document
					.write(document.getElementById("txtRepDDOCode").value);
			cw.document.write('</TD>');
			cw.document.write('</TR>');
		}

		
		cw.document.write('</TABLE>');
		cw.document.write('<BR>');
		cw.document.write('<CENTER>');
		cw.document
				.write('<BUTTON id="btnOk" name="btnOk" onclick="check();">O.K.</BUTTON>');
		cw.document.write('</CENTER>');
		/* var printString = document.getElementById("txtSpecialDDOCode").value;
		cw.document.write(printString);	 */
		cw.document.write('</body>');
		cw.document.write('<script language="javascript">');

		cw.document.write('var Cnfm;');
		//cw.document.write("function check(){Cnfm=confirm('Hirechy has been set.Are you Confirm with this?');if(Cnfm=='true'){var url;url ='ifms.htm?actionFlag=saveZpDDODtls';document.forms['ZpDDOOffice'].method = 'post';document.forms['ZpDDOOffice'].action = url;document.forms['ZpDDOOffice'].submit();return true;}}");
		cw.document
				.write("function check()"
						+ "{Cnfm=confirm('Hierarchy has been set.Are you Confirm with this?');"
						+ "if(Cnfm){"
						+ "var url;"
						+ "url ='ifms.htm?actionFlag=saveZpDDODtls';"
						+ "window.opener.document.forms['ZpDDOOffice'].method = 'post';"
						+ "window.opener.document.forms['ZpDDOOffice'].action = url;"
						+ "window.opener.document.forms['ZpDDOOffice'].submit();"
						+ "window.close();}}");
		//cw.document.write("window.print();");
		cw.document.write('<' + "/script" + '>');

		//cw.location.reload(false);
	}
}

	//added by samadhan
	function addSalutationToName() {
		//alert('inside addSalutationToName');
		//document.getElementById("txtDDOName").value=document.getElementById("txtDDOName").value
		if (document.getElementById("radioSalutationShri").checked == true) {
			//alert('Shree selected');
			document.getElementById("txtDDOName").value = 'Shri.';
		}
		if (document.getElementById("radioSalutationSmt").checked == true) {
			//alert('Smt Selected');
			document.getElementById("txtDDOName").value = 'Smt.';
		}
	}
	function validateDDOName() {
		//alert('inside validateDDOName()');
		var txt = document.getElementById("txtDDOName").value;
		var regex = /^[ A-Za-z.-]*$/;
		if (regex.test(txt)) {
			//alert('Valid Text')
		} else {
			alert('Please Enter Valid DDO Name.\nOnly Characters are allowed in DDO Name.');
			document.getElementById("txtDDOName").value = '';
			if (document.getElementById("radioSalutationShri").checked == true) {
				//alert('Shree selected');
				document.getElementById("txtDDOName").value = 'Shri.';
			}
			if (document.getElementById("radioSalutationSmt").checked == true) {
				//alert('Smt Selected');
				document.getElementById("txtDDOName").value = 'Smt.';
			}
			return false;
		}
	}

	function validateMobileNo() {
		//alert('inside validateMobileNo');
		var mobileNo = document.getElementById("txtMobileNo").value;
		//alert(mobileNo);
		var regex = /^[0-9]*$/;
		if (regex.test(mobileNo)) {
			//alert('Valid Text')
		} else {
			alert('Please enter only digit in Mobile No. field');
			document.getElementById("txtMobileNo").value = '';
			return false;
		}
		if (mobileNo.length != 10) {
			alert('Please enter complete Mobile No.');
			document.getElementById("txtMobileNo").value = '';
			return false;
		}
		if (!(mobileNo.charAt(0) == 7 || mobileNo.charAt(0) == 8 || mobileNo
				.charAt(0) == 9)) {
			//alert(mobileNo.charAt(0));
			alert('Please enter valid mobile No.');
			document.getElementById("txtMobileNo").value = '';
			return false;
		}
	}
	function validateEmailId() {
		var emailId = document.getElementById("txtEmailId").value;
		//alert(emailId);
		var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;

		if (!filter.test(emailId)) {
			alert('Please enter valid email address.');
			document.getElementById("txtEmailId").value = '';
			return false;
		}
	}
</script>
<%
	}catch (Exception e) {
		e.printStackTrace();
	}
%>

<ajax:select source="cmbAdminOffice" 
		target="cmbDistOffice" 
		baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=retriveDiscOfcList"
		eventType="change" 
		parameters="ofcId={cmbAdminOffice}">
	</ajax:select>
<ajax:select source="cmbAdminOffice" 
		target="cmbDept" 
		baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=retirveDept"
		eventType="change" 
		parameters="ofcId={cmbAdminOffice}">
	</ajax:select>
<ajax:autocomplete source="txtDDODsgn" target="txtDDODsgn"
	baseUrl="ifms.htm?actionFlag=getFirstDesignationForAutoComplete"
	parameters="searchKey={txtDDODsgn}" className="autocomplete" minimumCharacters="2" indicator="roleIndicatorRegion" />