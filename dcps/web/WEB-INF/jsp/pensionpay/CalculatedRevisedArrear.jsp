
<%try { %>
<link rel="stylesheet" href="themes/ifmsblue/exprcpt.css" type="text/css" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<script type="text/javascript"  src="script/common/IFMSCommonFunctions.js"></script>

<form name="ArrearCalcForRevision" method="post">
	<input type="hidden" name="hidPensnReqId" id="hidPensnReqId" value="${resValue.pensionReqId}"/>
	<input type="hidden" name="hidPnsnrCode" id="hidPnsnrCode" value="${resValue.PensionerCode}"/>
	<input type="hidden" name="hidArrearAmnt" id="hidArrearAmnt" value="${resValue.PrintString}"/>
	<input type="hidden" name="SupplyFlg" id="SupplyFlg" value="${resValue.lStrSupplyFlg}"/>
	<input type="hidden" name="hidRevArrearAmnt" id="hidRevArrearAmnt" value="${resValue.Arrear}"/>
	<input type="hidden" name="hidPrintString" id="hidPrintString" value="${resValue.PrintString}"/>
	<input type="hidden" name="hidPrintTotalAmt" id="hidPrintTotalAmt" value="${resValue.PrintTotalAmount}"/>
	<input type="hidden" name="hidnBillFlag" id="hidnBillFlag" value="${resValue.BillFlag}"/>
	<input type="hidden" name="hidRowCnt" id="hidRowCnt" value="${resValue.RowCnt}"/>
	
	<input type="hidden" name="hidPensionAmt" id="hidPensionAmt" value="${resValue.PensionAmount}"/>
	<input type="hidden" name="hidADPAmt" id="hidADPAmt" value="${resValue.ADPAmount}"/>
	<input type="hidden" name="hidDPAmt" id="hidDPAmt" value="${resValue.DPAmount}"/>
	<input type="hidden" name="hidIR1Amt" id="hidIR1Amt" value="${resValue.IR1Amount}"/>
	<input type="hidden" name="hidIR2Amt" id="hidIR2Amt" value="${resValue.IR2Amount}"/>
	<input type="hidden" name="hidIR3Amt" id="hidIR3Amt" value="${resValue.IR3Amount}"/>
	<input type="hidden" name="hidDAAmt" id="hidDAAmt" value="${resValue.DAAmount}"/>
	<input type="hidden" name="hidCVPAmt" id="hidCVPAmt" value="${resValue.CVPAmount}"/>
	<input type="hidden" name="hidPeonAllowAmt" id="hidPeonAllowAmt" value="${resValue.PeonAllowAmount}"/>
	<input type="hidden" name="hidMedicalAllowAmt" id="hidMedicalAllowAmt" value="${resValue.MedicalAllowAmount}"/>
	<input type="hidden" name="hidGallantryAmt" id="hidGallantryAmt" value="${resValue.GallantryAmount}"/>
	<input type="hidden" name="hidOtherBenefit" id="hidOtherBenefit" value="${resValue.OtherBenefit}"/>
	
	<div align="center"><h5>Arrear Calculation For Revision</h5></div>	
		
	<div align="center" style="width:100%; overflow:scroll;"> 
	      <textarea id="lop" rows="30" cols="140" class="tabstyle" style="font-family:monospace; font-size:14px" readonly="readonly">
	         ${resValue.DisplayString}
	      </textarea>		      
	 </div>
	 
	 <div align="center">
		<input type="button" name="print" id="print" value="Print" class="bigbutton" onclick="printDotMatrix()" />
		<c:if test="${resValue.BillFlag == 'P' or resValue.BillFlag == 'S' or resValue.BillFlag == 'L'}" >
		<input type="button" name="btnSave" id="btnSave" value="Save" class="bigbutton" onclick="populateArrerAmtInBill();" />
		</c:if>
		<input type="button" name="Close" id="Close" value="Close" class="bigbutton" onclick="self.close()" />
	</div>
</form>


<script type="text/javascript">
function populateArrerAmtInBill()
{
	if(document.getElementById("hidnBillFlag").value=='P' )
	{
		var lCalcOthrArrearAmt = Number(document.getElementById("hidRevArrearAmnt").value);
		window.opener.opener.document.getElementById("txtOthArrearAmt").value=lCalcOthrArrearAmt+Number(window.opener.opener.document.getElementById("txtOthArrearAmt").value)-Number(window.opener.opener.document.getElementById("hdnCalcOthrArrearAmt").value);
		setValidAmountFormat(window.opener.opener.document.getElementById("txtOthArrearAmt"));
		window.opener.opener.document.getElementById("arrearDtls").value="";
		window.opener.opener.document.getElementById("arrearDtlContainer").innerHTML="";
		window.opener.opener.document.getElementById("arrearDtls").value=document.getElementById("hidPrintTotalAmt").value;
		window.opener.opener.document.getElementById("arrearDtlContainer").innerHTML=document.getElementById("hidPrintTotalAmt").value;
		var lNetAmount = window.opener.opener.document.getElementById("txtNetPensionAmt").value;
		var lGrossAmount = window.opener.opener.document.getElementById("txtPensionBillAmt").value;
		var lCalcNetAmount = Number(lNetAmount)+Number(lCalcOthrArrearAmt)-Number(window.opener.opener.document.getElementById("hdnCalcOthrArrearAmt").value);
		var lCalcGrossAmount = Number(lGrossAmount)+Number(lCalcOthrArrearAmt)-Number(window.opener.opener.document.getElementById("hdnCalcOthrArrearAmt").value);
		window.opener.opener.document.getElementById("txtNetPensionAmt").value = lCalcNetAmount;
		window.opener.opener.document.getElementById("txtNetAmt").value = lCalcNetAmount;
		window.opener.opener.document.getElementById("txtNetAmtInword").innerHTML = "";
		window.opener.opener.document.getElementById("txtNetAmtInword").innerHTML = getAmountInWords(lCalcNetAmount);
		setValidAmountFormat(window.opener.opener.document.getElementById("txtNetPensionAmt"));
		window.opener.opener.document.getElementById("txtChkAmt").value = lCalcNetAmount;
		setValidAmountFormat(window.opener.opener.document.getElementById("txtChkAmt"));
		window.opener.opener.document.getElementById("txtPensionBillAmt").value = lCalcGrossAmount;
		window.opener.opener.document.getElementById("txtExpenditure").value = lCalcGrossAmount;
		setValidAmountFormat(window.opener.opener.document.getElementById("txtPensionBillAmt"));
		setValidAmountFormat(window.opener.opener.document.getElementById("txtExpenditure"));
		window.opener.opener.document.getElementById("hdnCalcOthrArrearAmt").value = lCalcOthrArrearAmt;	
	}
	if(document.getElementById("hidnBillFlag").value=='S')
	{
		var grossAmount;
		var lCalcOthrArrearAmt = document.getElementById("hidRevArrearAmnt").value;
		window.opener.opener.document.getElementById("hdnArrearAmt").value=lCalcOthrArrearAmt;
		setValidAmountFormat(window.opener.opener.document.getElementById("hdnArrearAmt"));
		//window.opener.opener.document.getElementById("txtOthArrearAmt").value=lCalcOthrArrearAmt;
		//setValidAmountFormat(window.opener.opener.document.getElementById("txtOthArrearAmt"));
		window.opener.opener.document.getElementById("arrearDtls").value="";
		window.opener.opener.document.getElementById("arrearDtlContainer").innerHTML="";
		window.opener.opener.document.getElementById("arrearDtls").value=document.getElementById("hidPrintTotalAmt").value;
		window.opener.opener.document.getElementById("arrearDtlContainer").innerHTML=document.getElementById("hidPrintTotalAmt").value;
		
		window.opener.opener.document.getElementById("txtPensionAmt").value=document.getElementById("hidPensionAmt").value;
		setValidAmountFormat(window.opener.opener.document.getElementById("txtPensionAmt"));
		window.opener.opener.document.getElementById("txtADPAmt").value=document.getElementById("hidADPAmt").value;
		setValidAmountFormat(window.opener.opener.document.getElementById("txtADPAmt"));
		window.opener.opener.document.getElementById("txtDPAmt").value=document.getElementById("hidDPAmt").value;
		setValidAmountFormat(window.opener.opener.document.getElementById("txtDPAmt"));
		window.opener.opener.document.getElementById("txtIR1Amt").value=document.getElementById("hidIR1Amt").value;
		setValidAmountFormat(window.opener.opener.document.getElementById("txtIR1Amt"));
		window.opener.opener.document.getElementById("txtIR2Amt").value=document.getElementById("hidIR2Amt").value;
		setValidAmountFormat(window.opener.opener.document.getElementById("txtIR2Amt"));
		window.opener.opener.document.getElementById("txtIR3Amt").value=document.getElementById("hidIR3Amt").value;
		setValidAmountFormat(window.opener.opener.document.getElementById("txtIR3Amt"));
		window.opener.opener.document.getElementById("txtDAAmt").value=document.getElementById("hidDAAmt").value;
		setValidAmountFormat(window.opener.opener.document.getElementById("txtDAAmt"));
		window.opener.opener.document.getElementById("txtCvpAmt").value=document.getElementById("hidCVPAmt").value;
		setValidAmountFormat(window.opener.opener.document.getElementById("txtCvpAmt"));
		window.opener.opener.document.getElementById("txtPeonAllowAmt").value=document.getElementById("hidPeonAllowAmt").value;
		setValidAmountFormat(window.opener.opener.document.getElementById("txtPeonAllowAmt"));
		window.opener.opener.document.getElementById("txtMedicalAllowAmt").value=document.getElementById("hidMedicalAllowAmt").value;
		setValidAmountFormat(window.opener.opener.document.getElementById("txtMedicalAllowAmt"));
		window.opener.opener.document.getElementById("txtGallantryAmt").value=document.getElementById("hidGallantryAmt").value;
		setValidAmountFormat(window.opener.opener.document.getElementById("txtGallantryAmt"));
		window.opener.opener.document.getElementById("txtOtherBenefit").value=document.getElementById("hidOtherBenefit").value;
		setValidAmountFormat(window.opener.opener.document.getElementById("txtOtherBenefit"));
		
		grossAmount = Number(document.getElementById("hidPensionAmt").value)+Number(document.getElementById("hidADPAmt").value)+
						Number(document.getElementById("hidDPAmt").value)+Number(document.getElementById("hidIR1Amt").value)+
						Number(document.getElementById("hidIR2Amt").value)+Number(document.getElementById("hidIR3Amt").value)+
						Number(document.getElementById("hidDAAmt").value)+Number(document.getElementById("hidCVPAmt").value)+
						Number(document.getElementById("hidPeonAllowAmt").value)+Number(document.getElementById("hidMedicalAllowAmt").value)+
						Number(document.getElementById("hidGallantryAmt").value)+Number(document.getElementById("hidOtherBenefit").value)+
						Number(window.opener.opener.document.getElementById("txtOthArrearAmt").value)+
						Number(window.opener.opener.document.getElementById("txtOthArrearAmt2").value)+
						Number(window.opener.opener.document.getElementById("txtOthArrearAmt3").value);
		window.opener.opener.document.getElementById("txtGrossAmt").value=grossAmount;
		setValidAmountFormat(window.opener.opener.document.getElementById("txtGrossAmt"));
		var netAmount = Number(window.opener.opener.document.getElementById("txtGrossAmt").value)-
						Number(window.opener.opener.document.getElementById("txtRecAmt").value);
		window.opener.opener.document.getElementById("txtNetAmt").value = netAmount;
		setValidAmountFormat(window.opener.opener.document.getElementById("txtNetAmt"));
		
		
	}
	if(document.getElementById("hidnBillFlag").value=='L')
	{
		var rowCnt = document.getElementById("hidRowCnt").value;
		var arrearAmt = document.getElementById("hidRevArrearAmnt").value;
		window.opener.opener.document.getElementById("txtArrearAmt"+rowCnt).value = arrearAmt;
		setValidAmountFormat(window.opener.opener.document.getElementById("txtArrearAmt"+rowCnt));
			
	}
	window.close();
	window.opener.close();
}


	function saveArrearByAjax()
	{
		alert("in saveArrearByAjax");
		if(document.getElementById('btnSave').disabled==false)
		{
			document.getElementById('btnSave').disabled = true ;
		}
		
		xmlHttp=GetXmlHttpObject();
		if (xmlHttp==null)
		{
			alert ("Your browser does not support AJAX!!");
			return;
		} 
		
		var url = run(); 
		
		var uri = 'ifms.htm?actionFlag=saveRevisedArrearInfo';
		url = uri + url; 
		xmlHttp.onreadystatechange=billstateChanged;
		xmlHttp.open("POST",uri,false);
		xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xmlHttp.send(url);		
		
	}
	function billstateChanged() 
	{	
		if (xmlHttp.readyState==complete_state)
		{ 
			XMLDoc = xmlHttp.responseXML.documentElement;	
			if(XMLDoc != null)
			{				
				var XmlHiddenValues = XMLDoc.getElementsByTagName('RVSAMT');
				var lRvsArrFlg = XmlHiddenValues[0].childNodes[0].text;				
				var lRvsArrAmt = XmlHiddenValues[0].childNodes[1].text;
				window.returnValue = lRvsArrAmt;
				if(lRvsArrFlg != null && lRvsArrFlg == 'Y')
				{
					self.close();
				}
				else
				{	
					
					if(lRvsArrAmt < 0)	
					{
						alert("Revised Arrear less than 0. Could not be saved.");
					}	
					else
					{
						alert("Record saved successfully");
					}
				}
				
				self.close();			
			}		
		}
	}
	
	function printDotMatrix()
	{
		var cw = window.open('','',"height=768,width=1024,top=0,left=0,status=no,scrollbars=yes,toolbar=no,menubar=no,location=no");
		cw.document.write('<style media="print">');
	    cw.document.write('@FONT-FACE { font-family: sans-serif;}');
	    cw.document.write('@media print {BODY { font-size: 8pt }}');
	    cw.document.write('@media screen {BODY { font-size: 8pt } }');
	    cw.document.write('@media screen, print { BODY { line-height: 1.2 }}');
	    cw.document.write('@page cheque{ size 14in 12in; margin: 5cm }');
        cw.document.write('DIV {page: cheque; page-break-after: left; font-size: 8pt   }');
        cw.document.write('</style>');
        cw.document.write('<body><pre style="padding-left:15px;padding-top:15px;overflow:scroll;" >');  
	    var two = document.getElementById("lop").value.replace(/\f/g,'<div></div>');
	    cw.document.write(two);	
	    cw.document.write('</body><pre>');  
	    cw.document.write('<script language="javascript">');
		cw.document.write("window.print();");
		cw.document.write( '<' + "/script" + '>');
        cw.location.reload(false); 
	}
	
	function closePage()
	{	
	 	window.close();		
	}
	
	var oMyObject = window.dialogArguments;

	//revisedArrearCalcReport();
	
	function revisedArrearCalcReport()
	{
		xmlHttp = GetXmlHttpObject();		
		var url = new String(oMyObject).replace(/&undefined=undefined/g, "").replace(/&/i, "");
		
	    xmlHttp.onreadystatechange = getRevisedArrearCalcReport;
	    xmlHttp.open("POST", 'ifms.htm?actionFlag=calculateArrearForRevision' , true);
	    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	    xmlHttp.send(url);
	}
	
	function getRevisedArrearCalcReport()
	{
		if (xmlHttp.readyState == 4)
	    { 
	    	if(xmlHttp.status == 200)
			{
				XMLDoc = xmlHttp.responseXML.documentElement;
				
		 		if(XMLDoc != null)
		 		{
					XMLEntries = XMLDoc.getElementsByTagName("RevisedArrear");
					document.getElementById('hidPrintString').value = XMLEntries[0].childNodes[0].text;
					document.getElementById('lop').value = XMLEntries[0].childNodes[1].text;
					document.getElementById('hidPnsnrCode').value = XMLEntries[0].childNodes[2].text;
					document.getElementById('hidPensnReqId').value = XMLEntries[0].childNodes[3].text;
					document.getElementById('hidArrearAmnt').value = XMLEntries[0].childNodes[4].text;
					document.getElementById('SupplyFlg').value = XMLEntries[0].childNodes[5].text;
				}
				else
				{
					alert('Revised Arrear Calculation Report cannot be generated currently. Please contact Helpdesk.');
				}
			}
			else
			{
				alert('Revised Arrear Calculation Report cannot be generated currently. Please contact Helpdesk.');
			}
	    }
	}
	
	function GetXmlHttpObject()
	{
		var xmlHttp=null;
		try
	    {
	  		xmlHttp=new XMLHttpRequest();
	  	}
		catch (e)
	  	{
	  		try
	    	{
	    		xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
	    	}
	  		catch (e)
	    	{
	    		xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
	    	}
	  	}
		return xmlHttp;
	}
</script>
<%}catch(Exception e){e.printStackTrace();}%>