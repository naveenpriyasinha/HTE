
<%
try {
%>

<%@ include file="../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/attachment.js"></script>
<script type="text/javascript" src="script/common/address.js"></script>

<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>
<c:set var="resultObj"	value="${result}"></c:set>
<c:set var="resultMap"	value="${resultObj.resultValue}"></c:set> 
<c:set var="outwardNo" value="${resultMap.outwardNo}"></c:set>
<c:set var="outwardRefNo" value="${resultMap.outwardRefNo}"></c:set>
<c:set var="refFileCorrNo" value="${resultMap.refFileCorrNo}"></c:set>
<c:set var="fileCatgry" value="${resultMap.fileCatgry}"></c:set>
<c:set var="outwardType" value="${resultMap.outwardType}"></c:set>
<c:set var="sentTo" value="${resultMap.sentTo}"></c:set>
<c:set var="ticket" value="${resultMap.ticket}"></c:set>
<c:set var="remarks" value="${resultMap.remarks}"></c:set>
<c:set var="outwardDesc" value="${resultMap.outwardDesc}"></c:set>
<c:set var="enclosure" value="${resultMap.enclosure}"></c:set>
<c:set var="lttrDt" value="${resultMap.lttrDt}"></c:set>
<c:set var="dispatch" value="${resultMap.dispatch}"></c:set>
<c:set var="branchName" value="${resultMap.branchName}"></c:set>
<c:set var="docName" value="${resultMap.docName}"></c:set>
<c:set var="externalloc" value="${resultMap.externalloc}"></c:set>
<c:set var="area" value="${resultMap.area}"></c:set>
<c:set var="cityname" value="${resultMap.cityname}"></c:set>
<c:set var="locname" value="${resultMap.locname}"></c:set>
<c:set var="loccity" value="${resultMap.loccity}"></c:set>
<c:set var="locstate" value="${resultMap.locstate}"></c:set>
<c:set var="locdistrict" value="${resultMap.locdistrict}"></c:set>
<c:set var="locpincode" value="${resultMap.locpincode}"></c:set>
<c:set var="attachmentNameList" value="${resultMap.attachmentNameList}"></c:set>
<c:set var="lurlList" value="${resultMap.lurlList}"></c:set>
<c:set var="attachmentDescList" value="${resultMap.attachmentDescList}"></c:set>
<c:set var="fmsoutwardDraftCustomVoList" value="${resultMap.fmsoutwardDraftCustomVoList}"></c:set>

<c:set var="attachdesc" value="${resultMap.attachdesc}"></c:set>
<c:set var="attachfilename" value="${resultMap.attachfilename}"></c:set>
<c:set var="attachid" value="${resultMap.attachid}"></c:set>

<hdiits:form name="frmviewOutward" id="frmviewOutward" validate="true" encType="multipart/form-data" method="POST" action="./hdiits.htm">

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected">
			<a href="#" rel="tcontent1">
				 <hdiits:caption captionid="FMS.OUTWARD" bundle="${fmsLables}" />
			</a>
		</li>
	</ul>
</div>

<div class="tabcontentstyle"><!-------------------------------------------------------- tb1 --------------------------------------------------->
	<div id="tcontent1" class="tabcontent">
	<br><br>
	
	
	<table width="100%">
		<tr>
			<td class="datatableheader">
				<hdiits:caption	captionid="FMS.OUTWARD" bundle="${fmsLables}" />
			</td>
		</tr>
	</table>
	<table id="OutwardTable1" class="tabtable" width="30%"> 		
		<tr>
			<td  class="fieldLabel" style="border:none">
				<hdiits:caption	captionid="FMS.OUTWARDNO" bundle="${fmsLables}" />
			</td>
			<td class="fieldLabel">
			   <hdiits:text name="txtoutno" id="txtoutnoid" captionid="FMS.OUTWARDNO" bundle="${fmsLables}"  validation="txt.isrequired"/>
			</td>		
			<td  class="fieldLabel" style="border:none">
				<hdiits:caption	captionid="FMS.REFNO" bundle="${fmsLables}" />
			</td>
			<td class="fieldLabel">
				  <hdiits:text name="txtoutrefno" id="txtoutrefnoid" captionid="FMS.REFNO" bundle="${fmsLables}"/>
			</td>	
		</tr>
		<tr>			
			<td  class="fieldLabel" style="border:none">
				<hdiits:caption	captionid="FMS.FILECATEGORY" bundle="${fmsLables}" />
			</td>
			<td class="fieldLabel">		
				<hdiits:text name="txtfileCategory" id="txtfileCategoryid" captionid="FMS.FILECATEGORY" bundle="${fmsLables}"/>
			</td>
			<td  class="fieldLabel" style="border:none">
				<hdiits:caption	captionid="FMS.LETTERDT" bundle="${fmsLables}" />
		 	</td>
			<td class="fieldLabel">				
				  <hdiits:dateTime name="txtletterdt" captionid="FMS.LETTERDT" bundle="${fmsLables}"/>	
			</td>	 
			</tr>
			<tr>
				<td  class="fieldLabel" style="border:none">
					<hdiits:caption	captionid="FMS.OUTWARDTYPE" bundle="${fmsLables}" />
				</td>
				<td class="fieldLabel">		
					<hdiits:text name="txtoutwardtype" id="txtoutwardtypeid" captionid="FMS.OUTWARDTYPE" bundle="${fmsLables}"/>
				</td>
								
				<td  class="fieldLabel" style="border:none">
				<hdiits:caption	captionid="FMS.REFNO" bundle="${fmsLables}" />
				</td>
				<td>
					 <hdiits:radio name="fileCorrRadio" id="FileRadio" value="File" mandatory="true" captionid="FMS.FILE" bundle="${fmsLables}"/>
					 <hdiits:radio name="fileCorrRadio" id="CorrRadio" value="Correspondence" mandatory="true" captionid="FMS.CORR" bundle="${fmsLables}"/>		   
				</td>		
				<td class="fieldLabel">
					 <hdiits:text id="txtFileCorrRefNumid" name="txtFileCorrRefNum"  captionid="FMS.REFNO" bundle="${fmsLables}" mandatory="true"/>
				</td>								
			</tr>		
			<tr>								
				<td  class="fieldLabel" style="border:none">
					<hdiits:caption	captionid="FMS.ENCLOSURE" bundle="${fmsLables}" />
				</td>
				<td class="fieldLabel">
					<hdiits:checkbox name="chkEnclosure" value="enclosure" />
				</td>
			</tr>	
			<tr>
				<td  class="fieldLabel" style="border:none">
					<hdiits:caption	 captionid="FMS.SENTTO" bundle="${fmsLables}"/>
				</td>
				<td colspan="3" width="100%">
					<table id="viewTable" border="2" width="50%" style="display:none" align="left">
							<tr>
								<th class="datatableheader">SrNo</th>
								<th class="datatableheader">Name</th>								
							</tr>
					</table>
				</td>				
			</tr>
			<tr>
				<c:if test="${resultMap.area ne '' }">							
					<td  class="fieldLabel" style="border:none">
						<hdiits:caption captionid="FMS.AREANAME" bundle="${fmsLables}" />
					</td>
					<td class="fieldLabel">
							<hdiits:text  name="txtarea" id="areaid" captionid="FMS.AREANAME" bundle="${fmsLables}"/>
					</td>				
					<td  class="fieldLabel" style="border:none">
						<hdiits:caption captionid="FMS.CITY" bundle="${fmsLables}" />
					</td>
					<td class="fieldLabel">
							<hdiits:text  name="txtcity" id="cityid" captionid="FMS.CITY" bundle="${fmsLables}"/>
					</td>
					<td  class="fieldLabel" style="border:none">
						<hdiits:caption captionid="FMS.EXTLOC" bundle="${fmsLables}" />
					</td>
					<td class="fieldLabel">
							<hdiits:text  name="txtextloc" id="extlocid" captionid="FMS.EXTLOC" bundle="${fmsLables}"/>
					</td>
				</c:if>
			</tr>
			<c:if test="${resultMap.locname ne '' }">	
			   <tr>					
					<td  class="fieldLabel" style="border:none">
						<hdiits:caption captionid="FMS.LOCNAME" bundle="${fmsLables}" />
					</td>
					<td class="fieldLabel">
							<hdiits:text  name="txtlocname" id="locnameid" captionid="FMS.LOCNAME" bundle="${fmsLables}"/>
					</td>
					
					<td  class="fieldLabel" style="border:none">
						<hdiits:caption captionid="FMS.LOCCITY" bundle="${fmsLables}" />
					</td>
					<td class="fieldLabel">
						<hdiits:text  name="txtloccity" id="txtloccityid" captionid="FMS.LOCCITY" bundle="${fmsLables}"/>
					</td>
				</tr>
					
				<tr>
					<td  class="fieldLabel" style="border:none">
						<hdiits:caption captionid="FMS.LOCSTATE" bundle="${fmsLables}" />
					</td>
					<td class="fieldLabel">
						<hdiits:text  name="txtlocstate" id="txtlocstateid" captionid="FMS.LOCSTATE" bundle="${fmsLables}"/>
					</td>
					
					<td  class="fieldLabel" style="border:none">
						<hdiits:caption captionid="FMS.LOCDISTRICT" bundle="${fmsLables}" />
					</td>					
					<td class="fieldLabel">
							<hdiits:text  name="txtlocdistrict" id="txtlocdistrictid" captionid="FMS.LOCDISTRICT" bundle="${fmsLables}"/>
					</td>
										
					<td  class="fieldLabel" style="border:none">
						<hdiits:caption captionid="FMS.LOCPINCODE" bundle="${fmsLables}" />
					</td>
					<td class="fieldLabel">
							<hdiits:text  name="txtpincode" id="txtpincodeid" captionid="FMS.LOCPINCODE" bundle="${fmsLables}"/>
					</td>									
				</tr>		
			</c:if>
		</table>
		
	
	<br/><br/>
	<table id="OutwardTable2" class="tabtable">
		<tr>
			<td colspan="4" class="datatableheader"><hdiits:caption	captionid="FMS.SENDERDETAILS" bundle="${fmsLables}" /></td>
		</tr>		
		<tr>
			<td  class="fieldLabel" style="border:none">
				<hdiits:caption	captionid="FMS.FORMSECTION" bundle="${fmsLables}" />
			</td>						
			
			 <td>
				<hdiits:text name="txtBranchName" id="txtBranchNameid" default="${resultMap.branchName}"></hdiits:text>				
				<hdiits:hidden name="hiddBranchId" default="${resultMap.branchId}" />
			</td>			
					
			<td  class="fieldLabel" style="border:none">
				<hdiits:caption	captionid="FMS.DISPATCHDT" bundle="${fmsLables}" />
			</td>
			<td class="fieldLabel">				
				<hdiits:dateTime name="DispatchDt" captionid="FMS.DISPATCHDT" bundle="${fmsLables}"  mandatory="true"/>
			</td>
		</tr>
		<tr>
			<td  class="fieldLabel" style="border:none">
				<hdiits:caption	captionid="FMS.SUBJECT" bundle="${fmsLables}" />
			</td>			
			 <td>
				<hdiits:text name="txtSubj" id="txtSubjid" default="${resultMap.docName}"></hdiits:text>
				<hdiits:hidden name="docId" default="${resultMap.docId}" />
			</td>			
			
			<td  class="fieldLabel" style="border:none">
				<hdiits:caption	captionid="FMS.TICKET" bundle="${fmsLables}" />
			</td>
			<td class="fieldLabel">
				<hdiits:text id="Tkt" name="txtTicket"/>
			</td>
		</tr>
		<tr>
			<td  class="fieldLabel" style="border:none">
				<hdiits:caption	captionid="FMS.DESC" bundle="${fmsLables}" />
			</td>
			<td class="fieldLabel">
				<hdiits:textarea id="Desc" name="txtDesc" maxlength="1000"/>
			</td>
			<td  class="fieldLabel" style="border:none">
				<hdiits:caption	captionid="FMS.REMARKS" bundle="${fmsLables}" />
			</td>
			<td class="fieldLabel">
				<hdiits:text id="Rmks" name="txtRemarks" maxlength="500"/>
			</td>
		</tr>	
		
				
		<c:if test="${resultMap.attachid ne '' }">	
		<tr style="display:none">			
				<td  class="fieldLabel" style="border:none">
						<hdiits:caption captionid="FMS.ATTACHDESC" bundle="${fmsLables}" />
				</td>					
				<td class="fieldLabel">
						<hdiits:text  name="txtattachdesc" id="txtattachdescid" captionid="FMS.ATTACHDESC" bundle="${fmsLables}"/>
				</td>
				
				<td  class="fieldLabel" style="border:none">
						<hdiits:caption captionid="FMS.ATTACHFILENAME" bundle="${fmsLables}" />
				</td>					
				<td class="fieldLabel">
						<hdiits:text  name="txtattachfilename" id="txtattachfilenameid" captionid="FMS.ATTACHFILENAME" bundle="${fmsLables}"/>
				</td>			
		</tr>	
		</c:if>
	</table>
	
	<br><br>
	<table class="tabtable" align="center" id="attachmentTab" border="2" style="display:none">
		<tr>
			<td align="center" class="datatableheader"><b>Attachment</b></td>	
			<td align="center" class="datatableheader"><b>FileName</b></td>		
		</tr>
		<tr>
			<td class="fieldLabel" align="center">
				<c:forEach var="attachmentcnt" items="${attachmentDescList}" varStatus="status1" >	
					<c:out value="${attachmentDescList[status1.index]}"></c:out>
					<br>
				</c:forEach>
			</td>			
			<td class="fieldLabel" align="center">
				<c:forEach var="attachmentcnt" items="${attachmentNameList}" varStatus="status" >	
					<a href="${lurlList[status.index]}"><c:out value="${attachmentNameList[status.index]}"></c:out></a>
					<br>
				</c:forEach>
			</td>
		</tr>		
	</table>
	
	<br><br><br>
	<table id="orderHeader" align="center" border="0" width="100%">	
		<tr>
			<td colspan="4" class="datatableheader">	
				<hdiits:caption	captionid="FMS.ORDERS" bundle="${fmsLables}"/>
			</td>
		</tr>
	</table>
	<c:set var="srNo" value="${1}" />		
	<c:if test="${not empty fmsoutwardDraftCustomVoList}">
		<display:table list="${fmsoutwardDraftCustomVoList}" id="fmsoutwardDraftCustomVoList" style="width:100%" pagesize="10" requestURI="">																																							   
			<display:column style="text-align:center" class="tablecelltext" titleKey="WF.SRNO" sortable="false" headerClass="datatableheader"  value="${srNo}"></display:column>
			<c:set var="srNo" value="${srNo+1}" />														
			<display:column style="text-align:center" class="tablecelltext"  title="DraftId"  sortable="false" headerClass="datatableheader">${fmsoutwardDraftCustomVoList.draftId}</display:column>  							
			<display:column style="text-align:center" class="tablecelltext"  title="Attachment Id"  sortable="false" headerClass="datatableheader">${fmsoutwardDraftCustomVoList.attachmentId}</display:column>  															
			<display:column style="text-align:center" class="tablecelltext"  title="Attachment Name"  sortable="false" headerClass="datatableheader">${fmsoutwardDraftCustomVoList.attachmentName}</display:column>  																			
		</display:table> 	
	</c:if>	
		
	<c:if test="${empty fmsoutwardDraftCustomVoList}">	
			<center>
				<script type="text/javascript">								
					document.getElementById('orderHeader').style.display='none';
				</script>			
			</center>		
	</c:if>		
		
	<br><br>
	<table class="tabtable" align="center">
		<tr>		
			<td class="fieldLabel" align="center">
					<hdiits:button type="button" name="btnOk" captionid="WF.Ok" bundle="${fmsLables}" onclick="javascript:window.close()"/>
			</td>
		</tr>
	</table>	
	</div>	
</div>
<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
</script>
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
</hdiits:form>
<script type="text/javascript">		

	var buttonValCorr=document.getElementById('CorrRadio');
	document.forms[0].fileCorrRadio[0].checked=true;
	buttonValCorr.disabled=true;
	
	document.forms[0].txtoutno.value="${outwardNo}";
	document.forms[0].txtoutrefno.value="${outwardRefNo}";
	document.forms[0].txtFileCorrRefNum.value="${refFileCorrNo}";
	document.forms[0].txtfileCategory.value="${fileCatgry}";
	document.forms[0].txtoutwardtype.value="${outwardType}";
	document.forms[0].txtletterdt.value="${lttrDt}";
	document.forms[0].DispatchDt.value="${dispatch}";	
	/**********************************************************************************/
	var nameArr=new Array();
	if("${sentTo}" != "")
	{			
		nameArr="${sentTo}".split(',');	
		var srno=0;	
		document.getElementById('viewTable').style.display = ''; 	
		for(var i=0; i<nameArr.length; i++)
		{		
			srno++;		
			var a=document.getElementById('viewTable').insertRow();
			var col1=a.insertCell(0);
			var col2=a.insertCell(1);		
			col1.align="center";
			col2.align="center";
			col1.innerHTML=srno;		
			col2.innerHTML=nameArr[i];
		}	
	}	
	/**********************************************************************************/
	if("${enclosure}"=='Y')
	{		
		document.forms[0].chkEnclosure.checked=true;;
	}
	document.forms[0].txtBranchName.value="${branchName}";
	document.forms[0].txtSubj.value="${docName}";	
	document.forms[0].txtDesc.value="${outwardDesc}";
	document.forms[0].txtRemarks.value="${remarks}";
	document.forms[0].txtTicket.value="${ticket}";
	if("${area}"!="")
	{
		document.forms[0].txtarea.value="${area}";
		document.forms[0].txtcity.value="${cityname}";
		document.forms[0].txtextloc.value="${externalloc}";
	}
	if("${locname}"!="")
	{	
		document.forms[0].txtlocname.value="${locname}";	
		document.forms[0].txtloccity.value="${loccity}";
		document.forms[0].txtlocstate.value="${locstate}";
		document.forms[0].txtlocdistrict.value="${locdistrict}";
		document.forms[0].txtpincode.value="${locpincode}";
	}	
	if("${attachid}"!=0)
	{
		document.getElementById('attachmentTab').style.display='';		
	}	
</script>
<% 
}
catch(Exception e) 
{
		e.printStackTrace();
}
%>
