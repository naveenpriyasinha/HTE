<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/gpf/gpfValidation.js"></script>
<script language="javascript">

function checkUncheckAll(theElement)
{
	var theForm = theElement.form, z = 0;	
	 for(z=0; z<theForm.length;z++)
	 {		 
	      if(theForm[z].type == 'checkbox' && theForm[z].name != 'SelectAll')
		  {
			  theForm[z].checked = theElement.checked;
		  }
     }   
}	
function approveSchedule(){
	var totalCount= document.getElementById("vo").rows.length - 1;
	var billGroupIds = "";
	var finYearIds = "";
	var monthId = "";
	var voucherNos = "";
	var voucherDates = "";
	var flag=0;
	var arrChkBox = document.getElementsByName("chkRequest");
	var currRecord = "";
	var approve = "";
	var chkApprove = 0;

		for(var i=0;i<arrChkBox.length;i++)
		{		
			if(arrChkBox[i].checked == true)
			{
				currRecord = (arrChkBox[i].id.split("_"))[1];
				if(document.getElementById("txtVoucherNo"+currRecord).value == "" || document.getElementById("txtVoucherDate"+currRecord).value == ""){
					alert("Please provide voucher details for the checked requests.");
					return;
				}
				//scheduleIds = scheduleIds + document.getElementById("chkRequest"+i).value + "~" ;
				billGroupIds = billGroupIds + document.getElementById("hidBillGroupId"+currRecord).value + "~" ;
				finYearIds = finYearIds + document.getElementById("hidYearId"+currRecord).value + "~" ;
				monthId = monthId + document.getElementById("hidMonthId"+currRecord).value + "~" ;
				voucherNos = voucherNos + document.getElementById("txtVoucherNo"+currRecord).value + "~";
				voucherDates = voucherDates + document.getElementById("txtVoucherDate"+currRecord).value + "~";
				approve = document.getElementById("txtVoucherDate"+currRecord).value
				flag = 1;
			}
		}
	
	if(flag == 1)
	{
		var uri = 'ifms.htm?actionFlag=approveGPFSchedule';
		var url ='&billGroupIds='+billGroupIds+'&finYearIds='+finYearIds+'&monthId='+monthId+'&voucherNos='+voucherNos+'&voucherDates='+voucherDates;
		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function(myAjax) {
						getApproveScheduleMsg(myAjax);
					},
			        onFailure: function(){ alert('Something went wrong...')} 
			          } );
	}
	else
	{
		alert("Please select a Schedule");
	}

}
function getApproveScheduleMsg(myAjax){
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblSaveFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if (lblSaveFlag) {
		alert('GPF Schedule Approved Successfully');
		self.location.reload();		
	}
}

function discardSchedule(){
	var totalCount= document.getElementById("vo").rows.length - 1;
	var billGroupIds = "";
	var finYearIds = "";
	var monthId = "";
	var flag=0;
	var lArrChkBox = document.getElementsByName("chkRequest");
	var currRecord = "";
	for(var i=0;i<lArrChkBox.length;i++)
	{
		if(lArrChkBox[i].checked == true)
		{
			currRecord = (lArrChkBox[i].id.split("_"))[1];
			billGroupIds = billGroupIds + document.getElementById("hidBillGroupId"+currRecord).value + "~" ;
			finYearIds = finYearIds + document.getElementById("hidYearId"+currRecord).value + "~" ;
			monthId = monthId + document.getElementById("hidMonthId"+currRecord).value + "~" ;
			flag = 1;
		}
	}
	if(flag == 1)
	{
		var uri = 'ifms.htm?actionFlag=discardGPFSchedule';
		var url='&billGroupIds='+billGroupIds+'&finYearIds='+finYearIds+'&monthId='+monthId;
		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function(myAjax) {
						getDiscardScheduleMsg(myAjax);
					},
			        onFailure: function(){ alert('Something went wrong...')} 
			          } );
	}
	else
	{
		alert("Please select a Schedule");
	}
}
function getDiscardScheduleMsg(myAjax){
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblSaveFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if (lblSaveFlag) {
		alert('GPF Schedule Discarded');
		self.location.reload();		
	}
}
function viewDiscardedSchedules()
{
	var url = "ifms.htm?actionFlag=loadDiscardedSchedules";
	var height = screen.height - 300;
   	var width = screen.width - 400;
   	var urlstring = url;
   	var urlstyle ="height="+height+",width="+width+",titlebar=no,directories=no,location=no,resizable=no,scrollbars=yes,status=no,titlebar=no,toolbar=no,left=150";
   	   	 
	var newWindow = null;
   	newWindow = window.open(urlstring,"Discarded",urlstyle,"false");

}
</script>

<fmt:setBundle basename="resources.gpf.gpfLabels" var="gpfLables" scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="MonthlyData" value="${resValue.MonthlyData}"></c:set>

