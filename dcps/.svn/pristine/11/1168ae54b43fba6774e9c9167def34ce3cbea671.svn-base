var Row_ID_Rev=0;
var Row_ID_Alloc=0;

function revisionDtlsTableAddRow()
{	
	Row_ID_Rev = document.getElementById("hdnRevGridSize").value;
	//var newRow =  document.all("tblRevisionDtls").insertRow();	
	
	var table=document.getElementById("tblRevisionDtls");
	var rowCount=table.rows.length;
	var newRow = table.insertRow(rowCount); 
	
	var Cell1 = newRow.insertCell(0);
   	Cell1.className = "tds";
   	Cell1.align="center";
   	var Cell2 = newRow.insertCell(1);
   	Cell2.className = "tds";
   	Cell2.align="center";
   	var Cell3 = newRow.insertCell(2);
   	Cell3.className = "tds";
   	Cell3.align="center";
   	var Cell4 = newRow.insertCell(3);
	Cell4.className = "tds";
   	Cell4.align="center";
   	var Cell5 = newRow.insertCell(4);
	Cell5.className = "tds";
   	Cell5.align="center";
   	var Cell6 = newRow.insertCell(5);
	Cell6.className = "tds";
   	Cell6.align="center";
   	var Cell7 = newRow.insertCell(6);
	Cell7.className = "tds";
   	Cell7.align="center";
   	var Cell8 = newRow.insertCell(7);
	Cell8.className = "tds";
   	Cell8.align="center";
   	var Cell9 = newRow.insertCell(8);
	Cell9.className = "tds";
   	Cell9.align="center";
   	 
   	Cell1.innerHTML = '<select name="cmbRopType" id="cmbRopType'+Number(Row_ID_Rev)+'" ><option value="-1">--Select--</option>'+ LISTROPTYPE +'</select>'
   						+ '<input type="hidden" name="hdnRevisionFlag" id="hdnRevisionFlag'+Number(Row_ID_Rev)+'"/>';
   	Cell2.innerHTML = '<input type="text" name="txtRevisionDate" id="txtRevisionDate'+Row_ID_Rev+'" style="width:90px" maxlength="10" size="15" onkeypress="digitFormat(this);dateFormat(this);" onblur="chkValidDate(this);"/> <img id="imgReceivedDate'+Row_ID_Rev+'" src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtRevisionDate'+Row_ID_Rev+'\', 375, 570, \'\', \'\', '+Row_ID_Rev+');" />';	
   	Cell3.innerHTML = '<input type="text" name="txtPensionSanctionAmt" id="txtPensionSanctionAmt'+Row_ID_Rev+'" size="15" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>';
   	Cell4.innerHTML = '<input type="text" name="txtCvpAmount" id="txtCvpAmount'+Row_ID_Rev+'" size="15" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>';      	
   	Cell5.innerHTML = '<input type="text" name="txtCvpMonthlyAmount" id="txtCvpMonthlyAmount'+Row_ID_Rev+'" size="15" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>';   	   	
   	Cell6.innerHTML = '<input type="text" name="txtFp1Amount" id="txtFp1Amount'+Row_ID_Rev+'" size="15" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>';
   	Cell7.innerHTML = '<input type="text" name="txtFp2Amount" id="txtFp2Amount'+Row_ID_Rev+'" size="15" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>';
   	Cell8.innerHTML = '<input type="text" name="txtDcrgAmount" id="txtDcrgAmount'+Row_ID_Rev+'" size="15" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>';
   	Cell9.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, \'tblRevisionDtls\')" /> ';
   	document.getElementById("hdnRevGridSize").value = Number(Row_ID_Rev)+1; 
}


