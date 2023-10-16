var globalTableName ='',editrowId,hidenFieldName,setGlobalRowNum;
var sendingDataArr = new Array('selType','fromDate','todate','remarksData','srNo','reqId');
var valiDationArray = new Array('selType','fromDate','todate','remarksData');
var displayFieldArray = new Array('fromDate','todate','Year_No','Mon_No','Day_No','remarksData');					
function openNewChildToAddData()
{
	if(document.getElementById('pensionChildOpenFlag').value=='false')
	{
		document.getElementById('pensionChildOpenFlag').value='true';	
		win=window.open("hrms.htm?viewName=pensionTotalServDataAdd","PensionServiceDtls","width=300,height=250,toolbar=no,status=yes,menubar=no,resizable=no,top=100,left=140,dependent=yes");
		win.focus();
	}
	else
	{
		win.focus();
	}
}
function checkForChildWindow()
{
	if(glbAfterDateSelect=='%^#$')
	{
		window.focus();
	}
}
function setWindowFlag()
{
	glbAfterDateSelect='%^#$';
}
function setFocusOnMe(fieldName)
{
	glbAfterDateSelect='';
	var obj = document.getElementById(fieldName);
	obj.focus();
}
function deletePensionRecord(rowId)
{
	if(ApprovedReqOpen==true)
	{
		var ans = deleteRecord(rowId);
		if(ans==true)
		{		
			for(x in mappingArrRowIdAndTableId);
			{
				var getObject = mappingArrRowIdAndTableId[x].toString();		
				var rowIdObj=getObject.split('/');					
				if(rowIdObj[0] == rowId)
				{
					mappingArrRowIdAndTableId.splice(x,1);
				}
			}		
			updateMySummaryData();
		}
	}
}
function DeleteRecordFromTable(rowId)
{		
	if(ApprovedReqOpen==true)
	{
		var hField = rowId.substring(3, rowId.length);		
		var hFieldValue = document.getElementById(hField).value; 		
		
		var ans = deleteRecord(rowId);
		if(ans==true)
		{
			if(hField.search(/'newencxML_sus'/i)==-1  && hField.search(/encXML_sus/i)!=-1)
			{		
				deletArrList_SUS[deletArrListCount_SUS]=hFieldValue;
				deletArrListCount_SUS=deletArrListCount_SUS+1;
			}
			else if(hField.search(/'newencxML'/i)==-1)
			{			
				deletArrList[deletArrListCount] = hFieldValue; 
				deletArrListCount=deletArrListCount+1;				
			}			
			for(x in mappingArrRowIdAndTableId);
			{
				var getObject = mappingArrRowIdAndTableId[x].toString();		
				var rowIdObj=getObject.split('/');					
				if(rowIdObj[0] == rowId)
				{
					mappingArrRowIdAndTableId.splice(x,1);
				}
			}		
			updateMySummaryData();
		}
	}
}
function getDateInProperFormat(v)
{
	if(v.indexOf("-")!=-1)
	{
		v=v.substring(0,10);				
		var splitDate=v.split("-");				
		var byr=splitDate[0];
		var bmo=splitDate[1];
		var bday=splitDate[2];				
		v= bday+'/'+bmo+'/'+byr;	
	}
	return v;
}
function getDateDiffInString(strDate1,strDate2)
{
		strDate1 = strDate1.split("/"); 		
		starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]); 
		starttime = new Date(starttime.valueOf()); 
		
		//End date split to UK date format 
		strDate2 = strDate2.split("/"); 
		endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]); 
		endtime = new Date(endtime.valueOf()); 												
						
		if(endtime >= starttime) 
		{	
			var setDay = 0;    	
			var lIntPenSerYear = strDate2[2] - strDate1[2];
     	 	var lIntPenSerMonth = strDate2[1]- strDate1[1];
     	 	var lIntPenSerDay = strDate2[0] - strDate1[0];     	 				
     	 	lIntPenSerDay=lIntPenSerDay+1;
     	 	var intMonth = parseInt(strDate1[1]);

     	 	var intday = parseInt(strDate1[0]);
     	 	intYear = parseInt(strDate1[2]);
     	 	while(parseInt(strDate2[2]) >= intYear)
     	 	{     	  		    	 		
				if (intMonth>=13) {			
					intMonth=1;
					intYear++;
				}
				if (intMonth == 1 || intMonth == 3 || intMonth == 5 || intMonth == 7 || intMonth == 8 || intMonth == 10 || intMonth == 12) {
					setDay = 31;	
				}
				if (intMonth == 4 || intMonth == 6 || intMonth == 9 || intMonth == 11) {
					setDay = 30;
				}
				if (intMonth == 2) 
				{
					if (LeapYear(intYear) == true) {
						setDay = 29;
					}
					else {
						setDay = 28;
					}
				}				
	     	 	if(setDay!=0)
	     	 	{     		     	 			
				    while(lIntPenSerDay >= setDay)
				    {
				          lIntPenSerDay -= setDay;
				          lIntPenSerMonth++;
				          if(lIntPenSerMonth==12)
				          {
				              lIntPenSerMonth=0;
				              lIntPenSerYear++;
				          }
				    }
				    while(lIntPenSerDay < 0)
				    {
				          lIntPenSerDay = setDay + lIntPenSerDay;
				          lIntPenSerMonth--;
				          if(lIntPenSerMonth<=-1)
				          {
				              lIntPenSerMonth=12+lIntPenSerMonth;
				              lIntPenSerYear--;              
				          }
				    }				    
				    if(lIntPenSerMonth <=-1)
				    {
				          lIntPenSerMonth=12+lIntPenSerMonth;
				          lIntPenSerYear--;              
				    }
				    return (lIntPenSerYear+'~'+lIntPenSerMonth+'~'+lIntPenSerDay);
				}
				else 
				{
					return '0~0~0'; 
				}
				intMonth++;													
			}
		}
		else
		{
			return '0~0~0'; 
		}
}
function giveMeDateDiff(year,month,day)
{
	var lIntPenSerYear = year;
    var lIntPenSerMonth = month;
    var lIntPenSerDay = day;
	while(lIntPenSerDay > 31)
	{
	    lIntPenSerDay -= 31;
		lIntPenSerMonth++;
          if(lIntPenSerMonth==12)
          {
              lIntPenSerMonth=0;
              lIntPenSerYear++;
          }
    }
    while(lIntPenSerDay < 0)
    {
          lIntPenSerDay = 31 + lIntPenSerDay;
          lIntPenSerMonth--;
          if(lIntPenSerMonth<=-1)
          {
              lIntPenSerMonth=12+lIntPenSerMonth;
              lIntPenSerYear--;              
          }
    }
    if(lIntPenSerMonth <=-1)
    {
          lIntPenSerMonth=12+lIntPenSerMonth;
          lIntPenSerYear--;              
    }
    return (lIntPenSerYear +"~" + lIntPenSerMonth+"~"+ lIntPenSerDay);
}
function LogicForMinusValue()
{
	var day=0,month=0,year=0;
	var totalDays = 0;
	for(x in mappingArrRowIdAndTableId)
	{
		var getObject = mappingArrRowIdAndTableId[x].toString();
		var rowIdObj=getObject.split('/');			
		trow = document.getElementById(rowIdObj[0]);		
		if(trow!=null)
		{
			date1 = trow.cells[1].innerText;
			date2 = trow.cells[2].innerText;
			totalDays = parseInt(getTotalDays(date1,date2)) + totalDays;
			year = year+ parseInt(trow.cells[3].innerText);
			month = month + parseInt(trow.cells[4].innerText);
			day = day + parseInt(trow.cells[5].innerText);				
		}		
	}	
	var dateDiff=giveMeDateDiff(year,month,day);	
	return dateDiff;
}
function getTotalDays(strDate1,strDate2)
{	
	
	strDate1 = strDate1.split("/"); 
	starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]); 
	starttime = new Date(starttime.valueOf()); 	
	//End date split to UK date format 
	strDate2 = strDate2.split("/"); 
	endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]); 
	endtime = new Date(endtime.valueOf()); 												
	
	setDay1 = getDays(parseInt(strDate1[1]),parseInt(strDate1[2]));
	setDay2 = getDays(parseInt(strDate2[1]),parseInt(strDate2[2]));
	var totalDays = setDay1 - (parseInt(strDate1[0])-1) + parseInt(strDate2[0]);		
	if(endtime >= starttime) 
	{	
		var setDay = 0;    	
		var lIntPenSerYear = strDate2[2] - strDate1[2];
   	 	var lIntPenSerMonth = (strDate2[1])- (strDate1[1]);
   	 	var lIntPenSerDay = strDate2[0] - strDate1[0];     	 	
   	 	     	 	 	 	
   	 	var intMonth=parseInt(strDate1[1]);
   	 	var intday = parseInt(strDate1[0]);
   	 	intYear = parseInt(strDate1[2]);
   	 	while(parseInt(strDate2[2]) >= intYear)
   	 	{   	 
   	 		intMonth++;			 		
   	 		if(intMonth!=parseInt(strDate2[1]))
   	 		{
				if (intMonth>=13) {			
					intMonth=1;
					intYear++;
				}
				setDay = getDays(intMonth,intYear);
				totalDays = totalDays + setDay;				
			}
			else if(intMonth==parseInt(strDate2[1]) && intYear==parseInt(strDate2[2]))
			{
				break;
			}			
		}
	}
	return totalDays;
}
function getDays(intMonth,intYear)
{
	var setDay=0;	
	if (intMonth == 1 || intMonth == 3 || intMonth == 5 || intMonth == 7 || intMonth == 8 || intMonth == 10 || intMonth == 12) {
		setDay = 31;	
	}
	if (intMonth == 4 || intMonth == 6 || intMonth == 9 || intMonth == 11) {
		setDay = 30;
	}
	if (intMonth == 2) 
	{
		if (LeapYear(intYear) == true) {
			setDay = 29;
		}
		else {
			setDay = 28;
		}
	}
	return setDay;
}
function editRecordForDB(rowId,rowNum)
{
	if(ApprovedReqOpen==true)
	{
		editrowId = rowId;
		document.getElementById('btnSave').disabled=false;
		document.getElementById('btnAdd').disabled=true;
		sendAjaxRequestForEditAttachment(rowId, 'populateForm','attachmentBiometric',rowNum);
	}
}

