
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
		"http://www.w3.org/TR/html4/strict.dtd" >
<meta http-equiv="Pragma" content="no-cache">
		
<%@ page contentType="text/html;charset=UTF-8"%>
<%String menuName = request.getParameter("menuName"); %>

<script type="text/javascript" src="<c:url value="script/common/statusbar.js"/>"></script>

<fmt:setBundle basename="resources.common.reports.hrmsReportLables" var="reportLable" scope="request" />
<fmt:setBundle basename="resources.common.reports.hrmsReportAlertLables" var="reportAlertLable" scope="request" />


<html>
<head>
	<style type="text/css">
		@import "dojoTree/dijit/tests/css/dijitTests.css";
	</style>

	<script type="text/javascript" src="dojoTree/dojo/dojo.js"
		djConfig="parseOnLoad: true, isDebug: false"></script>
	<script type="text/javascript" src="dojoTree/dijit/tests/_testCommon.js"></script>

	<script language="JavaScript" type="text/javascript">
		dojo.require("dojo.data.ItemFileReadStore");
		dojo.require("dijit.Tree");
		dojo.require("dijit.ColorPalette");
		dojo.require("dijit.Menu");
		dojo.require("dojo.parser");
		dojo.require("dojo.dnd.common");
		dojo.require("dojo.dnd.Source");
			// scan page for widgets and instantiate them
	</script>

</head>
<body>

	<div dojoType="dojo.data.ItemFileReadStore" jsId="continentStore"
		url="hrmsReportTree.jsp?<%=menuName%>" style="overflow:scrolling;"></div>
	<div dojoType="dijit.tree.ForestStoreModel" jsId="continentModel" 
		store="continentStore" query="{type:'continent'}"
		rootId="ContinentRoot" rootLabel="Continents" childrenAttrs="children" style="overflow:scrolling;"></div>
		
	<div dojoType="dijit.Tree" id="tree2"
		model="continentModel" showRoot="false" openOnClick="false" style="height: 400px; width: 300px">

		<script type="dojo/connect">
			var menu = dijit.byId("tree_menu");
			// when we right-click anywhere on the tree, make sure we open the menu
			//menu.bindDomNode(this.domNode);

			dojo.connect(menu, "_openMyself", this, function(e){
				// get a hold of, and log out, the tree node that was the source of this open event
				var tn = dijit.getEnclosingWidget(e.target);
				console.debug(tn);

				// now inspect the data store item that backs the tree node:
				console.debug(tn.item);

				// contrived condition: if this tree node doesn't have any children, disable all of the menu items
				//menu.getChildren().forEach(function(i){ i.setDisabled(!tn.item.children); });

				// IMPLEMENT CUSTOM MENU BEHAVIOR HERE
			});
		</script>
<script type="dojo/method" event="getIconClass" args="item, opened">
        return (item == this.model.root || continentStore.getValue(item, "type") == "continent") ?
        (opened ? "customFolderOpenedIcon" : "customFolderClosedIcon") :
        "noteIcon";
</script>
		
