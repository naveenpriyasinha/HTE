<%@ page language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript"	src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script  type="text/javascript"  src="script/common/CalendarPopup.js"></script>
<script type="text/javascript"	src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>
<script type="text/javascript" src="script/pensionpay/HeaderFields.js"></script>
<script type="text/javascript"	src="script/pensionpay/RevisedCaseProcess.js"></script>

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request" />

<script type="text/javascript">
LISTROPTYPE='';
</script>

<script>
LISTROPTYPE="<option value='<fmt:message key='PPMT.1986' bundle='${pensionLabels}'/>'><fmt:message key='PPMT.1986' bundle='${pensionLabels}'/></option>"
	        +"<option value='<fmt:message key='PPMT.1996' bundle='${pensionLabels}'/>'><fmt:message key='PPMT.1996' bundle='${pensionLabels}'/></option>"
		    +"<option value='<fmt:message key='PPMT.2006' bundle='${pensionLabels}'/>'><fmt:message key='PPMT.2006' bundle='${pensionLabels}'/></option>";
</script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<hdiits:form name="RevisedCaseForm" id="RevisedCaseForm"	method="POST" action="" encType="multipart/form-data" validate="true">

<fieldset class="tabstyle" style="width:100%"><legend> <b>
	<fmt:message key="PPMT.RVSDCASEPROC" bundle="${pensionLabels}"></fmt:message></b> </legend>	
<table width="80%">
    <tr>
		<td width="15%">
	       <fmt:message key="PPMT.PPONO" bundle="${pensionLabels}"></fmt:message>
	    </td>
		 <td width="35%">
		      <input type="text" id="txtPpoNumber" name="txtPpoNumber" value="${resValue.PpoNo}" disabled="disabled"/>
		      <input type="hidden" id="hdnPnsnrCode" name="hdnPnsnrCode" value="${resValue.PensionerCode}"/>	
		      <label id="mandtryFinal" class="mandatoryindicator">*</label>      
		 </td>
		
	    <td width="15%">
	       <fmt:message key="PPMT.PENSIONERNAME" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	        <input type="text" id="txtPensionerName" name="txtPensionerName" value="${resValue.PensionerName}" disabled="disabled"/>
	        <label id="mandtryFinal" class="mandatoryindicator">*</label>
		 </td>		 		      
	</tr>
</table>	

<fieldset class="tabstyle" style="width:100%"><legend> <b>
	<fmt:message key="PPMT.REVISION" bundle="${pensionLabels}"></fmt:message></b> </legend>
