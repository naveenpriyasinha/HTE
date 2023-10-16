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


<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="script/hrms/hr/award/award.js"></script>

<script type="text/javascript">
	var awardMsg = new Array();
	awardMsg[0] = '<fmt:message bundle="${awdlbl}" key="ess.clickView"/>'; //Click to View Details
	awardMsg[1] = '<fmt:message bundle="${awdlbl}" key="ess.clickView"/>'; //Click to View Details
	awardMsg[2] = '<fmt:message bundle="${awdlbl}" key="ess.clickViewEdit"/>'; //Click here to View/Edit
	awardMsg[3] = '<fmt:message bundle="${awdalert}" key="ess.Cat"/>'; // Please select Award Category
	awardMsg[4] = '<fmt:message bundle="${awdalert}" key="ess.Type"/>'; // Please select Award Type
	awardMsg[5] = '<fmt:message bundle="${awdalert}" key="ess.Name"/>'; // Please select Award Name
	awardMsg[6] = '<fmt:message bundle="${awdalert}" key="ess.For"/>'; //Please select Awarded For
	awardMsg[7] = '<fmt:message bundle="${awdalert}" key="ess.ForSearch"/>'; //Please Search and Select Employees 
	awardMsg[8] = '<fmt:message bundle="${awdalert}" key="ess.AjaxAlert"/>'; // Your browser does not support AJAX
	awardMsg[9] = '--<fmt:message bundle="${awdlbl}" key="ess.select"/>--'; // Select
	awardMsg[10] = '<fmt:message  bundle="${awdlbl}" key="ess.Other"/>'; // Add new
	var awardMst = null;
</script>



</head>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="lListlObj" value="${resValue.awdCatList}" > </c:set>	
<c:set var="lListlObjFor" value="${resValue.awdForList}"> </c:set>
<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="tableBGColor" value="#FFFFFF"></c:set>
<body>

<hdiits:form name="frm1" validate="true" method="POST"	encType="multipart/form-data">

<div id="tabmenu" >
	<ul id="maintab" class="shadetabs">
		<li class="selected" ><a href="#" rel="tcontent1"><b><hdiits:caption captionid="ess.awards" bundle="${awdlbl}" captionLang="single"></hdiits:caption></b></a></li>
	</ul>
	
</div>
<div class="tabcontentstyle" style="height: 100%">
<div id="tcontent1" class="tabcontent" tabno="0">
<br>
	
<hdiits:fieldGroup titleCaptionId="ess.awdSelDtls" bundle="${awdlbl}" >
<table id="" width="80%" align="center" >	

<tr>
			
			<td class="fieldLabel" width="20%"><b><hdiits:caption captionid="ess.awdCat" bundle="${awdlbl}"></hdiits:caption></b></td>
			<td class="fieldLabel" width="20%">
				<hdiits:select captionid="ess.awdCat" bundle="${awdlbl}" name="awdCat" id="awdCat"  mandatory="true" onchange="getAwdType(this);"> 
				<hdiits:option value="Select">--<fmt:message key="ess.select" bundle="${awdlbl}"></fmt:message>--</hdiits:option>
					<c:forEach var="unit" items="${lListlObj}">
			    		<option value="<c:out value="${unit.awardCode}" />"><c:out value="${unit.awardCategory }"/>
						</option>
					</c:forEach>
				</hdiits:select>
			</td>
			
			<td class="fieldLabel" width="20%"><b><hdiits:caption captionid="ess.awdType" bundle="${awdlbl}"></hdiits:caption></b></td>
			
			<td class="fieldLabel" width="20%">
				<hdiits:select id="awdType" name="awdType" captionid="ess.awdType" bundle="${awdlbl}"  mandatory="true" onchange="getAwdName(this,awdCat);">
				<hdiits:option value="Select">--<fmt:message key="ess.select" bundle="${awdlbl}"></fmt:message>--</hdiits:option>				
					<c:forEach var="unit" items="${lListlObj1 }">
			    		<option value="<c:out value="${unit.awardTypeCode}" />"><c:out value="${unit.awardType}"/>
						</option>
					</c:forEach>
				
				</hdiits:select>
			</td>		
