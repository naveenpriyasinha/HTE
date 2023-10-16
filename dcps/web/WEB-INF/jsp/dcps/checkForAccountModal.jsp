
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables"
	scope="request" />
<script language="JavaScript">
function onKeyDownEvent(event){
	if(event.keyCode == 78)
	{
		CloseBox();
	}
	else if (event.keyCode == 89)
	{
		openBox();
	}
}


function CloseBox()
{
	
	window.returnValue = "No";
	self.close();
	
}
function openBox()
{
	
	window.returnValue = "Yes";
	self.close();
}	
</script>

<table  border = "0">
<tr>
<td colspan = "2">Does the Employee have a 21-digit DCPS ID/HTESevaarth ID ?<br><b>Click No in case you want to enter data for new employee.</b></td></tr>
<tr><td colspan="2">&nbsp;</td></tr>
<tr>
<td align = "center">
<hdiits:button	name="BTN.YES" id="btnForwardForUpdateTotally" type="button"	captionid="CMN.YES" bundle="${dcpsLables}"
								onclick="openBox();" /></td>
<td align="center"><hdiits:button	name="BTN.NO" id="btnForwardForUpdateTotally" type="button" captionid="CMN.NO" bundle="${dcpsLables}" onclick="CloseBox();" /></td>
</tr></table>