<input type="hidden" id="hdnRevGridSize" name="hdnRevGridSize" value="0"/>	
<hdiits:button name="RevisionAddRow" type="button"  captionid="PPMT.ADDROW" bundle="${pensionLabels}" onclick="revisionDtlsTableAddRow();"  />
<div style="float: inherit; border: 1px solid #000000; background-color: transparent; width: 100%; height: 120px; overflow: scroll; overflow-x: scroll; overflow-y: scroll;">
 <table id="tblRevisionDtls" align="left" width="100%" cellspacing="0" border="1">	
	      <tr class="datatableheader"> 
	            		   <td width="10%" class="pensionLabels"><fmt:message key="PPMT.ROPTYPE" bundle="${pensionLabels}" ></fmt:message></td>	
						   <td width="15%" class="pensionLabels"><fmt:message key="PPMT.REVDATE" bundle="${pensionLabels}"></fmt:message></td>
						   <td width="10%" class="pensionLabels"><fmt:message key="PPMT.PNSNSANCTN" bundle="${pensionLabels}"></fmt:message></td>
						   <td width="10%" class="pensionLabels"><fmt:message key="PPMT.CVP" bundle="${pensionLabels}"></fmt:message></td>
						   <td width="10%" class="pensionLabels"><fmt:message key="PPMT.CVPMONTHLY" bundle="${pensionLabels}"></fmt:message></td>
						   <td width="10%" class="pensionLabels"><fmt:message key="PPMT.FP1AMOUNT" bundle="${pensionLabels}"></fmt:message></td>
						   <td width="10%" class="pensionLabels"><fmt:message key="PPMT.FP2AMOUNT" bundle="${pensionLabels}"></fmt:message></td>
						   <td width="10%" class="pensionLabels"><fmt:message key="PPMT.DCRGAMOUNT" bundle="${pensionLabels}"></fmt:message></td>
						   <td width="3%" class="pensionLabels"><fmt:message key="PPMT.DELETE" bundle="${pensionLabels}"></fmt:message></td>						   						   
		  </tr>					    
		<c:if test="${resValue.TotalCount == 0}">
			  
		  <tr>
		  <td class="tds" align="center">
		   <select name="cmbRopType" id="cmbRopType0" disabled="disabled">
		   <option value="-1">--Select--</option>
		   <c:choose>
		    	   <c:when test="${resValue.RopType == '1986'}">
			    		<option value="<fmt:message key="PPMT.1986" bundle="${pensionLabels}"/>" selected="selected"><fmt:message key="PPMT.1986" bundle="${pensionLabels}"/></option>
			   			<option value="<fmt:message key="PPMT.1996" bundle="${pensionLabels}"/>"><fmt:message key="PPMT.1996" bundle="${pensionLabels}"/></option>
			    		<option value="<fmt:message key="PPMT.2006" bundle="${pensionLabels}"/>"><fmt:message key="PPMT.2006" bundle="${pensionLabels}"/></option>
			    	</c:when>
			    	<c:when test="${resValue.RopType == '1996'}">
			    		<option value="<fmt:message key="PPMT.1986" bundle="${pensionLabels}"/>"><fmt:message key="PPMT.1986" bundle="${pensionLabels}"/></option>
			   			<option value="<fmt:message key="PPMT.1996" bundle="${pensionLabels}"/>" selected="selected"><fmt:message key="PPMT.1996" bundle="${pensionLabels}"/></option>
			    		<option value="<fmt:message key="PPMT.2006" bundle="${pensionLabels}"/>"><fmt:message key="PPMT.2006" bundle="${pensionLabels}"/></option>
			    	</c:when>
			    	<c:when test="${resValue.RopType == '2006'}">
			    		<option value="<fmt:message key="PPMT.1986" bundle="${pensionLabels}"/>"><fmt:message key="PPMT.1986" bundle="${pensionLabels}"/></option>
			   			<option value="<fmt:message key="PPMT.1996" bundle="${pensionLabels}"/>"><fmt:message key="PPMT.1996" bundle="${pensionLabels}"/></option>
			    		<option value="<fmt:message key="PPMT.2006" bundle="${pensionLabels}"/>" selected="selected"><fmt:message key="PPMT.2006" bundle="${pensionLabels}"/></option>
			    	</c:when>
			    	<c:otherwise>
			    		<option value="<fmt:message key="PPMT.1986" bundle="${pensionLabels}"/>"><fmt:message key="PPMT.1986" bundle="${pensionLabels}"/></option>
			   			<option value="<fmt:message key="PPMT.1996" bundle="${pensionLabels}"/>"><fmt:message key="PPMT.1996" bundle="${pensionLabels}"/></option>
			    		<option value="<fmt:message key="PPMT.2006" bundle="${pensionLabels}"/>"><fmt:message key="PPMT.2006" bundle="${pensionLabels}"/></option>
			        </c:otherwise>
			</c:choose>
		   </select>
		   <input type="hidden" name="hdnRevisionFlag" id="hdnRevisionFlag0"/>
		   </td>
		   <td class="tds" align="center">
		   <input type="text" name="txtRevisionDate" id="txtRevisionDate0"  value="${resValue.PpoInwardDate}" style="width:90px"
	         onkeypress="digitFormat(this);dateFormat(this);" maxlength="10"
					onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);" disabled="disabled"/>
	       
		   </td>
		   <td class="tds" align="center">
		   <input type="text" name="txtPensionSanctionAmt" id="txtPensionSanctionAmt0" value="${resValue.PnsnSanctionAmt}" disabled="disabled" size="15" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"   style="text-align: right"/>
		   </td>
		   <td class="tds" align="center">
		   <input type="text" name="txtCvpAmount" id="txtCvpAmount0" value="${resValue.CommutationAmt}" disabled="disabled" size="15" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"   style="text-align: right"/>
		   </td>
		   <td class="tds" align="center">
		   <input type="text" name="txtCvpMonthlyAmount" id="txtCvpMonthlyAmount0" value="${resValue.CvpMonthlyAmt}" disabled="disabled" size="15" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>
		   </td>
		   <td class="tds" align="center">
		   <input type="text" name="txtFp1Amount" id="txtFp1Amount0" value="${resValue.Fp1Amount}" disabled="disabled" size="15" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>
		   </td>
		   <td class="tds" align="center">
		   <input type="text" name="txtFp2Amount" id="txtFp2Amount0" value="${resValue.Fp2Amount}" disabled="disabled" size="15" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>
		   </td>
		   <td class="tds" align="center">
		   <input type="text" name="txtDcrgAmount" id="txtDcrgAmount0" value="${resValue.DcrgAmount}" disabled="disabled" size="15" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>
		   </td>
		   <td>
		   </td>
		   </tr>
		   <script>
		   document.getElementById("hdnRevGridSize").value=Number(document.getElementById("hdnRevGridSize").value)+1;
		   </script>
		 </c:if>	 
		 <c:if test="${resValue.TotalCount gt 0}">
		<c:forEach var="trnPnsnrRevisionDtls"	items="${resValue.TrnPensionerRevisionDtls}" varStatus="Counter">
		<tr>
		  <td class="tds" align="center">
		   <select name="cmbRopType" id="cmbRopType${Counter.index}">
		   <option value="-1">--Select--</option>
		   <c:choose>
		    	   <c:when test="${trnPnsnrRevisionDtls.ropType == '1986'}">
			    		<option value="<fmt:message key="PPMT.1986" bundle="${pensionLabels}"/>" selected="selected"><fmt:message key="PPMT.1986" bundle="${pensionLabels}"/></option>
			   			<option value="<fmt:message key="PPMT.1996" bundle="${pensionLabels}"/>"><fmt:message key="PPMT.1996" bundle="${pensionLabels}"/></option>
			    		<option value="<fmt:message key="PPMT.2006" bundle="${pensionLabels}"/>"><fmt:message key="PPMT.2006" bundle="${pensionLabels}"/></option>
			    	</c:when>
			    	<c:when test="${trnPnsnrRevisionDtls.ropType == '1996'}">
			    		<option value="<fmt:message key="PPMT.1986" bundle="${pensionLabels}"/>"><fmt:message key="PPMT.1986" bundle="${pensionLabels}"/></option>
			    		<option value="<fmt:message key="PPMT.1996" bundle="${pensionLabels}"/>" selected="selected"><fmt:message key="PPMT.1996" bundle="${pensionLabels}"/></option>
			    		<option value="<fmt:message key="PPMT.2006" bundle="${pensionLabels}"/>"><fmt:message key="PPMT.2006" bundle="${pensionLabels}"/></option>
			    	</c:when>
			    	<c:when test="${trnPnsnrRevisionDtls.ropType == '2006'}">
			    	    <option value="<fmt:message key="PPMT.1986" bundle="${pensionLabels}"/>"><fmt:message key="PPMT.1986" bundle="${pensionLabels}"/></option>
			    		<option value="<fmt:message key="PPMT.1996" bundle="${pensionLabels}"/>"><fmt:message key="PPMT.1996" bundle="${pensionLabels}"/></option>
			    		<option value="<fmt:message key="PPMT.2006" bundle="${pensionLabels}"/>" selected="selected"><fmt:message key="PPMT.2006" bundle="${pensionLabels}"/></option>
			    	</c:when>
			    	<c:otherwise>
			    	    <option value="<fmt:message key="PPMT.1986" bundle="${pensionLabels}"/>"><fmt:message key="PPMT.1986" bundle="${pensionLabels}"/></option>
			    		<option value="<fmt:message key="PPMT.1996" bundle="${pensionLabels}"/>"><fmt:message key="PPMT.1996" bundle="${pensionLabels}"/></option>
			    		<option value="<fmt:message key="PPMT.2006" bundle="${pensionLabels}"/>"><fmt:message key="PPMT.2006" bundle="${pensionLabels}"/></option>
			        </c:otherwise>
			</c:choose>
		   </select>
		   <input type="hidden" name="hdnRevisionFlag" id="hdnRevisionFlag${Counter.index}" value="${trnPnsnrRevisionDtls.revisionFlag}"/>
		   </td>
		   <td class="tds" align="center">
		   <input type="text" name="txtRevisionDate" id="txtRevisionDate${Counter.index}"  value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${trnPnsnrRevisionDtls.rivisionDate}"/>" style="width:90px"
	         onkeypress="digitFormat(this);dateFormat(this);" maxlength="10"
					onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);" />
			
			<img id="imgReceivedDate${Counter.index}" src='images/CalendarImages/ico-calendar.gif'
						style="width: 16px"
						onClick='window_open("txtRevisionDate${Counter.index}",375,570)'
						style="cursor: pointer;" />
			
	       
		   </td>
		   <td class="tds" align="center">
		   <input type="text" name="txtPensionSanctionAmt" id="txtPensionSanctionAmt${Counter.index}" value="${trnPnsnrRevisionDtls.basicPension}" size="15" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>
		   </td>
		   <td class="tds" align="center">
		   <input type="text" name="txtCvpAmount" id="txtCvpAmount${Counter.index}" value="${trnPnsnrRevisionDtls.cvpAmount}" size="15" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>
		   </td>
		   <td class="tds" align="center">
		   <input type="text" name="txtCvpMonthlyAmount" id="txtCvpMonthlyAmount${Counter.index}" value="${trnPnsnrRevisionDtls.cvpMonthlyAmount}" size="15" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>
		   </td>
		   <td class="tds" align="center">
		   <input type="text" name="txtFp1Amount" id="txtFp1Amount${Counter.index}" value="${trnPnsnrRevisionDtls.fp1Amount}" size="15" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>
		   </td>
		   <td class="tds" align="center">
		   <input type="text" name="txtFp2Amount" id="txtFp2Amount${Counter.index}" value="${trnPnsnrRevisionDtls.fp2Amount}" size="15" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>
		   </td>
		   <td class="tds" align="center">
		   <input type="text" name="txtDcrgAmount" id="txtDcrgAmount${Counter.index}" value="${trnPnsnrRevisionDtls.dcrgAmount}" size="15" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>
		   </td>
		   <td>
		   <c:if test="${trnPnsnrRevisionDtls.revisionFlag == 'S' and Counter.index > 0}">
		   <img name="Image" id="Image${Counter.index}"	src="images/CalendarImages/DeleteIcon.gif"	onclick="RemoveTableRow(this,'tblRevisionDtls')" />
		   </c:if>
		   </td>
		   </tr>
		   <c:if test="${trnPnsnrRevisionDtls.revisionFlag == 'A' or Counter.index == 0}">
		   <script>
		   	document.getElementById("cmbRopType"+Number('${Counter.index}')).disabled=true;
		   	document.getElementById("txtRevisionDate"+Number('${Counter.index}')).disabled=true;
		   	document.getElementById("txtPensionSanctionAmt"+Number('${Counter.index}')).disabled=true;
		   	document.getElementById("txtCvpAmount"+Number('${Counter.index}')).disabled=true;
		   	document.getElementById("txtCvpMonthlyAmount"+Number('${Counter.index}')).disabled=true;
		   	document.getElementById("txtFp1Amount"+Number('${Counter.index}')).disabled=true;
		   	document.getElementById("txtFp2Amount"+Number('${Counter.index}')).disabled=true;
		   	document.getElementById("txtDcrgAmount"+Number('${Counter.index}')).disabled=true;
		   	document.getElementById("imgReceivedDate"+Number('${Counter.index}')).disabled=true;
		   </script>
		   </c:if>
		   <script>
				document.getElementById("hdnRevGridSize").value=Number('${Counter.index}') + 1;
			</script>
		  </c:forEach>
		  </c:if>
