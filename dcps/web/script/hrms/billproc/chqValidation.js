//Chq Cancallation
function validateChqCncl()
		{
				if(document.forms[0].txtNewChqNo.value=='-')
				{
					
					document.forms[0].txtChqNo.value ="";
					document.forms[0].txtNewChqNo.value ="";
					document.forms[0].txtChqAmt.value="";
					document.forms[0].txtPartyName.value="";
					document.forms[0].txtChqDate.value="";
					alert('Cheque Not Found');
					return;
				}
				
				if(document.forms[0].txtNewChqNo.value=='CANCELLED')
				{
					
					document.forms[0].txtNewChqNo.value ="";
					document.forms[0].txtChqAmt.value="";
					document.forms[0].txtPartyName.value="";
					document.forms[0].txtChqDate.value="";
					alert('Cheque Already Cancel');
					return;
				}
				
				if(document.forms[0].txtNewChqNo.value=='CLEARED')
				{
					
					document.forms[0].txtNewChqNo.value ="";
					document.forms[0].txtChqAmt.value="";
					document.forms[0].txtPartyName.value="";
					document.forms[0].txtChqDate.value="";
					alert('Cheque Already Cleared');
					return;
				}
				
				if(document.forms[0].txtPartyName.value=='')
				{
					
					alert('Cheque Not Found');
					return false;
				}
				document.forms[0].btnCancle.disabled=false;
		}
		
		
		function disableRename()
		{
			alert('Rename Sucessfully');
		}
		
		
      	function cancleChq()
      	{
      		document.forms[0].action = "ifms.htm?actionFlag=chqCancleDetail";
			document.forms[0].submit();
			alert('Cheque Cancel Sucessfully');
      	}
      	
      	function genAdvice()
      	{
   			window.open("ifms.htm?actionFlag=generateAdvice&advFlg=1&chkbox="+document.getElementById("chkbox").value);
      	}
//Chq Cancallation


// chq Duplication
 function genChq()
	 {
		var url ="ifms.htm?actionFlag=generateChequePDF&Chqrename=chqRen&Cheques="+document.getElementById("chkbox").value; 
		window.open(url,'_blank','');
	 }
	 
	 function newChq()
	 {	
	    
	 	document.forms[0].action = "ifms.htm?viewName=chqRename";
		document.forms[0].submit();
	 }
	 function newChq()
	 {	
	    
	 	document.forms[0].action = "ifms.htm?viewName=chqRename";
		document.forms[0].submit();
	 }
	 function validateChqDupl()
		{
				if(document.forms[0].txtNewChqNo.value=='-')
				{
					
					document.forms[0].txtChqNo.value ="";
					document.forms[0].txtNewChqNo.value ="";
					document.forms[0].txtChqAmt.value="";
					document.forms[0].txtPartyName.value="";
					document.forms[0].txtChqDate.value="";
					alert('Cheque Not Found');
					return;
				}
				if(document.forms[0].txtNewChqNo.value=='CANCELLED')
				{
					
					document.forms[0].txtNewChqNo.value ="";
					document.forms[0].txtChqAmt.value="";
					document.forms[0].txtPartyName.value="";
					document.forms[0].txtChqDate.value="";
					alert('Cheque Already Cancel');
					return;
				}
				if(document.forms[0].txtNewChqNo.value=='CLEARED')
				{

					document.forms[0].txtNewChqNo.value ="";
					document.forms[0].txtChqAmt.value="";
					document.forms[0].txtPartyName.value="";
					document.forms[0].txtChqDate.value="";
					alert('Cheque Already Cleared');
					return;
				}
				
				document.forms[0].btnRename.disabled=false;
		}
		
		function disableDupl()
		{
			var msgTxt = document.getElementById("MessageStatus").value;
			if(msgTxt != null && msgTxt.length >0)
			{
				alert(msgTxt);
				document.getElementById("MessageStatus").value="";
				document.getElementById("txtNewChqNo").value = '';
				document.getElementById("txtNewChqNo").focus();
			}
			else
			{
				alert('Duplicate Sucessfully');
				document.forms[0].btnRename.disabled=true;
				//document.frmChqRename.btnAdvice.disabled=false;
				document.forms[0].btnChq.disabled=false;
				genAdvice();
			}
			
		}
		
		function duplChq()
      	{
      		document.forms[0].action = "ifms.htm?actionFlag=chqRenameDetail";
			document.forms[0].submit();
      	}
      	
      		
// chq Duplication	


