

<%
try {
%>

<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script type="text/javascript" src="<c:url value="/script/common/base64.js"/>">
</script>
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
       
<script type="text/javascript" src="/script/common/ajax_saveData.js"> </script>


<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>

<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>

<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>

<fmt:setBundle basename="resources.hr.transfer.transferLabels" var="transferLables" scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<c:set var="transType" value="${resValue.transferType}"></c:set>
<c:set var="fileid" value="${resValue.fileId}"/>

<c:set var="xmlFileName" value="${resValue.xmlFileName}" />

<c:set var="TransferVO" value="${resValue.TransferVO}" />
<c:set var="level1" value="${resValue.level1}"> </c:set>
<c:set var="idforlevel1" value="${resValue.idforlevel1}"> </c:set>
<c:set var="fileblktrs" value="${resValue.fileblktrs}"> </c:set>
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><hdiits:caption captionid="trn.tranRequest" bundle="${transferLables}" captionLang="single"></hdiits:caption>
		 </a></li>
	</ul>
	</div>
	<div class="tabcontentstyle" style="height: 100%">
	<div id="tcontent1" class="tabcontent" tabno="0">
	

	

<hdiits:form name="frmTransfer" validate="true" method="post" action="./hrms.htm?actionFlag=sendTransData" encType="text/form-data">
<script type="text/javascript">

var deletedArray=new Array();

var deletedArrayindex=new Array();
function Pager(tableName, itemsPerPage) {
    this.tableName = tableName;
    this.itemsPerPage = itemsPerPage;
    this.currentPage = 1;
    this.pages = 0;
    this.inited = false;
    
    this.showRecords = function(from, to) {        
        var rows = document.getElementById(tableName).rows;
    	 for (var i = 1; i < rows.length; i++) {
            if (i < from || i > to)  
                rows[i].style.display = 'none';
            else
                rows[i].style.display = '';
        }
      for(var b=0;b<deletedArray.length;b++){
       
		var deletedf=document.getElementById(deletedArray[b]);
		
		if(deletedf!=null)
		{
		deletedf.style.display='none';
		}
		}
    }
    this.showPage = function(pageNumber) {
    	if (! this.inited) {
    		alert("not inited");
    		return;
    	}

        var oldPageAnchor = document.getElementById('pg'+this.currentPage);
       
       if(oldPageAnchor!=null)
       {
        oldPageAnchor.className = 'pg-normal';
        
        this.currentPage = pageNumber;
        var newPageAnchor = document.getElementById('pg'+this.currentPage);
        newPageAnchor.className = 'pg-selected';
        
        var from = (pageNumber - 1) * itemsPerPage + 1;
        var to = from + itemsPerPage - 1;
      
        this.showRecords(from, to);
        }
        else if(oldPageAnchor==null)
        {
        var oldPageAnchor = document.getElementById('pg'+1);
      
     
       if(oldPageAnchor!=null)
       {
        oldPageAnchor.className = 'pg-normal';
        
        this.currentPage = pageNumber;
        var newPageAnchor = document.getElementById('pg'+this.currentPage);
        newPageAnchor.className = 'pg-selected';
        
        var from = (pageNumber - 1) * itemsPerPage + 1;
        var to = from + itemsPerPage - 1;
  
        this.showRecords(from, to);
        }
        else
        {
        for(var b=0;b<deletedArray.length;b++){
       
		var deletedf=document.getElementById(deletedArray[b]);
		
		if(deletedf!=null)
		{
		
		deletedf.style.display='none';
		}
		
		}
        }
        }
        else
        
        {
        for(var b=0;b<deletedArray.length;b++){
       
		var deletedf=document.getElementById(deletedArray[b]);
		
		if(deletedf!=null)
		{
		
		deletedf.style.display='none';
		}
		
		}
        }
    }   
    
    this.prev = function() {
        if (this.currentPage > 1)
            this.showPage(this.currentPage - 1);
    }
    
    this.next = function() {
        if (this.currentPage < this.pages) {
            this.showPage(this.currentPage + 1);
        }
    }                        
    
   /* this.init = function() {
        var rows = document.getElementById(tableName).rows;
        var records = (rows.length - 1); 
        this.pages = Math.ceil(records / itemsPerPage);
        this.inited = true;
    }*/

  this.init = function() {
        var rows = document.getElementById(tableName).rows;
        var records = (rows.length - 1-deletedArray.length); 
        this.pages = Math.ceil(records / itemsPerPage);
        this.inited = true;
    }

    this.showPageNav = function(pagerName, positionId) {
    	if (! this.inited) {
    		alert("not inited");
    		return;
    	}
    	var element = document.getElementById(positionId);
    	
    	var pagerHtml = '<span onclick="' + pagerName + '.prev();" class="pg-normal"> &#171 Prev </span> | ';
        for (var page = 1; page <= this.pages; page++) 
            pagerHtml += '<span id="pg' + page + '" class="pg-normal" onclick="' + pagerName + '.showPage(' + page + ');">' + page + '</span> | ';
        pagerHtml += '<span onclick="'+pagerName+'.next();" class="pg-normal"> Next &#187;</span>';            
        
        element.innerHTML = pagerHtml;
    }
}

function addDBDataInTableAftTransfer(tableId,hiddenField,dispFieldA,
						xmlFilePath,editMethodName,deleteMethodName,viewMethodName)
	{
		//	alert ('addDBDataInTable called....');
		if(deleteMethodName == undefined) 
		{
			deleteMethodName = '';
		}
		if(editMethodName 	== undefined) 
		{
			editMethodName 	 = '';
		}
		if(viewMethodName 	== undefined) 
		{
			viewMethodName 	 = '';
		}
		document.getElementById(tableId).style.display='';
		document.getElementById('mutipleDelete').style.display='';
		
		
		var trow=document.getElementById(tableId).insertRow();
		trow.id = 'row' + hiddenField + counter;
		
	
		trow.insertCell(0).innerHTML = "<INPUT type='hidden' name='" + hiddenField + "' id='" + hiddenField + counter + "' value='" +xmlFilePath  + "_N' />";
		trow.cells[0].style.display = 'none';
		var len = dispFieldA.length;
		var i=0;
		var chbxid='row^'+dispFieldA[i];
		
		//trow.insertCell(i+1).innerHTML = dispFieldA[i];
		trow.insertCell(i+1).innerHTML='<hdiits:checkbox id="'+chbxid+'" name="rowuserid" value="'+dispFieldA[i]+'"  />';
			
		trow.insertCell(i+2).innerHTML = dispFieldA[i+1];	
		trow.insertCell(i+3).innerHTML = dispFieldA[i+2];	
		trow.insertCell(i+4).innerHTML = dispFieldA[i+3];	
		trow.insertCell(i+5).innerHTML = dispFieldA[i+4];	
		trow.insertCell(i+6).innerHTML = dispFieldA[i+5];	
		trow.insertCell(i+7).innerHTML = dispFieldA[i+6];

	var end=dispFieldA[i+7].split('^');
	var no=eval(end.length)/eval(3);
	
	var f=0;
	 var td1=document.createElement("td");
	  td1.cellpadding="0";
	   td1.cellspacing="0";
	   
	   var y=document.createElement("table");
	   y.width="100%";
	   y.border="1";
	   y.cellpadding="0";
	   y.cellspacing="0";
	   										
	   y.className="TableBorderLTRBN";
	   for(var b=0;b<no;b++)
	   	{
	   										
	   	var tr=document.createElement("tr");
	   										
	   	var td12=document.createElement("td");
	   td12.width="40%";
	   	td12.align="center";
	   	td12.innerHTML=end[f];
	   	f=f+1;
	   										
	   var td2=document.createElement("td");
	   	td2.width="30%";
	   	td2.align="center";
	   	td2.innerHTML=end[f];
	   f=f+1;
	   										
	   	var td3=document.createElement("td");
	   	td3.align="center";
	   	td3.width="30%";
	   	td3.innerHTML=end[f];
	   	f=f+1;
	   	tr.appendChild(td12);
	   	tr.appendChild(td2);
	   	tr.appendChild(td3);
	   	y.appendChild(tr);
	   	}
	   									
	   td1.appendChild(y);
	   trow.insertCell(i+8).innerHTML =td1.innerHTML;
	   										
	   	f=0;
			
		//trow.insertCell(i+8).innerHTML = dispFieldA[i+7];
		trow.insertCell(i+9).innerHTML = dispFieldA[i+8];
		trow.insertCell(i+10).innerHTML = dispFieldA[i+9];
		trow.insertCell(i+11).innerHTML = dispFieldA[i+10];
		var editCap = "";
		var delCap  = "";
		var viewCap = "";
		try 
		{
			editCap = cmnLblArray[0];
			delCap  = cmnLblArray[1];
			viewCap = cmnLblArray[4];
		}
		catch (e)
		{
//			alert ("EXCEPTION THROWN ");
			editCap = "Edit";
			delCap  = "Delete";
			viewCap = "View";
		}
			
		if(editMethodName!='' && deleteMethodName!='' && viewMethodName!='') 		
		trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + 
											 "')>"+viewCap+"</a> / <a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id +
											 "')>"+editCap+"</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>"+delCap+"</a>";			
		
		else if(editMethodName!='' && deleteMethodName!='' && viewMethodName=='')
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id +
												 "')>"+editCap+"</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>"+delCap+"</a>";										
												 
		else if(editMethodName!='' && deleteMethodName=='' && viewMethodName!='')											 
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + 
												 "')>"+viewCap+"</a> / <a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id +  "')>"+editCap+"</a>";
												 
		else if(editMethodName=='' && deleteMethodName!='' && viewMethodName!='')											 											 
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + 
												 "')>"+viewCap+"</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>"+delCap+"</a>";			
		
		else if(editMethodName=='' && deleteMethodName!='' && viewMethodName=='')
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>"+delCap+"</a>";
			
		else if(editMethodName!='' && deleteMethodName=='' && viewMethodName=='')
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id + "')>"+editCap+"</a>";		
			
		else if(editMethodName=='' && deleteMethodName=='' && viewMethodName!='')		
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id +  "')>"+viewCap+"</a>";
		counter++;
		
		return trow.id;
	}


var idlist=new Array();
	var tmp="${idforlevel1}";
    var paramArray=new Array();
    
    
	var len=tmp.length;
	len=len*1-1;
	var idStr=tmp.substr(1,len-1);
	idlist=idStr.split(", ");
	var cnt=0;
	var gcnt=0;
	var locid='';
	var dupempid = new Array();
	var emparray = new Array();
	var empuserid = new Array();
	var deletedEmpID=new Array();
	var rowIdDel=new Array();
	var empcount = 0;
	var trnloc = '';
 	var updateUserId = '';
	var trntype = '';
	var i=0;
	var subid=100;
var dbRowID='';
var rsnTransfer='';

function populateTxt(combo)
{

if(combo.value=='OtherRsn')
{

document.getElementById('textTrforOther').style.display='';
document.getElementById('Reasontransfer').value='';
document.getElementById('Reasontransfer').style.display='';
}
else
{

document.getElementById('textTrforOther').style.display='none';
document.getElementById('Reasontransfer').style.display='none';
}
}

var mikExp = /[$\\@\\\#%\^\&\*\(\)\[\]\+\_\{\}\`\~\=\|]/;
function dodacheck(val) {

var strPass = val.value;
var strLength = strPass.length;
var lchar = val.value.charAt((strLength) - 1);
if(lchar.search(mikExp) != -1) {
var tst = val.value.substring(0, (strLength) - 1);
val.value = tst;
   }
}

var ind=0;
function multipleDlt()
{
var answer=confirm('<fmt:message bundle="${transferLables}" key="trnalert.delallChbx"/>');

if(answer==true)
{
var rowd=document.getElementsByName('rowuserid');

	var count=0;
	var rowcount=0;
for(var cx=0;  cx<rowd.length ; cx++){
	
		var pnode=rowd[cx].parentNode;
			 	 	
			 	 	var pnodtr=pnode.parentNode;
	
			if(rowd[cx].status && pnodtr.style.display=="")
			{
			
			var idrow=rowd[cx].id;
			
			var splitid=idrow.split('^');
			
			deletedEmpID[count]=splitid[1];
			count=count+1;
			
			 	 	var pnodetd=rowd[cx].parentNode;
			 	 	
			 	 	var pnodetr=pnodetd.parentNode;
			 	 	
			 	 	var roWID=pnodetr.id;
			 	 	
			 	 	if(roWID.charAt(3)=='t')
			 	 	{
			 	 	
			 	 			rowIdDel[rowcount]=roWID;
			 	 			rowcount=rowcount+1;
   							// trow.parentNode.removeChild(trow);
							roWID="";		
			 	 	}
			 	 	if(roWID.charAt(3)=='d')
			 	 	{
			 	 	
			 	 	
			 	 		var delRow 	= document.getElementById(roWID);
					var content = delRow.cells[0].innerHTML;
					showAlert(content);
					content = content.replace(".xml_N",".xml_D");
					content = content.replace(".xml_U",".xml_D");
					delRow.cells[0].innerHTML = content;					
		
					var   src=delRow.rowIndex;
					
		deletedArrayindex[ind]=src;
		
					//var  dest=eval(document.getElementById('AfterTransEmpDt').rows.length)-eval(1);
		
					//document.getElementById('AfterTransEmpDt').moveRow(src,dest);
					deletedArray[deletedArray.length]=roWID;
		 
    		 		roWID="";
			 	 	ind=ind+1;
			 	 	}
			 	 	
		}
								 
}

for(var abc=0;abc<deletedArrayindex.length;abc++)
{

			var   src=deletedArrayindex[abc];
		
					var  dest=eval(document.getElementById('AfterTransEmpDt').rows.length)-eval(1);
		
					document.getElementById('AfterTransEmpDt').moveRow(src,dest);
}

for(var bx=0;bx<rowIdDel.length;bx++)
{

	
trow=document.getElementById(rowIdDel[bx]);
if(trow!=null)
{
	if(trow.style.display=="")
	{
		 	 			
 trow.parentNode.deleteRow(trow.rowIndex);
 }
 }
}
for(var b=0;b<deletedArrayindex.length;b++)
{
			
deletedArrayindex.splice(b,1);
			
}  
ind=0;
pager.init(); 
pager.showPageNav('pager', 'pageNavPosition'); 
pager.showPage(1);												    
getListOfEmpdel();			    
}

}

function getListOfEmpdel(){
	
		 try{   
    	
    			xmlHttp=new XMLHttpRequest();    
	    
	    	}catch (e){
			    // Internet Explorer    
					try{
					
      					xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");      
      				
      				}catch (e){
		          		
		          		try{
		          		
                	           	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
       		  			
       		  			}catch (e){
       		  			
			           	   	  alert('not supported');        
			            	  return false;        
			      		}
			 		}			 
        	}	
        	 
       
		 
		
			if(deletedEmpID != ""){
			
				 
					var url = "hrms.htm?actionFlag=empDtlsForTrns&empArray="+deletedEmpID;  
					
			}
			
			
			
		xmlHttp.open("POST", encodeURI(url) , true);
		for(var b=0;b<deletedEmpID.length;b++){
			
					deletedEmpID.splice(b,1);
			
			}  
			
		xmlHttp.onreadystatechange = processResponseforEmpTrans;
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));	
 		
 	 
}	


