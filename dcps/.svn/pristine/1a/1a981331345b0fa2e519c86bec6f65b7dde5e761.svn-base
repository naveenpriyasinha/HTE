<%
try {
%>
<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.hr.acr.acr_AlertMessages" var="commonLables1" scope="request"/>

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

function submit1()
{
	document.AcrPointMst.method="POST";
	document.AcrPointMst.action="./hrms.htm?actionFlag=ACRPointMstSubmit";	
	document.AcrPointMst.submit();	
}

function removePoint(pointCode)
{
var row="row"+pointCode;
		deleteRecord(row);


	
try{   
    			xmlHttp=new XMLHttpRequest();    
	    	}
			catch (e)
			{    // Internet Explorer    
					try{
      					xmlHttp=new 
                        ActiveXObject("Msxml2.XMLHTTP");      
      				}
		    		catch (e){
		          		try
        		  		{
                	           	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
        		  		}
				      	catch (e)
				      	{
			           	   	  alert("Your browser does not support AJAX!");        
			            	  return false;        
			      		}
			 		}			 
        	}	     
        	
			var url = "hrms.htm?actionFlag=ACRPointRemoveAjaxFlag&pointCode="+pointCode;    
			xmlHttp.open("POST", encodeURI(url) , true);			
			xmlHttp.onreadystatechange = processResponse;
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));
}
function processResponse()
{
	if (xmlHttp.readyState == 4) 
			{     
				
				//alert('xmlHttp.status is'+xmlHttp.status);
				if (xmlHttp.status == 200) 
				{        			
				alert("Point removed Successfully.");
	           			
	           			
				}
				else 
				{  			
					alert("ERROR");					
				}
			}
			
			
			
}

function validation()
{

	
		var a=document.getElementById("GoalEn");
		var b=document.getElementById("GoalGu");
		if(a.value=="")
		{
		alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.Mandatory"/>');
		return;
		}
		else if(b.value=="")
		{
		alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.Mandatory"/>');
			return;
		}
		else
		{
			addOrUpdateRecord('addRecord', 'ACRAddMultiplePoints', new Array('GoalEn', 'GoalGu','code'));
		}
	

}



function checkRecordCount()
{
	if(document.getElementById('updateButton').style.display=='')
	{
		
		alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.RecordOpenForEdit"/>');
		return;
	}
	if(recordCount>0 || changeFlag==1)
	{
		submit1();
	}
	else
	{
			alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.AddPoint"/>');
			return;
	}
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
			//alert("document.getElementById('code').value::::"+document.getElementById('code').value);
		if(editDBFlag==-1)
		{
		
			updateDataInTable('encXML', displayFieldArray);		
	
		}
		else if(editDBFlag==1)
		{
			
			updateDataInTable('addedPunch', displayFieldArray);		
	
		}
		
		resetData();
		editDBFlag=0;
		document.getElementById('btnAdd').style.display='';
		document.getElementById('btnUpdate').style.display='none';    	  }
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
		}
	}
}


function editDBRecord(rowId) 
{	
		//alert("inn editDBRecord");
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
	
		  	var decXML = xmlHttp.responseText;
			var xmlDOM = getDOMFromXML(decXML,'addedPunch');
			
			var goalGu = getXPathValueFromDOM(xmlDOM,'goalGu'); 
			var goalEn = getXPathValueFromDOM(xmlDOM,'goalEn');
			var goalCode = getXPathValueFromDOM(xmlDOM,'goalCode');
	
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
		}
	}
}

function deleteDBRec(rowId)
{
		changeFlag=1;
		deleteDBRecord(rowId);
}


//method to check data into textarea(it should no tbe some special characters...)

function isValidData(txtarea)
{
	
	var len,str,str1,i;
	len=txtarea.value.length;
	str=txtarea.value;
	
	//add or remove characters into this string to be checked 
	str1='`~!#$%^&*+|';
	for(i=0;i<len;i++)
	{
		
		if((str1.indexOf(str.charAt(i)))!=-1)
		{
			alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.SpecialCharsNotAllowed"/>');
			txtarea.focus();
			return;
		}
		
	}

	return;
}
//function to be called on press of close
function closeButtonHandler()
{
	document.AcrPointMst.method="POST";
	document.AcrPointMst.action="./hrms.htm?actionFlag=getHomePage";	
	document.AcrPointMst.submit();
	
}


//function to reset data
function refreshPage()
{
	document.AcrPointMst.method="POST";
	document.AcrPointMst.action="./hrms.htm?actionFlag=ACRPointGroupSubmit";	
	document.AcrPointMst.submit();	
}

