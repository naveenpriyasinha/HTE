
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>


<%@page import="com.tcs.sgv.common.utils.DateUtility"%>
<fmt:setBundle basename="resources.trng.AllotmentLables" var="allotLables" scope="request" />
<fmt:setBundle basename="resources.trng.TrainingMstLables" var="trngLables" scope="request" />
<fmt:message key="TR.ALLOT_TO_VAL" bundle="${allotLables}" var="ALLOT_TO_VAL"/>
<fmt:message key="TR.BATCH_SIZE_VAL" bundle="${allotLables}" var="BATCH_SIZE_VAL"/>
<fmt:message key="TR.NO_SEAT_VAL" bundle="${allotLables}" var="NO_SEAT_VAL"/>
<fmt:message key="TR.NOSEAT_BATCH_VAL" bundle="${allotLables}" var="NOSEAT_BATCH_VAL"/>


<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/calendar.js"/></script>
<script type="text/javascript" src="script/training/training.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="script/hrms/training/seatAllotment.js"></script>


<c:set var="resultObjAllot" value="${result}"></c:set>
<c:set var="resValueAllot" value="${resultObjAllot.resultValue}"></c:set>	
<c:set var="allotList" value="${resValueAllot.allotList}" > </c:set>
<c:set var="editFlag" value="${resValueAllot.editFlag}" > </c:set>
<c:set var="trngVO" value="${resValueAllot.trngVO}" > </c:set>
<c:set var="schId" value="${resValueAllot.schId}" > </c:set>
<c:set var="langId" value="${resValueAllot.langId}" > </c:set>
<c:set var="allotList" value="${resValueAllot.allotList}" > </c:set>
<c:set var="postList" value="${resValueAllot.postList}" > </c:set>
<c:set var="xmlFilePathNameForMulAdd" value="${resValueAllot.xmlFilePathNameForMulAdd}" > </c:set>
<c:set var="readOnlyAfterResubmit" value="${resValueAllot.readOnlyAfterResubmit}" > </c:set>
<c:choose>
<c:when test="${readOnlyAfterResubmit eq true }">
	<c:set var="tttt" value="Y"></c:set>
</c:when>
<c:otherwise>
<c:set var="tttt" value="N"></c:set>
</c:otherwise>
</c:choose>
<script type="text/javascript">
var totalNoOfSeat=0;
var postIdName= new Array();
var tmpCount;

function addAllotment() 
{
	  if (xmlHttp.readyState == 4)
	  { 				
		  	var displayFieldArray = new Array("allotedTo", "NoOfSeats");
			addDataInTable('txnAdd', 'encXML1', displayFieldArray, 'editRecordAllot','deleteRecord');				
   	    	resetDataAllot();   	
	   }	
}

function updateAllotment() {

	  if (xmlHttp.readyState == 4) { 
			var displayFieldArray = new Array("allotedTo", "NoOfSeats");
			updateDataInTable('encXML1', displayFieldArray);
			resetDataAllot();
			document.SchMST.btnAdd.style.display="";
			document.SchMST.btnUpdate.style.display="none";
			document.SchMST.allotedTo.disabled="";
   	  }
}




	
function deleteRecord(rowId) 
{
	var row = document.getElementById(rowId);
	var tmp=row.cells[2].innerHTML;
	totalNoOfSeat=totalNoOfSeat-parseInt(tmp);
	var lenPostIdName=postIdName.length;
	for(var k=0;k<lenPostIdName;k++)
	{
		var tmpIdName=postIdName[k];
		var indexOfComm=tmpIdName.indexOf(',');
		var postidTmp=tmpIdName.substring(0,indexOfComm);
		var postNameTmp=tmpIdName.substring(indexOfComm+1);
		if(row.cells[1].innerHTML==postNameTmp)
		{
			deleteFromDuplicateArrayByValue('txnAdd',postidTmp);
		}
	}
	
	var delCap = "";
	var delNtAlllowed = "";
	try
	{
		delCap = cmnLblArray[2];
		delNtAlllowed = cmnLblArray[3];
	}
	catch (e)
	{
		delNtAlllowed = "You can't delete record,\n Because you have open one record for update";
		delCap = "Are you sure you want to delete this record ?";
	}
	
	if(updateRow != null)
	{
		alert (delNtAlllowed);
		return false;
	}

	var answer = confirm(delCap);
	if(answer)
	{	
		var delRow = document.getElementById(rowId);
		delRow.parentNode.deleteRow(delRow.rowIndex);		
	}
	return answer;
}	

