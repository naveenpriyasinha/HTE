function showRowCell(element)
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

function RemoveTableRow(obj, tblId)
{
	var rowID = showRowCell(obj); 
	var tbl = document.getElementById(tblId); 
	tbl.deleteRow(rowID); 
}

function sentCasesSearch()
{
	document.getElementById("tblSentBackCaseDtls").style.display="block";
	document.getElementById("btnUpdate").style.display="inline";
	document.getElementById("btnSaveForward").style.display="inline";
	document.getElementById("btnReset").style.display="none";
	document.getElementById("trForwardTo").style.display="block";
	document.getElementById("btnFind").style.display="none";
	document.getElementById("fsAdvSearchCases").style.display="none";
	document.getElementById("fsSearchCases").style.display="block";
}

function sentBackAdvSearch()
{
	document.getElementById("fsAdvSearchCases").style.display="block";
	document.getElementById("fsSearchCases").style.display="none";
	document.getElementById("btnAdvSearch").style.display="none";
	document.getElementById("btnReset").style.display="inline";
	document.getElementById("btnFind").style.display="inline";
	document.getElementById("tblSentBackCaseDtls").style.display="none";
	document.getElementById("btnUpdate").style.display="none";
	document.getElementById("btnSaveForward").style.display="none";
	document.getElementById("trForwardTo").style.display="none";
}

function advanceSearch(flag)
{
	var caseStatus = document.getElementById("DraftFlag").value;
	if(flag == 1){
		if(caseStatus == 'A'){
		document.getElementById("tblFrwdCasesFromDEO").style.display="none";
		document.getElementById("fsPensionCasesSearch").style.display="none";
		document.getElementById("btnSendToAG").style.display="none";
		document.getElementById("btnDdoMistake").style.display="none";
		document.getElementById("btnAgQuery").style.display="none";
		document.getElementById("btnFind").style.display="";
		document.getElementById("btnBack").style.display="";
		document.getElementById("btnAdvSearch").style.display="none";
		document.getElementById("fsPensionCaseAdvSearch").style.display="block";
		}else{
			document.getElementById("tblFrwdCasesFromDEO").style.display="none";
			document.getElementById("fsPensionCasesSearch").style.display="none";
			document.getElementById("btnFind").style.display="";
			document.getElementById("btnBack").style.display="";
			document.getElementById("btnAdvSearch").style.display="none";
			document.getElementById("fsPensionCaseAdvSearch").style.display="block";
		}
	}else if(flag == 2){
		document.getElementById("tblFrwdCasesFromDEO").style.display="none";
		document.getElementById("fsPensionCasesSearch").style.display="none";
		document.getElementById("btnFind").style.display="";
		document.getElementById("btnBack").style.display="";
		document.getElementById("btnAdvSearch").style.display="none";
		document.getElementById("fsPensionCaseAdvSearch").style.display="block";
	}
}

function back(flag)
{
	var caseStatus = document.getElementById("DraftFlag").value;
	if(flag == 1){
		if(caseStatus == 'A'){
			document.getElementById("tblFrwdCasesFromDEO").style.display="";
			document.getElementById("fsPensionCasesSearch").style.display="";
			document.getElementById("btnSendToAG").style.display="";
			document.getElementById("btnDdoMistake").style.display="";
			document.getElementById("btnAgQuery").style.display="";
			document.getElementById("btnFind").style.display="none";
			document.getElementById("btnBack").style.display="none";
			document.getElementById("btnAdvSearch").style.display="";
			document.getElementById("fsPensionCaseAdvSearch").style.display="none";	
		}else{
			document.getElementById("tblFrwdCasesFromDEO").style.display="";
			document.getElementById("fsPensionCasesSearch").style.display="";
			document.getElementById("btnFind").style.display="none";
			document.getElementById("btnBack").style.display="none";
			document.getElementById("btnAdvSearch").style.display="";
			document.getElementById("fsPensionCaseAdvSearch").style.display="none";
		}
	}else if(flag == 2){
			document.getElementById("tblFrwdCasesFromDEO").style.display="";
			document.getElementById("fsPensionCasesSearch").style.display="";
			document.getElementById("btnFind").style.display="none";
			document.getElementById("btnBack").style.display="none";
			document.getElementById("btnAdvSearch").style.display="";
			document.getElementById("fsPensionCaseAdvSearch").style.display="none";
	}
	
}
function viewPensionCaseList(flag)
{
	var lFlagValidate = 1;
	if(flag == 1){
		if(document.getElementById("cmbSearchBy").value == -1){
			alert('Please select any criteria for search.');
			document.getElementById("txtSearchVal").value="";	
			document.getElementById("cmbSearchBy").focus();
			return;
		}else{
			var searchType = document.getElementById("cmbSearchBy").value;
			if(searchType == 'r.Inward_No'  || searchType == 'r.Sevarth_Id' || searchType == 'r.Name'){
				if(document.getElementById("txtSearchVal").value == "")
				{
					alert('Please enter search value');
					document.getElementById("txtSearchVal").value="";		
					document.getElementById("txtSearchVal").focus();
					return;
				}
			}else if(searchType == 'r.Retirement_Date' || searchType == 'r.Inward_Date'){
				if(document.getElementById("txtSearchValDt").value == "")
				{
					alert('Please enter search value');
					document.getElementById("txtSearchValDt").value="";		
					document.getElementById("txtSearchValDt").focus();
					return;
				}
			}
			else if(searchType == 'r.Inward_Type'){
				if(document.getElementById("cmbCaseType").value == "-1")
				{
					alert('Please select Inward Type');						
					document.getElementById("cmbCaseType").focus();
					return;
				}
			}else if(searchType == 'r.Pension_Type'){
				if(document.getElementById("cmbClassOfPnsn").value == "-1")
				{
					alert('Please select Pension Type');						
					document.getElementById("cmbClassOfPnsn").focus();
					return;
				}
			}
			else if(searchType == 'r.Bank_Branch_Name'){
				if(document.getElementById("cmbBankName").value == "-1")
				{
					alert('Please select Bank Name');						
					document.getElementById("cmbBankName").focus();
					return;
				}
				if(document.getElementById("cmbTargetBranchName").value == "-1")
				{
					alert('Please select Branch Name');						
					document.getElementById("cmbTargetBranchName").focus();
					return;
				}
			}	
		
			
		}
		lFlagValidate = 2;
	}else if(flag == 2){
		if(document.getElementById("txtSearchFromDate").value.trim() != ""){
			if(document.getElementById("txtSearchToDate").value.trim() != "" ){
				lFlagValidate = 2;	
			}else{
				alert('Please enter To Date');
				document.getElementById("txtSearchToDate").focus();
				return;
			}			
		}else if(document.getElementById("txtSearchToDate").value.trim() != "" ){
			if(document.getElementById("txtSearchFromDate").value.trim() != "" ){
				lFlagValidate = 2;	
			}else{
				alert('Please enter From Date');
				document.getElementById("txtSearchFromDate").focus();
				return;
			}
		}else if(document.getElementById("txtSevaarthId").value.trim() != ""){
			lFlagValidate = 2;
		}else if(document.getElementById("txtInwardNo").value.trim() != ""){
			lFlagValidate = 2;
		}else if(document.getElementById("txtDateOfRetiremt").value.trim() != ""){
			lFlagValidate = 2;
		}else if(document.getElementById("txtName").value.trim() != ""){
			lFlagValidate = 2;
		}else if(document.getElementById("cmbDepartment").value != "-1"){
			lFlagValidate = 2;
		}else if(document.getElementById("cmbTypeOfPnsn").value != "-1"){
			lFlagValidate = 2;
		}		
	}
	var cmbSearchBy = document.getElementById("cmbSearchBy").options[document.getElementById('cmbSearchBy').selectedIndex].text;
	var txtSearchVal;
	var txtBranchName;
	if(cmbSearchBy == 'Inward No'  || cmbSearchBy=='Sevaarth ID' || cmbSearchBy=='Name of Pensioner'){
		txtSearchVal = document.getElementById("txtSearchVal").value;	
		
	}else if(cmbSearchBy == 'Inward Date' || cmbSearchBy == 'Retirement Date'){
		txtSearchVal = document.getElementById("txtSearchValDt").value;	
	}else if(cmbSearchBy == 'Inward Type'){
		txtSearchVal = document.getElementById("cmbCaseType").value;	
	}else if(cmbSearchBy == 'Pension Type'){
		txtSearchVal = document.getElementById("cmbClassOfPnsn").value;	
	}else if(cmbSearchBy == 'Bank Branch Name'){
		txtSearchVal = document.getElementById("cmbBankName").value;
		txtBranchName = document.getElementById("cmbTargetBranchName").value;
	}	
	if(lFlagValidate == 2){
		var txtSevaarthId = document.getElementById("txtSevaarthId").value;
		
		var DraftFlag = document.getElementById("DraftFlag").value;  
		
		var CasesFrom = document.getElementById("CasesFrom").value;
		
		var txtSearchFromDate =  document.getElementById("txtSearchFromDate").value;
	
		var txtSearchToDate = document.getElementById("txtSearchToDate").value;
	
	
		var txtInwardNo = document.getElementById("txtInwardNo").value;
	
		var txtDateOfRetiremt = document.getElementById("txtDateOfRetiremt").value;
			
		var txtName =  document.getElementById("txtName").value;
			
		var cmbDepartment =  document.getElementById("cmbDepartment").value;
			
		var cmbTypeOfPnsn = document.getElementById("cmbTypeOfPnsn").value;
		
		document.getElementById("fsPensionCaseAdvSearch").style.display="none";
		document.getElementById("fsPensionCasesSearch").style.display="block";
		
		document.getElementById("btnFind").style.display="none";
		document.getElementById("btnReset").style.display="none";
		var url=null;
		if(document.getElementById('cmbSearchBy').value == -1)
		{
			
			url = "ifms.htm?actionFlag=showPensionCaseList&CasesFrom="+CasesFrom+"&DraftFlag="+DraftFlag+"&txtSearchVal="+txtSearchVal+"&txtSearchFromDate="+txtSearchFromDate+
					  "&txtSearchToDate="+txtSearchToDate+"&txtSevaarthId="+txtSevaarthId+
				 	  "&txtInwardNo="+txtInwardNo+"&txtDateOfRetiremt="+txtDateOfRetiremt+"&txtName="+txtName+
					  "&cmbDepartment="+cmbDepartment+"&cmbTypeOfPnsn="+cmbTypeOfPnsn+"&txtBranchName="+txtBranchName;	
			
		}
		else
		{
			url = "ifms.htm?actionFlag=showPensionCaseList&CasesFrom="+CasesFrom+"&DraftFlag="+DraftFlag+"&cmbSearchBy="+cmbSearchBy+"&txtSearchVal="+txtSearchVal+"&txtSearchFromDate="+txtSearchFromDate+
			   		  "&txtSearchToDate="+txtSearchToDate+"&txtSevaarthId="+txtSevaarthId+
			   		  "&txtInwardNo="+txtInwardNo+"&txtDateOfRetiremt="+txtDateOfRetiremt+"&txtName="+txtName+
			   		  "&cmbDepartment="+cmbDepartment+"&cmbTypeOfPnsn="+cmbTypeOfPnsn+"&txtBranchName="+txtBranchName;
			
		}
		showProgressbar();
		document.getInwardCases.action = url ;
		enableAjaxSubmit(true);
		
		document.getInwardCases.submit();
	}else{
		alert('Please select any criteria for search.');
		document.getElementById("txtSearchFromDate").focus();
		return;
	}
}

function draftsAdvSearch()
{
	document.getElementById("fsDraftsSearchCases").style.display="none";
	document.getElementById("fsDraftsAdvSearchCases").style.display="block";
	document.getElementById("btnAdvSearch").style.display="none";
	document.getElementById("tblDraftsDtls").style.display="none";
	document.getElementById("btnFind").style.display="inline";
	document.getElementById("btnReset").style.display="inline";
}

function SearchDrafts()
{
	document.getElementById("fsDraftsSearchCases").style.display="block";
	document.getElementById("tblDraftsDtls").style.display="block";
	document.getElementById("btnFind").style.display="none";
	document.getElementById("fsDraftsAdvSearchCases").style.display="none";
	document.getElementById("btnAdvSearch").style.display="inline";
	document.getElementById("btnReset").style.display="none";
}
function popupCaseDueRetmntList()
{  
   window.location.href='ifms.htm?viewName=CasesDueForRetirementList';
}

function backToParentPage()
{
	 window.location.href='ifms.htm?viewName=SearchCasesDueForRetirement';	
}

function receiveCasesFromCEO()
{
	 window.location.href='ifms.htm?viewName=CasesFromCEOList';	
}

