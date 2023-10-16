
<%
try {
%>

<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>


<script type="text/javascript"
	src="<c:url value="/script/common/base64.js"/>">
</script>
<script type="text/javascript" src="/script/common/ajax_saveData.js"> </script>

<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>

<script type="text/javascript"
	src="<c:url value="script/common/calendar.js"/>"></script>

<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>

<fmt:setBundle basename="resources.hr.transfer.transferLabels"
	var="transferLables" scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<c:set var="xmlFileName" value="${resValue.xmlFileName}" />
<c:set var="TransferVO" value="${resValue.TransferVO}" />
<hdiits:form name="ReleaseApprovedTransfered" validate="true"	method="post">



	<script type="text/javascript">
var empcount=0;
var emparray= new Array();
var updateRow1=null;
function checkclick(form){
  
		if(form.checked == true)
		{
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
  					}	
			}

 
}

  function getReleaseEmpLst(){
   
   var relDt= document.ReleaseApprovedTransfered.releaseDt.value;
  if(relDt==""){
  
  	  alert('<fmt:message bundle="${transferLables}" key="trnalert.reldate"/>');
  	 
  return false; 
  }
  
   if(emparray.length==0)
   
   {
   		alert('<fmt:message bundle="${transferLables}" key="trnalert.plsselrec"/>');
   
   return false;
   }
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
			
					 
					var url = "hrms.htm?actionFlag=generateReleaseTransferList&emparray="+emparray+"&relDt="+relDt;  
					document.getElementById('ApprovedWithRelDt').style.display='';	
			}
			
	   		xmlHttp.open("POST", encodeURI(url) , true);			
			xmlHttp.onreadystatechange = processResponseforReleasedTrnsEmp;
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));	
               document.ReleaseApprovedTransfered.releaseDt.value="";

				
   }
   
   
function processResponseforReleasedTrnsEmp(){
  
           if (xmlHttp.readyState == 4){
                
				if (xmlHttp.status == 200){
				    
				    	var decXML = xmlHttp.responseText;
					 
						var xmlDOM = getDOMFromXML(decXML);
					 	
					 	var xmlFilePath = xmlDOM.getElementsByTagName('xmlfile');
					 
					    var empSerialNo = xmlDOM.getElementsByTagName('serialNo');	
						 
						var empUserId = xmlDOM.getElementsByTagName('userId');	
						 
   						var empName =xmlDOM.getElementsByTagName('empName');
   						var empLoc = xmlDOM.getElementsByTagName('locName');
   						var empDateR = xmlDOM.getElementsByTagName('dater');
   						var empDateJ = xmlDOM.getElementsByTagName('datej');
   						 var empTranLoc = xmlDOM.getElementsByTagName('location');
				    	var empTranType = xmlDOM.getElementsByTagName('TransType');			    	
				    	var empRlDt= xmlDOM.getElementsByTagName('releaseDt');
				    	 
				    	 for ( var i = 0 ; i < empName.length ; i++ ){
				    	   
				    	 		 var xmlfilepath = xmlFilePath[i].childNodes[0].text;
				    	 			
				    	 			var serialno = empSerialNo[i].childNodes[0].text; 
				    	 			var userid = empUserId[i].childNodes[0].text;
				    	 	 
				    	 			var name = empName[i].childNodes[0].text;
				    	 		 
				    	 			var loc  = empLoc[i].childNodes[0].text;
				    	 		 
				    	 			
				    	 		 
				    	 			var datej = empDateJ[i].childNodes[0].text;
				    	 		 
				    	 			
				    	 			
				    	 			var dater = empDateR[i].childNodes[0].text;
				    	 		 
				    	 			var emptranloc = empTranLoc[i].childNodes[0].text;
		 
				         			var emptrantype = empTranType[i].childNodes[0].text;
				    	 			 
				    	 			var emprelDt=empRlDt[i].childNodes[0].text;
				    	 			 
				    	 				var displayFieldA  = new Array(name,loc,datej,dater,emptranloc,emptrantype,emprelDt);

									addDBDataInTableForTrs('ApprovedWithRelDt','ReltrnsPunch',displayFieldA,xmlfilepath,'editRecord','delRecord','',serialno);
					   		         
					   		        deleteAllCheckedUserId();
					   		}			   	
			
			                
				  }			
	     						
			}	
				  
	 
	  for(var b=0;b<emparray.length;b++){
	  
			emparray.splice(b,1);
			empcount--;
			
      }
       			
}

