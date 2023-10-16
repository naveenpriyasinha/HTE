
<%
try {
%>
<%@ include file="../../../core/include.jsp"%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<html>
<fmt:setBundle basename="resources.hr.incharge.InchargeAlertMessage"
	var="alertLables" scope="request" />
<fmt:setBundle basename="resources.hr.incharge.InchargeLabels"
	var="CapLabels" scope="request" />

<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/addRecord.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/base64.js"/>"></script>



<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="applicationName" value="${resultValue.applicationName}"></c:set>
<c:set var="searchList" value="${resultValue.searchList}" />
<c:set var="langId" value="${resultValue.langId}" />
<c:set var="dt" value="${resultValue.dt}" />

<style type="text/css">

	#hintbox{ /*CSS for pop up hint box */
		position:absolute;
		top: 0;
		background-color: white;
		width: 250px; /*Default width of hint.*/ 
		padding: 3px;
		border:1px solid black;
		font:normal 11px Verdana;
		line-height: inherit;
		z-index:100;
		border-right: 3px solid black;
		border-bottom: 3px solid black;
		visibility: hidden;

	}



	.hintanchor{ /*CSS for link that shows hint onmouseover*/
		font-weight: bold;
		color: navy;
		margin: 3px 8px;
	}

</style>

<SCRIPT LANGUAGE="JavaScript"><!--



// Global Variable
var textVal=1;
var postRowId;
var radioCheckedId;
var gvindex;
var dropPostIndex;
var xmlFromDate;
var xmlToDate;
var counter = 2;
var delRowIdG = null;
var cnt =0;
var rowCnt =1;
var checkArray = '';
var srchUsrId;	 
var editFlag = 0;
var emprowid;


function openSearchWindow(textBoxId, index){

	postRowId=textBoxId;
	gvindex=index;
	srchUsrId = "usrId"+index;
	document.getElementById("searchStatusFlg").value="no";

	var href = "hrms.htm?actionFlag=getEmpSearchSelData&multiple=false&searchFieldName="+textBoxId;
	window.open(href,'chield', 'width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
	
}


function empSearch(from, fieldName){

	var empArrayInch = new Array();

	for(var i=0; i<from.length; i++){
		empArrayInch[i] = from[i].split("~"); 
	}
	var empRlt = empArrayInch[0];
	var userID = empRlt[2];
	
	document.getElementById(postRowId).value="";
	
	var objChk = document.forms[0].chkBox;
	var tbl=document.getElementById('searchPostResult');
	var rblvals='checkbox'+gvindex;			
	var checkList = User_Details[0];
	
	if(checkList == trimAll(userID)) {
	
	    		alert('<fmt:message  bundle="${alertLables}" key="IC.notEligibleInchargePerson"/>');
	}
	else{
	
	 	if(checkList.length>4) {
	    
	    			 document.getElementById(postRowId).value=empRlt[1];
	    	 		 document.getElementById(srchUsrId).value=trimAll(userID);
	    	 		
	    }

	}    	 	
	
}


// Post Detail
function getLeavePersonPostDetail()
{
	
	var	chkBk =  document.forms[0].radiobt;
	var cnt=0;
	
	
	// remove record from searchPostResult
		var tblname = document.getElementById('searchPostResult');
     	var len=tblname.rows.length;  	
     	
     	for(var j=1;j<len;j++)
     	{     	 		
     	 		tblname.deleteRow(1);	     	 		
     	}   
     	textVal=1;
     	counter = 2;
	// End of remove record from  searchPostResult
	
	// check one radio button selected

	
	
	

	
	//alert('Radio Button Value >'+radioCheckedId);
	// Remove searchPostResult  Table Data
     	var srchPostRtl=document.getElementById('searchPostResult');
     	var srchPostRtlLengh=srchPostRtl.rows.length;
     	if( srchPostRtlLengh>1)
     	{
     		for(i=1;i<srchPostRtlLengh;i++)
     		{
     		srchPostRtl.deleteRow(i);
     		}
     	}
     	// End of searchPostResult  Table Data	
	
	addOrUpdateRecordForLeavePersonPost('addPostDetail','getLeavePersonDetail',new Array('radiobt'));
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
			var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + ajaxKeyArray[i];
			
			xmlHttp.onreadystatechange = function() 
								{
									eval('populatePostForm()'); 
								}
			xmlHttp.open("POST",encodeURI(url),false);
			xmlHttp.send(null);						
		}
		
		
		// add into drop down
		 var objChk = document.forms[0].chkBox;
		 
		 var resVal=null;
		 var cnt=0;
			
	  	
	    	
	    	for(count=1; count<textVal; count++){
	    	
	    		if(editFlag==0){
	    			getPost(User_Details[0],count);
	    		}
	    		
	    		if(document.getElementById("textVal"+count).value!=""){
	    			getTypeofPostDetail(document.getElementById('dropDown'+count),count);
	    		}
	    		
	    	}
	    		   // }	   
		// end in Drop down		
		
		document.getElementById("searchPostResult").style.display="";
		
		
		
}

function addPostDetail()
{	
	if (xmlHttp.readyState == 4) 
		{
			var encXML=xmlHttp.responseText;
			var tmp=encXML;
			var len=tmp.length;
			var subXml=tmp.substring(1,len);
			document.getElementById('encPostDetail').value=subXml;
		}
}	
function addOrUpdateRecordForLeavePersonPost(methodName, actionFlag, fieldArray) 
	{	
		xmlHttp=GetXmlHttpObject();
		if (xmlHttp==null) {
		  alert('<fmt:message  bundle="${alertLables}" key="IC.notSupportAJAX"/>');
		  return;
		} 
		var reqBody = getRequestBody(fieldArray);
		
		var url='hdiits.htm?actionFlag='+ actionFlag + '&' + reqBody;
		methodName = methodName + "()";
		xmlHttp.onreadystatechange=function() { eval(methodName); }	
		xmlHttp.open("POST",encodeURI(url),false);
		xmlHttp.send(null);
	}


