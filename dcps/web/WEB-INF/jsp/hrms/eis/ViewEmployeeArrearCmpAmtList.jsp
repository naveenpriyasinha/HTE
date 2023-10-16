<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript" src="common/script/tagLibValidation.js"></script>
<script type="text/javascript" src="common/script/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/commonUtils.js"/>"></script>	
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>

<!-- resource Bundle  -->
<fmt:setBundle basename="resources.posting.postingLabels" var="empEditListCommonLables" scope="request" />
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>
<fmt:setBundle basename="resources.eis.eisLables_en_US" var="adminCreatePostLabel" scope="request" />
<fmt:setBundle basename="resources.ess.UserPost" var="userPostMapping" scope="request" />
<!-- resource Bundle  -->

<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>
<c:set var="empArrarCmpAmtData" value="${resValue.empArrarCmpAmtData}" ></c:set>
<c:set var="cmpLen" value="${resValue.cmpLen}" ></c:set>
<c:set var="dataSize" value="${resValue.dataSize}" ></c:set>
<c:set var="empArrCmpList" value="${resValue.empArrCmpList}" ></c:set>
<c:set var="salRevId" value="${resValue.salRevId}" ></c:set>
<c:set var="billSubheadId" value="${resValue.billSubheadId}" ></c:set>
<c:set var="msg" value="${resValue.msg}" ></c:set>
<c:set var="arrearBillPostDataLen" value="${resValue.arrearBillPostDataLen}" ></c:set>
<c:set var="cmpAmtDataLen" value="${resValue.cmpAmtDataLen}" ></c:set>

<fmt:setBundle basename="resources.posting.postingLabels" var="empEditListCommonLables" scope="request" />

<script type="text/javascript"><!--

var count=0;
function crtCmpAmtDataList(obj,cmpAmtId)
{
	var strCmpAmtArrList='';
	var val = 0;
	if(obj.value!='')
		val=obj.value;
	else
	{
		alert('Please enter amount');
		obj.focus();
		return false;
	}	
	strCmpAmtArrList=strCmpAmtArrList+cmpAmtId+'Z'+val;
	createHiddenVariable('cmpAmtMpgData'+count,strCmpAmtArrList);
	count=eval(count+1);
}
function createHiddenVariable(name,value)
{              
	var hiddenElement=document.createElement("input");
	hiddenElement.setAttribute('name',name);
	hiddenElement.setAttribute('id',name);
	hiddenElement.setAttribute('type','hidden');
	hiddenElement.setAttribute('value',value);
	document.forms[0].appendChild(hiddenElement);  
}
function saveCmpAmtList()
{	
	document.forms[0].elements('count').value=count;
	document.forms[0].action="hrms.htm?actionFlag=updateEmpArrearCmpAmtData";
	document.forms[0].submit();
	document.forms[0].elements('Submit').disabled=true;	
}
function closeWindow()
{
	window.close();
}
--></script>

<hdiits:form name="viewEmpArrearCmpAmt" validate="true" method="POST" action=""  encType="multipart/form-data">
<hdiits:hidden name="strCmpAmtList" />
<hdiits:hidden name="count" />
<hdiits:hidden name="salRevId" default="${salRevId}"/>
<hdiits:hidden name="billSubheadId" default="${billSubheadId}"/>
<link rel='stylesheet' href='<c:url value="/themes/hdiits/tabcontent.css"/>' type='text/css' />
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>View Employee and Arrear Bill Mapping</b></a></li>
	</ul>
	</div>

<div class="halftabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">
		
	<hdiits:fieldGroup titleCaptionId="pay.empCmpAmtList" bundle="${commonLables}" > 
		<table align="center" cellspacing="2" cellpadding="2" border="0" width="100%">	
		<c:if test="${empArrCmpList ne null}">
		<tr>
			<td width="5%" align="left" class="Label"><b><fmt:message key="eis.srno" bundle="${commonLables}"/></b></td>			
			<td width="25%" align="left" class="Label"><b><fmt:message key="pay.empName" bundle="${commonLables}"/></b></td>
			
			<c:forEach items="${empArrCmpList}" var="cmpNameRow">				
				<td align="center" class="Label"><b><c:out value="${cmpNameRow[1]}"></c:out></b>
				</td>
			</c:forEach>
		
		</tr>		
		</c:if>
		<c:set var="textCounter" value="0"></c:set>
		<c:set var="empSrNo" value="0"></c:set>
		<c:if test="${empArrarCmpAmtData ne null and cmpLen>0}">			
			<c:forEach var="row" items="${empArrarCmpAmtData}" step="${cmpLen}">
			<c:set var="empSrNo" value="${empSrNo+1}"></c:set>
				<tr>	
				<td width="5%" align="left" class="Label"><c:out value="${empSrNo}"></c:out></td>	
				<td width="25%" align="left" class="Label"><c:out value="${row[1]}(${row[7]},${row[8]})"></c:out></td>			
				<c:set var="postId" value="${row[2]}${row[6]}"></c:set>
					<c:forEach var="cmpRow" items="${empArrarCmpAmtData}" begin="0" end="${cmpAmtDataLen}" varStatus="i">
						<c:set var="postEmpId" value="${cmpRow[2]}${cmpRow[6]}"></c:set>
						<c:if test="${postId eq postEmpId}">
							<c:set var="textCounter" value="${textCounter+1}"></c:set>
								<td align="center" class="Label">
								<hdiits:number name="txt${cmpRow[0]}${row[2]}${textCounter}"  default="${cmpRow[5]}" style="align:right" onblur="crtCmpAmtDataList(this,${cmpRow[0]})"  />							
								</td>
							</c:if>												 
				</c:forEach>
				</tr>	
			</c:forEach>
		</c:if>
		</table>
		<table align="center">
			<tr>
			<td align="right" colspan="2">
				<hdiits:button name="Submit" type="button" captionid="SUBMIT" bundle="${empEditListCommonLables}" onclick="saveCmpAmtList()"></hdiits:button>
			</td>
	         <td align="left" colspan="1">
	             <hdiits:button name="closeButton" type="button" captionid="CLOSE" bundle="${empEditListCommonLables}" onclick="closeWindow()"/>
	         </td>
	       </tr>
		</table>
	</hdiits:fieldGroup>
	</div>
	<script type="text/javascript">
	<!--
		initializetabcontent("maintab");
		if('${msg}'!=null && '${msg}'!='')
		{
			alert('${msg}' );
			window.close();
		}			
	-->
	</script>
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' controlNames="" />
</div>
</hdiits:form>
<%
} 
catch (Exception e) 
{
	e.printStackTrace();
}
%>