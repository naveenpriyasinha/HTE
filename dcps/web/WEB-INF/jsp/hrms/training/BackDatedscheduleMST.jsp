<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="/WEB-INF/jsp/core/include.jsp"%>
<%@ include file="/WEB-INF/jsp/common/cmnMsg.jsp"%>


<%@page import="com.tcs.sgv.common.utils.DateUtility"%>
<fmt:setBundle basename="resources.trng.BackDatedtrschLables" var="trschLables" 	scope="request" />
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
<fmt:message key="TR.empAlt" bundle="${trschLables}" var="empAlt" />


<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" 	src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript"  src="<c:url value="/script/common/attachment.js"/>"> </script>
 <script type="text/javascript" src="<c:url value="/script/eis/Address.js"/>"></script>
 <script type="text/javascript" src="script/common/address.js"></script>
<script type="text/javascript" src="script/hrms/training/BackDatedscheduleMst.js"></script>

 <c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="trainingMstList" value="${resValue.trainingMstList}" > </c:set>
<c:set var="lstTrainingType" value="${resValue.lstTrainingType}" > </c:set>

 <c:set var="trngModeLookup" value="${resValue.trngModeLookup}" > </c:set>
   
  
  <c:set var="schId" value="${resValue.scheduleId}" > </c:set>
  
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
<hdiits:hidden name="actionFlag" default="HR_TR_Insert_BackDatedTraining_Attended" />
<hdiits:hidden name="editFlag" default="${editFlag}" />
<hdiits:hidden name="schId" default="${schId}" />
<fmt:formatDate value="${resValue.sysDate}" pattern="dd/MM/yyyy HH:mm" dateStyle="medium" var="sysDate"/>
<hdiits:hidden name="systemDate" default="${sysDate}" /> 
<fmt:formatDate value="${resValue.sysDate}" pattern="dd/MM/yyyy" dateStyle="medium" var="sysDateChck"/>
<hdiits:hidden name="sysDateHidd" default="${sysDateChck}" /> 
<hdiits:hidden name="counter" default="0"/>



<hdiits:hidden name="srNo" />
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
<style type="text/css">    
            .pg-normal {
                color: black;
                font-weight: normal;
                text-decoration: none;    
                cursor: pointer;    
            }
            .pg-selected {
                color: black;
                font-weight: bold;        
                text-decoration: underline;
                cursor: pointer;
            }
</style>

<script type="text/javascript">
var pager = new Pager('TrainingMemberTab', 10); 
var noOfPage=0;
var trainingList = new Array();


function getTrainingNames(val)
{
	var actionFlag = "getTrainingNamesBack";
     

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

</script>
	
	
	
<div id="tabmenu">
   <ul id="maintab" class="shadetabs">
   <li  class="selected"><a href="#"  rel="tcontent1"><hdiits:caption captionid="TR.SCHEDULEDETAILS" bundle="${trschLables}"/></a></li>
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
		 

		 
		 <TD class="fieldLabel" width="25%" style="vertical-align: top;"><hdiits:caption captionid="TR.TRAININGTYPE" bundle="${trschLables}"/></TD>
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
 </table>
   <c:choose>
   <c:when test="${loctdis eq 'N'}">
   <div id="externalInfo" style="">
   <jsp:include page="/WEB-INF/jsp/common/address.jsp">
		<jsp:param name="addrName" value="trainingAddress" />
		<jsp:param name="addrLookupName" value="Training Address" />
		<jsp:param name="addressTitle" value="Training Address" />
	 	<jsp:param name="mandatory" value="Yes"/> 
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

<div>
	<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
	<jsp:param name="SearchEmployee" value="TrainingMember" />
	<jsp:param name="searchEmployeeTitle" value="TrainingMember" />
	<jsp:param name="functionName" value="AddEmployee" />
	<jsp:param name="multiEmployeeSel" value="Yes" />
</jsp:include>
</div>		        
<br>
<table id="TrainingMemberTab" style="" border="1" align="center" width="80%" class="datatable">
	<tr>
		<td class="datatableheader"><b><hdiits:caption	captionid="TR.EmployeeName" bundle="${trschLables}" /></b></TD>
		<td class="datatableheader"><b><hdiits:caption	captionid="TR.Designation" bundle="${trschLables}" /></b></TD>
		<td class="datatableheader"><b><hdiits:caption	captionid="TR.RESULT" bundle="${trschLables}" /></b></TD>
		<td class="datatableheader"><b>Delete</b></td>
	</tr>
</table>		
<div id="pageNavPosition"></div>
</div>

<hdiits:jsField jsFunction="checkStartDateEndDate('${enddate1}','${sded}')" name="checkStartDateEndDate"/>
<hdiits:jsField jsFunction="checkEmp('${empAlt}')" name="checkEmp"/>

<div id="trngTabNav">
<jsp:include page="/WEB-INF/jsp/core/tabnavigation.jsp" ></jsp:include>
</div>
</div>

<script type="text/javascript">
		initializetabcontent("maintab");
</script>
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />

</hdiits:form>