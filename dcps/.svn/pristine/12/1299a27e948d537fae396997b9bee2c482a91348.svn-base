<%try{ %>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script type="text/javascript" src='<c:url value="script/common/calendar.js"/>'></script>
<script type="text/javascript" src='<c:url value="script/common/CalendarPopup.js"/>'></script>
<script type="text/javascript" src="script/pensionpay/PensionPayWorkList.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>
<script type="text/javascript" src="script/pensionpay/HeaderFields.js"></script>
<script type="text/javascript" src="script/pensionpay/ECS.js"></script>
<fmt:setBundle basename="resources.pensionpay.PensionLabels" var="pensionLabels" scope="request" />
<script type="text/javascript"> 

LISTREASONS = '<option value="reason1"><fmt:message key="PPMT.REASON1" bundle="${pensionLabels}"></fmt:message></option>'
+'<option value="reason2"><fmt:message key="PPMT.REASON2" bundle="${pensionLabels}"></fmt:message></option>'
+'<option value="reason3"><fmt:message key="PPMT.REASON3" bundle="${pensionLabels}"></fmt:message></option>'
+'<option value="others"><fmt:message key="PPMT.OTHERS" bundle="${pensionLabels}"></fmt:message></option>';
</script>




<hdiits:form name="frmBounceCheque" method="post" validate="" >
<fieldset style="width:100%"  class="tabstyle">
<legend	id="headingMsg">
			<b><fmt:message key="PPMT.RETECSDTLS" bundle="${pensionLabels}"></fmt:message></b>
	</legend>
	
	
<input type="hidden" name="hidECSReturnDtlsGridSize" id="hidECSReturnDtlsGridSize" value="0">
<hdiits:button name="ECSReturnAdd" type="button" captionid="PPMT.ADDROW" bundle="${pensionLabels}" onclick="ecsreturndtls();"  />
<table width="98%" id="tblECSReturn">
	     <tr class="datatableheader"> 
			    		<td width="20%" class="datatableheader"><fmt:message key="PPMT.MANDATENO" bundle="${pensionLabels}" ></fmt:message></td>	
			    		<td width="15%" class="datatableheader"><fmt:message key="PPMT.PPONO" bundle="${pensionLabels}"></fmt:message></td>
						<td width="15%" class="datatableheader"><fmt:message key="PPMT.PENNAME" bundle="${pensionLabels}"></fmt:message></td>
						<td width="15%" class="datatableheader"><fmt:message key="PPMT.AMOUNT" bundle="${pensionLabels}"></fmt:message></td>
						<td width="25%" class="datatableheader"><fmt:message key="PPMT.ROF" bundle="${pensionLabels}"></fmt:message></td>
						<td width="10%" class="datatableheader"><fmt:message key="PPMT.DELETE" bundle="${pensionLabels}"></fmt:message></td>
		 </tr>		
	
</table>
</fieldset>	
<table align="center">
<hdiits:button name="btnSave" id="btnSave" type="button"  classcss="bigbutton" captionid="PPMT.SAVE" bundle="${pensionLabels}" onclick="validateBeforeSave();" />
<hdiits:button name="btnClose" id="btnClose" type="button"  classcss="bigbutton" captionid="PPMT.CLOSE" bundle="${pensionLabels}" onclick="winCls();"/>
</table>
</hdiits:form>
<%}catch(Exception ex){
	ex.printStackTrace();
}
	%>