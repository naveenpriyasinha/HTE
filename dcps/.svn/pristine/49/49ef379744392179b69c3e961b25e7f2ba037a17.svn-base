<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%
try
{%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix= "ajax" %> 
<fmt:setBundle basename="resources.gnl.LaqApplication.LaqApplicationLables" var="Lables" scope="request"/>
<fmt:setBundle basename="resources.gnl.LaqApplication.LaqApplicationAlerts" var="Alerts" scope="request"/>


<script type="text/javascript" src="<c:url value="/script/hrms/gnl/LAQ/LaqBasicInfo.js"/>"></script>
<script type="text/javascript" src="script/common/addRecord.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="/script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="script/common/prototype-1.3.1.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/base64.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="LaqTypeList" value="${resultValue.LaqType}"></c:set>
<c:set var="Year" value="${resultValue.Year}"></c:set>
<c:set var="nameOfHodList" value="${resultValue.nameOfHodList}"></c:set>
<c:set var="laqMemDtls" value="${resultValue.laqMemDtls}"></c:set>
<c:set var="LaqStatus" value="${resultValue.LaqStatus}"></c:set>
<c:set var="subjectList" value="${resultValue.subjectList}"></c:set>
<c:set var="regionList" value="${resultValue.regionList}"></c:set>
<c:set var="fileId" value="${resultValue.fileId}"></c:set>
<c:set var="flag" value="${resultValue.flag}"></c:set>
<c:set var="laqNo" value="${resultValue.laqNo}"></c:set>
<c:set var="letterDated" value="${resultValue.letterDated}"></c:set>
<c:set var="subSubjectCode" value="${resultValue.subSubjectCode}"></c:set>
<c:set var="subSubjectName" value="${resultValue.subSubjectName}"></c:set>
<c:set var="selectedYear" value="${resultValue.selectedYear}"></c:set>
<c:set var="vidhansabha" value="${resultValue.vidhansabha}"></c:set>
<c:set var="session" value="${resultValue.session}"></c:set>
<c:set var="mainSubjectCode" value="${resultValue.mainSubjectCode}"></c:set>
<c:set var="queRaisedByMemCode" value="${resultValue.queRaisedByMemCode}"></c:set>
<c:set var="toBeRepliedByMemCode" value="${resultValue.toBeRepliedByMemCode}"></c:set>
<c:set var="laqTypeLookupName" value="${resultValue.laqTypeLookupName}"></c:set>
<c:set var="questionNo" value="${resultValue.questionNo}"></c:set>
<c:set var="prvRelatedQueNo" value="${resultValue.prvRelatedQueNo}"></c:set>
<c:set var="ifSupplementryQue" value="${resultValue.ifSupplementryQue}"></c:set>
<c:set var="dueDateOfReply" value="${resultValue.dueDateOfReply}"></c:set>
<c:set var="replyDateInAssbly" value="${resultValue.replyDateInAssbly}"></c:set>
<c:set var="priorityNo" value="${resultValue.priorityNo}"></c:set>
<c:set var="noOfCopies" value="${resultValue.noOfCopies}"></c:set>
<c:set var="laqStatusLookupName" value="${resultValue.laqStatusLookupName}"></c:set>
<c:set var="revisedDateInAssbly" value="${resultValue.revisedDateInAssbly}"></c:set>
<c:set var="ifDiscussedInAssbly" value="${resultValue.ifDiscussedInAssbly}"></c:set>
<c:set var="mainQuestion" value="${resultValue.mainQuestion}"></c:set>
<c:set var="mainAnswer" value="${resultValue.mainAnswer}"></c:set>
<c:set var="mainAssurance" value="${resultValue.mainAssurance}"></c:set>
<c:set var="hrGnlLaqreplyDtl" value="${resultValue.hrGnlLaqreplyDtl}"></c:set>
<c:set var="replyDate" value="${resultValue.replyDate}"></c:set>
<c:set var="hrGnlLaqactionDtlList" value="${resultValue.hrGnlLaqactionDtlList}"></c:set>
<c:set var="hrGnlLaqDtlList" value="${resultValue.hrGnlLaqDtlList}"></c:set>
<c:set var="hrGnlLaqsubitemDtlList" value="${resultValue.hrGnlLaqsubitemDtlList}"></c:set>
<c:set var="hrGnlLaqsubitemDtlSetKey" value="${resultValue.hrGnlLaqsubitemDtlSetKey}"></c:set>
<c:set var="xmlFilePathNameForMulAddForSubIteam" value="${resultValue.xmlFilePathNameForMulAddForSubIteam}"></c:set>


<script language="javascript">
var LAQAlertMsg=new Array();
LAQAlertMsg[0]='<fmt:message  bundle="${Lables}" key="SelectNameOfHodFirst"/>';
LAQAlertMsg[1]='<fmt:message  bundle="${Lables}" key="SelectNameOfOfficeFirst"/>';
LAQAlertMsg[2]='<fmt:message  bundle="${Alerts}" key="LAQ.MLA_NOT_SAME"/>';
LAQAlertMsg[3]='<fmt:message  bundle="${Alerts}" key="LAQ.PLZ_ENTER_QUE"/>';
LAQAlertMsg[4]='<fmt:message  bundle="${Alerts}" key="LAQ.CAN_NOT_ADD_BLANK_DATA"/>';


var i = 1;

function getSubOfficeFromOffice(nameOfOffice)
{	
	var selectedfileds;
	var locId = nameOfOffice.value;
	if(locId == '')
	{
		return;
	}
	if(locId != 0)
	{
		selectedfileds = getSelectedValues(nameOfOffice);
		var url="hdiits.htm?actionFlag=getAllComboDtlsForLAQ&selectedfileds="+selectedfileds;
		xmlHttp.open("POST", encodeURI(url) , true);			
		xmlHttp.onreadystatechange = displyNameOfSubOffices;
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));
		
	}
	
}
function displyNameOfSubOffices()
{
	if (xmlHttp.readyState == 4) 
	{     		
		if (xmlHttp.status == 200) 
		{	
			var subOffice = document.getElementById('Sub_Office');
			
			
			var xmlStr = xmlHttp.responseText;
			//alert(xmlStr);
			var XMLDoc=getDOMFromXML(xmlStr);   // xmlHttp.responseXML.documentElement;						    			    	
			//alert(XMLDoc);
			var nameOfSubOffices = XMLDoc.getElementsByTagName('nameOfSubOffices');
			var subOfficeLocId = XMLDoc.getElementsByTagName('subOfficeLocId');
			
			for (var i=subOffice.length;i>0;i--)  // removing a previous value of combo
  			{	     				     					
				subOffice.remove(subOffice.value);	     				
				subOffice.remove(subOffice.text);
			}
			var y=document.createElement('option');
 			y.text=('<fmt:message  bundle="${Lables}" key="SelectNameOfOfficeFirst"/>');
 			//y.value='Select';
			
			try
			{
				subOffice.add(y,null); 	    						
			}
			catch(ex)
			{	   			 
					subOffice.add(y);	   			 				 
			} 
			subOffice.value='Select'; 
			
			for ( var i = 0 ; i < nameOfSubOffices.length ; i++ ){
				value=subOfficeLocId[i].childNodes[0].text;
				text=nameOfSubOffices[i].childNodes[0].text;	
				var y=document.createElement('option');
	 			y.text=text;
	 			y.value=value;
			
				try
				{
					subOffice.add(y,null); 	    						
				}
				catch(ex)
				{	   			 
						subOffice.add(y);	   			 				 
				}     				
	   		}
			
		}
	}
}


function getConstituencyFromName(queRaisedBy,memFlag)
{
	var memCode = queRaisedBy.value;
	var check = checkSelectionOfMla(memCode,memFlag);
	if(memCode == '' ||  check == false)
	{
		return;
	}
	if(memCode != 0)
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
		var url="hdiits.htm?actionFlag=getAllComboDtlsForLAQ&memCode="+memCode+"&memFlag="+memFlag;
		xmlHttp.open("POST", encodeURI(url) , true);			
		xmlHttp.onreadystatechange = displayConstituency;
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));
	}
}

