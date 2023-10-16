<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@page import="com.tcs.sgv.common.constant.Constants"%>



<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLabels" scope="request"/>
<c:set var="resultObj" value="${result}"> </c:set>
<c:set var="resValue" value="${resultObj.resultValue}"> </c:set>
<c:set var="UserList" value="${resValue.UserList}"/>
<script type="text/javascript"  src="script/login/ajaxLoadContentSubmitPage_1.0.js"></script>

<script>

	function printEmpDetailReport(EmpId)
	{
		url = "ifms.htm?actionFlag=reportService&reportCode=700001&action=generateReport&empid="+EmpId;
		window_new_update(url);
	}

	function viewReport(empid)
	{
		url = "ifms.htm?actionFlag=reportService&reportCode=700001&action=generateReport&empid="+empid;
		alert(url);
		window_new_update(url);
	}

	function window_new_update(url)
	{
		var newWindow = null;
	   	var height = screen.height - 150;
	   	var width = screen.width;
	   	var urlstring = url;
	   	var urlstyle = "height="+height+",width="+width+",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
	   	newWindow = window.open(urlstring, "NomineeDetails", urlstyle);
	}
	
	function GoBack()
	{
		self.location.href = 'ifms.htm?actionFlag=loadDDOList&UserType=TO';
	}

	function submitAllForms() {

	    var cmb=document.DCPSForm.chkbxPhyFormReceived;
		var flag = 0;
		var EmpNos = "";
		var selectedFlag = false;

		if(cmb.checked == true)
		{
			flag=1;
			EmpNos=cmb.value;
		}
		else
		{
			if (cmb != null) {
				if (cmb.length != null) {
					for (i = 0; i < cmb.length; i++) {
						if (cmb[i].checked == true) {
							flag = 1;
							EmpNos += cmb[i].value + "~";
						}
					}
				}
			}
		}
	
		if (flag == 1) {
			var ForwardToPost = document.getElementById("ForwardToPost").value;
			url = "ifms.htm?actionFlag=sbmtPhyFrmSts&empNos="+ EmpNos +"&ForwardToPost="+ForwardToPost ;
			ForwardRequestUsingAjax(url);

		} else {
			alert("Please Select the request number to forward");
		}
	}
	
	function ForwardRequestUsingAjax(url)
	{
		xmlHttp=GetXmlHttpObject();

		if (xmlHttp==null)
		{
			alert ("Your browser does not support AJAX!");
			return;
		} 

		uri=url;
		xmlHttp.onreadystatechange=forwardDataStateChanged;
		xmlHttp.open("POST",uri,false);
		xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xmlHttp.send(uri);

	}
	
	function forwardDataStateChanged() 
	{ 
		if (xmlHttp.readyState==4)
		{ 
			if(xmlHttp.status==200)
			{
				alert("Your Request is Forwarded to Treasury Officer");
				self.location.reload();
			}
		}
	}

	function rejectAllForms()
	{
		 var cmb=document.DCPSForm.chkbxPhyFormReceived;
			var flag = 0;
			var EmpNos = "";
			var selectedFlag = false;

			if(cmb.checked == true)
			{
				flag=1;
				EmpNos=cmb.value;
			}
			else
			{
				if (cmb != null) {
					if (cmb.length != null) {
						for (i = 0; i < cmb.length; i++) {
							if (cmb[i].checked == true) {
								flag = 1;
								EmpNos += cmb[i].value + "~";
								

							}
						}
					}
				}
			}
			if (flag == 1) {
				
				url = "ifms.htm?actionFlag=RejReqTreasury&Emp_Id="+ EmpNos+"&rejectionFrom=ATO"  ;
				RejectRequestUsingAjax(url);

			} else {
				alert("Please Select the request number to reject");
			}
	}

	function RejectRequestUsingAjax(url)
	{
		xmlHttp=GetXmlHttpObject();

		if (xmlHttp==null)
		{
			alert ("Your browser does not support AJAX!");
			return;
		} 

		uri=url;
		xmlHttp.onreadystatechange=rejectDataStateChanged;
		xmlHttp.open("POST",uri,false);
		xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xmlHttp.send(uri);

	}

	function rejectDataStateChanged() 
	{ 
		if (xmlHttp.readyState==4)
		{ 
			if(xmlHttp.status==200)
			{
				alert("Form is sent back to the DDO assistant");
				self.location.reload();
			}
		}
	}
