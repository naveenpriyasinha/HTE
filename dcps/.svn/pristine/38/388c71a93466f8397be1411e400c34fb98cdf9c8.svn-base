
<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="resources.hr.roster.rosterMst" var="caption" scope="request" />
<fmt:setBundle basename="resources.hr.roster.RosterAlertMsges" var="alertMsges" scope="request" />

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
<script type="text/javascript" src="script/hrms/hr/rosterScript/recInboxEditTablePage.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>

<c:set var="resValue" value="${result.resultValue}"></c:set>
<c:set var="rosterdtl" value="${resValue.rosterlist}"></c:set>
<c:set var="empcategory" value="${resValue.category}"></c:set>
<c:set var="reqId" value="${resValue.reqId}"></c:set>
<c:set var="previousList" value="${resValue.previousList}"></c:set>
<c:set var="designationId" value="${resValue.designationId}"></c:set>
<c:set var="SCPer" value="${resValue.SCPer}"></c:set>
<c:set var="STPer" value="${resValue.STPer}"></c:set>
<c:set var="SEBCPer" value="${resValue.SEBCPer}"></c:set>
<c:set var="PHPer" value="${resValue.PHPer}"></c:set>

<c:set var="finalList" value="${resValue.finalList}"></c:set>

<style> 
 		.odd{background-color: #CCCCCC;} 
 		.even{background-color: #999999;} 
 		.preOdd{background-color: #CCFFCC;} 
 		.preEven{background-color: #99FFCC;} 
</style>
<script type="text/javascript">
var GlobaltorwId='';
 
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
	var catArray=new Array();
	//global Variable;
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
	//var href='./hrms.htm?actionFlag=getRosterAllEmpSearch';
	var href="./hrms.htm?actionFlag=getRosterEmpSearchSelData&multiple=false&code=ROSTER&designationId="+document.getElementById('designationId').value;
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
	height:16px;	
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
	top:-10px;
	font-family: Trebuchet MS, Lucida Sans Unicode, Arial, sans-serif;
	font-size:11px;
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
	action="./hrms.htm?actionFlag=submitAllocationDlts">
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"> <hdiits:caption
			captionid="Hr_recrallocation" bundle="${caption}" /> </a></li>
	</ul>
	</div>


	<div class="tabcontentstyle" style="height: 100%">


	<div id="tcontent1" class="tabcontent" tabno="0"><hdiits:hidden
		name="empSearchName" id="empSearchName" /> <hdiits:hidden
		name="deptname" id="deptname" /> <hdiits:hidden name="doj" id="doj" />
	<hdiits:hidden name="hidUserId" id="hidUserId" /> <hdiits:hidden
		name="prevacancyNo" id="prevacancyNo" /> <hdiits:hidden
		name="preValPresent" id="preValPresent" /> <hdiits:hidden
		name="hidcatId" id="hidcatId" /> <hdiits:hidden name="search"
		id="search" /> <hdiits:hidden name="totalLength" id="totalLength" />
	<hdiits:hidden name="wffileId_hidden" id="wffileId_hidden" />

	<div style="width: 100%;height: 100%;overflow:scroll">

	<table border="3" bordercolor="black" class="datatable" id="rostertb"	style="width: 100% ;height: 100%">
		<%-- First row for header--%>
		<tr class="datatableheader">
			<td valign="bottom" colspan="4" rowspan="1"><hdiits:caption
				captionid="Hr_resservedseat" bundle="${caption}" /></td>
			<td valign="bottom" rowspan="2"><hdiits:caption
				captionid="Hr_lastrecruitment" bundle="${caption}" /></td>
			<td valign="bottom" rowspan="2"><hdiits:caption
				captionid="Hr_serialno" bundle="${caption}" /></td>
			<td align="center" valign="bottom">${SCPer}&nbsp;<b>%</b>&nbsp;</td>
			<td valign="bottom" align="center">${STPer}&nbsp;<b>%</b>&nbsp;</td>
			<td valign="bottom" align="center">${SEBCPer}&nbsp;<b>%</b>&nbsp;</td>
			<td valign="bottom" align="center">${PHPer}&nbsp;<b>%</b>&nbsp;</td>
			<td valign="bottom" rowspan="2"><hdiits:caption
				captionid="Hr_nameofseats" bundle="${caption}" /></td>
			<td valign="bottom" rowspan="2"><hdiits:caption
				captionid="Hr_nameofemployee" bundle="${caption}" /></td>
			<td valign="bottom" rowspan="2"><hdiits:caption
				captionid="Hr_dateofjoin" bundle="${caption}" /></td>
			<td valign="bottom" rowspan="2"><hdiits:caption
				captionid="Hr_nameofempcategory" bundle="${caption}" /></td>
			<td align="center" valign="bottom">${SCPer}&nbsp;<b>%</b>&nbsp;</td>
			<td valign="bottom" align="center">${STPer}&nbsp;<b>%</b>&nbsp;</td>
			<td valign="bottom" align="center">${SEBCPer}&nbsp;<b>%</b>&nbsp;</td>
			<td valign="bottom" align="center">${PHPer}&nbsp;<b>%</b>&nbsp;</td>
			<td valign="bottom" rowspan="2" ><hdiits:caption
				captionid="Hr_transfer" bundle="${caption}" /><br><hdiits:caption
				captionid="Hr_retired" bundle="${caption}" /><br><hdiits:caption
				captionid="Hr_promotion" bundle="${caption}" />
			</td>
			<td valign="bottom" rowspan="2"><hdiits:caption
				captionid="Hr_specremarks" bundle="${caption}" /></td>
		</tr>


		<%-- Second row for header--%>
		<tr class="datatableheader">
			<td><hdiits:caption	captionid="Hr_sclabl" bundle="${caption}" /></td>
			<td><hdiits:caption	captionid="Hr_stlabl" bundle="${caption}" /></td>
			<td><hdiits:caption	captionid="Hr_sebclabl" bundle="${caption}" /></td>
			<td><hdiits:caption	captionid="Hr_phlabl" bundle="${caption}" /></td>

			<td><hdiits:caption	captionid="Hr_sclabl" bundle="${caption}" /></td>
			<td><hdiits:caption	captionid="Hr_stlabl" bundle="${caption}" /></td>
			<td><hdiits:caption	captionid="Hr_sebclabl" bundle="${caption}" /></td>
			<td><hdiits:caption	captionid="Hr_phlabl" bundle="${caption}" /></td>

			<td><hdiits:caption	captionid="Hr_sclabl" bundle="${caption}" /></td>
			<td><hdiits:caption	captionid="Hr_stlabl" bundle="${caption}" /></td>
			<td><hdiits:caption	captionid="Hr_sebclabl" bundle="${caption}" /></td>
			<td><hdiits:caption	captionid="Hr_phlabl" bundle="${caption}" /></td>
		</tr>
		<!-- previous Data -->

		
		<%--  	Row Data started	--%>

		<c:forEach var="rlist" items="${resValue.rosterlist}">
			<script>
				counterRoster=counterRoster+1;
			</script>
			
			<div id="tooltip_main${rlist.serialNo}" class="bubble_tooltip">
				<div class="bubble_top"><span></span></div>
				<div class="bubble_middle"><span id="tooltip${rlist.serialNo}"></span></div>
				<div class="bubble_bottom"></div>
		</div>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td id="${rlist.serialNo}" onmouseover= "xstooltip_show(event,'tooltip_main${rlist.serialNo}','tooltip${rlist.serialNo}', '${rlist.serialNo}')" onmouseout="xstooltip_hide('tooltip_main${rlist.serialNo}','tooltip${rlist.serialNo}')"><hdiits:hidden name="serial${rlist.serialNo}"
					default="${rlist.serialNo}" />
					<hdiits:hidden name="allocationId${rlist.serialNo}"
					default="${rlist.allocationId}" />
					<c:out value="${rlist.serialNo}" /></td>
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


				<td id="plan_category${rlist.serialNo}"><hdiits:select name="Categorysel${rlist.serialNo}"
					id="Categorysel${rlist.serialNo}" captionid="Category"  
					onchange="enableSearch(this)">
					<c:forEach var="c" items="${empcategory}">
						<hdiits:option value="${c.lookupId}">
							<c:out value="${c.lookupDesc}" />
						</hdiits:option>
					</c:forEach>
				</hdiits:select></td>


				<td style="display: none"></td>

				<td nowrap="nowrap"><hdiits:text readonly="true" name="empName${rlist.serialNo}" 
					id="empName${rlist.serialNo}"  /> <img  src="images/search_icon.gif" name="Search${rlist.serialNo}"  id="Search${rlist.serialNo}" tooltip="Click Here To Search Employee" onclick="SearchEmp(this)" style="display:none" />
					<img src="images/disable_search_icon.GIF" name="disSearch${rlist.serialNo}" id="disSearch${rlist.serialNo}" tooltip="" onclick="" /> 
					<hdiits:hidden
					name="userId${rlist.serialNo}" id="userId${rlist.serialNo}" /></td>
				<td><input type="text" size="6" readonly="readonly" id="lblDoj${rlist.serialNo}"
					name="lblDoj${rlist.serialNo}" /></td>
				<td>&nbsp;<label for="empName${rlist.serialNo}"
					id="lblDept${rlist.serialNo}"></label> <hdiits:hidden
					name="catId${rlist.serialNo}" id="catId${rlist.serialNo}" /> <hdiits:hidden
					name="lblDateOfJoin${rlist.serialNo}"
					id="lblDateOfJoin${rlist.serialNo}" /></td>
				<td id="sc_val${rlist.serialNo}">&nbsp;<label id="sc${rlist.serialNo}"></label> <hdiits:hidden
					name="hidsc${rlist.serialNo}" id="hidsc${rlist.serialNo}"
					default="0" /></td>
				<td id="st_val${rlist.serialNo}">&nbsp; <label id="st${rlist.serialNo}"></label><hdiits:hidden
					name="hidst${rlist.serialNo}" id="hidst${rlist.serialNo}"
					default="0" /></td>
				<td id="sebc_val${rlist.serialNo}">&nbsp; <label id="sebc${rlist.serialNo}"></label><hdiits:hidden
					name="hidsebc${rlist.serialNo}" id="hidsebc${rlist.serialNo}"
					default="0" /></td>
				<td id="ph_val${rlist.serialNo}">&nbsp; <label id="ph${rlist.serialNo}"></label><hdiits:hidden
					name="hidph${rlist.serialNo}" id="hidph${rlist.serialNo}"
					default="0" /></td>

				<td>&nbsp;</td>

				<td>
					<a href="javascript:void('remarks');" onclick=javascript:writeRemarks('row${rlist.serialNo}')><hdiits:caption captionid="HRMS.WriteMsg" bundle="${caption}"/></a>
					<INPUT type='textarea' name='remarks' id='text_row${rlist.serialNo}' value="" style="display:none"  maxlength="4s000"/>
					<input type="hidden" name='rwFlag_row${rlist.serialNo}' value="W" id='rwFlag_row${rlist.serialNo}' />
				</td>


			</tr>
			
		</c:forEach>

		<%--  Last row for the surplus--%>

		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td><LABEL id="prescsur" /></td>
			<td><LABEL id="prestsur" /></td>
			<td><LABEL id="presebcsur" /></td>
			<td><LABEL id="prephsur" /></td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td><LABEL id="scsur" /></td>
			<td><LABEL id="stsur" /></td>
			<td><LABEL id="sebcsur" /></td>
			<td><LABEL id="phsur" /></td>
		</tr>
	<script>
		var listCnt=1;
		var arrayCnt=0;
	</script>
	<c:forEach var="finalList" items="${finalList}"> 
	
		<script>
		
			document.getElementById('empName'+listCnt).value="${finalList.empName}";
			var dt=new Date();
			dt="${finalList.joinDate}";
			var arrDate=getDateAndTimeFromDateObj(dt);
			document.getElementById('lblDoj'+listCnt).value=arrDate[0];
			document.getElementById('lblDept'+listCnt).innerHTML="${finalList.actualCat}";
			document.getElementById('Categorysel'+listCnt).value="${finalList.plannedCatId}";
			document.getElementById('Categorysel'+listCnt).options[document.getElementById('Categorysel'+listCnt).selectedIndex].text="${finalList.plannedCat}";
			catArray[arrayCnt]="${finalList.plannedCat}";
			arrayCnt++;
			document.getElementById('userId'+listCnt).value="${finalList.userId}";
			document.getElementById('catId'+listCnt).value="${finalList.actualCatId}";
			listCnt=listCnt+1;
			
		</script>
	</c:forEach>

		<script>
	var table = document.getElementById('rostertb');   
   		var rows = table.getElementsByTagName("tr");   
   		
		for(i = previousSize+2; i < rows.length-1; i++){           
			 
    		 if(i % 2 == 0){ 
    			   rows[i].className = "even"; 
   			  }else{ 
   				    rows[i].className = "odd"; 
   				  }       

   		} 
		
		for(var i=1;i<=counterRoster;i++)
		{
			srId=i;
			presetCountForSurplus();
		}
		
</script>
	</table>
	</div>
	<center><input name="sub" value="Submit" type="button"
		onclick="submitDtls()" style="display:none" /><hdiits:button type="button"
		name="closeButton" value="Close" id="closeButton" captionid="Close"
		onclick="closewindow()" style="display:none" /></center>


	</div>
	</div>
	<!-- Hidden Parameters -->
	<hdiits:hidden name="vacancyNo" id="vacancyNo" />
	<hdiits:hidden name="reqId" id="reqId" default="${reqId}" />
	<hdiits:hidden name="textData" id="textData" default="" />
	<hdiits:hidden name="rwFlag" id="rwFlag" default="" />
	<hdiits:hidden name="designationId" id="designationId" default="${designationId}" />
<hdiits:hidden name="flag" id="flag" default="U" />
	<hdiits:validate controlNames="text"
		locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>
<script type="text/javascript">		

	document.getElementById('vacancyNo').value=counterRoster;
	
	
		initializetabcontent("maintab");

</script>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

<script type="text/javascript">		
var tempCatId=0;
for(var i=0;i<catArray.length;i++)
{
	tempCatId=i+1;
	var cmbObj=document.getElementById('Categorysel'+tempCatId);
				for(var k=0; k<cmbObj.options.length;k++)
				{
					if(cmbObj[k].text==catArray[i])
					{
						cmbObj.options[k].selected=true;
					}										
				}
}
</script>
