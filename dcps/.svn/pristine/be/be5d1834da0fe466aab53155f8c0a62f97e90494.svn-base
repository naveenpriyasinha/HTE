
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
<script type="text/javascript" src="script/hrms/hr/rosterScript/viewRecRoster.js">
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
	
<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="departList" value="${resultValue.departList}">
</c:set>
<c:set var="dsgnList" value="${resultValue.dsgnList}">
</c:set>
<c:set var="locList" value="${resultValue.locList}">
</c:set>
<c:set var="flag" value="${resultValue.flag}">
</c:set>

<fmt:setBundle basename="resources.hr.roster.rosterMst" var="caption" scope="request" />
<fmt:setBundle basename="resources.hr.roster.RosterAlertMsges" var="alertMsges" scope="request" />

<c:set var="rosterdtl" value="${resultValue.rosterlist}"></c:set>
<c:set var="empcategory" value="${resultValue.category}"></c:set>
<c:set var="reqId" value="${resultValue.reqId}"></c:set>
<c:set var="previousList" value="${resultValue.previousList}"></c:set>
<c:set var="transferUserList" value="${resultValue.transferUserList}"></c:set>
<c:set var="locationId" value="${resultValue.locationId}"></c:set>
<c:set var="designationId" value="${resultValue.designationId}"></c:set>
<c:set var="SCPer" value="${resultValue.SCPer}"></c:set>
<c:set var="STPer" value="${resultValue.STPer}"></c:set>
<c:set var="SEBCPer" value="${resultValue.SEBCPer}"></c:set>
<c:set var="PHPer" value="${resultValue.PHPer}"></c:set>
<style> 
 		.odd{background-color: #CCCCCC;} 
 		.even{background-color: #999999;} 
 		.preOdd{background-color: #CCFFCC;} 
 		.preEven{background-color: #99FFCC;} 
</style>


<script type="text/javascript">
//gloabal Variable;
var GlobaltorwId='';
 var mainTbChk=0;
function submitDtls()
{
	 
	for(i=1;i<=totalLength;i++){
		if(document.getElementById('empName'+i).value=="")
		{
			alert("<fmt:message key="HRMS.employeeReq" bundle="${alertMsges}" />");      
			return ;
		}
	}
 
	var urlstyle="hrms.htm?actionFlag=submitAllocationDlts";
	document.Roster.action=urlstyle;
	document.Roster.submit();
}
function writeRemarks(trowId)
{		
		GlobaltorwId=trowId;
		 
		document.getElementById('textData').value=document.getElementById('text_'+trowId).value;
			
		document.getElementById('rwFlag').value=document.getElementById('rwFlag_'+trowId).value;
		win=window.open("hrms.htm?viewName=rosterWriteRemark","WriteRemarks","width=650,height=250,scrollbars=yes,toolbar=no,status=yes,menubar=no,resizable=no,top=40,left=100,dependent=yes");
		if(win.opener == null) {win.opener = self;}	
		if(window.complete_state){win.focus();}
		try {
			win.focus();
		}catch(ex){}
	
}
function SetData()
{
	document.getElementById('text_'+GlobaltorwId).value=document.getElementById('textData').value;
}
var srId=0;
	var empArray=new Array();
	var cntSC=0,cntST=0,cntSEBC=0,cntPH=0;
	var counterRoster=0;
	var previousSize=0;
	var noOfUser=0;
	var emptyFlag=0;
	var totalLength=0;
function SearchEmp(form){
	
	var butName='Search'+srId;
	//document.getElementById('search').value='search'+srId;
	var n=form.name;
	srId=n.substring(6,n.length);
	
	document.getElementById('empSearchName').value='empName'+srId;
	document.getElementById('deptname').value='lblDept'+srId;
	document.getElementById('hidUserId').value='userId'+srId;
	document.getElementById('doj').value='lblDoj'+srId;
	document.getElementById('hidcatId').value='catId'+srId;
	document.getElementById('hidreset').value='reset'+srId;
	
	
	var locId=document.getElementById('hidlocationId').value;
	
	var dsgnId=document.getElementById('hiddesignationId').value;

	//var href='<%=request.getRequestURL() %>'+'?actionFlag=getAllSearchEmp&hidlocationId='+locId+'&hiddesignationId='+dsgnId;
	var href='./hrms.htm?actionFlag=getTransferEmp&hidlocationId='+locId+'&hiddesignationId='+dsgnId;
	
		
	window.open(href,'chield', 'width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
	
}
	
</script>

<style>

.bubble_tooltip{
	width:147px;
	position:absolute;
	display:none;
}
.bubble_tooltip .bubble_top{
	background-image: url('images/bubble_top.gif');
	background-repeat:no-repeat;
	height:15px;	
}
.bubble_tooltip .bubble_middle{
	background-image: url('images/bubble_middle.gif');
	background-repeat:repeat-y;	
	background-position:bottom left;
	padding-left:7px;
	padding-right:7px;
}
.bubble_tooltip .bubble_middle span{
	position:relative;
	top:-5px;
	font-family: Trebuchet MS, Lucida Sans Unicode, Arial, sans-serif;
	font-size:11px;
	max-height: 2cm;
}
.bubble_tooltip .bubble_bottom{
	background-image: url('images/bubble_bottom.gif');
	background-repeat:no-repeat;
	background-repeat:no-repeat;	
	height:44px;
	position:relative;
	top:-6px;
}
</style>


<hdiits:form name="Roster" validate="true" method="POST"
	encType="multipart/form-data">
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"> <hdiits:caption captionid="Hr_othertab" bundle="${caption}" /> </a></li>
	</ul>
	</div>
	<div class="tabcontentstyle" style="height: 100%;width: 100%">
	<div id="tcontent1" class="tabcontent" tabno="0" >
	
	<hdiits:hidden name="wffileId_hidden" id="wffileId_hidden" />
	<hdiits:hidden name="hidlocationId" id="locationId"
		default="${locationId}" />
	<hdiits:hidden name="hiddesignationId" id="designationId"
		default="${designationId}" />
	
	<table border="0"  align="center">
		<tr>
			<td><hdiits:caption	captionid="Hr_lbl_location" bundle="${caption}"/>&nbsp;</td>
			<td>
				 <c:forEach var="locList" items="${locList}">
					<hdiits:hidden name="location" id="location" default="${locList.locId}"/>
					<strong><c:out value="${locList.locName}"></c:out></strong>
				 </c:forEach>
				 &nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			
			<td><hdiits:caption	captionid="Hr_lbl_designation" bundle="${caption}"/>&nbsp;</td>
			<td><select name="dsgn" size="1" caption="dsgn" id="dsgn"
				tabindex="3">
				<option value="-1">------Select -----</option>
				<c:forEach var="dsgnList" items="${dsgnList}">
					<option value="${dsgnList.dsgnId}">
							${dsgnList.dsgnName}
						</option>
				</c:forEach>
			</select></td>
		</tr>

		<tr>

		</tr>

	</table>
	<center>
	
	<hdiits:button name="view" type="button" id="view"  value="Show Roster" onclick="showRoster()" />
	</center>

	
	<hdiits:hidden name="hidVal" id="hidVal" />
	<hdiits:hidden name="hidreset" id="hidreset" />
	<hdiits:hidden name="empSearchName" id="empSearchName" />
	<hdiits:hidden name="deptname" id="deptname" />
	<hdiits:hidden name="doj" id="doj" />
	<hdiits:hidden name="hidUserId" id="hidUserId" />
	<hdiits:hidden name="prevacancyNo" id="prevacancyNo" />
	<hdiits:hidden name="preValPresent" id="preValPresent" />
	<hdiits:hidden name="hidcatId" id="hidcatId" />
	<hdiits:hidden name="search" id="search" />
	<hdiits:hidden name="totalLength" id="totalLength" />
	<hdiits:hidden name="wffileId_hidden" id="wffileId_hidden" />
	<table border="3" bordercolor="black" id="headerTb">
		<%-- First row for header--%>
		
	

	</table>
	<c:if test="${empty rosterdtl}">
		 <c:if test="${empty previousList}">
		<c:if test="${flag=='N'}">
			<center><br><br><br><br><br> <hdiits:caption captionid="Hr_No_Record_Found" bundle="${caption}"/></center>
		</c:if>
		</c:if>
	</c:if>

	<c:if test="${not empty previousList}">
		<script>
			
			emptyFlag=1;
			mainTbChk=1;
		</script>
		</c:if>

		<c:if test="${empty previousList}">
		<script>
			emptyFlag=0;
			mainTbChk=0;
		</script>
		</c:if>

	<div style="width: 100%;height: 50%;overflow:scroll;" id="mainTable" >
	<table id="rostertb" style="width: 100%;height: 50%" border="1">
			<tr class="datatableheader">
			<td valign="bottom" colspan="4" rowspan="1" style="width: 149px"><hdiits:caption	captionid="Hr_resservedseat" bundle="${caption}"/></td>
			<td valign="bottom" rowspan="2" style="width: 58px" ><hdiits:caption	captionid="Hr_lastrecruitment" bundle="${caption}"/></td>
			<td valign="bottom" rowspan="2" style="width: 53px"><hdiits:caption	captionid="Hr_serialno" bundle="${caption}"/></td>
			<td align="center" valign="bottom" style="width: 20px">${SCPer}&nbsp;<b>%</b>&nbsp;</td>
			<td valign="bottom" align="center"  style="width: 28px">${STPer}&nbsp;<b>%</b>&nbsp;</td>
			<td valign="bottom" align="center">${SEBCPer}&nbsp;<b>%</b>&nbsp;</td>
			<td valign="bottom" align="center" style="width: 20px">${PHPer}&nbsp;<b>%</b>&nbsp;</td>
			<td valign="bottom" rowspan="2" style="width: 80px"><hdiits:caption captionid="Hr_nameofseats" bundle="${caption}"/></td>
			<td valign="bottom" rowspan="2" style="width: 90px"><hdiits:caption captionid="Hr_nameofemployee" bundle="${caption}"/></td>
			<td valign="bottom" rowspan="2" style="width: 85px"><hdiits:caption captionid="Hr_dateofjoin" bundle="${caption}"/></td>
			<td valign="bottom" rowspan="2"  style="width: 79px"><hdiits:caption captionid="Hr_nameofempcategory" bundle="${caption}"/></td>
			<td align="center" valign="bottom" style="width: 20px">${SCPer}&nbsp;<b>%</b>&nbsp;</td>
			<td valign="bottom" align="center" style="width: 28px">${STPer}&nbsp;<b>%</b>&nbsp;</td>
			<td valign="bottom" align="center" >${SEBCPer}&nbsp;<b>%</b>&nbsp;</td>
			<td valign="bottom" align="center" style="width: 20px">${PHPer}&nbsp;<b>%</b>&nbsp;</td>
			<td valign="bottom" rowspan="2" style="width: 90px">
			<hdiits:caption
				captionid="Hr_transfer" bundle="${caption}" /><br><hdiits:caption
				captionid="Hr_retired" bundle="${caption}" /><br><hdiits:caption
				captionid="Hr_promotion" bundle="${caption}" />
			</td>
			<td valign="bottom" rowspan="2"><hdiits:caption captionid="Hr_specremarks" bundle="${caption}"/></td>
		</tr>


		<%-- Second row for header--%>
		<tr class="datatableheader">
			<td nowrap="nowrap"><hdiits:caption	captionid="Hr_sclabl" bundle="${caption}" />&nbsp;</td>
			<td nowrap="nowrap"><hdiits:caption	captionid="Hr_stlabl" bundle="${caption}" />&nbsp;</td>
			<td nowrap="nowrap"><hdiits:caption	captionid="Hr_sebclabl" bundle="${caption}" />&nbsp;</td>
			<td nowrap="nowrap"><hdiits:caption	captionid="Hr_phlabl" bundle="${caption}" />&nbsp;</td>

			<td nowrap="nowrap"><hdiits:caption	captionid="Hr_sclabl" bundle="${caption}" />&nbsp;</td>
			<td nowrap="nowrap"><hdiits:caption	captionid="Hr_stlabl" bundle="${caption}" />&nbsp;</td>
			<td nowrap="nowrap"><hdiits:caption	captionid="Hr_sebclabl" bundle="${caption}" />&nbsp;</td>
			<td nowrap="nowrap"><hdiits:caption	captionid="Hr_phlabl" bundle="${caption}" />&nbsp;</td>


			<td nowrap="nowrap"><hdiits:caption	captionid="Hr_sclabl" bundle="${caption}" />&nbsp;</td>
			<td nowrap="nowrap"><hdiits:caption	captionid="Hr_stlabl" bundle="${caption}" />&nbsp;</td>
			<td nowrap="nowrap"><hdiits:caption	captionid="Hr_sebclabl" bundle="${caption}" />&nbsp;</td>
			<td nowrap="nowrap"><hdiits:caption	captionid="Hr_phlabl" bundle="${caption}" />&nbsp;</td>

		</tr>
		<c:forEach var="previousList" items="${previousList}">
			<script>
			previousSize=previousSize+1;
			totalLength=totalLength+1;
		</script>
		
			<div id="tooltip_main${previousList.serialNo}" class="bubble_tooltip">
					<div class="bubble_top"><span></span></div>
					<div class="bubble_middle"><span id="tooltip${previousList.serialNo}"></span></div>
					<div class="bubble_bottom"></div>
			</div>
			<tr bgcolor="WHITE">
				<td nowrap="nowrap">&nbsp;<label id="finalsc${previousList.serialNo}"></label> </td>		
				<td nowrap="nowrap">&nbsp;<label id="finalst${previousList.serialNo}" ></label></td>	
				<td nowrap="nowrap" >&nbsp;<label id="finalsebc${previousList.serialNo}"></label> </td>	
				<td nowrap="nowrap">&nbsp;<label id="finalph${previousList.serialNo}"></label></td>
				<td  rowspan="1" style="width: 65px" id="crtDate${previousList.serialNo}">
				<fmt:parseDate var="bday" pattern="yyyy-MM-dd HH:mm:ss" value="${previousList.createdDate}"/>
					<fmt:formatDate value="${bday}"  pattern="yyyy"/></td>
				<td rowspan="1" style="width: 45px" id="${previousList.serialNo}" onmouseover= "xstooltip_show(event,'tooltip_main${previousList.serialNo}','tooltip${previousList.serialNo}', '${previousList.serialNo}')" onmouseout="xstooltip_hide('tooltip_main${previousList.serialNo}','tooltip${previousList.serialNo}')"><hdiits:hidden name="serial${previousList.serialNo}"
					default="${previousList.serialNo}" /><c:out
					value="${previousList.serialNo}" /></td>
				<td rowspan="1" style="width: 15px"><hdiits:hidden name="scval${previousList.serialNo}"
					default="${previousList.scVal}" /><LABEL
					id="lscval${previousList.serialNo}" />
					<fmt:formatNumber type="number" value="${previousList.scVal}" maxFractionDigits="2" var="SCVale"/>
					<c:out value="${SCVale}" /></td>
				<td rowspan="1" style="width: 15px"><hdiits:hidden name="stval${previousList.serialNo}"
					default="${previousList.stVal}" /><LABEL
					id="lstval${previousList.serialNo}" />
					<fmt:formatNumber type="number" value="${previousList.stVal}" maxFractionDigits="2" var="STVale"/>
					<c:out value="${STVale}" /></td>
				<td rowspan="1" style="width: 28px"><hdiits:hidden name="sebcval${previousList.serialNo}"
					default="${previousList.sebcVal}" /><LABEL
					id="lsebcval${previousList.serialNo}" /><c:out
					value="${previousList.sebcVal}" /></td>
				<td rowspan="1" style="width: 15px"><hdiits:hidden name="phval${previousList.serialNo}"
					default="${previousList.phVal}" /><LABEL
					id="lphval${previousList.serialNo}" /><c:out
					value="${previousList.phVal}" /></td>


				<td  rowspan="1" style="width: 50px" id="plan_category${previousList.serialNo}"><hdiits:hidden name="Categorysel${previousList.serialNo}"
					id="Categorysel${previousList.serialNo}"
					default="${previousList.plannedCatId}" /> <c:out
					value="${previousList.plannedCat}" /></td>
				<td  rowspan="1" style="width: 75px" id="empName${previousList.serialNo}"><c:out value="${previousList.empName}" />
				<hdiits:hidden 	name="userId${previousList.serialNo}"  id="userId${previousList.serialNo}" default="${previousList.userId}"  />
				</td>
				<td  rowspan="1" style="width: 45px" id="joindate${previousList.serialNo}">
					<fmt:parseDate var="bday" pattern="yyyy-MM-dd HH:mm:ss" value="${previousList.joinDate}"/>
					<fmt:formatDate value="${bday}"  pattern="MM/dd/yyyy"/></td>
				<td rowspan="1" style="width: 60px" id="act_category${previousList.serialNo}"><label id="lblDept${previousList.serialNo}"><c:out
					value="${previousList.actualCat}" /></label> <hdiits:hidden
					name="catId${previousList.serialNo}"
					id="catId${previousList.serialNo}"
					default="${previousList.actualCatId}" /></td>

				<td rowspan="1" style="width: 15px" nowrap="nowrap" >&nbsp;<label id="sc${previousList.serialNo}"></label> <hdiits:hidden
					name="hidsc${previousList.serialNo}"
					id="hidsc${previousList.serialNo}" default="0" /></td>
				<td rowspan="1" style="width: 25px" nowrap="nowrap" >&nbsp; <label id="st${previousList.serialNo}"></label><hdiits:hidden
					name="hidst${previousList.serialNo}"
					id="hidst${previousList.serialNo}" default="0" /></td>
				<td rowspan="1" style="width: 28px" nowrap="nowrap">&nbsp; <label id="sebc${previousList.serialNo}"></label><hdiits:hidden
					name="hidsebc${previousList.serialNo}"
					id="hidsebc${previousList.serialNo}" default="0" /></td>
				<td rowspan="1" style="width: 15px" nowrap="nowrap">&nbsp; <label id="ph${previousList.serialNo}"></label><hdiits:hidden
					name="hidph${previousList.serialNo}"
					id="hidph${previousList.serialNo}" default="0" /></td>

				<td rowspan="1" style="width: 170px">&nbsp;</td>
				<td rowspan="1" style="width: 45px">
					<a href="javascript:void('remarks');" onclick=javascript:writeRemarks('row${previousList.serialNo}')> <hdiits:caption captionid="HRMS.ReadMsg" bundle="${caption}"/></a>
					<INPUT type='textarea' name='remarks' id='text_row${previousList.serialNo}' value='${previousList.remarks}' style="display:none"  maxlength="4s000"/>
					<input type="hidden" name='rwFlag_row${previousList.serialNo}' value="R" id='rwFlag_row${previousList.serialNo}' />
				</td>
				
				
				
			</tr>

		</c:forEach>

		<!-- end -->
		<%--  	Row Data started	--%>

		<c:forEach var="rlist" items="${rosterdtl}">
			<script>
		counterRoster=counterRoster+1;
		totalLength=totalLength+1;
	</script>
	
			<tr>
				<td nowrap="nowrap">&nbsp;<label id="finalsc${rlist.serialNo}"></label> </td>		
				<td nowrap="nowrap">&nbsp;<label id="finalst${rlist.serialNo}" ></label></td>	
				<td nowrap="nowrap">&nbsp;<label id="finalsebc${rlist.serialNo}"></label> </td>	
				<td nowrap="nowrap">&nbsp;<label id="finalph${rlist.serialNo}"></label></td>
				<td id="crtDate${rlist.serialNo}">
					<fmt:parseDate var="rday" pattern="yyyy-MM-dd" value="${rlist.createdDate}"/>
					<fmt:formatDate value="${rday}"  pattern="yyyy"/>
				</td>
				<td id="${rlist.serialNo}"><c:out value="${rlist.serialNo}" /></td>
				<td><hdiits:hidden name="scval${rlist.serialNo}"
					default="${rlist.scVal}" /><LABEL id="lscval${rlist.serialNo}" /><c:out
					value="${rlist.scVal}" /></td>
				<td><hdiits:hidden name="stval${rlist.serialNo}"
					default="${rlist.stVal}" /><LABEL id="lstval${rlist.serialNo}" /><c:out
					value="${rlist.stVal}" /></td>
				<td><hdiits:hidden name="sebcval${rlist.serialNo}"
					default="${rlist.sebcVal}" /><LABEL id="lsebcval${rlist.serialNo}" /><c:out
					value="${rlist.sebcVal}" /></td>
				<td><hdiits:hidden name="phval${rlist.serialNo}"
					default="${rlist.phVal}" /><LABEL id="lphval${rlist.serialNo}" /><c:out
					value="${rlist.phVal}" /></td>


				<td width="80px"><select name="Categorysel${rlist.serialNo}"
					id="Categorysel${rlist.serialNo}" captionid="Category"
					onchange="enableSearch(this)">
					<c:forEach var="c" items="${empcategory}">
						<option value="${c.lookupId}">
							<c:out value="${c.lookupDesc}" />
						</option>
					</c:forEach>
				</select></td>



 
				<td width="9px" nowrap="nowrap"><hdiits:text name="empName${rlist.serialNo}"
					id="empName${rlist.serialNo}" size="9"/> 
					<img  src="images/search_icon.gif" name="Search${rlist.serialNo}"   id="Search${rlist.serialNo}" tooltip="Click Here To Search Employee" onclick="SearchEmp(this)" style="display: none" />
					<img src="images/disable_search_icon.GIF" name="disSearch${rlist.serialNo}" id="disSearch${rlist.serialNo}" tooltip="" onclick="" /> 
					<hdiits:button
					name="reset${rlist.serialNo}" type="button"
					onclick="resetEmp(this);" value="ResetButton" 
					id="reset${rlist.serialNo}" style="display:none" /> <hdiits:hidden
					name="userId${rlist.serialNo}" id="userId${rlist.serialNo}" /></td>
				<td width="9px"><input type="text" id="lblDoj${rlist.serialNo}"
					name="lblDoj${rlist.serialNo}" size="9"/></td>
				<td style="width: 78px">&nbsp;<label for="empNamelbl${rlist.serialNo}"
					id="lblDept${rlist.serialNo}"></label> <hdiits:hidden
					name="catId${rlist.serialNo}" id="catId${rlist.serialNo}" /> <hdiits:hidden
					name="lblDateOfJoin${rlist.serialNo}"
					id="lblDateOfJoin${rlist.serialNo}" /></td>
				<td width="20px">&nbsp;<label id="sc${rlist.serialNo}"></label> <hdiits:hidden
					name="hidsc${rlist.serialNo}" id="hidsc${rlist.serialNo}"
					default="0" /></td>
				<td width="19px">&nbsp; <label id="st${rlist.serialNo}"></label><hdiits:hidden
					name="hidst${rlist.serialNo}" id="hidst${rlist.serialNo}"
					default="0" /></td>
				<td width="25px">&nbsp; <label id="sebc${rlist.serialNo}"></label><hdiits:hidden
					name="hidsebc${rlist.serialNo}" id="hidsebc${rlist.serialNo}"
					default="0" /></td>
				<td width="20px">&nbsp; <label id="ph${rlist.serialNo}"></label><hdiits:hidden
					name="hidph${rlist.serialNo}" id="hidph${rlist.serialNo}"
					default="0" /></td>

				<td style="width: 85px">&nbsp;</td>
				<td>
					<a href="javascript:void('remarks');" onclick=javascript:writeRemarks('row${rlist.serialNo}')> <hdiits:caption captionid="HRMS.ReadMsg" bundle="${caption}"/></a>
					<INPUT type='textarea' name='remarks' id='text_row${rlist.serialNo}' value="" style="display:none"  maxlength="4s000"/>
					<input type="hidden" name='rwFlag_row${rlist.serialNo}' value="R" id='rwFlag_row${rlist.serialNo}' />
				</td>

			</tr>
		
		</c:forEach>

		<%--  Last row for the surplus--%>

		<script type="text/javascript">
			document.getElementById('totalLength').value = totalLength;
			
			if(previousSize==0)
			{
				
				document.getElementById('headerTb').style.display="none";
				document.getElementById('rostertb').style.display="none";
			}
			
		</script>



		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td><LABEL id="prescsur" />&nbsp;</td>
			<td><LABEL id="prestsur" />&nbsp;</td>
			<td><LABEL id="presebcsur" />&nbsp;</td>
			<td><LABEL id="prephsur" />&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td><LABEL id="scsur" />&nbsp;</td>
			<td><LABEL id="stsur" />&nbsp;</td>
			<td><LABEL id="sebcsur" />&nbsp;</td>
			<td><LABEL id="phsur" />&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>

		<script>
	document.getElementById('prevacancyNo').value=previousSize;
		if(previousSize!=0)
		{
			
			document.getElementById('preValPresent').value="true";
		}else
		{
			
			document.getElementById('preValPresent').value="false";
		}
		
		for(var i=1;i<=previousSize;i++)
		{
			srId=i;
			
			presetCountForSurplus();
		}
		
		var table = document.getElementById('rostertb');   
   		var rows = table.getElementsByTagName("tr");   
   		 
   		for(var j=1;j<previousSize;j++)
	   	{
			document.getElementById('crtDate'+j).innerHTML="&nbsp";
	   	}
	   	
	   	var tmpScVal=0;
    	var tmpStVal=0;
    	var tmpSebcVal=0;
    	var tmpPhVal=0;
    	
    	for(var i=previousSize;i>=1;i--)
    	{
	    	var scname="sc"+i;
	    	var scValue=document.getElementById(scname).innerHTML;
	    	if(scValue.length!=0)
 			{
 				tmpScVal=scValue;
 				break;
 			}
	    	
    	}
    	//Calculation For ST
    	for(var i=previousSize;i>=1;i--)
    	{
	  	    var stname="st"+i;
	    	var stValue=document.getElementById(stname).innerHTML;
	    	if(stValue.length!=0)
 			{
 				tmpStVal=stValue;
 				break;
 			}
	    	
    	}
    	//Calculation For SEBC
    	for(var i=previousSize;i>=1;i--)
    	{
	    	var sebcname="sebc"+i;
	    	var sebcValue=document.getElementById(sebcname).innerHTML;
	    	if(sebcValue.length!=0)
 			{
 				tmpSebcVal=sebcValue;
 				break;
 			}
	    	
    	}
	   	
	   	for(var i=previousSize;i>=1;i--)
    	{
	    	var phname="ph"+i;
	    	var phValue=document.getElementById(phname).innerHTML;
	    	if(phValue.length!=0)
 			{
 				tmpPhVal=phValue;
 				break;
 			}
	    	
    	}
	   	for(var j=1;j<=previousSize;j++)
   		{
			document.getElementById('finalsc'+j).innerHTML=tmpScVal;
			document.getElementById('finalst'+j).innerHTML=tmpStVal;
			document.getElementById('finalsebc'+j).innerHTML=tmpSebcVal;
			document.getElementById('finalph'+j).innerHTML=tmpPhVal;
   		}
   		for(var j=1;j<previousSize;j++)
   		{
			document.getElementById('finalsc'+j).innerHTML="&nbsp";
			document.getElementById('finalst'+j).innerHTML="&nbsp";
			document.getElementById('finalsebc'+j).innerHTML="&nbsp";
			document.getElementById('finalph'+j).innerHTML="&nbsp";
   		}	
   		
		for(i = previousSize+2; i < rows.length-1; i++){           
			 
    		 if(i % 2 == 0){ 
    			   rows[i].className = "even"; 
   			  }else{ 
   				    rows[i].className = "odd"; 
   				  }       

   		} 
		
</script>
	</table>
	</div> 
	<br><br>	 
		

	<table border="1"  id="empDiv" align="center" width="85%">
		<tr class="datatableheader">
			<td><hdiits:caption	captionid="HRMS.SrNo" bundle="${caption}" /></td>
			<td><hdiits:caption	captionid="HRMS.UserName" bundle="${caption}" /></td>
			<td><hdiits:caption	captionid="HRMS.DateOfJoining" bundle="${caption}" /></td>
		</tr>
	 
 

		<c:forEach var="empDtls" items="${transferUserList}">
			<script>
		noOfUser=noOfUser+1;
	</script>
			<tr>
				<td style="width: 82px"><input type="checkbox" name="checkbox${empDtls.serialNo}"
					id="checkbox${empDtls.serialNo}" /></td>
				<td style="width: 82px">${empDtls.empName}</td>
				<td style="width: 82px">
					<fmt:parseDate var="bday" pattern="yyyy-MM-dd HH:mm:ss" value="${empDtls.joinDate}"/>
					<fmt:formatDate value="${bday}"  pattern="MM/dd/yyyy"/></td>
				</td>
			</tr>
		</c:forEach>
	</table>


	<c:if test="${not empty transferUserList}">
	
		<script>
		emptyFlag=1;
			document.getElementById('empDiv').style.display="";
			
		</script>
	</c:if>
	<c:if test="${empty transferUserList}">

		<script>
			emptyFlag=0;
			document.getElementById('empDiv').style.display="none";
			
		</script>
	</c:if>
	
	
	<center>
		<hdiits:button name="sub" id="sub" type="button" onclick="submitDtls()"  value="Submit"/>		
	</center>
		<script>
		 
		if(mainTbChk==1){
			document.getElementById('mainTable').style.display="";
		}else{
			document.getElementById('mainTable').style.display="none";
		}
		if(emptyFlag==1)
		{
			document.getElementById('sub').style.display="";
			
		}else
		{
			document.getElementById('sub').style.display="none";
			
		}
		
		</script>
	<!-- Hidden Parameters -->
	<hdiits:hidden name="reqType" id="reqType" default="TRA" />
	<hdiits:hidden name="vacancyNo" id="vacancyNo" />
	<hdiits:hidden name="reqId" id="reqId" default="${reqId}" />
	<hdiits:hidden name="reqId" id="reqId" />
	<hdiits:hidden name="noOfVac" id="noOfVac" />
	<hdiits:hidden name="flagOfupdation" id="flagOfupdation" />
	<hdiits:hidden name="textData" id="textData" default="" />
	<hdiits:hidden name="rwFlag" id="rwFlag" default="" />
	<hdiits:hidden name="rosterType" id="rosterType" default="R" />
	
	</div>
	</div>
	<hdiits:validate controlNames="text"
		locale='<%=(String)session.getAttribute("locale")%>' />
	
	<script type="text/javascript">
	initializetabcontent("maintab");
	document.getElementById('vacancyNo').value=totalLength;
	</script>

</hdiits:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

<script type="text/javascript">		
			var chooserloc=document.getElementById("dsgn");
			
			var locId=document.getElementById("hidlocationId").value;
			if(!(locId.length==0))
			{
			chooserloc.value=document.getElementById("hiddesignationId").value;	
			document.getElementById("location").value=document.getElementById("hidlocationId").value;
			}	
</script>