function backToSearch()
{
	window.location.href='ifms.htm?viewName=ReceivedCasesFromDEO';	
}

function window_new_update(url)
{
	var newWindow = null;
   	var height = screen.height - 150;
   	var width = screen.width;
   	var urlstring = url;
   	var urlstyle = "height="+height+",width="+width+",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
   	newWindow = window.open(urlstring, "PensionInward", urlstyle);
}


function BackToSearchAG()
{
	window.location.href='ifms.htm?viewName=SearchCasesToAG';
}

function listOfCasesToAG()
{
	window.location.href='ifms.htm?viewName=ListOfCasesToAG';
}

function chkValue(){
	if(document.getElementById("btnSaveForward")!=null)
	{
		if(document.getElementById("cmbForwardTo").value==-1){
			document.getElementById("btnSaveForward").disabled=true;	
			if(document.getElementById("btnSave")!=null)
			  document.getElementById("btnSave").disabled=false;
		}
		else{
			if(document.getElementById("btnSave")!=null)
			  document.getElementById("btnSave").disabled=true;
			document.getElementById("btnSaveForward").disabled=false;	
		}
	}
}
function SaveData(flag)
{
	
	//alert (flag);
	//alert(document.getElementById("pensionCaseType").value);
	//alert("inside save data");
	var uri;
	var comments = document.getElementById("txtComments").value;
//Added by shraddha for deputation module
	
	var flagPen = document.getElementById("flagPen").value;
	
    	
    	getFmlyAddrFlagValues();
	    getNomAddrFlagValues();	    
	   /* if(flag == "F"){
	    	if(document.getElementById("cmbForwardTo").value=="-1"){
	    		alert('Please select Forward To');
	    		document.getElementById("cmbForwardTo").focus();
	    		return false;
	    	}
	    }*/
	    
	    //alert("inward pension id::"+document.getElementById("inwardPensionId").value);
	   
	    
	    
	    
	    if(document.getElementById("inwardPensionId").value=="")
	    {
	    	 var pensionCaseType=document.getElementById("pensionCaseType").value;
	    	
	   
	    	if(pensionCaseType=='Provisional')
	    	{
	    		var order_no=document.getElementById("orderno").value;
	    		
	    		 var order_date=document.getElementById("orderDate").value;
	    		 var attachmentProv=document.getElementById("cntProv").value;
	    		
	    		if(attachmentProv=="0"){
	    			alert("Please attach the Provisional Certificate");
	    			return ;
	    		}
	    		
	    		//alert(order_no+" "+order_date);
	    		if(order_no=='')
	    		{
	    			alert("Please enter order No.");
	    			return ;
	    		}
	    		else if(order_date=='')
	    		{
	    			alert("Please enter order Date.");
	    			return ;
	    		}
	    		
	    	
	    		
	    		
	    		
	    		
	    		
	    	//	var lLngAttachId=document.getElementById("lLngAttachId").value;
	    		//alert("order_no :"+order_no+"  order_date: "+order_date);
	    		 var judCasePendingReason=document.getElementById("judCasePendingReason").value;
	    		 var deptInqPendingReason=document.getElementById("deptInqPendingReason").value;
	    		
	    		uri = 'ifms.htm?actionFlag=insertPensionCase&DraftFlag='+flag+"&pensionCaseType="+document.getElementById("pensionCaseType").value+
	    		 "&order_no="+order_no+"&order_date="+order_date+"&judCasePendingReason="+judCasePendingReason+"&deptInqPendingReason="+deptInqPendingReason+"&flagPen="+flagPen;
	    	}
	    	else
		   uri = 'ifms.htm?actionFlag=insertPensionCase&DraftFlag='+flag+"&pensionCaseType="+document.getElementById("pensionCaseType").value+"&flagPen="+flagPen;
	    }
	    else
	    {//alert("inside else");
	    	//alert(document.getElementById("pensionCaseType").value);
	    	var pensionCaseType1=document.getElementById("pensionCaseType").value;
	    	
	    	//alert("first if"+pensionCaseType1);
	    	if(pensionCaseType1=='Provisional')
	    	{
	    		var order_no1=document.getElementById("orderno").value;
	    		
	    		var order_date1=document.getElementById("orderDate").value;
	    		//alert(order_no+" "+order_date);
	    		if(order_no1=='')
	    		{
	    			alert("Please enter order No.");
	    			return ;
	    		}
	    		else if(order_date1=='')
	    		{
	    			alert("Please enter order Date.");
	    			return ;
	    		}
	    	//	var lLngAttachId=document.getElementById("lLngAttachId").value;
	    		//alert("order_no :"+order_no+"  order_date: "+order_date);
	    		url= "&order_no="+order_no1+"&order_date="+order_date1;
	    	}
	    	else{
	    		url="";
	    	}
	    	uri = 'ifms.htm?actionFlag=insertPensionCase&comments='+comments+'&DraftFlag='+flag+'&InwardId='+document.getElementById("inwardPensionId").value+'&status='+document.getElementById("hdnStatusId").value+"&pensionCaseType=Y&flagPen="+flagPen;
	 uri=uri+url;
	
	    }
	  
	    var currRole = document.getElementById("currRole").value;
	    var caseStatus = document.getElementById("statusFlag").value;
	    
	    /*if(currRole == "700001"){
	    	document.getElementById("btnSave").disabled=true;
	    	//document.getElementById("btnUpdate").disabled=true;
	    	document.getElementById("btnSaveForward").disabled=true;
	    	//document.getElementById("btnForward").disabled=true;
	    }else if(currRole == "700002"){
	    	document.getElementById("btnUpdate").disabled=true;
	    	document.getElementById("btnApprove").disabled=true; 
		    document.getElementById("btnReject").disabled=true;		    	
	    }*/
	    
	    showProgressbar();
	    savePensionDetailsUsingAjx(uri);    
   
}
 
function savePensionDetailsUsingAjx(uri)
{
	
	
   xmlHttp=GetXmlHttpObject();

   if (xmlHttp==null)
   {
      return;
   }  
        
   var url = runForm(0);   
   url = uri + url; 
   var elementMinor = document.getElementsByName("chkMinor");
	for(var i=0;i<elementMinor.length;i++)
	{
		if(elementMinor[i].checked)
		{
			url = url+'&chkMinorVar=Y';
		}
		else
		{
			url = url+'&chkMinorVar=';
		}
	}
	var elementMarried = document.getElementsByName("chkMarried");
	for(var j=0;j<elementMarried.length;j++)
	{
		if(elementMarried[j].checked)
		{
			url = url+'&chkMarriedVar=Y';
		}
		else
		{
			url = url+'&chkMarriedVar=';
		}
	}
	var elementFamlyPen = document.getElementsByName("chkFamlyPen");
	for(var k=0;k<elementFamlyPen.length;k++)
	{
		if(elementFamlyPen[k].checked)
		{
			url = url+'&chkFamlyPenVar=Y';
		}
		else
		{
			url = url+'&chkFamlyPenVar=';
		}
	}
	
   xmlHttp.onreadystatechange=caseStateChanged;
   xmlHttp.open("POST",uri,false);
   xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
   
   xmlHttp.send(url);
   
}


function caseStateChanged() 
{ 
	
   if (xmlHttp.readyState==complete_state)
   { 
	  var XMLDoc=xmlHttp.responseXML.documentElement;
      
      var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
      
     
      if(XmlHiddenValues[0].childNodes[1].firstChild.nodeValue=='insert')
      {
    	 alert("Pension Case has been saved successfully.Inward No for the pension case is :  "+XmlHiddenValues[0].childNodes[0].firstChild.nodeValue);
         inwardId=XmlHiddenValues[0].childNodes[2].firstChild.nodeValue;
         hideProgressbar();         
         self.location.href="ifms.htm?actionFlag=loadSearchPensionCase&DraftFlag=D&elementId=376395";
        // winCls();
      }
      else if(XmlHiddenValues[0].childNodes[1].firstChild.nodeValue=='update')
      {
    	  alert("Pension Case "+' '+""+'-'+XmlHiddenValues[0].childNodes[0].firstChild.nodeValue+" has been Updated Successfully.");
    	  hideProgressbar();
    	  if(XmlHiddenValues[0].childNodes[3].firstChild.nodeValue=='REVISION' && XmlHiddenValues[0].childNodes[4].firstChild.nodeValue!='FWDBYDEO'){
    			  self.location.href="ifms.htm?actionFlag=loadSearchPensionCase&DraftFlag=D&elementId=376395";    		  
    	  }else if(XmlHiddenValues[0].childNodes[3].firstChild.nodeValue=='REVISION'){    		  
    			  self.location.href="ifms.htm?actionFlag=loadSearchPensionCase&DraftFlag=F&CasesFrom=V&elementId=376399";
    	  }else{
    		  self.close();
        	  window.opener.location.reload();
    	  }
    	  
      }
      else//forward
      {
    	  alert("Pension Case "+' '+""+'-'+XmlHiddenValues[0].childNodes[0].firstChild.nodeValue+" has been Forwarded Successfully.");
    	  hideProgressbar();
    	//  self.location.href="ifms.htm?actionFlag=loadSearchPensionCase&DraftFlag=D&elementId=376395";    	  
    	  window.opener.location.reload();
    	  window.close();
      }
   }
}



