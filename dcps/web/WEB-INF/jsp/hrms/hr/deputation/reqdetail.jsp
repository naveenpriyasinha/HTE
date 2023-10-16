


<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="script/leave/DateDifference.js"></script>
<script type="text/javascript" src="script/leave/DateVal.js"></script>
<script type="text/javascript" src="script/leave/ajax_saveData.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/leave/leavevalidation.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<link rel="stylesheet"
	href="<c:url value="/common/css/tabcontent.css" />" />
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>">
</script>

<script type="text/javascript"
	src="<c:url value="/script/common/base64.js"/>">
</script>


<fmt:setBundle basename="resources.hr.deputation.Deputation" var="comLable"
	scope="request" />
<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="locations" value="${resultValue.locations}">
</c:set>
<c:set var="grades" value="${resultValue.grades}">
</c:set>
<c:set var="attributes" value="${resultValue.attribute}">
</c:set>
<c:set var="conditions" value="${resultValue.condition}">
</c:set>
<c:set var="FileId" value="${resultValue.FileId}">
</c:set>
<c:set var="locationMstObj" value="${resultValue.locationMstObj}">
</c:set>
<c:set var="orgGradeMst" value="${resultValue.orgGradeMst}">
</c:set>

<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="tableBGColor" value="white"></c:set>
<c:set var="hrDeputreqmtDtl" value="${resultValue.hrDeputreqmtDtl}">
</c:set>
<c:set var="todaysDate" value="${resultValue.todaysDate}"></c:set>
<fmt:formatDate var="todayDate"  pattern="dd/MM/yyyy" value="${todaysDate}" type="date"/>

<c:set var="xmlFilePathNameForMulAdd"
	value="${resultValue.xmlFilePathNameForMulAdd}" />

<c:set var="hdInvvisitPanchMpgSet" value="${resultValue.hrEligiblity}" />




<script>


function addRowUsingAjax()
{	
		
	if(!attributValidate())
	{
		
		return false;
	}
	var fieldArray=new Array();
		
	fieldArray[0]='attribute';
	fieldArray[1]='condition';
	fieldArray[2]='description';
	
	addOrUpdateRecord('addRecord','saveXml',fieldArray);

}

function savedetails()
{
	
	var fieldArray=new Array();
	
	fieldArray[0]='attribute';
	fieldArray[1]='condition';
	fieldArray[2]='description';
	
	addOrUpdateRecord('updateRecord','saveXml',fieldArray);
	
}
function updateRecord()
{
		
		if (xmlHttp.readyState == 4) 
		{
				var decXML = xmlHttp.responseText;

					
				var displayFieldArray= new Array();
				displayFieldArray[0]='attribute';
				displayFieldArray[1]='condition';
				displayFieldArray[2]='description';
				updateDataInTable('encXML',displayFieldArray);
				
		}
}

