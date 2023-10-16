
<%@ include file="../core/include.jsp"%>
<%@page	import="com.tcs.sgv.common.utils.DateUtility,com.tcs.sgv.common.valueobject.CmnLookupMst,java.util.List,java.util.Iterator"%>
<%@page import="com.tcs.sgv.core.valueobject.ResultObject,java.util.Map"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setBundle basename="resources.app.diary.DiaryLables"
	var="diaryLables" scope="request" />
<fmt:setBundle basename="resources.billproc.billproc_en_US"
	var="billprocLabels" scope="application" />
<fmt:setLocale value='<%=(String)session.getAttribute("locale")%>' />

<%@page import="com.tcs.sgv.billproc.common.valueobject.TrnShowBill"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
<%@page import="com.tcs.sgv.common.helper.SessionHelper"%>
<%@page import="com.tcs.sgv.acl.service.UserElements"%>
<%@page import="com.tcs.sgv.acl.login.valueobject.LoginDetails"%>
<%@page import="com.tcs.sgv.acl.dao.AclRoleElementRltDaoImpl"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.Date"%>
<%@page import="com.tcs.sgv.common.valueobject.TrnReceiptDetails"%>
<%@page import="org.apache.activemq.filter.SimpleDestinationFilter"%>

<%@page import="java.text.DecimalFormat"%>
<html>
<head>

<style>
.tabstyle {
	border-width: 5px 1px 1px 1px; 
	border-color: #2065c0;
	border-style: solid ;
	}
	
.legend {
	padding-left:5px;
	padding-right:5px;
	font-weight:normal; 
	font-family:verdana;
		
	border-width: 0px 0px 1px 0px; 
	border-color: #2065c0;
	border-style: solid ;
	}	
	
</style>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>


<script type="text/javascript"
	src="<c:url value="/script/common/newUserElements.js"/>"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script type="text/javascript"
	src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="script/common/tabcontent.js"/>"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="common/script/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/billproc/validation.js"></script>
<script src="script/common/prototype.js" language="JavaScript"
	type="text/javascript"></script>
<script>
			<%
				String  locale = (String)session.getAttribute("locale");
				Locale localee = new Locale(locale);
//				int audAmtFlag = 0;
				ResourceBundle bundle =ResourceBundle.getBundle("resources/billproc/billproc",localee);			
				System.out.println("Value of Locale from Session : " +session.getAttribute("locale"));
				ResourceBundle lObjRsrcBndle = ResourceBundle.getBundle("resources/billproc/BillprocConstants");
				String lStrTCBill = bundle.getString("CMN.TCBill");
				String lStrRegular = bundle.getString("CMN.RegularBill");
				String lStrNil = bundle.getString("CMN.NilBill");
				System.out.println("Regular Bill in Show Bill : " +lStrRegular);
				System.out.println("lStrTCBill Bill in Show Bill : " +lStrTCBill);
				System.out.println("lStrNil Bill in Show Bill : " +lStrNil);
				
				System.out.println("Value of Bill No in SHOW BILL  JSP : " +request.getParameter("BillNo"));
			 	String billNum = (String)request.getParameter("BillNo");
			 	ResultObject rs = (ResultObject) request.getAttribute("result");
			 	Map map = (Map)rs.getResultValue();
			 	TrnShowBill showVO = (TrnShowBill)map.get("ShowBillVO");
			 	
			 	System.out.println("Value of Approving Authority from Map request : " +map.get("ApprovingAuth"));
			 	String lStrApprvAuth=null;
			 	if(map.get("ApprovingAuth")!=null)
			 	{
					lStrApprvAuth = (String)map.get("ApprovingAuth");
					System.out.println("lStrApprvAuth : " +lStrApprvAuth);
			 	}
			 	System.out.println("StateMsg : " +map.get("StateMsg"));
			 	String BillInStat = lObjRsrcBndle.getString("STATUS.BillInward");
				String BillCrdxStat = lObjRsrcBndle.getString("STATUS.BillFwdCardex");
				System.out.println("Value of Bill Inwarded State : " +BillInStat);
				System.out.println("Value of Bill Inwarded State : " +BillCrdxStat );
				System.out.println("----- showVO.getStatus() " + showVO.getStatus());
				
				String lStrApproveState = lObjRsrcBndle.getString("STATUS.BillApproved");
				String lStrRejectState = lObjRsrcBndle.getString("STATUS.BillRejected");
				System.out.println("Outside Value of showVO.getStatus() : " +showVO.getStatus());
				System.out.println("Outside Value of lStrRejectState : " +lStrRejectState);				
				boolean flag = false;
				
				if(showVO.getStatus().equalsIgnoreCase(lStrApproveState) || showVO.getStatus().equalsIgnoreCase(lStrRejectState))
				{
					flag = true;
					System.out.println("Inside Value of showVO.getStatus() : " +showVO.getStatus());
					System.out.println("Inside Value of lStrApproveState : " +lStrApproveState);
				 } 
				int isChq = 0;
				if(request.getParameter("isChq")!=null)
				{
					isChq = Integer.parseInt((String)request.getParameter("isChq"));
				}
				System.out.println("Value of isCheque Flag is : " +isChq);
				System.out.println("Value of flag in scriptlet for hide/show : " +flag);
				UserElements userActions = (UserElements)map.get("homePageUserElements"); 	
				long permission = 0;
				long permission1 = 0;
				// permission is used for checking whether button is to be displayed or not.
				// permission = 1 is for showing and permission =2 is for hidden
				if(userActions!= null)
				{
	/*	To add elements(buttons) for Approve and Reject : Hiral  */
					if(userActions.getUserElements(120027)!= null)
					{
						if(userActions.getUserElements(120027).getPermissionObject() != null)
						{
							permission =userActions.getUserElements(120027).getPermissionObject().getPermission();
						}
					}
	/*	End : To add elements(buttons) for Approve and Reject : Hiral  */
					if(userActions.getUserElements(121060)!= null)
					{
						if(userActions.getUserElements(121060).getPermissionObject() != null)
						{
							permission = userActions.getUserElements(121060).getPermissionObject().getPermission();
						}
					}
					else
					{
						permission = 0;
					}
					
					System.out.println("userActions.getUserElements(121060) : " +userActions.getUserElements(121060));
					System.out.println("userActions.getUserElements(120027) : " +userActions.getUserElements(120027));
				}
				System.out.println("Value of user Actions : " +userActions);
				System.out.println("Permission is  : " +permission);
			%>
						
			var lookupArray = new Array();
			var audAmtFlag1;
			function setText(mThis)
		    {
		    	document.getElementById("txtBudDesc").value = lookupArray[mThis.selectedIndex];
			}
			function convertNum(ctrl)
			{
				hideHighLight(ctrl);			
				var strAmt = getAmountInWords(ctrl.value);
				document.form1.txtAdtAmtWords.value=strAmt;
				
			}
			function validateDDO()
			{
				var DDOno = document.getElementById('DDONo').value;
				var DDOname = document.getElementById('DDOName').value;
				if(DDOname=='null')
				{
					alert('Invalid Cardex Number.');
					document.getElementById('id_CardexNo').value='';
					document.getElementById('DDONo').value='';
					document.getElementById('DDOName').value='';
					document.getElementById('id_CardexNo').focus();
					return false;
				}
				return true;
			}
			
			function showAttach()
			{
				var url='ifms.htm?actionFlag=openAttachments&BillNo=<%=request.getParameter("BillNo")%>';
				window.open(url,"_blank","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,top=110,left=20,width=960,height=400"); 
			}
	
			function winClose()
			{
			<% if(isChq==1 || flag==true){
				%>
				self.close();
				return false;
				<%}%>
				var queryStr = window.opener.document.rm_accvousfrom.parentUrl1.value;
				if(queryStr.length > 0)
				{
		 			queryStr = queryStr.replace("#","?");
		 			var parts = queryStr.split('*');
		 			for(i=0;i<parts.length;i++)
		 			{
		 				queryStr = queryStr.replace("*","&");
		 			}
			 		window.opener.document.rm_accvousfrom.parentUrl1.value='';
					window.opener.document.rm_accvousfrom.action=queryStr;
					window.opener.document.rm_accvousfrom.submit();
				 }
				for(i=0;i<200000;i++);
				self.close();
			}		
			
			function saveBill(cmbTCCtgry,url)
			{
				if(confirm('Do you want to save the data ?'))
				{
					var allElem = document.getElementById('id_form1').elements;				
					for(i=0;i<allElem.length;i++)
					{
						allElem[i].disabled=false;
					}
					document.form1.cmbAuditorName.disabled = false;
					document.form1.cmbTCCtgry.disabled = false;	
					submitBill(cmbTCCtgry,url)					
				}
			}
						
			function submitBill(cmbTCCtgry,url)
			{
				if(move1(cmbTCCtgry,url)==true)
				{	
					var audURL = 'ifms.htm?viewName=AdtAmtMismatch&BillNo='+<%=request.getParameter("BillNo")%>;
					<%
						if(permission > 0) 
				   		{
							System.out.println("Inside useffffffrActions if != null");
					 %>
 					if(showAuditAmt(audURL)==true)
 					{
						<% 
							System.out.println("Inside if(showAuditAmt(audURL)==true) ");
						%>
						saveBillUsingAjax();
						audAmtFlag1=1;
					}
					else
					{
						<% 
						System.out.println("Inside else(showAuditAmt(audURL)==true) ");
						%>
						audAmtFlag1 = 2;
					}
					<% }  	else    { 
						System.out.println("Inside useffffffrActions if != null");
					%>
					saveBillUsingAjax();
					audAmtFlag1=1;
				   	<%	}  %>
				}
			<%
				if(showVO.getStatus().equals(BillInStat)|| showVO.getStatus().equals(BillCrdxStat))
				{
			%>
				document.form1.cmbAuditorName.disabled = false;
			<%	}  else  {						
			%>
				document.form1.cmbAuditorName.disabled = true;
			<%	}	%>
				document.form1.cmbTCCtgry.disabled = true;					
			}
			function displ()
			{
				window.opener.document.forms[0].toPost.value=document.form1.toPost.value;
				window.opener.document.forms[0].toUser.value=document.form1.toUser.value; // change here to user if needed
				saveBillUsingAjax();				
			}
			function saveBillUsingAjax()
			{
			<%if(permission>0)
				{%>
				if(document.form1.toPost.value!='')
				{
					window.opener.document.forms[0].toPost.value=document.form1.toPost.value;
					window.opener.document.forms[0].toUser.value=document.form1.toUser.value; // change here to user if needed
					window.opener.document.forms[0].actionVal.value=document.form1.actionVal.value;
				}
				<%}%>
				disable();
				document.form1.actionFlag.value='insAdtObjDetails';							
				showProgressbar();				
				window.setTimeout("HandleBillAjaxSave()",1000);
			}


