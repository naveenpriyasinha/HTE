function addDataInTableRole(tableId, displayFieldArray,deleteMethodName,editMethodName, hdnFieldValue, isDisable) 
{ 
	if(deleteMethodName == undefined) 
	{
		deleteMethodName = '';
	}
	
	if(editMethodName == undefined) 
	{
		editMethodName = '';
	}
	
	document.getElementById(tableId).style.display='';
	var trow=document.getElementById(tableId).insertRow();
	trow.id = displayFieldArray[0];
	
	trow.insertCell(0).innerHTML = "<INPUT type='hidden' name='hdnFlag"+displayFieldArray[0]+"' id='hdnFlag"+displayFieldArray[0]+"' value='"+hdnFieldValue+"'/>" +
								   "<INPUT type='hidden' name='hdnStartDate"+displayFieldArray[0]+"' id='hdnStartDate"+displayFieldArray[0]+"' value='"+displayFieldArray[3]+"'/>" +				
								   "<INPUT type='hidden' name='hdnEndDate"+displayFieldArray[0]+"' id='hdnEndDate"+displayFieldArray[0]+"' value='"+displayFieldArray[4]+"'/>" +				
								   "<INPUT type='hidden' name='hdnStatus"+displayFieldArray[0]+"' id='hdnStatus"+displayFieldArray[0]+"' value='"+displayFieldArray[5]+"'/>";				

	trow.cells[0].style.display = 'none';
	
	trow.insertCell(1).innerHTML = "<INPUT type='hidden' name='hdnRoleId' id='hdnRoleId' value='"+displayFieldArray[0]+"'/>";				
	trow.cells[1].style.display = 'none';
	
	trow.insertCell(2).innerHTML = displayFieldArray[1];
	trow.cells[2].align = 'center';				
	trow.cells[2].style.display = '';
	
	trow.insertCell(3).innerHTML = displayFieldArray[2];
	trow.cells[3].align = 'center';				
	trow.cells[3].style.display = '';
	
	/* commented by sandip - start
	trow.insertCell(4).innerHTML ="<INPUT type='text' size='10' name='startDate"+displayFieldArray[0]+"' onkeypress='return checkDateNumber()' class='dateTimetag'  onBlur='return checkDate(\"startDate"+displayFieldArray[0]+"\",\"Please enter valid Date\",\"Start Date\",\"0\",\"31/12/2099\",\"Please enter date less than 31/12/2099\")' value='" +displayFieldArray[3]+ "' maxlength=10 size=6 readonly=true/>" +
								  "<img style='cursor:hand' id='img_startDate"+displayFieldArray[0]+"' src='images/ico-calendar.gif' width=20 "+ (isDisable?"":"onClick=window_open('startDate"+displayFieldArray[0]+"',375,570,'',\"startDate"+displayFieldArray[0]+",Please~enter~valid~startDate"+displayFieldArray[0]+",Start~Date\")") +" >"+
								  "&nbsp;&nbsp;<label class=\"mandatoryindicator\">*</label>";
	trow.cells[4].align = 'center';								  
	trow.cells[4].style.display = '';
	
	trow.insertCell(5).innerHTML ="<INPUT type='text' size='10' name='endDate"+displayFieldArray[0]+"' onkeypress='return checkDateNumber()' class='dateTimetag'  onBlur='return checkDate(\"endDate"+displayFieldArray[0]+"\",\"Please enter valid Date\",\"End Date\",\"0\",\"31/12/2099\",\"Please enter date less than 31/12/2099\")' value='" +displayFieldArray[4]+ "' maxlength=10 size=6 readonly=true/>" +
								  "<img style='cursor:hand' id='img_endDate"+displayFieldArray[0]+"' src='images/ico-calendar.gif' width=20 "+ (isDisable?"":"onClick=window_open('endDate"+displayFieldArray[0]+"',375,570,'',\"endDate"+displayFieldArray[0]+",Please~enter~valid~endDate"+displayFieldArray[0]+",End~Date\")") +" >";
	trow.cells[5].align = 'center';								  
	trow.cells[5].style.display = '';
	end */
	
	trow.insertCell(4).innerHTML = "<INPUT type='radio' name='rdoStatusFlag"+displayFieldArray[0]+"' value='1' "+ (displayFieldArray[5]==1?"checked":"") +" "+ (isDisable?"DISABLED":"") +" class='radiobuttontag' id='rdoStatusFlag"+displayFieldArray[0]+"' />"+cmnRoleMappingLabel[0]+
								   "<INPUT type='radio' name='rdoStatusFlag"+displayFieldArray[0]+"' value='2' "+ (displayFieldArray[5]==2?"checked":"") +" "+ (isDisable?"DISABLED":"") +" class='radiobuttontag' id='rdoStatusFlag"+displayFieldArray[0]+"' />"+cmnRoleMappingLabel[1];
	trow.cells[4].align = 'center';								   
	trow.cells[4].style.display = '';
			
	trow.insertCell(5).innerHTML = "<a id='editLink"+ displayFieldArray[0] +"' href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id + "')>"+cmnRoleMappingLabel[2]+"&nbsp;/</a> " + 
								   "<a id='resetLink"+ displayFieldArray[0] +"' href=javascript:void('reset') onclick=javascript:resetRecord('" + trow.id + "')>"+cmnRoleMappingLabel[3]+"&nbsp;/</a> " +
								   "<a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>"+cmnRoleMappingLabel[4]+"</a>";										
	trow.cells[5].align = 'center';								   
	trow.cells[5].style.display = '';
	
	if(hdnFieldValue=='N')
	{
		document.getElementById("editLink"+ displayFieldArray[0] +"").style.display = 'none';
	}
	else
	{
		document.getElementById("resetLink"+ displayFieldArray[0] +"").style.display = 'none';
	}
	
	counter++;	
	
	return trow.id;
}
function showdate() 
{
	var t = new Date;
	var day = t.getDate();
	var month = t.getMonth() + 1;
	var year = t.getFullYear();
	if (day < 10) day = "0" + day;
	if (month < 10) month = "0" + month;
	var crntDate=day + '/' + month + '/' + year;
	return crntDate;
}