function addDBDataInTableForTrs(tableId,hiddenField,dispFieldA,
						xmlFilePath,editMethodName,deleteMethodName,viewMethodName,userId)
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
		
		
		var trow=document.getElementById(tableId).insertRow();
		trow.id = 'row' + hiddenField + counter;
	
		trow.insertCell(0).innerHTML = "<INPUT type='hidden' name='" + hiddenField + "' id='" + hiddenField + counter + "' value='" +xmlFilePath  + "_N' />";
		trow.cells[0].style.display = 'none';
		var len = dispFieldA.length;
		var increment=0;
		for(increment = 0; increment < len; increment++) 
		{
			trow.insertCell(increment+1).innerHTML = dispFieldA[increment];	
		}
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
		
		trow.insertCell(increment+2).innerHTML = userId;	
		trow.cells[increment+2].style.display = 'none';
		return trow.id;
	}


function editRecord(rowId){

var answer=confirm('<fmt:message bundle="${transferLables}" key="trnalert.editreq"/>');
if(answer==true)
{
    updateRow1=document.getElementById(rowId);
	updateRow = null;
 	sendAjaxRequestForEdit(rowId,'editDt');
 	}
}


function delRecord(rowId){

 var answer=confirm('<fmt:message bundle="${transferLables}" key="trnalert.delreq"/>');

if(answer==true)
{
    updateRow = null;
    
    trow=document.getElementById(rowId);
    getRecordBack(trow);
    trow.parentNode.deleteRow(trow.rowIndex);
	document.getElementById('updateBtn').style.display='none';
	document.getElementById('releaseBtn').style.display='';
	
	}
}

function getRecordBack(trow){

	
	 
	var row=document.getElementById('ApprovedWithRelDt').rows;
    var trowvalue=row[trow.rowIndex].cells;
    
   // var serialno = trowvalue[0].innerHTML;
	
	 
	var name  =  trowvalue[1].innerHTML;
	var currLocation = trowvalue[2].innerHTML;
	var doj = trowvalue[3].innerHTML;
	var dor = trowvalue[4].innerHTML;
	var transfertoLoc = trowvalue[5].innerHTML;
	var transferType=trowvalue[6].innerHTML;   	
	var serailno = trowvalue[9].innerHTML;
	
	var approvedWithoutRelDtObj = document.getElementById('ApprovedWithoutRelDt').insertRow();
	
	var j=0;			    	 
	approvedWithoutRelDtObj.insertCell(j).innerHTML=serailno;
	approvedWithoutRelDtObj.cells[0].style.display='none';
	approvedWithoutRelDtObj.insertCell(j+1).innerHTML = '<hdiits:checkbox id="'+serailno+'" name="userid" value="'+serailno+'"  onclick="checkclick(this)"/>';
	approvedWithoutRelDtObj.insertCell(j+2).innerHTML=name;
	approvedWithoutRelDtObj.insertCell(j+3).innerHTML=currLocation;
	approvedWithoutRelDtObj.insertCell(j+4).innerHTML=doj;	   					
	approvedWithoutRelDtObj.insertCell(j+5).innerHTML=dor;	   					
	approvedWithoutRelDtObj.insertCell(j+6).innerHTML=transfertoLoc;
	approvedWithoutRelDtObj.insertCell(j+7).innerHTML=transferType;
	j=0;
	
	document.ReleaseApprovedTransfered.releaseDt.value = trowvalue[7].innerHTML;
	



}
function editDt(){
		if (xmlHttp.readyState == 4){
		 				  
				   	var decXML = xmlHttp.responseText;
					var xmlDOM = getDOMFromXML(decXML);			
				 	var dtArray = new Array();
				 	var dt=getXPathValueFromDOM(xmlDOM,'releaseDt');
				 	dtarray= getDateAndTimeFromDateObj(dt);
				 	document.getElementById('releaseDt').value=dtarray[0];
			       	updateUserId = getXPathValueFromDOM(xmlDOM,'userId');
			      	document.getElementById('updateUserId').value = updateUserId;
			      	document.getElementById('serialno').value = getXPathValueFromDOM(xmlDOM,'serialNo');
			      	
                 
          
   } 
   
      
          document.ReleaseApprovedTransfered.releaseBtn.style.display='none';
          document.ReleaseApprovedTransfered.updateBtn.style.display='';
     
     
        }
   
    
       
       
 function updateRecord(){

  addOrUpdateRecord('updateRecordTransfer','updateOnReqDtls', new Array('updateUserId','releaseDt','serialno'));

}