//	JAVASCRIPT FUNCTION ADDED FOR REJECTING BILL : HIRAL
			function audReject(url)
			{
				if(document.forms[0].objCount.value<=0 && document.forms[0].chkbox.value=='')
				{
					alert('You have not raised any objections on bill. \nPlease raise some objection and then Reject the Bill.');
					return false;
				}
				if(document.forms[0].objCount.value>0 || document.forms[0].chkbox.value!='')
				{
					if(parseFloat(document.getElementById("id_txtNetAmt").value) != parseFloat(document.getElementById("id_txtAdtNetAmt").value))
					{
						alert('Auditor Net Amount mismatched with Net Amount.\nYou cannot Reject this bill at this time.');
						return false;
					}
					audAmtFlag1 = 2;
					url = url +"&BillNo="+<%=request.getParameter("BillNo")%>;
					url = url+ "&BillCat="+document.getElementById('id_cmbTCCtgry').value;
					window.open(url,"_blank","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,top=100,left=150,width=500,height=400"); 
				}
			}

//	JAVASCRIPT FUNCTION ADDED FOR APPROVING Bill : HIRAL
			function audApprove(url)
			{
				var apprvFlag = 1;
				if(document.forms[0].objCount.value>0 || document.forms[0].chkbox.value!='')
				{
					if(!confirm('You have raised some objections. Do you still want to approve ?'))
					{
						apprvFlag = 0;
						return false;
					}
					else
					{
						apprvFlag = 1;				
					}
				}
				if(parseFloat(document.getElementById("id_txtNetAmt").value) != parseFloat(document.getElementById("id_txtAdtNetAmt").value))
				{
					alert('Auditor Net Amount mismatched with Net Amount.\nYou cannot Approve this bill at this time.');
					apprvFlag=0;
					return false;
				}
				if(apprvFlag == 1)
				{
					url = url +"&BillNo="+<%=request.getParameter("BillNo")%>;
					url = url + "&BillCat="+document.getElementById('id_cmbTCCtgry').value;
					url = url + "&recFlag=1&updStatusFlag=BAPPRV_AUD";
					audAmtFlag1 = 2;					
					window.open(url,"_blank","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,top=100,left=150,width=500,height=400"); 
				}
			}
			
			function HandleBillAjaxSave()
			{
				xmlHttp=GetXmlHttpObject();
				
				if (xmlHttp==null)
				{
					alert ("Your browser does not support AJAX!");
					return;
				} 	
			  			   
				var url=run(); 
				var actionf=document.forms[0].actionFlag.value
				var uri='ifms.htm?actionFlag='+actionf;
				url=uri + url;           
				
				xmlHttp.onreadystatechange=billstateChanged;
				xmlHttp.open("POST",uri,false);
				xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
				xmlHttp.send(url);
			
				alert(xmlHttp.responseText);
				var frmObj=0;
				if(document.forms[0].chkbox.value!='')
				{
					printObjections();
					frmObj=1;
				}
				<%
					if(permission > 0)
					{
				%>	if(audAmtFlag1==2){
						self.close();}
				<%	}  				
				%>
				if(document.forms[0].objCount.value!=0)
				{
					if(frmObj==0)
					{
						printObjections();
					}
				}
				<%
				 //}
				%>
				
					
				
			}
			function billstateChanged() 
			{ 
				if (xmlHttp.readyState==complete_state)
				{ 
					hide_img();
					enable_div();
					hideProgressbar();
					enable();
					<%
						if(showVO.getStatus().equals(BillInStat)|| showVO.getStatus().equals(BillCrdxStat))
						{
					%>						
						document.form1.cmbAuditorName.disabled = false;
					<%}  else  {						
					%>
					document.form1.cmbAuditorName.disabled = true;
					<%}%>
					document.form1.cmbTCCtgry.disabled = true;	
					<% if(flag==true || isChq==1){%>
					var allElem = document.getElementById('id_form1').elements;		
					for(i=0;i<allElem.length;i++)
					{
						allElem[i].disabled=true;
						if(allElem[i].name=='txtareaRemarks' || allElem[i].type=='button')
							allElem[i].disabled=false;
					}
					<%}%>	
					<%
						if(permission>0)
						{
							System.out.println("Inside if(permission>0 && audAmtFlag==1){");
					%>
						
					if((document.form1.toUser.value!='' || document.form1.toPost.value!='') && audAmtFlag1==2)
					{
						var arr;
						for(i=0;i<window.opener.document.rm_accvousfrom.elements.length;i++)
					    {
					        if(window.opener.document.rm_accvousfrom.elements[i].type=="checkbox" && window.opener.document.rm_accvousfrom.elements[i].name != 'chkSelect')
					        {	
					        	arr  = window.opener.document.rm_accvousfrom.elements[i].value;
					        	var parts = arr.split("~");
					    		if(parts[0] == <%=request.getParameter("BillNo")%>)
					    		{
									window.opener.document.rm_accvousfrom.elements[i].checked = true;
									window.opener.displ();
					    		}
					        }
					    }
					}
					<%}%>	
				}	

			}
		
			function printObjections()
			{
				if(confirm('Do You Want to Print the Objection'))
				{
					showRemarks('ifms.htm?actionFlag=getAllObjectionData&pageFlg=sb');
				}
			}
			function move1(cmbTCCtgry,url)
			{	
				var Flag="true";
				var confirmFlag = false;
				document.getElementById("tcontent1").style.display = 'block';
				document.getElementById("tcontent2").style.display = 'none';
				
				if(check999('<%=lStrNil%>')==false)
				{
					document.forms[0].cmbTCCtgry.disabled = true;
					document.forms[0].cmbAuditorName.disabled = true;
					return false;
				}
				var	amt=document.getElementById('id_txtGrossAmt').value;				
				if((amt<=0 && cmbTCCtgry.options[cmbTCCtgry.selectedIndex].text=='<%=lStrRegular%>'))
				{
					alert('Gross Amount cannot be Negative or Zero.');
					document.getElementById('id_txtGrossAmt').focus();
					return false;
				}
				
				amt=document.getElementById('id_txtNetAmt').value;
				if((amt<=0 && cmbTCCtgry.options[cmbTCCtgry.selectedIndex].text=='<%=lStrRegular%>'))
				{
					alert('Net Amount cannot be Negative or Zero');
					document.getElementById('id_txtNetAmt').focus();
					return false;
				}
				if(basicBillValidation()==false)
					return false;
			<% if(lStrApprvAuth!=null){
					if(lStrApprvAuth.equals("YES")){
					%>
					if(parseFloat(document.getElementById("id_txtNetAmt").value) != parseFloat(document.getElementById("id_txtAdtNetAmt").value))
					{
						alert('Auditor Net Amount should be equal to Net Amount');
						document.getElementById("id_txtAdtNetAmt").focus();
						return false;
					}
				<%} }%>		
					
				<%if(showVO.getStatus().equals(BillCrdxStat)) { %>
				if(selCombo(document.getElementById('id_AuditorName'),"Auditor Name")==true)
					flag='true';
				else return false;
				<% } %>
				if(cmbTCCtgry.options[cmbTCCtgry.selectedIndex].text=='<%=lStrTCBill%>' && checkChallanDetails()==false)
				{	
					return false;
				}
				document.getElementById("tcontent1").style.display = 'none';
				document.getElementById("tcontent2").style.display = 'block';
				
				if(validateRemarks()==false)
					return false;						

				document.getElementById("tcontent1").style.display = 'block';
				document.getElementById("tcontent2").style.display = 'none';
				
				if(cmbTCCtgry.options[cmbTCCtgry.selectedIndex].text=='<%=lStrNil%>' && (document.getElementById("id_txtGrossAmt").value!='0' || document.getElementById("id_txtNetAmt").value!='0'))
				{					
					if(!confirm('This Nil Bill does not contain Zero Amount.\nDo you still want to continue?'))						
					{
						document.getElementById("id_txtGrossAmt").focus();
						return false;							
					}
					else
					{
						confirmFlag = true;
					}
				}							
				else					
				{			
					confirmFlag = true;
				}
				if(confirmFlag==true)
				{
				 	if(validateForm_form1())
					{
						return true;
					}
				}			   
			}
		</script>


