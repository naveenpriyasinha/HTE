<script type="text/javascript">
function getPageDetails()
{
	var uri = 'ifms.htm?actionFlag=getLoginPageDetails';
	var url = '';
	
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function (myAjax) {						
					getResponsePageDetails(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );		
}

function getResponsePageDetails(myAjax)
{
	var XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var DDO = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	var EMP = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;
	var BILL = XmlHiddenValues[0].childNodes[2].firstChild.nodeValue;
	var EMPBILL = XmlHiddenValues[0].childNodes[3].firstChild.nodeValue;
	var month = XmlHiddenValues[0].childNodes[4].firstChild.nodeValue;
	var year = XmlHiddenValues[0].childNodes[5].firstChild.nodeValue;
	var login = XmlHiddenValues[0].childNodes[6].firstChild.nodeValue;
		
	document.getElementById('divDDO').innerHTML = DDO;
	document.getElementById('divEMP').innerHTML = EMP;
	document.getElementById('divBills').innerHTML = BILL;
	document.getElementById('divEmpInBill').innerHTML = EMPBILL;	
	document.getElementById('lBlMonth').innerHTML = month;	
	document.getElementById('lBlYear').innerHTML = year;
	document.getElementById('lBlMonth1').innerHTML = month;
	document.getElementById('lBlYear1').innerHTML = year;
	document.getElementById('divUsrLogIn').innerHTML = login;
}
</script>

<table align="center" border="1">
	<tr>	
		<td colspan="6" ><font
					style="font-size: 13px; font-weight: bold; color: #FF3333;">
			Total No. of Users Loged In: </font>
		</td>
		<td>
			<div id="divUsrLogIn" style="display: inline" style="font-weight: bold;"></div>
		</td>
	</tr>
	<tr>
		<td colspan="6" ><font
					style="font-size: 13px; font-weight: bold; color: #FF3333;">
			Total No Of DDOs: </font>
		</td>
		<td>
			<div id="divDDO" style="display: inline" style="font-weight: bold;"></div>
		</td>
	</tr>
	<tr>
		<td colspan="6" ><font
					style="font-size: 13px; font-weight: bold; color: #FF3333;">
			Total No Of Employees Covered: </font>
		</td>
		<td>
			<div id="divEMP" style="display: inline" style="font-weight: bold;"></div>
		</td>
	</tr>
	<tr>
		<td colspan="6" ><font
					style="font-size: 13px; font-weight: bold; color: #FF3333;">
			Total No Of Bills Drawn (<label id="lBlMonth"></label>-<label id="lblYear"></label>): </font>
		</td>
		<td>
			<div id="divBills" style="display: inline" style="font-weight: bold;"></div>
		</td>
	</tr>
	<tr>	
		<td colspan="6" ><font
					style="font-size: 13px; font-weight: bold; color: #FF3333;">
			Total No. of Employees Covered in Bills (<label id="lBlMonth1"></label>-<label id="lblYear1"></label>): </font>
		</td>
		<td>
			<div id="divEmpInBill" style="display: inline" style="font-weight: bold;"></div>
		</td>
	</tr>
</table>

<script>
	getPageDetails();
</script>