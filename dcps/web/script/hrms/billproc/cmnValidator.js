//Start adviceVoucher.jsp

function selectCheck()
		{	
			indx = document.frmObjectionDtls.objCode.value;			
		}
		function submitForm(url)
		{
			document.frmObjectionDtls.action =url;
			document.frmObjectionDtls.submit();
			return true;
		}
		function SubmitData(url)
		{
			if(submitForm(url))
			{
				self.close();
			}
		}
		function selectParty(indx)
		{
			for(i=0;i< document.forms[0].chkbox.length;i++)
			{
				if(document.forms[0].chkbox[i].checked == true)
				{
				  partId =document.forms[0].chkbox[i].value;
				  values = partId.split("~");
				 
	              eval("window.opener.document.forms[0].txtPartyName"+indx).value=values[0];
    	          eval("window.opener.document.forms[0].txtAddress"+indx).value=values[1];
        	      eval("window.opener.document.forms[0].txtAccountNo"+indx).value=values[2];
				  
				}
			}
			
			window.close();
		}
		
		function dispatchCheques()
		{		
			
			//document.forms[0].action ='ifms.htm?actionFlag=chqRangeUpdateAtAdviceGen';
			//document.forms[0].submit();
			insertdt();
			dispatchCheques1();
		}
		function dispatchCheques1()
		{		
			var arr = window.opener.DispatchCheques();		
  		    var bill = arr[0].split("~");
			document.forms[0].action ='ifms.htm?actionFlag=getHyrUsers&statusFlag=CHQ_DISP_DDO&BillNo='+bill[0]+'&page=savedBillsUpdate&BillArr='+arr;

			document.forms[0].submit();
			
		}
		
		function checkUncheckAll(theElement) {
     var theForm = theElement.form, z = 0; 
	 for(z=0; z<theForm.length;z++){
      if(theForm[z].type == 'checkbox' && theForm[z].name != 'chkSelect'){
	  theForm[z].checked = theElement.checked;
	  }
     }
     }

//Ends adviceVoucher.jsp

//Start chqAcptApprBills.jsp
function checkUncheckAll(theElement) {
     var theForm = theElement.form, z = 0;
	 for(z=0; z<theForm.length;z++){
      if(theForm[z].type == 'checkbox' && theForm[z].name != 'chkSelect'){
	  theForm[z].checked = theElement.checked;
	  }
     }
     }
     function showBill(url)
			{
				window.open(url,"_blank","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,top=100,left=150,width=742,height=500"); 
			}
			 function chking()
			 {
			 alert("Select Any Check Box");
			 return false;
			 }
			 
//End chqAcptApprBills.jsp

// Start chqCancellation.jsp
		function validateChq()
		{
				if(document.frmChqCancle.txtNewChqNo.value=='-')
				{
					
					document.frmChqCancle.txtChqNo.value ="";
					document.frmChqCancle.txtNewChqNo.value ="";
					document.frmChqCancle.txtChqAmt.value="";
					document.frmChqCancle.txtPartyName.value="";
					document.frmChqCancle.txtChqDate.value="";
					alert('Cheque Not Found');
					return;
				}
				
				if(document.frmChqCancle.txtNewChqNo.value=='CANCELLED')
				{
					
					document.frmChqCancle.txtNewChqNo.value ="";
					document.frmChqCancle.txtChqAmt.value="";
					document.frmChqCancle.txtPartyName.value="";
					document.frmChqCancle.txtChqDate.value="";
					alert('Cheque Already Cancel');
					return;
				}
				
				if(document.frmChqCancle.txtNewChqNo.value=='CLEARED')
				{
					
					document.frmChqCancle.txtNewChqNo.value ="";
					document.frmChqCancle.txtChqAmt.value="";
					document.frmChqCancle.txtPartyName.value="";
					document.frmChqCancle.txtChqDate.value="";
					alert('Cheque Already Cleared');
					return;
				}
				
				if(document.frmChqCancle.txtPartyName.value=='')
				{
					
					alert('Cheque Not Found');
					return false;
				}
				document.frmChqCancle.btnCancle.disabled=false;
		}
		
		function disableRename()
		{
			
			alert('Rename Sucessfully');
			
			
		}
      	function cancleChq()
      	{
      		document.frmChqCancle.action = "ifms.htm?actionFlag=chqCancleDetail";
			document.frmChqCancle.submit();
			alert('Cheque Cancel Sucessfully');
      	}
      	
      	function genAdvice()
      	{
   			window.open("ifms.htm?actionFlag=generateAdvice&advFlg=1&chkbox="+document.getElementById("chkbox").value);
      	}
// end chqCancellation.jsp

