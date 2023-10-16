<%
try {
%>
<%@ include file="../../../core/include.jsp"%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<html>
<link rel="stylesheet" href="<c:url value="/themes/${themename}/calendar.css"/>" type="text/css" />
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/addRecord.js"></script>
<script type="text/javascript"  src="<c:url value="/script/common/base64.js"/>"></script>
<script type="text/javascript" src="script/hrms/hr/incharge/modifyInchargeApp.js"></script>

<fmt:setBundle basename="resources.hr.incharge.InchargeAlertMessage" var="alertLables"
	scope="request" />
<fmt:setBundle basename="resources.hr.incharge.InchargeLabels" var="CapLabels"
	scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="applicationName" value="${resultValue.applicationName}"></c:set>
<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="tableBGColor" value="#F0F4FB"></c:set>

<hdiits:form name="modifyIncharge"  id="modifyIncharge"   validate="true" method="POST"   encType="text/form-data" > 

<SCRIPT LANGUAGE="JavaScript">


// Global Variable
var tblRowId=1;
 var cnt=1;
var textVal=1;
var postRowId;
var gvindex;
var setVal='$';
var tvlIndexVal=1;
var row = null;
var inchRow;
var editRow;
var modifyBy;
var modifyDate;
var tempFileId;
var tempVar=0;
var temp=new Array();

// Search Emp
function SearchEmp(){
		
		document.getElementById("searchStatusFlg").value="yes";
		var href = "hrms.htm?actionFlag=getEmpSearchSelData&multiple=false&searchFieldName=searchText";
		window.open(href,'chield', 'width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
	
}


function openSearchWindow(textBoxId, index){

	postRowId=textBoxId;
	gvindex=index;
	
	document.getElementById("searchStatusFlg").value="no";

	var href = "hrms.htm?actionFlag=getEmpSearchSelData&multiple=false&searchFieldName="+postRowId;
	window.open(href,'chield', 'width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
	
}


var empArrayInch = new Array();

function empSearch(from, fieldName){

	for(var i=0; i<from.length; i++){
	
		empArrayInch[i] = from[i].split("~"); 
	
	}

	var empRlt = empArrayInch[0];
	var userID = empRlt[2];		
	var hVal='hdtxt'+gvindex;
		
	// get new user Id
	var tmp=new Array();
		if(document.getElementById("searchStatusFlg").value=="yes")
		{
			document.getElementById('searchText').value=empRlt[1] ;
			document.getElementById('textUserId').value =trimAll(userID);
	
		}
		else
		{

 		tmp=document.getElementById(hVal).value.split(',');
		//End of new User Id
		document.getElementById(fieldName).value="";	
		if(document.getElementById(tvlIndexVal).value == trimAll(userID) ) {
		alert('<fmt:message  bundle="${alertLables}" key="IC.notEligibleInchargePerson"/>');
		}
		else  {
			document.getElementById(hVal).value=tmp[0]+','+tmp[1]+','+tmp[2]+','+trimAll(userID)+','+tmp[4]+','+tmp[5];
			document.getElementById(fieldName).value=empRlt[1];
			document.getElementById(gvindex).value=trimAll(empRlt[4]);
		}
	}

}	

//End Search Emp

// XML Search Data
function getLeavePersonPostDetail()
{	
	//tblRowId=1;
	var tblength=document.getElementById('searchEmpResult');
     	//var oldlen=row.rows.length;
     	for(var i=tblength.rows.length;i>1;i--)
     	{
     			document.getElementById('searchEmpResult').deleteRow(1);     	 		
     	}     	
	tblRowId =1;
	var tblength=document.getElementById('editEmpResult');
     	//var oldlen=row.rows.length;
     	for(var i=tblength.rows.length;i>1;i--)
     	{
     			document.getElementById('editEmpResult').deleteRow(1);     	 		
     	}     	

  tempFileId="";
  tempVar=0;
	 
	cnt=1;
	
	addOrUpdateRecordForSearchResult('getSearchDetail','searchForModifyInchgApp',new Array('textUserId'));
	var xmlKey=document.getElementById('encPostDetail');
	var response=xmlKey.value;
	
	var ajaxKeyArray=response.split(","); 
	for(var i=0;i<ajaxKeyArray.length;i++)
		{
			xmlHttp=GetXmlHttpObject();
			if (xmlHttp==null) 
			{
			  alert('<fmt:message  bundle="${alertLables}" key="IC.notSupportAJAX"/>');
			  return;
			}
			
			var xmlDom=getDOMFromXML(ajaxKeyArray[i]);
			if (xmlDom != null && xmlDom.getElementsByTagName('Flag') != null)
			{				
				document.getElementById('searchEmpResult').style.display="none";
				document.getElementById('editEmpResult').style.display="none";
				document.getElementById('subForm').style.display="none";
				document.getElementById('ICinchgDtl').style.display="";
				document.getElementById('ICeditInchgDtl').style.display="none";
				var tripdtls=document.getElementById('searchEmpResult');  
			    tripdtls.style.display="";
			    var row=tripdtls.insertRow();
	            
	           row.align="center";
	            var cell0=row.insertCell(0);
	            cell0.colSpan="9";
	            cell0.innerHTML="<fmt:message  bundle="${alertLables}" key="IC.noRecordFound"/>";	
	            //alert('<fmt:message  bundle="${alertLables}" key="IC.noRecordFound"/>');
				return;
			}
			else
			{ 
				var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + ajaxKeyArray[i];
				xmlHttp.onreadystatechange = function() 
				{
					eval('populateSearchResult()'); 
				}
				xmlHttp.open("POST",encodeURI(url),false);
				xmlHttp.send(null);	
				document.getElementById('ICinchgDtl').style.display='';
			}					
		}		
}

function getSearchDetail()
{
	
	if (xmlHttp.readyState == 4) 
		{
			var encXML=xmlHttp.responseText;
			var tmp=encXML;
			var len=tmp.length;
			var subXml=tmp.substring(0,len);
			document.getElementById('encPostDetail').value=subXml;
		}
}	
function addOrUpdateRecordForSearchResult(methodName, actionFlag, fieldArray) 
	{	
		xmlHttp=GetXmlHttpObject();
		if (xmlHttp==null) {
		 alert('<fmt:message  bundle="${alertLables}" key="IC.notSupportAJAX"/>');
		 return;
		} 
		var reqBody = getRequestBody(fieldArray);
		var url='hdiits.htm?actionFlag=' + actionFlag + '&' + reqBody;
		methodName = methodName + "()";
		xmlHttp.onreadystatechange=function() { eval(methodName); }	
		xmlHttp.open("POST",encodeURI(url),false);
		xmlHttp.send(null);
	}

function populateSearchResult()
{
	if (xmlHttp.readyState == 4) 
	 { 				 		 			  
		  	var decXML = xmlHttp.responseText;
		  	var xmlDom=getDOMFromXML(decXML);		  	
			var tripdtls=document.getElementById('searchEmpResult');  
			tripdtls.style.display="";
			var row=tripdtls.insertRow();
			row.align ='center';
			row.id= 'row'+tblRowId;
		 
			inchRow = row.id;
			var usrPostId=getXPathValueFromDOM(xmlDom,'postId'); 
			var hiddenVal=getXPathValueFromDOM(xmlDom,'appUserId')+','+getXPathValueFromDOM(xmlDom,'appName')+','+getXPathValueFromDOM(xmlDom,'inchgPostName')+','+getXPathValueFromDOM(xmlDom,'inchgUserId')+','+getXPathValueFromDOM(xmlDom,'inchgName')+','+getXPathValueFromDOM(xmlDom,'frmDate')+','+getXPathValueFromDOM(xmlDom,'toDate')+','+getXPathValueFromDOM(xmlDom,'inchgPostHeld')+','+getXPathValueFromDOM(xmlDom,'hrInchgDtlRlt')+','+getXPathValueFromDOM(xmlDom,'hrInchgDtlId')+','+getXPathValueFromDOM(xmlDom,'applicationName'); 
			row.insertCell(0).innerHTML=tblRowId + "<INPUT type='hidden' name='hdId" + tblRowId + "' id='hdId" + tblRowId + "' value='" + hiddenVal + "'/>";
			row.insertCell(1).innerHTML=getXPathValueFromDOM(xmlDom,'appName'); 
			row.insertCell(2).innerHTML=getXPathValueFromDOM(xmlDom,'inchgPostName');
			row.insertCell(3).innerHTML=getXPathValueFromDOM(xmlDom,'inchgName');
			row.insertCell(4).innerHTML=getXPathValueFromDOM(xmlDom,'frmDate');
			row.insertCell(5).innerHTML=getXPathValueFromDOM(xmlDom,'toDate');
			row.insertCell(6).innerHTML=getXPathValueFromDOM(xmlDom,'department');
			row.insertCell(7).innerHTML=getXPathValueFromDOM(xmlDom,'location');
			if(getXPathValueFromDOM(xmlDom,'inchgStatus')=="Modify"){
			row.insertCell(8).innerHTML=' &nbsp;';
			modifyBy=getXPathValueFromDOM(xmlDom,'modfiyBy');
			modifyDate = getXPathValueFromDOM(xmlDom,'modifyDate');
			  	document.getElementById(row.id).style. background= "#FFFFCC ";
        
			}
			else{
			row.insertCell(8).innerHTML="<A NAME='" +'href'+ tblRowId + "' HREF='javascript:{editInchargeDetail(\""+tblRowId+"\",\""+inchRow+"\")}'>Edit</A>";
			
			}
			row.insertCell(9).innerHTML="<INPUT type='hidden' name='" + tblRowId + "' id='" + tblRowId + "' value='"+getXPathValueFromDOM(xmlDom,'appUserId')+"'/>";
			var rcell=row.cells;
			rcell[9].style.display="none";
			tblRowId=tblRowId + 1;
	     
	     
	 }	
}	

function modifyInchargeDetail(){
document.getElementById('modName').value= modifyBy;
            document.getElementById('modDate').value= modifyDate;
            document.getElementById('modify').style.display= "";
            
}

// End of XML Search Data

function editInchargeDetail(tvlIndex,rowid)
{
	var hdindex='hdId'+tvlIndex;
    
	temp=document.getElementById(hdindex).value.split(',');
 	
	if(tempVar==0){
	   	tempFileId=temp[9];
		tempVar=1; 
	
	}
   if(tempFileId==temp[9] ){ 
    
     document.getElementById('modify').style.display= "none";
     document.getElementById('ICeditInchgDtl').style.display='';
     var tblSearchPostRlt =document.getElementById('searchEmpResult');
     tblSearchPostRlt.rows[rowid].cells[8].innerHTML='<font color="red" >Open</font>';
	 tvlIndexVal=tvlIndex;
	 document.getElementById('subForm').style.display="";
	 var tbleditEmpResult=document.getElementById('editEmpResult');  
	
	 tbleditEmpResult.style.display="";
	 var row=tbleditEmpResult.insertRow();
	 row.align='center';
	 row.id='edit'+tvlIndex;
	 editRow = row.id;
	 var tval='txt'+tvlIndex;
	 var hiddVal=temp[8]+','+temp[9]+','+temp[7]+','+temp[3]+','+temp[5]+','+temp[6];
	 
	
	
	row.insertCell(0).innerHTML = cnt+"<INPUT type='hidden' name='hdtxt" +tvlIndex + "' id='hdtxt" + tvlIndex + "' value='" + hiddVal + "'/>"+"<INPUT type='hidden' name='" + textVal + "' id='" + textVal+ "' value=' '/>";
	row.insertCell(1).innerHTML=temp[1];
	row.insertCell(2).innerHTML=temp[2];
	row.insertCell(3).innerHTML= "<input type='text' id='"+tval+"' name='"+tval+"' readonly='true' value='"+temp[4]+"'>"+"<img src='images/search_icon.gif' onclick='openSearchWindow(\""+tval+"\", \""+tvlIndex+"\")'>";
	
	row.insertCell(4).innerHTML="<input type='text' name='frm"+tvlIndex+"' onkeypress='return checkDateNumber()' class='texttag'  value='"+temp[5]+"'  maxlength=10 size=10 readonly='readonly' />"+"<img style='cursor:hand' id='img_fromdate"+tvlIndex+"' src=themes/defaulttheme/images/taglib/ico-calendar.gif width=20 onClick=window_open('frm"+tvlIndex+"',375,570,'','frm"+tvlIndex+",Please~enter~valid~$CPTN,Incharge~Date,0,31/12/2099,Please~enter~$CPTN~less~than~31/12/2099') >";
	
	row.insertCell(5).innerHTML="<input type='text' name='to"+tvlIndex+"' onkeypress='return checkDateNumber()' class='texttag'  value='"+temp[6]+"'  maxlength=10 size=10 readonly='readonly' />"+"<img style='cursor:hand' id='img_todate"+tvlIndex+"' src=themes/defaulttheme/images/taglib/ico-calendar.gif width=20 onClick=window_open('to"+tvlIndex+"',375,570,'','to"+tvlIndex+",Please~enter~valid~$CPTN,Incharge~Date,0,31/12/2099,Please~enter~$CPTN~less~than~31/12/2099') >";
	
	row.insertCell(6).innerHTML="<A HREF='javascript:{deletesearchPostResultTblRow(\""+tvlIndex+"\",\""+editRow+"\")}'>Delete</A>";
  	cnt=cnt+1;
	var hval='hdtxt'+tvlIndex;
	textVal=textVal+1;				
}
else{
 		alert('<fmt:message  bundle="${alertLables}" key="IC.cantModify"/>');
		return;
}

}

function deletesearchPostResultTblRow(delRowId,rowid)
{
	 textVal=textVal-1;
	 tblRowId= delRowId;
	 
	 inchRow='row'+ delRowId;
	 var tblSearchPostRlt =document.getElementById('searchEmpResult');
     tblSearchPostRlt.rows['row'+delRowId].cells[8].innerHTML="<A NAME='" +'href'+ tblRowId + "' HREF='javascript:{editInchargeDetail(\""+tblRowId+"\",\""+inchRow+"\")}'>Edit</A>";
	 delRowIdG = delRowId;
 
	 row=document.getElementById('edit'+delRowId);
	 row.parentNode.deleteRow(row.rowIndex);		
 	
 	var tbleditEmpResult=document.getElementById('editEmpResult');  
 if(document.getElementById('editEmpResult').rows.length==1){
	
		 document.getElementById('editEmpResult').style.display="none";
    	 document.getElementById('ICeditInchgDtl') .style.display="none";	
	     document.getElementById('subForm').style.display="none";
	     cnt=1;
	     tempVar=0;
	     tempFileId="";
	 }
}



function trimAll(sString) 
{ 
	var ss=sString.trim();
	return ss; 
} 

function submitModifyForm1()
{
 
	var tblobj=document.getElementById("editEmpResult");
	var tbllen=tblobj.rows.length;
	 
	var newval='newInchg';
 	
 	
 	
	
		for(var i=0;i<document.modifyIncharge.elements.length;i++)
 		{  
			 if(document.modifyIncharge.elements[i].id.substring(0,3)=='txt'){
			
				var rowNo=document.modifyIncharge.elements[i].id.substring(3,document.modifyIncharge.elements[i].id.length)
				var hval='hdtxt'+rowNo;
				 
				var frmdt='frm'+rowNo;
	    		var todt='to'+rowNo;
	 			var tmp=new Array();
	 			var temp1=document.getElementById(hval).value+','+document.getElementById(frmdt).value+','+document.getElementById(todt).value;
	 			tmp=temp1.split(',');
	 	
			if(temp[10]=='Leave' || temp[10]=='Deputation'){
				
				if(getDateDiffInString(tmp[4],tmp[6]))
				{
					
					if(getDateDiffInString(tmp[7],tmp[5])) 	{
				
							if(getDateDiffInString(tmp[6],tmp[7]))	{
									
									setVal+=tmp[0]+','+tmp[1]+','+tmp[2]+','+tmp[3]+','+document.getElementById(frmdt).value+','+document.getElementById(todt).value+'$';
			 
							}
							else
							{
									alert('<fmt:message  bundle="${alertLables}" key="IC.inchrgeFrom"/>' +tmp[4]+ '<fmt:message  bundle="${alertLables}" key="IC.dateTo"/>' +tmp[5]+ '<fmt:message  bundle="${alertLables}" key="Ic.dt"/>');  
			 						return ;
							}
					}
					else
					{
							alert('<fmt:message  bundle="${alertLables}" key="IC.inchrgeFrom"/>' +tmp[4]+ '<fmt:message  bundle="${alertLables}" key="IC.dateTo"/>' +tmp[5]+ '<fmt:message  bundle="${alertLables}" key="Ic.dt"/>');  
    						return ;
					}
	  			}	
	  			else {
	 
	  				alert('<fmt:message  bundle="${alertLables}" key="IC.inchrgeFrom"/>' +tmp[4]+ '<fmt:message  bundle="${alertLables}" key="IC.dateTo"/>' +tmp[5]+ '<fmt:message  bundle="${alertLables}" key="Ic.dt"/>');  
					return;
	  			}
	
			}
			else{
			
				if(getDateDiffInString(temp[5],tmp[6])){	
					
					
					if(getDateDiffInString(temp[5],tmp[7])){
					
							setVal+=tmp[0]+','+tmp[1]+','+tmp[2]+','+tmp[3]+','+document.getElementById(frmdt).value+','+document.getElementById(todt).value+'$';
					
					}
					else{
					
							alert('<fmt:message bundle="${alertLables}" key="IC.fromDtLessThanToDate"/>');
							return;
					
					}
			
				}
				else{
				
						alert('<fmt:message bundle="${alertLables}" key="IC.frmDateGe" /> '+temp[5]);
						return;
				}
			}
		}
		
	}
	
    document.getElementById('setVal').value=setVal;
	document.getElementById('modifySubForm').disabled=true; 
	document.getElementById('frmClose1').disabled=true; 
 	document.modifyIncharge.action="hdiits.htm?actionFlag=addModifyInchgApp";  //       &setVal="+setVal;
 	document.modifyIncharge.submit();
	
}
// Check post Id
function checkPostId(appPostId,newInchgPostId)
{
	return true;
	// Disable Post check function
	/*		document.getElementById('hiddenResVal').value=appPostId+','+newInchgPostId+',';
		    functionCheckpostLevel('postLevel','checkPostLevel',new Array('hiddenResVal'));	    
	   
		    var xmlKey=document.getElementById('assPostLevel').value;
	    	
		     if(xmlKey == 'true')   {
	    	 	return true; 
		     }
		     else  {
	     		alert('<fmt:message  bundle="${alertLables}" key="IC.postNotSame"/>');
		     	return false;
		     }		
		// End of  Check Post Level	
	*/	
}
function functionCheckpostLevel(methodName, actionFlag, fieldArray)
{
	xmlHttp=GetXmlHttpObject();
		if (xmlHttp==null) {

		 alert('<fmt:message  bundle="${alertLables}" key="IC.notSupportAJAX"/>');
		  return;
		} 
		var reqBody = getRequestBody(fieldArray);
		var url='hdiits.htm?actionFlag=' + actionFlag + '&' + reqBody;
		methodName = methodName + "()";
		xmlHttp.onreadystatechange=function() { eval(methodName); }	
		xmlHttp.open("POST",encodeURI(url),false);
		xmlHttp.send(null);
}

function postLevel()
{
	
	if (xmlHttp.readyState == 4) 
		{
			var encXML=xmlHttp.responseText;
			var tmp=encXML;
			var len=tmp.length;
			var subXml=tmp.substring(0,len);
			document.getElementById('assPostLevel').value=subXml;
		}
}	
// End of check Post Id

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
			return true;
			}
		else
		{	return false;}
}

function closePage()
{
	document.modifyIncharge.action="hdiits.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
 	document.modifyIncharge.submit();
}

</SCRIPT>

 <style type="text/css">
  .modifyColor {
	background-color: #FFFFCC;
	border-color: black;
    
}
</style>


 	
<div id="tabmenu" >
			<ul id="maintab" class="shadetabs">
				<li class="selected"><a href="#" rel="tcontent1">
				<hdiits:caption captionid="IC.IncModify"  bundle="${CapLabels}" captionLang="single"/></a></li>
			</ul>
		</div>
		<div class="tabcontentstyle" style="height: 100%">
	
		
		<div id="tcontent1" class="tabcontent" tabno="0" >
	<br>

 
 
<table align="center" id="dtSearchDtl" width="100%" cellpadding="1" cellspacing="1" style="border-collapse: collapse;">
<center><tr>
<td align="right" width="50%"><hdiits:caption captionid="IC.inchgPerSearch" bundle="${CapLabels}"></hdiits:caption></td>
<td align="left"><input type="text" id="searchText" name="searchText" readonly="readonly" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue; ">
<input type="hidden" name="textUserId" />
<hdiits:hidden name="encPostDetail"/>
<img src="images/search_icon.gif" onclick="SearchEmp();">
</td>
</tr>
<tr>
</tr>
<tr><td>
</td>
</tr>
<tr><td>
</td>
</tr>
</table>
<table align="center" width="100%">
<tr align="center" width="100%">
	<td align="center" width="100%">
	<hdiits:button type="button" name="submiting" captionid="IC.search" bundle="${CapLabels}" onclick="getLeavePersonPostDetail();"/>
 	<hdiits:button name="frmClose" type="button" captionid="IC.close" bundle="${CapLabels}" onclick="closePage();"/>
	</td>
</tr>
</table>
 
 
<br>
 <br>

<!--<table width="100%" cellpadding="1" cellspacing="1" style="border-collapse: collapse;" id="ICinchgDtl"   >
<tr>
<td bordercolor="black"><span align=left style="border-color:black; border-width:1;"  class=modifyColor>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><b><fmt:message key="IC.reqInit" bundle="${CapLabels}" ></fmt:message><b> </td></tr>

<tr bgcolor="#386CB7" >
			<td class="fieldLabel"  align="center" >
			<font color="#ffffff"> <strong><u> <fmt:message key="IC.inchgDtl" bundle="${CapLabels}" /> </u></strong> </font>
 
</table>

--><hdiits:fieldGroup titleCaptionId="IC.inchgDtl" bundle="${CapLabels}" id="ICinchgDtl">

<table id="searchEmpResult" width="100%" align="center" border="1" style="border-collapse: collapse;display: none; background-color:${tableBGColor}" border=1 borderColor="black"  >
<tr class="datatableheader" style="background-color:${tdBGColor}">
		
		<td style="background-color:${tdBGColor}"><b><hdiits:caption captionid="IC.SrNo" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td><b><hdiits:caption captionid="IC.lieuOf" bundle="${CapLabels}"></hdiits:caption></b> </td>
		<td><b><hdiits:caption captionid="IC.post" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td><b><hdiits:caption captionid="IC.inchrgName" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td><b><hdiits:caption captionid="IC.frmDate" bundle="${CapLabels}"></hdiits:caption></b> </td>
		<td><b><hdiits:caption captionid="IC.toDate" bundle="${CapLabels}"></hdiits:caption></b> </td>
		<td><b><hdiits:caption captionid="IC.DeptName" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td><b><hdiits:caption captionid="IC.location" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td><b><hdiits:caption captionid="IC.action" bundle="${CapLabels}"></hdiits:caption> </b></td>
		
	</tr>	
 </table>
</hdiits:fieldGroup>
 <br>
 <br>
 
 <table id="modify" name ="modify" style="display:none;color:gray"  width="100%"> 
 <tr>	
	<td width="100%">
			
 <fieldset style="border-color: black;"><legend align="top" ><b>Modified Details</b></legend>
	<table align="center">
		<tr align="center" >	
   			<td style="color: gray;">Modified By:</td>
   			<td ><hdiits:text name="modName"  id="modName" style="border:none;color:black "/> </td>
			 <td align="center">Modified Date:</td>
 			<td><hdiits:text name ="modDate" id ="modDate" style="color: black; border:none"/></td>
 		</tr>
   </table>
  </fieldset>
 </td>
 </tr>
 </table><!--
  
 <table width="100%" cellpadding="1" id="ICeditInchgDtl" cellspacing="1" style="border-collapse: collapse;"  >
<tr bgcolor="#386CB7" >
			<td class="fieldLabel"  align="center" >
			<font color="#ffffff"> <strong><u> <fmt:message key="IC.editInchgDtl" bundle="${CapLabels}" /> </u></strong> </font>

</table>
--><hdiits:fieldGroup titleCaptionId="IC.editInchgDtl" bundle="${CapLabels}" id="ICeditInchgDtl">
 <table id="editEmpResult" width="100%" align="center" border="1" style="border-collapse: collapse;display: none; background-color:${tableBGColor}" border=1 borderColor="black" >
 <tr class="datatableheader" style="background-color:${tdBGColor}">
 		
		<td style="background-color:${tdBGColor}"><b><hdiits:caption captionid="IC.SrNo" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td><b><hdiits:caption captionid="IC.lieuOf" bundle="${CapLabels}"></hdiits:caption></b> </td>
		<td><b><hdiits:caption captionid="IC.post" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td><b><hdiits:caption captionid="IC.inchrgName" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td><b><hdiits:caption captionid="IC.frmDate" bundle="${CapLabels}"></hdiits:caption></b> </td>
		<td><b><hdiits:caption captionid="IC.toDate" bundle="${CapLabels}"></hdiits:caption></b> </td>
		<td><b><hdiits:caption captionid="IC.action" bundle="${CapLabels}"></hdiits:caption> </b></td>
		
		
	</tr>	
 </table>
</hdiits:fieldGroup>
 	<script>
		document.getElementById('ICinchgDtl').style.display='none';
		document.getElementById('ICeditInchgDtl').style.display='none';
	</script>

 <br>
 <table id="subForm" width="100%" align="center" style="display: none;" cellpadding="1" cellspacing="1" style="border-collapse: collapse;">
 <tr>
 <td align="center">
 <hdiits:button type="button" name="modifySubForm" id="modifySubForm" captionid="IC.submit" bundle="${CapLabels}" onclick="submitModifyForm1();"/>
 <b><hdiits:button name="frmClose1"  id="frmClose1" type="button" captionid="IC.close" bundle="${CapLabels}" onclick="closePage();"/></b>
 <hdiits:hidden name="assPostLevel"/>
<hdiits:hidden name="hiddenResVal"/>
<hdiits:hidden name="searchStatusFlg" id="searchStatusFlg" default="yes"/>
<hdiits:hidden name="setVal" id="setVal"/>  </td>
 </tr>
 </table>

	</div>

	</div>
	<script type="text/javascript">		
		initializetabcontent("maintab")
	</script>
	
	<fmt:setLocale value="${sessionScope.locale}"/>

</hdiits:form>

</html>

<%
	}

	catch (Exception e) {
		e.printStackTrace();
	}
%>