//function addRowAllocation()
//{			
//	++Row_ID_Alloc;			  		
//	var newRow = document.all("TableAllocation").insertRow(Row_ID_Alloc);  	
//   	var Cell1 = newRow.insertCell();
//   	Cell1.className = "pensionLabels";
//   	Cell1.align="center";
//   	var Cell2 = newRow.insertCell();	
//   	Cell2.className = "pensionLabels";
//   	Cell2.align="center";
//   	var Cell3 = newRow.insertCell();
//   	Cell3.className = "pensionLabels";
//   	Cell3.align="center";
//   	var Cell4 = newRow.insertCell();
//	Cell4.className = "pensionLabels";
//   	Cell4.align="center";
//   	var Cell5 = newRow.insertCell();
//	Cell5.className = "pensionLabels";
//   	Cell5.align="center";
//   	var Cell6 = newRow.insertCell();
//	Cell6.className = "pensionLabels";
//   	Cell6.align="center";
//   	var Cell7 = newRow.insertCell();
//	Cell7.className = "pensionLabels";
//   	Cell7.align="center";   	
//   	 
//   	Cell1.innerHTML = '<input type="text" name="txtPension'+Row_ID_Alloc+'" id="txtPension'+Row_ID_Alloc+'" size="15" />';
//	Cell2.innerHTML = '<input type="text" name="txtBefore1936'+Row_ID_Alloc+'" id="txtBefore1936'+Row_ID_Alloc+'" size="15" />';	
//   	Cell3.innerHTML = '<input type="text" name="txtAfter1936'+Row_ID_Alloc+'" id="txtAfter1936'+Row_ID_Alloc+'" size="15" />';
//   	Cell4.innerHTML = '<input type="text" name="txtAfter1956'+Row_ID_Alloc+'" id="txtAfter1956'+Row_ID_Alloc+'" size="15" />';      	
//   	Cell5.innerHTML = '<input type="text" name="txtAfter1960'+Row_ID_Alloc+'" id="txtAfter1960'+Row_ID_Alloc+'" size="15" />';   	   	
//   	Cell6.innerHTML = '<input type="text" name="txtAllocation5'+Row_ID_Alloc+'" id="txtAllocation5'+Row_ID_Alloc+'" size="15" />';   	
//   	Cell7.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRowAlloc(this, \'TableAllocation\')" /> ';
//   	assignTab();   	
//}

