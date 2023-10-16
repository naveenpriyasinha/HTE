<%@ include file="../core/include.jsp" %>
<%@page import="com.tcs.sgv.common.utils.DateUtility,com.tcs.sgv.common.valueobject.CmnLookupMst,java.util.List,java.util.Iterator"%>
<%@page import="java.util.Date, com.tcs.sgv.core.valueobject.ResultObject,java.util.Map"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
<%@page import="com.tcs.sgv.common.valuebeans.CommonVO"%>
<%@page import="com.tcs.sgv.common.valueobject.TrnReceiptDetails"%>
<%@page import="java.math.BigDecimal"%>
<fmt:setBundle basename="resources.billproc.billproc_en_US" var="billprocLabels" scope="application"/>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>    
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<fmt:setLocale value='<%=(String)session.getAttribute("locale")%>'/>
<fmt:setBundle basename="resources.app.diary.DiaryLables" var="diaryLables" scope="request"/>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="com.tcs.sgv.common.helper.SessionHelper"%>
<%@page import="com.tcs.sgv.common.dao.CmnLocationMstDaoImpl"%>
<%@page import="com.tcs.sgv.core.service.ServiceLocator"%>
<%@page import="com.tcs.sgv.common.valueobject.CmnLocationMst"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.tcs.sgv.billproc.counter.valueobject.NewBillVO"%>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>COUNTER BRANCH : Inward Physical Bills </title>
		
		<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
		
		<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
		<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
		<script type="text/javascript"	src="<c:url value="script/common/tabcontent.js"/>"></script>
		<script type="text/javascript"  src="script/common/commonfunctions.js"></script>
		<script  type="text/javascript"  src="script/common/CalendarPopup.js"></script>
		<script  type="text/javascript"  src="script/common/tagLibValidation.js"></script>
		<script  type="text/javascript"  src="script/billproc/validation.js"></script>
		<script src="script/common/prototype.js" language="JavaScript" type="text/javascript"></script>
		<script>
		
		<%
			String  locale = (String)session.getAttribute("locale");
			Locale localee = new Locale(locale);
			ResourceBundle bundle =ResourceBundle.getBundle("resources/billproc/billproc",localee);		
			ResourceBundle lObjRsrcBndle = ResourceBundle.getBundle("resources/billproc/BillprocConstants");
			String lStrTCBill = bundle.getString("CMN.TCBill");
			String lStrRegular = bundle.getString("CMN.RegularBill");
			String lStrNil = bundle.getString("CMN.NilBill");			
			System.out.println("Value of TC Bill : " +lStrTCBill);
			System.out.println("Value of Regular Bill : " +lStrRegular);
			System.out.println("Value of Nil Bill : " +lStrNil);
			
			if(session.getAttribute("status")!=null)
			{
				String state = (String)session.getAttribute("status");
			}
			String billCntrlNum = "";
			ResultObject rs = (ResultObject)request.getAttribute("result");
			Map map = (Map) rs.getResultValue();
			NewBillVO newbillVO = (NewBillVO)map.get("NewBillVO");
			if(newbillVO != null)
			{
				billCntrlNum = (String)map.get("BillCtrlNo");
			}
			
	%>
		
		var defaultEmptyOK = false;
		var lookupArray = new Array();
		function setText(mThis)
	    {
	    	document.getElementById("txtBudDesc").value = lookupArray[mThis.selectedIndex];
		}

		function showText()
		{
			hideHighLight(document.getElementById('id_cmbDetailHead'));
			var grantAmt = document.getElementById('id_txtGrantAmt').value;			
			if(grantAmt==0)
			{
				document.getElementById('id_txtGrantAmt').value='';
			}
			
			if(isEmpty(document.getElementById("id_cmbSubHead").value)==false)
				document.getElementById('id_cmbDetailHead').value="00";
		}  
		function validateDDO()
		{	
			hideHighLight(document.getElementById('id_CardexNo'));
			var DdoName = document.getElementById('DDOName').value;
			var DdoCode = document.getElementById('DDOCode').value;
			var DdoNo = document.getElementById('DDONo').value;			
			var BillCntrlNo = document.getElementById('id_BillControlNo').value;
						
			if(DdoName=='null' || DdoCode=='null' || DdoNo=='null' || BillCntrlNo=='null')
			{
				alert('Invalid Cardex Number.');
				document.getElementById('id_CardexNo').value='';
				document.getElementById('DDONo').value='';
				document.getElementById('DDOName').value='';
				document.getElementById('DDOCode').value='';
				var deptDdo = document.getElementById('DDODept');
			    var i=0;	
			    var x=document.getElementById("DDODept");
			    for(i=x.length-1;i>=0;i--)
			    {
				    x.remove(i);
			    }
				document.getElementById('id_BillControlNo').value='';				
				document.getElementById('id_CardexNo').focus();
				return false;
			}
			return true;
		}
		function displ()
		{
			document.frmCntrInwPhyBills.actionBtn.value = 'forward';
			saveBillUsingAjax();					
		}
			
