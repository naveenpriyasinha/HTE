//Start \web\WEB-INF\jsp\billproc\cntrSavedBillOnline.jsp
var peer='';
function receiveOnline(paraStatusFlag)
	{
		alert(paraStatusFlag);
			var indx=0;
			for(i=0;i < document.rm_accvousfrom.elements.length;i++)
				{
					if(document.rm_accvousfrom.elements[i].type=="checkbox" && document.rm_accvousfrom.elements[i].name != 'chkSelect')
					{
						if(document.rm_accvousfrom.elements[i].checked)
						{
							indx++;
							var tokn = document.getElementById("tokens_"+document.rm_accvousfrom.elements[i].value).value; 
							if(tokn =='')
							{
								alert('Please enter the token number');
								return false;
							}
						}
					}
				}
				if(indx==0)
		    	{
	    			alert("Select atleast one checkbox to Forward");
	    		 	return false;
	    		}
				
			document.rm_accvousfrom.parentUrl1.value='';
			document.rm_accvousfrom.action="ifms.htm?actionFlag=receiveOnlineBill&recFlag=0&statusFlag="+paraStatusFlag+"&updStatusFlag=BINWD";	
			document.rm_accvousfrom.submit();	
	}
	
function peerOnline(type,paraUpdStatusFlag)
			{
				peer='y';
				var indx=0;
		    	for(i=0;i < document.rm_accvousfrom.elements.length;i++)
				{
					if(document.rm_accvousfrom.elements[i].type=="checkbox" && document.rm_accvousfrom.elements[i].name != 'chkSelect')
					{
						if(document.rm_accvousfrom.elements[i].checked)
						{
							indx++;
							var chkVal=document.rm_accvousfrom.elements[i].value;
				        	var arr= new Array();
				        	arr=chkVal.split("~");
							var cmbname = eval('document.rm_accvousfrom.cmb_'+arr[0]);
							if(cmbname.value=='-1')
							{
								alert("Select Auditor For Token No "+arr[1]);
								return false;
							}
						} 
					}
				}
	    	
		    	if(indx==0)
		    	{
	    			alert("Select atleast one checkbox to Forward");
	    		 	return false;
	    		}

				var BillNo="-1";
				 for(i=0;i<document.rm_accvousfrom.elements.length;i++)
			    {

			        if(document.rm_accvousfrom.elements[i].type=="checkbox" && document.rm_accvousfrom.elements[i].name != 'chkSelect')
			        {	
			          if(document.rm_accvousfrom.elements[i].checked == true)
			          {
			        	BillNo=document.rm_accvousfrom.elements[i].value;  	
			        	var arr= new Array();
			        	arr=BillNo.split("~");

			            break;
			          }
			        }
			    }
			    var updStatusFlg= paraUpdStatusFlag;
			    if(type=='Forward')
			    {
			    	updStatusFlg =updStatusFlg+'&sendTo=H';
			    }
			    else if(type=='Peer')
			    {
			    	updStatusFlg =updStatusFlg+'&sendTo=P';			    	
			    }
				window.open('ifms.htm?actionFlag=getHyrUsers&statusFlag='+updStatusFlg+'&BillNo='+arr[0],'_blank','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,top=100,left=150,width=500,height=400');
			}
			
			function fwdOnline(paraStatusFlag)
			{
				peer='n';
				var indx=0;
		    	for(i=0;i < document.rm_accvousfrom.elements.length;i++)
				{
					if(document.rm_accvousfrom.elements[i].type=="checkbox" && document.rm_accvousfrom.elements[i].name != 'chkSelect')
					{
						if(document.rm_accvousfrom.elements[i].checked)
						{
							indx++;
							
							var tokn = document.getElementById("tokens_"+document.rm_accvousfrom.elements[i].value).value; 
							if(tokn =='')
							{
								alert('Please enter the token number');
								return false;
							}
							
							var chkVal=document.rm_accvousfrom.elements[i].value;
				        	var arr= new Array();
				        	arr=chkVal.split("~");
							var cmbname = eval('document.rm_accvousfrom.cmb_'+arr[0]);
							if(cmbname.value=='-1')
							{
								alert("Select Auditor");
								return false;
							}
						} 
					}
					
				}
	    	
		    	if(indx==0)
		    	{
	    			alert("Select atleast one checkbox to Forward");
	    		 	return false;
	    		}

				
				
				
				var BillNo="-1";
				 for(i=0;i<document.rm_accvousfrom.elements.length;i++)
			    {

			        if(document.rm_accvousfrom.elements[i].type=="checkbox" && document.rm_accvousfrom.elements[i].name != 'chkSelect')
			        {	
			          if(document.rm_accvousfrom.elements[i].checked == true)
			          {
			        	BillNo=document.rm_accvousfrom.elements[i].value;  	
			        	var arr= new Array();
			        	arr=BillNo.split("~");

			            break;
			          }
			        }
			    }
			    //var updStatusFlg= request.getParameter("updStatusFlag");
				//window.open('ifms.htm?actionFlag=getHyrUsers&statusFlag='+updStatusFlg+'&BillNo='+arr[0],'_blank','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,top=100,left=150,width=500,height=400');
				document.rm_accvousfrom.parentUrl1.value='';
				document.forms[0].action ="ifms.htm?actionFlag=receiveOnlineBill&recFlag=1&statusFlag="+paraStatusFlag+"&updStatusFlag=BAUD";	
				document.forms[0].submit();
			}	
				