function submit2()
{


	document.AcrPointMst.method="POST";

	document.AcrPointMst.action="./hrms.htm?actionFlag=AcrPointMstScrInitialize";
	
	document.AcrPointMst.submit();
	
}
</script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	


<c:set var="pointList" value="${resValue.pointList}"></c:set>
<c:set var="groupFlag" value="${resValue.groupFlag}"></c:set>
<c:set var="locVO" value="${resValue.locList}"></c:set>
<c:set var="authority1" value="${resValue.authority}"></c:set>
<c:set var="groupId" value="${resValue.groupId}"></c:set>
<c:set var="desgnVO" value="${resValue.desgnList}"></c:set>
<c:set var="sizeOfList" value="${resValue.sizeOfList}"></c:set>



	
<c:set var="xmlFilePathNameForMulAdd" value="${resValue.xmlList}"/>
<c:set var="customVoList" value="${resValue.customVoList}"/>


<fmt:setBundle basename="resources.hr.acr.acr" var="commonLables" scope="request"/>

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption captionid="HR.ACR.ACR" bundle="${commonLables}"/></b></a></li>
	</ul>
</div>
<div >
	 <div id="tcontent1"  tabno="0">




<table class="tabtable">
<tr bgcolor="#386CB7">
						<td class="fieldLabel" width="100%">
						<font class="Label3" align="center" color="white">
						<u>
						<b><hdiits:caption captionid="HR.ACR.GroupDetails" bundle="${commonLables}"/></b>
						</u>
						</font>
						</td>
					</tr>
					
</table>
<table id="grouptable" width="100%">
<tr colspan="4">
							<td width="25%" align="left">
								<b><hdiits:caption captionid="HR.ACR.Designation" bundle="${commonLables}" id="Name1"/>:</b>
							</td>
							<td width="25%" align="left">
						
							<c:out value="${desgnVO.dsgnName}"/>
							</td>
							<td width="25%" align="left">
								<b><hdiits:caption captionid="HR.ACR.Authority" bundle="${commonLables}"/>:</b>
							
							</td>
							<td width="25%" align="left">
							
								<c:if test="${authority1 eq 0}">
									<hdiits:caption captionid="HR.ACR.SelfAppraisal" bundle="${commonLables}"/>
								</c:if>
								<c:if test="${authority1 eq 1}">
									<hdiits:caption captionid="HR.ACR.Reporter" bundle="${commonLables}"/>
								</c:if>
								<c:if test="${authority1 eq 2}">
									<hdiits:caption captionid="HR.ACR.Reviewer" bundle="${commonLables}"/>
								</c:if>
								<c:if test="${authority1 eq 3}">
									<hdiits:caption captionid="HR.ACR.Approver" bundle="${commonLables}"/>
								</c:if>
							</td>

<td>
							<!-- <b><hdiits:caption captionid="HR.ACR.Office" bundle="${commonLables}" id="Name2"/>:</b> -->	
							</td>
							<td>
							
							</td>

<td>
							<!-- <b><hdiits:caption captionid="HR.ACR.Location" bundle="${commonLables}" id="Name1"/>:</b> -->	
							</td>
							<td>
							
						<!--<c:out value="${locVO.locName}"/>  -->	
							</td>

<td>
						<!-- <b><hdiits:caption captionid="HR.ACR.Role" bundle="${commonLables}" id="Name1"/>:</b>-->		
							</td>
							<td>
						
							
							</td>

<td>
						<!-- <b><hdiits:caption captionid="HR.ACR.Person" bundle="${commonLables}" id="Name1"/>:</b>  -->	
							</td>
							<td>
						
							</td>
<tr>

