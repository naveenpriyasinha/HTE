<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="/WEB-INF/jsp/core/include.jsp"%>
<%@ include file="/WEB-INF/jsp/common/cmnMsg.jsp"%>


<%@page import="com.tcs.sgv.common.utils.DateUtility"%>
<fmt:setBundle basename="resources.trng.trschLables" var="trschLables" 	scope="request" />
<fmt:setBundle basename="resources.trng.TrngAttendedLables" var="trngAttendedLables" scope="request" /> 
<fmt:setBundle basename="resources.trng.NominationLables" var="nmLables" scope="request" />
			
<fmt:setBundle basename="resources.trng.AllotmentLables" var="allotLables" scope="request" />			
<fmt:message key="TR.CLSZALT" bundle="${trschLables}" var="BATCH"/>
<fmt:message key="TR.SCHR" bundle="${trschLables}" var="schr"/>
<fmt:message key="TR.SDED" bundle="${trschLables}" var="sded"/>
<fmt:message key="TR.SD" bundle="${trschLables}" var="sd"/>
<fmt:message key="TR.CDDT" bundle="${trschLables}" var="cddt"/>
<fmt:message key="TR.TOTF" bundle="${trschLables}"  var="totf" />
<fmt:message key="TR.TTOTF" bundle="${trschLables}" var="ttotf" />
<fmt:message key="TR.MSG" bundle="${trschLables}" var="enddate" />
<fmt:message key="TR.MSG1" bundle="${trschLables}" var="enddate1" />
<fmt:message key="TR.NOMIDT" bundle="${trschLables}" var="NOMIDT" />


<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" 	src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript"  src="<c:url value="/script/common/attachment.js"/>"> </script>
 <script type="text/javascript" src="<c:url value="/script/eis/Address.js"/>"></script>
 <script type="text/javascript" src="script/common/address.js"></script>
<script type="text/javascript" src="script/hrms/training/scheduleMst.js"></script>

 <c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="trainingMstList" value="${resValue.trainingMstList}" > </c:set>
<c:set var="lstTrainingType" value="${resValue.lstTrainingType}" > </c:set>

 <c:set var="trainerMstList" value="${resValue.trainerMstList}" > </c:set>
 <c:set var="subjectMstList" value="${resValue.subjectMstList}" > </c:set>
 <c:set var="trngModeLookup" value="${resValue.trngModeLookup}" > </c:set>
 <c:set var="trngLoc" value="${resValue.trngLoc}" > </c:set>
  <c:set var="trngSubMpgList" value="${resValue.trngSubMpgList}" > </c:set>
  <c:set var="xmlKeyList" value="${resValue.xmlKeyList1}" > </c:set>
  <c:set var="schId" value="${resValue.scheduleId}" > </c:set>
  <c:set var="show3rdTab" value="${resValue.show3rdTab}" > </c:set>
  <c:set var="show4thTab" value="${resValue.show4thTab}" > </c:set>
  <c:set var="locList11" value="${resValue.locList11}" > </c:set>
  <c:set var="loctdis" value="${resValue.loctdis}" > </c:set>
    
<c:set var="msg" value="${resValue.msg}"></c:set>  

<c:set var="editFlag" value="${resValue.editFlag}" />
<c:set var="readOnlyAfterResubmit" value="${resValue.readOnlyAfterResubmit}" />


<c:set var="defaults" value="${resValue.defaults}" />
<c:set var="today" value="${resValue.today}" />

<c:set var="scheduleId" value="${resValue.trainingId}" />
	
 <c:set var="contextPath" scope="request" value="${pageContext.request.contextPath}"></c:set>
 
 	<c:set var="readOnly" value="false"/>
<c:if test="${editFlag eq 'Y'}">
	<c:set var="readOnly" value="true"/>		
</c:if>
 
 
 
  <script type="text/javascript" src="script/hod/ps/caseoccurance.js"></script>
<hdiits:form name="SchMST" action="hdiits.htm" validate="true" method="post" encType="multipart/form-data">


<hdiits:hidden name="wffileId_hidden"/>
<hdiits:hidden name="actionFlag" default="insertSchmstdtl" />
<hdiits:hidden name="editFlag" default="${editFlag}" />
<hdiits:hidden name="schId" default="${schId}" />
<fmt:formatDate value="${resValue.sysDate}" pattern="dd/MM/yyyy HH:mm" dateStyle="medium" var="sysDate"/>
<hdiits:hidden name="systemDate" default="${sysDate}" /> 
<fmt:formatDate value="${resValue.sysDate}" pattern="dd/MM/yyyy" dateStyle="medium" var="sysDateChck"/>
<hdiits:hidden name="sysDateHidd" default="${sysDateChck}" /> 


<hdiits:hidden name="show3rdTab" default="${show3rdTab}"/>

<hdiits:hidden name="srNo" />
<hdiits:hidden name="classDateHidd" id="classDateHidd"/>	
<hdiits:hidden name="timeFromHidd" id="timeFromHidd"/>	
<hdiits:hidden name="timeToHidd" id="timeToHidd"/>	
<hdiits:hidden name="externalFlag" id="externalFlag"/>
<c:out value="${resValue.msg}"/>

