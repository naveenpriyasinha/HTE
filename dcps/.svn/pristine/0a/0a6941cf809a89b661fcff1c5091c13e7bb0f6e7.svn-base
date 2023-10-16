
<%
try {
%>

<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script type="text/javascript" src="<c:url value="/script/common/base64.js"/>">
</script>

<script type="text/javascript" src="/script/common/ajax_saveData.js"> </script>

<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>

<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>

<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>

<fmt:setBundle basename="resources.hr.transfer.transferLabels" var="trnEmpLables" scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="level1" value="${resValue.level1}"></c:set>
<c:set var="idforlevel1" value="${resValue.idforlevel1}"></c:set>
<c:set var="PostingHistory" value="${resValue.PostingHistory}"></c:set>
<c:set var="sysdate" value="${resValue.sysdate}"></c:set>





<script type="text/javascript">

	var arrMulti= new Array();
	var temp= 0;
	var array = new Array();
	var counter = 1;
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
	
function addPostingDataInTable(tableId,dispFieldA)
	{
		document.getElementById(tableId).style.display='';
	
		var trow=document.getElementById(tableId).insertRow();
		trow.align="center";
		trow.insertCell(0).innerHTML = dispFieldA[0];
		
		trow.insertCell(1).innerHTML = dispFieldA[1];	
		trow.insertCell(2).innerHTML = dispFieldA[2];	
		trow.insertCell(3).innerHTML = dispFieldA[3];
		trow.insertCell(4).innerHTML = dispFieldA[4];
		trow.insertCell(5).innerHTML = dispFieldA[5];
		}
function getLocation1(cmb)
{
			var id=cmb.value;	
		
			if(id=='') {return;}					
			if(id=='select') {
				var z=document.getElementById('sel_location');
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
			var url = "hrms.htm?actionFlag=getTransferlocation&cmbid="+id;    
			xmlHttp.open("POST", encodeURI(url) , true);			
			xmlHttp.onreadystatechange = processLevel1;
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));
		}			

function processLevel1(){
						
			if (xmlHttp.readyState == 4){
			     
				if (xmlHttp.status == 200){     

						var textId;												
		            	var z=document.getElementById('sel_location');			            		            			            	
				    	var xmlStr = xmlHttp.responseText;
				    	
				    	var XMLDoc=getDOMFromXML(xmlStr);   // xmlHttp.responseXML.documentElement;	
				    					    			    	
				    	var SubQuaStr = XMLDoc.getElementsByTagName('SubLevel');				    	
				    	var SubCoCurrStr_ID = XMLDoc.getElementsByTagName('ID'); 
				    	
				    	for (var i=z.length;i>=0;i--){	     				     					
							z.remove(z.value);
							z.remove(i);
	     				}
	     				var a=document.createElement('option');	     		     							
						a.text='<fmt:message key="trn.select" bundle="${trnEmpLables}" />';
						a.value="select";
						try
	   						{
	    						z.add(a,null); 	    						
	   						}
	 						catch(ex)
	   						{	   			 
	   			 				z.add(a);	   			 				 
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
	           			
	           			document.getElementById('sel_location').value=locid;locid='';
	           			}
				}
				else 
				{  			

				}
			}
}
function reset1()
{
	document.getElementById('sel_Loctype').selectedIndex=0;
	document.getElementById('sel_priority').selectedIndex=0;
	getLocation1(document.getElementById('sel_Loctype'));
	document.formTransfer.reqTransferDate.value="";
}		
function getReqDtls(){
	
	    paramArray[0]='sel_Loctype';
   	 	paramArray[1]='sel_location';
   		paramArray[2]='sel_priority';
   		paramArray[3]='reqTransferDate';
   		if(document.getElementById('sel_Loctype').selectedIndex==0)	{
   		   alert('<fmt:message bundle="${trnEmpLables}" key="trnalert.loctype"/>');
   		}
   		
   		else if(document.getElementById('sel_location').selectedIndex==0){
   		   alert('<fmt:message bundle="${trnEmpLables}" key="trnalert.properLoc"/>');
   		}
   		else if(document.getElementById('sel_priority').selectedIndex==0){
          alert('<fmt:message bundle="${trnEmpLables}" key="trnalert.priority"/>');
       
       }
       else if(document.formTransfer.reqTransferDate.value==""){
          alert('<fmt:message bundle="${trnEmpLables}" key="trnalert.reqTrnsDate"/>');
       
       }
       else if(compareDate('${sysdate}',document.formTransfer.reqTransferDate.value)<0)
{
 alert('<fmt:message bundle="${trnEmpLables}" key="trnalert.gttrnsDate"/>');
}
       else{
   	
   		var checkPriorityDup = new Array('sel_priority');
    	if(( verifyDuplicateForTrans('onReqByEmpTrn' , checkPriorityDup, 'encXML','<fmt:message key="trn.duplicatepriority" bundle="${trnEmpLables}" />'))==true)
   		    {
   				addOrUpdateRecord('generateRequest','onRequestTrsData',paramArray);	
   			 }
   		 
        }
      }