function getMlaNameFromConstituency(constituency,memFlag)
{	//alert('memFlag'+memFlag);
	var memCode = constituency.value;
	var check = checkSelectionOfMla(memCode,memFlag);
	if(memCode == '' || check == false )
	{
		return;
	}
	if(memCode != 0)
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
		var url="hdiits.htm?actionFlag=getAllComboDtlsForLAQ&memCodeforconstituency="+memCode+"&memFlagforconstituency="+memFlag;
		xmlHttp.open("POST", encodeURI(url) , true);			
		xmlHttp.onreadystatechange = displayMlaName;
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));
	}
}

function checkSelectionOfMla(memCode,memFlag)
{
	if(memFlag == 1)
	{
		var to_be_rep_by = document.getElementById('To_Be_Rep_By');
		if(to_be_rep_by.value == memCode)
		{	
			alert(LAQAlertMsg[2]);
			document.getElementById('Question_Raised_By').value = 0;
			document.getElementById('Constituency').value = 0;
			return false;
		}
		else{
		return true;
		}
	}
	if(memFlag == 2)
	{
		var que_raised_by = document.getElementById('Question_Raised_By');
		if(que_raised_by.value == memCode)
		{	
			alert(LAQAlertMsg[2]);
			document.getElementById('To_Be_Rep_By').value = 0;
			document.getElementById('Rep_Constituency').value = 0;
			return false;
		}
		else{
		return true;
		}
	}
}
function getSelectedValues (select) 
{	
	var r = new Array();
  	for (var i = 0; i < select.options.length; i++)
    	if (select.options[i].selected)
      		r[r.length] = select.options[i].value;
  	return r;
}

function getSubSubjectFromSubject(selectedSubject)
{		
	var subject = selectedSubject.value;
	//alert(subject);
	if(subject == '')
	{
		return;
	}
	if(subject != 0)
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
		
		var url="hdiits.htm?actionFlag=getAllComboDtlsForLAQ&subject="+subject;
		xmlHttp.open("POST", encodeURI(url) , true);			
		xmlHttp.onreadystatechange = displaySubSubject;
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));
	}
}

function displaySubSubject()
{
	if (xmlHttp.readyState == 4) 
	{     	
		if (xmlHttp.status == 200) 
		{	
			var subSubject = document.getElementById('Sub_Subject');
			
			var xmlStr = xmlHttp.responseText;
			//alert(xmlStr);
			var XMLDoc=getDOMFromXML(xmlStr);   // xmlHttp.responseXML.documentElement;						    			    	
			//alert(XMLDoc);
			var subjectName = XMLDoc.getElementsByTagName('SubjectName');
			var subjectCode = XMLDoc.getElementsByTagName('SubjectCode');
			
			for (var i=subSubject.length;i>0;i--)  // removing a previous value of combo
  			{	     				     					
				subSubject.remove(subSubject.value);	     				
				subSubject.remove(subSubject.text);
				
				var y=document.createElement('option');
	 			y.text=('<fmt:message  bundle="${Lables}" key="SelectSubFirst"/>');
	 		//	y.value='Select';
				
				try
				{
					subSubject.add(y,null); 	    						
				}
				catch(ex)
				{	   			 
 					subSubject.add(y);	   			 				 
				} 
				subSubject.value='Select'; 
			}
			subSubject.value='Select'; 
			for ( var i = 0 ; i < subjectName.length ; i++ ){
			value=subjectCode[i].childNodes[0].text;
			text=subjectName[i].childNodes[0].text;	
			var y=document.createElement('option');
 			y.text=text;
 			y.value=value;
		
			try
			{
				subSubject.add(y,null); 	    						
			}
			catch(ex)
			{	   			 
					subSubject.add(y);	   			 				 
			}     				
   		}
			
		}
	}
}
function getDistrictFromRegion(region)
{
	var regionCode = getSelectedValues(region);
	//alert(regionCode);
	if(regionCode == '')
	{
		return;
	}
	if(regionCode != 0)
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
		
		var url="hdiits.htm?actionFlag=getAllComboDtlsForLAQ&regionCode="+regionCode;
		xmlHttp.open("POST", encodeURI(url) , true);			
		xmlHttp.onreadystatechange = displayDistrict;
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));
	}
}
function displayDistrict()
{
	if (xmlHttp.readyState == 4) 
	{     	
		if (xmlHttp.status == 200) 
		{	
			var district = document.getElementById('District');
			var taluka = document.getElementById('Taluka');
			var city = document.getElementById('City');
			var village = document.getElementById('Village');
			
			var xmlStr = xmlHttp.responseText;
			//alert(xmlStr);
			var XMLDoc=getDOMFromXML(xmlStr);   // xmlHttp.responseXML.documentElement;						    			    	
			//alert(XMLDoc);
			var districtName = XMLDoc.getElementsByTagName('DistrictName');
			var districtId = XMLDoc.getElementsByTagName('DistrictId');
			
			for (var i=district.length;i>0;i--)  // removing a previous value of combo
  			{	     				     					
				district.remove(district.value);	     				
				district.remove(district.text);
				
				var y=document.createElement('option');
	 			y.text=('<fmt:message  bundle="${Lables}" key="SelectRegionFirst"/>');
	 		//	y.value='Select';
				
				try
				{
					district.add(y,null); 	    						
				}
				catch(ex)
				{	   			 
 					district.add(y);	   			 				 
				} 
				district.value='Select'; 
			}
			for (var i=taluka.length;i>0;i--)  // removing a previous value of combo
  			{	     				     					
				taluka.remove(taluka.value);	     				
				taluka.remove(taluka.text);
			}
			var talukaSelect=document.createElement('option');
 			talukaSelect.text=('<fmt:message  bundle="${Lables}" key="SelectDistrictFirst"/>');
			taluka.add(talukaSelect);
			
			for (var i=city.length;i>0;i--)  // removing a previous value of combo
  			{	     				     					
				city.remove(city.value);	     				
				city.remove(city.text);
			}
			var citySelect=document.createElement('option');
 			citySelect.text=('<fmt:message  bundle="${Lables}" key="SelectDistrictFirst"/>');
			city.add(citySelect);
			
			for (var i=village.length;i>0;i--)  // removing a previous value of combo
  			{	     				     					
				village.remove(village.value);	     				
				village.remove(village.text);
			}
			var villageSelect=document.createElement('option');
 			villageSelect.text=('<fmt:message  bundle="${Lables}" key="SelectTalukaFirst"/>');
			village.add(villageSelect);
			
			//subSubject.value='Select'; 
			for ( var i = 0 ; i < districtName.length ; i++ ){
			value=districtId[i].childNodes[0].text;
			text=districtName[i].childNodes[0].text;	
			var y=document.createElement('option');
 			y.text=text;
 			y.value=value;
		
			try
			{
				district.add(y,null); 	    						
			}
			catch(ex)
			{	   			 
					district.add(y);	   			 				 
			}     				
   		}
		}
	}
}

function getTalukaFromDistrict(distId)
{	
	var selectedDistId = getSelectedValues(distId);
	//alert(selectedDistId);
	if(selectedDistId == '')
	{
		return;
	}
	if(selectedDistId != 0)
	{
		var url="hdiits.htm?actionFlag=getAllComboDtlsForLAQ&selectedDistId="+selectedDistId;
		xmlHttp.open("POST", encodeURI(url) , true);			
		xmlHttp.onreadystatechange = displayTaluka;
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));
	
	}
	
}