</table>

		
<hdiits:form name="AcrPointMst" method="POST" validate="true"  encType="multipart/form-data" >

	
	

							
		
		<hdiits:hidden name="code" id="code"/>
		
	
		
			
					<table class="tabtable">
						<tr bgcolor="#386CB7">
							<td class="fieldLabel" width="100%">
								<font class="Label3" align="center" color="white">
									<u>
									<b><hdiits:caption captionid="HR.ACR.SetPoints" bundle="${commonLables}"/></b>
									</u>
								</font>
							</td>
						</tr>
					
					</table>
					
					<br>	<br>
		
			<table id="pointTable" >
			
			
				<tr colspan="3">
				
					<td>
						<b><hdiits:caption captionid="HR.ACR.PointEnglish" bundle="${commonLables}"/></b>
					</td>
					
					<td width="20%">
					</td>
					<td>
						<b><hdiits:caption captionid="HR.ACR.PointGujarati" bundle="${commonLables}"/></b>
					</td>
				</tr>
				<tr colspan="4">
					
					<td width="25%">
					<hdiits:textarea mandatory="true" rows="3" cols="50"  
                                   			 name="GoalEn" tabindex="7" id="GoalEn" 
                                     		  captionid="HR.ACR.PointEnglish" 
                                     		 bundle="${commonLables}" 
                                   			maxlength="4000" onblur="isValidData(this);"/>
                                   			
                                   			
					</td>
					<td width="25%" style="color: red; font-family: Verdana; font-weight: bold; font-size: 8px;">
						<hdiits:caption captionid="HR.ACR.MaxLimit" bundle="${commonLables}"  />
					</td>
					<td width="25%">
					<hdiits:textarea mandatory="true" rows="3" cols="50"  
                                   			 name="GoalGu" tabindex="7" id="GoalGu" 
                                     		  captionid="HR.ACR.PointGujarati" 
                                     		 bundle="${commonLables}" 
                                   			maxlength="4000" onblur="isValidData(this);"/>
                                   			
                                   			
					</td>
					<td width="25%"  style="color: red; font-family: Verdana; font-weight: bold; font-size: 8px;">
						<hdiits:caption captionid="HR.ACR.MaxLimit" bundle="${commonLables}" />
					</td>
				</tr>
			</table>
		
		
	

	<br>
				<br>
		
	

		<table align="center">
			<tr colspan="2">
				<td>
					<hdiits:button type="button" name="btnAdd" value="Add" 
					onclick="javascript:validation();"/>
				</td>
				<td>
					<hdiits:button type="button" id="updateButton" name="btnUpdate" value="Update" style="display:none"
					onclick="javascript:addOrUpdateRecord('updateRecord', 'ACRAddMultiplePoints', new Array('GoalEn','code', 'GoalGu'))"/>
				</td>
				
			</tr>
			
		</table>
		
			<hdiits:hidden name="count" id="counter"/>
			<hdiits:hidden name="authority" default="${authority1}"/>
			<hdiits:hidden name="groupId" default="${groupId}"/>
				
				
				
					<table class="tabtable" id="pointLabelTable" style="display:none">
						<tr bgcolor="#386CB7">
							<td class="fieldLabel" width="100%">
								<font color="#ffffff">
									<b><hdiits:caption captionid="HR.ACR.PointTable" bundle="${commonLables}"/></b>
								</font>
							</td>
						</tr>
					
					</table>
				<br>
				<br>
		
				<table id="txnAdd" style="display:none" border="1" bgcolor="white" bordercolor="black" width="100%"> 
					
					
					<tr bgcolor="B0C4DE">
					
    					<td class="fieldLabel" width="40%">
    					
    						<b><hdiits:caption captionid="HR.ACR.PointEnglish" bundle="${commonLables}" id="PointEnglish"/></b>
		
						</td>
    					<td class="fieldLabel" width="40%">
    						<b><hdiits:caption captionid="HR.ACR.PointGujarati" bundle="${commonLables}" id="PointGujarati"/></b>
						</td>
						<td class="fieldLabel" width="20%" >
							<b><hdiits:caption captionid="HR.ACR.EditDelete" bundle="${commonLables}" /></b>
						</td>  
    				</tr>
				</table>  
				<br>
				<br>
		
		<table id="submit"  align="center">
		<tr colspan="3">
							<td>
								<hdiits:button captionid="HR.ACR.Submit" bundle="${commonLables}" name="AcceptChange" type="button"   onclick="checkRecordCount();"/>
		
							</td>
							<td>
								<hdiits:button captionid="HR.ACR.Close" bundle="${commonLables}" name="SetDefault" type="button" onclick="closeButtonHandler();"/>
		
							</td>
							<td>
								<hdiits:button captionid="HR.ACR.SearchPage" bundle="${commonLables}" name="SearchPage" type="button"   onclick="submit2();"/>
								
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
				document.getElementById('pointLabelTable').style.display='';
				addDBDataInTable('txnAdd','addedPunch',displayFieldA,xmlFileName,'editDBRecord','deleteDBRec');
			</script>
	
	</c:forEach>			
		


</hdiits:form>	
	<jsp:include page="../core/PayTabnavigation.jsp" />
	 
	<script type="text/javascript">
		initializetabcontent("maintab")
		</script>
	<hdiits:validate locale="${locale}" controlNames="" /></div>
</div>


	<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
		