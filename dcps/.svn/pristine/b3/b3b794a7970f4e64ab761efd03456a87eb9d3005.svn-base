<%@ include file="../../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<fmt:setBundle basename="resources.eis.zp.zpAdminOffice.ZpAdminOfficeLabels_en_US" var="ZpAdminOfficeLabels" scope="request"/>
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables" scope="request"/>

<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<c:set var="resultObj" value="${result}"/>
<c:set var="resValue" value="${resultObj.resultValue}"/>
<c:set var="updateFlag" value="${resValue.updateFlag}"/>
<c:set var="ZpAdminOfficeMst" value="${resValue.ZpAdminOfficeMstVO}"/>
<c:set var="OfcCode" value="${resValue.OfcCode}"/>
<c:set var="lstGRNames" value="${resValue.lstGRNames}"/>
<c:set var="GRDate" value="${resValue.GRDate}"/>
<c:set var="adminBranchname" value="${resValue.adminBranchname}"/>
<fmt:formatDate value="${ZpAdminOfficeMst.grDate}" pattern="dd/MM/yyyy" dateStyle="medium" var="grDate"/>
<fmt:formatDate value="${resultObj.resultValue.current_date}" pattern="dd/MM/yyyy" dateStyle="medium" var="currentDateJs"/>
<script type="text/javascript" src='<c:url value="script/common/validation.js"/>'></script>
<script type="text/javascript" src='<c:url value="script/common/tagLibValidation.js"/>'></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src='<c:url value="script/common/commonfunctions.js"/>'></script>
<script type="text/javascript" src='<c:url value="script/common/tabcontent.js"/>'></script>
<script type="text/javascript" src='<c:url value="script/common/script/calendar.js"/>'> </script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>