var chxaftbox=0;
function checkallaftTrans()
{
if(chxaftbox==0)
{
if(document.getElementsByName('rowuserid').length==0)
{
alert('<fmt:message bundle="${transferLables}" key="trnalert.datamsng"/>');	
   			
			document.getElementById('checkAllafttrans').checked = false;
			return false;

}
else
{
		var a=document.getElementsByName('rowuserid');
		/*for(var i=0;  i<a.length ; i++){
		
		 	a[i].checked=true;
		}*/
		var rows = document.getElementById("AfterTransEmpDt").rows;
     
        for (var i = 1; i < rows.length; i++) {
        
        if(rows[i].style.display=="")
        {
          a[eval(i)-eval(1)].checked=true;
        }
        }
          
		chxaftbox=1;

return true;

}
}
else if(chxaftbox==1)
{

var c=document.getElementsByName('rowuserid');

	/*	for(var b=0;  b<c.length ; b++){
			
			 	 	c[b].checked=false;
			 	 	
		
		}*/
		var rows = document.getElementById("AfterTransEmpDt").rows;
      for (var i = 1; i < rows.length; i++) {
        
        if(rows[i].style.display=="")
        {
     c[eval(i)-eval(1)].checked=false;
        }
        }
		chxaftbox=0;
		return true;
}
}