function SaveRevisionData(revisionFlag)
{
	
        var uri='ifms.htm?actionFlag=insertPensionerRevisionDtls';
    	showProgressbar();
		saveRevisionDataUsingAjx(uri,revisionFlag);
  
}

 
function saveRevisionDataUsingAjx(uri,revisionFlag)
{
	var url = runForm(0); 
	url = url + '&RevisionFlag='+revisionFlag;
	
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters: url,
		        onSuccess: function(myAjax) {
					saveOnStateChanged(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
	
	
	
//   xmlHttp=GetXmlHttpObject();
//
//   if (xmlHttp==null)
//   {
//      return;
//   }  
//        
//   var url = runForm(0); 
//   url = uri + url;
//  
//   xmlHttp.onreadystatechange=caseStateChanged;
//   xmlHttp.open("POST",uri,true);
//   xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
//   xmlHttp.send(url);
   
}


function saveOnStateChanged(myAjax) 
{ 
	 var XMLDoc =  myAjax.responseXML.documentElement;
	 
	 if(XMLDoc != null)
	 {
		 var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
		 if(XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue == 'insert')
   	     {
			 alert("Revision detail Saved Successfully.");
   	     }
   	     if(XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue == 'update' && XmlHiddenValues[0].childNodes[2].childNodes[0].nodeValue == 'S')
   	     {
   	    	 alert("Revision detail Updated Successfully.");
   	     }
   	     if(XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue=='update' && XmlHiddenValues[0].childNodes[2].childNodes[0].nodeValue == 'A')
   	     {
	   		  alert("Revision Accomodated Successfully.");
	   		  self.location.reload();
	   		  var rowCnt=document.getElementById("hdnRevGridSize").value;
	   		  rowCnt=rowCnt-1;
	   		  window.opener.document.getElementById("cmbRopType").value=document.getElementById("cmbRopType"+rowCnt).value;
	   		  window.opener.document.getElementById("txtBasicPensionAmt").value=document.getElementById("txtPensionSanctionAmt"+rowCnt).value;
	   		  window.opener.document.getElementById("txtTotalCvpAmount").value=document.getElementById("txtCvpAmount"+rowCnt).value;
	   		  window.opener.document.getElementById("txtCvpMonthly").value=document.getElementById("txtCvpMonthlyAmount"+rowCnt).value;
	   		  window.opener.document.getElementById("txtFp1amount").value=document.getElementById("txtFp1Amount"+rowCnt).value;
	   		  window.opener.document.getElementById("txtFp2amount").value=document.getElementById("txtFp2Amount"+rowCnt).value;
	   		  window.opener.document.getElementById("txtTotalDcrgAmount").value=document.getElementById("txtDcrgAmount"+rowCnt).value;
	   		 
	   		  if(document.getElementById("cmbRopType"+rowCnt).value == "1996")
	   		  {
	   			  window.opener.document.getElementById("radioDpMergeY").disabled=false;
	   			  window.opener.document.getElementById("radioDpMergeN").disabled=false;
	   			  window.opener.document.getElementById("radioDpMergeY").checked=false;
	   			  window.opener.document.getElementById("radioDpMergeN").checked=false;
	   			  window.opener.document.getElementById("chkFpForTenYears").disabled=true;
	   			  window.opener.document.getElementById("chkFpForTenYears").checked=false;
	   		  	
	   		  }
	   		  else if(document.getElementById("cmbRopType"+rowCnt).value == "2006")
	   		  {
	   			  window.opener.document.getElementById("radioDpMergeY").disabled=true;
	   			  window.opener.document.getElementById("radioDpMergeN").disabled=true;
	   			  window.opener.document.getElementById("radioDpMergeY").checked=false;
	   			  window.opener.document.getElementById("radioDpMergeN").checked=false;
	   			  window.opener.document.getElementById("chkFpForTenYears").disabled=false;
	   			  window.opener.document.getElementById("chkFpForTenYears").checked=false;
	   			  window.opener.document.getElementById("txtDpRate").value="0.00";
	   			  window.opener.document.getElementById("txtDpAmount").value="0.00";
	   		  	
	   		  }
	   		  
	   	  }
	      hideProgressbar();
   	     
   	 }
	
//   if (xmlHttp.readyState==complete_state)
//   { 
//	   	  var XMLDoc=xmlHttp.responseXML.documentElement;
//	   	 
//    	  var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
//     	
//    	  if(XmlHiddenValues[0].childNodes[0].text=='insert')
//    	  {
//    		  alert("Revision detail Saved Successfully.");
//    	  }
//    	  if(XmlHiddenValues[0].childNodes[0].text=='update' && XmlHiddenValues[0].childNodes[2].text == 'S')
//    	  {
//    		  alert("Revision detail Updated Successfully.");
//    	  }
//    	  if(XmlHiddenValues[0].childNodes[0].text=='update' && XmlHiddenValues[0].childNodes[2].text == 'A')
//    	  {
//    		  alert("Revision Accomodated Successfully.");
//    		  self.location.reload();
//    		  var rowCnt=document.getElementById("hdnRevGridSize").value;
//    		  rowCnt=rowCnt-1;
//    		  window.opener.document.getElementById("cmbRopType").value=document.getElementById("cmbRopType"+rowCnt).value;
//    		  window.opener.document.getElementById("txtBasicPensionAmt").value=document.getElementById("txtPensionSanctionAmt"+rowCnt).value;
//    		  window.opener.document.getElementById("txtCvpAmount").value=document.getElementById("txtCvpAmount"+rowCnt).value;
//    		  window.opener.document.getElementById("txtCvpMonthly").value=document.getElementById("txtCvpMonthlyAmount"+rowCnt).value;
//    		  window.opener.document.getElementById("txtFp1amount").value=document.getElementById("txtFp1Amount"+rowCnt).value;
//    		  window.opener.document.getElementById("txtFp2amount").value=document.getElementById("txtFp2Amount"+rowCnt).value;
//    		  window.opener.document.getElementById("txtTotalDcrgAmount").value=document.getElementById("txtDcrgAmount"+rowCnt).value;
//    		  
//    		  if(document.getElementById("cmbRopType"+rowCnt).value == "1996")
//    		  {
//    			  window.opener.document.getElementById("radioDpMergeY").disabled=false;
//    			  window.opener.document.getElementById("radioDpMergeN").disabled=false;
//    			  window.opener.document.getElementById("radioDpMergeY").checked=false;
//    			  window.opener.document.getElementById("radioDpMergeN").checked=false;
//    			  window.opener.document.getElementById("chkFpForTenYears").disabled=true;
//    			  window.opener.document.getElementById("chkFpForTenYears").checked=false;
//    		  	
//    		  }
//    		  else if(document.getElementById("cmbRopType"+rowCnt).value == "2006")
//    		  {
//    			  window.opener.document.getElementById("radioDpMergeY").disabled=true;
//    			  window.opener.document.getElementById("radioDpMergeN").disabled=true;
//    			  window.opener.document.getElementById("radioDpMergeY").checked=false;
//    			  window.opener.document.getElementById("radioDpMergeN").checked=false;
//    			  window.opener.document.getElementById("chkFpForTenYears").disabled=false;
//    			  window.opener.document.getElementById("chkFpForTenYears").checked=false;
//    			  window.opener.document.getElementById("txtDpRate").value="0.00";
//    			  window.opener.document.getElementById("txtDpAmount").value="0.00";
//    		  	
//    		  }
//    		  
//    	  }
//     	  hideProgressbar();
//   }
}

