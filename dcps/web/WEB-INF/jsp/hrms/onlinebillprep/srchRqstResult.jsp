<%@ include file="../../core/include.jsp" %>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<fmt:setLocale value='<%=(String)session.getAttribute("locale")%>'/>
<fmt:setBundle basename="resources.onlinebillprep.CommonLabels" var="onlinebillprepLabels" scope="application"/>
<fmt:setBundle basename="resources.onlinebillprep.CommonAlerts" var="onlinebillprepAlerts" scope="application"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
	<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
	<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
	<script type="text/javascript" src="<c:url value="script/common/tabcontent.js"/>"></script>
	<script type="text/javascript" src="script/common/commonfunctions.js"></script>
	<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
	<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
	<script src="script/common/prototype.js" language="JavaScript" type="text/javascript"></script>	
	<script type="text/javascript" src="script/hrms/onlinebillprep/Common.js"> </script>
	<script type="text/javascript">

		var SRCHRESULT_SELECTEMP = "<fmt:message key="SRCHRESULT.SELECTEMP" bundle="${onlinebillprepAlerts}"></fmt:message>";
		var SRCHRESULT_SAMEEMPRQST = "<fmt:message key="SRCHRESULT.SAMEEMPRQST" bundle="${onlinebillprepAlerts}"></fmt:message>";
		var SRCHRESULT_ONLYMEDRQST = "<fmt:message key="SRCHRESULT.ONLYMEDRQST" bundle="${onlinebillprepAlerts}"></fmt:message>";
		
		function window_new_open(lStr)
		{
			var newWindow;
	    	var height = screen.height - 100;
	    	var width = screen.width;
	    	var urlstring = getURL(lStr);
	    	var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,memubar=no,location=no,scrollbars=yes,top=0,left=0";
	    	newWindow = window.open(urlstring, "frmCreateOnlineBill", urlstyle);		   
	    }

	</script>
</head>

<hdiits:form name="rqstForm" validate="true" method="post">

	<table border="0" cellspacing="0" cellpadding="0" align="center" width="100%">
	  	<tr class="TableHeaderBG"> 
			<td  align="center" class="HLabel"> 
				<b> Search Request(s) </b> 
			</td>
		</tr>	
	</table>
	<br />
	<display:table list="${resValue.ResultMap}" pagesize="12" id="objArr" requestURI="ifms.htm?actionFlag=getHyrUsers&page=savedBillsUpdate" excludedParams="ajax" varTotals="totals" export="true" partialList="" style="width:100%">
		<display:setProperty name="paging.banner.placement" value="bottom"/>   
		<display:column class="oddcentre" titleKey="CMN.SELECT" sortable="true"headerClass="datatableheader" style="width:5%" >
			<center><hdiits:checkbox name="chkSelected" value="${objArr[0]}~${objArr[9]}"/>	</center>
		</display:column>
 	           	<display:column  class="oddcentre" titleKey="EMPLOYEE_NAME" sortable="true" headerClass="datatableheader" style="width:15%" >
          		<c:out value='${objArr[2]}'></c:out>
          	</display:column>
          	<display:column  class="oddcentre"  titleKey="EMPLOYEE_DESGN" sortable="true" headerClass="datatableheader" style="width:15%" >
          		<c:out value='${objArr[4]}'></c:out>
         	</display:column>
          	<display:column  class="oddcentre" titleKey="REQ_APRVD_DT" sortable="true" headerClass="datatableheader" style="width:15%" >
          		<center><fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${objArr[1]}"/></center>
          	</display:column>
          	<display:column  class="oddcentre" titleKey="CMN.BILLTYPE" sortable="true" headerClass="datatableheader" style="width:20%" >
		   		<input type="hidden" name="billType"  value='${objArr[10]}'/>
		   		<center><c:out value='${objArr[7]}'></c:out></center>
          	</display:column>
          	<display:column  class="oddcentre" titleKey="DEPT" sortable="true" headerClass="datatableheader" style="width:20%" >
           		<center><c:out value='${objArr[8]}'></c:out></center>
          	</display:column>
          	<display:column  class="oddcentre" titleKey="CMN.EMP.TYPE" sortable="true" headerClass="datatableheader" style="width:10%" >
            <select name="cmbSrch" id="id_cmbSrch">
           		<c:forEach var='empType' items="${resValue.EmpType}" varStatus="No" >
           			<c:if test='${objArr[6] == empType.lookupDesc}'>
    						<option value='${empType.lookupDesc}' selected="selected"> <center><c:out value='${empType.lookupDesc}' /></center> </option>
		        	</c:if>
		        	<c:if test='${objArr[6] != empType.lookupDesc}'>
		    			<option value='${empType.lookupDesc}'><center> <c:out value='${empType.lookupDesc}'/></center> </option>
		    		</c:if>
		   		</c:forEach>
			</select>
		</display:column>
        <display:footer media="html">
		</display:footer>
	</display:table>
	
	<center>
		<hdiits:button name="btnCreateBill" type="button" value=" Create Bill " onclick="isSelected()"/>
	</center>
	
</hdiits:form>