function populatePostForm()
{
	if (xmlHttp.readyState == 4) 
		 { 		
		 		
		 		
		 		var tval="textVal"+textVal;
		 		var cval="checkVal"+textVal;
			  	var decXML = xmlHttp.responseText;
			  	
			  	var xmlDom=getDOMFromXML(decXML);
			  	
				var tripdtls=document.getElementById('searchPostResult');  
				tripdtls.style.display="";
				var row=tripdtls.insertRow();
				row.align='center';
				row.id='row'+counter;
				var usrPostId=getXPathValueFromDOM(xmlDom,'postId'); 
				
				var inPersonName=getXPathValueFromDOM(xmlDom,'inPersonName');
				var inUserId = getXPathValueFromDOM(xmlDom,'inUserId');
				var postName = getXPathValueFromDOM(xmlDom,'postName');
				editFlag = getXPathValueFromDOM(xmlDom,'flag');
				var reqId = getXPathValueFromDOM(xmlDom,'reqId');
				
				document.getElementById('InchargeDtl_ReqId').value = getXPathValueFromDOM(xmlDom,'fileId');
				
				row.insertCell(0).innerHTML="<input type='text'   style=' border:none; text-align: center; width:50px ' id='checkbox"+textVal+"' name='chkBox' value='"+textVal+"'/>"; 
				
				if(editFlag==1){
				
				row.insertCell(1).innerHTML="<align='center'><select name='dropDown"+textVal+"';  style='  text-align: center' onchange='getTypeofPostDetail(this,\""+row.id+"\")' ><option SELECTED value='"+usrPostId+"'>"+postName+"</option> </select>"+"<label class='mandatoryindicator'>*</label>"+"<input type='hidden' name='ReqId"+textVal+"' id='ReqId"+textVal+"' value='"+reqId+"' />";
				
				}
				else{
				editFlag==0;
				row.insertCell(1).innerHTML="<align='center'><select name='dropDown"+textVal+"';  style='  text-align: center' onchange='getTypeofPostDetail(this,\""+row.id+"\")' ><option SELECTED value='-1'><fmt:message key='IC.select' bundle='${CapLabels}'></fmt:message></option> </select>"+"<label class='mandatoryindicator'>*</label>"+"<input type='hidden' name='ReqId"+textVal+"' id='ReqId"+textVal+"' value='0' />";
				}
				row.insertCell(2).innerHTML="";//getXPathValueFromDOM(xmlDom,'postLookupDesc');
				if(editFlag==1){
				
						row.insertCell(3).innerHTML="<input type='text' id='"+tval+"' name='"+tval+"'  readonly='true' value='"+inPersonName+"' style='color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue; ' onmouseover='getEmpDtls(this,\""+textVal+"\")' class='hintanchor'>"+"<label class='mandatoryindicator'>*</label>"+"<img src='images/search_icon.gif' onclick='openSearchWindow(\""+tval+"\", \""+textVal+"\")'>"; 
				
				}else{
						
						row.insertCell(3).innerHTML="<input type='text' id='"+tval+"' name='"+tval+"'  readonly='true'  style='color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;' onmouseover='getEmpDtls(this,\""+textVal+"\")' class='hintanchor'>"+"<label class='mandatoryindicator'>*</label>"+"<img src='images/search_icon.gif' onclick='openSearchWindow(\""+tval+"\", \""+textVal+"\")'>"; 
				
				}
				
				/*row.insertCell(4).innerHTML="<input type='text' readonly name='frm"+textVal+"'   style=' text-align: center' onfocus='return checkDateNumber()'   class='dateTimetag'   value='"+getXPathValueFromDOM(xmlDom,'frmDate')+"'  maxlength=10 size=10  />"
				+"<img style='cursor:hand'  src=themes/defaulttheme/images/taglib/ico-calendar.gif width=20 onClick=window_open('frm"+textVal+"',375,570,'','frm"+textVal+"','Please~enter~valid~$CPTN,31/12/2099') > "
				+"&nbsp;&nbsp;<label class='mandatoryindicator'>*</label>";
				
				row.insertCell(5).innerHTML="<input type='text' readonly  name='to"+textVal+"' size='12' style=' text-align: center' onfocus='return checkDateNumber()'  class='texttag'   value='"+getXPathValueFromDOM(xmlDom,'toDate')+"'  maxlength=10 size=10  />"
				+"<img style='cursor:hand'  src=themes/defaulttheme/images/taglib/ico-calendar.gif width=20 onClick=window_open('to"+textVal+"',375,570,'','to"+textVal+"','Please~enter~valid~$CPTN,31/12/2099') > "
				+"&nbsp;&nbsp;<label class='mandatoryindicator'>*</label>";*/
				
				row.insertCell(4).innerHTML="<input type='text' name='frm"+textVal+"'  onkeypress='return checkDateNumber()' onfocus='validateInchargeDate()'  class='texttag'  value='"+getXPathValueFromDOM(xmlDom,'frmDate')+"'  maxlength=10 size=10 readonly='true' />"+"<img style='cursor:hand' id='img_frm"+textVal+"' src=themes/defaulttheme/images/taglib/ico-calendar.gif width=20 onClick=window_open('frm"+textVal+"',375,570,'','frm"+textVal+",Please~enter~valid~$CPTN,Incharge~Date,0,31/12/2099,Please~enter~$CPTN~less~than~31/12/2099') >";
				
					
				row.insertCell(5).innerHTML="<input type='text' name='to"+textVal+"'  onkeypress='return checkDateNumber()' onfocus='validateInchargeDate()'  class='texttag'  value='"+getXPathValueFromDOM(xmlDom,'toDate')+"'  maxlength=10 size=10 readonly='true' />"+"<img style='cursor:hand' id='img_to"+textVal+"' src=themes/defaulttheme/images/taglib/ico-calendar.gif width=20 onClick=window_open('to"+textVal+"',375,570,'','to"+textVal+",Please~enter~valid~$CPTN,Incharge~Date,0,31/12/2099,Please~enter~$CPTN~less~than~31/12/2099') >";	
				
				
				
				xmlFromDate=getXPathValueFromDOM(xmlDom,'frmDate');
				xmlToDate=getXPathValueFromDOM(xmlDom,'toDate');
				
				var todateVar='to'+textVal;
			 
			    if(editFlag==1){
			       row.insertCell(6).innerHTML="<A HREF='javascript:{deleteTblRowFrmDb(\""+row.id+"\",\""+textVal+"\")}'>Delete</A>";
			    row.insertCell(7).innerHTML="<input type ='text' style='display:none' name ='usrId"+textVal+"' id ='usrId"+textVal+"' value='"+inUserId+"'/>";  
				}
				else{
				   row.insertCell(6).innerHTML="<A HREF='javascript:{deletesearchPostResultTblRow(\""+row.id+"\")}'>Delete</A>";
				row.insertCell(7).innerHTML="<input type ='text' style='display:none' name ='usrId"+textVal+"' id ='usrId"+textVal+"'/>"+"<input type ='text' style='display:none' name ='postName"+textVal+"' id ='postName"+textVal+"'/>"+"<input type ='text' style='display:none' name ='dsgnName"+textVal+"' id ='dsgnName"+textVal+"'/>";  
				}
				radlVal=radioCheckedId;
				rowCnt =rowCnt+1;
				counter = counter + 1;
				
				
				textVal=textVal+1;			
				
				
		 }	
}	


