<%
try {
%>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<fmt:setBundle basename="resources.common.MstScrLables" var="useHolidayLables" scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="multiLang" value="${resValue.multiLang}"></c:set>
<c:set var="flag" value="${resValue.flag}"></c:set>
<c:set var="langId" value="${resValue.langId}"></c:set>
<c:set var="holidayId" value="${resValue.holidayId}"></c:set>
<c:set var="cmnHolidayMst" value="${resValue.cmnHolidayMst}"></c:set>
<c:set var="cmnHolidayMst_gu" value="${resValue.cmnHolidayMst_gu}"></c:set>
<c:set var="langId" value="${resValue.langId}"></c:set>
<c:set value="${resValue.hldyTypeGU}" var="hldyTypeGU"></c:set>
<c:set value="${resValue.hldyTypeEN}" var="hldyTypeEN"></c:set>
<c:set var="editFlag" value="edit"></c:set>
<c:if test="${flag eq editFlag}">
	<fmt:formatDate var="hldyDate" pattern="dd/MM/yyyy" value="${cmnHolidayMst.hldyDt}"></fmt:formatDate>
	<fmt:formatDate var="hldyDate_gu" pattern="dd/MM/yyyy" value="${cmnHolidayMst_gu.hldyDt}"></fmt:formatDate>
</c:if>

<html>
<head>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/base64.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="script/common/cmnMstScr.js"></script>

<script>

	function copyEngToGuj()
	{		
		document.getElementById("hldyTypetxt_gu").value=document.getElementById("hldyTypetxt").value;	
		document.getElementById("hldyTypetxt_gu").disabled=true;	
		document.frmAdminCrtHoliday.hldyDt_gu.value=document.frmAdminCrtHoliday.hldyDt.value;				
		document.frmAdminCrtHoliday.hldyDt_gu.disabled=true;	
	}
	
	function copyGujToEng()
	{		
		document.getElementById("hldyTypetxt").value=document.getElementById("hldyTypetxt_gu").value;		
		document.getElementById("hldyTypetxt").disabled=true;	
		document.frmAdminCrtHoliday.hldyDt.value=document.frmAdminCrtHoliday.hldyDt_gu.value;						
		document.frmAdminCrtHoliday.hldyDt.disabled=true;	
	}
	
	function submit_frmAdminCrtHoliday()
	{		 
		if('${langId}' == '1')
		{
			copyEngToGuj();			
			var fieldArrayEng1 = new Array('hldyOccsnTxt','hldyTypetxt','hldyDt');
			var statusValidationEng1 = validateSpecificFormFields(fieldArrayEng1);			
			if(statusValidationEng1)
			{				
				if('${multiLang}'=='false' || '${multiLang}'==false)
				{
					showProgressbar("Please Wait... ");
					document.frmAdminCrtHoliday.action="hrms.htm?actionFlag=getHolidayMstData&submit=submitData";
					document.frmAdminCrtHoliday.submit();
				}
				else
				{
					var fieldArrayGuj1 = new Array('hldyOccsnTxt_gu','hldyTypetxt_gu','hldyDt_gu');		 		
					var statusValidationGuj1 = validateSpecificFormFields(fieldArrayGuj1);					
					if(statusValidationGuj1)
					{
							showProgressbar("Please Wait... ");
							document.frmAdminCrtHoliday.action="hrms.htm?actionFlag=getHolidayMstData&submit=submitData";
							document.frmAdminCrtHoliday.submit();
						
					}						
				}					
			}
		}else{
			copyGujToEng();
			var fieldArrayEng1 = new Array('hldyOccsnTxt_gu','hldyTypetxt_gu','hldyDt_gu');		
			var statusValidationEng1 = validateSpecificFormFields(fieldArrayEng1);
			
			if(statusValidationEng1)
			{				
				if('${multiLang}'=='false' || '${multiLang}'==false)
				{
					showProgressbar("Please Wait... ");
					document.frmAdminCrtHoliday.action="hrms.htm?actionFlag=getHolidayMstData&submit=submitData";
					document.frmAdminCrtHoliday.submit();
				}
				else
				{
					var fieldArrayEng1 = new Array('hldyOccsnTxt','hldyTypetxt','hldyDt');
					var statusValidationGuj1 = validateSpecificFormFields(fieldArrayEng1);
					if(statusValidationGuj1)
					{
							showProgressbar("Please Wait... ");
							document.frmAdminCrtHoliday.action="hrms.htm?actionFlag=getHolidayMstData&submit=submitData";
							document.frmAdminCrtHoliday.submit();
						
					}						
				}					
			}
		}
		
	}
	
	
	
	
	function resetData() 
	{
		document.getElementById("hldyOccsnTxt").value="";
		document.getElementById("hldyOccsnTxt_gu").value="";
		document.getElementById("hldyTypetxt").value="Select";
		document.getElementById("hldyTypetxt_gu").value="Select";	
		document.getElementById("hldyGreetMsg").value="";	
		document.getElementById("hldyGreetMsg_gu").value="";	
		document.frmAdminCrtHoliday.hldyDt_gu.value="";		
		document.frmAdminCrtHoliday.hldyDt.value="";		
	}
	
	function closeCurrWindow()
	{
		showProgressbar("Please Wait... ");
		document.frmAdminCrtHoliday.action = "hrms.htm?actionFlag=showHolidayMst";
	   	document.frmAdminCrtHoliday.submit();
	}
	function resetAllData()
	{
		if('${flag}'=='edit')
		{
			globalFlagType = "E";		
			document.getElementById("holidayId").value="${holidayId}";
			document.getElementById('newDsgn').style.display = 'none';	
			document.getElementById('newDsgn_gu').style.display = 'none';
			document.getElementById('editPost_gu').style.display = '';		
			document.getElementById('editPost').style.display = '';	
							
			document.getElementById("hldyOccsnTxt").value='${cmnHolidayMst.hldyOccsn}';
			generateInitCapName('hldyOccsnTxt');
			document.getElementById("hldyOccsnTxt_gu").value='${cmnHolidayMst_gu.hldyOccsn}';
			document.getElementById("hldyTypetxt").value='${cmnHolidayMst.cmnLookupMst.lookupName}';
			document.getElementById("hldyTypetxt_gu").value='${cmnHolidayMst_gu.cmnLookupMst.lookupName}';	
			document.getElementById("hldyGreetMsg").value='${cmnHolidayMst.hldyGreetingMsg}';	
			document.getElementById("hldyGreetMsg_gu").value='${cmnHolidayMst_gu.hldyGreetingMsg}';	
			document.frmAdminCrtHoliday.hldyDt.value="${hldyDate}";		
			document.frmAdminCrtHoliday.hldyDt_gu.value="${hldyDate_gu}";
		}
		else
		{
			resetData();
		}
	}
	function saveData()
	{
		submit_frmAdminCrtHoliday();
	}
</script>
</head>
<body onkeypress="return checkSpecialCharacter(event)">
<hdiits:form name="frmAdminCrtHoliday" action="hrms.htm?actionFlag=getHolidayMstData&submit=submitData" method="post" validate="true" encType="multipart/form-data">
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs" >
		<c:if test="${langId == 1}">
			<li class="selected" id="eng"><a href="#" rel="tcontent1" ><b>
				<fmt:message key="MstScr.English" bundle="${useHolidayLables}"></fmt:message>
			</b></a></li>
			<li class="selected" id="guj" style="display: block"><a href="#" rel="tcontent2" onfocus="copyEngToGuj()"><b>
				<fmt:message key="MstScr.Gujarati" bundle="${useHolidayLables}"></fmt:message>
			</b></a></li>
		</c:if>
		<c:if test="${langId == 2}">
			<li class="selected" id="guj" style="display: block"><a href="#" rel="tcontent1"><b>
				<fmt:message key="MstScr.Gujarati" bundle="${useHolidayLables}"></fmt:message>
			</b></a></li>
			<li class="selected" id="eng"><a href="#" rel="tcontent2" onfocus="copyGujToEng()"><b>
				<fmt:message key="MstScr.English" bundle="${useHolidayLables}"></fmt:message>
			</b></a></li>
		</c:if>
	</ul>
	</div>			
	<div id="createPost" name="createPost" class="tabcontentstyle" >
	<c:if test="${langId == 1}">
		<div id="tcontent1" class="tabcontent" tabno="0">
	</c:if>
	<c:if test="${langId == 2}">
		<div id="tcontent2" class="tabcontent" tabno="1">
	</c:if>
		<table width="100%" id="newDsgn_gu">
			<tr width="100%" align="center"><td><b><fmt:message bundle='${useHolidayLables}' key='Holiday.newOcc'/></b></td></tr>
		</table>
		<table width="100%" id="editPost_gu" style="display:none">
			<tr width="100%" align="center"><td><b><fmt:message bundle='${useHolidayLables}' key='Holiday.updateOcc'/></b></td></tr>
		</table>
		<table class="tabtable">				
			<tr><td class="fieldLabel"><b><hdiits:caption captionid="Holiday.Occ" bundle="${useHolidayLables}"/></b></td>
				<td class="fieldLabel"><hdiits:text name="hldyOccsnTxt" id="hldyOccsnTxt" mandatory="true" captionid="Holiday.Occ" bundle="${useHolidayLables}" validation="txt.isrequired" maxlength="40" onblur="generateInitCapName('hldyOccsnTxt')"></hdiits:text></td>
				<td class="fieldLabel"><b><hdiits:caption captionid="Holiday.OccType" bundle="${useHolidayLables}"/></b></td>
				<td class="fieldLabel">					
					<hdiits:select name="hldyTypetxt" id="hldyTypetxt" captionid="Holiday.OccType" bundle="${useHolidayLables}" mandatory="true" validation="sel.isrequired">
						<hdiits:option value="Select"><fmt:message key="Holiday.select"/></hdiits:option>
						<c:forEach var="hldyType" items="${hldyTypeEN}">
							<option value="<c:out value="${hldyType.lookupName}"/>">
							<c:out value="${hldyType.lookupDesc}" /></option>
						</c:forEach>
					</hdiits:select>
				</td>
			</tr>
			<tr><td class="fieldLabel"><b><hdiits:caption captionid="Holiday.OccDate" bundle="${useHolidayLables}"/></b></td>
				<td class="fieldLabel"><hdiits:dateTime name="hldyDt" captionid="Holiday.OccDate" bundle="${useHolidayLables}" mandatory="true" validation="txt.isdt,txt.isrequired"   maxvalue="31/12/2999"></hdiits:dateTime></td>
				<td class="fieldLabel"><b><hdiits:caption captionid="Holiday.OccText" bundle="${useHolidayLables}"/></b></td>
				<td class="fieldLabel"><hdiits:text name="hldyGreetMsg" id="hldyGreetMsg" maxlength="95"></hdiits:text></td>
			</tr>		
		</table>		
	</div>
	<c:if test="${langId == 1}">
		<div id="tcontent2" class="tabcontent" tabno="1">
	</c:if>
	<c:if test="${langId == 2}">
		<div id="tcontent1" class="tabcontent" tabno="0">
	</c:if>
		<table width="100%" id="newDsgn">
			<tr width="100%" align="center"><td><b><fmt:message bundle='${useHolidayLables}' key='Holiday.newOcc'/></b></td></tr>
		</table>
		<table width="100%" id="editPost" style="display:none">
			<tr width="100%" align="center"><td><b><fmt:message bundle='${useHolidayLables}' key='Holiday.updateOcc'/></b></td></tr>
		</table>	
		<table class="tabtable">
			
			<tr><td class="fieldLabel"><b><hdiits:caption captionid="Holiday.Occ" bundle="${useHolidayLables}"/></b></td>
				<td class="fieldLabel"><hdiits:text name="hldyOccsnTxt_gu" id="hldyOccsnTxt_gu" mandatory="true" captionid="Holiday.Occ" bundle="${useHolidayLables}" validation="txt.isrequired" maxlength="40"></hdiits:text></td>
				<td class="fieldLabel"><b><hdiits:caption captionid="Holiday.OccType" bundle="${useHolidayLables}"/></b></td>
				<td class="fieldLabel">
					<hdiits:select name="hldyTypetxt_gu" id="hldyTypetxt_gu" captionid="Holiday.OccType" bundle="${useHolidayLables}" mandatory="true" validation="sel.isrequired">
						<hdiits:option value="Select"><fmt:message key="Holiday.select"/></hdiits:option>
						<c:forEach var="hldyType" items="${hldyTypeGU}">
							<option value="<c:out value="${hldyType.lookupName}"/>">
							<c:out value="${hldyType.lookupDesc}" /></option>
						</c:forEach>
					</hdiits:select></td>
			</tr>
			<tr><td class="fieldLabel"><b><hdiits:caption captionid="Holiday.OccDate" bundle="${useHolidayLables}"/></b></td>
				<td class="fieldLabel"><hdiits:dateTime name="hldyDt_gu" captionid="Holiday.OccDate" bundle="${useHolidayLables}" mandatory="true" validation="txt.isdt,txt.isrequired"   maxvalue="31/12/2999"></hdiits:dateTime></td>
				<td class="fieldLabel"><b><hdiits:caption captionid="Holiday.OccText" bundle="${useHolidayLables}"/></b></td>
				<td class="fieldLabel"><hdiits:text name="hldyGreetMsg_gu" id="hldyGreetMsg_gu" maxlength="95"></hdiits:text></td>
			</tr>	
		</table>
			
	</div>
	<hdiits:hidden name="flag" id="flag" default="${flag}"/>
	<hdiits:hidden name="holidayId" id="holidayId" default=""/>	
		
	</div>
	<table align="center">	
		<tr>
			<td align="center">
				<c:if test="${flag ne'edit'}">
					<br><hdiits:button name="Submit" type="button" captionid="MstScr.submit" bundle="${useHolidayLables}" onclick="submit_frmAdminCrtHoliday()"></hdiits:button>
				</c:if>
				<c:if test="${flag eq 'edit'}">
					<br><hdiits:button name="Update" type="button" captionid="MstScr.update" bundle="${useHolidayLables}" onclick="submit_frmAdminCrtHoliday()"></hdiits:button>
				</c:if>
			</td>
			<td align="center">
				<br><hdiits:button name="reset" type="button" captionid="MstScr.reset" bundle="${useHolidayLables}" onclick="resetAllData()"></hdiits:button>
			</td>
			<td align="center">
				<br><hdiits:button name="Close" type="button" captionid="MstScr.close" bundle="${useHolidayLables}" onclick="closeCurrWindow()"></hdiits:button>
			</td>
		</tr>
	</table>
<hdiits:hidden name="langId" id="langId" default="${langId}"></hdiits:hidden>
<hdiits:hidden name="multiLangHdn" id="multiLangHdn" default="${multiLang}"></hdiits:hidden>	
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
 
<script type="text/javascript">
	initializetabcontent("maintab")	
</script>
</hdiits:form>
</body>
<script type="text/javascript">
	if('${multiLang}'=='false' || '${multiLang}'==false)
	{
		document.getElementById("guj").style.display='none';
		document.getElementById("eng").childNodes[0].childNodes[0].innerHTML = "Holiday Creation";
	}

	document.getElementById("flag").value='${flag}';	

	
	resetAllData();
	if('${langId}' == '1')
	{
		document.getElementById('hldyOccsnTxt').focus();
	}
	else if('${langId}' == '2')
	{
		document.getElementById('hldyOccsnTxt_gu').focus();
	}
</script>
</html>
<%
}catch(Exception ex){ex.printStackTrace();}
%>			