/*		function showForward(url)
		{
			if(savevalid()==true)
			{
				var audName = document.getElementById('id_AuditorName').value;
				if(audName <= 0)
				{
					alert('Auditor Name is required to be select');
					document.getElementById('id_AuditorName').focus();
					return false;
				}
				url=url+"&BillNo=-1";
				url = url+ "&BillCat="+document.forms[0].cmbTCCtgry.value;
				window.open(url,"_blank","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,top=100,left=150,width=500,height=400"); 
			}
		}
*/		
/*		function forwardSubmit(url)
		{
			window.open(url);
			return true;
		}
*/		
/*		function showCtrl()
		{
			var indx = document.forms[0].cmbTCCtgry.options[document.forms[0].cmbTCCtgry.selectedIndex].text;
			if(indx=='<%--=lStrTCBill--%>')
			{
				document.getElementById('divTC').style.display='block';					
			}
			else
			{					
				document.getElementById('divTC').style.display='none';
				document.getElementById('id_txtPrevBillNo').value='';
				document.getElementById('id_txtChallanNo').value='';
				document.getElementById('id_txtChallanMjrHead').value='';
			}
		}
*/			
/*		function saveNew()
		{
		 	if(savevalid()==true)
		 	{
				document.forms[0].actionFlag.value='insInwPhyBills';
				document.forms[0].actionBtn.value='SaveNew';
				document.forms[0].action ='ifms.htm';
				document.forms[0].submit();
			}
		}
*/
		function showAttach()
		{
			url="ifms.htm?actionFlag=openAttachments&parentForm="+document.forms[0].name;
			window.open(url,"_blank","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,top=110,left=20,width=960,height=400"); 
		}
				
		function savevalid()
		{
			var Flag="true";
			var lHeadStatus = document.getElementById("id_headStatus").value;
			var	amt=document.getElementById('id_txtGrossAmt').value;
			var billCateg = document.getElementById('id_cmbTCCtgry').options[document.frmCntrInwPhyBills.cmbTCCtgry.selectedIndex].text;
			if(chkDate(document.frmCntrInwPhyBills.txtBillDate, "Bill Date")==false)
			{
				return false;
			}			
			if(check999('<%=lStrNil%>')==false)
			{
				return false;
			}
				
			if((amt<=0 && billCateg=='<%=lStrRegular%>'))
			{
				alert('Gross Amount cannot be Negative or Zero.');
				document.getElementById('id_txtGrossAmt').focus();
				return false;
			}
			amt=document.getElementById('id_txtNetAmt').value;
			if((amt<=0 && billCateg=='<%=lStrRegular%>'))
			{
				alert('Net Amount cannot be Negative or Zero');
				document.getElementById('id_txtNetAmt').focus();
				return false;
			}			
			if(basicBillValidation()==false)
				return false;
			if(validateRemarks()==false)
				return false;
			if(billCateg=='<%=lStrTCBill%>' && checkChallanDetails()==false)
			{
				return false;
			}
			if(billCateg=='<%=lStrNil%>')
			{
				if(document.getElementById("id_txtGrossAmt").value!='0' || document.getElementById("id_txtNetAmt").value!='0')
				{
					var confirmStatus = confirm('This Nil Bill does not contain Zero Amount.\nDo you still want to continue?');
					if(confirmStatus==false)
					{
						document.getElementById("id_txtGrossAmt").focus();
						return false;
					}
				}
			}
			if(validateForm_frmCntrInwPhyBills())
			{			
				return true;
			}
		}
		function showSave()
		{
			if(savevalid()==true)
			{
				document.frmCntrInwPhyBills.actionBtn.value = 'Save';
				saveBillUsingAjax();					
			}
		}
		function saveBillUsingAjax()
		{
			disable();
			document.frmCntrInwPhyBills.actionFlag.value='insInwPhyBills';							
			showProgressbar();				
			window.setTimeout("HandleBillAjaxSave()",1000);
		}
		function billstateChanged() 
		{ 
			if (xmlHttp.readyState==complete_state)
			{ 
				hide_img();
				enable_div();
				hideProgressbar();
				enable();
			    if(document.frmCntrInwPhyBills.actionBtn.value!='SaveNew')
				{
					var state = xmlHttp.responseText;
					if(state.search('Success') >-1 )
					{
						var newURL = '<%=request.getQueryString()%>';
						var contextPath = '<%=request.getContextPath()%>';
						newURL = 'ifms.htm?' +newURL;
						document.frmCntrInwPhyBills.actionFlag.value='cntrInwPhyBills';
						document.frmCntrInwPhyBills.submit();
					}
				}
			}
		}				
	</script>
	</head>
	
	<c:set var="resultObj" value="${result}" > </c:set>		
	<c:set var="resValue" value="${resultObj.resultValue}"> </c:set>
	
	<c:set var="newBillVO" value="${resValue.NewBillVO}"></c:set>
	<body>		
		<hdiits:form method="post" name="frmCntrInwPhyBills" id="id_frmCntrInwPhyBills" validate="true" encType="multipart/form-data">		
    	<div id="statusBar" style="width:50%; background-color: #BADCFD ;display:none;font: bold 10px Arial"></div>		
		<div id="progressImage" style="display:none"></div>		

		<input type="hidden" name="parentForm" value="frmCntrInwPhyBills"/>
		<input type="hidden" name="toPost" value="-1">
		<input type="hidden" name="toUser" value="-1">
		<input type="hidden" name="deptId" value="" id="id_deptId"/>
		<input type="hidden" name="langId" value="en_US"/>
		<input type="hidden" name="SendTo" value="-1">
		<input type="hidden" name="rowCount" value ="0"/>
		<input type="hidden" name="challanTotal" value="0"/>
		<hdiits:hidden name="actionFlag" id="actionFlag" />
		
		<table align="center" width="100%" border="0">			
			<tr><td><br></td></tr>
			<tr><td ><br></td></tr>
					
			<tr>
				<td colspan="4" align="center" class="Label4">
					<b>
						<fmt:message key="CNTR.IN_PHY_BILLS" bundle="${billprocLabels}"></fmt:message>
					</b>
				</td>
			</tr>	
			<tr><td><br></td></tr>						
			<tr>
				<td align="center" colspan="4">
				<fieldset  style="width:100%" class="tabstyle">
					<legend id="headingMsg">	
						<b>
							<fmt:message key="CNTR.DDO_INFO" bundle="${billprocLabels}"></fmt:message>
						</b>
					</legend>
					<table width="100%" align="center"  border="0" cellspacing="1" cellpadding="1">
						<tr><td colspan="4">&nbsp;</td></tr>
						<tr>
							<td align="left" nowrap="true" class="Label" width="7%"> 
								&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="CMN.CARDEX_NO" bundle="${billprocLabels}"></fmt:message>										
							</td>
							<td align="left" width="30%">
						 		:&nbsp;<input type="text" id="id_CardexNo" class="texttag mandatorycontrol" 
						 					name="txtCardexNo" validation="txt.isrequired,txt.isnumber" 
						 					value="${newBillVO.cardexNo}" tabindex="1" onfocus="javascript:showHighLight(this)"/>							    
								&nbsp;<font color="red">*</font>
							</td>
							<td align="left" class="Label" width="7%">
								<fmt:message key="CMN.DDO_NAME" bundle="${billprocLabels}"></fmt:message>
							</td>
							<td align="left" width="46%">
								:&nbsp;<input type="text" name="txtDDOName" id="DDOName" size="37" readonly="true" caption="DDO Name" value="${newBillVO.ddoName}" maxlength="100"/>
							</td>						
						</tr>
						
						<tr>
							<td align="left" class="Label" width="20%">  
								&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="CNTR.DDO_NO" bundle="${billprocLabels}"></fmt:message>
							</td>
							<td align="left" width="25%">
								:&nbsp;<input type="text" id="DDONo" name="txtDDONo" size="20" readonly="true" caption="DDO No" value="${newBillVO.ddoNo}">
								<input type="hidden" name="DDOCode" value="${newBillVO.ddoCode}"/>
							</td>
							<td align="left" class="Label" width="20%">
								<fmt:message key="CNTR.DEPARTMENT" bundle="${billprocLabels}"></fmt:message>							
							</td>
							<td align="left" width="25%">
								:&nbsp;<select name="txtDepartment" id="DDODept" tabindex="3" style="width:92%" onfocus="javascript:showHighLight(this)" onblur="hideHighLight(this)">
											<c:forEach var="dept" items="${resValue.dept}">
												<option selected="true" value="<c:out value="${dept.commonId}"/>">
													<c:out value="${dept.commonDesc}" />
												</option>
											</c:forEach>
								</select>
								<script type="text/javascript">
									document.getElementById("DDODept").value='${newBillVO.deptId}';
								</script>
							</td>						
						</tr>
					</table>
					</fieldset>
				</td>
			</tr>
				
			<tr><td colspan="4"> &nbsp;	</td></tr>
			
			<tr>
				<td align="center" colspan="4">
				<fieldset  style="width:100%" class="tabstyle">
					<legend  id="headingMsg">	
						<b>
							<fmt:message key="CNTR.BILL_INFO" bundle="${billprocLabels}"></fmt:message>
						</b>
					</legend>
					<table width="100%" align="center" border="0" cellspacing="0" cellpadding="1">
						<tr><td colspan="4">&nbsp;</td></tr>
						<tr>
							<td align="left" class="Label" width="7%">
								&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="CMN.BILL_CONTROL_NO" bundle="${billprocLabels}"></fmt:message>
							</td>
							<td align="left" width="30%">
								:&nbsp;<input type="text" id="id_BillControlNo" class="texttag mandatorycontrol" 
											name="txtBillControlNo" readonly="true" value="<%=billCntrlNum%>" />	
								&nbsp;<font color="red">*</font>
							</td>
							<td align="left" class="Label" width="7%"> 
								<fmt:message key="CMN.TOKEN_NO" bundle="${billprocLabels}"></fmt:message>
							</td>
							<td align="left" width="46%"> 
								:&nbsp;<input type="text" name="txtTokenNo" class="texttag mandatorycontrol" 
											id="id_txtTokenNo" tabindex="4" maxlength="5"
											value="${newBillVO.tokenNum}" onfocus="javascript:showHighLight(this)" 
											onblur="hideHighLight(this)"/>
								&nbsp;<font color="red">*</font>
							</td>
						</tr>

						<tr>
							<td align="left" class="Label" width="20%">							
								&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="CMN.BILL_DATE" bundle="${billprocLabels}"></fmt:message>							
							</td>
							<td align="left" width="25%">										
								<%
								String dateTime = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
								 request.setAttribute("dateTime",dateTime);
																
								%>								
							:&nbsp;<input type="text" name="txtBillDate" id="txtBillDate" value="<%=dateTime%>" 
									maxlength="10" class="texttag mandatorycontrol"  tabindex="5" 
									onfocus="javascript:showHighLight(this)" onblur="hideHighLight(this)"/>
								<img src='images/CalendarImages/ico-calendar.gif' width='20' onClick='window_open("txtBillDate",375,570)' />
								&nbsp;<font color="red">*</font>
							</td>									
						</tr>

						<tr>
							<td align="left" class="Label" width="20%">
								&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="CNTR.GROSS_AMOUNT" bundle="${billprocLabels}"></fmt:message>										
							</td>
							<td align="left" width="25%">
								:&nbsp;<input type="text" name="txtGrossAmt" id="id_txtGrossAmt" 
											class="texttag mandatorycontrol" validation="txt.isrequired,txt.isflt" 
											caption="Gross Amount" value="${newBillVO.billGrossAmount}" 
											onblur="javascript:reflectAmount(this, 'Gross Amount',17);" tabindex="6" 
											maxlength="17" onkeypress="javascript:AmountFormat()"
											onfocus="javascript:showHighLight(this)" 
										/>
								&nbsp;<font color="red">*</font>
							</td>
							<td align="left" class="Label" width="20%"> 
								<fmt:message key="CNTR.NET_AMOUNT" bundle="${billprocLabels}"></fmt:message>										
							</td>	
							<td align="left" width="25%">
								:&nbsp;<input type="text" name="txtNetAmt" id="id_txtNetAmt" 
										class="texttag mandatorycontrol" value="${newBillVO.billNetAmount}" 
										tabindex="7" maxlength="17" onkeypress="javascript:AmountFormat()" 
										onblur="javascript:chkAmtLength(this,'Net Amount',17);"
										onfocus="javascript:showHighLight(this)"
									/>
								&nbsp;<font color="red">*</font>
							</td>
						</tr>

						<tr>									
							<td align="left" class="Label" width="20%">
								&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="CNTR.GO/NGO" bundle="${billprocLabels}"></fmt:message>
							</td>
							<td align="left" width="25%">
								:&nbsp;<select name="cmbGONGO" id="id_GoNgo" caption="GO/NGO" tabindex="8" style="width:56%">
										<option value="">--Select--</option>																								
								<c:forEach var="goNgo" items="${resValue.goNgoList}" >
									 <option value='<c:out value="${goNgo.lookupName}"/>'>
										 	<c:out  value="${goNgo.lookupDesc}"/>
										 </option>
								 </c:forEach>
							</select>

							<script type="text/javascript">
									document.getElementById("id_GoNgo").value='${newBillVO.goNgo}';
							</script>
							</td>
							<td align="left" class="Label" width="20%">
								<fmt:message key="CMN.BILL_CATEGORY" bundle="${billprocLabels}"></fmt:message>									
							</td>
							<td align="left" width="25%">		
							<%
								List tcBillList = (List)map.get("TcBillList");
								CmnLookupMst lObjCmnLookupMst = (CmnLookupMst)tcBillList.get(0);
								System.out.println(")))))))))))) Value of TCCTgry : " +lObjCmnLookupMst.getLookupDesc());							
							%>

							:&nbsp;<select name="cmbTCCtgry" onchange="javascript:showCtrl('<%=lStrTCBill%>');" id="id_cmbTCCtgry" caption="Bill Category" validation="sel.isrequired"  tabindex="9" style="width:56%">
										<option value="">--Select--</option>									
									<c:forEach var="tcBill" items="${resValue.TcBillList}" >
											<option value='<c:out value="${tcBill.lookupName}"/>'>
 										 	<c:out  value="${tcBill.lookupDesc}"/>
											 </option>
									 </c:forEach>
								</select>&nbsp;<font color="red">*</font>
								<script type="text/javascript">
									if('${newBillVO.tcBill}'!='')
										document.getElementById("id_cmbTCCtgry").value='${newBillVO.tcBill}';
									else
									{
										var indxName = document.frmCntrInwPhyBills.cmbTCCtgry.options[document.frmCntrInwPhyBills.cmbTCCtgry.selectedIndex].text;
										var indx = document.frmCntrInwPhyBills.cmbTCCtgry.options[document.frmCntrInwPhyBills.cmbTCCtgry.selectedIndex].value;
										var x=document.getElementById("id_cmbTCCtgry");
									    for(i=x.length-1;i>=0;i--)
									    {
	  											var indxName = document.frmCntrInwPhyBills.cmbTCCtgry.options[i].text;
											var indx = document.frmCntrInwPhyBills.cmbTCCtgry.options[i].value;
											if(indxName=='<%=lStrRegular%>')
											{
												document.getElementById("id_cmbTCCtgry").value = indx;
											}
										}
									}							
								</script>
							</td>
						</tr>

						<tr height="30px">
							<td align="left" class="Label" width="20%"> 
								&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="CMN.BILL_TYPE" bundle="${billprocLabels}"></fmt:message>
							</td>
							<td align="left" colspan="3"> 
