<%
try {
%>
<%@ include file="//WEB-INF/jsp/core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.hr.qtr.qtr_AlertMessages" var="commonLables1" scope="request"/>

<script type="text/javascript"   
	src="common/script/tagLibValidation.js">
</script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript"  
    src="script/common/commonfunctions.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>

<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>

<script type="text/javascript"  
         src="<c:url value="/script/common/base64.js"/>">
</script>

<script type="text/javascript">


var counter=new Number();
var recordCount=new Number();
var editDBFlag=new Number();
var changeFlag=new Number();

function validation(action)
{

	
		var a=document.getElementById("GoalEn");
		var b=document.getElementById("GoalGu");
		if(a.value=="")
		{
		alert('<fmt:message  bundle="${commonLables1}" key="HR.QTR.Mandatory"/>');
		setFocusSelection('GoalEn');
		a.focus();
		return;
		}
		else if(b.value=="")
		{
		alert('<fmt:message  bundle="${commonLables1}" key="HR.QTR.Mandatory"/>');
			setFocusSelection('GoalGu');
			b.focus();
			return;
		}
		else
		{
			addOrUpdateRecord(action, 'ACRAddMultipleFields', new Array('GoalEn', 'GoalGu','code'));
		}
	

}

function chkSpChars(control)
			{
				var iChars = "#^+[]\\\;/|\:<>";
				for (var i = 0; i < control.value.length; i++)
  					{
  						if (iChars.indexOf(control.value.charAt(i)) != -1) 
  						{
  							alert("<fmt:message key="PS.SPECIALCHARS" bundle="${commonLables1}"/>");
							setFocusSelection(control.name);
  							control.focus();
  							return false;
  						}
  					}
			}


function checkRecordCount()
{
	if(recordCount>0 || changeFlag==1)
	{
		submit1();
	}
	else
	{
			alert('<fmt:message  bundle="${commonLables1}" key="HR.QTR.AddPoint"/>');
			return;
	}
}

function submit1()
{
	document.QtrMstScr.method="POST";
	document.QtrMstScr.action="./hrms.htm?actionFlag=QtrFieldMstSubmit";	
	showProgressbar('Submitting Request...<br>Please wait...');
	document.QtrMstScr.submit();	
}
function addRecord() {
	  if (xmlHttp.readyState == 4)
	  { 		
		var displayFieldArray = new Array('GoalEn', 'GoalGu');
		addDataInTable('txnAdd', 'encXML', displayFieldArray,'editRecord','deleteRec','');	
		recordCount=recordCount+1;			
   	    resetData();   			
	   }	
}


function deleteRec(rowId)
{
	recordCount=recordCount-1;
	deleteRecord(rowId);
}
function updateRecord() 
{

	if (xmlHttp.readyState == 4) 
	{ 	
		var displayFieldArray = new Array('GoalEn', 'GoalGu');
		//	alert("document.getElementById('code').value::::"+document.getElementById('code').value);
		if(editDBFlag==-1)
		{
			//alert("in update");
				updateDataInTable('encXML', displayFieldArray);		
		
			}
			else if(editDBFlag==1)
			{
			//	alert("DB update");
				updateDataInTable('addedPunch', displayFieldArray);		
		
			}
			
			resetData();
			editDBFlag=0;
			document.getElementById('btnAdd').style.display='';
			document.getElementById('btnUpdate').style.display='none'; 
			objSubmit =eval("document.getElementById('submitBut')");
			objSubmit.disabled=false;	  
			objClose =eval("document.getElementById('closeBut')");
			objClose.disabled=false;    	 
	    }
	}
	
function resetData() {	
		document.getElementById('GoalGu').value = '';
		document.getElementById('GoalEn').value = '';
   		
   	}		

function editRecord(rowId) 
{	
	editDBFlag=-1;
	sendAjaxRequestForEdit(rowId,'populateForm');    	
}	


function populateForm() 
{
	if (xmlHttp.readyState == 4) 
	{ 
		if (xmlHttp.status == 200) 
		{	
		//	alert(xmlHttp.responseText);
		  	var decXML = xmlHttp.responseText;
			var xmlDOM = getDOMFromXML(decXML,'encXML');
			
			var goalGu = getXPathValueFromDOM(xmlDOM,'goalGu'); 
			var goalEn = getXPathValueFromDOM(xmlDOM,'goalEn');
			if(goalGu != null)
			{				  
				  document.getElementById('GoalGu').value = goalGu;				  
		  	}
		  	if(goalEn != null)
			{				  
				  document.getElementById('Goalen').value = goalEn;				  
		  	}		    
			document.getElementById('btnAdd').style.display='none'; 	    
			document.getElementById('btnUpdate').style.display='';   	    		   		    
		}
	}
}


function editDBRecord(rowId) 
{	
		setFocusSelection('GoalEn');
		editDBFlag=1;
		changeFlag=1;
	sendAjaxRequestForEdit(rowId,'populateFormWithDBData');    	
}	


function populateFormWithDBData() 
{
	if (xmlHttp.readyState == 4) 
	{ 
		if (xmlHttp.status == 200) 
		{	
	//	alert("inn populateFormWithDBData");
	//		alert(xmlHttp.responseText);
		  	var decXML = xmlHttp.responseText;
			var xmlDOM = getDOMFromXML(decXML,'addedPunch');
			
			var goalGu = getXPathValueFromDOM(xmlDOM,'goalGu'); 
			var goalEn = getXPathValueFromDOM(xmlDOM,'goalEn');
			var goalCode = getXPathValueFromDOM(xmlDOM,'goalCode');
	//		alert("goalCode"+goalCode);
			if(goalGu != null)
			{				  
				  document.getElementById('GoalGu').value = goalGu;				  
		  	}
		  	if(goalEn != null)
			{				  
				  document.getElementById('Goalen').value = goalEn;				  
		  	}	
		  	if(goalCode != null)
			{				  
				  document.getElementById('code').value = goalCode;				  
		  	}	    
		  	
			document.getElementById('btnAdd').style.display='none'; 	    
			document.getElementById('btnUpdate').style.display='';   
						
			objSubmit =eval("document.getElementById('submitBut')");
			objSubmit.disabled=true;	 
			objClose =eval("document.getElementById('closeBut')");
			objClose.disabled=true;    		   		    
		}
	}
}

function deleteDBRec(rowId)
{
		changeFlag=1;
		deleteDBRecord(rowId);
}



function submit4()
{
	//alert("inside submit function");
	document.QtrMstScr.method="POST";
	document.QtrMstScr.action="hdiits.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome&elementId=302322";
	showProgressbar('Opening Home Page...<br>Please wait...');
	document.QtrMstScr.submit();
}
	

</script>
<fmt:setBundle basename="resources.hr.qtr.qtr" var="commonLables" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="pointList" value="${resValue.masterFieldList}"></c:set>
<c:set var="sizeOfList" value="${resValue.masterFieldListsize}"></c:set>
<c:set var="xmlFilePathNameForMulAdd" value="${resValue.xmlList}"/>
<c:set var="customVoList" value="${resValue.customVoList}"/>

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption captionid="HR.QTR.QTR" bundle="${commonLables}" /> </b></a></li>
	</ul>
</div>


<hdiits:form name="QtrMstScr"  validate="true"  encType="multipart/form-data" >	 
<div class="halftabcontentstyle">
	 <div id="tcontent1" class="tabcontent" tabno="0"> 	
		<hdiits:hidden name="code" id="code"/>

	<hdiits:fieldGroup bundle="${commonLables}"  expandable="true" mandatory="true" id="SetPointsGrp" titleCaptionId="HR.QTR.SetPoints">
				
			<table id="pointTable" align="center">
				<tr>
					<td>
						<b><hdiits:caption captionid="HR.QTR.GoalEn" bundle="${commonLables}" /></b>
					</td>
					<td>
					<hdiits:textarea mandatory="true" rows="3" cols="50"  
                                   			 name="GoalEn" tabindex="1" id="GoalEn" 
                                     		  captionid="HR.QTR.GoalEn" 
                                     		 bundle="${commonLables}" maxlength="4000" onblur="chkSpChars(this)"
                                   			/>
                                   			
                                   			
					</td>
					<td>
						<b><hdiits:caption captionid="HR.QTR.GoalGu" bundle="${commonLables}" /></b>
					</td>
					<td>
					<hdiits:textarea mandatory="true" rows="3" cols="50"  
                                   			 name="GoalGu" tabindex="2" id="GoalGu" 
                                     		  captionid="HR.QTR.GoalGu" 
                                     		 bundle="${commonLables}" maxlength="4000" onblur="chkSpChars(this)"
                                   			/>
                                   			
                                   			
					</td>
				</tr>
			</table>
	

		<table align="center">
			<tr>
				<td>
					<hdiits:button type="button" name="btnAdd" captionid="HR.QTR.ADD" id="btnAdd" bundle="${commonLables}" 
					onclick="javascript:validation('addRecord');" tabindex="3"/>
				</td>
				<td>
					<hdiits:button type="button" name="btnUpdate" captionid="HR.QTR.UPDATE" id="btnUpdate" bundle="${commonLables}" style="display:none"
					onclick="javascript:validation('updateRecord');" tabindex="3"/>
				</td>
			</tr>
			
		</table>
	</hdiits:fieldGroup>
			<hdiits:hidden name="count" id="counter"/>
			
<hdiits:fieldGroup bundle="${commonLables}"  expandable="true" id="PointsGrp" titleCaptionId="HR.QTR.PointTable">			
		
				<table id="txnAdd" style="display:none" border="1" align="center" width="80%"> 
					<tr bgcolor="#C9DFFF">
    					<td class="fieldLabel" width="5%">
    					
    						<b><hdiits:caption captionid="HR.QTR.PointEnglish" bundle="${commonLables}" id="PointEnglish" /></b>
		
						</td>
    					<td class="fieldLabel" width="15%">
    						<b><hdiits:caption captionid="HR.QTR.PointGujarati" bundle="${commonLables}" id="PointGujarati" /></b>
						</td>
						<td class="fieldLabel" width="10%" >
							<b><hdiits:caption captionid="HR.QTR.EditDelete" bundle="${commonLables}" /></b>
						</td>  
    				</tr>
				</table> 
</hdiits:fieldGroup> 
				<br>
				<br>
		
		<table id="submit"  align="center">
		<tr colspan="2">
							<td>
								<hdiits:button captionid="HR.QTR.Submit" id="submitBut" bundle="${commonLables}" name="AcceptChange" type="button"  onclick="checkRecordCount();" tabindex="4"/>
		
							</td>
							<td>
								<hdiits:button captionid="HR.QTR.Close" id="closeBut" bundle="${commonLables}" name="SetDefault" type="button" onclick="submit4();" tabindex="5"/>
		
							</td>
				</tr>
			
		</table>
		
		<c:forEach var="curXMLFileName" items="${xmlFilePathNameForMulAdd}" varStatus="x">
			<c:set var="customVo" value="${customVoList[x.index]}" ></c:set>

			<c:set var="gujarati" value="${customVo.goalGu}"/>

			<c:set var="english" value="${customVo.goalEn}"/>

			
			
			<script type="text/javascript">
				var xmlFileName = '${curXMLFileName}';
				var displayFieldA  = new Array('${english}','${gujarati}');
				
				addDBDataInTable('txnAdd','addedPunch',displayFieldA,xmlFileName,'editDBRecord','deleteDBRec');
			</script>
	
	</c:forEach>			
		<hdiits:validate controlNames="" locale='${locale}' />
	</div>
	</div>	
		
</hdiits:form>

<script type="text/javascript">
		initializetabcontent("maintab")
</script>


	<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
	