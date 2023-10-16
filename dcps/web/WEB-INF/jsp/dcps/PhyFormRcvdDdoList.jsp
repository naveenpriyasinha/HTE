<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@page import="com.tcs.sgv.common.constant.Constants"%>

<script>
function GoBack()
{
	self.location.href = 'ifms.htm?actionFlag=validateLogin';
}
</script>

<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLabels" scope="request"/>

<c:set var="resultObj" value="${result}"> </c:set>
<c:set var="resValue" value="${resultObj.resultValue}"> </c:set>

<hdiits:form name="DDOList" id="DDOList" encType="multipart/form-data" validate="true" method="post"  >
<fieldset class="tabstyle"><legend><fmt:message
		key="CMN.PHYFORMSRCVD" bundle="${dcpsLabels}"></fmt:message></legend>
<div align="center">
		<div style="float: inherit; border:1px solid #000000; background-color: transparent;width:100%; height:375px;   overflow-x:scroll;">
			<table width="98%" align="center">
					<tr>
						<td width="20%" align="left"><label style="color:red"><fmt:message key="MSG.FORMRCVD" bundle="${dcpsLabels}"></fmt:message></label></td>
					</tr>
			  		<tr>
			  			<td>		
							<display:table list="${resValue.formList}" size="10"  id="formList" pagesize="<%=Constants.PDWL_PAGE_SIZE %>"cellpadding="5" style="width:99%" requestURI="" >
								<display:column style="height:35;text-align: center;"  class="oddcentre" titleKey="CMN.DDOCODE" headerClass="datatableheader" sortable="true"><a href="ifms.htm?actionFlag=loadPhyForm&ddoCode=${formList[0]}">${formList[0]}</a></display:column>
								<display:column style="height:35;text-align: center;"  class="oddcentre" titleKey="CMN.DDONAME" headerClass="datatableheader" value="${formList[1]}" sortable="true" ></display:column>
								<display:column style="height:35;text-align: center;"  class="oddcentre" titleKey="CMN.DDODESIGNATION" headerClass="datatableheader" value="${formList[2]}" sortable="true" ></display:column>
							</display:table>
						</td>
			  	</tr>
			</table>
		</div>
</div>

	<table width="100%" align="center" height="10%" cellpadding="0" cellspacing="0">
		<tr></tr>
		<tr></tr>
		<tr>
			<td width="100%" align="center">
				<hdiits:button name="btnBack" id="btnBack" type="button" captionid="BTN.BACK" bundle="${dcpsLabels}" onclick="GoBack();"/>
			</td>
		</tr>	
   </table>
</fieldset>
</hdiits:form>	

