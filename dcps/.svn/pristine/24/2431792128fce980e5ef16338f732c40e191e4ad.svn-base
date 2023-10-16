<%
try {
	

%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables"	scope="request" />
<script type="text/javascript" src="script/common/base64.js"></script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="frompostlist" value="${resValue.frompostlist}"></c:set>
<c:set var="recdatelist" value="${resValue.recdatelist}"></c:set>
<c:set var="topostlist" value="${resValue.topostlist}"></c:set>
<c:set var="ccpostlist" value="${resValue.ccpostlist}"></c:set>
<c:set var="subjectlist" value="${resValue.subjectlist}"></c:set>
<c:set var="attachmentNameList" value="${resValue.attachmentNameList}"></c:set>
<c:set var="lurlList" value="${resValue.lurlList}"></c:set>
<c:set var="aclElementMstList" value="${resValue.aclElementMstList}" scope="session"></c:set>
<c:set var="hideMenuLookupID" value="${resValue.hideMenuLookupID}"></c:set>




<script>
var comm_dtl='${resValue.commdtl}';
var decoded_text=decodeBase64(comm_dtl);
var intiatorcommno='${resValue.intiatorcommno}'	
var commnumber='${resValue.commno}';		
var category='${resValue.category}';
var loginPost='${resValue.loginPost}';
var crt_post='${resValue.crt_post}';		
			
	

function CommuniqueAction(action)
{

		var commact=action;
		var frompost='${resValue.frompost}'
		var urlStyle ='toolbar=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0'; 
		var action_url
		
		if(commact=='replyattach')
		{
			
			action_url='hdiits.htm?actionFlag=FMS_viewCommuniqueDetail&attchment=yes&replyflag=yes&commno='+commnumber+'&fromPost='+frompost+'&intiatorcommno='+intiatorcommno
		}
		else if(commact=='reply')
		{
			//window.open('hdiits.htm?actionFlag=FMS_viewCommuniqueDetail&attchment=no&replyflag=yes&commno='+commnumber+'&fromPost='+frompost+'&intiatorcommno='+intiatorcommno,'',urlStyle);
			action_url='hdiits.htm?actionFlag=FMS_viewCommuniqueDetail&attchment=no&replyflag=yes&commno='+commnumber+'&fromPost='+frompost+'&intiatorcommno='+intiatorcommno
		}
		else if(commact=='fwdattach')
		{
			action_url='hdiits.htm?actionFlag=FMS_viewCommuniqueDetail&attchment=yes&forward=yes&replyflag=yes&commno='+commnumber+'&fromPost='+frompost+'&intiatorcommno='+intiatorcommno
		}
		else if(commact=='fwd')
		{
			action_url='hdiits.htm?actionFlag=FMS_viewCommuniqueDetail&attchment=no&forward=yes&replyflag=yes&commno='+commnumber+'&fromPost='+frompost+'&intiatorcommno='+intiatorcommno
		}
		else if(commact=='fwdasnew')
		{
			action_url='hdiits.htm?actionFlag=FMS_viewCommuniqueDetail&forwardasnew=yes&attchment=yes&forward=yes&replyflag=yes&commno='+commnumber+'&fromPost='+frompost
		}
		
		document.getElementById("viewcommdtlfrm").method="post";
		document.getElementById("viewcommdtlfrm").action=action_url;
		document.getElementById("viewcommdtlfrm").submit();
		
		
}
function showHistDetail()
{

	var urlStyle ='toolbar=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0,type=fullWindow,fullscreen'; 
	window.open('hdiits.htm?actionFlag=FMS_showCommuniqueHistoryDetail&commno='+commnumber,'',urlStyle);
}
function closecommunique()
{
	var to_post_hdn=window.opener.document.getElementById('toPosthdn').value
	var from_post_hdn=window.opener.document.getElementById('fromPosthdn').value
	
	var to_post;
	var url;
	
	if(from_post_hdn=='')
	{
		to_post=to_post_hdn
		url='hdiits.htm?actionFlag=FMS_Close_Communique&action=closecomm&callfrom=sentitem&commno='+commnumber+'&to_post='+to_post
	}
	else
	{
		
		to_post=from_post_hdn;
		url='hdiits.htm?actionFlag=FMS_Close_Communique&action=closecomm&callfrom=recieveditem&commno='+commnumber+'&to_post='+to_post;
		
	}
	var status='${resValue.active_flag}';
	if(status!='Close')
	{
			if(category=='Generic')
			{
				
				//	window.document.forms[0].action ="hdiits.htm?actionFlag=FMS_Close_Communique&commno="+commnumber;
				//	window.document.forms[0].submit();
				alert('<fmt:message key="WF.GenCommCloseMsg" bundle="${fmsLables}"></fmt:message>')			
			}
			else
			{
				if(loginPost==crt_post)
				{
			     	if(confirm('<fmt:message key="WF.CommCloseConfirmMsg" bundle="${fmsLables}"></fmt:message>'))
					{
						window.document.forms[0].action =url;
						window.document.forms[0].submit();
					}
				}
				else
				{
					alert('<fmt:message key="WF.CommCloseMsg" bundle="${fmsLables}"></fmt:message>');
				}	
			}
	}
	else
	{
			alert('<fmt:message key="WF.SelCommCloseMsg" bundle="${fmsLables}"></fmt:message>');
	}			
	
}
function delfromlist()
{

	var to_post_hdn=window.opener.document.getElementById('toPosthdn').value
	var from_post_hdn=window.opener.document.getElementById('fromPosthdn').value
	
	var to_post;
	var url;
	if(from_post_hdn=='')
	{
		to_post=to_post_hdn
		url='hdiits.htm?actionFlag=FMS_Close_Communique&action=deletecomm&callfrom=sentitem&commno='+commnumber+'&to_post='+to_post
	}
	else
	{
		
		to_post=from_post_hdn;
		url='hdiits.htm?actionFlag=FMS_Close_Communique&action=deletecomm&callfrom=recieveditem&commno='+commnumber+'&to_post='+to_post
	}
		var status='${resValue.active_flag}';
			if(category=='Generic')
			{
					if(confirm('<fmt:message key="WF.CommDelConfirmMsg" bundle="${fmsLables}"></fmt:message>'))
					{
				
						window.document.forms[0].action =url
						window.document.forms[0].submit();
					 
					}
				
			}
			else
			{	
				
				if(status=='Close')
				{
					if(confirm('<fmt:message key="WF.CommDelConfirmMsg" bundle="${fmsLables}"></fmt:message>'))
					{
				
						window.document.forms[0].action =url
						window.document.forms[0].submit();
					 
					}
				}
				else
				{
					alert('<fmt:message key="WF.SelCommClosedMsg" bundle="${fmsLables}"></fmt:message>');
				}	
			}
		
	
			

}
</script>
<hdiits:form name="viewcommdtlfrm" method="post" validate="true" encType="multipart/form-data">
<table width="100%" border="1" bordercolor="black" >
<tr>
<td style="border: none">
		<jsp:include flush="true" page="../workflow/LinkSpecificMenu.jsp">
		<jsp:param value="${hideMenuLookupID}" name="hideMenuLookupID"/>
</jsp:include>
</td>
</tr>
<tr>
<td style="border: none">
<center><h5><fmt:message key="WF.CommDtl" bundle="${fmsLables}"></fmt:message></h5></center>
</td>
</tr>
<tr>
<td style="border: none">
	<table width="100%"  >
		<tr>
			<td style="border: none">
				<table  width="70%">
					<tr>
						<td align="right" width="16%">
							<table width="100%">
								<tr>
									<td align="left">
										<hdiits:caption captionid="WF.CommNo"  bundle="${fmsLables}" />
									</td>
									<td align="right">
										<hdiits:caption captionid="" caption=":"  />
									</td>
								</tr>
							</table>
						</td>
						<td width="19%"><b><c:out value="${resValue.commno}" ></c:out></b>
						<td align="right" width="16%">
							<table>
								<tr>
									<td align="left">
										<hdiits:caption captionid="WF.CommRecDate"  bundle="${fmsLables}"  />
									</td>
									<td align="right">
										<hdiits:caption captionid="" caption=":"  />
									</td>
								</tr>
							</table>
						</td>
						<td width="19%"><c:out value="${resValue.recdate}" ></c:out></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td style="border: none">
				<table  width="70%">
					<tr>
						<td align="right" width="16%">
							<table width="100%">
								<tr>
									<td align="left">
										<hdiits:caption captionid="WF.CommFrom"  bundle="${fmsLables}" />
									</td>
									<td align="right">
										<hdiits:caption captionid="" caption=":"  />
									</td>
								</tr>
							</table>
						</td>
						<td width="19%"><c:out value="${resValue.FromEmpName}" ></c:out></td>
						<td align="right" width="16%">
							<table>
								<tr>
									<td align="left">
										<hdiits:caption captionid="WF.CommSubject" bundle="${fmsLables}" />
									</td>
									<td align="right">
										<hdiits:caption captionid="" caption=":"  />
									</td>
								</tr>
							</table>
						</td>
						<td width="19%"><c:out value="${resValue.subject}" ></c:out></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td style="border: none">
				<table  width="70%">
					<tr>
						<td align="right" width="16%">
							<table width="100%">
								<tr>
									<td align="left">
										<hdiits:caption captionid="WF.CommCat" bundle="${fmsLables}" />
									</td>
									<td align="right">
										<hdiits:caption captionid="" caption=":"  />
									</td>
								</tr>
							</table>
						</td>
						<td width="19%"><c:out value="${resValue.category}" ></c:out></td>
						<td align="right" width="16%">
							<table>
								<tr>
									<td align="left">
										<hdiits:caption captionid="WF.CommStatus" bundle="${fmsLables}"  />
									</td>
									<td align="right">
										<hdiits:caption captionid="" caption=":"  />
									</td>
								</tr>
							</table>
						</td>
						<td width="19%"><c:out value="${resValue.active_flag}" ></c:out></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td style="border: none">
				<table  width="70%">
					<tr>
						<td width="23%">
							<table width="100%">
								<tr>
									<td align="left">
										<hdiits:caption  captionid="WF.CommTo" bundle="${fmsLables}"  />
									</td>
									<td align="right">
										<hdiits:caption captionid="" caption=":"  />
									</td>
								</tr>
							</table>
						</td>
						<td width="77%">
						<hdiits:textarea name="ToList" readonly="true"  rows="4" cols="70" default="${resValue.to_post_emp_list}" ></hdiits:textarea>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td style="border: none">
				<table  width="100%">
					<tr>
						<td width="13%">
							<table width="100%">
								<tr>
									<td align="left">
										<hdiits:caption captionid="WF.CommDtl" bundle="${fmsLables}"  />
									</td>
									<td align="right">
										<hdiits:caption captionid="" caption=":"  />
									</td>
								</tr>
							</table>
						</td>
						<td width="68%">
							<script>							
							document.writeln(decoded_text);
							</script>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td style="border: none">
				<table  width="70%">
					<tr>
						<td width="23%">
							<table width="100%">
								<tr>
									<td align="left">
										<hdiits:caption captionid="WF.CC" bundle="${fmsLables}"  />
									</td>
									<td align="right">
										<hdiits:caption captionid="" caption=":"  />
									</td>
								</tr>
							</table>
						</td>
						<td width="77%">
						<hdiits:textarea name="CCList"  readonly="true" rows="4" cols="70" default="${resValue.cc_post_emp_list}" ></hdiits:textarea>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td style="border: none">
				<table  width="70%">
					<tr>
						<td width="23%">
							<table width="100%">
								<tr>
									<td align="left">
										<hdiits:caption captionid="WF.CCRem" bundle="${fmsLables}"  />
									</td>
									<td align="right">
										<hdiits:caption captionid="" caption=":"  />
									</td>
								</tr>
							</table>
						</td>
						<td width="77%">
						<hdiits:textarea name="CCRemarks" readonly="true"  rows="4" cols="70" default="${resValue.cc_remarks}" ></hdiits:textarea>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td style="border: none">
				<table  width="100%">
					<tr>
						<td width="15%">
							<table width="100%">
								<tr>
									<td align="left">
										<hdiits:caption captionid="WF.Attachment" bundle="${fmsLables}"/>
									</td>
									<td align="right">
										<hdiits:caption captionid="" caption=":"/>
									</td>
								</tr>
							</table>
						</td>
						<td width="77%" >
														
							<c:forEach var="attachmentcnt" items="${attachmentNameList}" varStatus="status" >
								<a href="${lurlList[status.index]}"  > <c:out value="${attachmentNameList[status.index]}"> </c:out></a>
								<br>
							</c:forEach>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		
				
</table>
</td>
</tr>
<tr>

<td style="border: none">
<center>
<hdiits:button name="closebtn" captionid="WF.Close" bundle="${fmsLables}"  type="button"  onclick="window.close()"/>
<hdiits:button name="histbtn"  captionid="WF.ShowHistory" bundle="${fmsLables}"  type="button"  onclick="showHistDetail()"/>
</center>
</td>

</tr>
<tr>
</tr>
<tr>
</tr>

</table>
		
</hdiits:form>

<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>