<script type="text/javascript">
        	 var navDisplay = true;
         </script>
</head>
<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="ShowBillVO" value="${resValue.ShowBillVO}" scope="request">
</c:set>
<c:set var="audListVo" value="${resValue.audList}" scope="request">
</c:set>
<c:set var="Message" value="${resValue.StateMsg}" />
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>


<body>
<hdiits:form name="form1" validate="true" method="post" id="id_form1"
	encType="multipart/form-data">
	<%
//      	if(request.getParameter("audAmtFlag")!=null)
      	System.out.println("Value of AudAmtFlag for raisiing objection is : " +request.getParameter("audAmtFlag"));
      %>
	<input type="hidden" name="toPost" value="-1">
	<input type="hidden" name="toUser" value="-1">
	<input type="hidden" name="SendTo" value="-1">
	<input type="hidden" name="actionVal" value="">
	<input type="hidden" name="audAmtFlag" value="">
	<input type="hidden" name="rowCount" value="0" />
	<input type="hidden" name="exempted" value="${ShowBillVO.exempted}" />
	<input type="hidden" name="objCount" value="${ShowBillVO.objCount}" />
	<hdiits:hidden name="actionFlag" id="actionFlag" />
	<div id="statusBar"
		style="width:50%; background-color: #BADCFD ;display:none;font: bold 10px Arial"></div>
	<div id="progressImage" style="display:none"></div>

	<table width="100%">
		<tr>
			<td width="75%">&nbsp;</td>
			<td align="left" width="15%"><b><fmt:message
				key="CMN.BILL_CONTROL_NO" bundle="${billprocLabels}"></fmt:message></b>
			</td>
			<td align="left" width="*"><b>&nbsp;:&nbsp;<c:out
				value="${ShowBillVO.billCntrlNo}" /></b></td>
		</tr>
		<tr>
			<td width="75%">&nbsp;</td>
			<td align="left" width="15%"><b><fmt:message
				key="CMN.RefNumber" bundle="${billprocLabels}"></fmt:message></b></td>
			<td align="left" width="*"><b>&nbsp;:&nbsp;<c:out
				value="${ShowBillVO.referenceNo}" /></b></td>
		</tr>
	</table>
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"> <fmt:message
			key="CMN.BILL_DETAILS" bundle="${billprocLabels}"></fmt:message> </a></li>
		<li><a href="#" rel="tcontent2"> <fmt:message
			key="CMN.REMARKS" bundle="${billprocLabels}"></fmt:message> </a></li>
	</ul>
	</div>

	<!-- ------------------------------------------BILL DETAILS TAB------------------------------------------------------------- -->
	<div id="tcontent1" class="tabcontent"><input type="hidden"
		name="deptId" value="DP10" /> <input type="hidden" name="langId"
		value="en_US" />
	<table align="center" width="100%" border="0">

		<tr>
			<td><br>
			</td>
		</tr>
		<tr>
			<td><br>
			</td>
		</tr>
		<tr>
			<td><br>
			</td>
		</tr>
		<tr>
			<td align="center" colspan="4">
			<fieldset style="width:100%" class="tabstyle"><legend
				id="headingMsg"> <b> <fmt:message key="CNTR.DDO_INFO"
				bundle="${billprocLabels}"></fmt:message> </b> </legend>
			<table width="100%" align="center" border="0" cellspacing="0"
				cellpadding="1">
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>
				<tr>
					<td align="left" class="Label" width="21%">
					&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="CMN.CARDEX_NO"
						bundle="${billprocLabels}"></fmt:message></td>
					<td align="left" width="28%">:&nbsp;<input type="text"
						id="id_CardexNo" class="texttag mandatorycontrol"
						name="txtCardexNo" value="${ShowBillVO.cardexNo}" readonly="true" />
					&nbsp;<font color="red">*</font></td>
					<td align="left" class="Label" width="22%"><fmt:message
						key="CMN.DDO_NAME" bundle="${billprocLabels}"></fmt:message></td>
					<td align="left" width="*">:&nbsp;<input type="text"
						name="txtDDOName" size="37" id="DDOName" readonly="true"
						value="${ShowBillVO.ddoName}" maxlength="100"></td>
				</tr>

				<tr>
					<td align="left" class="Label">&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message
						key="CNTR.DDO_NO" bundle="${billprocLabels}"></fmt:message></td>
					<td align="left">:&nbsp;<input type="text" id="DDONo"
						name="txtDDONo" size="20" readonly="true"
						value="${ShowBillVO.ddoNo}"> <input type="hidden"
						name="DDOCode" value="${ShowBillVO.ddoCode}" /> <input
						type="hidden" name="DDOdeptId" value="${ShowBillVO.deptCode}" /></td>
					<td align="left" class="Label"><fmt:message
						key="CNTR.DEPARTMENT" bundle="${billprocLabels}"></fmt:message></td>
					<td align="left">:&nbsp;<select name="txtDepartment"
						id="DDODept" style="width:93%" tabindex="1">
						<c:forEach var="dept" items="${resValue.showDeptList}">
							<option selected="true" value="<c:out value="${dept.commonId}"/>">
							<c:out value="${dept.commonDesc}" /></option>
						</c:forEach>
					</select> <script type="text/javascript">
										document.getElementById("DDODept").value='${ShowBillVO.deptCode}';
									</script></td>
				</tr>
			</table>
			</fieldset>
			</td>
		</tr>
		<tr>
			<td colspan="4">&nbsp;</td>
		</tr>

		<tr>
			<td align="center" colspan="4">
			<fieldset style="width:100%" class="tabstyle"><legend
				id="headingMsg"> <b> <fmt:message key="CNTR.BILL_INFO"
				bundle="${billprocLabels}"></fmt:message> </b> </legend>
			<table width="100%" align="center" border="0" cellspacing="0"
				cellpadding="1">
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>
				<tr>
					<td align="left" class="Label" width="18%">
					&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="CMN.BILL_CONTROL_NO"
						bundle="${billprocLabels}"></fmt:message></td>
					<td align="left" width="25%">:&nbsp;<input type="text"
						name="txtBillNo" class="texttag mandatorycontrol"
						value="${ShowBillVO.billCntrlNo}" readonly="true" /> &nbsp;<font
						color="red">*</font></td>
					<td align="left" class="Label" width="15%"><fmt:message
						key="CMN.TOKEN_NO" bundle="${billprocLabels}"></fmt:message></td>
					<td align="left" width="*">:&nbsp;<input type="text"
						name="txtTokenNo" id="id_txtTokenNo"
						class="texttag mandatorycontrol" value="${ShowBillVO.tokenNum}"
						readonly="TRUE" maxlength="5" /> &nbsp;<font color="red">*</font>
					</td>
				</tr>

				<tr>
					<td align="left" class="Label">&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message
						key="CMN.BILL_DATE" bundle="${billprocLabels}"></fmt:message></td>
					<%
								SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
								String billDt = sdf.format(showVO.getBillDate());
								System.out.println("Bill Date in Show Bill Jsp : " +billDt);
							%>
					<td align="left">:&nbsp;<input type="text" name="txtBillDate"
						id="txtBillDate" value="<%=billDt%>"
						class="texttag mandatorycontrol" maxlength="10" tabindex="2" /> <img
						src="images/CalendarImages/ico-calendar.gif" width="20"
						onclick="window_open('txtBillDate',375,570)" id="id_imgDate" />
					&nbsp;<font color="red">*</font> <%	if(flag==true || isChq==1)  {    %>
					<script>
			    		document.getElementById('id_imgDate').style.display='none';
			    	</script> <%}%>
					</td>

				</tr>

				<tr>
					<td align="left" class="Label">&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message
						key="CNTR.GROSS_AMOUNT" bundle="${billprocLabels}"></fmt:message>
					</td>
					<td align="left">:&nbsp;<input type="text" name="txtGrossAmt"
						class="texttag mandatorycontrol" id="id_txtGrossAmt"
						value="${ShowBillVO.billGrossAmount}" maxlength="17"
						onkeypress="javascript:AmountFormat();" tabindex="3" 
						onfocus="javascript:showHighLight(this)" onblur="javascript:chkAmtLength(this,'Net Amount',17)"
					/> &nbsp;<font
						color="red">*</font></td>
					<td align="left" class="Label" width="20%"><fmt:message
						key="CNTR.NET_AMOUNT" bundle="${billprocLabels}"></fmt:message></td>
					<td align="left" width="25%">:&nbsp;<input type="text"
						name="txtNetAmt" class="texttag mandatorycontrol"
						id="id_txtNetAmt" value="${ShowBillVO.billNetAmount}"
						maxlength="17" onkeypress="javascript:AmountFormat();"
						onblur="javascript:chkAmtLength(this,'Net Amount',17)"
						onfocus="javascript:showHighLight(this)"
						tabindex="4" /> &nbsp;<font color="red">*</font></td>
				</tr>

				<tr>
					<%
								if(userActions != null ) 
						   		{
							   		if( userActions.getUserElements(121060) != null )	
							   		{							   			
							%>
					<td align="left" class="Label">&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message
						key="ADT.AUDITOR_NET_AMOUNT" bundle="${billprocLabels}"></fmt:message>
					</td>
					<td align="left">
						:&nbsp;<hdiits:text
									userObject="<%=userActions.getUserElements(121060)%>"
									name="txtAdtNetAmt" id="id_txtAdtNetAmt"
									caption="Auditor Net Amount" validation="txt.isrequired,txt.isflt"
									captionid="id_txtAdtNetAmt"
									onkeypress="javascript:AmountFormat();"
									classcss="texttag mandatorycontrol"
									onblur="javascript:convertNum(this);"
									default="${ShowBillVO.auditorNetAmount}" 
									onfocus="javascript:showHighLight(this)"
							></hdiits:text> &nbsp;
							<font color="red">*</font></td>
					<% }   
								else
								{
							%>
					<td align="left" class="Label">&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message
						key="ADT.AUDITOR_NET_AMOUNT" bundle="${billprocLabels}"></fmt:message>
					</td>
					<td align="left">:&nbsp;<input type="text" name="txtAdtNetAmt"
						id="id_txtAdtNetAmt" readonly="true"
						value="${ShowBillVO.auditorNetAmount}"></td>
					<% } }%>
					<td align="left" class="Label"><fmt:message
						key="ADT.AUDIT_AMT_WORDS" bundle="${billprocLabels}"></fmt:message>
					</td>
					<td align="left">:&nbsp;<textarea name="txtAdtAmtWords"
						rows="3" id="id_txtAdtAmtWords" cols="17" readonly="true"></textarea>
					<script>
								if(document.getElementById('id_txtAdtNetAmt').value!='')								
								{
									convertNum(document.getElementById('id_txtAdtNetAmt'));
								}
							</script></td>
				</tr>
				<tr>
					<td align="left" class="Label">&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message
						key="CNTR.GO/NGO" bundle="${billprocLabels}"></fmt:message></td>
					<td align="left">:&nbsp;<select name="cmbGONGO" id="id_GoNgo"
						style="width:56%" tabindex="5">
						<option value="">--Select--</option>
						<c:forEach var="goNgo" items="${resValue.goNgoList}">
							<option value='<c:out value="${goNgo.lookupName}"/>'><c:out
								value="${goNgo.lookupDesc}" /></option>
						</c:forEach>
					</select> <script type="text/javascript">
									document.getElementById("id_GoNgo").value='${ShowBillVO.goNgo}';
							</script></td>
					<td align="left" class="Label"><fmt:message
						key="CMN.BILL_CATEGORY" bundle="${billprocLabels}"></fmt:message>
					</td>
					<td align="left">:&nbsp;<select name="cmbTCCtgry"
						id="id_cmbTCCtgry" disabled="true" style="width:56%" tabindex="6">
						<option value="0">--Select--</option>
						<c:forEach var="tcBill" items="${resValue.TcBillList}">
							<option value='<c:out value="${tcBill.lookupName}"/>'><c:out
								value="${tcBill.lookupDesc}" /></option>
						</c:forEach>
					</select>&nbsp;<font color="red">*</font> <script type="text/javascript">
								document.getElementById("id_cmbTCCtgry").value='${ShowBillVO.tcBill}';
							</script></td>
				</tr>

				<tr height="30px">
					<td align="left" class="Label">&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message
						key="CMN.BILL_TYPE" bundle="${billprocLabels}"></fmt:message></td>
					<td align="left" colspan="3">:&nbsp;