function validPensionInward()
{
	var flagPen=document.getElementById("flagPen").value;
	//alert("flagPen*****"+flagPen);
	var pensionCaseType=document.getElementById("pensionCaseType").value;
	//alert("pensionCaseType"+pensionCaseType);
	var caseStatus = document.getElementById("draftFlag").value;
	//alert("caseStatus"+caseStatus);
	var pensionFlag = document.getElementById("pensionFlag").value;
	//alert(pensionFlag);
	/***************Basic detais tab validation ***************/
	
	if(pensionFlag != 'P1' && flagPen!='N'){
		
		
	if(IsEmptyFun("cmbCaseType",CASETYPE,'0')==false)
	{
		//alert("validPensionInward1");
		return false;
	}
	if(IsEmptyFun("cmbPayComsn",PAYCOMSN,'0')==false)
	{
		//alert("validPensionInward2");
		return false;
	}
	if(IsEmptyFun("cmbClassOfPnsn",CLSOFPNSN,'0')==false)
	{
		//alert("validPensionInward3");
		return false;
	}
	if(IsEmptyFun("txtSevaarthId",SEVAARTHID,'0')==false)
	{
		//alert("validPensionInward4");
		return false;
	}
	if(IsEmptyFun("cmbPnsnCatg",PNSNCATGRY,'0')==false)
	{
		//alert("validPensionInward5");
		return false;
	}
	if(IsEmptyFun("txtDDOCode",DDOCODE,'0')==false)
	{
		//alert("validPensionInward6");
		return false;
	}
	
	
	
	if(document.getElementById("txtGratDDOCode").value == "")
	{
		alert('Please enter Gratuity DDO Code');	
		document.getElementById("txtGratDDOCode").focus();
		return false;
	}
	
	
	
	if(document.getElementById("txtPenDDOCode").value == "")
	{
		alert('Please enter Pension DDO Code');	
		document.getElementById("txtPenDDOCode").focus();
		return false;
	}
	
	//added by shraddha
	//if(flagPen == 'Y' || caseStatus=='P' || pensionCaseType=='Deputation'){
	if(document.getElementById("txtDateOfConfirmation").value == "")
	{
		alert('Please enter Date of Confirmation');						
		document.getElementById("txtDateOfConfirmation").focus();
		return false;
	}
	//}
	
	
	/*if(IsEmptyFun("txtCvpAppDate","Please enter CVP Application Date",'0')==false)
	{
		return false;
	}*/
	var lagecyFlag = document.getElementById("hdnLagecyFlag").value;
	if(lagecyFlag == 'Y'){
		//alert("validPensionInward7");
		if(IsEmptyFun("txtOutwardNo",OUTWARDNO,'0')==false)
		{
			//alert("validPensionInward8");
			return false;
		}
		if(IsEmptyFun("txtOutwardDate",OUTWARDDATE,'0')==false)
		{
			//alert("validPensionInward9");
			return false;
		}
	}
	if(IsEmptyFun("txtPnsnrName",NAME,'0')==false)
	{
		//alert("validPensionInward10");
		return false;
	}
	if(document.getElementById("cmbClassOfPnsn").value != FAMILYPNSN)
	{
		//alert("validPensionInward11");
		if(IsEmptyFun("txtDateOfRetiremt",DATEOFRETMNT,'0')==false)
		{
			//alert("validPensionInward12");
			return false;
		}
	}else{
		//alert("validPensionInward1 else");
		if(IsEmptyFun("txtDateOfExpiry",DTOFEXPIRY,'0')==false)
		{
			//alert("validPensionInward13");
			return false;
		}
	}
	if(IsEmptyFun("txtDateOfBirth",DATEOFBIRTH,'0')==false)
	{
		//alert("validPensionInward14");
		return false;
	}
	if(IsEmptyFun("txtDateOfStartingService",DATEOFSTARTSERV,'0')==false)
	{
		//alert("validPensionInward15");
		return false;
	}
	/*if(IsEmptyFun("txtHeight",HEIGHTFEET,'0')==false)
	{
		return false;
	}
	if(IsEmptyFun("txtInches",HEIGHTINCH,'0')==false)
	{
		return false;
	}*/
/*	if(IsEmptyFun("cmbGroupOrClass",GROUP,'0')==false)
	{
		return false;
	}*/
	if(IsEmptyFun("txtDesignation",DESIGNATION,'0')==false)
	{
	//	alert("validPensionInward16");
		return false;
	}	
	if(IsEmptyFun("cmbHeadOfOff","Please Select Admin Department",'0')==false)
	{
	//	alert("validPensionInward17");
		return false;
	}
	if(IsEmptyFun("cmbFieldDept",DEPARTMENT,'0')==false)
	{
	//	alert("validPensionInward18");
		return false;
	}
	/*if(IsEmptyFun("txtUID",UID,'0')==false)
	{
		return false;
	}*/
	if(IsEmptyFun("cmbPrState",PRSTATE,'0')==false)
	{
		//alert("validPensionInward19");
		return false;
	}
	
	/*if(IsEmptyFun("txtOffPincode","Please enter the Pincode of the Office Address",'0')==false)
	{
		return false;
	}*/
	//updated by vishnu-start for pincode mandatory
	//if(flagPen == 'Y' || caseStatus=='P' || pensionCaseType=='Deputation'){
	if(IsEmptyFun("txtPrPincode","Please enter the Pincode of the Present Address",'0')==false)
	{
		//alert("validPensionInward20");
		return false;
	}
	//}
	//updated by vishnu-end for pincode mandatory
	//if(flagPen == 'Y' || caseStatus=='P' || pensionCaseType=='Deputation'){
	if(IsEmptyFun("cmbPrTownCityDist",PRTOWNCITYDIST,'0')==false)
	{
	//	alert("validPensionInward21");
		return false;
	}
	//}
	/*if(IsEmptyFun("cmbAgOfficeForPension",AGOFFICE,'0')==false)
	{
		return false;
	}*/
	//if(flagPen == 'Y' || caseStatus=='P' || pensionCaseType=='Deputation'){
	if(IsEmptyFun("cmbTreasuryForPension",TRSRYPNS,'0')==false)
	{
	//	alert("validPensionInward22");
		return false;
	}	
	//}
	/*if(document.getElementById("radioIsTreasuryChangeYes").checked){
		if(IsEmptyFun("cmbAgOffice",AGOFFICEPAY,'0')==false)
		{
			return false;
		}
		if(IsEmptyFun("cmbTreasury",TRSRYPNSPAY,'0')==false)
		{
			return false;
		}
	}*/
	var caseType = document.getElementById("cmbCaseType").value;
	if(caseType == 'REVISION'){
		if(IsEmptyFun("txtNewMonthAmt",NEWCVPMONTH,'2')==false)
		{
		//	alert("validPensionInward23");
			return false;
		}
		if(IsEmptyFun("txtNewCVPAmt",NEWCVPAMT,'2')==false)
		{
		//	alert("validPensionInward24");
			return false;
		}
		if(IsEmptyFun("txtNewTotPnsnAmt",NEWPENSIONAMT,'2')==false)
		{
		//	alert("validPensionInward25");
			return false;
		}
	
		
		
		
	}

	/******************Pay and Service detais tab validation **************/
	if(validPensionCasePayService() == false)
	{
		//alert("validPensionInward26");
		return false;
	}	
	/******************Family detais tab validation **************/
	if(validPensionCaseFamilyDtls() == false)
	{
		//alert("validPensionInward27");
		return false;
	}
	/******************Recovery tab validation **************/
	if(validPensionCaseRecovery() == false)
	{
		//alert("validPensionInward28");
		return false;
	}
	if(validateAgDtls() == false)
	{
		//alert("validPensionInward29");
		return false;
	}
	if(validPensionCaseAutho() == false)
	{
		//alert("validPensionInward30");
		return false;
	}
	var checkListRowLength = document.getElementById("tblCertDtls").rows.length;
	//if(flagPen == 'Y' || caseStatus=='P' || pensionCaseType=='Deputation'){
	if(checkListRowLength <= 1)
	{
		//alert("validPensionInward31");
		alert('Please add Certificate Details');
		goToTab(4);
		return false;
	}
	//}
	
}
	if(pensionFlag == 'P1' || flagPen=='N'){
		
		
		if(document.getElementById("txtGratDDOCode").value == "")
		{
			alert('Please enter Gratuity DDO Code');	
			document.getElementById("txtGratDDOCode").focus();
			return false;
		}
		
		
		
		if(document.getElementById("txtPenDDOCode").value == "")
		{
			alert('Please enter Pension DDO Code');	
			document.getElementById("txtPenDDOCode").focus();
			return false;
		}
		
	
	}
	return true;
	}


function IsEmptyFun(varStr,alrtStr,tabNo)
{
	var element = document.getElementById(varStr).value;
	var lStr = new String(element);
	element = lStr.trim();
	if( element == "" || element == "0" || element == "-1" || element == "0.00")
	{
		alert(alrtStr);
		goToFieldTab(varStr,tabNo);
		return false;
	}
	return true;
}


function goToFieldTab(field,cnt)
{
	goToTab(cnt);

	if(document.getElementById(field) != null && ! document.getElementById(field).disabled)
	{
		document.getElementById(field).focus();
	}
}

/*---------Date Validation---------*/
var dtCh= "/";
var minYear=1900;
var maxYear=2100;

function chkValidDate(dtCtrl){

	var dtStr = dtCtrl.value;
	
	if(dtStr != '')
	{
		var daysInMonth = DaysArray(12);
		var pos1=dtStr.indexOf(dtCh);
		var pos2=dtStr.indexOf(dtCh,pos1+1);
		var strDay=dtStr.substring(0,pos1);
		var strMonth=dtStr.substring(pos1+1,pos2);
		var strYear=dtStr.substring(pos2+1);
		strYr=strYear;
		if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1);
		if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1);
		for (var i = 1; i <= 3; i++) {
			if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1);
		}
		month=parseInt(strMonth);
		day=parseInt(strDay);
		year=parseInt(strYr);
		if (pos1==-1 || pos2==-1){

			dtCtrl.focus();
			alert('Please Enter Date in dd/mm/yyyy format ');
			dtCtrl.value='';
			return false;
		}
		if (strMonth.length<1 || month<1 || month>12){

			dtCtrl.focus();
			alert('Please Enter valid Month ');
			dtCtrl.value='';
			return false;
		}
		if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){

			dtCtrl.focus();
			alert('Please Enter valid Date ');
			dtCtrl.value='';
			return false;
		}
		if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){

			dtCtrl.focus();
			alert('Please Enter valid year ');
			dtCtrl.value='';
			return false;
		}
		if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){

			dtCtrl.focus();
			alert('Please Enter Date in dd/mm/yyyy format ');
			dtCtrl.value='';
			return false;
		}
		return true;
	}
	return false; 
}
/*---------Comparing two dates---------*/
function compareDates(fieldName1,fieldName2,alrtStr,flag)
{
	
	
	var Date1 = fieldName1.value;
	var Date2 = fieldName2.value;
    var la_Date1 = new Array();
    la_Date1 = Date1.split("/");
    var day1=parseFloat(la_Date1[0]);
    var month1=parseFloat(la_Date1[1]);
    var year1=parseFloat(la_Date1[2]);

    var la_Date2 = new Array();
    la_Date2 = Date2.split("/");
    var day2=parseFloat(la_Date2[0]);
    var month2=parseFloat(la_Date2[1]);
    var year2=parseFloat(la_Date2[2]);

    if (year2 == year1 && month2 == month1 && day2 == day1)
    {
    	if(flag == '=')
    	{
    		alert(alrtStr);
    	    fieldName1.focus();
    	}
    	else
    	{
	        return true;
	    }
    }
    else if( year2 > year1 )
    {
        return true;
    }
    else if( year2 < year1 && flag != '=')
    {
        alert(alrtStr);
        if(flag == '<')
        {
    	    fieldName1.focus();
    	}
    	else if(flag == '>')
    	{
    	    fieldName2.focus();
    	}
    }
    else if (flag != '=')
    {
        if( month2 > month1 )
        {
            return true;
        }
        else if( month2 < month1 )
        {     
             alert(alrtStr);
             if(flag == '<')
	        {
    		    fieldName1.focus();
	    	}
    		else if(flag == '>')
    		{
	    	    fieldName2.focus();
    		}
        }
        else
        {
            if( day2 > day1 )
            {
                return true;
            }
            else if( day2 < day1 )
            {
                 alert(alrtStr);
                 if(flag == '<')
			     {
    			    fieldName1.focus();
			   	}
		    	else if(flag == '>')
    			{
    			    fieldName2.focus();
		    	}
            }
        }
    }
    return true ;
}

/*---------Reset Fields---------*/
function resetAllFields()
{
	document.getElementById("cmbCaseType").value=-1;
	document.getElementById("cmbPayComsn").value=-1;
	document.getElementById("txtInwardId").value='';
	document.getElementById("cmbClassOfPnsn").value=-1;
    document.getElementById("txtSevaarthId").value='';
	document.getElementById("cmbPnsnCatg").value=-1;
	document.getElementById("txtDDOCode").value='';
	document.getElementById("txtPnsnrName").value='';
	document.getElementById("txtDateOfRetiremt").value='';
    document.getElementById("txtDateOfBirth").value='';
    document.getElementById("txtDateOfStartingService").value='';
    document.getElementById("txtHeight").value='';
    document.getElementById("txtInches").value='';
    document.getElementById("cmbGroupOrClass").value=-1;
    document.getElementById("txtPersRemarks").value='';
    document.getElementById("txtDesignation").value='';
    document.getElementById("txtPrFlatDoorBlk").value='';
    document.getElementById("txtPrRoadPostOff").value='';
    document.getElementById("txtPrAreaLocality").value='';
    document.getElementById("cmbPrTownCityDist").value=-1;
    document.getElementById("txtPrPincode").value='';
    document.getElementById("txtLandlineNo").value='';
    document.getElementById("txtMobileNo").value='';
    document.getElementById("txtEmailId").value='';
    document.getElementById("txtARFlatDoorBlk").value='';
    document.getElementById("txtARRoadPost").value='';
    document.getElementById("txtARAreaLocality").value='';
    document.getElementById("cmbARTownCityDist").value=-1;
    document.getElementById("txtARPincode").value='';
    document.getElementById("cmbFieldDept").value=-1;
    document.getElementById("cmbHeadOfOff").value=-1;
    document.getElementById("cmbTreasuryForPension").value=-1;
    document.getElementById("cmbTreasury").value=-1;
    document.getElementById("txtOffFlatDoorBlk").value='';
    document.getElementById("txtOffLandlineNo").value='';
    document.getElementById("txtOffRoadPostOff").value='';
    document.getElementById("txtOffMobileNo").value='';
    document.getElementById("txtOffAreaLocality").value='';
    document.getElementById("txtOffEmailId").value='';
    document.getElementById("cmbOffTownCityDist").value=-1;
    document.getElementById("txtOffPincode").value='';
    document.getElementById("txtBank").value='';
    document.getElementById("txtActNo").value='';
    document.getElementById("txtBranch").value='';
    document.getElementById("txtIFSCCode").value='';
    document.getElementById("txtBankAddress").value='';
}

function addrAfterRetmnt(obj)
{
	   if(obj.value == "Y")
	   {
		   document.getElementById("fsAddrAfterRetmnt").style.display="none";
	   }
	   else if(obj.value == "N")
	   {
		   document.getElementById("fsAddrAfterRetmnt").style.display="inline";
		   
	     
	   }
}

