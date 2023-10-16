<%
try {
	
%>


<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>



<fmt:setBundle basename="resources.hr.payfixation.PayRelated" var="PayLab" scope="request"/>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	

<c:set var="EmpfixList" value="${resValue.EmpfixList}"> </c:set>
<c:set var="scalelist" value="${resValue.scalelist}"> </c:set>

<c:set var="hrPayfixMst" value="${resValue.hrPayfixMst}" />
<c:set var="option2" value="${resValue.option2}" />
<c:set var="option2date" value="${resValue.option2date}" />
<c:set var="option11" value="${resValue.option11}" />
<c:set var="option11date" value="${resValue.option11date}" />
<c:set var="option12" value="${resValue.option12}" />
<c:set var="option12date" value="${resValue.option12date}" />

<html>

<script type="text/javascript">

function getDate1()
   {
   		var dt= new Date();
   		var dd1=dt.getDate();
   		var mm1=dt.getMonth()+1;
   		var yy1=dt.getYear();
   		var dt1=dd1+'/'+mm1+'/'+yy1;
		document.getElementById("date1").innerHTML=dt1;

   }
  
   

function insert(){
document.frmVac.action="hrms.htm?actionFlag=transac";
document.frmVac.submit();

}
function show1(){
}
function show(mode){

if(mode==1){
document.getElementById("comparison").style.display="";
document.getElementById("display1").style.display="";
document.getElementById("display2").style.display="none";
}
else{
document.getElementById("comparison").style.display="none";
document.getElementById("display1").style.display="none";
document.getElementById("display2").style.display="";

}


}
function Closebt()
{	
	method="POST";
	document.frmVac.action="./hrms.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
	
	document.frmVac.submit();
}



</script>

</html>

<hdiits:form name="frmVac" validate="true" method="POST"  action="./hrms.htm?" encType="text/form-data" >

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>
		<hdiits:caption captionid="Pf.PayFixation" bundle="${PayLab}" captionLang="single"></hdiits:caption></b></a></li>
		
	</ul>
	</div>
	
	<div class="tabcontentstyle">
	
	<hdiits:hidden name="actionFlag" default="empseldate"/>
	<div id="tcontent1" class="tabcontent" tabno="0">	

<hdiits:hidden name="payfixid" default="${EmpfixList.payFixId}"/>
 
   <%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>
   <br>
    <hdiits:fieldGroup titleCaptionId="Pf.fix" bundle="${PayLab}" mandatory="true" id="payfixfieldGrp">
   
	<table width="100%">
	<tr>
	<td width="25%"> 
	<b><hdiits:caption captionid="Pf.adddate" bundle="${PayLab}"></hdiits:caption></b>
	</td>
	<td width="25%">
	<fmt:formatDate value="${EmpfixList.applDate}" pattern="dd/MM/yyyy"/>
	</td>
	
	  
	  <td width="25%">
		<b><hdiits:caption captionid="Pf.prepay" bundle="${PayLab}"></hdiits:caption></b></td>
		   <td width="25%"><c:out  value="${hrPayfixMst.revPay}"default=""></c:out>
	       </td>
	       </tr>
	       
		
		<tr></tr>
		<tr>
		<td width="25%">
		<b><hdiits:caption captionid="Pf.presentscale" bundle="${PayLab}"></hdiits:caption></b>
	       </td>
	       <td width="25%">
	         <c:out  value="${hrPayfixMst.revPayScale.scaleStartAmt}"default=""></c:out><c:out value="-"/><c:out  value="${hrPayfixMst.revPayScale.scaleIncrAmt}"default=""/><c:out value="-"/><c:out  value="${hrPayfixMst.revPayScale.scaleEndAmt}"default=""/>
	       </td>
	       <td width="25%">
		<b><hdiits:caption captionid="Pf.higher" bundle="${PayLab}"></hdiits:caption></b>
	       </td>
	       
	<td width="25%">
	<c:out  value="${scalelist.scaleStartAmt}"default=""/><c:out value="-"/><c:out  value="${scalelist.scaleIncrAmt}"default=""/><c:out value="-"/><c:out  value="${scalelist.scaleEndAmt}"default=""/>
	</td>
	       	
     
            </tr>
	
	</table>
	
	      <table>
 <tr> 	  
 <tr>
      <td width="100%"><hdiits:radio name="accept" value="1"  validation="sel.isradio" captionid="Pf.Yes" bundle="${PayLab}" default="" errCaption="Radio Button" captionLang="single"/> <b><fmt:formatDate value="${EmpfixList.incrDate}" pattern="dd/MM/yyyy"/></b></td>
      </tr>
      <tr>
	<td><hdiits:radio name="accept" validation="sel.isradio" value="0" captionid="Pf.No" bundle="${PayLab}" default="" mandatory="true" captionLang="single"/><b><fmt:formatDate value="${EmpfixList.payFixDate}" pattern="dd/MM/yyyy"/></b></td>
	</tr>
	
	<tr>
	<td><u><b><hdiits:caption captionid="Pf.comparison" bundle="${PayLab}"/></b></u>
	</td>
	</tr>
	
	
	<tr>
	<td>
	<input name="Display" value="V" type="button" onclick="show(1);" id="display2" style="display: none"/>
	<input name="Display" value="^" type="button" onclick="show(2);" style="display: none" id="display1"/>

	</td>
	</tr>
	</table>
	
	


<table id="comparison" width="60%" align="center" border="1"  bordercolor="black" bgcolor="white" >

<tr>
<td width="15%" align="center" ><b><hdiits:caption captionid="Pf.Options" bundle="${PayLab}"/></b></td>
<td width="15%" align="center" ><b><hdiits:caption captionid="Pf.efffrom" bundle="${PayLab}"/></b>
</td>
<td width="15%" align="center" ><b><hdiits:caption captionid="Pf.newbasic" bundle="${PayLab}"/></b>
</td>
<td width="15%" align="center" ><b><hdiits:caption captionid="Pf.nextincr" bundle="${PayLab}"/></b>
</td>

</tr>

<tr>
<th width="15%" rowspan="2" align="center" ><b><hdiits:caption captionid="Pf.option1" bundle="${PayLab}"/></b></th>
<td width="15%" align="center"><fmt:formatDate value="${EmpfixList.payFixDate}" pattern="dd/MM/yyyy"/></td>
<td width="15%" align="center"><c:out value="${option11}" default=""/></td>
<td width="15%" align="center"><c:out value="${option11date}" default=""/></td>
</tr>

<tr>
<td width="15%" align="center"><c:out value="${option11date}" default=""/></td>
<td width="15%" align="center"><c:out value="${option12}" default=""/></td>
<td width="15%" align="center"><c:out value="${option12date}" default=""/></td>
</tr>

<tr>
<td width="15%" align="center" ><b><hdiits:caption captionid="Pf.option2" bundle="${PayLab}"/></b></td>
<td width="15%" align="center"><fmt:formatDate value="${EmpfixList.payFixDate}" pattern="dd/MM/yyyy"/></td>
<td width="15%" align="center"><c:out value="${option2}" default=""/></td>
<td width="15%" align="center"><c:out value="${option2date}" default=""/></td>
</tr>


</table>
</hdiits:fieldGroup>
<br><br><br><br>
<table align="center">
	
	
<hdiits:formSubmitButton name="Submit" type="button" captionid="Pay.Submit"  bundle="${PayLab}"/>

	
	<hdiits:button type="button" captionid="Pay.Close" name="Close" value="Close" onclick="Closebt()" bundle="${PayLab}"/>
	</table>

	</div>	
	
 	</div>
	<script type="text/javascript">

		initializetabcontent("maintab")
		</script>
	<hdiits:validate controlNames="tesxt" locale='<%=(String)session.getAttribute("locale")%>' />
	
	

</hdiits:form>
<%
} catch (Exception e) {
		e.printStackTrace();
	}
%>