<!-- 	changes for bill type textbox : Hiral -->												
					<input type="text" name="cmbBillType" id="id_BillType" tabindex="10" 
									value="${ShowBillVO.billTypeCode}" onblur="javascript:convertToUpper(this);"
									onfocus="showHighLight(this);" />
<!-- 	changes for bill type textbox : Hiral -->							
									
<!-- 					<select name="cmbBillType"
						id="id_BillType" style="width:61%" tabindex="7"${resValue.disableFlag}>
						<option value="0">--Select--</option>
						<c:forEach var="billType" items="${resValue.BillType}">
							<option value='<c:out value="${billType[0]}"/>'>
								<c:out value="${billType[2]}(${billType[1]})"/>
							</option>
						</c:forEach>
					</select>
 -->					
					&nbsp;<font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp; 
						<script	type="text/javascript">
//								document.getElementById("id_BillType").value='${ShowBillVO.billType}';
						</script>
					</td>
				</tr>

				<tr>
					<td align="left" class="Label">&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message
						key="CNTR.EXEMPTED" bundle="${billprocLabels}">
					</fmt:message></td>
					<td align="left" class="Label"><input type="radio"
						name="radExempted" value="Y" id="id_radExempted"
						onclick="javascript:showExempt(this);" checked="checked"
						tabindex="8" /> <fmt:message key="CMN.YES"
						bundle="${billprocLabels}"></fmt:message> <input type="radio"
						name="radExempted" value="N" id="id_radExempted"
						onclick="javascript:showExempt(this);" checked="checked"
						tabindex="8" /> <fmt:message key="CMN.NO"
						bundle="${billprocLabels}"></fmt:message> <input type="hidden"
						id="exempted" /></td>
				</tr>

				<tr>
					<td colspan="4" align="left">
					<table id="divExempted" align="center" border="0" width="100%">
						<tr>
							<td align="left" class="Label" width="19%">
							&nbsp;&nbsp;&nbsp;<fmt:message key="CMN.BILL_CODE"
								bundle="${billprocLabels}"></fmt:message></td>
							<td align="left" width="*">:&nbsp;<select name="cmbBillCode"
								id="id_cmbBillCode" style="width:61%" tabindex="9">
								<option value="">--Select--</option>
								<c:forEach var="billcode" items="${resValue.BillCodeList}">
									<option value='<c:out value="${billcode.lookupName}"/>'>
									<c:out value="${billcode.lookupShortName}" /> - <c:out
										value="${billcode.lookupDesc}" /></option>
								</c:forEach>
							</select>&nbsp;<font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;</td>
						</tr>
					</table>
					<script type="text/javascript">
								document.getElementById("exempted").value='${ShowBillVO.exempted}';
								if(document.getElementById("exempted").value=='Y')
								{
									document.forms[0].radExempted[0].checked="checked";
									document.getElementById('divExempted').style.display='block';
									document.getElementById("id_cmbBillCode").value='${ShowBillVO.billCode}';
								}
								else
								{
									document.forms[0].radExempted[1].checked="checked";
									document.getElementById('divExempted').style.display='none';
								}
							</script></td>
				</tr>
				<tr>
					<td align="left" class="Label">&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message
						key="CNTR.PREVIOUS_BILL_NO" bundle="${billprocLabels}"></fmt:message>
					</td>
					<td align="left">:&nbsp;<input type="text"
						name="txtPrevBillNo" id="id_txtPrevBillNo" size="20"
						value="${ShowBillVO.prevBillNo}" tabindex="10"
						onfocus="javascript:showHighLight(this)"
						onblur="hideHighLight(this)"
					></td>
					<td></td>
					<td></td>
				</tr>
			</table>
			</fieldset>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td colspan="4">
			<div id="divTC">
			<fieldset style="width:100%" class="tabstyle"><legend
				id="headingMsg"> <b> &nbsp;<fmt:message
				key="CNTR.TC_INFO" bundle="${billprocLabels}"></fmt:message> </b> </legend>
			<table align="center" width="100%" border="0" cellpadding="1"
				cellspacing="0" id="TCTable">
				<tr>
					<td align="center" class="datatableheader"><fmt:message
						key="CNTR.CHALLAN_NO" bundle="${billprocLabels}"></fmt:message></td>
					<td align="center" class="datatableheader"><fmt:message
						key="CNTR.CHALLAN_DATE" bundle="${billprocLabels}"></fmt:message>

					</td>
					<td align="center" class="datatableheader"><fmt:message
						key="CNTR.CHALLAN_MAJORHEAD" bundle="${billprocLabels}"></fmt:message>
					</td>
					<td align="center" class="datatableheader"><fmt:message
						key="CNTR.CHALLAN_AMT" bundle="${billprocLabels}"></fmt:message></td>
				</tr>
				<tr>
					<td align="center">
					<input type="text" id="id_txtChallanNo"
						name="txtChallanNo1" size="20" tabindex="16"
						attrTitle="Challan No"
						onfocus="javascript:showHighLight(this)" 
						onblur="hideHighLight(this)"
					 /></td>
					<td align="center">
					<input type="text" name="txtChallanDate1"
						maxlength="10" size="20" tabindex="17" attrTitle="Challan Date" 
						onfocus="javascript:showHighLight(this)" 
						onblur="hideHighLight(this)"
						/>
					<img src='images/CalendarImages/ico-calendar.gif' width='20'
						onClick='window_open("txtChallanDate1",375,570)'></td>
					<td align="center">
					<input type="text"
						name="txtChallanMjrHead1" attrTitle="Challan Major Head"
						class="texttag mandatorycontrol" id="id_txtChallanMjrHead"
						size="20" maxlength="4" tabindex="18" 
						onblur="javascript:chkHeadLength(this,4)" 
						onfocus="javascript:showHighLight(this)" 
						/> &nbsp;<font
						color="red">*</font></td>
					<td align="center"><input type="text" id="id_txtChallanAmt"
						attrTitle="Challan Amount" class="texttag mandatorycontrol"
						onkeypress="javascript:AmountFormat()" name="txtChallanAmt1"
						maxlength="17" tabindex="19" size="20" onblur="getTotal(this);" 
						onfocus="javascript:showHighLight(this)" 						
					/>
					&nbsp;<font color="red">*</font></td>
				</tr>
				<%
							double ldTotalAmt = 0;
							String lStrTotalAmt = "";
							if(showVO!=null && showVO.getTcBill().equals(lStrTCBill))
							{
								System.out.println("List from ShowBillVO : " +showVO);
								List lListRcptDtls = (List)showVO.getListReceipt();
								System.out.println("List from ShowBillVO : " +lListRcptDtls.size());
								TrnReceiptDetails lListRcptVO1 = (TrnReceiptDetails)lListRcptDtls.get(0);
								
								Date lDtChallan = null;
								String SearchDate ="";
								ldTotalAmt = ldTotalAmt + lListRcptVO1.getAmount().doubleValue();
								String lStrRcptNo = lListRcptVO1.getReceiptNo()!=null ? lListRcptVO1.getReceiptNo():"";
								String lStrRcptMjrHd = lListRcptVO1.getMajorHead()!=null ? lListRcptVO1.getMajorHead():"";
								String lStrRcptAmt = lListRcptVO1.getAmount()!=null ? String.valueOf(lListRcptVO1.getAmount()):"";
								Date lStrRcptDt = lListRcptVO1.getReceiptDate()!=null ? lListRcptVO1.getReceiptDate():null;
								
//								Date todayDt = new Date(System.currentTimeMillis());
//								String lDtChallan = sdf.format(lStrRcptDt);
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
								document.forms[0].rowCount.value = '<%=(lListRcptDtls.size())%>';
							</script>
				<%		int i=0;
								
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
											SearchDate=new SimpleDateFormat("dd/MM/yyyy").format(lStrRcptDt);
									}
									catch(Exception e)
									{
										e.printStackTrace();
									}
						%>
				<tr>
					<td align="center">
						<input type="text" id="id_txtChallanNo"
						name="txtChallanNo<%=(i+1)%>" size="20" value="<%=lStrRcptNo%>"
						tabindex="16" onfocus="javascript:showHighLight(this)" 
						onblur="hideHighLight(this)"
						/></td>
					<td align="center"><input type="text"
						name="txtChallanDate<%=(i+1)%>" maxlength="10" size="20"
						tabindex="17" attrTitle="Challan Date" value="<%=SearchDate%>" 
						onfocus="javascript:showHighLight(this)" 
						onblur="hideHighLight(this)"
						/>
					<img src='images/CalendarImages/ico-calendar.gif' width='20'
						onClick='window_open("txtChallanDate<%=(i+1)%>",375,570)'>
					</td>
					<td align="center">
						<input type="text"
						name="txtChallanMjrHead<%=(i+1)%>" attrTitle="Challan Major Head"
						class="texttag mandatorycontrol" id="id_txtChallanMjrHead"
						size="20" maxlength="4" tabindex="18" value="<%=lStrRcptMjrHd%>"
						onblur="javascript:chkHeadLength(this,4)" onfocus="javascript:showHighLight(this)" 
						/> &nbsp;<font
						color="red">*</font></td>
					<td align="center"><input type="text" id="id_txtChallanAmt"
						attrTitle="Challan Amount" class="texttag mandatorycontrol"
						onkeypress="javascript:AmountFormat()"
						name="txtChallanAmt<%=(i+1)%>" maxlength="17" tabindex="19"
						size="20" onblur="getTotal(this);" value="<%=lStrRcptAmt%>" 
						onfocus="javascript:showHighLight(this)" 
						/> &nbsp;<font
						color="red">*</font></td>
				</tr>
				<%		
							} 
								DecimalFormat dFormat=new DecimalFormat();
								dFormat.applyPattern("#0.00");
								lStrTotalAmt=dFormat.format(ldTotalAmt);
