
<%
try {
%>


<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>



<html>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<fmt:setBundle basename="resources.hr.payincrement.PayInc" var="pyinc" scope="request" />
	

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="MonthList" value="${resValue.MonthList}"></c:set>

<c:set var="YearList" value="${resValue.YearList}"></c:set>
<script type="text/javascript">


function validate()
{
if( document.frmPayInc.Year.value=='Select' )
{
alert('<fmt:message  bundle="${pyinc}" key="payinc.alert1"/>');

}
else if(document.frmPayInc.Month.value=='Select')
{
alert('<fmt:message  bundle="${pyinc}" key="payinc.alert2"/>');
}

else{
document.getElementById('btnSubmit').disabled='true';
document.frmPayInc.action ="hrms.htm?actionFlag=PayInc";
document.frmPayInc.submit();
}

}
function Closebt()
{	
	method="POST";
	document.frmPayInc.action="./hrms.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
	
	document.frmPayInc.submit();
}
</script>
<hdiits:form name="frmPayInc" validate="true" method="POST" action="./hrms.htm?" encType="text/form-data">
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption
			captionid="payinc.payinc" bundle="${pyinc}" captionLang="single"></hdiits:caption></b></a></li>
	</ul>
	</div>

	<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">
          <!-- For Header -->
          <hdiits:fieldGroup titleCaptionId="payinc.search" bundle="${pyinc}" mandatory="true" >
   

	<br><br><br><br>

	<hdiits:table align="center" height="20%">


		<hdiits:tr></hdiits:tr>
		<hdiits:tr>
			<hdiits:td>
				<b><hdiits:caption captionid="payinc.year" bundle="${pyinc}" ></hdiits:caption></b>
				<hdiits:td>
					<hdiits:select captionid="payinc.year" bundle="${pyinc}" 
						name="Year" mandatory="true" validation="sel.isrequired">
						<hdiits:option value="Select"><fmt:message key="PayInc.select" bundle="${pyinc}" /></hdiits:option>
						<c:forEach var="Year" items="${YearList}">
							<option value=<c:out value="${Year}"/>><c:out
								value="${Year}" /></option>
						</c:forEach>
					</hdiits:select>
				</hdiits:td>

			</hdiits:td>

			<hdiits:td>
			</hdiits:td>
			<hdiits:td>
			</hdiits:td>
			<hdiits:td>

				<b><hdiits:caption captionid="payinc.month" bundle="${pyinc}"></hdiits:caption></b>
				<hdiits:td>
					<hdiits:td>
						<hdiits:td>
							<hdiits:select captionid="payinc.month" bundle="${pyinc}"
								name="Month" validation="sel.isrequired" mandatory="true"
								sort="false">
								<hdiits:option value="Select"><fmt:message key="PayInc.select" bundle="${pyinc}" /></hdiits:option>
								<c:forEach var="Month" items="${MonthList}">
									<option value=<c:out value="${Month.lookupShortName}"/>><c:out
										value="${Month.lookupDesc}" /></option>
								</c:forEach>
							</hdiits:select>
						</hdiits:td>
					</hdiits:td>
				</hdiits:td>

			</hdiits:td>

		</hdiits:tr>

	</hdiits:table>
	<br><br>
	<table width="100%" align="center" 	style="margin: 5px 0px;  font-family: arial; color: #333333;border: solid 1px #6B2700; width: 50%; clear: center; display:none;" id="Norecord">
	<tr>
		<td align="center">
		<hdiits:caption captionid="Payinc.rntfnd" bundle="${pyinc}" captionLang="single"/>
		</td>
	</tr>
		

</table> 
	</hdiits:fieldGroup>
	<br><br><br><br>
	<table align="center">
	<hdiits:button type="button" captionid="Payinc.Submit" value="Submit" onclick="validate();" name="btnSubmit" bundle="${pyinc}"/>
	
	<hdiits:button type="button" captionid="Payinc.Reset" name="Reset" value="Reset" onclick="reset();" bundle="${pyinc}"/>
	<hdiits:button name="close" type="button" captionid="Payinc.Close" bundle="${pyinc}" onclick="Closebt()"/>
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
	
</hdiits:form>
</html>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