<c:choose>
<c:when test="${readOnlyAfterResubmit eq 'true'}">
	<c:set var="attread" value="Y"></c:set>
	<c:set var="addre" value="Yes"/>
</c:when>
<c:otherwise>
		<c:set var="attread" value="N"></c:set>
		<c:set var="addre" value="No"/>
</c:otherwise>
</c:choose>

<script type="text/javascript">



var trainingList = new Array();


function getTrainingNames(val)
{
	var actionFlag = "getTrainingNames";
     

	xmlHttp=GetXmlHttpObject();

	if(xmlHttp == null)
	{
	alert('Your browser does not support AJAX!');
	}
  
  var url= '${contextPath}' + '/hrms.htm?actionFlag=' + actionFlag + '&trngType=' + val.value;
  

  
  showProgressbar("Please Wait...");
  
  xmlHttp.onreadystatechange=trainingNameResponse;
	
		xmlHttp.open("POST",encodeURI(url),true);
		xmlHttp.send(encodeURIComponent(null));

}

function trainingNameResponse()
{
	if (xmlHttp.readyState == 4) 
			{     
				
				if (xmlHttp.status == 200) 
				{ 
  				
					var XMLDoc=xmlHttp.responseXML;
					if(XMLDoc!= null)
				    	{
				    	var entries = XMLDoc.getElementsByTagName('training');	
				    	var targetCombo = document.getElementById('lstSchtrn');
				    	
				    	 if(targetCombo.length > 1)
				    		  {
				    		     clearListSch(targetCombo);
				    		  }
				    	
				    	trainingList = new Array();
				    	for ( var i = 0 ; i < entries.length ; i++ )
	     				{
	     				
							trnglisttuple = entries[i].childNodes[0].text+ ',' +entries[i].childNodes[2].text;     				
	     					trainingList[i]= trnglisttuple;
	     					text=entries[i].childNodes[0].text;   
	     				    value=entries[i].childNodes[1].text;
	     				   
	     					var y=document.createElement('option');
		 					y.text=value;
							y.value=text;
							
							try
	   						{
	    						targetCombo.add(y,null); 
	   						}
	 						catch(ex)
	   						{
   			 
	   			 				targetCombo.add(y); 
	   						}     					
	     					
	           			}
				    	
				    	}
					
					
					

				}
				else 
				{  
			
					alert("ERROR");
				}
			}


hideProgressbar();
}
function addRecord() 
{
	  if (xmlHttp.readyState == 4)
	  { 				
		  var displayFieldArray;
		  displayFieldArray = new Array('classDate','trngSubject', 'Trainer', 'Timefrom','Timeto','Classrmno');
		  addDataInTable('txnAddSch','encXML', displayFieldArray,'editRecord','deleteRecord1');	
	      resetData();	
	   }	
}
function deleteRecord1(rowId) 
{
		var delCap = "";
		var delNtAlllowed = "";
		try
		{
			delCap = cmnLblArray[2];
			delNtAlllowed = cmnLblArray[3];
		}
		catch (e)
		{
			delNtAlllowed = "You can't delete record,\n Because you have open one record for update";
			delCap = "Are you sure you want to delete this record ?";
		}
		
		if(updateRow != null)
		{
			alert (delNtAlllowed);
			return false;
		}
		var answer = confirm(delCap);
		if(answer)
		{	
			var delRow = document.getElementById(rowId);
			delRow.parentNode.deleteRow(delRow.rowIndex);		
		}
	deleteFromDuplicateArrayLoc('txnAddSch',rowId);
	return answer;
	}
	

function updateRecord() {

	  if (xmlHttp.readyState == 4) { 
	  var displayFieldArray;
      displayFieldArray = new Array('classDate','trngSubject', 'Trainer', 'Timefrom','Timeto','Classrmno')
	  updateDataInTable('encXML', displayFieldArray);
	  document.getElementById('btnAdd').style.display='';
	  document.getElementById('btnUpdate').style.display='none';    
	  resetData();
	   	  }
	}