function changerecord()
{
	if(!attributValidate())
	{
		
		return false;
	}
	var fieldArray=new Array();
			
	fieldArray[0]= 'attribute';
	fieldArray[1]='condition';
	fieldArray[2]='description';
    fieldArray[3]='eligiblityid';

	addOrUpdateRecord('updaterecord','updateXml',fieldArray);
	
}
function updaterecord()
{
		if (xmlHttp.readyState == 4) 
		{
				var decXML = xmlHttp.responseText;
		
					
				var displayFieldArray= new Array();
				displayFieldArray[0]='attribute';
				displayFieldArray[1]='condition';
				displayFieldArray[2]='description';
			


				updateDataInTable('addedPunch',displayFieldArray);
				
		}
		document.reqUpdtform.save.disabled=true;
		document.reqUpdtform.add.disabled=false;
		resetAttribut();
}
function addRecord()
{
	
	if (xmlHttp.readyState == 4) 
	{
		var encXML=xmlHttp.responseText;
		
		var displayFieldArray= new Array();
		displayFieldArray[0]='attribute';
		displayFieldArray[1]='condition';
		displayFieldArray[2]='description';
		addDataInTable('eligitable', 'encXML', displayFieldArray, 'editRecord','deleteRecord');
		
	}
	resetAttribut();
	
}
 function editRecord(rowId)
{
		document.reqUpdtform.save.disabled=false;
		document.reqUpdtform.add.disabled=true;
		sendAjaxRequestForEdit(rowId,'populateRecord');
		
		
	
}
function populateRecord()
{
			
	 if (xmlHttp.readyState == 4) 
		 { 				  
			  	var decXML = xmlHttp.responseText;

				var xmlDOM = getDOMFromXML(decXML);	
				/*alert("Value From Dom::::"+getXPathValueFromDOM(xmlDOM,'cmnLookupMstByAttributeId/lookupId'));			*/
				var attribute1=document.getElementById('attribute');
				attribute1.value=getXPathValueFromDOM(xmlDOM,'cmnLookupMstByAttributeId/lookupId');
				attribute1.selected="true";
				var eligibityId = getXPathValueFromDOM(xmlDOM,'eligiblityId');

				document.reqUpdtform.eligiblityid.value = eligibityId;
				var condition1=document.getElementById('condition');
				condition1.value=getXPathValueFromDOM(xmlDOM,'cmnLookupMstByAttributeConditionId/lookupId');
				condition1.selected="true";
				
				var description1=document.getElementById('description');
				description1.value=getXPathValueFromDOM(xmlDOM,'attributeValue');
				
				
		}
}


</script>
<script>
var eligiblity='';

function addRow()
{
 

  if(attributValidate())	
  {
	  var rows=document.getElementById('criteria').rows;
	  var x=document.getElementById('criteria').insertRow(rows.length);
	  var col1=x.insertCell(0);
	  var col2=x.insertCell(1);
	  var col3=x.insertCell(2);
	  col1.colSpan='50';
	  col2.colSpan='50';
	  col3.colSpan='50';
	  col1.innerHTML=''+(rows.length-1);
	  col2.innerHTML=''+document.reqUpdtform.attribute.options[document.reqUpdtform.attribute.options.selectedIndex].text;
	  col3.innerHTML=document.reqUpdtform.condition.options[document.reqUpdtform.condition.options.selectedIndex].text+' '+document.reqUpdtform.description.value;
	  eligiblity='['+document.reqUpdtform.description.value+'~'+document.reqUpdtform.attribute.options[document.reqUpdtform.attribute.options.selectedIndex].value+'~'+document.reqUpdtform.condition.options[document.reqUpdtform.condition.options.selectedIndex].value+'],'+eligiblity;
	  document.reqUpdtform.eligiblity.value=eligiblity;
   }
}
</script>
<script type="text/javascript">

<!-- getPosts function used to show a data in combo box Deputed place reqLoc call a vogen and service-->