</tr>
<tr></tr>
<tr>
			
			<td class="fieldLabel" width="20%"><b><hdiits:caption captionid="ess.awdName" bundle="${awdlbl}"></hdiits:caption></b></td>
			
			<td class="fieldLabel" width="20%">
				<hdiits:select captionid="ess.awdName" bundle="${awdlbl}" name="awdName" id="awdName"  mandatory="true" onchange="getAwardedFor(this, awdCat, awdType)"> 
				<hdiits:option value="Select">--<fmt:message key="ess.select" bundle="${awdlbl}"></fmt:message>--</hdiits:option>
					<c:forEach var="unit" items="${lListlObj1}">
			    	 <option value="<c:out value="${unit.awardNameCode}" />"><c:out value="${unit.awardName}"/>
									</option>
					</c:forEach>
				</hdiits:select>
			</td>
			
			<td class="fieldLabel" width="20%"><b><hdiits:caption captionid="ess.awdedFor" bundle="${awdlbl}"></hdiits:caption></b></td>
			
			<td class="fieldLabel" width="20%"><hdiits:select id="awdedFor" name="awdedFor" captionid="ess.awdedFor" bundle="${awdlbl}"  mandatory="true" onchange="chkforAwdFor();">
			<option value="Select">--<fmt:message key="ess.select" bundle="${awdlbl}"></fmt:message>--</option>				
			<c:forEach var="unit1" items="${lListlObjFor }">
			    	<option value="<c:out value="${unit1.awardedFor}" />"><c:out value="${unit1.awardedFor}"/>
									</option>
					</c:forEach>
			</hdiits:select></td>
						
</tr>
</table>
</hdiits:fieldGroup>
<hdiits:fieldGroup bundle="${awdlbl}" titleCaptionId="ess.awdEmpList">
	<table class="TableBorderLTRBN" id="EmpListDtlsTab" border="1" cellpadding="0" cellspacing="0"  name= "EmpListDtlsTab" align="center" width="90%" borderColor="black" style="border-collapse: collapse;background-color: ${tableBGColor}">					
			
			<tr>
			<td style="display:none">&nbsp;</td>
			<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="ess.select" bundle="${awdlbl}"></hdiits:caption></b></td>						
			<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="ess.srno" bundle="${awdlbl}"></hdiits:caption></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="ess.name" bundle="${awdlbl}"></hdiits:caption></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="ess.desig" bundle="${awdlbl}"></hdiits:caption></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="ess.preAwdPmt" bundle="${awdlbl}"></hdiits:caption></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="ess.othrDtls" bundle="${awdlbl}"></hdiits:caption></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="ess.reasnofrec" bundle="${awdlbl}"></hdiits:caption></b></td>
			<td style="display:none;"></td>
			<td style="display:none;"></td>
			<td style="display:none;"></td>
			<td style="display:none;"></td>
			</tr>	
							
	</table>
</hdiits:fieldGroup>

<BR>
<BR>
<table align="center">
<tr>
	<center>
	<td></td>
	<td>
	<hdiits:button type="button"  
	name="srchButton"  id="srchButton" captionid="ess.Search" bundle="${awdlbl}"
	onclick="SearchEmp();"  style="width:100px"/>
	</td>
	<td></td>
	<td>
	<hdiits:button type="button"  
		name="subButton" id="subButton" captionid="ess.Submit" bundle="${awdlbl}"
		onclick="submitReq()"  style="width:100px"/>
	</td>
	<td></td>
	<td>
	<hdiits:button type="button" 
		name="closeButton" id="closeButton" captionid="ess.Close" bundle="${awdlbl}"
		onclick="closewindow()"  style="width:100px"/>
	</td>
	<td></td>
	</tr>
</table>

</div>
 


</div>
<script type="text/javascript">		
		initializetabcontent("maintab")
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
