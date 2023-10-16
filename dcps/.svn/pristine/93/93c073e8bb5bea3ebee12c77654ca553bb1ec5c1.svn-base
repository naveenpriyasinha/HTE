<%
try {
%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="//WEB-INF/jsp/core/include.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.hr.sheetremarks.SheetRemarks_AlertMessages" var="commonLables1" scope="request"/>

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
<fmt:setBundle basename="resources.hr.sheetremarks.SheetRemarks" var="commonLables" scope="request"/>

var counter=new Number();
var recordCount=new Number();
var editDBFlag=new Number();
var changeFlag=new Number();

function validation()
{

	
		var a=document.getElementById("GoalEn");
		var b=document.getElementById("GoalGu");
		if(a.value=="")
		{
		alert('<fmt:message  bundle="${commonLables}" key="HR.ESS.Mandatory"/>');
		return;
		}
		else if(b.value=="")
		{
		alert('<fmt:message  bundle="${commonLables}" key="HR.ESS.Mandatory"/>');
			return;
		}
		else
		{
			addOrUpdateRecord('addRecord', 'SheetRemarksAddMultiplePoints', new Array('GoalEn', 'GoalGu','code'));
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
			alert('<fmt:message  bundle="${commonLables}" key="HR.ESS.AddPoint"/>');
			return;
	}
}

function submit1()
{
	document.sheetremarksPointMst.method="POST";
	document.sheetremarksPointMst.action="./hrms.htm?actionFlag=sheetremarksPointMstSubmit";	
	showProgressbar('Submitting Request...<br>Please wait...');
	document.sheetremarksPointMst.submit();	
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
	document.getElementById('submit').style.display=''; 
	if (xmlHttp.readyState == 4) 
	{ 	
		var displayFieldArray = new Array('GoalEn', 'GoalGu');
			//alert("document.getElementById('code').value::::"+document.getElementById('code').value);
			if(editDBFlag==-1)
			{
		//alert("in update");
		
				var a=document.getElementById("GoalEn");
				var b=document.getElementById("GoalGu");
				if(a.value=="")
				{
				alert('<fmt:message  bundle="${commonLables}" key="HR.ESS.Mandatory"/>');
				return;
				}
				else if(b.value=="")
				{
				alert('<fmt:message  bundle="${commonLables}" key="HR.ESS.Mandatory"/>');
				return;
				}
				else
				{
				updateDataInTable('encXML', displayFieldArray);		
				}
			}
			else if(editDBFlag==1)
			{
			//alert("DB update");
			var a=document.getElementById("GoalEn");
				var b=document.getElementById("GoalGu");
				if(a.value=="")
				{
				alert('<fmt:message  bundle="${commonLables}" key="HR.ESS.Mandatory"/>');
				return;
				}
				else if(b.value=="")
				{
				alert('<fmt:message  bundle="${commonLables}" key="HR.ESS.Mandatory"/>');
				return;
				}
				else
				{
			
				updateDataInTable('addedPunch', displayFieldArray);		
				}
			}
		
		resetData();
		editDBFlag=0;
		document.getElementById('btnAdd').style.display='';
		document.getElementById('btnUpdate').style.display='none'; 
		document.getElementById('reset').style.display='none';
		   	  }
	}
	
function resetData() {	
		document.getElementById('GoalGu').value = '';
		document.getElementById('GoalEn').value = '';
   		
   	}		

function editRecord(rowId) 
{	
	editDBFlag=-1;
	document.getElementById('submit').style.display='none'; 
	sendAjaxRequestForEdit(rowId,'populateForm'); 
		    
	   	
}	


function populateForm() 
{
	if (xmlHttp.readyState == 4) 
	{ 
		if (xmlHttp.status == 200) 
		{	
			//alert(xmlHttp.responseText);
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
			document.getElementById('reset').style.display='';  	    		   		    
		}
	}
}


function editDBRecord(rowId) 
{	
		setFocusSelection('GoalEn');
		//alert("inn editDBRecord");
		editDBFlag=1;
		changeFlag=1;
		document.getElementById('submit').style.display='none'; 
	sendAjaxRequestForEdit(rowId,'populateFormWithDBData');    	
}	


function populateFormWithDBData() 
{
	if (xmlHttp.readyState == 4) 
	{ 
		if (xmlHttp.status == 200) 
		{	
		//alert("inn populateFormWithDBData");
			//alert(xmlHttp.responseText);
		  	var decXML = xmlHttp.responseText;
			var xmlDOM = getDOMFromXML(decXML,'addedPunch');
			
			var goalGu = getXPathValueFromDOM(xmlDOM,'goalGu'); 
			var goalEn = getXPathValueFromDOM(xmlDOM,'goalEn');
			var goalCode = getXPathValueFromDOM(xmlDOM,'goalCode');
			//alert("goalCode"+goalCode);
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
			document.getElementById('reset').style.display=''; 	    		   		    
		}
	}
}

function deleteDBRec(rowId)
{
		changeFlag=1;
		deleteDBRecord(rowId);
}

function datareset()
{


	document.sheetremarksPointMst.method="POST";
	document.sheetremarksPointMst.action="./hrms.htm?actionFlag=MasterScreenSheetRemarks";	
	showProgressbar('Submitting Request...<br>Please wait...');
	document.sheetremarksPointMst.submit();	
}


function submit4()
{
	//alert("inside submit function");
	document.sheetremarksPointMst.method="POST";
	document.sheetremarksPointMst.action="hdiits.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome&elementId=302322";
	showProgressbar('Opening Home Page...<br>Please wait...');
	document.sheetremarksPointMst.submit();
}
	
function specialChar(txtarea)
{
	
	var len,str,str1,i;
	len=txtarea.value.length;
	str=txtarea.value;
	
	//add or remove characters into this string to be checked 
	str1="!@#$%^&*()<>?/~`-=+{}[]|\:;',."
	for(i=0;i<len;i++)
	{
		
		if((str1.indexOf(str.charAt(i)))!=-1)
		{
		
		alert('<fmt:message  bundle="${commonLables}" key="HR.ESS.SpecialChar"/>');
			
		//alert("Special characters are not allowed");
			//alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.SpecialCharsNotAllowed"/>');
			setFocusSelection(txtarea.name);
			txtarea.focus();
			return;
		}
		
	}

	return;
}

</script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	


<c:set var="pointList" value="${resValue.masterpointList}"></c:set>
<c:set var="sizeOfList" value="${resValue.masterpointListsize}"></c:set>


<c:set var="xmlFilePathNameForMulAdd" value="${resValue.xmlList}"/>
<c:set var="customVoList" value="${resValue.customVoList}"/>


<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected">
			<a href="#" rel="tcontent1">
				<b>
					<hdiits:caption captionid="HR.ESS.Sheet" bundle="${commonLables}" />
				</b>
			</a>
		</li>
	</ul>
</div>





<hdiits:form name="sheetremarksPointMst"  validate="true"  encType="multipart/form-data" >	 
	 		
	<div class="halftabcontentstyle">
	 <div id="tcontent1" class="tabcontent" tabno="0">
		<hdiits:hidden name="code" id="code"/>
	
<hdiits:fieldGroup bundle="${commonLables}"  mandatory="true" expandable="true" id="SetPointsGrp" titleCaptionId="HR.ESS.SetPoints">
				
			<table id="pointTable" align="center">
				<tr>
					<td>
						<hdiits:caption captionid="HR.ESS.GoalEn" bundle="${commonLables}" />
					</td>
					<td>
					<hdiits:textarea mandatory="true" rows="3" cols="50"  
                                   			 name="GoalEn" tabindex="7" id="GoalEn" 
                                     		  captionid="HR.ESS.GoalEn" 
                                     		 bundle="${commonLables}" maxlength="4000" onblur="specialChar(this);"
                                   			/>
                                   			
                                   			
					</td>
					<td>
						<hdiits:caption captionid="HR.ESS.GoalGu" bundle="${commonLables}" />
					</td>
					<td>
					<hdiits:textarea mandatory="true" rows="3" cols="50"  
                                   			 name="GoalGu" tabindex="7" id="GoalGu" 
                                     		  captionid="HR.ESS.GoalGu" 
                                     		 bundle="${commonLables}" maxlength="4000" onblur="specialChar(this);"
                                   			/>
                                   			
                                   			
					</td>
				</tr>
			</table>

		<table align="center">
			<tr>
				<td>
					<hdiits:button type="button" name="btnAdd" value="Add" 
					onclick="javascript:validation();"/>
				</td>
				<td>
					<hdiits:button type="button" name="btnUpdate" value="Update" style="display:none"
					onclick="javascript:addOrUpdateRecord('updateRecord', 'SheetRemarksAddMultiplePoints', new Array('GoalEn','code', 'GoalGu'))"/>
				</td>
				
				<td>
					<hdiits:button type="button" name="reset" value="Reset" style="display:none" onclick="javascript:datareset();"/>
		
				</td>
			</tr>
			
		</table>
</hdiits:fieldGroup>	
			<hdiits:hidden name="count" id="counter"/>
			
				
<hdiits:fieldGroup bundle="${commonLables}"  mandatory="false" expandable="true" id="PointsDetailsGrp" titleCaptionId="HR.ESS.PointTable">
		
				<table id="txnAdd" style="display:none" border="1" align="center" width="80%"> 
					<tr bgcolor="#C9DFFF">
    					<td class="fieldLabel" width="5%">
    					
    						<b><hdiits:caption captionid="HR.ESS.PointEnglish" bundle="${commonLables}" id="PointEnglish" /></b>
		
						</td>
    					<td class="fieldLabel" width="15%">
    						<b><hdiits:caption captionid="HR.ESS.PointGujarati" bundle="${commonLables}" id="PointGujarati" /></b>
						</td>
						<td class="fieldLabel" width="10%" >
							<b><hdiits:caption captionid="HR.ESS.EditDelete" bundle="${commonLables}" /></b>
						</td>  
    				</tr>
				</table>  
				<br>
				<br>
</hdiits:fieldGroup>               	
		<table id="submit" align="center">
		<tr colspan="2">
							<td>
								<hdiits:button captionid="HR.ESS.Submit" bundle="${commonLables}" name="AcceptChange" type="button"   onclick="checkRecordCount();"/>
		
							</td>
							<td>
								<hdiits:button captionid="HR.ESS.Close" bundle="${commonLables}" name="SetDefault" type="button" onclick="submit4();"/>
		
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
		</div>
		</div>	
		
	<hdiits:validate controlNames="test" locale='<%=(String)session.getAttribute("locale")%>'/>	
</hdiits:form>

<script type="text/javascript">
		initializetabcontent("maintab")
</script>
	<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
	