function getEmpDtls(id,rid){

/*var inchargeUserId = document.getElementById('usrId'+rid).value;
var inchargePersonName = document.getElementById('textVal'+rid).value;
if(inchargeUserId!=''){
emprowid=id;
var url='hdiits.htm'+'?actionFlag=getInchargePersonDtls&inchargeUserId='+inchargeUserId+'&inchargePersonName='+inchargePersonName;

xmlHttp.open("POST", encodeURI(url) , true);			
			xmlHttp.onreadystatechange = getInchargeDtlFrmXML;
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));


//		window.open(href,'chield', 'width=420,height=150,toolbar=no,minimize=no,status=no,memubar=no,titlebar=0,location=no,scrollbars=no,top=100,left=100');
}
*/

	var postName = document.getElementById('postName'+rid).value;
	var dsgnName = document.getElementById('dsgnName'+rid).value;
	var tableobj1 = new Array();

	tableobj1[0]=postName;
	tableobj1[1]=dsgnName;
	if(postName !='' && dsgnName!=''){
		hrmsHint(tableobj1, id, event, 'auto');
	}



}

/*function getInchargeDtlFrmXML(){

if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{     
				var decXml = xmlHttp.responseText;
				
				var xmlDom=getDOMFromXML(decXml);
				
				var desgnName = xmlDom.getElementsByTagName('desgnName');
				var parentPostName = xmlDom.getElementsByTagName('parentPostName');
				var tableobj1 = new Array();
				
					for(var i=0; i<desgnName.length;i++){
				
						tableobj1[0]=desgnName[i].childNodes[0].text;
					}
				
					for(var i=0; i<parentPostName.length;i++){
				
						tableobj1[1]=parentPostName[i].childNodes[0].text;
					}
				
				

			var id = emprowid;
			hrmsHint(tableobj1, id, event, 'auto');
				
				
				
				}
			}
}
*/

function deleteTblRowFrmDb(delRowId, rowNo){

		var confirmation = window.confirm("Are you sure , you want to Delete the Incharge ?");
		
		if(confirmation==true){
		
			var delReqId = document.getElementById('ReqId'+rowNo).value;
		
			var url='hdiits.htm?actionFlag=delRecordFrmInchargeDtlRlt&reqId='+delReqId;
			xmlHttp.open("POST",encodeURI(url),false);
			xmlHttp.send(null);
			deletesearchPostResultTblRow(delRowId);
		}

}


var row = null;

