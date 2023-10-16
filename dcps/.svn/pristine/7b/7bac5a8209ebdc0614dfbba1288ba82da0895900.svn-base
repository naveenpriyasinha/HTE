function addDataRelatedToPayment()
{
	var totalArray=new Array("paymentplace","PriceFromPerticularSrc","savingsrc");
  	addOrUpdateRecord('showPaymentTable','addAssetPaymentrelatedData',totalArray);		
 	
}

function showPaymentTable()
{
	if (xmlHttp.readyState == 4)
	{		  		
	var decXml=xmlHttp.responseText;
	//alert(decXml);
		if(xmlHttp.status == 200)
		{
			var displayFieldArray=new Array("paymentplace","PriceFromPerticularSrc","savingsrc");
			addDataInTable("addSrcOfPaymentTable", 'encXML', displayFieldArray, "editPaymentData", "deletePaymentData", "") 
			document.getElementById('addSrcOfPaymentTable').style.display="";	
			resetPaymentData();
		 }
	}	
}

function editPaymentData(rowId)
{
	changeRowColor("addSrcOfPaymentTable",rowId,true);
	sendAjaxRequestForEdit(rowId, 'populatePaymentData');
}

function deletePaymentData(rowId)
{
	deleteRecord(rowId);
}

function resetPaymentData()
{
	document.form1.paymentplace.value = 0; 
	document.form1.PriceFromPerticularSrc.value = ""; 
	document.form1.savingsrc.value = ""; 
}

function populatePaymentData()
{
	if (xmlHttp.readyState == 4) { 	
	
		document.getElementById('addSrcButton').style.display="none";	
		document.getElementById('updateSrcButton').style.display="";	
	  	var decXML = xmlHttp.responseText;
	 	//alert(decXML);
		var xmlDOM = getDOMFromXML(decXML);
		//alert(xmlDOM);
		
		document.form1.paymentplace.value = getXPathValueFromDOM(xmlDOM, 'cmnLookupMst/lookupName');
		if(getXPathValueFromDOM(xmlDOM, 'investedPrice') == 0)
		{
			document.form1.PriceFromPerticularSrc.value = "";
		}
		else{
		document.form1.PriceFromPerticularSrc.value = getXPathValueFromDOM(xmlDOM, 'investedPrice');
		}
		document.form1.savingsrc.value = getXPathValueFromDOM(xmlDOM, 'descOfPayment');
		
	}
}

function updateDataRelatedToPayment()
{
	var totalArray=new Array("paymentplace","PriceFromPerticularSrc","savingsrc");
  	addOrUpdateRecord('showUpdatedPaymentTable','addAssetPaymentrelatedData',totalArray);	
}

function showUpdatedPaymentTable()
{
	if (xmlHttp.readyState == 4)
	{		  		
	var decXml=xmlHttp.responseText;
	//alert(decXml);
		if(xmlHttp.status == 200)
		{
			var displayFieldArray=new Array("paymentplace","PriceFromPerticularSrc","savingsrc");
			//addDataInTable("addSrcOfPaymentTable", 'encXML', displayFieldArray, "editPaymentData", "deletePaymentData", "") 
			updateDataInTable('encXML',displayFieldArray);
			document.getElementById('addSrcButton').style.display="";	
			document.getElementById('updateSrcButton').style.display="none";	
			resetPaymentData();
		 }
	}	
}