//chqDuplication.jsp
 function genChq()
	 {
		var url ="ifms.htm?actionFlag=generateChequePDF&Chqrename=chqRen&Cheques="+document.getElementById("chkbox").value; 
		window.open(url,'_blank','');
	 }
	 function newChq()
	 {	
	    
	 	document.frmChqRename.action = "ifms.htm?viewName=chqRename";
		document.frmChqRename.submit();
	 }
		function autoFill()
		{
			var chqCtrl = document.frmChqRename.txtChqNo;
			var chqNo = chqCtrl.value;
			if(chqNo.length < 1)
			{
				alert("Please Enter Cheque No.");
				chqCtrl.focus();
				return false;
			}
			else
			{	
				document.frmChqRename.txtChqAmt.value="30000.00";
				document.frmChqRename.txtPartyName.value="A.S. Shah";
				document.frmChqRename.txtChqDate.value="03/10/2006";
				document.frmChqRename.btnRename.disabled=false;
			}
			return true;
		}
		
		function validateChq()
		{
				if(document.frmChqRename.txtNewChqNo.value=='-')
				{
					
					document.frmChqRename.txtChqNo.value ="";
					document.frmChqRename.txtNewChqNo.value ="";
					document.frmChqRename.txtChqAmt.value="";
					document.frmChqRename.txtPartyName.value="";
					document.frmChqRename.txtChqDate.value="";
					alert('Cheque Not Found');
					return;
				}
				if(document.frmChqRename.txtNewChqNo.value=='CANCELLED')
				{
					
					document.frmChqRename.txtNewChqNo.value ="";
					document.frmChqRename.txtChqAmt.value="";
					document.frmChqRename.txtPartyName.value="";
					document.frmChqRename.txtChqDate.value="";
					alert('Cheque Already Cancel');
					return;
				}
				if(document.frmChqRename.txtNewChqNo.value=='CLEARED')
				{

					document.frmChqRename.txtNewChqNo.value ="";
					document.frmChqRename.txtChqAmt.value="";
					document.frmChqRename.txtPartyName.value="";
					document.frmChqRename.txtChqDate.value="";
					alert('Cheque Already Cleared');
					return;
				}
				
				document.frmChqRename.btnRename.disabled=false;
		}
		
		function disableRename()
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
				document.frmChqRename.btnRename.disabled=true;
				//document.frmChqRename.btnAdvice.disabled=false;
				document.frmChqRename.btnChq.disabled=false;
				genAdvice();
			}
			
		}
      	function renameChq()
      	{
      		document.frmChqRename.action = "ifms.htm?actionFlag=chqRenameDetail";
			document.frmChqRename.submit();
      	}
      	
      	function genAdvice()
      	{
   			window.open("ifms.htm?actionFlag=generateAdvice&advFlg=1&chkbox="+document.getElementById("chkbox").value,"_blank","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,top=300,left=400,width=400,height=300"); 
      	}
//chqDuplication.jsp