</table> 
</div>
	
</fieldset>
<br/>

<script>
if(window.opener.document.getElementById("hdnCaseStatus").value=='Rejected')
{
	var rowCnt=document.getElementById("hdnRevGridSize").value;
	rowCnt=Number(rowCnt)-1;
	document.getElementById("cmbRopType"+rowCnt).disabled=false;
   	document.getElementById("txtRevisionDate"+rowCnt).disabled=false;
   	document.getElementById("txtPensionSanctionAmt"+rowCnt).disabled=false;
   	document.getElementById("txtCvpAmount"+rowCnt).disabled=false;
   	document.getElementById("txtCvpMonthlyAmount"+rowCnt).disabled=false;
   	document.getElementById("txtFp1Amount"+rowCnt).disabled=false;
   	document.getElementById("txtFp2Amount"+rowCnt).disabled=false;
   	document.getElementById("txtDcrgAmount"+rowCnt).disabled=false;
   	document.getElementById("imgReceivedDate"+rowCnt).disabled=false;
}
</script>
<!-- 
<fieldset class="tabstyle" style="width:100%"><legend> <b>
	<fmt:message key="PPMT.ALLOCATION" bundle="${pensionLabels}"></fmt:message></b> </legend>
	
<div style="float: inherit; border: 1px solid #000000; background-color: transparent; width: 100%; height: 120px; overflow: scroll; overflow-x: scroll; overflow-y: scroll;">
<table width="95%">
	       <tr>
	             <td width="90%">
	                 <table id="TableAllocation" align="left" width="100%" cellspacing="0" border="1">				
			    		<tr class="datatableheader"> 
						   <td width="10%" class="pensionLabels"><fmt:message key="PPMT.PENSION" bundle="${pensionLabels}" ></fmt:message></td>	
						   <td width="10%" class="pensionLabels"><fmt:message key="PPMT.BEFORE01041936" bundle="${pensionLabels}"></fmt:message></td>
						   <td width="10%" class="pensionLabels"><fmt:message key="PPMT.AFTER01041936" bundle="${pensionLabels}"></fmt:message></td>
						   <td width="10%" class="pensionLabels"><fmt:message key="PPMT.AFTER01111956" bundle="${pensionLabels}"></fmt:message></td>
						   <td width="10%" class="pensionLabels"><fmt:message key="PPMT.AFTER01051960" bundle="${pensionLabels}"></fmt:message></td>
						   <td width="10%" class="pensionLabels"><fmt:message key="PPMT.ALLOCATION5" bundle="${pensionLabels}"></fmt:message></td>						   
						   <td width="1%" class="pensionLabels"></td>						   						   
					    </tr>					    
				     </table>
				 </td>
				 <td width="10%" valign="bottom">
				 <hdiits:button name="AllocationAddRow" type="button" caption="ADDROW" bundle="${pensionLabels}" onclick="addRowAllocation();"  />
				 </td>
				 
		   </tr>