function commute(radioObj)
{	
	var pensionerType = document.getElementById("cmbPnsnCatg").value;
	var commute;
	var radioLength = radioObj.length;
	for(var i = 0; i < radioLength; i++) {
		if(radioObj[i].checked) {
			commute = radioObj[i].value;
		}
	}
	if(commute == "Y")
	{
		document.getElementById("divForCom").style.display="inline";
		document.getElementById("txtDoWantCommute").style.display="inline";
		document.getElementById("txtDoWantCommute").value = '40';		
		
		var cmbPayComsn = document.getElementById("cmbPayComsn").value;
		 if(cmbPayComsn == 'PadmanabhanComm')
			document.getElementById("txtDoWantCommute").value = '50';		
				
	}
		
		else{
		document.getElementById("divForCom").style.display="none";
		document.getElementById("txtDoWantCommute").style.display="none";
		document.getElementById("txtDoWantCommute").value = '';			
	}
	
	/*
	if(obj.value == "Y")
	{
		document.getElementById("txtDoWantCommute").style.display="inline";
	
	}
	else if(obj.value == "N")
	{
		 document.getElementById("txtDoWantCommute").style.display="none";
	}*/
}

function chckLength(fieldname)
{
	var numberField=fieldname.value;
	if(numberField.length>0)
	{
		if(numberField.length >= 10)
		{
			return true;
		}
		else
		{
			alert("Mobile Number must be of 10 digit. ");
			fieldname.focus();
			return false;
		}	
	}
	return true;
}

function showCase(url)
{
	var newWindow;
	var height = screen.height - 100;
	var width = screen.width;
	var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";

	newWindow = window.open(url, "frmPensionCase", urlstyle);
}


function validateEmailID(txtEmail,alerttxt)
{
	var emailfilter=/^\w+[\+\.\w-]*@([\w-]+\.)*\w+[\w-]*\.([a-z]{2,4}|\d+)$/i;
	if(txtEmail.value.length > 0)
	{
		var returnval=emailfilter.test(txtEmail.value);
		if (returnval==false)
		{
			alert(alerttxt);
			txtEmail.focus();
			return false;
		}
		return returnval;
	}
	return false;
}


//function used to forward pension case
var directFlag;
function forwardPensionCase(flag)
{	
	//alert("hiiii");
	if(validPensionInward() == true)
	{
		//Added by shraddha for deputation module
		var flagPen = document.getElementById("flagPen").value;
		//alert("flag******"+flag);
		var pensionFlag = document.getElementById("pensionFlag").value;
		//alert(pensionFlag);
		
		xmlHttp=GetXmlHttpObject();
		
		if (xmlHttp==null)
		{
		     return;
		}
		showProgressbar();
		var url = runForm(0);	
		var uri;
		
		var elementMinor = document.getElementsByName("chkMinor");
		for(var i=0;i<elementMinor.length;i++)
		{
			if(elementMinor[i].checked)
			{
				url = url+'&chkMinorVar=Y';
			}
			else
			{
				url = url+'&chkMinorVar=';
			}
		}
		var elementMarried = document.getElementsByName("chkMarried");
		for(var j=0;j<elementMarried.length;j++)
		{
			if(elementMarried[j].checked)
			{
				url = url+'&chkMarriedVar=Y';
			}
			else
			{
				url = url+'&chkMarriedVar=';
			}
		}
		var elementFamlyPen = document.getElementsByName("chkFamlyPen");
		for(var k=0;k<elementFamlyPen.length;k++)
		{
			if(elementFamlyPen[k].checked)
			{
				url = url+'&chkFamlyPenVar=Y';
			}
			else
			{
				url = url+'&chkFamlyPenVar=';
			}
		}
		
		//alert(chkFamlyPenVar);
		
		
		if(document.getElementById("inwardPensionId").value=="")
	    {
			var pensionCaseType=document.getElementById("pensionCaseType").value;
    	
    	//alert("first if inwardPensionId:-"+pensionCaseType);
    	if(pensionCaseType=='Provisional')
    	{
    		var order_no=document.getElementById("orderno").value;
    		
    		var order_date=document.getElementById("orderDate").value;
    		var attachmentProv=document.getElementById("cntProv").value;
    		//alert("attachmentProv"+attachmentProv);
    		if(attachmentProv=="0"){
    			alert("Please attach the Provisional Certificate");
    			hideProgressbar();
    			return ;
    		}
    		
    		//alert(order_no+" "+order_date);
    		if(order_no=='')
    		{
    			alert("Please enter order No.");
    			hideProgressbar();
    			return ;
    		}
    		else if(order_date=='')
    		{
    			alert("Please enter order Date.");
    			hideProgressbar();
    			return ;
    		}
    		
    	
    		
    	//	var lLngAttachId=document.getElementById("lLngAttachId").value;
    		//alert("order_no :"+order_no+"  order_date: "+order_date);
    		var judCasePendingReason=document.getElementById("judCasePendingReason").value;
    		var deptInqPendingReason=document.getElementById("deptInqPendingReason").value;
    		uri = 'ifms.htm?actionFlag=insertPensionCase&DraftFlag='+flag+"&pensionCaseType="+document.getElementById("pensionCaseType").value+
   		 "&order_no="+order_no+"&order_date="+order_date+"&judCasePendingReason="+judCasePendingReason+"&deptInqPendingReason="+deptInqPendingReason+"&flagPen="+flagPen+"&pensionFlag="+pensionFlag;
   		 directFlag = 1;
    	}
    	else
    	{
    		uri = 'ifms.htm?actionFlag=insertPensionCase&DraftFlag='+flag+"&pensionCaseType="+pensionCaseType+"&flagPen="+flagPen+"&pensionFlag="+pensionFlag;
		   directFlag = 1;
	    }
	    }
	    else
	    {
	    	
	    	var pensionCaseType1=document.getElementById("pensionCaseType").value;
	    	
	    	//alert("first if"+pensionCaseType1);
	    	if(pensionCaseType1=='Provisional')
	    	{
	    		var order_no1=document.getElementById("orderno").value;
	    		
	    		var order_date1=document.getElementById("orderDate").value;
	    		//alert(order_no+" "+order_date);
	    		if(order_no1=='')
	    		{
	    			alert("Please enter order No.");
	    			hideProgressbar();
	    			return ;
	    		}
	    		else if(order_date1=='')
	    		{
	    			alert("Please enter order Date.");
	    			hideProgressbar();
	    			return ;
	    		}
	    	//	var lLngAttachId=document.getElementById("lLngAttachId").value;
	    		//alert("order_no :"+order_no+"  order_date: "+order_date);
	    		url2= "&order_no="+order_no1+"&order_date="+order_date1+"&flagPen="+flagPen+"&pensionFlag="+pensionFlag;
	    	}
	    	else{
	    		url2="";
	    	}
	    	
	    	//alert("cmbCaseType::"+document.getElementById("cmbCaseType").value);
	    	uri = 'ifms.htm?actionFlag=insertPensionCase&DraftFlag='+flag+'&InwardId='+document.getElementById("inwardPensionId").value+'&status='+document.getElementById("hdnStatusId").value+url2+"&pensionCaseType="+pensionCaseType1+"&flagPen="+flagPen+"&pensionFlag="+pensionFlag;
		   directFlag = 2;
	    }
		/* var myAjax = new Ajax.Request(uri,
						{
					        method: 'post',
					        asynchronous: false,
					        parameters:url,
					        onSuccess: function(myAjax) {forwardCaseStateChanged(myAjax,directFlag);},
					        onFailure: function(){ alert('Something went wrong...');} 
						});*/
		
	//alert(url);
		
		 xmlHttp.onreadystatechange=forwardCaseStateChanged;
		 xmlHttp.open("POST",uri,false);
		 xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		 xmlHttp.send(url);
	}
}

function forwardCaseStateChanged() 
{ 
	if (xmlHttp.readyState==complete_state)
	{
		var XMLDoc=xmlHttp.responseXML.documentElement;
	    var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	     
	    alert("Pension Case "+' '+""+'-'+XmlHiddenValues[0].childNodes[0].firstChild.nodeValue+" has been Forward Successfully.");	
		if(directFlag == 1){
			hideProgressbar();
			window.location.href = 'ifms.htm?viewName=PensionEmpSearch&elementId=376394';
		}else{
			hideProgressbar();
	    	if(XmlHiddenValues[0].childNodes[3].firstChild.nodeValue=='REVISION'){
	    		  self.location.href="ifms.htm?actionFlag=loadSearchPensionCase&DraftFlag=D&elementId=376395";    		  
	    	}else{
	    		self.close();
	        	window.opener.location.reload();
	    	}		    
		}
	}
}
//function used to reject pension case
function rejectPensionCase()
{
	var comments = document.getElementById("txtComments").value;
	//var rejectTo = document.getElementById("cmbRejectTo").value;
	var pensionCaseId=document.getElementById("inwardPensionId").value;
	/*if(rejectTo==-1)
	{
		alert("Please Select User To Reject");
		document.getElementById("cmbRejectTo").focus();
		return false;
	}
	else
	{*/
		showProgressbar();
		var url = "ifms.htm?actionFlag=forwardPensionCase&pensionCaseId="+pensionCaseId+"&comments="+comments+"&status=Reject"; 
	    var myAjax = new Ajax.Request(url,
				{
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function(myAjax) {rejectCaseStateChanged(myAjax);},
			        onFailure: function(){ alert('Something went wrong...');} 
				});
		   
		
	//}
	
}

function rejectCaseStateChanged(myAjax) 
{ 
	alert("Pension case has been sent back to DDO Asst.");
	hideProgressbar();
	window.opener.location.reload();
	window.close();
	
}
function approvePensionCase()
{
	//SaveData('F');
	var pensionCaseId=document.getElementById("inwardPensionId").value;
	var comments = document.getElementById("txtComments").value;
	var url = "ifms.htm?actionFlag=approvePensionCase&pensionCaseId="+pensionCaseId+"&comments="+comments;
	showProgressbar();
	 var myAjax = new Ajax.Request(url,
				{
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function(myAjax) {approveCaseStateChanged(myAjax);},
			        onFailure: function(){ alert('Something went wrong...');} 
				});
}

function approveCaseStateChanged(myAjax) 
{ 
	alert("Pension Case has been Approved Successfully");
	hideProgressbar();
	window.opener.location.reload();
	window.close();
	//opener.location.href='ifms.htm?actionFlag=loadSearchPensionCase&DraftFlag=F&ApproveFlag=Y';
}

function showDtPic()
{
	if(document.getElementById("cmbSearchBy").value=="r.Inward_Date" || document.getElementById("cmbSearchBy").value=="r.Retirement_Date")
	{
		document.getElementById("SearchVal").style.display="none";
		document.getElementById("dtpicker").style.display="";
		document.getElementById("InwardType").style.display="none";
		document.getElementById("PensionType").style.display="none";
		document.getElementById("BankName").style.display="none";		
		document.getElementById("BranchName").style.display="none";
		document.getElementById("txtSearchVal").value = "";
		document.getElementById("cmbCaseType").value = "-1";
		document.getElementById("cmbClassOfPnsn").value = "-1";
		document.getElementById("cmbBankName").value = "-1";
		document.getElementById("BranchName").value = "-1";
	}
	else if(document.getElementById("cmbSearchBy").value=="r.Inward_No")
	{
		document.getElementById("SearchVal").style.display="";
		document.getElementById("dtpicker").style.display="none";
		document.getElementById("InwardType").style.display="none";
		document.getElementById("PensionType").style.display="none";
		document.getElementById("BankName").style.display="none";		
		document.getElementById("BranchName").style.display="none";
		document.getElementById("txtSearchVal").value = "";
		document.getElementById("cmbCaseType").value = "-1";
		document.getElementById("cmbClassOfPnsn").value = "-1";
		document.getElementById("cmbBankName").value = "-1";
		document.getElementById("BranchName").value = "-1";
	}
	else if(document.getElementById("cmbSearchBy").value=="r.Inward_Type")
	{
		document.getElementById("SearchVal").style.display="none";
		document.getElementById("dtpicker").style.display="none";
		document.getElementById("InwardType").style.display="";
		document.getElementById("PensionType").style.display="none";
		document.getElementById("BankName").style.display="none";		
		document.getElementById("BranchName").style.display="none";
		document.getElementById("txtSearchVal").value = "";
		document.getElementById("cmbCaseType").value = "-1";
		document.getElementById("cmbClassOfPnsn").value = "-1";
		document.getElementById("cmbBankName").value = "-1";
		document.getElementById("BranchName").value = "-1";
	}
	else if(document.getElementById("cmbSearchBy").value=="r.Pension_Type")
	{
		document.getElementById("SearchVal").style.display="none";
		document.getElementById("dtpicker").style.display="none";
		document.getElementById("InwardType").style.display="none";
		document.getElementById("PensionType").style.display="";
		document.getElementById("BankName").style.display="none";		
		document.getElementById("BranchName").style.display="none";	
		document.getElementById("txtSearchVal").value = "";
		document.getElementById("cmbCaseType").value = "-1";
		document.getElementById("cmbClassOfPnsn").value = "-1";
		document.getElementById("cmbBankName").value = "-1";
		document.getElementById("BranchName").value = "-1";
	}
	else if(document.getElementById("cmbSearchBy").value=="r.Bank_Branch_Name")
	{
		document.getElementById("SearchVal").style.display="none";
		document.getElementById("dtpicker").style.display="none";
		document.getElementById("InwardType").style.display="none";
		document.getElementById("PensionType").style.display="none";
		document.getElementById("BankName").style.display="";		
		document.getElementById("BranchName").style.display="";
		document.getElementById("txtSearchVal").value = "";
		document.getElementById("cmbCaseType").value = "-1";
		document.getElementById("cmbClassOfPnsn").value = "-1";
		document.getElementById("cmbBankName").value = "-1";
		document.getElementById("BranchName").value = "-1";
	}
	else if(document.getElementById("cmbSearchBy").value=="r.PPO_No")
	{
		document.getElementById("SearchVal").style.display="";
		document.getElementById("dtpicker").style.display="none";
		document.getElementById("InwardType").style.display="none";
		document.getElementById("PensionType").style.display="none";
		document.getElementById("BankName").style.display="none";		
		document.getElementById("BranchName").style.display="none";
		document.getElementById("txtSearchVal").value = "";
		document.getElementById("cmbCaseType").value = "-1";
		document.getElementById("cmbClassOfPnsn").value = "-1";
		document.getElementById("cmbBankName").value = "-1";
		document.getElementById("BranchName").value = "-1";
	}
	
}

