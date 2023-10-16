
<%try{ %>
<%@ include file="../../core/include.jsp"%>
<%@page import="com.tcs.sgv.core.valueobject.ResultObject"%>
<%@page import="com.tcs.sgv.acl.service.UserElements"%>
<%@page import="com.tcs.sgv.acl.service.UserElement"%>
<!-- Added by Paurav -->
<%@page import="com.tcs.sgv.common.valueobject.TrnBillRegister"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ResourceBundle"%>
<!-- Ended By Paurav -->
<%@page import="java.util.Map"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<fmt:setLocale value='<%=session.getAttribute("locale")%>' />
<fmt:setBundle basename="resources.onlinebillprep.CommonAlerts" var="onlinebillprepAlerts" scope="application" />
<fmt:setBundle basename="resources.onlinebillprep.CommonLabels" var="onlinebillprepLabels" scope="application" />
<fmt:setBundle basename="resources.billproc.billproc" var="billprocLabels" scope="application" />
<fmt:setBundle basename="resources.onlinebillprep.OnlineBillConstants" var="OnlineBillConstants" scope="application" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<!-- Added by Ankit Bhatt-->
<c:set var="month" value="${resValue.monthNo}"></c:set>
<c:set var="year" value="${resValue.yearSel}"></c:set>
<c:set var="arrearBillNo" value="${resValue.arrearBillNo}"></c:set>
<!-- Added by Paurav -->
<c:set var="BillRegisterObj" value="${resultObj.resultValue.TrnBillRegister}"></c:set>
<!--  Ended by Paurav -->
<fmt:message key="DigiEnabled" var="DigFlag" bundle="${OnlineBillConstants}" />

<c:set var="demandCode" value="${resValue.demandNo}"></c:set>


<%
	TrnBillRegister billReg = (TrnBillRegister)pageContext.getAttribute("BillRegisterObj");
	if(billReg != null)
	{
		System.out.println("In Jsp for inner - outer");
	   	ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
	   	final long arrearbillTypeId=Integer.parseInt(resourceBundle.getString("arrearbillTypeId"));
		pageContext.setAttribute("arrearbillTypeId",arrearbillTypeId);
	    System.out.println("Bill Type===> "+arrearbillTypeId);
		Date billDate = billReg.getBillDate();
		long billNo=billReg.getBillNo();
		int   inx = billReg.getBillCntrlNo().indexOf('(');
		String BillNum = billReg.getBillCntrlNo().substring( inx+1, billReg.getBillCntrlNo().indexOf('(',inx+1) );
		pageContext.setAttribute("BillMonth",(billDate.getMonth()+1));
		pageContext.setAttribute("BillYear",(billDate.getYear()+1900));
		pageContext.setAttribute("billNo",billNo);
		pageContext.setAttribute("BillNum",BillNum);
		   System.out.println("Bill Num===> "+BillNum);
		System.out.println("Bill Month and Year Taken and billno=> "+billNo);
		
		
		
	}
%>

<c:if test="${DigFlag == 'YES'}">
	<c:choose>
		<c:when
			test="${resValue.EditBill != null && resValue.EditBill == 'N'}">
		</c:when>
		<c:otherwise>
			<%
				String lStrServer = request.getServerName();
				String lStrContext = request.getContextPath();
				int lIntPort = request.getServerPort();
				
				String lStrURL = "http://" + lStrServer + ":" + lIntPort + lStrContext ;
				lStrURL = lStrURL + "/TCSCADataSigner.cab#Version=-1,-1,-1,-1";
				
				String lStrLPKPath = lStrContext + "/TCSCADataSigner.lpk";	
			%>
			<OBJECT CLASSID="clsid:5220CB21-C88D-11cf-B347-00AA00A28331" VIEWASTEXT>
				<PARAM NAME="LPKPath" VALUE="<%=lStrLPKPath%>">
			</OBJECT>

			<OBJECT id=TCSCADataSigner1 style="LEFT: 0px; TOP: 0px"
				codeBase="<%=lStrURL%>"
				classid=clsid:E30F1B1E-AB22-4A25-9D98-9D453DCBCB9F VIEWASTEXT>
				<PARAM NAME="_Version" VALUE="65536">
				<PARAM NAME="_ExtentX" VALUE="2646">
				<PARAM NAME="_ExtentY" VALUE="1323">
				<PARAM NAME="_StockProps" VALUE="0">
			</OBJECT>
		</c:otherwise>
	</c:choose>
</c:if>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Online Bill</title>

<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/tabcontent.js"/>"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script src="script/common/prototype.js" language="JavaScript" type="text/javascript"></script>


<script type="text/javascript"><!--

	onerror=handleErr

		function handleErr()
		{
			//Handle the error here
			window.status="Done";
		return true;
		}
		
		function notNullValidation()
		{
			var isConfigStatus = "${resValue.isConfigStatus}";
			
			//Commented by Paurav to disable Employee validation
			if(isConfigStatus == 'Y')
			{
				if(validateBudDtls() && isValidBillData() && isValidPartiData()) //validateEmpDtls() && 
				{
					return true;
				}
			}
			else
			{
				if(validateBudDtls() && isValidPartiData()) //validateEmpDtls() && 
				{
					return true;
				}
			}
			
			return false;
		}	
		function showAttach()
		{
			if("${resValue.BillNo}" == "")
			{
				showAttachPage();
			}
			else
			{
				var url = 'ifms.htm?actionFlag=openAttachments&BillNo=${resValue.BillNo}';
				window.open(url,"_blank","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,top=100,left=150,width=800,height=400");
			} 
		}
		function Open_PrintBill()
		{
			var height = screen.height - 150;
	    	var width = screen.width - 50;
            //added by Ankit Bhatt.
            var URL = "ifms.htm?actionFlag=printOuter&billNo=${resValue.TrnBillRegister.billNo}";
            //ended by Ankit Bhatt.
			window.open(URL,"_blank","toolbar=no,location=no,directories=no,status=no,menubar=yes,scrollbars=yes,resizable=yes,height=600,width=800"); 			
		}
		
		//Added By varun for Arrear Report
		function Open_ArrearBill_Schedule()
		{
			var height = screen.height - 150;
	    	var width = screen.width - 50;  
	    	var URL = "ifms.htm?actionFlag=OuterArrearPrint&billNo=${resValue.TrnBillRegister.billNo}";
            window.open(URL,"_blank","toolbar=no,location=no,directories=no,status=no,menubar=yes,scrollbars=yes,resizable=yes,height=600,width=800"); 			
		}
		
		function Open_PrintCertificate()
		{
			var height = screen.height;
	    	var width = screen.width - 50;  
	    	
            var URL = "ifms.htm?actionFlag=printCertificate&billNo=${resValue.TrnBillRegister.billNo}";

			window.open(URL,"_blank","toolbar=no,location=no,directories=no,status=no,menubar=yes,scrollbars=yes,resizable=yes,height=900,width=800");
		}
		
		function approveDigiSign()
		{
			<c:if test='${DigFlag=="YES"}'>
				digiSign();
			</c:if>
		}
		
		function showChallan()
		{
			var billCat = document.getElementById("id_cmbBillType").value;
			if(billCat == "TC")
			{
				document.getElementById("spanChallanLabel1").style.display = "inline";
				showMjrHdFrmEDP();
			}
			else
			{
				hideChallan();
			}
		}
		
		function hideChallan()
		{	
			document.getElementById("spanChallanLabel1").style.display = "none";					
		}		
		
		// Function For remarks.jsp.............
		/*		JAVASCRIPT FUNCTION TO HIGHLIGHT FROM FOCUSED CONTROL  */
		function showHighLight(cntrl)
		{
		    cntrl.style.borderColor = "Red";
		}
		
		/*		JAVASCRIPT FUNCTION TO REMOVE HIGHLIGHT FROM FOCUSED CONTROL  */
		function hideHighLight(cntrl)
		{
		    cntrl.style.borderColor = "transparent";
		    cntrl.style.borderStyle = "inset";
		    cntrl.style.borderWidth = "1px";
		}


	 
		
		function ShowPayBill()
		{
			var height = screen.height - 100;
		   	var width = screen.width;
		   	var urlstring ;
		   	if("${arrearBillNo}" != null && "${arrearBillNo}" > 0)
		   		urlstring = "hrms.htm?actionFlag=reportService&reportCode=2&action=generateReport&FromParaPage=TRUE&backBtn=0&Month=${month}&Year=${year}&billTypePara=${arrearbillTypeId}&billNo=${resValue.TrnBillRegister.billNo}";
			else
		   		urlstring = "hdiits.htm?actionFlag=reportService&reportCode=3000003&action=generateReport&FromParaPage=TRUE&Month=${month}&Year=${year}&billNo=${BillNum}";
		   	var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=no,menubar=no,location=no,scrollbars=yes,top=0,left=0";
		   	//alert(urlstring);
		 	newWindow = window.open(urlstring, "frmViewOnlineBillchild", urlstyle);
		}


		function ShowOuter()
		{
			var height = screen.height - 100;
		   	var width = screen.width;
		   	var urlstring ;
		   	/*if("${arrearBillNo}" != null && "${arrearBillNo}" > 0)
		   		urlstring = "hrms.htm?actionFlag=reportService&reportCode=2&action=generateReport&FromParaPage=TRUE&backBtn=0&Month=${month}&Year=${year}&billTypePara=${arrearbillTypeId}&billNo=${resValue.TrnBillRegister.billNo}";
			else
		   		urlstring = "hdiits.htm?actionFlag=reportService&reportCode=3000003&action=generateReport&FromParaPage=TRUE&month=${month}&Year=${year}&billNo=${BillNum}";
		   	var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=no,menubar=no,location=no,scrollbars=yes,top=0,left=0";*/
		   	//alert(urlstring);
		   	urlstring = "./hrms.htm?actionFlag=getOuterData&BillNumber=${BillNum}&month=${month}&year=${year}";
		   	var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=no,menubar=no,location=no,scrollbars=yes,top=0,left=0";
		 	newWindow = window.open(urlstring, "frmViewOnlineBillchild", urlstyle);
		}
		
	--></script>
	
<script type="text/javascript" src="script/hrms/onlinebillprep/Common.js"> </script>

<style>
	.tabstyle {
		border-width: 5px 1px 1px 1px;
		border-color: #2065c0;
		border-style: solid;
	}
	
	legend {
		padding-left: 5px;
		padding-right: 5px;
		font-weight: normal;
		font-family: verdana;
		border-width: 0px 0px 1px 0px;
		border-color: #2065c0;
		border-style: solid;
	}
</style>

</head>

	<%

			ResultObject result=(ResultObject)request.getAttribute("result");
			System.out.println("result in createOnlineBill is: "+result);
			
			Map resultMap=(Map)result.getResultValue();
			System.out.println("resultMap in createOnlineBill is: "+resultMap);
			
			UserElements userActions = (UserElements)resultMap.get("homePageUserElements");
			
			long permission = 2;
			// permission is used for checking whether button is to be displayed or not.
			// permission = 1 is for showing and permission =2 is for hidden
			if(userActions!= null)
			{
			    UserElement userElement_En = userActions.getUserElements(110017);
			    
				if(userElement_En != null )
				{
					if(userElement_En.getPermissionObject() != null)
					{	
					    //assuming that permission will be same for all languages
						permission = userElement_En.getPermissionObject().getPermission();
						request.setAttribute("permission",permission);
					}				
				}
			}

	%>

<hdiits:form method="post" name="frmCreateOnlineBill" validate="true" encType="multipart/form-data"	action="ifms.htm?actionFlag=createOnlineBill&action=save">

	<fmt:message key="DigiEnabled" var="DigFlag" bundle="${OnlineBillConstants}" />
	<hdiits:hidden name="hidBillTypeId" id="hidBillTypeId"	default="${resValue.BillTypeId}" />
	<hdiits:hidden name="userAction" id="userAction" default="save" />
	<input name="hidSelRqstId" type="hidden" value="${resValue.SelReqId}" />
	<hdiits:hidden name="isNewFromRejected" id="isNewFromRejected"	default="${resValue.isNewFromRejected}" />
	<hdiits:hidden name="prevBillNo" id="prevBillNo" default="${resValue.prevBillNo}" />
	<hdiits:hidden name="hidTrnOnlineBillEmpPk" default="${resValue.EmpDtlsList[0].trnOnlinebillEmpId}" />
	<input type="hidden" name="objCount" value="${resValue.ShowBillVO.objCount}" />

	<c:if test="${resValue.isNewFromRejected != 'Y'}">
		<hdiits:hidden name="hidBillBudHdId" id="hidBillBudHdId" default="${resValue.TrnBillBudheadDtls.billBudId}" />
		<hdiits:hidden name="hidBillRemarksId" id="hidBillRemarksId" default="${resValue.TrnBillRemarks.rmrksId}" />
		<hdiits:hidden name="hidBillNo" id="hidBillNo" default="${resValue.TrnBillRegister.billNo}" />
		<input name="hidBillCntrlNo" type="hidden" value="${resValue.BillCntrNo}" />
	</c:if>

	<div id="statusBar" style="width: 50%; background-color: #BADCFD; display: none; font: bold 10px Arial"></div>	
	<div id="progressImage" style="display: none"></div>
	<div id="tabmenu" class="HLabel">
		<c:choose>
		<c:when test="${demandCode eq null}">
			<ul id="maintab" class="shadetabs">
				<li>
					<a href="#" rel="tcontent1"> <hdiits:caption caption="Payroll Parameters" captionid="PaybillPara" /></a></li>
				<li style="display: none;">
					<a href="#" rel="tcontent2"> <fmt:message key="BUD.DTLS" bundle="${onlinebillprepLabels}"></fmt:message></a></li>
				<li style="display: none;"> 
					<a href="javascript:void(0)" onclick="ShowPayBill()"><fmt:message key="BILL.HEADING" bundle="${onlinebillprepLabels}"></fmt:message> </a> </li>
				<li style="display: none;"> 
					<a href="javascript:void(0)" onclick="ShowOuter()"><fmt:message key="BILL.OUTER" bundle="${onlinebillprepLabels}"></fmt:message> </a> </li>	
				<li style="display: none;"> 
					<a href="#" rel="tcontent4"> <fmt:message key="BILL.REMARKS" bundle="${onlinebillprepLabels}"></fmt:message> </a> </li>
	    		<li id="spanChallanLabel1" style="display: none;"> 
	    			<a href="#" rel="tcontent5"> Challan </a> </li>
			</ul>
		</c:when>
		<c:otherwise>
			<ul id="maintab" class="shadetabs">
				<li>
					<a href="#" rel="tcontent1"> <hdiits:caption caption="Payroll Parameters" captionid="PaybillPara"/></a></li>
				<li>
					<a href="#" rel="tcontent2"> <fmt:message key="BUD.DTLS" bundle="${onlinebillprepLabels}"></fmt:message></a></li>
			
				<li>
					<a href="javascript:void(0)" onclick="ShowPayBill()"> <fmt:message key="BILL.HEADING" bundle="${onlinebillprepLabels}"></fmt:message></a></li>
					
				<li> 
					<a href="javascript:void(0)" onclick="ShowOuter()"><fmt:message key="BILL.OUTER" bundle="${onlinebillprepLabels}"></fmt:message> </a> </li>
					
				<li> 
					<a href="#" rel="tcontent4"> <fmt:message key="BILL.REMARKS" bundle="${onlinebillprepLabels}"></fmt:message> </a> </li>
					
				
	    		
	    		<li id="spanChallanLabel1" style="display: none;"> 
	    			<a href="#" rel="tcontent5"> Challan</a> </li>		
	    		<li>
	    			<a href="#" rel="tcontent6"> <hdiits:caption caption="Token No" captionid="Voucher No" /> </a> </li>		
			</ul>
		</c:otherwise>
		</c:choose>
	</div>
	
	
	
	<div class="tabcontentstyle">
		<!-- Added by Paurav for showing Paybill Head parameters Page -->
		<div id="tcontent1" class="tabcontent">
			<jsp:include page="/WEB-INF/jsp/hrms/eis/OuterPaybillPara.jsp" /></div>
	
 <!-- ------------------------------------------Employee Details------------------------------------------------------------- -->        
 	<!-- Id changed by Paurav from tcontent1 to tcontent5 -->
	<div id="tcontent5" class="tabcontent">
    	<hdiits:table align="center" width="100%">			
			<hdiits:tr>
				<hdiits:td>
					<c:if test="${resValue.TrnBillRegister != null}">
					<div align="right">
						<b> <hdiits:a href="javascript:Open_PrintBill()" caption="Print Outer"> </hdiits:a>  </b> <br /><br />						
					</div>
					<div align="left">
						<b> <hdiits:a href="javascript:Open_PrintCertificate()" caption="Print Certificate"> </hdiits:a>  </b> <br />	<br/>				
					</div>
					</c:if>
					<c:if test="${resValue.billStatus != null && resValue.billStatus != 'BCRTD' && resValue.billStatus != 'BAPRVD_DDO'}">
					<div align="right">						
						<b> Bill Control Number : </b> <c:out value="${resValue.BillCntrNo}" /> 
					</div>
					</c:if>
					<br /><jsp:include page="/WEB-INF/jsp/hrms/onlinebillprep/empDtls.jsp" />
				</hdiits:td>	
			</hdiits:tr>
		</hdiits:table>
	</div>
<!-- --------------------------------------Budget Details------------------------------------------------------- -->      
   	<div id="tcontent2" class="tabcontent">
 		<hdiits:table align="center" width="100%">			
			<hdiits:tr>
				<hdiits:td>
					<c:if test="${resValue.TrnBillRegister != null}">
					<div align="right">
				 <b> <hdiits:a href="javascript:Open_PrintBill()" caption="Print Outer"> </hdiits:a>  </b> <br /><br />						 
					</div>
					<div align="right">
						<b> <hdiits:a href="javascript:Open_PrintCertificate()" caption="Print Certificate"> </hdiits:a>  </b> <br />	<br/>				
					</div>
					</c:if>
					<c:if test="${resValue.billStatus != null && resValue.billStatus != 'BCRTD' && resValue.billStatus != 'BAPRVD_DDO'}">
					<div align="right">						
						<b> Bill Control Number : </b> <c:out value="${resValue.BillCntrNo}" /> 
					</div>
					</c:if>
					<jsp:include page="/WEB-INF/jsp/hrms/onlinebillprep/budDtls.jsp" />
					<br/>
					<jsp:include page="/WEB-INF/jsp/hrms/onlinebillprep/partyInfo.jsp" />						
				</hdiits:td>	
			</hdiits:tr>
		</hdiits:table>
	</div>
<!-- --------------------------------------Bill Details------------------------------------------------------- -->      
 	<div id="tcontent3" class="tabcontent">
		<hdiits:table align="center" width="100%">
		<hdiits:tr>
			<hdiits:td>
				<c:if test="${resValue.TrnBillRegister != null}">
					<div align="left"><b> <hdiits:a href="javascript:Open_PrintCertificate()" caption="Print Certificate"></hdiits:a></b></div>
					<div align="left"><b> <hdiits:a href="javascript:Open_PrintBill()" caption="Print Outer"></hdiits:a></b>
						<c:choose>
						<c:when test="${arrearBillNo ne null && arrearBillNo ge 0 }">
							<b><hdiits:a href="javascript:Open_ArrearBill_Schedule()" caption="Print Arrear Report"></hdiits:a></b>
							<jsp:include page="/hrms.htm?actionFlag=reportService&reportCode=2&action=generateReport&FromParaPage=TRUE&backBtn=0&Month=${month}&Year=${year}&billTypePara=${arrearbillTypeId}&billNo=${resValue.TrnBillRegister.billNo}"/>
						</c:when>
				<%--	<c:otherwise>

							<jsp:include page="/hrms.htm?actionFlag=reportService&reportCode=2&action=generateReport
										&FromParaPage=TRUE&backBtn=0&Month=${month}&Year=${year}&billNo=${resValue.TrnBillRegister.billNo}" />
						</c:otherwise>
--%>
					</c:choose>
					</div>
				</c:if>

			</hdiits:td>
		</hdiits:tr>
		</hdiits:table>
	</div>

<!-- --------------------------------------Remarks------------------------------------------------------- -->      
   	<div id="tcontent4" class="tabcontent">
 		<hdiits:table align="center" width="100%">			
			<hdiits:tr>
				<hdiits:td>
					<c:if test="${resValue.TrnBillRegister != null}">
					<div align="right">
						<b> <hdiits:a href="javascript:Open_PrintBill()" caption="Print Outer"> </hdiits:a>  </b> <br /><br />						
					</div>
					</c:if>
					<c:if test="${resValue.billStatus != null && resValue.billStatus != 'BCRTD' && resValue.billStatus != 'BAPRVD_DDO'}">
					<div align="right">						
						<b> Bill Control Number : </b> <c:out value="${resValue.BillCntrNo}" /> 
					</div>
					</c:if>
					<jsp:include page="/WEB-INF/jsp/hrms/billproc/remarksDetails.jsp" />
				</hdiits:td>	
			</hdiits:tr>
		</hdiits:table>
	</div>
<!-- --------------------------------------Challan-------------------------------------------------------- -->  
	<div id="tcontent5" class="tabcontent">
		<hdiits:table align="center" width="100%">			
			<hdiits:tr>
				<hdiits:td>
					<div align="right">
						<b> <hdiits:a href="ifms.htm?actionFlag=getBillData&billNo=${resValue.TrnBillRegister.billNo}" caption="Print Outer"> </hdiits:a>  </b> <br /><br />
						<b> Bill Number : </b> <c:out value="${resValue.BillCntrNo}" /> 
					</div>
					<c:choose>
						<c:when test="${resValue.challanFlag == 'Y'}">
							<jsp:include page="/WEB-INF/jsp/hrms/onlinebillprep/challanDetPost.jsp" />
						</c:when>
						<c:otherwise>
							<jsp:include page="/WEB-INF/jsp/hrms/onlinebillprep/rcptChallan.jsp" />
						</c:otherwise>
					</c:choose>
				</hdiits:td>	
			</hdiits:tr>
		</hdiits:table>
	</div>
	
<!-- -----------------------------------   token Number-------------------------------------------- -->     

	<div id="tcontent6" class="tabcontent">

			<jsp:include page="/WEB-INF/jsp/hrms/eis/voucherNo.jsp"/>

	</div>
	
<!-- ----------------------------------------------------------------------------------------------------- -->  
<%
		
		// permission is used for checking whether button is to be displayed or not.
		// permission = 1 is for showing and permission =2 is for hidden
		if(userActions!= null)
		{
		    UserElement userElement_En = userActions.getUserElements(110017);
		    
			if(userElement_En != null )
			{
				if(userElement_En.getPermissionObject() != null)
				{	
				    //assuming that permission will be same for all languages
					permission = userElement_En.getPermissionObject().getPermission();
					request.setAttribute("permission",permission);
				}				
			}
		}
	%>


 
	<div align="center">
		<c:if test="${resValue.billStatus == null || (resValue.billStatus != 'BAPRVD_DDO' && resValue.billStatus != 'BSNT_TO' 
					&& resValue.billStatus!='BCRTD' && (resValue.billStatus != 'BRJCT_AUD' || resValue.isNewFromRejected == 'Y'))}">
			<hdiits:button type="button" name="btnSave" value=" Save "onclick="saveBill();" />
		</c:if> 
		<c:if test="${permission == 1 && (resValue.billStatus != 'BAPRVD_DDO' && resValue.billStatus != 'BSNT_TO' 
					&& (resValue.billStatus != 'BRJCT_AUD' || resValue.isNewFromRejected == 'Y'))}">
			<input type="button" name="btnApprove" value=" Sign & Submit " onclick="approveBill()" class="bigbutton" />
		</c:if>
		<c:if test="${resValue.billStatus != 'BAPRVD_DDO' && resValue.billStatus != null && DigFlag =='YES'}">
			<hdiits:button type="button" name="btnVerify" value=" Verify " onclick="verifySavedBill()" />
		</c:if>
		 <%-- Added by Ankit Bhatt --%>
		 <c:choose>
			<c:when test="${resValue.TrnBillBudheadDtls.dmndNo ne null}">
				<hdiits:button type="button" name="btnClose" value=" Close " onclick="closeBill()" />
			</c:when>
			<c:otherwise>
				<hdiits:button type="button" name="btnClose" value=" Close " onclick="navigateToHomePage()" />
			</c:otherwise>
		</c:choose> <%-- Ended By Ankit Bhatt --%>
	</div>

	</div>

	<!--  Added by Keyur for Workflow - Starts -->
	<hdiits:hidden name="toPost" id="toPost" default="-1" />
	<hdiits:hidden name="toUser" id="toUser" default="-1" />
	<hdiits:hidden name="seltdTrsry" id="seltdTrsry" />
	<hdiits:hidden name="toLevel" id="toLevel" default="-1" />
	<hdiits:hidden name="SendTo" default="-1" />
	<hdiits:hidden name="ToHeirarchyRefId" default="-1" />
	<hdiits:hidden name="CurrBillStatus" default="BCRTD" />
	<hdiits:hidden name="isFromChqPrep" default="${resValue.isFromChqPrep}" />
	<!--  Added by Keyur for Workflow - Ends -->


	<!-- JAVA SCRIPT -->
	<script type="text/javascript">		
		initializetabcontent("maintab");
	</script>
</hdiits:form>
<% } catch(Exception e )
{
	e.printStackTrace();	
}%>