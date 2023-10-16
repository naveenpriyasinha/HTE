
<%
try {
%>
<%@ include file="../../../../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<fmt:setBundle basename="resources.eis.eisLables_en_US" var="DistrictOfficeLables" scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="cmnDistrictMstList" value="${resValue.cmnDistrictMstList}"></c:set>
<c:set var="zpadminOffice" value="${resValue.zpadminOffice}"></c:set>
<c:set var="editFlag" value="${resValue.editFlag}"></c:set>
<c:set var="distId" value="${resValue.distId}"></c:set>
<c:set var="zpadminOfficeedit" value="${resValue.zpadminOfficeedit}"></c:set>


<html>
<head>
<script type="text/javascript" src="<c:url value="/script/common/ajaxLogoutError.js"/>"></script>
<script type="text/javascript" src="script/common/cmnMstScr.js"></script>
<script type="text/javascript">
document.onload = disableField;

function disableField(){

	
alert("done");
		
	
}
</script>
</head>
<body onload="disableField()">
	

<hdiits:form name="frmDistrictOffice" action="" validate="true" method="post" encType="multipart/form-data">
<hdiits:hidden name="" default="${distId}"/>
<hdiits:hidden name="editFlag" default="${editFlag}"/>  


<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b>District Office Master</b></a></li>
		</ul>
	</div>
	<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >
	<table width="100%">
		<tr>
			<td width="25%">
	    		<b><hdiits:caption captionid="DISTRICT_NAME" bundle="${DistrictOfficeLables}"/></b>
	    	</td>
	    	<c:if test="${editFlag ne 'Y'}">
			<td width="25%">
				<hdiits:select name="selDistrictname" id="selDistrictname" captionid="DISTRICT_NAME" bundle="${DistrictOfficeLables}" default="" onchange="resetAdminOffice();">
					<hdiits:option value="0"><fmt:message key="EIS.Select" bundle="${DistrictOfficeLables}"/></hdiits:option>
					<c:forEach var="cmnDistrictMstList" items="${cmnDistrictMstList}">
						<option value="<c:out value="${cmnDistrictMstList[0]}"/>">
						<c:out value="${cmnDistrictMstList[1]}"/></option>
					</c:forEach>
				</hdiits:select>				
			</td>
			</c:if> 
			<c:if test="${editFlag eq 'Y'}">
			<td width="25%">
				<hdiits:select readonly="true"  name="selDistrictname" id="selDistrictname" captionid="DISTRICT_NAME" bundle="${DistrictOfficeLables}" default="${zpadminOfficeedit.distCode}" >
					<hdiits:option value="0"><fmt:message key="EIS.Select" bundle="${DistrictOfficeLables}"/></hdiits:option>
					<c:forEach var="cmnDistrictMstList" items="${cmnDistrictMstList}">
						<option value="<c:out value="${cmnDistrictMstList[0]}"/>" disabled="disabled">
						<c:out value="${cmnDistrictMstList[1]}"/></option>
					</c:forEach>
				</hdiits:select>				
			</td>
			</c:if> 
			<td width="25%"></td>
			<td width="25%"></td>
		</tr>
		
		<tr>
			<td width="25%">
	    		<b><hdiits:caption captionid="ADMIN_OFFICE" bundle="${DistrictOfficeLables}"/></b>
	    	</td>
	    	<c:if test="${editFlag ne 'Y'}">
			<td width="25%">
				<hdiits:select  name="selAdminOffice" id="selAdminOffice" captionid="ADMIN_OFFICE" bundle="${DistrictOfficeLables}" default="" onchange="popUpDistOfcName(this);checkExistingOffice();">
					<hdiits:option value="0"><fmt:message key="EIS.Select" bundle="${DistrictOfficeLables}"/></hdiits:option>
						
				<c:forEach var="zpadminOffice" items="${zpadminOffice}">
						<option value="<c:out value="${zpadminOffice.officeCode}"/>">
						<c:out value="${zpadminOffice.officeName}"/></option>
					</c:forEach>
				</hdiits:select>				
			</td>
			</c:if>
			<c:if test="${editFlag eq 'Y'}">
			<td width="25%">
				<hdiits:select readonly="true" name="selAdminOffice" id="selAdminOffice" captionid="ADMIN_OFFICE" bundle="${DistrictOfficeLables}" default="${zpadminOfficeedit.adminOfficeCode}" onchange="checkExistingOffice();">
					<hdiits:option value="0"><fmt:message key="EIS.Select" bundle="${DistrictOfficeLables}"/></hdiits:option>
						
				<c:forEach var="zpadminOffice" items="${zpadminOffice}">
						<option value="<c:out value="${zpadminOffice.officeCode}"/>">
						<c:out value="${zpadminOffice.officeName}"/></option>
					</c:forEach>
				</hdiits:select>				
			</td>
			</c:if>
			<td width="25%"></td>
			<td width="25%"></td>
		</tr>
		<tr>
			<td width="25%">
	    		<b><hdiits:caption captionid="DISTRICT_OFFICE_NAME" bundle="${DistrictOfficeLables}"/></b>
	    	</td>
	    	<c:if test="${editFlag ne 'Y'}">
			<td width="25%">
				<hdiits:text id="txtdistrictOfficename" name="txtdistrictOfficename" captionid="DISTRICT_OFFICE_NAME" bundle="${DistrictOfficeLables}" default="" readonly="true" />			
			</td>
			</c:if>
			<c:if test="${editFlag eq 'Y'}">
			<td width="25%">
				<hdiits:text id="txtdistrictOfficename" name="txtdistrictOfficename" captionid="DISTRICT_OFFICE_NAME" bundle="${DistrictOfficeLables}" default="${zpadminOfficeedit.distMstOfficeName}" />			
			</td>
			</c:if>
	    	<td width="25%"></td>
	    		
			
			<td width="25%"></td>
				
		</tr>
	</table>
	<br><br>
	<table align="center">
		<tr>
			<td align="center">
			<c:if test="${editFlag ne 'Y'}">
				<hdiits:button name="btnSave" id="btnSave" type="button" captionid="eis.save" bundle="${DistrictOfficeLables}" onclick="savedistrictData()"/>
			</c:if>
			<c:if test="${editFlag eq 'Y'}">
				<hdiits:button name="btnSave" id="btnSave" type="button" captionid="eis.save" bundle="${DistrictOfficeLables}" onclick="savedistrictDataedit()"/>
			</c:if>
				<hdiits:button name="btnClose" id="btnClose" type="button" captionid="eis.close" bundle="${DistrictOfficeLables}" onclick="CloseScreen()"/>
			</td>
		</tr>
	</table>
	<br>
	</div>
	
	

<hdiits:validate locale="${locale}" controlNames="" />
<script type="text/javascript">	
	initializetabcontent("maintab");

function savedistrictData(){

	var selDistrictname = document.getElementById('selDistrictname').value;
	var selAdminOffice = document.getElementById('selAdminOffice').value;
	var txtdistrictOfficename=document.forms["frmDistrictOffice"]["txtdistrictOfficename"].value;
	
	
	if (selDistrictname == '0') {
				alert("Please Select District Name");
				document.getElementById('selDistrictname').focus();
				return false;
		
			}
	if (selAdminOffice == '0') {
		alert("Please Select Admin Office Name");
		document.getElementById('selAdminOffice').focus();
		return false;

	}
	if (txtdistrictOfficename==null || txtdistrictOfficename=="")
	  {
		alert("Please Enter District Office Name");
		document.getElementById('txtdistrictOfficename').focus();
		return false;
	  }
	
	 var urlstyle="ifms.htm?actionFlag=SaveDistrictmpg";
     document.forms[0].action=urlstyle;
     document.forms[0].submit();
}
function savedistrictDataedit(){

	var selDistrictname = document.getElementById('selDistrictname').value;
	var selAdminOffice = document.getElementById('selAdminOffice').value;
	var txtdistrictOfficename=document.forms["frmDistrictOffice"]["txtdistrictOfficename"].value;
	
	if (selDistrictname == '0')
          {
				alert("Please Select District Name");
				document.getElementById('selDistrictname').focus();
				return false;
		
          }	
	if (selAdminOffice == '0') 
	{
		alert("Please Select Admin Office Name");
		document.getElementById('selAdminOffice').focus();
		return false;
	}
	if (txtdistrictOfficename==null || txtdistrictOfficename=="")
	  {
		alert("Please Enter District Office Name");
		document.getElementById('txtdistrictOfficename').focus();
		return false;
	  }
	var urlstyle="ifms.htm?actionFlag=SaveDistrictmpg&edit=Y&distId=${distId}";
    document.forms[0].action=urlstyle;
    document.forms[0].submit();

}

function CloseScreen(){
	var urlstyle="ifms.htm?actionFlag=validateLogin";
    document.forms[0].action=urlstyle;
    document.forms[0].submit();
}	

function popUpDistOfcName(){
	var selDistrictname = document.getElementById('selDistrictname').value;
	var selAdminOffice = document.getElementById('selAdminOffice').value;
	var selDistrictnameobj=document.getElementById('selDistrictname');
	var selDistrictnametxt=selDistrictnameobj.options[selDistrictnameobj.selectedIndex].text;
	var selAdminOfficeobj=document.getElementById('selAdminOffice');
	var selAdminOfficetxt=selAdminOfficeobj.options[selAdminOfficeobj.selectedIndex].text;
	var txtdistrictOfficename=document.forms["frmDistrictOffice"]["txtdistrictOfficename"].value;
	
	if (selDistrictname == '0')
          {
				alert("Please Select District Name");
				document.getElementById('selDistrictname').focus();
				return false;
		
          }
	else{
		document.forms["frmDistrictOffice"]["txtdistrictOfficename"].value=selAdminOfficetxt+", "+selDistrictnametxt;
	}
		
}
function resetAdminOffice(){
	document.getElementById('selAdminOffice').value="0";
	document.forms["frmDistrictOffice"]["txtdistrictOfficename"].value="";
}

function checkExistingOffice()
{
	//alert('inside checkExistingOffice()');
	var distCode = document.getElementById("selDistrictname").value;
	var adminOfficeCode = document.getElementById("selAdminOffice").value;
	//alert('distCode: '+distCode+' adminOfficeCode: '+adminOfficeCode);
	if(distCode == 0)
	{
		resetAdminOffice();
	}
	else
	{
		var uri = 'ifms.htm?actionFlag=validateDistrictOffice';
		var url = 'distCode='+distCode+'&adminOfficeCode='+adminOfficeCode;

		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function(myAjax) {
			checkDistrictOffice(myAjax);
			
		},
        onFailure: function()
        			{ 
	  						alert('Something went wrong...');
	  					} 
			          } 
	);
	}
}
function checkDistrictOffice(myAjax)
{
	var status;
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var checkFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	//alert("checkFlag"+checkFlag);
	if(checkFlag=='correct')
	{
		status= true;
		//alert('all ok');
	}
	else if(checkFlag=='wrong')
	{
	
	
	alert('Entered District Office is already created. Please select correct District office.');
	resetAdminOffice();
	status= false;
	}
return status;
}
</script>

</hdiits:form>
</body>

</html>



<%
}catch(Exception e) {e.printStackTrace();}
%>