function updateRecordTransfer(){
	  
     		if (xmlHttp.readyState == 4){
 
		 
				    if (xmlHttp.status == 200){
			 
				    		var decXML = xmlHttp.responseText;
							  var displayFieldArray = new Array("releaseDt");
	      				 	 updateDataInTableforTransfer('ReltrnsPunch', displayFieldArray); 
	     					
						document.ReleaseApprovedTransfered.releaseDt.value="";
	                    document.ReleaseApprovedTransfered.updateBtn.style.display='none';
	                    document.ReleaseApprovedTransfered.releaseBtn.style.display='';
	 				}
			}
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
		trow.cells[7].innerHTML = field.value;			
	}
   

function deleteAllCheckedUserId() {
	var a = document.getElementsByName('userid');
	for(i=a.length ;  i>=1 ; i--) {
			if(a[i-1].checked==true) {
			
			
				document.getElementById('ApprovedWithoutRelDt').deleteRow(i);
			}
	}
}	

function insertData(){

var answer=confirm('<fmt:message bundle="${transferLables}" key="trnalert.submitdtls"/>');

if(answer==true)
{
   if(document.getElementById('ApprovedWithRelDt').rows.length >1){
   document.getElementById('submitTrnfer').disabled=true;
       document.ReleaseApprovedTransfered.action="hrms.htm?actionFlag=sendRelDate";
		document.ReleaseApprovedTransfered.submit();
		}

   else{
   		alert('<fmt:message bundle="${transferLables}" key="trnalert.datamsng"/>');
   		return false;
   }
   }
}	

function Closebt()
{	
	method="POST";
	document.ReleaseApprovedTransfered.action="./hrms.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
	
	document.ReleaseApprovedTransfered.submit();
}
   </script>
<hdiits:fieldGroup titleCaptionId="trn.rlsempl" bundle="${transferLables}"  mandatory="true" > 
<hdiits:hidden name="serialno" id="serialno"  default=""/>
	<table width="100%" id="ApprovedWithoutRelDt"
		name="ApprovedWithoutRelDt" border="1" style="display:none">
		<tr class="datatableheader">
			<td width="5%" style="display:none"><hdiits:caption
				captionid="trn.serailno" bundle="${transferLables}" captionLang="single"></hdiits:caption></td>
			<td width="5%"><hdiits:caption
				captionid="trn.serailno" bundle="${transferLables}" /></td>
			<td width="15%"><hdiits:caption captionid="trn.empNm"
				bundle="${transferLables}" ></hdiits:caption></td>
			<td width="15%"><hdiits:caption captionid="trn.loc"
				bundle="${transferLables}" ></hdiits:caption></td>
			<td width="15%"><hdiits:caption captionid="trn.dtJoin"
				bundle="${transferLables}" ></hdiits:caption></td>
			<td width="15%"><hdiits:caption captionid="trn.transferDate"
				bundle="${transferLables}" ></hdiits:caption></td>
			<td width="15%"><hdiits:caption captionid="trn.locto"
				bundle="${transferLables}" ></hdiits:caption></td>
			<td width="15%"><hdiits:caption captionid="trn.trnType"
				bundle="${transferLables}" ></hdiits:caption></td>
			<!--<td width="5%"><hdiits:caption captionid="trn.action"
				bundle="${transferLables}"></hdiits:caption></td>-->
		</tr>


	</table>

 <table width="100%" align="center" 	style="margin: 5px 0px;  font-family: arial; color: #333333;border: solid 1px #6B2700; width: 50%; clear: center;" id="Norecord">
	<tr>
		<td align="center">
		<hdiits:caption captionid="trn.norecord" bundle="${transferLables}" captionLang="single"/>
		</td>
	</tr>
		

