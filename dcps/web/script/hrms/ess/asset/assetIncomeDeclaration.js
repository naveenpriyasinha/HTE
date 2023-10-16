function getAllDataAccordingToYear(year)
{	//alert(year);
	try{   
    			xmlHttp=new XMLHttpRequest();	    	    
	    	}
			catch (e)
			{    // Internet Explorer    
					try{
      					xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");	      					   
      				}
		    		catch (e){
		          		try
        		  		{
                	           	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");   	                	             
        		  		}
				      	catch (e)
				      	{
       	   	  	           	  
			            	  return false;        
			      		}
			 	}
        	}
        	var url = "hdiits.htm?actionFlag=getAllAssetIncomeDtlsOfYear&year="+year;    
			xmlHttp.open("POST", encodeURI(url) , true);			
			xmlHttp.onreadystatechange = showAllDetailsOfYear;
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));
}
	
function showAllDetailsOfYear()
{
	if (xmlHttp.readyState == 4) 
	{    // alert(xmlHttp.status);
		if (xmlHttp.status == 200) 
		{	
			var xmlStr = xmlHttp.responseText;//alert(xmlStr);
			var XMLDoc=getDOMFromXML(xmlStr);
					
			
			
			var totalRow = XMLDoc.getElementsByTagName('rowId')[0].childNodes[0].text;
			var year = XMLDoc.getElementsByTagName('year')[0].childNodes[0].text;
			
			if(totalRow == 1 && year == document.form1.hiddenThirdPrvYear.value)
			{
				document.getElementById('noRecordForThirdPrvYear').style.display="";
			}
			
			if(totalRow == 1 && year == document.form1.hiddenSecondPrvYear.value)
			{
				document.getElementById('noRecordForSecondPrvYear').style.display="";
			}
			
			if(totalRow == 1 && year == document.form1.hiddenFirstPrvYear.value)
			{
				document.getElementById('noRecordForFirstPrvYear').style.display="";
			}
			
			if(year == document.form1.hiddenFirstPrvYear.value)
			{
				var num = document.getElementById('firstPrvYearDataTable').rows.length;
		
				if(num>1)
				{
					for(var i=1;i<num;i++)
					{
						document.getElementById('firstPrvYearDataTable').deleteRow();
					}
				}
			
			}
			
			if(year == document.form1.hiddenSecondPrvYear.value)
			{
				var num = document.getElementById('secondPrvYearDataTable').rows.length;
		
				if(num>1)
				{
					for(var i=1;i<num;i++)
					{
						document.getElementById('secondPrvYearDataTable').deleteRow();
					}
				}
			
			}
			
			if(year == document.form1.hiddenThirdPrvYear.value)
			{
				var num = document.getElementById('thirdPrvYearDataTable').rows.length;
		
				if(num>1)
				{
					for(var i=1;i<num;i++)
					{
						document.getElementById('thirdPrvYearDataTable').deleteRow();
					}
				}
			}
			
			var assetId1 = XMLDoc.getElementsByTagName('assetId');
			var assetName1 = XMLDoc.getElementsByTagName('assetName');
			var purchasedDate1 = XMLDoc.getElementsByTagName('purchasedDate');
			var sellDate1 = XMLDoc.getElementsByTagName('sellDate');
			var marketPrice1 = XMLDoc.getElementsByTagName('marketPrice');
			var annualIncome1 = XMLDoc.getElementsByTagName('annualIncome')
			
			for(var i = 0; i<totalRow-1; i++)
			{	
				
				var temp = XMLDoc.getElementsByTagName('XmlFileName');
				var xmlValue = temp[i].childNodes[0].text;
				
				var assetId = assetId1[i].childNodes[0].text;
				var assetName = assetName1[i].childNodes[0].text;
				var purchasedDate = purchasedDate1[i].childNodes[0].text;
				var sellDate;
				if(sellDate1[i].childNodes[0].text == "null")
				{
					sellDate = "-";
				}
				else{
				sellDate = sellDate1[i].childNodes[0].text;
				}
				var marketPrice = marketPrice1[i].childNodes[0].text;
				var annualIncome = annualIncome1[i].childNodes[0].text;
				
				var displayFieldArray=new Array(assetId,assetName,purchasedDate,sellDate,marketPrice,annualIncome);
				if(year == document.form1.hiddenFirstPrvYear.value)
				{	
					addDBDataInTable("firstPrvYearDataTable",'encXML',displayFieldArray,xmlValue,"","","")
					document.getElementById('firstPrvYearDataTable').style.display="";
				}	
				if(year == document.form1.hiddenSecondPrvYear.value)
				{
					addDBDataInTable("secondPrvYearDataTable",'encXML',displayFieldArray,xmlValue,"","","")
					document.getElementById('secondPrvYearDataTable').style.display="";
				}
				if(year == document.form1.hiddenThirdPrvYear.value)
				{
					addDBDataInTable("thirdPrvYearDataTable",'encXML',displayFieldArray,xmlValue,"","","")
					document.getElementById('thirdPrvYearDataTable').style.display="";
				}
			}	
			
		}
	}		
	
	
}	