function addsearchPostResultTblRow(){

  
	var a = document.getElementsByName('chkBox');
	
	for(i=1; i<=a.length; i++) {

		if(document.getElementById('frm'+i).value==''){
		
			alert('<fmt:message  bundle="${alertLables}" key="IC.frmDt"/>');
			return false;
		}
		
		if(document.getElementById('to'+i).value==''){
		
			alert('<fmt:message  bundle="${alertLables}" key="IC.toDt"/>');
			return false;
		}

	}  
	
	var tval="textVal"+textVal;
	var cval="checkVal"+textVal;
	row=document.getElementById('searchPostResult').insertRow();  
	editFlag=0;		
	var	toottipSize = '150px';
			//var row =addtblResult.insertRow();
				
				row.id='row'+counter;
				row.align='center';
				row.insertCell(0).innerHTML="<input type='text'   style=' border:none; text-align: center; width:50px ' id='checkbox"+textVal+"' name='chkBox' value='"+textVal+"'/>"; 
				row.insertCell(1).innerHTML="<select name='dropDown"+textVal+"' style=' text-align: center' onchange='getTypeofPostDetail(this,\""+row.id+"\")' ><option SELECTED value='-1'><fmt:message key='IC.select' bundle='${CapLabels}'></fmt:message></option> </select>"+"<label class='mandatoryindicator'>*</label>"+"<input type='hidden' name='ReqId"+textVal+"' id='ReqId"+textVal+"' value='0' />";
				row.insertCell(2).innerHTML="";//getXPathValueFromDOM(xmlDom,'postLookupDesc');
				row.insertCell(3).innerHTML="<input type='text' id='"+tval+"' name='"+tval+"' readonly='true' style='color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;' onmouseover='getEmpDtls(this,\""+textVal+"\")' class='hintanchor'>"+"<label class='mandatoryindicator'>*</label>"+"<img src='images/search_icon.gif' onclick='openSearchWindow(\""+tval+"\", \""+textVal+"\")'>";
				
				row.insertCell(4).innerHTML="<input type='text' name='frm"+textVal+"'  onkeypress='return checkDateNumber()' onfocus='validateInchargeDate()'  class='texttag' maxlength=10 size=10 readonly='true' />"+"<img style='cursor:hand' id='img_frm"+textVal+"' src=themes/defaulttheme/images/taglib/ico-calendar.gif width=20 onClick=window_open('frm"+textVal+"',375,570,'','frm"+textVal+",Please~enter~valid~$CPTN,Incharge~Date,0,31/12/2099,Please~enter~$CPTN~less~than~31/12/2099') >";
				row.insertCell(5).innerHTML="<input type='text' name='to"+textVal+"'  onkeypress='return checkDateNumber()' onfocus='validateInchargeDate()' class='texttag'   maxlength=10 size=10 readonly='true' />"+"<img style='cursor:hand' id='img_to"+textVal+"' src=themes/defaulttheme/images/taglib/ico-calendar.gif width=20 onClick=window_open('to"+textVal+"',375,570,'','to"+textVal+",Please~enter~valid~$CPTN,Incharge~Date,0,31/12/2099,Please~enter~$CPTN~less~than~31/12/2099') >";	
			
				var todateVar='to'+textVal;
				row.insertCell(6).innerHTML="<A HREF='javascript:{deletesearchPostResultTblRow(\""+row.id+"\")}'>Delete</A>";
				row.insertCell(7).innerHTML="<input type ='text' style='display:none' name ='usrId"+textVal +"' id ='usrId"+textVal +"'/>"+"<input type ='text' style='display:none' name ='postName"+textVal+"' id ='postName"+textVal+"'/>"+"<input type ='text' style='display:none' name ='dsgnName"+textVal+"' id ='dsgnName"+textVal+"'/>";  
				
				var objChk = document.forms[0].chkBox;
			
				getPost(User_Details[0],textVal);
					
				textVal+=1;
				counter = counter + 1;				
}

function deletesearchPostResultTblRow(delRowId)
{
	textVal = textVal -1 ;
	rowCnt = rowCnt - 1;
	delRowIdG = delRowId;
	row=document.getElementById(delRowId);
    row.parentNode.deleteRow(row.rowIndex);		
	
}

var cnt=0;

function createIncharge()
{
    
    var checkArray = '';
    
    //var objChk = document.forms[0].chkBox;
	var a = document.getElementsByName('chkBox');
	
	for(i=1; i<=a.length; i++) {
	 	  
	 	   		//var textBx = "textVal" + a[i].value;
	 	  		//var PostVal ="dropDown" + a[i].value;
	 	  		
	 	  		if(document.getElementById("dropDown"+i).value != -1){
		      		if(document.getElementById("textVal"+i).value == ''){
   	    	  			document.getElementById("textVal"+i).focus();
	    	   			alert('<fmt:message  bundle="${alertLables}" key="IC.selectInchargePerson"/>');	  
	    	   			return false;
	    		}
	    		}
	    		
	 	  
	 	  		cnt=1;
	 	  
	}
	
	
	    //var a = document.getElementsByName('chkBox');
	     var radioCheckedId = document.getElementById('radioValues').value;
	     
	 	 var checkList = radioCheckedId.split(",");
	 	 var frmDt= checkList[2];
	 	 var toDt = checkList[3];
	 	for(i=1;  i<=a.length ; i++) {
	 	 
	 	 	var textBx = "textVal"+i;
	    	var inchFrmDt = "frm"+i;
	    	var inchToDt ="to"+i;
	    	if(getDateDiffInString(frmDt,document.getElementById(inchFrmDt).value)){
	    		 
	    		 if(getDateDiffInString(document.getElementById(inchFrmDt).value,document.getElementById(inchToDt).value))
	    		  { 	
	    		  		if(getDateDiffInString(document.getElementById(inchToDt).value,toDt)){
	    		      	
	    		      		 checkArray=checkArray+','+i;
	    		       	}
	    		    	else{
	    		    		alert('<fmt:message  bundle="${alertLables}" key="IC.inchrgeFrom"/>' +frmDt+ '<fmt:message  bundle="${alertLables}" key="IC.dateTo"/>' +toDt+ '<fmt:message  bundle="${alertLables}" key="Ic.dt"/>');  
	    					return false;
	    		    	}
	    		 } 
	    		 else{
	    		  			alert('<fmt:message  bundle="${alertLables}" key="IC.inchrgeFrom"/>' +frmDt+ '<fmt:message  bundle="${alertLables}" key="IC.dateTo"/>' +toDt+ '<fmt:message  bundle="${alertLables}" key="Ic.dt"/>');  
	    					return false;
	    		}
	    	}	 
	    	else{
	    		    alert('<fmt:message  bundle="${alertLables}" key="IC.inchrgeFrom"/>' +frmDt+ '<fmt:message  bundle="${alertLables}" key="IC.dateTo"/>' +toDt+ '<fmt:message  bundle="${alertLables}" key="Ic.dt"/>');  
	    						return false;
	    		}
	    
	    
 
	}
	document.forms[0].checkArray.value = checkArray;
	//document.forms[0].action='hdiits.htm?actionFlag=setLeaveIncharge';
	//document.forms[0].submit();
	return true;
}

