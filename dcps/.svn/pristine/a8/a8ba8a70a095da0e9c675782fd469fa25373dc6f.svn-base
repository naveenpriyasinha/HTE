<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

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

${resValue.msg}

<script type="text/javascript" language="JavaScript">
function submitForm(){ 
	win = window.open("common/progressbar.html",'','width=270,height=30,titlebar=0,toolbar=0,left=400,top=300');
	
	//bar1.showBar();
	
	//pause(4000);	
	return true;
}
function init()
{
	document.forms[0].scaleName.focus();
}
</script>



<hdiits:form name="frmScaleMaster" validate="true" method="POST"
	action="./hrms.htm?actionFlag=insertScaleData" >

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1">Scale Master</a></li>
		<li class="selected"><a href="#" rel="tcontent2">INSERT FORM2</a></li>
	</ul>
</div>
	
	
<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">

	<TABLE>  
	<TR>
		<TD>Scale Id</TD>			
		<TD>
			<hdiits:text name="scaleId" default="" caption="scaleid"  
			maxlength="10" readonly="true" style="background:gray;color:white" size="10"/>
		</TD>	
	</tr>
	<TR>
		<TD>Scale Name</TD>			
		<TD>
			<hdiits:text name="scaleName" default="" 
			caption="scalename"  maxlength="50" validation="txt.isrequired" />
		</TD>	
	</tr>
	<TR>
		<TD>Scale Description</TD>			
		<TD>
			<hdiits:text name="scaleDesc" default="" 
			caption="scaledesc"  maxlength="100" validation="txt.isrequired" size="60"/>
		</TD>	
	</tr>
	<TR>
		<TD>Scale Start Amount</TD>			
		<TD>
			<hdiits:number name="scaleStartAmt" default="" 
			caption="scalestartamt"  maxlength="50" validation="txt.isnumber,txt.isrequired"/>
		</TD>	
	</tr>
	<TR>
		<TD>Scale Increament Amount</TD>			
		<TD>
			<hdiits:number name="scaleIncrAmt" default="" 
			caption="scaleincramt"  maxlength="50" validation="txt.isnumber,txt.isrequired"/>
		</TD>	
	</tr>
	<TR>
		<TD>Scale End Amount</TD>			
		<TD>
			<hdiits:number name="scaleEndAmt" default="" 
			caption="scaleendamt"  maxlength="50" validation="txt.isrequired,txt.isnumber"/>
		</TD>	
	</tr>
	</TABLE>
	</div> 
	<div id="tcontent2" class="tabcontent" tabno="1">
	</div>
	<jsp:include page="../../core/tabnavigation.jsp" />
</div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />

<script type="text/javascript" language="javascript">
init();
</script>

</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>