function displayTaluka()
{
	if (xmlHttp.readyState == 4) 
	{     		
		if (xmlHttp.status == 200) 
		{	
			var taluka = document.getElementById('Taluka');
			var city = document.getElementById('City');
			
			var xmlStr = xmlHttp.responseText;
			//alert(xmlStr);
			var XMLDoc=getDOMFromXML(xmlStr);   // xmlHttp.responseXML.documentElement;						    			    	
			//alert(XMLDoc);
			var talukaName = XMLDoc.getElementsByTagName('TalukaName');
			var talukaCode = XMLDoc.getElementsByTagName('TalukaCode');
			var cityName = XMLDoc.getElementsByTagName('CityName');
			var cityCode = XMLDoc.getElementsByTagName('CityCode');
			
			for (var i=taluka.length;i>0;i--)  // removing a previous value of combo
  			{	     				     					
				taluka.remove(taluka.value);	     				
				taluka.remove(taluka.text);
			}
			for (var i=city.length;i>0;i--)  // removing a previous value of combo
  			{	     				     					
				city.remove(city.value);	     				
				city.remove(city.text);
			}
			var y=document.createElement('option');
 			y.text=('<fmt:message  bundle="${Lables}" key="SelectDistrictFirst"/>');
 			//y.value='Select';
			
			try
			{
				taluka.add(y,null); 
									
			}
			catch(ex)
			{	   			 
					taluka.add(y);	
						   			 				 
			} 
			taluka.value='Select'; 
			
			
			for ( var i = 0 ; i < talukaName.length ; i++ ){
				value=talukaCode[i].childNodes[0].text;
				text=talukaName[i].childNodes[0].text;	
				var y=document.createElement('option');
	 			y.text=text;
	 			y.value=value;
	 			
	 			try
				{
					taluka.add(y,null); 
						    						
				}
				catch(ex)
				{	   			 
						taluka.add(y);	
						 		   			 				 
				}   
	 		}
	 		
	 		var y=document.createElement('option');
 			y.text=('<fmt:message  bundle="${Lables}" key="SelectDistrictFirst"/>');	
	 		try
			{
				
				city.add(y,null); 	    						
			}
			catch(ex)
			{	   			 
					
					city.add(y);	   		   			 				 
			} 
			//city.value='Select'; 
	 		for ( var j = 0 ; j < cityName.length ; j++ ){
				value=cityCode[j].childNodes[0].text;
				text=cityName[j].childNodes[0].text;	
				var y=document.createElement('option');
	 			y.text=text;
	 			y.value=value;	
			
				try
				{
					
					city.add(y,null); 	    	    						
				}
				catch(ex)
				{	   			 
						
					city.add(y);	   		   			 				 
				}     				
	   		}
	   	
		  
			
		}
	}
}

function getVillageFromTaluka(talukacode)
{
	var selectedTalukaCode = getSelectedValues(talukacode);
	//alert(selectedTalukaCode);
	if(selectedTalukaCode == '')
	{
		return;
	}
	if(selectedTalukaCode != 0)
	{
		var url="hdiits.htm?actionFlag=getAllComboDtlsForLAQ&selectedTalukaCode="+selectedTalukaCode;
		xmlHttp.open("POST", encodeURI(url) , true);			
		xmlHttp.onreadystatechange = displayVillage;
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));
	
	}
}

function displayVillage()
{
	if (xmlHttp.readyState == 4) 
	{     		
		if (xmlHttp.status == 200) 
		{	
			var village = document.getElementById('Village');
			
			var xmlStr = xmlHttp.responseText;
			//alert(xmlStr);
			var XMLDoc=getDOMFromXML(xmlStr);   // xmlHttp.responseXML.documentElement;						    			    	
			//alert(XMLDoc);
			var villageName = XMLDoc.getElementsByTagName('VillageName');
			var villageCode = XMLDoc.getElementsByTagName('VillageCode');
			
			
			for (var i=village.length;i>0;i--)  // removing a previous value of combo
  			{	     				     					
				village.remove(village.value);	     				
				village.remove(village.text);
			}
			
			var y=document.createElement('option');
 			y.text=('<fmt:message  bundle="${Lables}" key="SelectTalukaFirst"/>');
 			//y.value='Select';
			
			try
			{
				village.add(y,null); 
									
			}
			catch(ex)
			{	   			 
					village.add(y);	
						   			 				 
			} 
			village.value='Select'; 
			
			
			for ( var i = 0 ; i < villageName.length ; i++ ){
				value=villageCode[i].childNodes[0].text;
				text=villageName[i].childNodes[0].text;	
				var y=document.createElement('option');
	 			y.text=text;
	 			y.value=value;
	 			
	 			try
				{
					village.add(y,null); 
						    						
				}
				catch(ex)
				{	   			 
						village.add(y);	
						 		   			 				 
				}   
	 		}
	 	
		}
	}
	
}

function validateRichText()
{	
	
	if(document.LAQForm.elements['rte'+'QuestionRichText'].value == "")
	{
		alert(LAQAlertMsg[3]);
		return true;
	}
	else
	{
		return false;
	}
}
function valRichText()
{
}
</script>

<hdiits:form name="LAQForm" validate="true" method="post" encType="multipart/form-data"  action="javascript:submitLaqRequest()"> 

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
	<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="Leg_Ass_Ans__Que_Det" bundle="${Lables}"/></b></a></li>
	<li><a href="#" rel="tcontent2"><b><fmt:message key="Que_And_Ans" bundle="${Lables}"/></b></a></li>
	<li><a href="#" rel="tcontent3"><b><fmt:message key="Status_dtls" bundle="${Lables}"/></b></a></li>
	<li><a href="#" rel="tcontent4"><b><fmt:message key="Action_Taken" bundle="${Lables}"/></b></a></li>
	</ul>
</div>
<div id="tcontent1" class="tabcontent" tabno="0">


