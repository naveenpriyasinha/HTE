<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>


<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="empNames" value="${resValue.empList}" > </c:set>	
<c:set var="code" value="${resValue.code}" > </c:set>

<script>
var currentRow=-1;
var mouseOverRow=-1;

function SelectRow(newRow)
{
   var row = document.getElementById(newRow);
   var strLength = row.title.length;
   var strEnd = row.title.indexOf('&');
   var strEmpid = row.title.substring(strEnd+1,strLength);
   var indexEmpid = strEmpid.indexOf('=');
   var empid = strEmpid.substring(indexEmpid+1,strLength);
   var empName = row.title.substring(0,strEnd);

   row.style.background='#AAF';
   if(currentRow!=-1)
    {
     var row=document.getElementById(currentRow);
     row.style.background='#FFF';
    }
        currentRow=newRow;       
     var empName = empName;
     var empid = empid;
     opener.document.frmBF.txtEmpName.value = empName;
     opener.document.frmBF.txtEmpId.value = empid;
     opener.getEmployeeDetails();
     window.close();
     
}



function showHandPointer(currow)
{
  document.body.style.cursor='pointer';
  var row = document.getElementById(currow);
  row.style.background='#E6E6FA';
    if(mouseOverRow!=-1)
    {
     var row=document.getElementById(mouseOverRow);
     row.style.background='#FFF';
    }
        mouseOverRow=currow;       
}
function showGenPointer(currentRow)
{
 document.body.style.cursor='default';
 if(mouseOverRow!=-1)
    {
     var row=document.getElementById(mouseOverRow);
     row.style.background='#FFF';
    }
}

function getEmpFirstNames()
{ 
  var empname =document.empNameFrm.txtEmpFirstName.value;
  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  url= uri+'&orgflag=true&emp_first_name='+ document.empNameFrm.txtEmpFirstName.value;
		  var actionf="findEmpName";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
			xmlHttp.onreadystatechange=find_EmpName;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);	
}

function find_EmpName()
{
if (xmlHttp.readyState==complete_state)
 { 						
			
					var XMLDoc=xmlHttp.responseXML.documentElement;			
                    var namesEntries = XMLDoc.getElementsByTagName('emp-mapping');
                    deleteExistingTable();
                    addTableHeader();
 
                     currentRow=-1;
                     mouseOverRow=-1;
                   for(j=0;j<namesEntries.length;j++)
				{
					var trow1=document.getElementById('empNamesTbl').insertRow();			
                    trow1.id = namesEntries[j].childNodes[0].text;
					var rowOver = 'showHandPointer(' + namesEntries[j].childNodes[0].text +')';
                    var rowOut = 'showGenPointer()';
                    trow1.onmouseover=new Function(rowOver);
                    trow1.onmouseout = new Function(rowOut);
                    trow1.style.background = "#FFFFFF";
                    
                    if(namesEntries[j].childNodes[1].text == 'null')
                     namesEntries[j].childNodes[1].text = '  ';
                    if(namesEntries[j].childNodes[2].text == 'null')
                     namesEntries[j].childNodes[2].text = '  ';
                    if(namesEntries[j].childNodes[3].text == 'null')
                     namesEntries[j].childNodes[3].text = '  ';
                     
					
					trow1.title = namesEntries[j].childNodes[1].text + ' ' + namesEntries[j].childNodes[2].text + namesEntries[j].childNodes[3].text + '&emp_id='  + namesEntries[j].childNodes[0].text;
			      	var tdata1=trow1.insertCell(0);
				    var tdata2=trow1.insertCell(1);
					var tdata3=trow1.insertCell(2);
								   		
			   		var cellFunc = 'SelectRow(' + namesEntries[j].childNodes[0].text +')';
                    
				    tdata1.innerHTML=namesEntries[j].childNodes[1].text;				   				 
				    tdata2.innerHTML=namesEntries[j].childNodes[2].text;				   
				   	tdata3.innerHTML=namesEntries[j].childNodes[3].text;
				   
				   	
				   	tdata1.onclick = new Function(cellFunc);
                    tdata2.onclick = new Function(cellFunc);
                    tdata3.onclick = new Function(cellFunc);    				  	

				}
                    
                    
  }
}

function addTableHeader()
{
  var trow1=document.getElementById('empNamesTbl').insertRow();
  var tdata1=trow1.insertCell(0);
  var tdata2=trow1.insertCell(1);
  var tdata3=trow1.insertCell(2);
  
  tdata1.innerHTML = '<div align="center"> <b> First Name'; 
  tdata2.innerHTML = '<div align="center"> <b>  Middle Name';
  tdata3.innerHTML = '<div align="center"> <b>  Last Name';
}

function deleteExistingTable()
 	{
  		var rows=document.getElementById("empNamesTbl").rows.length;
		
		for(var i=0;i<eval(rows);i++)
		{
			document.getElementById("empNamesTbl").deleteRow(document.getElementById("empNamesTbl").rowIndex);
    	}
    	
 	}
	

</script>
<hdiits:form name="empNameFrm" validate="true" method="POST" 
	action="" encType="multipart/form-data">

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1">Employee Names</a></li>
	</ul>
	</div>
	
	
<div class="tabcontentstyle">


	<div id="tcontent1" class="tabcontent" tabno="0">
	<br> <br>
	<div align="center"> <h1>
     Select Employee Name </h1> </div>
    <br> <br> <br>
    <hdiits:caption captionid="Search Name" caption="Search Name "/> &nbsp;&nbsp;
    <input type="text" name="txtEmpFirstName" onkeyup="getEmpFirstNames()"/>
    <br> <br> <br>
<table align="center" width="70%" datapagesize="10" bgcolor="#99CCFF" id="empNamesTbl">

<th align="center"> First Name </th>
<th align="center"> Middle Name </th>
<th align="center"> Last Name </th>

 <TR>
			
</TR>
<c:forEach items="${resValue.empList}" var="k" varStatus="i">
 <tr id="${i.index}" bgcolor="#FFFFFF" title="${k.empFname} ${k.empMname} ${k.empLname}&empid=${k.empId}" 
 onmouseover="showHandPointer(${i.index})" onmouseout="showGenPointer()">
 <td onclick="SelectRow(${i.index})"  align="center">
 <c:out value = "${k.empFname}" />
 </td>
 
  <td onclick="SelectRow(${i.index})" align="center">
 <c:out value = "${k.empMname}" />
 </td>
 
  <td onclick="SelectRow(${i.index})" align="center">
 <c:out value = "${k.empLname}" />
 </td>
 </tr>
 </c:forEach>			
<tR><td></td></tR>

 </table>
 
 

	</div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

