
<%
try {
%>
<%@ include file="/WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>


<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/xmldom.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/hrms/eis/showImage.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="script/leave/ajax_saveData.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/base64.js"/>"></script>
<script type="text/javascript" src="script/hrms/ess/gpf/gpfNewAcc.js"></script>

<fmt:setBundle basename="resources.ess.gpf.gpfLables" var="gpfLables" scope="request" />
<link rel="stylesheet" href="<c:url value="/themes/hdiits/tabcontent.css"/>" type="text/css" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="EmpDet" value="${resValue.EmpDet}"></c:set>
<c:set var="allDtlMap" value="${resValue.allDtlMap}"></c:set>
<c:set var="nomineeDetailsCombo" value="${resValue.nomineeDetailsCombo}"></c:set>
<c:set var="cmnLookupMst2" value="${resValue.cmnLookupMst2}"></c:set>
<c:set var="emoulmentAmt" value="${resValue.emoulmentAmt}"></c:set>
<c:set var="remk" value="${resValue.remk}"></c:set>
<c:set var="finalListStr" value="${resValue.finalListStr}"></c:set>
<c:set var="Address" value="${resValue.Address}"></c:set>
<c:set var="finalList" value="${resValue.finalList}"></c:set>
<c:set var="newAccReqRaised" value="${resValue.newAccReqRaised}"></c:set>
<c:set var="validateRuleEngine" value="${resValue.validateRuleEngine}"></c:set>  

<c:set var="empPhtoId" value="${resValue.empPhtoId}"></c:set>
<c:set var="mpgSrNo" value="${resValue.mpgSrNo}"></c:set>
<c:set var="empThumpId" value="${resValue.empThumpId}"></c:set>
<c:set var="mpgSrNoThumb" value="${resValue.mpgSrNoThumb}"></c:set>

<script>

var totShare=0;

function calcAmt(val)
{
	var subAmt=(val.value)*("${allDtlMap.basicSalary}")/100;
	document.getElementById("rate_emolument").value=decimalPoint(subAmt);
}


function callOnSubmit()
{

var validData=true;
if("${newAccReqRaised}">0)
{
	document.getElementById("newAccReqRaised").style.display='block';
	validData=false;
	return;
}

if(totShare==0)
{
	hideAll();
	document.getElementById("case4").style.display='block';
	validData=false;
	return;
}

else if(totShare!=100)
{
	hideAll();
	document.getElementById("case5").style.display='block';
	validData=false;
	return;
}


if(""==document.forms.frmBF.rate_emolument.value.length)
{
	document.getElementById("case0").style.display='block';
	document.getElementById("case1").style.display='none';
	document.getElementById("case2").style.display='none';
	document.getElementById("case3").style.display='none';

	validData=false;
	return;
}

var subsAmt=document.forms.frmBF.rate_emolument.value;
var subAmtLength=document.forms.frmBF.rate_emolument.value.length;
var basicSalary=document.forms.frmBF.hiddenSalary.value;
var minSubsAmt=parseInt(basicSalary/10);
var i=0;
while ((i < subAmtLength) && (subsAmt.charAt(i) != ".") && (subsAmt.charAt(i) != "/") ) 
{
	i++
}
if ((i >= subAmtLength) || (subsAmt.charAt(i) != "."  && subsAmt.charAt(i) != "/"))
{
	validData=true;
}
else
{
	document.getElementById("case1").style.display='block';
	document.getElementById("case0").style.display='none';
	document.getElementById("case2").style.display='none';
	document.getElementById("case3").style.display='none';
	validData=false;
	return;
}


if(validData==true)
{
	document.frmBF.action="hrms.htm?actionFlag=gpfNewAcc";
	window.document.frmBF.submit();
}
}


var mikExp = /[$\\@\\\#%\^\&\*\(\)\[\]\+\_\{\}\`\~\=\|]/;

  
</script>


<hdiits:form name="frmBF" validate="true" method="POST"
	encType="multipart/form-data">
	<hdiits:hidden name="hiddenSalary" id="hiddenSalary" default="${EmpDet.salary}" />
	<hdiits:hidden name="hiddenUserID" id="hiddenUserID" default="${allDtlMap.userID}" />
	<hdiits:hidden name="comboBoxSel" caption="comboBoxSel" />

	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><fmt:message key="GPF" /></a></li>
	</ul>
	</div>


	<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">


	<table width="100%" style="display:none">
		<tr>
			<td width="25%" align="left">
			<hdiits:caption captionid="gpf.GPFfunction" bundle="${gpfLables}"/>
			</td>

			<td>
			<hdiits:select name="gpfFunction" caption="gpfCat" onchange="callOnchnge();"	validation="txt.isrequired" id="gpfFunction" sort="false">
				<hdiits:option value="-1">
					<fmt:message key="COMMON.DROPDOWN.SELECT" bundle="${gpfLables}"/>
				</hdiits:option>

				<c:forEach var="name1" items="${nomineeDetailsCombo}">
					<option value="${name1.lookupId}">${name1.lookupDesc}</option>
				</c:forEach>

			</hdiits:select></td>

			<td width="25%"></td>

			<td width="25%"></td>

			<td width="25%"></td>
		</tr>
	</table>

	<hdiits:fieldGroup titleCaptionId="GPF.empDet" bundle="${gpfLables}"  expandable="true" collapseOnLoad="true">
	
			<table border="0" width="100%">
				<tr>
					<td width="25%">
					<hdiits:caption captionid="HRMS.EmpName" bundle="${gpfLables}"/>
					</td>

					<td width="25%"><c:out value="${allDtlMap.empName}"></c:out>
					</td>

					<td width=25%>
					<hdiits:caption captionid="HRMS.Designation" bundle="${gpfLables}"/>
					</td>

					<td width="25%"><c:out value="${allDtlMap.dsgnName}"></c:out>
					</td>

				</tr>

				<tr>
					<td width="25%">
					<hdiits:caption captionid="HRMS.Address" bundle="${gpfLables}"/>
					</td>

					<td width="25%"><c:if test="${Address!=''}">
						<c:out value="${Address}">
						</c:out>
					</c:if> <c:if test="${Address==''}">
						<hdiits:caption captionid="GPF.NA" bundle="${gpfLables}"/>
					</c:if></td>

					<td width=25%>
					<hdiits:caption captionid="HRMS.OfficeAttached" bundle="${gpfLables}"/>
					</td>

					<td width="25%"><c:out value="${allDtlMap.office}"></c:out></td>
				</tr>

				<tr>
					<td width=25%> 
					<hdiits:caption captionid="HRMS.BasicSalary" bundle="${gpfLables}"/>(INR)
					</td>

					<td width="25%"><c:out value="${allDtlMap.basicSalary}"></c:out>
					
					</td>

				</tr>
				</table>
			</hdiits:fieldGroup>
			<hdiits:fieldGroup titleCaptionId="HRMS.EmpOtherDetails" bundle="${gpfLables}"  expandable="true" collapseOnLoad="true">	
			<table>
				<tr>
					<td width="25%">
					<hdiits:caption captionid="HRMS.service" bundle="${gpfLables}"/>
					</td>

					<td width="25%">
					<hdiits:caption captionid="GPF.GPS" bundle="${gpfLables}"/>
					</td>

					<td width="25%">
					<hdiits:caption captionid="HRMS.Family" bundle="${gpfLables}"/>
					</td>
					

					<td width="25%">
						<c:if test="${allDtlMap.isFam=='Yes' }">
							<hdiits:caption captionid="GPF.yes" bundle="${gpfLables}"/>
						</c:if>	
						
						<c:if test="${allDtlMap.isFam=='No' }">
							<hdiits:caption captionid="GPF.no" bundle="${gpfLables}"/>
						</c:if>	
					</td>

				</tr>

				<tr>
					<td width=25%>
					<hdiits:caption captionid="HRMS.tempOrPermanent" bundle="${gpfLables}"/>
					</td>

					<td width=25%><c:out value="${allDtlMap.empTypeName}"></c:out>
					</td>

					<td width=25%>
					<hdiits:caption captionid="HRMS.DateofJoining" bundle="${gpfLables}"/>
					</td>

					<td width=25%>
					<fmt:formatDate value="${allDtlMap.empDoj}" pattern="dd/MM/yyyy" var="empDoj" /> 
					<c:if test="${allDtlMap.empDoj!=''}">
						<c:out value="${empDoj}"></c:out>
					</c:if>
					 <c:if test="${allDtlMap.empDoj==''}">
						<hdiits:caption captionid="GPF.NA" bundle="${gpfLables}"/>
					</c:if>
					</td>


				</tr>
				

				<tr>
					<td width=25%>
					<hdiits:caption captionid="HRMS.Photo" bundle="${gpfLables}"/>
					</td>

					<td><img width="100px" id="myImage1" name="myImage1"
						onmouseover="showtrail('myImage_div','',200,150,'${empPhtoId}','${mpgSrNo}');"
						onmouseout="hidetrail();"> <c:if
						test="${mpgSrNo==0}">
						<hdiits:caption captionid="GPF.NA" bundle="${gpfLables}"/>
					</c:if></td>

					<td width=25%>
					<hdiits:caption captionid="HRMS.FingerPrints" bundle="${gpfLables}"/>
					</td>

					<td><img width="100px" id="myImage2" name="myImage2"
						onmouseover="showtrail('myImage_div','',200,150,'${empThumpId}','${mpgSrNoThumb}');"
						onmouseout="hidetrail();"> <c:if
						test="${mpgSrNoThumb==0}">
						<hdiits:caption captionid="GPF.NA" bundle="${gpfLables}"/>
					</c:if></td>

					<td>
					<div style="display: none; position: absolute; z-index:110; "
						id="preview_div"></div>
					</td>
				</tr>

			</table>
</hdiits:fieldGroup>

<hdiits:fieldGroup titleCaptionId="HRMS.NomineeDtls" bundle="${gpfLables}"  expandable="true" collapseOnLoad="true">
				
			<c:if test="${finalList ne null and finalListStr ne 'Y'}">
			

				<table border=1 bordercolor="black" bgcolor="#C6DEFF" align="center"
					width="75%" cellpadding="1" cellspacing="1"
					style="border-collapse: collapse">
					<tr>
						<td width=10%><b>
						<hdiits:caption captionid="GPF.SrNo" bundle="${gpfLables}"/>
						</b></td>
						<td width="25%"><b>
						<hdiits:caption captionid="GPF.name" bundle="${gpfLables}"/>
						</b></td>
						<td width="25%"><b>
						<hdiits:caption captionid="GPF.rel" bundle="${gpfLables}"/>
						</b></td>
						<td align="right" width=25%><b>
						 <hdiits:caption captionid="HRMS.Share" bundle="${gpfLables}"/>
						 </b></td>

					</tr>
				</table>
			</c:if> <c:if test="${finalList ne null}">
				<c:forEach var="obj" items="${finalList}">
					<c:set value="1" var="i"></c:set>
					<table border=1 align="center" width="75%" cellpadding="1"
						cellspacing="1" style="border-collapse: collapse">
						<tr>
							<td width=10%><c:out value="${obj.counter}" /></td>


							<td width=25%><c:if test="${obj.nomineeName!=''}">
		${obj.nomineeName}
		</c:if> <c:if test="${obj.nomineeName==''}">
								<hdiits:caption captionid="GPF.NA" bundle="${gpfLables}"/>
							</c:if></td>


							<td width=25%><c:if test="${obj.relationNominee!=''}">
		${obj.relationNominee}
		</c:if> <c:if test="${obj.relationNominee==''}">
								<hdiits:caption captionid="GPF.NA" bundle="${gpfLables}"/>
							</c:if></td>

							<td width=25% align="right"><script>
		document.write(decimalPoint(${obj.share}))
		var share="${obj.share}"*1;
		totShare=totShare+share;
		</script></td>
						</tr>
					</table>


					<c:set value="${i+1}" var="i"></c:set>
				</c:forEach>
			</c:if> <c:if test="${finalListStr eq 'Y'}">



				<br> 
				<centr><b> 
				<hdiits:caption captionid="GPF.noRecord" bundle="${gpfLables}"/>
				</b></center>
				<br>
				
			</c:if> </hdiits:fieldGroup><br>

	<hdiits:fieldGroup titleCaptionId="HRMS.RequestDetails" bundle="${gpfLables}"  expandable="true" collapseOnLoad="true">
	<table>
				<tr>
					<td>
					<p id="commonNote" style="display:none">
					<font color="RED">
					<hdiits:caption captionid="GPF.commonNote" bundle="${gpfLables}"/>
					</font>
					</p>
					<p id="lessThanTenPercent" style="display:none">
					<font color="RED">
					<hdiits:caption captionid="GPF.lessThanTenPercent" bundle="${gpfLables}"/>
					</font>
					</p>
					<p id="newAmtgrossPay" style="display:none">
					<font color="RED">
					<hdiits:caption captionid="GPF.subsAmtGrossSal" bundle="${gpfLables}"/>
					</font>
					</p>		
		
					<p id="case0" style="display:none"><font color="RED">
					<hdiits:caption captionid="emoulmentRequired" bundle="${gpfLables}"/>
					</font></p>
					<p id="case1" style="display:none"><font color="RED">
					<hdiits:caption captionid="emoulmentNotValid" bundle="${gpfLables}"/>
					</font></p>
					<p id="case2" style="display:none"><font color="RED">
					<hdiits:caption captionid="subsAmtNotVald" bundle="${gpfLables}"/>
					</font></p>
					<p id="case3" style="display:none"><font color="RED">
					<hdiits:caption captionid="GPF.subsAmtGrossSal" bundle="${gpfLables}"/>
					</font></p>
					<p id="case4" style="display:none"><font color="RED">
					<hdiits:caption captionid="GPF.noNominee" bundle="${gpfLables}"/>
					</font></p>
					<p id="case5" style="display:none"><font color="RED">
					<hdiits:caption captionid="GPF.shareNot100" bundle="${gpfLables}"/>
					</font>
					<p id="newAccReqRaised" style="display:none"><font color="RED">
					<hdiits:caption captionid="GPF.newAccReqRaised" bundle="${gpfLables}"/>
					</font></p>

					</td>
				</tr>
			</table>

			<table width="100%">

				<tr style="display: none">
					<td width="25%">
					<hdiits:caption captionid="GPF.subRateAmt" bundle="${gpfLables}"/>
					</td>

					<td width="25%">
					<hdiits:radio name="radSubRateAmt" value="Amt" id="radSubRateAmt" onclick="showSub();" />
						<hdiits:caption captionid="GPF.subAmt" bundle="${gpfLables}"/>
					<hdiits:radio name="radSubRateAmt" value="Rate" id="radSubRateAmt" onclick="showSub();" />
						<hdiits:caption captionid="GPF.subRt" bundle="${gpfLables}"/>
					</td>


				</tr>

				<tr>
					<td width=25%>
					<hdiits:caption captionid="rate_emolument" bundle="${gpfLables}"/>
					</td>

					<td width=25%>
					<hdiits:number name="rate_emolument" id="rate_emolument" mandatory="true" size="15" maxlength="10" />
					</td>

					<td width=25%>
					<hdiits:caption captionid="remarks" bundle="${gpfLables}"/>
					</td>

					<td width=25%>
					<hdiits:textarea name="remarks" default="${remk}" id="remarks" rows="3" cols="30" maxlength="99"	onclick="dodacheck(this)" onblur="dodacheck(this)"	onkeypress="dodacheck(this)">
					</hdiits:textarea>
					</td>


					<td width="25%" style="display: none">
					<hdiits:caption captionid="GPF.newSubRt" bundle="${gpfLables}"/>
					</td>

					<td width="25%" style="display: none">
					<hdiits:number	name="newSubsRt" id="newSubsRt" maxlength="12" mandatory="true" onblur="calcAmt(this);roundRate();" /></td>

				</tr>
				<tr>

					<td width="25%"></td>
					<td width="25%"></td>

				</tr>
			</table>


			</hdiits:fieldGroup>





			<table width=100% style="display:none">

				<br>
				<br>
				<tr align="center">
					<td><hdiits:button name="submitOne" type="button" id="submitOne" caption="Submit" onclick="callOnSubmit()" /> 
					<hdiits:button	value="Close" name="Close" type="button" onclick="closeWindow()" />
					</td>
				</tr>
			</table>
			<hdiits:jsField jsFunction="callOnSubmit()" name="callOnSubmit" /> 
			<jsp:include page="/WEB-INF/jsp/core/tabnavigation.jsp" />



			<table width="100%">
				<tr align="right">
					<td><font size="1"> 
					<hdiits:caption captionid="GPF.commonNote" bundle="${gpfLables}"/>
					<br>
					<hdiits:caption captionid="GPF.amtFormat" bundle="${gpfLables}"/>
					<br>
					<hdiits:caption captionid="GPF.dateFormat" bundle="${gpfLables}"/>
					 </font></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	</div>


	</div>



	<script>
		initializetabcontent("maintab")
		defaultRadio();
		
		if('${empPhtoId}'!='')
		{
			document.getElementById('myImage1').src="hdiits.htm?actionFlag=viewAttachment&attachmentId="+'${empPhtoId}'+"&attachmentSerialNumber="+'${mpgSrNo}'; 
		}
		else
		{
			document.getElementById('myImage1').style.display='none';
		}
		if('${empThumpId}'!='')
		{
			document.getElementById('myImage2').src="hdiits.htm?actionFlag=viewAttachment&attachmentId="+'${empThumpId}'+"&attachmentSerialNumber="+'${mpgSrNoThumb}'; 
		}
		else
		{
			document.getElementById('myImage2').style.display='none';
		}
	
		</script>

	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />

</hdiits:form>
<c:if test="${cmnLookupMst2 ne null}">
	<script>
			
		var gpfNomineeFunc=document.getElementById("gpfFunction");
		var idOfFunc=document.getElementById("gpfFunction").value;
		document.getElementById("gpfFunction").value="${cmnLookupMst2.lookupId}";
		gpfNomineeFunc.selected="true";
		
		
		
		</script>
		
		
</c:if>
<c:if test="${emoulmentAmt ne null }">
	<script>
document.getElementById("rate_emolument").value="${emoulmentAmt}";
</script>
</c:if>
<c:if test="${emoulmentAmt==0}">
	<script>
document.getElementById("rate_emolument").value="";
</script>
</c:if>


<script>
if("${validateRuleEngine}"==1)
	{
		document.getElementById("commonNote").style.display='';
		document.getElementById("lessThanTenPercent").style.display='';
		document.getElementById("newAmtgrossPay").style.display='';
	}
</script>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