function populateForm() 
{

	  if (xmlHttp.readyState == 4) 
	  { 	
     	var decXML = xmlHttp.responseText;
		var xmlDOM = getDOMFromXML(decXML);

		if(getXPathValueFromDOM(xmlDOM, 'classDate')!= null)
		{ 
		        var datetimearray = new Array();


				var dateTime = getXPathValueFromDOM(xmlDOM, 'classDate');
				datetimearray= getDateAndTimeFromDateObj(dateTime)
		        document.SchMST.classDate.value = 	datetimearray[0] ;
		}  
		
		
		
		if(getXPathValueFromDOM(xmlDOM, 'hrTrSubjectMst/subjectId')!= null)      	
		document.SchMST.trngSubject.value = getXPathValueFromDOM(xmlDOM, 'hrTrSubjectMst/subjectId');
		
		if(getXPathValueFromDOM(xmlDOM, 'hrTrTrainerMst/trainerId')!= null)      	
		document.SchMST.Trainer.value = getXPathValueFromDOM(xmlDOM, 'hrTrTrainerMst/trainerId');
		
		if(getXPathValueFromDOM(xmlDOM, 'timeFrom')!= null)  {		
		
		     var datetimearray = new Array();
		       var dateTime = getXPathValueFromDOM(xmlDOM, 'timeFrom');
				datetimearray= getDateAndTimeFromDateObj(dateTime)
		        
			document.SchMST.Timefrom.value = datetimearray[1] ;
		}
		
		if(getXPathValueFromDOM(xmlDOM, 'timeTo')!= null) {
		 var datetimearray = new Array();
		  var dateTime = getXPathValueFromDOM(xmlDOM, 'timeTo');
				datetimearray= getDateAndTimeFromDateObj(dateTime)
		        
		        
			document.SchMST.Timeto.value = datetimearray[1];
			}
		
		if(getXPathValueFromDOM(xmlDOM, 'classRoomNo')!= null) 
		document.SchMST.Classrmno.value= getXPathValueFromDOM(xmlDOM, 'classRoomNo'); 
		
		if(getXPathValueFromDOM(xmlDOM, 'srNo')!= null) 
		document.SchMST.srNo.value= getXPathValueFromDOM(xmlDOM, 'srNo'); 
		
		 
		document.getElementById('btnAdd').style.display='none';
	    document.getElementById('btnUpdate').style.display='';        		   		    
	   }
}

function deleteDBRecord1(rowId)
	{
		var delCap = "";
		var delNtAlllowed = "";
		try
		{
			delCap = cmnLblArray[2];
			delNtAlllowed = cmnLblArray[3];
		}
		catch (e)
		{
			delNtAlllowed = "You can't delete record,\n Because you have open one record for update";
			delCap = "Are you sure you want to delete this record ?";
		}
		if(updateRow != null)
		{
			alert (delNtAlllowed);
			return false;
		}
		var answer = confirm(delCap);
		if(answer)
		{
			var delRow 	= document.getElementById(rowId);
			var content = delRow.cells[0].innerHTML;
			showAlert(content);
			content = content.replace(".xml_N",".xml_D");
			content = content.replace(".xml_U",".xml_D");
			delRow.cells[0].innerHTML = content;					
			delRow.style.display='none';
		}
		deleteFromDuplicateArrayLoc('txnAddSch',rowId);
	return answer;	
	}
function verifyDuplicateLoc(tableName,elementNameArray,elementNameArray1,hiddenField,duplicateAlert,cnt)
{
	if(cnt)
		incr = cnt; 
	else
		incr = counter;
		
	var value="";
	var value1="";
	var	rowId ;
	for(var y=0;y<elementNameArray.length;y++)
	{
		value=value+trim(document.getElementsByName(elementNameArray[y])[0].value);
		if(y<elementNameArray.length-1)
		{
			value=value+",";
		}
	}
	for(var y=0;y<elementNameArray1.length;y++)
	{
		value1=value1+trim(document.getElementsByName(elementNameArray1[y])[0].value);
		if(y<elementNameArray1.length-1)
		{
			value1=value1+",";
		}
	}

	if(arrMulti[tableName] && arrMultiLoc[tableName])
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
						alert("<fmt:message key="TR.SMRC" bundle="${trschLables}" />");			
					}
				}
				isPresent=true;
				return false;
			}
		}
		for(x in arrMultiLoc[tableName])
		{
			if(value1==arrMultiLoc[tableName][x])
			{
				if(duplicateAlert!=null)
				{
					alert("<fmt:message key="TR.DPTR" bundle="${trschLables}" />");
				}
				else
				{
					var duplicateRecord=cmnLblArray[6];
					if(duplicateRecord)
					{
						alert("<fmt:message key="TR.DPTR" bundle="${trschLables}" />");
					}
					else
					{
						alert("<fmt:message key="TR.DPTR" bundle="${trschLables}" />");
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
				arrMultiLoc[tableName][rowToUpdate]=value1;
				rowToUpdate = null;
				return true;
			}
			else
			{
				rowId = 'row' + hiddenField + incr;
				arrMulti[tableName][rowId]=value;
				arrMultiLoc[tableName][rowId]=value1;
				return true;
			}
		}
	}
	else
	{
		rowId = 'row' + hiddenField + incr;
		arrMulti[tableName]=new Array();
		arrMulti[tableName][rowId]=value;
		arrMultiLoc[tableName]=new Array();
		arrMultiLoc[tableName][rowId]=value1;
		return true;
	}
}
</script>
	
	
	
<div id="tabmenu">
   <ul id="maintab" class="shadetabs">
   <li  class="selected"><a href="#"  rel="tcontent1"><hdiits:caption captionid="TR.SCHEDULEDETAILS" bundle="${trschLables}"/></a></li>
   <li><a href="#" rel="tcontent2"><hdiits:caption captionid="TR.SeatAllotment" bundle="${allotLables}"></hdiits:caption>  </a></li>
	<c:if test="${show3rdTab eq 'Y'}">
   <li><a href="#" rel="tcontent3"><hdiits:caption captionid="TR.NOM" bundle="${allotLables}"></hdiits:caption>  </a></li>   	
   	</c:if>		
   	<c:if test="${show4thTab eq 'Y'}">
   <li><a href="#" rel="tcontent4"><hdiits:caption captionid="TR.APPNOM" bundle="${allotLables}"></hdiits:caption>  </a></li>   	
   	</c:if>		
   </ul>
