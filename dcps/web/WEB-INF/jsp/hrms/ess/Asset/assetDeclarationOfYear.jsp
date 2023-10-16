<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%
try
{%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix= "ajax" %> 
<fmt:setBundle basename="resources.ess.asset.AssetAlertsMsg" var="as1" scope="request"/>
<fmt:setBundle basename="resources.ess.asset.AssetLables" var="al" scope="request"/> 
<script type="text/javascript" src="script/common/addRecord.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/hrms/ess/asset/assetPermission.js"></script>
<script type="text/javascript" src="/script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/base64.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript"  src="script/hrms/ess/asset/assetValidation.js"></script>
<script type="text/javascript"  src="script/hrms/ess/asset/assetAdminScreen.js"></script>
<script type="text/javascript"  src="script/hrms/ess/asset/assetJoining.js"></script>
<script type="text/javascript"  src="script/hrms/ess/asset/assetDeclarationOfYear.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="systemYear" value="${resultValue.systemYear}"></c:set>
<c:set var="totalAssetData" value="${resultValue.totalAssetData}"></c:set>
<c:set var="NoYearData" value="${resultValue.NoYearData}"></c:set>
<c:set var="officerType" value="${resultValue.officerType}"></c:set>
<c:set var="empDob" value="${resultValue.empDob}"></c:set>
<c:set var="fromToYear" value="${resultValue.fromToYear}"></c:set>
<c:set var="firstPreviousFromToYear" value="${resultValue.firstPreviousFromToYear}"></c:set>
<c:set var="secondPreviousFromToYear" value="${resultValue.secondPreviousFromToYear}"></c:set>
<c:set var="thirdPreviousFromToYear" value="${resultValue.thirdPreviousFromToYear}"></c:set>
<c:set var="flag" value="${resultValue.flag}"></c:set>
<script language="javascript">


function SubmitAssetDeclOfYearReq()
{
	startProcess();
	window.setTimeout('submitForm_Submit("form1")',1000);
}

function submitForm_Submit(formNameValue)
{
	
	var validData = validateForm_form1();
	if( validData==true )
	{
		//window.document.forms[formNameValue].submit();
		submitAssetDeclYearReq();
	}
endProcess();
}

function submitAssetDeclYearReq()
{
	var fromToYear = "${fromToYear}";
	var splitToYear = fromToYear.split("-");
	var toYear = splitToYear[1];
	var sysYear = "${systemYear+1}";
	var officerType = "${officerType}";
	if(officerType == "Non-Gazetted")
	{
		if(toYear >=sysYear)
		{
			alert('<fmt:message  bundle="${as1}" key="ASSET.DO_NOT_DECLARE"/>');
			return;
		}
	}	
	var agree=confirm('<fmt:message  bundle="${as1}" key="ASSET.DO_WANT_SUBMIT"/>');
     	if (agree)
     	{
			var urlstyle="hdiits.htm?actionFlag=submitAssetDeclarationOfYearReq";
			document.form1.action=urlstyle;
			document.form1.submit();
		}	
}

function getDataAccordingToYear(l,flag)
{
	var id = l.value;
	var officerType = "${officerType}";
	var year;
	if(id == '')
				{
					return;
				}
	if(id == "V")
	{
		if(flag == 1 && officerType == "Gazetted")
		{
			year = "${systemYear}" -1;
			document.form1.firstPrvYear.value = "^";
		}
		else if(flag == 1 && officerType == "Non-Gazetted")
		{
			year = "${firstPreviousFromToYear}";
			document.form1.firstPrvYear.value = "^";
		}
		else if(flag == 2 && officerType == "Gazetted")
		{
			year = "${systemYear}" -2;
			document.form1.secondPrvYear.value = "^";
		}
		else if(flag == 2 && officerType == "Non-Gazetted")
		{
			year = "${secondPreviousFromToYear}";
			document.form1.secondPrvYear.value = "^";
		}
		else if(flag == 3 && officerType == "Gazetted")
		{
			year = "${systemYear}" -3;
			document.form1.thirdPrvYear.value = "^";
		}
		else if(flag == 3 && officerType == "Non-Gazetted")
		{
			year = "${thirdPreviousFromToYear}";
			document.form1.thirdPrvYear.value = "^";
		}
		//alert(year);
		try{   
    			xmlHttp=new XMLHttpRequest();	    	    
	    	}
			catch (e)
			{    // Internet Explorer    
					try{
      					xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");	      					   
      				}
		    		catch (e){
		          		try
        		  		{
                	           	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");   	                	             
        		  		}
				      	catch (e)
				      	{
       	   	  	           	  
			            	  return false;        
			      		}
			 	}
        	}
        	var url = "hdiits.htm?actionFlag=getAllMovableAssetDeclarationDtlsOfYear&year="+year+"&flag="+flag+"&officerType="+officerType;    
			xmlHttp.open("POST", encodeURI(url) , true);			
			xmlHttp.onreadystatechange = showAllRecordsOfMovableAssetDeclaration;
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));

	}
	if(id == "^")
	{ 	
		if(flag == 1)
		{
			document.form1.firstPrvYear.value = "V";
			document.getElementById('firstPrvYearDataTable').style.display="none";
			document.getElementById('noRecordForFirstPrvYear').style.display="none";
			
		}
		else if(flag == 2)
		{
			document.form1.secondPrvYear.value = "V";
			document.getElementById('secondPrvYearDataTable').style.display="none";
			document.getElementById('noRecordForSecondPrvYear').style.display="none";
		}
		else if(flag == 3)
		{
			document.form1.thirdPrvYear.value = "V";
			document.getElementById('thirdPrvYearDataTable').style.display="none";
			document.getElementById('noRecordForThirdPrvYear').style.display="none";
		}
	
	}
}
function displayStatus(status)
{
	if(status == 'N'){
		status= '<fmt:message key="PENDING" bundle="${al}"/>';
	}
	else if(status == 'A'){
		status= '<fmt:message key="APPROVED" bundle="${al}"/>';
	}
	else if(status == 'R'){
		status= '<fmt:message key="REJECTED" bundle="${al}"/>';
	}
	return status;
}

