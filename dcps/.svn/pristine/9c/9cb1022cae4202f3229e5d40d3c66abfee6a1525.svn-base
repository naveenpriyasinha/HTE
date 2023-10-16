<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="common/script/tagLibValidation.js">
</script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<script type="text/javascript" src="script/common/commonfunctions.js">
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
<c:set var="resultObject" value="${result}" />
<c:set var="resultValue" value="${resultObject.resultValue}" />
<c:set var="finalList" value="${resultValue.finalList}"></c:set>
<c:set var="lstsz" value="${resultValue.lstsz}"></c:set>

<fmt:setBundle basename="resources.ess.resignation.ResignationLabels"
	var="commonLables1" scope="request" />
	
<script type="text/javascript">

function hrmssearch(object)
{

	
var asd=object.parentNode.innerHTML.split(">");

var alb=asd[1].split("<");
var fileid=alb[0];
alert(fileid);

myHTML='<html><head><title></title></head></html>';

parent.document.frames['applicationReport'].document.open();
parent.document.frames['applicationReport'].document.write(myHTML);
parent.document.frames['applicationReport'].document.close();

top.frames['applicationReport'].location ="./hrms.htm?actionFlag=hrmsResignationReport&fileId="+fileid;  

document.getElementById("deptinqinf").style.display="";	
	
	
}
</script>

<div id="tabmenu">
<ul id="maintab" class="shadetabs">
	<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption
		captionid="HRMS.enqTrackingSystem" bundle="${commonLables1}" captionLang="single"/></b></a></li>
</ul>
</div>
<div class="halftabcontentstyle">
<div id="tcontent1" class="tabcontent" tabno="0">
<table class="tabhdiits:table" bordercolor="#386CB7" width="100%">
	<tr>
		<td><hdiits:form name="deptDtls" validate="true" method="POST"
			encType="multipart/form-data">


			<div id="resig" name="resig">
			<table class="tabtable">
<c:if test="${lstsz!=0}"> 


<table width=100%  id="dept" >
 	<tr  height="30"  style="color: #000000;  font-family: Verdana; font-size: 12px; background-color: #C6DEFF;">
		<td width=100%>
		<b><hdiits:caption captionid="HRMS.EnqInformation" bundle="${commonLables1}"/></b>
		
		</td>
	</tr>
 </table>
						

				<tr>
					<td>
					<table align="center" width="100%"   border="1" id="empList">
					<br>
					
						<tr style="color: #000000; font-family: Verdana; font-size: 12px; background-color: #C6DEFF;">
							
							<td align="center"   colspan="20"><hdiits:caption captionid="HRMS.SrNo" bundle="${commonLables1}"/></td>
							<td align="center"   colspan="30"><hdiits:caption captionid="HRMS.FileId" bundle="${commonLables1}"/></td>
								<td align="center" colspan="50"><hdiits:caption captionid="HRMS.status" bundle="${commonLables1}"/></td>
						
						</tr>
						<c:forEach var="obj" items="${finalList}">

							<tr id="fullName">
								<td align="center" colspan="20">${obj.counter}</td>
								<td align="center" colspan="30"><a href="#"  onclick="hrmssearch(this)">${obj.fileId}  </a></td>
								<td align="center" colspan="50">${obj.status}</td>
							</tr>
						</c:forEach>
						
					</table>

					</td>
				</tr></c:if>
				
				
				
				
				<c:if test="${lstsz==0}">
				<hr align="center" width=" 50%">
<table width="100%" align="center">


<tr ></tr>

<tr><th align="center"><c:out value="No Record Found"></c:out></th></tr>
<tr></tr>


</table><hr align="center" width="50%"></c:if>


<table width=100%  id="deptinqinf"  style="display:none">
 	<tr  height="30"  style="color: #000000;  font-family: Verdana; font-size: 12px; background-color: #C6DEFF;">
		<td width=100%>
		<b><hdiits:caption captionid="HRMS.departmentalDtls" bundle="${commonLables1}"/></b>
		
		</td>
	</tr>
 </table>

<br><br>
				<tr>
			<td id="allReportTD">
				<iframe    scrolling="auto" name="applicationReport" id="applicationReport" src="" frameborder="0" frameborder="0" width="100%" height="400px" align="left" >
				</iframe>
			</td>
			
		</tr>
	



			</table>



			</div>

			<script type="text/javascript">
			
			
		initializetabcontent("maintab")
		</script>
			<hdiits:validate controlNames="tesxt"
				locale='<%=(String)session.getAttribute("locale")%>' />
				<br><br>
		
	</hdiits:form> 
	
		
		</td>
	</tr>
</table>
</div>
</div>
<script type="text/javascript">
	initializetabcontent("maintab")
</script>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>