function validCommutePer()
{
	var cmbPayComsn = document.getElementById("cmbPayComsn").value;
//	alert("cmbPayComsn"+cmbPayComsn);
	var pensionerType = document.getElementById("cmbPnsnCatg").value;
	if(cmbPayComsn == '5THPAYCOMSN'){
		document.getElementById("radioEfpYes").checked="true";
		
		if(Number(document.getElementById("txtDoWantCommute").value)>40)
		{
			alert('Commutation percentage cannot be more than 40.Please re-enter it.');
			document.getElementById("txtDoWantCommute").value="";
			return;
		}
	}
	else if(cmbPayComsn == 'PadmanabhanComm'){
		if(Number(document.getElementById("txtDoWantCommute").value)>50)
		{
			alert('Commutation percentage cannot be more than 50.Please re-enter it.');
			document.getElementById("txtDoWantCommute").value="";
			return;
		}	
	}
	
	else{	
		document.getElementById("radioEfpYes").checked="false";
		if(pensionerType == 'High Court'){
			if(Number(document.getElementById("txtDoWantCommute").value)>50)
			{
				alert('Commutation percentage cannot be more than 50.Please re-enter it.');
				document.getElementById("txtDoWantCommute").value="";
				return;
			}
		}else{
			if(Number(document.getElementById("txtDoWantCommute").value)>40)
			{
				alert('Commutation percentage cannot be more than 40.Please re-enter it.');
				document.getElementById("txtDoWantCommute").value="";
				return;
			}
		}
	}
}
function getNomBranchNameFromBankCode(object)
{
	var elementId=object.id;
	var rowNum=elementId.substring(10);
	var bankCode=document.getElementById(elementId).value;
	
	
	uri="ifms.htm?actionFlag=getBranchesOfBank&bankCode="+bankCode;
	 var myAjax = new Ajax.Request(uri,
				{
			        method: 'post',
			        asynchronous: false,
			        parameters:uri,
			        onSuccess: function(myAjax) {getDataStateChangedForBranchName(myAjax,rowNum);},
			        onFailure: function(){ alert('Something went wrong...');} 
				});
}
function getDataStateChangedForBranchName(myAjax,rowNum){
	
	XMLDoc =  myAjax.responseXML.documentElement;
	var resultElementId="cmbNomBankBranch"+rowNum;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('BranchCode');
	var XmlHiddenValues1 = XMLDoc.getElementsByTagName('BranchName');
   	if(XmlHiddenValues.length >0)
	{
   			var theOption = new Option;
   			theOption.value = "-1";
   			theOption.text ="--Select--";
   			document.getElementById(resultElementId).options[0]=theOption;
	 		document.getElementById(resultElementId).options[0].selected="selected";
	 		for( var i=0; i<XmlHiddenValues.length ;i++) {
	 		theOption = new Option;	
			theOption.value = XmlHiddenValues[i].childNodes[0].nodeValue;
			theOption.text = XmlHiddenValues1[i].childNodes[0].nodeValue;
			document.getElementById(resultElementId).options[i+1] = theOption;
	}
}
else
{
	 		alert("This bank does not belong to your location.");
	 		document.getElementById(resultElementId).options.length = 0;
	 		theOption = new Option;
			theOption.value = "-1";
			theOption.text ="--Select--";
			document.getElementById(resultElementId).options[0]=theOption;
	 		document.getElementById(resultElementId).options[0].selected="selected";
	 		document.getElementById(resultElementId).options[0].disabled=true;
	 		document.getElementById("txtNomBankBranchCode"+rowNum).value="";
}
	
}
function compareDOBAndDoj(){

		var fieldName1 = document.getElementById("txtDateOfBirth");
		var fieldName2 = document.getElementById("txtDateOfStartingService");
		var Date1 = fieldName1.value;
		var Date2 = fieldName2.value;
		var flag = '<';
		var alrtStr = 'Date of Starting Service should be greater than Date of Birth.';
		
		if(Date1 != '' && Date2 != ''){
		    var la_Date1 = new Array();
		    la_Date1 = Date1.split("/");
		    var day1=parseFloat(la_Date1[0]);
		    var month1=parseFloat(la_Date1[1]);
		    var year1=parseFloat(la_Date1[2]);
	
		    var la_Date2 = new Array();
		    la_Date2 = Date2.split("/");
		    var day2=parseFloat(la_Date2[0]);
		    var month2=parseFloat(la_Date2[1]);
		    var year2=parseFloat(la_Date2[2]);
	
		    if (year2 == year1 && month2 == month1 && day2 == day1)
		    {
		    	if(flag == '=')
		    	{
		    		alert(alrtStr);
		    		fieldName1.value = '';
		    	    fieldName1.focus();
		    	}
		    	else
		    	{
			        return true;
			    }
		    }
		    else if( year2 > year1 )
		    {
		        return true;
		    }
		    else if( year2 < year1 && flag != '=')
		    {
		        alert(alrtStr);
		        if(flag == '<')
		        {
		        	fieldName1.value = '';
		    	    fieldName1.focus();
		    	}
		    	else if(flag == '>')
		    	{
		    		fieldName2.value = '';
		    	    fieldName2.focus();
		    	}
		    }
		    else if (flag != '=')
		    {
		        if( month2 > month1 )
		        {
		            return true;
		        }
		        else if( month2 < month1 )
		        {     
		             alert(alrtStr);
		             if(flag == '<')
			        {
		            	fieldName1.value = '';
		    		    fieldName1.focus();
			    	}
		    		else if(flag == '>')
		    		{
		    			fieldName2.value = '';
			    	    fieldName2.focus();
		    		}
		        }
		        else
		        {
		            if( day2 > day1 )
		            {
		                return true;
		            }
		            else if( day2 < day1 )
		            {
		                 alert(alrtStr);
		                 if(flag == '<')
					     {
		                	fieldName1.value = '';
		    			    fieldName1.focus();
					   	}
				    	else if(flag == '>')
		    			{
				    		fieldName2.value = '';
		    			    fieldName2.focus();
				    	}
		            }
		        }
		    }
		}
	    return true ;
}


//added by shraddha 
function validateDOC(){
	if(document.getElementById("txtDateOfStartingService").value != "" && document.getElementById("txtDateOfConfirmation").value != "" )
	{

		var brkDOJ = document.getElementById("txtDateOfStartingService").value; // Date of starting service
		var brkDOC = document.getElementById("txtDateOfConfirmation").value; // Date of Confirmation
		
		var brkDOJDateArr = brkDOJ.split("/");
		var brkDOJDateDay = brkDOJDateArr[0];
		var brkDOJDateMonth = brkDOJDateArr[1];
		var brkDOJDateYear = brkDOJDateArr[2];

		
		var brkDOCDateArr = brkDOC.split("/");
		var brkDOCDateDay = brkDOCDateArr[0];
		var brkDOCDateMonth = brkDOCDateArr[1];
		var brkDOCDateYear = brkDOCDateArr[2];

		if(Number(brkDOJDateYear) > Number(brkDOCDateYear))
		{
			alert ("Date of Confirmation should be greater than Date of Joining");
			document.getElementById("txtDateOfConfirmation").value ="";
			document.getElementById("txtDateOfConfirmation").focus();
			return false;
		}
		else
		{
			if(Number(brkDOJDateYear) == Number(brkDOCDateYear)) 
			{
				if(Number(brkDOJDateMonth) > Number(brkDOCDateMonth))
				{
					alert ("Date of Confirmation should be greater than Date of Joining");
					document.getElementById("txtDateOfConfirmation").value ="";
					document.getElementById("txtDateOfConfirmation").focus();
					return false;
				}
				
				if(Number(brkDOJDateMonth) == Number(brkDOCDateMonth))
				{
					if(Number(brkDOJDateDay) > Number(brkDOCDateDay))
					{
						alert ("Date of Confirmation should be greater than Date of Joining");
						document.getElementById("txtDateOfConfirmation").value ="";
						document.getElementById("txtDateOfConfirmation").focus();
						return false;
					}
					
				}
}
		}
		
		
	}else 
		return true;
	if(document.getElementById("txtDateOfRetiremt").value != "" && document.getElementById("txtDateOfConfirmation").value != "" )
	{
		
		var brkDOR = document.getElementById("txtDateOfRetiremt").value; // Date of starting service
		var brkDOC1 = document.getElementById("txtDateOfConfirmation").value; // Date of Confirmation
		
		var brkDORDateArr = brkDOR.split("/");
		var brkDORDateDay = brkDORDateArr[0];
		var brkDORDateMonth = brkDORDateArr[1];
		var brkDORDateYear = brkDORDateArr[2];

		var brkDOC1DateArr = brkDOC1.split("/");
		var brkDOC1DateDay = brkDOC1DateArr[0];
		var brkDOC1DateMonth = brkDOC1DateArr[1];
		var brkDOC1DateYear = brkDOC1DateArr[2];
		
		
		if(Number(brkDOC1DateYear) > Number(brkDORDateYear))
		{
			
		
			alert ("Date of Confirmation should be less than Date of Retirement");
			document.getElementById("txtDateOfConfirmation").value ="";
			document.getElementById("txtDateOfConfirmation").focus();
			return false;
		}
		else
		{
			if(Number(brkDOC1DateYear) == Number(brkDORDateYear)) 
			{
				
				if(Number(brkDOC1DateMonth) > Number(brkDORDateMonth))
				{
					
					
					alert ("Date of Confirmation should be less than Date of Retirement");
					document.getElementById("txtDateOfConfirmation").value ="";
					document.getElementById("txtDateOfConfirmation").focus();
					return false;
				}
				
				 if(Number(brkDORDateMonth) == Number(brkDOC1DateMonth))
				{
					 
					if(Number(brkDOC1DateDay) > Number(brkDORDateDay))
					{
						alert ("Date of Confirmation should be less than Date of Retirement");
						document.getElementById("txtDateOfConfirmation").value ="";
						document.getElementById("txtDateOfConfirmation").focus();
						return false;
					}
					
				}
}
		}
		
		
		
	}
	else 
		return true;
	}