<!-- 	changes for bill type textbox : Hiral -->							
								:&nbsp;<input type="text" name="cmbBillType" id="id_BillType" tabindex="10" 
									value="${newBillVO.billType}" onblur="javascript:convertToUpper(this);"
									onfocus="showHighLight(this);" />
<!--  Ends :  changes for bill type textbox : Hiral -->																
<!-- 								:&nbsp;<select name="cmbBillType" id="id_BillType" caption="Bill Type" validation="sel.isrequired" style="width:61%" tabindex="10">
										<option value="">---Select---</option>								
										<c:forEach var="billType" items="${resValue.BillType}">
											<option value='<c:out value="${billType[0]}"/>'>
										<c:out value="${billType[2]}(${billType[1]})"/>	
											</option>
										</c:forEach>
 -->										
									</select>&nbsp;<font color="red">*</font>	&nbsp;&nbsp;&nbsp;&nbsp;
								<script type="text/javascript">
//									document.getElementById("id_BillType").value='${newBillVO.billType}';
								</script>
							</td>
						</tr>			
						
						<tr>
							<td align="left" class="Label" width="20%">
								&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="CNTR.EXEMPTED" bundle="${billprocLabels}"></fmt:message> ?
							</td>
							<td align="left" width="25%">
								<input type="radio" name="radExempted" value="Y" id="radExempted" onclick="javascript:showExempt(this);" checked="true" tabindex="12" />
								<fmt:message key="CMN.YES" bundle="${billprocLabels}"></fmt:message> 
								<input type="radio" name="radExempted" value="N" id="radExempted" onclick="javascript:showExempt(this);" tabindex="12"/>
								<fmt:message key="CMN.NO" bundle="${billprocLabels}"></fmt:message> 
								<input type="hidden" id="exempted" /> 
							</td>		
							<td align="left" class="Label" width="20%">
								<fmt:message key="CNTR.ATTACH_SCANNED_BILL" bundle="${billprocLabels}"></fmt:message>
							</td>
							<td align="left" width="25%">:&nbsp;
							<hdiits:a href="#" onclick="javascript:showAttach();"  caption="">
								<hdiits:image source="common/images/CalendarImages/doclink.gif" tabindex="13"/>
							</hdiits:a>
							</td>
						</tr>	
						
						<tr>				
							<td colspan="4">
								<table id="divExempted" align="center" border="0" width="100%">
									<tr>
										<td align="left" class="Label" width="20%"> 
											 &nbsp;&nbsp;&nbsp;<fmt:message key="CMN.BILL_CODE" bundle="${billprocLabels}"></fmt:message>											
										</td>
										<td align="left" class="Label" width="*">
											:&nbsp;<select name="cmbBillCode" id="id_cmbBillCode" style="width:61%" tabindex="14">
											<option value="">--Select--</option>													
											<c:forEach var="billcode" items="${resValue.BillCodeList}" >
												 <option value='<c:out value="${billcode.lookupName}"/>'>
										 				<c:out value="${billcode.lookupShortName}" /> - <c:out  value="${billcode.lookupDesc}"/>
	 											 </option>
											 </c:forEach>
											</select>&nbsp;<font color="red">*</font>
											<script language="javascript">												
												<% if(newbillVO!=null) {%>
												document.getElementById("exempted").value='${newBillVO.exempted}';
												if(document.getElementById("exempted").value=='Y')
												{
													document.forms[0].radExempted[0].checked="checked";
													document.getElementById('divExempted').style.display='block';
													document.getElementById("id_cmbBillCode").value='${newBillVO.billCode}';
												}
												else
												{
													document.forms[0].radExempted[1].checked="checked";
													document.getElementById('divExempted').style.display='none';
												}
												<%}%>
											</script>
										 </td>
									</tr>									
								</table>										
							</td>
							</tr>
							<tr>
								<td align="left" class="Label" width="7%">
									&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="CNTR.PREVIOUS_BILL_NO" bundle="${billprocLabels}"></fmt:message>
								</td>
								<td align="left" width="30%">
									:&nbsp;<input type="text" id="id_txtPrevBillNo" name="txtPrevBillNo" size="20" 
												value="${newBillVO.prevBillNo}" tabindex="15"
												onfocus="javascript:showHighLight(this)" onblur="hideHighLight(this)" />
								</td>
								<td></td>
								<td></td>
							</tr>	
						</table>
						</fieldset>
					</td>
				</tr>
					
				<tr><td colspan="4">&nbsp;</td></tr>
				<tr><td colspan="4">
				<div id="divTC">
				<fieldset  style="width:100%" class="tabstyle">
					<legend  id="headingMsg">	
						<b>
							<fmt:message key="CNTR.TC_INFO" bundle="${billprocLabels}"></fmt:message>
						</b>
					</legend>
					<table align="center" width="100%" border="0" cellpadding="1" cellspacing="0" id="TCTable">
						<tr>
							<td align="center" class="datatableheader">
								<fmt:message key="CNTR.CHALLAN_NO" bundle="${billprocLabels}"></fmt:message>
							</td>
							<td align="center" class="datatableheader">
								<fmt:message key="CNTR.CHALLAN_DATE" bundle="${billprocLabels}"></fmt:message>
							</td>
							<td align="center" class="datatableheader">
								<fmt:message key="CNTR.CHALLAN_MAJORHEAD" bundle="${billprocLabels}"></fmt:message>
							</td>
							<td align="center" class="datatableheader">
								<fmt:message key="CNTR.CHALLAN_AMT" bundle="${billprocLabels}"></fmt:message>
							</td>
						</tr>
						<tr>
							<td align="center">	
								<input type="text" id="id_txtChallanNo" name="txtChallanNo1" size="20" tabindex="16"
									 attrTitle="Challan No" onfocus="javascript:showHighLight(this)" onblur="hideHighLight(this)"/>
							</td>
							<td align="center">
								<input type="text" name="txtChallanDate1" maxlength="10" size="20" tabindex="17" 
									attrTitle="Challan Date" onfocus="javascript:showHighLight(this)" onblur="hideHighLight(this)"/>
								<img src='images/CalendarImages/ico-calendar.gif' width='20' onClick='window_open("txtChallanDate1",375,570)' >
							</td>
							<td align="center"> 
								<input type="text" name="txtChallanMjrHead1" attrTitle="Challan Major Head" 
									class="texttag mandatorycontrol" id="id_txtChallanMjrHead" size="20" 									
									maxlength="4" tabindex="18" onblur="javascript:chkHeadLength(this,4)"
									onfocus="javascript:showHighLight(this)"/>
								&nbsp;<font color="red">*</font>
							</td>
							<td align="center"> 
								<input type="text" id="id_txtChallanAmt" attrTitle="Challan Amount" class="texttag mandatorycontrol" onkeypress="javascript:AmountFormat()" name="txtChallanAmt1" 
									maxlength="17" tabindex="19" size="20" onblur="getTotal(this);"
									onfocus="javascript:showHighLight(this)" 
								/>
								&nbsp;<font color="red">*</font>
							</td>							
						</tr>
						<%							
							String lStrTotalAmt = "";
							double ldTotalAmt = 0;
							System.out.println("Value of Nwe Bill VO : " +newbillVO);							
							if(newbillVO!=null && newbillVO.getTcBill().equals(lStrTCBill))
							{
								System.out.println("Value of TC Bill from New Bill VO : " +newbillVO.getTcBill());
								System.out.println("List from ShowBillVO : " +newbillVO);
								List lListRcptDtls = (List)newbillVO.getListReceipt();
								System.out.println("List from ShowBillVO : " +lListRcptDtls.size());
								TrnReceiptDetails lListRcptVO1 = (TrnReceiptDetails)lListRcptDtls.get(0);
								
								Date lDtChallan = null;
								String SearchDate ="";
								ldTotalAmt = ldTotalAmt + lListRcptVO1.getAmount().doubleValue();
								
								String lStrRcptNo = lListRcptVO1.getReceiptNo()!=null ? lListRcptVO1.getReceiptNo():"";
								String lStrRcptMjrHd = lListRcptVO1.getMajorHead()!=null ? lListRcptVO1.getMajorHead():"";
								String lStrRcptAmt = lListRcptVO1.getAmount()!=null ? String.valueOf(lListRcptVO1.getAmount()):"";
								Date lStrRcptDt = lListRcptVO1.getReceiptDate()!=null ? lListRcptVO1.getReceiptDate():null;
								
								try
								{
									if(lStrRcptDt!=null)
//										lDtChallan = sdf.parse(lStrRcptDt);
										SearchDate=new SimpleDateFormat("dd/MM/yyyy").format(lStrRcptDt);
								}
								catch(Exception e)
								{
									e.printStackTrace();
								}
								
								System.out.println("Value of RcptNo : " +lStrRcptNo);
								System.out.println("Value of lStrRcptMjrHd : " +lStrRcptMjrHd);
								System.out.println("Value of lStrRcptAmt : " +lStrRcptAmt);
								System.out.println("Value of lStrRcptDt : " +SearchDate);
						%>
							<script>
								document.forms[0].txtChallanNo1.value = '<%=lStrRcptNo%>';
								document.forms[0].txtChallanDate1.value = '<%=SearchDate%>';
								document.forms[0].txtChallanMjrHead1.value = '<%=lStrRcptMjrHd%>';
								document.forms[0].txtChallanAmt1.value = '<%=lStrRcptAmt%>';
								document.forms[0].rowCount.value='<%=lListRcptDtls.size()%>';
							</script>
						<%		
						int i=0;
						
						for(i=1;i<lListRcptDtls.size();i++)
						{
							TrnReceiptDetails lObjTrnRcptDtsl = (TrnReceiptDetails)lListRcptDtls.get(i);

							ldTotalAmt = ldTotalAmt +lObjTrnRcptDtsl.getAmount().doubleValue();
							
							lStrRcptNo = lObjTrnRcptDtsl.getReceiptNo()!=null ? lObjTrnRcptDtsl.getReceiptNo():"";
							lStrRcptMjrHd = lObjTrnRcptDtsl.getMajorHead()!=null ? lObjTrnRcptDtsl.getMajorHead():"";
							lStrRcptAmt = lObjTrnRcptDtsl.getAmount()!=null ? String.valueOf(lObjTrnRcptDtsl.getAmount()):"";
							lStrRcptDt = lObjTrnRcptDtsl.getReceiptDate()!=null ? lObjTrnRcptDtsl.getReceiptDate():null;
							
							try
							{
								if(lStrRcptDt!=null)
//									lDtChallan = sdf.parse(lStrRcptDt);
									SearchDate=new SimpleDateFormat("dd/MM/yyyy").format(lStrRcptDt);
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
							
							try
							{
								if(lStrRcptDt!=null)
									SearchDate=new SimpleDateFormat("dd/MM/yyyy").format(lStrRcptDt);
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
						%>	
						<tr>		
							<td align="center">	
								<input type="text" id="id_txtChallanNo" name="txtChallanNo<%=(i+1)%>" size="20" 
									value="<%=lStrRcptNo%>" tabindex="18"/>	
							</td>
							<td align="center">	
								<input type="text" name="txtChallanDate<%=(i+1)%>" maxlength="10" size="20" 
									tabindex="17" attrTitle="Challan Date" value="<%=SearchDate%>"
									onfocus="javascript:showHighLight(this)" onblur="hideHighLight(this)"
								/>
								<img src='images/CalendarImages/ico-calendar.gif' width='20' onClick='window_open("txtChallanDate",375,570)' >								
							</td>
							<td align="center">	
								<input type="text" name="txtChallanMjrHead<%=(i+1)%>" attrTitle="Challan Major Head" 
									class="texttag mandatorycontrol" id="id_txtChallanMjrHead" size="20" maxlength="4" 
									tabindex="18" value="<%=lStrRcptMjrHd %>" onfocus="javascript:showHighLight(this)"
									onblur="javascript:chkHeadLength(this,4)"/>
								&nbsp;<font color="red">*</font>								
							</td>
							<td align="center"> 
								<input type="text" id="id_txtChallanAmt" attrTitle="Challan Amount" 
									class="texttag mandatorycontrol" onkeypress="javascript:AmountFormat()" 
									name="txtChallanAmt<%=(i+1)%>" maxlength="17" tabindex="19" size="20" 
									onblur="getTotal(this);" value="<%=lStrRcptAmt%>"
									onfocus="javascript:showHighLight(this)"/>
								&nbsp;<font color="red">*</font>
								<img src=images/CalendarImages/DeleteIcon.gif onclick="DeleteThis(this,'TCTable')" />									
							</td>		
						</tr>
						<%		}
								DecimalFormat dFormat=new DecimalFormat();
								dFormat.applyPattern("#0.00");
								lStrTotalAmt=dFormat.format(ldTotalAmt);
//								lStrTotalAmt = BigDecimal.valueOf(ldTotalAmt).toPlainString();
							}
							else
							{
								lStrTotalAmt = "0";
							}
							System.out.println("Value of Total Challan Amount : " +lStrTotalAmt);
						%>
					</table>
					<table align="center" width="100%" border="0">
						<tr>
							<td align="left" width="25%">&nbsp;</td>
							<td align="left" width="25%">&nbsp;</td>
							<td align="right" width="25%"><fmt:message key="CMN.ChallanTotal" bundle="${billprocLabels}"></fmt:message>:&nbsp;
							</td>
							<td align="left" width="*">
							<td align="left" class="even" id="challanAmtTotal">
								<fmt:formatNumber pattern=".00" type="number" value="0"></fmt:formatNumber>							
							<script>
								document.getElementById("challanAmtTotal").innerHTML = '<%=lStrTotalAmt%>';
								document.forms[0].challanTotal.value = '<%=lStrTotalAmt%>';
							</script>
							</td>
							
						</tr>
						<tr>
							<td align="center" colspan="4">
								<hdiits:button name="btnAddRow" value="Add Row" type="button" onclick="AddRowToTable()"/>
							</td>
						</tr>
					</table>
					</fieldset>
					</div>
				</td>
			</tr>
			<tr><td colspan="4">&nbsp;</td></tr>
			<tr height="20">	
				<td align="left" colspan="4">
				<fieldset  style="width:100%" class="tabstyle">
					<legend  id="headingMsg">	
						<b>
							<fmt:message key="CNTR.BUDGET_INFO" bundle="${billprocLabels}"></fmt:message>
						</b>
					</legend>
					<table width="100%" align="center" border="0" cellspacing="0" cellpadding="1">
						<tr>							
							<td align="left" width="7%" class="Label">
								&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="BUD.TYPE_CODE" bundle="${billprocLabels}"></fmt:message>
							</td>	
							<td width="30%" align="left">
								:&nbsp;<select name="cmbPlan" id="id_cmbPlan" tabindex="19" onchange="setText(this)" style="width:56%">
								<option selected="selected" value=""> --Select-- </option>
								<script type="text/javascript">
									lookupArray[0] = "";
								</script>	
																								
								<c:forEach var="BudType" items="${resValue.planList}" varStatus="No">
								<c:choose>
								<c:when test='${resValue.Selected_BudType != null && BudType.lookupName == resValue.Selected_BudType.Value}' >
									<option selected="selected" value='${BudType.lookupName}'><c:out value="${BudType.lookupShortName}" /></option>								
								</c:when>								
								<c:otherwise>
									<option value='${BudType.lookupName}'> <c:out value="${BudType.lookupShortName}" /> </option>
								</c:otherwise>
								</c:choose>
									<script type="text/javascript">
										lookupArray[${No.count}] = "${BudType.lookupDesc}";
									</script>							
								</c:forEach>																	
								</select>
								<font color="red">*</font>
								<script type="text/javascript">
									document.getElementById("id_cmbPlan").value='${newBillVO.budType}'
							  </script>
							</td>
							<td align="left" width="20%" class="Label">
								<fmt:message key="BUD.TYPE_DESC" bundle="${billprocLabels}"></fmt:message>
							</td>	
							<td width="*" align="left">
<!-- 								:&nbsp;<input type="text" name="txtBudDesc" size="35" readonly="readonly" value="${newBillVO.budTypeDesc}">					 -->
									:&nbsp;<textarea name="txtBudDesc" rows="3" cols="17" readonly="readonly"><c:out value="${newBillVO.budTypeDesc}" /></textarea>
							</td>						
						</tr>
						
						<tr>
							<td align="left" class="Label">
								&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="CNTR.ENTRY" bundle="${billprocLabels}"></fmt:message>?
							</td>
							<td align="left">
								<input type="radio" name="radEntry" value="ddo" id="id_radEntry" onclick="" tabindex="20" checked="true"/>
								<fmt:message key="CMN.USER" bundle="${billprocLabels}"></fmt:message>								
								&nbsp;&nbsp;&nbsp;<input type="radio" name="radEntry" value="user" id="id_radEntry" onclick="javascript:openDdoHeadPopup();" tabindex="20"/>
								<fmt:message key="CMN.Ddo" bundle="${billprocLabels}"></fmt:message> 
							</td>
							<td width="20%" class="Label" align="left">
								<fmt:message key="CNTR.SCHEME_CODE" bundle="${billprocLabels}"></fmt:message>
							</td>	
							<td width="25%" align="left">
								:&nbsp;<input type="text" name="txtSchemeCode" size="20" id="id_txtSchemeCode" 
										value="${newBillVO.schemeCode}" onblur="getDDOHeadBySchemeNo('${pageContext.request.contextPath}')" 
										tabindex="21" onfocus="javascript:showHighLight(this)">
								<input type="hidden" name="headStatus" id="id_headStatus" value="" />
							</td>
						</tr>
						
						<tr>
							<td width="7%" align="left" class="Label">
								&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="CMN.DEMAND_NO" bundle="${billprocLabels}"></fmt:message>
							</td>
							<td align="left" width="30%">
								:&nbsp;<input type="text" class="texttag mandatorycontrol" name="cmbDemand" 
										id="id_cmbDemand" value="${newBillVO.dmndNo}" tabindex="22" maxlength="3" 
										onblur="javascript:chkHeadLength(this,3)" onkeypress="javascript:AmountFormat()"
										onchange="javascript:cleanHeadDtls(this);" onfocus="javascript:showHighLight(this)"
										/>
								&nbsp;<font color="red">*</font>
							</td>
							<td align="left" width="7%" class="Label">
								<fmt:message key="CMN.MAJOR_HEAD" bundle="${billprocLabels}"></fmt:message>						
							</td>
							<td align="left" width="46%">
								:&nbsp;<input type="text" name="cmbMajorHead" class="texttag mandatorycontrol" 
										id="id_cmbMajorHead" value="${newBillVO.budmjrHd}" tabindex="23" maxlength="4"
										onkeypress="javascript:AmountFormat()" onfocus="javascript:showHighLight(this)"							
										/>
								&nbsp;<font color="red">*</font>
							</td>
						</tr>
					
						<tr>
							<td align="left" width="20%" class="Label">
								&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="CMN.SUB_MAJORHEAD" bundle="${billprocLabels}"></fmt:message>
							</td>
							<td align="left" width="25%">
								:&nbsp;<input type="text" name="cmbSubMajorHead" id="id_cmbSubMajorHead" size="20" 
										caption="Sub Major Head" validation="txt.isnumber" 
										value="${newBillVO.budSubMjrHd}" tabindex="24" maxlength="2" 
										onblur="javascript:chkHeadLength(this,2)" onkeypress="javascript:AmountFormat()"
										onchange="javascript:cleanHeadDtls(this);" onfocus="javascript:showHighLight(this)"
										/>
							</td>
							<td align="left" width="20%" class="Label">
								<fmt:message key="CMN.MINOR_HEAD" bundle="${billprocLabels}"></fmt:message>
							</td>
							<td align="left" width="25%">
								:&nbsp;<input type="text" name="cmbMinorHead" id="id_cmbMinorHead" size="20" 
										 value="${newBillVO.budMinHd}" tabindex="25" maxlength="3" 
										 onblur="javascript:chkHeadLength(this,3)" onkeypress="javascript:AmountFormat()"
										 onchange="javascript:cleanHeadDtls(this);" onfocus="javascript:showHighLight(this)"
										/>
							</td>								
						</tr>
					
						<tr>
							<td align="left" width="20%" class="Label">
								&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="CMN.SUB_HEAD" bundle="${billprocLabels}"></fmt:message>
							</td>
							<td align="left" width="25%">
								:&nbsp;<input type="text" name="cmbSubHead" id="id_cmbSubHead" size="20" 
										value="${newBillVO.budSubHd}" tabindex="26" maxlength="2" 
										onblur="javascript:getSchemeNoByDDOGrantHead(this,2,'${pageContext.request.contextPath}')"
										onkeypress="javascript:AmountFormat()"
										onchange="javascript:cleanHeadDtls(this);" 	onfocus="javascript:showHighLight(this)"									
									/>
							</td>
							<td align="left" width="20%" class="Label">
								<fmt:message key="CMN.DETAIL_HEAD" bundle="${billprocLabels}"></fmt:message>
							</td>
							<td align="left" width="25%">
								:&nbsp;<hdiits:text name="cmbDetailHead" id="id_cmbDetailHead" size="20" caption="Detail Head" validation="txt.isnumber" default="${newBillVO.budDetailHd}" tabindex="27" maxlength="2" onfocus="javascript:showHighLight(this)" onblur="hideHighLight(this)"/>
							</td>
						</tr>
						
						<tr>
							<td align="left" width="20%" class="Label">
								&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="CNTR.GRANT_AMOUNT" bundle="${billprocLabels}"></fmt:message>
							</td>	
							<td align="left" width="25%">
								:&nbsp;<input type="text" name="txtGrantAmt" size="20" id="id_txtGrantAmt" value="${newBillVO.grantAmt}" readonly="true">
							</td>
						</tr>
					</table>
					</fieldset>
				</td>
			</tr>			
		
			<tr>
				<td colspan="4" align="left"> &nbsp;
				</td>
			</tr>
			
			<tr>
				<td align="left" colspan="4">
				<fieldset  style="width:100%" class="tabstyle">
					<legend  id="headingMsg">	
						<b>
							<fmt:message key="CNTR.TREASURY_INFO" bundle="${billprocLabels}"></fmt:message>
						</b>
					</legend>
					<table width="100%" align="center" border="0" cellspacing="0" cellpadding="1">
						<tr><td>&nbsp;</td></tr>
						<tr>
							<td align="left" width="20%" class="Label2">
								&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="CMN.AUDITOR_NAME" bundle="${billprocLabels}"></fmt:message>
							</td>
							<td align="left" width="*">
							:&nbsp;<select name="cmbAuditorName" id="id_AuditorName" caption="Auditor Name" tabindex="28" style="width:49%">
								<option value="">---select----</option>
								<c:choose>
									<c:when test="${newBillVO.auditorPostId==''}">
										</select> 
									</c:when>
									<c:otherwise>
										<c:forEach var="emp" items="${newBillVO.audList}">
											<% System.out.println("Inside auditor loop"); %>
											<option selected="true" value="<c:out value="${emp.postId}"/>">
												<c:out value="${emp.fullName}" />
											</option>
										</c:forEach>
										</select> 										
										<script type="text/javascript">
											document.getElementById("id_AuditorName").value='${newBillVO.auditorPostId}';
										</script>
									</c:otherwise>
								</c:choose>
								</td>
							</tr>
				
							<tr>
								<td align="left" align="20%" class="Label2">
									&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="CMN.REMARKS" bundle="${billprocLabels}"></fmt:message>
								</td>
								<td align="left" align="70%">
									:&nbsp;<hdiits:textarea name="txtareaRemarks" id="id_txtareaRemarks" rows="7" cols="60" default="${newBillVO.remarks}" tabindex="29" onfocus="javascript:showHighLight(this)" onblur="hideHighLight(this)">
									</hdiits:textarea>
								</td>							
							</tr>
						</table>
						</fieldset>
					</td>
				</tr>

				<tr><td>&nbsp;</td></tr>
				<tr><td>&nbsp;</td></tr>
				
				<tr>
					<td>
						<center>
							<hdiits:button name="btnSave" type="button" value='<%= bundle.getString("CMN.SAVE")%>' onclick="javascript:showSave()" tabindex="30"/>
 							<hdiits:button name="btnSaveAndNew" type="button" value='<%= bundle.getString("CNTR.SAVENEW")%>' onclick="saveNew();" tabindex="31"/> 
							<hdiits:button name="btnForward" type="button" value='<%= bundle.getString("CMN.FORWARD")%>' onclick="javascript:showForward('ifms.htm?actionFlag=getHyrUsers&statusFlag=BCRDX')" tabindex="29"></hdiits:button>
							<input type="hidden" name="actionBtn" value="" id="actionBtn"/>
<!-- 							<input type="hidden"  name="actionFlag" value="insInwPhyBills" id="actionFlag"/>	-->
					 	</center>
					</td>
				</tr>
				<tr><td colspan="2">&nbsp;</td></tr>
			</table>

		<script type="text/javascript" language="JavaScript">
			function emptyAuditor(){
				//alert('Inside postMsg');
				document.getElementById("id_AuditorName").options.length = 0;
				document.getElementById("id_AuditorName").options[0] = new Option("--Select--", "0");					
			}
			function postMsg()
			{
			}
			function postDept()
			{
		
			}
		</script>		
	
     <ajax:updateField 
     	baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getDDOData" 
	   	action="DDODept"  
	   	source="id_CardexNo" 
	   	target="DDONo,DDOName,DDOCode,id_BillControlNo" 
		parameters="CardexNo={id_CardexNo},dept=no" 
		postFunction="validateDDO" 
		errorFunction="validateDDO"
		eventType="focus">
	</ajax:updateField>

	<ajax:select 
		baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getDDOData" 
	   	source="id_CardexNo" 
	   	target="DDODept" 
		parameters="CardexNo={id_CardexNo},dept=yes" 
		eventType="blur" 
		errorFunction="validateDDO"
		postFunction="validateDDO">
	</ajax:select>

<!-- 	Parameters named changed -->	
	<ajax:select 
		baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=GONGOData" 
		source="id_GoNgo" 
		target="id_AuditorName" 
	    parameters="langId={langId},txtDepartment={txtDepartment},cmbMajorHead={cmbMajorHead},cmbGONGO={cmbGONGO},cmbBillType={cmbBillType},txtCardexNo={txtCardexNo}" 	  
		emptyFunction="emptyAuditor"  
		postFunction="postMsg">
	</ajax:select>
	<ajax:select 
		baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=GONGOData" 
		source="id_CardexNo" 
		target="id_AuditorName" 
		parameters="langId={langId},txtDepartment={txtDepartment},cmbMajorHead={cmbMajorHead},cmbGONGO={cmbGONGO},cmbBillType={cmbBillType},txtCardexNo={txtCardexNo}" 	  
		emptyFunction="emptyAuditor"  
		postFunction="postMsg">
	</ajax:select>
	<ajax:select 
		baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=GONGOData" 
		source="id_BillType" 
		target="id_AuditorName" 
		parameters="langId={langId},txtDepartment={txtDepartment},cmbMajorHead={cmbMajorHead},cmbGONGO={cmbGONGO},cmbBillType={cmbBillType},txtCardexNo={txtCardexNo}" 	  
		emptyFunction="emptyAuditor" 
		postFunction="postMsg">
	</ajax:select>
	<ajax:select 
		baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=GONGOData" 
		source="DDODept" 
		target="id_AuditorName" 
		parameters="langId={langId},txtDepartment={txtDepartment},cmbMajorHead={cmbMajorHead},cmbGONGO={cmbGONGO},cmbBillType={cmbBillType},txtCardexNo={txtCardexNo}" 	  
		emptyFunction="emptyAuditor"  
		postFunction="postMsg">
	</ajax:select>
<!-- 	Parameters named changed -->	
	
	<% System.out.println("StatusMessage from Map : " +map.get("StatusMsg")); %>
	<%
		if(map.get("StatusMsg") != null )
		{
			%>
				<script>
				     alert('<%=map.get("StatusMsg")%>');
				     document.getElementById('id_txtTokenNo').focus();
				</script>
			<%
		}
	%>
	</hdiits:form>
	<hdiits:validate locale="<%=(String)session.getAttribute("locale")%>" />
	</body>
	

	<c:if test="${newBillVO.billCode==null}">
	<script language="javascript">
		if(document.frmCntrInwPhyBills.radExempted.value=='Y')
		{
			document.getElementById('divExempted').style.display='block';
		}
	</script>
	</c:if>		
	<script language="javascript">
/*		function AddRowToTable()
	  	{
		      var tbl = document.getElementById("TCTable");
		      var rowSize = tbl.rows.length;     
		      var row = tbl.insertRow(rowSize);       
		      document.frmCntrInwPhyBills.rowCount.value = rowSize;
	
		      var cellChallanNo = row.insertCell(-1);
		      cellChallanNo.align ="center";
		      cellChallanNo.innerHTML = '<input  class="TextCss" type="text" attrTitle="Challan No" name="txtChallanNo'+rowSize+'" size="20" onfocus="javascript:showHighLight(this)" onblur="hideHighLight(this)">';	      
	
		      var cellDate = row.insertCell(-1);
		      cellDate.align ="center";
		      cellDate.innerHTML = '<input  class="TextCss" type="text" attrTitle="Challan Date" name="txtChallanDate'+rowSize+'" size="20" maxlength="10" onfocus="javascript:showHighLight(this)" onblur="hideHighLight(this)"><img src=images/CalendarImages/ico-calendar.gif width=20 onClick=window_open("txtChallanDate'+rowSize+'",375,570) >';
	
		      var cellMjrHd = row.insertCell(-1);  
		      cellMjrHd.align ="center";
		      cellMjrHd.innerHTML = '<input class="texttag mandatorycontrol" type="text" attrTitle="Challan Major Head" name="txtChallanMjrHead'+rowSize+'" maxlength="4" size="20" class="texttag mandatorycontrol" onblur="javascript:chkHeadLength(this,4)" onfocus="javascript:showHighLight(this)">&nbsp;<font color="red">*</font>';
		      
		      var cellAmnt = row.insertCell(-1);
		      cellAmnt.align ="center";
		      cellAmnt.innerHTML = '<input type="text" attrTitle="Challan Amount" name="txtChallanAmt'+rowSize+'" maxlength="17" class="texttag mandatorycontrol" onkeypress="javascript:AmountFormat()"  size="20" onblur="getTotal(this)" onfocus="javascript:showHighLight(this)"/>&nbsp;<font color="red">*</font><img src=images/CalendarImages/DeleteIcon.gif onclick="DeleteThis(this,\'TCTable\')" />';
	  	}
		  function DeleteThis(obj,tblId)
		 {	   	 	 
		      var rowID = showRowCell(obj);            
		      var tbl = document.getElementById(tblId);    
		      tbl.deleteRow(rowID);  
		      getTotal(obj);
		 }
		 function showRowCell (element)
		 {
		    var cell, row;    
		    if (element.parentNode) 
		    {
		      do
		      cell = element.parentNode;
		      while (cell.tagName.toLowerCase() != 'td')
		      row = cell.parentNode;
		    }
		    else if (element.parentElement) 
		    {
		      do
		      cell= element.parentElement;
		      while (cell.tagName.toLowerCase() != 'td')
		      row = cell.parentElement;
		    }
		    return row.rowIndex;
		}	  
*/		
		var indx = document.forms[0].cmbTCCtgry.options[document.forms[0].cmbTCCtgry.selectedIndex].text;		
		if(indx=='<%=lStrTCBill%>')
		{
			document.getElementById('divTC').style.display='block';
		}
		else
		{
			document.getElementById('divTC').style.display='none';
		}
/*		function getTotal(ctrl)
		{
			hideHighLight(ctrl);
			var tbl = document.getElementById("TCTable");
	  	    var rowSize = tbl.rows.length;
	  	    var rowLen = document.frmCntrInwPhyBills.rowCount.value;
	  	    var total=0;
	  	    for(ii=1;ii<=(rowSize-1);ii++)
	  	    {	  	    	
    	  	    var amt = eval("document.forms[0].txtChallanAmt"+(ii));					    	  	    
    	  	    if(amt.value!='' && amt.value.length>0)
    	  	    {
	  	    		total = parseFloat(total) + parseFloat(amt.value);
	  	    	}
	  	    }
	  	    document.getElementById('challanAmtTotal').innerHTML = total;
	  	    document.forms[0].challanTotal.value=total;
		}
*/		
/*		function validateScheme()
		{
			var scheme = document.getElementById('id_txtSchemeCode').value;
			if(scheme=='null')
			{
				document.getElementById('id_txtSchemeCode').value = '';
			}
		}
		*/
/*		function getSchemeNoByDDOGrantHead(ctrl,num)
		{
			if(chkHeadLength(ctrl,num)==true)
			{
				req = createXMLHttpRequest();
				
				disable();
				showProgressbar();
				
				var baseUrl = "${pageContext.request.contextPath}/ifms.htm?actionFlag=getSchemeNoByDDOGrantHead";
				baseUrl += "&demandCode=" + document.getElementById("id_cmbDemand").value;
				baseUrl += "&majorHead=" + document.getElementById("id_cmbMajorHead").value;
				baseUrl += "&subMajorHead=" + document.getElementById("id_cmbSubMajorHead").value;
				baseUrl += "&minorHead=" + document.getElementById("id_cmbMinorHead").value;
				baseUrl += "&subHead=" + document.getElementById("id_cmbSubHead").value;
				
				req.open("post", baseUrl, false); 
				req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
				req.onreadystatechange = responseSchemeNoByDDOGrantHead; 
				req.send(baseUrl);
			}
		}
			
		function responseSchemeNoByDDOGrantHead()
		{
			if(req.readyState == 4)
			{ 
				if(req.status == 200)
				{
					hide_img();
					enable_div();
					hideProgressbar();
					enable();
					
					var XMLDoc = req.responseXML.documentElement;
					var XMLEntries = XMLDoc.getElementsByTagName("DDOGrantHead");
					
					if(!(XMLEntries[0].childNodes[0].text == "null" || XMLEntries[0].childNodes[0].text == ""))
					{
						document.getElementById("id_txtSchemeCode").value = XMLEntries[0].childNodes[0].text;
					}
					document.getElementById("id_cmbDetailHead").value = "";
				}
			}		
		}
*/		
/*		function getDDOHeadBySchemeNo()
		{
			req = createXMLHttpRequest();
			hideHighLight(document.getElementById("id_txtSchemeCode"));
			disable();
			showProgressbar();
			
			var baseUrl = "${pageContext.request.contextPath}/ifms.htm?actionFlag=getDDOHeadBySchemeNo";
			baseUrl += "&schemeNo=" + document.getElementById("id_txtSchemeCode").value;
			
			req.open("post", baseUrl, false); 
			req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			req.onreadystatechange = responseDDOHeadBySchemeNo; 
			req.send(baseUrl);
			if(document.getElementById("id_txtSchemeCode").value == '')
			{
				document.getElementById("id_cmbDemand").focus();
			}
		}
		function responseDDOHeadBySchemeNo()
		{
			if(req.readyState == 4)
			{ 
				if(req.status == 200)
				{
					hide_img();
					enable_div();
					hideProgressbar();
					enable();
					
					var XMLDoc = req.responseXML.documentElement;
					var XMLEntries = XMLDoc.getElementsByTagName("DDOGrantHead");
					document.getElementById("id_cmbDemand").value = XMLEntries[0].childNodes[0].text;
					document.getElementById("id_cmbMajorHead").value = XMLEntries[0].childNodes[1].text;
					document.getElementById("id_cmbSubMajorHead").value = XMLEntries[0].childNodes[2].text;
					document.getElementById("id_cmbMinorHead").value = XMLEntries[0].childNodes[3].text;
					document.getElementById("id_cmbSubHead").value = XMLEntries[0].childNodes[4].text;
					document.getElementById("id_cmbDetailHead").value = "";
					if(XMLEntries[0].childNodes[0].text=='')
						document.getElementById("id_cmbDemand").focus();
				}
			}
		}
*/		
	function clearMjrHdDtls()
	{
		document.getElementById("id_cmbSubMajorHead").value = "";
		document.getElementById("id_cmbMinorHead").value = "";
		document.getElementById("id_cmbSubHead").value = "";
		document.getElementById("id_cmbDetailHead").value = "";

		hideHighLight(document.getElementById("id_cmbMajorHead"));
	}

	</script>
<!-- 	Parameters named changed -->	
	  <ajax:select 
	  baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=GONGOData"
	  source="cmbMajorHead"
	  target="cmbAuditorName"
	  parameters="cmbDemand={cmbDemand},cmbMajorHead={cmbMajorHead},langId={langId},txtDepartment={txtDepartment},cmbMajorHead={cmbMajorHead},cmbGONGO={cmbGONGO},cmbBillType={cmbBillType},txtCardexNo={txtCardexNo}" 	  
	  postFunction="clearMjrHdDtls"
	  errorFunction="clearMjrHdDtls"
	  eventType="blur"
	  />
<!-- Ends :	Parameters named changed -->		  
	 <ajax:updateField 
		  baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getGrantAmount"
		  action="cmbPlan"
		  source="cmbPlan"
		  target="id_txtGrantAmt,id_headStatus"
		  parameters="DDOCode={DDOCode},txtDepartment={txtDepartment},cmbDemand={cmbDemand},cmbMajorHead={cmbMajorHead},cmbSubMajorHead={cmbSubMajorHead},cmbMinorHead={cmbMinorHead},cmbSubHead={cmbSubHead},langId={langId},deptId={deptId},cmbPlan={cmbPlan}" 
		  postFunction="showText"
	  /> 
	 
	   <ajax:updateField 
		  baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getGrantAmount"
		  action="cmbDetailHead"
		  source="cmbDetailHead"
		  target="id_txtGrantAmt,id_headStatus"
		  parameters="DDOCode={DDOCode},txtDepartment={txtDepartment},cmbDemand={cmbDemand},cmbMajorHead={cmbMajorHead},cmbSubMajorHead={cmbSubMajorHead},cmbMinorHead={cmbMinorHead},cmbSubHead={cmbSubHead},langId={langId},deptId={deptId},cmbPlan={cmbPlan}" 
		  postFunction="showText"
		  errorFunction="showText"
		  eventType="blur"
	  /> 