function deleteAllCheckedUserId() {
	var a = document.getElementsByName('userid');
	for(i=a.length-1 ;  i>=1 ; i--) {
			if(a[i].checked==true) {
				document.getElementById('transferEmp').deleteRow(i);
			}
	}
}		


function empTransLoc(){

		 trnloc = document.getElementById('locnm').value;
		trntype = document.getElementById('typeTrns').value;
		rsnTransfer=document.getElementById('Reasontransfer').value;
		dodacheck(document.getElementById('Reasontransfer'));	
	
	if(document.getElementById('typeTrns').selectedIndex==0 && document.getElementById('locnm').selectedIndex==0){
   	
   			alert('<fmt:message bundle="${transferLables}" key="trnalert.trntype"/>');
   			alert('<fmt:message bundle="${transferLables}" key="trnalert.trnloc"/>');	
   			return false;
   }
   
   if(document.getElementById('typeTrns').value=='OtherRsn')
   {
   var axn=document.getElementById('Reasontransfer').value.trim();
   
 if(axn=="")  
 {
   		alert('<fmt:message bundle="${transferLables}" key="trnalert.noreason"/>');
  	 	return false;
  	 	}
   }
   if(document.getElementById('typeTrns').selectedIndex==0){
   	
   			alert('<fmt:message bundle="${transferLables}" key="trnalert.trntype"/>');	
   			return false;
   }
   
   if(document.getElementById('locnm').selectedIndex==0){
   		
   		alert('<fmt:message bundle="${transferLables}" key="trnalert.trnloc"/>');
   		return false;
   }
	 var relDt= document.frmTransfer.trnsDate.value;
  if(relDt=="")
  {
  		alert('<fmt:message bundle="${transferLables}" key="trnalert.trnsdate"/>');
   		return false;
  }
	
	
			deleteAllCheckedUserId();

		 try{   
    		
    			xmlHttp=new XMLHttpRequest();    
	    	
	    	}catch (e)
			{    // Internet Explorer    
					try{
      					xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");      
      				
      				}catch(e){
		          		
		          		try
        		  		{
                	  	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
       		  			}catch (e){
			           	   	  
			           	   	  alert('not supported');        
			            	  return false;        
			      		
			      		}
			 		}			 
        	}	
        	 	
			if(emparray != ""){
			
			var url = "hrms.htm?actionFlag=empDtlsAfterTrns&emparray="+emparray+"&transloc="+trnloc+"&trnstype="+trntype+"&rsnTransfer="+rsnTransfer+"&transferDate="+relDt;  			
			}
			
	   		xmlHttp.open("POST", encodeURI(url) , true);			
			xmlHttp.onreadystatechange = processResponseforAfterEmpTrans;
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));	
			document.frmTransfer.locnm.selectedIndex = 0;
			document.frmTransfer.typeTrns.selectedIndex = 0;
   			document.getElementById('trloc').style.display='none';
   			document.getElementById('Add').style.display='none';
   				
}	


