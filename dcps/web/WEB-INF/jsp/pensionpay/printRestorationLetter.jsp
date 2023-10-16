<%
try {
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	

<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script type="text/javascript"	src="script/common/val.js"></script>
<script type="text/javascript"	src="script/common/tabcontent.js"></script>
<script type="text/javascript"  src="script/common/commonfunctions.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script  type="text/javascript"  src="script/common/CalendarPopup.js"></script>
<script type="text/javascript"	src="script/common/IFMSCommonFunctions.js"></script>

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request" />
<c:set var="resultObj" value="${result}" />
<c:set var="resValue" value="${resultObj.resultValue}" />
<c:set var="rstrnLetter" value="${resValue.DisplayRstrnLetterString}"></c:set>
<c:set var="PopUpScreen" value="${resValue.PopUpScreen}"></c:set>
<input type="hidden" id="hdnRstrnLetter" name="hdnRstrnLetter" value="${resValue.PrintRstrnLetterString}"/>
<script>
function genearateCVPLetter()
{
	var url = "ifms.htm?actionFlag=generateCVPRestorationLetter&fromDate="+document.getElementById("txtFromDate").value+"&toDate="+document.getElementById("txtToDate").value;
	var newWindow = null;
   	var height = screen.height-50;
   	var width = screen.width-50;
   	var urlstring = url;
   	var urlstyle = "height="+height+",width="+width+",toolbar=no,minimize=no,status=no,menubar=no,location=no,scrollbars=yes,top=180,left=250";
   	newWindow = window.open(urlstring, "DepositAccMst", urlstyle);
}
function printRstrnLetter()
{
	var cw = window.open("","","status=yes,toolbar=no,menubar=yes,location=no,scrollbars=yes,resizable=yes,width=750");
	cw.document.write('<style media="print">');
    cw.document.write('@FONT-FACE { font-family : sans-serif;}');
    cw.document.write('@media print {BODY { font-size: 10pt }}');
    cw.document.write('@media screen {BODY { font-size: 10pt } }');
    cw.document.write('@media screen, print { BODY { line-height: 1.2 }}');
    cw.document.write('@page cheque{ size 12in 12in; margin: 0.5cm }');
    cw.document.write('DIV {page: cheque; page-break-after: left;  }');
    cw.document.write('</style>');
    cw.document.write('<body>');  
    var printString = document.getElementById("hdnRstrnLetter").value;
    cw.document.write(printString);	
    cw.document.write('</body>');  
    cw.document.write('<script language="javascript">');
	cw.document.write("window.print();");
 	cw.document.write( '<' + "/script" + '>'); 
    cw.location.reload(false);   
}
</script>

<hdiits:form name="frmRstrnLetter" id="frmRstrnLetter"  validate="true" method="post">

<c:if test="${PopUpScreen == null}">
	<fieldset style="width:100%"  class="tabstyle">

	<legend	id="headingMsg">
			<b><fmt:message key="PPMT.GENERATECVPLETR" bundle="${pensionLabels}"></fmt:message></b>
	</legend>
	<div>&nbsp;</div>
<table width="75%" align="center">
	<tr>
		<td width="5%">
	    <td width="10%">
	       <fmt:message key="PPMT.FROMDATE" bundle="${pensionLabels}"></fmt:message>
	    </td>
	     <td width="8%">
	  		<input type="text" name="txtFromDate" onkeypress="digitFormat(this);dateFormat(this);numberFormat(event)" id="txtFromDate" />
	  	</td>
	  	<td width="2%">
	  		<img src='images/CalendarImages/ico-calendar.gif' width='20' onClick='window_open(event,"txtFromDate",375,570)' style="cursor: pointer;"/>
	  	</td>
	   
	     <td width="25%">
	     <td width="10%">
	       <fmt:message key="PPMT.TODATE" bundle="${pensionLabels}"></fmt:message>
	    </td>
	     <td width="8%">
	  		<input type="text" name="txtToDate" onkeypress="digitFormat(this);dateFormat(this);numberFormat(event)" id="txtToDate" />
	  	</td>
	  	<td width="2%">
	  		<img src='images/CalendarImages/ico-calendar.gif' width='20' onClick='window_open(event,"txtToDate",375,570)' style="cursor: pointer;"/>
	    </td>
	   <td width="5%">      
	</tr>
</table>

<div>&nbsp;</div>
	
</fieldset>
<div>&nbsp;</div>
<div align="center">
	<hdiits:button name="btnGenerateCVPLatter" type="button" captionid="PPMT.GENERATECVPLETR" bundle="${pensionLabels}" onclick="genearateCVPLetter();"  style="width:300px"  />
	<hdiits:button name="btnClose" type="button" captionid="PPMT.CLOSE" bundle="${pensionLabels}" onclick="winCls();"  />
</div>
</c:if>
<c:if test="${PopUpScreen == 'PopUp'}">
	<div align="center"><h5><fmt:message key="PPMT.CMTNRSTRNLETTER" bundle="${pensionLabels}" ></fmt:message></h5></div>
    <div id='maindiv' style="width:100%; overflow:scroll;" align="center"> 
      <textarea  id="lop" rows="30"  cols="100" class="tabstyle" style="font-family:monospace; font-size:14px;padding-left: 25px;text-align: justify"  readonly="readonly" >
        ${rstrnLetter}
      </textarea>
    </div>
    <div align="center">
	<hdiits:button type="button" captionid="PPMT.PRINTREPORT" bundle="${pensionLabels}" id="btnPrintReport" name="btnPrintReport" onclick="printRstrnLetter();" classcss="bigbutton"/>
	<hdiits:button type="button" captionid="PPMT.CLOSE"  bundle="${pensionLabels}" id="btnClose" name="btnClose" onclick="winCls();" />
	</div>
</c:if>


</hdiits:form>
<%

} catch (Exception e) {
	e.printStackTrace();
}


%>