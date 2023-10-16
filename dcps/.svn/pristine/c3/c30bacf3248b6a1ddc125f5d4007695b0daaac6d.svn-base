
<% 
try
{
%>
<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<fmt:setBundle basename="resources.ess.quarter.QuarterAllocationLables" var="QtrLables" scope="request" />
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/ess/quarter/QuarteraddRecord1.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/ess/quarter/quarterAllot.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/eis/commonUtils.js"/>"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="DeptList" value="${resValue.DeptList}"></c:set>
<c:set var="EmpList" value="${resValue.EmpList}"></c:set>

<script type="text/javascript" language="JavaScript">

function ClosePage(frm){
frm.action="hdiits.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
frm.method="POST";
frm.submit();
}
function EditAllocation()
{
	
}
function setQId(qtrId)
{
	
	 document.getElementById('h1').value=qtrId;
}
function deleteExistingTable()
{
  		var rows=document.getElementById("QtrList").rows.length;
  	
		for(var i=eval(rows);i>0;i--)
		{
			document.getElementById("QtrList").deleteRow(document.getElementById("QtrList").rowIndex);
    	}
    	trow=document.getElementById('QtrList').insertRow();	
    	
    	trow.insertCell(0).innerHTML="";
		trow.insertCell(1).innerHTML="<b>"+" Quarter Type "+"</b>";
		trow.insertCell(2).innerHTML="<b>"+" Quarter Name "+"</b>";
		trow.insertCell(3).innerHTML="<b>"+" User id "+"</b>";
		trow.insertCell(4).innerHTML="<b>"+" User "+"</b>";
		trow.insertCell(5).innerHTML="<b>"+" Allocation Date"+"</b>";
		
 }
function getQuarterTypes(){
	var pID;
	var plID;
	pID=document.getElementById('Policestation');
	plID=document.getElementById('PoliceLine');
	
	try{   
		xmlHttp=new XMLHttpRequest();    
	}
	catch (e)
	{   
		try{
    		xmlHttp=new 
            ActiveXObject("Msxml2.XMLHTTP");      
    	}
		catch (e){
			try{
            	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
   			}
	   		catch (e){
	  	   		alert("Your browser does not support AJAX!");        
	        	return false;        
			}
		}			 
   }
   	var url = "hrms.htm?actionFlag=getQuarters&PoliceSTId="+pID.value+"&PoliceLine="+plID.value;    
	xmlHttp.open("POST", encodeURI(url) , true);			
	xmlHttp.onreadystatechange = processResponse5;
	xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
	xmlHttp.send(encodeURIComponent(null));
}
function processResponse5()
{
	if (xmlHttp.readyState == 4) {
	    if (xmlHttp.status == 200) {
	    	deleteExistingTable();  
	    	
	    	document.getElementById('EditAllocation').style.display='';			  	
			var xmlStr = xmlHttp.responseText; 
			
			var XMLDoc=getDOMFromXML(xmlStr);   // xmlHttp.responseXML.documentElement;						    			    	
			
			
			var quarterId = XMLDoc.getElementsByTagName('QuarterId');
			
			var quarterType = XMLDoc.getElementsByTagName('QuarterType');
			var quaterName = XMLDoc.getElementsByTagName('QuaterName');
			var allocatedToId = XMLDoc.getElementsByTagName('AllocatedToId');
			var allocatedTo = XMLDoc.getElementsByTagName('AllocatedTo');
			var allocationDate = XMLDoc.getElementsByTagName('AllocationDate');
			var xmlFile = XMLDoc.getElementsByTagName('XmlFileName');
			
			
	    	if(quarterId.length>0)
			{	    		
				for(var i = 0 ; i < quarterId.length ; i++ )
				{
					qId=quarterId[i].childNodes[0].text;	
					qType=quarterType[i].childNodes[0].text;	
					qName=quaterName[i].childNodes[0].text;	
					allocateId= allocatedToId[i].childNodes[0].text;	
					allocate=allocatedTo[i].childNodes[0].text;	
					alloDt=allocationDate[i].childNodes[0].text;	
					Xmlfile=xmlFile[i].childNodes[0].text;
					
					var byr=alloDt.substring(0,4);							
					var bmo=alloDt.substring(5,7);
					var bDate=alloDt.substring(8,10);							
					var	Datealloc= bDate+'/'+bmo+'/'+byr;

					var displayFieldArray = new Array(qType,qName,allocateId,allocate,Datealloc);
					var tRowId=addDBDataInTable('QtrList','encXML',displayFieldArray,Xmlfile,'','','');				
				}// end of for loop
   			}// end of if loop
   			var quarterId1 = XMLDoc.getElementsByTagName('QuarterId1');
   			var quarterType1 = XMLDoc.getElementsByTagName('QuarterType1');
			var quaterName1 = XMLDoc.getElementsByTagName('QuaterName1');
			var XMLfileName1=XMLDoc.getElementsByTagName('XmlFileName1');
			if(quarterId1.length>0)
			{
				for(var i = 0 ; i < quarterId1.length ; i++ )
				{
					trow=document.getElementById('QtrList').insertRow();	
					
					qId=quarterId1[i].childNodes[0].text;	
					qType=quarterType1[i].childNodes[0].text;	
					qName=quaterName1[i].childNodes[0].text;
					XmlFileName=XMLfileName1[i].childNodes[0].text;	
										
										
					var displayFieldArray = new Array(qType,qName,"---","---","---");
					var tRowId=addDBDataInTable('QtrList','encXML',displayFieldArray,XmlFileName,'','','');						
				}// end of for loop
			}//end of if loop
	    }
	}
}
function convertDate(date)
{
	var arr1 = date.split(" ");
	var str=arr1[0];
	var arr2=str.split("-");
	return arr2[2]+"/"+arr2[1]+"/"+arr2[0];
}

var UserId;
var userName;
function GetUserId(l)
{

	var separate=l.value.split("/");
	UserId=separate[0];
	document.getElementById('UserNmH1').value=UserId;

	userName=separate[1];
	document.getElementById('UserIDH1').value=userName;
}
var ROWid;
var RoqId2;
var Counter;
function showchecked(rowIds,cntr)
{	

	if(GlobCntr==0)
	{
		ROWid=rowIds;
		RoqId2=rowIds;
		Counter=cntr;
	}
	else
	{
		alert("<fmt:message key="HRMS.AlreadyOpenAlert"  bundle="${QtrLables}"/>");
	}			
}

var GlobCntr=0;
var GlobFlag=false;
function EditAllocation1()
{	
	GlobCntr++;
	GlobFlag=true;
	
	if(GlobCntr==1 && GlobFlag==true)
	{
		
		if(ROWid!=undefined)
		{
			sendAjaxRequestForEdit(ROWid, 'GettingVals');
		}
		else
		{
			alert("<fmt:message key="HRMS.FirstRecordSel"  bundle="${QtrLables}"/>");
			GlobCntr--;
		}
	}
	else 
	{
		alert("<fmt:message key="HRMS.AlreadyOpenAlert"  bundle="${QtrLables}"/>");
		GlobCntr--;
	}
	
}
var decXML1;
function GettingVals()
{
	if (xmlHttp.readyState == 4)
	   { 	
	  		var decXML = xmlHttp.responseText;
	  			decXML1=decXML;
			var xmlDOM = getDOMFromXML(decXML);
		
			document.getElementById('qtrtype').value=getXPathValueFromDOM(xmlDOM, 'hrQuaterTypeMst/quaType');
			
			document.getElementById('qtrnm').value=getXPathValueFromDOM(xmlDOM, 'quarterName');
			
			document.getElementById('userid').value="";
			document.getElementById('usernm').value="";
			document.getElementById('allodat').value="";
			
			var displayFieldArray=new Array('qtrtype','qtrnm','userid','usernm','allodat');
			updateDataInTable('encXML1',displayFieldArray);
			ROWid="";
		}
}

function validateAdd(){

 	var officeTypeArray = new Array('TypeOfOff','Jurisdiction','firstName','PoliceLine','alloctnDate');
	var statusOfficeTypeValidation =  validateSpecificFormFields(officeTypeArray);

	if(statusOfficeTypeValidation)
	{
	ReallocateData();
	}
	}

var updateRow1=null;
function ReallocateData()
{	
	if(RoqId2!=undefined)
	{
		var rwid=RoqId2;
		
		updateRow1=rwid;

		if(decXML1==undefined ||decXML1=="")
		{
			alert("<fmt:message key="HRMS.ClickEditAllocation"  bundle="${QtrLables}"/>");
		}
		else
		{
			if(UserId==undefined || UserId=="")
			{	

				if(confirm("<fmt:message key="HRMS.VacateQuarter"  bundle="${QtrLables}"/>")==true)
				{
					document.getElementById('UserIDH1').value="";
					//document.getElementById('UserNmH1').value="";
					//document.getElementById('alloctnDate').value="";
				}
				else
				{
					alert("<fmt:message key="HRMS.UserSelect"  bundle="${QtrLables}"/>");
					return;
				}
			}
			else
			{
				
				if(document.getElementById('alloctnDate').value=="")
				{
					alert("<fmt:message key="HRMS.DateSelect"  bundle="${QtrLables}"/>");
					return;
				}
				
			}
			SavingNew();
		}
	}
	else
	{
		alert("<fmt:message key="HRMS.FirstRecordSel"  bundle="${QtrLables}"/>");
	}
}
function SavingNew()
{
			var xmlDOM = getDOMFromXML(decXML1);
			document.getElementById('qtrtype').value=getXPathValueFromDOM(xmlDOM, 'hrQuaterTypeMst/quaType');
			document.getElementById('qtrtypeId').value=getXPathValueFromDOM(xmlDOM, 'hrQuaterTypeMst/quaId');
			document.getElementById('qtrnm').value=getXPathValueFromDOM(xmlDOM, 'quarterName');
			document.getElementById('QuarterId').value=getXPathValueFromDOM(xmlDOM, 'quarterId');
			
			addOrUpdateRecord('updateRecord','UpdateAllocatnDtls',new Array('qtrtype','qtrtypeId','qtrnm','UserIDH1','UserNmH1','alloctnDate','QuarterId'));
			decXML1="";
}

function updateRecord()
{
	if(xmlHttp.readyState == 4)
	  {
	  	var encXML2=xmlHttp.responseText;
	  	
	  	var displayFieldArray = new Array("qtrtype","qtrnm","UserIDH1","UserNmH1","alloctnDate");
	  	updateDataInTable1('encXML',displayFieldArray,updateRow1);
	  
	  	addDataInTable1('Newtabl','encXML2', displayFieldArray, '', '', '','') 
	  	document.getElementById('Newtabl').style.display='none';
	  	GlobCntr--;
	  }
}



</script>
<br>
<hdiits:form name="form1" validate="true" action="hrms.htm?actionFlag=UpdateAllocateInDB" method="post" >



<hdiits:fieldGroup titleCaptionId= "HRMS.Quarter" bundle="${QtrLables}"  >
<!--  <TABLE width="100%">
<TBODY>
  <TR bgColor=#386cb7>
    <TD align="center" class=fieldLabel colSpan=5><FONT color=#ffffff ><STRONG><U><fmt:message key="HRMS.Quarter" bundle="${QtrLables}"/>
      </U></STRONG></FONT></TD></TR></TBODY>
</TABLE>
-->
<br>
<TABLE width="80%">
<TR>
	<TD>
		<b><hdiits:caption captionid="HRMS.TypeOfOffice" bundle="${QtrLables}" /></b>
	</TD>
	<TD>	
		<hdiits:select  captionid="HRMS.TypeOfOffice" bundle="${QtrLables}" validation="sel.isrequired" name="TypeOfOff" size ="1" sort="false" onchange="getJurisdiction(this,2)" mandatory="true">
		<hdiits:option value="0" ><fmt:message key="HRMS.SELECT"
				bundle="${QtrLables}" /></hdiits:option>
		<c:forEach var="dept" items="${DeptList}">
			
			<hdiits:option value="${dept.departmentId}"><c:out value="${dept.depName}"/></hdiits:option>
		</c:forEach>
		</hdiits:select>
	</TD>
	<TD>
		<b><hdiits:caption captionid="HRMS.NameOffice" bundle="${QtrLables}" /></b>
	</TD>
	<TD>	
		<hdiits:select  captionid="HRMS.NameOffice" id="Jurisdiction"  bundle="${QtrLables}" validation="sel.isrequired" name="Jurisdiction" size ="1" sort="false" onchange="getPoliceST(this,2)" mandatory="true">
		<hdiits:option value="0" ><fmt:message key="HRMS.SELECT"
				bundle="${QtrLables}" /></hdiits:option>
		</hdiits:select>
	</TD>
</TR>
<TR>
	<TD>
		<b><hdiits:caption captionid="HRMS.PoliceStationHQ" bundle="${QtrLables}" /></b>
	</TD>
	<TD>
		<hdiits:select  captionid="HRMS.PoliceStationHQ" bundle="${QtrLables}" validation="sel.isrequired" id="Policestation" name="Policestation" size ="1" sort="false"  onchange="getPoliceLine(this,2)" mandatory="true">
		<hdiits:option value="0" ><fmt:message key="HRMS.SELECT"
				bundle="${QtrLables}" /></hdiits:option>
		</hdiits:select>
	</TD>
	<TD>
		<b><hdiits:caption captionid="HRMS.PoliceLine" bundle="${QtrLables}" /></b>
	</TD>
	<TD>
		<hdiits:select  captionid="HRMS.PoliceLine" bundle="${QtrLables}" validation="sel.isrequired" id="PoliceLine" name="PoliceLine" size ="1" sort="false"  onchange="getQuarterTypes()" mandatory="true">
		<hdiits:option value="0" ><fmt:message key="HRMS.SELECT"
				bundle="${QtrLables}" /></hdiits:option>
		</hdiits:select>
	</TD>
</TR>
</TABLE>

<br>
<br>

<TABLE id=QtrList  border="1" style=display:none width="100%" style="border-collapse: collapse;" borderColor="BLACK"  >
</TABLE>
<br>
<TABLE id="EditAllocation" style="display:none" width="100%">
	<TR align="center">
		<TD><hdiits:button name="EditAllocation" type="button" value="Edit Allocation"  captionid="HRMS.EditAllocation" bundle="${QtrLables}" onclick="EditAllocation1()"/></TD>
	</TR>
</TABLE>



  <TABLE border="1" id="EmpList" width="100%" style="border-collapse: collapse;" borderColor="BLACK"  >
	
	<TR >
		<TD class="fieldLabel" align="center" 	bgcolor="#C9DFFF" ></TD>
		<TD class="fieldLabel" align="center"  	bgcolor="#C9DFFF" ><b><hdiits:caption  captionid="HRMS.EmpName" bundle="${QtrLables}" /></b></TD>
		<TD class="fieldLabel" align="center"  	bgcolor="#C9DFFF" ><b><hdiits:caption  captionid="HRMS.Designation" bundle="${QtrLables}" /></b></TD>	
	</TR>
			<c:forEach var="EmpList" items="${EmpList }">
				<tr>
					<td><hdiits:radio name="EmpId" id="EmpId" value="${EmpList.userName}/${EmpList.userId}" onclick="GetUserId(this)" /></td>
 					<td><c:out value="${EmpList.userName}"></c:out> </td>
 					<td><c:out value="${EmpList.designation}"></c:out></td>
				</tr>	
			</c:forEach>
		
</TABLE>

  <!--   <table width="100%">
 <tr>
 <td width="100%">
	<iframe  scrolling=""  name="applicationReport" onload=''   id="applicationReport" src="hrms.htm?actionFlag=getQtrUserMpgDtls" frameborder="0" frameborder="0" width="100%" height="200px" align="right"  >
	</iframe>	
</td>
</tr>
</table>
 
 <display:table list="${resValue.EmpList}" id="EmpList" style="width:100%" pagesize="10" requestURI="" defaultsort="1"  defaultorder="ascending">
 	  <display:setProperty name="paging.banner.placement" value="bottom"/>
 
			<display:column class="tablecelltext" titleKey="HRMS.Select"  headerClass="datatableheader" sortable="false" style="text-align: center"><hdiits:radio name="EmpId" id="EmpId" value="${EmpList.userName}/${EmpList.userId}" onclick="GetUserId(this)" /></display:column>
			<display:column class="tablecelltext" titleKey="HRMS.EmpName" sortable="false" headerClass="datatableheader"  >${EmpList.userName}</display:column>		
			<display:column class="tablecelltext" titleKey="HRMS.Designation"  sortable="false" headerClass="datatableheader" >${EmpList.designation} </display:column>  	

 </display:table>  

-->



<table>
	<tr>
		<td>
			 <hdiits:caption captionid="HRMS.AllocatnDte" bundle="${QtrLables}"/>
		</td>
		<td>
			<hdiits:dateTime name="alloctnDate" captionid="HRMS.AllocatnDte" bundle="${QtrLables}" validation="txt.isrequired" mandatory="true" maxvalue="29/12/2099"/>
		</td>
		<td><hdiits:button type="button" name="Reallocate" id="Reallocate" value="Reallocate" captionid="HRMS.Reallocate" bundle="${QtrLables}" onclick="validateAdd()"/>
		</td>
	</tr>
</table>

<center>
	<hdiits:formSubmitButton  name="UpdateChanges" type="button" id="UpdateChanges" value="UpdateChanges"	captionid="HRMS.Save" bundle="${QtrLables}"/>
	<hdiits:button name="Close" type="button" value="Close"  captionid="HRMS.Close" bundle="${QtrLables}" onclick="ClosePage(document.form1);"/>
</center>
<table id="Newtabl" style="display:none">
<tr>
</tr>
</table>
</hdiits:fieldGroup>
<input type="hidden" name="h1" id="h1"/>
<hdiits:hidden name="qtrtype" id="qtrtype"/>
<hdiits:hidden name="qtrtypeId" id="qtrtypeId"/>
<hdiits:hidden name="qtrnm" id="qtrnm"/>
<hdiits:hidden name="userid" id="userid"/>
<hdiits:hidden name="usernm" id="usernm"/>
<hdiits:hidden name="allodat" id="allodat"/>
<hdiits:hidden name="UserIDH1" id="UserIDH1"/>
<hdiits:hidden name="UserNmH1" id="UserNmH1"/>
<hdiits:hidden name="QuarterId" id="QuarterId"/>

<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' controlNames="TypeOfOff,Jurisdiction,Policestation,PoliceLine" />

<hdiits:validate controlNames="test" locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>

<%
}
catch(Exception e){
	e.printStackTrace();
}
 %>