function processResponseforAfterEmpTrans(){
  
           if (xmlHttp.readyState == 4){
                
				if (xmlHttp.status == 200){
				    
				    	var decXML = xmlHttp.responseText;
					 	var xmlDOM = getDOMFromXML(decXML);
					 	var xmlFilePath = xmlDOM.getElementsByTagName('xmlfile');
					 	var empUserId = xmlDOM.getElementsByTagName('userId');	
   						var empName =xmlDOM.getElementsByTagName('empName');
   						
   					     var empTranLoc = xmlDOM.getElementsByTagName('Trans_Location');
				    	var empTranType = xmlDOM.getElementsByTagName('Transfer_Type');			    	
				     	
				    	var designation = xmlDOM.getElementsByTagName('designation');
   						var empDateR = xmlDOM.getElementsByTagName('dater');
   						var empDateP = xmlDOM.getElementsByTagName('datep');
   						var empLoc = xmlDOM.getElementsByTagName('location');
					   var empnativePl = xmlDOM.getElementsByTagName('nativeplace');
				    	var postinghist = xmlDOM.getElementsByTagName('postinghistory');			    	
				    	var transferDate = xmlDOM.getElementsByTagName('TransferDate');			    	
				    	
				    	
				     		for ( var i = 0 ; i < empName.length ; i++ ){
				    	   
				    	 			var xmlfilepath = xmlFilePath[i].childNodes[0].text;
				    	 	 		var userid = empUserId[i].childNodes[0].text;
				    	 			var name = empName[i].childNodes[0].text;
				    	 			var dsgn = designation[i].childNodes[0].text;
				    	 			var loc  = empLoc[i].childNodes[0].text;
				    	 			var datep = empDateP[i].childNodes[0].text;
				    	 			var ntvplace  = empnativePl[i].childNodes[0].text;
				    	 			var dater = empDateR[i].childNodes[0].text;
				    	 			
				    	 			var psthistory  = postinghist[i].childNodes[0].text;
				    	 			var emptranloc = empTranLoc[i].childNodes[0].text;
				         			var emptrantype = empTranType[i].childNodes[0].text;
				         			var trnsDate = transferDate[i].childNodes[0].text;
				    	 			
				    	 			var displayFieldA  = new Array(userid,name,dsgn,loc,datep,ntvplace,dater,psthistory,emptranloc,emptrantype,trnsDate);
									document.getElementById('Reasontransfer').value='';
									document.frmTransfer.trnsDate.value='';
										document.getElementById('textTrforOther').style.display='none';

			      						document.getElementById('Reasontransfer').style.display='none';
										var idm=addDBDataInTableAftTransfer('AfterTransEmpDt','trnsPunch',displayFieldA,xmlfilepath,'editRecord','delRecord','');
										var rowInd=document.getElementById(idm);
										var   src=rowInd.rowIndex;
									document.getElementById('AfterTransEmpDt').moveRow(src,1);									
								
					   		}
					   				pager.init(); 
       								pager.showPageNav('pager', 'pageNavPosition'); 
								    pager.showPage(1);			   	
				  }			
	     						
				}	
				  
	  for(var b=0;b<emparray.length;b++){
	  
			emparray.splice(b,1);
			empcount--;
			
      } 			
}
function editRecord(rowId){
var answer=confirm('<fmt:message bundle="${transferLables}" key="trnalert.editreq"/>');
if(answer==true)
{
	updateRow = null;
	sendAjaxRequestForEdit(rowId,'editDt');
	}
}
function deleteDBRecord(rowId)
{
var answer=confirm('<fmt:message bundle="${transferLables}" key="trnalert.delreq"/>');

if(answer==true)
{
	dbRowID=rowId;
 	updateRow = null;
    sendAjaxRequestForEdit(rowId,'delDtDB');
	
	}
		
}
function delDtDB(){
  
    document.getElementById('trloc').style.display='none';
    document.getElementById('srcdAdd').disabled='';
    document.getElementById('Add').style.display='none';
    
	document.getElementById('btnUpdate').style.display='none';
	document.getElementById('btnUpdateDB').style.display='none';
		if (xmlHttp.readyState == 4){
		 				  
				   	var decXML = xmlHttp.responseText;
					var xmlDOM = getDOMFromXML(decXML);																			
	     		 	updateUserId = getXPathValueFromDOM(xmlDOM,'userId');
			       	document.getElementById('updateUserId').value = updateUserId;
			     
				empuserid.splice(0,empuserid.length);
				empuserid[0] = updateUserId; 
				var delCap = "";
		
			var delRow 	= document.getElementById(dbRowID);
			var content = delRow.cells[0].innerHTML;
			showAlert(content);
			content = content.replace(".xml_N",".xml_D");
			content = content.replace(".xml_U",".xml_D");
			delRow.cells[0].innerHTML = content;					
			//delRow.style.display='none';
			getListOfEmp();
	
		var   src=delRow.rowIndex;
		
		var  dest=eval(document.getElementById('AfterTransEmpDt').rows.length)-eval(1);
		
		document.getElementById('AfterTransEmpDt').moveRow(src,dest);
		deletedArray[deletedArray.length]=dbRowID;
	 	pager.init(); 
       	pager.showPageNav('pager', 'pageNavPosition');
       	pager.showPage(1);
								    	
        }
        
         
}
function delRecord(rowId){

var answer=confirm('<fmt:message bundle="${transferLables}" key="trnalert.delreq"/>');
if(answer==true)
  {
    updateRow = null;
    sendAjaxRequestForEdit(rowId,'delDt');
	trow=document.getElementById(rowId);
    trow.parentNode.deleteRow(trow.rowIndex);
	 pager.init(); 
     pager.showPageNav('pager', 'pageNavPosition'); 
	 pager.showPage(1);
  }
	
}

