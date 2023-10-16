<%
try {
%>
<%@ include file="/WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript" src="<c:url value="/script/leave/leavevalidation.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/ess/leave/leavecommon.js"/>"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>

<fmt:setBundle basename="resources.ess.leave.AlertMessages" var="alertLables" scope="request" />
<fmt:setBundle basename="resources.ess.leave.LeaveCaption" var="LeaveCaption" scope="request" />
	
<style type="text/css">    
            .pg-normal {
                color: black;
                font-weight: normal;
                text-decoration: none;    
                cursor: pointer;    
            }
            .pg-selected {
                color: black;
                font-weight: bold;        
                text-decoration: underline;
                cursor: pointer;
            }
</style>
	
<script>
obj=this;

function SearchEmp()
{
	//var href='hdiits.htm?actionFlag=allData';
	var href='hdiits.htm?actionFlag=getEmpSearchSelData&multiple=true';
	window.open(href,'chield','width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
}
function memberSelect()
{
	var checkboxes=document.forms[0].elements['check'];
	if(isNaN(checkboxes.length))
	{
		document.forms[0].elements['check'].checked=true;
	}
	else
	{
		for(i=0;i<checkboxes.length;i++)
		{
			if(document.forms[0].MemSel.status){
				checkboxes[i].checked=true;
			}
			else{
				checkboxes[i].checked=false;
			}
		}
	}
}
function deleteMember()
{
	var tb = document.getElementById('travelTableData');
	var deletedRow= new Array();
	var checkboxes=document.forms[0].elements['check'];
	var	j=0;
	if(isNaN(checkboxes.length))
	{
			document.getElementById('travelTable').style.display='none';
			document.getElementById('deleteMem').style.display='none';
			if(checkboxes.checked)
			{
				var index = eval(checkboxes.getAttribute("indexPos"));
				uniqueuserId=new Array();		
				k=0;
				document.getElementById("row_"+index).parentNode.removeChild(document.getElementById("row_"+index));
			}
	}
	else
	{	
		
		
		var checkedCounter=0;		
		for(i=0;i<checkboxes.length;i++)
		{
		
			if(checkboxes[i].checked)
			{
				var a=0;
				checkedCounter++;
				var index = eval(checkboxes[i].getAttribute("indexPos"));
				deletedRow[j]=document.getElementById("row_"+index);
				var tempArray=new Array();
				for(var y=0;y<uniqueUserId.length;y++)
				{
					if( document.getElementById('leaveBalUserId'+index).value != uniqueUserId[y])
					{
						tempArray[a]=uniqueUserId[y];
						a++;
					}
					
				}
				uniqueUserId = tempArray;
				k = uniqueUserId.length;
				j++;
				
			}
			
		}
		
		if( checkedCounter == document.forms[0].elements['check'].length )
		{
			document.getElementById('travelTable').style.display='none';
			document.getElementById('deleteMem').style.display='none';
		}
	}		
		j=0;
	deleteRowOfMembers(deletedRow);
	
}

function deleteRowOfMembers(delMember)
{
	for(d=0;d<delMember.length;d++)
	{
		
		delMember[d].parentNode.removeChild(delMember[d]);
		
	}
	var pager=new Pager('travelTableData', 10,obj); 
    pager.init();
    pager.showPageNav('pager', 'pageNavPosition'); 
    pager.showPage(1);
}



var empArray = new Array();
var emparray = new Array();
var uniqueUserId = new Array();
var empcount = 0 ;
var finalEmpArr = new Array();
var ctr=1;
var records=new Array();
var tempUserID=0;
var k=0;


function copyUserId(tempUserID)
{
	uniqueUserId[k]=tempUserID;
	k++;
	
}

function checkDuplicate(addUserId)
{
	var temp=0;
	for(var x=0;x<uniqueUserId.length;x++)
	{
		if( addUserId == uniqueUserId[x] )
		{
			temp++;
		}
	}
	if(temp>1)
	{
		return false;
	}
	else
	{
		return true;
	}
}



function empSearch(from)		
{	
	for(var i=0; i<from.length; i++)
	{
	
	empArray[i] = from[i].split("~"); 
	records=empArray[i];
	tempUserID=records[4];
	copyUserId(tempUserID);
	
	}
	for(var j=0; j<from.length; j++)
	{
		var single = empArray[j];

		if(checkDuplicate(single[4]))
		{
		//document.getElementById('empName').value = single[1];
		//document.getElementById('desigName').value = single[7];
		var empId = single[0];
		var empName = single[1];
		var userid = single[2];
		var userName = single[3];
		var postid = single[4];
		var postName = single[5];
		var desgid = single[6];
		var desigName = single[7];
		var depName=single[11];
		var srvcExpDate=single[12];
	
		var tb = document.getElementById('travelTableData');
		var tableLength = tb.rows.length;
		var nextId = tableLength-1;
	
	
	
		var row = tb.insertRow(tableLength);
		row.setAttribute("id","row_"+nextId);
		row.setAttribute('bgColor','#F8F8FF');
	
		//1st cell
	 	var cell1 = row.insertCell(0);
	
	  	cell1.innerHTML+="<INPUT type='checkbox' id='check"+nextId+"' name='check' indexPos="+nextId+" onclick='document.forms[0].MemSel.status=false'>";
		
	  
		//2rd cell
	 	 var cell2 = row.insertCell(1);
	  	cell2.innerHTML+="<label>"+empName+"</label>";
	  	cell2.innerHTML+="<INPUT type='hidden'  id='empName"+nextId+"' value='"+empName+"' name='empName"+nextId+"'  maxlength=70>";
		//3th cell
	  	var cell3 = row.insertCell(2);
	  	cell3.innerHTML+="<label>"+depName+"</label>";
	  	cell3.innerHTML+="<INPUT type='hidden'  id='depName"+nextId+"' value='"+depName+"'  name='depName"+nextId+"'  maxlength=70>";
		//4th cell
	  	var cell4 = row.insertCell(3);
	  	cell4.innerHTML+="<label>"+desigName+"</label>";
	  	cell4.innerHTML+="<INPUT TYPE='hidden' id='desigName"+nextId+"' value='"+desigName+"'  name='desigName"+nextId+"' maxlength=70>";
  	  	cell4.innerHTML+="<INPUT TYPE='hidden' id='leaveBalUserId"+nextId+"' value='"+userid+"'  name='leaveBalUserId"+nextId+"' >";
  	  	cell4.innerHTML+="<INPUT TYPE='hidden' id='userSrvcExpDate"+nextId+"' value='"+srvcExpDate+"'  name='userSrvcExpDate"+nextId+"' >";
  	  	
		ctr++;
		document.getElementById('nextId').value=nextId;
		if(document.getElementById('travelTable').style.display='none')
		{
			document.getElementById('MemSel').checked=false;
		}
		document.getElementById('travelTable').style.display='';
		document.getElementById('deleteMem').style.display='';
		}
	}
	var pager=new Pager('travelTableData', 10,obj); 
    pager.init();
    pager.showPageNav('pager', 'pageNavPosition'); 
    pager.showPage(1);
}
/*  Employee Search code End  */
function submitAdminLeaveMemberAdd()
{
	document.getElementById('Add').disabled=true;
	document.getElementById('Update').disabled=true;
	frmAdminLeaveMemberAdd.submit();
}
</script>
	
	
	<hdiits:form name="frmAdminLeaveMemberAdd" validate="true" method="post"
	action="hdiits.htm?actionFlag=passUserIdList" encType="multipart/form-data">
	
	<table id="adminLeaveTable" width="100%" align="center" >
	
	
		<tr align="center">
			<td><b><hdiits:caption	captionid="HRMS.AddEmpForBal" bundle="${LeaveCaption}"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b><hdiits:button id="search" type="button" name="search" tabindex="21"
				captionid="HRMS.Search" bundle="${LeaveCaption}" onclick="SearchEmp();" />
			</tr>
		<hdiits:hidden name="nextId" id="nextId"/>
		
		<hdiits:hidden name="leaveCodeArray" id="leaveCodeArray" />
		
		<tr id="travelTable" colspan="8" style="display:none" >
				<td colspan="4" align="center">
				<table id="travelTableData" style="border-collapse: collapse;" borderColor="BLACK"  border="1"
					cellpadding="0" cellspacing="0" BGCOLOR="#A9A9A9" width="80%" align="center">
					<tr colspan="4">
						<td align="center" width="8%" bgcolor="#C9DFFF"><b><hdiits:checkbox value="0" name="MemSel" id="MemSel" onclick="memberSelect();" /></b></td>
						<td align="center" width="15%" bgcolor="#C9DFFF"><b><hdiits:caption
							captionid="HRMS.Name" bundle="${LeaveCaption}"/></b></td>
						<td align="center" width="15%" bgcolor="#C9DFFF"><b><hdiits:caption
							captionid="HRMS.Department" bundle="${LeaveCaption}"/></b></td>
						<td align="center" width="15%" bgcolor="#C9DFFF"><b><hdiits:caption
							captionid="HRMS.Designation" bundle="${LeaveCaption}"/></b></td>
					
					</tr>
	
				</table>
				<div id="pageNavPosition"></div>
				</td>
		 </tr>
		 
		 
		 <tr style="display:none" id="deleteMem">
			<td class="fieldLabel" colspan="5" align="center" >
			<hdiits:button type="button" captionid="HRMS.Delete" bundle="${LeaveCaption}" name="deleteTourMem" id="deleteTourMem"
					onclick="deleteMember();" />
			<hdiits:hidden name="operation" id="operation"/>
			<hdiits:button type="button" captionid="HRMS.Add" bundle="${LeaveCaption}" name="Add" id="Add" onclick="document.forms[0].operation.value=1;submitAdminLeaveMemberAdd();"/>	
			<hdiits:button type="button" captionid="HRMS.Update" bundle="${LeaveCaption}" name="Update" id="Update" onclick="document.forms[0].operation.value=2;submitAdminLeaveMemberAdd();"/>
			</td>
		</tr>	
	</table>		
	</hdiits:form>	
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>