<%
try {
%>

<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>



<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/base64.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/statusbar.js"></script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<fmt:setBundle basename="resources.common.reports.hrmsReportLables" var="reportLable" scope="request" />
<fmt:setBundle basename="resources.common.reports.hrmsReportAlertLables" var="reportAlertLable" scope="request" />

<script type="text/javascript">

	function showdate() {
		var t = new Date;
		var day = t.getDate();
		var month = t.getMonth() + 1;
		var year = t.getFullYear();
		//if zeros are needed to keep date format correct!
		if (day < 10) day = "0" + day;
		if (month < 10) month = "0" + month;
		document.reportMain.toDate.value=day + '/' + month + '/' + year;
	}
	
	
	var hrmslabel = "<fmt:message key="report.hrms" bundle="${reportLable}" />";
	var pendinglabel= "<fmt:message key="report.pending" bundle="${reportLable}" />";
	var approvelabel = "<fmt:message key="report.approve" bundle="${reportLable}" />";
	var alllabel = "<fmt:message key="report.all" bundle="${reportLable}" />";

	var treelabel = new Array();
	treelabel[0] = "<fmt:message key="report.eprofile" bundle="${reportLable}" />";
	treelabel[1] = "<fmt:message key="report.family" bundle="${reportLable}" />";
	treelabel[2] = "<fmt:message key="report.nominee" bundle="${reportLable}" />";
	treelabel[3] = "<fmt:message key="report.qualification" bundle="${reportLable}" />";
	treelabel[4] = "<fmt:message key="report.changeempaddress" bundle="${reportLable}" />";
	treelabel[5] = "<fmt:message key="report.changeempprofile" bundle="${reportLable}" />";
	treelabel[6] = "<fmt:message key="report.leave" bundle="${reportLable}" />";
	treelabel[7] = "<fmt:message key="report.joinleave" bundle="${reportLable}" />";
	treelabel[8] = "<fmt:message key="report.applyleave" bundle="${reportLable}" />";
	treelabel[9] = "<fmt:message key="report.cancelleave" bundle="${reportLable}" />";
	treelabel[10] = "<fmt:message key="report.ltc" bundle="${reportLable}" />";
	treelabel[11] = "<fmt:message key="report.domesticTT" bundle="${reportLable}" />";
	treelabel[12] = "<fmt:message key="report.gpf" bundle="${reportLable}" />";
	treelabel[13] = "<fmt:message key="report.gpfadvance" bundle="${reportLable}" />";
	treelabel[14] = "<fmt:message key="report.gpfwithdraw" bundle="${reportLable}" />";
	treelabel[15] = "<fmt:message key="report.gpfsubscription" bundle="${reportLable}" />";
	treelabel[16] = "<fmt:message key="report.gpfnewacc" bundle="${reportLable}" />";
	treelabel[17] = "<fmt:message key="report.asset" bundle="${reportLable}" />";
	treelabel[18] = "<fmt:message key="report.assetsubmission" bundle="${reportLable}" />";
	treelabel[19] = "<fmt:message key="report.assetpurchasesell" bundle="${reportLable}" />";
	treelabel[20] = "<fmt:message key="report.quarter" bundle="${reportLable}" />";
	treelabel[21] = "<fmt:message key="report.noc" bundle="${reportLable}" />";
	treelabel[22] = "<fmt:message key="report.passportnoc" bundle="${reportLable}" />";
	treelabel[23] = "<fmt:message key="report.foreignvisitnoc" bundle="${reportLable}" />";
	treelabel[24] = "<fmt:message key="report.foreignvist" bundle="${reportLable}" />";
	treelabel[25] = "<fmt:message key="report.cessation" bundle="${reportLable}" />";
	treelabel[26] = "<fmt:message key="report.all" bundle="${reportLable}" />";
	treelabel[27] = "<fmt:message key="report.rta" bundle="${reportLable}" />";
	treelabel[28] = "<fmt:message key="report.fgntravel" bundle="${reportLable}" />";
	treelabel[29] = "<fmt:message key="report.fgntravelrequest" bundle="${reportLable}" />";
	treelabel[30] = "<fmt:message key="report.fgntravelreturn" bundle="${reportLable}" />";
	treelabel[31] = "<fmt:message key="report.grievance" bundle="${reportLable}" />";
	treelabel[32] = "<fmt:message key="report.pension" bundle="${reportLable}" />";
	treelabel[33] = "<fmt:message key="report.traveladvance" bundle="${reportLable}" />";
	treelabel[34] = "<fmt:message key="report.travelrequest" bundle="${reportLable}" />";
	treelabel[35] = "<fmt:message key="report.presanctionreimb" bundle="${reportLable}" />";
	treelabel[36] = "<fmt:message key="report.postfactoreimb" bundle="${reportLable}" />";
	treelabel[37] = "<fmt:message key="report.mrb" bundle="${reportLable}" />";
	treelabel[38] = "<fmt:message key="report.welfare" bundle="${reportLable}" />";
	//var treelabel = 
	treelabel[39] = "<fmt:message key="report.OnRequestTransfer" bundle="${reportLable}" />";
	treelabel[40] = "<fmt:message key="report.MRB" bundle="${reportLable}" />";
	treelabel[41] = "<fmt:message key="report.AdditionalPay" bundle="${reportLable}" />";
	treelabel[42] = "<fmt:message key="report.Deputation" bundle="${reportLable}" />";
	
	treelabel[43] = "<fmt:message key="report.AssetJoining" bundle="${reportLable}" />";
	treelabel[44] = "<fmt:message key="report.AssetIncome" bundle="${reportLable}" />";
	treelabel[45] = "<fmt:message key="report.AssetDeclarationOfYear" bundle="${reportLable}" />";
	
	treelabel[46] = "<fmt:message key="report.LoanAdvance" bundle="${reportLable}" />";

	treelabel[47] = "<fmt:message key="report.rtaFolder" bundle="${reportLable}" />";
	treelabel[48] = "<fmt:message key="report.rtaReimb" bundle="${reportLable}" />";
	treelabel[49] = "<fmt:message key="report.onReqRtaReimb" bundle="${reportLable}" />";
	
	
	var nod="-1";
	var apptype="-1";

function setNodeId(nodeId){

	apptype="-1";
	nod=nodeId;
	
	if(nod=='GPFAdvPend'){
		appname=330023;
		appstatus=1;
		apptype="f";
	}else if(nod=='GPFWithPend'){
		appname=330024;
		appstatus=1;
		apptype="f";
		
	}else if(nod=='ResignPend'){
		appname=300025;
		appstatus=1;
		apptype="f";
		
	}else if(nod=='AllPending'){
		appname=0;
		appstatus=1;
		apptype="b";	
		
	}else if(nod=='GPFAdvApprove'){
		appname=330023;
		appstatus=2;
		apptype="f";
		
	}else if(nod=='GPFWithApprove'){
		appname=330024;
		appstatus=2;
		apptype="f";
		
	}else if(nod=='ResignApprove'){
		appname=300025;
		appstatus=2;
		apptype="f";
		
	}else if(nod=='AllApproved'){
		appname=0;
		appstatus=2;
		apptype="b";
		
	}else if(nod=='All'){
		appname=0;
		appstatus=0;
		apptype="b";
		
	}else if(nod=='Pending'){
		
		nod="-1";
		
	}else if(nod=='Approve'){
		
		nod="-1";
		
	}else if(nod=='QtrPending'){
	
		appname=300013;
		appstatus=1;
		apptype="c";
		
	}else if(nod=='QtrApprove'){
	
		appname=300013;
		appstatus=2;
		apptype="c";
			
	}else if(nod=='NOCpassportPending'){
	
		appname=300010;
		appstatus=1;
		apptype="f";
		
	}else if(nod=='NOCpassportApprove'){
	
		appname=300010;
		appstatus=2;
		apptype="f";
	
	}else if(nod=='NOCforeignPending'){
	
		appname=300011;
		appstatus=1;
		apptype="f";
	
	}else if(nod=='NOCforeignApprove'){
	
		appname=300011;
		appstatus=2;
		apptype="f";
		
	}else if(nod=='ForeignVisitPending'){
	
		appname=300012;
		appstatus=1;
		apptype="f";
			
	}else if(nod=='ForeignVisitApprove'){
	
		appname=300012;
		appstatus=2;
		apptype="f";
		
	}

	else if(nod=='QualificationPending'){
	
		appname=300005;
		appstatus=1;
		apptype="c";
			
	}else if(nod=='QualificationApprove'){
	
		appname=300005;
		appstatus=2;
		apptype="c";
			
	}else if(nod=='NomineePending'){
	
		appname=300006;
		appstatus=1;
		apptype="c";
		
	}else if(nod=='NomineeApprove'){
	
		appname=300006;
		appstatus=2;
		apptype="c";
		
	}else if(nod=='FamilyPending'){
	
		appname=300007;
		appstatus=1;
		apptype="c";
			
	}else if(nod=='FamilyApprove'){
	
		appname=300007;
		appstatus=2;
		apptype="c";
		
	}else if(nod=='ChangeEmpProfilePending'){
	
		appname=300020;
		appstatus=1;
		apptype="c";
		
	}else if(nod=='ChangeEmpProfileApprove'){
	
		appname=300020;
		appstatus=2;
		apptype="c";
			
	}else if(nod=='ChangeEmpAddressPending'){
	
		appname=300021;
		appstatus=1;
		apptype="c";
			
	}else if(nod=='ChangeEmpAddressApprove'){
	
		appname=300021;
		appstatus=2;
		apptype="c";
		
	}else if(nod=='LTCPending'){
	
		appname=300015;
		appstatus=1;
		apptype="f";
		
	}else if(nod=='LTCApprove'){
	
		appname=300015;
		appstatus=2;
		apptype="f";
		
	}else if(nod=='AssetPending'){
	
		appname=320401;
		appstatus=1;
		apptype="c";
		
	}else if(nod=='AssetApprove'){
	
		appname=320401;
		appstatus=2;
		apptype="c";
		
	}else if(nod=='AssetPurchaseSellPending'){
	
		appname=320400;
		appstatus=1;
		apptype="c";
			
	}else if(nod=='AssetPurchaseSellApprove'){
	
		appname=320400;
		appstatus=2;
		apptype="c";
		
	}else if(nod=='TravelPreSanctionReimbPending'){
	
		appname=300128;
		appstatus=1;
		apptype="c";
		
	}else if(nod=='TravelPreSanctionReimbApprove'){
	
		appname=300128;
		appstatus=2;
		apptype="c";
			
	}else if(nod=='TravelAdvancePending'){
	
		appname=300126;
		appstatus=1;
		apptype="c";
			
	}else if(nod=='TravelAdvanceApprove'){
	
		appname=300126;
		appstatus=2;
		apptype="c";
			
	}else if(nod=='TravelPostFactoReimbApprove'){
	
		appname=300127;
		appstatus=2;
		apptype="c";
			
	}else if(nod=='TravelPostFactoReimbPending'){
	
		appname=300127;
		appstatus=1;
		apptype="c";
			
	}else if(nod=='TravelRequestApprove'){
	
		appname=300125;
		appstatus=2;
		apptype="c";
			
	}else if(nod=='TravelRequestPending'){
	
		appname=300125;
		appstatus=1;
		apptype="c";
			
	}else if(nod=='gpfNewAccPending'){
	
		appname=330026;
		appstatus=1;
		apptype="f";
			
	}else if(nod=='gpfNewAccApprove'){
	
		appname=330026;
		appstatus=2;
		apptype="f";
			
	}else if(nod=='ApplyLeavePending'){
	
		appname=300001;
		appstatus=1;
		apptype="c";
			
	}else if(nod=='ApplyLeaveApprove'){
	
		appname=300001;
		appstatus=2;
		apptype="c";
		
	}else if(nod=='CancelLeavePending'){
	
		appname=300002;
		appstatus=1;
		apptype="c";
			
	}else if(nod=='CancelLeaveApprove'){
	
		appname=300002;
		appstatus=2;
		apptype="c";
		
	}else if(nod=='JoinLeavePending'){
	
		appname=300003;
		appstatus=1;
		apptype="c";
		
	}else if(nod=='JoinLeaveApprove'){

		appname=300003;
		appstatus=2;
		apptype="c";
	}
	else if(nod=='ForeingPendRequest'){
	
		appname=300144;
		appstatus=1;
		apptype="f";
		
	}else if(nod=='ForeingAppRequest'){

		appname=300144;
		appstatus=2;
		apptype="f";
	}
	else if(nod=='ForeignPendReturn'){
	
		appname=300145;
		appstatus=1;
		apptype="f";
		
	}else if(nod=='ForeignAppReturn'){

		appname=300145;
		appstatus=2;
		apptype="f";
	}
	else if(nod=='GrievancePending'){
	
		appname=320200;
		appstatus=1;
		apptype="c";
		
	}else if(nod=='GrievanceApprove'){

		appname=320200;
		appstatus=2;
		apptype="c";
	}
	else if(nod=='RTAPend'){
	
		appname=300143;
		appstatus=1;
		apptype="f";
		
	}else if(nod=='RTAApprove'){

		appname=300143;
		appstatus=2;
		apptype="f";
	}
	else if(nod=='PensionPend'){
	
		appname=300016;
		appstatus=1;
		apptype="f";
		
	}else if(nod=='PensionApprove'){

		appname=300016;
		appstatus=2;
		apptype="f";
	}
	else if(nod=='MRBPending'){

		appname=380001;
		appstatus=1;
		apptype="f";
	}
	else if(nod=='MRBApprove'){

		appname=380001;
		appstatus=2;
		apptype="f";
	}	
	else if(nod=='WelfarePending'){

		appname=300009;
		appstatus=1;
		apptype="f";
	}
	else if(nod=='WelfareApprove'){

		appname=300009;
		appstatus=2;
		apptype="f";
	}
	
	else if(nod=='OnRequestTransferPending'){

		appname=331000;
		appstatus=1;
		apptype="f";
	}
	else if(nod=='OnRequestTransferApprove'){

		appname=331000;
		appstatus=2;
		apptype="f";
	}
	else if(nod=='DeputationPending'){

		appname=331108;
		appstatus=1;
		apptype="c";
	}
	else if(nod=='DeputationApprove'){

		appname=331108;
		appstatus=2;
		apptype="c";
	}
	else if(nod=='AdditionalPayPending'){

		appname=300004;
		appstatus=1;
		apptype="f";
	}
	else if(nod=='AdditionalPayApprove'){

		appname=300004;
		appstatus=2;
		apptype="f";
	}
	
	else if(nod=='AssetDeclarationJoiningPending'){

		appname=320402;
		appstatus=1;
		apptype="c";
	}
	else if(nod=='AssetDeclarationJoiningApprove'){

		appname=320402;
		appstatus=2;
		apptype="c";
	}
	
	else if(nod=='AssetIncomeDeclarationPending'){

		appname=320403;
		appstatus=1;
		apptype="c";
	}
	else if(nod=='AssetIncomeDeclarationApprove'){

		appname=320403;
		appstatus=2;
		apptype="c";
	}
	
	else if(nod=='MoveableAssetDeclarationPending'){

		appname=320404;
		appstatus=1;
		apptype="c";
	}
	else if(nod=='MoveableAssetDeclarationApprove'){

		appname=320404;
		appstatus=2;
		apptype="c";
	}
	
	else if(nod=='LoanAdvancePend'){

		appname=300800;
		appstatus=1;
		apptype="c";
	}
	else if(nod=='LoanAdvanceApprove'){

		appname=300800;
		appstatus=2;
		apptype="c";
	}

	else if(nod=='RTAReimbPend'){

		appname=300146;
		appstatus=1;
		apptype="f";
	}
	else if(nod=='RTAReimbApprove'){

		appname=300146;
		appstatus=2;
		apptype="f";
	}

	else if(nod=='OnReqRTAReimbPend'){

		appname=300147;
		appstatus=1;
		apptype="c";
	}
	else if(nod=='OnReqRTAReimbApprove'){

		appname=300147;
		appstatus=2;
		apptype="c";
	}
}

function alm()
{

	var objm =window.frames['applicationReport'].document.getElementById("nav");
	if(objm!=null)
	{
		window.frames['applicationReport'].document.getElementById("nav").style.display='none';		
		window.frames['applicationReport'].document.getElementById("header").style.display='none';
	}
}



function pendingSearch()
{
	if(nod=="-1" || apptype=="-1"){
		alert('Please Select Application');
		return ;
	}
	if(document.reportMain.fromDate.value==""){
		alert('Please Enter From Date');
		return ;
	}
	if(document.reportMain.toDate.value==""){
		alert('Please Enter To Date');
		return ;
	}
	if(compareDate(document.reportMain.fromDate.value,document.reportMain.toDate.value)<0){
		alert('Please Enter To Date less than From Date');
		document.reportMain.toDate.focus();
		return ;
	}
	/*if(document.reportMain.appliedDate.value==""){
		alert('Please Select Applied Date');
		return;
	}
	
	if(document.getElementById('operator').value=="-1"){
		alert('Please Select Operator');
		return;
	}
	
	if(document.getElementById('NoOfdays').value=="" || document.getElementById('NoOfdays').value<parseInt("0")){
		alert('Please Enter Valid No. of days');
		return;
	}*/
	
	showProgressbar();
	
	var pendFrmDt=document.reportMain.fromDate.value;
	var pendToDt=document.reportMain.toDate.value;
	//var noOfDays=document.reportMain.NoOfdays.value;
	//var operator=document.reportMain.operator.value;
	//var appliedDt=document.reportMain.appliedDate.value;
	
	myHTML='<html><head><title></title></head></html>';
	
	parent.document.frames['applicationReport'].document.open();
	parent.document.frames['applicationReport'].document.write(myHTML);
	parent.document.frames['applicationReport'].document.close();
	
	//top.frames['applicationReport'].location ="./hrms.htm?actionFlag=hrmsReport&appname="+appname+"&appstatus="+appstatus+"&fromdate="+pendFrmDt+"&todate="+pendToDt+"&NoOfDays="+noOfDays+"&operator="+operator+"&appliedDt="+appliedDt+"&apptype="+apptype;  
	top.frames['applicationReport'].location ="./hrms.htm?actionFlag=hrmsReport&appname="+appname+"&appstatus="+appstatus+"&fromdate="+pendFrmDt+"&todate="+pendToDt+"&apptype="+apptype;  
	
	window.setTimeout( "a1()", "10");
}

function approveSearch()
{
	if(nod=="-1" || apptype=="-1"){
		alert('Please Select Application');
		return;
	}
	if(document.reportMain.fromDate.value==""){
		alert('Please Enter From Date');
		return ;
	}
	if(document.reportMain.toDate.value==""){
		alert('Please Enter To Date');
		return ;
	}
	if(compareDate(document.reportMain.fromDate.value,document.reportMain.toDate.value)<0){
		alert('Please Enter To Date less than From Date');
		document.reportMain.toDate.focus();
		return ;
	}
 /*	if(document.reportMain.appliedDate.value==""){
		alert('Please Select Applied Date');
		return;
	}
		
	if(document.getElementById('operator2').value=="-1"){
		alert('Please Select Operator');
		return;
	}
	
	if(document.getElementById('NoOfdays2').value=="" || document.getElementById('NoOfdays2').value<parseInt("0")){
		alert('Please Enter Valid No. of days');
		return;
	}*/


	
	
	showProgressbar();
	//var noOfDays=document.reportMain.NoOfdays2.value;
	//var operator=document.reportMain.operator2.value;
	//var appliedDt=document.reportMain.appliedDate.value;
	var approveFrmDt=document.reportMain.fromDate.value;
	var approveToDt=document.reportMain.toDate.value;
	
	myHTML='<html><head><title></title></head></html>';
	
	parent.document.frames['applicationReport'].document.open();
	parent.document.frames['applicationReport'].document.write(myHTML);
	parent.document.frames['applicationReport'].document.close();
	
	top.frames['applicationReport'].location ="./hrms.htm?actionFlag=hrmsReport&appname="+appname+"&appstatus="+appstatus+"&fromdate="+approveFrmDt+"&todate="+approveToDt+"&apptype="+apptype;  
	window.setTimeout( "a1()", "10");
}

function AllSearch(){
   if(document.reportMain.fromDate.value==""){
		alert('Please Enter From Date');
		return ;
	}
	if(document.reportMain.toDate.value==""){
		alert('Please Enter To Date');
		return ;
	}
	if(compareDate(document.reportMain.fromDate.value,document.reportMain.toDate.value)<0){
		alert('Please Enter To Date less than From Date');
		document.reportMain.toDate.focus();
		return ;
	}
/*	if(document.reportMain.appliedDate.value==""){
		alert('Please Select Applied Date');
		return;
	}
	
	if(document.getElementById('operator3').value=="-1"){
		alert('Please Select Operator');
		return;
	}
	
	if(document.getElementById('NoOfdays3').value=="" || document.getElementById('NoOfdays3').value<parseInt("0")){
		alert('Please Enter Valid No. of days');
		return;
	}*/
	
	showProgressbar();
	myHTML='<html><head><title></title></head></html>';
	
	parent.document.frames['applicationReport'].document.open();
	parent.document.frames['applicationReport'].document.write(myHTML);
	parent.document.frames['applicationReport'].document.close();
	
	var approveFrmDt=document.reportMain.fromDate.value;
	var approveToDt=document.reportMain.toDate.value;
	
	//var noOfDays=document.reportMain.NoOfdays3.value;
	//var operator=document.reportMain.operator3.value;
	//var appliedDt=document.reportMain.appliedDate.value;

	
	//top.frames['applicationReport'].location = "./hrms.htm?actionFlag=hrmsReport&appname="+appname+"&appstatus="+appstatus+"&fromdate="+approveFrmDt+"&todate="+approveToDt+"&NoOfDays="+noOfDays+"&operator="+operator+"&appliedDt="+appliedDt+"&apptype="+apptype;  
	top.frames['applicationReport'].location = "./hrms.htm?actionFlag=hrmsReport&appname="+appname+"&appstatus="+appstatus+"&fromdate="+approveFrmDt+"&todate="+approveToDt+"&apptype="+apptype;  
	window.setTimeout( "a2()", "10");
}

function a1()
{				
	var obj=null;
	var obj =window.frames['applicationReport'].document.getElementById("nav");
	if(obj!=null)
	{
		hideProgressbar();
		window.frames['applicationReport'].document.getElementById("nav").style.display='none';		
		window.frames['applicationReport'].document.getElementById("header").style.display='none';
	}
	else
	{			
		window.setTimeout( "a1()", "10");
	}		
}

	function closeReportWindow()
	{
		var urlstyle="hdiits.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
		document.reportMain.action=urlstyle;
		document.reportMain.submit();
	}

</script>



<hdiits:form name="reportMain" validate="true" method="POST" encType="multipart/form-data" >
		
	<table id="mainTable" width="100%">
		<tr height="20%">
			<td width="30%" align="left" >
				<div id="reportTree12" style="overflow:auto; height:200px;width:250px;border:2px solid #c3daf9;"><jsp:include page="/WEB-INF/jsp/hrms/reports/TreeForReport.jsp"/></div>
			</td>
			
			<td width="70%" align="left">
			
				<table align="center" width="100%">
					<tr id="appliedDtTable" style="display:none" >
						<td width="25%"><b><fmt:message key="report.fromdate" /></b></td>
						<td width="25%" ><hdiits:dateTime name="fromDate" bundle="${reportLable}" captionid="report.fromdate" mandatory="true"/></td>
						<td width="25%"><b><fmt:message key="report.todate" /></b></td>
						<td width="25%" ><hdiits:dateTime name="toDate" bundle="${reportLable}" captionid="report.todate" mandatory="true"/></td>
					</tr>
							
					
				<tr ></tr>
				<tr ></tr>
				<tr ></tr>
					<tr id="pendsearchbtTable" style="display:none">
					<td ></td>
					
						<td align="right"> 
							<hdiits:button name="pendsearchbt"  id="pendsearchbt" type="button" value="Search" onclick="pendingSearch()"></hdiits:button>
						</td>
						<td align="left"> 
							<hdiits:button name="pendClosebt"  id="pendClosebt" type="button" value="Close" onclick="closeReportWindow()"></hdiits:button>
						</td>
						<td></td>
					</tr>
				
					<tr id="approvesearchbtTable" style="display:none">
					<td ></td>
					
						<td align="right">
							<hdiits:button name="approvesearchbt"  id="approvesearchbt" type="button" value="Search" onclick="approveSearch()"></hdiits:button>
						</td>
						<td align="left">
							<hdiits:button name="approveClosebt"  id="approveClosebt" type="button" value="Close" onclick="closeReportWindow()"></hdiits:button>
						</td>
						
						<td></td>
					</tr>	
			
					<tr id="AllsearchbtTable" style="display:none">
					<td ></td>
						<td align="right">
							<hdiits:button name="Allsearchbt"  id="Allsearchbt" type="button" value="Search" onclick="AllSearch()"></hdiits:button>
						</td>
						<td align="left">
							<hdiits:button name="AllClosebt"  id="AllClosebt" type="button" value="Close" onclick="closeReportWindow()"></hdiits:button>
						</td>
						
						<td></td>
					</tr>
				</table>
			
			</td>			
		</tr>
		
		<tr bgcolor="#456789">
			<td width="100%" colspan="3"></td>
			<td></td>
			<td></td>
		</tr>
	</table>
	
	<table width="100%" >
		<tr>
			<td width="100%">
				<iframe  scrolling="auto"  name="applicationReport" onload='alm()' id="applicationReport" src="" frameborder="0" frameborder="0" width="100%" height="400px" align="left"></iframe>
			</td>
		</tr>
	</table>
	
</hdiits:form>

<script>
	showdate();
</script>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
