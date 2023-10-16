<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
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
<script type="text/javascript"
	src="<c:url value="/script/hrms/eis/commonUtils.js"/>"></script>
	<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>
<c:set var="msg" value="${resValue.msg}" ></c:set>
<c:set var="monthList" value="${resValue.monthList}" ></c:set>
<c:set var="yearList" value="${resValue.yearList}" ></c:set>
	<c:set var="nonGovDeducTypes" value="${resValue.nonGovDeducTypes}" > </c:set>
<script>



function uploadFile(){
	alert("File Upload");
}
function chekc(){
	var r=confirm("Are you sure to update records");
	if (r==true)
	 return true;
	else
	return false;
}
			
</script>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="enLables" scope="page"/>


<hdiits:form name="uploadNonGovDeduc" validate="true" method="POST"
	action="hrms.htm?actionFlag=UploadExcelData" encType="multipart/form-data">

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption captionid="NGD.updateNonGovDeductMaster" bundle="${enLables}"></hdiits:caption></b></a></li>
		
	</ul>
	</div>
	
	
<div class="halftabcontentstyle">


	<div id="tcontent1" class="halftabcontent" tabno="0">
<br>
    
   <TABLE width="85%" align="center">
 <tr>	
	<TD> <b><hdiits:caption caption="Month" captionid="Month"/></b></TD>
	<td>
	<hdiits:select name="selMonth" size="1" sort="false" caption="Month" id="selMonth" mandatory="true" validation="sel.isrequired">
	<hdiits:option value=""> --Select-- </hdiits:option>
	<c:forEach items="${monthList}" var="month">
	<hdiits:option value="${month.lookupShortName}"> ${month.lookupDesc} </hdiits:option>
	</c:forEach>
	</hdiits:select>
	</td>
   <td> </td>
	<TD> <b><hdiits:caption caption="Year" captionid="Year"/></b></TD>
	<td>
		<hdiits:select name="selYear" size="1" sort="false" caption="Year" id="selYear" mandatory="true" validation="sel.isrequired">
	<hdiits:option value=""> --Select-- </hdiits:option>
	<c:forEach items="${yearList}" var="yearList">
	<hdiits:option value="${yearList.lookupShortName}"> ${yearList.lookupDesc} </hdiits:option>
	</c:forEach>
	</hdiits:select>
	</td>
	</tr>
 <tr>
 <td><font size="2"><b><hdiits:caption captionid="NGD.deductionType" bundle="${enLables}"></hdiits:caption> </b> </td>
  <td><hdiits:select name="deductionType"
				caption="Non Deduction Type"
			    captionid="NGD.deductionType"
			    mandatory="true"
			    validation="sel.isrequired">
			    <hdiits:option value="-1">---------------Select----------------</hdiits:option>
			    <c:forEach items="${nonGovDeducTypes}" var="deducTypes">
			    	<hdiits:option value="${deducTypes.deducCode}">${deducTypes.deducDisplayName}</hdiits:option>
			    </c:forEach>
			    </hdiits:select></td> </tr>
			    <tr> <td></td><td></td></tr>
 <TR><TD>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD>

<%-- 				 <hdiits:button name="btnUpload" captionid="eis.btnUpload" onclick=""/> 
				<TD width=15%><b>File : </b></TD>
			
			<td>
			 <hdiits:file name="btnUpload" type="txt-identityname" captionid="eis.btnUpload" bundle="${enLables}" />
			</td> --%>
			
							<td colspan="10"><b>Upload and Update Non Government Data</b>
					   <jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
        	    	    	<jsp:param name="attachmentName" value="nonGovData" />
            	    		<jsp:param name="formName" value="uploadNonGovDeduc" />
	            	    	<jsp:param name="attachmentType" value="Excel" />
				    		<jsp:param name="multiple" value="N" />  
				    		<jsp:param name="mandatory" value="Y"/>              
	    				</jsp:include>
	</td>
	</TR>
	</table> 
 </div>

	<jsp:include page="../../core/PayTabnavigation.jsp" /></div>
	<hdiits:jsField  name="validate" jsFunction="chekc()" /> 
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		if("${msg}"!=null&&"${msg}"!='')
		{   
     		alert("${msg}");
			if("${empId}"!=null && "${empId}"!='' && "${empAllRec}"=='added')
			{

			var url="./hrms.htm?actionFlag=getNonGovDeductionMaster&Employee_ID_NonGovEmpSearch=${empId}&empAllRec=Y";
			}
			else
			{

			var url="./hrms.htm?actionFlag=getNonGovDeductionMaster";
			}
			
			document.frmBF.action=url;
			document.frmBF.submit();
			
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

			