function delDt(){
  
    document.getElementById('trloc').style.display='none';
    document.getElementById('srcdAdd').disabled='';
    document.getElementById('Add').style.display='none';
     document.getElementById('btnUpdate').style.display='none';
      document.getElementById('btnUpdateDB').style.display='none';
	
		if (xmlHttp.readyState == 4){
		 				  
				   	var decXML = xmlHttp.responseText;
					var xmlDOM = getDOMFromXML(decXML);																			
	     		  	updateUserId = getXPathValueFromDOM(xmlDOM,'userId');
			      	
			      	document.getElementById('updateUserId').value = updateUserId;
			       
			for(var b=0;b<empuserid.length;b++)
			{
					empuserid.splice(b,1);
			}
				empuserid[0] = updateUserId; 
				getListOfEmp();    
        }
  
}

	
function editDt(){
  
   
		if (xmlHttp.readyState == 4){
		 				  
				   	var decXML = xmlHttp.responseText;
					var xmlDOM = getDOMFromXML(decXML);			
				 	document.getElementById('sel_Loctype').value=getXPathValueFromDOM(xmlDOM,'depId');
				 	locid=getXPathValueFromDOM(xmlDOM,'locId');
			      	document.getElementById('typeTrns').value=getXPathValueFromDOM(xmlDOM,'transType');
			      	var dtArray = new Array();
			      	var dt=getXPathValueFromDOM(xmlDOM,'transferDate');
			      	dtarray= getDateAndTimeFromDateObj(dt);
			      	document.getElementById('trnsDate').value=dtarray[0];
			      	if(getXPathValueFromDOM(xmlDOM,'transType')=='OtherRsn')
			      	{
			      	document.getElementById('textTrforOther').style.display='';

			      	document.getElementById('Reasontransfer').style.display='';
			      	document.getElementById('Reasontransfer').value=getXPathValueFromDOM(xmlDOM,'rsnForTrans');
			      	}
			       	updateUserId = getXPathValueFromDOM(xmlDOM,'userId');
			      	document.getElementById('updateUserId').value = updateUserId;
                 
        }
   
    document.getElementById('AddTrsDt').style.display='none';
    document.getElementById('btnUpdate').style.display=''; 	
     document.getElementById('trloc').style.display='';
    document.getElementById('srcdAdd').disabled="true";
    document.getElementById('Add').style.display='none';
   getLocation1(document.getElementById('sel_Loctype'));
	
}
function editDBRecord(rowId){

var answer=confirm('<fmt:message bundle="${transferLables}" key="trnalert.editreq"/>');
if(answer==true)
{
	updateRow = null;
	sendAjaxRequestForEdit(rowId,'editDtDB');
}
}
function editDtDB(){
  
   
		if (xmlHttp.readyState == 4){
		 				  
				var decXML = xmlHttp.responseText;
				var xmlDOM = getDOMFromXML(decXML);	
				document.getElementById('serialno').value=getXPathValueFromDOM(xmlDOM,'serialNo');
				document.getElementById('sel_Loctype').value=getXPathValueFromDOM(xmlDOM,'depId');
				 locid=getXPathValueFromDOM(xmlDOM,'locId');
					
			     document.getElementById('typeTrns').value=getXPathValueFromDOM(xmlDOM,'transType');
			      
			     var dtArray = new Array();
			     var dt=getXPathValueFromDOM(xmlDOM,'transferDate');
			     dtarray= getDateAndTimeFromDateObj(dt);
			     document.getElementById('trnsDate').value=dtarray[0];
			     if(getXPathValueFromDOM(xmlDOM,'transType')=='OtherRsn')
			     {
			      document.getElementById('Reasontransfer').style.display='';
			      document.getElementById('textTrforOther').style.display='';
			      document.getElementById('Reasontransfer').value=getXPathValueFromDOM(xmlDOM,'rsnForTrans');
			      }
			       updateUserId = getXPathValueFromDOM(xmlDOM,'userId');
			      document.getElementById('updateUserId').value = updateUserId;
                   
   }
 document.getElementById('AddTrsDt').style.display='none';
 document.getElementById('btnUpdate').style.display='none'; 
 document.getElementById('btnUpdateDB').style.display=''; 	
 document.getElementById('trloc').style.display='';
 document.getElementById('srcdAdd').disabled="true";
 document.getElementById('Add').style.display='none';
 getLocation1(document.getElementById('sel_Loctype'));
}
function UpdateDBRecord()
{
dodacheck(document.getElementById('Reasontransfer'));	

	if(document.getElementById('typeTrns').selectedIndex==0 && document.getElementById('locnm').selectedIndex==0){
   	
   			alert('<fmt:message bundle="${transferLables}" key="trnalert.trntype"/>');
   			alert('<fmt:message bundle="${transferLables}" key="trnalert.trnloc"/>');	
   			return false;
   }
   
   if(document.getElementById('typeTrns').selectedIndex==0){
   	
   			alert('<fmt:message bundle="${transferLables}" key="trnalert.trntype"/>');
   			
   			return false;
   }
   if(document.getElementById('typeTrns').value=='OtherRsn')
   {
   var axn=document.getElementById('Reasontransfer').value.trim();
   
 if(axn=="")  
 {
   		alert('<fmt:message bundle="${transferLables}" key="trnalert.noreason"/>');
  	 	return false;
  	 	}
   }
   
   if(document.getElementById('locnm').selectedIndex==0){
   		
   		alert('<fmt:message bundle="${transferLables}" key="trnalert.trnloc"/>');	
   			
   		return false;
   }
    var relDt= document.frmTransfer.trnsDate.value;
  if(relDt=="")
  {
  		alert('<fmt:message bundle="${transferLables}" key="trnalert.trnsdate"/>');
   		return false;
  }
   
 addOrUpdateRecord('updateRecordDBTransfer','updateTransferDt', new Array('locnm','updateUserId','typeTrns','serialno','Reasontransfer','trnsDate'));
   document.getElementById('AddTrsDt').style.display='';
   document.getElementById('btnUpdateDB').style.display='none'; 	
   
   document.getElementById('srcdAdd').disabled='';
   document.getElementById('trloc').style.display='none';
}
function setUpdateRecord(){
   
    dodacheck(document.getElementById('Reasontransfer'));	
   if(document.getElementById('typeTrns').selectedIndex==0 && document.getElementById('locnm').selectedIndex==0){
   	
   			alert('<fmt:message bundle="${transferLables}" key="trnalert.trntype"/>');
   			alert('<fmt:message bundle="${transferLables}" key="trnalert.trnloc"/>');	
   			return false;
   }
   
   if(document.getElementById('typeTrns').selectedIndex==0){
   	
   			alert('<fmt:message bundle="${transferLables}" key="trnalert.trntype"/>');
   			return false;
   }
   if(document.getElementById('typeTrns').value=='OtherRsn')
   {
   var axn=document.getElementById('Reasontransfer').value.trim();
   
 if(axn=="")  
 {
   		alert('<fmt:message bundle="${transferLables}" key="trnalert.noreason"/>');
  	 	return false;
  	 	}
   }
   
   if(document.getElementById('locnm').selectedIndex==0){
   		
   		alert('<fmt:message bundle="${transferLables}" key="trnalert.trnloc"/>');	
   			
   		return false;
   }
    var relDt= document.frmTransfer.trnsDate.value;
  if(relDt=="")
  {
  		alert('<fmt:message bundle="${transferLables}" key="trnalert.trnsdate"/>');
   		return false;
  }
   
   
   addOrUpdateRecord('updateRecordTransfer','updateTransferDt', new Array('locnm','updateUserId','typeTrns','Reasontransfer','trnsDate'));
   document.getElementById('AddTrsDt').style.display='';
   document.getElementById('btnUpdate').style.display='none'; 	
   
   document.getElementById('srcdAdd').disabled='';
   document.getElementById('trloc').style.display='none';

} 
 function updateDataInTableforTransfer(hiddenField, displayFieldArray) 
	{
		
		var trow = document.getElementById(updateRow);
		
		var hFieldId = updateRow.substring(3, updateRow.length);
		
		updateRow = null;
		
		
		if(flagForUpdatedVO)
		{
		
			document.getElementById(hFieldId).value = xmlHttp.responseText +"_U";
			flagForUpdatedVO = false;
		}
		else
		{
			document.getElementById(hFieldId).value = xmlHttp.responseText;
		}
		
		
		trow.cells[0].style.display = 'none';
		
		var field = document.getElementById(displayFieldArray[0]);
		var type = document.getElementById(displayFieldArray[1]);
		trow.cells[9].innerHTML = field.options[field.selectedIndex].text;	
		
		if(document.getElementById(displayFieldArray[1]).value=='OtherRsn')
	{
			 document.getElementById('Reasontransfer').style.display='none';
			 document.getElementById('textTrforOther').style.display='none';
			  var othrdisTrans=type.options[type.selectedIndex].text+" ("+document.getElementById('Reasontransfer').value+")";
			 
			    trow.cells[10].innerHTML= othrdisTrans; 			
			      			
	}
	else
	{
		
		trow.cells[10].innerHTML = type.options[type.selectedIndex].text;		
		}	
		
		trow.cells[11].innerHTML=document.frmTransfer.trnsDate.value;
		document.frmTransfer.trnsDate.value='';
	}
   

function updateRecordDBTransfer(){
	 
     		if (xmlHttp.readyState == 4){
 
			 	    if (xmlHttp.status == 200){
				        	
				    		var decXML = xmlHttp.responseText;
				 			    	
				    		
				     		 var displayFieldArray = new Array("locnm","typeTrns","trnsDate");
	      				 	 updateDataInTableforTransfer('dbTransferPunch', displayFieldArray); 
	     					 document.getElementById('btnUpdateDB').style.display='none'; 
						 
						
	 				}
			}
}

   
function updateRecordTransfer(){
	  
     		if (xmlHttp.readyState == 4){
 
		 
				    if (xmlHttp.status == 200){
				        	
				    		var decXML = xmlHttp.responseText;
							 
							    	
				    		
			      			
				     		 var displayFieldArray = new Array("locnm","typeTrns","trnsDate");
				     		
	      				 	 updateDataInTableforTransfer('trnsPunch', displayFieldArray); 
	     					 document.getElementById('btnUpdate').style.display='none'; 
						 
						
	 				}
			}
}

var checkFlag = 0;
	
function checkall(){
 
	var a = document.getElementsByName('userid');
	
	
		if(a.length==1){
		
			alert('<fmt:message bundle="${transferLables}" key="trnalert.datamsng"/>');	
   			
			document.getElementById('checkAll').checked = false;
			return false;
			
		}
		
		
		
	if(checkFlag == 0)
	{
			emparray.splice(0,emparray.length);
			empcount = 0;
			
		
		for(var i=1;  i<a.length ; i++){
			
			 	 	a[i].checked=true;
			 	 	checkclick(a[i]);
		
		}
		document.getElementById('Add').style.display = '';
		checkFlag = 1;
		
		
	}
	
	else if(checkFlag == 1)
	{
		for(var i=1;  i<a.length ; i++){
			 	 a[i].checked=false;
		}
			
			document.getElementById('Add').style.display = 'none';
			emparray.splice(0,emparray.length);
			checkFlag = 0;
	}
	
}


