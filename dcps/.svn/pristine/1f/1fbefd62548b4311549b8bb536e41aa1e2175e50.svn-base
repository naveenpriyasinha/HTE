var Row_ID_Pension=0;
var Row_ID_DP=0;
var Row_ID_CVP=0;
function addRowPension()
{	
	++Row_ID_Pension;
			  		
	var newRow = document.all("TablePension").insertRow(Row_ID_Pension);
   	var Cell1 = newRow.insertCell();
   	Cell1.className = "pensionLabels";
   	Cell1.align="center";
   	var Cell2 = newRow.insertCell();
   	Cell2.className = "pensionLabels";
   	Cell2.align="center";
   	var Cell3 = newRow.insertCell();
   	Cell3.className = "pensionLabels";
   	Cell3.align="center";
   	var Cell4 = newRow.insertCell();
	Cell4.className = "pensionLabels";
   	Cell4.align="center";
   	var Cell5 = newRow.insertCell();
	Cell5.className = "pensionLabels";
   	Cell5.align="center";
   	var Cell6 = newRow.insertCell();
	Cell6.className = "pensionLabels";
   	Cell6.align="center";   	   	
   	   
   	Cell1.innerHTML = '<select name="cmbRevisedTypes" id="cmbRevisedTypes'+Row_ID_Pension+'"  style="width: 100%"> <option value="-1">--SELECT--</option><option value="1986-1986">1986-1986</option><option value="1986-1996">1986-1996</option><option value="1996-1996">1996-1996</option><option value="1996-2006">1996-2006</option><option value="2006-2006">2006-2006</option></select>';
	Cell2.innerHTML = '<input type="text" name="txtOldBasicPen'+Row_ID_Pension+'" id="txtOldBasicPen'+Row_ID_Pension+'" size="15" />';      	
   	Cell3.innerHTML = '<input type="text" name="txtNewBasicPen'+Row_ID_Pension+'" id="txtNewBasicPen'+Row_ID_Pension+'" size="15" />';
   	Cell4.innerHTML = '<input type="text" name="txtFromDatePen'+Row_ID_Pension+'" id="txtFromDatePen'+Row_ID_Pension+'" size="15" /> <img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtFromDatePen'+Row_ID_Pension+'\', 375, 570, \'\', \'\', '+Row_ID_Pension+');" />';
   	Cell5.innerHTML = '<input type="text" name="txtToDatePen'+Row_ID_Pension+'" id="txtToDatePen'+Row_ID_Pension+'" size="15" /> <img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtToDatePen'+Row_ID_Pension+'\', 375, 570, \'\', \'\', '+Row_ID_Pension+');" />';
   	Cell6.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRowPension(this, \'TablePension\')" /> ';
   	assignTab();   	
}

function RemoveTableRowPension(obj, tblId)
{	  	
	var rowID = showRowCell(obj);	
    var tbl = document.getElementById(tblId);    
    tbl.deleteRow(rowID); 
    Row_ID_Pension = Row_ID_Pension - 1; 	        
}



function addRowDP()
{	
	++Row_ID_DP;
			  		
	var newRow = document.all("TableDP").insertRow(Row_ID_DP);
   	var Cell1 = newRow.insertCell();
   	Cell1.className = "pensionLabels";
   	Cell1.align="center";
   	var Cell2 = newRow.insertCell();
   	Cell2.className = "pensionLabels";
   	Cell2.align="center";
   	var Cell3 = newRow.insertCell();
   	Cell3.className = "pensionLabels";
   	Cell3.align="center";
   	var Cell4 = newRow.insertCell();
	Cell4.className = "pensionLabels";
   	Cell4.align="center";
   	   	
   	   
   	Cell1.innerHTML = '<select name="cmbDP" id="cmbDP'+Row_ID_DP+'"  style="width: 100%"> <option value="-1">--SELECT--</option></select>';

   	Cell2.innerHTML = '<input type="text" name="txtFromDateDP'+Row_ID_DP+'" id="txtFromDateDP'+Row_ID_DP+'" size="15" /> <img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtFromDateDP'+Row_ID_DP+'\', 375, 570, \'\', \'\', '+Row_ID_DP+');" />';
   	Cell3.innerHTML = '<input type="text" name="txtToDateDP'+Row_ID_DP+'" id="txtToDateDP'+Row_ID_DP+'" size="15" /> <img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtToDateDP'+Row_ID_DP+'\', 375, 570, \'\', \'\', '+Row_ID_DP+');" />';
   	Cell4.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRowDP(this, \'TableDP\')" /> ';
   	assignTab();   	
}

function RemoveTableRowDP(obj, tblId)
{	  	
	var rowID = showRowCell(obj);	
    var tbl = document.getElementById(tblId);    
    tbl.deleteRow(rowID); 
    Row_ID_DP = Row_ID_DP - 1; 	        
}

function addRowCVP()
{	
	++Row_ID_CVP;
			  		
	var newRow = document.all("TableCVP").insertRow(Row_ID_CVP);
   	var Cell1 = newRow.insertCell();
   	Cell1.className = "pensionLabels";
   	Cell1.align="center";
   	var Cell2 = newRow.insertCell();
   	Cell2.className = "pensionLabels";
   	Cell2.align="center";
   	var Cell3 = newRow.insertCell();
   	Cell3.className = "pensionLabels";
   	Cell3.align="center";
   	var Cell4 = newRow.insertCell();
	Cell4.className = "pensionLabels";
   	Cell4.align="center";
   	var Cell5 = newRow.insertCell();
	Cell5.className = "pensionLabels";
   	Cell5.align="center";
   	var Cell6 = newRow.insertCell();
	Cell6.className = "pensionLabels";
   	Cell6.align="center";   	   	
   	   
   
	Cell1.innerHTML = '<input type="text" name="txtCvpOldAmt'+Row_ID_CVP+'" id="txtCvpOldAmt'+Row_ID_CVP+'" size="15" />';      	
   	Cell2.innerHTML = '<input type="text" name="txtCvpNewAmt'+Row_ID_CVP+'" id="txtCvpNewAmt'+Row_ID_CVP+'" size="15" />';
   	Cell3.innerHTML = '<input type="text" name="txtFromDateCVP'+Row_ID_CVP+'" id="txtFromDateCVP'+Row_ID_CVP+'" size="15" /> <img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtFromDateCVP'+Row_ID_CVP+'\', 375, 570, \'\', \'\', '+Row_ID_CVP+');" />';
   	Cell4.innerHTML = '<input type="text" name="txtToDateCVP'+Row_ID_CVP+'" id="txtToDateCVP'+Row_ID_CVP+'" size="15" /> <img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtToDateCVP'+Row_ID_CVP+'\', 375, 570, \'\', \'\', '+Row_ID_CVP+');" />';
   	Cell5.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRowCVP(this, \'TableCVP\')" /> ';
   	assignTab();   	
}

function RemoveTableRowCVP(obj, tblId)
{	  	
	var rowID = showRowCell(obj);	
    var tbl = document.getElementById(tblId);    
    tbl.deleteRow(rowID); 
    Row_ID_CVP = Row_ID_CVP - 1; 	        
}


/*Common Function*/

function showRowCell(element)
{
	var cell, row;    
 
    if (element.parentNode) 
    {
      do
      	cell = element.parentNode;
      while (cell.tagName.toLowerCase() != 'td')
      row = cell.parentNode;
    }
    else if (element.parentElement) 
    {
      do
      	cell= element.parentElement;
      while (cell.tagName.toLowerCase() != 'td')
      row = cell.parentElement;
    }
    
    return row.rowIndex;
}


function window_open(val,x,y,afterDateSelect,dateInputParams)
{
    var newWindow;
 
    if(afterDateSelect == undefined) 
    {
		afterDateSelect = '';
	}
	glbAfterDateSelect = afterDateSelect;
   
    var urlstring = "common/calendarDppf.jsp?requestSent=" +val;
    
    dateChkInputs = dateInputParams;
    var X = window.event.screenX  - window.event.offsetX;
    var Y = window.event.screenY  + (20 - window.event.offsetY);	    
  
    var urlstyle = 'height=230,width=315,toolbar=no,minimize=no,status=no,memubar=no,location=no,scrollbars=no,top='+Y+',left='+X;
	
	newWindow = displayModalDialog(urlstring,'Calendar',urlstyle);
}
