<%try{ %>
<script type="text/javascript">

	var altPhoneNos='<fmt:message key="MSG_PHONES"/>';
	var altEmails='<fmt:message key="MSG_EMAIL"/>';
	
	var cmnLblArray = new Array();
	
	cmnLblArray[0] = '<fmt:message key="MUL_ADD_EDIT" />';
	
	cmnLblArray[1] = '<fmt:message key="MUL_ADD_DELETE" />';
	
	cmnLblArray[2] = '<fmt:message key="MUL_ADD_ON_DELETE_ALERT" />';
	
	cmnLblArray[3] = '<fmt:message key="MUL_ADD_NOT_ALLOWED_DELETE" />';

	cmnLblArray[4] = '<fmt:message key="MUL_ADD_VIEW" />';

	cmnLblArray[5] = '<fmt:message key="MUL_ADD_NOT_ALLOWED_EDIT" />';
	
	cmnLblArray[6] = '<fmt:message key="MUL_ADD_DUPLICATE_ALERT" />';
	
	cmnLblArray[7] = '<fmt:message key="ALERT_IS_NAME" />';
	

</script>
<%
}
catch (Exception e)
{
	e.printStackTrace();
}
%>