<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page import="java.util.List"%>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>
<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>	  
<c:set var="actionList" value="${resultValue.actionList}" > </c:set>

<script>
alert('${resultValue.cadreId}');
</script>
<hdiits:form name="UpdateCadre" validate="true" method="POST" action="">
<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
		
			<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption captionid="cm.UpdateCadreMaster" bundle="${commonLables}"/></b></a></li>
		
		</ul>
	</div>

<br><br><br>
<hdiits:hidden  default ="${resultValue.cadreId}" name="cadreId" caption="cadreId"  />
<table width="100%" bgcolor="white" align="center" id="cadreTable">

<tr>
<td  align = "right" width="44%"><b><fmt:message key="cm.CadreName" bundle="${commonLables}"/></b></td>
<td>
<td  align = "left" >
<hdiits:text name="cadreName" id="cadreName" default = "${resultValue.cadreName}" mandatory="" size = "16"  validation="txt.istext" caption="cadreName" onblur="validateCadreName()" />
</td>

</tr>

<tr>
<td  align = "right" ><b><fmt:message key="cm.CadreCode" bundle="${commonLables}"/></b></td>
<td>
<td  align = "left" >
<hdiits:text name="cadreCode" id="cadreCode" default = "${resultValue.cadreCode}" mandatory="" size = "16"  validation="txt.istext" caption="cadreCode" />
</td>

</tr>

<tr>
<td  align = "right" ><b><fmt:message key="cm.CadreDesc" bundle="${commonLables}"/></b></td>
<td>
<td  align = "left" >
<hdiits:text name="cadreDesc" id="cadreDesc" default = "${resultValue.cadreDesc}" mandatory="" size = "16"  validation="txt.istext" caption="cadreDesc" onblur="validateCadreDesc()"/>
</td>

</tr>



</table>


</hdiits:form>

<fmt:setBundle basename="resources.payroll.payrollLables" var="Lables" scope="request"/>
<center >
	
	<hdiits:button name="Update" type="button" captionid="UPDATE" bundle="${commonLables}" onclick="saveInfo()"></hdiits:button>     

	 <hdiits:button name="btnClose1" type="button" captionid="eis.close" bundle="${Lables}" onclick="onclosefn()" />  
</center>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>


<script>

function saveInfo()
{
//alert ("inside function");
var url="./hrms.htm?actionFlag=UpdateCadreDtls";
document.UpdateCadre.action=url;
document.UpdateCadre.submit();
}
function onclosefn()
{
		window.location="./hrms.htm?actionFlag=viewCadreData";
}


function validateCadreName()
{
	var cadreName = document.forms[0].elements['cadreName'].value
	 if(cadreName == "")
	 {
		  alert('Please Enter CadreName');
	 }
}



function validateCadreDesc()
{
	var cadreDesc = document.forms[0].elements['cadreDesc'].value
	 if(cadreDesc == "")
	 {
		  alert('Please Enter Cadre Description');
	 }
}

</script>