function generateRequest(){


     if (xmlHttp.readyState == 4){

		var ReqTrnsDtls = new Array('sel_Loctype','sel_location','sel_priority','reqTransferDate');
		addDataInTable('onReqByEmpTrn','encXML', ReqTrnsDtls,'editTranDtl','delTranDtl');	
		
		document.getElementById("RsnTransfer").style.display='';
        reset1();
      }


}		
 
 
 function verifyDuplicateForTrans(tableName,elementNameArray,hiddenField,duplicateAlert)
	{
		var value="";
		var	rowId ;
		for(var y=0;y<elementNameArray.length;y++)
		{
			value=value+trim(document.getElementsByName(elementNameArray[y])[0].value);
			if(y<elementNameArray.length-1)
			{
				value=value+",";
			}
		}
		if(arrMulti[tableName])
		{
			var isPresent = false;
			for(x in arrMulti[tableName])
			{
				if(value==arrMulti[tableName][x])
				{
					if(duplicateAlert!=null)
					{
						alert(duplicateAlert);
					}
					else
					{
						var duplicateRecord=cmnLblArray[6];
						if(duplicateRecord)
						{
							alert(duplicateRecord);
						}
						else
						{
							  alert('<fmt:message bundle="${trnEmpLables}" key="trnalert.verifydata"/>');
						}
					}
					isPresent=true;
					return false;
				}
			}
			if(isPresent==false)
			{
				if(rowToUpdate!=null)
				{
					arrMulti[tableName][rowToUpdate]=value;
					rowToUpdate = null;
					array[temp]=value;
				temp++;
	 
					return true;
				}
				else
				{
					rowId ='row' + hiddenField + counter;
					
				 
					arrMulti[tableName][rowId]=value;
					 
					array[temp]=value;
				temp++;
				 
					return true;
				}
			}
		}
		else
		{
			rowId ='row' + hiddenField + 1;
			arrMulti[tableName]=new Array();
			arrMulti[tableName][rowId]=value;
		 
		
			
			array[temp]=value;
				temp++;
	 
			
			return true;
		}
	
 
	}
	
	
function editTranDtl(rowId){
var answer=confirm('<fmt:message bundle="${trnEmpLables}" key="trnalert.editreq"/>');
if(answer==true)
{
	updateRow=null;
	sendAjaxRequestForEdit(rowId,'transferEdit');
	
    document.getElementById('Req_Trns').style.display='none'
    document.getElementById('update_Req_Trns').style.display='';
	rowToUpdate =rowId;
    deleteFromDuplicateArray('onReqByEmpTrn',rowId);
    }
}

function delTranDtl(rowId){
 	var answer=confirm('<fmt:message bundle="${trnEmpLables}" key="trnalert.delreq"/>');

if(answer==true)
{
 	updateRow = null;
    trow=document.getElementById(rowId);
    trow.parentNode.deleteRow(trow.rowIndex);
	deleteFromDuplicateArray('onReqByEmpTrn',rowId);
	}	
}