<script type="dojo/method" event="onClick" args="item">
			
			//showProgressbar();
			//document.getElementById("dataFrame").src = continentStore.getValue(item, "url");

		//alert(continentStore.getValue(item, "name"));
		setNodeId(continentStore.getValue(item, "name"));
		var nodid=continentStore.getValue(item, "name");

		//myHTML='<html><head><title></title></head></html>';
		
		//parent.document.frames['applicationReport'].document.open();
		//parent.document.frames['applicationReport'].document.write(myHTML);
		//parent.document.frames['applicationReport'].document.close();
		
		if(nodid=='Pending')
		{
			//alert('In pending');
			pendingDisplay();
			return;
			
		}else if(nodid=='Approve'){
			approveDisplay();
			return;
			
		}else if(nodid=='AllPending'){
		
			//document.getElementsByName('appliedDate').value="";
			pendingDisplay();
			return;
			
		}else if(nodid=='AllApproved'){
			//document.getElementsByName('appliedDate').value="";
			approveDisplay();
			return;
			
		}else if(nodid=='All'){
			//alert('Inside all');
			//document.getElementsByName('appliedDate').value="";
			document.getElementById('pendsearchbtTable').style.display='none';
			document.getElementById('approvesearchbtTable').style.display='none';
		//	document.getElementById('pendSinceTable').style.display='none';
			//document.getElementById('approveSinceTable').style.display='none';
			document.getElementById('appliedDtTable').style.display='';
		//	document.getElementById('AllSearchTable').style.display='';
			document.getElementById('AllsearchbtTable').style.display='';
			
			return;
			
		}else if(nodid=='GPFAdvPend'){
			
			pendingDisplay();
			return;
			
		}else if(nodid=='GPFAdvApprove'){
			
			approveDisplay();
			return;
			
			
		}else if(nodid=='GPFWithPend'){
			
			pendingDisplay();
			return;
			
		}else if(nodid=='GPFWithApprove'){
			
			approveDisplay();
			return;
			
		}else if(nodid=='GPFSubPend'){
			
			pendingDisplay();
			return;
			
		}else if(nodid=='GPFSubApprove'){
			
			approveDisplay();
			return;
			
		}else if(nodid=='ResignPend'){

			pendingDisplay();
			return;
			
		}else if(nodid=='ResignApprove'){
		
			approveDisplay();
			return;
			
		}else if(nodid=='QtrPending'){
			
			pendingDisplay();
			return;
			
		}else if(nodid=='QtrApprove'){
			
			approveDisplay();
			return;
		}else if(nodid=='NOCpassportPending'){
			
			pendingDisplay();
			return;
			
		}else if(nodid=='NOCpassportApprove'){
		
			approveDisplay();
			return;
			
		}else if(nodid=='NOCforeignPending'){
			
			pendingDisplay();
			return;
			
		}else if(nodid=='NOCforeignApprove'){
		
			approveDisplay();
			return;
			
		}else if(nodid=='ForeignVisitPending'){
			
			pendingDisplay();
			return;
			
		}else if(nodid=='ForeignVisitApprove'){
		
			approveDisplay();
			return;
		}else if(nodid=='QualificationPending'){
		
			pendingDisplay();
			return;
		}else if(nodid=='QualificationApprove'){
		
			approveDisplay();
			return;
		}else if(nodid=='NomineePending'){
		
			pendingDisplay();
			return;
		}else if(nodid=='NomineeApprove'){
		
			approveDisplay();
			return;
		}else if(nodid=='FamilyPending'){
		
			pendingDisplay();
			return;
			
		}else if(nodid=='FamilyApprove'){
			approveDisplay();
			return;
			
		}else if(nodid=='ChangeEmpProfilePending'){
		
			pendingDisplay();
			return;
			
		}else if(nodid=='ChangeEmpProfileApprove'){
			approveDisplay();
			return;
			
		}else if(nodid=='ChangeEmpAddressPending'){
		
			pendingDisplay();
			return;
			
		}else if(nodid=='ChangeEmpAddressApprove'){
			approveDisplay();
			return;
			
		}else if(nodid=='LTCPending'){
		
			pendingDisplay();
			return;
			
		}else if(nodid=='LTCApprove'){
		
			approveDisplay();
			return;
			
		}else if(nodid=='AssetPending'){
		
			pendingDisplay();
			return;
			
		}else if(nodid=='AssetApprove'){
		
			approveDisplay();
			return;
			
		}else if(nodid=='AssetPurchaseSellPending'){
		
			pendingDisplay();
			return;
			
		}else if(nodid=='AssetPurchaseSellApprove'){
		
			approveDisplay();
			return;
		}else if(nodid=='gpfNewAccPending'){
		
			pendingDisplay();
			return;
			
		}else if(nodid=='gpfNewAccApprove'){
		
			approveDisplay();
			return;
		}
		else if(nodid=='ApplyLeavePending'){
		
			pendingDisplay();
			return;
			
		}else if(nodid=='ApplyLeaveApprove'){
		
			approveDisplay();
			return;
		}
		else if(nodid=='CancelLeavePending'){
		
			pendingDisplay();
			return;
			
		}else if(nodid=='CancelLeaveApprove'){
		
			approveDisplay();
			return;
		}
		else if(nodid=='JoinLeavePending'){
		
			pendingDisplay();
			return;
			
		}else if(nodid=='JoinLeaveApprove'){
		
			approveDisplay();
			return;
		}
		else if(nodid=='RTAPend'){
		
			pendingDisplay();
			return;
			
		}else if(nodid=='RTAApprove'){
		
			approveDisplay();
			return;
		}else if(nodid=='GrievancePending'){
		
			pendingDisplay();
			return;
			
		}else if(nodid=='GrievanceApprove'){
		
			approveDisplay();
			return;
		}else if(nodid=='ForeingPendRequest'){
		
			pendingDisplay();
			return;
			
		}else if(nodid=='ForeingAppRequest'){
		
			approveDisplay();
			return;
		}else if(nodid=='ForeignPendReturn'){
		
			pendingDisplay();
			return;
			
		}else if(nodid=='ForeignAppReturn'){
		
			approveDisplay();
			return;
		}else if(nodid=='PensionPend'){
		
			pendingDisplay();
			return;
			
		}else if(nodid=='PensionApprove'){

			approveDisplay();
			return;
		}
		
		else if(nodid=='TravelRequestPending'){

			pendingDisplay();
			return;
		}else if(nodid=='TravelRequestApprove'){

			approveDisplay();
			return;
		}
		else if(nodid=='TravelAdvancePending'){

			pendingDisplay();
			return;
		}else if(nodid=='TravelAdvanceApprove'){

			approveDisplay();
			return;
		}
		else if(nodid=='TravelPreSanctionReimbPending'){

			pendingDisplay();
			return;
		}else if(nodid=='TravelPreSanctionReimbApprove'){

			approveDisplay();
			return;
		}
		else if(nodid=='TravelPostFactoReimbApprove'){

			approveDisplay();
			return;
		}else if(nodid=='TravelPostFactoReimbPending'){

			pendingDisplay();
			return;
		}

		else if(nodid=='MRBApprove'){

			approveDisplay();
			return;
		}else if(nodid=='MRBPending'){

			pendingDisplay();
			return;
		}

		else if(nodid=='WelfareApprove'){

			approveDisplay();
			return;
		}else if(nodid=='WelfarePending'){

			pendingDisplay();
			return;
		}
		
		else if(nodid=='OnRequestTransferApprove'){

			approveDisplay();
			return;
		}else if(nodid=='OnRequestTransferPending'){

			pendingDisplay();
			return;
		}
		else if(nodid=='DeputationApprove'){

			approveDisplay();
			return;
		}else if(nodid=='DeputationPending'){

			pendingDisplay();
			return;
		}
		else if(nodid=='AdditionalPayApprove'){

			approveDisplay();
			return;
		}else if(nodid=='AdditionalPayPending'){

			pendingDisplay();
			return;
		}

		else if(nodid=='AssetDeclarationJoiningApprove'){

			approveDisplay();
			return;
		}else if(nodid=='AssetDeclarationJoiningPending'){

			pendingDisplay();
			return;
		}
		else if(nodid=='AssetIncomeDeclarationApprove'){

			approveDisplay();
			return;
		}else if(nodid=='AssetIncomeDeclarationPending'){

			pendingDisplay();
			return;
		}
		else if(nodid=='MoveableAssetDeclarationApprove'){

			approveDisplay();
			return;
		}else if(nodid=='MoveableAssetDeclarationPending'){

			pendingDisplay();
			return;
		}

		else if(nodid=='LoanAdvancePend'){

			pendingDisplay();
			return;
		}else if(nodid=='LoanAdvanceApprove'){

			approveDisplay();
			return;
		}

		else if(nodid=='RTAReimbPend'){

			pendingDisplay();
			return;
		}else if(nodid=='RTAReimbApprove'){

			approveDisplay();
			return;
		}

		else if(nodid=='OnReqRTAReimbPend'){

			pendingDisplay();
			return;
		}else if(nodid=='OnReqRTAReimbApprove'){

			approveDisplay();
			return;
		}

		window.setTimeout( "a2()", "10");
		//selectedNode = node;
