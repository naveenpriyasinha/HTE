<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>  
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@page	import="com.tcs.sgv.common.valueobject.SgvaMonthMst"%>
<script type="text/javascript" src="script/pensionpay/Monthly.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="script/pensionpay/pensionBills.js"> </script>
<head>
<title>Variation Monthly Report</title>
	<style>
		.tabstyle 
		{
			border-width: 5px 1px 1px 1px; 
			border-color: #2065c0;
			border-style: solid ;
		}	
		.legend 
		{
			padding-left: 5px;
			padding-right: 5px;
			font-weight: normal; 
			font-family: verdana;
			border-width: 0px 0px 1px 0px; 
			border-color: #2065c0;
			border-style: solid ;
		}	
	</style>	
</head>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="DisplayString" value="${resValue.DisplayString}"> </c:set>

<fieldset class="tabstyle">
<legend id="headingMsg"> <b> Variation Details </b> </legend>
<form name="VariationForm" id="VariationForm" action="post">
	<table align="center" width="90%" border="0">
		<tbody>
			<tr>	
				<td align="left" width="8%">
					<input name='chkBB' type='checkbox' checked='Y' value="Y"> B.B. 1/11/56
				</td>
				<td align="left" width="8%">
					<input name='chkBA' type='checkbox' value="Y" checked='Y'> B.A. 1/11/56
				</td>
				
				<td align="left" width="8%">
					<input name='chkBasic' type='checkbox' value="Y" checked='Y' disabled="disabled"> Basic Pension     
				</td>
				<td align="left" width="8%">
					 <input name='chkDA' type='checkbox' value="Y" checked='Y'> DA Pension     
				</td>
				<td align="left" width="8%">
					<input name='chkIR' type='checkbox' value="Y" checked='Y'> IR        
				</td>
				<td align="left" width="8%">
					<input name='chkMA' type='checkbox' value="Y" checked='Y' disabled="disabled"> MA
				</td>
				<td align="left" width="8%">
					<input name='chkTI' type='checkbox' value="Y" checked='Y'> TI     
				</td>
				<td align="left" width="8%">
					<input name='chkATI' type='checkbox' value="chkATI" value="Y" checked='Y'> Arrear TI     
				</td>
				<td align="left" width="8%">
					<input name='chkNetAmt' type='checkbox' value="Y" checked='Y'> Net Amount   
				</td>
			</tr>
			<tr>	
				<td align="left">
					<input name='chkGA' type='checkbox' value="Y" checked='Y'> G.A. 1/5/60
				</td>
				<td align="left">
					<input name='chkPenCut' type='checkbox' value="Y" checked='Y' disabled="disabled"> Pension Cut    
				</td>
				<td align="left">
					<input name='chkCVP' type='checkbox' value="Y" checked='Y' disabled="disabled"> CVP Monthly       
				</td>
				<td align="left">
					<input name='chkPerPen' type='checkbox' value="Y" checked='Y'> Personal Pension  
				</td>
				<td align="left">
					<input name='chkArr' type='checkbox' value="Y" checked='Y'> Arrear 
				</td>
				<td align="left">
					<input name='chkOthBen' type='checkbox' value="Y" checked='Y'> Other Benefit   
				</td>
				<td align="left">
					<input name='chkOthCut' type='checkbox' value="Y" checked='Y'> Other Cut    
				</td>
				<td align="left">
					<input name='chkMOCom' type='checkbox' value="Y" checked='Y'> MO. Com 
				</td>
				<td align="left">
					<input name='chkDed' type='checkbox' value="Y" checked='Y'> Deduction    
				</td>
			</tr>
			</tbody>
			<tfoot>
			<tr><td></td><td></td></tr>
			<tr><td></td><td></td></tr>
			<tr><td></td><td></td></tr>
			<tr><td></td><td></td></tr>
			<tr><td></td><td></td></tr>
			<tr>
				<td>
				</td>
				<td width="20%" colspan="2">
					<hdiits:button type="button" name="btnVariation" id="btnVariation" value="Variation" onclick="getVariationDetails()"/>
				</td>
				<td width="20%" colspan="2">
					<hdiits:button type="button" name="btnClose" id="btnClose" value="Close" onclick="window.close()"/>
				</td>
				<td width="20%" colspan="2">
					<hdiits:button type="button" name="btnSave" id="btnSave" value="Save" onclick="saveData1()" readonly="true" />
				</td>
				<td width="20%" colspan="2">
					<hdiits:button type="button" name="btnPrint" id="btnPrint" value="Print" onclick="Open_MonthlyPrintBill()" readonly="true" />
				</td>
			</tr>
			</tfoot>
</table>
<div id='maindiv' style="width:100%; overflow:scroll;"> 
  <textarea  id="MonthlyPrint" rows="30"  cols="150" class="tabstyle" style="font-family:monospace; font-size:14px"  readonly="readonly" >
     ${DisplayString}
  </textarea>
</div>
</form>
</fieldset>
<script>
	if(document.getElementById("MonthlyPrint").value.length > 10 )
	{
		try{
			//window.opener.document.getElementById("MonthlyPrint").value = document.getElementById("MonthlyPrint").value;
			//window.opener.document.getElementById("btnSave").disabled = false;
			//window.opener.document.getElementById("btnPrint").disabled = false;
			window.returnValue = document.getElementById("MonthlyPrint").value;
			window.close();
		}catch(err)	{}
	}
	function saveData1()
	{
		document.getElementById("MonthlyPrint").value = '';
		window.returnValue = true;
		window.close();
	}
	function Open_MonthlyPrintBill()
	{
//			var cw = window.open(null,null,"height="+(screen.height - 75)+",width="+(screen.width)+",top=200,left=200,status=no,toolbar=no,menubar=no,location=no");
		var cw = window.open("","","status=yes,toolbar=no,menubar=yes,location=no,scrollbars=yes,resizable=yes");
		cw.document.write('<style media="print">');
	    cw.document.write('@FONT-FACE { font-family : sans-serif;}');
	    cw.document.write('@media print {BODY { font-size: 10pt }}');
	    cw.document.write('@media screen {BODY { font-size: 10pt } }');
	    cw.document.write('@media screen, print { BODY { line-height: 1.2 }}');
	    cw.document.write('@page cheque{ size 14in 12in; margin: 0.5cm }');
        cw.document.write('DIV {page: cheque; page-break-after: left;  }');
        cw.document.write('</style>');
        cw.document.write('<body>');  
        
        var printString = document.getElementById("MonthlyPrint").value;
        cw.document.write(printString);	
      
	    cw.document.write('</body>');  
	    cw.document.write('<script language="javascript">');
		cw.document.write("window.print();");
     	cw.document.write( '<' + "/script" + '>');
        cw.location.reload(false); 
	}
</script>	
