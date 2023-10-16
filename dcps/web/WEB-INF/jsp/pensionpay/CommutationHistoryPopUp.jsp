<%@ page language="java" %>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request" />
<fmt:setBundle basename="resources.pensionpay.PensionCaseConstants" var="pensionConstants" scope="request" />

<fmt:setBundle basename="resources.pensionpay.PensionPayAlerts_en_US" var="pensionAlerts" scope="request" />

<script  type="text/javascript"  src="script/common/CalendarPopup.js"></script>
<script type="text/javascript"  src="script/pensionpay/HeaderFields.js"></script>
<script type="text/javascript"  src="script/pensionpay/PaymentTab.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>
<script type="text/javascript"	src="script/common/IFMSCommonFunctions.js"></script>


<script>
var cvpDtlsId=new Array();
function getCvpDtlsId(object)
{
	var elementId = object.id;
	var rowCnt = elementId.substring(5);
	var count = cvpDtlsId.length;
	
	cvpDtlsId[count] =  document.getElementById("hdnCvpDtlsId"+rowCnt).value;
	count++;
}

function saveCommutationHistory()
{
    var uri;
        
    uri = 'ifms.htm?actionFlag=deleteCommutationHistory&hstCvpDtlsId='+cvpDtlsId.join('~');
    saveCommutationHistoryUsingAjax(uri);
    
}

function saveCommutationHistoryUsingAjax(uri)
{
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        onSuccess: function(myAjax) {
					saveComtnHistoryOnStateChanged(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}
function saveComtnHistoryOnStateChanged(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
    
    var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
    
    alert(XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue);
    
}

</script>

<c:set var="resultObj" value="${result}" />
<c:set var="resValue" value="${resultObj.resultValue}" />

<fieldset class="tabstyle" style="width:100%"><legend> <b>
	<fmt:message key="PPMT.COMHISTORY" bundle="${pensionLabels}"></fmt:message></b> </legend>	
<div style="float: inherit; border: 1px solid #000000; background-color: transparent; width: 85%; height: 200px; overflow: scroll; overflow-x: scroll; overflow-y: scroll;">
 <input type="hidden" name="hdnCvpHstGridSize" id="hdnCvpHstGridSize" value="0">

<table width="90%" id="tblCvpHistory" border="1">
	     <tr class="datatableheader"> 
			    		<td width="15%" class="datatableheader"><fmt:message key="PPMT.CVPORDERNO" bundle="${pensionLabels}" ></fmt:message></td>	
			    		<td width="15%" class="datatableheader"><fmt:message key="PPMT.CVPPAIDDATE" bundle="${pensionLabels}"></fmt:message></td>
			    		<!-- <td width="15%" class="datatableheader"><fmt:message key="PPMT.TOTALCVPAMOUNT" bundle="${pensionLabels}" ></fmt:message></td> -->
			    		<td width="15%" class="datatableheader"><fmt:message key="PPMT.PAYPAYEBLEAMNT" bundle="${pensionLabels}" ></fmt:message></td>	
			    		<td width="15%" class="datatableheader"><fmt:message key="PPMT.VOUCHERNO" bundle="${pensionLabels}"></fmt:message></td>
						<td width="15%" class="datatableheader"><fmt:message key="PPMT.VOUCHERDATE" bundle="${pensionLabels}"></fmt:message></td>
						<td width="10%" class="datatableheader"><fmt:message key="PPMT.DELETE" bundle="${pensionLabels}"></fmt:message></td>
		 </tr>
		 <c:choose>

		<c:when test="${resValue.lLstHstCommutationDtlsVO !=null}">

			<c:forEach var="hstCommutationDtlsVO" items="${resValue.lLstHstCommutationDtlsVO}" varStatus="Counter">
				<tr>
					
					<td class="tds" align="left">
					<input type="hidden" name="hdnCvpDtlsId" id="hdnCvpDtlsId${Counter.index}" value="${hstCommutationDtlsVO.cvpDtlsId}"/>
					${hstCommutationDtlsVO.orderNo}
					</td>
				    <td class="tds" align="center">
						<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${hstCommutationDtlsVO.orderDate}" />	
					</td>
					<!-- 
					<td class="tds" align="right">
					${hstCommutationDtlsVO.totalOrderAmount}	
					</td> -->
					<td class="tds" align="right">
					${hstCommutationDtlsVO.paymentAmount}
					</td>
					<td class="tds" align="left">
					${hstCommutationDtlsVO.voucherNo}
					</td>
					<td class="tds" align="center">
					<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${hstCommutationDtlsVO.voucherDate}" />
	       				
					</td>
					<td class="tds" align="center">
					
					<img name="Image"
						id="Image${Counter.index}"
						src="images/CalendarImages/DeleteIcon.gif"
						onclick="getCvpDtlsId(this);RemoveTableRow(this, 'tblCvpHistory');"/>
					</td>
				</tr>
				<c:if test="${hstCommutationDtlsVO.billNo != null}">
				<script>
				   document.getElementById("Image${Counter.index}").disabled=true;
				</script>
				</c:if>
				<script>
				    
					document.getElementById("hdnCvpHstGridSize").value = Number('${Counter.index}') + 1;
					
				</script>
					
		    	</c:forEach>
			</c:when>
		</c:choose>		
</table>
</div>
</fieldset>
<table align="center">
<tr>
<td>
<hdiits:button name="btnSave"	type="button" captionid="PPMT.SAVE" bundle="${pensionLabels}" onclick="saveCommutationHistory();" />&nbsp;&nbsp;&nbsp;
<hdiits:button id="btnClose" name="btnClose" type="button" captionid="PPMT.CLOSE" bundle="${pensionLabels}" onclick="winCls();" />&nbsp;&nbsp;&nbsp;
</td>
</tr>