function delchecked(){

var answer=confirm('<fmt:message bundle="${transferLables}" key="trnalert.delallChbx"/>');
if(answer==true)
{

	var a = document.getElementsByName('userid');

	for(var i=a.length-1;i>0; i--) {
			
			if(a[i].checked==true) {
				
					 
					emparray.splice(0,emparray.length);
					empcount = 0;
     				 
     				 for(var b=0; b<dupempid.length; b++){
     				 
     				 		if(a[i].id == dupempid[b])
     				 		{
     				 			dupempid.splice(b,1);
     				 		}
     				 
     				 
     				 }		
				
			document.getElementById('transferEmp').deleteRow(i);
				
				
			}

	}
document.getElementById('checkAll').checked = false;
document.getElementById('Add').style.display = 'none';
checkFlag = 0;

}
}
function checkclick(form){

		if(form.checked == true)
		{
   				document.getElementById('Add').style.display = '';
				emparray[empcount]=form.value;
				 
				empcount++;
				 
		}else
		{ 
  				for(var i=0; i<emparray.length; i++)
  				{  
				 
  
  						if(emparray[i]== form.id){
  								
  								emparray.splice(i,1);
    							empcount--;
  						}
  					if(emparray==""){
				
						document.getElementById('Add').style.display = 'none';
			  			document.getElementById('trloc').style.display='none';
  						 
						}
				}	
			}

 
}

function empTransDt(){
		
		 
		
		document.getElementById('checkAll').checked = false;
		checkFlag = 0;
		document.getElementById('sel_Loctype').value = 'select';
		document.getElementById('typeTrns').selectedIndex = 0;
		
		document.getElementById('trloc').style.display = '';
		 getLocation1(document.getElementById('sel_Loctype'));
		 document.getElementById('AddTrsDt').style.display = '';
		
			
}


function getListOfEmp(){
	
		 try{   
    	
    			xmlHttp=new XMLHttpRequest();    
	    
	    	}catch (e){
			    // Internet Explorer    
					try{
					
      					xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");      
      				
      				}catch (e){
		          		
		          		try{
		          		
                	           	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
       		  			
       		  			}catch (e){
       		  			
			           	   	  alert('not supported');        
			            	  return false;        
			      		}
			 		}			 
        	}	
        	 
       	var userId = document.getElementById('userId').value;
		 
		
			if(empuserid != ""){
			
				 
					var url = "hrms.htm?actionFlag=empDtlsForTrns&empArray="+empuserid;  
					
			}
			
			for(var b=0;b<empuserid.length;b++){
			
					empuserid.splice(b,1);
			
			}  

			
		xmlHttp.open("POST", encodeURI(url) , true);			
		xmlHttp.onreadystatechange = processResponseforEmpTrans;
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));	
 		
 	 
}	




function processResponseforEmpTrans(){

	if (xmlHttp.readyState == 4){
	     
				if (xmlHttp.status == 200){    
				    	
				    	var decXML = xmlHttp.responseText;

						var xmlDOM = getDOMFromXML(decXML);
						var empUserId = xmlDOM.getElementsByTagName('userId');	
   						var empName =xmlDOM.getElementsByTagName('empName');
   						var designation = xmlDOM.getElementsByTagName('designation');
   						var empDateR = xmlDOM.getElementsByTagName('dater');
   						var empDateP = xmlDOM.getElementsByTagName('datep');
   						var empLoc = xmlDOM.getElementsByTagName('location');
					   var empnativePl = xmlDOM.getElementsByTagName('nativeplace');
				    	var postinghist = xmlDOM.getElementsByTagName('postinghistory');			    	
				    	
				    	var j=0,k=1;
				    			for ( var i = 0 ; i < empName.length ; i++ ){
				    	   
				    	 					var userid = empUserId[i].childNodes[0].text;
				    	 					var name = empName[i].childNodes[0].text;
				    	 					var loc  = empLoc[i].childNodes[0].text;
				    	 					var datep = empDateP[i].childNodes[0].text;
				    	 					var nativePlace = empnativePl[i].childNodes[0].text;
				    	 					var dater = empDateR[i].childNodes[0].text;
				    	 				 var dsgn = designation[i].childNodes[0].text;
				    	 				 
				    	 					 var psthist = postinghist[i].childNodes[0].text;
				    	 					
				    	 					trow= document.getElementById('transferEmp').insertRow();
				    	 
									    	trow.insertCell(j).innerHTML = '<hdiits:checkbox id="'+userid+'" name="userid" value="'+userid+'"  onclick="checkclick(this)" />';
				   							trow.insertCell(j+1).innerHTML=name;
	   										trow.insertCell(j+2).innerHTML=dsgn;
						   					trow.insertCell(j+3).innerHTML=loc;	   					
					    					trow.insertCell(j+4).innerHTML=datep;	   					
						   					trow.insertCell(j+5).innerHTML=nativePlace;
	   										trow.insertCell(j+6).innerHTML=dater;
								var end=psthist.split('^');
								var no=eval(end.length)/eval(3);
								var f=0;
	   										var td1=document.createElement("td");
	   										td1.cellpadding="0";
	   										td1.cellspacing="0";
	   										
	   										//tr.appendChild(td1);
	   										var y=document.createElement("table");
	   										y.width="100%";
	   										y.border="1";
	   										y.cellpadding="0";
	   										y.cellspacing="0";
	   										
	   										y.className="TableBorderLTRBN";
	   										for(var b=0;b<no;b++)
	   										{
	   										
	   										var tr=document.createElement("tr");
	   										
	   										var td12=document.createElement("td");
	   										td12.width="40%";
	   										td12.align="center";
	   										td12.innerHTML=end[f];
	   										f=f+1;
	   										
	   										var td2=document.createElement("td");
	   										td2.width="30%";
	   										td2.align="center";
	   										td2.innerHTML=end[f];
	   										f=f+1;
	   										
	   										var td3=document.createElement("td");
	   										td3.align="center";
	   										td3.width="30%";
	   										td3.innerHTML=end[f];
	   										f=f+1;
	   										tr.appendChild(td12);
	   										tr.appendChild(td2);
	   										tr.appendChild(td3);
	   										y.appendChild(tr);
	   										}
	   									
	   										td1.appendChild(y);
	   										trow.insertCell(j+7).innerHTML=td1.innerHTML;
	   										j=0;
	   										f=0;
				    			}
				   }							
		}				
}



function SearchEmp(){

		var href='./hrms.htm?actionFlag=getEmpSearchSelData&multiple=true';
		window.open(href,'chield', 'width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
        
      
}

function empSearch(from){

		 	for(var i=0; i<from.length; i++){
				
 
				var empArray = new Array();
				empArray = from[i].split("~"); 
				 
        		empuserid[i]=empArray[2];
      		}

  
  			for(var k=0;k<dupempid.length;k++){
	
				for(var j=0;j<empuserid.length;j++){
			
					if(dupempid[k]==empuserid[j]){
				
						 
							empuserid.splice(j,1);
							
							
				
					}
		
				}
		
		   }
			
		 
     		for(var i=0; i<empuserid.length;i++){
 	       			
 	       			 
 	       			dupempid.splice(dupempid.length,1,empuserid[i]);
 	       			 
         	}
         	
    getListOfEmp();

	
}

function srchRslt(){

	 document.getElementById('detailsTable').style.display="";
     document.getElementById('searchLabel').style.display="";
}


		




function getLocation1(cmb)
{
			var id=cmb.value;	
			
			
			if(id=='') {return;}					
			if(id=='select') {
				var z=document.getElementById('locnm');
				for (var i=z.length;i>=0;i--)
   				{	     	   								
					z.remove(z.value);
					z.remove(i);					   				
   				}
   				var y=document.createElement('option');
   				y.text='--Select--';
				y.value='Select';
   				try
				{
					z.add(y,null); 	    						
				}
				catch(ex)
				{	   			 
	 				z.add(y);	   			 				 
				}
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
			            	  return false;        
			      		}
			 		}			 
        	}   
        	
			var url = "hrms.htm?actionFlag=getTransferchildlocation&cmbid="+id;    
			xmlHttp.open("POST", encodeURI(url) , true);			
			xmlHttp.onreadystatechange = processLevel1;
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));
		}			

