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
<c:set var="billFlag" value="${resValue.billFlag}"></c:set>
<script>
function printDoc(){
			
			var cw = window.open("","","status=yes,toolbar=no,menubar=yes,location=no,scrollbars=yes,resizable=yes,width=670");
			cw.document.write('<style media="print">');
		   	cw.document.write('@FONT-FACE { font-family : sans-serif;}');
		   	cw.document.write('@media print {BODY { font-size: 10pt }}');
		   	cw.document.write('@media screen {BODY { font-size: 10pt } }');
		   	cw.document.write('@media screen, print { BODY { line-height: 1.2 }}');
		   	cw.document.write('@page cheque{ size 12in 12in; margin: 0.5cm }');
		   	cw.document.write('</style>');
		   	cw.document.write('<body>');  
		 //   cw.document.write('@page { margin: 2cm }');
		   // cw.document.write('@media print {#allbillString{page-break-after:always}}');
		   // cw.document.write('@media screen {#allbillString{page-break-after:always}}');
		   // cw.document.write('</style>');
		    //cw.document.write('@media screen, print { BODY { line-height: 1.2 }}');
		  //  cw.document.write('@page cheque{ size 12in 12in; margin: 0.5cm }');
		   // cw.document.write('DIV {page: cheque; page-break-after: left;  }');
		    //cw.document.write('</style>');
        	cw.document.write('<pre>');
           	cw.document.write(document.getElementById("hidPrintString").value);	
         //  cw.document.write(document.getElementById("tcontent1").style="page-break-after:always");
                   	
            cw.document.write('</pre>');
           	
            cw.document.write('</body>');  
            cw.document.write('<script language="javascript">');
            cw.document.write("window.print();");
            cw.document.write( '<' + "/script" + '>');
            cw.location.reload(false);
  	
             
			}
function closePrintBill()
{
	var billFlag = "${billFlag}";
	//--If billFlag is C then request is came from Generate Monthly Bill Report link , else from pring monthly bill report link
	if(billFlag == "C")
	{
		window.location.href="ifms.htm?actionFlag=loadMonthlyList";
	}
	else
	{
		window.location.href="ifms.htm?actionFlag=loadMonthlyBillReport";
	}
	
}

</script>

<hdiits:form name="frmPrintBills" validate="false">
<br>
<input type="hidden" id="hidPrintString" name="hidPrintString" value="${resValue.FinalPrintString}"/>
<div align="center"><h5><fmt:message key="PPMT.MONTHLYBILLREPORT" bundle="${pensionLabels}" ></fmt:message></h5></div>
	
	
    <div id='maindiv' style="width:100%; overflow:scroll;" align="center"> 
      <textarea  id="allbillString" rows="50"  cols="135" class="tabstyle" style="font-family:monospace; font-size:14px;padding-left: 25px;text-align: justify"  readonly="readonly" >
        ${resValue.FinalPrintString}
      </textarea>
    </div>
   
	<div align="center">
	<hdiits:button type="button" captionid="PPMT.PRINTREPORT" bundle="${pensionLabels}" id="btnPrintReport" name="btnPrintReport" onclick="printDoc()" classcss="bigbutton"/>
	<hdiits:button type="button" captionid="PPMT.CLOSE"  bundle="${pensionLabels}" id="btnClose" name="btnClose" onclick="closePrintBill();" />
	</div>
</hdiits:form>
