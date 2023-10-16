<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@page import="com.tcs.sgv.common.constant.Constants"%>

<script type="text/javascript"  src="script/common/common.js"></script>
<script type="text/javascript"	src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript"  src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script  type="text/javascript"  src="script/common/CalendarPopup.js"></script>
<script  type="text/javascript"  src="script/pensionpay/SavedPensionBills.js"></script>
<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="VOList" value="${resValue.lLstResult}"></c:set>
<c:set var="totalRecords" value="${resValue.totalRecords}"></c:set>

<script type="text/javascript">
var billVoucherString="";
function getBillVoucherString()
{
	var flag=0;
	var arrChkBox=document.getElementsByName("chkbxBillNo");
	billVoucherString="";
	var voucherNo="";
	if(arrChkBox!=null)
	{
		if(arrChkBox.length > 0)
		{
			for(var i=0;i<arrChkBox.length;i++)
			{
				if(arrChkBox[i].checked == true)
				{
					var rowId=arrChkBox[i].id;
					rowNum=rowId.substring(rowId.indexOf("_")+1);
					voucherNo = document.getElementById("txtVoucherNo_"+rowNum).value;
					if(flag==0)
					{
						flag=1;
						billVoucherString=arrChkBox[i].value + "*" +voucherNo;
						
					}
					else
					{
						billVoucherString=billVoucherString+"~"+arrChkBox[i].value + "*" +voucherNo;
					    
					}
					
				}
			}
		}
	}
}
function validateBillVoucherDtls()
{
	var arrChkBox= document.getElementsByName("chkbxBillNo");
	var flag=0;
	if(arrChkBox.length > 0)
	{
		for(var i=0;i<arrChkBox.length;i++)
		{
			if(arrChkBox[i].checked==true)
			{
				flag=1;
							
				var rowId=arrChkBox[i].id;
				rowNum=rowId.substring(rowId.indexOf("_")+1);
				
     			var voucherNoId="txtVoucherNo_"+rowNum;
     			
				var voucherNo=document.getElementById(voucherNoId).value;
				if(voucherNo.length == 0 )
				{
					alert("Please Enter Voucher Number.");
					document.getElementById(voucherNoId).focus();
					return false;
				}
				var voucherDate = document.getElementById("txtVoucherDate_"+rowNum).value;
				if(voucherDate.length == 0 )
				{
					alert("Please Enter Voucher Date.");
					document.getElementById("txtVoucherDate_"+rowNum).focus();
					return false;
				}
				var billNo = document.getElementById("chkbxBillNo_"+rowNum).value;
				
				billVoucherString = billVoucherString+"&billNo=" +billNo + "&txtVoucherNo_"+rowNum + "=" +voucherNo +"&txtVoucherDate_"+rowNum+"=" + voucherDate;
				
				var lArrChkNos = document.getElementsByName("txtChequeNo_"+rowNum);
     			var lChkNos = "";
     			for(var j = 0;j<lArrChkNos.length;j++)
     			{
     				lChkNos = lArrChkNos[j].value.trim();
     				if(lChkNos.length == 0)
     				{
     					alert("Please Enter Cheque Number.");
     					document.getElementById(lArrChkNos[j].id).focus();
     					return false;
     				}
     				var billPartyId = document.getElementById("hdnBillPartyId_"+rowNum+"_"+j).value;
					var chequeNo = document.getElementById("txtChequeNo_"+rowNum+"_"+j).value;
					
					billVoucherString =	billVoucherString+"&hdnBillPartyId_"+rowNum+"=" + billPartyId + "&txtChequeNo_"+ rowNum + "=" + chequeNo;
     			}
     			
			}
		}
	}

	if(flag==0)
	{
		alert("Please check atleast one bill.");
		return false;
	}
	else{
		return true;
	}
	
}
function updateBillVoucherMpgDtls()
{
	billVoucherString = "";
	if(validateBillVoucherDtls()==true)
	{
		//getBillVoucherString();
		var uri="";
		if(billVoucherString != "")
		{
			showProgressbar();
			uri = 'ifms.htm?actionFlag=updateBillVoucherMpgDtls';
			updateBillVoucherMpgUsingAjax(uri);
		}
		else
		{
		    alert("Please check atleast one bill.");	
		}
	}
}
function updateBillVoucherMpgUsingAjax(uri)
{
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters: billVoucherString,
		        onSuccess: function(myAjax) {
		        	updateBillVoucherMpgOnStateChanged(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}

function updateBillVoucherMpgOnStateChanged(myAjax)
{
	  var XMLDoc =  myAjax.responseXML.documentElement;
	   
	  var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	  
	  if(XmlHiddenValues != null && XmlHiddenValues.length > 0)
	  {
		  alert(XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue);
		  hideProgressbar();		
	  }
	  else
	  {
		  alert("Some Problem Occurred.Please Try Again");	
		  hideProgressbar();
	  }
}
var billNoString="";
function getBillNoString()
{
	var flag=0;
	var arrChkBox=document.getElementsByName("chkbxBillNo");
	billNoString="";
	
	if(arrChkBox!=null)
	{
		if(arrChkBox.length > 0)
		{
			for(var i=0;i<arrChkBox.length;i++)
			{
				if(arrChkBox[i].checked == true)
				{
					var rowId=arrChkBox[i].id;
					rowNum=rowId.substring(rowId.indexOf("_")+1);
					
					if(flag==0)
					{
						flag=1;
						billNoString=arrChkBox[i].value;
						
					}
					else
					{
						billNoString=billNoString+"~"+arrChkBox[i].value;
					    
					}
					
				}
			}
		}
	}
}
function archieveBillAfterMapping()
{
	getBillNoString();
	var uri="";
	if(billNoString != "")
	{
		
		uri = 'ifms.htm?actionFlag=archieveBillAfterVoucherMpg&billNoString='+billNoString;
		archieveBillAfterMpgUsingAjax(uri);
	}
	else
	{
	    alert("Please check atleast one bill.")	
	}
}
function archieveBillAfterMpgUsingAjax(uri)
{
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        onSuccess: function(myAjax) {
		        	archieveBillAfterMpgOnStateChanged(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}

function archieveBillAfterMpgOnStateChanged(myAjax)
{
	  var XMLDoc =  myAjax.responseXML.documentElement;
	   
	  var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	  
	  if(XmlHiddenValues != null && XmlHiddenValues.length > 0)
	  {
		  alert(XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue);
		  self.location.reload();		
	  }
	  else
	  {
		  alert("Some Problem Occurred.Please Try Again");	
		  
	  }
}
function viewArchieveBill()
{
	var currRole=document.getElementById("hdnCurrRole").value;
	var newWindow;
	var height = screen.height - 100;
	var width = screen.width;
	var url='ifms.htm?actionFlag=getDraftBills&billFlag=AR&voucherMpgFlag=Y&currRole='+currRole;
	var urlstyle = "height=500,width=1000,toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
	newWindow = window.open(url, "ArchieveBills", urlstyle);
	
}
</script>

<hdiits:form name="frmBillVoucherMpg" id="frmBillVoucherMpg" method="post" validate="">
<input type="hidden" id="hdnCurrRole" name="hdnCurrRole" value="${resValue.currRole}"/>
<input type="hidden" id="billFlag" value="MPG" name ="billFlag" />
<fieldset class="tabstyle">
<legend	id="headingMsg">
			<b><fmt:message key="PPMT.BILLVOUCHERMPG" bundle="${pensionLabels}"></fmt:message></b>
</legend>
<div style="width:100%;overflow:auto;height:100%;" >
<display:table list="${VOList}" id="vo" requestURI="" pagesize="<%= Constants.PAGE_SIZE %>"  export="false" style="width:100%" partialList="true" 
						 offset="1" excludedParams="ajax" size="${totalRecords}" sort="external" defaultsort="3" defaultorder="descending" cellpadding="5" >	
	<display:column title="<input name='chkSelect'type='checkbox' onclick='checkUncheckAll(this)'/>"
			headerClass="datatableheader" style="width:2%" >
			<input type="checkbox" style="text-align:center" name="chkbxBillNo"
				id="chkbxBillNo_${vo_rowNum}"
				onclick="" value="${vo.billNo}_${vo_rowNum}" />
	</display:column>	
	<display:column titleKey="PPMT.BILLNO" headerClass="datatableheader"
			sortable="false" style="width:20%;text-align:left" >
		   <a href="#" onclick="javascript:showPensionBills('${vo.billNo}','${vo.subjectId}','${vo.ppoNo}');">${vo.billCntrlNo}</a>
	</display:column>
	<display:column titleKey="PPMT.PARTYNAME" headerClass="datatableheader"
			sortable="false" style="width:40%;text-align:left" >
			<c:forEach var="PartyName" items="${vo.partyNames}" varStatus="par">
				<c:out value="${PartyName}" /><br><br>
			</c:forEach>
	</display:column>
	<display:column titleKey="PPMT.VOUCHERNO" headerClass="datatableheader"
			sortable="false" style="width:10%;text-align:left" >
			<input type="text" name="txtVoucherNo_${vo_rowNum}" id="txtVoucherNo_${vo_rowNum}" value="${vo.voucherNo}" onkeypress="digitFormat(this);" maxlength="10"/>
	</display:column>

	<display:column titleKey="PPMT.VOUCHERDATE" headerClass="datatableheader"
			sortable="false" style="width:20%;text-align:center"  >
			<fmt:formatDate var="voucherDate" dateStyle="full" pattern="dd/MM/yyyy" value="${vo.voucherDate}"/>	
			<input type="text" name="txtVoucherDate" id="txtVoucherDate_${vo_rowNum}" value="${voucherDate}" onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);" maxlength="10" size="9"/>
			<img src='images/CalendarImages/ico-calendar.gif'
						        onClick='window_open(event,"txtVoucherDate_${vo_rowNum}",375,570)'style="cursor: pointer;" />	
	</display:column>
	<display:column titleKey="PPMT.VOUCHERAMT" headerClass="datatableheader"
			sortable="false" style="width:8%;text-align:right"  >
			${vo.billNetAmt}
			
	</display:column>
	<display:column titleKey="PPMT.CHEQUENO" headerClass="datatableheader"
			sortable="false" style="width:10%;text-align:left"  >
			<c:forEach var="chequeNos" items="${vo.chequeNos}" varStatus="pn">
						<input type="text" value="${chequeNos}" name="txtChequeNo_${vo_rowNum}" id="txtChequeNo_${vo_rowNum}_${pn.index}" onkeypress="digitFormat(this);" maxlength="19" /><br><br>
						<!--<input type="text" name="txtChequeNo" id="txtChequeNo_${vo_rowNum}" value="${vo.chequeNo}" onkeypress="digitFormat(this);" maxlength="19"/> -->
						<c:set value="${pn.index}" var="size"/>
			</c:forEach>
			<c:forEach var="billPartyId" items="${vo.billPartyIds}" varStatus="bp">
						<input type="hidden" value="${billPartyId}" name="hdnBillPartyId_${vo_rowNum}" id="hdnBillPartyId_${vo_rowNum}_${bp.index}" onkeypress="digitFormat(this);" maxlength="19" />
						<c:set value="${bp.index}" var="size"/>
			</c:forEach>
	</display:column>
	<display:column titleKey="PPMT.CHEQUEAMOUNT" headerClass="datatableheader"
			sortable="false" style="width:8%;text-align:right"  >
			<c:forEach var="chequeAmount" items="${vo.chequeAmounts}" varStatus="pn">
				<c:out value="${chequeAmount}" /><br><br>
			</c:forEach>
	</display:column>
</display:table>
</div>
</fieldset>
<div style="width:100%;overflow:auto;height:100%;">
<table width="90%" align="center" id="tblReceivedBtn">
	<tr>
		<td  align="center" width="100%">
			<hdiits:button type="button" captionid="PPMT.VIEWARCHIEVERED" bundle="${pensionLabels}" id="btnViewArchieveRecd" name="btnViewArchieveRecd" onclick="viewArchieveBill();" classcss="bigbutton"/>
			<hdiits:button type="button" captionid="PPMT.UPDATE" bundle="${pensionLabels}" id="btnUpdate" name="btnUpdate" onclick="updateBillVoucherMpgDtls();" />
			<hdiits:button type="button" captionid="PPMT.ARCHIEVE" bundle="${pensionLabels}" id="btnArchieve" name="btnArchieve" onclick="archieveBillAfterMapping();" />
			<hdiits:button id="btnClose" name="btnClose" type="button" captionid="PPMT.CLOSE" bundle="${pensionLabels}" onclick="winCls();" />
		</td>
	</tr>
</table>
</div>
</hdiits:form>