function editRecord(rowId)
{	
	document.getElementById("editLink"+ rowId +"").style.display = 'none';
	document.getElementById("resetLink"+ rowId +"").style.display = '';
	
	//commented by sandip
	//document.getElementById("img_startDate"+ rowId +"").onclick = function(){window_open("startDate"+ rowId +"",375,570,'',"startDate"+rowId+",Please~enter~valid~startDate"+rowId+",Start~Date")};
	//document.getElementById("img_endDate"+ rowId +"").onclick = function(){window_open("endDate"+ rowId +"",375,570,'',"endDate"+rowId+",Please~enter~valid~endDate"+rowId+",End~Date")};
	
	eval("document.getElementById(\"hdnFlag"+rowId+"\").value='U'");
	
	eval("document."+ document.forms[0].name +".rdoStatusFlag"+rowId+"[0].disabled=false");
	eval("document."+ document.forms[0].name +".rdoStatusFlag"+rowId+"[1].disabled=false");
}

function resetRecord(rowId)
{
	//commented by sandip
	//document.getElementById("startDate"+rowId+"").value = document.getElementById("hdnStartDate"+rowId+"").value;
	//document.getElementById("endDate"+rowId+"").value = document.getElementById("hdnEndDate"+rowId+"").value;
	
	if(parseInt(document.getElementById("hdnStatus"+rowId+"").value) == 1)
		eval("document."+ document.forms[0].name +".rdoStatusFlag"+rowId+"[0].checked=true");
	else
		eval("document."+ document.forms[0].name +".rdoStatusFlag"+rowId+"[1].checked=true");	
	
	var hdnVar = document.getElementById("hdnFlag"+rowId).value;
	if(hdnVar!='N')
	{
		eval("document."+ document.forms[0].name +".rdoStatusFlag"+rowId+"[0].disabled=true");
		eval("document."+ document.forms[0].name +".rdoStatusFlag"+rowId+"[1].disabled=true");

		document.getElementById("resetLink"+ rowId +"").style.display = 'none';
		document.getElementById("editLink"+ rowId +"").style.display = '';

		//commented by sandip
		//document.getElementById("img_startDate"+ rowId +"").onclick = function(){};
		//document.getElementById("img_endDate"+ rowId +"").onclick = function(){};
	
		eval("document.getElementById(\"hdnFlag"+rowId+"\").value='M'");
	}
}


function deleteRecord(rowId)
{
	if(document.getElementById("hdnFlag"+rowId).value=='N')
	{
		var delRow = document.getElementById(rowId);
		delRow.parentNode.deleteRow(delRow.rowIndex);
	}
	else
	{	
		document.getElementById(rowId).style.display='none';
		document.getElementById("hdnFlag"+rowId).value='D';
		//eval("document.frmPostRole.hdnFlag"+rowId+".value='D'");
		var delRow = document.getElementById(rowId);
		delRow.id=0;
	}
}
function getHomePage()
{
	document.forms[0].action="hdiits.htm?actionFlag=getHomePage";
	document.forms[0].submit();
}