//								lStrTotalAmt = BigDecimal.valueOf(ldTotalAmt).toPlainString();
							}
							else
							{
								lStrTotalAmt = "0";
							}
							System.out.println("Value of Total Challan Amount : " +ldTotalAmt);
						%>
			</table>
			<table align="center" width="100%" border="0">
				<tr>
					<td align="left" width="25%">&nbsp;</td>
					<td align="left" width="25%">&nbsp;</td>
					<td align="right" width="25%"><fmt:message
						key="CMN.ChallanTotal" bundle="${billprocLabels}"></fmt:message>:&nbsp;
					</td>
					<td align="left" class="even" id="challanAmtTotal">
					<%	System.out.println("Value of Total Challan Amount 2222 : " +ldTotalAmt);	  %>
					<fmt:formatNumber pattern=".00" type="number" value="0"></fmt:formatNumber>
					</td>
					<script>
							<% System.out.println("Value of Challan Amount in String : " +lStrTotalAmt); %>
								document.getElementById("challanAmtTotal").innerHTML = '<%=lStrTotalAmt%>';
							</script>
				</tr>
			</table>

			</fieldset>
			</div>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>
			<fieldset style="width:100%" class="tabstyle"><legend
				id="headingMsg"> <b> &nbsp;<fmt:message
				key="CNTR.BUDGET_INFO" bundle="${billprocLabels}"></fmt:message> </b> </legend>
			<table width="100%" align="center" border="0" cellspacing="0"
				cellpadding="1">
				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td align="left" class="Label" width="20%">
					&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="BUD.TYPE_CODE"
						bundle="${billprocLabels}"></fmt:message></td>
					<td align="left" width="30%">:&nbsp;<select name="cmbPlan"
						id="id_cmbPlan" tabindex="15" style="width:56%"
						onchange="setText(this)">
						<option selected="selected" value="">--Select--</option>
						<script type="text/javascript">
								lookupArray[0] = "";
							</script>

						<c:forEach var="BudType" items="${resValue.planList}"
							varStatus="No">
							<c:choose>
								<c:when
									test='${resValue.Selected_BudType != null && BudType.lookupName == resValue.Selected_BudType.Value}'>
									<option selected="selected" value='${BudType.lookupName}'><c:out
										value="${BudType.lookupShortName}" /></option>
								</c:when>
								<c:otherwise>
									<option value='${BudType.lookupName}'><c:out
										value="${BudType.lookupShortName}" /></option>
								</c:otherwise>
							</c:choose>
							<script type="text/javascript">
									lookupArray[${No.count}] = "${BudType.lookupDesc}";
								</script>
						</c:forEach>
					</select>&nbsp;<font color="red">*</font> <script type="text/javascript">
								document.getElementById("id_cmbPlan").value='${ShowBillVO.budType}'
						  </script></td>
					<td align="left" class="Label" width="23%"><fmt:message
						key="BUD.TYPE_DESC" bundle="${billprocLabels}"></fmt:message></td>
					<td align="left" width="20%">:&nbsp;<textarea
						name="txtBudDesc" rows="3" cols="17" readonly="readonly"><c:out
						value="${ShowBillVO.budTypeDesc}" /></textarea></td>
				</tr>

				<tr>
					<td align="left" width="20%" class="Label">
					&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="CNTR.ENTRY"
						bundle="${billprocLabels}"></fmt:message>?</td>
					<td align="left" width="*"><input type="radio" name="radEntry"
						value="ddo" id="id_radEntry" tabindex="16" checked="true" /> <fmt:message
						key="CMN.USER" bundle="${billprocLabels}"></fmt:message>
					&nbsp;&nbsp;&nbsp;<input type="radio" name="radEntry" value="user"
						id="id_radEntry" onclick="javascript:openDdoHeadPopup();"
						tabindex="17" /> <fmt:message key="CMN.Ddo"
						bundle="${billprocLabels}"></fmt:message></td>
					<td align="left" class="Label" width="20%"><fmt:message
						key="CNTR.SCHEME_CODE" bundle="${billprocLabels}"></fmt:message></td>
					<td align="left" width="25%">:&nbsp;<input type="text"
						name="txtSchemeCode" size="20" id="id_txtSchemeCode"
						value="${ShowBillVO.schemeCode}"
						onblur="javascript:getDDOHeadBySchemeNo('${pageContext.request.contextPath}');" tabindex="18"
						onkeypress="javascript:AmountFormat()" 
						onfocus="javascript:showHighLight(this)" 
						>
					<input type="hidden" name="headStatus" id="id_headStatus" value="" />
					</td>
				</tr>
				<tr>
					<td align="left" class="Label" width="7%">
					&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="CMN.DEMAND_NO"
						bundle="${billprocLabels}"></fmt:message></td>
					<td align="left" width="30%">:&nbsp;<input type="text"
						name="cmbDemand" class="texttag mandatorycontrol"
						id="id_cmbDemand" value="${ShowBillVO.dmndNo}" maxlength="3"
						onblur="javascript:chkHeadLength(this,3)"
						onkeypress="javascript:AmountFormat()"
						onchange="javascript:cleanHeadDtls(this);" tabindex="19" 
						onfocus="javascript:showHighLight(this)"
						/> &nbsp;<font
						color="red">*</font></td>
					<td align="left" class="Label" width="7%"><fmt:message
						key="CMN.MAJOR_HEAD" bundle="${billprocLabels}"></fmt:message></td>
					<td align="left" width="46%">:&nbsp;<input type="text"
						name="cmbMajorHead" class="texttag mandatorycontrol"
						id="id_cmbMajorHead" value="${ShowBillVO.budmjrHd}" maxlength="4"
						tabindex="20" onkeypress="javascript:AmountFormat()" 
						onfocus="showHighLight(this)" onblur="hideHighLight(this)"
						/>
					&nbsp;<font color="red">*</font></td>
				</tr>

				<tr>
					<td align="left" class="Label" width="20%">
					&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="CMN.SUB_MAJORHEAD"
						bundle="${billprocLabels}"></fmt:message></td>
					<td align="left" width="25%">:&nbsp;<input type="text"
						name="cmbSubMajorHead" size="20" id="id_cmbSubMajorHead"
						value="${ShowBillVO.budSubMjrHd}" maxlength="2"
						onblur="javascript:chkHeadLength(this,2)" tabindex="21"
						onkeypress="javascript:AmountFormat()"
						onchange="javascript:cleanHeadDtls(this);" 
						onfocus="javascript:showHighLight(this)"
						/></td>
					<td align="left" class="Label" width="20%"><fmt:message
						key="CMN.MINOR_HEAD" bundle="${billprocLabels}"></fmt:message></td>
					<td align="left" width="25%">:&nbsp;<input type="text"
						caption="Minor Head" name="cmbMinorHead" size="20"
						id="id_cmbMinorHead" value="${ShowBillVO.budMinHd}" maxlength="3"
						onblur="javascript:chkHeadLength(this,3)" tabindex="22"
						onkeypress="javascript:AmountFormat()"
						onchange="javascript:cleanHeadDtls(this);"
						onfocus="javascript:showHighLight(this)"
					/></td>
				</tr>

				<tr>
					<td align="left" class="Label2" width="20%">
					&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="CMN.SUB_HEAD"
						bundle="${billprocLabels}"></fmt:message></td>
					<td align="left" width="25%">:&nbsp;<input type="text"
						name="cmbSubHead" size="20" id="id_cmbSubHead"
						value="${ShowBillVO.budSubHd}" maxlength="2"
						onblur="javascript:getSchemeNoByDDOGrantHead(this,2,'${pageContext.request.contextPath}')"
						tabindex="23" onfocus="javascript:showHighLight(this)"
						/></td>
					<td align="left" width="20%" class="Label"><fmt:message
						key="CMN.DETAIL_HEAD" bundle="${billprocLabels}"></fmt:message></td>
					<td align="left" width="25%">:&nbsp;<input type="text"
						name="cmbDetailHead" id="id_cmbDetailHead" size="20"
						value="${ShowBillVO.budDetailHd}" tabindex="24" maxlength="2"
						onblur="javascript:chkHeadLength(this,2)" 
						onkeypress="javascript:AmountFormat()" 
						onfocus="showHighLight(this)"
						/></td>
				</tr>



				<tr>
					<td align="left" class="Label" width="20%">
					&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="CNTR.GRANT_AMOUNT"
						bundle="${billprocLabels}"></fmt:message></td>
					<td align="left" width="25%">:&nbsp;<input type="text"
						name="txtGrantAmt" size="20" id="id_txtGrantAmt"
						value="${ShowBillVO.grantAmt}" readonly="true" /></td>

				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="4" align="left">&nbsp;</td>
		</tr>

		<tr>
			<td align="left" colspan="4">
			<fieldset style="width:100%" class="tabstyle"><legend
				id="headingMsg"> <b> &nbsp;<fmt:message
				key="CNTR.TREASURY_INFO" bundle="${billprocLabels}"></fmt:message> </b>
			</legend>
			<table width="100%" align="center" cellpadding="0" cellspacing="0"
				border="0">
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td width="20%">&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message
						key="CMN.AUDITOR_NAME" bundle="${billprocLabels}"></fmt:message> <%if(showVO.getStatus().equals(BillCrdxStat)) { %>
					&nbsp;<font color="red">*</font> <%} %>
					</td>
					<td align="left" width="*">:&nbsp; <%
								if(showVO.getStatus().equals(BillInStat)|| showVO.getStatus().equals(BillCrdxStat))
									{													
								%> <c:set var="varDisb" value=""></c:set> <%
									}
									else
									{
								%> <c:set var="varDisb" value="disabled"></c:set> <%													
									}
								%> <select name="cmbAuditorName" style="width:45%"
						id="id_AuditorName" ${varDisb} tabindex="25">
						<option value="">-----Select-----</option>
						<c:forEach var="emp" items="${audListVo}" varStatus="counter">
							<c:choose>
								<c:when test="${emp.postId==ShowBillVO.auditorPostId}">
									<option selected="true" value="<c:out value="${emp.postId}"/>"><c:out
										value="${emp.fullName}" /></option>
								</c:when>
								<c:otherwise>
									<option value="<c:out value="${emp.postId}"/>"><c:out
										value="${emp.fullName}" /></option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select> <script type="text/javascript">
									document.getElementById("id_AuditorName").value='${ShowBillVO.auditorPostId}';
							 </script></td>
				</tr>
			</table>
			</fieldset>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
	</table>
	</div>

	<!-- --------------------------------------AUDITOR'S REMARKS TAB------------------------------------------------------- -->
	<div id="tcontent2" class="tabcontent"><jsp:include
		page="remarksDetails.jsp" flush="true" /> <%	if(flag==true || isChq==1)  {    %>
	<script>
							document.getElementById('id_objection').style.display='none';
						</script> <%}%>
	</div>
	<!---------------------------------JAVA SCRIPT----------------------------------------->


	<center>
		<hdiits:button name="btnSave" type="button" value='<%= bundle.getString("CMN.SAVE")%>' onclick="javascript:saveBill(cmbTCCtgry,'ifms.htm?actionFlag=insAdtObjDetails')" />
  	  <input type="hidden" name="BillNo" value=<%=request.getParameter("BillNo")%> /> 		 	
		<hdiits:button name="btnClose" type="button" value='<%= bundle.getString("CMN.CLOSE")%>' onclick="javascript:winClose();"></hdiits:button>
