<%try{ %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@page import="com.tcs.sgv.common.constant.Constants"%>

<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script  type="text/javascript"  src="script/pensionpay/SavedPensionBills.js"></script>
<script  type="text/javascript"  src="script/pensionpay/ECS.js"></script>
<script type="text/javascript"  src="script/pensionpay/Common.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>
<script type="text/javascript"	src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript" src="script/pensionpay/HeaderFields.js"></script>
<fmt:setBundle basename="resources.pensionpay.PensionLabels" var="pensionLabels" scope="request"/>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="totalRecords" value="${resValue.totalRecords}"></c:set>

<hdiits:form name="frmECSFiles"  method="post" validate="">
<fieldset style="width:100%"  class="tabstyle">
	<legend	id="headingMsg">
	<c:if test="${resValue.currRole == '365462'}">
			<b><fmt:message key="PPMT.ECSREPORT" bundle="${pensionLabels}"></fmt:message></b>
	</c:if>
	<c:if test="${(resValue.currRole eq '365463') and (resValue.authFlag eq 'N')}">
			<b><fmt:message key="PPMT.AUTHORISEECS" bundle="${pensionLabels}"></fmt:message></b>
	</c:if>
	<c:if test="${(resValue.currRole eq '365463') and (resValue.authFlag eq 'Y')}">
			<b><fmt:message key="PPMT.AUTHORISEDECS" bundle="${pensionLabels}"></fmt:message></b>
	</c:if>
	</legend>
<div style="width:100%;overflow:auto;height:100%;" >

<display:table list="${resValue.lLstECSFiles}" id="vo" requestURI="" pagesize="<%= Constants.PAGE_SIZE %>"  export="false" style="width:100%;text-align:center"  partialList="true" 
						 offset="1" excludedParams="ajax" size="${totalRecords}" sort="external" defaultsort="3" defaultorder="descending" cellpadding="5" >	
	<c:if test="${(resValue.currRole=='365462') or (resValue.currRole == '365463' and (resValue.authFlag eq 'N'))}">
		<display:column title="<input name='chkSelect'type='checkbox' onclick='checkUncheckAll(this)'/>"
			headerClass="datatableheader" style="width:2%" >
			<input type="checkbox" align="middle" name="chkbxECSFiles"
				id="chkbxECSFiles"
				 value="${vo[0]}_${vo_rowNum}" />
			<input type="hidden" id="hdChequeId_${vo_rowNum}" name="hdChequeId" value="${vo[2]}">
					
		</display:column>	
	</c:if>	
	<display:column titleKey="PPMT.ECSFILENO" headerClass="datatableheader"
			 style="width:10%;text-align:center" >
			<a href="#" onclick="javascript:showECSFile('${vo[0]}');">${vo[0]}</a>
	</display:column>
	<display:column titleKey="PPMT.ECSAMOUNT" headerClass="datatableheader"
			 style="width:10%;text-align:center" >
			 	${vo[3]}			
	</display:column>
	<display:column titleKey="PPMT.ECSGENDATE" headerClass="datatableheader"
			 style="width:10%;text-align:center" >
			 <fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[4]}"/>	
	</display:column>
	<c:if test="${(resValue.currRole eq '365463') and (resValue.authFlag eq 'N')}">
			<display:column titleKey="PPMT.AUTHODATE" headerClass="datatableheader"
			style="width:15%" >
			<input type="hidden" name="currdate" id="currdate" value='<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${resValue.currdate}"/>'/>
			<input type="text" name="txtAuthDate_${vo_rowNum}" id="txtAuthDate_${vo_rowNum}" size="12" onkeypress="digitFormat(this);dateFormat(this);" maxlength="10"
					onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);compareDatesForAuth(document.getElementById('currdate'),this,'Authorization Date should be greater than current date.','<');">&nbsp;
			<img src='images/CalendarImages/ico-calendar.gif'
			onClick='window_open("txtAuthDate_${vo_rowNum}",375,570)'style="cursor: pointer;" />
			</display:column>
			<display:column titleKey="PPMT.CHQNO" headerClass="datatableheader"
			 style="width:10%;text-align:center" >
			 <input type="text" name="txtChequeNo_${vo_rowNum}" id="txtChequeNo_${vo_rowNum}" size="8" onKeyPress="numberFormat(this)">				
			</display:column>
			<display:column titleKey="PPMT.SETTLEDATE" headerClass="datatableheader"
			style="width:15%" >
			<input type="text" name="txtSettDate_${vo_rowNum}" id="txtSettDate_${vo_rowNum}" size="12" onkeypress="digitFormat(this);dateFormat(this);" maxlength="10"
					onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);compareDatesForAuth(document.getElementById('currdate'),this,'Settlement Date should be greater than current date.','<');">&nbsp;
			<img src='images/CalendarImages/ico-calendar.gif'
			onClick='window_open("txtSettDate_${vo_rowNum}",375,570)'style="cursor: pointer;" />
			</display:column>
	</c:if>
	<c:if test="${(resValue.currRole eq '365463') and (resValue.authFlag eq 'Y')}">
			<display:column titleKey="PPMT.GENMAN" headerClass="datatableheader"
			style="width:15%" >
			<a href="#" onclick="javascript:generateMandate('${vo[0]}');">Generate Mandate</a>
			</display:column>
	</c:if>
			
</display:table>
</div>
</fieldset>
<div align="center">
	<c:if test="${resValue.currRole == '365462'}">
		<hdiits:button type="button" name="forward" captionid="PPMT.FORWARDTOPMNTATO"  bundle="${pensionLabels}" onclick="frwdECSForAuth();" classcss="bigbutton"  />			
		<hdiits:button type="button" name="discard" captionid="PPMT.DISCARD"  bundle="${pensionLabels}" onclick="discardECS()" />
	</c:if>
	<c:if test="${resValue.currRole == '365463' and (resValue.authFlag eq 'N')}">
		<hdiits:button type="button" name="authECS" captionid="PPMT.AUTHOECS"  bundle="${pensionLabels}" classcss="bigbutton" onclick="authorizeECS();" />
		<hdiits:button type="button" name="reject" captionid="PPMT.REJECT"  bundle="${pensionLabels}" onclick="discardECS()" />
	</c:if>													
	<hdiits:button type="button" name="close" captionid="PNSN.CLOSE" bundle="${pensionLabels}"  onclick="javascript:pageClose()"/>
</div>
</hdiits:form>
<script>

</script>
<%}catch(Exception e){
e.printStackTrace();
}%>