// chq rename

		function validateChqRname()
			{

				if(document.forms[0].txtNewChqNo.value=='-')
				{
					document.forms[0].txtChqNo.value ="";
					document.forms[0].txtNewChqNo.value ="";
					document.forms[0].txtChqAmt.value="";
					document.forms[0].txtPartyName.value="";
					document.forms[0].txtChqDate.value="";
					alert('Cheque Not Found');
					return;
				}
				if(document.forms[0].txtNewChqNo.value=='CANCELLED')
				{
					
					document.forms[0].txtNewChqNo.value ="";
					document.forms[0].txtChqAmt.value="";
					document.forms[0].txtPartyName.value="";
					document.forms[0].txtChqDate.value="";
					alert('Cheque Already Cancel');
					return;
				}
				if(document.forms[0].txtNewChqNo.value=='CLEARED')
				{
					
					document.forms[0].txtNewChqNo.value ="";
					document.forms[0].txtChqAmt.value="";
					document.forms[0].txtPartyName.value="";
					document.forms[0].txtChqDate.value="";
					alert('Cheque Already Cleared');
					return;
				}
				
				var accNo=document.forms[0].txtAccountNo.value;
				var addr = document.forms[0].txtAddress.value;
				var parCode = document.forms[0].partyCode.value
				
				if(accNo=='null')
				{
					document.forms[0].txtAccountNo.value='';
				}
				if(addr=='null')
				{
					document.forms[0].txtAddress.value='';
				}
				if(parCode=='null')
				{
					document.forms[0].partyCode.value='';
				}
				
				document.forms[0].Ddolists.value=document.forms[0].MessageStatus.value
				document.forms[0].MessageStatus.value='';
				document.forms[0].btnRename.disabled=false;
		}
		
		function disableRname()
		{
			
			var msgTxt = document.getElementById("MessageStatus").value;
			if(msgTxt != null && msgTxt.length >0)
			{
				alert(msgTxt);
				document.getElementById("MessageStatus").value="";
				if(document.getElementById("txtNewChqNo").value=='-')
				{
					document.getElementById("txtNewChqNo").value = '';
					document.getElementById("txtNewChqNo").focus();
				}
				
			}
			else
			{
				alert('Rename Sucessfully');
				document.forms[0].btnRename.disabled=true;
				//document.frmChqRename.btnAdvice.disabled=false;
				document.forms[0].btnChq.disabled=false;
				genAdvice();
			}
		}
		
		function renameChq()
      	{
      	alert('asdf');
      		//document.frmChqRename.action = "ifms.htm?actionFlag=chqRenameDetail";
			//document.frmChqRename.submit();
      	}
      	
      	function getParty()
      	{
      		var arr=new Array(2);
      		if(document.getElementById("ddoParty").value=='DDO')
			{		
				urlParty="ifms.htm?actionFlag=getAllParties&ddo=y&Index=-1&ddos="+document.forms[0].Ddolists.value;
			}
			else
			{
				urlParty ="ifms.htm?actionFlag=getAllParties&&ddo=n&Index=-1";
			}
		 	window.open(urlParty,"_blank","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,top=100,left=150,width=500,height=350"); 
      	}
      	
      	
// chq rename	


//chq rvldt
function validateChqRvldt()
		{
				if(document.forms[0].txtNewChqNo.value=='-')
				{
					
					document.forms[0].txtChqNo.value ="";
					document.forms[0].txtNewChqNo.value ="";
					document.forms[0].txtChqAmt.value="";
					document.forms[0].txtPartyName.value="";
					document.forms[0].txtChqDate.value="";
					document.forms[0].txtChqFromDt.value="";
					document.forms[0].txtChqToDt.value="";
					alert('Cheque Not Found');
					return;
				}
				if(document.forms[0].txtNewChqNo.value=='CANCELLED')
				{
					
					document.forms[0].txtNewChqNo.value ="";
					document.forms[0].txtChqAmt.value="";
					document.forms[0].txtPartyName.value="";
					document.forms[0].txtChqDate.value="";
					document.forms[0].txtChqFromDt.value="";
					document.forms[0].txtChqToDt.value="";
					alert('Cheque Already Cancel');
					return;
				}
				if(document.forms[0].txtNewChqNo.value=='CLEARED')
				{
					
					document.forms[0].txtNewChqNo.value ="";
					document.forms[0].txtChqAmt.value="";
					document.forms[0].txtPartyName.value="";
					document.forms[0].txtChqDate.value="";
					document.forms[0].txtChqFromDt.value="";
					document.forms[0].txtChqToDt.value="";
					alert('Cheque Already Cleared');
					return;
				}
				if(document.forms[0].txtNewChqNo.value=='RENAMED')
				{
					
					document.forms[0].txtNewChqNo.value ="";
					document.forms[0].txtChqAmt.value="";
					document.forms[0].txtPartyName.value="";
					document.forms[0].txtChqDate.value="";
					document.forms[0].txtChqFromDt.value="";
					document.forms[0].txtChqToDt.value="";
					alert('Cheque Already Revalidated');
					return;
				}
				if(document.forms[0].txtPartyName.value=='')
				{
					
					alert('Cheque Not Found');
					return false;
				}
				document.forms[0].btnRevalid.disabled=false;
		}
		
		function revalidChq()
      	{
      		document.forms[0].action = "ifms.htm?actionFlag=chqRevalidDetail";
			document.forms[0].submit();
      	}
// chq rvldt