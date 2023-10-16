<%@ page language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script>
function showPensionerDtls()
{
	if(document.getElementById("txtPpoNo").value !='')
	{
		var url="ifms.htm?actionFlag=getPensionerDetailsFrmPpoNo&ppoNo="+document.getElementById("txtPpoNo").value;
		var newWindow;
		var height = screen.height - 100;
		var width = screen.width;
		var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
		newWindow = window.open(url, "PensionerDetails", urlstyle);
		
	}
}
</script>
<hdiits:form name="PensionerDtlsForm" id="PensionerDtlsForm"
	method="POST" action="" encType="multipart/form-data" validate="true">
<table align="left">
<tr>
<td>
PPO Number
</td>
<td>
<input type="text" id="txtPpoNo" name="txtPpoNo" style="display: ;text-transform: uppercase;"/>
</td>
</tr>
</table>
<br/><br/>
<table align="left">
<tr>
<td>
<hdiits:button name="btnGenReport" type="button" caption="Generate Report" onclick="showPensionerDtls()" classcss="bigbutton"/>
</td>
</tr>
</table>
</hdiits:form>