</div>



<div class="tabcontentstyle">


	<div id="tcontent1" class="tabcontent" tabno="0">


<table class="tabtable">
   <TR>
    		<TD class="fieldLabel" width="25%"><hdiits:caption captionid="TR.ORDERNO" bundle="${trschLables}"/></TD>
			
    		<TD width="25%"><hdiits:text name="txtSchOrderno" 
                    captionid="TR.ORDERNO"  bundle="${trschLables}" validation="txt.isrequired" mandatory="true" maxlength="50" default="${defaults.orderNo}" readonly="${readOnlyAfterResubmit}"/>  
 			</TD>
    		<TD class="fieldLabel" width="25%">
			<hdiits:caption captionid="TR.SCHRCVDATE" bundle="${trschLables}"/>&nbsp;<hdiits:caption captionid="TR.DateFormat" bundle="${trschLables}" captionLang="single"/></TD>
    		<TD width="25%"><hdiits:dateTime name="txtSchrcvdate"  captionid="TR.SCHRCVDATE" bundle="${trschLables}" validation="txt.isrequired" mandatory="true" default="${defaults.schReceivedDt}"  disabled="${readOnlyAfterResubmit}" /></TD>   
   </TR>
     
  		<TR>
		 

		 
		 <TD class="fieldLabel" width="25%"><hdiits:caption captionid="TR.TRAININGTYPE" bundle="${trschLables}"/></TD>
		    <TD>
		    <hdiits:select name="lstSchtyp"  captionid="TR.TRAININGTYPE" bundle="${trschLables}"  validation="sel.isrequired" mandatory="true" sort="false" onchange="getTrainingNames(this)" readonly="${readOnly}">
		    
		   <c:if test="${editFlag ne 'Y'}">
		    <hdiits:option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" bundle="${trschLables}"/></hdiits:option>
	       	 <c:forEach var="trngType" items="${lstTrainingType}">
					<option value='<c:out value="${trngType.lookupId}"/>'><c:out value="${trngType.lookupDesc}" /></option>
			</c:forEach>
		  </c:if>
		  
		  <c:if test="${editFlag eq 'Y'}">
		    <option value='<c:out value="${defaults.hrTrTrainingMst.cmnLookupMstTrainingTypeLookupId.lookupName}"/>' ><c:out value="${defaults.hrTrTrainingMst.cmnLookupMstTrainingTypeLookupId.lookupName}" /></option>
			
		  </c:if>
			
						
		   </hdiits:select>    
		  </TD>
		 
		 
		 
		  
		  <TD class="fieldLabel" width="25%"><hdiits:caption captionid="TR.TRAININGNAME" bundle="${trschLables}"/></TD>
		    <TD>
		    <hdiits:select name="lstSchtrn"  captionid="TR.TRAININGNAME" bundle="${trschLables}"  validation="sel.isrequired" mandatory="true" sort="false" default="${defaults.hrTrTrainingMst.trainingMstId}"  readonly="${readOnly}">
		   
		   <c:if test="${editFlag ne 'Y'}"> 
		  <hdiits:option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" bundle="${trschLables}"/></hdiits:option>
	        </c:if>
		  
		 	 <c:if test="${editFlag eq 'Y'}">
			<option value='<c:out value="${defaults.hrTrTrainingMst.trainingName}"/>'><c:out value="${defaults.hrTrTrainingMst.trainingName}" /></option>
			</c:if>
		  
						
		   </hdiits:select>    
		  </TD>
		  
		   </TR>
			<tr>
		  
		  
		   <TD class="fieldLabel" width="25%">
			<hdiits:caption captionid="TR.STARTDATE" bundle="${trschLables}"/>&nbsp;
			<hdiits:caption captionid="TR.DateFormat" bundle="${trschLables}" captionLang="single"/></TD>
			<TD class="fieldLabel" width="25%">
			 <hdiits:dateTime name="txtSchstdate" 
                    captionid="TR.STARTDATE"  bundle="${trschLables}" maxvalue="01/01/9999" validation="txt.isrequired" mandatory="true" default="${defaults.startDt}" onblur="checkStartDate('${schr}','${sded}')" disabled="${readOnlyAfterResubmit}"/>  </TD>
                   
			<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.ENDDATE" bundle="${trschLables}"/>&nbsp;
			<hdiits:caption captionid="TR.DateFormat" bundle="${trschLables}" captionLang="single"/></td>
			<td class="fieldLabel width="25%"><hdiits:dateTime name="txtScheddate" 
                    captionid="TR.DateFormat"  bundle="${trschLables}"  maxvalue="01/01/9999" validation="txt.isrequired" mandatory="true" default="${defaults.endDt}" onblur="checkEndDate('${enddate}','${sd}')" disabled="${readOnlyAfterResubmit}"/> </td>
                    
        

        	 </TR>
			<tr>
		  
		  
		   <TD class="fieldLabel" width="25%">
			<hdiits:caption captionid="TR.NMLST" bundle="${trschLables}"/>&nbsp;
			<hdiits:caption captionid="TR.DateFormat" bundle="${trschLables}" captionLang="single"/></TD>
			<TD class="fieldLabel" width="25%">
			 <hdiits:dateTime name="txtNomiLastDate" 
                    captionid="TR.NMLST"  bundle="${trschLables}" maxvalue="01/01/9999" validation="txt.isrequired" mandatory="true" default="${defaults.nomRecLastDate}"  disabled="${readOnlyAfterResubmit}" onblur="checkNomiRecvDate('${NOMIDT}')"/>  </TD>
                   
			<td class="fieldLabel" width="25%"></td>
			
			<td class="fieldLabel width="25%"></td>
                    
                    
        	</tr>

    		</table>
   <c:choose>
   <c:when test="${loctdis eq 'N'}">
   <div id="externalInfo" style="">
   <jsp:include page="/WEB-INF/jsp/common/address.jsp">
		<jsp:param name="addrName" value="trainingAddress" />
		<jsp:param name="addrLookupName" value="Training Address" />
		<jsp:param name="addressTitle" value="Training Address" />
	 	<jsp:param name="mandatory" value="Yes"/> 
	 	<jsp:param name="condition" value="addrReq"/>
	 	<jsp:param name="isReadOnly" value="${addre}"/>
	</jsp:include>
    </div>	
	</c:when>
	<c:otherwise>
    <div id="internalInfo" style="">
   	 <hdiits:hidden name="lstLoc" id="lstLoc" default="${locList11.locId}"/>
    <table class="tabtable">
    <tr>
    <td class="fieldLabel"  width="25%"><hdiits:caption captionid="TR.TRAININGLOC" bundle="${trschLables}"/></td>
    <td class="fieldLabel">
		    <hdiits:select name="lstLoc1"  captionid="TR.TRAININGLOC" bundle="${trschLables}"  mandatory="true" sort="false" default="${defaults.cmnLocationMstTrainingCenterId.locId}" readonly="true">
		       			<option value="${locList11.locId}" selected="selected"><c:out value="${locList11.locName}"/></option>
    		</hdiits:select>    
    		
     </td>
    </tr>
    </table>
    </div>
   </c:otherwise> 	
   </c:choose>	
    <c:if test="${editFlag eq 'Y'}">
     	<c:if test="${defaults.hrTrTrainingMst.isExternal eq 'Y'}">	
	    	<script>
			document.getElementById("externalInfo").style.display="";
			</script>
		</c:if>
		<c:if test="${defaults.hrTrTrainingMst.isExternal eq 'N'}">	
	    	<script>
			document.getElementById("internalInfo").style.display="";
			</script>
		</c:if>
    </c:if>
    		
    		
    <table class="tabtable">		
    <tr>            
                    			
			<td class="fieldLabel" width="25%">
			<hdiits:caption captionid="TR.BATCHNO" bundle="${trschLables}"/></TD>
    		<TD width="25%"><hdiits:number name="txtSchbtno" id="mm" captionid="TR.BATCHNO" maxlength="3" bundle="${trschLables}" mandatory="true" validation="txt.isrequired" default="${defaults.batchNo}" readonly="${readOnlyAfterResubmit}"/></TD>
    	     
		    <td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.BATCHSIZE" bundle="${trschLables}"/></td>
		    <TD class="fieldLabel" width="25%"><hdiits:number name="txtSchbchsize"  captionid="TR.BATCHSIZE" maxlength="4" bundle="${trschLables}"  validation="txt.isrequired" mandatory="true" default="${defaults.batchSize}" onblur="checkBatchSize(this,'${BATCH}')" readonly="${readOnlyAfterResubmit}"/></TD>
		    
	</TR>
    <tr>
	       <TD class="fieldLabel" width="25%"><hdiits:caption captionid="TR.ORGANISER" bundle="${trschLables}"/>&nbsp;
			</TD>
		    <TD class="fieldLabel" width="25%">
		     <hdiits:text name="txtSchorganiser" validation="txt.isname,txt.isrequired" maxlength="50" mandatory="true" default="${defaults.organizer}" captionid="TR.ORGANISER" bundle="${trschLables}" onkeypress="return validateText(event, false)" readonly="${readOnlyAfterResubmit}"/>
		    </TD>
		    
			<TD class="fieldLabel" width="25%"><hdiits:caption captionid="TR.DIRECTOR" bundle="${trschLables}"/>&nbsp;
			</TD>
		    <TD class="fieldLabel" width="25%">
		     <hdiits:text name="txtSchdirector" validation="txt.isname" maxlength="50" default="${defaults.director}" captionid="TR.DIRECTOR" bundle="${trschLables}" onkeypress="return validateText(event, false)"  readonly="${readOnlyAfterResubmit}"/>
		    </TD>
			
    		
  </TR> 
  
   
  <tr>
  			<TD class="fieldLabel" width="25%"><hdiits:caption captionid="TR.TRNGMODE" bundle="${trschLables}"/>&nbsp;
			</TD>
  			<TD class="fieldLabel" width="25%">
		     <hdiits:select name="lstTrngmode"  captionid="TR.TRNGMODE" bundle="${trschLables}" validation="sel.isrequired" mandatory="true" sort="false" default="${defaults.cmnLookupMstTrainingModeLookupId.lookupId}" readonly="${readOnlyAfterResubmit}">
		 <hdiits:option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" bundle="${trschLables}"/></hdiits:option>
	        
		 	<c:forEach var="tmodelookup" items="${trngModeLookup}">
					<hdiits:option value="${tmodelookup.lookupId}"><c:out value="${tmodelookup.lookupDesc}"> </c:out></hdiits:option>
			</c:forEach>
		  
						
		   </hdiits:select>  
		    </TD>
  			
	       <TD class="fieldLabel" width="25%"><hdiits:caption captionid="TR.REMARKS" bundle="${trschLables}"/>&nbsp;
			</TD>
		    <TD class="fieldLabel" width="25%">
		     <hdiits:textarea name="Remarks" rows="3" cols="30" default="${defaults.trngScheduleRemarks}" readonly="${readOnlyAfterResubmit}"></hdiits:textarea>
		    </TD>
		    
			
			
    		
  </TR> 
  <tr>
  <TD class="fieldLabel" width="25%"><hdiits:caption captionid="TR.PREREQUISITES" bundle="${trschLables}"/>&nbsp;
			</TD></tr>
			<tr><td colspan="4">
			
			
				 <jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
                <jsp:param name="attachmentName" value="attachPrerequisites" />
                <jsp:param name="formName" value="SchMST" />
                <jsp:param name="removeAttachmentFromDB" value="Y" />                
                <jsp:param name="attachmentType" value="Document" />
                <jsp:param name="readOnly" value="${attread}"/>
                
              
	 </jsp:include>
	   <script>
             
      </script>
	
  </td></tr>
