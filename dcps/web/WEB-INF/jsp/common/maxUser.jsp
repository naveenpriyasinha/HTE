<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="resources.core.lables" var="coreLables" scope="request"/>

<center>
<table border=0 cellpspacing=0 cellpadding=2 width="400" height="400">
			<tr>
				<td valign="middle" align="center"></td>
					<td height="100%">
					<hdiits:form name="maxUser" validate="false" method="POST">
					<hdiits:fieldGroup>
									<table border=0 width="100%" cellpadding="5"><tr><td valign="middle" align="center">
									&nbsp;
									</td>
									<td valign="middle" align="center">

									<font style="font-size: 14px; font-weight: normal;">
									<hdiits:caption captionid="LABEL.MAXUSER" bundle="${coreLables}" />
									</font>
									</td></tr>
									<tr>




								</tr></table>
						</hdiits:fieldGroup>
						</hdiits:form>
<script type="text/javascript">
document.getElementById("currentApplicationDiv").style.display='none';
//document.getElementById("toolbarHome").style.display='none';
//document.getElementById("toolbarChngPwd").style.display='none';
//document.getElementById("toolbarSetting").style.display='none';


</script>


						</td>
						</tr>
					</table>
		</center>




</script>
						
						
						</td>
						</tr>
					</table>
		</center>
					


















