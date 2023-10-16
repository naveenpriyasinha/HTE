
<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script> 
<script type="text/javascript" src="script/common/modalDialog.js"></script> 


<fmt:setBundle basename="resources.hr.payfixation.PayRelated" var="empPayfixationLables" scope="request"/>

<script type="text/javascript">
	var globalCurrent=0;
	var recType='';
	var reasonLookupName = '';
	var reasonLookupDesc = '';
					
	function checkRecType()
	{
		if(checkHistoryRec[0].checked) 
		{
			recType=checkHistoryRec[0].value;
			calculatePayDisplay();
		}
		else 
		{
			recType=checkHistoryRec[1].value;
			calculatePayDisplay();
		}
	}

	function resetData()
	{
		checkHistoryRec[1].checked='true';
		
		document.getElementById("selNewPayScale").value='0';
		
		document.frmEmpPayFixation.payFixationDate.value='';
		
		document.getElementById("selReasonPay").value='0';
		
		document.getElementById("remark").value='';
		
		document.frmEmpPayFixation.newBasicPay.value='';
		
		document.frmEmpPayFixation.NextIncrDate.value='';
		
		calculatePayDisplay();
		
		checkHistoryRec[0].disabled=false;
		checkHistoryRec[1].disabled=false;
		
		updateRow = null;
		
		var x=document.frmEmpPayFixation.selReasonPay;
	    x.remove(3);
	    
	    /*var rsnAry = document.getElementById('selReasonPay');
		var rsnlength  = rsnAry.length;
		for(var i=0; i<=parseInt(rsnlength); i=i+1)
		{
			if(reasonLookupName==rsnAry[i].value)
			{
				rsnAry.remove(i);
			}
		}*/
	}
	
	function closeWindow()
	{
		window.close();
	}
	
	/** Update By Sandip START*/
	function populateBasicPay()
	{	
		var cmbPayScale = document.getElementById("selNewPayScale");
		var NewayScaleValue = cmbPayScale.options[cmbPayScale.selectedIndex].text;
		
		if(document.getElementById("selNewPayScale").value!='0')
		{
			var sptPayScale = NewayScaleValue.split("-");
			var srtAmt = sptPayScale[0];
			document.getElementById("newBasicPay").value=srtAmt;
		}
		else
		{	
			document.getElementById("newBasicPay").value='';
		}
	}
	/** Update By Sandip END*/
	
	
	function basicPayCheck()
	{
		var statusFlag=false;
		var cmbNewPayScale = document.getElementById("selNewPayScale");
		var payScaleValue = cmbNewPayScale.options[cmbNewPayScale.selectedIndex].text;
		var basicPayValue = document.getElementById("newBasicPay").value;
		
		if(payScaleValue!='0')
		{
			var splitPayScale = payScaleValue.split("-");
			var startAmt = splitPayScale[0];
			var incrAmt = splitPayScale[1];
			var endAmt = splitPayScale[2];
	
			
			for(var i=parseInt(startAmt); i<=parseInt(endAmt); i=i+parseInt(incrAmt))
			{
				if(parseInt(basicPayValue)==i)
				{
					statusFlag=true;
					break;
				}
				if(parseInt(incrAmt)==0)
					break;
			}
		}
		else{statusFlag=true;}
			
		return statusFlag;
	}
	
																																//payFixationDate,selReasonPay,selNewPayScale,newBasicPay,NextIncrDate,remark
	function addOrUpdatePayFixationDtls(btnObj)
	{	
		var fieldArray = new Array('payFixationDate','selReasonPay','selNewPayScale','newBasicPay','NextIncrDate');
		var statusValidation = validateSpecificFormFields(fieldArray); 
		
		if(statusValidation)
		{
			var payScaleStatus = basicPayCheck();
			if(payScaleStatus)
			{
				
				if(parseInt(globalCurrent)>0 && recType=='C')
				{
					alert('<fmt:message  bundle="${empPayfixationLables}" key="ValidAnthrCrntAlert"/>');
				}
				else
				{
			    	if (btnObj == 'Add')
					{
						var hdnPayFixationId = 0;
						addOrUpdateRecord('addEmpPayFixationRecord','addPayFixatnDtl&hdnPayFixationId='+ hdnPayFixationId +'&recType='+ recType, new Array('payFixationDate','selReasonPay','selNewPayScale','newBasicPay','NextIncrDate','remark'));
			         }
			         else if(btnObj == 'Update')
					 {
						var hdnPayFixationId = document.getElementById('hdnPayFixationId').value;
						addOrUpdateRecord('updateEmpPayFixationRecord','addPayFixatnDtl&hdnPayFixationId='+ hdnPayFixationId +'&recType='+ recType, new Array('payFixationDate','selReasonPay','selNewPayScale','newBasicPay','NextIncrDate','remark'));
				     }
				 }
			}
			else
			{
				alert('<fmt:message  bundle="${empPayfixationLables}" key="ValidBaisPayAlert"/>');
				document.getElementById("newBasicPay").focus();
			}
	    }	
    }
    
    function addEmpPayFixationRecord()
	{
		if (xmlHttp.readyState == 4)
		{ 				
			if (xmlHttp.readyState == 4)
		    { 				
		    
				displayFieldArray1 = new Array('payFixationDate','selReasonPay','selNewPayScale','newBasicPay','NextIncrDate','remark');
				addDataInTable('txnPayFixation','encXMLpay',displayFieldArray1, 'editEmpPayFixationRecord','deleteEmpPayFixationRecord');
	   	    	resetData();
	   	    	if(recType=='C')
	   	    	{
	   	    		globalCurrent = parseInt(globalCurrent)+1;
	   	    	}
		    }	
	    }
	}
	
	function updateEmpPayFixationRecord() 
	{
	  if (xmlHttp.readyState == 4) 
	  { 	
		var displayFieldArray = new Array('payFixationDate','selReasonPay','selNewPayScale','newBasicPay','NextIncrDate','remark');
		updateDataInTable('encXMLpay', displayFieldArray);		
		resetData(); 
     	document.frmEmpPayFixation.update.disabled=true;
		document.frmEmpPayFixation.add.disabled=false;
		/*if(recType=='C')
	    {
	    	globalCurrent = parseInt(globalCurrent)+1;
	    	alert("globalCurrent"+globalCurrent);
	    }*/
		
	  }
	}
	
	function deleteEmpPayFixationRecord(rowId)
	{
		deleteRecord(rowId);
	}
	
	function deleteDBEmpPayFixationRecord(rowId)
	{
		deleteDBRecord(rowId);
	}
	function editEmpPayFixationRecord(rowId) 
	{
		sendAjaxRequestForEdit(rowId,'populateFormPayFixtn');
		document.frmEmpPayFixation.update.disabled=false;
		document.frmEmpPayFixation.add.disabled=true;
		
	}	   	
	
	function populateFormPayFixtn() 
	{
 		 if (xmlHttp.readyState == 4) 
		 {	 	
		 	if (xmlHttp.status == 200) 
			{
			  	var decXML = xmlHttp.responseText;				  	
	  	        var xmlDOM = getDOMFromXML(decXML);
				document.getElementById('hdnPayFixationId').value = getXPathValueFromDOM(xmlDOM, 'payfixId');
				
				str = getXPathValueFromDOM(xmlDOM, 'payFixDate');
				
						if(str==null || str=='' || str=='null')
						{
						str='';
						}
						if (str != '')
						{
							var dateArray = getDateFromDateObj(str);
							document.getElementById('payFixationDate').value = dateArray;	
						}
						
						
					str = getXPathValueFromDOM(xmlDOM, 'cmnLookupMst/lookupName');	
					reasonLookupName = getXPathValueFromDOM(xmlDOM, 'cmnLookupMst/lookupName');
					reasonLookupDesc = getXPathValueFromDOM(xmlDOM, 'cmnLookupMst/lookupDesc');
						if(str==null || str=='' || str=='null')
						{
						document.getElementById('selReasonPay').value='0';
						}	
						else
						{
							var reasonAry = document.getElementById('selReasonPay');
							var reasonlength  = document.getElementById('selReasonPay').length;
							var flagStatus=true;
							for(var i=0; i<reasonlength; i++)
							{
								if(str==reasonAry[i].value)
								{
									flagStatus=false;
									break;
								}
							}
							if(flagStatus)
							{
								createOptionForReason(reasonLookupName,reasonLookupDesc);
							}
							document.getElementById('selReasonPay').value=str;
						}	
						
				str = getXPathValueFromDOM(xmlDOM, 'revPayScale/scaleId');	
						if(str==null || str=='' || str=='null')
						{
						document.getElementById('selNewPayScale').value='0';
						}	
						else
						{
						document.getElementById('selNewPayScale').value=str;
						}	
						
				str = getXPathValueFromDOM(xmlDOM, 'revPay');	
						if(str==null || str=='' || str=='null')
						{
						str='';
						}
						document.getElementById('newBasicPay').value = str;	   
								
				str = getXPathValueFromDOM(xmlDOM, 'nxtIncrDate');						
						
						if(str==null || str=='' || str=='null')
						{
						str='';
						}
						if (str != '')
						{
							var dateArray = getDateFromDateObj(str);
							document.getElementById('NextIncrDate').value = dateArray;	
						}
						
				str = getXPathValueFromDOM(xmlDOM, 'remarks');	
						if(str==null || str=='' || str=='null')
						{
						str='';
						}
						document.getElementById('remark').value = str;
						
						
				str = getXPathValueFromDOM(xmlDOM, 'flagType');	
						if(str==null || str=='' || str=='null')
						{
						str='N';
						}
						if(str=='Y')
						{
							checkHistoryRec[1].checked='true';
							recType=checkHistoryRec[1].value;
							document.frmEmpPayFixation.calPay.disabled=true;
						}
						else
						{
							checkHistoryRec[0].checked='true';
							recType=checkHistoryRec[0].value;
							document.frmEmpPayFixation.calPay.disabled=false;
						}
						checkHistoryRec[0].disabled=true;
						checkHistoryRec[1].disabled=true;
			 }		   		    
		 }	
	 }
	
	function getDateFromDateObj (dateObj)
	{
		var returnArray;
		var array = dateObj.split (" ");
		
		var date = array[0];

		var dateArray =  date.split ("-");
		var dateStr = dateArray[2]+"/"+dateArray[1] +"/"+dateArray[0]; 
		
		returnArray = dateStr; 
		
		return returnArray;
	}
	
	function submitInDb()
	{
		document.frmEmpPayFixation.action="hrms.htm?actionFlag=savePayFixDtlInDB";
		document.frmEmpPayFixation.submit();
	}
	
	function openWidnow()
	{
		var selectedUserId = document.getElementById('userId').value;
		var newPayScale = document.getElementById("selNewPayScale").value;
		var payFixDate = document.frmEmpPayFixation.payFixationDate.value;
		displayModalDialog ("hrms.htm?actionFlag=showEmpPayfixationOption&UserId="+ selectedUserId +"&NewPayScale="+ newPayScale +"&PayFixDate="+ payFixDate +"" ,"PayFixation" ,"toolbar=no, location=no, directories=no,status=yes, menubar=no, scrollbars=no, resizable=no, top=50, left=100, width="+ 700 +", height="+ 500 +", copyhistory=no");    //update by sandip
	}
	function calculatePayDisplay()
	{
		var checkNewPayScale = document.getElementById("selNewPayScale").value;
		var checkPayFixDt = document.frmEmpPayFixation.payFixationDate.value;
		if(recType=='C' && checkNewPayScale != '0' && checkPayFixDt)
		{
			document.frmEmpPayFixation.calPay.disabled=false;
		}
		else
		{
			document.frmEmpPayFixation.calPay.disabled=true;
		}
	}
	function createOptionForReason(reasonLookupName,reasonLookupDesc)
	{
		var z=document.frmEmpPayFixation.selReasonPay;
 		var y=document.createElement('option');
 		
 		y.text=reasonLookupDesc;
		y.value=reasonLookupName;
		z.add(y);
	}
	function comparePayFixaDate()
	{
		var newPayFixDate = document.frmEmpPayFixation.payFixationDate.value;
		
		if(recType=='C' && lastIncrDate!="" && nextIncrDate!="" && (compareDate(lastIncrDate,newPayFixDate) < 0 || compareDate(newPayFixDate,nextIncrDate) < 0))
		{
			var payDateAlert='<fmt:message  bundle="${empPayfixationLables}" key="PAY_DATE_ALERT1"/> '+lastIncrDate+' <fmt:message  bundle="${empPayfixationLables}" key="PAY_DATE_ALERT2"/> '+nextIncrDate+' <fmt:message  bundle="${empPayfixationLables}" key="PAY_DATE_ALERT3"/>'
			alert(payDateAlert);
			document.frmEmpPayFixation.payFixationDate.value='';
		}
	}
	function populateNextIncrDate()
	{
		var PayFixdate = document.frmEmpPayFixation.payFixationDate.value;
		var splitDate=PayFixdate.split("/");							
		var jday=splitDate[0];
		var jmo=splitDate[1];
		var jyr=parseInt(splitDate[2]);
		jyr=jyr+1;
		var NextIncrDate=jday+'/'+jmo+'/'+jyr;
		document.frmEmpPayFixation.NextIncrDate.value=NextIncrDate;
	}