//chqPrinting
	function searching()
		{
			var str= document.frmChqPrinting.txtSearch.value;
			if(str=='')
			{
			 	alert('Search Text is Empty');
			 	return;
			}
			if(document.frmChqPrinting.cmbSearch.value =='fromDt')
				{
					if(!isDate(document.frmChqPrinting.txtSearch.value))
					{
						return;
					}
				}
			
				document.frmChqPrinting.action='ifms.htm?actionFlag=getCheques&StatusFlag='+'<%=bundle.getString("STATUS.CheqWritten")%>';
			
			
			document.frmChqPrinting.displ.value=str;			
			document.frmChqPrinting.method='post';
			document.frmChqPrinting.submit();

		}

		
		
			function checkUncheckAll(theElement) {
	     var theForm = theElement.form, z = 0;
		 for(z=0; z<theForm.length;z++){
	
	      if(theForm[z].type == 'checkbox' && theForm[z].name != 'chkSelect'){
		  theForm[z].checked = theElement.checked;
		  }
	     }
	     for(z=0; z<theForm.length;z++){
	      
	      if(theForm[z].type == 'select-one'){
		  //alert("Name"+ theForm[z].name +"VALUE"+theForm[z].value);
		  }
	     }
	     	
	     	var startChq=document.frmChqPrinting.nextCheque.value;
	     	if(document.frmChqPrinting.chkSelect.checked == true)
	     	{
	    		var incNo=0;
				for(i=0;i<document.frmChqPrinting.elements.length;i++)
			    {
			        if(document.frmChqPrinting.elements[i].type=="checkbox" && document.frmChqPrinting.elements[i].name !='chkSelect')
			        {	
			          document.getElementById(incNo).value='';
			          document.getElementById(incNo).value=startChq++;
			          incNo++;	
			        } 
			    } 
			 }
			 else
			 {
			 	var incNo=0;
			 	for(i=0;i<document.frmChqPrinting.elements.length;i++)
			    {
			        if(document.frmChqPrinting.elements[i].type=="checkbox" && document.frmChqPrinting.elements[i].name !='chkSelect')
			        {
			 			document.getElementById(incNo).value='';
			 			incNo++;
			 		}
			 	}	
			 }   
	     }
		
			function generatePDF()
			{
				var arr = new Array();
			    var indx  =0;
			    var flag =0;
			    for(i=0;i<document.frmChqPrinting.elements.length;i++)
			    {

			        if(document.frmChqPrinting.elements[i].type=="checkbox" && document.frmChqPrinting.elements[i].name !='chkSelect')
			        {	
			          if(document.frmChqPrinting.elements[i].checked == true)
			          {
			            arr[indx]  = document.frmChqPrinting.elements[i].value;
			            indx = indx+1;
			            flag =1;
			          }
			        }
			    }
			    if(flag ==0)
			    {
			       alert(' Select atleast one checkbox ');
			       return false;
			    }				
				url ="ifms.htm?actionFlag=generateChequePDF&Cheques="+arr;
				window.open(url,'_blank','');
			}
			
			function printCheque()
			{
				var arr = new Array();
			    var indx  =0;
			    var flag =0;
			    for(i=0;i<document.frmChqPrinting.elements.length;i++)
			    {

			        if(document.frmChqPrinting.elements[i].type=="checkbox" && document.frmChqPrinting.elements[i].name !='chkSelect')
			        {	
			          if(document.frmChqPrinting.elements[i].checked == true)
			          {
			            arr[indx]  = document.frmChqPrinting.elements[i].value;
			            indx = indx+1;
			            flag =1;
			          }
			        }
			    }
			    if(flag ==0)
			    {
			       alert(' Select atleast one checkbox ');
			       return false;
			    }
			    var nextCheq = document.frmChqPrinting.nextCheque.value;
			    if(nextCheq=='' || nextCheq =='0' )
			    {
			    	alert('Starting Cheque No. is not valid');
			    	document.frmChqPrinting.nextCheque.focus();
			    	return false;
			    }
			    document.frmChqPrinting.selectCheques.value=arr;
			    //insertdt();			     
			    document.frmChqPrinting.btnPrintChqs.disabled = true;
			    document.frmChqPrinting.action ="ifms.htm?StatusFlag="+'<%=bundle.getString("STATUS.CheqWritten")%>';
			    document.frmChqPrinting.submit();
			}
			function showDtPic()
  			{
  				if(document.frmChqPrinting.cmbSearch.value=="fromDt")
  				{
  					document.getElementById("dtpicker").style.display="inline";
  				}
  				else
  				{
  					document.getElementById("dtpicker").style.display="none";
  				}
  			}
  			function returnBill()
			{
				document.frmChqPrinting.actionFlag.value="sendToCounter";
				var arr = new Array();
			    var indx  =0;
			    var flag =0;
			    for(i=0;i<document.frmChqPrinting.elements.length;i++)
			    {
			        if(document.frmChqPrinting.elements[i].type=="checkbox" && document.frmChqPrinting.elements[i].name !='chkSelect')
			        {	
			          if(document.frmChqPrinting.elements[i].checked == true)
			          {
			            arr[indx]  = document.frmChqPrinting.elements[i].value;
			            indx = indx+1;
			            flag =1;
			          }
			        }
			    }
			    if(flag ==0)
			    {
			       alert('Please Check atleast one checkbox');
			       return false;
			    }
				if(confirm('Existing cheques will be cancelled.Do you still want to proceed ?'))
				{
					document.frmChqPrinting.action = 'ifms.htm?actionFlag=sendToCounter&ReturnBill=Yes&StatusFlag='+'<%=bundle.getString("STATUS.CheqWritten")%>';
					document.frmChqPrinting.submit();
				}
			}
			function onSearch(e)
  			{
  				
  				if(e.keyCode==13)
  				{
  					var str= document.frmChqPrinting.txtSearch.value;
					if(str=='')
					{
						//document.rm_accvousfrom.parentUrl1.value = "";
						alert("Search Text is Empty");
						return false;
					}
					else
					{
  						searching();
  					}
  				}
  			}
			function changChqNo()
			{
				var startChq=document.frmChqPrinting.nextCheque.value;
				var incNo=0;
				for(i=0;i<document.frmChqPrinting.elements.length;i++)
			    {
			        if(document.frmChqPrinting.elements[i].type=="checkbox" && document.frmChqPrinting.elements[i].name !='chkSelect')
			        {	
			          document.getElementById(incNo).value='';
			          if(document.frmChqPrinting.elements[i].checked == true)
			          {
			          		document.getElementById(incNo).value=startChq++;
			          }
			          incNo++;	
			        } 
			    }    
			}
			function showBills(BillNo, BillStatus)
			{
				var newWindow;
		    	var height = screen.height - 100;
		    	var width = screen.width;
		    	var urlstring = "ifms.htm?actionFlag=getBillData&isChq=1&billNo=" + BillNo + "&billStatus=" + BillStatus;
		    	var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
		    	newWindow = window.open(urlstring, "frmViewOnlineBill", urlstyle);
			}
			
			function showBill(url)
			{
				window.open(url,"_blank","toolbar=no,location=no,directories=no,status=yes,menubar=no,scrollbars=yes,resizable=no,top=10,left=10,width=1010,height=670");
			//window.open(url);
			}