<script type="text/javascript" src="<c:url value="/script/exptracking/zp_admin_ofc/admin/zpAdminOffice.js"/>"></script>
<style type="text/css">
#schemeName{
visibility: hidden;
}
#schemeNameLabel{
visibility: hidden;
}
</style>
 <script type="text/javascript">

 function setSchemeCode()
 {
 	document.getElementById("txtDcpsShemeCode").value=document.getElementById("cmbSchemeName").value;
 }
 function getGRDate()
 {
 	//alert("getGRDate Calling");
 	var GRNO=document.getElementById("GrNo").value;
 	document.getElementById("cmbGrNo").value=GRNO;
 	//alert(GRNO);
 	var url = "ifms.htm?actionFlag=retriveDateFromGRNo&cmbGrNo="+GRNO;
 	 //document.ZpAdminOffice.action = url ;
 //	document.ZpAdminOffice.submit(); 
 	
 	//var url = "";
	 var myAjax = new Ajax.Request(url,
		       {
		        method: 'post',
		        asynchronous: true,
		        parameters:url,
		        onSuccess: function (myAjax) {
		
					 dispGrDate(myAjax);
		        	
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } ); 
 }
 function dispGrDate(myAjax)
 {

		document.getElementById("txtDcpsGRDate").value="";
	 	var XMLDoc = myAjax.responseXML.documentElement;
		var XmlHiddenValues = XMLDoc.getElementsByTagName('value');
		var GRDate = XmlHiddenValues[0].firstChild.nodeValue;
		document.getElementById("txtDcpsGRDate").value=GRDate;
		
 }
 
 function popUpSchemeName()
 {
	 if (document.getElementById) { // DOM3 = IE5, NS6
		 document.getElementById('schemeName').style.visibility = 'visible';
		 document.getElementById('schemeNameLabel').style.visibility = 'visible';
		 }
		 else {
		 if (document.layers) { // Netscape 4
			 document.getElementById('schemeName').style.visibility = 'visible';
			 document.getElementById('schemeNameLabel').style.visibility = 'visible';
		 }
		 else { // IE 4
			 document.getElementById('schemeName').style.visibility = 'visible';
			 document.getElementById('schemeNameLabel').style.visibility = 'visible';
		 }
		 } 
	var txtSchemeCode = document.getElementById("txtDcpsShemeCode").value;
 	if(txtSchemeCode.length >= 4)
 	{
 		var uri="ifms.htm?actionFlag=displaySchemeNameForCode&txtSchemeCode="+txtSchemeCode;
 		var url="txtSchemeCode="+txtSchemeCode;
 		
 		var myAjax = new Ajax.Request(uri,
 			       {
 			        method: 'post',
 			        asynchronous: false,
 			        parameters:url,
 			        onSuccess: function(myAjax) {
 						getDataStateChangedForPopUpSchemes(myAjax);
 					},
 			        onFailure: function(){ alert('Something went wrong...')} 
 			          } );
 	}
 	else
 	{
 		var cmbSchemeName = document.getElementById('cmbSchemeName');
 		cmbSchemeName.options.length = 0;
 		var optn = document.createElement("OPTION");
 		optn.text = "-- Select --";
 		optn.value = "-1";
 		cmbSchemeName.options.add(optn);
 	}
 }

 function getDataStateChangedForPopUpSchemes(myAjax)
 {
 	XMLDoc = myAjax.responseXML.documentElement;
 	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
 	var cmbSchemeName = document.getElementById('cmbSchemeName');
 	cmbSchemeName.options.length = 0;
 	var optn = document.createElement("OPTION");
 	optn.text = "-- Select --";
 	optn.value = "-1";
 	cmbSchemeName.options.add(optn);

 	var totalSchemes = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
 	if(totalSchemes<=0){
 	 	alert("Please enter valid Scheme code.There is no scheme starting with "+document.getElementById("txtDcpsShemeCode").value);
 	 	 document.getElementById("txtDcpsShemeCode").value="";
 	 	 document.getElementById('schemeName').style.visibility = 'hidden';
		 document.getElementById('schemeNameLabel').style.visibility = 'hidden';
 	 	return false;
 	}
 	var optnScheme ;

 		var count=1;
 		while(count<=(2*totalSchemes))
 		{
 			optnScheme = document.createElement("OPTION");
 			optnScheme.text = XmlHiddenValues[0].childNodes[count].firstChild.nodeValue;
 			optnScheme.title= XmlHiddenValues[0].childNodes[count+1].firstChild.nodeValue;
 			optnScheme.value = XmlHiddenValues[0].childNodes[count+1].firstChild.nodeValue;
 			document.getElementById("cmbSchemeName").options.add(optnScheme);
 			count=count+2;
 		}
 		
 		alert("Please selcet scheme from scheme Name option");
 }

 function adminCodecopytoOfficeCode(){
	 document.getElementById('txtCode').value = document.getElementById('cmbAdminOfficeName').value;
	 var cmbObj= document.getElementById('cmbAdminOfficeName');
	 var comVal= cmbObj.options[cmbObj.selectedIndex].text;
	 document.getElementById('txtAdminOfficeName').value= comVal;
	 } 
  
 </script>
<hdiits:form name="ZpAdminOffice"  method="post" action="ifms.htm?actionFlag=save_ZpAdminOffice" validate="true" encType="multipart/form-data">
	<hdiits:hidden name="updateFlag"  default = "${updateFlag}"/>
	<hdiits:hidden name="currentDate" id="currentDate" default="${currentDateJs}"/>
		
	  <div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li ><a href="#" rel="tcontent1" class="selected"><b><div >Admin Office</div></b></a></li>
			
		</ul>
	</div>
	<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >          
	<table>
		<tr>
			<td ><hdiits:caption captionid="ZPADMINOFFICE_ADMIN_OFFICE_NAME" bundle="${ZpAdminOfficeLabels}"/></td>
			<td  >
			<select  name="cmbAdminOfficeName"
				id="cmbAdminOfficeName"  onchange="adminCodecopytoOfficeCode(this);">
				<option value="-1"><fmt:message key="ZPADMINOFFICE_SELECT" bundle="${ZpAdminOfficeLabels}" ></fmt:message></option>
				 <c:forEach items="${adminBranchname}" var="adminBranchname">
								
					<c:choose>
					<c:when
						test="${ZpAdminOfficeMst.officeName eq adminBranchname[1]}">
						<option value='<c:out value="${adminBranchname[0]}"/>' selected="selected" disabled="disabled">
						<c:out value="${adminBranchname[1]}"/>
						</option>
					</c:when>
					<c:otherwise>
						
						 <c:choose>
							<c:when test="${adminBranchname[0]=='01' || adminBranchname[0]=='1' }">
								<option value='<c:out value="${adminBranchname[0]}"/>' disabled="disabled">
								<c:out value="${adminBranchname[1]}"/>
									</option>
							</c:when>
							<c:otherwise>
	     						 <option value='<c:out value="${adminBranchname[0]}"/>'>
	         					   <c:out value="${adminBranchname[1]}"/>
	       						</option>
							</c:otherwise>
						</c:choose>						 
					</c:otherwise>
				</c:choose>	
						
						
				</c:forEach> 
				</select>
				<input type="hidden" value="${ZpAdminOfficeMst.officeName}" name="txtAdminOfficeName" id="txtAdminOfficeName" />
			</td>
			
		</tr>
		<tr>
			<td  ><hdiits:caption captionid="ZPADMINOFFICE_DCPS_Scheme_Code" bundle="${ZpAdminOfficeLabels}"/></td>
			<td ><hdiits:text name="txtDcpsShemeCode" id="txtDcpsShemeCode" mandatory="true" maxlength="10" captionid="ZPADMINOFFICE_DCPS_Scheme_Code"  bundle="${ZpAdminOfficeLabels}"   default="${ZpAdminOfficeMst.schemeCode}"  onblur="chkEnteredSchemeLength();" onkeyup="popUpSchemeName();"/>
			<td ><div id="schemeNameLabel" >
				<hdiits:caption captionid="CMN.SCHEMENAME" bundle="${dcpsLables}"/></div>
				</td>
		<td colspan="3"><div id="schemeName">		<hdiits:select  name="cmbSchemeName"
					id="cmbSchemeName"  onchange="setSchemeCode();">
				<option value="-1"><fmt:message
					key="COMMON.DROPDOWN.SELECT" /></option>
				</hdiits:select>
			<label class="mandatoryindicator">*</label>
			</div>
			</td>
		</tr>
		<tr>
			<td><hdiits:caption captionid="ZPADMINOFFICE_DCPS_GR_NO" bundle="${ZpAdminOfficeLabels}"/></td>
			<td>
							
				
				
				<select  name="GrNo"
				id="GrNo" onchange="getGRDate();">
				<option value="-1"><fmt:message key="ZPADMINOFFICE_SELECT" bundle="${ZpAdminOfficeLabels}"></fmt:message></option>
				<c:forEach items="${lstGRNames}" var="lstGRNames">
				<c:choose>
					<c:when
						test="${ZpAdminOfficeMst.grNo eq lstGRNames}">
						<option value="<c:out value="${lstGRNames}"/>"  selected="selected" disabled="disabled">
						<c:out value="${lstGRNames}"/>
						</option>
					
					</c:when>
					<c:otherwise>
						<option value="<c:out value="${lstGRNames}"/>" >
						<c:out value="${lstGRNames}"/>
						</option>
					</c:otherwise>
				</c:choose>	
				</c:forEach> 
				</select>
				<input type="hidden" value="${ZpAdminOfficeMst.grNo}" name="cmbGrNo" id="cmbGrNo"/>
			</td>
			<td colspan="4">&nbsp;</td>
		</tr>
		<tr>
			<td  ><hdiits:caption captionid="ZPADMINOFFICE_DCPS_GR_DATE" bundle="${ZpAdminOfficeLabels}"/></td>
			<td  ><hdiits:text name="txtDcpsGRDate" id="txtDcpsGRDate" mandatory="false" maxlength="10" captionid="ZPADMINOFFICE_DCPS_GR_DATE"  bundle="${ZpAdminOfficeLabels}"  
			default="${grDate}"  onkeypress="digitFormat(this);dateFormat(this);" readonly="true"  onblur="validateDate(this);chkDate(this);"
			 />
			<img
			src='images/CalendarImages/ico-calendar.gif' width='20'
			onClick='window_open("txtJoinParentDeptDate",375,570)'
			style="cursor: pointer;" ${varImageDisabled} /> <label
			class="mandatoryindicator"${varLabelDisabled} >*</label><fmt:message key="CMN.DATEFORMAT" bundle="${ZpAdminOfficeLabels}"></fmt:message></td>
				<td colspan="4">&nbsp;</td>
			
	<td colspan="6">&nbsp;</td>
		</tr>
		<tr>
			<td  ><hdiits:caption captionid="ZPADMINOFFICE_DCPS_OFF_NAME" bundle="${ZpAdminOfficeLabels}"/></td>
			<td  >
			<%--
			<hdiits:text name="txtDcpsOffName" id="txtDcpsOffName" mandatory="true" maxlength="150" captionid="ZPADMINOFFICE_DCPS_OFF_NAME"  bundle="${ZpAdminOfficeLabels}"  default="${ZpAdminOfficeMst.dcpsOffName}"  validation="txt.isrequired"  onblur="trimData(this)"/>
			--%>
			
				<select  name="txtDcpsOffName"
				id="txtDcpsOffName" >
				<option value="Chief Accounts and Finance Officer Z.P.">Chief Accounts and Finance Officer Z.P.</option>
				<option value="Deputy Director, Education">Deputy Director, Education</option>
				<option value="Comptroller,MAFS University,Nagpur">Comptroller,MAFS University,Nagpur</option>
				<option value="Assistant Tribal Commissioner">Assistant Tribal Commissioner</option>
				<option value="Regional Joint Director">Regional Joint Director</option>
				<option value="Regional Deputy Commissioner,Social Welfare">Regional Deputy Commissioner,Social Welfare</option>
				<option value="Regional Deputy Commissioner,Social Welfare">Regional Deputy Commissioner,Social Welfare</option>
				<option value="Comptroller,Mahatma Phule Agriculture University,Rahuri">Comptroller,Mahatma Phule Agriculture University,Rahuri</option>
				<option value="Comptroller,Dr. Babasaheb Sawant kokan Agriculture University,Dapoli">Comptroller,Dr. Babasaheb Sawant kokan Agriculture University,Dapoli</option>
				<option value="Comptroller,Dr.Punjabrao Deshmukh Agriculture University,Akola">Comptroller,Dr.Punjabrao Deshmukh Agriculture University,Akola</option>
				<option value="Comptroller,Marathwada Agriculture University,Parbhani">Comptroller, Marathwada Agriculture University,Parbhani</option>


				</td>
		<td colspan="4"></td>	</tr>
		<tr>
			<td  ><hdiits:caption captionid="ZPADMINOFFICE_CODE" bundle="${ZpAdminOfficeLabels}"/></td>
			
			  <c:choose>
				<c:when test="${OfcCode!=null}">
					<td  ><hdiits:number floatAllowed="true" name="txtCode" id="txtCode" mandatory="true" maxlength="10" captionid="ZPADMINOFFICE_CODE" bundle="${ZpAdminOfficeLabels}" validation="txt.isrequired" default="${OfcCode!=null ? OfcCode:02}" readonly="true"/>
				</c:when>
				<c:otherwise>
					<td  >
						<hdiits:text name="txtCode" id="txtCode" mandatory="true" maxlength="10" captionid="ZPADMINOFFICE_CODE" bundle="${ZpAdminOfficeLabels}" validation="txt.isrequired" default="${ZpAdminOfficeMst.officeCode}" readonly="true"/>
				</c:otherwise>
			</c:choose>
			</td>
				<td colspan="4">&nbsp;</td>
		</tr>
		<tr>
			<hdiits:hidden name="hdnOfcId" id="hdnOfcId" default="${ZpAdminOfficeMst.ofcId}"/>
				<td colspan="4"></td>
		</tr>
		<tr align="justify">
			<td colspan="6">
				<hdiits:button name="btnSave" type="button" captionid="ZPADMINOFFICE_SAVE" bundle="${ZpAdminOfficeLabels}" onclick="fnZpAdminOfficeSave();"></hdiits:button>
				
				<hdiits:button name="btnCancel" type="button" captionid="ZPADMINOFFICE_CANCEL" bundle="${ZpAdminOfficeLabels}" onclick="fnZpAdminOfficeClose();"></hdiits:button>
			</td>
		</tr>
	</table>
	</div>



	</hdiits:form>
	</div>
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
<script type="text/javascript">


var hidName = "";
var isMode = "";
initializetabcontent("mainTab");
function trimData(ele){
	ele.value=trim(ele.value);
}

function trim(str)
{
	return str.replace(/^\s+|\s+$/g,"");

}
function chkDate(field){


	var currentDate = document.getElementById('currentDate').value ;
	var date1=field.value;


	//alert("===> in validate function.."+currentDate+"date1 :: "+date1);
	var Currsplitval = currentDate.split("/");
	
	var CurrMonth = Currsplitval[1];
	var CurrYear = Currsplitval[2];
	var currDate = Currsplitval[0];
	
	var WEFsplitval = date1.split("/");
	
	var WEFMonth =  WEFsplitval[1];
	var WEFYear = WEFsplitval[2];
	var WEFdate = WEFsplitval[0];
	
	
	//alert("===> in CurrMonth ::"+CurrMonth+"==> WEFMonth :: "+WEFMonth);
	//alert("===> in CurrYear ::"+WEFMonth+"==> WEFYear :: "+WEFYear);
	if(field.value != "")
	{
		if(CurrYear<WEFYear)
		{
			alert("Please select date less then todays date.");
			field.value="";
			field.focus();
			return false;
		}
		
		if(CurrYear==WEFYear)
		{
			if(CurrMonth<WEFMonth )
			{
				alert("Please select date less then todays date.");
				field.value="";
				field.focus();
				return false;
			}
			else if(CurrMonth==WEFMonth && currDate<WEFdate)
			{
				alert("Please select current month of current year.");
				field.value="";
				field.focus();
				return false;
			}
		}
		

	
}
	
}



function fnZpAdminOfficeClose()
{
	var url;
	url = "ifms.htm?actionFlag=search_ZpAdminOffice";
	document.forms[0].method = 'post';
	document.forms[0].action = url;
	document.forms[0].submit();
}

function fnZpAdminOfficeSave()
{
	
	var validateRowStatus = false;
	var validateArray = new Array('txtAdminOfficeName','txtCode','txtDcpsOffName');
	var validateStatus = validateSpecificFormFields(validateArray);
	if(validateStatus)
	{
	validateRowStatus = true;
		if(validateRowStatus)
		{
			var url;
			url = "ifms.htm?actionFlag=saveZpAdminOfficeDtls";
			document.forms[0].method = 'post';
			document.forms[0].action = url;
			document.forms[0].submit();
			return true;
		}
	}
	return false;
}
function chkEnteredSchemeLength(){
	if(document.getElementById("txtDcpsShemeCode").value.length<4 && document.getElementById("txtDcpsShemeCode").value!=""){
		alert("Please enter valid scheme code");
		document.getElementById("txtDcpsShemeCode").value="";
		 document.getElementById('schemeName').style.visibility = 'hidden';
		 document.getElementById('schemeNameLabel').style.visibility = 'hidden';
		return false;
	}
		
}

function checkSchemeCode()
{

	 var schemecode=document.getElementById("txtDcpsShemeCode").value;
	 	schemecode=trim(schemecode); 
	 	document.getElementById("txtDcpsShemeCode").value=schemecode;
	 	var url;
	 
	 var uri = 'ifms.htm?actionFlag=checkSchemeCode&schemeCode='+schemecode;
	 var url = "";
	 var myAjax = new Ajax.Request(uri,
			      {
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function (myAjax) {
			        	getSaveMsg(myAjax);
					},
			        onFailure: function(){ alert('Something went wrong...');} 
			          } );
		
}


function getSaveMsg(myAjax)
{
	
	var XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var isValid= XmlHiddenValues[0].childNodes[0].firstChild.nodeValue; 
	if (isValid==0) {
		alert('Please Enter Valid Scheme Code');		
		document.getElementById("txtDcpsShemeCode").value="";
		return false;
		//document.getElementById("txtDDOCode").disabled="disabled";
		
	} 
	
}


</script>