<hdiits:fieldGroup bundle="${Lables}" expandable="true" id="Leg_Ass_Que_Det" titleCaptionId="Leg_Ass_Que_Det">
<TABLE class=tabtable align="center">  
	<tr>  
	<TD width="25%"><b><hdiits:caption captionid="Laq_No" bundle="${Lables}"/></b></td>
	<td width="25%"><hdiits:number name="Laq_No" maxlength="10" mandatory="true"  captionid="Laq_No" bundle="${Lables}" validation="txt.isrequired"/></td>      
	<c:if test="${flag eq 'redirectView'}">
	<script>
	document.LAQForm.Laq_No.value = "${laqNo}";
	</script>	
	</c:if>
	
	
	<TD width="25%"><b><hdiits:caption captionid="Letter_dated" bundle="${Lables}"/></b></td>
	<td width="25%"><hdiits:dateTime name="Letter_dated"  mandatory="true" captionid="Letter_dated" bundle="${Lables}" validation="txt.isrequired" maxvalue="31/12/2999"/></td>
	<c:if test="${flag eq 'redirectView'}">
	<script>
	document.LAQForm.Letter_dated.value = "${letterDated}";
	</script>	
	</c:if>
	
  </TR>
  
  <tr>  
  	<TD width="25%"><b><hdiits:caption captionid="Subject" bundle="${Lables}"/></b></td>
	<td width="25%"><hdiits:select name="Subject" sort="false" mandatory="true"  captionid="Subject" bundle="${Lables}" validation="sel.isrequired" onchange="getSubSubjectFromSubject(this)">      
		<hdiits:option value="0"><fmt:message key="Select" bundle="${Lables}" /></hdiits:option>
		<c:forEach var="subjectList" items="${subjectList}">					
			<c:choose>
				<c:when test="${flag eq 'redirectView' and mainSubjectCode eq subjectList.subjectCode}">
					<hdiits:option selected="true" value="${subjectList.subjectCode}">${subjectList.subjectName}</hdiits:option>
				</c:when>
				<c:otherwise>
					<hdiits:option value="${subjectList.subjectCode}">${subjectList.subjectName}</hdiits:option>
				</c:otherwise>
			</c:choose>			
		</c:forEach></hdiits:select></td>
		
		
		
	<TD width="25%"><b><hdiits:caption captionid="Sub_Subject" bundle="${Lables}"/></b></td>
	<td width="25%"><hdiits:select sort="false" name="Sub_Subject"  mandatory="true"  captionid="Sub_Subject" bundle="${Lables}" validation="sel.isrequired">      
		<hdiits:option value="0"><fmt:message key="SelectSubFirst" bundle="${Lables}" /></hdiits:option>
		<c:if test="${flag eq 'redirectView'}">
		<hdiits:option selected="true" value="${subSubjectCode}">${subSubjectName}</hdiits:option>
		</c:if>
		</hdiits:select></td>
		
		
  </tr>
  <tr>
  	<td width="25%"><b><hdiits:caption captionid="Department" bundle="${Lables}"/></b></td>
    <td width="25%"><b>HOME</b></td>
  
    
    <TD width="25%"><b><hdiits:caption captionid="Year" bundle="${Lables}"/></b></td>
	<td width="25%"><hdiits:select sort="false" name="Year">      
		<hdiits:option value="0"><fmt:message key="Select" bundle="${Lables}" /></hdiits:option>
		<c:forEach var="Year" items="${Year}">
		<hdiits:option value="${Year}">${Year}</hdiits:option>
		</c:forEach>
	</hdiits:select></td>
	<c:if test="${flag eq 'redirectView'}">
	<script>
	document.LAQForm.Year.value = "${selectedYear}";
	</script>
	</c:if>
	
	</tr>
  
  <tr>
  <td width="25%"><b><hdiits:caption captionid="Vidhan_Sabha"bundle="${Lables}"/></b></td>
  <td width="25%"><hdiits:text name="VidhanSabha" maxlength="5" /></td>
  <c:if test="${flag eq 'redirectView'}">
  <script>
  document.LAQForm.VidhanSabha.value = "${vidhansabha}";
  </script>	
  </c:if>
  
  
  <td width="25%"><b><hdiits:caption captionid="Session"bundle="${Lables}"/></b></td>
  <td width="25%"><hdiits:text name="Session" maxlength="5" /></td>
  <c:if test="${flag eq 'redirectView'}">
  <script>
  document.LAQForm.Session.value = "${session}";
  </script>	
  </c:if>
  </tr>
 
  <tr>
  	<TD width="25%"><b><hdiits:caption captionid="Question_Raised_By" bundle="${Lables}"/></b></td>
	<td width="25%"><hdiits:select name="Question_Raised_By"  sort="false" mandatory="true"  captionid="Question_Raised_By" bundle="${Lables}" validation="sel.isrequired" onchange="getConstituencyFromName(this,1)">      
		<hdiits:option value="0"><fmt:message key="Select" bundle="${Lables}" /></hdiits:option>
		<c:forEach var="laqMemDtls" items="${laqMemDtls}">
			<c:choose>
				<c:when test="${flag eq 'redirectView' and queRaisedByMemCode eq laqMemDtls.memCode}">
					<hdiits:option selected="true" value="${laqMemDtls.memCode}">${laqMemDtls.mlaName}</hdiits:option>
				</c:when>
				<c:otherwise>
					<hdiits:option value="${laqMemDtls.memCode}">${laqMemDtls.mlaName}</hdiits:option>
				</c:otherwise>
			</c:choose>	
		</c:forEach>
		</hdiits:select></td>
		  
	
	<TD width="25%"><b><hdiits:caption captionid="Constituency" bundle="${Lables}"/></b></td>
	<td width="25%"><hdiits:select name="Constituency" sort="false" onchange="getMlaNameFromConstituency(this,1)">      
		<hdiits:option value="0"><fmt:message key="Select" bundle="${Lables}" /></hdiits:option>
		<c:forEach var="laqMemconstituencyName" items="${resultValue.laqMemDtls}">
			<c:choose>
				<c:when test="${flag eq 'redirectView' and queRaisedByMemCode eq laqMemconstituencyName.memCode}">
					<hdiits:option selected="true" value="${laqMemconstituencyName.memCode}">${laqMemconstituencyName.constituencyName}</hdiits:option>
				</c:when>
				<c:otherwise>
					<hdiits:option value="${laqMemconstituencyName.memCode}">${laqMemconstituencyName.constituencyName}</hdiits:option>
				</c:otherwise>
			</c:choose>	
		</c:forEach>
		</hdiits:select></td>	
		 
		
  </tr>
  
   <tr>
  <TD width="25%"><b><hdiits:caption captionid="To_Be_Rep_By" bundle="${Lables}"/></b></td>
	<td width="25%"><hdiits:select name="To_Be_Rep_By" sort="false" captionid="To_Be_Rep_By" bundle="${Lables}" onchange="getConstituencyFromName(this,2)">      
		<hdiits:option value="0"><fmt:message key="Select" bundle="${Lables}" /></hdiits:option>
		<c:forEach var="laqMemDtls" items="${resultValue.laqMemDtls}">
			<c:choose>
				<c:when test="${flag eq 'redirectView' and toBeRepliedByMemCode eq laqMemDtls.memCode}">
					<hdiits:option selected="true" value="${laqMemDtls.memCode}">${laqMemDtls.mlaName}</hdiits:option>
				</c:when>
				<c:otherwise>
					<hdiits:option value="${laqMemDtls.memCode}">${laqMemDtls.mlaName}</hdiits:option>
				</c:otherwise>
			</c:choose>	
		</c:forEach>
		</hdiits:select></td>
		
  
  <TD width="25%"><b><hdiits:caption captionid="Constituency" bundle="${Lables}"/></b></td>
	<td width="25%"><hdiits:select name="Rep_Constituency" sort="false" onchange="getMlaNameFromConstituency(this,2)">      
		<hdiits:option value="0"><fmt:message key="Select" bundle="${Lables}" /></hdiits:option>
		<c:forEach var="laqMemconstituencyName" items="${resultValue.laqMemDtls}">
			<c:choose>
				<c:when test="${flag eq 'redirectView' and toBeRepliedByMemCode eq laqMemconstituencyName.memCode}">
					<hdiits:option selected="true" value="${laqMemconstituencyName.memCode}">${laqMemconstituencyName.constituencyName}</hdiits:option>
				</c:when>
				<c:otherwise>
					<hdiits:option value="${laqMemconstituencyName.memCode}">${laqMemconstituencyName.constituencyName}</hdiits:option>
				</c:otherwise>
			</c:choose>	
		</c:forEach>
		</hdiits:select></td>
		
  </tr>
  
  <tr>
  	<TD width="25%"><b><hdiits:caption captionid="LAQ_Type" bundle="${Lables}"/></b></td>
	<td width="25%"><hdiits:select name="LAQ_Type" sort="false"  mandatory="true"  captionid="LAQ_Type" bundle="${Lables}" validation="sel.isrequired">      
		<hdiits:option value="0"><fmt:message key="Select" bundle="${Lables}" /></hdiits:option>
		<c:forEach var="LaqTypeListLoc" items="${LaqTypeList}">
			<c:choose>
				<c:when test="${flag eq 'redirectView' and laqTypeLookupName eq LaqTypeListLoc.lookupName}">
					<hdiits:option selected="true" value="${LaqTypeListLoc.lookupName}">${LaqTypeListLoc.lookupDesc}</hdiits:option>
				</c:when>
				<c:otherwise>
					<hdiits:option  value="${LaqTypeListLoc.lookupName}">${LaqTypeListLoc.lookupDesc}</hdiits:option>
				</c:otherwise>
			</c:choose>	
		</c:forEach>
		</hdiits:select></td>
			
		
	<TD width="25%"><b><hdiits:caption captionid="Name_of_HOD" bundle="${Lables}"/></b></td>
	<td width="25%"><hdiits:select name="Name_of_HOD" sort="false" multiple="true" onchange="getNameOfOfficeFromHodName(this,1)">      
		<hdiits:option value="0"><fmt:message key="Select" bundle="${Lables}" /></hdiits:option>
		<c:forEach var="nameOfHodList" items="${resultValue.nameOfHodList}">
			<c:set value="1" var="hodFlag"></c:set>
			<c:choose>
				<c:when test="${flag eq 'redirectView'}">
					<c:forEach var="hrGnlLaqDtlList" items="${resultValue.hrGnlLaqDtlList}">
						<c:if test="${hrGnlLaqDtlList.departmentId eq nameOfHodList.departmentId}">
							<c:set value="2" var="hodFlag"></c:set>
						</c:if>
					</c:forEach>
					<c:choose>
						<c:when test="${hodFlag eq 2}">
							<hdiits:option selected="true" value="${nameOfHodList.departmentId}">${nameOfHodList.depName}</hdiits:option>
						</c:when>
						<c:otherwise>
							<hdiits:option  value="${nameOfHodList.departmentId}">${nameOfHodList.depName}</hdiits:option>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<hdiits:option  value="${nameOfHodList.departmentId}">${nameOfHodList.depName}</hdiits:option>
				</c:otherwise>
			</c:choose>	
		</c:forEach>
		</hdiits:select></td>	
	
  </tr>
  
  <tr>
  <TD width="25%"><b><hdiits:caption captionid="Name_of_Office" bundle="${Lables}"/></b></td>
	<td width="25%"><hdiits:select name="Name_of_Office" sort="false" multiple="true" onchange="getSubOfficeFromOffice(this)">      
		<hdiits:option value="0"><fmt:message key="SelectNameOfHodFirst" bundle="${Lables}" /></hdiits:option>
		<c:if test="${flag eq 'redirectView'}">
		<c:forEach var="hrGnlLaqDtlList" items="${resultValue.hrGnlLaqDtlList}">
		<c:if test="${hrGnlLaqDtlList.officeLocId ne 0}">
		<hdiits:option selected="true" value="${hrGnlLaqDtlList.officeLocId}">${hrGnlLaqDtlList.officeLocName}</hdiits:option>
		</c:if>
		</c:forEach>
		</c:if>
		</hdiits:select></td>
		
		
  <TD width="25%"><b><hdiits:caption captionid="Sub_Office" bundle="${Lables}"/></b></td>
	<td width="25%"><hdiits:select name="Sub_Office" sort="false" multiple="true" >      
		<hdiits:option value="0"><fmt:message key="SelectNameOfOfficeFirst" bundle="${Lables}" /></hdiits:option>
		<c:if test="${flag eq 'redirectView'}">
		<c:forEach var="hrGnlLaqDtlList" items="${resultValue.hrGnlLaqDtlList}">
		<c:if test="${hrGnlLaqDtlList.subOfficeLocId ne 0}">
		<hdiits:option selected="true" value="${hrGnlLaqDtlList.subOfficeLocId}">${hrGnlLaqDtlList.subOfficeLocName}</hdiits:option>
		</c:if>
		</c:forEach>
		</c:if>
		</hdiits:select></td>
	
  </tr>
  
