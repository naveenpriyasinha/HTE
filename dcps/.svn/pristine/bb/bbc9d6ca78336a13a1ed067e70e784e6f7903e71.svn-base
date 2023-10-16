<%
try {
%>
<%@ include file="../../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>	
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<fmt:setBundle basename="resources.hr.retirement.Retirement" var="retireLabel" scope="request" />
<html>
<head>
<title>Human Resource Management System</title>
<script type="text/javascript" src="/script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="script/common/prototype-1.3.1.js"></script>
<script type="text/javascript" src="script/common/ajaxtags-1.1.5.js"></script>

<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/tagLibValidation.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/base64.js"/>"></script>
<script type="text/javascript">
	var myJoiningDate='';
	function validateMe()
	{
		var str = document.getElementById('retTypecmb').value;
		var userStr = document.getElementById('userId').value;
		var dateStr = document.frmAdminReq.retDate.value;
		if(str=='' || str=='Select'){document.getElementById('retTypecmb').focus();alert("<fmt:message  bundle="${retireLabel}" key="retire.enterRetType"/>");}
		else if(dateStr==''){alert("<fmt:message  bundle="${retireLabel}" key="retire.dateEnter"/>");}
		else if(userStr=='0'){alert("<fmt:message  bundle="${retireLabel}" key="retire.selectEmp"/>");}
		else if(confirm("Do you want to submit?")==true)
		{
			document.frmAdminReq.action="hrms.htm?actionFlag=retirementAdminSubmit";
			document.frmAdminReq.submit();
		}
	}
	function SearchEmp()
	{
		var href='hdiits.htm?actionFlag=getEmpSearchSelData&multiple=false';
		window.open(href,'chield', 'width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
	}
	var empArray = new Array();

	function empSearch(from)
	{
		for(var i=0; i<from.length; i++)
		{		
			empArray[i] = from[i].split("~"); 
		}
		var single = empArray[0];	
		document.getElementById('nameTxt').value=single[1];
		document.getElementById('nameDesig').value=single[11];
		document.getElementById('userId').value=single[2];
		var str = single[14];		
		
		if(str=='') {myJoiningDate=null;}
		else if (str.indexOf("-")!=-1)
		{
			str=str.substring(0,10);		
			var splitDate=str.split("-");				
			var byr=splitDate[0];
			var bmo=splitDate[1];
			var bday=splitDate[2];				
			myJoiningDate= bday+'/'+bmo+'/'+byr;							
		}
		else
		{
			myJoiningDate = str;
		}		
	}
	function checkDate()
	{		
		if(myJoiningDate!='' && myJoiningDate!=null)
		{
			var retData = datedifference();
			if(retData==false)
			{
				document.frmAdminReq.retDate.value='';
				alert("<fmt:message  bundle="${retireLabel}" key="retire.dateRetValidate"/>" + " : "+myJoiningDate);
			}	
		}
		else if (myJoiningDate!=null && myJoiningDate!='')
		{
			document.frmAdminReq.retDate.value='';
			alert("<fmt:message  bundle="${retireLabel}" key="retire.selectEmp"/>");
		}		
	}
	function datedifference() 
	{ 
		//alert("myJoiningDate :  "+myJoiningDate);
		var strDate1 = myJoiningDate;
		var strDate2 = document.frmAdminReq.retDate.value;			
		var bldatediff = false ;
		
		//Start date split to UK date format and add 31 days for maximum datediff 
		strDate1 = strDate1.split("/"); 
		starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]); 
		starttime = new Date(starttime.valueOf()); 
		
		//End date split to UK date format 
		strDate2 = strDate2.split("/"); 
		endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]); 
		endtime = new Date(endtime.valueOf()); 
		//alert ('end : '+endtime +'  start   : ' + starttime );
		if(endtime >= starttime) 
		{ 
			bldatediff = true 
		} 
		
		return bldatediff 
	}
</script>
</head>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="retCmbLst" value="${resValue.retCmbLst}"> </c:set>	
<body>
<hdiits:form name="frmAdminReq" action="hdiits.htm" validate="true" method="post" encType="multipart/form-data">
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>
		<fmt:message key="retire.AdminPage" bundle="${retireLabel}"/>
		</b></a></li>
	</ul>
	</div>
	<div id="frmAdminReqDiv" name="frmAdminReqDiv">
	<div id="tcontent1" class="tabcontent" tabno="0">	
	<table class="tabtable">
		<tr>
			<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="retire.name" bundle="${retireLabel}"></hdiits:caption></b></td>
			<td class="fieldLabel" width="25%"><hdiits:text name="nameTxt" readonly="true"></hdiits:text></td>
			<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="retire.desi" bundle="${retireLabel}"></hdiits:caption></b></td>
			<td class="fieldLabel" width="25%"><hdiits:text name="nameDesig" readonly="true"></hdiits:text></td>
		</tr>
		<tr>
			<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="retire.retType" bundle="${retireLabel}"></hdiits:caption></b></td>
			<td class="fieldLabel" width="25%">
				<hdiits:select name="retTypecmb" id="retTypecmb" mandatory="true">
					<hdiits:option value="Select">--<hdiits:caption captionid="retire.Select" bundle="${retireLabel}"></hdiits:caption>--</hdiits:option>
					<c:forEach var="cmbType" items="${retCmbLst}">
					<c:if test="${cmbType.lookupName != 'retire_vr'}">
		    					<option value="<c:out value="${cmbType.lookupName}"/>">
								<c:out value="${cmbType.lookupDesc}"/></option>						
					</c:if>
					</c:forEach>
				</hdiits:select>
			</td>
			<td class="fieldLabel" width="25%"><b><hdiits:caption  captionid="retire.retDate" bundle="${retireLabel}"></hdiits:caption></b></td>
			<td class="fieldLabel" width="25%">
				<hdiits:dateTime name="retDate" afterDateSelect="checkDate()" mandatory="true" captionid="retire.retDate" bundle="${retireLabel}" maxvalue="31/12/2099"></hdiits:dateTime>
			</td>
		</tr>
		<tr>
		</tr>
	</table>
	<BR>
	<BR>	
	<center>
		<hdiits:button name="Search" type="button" onclick="SearchEmp();" captionid="Button.empSearch" bundle="${retireLabel}"/>&nbsp;&nbsp;
		<hdiits:button name="btnSubmit" captionid="Button.Submit" bundle="${retireLabel}" type="button" onclick="validateMe();"/>
	</center>
	
	<hdiits:hidden name="userId" id="userId" default="0"/>
	</div>
	</div>
	<hdiits:validate locale="${locale}" controlNames="" />
	<script type="text/javascript">	
		document.frmAdminReq.retDate.readOnly=true;
		initializetabcontent("maintab")
	</script>
</hdiits:form>
</body>
</html>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>