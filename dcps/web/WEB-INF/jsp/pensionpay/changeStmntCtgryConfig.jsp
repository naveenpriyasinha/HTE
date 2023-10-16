<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script type="text/javascript"  src="script/common/common.js"></script>
<script  type="text/javascript"  src="script/pension/Common.js"></script>

<fmt:setBundle basename="resources.pensionpay.PensionLabels" var="pensionLabels" scope="request"/>
<fmt:setBundle basename="resources.pensionpay.PensionConstants" var="pensionConstants" scope="request"/>
<script  type="text/javascript"  src="script/pensionpay/PensionConfig.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="lLstChangeStmntCtgryConfig" value="${resValue.lLstChangeStmntCtgryConfig}"></c:set>

<input type="hidden" name="hidListOfCtgry" id="hidListOfCtgry" value="${lLstChangeStmntCtgryConfig}"></input>


<hdiits:form name="changeStmntCtgryConfig" id="changeStmntCtgryConfig" method="POST"  encType="multipart/form-data" validate="true" >
	<fieldset style="width: 100%" class="tabstyle"><legend
	id="headingMsg"><b> <fmt:message key="CHNGSTMNT.CATEGORYCONFIG"
	bundle="${pensionLabels}"></fmt:message></b></legend>
	<div>&nbsp;</div>
	<table width="70%" align="left">
	  <tr>
	  	<td width="10%" align="left" >
		<td width="5%" align="left" >
			<input type="checkbox" name="chkChangeStmntCtgryConfig" id="chkChangeStmntCtgryConfig1" value="1"></input>
		</td>
		<td width="20%" align="left">
		   <label><fmt:message key="CHANGSTMNT.CATEGORY1" bundle="${pensionConstants}"></fmt:message></label>
		</td>
		<td width="10%" align="left" >
		<td width="5%" align="left" >
			<input type="checkbox" name="chkChangeStmntCtgryConfig" id="chkChangeStmntCtgryConfig2" value="2"></input>
		</td>
		<td width="20%" align="left">
		   <label><fmt:message key="CHANGSTMNT.CATEGORY2" bundle="${pensionConstants}"></fmt:message></label>
		</td>
	  </tr>
	  <tr>
	  	<td width="10%" align="left" >
		<td width="5%" align="left" >
			<input type="checkbox" name="chkChangeStmntCtgryConfig" id="chkChangeStmntCtgryConfig3" value="3"></input>
		</td>
		<td width="20%" align="left">
		   <label><fmt:message key="CHANGSTMNT.CATEGORY3" bundle="${pensionConstants}"></fmt:message></label>
		</td>
		<td width="10%" align="left" >
		<td width="5%" align="left" >
			<input type="checkbox" name="chkChangeStmntCtgryConfig" id="chkChangeStmntCtgryConfig4" value="4"></input>
		</td>
		<td width="20%" align="left">
		   <label><fmt:message key="CHANGSTMNT.CATEGORY4" bundle="${pensionConstants}"></fmt:message></label>
		</td>
	  </tr>
	  <tr>
	  	<td width="10%" align="left" >
		<td width="5%" align="left" >
			<input type="checkbox" name="chkChangeStmntCtgryConfig" id="chkChangeStmntCtgryConfig6" value="6"></input>
		</td>
		<td width="20%" align="left">
		   <label><fmt:message key="CHANGSTMNT.CATEGORY6" bundle="${pensionConstants}"></fmt:message></label>
		</td>
		<td width="10%" align="left" >
		<td width="5%" align="left" >
			<input type="checkbox" name="chkChangeStmntCtgryConfig" id="chkChangeStmntCtgryConfig7" value="7"></input>
		</td>
		<td width="20%" align="left">
		   <label><fmt:message key="CHANGSTMNT.CATEGORY7" bundle="${pensionConstants}"></fmt:message></label>
		</td>
	  </tr>
	  <tr>
	  	<td width="10%" align="left" >
		<td width="5%" align="left" >
			<input type="checkbox" name="chkChangeStmntCtgryConfig" id="chkChangeStmntCtgryConfig8" value="8"></input>
		</td>
		<td width="20%" align="left">
		   <label><fmt:message key="CHANGSTMNT.CATEGORY8" bundle="${pensionConstants}"></fmt:message></label>
		</td>
		<td width="10%" align="left" >
		<td width="5%" align="left" >
			<input type="checkbox" name="chkChangeStmntCtgryConfig" id="chkChangeStmntCtgryConfig9" value="9"></input>
		</td>
		<td width="20%" align="left">
		   <label><fmt:message key="CHANGSTMNT.CATEGORY9" bundle="${pensionConstants}"></fmt:message></label>
		</td>
	  </tr>
	  <tr>
	  	<td width="10%" align="left" >
		<td width="5%" align="left" >
			<input type="checkbox" name="chkChangeStmntCtgryConfig" id="chkChangeStmntCtgryConfig10" value="10"></input>
		</td>
		<td width="20%" align="left">
		   <label><fmt:message key="CHANGSTMNT.CATEGORY10" bundle="${pensionConstants}"></fmt:message></label>
		</td>
		<td width="10%" align="left" >
		<td width="5%" align="left" >
			<input type="checkbox" name="chkChangeStmntCtgryConfig" id="chkChangeStmntCtgryConfig11" value="11"></input>
		</td>
		<td width="20%" align="left">
		   <label><fmt:message key="CHANGSTMNT.CATEGORY11" bundle="${pensionConstants}"></fmt:message></label>
		</td>
	  </tr>
	  <tr>
	  	<td width="10%" align="left" >
		<td width="5%" align="left" >
			<input type="checkbox" name="chkChangeStmntCtgryConfig" id="chkChangeStmntCtgryConfig12" value="12"></input>
		</td>
		<td width="30%" align="left">
		   <label><fmt:message key="CHANGSTMNT.CATEGORY12" bundle="${pensionConstants}"></fmt:message></label>
		</td>
		<td width="10%" align="left" >
		<td width="5%" align="left" >
			<input type="checkbox" name="chkChangeStmntCtgryConfig" id="chkChangeStmntCtgryConfig14" value="14"></input>
		</td>
		<td width="30%" align="left">
		   <label><fmt:message key="CHANGSTMNT.CATEGORY14" bundle="${pensionConstants}"></fmt:message></label>
		</td>
	</tr>
	<tr>
		<td width="10%" align="left" >
		<td width="5%" align="left" >
			<input type="checkbox" name="chkChangeStmntCtgryConfig" id="chkChangeStmntCtgryConfig15" value="15"></input>
		</td>
		<td width="30%" align="left">
		   <label><fmt:message key="CHANGSTMNT.CATEGORY15" bundle="${pensionConstants}"></fmt:message></label>
		</td>
	</tr>
	
	  
	
</table>
	</fieldset>
	<div>&nbsp;</div>
<div align="center">
<hdiits:button name="btnSave"	type="button" captionid="RECO.SAVE" bundle="${pensionLabels}" onclick="saveChangeStmntCtgryConfig();"/>&nbsp;&nbsp;&nbsp;&nbsp;
<hdiits:button name="btnClose"	type="button" captionid="RECO.CLOSE" bundle="${pensionLabels}" onclick="winCls();" />
</div>
</hdiits:form>
<script type="text/javascript" language="javascript">
var listOfCtgry = document.getElementById("hidListOfCtgry").value;
var tempCtgryArr = listOfCtgry.substr(1,listOfCtgry.length-2);
var arrTotalCtgry = tempCtgryArr.split(",");
var tempArr  =  new Array();
for(var j=0;j<arrTotalCtgry.length;j++)
{
	for(var i=1;i<=15;i++) 
	{
		if(arrTotalCtgry[j].trim() == i)
		{
			document.getElementById("chkChangeStmntCtgryConfig"+i).checked = true;
		}
	}
}
</script>
