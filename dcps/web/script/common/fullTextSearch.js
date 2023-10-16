function allCbClicked(allCb) {
	var cboxes = document.getElementsByTagName('input');
	for(var i=0; i<cboxes.length; i++) {				
		if(cboxes[i].type == 'checkbox' && cboxes[i].name != 'fts_All') {			
			if(allCb.checked) {
				cboxes[i].checked = true;
			}
			else {
				cboxes[i].checked = false;
			}	
		}
	}
}

function indiCbClicked() {	
	var allCbChecked = true;
	var cboxes = document.getElementsByTagName('input');
	for(var i=0; i<cboxes.length; i++) {				
		if(cboxes[i].type == 'checkbox' && cboxes[i].name != 'fts_All') {			
			if(!(cboxes[i].checked)) {
				allCbChecked = false;
				break;
			}
		}
	}
	
	var allCb = document.getElementById("fts_All");
	if(allCbChecked)
		allCb.checked = true;
	else
		allCb.checked = false;			
}	

function notImpl() {
	alert('Not Implemented...');
}


function sendAllSearchRequest(contextPath, queryString, cboxes, mulCB) {	

	var subUrl;
	var cVal;
	var allSelect = false;
	if(mulCB) {
		cVal = document.getElementById("fts_All").value;
		subUrl = contextPath + "/hdiits.htm?actionFlag=reportService&reportCode=" + cVal + "&action=generateReport&FromParaPage=TRUE";	
		for(var i=0; i<cboxes.length; i++) 
		{				
			var repCodeHiddenObj = document.getElementById("repCode_" + cboxes[i].name);

			if(cboxes[i].type == 'checkbox' && cboxes[i].checked && cboxes[i].name != 'fts_All') {	
				subUrl = subUrl + "&subReportCode=" + repCodeHiddenObj.value + "&subReportType=" + cboxes[i].id
				+ "&indexDir=" + cboxes[i].value; 
			}	
			if(cboxes[i].name == 'fts_All' && cboxes[i].type == 'checkbox' && cboxes[i].checked)
			{
				allSelect = true;
			}

		}
		subUrl = subUrl + "&searchAll=true&advanceSearch=Y" + "&allselect="+allSelect;
	}			
	else {
		for(var i=0; i<cboxes.length; i++) {				
			if(cboxes[i].type == 'checkbox' && cboxes[i].checked) {
				cVal = cboxes[i].value;	
				var custUrlHiddenObj = document.getElementById("custUrl_" + cboxes[i].name);
				var repCodeHiddenObj = document.getElementById("repCode_" + cboxes[i].name);					
				
				//alert('hiddenObj : ' + hiddenObj);
				if(custUrlHiddenObj.value != 'null') {
					subUrl = contextPath + "/" + custUrlHiddenObj.value
							 + "&fromFTSearch=Y&advanceSearch=Y" + "&indexDir=" + cVal;					
				}
				else if(repCodeHiddenObj.value != 'null') {
					subUrl = contextPath + "/ifms.htm?actionFlag=reportService&reportCode=" + repCodeHiddenObj.value 
						 	+ "&action=generateReport&FromParaPage=TRUE&advanceSearch=Y" + "&indexDir=" + cVal;
				}
									
				break;
			}
		}
	}
	subUrl = subUrl + "&okButtonUrl=" + queryString;
	//alert("Final URL :" + subUrl);
	showProgressbar();		
	document.forms['frmFullTextSearch'].action = subUrl;
	document.forms['frmFullTextSearch'].submit();
}

function sendSingleSearchRequest(contextPath) {
	var radios = document.getElementsByName("rdoSearchEntity");
	
	for(var i=0; i<radios.length; i++) {				
		if(radios[i].checked) {
			cVal = radios[i].value;	
			var custUrlHiddenObj = document.getElementById("custUrl_fts_" + radios[i].value);
			var repCodeHiddenObj = document.getElementById("repCode_fts_" + radios[i].value);
			
			//alert('hiddenObj : ' + hiddenObj);
			if(custUrlHiddenObj.value != 'null') {
				subUrl = contextPath + "/" + custUrlHiddenObj.value
						 + "&fromFTSearch=Y&advanceSearch=Y" + "&indexDir=" + cVal;					
			}
			else if(repCodeHiddenObj.value != 'null') {
				subUrl = contextPath + "/ifms.htm?actionFlag=reportService&reportCode=" + repCodeHiddenObj.value 
					 	+ "&action=generateReport&FromParaPage=TRUE&advanceSearch=Y" + "&indexDir=" + cVal;
			}									
			break;
		}
	}
	//alert("Final URL :" + subUrl);
	showProgressbar();		
	document.forms['frmFullTextSearch'].action = subUrl;
	document.forms['frmFullTextSearch'].submit();
}
