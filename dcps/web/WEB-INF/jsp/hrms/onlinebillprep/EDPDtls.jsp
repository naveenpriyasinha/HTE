<%@page import="com.tcs.sgv.common.valueobject.BillEdpVO"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>  

<fmt:setLocale value='<%=(String)session.getAttribute("locale")%>'/>
<fmt:setBundle basename="resources.expacct.expacct" var="expacctLabels" scope="application"/>

<c:set var="resultObj" value="${result}" scope="request"> </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" scope="request"> </c:set>		
<c:set var="rcptObjHds" value="${resValue.rcptObjHds}" scope="request"> </c:set>
<c:set var="expObjHds" value="${resValue.expObjHds}" scope="request"></c:set>
<c:set var="recObjHds" value="${resValue.recObjHds}" scope="request"></c:set>
<c:set var="expEdpList" value="${resValue.expEdpList}" scope="request"> </c:set>
<c:set var="expObjHdsAdded" value="${resValue.expObjHdsAdded}" scope="request"> </c:set>
<c:set var="recObjHdsAdded" value="${resValue.recObjHdsAdded}" scope="request"> </c:set>
<c:set var="rcptObjHdsAdded" value="${resValue.rcptObjHdsAdded}" scope="request"> </c:set>

<% int counter = 0;%>
<% int expCounter = 0;%>
<% int dCounter1 = 0;%>
<% int dCounter2 = 0;%>

<head>
	<style>
		.tabstyle 
		{
			border-width: 5px 1px 1px 1px; 
			border-color: #2065c0;
			border-style: solid;
		}
		legend 
		{
			padding-left:5px;
			padding-right:5px;
			font-weight:normal; 
			font-family:verdana;
				
			border-width: 0px 0px 1px 0px; 
			border-color: #2065c0;
			border-style: solid;
		}	
	</style>
	<script type="text/javascript">
		var array = new Array();
		var expEdpArry = new Array();
		var deduA = 0.0, deduB = 0.0, expenditure = 0.0, recovery = 0.0;
		var edpRowNo = 0;
		var recEdpRowNo = 0;
		var rcptEdpRowNo = 0;
		var counter = 0;
		var nCounter = 0;
		var rowCount = 0;
		var EDP_TXTEDPCODE = "<fmt:message key="EDP.TXTEDPCODE" bundle="${onlinebillprepAlerts}"></fmt:message>";
		
		function getExpEdpDtls(txtEdpCode, edpRowNo,vCounter)
		{
			return getExpenditureEdpDtls(txtEdpCode, edpRowNo,vCounter);
		}
		function getRecEdpDtls(txtRecEdpCode,recEdpRowNo,vCounter)
		{
			return getRecoveryEdpDtls(txtRecEdpCode,recEdpRowNo,vCounter);
		}
		function getRcptEdpDtls(txtRcptEdpCode,rcptEdpRowNo,vCounter)
		{
			return getReceiptEdpDtls(txtRcptEdpCode,rcptEdpRowNo,vCounter);
		}
	</script>
</head>

