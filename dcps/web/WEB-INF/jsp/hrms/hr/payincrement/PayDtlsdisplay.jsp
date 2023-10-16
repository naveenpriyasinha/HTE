<%try{%>

<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<fmt:setBundle basename="resources.hr.payincrement.PayInc" var="pyinc" scope="request" />




<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>	  
<c:set var="actionList" value="${resultValue.actionList}" > </c:set>
<html>
<script type="text/javascript">

function checkresp()
{

var flag=false;
var chkBx=document.forms[0].check;
if(chkBx.checked==true)
{
flag=true;

}



for(var i=0;i<chkBx.length;i++)

{

if(chkBx[i].checked==true)
{
flag=true;

}

}


if(!flag){

alert('<fmt:message  bundle="${pyinc}" key="payinc.alertchkbx"/>');
}

if(flag)
{
document.frmPayInc.action ="hrms.htm?actionFlag=forward";
document.frmPayInc.submit();
}
}
 

function checkMulti(j){

var chkBx=document.forms[0].check;
for(var i=0;i<chkBx.length;i++)
{
if(chkBx[i].checked=true)
{
chkBx[i].checked=false;
}
 if(i==j)
{
chkBx[i].checked=true;
}
}
}

function Closebt()
{	
	method="POST";
	document.frmPayInc.action="./hrms.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
	
	document.frmPayInc.submit();
}
</script>	
<hdiits:form name="frmPayInc" validate="true" method="POST"
	action="./hrms.htm?" encType="text/form-data">

	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption
			captionid="payinc.payinc" bundle="${pyinc}" captionLang="single"></hdiits:caption></b></a></li>
	</ul>
	</div>

	<div class="tabcontentstyle"><hdiits:hidden name="actionFlag"
		default="forward" />
	<div id="tcontent1" class="tabcontent" tabno="0">
 <hdiits:fieldGroup titleCaptionId="payinc.dtls_inc" bundle="${pyinc}" mandatory="true" >
	
 <c:set var="i" value="0"/>
<%int a=0; %>	



 <display:table name="${actionList}" id="row" export="false" style="width:100%" >
<tr></tr>
			<display:column class="tablecelltext" title=""
				headerClass="datatableheader">
				<hdiits:checkbox name="check" value="${row.userid}" onclick="checkMulti('${i}')" />
			</display:column>
			<display:column class="tablecelltext" titleKey="payinc.SerialNo" 
				headerClass="datatableheader" style="text-align: center"  value="<%= a=a+1 %>">
			</display:column>
			<display:column class="tablecelltext" titleKey="payinc.name"
				headerClass="datatableheader"  style="text-align: center" value="${row.empName}" />
			<display:column class="tablecelltext" titleKey="payinc.Desig"
				headerClass="datatableheader"  style="text-align: center" value="${row.designation}" />
			<display:column class="tablecelltext" titleKey="payinc.cubasal"
				headerClass="datatableheader" style="text-align: center" value="${row.salary}" />
			<display:setProperty name="export.pdf"  value="true" />
			<display:column class="tablecelltext" titleKey="payinc.lst_dt_inc"
				headerClass="datatableheader" style="text-align: center" > <fmt:formatDate value="${row.lastincDate}" pattern="dd/MM/yyyy"/></display:column>
			<display:setProperty name="export.pdf"  value="true" />
			<display:column class="tablecelltext" titleKey="payinc.due_dt_inc"
				headerClass="datatableheader" style="text-align: center" > <fmt:formatDate value="${row.actualincdate}" pattern="dd/MM/yyyy"/></display:column>
			<display:setProperty name="export.pdf" value="true" />
			<display:column class="tablecelltext" titleKey='payinc.lwp_1'
				headerClass="datatableheader" style="text-align: center" > ${row.lwp}</display:column>
			<display:setProperty name="export.pdf" value="true" />
			 <display:column class="tablecelltext"titleKey="payinc.efe_dt_inc" headerClass="datatableheader" style="text-align: center" ><fmt:formatDate value="${row.effectiveincdate}" pattern="dd/MM/yyyy"/></display:column>
			<display:setProperty name="export.pdf" value="true" />
           <c:set var="i" value="${i+1}"/>

</display:table>
</hdiits:fieldGroup>
<br><br><br><br>
<table align="center" id="SubBtns" style="display:none">
			<hdiits:button name="Submit" type="button" captionid="Payinc.Submit" onclick="checkresp();" bundle="${pyinc}"/>
	<hdiits:button type="button" captionid="Payinc.Close" name="Close" value="Close" onclick="Closebt()" bundle="${pyinc}"/>
			</table>
</div>

</div>
<c:if test="${not empty actionList}">
	<script type="text/javascript">
document.getElementById('SubBtns').style.display='';
	
		</script>
		</c:if>
	<script type="text/javascript">

		initializetabcontent("maintab")
		</script>
	<hdiits:validate controlNames="tesxt" locale='<%=(String)session.getAttribute("locale")%>' />

</hdiits:form>
</html>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>