</table>

<!-- Multiple Add -->

<br>
<br>
<br>
 		<c:if test="${loctdis eq 'Y'}">
 			<table class="tabtable" bgcolor="#ebebeb">
 			<tr>
 			<fmt:formatDate value="${hrTrTrainerMst.dateTo}" pattern="yyyy-MM-dd HH:mm:ss" dateStyle="medium" var="availableToDate"/>
 			<td width="25%" class="fieldLabel"><hdiits:caption  captionid="TR.CLASSDATE" bundle="${trschLables}"/></td>
 			<td width="25%" class="fieldLabel"><hdiits:dateTime name="classDate"    captionid="TR.CLASSDATE" bundle="${trschLables}"  mandatory="true" maxvalue="01/01/9999" validation="txt.isrequired" onblur="checkClassDate('${cddt}')" disabled="${readOnlyAfterResubmit}"></hdiits:dateTime></td>
 			<td width="25%" class="fieldLabel"><hdiits:caption  captionid="TR.TRNGSUBJECT" bundle="${trschLables}"/></td>
 			<td width="25%" class="fieldLabel"><hdiits:select name="trngSubject"  captionid="TR.TRNGSUBJECT" bundle="${trschLables}"  mandatory="true" validation="sel.isrequired" readonly="${readOnlyAfterResubmit}">
 													<hdiits:option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" bundle="${trschLables}"/></hdiits:option>
	       											 <c:forEach var="ts" items="${subjectMstList}">
								<hdiits:option value="${ts.subjectId}"><c:out value="${ts.subjectName}"> </c:out></hdiits:option>
							</c:forEach>
									 			</hdiits:select></td>
 			
 			</tr>
 			<tr>
 			<td width="25%" class="fieldLabel"><hdiits:caption  captionid="TR.TRAINER" bundle="${trschLables}"></hdiits:caption></td>
 			<td width="25%" class="fieldLabel"><hdiits:select name="Trainer" mandatory="true" validation="sel.isrequired"  captionid="TR.TRAINER" bundle="${trschLables}" readonly="${readOnlyAfterResubmit}">
 													<hdiits:option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" bundle="${trschLables}"/></hdiits:option>
	      											 <c:forEach var="trnr" items="${trainerMstList}">
					<hdiits:option value="${trnr.trainerId}"><c:out value="${trnr.firstName} ${trnr.lastName}"> </c:out></hdiits:option>
			</c:forEach>
									 			</hdiits:select></td>
									 			
 			
 			<td width="25%" class="fieldLabel"><hdiits:caption  captionid="TR.TIMEFROM" bundle="${trschLables}"></hdiits:caption>&nbsp;<hdiits:caption  captionid="TR.TIMEFR" bundle="${trschLables}" captionLang="single"></hdiits:caption></td>
 			<td width="25%" class="fieldLabel"><hdiits:time name="Timefrom" mandatory="true" validation="txt.isrequired"  captionid="TR.TIMEFROM" bundle="${trschLables}" readonly="${readOnlyAfterResubmit}"></hdiits:time></td>
 			
 			</tr>
 			
 			<tr>
 			<td width="25%" class="fieldLabel"><hdiits:caption  captionid="TR.TIMETO" bundle="${trschLables}" />&nbsp;<hdiits:caption  captionid="TR.TIMEFR" bundle="${trschLables}" captionLang="single"></hdiits:caption></td>
 			<td width="25%" class="fieldLabel"><hdiits:time name="Timeto" mandatory="true" validation="txt.isrequired"  captionid="TR.TIMETO" bundle="${trschLables}" readonly="${readOnlyAfterResubmit}"></hdiits:time></td>
 			<td width="25%" class="fieldLabel"><hdiits:caption  captionid="TR.CLASSRMNO" bundle="${trschLables}"></hdiits:caption></td>
 			<td width="25%" class="fieldLabel"><hdiits:number name="Classrmno"  maxlength="18" captionid="TR.CLASSRMNO" bundle="${trschLables}" mandatory="true" validation="txt.isrequired,txt.isnumber" readonly="${readOnlyAfterResubmit}"></hdiits:number></td>
 			
 			</tr>
 			
 			</table>
 			
 			
 			
 			<table id="btnAdd" style="display" align="center">
 			<tr>
 			<TD class="fieldLabel" align="center" colspan="4"><hdiits:button  name="add"  type="button" captionid="TR.add" bundle="${trngAttendedLables}" onclick="validateTime('${totf}','${ttotf}');addSchdetails('addRecord');"></hdiits:button></td>
 			</tr>
 			</table>
 			<table id="btnUpdate" style="display:none" align="center">
 			<tr>
 			<TD class="fieldLabel" align="center" colspan="4"><hdiits:button  name="update"  type="button"  captionid="TR.updt" bundle="${trngAttendedLables}"  onclick="validateTime('${totf}','${ttotf}');addSchdetails('updateRecord');"></hdiits:button></td>
 			</tr>
 			</table>
 			<table id="txnAddSch" style="display:none" border="1" align="center" width="80%" class="datatable"> 
 			
            <tr>
    		<td class="datatableheader"><b><hdiits:caption captionid="TR.CLASSDATE" bundle="${trschLables}"/></b></td>
    		<td class="datatableheader"><b><hdiits:caption captionid="TR.TRNGSUBJECT" bundle="${trschLables}"/></b></td>
            <td class="datatableheader"><b><hdiits:caption captionid="TR.TRAINER" bundle="${trschLables}"/></b></td>
            <td class="datatableheader"><b><hdiits:caption captionid="TR.TIMEFROM" bundle="${trschLables}"/></b></td>
            <td class="datatableheader"><b><hdiits:caption captionid="TR.TIMETO" bundle="${trschLables}"/></b></td>
            <td class="datatableheader"><b><hdiits:caption captionid="TR.CLASSRMNO" bundle="${trschLables}"/></b></td>
            
            <td class="datatableheader"><b>Edit / Delete</b></td>  
    	    </tr>
            </table>  
            
            <c:set var="serialNumber1" value="0" />
            <c:if test="${trngSubMpgList != null}">
				<script type="text/javascript">
					arrMulti['txnAddSch'] = new Array();
					arrMultiLoc['txnAddSch'] = new Array();
					//alert("new array created...");
				</script>
			</c:if>
	<c:forEach items="${trngSubMpgList}" var="Lst">
		<c:set var="curXMLFileName1" value="${xmlKeyList[serialNumber1]}" ></c:set>
		<c:set var="classdt" 	value="${Lst.classDate}" />
		<c:set var="trngsub" 	value="${Lst.hrTrSubjectMst.subjectName}" />
		<c:set var="trnr" 	value="${Lst.hrTrTrainerMst.firstName} ${Lst.hrTrTrainerMst.lastName}" />
		<c:set var="timefrm" 	value="${Lst.timeFrom}" />
		<c:set var="timet"		value="${Lst.timeTo}" ></c:set>
		<c:set var="clssrmno"		value="${Lst.classRoomNo}" ></c:set>
		
		

		<script type="text/javascript">
			
			
			var xmlFileName1 = '${curXMLFileName1}';
			 var datetimearray1 = new Array();
			datetimearray1= getDateAndTimeFromDateObj('${timefrm}');
			 var datetimearray2 = new Array();
			datetimearray2= getDateAndTimeFromDateObj('${timet}');
			 var datetimearray3 = new Array();
			datetimearray3= getDateAndTimeFromDateObj('${classdt}');
			
			var tempA1 = new Array(datetimearray3[0],'${trngsub}','${trnr}',datetimearray1[1],datetimearray2[1],'${clssrmno}');
			//alert("${firstName}");
			
			/*document.getElementById("classDateHidd").value =datetimearray3[0];
  		  	document.getElementById("timeFromHidd").value =datetimearray1[1]; 
  		  	document.getElementById("timeToHidd").value =datetimearray2[1];*/
  		  	document.getElementById("classDateHidd").value ='${classdt}';
  		  	document.getElementById("timeFromHidd").value ='${timefrm}'; 
  		  	document.getElementById("timeToHidd").value ='${timet}';
  		  	var dupArrayNew = new Array('classDateHidd','timeFromHidd','timeToHidd');
  		  	var c1 = "${attread}";
  		  	
  		  	if(c1 == "Y"){
  		  			
  		  			document.getElementById('btnAdd').style.display='none';
					addDBDataInTable('txnAddSch','EditXmlKey',tempA1,xmlFileName1,'','','');
					
			}
			else
			{
					addDBDataInTable('txnAddSch','EditXmlKey',tempA1,xmlFileName1,'editRecord','deleteDBRecord1','');
			}
			var rowId = 'rowEditXmlKey${serialNumber1 + 1}';
			arrMulti['txnAddSch'][rowId] = datetimearray3[0]+','+datetimearray1[1]+','+datetimearray2[1]+','+'${clssrmno}';
			arrMultiLoc['txnAddSch'][rowId] = datetimearray3[0]+','+'${trnr}'+','+datetimearray1[1]+','+datetimearray2[1];
			
		</script>
		<c:set var="serialNumber1" value="${serialNumber1 +1}" />	
	</c:forEach>
 	</c:if>			