function transferEdit() {

	  if (xmlHttp.readyState == 4) { 	
	
	  	var decXML = xmlHttp.responseText;
	 	var xmlDOM = getDOMFromXML(decXML);
	 	
	 	var dtArray = new Array();
	 		var dt=getXPathValueFromDOM(xmlDOM,'transferDate');
			      	 dtarray= getDateAndTimeFromDateObj(dt);
			      	
	 	document.formTransfer.reqTransferDate.value=dtarray[0];
		document.formTransfer.sel_priority.value=getXPathValueFromDOM(xmlDOM,'priority'); 
		document.formTransfer.sel_Loctype.value=getXPathValueFromDOM(xmlDOM,'depId');
		locid=getXPathValueFromDOM(xmlDOM,'locId');
		getLocation1(document.formTransfer.sel_Loctype);
   }
}
	
	function updateRecord(){
	
	 	var checkPriorityDup = new Array('sel_priority');
   		var checkLocationTypeDup = new Array('sel_Loctype','sel_location');
   		if(document.getElementById('sel_Loctype').selectedIndex==0){
   		   alert('<fmt:message bundle="${trnEmpLables}" key="trnalert.loctype"/>');
   		   return false;
   		}
   	
   	   if(document.getElementById('sel_location').selectedIndex==0){
   		   alert('<fmt:message bundle="${trnEmpLables}" key="trnalert.properLoc"/>');
   		   return false;
   		}
   	
   	 if(document.getElementById('sel_priority').selectedIndex==0){
             alert('<fmt:message bundle="${trnEmpLables}" key="trnalert.priority"/>');
             return false;
       
       }
        if(document.formTransfer.reqTransferDate.value==""){
          alert('<fmt:message bundle="${trnEmpLables}" key="trnalert.reqTrnsDate"/>');
       return false;
       }
            if(compareDate('${sysdate}',document.formTransfer.reqTransferDate.value)<0)
{
 alert('<fmt:message bundle="${trnEmpLables}" key="trnalert.gttrnsDate"/>');
 return false;
}
     if(( verifyDuplicateForTrans('onReqByEmpTrn' , checkPriorityDup, 'encXML','<fmt:message key="trn.duplicatepriority" bundle="${trnEmpLables}" />'))==true){  
     
            var ReqTrnsDtls = new Array('sel_Loctype','sel_location','sel_priority','reqTransferDate');
   			addOrUpdateRecord('updateRecordTransfer','onRequestTrsData',ReqTrnsDtls);	
   			document.getElementById('update_Req_Trns').style.display='none';
   			document.getElementById('Req_Trns').style.display='';
   	 	
   		}
   		
  
	}
	
	function updateRecordTransfer(){ 
     
     		if (xmlHttp.readyState == 4){
  
				    if (xmlHttp.status == 200){
				        	
				    		 var decXML = xmlHttp.responseText;
	      				 	 updateDataInTable('encXML', paramArray); 
	      				 	
           					 reset1();
	 				}
			
			}
}
	
