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
     
     
function chkCombo(theElement){
     var theForm = theElement.form, z = 0;
     for(z=0; z<theForm.length;z++){
      
      if(theForm[z].type == 'select-one'){
	  alert("Name"+ theForm[z].name +"VALUE"+theForm[z].value);
	  }
     }
}   


 function showBill(url)
			{
				//alert(url);
				window.open(url,"_blank","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,top=40,left=40,width=950,height=650"); 
				//window.open(url);
			}
			
function showBills(BillNo, BillStatus)
	{
		var newWindow;
    	var height = screen.height - 100;
    	var width = screen.width;
    	var urlstring = "ifms.htm?actionFlag=getBillData&billNo=" + BillNo + "&billStatus=" + BillStatus;
    	var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
    	newWindow = window.open(urlstring, "frmViewOnlineBill", urlstyle);
	}			
			
function searching(paraStatusFlag,paraRecFlag,paraUpdStatusFlag)
			{
				var str= document.rm_accvousfrom.txtSearch.value;

				if(str=='')
				{
					alert("Search Text is Empty");
					return;
				}
				if(document.rm_accvousfrom.cmbSearch.value =='p.inwardDt')
				{
					if(!isDate(document.rm_accvousfrom.txtSearch.value))
					{
						return;
					}
				}
				
				document.rm_accvousfrom.parentUrl1.value = "";
				document.rm_accvousfrom.action='ifms.htm?actionFlag=getSavedBill&statusFlag='+paraStatusFlag+'&recFlag='+paraRecFlag+'&updStatusFlag='+paraUpdStatusFlag;

				
				//document.rm_accvousfrom.displ.value=str;			
				document.rm_accvousfrom.method='post';
				document.rm_accvousfrom.submit();
			}
  			function showDtPic()
  			{
  				if(document.rm_accvousfrom.cmbSearch.value=="p.inwardDt")
  				{
  					document.getElementById("dtpicker").style.display="inline";
  				}
  				else
  				{
  					document.getElementById("dtpicker").style.display="none";
  				}
  			}
  			
  			
  			function onSearch(e,paraStatusFlag,paraRecFlag,paraUpdStatusFlag)
  			{
  				
  				if(e.keyCode==13)
  				{
  					var str= document.rm_accvousfrom.txtSearch.value;
					if(str=='')
					{
						document.rm_accvousfrom.parentUrl1.value = "";
						alert("Search Text is Empty");
						return false;
					}
					else
					{
						document.rm_accvousfrom.parentUrl1.value='';
  						searching(paraStatusFlag,paraRecFlag,paraUpdStatusFlag);
  					}
  				}
  			}			  