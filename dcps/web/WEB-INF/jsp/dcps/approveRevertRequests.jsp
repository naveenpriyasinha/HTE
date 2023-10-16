<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>

<%@page import="com.tcs.sgv.common.constant.Constants"%>

<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables" scope="request"/>
<c:set var="resultObj" value="${result}"> </c:set>
<c:set var="resValue" value="${resultObj.resultValue}"> </c:set>

<script>

var approveOrRejectFlag ;
function approveOrRejectRevertRequests(flag)
{
	approveOrRejectFlag = flag ;
	var voucherPKs = "" ;
	var finalSelectedVoucher=0;
	var totalVouchers = document.getElementById("hdnCounter").value ;	
	var uri;
	var url;
	
	for(var k=1;k<=totalVouchers;k++)
	{
		if(document.getElementById("checkbox"+k).checked)
		{  
			
			finalSelectedVoucher = k ;	
		}
	}

	if(finalSelectedVoucher == 0)
	{
		alert('Please select at least one voucher');
		return false; 
	}

	for(var i=1;i<=totalVouchers;i++)
	{
		if(document.getElementById("checkbox"+i).checked)
		{
			if(i==finalSelectedVoucher)
			{
				voucherPKs = voucherPKs +  document.getElementById("checkbox"+i).value ;
			}
			else
			{
				voucherPKs = voucherPKs +  document.getElementById("checkbox"+i).value + "~" ;
			}
		}
	}

	if(approveOrRejectFlag==1)
	{
		uri = "ifms.htm?actionFlag=approveRevertRequests";
		url = "revertRequest=yes&requestType=Approve&voucherPKs="+voucherPKs;
	}
	if(approveOrRejectFlag==2)
	{
		uri = "ifms.htm?actionFlag=approveRevertRequests";
		url = "revertRequest=yes&requestType=Reject&voucherPKs="+voucherPKs;
	}

	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					dataStateChangedForAprOrRjtRqst(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}

function dataStateChangedForAprOrRjtRqst(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');

		var successFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
								
		if (successFlag=='true') {
			
			if(approveOrRejectFlag==1)
			{
				alert('Revert Requests are approved.');
			}
			if(approveOrRejectFlag==2)
			{
				alert('Revert Requests are rejected.');
			}

			self.location.href="ifms.htm?actionFlag=approveRevertRequests&elementId=700107";
		}
}

</script>

<hdiits:form name="ApproveRevertRequestForm" id="ApproveRevertRequestForm" encType="multipart/form-data" validate="true" method="post" >
<fieldset class="tabstyle"><legend> <b><fmt:message
				key="CMN.ALLREVERTREQUESTS" bundle="${dcpsLables}"></fmt:message></b> </legend>
	
	<br/>
	<br/>
				<input type="hidden" name="hdnCounter" id="hdnCounter" value="0"/>
		  			<div style="width:100%;overflow:auto;" >	
						<display:table list="${resValue.listReversionRequests}" size="10"  id="VO" pagesize="10" cellpadding="5" style="width:100%" requestURI="" >
						<display:setProperty name="paging.banner.placement" value="bottom" />

						<display:column style="text-align: center;"  class="oddcentre" titleKey="CMN.EMPSRNO" headerClass="datatableheader" sortable="true" value="${VO_rowNum}" ></display:column>
						<display:column style="text-align: center;"  class="oddcentre" titleKey="CMN.DDOCODE" headerClass="datatableheader"  sortable="true" ><c:out value="${VO[1]} "/></display:column>
						<display:column style="text-align: center;"  class="oddcentre" titleKey="CMN.MONTH" headerClass="datatableheader"  sortable="true" ><c:out value="${VO[2]} "/></display:column>
						<display:column style="text-align: center;"  class="oddcentre" titleKey="CMN.YEAR" headerClass="datatableheader"  sortable="true" ><c:out value="${VO[9]} "/></display:column>
						<display:column style="text-align: center;"  class="oddcentre" titleKey="CMN.BillGroup" headerClass="datatableheader"  sortable="true" ><c:out value="${VO[4]} "/></display:column>
						<display:column style="text-align: center;"  class="oddcentre" titleKey="CMN.Scheme" headerClass="datatableheader" value="${VO[5]}" sortable="true" ></display:column>
						<display:column style="text-align: center;"  class="oddcentre" titleKey="CMN.VOUCHERNO" headerClass="datatableheader" value="${VO[6]}" sortable="true" ></display:column>
						
						<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${VO[7]}" var="voucherDate"/>
						<display:column style="text-align: center;"  class="oddcentre" titleKey="CMN.VOUCHERDATE" headerClass="datatableheader" value="${voucherDate}" sortable="true" ></display:column>
						
						<display:column style="text-align: center;"  class="oddcentre" titleKey="CMN.REASONFORREVERT" headerClass="datatableheader" value="${VO[8]}" sortable="true" ></display:column>
						
						<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center;"  sortable="true"  title="<input type='checkbox' name='SelectAll' onclick='checkUncheckAll(this)'/>" >
							<input type="checkbox" name="checkbox" id="checkbox${VO_rowNum}" value="${VO[0]}"/>
							
							 	<script>
										document.getElementById("hdnCounter").value=Number(document.getElementById("hdnCounter").value) + 1;
								</script>
						</display:column>
						</display:table>
					</div>
		
	<br/>		
	<div align="center">
			<hdiits:button name="btnSubmit" id="btnSubmit" type="button"  captionid="BTN.APPROVE" bundle="${dcpsLables}" onclick="approveOrRejectRevertRequests(1);"/>
			<hdiits:button name="btnReject" id="btnReject" type="button"  captionid="BTN.REJECT" bundle="${dcpsLables}" onclick="approveOrRejectRevertRequests(2)" />
	</div>


</fieldset>
</hdiits:form>