</script>

<script type="text/javascript">
	function a2()
	{
		var obj=null;
		obj=window.frames['applicationReport'].document.getElementById("nav");
					
		if(obj!=null)
		{
			hideProgressbar();
			
			top.frames['applicationReport'].document.getElementById("nav").style.display='none';		
			top.frames['applicationReport'].document.getElementById("header").style.display='none';
			obj=null;
		}
		else
		{
			window.setTimeout( "a2()", "10");
		}	
	}
	
	function pendingDisplay(){
		
				//document.getElementById('approveSinceTable').style.display='none';
				document.getElementById('approvesearchbtTable').style.display='none';
				document.getElementById('AllsearchbtTable').style.display='none';
				//document.getElementById('applicationTable').style.display='none';
				//document.getElementById('AllSearchTable').style.display='none';
				document.getElementById('pendsearchbtTable').style.display='';
				document.getElementById('appliedDtTable').style.display='';
				//document.getElementById('pendSinceTable').style.display='';
	
	}

	function approveDisplay(){
		
				document.getElementById('pendsearchbtTable').style.display='none';
			//	document.getElementById('pendSinceTable').style.display='none';
				document.getElementById('AllsearchbtTable').style.display='none';
			//	document.getElementById('AllSearchTable').style.display='none';
				//document.getElementById('applicationTable').style.display='none';
			//	document.getElementById('approveSinceTable').style.display='';
				document.getElementById('approvesearchbtTable').style.display='';
				document.getElementById('appliedDtTable').style.display='';
				
	}
</script>	
</div>

</body>
</html>




