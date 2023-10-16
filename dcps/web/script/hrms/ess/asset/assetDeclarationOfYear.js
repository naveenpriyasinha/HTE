

function getAllAssetDataOfSelectedYear(l)
{
	if(l.value == 0)
	{
		document.getElementById('movableAssetDataTable').style.display="none";	
		document.getElementById('movableAssetDataLable').style.display="none";	
		var num = document.getElementById('movableAssetDataTable').rows.length;
		
		if(num>1)
		{
			for(var i=1;i<num;i++)
			{
				document.getElementById('movableAssetDataTable').deleteRow();
			}
		}
	}	
	var year = l.value;
	document.getElementById('year').value = year;
	if(year == '')
	{
		return;
	}
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
        	var url = "hdiits.htm?actionFlag=getAllAssetDeclarationDtlsOfYear&year="+year;    
			xmlHttp.open("POST", encodeURI(url) , true);			
			xmlHttp.onreadystatechange = allDetailsOfAssetDeclarationYear;
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));			
}


function allDetailsOfAssetDeclarationYear()
{
	if (xmlHttp.readyState == 4) 
	{    // alert(xmlHttp.status);
		if (xmlHttp.status == 200) 
		{	
			var xmlStr = xmlHttp.responseText;//alert(xmlStr);
			var XMLDoc=getDOMFromXML(xmlStr);
					
			
			
			var totalRow = XMLDoc.getElementsByTagName('rowId')[0].childNodes[0].text;
			//alert(totalRow);
			var year = XMLDoc.getElementsByTagName('year')[0].childNodes[0].text;
			//alert(totalRow);
			if(totalRow != 0)
			{
				var assetId1 = XMLDoc.getElementsByTagName('assetId');
				var assetName1 = XMLDoc.getElementsByTagName('assetName');
				var assetTypeLookupName1 = XMLDoc.getElementsByTagName('assetTypeLookupName');
				var registrationNo1 = XMLDoc.getElementsByTagName('registrationNo');
				var assetAddress1 = XMLDoc.getElementsByTagName('assetAddress');
				var purchasedDate1 = XMLDoc.getElementsByTagName('purchasedDate');
				var sellDate1 = XMLDoc.getElementsByTagName('sellDate');
				
				for(var i = 0; i<totalRow-1; i++)
				{	
					
					var temp = XMLDoc.getElementsByTagName('XmlFileName');
					var xmlValue = temp[i].childNodes[0].text;
					
					if(assetTypeLookupName1[i].childNodes[0].text == "Movable_asset")
					{
						var assetId = assetId1[i].childNodes[0].text;
						var assetName = assetName1[i].childNodes[0].text;
						var registrationNo = registrationNo1[i].childNodes[0].text;
						var purchasedDate = purchasedDate1[i].childNodes[0].text;
						var sellDate;
						if(sellDate1[i].childNodes[0].text == "null")
						{
							sellDate = "";
						}
						else{
						sellDate = sellDate1[i].childNodes[0].text;
						}
					
						var displayFieldArray=new Array(assetId,assetName,registrationNo,purchasedDate,sellDate);
						addDBDataInTable("movableAssetDataTable",'encXML',displayFieldArray,xmlValue,"","","")
						document.getElementById('movableAssetDataTable').style.display="";
						document.getElementById('movableAssetDataLable').style.display="";
					}
					
		/*		if(assetTypeLookupName1[i].childNodes[0].text == "Fixed_asset")	
				{
					var assetId = assetId1[i].childNodes[0].text;
					var assetName = assetName1[i].childNodes[0].text;
					var assetAddress = assetAddress1[i].childNodes[0].text;
					var purchasedDate = purchasedDate1[i].childNodes[0].text;
					var sellDate;
					if(sellDate1[i].childNodes[0].text == "null")
					{
						sellDate = "";
					}
					else{
					sellDate = sellDate1[i].childNodes[0].text;
					}
					
					var displayFieldArray=new Array(assetId,assetName,assetAddress,purchasedDate,sellDate);
					addDBDataInTable("fixedAssetDataTable",'encXML',displayFieldArray,xmlValue,"","","")
					document.getElementById('fixedAssetDataTable').style.display="";
					document.getElementById('FixedAssetDataLable').style.display="";
				}          */
			}	
			
			}	
			else{
				document.getElementById('recordForYear').style.display="";
			}
		}
	}		
}
