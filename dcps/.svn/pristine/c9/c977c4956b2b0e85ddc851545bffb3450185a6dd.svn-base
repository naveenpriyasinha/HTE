<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<fmt:setBundle basename="resources.acl.acl" var="ErrorPageLables" scope="request"/>

<head>

<script type="text/javascript">
 
function submitErrorForm()
{
var varUrl = "j_reauthentication_check";

if(document.getElementById('j_password').value == "")
{
	alert('<fmt:message bundle="${ErrorPageLables}" key="RELOGIN_ERROR1"/>');
}
else{
	document.frmReLogin.action=varUrl;
	document.frmReLogin.submit();	
}

}
   
</script>
</head>
								
						 
							<center>
							<table cellpspacing=0 cellpadding=2 width="500" height="400" border="0">
								<tr>
									<td valign="middle" align="center"></td>
									<td height="100%">
										<hdiits:form name="frmReLogin" method="POST" validate="false" action="javascript:submitErrorForm();" >
												<hdiits:fieldGroup>
													<table   border=0 width="100%" cellpadding="5">
														<tr valign="bottom">
													 		<td colspan="2" style="text-align: left;"> 
														 		<fmt:message bundle="${ErrorPageLables}" key="RELOGIN_NOTE"/> 
														 	</td>
														</tr>
														<tr>
													 		<td> 
													 			<span class="Label">
													 				<B><fmt:message bundle="${ErrorPageLables}" key="RELOGIN_TXN_PASSWORD"/></B>
													 			</span> 
													 		</td>
													 		<td>
													 		    <hdiits:password  type="txt-password" name="j_password"  validation="txt.isrequired"  caption="tt"  mandatory="true"></hdiits:password>
															</td>
														</tr>
														
														<% if( request.getParameter("error") != null ) {%>
														<tr valign="top">
															<td colspan="2" >
																<font color="red" style="color: red; vertical-align: middle;">
																	<fmt:message bundle="${ErrorPageLables}" key="RELOGIN_ERROR1"/>
																</font>
															</td>
														</tr>
														<%}%>
													   
														<tr>
															<td colspan=2 valign="middle" align="center">
																<hdiits:submitbutton name="okbutton" value="OK" />		
															</td>
														</tr>
														
													</table>
												</hdiits:fieldGroup>
									</hdiits:form>
								</td>
							</tr>
					</table>
					</center>