</script>

<hdiits:form name="form1" validate="true" method="post" encType="multipart/form-data"  action="hdiits.htm"> 

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
	<c:if test="${officerType == 'Gazetted'}">
	<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="ASSET_DECL_YEAR" bundle="${al}"/>${systemYear}</b></a></li>
	</c:if>
	<c:if test="${officerType == 'Non-Gazetted'}">
	<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="ASSET_DECL_YEAR" bundle="${al}"/>${fromToYear}</b></a></li>
	</c:if>
	<li><a href="#" rel="tcontent2"><b><fmt:message key="PREV_YEAR_ASSET_DECL" bundle="${al}"/></b></a></li>
	</ul>
</div>
<div id="tcontent1" class="tabcontent" tabno="0">

<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>

<hdiits:fieldGroup bundle="${al}" expandable="true" id="assetDtlsFldGrp" titleCaptionId="ASSET_DTLS">
<!--<TABLE class=tabtable>
	<TBODY>
	<TR bgColor=#386cb7>
	<TD class=fieldLabel align=middle colSpan=5 ><FONT color=#ffffff><STRONG><U><fmt:message key="ASSET_DTLS" bundle="${al}"/></U></STRONG> 
	</FONT></TD></TR>
	</TBODY>
</TABLE>

--><c:if test="${officerType == 'Gazetted'}">
<c:if test="${flag == 'alreadyNotDeclared'}">
<table class=tabtable >
<tr>
<td class="fieldLabel" width="25%"><hdiits:caption captionid="ASSET_DECL_FOR_YEAR" bundle="${al}"/></td>
<td><hdiits:select name="chooseYear" size="1" sort="false"  tabindex="4" mandatory="true"  captionid="ASSET_DECL_FOR_YEAR" bundle="${al}" validation="sel.isrequired"  onchange="getAllAssetDataOfSelectedYear(this,1)">
	<hdiits:option value="0"><fmt:message key="DROPDOWN.SELECT" bundle="${al}" /></hdiits:option>
	<hdiits:option value="${systemYear}">${systemYear}</hdiits:option>
	</hdiits:select></td>