function calActualServiceDays()
{
	if(document.getElementById("txtDateOfRetiremt").value != "" && document.getElementById("txtDateOfStartingService").value != "" )
	{
		var brkFrom = document.getElementById("txtDateOfStartingService").value; // Date of starting service
		var brkTo = document.getElementById("txtDateOfRetiremt").value; // Date of Retirement
		var dayDiff = 0;
		var brkFromDateArr = brkFrom.split("/");
		var brkFromDateDay = brkFromDateArr[0];
		//alert('brkFromDateDay '+brkFromDateDay);
		var brkFromDateMonth = brkFromDateArr[1];
		var brkFromDateYear = brkFromDateArr[2];
		
		var brkToDateArr = brkTo.split("/");
		var brkToDate = new Date(brkToDateArr[1] +"/"+brkToDateArr[0]+"/"+ brkToDateArr[2]);
		brkToDate.setDate(brkToDate.getDate()+1);
		
		var brkToDateDay = brkToDate.getDate();
		//alert('brkToDateDay '+brkToDateDay);
		var brkToDateMonth = brkToDate.getMonth()+1;
		var brkToDateYear = brkToDate.getFullYear();
		//var noOfDaysMonthWise;
		
	/*	if(brkToDateMonth==4 || brkToDateMonth==6 || brkToDateMonth==9 || brkToDateMonth==4)
		{
			noOfDaysMonthWise = 30;
		}
		else
		{
			noOfDaysMonthWise = 31;
		}*/
		
		//alert('noOfDaysMonthWise'+noOfDaysMonthWise);
		if(Number(brkFromDateYear) > Number(brkToDateYear))
		{
			alert('Date of Retirement should be greater than Date of Starting Service.');
			document.getElementById("txtDateOfRetiremt").value ="";
			document.getElementById("txtDateOfRetiremt").focus();
		}
		else
		{
			if(Number(brkFromDateYear) == Number(brkToDateYear)) 
			{
				if(Number(brkFromDateMonth) > Number(brkToDateMonth))
				{
					alert('Date of Retirement should be greater than Date of Starting Service.');
					document.getElementById("txtDateOfRetiremt").value ="";
					document.getElementById("txtDateOfRetiremt").focus();
				}
				else
				{
					if((Number(brkFromDateMonth) == Number(brkToDateMonth)))
					{
						if(Number(brkFromDateDay) > Number(brkToDateDay))
						{
							alert('Date of Retirement should be greater than Date of Starting Service.');
							document.getElementById("txtDateOfRetiremt").value ="";
							document.getElementById("txtDateOfRetiremt").focus();
						}
						else
						{
							if(Number(brkFromDateDay) ==  Number(brkToDateDay))
							{
								dayDiff = 0;
								monthDiff = 0;
								yearDiff = 0;
							}
							else // brkFromDateDay is less than brkToDateDay
							{
								dayDiff = Number(brkToDateDay) - Number(brkFromDateDay);
								monthDiff = 0;
								yearDiff = 0;
							}
						}
					}
					else // brkFromDateMonth is less than brkToDateMonth
					{
						monthDiff = Number(brkToDateMonth) - Number(brkFromDateMonth);
						yearDiff = 0;
						if(Number(brkToDateDay) == Number(brkFromDateDay))
						{
							dayDiff = 30 * monthDiff;//commented by Kinjal
							//dayDiff = noOfDaysMonthWise * monthDiff;
						}
						else
						{
							dayDiff = (30 - Number(brkFromDateDay) + (30 * (monthDiff - 1))  + Number(brkToDateDay));//commented by Kinjal
							//dayDiff = (noOfDaysMonthWise - Number(brkFromDateDay) + (noOfDaysMonthWise * (monthDiff - 1))  + Number(brkToDateDay));
						}
					}
				}
			}
			else // brkFromDateYear is less than brkToDateYear
			{
				yearDiff = Number(brkToDateYear) - Number(brkFromDateYear);
				//alert('brkFromDateYear is less than brkToDateYear---yearDiff--'+yearDiff);
				if((Number(brkFromDateMonth) == Number(brkToDateMonth)))
				{
					monthDiff = 12 * yearDiff;
					if(Number(brkFromDateDay) ==  Number(brkToDateDay))
					{
						dayDiff =  (yearDiff * 360);
					}
					else
					{
						if(Number(brkToDateDay) > Number(brkFromDateDay))
						{
							dayDiff = (Number(brkToDateDay)-Number(brkFromDateDay)) + (360 * (yearDiff));
						}
						else//brkToDateDay is less than brkFromDateDay
						{
							dayDiff = (360 * (yearDiff)) - (Number(brkFromDateDay)-Number(brkToDateDay));
						}
					}
				}
				else 
				{
					if(Number(brkToDateMonth) > Number(brkFromDateMonth))
					{
						
						monthDiff = (12 * yearDiff) +  (Number(brkToDateMonth) - Number(brkFromDateMonth));
						//alert('brkFromDateMonth is less than brkToDateMonth--monthDiff'+monthDiff);
						if(Number(brkFromDateDay) ==  Number(brkToDateDay))
						{
							dayDiff =  (yearDiff * 360) + (30 * (Number(brkToDateMonth) - Number(brkFromDateMonth)));//commented by Kinjal
							//dayDiff =  (yearDiff * 360) + (noOfDaysMonthWise * (Number(brkToDateMonth) - Number(brkFromDateMonth)));
						}
						else
						{
							if(Number(brkToDateDay) > Number(brkFromDateDay))
							{
								/*a=(yearDiff * 360);
								b=(Number(brkToDateMonth) - Number(brkFromDateMonth));
								c=(Number(brkToDateDay) - Number(brkFromDateDay));
								alert('a'+a);
								alert('b'+b);
								alert('c'+c);*/
								dayDiff = (yearDiff * 360) + (30 * (Number(brkToDateMonth) - Number(brkFromDateMonth))) +  (Number(brkToDateDay) - Number(brkFromDateDay)) ;//commented by Kinjal
								//dayDiff = (yearDiff * 360) + (noOfDaysMonthWise * (Number(brkToDateMonth) - Number(brkFromDateMonth))) +  (Number(brkToDateDay) - Number(brkFromDateDay)) ;
								//alert('brkFromDateDay is less than brkToDateDay--dayDiff'+dayDiff);
							}
							else//brkToDateDay is less than brkFromDateDay
							{
								dayDiff = (yearDiff * 360) + (30 * (Number(brkToDateMonth) - Number(brkFromDateMonth))) - (Number(brkFromDateDay) - Number(brkToDateDay)) ;//commented by Kinjal
								//dayDiff = (yearDiff * 360) + (noOfDaysMonthWise * (Number(brkToDateMonth) - Number(brkFromDateMonth))) - (Number(brkFromDateDay) - Number(brkToDateDay)) ;
							}
						}
						
					}
					else //brkToDateMonth is less than brkFromDateMonth
					{
						monthDiff = (12 * yearDiff) -  (Number(brkFromDateMonth) - Number(brkToDateMonth));
						if(Number(brkFromDateDay) ==  Number(brkToDateDay))
						{
							dayDiff =  (yearDiff * 360) - (30 * (Number(brkFromDateMonth) - Number(brkToDateMonth)));//commented by Kinjal
							//dayDiff =  (yearDiff * 360) - (noOfDaysMonthWise * (Number(brkFromDateMonth) - Number(brkToDateMonth)));
						}
						else
						{	
							if(Number(brkToDateDay) > Number(brkFromDateDay))
							{
								if(brkToDateMonth==2 && (Math.floor(Number(brkToDateYear%4 == 0 )))){
									
								dayDiff = ((yearDiff * 360) + (28 * (Number(brkToDateMonth) - Number(brkFromDateMonth)))) + (Number(brkFromDateDay) - Number(brkToDateDay)) ;
								
								
								}
								else
								dayDiff = ((yearDiff * 360) + (30 * (Number(brkToDateMonth) - Number(brkFromDateMonth)))) + (Number(brkFromDateDay) - Number(brkToDateDay)) ;//commented by Kinjal
									//dayDiff = ((yearDiff * 360) + (noOfDaysMonthWise * (Number(brkToDateMonth) - Number(brkFromDateMonth)))) + (Number(brkFromDateDay) - Number(brkToDateDay))
								//dayDiff = ((yearDiff * 360) - (30 * (Number(brkFromDateMonth) - Number(brkToDateMonth)))) +  (Number(brkToDateDay) - Number(brkFromDateDay)) ;
							}
							else//brkToDateDay is less than brkFromDateDay
							{	
							dayDiff = (( yearDiff * 360) - (30 * (Number(brkFromDateMonth) - Number(brkToDateMonth)))) +  (Number(brkToDateDay) - Number(brkFromDateDay)) ;//commented by Kinjal
								//dayDiff = (( yearDiff * 360) - (noOfDaysMonthWise * (Number(brkFromDateMonth) - Number(brkToDateMonth)))) +  (Number(brkToDateDay) - Number(brkFromDateDay)) ;
								//dayDiff = ((yearDiff * 360) - (30 * (Number(brkToDateMonth) - Number(brkFromDateMonth)))) - (Number(brkFromDateDay) - Number(brkToDateDay)) ;
							}
						}
					}
				}
			}
		}
		document.getElementById("txtActualSer").value = dayDiff;
		document.getElementById("txtQualifyingServ").value = dayDiff;
		var TotalYear = Math.floor(Number(dayDiff/360));
		//alert('TotalYear '+TotalYear);
		var TotalMonth = Math.floor(Number((dayDiff%360)/30));
		//alert('TotalMonth '+TotalMonth);
		
		if(brkToDateMonth==2 && (Math.floor(Number(brkToDateYear%4 == 0 )))){
		
		var TotalDays = (dayDiff%362)%29;
		
		}
		else
		{
			 TotalDays = (dayDiff%360)%30;//commented by Kinjal
			//TotalDays = (dayDiff%360)%noOfDaysMonthWise;
		}
		//alert('TotalDays '+TotalDays);
		document.getElementById("hidTotalyear").value=TotalYear;
		document.getElementById("hidTotalMonth").value=TotalMonth;
		document.getElementById("hidTotalDays").value=TotalDays;
		
		document.getElementById("txtActualSerYear").value=TotalYear;
		document.getElementById("txtActualSerMonth").value=TotalMonth;
		document.getElementById("txtActualSerDay").value=TotalDays;
		
		
		
		if(document.getElementById("txtQualiServYear").value=="" && document.getElementById("txtQualiServMonth").value=="" && document.getElementById("txtQualiServDay").value==""){
			//alert("Inside if^^^^");
		document.getElementById("txtQualifyingServYear").value=TotalYear;
		document.getElementById("txtQualifyingServMonth").value=TotalMonth;
		document.getElementById("txtQualifyingServDay").value=TotalDays;
		}
	}
	else
	{
		if(document.getElementById("txtDateOfRetiremt").value == "" )
		{
		//	alert('Please enter date of retirement.');
		//	document.getElementById("txtDateOfRetiremt").focus();
			return;
		}
		if(document.getElementById("txtDateOfStartingService").value == "")
		{
		//	alert('Please enter date of starting service.');
		//	document.getElementById("txtDateOfStartingService").focus();
			return;
		}
	}
}
function setEmolumnetToDate(flag)
{
	
	/*if(document.getElementById('txtDateOfRetiremt').value == "")
	{
		//alert('Please enter date of retirement.');
	//	document.getElementById("txtDateOfRetiremt").focus();
		return;
	}
	else
	{
		// 31/10/2006 consider last emolument/2 directly instead of comparing avg. of last 10 emoluments.
		
		var date = new Date();
		date.setFullYear(2006, 10,31);
		date.setMonth(date.getMonth()-1);
		if(compareTwoDates(document.getElementById('txtDateOfExpiry'),date,"<")== true)
		{
			document.getElementById("hdnSixPayRetireFlag").value="Y";
		}
		else
		{
			document.getElementById("hdnSixPayRetireFlag").value="N";
		}		
		document.getElementById('txtEmolumentToDate').value=document.getElementById('txtDateOfRetiremt').value;
		if(document.getElementById("cmbClassOfPnsn").value == FAMILYPNSN)
		{
			document.getElementById('txtDateOfRetiremt').value=document.getElementById('txtDateOfExpiry').value;
		}
	}*/
	
	if(document.getElementById("cmbClassOfPnsn").value == FAMILYPNSN)
	{
		document.getElementById('txtDateOfRetiremt').value=document.getElementById('txtDateOfExpiry').value;
	}
	document.getElementById('txtEmolumentToDate').value=document.getElementById('txtDateOfRetiremt').value;
	
	
	
	
	var retirementDt = document.getElementById('txtDateOfRetiremt').value;
	
	if(retirementDt != ''){
		
	
		var dt1=retirementDt.split("/");
	
		var temp=0;
		if(dt1[0] < 12){
			var temp=dt1[0];
			dt1[0]=dt1[1];
			dt1[1]=temp;
		    retirementDt=dt1.toString();
		    var tmpDate =Date.parse(retirementDt);
			var finalDate = tmpDate.add(-9).month();
		}   
		else{
		var tmpDate =Date.parse(retirementDt);
		var finalDate = tmpDate.add(-9).month();
		}
		var finalDay = "1";
		var finalMonth = finalDate.getMonth()+1;
		
		var finalYear = finalDate.getFullYear();
		
		if(Number(finalDay) < 10)
		{
			finalDay = "0"+finalDay;
			if(Number(finalMonth) < 10)
			{
				finalMonth = "0"+finalMonth;
			}
		}
		else
		{
			if(Number(finalMonth) < 10)
			{
				finalMonth = "0"+finalMonth;
			}
		}
		
		document.getElementById('txtEmolumentFromDate').value =  finalDay + "/" + finalMonth + "/" + finalYear;
		var rowCnt = document.getElementById("hidCalcGridSize").value;
		//Setting dates automatically
		//setAvgFromDateToDate();
		
		if(flag != '1' && rowCnt < 1){//prevent add new row for Onload
			avgPayCalcTableAddRow();
		}
	}
}
function setFP1AndFp2Date(flag)
{
	
	if(flag == 1)
		setFp1Date("txtDateOfBirth","txtFP1Date","dateOfBirth");	
	else if(flag == 2)
		setFp1Date("txtDateOfExpiry","txtFP1Date","dateOfExpiry");
	setFp2Date();
}