function validateInchargeDate(){

	var chkBoxLength = document.getElementsByName('chkBox');
	
	for(i=1; i<=chkBoxLength.length; i++) {
	
		if(i>1){
	    				
	    	for(var count = i-1; count>=1; count--){
	    			
	    				var frmDate = document.getElementById("frm"+i).value;
	    				var toDate	= document.getElementById("to"+i).value;
	    				
	    				var toDateprev  = document.getElementById("to"+count).value;
	    				var frmDateprev = document.getElementById("frm"+count).value;
	    		
	    		if(document.getElementById("dropDown"+i).value == document.getElementById("dropDown"+count).value){
	
		    			
	      				
	    				if(frmDate!='' || toDate!='' || toDateprev!='' || frmDateprev!=''){			
	    		
	    							if(getDateDiffInString(frmDate,frmDateprev)){
	    				
	    									if(getDateDiffInString(frmDateprev,toDate)){
	    						
	    											alert('<fmt:message  bundle="${alertLables}" key="IC.toDtOR"/> '+i);
	    											return false;
	    									}
	    							}
	    				
	    				
									if(getDateDiffInString(frmDateprev,frmDate)){
	    				
	    									if(getDateDiffInString(frmDate,toDateprev)){
	    						
	    											alert('<fmt:message  bundle="${alertLables}" key="IC.frmDtOR"/> '+i);
	    											return false;
	    									}
	    						
	    				
	    									if(getDateDiffInString(toDate,toDateprev)){
	    						
	    											alert('<fmt:message  bundle="${alertLables}" key="IC.toDtOR"/> '+i);
	    											return false;
	    									}
	    					
	    							}	    		
	    				
	    			
	    						if(getDateDiffInString(toDate,toDateprev)){
	    			
	    									if(getDateDiffInString(frmDateprev, toDate)){
	    					
	    											alert('<fmt:message  bundle="${alertLables}" key="IC.toDtOR"/> '+i);
					    							return false;
	    									}
	    				
	    						}
	    			
	    				    			
	    						if(getDateDiffInString(toDate, frmDate)){
	    				
	    								alert('<fmt:message  bundle="${alertLables}" key="IC.frmDtOR"/> '+i);
	    								return false;	
	    						}	
	    						
	    					}
	    					
	    				}			
	    			
	    			}		
	    		
				}
	 	  
			}

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

function checkInchrgPost()
{
	if (xmlHttp.readyState == 4) 
		 { 		
		 		  
			  	var decXML = xmlHttp.responseText;
			  	var xmlDom=getDOMFromXML(decXML);
		 }	
}

// get Post Value in Drop Down 
function getPost(radVal,index)
{
	
	var temp=new Array();
	temp=radVal.split(',');
	dropPostIndex=index;
	document.getElementById('radioUserId').value=temp[0];
	addOrUpdateRecordForGetPost('getPostDetail','getPersonPostDetail',new Array('radioUserId'));
	var xmlKey=document.getElementById('encRadioUserId');
	var response=xmlKey.value;
	var ajaxKeyArray=response.split(","); 
	for(var i=0;i<ajaxKeyArray.length - 1;i++)
		{
			xmlHttp=GetXmlHttpObject();
			if (xmlHttp==null) 
			{
			  alert('<fmt:message  bundle="${alertLables}" key="IC.notSupportAJAX"/>');
			  return;
			} 
			var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + ajaxKeyArray[i];
			xmlHttp.onreadystatechange = function() 
								{
									eval('populateGetPostForm()'); 
								}
			xmlHttp.open("POST",encodeURI(url),false);
			xmlHttp.send(null);	
		}
}

function getPostDetail()
{
	if (xmlHttp.readyState == 4) 
		{
			var encXML=xmlHttp.responseText;
			var tmp=encXML;
			var len=tmp.length;
			var subXml=tmp.substring(1,len);
			document.getElementById('encRadioUserId').value=subXml;
		}
}
function addOrUpdateRecordForGetPost(methodName, actionFlag, fieldArray) 
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
		xmlHttp.onreadystatechange = populateGetPostForm;
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));	
		
	}	
	