</script>
<hdiits:form name="DCPSForm" id="DCPSForm" encType="multipart/form-data" validate="true" method="post"  >
<fieldset class="tabstyle">
<legend><fmt:message
		key="CMN.PHYFORMSRCVD" bundle="${dcpsLabels}"></fmt:message></legend>
<table width="100%">
		  		<tr>
		  			<td>		
						<display:table list="${resValue.formPhyList}" size="10"  id="vo" pagesize="<%=Constants.PDWL_PAGE_SIZE %>"cellpadding="5" style="width:100%" requestURI="" >

						<display:column style="text-align: left;"  class="oddcentre" titleKey="CMN.EMPSRNO" headerClass="datatableheader" sortable="true"><c:out value="${vo_rowNum}"/></display:column>
						<display:column style="text-align: left;"  class="oddcentre" titleKey="CMN.DDOCODE" headerClass="datatableheader"  sortable="true" ><c:out value="${vo[1]}   (${vo[7]})"/></display:column>
						<display:column style="text-align: left;"  class="oddcentre" titleKey="CMN.EMPLOYEENAME" headerClass="datatableheader"  sortable="true" ><a href = # onclick="printEmpDetailReport('${vo[0]}');"><c:out value="${vo[2]}"/></a></display:column>
						
						<display:column style="text-align: left;"  class="oddcentre" titleKey="CMN.SEVARTHID" headerClass="datatableheader" sortable="true"><c:out value="${vo[8]}"/></display:column>
						
						<display:column style="text-align: left;"  class="oddcentre" titleKey="CMN.GENDERWOCOLON" headerClass="datatableheader" sortable="true" >
							<c:choose>
								<c:when test="${vo[3] == 'M'}">
									<c:out value="Male"></c:out>
								</c:when>
								<c:when test="${vo[3] == 'F'}">
									<c:out value="Female"></c:out>
								</c:when>
								<c:otherwise>
									<c:out value="Transgender"></c:out>
								</c:otherwise>
    						</c:choose>
						</display:column>
						
						<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[4]}" var="birthDate"/>
						<display:column style="text-align: left;"  class="oddcentre" titleKey="CMN.DOB" headerClass="datatableheader" value="${birthDate}" sortable="true" ></display:column>
						<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[6]}" var="onlineFormRcvdDate"/>
						<display:column style="text-align: left;"  class="oddcentre" titleKey="CMN.ONLINEFORMRCVDDATE" headerClass="datatableheader" value="${onlineFormRcvdDate}" sortable="true" ></display:column>
						<display:column style="text-align: left;"  class="oddcentre" titleKey="CMN.DESIGNATIONWOCOLON" headerClass="datatableheader" value="${vo[5]}" sortable="true" ></display:column>
						<display:column headerClass="datatableheader" style="text-align:center;width:1%" class="oddcentre" titleKey="CMN.PHYFORMRCVD">
							<input type="checkbox"   name="chkbxPhyFormReceived" id="chkbxPhyFormReceived" value="${vo[0]}"/>
						</display:column>
						</display:table>
				</td>
		  	</tr>
</table>
<br/>
</fieldset>
<br/>
<table width="100%" align="center" height="10%" cellpadding="0" cellspacing="0">
	<tr></tr>
	<tr></tr>
	<tr>
		<td width="100%" align="center">
		<input type="hidden" id="ForwardToPost" name="ForwardToPost" value="${UserList[0]}"/>	
			<hdiits:button name="btnSubmit" id="btnSubmit" type="button"  captionid="BTN.APPROVE" bundle="${dcpsLabels}" onclick="submitAllForms();"/>
			<hdiits:button name="btnReject" id="btnReject" type="button"  captionid="BTN.REJECT" bundle="${dcpsLabels}" onclick="rejectAllForms();"/>
			<hdiits:button name="btnBack" id="btnBack" type="button"  captionid="BTN.BACK" bundle="${dcpsLabels}" onclick="GoBack();"/>
		</td>
	</tr>	
</table>

</hdiits:form>	