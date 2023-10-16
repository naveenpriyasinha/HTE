<%@ include file="/WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>


<c:set var="resultObj" value="${result}">
</c:set>


<fmt:setBundle basename="resources.hr.allocation.Allocation"
	var="msgBundle" scope="request" />
	<script type="text/javascript">
	function Closebt()
{	
	method="POST";
	document.Allocation.action="./hrms.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
	
	document.Allocation.submit();
}
</script>
<hdiits:form name="Allocation" validate="true" method="POST"  action="./hrms.htm?" encType="text/form-data" >
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>

<table width="100%" align="center" 	style="margin: 5px 0px;  font-family: arial; color: #333333;border: solid 1px #6B2700; width: 50%; clear: center;">
	<tr>
		<td align="center">
		<hdiits:caption captionid="Allocation.norecfnd" bundle="${msgBundle}"/>
		</td>
	</tr>
		<tr>
		<td align="center">
		<hdiits:button name="close" type="button" captionid="Allocation.Close" bundle="${msgBundle}" onclick="Closebt()"/>
		</td>
		
		</tr>

</table>


</hdiits:form>



