
<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<html>
<fmt:setBundle basename="resources.hr.incharge.InchargeAlertMessage" var="alertLables" scope="request" />
<fmt:setBundle basename="resources.hr.incharge.InchargeLabels" var="CapLabels" scope="request" />

<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/addRecord.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/base64.js"/>"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="applicationName" value="${resultValue.applicationName}"></c:set>
<c:set var="searchList" value="${resultValue.searchList}" />
<c:set var="langId" value="${resultValue.langId}" />
<c:set var="dt" value="${resultValue.dt}" />

<script type="text/javascript"><!--

// Global Variable
var textVal=1;
var radCnt =1;
var postRowId;
var radioCheckedId;
var gvindex;
var appName1;
var dropPostIndex;
var tblsearchPostResultRow;
var xmlFromDate;
var xmlToDate;
var delRecordArray=new Array();
var noDelRows=0;
var formId;
var counter = 2;
var delRowIdG = null;
var empArray = new Array();
var cnt =0;
var counter1 =0;
var rowCnt =1;
var pstValCnt =0;
var chkUserId;
var checkArray = '';
var dt= new Date();
var srchUsrId;	 
var emprowid;
var empArrayInch = new Array();

function SearchEmp(){
	
		document.getElementById("searchStatusFlg").value="yes";
		
		var href='./hdiits.htm?actionFlag=getEmpSearchSelData&multiple=false&searchFieldName=empName';
		window.open(href,'chield', 'width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
	
}


function showSearch(){

	document.getElementById("header1").style.display="";
}


function popupSearch(){
				
		appName1 = document.Incharge.appName.value;		
			 
		var ltcType = "";
		
		if(appName1 == "Leave" || appName1 == "Transfer" || appName1 == "Retirement" || appName1 == "Promotion" || appName1 == "Deputation" ){
		 
				 document.getElementById('frmCls').style.display="none";
				 document.getElementById('fromdate').value="${dt}";
			 	 showSearch();
			 		
			 		/*if( document.getElementById('langId').value == 2){			 		
			 		
			 				document.getElementById('header1').rows[0].cells[0].innerHTML =  '<font color="#ffffff"><strong><u style="color: white;">'+ document.Incharge.appName.options[document.Incharge.appName.selectedIndex].text +'</u></strong></font>'+'<font color="#ffffff"> <strong><u><fmt:message bundle="${CapLabels}" key="IC.searchLeave"/></u></strong> </font>';
			  
			 		}
			 		else {
			 		
			 		document.getElementById('header1').rows[0].cells[0].innerHTML = '<font color="#ffffff"> <strong><u><fmt:message bundle="${CapLabels}" key="IC.searchLeave"/></u></strong> </font>' +'<strong><u style="color: white;">'+ document.Incharge.appName.options[document.Incharge.appName.selectedIndex].text +'</u></strong>';
			 		
			 		}*/
			   
		}	
		else if(appName1 == -1){
		
			 	 document.getElementById("addBtn").style.display="none";	
				 document.getElementById('tripdtls').style.display="none";	
				// document.getElementById('fldEmpLeave').style.display="none";	//222407
				 document.getElementById('addNewRecord').style.display="none";
				 //document.getElementById('tblPostDetails').style.display="none";	
				 document.getElementById("postDtls").style.display="none"; // 222407
				 //document.getElementById("searchPostResult").style.display="none";
				 document.getElementById("frmSubmit").style.display="none";
			     document.getElementById('frmCls').style.display='';
		 
				 document.getElementById('fromdate').value='';
				 document.getElementById('todate').value='';
				 document.getElementById("empName").value='';
				 document.getElementById("empDesg").value='';
				 document.getElementById("header1").style.display="none";
                 return;			  
		}	
		else{
		
			  	 document.getElementById("addBtn").style.display="none";	
				 document.getElementById('tripdtls').style.display="none";	
				 //document.getElementById('fldEmpLeave').style.display="none";	//222407
				 document.getElementById('addNewRecord').style.display="none";
				 //document.getElementById('tblPostDetails').style.display="none";	
				 document.getElementById("postDtls").style.display="none"; // 222407
				// document.getElementById("searchPostResult").style.display="none";
				 document.getElementById("frmSubmit").style.display="none";
				 document.getElementById('fromdate').value='';
				 document.getElementById('todate').value='';
				 document.getElementById("empName").value='';
				 document.getElementById("empDesg").value='';
				 document.getElementById("header1").style.display="none";
              	 return;
			 	
		}	
	 
		empSearch1();
		
}



function processResponseforSearch()	{

        	if (xmlHttp.readyState == 4) 
			{  
				if (xmlHttp.status == 200) 
				{ 
					var xmlStr = xmlHttp.responseText;	
					var XMLDoc=getDOMFromXML(xmlStr);	      					  
					var fromDate = XMLDoc.getElementsByTagName('fromDate');
				    var toDate = XMLDoc.getElementsByTagName('toDate');					  
				 }
			}	
}


var flag = 0;
	
function  empSearch1(){

		rowCnt=0;
	
		if(flag == 1){
		
			var tblength=document.getElementsByName('radiobt');
    
     		for(var i=tblength.length+1;i>1;i--){
     		     	 		
     	 		document.getElementById('tripdtls').deleteRow(i);     	 		
     		}     	
     	
     		var tblname = document.getElementById('searchPostResult');
     		var len=tblname.rows.length;  	
     		
     		for(var j=1;j<len;j++){
     		     	 		
     	 		tblname.deleteRow(1);	     	 		
     		}     	
     		flag=0;     	
     	}     
		
		
		
		flag=1;	
		// code for remove table index
		
		textVal=1;
     	counter = 2;
		// end of code for remove table index	

 	 	if(document.getElementById('fromdate').value==''){
 	 	
 	 		document.getElementById('fromdate').focus();
			document.getElementById("addBtn").style.display="none";	
			document.getElementById('tripdtls').style.display="none";
			//document.getElementById('fldEmpLeave').style.display="none";	//222407	
			document.getElementById('addNewRecord').style.display="none";
			//document.getElementById('tblPostDetails').style.display="none";	
			document.getElementById("postDtls").style.display="none"; // 222407
			//document.getElementById("searchPostResult").style.display="none";
			document.getElementById("frmSubmit").style.display="none";
			alert('<fmt:message  bundle="${alertLables}" key="IC.selectFromDt"/>');			
			return;
		}	
		 
		
		/*if( document.getElementById('langId').value == 2) {			 		
			
        	  				
		}
		else{
			
			document.getElementById('tripdtls').rows[0].cells[0].innerHTML = '<font color="#ffffff"> <strong><u><fmt:message key="IC.empOnLeave" bundle="${CapLabels}" /></u></strong> </font>' + '<font color="#ffffff"> <strong><u>'+ document.Incharge.appName.options[document.Incharge.appName.selectedIndex].text +'</u></strong></font>';
			
		}*/
		
     	// Remove searchPostResult  Table Data
     	var srchPostRtl=document.getElementById('searchPostResult');
     	var srchPostRtlLengh=srchPostRtl.rows.length;
     	
     	if( srchPostRtlLengh>1){
     	
     		for(i=1;i<srchPostRtlLengh;i++){
     		
     			srchPostRtl.deleteRow(1);
     		}
     	}
     	// End of searchPostResult  Table Data
     
    	
    	var tblength=document.getElementById('tripdtls');
     	//var oldlen=row.rows.length;
 
     	for(var i=tblength.rows.length;i>2;i--){
     	
     			document.getElementById('tripdtls').deleteRow(2);     	 		
     	}     	
	
     	 
		addOrUpdateRecordForIncharg('addRecord','getEmpLeaveDlt',new Array('fromdate','todate','empName','userIDid','appName'));
		var xmlKey=document.getElementById('encPath');
		var response=xmlKey.value;
		var ajaxKeyArray=response.split(",");

		for(var i=0;i<ajaxKeyArray.length;i++){
		
			xmlHttp=GetXmlHttpObject();
		
			if (xmlHttp==null){
				
				 alert('<fmt:message  bundle="${alertLables}" key="IC.notSupportAJAX"/>');				
			  	return;
			} 
			
			var xmlDom=getDOMFromXML(ajaxKeyArray[i]);
			
			if (xmlDom != null && xmlDom.getElementsByTagName('Flag') != null){
			
				document.getElementById("addBtn").style.display="none";	
				//document.getElementById('fldEmpLeave').style.display="";	//222407
				document.getElementById('tripdtls').style.display="";	
				document.getElementById('addNewRecord').style.display="none";
				//document.getElementById('tblPostDetails').style.display="none";	
				document.getElementById("postDtls").style.display="none"; // 222407
				//document.getElementById("searchPostResult").style.display="none";
				document.getElementById("frmSubmit").style.display="none";
				
			
				var tripdtls=document.getElementById('tripdtls');  
			    tripdtls.style.display="";
			    var row=tripdtls.insertRow();
	            
	           	row.align="center";
	            var cell0=row.insertCell(0);
	            cell0.colSpan="7";
	            cell0.innerHTML="<fmt:message  bundle="${alertLables}" key="IC.noRecordFound"/>";	
	           	return;
							
			}
			else
			{	
				
				var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + ajaxKeyArray[i];
				xmlHttp.onreadystatechange = function(){
				
									eval('populateForm()'); 
				
								}
				xmlHttp.open("POST",encodeURI(url),false);
				xmlHttp.send(null);	
				document.getElementById("addBtn").style.display="";
			}
			

			document.getElementById("postDtls").style.display="none"; // 222407
			//document.getElementById("searchPostResult").style.display="none";
			document.getElementById("frmSubmit").style.display="none";
			document.getElementById("addNewRecord").style.display="none";
			//document.getElementById('tblPostDetails').style.display="none";	
			
		}
	
}


function populateForm(){
			
	if (xmlHttp.readyState == 4){
	 				  
			  	var decXML = xmlHttp.responseText;
			  	var xmlDom=getDOMFromXML(decXML);
			  	//document.getElementById('fldEmpLeave').style.display="";	//222407
				var tripdtls=document.getElementById('tripdtls');
	
				tripdtls.style.display="";
				var row=tripdtls.insertRow();
				var usrId=getXPathValueFromDOM(xmlDom,'userId')+','+getXPathValueFromDOM(xmlDom,'leaveMainId')+','+getXPathValueFromDOM(xmlDom, 'frmDate')+','+getXPathValueFromDOM(xmlDom, 'toDate')+','+appName1;  
	
				row.align='center';
				row.insertCell(0).innerHTML="<input type='radio' id='radiobt' name='radiobt' value='"+usrId+"' />"; 
	
				var temp;
	
				if(getXPathValueFromDOM(xmlDom,'userMName')=='' || getXPathValueFromDOM(xmlDom,'userMName')== null){
				   
				     	 temp=getXPathValueFromDOM(xmlDom,'userFName')+' '+getXPathValueFromDOM(xmlDom,'userLName'); 
						 row.insertCell(1).innerHTML="<input type='text' size='12'  value='"+temp+"' style=' background:#F0F4FB; border:none; text-align: center; '   name='nm"+radCnt+"' id='nm"+radCnt+"'  />";
				}
				else{
				
						 temp= getXPathValueFromDOM(xmlDom,'userFName')+' '+getXPathValueFromDOM(xmlDom,'userMName')+' '+getXPathValueFromDOM(xmlDom,'userLName'); 
			 			 row.insertCell(1).innerHTML="<input type='text' size='12'  value='"+temp+"' style='background:#F0F4FB;border:none; text-align: center'   name='nm"+radCnt+"' id='nm"+radCnt+"'  />";
				
				}
				
				
				row.insertCell(2).innerHTML="<input type='text'  size='12' value='"+getXPathValueFromDOM(xmlDom,'dsgn')+"' style='background:#F0F4FB;  border:none;text-align: center'   name='dsgn"+radCnt+"' id='dsgn"+radCnt+"'  />";
				row.insertCell(3).innerHTML="<input type='text' size='12' value='"+getXPathValueFromDOM(xmlDom,'post')+"' style='background:#F0F4FB; border:none;  text-align: center'   name='pst"+radCnt+"' id='pst"+radCnt+"'  />";
				var tmp_dt=new Date();
				
								
				row.insertCell(4).innerHTML="<input type='text'  value='"+getXPathValueFromDOM(xmlDom, 'frmDate')+"' size='12' style=' background:#F0F4FB; border:none;   text-align: center'   name='fdt"+radCnt+"' id='fdt"+radCnt+"'  />";
				if(getXPathValueFromDOM(xmlDom, 'toDate')!=null){
				row.insertCell(5).innerHTML="<input type='text'  value='"+getXPathValueFromDOM(xmlDom, 'toDate')+"' size='12' style=' background:#F0F4FB; border:none; text-align: center'   name='tdt"+radCnt+"' id='tsgn"+radCnt+"'  />";
				}
				else{
				row.insertCell(5).innerHTML="<input type='text'  value='-' size='12' style=' background:#F0F4FB; border:none; text-align: center'   name='tdt"+radCnt+"' id='tsgn"+radCnt+"'  />";
				
				}
				row.insertCell(6).innerHTML="<input type='text' size='12'  value='"+getXPathValueFromDOM(xmlDom,'deptName')+"' style='background:#F0F4FB;   border:none; text-align: center'   name='dept"+radCnt+"' id='dept"+radCnt+"'  />";
			    row.insertCell(7).innerHTML="<input type='text' size='12'  value='"+getXPathValueFromDOM(xmlDom,'location')+"' style='background:#F0F4FB; border:none; text-align: center'   name='loc"+radCnt+"' id='loc"+radCnt+"'  />";
				radCnt +=1;
		
	 
		if(tripdtls.rows.length==3){
		
			 document.getElementById('radiobt').checked='checked';
			 
		}
		
	}	
		  	
}

function addOrUpdateRecordForIncharg(methodName, actionFlag, fieldArray){
	
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
	
function addRecord(){


	if (xmlHttp.readyState == 4){
	
			var encXML=xmlHttp.responseText;
			var tmp=encXML;
			var len=tmp.length;
			var subXml=tmp.substring(0,len);
			document.getElementById('encPath').value=subXml;
	}
	
}

// Post Detail
function getLeavePersonPostDetail(){
		
		var	chkBk =  document.forms[0].radiobt;
		var cnt=0;
	
	// remove record from searchPostResult
		var tblname = document.getElementById('searchPostResult');
     	var len=tblname.rows.length;  	
     	
     	for(var j=1;j<len;j++){     	 		
     	 	
     	 		tblname.deleteRow(1);	     	 		
     	
     	}   
     	
     	textVal=1;
     	counter = 2;
	// End of remove record from  searchPostResult
	
	// check one radio button selected
	for(var iElement=0; iElement<chkBk.length; iElement++){
	
	    		if(chkBk[iElement].checked == true){
	    		
	    		  cnt=1;
	    		}
	}
	
	if(chkBk.checked == true){
	 
	    		cnt=1;
	}
	
	if(cnt==0){
	
		alert('<fmt:message  bundle="${alertLables}" key="IC.selectOneRecord"/>');
		return;
	}
	
	if (chkBk.length){
	
	    	for(var iElement=0; iElement<chkBk.length; iElement++){
	    	
	    		if(chkBk[iElement].checked == true){
	    		
	    		  radioCheckedId= chkBk[iElement].value;
	    		
	    		}
	    	}
	}
	else{
	
	    	if(chkBk.checked == true){
	    	
	    	  radioCheckedId=chkBk.value;
	    	  
	    	}
	}
	
	document.getElementById("addNewRecord").style.display='';
	//document.getElementById("tblPostDetails").style.display='';
	document.getElementById('radioValues').value=radioCheckedId;

	
	//alert('Radio Button Value >'+radioCheckedId);
	// Remove searchPostResult  Table Data
    var srchPostRtl=document.getElementById('searchPostResult');
    var srchPostRtlLengh=srchPostRtl.rows.length;
    
     	if( srchPostRtlLengh>1){
     	
     		for(i=1;i<srchPostRtlLengh;i++){
     		
     			srchPostRtl.deleteRow(i);
     		
     		}
     	}
     	// End of searchPostResult  Table Data	
	
	addOrUpdateRecordForLeavePersonPost('addPostDetail','getLeavePersonDetail',new Array('radiobt'));
	var xmlKey=document.getElementById('encPostDetail');
	var response=xmlKey.value;
	var ajaxKeyArray=response.split(","); 
	
	for(var i=0;i<ajaxKeyArray.length;i++){
	
			xmlHttp=GetXmlHttpObject();
			
			if (xmlHttp==null){
			
			  alert('<fmt:message  bundle="${alertLables}" key="IC.notSupportAJAX"/>');
			  return;
			} 
			
		var url="hdiits.htm?actionFlag=getXmlContent&xmlFileName="+ajaxKeyArray[i];
			xmlHttp.onreadystatechange = populatePostFormIncharge;
			xmlHttp.open("POST",encodeURI(url),false);
			xmlHttp.send(null);							
	}
		
		
		// add into drop down
		 var objChk = document.forms[0].chkBox;
		 var resVal=null;
		 var cnt=0;
			
	    if (objChk.length){
	    
	    	for(var iElement=0; iElement<objChk.length; iElement++){
	    	
	    		getPost( radioCheckedId,iElement+1);			    		    		
	    		
	    	}
	    }
	    else{
	    
	    		getPost(radioCheckedId,1);	
	    		
	    }	   
		// end in Drop down		
		document.getElementById("postDtls").style.display=""; // 222407
		
		document.getElementById("frmSubmit").style.display="";
		
}


function addPostDetail(){
	
	if (xmlHttp.readyState == 4){
	
			
			var encXML=xmlHttp.responseText;
			var tmp=encXML;
			var len=tmp.length;
			var subXml=tmp.substring(1,len);
			document.getElementById('encPostDetail').value=subXml;
	}
	
}	

function addOrUpdateRecordForLeavePersonPost(methodName, actionFlag, fieldArray){
		
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

function populatePostFormIncharge(){
	
	if (xmlHttp.readyState == 4){
	 		
	 			
		 		var tval="textVal"+textVal;
		 		var cval="checkVal"+textVal;
			  	var decXML = xmlHttp.responseText;
			  	var xmlDom=getDOMFromXML(decXML);
			  	
			  	var tripdtls=document.getElementById('searchPostResult');  
				document.getElementById("postDtls").style.display=""; // 222407
				tripdtls.style.display="";
				var row=tripdtls.insertRow();
				row.align='center';
				row.id='row'+counter;
				
				var usrPostId=getXPathValueFromDOM(xmlDom,'postId'); 
				
				row.insertCell(0).innerHTML="<input type='checkbox' id='checkbox"+textVal+"' name='chkBox' value='"+textVal+"' />"; 
				
				row.insertCell(1).innerHTML="<align='center'><select name='dropDown"+textVal+"'  style='text-align: center;' onchange='getTypeofPostDetail(this,\""+row.id+"\")' ><option SELECTED value='-1'><fmt:message key='IC.select' bundle='${CapLabels}'></fmt:message></option></select>"+"<label class='mandatoryindicator'>*</label>"+"<input type='hidden' name='ReqId"+textVal+"' id='ReqId"+textVal+"' value='0' />";
				
				row.insertCell(2).innerHTML="";
				
				row.insertCell(3).innerHTML="<input type='text' id='"+tval+"' name='"+tval+"'  readonly='true' style='color: black; text-align:center; font-family:Verdana; font-weight:bold; font-size:12px; background-color:lightblue;' onmouseover='getEmpDtls(this,\""+textVal+"\")' class='hintanchor'>"+"<label class='mandatoryindicator' >*</label>"+"<img src='images/search_icon.gif' onclick='openSearchWindow(\""+tval+"\", \""+textVal+"\")'>";  
				
				xmlFromDate=getXPathValueFromDOM(xmlDom,'frmDate');
				xmlToDate=getXPathValueFromDOM(xmlDom,'toDate');
				
				row.insertCell(4).innerHTML="<input type='text' name='frm"+textVal+"' onkeypress='return checkDateNumber()' onfocus='validateInchargeDate()'  class='texttag'  value='"+getXPathValueFromDOM(xmlDom,'frmDate')+"'  maxlength=10 size=10 readonly='readonly' />"+"<img style='cursor:hand' id='img_frm"+textVal+"' src=themes/defaulttheme/images/taglib/ico-calendar.gif width=20 onClick=window_open('frm"+textVal+"',375,570,'','frm"+textVal+",Please~enter~valid~$CPTN,Incharge~Date,0,31/12/2099,Please~enter~$CPTN~less~than~31/12/2099') >";
				
				if(xmlToDate != null){
					row.insertCell(5).innerHTML="<input type='text' name='to"+textVal+"'  onkeypress='return checkDateNumber()' onfocus='validateInchargeDate()'  class='texttag'  value='"+getXPathValueFromDOM(xmlDom,'toDate')+"'  maxlength=10 size=10 readonly='readonly' />"+"<img style='cursor:hand' id='img_to"+textVal+"' src=themes/defaulttheme/images/taglib/ico-calendar.gif width=20 onClick=window_open('to"+textVal+"',375,570,'','to"+textVal+",Please~enter~valid~$CPTN,Incharge~Date,0,31/12/2099,Please~enter~$CPTN~less~than~31/12/2099') >";	
				}
				else{
					row.insertCell(5).innerHTML="<input type='text' name='to"+textVal+"'  onkeypress='return checkDateNumber()' onfocus='validateInchargeDate()'  class='texttag'  maxlength=10 size=10 readonly='readonly' />"+"<img style='cursor:hand' id='img_to"+textVal+"' src=themes/defaulttheme/images/taglib/ico-calendar.gif width=20 onClick=window_open('to"+textVal+"',375,570,'','to"+textVal+",Please~enter~valid~$CPTN,Incharge~Date,0,31/12/2099,Please~enter~$CPTN~less~than~31/12/2099') >";	
				}
				
				
				var todateVar='to'+textVal;
			    row.insertCell(6).innerHTML="<A HREF='javascript:{deletesearchPostResultTblRow(\""+row.id+"\")}'>Delete</A>";
			    row.insertCell(7).innerHTML="<input type ='text' style='display:none' name ='usrId"+textVal+"' id ='usrId"+textVal+"'/>"+"<input type ='text' style='display:none' name ='postName"+textVal+"' id ='postName"+textVal+"'/>"+"<input type ='text' style='display:none' name ='dsgnName"+textVal+"' id ='dsgnName"+textVal+"'/>";  
				radlVal=radioCheckedId;
				rowCnt =rowCnt+1;
				counter = counter + 1;
				
				if(textVal==1){
				document.getElementById('checkbox1').checked='checked';
				}
				textVal=textVal+1;					
		 }	
		 
}	

function getEmpDtls(id,rid){

//var inchargeUserId = document.getElementById('usrId'+rid).value;
//var inchargePersonName = document.getElementById('textVal'+rid).value;



	/*if(inchargeUserId!=''){
			emprowid=id;
			var url='hdiits.htm'+'?actionFlag=getInchargePersonDtls&inchargeUserId='+inchargeUserId+'&inchargePersonName='+inchargePersonName;

			xmlHttp.open("POST", encodeURI(url) , true);			
			xmlHttp.onreadystatechange = getInchargeDtlFrmXML;
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));


	//		window.open(href,'chield', 'width=420,height=150,toolbar=no,minimize=no,status=no,memubar=no,titlebar=0,location=no,scrollbars=no,top=100,left=100');

	}*/


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
}*/



function openSearchWindow(textBoxId, index){

	postRowId=textBoxId;
	gvindex=index;
	srchUsrId = "usrId"+index;
	document.getElementById("searchStatusFlg").value="no";

	var href = "hrms.htm?actionFlag=getEmpSearchSelData&multiple=false&searchFieldName="+textBoxId;
	window.open(href,'chield', 'width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
	
}



function empSearch(from, fieldName){

	for(var i=0; i<from.length; i++){
		empArrayInch[i] = from[i].split("~"); 
	}
	
	
	var empRlt = empArrayInch[0];
	var userID = empRlt[2];
	
		if(document.getElementById("searchStatusFlg").value=="yes")
		{
			document.getElementById('userIDid').value =trimAll(userID);
			document.getElementById('empName').value = empRlt[1];
			document.getElementById('empDesg').value = empRlt[7];
		}
		else
		{
			document.getElementById(postRowId).value="";

			var objChk = document.forms[0].chkBox;
			var tbl=document.getElementById('searchPostResult');
		
			 var rblvals='checkbox'+gvindex;			
	    	//var checkList=document.getElementById(rblvals).value.split(",");
	    	 var checkList = radioCheckedId.split(",");
	    	if(checkList[0] == trimAll(userID)) {
	    		alert('<fmt:message  bundle="${alertLables}" key="IC.notEligibleInchargePerson"/>');
	    		}
	    		else {
	    		if(checkList.length>4) {
	    			 document.getElementById(postRowId).value=empRlt[1];
	    	 		 document.getElementById(srchUsrId).value=trimAll(userID);

					document.getElementById('postName'+gvindex).value = empRlt[7];	
					document.getElementById('dsgnName'+gvindex).value = empRlt[5];	

		    	 		//document.getElementById(rblvals).value=checkList[0]+','+checkList[1]+','+checkList[2]+','+checkList[3]+','+checkList[4]+','+trimAll(userID)+','+trimAll(empRlt[4]);
	    	 		
	    	 		}
	    		}    	 	
		}
	
	 
}	

var row = null;
function addsearchPostResultTblRow()
{
 
	var tval="textVal"+textVal;
	var cval="checkVal"+textVal;
	row=document.getElementById('searchPostResult').insertRow();  
				
			//var row =addtblResult.insertRow();
				
				row.id='row'+counter;
				row.align='center';
				row.insertCell(0).innerHTML="<input type='checkbox'   style=' border:none; text-align: center'id='checkbox"+textVal+"' name='chkBox' value='"+textVal+"' onclick = 'checkClick(this)' />"; 
				row.insertCell(1).innerHTML="<select name='dropDown"+textVal+"' style=' text-align: center' onchange='getTypeofPostDetail(this,\""+row.id+"\")' ><option SELECTED value='-1'><fmt:message key='IC.select' bundle='${CapLabels}'></fmt:message></option> </select>"+"<label class='mandatoryindicator'>*</label>"+"<input type='hidden' name='ReqId"+textVal+"' id='ReqId"+textVal+"' value='0' />";
				row.insertCell(2).innerHTML="";//getXPathValueFromDOM(xmlDom,'postLookupDesc');
				row.insertCell(3).innerHTML="<input type='text' id='"+tval+"' name='"+tval+"' readonly='true' style='  text-align: center; color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;' onmouseover='getEmpDtls(this,\""+textVal+"\")' class='hintanchor'>"+"<label class='mandatoryindicator'>*</label>"+"<img src='images/search_icon.gif' onclick='openSearchWindow(\""+tval+"\", \""+textVal+"\")'>";
				
				//row.insertCell(4).innerHTML="<input type='text'  name='frm"+textVal+"'   style=' text-align: center'  onfocus='return checkDateNumber()'  class='texttag'   value='"+xmlFromDate+"'  maxlength=10 size=10  />"
				//+"<img style='cursor:hand'  src=themes/defaulttheme/images/taglib/ico-calendar.gif width=20 onClick=window_open('frm"+textVal+"',375,570,'','frm"+textVal+"','Please~enter~valid~$CPTN,31/12/2099') > "
				//+"&nbsp;&nbsp;<label class='mandatoryindicator'>*</label>";
				row.insertCell(4).innerHTML="<input type='text' name='frm"+textVal+"'  onkeypress='return checkDateNumber()' onfocus='validateInchargeDate()'  class='texttag'   value='"+xmlFromDate+"'  maxlength=10 size=10 readonly='true' />"+"<img style='cursor:hand' id='img_frm"+textVal+"' src=themes/defaulttheme/images/taglib/ico-calendar.gif width=20 onClick=window_open('frm"+textVal+"',375,570,'','frm"+textVal+",Please~enter~valid~$CPTN,Incharge~Date,0,31/12/2099,Please~enter~$CPTN~less~than~31/12/2099') >";
				
				if(xmlToDate !=null){	
				row.insertCell(5).innerHTML="<input type='text' name='to"+textVal+"'  onkeypress='return checkDateNumber()' onfocus='validateInchargeDate()' class='texttag'   value='"+xmlToDate+"'  maxlength=10 size=10 readonly='true' />"+"<img style='cursor:hand' id='img_to"+textVal+"' src=themes/defaulttheme/images/taglib/ico-calendar.gif width=20 onClick=window_open('to"+textVal+"',375,570,'','to"+textVal+",Please~enter~valid~$CPTN,Incharge~Date,0,31/12/2099,Please~enter~$CPTN~less~than~31/12/2099') >";	
				}
				else{
				row.insertCell(5).innerHTML="<input type='text' name='to"+textVal+"'  onkeypress='return checkDateNumber()' onfocus='validateInchargeDate()' class='texttag' maxlength=10 size=10 readonly='true' />"+"<img style='cursor:hand' id='img_to"+textVal+"' src=themes/defaulttheme/images/taglib/ico-calendar.gif width=20 onClick=window_open('to"+textVal+"',375,570,'','to"+textVal+",Please~enter~valid~$CPTN,Incharge~Date,0,31/12/2099,Please~enter~$CPTN~less~than~31/12/2099') >";	
				}								
				//row.insertCell(5).innerHTML="<input type='text'  name='to"+textVal+"'  style=' text-align: center'  onfocus='return checkDateNumber()'  class='texttag'   value='"+xmlToDate+"' maxlength=10 size=10  />"
				//+"<img style='cursor:hand'  src=themes/defaulttheme/images/taglib/ico-calendar.gif width=20 onClick=window_open('to"+textVal+"',375,570,'','to"+textVal+"','Please~enter~valid~$CPTN,31/12/2099') > "
				//+"&nbsp;&nbsp;<label class='mandatoryindicator'>*</label>";
			
				var todateVar='to'+textVal;
				row.insertCell(6).innerHTML="<A HREF='javascript:{deletesearchPostResultTblRow(\""+row.id+"\")}'>Delete</A>";
				row.insertCell(7).innerHTML="<input type ='text' style='display:none' name ='usrId"+textVal+"' id ='usrId"+textVal+"'/>"+"<input type ='text' style='display:none' name ='postName"+textVal+"' id ='postName"+textVal+"'/>"+"<input type ='text' style='display:none' name ='dsgnName"+textVal+"' id ='dsgnName"+textVal+"'/>";  
				
			var objChk = document.forms[0].chkBox;
			//getPost(objChk[textVal-1].value,textVal);	
			getPost(radlVal,textVal);
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
	if(document.getElementById('searchPostResult').rows.length==1){
 
		document.getElementById('frmSubmit').style.display="none";
		document.getElementById("postDtls").style.display="none"; // 222407
		//document.getElementById('searchPostResult').style.display="none";
		document.getElementById('addNewRecord').style.display="none";
		//document.getElementById('tblPostDetails').style.display="none";

}
}

var cnt=-1;

function checkClick(form){

	if(form.checked==true){
		cnt = cnt + 1;		
	}
	
	else{	
		cnt = cnt - 1;
	}	
}



function InchargeSubmitForm()
{
    var checkArray = '';
    var objChk = document.forms[0].chkBox;
	var a = document.getElementsByName('chkBox');
	for(i=a.length-1 ;  i>=0 ; i--) {
	 	  if(a[i].checked == true){

	 	  var textBx = "textVal" + a[i].value;
	 	  var PostVal ="dropDown" + a[i].value;
	 	  if(document.getElementById(textBx).value == ''){

   	    	  document.getElementById(textBx).focus();
	    	   alert('<fmt:message  bundle="${alertLables}" key="IC.selectInchargePerson"/>');	  
	    	   return 
	    	}
	 	  if(document.getElementById(PostVal).value == -1){
	
		    	  document.getElementById(PostVal).focus();
	    		  alert('<fmt:message  bundle="${alertLables}" key="IC.selectPostName"/>');	  
	    	   	  return 
	      }
	 	  
	 	  cnt=1;
	 	  }
	}
	if(cnt==-1)
	{
			alert('<fmt:message  bundle="${alertLables}" key="IC.selectOneRecord"/>');
			return;
	}
	else
		{
		
		 var a = document.getElementsByName('chkBox');
	 	 var checkList = radioCheckedId.split(",");
	 	 var frmDt= xmlFromDate;
	 	 var toDt = xmlToDate;
	 	 for(i=a.length-1 ;  i>=0 ; i--) {
	 	 if(a[i].checked == true){
	    	
	    	var textBx 		= "textVal" + a[i].value;
	    	var inchFrmDt 	= "frm"+a[i].value;
	    	var inchToDt 	= "to"+a[i].value;
	    	
	    	if(appName1 == 'Leave' || appName1 == 'Deputation'){
	    	
	    		if(getDateDiffInString(frmDt,document.getElementById(inchFrmDt).value)){
	    		 
	    			 if(getDateDiffInString(document.getElementById(inchFrmDt).value,document.getElementById(inchToDt).value))
	    		  	 { 	
	    		  			if(getDateDiffInString(document.getElementById(inchToDt).value,toDt)){
	    		      	
	    		      				 checkArray=checkArray+','+a[i].value;
	    		       		}
	    		    		else{
	    		    				alert('<fmt:message  bundle="${alertLables}" key="IC.inchrgeFrom"/>' +frmDt+ '<fmt:message  bundle="${alertLables}" key="IC.dateTo"/>' +toDt+ '<fmt:message  bundle="${alertLables}" key="Ic.dt"/>');  
	    							return;
	    		    		}
	    		 	} 
	    		 	else{
	    		  					alert('<fmt:message  bundle="${alertLables}" key="IC.inchrgeFrom"/>' +frmDt+ '<fmt:message  bundle="${alertLables}" key="IC.dateTo"/>' +toDt+ '<fmt:message  bundle="${alertLables}" key="Ic.dt"/>');  
	    							return;
	    		 	}
	    		} 
	    		else{
	    			    alert('<fmt:message  bundle="${alertLables}" key="IC.inchrgeFrom"/>' +frmDt+ '<fmt:message  bundle="${alertLables}" key="IC.dateTo"/>' +toDt+ '<fmt:message  bundle="${alertLables}" key="Ic.dt"/>');  
	    				return;
	    		}
	    
			}
			else{
				
				
				if(getDateDiffInString(frmDt, document.getElementById(inchFrmDt).value)){
						
						 checkArray=checkArray+','+a[i].value;
				}
				else{
				
					 alert('<fmt:message  bundle="${alertLables}" key="IC.inchargeDate"/> '+frmDt);
					 return;
					 
				}
			
			}	    
				    
	    }	    
 
	}
	
	    document.getElementById('frmSubmit').disabled=true;
	    document.getElementById('frmClose1').disabled=true;
	    document.Incharge.action='hdiits.htm?actionFlag=setLeaveIncharge&checkArray='+checkArray;
     	document.Incharge.submit(); 
 		 
 	}		  
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
			xmlHttp.open("POST", encodeURI(url) , true);			
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
				    	//var ddlId=document.getElementById(formId);
				    	
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

     //alert(strDate1+"===="+strDate2);
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
	document.Incharge.action="hrms.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
 	document.Incharge.submit();
}

--></script>
<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="tableBGColor" value="#F0F4FB"></c:set>

<hdiits:form name="Incharge" validate="true" method="POST" action=""
	encType="text/form-data">

	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"> <hdiits:caption
			captionid="IC.Incharge" bundle="${CapLabels}" captionLang="single"/></a></li>
	</ul>
	</div>
	<div class="tabcontentstyle" style="height: 100%">
	<div id="tcontent1" class="tabcontent" tabno="0"><br>


	<!--<table width="100%" cellpadding="1" cellspacing="1">
		<tr bgcolor="#386CB7">
			<td class="fieldLabel" align="center"><font color="#ffffff">
			<strong><u> <fmt:message key="IC.Inchrfor"
				bundle="${CapLabels}" /></u></strong> </font>
		<tr height="20"></tr>
	</table>
	-->
<hdiits:fieldGroup id="appliName" titleCaptionId="IC.Inchrfor" bundle="${CapLabels}" collapseOnLoad="false">
	<table align="center" cellpadding="1" cellspacing="1"
		style="border-collapse: collapse;">
		<tr>
			<td><b><hdiits:caption captionid="IC.Icfor"
				bundle="${CapLabels}"></hdiits:caption></b></td>
			<td><hdiits:select sort="false"  name="appName" size="1"
				caption="drop_down" mandatory="true" onchange="popupSearch();  ">

				<hdiits:option value="-1"><fmt:message key="IC.select" bundle="${CapLabels}"></fmt:message>
				</hdiits:option>
				<!--<hdiits:option value="Leave"><fmt:message key="IC.Leave" bundle="${CapLabels}"></fmt:message>
				</hdiits:option>-->
				<c:forEach var="appName1" items="${applicationName}">
					<hdiits:option value="${appName1.lookupName}">${appName1.lookupDesc}</hdiits:option>
				</c:forEach>
			</hdiits:select> <hdiits:hidden name="langId" id="langId" default="${langId}" /></td>
			<td><hdiits:button name="frmCls" id="frmCls" type="button"
				captionid="IC.close" bundle="${CapLabels}" onclick="closePage()" />
			</td>
		</tr>
	</table>

	<br>

</hdiits:fieldGroup>
<hdiits:fieldGroup id="header1" titleCaptionId="IC.searchLeave" bundle="${CapLabels}" collapseOnLoad="false">	
	<!--<table width="100%" id="header1" class="tabtable" align="center">
		<tr bgcolor="#386CB7">
			<td align="center" colspan="4" id="searchOn"><font
				color="#ffffff"> <strong><u> <fmt:message
				key="IC.searchLeave" bundle="${CapLabels}"></fmt:message></u></strong> </font>
			</td>

		</tr>
		--><table width="100%" >
		<tr>
			<td colspan="4">
			</td>
		</tr>
		
		
		<tr>
			<td><b><hdiits:caption captionid="IC.frmDate" bundle="${CapLabels}"></hdiits:caption></b></td>

			<td>
			<hdiits:dateTime mandatory="true" name="fromdate"
				captionid="IC.frmDate" bundle="${CapLabels}" maxvalue="31/12/2999"></hdiits:dateTime>
			</td>

			<td><b><hdiits:caption captionid="IC.toDate"
				bundle="${CapLabels}"></hdiits:caption></b></td>
			<td><hdiits:dateTime  validation="txt.isrequired" name="todate"
				captionid="IC.toDate" bundle="${CapLabels}"   maxvalue="31/12/2999"></hdiits:dateTime></td>
		</tr>
	<tr>
			<td><b><hdiits:caption captionid="IC.empName"
				bundle="${CapLabels}"></hdiits:caption></b></td>
			<td><hdiits:text name="empName" id="empName" readonly="true"
				style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;" />
			<img src="images/search_icon.gif" onclick="SearchEmp();"></td>
			<td><b><hdiits:caption captionid="IC.Designation"
				bundle="${CapLabels}"></hdiits:caption></b></td>
			<td><hdiits:text name="empDesg" id="empDesg" readonly="true"
				style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;" />
			<hdiits:hidden name="userIDid" id="userIDid" default="0" /> <hdiits:hidden
				name="searchStatusFlg" id="searchStatusFlg" default="yes" /></td>
		</tr>
		
		<tr>
			<td colspan="4" align="center"><b> <br>
			<hdiits:button name="empSearch" type="button" captionid="IC.search"
				bundle="${CapLabels}" onclick="empSearch1();" /></b> <b><hdiits:button
				name="frmClose" type="button" captionid="IC.close"
				bundle="${CapLabels}" onclick="closePage();" /></b></td>
		</tr>
	</table>
	
	
	

	<table width="100%" align="center" id="tripdtls"  cellpadding="0" cellspacing="0" BGCOLOR="WHITE" width="100%" style="border-collapse: collapse; background-color:${tableBGColor}" border=1 borderColor="black">

		<tr bgcolor="#386CB7" style="display:none">
			<td align="center" colspan="8"><font color="#ffffff"> <strong><u>
			<fmt:message key="IC.empOnLeave" bundle="${CapLabels}" /></u></strong> </font></td>

		</tr>

		<tr class="datatableheader" style="background-color:${tdBGColor}">
			<td bgcolor="${tdBGColor}">&nbsp;</td>
			<td><b><hdiits:caption captionid="IC.userName"
				bundle="${CapLabels}"></hdiits:caption></b></td>
			<td><b><hdiits:caption captionid="IC.Designation"
				bundle="${CapLabels}"></hdiits:caption></b></td>
			<td><b><hdiits:caption captionid="IC.post"
				bundle="${CapLabels}"></hdiits:caption></b></td>
			<td><b><hdiits:caption captionid="IC.frmDate"
				bundle="${CapLabels}"></hdiits:caption></b></td>
			<td><b><hdiits:caption captionid="IC.toDate"
				bundle="${CapLabels}"></hdiits:caption></b></td>
			<td><b><hdiits:caption captionid="IC.DeptName"
				bundle="${CapLabels}"></hdiits:caption></b></td>
			<td><b><hdiits:caption captionid="IC.location"
				bundle="${CapLabels}"></hdiits:caption></b></td>

		</tr>
		
	</table>
</hdiits:fieldGroup>

	<table width="100%" id="addBtn" cellpadding="1" cellspacing="1">
		<tr>
			<td align="center"><b> <hdiits:button name="frmAdd"
				type="button" captionid="IC.add" bundle="${CapLabels}"
				onclick="getLeavePersonPostDetail()" /> </b></td>
		    </tr>

	</table>
	<br>
	<br>
	<table id="searchResult" border="1" cellpadding="1" cellspacing="1">


		<c:forEach var="sList" items="${searchList}">
			<tr>
				<td><hdiits:radio name="rdBt" value="sList.userId" /></td>
				<td><c:out value="${sList.userFName}" /><c:out value=" " /> <c:out
					value="${sList.userMName}" /><c:out value=" " /> <c:out
					value="${sList.userLName}" /></td>
				<td><c:out value="${sList.post}" /></td>
				<td><c:out value="${sList.dsgn}" /></td>
				<td><c:out value="${sList.deptName}" /></td>
				<td><c:out value="${sList.frmDate}" /></td>
				<td><c:out value="${sList.toDate}" /></td>

			</tr>
		</c:forEach>
	</table>

	<!--<table id="tblPostDetails" border="1" width="100%" cellpadding="1"
		cellspacing="1" style="border-collapse: collapse; background-color:${tableBGColor}" border=1 borderColor="black">
		<tr bgcolor="#386CB7">
			<td align="center" colspan="7"><font color="#ffffff"> <strong><u>
			<fmt:message key="IC.postDetails" bundle="${CapLabels}" /> </u></strong> </font></td>

		</tr>
	</table>

--><hdiits:fieldGroup id="postDtls" titleCaptionId="IC.postDetails" bundle="${CapLabels}" collapseOnLoad="false">	
	<table id="searchPostResult" name="searchPostResult" width="100%" style="border-collapse: collapse; background-color:${tableBGColor}" border=1 borderColor="black">

		<tr class="datatableheader" style="background-color:${tdBGColor}">
			<td bgcolor="${tdBGColor}">&nbsp;</td>
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

	<table id="addNewRecord" cellpadding="1" cellspacing="1"
		style="border-collapse: collapse;" align="right">
		<tr>
			<td align="center"><b> <hdiits:button name="frmAddRecord"
				type="button" captionid="IC.add" bundle="${CapLabels}"
				onclick="addsearchPostResultTblRow()" /> </b></td>
		</tr>
	</table>
</hdiits:fieldGroup>
	<br>
	<table id="frmSubmit" width="100%" align="center" cellpadding="1"
		cellspacing="1">
		<tr>
			<td align="center"><b> <hdiits:button name="frmSubmit"
				id="frmSubmit" type="button" captionid="IC.submit"
				bundle="${CapLabels}" onclick="InchargeSubmitForm()" /> </b> <b><hdiits:button
				name="frmClose1" id =" frmClose1" type="button" captionid="IC.close"
				bundle="${CapLabels}" onclick="closePage();" /></b></td>
		</tr>
	</table>


	<script type="text/javascript">
		document.getElementById("header1").style.display="none";
		document.getElementById("addBtn").style.display="none";
		document.getElementById("postDtls").style.display="none"; // 222407
		document.getElementById("searchPostResult").style.display="none";
		document.getElementById("frmSubmit").style.display="none";
		document.getElementById("addNewRecord").style.display="none";
		//document.getElementById("tblPostDetails").style.display="none";

	</script>

	</div>
	</div>
	<script type="text/javascript">		
		initializetabcontent("maintab")
	</script>

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
	<hdiits:hidden name="FILE_FLAG" id="FILE_FLAG" default="YES"/>
	<hdiits:hidden name="InchargeDtl_ReqId" id="InchargeDtl_ReqId" default="0" />


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


</hdiits:form>



</html>



<%
	}

	catch (Exception e) {
		e.printStackTrace();
	}
%>