function processLevel1(){
						
			if (xmlHttp.readyState == 4){
			     
				if (xmlHttp.status == 200){     

						var textId;												
		            	var z=document.getElementById('locnm');			            		            			            	
				    	var xmlStr = xmlHttp.responseText;
				    	
				    	var XMLDoc=getDOMFromXML(xmlStr);   // xmlHttp.responseXML.documentElement;						    			    	
				    	var SubQuaStr = XMLDoc.getElementsByTagName('SubLevel');				    	
				    	var SubCoCurrStr_ID = XMLDoc.getElementsByTagName('ID'); 
				    	
				    	for (var i=z.length;i>=0;i--){	     				     					
							z.remove(z.value);
							z.remove(i);
	     				}	     		     							
						
						for ( var i = 0 ; i < SubQuaStr.length ; i++ ){
							     		     								
	     				    value=SubQuaStr[i].childNodes[0].text;	     				    
	     				    textId=SubCoCurrStr_ID[i].childNodes[0].text;
	     					var y=document.createElement('option');
		 					y.text=value;
							y.value=textId;

							try
	   						{
	    						z.add(y,null); 	    						
	   						}
	 						catch(ex)
	   						{	   			 
	   			 				z.add(y);	   			 				 
	   						}     						     					
	           			}
	           			if(locid!=''){
	           			
	           			document.getElementById('locnm').value=locid;locid='';
	           			}
				}
				else 
				{  			

				}
			}
}
function Closebt()
{	
	method="POST";
	document.frmTransfer.action="./hrms.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
	
	document.frmTransfer.submit();
}
</script>
<hdiits:fieldGroup titleCaptionId="trn.gnlst" bundle="${transferLables}" expandable="true" mandatory="true" > 
	
		<hdiits:hidden name="wffileId_hidden" id="wffileId_hidden" />
		<hdiits:hidden name="fileId" id="fileId" default="${fileid}"/>
		<hdiits:hidden name="serialno" id="serialno" default=""/>
		
	 <hdiits:hidden name="flag" default="0"/>
	
	<input type="hidden" id="userId" name="userid">

    
	<table width="100%" id="transferEmp" name="transferEmp" border="1" cellpadding="0" cellspacing="0"  class="TableBorderLTRBN">
		<tr class="datatableheader">

			<td width="5%" >
			   <br>
			   <hdiits:checkbox name="checkAll" value="SelectAll" id="checkAll" onclick="checkall()"/>
			  
			</td>
			<td width="15%">
			   <hdiits:caption captionid="trn.empNm" bundle="${transferLables}" captionLang="single"></hdiits:caption>
			</td>
			<td width="10%">
			   <hdiits:caption captionid="trn.designation" bundle="${transferLables}" captionLang="single"></hdiits:caption>
			</td>
			<td width="10%">
			    <hdiits:caption captionid="trn.prstpostplac" bundle="${transferLables}" captionLang="single"></hdiits:caption>
			</td>
			<td width="10%">
			    <hdiits:caption captionid="trn.dateposting"	bundle="${transferLables}" captionLang="single"></hdiits:caption>
			</td>
			<td width="10%">
			    <hdiits:caption captionid="trn.nativeplace" bundle="${transferLables}" captionLang="single"></hdiits:caption>
			</td>
			<td width="10%">
			    <hdiits:caption captionid="trn.dtRetire" bundle="${transferLables}" captionLang="single"></hdiits:caption>
			</td>
			<td width="30%">
			    <table width="100%" border="1" cellpadding="0" cellspacing="0"  class="TableBorderLTRBN">
			    <tr >
			        <td colspan="3" width="100%"><hdiits:caption captionid="trn.posthistory" bundle="${transferLables}" captionLang="single"></hdiits:caption></td>
			    </tr>
			    <tr >
			        <td colspan="1" align="left" width="40%"><hdiits:caption captionid="trn.postplace" bundle="${transferLables}" captionLang="single"></hdiits:caption></td>
			        <td colspan="1" align="left" width="30%"><hdiits:caption captionid="trn.frmdate" bundle="${transferLables}" captionLang="single"></hdiits:caption></td>
			        <td colspan="1" align="left" width="30%"><hdiits:caption captionid="trn.todate" bundle="${transferLables}" captionLang="single"></hdiits:caption></td>
			    </tr>
			    </table>
			</td>
			
		</tr>

	</table>
  <hdiits:button name="DeleteChecked" type="button" bundle="${transferLables}" captionid="trn.rmv" onclick="delchecked()"/>

	<table width="100%">
		<tr align="center">
			<td>
			     <hdiits:button name="srcdAdd" type="button" captionid="trn.srchEmp" bundle="${transferLables}" onclick="SearchEmp()" ></hdiits:button> 
			     <hdiits:button name="Add" captionid="trn.trntoloc"  bundle="${transferLables}" type="button" id="Add"onclick="empTransDt()"  style="display:none"></hdiits:button> 
			     <input type="hidden" id="updateUserId" name="updateUserId">
			</td>
	   </tr>
	</table>

	<table id="trloc" style="display:none" width="100%">
		<tr>
		<td width="25%">
			       <hdiits:caption captionid="trn.type" bundle="${transferLables}"></hdiits:caption>
		</td>
		<td width="25%"><hdiits:caption captionid="trn.locType"
				bundle="${transferLables}"></hdiits:caption>
		</td>
		<td width="25%">
			      <hdiits:caption captionid="trn.trnLoc" bundle="${transferLables}"></hdiits:caption>
		</td>
		<td width="25%">
					<hdiits:caption captionid="trn.transferDate" bundle="${transferLables}"></hdiits:caption>
		</td>
		<tr>
		<td width="25%">
			       <hdiits:select mandatory="true" name="typeTrns" style="width:200px" id="typeTrns" onchange="populateTxt(this)">
				        <hdiits:option value="0"><fmt:message key="trn.select" bundle="${transferLables}" /></hdiits:option>
				        <c:forEach var="transType" items="${transType}">
				            <option value="<c:out value="${transType.lookupName}"/>">
				        <c:out value="${transType.lookupDesc}" /></option>
				       </c:forEach>
			        </hdiits:select>
		 </td>
 
		 <td width="25%">
			  <hdiits:select mandatory="true" name="sel_Loctype" id="sel_Loctype" captionid="trn.locType"
		  		bundle="${transferLables}" onchange="getLocation1(this);  ">
		  		<hdiits:option value="select"><fmt:message key="trn.select" bundle="${transferLables}" /></hdiits:option>
			 		<c:forEach items="${level1}" var="level1">
			 		<option>
					<c:out value="${level1}"/></option>
			 		</c:forEach>	
				</hdiits:select>
		</td>
		<td width="25%">
			      <hdiits:select mandatory="true" name="locnm" style="width:250px" id="locnm">
				          <hdiits:option value="0"><fmt:message key="trn.select" bundle="${transferLables}" /></hdiits:option>
				         
		     	  </hdiits:select> 
		     	  
		</td>
		<td width="25%">
					<hdiits:dateTime name="trnsDate" captionid="trn.transferDate" bundle="${transferLables}" mandatory="true" maxvalue="31/12/2099"/>
		</td>
		</tr>
		<tr style="display: none" id="textTrforOther">
			<td width="25%"><hdiits:textarea  name="Reasontransfer"  id="Reasontransfer" mandatory="true" cols="25"  rows="5" maxlength="50" onkeypress="dodacheck(this)"/></td>
			<td width="25%"></td>
			<td width="25%"></td>
			<td width="25%"></td>
		</tr>
		<tr>
			<td colspan="4" align="center">
			      <hdiits:button name="AddTrsDt" captionid="trn.mkTransfer" type="button" onclick="empTransLoc()" bundle="${transferLables}"></hdiits:button> 
			      <hdiits:button name="btnUpdate" type="button" style="display:none" captionid="trn.updtTrn" onclick="setUpdateRecord()" bundle="${transferLables}"></hdiits:button>
			      <hdiits:button name="btnUpdateDB"  id="btnUpdateDB" type="button" style="display:none" captionid="trn.updtTrn" onclick="UpdateDBRecord()" bundle="${transferLables}"></hdiits:button>  
			</td>
		</tr>

	</table>
	</hdiits:fieldGroup>
	<br>
	<hdiits:fieldGroup titleCaptionId="trn.lstOfTrnEmp" bundle="${transferLables}" expandable="true" mandatory="true" > 
	
	
	
	<div id="pageNavPosition"></div>
	<table width="100%" id="AfterTransEmpDt" name="AfterTransEmpDt"
		border="1" cellpadding="0" cellspacing="0"  class="TableBorderLTRBN" style="display: none">
		<tr class="datatableheader" id='Heading'>

			<td width="5%"><hdiits:checkbox name="checkAllafttrans" value="SelectAllafttrans" id="checkAllafttrans"  onclick="checkallaftTrans()"/></td>
			<td width="10%">
			   <hdiits:caption captionid="trn.empNm" bundle="${transferLables}" captionLang="single"></hdiits:caption>
			</td>
			<td width="10%">
			   <hdiits:caption captionid="trn.designation" bundle="${transferLables}"captionLang="single"></hdiits:caption>
			</td>
			<td width="10%">
			    <hdiits:caption captionid="trn.prstpostplac" bundle="${transferLables}" captionLang="single"></hdiits:caption>
			</td>
			<td width="5%">
			    <hdiits:caption captionid="trn.dateposting"	bundle="${transferLables}" captionLang="single"></hdiits:caption>
			</td>
			<td width="5%">
			    <hdiits:caption captionid="trn.nativeplace" bundle="${transferLables}" captionLang="single"></hdiits:caption>
			</td>
			<td width="5%">
			    <hdiits:caption captionid="trn.dtRetire" bundle="${transferLables}" captionLang="single"></hdiits:caption>
			</td>
			<td width="20%">
			    <table width="100%" border="1" cellpadding="0" cellspacing="0"  class="TableBorderLTRBN">
			    <tr >
			        <td colspan="3" width="100%"><hdiits:caption captionid="trn.posthistory" bundle="${transferLables}" captionLang="single"></hdiits:caption></td>
			    </tr>
			    <tr >
			        <td colspan="1" align="left" width="40%"><hdiits:caption captionid="trn.postplace" bundle="${transferLables}" captionLang="single"></hdiits:caption></td>
			        <td colspan="1" align="left" width="30%"><hdiits:caption captionid="trn.frmdate" bundle="${transferLables}" captionLang="single"></hdiits:caption></td>
			        <td colspan="1" align="left" width="30%"><hdiits:caption captionid="trn.todate" bundle="${transferLables}" captionLang="single"></hdiits:caption></td>
			    </tr>
			    </table>
			</td>
			<td width="10%"><hdiits:caption captionid="trn.locto"
				bundle="${transferLables}" captionLang="single"></hdiits:caption></td>
			<td width="10%"><hdiits:caption captionid="trn.trnType"
				bundle="${transferLables}" captionLang="single"></hdiits:caption></td>
	  		<td width="5%">
			    <hdiits:caption captionid="trn.transferDate" bundle="${transferLables}" captionLang="single"></hdiits:caption>
			</td>
			<td width="5%"><hdiits:caption captionid="trn.action"
				bundle="${transferLables}" captionLang="single"></hdiits:caption></td>
		</tr>
	
	
	</table>
	
	
	   		<hdiits:button name="mutipleDelete" type="button" id="mutipleDelete" captionid="trn.multidel" bundle="${transferLables}" onclick="multipleDlt()" style="display:none"/>
	
	</hdiits:fieldGroup>
	<table align="center" id="blksubmit">
	<tr>
	<td  align="right"><hdiits:formSubmitButton name="trsSubmit"
				id="trsSubmit" type="button" captionid="trn.onReqSubmit"
				bundle="${transferLables}" /></td>
	 <td  align="left"><hdiits:button name="Closebutton"
				id="Closebutton" type="button" captionid="trn.Close"
				bundle="${transferLables}" onclick="Closebt()" /></td>	
				</tr>
				</table>
	
	<c:if test="${not empty TransferVO}">
	<c:forEach items="${TransferVO}" var="TransferVO" varStatus="x">

