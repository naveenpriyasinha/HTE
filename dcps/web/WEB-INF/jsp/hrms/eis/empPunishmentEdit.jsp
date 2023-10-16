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
<c:set var="actionList" value="${resValue.actionList}" ></c:set>
 <script><!--




function chkDateCompare()
{
  
    var sysdate= new Date();
   
    if(document.getElementById("startDate").value!='' )
    { 
    	
    	var dateday=sysdate.getDate();
    	var datemonth = sysdate.getMonth()+1;
    	var dateYear= sysdate.getFullYear();
    	
    	if(datemonth<10)
    	{
    		datemonth="0"+datemonth;
    	}
    	
    	var dateString = dateday + "/" + datemonth+ "/" +dateYear;
    	
    var diff = compareDate(document.getElementById("startDate").value,dateString);
    if(diff<0)
    {
    	alert("Recovery Date must be greater than or equal to Current Date");
    	document.getElementById("startDate").value='';    
    	document.getElementById("startDate").focus();	
    	return false;
    }
    }
    return;
}





function cmpDate()
{
	 var diff = compareDate(document.punishment.startDate.value,document.punishment.endDate.value);   

	 if(document.punishment.endDate.value!=null && document.punishment.endDate.value!='')
	 {
	 
	 	var MonthDiff=	datediff(document.punishment.startDate.value,document.punishment.endDate.value);
	 	if(diff < 0  || MonthDiff==-1)
  	 	{
   			alert("To Date must be greater than From Date");
	   		document.punishment.endDate.value='';
   			return false;
  	 	}
	  	
  	 }

}


--></script>

<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>



<hdiits:form name="punishment" validate="true" method="POST"
	action="./hrms.htm?actionFlag=insertPunishmentData&edit=Y&pmtId=${actionList.punishmentId}" encType="multipart/form-data">

	<div id="tabmenu">
		<ul id="maintab" class="shadetabs" compact="compact">
			<li class="selected"><a href="#" rel="tcontent1"><font size="2"><b><fmt:message key="PMT.Punishment" bundle="${commonLables}"/></b></a></li>
		</ul>
	</div>

	<div class="halftabcontentstyle">
	<div id="tcontent1" class="halftabcontent" tabno="0">
	<table width= "65%" align= "center"><br>
		
		
		<tr>
			<td><b><hdiits:caption captionid="PMT.employee" bundle="${commonLables}"/></b></td>
			<td><hdiits:text name="employee" default="${actionList.hrEisEmpMst.orgEmpMst.empPrefix} ${actionList.hrEisEmpMst.orgEmpMst.empFname} ${actionList.hrEisEmpMst.orgEmpMst.empMname} ${actionList.hrEisEmpMst.orgEmpMst.empLname}" captionid="MISC.employee" bundle="${commonLables}"  validation="txt.isrequired"  maxlength="10"  readonly="true" size="20"/> </td>
	    </tr>
	    
	
	    <tr>
			<td><b><hdiits:caption captionid="PMT.reason" bundle="${commonLables}"/></b></td>
			<td><hdiits:textarea cols="50" rows="3" name="reason"  captionid="PMT.reason" bundle="${commonLables}"  default="${actionList.reason}" validation="txt.isrequired" onblur=""   mandatory="true" maxlength="500"/> </td>
	    </tr>
	    
	    <tr>
			<td><b><hdiits:caption captionid="PMT.startDate" bundle="${commonLables}"/></b></td>
			<td><hdiits:dateTime captionid="PMT.startDate" bundle="${commonLables}" name="startDate" default="${actionList.startDate}"mandatory="true" onblur="chkDateCompare();" validation="txt.isrequired,txt.isdt" /></TD>	
		</tr>
		
		<tr>
			<td><b><hdiits:caption captionid="PMT.endDate" bundle="${commonLables}"/></b></td>
			<td><hdiits:dateTime captionid="PMT.endDate" bundle="${commonLables}" name="endDate"  default="${actionList.endDate}" mandatory="" onblur="cmpDate();" validation="txt.isdt" /></TD>	
		</tr>
		
	     <tr>
	     <td colspan="4"><b>Order Upload:-</b>
					   <jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
        	    	    	<jsp:param name="attachmentName" value="orderId" />
            	    		<jsp:param name="formName" value="punishment" />
	            	    	<jsp:param name="attachmentType" value="Document" />
				    		<jsp:param name="multiple" value="N" />  
				    		<jsp:param name="mandatory" value="Y"/>              
	    				</jsp:include>
	</td></tr>	
	</table>
 	</div>

<hdiits:hidden default="getPunishmentData" name="givenurl"/>
	<jsp:include page="../../core/PayTabnavigation.jsp" />
	
	</div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
	
		
		if("${msg}"!=null&&"${msg}"!='')
		{
			alert("${msg}");
			var url="./hrms.htm?actionFlag=getPunishmentData";
			document.punishment.action=url;
			document.punishment.submit();
		}
	</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>
</body>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