<hdiits:form name="frmGPFDraftSchedule" action="" id="frmGPFDraftSchedule" encType="multipart/form-data" validate="true" method="post">
<fieldset class="tabstyle" ><legend><fmt:message key="CMN.DRAFTSCH" bundle="${gpfLables}"></fmt:message></legend>

<display:table list="${MonthlyData}" pagesize="10"  id="vo" cellpadding="4" requestURI="">

		<display:setProperty name="paging.banner.placement" value="bottom" />
		
		<display:column style="height:35;text-align: left;" class="oddcentre" titleKey="CMN.GROUP" headerClass="datatableheader" value="D">
		</display:column>
		
		<display:column style="height:35;text-align: left;" class="oddcentre" titleKey="CMN.BILLGRP" headerClass="datatableheader" >
		<c:out value="${vo[0]}"></c:out> 
		<input type="hidden" id="hidBillGroupId${vo_rowNum}"  name="hidBillGroupId${vo_rowNum}" readOnly="readOnly" value="${vo[7]}"/>
		</display:column>
		
		<display:column style="text-align: left;" class="oddcentre" titleKey="CMN.MONTH" headerClass="datatableheader" > 
		<c:out value="${vo[1]}"></c:out>
		<input type="hidden" id="hidMonthId${vo_rowNum}"  name="hidMonthId${vo_rowNum}" readOnly="readOnly" value="${vo[8]}"/>
		</display:column>
		
		<display:column style="text-align: left;" class="oddcentre" titleKey="CMN.YEAR" headerClass="datatableheader" >
		<c:out value="${vo[2]}"></c:out> 
		<input type="hidden" id="hidYearId${vo_rowNum}"  name="hidYearId${vo_rowNum}" readOnly="readOnly" value="${vo[9]}"/>
		</display:column>

		<display:column style="text-align: left;width:120px" class="oddcentre" titleKey="CMN.TOTALSUB" headerClass="datatableheader" value="${vo[3]}"> 
		</display:column>
		
		<display:column style="text-align: left;width:80px" class="oddcentre" titleKey="CMN.VOUCHERNO" headerClass="datatableheader" >
		<c:choose>
		<c:when test="${vo[4] == 'A'}">
		<input type="text" id="txtVoucherNo${vo_rowNum}"  name="txtVoucherNo" readOnly="readOnly" value="${vo[5]}"/>
		</c:when>
		<c:otherwise>
		<input type="text" id="txtVoucherNo${vo_rowNum}" name="txtVoucherNo" value=""/>
		</c:otherwise>
		</c:choose>
		</display:column>
		
		<display:column style="text-align: left;width:140px" class="oddcentre" titleKey="CMN.VOUCHERDATE" headerClass="datatableheader" >
		
		<c:choose>
		<c:when test="${vo[4] == 'A'}">
		<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[6]}" var="voucherDate"/>
		<input type="text" id="txtVoucherDate${vo_rowNum}" name="txtVoucherDate" readOnly="readOnly" value="${voucherDate}" size="12" />
		
		</c:when>
		<c:otherwise>
		<input type="text" id="txtVoucherDate${vo_rowNum}" name="txtVoucherDate${vo_rowNum}" size="12" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);"/>
		<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open("txtVoucherDate${vo_rowNum}",375,570)'
								style="cursor: pointer;" />
		</c:otherwise>
		</c:choose> 
		</display:column>
		
		<display:column style="text-align: left;" class="oddcentre" titleKey="CMN.STATUS" headerClass="datatableheader" >
		<c:if test="${vo[4] == 'A'}"><c:out value="Approved"/></c:if>
		<c:if test="${vo[4] == 'P'}"><c:out value="Pending"/></c:if> 
		</display:column>				
		
		<display:column style="text-align:center" class="oddcentre" title= "<input type='checkbox' name='SelectAll' onclick='checkUncheckAll(this)'/>" headerClass="datatableheader">
		     <c:choose>
				<c:when test="${vo[4] == 'A'}"> 
		      		<input type="checkbox" name="chkRequest" id="chkRequest_${vo_rowNum}" disabled="disabled" value="${vo[0]}" />		      		
		      	</c:when>
		      	<c:otherwise>
		      		<input type="checkbox" name="chkRequest" id="chkRequest_${vo_rowNum}" value="${vo[0]}" />
		      	</c:otherwise>	
		     </c:choose>		     
		</display:column>
		
</display:table>

</fieldset>

<c:if test="${MonthlyData[0] != null}">
<table width="30%" align="center">
<br>
	<tr>
    	<td width="10%">
        	<hdiits:button type="button" captionid="BTN.APPROVE" bundle="${gpfLables}" id="btnApprove" name="btnApprove" onclick="approveSchedule();"></hdiits:button>
        </td>
        <td width="10%">
            <hdiits:button type="button" captionid="BTN.DISCARD" bundle="${gpfLables}" id="btnDiscard" name="btnDiscard" onclick="discardSchedule();"></hdiits:button>
        </td>
    </tr>
</table>
</c:if>
</hdiits:form>       