function getPosts(loc)
{

	
	xmlHttp = GetXmlHttpObject(); 
	
    var url='./hdiits.htm?actionFlag=reqLoc&locId='+loc.value;
    xmlHttp.open("POST",encodeURI(url),true);  
    xmlHttp.onreadystatechange=readXML;
	xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
	xmlHttp.send(encodeURIComponent(null));
}
function readXML() 
{ 
		removePosts();
	
		if(xmlHttp.readyState == 4)
		{ 
			if(xmlHttp.status == 200) 
			{   
			   	var xmlDoc=xmlHttp.responseText;
				var xmlDOM=getDOMFromXML(xmlDoc);	
				address = xmlDOM.getElementsByTagName('loc-address');
				
				var addr=document.getElementById('locadd');
				addr.innerHTML=''+address[0].text;
				element = xmlDOM.getElementsByTagName('element');
				for(i=0; i<element.length;i++)
				{
					   var y=document.createElement('option');
					   y.value=element[i].childNodes[1].text;
					   y.text=element[i].childNodes[3].text;
					   var x=document.getElementById("reqforpost");
					   x.add(y); 	
				
					   
					   				     
				}
				
				if(reqforPostSet != '')
				{
				 document.getElementById('reqforpost').value = reqforPostSet;
				 reqforPostSet = '';
				}
			} 	
 		}
}	
function removePosts()
{
  var x=document.getElementById("reqforpost")
  i=1
  for(; i<=x.length;)
  {
  	i=1;
  	x.remove(i);
  	i++;
  }
}
function findBigDate()
{
	var d1=document.reqUpdtform.fromdate.value;
	var d2=document.reqUpdtform.todate.value;
    var sdate=new Array();
  	sdate=d1.split('/');
    var edate=new Array();
  	edate=d2.split('/');
  	sdd=sdate[0];
  	smm=sdate[1];
  	syy=sdate[2];
  	edd=edate[0];
  	emm=edate[1];
  	eyy=edate[2];

   if(eval(eyy) >= eval(syy))
   {
	   if(eval(emm) >= eval(smm))   
	   {
		   if(eval(edd) >= eval(sdd))   
		   {

	   	   }else
   	   		{
   	      		alert('<fmt:message bundle="${comLable}" key="Dep.dateValidate"/>');
   	      		return false;
       		}
	   }else
   	   {
   	      alert('<fmt:message bundle="${comLable}" key="Dep.dateValidate"/>');
   	      return false;
       }
   }else
   {
   		alert('<fmt:message bundle="${comLable}" key="Dep.dateValidate"/>');
   		return false;
   }
   return true;
}
</script>
<script>
function validateform()
{
  var rows=document.getElementById('eligitable').rows;	
  if(document.reqUpdtform.location.options.selectedIndex==0)
  {
  	alert('<fmt:message bundle="${comLable}" key="Dep.locValidate"/>');
  document.getElementById('location').focus();
  }else
  {
	  if(document.reqUpdtform.reqforclass.options.selectedIndex==0)
	  {
	  	alert('<fmt:message bundle="${comLable}" key="Dep.classValidate"/>');
	  	  document.getElementById('reqforclass').focus();
	  }else
	  {
		  if(document.reqUpdtform.reqforpost.options.selectedIndex==0)
		  {
		  	alert('<fmt:message bundle="${comLable}" key="Dep.postValidate"/>');
		  	 document.getElementById('reqforpost').focus();
		  }else
		  {
		  if(document.reqUpdtform.noofperson.value.length==0)
		  {
		  	alert('<fmt:message bundle="${comLable}" key="Dep.noofPerson"/>');
		  		 document.getElementById('noofperson').focus();
		  }else
		  {
		  if(document.reqUpdtform.save.disabled==false)
		  	{
		  		alert('<fmt:message bundle="${comLable}" key="Dep.updateBtnValidate"/>');
		  	}else
		  	{
		  	 if(findBigDate())
		  	 {
		  	 	if(rows.length>1)
		  	 	{
		  	 		return true;
			    }else
			    {
			    	alert('<fmt:message bundle="${comLable}" key="Dep.addAttValidate"/>');
			    	document.getElementById('attribute').focus();
			    }
  		     }
  		     }
		  }	  
	  }  
	  }
  }

}
function resetAttribut()
{
	document.reqUpdtform.attribute.options.selectedIndex=0;
	document.reqUpdtform.condition.options.selectedIndex=0;
	document.reqUpdtform.description.value='';
}
function attributValidate()
{
	  if(document.reqUpdtform.attribute.options.selectedIndex==0)
	  {
	  	alert('<fmt:message bundle="${comLable}" key="Dep.attValidate"/>');
	  	document.getElementById('attribute').focus();
	  	return false;
	  }else
	  {
		  if(document.reqUpdtform.condition.options.selectedIndex==0)
		  {
		  	alert('<fmt:message bundle="${comLable}" key="Dep.conValidate"/>');
		 	document.getElementById('condition').focus();
		  	return false;
		  }else
		  {
		  	if(document.reqUpdtform.description.value.length==0)
		  	{
		  		alert('<fmt:message bundle="${comLable}" key="Dep.desValidate"/>');
		  		document.getElementById('description').focus();
		  		return false;
		  	}else
		  	{
		  		return true;
		  	}   				  	
		  }    		 			  	
	  }
}
function resetForm()
{
   document.reqUpdtform.reset();	
}
function descShow()
{
	document.reqUpdtform.description.value='';
	
}
function checkDesc()
{
	var num;
		num = window.event.keyCode;
		if(document.reqUpdtform.attribute.options.selectedIndex==1 ||document.reqUpdtform.attribute.options.selectedIndex==2)
		{
		
			if (eval(num)<48||eval(num)>57)
			{
			document.getElementById('description').focus();
			return false;
			}else{
				if(document.reqUpdtform.description.value.length>1)
				{	
					return false;
				}	
			}
			
		
		}else
		{
			if(document.reqUpdtform.description.value.length>10)
				{	
					return false;
				}
		}
			
}
function showDuration()
	{	
	
	
		if(document.getElementById("fromdate").value!="")
		{
			if(compareDate("${todayDate}",document.getElementById("fromdate").value) < 0 )
			{
				alert("<fmt:message bundle='${comLable}' key='Dep.GreaterThanTodaysDate'/>");
				document.getElementById("fromdate").value="";
				document.getElementById("duration").value="";				
				return;				
			}
		}
		if(document.getElementById("todate").value!="")
		{
			if(compareDate("${todayDate}",document.getElementById("todate").value) < 0 )
			{
				alert("<fmt:message bundle='${comLable}' key='Dep.GreaterThanTodaysDate'/>");
				document.getElementById("todate").value="";	
				document.getElementById("duration").value="";			
				return;	
			}
		}
		if(document.getElementById("fromdate").value!="" && document.getElementById("todate").value!="")
		{			
			var lFrmDate=document.getElementById("fromdate").value;							
			var lToDate=document.getElementById("todate").value;					
			if(compareDate(lFrmDate,lToDate) < 0 )
			{
				alert("<fmt:message bundle='${comLable}' key='Dep.VDate'/>");
				document.getElementById('todate').value='';
				document.getElementById("duration").value="";
				document.reqUpdtform.todate.focus();				
			}
			else
			{
				var dateDiff = getDateDiffInString(lFrmDate,lToDate);
				
				var duration = dateDiff.split("~");
				if(duration[0] != 0 && duration[1] != 0)
					document.getElementById("duration").value=duration[0] +" <fmt:message bundle='${comLable}' key='dep.Years'/> " +duration[1] +" <fmt:message bundle='${comLable}' key='dep.Months'/> " + duration[2]+" <fmt:message bundle='${comLable}' key='dep.Days'/>";
				else if(duration[0] == 0 && duration[1] != 0)
						document.getElementById("duration").value=duration[1] +" <fmt:message bundle='${comLable}' key='dep.Months'/> " + duration[2]+" <fmt:message bundle='${comLable}' key='dep.Days'/>";
					 else if(duration[0] != 0 && duration[1] == 0)
							document.getElementById("duration").value=duration[0] +" <fmt:message bundle='${comLable}' key='dep.Years'/> " + duration[2]+" <fmt:message bundle='${comLable}' key='dep.Days'/>";
						else if(duration[0] == 0 && duration[1] == 0)
								document.getElementById("duration").value=duration[2]+" <fmt:message bundle='${comLable}' key='dep.Days'/>";
							else
								document.getElementById("duration").value=duration[0] +" <fmt:message bundle='${comLable}' key='dep.Years'/> " +duration[1] +" <fmt:message bundle='${comLable}' key='dep.Months'/> " + duration[2]+" <fmt:message bundle='${comLable}' key='dep.Days'/>";
			}	
		}
	}
	