<!--  Elements for Approve and Reject added : Hiral -->		
		<% 	if(userActions != null && userActions.getUserElements(120027) != null)
	 		{
		%> 
			<hdiits:button type="button" userObject="<%=userActions.getUserElements(120027)%>" name="btnApprove" value='<%=bundle.getString("COMMON.APPROVE")%>' onclick="javascript:audApprove('ifms.htm?actionFlag=getHyrUsers&statusFlag=BAUD&actionVal=APPROVE');"></hdiits:button> 
			<hdiits:button type="button" userObject="<%=userActions.getUserElements(120027)%>" name="btnReject" value='<%=bundle.getString("COMMON.REJECT")%>' onclick="javascript:audReject('ifms.htm?actionFlag=getHyrUsers&statusFlag=BAUD&actionVal=REJECT');"></hdiits:button>
		<%	
			}
	 	%>
<!-- Ends  Elements for Approve and Reject added : Hiral -->		
	</center>
	<script language="javascript">
		 	function emptyAuditor()
		 	{				
				document.getElementById("id_AuditorName").options.length = 0;
				document.getElementById("id_AuditorName").options[0] = new Option("--Select--", "0");
			}							
		</script>
	<script language="javascript">
		
			var indx = document.form1.cmbTCCtgry.options[document.form1.cmbTCCtgry.selectedIndex];		
			if(indx.text=='<%=lStrTCBill%>')
			{
				document.getElementById('divTC').style.display='block';
				
			}
			else
			{					
				document.getElementById('divTC').style.display='none';
			}
				
			function showBill(url)
			{	
				var bill = '<%=billNum%>';
				url=url+"&BillNo="+bill;			
				window.open(url,"_blank","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,top=100,left=150,width=550,height=400"); 			
			}
			function showRemarks(url)
			{
				var bill = '<%=billNum%>';
				url=url+"&BillNo="+bill;			
				window.open(url,"_blank","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,top=30,left=120,width=700,height=650"); 			
			}
