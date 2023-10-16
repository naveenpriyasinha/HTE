<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>

<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
<fmt:setBundle basename="resources.payroll.payrollLables_en_US" var="enLables" scope="request"/>

<script type="text/javascript"
	src="<c:url value="/script/hrms/eis/commonUtils.js"/>"></script>
<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="actionList" value="${resValue.actionList}" > </c:set>
<c:set var="msg" value="${resValue.msg}" ></c:set>
<script>
function checkAvailability(newAllowName1)
{
	var newAllowName=newAllowName1.value;
	var oldAllowName="${actionList.allowName}";	
	
		
	
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
	//    var url = "hrms.htm?actionFlag=getDesigData&editdesig=${desg_name}&&chk=1&dname="+dname;  
   			if(newAllowName==oldAllowName) {	
				return true;
			}
			else{
				//alert("Not eqal");
				var url = "hrms.htm?actionFlag=checkAllowNameAvailability&newAllowName="+newAllowName;  	
			}
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
	
	
	xmlHttp.open("POST", encodeURI(url) , false);    
	xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
	xmlHttp.send(encodeURIComponent(null));	
	return true;
	}
	}
	
	

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
</script>
<%String editMode=request.getParameter("edit");
 session.putValue("edit",editMode);
 %>
<hdiits:form name="frmBF" validate="true" method="POST" 
	action="./hrms.htm?actionFlag=InsertAllowanceTypeMasterData&allowCode=${actionList.allowCode}&edit=Y" encType="text/form-data">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs" compact="compact">
			<li class="selected"><a href="#" rel="tcontent1"><b><font size="2"><b><fmt:message key="AM.updateAllowanceMaster" bundle="${enLables}"/></b></a></li>
			<!-- <li class="selected"><a href="#" rel="tcontent2">INSERT FORM2</a></li> -->
		</ul>
	</div>
	<div class="halftabcontentstyle">
	<div id="tcontent1" class="halftabcontent" tabno="0" >
	<table align="center" width="45%" >
	<br>
		<tr>
			<td><font size="2"><b><fmt:message key="AM.allowanceShortName" bundle="${enLables}"/></b> </font></td>
			<td><hdiits:text name="allowanceName" validation="txt.isrequired" caption="Allowance Name" default="${actionList.allowName}" mandatory="true"  maxlength="50" size="30" onblur="checkSplCharExceptArg(this,'(.)');checkAvailability(this)" onkeypress="return chkKey(event)"/></td>
		</tr>
		<tr>
			<td><font size="2"><b><fmt:message key="AM.allowanceFullName" bundle="${enLables}"/></b> </font></td>
			<td><hdiits:text name="allowanceDisplayName" validation="txt.isrequired,txt.isname" caption="Allowance Full Name" default="${actionList.allowDisplayName}" mandatory="true"  maxlength="50" size="30" onblur="checkSplCharExceptArg(this,'(.)')" onkeypress="return chkKey(event)"/></td>
		</tr>
		<tr>
			<td><font size="2"><b><fmt:message key="AM.allowacenDesc" bundle="${enLables}"/></b> </font></td>
			<td><hdiits:textarea name="allowanceDesc" default="${actionList.allowDesc}" caption="allowanceDesc" cols="32" rows="3" maxlength="190" onblur="checkSplCharExceptArg(this,'(.)')" /></td>
		</tr>
	</table>
 	</div>
 	<div id="tcontent2" class="tabcontent" tabno="1">
 	</div>
        <fmt:setBundle basename="resources.payroll.payrollLables_en_US" var="Lables" scope="request"/>
        <hdiits:hidden default="getAllowTypeView" name="givenurl"/>
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

	