</tr>
</table>
</c:if>
<c:if test="${flag == 'alreadyDeclared'}">
<br>
<table align="center">
	<TBODY>
	 <TR bgColor=#386cb7>
    <TD class=fieldLabel align="center" colSpan=5 ><FONT color=#ffffff><STRONG><U></U></STRONG></FONT>
    </TD></TR>
    <tr><TD class=fieldLabel  align="center"><b><fmt:message key="ASSET_DECL_OF_YEAR_REQ" bundle="${al}"/> ${systemYear} <fmt:message key="SUBMITTED_ALLREADY" bundle="${al}"/></b></TD></tr>
    <TR bgColor=#386cb7>
    <TD class=fieldLabel align="center" colSpan=5 ><FONT color=#ffffff><STRONG><U></U></STRONG></FONT>
    </TD></TR>
   </TBODY>
</table> 	
</c:if>
</c:if>
<c:if test="${officerType == 'Non-Gazetted'}">
<table class=tabtable >
<tr>
<td class="fieldLabel" width="25%"><hdiits:caption captionid="ASSET_DECL_FOR_YEAR" bundle="${al}"/></td>
<td><hdiits:select name="chooseFromToYear" size="1" sort="false"  tabindex="4" mandatory="true"  captionid="ASSET_DECL_FOR_YEAR" bundle="${al}" validation="sel.isrequired"  onchange="getAllAssetDataOfSelectedYear(this,2)">
	<hdiits:option value="0"><fmt:message key="DROPDOWN.SELECT" bundle="${al}" /></hdiits:option>
	<hdiits:option value="${fromToYear}">${fromToYear}</hdiits:option>
	</hdiits:select></td>
</tr>
</table>
</c:if>
<br>
<TABLE class=tabtable style="DISPLAY: none" id="movableAssetDataLable">
  	<tr>
 	<TD class=fieldLabel width="25%"><hdiits:caption captionid="MOVABLE_ASSETS" bundle="${al}"/></td>
	</tr>
</TABLE>


<table id="movableAssetDataTable" style="DISPLAY: none" align="center" style="border-collapse: collapse;" borderColor="BLACK"  border=1  width="98%"> 
<br>
<tr bgcolor="#C9DFFF">
<td class="fieldLabel"  align="center" width="5%"><b><hdiits:caption captionid="SRNO" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="ASSETID" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="NAME_OF_PRPTY" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="REGI_NO" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="DATE_OF_TRANS" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="SELL_DATE" bundle="${al}" /></b></td>

</tr>	
</table>

<TABLE class=tabtable style="DISPLAY: none" id="recordForYear" style="border-collapse: collapse;" borderColor="BLACK"  border=1  width="98%">
<br>
<tr bgcolor="#C9DFFF">
<td class="fieldLabel"  align="center" width="5%"><b><hdiits:caption captionid="SRNO" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="ASSETID" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="NAME_OF_PRPTY" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="REGI_NO" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="DATE_OF_TRANS" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="SELL_DATE" bundle="${al}" /></b></td>
</tr>	

<tr><td align="center" colspan="6"><b><fmt:message key="NO_RECORDS" bundle="${al}"/></b></td></tr>
</TABLE>


<br>

<TABLE class=tabtable style="" id="FixedAssetDataLable">

  	<tr>
 	<TD class=fieldLabel width="25%"><hdiits:caption captionid="FIXED_ASSET" bundle="${al}"/></td>
	</tr>
</TABLE>
<c:if test="${not empty totalAssetData}">		
<table id="fixedAssetDataTable" style="" align="center" style="border-collapse: collapse;" borderColor="BLACK"  border=1 width="98%"> 
<br>
<tr bgcolor="#C9DFFF">
<td class="fieldLabel"  align="center" width="5%"><b><hdiits:caption captionid="SRNO" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="ASSETID" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="MOV_OR_NOT" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="NAME_OF_PRPTY" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="ADD_OF_ASSET" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="REGI_NO" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="DATE_OF_TRANS" bundle="${al}" /></b></td>
</tr>	

