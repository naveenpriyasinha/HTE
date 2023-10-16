<%
try {
%>
<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
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
	src="<c:url value="/script/common/attachment.js"/>"></script>

<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>
  <c:set  var="AllowTypeMstList" value='${resultValue.AllowTypeMstList}' />
  <c:set  var="DeducTypeMasterList" value='${resultValue.DeducTypeMasterList}' /> 
  <c:set var="msg" value="${resultValue.msg}" ></c:set>
  <c:set var="result" value="${resultValue.result}" ></c:set>
  
<fmt:setBundle basename="resources.payroll.payrollLables_en_US" var="commonLables" scope="request"/>

${resValue.msg}


<script type="text/javascript" language="JavaScript">

function validateForm()
{	
    var allowId=document.AllOtherView.allowId.value;
    allowId='';
    var path1 = document.getElementById('allowIdList');
    for(var i = 1; i < path1.options.length; i++)
     {
        if (path1.options[i].selected)
        {
        allowId+=","+ path1.options[i].value;
        }
      }

    var deducId=document.AllOtherView.deducId.value;
    deducId='';
    var path1 = document.getElementById('deducIdList');
    for(var i = 1; i < path1.options.length; i++)
     {
        if (path1.options[i].selected)
        {
        deducId+=","+ path1.options[i].value;
        }
      }

	document.AllOtherView.allowId.value=allowId;
	document.AllOtherView.deducId.value=deducId;
	if(allowId==''&&deducId=='')
	{
	alert('Please Select atleast one allowance or deduction');
	return false;
	}
	else
	return true;
}

function beforeSubmit()
{
	document.AllOtherView.action="./hdiits.htm?actionFlag=updateAllOtherDetails";
	document.AllOtherView.submit();

}	

</script>

<hdiits:form name="AllOtherView" validate="true" method="POST"
  	action="javascript:beforeSubmit()" >

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="UpdateBasic" bundle="${commonLables}"/></b></a></li>
	</ul>
</div>
	
	
<div class="halftabcontentstyle">
	<div id="tcontent1" class="halftabcontent" tabno="0">
	<br/>
	<TABLE align="center"  width = "60%">  
	<TR>
		<TD>
	<hdiits:hidden name="allowId"></hdiits:hidden>
	<hdiits:hidden name="deducId"></hdiits:hidden>
		
		  <b>	<fmt:message key="EX.AT" bundle="${commonLables}"/></b>
		</TD>			
			<TD><hdiits:select   sort="false"  captionid="EX.AT" bundle="${commonLables}"   id="allowIdList" name="allowIdList"  multiple="true" size ="5"  >
				<hdiits:option value="-1" selected="true">------------------------Select-----------------------</hdiits:option>
						<c:forEach var="AllowTypeMstList" items="${AllowTypeMstList}">
							<option value='<c:out value="${AllowTypeMstList.allowCode}"/>' >
								<c:out value="${AllowTypeMstList.allowName}"/>
				    	</c:forEach>		
				</hdiits:select>
			</TD>
	
	
	</tr><tr></tr>
	<TR>
		<TD>
		  <b> <fmt:message key="DM.deductionMaster" bundle="${commonLables}"/></b>
		</TD>			
			<TD><hdiits:select   sort="false" captionid="DM.deductionMaster" bundle="${commonLables}"  multiple="true" size ="5"  name="deducIdList" id="deducIdList" >
				<hdiits:option value="-1" selected="true">------------------------Select-----------------------</hdiits:option>
						<c:forEach var="DeducTypeMasterList" items="${DeducTypeMasterList}">
							<option value='<c:out value="${DeducTypeMasterList.deducCode}"/>' >
								<c:out value="${DeducTypeMasterList.deducName}"/>
				    	</c:forEach>		
				</hdiits:select>
			</TD>
	</tr>
	<tr><td colspan="2">&nbsp;</td></tr>
	</TABLE>
	</div> 
	 	
	 	 	<hdiits:jsField  name="validate" jsFunction="validateForm()" /> 
	 
<fmt:setBundle basename="resources.payroll.payrollLables_en_US" var="Lables" scope="request"/>
	<jsp:include page="../../core/PayTabnavigation.jsp" />
</div>

	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		
		</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />

<script type="text/javascript" language="javascript">
		if("${msg}"!=null&&"${msg}"!='')
		{
			alert("${msg}");
		}

</script>

</hdiits:form>
</body>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>


