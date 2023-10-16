
<%
try
{
%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<script type="text/javascript" src="script/common/address.js"></script>
<script type="text/javascript" src="/script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="script/common/prototype-1.3.1.js"></script>
<script type="text/javascript" src="script/common/ajaxtags-1.1.5.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"  
         src="common/script/commonfunctions.js">
</script>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/gnl/LAQ/LaqAttachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/base64.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>

<c:set var="attachments" value=""/>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="LaqType" value="${resultValue.LaqType}"></c:set>
<c:set var="memberdtls" value="${resultValue.memberdtls}"></c:set>
<c:set var="deptName" value="${resultValue.deptName}"></c:set>
<c:set var="FileID" value="${resultValue.FileID}"></c:set>
<c:set var="SubItemDtl" value="${resultValue.SubItemDtl}"></c:set>
<c:set var="LaqDtl" value="${resultValue.LaqDtl}"></c:set>
<c:set var="LtrDtd" value="${resultValue.LtrDtd}"></c:set>
<c:set var="DueDate" value="${resultValue.DueDate}"></c:set>
<c:set var="RepDate" value="${resultValue.RepDate}"></c:set>
<c:set var="LookupCombo" value="${resultValue.LookupCombo}"></c:set>
<c:set var="QuesRaiseId" value="${resultValue.QuesRaiseId}"></c:set>
<c:set var="QuesRepId" value="${resultValue.QuesRepId}"></c:set>
<c:set var="ConstiRaisId" value="${resultValue.ConstiRaisId}"></c:set>
<c:set var="ConstiRepId" value="${resultValue.ConstiRepId}"></c:set>

<fmt:setBundle basename="resources.gnl.LaqApplication.LaqApplicationLables" var="Lables" scope="request"/> 
<html>
<script language="javascript">


function ClosePage(frm)
{
frm.action="hdiits.htm?actionFlag=getHomePage";
frm.method="POST";
frm.submit();
}

var counter=0;
var SetFlagToCheck=false;
function addData()
{

	addOrUpdateRecord('addRecord', 'addXmlLaqAllicationActionFlag', new Array('sub_item_que_text','sub_item_ans_text','h2'));
	counter++;
}
function addRecord() {
	  if (xmlHttp.readyState == 4)
	  { 	
	  	if (xmlHttp.status == 200) 
	  	{
	  		var encXML=xmlHttp.responseText;
	  		
			var displayFieldArray = new Array("sub_item_que_text","sub_item_ans_text");
			var rowNum = addDataInTableAttachment('xmlTable','encXML', displayFieldArray,'editRecord','deleteRecord');	
   	    	resetData(rowNum);   			
	   	}
	   }	
	}

function editRecord(rowId,rowNum)
{ 
	
	SetFlagToCheck=false;
	sendAjaxRequestForEditAttachment(rowId, 'populateForm','sub_item_att_txtNew',rowNum);
	document.getElementById('update').style.display='';
	document.getElementById('add').style.display='none';
//resetData();
}

function editRecordForDB(rowId,rowNum)
{
SetFlagToCheck=true;

sendAjaxRequestForEditAttachment(rowId, 'populateForm','sub_item_att_txtNew',rowNum);
document.getElementById('update').style.display='';
document.getElementById('add').style.display='none';
}
function resetData(rowNum)
 {		
 	
	document.getElementById('sub_item_que_text').value ='';
	document.getElementById('sub_item_ans_text').value ='';
	removeRowFromTablesub_item_att_txtNew(rowNum,'LAQForm'); 	
 }
function populateForm() {
	if (xmlHttp.readyState == 4)
	 { 	
	 	if (xmlHttp.status == 200) 
	 	{
	  		var decXML = xmlHttp.responseText;	
	  				  	
	  	    var xmlDOM = populateAttachment(decXML,LAQForm);
			
			document.getElementById('sub_item_que_text').value = getXPathValueFromDOM(xmlDOM, 'subItemQueDtls');
			document.getElementById('sub_item_ans_text').value = getXPathValueFromDOM(xmlDOM, 'subItemAnsDtls');
			document.getElementById('h2').value=getXPathValueFromDOM(xmlDOM, 'srNo');
			//document.getElementById('sub_item_att_txt').value=getXPathValueFromDOM(xmlDOM, 'rowNum');    		    
		}
	}
}

function updateRecord() 
{	
	
	addOrUpdateRecord('UpdateRecord', 'addXmlLaqAllicationActionFlag', new Array('sub_item_que_text','sub_item_ans_text','h2'));
	document.getElementById('h2').value="0";
}
function UpdateRecord() 
			{		
				 if (xmlHttp.readyState == 4) 
				 { 				
				 	if (xmlHttp.status == 200) 
					{
						
						var displayFieldArray = new Array("sub_item_que_text","sub_item_ans_text");
					
						var rowNum;
						
						if(SetFlagToCheck==true)
						{
							rowNum = updateDataInTableAttachment('xmlTable','encXML1', displayFieldArray);
						}
						else
						{
							rowNum = updateDataInTableAttachment('xmlTable','encXML', displayFieldArray);
						}
					
						resetData(rowNum);					
					}
				}
				document.getElementById('update').style.display='none';
				document.getElementById('add').style.display='';
			}				   
	   
function submitPage()
	{
		document.LAQForm.action = "hrms.htm?actionFlag=legislatveassmble";
		document.LAQForm.submit();
	}
function validateAdd(){

 	var officeTypeArray = new Array('sub_item_que_text','sub_item_ans_text');
	var statusOfficeTypeValidation =  validateSpecificFormFields(officeTypeArray);
	
	
	if(statusOfficeTypeValidation)
	{
	addData();
	}
	
	}
function validateSubmit()
{
	var officeTypeArray = new Array('LetNo','Letterdate','SubjectArea','QueRaiscombo','Laqtyoecombo','Questionotext','Suppqueradbut','QuestionTextArea','Duesendingreplydte');
	var statusOfficeTypeValidation =  validateSpecificFormFields(officeTypeArray);
	
	
	if(statusOfficeTypeValidation)
	{
		var LAQForm="LAQForm";
		if(counter!=0)
		{
			window.document.forms[LAQForm].submit();
		}
		else
		{	
			alert("<fmt:message key="SubItem" bundle="${Lables}"/>");
		}
	}
} 

function LoadTableData(LaqId)
{
	
	var LAQID=LaqId;
	
	ShowDataInTable(LAQID);
}
function ShowDataInTable(LAQID)
{
	try{   
    			xmlHttp=new XMLHttpRequest();    
	    	}
			catch (e)
			{    // Internet Explorer    
					try{
      					xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");      
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
			var url = "hrms.htm?actionFlag=getsavedData&LaqId="+LAQID;    
			xmlHttp.open("POST", encodeURI(url) , false);			
			xmlHttp.onreadystatechange = processResponse;
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));
			
			
			
}
function processResponse()
{
	if (xmlHttp.readyState == 4)
	 {
		if (xmlHttp.status == 200)
		 {
				var xmlStr = xmlHttp.responseText;
				
			    var XMLDoc=getDOMFromXML(xmlStr);
			    
			     var SubQues=XMLDoc.getElementsByTagName('SubQues');
			   
			   	 var SubAns=XMLDoc.getElementsByTagName('SubAns');
			    
			     var XmlFilePath=XMLDoc.getElementsByTagName('XMLFilePath');
			     
			     var attachID=XMLDoc.getElementsByTagName('attachID');
			    
			     if(SubQues.length>0)
			     {
			     	for(var i=0;i<SubQues.length;i++)
			    	{
			    		
			    		
			    		var	SubQuestn=SubQues[i].childNodes[0].text;
			    		
			    		var	SubAnswrs=SubAns[i].childNodes[0].text;
			    		
			    		var XMLPath=XmlFilePath[i].childNodes[0].text;
			    		
			    		
			    		var AttchmentId=attachID[i].childNodes[0].text;
			    		
			    		var rowNum=addDBDataInTableAttachment('XmlTable', 'encXML1', new Array(SubQuestn,SubAnswrs), XMLPath, AttchmentId,'editRecordForDB', 'DeleteDBRecord', '');
			    		
			 }
		}
	}
}    
}
function DeleteDBRecord(rowId)
{	

	deleteDBRecord(rowId);
}			  
function validateUpdate(){
	
 	var officeTypeArray = new Array('sub_item_que_text','sub_item_ans_text');
	var statusOfficeTypeValidation =  validateSpecificFormFields(officeTypeArray);
	
	
	if(statusOfficeTypeValidation)
	{
	updateRecord();
	}
	
	}

function chkSpChars(control)
			{
				var iChars = "#^+=\\\;|\<>";
				for (var i = 0; i < control.value.length; i++)
  					{
  						if (iChars.indexOf(control.value.charAt(i)) != -1) 
  						{
  							alert("<fmt:message bundle="${Lables}" key="HRMS.SpclChars"/>");
  							control.focus();
  							return false;
  						}
  					}
			}
			
function chkForNosAndSpChars(control)
			{
				var iChars = "#^+=\\\;|\<>\.,";
				for (var i = 0; i < control.value.length; i++)
  					{
  						if (iChars.indexOf(control.value.charAt(i)) != -1) 
  						{
  							alert("<fmt:message bundle="${Lables}" key="HRMS.SpclChars"/>");
  							control.focus();
  							return false;
  						}
  					}
			}

</script>

<hdiits:form name="LAQForm" validate="true" method="post" action="hrms.htm?actionFlag=SaveNewlegislatveassmble" encType="multipart/form-data"> 

	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b>
			<fmt:message key="Leg_Ass_Ans__Que_Det"bundle="${Lables}"/></b></a>
			</li>
		</ul>
	</div>

<div class="tabcontentstyle">

<div id="tcontent1" class="tabcontent" tabno="0"> 

<TABLE class=tabtable>
	<tr bgcolor="#386CB7">
		<td class="fieldLabel"  align=middle  colspan="4">
		<font color="#ffffff">
		<strong><u><fmt:message key="Leg_Ass_Que_Det"bundle="${Lables}"/></u></strong>
		</font>
		</td>
	</tr>
</TABLE>
<table width="100%" align="center">
	<tr>
		<td>
			<b><fmt:message key="Let_No"bundle="${Lables}"/></b>
		</td>
		<td>
        	<hdiits:text name="LetNo" id="LetNo" mandatory="true" size="15" captionid="Let_No" bundle="${Lables}" validation="txt.isrequired"/>
		</td>	
       	<td>
       		<b><fmt:message key="Let_Dated"bundle="${Lables}"/></b></td>
		<td>
       		<hdiits:dateTime name="Letterdate" caption="dateTime" mandatory="true" validation="txt.isrequired" captionid="LetterDate" bundle="${Lables}" maxvalue="31/12/9999"/>
		</td>
	</tr>
	<tr>
	  	<td>
	  		<b><fmt:message key="Subject"bundle="${Lables}"/></b>
	  	</td>
		<td>
       		<hdiits:textarea name="SubjectArea" captionid="HRMS.Subject" bundle="${Lables}" cols="25" rows="3" mandatory="true" validation="txt.isrequired" onblur="chkSpChars(this)"/>
		</td>
       	<td>
       		<b><fmt:message key="Department"bundle="${Lables}"/></b>
       	</td>
      	<td>
      		<b><c:out value="${deptName}"></c:out></b>
      	</td>
	</tr>
	<tr>
		<td>
			<b><fmt:message key="Question_Raised_By"bundle="${Lables}"/></b>
		</td>
		<td>
			<hdiits:select name="QueRaiscombo" size="1" mandatory="true" validation="sel.isrequired" caption="Question Raised By" sort="false">
      			<hdiits:option value=""><fmt:message key="Select"bundle="${Lables}"/></hdiits:option>
	  			<c:forEach var="memberdtls" items="${memberdtls}">
				<hdiits:option value="${memberdtls.memberId}">${memberdtls.mlaName}</hdiits:option>		
				</c:forEach>
     		</hdiits:select>
		</td>  

		<td><b><fmt:message key="Constituency"bundle="${Lables}"/></b></td>
		<td>
     		<hdiits:select name="ConstituencyFirstCombo" size="1" sort="false">
     			<hdiits:option value=""><fmt:message key="Select"bundle="${Lables}"/></hdiits:option>
				<c:forEach var="memberdtls" items="${resultValue.memberdtls}">
				<hdiits:option value="${memberdtls.memberId}">${memberdtls.constituencyName}</hdiits:option>		
				</c:forEach>
    		</hdiits:select>
		</td>  
	</tr>
	<tr>
		<td><b><fmt:message key="LAQ_Type"bundle="${Lables}"/></b></td>
		<td>
     		<hdiits:select name="Laqtyoecombo" size="1" mandatory="true" validation="sel.isrequired" caption="LAQ Type" sort="false">
     			<hdiits:option value=""><fmt:message key="Select"bundle="${Lables}"/></hdiits:option>
				<c:forEach var="LaqType" items="${LaqType}">
				<hdiits:option  value="${LaqType.lookupId}">${LaqType.lookupDesc}</hdiits:option>
				</c:forEach>
    		</hdiits:select>
		</td>  
		<td><b><fmt:message key="Vidhan_Sabha"bundle="${Lables}"/></b></td>
		<td>
     		<hdiits:text name="Vidhansabhatext" size="15"/>
		</td>
	</tr>
	
	<tr>  
		<td><b><fmt:message key="To_Be_Rep_By"bundle="${Lables}"/></b></td>
		<td>
			<hdiits:select  captionid="To_Be_Rep_By" bundle="${Lables}" name="Repcombo" size ="1" sort="false" >
				<hdiits:option value=""><fmt:message key="Select"bundle="${Lables}"/></hdiits:option>
				<c:forEach var="memberdtls" items="${resultValue.memberdtls}">
				<hdiits:option value="${memberdtls.memberId}">${memberdtls.mlaName}</hdiits:option>		
				</c:forEach>
			</hdiits:select>
		</td>
		<td><b><fmt:message key="Constituency"bundle="${Lables}"/></b></td>
		<td>
     		<hdiits:select name="Constituencysecondcombo" size="1" sort="false">
     			<hdiits:option value=""><fmt:message key="Select"bundle="${Lables}"/></hdiits:option>
				<c:forEach var="memberdtls" items="${resultValue.memberdtls}">
				<hdiits:option value="${memberdtls.memberId}">${memberdtls.constituencyName}</hdiits:option>		
				</c:forEach>
    		</hdiits:select>
		</td>
	</tr>		
</table>
<script>
	var LaqType="${LookupCombo.lookupId}";
	
	document.getElementById('Laqtyoecombo').value=LaqType;
</script>
<TABLE class=tabtable width="100%" align="center">
	<tr bgcolor="#386CB7">
		<td class="fieldLabel"  align=middle  colspan="4">
		<font color="#ffffff">
		<strong><u><fmt:message key="Que_Ans_Detail"bundle="${Lables}"/></u></strong>
		</font>
		</td>
	</tr>
</table>

<table width="100%" align="center">
	<tr>
		<td width="30%"><b><fmt:message key="Que_No"bundle="${Lables}"/></b></td>
		<td width="30%">
        	<hdiits:number name="Questionotext" size="15" mandatory="true" caption="Question No" validation="txt.isrequired" onblur="chkForNosAndSpChars(this)"/>
		</td>	
		<td><b><fmt:message key="Pre_Que_NO"bundle="${Lables}"/></b></td>
		<td>
			<hdiits:number name="Prequestionotext" size="15" onblur="chkForNosAndSpChars(this)"/>
		</td>
	</tr>
	<tr>	
		<td><b><fmt:message key="Supplementary_Que"bundle="${Lables}"/></b></td>
		<td><hdiits:radio name="Suppqueradbut" value="Yes" default="true" mandatory="true" caption="Supplementary" captionid="YES" bundle="${Lables}" validation="sel.isradio" />
			<hdiits:radio name="Suppqueradbut" value="No" mandatory="true" caption="Supplementary" captionid="NO"  bundle="${Lables}" validation="sel.isradio" />
		</td>
	</tr>
</table>

<table>
	<tr>
	   <td><b><fmt:message key="Question"bundle="${Lables}"/></b></td>
		<td>
			<hdiits:textarea name="QuestionTextArea" cols="50" rows="3" caption="Question" mandatory="true" validation="txt.isrequired" onblur="chkSpChars(this)"/>
		</td>
	</tr>
	<tr>
		<td><b><fmt:message key="Bri_Ans"bundle="${Lables}"/></b></td>
		<td>
			<hdiits:textarea name="BriefansTextAra" cols="50" rows="3" onblur="chkSpChars(this)"/>
		</td>
	</tr>
	<tr>
		<td><b><fmt:message key="Supp_Inf"bundle="${Lables}"/></b></td>
		<td>
       		<hdiits:textarea name="Supplementryinfo" cols="50" rows="3" onblur="chkSpChars(this)"/>
       	</td>
	</tr>
</table>

<table border='0' width="100%">
		<tr>
			<td>
				<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
					<jsp:param name="attachmentName" value="LaqAttachment" />
					<jsp:param name="formName" value="LAQForm" />
					<jsp:param name="attachmentTitle" value="Answer Attachment" />
					<jsp:param name="attachmentType" value="Biometric" />
					<jsp:param name="multiple" value="N" />    
					<jsp:param name="removeAttachmentFromDB" value="Y" />            
				</jsp:include>
			</td>
		</tr>
</table>
<br>
<script>
document.getElementById('target_uploadLaqAttachment').style.display='none';
document.getElementById('formTable1LaqAttachment').firstChild.firstChild.style.display='';	

</script>
<table class="tabtable">
	<tr bgcolor="#386CB7">
		<td class="fieldLabel"  align=middle colspan="4">
			<font color="#ffffff">
			<u><strong><fmt:message key="Sub__Item_Detail"bundle="${Lables}"/></strong></u>
			</font>
		</td>
	</tr>
</table>
<table width="125%" align="center">
		
<tr>
<td><b><fmt:message key="Sub_Item_Question"bundle="${Lables}"></fmt:message></b></td>
<td>
<hdiits:textarea id="sub_item_que_text" name="sub_item_que_text"cols="50" rows="3"   validation="txt.isrequired" caption="Sub Item Questions" onblur="chkSpChars(this)"/>
</td>
</tr>

<tr>
<td ><b><fmt:message key="Sub_Item_Answer"bundle="${Lables}"></fmt:message></b></td>
<td><hdiits:textarea name="sub_item_ans_text"cols="50" rows="3"  validation="txt.isrequired" caption="Sub Item Answers" onblur="chkSpChars(this)"/></td>
</tr>	
</table>

<table border='0' width="100%">
			<tr>
				<td>
						<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
						<jsp:param name="attachmentName" value="sub_item_att_txtNew" />
						<jsp:param name="formName" value="LAQForm" />
						<jsp:param name="attachmentType" value="Biometric" />
						<jsp:param name="attachmentTitle" value="Sub Item Attachment" />
						<jsp:param name="multiple" value="Y" />
						<jsp:param name="rowNumber" value="1" />
						            
						</jsp:include>
				</td>
			</tr>
</table>

<center>
	<hdiits:button type="button" name="add" value="Add" captionid="Add"bundle="${Lables}"onclick="validateAdd()"/>

	<hdiits:button type="button" name="update" value="Update" captionid="Update"bundle="${Lables}"onclick="validateUpdate()" style="display:none"/>
</center>	

<script>

</script>
<br><br>
<table id="XmlTable" style="display:none" border="1"  width="100%">
<tr>
<td class="fieldLabel" bgcolor="lightblue" width="35%"><b><fmt:message key="SubItemQue" bundle="${Lables}"></fmt:message></b> </td>
<td class="fieldLabel" bgcolor="lightblue" width="35%"><b><fmt:message key="SubItemAns" bundle="${Lables}"></fmt:message></b></td>
<td class="fieldLabel" bgcolor="lightblue" width="15%"><b><fmt:message key="Action" bundle="${Lables}"></fmt:message></b></td>
</tr>
</table>


	




<table class="tabtable">
	<tr bgcolor="#386CB7">
		<td class="fieldLabel"  align=middle  colspan="4">
			<font color="#ffffff">
			<strong><u><fmt:message key="Status_Details"bundle="${Lables}"/></u></strong>
			</font>
		</td>
	</tr>
</table>
<table width="100%" align="center">
	<tr>		
 		<td><b><fmt:message key="Due_Dte_Of_Sending_Rep"bundle="${Lables}"/></b></td>
		<td>
       		<hdiits:dateTime name="Duesendingreplydte" caption="dateTime" mandatory="true" validation="txt.isrequired" captionid="DueDate" bundle="${Lables}" maxvalue="31/12/9999"/>
		</td>
		<td><b><fmt:message key="Dte_Sendting_Rep"bundle="${Lables}"/></b></td>
		<td>
       		<hdiits:dateTime name="Sendingreplydte" caption="dateTime" maxvalue="31/12/9999"/>
		</td>
	</tr>
</table>
<center>
	<hdiits:button type="button" name="Save" value="Save" captionid="Save" bundle="${Lables}" onclick="validateSubmit()"/>
	<hdiits:button name="Close" type="button" value="Close" captionid="HRMS.Close" bundle="${Lables}" onclick="ClosePage(document.LAQForm);"/> 
</center>

<script type="text/javascript">


var letNo="${LaqDtl.lttrNum}";
document.getElementById('LetNo').value=letNo;

var Letterdt="${LtrDtd}";
document.getElementById('Letterdate').value=Letterdt;

var Subjct="${LaqDtl.subjectText}";
document.getElementById('SubjectArea').value=Subjct;

var QustnRaised="${resultValue.QuesRaiseId.memberId}";
document.getElementById('QueRaiscombo').value=QustnRaised;


var Constituency="${ConstiRaisId.memberId}";
document.getElementById('ConstituencyFirstCombo').value=Constituency;

var vidhansabha="${LaqDtl.laqVsabha}";
document.getElementById('Vidhansabhatext').value=vidhansabha;

var RepliedBy="${QuesRepId.memberId}";
document.getElementById('Repcombo').value=RepliedBy;

var repliedVdhnsbh="${ConstiRepId.memberId}";
document.getElementById('Constituencysecondcombo').value=repliedVdhnsbh;

var QuestionNo="${LaqDtl.qustNum}";
document.getElementById('Questionotext').value=QuestionNo;

var RelatedQues="${LaqDtl.previousRelatedNum}";
document.getElementById('Prequestionotext').value=RelatedQues;

var Question="${LaqDtl.qauestionText}";
document.getElementById('QuestionTextArea').value=Question;

var BriefAns="${LaqDtl.answerText}";
document.getElementById('BriefansTextAra').value=BriefAns;

var SuppliInfo="${LaqDtl.suppInfoText}";
document.getElementById('Supplementryinfo').value=SuppliInfo;

var DueDate="${DueDate}";
document.getElementById('Duesendingreplydte').value=DueDate;

var replyDate="${RepDate}";
document.getElementById('Sendingreplydte').value=replyDate;



</script>
<script type="text/javascript">
var LaqId="${LaqDtl.laqId}";

LoadTableData(LaqId);
</script>
<input type="text" name="h1" id="h1" value="${FileID}" style="display:none"/>
<hdiits:hidden name="h2" id="h2"/>
<hdiits:hidden name="FlagForCheck" id="FlagForCheck"/>
<hdiits:hidden name="LaqId" id="LaqId"/>
<script type="text/javascript">

document.getElementById('LaqId').value="${LaqDtl.laqId}";
document.getElementById('FlagForCheck').value="UpdateData";
</script>

</div> 
</div>
<script type="text/javascript">
initializetabcontent("maintab")
</script>



<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>"'controlNames="LetNo,Letterdate,SubjectArea,QueRaiscombo,Laqtyoecombo,Questionotext,Suppqueradbut,QuestionTextArea,sub_item_que_text,sub_item_ans_text,Duesendingreplydte" />

</hdiits:form>
</html>
<%
} catch (Exception e) {
e.printStackTrace();
}
%>

