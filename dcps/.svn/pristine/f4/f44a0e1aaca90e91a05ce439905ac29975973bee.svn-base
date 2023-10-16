<%
  try{
%>

<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>

<script type="text/javascript"   src="common/script/tagLibValidation.js"></script>


<c:set var="resultObj" 	value="${result}"></c:set>
<c:set var="resValue" 	value="${resultObj.resultValue}"></c:set>
<c:set var="billNoList" value="${resValue.billNoList}" ></c:set>


<SCRIPT><!--

//Function:- This method add selected items from Source Post List to Destination List and remove the Selected Items from the Source Post List.

function moveFromSrcToDest(){
	
	var destObj = document.getElementById('destEmpId');
	var srcObj = document.getElementById('srcEmpId');
	var srcEmpLgth = document.getElementById('srcEmpId').length;
	var destEmpLgth = document.getElementById('destEmpId').length;

	//alert("The length is"+eval(srcEmpLgth));
	
	for(i=srcEmpLgth-1;i>=0;i--) 
	{
		if(srcObj.options[i].selected) 
		{
			var optDest = document.createElement('option');
		    optDest.value=srcObj.options[i].value;
		    optDest.text=srcObj.options[i].text;
		    //alert('text and value is ' + srcObj.options[i].value + ' ' + srcObj.options[i].text);
		 
		    try {      				    					
             destObj.add(optDest,null);
             
        	}
			catch(ex) {
			 	 destObj.add(optDest); 
			}
			srcObj.options[i]=null;
		}	
	}
}

//Function:- This method add all items from Source Post List and add them in to Destination List and remove all items from the Source Post List.

function moveAllFromSrcToDest(){
	
	var destObj = document.getElementById('destEmpId');
	var srcObj = document.getElementById('srcEmpId');
	var srcEmpLgth = document.getElementById('srcEmpId').length;
	var destEmpLgth = document.getElementById('destEmpId').length;
	
	for(i=0;i<srcEmpLgth;i++) {
		
		var optDest=document.createElement('option');
		
	    optDest.value=srcObj.options[i].value;
	    optDest.text=srcObj.options[i].text;
	    
	    try {      				    					
            destObj.add(optDest,null);
       	}
		catch(ex) {
			destObj.add(optDest); 
		}
	}
	
	for(i=srcEmpLgth-1;i>=0;i--) {
		srcObj.options[i]=null;
	}
	
}

//Function:- This method remove selected items from Destination Post List and add to Source List and add to the Source Post List.

function moveFromDestToSrc(){
	
	var destObj = document.getElementById('destEmpId');
	var srcObj = document.getElementById('srcEmpId');
	var srcEmpLgth = document.getElementById('srcEmpId').length;
	var destEmpLgth = document.getElementById('destEmpId').length;
	
	for(i=destEmpLgth-1;i>=0;i--) {
		if(destObj.options[i].selected) {
			
			var optSrc = document.createElement('option');
		    optSrc.value=destObj.options[i].value;
		    optSrc.text=destObj.options[i].text;
		    
		    try {      				    					
             srcObj.add(optSrc,null);
        	}
			catch(ex) {
			 	 srcObj.add(optSrc); 
			}
			destObj.options[i]=null;
		}	
	}
}

//Function:- This method remove all items from Destination Post List and add them in to the Source Post List.

function moveAllFromDestToSrc(){
	
	var destObj = document.getElementById('destEmpId');
	var srcObj = document.getElementById('srcEmpId');
	var srcEmpLgth = document.getElementById('srcEmpId').length;
	var destEmpLgth = document.getElementById('destEmpId').length;
	
	for(i=0;i<destEmpLgth;i++) {
		var optSrc = document.createElement('option');
	    optSrc.value=destObj.options[i].value;
	    optSrc.text=destObj.options[i].text;
	    
	    try {      				    					
            srcObj.add(optSrc,null);
       	}
		catch(ex) {
			srcObj.add(optSrc); 
		}
	}
	for(i=destEmpLgth-1;i>=0;i--) {
		destObj.options[i]=null;
	}
	
}


function clearSourceSelect()
{
	var srcEmpCmbBx = document.getElementById("srcEmpId");
	
	//clear source select
	for(i=srcEmpCmbBx.length;i>=0;i--)
	{
		srcEmpCmbBx.options[i]=null;
	}

}


function clearDestinationSelect()
{
	
	var destEmpCmbBx = document.getElementById("destEmpId");

	//clear destination select
	for(i=destEmpCmbBx.length;i>=0;i--)
	{
		destEmpCmbBx.options[i]=null;
	}

}


//Function:- returns the list all employees which are not in 6th pay commission
function getOldEmpList()
{

	//clear selectBox
	clearSourceSelect();
	clearDestinationSelect();

	var billId = document.frmEmpCommissionMpg.billNoCmboBx.value;

	//select in which dynamic value of newOption will be set
	var oldEmpCmbBx = document.getElementById("srcEmpId");

	try{
		xmlHttp=new XMLHttpRequest();
   	}
	catch(e)
	{
		// Internet Explorer
		try{
	    	xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
    	}
	    catch (e)
	    {
		 	try{
	        	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
    	   	}
			catch (e)
			{
				alert("Your browser does not support AJAX!");
			    retValue=false;
			}
		}
	}

	//fetch old employees
	var url = "./hrms.htm?actionFlag=getOldCommissionEmployees&billId="+billId;

	//alert(' ' + url);
    xmlHttp.onreadystatechange = function()
    {
		if(xmlHttp.readyState == 4)
		{
			if (xmlHttp.status == 200)
			{
				var XMLDocForAjax=xmlHttp.responseXML.documentElement;
				var oldEmpList = XMLDocForAjax.getElementsByTagName('oldEmpList');	

				if(oldEmpList.length != 0)
				{	
					var i=0;

					for(i=0;i<oldEmpList.length;i++)
					{
						//new option for dynamically adding values to select
						var newOption = document.createElement("option");

						var empId = oldEmpList[i].childNodes[0].text;
						var empName = oldEmpList[i].childNodes[1].text;

						 try {
								newOption.text=empName;
								newOption.value=empId;

								oldEmpCmbBx.add(newOption,null);
						}
				 		catch(ex) {
				 			oldEmpCmbBx.add(newOption);
				   		}

					}//end for
                }//end if
		     }
	    }
	}


	xmlHttp.open("POST", encodeURI(url), false);    
	xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
	xmlHttp.send(encodeURIComponent(null));

 return true;

}//end method:getOldEmpList()


//add employees to upgraded into table
function addToEmployeesToTable()
{
		var destObj = document.getElementById("destEmpId");
		var destEmpLgth = destObj.length;


	//ensure employees are selected
	if( destEmpLgth > 0 )
	{
		var counter=0;

		// Inserting Cells
		for(i=0;i<destEmpLgth;i++)
		{
			//fetch value from destination select box
			var empName=destObj.options[i].text;
			var empId=destObj.options[i].value;

			//insert row
			var trow=document.getElementById('tblNewCommissionEmployees').insertRow();
			trow.align="center";

			//insert cells i.e. td
			var tdEmpId=trow.insertCell(0);
			var tdEmployeeName=trow.insertCell(1);
			var tdBasicPay=trow.insertCell(2);
			var tdEffectiveDate=trow.insertCell(3);
			var tdDelete=trow.insertCell(4);

			//default date
			var defalultDate='01/01/2006';

			//insert values			
			if( i%2!=0)
			{
				tdEmpId.innerHTML="<input type='hidden'  name='empId"+i+"' style='width:0%'  value='"+empId+"' >";
				tdEmployeeName.innerHTML="<input type='text' style='width:99%; border-width:1px; border-color:#cc9900;' name='empName"+i+"' value='"+empName+"' readonly='true' onkeyup='return deleteRecord(event,this)' onclick='toggleSelected(this)' >";
				tdBasicPay.innerHTML="<input type='text' style='width:99%; text-align: right; border-width:1px; border-color:#cc9900;background-color: #fff' name='txtBasicPay"+i+"' value='' onkeypress='gotoNxtRecord(event,this)'>";
				tdEffectiveDate.innerHTML="<input type='text' style='width:99%; text-align: center; border-width:1px; border-color:#cc9900;' name='txtEffectiveDate"+i+"' value='"+defalultDate+"' >"; //onblur='return validateDate(this)'
			    tdDelete.innerHTML="<input type='button' name='delete"+i+"' style='width:50%;border-width:0px' value='Remove' onclick='return deleteRecord(event,this)'>";
			}
			else
			{
				tdEmpId.innerHTML="<input type='hidden'  name='empId"+i+"' style='width:0%'  value='"+empId+"' >";
				tdEmployeeName.innerHTML="<input type='text' style='width:99%; border-width:1px; border-color:#000000;' name='empName"+i+"' value='"+empName+"' readonly='true' onkeyup='return deleteRecord(event,this)' >";
				tdBasicPay.innerHTML="<input type='text' style='width:99%; text-align: right; border-width:1px; border-color:#000000;background-color: #fff;color:#119900' name='txtBasicPay"+i+"' value='' onkeypress='gotoNxtRecord(event,this)'>";
				tdEffectiveDate.innerHTML="<input type='text' style='width:99%; text-align: center; border-width:1px; border-color:#000000;' name='txtEffectiveDate"+i+"' value='"+defalultDate+"' >"; //onblur='return validateDate(this)'
			    tdDelete.innerHTML="<input type='button' name='delete"+i+"' style='width:50%;border-width:0px' value='Remove' onclick='return deleteRecord(event,this)'>";
			}

		   	//increment counter
		    counter++;

		}//end for

		//clear destination select
		clearDestinationSelect();

		//set hidden counter
		document.frmEmpCommissionMpg.hiddenCounter.value=counter;
		//alert(document.frmEmpCommissionMpg.hiddenCounter.value+" employees added");
	}
	else
	{
		alert("Please select employees to upgrade");
		document.getElementById("srcEmpId").focus();
	 return false;
	}

 return true;
}//end method


function toggleSelected( obj )
{
	var color = obj.style.backgroundColor.value;
	//alert(color);
}


function gotoNxtRecord(event, obj)
{
	if( event.keyCode == 13 )
	{
		var table = document.getElementById('tblNewCommissionEmployees');
		var totalTR = table.rows.length-1; //to exclude header tr deduct 1
		var tr = eval(obj.parentNode.parentNode.rowIndex); //current TR

			//set next row on which focus needs to be set.
			if( (tr+1)<=totalTR ){
				tr=tr+1;				
			}
			table.childNodes[0].childNodes[tr].childNodes[2].childNodes[0].focus();
	}
}//end function

function clearTableCells()
{
	//if( confirm('Do u wish to remove all employees from table?') )
	{
	//	alert('inside clearTableCells..');
	}
}//end method


function submitForm()
{
	if( (document.getElementById("idHiddenCounter").value) > 0 )
	{
		var uri="hrms.htm?actionFlag=upgradeEmployeesCommission";
		document.frmEmpCommissionMpg.clkvalidation.disabled = true;
		document.frmEmpCommissionMpg.method="POST";
		document.frmEmpCommissionMpg.action=uri;
		document.frmEmpCommissionMpg.submit();
	}
	else
	{
		alert("Noting selected");
	}
}//end method


function deleteRecord( event,obj )
{

	// if deletetion of employee requested
	if( (event.keyCode==46) || (event.type=='click') ) 
	{
		if( confirm("Do u really wish to remove "+obj.parentNode.parentNode.childNodes[1].childNodes[0].value+" ?" ) )
		{

			var	objValue = obj.parentNode.parentNode.childNodes[0].childNodes[0].value;
			var	objText = obj.parentNode.parentNode.childNodes[1].childNodes[0].value;

			//alert('value: ' +objValue);
			//alert('text : ' +objText);

			//send da record back to destSelect				
			var destObj = document.getElementById('destEmpId');
			var optDest = document.createElement('option');
			    optDest.value=objValue;
			    optDest.text=objText;

			    try {      				    					
	             	  destObj.add(optDest,null);
	        	}
				catch(ex) {
				 	 destObj.add(optDest); 
				}


			//delete record
			document.getElementById('tblNewCommissionEmployees').deleteRow(obj.parentNode.parentNode.rowIndex);


			//total no of employees left are
			var counter = document.frmEmpCommissionMpg.hiddenCounter.value;
				counter = counter-1;

			//decrease hidden increment counter
			document.frmEmpCommissionMpg.hiddenCounter.value = document.frmEmpCommissionMpg.hiddenCounter.value-1; 

			//reset the name of each td element
			for( i=1; i<=counter; i++ )
			{

				var cell1 = document.getElementById('tblNewCommissionEmployees').cells( ((i*5)+0) );
				var cell2 = document.getElementById('tblNewCommissionEmployees').cells( ((i*5)+1) );
				var cell3 = document.getElementById('tblNewCommissionEmployees').cells( ((i*5)+2) );
				var cell4 = document.getElementById('tblNewCommissionEmployees').cells( ((i*5)+3) );
				var cell5 = document.getElementById('tblNewCommissionEmployees').cells( ((i*5)+4) );


					cell1.children[0].name = 'empId'+(i-1);
					cell2.children[0].name = 'empName'+(i-1);
					cell3.children[0].name = 'txtBasicPay'+(i-1);
					cell4.children[0].name = 'txtEffectiveDate'+(i-1);
					cell5.children[0].name = 'delete'+(i-1);

			}//end for

		}//end if

	}//end if

}//end funtion
--></SCRIPT>

<hdiits:form name="frmEmpCommissionMpg" method="POST" validate="" >

	<BR>
	<BR>

	<TABLE align="left">
		<TR>
		<TD>
			<hdiits:caption captionid="EC.billNo" bundle="${commonLables}" />
		</TD>
		<TD>
			<hdiits:select name="billNoCmboBx" captionid="EC.billNo" bundle="${commonLables}" sort="false" mandatory="true" onchange="getOldEmpList()" >
				<hdiits:option value="-1">------Select----- </hdiits:option>
					<c:forEach items="${billNoList}" var="billNoList">
						<option value="${billNoList.billHeadId}">
							<c:out value="${billNoList.billId}"></c:out>
						</option>
					</c:forEach>
			</hdiits:select>
		</TD>
		</TR>
	</TABLE>

	<BR>
	<BR>

												<!-- TABLE HEADER -->


	<TABLE align="center" width="90%" border="0">
		<TR>
			<TD style="width:15%">
			</TD>
			<TD style="width:30%" align="center">
				<hdiits:caption captionid="EC.Old_Emp_Name" bundle="${commonLables}"/>
			</TD>
			<TD align="center" style="width:10%" >
			</TD>
			<TD style="width:30%" align="center">
				<hdiits:caption captionid="EC.New_Emp_Name" bundle="${commonLables}"/>
			</TD>
			<TD>
			</TD>
		</TR>
	</TABLE>
												<!-- OLD AND NEW EMPLOYEES LIST  -->

	<TABLE align="center" style="width:90%;" border="0" >
		<TR>	
		<TD style="width:15%">
		</TD>
		<TD style="width:30%;"> 
			<hdiits:select style="width:95%;" captionid="EC.Old_Emp_Name" bundle="${enLables}" id="srcEmpId" name="srcEmpId" mandatory="true" multiple="true" size="10" ></hdiits:select>
		</TD>

		<TD align="center" style="width:10%" >
			<table >
				<tr>
				<td>
					<hdiits:button name="addFromSrvToDest" type="button" style='width:100%;' captionid="eis.addFromSrcToDest" bundle="${commonLables}" 
						onclick="moveFromSrcToDest()"/>
				</td>
				</tr>
				<tr>
				<td>
					<hdiits:button name="addMultiFromSrcToDest" type="button" style='width:100%;' captionid="eis.addMultiFromSrcToDest" bundle="${commonLables}" 
						onclick="moveAllFromSrcToDest()"/>
				</td>
				</tr>
				<tr>
				<td>
					<hdiits:button name="addFromDestToSrc" type="button" style='width:100%;' captionid="eis.addFromDestToSrc" bundle="${commonLables}" 
						onclick="moveFromDestToSrc()"/>
				</td>
				</tr>
				<tr>
				<td>
					<hdiits:button name="addMultiFromDestToSrc" type="button" style='width:100%;' captionid="eis.addMultiFromDestToSrc" bundle="${commonLables}" 
						onclick="moveAllFromDestToSrc()"/>
				</td>	
				</tr>
			</table>
		</TD>
		<TD>
		</TD>		
		<TD style="width:30%;" >
			<hdiits:select style="width:95%;" captionid="EC.New_Emp_Name" bundle="${commonLables}" id="destEmpId" mandatory="true" name="destEmpId" multiple="true" size ="10"></hdiits:select>
	  	</TD>	
		<TD>
			<hdiits:button name="btnAdd" type="button" style="width:50%" captionid="EC.ADD" bundle="${commonLables}" onclick="addToEmployeesToTable()" />
		</TD>
		</TR>
	</TABLE>
	<BR>
	<BR>
	<TABLE id="tblNewCommissionEmployees" align="center" width="65%" border="0px" bordercolor="#000000" cellpadding="1px" >
		<TR>
		<TD style="width:0px%">
		</TD>
		<TD align="center" style="width:35%; border:1px solid #cc9900;" bgcolor="lightblue" ><b>Employee Name</b></TD>
		<TD align="center" style="width:20%; border:1px solid #cc9900;" bgcolor="lightblue" ><b>Basic Salary</b></TD>
		<TD align="center" style="width:25%; border:1px solid #cc9900;" bgcolor="lightblue" ><b>Effective Date (DD-MM-YYYY)</b></TD>
		<TD align="center" style="width:20%; border:1px solid #cc9900;" bgcolor="lightblue" ><b>Remove </b></TD>
		</TR>
	</TABLE>

	<BR>
	<BR>
	<TABLE id="tbleButton" align="center" style="border-width:0px">
		<TR>
		<TD>
			<hdiits:button name="btnSubmit" id = "clkvalidation" type="button" style="width:100%;" caption=" Submit " onclick="submitForm()"/>
		</TD>
		<TD>
		</TD>
		<!--<TD>
			<hdiits:button name="btnClear" type="button" style="width:100%;" caption=" Clear All " onclick="clearTableCells()"/>
		</TD>
		--></TR>
	</TABLE>
	
	<input id="idHiddenCounter" type="hidden" name="hiddenCounter" ></input>
</hdiits:form>


<%
  }catch( Exception e ){
	  e.printStackTrace();
  }
%>