function populateGetPostForm()
{
	if (xmlHttp.readyState == 4) 
		 { 		
		 		var dpIndex="dropDown"+dropPostIndex;
			  	var decXML = xmlHttp.responseText;
			  	var XMLDoc=getDOMFromXML(decXML);
				var dpOpt=document.getElementById('dpIndex');  
				//if(XMLDoc.getElementsByTagName('Name')!= null)
				{
					var name1 = XMLDoc.getElementsByTagName('postName'); 	
					var value1 = XMLDoc.getElementsByTagName('PostId');
					var objPostCombo = document.getElementById(dpIndex);
					for ( var i = 0 ; i < name1.length ; i++ ) // adding a new vlaue in combo
		     		{
		     		    postNameStr=name1[i].childNodes[0].text;	     				    
		     		    pId=value1[i].childNodes[0].text;	
		     	//	    alert('Post  Name ' + postNameStr + '   Post Id : ' + pId);
		     		    
		     		    var y=document.createElement('option');
		     		    		y.text=postNameStr;
								y.value=pId;			 					
								try
		   						{
		    						objPostCombo.add(y,null); 	    						
		   						}
		 						catch(ex)
		   						{	   			 
		   			 				objPostCombo.add(y);	   			 				 
		   						}     			
		     		}
				
				} 						
		 }	
}

function getTypeofPostDetail(form,ddlUniqId)
{
	var postIdis=form.value;
	//alert("postIdis:::"+postIdis);
	
	 TblRowsId=ddlUniqId;
	 
	 if(postIdis == -1)
	 {
	 	alert('<fmt:message  bundle="${alertLables}" key="IC.selectPostName"/>');
	 		var tblSearchPostRlt=document.getElementById('searchPostResult');  
	 	tblSearchPostRlt.rows[TblRowsId].cells[2].innerHTML='';
	 	return;
	 }	
		try{   
    			xmlHttp=new XMLHttpRequest();    
	    	}
			catch (e)
			{    // Internet Explorer    
					try{
      					xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");      
      				}
		    		catch (e){
		          		try
        		  		{
                	           	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
        		  		}
				      	catch (e)
				      	{
			           	   	   alert('<fmt:message  bundle="${alertLables}" key="IC.notSupportAJAX"/>');
			            	  return false;        
			      		}
			 		}			 
        	}	
			
			var url = "hrms.htm?actionFlag=getUserPostType&postIdis="+postIdis;    
			xmlHttp.open("POST", encodeURI(url) , false);			
			xmlHttp.onreadystatechange = getPostType;
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));						
			
}	
 
function getPostType()
{ 

	if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{ 	      					  
				    	var xmlStr = xmlHttp.responseText;				    				    	
				    	var XMLDoc=getDOMFromXML(xmlStr);   // xmlHttp.responseXML.documentElement;						    			    					    					    						    					    	
				    	var posttype = XMLDoc.getElementsByTagName('PostType');
				    	var tblSearchPostRlt=document.getElementById('searchPostResult');  
				    	
				    	
				    	for ( var i = 0 ; i < posttype.length ; i++ ) // adding a new vlaue in combo
		     			{   var tval="PostVal"+TblRowsId; 
		     				var pType=posttype[i].childNodes[0].text;
		     				//tblSearchPostRlt.rows[tblsearchPostResultRow].cells[2].innerHTML=pType;
		     				tblSearchPostRlt.rows[TblRowsId].cells[2].innerHTML="<input type='text' id='"+tval+"' name='"+tval+"' value ='"+pType+"'readonly='true' style=' background:#F0F4FB; border:none ;text-align: center'>";
		     			
		     				//ddlId.options[ddlId.length]=pType;
		     			}								
				}	
			}			
}
// End of Post Value in Drop Down

function trimAll(sString) 
{ 
	var ss=sString.trim();
	return ss; 
} 

