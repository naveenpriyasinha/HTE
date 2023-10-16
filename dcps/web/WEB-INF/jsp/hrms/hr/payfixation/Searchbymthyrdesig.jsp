<%
try {
%>


<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<fmt:setBundle basename="resources.hr.payfixation.PayRelated" var="PayLab" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="MonthList" value="${resValue.MonthList}"></c:set>

<c:set var="YearList" value="${resValue.YearList}"></c:set>
<html>
<script type="text/javascript">
function show(){
document.getElementById("show1").style.display="none";
}
function show1(){

document.getElementById("show1").style.display="";
}
function Closebt()
{	
	method="POST";
	document.frmPayFix.action="./hrms.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
	
	document.frmPayFix.submit();
}

</script>


<hdiits:form name="frmPayFix" validate="true" method="POST"
	action="./hrms.htm?" encType="text/form-data">

	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption
			captionid="Pf.PayFixation" bundle="${PayLab}" captionLang="single"></hdiits:caption></b></a></li>
	</ul>
	</div>

	<div class="tabcontentstyle">
	<hdiits:hidden name="actionFlag" default="searchpayfix" />
	<div id="tcontent1" class="tabcontent" tabno="0">





<hdiits:fieldGroup titleCaptionId="Pf.PayFixation" bundle="${PayLab}" mandatory="true" >
	
<table width="100%" cellpadding="60" height="60%">
<tr>
<td></td>

      <td><hdiits:radio name="option" value="1" mandatory="true" validation="sel.isradio"  default="" onclick="show1();" captionid="Pf.searchpayfixationdate" bundle="${PayLab}" /></td>
         

	<td><hdiits:radio name="option" validation="sel.isradio" value="0"  default="" onclick="show1();" captionid="Pf.searchpayincrdate" bundle="${PayLab}"/></td>
	</tr>

	</table>
	<hdiits:table id="show1" width="100%">


		<tr></tr>
		<hdiits:tr>
		<td width="30%"></td>
			<hdiits:td width="25%">
				<b><hdiits:caption captionid="Pf.Year" bundle="${PayLab}"></hdiits:caption></b>
				
					<hdiits:select captionid="Pf.Year" bundle="${PayLab}"
						name="Year" mandatory="true" validation="sel.isrequired">
						<hdiits:option value="Select"><fmt:message key="Payfix.select" bundle="${PayLab}" /></hdiits:option>
						<c:forEach var="Year" items="${YearList}">
							<option value=<c:out value="${Year}"/>><c:out
								value="${Year}" /></option>
						</c:forEach>
					</hdiits:select>
				</hdiits:td>

		



			
			
			<hdiits:td>

				<b><hdiits:caption captionid="Pf.Month" bundle="${PayLab}"></hdiits:caption></b>
				
						
							<hdiits:select captionid="Pf.Month" bundle="${PayLab}"
								name="Month" mandatory="true" validation="sel.isrequired" sort="false">
								<hdiits:option value="Select"><fmt:message key="Payfix.select" bundle="${PayLab}" /></hdiits:option>
								<c:forEach var="Month" items="${MonthList}">
									<option value=<c:out value="${Month.lookupShortName}"/>><c:out
										value="${Month.lookupDesc}" /></option>
								</c:forEach>
							</hdiits:select>
						
					</hdiits:td>
				

			
		</hdiits:tr>

		
		
		
		
		
		<script>show();</script>
		
		

		</hdiits:table>
		<br><br>
		<table width="100%" align="center" 	style="margin: 5px 0px;  font-family: arial; color: #333333;border: solid 1px #6B2700; width: 50%; clear: center; display:none;" id="Norecord">
	<tr>
		<td align="center">
		<hdiits:caption captionid="Pf.Recordnotfound" bundle="${PayLab}" captionLang="single"/>
		</td>
	</tr>
		

</table> 
		</hdiits:fieldGroup>
		<br><br><br><br><br><br>
		<table align="center">
		<hdiits:formSubmitButton name="Submit" type="button" captionid="Pay.Submit" bundle="${PayLab}"/>
	
	<hdiits:button type="button" captionid="Pay.Reset" name="Reset" value="Reset" onclick="reset();" bundle="${PayLab}"/>
	<hdiits:button name="close" type="button" captionid="Pay.Close" bundle="${PayLab}" onclick="Closebt()"/>
	</table>
	<script type="text/javascript">
	
	if('${resValue.noRec}'=="1")
	{
	document.getElementById('Norecord').style.display='';
	}
	</script>
		</div>
		
	</div>
	
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
