/**
 * FILENAME      : policeStationSearch.js
 * @VERSION      : 1.0
 * @AUTHOR       : Jay Parikh
 * DATE          : 2nd January 2008
 *
 * REV. HISTORY :
 *--------------------------------------------------------------------------------------------------------
 
 *     DATE              AUTHOR               DESCRIPTION
 *
 * 2nd January 2008     Jay				  
 												
 *--------------------------------------------------------------------------------------------------------1
 */
var xmlHttp;

function GetXmlHttpObject()
{
//alert("GetXmlHttpObject");
try
  {
  // Firefox, Opera 8.0+, Safari
  xmlHttp=new XMLHttpRequest();
  }
catch (e)
  {
  // Internet Explorer
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



function checkForMode()
{
//alert("checkForMode");
		var licenseId = document.getElementById("license").value;
		var modeId    = document.getElementById("mode").value;
		var effectiveDate =document.getElementById("txtEffectDate").value;
		
		if(modeId == -1)
		{
			alert("Please Select Application type.");
			document.getElementById("txtEffectDate").value=""
			return;
		}
			
//		alert(effectiveDate)
		var url='./ifms.htm?actionFlag=SPC_ModeMappingSearch&modeId='+modeId+'&licenseId='+licenseId+'&effectiveDate='+effectiveDate;
//			alert(url);
		   xmlHttp = GetXmlHttpObject();
		   if (xmlHttp==null) {
				  alert ("Your browser does not support AJAX!");
				 return;
			}
 		   xmlHttp.onreadystatechange=stateChangedForCheckMode
		   xmlHttp.open("POST",encodeURI(url),true);

		   xmlHttp.send(url);
}



function stateChangedForCheckMode() 
	{
//	alert("stateChangedForCheckMode");
//		alert("2");
		if(xmlHttp.readyState==4)
		{
			if(xmlHttp.status == 200)
			{
//			alert(xmlHttp.responseText);
			   	if(xmlHttp.responseText == 'ERROR')
			   	{
//					alert('Request Processing Failed');
					return;
				}
				var XMLDoc=xmlHttp.responseXML.documentElement;

		
			   	var len = setModeValue(XMLDoc);

            }
			else
			{
//				alert("ERROR");
			}
		}
	}

function setModeValue(XMLDoc)
{
 	//alert("abbb:::::");


      	var entriesSheet = XMLDoc.getElementsByTagName('elementSheet');
      	var entryExists =XMLDoc.getElementsByTagName('entryExists');
	    var i = 0;
		var len;
//		alert("document.getElementById(cmbName)"+document.getElementById(cmbName));
//		alert("len"+len);

	   	if(entriesSheet.length==0)
		{
			return 0;
		}
//		alert("try"+entriesSheet[0].childNodes[2].text)
//		alert("try121"+entriesSheet[0].childNodes[3].text)
		var x=document.getElementById('fees');
		if(entryExists[0].text=='true')
		{
				
			//alert("sdsdf "+x);
		      	var elementFee = XMLDoc.getElementsByTagName('elementFee');
		    	var elementValidity = XMLDoc.getElementsByTagName('elementValidity');
			   	var elementYearDesc = XMLDoc.getElementsByTagName('elementYearDesc');
			   	var elementYearName = XMLDoc.getElementsByTagName('elementYearName');
			   	var mapSrno = XMLDoc.getElementsByTagName('mapSrno');
				var mapSrnoSecond = XMLDoc.getElementsByTagName('mapSrnoSecond');
			   	var elementValidityCmb = XMLDoc.getElementsByTagName('elementValidityCmb');
		    	var elementEffectiveDt = XMLDoc.getElementsByTagName('elementEffectiveDt');
			   	var elementUnit = XMLDoc.getElementsByTagName('elementUnit');
			   	var elementGrNo = XMLDoc.getElementsByTagName('elementGrNo');			   	
			   	
			   	if(elementFee[0]!=null)
					document.getElementById('LicenseFees').value=elementFee[0].text;
			   	if(elementValidity[0]!=null)
					document.getElementById('Validity').value=elementValidity[0].text;
			   	if(elementYearName[0]!=null)				
					x.value=elementYearName[0].text;
			   	if(mapSrno[0]!=null)				
					document.getElementById('hdnMapSrno').value=mapSrno[0].text;
				if(mapSrnoSecond[0]!=null)				
					document.getElementById('hdnMapSrnoSecond').value=mapSrnoSecond[0].text;
			   	if(elementValidityCmb[0]!=null)				
					document.getElementById('hdnEntryExists').value=entryExists[0].text;
			   	if(elementValidityCmb[0]!=null)				
			   	{
					document.getElementById('validityPeriod').value=elementValidityCmb[0].text;
					showValidity()
					if(elementValidityCmb[0].text!= "Up to Calendar Year")
					{
						document.getElementById('Validity').value='0';
						document.getElementById('fees').selectedIndex=0;
					}
				}
				
			   	if(elementEffectiveDt[0]!=null)				
				{
					var res = getDateAndTimeFromDateObj(elementEffectiveDt[0].text);
					document.getElementById('txtEffectDate').value= res[0];
				}
			   	if(elementUnit[0]!=null)				
					document.getElementById('cmnUnit').value=elementUnit[0].text;
			   	if(elementGrNo[0]!=null)				
					document.getElementById('txtGrNo').value=elementGrNo[0].text;
			   	
			   					
		//		alert("document.getElementById('hdnEntryExists').value:::::"+document.getElementById('hdnEntryExists').value)
		//		alert("document.getElementById('hdnMapSrno').value:::::"+document.getElementById('hdnMapSrno').value)
		//		document.getElementById('year').value=entriesSheet[0].childNodes[2].text;
		//		document.getElementById('year').text=entriesSheet[0].childNodes[3].text;

		}
		else if(entryExists[0].text=='false')
		{
				document.getElementById('LicenseFees').value="";
				document.getElementById('Validity').value="";
				x.selectedIndex=0
				document.getElementById('validityPeriod').selectedIndex=0;
				document.getElementById('cmnUnit').selectedIndex=0;
				document.getElementById('hdnEntryExists').value=entryExists[0].text;
				document.getElementById('txtGrNo').value="";
		}
}

