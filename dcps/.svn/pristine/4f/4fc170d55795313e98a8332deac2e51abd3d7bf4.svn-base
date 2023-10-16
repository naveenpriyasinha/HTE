<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript" >
function submitDetails(){
	
	var FieldDept = document.getElementById("cmbFieldDept").value;
	var DdoDtl = document.getElementById("cmbDdoDtl").value;	

	if(FieldDept == "-1"){
		alert('Please select a Field Department');
		document.getElementById("cmbFieldDept").focus();
		return false;
	}
	if(DdoDtl == "-1"){
		alert('Please select a DDO Office');
		document.getElementById("cmbDdoDtl").focus();
		return false;
	}

	var url = "ifms.htm?actionFlag=showDeletePostDtl&elementId=90002635&FD="+FieldDept+"&DDO="+DdoDtl;

	document.frmPostDtls.action = url ;
	document.frmPostDtls.submit();
	showProgressbar("Please wait...");
}
</script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables" scope="request" />
<script type="text/javascript" src="script/common/common.js"></script>
<hdiits:form name="frmPostDtls"	validate="true" method="post" encType="multipart/form-data" >

<fieldset class="tabstyle"><legend>Post Details</legend>
<table width="50%">
<tr>
			<td width="8%">
				<fmt:message key="CMN.FIELDDEPT" bundle="${dcpsLables}"></fmt:message>
			</td>
			<td width="42%">
			<select name="cmbFieldDept" id="cmbFieldDept" style="width: 95%" disabled="disabled">
				<option value="-1"><fmt:message	key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="FieldDept" items="${resValue.lLstFieldDept}">
					<option value="${FieldDept.id}" selected="selected">
						<c:out value="${FieldDept.desc}"/>
					</option>						
				</c:forEach>
			</select> <label id="mandtryFinal" class="mandatoryindicator">*</label>	
			</td>
</tr>
<tr>
			<td width="8%">
				DDO
			</td>
			<td width="42%">
			<select name="cmbDdoDtl" id="cmbDdoDtl" style="width: 95%">
				<c:forEach var="ddo" items="${resValue.lLstDdo}">
					<option value="${ddo.id}">
						<c:out value="${ddo.desc}"/>
					</option>						
				</c:forEach>			
			</select> <label id="mandtryFinal" class="mandatoryindicator">*</label>	
			</td>
</tr>
<tr><td width="15%"><br></br></td></tr>
<tr>
			<td width="15%">
				
			</td>
			<td width="40%">
			<hdiits:button name="btnSubmitData" id="btnSubmitData" type="button" captionid="BTN.GO" bundle="${dcpsLables}" onclick="submitDetails();" />			
			<hdiits:button name="btnClose" id="btnClose" type="button" captionid="BTN.CLOSE"	bundle="${dcpsLables}" onclick="winCls();" />
			</td>
</tr>
</table>
</fieldset>
</hdiits:form>

<ajax:select baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getDdoListFromFieldDept"
 source="cmbFieldDept" target="cmbDdoDtl" parameters="FieldDeptCode={cmbFieldDept}" ></ajax:select>