function submitTransferDtls(){
	
	dodacheck(document.getElementById('rsnOfTrs'));
	var axn=document.getElementById('rsnOfTrs').value.trim();
   
 
	 if(temp==0){
	 	 alert('<fmt:message bundle="${trnEmpLables}" key="trnalert.nodata"/>');
	  	 return false; 	
	}
	else if(document.getElementById('onReqByEmpTrn').rows.length<=1)
	{
	 alert('<fmt:message bundle="${trnEmpLables}" key="trnalert.nodata"/>');
	  	 return false;
	}
	if(temp==1){
	
	    if(!(array[0]==1)){
	    {
	       alert('<fmt:message bundle="${trnEmpLables}" key="trnalert.prty1"/>');
	       return false;
	     }
	   }
	}     
  if(temp==2){
	if(!((array[0]==1) || (array[1]==1))){
    
    	 if(!((array[0]==2) || (array[1]==2))){
 	   		alert('<fmt:message bundle="${trnEmpLables}" key="trnalert.priority"/>');
	        return false;
	}
	
	   alert('<fmt:message bundle="${trnEmpLables}" key="trnalert.priority"/>');
	
	return false;
	}
	
	
	}
	if(axn=="") 
	{
	      alert('<fmt:message bundle="${trnEmpLables}" key="trnalert.noreason"/>');
	      return false;
	   	 }
	var answer=confirm('<fmt:message bundle="${trnEmpLables}" key="trnalert.submitTrnsDtls"/>');
if(answer==true)
{
   document.getElementById('onReqSubmit').disabled=true;
  
	 document.formTransfer.action="hrms.htm?actionFlag=onReqTransferSub";
 	 document.formTransfer.submit();
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
function Closebt()
{	
	method="POST";
	document.formTransfer.action="./hrms.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
	
	document.formTransfer.submit();
}
</script>
<hdiits:form name="formTransfer" validate="true" method="post"  encType="text/form-data">
<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>
<hdiits:fieldGroup titleCaptionId="trn.rqpl" bundle="${trnEmpLables}"  mandatory="true"  > 
	

	<table width="100%" align="center">
	<tr>
	<td width="25%"><hdiits:caption captionid="trn.locType"
				bundle="${trnEmpLables}"></hdiits:caption> </td>
	<td width="25%"><hdiits:caption captionid="trn.trnLoc"
				bundle="${trnEmpLables}"></hdiits:caption></td>
	<td width="25%"><hdiits:caption captionid="trn.prty"
				bundle="${trnEmpLables}"></hdiits:caption></td>
	<td width="25%"><hdiits:caption captionid="trn.reqTrnsdate"
				bundle="${trnEmpLables}"></hdiits:caption></td>
	</tr>
		<tr>
			<td width="25%"><hdiits:select
				mandatory="true" name="sel_Loctype" id="sel_Loctype"
				captionid="trn.locType" bundle="${trnEmpLables}"
				onchange="getLocation1(this);  ">
				<hdiits:option value="select">
					<fmt:message key="trn.select" bundle="${trnEmpLables}" />
				</hdiits:option>
				<c:forEach items="${level1}" var="level1">
					<option><c:out value="${level1}" /></option>
				</c:forEach>
			</hdiits:select></td>
			<td width="25%"> <hdiits:select
				name="sel_location" id="sel_location" captionid="trn.trnLoc"
				mandatory="true" bundle="${trnEmpLables}" style="width:150px">
				<hdiits:option value="select">
					<fmt:message key="trn.select" bundle="${trnEmpLables}" />
				</hdiits:option>
			</hdiits:select></td>

			<td width="25%"> <hdiits:select
				name="sel_priority" id="sel_priority" mandatory="true"
				captionid="trn.prty" bundle="${trnEmpLables}" style="width:150px">
				<hdiits:option value="select">
					<fmt:message key="trn.select" bundle="${trnEmpLables}" />
				</hdiits:option>
				<hdiits:option value="1">1</hdiits:option>
				<hdiits:option value="2">2</hdiits:option>
				<hdiits:option value="3">3</hdiits:option>
			</hdiits:select></td>
			<td width="25%"><hdiits:dateTime name="reqTransferDate" captionid="trn.reqTrnsdate" bundle="${trnEmpLables}" maxvalue="31/12/2099" mandatory="true" /></td>
		</tr>
		<tr>
			<td colspan="4" align="center"><hdiits:button type="button"
				captionid="trn.reqTrn" name="Req_Trns" id="Req_Trns"
				bundle="${trnEmpLables}" onclick="getReqDtls()" /> <hdiits:button
				name="update_Req_Trns" type="button" id="update_Req_Trns"
				captionid="trn.updtTrn" style="display:none"
				bundle="${trnEmpLables}" onclick="updateRecord()" /></td>



		</tr>

	</table>

	<table width="100%" border="1" id="onReqByEmpTrn" style="display:none">
		<tr class="datatableheader">
			<td width="15%"><hdiits:caption captionid="trn.locType"
				bundle="${trnEmpLables}"></hdiits:caption></td>
			<td width="15%"><hdiits:caption captionid="trn.trnLoc"
				bundle="${trnEmpLables}"></hdiits:caption></td>
			<td width="15%"><hdiits:caption captionid="trn.prty"
				bundle="${trnEmpLables}"></hdiits:caption></td>
			<td width="15%"><hdiits:caption captionid="trn.reqDate"
				bundle="${trnEmpLables}"></hdiits:caption></td>	
			<td width="15%"><hdiits:caption captionid="trn.action"
				bundle="${trnEmpLables}"></hdiits:caption></td>
		</tr>
	</table>


	<table width="100%"  id="RsnTransfer">
		<tr>
			<td width="25%"><hdiits:caption captionid="trn.rsnOfTrs"
				bundle="${trnEmpLables}"></hdiits:caption></td>
			<td width="25%"><hdiits:textarea name="rsnOfTrs" id="rsnOfTrs"
				captionid="trn.rsnOfTrs" bundle="${trnEmpLables}" onkeypress="dodacheck(this)" mandatory="true" ></hdiits:textarea></td>
			<td width="25%"></td>
			<td width="25%"></td>
		</tr>

		

	</table>
</hdiits:fieldGroup>

	<hr>
	<br>
<hdiits:fieldGroup titleCaptionId="trn.prvPstDt" bundle="${trnEmpLables}" expandable="true"  > 	
	

	<table width="100%" border="1" name="prevPostDtl" id="prevPostDtl">
		<tr class="datatableheader">
			<td width="20%"><hdiits:caption captionid="trn.pstNm"
				bundle="${trnEmpLables}"></hdiits:caption></td>
			<td width="20%"><hdiits:caption captionid="trn.locType"
				bundle="${trnEmpLables}"></hdiits:caption></td>
			<td width="20%"><hdiits:caption captionid="trn.trnLoc"
				bundle="${trnEmpLables}"></hdiits:caption></td>

			<td width="10%"><hdiits:caption captionid="trn.frmDt"
				bundle="${trnEmpLables}"></hdiits:caption></td>
			<td width="10%"><hdiits:caption captionid="trn.toDt"
				bundle="${trnEmpLables}"></hdiits:caption></td>
			<td width="20%"><hdiits:caption captionid="trn.period"
				bundle="${trnEmpLables}"></hdiits:caption></td>
		</tr>
	</table>
	</hdiits:fieldGroup>
	<br><br><br><br><br><br><br><br>
	<table align="center">
	<tr>
	<td  align="right"><hdiits:button name="onReqSubmit"
				id="onReqSubmit" type="button" captionid="trn.onReqSubmit"
				bundle="${trnEmpLables}" onclick="submitTransferDtls()" /></td>
	 <td  align="left"><hdiits:button name="Closebutton"
				id="Closebutton" type="button" captionid="trn.Close"
				bundle="${trnEmpLables}" onclick="Closebt()" /></td>	
				</tr>
				</table>
				
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />


	

<c:if test="${not empty PostingHistory}">
	<c:forEach items="${PostingHistory}" var="PostingHistory" varStatus="x">



<c:set var="dsgnName" value="${PostingHistory.postName}"/>
<c:set var="depName" value="${PostingHistory.depName}"/>
<c:set var="locName" value="${PostingHistory.locName}"/>
<c:set var="frmDate" value="${PostingHistory.fromDate}"/>
<c:set var="toDate" value="${PostingHistory.toDate}"/>
<c:set var="period" value="${PostingHistory.period}"/>
<script type="text/javascript">

var displayFieldA  = new Array('${dsgnName}','${depName}','${locName}','${frmDate}','${toDate}','${period}');

addPostingDataInTable('prevPostDtl',displayFieldA);
</script>
</c:forEach>
	
	</c:if>
	<script type="text/javascript">
	
	var sel_level1=document.getElementById('sel_Loctype');
	
	for(var i=1;i<=idlist.length;i++)
	{
		sel_level1.options[i].value=idlist[gcnt];
		gcnt++;
	}

	
</script>
</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
