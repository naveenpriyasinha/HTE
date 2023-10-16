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
<script type="text/javascript"
	src="<c:url value="/script/hrms/eis/commonUtils.js"/>"></script>
<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>
<c:set var="actionList" value="${resValue.actionList}" > </c:set>
<c:set var="msg" value="${resValue.msg}" ></c:set>
<script>
function chkKey(e)
{
	
	if(e.keyCode=="13")
	{
		return false;
	}
	else
	{
		
		return true;
	}
}
function checkAvailability(newDeducName)
{
	var newDeducName=newDeducName.value;	
	if(newDeducName!="")
	{
		try {   
				xmlHttp=new XMLHttpRequest();
   		}
		catch(e){    // Internet Explorer    
			try {
	     		xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");   
    	 	}
	    	catch (e) {
		    	try {
	            	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
    	   		}
			    catch (e) {
			        alert("Your browser does not support AJAX!");        
			        return false;        
			    }
			}
		}
		var url = "hrms.htm?actionFlag=checkDeducAvailability&newDeducName="+newDeducName;  	

    xmlHttp.onreadystatechange = function() {		
		if (xmlHttp.readyState == 4) {     			
			if (xmlHttp.status == 200) {			
				var XMLDocForAjax=xmlHttp.responseXML.documentElement;
				var DeducNameMapping = XMLDocForAjax.getElementsByTagName('deducNameMapping');	
				var flag="true";				
				if(DeducNameMapping.length != 0) {		

						if(DeducNameMapping[0].childNodes[0].text==flag)
						{			
							alert("Deduction Name is already Exists, Please Enter other Name");
							document.frmBF.deductionName.value='';
							document.frmBF.deductionName.focus();
						}
				}
				
			}
		}
	}
	
	xmlHttp.open("POST", encodeURI(url) , false);    
	xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
	xmlHttp.send(encodeURIComponent(null));	
	return true;
	}
	
	
}
</script>

<fmt:setBundle basename="resources.payroll.payrollLables_en_US" var="enLables" scope="request"/>

<hdiits:form name="frmBF" validate="true" method="POST"
	action="./hrms.htm?actionFlag=insertDeducTypeMasterDtls&edit=N" encType="text/form-data">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs" compact="compact">
			<li class="selected"><a href="#" rel="tcontent1"><font size="2"><b><hdiits:caption captionid="DM.insertDeductionMaster" bundle="${enLables}"></hdiits:caption></a></li>
		</ul>
	</div>
	<div class="halftabcontentstyle">
	<div id="tcontent1" class="halftabcontent" tabno="0">
	<table width="50%" align="center"><br>
		<tr>
			<td><font size="2"><b><hdiits:caption captionid="DM.deductionType" bundle="${enLables}"></hdiits:caption> </b> </td>
			<td><hdiits:select name="deductionType"
			    captionid="DM.deductionType"
			    mandatory="true"
			    style="width:62%" 
			    validation="sel.isrequired">
			    <hdiits:option value="-1">-------------------Select------------------</hdiits:option>
			    <c:forEach items="${actionList}" var="deducTypes">
			    	<hdiits:option value="${deducTypes.lookupId}">${deducTypes.lookupDesc}</hdiits:option>
			    </c:forEach>
			    </hdiits:select></td>
		</tr>
		<tr>
			<td><font size="2"><b><hdiits:caption captionid="DM.deductionShortName" bundle="${enLables}"></hdiits:caption> </b> </td>
			<td><hdiits:text name="deductionName" default="" size="30" caption="Deduction Name" style="" validation="txt.isrequired,txt.isname" maxlength="20" onblur="checkAvailability(this)" mandatory="true" onkeypress="return chkKey(event)"/></td>
		</tr>
		<tr>
			<td><font size="2"><b><hdiits:caption captionid="DM.deductionFullName" bundle="${enLables}"></hdiits:caption> </b> </td>
			<td><hdiits:text name="deductionDisplayName" default="" size="30" caption="Deduction Full Name" style="" validation="txt.isrequired,txt.isname" maxlength="20" onblur="checkAvailability(this)" mandatory="true" onkeypress="return chkKey(event)"/></td>
		</tr>
		<tr>
			<td><font size="2"><b><font size="2"><b><hdiits:caption captionid="DM.deductionDesc" bundle="${enLables}"></hdiits:caption> </b> </font></td>
			<td><hdiits:textarea name="deductionDesc" default="" caption="deductionDesc" cols="32" rows="3" maxlength="95" onblur="checkSplChar(this)"  /></td>
		</tr>
	</table>
 	</div>
 	<fmt:setBundle basename="resources.eis.eisLables_en_US" var="Lables" scope="request"/>
	<jsp:include page="../../core/PayTabnavigation.jsp" /></div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		if("${msg}"!=null&&"${msg}"!='')
		{
			alert("${msg}");
			var url="./hrms.htm?actionFlag=getDeducData";
			document.frmBF.action=url;
			document.frmBF.submit();
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

	