function deleteDBRecord(rowId)
{
		var row = document.getElementById(rowId);
		var tmp=row.cells[2].innerHTML;
		totalNoOfSeat=totalNoOfSeat-parseInt(tmp);
		var lenPostIdName=postIdName.length;
		for(var k=0;k<lenPostIdName;k++)
		{
			var tmpIdName=postIdName[k];
			var indexOfComm=tmpIdName.indexOf(',');
			var postidTmp=tmpIdName.substring(0,indexOfComm);
			var postNameTmp=tmpIdName.substring(indexOfComm+1);
			if(row.cells[1].innerHTML==postNameTmp)
			{
				deleteFromDuplicateArrayByValue('txnAdd',postidTmp);
			}
		}
		
		var delCap = "";
		var delNtAlllowed = "";
		try
		{
			delCap = cmnLblArray[2];
			delNtAlllowed = cmnLblArray[3];
		}
		catch (e)
		{
			delNtAlllowed = "You can't delete record,\n Because you have open one record for update";
			delCap = "Are you sure you want to delete this record ?";
		}
		if(updateRow != null)
		{
			alert (delNtAlllowed);
			return false;
		}
		var answer = confirm(delCap);
		if(answer)
		{
			var delRow 	= document.getElementById(rowId);
			var content = delRow.cells[0].innerHTML;
			showAlert(content);
			content = content.replace(".xml_N",".xml_D");
			content = content.replace(".xml_U",".xml_D");
			delRow.cells[0].innerHTML = content;					
			delRow.style.display='none';
		}
		
	return answer;	
}

function populateFormAllot() {
	  if (xmlHttp.readyState == 4) 
	  {
	
	  	var decXML = xmlHttp.responseText;
		var xmlDOM = getDOMFromXML(decXML);

		var tmp=getXPathValueFromDOM(xmlDOM, 'noOfSeat');
		totalNoOfSeat=totalNoOfSeat-parseInt(tmp);
		
	
		if(getXPathValueFromDOM(xmlDOM, 'orgPostMstAllotedTo/postId')!=null)	{
			document.SchMST.allotedTo.value = getXPathValueFromDOM(xmlDOM, 'orgPostMstAllotedTo/postId');   		    
		}
		document.SchMST.NoOfSeats.value = getXPathValueFromDOM(xmlDOM, 'noOfSeat');   
		if(getXPathValueFromDOM(xmlDOM, 'allotmentId')!=null)	{	
			document.SchMST.hdnAllotId.value = getXPathValueFromDOM(xmlDOM, 'allotmentId');
		}
		document.SchMST.btnAdd.style.display='none';    
		document.SchMST.btnUpdate.style.display='';
		document.SchMST.allotedTo.disabled='true';
	}
}

</script>


<hdiits:hidden name="hdnAllotId" id="hdnAllotId" /> 



<TABLE class="tabtable">
		 
		<c:if test="${editFlag eq 'Y'}">
		<tr> 
		<td class="fieldLabel" width="20%">
		<b><hdiits:caption captionid="TR.TrainingName"	bundle="${trngLables}" /></b>
		<hdiits:hidden name="hdntrngId" id="hdntrngId" default="${trngVO.trainingMstId}"/>	
		<hdiits:hidden name="hdnschId" id="hdnschId" default="${schId}"/>	
		<hdiits:hidden name="editFlag" id="editFlag" default="${editFlag}"/>	
		
		</td>
		<td class="fieldLabel" width="25%"><c:out value="${trngVO.trainingName}" /></td>
		<td class="fieldLabel" width="20%"><b><hdiits:caption captionid="TR.TrainingType"	bundle="${trngLables}" /></b></td>
		<td class="fieldLabel" width="25%"><c:out value="${trngVO.cmnLookupMstTrainingTypeLookupId.lookupName}" /></td>
		</tr>
		<tr>
		<td class="fieldLabel"></td>
		<td class="fieldLabel"></td>
		</tr>
		</c:if>
		
		
		
		<tr height="10"></tr>
		<tr>
		<td class="datatableheader" colspan="6"><b>Seat Allotment</b></td>
		</tr>
		<tr height="10"></tr>
		<tr>
			<td class="fieldLabel" width="10%"><hdiits:caption captionid="TR.TO"	bundle="${allotLables}" /></td>
			<td class="fieldLabel" width="20%">
			<hdiits:select name="allotedTo" id="allotedTo" captionid="TR.TO" bundle="${allotLables}" sort="false" readonly="${readOnlyAfterResubmit}"> 
			<hdiits:option value="-1">-----select----</hdiits:option>
			 <c:forEach var="postListTuple" items="${postList}">
					<option value='<c:out value="${postListTuple.postId}"/>'>
					
					<c:set var="postSet" value="${postListTuple.orgPostDetailsRlt}"/>
	
					<c:forEach var="i" items="${postSet}">
					<c:if test="${i.cmnLanguageMst.langId == langId}">
					<c:set var="dsgnName" value="${i.orgDesignationMst.dsgnName}"></c:set>
					<c:set var="locName" value="${i.cmnLocationMst.locName}"></c:set>
					</c:if>
					</c:forEach>
					
					
					<c:out value="${dsgnName} - ${locName}"></c:out>
					
					
				
					
					</option>
			</c:forEach>
			</hdiits:select>
			</td>
			<td class="fieldLabel" width="10%"><hdiits:caption captionid="TR.NoOfSeats"	bundle="${allotLables}" /></td>
			<td class="fieldLabel" width="20%"><hdiits:number name="NoOfSeats" captionid="TR.NoOfSeats" bundle="${allotLables}" validation="txt.isnumber" maxlength="4" floatAllowed="false" readonly="${readOnlyAfterResubmit}"/></td>
			<td class="fieldLabel" width="40%">

			<hdiits:button name="btnAdd" id="btnAdd" type="button" classcss="buttontag" captionid="TR.ADD" bundle="${allotLables}" onclick="javascript:addUpdateRecord('addAllotment','${ALLOT_TO_VAL}','${BATCH_SIZE_VAL}','${NO_SEAT_VAL}','${NOSEAT_BATCH_VAL}')" />
			<hdiits:button name="btnUpdate" id="btnUpdate" style="display:none" type="button" classcss="buttontag" captionid="TR.UPDATE" bundle="${allotLables}" onclick="addUpdateRecord('updateAllotment','${ALLOT_TO_VAL}','${BATCH_SIZE_VAL}','${NO_SEAT_VAL}','${NOSEAT_BATCH_VAL}')"/>
			</td>
	
		</tr>