<input type="hidden" name="expEdpCounter" value="" />
<input type="hidden" name="recEdpCounter" value="" />

	<fieldset class="tabstyle">
	<legend id="headingMsg"> <b> EDP Details </b> </legend>
	<br />
		<table width="100%">
			<c:choose>
				<c:when test="${resValue.EditBill != null && resValue.EditBill == 'Y'}">
					<tr class="TableHeaderBG">
						<td align="center" class="HLabel">
							Expenditure Details
						</td>
					</tr>
					<tr>
						<td class="Label">
							<font color="red" size="2"><div id="msg"> </div></font>
							<hdiits:button type="button" name="btnAddRowDtl" value="Add Row" onclick="addEdpRow()" ></hdiits:button>
						</td>
					</tr>
					<tr>
						<td>
							<table width="100%" align="center" id="ExpDetPostTbl" >	
							<tbody>
									<% expCounter=0; %>
									<c:forEach var="billEdpVO" items="${expEdpList}" >
									<script type="text/javascript">
										var expEdpSubArr=new Array();
										expEdpSubArr['edpCode']='${billEdpVO.edpCode}';
										expEdpSubArr['edpDesc']='${billEdpVO.edpDesc}';
										expEdpSubArr['objCode']='${billEdpVO.objHdCode}';	
										expEdpSubArr['receiptEdp']='${billEdpVO.receiptEdp}';
										expEdpSubArr['budMjrHd']='${billEdpVO.budmjrHd}';	
										expEdpSubArr['budSubMjrHd']='${billEdpVO.budsubmjrHd}';
										expEdpSubArr['budMinHd']='${billEdpVO.budminHd}';
										expEdpSubArr['budSubHd']='${billEdpVO.budsubHd}';
										expEdpSubArr['budDtlHd']='${billEdpVO.buddtlHd}';
										expEdpSubArr['budObjHd']='${billEdpVO.budobjHd}';						
										expEdpArry[<%=expCounter++%>]=expEdpSubArr;
									</script>
									</c:forEach>
									<tr align="center" class="datatableheader">
										<td class="HLabel" width="15%">
											&nbsp;&nbsp;<hdiits:caption captionid="VDP.EDP_CODE" bundle="${expacctLabels}"/>&nbsp;&nbsp;
										</td>
										<td class="HLabel" width="55%">
											&nbsp;&nbsp;<hdiits:caption captionid="VDP.DESCRIPTION" bundle="${expacctLabels}"/>&nbsp;&nbsp;
										</td>	
										<td class="HLabel" width="15%">
											<hdiits:caption captionid="VDP.BUDGET_CODE" bundle="${expacctLabels}"/>&nbsp;&nbsp;
										</td>	
										<td class="HLabel" width="15%">
											&nbsp;&nbsp;<hdiits:caption captionid="CMN.AMOUNT" bundle="${expacctLabels}"/>&nbsp;&nbsp;
										</td>	
									</tr>
								<%dCounter1=0; %>
								<c:forEach var="billEdpVO" items="${expObjHds}" varStatus="dExpcounter">
									<tr align="center" class="odd">
										<script type="text/javascript">
											var subArray=new Array();
											subArray['edpId']=${billEdpVO.typeEdpId};
											subArray['edpCode']=${billEdpVO.edpCode};
											subArray['category']='${billEdpVO.edpCategory}';
											subArray['expRcpRev']='Expenditure';						
											subArray['amount']=${billEdpVO.edpAmt};
											if(subArray['expRcpRev']=='Expenditure')
											{
												expenditure=parseFloat(expenditure)+parseFloat(subArray['amount']);	
											}
											array[<%=counter++%>]=subArray;
											document.forms[0].expEdpCounter.value = <%=++dCounter1%>;
										</script>
										<td class="Label">
											&nbsp;<c:out value="${billEdpVO.edpCode}"/>
											<input type="hidden" name="hdPayEdpId" value="${billEdpVO.typeEdpId}"/>
											<input type="hidden" name="hdPayEdpCode" value="${billEdpVO.edpCode}"/>
											<input type="hidden" name="digi1_${dExpcounter.count-1}_EDP_CODE" />
											<input type="hidden" name="hdPayEdpCate" value="${billEdpVO.edpCategory}"/>
											<input type="hidden" name="digi1_${dExpcounter.count-1}_EDP_CATEGORY" />
											<input type="hidden" name="hdPayAddDed" value="${billEdpVO.addDedFlag}"/>
											<input type="hidden" name="digi1_${dExpcounter.count-1}_ADD_DED_FLAG" />		
											
										</td>	
										<td class="Label">
											&nbsp;&nbsp;<c:out value="${billEdpVO.edpDesc}"/>&nbsp;
										</td>	
										<td class="Label">
											&nbsp;&nbsp;<c:out value="${billEdpVO.objHdCode}"/>&nbsp;
										</td>	
										<td class="Label">
											&nbsp;<input type="text" name="txtAmt${billEdpVO.edpCode}" size="9" value="${billEdpVO.edpAmt}" id="id_txtAmt${billEdpVO.edpCode}" onkeypress="NumberFormet()" onblur="computeSum(<%=counter-1%>,'${billEdpVO.edpCode}','-','-')" />
												 <input type="hidden" name="digi1_${dExpcounter.count-1}_EDP_AMT" />		 
										</td>
									</tr>	
								</c:forEach>
								
								<c:forEach var="billEdpVO" items="${expObjHdsAdded}" varStatus="counter">
									<tr align="center" class="odd">
										<script type="text/javascript">
											edpRowNo=${counter.index+1};
											var subArray=new Array();
											subArray['edpId']=${billEdpVO.typeEdpId};
											subArray['edpCode']=${billEdpVO.edpCode};
											subArray['category']='${billEdpVO.edpCategory}';
											subArray['expRcpRev']='Expenditure';						
											subArray['amount']=${billEdpVO.edpAmt};
											if(subArray['expRcpRev']=='Expenditure')
												expenditure=parseFloat(expenditure)+parseFloat(subArray['amount']);	
											array[<%=counter++%>]=subArray;
										</script>
										<td class="Label">
											<input type="hidden" name="exprows" value="${counter.index+1}"/>
											<input type="hidden" name="txtDedType${counter.index+1}" id="id_txtDedType${counter.index+1}" value="${billEdpVO.edpCategory}" size="6"/>
											<input type="hidden" name="digi1_${counter.index+1}_EDP_CODE" />
											<input type="text" name="txtEdpCode${counter.index+1}" id="id_txtEdpCode${counter.index+1}" value="${billEdpVO.edpCode}" size="6" onblur='getExpEdpDtls(this,${counter.index+1},<%=counter-1%>)'/>
											<input type="hidden" name="digi1_${counter.index+1}_EDP_CATEGORY" />
											<input type="hidden" name="txtAddDed${counter.index+1}" id="id_txtAddDed${counter.index+1}" value="${billEdpVO.addDedFlag}" size="6"/>
											<input type="hidden" name="digi1_${counter.index+1}_ADD_DED_FLAG" />
										</td>	
										<td id="id_txtEdpDesc${counter.index+1}" class="Label">
											${billEdpVO.edpDesc}
										</td>	
										<td id="id_txtBudCode${counter.index+1}" class="Label">
											${billEdpVO.objHdCode}
										</td>	
										<td class="Label">
											<input type="text" name="txtAmt${counter.index+1}" id="id_txtAmt${counter.index+1}" size="9" value="${billEdpVO.edpAmt}" id="id_txtAmt${billEdpVO.edpCode}" onblur="computeSum(<%=counter-1%>,'${billEdpVO.edpCode}','${counter.index+1}','EXP')" onkeypress="NumberFormet()"/>
											<input type="hidden" name="digi1_${counter.index+1}_EDP_AMT" />
											<img src="images/CalendarImages/DeleteIcon.gif" onclick="deleteRow(this,<%=counter-1%>)" />
										</td>
									</tr>	
								</c:forEach>
								</tbody>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<br />
						</td>
					</tr>
					<tr class="TableHeaderBG">
						<td align="center" class="HLabel">
							Recovery Details
						</td>
					</tr>
					<tr>
						<td class="Label">
							<font color="red" size="2"><div id="msgRec"> </div></font>
							<hdiits:button type="button" name="btnRecAddRowDtl" value="Add Row" onclick="addRecEdpRow()" ></hdiits:button>
						</td>
					</tr>
					<tr>
						<td>
							<table width="100%" align="center" id=recDetPostTbl>	
							<tbody>
								<tr align="center" class="datatableheader">
									<td class="HLabel" width="15%">
										&nbsp;&nbsp;<hdiits:caption captionid="VDP.EDP_CODE" bundle="${expacctLabels}"/>&nbsp;&nbsp;
									</td>
									<td class="HLabel" width="55%">
										&nbsp;&nbsp;<hdiits:caption captionid="VDP.DESCRIPTION" bundle="${expacctLabels}"/>&nbsp;&nbsp;
									</td>	
									<td class="HLabel" width="15%">
										<hdiits:caption captionid="VDP.BUDGET_CODE" bundle="${expacctLabels}"/>&nbsp;&nbsp;
									</td>	
									<td class="HLabel" width="15%">
										&nbsp;&nbsp;<hdiits:caption captionid="CMN.AMOUNT" bundle="${expacctLabels}"/>&nbsp;&nbsp;
									</td>	
								</tr>
								<%dCounter2=0; %>
								<c:forEach var="billEdpVO" items="${recObjHds}" varStatus="digiRecCounter">
									<tr align="center" class="odd">
										<script type="text/javascript">
											var subArray=new Array();
											subArray['edpId']=${billEdpVO.typeEdpId};
											subArray['category']='${billEdpVO.edpCategory}';
											subArray['expRcpRev']='Recovery';						
											subArray['amount']=${billEdpVO.edpAmt};
											if(subArray['expRcpRev']=='Recovery')
												recovery=parseFloat(recovery)+parseFloat(subArray['amount']);	
											array[<%=counter++%>]=subArray;	
											document.forms[0].recEdpCounter.value = <%=++dCounter2%>;					
										</script>
										<td class="Label">
											&nbsp;<c:out value="${billEdpVO.edpCode}"/>
											<input type="hidden" name="hdRecEdpId" value="${billEdpVO.typeEdpId}"/>
											<input type="hidden" name="hdRecEdpCode" value="${billEdpVO.edpCode}"/>
											<input type="hidden" name="digi1_<%=dCounter1%>_EDP_CODE" />
											<input type="hidden" name="hdRecEdpCate" value="${billEdpVO.edpCategory}"/>
											<input type="hidden" name="digi1_<%=dCounter1%>_EDP_CATEGORY" />
											<input type="hidden" name="hdRecAddDed" value="${billEdpVO.addDedFlag}"/>
											<input type="hidden" name="digi1_<%=dCounter1%>_ADD_DED_FLAG" /> 
											
										</td>
										<td class="Label">
											&nbsp;&nbsp;<c:out value="${billEdpVO.edpDesc}"/>&nbsp;
										</td>	
										<td class="Label">
											&nbsp;&nbsp;<c:out value="${billEdpVO.objHdCode}"/>&nbsp;
										</td>
										<td class="Label">
											&nbsp;<input type="text" name="txtRecAmt${billEdpVO.edpCode}" size="9" value="${billEdpVO.edpAmt}" id="id_txtRecAmt${billEdpVO.edpCode}" onblur="computeSum(<%=counter-1%>,'${billEdpVO.edpCode}','-','REC')" onkeypress="NumberFormet()"/>
												  <input type="hidden" name="digi1_<%=dCounter1%>_EDP_AMT" />
										</td>
									</tr>	
									<script type="text/javascript">
										<%=dCounter1++%>;
									</script>
								</c:forEach>
								<c:forEach var="billEdpVO" items="${recObjHdsAdded}" varStatus="recCounter">
									<tr align="center" class="odd">
										<script type="text/javascript">
											recEdpRowNo=${recCounter.index+1};
											var subArray=new Array();
											subArray['edpId']=${billEdpVO.typeEdpId};
											subArray['edpCode']=${billEdpVO.edpCode};
											subArray['category']='${billEdpVO.edpCategory}';
											subArray['expRcpRev']='Recovery';						
											subArray['amount']=${billEdpVO.edpAmt};
											if(subArray['expRcpRev']=='Recovery')
												recovery=parseFloat(recovery)+parseFloat(subArray['amount']);	
											array[<%=counter++%>]=subArray;
										</script>
										<td class="Label">
											<input type="hidden" name="recRows" value="${recCounter.index+1}" />
											<input type="hidden" name="txtRecDedType${recCounter.index+1}" id="id_txtRecDedType${recCounter.index+1}" value="${billEdpVO.edpCategory}" size="6"/>
											<input type="hidden" name="digi1_${recCounter.index+1}_EDP_CODE" />
											<input type="hidden" name="txtRecAddDed${recCounter.index+1}" id="id_txtRecAddDed${recCounter.index+1}" value="${billEdpVO.addDedFlag}" size="6"/>
											<input type="hidden" name="digi1_${recCounter.index+1}_EDP_CATEGORY" />
											<input type="text" name="txtRecEdpCode${recCounter.index+1}" id="id_txtRecEdpCode${recCounter.index+1}" value="${billEdpVO.edpCode}" size="6" onblur='getRecEdpDtls(this,${recCounter.index+1},<%=counter-1%>)'/>
											<input type="hidden" name="digi1_${recCounter.index+1}>_ADD_DED_FLAG" /> 
											</td>	
										<td id="id_txtRecEdpDesc${recCounter.index+1}" class="Label">
											${billEdpVO.edpDesc}
										</td>	
										<td id="id_txtRecBudCode${recCounter.index+1}" class="Label">
											${billEdpVO.objHdCode}
										</td>
										<td class="Label">
											<input type="text" name="txtRecAmt${recCounter.index+1}" id="id_txtRecAmt${recCounter.index+1}" size="9" value="${billEdpVO.edpAmt}" onblur="computeSum(<%=counter-1%>,'${billEdpVO.edpCode}','${recCounter.index+1}','REC')" onkeypress="NumberFormet()"/>
											 <input type="hidden" name="digi1_${recCounter.index+1}_EDP_AMT" />
											<img src="images/CalendarImages/DeleteIcon.gif" onclick="deleteRecRow(this,<%=counter-1%>)" />
										</td>
									</tr>	
								</c:forEach>
							</tbody>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<br />
						</td>
					</tr>
					<tr class="TableHeaderBG">
						<td align="center" class="HLabel">
							Receipt Details
						</td>
					</tr>
					<tr>
						<td class="Label">
							<font color="red" size="2"><div id="msgRcpt"> </div></font>
							<hdiits:button type="button" name="btnRcptAddRowDtl" value="Add Row" onclick="addRcptEdpRow()" ></hdiits:button>
						</td>
					</tr>
					<tr> 
						<td>
							<table width="100%" id="rcptDetPostTbl">
							<tbody>
								<tr>
									<td colspan="4"></td>
								</tr>
								<tr align="center" class="datatableheader">
									<td align="center" class="HLabel" width="14%">
										<hdiits:caption captionid="VDP.EDP_CODE" bundle="${expacctLabels}"/>
									</td>
									<td align="center" class="HLabel" width="14%">
										<hdiits:caption captionid="VDP.EDP_CATEGORY" bundle="${expacctLabels}"/>
									</td>
									<td align="center" class="HLabel" width="14%">
										<hdiits:caption captionid="CMN.MAJORHEAD" bundle="${expacctLabels}"/>
									</td>	
									<td align="center" class="HLabel" width="14%">
										<hdiits:caption captionid="CMN.SUB_MAJORHEAD" bundle="${expacctLabels}"/>
									</td>	
									<td align="center" class="HLabel" width="14%">
										<hdiits:caption captionid="CMN.MINORHEAD" bundle="${expacctLabels}"/>
									</td>
									<td align="center" class="HLabel" width="14%">
										<hdiits:caption captionid="CMN.SUB_HEAD" bundle="${expacctLabels}"/>
									</td>
									<td align="center" class="HLabel">
										<hdiits:caption captionid="CMN.AMOUNT" bundle="${expacctLabels}"/>
									</td>					
								</tr>
								<c:forEach var="billEdpVO" items="${rcptObjHds}" >
								<tr align="center" class="odd">
									<script type="text/javascript">
										var subArray=new Array();
										subArray['edpId']=${billEdpVO.typeEdpId};
										subArray['category']='${billEdpVO.edpCategory}';
										subArray['expRcpRev']='Receipt';						
										subArray['amount']=${billEdpVO.edpAmt};						
										if(subArray['category']=='A')
											deduA=parseFloat(deduA)+parseFloat(subArray['amount']);
										if(subArray['category']=='B')
											deduB=parseFloat(deduB)+parseFloat(subArray['amount']);
										array[<%=counter++%>]=subArray;					
									</script>
									<td class="Label">
										&nbsp;&nbsp;<c:out value="${billEdpVO.edpCode}"/>&nbsp;
										<input type="hidden" name="hdRcptEdpId" value="${billEdpVO.typeEdpId}"/>
										<input type="hidden" name="hdRcptEdpCode" value="${billEdpVO.edpCode}"/>						
									</td>
									<td class="Label">
										&nbsp;&nbsp;<c:out value="${billEdpVO.edpDesc}"/>&nbsp;
										<input type="hidden" name="hdRcptEdpCate" value="${billEdpVO.edpCategory}"/>
									</td> 
									<td class="Label">
										&nbsp;<c:out value="${billEdpVO.budmjrHd}"/>
									</td>	
									<td class="Label">
										&nbsp;<c:out value="${billEdpVO.budsubmjrHd}"/>
									</td>
									<td class="Label">
										&nbsp;<c:out value="${billEdpVO.budminHd}"/>
									</td>
									<td class="Label">
										&nbsp;<c:out value="${billEdpVO.budsubHd}"/>
									</td>
									<td align="center" class="Label">
										&nbsp;<input type="text" name="txtAmt${billEdpVO.edpCode}" size="9" value="${billEdpVO.edpAmt}" id="id_txtAmt${billEdpVO.edpCode}" onblur="computeSum(<%=counter-1%>,'${billEdpVO.edpCode}','-','-')" onkeypress="NumberFormet()"/>
									</td>
								</tr>	
								</c:forEach>
								<c:forEach var="billEdpVO" items="${rcptObjHdsAdded}" varStatus="rcptCounter">
								<tr align="center" class="odd">
									<script type="text/javascript">
										rcptEdpRowNo=${rcptCounter.index+1};
										var subArray=new Array();
										subArray['edpId']=${billEdpVO.typeEdpId};
										subArray['category']='${billEdpVO.edpCategory}';
										subArray['expRcpRev']='Receipt';						
										subArray['amount']=${billEdpVO.edpAmt};						
										if(subArray['category']=='A')
											deduA=parseFloat(deduA)+parseFloat(subArray['amount']);
										if(subArray['category']=='B')
											deduB=parseFloat(deduB)+parseFloat(subArray['amount']);
										array[<%=counter++%>]=subArray;					
										//alert(array[<%=counter-1%>]['amount']);		
									</script>
									<td class="Label">
										<input type="hidden" name="rcptRows" value="${rcptCounter.index+1}" />
									
										<input type="text" name="txtRcptEdpCode${rcptCounter.index+1}" id="id_txtRcptEdpCode${rcptCounter.index+1}" value="${billEdpVO.edpCode}" size="6" onblur='getRcptEdpDtls(this,${rcptCounter.index+1},<%=counter-1%>)'/>
									</td>
									<td class="Label">
										<input type="text" name="txtRcptDedType${rcptCounter.index+1}" id="id_txtRcptDedType${rcptCounter.index+1}" value="${billEdpVO.edpCategory}" size="6" maxlength="1"/>
									</td> 
									<td id="id_txtRcptMjrHdCode${rcptCounter.index+1}" class="Label">
										${billEdpVO.budmjrHd}
									</td>	
									<td id="id_txtRcptSubMjrHdCode${rcptCounter.index+1}" class="Label">
										${billEdpVO.budsubmjrHd}
									</td>
									<td id="id_txtRcptMinHdCode${rcptCounter.index+1}" class="Label">
										${billEdpVO.budminHd}
									</td>
									<td id="id_txtRcptSubHdCode${rcptCounter.index+1}" class="Label">
										${billEdpVO.budsubHd}
									</td>
									<td align="left" nowrap="nowrap" class="Label">
										&nbsp;<input type="text" name="txtRcptAmt${rcptCounter.index+1}" size="7" value="${billEdpVO.edpAmt}" id="id_txtRcptAmt${rcptCounter.index+1}" onblur="computeSum(<%=counter-1%>,'${billEdpVO.edpCode}',${rcptCounter.index+1},'RCP')" onkeypress="NumberFormet()"/>
										<img src="images/CalendarImages/DeleteIcon.gif" onclick="deleteRow(this,<%=counter-1%>)" />
									</td>
								</tr>	
								</c:forEach>				
							<tbody>
							</table>
						</td>
					</tr>
					<script type="text/javascript">
						nCounter = <%=counter%>;
					</script>
					<tr>
						<td>
							<br />
						</td>
					</tr>
					<tr>
						<td>
							<table width="100%" align="center">
								<tr class="datatableheader">	
									<td align="center" class="HLabel" width="25%">
										<hdiits:caption captionid="VDP.DEDUCTION-A" bundle="${expacctLabels}"/>
									</td>
									<td align="center" class="HLabel" width="25%">
										<hdiits:caption captionid="VDP.DEDUCTION-B" bundle="${expacctLabels}"/>
									</td>
									<td align="center" class="HLabel" width="25%">
										Expenditure
									</td>					
									<td align="center" class="HLabel" width="25%">
										Recovery
									</td>					
								</tr>
								<tr align="center" class="odd">
									<td align="center" class="Label">&nbsp;<input type="text" name="DeductionA" id="id_deductionA" size="9" value="0.00" readonly="readonly"/></td>
									<td align="center" class="Label">&nbsp;<input type="text" name="DeductionB" id="id_deductionB" size="9" value="0.00" readonly="readonly"/></td>
									<td align="center" class="Label">&nbsp;<input type="text" name="txtExpenditure" id="id_Expenditure" size="9" value="0.00" readonly="readonly"/></td>
									<td align="center" class="Label">&nbsp;<input type="text" name="txtRecovery" id="id_Recovery" size="9" value="0.00" readonly="readonly"/></td>
								</tr>	
								<script type="text/javascript">
									document.forms[0].DeductionA.value = deduA;
									document.forms[0].DeductionB.value = deduB;
									document.forms[0].txtExpenditure.value = expenditure;
									document.forms[0].txtRecovery.value = recovery;
								</script>
							</table>
						</td>
					</tr>
				</c:when>
				
				<c:when test="${resValue.EditBill != null && resValue.EditBill == 'N'}">
					<tr class="TableHeaderBG">
						<td align="center" class="HLabel">
							Expenditure Details
						</td>
					</tr>
					<tr>
						<td>
							<table width="100%" align="center" id="ExpDetPostTbl" >	
							<tbody>
									<% expCounter=0; %>
									<c:forEach var="billEdpVO" items="${expEdpList}" >
									<script type="text/javascript">
										var expEdpSubArr=new Array();
										expEdpSubArr['edpCode']='${billEdpVO.edpCode}';
										expEdpSubArr['edpDesc']='${billEdpVO.edpDesc}';
										expEdpSubArr['objCode']='${billEdpVO.objHdCode}';	
										expEdpSubArr['receiptEdp']='${billEdpVO.receiptEdp}';
										expEdpSubArr['budMjrHd']='${billEdpVO.budmjrHd}';	
										expEdpSubArr['budSubMjrHd']='${billEdpVO.budsubmjrHd}';
										expEdpSubArr['budMinHd']='${billEdpVO.budminHd}';
										expEdpSubArr['budSubHd']='${billEdpVO.budsubHd}';
										expEdpSubArr['budDtlHd']='${billEdpVO.buddtlHd}';
										expEdpSubArr['budObjHd']='${billEdpVO.budobjHd}';						
										expEdpArry[<%=expCounter++%>]=expEdpSubArr;
									</script>
									</c:forEach>
									<tr align="center" class="datatableheader">
										<td class="HLabel" width="15%">
											&nbsp;&nbsp;<hdiits:caption captionid="VDP.EDP_CODE" bundle="${expacctLabels}"/>&nbsp;&nbsp;
										</td>
										<td class="HLabel" width="55%">
											&nbsp;&nbsp;<hdiits:caption captionid="VDP.DESCRIPTION" bundle="${expacctLabels}"/>&nbsp;&nbsp;
										</td>	
										<td class="HLabel" width="15%">
											<hdiits:caption captionid="VDP.BUDGET_CODE" bundle="${expacctLabels}"/>&nbsp;&nbsp;
										</td>	
										<td class="HLabel" width="15%">
											&nbsp;&nbsp;<hdiits:caption captionid="CMN.AMOUNT" bundle="${expacctLabels}"/>&nbsp;&nbsp;
										</td>	
									</tr>
								<%dCounter1=0; %>
								<c:forEach var="billEdpVO" items="${expObjHds}" varStatus="dExpcounter">
									<tr align="center" class="odd">
										<script type="text/javascript">
											var subArray=new Array();
											subArray['edpId']=${billEdpVO.typeEdpId};
											subArray['edpCode']=${billEdpVO.edpCode};
											subArray['category']='${billEdpVO.edpCategory}';
											subArray['expRcpRev']='Expenditure';						
											subArray['amount']=${billEdpVO.edpAmt};
											if(subArray['expRcpRev']=='Expenditure')
											{
												expenditure=parseFloat(expenditure)+parseFloat(subArray['amount']);	
											}
											array[<%=counter++%>]=subArray;
											document.forms[0].expEdpCounter.value = <%=++dCounter1%>;
										</script>
										<td class="Label">
											&nbsp;<c:out value="${billEdpVO.edpCode}"/>
											<input type="hidden" name="hdPayEdpId" value="${billEdpVO.typeEdpId}"/>
											<input type="hidden" name="hdPayEdpCode" value="${billEdpVO.edpCode}"/>
											<input type="hidden" name="hdPayEdpCate" value="${billEdpVO.edpCategory}"/>
											<input type="hidden" name="hdPayAddDed" value="${billEdpVO.addDedFlag}"/>
														
											<input type="hidden" name="digi1_${dExpcounter.count-1}_EDP_CODE" />
											<input type="hidden" name="digi1_${dExpcounter.count-1}_EDP_CATEGORY" />
											<input type="hidden" name="digi1_${dExpcounter.count-1}_ADD_DED_FLAG" />
																		
											<input type="hidden" name="dhdPayEdpCode${dExpcounter.count-1}" value="${billEdpVO.edpCode}"/>
											<input type="hidden" name="dhdPayEdpCate${dExpcounter.count-1}" value="${billEdpVO.edpCategory}"/>
											<input type="hidden" name="dhdPayAddDed${dExpcounter.count-1}" value="${billEdpVO.addDedFlag}"/>
										</td>	
										<td class="Label">
											&nbsp;&nbsp;<c:out value="${billEdpVO.edpDesc}"/>&nbsp;
										</td>	
										<td class="Label">
											&nbsp;&nbsp;<c:out value="${billEdpVO.objHdCode}"/>&nbsp;
										</td>	
										<td class="Label">
											&nbsp;<input type="text" name="txtAmt${billEdpVO.edpCode}" size="9" value="${billEdpVO.edpAmt}" id="id_txtAmt${billEdpVO.edpCode}" disabled="disabled" onblur="computeSum(<%=counter-1%>,'${billEdpVO.edpCode}','-','-')" onkeypress="NumberFormet()"/>
												 <input type="hidden" name="digi1_${dExpcounter.count-1}_EDP_AMT" />		 
										</td>
									</tr>	
								</c:forEach>
								
								<c:forEach var="billEdpVO" items="${expObjHdsAdded}" varStatus="counter">
									<tr align="center" class="odd">
										<script type="text/javascript">
											edpRowNo=${counter.index+1};
											var subArray=new Array();
											subArray['edpId']=${billEdpVO.typeEdpId};
											subArray['edpCode']=${billEdpVO.edpCode};
											subArray['category']='${billEdpVO.edpCategory}';
											subArray['expRcpRev']='Expenditure';						
											subArray['amount']=${billEdpVO.edpAmt};
											if(subArray['expRcpRev']=='Expenditure')
												expenditure=parseFloat(expenditure)+parseFloat(subArray['amount']);	
											array[<%=counter++%>]=subArray;
										</script>
										<td class="Label">
											<input type="hidden" name="exprows" value="${counter.index+1}"/>
											<input type="hidden" name="txtDedType${counter.index+1}" id="id_txtDedType${counter.index+1}" value="${billEdpVO.edpCategory}" size="6"/>
											<input type="text" name="txtEdpCode${counter.index+1}" id="id_txtEdpCode${counter.index+1}" value="${billEdpVO.edpCode}" size="6" onblur='getExpEdpDtls(this,${counter.index+1},<%=counter-1%>)' disabled="disabled"/>
											<input type="hidden" name="txtAddDed${counter.index+1}" id="id_txtAddDed${counter.index+1}" value="${billEdpVO.addDedFlag}" size="6"/>
										</td>	
										<td class="Label">
											<input type="text" name="txtEdpDesc${counter.index+1}" id="id_txtEdpDesc${counter.index+1}" value="${billEdpVO.edpDesc}" size="6" disabled="disabled"/>
										</td>	
										<td class="Label">
											<input type="text" name="txtBudCode${counter.index+1}" id="id_txtBudCode${counter.index+1}" value="${billEdpVO.objHdCode}" size="6" disabled="disabled" />
										</td>	
										<td class="Label">
											<input type="text" name="txtAmt${counter.index+1}" id="id_txtAmt${counter.index+1}" size="9" value="${billEdpVO.edpAmt}" id="id_txtAmt${billEdpVO.edpCode}" disabled="disabled" onblur="computeSum(<%=counter-1%>,'${billEdpVO.edpCode}','${counter.index+1}','EXP')" onkeypress="NumberFormet()" />
											<img src="images/CalendarImages/DeleteIcon.gif" onclick="deleteRow(this,<%=counter-1%>)" />
										</td>
									</tr>	
								</c:forEach>
								</tbody>
							</table>
						</td>
					</tr>
					<tr>
						<td> 
							<br />
						</td>
					</tr>
					<tr class="TableHeaderBG">
						<td align="center" class="HLabel">
							Recovery Details
						</td>
					</tr>
					<tr>
						<td>
							<table width="100%" align="center" id="recDetPostTbl">	
							<tbody>
								<tr align="center" class="datatableheader">
									<td class="HLabel" width="15%">
										&nbsp;&nbsp;<hdiits:caption captionid="VDP.EDP_CODE" bundle="${expacctLabels}"/>&nbsp;&nbsp;
									</td>
									<td class="HLabel" width="55%">
										&nbsp;&nbsp;<hdiits:caption captionid="VDP.DESCRIPTION" bundle="${expacctLabels}"/>&nbsp;&nbsp;
									</td>	
									<td class="HLabel" width="15%">
										<hdiits:caption captionid="VDP.BUDGET_CODE" bundle="${expacctLabels}"/>&nbsp;&nbsp;
									</td>	
									<td class="HLabel" width="15%">
										&nbsp;&nbsp;<hdiits:caption captionid="CMN.AMOUNT" bundle="${expacctLabels}"/>&nbsp;&nbsp;
									</td>	
								</tr>
								<%dCounter2=0; %>
								<c:forEach var="billEdpVO" items="${recObjHds}" varStatus="digiRecCounter">
									<tr align="center" class="odd">
										<script type="text/javascript">
											var subArray=new Array();
											subArray['edpId']=${billEdpVO.typeEdpId};
											subArray['category']='${billEdpVO.edpCategory}';
											subArray['expRcpRev']='Recovery';						
											subArray['amount']=${billEdpVO.edpAmt};
											if(subArray['expRcpRev']=='Recovery')
												recovery=parseFloat(recovery)+parseFloat(subArray['amount']);	
											array[<%=counter++%>]=subArray;	
											document.forms[0].recEdpCounter.value = <%=++dCounter2%>;					
										</script>
										<td class="Label">
											&nbsp;<c:out value="${billEdpVO.edpCode}"/>
											<input type="hidden" name="hdRecEdpId" value="${billEdpVO.typeEdpId}"/>
											<input type="hidden" name="hdRecEdpCode" value="${billEdpVO.edpCode}"/>
											<input type="hidden" name="hdRecEdpCate" value="${billEdpVO.edpCategory}"/>
											<input type="hidden" name="hdRecAddDed" value="${billEdpVO.addDedFlag}"/>
											
											<input type="hidden" name="digi1_<%=dCounter1%>_EDP_CODE" />
											<input type="hidden" name="digi1_<%=dCounter1%>_EDP_CATEGORY" />
											<input type="hidden" name="digi1_<%=dCounter1%>_ADD_DED_FLAG" /> 
											
											<input type="hidden" name="dhdRecEdpCode<%=dCounter1%>" value="${billEdpVO.edpCode}"/>
											<input type="hidden" name="dhdRecEdpCate<%=dCounter1%>" value="${billEdpVO.edpCategory}"/>
											<input type="hidden" name="dhdRecAddDed<%=dCounter1%>" value="${billEdpVO.addDedFlag}"/>
										</td>
										<td class="Label">
											&nbsp;&nbsp;<c:out value="${billEdpVO.edpDesc}"/>&nbsp;
										</td>	
										<td class="Label">
											&nbsp;&nbsp;<c:out value="${billEdpVO.objHdCode}"/>&nbsp;
										</td>
										<td class="Label">
											&nbsp;<input type="text" name="txtRecAmt${billEdpVO.edpCode}" size="9" value="${billEdpVO.edpAmt}" disabled="disabled" id="id_txtRecAmt${billEdpVO.edpCode}" onblur="computeSum(<%=counter-1%>,'${billEdpVO.edpCode}','-','REC')" onkeypress="NumberFormet()"/>
												  <input type="hidden" name="digi1_<%=dCounter1%>_EDP_AMT" />
										</td>
									</tr>	
									<script type="text/javascript">
										<%=dCounter1++%>;
									</script>
								</c:forEach>
								<c:forEach var="billEdpVO" items="${recObjHdsAdded}" varStatus="recCounter">
									<tr align="center" class="odd">
										<script type="text/javascript">
											recEdpRowNo=${recCounter.index+1};
											var subArray=new Array();
											subArray['edpId']=${billEdpVO.typeEdpId};
											subArray['edpCode']=${billEdpVO.edpCode};
											subArray['category']='${billEdpVO.edpCategory}';
											subArray['expRcpRev']='Recovery';						
											subArray['amount']=${billEdpVO.edpAmt};
											if(subArray['expRcpRev']=='Recovery')
												recovery=parseFloat(recovery)+parseFloat(subArray['amount']);	
											array[<%=counter++%>]=subArray;
										</script>
										<td class="Label">
											<input type="hidden" name="recRows" value="${recCounter.index+1}" />
											<input type="hidden" name="txtRecDedType${recCounter.index+1}" id="id_txtRecDedType${recCounter.index+1}" value="${billEdpVO.edpCategory}" size="6"/>
											<input type="hidden" name="txtRecAddDed${recCounter.index+1}" id="id_txtRecAddDed${recCounter.index+1}" value="${billEdpVO.addDedFlag}" size="6"/>
											<input type="text" name="txtRecEdpCode${recCounter.index+1}" id="id_txtRecEdpCode${recCounter.index+1}" value="${billEdpVO.edpCode}" size="6" onblur='getRecEdpDtls(this,${recCounter.index+1},<%=counter-1%>)' disabled="disabled"/>
										</td>	
										<td id="id_txtRecEdpDesc${recCounter.index+1}" class="Label">
											${billEdpVO.edpDesc}
										</td>	
										<td id="id_txtRecBudCode${recCounter.index+1}" class="Label">
											${billEdpVO.objHdCode}
										</td>
										<td class="Label">
											<input type="text" name="txtRecAmt${recCounter.index+1}" id="id_txtRecAmt${recCounter.index+1}" size="9" value="${billEdpVO.edpAmt}" disabled="disabled" onblur="computeSum(<%=counter-1%>,'${billEdpVO.edpCode}','${recCounter.index+1}','REC')" onkeypress="NumberFormet()" />
											<img src="images/CalendarImages/DeleteIcon.gif" onclick="deleteRecRow(this,<%=counter-1%>)"/>
										</td>
									</tr>	
								</c:forEach>
							</tbody>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<br />
						</td>
					</tr>
					<tr class="TableHeaderBG">
						<td align="center" class="HLabel">
							Receipt Details
						</td>
					</tr>
					<tr> 
						<td>
							<table width="100%" id="rcptDetPostTbl">
							<tbody>
								<tr>
									<td colspan="4"></td>
								</tr>
								<tr align="center" class="datatableheader">
									<td align="center" class="HLabel" width="14%"><hdiits:caption captionid="VDP.EDP_CODE" bundle="${expacctLabels}"/></td>
									<td align="center" class="HLabel" width="14%"><hdiits:caption captionid="VDP.EDP_CATEGORY" bundle="${expacctLabels}"/></td>
									<td align="center" class="HLabel" width="14%"><hdiits:caption captionid="CMN.MAJORHEAD" bundle="${expacctLabels}"/></td>	
									<td align="center" class="HLabel" width="14%"><hdiits:caption captionid="CMN.SUB_MAJORHEAD" bundle="${expacctLabels}"/></td>	
									<td align="center" class="HLabel" width="14%"><hdiits:caption captionid="CMN.MINORHEAD" bundle="${expacctLabels}"/></td>
									<td align="center" class="HLabel" width="14%"><hdiits:caption captionid="CMN.SUB_HEAD" bundle="${expacctLabels}"/></td>
									<td align="center" class="HLabel"><hdiits:caption captionid="CMN.AMOUNT" bundle="${expacctLabels}"/></td>					
								</tr>
								<c:forEach var="billEdpVO" items="${rcptObjHds}" >
								<tr align="center" class="odd">
									<script type="text/javascript">
										var subArray=new Array();
										subArray['edpId']=${billEdpVO.typeEdpId};
										subArray['category']='${billEdpVO.edpCategory}';
										subArray['expRcpRev']='Receipt';						
										subArray['amount']=${billEdpVO.edpAmt};						
										if(subArray['category']=='A')
											deduA=parseFloat(deduA)+parseFloat(subArray['amount']);
										if(subArray['category']=='B')
											deduB=parseFloat(deduB)+parseFloat(subArray['amount']);
										array[<%=counter++%>]=subArray;					
									</script>
									<td class="Label">
										&nbsp;&nbsp;<c:out value="${billEdpVO.edpCode}"/>&nbsp;
										<input type="hidden" name="hdRcptEdpId" value="${billEdpVO.typeEdpId}"/>
										<input type="hidden" name="hdRcptEdpCode" value="${billEdpVO.edpCode}"/>						
									</td>
									<td class="Label">
										&nbsp;&nbsp;<c:out value="${billEdpVO.edpCategory}"/>&nbsp;
										<input type="hidden" name="hdRcptEdpCate" value="${billEdpVO.edpCategory}"/>
									</td> 
									<td class="Label">
										&nbsp;<c:out value="${billEdpVO.budmjrHd}"/>
									</td>	
									<td class="Label">
										&nbsp;<c:out value="${billEdpVO.budsubmjrHd}"/>
									</td>
									<td class="Label">
										&nbsp;<c:out value="${billEdpVO.budminHd}"/>
									</td>
									<td class="Label">
										&nbsp;<c:out value="${billEdpVO.budsubHd}"/>
									</td>
									<td align="center" class="Label">
										&nbsp;<input type="text" name="txtAmt${billEdpVO.edpCode}" size="9" value="${billEdpVO.edpAmt}" id="id_txtAmt${billEdpVO.edpCode}" disabled="disabled" onblur="computeSum(<%=counter-1%>,'${billEdpVO.edpCode}','-','-')" onkeypress="NumberFormet()"/>
									</td>
								</tr>	
								</c:forEach>
								<c:forEach var="billEdpVO" items="${rcptObjHdsAdded}" varStatus="rcptCounter">
								<tr align="center" class="odd">
									<script type="text/javascript">
										rcptEdpRowNo=${rcptCounter.index+1};
										var subArray=new Array();
										subArray['edpId']=${billEdpVO.typeEdpId};
										subArray['category']='${billEdpVO.edpCategory}';
										subArray['expRcpRev']='Receipt';						
										subArray['amount']=${billEdpVO.edpAmt};						
										if(subArray['category']=='A')
											deduA=parseFloat(deduA)+parseFloat(subArray['amount']);
										if(subArray['category']=='B')
											deduB=parseFloat(deduB)+parseFloat(subArray['amount']);
										array[<%=counter++%>]=subArray;					
										//alert(array[<%=counter-1%>]['amount']);		
									</script>
									<td class="Label">
										<input type="hidden" name="rcptRows" value="${rcptCounter.index+1}" />
										<input type="text" name="txtRcptEdpCode${rcptCounter.index+1}" id="id_txtRcptEdpCode${rcptCounter.index+1}" value="${billEdpVO.edpCode}" size="6" disabled="disabled" onblur='getRcptEdpDtls(this,${rcptCounter.index+1},<%=counter-1%>)'/>
									</td>
									<td class="Label">
										<input type="text" name="txtRcptDedType${rcptCounter.index+1}" id="id_txtRcptDedType${rcptCounter.index+1}" value="${billEdpVO.edpCategory}" size="6" maxlength="1" disabled="disabled"/>
									</td> 
									<td id="id_txtRcptMjrHdCode${rcptCounter.index+1}" class="Label">
										${billEdpVO.budmjrHd}
									</td>	
									<td id="id_txtRcptSubMjrHdCode${rcptCounter.index+1}" class="Label">
										${billEdpVO.budsubmjrHd}
									</td>
									<td id="id_txtRcptMinHdCode${rcptCounter.index+1}" class="Label">
										${billEdpVO.budminHd}
									</td>
									<td id="id_txtRcptSubHdCode${rcptCounter.index+1}" class="Label">
										${billEdpVO.budsubHd}
									</td>
									<td align="left" nowrap="nowrap" class="Label">
										&nbsp;<input type="text" name="txtRcptAmt${rcptCounter.index+1}" size="7" value="${billEdpVO.edpAmt}" id="id_txtRcptAmt${rcptCounter.index+1}" disabled="disabled" onblur="computeSum(<%=counter-1%>,'${billEdpVO.edpCode}',${rcptCounter.index+1},'RCP')" onkeypress="NumberFormet()"/>
										<img src="images/CalendarImages/DeleteIcon.gif" onclick="deleteRcptRow(this,<%=counter-1%>)"/>
									</td>
								</tr>	
								</c:forEach>				
							<tbody>
							</table>
						</td>
					</tr>
					<script type="text/javascript">
						nCounter = <%=counter%>;
					</script>
					<tr>
						<td>
							<br />
						</td>
					</tr>
					<tr>
						<td>
							<table width="100%" align="center">
								<tr class="datatableheader" >	
									<td align="center" class="HLabel" width="25%"><hdiits:caption captionid="VDP.DEDUCTION-A" bundle="${expacctLabels}"/></td>
									<td align="center" class="HLabel" width="25%"><hdiits:caption captionid="VDP.DEDUCTION-B" bundle="${expacctLabels}"/></td>
									<td align="center" class="HLabel" width="25%">Expenditure</td>					
									<td align="center" class="HLabel" width="25%">Recovery</td>					
								</tr>
								<tr align="center" class="odd">
									<td align="center" class="Label">&nbsp;<input type="text" name="DeductionA" id="id_deductionA" size="9" value="0.00" disabled="disabled"/></td>
									<td align="center" class="Label">&nbsp;<input type="text" name="DeductionB" id="id_deductionB" size="9" value="0.00" disabled="disabled"/></td>
									<td align="center" class="Label">&nbsp;<input type="text" name="txtExpenditure" id="id_Expenditure" size="9" value="0.00" disabled="disabled"/></td>
									<td align="center" class="Label">&nbsp;<input type="text" name="txtRecovery" id="id_Recovery" size="9" value="0.00" disabled="disabled"/></td>
								</tr>	
								<script type="text/javascript">
									document.forms[0].DeductionA.value = deduA;
									document.forms[0].DeductionB.value = deduB;
									document.forms[0].txtExpenditure.value = expenditure;
									document.forms[0].txtRecovery.value = recovery;
								</script>
							</table>
						</td>
					</tr>
				</c:when>
				
				<c:otherwise>
					<tr class="TableHeaderBG">
						<td align="center" class="HLabel">
							Expenditure Details
						</td>
					</tr>
					<tr>
						
						<td class="Label">
							<font color="red" size="2"><div id="msg"> </div></font>
							<hdiits:button type="button" name="btnAddRowDtl" value="Add Row" onclick="addEdpRow()" ></hdiits:button>
						</td>
					</tr>
					<tr>
						<td>
							<table width="100%" align="center" id="ExpDetPostTbl" >	
							<tbody>
									<%expCounter=0; %>
									<c:forEach var="billEdpVO" items="${expEdpList}" >
									<script type="text/javascript">
										var expEdpSubArr=new Array();
										expEdpSubArr['edpCode']='${billEdpVO.edpCode}';
										expEdpSubArr['edpDesc']='${billEdpVO.edpDesc}';
										expEdpSubArr['objCode']='${billEdpVO.objHdCode}';	
										expEdpSubArr['receiptEdp']='${billEdpVO.receiptEdp}';
										expEdpSubArr['budMjrHd']='${billEdpVO.budmjrHd}';	
										expEdpSubArr['budSubMjrHd']='${billEdpVO.budsubmjrHd}';
										expEdpSubArr['budMinHd']='${billEdpVO.budminHd}';
										expEdpSubArr['budSubHd']='${billEdpVO.budsubHd}';
										expEdpSubArr['budDtlHd']='${billEdpVO.buddtlHd}';
										expEdpSubArr['budObjHd']='${billEdpVO.budobjHd}';						
										expEdpArry[<%=expCounter++%>]=expEdpSubArr;
									</script>
									</c:forEach>
									<tr align="center" class="datatableheader">
										<td class="HLabel" width="15%">
											&nbsp;&nbsp;<hdiits:caption captionid="VDP.EDP_CODE" bundle="${expacctLabels}"/>&nbsp;&nbsp;
										</td>
										<td class="HLabel" width="55%">
											&nbsp;&nbsp;<hdiits:caption captionid="VDP.DESCRIPTION" bundle="${expacctLabels}"/>&nbsp;&nbsp;
										</td>	
										<td class="HLabel" width="15%">
											<hdiits:caption captionid="VDP.BUDGET_CODE" bundle="${expacctLabels}"/>&nbsp;&nbsp;
										</td>	
										<td class="HLabel" width="15%">
											&nbsp;&nbsp;<hdiits:caption captionid="CMN.AMOUNT" bundle="${expacctLabels}"/>&nbsp;&nbsp;
										</td>	
									</tr>
								<%dCounter1=0; %>
								<c:forEach var="billEdpVO" items="${expObjHds}" varStatus="dExpcounter">
									<tr align="center" class="odd">
										<script type="text/javascript">
											var subArray=new Array();
											subArray['edpId']=${billEdpVO.typeEdpId};
											subArray['edpCode']=${billEdpVO.edpCode};
											subArray['category']='${billEdpVO.edpCategory}';
											subArray['expRcpRev']='Expenditure';						
											subArray['amount']=${billEdpVO.edpAmt};
											if(subArray['expRcpRev']=='Expenditure')
											{
												expenditure=parseFloat(expenditure)+parseFloat(subArray['amount']);	
											}
											array[<%=counter++%>]=subArray;
											document.forms[0].expEdpCounter.value = <%=++dCounter1%>;
										</script>
										<td class="Label">
											&nbsp;<c:out value="${billEdpVO.edpCode}"/>
											<input type="hidden" name="hdPayEdpId" value="${billEdpVO.typeEdpId}"/>
											<input type="hidden" name="hdPayEdpCode" value="${billEdpVO.edpCode}"/>
											<input type="hidden" name="hdPayEdpCate" value="${billEdpVO.edpCategory}"/>
											<input type="hidden" name="hdPayAddDed" value="${billEdpVO.addDedFlag}"/>
														
											<input type="hidden" name="digi1_${dExpcounter.count-1}_EDP_CODE" />
											<input type="hidden" name="digi1_${dExpcounter.count-1}_EDP_CATEGORY" />
											<input type="hidden" name="digi1_${dExpcounter.count-1}_ADD_DED_FLAG" />
																		
											<input type="hidden" name="dhdPayEdpCode${dExpcounter.count-1}" value="${billEdpVO.edpCode}"/>
											<input type="hidden" name="dhdPayEdpCate${dExpcounter.count-1}" value="${billEdpVO.edpCategory}"/>
											<input type="hidden" name="dhdPayAddDed${dExpcounter.count-1}" value="${billEdpVO.addDedFlag}"/>
										</td>	
										<td class="Label">
											&nbsp;&nbsp;<c:out value="${billEdpVO.edpDesc}"/>&nbsp;
										</td>	
										<td class="Label"> 
											&nbsp;&nbsp;<c:out value="${billEdpVO.objHdCode}"/>&nbsp;
										</td>	
										<td class="Label">
											&nbsp;<input type="text" name="txtAmt${billEdpVO.edpCode}" size="9" value="${billEdpVO.edpAmt}" id="id_txtAmt${billEdpVO.edpCode}"  onblur="computeSum(<%=counter-1%>,'${billEdpVO.edpCode}','-','-')" onkeypress="NumberFormet()"/>
												 <input type="hidden" name="digi1_${dExpcounter.count-1}_EDP_AMT" />		 
										</td>
									</tr>	
								</c:forEach>
								
								<c:forEach var="billEdpVO" items="${expObjHdsAdded}" varStatus="counter">
									<tr align="center" class="odd">
											<script type="text/javascript">
											edpRowNo=${counter.index+1};
											var subArray=new Array();
											subArray['edpId']=${billEdpVO.typeEdpId};
											subArray['edpCode']=${billEdpVO.edpCode};
											subArray['category']='${billEdpVO.edpCategory}';
											subArray['expRcpRev']='Expenditure';						
											subArray['amount']=${billEdpVO.edpAmt};
											if(subArray['expRcpRev']=='Expenditure')
												expenditure=parseFloat(expenditure)+parseFloat(subArray['amount']);	
											array[<%=counter++%>]=subArray;
																		
										</script>
										<td>
											<input type="hidden" name="exprows" value="${counter.index+1}"/>
											<input type="hidden" name="txtDedType${counter.index+1}" id="id_txtDedType${counter.index+1}" value="${billEdpVO.edpCategory}" size="6"/>
											<input type="text" name="txtEdpCode${counter.index+1}" id="id_txtEdpCode${counter.index+1}" value="${billEdpVO.edpCode}" size="6" onblur='getExpEdpDtls(this,${counter.index+1},<%=counter-1%>)'/>
											<input type="hidden" name="txtAddDed${counter.index+1}" id="id_txtAddDed${counter.index+1}" value="${billEdpVO.addDedFlag}" size="6"/>
										</td>	
										<td id="id_txtEdpDesc${counter.index+1}" class="Label">
											${billEdpVO.edpDesc}
										</td>	
										<td id="id_txtBudCode${counter.index+1}" class="Label">
											${billEdpVO.objHdCode}
										</td>	
										<td class="Label">
											<input type="text" name="txtAmt${counter.index+1}" id="id_txtAmt${counter.index+1}" size="9" value="${billEdpVO.edpAmt}" id="id_txtAmt${billEdpVO.edpCode}" onblur="computeSum(<%=counter-1%>,'${billEdpVO.edpCode}','${counter.index+1}','EXP')" onkeypress="NumberFormet()"/>
											<img src="images/CalendarImages/DeleteIcon.gif" onclick="deleteRow(this,<%=counter-1%>)"/>
										</td>
									</tr>	
								</c:forEach>
								</tbody>
							</table>
						</td>
					</tr>
					<tr>
						<td> 
							<br />
						</td>
					</tr>
					<tr class="TableHeaderBG">
						<td align="center" class="HLabel">
							Recovery Details
						</td>
					</tr>
					<tr>
						<td>
							<font color="red" size="2"><div id="msgRec"> </div></font>
							<hdiits:button type="button" name="btnRecAddRowDtl" value="Add Row" onclick="addRecEdpRow()" ></hdiits:button>
						</td>
					</tr>
					<tr>
						<td>
							<table width="100%" align="center" id="recDetPostTbl">	
							<tbody>
								<tr align="center" class="datatableheader">
									<td class="HLabel" width="15%">
										&nbsp;&nbsp;<hdiits:caption captionid="VDP.EDP_CODE" bundle="${expacctLabels}"/>&nbsp;&nbsp;
									</td>
									<td class="HLabel" width="55%">
										&nbsp;&nbsp;<hdiits:caption captionid="VDP.DESCRIPTION" bundle="${expacctLabels}"/>&nbsp;&nbsp;
									</td>	
									<td class="HLabel" width="15%">
										<hdiits:caption captionid="VDP.BUDGET_CODE" bundle="${expacctLabels}"/>&nbsp;&nbsp;
									</td>	
									<td class="HLabel" width="15%">
										&nbsp;&nbsp;<hdiits:caption captionid="CMN.AMOUNT" bundle="${expacctLabels}"/>&nbsp;&nbsp;
									</td>	
								</tr>
								<%dCounter2=0; %>
								<c:forEach var="billEdpVO" items="${recObjHds}" varStatus="digiRecCounter">
									<tr align="center" class="odd">
										<script type="text/javascript">
											var subArray=new Array();
											subArray['edpId']=${billEdpVO.typeEdpId};
											subArray['category']='${billEdpVO.edpCategory}';
											subArray['expRcpRev']='Recovery';						
											subArray['amount']=${billEdpVO.edpAmt};
											if(subArray['expRcpRev']=='Recovery')
												recovery=parseFloat(recovery)+parseFloat(subArray['amount']);	
											array[<%=counter++%>]=subArray;	
											document.forms[0].recEdpCounter.value = <%=++dCounter2%>;					
										</script>
										<td class="Label">
											&nbsp;<c:out value="${billEdpVO.edpCode}"/>
											<input type="hidden" name="hdRecEdpId" value="${billEdpVO.typeEdpId}"/>
											<input type="hidden" name="hdRecEdpCode" value="${billEdpVO.edpCode}"/>
											<input type="hidden" name="hdRecEdpCate" value="${billEdpVO.edpCategory}"/>
											<input type="hidden" name="hdRecAddDed" value="${billEdpVO.addDedFlag}"/>
											
											<input type="hidden" name="digi1_<%=dCounter1%>_EDP_CODE" />
											<input type="hidden" name="digi1_<%=dCounter1%>_EDP_CATEGORY" />
											<input type="hidden" name="digi1_<%=dCounter1%>_ADD_DED_FLAG" /> 
											
											<input type="hidden" name="dhdRecEdpCode<%=dCounter1%>" value="${billEdpVO.edpCode}"/>
											<input type="hidden" name="dhdRecEdpCate<%=dCounter1%>" value="${billEdpVO.edpCategory}"/>
											<input type="hidden" name="dhdRecAddDed<%=dCounter1%>" value="${billEdpVO.addDedFlag}"/>
										</td>
										<td class="Label">
											&nbsp;&nbsp;<c:out value="${billEdpVO.edpDesc}"/>&nbsp;
										</td>	
										<td class="Label">
											&nbsp;&nbsp;<c:out value="${billEdpVO.objHdCode}"/>&nbsp;
										</td>
										<td class="Label">
											&nbsp;<input type="text" name="txtRecAmt${billEdpVO.edpCode}" size="9" value="${billEdpVO.edpAmt}" id="id_txtRecAmt${billEdpVO.edpCode}" onblur="computeSum(<%=counter-1%>,'${billEdpVO.edpCode}','-','REC')" onkeypress="NumberFormet()"/>
												  <input type="hidden" name="digi1_<%=dCounter1%>_EDP_AMT" />
										</td>
									</tr>	
									<script type="text/javascript">
										<%=dCounter1++%>;
									</script>
								</c:forEach>
								<c:forEach var="billEdpVO" items="${recObjHdsAdded}" varStatus="recCounter">
									<tr align="center" class="odd">
										<script type="text/javascript">
											recEdpRowNo=${recCounter.index+1};
											var subArray=new Array();
											subArray['edpId']=${billEdpVO.typeEdpId};
											subArray['edpCode']=${billEdpVO.edpCode};
											subArray['category']='${billEdpVO.edpCategory}';
											subArray['expRcpRev']='Recovery';						
											subArray['amount']=${billEdpVO.edpAmt};
											if(subArray['expRcpRev']=='Recovery')
												recovery=parseFloat(recovery)+parseFloat(subArray['amount']);	
											array[<%=counter++%>]=subArray;
										</script>
										<td class="Label">
											<input type="hidden" name="recRows" value="${recCounter.index+1}" />
											<input type="hidden" name="txtRecDedType${recCounter.index+1}" id="id_txtRecDedType${recCounter.index+1}" value="${billEdpVO.edpCategory}" size="6"/>
											<input type="hidden" name="txtRecAddDed${recCounter.index+1}" id="id_txtRecAddDed${recCounter.index+1}" value="${billEdpVO.addDedFlag}" size="6"/>
											<input type="text" name="txtRecEdpCode${recCounter.index+1}" id="id_txtRecEdpCode${recCounter.index+1}" value="${billEdpVO.edpCode}" size="6" onblur='getRecEdpDtls(this,${recCounter.index+1},<%=counter-1%>)'/>
											</td>	
										<td id="id_txtRecEdpDesc${recCounter.index+1}" class="Label">
											${billEdpVO.edpDesc}
										</td>	
										<td id="id_txtRecBudCode${recCounter.index+1}" class="Label">
											${billEdpVO.objHdCode}
										</td>
										<td class="Label">
											<input type="text" name="txtRecAmt${recCounter.index+1}" id="id_txtRecAmt${recCounter.index+1}" size="9" value="${billEdpVO.edpAmt}" onblur="computeSum(<%=counter-1%>,'${billEdpVO.edpCode}','${recCounter.index+1}','REC')" onkeypress="NumberFormet()"/>
											<img src="images/CalendarImages/DeleteIcon.gif" onclick="deleteRecRow(this,<%=counter-1%>)"/>
										</td>
									</tr>	
								</c:forEach>
							</tbody>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<br />
						</td>
					</tr>
					<tr class="TableHeaderBG">
						<td align="center" class="HLabel">
							Receipt Details
						</td>
					</tr>
					<tr>
						<td class="Label">
							<font color="red" size="2"><div id="msgRcpt"> </div></font>
							<hdiits:button type="button" name="btnRcptAddRowDtl" value="Add Row" onclick="addRcptEdpRow()" ></hdiits:button>
						</td>
					</tr>
					<tr> 
						<td>
							<table width="100%" id="rcptDetPostTbl">
							<tbody>
								<tr>
									<td colspan="4"></td>
								</tr>
								<tr align="center" class="datatableheader">
									<td align="center" class="HLabel" width="14%"><hdiits:caption captionid="VDP.EDP_CODE" bundle="${expacctLabels}"/></td>
									<td align="center" class="HLabel" width="14%"><hdiits:caption captionid="VDP.EDP_CATEGORY" bundle="${expacctLabels}"/></td>
									<td align="center" class="HLabel" width="14%"><hdiits:caption captionid="CMN.MAJORHEAD" bundle="${expacctLabels}"/></td>	
									<td align="center" class="HLabel" width="14%"><hdiits:caption captionid="CMN.SUB_MAJORHEAD" bundle="${expacctLabels}"/></td>	
									<td align="center" class="HLabel" width="14%"><hdiits:caption captionid="CMN.MINORHEAD" bundle="${expacctLabels}"/></td>
									<td align="center" class="HLabel" width="14%"><hdiits:caption captionid="CMN.SUB_HEAD" bundle="${expacctLabels}"/></td>
									<td align="center" class="HLabel"><hdiits:caption captionid="CMN.AMOUNT" bundle="${expacctLabels}"/></td>					
								</tr>
								<c:forEach var="billEdpVO" items="${rcptObjHds}" >
								<tr align="center" class="odd">
									<script type="text/javascript">
										var subArray=new Array();
										subArray['edpId']=${billEdpVO.typeEdpId};
										subArray['category']='${billEdpVO.edpCategory}';
										subArray['expRcpRev']='Receipt';						
										subArray['amount']=${billEdpVO.edpAmt};						
										if(subArray['category']=='A')
											deduA=parseFloat(deduA)+parseFloat(subArray['amount']);
										if(subArray['category']=='B')
											deduB=parseFloat(deduB)+parseFloat(subArray['amount']);
										array[<%=counter++%>]=subArray;					
									</script>
									<td class="Label">
										&nbsp;&nbsp;<c:out value="${billEdpVO.edpCode}"/>&nbsp;
										<input type="hidden" name="hdRcptEdpId" value="${billEdpVO.typeEdpId}"/>
										<input type="hidden" name="hdRcptEdpCode" value="${billEdpVO.edpCode}"/>						
									</td>
									<td class="Label">
										&nbsp;&nbsp;<c:out value="${billEdpVO.edpDesc}"/>&nbsp;
										<input type="hidden" name="hdRcptEdpCate" value="${billEdpVO.edpCategory}"/>
									</td> 
									<td class="Label">
										&nbsp;<c:out value="${billEdpVO.budmjrHd}"/>
									</td>	
									<td class="Label">
										&nbsp;<c:out value="${billEdpVO.budsubmjrHd}"/>
									</td>
									<td class="Label">
										&nbsp;<c:out value="${billEdpVO.budminHd}"/>
									</td>
									<td class="Label">
										&nbsp;<c:out value="${billEdpVO.budsubHd}"/>
									</td>
									<td align="center" class="Label">
										&nbsp;<input type="text" name="txtAmt${billEdpVO.edpCode}" size="9" value="${billEdpVO.edpAmt}" id="id_txtAmt${billEdpVO.edpCode}" onblur="computeSum(<%=counter-1%>,'${billEdpVO.edpCode}','-','-')" onkeypress="NumberFormet()"/>
									</td>
								</tr>	
								</c:forEach>
								<c:forEach var="billEdpVO" items="${rcptObjHdsAdded}" varStatus="rcptCounter">
								<tr align="center" class="odd">
									<script type="text/javascript">
										rcptEdpRowNo=${rcptCounter.index+1};
										var subArray=new Array();
										subArray['edpId']=${billEdpVO.typeEdpId};
										subArray['category']='${billEdpVO.edpCategory}';
										subArray['expRcpRev']='Receipt';						
										subArray['amount']=${billEdpVO.edpAmt};						
										if(subArray['category']=='A')
											deduA=parseFloat(deduA)+parseFloat(subArray['amount']);
										if(subArray['category']=='B')
											deduB=parseFloat(deduB)+parseFloat(subArray['amount']);
										array[<%=counter++%>]=subArray;					
										//alert(array[<%=counter-1%>]['amount']);		
									</script>
									<td class="Label">
										<input type="hidden" name="rcptRows" value="${rcptCounter.index+1}" />
									
										<input type="text" name="txtRcptEdpCode${rcptCounter.index+1}" id="id_txtRcptEdpCode${rcptCounter.index+1}" value="${billEdpVO.edpCode}" size="6" onblur='getRcptEdpDtls(this,${rcptCounter.index+1},<%=counter-1%>)'/>
									</td>
									<td class="Label">
										<input type="text" name="txtRcptDedType${rcptCounter.index+1}" id="id_txtRcptDedType${rcptCounter.index+1}" value="${billEdpVO.edpCategory}" size="6" maxlength="1"/>
									</td> 
									<td id="id_txtRcptMjrHdCode${rcptCounter.index+1}" class="Label">
										${billEdpVO.budmjrHd}
									</td>	
									<td id="id_txtRcptSubMjrHdCode${rcptCounter.index+1}" class="Label">
										${billEdpVO.budsubmjrHd}
									</td>
									<td id="id_txtRcptMinHdCode${rcptCounter.index+1}" class="Label">
										${billEdpVO.budminHd}
									</td>
									<td id="id_txtRcptSubHdCode${rcptCounter.index+1}" class="Label">
										${billEdpVO.budsubHd}
									</td>
									<td align="left" nowrap="nowrap" class="Label">
										&nbsp;<input type="text" name="txtRcptAmt${rcptCounter.index+1}" size="7" value="${billEdpVO.edpAmt}" id="id_txtRcptAmt${rcptCounter.index+1}" onblur="computeSum(<%=counter-1%>,'${billEdpVO.edpCode}',${rcptCounter.index+1},'RCP')" onkeypress="NumberFormet()"/>
										<img src="images/CalendarImages/DeleteIcon.gif" onclick="deleteRcptRow(this,<%=counter-1%>)"/>
									</td>
								</tr>	
								</c:forEach>				
							<tbody>
							</table>
						</td>
					</tr>
					<script type="text/javascript">
						nCounter = <%=counter%>;
					</script>
					<tr>
						<td>
							<br />
						</td>
					</tr>
					<tr>
						<td>
							<table width="100%" align="center">
								<tr class="datatableheader" >	
									<td align="center" class="HLabel" width="25%"><hdiits:caption captionid="VDP.DEDUCTION-A" bundle="${expacctLabels}"/></td>
									<td align="center" class="HLabel" width="25%"><hdiits:caption captionid="VDP.DEDUCTION-B" bundle="${expacctLabels}"/></td>
									<td align="center" class="HLabel" width="25%">Expenditure</td>					
									<td align="center" class="HLabel" width="25%">Recovery</td>					
								</tr>
								<tr align="center" class="odd">
									<td align="center" class="Label">&nbsp;<input type="text" name="DeductionA" id="id_deductionA" size="9" value="0.00" readonly="readonly"/></td>
									<td align="center" class="Label">&nbsp;<input type="text" name="DeductionB" id="id_deductionB" size="9" value="0.00" readonly="readonly"/></td>
									<td align="center" class="Label">&nbsp;<input type="text" name="txtExpenditure" id="id_Expenditure" size="9" value="0.00" readonly="readonly"/></td>
									<td align="center" class="Label">&nbsp;<input type="text" name="txtRecovery" id="id_Recovery" size="9" value="0.00" readonly="readonly"/></td>
								</tr>	
								<script type="text/javascript">
									document.forms[0].DeductionA.value = deduA;
									document.forms[0].DeductionB.value = deduB;
									document.forms[0].txtExpenditure.value = expenditure;
									document.forms[0].txtRecovery.value = recovery;
								</script>
							</table>
						</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>	
	</fieldset>
	