function getDateDiffInString(strDate1,strDate2)
{
			
		strDate1 = strDate1.split("/"); 		
		var starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]); 
		var starttime = new Date(starttime.valueOf()); 
		
		//End date split to UK date format 
		strDate2 = strDate2.split("/"); 
		var endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]); 
		var endtime = new Date(endtime.valueOf()); 												
				
		if(endtime >= starttime) 
		{	
			var setDay = 0;    	
			var lIntPenSerYear = strDate2[2] - strDate1[2];
     	 	var lIntPenSerMonth = strDate2[1]- strDate1[1];
     	 	var lIntPenSerDay = strDate2[0] - strDate1[0];     	 				
     	 	lIntPenSerDay=lIntPenSerDay+1;
     	 	//var intMonth = parseInt(strDate1[1]);
			var intMonth = strDate1[1];
			
     	 	var intday = parseInt(strDate1[0]);   
     	 	intYear = parseInt(strDate1[2]);
     	 	
     	 	while(parseInt(strDate2[2]) >= intYear)
     	 	{     	  		    	 		
				if (intMonth>=13) {			
					intMonth=1;
					intYear++;
				}
				if (intMonth == 1 || intMonth == 3 || intMonth == 5 || intMonth == 7 || intMonth == 8 || intMonth == 10 || intMonth == 12) {
					setDay = 31;	
				}
				if (intMonth == 4 || intMonth == 6 || intMonth == 9 || intMonth == 11) {
					setDay = 30;
				}
				if (intMonth == 2) 
				{
					if (LeapYear(intYear) == true) {
						setDay = 29;
					}
					else {
						setDay = 28;
					}
				}				
	     	 	if(setDay!=0)
	     	 	{     		     	 			
				    while(lIntPenSerDay >= setDay)
				    {
				          lIntPenSerDay -= setDay;
				          lIntPenSerMonth++;
				          if(lIntPenSerMonth==12)
				          {
				              lIntPenSerMonth=0;
				              lIntPenSerYear++;
				          }
				    }
				    while(lIntPenSerDay < 0)
				    {
				          lIntPenSerDay = setDay + lIntPenSerDay;
				          lIntPenSerMonth--;
				          if(lIntPenSerMonth<=-1)
				          {
				              lIntPenSerMonth=12+lIntPenSerMonth;
				              lIntPenSerYear--;    
				                   
				          }
				        
				    }				    
				    if(lIntPenSerMonth <=-1)
				    {
				          lIntPenSerMonth=12+lIntPenSerMonth;
				          lIntPenSerYear--;  
				                      
				    }
				    if(strDate1.toString() == strDate2.toString())
						{lIntPenSerDay=1;}
				    return (lIntPenSerYear+'~'+lIntPenSerMonth+'~'+lIntPenSerDay);
				}
				else 
				{
					return '0~0~0'; 
				}
				intMonth++;													
			}
		}
		else
		{
			return '0~0~0'; 
		}
	
}