function setFp1Date(sourceFieldId,targetFieldId,str)
{

	lStrDate = document.getElementById(sourceFieldId).value;	
	

	var lArrDate = lStrDate.split("/");
	var date = new Date(lArrDate[1] + "/" + lArrDate[0] + "/" + lArrDate[2]);
	
	
	if(lStrDate == "")
	{
		document.getElementById(targetFieldId).value = (new String(""));
		return;
	}

	if(str == "dateOfBirth")
	{
		//alert("inside DOB");
		lStrDate1=document.getElementById("txtDateOfRetiremt").value;
		//alert("date is"+date);
		//alert("lStrDate1 is"+lStrDate1);
		var lArrDate1 = lStrDate1.split("/");
		var date1 = new Date(lArrDate1[1] + "/" + lArrDate1[0] + "/" + lArrDate1[2]);
		//alert("date1 is"+date1);
		var rtrYear=date1.getFullYear()+7;
		//alert("rtrYear is"+rtrYear);
		var brthYear=date.getFullYear()+65;
		
		if(brthYear==rtrYear){
			
		if((lArrDate[1]<lArrDate1[1])||((lArrDate[1]==lArrDate1[1]) && (lArrDate[0]<lArrDate1[0]))){
		
			date.setFullYear(brthYear);
					}
		if((lArrDate[1]>lArrDate1[1])||((lArrDate[1]==lArrDate1[1]) && (lArrDate[0]>lArrDate1[0]))){
		
			date.setFullYear(rtrYear);
			lArrDate[1]=lArrDate1[1];
			lArrDate[0]=lArrDate1[0];
		}
		}
		if(brthYear<rtrYear){
			
		date.setFullYear(brthYear);
		
		}if(brthYear>rtrYear){
			
		date.setFullYear(rtrYear);
		lArrDate[1]=lArrDate1[1];
		lArrDate[0]=lArrDate1[0];
		
		
		}
		
	}
	if(str == "dateOfExpiry")
	{
		
		if(document.getElementById('radioEfpYes').checked){
		
			date.setFullYear(date.getFullYear()+10);
		}
		else{
			date.setFullYear(date.getFullYear()+7);
			
		}
	}
	
	if(lArrDate[0] == 1)
	{
		var x;
		if(lArrDate[1] == 1)
		{
			x = DaysArray2(12 ,lArrDate[2]-1);
			lArrDate[1] = 13;
			date.setFullYear(date.getFullYear()-1);
		}
		else
		{
			if(lArrDate[1]-1 == 2 )
			{
				x = daysInFebruaryFP(lArrDate[2]);
			}
			else
			{
				x = DaysArrayFP(lArrDate[1]-1);
			}
		}
		document.getElementById(targetFieldId).value =  (new String(x).length > 1 ? x : "0" + new String(x)) + "/" +(new String(lArrDate[1] -1).length > 1 ? lArrDate[1] -1 : "0"+new String(lArrDate[1] -1))+"/"+date.getFullYear();

	}
	else
	{
		document.getElementById(targetFieldId).value = (new String(lArrDate[0]-1).length > 1 ? lArrDate[0]-1 : "0" + new String(lArrDate[0]-1)) + "/" +(new String(lArrDate[1]).length > 1 ? lArrDate[1] : "0" + new String(lArrDate[1]) )+"/"+date.getFullYear();
	}
	
}


function daysInFebruaryFP (year)
{
   return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}
function DaysArrayFP(n)
{
	var daysFp;
	for (var i = 1; i <= n; i++)
	{
		if(i != 2)
		{
			daysFp = 31
		}
		if (i==4 || i==6 || i==9 || i==11)
		{
			daysFp = 30
		}
	 	}
		return daysFp;
}
function setFp2Date()
{
	var lStrDate = document.getElementById("txtFP1Date").value;
	var lArrDate = lStrDate.split("/");
	var date = new Date(lArrDate[1] + "/" + lArrDate[0] + "/" + lArrDate[2]);
	if(lStrDate == "")
	{
		document.getElementById("txtFP2Date").value = (new String(""));
		return;
	}
	date.setDate(date.getDate()+1);

	if(date.getMonth()==11)
	 {
		document.getElementById("txtFP2Date").value = (new String(date.getDate()).length > 1 ? date.getDate() : "0" + date.getDate()) + "/" +"12"+"/"+date.getFullYear();

	 }
	 else
	 {
		  document.getElementById("txtFP2Date").value = (new String(date.getDate()).length > 1 ? date.getDate() : "0" + date.getDate()) + "/" +(new String(date.getMonth()+1).length > 1 ? date.getMonth()+1 : "0" + Number(date.getMonth()+1))+"/"+date.getFullYear();

	 }
}
function DaysArray2(m,y)
{
	var days = '';
	if (m==4 || m==6 || m==9 || m==11)
	{
		days = 30;
	}
	else
	if(m == 1)
	{
		days = daysInFebruary2(y)
	}
	else
	{
		days = 31;
	}
		return days;
}

function daysInFebruary2(year)
{
   return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}
function disabledDeathDate()
{
	
	if(document.getElementById("cmbClassOfPnsn").options[document.getElementById("cmbClassOfPnsn").selectedIndex].text == 'Family Penison')
	{
		
		document.getElementById("txtDateOfExpiry").disabled=false;
		document.getElementById("imgDtOfExpiry").style.display='';		
	}
	else
	{
		document.getElementById("txtDateOfExpiry").disabled=true;
		document.getElementById("imgDtOfExpiry").style.display='none';
	}
}
function caseTypeChange()
{
	if(document.getElementById("cmbCaseType").value == CASETYPEREVISION)
	{
		document.getElementById("btnCaseSearch").style.display="inline";
		document.getElementById("btnRevised").style.display="inline";
		document.getElementById("txtInwardId").readOnly=false;
	}
	else
	{
		document.getElementById("btnCaseSearch").style.display="none";
		document.getElementById("btnRevised").style.display="none";
		document.getElementById("txtInwardId").readOnly=true;
	}
}
function onChangePensionType()
{
	//alert('document.getElementById("cmbClassOfPnsn").value is ' + document.getElementById("cmbClassOfPnsn").value);
	
	if(document.getElementById("cmbClassOfPnsn").value == 'FAMILYPNSN')
	{
		document.getElementById("txtDateOfRetiremt").value="";
		document.getElementById("txtDateOfRetiremt").readOnly=true;
		document.getElementById("imgDtOfRetierment").style.display='none';
		document.getElementById("imgMandtryDtOfRetierment").style.display='none';
		
		document.getElementById("txtDateofExpiry").readOnly=false;  
	//	document.getElementById("imgDateofExpiry").disabled=false;
		document.getElementById("imgDtOfExpiry").style.display='';
	}
	else
	{
		document.getElementById("txtDateofExpiry").value="";
		document.getElementById("txtDateofExpiry").readOnly=true;  
		document.getElementById("txtDateOfRetiremt").readOnly=false;
		document.getElementById("imgDtOfRetierment").style.display='';
		document.getElementById("imgMandtryDtOfRetierment").style.display='';
	//	document.getElementById("imgDateofExpiry").disabled=true;
		document.getElementById("imgDtOfExpiry").style.display='none';
	}
	if((document.getElementById("cmbClassOfPnsn").value == 'VOLUNTARY64')||(document.getElementById("cmbClassOfPnsn").value == 'VOLUNTARY65')){
		alert("Hiii");
		document.getElementById("txtDateOfRetiremt").readOnly=false;
	}
}
function onChangePnsnrType()
{
	if(document.getElementById("cmbPnsnCatg").value == "Others")
	{
		document.getElementById("txtOtherPnsnrType").style.display = "inline";
	}
	else
	{
		document.getElementById("txtOtherPnsnrType").style.display = "none";
	}
}
function autoTab(input,len, e) {
	 
	var keyCode =  e.keyCode; 
	var filter =  [0,8,9,16,17,18,37,38,39,40,46];
	if(input.value.length >= len && !containsElement(filter,keyCode)) {
	  input.value = input.value.slice(0, len);
	  input.form[(getIndex(input)+1) % input.form.length].focus();
	}

	function containsElement(arr, ele) {
	  var found = false, index = 0;
	  while(!found && index < arr.length)
	  if(arr[index] == ele)
	  found = true;
	  else
	  index++;
	  return found;
	}

	function getIndex(input) {
	  var index = -1, i = 0, found = false;
	  while (i < input.form.length && index == -1)
	  if (input.form[i] == input)index = i;
	  else i++;
	  return index;
	}
	return true;
}
function setRetirementDate(object)
{
	var pensionerType = document.getElementById("cmbPnsnCatg").value;
	var cmbTypeOfPnsn = document.getElementById("cmbClassOfPnsn").value;
	var cmbPayComsn = document.getElementById("cmbPayComsn").value;
	if(pensionerType != '-1')
	{
		if(cmbTypeOfPnsn != 'VOLUNTARY64' && cmbTypeOfPnsn != 'VOLUNTARY65'){
			if(pensionerType == 'Group A' || pensionerType == 'Group B' || pensionerType == 'Group C')
			{
				addNoOfYearsInDate(object,"txtDateOfRetiremt",58);
			}
			else if(pensionerType == 'Group D' || pensionerType == 'IAS' || pensionerType == 'IFS' || pensionerType == 'IPS' || pensionerType == 'High Court' || pensionerType == 'Medical Officer')
			{
				addNoOfYearsInDate(object,"txtDateOfRetiremt",60);
			}			
			else if(pensionerType == 'Teachers')
			{
				if(cmbPayComsn == '5THPAYCOMSN')
					addNoOfYearsInDate(object,"txtDateOfRetiremt",62);
				else
					addNoOfYearsInDate(object,"txtDateOfRetiremt",60);
			}
		
			if(cmbTypeOfPnsn != 'FAMILYPNSN'){
				getNextDate();
			}
		}
	}
}
function addNoOfYearsInDate(object,targetField,noOfYears)
{
	lStrDate = object.value;
	
	var lArrDate = lStrDate.split("/");
	var date = new Date(lArrDate[1] + "/" + lArrDate[0] + "/" + lArrDate[2]);
	if(lStrDate == "")
	{
		document.getElementById(targetField).value = (new String(""));
		return;
	}
	date.setFullYear(date.getFullYear()+ Number(noOfYears));
		
	if(lArrDate[0] == 1)
	{
		var x;
		if(lArrDate[1] == 1)
		{
			x = DaysArray2(12 ,lArrDate[2]-1);
			lArrDate[1] = 13;
			date.setFullYear(date.getFullYear()-1);
		}
		else
		{
			if(lArrDate[1]-1 == 2 )
			{
				x = daysInFebruaryFP(date.getFullYear());
			}
			else
			{
				x = DaysArrayFP(date.getMonth());
			}
		}
		
		document.getElementById(targetField).value =  (new String(x).length > 1 ? x : "0" + new String(x)) + "/" +(new String(lArrDate[1] -1).length > 1 ? lArrDate[1] -1 : "0"+new String(lArrDate[1] -1))+"/"+date.getFullYear();

	}
	else
	{	
		var maxDaysInMonth = new Date(date.getFullYear(),lArrDate[1], 0).getDate();
		document.getElementById(targetField).value = (new String(maxDaysInMonth).length > 1 ? maxDaysInMonth: "0" + maxDaysInMonth) + "/" +(new String(lArrDate[1]).length > 1 ? lArrDate[1] : "0" + new String(lArrDate[1]) )+"/"+date.getFullYear();
	}
}
function getNextDate()
{
	var ClassOfPnsn = document.getElementById("cmbClassOfPnsn").value;
	var lStrDate = document.getElementById("txtDateOfRetiremt").value;
	var lArrDate = lStrDate.split("/");
	var date = new Date(lArrDate[1] + "/" + lArrDate[0] + "/" + lArrDate[2]);

	
	//alert(ClassOfPnsn);
	
	if(lStrDate == "")
	{
		document.getElementById("hdnCommencementDate").value = (new String(""));
		document.getElementById("txtDateOfCommencement").value = (new String(""));
		return;
	}
	//added by shraddha for voluntary case
	
	if(ClassOfPnsn == 'VOLUNTARY64' || ClassOfPnsn == 'VOLUNTARY65' || ClassOfPnsn=='RETIRING105' || ClassOfPnsn=='RETIRING104'){
	//alert("Inside if");
	
		date.setDate(date.getDate());
		//alert(date);
	}
	else{
		//alert("Inside Else");
	date.setDate(date.getDate()+1);
	}
	if(date.getMonth()==11)
	 {
		document.getElementById("hdnCommencementDate").value = (new String(date.getDate()).length > 1 ? date.getDate() : "0" + date.getDate()) + "/" +"12"+"/"+date.getFullYear();
		document.getElementById("txtDateOfCommencement").value = (new String(date.getDate()).length > 1 ? date.getDate() : "0" + date.getDate()) + "/" +"12"+"/"+date.getFullYear();
	 }
	 else
	 {
		  document.getElementById("hdnCommencementDate").value = (new String(date.getDate()).length > 1 ? date.getDate() : "0" + date.getDate()) + "/" +(new String(date.getMonth()+1).length > 1 ? date.getMonth()+1 : "0" + Number(date.getMonth()+1))+"/"+date.getFullYear();
		  document.getElementById("txtDateOfCommencement").value = (new String(date.getDate()).length > 1 ? date.getDate() : "0" + date.getDate()) + "/" +(new String(date.getMonth()+1).length > 1 ? date.getMonth()+1 : "0" + Number(date.getMonth()+1))+"/"+date.getFullYear();
	 }
}

