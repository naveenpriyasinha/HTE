
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
<c:set var="locId" value="${resultValue.locId}"></c:set>
<c:set var="memberdtls" value="${resultValue.memberdtls}"></c:set>
<c:set var="deptName" value="${resultValue.deptName}"></c:set>
<c:set var="FileID" value="${resultValue.FileID}"></c:set>

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
	
	sendAjaxRequestForEditAttachment(rowId, 'populateForm','sub_item_att_txt',rowNum);
//resetData();
	document.getElementById('update').style.display='';
	document.getElementById('add').style.display='none';
}
function resetData(rowNum)
 {		
 	
	document.getElementById('sub_item_que_text').value ='';
	document.getElementById('sub_item_ans_text').value ='';
	
	removeRowFromTablesub_item_att_txt(rowNum,'LAQForm'); 	
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
			//document.getElementById('sub_item_att_txt').value=getXPathValueFromDOM(xmlDOM, 'rowNum');    		    
		}
	}
}

function updateRecord() 
{	
	
	addOrUpdateRecord('UpdateRecord', 'addXmlLaqAllicationActionFlag', new Array('sub_item_que_text','sub_item_ans_text'));
}
function UpdateRecord() 
			{		
				 if (xmlHttp.readyState == 4) 
				 { 				
				 	if (xmlHttp.status == 200) 
					{
						
						var displayFieldArray = new Array("sub_item_que_text","sub_item_ans_text");
						//updateDataInTable('encXML', displayFieldArray);
						var rowNum;
						rowNum = updateDataInTableAttachment('xmlTable','encXML', displayFieldArray);
						
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
function validateUpdate(){
	
 	var officeTypeArray = new Array('sub_item_que_text','sub_item_ans_text');
	var statusOfficeTypeValidation =  validateSpecificFormFields(officeTypeArray);
	
	
	if(statusOfficeTypeValidation)
	{
	updateRecord();
	}
	
	}
function validateSubmit()
{
	var officeTypeArray = new Array('LetNo','Letterdate','SubjectArea','QueRaiscombo','Laqtyoecombo','Questionotext','Suppqueradbut','QuestionTextArea','Duesendingreplydte');
	var statusOfficeTypeValidation =  validateSpecificFormFields(officeTypeArray);
	
	document.getElementById('FlagForCheck').value="SaveData";
	
	
	if(statusOfficeTypeValidation)
	{
		var LAQForm="LAQForm";
		if(counter!=0)
		{
			window.document.forms[LAQForm].submit();
		}
		else
		{	
			alert("<fmt:message key="SubItem" bundle="${Lables}"/>")
		}
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

<hdiits:form name="LAQForm" validate="true" method="post" action="hrms.htm?actionFlag=legislatveassmble" encType="multipart/form-data"> 

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
       		<hdiits:textarea name="SubjectArea" captionid="Subject" cols="25" rows="3" mandatory="true" validation="txt.isrequired" onblur="chkSpChars(this)"/>
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
			<hdiits:select name="QueRaiscombo" size="1" mandatory="true" validation="sel.isrequired" caption="Question Raised By">
      			<hdiits:option value=""><fmt:message key="Select"bundle="${Lables}"/></hdiits:option>
	  			<c:forEach var="memberdtls" items="${resultValue.memberdtls}">
				<hdiits:option value="${memberdtls.memberId}">${memberdtls.mlaName}</hdiits:option>		
				</c:forEach>
     		</hdiits:select>
		</td>  

		<td><b><fmt:message key="Constituency"bundle="${Lables}"/></b></td>
		<td>
     		<hdiits:select name="ConstituencyFirstCombo" size="1">
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
     		<hdiits:select name="Laqtyoecombo" size="1" mandatory="true" validation="sel.isrequired" caption="LAQ Type">
     			<hdiits:option value="0"><fmt:message key="Select"bundle="${Lables}"/></hdiits:option>
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
     		<hdiits:select name="Constituencysecondcombo" size="1">
     			<hdiits:option value=""><fmt:message key="Select"bundle="${Lables}"/></hdiits:option>
				<c:forEach var="memberdtls" items="${resultValue.memberdtls}">
				<hdiits:option value="${memberdtls.memberId}">${memberdtls.constituencyName}</hdiits:option>		
				</c:forEach>
    		</hdiits:select>
		</td>
	</tr>		
</table>

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
		<td ><b><fmt:message key="Pre_Que_NO"bundle="${Lables}"/></b></td>
		<td >
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
				<!-- (Example-Scan and Attach Medical Certificate In Case Any) -->
				<!-- For attachment : Start-->	
				<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
					<jsp:param name="attachmentName" value="LaqAttachment" />
					<jsp:param name="formName" value="LAQForm" />
					<jsp:param name="attachmentTitle" value="Answer Attachment" />
					<jsp:param name="attachmentType" value="Biometric" />
					<jsp:param name="multiple" value="N" />                
				</jsp:include>
				<!-- For attachment : End-->
			</td>
		</tr>
</table>
<br>

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
					<!-- (Example-Scan and Attach Medical Certificate In Case Any) -->
					<!-- For attachment : Start-->	
					<td>
						<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
						<jsp:param name="attachmentName" value="sub_item_att_txt" />
						<jsp:param name="formName" value="LAQForm" />
						<jsp:param name="attachmentType" value="Biometric" />
						<jsp:param name="attachmentTitle" value="Sub Item Attachment" />
						<jsp:param name="multiple" value="Y" />
						<jsp:param name="rowNumber" value="1" />
						            
						</jsp:include>
					<!-- For attachment : End-->
					</td>
			</tr>
</table>

<center>
	<hdiits:button type="button" name="add" value="Add" captionid="Add"bundle="${Lables}"onclick="validateAdd()"/>

	<hdiits:button type="button" name="update" value="Update" captionid="Update"bundle="${Lables}"onclick="validateUpdate()" style="display:none"/>
</center>	


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




</div> 
</div>
<script type="text/javascript">
initializetabcontent("maintab")
</script>

<input type="text" name="h1" id="h1" value="${FileID}" style="display:none"/>
<hdiits:hidden name="FlagForCheck" id="FlagForCheck"/>
<hdiits:hidden name="h2" id="h2"/>

<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>"'controlNames="LetNo,Letterdate,SubjectArea,QueRaiscombo,Laqtyoecombo,Questionotext,Suppqueradbut,QuestionTextArea,sub_item_que_text,sub_item_ans_text,Duesendingreplydte" />

</hdiits:form>
</html>
<%
} catch (Exception e) {
e.printStackTrace();
}
%>

