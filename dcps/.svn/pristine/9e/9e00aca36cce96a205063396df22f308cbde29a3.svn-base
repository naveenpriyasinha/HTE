<%

try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page session="true" %>
<script type="text/javascript"   
	src="common/script/tagLibValidation.js">
</script>

<script type="text/javascript"  
    src="common/script/commonfunctions.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>

<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="msg" value="${resValue.msg}" ></c:set>
<c:set var="statusList" value="${resValue.statusList}" ></c:set>

<script>
function cmpDate()
{
	 var diff = compareDate(document.ITSection.sectionStartDate.value,document.ITSection.sectionEndDate.value);   
	 
	 if(document.ITSection.sectionEndDate.value!=null&&document.ITSection.sectionEndDate.value!='')
	 {
	 	if(diff < 0)
  	 	{
   			alert("End Date must be greater than Start Date");
   			document.ITSection.sectionEndDate.value='';
   			return false;
  	 	}
  	 	
  	 	else
  	 		return true;
     }
}
</script>


<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>


<hdiits:form name="ITSection" validate="true" method="POST"
	action="./hrms.htm?actionFlag=insertSectionData&edit=N">

	<div id="tabmenu">
		<ul id="maintab" class="shadetabs" compact="compact">
			<li class="selected"><a href="#" rel="tcontent1"><font size="2"><b><hdiits:caption captionid="SECT.sectAdd" bundle="${commonLables}"></hdiits:caption></a></li>
		</ul>
	</div>

	<div class="halftabcontentstyle">
	<div id="tcontent1" class="halftabcontent" tabno="0">
	<table align= "center"><br>
		
	    
	    <tr>
	  	    <td width="10%"> </td>
			<td width="15%"><b><hdiits:caption captionid="SECT.sectCode" bundle="${commonLables}"/></b></td>
			<td width="20%"><hdiits:text name="sectionCode" default="" captionid="SECT.sectCode" bundle="${commonLables}"  validation="txt.isrequired"   maxlength="50"  mandatory="true" size="25"/> </td>
	    	<td width="10%"> </td>
			<td width="15%"><b><hdiits:caption captionid="SECT.sectName" bundle="${commonLables}"/></b></td>
			<td width="20%"><hdiits:text name="sectionName" default="" captionid="SECT.sectName" bundle="${commonLables}"    maxlength="50"  size="25"/> </td>
	  	    <td width="10%"> </td>			
	    </tr>
	    
	    
	    <tr>
	  	    <td width="10%"> </td>	    
			<td width="15%"><b><hdiits:caption captionid="SECT.sectDesc" bundle="${commonLables}"/></b></td>
			<td width="20%"><hdiits:textarea cols="28" rows="3" name="sectionDesc" default="" captionid="SECT.sectDesc" bundle="${commonLables}"   maxlength="1000"/> </td>
			 <td width="10%"> </td>
			<td class="fieldLabel" width="15%">
		    <b><hdiits:caption captionid="SECT.sectStatus" bundle="${commonLables}"></hdiits:caption>
		    </td>
			
			<td width="20%"><b><hdiits:select name="status" size="1"
                       captionid="SECT.sectStatus" bundle="${commonLables}"   
                       validation="sel.isrequired" mandatory="true" >
                        
        	<hdiits:option value="choose">--------------Select-----------------</hdiits:option>
         
         	<c:forEach var="status" items="${statusList}">
         		<hdiits:option value="${status.lookupId}"><c:out value="${status.lookupDesc}"> </c:out></hdiits:option>
         	</c:forEach>
		 
		 	</hdiits:select > 
        	</td>
	  	    <td width="10%"> </td>        	
	    </tr>
	    
	    <tr>
	    	<td width="10%"> </td>
			<td width="15%"><b><hdiits:caption captionid="SECT.sectStartDt" bundle="${commonLables}"/></b></td>
			<td width="20%"><hdiits:dateTime captionid="SECT.sectStartDt" bundle="${commonLables}" name="sectionStartDate" mandatory="true" validation="txt.isrequired,txt.isdt" /></TD>	
		    <td width="10%"> </td>
			<td width="15%"><b><hdiits:caption captionid="SECT.sectEndDt" bundle="${commonLables}"/></b></td>
			<td width="20%"><hdiits:dateTime captionid="SECT.sectEndDt" bundle="${commonLables}" name="sectionEndDate"   validation="txt.isdt" onblur="cmpDate()"/></TD>	
			<td width="10%"> </td>
		</tr>
		
		<tr>
			
	     </tr>
	</table>
 	</div>
<fmt:setBundle basename="resources.eis.eisLables_en_US" var="Lables" scope="request"/> 	
	<jsp:include page="../../core/PayTabnavigation.jsp" />
	</div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		
		if("${msg}"!=null&&"${msg}"!='')
		{
			alert("${msg}");
			var url="./hrms.htm?actionFlag=getSectionData";
			document.ITSection.action=url;
			document.ITSection.submit();
		}
	</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

