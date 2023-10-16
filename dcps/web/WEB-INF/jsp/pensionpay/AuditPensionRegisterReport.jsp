<%try{ %>
<%@ page language="java" %>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript"	src="script/common/tabcontent.js"></script>

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request" />
<script>
function printDoc(){
			
			var cw = window.open("","","status=yes,toolbar=no,menubar=yes,location=no,scrollbars=yes,resizable=yes,width=670");
			cw.document.write('<style media="print">');
		    cw.document.write('@FONT-FACE { font-family : sans-serif;}');
		 //   cw.document.write('@page { margin: 2cm }');
		    cw.document.write('@media print {#tcontent1{page-break-after:always}}');
		    cw.document.write('@media screen {#tcontent1{page-break-after:always}}');
		    cw.document.write(' <style>');
		    cw.document.write('@FONT-FACE { font-family : sans-serif;}');
		    cw.document.write('@media print {#tblPensionerDtls{page-break-after:always;}}');
		    cw.document.write('@media screen {#tblPensionerDtls{page-break-after:always;}}');
		    cw.document.write('#tblPensionerDtls{margin-top:-50px;}');
		    cw.document.write('</style>');
		    //cw.document.write('@media screen, print { BODY { line-height: 1.2 }}');
		  //  cw.document.write('@page cheque{ size 12in 12in; margin: 0.5cm }');
		   // cw.document.write('DIV {page: cheque; page-break-after: left;  }');
		    //cw.document.write('</style>');
        	cw.document.write('<pre>');
           	cw.document.write(document.getElementById("tcontent1").innerHTML);	
         //  cw.document.write(document.getElementById("tcontent1").style="page-break-after:always");
                   	
           	cw.document.write(document.getElementById("tcontent2").innerHTML);
           	cw.document.write('</pre>');
           	
         	cw.document.write( '<' + "script" + '>');
         	cw.document.write("window.print();");
           	cw.document.write( '<' + "/script" + '>');
             
			
		}
</script>
<hdiits:form name="PnsnrDtlsTabForm" id="PnsnrDtlsTabForm"
	method="POST" action="" encType="multipart/form-data" validate="true">
<div id="tabmenu">
     <ul id="maintab" class="shadetabs" >
	     	<li class="selected">
		    	<a href="#" rel="tcontent1" >
		  			<fmt:message key="PPMT.PENSIONERDTLS" bundle="${pensionLabels}"></fmt:message>
		        </a>
	        </li>
	       	<li>
		        <a href="#" rel="tcontent2"  tabindex="50">
					<fmt:message key="PPMT.PAYMENTDTLS" bundle="${pensionLabels}"></fmt:message>
		        </a>
	        </li>
	 </ul>
</div>

<div class="tabcontentstyle">
	<!-- ------------------Pension Details--------------- -->
		<div id="tcontent1" class="tabcontent" align="left" style="page-break-after:always" >
			<jsp:include page="/WEB-INF/jsp/pensionpay/AuditRegisterPensionerDtls.jsp" />
		</div>	

	<!-- ------------------ Payment Details --------------- -->
		<div id="tcontent2" class="tabcontent" align="left">
			<jsp:include page="/WEB-INF/jsp/pensionpay/AuditRegisterPayDtls.jsp" />
		</div>
</div>
	
</hdiits:form>
<table>
<tr>
	<td><input type="button" value="print" onclick="printDoc()"/></td>
</tr>
</table>
<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab");
</script>


<%}catch(Exception ex){ex.printStackTrace();}%>