<script type="text/javascript">
	function computeSumExtSum(rowName, rowNum, rowType, counter)
	{		
		if(rowType=="EXP")
		{	
			sAmount=document.getElementById("id_txtAmt" + rowNum).value
			if(sAmount.length==0 || sAmount==null)
			{
				document.getElementById("id_txtAmt" + rowNum).value = 0;
			}
			var subArray = new Array();
			sEdpCode = document.getElementById("id_txtEdpCode" + rowNum).value;
			if(sEdpCode == null || sEdpCode.length == 0)
			{
				
				document.getElementById("id_txtAmt"+rowNum).value=0;
				return;
			}
			subArray['edpId']='-';
			subArray['edpCode']=document.getElementById("id_txtEdpCode"+rowNum).value;
			subArray['category']=document.getElementById("id_txtDedType"+rowNum).value;
			subArray['expRcpRev']='Expenditure';						
			subArray['amount']=document.getElementById("id_txtAmt"+rowNum).value;
			if(subArray['expRcpRev']=='Expenditure')
				expenditure=parseFloat(expenditure)+parseFloat(subArray['amount']);	
			array[counter]=subArray;
		
			edpCode=document.getElementById("id_txtEdpCode"+rowNum).value;
			computeSum(counter,edpCode,rowNum,rowType);
		}
		if(rowType=="REC")
		{
			sAmount=document.getElementById("id_txtRecAmt"+rowNum).value
			if(sAmount.length==0 || sAmount==null)
			{
				document.getElementById("id_txtRecAmt"+rowNum).value=0;
			}
			sEdpCode=document.getElementById("id_txtRecEdpCode"+rowNum).value;
			if(sEdpCode==null || sEdpCode.length==0)
			{
				document.getElementById("id_txtRecAmt"+rowNum).value=0;
				return;
			}
			var subArray=new Array();
			subArray['edpId'] = '-';
			subArray['edpCode'] = document.getElementById("id_txtRecEdpCode"+rowNum).value;
			subArray['category'] = document.getElementById("id_txtRecDedType"+rowNum).value;
			subArray['expRcpRev'] = 'Recovery';						
			subArray['amount'] = document.getElementById("id_txtRecAmt"+rowNum).value;
			if(subArray['expRcpRev']=='Recovery')
				recovery = parseFloat(recovery)+parseFloat(subArray['amount']);	
			array[counter] = subArray;
			edpCode = document.getElementById("id_txtRecEdpCode"+rowNum).value;
			computeSum(counter, edpCode, rowNum, rowType);
		}
		if(rowType=="RCP")
		{
			sAmount=document.getElementById("id_txtRcptAmt" + rowNum).value;
			if(sAmount.length==0 || sAmount==null)
			{
				document.getElementById("id_txtRcptAmt" + rowNum).value = 0;
			}
			sEdpCode=document.getElementById("id_txtRcptEdpCode"+rowNum).value;
			if(sEdpCode==null || sEdpCode.length==0)
			{
				document.getElementById("id_txtRcptAmt"+rowNum).value = 0;
				return;
			}
			sDedType=document.getElementById("id_txtRcptDedType" + rowNum).value;
			if(sDedType == null || sDedType.length == 0)
			{
				document.getElementById("id_txtRcptDedType" + rowNum).focus();
				return;
			}
			
			var subArray = new Array();
			subArray['edpId'] = '-';
			subArray['edpCode'] = document.getElementById("id_txtRcptEdpCode" + rowNum).value;
			subArray['category'] = document.getElementById("id_txtRcptDedType" + rowNum).value;
			subArray['expRcpRev'] = "Receipt";						
			subArray['amount'] = document.getElementById("id_txtRcptAmt" + rowNum).value;
			if(subArray['category'] == 'A')
				deduA = parseFloat(deduA) + parseFloat(subArray['amount']);
			if(subArray['category'] == 'B')
				deduB = parseFloat(deduB) + parseFloat(subArray['amount']);
			array[counter] = subArray;
			edpCode = document.getElementById("id_txtRcptEdpCode" + rowNum).value;
			computeSum(counter, edpCode, rowNum, rowType);
		}
	}
</script>


