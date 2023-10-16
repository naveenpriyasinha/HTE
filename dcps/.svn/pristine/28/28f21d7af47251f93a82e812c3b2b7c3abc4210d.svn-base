<%@ page isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<%@ page contentType="text/html;charset=UTF-8"%>

<fmt:setBundle basename="resources.CommonLables" var="ErrorPageLables" scope="request" />

<head>

<script type="text/javascript">
 
function submitErrorForm()
{
	var varUrl = "ifms.htm?actionFlag=getHomePage";
	document.ePage.action=varUrl;
	document.ePage.submit();
}
	if(document.getElementById('td_userDetailsTable')!= null)
	{
   		document.getElementById('td_userDetailsTable').style.display='none';
    }
	if(document.getElementById('currentApplication')!= null)
   	{
   		document.getElementById('currentApplication').style.display='none';
   	}
</script>
</head>

<c:set var="msgVar" value="ERRORPG.INTLSERVER"></c:set>
<center>
<table border=0 cellpspacing=0 cellpadding=2 width="400" height="400">
	<tr>
		<td valign="middle" align="center"></td>
		<td height="100%">
		<hdiits:form name="ePage" method="POST" validate="false" action="javascript:submitErrorForm();">
		
			<fieldset style="width:100%; align: left" class="tabstyle">
				<legend>Alert</legend>
					<table border=0 width="100%" cellpadding="2">
						<tr>
							<td valign="middle" align="center">&nbsp;</td>
							<td valign="middle" align="center">
								<font style="font-size: 14px; font-weight: normal;">
							 		<hdiits:caption captionid="${msgVar}" bundle="${ErrorPageLables}" captionLang="single" />
								</font>
  						    </td>
						</tr>
						<tr>
							<td colspan=2 valign="middle" align="center">
							<hdiits:submitbutton name="okbutton" value="OK" /></td>
						</tr>
					</table>
			</fieldset>				
		
		
		</hdiits:form>
		</td>
	</tr>
</table>
</center>