</TABLE>
</hdiits:fieldGroup>

<hdiits:fieldGroup bundle="${Lables}" expandable="true" id="Related_to" titleCaptionId="Related_to">
<TABLE class=tabtable align="center">  
	<tr>  
	<TD width="25%"><b><hdiits:caption captionid="Region" bundle="${Lables}"/></b></td>
	<td width="25%"><hdiits:select name="Region" sort="false" multiple="true" onchange="getDistrictFromRegion(this)">      
		<hdiits:option value="0"><fmt:message key="Select" bundle="${Lables}" /></hdiits:option>
		<c:forEach var="regionList" items="${resultValue.regionList}">
		<c:set value="1" var="regionFlag"></c:set>
			<c:choose>
				<c:when test="${flag eq 'redirectView'}">
					<c:forEach var="hrGnlLaqDtlList" items="${resultValue.hrGnlLaqDtlList}">
						<c:if test="${hrGnlLaqDtlList.regionCode eq regionList.regionCode}">
							<c:set value="2" var="regionFlag"></c:set>
						</c:if>
					</c:forEach>
					<c:choose>
						<c:when test="${regionFlag eq 2}">
							<hdiits:option selected="true" value="${regionList.regionCode}">${regionList.regionName}</hdiits:option>
						</c:when>
						<c:otherwise>
							<hdiits:option value="${regionList.regionCode}">${regionList.regionName}</hdiits:option>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<hdiits:option  value="${regionList.regionCode}">${regionList.regionName}</hdiits:option>
				</c:otherwise>
			</c:choose>	
		</c:forEach>
		</hdiits:select></td>	 
	
	<TD width="25%"><b><hdiits:caption captionid="District" bundle="${Lables}"/></b></td>
	<td width="25%"><hdiits:select name="District" sort="false" multiple="true" onchange="getTalukaFromDistrict(this)">      
		<hdiits:option value="0"><fmt:message key="SelectRegionFirst" bundle="${Lables}" /></hdiits:option>
		<c:if test="${flag eq 'redirectView'}">
		<c:forEach var="hrGnlLaqDtlList" items="${resultValue.hrGnlLaqDtlList}">
		<c:if test="${hrGnlLaqDtlList.districtId ne 0}">
		<hdiits:option selected="true" value="${hrGnlLaqDtlList.districtId}">${hrGnlLaqDtlList.districtName}</hdiits:option>
		</c:if>
		</c:forEach>
		</c:if>
		</hdiits:select></td>	
  </TR>
  
  <tr>
  <TD width="25%"><b><hdiits:caption captionid="Taluka" bundle="${Lables}"/></b></td>
  <td width="25%"><hdiits:select name="Taluka" sort="false" multiple="true" onchange="getVillageFromTaluka(this)">      
		<hdiits:option value="0"><fmt:message key="SelectDistrictFirst" bundle="${Lables}" /></hdiits:option>
		<c:if test="${flag eq 'redirectView'}">
		<c:forEach var="hrGnlLaqDtlList" items="${resultValue.hrGnlLaqDtlList}">
		<c:if test="${hrGnlLaqDtlList.talukaCode ne null}">
		<hdiits:option selected="true" value="${hrGnlLaqDtlList.talukaCode}">${hrGnlLaqDtlList.talukaName}</hdiits:option>
		</c:if>
		</c:forEach>
		</c:if>
		</hdiits:select></td>	
		
  <TD width="25%"><b><hdiits:caption captionid="City" bundle="${Lables}"/></b></td>
  <td width="25%"><hdiits:select name="City" sort="false" multiple="true">      
		<hdiits:option value="0"><fmt:message key="SelectDistrictFirst" bundle="${Lables}" /></hdiits:option>
		<c:if test="${flag eq 'redirectView'}">
		<c:forEach var="hrGnlLaqDtlList" items="${resultValue.hrGnlLaqDtlList}">
		<c:if test="${hrGnlLaqDtlList.cityCode ne null}">
		<hdiits:option selected="true" value="${hrGnlLaqDtlList.cityCode}">${hrGnlLaqDtlList.cityName}</hdiits:option>
		</c:if>
		</c:forEach>
		</c:if>
		</hdiits:select></td>	
  </tr>
  
  <tr>
  <TD width="25%"><b><hdiits:caption captionid="Village" bundle="${Lables}"/></b></td>
  <td width="25%"><hdiits:select name="Village" sort="false" multiple="true">      
		<hdiits:option value="0"><fmt:message key="SelectTalukaFirst" bundle="${Lables}" /></hdiits:option>
		<c:if test="${flag eq 'redirectView'}">
		<c:forEach var="hrGnlLaqDtlList" items="${resultValue.hrGnlLaqDtlList}">
		<c:if test="${hrGnlLaqDtlList.villageCode ne null}">
		<hdiits:option selected="true" value="${hrGnlLaqDtlList.villageCode}">${hrGnlLaqDtlList.villageName}</hdiits:option>
		</c:if>
		</c:forEach>
		</c:if>
		</hdiits:select></td>	
	</tr>
  
</TABLE>
</hdiits:fieldGroup>

</div>

<div id="tcontent2" class="tabcontent" tabno="1">


<hdiits:fieldGroup bundle="${Lables}" expandable="true" id="Que_Ans_Detail" titleCaptionId="Que_Ans_Detail">
<TABLE class=tabtable align="center">  

<tr>  
	<TD width="25%"><b><hdiits:caption captionid="Que_No" bundle="${Lables}"/></b></TD>
  	<TD width="25%"><hdiits:number name="questionNo" maxlength="5" mandatory="true" validation="txt.isrequired" captionid="Que_No" bundle="${Lables}"/></TD>
      <c:if test="${flag eq 'redirectView'}">
	  <script>
	  document.LAQForm.questionNo.value = "${questionNo}";
	  </script>	
	  </c:if>
	
    
    <TD width="25%"><b><hdiits:caption captionid="Pre_Que_NO" bundle="${Lables}"/></b></TD>
  	<TD width="25%"><hdiits:number name="prv_related_que" maxlength="5"/></TD>
  	  <c:if test="${flag eq 'redirectView'}">
	  <script>
	  document.LAQForm.prv_related_que.value = "${prvRelatedQueNo}";
	  </script>	
	  </c:if>
	 
</tr>
</TABLE>
<br>
<table id="Que_Ans_Dtls" style="" align="center" style="border-collapse: collapse;" borderColor="BLACK"  border=1  width="98%"> 