<c:forEach items="${totalAssetData}" var="totalAssetData" varStatus="x">
<tr>
<td><c:out value="${totalAssetData.rowId}"/></td>
<td><c:out value="${totalAssetData.assetId}"/></td>
<td><c:out value="${totalAssetData.assetType}"/></td>
<td><c:out value="${totalAssetData.assetName}"/></td>
<td width="20%"><c:out value="${totalAssetData.assetAddress}"/></td>
<td><c:out value="${totalAssetData.registrationNo}"/></td>
<td><c:out value="${totalAssetData.transactionDate}"/></td>
</tr>

</c:forEach>
</table>
</c:if>

<c:if test="${NoYearData == 'NoYearData'}">	
<TABLE class=tabtable style="" align="center" style="border-collapse: collapse;" borderColor="BLACK"  border=1 width="98%">
<br>
<tr bgcolor="#C9DFFF">
<td class="fieldLabel"  align="center" width="5%"><b><hdiits:caption captionid="SRNO" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="ASSETID" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="MOV_OR_NOT" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="NAME_OF_PRPTY" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="ADD_OF_ASSET" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="REGI_NO" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="DATE_OF_TRANS" bundle="${al}" /></b></td>
</tr>	
<tr><td align="center" colspan="7"><b><fmt:message key="NO_RECORDS" bundle="${al}"/></b></td></tr>
</table>
</c:if>
</hdiits:fieldGroup>
<TABLE class=tabtable style="" id="submitRequest">
<br>
<tr>
	<td align="center">
	<hdiits:button  type="button" name="Submit" captionid="ASSET_SUBMIT" bundle="${al}" onclick="SubmitAssetDeclOfYearReq()"  />
	<hdiits:button type="button" name="Close"  captionid="ASSET_CLOSE" bundle="${al}" onclick="closewindow()"/>
	</td></tr> 
</table>
<c:if test="${officerType == 'Gazetted'}">
<c:if test="${flag == 'alreadyDeclared'}">
<script>
document.form1.Submit.disabled=true;
</script>
</c:if>
</c:if>
</div>

<div id="tcontent2" class="tabcontent" tabno="1">
<hdiits:fieldGroup bundle="${al}" expandable="true" id="prvYrFldGrp" titleCaptionId="PREV_YEAR_ASSET_DECL">
<!--<TABLE class=tabtable>
	<TBODY>
	<TR bgColor=#386cb7>
	<TD class=fieldLabel align=middle colSpan=5 ><FONT color=#ffffff><STRONG><U><fmt:message key="PREV_YEAR_ASSET_DECL" bundle="${al}"/></U></STRONG> 
	</FONT></TD></TR>
	</TBODY>
</TABLE>

--><table class=tabtable >
<tr>
<td align="left" width="20%"><hdiits:button  type="button" name="thirdPrvYear" value="V" onclick="getDataAccordingToYear(this,3)" />
<c:if test="${officerType == 'Gazetted'}">
<b><fmt:message key="ASSET_YEAR_DECL" bundle="${al}"/> ${systemYear -3}</b>
</c:if>
<c:if test="${officerType == 'Non-Gazetted'}">
<b><fmt:message key="ASSET_YEAR_DECL" bundle="${al}"/> ${thirdPreviousFromToYear}</b>
</c:if>
</td>
</tr> 
</table>
<br>
<table id="thirdPrvYearDataTable" style="DISPLAY: none" align="center" style="border-collapse: collapse;" borderColor="BLACK"  border=1 width="98%"> 
<tr bgcolor="#C9DFFF">
<td class="fieldLabel"  align="center" width="5%"><b><hdiits:caption captionid="SRNO" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="ASSETID" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="NAME_OF_PRPTY" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="REGI_NO" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="DATE_OF_TRANS" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="SELL_DATE" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="STATUS" bundle="${al}" /></b></td>
</tr>	
</table>

<table id="noRecordForThirdPrvYear" style="DISPLAY: none" align="center" style="border-collapse: collapse;" borderColor="BLACK"  border=1 width="98%">
<tr bgcolor="#C9DFFF">
<td class="fieldLabel"  align="center" width="5%"><b><hdiits:caption captionid="SRNO" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="ASSETID" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="NAME_OF_PRPTY" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="REGI_NO" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="DATE_OF_TRANS" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="SELL_DATE" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="STATUS" bundle="${al}" /></b></td>
</tr>	
<tr><td align="center" colspan="7"><b><fmt:message key="NO_RECORDS" bundle="${al}"/></b></td></tr>
</table>