</div>

<!-- TAB2  ------------------------------------------------------------------------------------------------- -->
<div id="tcontent2" class="tabcontent" tabno="1">

<jsp:include page="seatAllotment.jsp"></jsp:include>

</div>

<!-- ------------------TAB3----------------------------------------------------------------------------- -->
<c:if test="${show3rdTab eq 'Y'}">
<div id="tcontent3" class="tabcontent" tabno="2">

<jsp:include page="ViewNominateTrng.jsp">
<jsp:param name="forTabNo" value="3"/>
</jsp:include>

</div>


</c:if>


<!-- ------------------TAB4----------------------------------------------------------------------------- -->
<c:if test="${show4thTab eq 'Y'}">
<div id="tcontent4" class="tabcontent" tabno="3">
<c:set var="approvedNominationDetails" value="${resValue.approvedNominationDetails}" > </c:set>
<c:set var="approvedSize" value="${resValue.approvedSize}" > </c:set>
<c:set var="approvedCounter" value="0" > </c:set>
<hdiits:hidden name="approvedCounter" />
<script>
		document.forms[0].approvedCounter.value= ${approvedCounter};
		
</script>

<table id="approvedNomTable" border="1"  class="datatable">

<tr>
		<td class="datatableheader"><b><hdiits:caption captionid="TR.SRNO"	bundle="${nmLables}" /></b></td>
		<td class="datatableheader"><b><hdiits:caption captionid="TR.EMPNAME"	bundle="${nmLables}" /></b></td>
		<td class="datatableheader"><b><hdiits:caption captionid="TR.DESIGNATION"	bundle="${nmLables}" /></b></td>
		<td class="datatableheader"><b><hdiits:caption captionid="TR.QUALIFICATION"	bundle="${nmLables}" /></b></td>
		<td class="datatableheader"><b><hdiits:caption captionid="TR.DISTRICT"	bundle="${nmLables}" /></b></td>
		<td class="datatableheader"><b><hdiits:caption captionid="TR.LSTTRNG"	bundle="${nmLables}" /></b></td>
		<td class="datatableheader"><b><hdiits:caption captionid="TR.REMARKS"	bundle="${nmLables}" /></b></td>
		