<tr bgcolor="#C9DFFF">
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="Question" bundle="${Lables}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="Answer" bundle="${Lables}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="Assurance" bundle="${Lables}" /></b></td>
</tr>

<tr align="center">
<td align="center">
<jsp:include page="/WEB-INF/jsp/hrms/gnl/LaqApplication/laqRichText.jsp">
<jsp:param name="richTextName" value="QuestionRichText"/>
</jsp:include>
<hdiits:jsField jsFunction="valRichText()" name="valRichText"  condition="validateRichText()"/>
</td>
		<c:if test="${flag eq 'redirectView'}">
		<script>
		var decoded_que_text = replace("${mainQuestion}",' ','+');  
	 	decoded_que_text=decodeBase64(decoded_que_text);
	 	enableDesignMode('rte'+'QuestionRichText', decoded_que_text, true);		
	 	document.LAQForm.elements['rte'+'QuestionRichText'].value = decoded_que_text;
		</script>	
		</c:if>
		
<td align="center">
<jsp:include page="/WEB-INF/jsp/hrms/gnl/LaqApplication/laqRichText.jsp">
<jsp:param name="richTextName" value="AnswerRichText" />
</jsp:include></td>
		<c:if test="${flag eq 'redirectView'}">
		<script>
		var decoded_ans_text = replace("${mainAnswer}",' ','+');  
	 	decoded_ans_text=decodeBase64(decoded_ans_text);
	 	enableDesignMode('rte'+'AnswerRichText', decoded_ans_text, false);	
	 	document.LAQForm.elements['rte'+'AnswerRichText'].value = decoded_ans_text;	
		</script>	
		</c:if>
<td align="center">
<jsp:include page="/WEB-INF/jsp/hrms/gnl/LaqApplication/laqRichText.jsp">
<jsp:param name="richTextName" value="AssuranceRichText" />
</jsp:include></td>
		<c:if test="${flag eq 'redirectView'}">
		<script>
		var decoded_assurance_text = replace("${mainAssurance}",' ','+');  
	 	decoded_assurance_text=decodeBase64(decoded_assurance_text);
	 	enableDesignMode('rte'+'AssuranceRichText', decoded_assurance_text, false);		
	 	document.LAQForm.elements['rte'+'AssuranceRichText'].value = decoded_assurance_text;	
		</script>	
		</c:if>
</tr>
</table>
<script>
//var decoded_ans_text = replace("${que}",' ','+');  
//decoded_ans_text=decodeBase64(decoded_ans_text);
//enableDesignMode('rte'+'QuestionRichText', decoded_ans_text, true);		
</script>

<table border='0' width="100%">
	<tr>
		<td>
			
			<!-- For attachment : Start-->	
			<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
			<jsp:param name="attachmentName" value="LAQQueAnsAttachment" />
			<jsp:param name="formName" value="LAQForm" />
			<jsp:param name="attachmentTitle" value="Attachment" />
			<jsp:param name="attachmentType" value="Document" />
			<jsp:param name="multiple" value="N" />     
			<jsp:param name="rowNumber" value="1" />                 
			</jsp:include>
		<!-- For attachment : End-->
		</td>
	</tr>
</table>
</hdiits:fieldGroup>

<br>
<table width="100%">
<TR bgColor=#386cb7>
    <TD class=fieldLabel align=middle colSpan=5><FONT color=#ffffff><STRONG><U></U></STRONG></FONT>
    </TD></TR>
    </table>
<TABLE class=tabtable align="center">  
<tr>  
	<TD width="25%"><b><hdiits:caption captionid="Supplementary_Que" bundle="${Lables}"/></b></TD>
  	<TD width="25%"><hdiits:radio name="Supplementary_Que" value="Yes" mandatory="true" validation="sel.isradio" errCaption="Supplementary Question" captionid="YES" bundle="${Lables}" onclick="AddSubIteamYes()"/>
    <hdiits:radio name="Supplementary_Que" value="No" mandatory="true" validation="sel.isradio" captionid="NO" bundle="${Lables}" onclick="AddSubIteamNo()"/></TD>
      <c:if test="${flag eq 'redirectView'}">
	  <script>
	  var ifSuppleQue = "${ifSupplementryQue}";
	  if(ifSuppleQue == 'Y')
	  document.LAQForm.Supplementary_Que[0].checked = true;
	  else
	  document.LAQForm.Supplementary_Que[1].checked = true;
	</script>	
	  </c:if>
    <td></td>
    <td></td>
</tr>
</TABLE>

<hdiits:fieldGroup bundle="${Lables}" expandable="true" id="SubItemLable" titleCaptionId="Sub_Item_Detail">
<table id="Sub_Item_Detail" style="Display:none" align="center" style="border-collapse: collapse;" borderColor="BLACK"  border=1  width="98%"> 

<tr bgcolor="#C9DFFF">
<td class="fieldLabel"  align="center"><hdiits:checkbox name="sub_check" value="0"  /></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="Sub_Item_Question" bundle="${Lables}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="Sub_Item_Answer" bundle="${Lables}" /></b></td>
</tr>
<tr align="center">
<td align="center"><hdiits:checkbox name="sub_check" value="1"  /></td>
<td align="center"><jsp:include page="/WEB-INF/jsp/hrms/gnl/LaqApplication/laqRichText.jsp">
<jsp:param name="richTextName" value="Sub_Item_Question_RichText" />
</jsp:include></td>
<td align="center"><jsp:include page="/WEB-INF/jsp/hrms/gnl/LaqApplication/laqRichText.jsp">
<jsp:param name="richTextName" value="Sub_Item_Answer_RichText" />
</jsp:include></td>
</tr>
</table>

<table border='0' width="100%" style="Display:none" id="Laq_Sub_Que_Ans_Attachment_Display">
	<tr>
		<td>
			
			<!-- For attachment : Start-->	
			<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
			<jsp:param name="attachmentName" value="LAQSubQueAnsAttachment" />
			<jsp:param name="formName" value="LAQForm" />
			<jsp:param name="attachmentTitle" value="Attachment" />
			<jsp:param name="attachmentType" value="Document" />
			<jsp:param name="multiple" value="Y" />
			<jsp:param name="rowNumber" value="1" />                
			</jsp:include>
		<!-- For attachment : End-->
		</td>
	</tr>
</table>
<br>
<br>

<table style="Display:none" align="center" id="AddDeleteButton">
<tr align="center">
<td><hdiits:button name="Add" type="button" value="Add" captionid="Add" bundle="${Lables}" onclick="AddUpdateSubItemData('Add',1)"/></td>
</tr>
</table>
<table style="Display:none" align="center" id="UpdateButton">
<tr align="center">
<td><hdiits:button name="Update" type="button" value="Update" captionid="Update" bundle="${Lables}" onclick="AddUpdateSubItemData('Update',1)"/></td>
</tr>
</table>
<table style="Display:none" align="center" id="ResetButton">
<tr align="center">
<td><hdiits:button name="Reset" type="button" value="Reset" captionid="Reset" bundle="${Lables}" onclick="ResetSubItemData()"/></td>
</tr>
</table>
<table id="subItemsViewTable" style="DISPLAY: none" align="center" style="border-collapse: collapse;" borderColor="BLACK"  border=1  width="98%"> 
<br></br>
<tr bgcolor="#C9DFFF">
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="Sub_Item_Question" bundle="${Lables}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="Sub_Item_Answer" bundle="${Lables}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="Action" bundle="${Lables}" /></b></td>
</tr>
</table>
<c:if test="${flag eq 'redirectView'}">
<c:forEach var="hrGnlLaqsubitemDtl" items="${hrGnlLaqsubitemDtlList}" varStatus="x">
<c:set var="curXMLFileName" value="${xmlFilePathNameForMulAddForSubIteam[x.index]}"></c:set>
<c:set var="subQuestion" value="Sub Question ${x.index+1}" />
<c:set var="subAnswer" value="Sub Answer ${x.index+1}" />
<c:set var="attachmentId" value="${hrGnlLaqsubitemDtl.cmnAttachmentMst.attachmentId}" />
<script type="text/javascript">					
				var xmlFileName = '${curXMLFileName}';
				var displayFieldA  = new Array('${subQuestion}','${subAnswer}');
				var attachmentId = "";
				if ('${attachmentId}' != 'null' && '${attachmentId}' != 'NULL' && '${attachmentId}' != '0')
				attachmentId = '${attachmentId}';
				addDBDataInTableAttachment('subItemsViewTable','addedEncXML',displayFieldA,xmlFileName,attachmentId,'editAddSubItemsDBDtls', 'deleteAddSubItemsDBDtls','');			
