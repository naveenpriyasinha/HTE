<%
	try {
%>
<%@ include file="../../../../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<fmt:setBundle basename="resources.eis.zp.zpAdminOffice.ZpAdminOfficeLabels" var="ZpAdminOfficeLabels" scope="request"/>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="ZpAdminOfficeMstList" value="${resValue.ZpAdminOfficeMstList}"></c:set>
<c:set var="paginateList" value="${resValue.paginateList}"></c:set>
<script type="text/javascript" src="script/common/cmnMstScr.js"></script>
<c:set value="1" var="i"></c:set>

<hdiits:form name="searchZpAdminOffice" validate="true" method="post" encType="multipart/form-data">
<hdiits:button name="btnCreate" type="button" classcss="bigbutton" captionid="ZPADMINOFFICE_ZPADMINOFFICECREATE" bundle="${ZpAdminOfficeLabels}"></hdiits:button>
<fieldset class="tabstyle"><legend> 
	                  <fmt:message key="ZPADMINOFFICE_SEARCHRESULT" bundle="${ZpAdminOfficeLabels}"></fmt:message></legend>

</fieldset>
	<% int srNo=1; %>
	
<table width="100%" align="center">
	<tr>
		<td align="center" width="">
			<fieldset class="tabstyle"><legend> 
	                  <fmt:message key="ZPADMINOFFICE_ZP_OFFICES" bundle="${ZpAdminOfficeLabels}"></fmt:message></legend>
				<display:table pagesize="15" name="${ZpAdminOfficeMstList}" id="row" export="false" style="width:100%" requestURI="">
					<display:column  class="oddcentre"  title="Sr No" headerClass="datatableheader"  style="text-align:center"><%=srNo++%></display:column>
					<display:column class="tablecelltext" titleKey="ZPADMINOFFICE_CODE" headerClass="datatableheader" style="text-align:center"></display:column>
				<display:column class="tablecelltext" titleKey="ZPADMINOFFICE_ADMIN_OFFICE_NAME" headerClass="datatableheader" style="text-align:center"><a href="#" onclick=""></a></display:column>
				</display:table>
		</fieldset>
		</td>
	</tr>
</table>
</div>
</div>
</hdiits:form>
<script type="text/javascript">




function closeCurrWindow(){
	showProgressbar();
	document.frmAdminCrtDept.action = "ifms.htm?actionFlag=getMenuOnApplicationPage&moduleCode=5";
	document.frmAdminCrtDept.submit();
}
function newZpAdminOfficeCreate(){
	var url;
	url = "ifms.htm?actionFlag=loadZPOfcData&elementId=100041";
	document.forms[0].method = 'post';
	document.forms[0].action = url;
	document.forms[0].submit();
}
function updateDetails(zpAdminOfficeMstPkID){
	var url;
	url = "ifms.htm?actionFlag=loadUpdateData&elementId=100041&zpAdminOfficeMstPkID="+zpAdminOfficeMstPkID;
	document.forms[0].method = 'post';
	document.forms[0].action = url;
	document.forms[0].submit();
}
function searchFunction(){
	
	var url;
	url = "ifms.htm?actionFlag=search_ZpAdminOffice";
	document.forms[0].method = 'post';
	document.forms[0].action = url;
	document.forms[0].submit();
}
initializetabcontent("mainTab");
function fnZpAdminOfficeClose()
{
		showProgressbar();
		document.forms[0].action="ifms.htm?actionFlag=getMenuOnApplicationPage&moduleCode=1000135";
		document.forms[0].submit();
}
</script>
<%
	}catch (Exception e) {
		e.printStackTrace();
	}
%>
