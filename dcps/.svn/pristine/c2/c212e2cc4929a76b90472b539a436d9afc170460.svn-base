<%
try {
%>


<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>


<fmt:setBundle basename="resources.hr.payfixation.PayRelated" var="PayLab" scope="request"/>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<html>


<script type="text/javascript">
function SearchEmp(){
var href='./hrms.htm?actionFlag=getEmpSearchSelData&multiple=false';
		window.open(href,'chield', 'width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
}


var empArray = new Array();

function empSearch(from){

if (from.length == 0)
			    {
			    	alert('<fmt:message bundle="${PayLab}" key="Pf.selectbox"/>');
			    	return false;
			    }
			    else if (from.length > 1)
			    {
				   alert('<fmt:message bundle="${PayLab}" key="Pf.mroneRec"/>');
			    	return false;
			    }		       
else
{
for(var i=0; i<from.length; i++){

empArray[i] = from[i].split("~"); 

}
var single = empArray[0];

document.getElementById('EmpName').value = single[1];
document.getElementById('hempId').value = single[0];
}

}
function Closebt()
{	
	method="POST";
	document.frmVac.action="./hrms.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
	
	document.frmVac.submit();
}


</script>




<hdiits:form name="frmVac" validate="true" method="POST"  action="./hrms.htm?" encType="text/form-data" >
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>
		<hdiits:caption captionid="Pf.PayFixation" bundle="${PayLab}" captionLang="single"></hdiits:caption></b></a></li>
		
	</ul>
	</div>
	
	
	<div class="tabcontentstyle">
	
    <hdiits:hidden name="actionFlag" default="getEmpDetail"/>
	<div id="tcontent1" class="tabcontent" tabno="0">
	<hdiits:fieldGroup titleCaptionId="Pf.empsearch" bundle="${PayLab}" expandable="true" mandatory="true" id="payfixfieldGrp"> 
	<table align="center" width="25%">
	<tr align="center">
	<td width="25%"></td>
	<td width="25%"> <hdiits:text name="EmpName" id="EmpName" readonly="true" mandatory="true" /> </td>
	
	<td width="25%"><hdiits:button name="EmpSearch" captionid="Payfix.empsearch" type="button" onclick="SearchEmp();" bundle="${PayLab}"/> </td>
	<td width="25%"></td>
	</tr>
	
	</table>
</hdiits:fieldGroup>
	<hdiits:hidden name="hempId" id="hempId"/>	
	<br><br><br><br><br><br><br>
	<hdiits:table align="center">
	<tr >
	<td align="right">
	<hdiits:button name="Submit1"  type="button" captionid="Pay.Submit" onclick="javascript:validate()" bundle="${PayLab}"/>
	</td>
	<td align="left">
	<hdiits:button type="button" captionid="Pay.Close" name="Close" value="Close" onclick="Closebt()" bundle="${PayLab}"/>
	</td>
	</tr>
	</hdiits:table>
	
   
		    	  	
		    	
		
	
</div>
</div>
	
 	<script type="text/javascript">
function validate()
{

var gpf="";
gpf=document.frmVac.hempId.value;


if(gpf=="")
{

alert('<fmt:message bundle="${PayLab}" key="Pf.validempid"/>');
setFocusSelection('EmpName');
}
else{
document.frmVac.action="hrms.htm?actionFlag=getEmpDetail";
document.frmVac.submit();
}
}



</script>
	<script type="text/javascript">

		initializetabcontent("maintab")
		</script>
	<hdiits:validate controlNames="tesxt" locale='<%=(String)session.getAttribute("locale")%>' />

</hdiits:form>
</html>
<%
} catch (Exception e) {
		e.printStackTrace();
	}
%>