//chqPrinting

//chqRename.jsp
function genChq()
	 {
		var url ="ifms.htm?actionFlag=generateChequePDF&Chqrename=chqRen&Cheques="+document.getElementById("chkbox").value; 
		window.open(url,'_blank','');
	 }
	 function newChq()
	 {	
	    
	 	document.frmChqRename.action = "ifms.htm?viewName=chqRename";
		document.frmChqRename.submit();
	 }
		function autoFill()
		{
			var chqCtrl = document.frmChqRename.txtChqNo;
			var chqNo = chqCtrl.value;
			if(chqNo.length < 1)
			{
				alert("Please Enter Cheque No.");
				chqCtrl.focus();
				return false;
			}
			else
			{	
				document.frmChqRename.txtChqAmt.value="30000.00";
				document.frmChqRename.txtPartyName.value="A.S. Shah";
				document.frmChqRename.txtChqDate.value="03/10/2006";
				document.frmChqRename.btnRename.disabled=false;
			}
			return true;
		}
		
		function validateChq()
		{

				if(document.frmChqRename.txtNewChqNo.value=='-')
				{
					document.frmChqRename.txtChqNo.value ="";
					document.frmChqRename.txtNewChqNo.value ="";
					document.frmChqRename.txtChqAmt.value="";
					document.frmChqRename.txtPartyName.value="";
					document.frmChqRename.txtChqDate.value="";
					alert('Cheque Not Found');
					return;
				}
				if(document.frmChqRename.txtNewChqNo.value=='CANCELLED')
				{
					
					document.frmChqRename.txtNewChqNo.value ="";
					document.frmChqRename.txtChqAmt.value="";
					document.frmChqRename.txtPartyName.value="";
					document.frmChqRename.txtChqDate.value="";
					alert('Cheque Already Cancel');
					return;
				}
				if(document.frmChqRename.txtNewChqNo.value=='CLEARED')
				{
					
					document.frmChqRename.txtNewChqNo.value ="";
					document.frmChqRename.txtChqAmt.value="";
					document.frmChqRename.txtPartyName.value="";
					document.frmChqRename.txtChqDate.value="";
					alert('Cheque Already Cleared');
					return;
				}
				
				var accNo=document.frmChqRename.txtAccountNo.value;
				var addr = document.frmChqRename.txtAddress.value;
				var parCode = document.frmChqRename.partyCode.value
				
				if(accNo=='null')
				{
					document.frmChqRename.txtAccountNo.value='';
				}
				if(addr=='null')
				{
					document.frmChqRename.txtAddress.value='';
				}
				if(parCode=='null')
				{
					document.frmChqRename.partyCode.value='';
				}
				
				document.frmChqRename.Ddolists.value=document.frmChqRename.MessageStatus.value
				document.frmChqRename.MessageStatus.value='';
				document.frmChqRename.btnRename.disabled=false;
		}
		
		function disableRename()
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
				document.frmChqRename.btnRename.disabled=true;
				//document.frmChqRename.btnAdvice.disabled=false;
				document.frmChqRename.btnChq.disabled=false;
				genAdvice();
			}
		}
      	function renameChq()
      	{
      	alert('asdf');
      		//document.frmChqRename.action = "ifms.htm?actionFlag=chqRenameDetail";
			//document.frmChqRename.submit();
      	}
      	
      	function genAdvice()
      	{
   			window.open("ifms.htm?actionFlag=generateAdvice&advFlg=1&chkbox="+document.getElementById("chkbox").value,"_blank","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,top=300,left=400,width=400,height=300"); 
      	}
      	
      	function getParty()
      	{
      		var arr=new Array(2);
      		if(document.getElementById("ddoParty").value=='DDO')
			{		
				urlParty="ifms.htm?actionFlag=getAllParties&ddo=y&Index=-1&ddos="+document.frmChqRename.Ddolists.value;
			}
			else
			{
				urlParty ="ifms.htm?actionFlag=getAllParties&&ddo=n&Index=-1";
			}
		 	window.open(urlParty,"_blank","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,top=100,left=150,width=500,height=350"); 
		
      	}
//chqRename.jsp