</script>
</c:forEach>
</c:if>     
</hdiits:fieldGroup>
<script>
document.getElementById('SubItemLable').style.display="none";	
</script>
</div>


<div id="tcontent3" class="tabcontent" tabno="2">

<hdiits:fieldGroup bundle="${Lables}" expandable="true" id="Status_dtls" titleCaptionId="Status_dtls">
<TABLE class=tabtable align="center">  
<tr>  
	<TD width="25%"><b><hdiits:caption captionid="Due_date_of_reply" bundle="${Lables}"/></b></TD>
  	<TD><hdiits:dateTime name="Due_date_of_reply" mandatory="true" captionid="Due_date_of_reply" bundle="${Lables}" validation="txt.isrequired" maxvalue="31/12/2999"/></TD>
  	<c:if test="${flag eq 'redirectView'}">
	<script>
	document.LAQForm.Due_date_of_reply.value = "${dueDateOfReply}";
	</script>	
	</c:if>
	
    <TD width="25%"><b><hdiits:caption captionid="Reply_date_in_assembly" bundle="${Lables}"/></b></TD>
  	<TD width="25%"><hdiits:dateTime name="Reply_date_in_assembly" caption="dateTime" captionid="Reply_date_in_assembly" bundle="${Lables}" maxvalue="31/12/2999"/>
    <c:if test="${flag eq 'redirectView'}">
	<script>
	document.LAQForm.Reply_date_in_assembly.value = "${replyDateInAssbly}";
	</script>	
	</c:if>
    </TD>
</tr>

<tr>  
	<TD width="25%"><b><hdiits:caption captionid="Priority_no" bundle="${Lables}"/></b></TD>
  	<TD width="25%"><hdiits:number name="Priority_no" maxlength="5"/></TD>
  	<c:if test="${flag eq 'redirectView'}">
	<script>
	document.LAQForm.Priority_no.value = "${priorityNo}";
	</script>	
	</c:if>
    
    <TD width="25%"><b><hdiits:caption captionid="No_of_copies" bundle="${Lables}"/></b></TD>
  	<TD width="25%"><hdiits:number name="No_of_copies" maxlength="5"/></TD>
  	<c:if test="${flag eq 'redirectView'}">
	<script>
	document.LAQForm.No_of_copies.value = "${noOfCopies}";
	</script>	
	</c:if>
</tr>

<tr>
<TD width="25%"><b><hdiits:caption captionid="LAQ_Status" bundle="${Lables}"/></b></td>
	<td width="25%"><hdiits:select name="LAQ_Status" sort="false">      
		<hdiits:option value="0"><fmt:message key="Select" bundle="${Lables}" /></hdiits:option>
		<c:forEach var="LaqStatus" items="${LaqStatus}">
			<c:choose>
				<c:when test="${flag eq 'redirectView' and laqStatusLookupName eq LaqStatus.lookupName}">
					<hdiits:option selected="true" value="${LaqStatus.lookupName}">${LaqStatus.lookupDesc}</hdiits:option>
				</c:when>
				<c:otherwise>
					<hdiits:option value="${LaqStatus.lookupName}">${LaqStatus.lookupDesc}</hdiits:option>
				</c:otherwise>
			</c:choose>	
		</c:forEach>
		</hdiits:select></td>	
		
	<TD width="25%"><b><hdiits:caption captionid="Revised_date_in_assembly" bundle="${Lables}"/></b></TD>
  	<TD width="25%"><hdiits:dateTime name="Revised_date_in_assembly" caption="dateTime" captionid="Revised_date_in_assembly" bundle="${Lables}" maxvalue="31/12/2999"/>
	<c:if test="${flag eq 'redirectView'}">
	<script>
	document.LAQForm.Revised_date_in_assembly.value = "${revisedDateInAssbly}";
	</script>	
	</c:if>
</tr>
</TABLE>
</hdiits:fieldGroup>

</div>

<div id="tcontent4" class="tabcontent" tabno="3">

<TABLE class=tabtable align="center">  
<tr>  
	<TD width="25%"><b><hdiits:caption captionid="Discussed_In_Assembly" bundle="${Lables}"/></b></TD>
  	<TD><hdiits:radio name="Discussed_In_Assembly" value="Yes" captionid="YES" bundle="${Lables}" onclick="discussedInAssemblyYes()"/>
    <hdiits:radio name="Discussed_In_Assembly" value="No" default="No" captionid="NO" bundle="${Lables}" onclick="discussedInAssemblyNo()"/></TD>
    <c:if test="${flag eq 'redirectView'}">
	<script>
	var ifdiscussInAssbly = "${ifDiscussedInAssbly}";
	if(ifdiscussInAssbly == 'Y')
	document.LAQForm.Discussed_In_Assembly[0].checked = true;
	else
	document.LAQForm.Discussed_In_Assembly[1].checked = true;
	</script>	
	</c:if>
</tr>
</TABLE>


<hdiits:fieldGroup bundle="${Lables}" expandable="true" id="ReplyDtlsLable" titleCaptionId="Reply_dtls">
<TABLE class=tabtable align="center" style="Display:none" id="ReplyDtls">  
<tr>  
	<TD width="25%"><b><hdiits:caption captionid="Reply_Date" bundle="${Lables}"/></b></TD>
  	<TD width="25%"><hdiits:dateTime name="Reply_dtls_Reply_date" caption="dateTime" captionid="Reply_Date" bundle="${Lables}" maxvalue="31/12/2999"/>
    </TD>
    <c:if test="${flag eq 'redirectView'}">
	<script>
	document.LAQForm.Reply_dtls_Reply_date.value = "${replyDate}";
	</script>	
	</c:if>
	
    <TD width="25%"><b><hdiits:caption captionid="Reply_given_in_assembly" bundle="${Lables}"/></b></TD>
  	<TD width="25%"><hdiits:textarea name="Reply_given_in_assembly"></hdiits:textarea>
    </TD>
    <c:if test="${flag eq 'redirectView'}">
	<script>
	document.LAQForm.Reply_given_in_assembly.value = "${hrGnlLaqreplyDtl.replyGivenInAssbly}";
	</script>	
	</c:if>
    
</tr>
<tr>  
	<TD width="25%"><b><hdiits:caption captionid="Reply_given_by" bundle="${Lables}"/></b></TD>
  	<TD width="25%"><hdiits:text name="Reply_given_by"/></TD>
    <c:if test="${flag eq 'redirectView'}">
	<script>
	document.LAQForm.Reply_given_by.value = "${hrGnlLaqreplyDtl.replyGivenBy}";
	</script>	
	</c:if>
    <td></td>
    <td></td>
</tr>
</TABLE>
</hdiits:fieldGroup>
<script>
document.getElementById('ReplyDtlsLable').style.display="none";	
</script>
<br>
<table width="100%" id="ActionTakenLine" style="Display:none">
<TR bgColor=#386cb7>
    <TD class=fieldLabel align=middle colSpan=5><FONT color=#ffffff><STRONG><U></U></STRONG></FONT>
    </TD></TR>
    </table>
 <TABLE class=tabtable align="center">  
 
<tr>    
 <TD width="25%" style="Display:none" id="ActionTakenDisplay1"><b><hdiits:caption captionid="Action_To_Be_Taken" bundle="${Lables}"/></b></TD>
  	<TD width="25%" style="Display:none" id="ActionTakenDisplay2"><hdiits:radio name="Action_To_Be_Taken" value="Yes" captionid="YES" bundle="${Lables}" onclick="ActionTakenYes()"/>
    <hdiits:radio name="Action_To_Be_Taken" value="No" default="No" captionid="NO" bundle="${Lables}" onclick="ActionTakenNo()"/></TD>
    <c:if test="${flag eq 'redirectView'}">
	<script>
	var ifActionTaken = "${hrGnlLaqreplyDtl.ifActionTaken}";
	if(ifActionTaken == 'Y') 
	document.LAQForm.Action_To_Be_Taken[0].checked = true;
	else
	document.LAQForm.Action_To_Be_Taken[1].checked = true;
	</script>	
	</c:if>
   
    <td></td>
    <td></td>