function validateCommencementDate()
{
	var ClassOfPnsn = document.getElementById("cmbClassOfPnsn").value;
	var commencementDate=document.getElementById("txtDateOfCommencement").value;
	var validDate=document.getElementById("hdnCommencementDate").value;
	//alert(ClassOfPnsn);
	if(commencementDate != "" && validDate!= ""){
		if(document.getElementById("cmbClassOfPnsn").value != 'Teacher')
		{
			//alert("Inside if");
			
			//alert(ClassOfPnsn);
			
			if (commencementDate != validDate)
			{
				alert("Pension Commencement Date must be next date of Retirement Date.");
				document.getElementById("txtDateOfCommencement").value = "";
				document.getElementById("txtDateOfCommencement").focus();
				//document.getElementById("txtDateOfCommencement").select();
				//document.getElementById("txtDateOfCommencement").value="";
				//document.getElementById("txtDateOfCommencement").focus();
			}
		}
	}
}
function trsryChange(obj)
{
	if(obj.value == "Y")
	{		
		document.getElementById("cmbAgOffice").disabled=false;
		document.getElementById("cmbTreasury").disabled=false;
		document.getElementById("hdnTrsryFlag").value='Y';
	
	}
	else if(obj.value == "N")
   {	 	
		document.getElementById("cmbAgOffice").value="-1";
		document.getElementById("cmbTreasury").value="-1";
		document.getElementById("cmbAgOffice").disabled=true;
		document.getElementById("cmbTreasury").disabled=true;
		document.getElementById("hdnTrsryFlag").value='N';
   }
}
function getIfscCodeFromBrachCode()
{
	if(document.getElementById("cmbTargetBranchName").value != '-1')
	{
		var uri;
		uri = 'ifms.htm?actionFlag=getIfscCodeFromBranchCodePenProc&branchCode='+document.getElementById("cmbTargetBranchName").value;
		//getIfscCodeUsingAjax(uri);
		getIFSCCode(uri);
	}
	
}
//new AJAX Call

function getIFSCCode(uri)
{
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters: "&branchCode="+document.getElementById("cmbTargetBranchName").value,
		        onSuccess: function(myAjax) {
					getIfscCodeOnStateChangedUsingAJAX(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}
function getIfscCodeOnStateChangedUsingAJAX(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
    
    var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
    
    document.getElementById("txtIFSCCode").value=XmlHiddenValues[0].childNodes[1].childNodes[0].nodeValue;
   // document.getElementById("txtBankBranchCode").value=document.getElementById("cmbBankBranch").value;
}

function setCommencementDate(){

	var lStrDate = document.getElementById("txtDateOfRetiremt").value;	
	var lStrNoon= document.getElementById("bnOrAn").value;
	var lStrNoonNew= document.getElementById("bnOrAnNew").value;
	//alert(lStrNoonNew);
	//alert(lStrDate);
	if(lStrNoonNew=='AN'){
		//	alert("inside AN")
		if(lStrDate != ''){
			var lArrDate = lStrDate.split("/");		
			var date = new Date(lArrDate[1] + "/" + lArrDate[0] + "/" + lArrDate[2]);
			date.setDate(date.getDate()+1);

			if(date.getMonth()==11)
			{
				document.getElementById("hdnCommencementDate").value = (new String(date.getDate()).length > 1 ? date.getDate() : "0" + date.getDate()) + "/" +"12"+"/"+date.getFullYear();
				document.getElementById("txtDateOfCommencement").value = (new String(date.getDate()).length > 1 ? date.getDate() : "0" + date.getDate()) + "/" +"12"+"/"+date.getFullYear();
			}
			else
			{
				document.getElementById("hdnCommencementDate").value = (new String(date.getDate()).length > 1 ? date.getDate() : "0" + date.getDate()) + "/" +(new String(date.getMonth()+1).length > 1 ? date.getMonth()+1 : "0" + Number(date.getMonth()+1))+"/"+date.getFullYear();
				document.getElementById("txtDateOfCommencement").value = (new String(date.getDate()).length > 1 ? date.getDate() : "0" + date.getDate()) + "/" +(new String(date.getMonth()+1).length > 1 ? date.getMonth()+1 : "0" + Number(date.getMonth()+1))+"/"+date.getFullYear();
			}
		}

	}
	else
	{
		//	alert("inside bn");
		document.getElementById("hdnCommencementDate").value = lStrDate;
		document.getElementById("txtDateOfCommencement").value =	lStrDate;
	}

}
//added by ankita
function bnOrAnValue(valueAB){
	//alert(valueAB);
	document.getElementById("bnOrAn").value=valueAB;
	document.getElementById("bnOrAnNew").value=valueAB //Added by harsh
	setCommencementDate();
}
//
function bnOrAnValueJoin(valueAB){
	//alert(valueAB);
	document.getElementById("bnOrAnJoin").value=valueAB;
	setJoiningDate();
	}
function setJoiningDate(){
	
	//alert('Hi');
	var lStrDate = document.getElementById("hdnStartingServiceDate").value;	
	var lStrNoon=document.getElementById("bnOrAnJoin").value;
	//alert('lStrDate-----'+lStrDate);
	
	if(lStrNoon=='AN'){
		//alert('Hi AN');
		if(lStrDate != ''){
			var lArrDate = lStrDate.split("/");		
			var date = new Date(lArrDate[1] + "/" + lArrDate[0] + "/" + lArrDate[2]);
			date.setDate(date.getDate()+1);
	
			if(date.getMonth()==11)
			 {
				//document.getElementById("hdnCommencementDate").value = (new String(date.getDate()).length > 1 ? date.getDate() : "0" + date.getDate()) + "/" +"12"+"/"+date.getFullYear();
				document.getElementById("txtDateOfStartingService").value = (new String(date.getDate()).length > 1 ? date.getDate() : "0" + date.getDate()) + "/" +"12"+"/"+date.getFullYear();
			 }
			 else
			 {
				  //document.getElementById("hdnCommencementDate").value = (new String(date.getDate()).length > 1 ? date.getDate() : "0" + date.getDate()) + "/" +(new String(date.getMonth()+1).length > 1 ? date.getMonth()+1 : "0" + Number(date.getMonth()+1))+"/"+date.getFullYear();
				  document.getElementById("txtDateOfStartingService").value = (new String(date.getDate()).length > 1 ? date.getDate() : "0" + date.getDate()) + "/" +(new String(date.getMonth()+1).length > 1 ? date.getMonth()+1 : "0" + Number(date.getMonth()+1))+"/"+date.getFullYear();
			 }
		}
		
	}
	else
	{
		  //document.getElementById("hdnCommencementDate").value = lStrDate;
		  document.getElementById("txtDateOfStartingService").value =	lStrDate;
	}
	
}
//added by ankita

function validPensionType(){

	var ClassOfPnsn = document.getElementById("cmbClassOfPnsn").value;
	var TotalBrkYear = document.getElementById("txtQualifyingServYear").value;
	var DOJ = document.getElementById("txtDateOfStartingService").value;
	var DOR = document.getElementById("txtDateOfRetiremt").value;
	if(DOR != "" && DOJ != ""){
		if(ClassOfPnsn != "-1" && TotalBrkYear != ""){
			if(Number(TotalBrkYear)<20 && (ClassOfPnsn == 'VOLUNTARY64' || ClassOfPnsn == 'VOLUNTARY65')){
				alert('For Voluntary Pension type of pension total service should be greater than 20 years.')
				document.getElementById("cmbClassOfPnsn").value = "-1";
				document.getElementById("cmbClassOfPnsn").focus();
			}	
		}
	}
}
function compareDatesWithoutAlert(Date1,Date2,flag)
{
	var one_day = 1000*60*60*24; 

	var la_Date1 = new Array();
    la_Date1 = Date1.split("/");
    var la_Date2 = new Array();
    la_Date2 = Date2.split("/");
    
    var date1 = new Date(la_Date1[2],la_Date1[1]-1,la_Date1[0]);
    var date2 = new Date(la_Date2[2],la_Date2[1]-1,la_Date2[0]);
    
	var Diff = Math.floor((date2.getTime() - date1.getTime())/(one_day)); 
	
	if(flag == '=' &&  Number(Diff) == 0){		
		return false;		
	}

    else if( (flag == '<' &&  Number(Diff)<=0) || (flag == '>' && Number(Diff)>=0))
    {
         	return false;
    }
    else {    
    	return true;
    }
}
function enableEFPYear(){
	
	if(document.getElementById("cmbClassOfPnsn").value == FAMILYPNSN)
	{
		document.getElementById('radioEfpNo').checked = true;
		document.getElementById('radioEfpNo').disabled = false;
		document.getElementById('radioEfpYes').disabled = false;
	}else{
		document.getElementById('radioEfpYes').checked = false;
		document.getElementById('radioEfpNo').checked = false;
		document.getElementById('radioEfpNo').disabled = true;
		document.getElementById('radioEfpYes').disabled = true;
	}
}
function approvePensionCaseForAg()
{
	//SaveData('F');
	var pensionCaseId=document.getElementById("inwardPensionId").value;
	var comments = document.getElementById("txtComments").value;
	
	if(IsEmptyFun("txtCpoNo",'Please Enter CPO No.','2')==false)
	{
		return false;
	}
	if(IsEmptyFun("txtCpoDate",'Please Enter CPO Date','2')==false)
	{
		return false;
	}
	if(IsEmptyFun("txtPpoNo",'Please Enter PPO No.','2')==false)
	{
		return false;
	}
	if(IsEmptyFun("txtPpoDate",'Please Enter PPO Date','2')==false)
	{
		return false;
	}
	if(IsEmptyFun("txtGpoNo",'Please Enter GPO No.','2')==false)
	{
		return false;
	}
	if(IsEmptyFun("txtGpoDate",'Please Enter GPO Date','2')==false)
	{
		return false;
	}
	
	var uri = "ifms.htm?actionFlag=approvePensionCaseForAg&pensionCaseId="+pensionCaseId+"&comments="+comments;
	var url = runForm(0);
	showProgressbar();
	 var myAjax = new Ajax.Request(uri,
				{
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function(myAjax) {approveCaseStateChangedForAg(myAjax);},
			        onFailure: function(){ alert('Something went wrong...');} 
				});
}

function approveCaseStateChangedForAg(myAjax) 
{ 
	alert("Pension Case has been Approved Successfully");
	hideProgressbar();
	window.opener.location.reload();
	window.close();
	//opener.location.href='ifms.htm?actionFlag=loadSearchPensionCase&DraftFlag=F&ApproveFlag=Y';
}
function greaterThanCurrDateValidation(fieldname,altStr) 
{
	var	day = fieldname.value.split("/")[0];
	var month = fieldname.value.split("/")[1]; 
	var year = fieldname.value.split("/")[2];

	var dt = new Date(year, month-1, day); 
	var today = new Date();
	
	if(dt>today) 
	{
		alert(altStr);
		fieldname.value="";
		fieldname.focus();
		return false; 
	}
	
	return true;
}


function checkValue()
{
	//alert("inside check");
	var check= document.getElementById("txtDateOfCommencement").value;
//	alert("check is"+check);
	if(check==''){
		//alert("inside if");
		getNextDate();
	}
	else{
		//alert("inside else");
		setCommencementDate();
	}
}