//Ends  \web\WEB-INF\jsp\billproc\cntrSavedBillOnline.jsp

//start cardex verify 
function openDigiPage(ddo)
		{
			window.open("ifms.htm?actionFlag=digiSigVerify&ddoCode="+ddo,"_blank","toolbar=no,location=no,directories=no,status=yes,menubar=no,scrollbars=no,resizable=no,top=300,left=330,width=500,height=300");
		}
		
		function tokenRelese()
		{
			 var chk=0;
			 for(i=0;i<document.rm_accvousfrom.elements.length;i++)
			    {
			        if(document.rm_accvousfrom.elements[i].type=="checkbox" && document.rm_accvousfrom.elements[i].name != 'chkSelect')
			        {	
			          if(document.rm_accvousfrom.elements[i].checked == true)
			          {
			          	chk++;
			          	document.rm_accvousfrom.parentUrl1.value='';
			 			document.rm_accvousfrom.action="ifms.htm?actionFlag=releseToken&statusFlag=BRJCT_AUD&updStatusFlag=BRJCT_AUD&recFlag=1";
						document.rm_accvousfrom.submit();	
			          	break;
			          }
			        }
			     }
			   if(chk==0)
			   {
			   		alert('Select atleast one bill for token relese');
			   		return false;
			   }      
		}
		
		function showChqPrepare(url)
		{
		
			//document.rm_accvousfrom.action=url;
			//document.rm_accvousfrom.submit();
			
			
			var arr = new Array();
		    var indx  =0;
		    var flag =0;
		    
		   for(i=0;i<document.rm_accvousfrom.elements.length;i++)
			    {

			        if(document.rm_accvousfrom.elements[i].type=="checkbox" && document.rm_accvousfrom.elements[i].name != 'chkSelect')
			        {	
			          if(document.rm_accvousfrom.elements[i].checked == true)
			          {
			            arr[indx]  = document.rm_accvousfrom.elements[i].value;
			            indx = indx+1;
			            flag =1;
			          }
			        }
			    }
			    if(flag ==0)
			    {
			       alert(' Please Check atleast one checkbox ');
			       return false;
			    }
		    for( i=0;i<arr.length;i++)
		    {
		    	var parts = arr[i].split("~");
		    	for(j=0;j<arr.length;j++)
		    	{
		    		var innerparts = arr[j].split("~");		    	
		    		if(parts[2] != innerparts[2])
		    		{
		    			alert(' Major head should be same for selected bills');
		    			return false;
		    		}
		    	}

		    }
		    for( i=0;i<arr.length;i++)
		    {
		    	var parts1 = arr[i].split("~");
		    	for(j=0;j<arr.length;j++)
		    	{
		    		var innerparts1 = arr[j].split("~");		    	
		    		if(parts1[6] != innerparts1[6])
		    		{
		    			alert('DDO should be same for selected bills');
		    			return false;
		    		}
		    	}

		    }
		    
      		url=url+'&chkbox='+arr;
			
			window.open(url,"_blank","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,top=10,left=30,width=900,height=630"); 
		}
		
			
			
			function showRemarks(url)
			{		
				window.open(url,"_blank","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,top=30,left=120,width=700,height=650"); 			
			}	
			
			 function cardexVerify()
			{
				var indx=0;
		    	for(i=0;i < document.rm_accvousfrom.elements.length;i++)
				{
					if(document.rm_accvousfrom.elements[i].type=="checkbox" && document.rm_accvousfrom.elements[i].name != 'chkSelect')
					{
						if(document.rm_accvousfrom.elements[i].checked)
						{
							indx++;
						} 
					}
				}
	    	
		    	if(indx==0)
		    	{
	    			alert("Select atleast one checkbox Cardex Verification");
	    		 	return false;
	    		}
				document.rm_accvousfrom.parentUrl1.value='';
				//alert('in submit'+document.forms[0]);
				document.rm_accvousfrom.action="ifms.htm?actionFlag=updateAcceptBills&page=cardexVerify";
				document.rm_accvousfrom.submit();	
			}
// end 	cardex verify
