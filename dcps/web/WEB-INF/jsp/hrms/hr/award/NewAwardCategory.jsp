<%
try{
%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../../../core/include.jsp" %>

<fmt:setBundle basename="resources.hr.award.award_AlertMessages" var="awdalert" scope="request"/>
<fmt:setBundle basename="resources.hr.award.award" var="awdlbl" scope="request"/>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="lListlObj" value="${resValue.awdCatList}" ></c:set>
<c:set var="lListlObjFor" value="${resValue.awdForList}"></c:set>
<c:set var="lList2Obj" value="${resValue.awdTypeList}" ></c:set>	
<c:set var="lList3Obj" value="${resValue.awdNameList}"></c:set>
<c:set var="objAwardMst" value="${resValue.objAwardMst}"></c:set>
<c:set var="engAwardedBy" value="${resValue.engAwardedBy}"></c:set>
<c:set var="gujAwardedBy" value="${resValue.gujAwardedBy}"></c:set>
<c:set var="duplicate" value="${resValue.duplicate}"></c:set>
<script type="text/javascript" src="script/hrms/hr/award/award.js"></script>
<body>



<script type="text/javascript">
	var awardMsg = new Array();
	awardMsg[0] = '<fmt:message  bundle="${awdlbl}" key="ess.clickView"/>'; //Click to View Details
	awardMsg[1] = '<fmt:message  bundle="${awdlbl}" key="ess.clickView"/>'; //Click to View Details
	awardMsg[2] = '<fmt:message  bundle="${awdlbl}" key="ess.clickViewEdit"/>'; //Click here to View/Edit
	awardMsg[3] = '<fmt:message  bundle="${awdalert}" key="ess.Cat"/>'; // Please select Award Category
	awardMsg[4] = '<fmt:message  bundle="${awdalert}" key="ess.Type"/>'; // Please select Award Type
	awardMsg[5] = '<fmt:message  bundle="${awdalert}" key="ess.Name"/>'; // Please select Award Name
	awardMsg[6] = '<fmt:message  bundle="${awdalert}" key="ess.For"/>'; //Please select Awarded For
	awardMsg[7] = '<fmt:message  bundle="${awdalert}" key="ess.ForSearch"/>'; //Please Search and Select Employees 
	awardMsg[8] = '<fmt:message  bundle="${awdalert}" key="ess.AjaxAlert"/>'; // Your browser does not support AJAX
	awardMsg[9] = '--<fmt:message  bundle="${awdlbl}" key="ess.select"/>--'; // Select
	awardMsg[10] = '<fmt:message  bundle="${awdlbl}" key="ess.Other"/>'; // Add new
	

function closewindow()
{		
	var urlstyle="hdiits.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
	document.AwardNewCategory.action=urlstyle;
	document.AwardNewCategory.submit();
}


function onSendingData()
{

	if(document.getElementById('awdCat').selectedIndex=='0')
	{	
		alert('<fmt:message  bundle="${awdalert}" key="ess.Cat"/>');
		document.getElementById('awdCat').focus();
		return false;
	}
	if(document.getElementById('awdCat').value=="Other" && (document.getElementById('awdCatEng').value=='' || document.getElementById('awdCatGuj').value==''))
	{	
		alert('<fmt:message  bundle="${awdalert}" key="ess.plzAwdCat"/>');
		if(document.getElementById('awdCatEng').value=='')
		{
			document.getElementById('awdCatEng').focus();
			return false;
		}
		if(document.getElementById('awdCatGuj').value=='')
		{
			document.getElementById('awdCatGuj').focus();
			return false;
		}		
	}
		
	if(document.getElementById('awdType').selectedIndex=='0')
	{	
		alert('<fmt:message  bundle="${awdalert}" key="ess.Type"/>');
		document.getElementById('awdType').focus();
		return false;
	}
	if(document.getElementById('awdType').value=="Other" && (document.getElementById('awdTypeEng').value=='' || document.getElementById('awdTypeGuj').value==''))
	{	
		alert('<fmt:message  bundle="${awdalert}" key="ess.plzAwdType"/>');
		if(document.getElementById('awdTypeEng').value=='')
		{
			document.getElementById('awdTypeEng').focus();
			return false;
		}
		if(document.getElementById('awdTypeGuj').value=='')
		{
			document.getElementById('awdTypeGuj').focus();
			return false;
		}
		
	}
	if(document.getElementById('awdName').selectedIndex=='0')
	{	
		alert('<fmt:message  bundle="${awdalert}" key="ess.Name"/>');
		document.getElementById('awdName').focus();
		return false;
	}
	if(document.getElementById('awdName').value=="Other" && (document.getElementById('awdNameEng').value=='' || document.getElementById('awdNameGuj').value==''))
	{	
		alert('<fmt:message  bundle="${awdalert}" key="ess.plzAwdName"/>');
		if(document.getElementById('awdNameEng').value=='')
		{
			document.getElementById('awdNameEng').focus();
			return false;
		}
		if(document.getElementById('awdNameGuj').value=='')
		{
			document.getElementById('awdNameGuj').focus();
			return false;
		}		
	}
	if(document.getElementById('awdedFor').selectedIndex=='0')
	{	
		alert('<fmt:message  bundle="${awdalert}" key="ess.For"/>');
		document.getElementById('awdedFor').focus();
		return false;
	}
	if(document.getElementById('awdedFor').value=="Other" && (document.getElementById('awdedForEng').value=='' || document.getElementById('awdedForGuj').value==''))
	{	
		alert('<fmt:message  bundle="${awdalert}" key="ess.plzAwdFor"/>');
		if(document.getElementById('awdedForEng').value=='')
		{
			document.getElementById('awdedForEng').focus();
			return false;
		}
		if(document.getElementById('awdedForGuj').value=='')
		{
			document.getElementById('awdedForGuj').focus();
			return false;
		}		
	}

	if(document.getElementById('awdByEng').value=='')
	{	
		alert('<fmt:message  bundle="${awdalert}" key="ess.awdByEng"/>');
		document.getElementById('awdByEng').focus();
		return false;
	}

	if(document.getElementById('awdByGuj').value=='')
	{	
		alert('<fmt:message  bundle="${awdalert}" key="ess.awdByGuj"/>');
		document.getElementById('awdByGuj').focus();
		return false;	
	}

	if(document.getElementById('awdRs').value=='')
	{	
		alert('<fmt:message  bundle="${awdalert}" key="ess.awdRs"/>');
		document.getElementById('awdRs').focus();
		return false;	
	}

	if(document.getElementById('awdDt').value=='')
	{	
		alert('<fmt:message  bundle="${awdalert}" key="ess.awdDt"/>');
		document.getElementById('awdDt').focus();
		return false;	
	}
	if(document.getElementById('awdStartEndDt').value=='')
	{	
		if(document.AwardNewCategory.rdoActivate[0].checked)
			alert('<fmt:message  bundle="${awdalert}" key="ess.plzStrtDt"/>');
		else
			alert('<fmt:message  bundle="${awdalert}" key="ess.plzEndDt"/>');
		document.getElementById('awdStartEndDt').focus();
		return false;	
	}
	var awdId = "${objAwardMst.awardId}";
	document.AwardNewCategory.action="hrms.htm?actionFlag=getNewCategorySubmitData&ReqId="+awdId;
	document.AwardNewCategory.submit();

}

function changeLabel()
{
	if(document.AwardNewCategory.rdoActivate[0].checked)
	{
		document.getElementById("startDateId").style.display = '';
		document.getElementById("endDateId").style.display = 'none';
	}
	else
	{
		document.getElementById("startDateId").style.display = 'none';
		document.getElementById("endDateId").style.display = '';
	}
	document.getElementById('awdStartEndDt').focus();
}

function showCatTxt(obj)
{	
	if(obj.value == "Other")
	{
		document.getElementById("Cat1").style.display = '';
		document.getElementById("Cat2").style.display = '';
		document.getElementById("Cat3").style.display = '';
		document.getElementById("altCat1").style.display = 'none';
		document.getElementById("altCat2").style.display = 'none';
		document.getElementById("altCat3").style.display = 'none';
		document.getElementById("engAndGuj1").style.display = '';
		document.getElementById("engAndGuj2").style.display = '';
		var x=document.getElementById('awdType');
		var y=document.getElementById('awdName');
		var z=document.getElementById('awdedFor');
		for (var i=x.length-2;i>0;i--)  // For Removing previous values of combo
		{
			x.remove(i);		
		}		
		for (var i=y.length-2;i>0;i--)  // For Removing previous values of combo
		{	     				     					
			y.remove(i);		
		}
		for (var i=z.length-2;i>0;i--)  // For Removing previous values of combo
		{	     				     					
			z.remove(i);		
		}
		var addNew = document.createElement('option');
       	addNew.text = "Add New";
		addNew.value='Other';
		if(x.length == 1)
			x.add(addNew);
		if(y.length == 1)
			y.add(addNew);
		if(z.length == 1)
			z.add(addNew);
		
		x.selectedIndex='Select';	    	 		
		y.selectedIndex='Select';
		z.selectedIndex='Select';
	}
	else
	{
		getAwdType(obj,"newAward");
		if(obj.value == "Select")
		{
			document.getElementById("Type1").style.display = 'none';
			document.getElementById("Type2").style.display = 'none';
			document.getElementById("Type3").style.display = 'none';
			document.getElementById("awdTypeEng").value = '';
			document.getElementById("awdTypeGuj").value = '';
			
			document.getElementById("Name1").style.display = 'none';
			document.getElementById("Name2").style.display = 'none';
			document.getElementById("Name3").style.display = 'none';
			document.getElementById("awdNameEng").value = '';
			document.getElementById("awdNameGuj").value = '';			
		}
		document.getElementById("Cat1").style.display = 'none';
		document.getElementById("Cat2").style.display = 'none';
		document.getElementById("Cat3").style.display = 'none';
		document.getElementById("altCat1").style.display = '';
		document.getElementById("altCat2").style.display = '';
		document.getElementById("altCat3").style.display = '';
		document.getElementById("awdCatEng").value = '';
		document.getElementById("awdCatGuj").value = '';
		if(document.getElementById("awdType").value != "Other" && document.getElementById("awdName").value != "Other" && document.getElementById("awdedFor").value != "Other")
		{
			document.getElementById("engAndGuj1").style.display = 'none';
			document.getElementById("engAndGuj2").style.display = 'none';
		}
	}
}

function showTypetxt(obj,awdCat)
{
	//alert("awdCat value----"+awdCat.value);
	if(obj.value == "Other")
	{
		document.getElementById("Type1").style.display = '';
		document.getElementById("Type2").style.display = '';
		document.getElementById("Type3").style.display = '';
		document.getElementById("engAndGuj1").style.display = '';
		document.getElementById("engAndGuj2").style.display = '';
		var y=document.getElementById('awdName');
		for (var i=y.length-2;i>0;i--)  // For Removing previous values of combo :: Award name
		{	     				     					
			y.remove(i);		
		}
		var addNew = document.createElement('option');
       	addNew.text = "Add New";
		addNew.value='Other';
		if(y.length == 1)
			y.add(addNew);		
		 	 		
		y.selectedIndex='Select';
		
		var z=document.getElementById('awdedFor');
		for (var i=z.length-2;i>0;i--)  // For Removing previous values of combo :: Awarded For
		{	     				     					
			z.remove(i);		
		}
		addNew = document.createElement('option');
       	addNew.text = "Add New";
		addNew.value='Other';
		if(z.length == 1)
			z.add(addNew);		
		 	 		
		z.selectedIndex='Select';
		
	}
	else
	{
		//alert("awartd type -->>"+obj.value);
		//alert("awartd awdCat -->>"+awdCat.value);
		getAwdName(obj,awdCat,"newAward");
		document.getElementById("Type1").style.display = 'none';
		document.getElementById("Type2").style.display = 'none';
		document.getElementById("Type3").style.display = 'none';
		document.getElementById("awdTypeEng").value = '';
		document.getElementById("awdTypeGuj").value = '';
		if(document.getElementById("awdCat").value != "Other" && document.getElementById("awdName").value != "Other" && document.getElementById("awdedFor").value != "Other")
		{
			document.getElementById("engAndGuj1").style.display = 'none';
			document.getElementById("engAndGuj2").style.display = 'none';
		}
	}
}

function showNametxt(obj, objAwdCat, objAwdType)
{
	if(obj.value == "Other")
	{
		document.getElementById("Name1").style.display = '';
		document.getElementById("Name2").style.display = '';
		document.getElementById("Name3").style.display = '';
		document.getElementById("engAndGuj1").style.display = '';
		document.getElementById("engAndGuj2").style.display = '';
		
		var z=document.getElementById('awdedFor');
		for (var i=z.length-2;i>0;i--)  // For Removing previous values of combo :: Awarded For
		{	     				     					
			z.remove(i);		
		}
		addNew = document.createElement('option');
       	addNew.text = "Add New";
		addNew.value='Other';
		if(z.length == 1)
			z.add(addNew);		
		 	 		
		z.selectedIndex='Select';
	}
	else
	{
		getAwardedFor(obj, objAwdCat, objAwdType, "newAward");
		document.getElementById("Name1").style.display = 'none';
		document.getElementById("Name2").style.display = 'none';
		document.getElementById("Name3").style.display = 'none';
		document.getElementById("awdNameEng").value = '';
		document.getElementById("awdNameGuj").value = '';
		if(document.getElementById("awdCat").value != "Other" && document.getElementById("awdType").value != "Other" && document.getElementById("awdedFor").value != "Other")
		{
			document.getElementById("engAndGuj1").style.display = 'none';
			document.getElementById("engAndGuj2").style.display = 'none';
		}
	}
}

function showFortxt(obj)
{
	if(obj.value == "Other")
	{
		document.getElementById("awdFor1").style.display = '';
		document.getElementById("awdFor2").style.display = '';
		document.getElementById("awdFor3").style.display = '';
		document.getElementById("engAndGuj1").style.display = '';
		document.getElementById("engAndGuj2").style.display = '';
	}
	else
	{
		document.getElementById("awdFor1").style.display = 'none';
		document.getElementById("awdFor2").style.display = 'none';
		document.getElementById("awdFor3").style.display = 'none';
		document.getElementById("awdedForEng").value = '';
		document.getElementById("awdedForGuj").value = '';
		if(document.getElementById("awdCat").value != "Other" && document.getElementById("awdType").value != "Other" && document.getElementById("awdName").value != "Other")
		{
			document.getElementById("engAndGuj1").style.display = 'none';
			document.getElementById("engAndGuj2").style.display = 'none';
		}
	}
}

function checkIfNumber(fieldValue)
{
	var num;
	num = window.event.keyCode;
	if (eval(num)<48||eval(num)>57)
	{
		return false;
	}	
	else 
	{
		return true;
	}
}

function goback()
{
	var urlstyle="hdiits.htm?actionFlag=getExistingAwdList";
	document.AwardNewCategory.action=urlstyle;
	document.AwardNewCategory.submit();
}
</script>
<hdiits:form name="AwardNewCategory" validate="true" method="POST" 	encType="multipart/form-data">
<fmt:formatDate var="awardedDate" pattern="dd/MM/yyyy" value="${objAwardMst.awardDate}" type="date" />
<fmt:formatDate var="awardStartDt" pattern="dd/MM/yyyy" value="${objAwardMst.awardStartDt}" type="date" />
<fmt:formatDate var="awardEndDt" pattern="dd/MM/yyyy" value="${objAwardMst.awardEndDt}" type="date" />
<div id="tabmenu" >
	<ul id="maintab" class="shadetabs">
		<li class="selected" ><a href="#" rel="tcontent1"><b><hdiits:caption captionid="ess.awards" bundle="${awdlbl}" captionLang="single"></hdiits:caption></b></a></li>
	</ul>
	
</div>
<div class="tabcontentstyle" style="height: 100%">
<div id="tcontent1" class="tabcontent" tabno="0">
<br>


<table width="100%" class="tabtable">
<tr><td width="15%" class="fieldLabel"></td><td width="25%" class="fieldLabel"></td><td width="20%" class="fieldLabel"></td><td width="20%" id="engAndGuj1" style="display:none" class="fieldLabel"><hdiits:caption captionid="ess.InEnglish" bundle="${awdlbl}"/></td><td width="20%" id="engAndGuj2" style="display:none" class="fieldLabel"><hdiits:caption captionid="ess.InGujarati" bundle="${awdlbl}"/></td></tr>

<tr><td width="15%" class="fieldLabel"><hdiits:caption captionid="ess.awdCat" bundle="${awdlbl}" /></td>
	<td width="25%" class="fieldLabel"><hdiits:select captionid="ess.awdCat" bundle="${awdlbl}" name="awdCat" id="awdCat" sort="false" onfocus="" onchange="showCatTxt(this)" mandatory="true" > 
				<hdiits:option value="Select">--<fmt:message key="ess.select" bundle="${awdlbl}"></fmt:message>--</hdiits:option>
					<c:forEach var="unit" items="${lListlObj}">
			    		<option value="<c:out value="${unit.awardCode}" />"><c:out value="${unit.awardCategory}"/>
						</option>						
					</c:forEach>
					<option value="Other" /><fmt:message key="ess.Other" bundle="${awdlbl}"/>
						</option>
				</hdiits:select></td>
	
	<td width="20%" id="Cat1" style="display:none" class="fieldLabel"><hdiits:caption captionid="ess.newAwdCat" bundle="${awdlbl}" /></td>
	<td width="20%" id="Cat2" style="display:none" class="fieldLabel"><hdiits:text name="awdCatEng" id="awdCatEng" onfocus="" mandatory="true"/></td>
	<td width="20%" id="Cat3" style="display:none" class="fieldLabel"><hdiits:text name="awdCatGuj" id="awdCatGuj" onfocus="" mandatory="true"/></td>
	<td width="20%" id="altCat1" class="fieldLabel"></td>
	<td width="20%" id="altCat2" class="fieldLabel"></td>
	<td width="20%" id="altCat3" class="fieldLabel"></td>
</tr>



<tr><td><hdiits:caption captionid="ess.awdType" bundle="${awdlbl}" /></td>
	<td><hdiits:select id="awdType" name="awdType" captionid="ess.awdType" bundle="${awdlbl}" sort="false" onfocus="" onchange="showTypetxt(this, awdCat)" mandatory="true" >
				<hdiits:option value="0">--<fmt:message key="ess.select" bundle="${awdlbl}"></fmt:message>--</hdiits:option>				
					<c:forEach var="unit" items="${lList2Obj }">
			    		<option value="<c:out value="${unit.awardTypeCode}" />"><c:out value="${unit.awardType}"/>
						</option>
					</c:forEach>
					<option value="Other" /><fmt:message key="ess.Other" bundle="${awdlbl}"/>
						</option>
				</hdiits:select></td>
	
	<td id="Type1" style="display:none"><hdiits:caption captionid="ess.newAwdType" bundle="${awdlbl}" /></td>
	<td id="Type2" style="display:none"><hdiits:text name="awdTypeEng" id="awdTypeEng" onfocus=""  mandatory="true"/></td>
	<td id="Type3" style="display:none"><hdiits:text name="awdTypeGuj" id="awdTypeGuj" onfocus="" mandatory="true"/></td>
</tr>


<tr><td><hdiits:caption captionid="ess.awdName" bundle="${awdlbl}" /></td>
	<td><hdiits:select captionid="ess.awdName" bundle="${awdlbl}" name="awdName" id="awdName" sort="false" onfocus="" onchange="showNametxt(this, awdCat, awdType)" mandatory="true"> 
				<hdiits:option value="0">--<fmt:message key="ess.select" bundle="${awdlbl}"></fmt:message>--</hdiits:option>
					<c:forEach var="unit" items="${lList3Obj}">
			    	 <option value="<c:out value="${unit.awardNameCode}" />"><c:out value="${unit.awardName}"/>
									</option>
					</c:forEach>
					<option value="Other" /><fmt:message key="ess.Other" bundle="${awdlbl}"/>
						</option>
				</hdiits:select></td>
	
	<td id="Name1" style="display:none"><hdiits:caption captionid="ess.newAwdName" bundle="${awdlbl}" /></td>
	<td id="Name2" style="display:none"><hdiits:text name="awdNameEng" id="awdNameEng" onfocus="" mandatory="true"/></td>
	<td id="Name3" style="display:none"><hdiits:text name="awdNameGuj" id="awdNameGuj" onfocus="" mandatory="true"/></td>
</tr>



<tr><td><hdiits:caption captionid="ess.awdedFor" bundle="${awdlbl}" /></td>
	<td><hdiits:select id="awdedFor" name="awdedFor" captionid="ess.awdedFor" bundle="${awdlbl}" sort="false" onfocus="" onchange="showFortxt(this)" mandatory="true">
			<option value="0">--<fmt:message key="ess.select" bundle="${awdlbl}"></fmt:message>--</option>				
			<c:forEach var="unit1" items="${lListlObjFor}">
			    	<option value="<c:out value="${unit1.awardedFor}" />"><c:out value="${unit1.awardedFor}"/>
									</option>
					</c:forEach>
					<option value="Other" /><fmt:message key="ess.Other" bundle="${awdlbl}"/>
						</option>
			</hdiits:select></td>
	
	<td id="awdFor1" style="display:none"><hdiits:caption captionid="ess.newAwdedFor" bundle="${awdlbl}" /></td>
	<td id="awdFor2" style="display:none"><hdiits:text name="awdedForEng" id="awdedForEng" onfocus="" mandatory="true"/></td>
	<td id="awdFor3" style="display:none"><hdiits:text name="awdedForGuj" id="awdedForGuj" onfocus="" mandatory="true"/></td>
</tr>
</table>
<br>

<table width="100%" class="tabtable">
<tr><td width="15%" class="fieldLabel"></td><td  width="25%"><hdiits:caption captionid="ess.InEnglish" bundle="${awdlbl}"/></td><td><hdiits:caption captionid="ess.InGujarati" bundle="${awdlbl}"/></td></tr>
<tr><td class="fieldLabel"><hdiits:caption captionid="ess.awdBy" bundle="${awdlbl}"/></td>
<td><hdiits:text name="awdByEng" id="awdForEng" mandatory="true"/></td>
<td><hdiits:text name="awdByGuj" id="awdForGuj" mandatory="true"/></td>
</tr>
<tr><td><hdiits:caption captionid="ess.awdRs" bundle="${awdlbl}"/></td>
	<td><hdiits:text name="awdRs" id="awdRs" mandatory="true" maxlength="10" onkeypress="return checkIfNumber(this.value)" style="text-align: right"/></td>
</tr>
<tr><td><hdiits:caption captionid="ess.awdDt" bundle="${awdlbl}"/></td>
	<td><hdiits:dateTime name="awdDt" captionid="ess.awdDt" bundle="${awdlbl}" mandatory="true" /></td>
</tr>
<tr>
	<td><hdiits:caption captionid="awd.activateFlag" bundle="${awdlbl}"/></td>
	<td><hdiits:radio name="rdoActivate" value="Y" captionid="awd.active" bundle="${awdlbl}" mandatory="false" default="Y" onclick="changeLabel()"/><br>
	<hdiits:radio name="rdoActivate" value="N" captionid="awd.deactive" bundle="${awdlbl}" mandatory="false" onclick="changeLabel()"/></td>
</tr>
<tr>
	<td id="startDateId" ><hdiits:caption captionid="awd.startDt" bundle="${awdlbl}"/></td>
	<td id="endDateId" style="display:none"><hdiits:caption captionid="awd.endDt" bundle="${awdlbl}"/></td>
	<td><hdiits:dateTime name="awdStartEndDt" captionid="ess.awdDt" bundle="${awdlbl}" mandatory="true" /></td>
</tr>

</table>
<br>

<table align="center">
<tr>
<c:if test="${objAwardMst eq null}">
	<td><hdiits:button type="button" name="subButton" id="subButton" captionid="ess.Submit" bundle="${awdlbl}" style="width:100px" onclick="onSendingData()"/></td>
</c:if>
<c:if test="${objAwardMst ne null}">
	<td><hdiits:button type="button" name="subButton" id="subButton" captionid="awd.update" bundle="${awdlbl}" style="width:100px" onclick="onSendingData()"/></td>
</c:if>
<td><hdiits:button type="button" name="Back" id="back" captionid="awd.back" bundle="${awdlbl}" style="width:100px" onclick="goback()" /></td>
<td><hdiits:button type="button" name="close" id="close" captionid="ess.Close" bundle="${awdlbl}" style="width:100px" onclick="closewindow()" />
</td>
</tr>
</table>
<br>
	<c:if test="${duplicate eq 'duplicate'}">
	<center><b><font color="red"><fmt:message key="awd.alreadyExist" bundle="${awdlbl}" /></font></b></center>
	</c:if>
	<c:if test="${duplicate eq 'updated'}">
	<center><b><font color="red"><fmt:message key="awd.updated" bundle="${awdlbl}" /></font></b></center>
	</c:if>
	<c:if test="${duplicate eq 'inserted'}">
	<center><b><font color="red"><fmt:message key="awd.inserted" bundle="${awdlbl}" /></font></b></center>
	</c:if>
<br>
</div>
 </div>

 
<script type="text/javascript">		
	initializetabcontent("maintab");

	var awardMst = "${objAwardMst}";
	var awdTypeCodeVal= "${objAwardMst.awardTypeCode}";
	var awdNameCodeVal= "${objAwardMst.awardNameCode}";
	var awdedForCodeVal = "${objAwardMst.awardedFor}";
	
	if ("${objAwardMst}" != null && "${objAwardMst}" != '')
	{			
		document.getElementById("awdCat").value = '${objAwardMst.awardCode}';
		showCatTxt(document.getElementById("awdCat"), "${objAwardMst.awardTypeCode}");
		document.getElementById("awdRs").value = '${objAwardMst.awardRupees}';		
		document.getElementById("awdByEng").value = '${engAwardedBy}';
		document.getElementById("awdByGuj").value = '${gujAwardedBy}';
		document.getElementById("awdDt").value = '${awardedDate}';
		if("${objAwardMst.awardStartDt}" != null && "${objAwardMst.awardStartDt}" != '')
		{
			document.getElementById("awdStartEndDt").value = '${awardStartDt}';
			document.AwardNewCategory.rdoActivate[0].checked = 'true';
			document.getElementById("startDateId").style.display = '';
			document.getElementById("endDateId").style.display = 'none';
		}
		else
		{
			document.getElementById("awdStartEndDt").value = '${awardEndDt}';
			document.AwardNewCategory.rdoActivate[1].checked = 'true';
			document.getElementById("startDateId").style.display = 'none';
			document.getElementById("endDateId").style.display = '';
		}
	}
	
</script>

<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />

</hdiits:form>
</body>
</html>
 <%
}
  	  catch(Exception e)
  	  {
  		  
  		  e.printStackTrace();
  	  }
 %>
