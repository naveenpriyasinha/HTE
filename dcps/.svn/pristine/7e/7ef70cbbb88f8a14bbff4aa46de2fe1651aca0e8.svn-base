
<%
try {
%>
<%@ include file="/WEB-INF/jsp/core/include.jsp"%>


<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript" src="common/script/tagLibValidation.js">
</script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<script type="text/javascript" src="script/common/commonfunctions.js">
</script>
<script type="text/javascript"	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript"	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"	src="<c:url value="/script/common/attachment.js"/>"></script>

<fmt:setBundle basename="resources.ess.gpf.gpfLables" var="gpfLables"
	scope="request" />




<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="newAcc" value="${resValue.newAcc}"></c:set>
<c:set var="srvDate" value="${resValue.srvDate}"></c:set>
<c:set var="userId" value="${resValue.userId}"></c:set>
<c:set var="empDtl" value="${resValue.empDtl}" />
<c:set var="empDetails" value="${resValue.empDetails}" />
<c:set var="sprFlag" value="${resValue.sprFlag}" />
<c:set var="subsAmt" value="${resValue.subsAmt}" />
<c:set var="allDtlMap" value="${resValue.allDtlMap}" />
<c:set var="Address" value="${resValue.Address}" />
<c:set var="currDate" value="${resValue.currDate}" />
<c:set var="loggedInUser" value="${resValue.loggedInUser}" />
<c:set var="newAcc" value="${resValue.newAcc}" />
<c:set var="agResponse" value="${resValue.agResponse}"></c:set>
<c:set var="accNo" value="${resValue.accNo}"></c:set>
<c:set var="cancelType" value="${resValue.cancelType}"></c:set>

<c:set var="empPhtoId" value="${resValue.empPhtoId}"></c:set>
<c:set var="mpgSrNo" value="${resValue.mpgSrNo}"></c:set>
<c:set var="empThumpId" value="${resValue.empThumpId}"></c:set>
<c:set var="mpgSrNoThumb" value="${resValue.mpgSrNoThumb}"></c:set>

<script type="text/javascript">

function chkAccountNo()
{ 
  if(document.frmBF1.accNo.value !="")
  {
  var name =document.frmBF1.accNo.value;
  
  
  		xmlHttp=GetXmlHttpObject();
	    if (xmlHttp==null)
		 {
			  alert ("Your browser does not support AJAX!");
			  return;
		 } 
		  
		 var url; 
		 var uri='';
		 url= uri+'&accNo='+ document.frmBF1.accNo.value;
		 var actionf="chkAccountNo";
		 uri='./hrms.htm?actionFlag='+actionf;
		 url=uri+url; 
      	  		  
		xmlHttp.onreadystatechange=chk_AccNo;
		xmlHttp.open("POST",encodeURI(url),true);
		xmlHttp.send(null);	
	}
}

function chk_AccNo()
{

	if (xmlHttp.readyState==complete_state)
 	{ 						
 			var XMLDoc=xmlHttp.responseXML.documentElement;		
				 
            var namesEntries = XMLDoc.getElementsByTagName('order-no');
            
          if(namesEntries.length != 0 && namesEntries[0].childNodes[0].text!='0')
                {                    
                document.getElementById("ifSamAaccNo").style.display='block'; 
                document.getElementById("case1").style.display='none'; 
                 document.frmBF1.accNo.value = '';
                 document.frmBF1.accNo.focus();
                }
                else
                {
                 document.getElementById("ifSamAaccNo").style.display='none'; 
                }
                  
  }
}
	  
</script>

<div id="tabmenu">
<ul id="maintab" class="shadetabs">
	<li class="selected"><a href="#" rel="tcontent1"><b>
		<fmt:message key="GPF" />
		</b></a></li>
</ul>
</div>
<div class="halftabcontentstyle">
<div id="tcontent1" class="tabcontent" tabno="0">
<table class="tabhdiits:table" bordercolor="#386CB7" width="100%">
	<tr>
		<td><hdiits:form name="frmBF1" validate="true" method="POST" encType="multipart/form-data">

			<hdiits:hidden name="hiddenReqId" id="reqId"default="${newAcc.requestId}" />
			<hdiits:hidden name="fileId" default="${newAcc.requestId}" />
			<hdiits:hidden name="wffileId_hidden" default="${newAcc.requestId}" />

			<div id="leave" name="leave">
			
			<hdiits:fieldGroup titleCaptionId="GPF.empDet" bundle="${gpfLables}"  expandable="true" collapseOnLoad="true">
			
					<table border="0" width="100%">
						<tr>
							<td align="left" width="25%">
							<hdiits:caption captionid="HRMS.EmpName" bundle="${gpfLables}"/>
							</td>

							<td width="25%"><c:out value="${allDtlMap.empName}"></c:out></td>

							<td align="left" width="25%">
							<hdiits:caption captionid="HRMS.Designation" bundle="${gpfLables}"/>
							</td>
							

							<td width="25%"><c:out value="${allDtlMap.dsgnName}"></c:out></td>

						</tr>
						<tr>
							<td align="left" width="25%">
							<hdiits:caption captionid="HRMS.Address" bundle="${gpfLables}"/>
							</td>
							<td width="25%"><c:out value="${Address}"></c:out></td>

							<td align="left" width="25%">
							<hdiits:caption captionid="HRMS.OfficeAttached" bundle="${gpfLables}"/>
							</td>
							<td width="25%"><c:out value="${allDtlMap.office}"></c:out></td>
						</tr>
						<tr>
							<td width=25%>
							<hdiits:caption captionid="HRMS.BasicSalary" bundle="${gpfLables}"/>
							(INR)</td>
							<td width="25%"><c:out value="${allDtlMap.basicSalary}"></c:out>
							
							</td>


						</tr>
						
				</table>
				 </hdiits:fieldGroup>
		<hdiits:fieldGroup titleCaptionId="HRMS.EmpOtherDetails" bundle="${gpfLables}"  expandable="true" collapseOnLoad="true">
						<table width=100%>
						<tr>
							<td align="left" width="25%">
							<hdiits:caption captionid="HRMS.service" bundle="${gpfLables}"/>
							</td>
							<td width="25%">
							<hdiits:caption captionid="GPF.GPS" bundle="${gpfLables}"/>
							</td>

							<td align="left" width="25%">
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
							<td align="left" width="25%">
							<hdiits:caption captionid="HRMS.tempOrPermanent" bundle="${gpfLables}"/>
							</td>
							<td width="25%"><c:out value="${allDtlMap.empTypeName}"></c:out></td>
							<c:if test="${allDtlMap.empDoj ne null }">
								<td align="left" width="25%">
								<hdiits:caption captionid="HRMS.DateofJoining" bundle="${gpfLables}"/>
								</td>
								<td align="left" width="25%"><fmt:formatDate
									value="${allDtlMap.empDoj}" pattern="dd/MM/yyyy" /></td>
							</c:if>
							<td id="srvdate" style="display:none"><fmt:formatDate
								value="${currDate}" pattern="dd/MM/yyyy" /></td>
						</tr>
						<tr>
							<td align="left" width="25%">
							<hdiits:caption captionid="HRMS.Photo" bundle="${gpfLables}"/>
							</td>
							<td id=""><img id="myImage1" name="myImage1" src=""
								width="50px">
							<c:if test="${mpgSrNo==0}">
								<hdiits:caption captionid="GPF.NA" bundle="${gpfLables}"/>
							</c:if>	
							</td>

							<td align="left" width="25%">
							<hdiits:caption captionid="HRMS.FingerPrints" bundle="${gpfLables}"/>
							</td>
							<td id=""><img id="myImage2" name="myImage2" src=""
								width="50px">
								<c:if
						test="${mpgSrNoThumb==0}">
						<hdiits:caption captionid="GPF.NA" bundle="${gpfLables}"/>
					</c:if>
								</td>
						</tr>
						
						</table>
						</hdiits:fieldGroup>
						
					<hdiits:fieldGroup titleCaptionId="HRMS.RequestDetails" bundle="${gpfLables}"  expandable="true" collapseOnLoad="true">
					
					<table width=100%>
						<tr>
							<td width="25%">
							<hdiits:caption captionid="rate_emolument" bundle="${gpfLables}"/>
							</td>

							<c:if test="${newAcc.subsAmt ne null}">
								<td width="25%"><c:out value="${newAcc.subsAmt}"></c:out></td>
							</c:if>
							<td width="25%">
							<hdiits:caption captionid="remarks" bundle="${gpfLables}"/>
							</td>
							<c:if test="${newAcc.remarks ne null}">
								<td width="25%"><hdiits:textarea name="rem"
									default="${newAcc.remarks}" readonly="true" rows="20" cols="40" /></td>
							</c:if>

						</tr>

					</table>
					
					</hdiits:fieldGroup>

				<%@ include file="../gpf/masterScreenForNewAcc.jsp"%>


				
					<table>
					<tr><td>
							<p id="requestCancel" style="display:none"> 
											<font color="RED">
											CANCELLED REQUEST
											</font>
										</p>	
					</td></tr>
						<tr>
							<td>
							<p id="case0" style="display:none"><font color="RED">
							<hdiits:caption captionid="GPF.enterAppAG" bundle="${gpfLables}"/>
							</font></p>
							<p id="case1" style="display:none"><font color="RED">
							<hdiits:caption captionid="GPF.accNoIsReq" bundle="${gpfLables}"/>
							</font></p>
							<p id="case2" style="display:none"><font color="RED">
							<hdiits:caption captionid="GPF.OrderNo" bundle="${gpfLables}"/>
							</font></p>
							<p id="case3" style="display:none"><font color="RED">
							<hdiits:caption captionid="GPF.OrderDate" bundle="${gpfLables}"/>
							</font></p>
							<p id="case4" style="display:none"><font color="RED">
							<hdiits:caption captionid="GPF.OrderDateNotValid" bundle="${gpfLables}"/>
							</font></p>
							<p id="case5" style="display:none"><font color="RED">
							<hdiits:caption captionid="GPF.accNoNotValid" bundle="${gpfLables}"/>
							</font></p>
							<p id="case6" style="display:none"><font color="RED">
							<hdiits:caption captionid="GPF.orderNoNotValid" bundle="${gpfLables}"/>
							</font></p>
							<p id="ifSamAaccNo" style="display:none"><font color="RED">
							<hdiits:caption captionid="GPF.accExists" bundle="${gpfLables}"/>
							</font></p>
							</td>
						</tr>
					</table>
					
			</table>

			</div>



			<script type="text/javascript">
		if('${mpgSrNo}'!=0)
		{
		document.getElementById('myImage1').src="hdiits.htm?actionFlag=viewAttachment&attachmentId="+'${empPhtoId}'+"&attachmentSerialNumber="+'${mpgSrNo}'; 
		}
		else
		{
		document.getElementById('myImage1').style.display='none';
		}
		if('${mpgSrNoThumb}'!=0)
		{
		document.getElementById('myImage2').src="hdiits.htm?actionFlag=viewAttachment&attachmentId="+'${empThumpId}'+"&attachmentSerialNumber="+'${mpgSrNoThumb}'; 
		}
		else
		{
		document.getElementById('myImage2').style.display='none';
		}
		initializetabcontent("maintab")
		</script>


		<hdiits:jsField jsFunction="submitData()" name="submitData" />
		<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />

		</hdiits:form></td>
	</tr>
</table>
</div>
</div>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>


<script>

if("${isAppByRajkot}"=='C')
{
	document.getElementById("requestCancel").style.display='';
}
</script>