function editRecord(rowId,rowNum)
{
	if(ApprovedReqOpen==true)
	{
		editrowId = rowId;
		document.getElementById('btnSave').disabled=false;
		document.getElementById('btnAdd').disabled=true;
		sendAjaxRequestForEditAttachment(rowId, 'populateForm','attachmentBiometric',rowNum);
	}
}
function populateForm()
{
	if (xmlHttp.readyState == 4)
  	{ 	
  		if (xmlHttp.status == 200) 
		{
			var decXML = xmlHttp.responseText;				  	
			var xmlDOM = populateAttachment(decXML,'frmPensionServDtls');			
			for(var x=0;x<mappingArrRowIdAndTableId.length;x++)
			{
				var getObject = mappingArrRowIdAndTableId[x].toString();						
				var rowIdObj=getObject.split('/');					
				if(rowIdObj[0] == editrowId)
				{
					globalTableName=rowIdObj[1];
					hidenFieldName=rowIdObj[2];	
				}
			}
			var callLeaveTab =false;
			if(globalTableName=='extraOrdinaryLeaveTable')
			{
				callLeaveTab=true;
				document.getElementById('selType').value='EOL';							
			}
			else if(globalTableName=='brakInSrvcTable')
			{
				callLeaveTab=true;
				document.getElementById('selType').value='LWP';
			}
			else
			{
				document.getElementById('selType').value='SUS';
				document.getElementById('formTable1attachmentBiometric').firstChild.firstChild.style.display='none';				
				document.frmPensionServDtls.fromDate.value=getDateInProperFormat(getXPathValueFromDOM (xmlDOM, 'suspenFromDate'));
				document.frmPensionServDtls.todate.value=getDateInProperFormat(getXPathValueFromDOM (xmlDOM, 'suspenToDate'));
				document.getElementById('remarksData').value=getXPathValueFromDOM (xmlDOM, 'remarks');	
				try {		
					document.getElementById('srNo').value = getXPathValueFromDOM (xmlDOM, 'suspensionId');
				}catch(ex){
					document.getElementById('srNo').value = 0;
				}
			}
			document.getElementById('selType').disabled=true;	
			if(callLeaveTab==true)
			{				
				document.frmPensionServDtls.fromDate.value=getDateInProperFormat(getXPathValueFromDOM (xmlDOM, 'appLeaveFrom'));
				document.frmPensionServDtls.todate.value=getDateInProperFormat(getXPathValueFromDOM (xmlDOM, 'appLeaveTo'));
				document.getElementById('remarksData').value=getXPathValueFromDOM (xmlDOM, 'remarks');	
				try {		
					document.getElementById('srNo').value = getXPathValueFromDOM (xmlDOM, 'leaveid');
				}catch(ex){
					document.getElementById('srNo').value = 0;
				}
			}
		}
	}
}
function addRecord()
{
	if (xmlHttp.readyState == 4)
  	{ 	
  		if (xmlHttp.status == 200) 
		{
			if(globalTableName!='')
			{	
				var dateDiff = getDateDiffInString(document.getElementById('fromDate').value,document.getElementById('toDate').value);						
				var minValue = dateDiff.split('~');	
				document.getElementById('Year_No').value = minValue[0];
				document.getElementById('Mon_No').value = minValue[1];
				document.getElementById('Day_No').value = minValue[2];				
				if(globalTableName=='suspensionPeriodTable')
				{
					var rowNum = addDataInTableAttachment(globalTableName,'newencXML_sus',displayFieldArray, 'editRecord','deletePensionRecord','');				
					mappingArrRowIdAndTableId[mappingArrRowIdAndTableIdCount]="rownewencXML_sus"+parseInt(counter-1)+"/"+globalTableName+"/newencXML_sus";
					mappingArrRowIdAndTableIdCount=mappingArrRowIdAndTableIdCount+1;
				}
				else
				{
					var rowNum = addDataInTableAttachment(globalTableName,'newencXML',displayFieldArray, 'editRecord','deletePensionRecord','');	
					mappingArrRowIdAndTableId[mappingArrRowIdAndTableIdCount]="rownewencXML"+parseInt(counter-1)+"/"+globalTableName+"/newencXML";
					mappingArrRowIdAndTableIdCount=mappingArrRowIdAndTableIdCount+1;
				}				
				updateMySummaryData();
		 		resetData(rowNum);
		 	}
		}
	}
}
function updateRecord()
{
	if (xmlHttp.readyState == 4)
  	{ 	
  		if (xmlHttp.status == 200) 
		{			
			var dateDiff = getDateDiffInString(document.getElementById('fromDate').value,document.getElementById('toDate').value);						
			var minValue = dateDiff.split('~');	
			document.getElementById('Year_No').value = minValue[0];
			document.getElementById('Mon_No').value = minValue[1];
			document.getElementById('Day_No').value = minValue[2];			
			var rowNum = updateDataInTableAttachment(globalTableName,hidenFieldName, displayFieldArray);
			document.getElementById('formTable1attachmentBiometric').firstChild.firstChild.style.display='';		
			updateMySummaryData();									
			resetData(rowNum);
			document.getElementById('selType').disabled=false;
			document.getElementById('btnSave').disabled=true;
			document.getElementById('btnAdd').disabled=false;
		}
	}
}
function updateMySummaryData()
{		
	document.getElementById('min_Year').value = 0;
	document.getElementById('min_Month').value = 0;
	document.getElementById('min_Day').value = 0;
	
	var dateDiff = LogicForMinusValue();
	var minValue = dateDiff.split('~');		
		
	var year=minValue[0];
	var mon= minValue[1];
	var day= minValue[2];
	
	dateDiff = giveMeDateDiff(year,mon,day);
	minValue = dateDiff.split('~');	
	
	document.getElementById('min_Year').value = minValue[0];
	document.getElementById('min_Month').value = minValue[1];
	document.getElementById('min_Day').value = minValue[2];	
	
	var year=parseInt(document.getElementById('total_Year').value) - minValue[0];
	var mon=parseInt(document.getElementById('total_Month').value) - minValue[1];
	var day=parseInt(document.getElementById('total_Day').value) - minValue[2];	
	
	var dateDiff = giveMeDateDiff(year,mon,day);
	var minValue = dateDiff.split('~');					
	document.getElementById('final_Year').value=minValue[0];
	document.getElementById('final_Month').value=minValue[1];
	document.getElementById('final_Day').value=minValue[2];
}
function AddOrUpdateME(buttonName)
{	
	var statusValidation = false;
	statusValidation = validateSpecificFormFields(valiDationArray);
	if(statusValidation==true)
	{	
		statusValidation = validateToAndFromDate();			
		if(statusValidation==true)
		{
			if(buttonName == 'btnAdd')
			{		
				var str = document.getElementById('selType').value;
				if(str=='EOL')
				{
					globalTableName='extraOrdinaryLeaveTable';
				}
				else if(str=='LWP')
				{
					globalTableName='brakInSrvcTable';
				}
				else
				{
					globalTableName='suspensionPeriodTable';
					document.getElementById('formTable1attachmentBiometric').firstChild.firstChild.style.display='';
				}
				addOrUpdateRecord('addRecord','AddPensionTotalDtls',sendingDataArr);
			}
			else
			{
				addOrUpdateRecord('updateRecord','AddPensionTotalDtls',sendingDataArr);
			}
		}
	}
}
function resetData(rowNum)
{	
	removeRowFromTableattachmentBiometric(rowNum,'frmPensionServDtls'); 
	document.getElementById('selType').value='Select';
	document.frmPensionServDtls.fromDate.value='';
	document.frmPensionServDtls.todate.value='';
	document.getElementById('remarksData').value='';	
}
function LeapYear(year)
{	
	if((year%4 == 0) && ((year % 100)!=0) || ((year % 400)==0) ){return true;}
	else 	{
		return false;		
	}
}
/// When User Click on View Link
function viewThisData(rowId,rowNum)
{	
	if(ApprovedReqOpen==true)
	{
		editrowId = rowId;
		sendAjaxRequestForEditAttachment(rowId, 'populateDisable','attachmentBiometric',rowNum);	
		setGlobalRowNum = rowNum;
	}
}
function populateDisable()
{
	if (xmlHttp.readyState == 4)
  	{ 	
  		if (xmlHttp.status == 200) 
		{
			var decXML = xmlHttp.responseText;				  	
			var xmlDOM = populateAttachment(decXML,'frmPensionServDtls');			
			for(x in mappingArrRowIdAndTableId)
			{
				var getObject = mappingArrRowIdAndTableId[x].toString();						
				var rowIdObj=getObject.split('/');					
				if(rowIdObj[0] == editrowId)
				{
					globalTableName=rowIdObj[1];
					hidenFieldName =rowIdObj[2];					
				}
			}
			var leaveFlag=false;
			if(globalTableName=='extraOrdinaryLeaveTable')
			{
				leaveFlag=true;
				document.getElementById('selType').value='EOL';							
			}
			else if(globalTableName=='brakInSrvcTable')
			{
				leaveFlag=true;
				document.getElementById('selType').value='LWP';
			}	
			else
			{
				document.getElementById('selType').value='SUS';
				document.frmPensionServDtls.fromDate.value=getDateInProperFormat(getXPathValueFromDOM (xmlDOM, 'suspenFromDate'));
				document.frmPensionServDtls.todate.value=getDateInProperFormat(getXPathValueFromDOM (xmlDOM, 'suspenToDate'));
				document.getElementById('remarksData').value=getXPathValueFromDOM (xmlDOM, 'remarks');
			}
			if(leaveFlag==true)			
			{
				document.frmPensionServDtls.fromDate.value=getDateInProperFormat(getXPathValueFromDOM (xmlDOM, 'appLeaveFrom'));
				document.frmPensionServDtls.todate.value=getDateInProperFormat(getXPathValueFromDOM (xmlDOM, 'appLeaveTo'));
				document.getElementById('remarksData').value=getXPathValueFromDOM (xmlDOM, 'remarks');									
			}
			disable();			
		}
	}
}
function closeViewData()
{
	updateRow = null;	
	enable();
	resetData(setGlobalRowNum);		
}
function enable()
{	
	document.getElementById('btnSave').disabled=true;
	document.getElementById('btnAdd').disabled=false;
	document.getElementById('btnCloseView').style.display='none';
	document.getElementById('btnCloseView').disabled=true;
	document.getElementById('fromDate').disabled=false;
	document.getElementById('toDate').disabled=false;	
	document.getElementById('selType').disabled=false;
	document.getElementById('remarksData').disabled=false;
	document.getElementById('fromDate').readOnly=true;
	document.getElementById('toDate').readOnly=true;	
	document.getElementById('formTable1attachmentBiometric').firstChild.firstChild.style.display='';
	/*var totalElements= document.forms[0].elements.length
	for(i=0;i<totalElements;i++)
	{
		document.forms[0].elements[i].disabled=false
	}*/		
}
function disable()
{
	document.getElementById('btnCloseView').style.display='';
	document.getElementById('btnCloseView').disabled=false;
	document.getElementById('fromDate').disabled=true;
	document.getElementById('toDate').disabled=true;	
	document.getElementById('selType').disabled=true;
	document.getElementById('remarksData').disabled=true;
	document.getElementById('btnSave').disabled=true;
	document.getElementById('btnAdd').disabled=true;
	document.getElementById('formTable1attachmentBiometric').firstChild.firstChild.style.display='none';
	/*var totalElements= document.forms[0].elements.length
	for(i=0;i<totalElements;i++)
	{
			document.forms[0].elements[i].disabled=true
	}*/
}