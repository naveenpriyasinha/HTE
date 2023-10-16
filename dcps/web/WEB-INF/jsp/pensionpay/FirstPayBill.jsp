
<%try { %>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.tcs.sgv.common.constant.Constants"%>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>
<script type="text/javascript"  src="script/pensionpay/Common.js"></script>
<script type="text/javascript" src="script/pensionpay/PensionPayWorkList.js"></script>
<script type="text/javascript">
function showPensinCase(url)
{
	var newWindow;
	var height = screen.height - 100;
	var width = screen.width;
	var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
	newWindow = window.open(url, "frmPensionCase", urlstyle);
}
</script>
<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="curRole" value="${resValue.lStrToRole}"/>
<c:set var="totalRecords" value="${resValue.totalRecords}"></c:set>
<c:set var="VOList" value="${resValue.lLstSavedCaseVO}" />
<c:set var="showCaseFor" value="${resValue.showCaseFor}" />
<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels"
	var="pensionLabels" scope="request" />

<hdiits:form name="FirstPay" id="FirstPay" method="POST" action=""
	encType="multipart/form-data" validate="true">
<jsp:include page="/WEB-INF/jsp/pensionpay/searchPensionCase.jsp" />	
	<fieldset style="width: 100%" class="tabstyle"><legend
		id="headingMsg"> <b><fmt:message key="PPMT.FRSTPAYBILL"
		bundle="${pensionLabels}"></fmt:message></b> </legend> 

<div  class="scrollablediv" >
<display:table list="${VOList}" id="vo" requestURI="" pagesize="<%= Constants.PAGE_SIZE %>"  export="false" style="width:100%" partialList="true" 
						 offset="1" excludedParams="ajax" size="${totalRecords}" sort="external" defaultsort="3" defaultorder="descending" cellpadding="5" >	
		<display:column title="<input name='chkSelect'type='checkbox' onclick='checkUncheckAll(this)'/>"
			headerClass="datatableheader" style="width:2%">
			<input type="checkbox" align="middle" name="chkbxPesnionerNo"
				id="chkbxPesnionerNo_${vo_rowNum}"
				onclick="" value="${vo.ppoNo}_${vo_rowNum}" />
			<input type="hidden" name="hdnPPONo${vo_rowNum}" id="hdnPPONo${vo_rowNum}" value="${vo.ppoNo}"/>
			<input type="hidden" name="hdnPensionerId${vo_rowNum}" id="hdnPensionerId${vo_rowNum}" value="${vo.pensionerId}"/>
			<input type="hidden" name="hdnPnsnrDtlsId${vo_rowNum}" id="hdnPnsnrDtlsId${vo_rowNum}" size="30" value="${vo.pensionerDtlsId}">
			<input type="hidden" name="hdnpnsnrqstid${vo_rowNum}" id="hdnpnsnrqstid${vo_rowNum}" value="${vo.pensionRequestId}" >
		</display:column>
		<display:column titleKey="PPMT.PPONO" headerClass="datatableheader"
			sortable="true" style="width:10%">
			<c:set var="URLLink" value="ifms.htm?actionFlag=loadPhyPenCase&pensionerId=${vo.pensionerId}&showReadOnly=Y&showCaseFor=${showCaseFor}"></c:set>
			<a href="javascript:void(0)" onclick="javascript:showPensinCase('${URLLink}');">
			<div id="lblPpoNo${vo_rowNum}">${vo.ppoNo}</div></a>
		</display:column>
		<display:column titleKey="PPMT.PENSIONERNAME" headerClass="datatableheader"
			sortable="true" style="width:20%;text-align:left" >
			<c:choose>
			<c:when test="${vo.deathDate != null}">
			<label id="lblPnsrName${vo_rowNum}" name="lblPnsrName${vo_rowNum}">${vo.familyMemName}</label>
			</c:when>
			<c:otherwise>
			<label id="lblPnsrName${vo_rowNum}" name="lblPnsrName${vo_rowNum}">${vo.pnsrName}</label>
			</c:otherwise>
			</c:choose>
			</display:column>				
		<display:column titleKey="PPMT.DOR" headerClass="datatableheader" 
			sortable="true" style="width:10%;text-align:center" ><fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo.retirementDate}"/>
		</display:column>
		<display:column titleKey="PPMT.PSD" headerClass="datatableheader"
			sortable="true" style="width:10%;text-align:center" ><fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo.commencementDate}"/>
		</display:column>
		<display:column titleKey="PPMT.BANKNAME" headerClass="datatableheader"	sortable="true" style="width:20%;text-align:left;" >
			
					 	<label name="txtBankName${vo_rowNum}" id="txtBankName${vo_rowNum}">${vo.bankName}</label>
		</display:column>
		<display:column titleKey="PPMT.BANKBRNNAME" headerClass="datatableheader"	sortable="true" style="width:20%;text-align:left;" >
											
					 	<label name="txtBranchName${vo_rowNum}" id="txtBranchName${vo_rowNum}">${vo.branchName}</label>
		</display:column>
		<display:column titleKey="PPMT.ACCOUNTNO" headerClass="datatableheader"	 style="width:20%;text-align:left;" >
				 	<label name="txtAccountNo${vo_rowNum}" id="txtAccountNo${vo_rowNum}">${vo.accountNo}</label>
		</display:column>
	</display:table>
	<input type="hidden" id="selectedPpoNo" name="selectedPpoNo"/>
	<input type="hidden" id="pnsnBillGenFor" name="pnsnBillGenFor"  />