</table> 
</div>
	
</fieldset>	




<fieldset class="tabstyle" style="width:100%"><legend> <b>
	<fmt:message key="PPMT.CVPRESTORATION" bundle="${pensionLabels}"></fmt:message></b> </legend>
<table>
	<tr>
		<td width="15%">
	       <fmt:message key="PPMT.CVPRESAMT" bundle="${pensionLabels}"></fmt:message>
	    </td>
		 <td width="35%">
		      <input type="text" id="txtCvpResAmt" name="txtCvpResAmt"/>				         
		 </td>
	</tr>
	<tr>	
	    <td width="15%">
	       <fmt:message key="PPMT.FROMDATE" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	        <input type="text" id="txtFromDate" name="txtFromDate" />	
	        <img src='images/CalendarImages/ico-calendar.gif'
					        onClick='window_open("txtFromDate",375,570)'style="cursor: pointer;" ${disabled}/>        
		 </td>		 		      
	</tr>
	<tr>	
	    <td width="15%">
	       <fmt:message key="PPMT.TODATE" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	        <input type="text" id="txtToDate" name="txtToDate" />	  
	        <img src='images/CalendarImages/ico-calendar.gif'
					        onClick='window_open("txtToDate",375,570)'style="cursor: pointer;" ${disabled}/>      
		 </td>		 		      
	</tr>
</table>
</fieldset>
 -->
</fieldset>

<br/>
<table  align="center">
<tr>
	<td>
	<hdiits:button name="btnAccomodate" type="button" caption="Accommodate" bundle="${pensionLabels}" onclick="SaveRevisionData('A');" classcss="bigbutton" />
	<hdiits:button name="btnSave" type="button" caption="Save" bundle="${pensionLabels}" onclick="SaveRevisionData('S');"  />
	<hdiits:button name="btnClose" type="button" caption="Close" bundle="${pensionLabels}" onclick="winCls();"  />
	</td>
</tr>
</table>
</hdiits:form>