/*			function getSchemeNoByDDOGrantHead(ctrl,num)
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
			
			function getDDOHeadBySchemeNo()
			{
				hideHighLight(document.getElementById("id_txtSchemeCode"));
				req = createXMLHttpRequest();
					
				disable();
				showProgressbar();
				
				var baseUrl = "${pageContext.request.contextPath}/ifms.htm?actionFlag=getDDOHeadBySchemeNo";
				baseUrl += "&schemeNo=" + document.getElementById("id_txtSchemeCode").value;
				
				req.open("post", baseUrl, false); 
				req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
				req.onreadystatechange = responseDDOHeadBySchemeNo; 
				req.send(baseUrl);
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
					}
				}		
			}			
*/			
			function clearMjrHdDtls()
			{
				hideHightLight(document.getElementById("id_cmbMajorHead"));
				document.getElementById("id_cmbSubMajorHead").value = "";
				document.getElementById("id_cmbMinorHead").value = "";
				document.getElementById("id_cmbSubHead").value = "";
				document.getElementById("id_cmbDetailHead").value = "";
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
		</script>

	<%
		System.out.println("Status from ShowVO : " +showVO.getStatus());
		System.out.println("Status from Constants INWARD : " +BillInStat);
		System.out.println("Status from Constants CARDEX : " +BillCrdxStat);
	if(showVO.getStatus().equals(BillInStat)|| showVO.getStatus().equals(BillCrdxStat))
	{
		System.out.println("Inside Status IFF : ");
	%>
<!-- 	Parameters names changed -->		
	<ajax:select
		baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=GONGOData"
		source="id_GoNgo" 
		target="id_AuditorName"
  	    parameters="cmbDemand={cmbDemand},cmbMajorHead={cmbMajorHead},langId={langId},txtDepartment={txtDepartment},cmbMajorHead={cmbMajorHead},cmbGONGO={cmbGONGO},cmbBillType={cmbBillType},txtCardexNo={txtCardexNo}" 	  
		emptyFunction="emptyAuditor" />
	<ajax:select
		baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=GONGOData"
		source="id_CardexNo" 
		target="id_AuditorName"
 	    parameters="cmbDemand={cmbDemand},cmbMajorHead={cmbMajorHead},langId={langId},txtDepartment={txtDepartment},cmbMajorHead={cmbMajorHead},cmbGONGO={cmbGONGO},cmbBillType={cmbBillType},txtCardexNo={txtCardexNo}" 	  
		emptyFunction="emptyAuditor" />
	<ajax:select
		baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=GONGOData"
		source="id_BillType" 
		target="id_AuditorName"
	    parameters="cmbDemand={cmbDemand},cmbMajorHead={cmbMajorHead},langId={langId},txtDepartment={txtDepartment},cmbMajorHead={cmbMajorHead},cmbGONGO={cmbGONGO},cmbBillType={cmbBillType},txtCardexNo={txtCardexNo}" 	  
		emptyFunction="emptyAuditor" />
	<ajax:select
		baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=GONGOData"
		source="DDODept" 
		target="id_AuditorName"
	    parameters="cmbDemand={cmbDemand},cmbMajorHead={cmbMajorHead},langId={langId},txtDepartment={txtDepartment},cmbMajorHead={cmbMajorHead},cmbGONGO={cmbGONGO},cmbBillType={cmbBillType},txtCardexNo={txtCardexNo}" 	  
		emptyFunction="emptyAuditor" />

	<ajax:select
		baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=GONGOData"
		source="cmbMajorHead" 
		target="cmbAuditorName"
	    parameters="cmbDemand={cmbDemand},cmbMajorHead={cmbMajorHead},langId={langId},txtDepartment={txtDepartment},cmbMajorHead={cmbMajorHead},cmbGONGO={cmbGONGO},cmbBillType={cmbBillType},txtCardexNo={txtCardexNo}" 	  
		postFunction="clearMjrHdDtls" 
		errorFunction="clearMjrHdDtls"
		eventType="blur" />
<!--  Ends : 	Parameters names changed -->	
	<%
	}
	%>
	<!--  	 <ajax:updateField 
		  baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getGrantAmount"
		  action="cmbPlan"
		  source="cmbPlan"
		  target="id_txtGrantAmt,id_txtSchemeCode,id_headStatus"
		  parameters="DDOCode={DDOCode},txtDepartment={txtDepartment},cmbDemand={cmbDemand},cmbMajorHead={cmbMajorHead},cmbSubMajorHead={cmbSubMajorHead},cmbMinorHead={cmbMinorHead},cmbSubHead={cmbSubHead},langId={langId},deptId={deptId},cmbPlan={cmbPlan}" 
	 	  postFunction="showText" 
	  /> 
  -->
	<ajax:updateField
		baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getGrantAmount"
		action="cmbPlan"
		source="cmbPlan"
		target="id_txtGrantAmt,id_headStatus"
		parameters="DDOCode={DDOCode},txtDepartment={txtDepartment},cmbDemand={cmbDemand},cmbMajorHead={cmbMajorHead},cmbSubMajorHead={cmbSubMajorHead},cmbMinorHead={cmbMinorHead},cmbSubHead={cmbSubHead},langId={langId},deptId={deptId},cmbPlan={cmbPlan}"
		postFunction="showText" />
	<ajax:updateField
		baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getGrantAmount"
		action="cmbDetailHead" 
		source="cmbDetailHead"
		target="id_txtGrantAmt,id_headStatus"
		parameters="DDOCode={DDOCode},txtDepartment={txtDepartment},cmbDemand={cmbDemand},cmbMajorHead={cmbMajorHead},cmbSubMajorHead={cmbSubMajorHead},cmbMinorHead={cmbMinorHead},cmbSubHead={cmbSubHead},langId={langId},deptId={deptId},cmbPlan={cmbPlan}"
		postFunction="showText" 
		errorFunction="showText"
		eventType="blur" />
	<hdiits:validate locale="en_US" />


	<script type="text/javascript">			
			initializetabcontent("maintab");
		</script>
	<%if(flag==true || isChq==1) {%>
	<script>
				var allElem = document.getElementById('id_form1').elements;				
				for(i=0;i<allElem.length;i++)
				{
					allElem[i].disabled=true;
					if(allElem[i].name=='txtareaRemarks' || allElem[i].type=='button')
						allElem[i].disabled=false;
				}
				</script>
	<%}%>
	<script type="text/javascript">
			<%if(lStrApprvAuth!=null){
				if(lStrApprvAuth.equals("YES")){%>
				document.getElementById('id_txtNetAmt').readOnly=false;
				document.getElementById('id_txtGrossAmt').readOnly=false;
			<%}else{%>
			document.getElementById('id_txtNetAmt').readOnly=true;
				document.getElementById('id_txtGrossAmt').readOnly=true;
				<%}}
				else{
			%>
				document.getElementById('id_txtNetAmt').readOnly=true;
				document.getElementById('id_txtGrossAmt').readOnly=true;
				<%}%>
/*		function getTotal(ctrl)
		{
			hideHighLight(ctrl);
			var tbl = document.getElementById("TCTable");
	  	    var rowSize = tbl.rows.length;
	  	    var rowLen = document.form1.rowCount.value;
	  	    var total=0.0;
	  	    for(ii=1;ii<=(rowSize-1);ii++)
	  	    {	  	    	
    	  	    var amt = eval("document.form1.txtChallanAmt"+(ii));					    	  	    
    	  	    if(amt.value!='' && amt.value.length>0)
    	  	    {
	  	    		total = parseFloat(total) + parseFloat(amt.value);
	  	    	}
	  	    }
	  	    document.getElementById('challanAmtTotal').innerHTML = total;
	  	    document.forms[0].challanTotal.value=total;
		}
*/		
		document.getElementById("challanAmtTotal").innerHTML='<%=lStrTotalAmt%>';
		<%System.out.println("Value of toatl Amount at the end : " +lStrTotalAmt);%>
		</script>
	<input type="hidden" name="challanTotal" value="<%=lStrTotalAmt%>" />
</hdiits:form>
</body>
</html>