//chqRevalidateChq.jsp
function validateChq()
		{
				if(document.frmChqRevalid.txtNewChqNo.value=='-')
				{
					
					document.frmChqRevalid.txtChqNo.value ="";
					document.frmChqRevalid.txtNewChqNo.value ="";
					document.frmChqRevalid.txtChqAmt.value="";
					document.frmChqRevalid.txtPartyName.value="";
					document.frmChqRevalid.txtChqDate.value="";
					document.frmChqRevalid.txtChqFromDt.value="";
					document.frmChqRevalid.txtChqToDt.value="";
					alert('Cheque Not Found');
					return;
				}
				if(document.frmChqRevalid.txtNewChqNo.value=='CANCELLED')
				{
					
					document.frmChqRevalid.txtNewChqNo.value ="";
					document.frmChqRevalid.txtChqAmt.value="";
					document.frmChqRevalid.txtPartyName.value="";
					document.frmChqRevalid.txtChqDate.value="";
					document.frmChqRevalid.txtChqFromDt.value="";
					document.frmChqRevalid.txtChqToDt.value="";
					alert('Cheque Already Cancel');
					return;
				}
				if(document.frmChqRevalid.txtNewChqNo.value=='CLEARED')
				{
					
					document.frmChqRevalid.txtNewChqNo.value ="";
					document.frmChqRevalid.txtChqAmt.value="";
					document.frmChqRevalid.txtPartyName.value="";
					document.frmChqRevalid.txtChqDate.value="";
					document.frmChqRevalid.txtChqFromDt.value="";
					document.frmChqRevalid.txtChqToDt.value="";
					alert('Cheque Already Cleared');
					return;
				}
				if(document.frmChqRevalid.txtNewChqNo.value=='RENAMED')
				{
					
					document.frmChqRevalid.txtNewChqNo.value ="";
					document.frmChqRevalid.txtChqAmt.value="";
					document.frmChqRevalid.txtPartyName.value="";
					document.frmChqRevalid.txtChqDate.value="";
					document.frmChqRevalid.txtChqFromDt.value="";
					document.frmChqRevalid.txtChqToDt.value="";
					alert('Cheque Already Revalidated');
					return;
				}
				if(document.frmChqRevalid.txtPartyName.value=='')
				{
					
					alert('Cheque Not Found');
					return false;
				}
				document.frmChqRevalid.btnRevalid.disabled=false;
		}
		
		function disableRename()
		{
			
			alert('Rename Sucessfully');
			
			
		}
      	function revalidChq()
      	{
      		document.frmChqRevalid.action = "ifms.htm?actionFlag=chqRevalidDetail";
			document.frmChqRevalid.submit();
      	}
      	
      	function genAdvice()
      	{
   			window.open("ifms.htm?actionFlag=generateAdvice&advFlg=1&chkbox="+document.getElementById("chkbox").value);
      	}
//chqRevalidateChq.jsp