function getDateDiffInString(strDate1,strDate2)
{

    // alert(strDate1+"===="+strDate2);
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

/********** Code for Tooltip **********/

/***********************************************
* Show Hint script- By  223001

***********************************************/
		
var horizontal_offset="9px" //horizontal offset of hint box from anchor link

/////No further editting needed

var vertical_offset="0" //horizontal offset of hint box from anchor link. No need to change.
var ie=document.all
var ns6=document.getElementById&&!document.all

function getposOffset(what, offsettype){
var totaloffset=(offsettype=="left")? what.offsetLeft : what.offsetTop;
var parentEl=what.offsetParent;
while (parentEl!=null){
totaloffset=(offsettype=="left")? totaloffset+parentEl.offsetLeft : totaloffset+parentEl.offsetTop;
parentEl=parentEl.offsetParent;
}
return totaloffset;
}

function iecompattest(){
return (document.compatMode && document.compatMode!="BackCompat")? document.documentElement : document.body
}

function clearbrowseredge(obj, whichedge){
var edgeoffset=(whichedge=="rightedge")? parseInt(horizontal_offset)*-1 : parseInt(vertical_offset)*-1
if (whichedge=="rightedge"){
var windowedge=ie && !window.opera? iecompattest().scrollLeft+iecompattest().clientWidth-30 : window.pageXOffset+window.innerWidth-40
dropmenuobj.contentmeasure=dropmenuobj.offsetWidth
if (windowedge-dropmenuobj.x < dropmenuobj.contentmeasure)
edgeoffset=dropmenuobj.contentmeasure+obj.offsetWidth+parseInt(horizontal_offset)
}
else{
var windowedge=ie && !window.opera? iecompattest().scrollTop+iecompattest().clientHeight-15 : window.pageYOffset+window.innerHeight-18
dropmenuobj.contentmeasure=dropmenuobj.offsetHeight
if (windowedge-dropmenuobj.y < dropmenuobj.contentmeasure)
edgeoffset=dropmenuobj.contentmeasure-obj.offsetHeight
}
return edgeoffset
}

function hrmsHint(menucontents, obj, e, tipwidth){
if ((ie||ns6) && document.getElementById("hintbox")){
dropmenuobj=document.getElementById("hintbox")
dropmenuobj.innerHTML='<table border="0"><tr bgcolor="#386CB7"><td align="center" colspan="3"><font color="#ffffff"> <strong><u><fmt:message key="IC.personDtl" bundle="${CapLabels}" /></u></strong> </font></td></tr></th><tr><td align="left"><hdiits:caption captionid="IC.Designation" bundle="${CapLabels}"></hdiits:caption></td><td>:</td><td align="left">'+menucontents[0]+'</td></tr><tr><td align="left"><hdiits:caption captionid="IC.postHeld" bundle="${CapLabels}"></hdiits:caption></td><td>:</td><td align="left">'+menucontents[1]+'</td></tr></table>';
dropmenuobj.style.left=dropmenuobj.style.top=-500

if (tipwidth!=""){
dropmenuobj.widthobj=dropmenuobj.style
dropmenuobj.widthobj.width=tipwidth
}

dropmenuobj.x=getposOffset(obj, "left")
dropmenuobj.y=getposOffset(obj, "top")
dropmenuobj.style.left=dropmenuobj.x-clearbrowseredge(obj, "rightedge")+obj.offsetWidth+"px"
dropmenuobj.style.top=dropmenuobj.y-clearbrowseredge(obj, "bottomedge")+"px"
dropmenuobj.style.visibility="visible"


obj.onmouseout=hidetip
}

}

function hidetip(e){
dropmenuobj.style.visibility="hidden"
dropmenuobj.style.left="-500px"
}

function createhintbox(){
var divblock=document.createElement("div")
divblock.setAttribute("id", "hintbox")
document.body.appendChild(divblock)



}

if (window.addEventListener)
window.addEventListener("load", createhintbox, false)
else if (window.attachEvent)
window.attachEvent("onload", createhintbox)
else if (document.getElementById)
window.onload=createhintbox



/********** Code Ended for Tooltip **********/


--></script>
<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="tableBGColor" value="#F0F4FB"></c:set>


<fieldset style="border-color: green">
<legend style="font-size-adjust: inherit">Create Incharge</legend>

<br>
	<table id="tblPostDetails" border="1" width="90%" cellpadding="1"
		cellspacing="1" style="border-collapse: collapse; background-color:${tableBGColor}" border=1 borderColor="black" align="center">
		<tr bgcolor="#386CB7">
			<td align="center" colspan="7"><font color="#ffffff"> <strong><u>
			<fmt:message key="IC.postDetails" bundle="${CapLabels}" /> </u></strong> </font></td>
			<td align="right" width="5%">
			
			<hdiits:button name="frmAddRecord" type="button" bundle="${CapLabels}" onclick="addsearchPostResultTblRow()" captionid="IC.add" style="width:100%; height:20px" />
			</td>

		</tr>
	</table>


	<table id="searchPostResult" name="searchPostResult" 
		width="90%" style="border-collapse: collapse; background-color:${tableBGColor}" border=0 borderColor="black" align="center">


		<tr class="datatableheader" style="background-color:${tdBGColor}">
			<td bgcolor="${tdBGColor}"></td>
			<td><b><hdiits:caption captionid="IC.postName"
				bundle="${CapLabels}"></hdiits:caption></b></td>
			<td><b><hdiits:caption captionid="IC.TypeofPost"
				bundle="${CapLabels}"></hdiits:caption></b></td>
			<td><b><hdiits:caption captionid="IC.inchrgName"
				bundle="${CapLabels}"></hdiits:caption></b></td>
			<td><b><hdiits:caption captionid="IC.frmDate"
				bundle="${CapLabels}"></hdiits:caption></b></td>
			<td><b><hdiits:caption captionid="IC.toDate"
				bundle="${CapLabels}"></hdiits:caption></b></td>
			<td><b><hdiits:caption captionid="IC.action"
				bundle="${CapLabels}"></hdiits:caption></b></td>
		</tr>
	</table>

	
	

	<fmt:setLocale value="${sessionScope.locale}"/>
	<hdiits:hidden name="encPath" />
	<hdiits:hidden name="encPostDetail" />
	<hdiits:hidden name="assPostLevel" />
	<hdiits:hidden name="setVal" />
	<hdiits:hidden name="radioUserId" />
	<hdiits:hidden name="encRadioUserId" />
	<hdiits:hidden name="resVal" />
	<hdiits:hidden name="radioValues" />
	<hdiits:hidden name="nameApp" />
	<hdiits:hidden name='radiobt'/>
	<hdiits:hidden name="userIDid" id="userIDid" default="0" />
	<hdiits:hidden name="searchStatusFlg" id="searchStatusFlg" default="yes" />
	<hdiits:hidden name="checkArray" id="checkArray"/>
	<hdiits:hidden name="InchargeDtl_ReqId" id="InchargeDtl_ReqId" default="0" />
	
	
	<hdiits:hidden name="USERID" id="USERID" default='<%=request.getParameter("USERID") %>'/>
	<hdiits:hidden name="APPLICATION_MAP_ID" id="APPLICATION_MAP_ID" default='<%= request.getParameter("APPLICATION_MAP_ID")%>'/>
	<hdiits:hidden name="FROM_DATE" id="FROM_DATE" default='<%= request.getParameter("FROM_DATE") %>'/>
	<hdiits:hidden name="TO_DATE" id="TO_DATE" default='<%= request.getParameter("TO_DATE") %>'/>
	<hdiits:hidden name="APPLICATION_NAME" id="APPLICATION_NAME" default='<%= request.getParameter("APPLICATION_NAME") %>'/>
	<hdiits:hidden name="FILE_FLAG" id="FILE_FLAG" default="NO"/>

<br>

</fieldset>
	


<script type="text/javascript">

var User_Details = new Array();

User_Details[0] = document.getElementById('USERID').value;
User_Details[1] = document.getElementById('APPLICATION_MAP_ID').value;
User_Details[2] = document.getElementById('FROM_DATE').value;
User_Details[3] = document.getElementById('TO_DATE').value;
User_Details[4] = document.getElementById('APPLICATION_NAME').value;

var usrId = User_Details[0]+','+User_Details[1]+','+User_Details[2]+','+User_Details[3]+','+User_Details[4];

document.getElementById('radiobt').value = usrId;
document.getElementById('radioValues').value = usrId;
getLeavePersonPostDetail();

</script>

<!-- Code of Tooltip -->
<style type="text/css">

#hintbox{ /*CSS for pop up hint box */
position:absolute;
top: 0;
background-color: white;
width: 250px; /*Default width of hint.*/ 
padding: 3px;
border:1px solid black;
font:normal 11px Verdana;
line-height: inherit;
z-index:100;
border-right: 3px solid black;
border-bottom: 3px solid black;
visibility: hidden;

}