</tr>
</TABLE>

<hdiits:fieldGroup bundle="${Lables}" expandable="true" id="ActionDtlsLable" titleCaptionId="Action_To_Be_Taken_By">
<TABLE class=tabtable align="center" style="Display:none" id="ActionDtls">  
<tr>
	<td width="25%"><b><hdiits:caption captionid="Assurance_Action_To_Be_Taken" bundle="${Lables}"/></b></td>
  	<td width="92%"><hdiits:textarea name="Assurance_Action_To_Be_Taken" cols="60"></hdiits:textarea></td>
</tr>
</table>
<TABLE class=tabtable align="center" style="Display:none" id="ActionOtherDtls">  
<tr>
<TD width="25%"><b><hdiits:caption captionid="Name_of_HOD" bundle="${Lables}"/></b></td>
	<td width="25%"><hdiits:select name="Action_Name_of_HOD" onchange="getNameOfOfficeFromHodName(this,2)">      
		<hdiits:option value="0"><fmt:message key="Select" bundle="${Lables}" /></hdiits:option>
		<c:forEach var="nameOfHodList" items="${resultValue.nameOfHodList}">
				<hdiits:option  value="${nameOfHodList.departmentId}">${nameOfHodList.depName}</hdiits:option>
				</c:forEach>
		</hdiits:select></td>
			 
<TD width="25%"><b><hdiits:caption captionid="Office" bundle="${Lables}"/></b></td>
	<td width="25%"><hdiits:select name="Office">      
		<hdiits:option value="0"><fmt:message key="SelectNameOfHodFirst" bundle="${Lables}" /></hdiits:option>
		</hdiits:select></td>	
</tr>

<tr>
<TD width="25%"><b><hdiits:caption captionid="Time_Limit" bundle="${Lables}"/></b></td>
<td width="25%"><hdiits:dateTime name="action_timeLimit"  caption="dateTime" captionid="Time_Limit" bundle="${Lables}" maxvalue="31/12/2999"></hdiits:dateTime></td>

<TD width="25%"><b><hdiits:caption captionid="Status" bundle="${Lables}"/></b></td>
	<td width="25%"><hdiits:select name="Status" sort="false">      
		<hdiits:option value="0"><fmt:message key="Select" bundle="${Lables}" /></hdiits:option>
		<c:forEach var="LaqStatus" items="${resultValue.LaqStatus}">
		<hdiits:option value="${LaqStatus.lookupName}">${LaqStatus.lookupDesc}</hdiits:option>
	</c:forEach>
		</hdiits:select></td>	
</tr>
</TABLE>


<hdiits:fieldGroup bundle="${Lables}" id="ActionTakenReportLable" titleCaptionId="Action_Taken_Report" expandable="false">
<TABLE class=tabtable align="center" style="Display:none" id="ActionTakenReport">
<tr>
<td width="25%"><b><hdiits:caption captionid="Description" bundle="${Lables}"/></b></td>
 <td width="25%"><hdiits:textarea name="Description" cols="30"></hdiits:textarea></td>
 
<TD width="25%"><b><hdiits:caption captionid="Date_Of_Completion" bundle="${Lables}"/></b></td>
<td width="25%"><hdiits:dateTime name="Date_Of_Completion" caption="dateTime" captionid="Date_Of_Completion" bundle="${Lables}" maxvalue="31/12/2999"></hdiits:dateTime></td>

</tr>
</TABLE>
</hdiits:fieldGroup>
<table style="Display:none" align="center" id="ActionAddButton">
<br>
<tr align="center">
<td><hdiits:button name="AddAction" type="button" captionid="Add"  bundle="${Lables}" onclick="AddActionTakenDtls('Add')"/></td>
</tr>
</table>
<table style="Display:none" align="center" id="ActionUpdateButton">
<br>
<tr align="center">
<td><hdiits:button name="UpdateAction" type="button" captionid="Update" bundle="${Lables}" onclick="AddActionTakenDtls('Update')"/></td>
</tr>
</table>



<table id="ActionAddDataTable" style="Display:none" align="center" style="border-collapse: collapse;" borderColor="BLACK"  border=1  width="98%"> 
<br>
<tr bgcolor="#C9DFFF">
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="Assurance_Action_To_Be_Taken" bundle="${Lables}"/></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="Name_of_HOD" bundle="${Lables}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="Office" bundle="${Lables}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="Time_Limit" bundle="${Lables}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="Status" bundle="${Lables}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="Description" bundle="${Lables}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="Date_Of_Completion" bundle="${Lables}" /></b></td>
<td class="fieldLabel"  align="center"><b><hdiits:caption captionid="Action" bundle="${Lables}" /></b></td>
</tr>
<c:if test="${flag eq 'redirectView'}">
<c:forEach items="${hrGnlLaqactionDtlList}" var="hrGnlLaqactionDtlList" varStatus="x">
<tr id='${hrGnlLaqactionDtlList.rowId}'>
<td><c:out value="${hrGnlLaqactionDtlList.assuranceAction}"/></td>
<td><c:out value="${hrGnlLaqactionDtlList.orgDepartmentMstDepName}"/></td>
<td><c:out value="${hrGnlLaqactionDtlList.cmnLocationMstByHodOfficeLocName}"/></td>
<td><c:out value="${hrGnlLaqactionDtlList.timeLimit}"/></td>
<td><c:out value="${hrGnlLaqactionDtlList.statusLookupName}"/></td>
<td><c:out value="${hrGnlLaqactionDtlList.discription}"/></td>
<td><c:out value="${hrGnlLaqactionDtlList.dateOfCompletion}"/></td>
<td><hdiits:a href="javascript:deleteSelectedAction('${hrGnlLaqactionDtlList.actionId}','${hrGnlLaqactionDtlList.rowId}')" bundle="${Lables}" captionid="Delete" ></hdiits:a></td>
</tr>
</c:forEach>
</c:if>    
</table>
</hdiits:fieldGroup>
<script>
document.getElementById('ActionDtlsLable').style.display="none";	
</script>


</div>

<hdiits:hidden name="Question" />
<hdiits:hidden name="Answer"/>
<hdiits:hidden name="flag"/>
<hdiits:hidden name="rowId"/>
<hdiits:hidden name="srNo" id="srNo"></hdiits:hidden>
<hdiits:hidden name="defaultValue" id="srNo" default="-"></hdiits:hidden>
<hdiits:hidden name="actionDtlFlag" id="actionDtlFlag"></hdiits:hidden>
<hdiits:hidden name="fileId" id="fileId" default="${fileId}"></hdiits:hidden>
<hdiits:hidden name="actionRowId" id="actionRowId"></hdiits:hidden>
<hdiits:hidden name="hiddenSubIteamId" id="hiddenSubIteamId"></hdiits:hidden>

<c:if test="${flag eq 'redirectView'}">
<script>
var ifSuppleQue = "${ifSupplementryQue}";
if(ifSuppleQue == 'Y')
{
	AddSubIteamYes();
	document.getElementById('subItemsViewTable').style.display="";
}
var ifdiscussInAssbly = "${ifDiscussedInAssbly}";
if(ifdiscussInAssbly == 'Y')
discussedInAssemblyYes();
var ifActionTaken = "${hrGnlLaqreplyDtl.ifActionTaken}";
if(ifActionTaken == 'Y') 
{
	ActionTakenYes();
	document.getElementById('ActionAddDataTable').style.display="";
}
</script>	
</c:if>

<hdiits:hidden name="Converted_text" id="Converted_text"/>
<jsp:include page="../../../core/tabnavigation.jsp" />
		<script type="text/javascript">
		initializetabcontent("maintab")
		</script>
<hdiits:validate controlNames="test" locale='<%=(String)session.getAttribute("locale")%>'/>	

</hdiits:form>


<%}catch(Exception e)
  {
  e.printStackTrace();
  }
%>