</script>

<c:set var="resultObj" value="${result}" />
<c:set var="resValue" value="${resultObj.resultValue}" />
<c:set var="payScaleList" value="${resValue.payScaleList}" />
<c:set var="payScaleIdList" value="${resValue.payScaleIdList}" />
<c:set var="reasonList" value="${resValue.reasonList}" />
<c:set var="hrPayfixMst" value="${resValue.hrPayfixMst}" />
<c:set var="hrPayfixMstPayScale" value="${resValue.hrPayfixMstPayScale}" />
<c:set var="xmlFileNmPayFix" value="${resValue.xmlFileNmPayFix}" />
<c:set var="PayFixVOList" value="${resValue.PayFixVOList}" />
<c:set var="payScaleValue" value="${resValue.payScaleValue}" />
<c:set var="tdBGColor" value="#76A9E2"></c:set>
<c:set var="tableBGColor" value="#F0F4FB"></c:set>
<c:set var="selectedUserId" value="${resValue.userId}"></c:set>

<fmt:formatDate pattern="dd/MM/yyyy" value="${resValue.nextIncrDate}" var="nextIncrDate"/>
<fmt:formatDate pattern="dd/MM/yyyy" value="${resValue.lastIncrDate}" var="lastIncrDate"/>

<hdiits:form name="frmEmpPayFixation" validate="true" method="POST" action="">

	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected">
				<a href="#" rel="tcontent1"><b><hdiits:caption captionid="EMP_PAYFIXATION" bundle="${empPayfixationLables}"/></b></a>
			</li>
		</ul>
	</div>
    <div id="tcontent1" class="tabcontent" tabno="0">

	<table bgcolor="#386CB7" align="center"  width="100%">
		<tr align="center">
			<td>
				<font class="Label3" align="center" ><u><font class="Label3" align="center" color="white"><b><fmt:message key="CURRENT_PAY_FIX" bundle="${empPayfixationLables}"/></b></u><span class="UserText" lang="en-us"></span></font>
			</td>
		</tr> 
	</table>
	
	
	
	<table width="100%">
		<tr>
			<td width="20%">
				<b><hdiits:caption captionid="PRESENT_PAY_SCALE" bundle="${empPayfixationLables}"/></b>
			</td>
			<td align="left" width="30%">
				<c:out value="${hrPayfixMstPayScale}"/>
			</td>
			<td align="center" width="20%">
				<b><hdiits:caption captionid="PRESENT_PAY" bundle="${empPayfixationLables}"/></b>
			</td>
			<td align="left" width="30%">
				<c:out value="${hrPayfixMst.revPay}"/>
			</td>
		</tr>
		<tr>
			<td width="20%">
				<b><hdiits:caption captionid="LAST_INCR_DATE" bundle="${empPayfixationLables}"/></b>
			</td>
			<td align="left" width="30%">
       			${lastIncrDate}
			</td>
			<td width="20%">
				<b><hdiits:caption captionid="DUE_DATE_INCR" bundle="${empPayfixationLables}"/></b>
			</td>
			<td align="left" width="30%">
       			${nextIncrDate}
			</td>
		</tr>
	</table>
	
	<table bgcolor="#386CB7" align="center"  width="100%">
		<tr align="center">
			<td>
				<font class="Label3" align="center" ><u><font class="Label3" align="center" color="white"><b><fmt:message key="PAYFIXATION_DTLS" bundle="${empPayfixationLables}"/></b></u><span class="UserText" lang="en-us"></span></font>
			</td>
		</tr> 
	</table>
	
	<table class="tabtable">
		
		<tr>
			<td width="25%">
				<b><hdiits:caption captionid="TYPE_OF_REC" bundle="${empPayfixationLables}"></hdiits:caption></b>
			</td>
			<td align="left" width="25%">
				<hdiits:radio name="rdoTypeOfRec" id="rdoTypeOfRec" value="H" onclick="checkRecType()" captionid="HISTORY"  bundle="${empPayfixationLables}"   />
				<hdiits:radio name="rdoTypeOfRec" id="rdoTypeOfRec" value="C"  onclick="checkRecType()" captionid="CURRENT" bundle="${empPayfixationLables}"  />
			</td>
			<td width="25%"></td>
			<td align="left" width="25%">
				<hdiits:button name="calPay" type="button" captionid="CAL_PAY" bundle="${empPayfixationLables}" onclick="javascript:openWidnow()" readonly="true"></hdiits:button>
			</td>
		</tr>
		
		<tr>
			<td align="left" width="25%">
				<b><hdiits:caption captionid="PAYFIX_DATE" bundle="${empPayfixationLables}"></hdiits:caption></b>
			</td>
			<td align="left" width="25%">
				<hdiits:dateTime name="payFixationDate" captionid="PAYFIX_DATE" bundle="${empPayfixationLables}" validation="txt.isdt,txt.isrequired" afterDateSelect="comparePayFixaDate();calculatePayDisplay();populateNextIncrDate();" maxvalue="31/12/2099"/>
			</td>
			<td align="left" width="25%">
				<b><hdiits:caption captionid="REASON_PAY_FIX" bundle="${empPayfixationLables}"></hdiits:caption></b>
			</td>
			<td align="left" width="25%">
				<hdiits:select name="selReasonPay" id="selReasonPay" captionid="REASON_PAY_FIX" bundle="${empPayfixationLables}" validation="sel.isrequired">
					<option value="0"><hdiits:caption captionid="SELECT" bundle="${empPayfixationLables}" /></option>
					<c:forEach var="reason" items="${reasonList}">
						<option value="<c:out value="${reason.lookupName}"/>">
						<c:out value="${reason.lookupDesc}" /></option>
					</c:forEach>
				</hdiits:select>
			</td>
		</tr>
		
		<tr>
			<td align="left" width="25%">
				<b><hdiits:caption captionid="NEW_PAY_SCALE" bundle="${empPayfixationLables}"/></b>
			</td>
			<td align="left" width="25%">
				<hdiits:select name="selNewPayScale" id="selNewPayScale" captionid="NEW_PAY_SCALE" bundle="${empPayfixationLables}" validation="sel.isrequired" onchange="calculatePayDisplay();populateBasicPay();">
					<option value="0"><hdiits:caption captionid="SELECT" bundle="${empPayfixationLables}"/></option>
					<c:forEach var="payScale" items="${payScaleList}" varStatus="x">
						<c:set var="payScaleId" value="${payScaleIdList[x.index]}"></c:set>
						<option value="<c:out value="${payScaleId}"/>">
						<c:out value="${payScale}" /></option>
					</c:forEach>
				</hdiits:select>
				
			</td>
			<td align="left" width="25%">
				<b><hdiits:caption captionid="NEW_BASIC_PAY" bundle="${empPayfixationLables}"/></b>
			</td>
			<td align="left" width="25%">
				<hdiits:text name="newBasicPay" id="newBasicPay" captionid="NEW_BASIC_PAY" bundle="${empPayfixationLables}" validation="txt.isrequired" onkeypress="return checkNumberOnly(false)"/>
			</td>
		</tr>
		
		<tr>
			<td align="left" width="25%">
				<b><hdiits:caption	captionid="NEXT_INCR_DATE" bundle="${empPayfixationLables}"></hdiits:caption></b>
			</td>
			<td align="left" width="25%">
				<hdiits:dateTime name="NextIncrDate" captionid="NEXT_INCR_DATE" bundle="${empPayfixationLables}" validation="txt.isdt,txt.isrequired" maxvalue="31/12/2099"/>
			</td>
			<td align="left" width="25%">
				<b><hdiits:caption captionid="REMARKS" bundle="${empPayfixationLables}"></hdiits:caption></b>
			</td>
			<td align="left" width="25%">
				<hdiits:textarea name="remark" id="remark" captionid="REMARKS" bundle="${empPayfixationLables}" validation="txt.isrequired" />
			</td>
		</tr>
		
	</table>
	
	<table align="center">	
		<tr>
			<td align="center">
				<br></br><hdiits:button name="add" type="button" captionid="ADD" bundle="${empPayfixationLables}" onclick="javascript:addOrUpdatePayFixationDtls('Add')"></hdiits:button>
			</td>
			<td align="center">
				<br></br><hdiits:button name="update" type="button" captionid="UPDATE" bundle="${empPayfixationLables}" onclick="javascript:addOrUpdatePayFixationDtls('Update')" readonly="true"></hdiits:button>
			</td>
			<td align="center">
				<br></br><hdiits:button name="reset" type="button" captionid="RESET" bundle="${empPayfixationLables}" onclick="resetData()"></hdiits:button>
			</td>
		</tr>
	</table>
			<br>
	<table id='txnPayFixation' name="EmpPayFixation" border="1" borderColor="black" align="center" width="90%" cellpadding="1"
		cellspacing="1" style="border-collapse: collapse">
		<tr>
			<td style="display:none">&nbsp;</td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="PAYFIX_DATE" bundle="${empPayfixationLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="REASON_PAY_FIX" bundle="${empPayfixationLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NEW_PAY_SCALE" bundle="${empPayfixationLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NEW_BASIC_PAY" bundle="${empPayfixationLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NEXT_INCR_DATE" bundle="${empPayfixationLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="REMARKS" bundle="${empPayfixationLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="EDT_DEL" bundle="${empPayfixationLables}" /></label></b></td>
		</tr>
	</table>
	
	<c:forEach items="${PayFixVOList}" var="PayFixVO" varStatus="x">
			<c:set var="currentXMLFileE" value="${xmlFileNmPayFix[x.index]}"></c:set>
			<fmt:formatDate var="PayFixDate" pattern="dd/MM/yyyy" value="${PayFixVO.payFixDate}" type="date" />
				<c:set var="PayFixDate" value="${PayFixDate}" />
			<c:set var="ReasonPayFix" value="${PayFixVO.cmnLookupMst.lookupDesc}" />
			<c:set var="NewPayScale" value="${payScaleValue[x.index]}" />
			<c:set var="NewBasicScale" value="${PayFixVO.revPay}" />
			<fmt:formatDate var="NextIncDate" pattern="dd/MM/yyyy" value="${PayFixVO.nxtIncrDate}" type="date" />
			<c:set var="NextIncDate" value="${NextIncDate}" />
			<c:set var="Remarks" value="${PayFixVO.remarks}" />
				<script type="text/javascript">
					var xmlFileNameE = '${currentXMLFileE}';
					var displayFieldArray1  = new Array('${PayFixDate}','${ReasonPayFix}','${NewPayScale}','${NewBasicScale}','${NextIncDate}','${Remarks}');
					addDBDataInTable('txnPayFixation','encXMLpayFixtn',displayFieldArray1,xmlFileNameE, 'editEmpPayFixationRecord','deleteDBEmpPayFixationRecord');
				</script>
    </c:forEach>		
	
	<table align="center">
		<td>
			<br></br><hdiits:button name="Submit" type="button" captionid="SUBMIT" bundle="${empPayfixationLables}" onclick="submitInDb()"/>
		</td>
		<td>
			<br></br><hdiits:button name="Close" type="button" captionid="CLOSE" bundle="${empPayfixationLables}" onclick="closeWindow()"></hdiits:button>
		</td>
	</table>	
		

<script type="text/javascript">

	var checkHistoryRec=document.frmEmpPayFixation.rdoTypeOfRec;
	checkHistoryRec[1].checked='true';
	recType=checkHistoryRec[1].value;
	document.frmEmpPayFixation.NextIncrDate.readOnly=true;
	document.frmEmpPayFixation.payFixationDate.readOnly=true;
	initializetabcontent("maintab");
	
	var nextIncrDate = '${nextIncrDate}';
	var lastIncrDate = '${lastIncrDate}';
</script>

<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>"'controlNames="payFixationDate,selReasonPay,selNewPayScale,newBasicPay,NextIncrDate" />
	


<hdiits:hidden name="hdnPayFixationId" id="hdnPayFixationId" />
<hdiits:hidden name="userId" id="userId" default="${selectedUserId}"/>

</div>
</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
