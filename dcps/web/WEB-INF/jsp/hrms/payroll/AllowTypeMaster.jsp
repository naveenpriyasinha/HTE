<%

try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page session="true" %>
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
<c:set var="msg" value="${resValue.msg}" ></c:set>
<%String editMode=request.getParameter("edit");
 session.putValue("edit",editMode);
 %>
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
function checkAvailability(newAllowName)
{
	var newAllowName=newAllowName.value;	
	if(newAllowName!="")
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
		var url = "hrms.htm?actionFlag=checkAllowNameAvailability&newAllowName="+newAllowName;  	

    xmlHttp.onreadystatechange = function() {		
		if (xmlHttp.readyState == 4) {     			
			if (xmlHttp.status == 200) {			
				var XMLDocForAjax=xmlHttp.responseXML.documentElement;
				var allowNameMapping = XMLDocForAjax.getElementsByTagName('allowNameMapping');	
				var flag="true";				
				if(allowNameMapping.length != 0) {		

						if(allowNameMapping[0].childNodes[0].text==flag)
						{			
							alert("Allowance Name is already Exists, Please Enter other Name");
							document.frmBF.allowanceName.value='';
							document.frmBF.allowanceName.focus();
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
	action="./hrms.htm?actionFlag=InsertAllowanceTypeMasterData&edit=N" encType="text/form-data">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs" compact="compact">
			<li class="selected"><a href="#" rel="tcontent1"><font size="2"><b><hdiits:caption captionid="AM.insertAllowanceMaster" bundle="${enLables}"></hdiits:caption></a></li>
			<!-- <li class="selected"><a href="#" rel="tcontent2">INSERT FORM2</a></li> -->
		</ul>
	</div>
	<div class="halftabcontentstyle">
	<div id="tcontent1" class="halftabcontent" tabno="0">
	<table width="45%" align="center"><br>
		<tr>
			<td><font size="2"><b><hdiits:caption captionid="AM.allowanceShortName" bundle="${enLables}"></hdiits:caption> </b> </td>
			<td><hdiits:text name="allowanceName" default="" mandatory="true" size="30" caption="Allowance Name" style="" validation="txt.isrequired,txt.isname" maxlength="100" onblur="checkAvailability(this)" onkeypress="return chkKey(event)"/></td>
		</tr>
		<tr>
			<td><font size="2"><b><hdiits:caption captionid="AM.allowanceFullName" bundle="${enLables}"></hdiits:caption> </b> </td>
			<td><hdiits:text name="allowanceDisplayName" default="" mandatory="true" size="30" caption="Allowance Full Name" style="" validation="txt.isrequired,,txt.isname" maxlength="100" onblur="checkAvailability(this)" onkeypress="return chkKey(event)"/></td>
		</tr>
		<tr>
			<td><font size="2"><b><font size="2"><b><hdiits:caption captionid="AM.allowacenDesc" bundle="${enLables}"></hdiits:caption> </b> </font></td>
			<td><hdiits:textarea name="allowanceDesc" default="" caption="allowanceDesc" cols="32" rows="3" maxlength="190"   onblur="checkSplChar(this)" /></td>
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
			var url="./hrms.htm?actionFlag=getAllowTypeView";
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

	