function LeapYear(year)
{	
	if((year%4 == 0) && ((year % 100)!=0) || ((year % 400)==0) ){return true;}
	else 	{
		return false;		
	}
}
function loadduration(lFrmDate,lToDate)
{
			var dateDiff = getDateDiffInString(lFrmDate,lToDate);
			var duration = dateDiff.split("~");
				if(duration[0] != 0 && duration[1] != 0)
					document.getElementById("duration").value=duration[0] +" <fmt:message bundle='${comLable}' key='dep.Years'/> " +duration[1] +" <fmt:message bundle='${comLable}' key='dep.Months'/> " + duration[2]+" <fmt:message bundle='${comLable}' key='dep.Days'/>";
				else if(duration[0] == 0 && duration[1] != 0)
						document.getElementById("duration").value=duration[1] +" <fmt:message bundle='${comLable}' key='dep.Months'/> " + duration[2]+" <fmt:message bundle='${comLable}' key='dep.Days'/>";
					 else if(duration[0] != 0 && duration[1] == 0)
							document.getElementById("duration").value=duration[0] +" <fmt:message bundle='${comLable}' key='dep.Years'/> " + duration[2]+" <fmt:message bundle='${comLable}' key='dep.Days'/>";
						else if(duration[0] == 0 && duration[1] == 0)
								document.getElementById("duration").value=duration[2]+" <fmt:message bundle='${comLable}' key='dep.Days'/>";
							else
								document.getElementById("duration").value=duration[0] +" <fmt:message bundle='${comLable}' key='dep.Years'/> " +duration[1] +" <fmt:message bundle='${comLable}' key='dep.Months'/> " + duration[2]+" <fmt:message bundle='${comLable}' key='dep.Days'/>";

}