function addAssetDataForIncome()
{	
	
	var assetRowId = document.getElementById('assetRowId').value;
	var delRow = document.getElementById(assetRowId);
	delRow.parentNode.deleteRow(delRow.rowIndex);
	
	var totalArray=new Array("assetId","assetType","assetName","ownerName","marketPrice","annualIncome","remarks","declaredYear");
    addOrUpdateRecord('showApprovedIncomeData','addAssetIncomeData',totalArray);	
 
}

function showApprovedIncomeData()
{
	if (xmlHttp.readyState == 4)
	{		  		
	var decXml=xmlHttp.responseText;
	//alert(decXml);
		if(xmlHttp.status == 200)
		{
			var displayFieldArray=new Array("assetId","assetType","assetName","ownerName","marketPrice","annualIncome","remarks");
			addDataInTable("selectedIncomeDataTable", 'encXML', displayFieldArray, "editIncomeData", "deleteIncomeData", "") 
			document.getElementById('selectedIncomeDataTable').style.display='';	
			document.getElementById('incomeSubmitClose').style.display='';	
			document.getElementById('marketPrice').value = "";
			document.getElementById('annualIncome').value = "";
			document.getElementById('remarks').value = "";
			
			if(document.getElementById('incomeDataTable').rows.length ==1)
			{
				document.getElementById('incomeDataTable').style.display="none";
				document.getElementById('incomeData').style.display="none";
				document.getElementById('incomeAddButton').style.display="none";
				
					
			}
		
     	
		}
	}
}

function editIncomeData(rowId)
{	//alert(rowId);
	sendAjaxRequestForEdit(rowId, 'populateAllData');
}
function populateAllData()
{
	if (xmlHttp.readyState == 4) { 	
	
		document.getElementById('incomeAddButton').style.display="none";	
		document.getElementById('incomeUpdateButton').style.display="";	
		
		var decXML = xmlHttp.responseText;
	 	var xmlDOM = getDOMFromXML(decXML);
		//alert(xmlDOM);
		document.getElementById('marketPrice').value = getXPathValueFromDOM(xmlDOM, 'marketPrice');
		document.getElementById('annualIncome').value = getXPathValueFromDOM(xmlDOM, 'annualIncome');
		document.getElementById('remarks').value = getXPathValueFromDOM(xmlDOM, 'remarks');
		
		
	}
}		
function deleteIncomeData(rowId)
{
	deleteRecord(rowId);
}

function updateIncomeData()
{
	var totalArray=new Array("assetId","assetType","assetName","ownerName","marketPrice","annualIncome","remarks","declaredYear");
   	addOrUpdateRecord('updateIncomeRecord','addAssetIncomeData',totalArray);	
}

function updateIncomeRecord()
{
	 if (xmlHttp.readyState == 4)
	  {
	  	var displayFieldArray=new Array("assetId","assetType","assetName","ownerName","marketPrice","annualIncome","remarks");
	  	updateDataInTable('encXML',displayFieldArray);
	  	document.getElementById('marketPrice').value = "";
		document.getElementById('annualIncome').value = "";
		document.getElementById('remarks').value = "";
		document.getElementById('incomeAddButton').style.display="";	
		document.getElementById('incomeUpdateButton').style.display="none";	
	  
	  }
}