</TABLE>

<table id="txnAdd" style="display:none"  border="1" align="center" width="80%"> 
		<tr>
    		<td class="datatableheader" width="15%"><hdiits:caption captionid="TR.TO" bundle="${allotLables}" /></td>
    		<td class="datatableheader" width="5%"><hdiits:caption captionid="TR.NoOfSeats" bundle="${allotLables}" /></td>
    		<c:choose>
    			<c:when test="${readOnlyAfterResubmit eq 'false'}">
    				<td class="datatableheader" width="10%" >Edit / Delete</td> 
    			</c:when>
    			<c:otherwise>
    				<td class="datatableheader" width="10%" >Edit / Delete</td> 
    			</c:otherwise>
    		</c:choose>
    		
    	</tr>
</table>  

    <c:if test="${allotList != null}">
		<script type="text/javascript">
			arrMulti['txnAdd'] = new Array();
		</script>
	</c:if>

	<c:forEach items="${allotList}" var="allotListtuples" varStatus="x">
	<c:set var="curXMLFileName" value="${xmlFilePathNameForMulAdd[x.index]}" ></c:set>
	<c:set var="postSet" value="${allotListtuples.orgPostMstAllotedTo.orgPostDetailsRlt}"/>
	
	<c:forEach var="i" items="${postSet}">
	<c:if test="${i.cmnLanguageMst.langId == langId}">
	<c:set var="dsgnName" value="${i.orgDesignationMst.dsgnName}"></c:set>
	<c:set var="locName" value="${i.cmnLocationMst.locName}"></c:set>
	</c:if>
	</c:forEach>
	
	
	<c:set var="noOfseatsE" value="${allotListtuples.noOfSeat}"/>
		<script type="text/javascript">
		var dsgn='${dsgnName}';
		var loc='${locName}';
		var dsgnloc=dsgn+"-"+loc;
		
		tmpCount='${x.index}';
		postIdName[tmpCount]='${allotListtuples.orgPostMstAllotedTo.postId}'+","+ dsgnloc;
		var tmpNoOfSeat='${noOfseatsE}';
		totalNoOfSeat=totalNoOfSeat+parseInt(tmpNoOfSeat);
		var xmlFileName = '${curXMLFileName}';
		var displayFieldA  = new Array(dsgnloc,'${noOfseatsE}');
		var cc="${tttt}";
	   
		if(cc == "Y")
		{
			document.SchMST.btnAdd.style.display='none';
			addDBDataInTable('txnAdd','addedAllotment',displayFieldA,xmlFileName,'', '', '');
		}
		<c:if test="${allotListtuples.fmsCorrMst ne null && tttt eq 'N'}" >
			addDBDataInTable('txnAdd','addedAllotment',displayFieldA,xmlFileName,'editRecordAllot', 'deleteDBRecord', '');
		</c:if>
		<c:if test="${allotListtuples.fmsCorrMst eq null}" >
			addDBDataInTable('txnAdd','addedAllotment',displayFieldA,xmlFileName,'editRecordAllot', 'deleteDBRecord', '');
		</c:if>
		var rowId = 'rowEditXmlKey${x.index + 1}';
		arrMulti['txnAdd'][rowId] = '${allotListtuples.orgPostMstAllotedTo.postId}';
		</script>
	</c:forEach>



		