function validForEnterdate()
{
	 if(showDuration())
	 {
		
		
	 }
	 
	
}
</script>
<hdiits:form name="reqUpdtform" validate="true" method="POST" action="./hrms.htm?actionFlag=updateReq">

<div>
<!-- 
<table width="100%">
<tr bgcolor="#386CB7">
					<td class="fieldLabel" align="center"><font color="#ffffff"> <b>
					<fmt:message key="Dep.DeputReqDtl" bundle="${comLable}"></fmt:message>
					</b> </font></td>
				</tr>
</table> -->
<hdiits:fieldGroup titleCaptionId="Dep.DeputReqDtl" bundle="${comLable}"  collapseOnLoad="true">
<table class="tabhdiits:table" bordercolor="#386CB7" width="100%">
	<tr>


		<td>

			<c:set var="LocName"
				value="${locationMstObj.locId}"></c:set>
			
			<c:set var="LocAdd"
				value="${hrDeputreqmtDtl.cmnLocationMstByReqdforlocationId.locAddr1}"></c:set>
			<c:set var="NoofPer" value="${hrDeputreqmtDtl.noOfPersons}"></c:set>
			<c:set var="ReqforClass"
				value="${orgGradeMst.gradeId}"></c:set>
			<c:set var="ReqforPost"
				value="${hrDeputreqmtDtl.orgPostMstByReqdforpostId.postId}"></c:set>
			
			<c:set var="FromDate" value="${hrDeputreqmtDtl.deputfromDate}" ></c:set>
			<fmt:formatDate var="FromDate" value="${hrDeputreqmtDtl.deputfromDate}" pattern="dd/MM/yyyy"/>
			<c:set var="ToDate" value="${hrDeputreqmtDtl.deputtoDate}"></c:set>
			<fmt:formatDate var="ToDate" value="${hrDeputreqmtDtl.deputtoDate}" pattern="dd/MM/yyyy"/>
			






			<hdiits:hidden name="eligiblity" id="eligiblity" />
			<hdiits:hidden name="eligiblityid" id="eligiblityid" />
			<c:set var="fileId" value="${FileId}"></c:set>
			<input type="hidden" name="fileid" value="${fileId}" />
			<hdiits:hidden name="wffileId_hidden" id="wffileId_hidden" />

			<table class="tabhdiits:table" border="0" bordercolor="#386CB7"
				width="100%">

				
				<tr>
					<td>
					<table align="left" width="100%">
						<tr>
							<td>
							<table width="100%">
								<tr>
									<td width="25%"><b><hdiits:caption
										captionid="deputedplace" bundle="${comLable}" /></b></td>
									<td width="25%"><hdiits:select name="location"
										id="location" onchange="getPosts(this)" mandatory="true">
										<option value="0"><fmt:message key="Dep.sel" bundle="${comLable}"></fmt:message></option>


										<c:forEach var="locObj" items="${locations}">
											<hdiits:option value='${locObj.locId}'>${locObj.locName}</hdiits:option>
										</c:forEach>

									</hdiits:select></td>
									<td width="25%"></td>

									<td width="25%"></td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td>
							<table width="100%">
								<tr>
									<td width="25%"><b><hdiits:caption captionid="address"
										bundle="${comLable}" /></b></td>
									<td width="25%"><span id="locadd"> </span></td>
									<td width="25%"><b><hdiits:caption
										captionid="noofperson" bundle="${comLable}" /></b></td>

									<td width="25%"><hdiits:text name="noofperson"
										id="noofperson" mandatory="true" maxlength="4" onkeypress="return checkNumberOnly(false);"/></td>
								</tr>
							</table>
							</td>
						</tr>

						<tr>
							<td>
							<table align="left" width="100%">
								<tr>
									<td width="25%"><b><hdiits:caption
										captionid="reqforclass" bundle="${comLable}" /></b></td>
									<td width="25%"><hdiits:select name="reqforclass"
										id="reqforclass" mandatory="true">
										<option value="0"><fmt:message key="Dep.sel" bundle="${comLable}"></fmt:message></option>
										<c:forEach var="gradObj" items="${grades}">
											<hdiits:option value="${gradObj.gradeId}">${gradObj.gradeName}</hdiits:option>
										</c:forEach>

									</hdiits:select></td>
									<td width="25%"><b><hdiits:caption
										captionid="reqforpost" bundle="${comLable}" /></b></td>

									<td width="25%"><hdiits:select name="reqforpost"
										id="reqforpost" mandatory="true">
										<option value="0"><fmt:message key="Dep.sel" bundle="${comLable}"></fmt:message></option>
									</hdiits:select></td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td>
							<table width="100%">
								<tr>
									<td width="25%"><b><hdiits:caption
										captionid="fromdate" bundle="${comLable}" /></b></td>
									<td width="25%"><hdiits:dateTime name="fromdate"
										captionid="fromdate" mandatory="true" bundle="${comLable}" maxvalue="31/12/2099" afterDateSelect="showDuration();" onblur="validForEnterdate();"/></td>
									<td width="25%"><b><hdiits:caption captionid="todate"
										bundle="${comLable}" /></b></td>
									<td width="25%"><hdiits:dateTime name="todate"
										captionid="todate" mandatory="true" bundle="${comLable}" maxvalue="31/12/2099" afterDateSelect="showDuration();" onblur="validForEnterdate();"/></td>
								</tr>
								<tr>
								<td><b><hdiits:caption captionid="duration" bundle="${comLable}" /></b></td>
								<td width="25%"><hdiits:text name="duration" id="duration" readonly="true"/></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				
				<tr>
					<td>
					<hdiits:fieldGroup titleCaptionId="eligibilitycriteria" bundle="${comLable}"  collapseOnLoad="true">
					<table align="left" width="100%">
						<tr>
							<td>
							<table align="left" width="100%">
								<tr>
									<td width="15%"><b><hdiits:caption
										captionid="attribute" bundle="${comLable}" /></b></td>
									<td width="15%"><hdiits:select name="attribute"
										id="attribute" mandatory="true" onchange="descShow();">
										<option value="0"><fmt:message key="Dep.sel" bundle="${comLable}"></fmt:message></option>
										<c:forEach var="attr" items="${attributes}">
											<hdiits:option value="${attr.lookupId}">${attr.lookupDesc}</hdiits:option>
										</c:forEach>
									</hdiits:select></td>
									<td width="15%" align="left"><b><hdiits:caption
										captionid="condition" bundle="${comLable}" /></b></td>
									<td width="15%"><hdiits:select name="condition"
										id="condition" mandatory="true">
										<option value="0"><fmt:message key="Dep.sel" bundle="${comLable}"></fmt:message></option>
										<c:forEach var="con" items="${conditions}">
											<hdiits:option value="${con.lookupId}">${con.lookupDesc}</hdiits:option>
										</c:forEach>
									</hdiits:select></td>
									<td width="15%" align="left"><b><hdiits:caption
										captionid="description" bundle="${comLable}" /></b></td>

									<td width="18%"><hdiits:text name="description"
										id="description" size="20" mandatory="true" onkeypress="return checkDesc();"/></td>
								</tr>
							</table>
							</td>
						</tr>

						<tr>
							<td>
							<table align="center" width="30%">
								<tr>

									<td align="right" colspan="10"><hdiits:button name="add"
										captionid="addB" bundle="${comLable}" type="button"
										tabindex="3" onclick="addRowUsingAjax()" /></td>
									
									<td align="left" colspan="10" ><hdiits:button name="save"
										captionid="updateB" bundle="${comLable}" type="button"
										tabindex="3" onclick="changerecord()" readonly="true"/></td>


								</tr>
							</table>
							</td>
						</tr>

						<tr>
							<td>
							<table id="eligitable" style="display:none" 
								align="center" width="80%" border="1"
				cellpadding="0" cellspacing="0" BGCOLOR="WHITE" width="100%" style="border-collapse: collapse; background-color:${tableBGColor}" border=1 borderColor="black" class="tabtable">
								<tr style="background-color:${tdBGColor}">
									<td class="fieldLabel" width="20%" align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
										captionid="attribute" bundle="${comLable}" /></b></td>
									<td class="fieldLabel" width="20%" align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
										captionid="condition" bundle="${comLable}" /></b></td>
									<td class="fieldLabel" width="20%" align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
										captionid="description" bundle="${comLable}" /></b></td>
									<td class="fieldLabel" width="20%" align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
										captionid="action" bundle="${comLable}" /></b></td>
								</tr>
							</table>



							<c:forEach items="${hdInvvisitPanchMpgSet}"
								var="hdInvvisitPanchMpgTuples" varStatus="x">

								<c:set var="curXMLFileName"
									value="${xmlFilePathNameForMulAdd[x.index]}"></c:set>
							

								
								<c:set var="Attribute"
									value="${hdInvvisitPanchMpgTuples.cmnLookupMstByAttributeId.lookupDesc}"></c:set>
								<c:set var="Condition"
									value="${hdInvvisitPanchMpgTuples.cmnLookupMstByAttributeConditionId.lookupDesc}"></c:set>
								<c:set var="attributeValue"
									value="${hdInvvisitPanchMpgTuples.attributeValue}"></c:set>
								<script type="text/javascript">
										var xmlFileName = '${curXMLFileName}';
 									
							var displayFieldA  = new Array('${Attribute}','${Condition}','${attributeValue}');

							addDBDataInTable('eligitable','addedPunch',displayFieldA,xmlFileName,'editRecord', 'deleteDBRecord');
							</script>
							</c:forEach></td>
						</tr>
						<tr>
							<td></td>
						</tr>
						<tr>
							<td>
							<table align="center" width="30%">
								<tr>

									<hdiits:jsField jsFunction="validateform()" name="validate" />

								</tr>
								<tr>

								</tr>
							</table>
							</td>
						</tr>
					</table>
					</hdiits:fieldGroup>
					</td>
				</tr>
			</table>
		
	</tr>
</table>

<jsp:include page="../../../core/tabnavigation.jsp" />
</hdiits:fieldGroup>
<script type="text/javascript">
	
	
</script>

</div>
<hdiits:validate controlNames="text"
	locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
<script type="text/javascript">
		var locNameSet = '${LocName}';
		var locaddSet='${LocAdd}';
		var noofperSet='${NoofPer}';
		var reqforclassSet='${ReqforClass}';
		var reqforPostSet='${ReqforPost}';
		var fromDateSet='${FromDate}';
		var toDateSet='${ToDate}';
		
		var XmlFile = '${xmlFilePathNameForMulAdd}';
	
		var addr=document.getElementById('locadd');
				addr.innerHTML=''+locaddSet;
				
	document.getElementById('location').value = locNameSet;
	document.getElementById('locadd').value = locaddSet;
	document.getElementById('noofperson').value = noofperSet;
	document.getElementById('reqforclass').value = reqforclassSet;
	
	getPosts(document.getElementById('location'));
	
	document.getElementById('reqforpost').value = reqforPostSet;
	document.getElementById('fromdate').value = fromDateSet;
	document.getElementById('todate').value =toDateSet;
	
	loadduration(fromDateSet,toDateSet);
	

</script>