.hintanchor{ /*CSS for link that shows hint onmouseover*/
font-weight: bold;
color: navy;
margin: 3px 8px;
}

</style>


<script type="text/javascript">

/***********************************************
* Show Hint script- By  223001

***********************************************/
		
var horizontal_offset="9px" //horizontal offset of hint box from anchor link

/////No further editting needed

var vertical_offset="0" //horizontal offset of hint box from anchor link. No need to change.
var ie=document.all
var ns6=document.getElementById&&!document.all

function getposOffset(what, offsettype){
var totaloffset=(offsettype=="left")? what.offsetLeft : what.offsetTop;
var parentEl=what.offsetParent;
while (parentEl!=null){
totaloffset=(offsettype=="left")? totaloffset+parentEl.offsetLeft : totaloffset+parentEl.offsetTop;
parentEl=parentEl.offsetParent;
}
return totaloffset;
}

function iecompattest(){
return (document.compatMode && document.compatMode!="BackCompat")? document.documentElement : document.body
}

function clearbrowseredge(obj, whichedge){
var edgeoffset=(whichedge=="rightedge")? parseInt(horizontal_offset)*-1 : parseInt(vertical_offset)*-1
if (whichedge=="rightedge"){
var windowedge=ie && !window.opera? iecompattest().scrollLeft+iecompattest().clientWidth-30 : window.pageXOffset+window.innerWidth-40
dropmenuobj.contentmeasure=dropmenuobj.offsetWidth
if (windowedge-dropmenuobj.x < dropmenuobj.contentmeasure)
edgeoffset=dropmenuobj.contentmeasure+obj.offsetWidth+parseInt(horizontal_offset)
}
else{
var windowedge=ie && !window.opera? iecompattest().scrollTop+iecompattest().clientHeight-15 : window.pageYOffset+window.innerHeight-18
dropmenuobj.contentmeasure=dropmenuobj.offsetHeight
if (windowedge-dropmenuobj.y < dropmenuobj.contentmeasure)
edgeoffset=dropmenuobj.contentmeasure-obj.offsetHeight
}
return edgeoffset
}

function hrmsHint(menucontents, obj, e, tipwidth){
if ((ie||ns6) && document.getElementById("hintbox")){
dropmenuobj=document.getElementById("hintbox")
dropmenuobj.innerHTML='<table border="0"><tr bgcolor="#386CB7"><td align="center" colspan="3"><font color="#ffffff"> <strong><u><fmt:message key="IC.personDtl" bundle="${CapLabels}" /></u></strong> </font></td></tr></th><tr><td align="left"><hdiits:caption captionid="IC.Designation" bundle="${CapLabels}"></hdiits:caption></td><td>:</td><td align="left">'+menucontents[0]+'</td></tr><tr><td align="left"><hdiits:caption captionid="IC.postHeld" bundle="${CapLabels}"></hdiits:caption></td><td>:</td><td align="left">'+menucontents[1]+'</td></tr></table>';
dropmenuobj.style.left=dropmenuobj.style.top=-500

if (tipwidth!=""){
dropmenuobj.widthobj=dropmenuobj.style
dropmenuobj.widthobj.width=tipwidth
}

dropmenuobj.x=getposOffset(obj, "left")
dropmenuobj.y=getposOffset(obj, "top")
dropmenuobj.style.left=dropmenuobj.x-clearbrowseredge(obj, "rightedge")+obj.offsetWidth+"px"
dropmenuobj.style.top=dropmenuobj.y-clearbrowseredge(obj, "bottomedge")+"px"
dropmenuobj.style.visibility="visible"


obj.onmouseout=hidetip
}

}

function hidetip(e){
dropmenuobj.style.visibility="hidden"
dropmenuobj.style.left="-500px"
}

function createhintbox(){
var divblock=document.createElement("div")
divblock.setAttribute("id", "hintbox")
document.body.appendChild(divblock)



}

if (window.addEventListener)
window.addEventListener("load", createhintbox, false)
else if (window.attachEvent)
window.attachEvent("onload", createhintbox)
else if (document.getElementById)
window.onload=createhintbox

</script>


<!-- End of Code For ToolTip -->

</html>



<%
	}

	catch (Exception e) {
		e.printStackTrace();
	}
%>
