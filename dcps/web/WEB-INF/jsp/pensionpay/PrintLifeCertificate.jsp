<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@page import="com.tcs.sgv.common.constant.Constants"%>

<script type="text/javascript"  src="script/common/common.js"></script>
<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="lifeCertificate" value="${resValue.DisplayLifeCertificateString}"></c:set>
<input type="hidden" id="hdnLifeCertificate" name="hdnLifeCertificate" value="${resValue.PrintLifeCertificateString}"/>
<script>
function printLifeCertificate()
{

	var cw = window.open("","","status=yes,toolbar=no,menubar=yes,location=no,scrollbars=yes,resizable=yes,width=670");
	cw.document.write('<style media="print">');
    cw.document.write('@FONT-FACE { font-family : sans-serif;}');
    cw.document.write('@media print {BODY { font-size: 10pt }}');
    cw.document.write('@media screen {BODY { font-size: 10pt } }');
    cw.document.write('@media screen, print { BODY { line-height: 1.2 }}');
    cw.document.write('@page cheque{ size 12in 12in; margin: 0.5cm }');
    cw.document.write('DIV {page: cheque; page-break-after: left;  }');
    cw.document.write('</style>');
    cw.document.write('<body>');  
    var printString = document.getElementById("hdnLifeCertificate").value;
    cw.document.write(printString);	
    cw.document.write('</body>');  
    cw.document.write('<script language="javascript">');
	cw.document.write("window.print();");
 	cw.document.write( '<' + "/script" + '>');
    cw.location.reload(false); 
}

function getLifeCertList()
{
	window.location.href="ifms.htm?actionFlag=getLifeCertificateList";
	
}
</script>

<hdiits:form name="frmPrintLifeCertificate" validate="false">
<br>
<div align="center"><h5><fmt:message key="PPMT.LIFECERTREPORT" bundle="${pensionLabels}" ></fmt:message></h5></div>
	
	
    <div id='maindiv' style="width:100%; overflow:scroll;" align="center"> 
      <textarea  id="lop" rows="30"  cols="80" class="tabstyle" style="font-family:monospace; font-size:14px;padding-left: 25px;text-align: justify"  readonly="readonly" >
        ${lifeCertificate}
      </textarea>
    </div>
   
	<div align="center">
	<hdiits:button type="button" captionid="PPMT.PRINTREPORT" bundle="${pensionLabels}" id="btnPrintReport" name="btnPrintReport" onclick="printLifeCertificate()" classcss="bigbutton"/>
	<hdiits:button type="button" captionid="PPMT.CLOSE"  bundle="${pensionLabels}" id="btnClose" name="btnClose" onclick="getLifeCertList();" />
	</div>
</hdiits:form>


