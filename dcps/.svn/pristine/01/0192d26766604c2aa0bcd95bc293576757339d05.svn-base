<%

try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page session="true" %>
<script type="text/javascript"  
    src="common/script/commonfunctions.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript"   
	src="common/script/tagLibValidation.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>

<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>

<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>
<c:set var="lstSection" value="${resValue.lstSection}"> </c:set>
<c:set var="lstStatus" value="${resValue.lstStatus}"> </c:set>

<c:set var="msg" value="${resValue.msg}" ></c:set>

<script><!--
function chkKey(e){	
	if(e.keyCode=="13") {
		return false;
	}
	else {		
		return true;
	}
}

function addInvestmentTypeToTable(){
		
		var investmentTypeDataForVOGEN = new Array('investName','sectId','investDesc','statusFlag','startDate','endDate');
		addOrUpdateRecord('addRecord', 'addMultipleInvestmentType', investmentTypeDataForVOGEN);
}


function addRecord(){

	if(document.frmInvestmentType.sectId.value == -1){
	    alert("Select the Section Code");
    	return false;
    }
    if(document.frmInvestmentType.investName.value == ''){
    	alert("Enter the Investment Name");
    	return false;
    }
    if(document.frmInvestmentType.statusFlag.value == -1){
	    alert("Select the Status Flag");
    	return false;
    }    
    
    if(document.frmInvestmentType.startDate.value == ''){
    	alert("Enter Start Date");
    	return false;
    }   
    if(document.frmInvestmentType.endDate.value!='') {
	    var diff = compareDate(document.frmInvestmentType.startDate.value,document.frmInvestmentType.endDate.value);
    	if(diff<0){
    		alert("End Date must be Greater then Start Date");
	    	document.frmInvestmentType.endDate.value='';    	
    		return false;
	    }
    }


 	if (xmlHttp.readyState == 4)
	{
		var investmentDataForTableDisplay = new Array('investName','sectId','investDesc','statusFlag','startDate','endDate');

		var rowId = addDataInTable('tblInvestType', 'encXML', investmentDataForTableDisplay, '', 'deleteRecord');

		/* reset():
				- to clear the fields after records have been inserted into dynamic table
				- to avoid user from adding same record, by mistake.
		*/
		//resetFields();

	 return true;

	}

}

function resetFields(){
	document.frmInvestmentType.sectId.value=-1;
	document.frmInvestmentType.investName.value='';
	document.frmInvestmentType.investDesc.value='';
	document.frmInvestmentType.statusFlag.value=-1;
	document.frmInvestmentType.startDate.value='';
	document.frmInvestmentType.endDate.value='';
}

--></script>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="enLables" scope="page"/>


<hdiits:form name="frmInvestmentType" validate="true" method="POST"
	action="./hrms.htm?actionFlag=insertUpdateInvestType&edit=N" encType="text/form-data">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs" compact="compact">
			<li class="selected"><a href="#" rel="tcontent1"><font size="2"><b><hdiits:caption captionid="investTypeMasterInsert" bundle="${enLables}"></hdiits:caption></a></li>
		</ul>
	</div>
	<div class="exhalftabcontentstyle">
	<div id="tcontent1" class="halftabcontent" tabno="0">
	<table><br>
		<tr><td width="10%">&nbsp;</td>
			<td width="15%"><font size="2"><b><hdiits:caption captionid="sectionCode" bundle="${enLables}"></hdiits:caption> </b> </td>
			<td width="20%"><hdiits:select name="sectId"
			    
			    caption="Section Code"
				mandatory="true"
			    validation="sel.isrequired">
			    <hdiits:option value="-1">---------------Select----------------</hdiits:option>
			    <c:forEach items="${lstSection}" var="list">
			    	<hdiits:option value="${list.sectId}">${list.sectCode}</hdiits:option>
			    </c:forEach>
			    </hdiits:select></td>
			<td width="10%">&nbsp;</td>
			<td width="15%"><font size="2"><b><hdiits:caption captionid="investName" bundle="${enLables}"></hdiits:caption> </b> </td>
			<td width="20%"><hdiits:text name="investName" default="" size="25" caption="investName" captionid="investName" maxlength="100" mandatory="true"/></td>
			<td width="10%"></td>
		</tr>
		
		<tr>
		<tr><td width="10%"></td>
			<td width="15%"> <font size="2"><b><hdiits:caption captionid="investDesc" bundle="${enLables}"></hdiits:caption> </b> </font></td>
			<td width="20%"> <hdiits:textarea name="investDesc" caption="investDesc" rows="3" cols="28" > </hdiits:textarea> </td>
			<td width="10%"></td>
			<td width="15%"> <font size="2"><b><hdiits:caption captionid="STATUS" bundle="${enLables}"></hdiits:caption> </b> </font></td>
			<td width="20%"> <hdiits:select name="statusFlag"
			    captionid="STATUS"
			    caption="Status Flag"
				mandatory="true"
			    validation="sel.isrequired">
			    <hdiits:option value="-1">---------------Select----------------</hdiits:option>
			    <c:forEach items="${lstStatus}" var="list">
			    	<hdiits:option value="${list.lookupId}">${list.lookupDesc}</hdiits:option>
			    </c:forEach>
			    </hdiits:select></td>
			    <td width="10%"></td>
		</tr>
		
		<tr>
		<tr><td width="10%"></td>
			<td width="15%"> <font size="2"><b><hdiits:caption captionid="NGD.startDate" bundle="${enLables}"></hdiits:caption> </b> </font></td>
			<td width="20%"> <hdiits:dateTime name="startDate" caption="NGD.startDate" mandatory="true" bundle="${enLables}" /> </td>
			<td width="10%"></td>
			<td width="15%"> <font size="2"><b><hdiits:caption captionid="NGD.endDate" bundle="${enLables}" ></hdiits:caption> </b> </font></td>
			<td width="20%"> <hdiits:dateTime name="endDate" captionid="NGD.endDate" bundle="${enLables}" /> </td>
			
			<td width="10%"></td>
		</tr>
	

		</table>
 	</div>  
 

	<table id="btnAdd" style="" align="center">
		<tr>
			<td class="fieldLabel" align="center" colspan="4">
				<hdiits:button  name="add"  type="button"  caption="Add" onclick="addInvestmentTypeToTable()"></hdiits:button>
			</td>
		</tr>
	</table>

	
	<br><font size="2"><b>
	<table id="tblInvestType" border="1" bgcolor="ffffff" bordercolor="aaaaaa" align="center">
 			<tr>				
 				<th width="15%" align="center">Investment Name</th>
 				<th width="15%" align="center">Section Name</th>
 				<th width="15%" align="center">Investment Description</th>
 				<th width="15%" align="center">Status Flag</th>
 				<th width="10%" align="center">Start Date</th>
 				<th width="10%" align="center">End Date</th>
 				<th width="10%" align="center"></th> 				
 			</tr>
 		</table>        
	
 	</font>
 	<br>
 <fmt:setBundle basename="resources.eis.eis_common_lables" var="Lables" scope="page"/>	
 	<jsp:include page="../../core/PayTabnavigation.jsp" />
 	</div>
 		
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		if("${msg}"!=null&&"${msg}"!='')
		{
			alert("${msg}");
			var url="./hrms.htm?actionFlag=getInvestmentTypes";
			document.frmInvestmentType.action=url;
			document.frmInvestmentType.submit();
		}
		</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

	