//chqWriteChq.jsp
var billLst;
		var billCntrlLst;
		var billAmt;
		var ddoList ;
		var billDates;
		function loadcalendar(name,img)
  	    {			  
		   var cal1 = new CalendarPopup();			   
		   cal1.select(name,img,'dd/MM/yyyy'); 
		   return false;			   
	    }
					
		function showBill(url)
		{
			window.open(url,"_blank","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,top=100,left=150,width=742,height=500"); 
		}
				
		function displ()
		{						
			if(submitCheques()== true)
			{						
				return true;
			}
		}
		function submitCheques()
		{
			document.frmWriteChq.action ='ifms.htm?actionFlag=writeCheque';
			document.frmWriteChq.submit();
			return true;
		}
		function deleteCheques()
		{
			var indx  =0;
	    	for(i=0;i < document.frmWriteChq.elements.length;i++)
			{
				if(document.frmWriteChq.elements[i].type =="checkbox" && document.frmWriteChq.elements[i].name != "chkSelect")
				{
					if(document.frmWriteChq.elements[i].checked)
					{
						indx++;
					} 
				}
			}
	    	
	    	if(indx==0)
	    	{
	    	 alert("Select atleast one checkbox");
	    	 return false;
	    	}
			document.frmWriteChq.action ='ifms.htm?actionFlag=chequeInSession&flag=REMOVE';
			document.frmWriteChq.submit();
		}
		 function DeleteThis(obj,tblId)
		 {	   	 	 
		      var rowID = showRowCell(obj);            
		      var tbl = document.getElementById(tblId);    
		      tbl.deleteRow(rowID);  
		      CaluculateTotal();		      
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
			function checkUncheckAll(theElement) {
	     var theForm = theElement.form, z = 0;
		 for(z=0; z<theForm.length;z++){
	
	      if(theForm[z].type == 'checkbox' && theForm[z].name != 'chkSelect'){
		  theForm[z].checked = theElement.checked;
		  }
	     }
	     for(z=0; z<theForm.length;z++){
	      
	      if(theForm[z].type == 'select-one'){
		  //alert("Name"+ theForm[z].name +"VALUE"+theForm[z].value);
		  }
	     }
	     }
//chqWriteChq.jsp


//cntrAcptCheques.jsp
var fromFwd="-1";     // from which fwd comes
	    
	    function  searching1(resFlg)
		{
			
			if(document.frmCntrDisptchBills.srch0.value!='')
			{
				if(!isDate(document.frmCntrDisptchBills.srch0.value))
				{
				return false;
				}
			}
			
			if(document.frmCntrDisptchBills.srch1.value!='')
			{
				if(!isDate(document.frmCntrDisptchBills.srch1.value))
				{
				return;
				}
			}
			
			
			if(document.frmCntrDisptchBills.srch2.value!='')
			{
				if(!(validations(document.frmCntrDisptchBills.srch2.value)))
				{
				 	document.frmCntrDisptchBills.srch2.value='';
					document.frmCntrDisptchBills.srch2.focus();
					return false;
				}
			}	
			if(document.frmCntrDisptchBills.srch3.value!='')
			{
				if(!(validations(document.frmCntrDisptchBills.srch3.value)))
				{
					document.frmCntrDisptchBills.srch3.value='';				
					document.frmCntrDisptchBills.srch3.focus();
					return false;
				}
			}	
		
				document.frmCntrDisptchBills.action ='ifms.htm?actionFlag=acceptCheques&StatusFlag=<%=(String)request.getParameter("StatusFlag")%>&ReceiveFlag='+resFlg ;
				document.frmCntrDisptchBills.method ='post';
				document.frmCntrDisptchBills.submit();
		}
	    
	    
	    
		function  searching(resFlg)
		{
		document.frmCntrDisptchBills.displ.value = document.frmCntrDisptchBills.txtSearch.value;
			if(document.frmCntrDisptchBills.txtSearch.value=='')
			{
				alert("Search Text is Empty");
				return;
			}
			if(document.frmCntrDisptchBills.cmbSearch.value =='p.billDate')
			{
			  
			  var str = document.frmCntrDisptchBills.txtSearch.value;
			  
			  if(!isDate(str))
					{
						return;
					}
			  var arr =  str.split('/');
			  //document.frmCntrDisptchBills.action='ifms.htm?actionFlag=getCheques&StatusFlag=CHEQPRNT&txtSearch='+arr[2]+'-'+arr[1]+'-'+arr[0];
			  
			  document.frmCntrDisptchBills.action ='ifms.htm?actionFlag=acceptCheques&StatusFlag=<%=(String)request.getParameter("StatusFlag")%>&ReceiveFlag='+resFlg + '&txtSearch='+arr[2]+'-'+arr[1]+'-'+arr[0] ;
			}
			else
			{
				document.frmCntrDisptchBills.action ='ifms.htm?actionFlag=acceptCheques&StatusFlag=<%=(String)request.getParameter("StatusFlag")%>&ReceiveFlag='+resFlg ;
			}
			
			//document.frmCntrDisptchBills.action ='ifms.htm?actionFlag=acceptCheques&StatusFlag=CHEQDISPCNTR&ReceiveFlag='+resFlg ;
			document.frmCntrDisptchBills.method ='post';
			document.frmCntrDisptchBills.submit();
		}
		function showBill(url)
		{
			window.open(url,"_blank","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,top=100,left=150,width=742,height=500"); 
		}
		function checkUncheckAll(theElement) {
	     var theForm = theElement.form, z = 0;
		 for(z=0; z<theForm.length;z++){
	
	      if(theForm[z].type == 'checkbox' && theForm[z].name != 'chkSelect'){
		  theForm[z].checked = theElement.checked;
		  }
	     }
	     for(z=0; z<theForm.length;z++){
	      
	      if(theForm[z].type == 'select-one'){
		  //alert("Name"+ theForm[z].name +"VALUE"+theForm[z].value);
		  }
	     }
	     }
		
		function ReceiveCheques(type)
		{
			fromFwd="sendToCntr";
		    var flag =0;
		    for(i=0;i<document.frmCntrDisptchBills.elements.length;i++)
		    {
		        if(document.frmCntrDisptchBills.elements[i].type=="checkbox" && document.frmCntrDisptchBills.elements[i].name!='chkSelect')
		        {	
		          if(document.frmCntrDisptchBills.elements[i].checked == true)
		          {
		            flag =1;
		            break;
		          }
		        }
		    }
		    if(flag ==0)
		    {
		       alert('Select atleast one checkbox');
		       return false;
		    }
		    var statusFlag = '';
		    if(type == 'Custody')
		    {
		    	statusFlag = '<%=request.getParameter("StatusFlag")%>';
		    }
		    else
		    {
		    	statusFlag = 'CHQ_CNTR';
		    }
		    
			document.frmCntrDisptchBills.action ='ifms.htm?actionFlag=acceptCheques&StatusFlag='+statusFlag+'&ReceiveFlag=0&Receive=Yes';
			
			document.frmCntrDisptchBills.submit();
		}
		function generateAdvice()
		{
			var arr = new Array();
		    var indx  =0;
		    var flag =0;
		    for(i=0;i<document.frmCntrDisptchBills.elements.length;i++)
		    {
		        if(document.frmCntrDisptchBills.elements[i].type=="checkbox" && document.frmCntrDisptchBills.elements[i].name !='chkSelect')
		        {	
		          if(document.frmCntrDisptchBills.elements[i].checked == true)
		          {
		            arr[indx]  = document.frmCntrDisptchBills.elements[i].value;
		            indx = indx+1;
		            flag =1;
		          }
		        }
		    }
		    if(flag ==0)
		    {
		       alert(' Select atleast one checkbox ');
		       return false;
		    }
		    document.frmCntrDisptchBills.actionFlag.value='generateVouchAdvc';
		    urlStr = 'ifms.htm?actionFlag=generateVouchAdvc&chkbox='+arr;
		    window.open(urlStr,'_blank','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,copyhistory=no,top=200,left=300,width=450,height=400')
		    //alert(arr);
			//insertdt();  
		}	
		
		function DispatchCheques()
		{
			fromFwd="DispChq";
			var arr = new Array();
		    var indx  =0;
		    var flag =0;
		    for(i=0;i<document.frmCntrDisptchBills.elements.length;i++)
		    {

		        if(document.frmCntrDisptchBills.elements[i].type=="checkbox" && document.frmCntrDisptchBills.elements[i].name !='chkSelect')
		        {	
		          if(document.frmCntrDisptchBills.elements[i].checked == true)
		          {
		            arr[indx]  = document.frmCntrDisptchBills.elements[i].value;
		            //alert(arr[indx]);
		            indx = indx+1;
		            flag =1;
		          }
		        }
		    }
		    if(flag ==0)
		    {
		       alert(' Select atleast one checkbox ');
		       return false;
		    }

		    var bill = arr[0].split("~");
			//window.open('ifms.htm?actionFlag=getHyrUsers&BillNo='+bill[0]+'&page=savedBillsUpdate','','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,top=100,left=150,width=500,height=400');
			//window.open('ifms.htm?actionFlag=getHyrUsers&BillNo='+bill[0]+'&page=savedBillsUpdate&BillArr='+arr,'','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,top=250,left=350,width=500,height=400');
			//document.frmCntrDisptchBills.action ='ifms.htm?actionFlag=getHyrUsers&BillNo='+bill[0]+'&page=savedBillsUpdate&BillArr='+arr;
			//alert(document.frmCntrDisptchBills.action);
			//document.frmCntrDisptchBills.submit();
			return arr;
			
		}		
		
		function sendToCounter(type)
		{
			var i,j=0,ale='n';
			for(i=0;i<document.frmCntrDisptchBills.elements.length;i++)
			    {

			        if(document.frmCntrDisptchBills.elements[i].type=="checkbox" && document.frmCntrDisptchBills.elements[i].name != 'chkSelect')
			        {	
			          
			          if(document.frmCntrDisptchBills.elements[i].checked == true)
			          {
			          	var exm = eval('document.frmCntrDisptchBills.exempted' +j);
			          	if(exm.value=='N')
			          	{
			          		ale='y';
			          		break;
			          	}
			          }
			          j++;
			         }
			       }  
			          	
			if(ale=='y')
			{
				if(!confirm('Are You Sure To Forwarding Non exempted Bill?'))
				return false;
			}
			fromFwd="sendToCntr";
			var arr = new Array();
		    var indx  =0;
		    var flag =0;
		    for(i=0;i<document.frmCntrDisptchBills.elements.length;i++)
		    {
		        if(document.frmCntrDisptchBills.elements[i].type=="checkbox" && document.frmCntrDisptchBills.elements[i].name!='chkSelect')
		        {	
		          if(document.frmCntrDisptchBills.elements[i].checked == true)
		          {
		            arr[indx]  = document.frmCntrDisptchBills.elements[i].value;
		            indx = indx+1;
		            flag =1;
		          }
		        }
		    }
		    if(flag ==0)
		    {
		       alert('Select atleast one checkbox');
		       return false;
		    }
		    var bill = arr[0].split("~");
			url = 'ifms.htm?actionFlag=getHyrUsers&statusFlag=CHQ_CNTR&BillNo='+bill[0]+'&BillArr='+arr ;
			if(type == 'Forward')
		    {
		    	url = url+'&sendTo=H';
		    }
		    else if(type=='Peer')
		    {
		    	url = url+'&sendTo=P';		    	
		    }
		    window.open(url,'','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,top=250,left=350,width=500,height=400');

		}
		
		function displ()
		{
			if(fromFwd=="DispChq")
			{
			document.frmCntrDisptchBills.actionFlag.value="sendToBook";
			}
			else if (fromFwd=="sendToCntr")
			{
			document.frmCntrDisptchBills.actionFlag.value="sendToCounter";
			}
			var arr = new Array();
		    var indx  =0;
		    var flag =0;
		    for(i=0;i<document.frmCntrDisptchBills.elements.length;i++)
		    {

		        if(document.frmCntrDisptchBills.elements[i].type=="checkbox" && document.frmCntrDisptchBills.elements[i].name!='chkSelect')
		        {	
		          if(document.frmCntrDisptchBills.elements[i].checked == true)
		          {
		            arr[indx]  = document.frmCntrDisptchBills.elements[i].value;
		            indx = indx+1;
		            flag =1;
		          }
		        }
		    }
		    if(flag ==0)
		    {
		       alert(' Select atleast one checkbox ');
		       return false;
		    }
			//insertdt();
			if(fromFwd=="sendToCntr")
			{
			document.frmCntrDisptchBills.action = 'ifms.htm?actionFlag=sendToCounter&StatusFlag=<%=(String)request.getParameter("StatusFlag")%>&ReceiveFlag=1';
			}
			else if(fromFwd=="DispChq")
			{
			document.frmCntrDisptchBills.action= 'ifms.htm?actionFlag=sendToDDO&StatusFlag=<%=(String)request.getParameter("StatusFlag")%>&ReceiveFlag=1';

			}
			document.frmCntrDisptchBills.submit();
			
			if("CUSTCON2"=="<%=(String)request.getParameter("StatusFlag")%>" || "CUSTCON1"=="<%=(String)request.getParameter("StatusFlag")%>")
			{
			//document.frmCntrDisptchBills.action="ifms.htm?actionFlag=acceptCheques&StatusFlag=<%=(String)request.getParameter("StatusFlag")%>&ReceiveFlag=0";
			//document.frmCntrDisptchBills.submit();
			}
			
		}
		
		function returnBill()
		{
			var arr = new Array();
		    var indx  =0;
		    var flag =0;
		    for(i=0;i<document.frmCntrDisptchBills.elements.length;i++)
		    {

		        if(document.frmCntrDisptchBills.elements[i].type=="checkbox" && document.frmCntrDisptchBills.elements[i].name!='chkSelect')
		        {	
		          if(document.frmCntrDisptchBills.elements[i].checked == true)
		          {
		            arr[indx]  = document.frmCntrDisptchBills.elements[i].value;
		            indx = indx+1;
		            flag =1;
		          }
		        }
		    }
		    if(flag ==0)
		    {
		       alert(' Select atleast one checkbox ');
		       return false;
		    }
		    document.frmCntrDisptchBills.actionFlag.value="sendToCounter";
		    document.frmCntrDisptchBills.action = 'ifms.htm?actionFlag=sendToCounter&ReturnBill=Yes&StatusFlag=<%=(String)request.getParameter("StatusFlag")%>&ReceiveFlag=1&BillArr='+arr;
		   
		    document.frmCntrDisptchBills.submit();
		}
		
		function validations(data)
			{
				if(data=='.')
				{
					alert("Amount Not Valid");
					return false;
				}
				var numaric = data;
				for(var j=0; j<numaric.length; j++)
				{
 				    var hh = numaric.charAt(j);
				    if(hh >= '0' && hh <= '9'  || hh=='.' )
		  			{
				    }
					else	
					{
						alert("Amount Not Valid");
					    return false;
		            }
				}
				return true;				
			}		
			function showDtPic()
  			{
  				if(document.frmCntrDisptchBills.cmbSearch.value=="p.billDate")
  				{
  					document.getElementById("dtpicker").style.display="inline";
  				}
  				else
  				{
  					document.getElementById("dtpicker").style.display="none";
  				}
  			}
  			function onSearch(e)
  			{
  				
  				if(e.keyCode==13)
  				{
  					
					if(document.frmCntrDisptchBills.txtSearch.value=='')
					{
						//document.frmCntrDisptchBills.parentUrl1.value = "";
						alert("Search Text is Empty");
						return false;
					}
					else
					{
						searching(<%=request.getParameter("ReceiveFlag") %>);
  					}
  				}
  			}
  			
  			function showBills(BillNo, BillStatus)
			{
				var newWindow;
		    	var height = screen.height - 100;
		    	var width = screen.width;
		    	var urlstring = "ifms.htm?actionFlag=getBillData&isChq=1&billNo=" + BillNo + "&billStatus=" + BillStatus;
		    	var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
		    	alert(urlstring);		    	
		    	newWindow = window.open(urlstring, "frmViewOnlineBill", urlstyle);
			}
			
			function showBill(url)
			{
				window.open(url,"_blank","toolbar=no,location=no,directories=no,status=yes,menubar=no,scrollbars=yes,resizable=no,top=10,left=10,width=1010,height=670");
			//window.open(url);
			}
//cntrAcptCheques.jsp