<table class=tabtable >
<tr>
<td align="left" width="20%"><hdiits:button  type="button" name="secondPrvYear" value="V" onclick="getDataAccordingToYear(this,2)" />
<c:if test="${officerType == 'Gazetted'}">
<b><fmt:message key="ASSET_YEAR_DECL" bundle="${al}"/> ${systemYear -2}</b>
</c:if>
<c:if test="${officerType == 'Non-Gazetted'}">
<b><fmt:message key="ASSET_YEAR_DECL" bundle="${al}"/> ${secondPreviousFromToYear}</b>
</c:if>
</td>
</tr> 
</table>
<br>
<table id="secondPrvYearDataTable" style="DISPLAY: none" align="center" style="border-collapse: collapse;" borderColor="BLACK"  border=1 width="98%"> 
<tr bgcolor="#C9DFFF">
<td class="fieldLabel"  align="center" width="5%"><b><hdiits:caption captionid="SRNO" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="ASSETID" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="NAME_OF_PRPTY" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="REGI_NO" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="DATE_OF_TRANS" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="SELL_DATE" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="STATUS" bundle="${al}" /></b></td>
</tr>	
</table>

<table id="noRecordForSecondPrvYear" style="DISPLAY: none" align="center" style="border-collapse: collapse;" borderColor="BLACK"  border=1 width="98%">
<tr bgcolor="#C9DFFF">
<td class="fieldLabel"  align="center" width="5%"><b><hdiits:caption captionid="SRNO" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="ASSETID" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="NAME_OF_PRPTY" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="REGI_NO" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="DATE_OF_TRANS" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="SELL_DATE" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="STATUS" bundle="${al}" /></b></td>
</tr>	
<tr><td align="center" colspan="7"><b><fmt:message key="NO_RECORDS" bundle="${al}"/></b></td></tr>
</table>

<table class=tabtable >
<tr>
<td align="left" width="20%"><hdiits:button  type="button" name="firstPrvYear" value="V" onclick="getDataAccordingToYear(this,1)" />
<c:if test="${officerType == 'Gazetted'}">
<b><fmt:message key="ASSET_YEAR_DECL" bundle="${al}"/> ${systemYear -1}</b>
</c:if>
<c:if test="${officerType == 'Non-Gazetted'}">
<b><fmt:message key="ASSET_YEAR_DECL" bundle="${al}"/> ${firstPreviousFromToYear}</b>
</c:if>
</td>
</tr> 
</table>
<br>
<table id="firstPrvYearDataTable" style="DISPLAY: none" align="center" style="border-collapse: collapse;" borderColor="BLACK"  border=1 width="98%"> 
<tr bgcolor="#C9DFFF">
<td class="fieldLabel"  align="center" width="5%"><b><hdiits:caption captionid="SRNO" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="ASSETID" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="NAME_OF_PRPTY" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="REGI_NO" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="DATE_OF_TRANS" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="SELL_DATE" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="STATUS" bundle="${al}" /></b></td>
</tr>	
</table>
<table id="noRecordForFirstPrvYear" style="DISPLAY: none" align="center" style="border-collapse: collapse;" borderColor="BLACK"  border=1 width="98%">
<tr bgcolor="#C9DFFF">
<td class="fieldLabel"  align="center" width="5%"><b><hdiits:caption captionid="SRNO" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="ASSETID" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="NAME_OF_PRPTY" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="REGI_NO" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="DATE_OF_TRANS" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="SELL_DATE" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="STATUS" bundle="${al}" /></b></td>
</tr>	
<tr><td align="center" colspan="7"><b><fmt:message key="NO_RECORDS" bundle="${al}"/></b></td></tr>
</table>
</hdiits:fieldGroup>
</div>
<hdiits:hidden name="year" id="year"/>
<hdiits:hidden name="assetId" id="assetId"/>
<hdiits:hidden name="flag" id="flag"/>

		<script type="text/javascript">
		initializetabcontent("maintab")
		</script>
<hdiits:validate controlNames="test" locale='<%=(String)session.getAttribute("locale")%>' />	

</hdiits:form>


<%}catch(Exception e)
  {
  e.printStackTrace();
  }
%>