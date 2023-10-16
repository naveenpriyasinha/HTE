var nodeId="-1";
var appname="-1";
var appstatus="-1";


function pendingDisplay(){
	//alert('Pending Display Method');
			document.getElementById('approveSinceTable').style.display='none';
			document.getElementById('approvesearchbtTable').style.display='none';
			document.getElementById('AllsearchbtTable').style.display='none';
			//document.getElementById('applicationTable').style.display='none';
			document.getElementById('AllSearchTable').style.display='none';
			document.getElementById('pendsearchbtTable').style.display='';
			document.getElementById('appliedDtTable').style.display='';
			document.getElementById('pendSinceTable').style.display='';

}

function approveDisplay(){
	//alert('Approve Display Method');
			document.getElementById('pendsearchbtTable').style.display='none';
			document.getElementById('pendSinceTable').style.display='none';
			document.getElementById('AllsearchbtTable').style.display='none';
			document.getElementById('AllSearchTable').style.display='none';
			//document.getElementById('applicationTable').style.display='none';
			document.getElementById('approveSinceTable').style.display='';
			document.getElementById('approvesearchbtTable').style.display='';
			document.getElementById('appliedDtTable').style.display='';
			
}
Ext.onReady(function(){

	// Note: For the purposes of following along with the tutorial, all 
	// new code should be placed inside this method.
	
//alert("Congratulations!  You have Ext configured correctly!");
	
	
	
var Tree = Ext.tree;
var selectedNode ;
    
    var tree = new Tree.TreePanel('tree-div', {
        animate:true, 
		enableDD:false,
        containerScroll: true,
		rootVisible:true,
		lines:true
    });

    // set the root node
    var root = new Tree.TreeNode({
    	"cls" : "folder",
        text: '<b>HRMS</b>',
        id:'source'
    });
   
	
	var node1 = new Tree.TreeNode( { "cls" : "folder", "text": '<b>Pending</b>', "id": 'Pending'});
	var node2 = new Tree.TreeNode( { "cls" : "folder", "text": '<b>Approved</b>', "id": 'Approve'});
	var node3 = new Tree.TreeNode( { "cls" : "folder", "text": '<b>All</b>', "id": 'All'});
	
	var TravelPend = new Tree.TreeNode( { "cls" : "folder", "text": '<b>Travel</b>', "id": 'TravelPendFolder'});
	var GPFPend = new Tree.TreeNode( { "cls" : "folder", "text": '<b>GPF</b>', "id": 'GPFPendFolder'});
	var NOCPend = new Tree.TreeNode( { "cls" : "folder", "text": '<b>NOC</b>', "id": 'NOCPendFolder'});
	var LeavePend = new Tree.TreeNode( { "cls" : "folder", "text": '<b>Leave</b>', "id": 'LeavePendFolder'});
	var EprofilePend = new Tree.TreeNode( { "cls" : "folder", "text": '<b>E Profile</b>', "id": 'EprofilePendFolder'});
	var AssetPend = new Tree.TreeNode( { "cls" : "folder", "text": '<b>Asset</b>', "id": 'AssetPendFolder'});
	
	var TravelApp = new Tree.TreeNode( { "cls" : "folder", "text": '<b>Travel</b>', "id": 'TravelAppFolder'});
	var GPFApp = new Tree.TreeNode( { "cls" : "folder", "text": '<b>GPF</b>', "id": 'GPFAppFolder'});
	var NOCApp = new Tree.TreeNode( { "cls" : "folder", "text": '<b>NOC</b>', "id": 'NOCAppFolder'});
	var LeaveApp = new Tree.TreeNode( { "cls" : "folder", "text": '<b>Leave</b>', "id": 'LeaveAppFolder'});
	var EprofileApp = new Tree.TreeNode( { "cls" : "folder", "text": '<b>E Profile</b>', "id": 'EprofileAppFolder'});
	var AssetApp = new Tree.TreeNode( { "cls" : "folder", "text": '<b>Asset</b>', "id": 'AssetAppFolder'});
	
	
	var pend1 = new Tree.TreeNode( { "cls" : "file", "text": '<b>GPF Advance</b>', "id": 'GPFAdvPend'});
	var pend2 = new Tree.TreeNode( { "cls" : "file", "text": '<b>GPF Withdraw</b>', "id": 'GPFWithPend'});
	var pend3 = new Tree.TreeNode( { "cls" : "file", "text": '<b>GPF Subscription</b>', "id": 'GPFSubPend'});
	var gpfNewAccPend = new Tree.TreeNode( { "cls" : "file", "text": '<b>GPF New Account</b>', "id": 'gpfNewAccPending'});
	var pend4 = new Tree.TreeNode( { "cls" : "file", "text": '<b>Resignation</b>', "id": 'ResignPend'});
	var qtrAllotmentPend = new Tree.TreeNode( { "cls" : "file", "text": '<b>Quarter Allotment</b>', "id": 'QtrPending'});
	var passportNOCPend = new Tree.TreeNode( { "cls" : "file", "text": '<b>NOC Passport</b>', "id": 'NOCpassportPending'});
	var ForeignNOCPend = new Tree.TreeNode( { "cls" : "file", "text": '<b>NOC Foreign Visit</b>', "id": 'NOCforeignPending'});
	var ForeignVisitPend = new Tree.TreeNode( { "cls" : "file", "text": '<b>Foreign Visit Report</b>', "id": 'ForeignVisitPending'});
	var QualificationPend = new Tree.TreeNode( { "cls" : "file", "text": '<b>Qualification</b>', "id": 'QualificationPending'});
	var NomineePend = new Tree.TreeNode( { "cls" : "file", "text": '<b>Nominee</b>', "id": 'NomineePending'});
	var FamilyPend = new Tree.TreeNode( { "cls" : "file", "text": '<b>Family</b>', "id": 'FamilyPending'});
	var ChangeEmpProfilePend = new Tree.TreeNode( { "cls" : "file", "text": '<b>Change Employee Profile</b>', "id": 'ChangeEmpProfilePending'});
	var ChangeEmpAddressPend = new Tree.TreeNode( { "cls" : "file", "text": '<b>Change Employee  Address</b>', "id": 'ChangeEmpAddressPending'});
	var LTCPend = new Tree.TreeNode( { "cls" : "file", "text": '<b>LTC</b>', "id": 'LTCPending'});
	var AssetPend1 = new Tree.TreeNode( { "cls" : "file", "text": '<b>Submission Of Actuals</b>', "id": 'AssetPending'});
	var AssetPend2 = new Tree.TreeNode( { "cls" : "file", "text": '<b>Permission to Purchase/Sell </b>', "id": 'AssetPurchaseSellPending'});
	var TravelReimbursPend = new Tree.TreeNode( { "cls" : "file", "text": '<b>Travel Reimbursment</b>', "id": 'TravelReimbursmentPending'});
	var TravelAdvancePend = new Tree.TreeNode( { "cls" : "file", "text": '<b>Travel Advance</b>', "id": 'TravelAdvancePending'});
	var TravelCancelPend = new Tree.TreeNode( { "cls" : "file", "text": '<b>Travel Cancellation</b>', "id": 'TravelCancellationPending'});
	var TravelRquestPend = new Tree.TreeNode( { "cls" : "file", "text": '<b>Travel Pre Sanction</b>', "id": 'TravelRequestPending'});
	var ApplyLeavePend = new Tree.TreeNode( { "cls" : "file", "text": '<b>Apply for leave</b>', "id": 'ApplyLeavePending'});
	var CancelLeavePend = new Tree.TreeNode( { "cls" : "file", "text": '<b>Cancel Leave</b>', "id": 'CancelLeavePending'});
	var JoinLeavePend = new Tree.TreeNode( { "cls" : "file", "text": '<b>Joining Leave</b>', "id": 'JoinLeavePending'});
	var pendAll = new Tree.TreeNode( { "cls" : "file", "text": '<b>All</b>', "id": 'AllPending'});
	
	
	var app1 = new Tree.TreeNode( { "cls" : "file", "text": '<b>GPF Advance</b>', "id": 'GPFAdvApprove'});
	var app2 = new Tree.TreeNode( { "cls" : "file", "text": '<b>GPF Withdraw</b>', "id": 'GPFWithApprove'});
	var app3 = new Tree.TreeNode( { "cls" : "file", "text": '<b>GPF Subscription</b>', "id": 'GPFSubApprove'});
	var gpfNewAccApp = new Tree.TreeNode( { "cls" : "file", "text": '<b>GPF New Account</b>', "id": 'gpfNewAccApprove'});
	var app4 = new Tree.TreeNode( { "cls" : "file", "text": '<b>Resignation</b>', "id": 'ResignApprove'});
	var qtrAllotmentApp = new Tree.TreeNode( { "cls" : "file", "text": '<b>Quarter Allotment</b>', "id": 'QtrApprove'});
	var passportNOCApp = new Tree.TreeNode( { "cls" : "file", "text": '<b>NOC Passport</b>', "id": 'NOCpassportApprove'});
	var ForeignNOCApp = new Tree.TreeNode( { "cls" : "file", "text": '<b>NOC Foreign Visit</b>', "id": 'NOCforeignApprove'});
	var ForeignVisitApp = new Tree.TreeNode( { "cls" : "file", "text": '<b>Foreign Visit Report</b>', "id": 'ForeignVisitApprove'});
	var QualificationApp = new Tree.TreeNode( { "cls" : "file", "text": '<b>Qualification</b>', "id": 'QualificationApprove'});
	var NomineeApp = new Tree.TreeNode( { "cls" : "file", "text": '<b>Nominee</b>', "id": 'NomineeApprove'});
	var FamilyApp = new Tree.TreeNode( { "cls" : "file", "text": '<b>Family</b>', "id": 'FamilyApprove'});
	var ChangeEmpProfileApp = new Tree.TreeNode( { "cls" : "file", "text": '<b>Change Employee Profile</b>', "id": 'ChangeEmpProfileApprove'});
	var ChangeEmpAddressApp = new Tree.TreeNode( { "cls" : "file", "text": '<b>Change Employee  Address</b>', "id": 'ChangeEmpAddressApprove'});
	var LTCApp = new Tree.TreeNode( { "cls" : "file", "text": '<b>LTC</b>', "id": 'LTCApprove'});
	var AssetApp1 = new Tree.TreeNode( { "cls" : "file", "text": '<b>Submission Of Actuals</b>', "id": 'AssetApprove'});
	var AssetApp2 = new Tree.TreeNode( { "cls" : "file", "text": '<b>Permission to Purchase/Sell </b>', "id": 'AssetPurchaseSellApprove'});
	var TravelReimbursApp = new Tree.TreeNode( { "cls" : "file", "text": '<b>Travel Reimbursment</b>', "id": 'TravelReimbursmentApprove'});
	var TravelAdvanceApp = new Tree.TreeNode( { "cls" : "file", "text": '<b>Travel Advance</b>', "id": 'TravelAdvanceApprove'});
	var TravelCancelApp = new Tree.TreeNode( { "cls" : "file", "text": '<b>Travel Cancellation</b>', "id": 'TravelCancellationApprove'});
	var TravelRequestApp = new Tree.TreeNode( { "cls" : "file", "text": '<b>Travel Pre Sanction</b>', "id": 'TravelRequestApprove'});
	var ApplyLeaveApp = new Tree.TreeNode( { "cls" : "file", "text": '<b>Apply for leave</b>', "id": 'ApplyLeaveApprove'});
	var CancelLeaveApp = new Tree.TreeNode( { "cls" : "file", "text": '<b>Cancel Leave</b>', "id": 'CancelLeaveApprove'});
	var JoinLeaveApp = new Tree.TreeNode( { "cls" : "file", "text": '<b>Joining Leave</b>', "id": 'JoinLeaveApprove'});
	var approveAll = new Tree.TreeNode( { "cls" : "file", "text": '<b>All</b>', "id": 'AllApproved'});
		
	root.appendChild(node1);
	root.appendChild(node2);
	root.appendChild(node3);
	
	//Pending
	node1.appendChild(TravelPend);
	node1.appendChild(GPFPend);
	node1.appendChild(NOCPend);	
	node1.appendChild(LeavePend);	
	node1.appendChild(EprofilePend);	
	node1.appendChild(AssetPend);	
	
	TravelPend.appendChild(TravelRquestPend);
	TravelPend.appendChild(TravelAdvancePend);
	TravelPend.appendChild(TravelReimbursPend);
	TravelPend.appendChild(TravelCancelPend);
	GPFPend.appendChild(gpfNewAccPend);
	GPFPend.appendChild(pend1);
	GPFPend.appendChild(pend3);
	GPFPend.appendChild(pend2);
	NOCPend.appendChild(passportNOCPend);
	NOCPend.appendChild(ForeignNOCPend);
	NOCPend.appendChild(ForeignVisitPend);
	LeavePend.appendChild(ApplyLeavePend);	
	LeavePend.appendChild(CancelLeavePend);
	LeavePend.appendChild(JoinLeavePend);
	EprofilePend.appendChild(ChangeEmpProfilePend);
	EprofilePend.appendChild(ChangeEmpAddressPend);
	AssetPend.appendChild(AssetPend1);
	AssetPend.appendChild(AssetPend2);
	node1.appendChild(pend4);
	node1.appendChild(qtrAllotmentPend);
	node1.appendChild(QualificationPend);
	node1.appendChild(NomineePend);
	node1.appendChild(FamilyPend);
	node1.appendChild(LTCPend);
	node1.appendChild(pendAll);
	
	//Approve
	node2.appendChild(TravelApp);
	node2.appendChild(GPFApp);
	node2.appendChild(NOCApp);	
	node2.appendChild(LeaveApp);	
	node2.appendChild(EprofileApp);	
	node2.appendChild(AssetApp);
	
	
	TravelApp.appendChild(TravelRequestApp);
	TravelApp.appendChild(TravelAdvanceApp);
	TravelApp.appendChild(TravelReimbursApp);
	TravelApp.appendChild(TravelCancelApp);
	GPFApp.appendChild(gpfNewAccApp);
	GPFApp.appendChild(app1);
	GPFApp.appendChild(app2);
	GPFApp.appendChild(app3);
	NOCApp.appendChild(passportNOCApp);
	NOCApp.appendChild(ForeignNOCApp);
	NOCApp.appendChild(ForeignVisitApp);
	LeaveApp.appendChild(ApplyLeaveApp);	
	LeaveApp.appendChild(CancelLeaveApp);
	LeaveApp.appendChild(JoinLeaveApp);
	EprofileApp.appendChild(ChangeEmpProfileApp);
	EprofileApp.appendChild(ChangeEmpAddressApp);
	AssetApp.appendChild(AssetApp1);
	AssetApp.appendChild(AssetApp2);
	node2.appendChild(app4);
	node2.appendChild(qtrAllotmentApp);
	node2.appendChild(QualificationApp);
	node2.appendChild(NomineeApp);
	node2.appendChild(FamilyApp);
	node2.appendChild(LTCApp);
	node2.appendChild(approveAll);
	
    // render the tree
    tree.setRootNode(root);
    tree.render();
	tree.root.expand();
	tree.selectPath("folder");
	selectedNode = root;
	tree.on("click", function(node, e) {

		// In this function you have full access to the node that was clicked.
		nodeId=node.id;
		setNodeId(nodeId);
		
		var selectedId = node.attributes.id;
		var text = node.text;
		myHTML='<html><head><title></title></head></html>';
		
		parent.document.frames['applicationReport'].document.open();
		parent.document.frames['applicationReport'].document.write(myHTML);
		parent.document.frames['applicationReport'].document.close();
		
		if(node.id=='Pending')
		{
			//alert('In pending');
			pendingDisplay();
			return;
			
		}else if(node.id=='Approve'){
		
			approveDisplay();
			return;
			
		}else if(node.id=='AllPending'){
		
			//document.getElementsByName('appliedDate').value="";
			pendingDisplay();
			return;
			
		}else if(node.id=='AllApproved'){
			//document.getElementsByName('appliedDate').value="";
			approveDisplay();
			return;
			
		}else if(node.id=='All'){
			//alert('Inside all');
			//document.getElementsByName('appliedDate').value="";
			document.getElementById('pendsearchbtTable').style.display='none';
			document.getElementById('approvesearchbtTable').style.display='none';
			document.getElementById('pendSinceTable').style.display='none';
			document.getElementById('approveSinceTable').style.display='none';
			document.getElementById('appliedDtTable').style.display='';
			document.getElementById('AllSearchTable').style.display='';
			document.getElementById('AllsearchbtTable').style.display='';
			
			return;
			
		}else if(node.id=='GPFAdvPend'){
			
			pendingDisplay();
			return;
			
		}else if(node.id=='GPFAdvApprove'){
			
			approveDisplay();
			return;
			
			
		}else if(node.id=='GPFWithPend'){
			
			pendingDisplay();
			return;
			
		}else if(node.id=='GPFWithApprove'){
			
			approveDisplay();
			return;
			
		}else if(node.id=='GPFSubPend'){
			
			pendingDisplay();
			return;
			
		}else if(node.id=='GPFSubApprove'){
			
			approveDisplay();
			return;
			
		}else if(node.id=='ResignPend'){

			pendingDisplay();
			return;
			
		}else if(node.id=='ResignApprove'){
		
			approveDisplay();
			return;
			
		}else if(node.id=='QtrPending'){
			
			pendingDisplay();
			return;
			
		}else if(node.id=='QtrApprove'){
			
			approveDisplay();
			return;
		}else if(node.id=='NOCpassportPending'){
			
			pendingDisplay();
			return;
			
		}else if(node.id=='NOCpassportApprove'){
		
			approveDisplay();
			return;
			
		}else if(node.id=='NOCforeignPending'){
			
			pendingDisplay();
			return;
			
		}else if(node.id=='NOCforeignApprove'){
		
			approveDisplay();
			return;
			
		}else if(node.id=='ForeignVisitPending'){
			
			pendingDisplay();
			return;
			
		}else if(node.id=='ForeignVisitApprove'){
		
			approveDisplay();
			return;
		}else if(node.id=='QualificationPending'){
		
			pendingDisplay();
			return;
		}else if(node.id=='QualificationApprove'){
		
			approveDisplay();
			return;
		}else if(node.id=='NomineePending'){
		
			pendingDisplay();
			return;
		}else if(node.id=='NomineeApprove'){
		
			approveDisplay();
			return;
		}else if(node.id=='FamilyPending'){
		
			pendingDisplay();
			return;
			
		}else if(node.id=='FamilyApprove'){
			approveDisplay();
			return;
			
		}else if(node.id=='ChangeEmpProfilePending'){
		
			pendingDisplay();
			return;
			
		}else if(node.id=='ChangeEmpProfileApprove'){
			approveDisplay();
			return;
			
		}else if(node.id=='ChangeEmpAddressPending'){
		
			pendingDisplay();
			return;
			
		}else if(node.id=='ChangeEmpAddressApprove'){
			approveDisplay();
			return;
			
		}else if(node.id=='LTCPending'){
		
			pendingDisplay();
			return;
			
		}else if(node.id=='LTCApprove'){
		
			approveDisplay();
			return;
			
		}else if(node.id=='AssetPending'){
		
			pendingDisplay();
			return;
			
		}else if(node.id=='AssetApprove'){
		
			approveDisplay();
			return;
			
		}else if(node.id=='AssetPurchaseSellPending'){
		
			pendingDisplay();
			return;
			
		}else if(node.id=='AssetPurchaseSellApprove'){
		
			approveDisplay();
			return;
		}else if(node.id=='TravelReimbursmentPending'){
		
			pendingDisplay();
			return;
			
		}else if(node.id=='TravelReimbursmentApprove'){
		
			approveDisplay();
			return;
			
		}else if(node.id=='TravelAdvancePending'){
		
			pendingDisplay();
			return;
		}else if(node.id=='TravelAdvanceApprove'){
		
			approveDisplay();
			return;
			
		}else if(node.id=='TravelCancellationPending'){
		
			pendingDisplay();
			return;
			
		}else if(node.id=='TravelCancellationApprove'){
		
			approveDisplay();
			return;
			
		}else if(node.id=='TravelRequestPending'){
		
			pendingDisplay();
			return;
			
		}else if(node.id=='TravelRequestApprove'){
		
			approveDisplay();
			return;
			
		}else if(node.id=='gpfNewAccPending'){
		
			pendingDisplay();
			return;
			
		}else if(node.id=='gpfNewAccApprove'){
		
			approveDisplay();
			return;
		}
		else if(node.id=='ApplyLeavePending'){
		
			pendingDisplay();
			return;
			
		}else if(node.id=='ApplyLeaveApprove'){
		
			approveDisplay();
			return;
		}
		else if(node.id=='CancelLeavePending'){
		
			pendingDisplay();
			return;
			
		}else if(node.id=='CancelLeaveApprove'){
		
			approveDisplay();
			return;
		}
		else if(node.id=='JoinLeavePending'){
		
			pendingDisplay();
			return;
			
		}else if(node.id=='JoinLeaveApprove'){
		
			approveDisplay();
			return;
		}
		
		
		window.setTimeout( "a2()", "10");
		
		selectedNode = node;
	});
});

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