</div>
</fieldset>
	<br/>
	<input type="hidden" name="hidshowCaseFor"id="hidshowCaseFor" value="${resValue.showCaseFor}"/>
	<div style="text-align:center;">

		<hdiits:button name="btnPension" type="button"
			captionid="PPMT.PENSION" bundle="${pensionLabels}" onclick="generateBill('PENSION');"/>

		<hdiits:button name="btnDCRG" type="button" captionid="PPMT.DCRG"
			bundle="${pensionLabels}"  onclick="generateBill('DCRG');"/>

		<hdiits:button name="btnCVP" type="button" captionid="PPMT.CVP"
			classcss="bigbutton" bundle="${pensionLabels}" onclick="generateBill('CVP');"/>

		<hdiits:button name="btnMovetoRecordRoom" classcss="bigbutton"
			type="button" captionid="PPMT.MOVETORECORDROOM"
			bundle="${pensionLabels}" onclick="generateBill('RecordRoom');" />

		<hdiits:button name="btnClose" type="button" captionid="PPMT.CLOSE"
			bundle="${pensionLabels}" onclick="winCls();" />
	</div>
	<script type="text/javascript">
		var showCaseFor = '${resValue.showCaseFor}';
	</script>
	<script>
	function generateBill(lStr)
	{
		showProgressbar();
		gRowNumList = "";
		getPensionerDtlId();
		if(gRowNumList != null && gRowNumList.length>0)
		{
			var lArrRowNums = gRowNumList.split("~");
			if(lArrRowNums.length == 1)
			{
				
				var lPPONo = document.getElementById("hdnPPONo"+lArrRowNums[0]).value;
				document.getElementById("selectedPpoNo").value = lPPONo;
				//isBillCreated(lStr,lPPONo);
				isBillCreatedUsingAJAX(lStr,lPPONo);
			}
			else
			{
				hideProgressbar();
				alert("Please select one case at a time");
				
			}
		}
		else{
			hideProgressbar();
			alert("Please select any one case.");
		}
	}
	
	var xmlHttp = null;
	function isBillCreatedUsingAJAX(lStr,ppoNo)
	{
		if(lStr == 'RecordRoom')
		{
			url = 'ifms.htm?actionFlag=generateBillCase&BillType=PENSION&ppoNo='+ppoNo;  
		}
		else
		{
			url = 'ifms.htm?actionFlag=generateBillCase&BillType='+lStr+'&ppoNo='+ppoNo;  
		}
		var myAjax = new Ajax.Request(url,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters: "&BillType="+lStr+"&ppoNo="+ppoNo,
			        onSuccess: function(myAjax) {
								isBillCreatedStateChangedUsingAJAX(myAjax,lStr);
					},
			        onFailure: function(){ alert('Something went wrong...')} 
			          } );
	}
	function isBillCreatedStateChangedUsingAJAX(myAjax,lStr)
	{
		
		XMLDoc =  myAjax.responseXML.documentElement;
	
		if(XMLDoc != null)
		{
			var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
			var billPaid=XMLDoc.getElementsByTagName('Paid');
			var billType = XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue;
			var ppoNo = XmlHiddenValues[0].childNodes[1].childNodes[0].nodeValue;

			//For Bill genertion Flag
			if(billPaid[0]!=null)
			{
				alert(billPaid[0].childNodes[0].nodeValue);
				hideProgressbar();
				return false;
			}
				
			if( XmlHiddenValues[0].childNodes.length > 2)
			{
				
				if(lStr == 'RecordRoom')
				{
					showVoucherDetails();
				//	forwardCaseUsingAjax(showCaseFor);
				}else
				{
					alert(XmlHiddenValues[0].childNodes[2].childNodes[0].nodeValue); // Bill is already created
				}
				hideProgressbar();
				return false;
			}else if(lStr == 'RecordRoom' && XmlHiddenValues[0].childNodes.length <= 2){
				alert("Case cannot be moved to Library as First Pension Bill is not generated for the case.");
				hideProgressbar();
				return false;
			}
			else
			{
				//alert('in else');
				hideProgressbar();
				if(billType == 'PENSION')
				{
					showPensionBillMonthPopup(ppoNo);
				}
				else
				{
					openBillDtls(billType,ppoNo);
				}
			}
		}
	}
	function isBillCreated(lStr,ppoNo)
	{
		xmlHttp = createXMLHttpRequest();
		
		if (xmlHttp==null)
		{
			alert ("Your browser does not support AJAX!");
			return;
		} 
		
		url = 'ifms.htm?actionFlag=generateBillCase&BillType='+lStr+'&ppoNo='+ppoNo;  
		url = url+'&CVPAmt=1000&CVPFlag=Y';
		  
		xmlHttp.onreadystatechange=isBillCreatedStateChanged;
		xmlHttp.open("POST",url,false);
		xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xmlHttp.send(url);
	}
	
	function isBillCreatedStateChanged() 
	{ 
		if (xmlHttp.readyState==4)
		{ 
			if(xmlHttp.status==200)
			{
				XMLDoc = xmlHttp.responseXML.documentElement;
			
				if(XMLDoc != null)
				{
					var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
					var billType = XmlHiddenValues[0].childNodes[0].text;
					var ppoNo = XmlHiddenValues[0].childNodes[1].text;
					if( XmlHiddenValues[0].childNodes[2] != null)
					{
						alert(XmlHiddenValues[0].childNodes[2].text); // Bill is already created
						hideProgressbar();
						return false;
					}
					else
					{
						//alert('in else');
						hideProgressbar();
					}
				}
			}
			else
			{
				alert('Some problem occured during save.');
				hideProgressbar();
			}
		}
	}
	
	function openBillDtls(billType,ppoNo)
	{
		//alert('1');
		var subId;
		if(billType == 'CVP')
		{
			subId = 11;
		}
		else if (billType == 'DCRG')
		{
			subId= 10;
		}
		else if(billType == 'PENSION')
		{
			subId = 9;
		}
		var height = screen.height - 100;
		var width = screen.width;
		var pnsnBillGenFor = document.getElementById("pnsnBillGenFor").value;
		var urlstring = 'ifms.htm?actionFlag=createPensionSpecificBills&subjectId='+subId+'&PPONo='+ppoNo+'&isNewSavingBill=Y&pnsnBillGenFor='+pnsnBillGenFor;
		var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
		//alert(urlstyle);
		window.open(urlstring,"VoucherDetails", urlstyle);
	}

	function showVoucherDetails()
	{
		showProgressbar();
		gRowNumList = "";
		getPensionerDtlId();
		if(gRowNumList != null && gRowNumList.length>0)
		{
			var lArrRowNums = gRowNumList.split("~");
			if(lArrRowNums.length == 1)
			{
				var lPPONo = document.getElementById("hdnPPONo"+lArrRowNums[0]).value;
				
				showVoucherDetailsUsingAJAX(lPPONo);
			}else
			{
				hideProgressbar();
				alert("Please select one case at a time");
				
			}
		}
		else{
			hideProgressbar();
			alert("Please select any one case.");
		}
	}
	function showVoucherDetailsUsingAJAX(ppoNo)
	{
		//var height = screen.height - 100;
		//var width = screen.width;
		var lArrRowNums = gRowNumList.split("~");
		
		var lpnsnRqstId = document.getElementById("hdnpnsnrqstid"+lArrRowNums[0]).value;
		var lpensionerDtlId=document.getElementById("hdnPnsnrDtlsId"+lArrRowNums[0]).value;
		var lpensionerCode=document.getElementById("hdnPensionerId"+lArrRowNums[0]).value;
		var urlstring = 'ifms.htm?actionFlag=showVoucherDetails&ppoNo='+ppoNo+'&pnsnRqstId='+lpnsnRqstId+'&pensionerDtlId='+lpensionerDtlId+'&pensionerCode='+lpensionerCode;
		var urlstyle = "height=250,width=500,toolbar=no,minimize=no,status=yes,memubar=no,location=no,scrollbars=yes,top=200,left=400";
		//alert(urlstring );
		//var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
		//alert(urlstyle);
		window.open(urlstring,"VoucherDetails", urlstyle);
		
		//window.open(uri, "open", urlstyle);
	}
	
	</script>


<!-- Script for Bill Opening -->
<script>


function showPensionBills(BillNo,SubId,PpoNo)
{
	var newWindow;
    var height = screen.height - 100;
    var width = screen.width;
    
    var urlstring = "ifms.htm?actionFlag=getPensionBillsData&billNo=" + BillNo + "&billStatus=1&subId=" + SubId + "&ppoNo=" + PpoNo;
   	var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
   	window.open(urlstring, "frmViewOnlineBill", urlstyle);
}


function showPensionBillMonthPopup(ppoNo)
{
	var newWindow;
    var height = 150;
    var width = 500;
    var posY = screen.height/2 - height/2;
    var posX = screen.width/2 - width/2;
    var urlstring = "ifms.htm?viewName=pensionBillPopup";
   	var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top="+posY+",left="+posX;
   	newWindow = window.open(urlstring, "GeneratePensionBillFor", urlstyle);
   	if(newWindow != null)
   	{
   		newWindow.document.getElementById("hidPpoNo").value = ppoNo;
  	}   	
}
</script>

</hdiits:form>
<% } catch(Exception e){
e.printStackTrace();
}%>