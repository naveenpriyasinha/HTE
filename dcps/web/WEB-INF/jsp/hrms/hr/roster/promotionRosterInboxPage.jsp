
<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
<script type="text/javascript" src="script/hrms/hr/rosterScript/promRosterInbPage.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>

<c:set var="resValue" value="${result.resultValue}"></c:set>
<c:set var="rosterdtl" value="${resValue.rosterlist}"></c:set>
<c:set var="finalList" value="${resValue.finalList}"></c:set>
<c:set var="reqId" value="${resValue.reqId}"></c:set>
<fmt:setBundle basename="resources.hr.roster.rosterMst" var="caption"
	scope="request" />
<fmt:setBundle basename="resources.hr.roster.RosterAlertMsges"
	var="alertMsges" scope="request" />

<c:set var="SCPer" value="${resValue.SCPer}"></c:set>
<c:set var="STPer" value="${resValue.STPer}"></c:set>
<c:set var="SEBCPer" value="${resValue.SEBCPer}"></c:set>
<c:set var="PHPer" value="${resValue.PHPer}"></c:set>

<style> 
 		.odd{background-color: #CCCCCC;} 
 		.even{background-color: #999999;} 
 		.tra{background-color: #CCCFFF;}
 		.pro{background-color: #FFFCCC;}
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

function xstooltip_show(e,maintooltipId,tooltipId, parentId)
{
//Showing tooltip onmouseover
		if(document.all)e = event;
    	it = document.getElementById(maintooltipId);
   		tooltip_content = document.getElementById(tooltipId);
         
        img = document.getElementById(parentId); 
    	
    	//Giving the content to the tooltip
    	 
    	var empname = document.getElementById("empName"+parentId).innerHTML;
    	var dateofjoining= document.getElementById("joindate"+parentId).innerHTML;
    	var plan_category= document.getElementById("plan_category"+parentId).innerHTML;
    	var act_category= document.getElementById("act_category"+parentId).innerHTML;
    	var tmpScVal=0;
    	var tmpStVal=0;
    	
 		for(var i=1;i<=parentId;i++)
 		{
 			var scname="sc"+i;
 			var stname="st"+i;
 			
 			
 			
 		
 			var scValue=document.getElementById(scname).innerHTML;
			var stValue=document.getElementById(stname).innerHTML;
			
 			if(scValue.length!=0)
 			{
 				if(scValue==0)
 				{
 					tmpScVal=tmpScVal*1+scValue*1;
				}
				else
				{
					tmpScVal=scValue*1;
				}
 			}
 			if(stValue.length!=0)
 			{
 				if(stValue==0)
 				{
 					tmpStVal=tmpStVal*1+stValue*1;
				}
				else
				{
					tmpStVal=stValue*1;
				}
 			}
 			 			
 		}
		
    	tooltip_content.innerHTML = empname + '<br>' + dateofjoining+'<br>'+"<fmt:message key="HRMS.PlannedCat" bundle="${alertMsges}" />:<b>"+plan_category+'</b><br>'+"<fmt:message key="HRMS.ActualCat" bundle="${alertMsges}" />:<b> "+act_category+"</b><br>"+"<b> SC:</b>"+tmpScVal+"<b> ST:</b>"+tmpStVal;
    	
    	it.style.display = 'block';
    	
    	//Setting the position of the tooltip
    	
    	var posx=0,posy=0;
	if(e==null) e=event;
	if(e.pageX || e.pageY){
    posx=e.pageX; posy=e.pageY;
    }
	else if(e.clientX || e.clientY){
    if(document.documentElement.scrollTop){
        posx=e.clientX+document.documentElement.scrollLeft;
        posy=e.clientY+document.documentElement.scrollTop;
        }
    else{
        posx=e.clientX+document.body.scrollLeft;
        posy=e.clientY+document.body.scrollTop;
        }
    }
   it.style.top=(posy-180)+"px";
   it.style.left=(posx-104)+"px";
    	
    	        
    
}
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
	var href='./hrms.htm?actionFlag=getRosterAllEmpSearch';
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
	top:-15px;
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


<hdiits:form name="Roster" validate="true" method="POST" action="" >
	<hdiits:hidden name="empSearchName" id="empSearchName" />
	<hdiits:hidden name="deptname" id="deptname" />
	<hdiits:hidden name="doj" id="doj" />
	<hdiits:hidden name="hidUserId" id="hidUserId" />
	
	<hdiits:hidden name="hidcatId" id="hidcatId" />
	<hdiits:hidden name="search" id="search" />
	<hdiits:hidden name="totalLength" id="totalLength" />
	<hdiits:hidden name="wffileId_hidden" id="wffileId_hidden"/>
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"> <hdiits:caption captionid="Hr_recrallocation" bundle="${caption}" /> </a></li>
	</ul>
	</div>
	

 	<div class="tabcontentstyle" style="height: 100%">


	<div id="tcontent1" class="tabcontent" tabno="0">
	
	<div style="width: 100%;height: 100%;overflow:scroll">
	<table border="3" bordercolor="black" class="datatable" id="rostertb"	style="width: 100% ;height: 100%">

		<%-- First row for header--%>
		<tr class="datatableheader">
			<td valign="bottom" colspan="2" rowspan="1"><hdiits:caption	captionid="Hr_resservedseat" bundle="${caption}"/></td>
			<td valign="bottom" rowspan="2"><hdiits:caption	captionid="Hr_lastrecruitment" bundle="${caption}"/></td>
			<td valign="bottom" rowspan="2"><hdiits:caption	captionid="Hr_serialno" bundle="${caption}"/></td>
			<td align="center" valign="bottom">${SCPer}%</td>
			<td valign="bottom" align="center">${STPer}%</td>
			<td valign="bottom" rowspan="2"><hdiits:caption captionid="Hr_nameofseats" bundle="${caption}"/></td>
			<td valign="bottom" rowspan="2"><hdiits:caption captionid="Hr_nameofemployee" bundle="${caption}"/></td>
			<td valign="bottom" rowspan="2"><hdiits:caption captionid="Hr_dateofjoin" bundle="${caption}"/></td>
			<td valign="bottom" rowspan="2"><hdiits:caption captionid="Hr_nameofempcategory" bundle="${caption}"/></td>
			<td align="center" valign="bottom">${SCPer}%</td>
			<td valign="bottom" align="center">${STPer}%</td>
			<td valign="bottom" rowspan="2" ><hdiits:caption
				captionid="Hr_transfer" bundle="${caption}" /><br><hdiits:caption
				captionid="Hr_retired" bundle="${caption}" /><br><hdiits:caption
				captionid="Hr_promotion" bundle="${caption}" /></td>
			<td valign="bottom" rowspan="2"><hdiits:caption captionid="Hr_specremarks" bundle="${caption}"/></td>	
		</tr>


		<%-- Second row for header--%>
		<tr class="datatableheader">
			<td><hdiits:caption	captionid="Hr_sclabl" bundle="${caption}" /></td>
			<td><hdiits:caption	captionid="Hr_stlabl" bundle="${caption}" /></td>
			
			
			<td><hdiits:caption	captionid="Hr_sclabl" bundle="${caption}" /></td>
			<td><hdiits:caption	captionid="Hr_stlabl" bundle="${caption}" /></td>
			
			
			<td><hdiits:caption	captionid="Hr_sclabl" bundle="${caption}" /></td>
			<td><hdiits:caption	captionid="Hr_stlabl" bundle="${caption}" /></td>
			
		</tr>
		
		<%--  	Row Data started	--%>

		<c:forEach var="rlist" items="${finalList}">
			<script>
		counterRoster=counterRoster+1;
	</script>
	<div id="tooltip_main${rlist.serialNo}" class="bubble_tooltip">
				<div class="bubble_top"><span></span></div>
				<div class="bubble_middle"><span id="tooltip${rlist.serialNo}"></span></div>
				<div class="bubble_bottom"></div>
		</div>
			<tr>
				
				<td >&nbsp;<label id="finalsc${rlist.serialNo}"></label> </td>		
				<td >&nbsp;<label id="finalst${rlist.serialNo}" ></label></td>	
				<td id="crtDate${rlist.serialNo}">
					<fmt:parseDate var="rday" pattern="yyyy-MM-dd" value="${rlist.createdDate}"/>
					<fmt:formatDate value="${rday}"  pattern="yyyy"/>
				</td>
				<td id="${rlist.serialNo}" onmouseover= "xstooltip_show(event,'tooltip_main${rlist.serialNo}','tooltip${rlist.serialNo}', '${rlist.serialNo}')" onmouseout="xstooltip_hide('tooltip_main${rlist.serialNo}','tooltip${rlist.serialNo}')">
				<hdiits:hidden name="serial${rlist.serialNo}"
					default="${rlist.serialNo}" /><c:out value="${rlist.serialNo}" /></td>
				<td><hdiits:hidden name="scval${rlist.serialNo}"
					default="${rlist.scVal}" /><LABEL id="lscval${rlist.serialNo}" /><c:out
					value="${rlist.scVal}" /></td>
				<td><hdiits:hidden name="stval${rlist.serialNo}"
					default="${rlist.stVal}" /><LABEL id="lstval${rlist.serialNo}" /><c:out
					value="${rlist.stVal}" /></td>
			

				<td id="plan_category${rlist.serialNo}"><label id="Categorysel${rlist.serialNo}"><c:out value="${rlist.plannedCat}" /></label>
				<hdiits:hidden name="plannedCatId${rlist.serialNo}" id="plannedCatId${rlist.serialNo}" default="${rlist.plannedCatId}"/>			
				 </td>
				<td id="empName${rlist.serialNo}"><c:out value="${rlist.empName}" />
				</td>
				<td id="joindate${rlist.serialNo}">
				<fmt:parseDate var="bday" pattern="yyyy-MM-dd HH:mm:ss" value="${rlist.joinDate}"/>
				<fmt:formatDate value="${bday}"  pattern="MM/dd/yyyy"/>
				</td>
				
				<td id="act_category${rlist.serialNo}"><label id="lblDept${rlist.serialNo}"><c:out value="${rlist.actualCat}" /></label>
				<hdiits:hidden name="catId${rlist.serialNo}" id="catId${rlist.serialNo}" default="${rlist.actualCatId}" />
				</td>
				
				<td >&nbsp;<label id="sc${rlist.serialNo}"></label> <hdiits:hidden name="hidsc${rlist.serialNo}" id="hidsc${rlist.serialNo}" default="0"/> </td>		
				<td >&nbsp; <label id="st${rlist.serialNo}" ></label><hdiits:hidden name="hidst${rlist.serialNo}" id="hidst${rlist.serialNo}" default="0" />  </td>	
			
				<td>
					<c:if test="${rlist.empStatus eq 'TRA'}">
						<c:out value="TRANSFERED"></c:out>
						<script>
							transferArray[transferCnt]="${rlist.serialNo}";
							transferCnt=transferCnt+1;
						</script>
					</c:if>
					<c:if test="${rlist.empStatus eq 'PRO'}">
						<c:out value="PROMOTED"></c:out>
						<script>
							proArry[proCnt]="${rlist.serialNo}";
							proCnt=proCnt+1;
						</script>
					</c:if>
					<c:if test="${rlist.empStatus eq ''}">
						<c:out value="&nbsp"></c:out>
					</c:if>
				</td>		
				<td>
				   	<a href="javascript:void('remarks');" onclick=javascript:writeRemarks('row${rlist.serialNo}')><hdiits:caption captionid="HRMS.ReadMsg" bundle="${caption}"/></a>
					<INPUT type='textarea' name='remarks' id='text_row${rlist.serialNo}' value='${rlist.remarks}' style="display:none"  maxlength="4s000"/>
					<input type="hidden" name='rwFlag_row${rlist.serialNo}' value="R" id='rwFlag_row${rlist.serialNo}' />
				
				</td>
				
			</tr>
				<script type="text/javascript">
			totalLength=totalLength+1;
		</script>
		</c:forEach>

		<%--  Last row for the surplus--%>

		



		<tr class="datatableheader">
			<td>&nbsp;</td>		
			<td>&nbsp;</td>
			<td>&nbsp;</td>		
			<td>&nbsp;</td>
			
			<td><LABEL id="prescsur"/>&nbsp;</td>		
			<td><LABEL id="prestsur"/>&nbsp;</td>	
			
			<td>&nbsp;</td>		
			<td>&nbsp;</td>
			<td>&nbsp;</td>		
			<td>&nbsp;</td>
			<td><LABEL id="scsur"/>&nbsp;</td>		
			<td><LABEL id="stsur"/>&nbsp;</td>	
			
			<td>&nbsp;</td>		
			<td>&nbsp;</td>
		</tr>
		

	<script>
	
		
		document.getElementById('totalLength').value = totalLength;
		
		for(var i=1;i<=totalLength;i++)
		{
			srId=i;
			setCountForSurplus();
		}
		
</script>
	</table>
	</div>
	<!-- Hidden Parameters -->
	<hdiits:hidden name="vacancyNo" id="vacancyNo" />
	<hdiits:hidden name="reqId" id="reqId" default="${reqId}" />
	<hdiits:hidden name="textData" id="textData" default="" />
	<hdiits:hidden name="rwFlag" id="rwFlag" default="" />
	
	</div>
	</div>
	<hdiits:validate controlNames="text" locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>
<script>
	document.getElementById('vacancyNo').value=counterRoster;
	var table = document.getElementById('rostertb');   
   	var rows = table.getElementsByTagName("tr");   
   	for(var j=1;j<counterRoster;j++)
   	{
		document.getElementById('crtDate'+j).innerHTML="&nbsp";
   	}	
   	var tmpScVal=0;
    	var tmpStVal=0;
    	var tmpSebcVal=0;
    	var tmpPhVal=0;
    	
    	for(var i=counterRoster;i>=1;i--)
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
    	for(var i=counterRoster;i>=1;i--)
    	{
	  	    var stname="st"+i;
	    	var stValue=document.getElementById(stname).innerHTML;
	    	if(stValue.length!=0)
 			{
 				tmpStVal=stValue;
 				break;
 			}
	    	
    	}
    	
    	for(var j=1;j<=counterRoster;j++)
   		{
			document.getElementById('finalsc'+j).innerHTML=tmpScVal;
			document.getElementById('finalst'+j).innerHTML=tmpStVal;
			
   		}
   		for(var j=1;j<counterRoster;j++)
   		{
			document.getElementById('finalsc'+j).innerHTML="&nbsp";
			document.getElementById('finalst'+j).innerHTML="&nbsp";
			
   		}
    	
	for(i = 2; i <counterRoster+2; i++){           
			 
    		if(i % 2 == 0){ 
    		   rows[i].className = "even"; 
   			 }else{ 
   			    rows[i].className = "odd"; 
   			  }       

   	} 
   	
   	
   	var tmpCnt=0;
   	for(var i=0;i<transferArray.length;i++)
   	{
   		tmpCnt=transferArray[i]*1+1;
   		
   		rows[tmpCnt].className="tra";
   	}
   	var proTmpCnt=0;
   	for(var i=0;i<proArry.length;i++)
   	{
   		proTmpCnt=proArry[i]*1+1;
   		
   		rows[proTmpCnt].className="pro";
   	}
	initializetabcontent("maintab");
</script>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