</table> 
	<table style="display:none" id="tblrelbtn">
		<tr>
			<td><hdiits:caption captionid="trn.releaseDt"
				bundle="${transferLables}" /> <hdiits:dateTime name="releaseDt"
				mandatory="true" captionid="trn.releaseDt"
				bundle="${transferLables}" maxvalue="31/12/2099"/></td>
			<td><hdiits:button name="releaseBtn" type="button"
				id="releaseBtn" bundle="${transferLables}"
				captionid="trn.releaseBtn" onclick="getReleaseEmpLst()" /> <hdiits:button
				name="updateBtn" type="button" style="display:none" id="updateBtn"
				bundle="${transferLables}" captionid="trn.update"
				onclick="updateRecord()" /> <input type="hidden" id="updateUserId"
				name="updateUserId"></td>

		</tr>

	</table>


	<table width="100%" id="ApprovedWithRelDt" name="ApprovedWithRelDt"
		border="1" style="display:none">
		<tr class="datatableheader">
			<td width="5%" style="display:none"><hdiits:caption
				captionid="trn.serailno" bundle="${transferLables}" captionLang="single"></hdiits:caption></td>
			
			<td width="15%"><hdiits:caption captionid="trn.empNm"
				bundle="${transferLables}" ></hdiits:caption></td>
			<td width="15%"><hdiits:caption captionid="trn.loc"
				bundle="${transferLables}" ></hdiits:caption></td>
			<td width="15%"><hdiits:caption captionid="trn.dtJoin"
				bundle="${transferLables}" ></hdiits:caption></td>
			<td width="15%"><hdiits:caption captionid="trn.transferDate"
				bundle="${transferLables}" ></hdiits:caption></td>
			<td width="15%"><hdiits:caption captionid="trn.locto"
				bundle="${transferLables}" ></hdiits:caption></td>
			<td width="15%"><hdiits:caption captionid="trn.trnType"
				bundle="${transferLables}" ></hdiits:caption></td>
			<td width="10%"><hdiits:caption captionid="trn.releaseDt"
				bundle="${transferLables}" ></hdiits:caption></td>
			<td width="5%"><hdiits:caption captionid="trn.action"
				bundle="${transferLables}" ></hdiits:caption></td>
				<td width="5%" style="display:none" ></td>
		</tr>


	</table>
</hdiits:fieldGroup>
<br><br><br><br><br>

	<table width="100%" align="center" id="tblSubmit" style="display:none">
		<tr >
		<td width="45%"></td>
		<td ><hdiits:button name="submitTrnfer" type="button"
			captionid="trn.subTrn" bundle="${transferLables}"
			onclick="insertData();" id="submitTrnfer"></hdiits:button>
			</td>
			<td><hdiits:button name="Closebutton"
				id="Closebutton" type="button" captionid="trn.Close"
				bundle="${transferLables}" onclick="Closebt()" /></td>
				<td width="45%"></td>
			</tr>

	</table>
	<c:if test="${not empty TransferVO}">
		<c:forEach items="${TransferVO}" var="TransferVO" varStatus="x">


			<c:set var="userId" value="${TransferVO.userId}" />
			<c:set var="serialNo" value="${TransferVO.serialNo}" />
			<c:set var="name" value="${TransferVO.empfName}" />
			<c:set var="currLocation" value="${TransferVO.currentLocation}" />
			<fmt:formatDate var="doj" value="${TransferVO.datej}"
				pattern="dd/MM/yyyy" />
			<fmt:formatDate var="transferDate" value="${TransferVO.transferDate}"
				pattern="dd/MM/yyyy" />
			<c:set var="transfertoLoc" value="${TransferVO.locName}" />
			<c:set var="transferType" value="${TransferVO.transTypelookupName}" />

			<script type="text/javascript">
   	
				   var j=0;
				    	
				    var trow= document.getElementById('ApprovedWithoutRelDt').insertRow();
			        trow.insertCell(j).innerHTML='${serialNo}';
		            trow.cells[0].style.display = 'none';
		
	   	            trow.insertCell(j+1).innerHTML = '<hdiits:checkbox id="'+${serialNo}+'" name="userid" value="'+${serialNo}+'"  onclick="checkclick(this)"/>';
				    trow.insertCell(j+2).innerHTML='${name}';
	   				trow.insertCell(j+3).innerHTML='${currLocation}';
				    trow.insertCell(j+4).innerHTML='${doj}';	   					
					trow.insertCell(j+5).innerHTML='${transferDate}';	   					
				    trow.insertCell(j+6).innerHTML='${transfertoLoc}';
	   													
				    trow.insertCell(j+7).innerHTML='${transferType}';
	   			    j=0;
	   				document.getElementById('Norecord').style.display='none';
	   				document.getElementById('tblrelbtn').style.display='';
	   				document.getElementById('ApprovedWithoutRelDt').style.display='';
	   				document.getElementById('tblSubmit').style.display='';

          </script>
	</c:forEach>

</c:if>



	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />



</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