</tr>

<c:forEach var="nm" items="${approvedNominationDetails}">
<c:set var="approvedCounter" value="${approvedCounter + 1}" > </c:set>
<script>
		document.forms[0].approvedCounter.value= ${approvedCounter};
</script>

<tr id="approvedNomNo${approvedCounter}">
<td><hdiits:hidden name="approvedId_${approvedCounter}" default="${nm.nominationId}"/><c:out value="${nm.srNo}"/></td>
<td><hdiits:hidden name="approvedEmpId_${approvedCounter}" default="${nm.empId}"/><c:out value="${nm.empName}"/></td>
<td><hdiits:hidden name="approvedFlag_${approvedCounter}" default="I"/><c:out value="${nm.designation}"/></td>
<td><c:out value="${nm.qualification}"/></td>
<td><c:out value="${nm.district}"/></td>
<td><c:out value="${nm.lastTrngAttended}"/></td>
<td><hdiits:textarea name="approvedRemarks_${approvedCounter}" caption="remarks" default="${nm.remarks}" readonly="${readOnlyAfterResubmit}"/></td>

</tr>
</c:forEach>
</table>

</div>
</c:if>

<hdiits:jsField jsFunction="checkStartDateEndDate('${enddate1}','${sded}')" name="checkStartDateEndDate"/>


<div id="trngTabNav">
<jsp:include page="/WEB-INF/jsp/core/tabnavigation.jsp" ></jsp:include>
</div>
</div>
<c:if test="${editFlag eq 'Y'}">
<script type="text/javascript">
//alert("IN CHANGE");
document.getElementById("trngTabNav").style.display='none';

</script>
</c:if>
<script>
<c:if test="${show3rdTab eq 'N'}">
		document.getElementById('Next').disabled=true;
</c:if>
</script>
<script type="text/javascript">
var c1 = "${attread}";
  		  	if(c1 == "Y"){
  		  			
  		  			document.getElementById('btnAdd').style.display='none';
  		  	}
		initializetabcontent("maintab");
</script>
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' controlNames="classDate,trngSubject,Classrmno,Timeto,Timefrom,Trainer"/>
</hdiits:form>