<c:set var="curXMLFileName" value="${xmlFileName[x.index]}" ></c:set>

<c:set var="userId" value="${TransferVO.userId}"/>

<c:set var="name" value="${TransferVO.empfName}"/>
<c:set var="desig" value="${TransferVO.dsgnName}"/>
<c:set var="currLocation" value="${TransferVO.currentLocation}"/>
<fmt:formatDate var="dop" value="${TransferVO.dateofposting}" pattern="dd/MM/yyyy"/>
<c:set var="nativeplace" value="${TransferVO.nativePlace}"/>

<fmt:formatDate var="dor" value="${TransferVO.dateR}" pattern="dd/MM/yyyy"/>
<c:set var="postihistory" value="${TransferVO.postinghistory}"/>
<c:set var="transfertoLoc" value="${TransferVO.locName}"/>
<c:set var="transferType" value="${TransferVO.transTypelookupName}"/>
<fmt:formatDate var="transDate" value="${TransferVO.transferDate}" pattern="dd/MM/yyyy"/>
<script type="text/javascript">
var xmlFileName = '${curXMLFileName}';

empuserid[i]='${userId}';
 
dupempid.splice(dupempid.length,1,'${userId}');
i++;
var displayFieldA  = new Array('${userId}','${name}','${desig}','${currLocation}','${dop}','${nativeplace}','${dor}','${postihistory}','${transfertoLoc}','${transferType}','${transDate}');

addDBDataInTableAftTransfer('AfterTransEmpDt','dbTransferPunch',displayFieldA,xmlFileName,'editDBRecord','deleteDBRecord','');
</script>
</c:forEach>
	
	</c:if>
			</div>
			
	
	
	</div>
	
<script type="text/javascript">
	
	var sel_level1=document.getElementById('sel_Loctype');
	try{
	for(var i=1;i<=idlist.length;i++)
	{
		sel_level1.options[i].value=idlist[gcnt];
		gcnt++;
	}
	}
	catch(ex){}
	
</script>
<script type="text/javascript">
if(${fileblktrs}=="1")
{
//document.getElementById('header').style.display='none';
//document.getElementById('toolbar').style.display='none';
//document.getElementById('nav').style.display='none';
document.getElementById('blksubmit').style.display='none';
//document.getElementById('footer').style.display='none';
}

</script>
	<script type="text/javascript">		
		initializetabcontent("maintab")
	</script>

	<hdiits:validate controlNames="text" locale='<%=(String)session.getAttribute("locale")%>' />
<script type="text/javascript">
        var pager = new Pager('AfterTransEmpDt', 3); 
        pager.init(); 
        pager.showPageNav('pager','pageNavPosition'); 
        pager.showPage(1);
  </script>
</hdiits:form>
 

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
