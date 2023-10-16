<%
	try {
%>

<%@ include file="../../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<fmt:setBundle basename="resources.eis.zp.zpDDOOffice.ZpDDOOfficeLabels_en_US" var="ZpDDOOfficeLabels" scope="request"/>
<fmt:setBundle basename="resources.eis.eisLables_en_US" var="DistrictOfficeLables" scope="request" />
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLabels" scope="request"/>
<fmt:setBundle basename="resources.dcps.DcpsAlerts" var="dcpsAlerts" scope="request"/>
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<c:set var="resultObj" value="${result}"/>
<c:set var="resValue" value="${resultObj.resultValue}"/>
<c:set var="updateFlag" value="${resValue.updateFlag}"/>
<c:set var="AdminOfficeList" value="${resValue.ZpAdminOfficeMstList}"/>
<c:set var="DistOfcList" value="${resValue.DistOfcList}"/>
<c:set var="locId" value="${resValue.locId}"/>
<c:set var="cmntreasuryMstList" value="${resValue.cmntreasuryMstList}"></c:set>
<html>
<head>
<script type="text/javascript" src="<c:url value="/script/common/ajaxLogoutError.js"/>"></script>
<script type="text/javascript" src="script/common/cmnMstScr.js"></script>
<script type="text/javascript">
function trimData(ele){
	ele.value=trim(ele.value);
}

function trim(str)
{
	return str.replace(/^\s+|\s+$/g,"");
}
function canceal()
{
	var url;
	url = "ifms.htm?actionFlag=loadLevel2DDOScreen";
	document.forms[0].method = 'post';
	document.forms[0].action = url;
	document.forms[0].submit();
}


function checkTextEmpty(ele,msg){
	if(document.getElementById(ele).value=="")
		{
			alert(msg);
			return false;
		}
	return true;
}
function isComboSelexcted(ele,msg){
	if(document.getElementById(ele).value==-1)
		{
			alert(msg);
			return false;
		}
	return true;
}
function generateDDOCode()
{
 var tcode=document.getElementById("tCode").value;
 var tSubTCode=0000;
 
	 var subTCode=document.getElementById("subTCode").value;
	 var DistOfc=document.getElementById("cmbDistOffice").value;
	 
	 if(subTCode==-1)
		 tSubTCode=tcode;
	 else
		 tSubTCode=subTCode;
	if(checkTextEmpty("cmbDdoCode","Please Select DDO CODE"))
			if(isComboSelexcted("cmbFieldDept","Please Select Field Department"))
					if(isComboSelexcted("cmbAdminDept","Please Select Admin Department"))
						if(document.getElementById("cmbAdminOffice").value!==-1)
							if(document.getElementById("cmbAdminOffice").value!==-1)
								if(tSubTCode!=0000)
				{
	
	
	url = "ifms.htm?actionFlag=saveReportingDDOCode";
	document.forms[0].method = 'post';
	document.forms[0].action = url;
	document.forms[0].submit();
	showProgressbar('Please wait,Your Request is in progress');
	return true;}
				else
					return false;
}



function checkDDOCode()
{
	 var AdminOfc=document.getElementById("cmbAdminOffice").value;
	 var tSubTCode=0000;
	 var tcode=document.getElementById("tCode").value;
	 
	 var subTCode=document.getElementById("subTCode").value;
	 var DistOfc=document.getElementById("cmbDistOffice").value;
	 if(subTCode==-1)
		 tSubTCode=tcode;
	 else
		 tSubTCode=subTCode;
	 var url;
	 if(AdminOfc==-1 || DistOfc==-1 ||tcode==-1  )
	 {
		 alert("Please select all combo Values First");
		 return false;
	 }
	 var uri = 'ifms.htm?actionFlag=generateReportingDDOCode&tSubTCode='+tSubTCode+'&adminOffice='+AdminOfc;
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
	var CreatedDDOCode= XmlHiddenValues[0].childNodes[0].firstChild.nodeValue; 
	if (CreatedDDOCode) {
		alert('Level 2 DDO Code generated Scuccessfully');		
		document.getElementById("txtDDOCode").value=CreatedDDOCode;
		//document.getElementById("txtDDOCode").disabled="disabled";
		
	} 
	
}
</script>
</head>
<body>
<hdiits:form name="frmDReportingDDO" action="" validate="true" method="post" encType="multipart/form-data">
<hdiits:hidden name="locId" id="locId" default="${locId}"/> 
	<div id="tabmenu" >
	<ul id="maintab" class="shadetabs">
		<li class="selected" ><a href="#" rel="tcontent1" class="selected" style="width:200%"><b>   <fmt:message key="ZP.DefineLvl2DDO" ></fmt:message>
		</b></a></li>
	</ul>
	</div>
	<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >
	<br/>
	                  
	                  
		<input type="hidden" value="lvl2" name="lvl2" id="lvl2"/>
<table border="0">
		<tr>
			<td width="15%">
	    		<b><hdiits:caption captionid="ZPDDOOOFFICE_TREASURY" bundle="${ZpDDOOfficeLabels}"/></b>
	    		</td>
	    	
			<td width="35%">
				<hdiits:select name="tCode" id="tCode" captionid="DISTRICT_NAME" bundle="${DistrictOfficeLables}" default="${zpadminOfficeedit.distCode}" validation="txt.isrequired" style="width:550px">
					<hdiits:option value="-1"><fmt:message key="ZPDDOOFFICE_SELECT" bundle="${ZpDDOOfficeLabels}"></fmt:message></hdiits:option>
					<c:forEach var="cmntreasuryMstList" items="${cmntreasuryMstList}">
						<option value="<c:out value="${cmntreasuryMstList[0]}"/>">
						<c:out value="${cmntreasuryMstList[1]}"/></option>
						
					</c:forEach>
				</hdiits:select>				
			</td>
		</tr>
		
		<tr>
			<td width="15%"><b><hdiits:caption captionid="ZPDDOOOFFICE_SUB_TREASURY" bundle="${ZpDDOOfficeLabels}"/></b></td>
			<td width="35%">
				<hdiits:select name="subTCode" id="subTCode"  bundle="${ZpDDOOfficeLabels}" style="width:550px">
					<hdiits:option value="-1" ><fmt:message key="ZPDDOOFFICE_SELECT" bundle="${ZpDDOOfficeLabels}"></fmt:message></hdiits:option>
				</hdiits:select>
			</td>
		</tr>
		
		
		<tr>
			<td width="15%"><hdiits:caption captionid="ZPDDOOFFICE_ZPADMINOFFICE" bundle="${ZpDDOOfficeLabels}"/></td>
			<td width="35%">
				<select name="cmbAdminOffice" id="cmbAdminOffice" style="width:550px" >
					<option value="-1"><fmt:message key="ZPDDOOFFICE_SELECT" /></option>
					<c:forEach items="${AdminOfficeList}" var="AdminOfficeList">					
						<c:choose>
								<c:when test="${AdminOfficeList[2]=='01' || AdminOfficeList[2]=='1' }">
									<option value='<c:out value="${AdminOfficeList[2]}"/>' disabled="disabled">
									<c:out value="${AdminOfficeList[1]}"/>
										</option>
								</c:when>
								<c:otherwise>
		     						 <option value='<c:out value="${AdminOfficeList[2]}"/>'>
		         					   <c:out value="${AdminOfficeList[1]}"/>
		       						</option>
								</c:otherwise>
						</c:choose>			
					</c:forEach>
				</select>
			</td>
		</tr>
		
		<tr>
			<td width="15%"><hdiits:caption captionid="ZPDDOOFFICE_ZPDISTRICTOFFICE" bundle="${ZpDDOOfficeLabels}"/></td>
			<td width="35%">
				<hdiits:select name="cmbDistOffice" id="cmbDistOffice"  bundle="${ZpDDOOfficeLabels}" style="width:550px" >
					<option value="-1"><fmt:message key="ZPDDOOFFICE_SELECT" bundle="${ZpDDOOfficeLabels}"></fmt:message></select>
				</hdiits:select>
			</td>
		</tr>
		

		<tr>
			<td width="400px"><fmt:message key="CMN.ADMNDEPT"
				bundle="${dcpsLabels}"></fmt:message>
			</td>
				
			<td  width="35%">
				<select name="cmbAdminDept" id="cmbAdminDept" style="width:550px">
					<option value="-1"><fmt:message key="ZPDDOOFFICE_SELECT" bundle="${ZpDDOOfficeLabels}"></fmt:message></option>
					    <c:forEach var="AdminDpt" items="${resValue.AdminDept}" >
					    	<c:choose>
								<c:when test="${AdminDpt.id != 10023}">
									<%-- <option value="${AdminDpt.id}" disabled="disabled">${AdminDpt.desc}</option> --%>
								</c:when>
								<c:otherwise>
				     						 <option value="${AdminDpt.id}" >${AdminDpt.desc}</option>
								</c:otherwise>
							</c:choose>						 
				      </c:forEach>
				</select>
			</td>
		</tr>
			
		<tr>
			<td  width="15%"><fmt:message key="CMN.FIELDDEPT"
				bundle="${dcpsLabels}"></fmt:message></td>
			<td width="35%"><select name="cmbFieldDept" id="cmbFieldDept" style="width:800px">
				<option value="-1"><fmt:message key="ZPDDOOFFICE_SELECT1" bundle="${ZpDDOOfficeLabels}"></fmt:message></option>
			</td>
			
		</tr>
		
		<tr>
			<td width="15%"><hdiits:caption captionid="ZP.lvl2DDO" bundle="${ZpDDOOfficeLabels}"/></td>
			<td width="35%">
				<hdiits:select name="cmbDdoCode" id="cmbDdoCode"  bundle="${ZpDDOOfficeLabels}" style="width:800px">
					<hdiits:option value="-1" ><fmt:message key="ZPDDOOFFICE_SELECT1" bundle="${ZpDDOOfficeLabels}"></fmt:message></hdiits:option>
				</hdiits:select>
			</td>
			
		</tr>
		
		
		<!--<tr>
			<td><hdiits:caption captionid="ZPDDOOOFFICE_OFFICE_NAME" bundle="${ZpDDOOfficeLabels}"/></td>
			<td>
			<hdiits:text name="txtOfficeName" id="txtOfficeName" mandatory="true" maxlength="500" captionid="ZPDDOOOFFICE_OFFICE_NAME"  bundle="${ZpDDOOfficeLabels}"  validation="txt.isrequired" default="" />
			</td>
		</tr>
		
		--><tr>
			<td>&nbsp;</td>
		<tr>
		
		<tr>
			<td colspan="2" align="center">
				<hdiits:button name="btnSave" type="button" captionid="ZPADMINOFFICE_SAVE" bundle="${ZpDDOOfficeLabels}" onclick="generateDDOCode();"></hdiits:button>
				<hdiits:button name="btnCancel" type="button" captionid="ZPADMINOFFICE_CANCEL" bundle="${ZpDDOOfficeLabels}" onclick="canceal();"></hdiits:button>
			</td>
		</tr>
		
	
		</table>
	</div>		
</hdiits:form>



<%
	}catch (Exception e) {
		e.printStackTrace();
	}
%>
<ajax:select source="cmbAdminOffice" 
		target="cmbDistOffice" 
		baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=retriveDiscOfcList"
		eventType="change" 
		parameters="ofcId={cmbAdminOffice},flag={lvl2},treasuryId={tCode} ">
	</ajax:select>

	<ajax:select source="tCode" target="subTCode" 
		baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=loadSubTreasuryOffice"
		eventType="change" 
		parameters="treasuryId={tCode}">
	</ajax:select>
	
	<ajax:select source="cmbAdminDept" target="cmbFieldDept"
	eventType="change"
	baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=popFieldDept"
	parameters="cmbAdminDept={cmbAdminDept},ofcId={cmbAdminOffice}">
</ajax:select>

<ajax:select source="cmbFieldDept" target="cmbDdoCode"
	eventType="change"
	baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=generateReportingDDOCode"
	parameters="cmbAdminDept={cmbAdminDept},cmbFieldDept={cmbFieldDept},treasuryId={tCode}">
</ajax:select>
