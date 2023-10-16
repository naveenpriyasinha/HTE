<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.core.lables" var="lables" scope="request"/>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/commonUtils.js"/>"></script>

<!-- resource Bundle  -->

<fmt:setBundle basename="resources.posting.postingLabels" var="empEditListCommonLables" scope="request" />
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>
<fmt:setBundle basename="resources.eis.eisLables_en_US" var="adminCreatePostLabel" scope="request" />
<fmt:setBundle basename="resources.ess.UserPost" var="userPostMapping" scope="request" />
<!-- resource Bundle  -->
<!-- Getting ResultSet into c set variables  -->
<c:set var="resultObj" value="${result}" />
<c:set var="resValue" value="${resultObj.resultValue}" />
<c:set var="userClassTypeList" value="${resValue.classTypeList}"></c:set>
<c:set var="gpfClassTypeList" value="${resValue.classTypeList}"></c:set>
<c:set var="empTypeList" value="${resValue.empTypeList}"></c:set>
<c:set var="bankList" value="${resValue.bankList}"></c:set>
<c:set var="accTypes" value="${resValue.accountTypeList}"></c:set>
<c:set var="arDesignationVO" value="${resValue.arDesignationVO}"></c:set>
<c:set var="postTypeList" value="${resValue.postTypeList}"></c:set>
<c:set var="branchList" value="${resValue.branchList}"></c:set>
<c:set var="officeList" value="${resValue.officeList}"></c:set>
<c:set var="prevPostList" value="${resValue.prevPostList}"></c:set>
<c:set var="billNoList" value="${resValue.billNoList}"></c:set>
<c:set var="sanctOrderList" value="${resValue.sanctOrderList}"></c:set>
<c:set var="sAccType" value="${resValue.sAccType}"></c:set>
<c:set var="flag" value="add" />
<c:set var="editFlag" value="${resValue.editFlag}" />
<c:set var="msg" value="${resValue.msg}" ></c:set>
<c:set var="firstDay" value="${resValue.firstDay}" ></c:set>
<c:set var="selectedUserId" value="${resValue.userId}"></c:set>
<c:set var="lUserPostRltId" value="${resValue.lUserPostRltId}"></c:set>
<c:set var="location_Id" value="${resValue.location_Id}"></c:set>
<c:set var="empCustomVOList" value="${resValue.empCustomVOList}"></c:set>
<c:set var="empCustomVO" value="${resValue.empCustomVO}"></c:set>
<c:set var="bankBranchId" value="${resValue.bankBranchId}"></c:set>
<c:set var="bankBranchList" value="${resValue.bankBranchList}"></c:set>
<c:set var="selOfficeBranch" value="${resValue.selOfficeBranch}" />
<c:set var="AISGradeCode" value="${resValue.AISGradeCode}" />
<c:set var="locationCode" value="${resValue.locationCode}" />
<c:set var="lStrOrderEndDate" value="${resValue.lStrOrderEndDate}" />
<c:set var="lStrOrderStartDate" value="${resValue.lStrOrderStartDate}" />
<c:set var="lStrOrderName" value="${resValue.lStrOrderName}" />
<c:set var="orderMstId" value="${resValue.orderMstId}" />
<fmt:message key='admin.cash2Admin' bundle='${adminCreatePostLabel}' var="cash2LocationCode"></fmt:message>
<!-- Getting result set ends -->
<script type="text/javascript"><!--	
////////////Branch Fetching starts
	function checkUpdatedtl()
	{	
		if(${flag!='edit'})
		{	
			 //if(chkFunc())
				 getBranchNames();
		}
		else
		{ 			
			getBranchNames();
		}
		 
	}
	function getBranchNames()
	{ 
	
		 if(document.forms[0].elements['cmbBankName'].value=='Select')
		 {
			  alert('Please Enter Bank Name');
			  document.forms[0].elements['cmbBankName'].value='Select';
		 }
		 else
		 {  
				 clearBranchCombo();
				 var bankId = document.forms[0].elements['cmbBankName'].value;
				 xmlHttp=GetXmlHttpObject();
				 if (xmlHttp==null)
				 {
					  alert ("Your browser does not support AJAX!");
					  return;
				 } 
				  
				 var url; 
				 var uri='';
				 url= '&bankId=' + bankId;
				 var actionf="getBranchNames";
				 uri='./hrms.htm?actionFlag='+actionf;
				 url=uri+url;
				 xmlHttp.onreadystatechange=get_Branchs;
				 xmlHttp.open("POST",encodeURI(url),true);
				 xmlHttp.send(null);	
		  }		
	}

function clearBranchCombo()
{
	var v=document.getElementById("branchID").length;
	for(i=1;i<v;i++)
	{
			lgth = document.getElementById("branchID").options.length -1;
			document.getElementById("branchID").options[lgth] = null;
	}		
}

function get_Branchs()
{
if (xmlHttp.readyState==complete_state)
 { 						
   var cmbBranchName = document.getElementById("branchID");
   var XMLDoc=xmlHttp.responseXML.documentElement;	

   var branchEntries = XMLDoc.getElementsByTagName('branch-mapping');
   if(branchEntries.length == 0)
   {
     window.status = 'No Records Found.';
   }
   else
   {
    window.status='';
    //alert('Length ' + namesEntries.length + ' ' + namesEntries[0].childNodes[0].text);
    if(branchEntries.length != 0)
    {   
      for ( var i = 0 ; i < branchEntries.length ; i++ )
	  {                 
       val=branchEntries[i].childNodes[0].text;    
       text = branchEntries[i].childNodes[1].text; 
       var y = document.createElement('option')   
	   y.value=val;
	   y.text=text;
 	   try
 	   {	      				    					
         cmbBranchName.add(y,null);
	   }
	   catch(ex)
	   {
	     cmbBranchName.add(y); 
	   }	  
	  }
    }
  }
 }
}



/////////////// ends
/////Function to get dtls of selected post
function clearSelectedPostDtls()
{
	document.forms[0].elements['psr'].value='';
    document.forms[0].elements['postNameTxt'].value='';
    document.forms[0].elements['postShrtNametxt'].value='';
    document.forms[0].elements['postTypeCmb'].value='';
    document.forms[0].elements['branchCmb'].value='${selOfficeBranch}';
    document.forms[0].elements['officeCmb'].value='';
    document.forms[0].elements['remarksTxt'].value='';
    document.forms[0].elements['startDate'].value='${firstDay}';
    document.forms[0].elements['endDate'].value='';
    document.forms[0].elements['orderHeadpostId'].value='';
    document.forms[0].elements['order'].value='0';
    document.forms[0].elements['billNo'].value='0';
    document.forms[0].elements['psrIdToCheck'].value = '';
    document.forms[0].elements['psrPostIdToCheck'].value = '';	      
    		    	
}
function getSelectedPostDtls()
	{ 
		 /*if(document.forms[0].elements['selPost'].value=='0')
		 {
			  clearSelectedPostDtls();			  
		 }
		 else*/
		 {  	
			 	 var selectedPostId;
				 if(document.forms[0].elements['selPost'].disabled!=true)
					 selectedPostId = document.forms[0].elements['selPost'].value;
				 else
					 selectedPostId = '${empCustomVO.postId}';
				 xmlHttp=GetXmlHttpObject();
				 if (xmlHttp==null)
				 {
					  alert ("Your browser does not support AJAX!");
					  return;
				 } 
				  
				 var url; 
				 var uri='';
				 url= '&selectedPostId=' + selectedPostId;
				 var actionf="getSelectedPostDtls";
				 uri='./hrms.htm?actionFlag='+actionf;
				 url=uri+url;
				 xmlHttp.onreadystatechange=getPostDtls;
				 xmlHttp.open("POST",encodeURI(url),true);
				 xmlHttp.send(null);	
		  }		
	}

function getPostDtls()
{
	if (xmlHttp.readyState==complete_state)
	 { 
	   var XMLDoc=xmlHttp.responseXML.documentElement;	
	
	   var postDtls = XMLDoc.getElementsByTagName('postDtls');
	   if(postDtls.length == 0)
	   {
	     window.status = 'No Records Found.';	     
	   }
	   else
	   {
	    window.status='';	   
	    if(postDtls.length != 0)
	    {   
	    	var psrNo = "";
	        var postName= "";
	        var postShortName= "";
	        var postTypeId= "";
	        var branchId= "";
	        var officeId= "";
	        var remarks= "";
	        var startDate= "";
	        var endDate= "";
	        var activateFlag= "";
	        var ohpId= "";
	        var orderId= "";
	        var billNo= "0";
	        var psrPostId = "";

	        psrNo = postDtls[0].childNodes[0].text;
	        postName = postDtls[0].childNodes[1].text;
	        postShortName = postDtls[0].childNodes[2].text;
	        postTypeId = postDtls[0].childNodes[3].text;
	        branchId = postDtls[0].childNodes[4].text;
	        officeId = postDtls[0].childNodes[5].text;
	        remarks = postDtls[0].childNodes[6].text;
	        startDate = postDtls[0].childNodes[7].text;
	        endDate = postDtls[0].childNodes[8].text;
	        activateFlag = postDtls[0].childNodes[9].text; 
	        ohpId = postDtls[0].childNodes[10].text;	        
	        orderId = postDtls[0].childNodes[11].text;	        
	        billNumb = postDtls[0].childNodes[12].text;	      
	        psrPostId  = postDtls[0].childNodes[13].text;
	        
	        document.forms[0].elements['psr'].value=psrNo;
	        document.forms[0].elements['postNameTxt'].value=postName;
	        document.forms[0].elements['postShrtNametxt'].value=postShortName;
	        document.forms[0].elements['postTypeCmb'].value=postTypeId;
	        if(branchId!='')
	        {   
	        	document.forms[0].elements['branchCmb'].value=branchId;
	        }
	        else
	        {
	        	document.forms[0].elements['branchCmb'].value='${selOfficeBranch}';
	        }
	        if(document.forms[0].elements['officeCmb']!=null)
	        {    
		        document.forms[0].elements['officeCmb'].value=officeId;
		        document.forms[0].elements['remarksTxt'].value=remarks;
	        }
	        //document.forms[0].elements['startDate'].value=startDate;
	        //document.forms[0].elements['endDate'].value=endDate;
	      
	        if (billNumb=='')              
	        	document.forms[0].elements['billNo'].value=0;
	        else
	        	document.forms[0].elements['billNo'].value=billNumb;
            
	        if(document.forms[0].elements['billNo'].value!=0)
	        	getOrderNamesList();	
	        document.forms[0].elements['orderHeadpostId'].value=ohpId;	        
			if(orderId=='')
				orderId=0;
	        var orderCtrl = document.getElementById("order");
	        
	        for (i=0;i<orderCtrl.length;i++)
	        {       
	        	if (orderId==orderCtrl[i].value)
                {   
	        		orderCtrl.options[i].selected=true;
                }
	        }	        	        
	        orderCtrl.value = orderId;
	        document.forms[0].elements['psrIdToCheck'].value = psrNo;
	        document.forms[0].elements['psrPostIdToCheck'].value = psrPostId;	        		    		     
		  	
	    }
	  }
 	}
}
/////Function ends
	function retdate()
	{
		var doj=document.frmNewEmpInfo.DOB.value;
		if(doj!=null && doj!='')
		{	
			var cls = document.frmNewEmpInfo.selClass.value;
			var splitDate=doj.split("/");							
			var jday=splitDate[0];
			var jmo=splitDate[1];
			var jyr=parseInt(splitDate[2]);
			// Added by Rajan
			
				if(cls=='${AISGradeCode}')
					jyr=jyr+60;
				else
					jyr=jyr+58;
					
			jday = LastDayOfMonth(jyr,jmo);
			//Ended by Rajan
			var dor=jday+'/'+jmo+'/'+jyr;
			document.frmNewEmpInfo.DOR.value=dor;
		}		
	}
	function LastDayOfMonth(Year, Month)
		{
        	//return(new Date((new Date(Year, Month+1,1))-1)).getDate();
        	var dd = new Date(Year, Month, 0);
			return dd.getDate();
        	
		}
	function checkPostStartDate()
	{
		doj=document.frmNewEmpInfo.DOJ.value;
		startDate=document.frmNewEmpInfo.startDate.value;
		if(doj!='' && startDate!='' && compareDate(doj,startDate) < 0)
		{
			alert('Date of Joining can not be Greater than Start Date of Post');
			//document.frmNewEmpInfo.DOB.value='';
			//document.frmNewEmpInfo.DOJ.value='';
			//document.frmNewEmpInfo.DOR.value='';
			document.frmNewEmpInfo.startDate.focus();
			return false;
		}
		else
		{
			return true;
		}
	}
	function checkDOR()
	{
		doj=document.frmNewEmpInfo.DOJ.value;
		dor=document.frmNewEmpInfo.DOR.value;		
		if(doj!='' && dor!='' && compareDate(doj,dor) < 0)
		{
			alert('Date of Joining can not be Greater than Date of Retirement');
			//document.frmNewEmpInfo.DOB.value='';
			//document.frmNewEmpInfo.DOJ.value='';
			//document.frmNewEmpInfo.DOR.value='';
			document.frmNewEmpInfo.dor.focus();
			return false;
		}
		else
		{
			return true;
		}
	}
	function checkBankDate()
	{
		txtStartDate=document.frmNewEmpInfo.txtStartDate.value;
		dob=document.frmNewEmpInfo.DOB.value;		
		dor=document.frmNewEmpInfo.DOR.value;		
		if(dob!='' && txtStartDate!='' && compareDate(dob,txtStartDate) < 0)
		{
			alert('Start Date of Bank can not be less than Date of Birth');
			//document.frmNewEmpInfo.DOB.value='';
			//document.frmNewEmpInfo.DOJ.value='';
			//document.frmNewEmpInfo.DOR.value='';
			document.frmNewEmpInfo.dor.focus();
			return false;
		}
		if(dor!='' && txtStartDate!='' && compareDate(dor,txtStartDate) > 0)
		{
			alert('Start Date of Bank can not be Greater than Date of Retirement');
			//document.frmNewEmpInfo.DOB.value='';
			//document.frmNewEmpInfo.DOJ.value='';
			//document.frmNewEmpInfo.DOR.value='';
			document.frmNewEmpInfo.dor.focus();
			return false;
		}
		else
		{
			return true;
		}
	}
	function verifyPostMpgDate()
	{
		var startDate = document.frmNewEmpInfo.startDate.value;
		var prevPostEndDate;
		if(document.frmNewEmpInfo.prevPostEndDate!=null)
			prevPostEndDate = document.frmNewEmpInfo.prevPostEndDate.value;		
		if(document.frmNewEmpInfo.changePostCheck != null && document.frmNewEmpInfo.changePostCheck.checked==true && prevPostEndDate != null && prevPostEndDate!='')
		{			
			 if(compareDate(prevPostEndDate,startDate) <= 0)
			 {
				 alert('End date of mapping with current post can not be greater than or equal to Start Date of employee post Mapping');
				 document.frmNewEmpInfo.prevPostEndDate.focus();
				 return false;
			 }		
			 else
				 return true;	
		}
		else
			return true;
	}
	function enablePostSelection()
	{	
		if(document.frmNewEmpInfo.changePostCheck != null && document.frmNewEmpInfo.changePostCheck.checked==true )
		{			
			document.frmNewEmpInfo.selPost.disabled=false;
			GetPostfromDesg();			
		}
		if(document.frmNewEmpInfo.changePostCheck != null && document.frmNewEmpInfo.changePostCheck.checked==false )
		{			
			document.frmNewEmpInfo.selPost.value='0';	
			document.frmNewEmpInfo.selPost.disabled=true;
			document.frmNewEmpInfo.prevPostEndDate.value='';			
			getSelectedPostDtls();
		}
	}
	function checkMappingDate()
	{
		startDt=document.frmNewEmpInfo.startDate.value;
		endDt=document.frmNewEmpInfo.endDate.value;		
		res = compareDate(startDt,endDt);		
		if(startDt!='' && endDt!='' && res < 0)
		{
			alert('End Date of employee post Mapping can not be less than Start Date of employee post Mapping');			
			document.frmNewEmpInfo.endDate.focus();
			return false;
		}
		else
		{
			return true;
		}
	}
	function verifyOrderNameAndDate()
	{
		orderCtrl = document.frmNewEmpInfo.order;
		if(orderCtrl.value==0)
		 {
			if(document.forms[0].txtOrderStartDate.value=='' && document.forms[0].txtOrderName.value=='')
			{
				alert('Please provide Sanction order details using text box or combo');
				return false;
			}
			else
			{
				return true;
			}
			
		 }
		else
		{
			return true;
		}
	}
	function saveInfo()
	{
		var statusSpecific = validateSpecificForm();
		if(statusSpecific)
		{			
			//date validation before submit
			if(!checkDate())
				return false;				
			if(!checkPostStartDate())
				return false;
			if(!checkDOR())
				return false;
			if(!checkBankDate())
				return false;			
			if(!checkMappingDate())
				return false;
			//date validation ends			
			if(document.frmNewEmpInfo.changePostCheck != null && document.frmNewEmpInfo.changePostCheck.checked==true && document.frmNewEmpInfo.prevPostEndDate.value=='')
			{
				alert('Please Enter Enddate of Current Post')
				return false;
			}
			if(document.frmNewEmpInfo.selPost!=null && document.frmNewEmpInfo.selPost.value==0 && document.frmNewEmpInfo.changePostCheck != null && document.frmNewEmpInfo.changePostCheck.checked==true && document.frmNewEmpInfo.prevPostEndDate.value!='')
			{	
				var psr=document.frmNewEmpInfo.psr.value;							
				if((psr=='${empCustomVO.psrNo}') && '' != '${empCustomVO.psrNo}')
				{		
					alert('You have not selected post or not modified post details');
					return false;
				}
			}
			if(!verifyPostMpgDate())
				return false;
			if(document.forms[0].elements['psrPostIdToCheck'].value!='')
				document.forms[0].elements['psrpostId'].value = document.forms[0].elements['psrPostIdToCheck'].value;
			selPostCtrl = document.getElementById("selPost");

			//Using follwoing function veryfing that sanction order no is selected from combo or textbox and doing respective validation
			if(!verifyOrderNameAndDate())
				return false;
			
			var fieldArray;			
			fieldArray = new Array('Salutation','emp_first_name','','emp_last_name','selClass','DOB','DOJ','EmpType','empEmail','cmbBankName','cmbBranchName','cmbAccType','txtAccNo','txtStartDate','selDesignation','psr','postNameTxt','postShrtNametxt','postTypeCmb','branchCmb','officeCmb','startDate','billNo','order');				
			var statusValidation = validateSpecificFormFields(fieldArray); 
		}
		
		if(statusValidation)
		{		
			if('${editFlag}' == 'N')
				document.frmNewEmpInfo.action="hrms.htm?actionFlag=saveNewEmployeeConfigDtls&flag=add";
			else if('${editFlag}' == 'Y')
				document.frmNewEmpInfo.action="hrms.htm?actionFlag=updateNewEmployeeConfigDtls&flag=edit";
				
			document.frmNewEmpInfo.emp_middle_name.value = document.frmNewEmpInfo.emp_middle_name.value + ' ';			
			document.frmNewEmpInfo.submit();
			document.frmNewEmpInfo.Submit.disabled=true;	
		}
	}
	function validateSpecificForm()
	{
		var name_rule_num="qwertyuiopasdfghjklzxcvbnm ASDFGHJKLPOIUYTREWQZXCVBNM";
		var statusFlag = true;
		
		var first_name_eng=document.getElementById("emp_first_name").value;
		if(first_name_eng!="")
	   	{
	   		for(var i=0;i<first_name_eng.length;i++)
			{
        		var cchar=first_name_eng.charAt(i);
		        if(name_rule_num.indexOf(cchar)==-1)
        		{	
        			alert('<fmt:message  bundle="${empEditListCommonLables}" key="ValidFNameAlert"/>');
        			document.getElementById("emp_first_name").value='';
        			document.getElementById("emp_first_name").focus();				
	      	  	 	return false;
	      	  	 	break;
        		}
	    	} 
	   	}
	   	var last_name_eng=document.getElementById("emp_last_name").value;
		if(last_name_eng!="")
	   	{
	   		for(var i=0;i<last_name_eng.length;i++)
			{
        		var cchar=last_name_eng.charAt(i);
		        if(name_rule_num.indexOf(cchar)==-1)
        		{
        			alert('<fmt:message  bundle="${empEditListCommonLables}" key="ValidLNameAlert"/>');
        			document.getElementById("emp_last_name").value='';
        			document.getElementById("emp_last_name").focus();
					
	      	  	 	return false;
	      	  	 	break;
        		}
	    	} 
	   	}
	   	return true;
	}
	function GoToPage()
	{
		document.frmNewEmpInfo.action="./hrms.htm?actionFlag=getViewEmployeeList";
		document.frmNewEmpInfo.submit();
	}
	function resetData()
	{
		document.frmNewEmpInfo.Salutation.value='';
		document.frmNewEmpInfo.emp_first_name.value='';
		document.frmNewEmpInfo.emp_middle_name.value='';
		document.frmNewEmpInfo.emp_last_name.value='';		
		document.frmNewEmpInfo.DOB.value='';
		document.frmNewEmpInfo.DOJ.value='';
		document.frmNewEmpInfo.DOR.value='';
		document.frmNewEmpInfo.selClass.value='0';
	}
	function resetForm()
			  {
			  	if(confirm("<fmt:message key="TABNAV.RESETCONFIRM" bundle="${lables}"></fmt:message>") == true)
			  	{
			  		document.frmNewEmpInfo.reset();
			  		<c:if test="${param.afterReset gt ''}">
						${param.afterReset};
					</c:if>
					window.location.reload();
			  	}
			  				  	
			  }
	
	function checkDate()
	{
		dob=document.frmNewEmpInfo.DOB.value;
		doj=document.frmNewEmpInfo.DOJ.value;

		if(dob!='' && doj!='' && compareDate(dob,doj) < 0)
		{
			alert('Date of Birth Should not be Greater than Date of Joining');
			//document.frmNewEmpInfo.DOB.value='';
			//document.frmNewEmpInfo.DOJ.value='';
			//document.frmNewEmpInfo.DOR.value='';
			document.frmNewEmpInfo.DOB.focus();
			return false;
		}

		return true;
	}
	

	function closeWindow()
	{
		window.close();
	}

	function verifyGPFName(control) 
	{              
	              var iChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789/  ";
	              var value="";
	              var valid=true;

	              for (var i=0; i<control.value.length;i++) 
	               {              
	                  if (iChars.indexOf(control.value.charAt(i))!=-1) 
	                 {

	                    value=value+control.value.charAt(i);
	                 }               
	                 else
	                 {                  
	                    valid=false;
	                 }
	              }                   
	              if(!valid)
	              {
	                  alert('Please Enter Proper Name');
	                  control.value="";
	                  control.focus();
	                  return false;
	              }
	              else
	              {
		              if(document.frmNewEmpInfo.selClass.value!='0' && control.value!='')
	            	  	document.frmNewEmpInfo.gradeName.value=document.frmNewEmpInfo.selClass.value;
	              }
	              //return true;              
	}
	//varun from combo filling by designation post only unmapped post
function GetPostfromDesg()
	{

			  var v=document.getElementById("selPost").length;
			
			  for(i=1;i<v;i++)
			  {
				  lgth = document.getElementById("selPost").options.length -1;				  
				  document.getElementById("selPost").options[lgth] = null;
			  }		
			  xmlHttp=GetXmlHttpObject();
			  if (xmlHttp==null)
			  {
				  alert ("Your browser does not support AJAX!");
				  return;
			  } 
			  
			  var url=''; 
			  var uri='';
			  
			  url= uri+'&dsgnId='+document.forms[0].elements['selDesignation'].value;
			  

			  var actionf="GetPostfromDesignation";
			  uri='./hrms.htm?actionFlag='+actionf;
			  url=uri+url; 
			  xmlHttp.onreadystatechange=GetPostsfromDesg;
			  xmlHttp.open("POST",encodeURI(url),true);
			  xmlHttp.send(null);		
	}

	function GetPostsfromDesg()
		{	
			if (xmlHttp.readyState==complete_state)
			{ 
				var post = document.getElementById("selPost");
				
						var XMLDoc=xmlHttp.responseXML.documentElement;
					    var entries = XMLDoc.getElementsByTagName('post-mapping');
						var val=0;
						var text='';
						 for ( var i = 0 ; i < entries.length ; i++ )
		     			 {
	     				    val=entries[i].childNodes[0].text;
	     				    text =entries[i].childNodes[1].text; 
	     				    
	     				    var y = document.createElement('option')   
	     			        y.value=val;
	     			        y.text=text;	
	     			        try
	   				        {      				    					
	                            post.add(y,null);
	           			    }
	 				        catch(ex)
	   				        {
	   			 		       post.add(y); 
	   			   	        }	
		                
		                 }
	  }
	}
	//End by varun
	function chkbillHeadunique()
	{
		var psr=document.frmNewEmpInfo.psr.value;
		var flag = 'Yes';		
		if(psr == document.forms[0].elements['psrIdToCheck'].value && '' != document.forms[0].elements['psrIdToCheck'].value)
		{			
			flag = 'No';
		}
		else if((psr=='${empCustomVO.psrNo}') && '' != '${empCustomVO.psrNo}')
		{		
			flag = 'No';
		}
		else
		{		
			flag = 'Yes';
		}		
		if(flag=='Yes')
		{
			var location_Id='${location_Id}';
			var psrPostId='${hrPayPsrPostMpg.psrPostId}';
			var mpgId =0;
			if(psr!=""&&location_Id!="")
			{
			try
		   	{   
		   		xmlHttp=new XMLHttpRequest();    
		   	}
			catch (e)
			{    // Internet Explorer    
				try
		     	{
		     		xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");   
		     	}
			    catch (e)
			    {
			    	try
		       		{
		            	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
		       		}
				    catch (e)
				    {
				        alert("Your browser does not support AJAX!");        
				        return false;        
				    }
				}
			}
				if(psrPostId != "" )
					{
					mpgId=psrPostId;
					}
				else
					{
					
					mpgId;
					}	
			
		    var url = "hrms.htm?actionFlag=chkpsrLocationunique&psr="+psr+"&location_Id="+location_Id+"&ppMapId="+mpgId;  
		    xmlHttp.onreadystatechange = function()
			{
				
				if (xmlHttp.readyState == 4) 
				{     
					
					if (xmlHttp.status == 200) 
					{
						var XMLDocForAjax=xmlHttp.responseXML.documentElement;
						var mpgflag = XMLDocForAjax.getElementsByTagName('pp-mapping');	
						
						if(mpgflag.length != 0)
						{
							if(mpgflag[0].childNodes[0].text!='null'&&mpgflag[0].childNodes[0].text!='-1')
							{			
								alert("Psr No is already Assigned");
								//document.frmNewEmpInfo.psr.value = '';
								document.frmNewEmpInfo.psr.focus();
								chkflag=0;
							}
							else
							chkflag=1;
						}
						
					}
				}
			}
			
			xmlHttp.open("POST", encodeURI(url) , false);    
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));	
		}
		flag ='Yes'; 
		return true;
		}
	}	

// To get order Name List
function getOrderNamesList()
	{   
		
	 	 var billSubHeadId = document.frmNewEmpInfo.billNo.value;		
	 	
	 	 var v=document.frmNewEmpInfo.order.length;			
		 for(i=1;i<v;i++)
		 {
			  lgth = document.frmNewEmpInfo.order.options.length -1;			  				  
			  document.frmNewEmpInfo.order.options[lgth] = null;
		 }		
		 xmlHttp=GetXmlHttpObject();
		 if (xmlHttp==null)
		 {
			  alert ("Your browser does not support AJAX!");
			  return;
		 } 
		  
		 var url; 
		 var uri='';
		 url= '&billSubHeadId=' + billSubHeadId;
		 var actionf="getOrderNamesList";
		 uri='./hrms.htm?actionFlag='+actionf;
		 url=uri+url;
		 xmlHttp.onreadystatechange=getOrderNames;
		 xmlHttp.open("POST",encodeURI(url),true);
		 xmlHttp.send(null);
	}

function getOrderNames()
{	
	if (xmlHttp.readyState==complete_state)
	 { 
	   var XMLDoc=xmlHttp.responseXML.documentElement;	
	
	   var orderNames = XMLDoc.getElementsByTagName('orderNames');
	   if(orderNames.length == 0)
	   {
	     window.status = 'No Records Found.';	     
	   }
	   else
	   {		    
		    window.status='';
			var order = document.getElementById("order");
			
			var XMLDoc=xmlHttp.responseXML.documentElement;
		    var entries = XMLDoc.getElementsByTagName('orderNames');
			var val=0;
			var text='';
			for ( var i = 0 ; i < entries.length ; i++ )
			{
				    val=entries[i].childNodes[0].text;
				    text =entries[i].childNodes[1].text; 
				    
				    var y = document.createElement('option')   
			        y.value=val;
			        y.text=text;	
			        try
			        {      				    					
			        	order.add(y,null);
				    }
			        catch(ex)
			        {
			        	order.add(y); 
		   	        }	
	        
	        }
	  }
 	}
}
function setPostNameText(obj)
{
		document.frmNewEmpInfo.postNameTxt.value = obj.value;
}
function checkAvailability()
{

	var newOrderName=trim(document.frmNewEmpInfo.txtOrderName.value);
	var dept=${locationCode};
	var orderDate=document.frmNewEmpInfo.txtOrderStartDate.value;
	
	if(newOrderName!=""&&dept!=""&&orderDate!="")
	{
		try {   
				xmlHttp=new XMLHttpRequest();
   		}
		catch(e){    // Internet Explorer    
			try {
	     		xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");   
    	 	}
	    	catch (e) {
		    	try {
	            	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
    	   		}
			    catch (e) {
			        alert("Your browser does not support AJAX!");        
			        return false;        
			    }
			}
		}
		var url = "hrms.htm?actionFlag=checkOrderNameAvailability&newOrderName="+newOrderName+"&dept="+dept+"&date="+orderDate;  	


    xmlHttp.onreadystatechange = function() {	

		if (xmlHttp.readyState == 4) {     			
			if (xmlHttp.status == 200) {			
				var XMLDocForAjax=xmlHttp.responseXML.documentElement;
				var orderNameMapping = XMLDocForAjax.getElementsByTagName('orderNameMapping');	

				var flag="true";				
				if(orderNameMapping.length != 0) {		

						if(orderNameMapping[0].childNodes[0].text==flag)
						{	
							if(document.frmNewEmpInfo.order.value==0)
							{
								alert("Order Name is already Exists, Please Enter other Name");							
								//document.frmNewEmpInfo.txtOrderName.value='';
								document.frmNewEmpInfo.txtOrderName.focus();
							}
						}
				}
				
			}
		}
	}
	
	xmlHttp.open("POST", encodeURI(url) , false);    
	xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
	xmlHttp.send(encodeURIComponent(null));	
	return true;
	}
	}
function chkDate(){
	
	if(document.frmNewEmpInfo.txtOrderEndDate.value!=null && document.frmNewEmpInfo.txtOrderEndDate.value!="")
	{	
		var diff = compareDate(document.frmNewEmpInfo.txtOrderStartDate.value,document.frmNewEmpInfo.txtOrderEndDate.value);
	    if(diff<0){
	    	alert("End Date must be Greater then Start Date");
	    	document.forms[0].elements("endDate").value=''; 
	    	document.forms[0].elements("endDate").focus();   	
	    	return false;
	    }
	}
	return true;    
}
function getOrderDetails()
{	
	 var orderHeadId = document.frmNewEmpInfo.order.value;	

	 if(document.frmNewEmpInfo.order.value==0)
	 {
		 	document.forms[0].lOrderMstId.value=0;
			document.forms[0].txtOrderName.value = '';     
	    	document.forms[0].txtOrderStartDate.value ='';        
	    	document.forms[0].txtOrderEndDate.value = '';  
			return false;
	 }
 	 
	 xmlHttp=GetXmlHttpObject();
	 if (xmlHttp==null)
	 {
		  alert ("Your browser does not support AJAX!");
		  return;
	 } 
	  
	 var url; 
	 var uri='';
	 url= '&lOrderHeadId=' + orderHeadId;
	 var actionf="getOrderDetails";
	 uri='./hrms.htm?actionFlag='+actionf;
	 url=uri+url;
	 xmlHttp.onreadystatechange=fillOrderDetails;
	 xmlHttp.open("POST",encodeURI(url),true);
	 xmlHttp.send(null);
}
function fillOrderDetails()
{	
	if (xmlHttp.readyState==complete_state)
	 { 
	   var XMLDoc=xmlHttp.responseXML.documentElement;	
	
	   var orderDetails = XMLDoc.getElementsByTagName('orderDetails');
	   if(orderDetails.length == 0)
	   {
	     window.status = 'No Records Found.';	     
	   }
	   else
	   {		    
		    window.status='';
			var order = document.getElementById("order");
			
			var XMLDoc=xmlHttp.responseXML.documentElement;
			
		    var entries = XMLDoc.getElementsByTagName('orderDetails');
		    if(entries[0].childNodes[0].text!=null)
            {   
		    	document.forms[0].lOrderMstId.value=entries[0].childNodes[0].text;
            }
		    if(entries[0].childNodes[1].text!=null)
            {   
		    	document.forms[0].txtOrderName.value = entries[0].childNodes[1].text;
            }
		    if(entries[0].childNodes[2].text!=null)
            {   
		    	document.forms[0].txtOrderStartDate.value = entries[0].childNodes[2].text;
            }
		    if(entries[0].childNodes[3].text!=null)
            {   
		    	document.forms[0].txtOrderEndDate.value = entries[0].childNodes[3].text;  
            }		
	  }
 	}
}

//
--></script>



<link rel="stylesheet"
	href="<c:url value="/themes/hdiits/tabcontent.css"/>" type="text/css" />
<hdiits:form name="frmNewEmpInfo" validate="true" method="POST" action="">	
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>New Employee Configuration</b></a></li>
	</ul>
	</div>

<div class="halftabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">
	<hdiits:hidden name="userId" default="${empCustomVO.userId}"/>
	<hdiits:hidden name="postId" default="${empCustomVO.postId}"/>	
	<hdiits:hidden name="psrpostId" default="${empCustomVO.psrpostId}"/>
	<hdiits:hidden name="lUserPostRltId" default="${empCustomVO.usrPostId}"/>
	<hdiits:hidden name="employeeId" default="${empCustomVO.hrEisEmpId}"/>
	<hdiits:hidden name="bankDtlsId" default="${empCustomVO.bankDtlsId}"/>	
	<hdiits:hidden name="ohpId" default="${empCustomVO.ohpId}"/>
	<hdiits:hidden name="orderHeadpostId" />
	<hdiits:hidden name="psrIdToCheck" />
	<hdiits:hidden name="psrPostIdToCheck" />
	<hdiits:hidden name="lOrderMstId" default="${orderMstId}"/>
	
	<hdiits:fieldGroup titleCaptionId="pay.usrDetails" bundle="${commonLables}" >
	<table align="center" cellspacing="2" cellpadding="2" border="0"  width="100%">
		<!-- New User Details Started -->
		
		<tr>			
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="Salutation" bundle="${empEditListCommonLables}"/></b></td>
			
			<td width="22%" align="left">			
				<hdiits:text name="Salutation" id="Salutation"
				maxlength="8" captionid="Salutation" size="6"
				bundle="${empEditListCommonLables}" validation = "txt.isrequired" default="${empCustomVO.empSal}" mandatory="true" /></td>
			<td width="4%" align="center"></td>
			<td width="22%" align="left"></td>			
			<td width="22%" align="left"></td>			
		</tr>
		<tr>			
			<td width="22%" align="left"></td>
			
			<td width="22%" align="left"><b><fmt:message key="FIRST_NAME" bundle="${empEditListCommonLables}"/></b></td>
			<td width="4%" align="center"></td>
			<td width="22%" align="left"><b><fmt:message key="MIDDLE_NAME" bundle="${empEditListCommonLables}"/></b></td>			
			<td width="22%" align="left"><b><fmt:message key="LAST_NAME" bundle="${empEditListCommonLables}"/></b></td>			
		</tr>
		<tr>			
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="NAME_EN" bundle="${empEditListCommonLables}"/></b></td>
			
			<td width="22%" align="left"><hdiits:text name="emp_first_name" id="emp_first_name" captionid="FIRST_NAME" bundle="${empEditListCommonLables}" maxlength="30" validation="txt.isrequired" default="${empCustomVO.empFname}" mandatory="true" /></td>
			<td width="4%" align="center"></td>
			<td width="22%" align="left"><hdiits:text name="emp_middle_name" id="emp_middle_name" captionid="MIDDLE_NAME" bundle="${empEditListCommonLables}" maxlength="30" default="${empCustomVO.empMname}"  /></td>			
			<td width="22%" align="left"><hdiits:text name="emp_last_name" id="emp_last_name" captionid="LAST_NAME" bundle="${empEditListCommonLables}" maxlength="30" validation="txt.isrequired" default="${empCustomVO.empLname}" mandatory="true" /></td>			
		</tr>	
		<tr>
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="GENDER" bundle="${commonLables}"/></b></td>			
			<td width="22%" align="left">
				<hdiits:radio caption='Male'  bundle="${commonLables}" name="gender" value="M" default="M" validation="sel.isradio" errCaption ="Gender"></hdiits:radio>
				<hdiits:radio caption='Female' bundle="${commonLables}" name="gender" value="F" validation="sel.isradio" ></hdiits:radio>
			</td>
			<td width="4%" align="center"></td>
			<td width="22%" align="left"><b><fmt:message key="CLASS" bundle="${empEditListCommonLables}"/></b></td>
			<td width="22%" align="left">					
					<hdiits:select name="selClass" id="selClass" captionid="CLASS" bundle="${empEditListCommonLables}" mandatory="true" default="${empCustomVO.usrGradeId}" validation="sel.isrequired" sort="false">
						<hdiits:option value="0" selected="true"><hdiits:caption captionid="SELECT" bundle="${empEditListCommonLables}"/></hdiits:option>
						<c:forEach var="classes" items="${userClassTypeList}">
								<hdiits:option value="${classes.gradeId}"><c:out value="${classes.gradeName}" /></hdiits:option>							
						</c:forEach>						
					</hdiits:select></td>
					
		</tr>
		<tr>			
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="DATE_OF_BIRTH" bundle="${empEditListCommonLables}"/></b></td>
			<td width="22%" align="left">
					<hdiits:dateTime name="DOB" captionid="DATE_OF_BIRTH" bundle="${empEditListCommonLables}"  mandatory="true" afterDateSelect="retdate()" validation="txt.isdt,txt.isrequired" onblur="retdate();" />
			</td>
			<td width="4%" align="center"></td>
			<td width="22%" align="left"><b><fmt:message key="DATE_OF_JOINING" bundle="${empEditListCommonLables}"/></b></td>			
			<td width="22%" align="left"><hdiits:dateTime name="DOJ" captionid="DATE_OF_JOINING" bundle="${empEditListCommonLables}"  mandatory="true" validation="txt.isdt,txt.isrequired" onblur="checkDate()"/></td>			
		</tr>
		<tr>			
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="LEAVE_DATE" bundle="${empEditListCommonLables}"/></b>
			</td>
			<td width="22%" align="left">
					<hdiits:dateTime name="DOR" captionid="LEAVE_DATE" bundle="${empEditListCommonLables}"   />
			</td>
			<td width="4%" align="center"></td>
			<td width="22%" align="left"></td>			
			<td width="22%" align="left"></td>			
		</tr>
		
	<!-- New User Details Ended -->
</table>
</hdiits:fieldGroup>
<hdiits:fieldGroup titleCaptionId="pay.gpfDetails" bundle="${commonLables}" >
<table align="center" cellspacing="2" cellpadding="2" border="0"  width="100%">
	<!-- GPF Details Started -->
		
		<tr>			
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="GPF.ACC_NO" bundle="${commonLables}"/></b>
			</td>
			<td width="22%" align="left">
					<hdiits:text   name="gpfAcctNo" id="gpfAcctNo" default="${empCustomVO.gpfAccNo}"  captionid="ACC.NO" bundle="${commonLables}"    onblur="verifyGPFName(this);"  maxlength="20"   size="22" /> 
			</td>
			<td width="4%" align="center"></td>
			<td width="22%" align="left"><b><fmt:message key="CLASS" bundle="${commonLables}"/></b></td>			
			<td width="22%" align="left"><hdiits:hidden name ="GradeName" />
			<hdiits:select captionid="CLASS" bundle="${commonLables}" sort="false" style="width:150px;"
					default="${empCustomVO.gpfGradeId}" name="gradeName" id="gradeName" readonly="false">
					<hdiits:option value="-1" selected="true">-----------Select-----------</hdiits:option>
					<c:forEach var="class" items="${gpfClassTypeList}">
						<hdiits:option value="${class.gradeId}">
							<c:out value="${class.gradeName}" />
						</hdiits:option>
					</c:forEach>

				</hdiits:select></td>			
		</tr>
		
		<tr>			
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="PANNO" bundle="${commonLables}"/></b>
			</td>
			<td width="22%" align="left">
					<hdiits:text name="empPAN" id="empPAN" captionid="PANNO" bundle="${commonLables}" maxlength="13" default="${empCustomVO.panNo}"  /> 
			</td>
			<td width="4%" align="center"></td>
			<td width="22%" align="left" >
				<b><fmt:message key="EMP_TYPE" bundle="${commonLables}"/></b>
			</td>			
			<td width="22%" align="left">
				<hdiits:select  captionid="EMP_TYPE" bundle="${commonLables}" name="EmpType" sort="false" validation="sel.isrequired" default="${empCustomVO.empType}"   mandatory ="true" > 
					<hdiits:option value=""  selected="true">-----------Select-----------</hdiits:option>
					<c:forEach var="emptype" items="${empTypeList}">
			    					<hdiits:option value="${emptype.lookupId}">
									<c:out value="${emptype.lookupDesc}"/></hdiits:option>
					</c:forEach>	
				</hdiits:select>	
			</td>			
		</tr>
		<tr>			
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="email" bundle="${commonLables}"/></b></td>			
			<td width="22%" align="left" colspan="2">
				<hdiits:Email name="empEmail" captionid="email" bundle="${commonLables}"  default="${empCustomVO.email}"  validation ="txt.email" />
			</td>		
			
			<td width="22%" align="left"></td>			
			<td width="22%" align="left"></td>		
		</tr>
	<!-- GPF Details Ended -->
</table>
</hdiits:fieldGroup>
<hdiits:fieldGroup titleCaptionId="pay.bankDetails" bundle="${commonLables}" >
<table align="center" cellspacing="2" cellpadding="2" border="0"  width="100%">
	<!-- Bank Details Started -->
		
		<tr>			
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="BM.bankName" bundle="${commonLables}"/> </b></td>			
			<td width="22%" align="left">
				<hdiits:select name="cmbBankName" sort="false" validation="sel.isrequired" id="bankID" size="1"  mandatory="true" default="${empCustomVO.bankId}"
				caption="Bank Name" onchange="checkUpdatedtl();" style="width:240px" >
				<hdiits:option value="Select"  selected="true">-------------------Select-------------------</hdiits:option>
			     <c:forEach items ="${resValue.bankList}" var="bankList">
			     <c:choose>
			     <c:when test="${bankList.bankId eq actionList.hrEisBankMst.bankId}">
	    			  <hdiits:option value="${actionList.hrEisBankMst.bankId}" selected="true"> ${actionList.hrEisBankMst.bankName} </hdiits:option>
	    	     </c:when>
	    	     <c:otherwise>
				   <hdiits:option value="${bankList.bankId}"> ${bankList.bankName} </hdiits:option>
				  </c:otherwise>
				 </c:choose>
				</c:forEach>  
				</hdiits:select>		
			</td>		
			<td width="4%" align="center"></td>
			<td width="22%" align="left"><b><fmt:message key="BR.NAME" bundle="${commonLables}"/> </b></td>			
			<td width="22%" align="left">
				<hdiits:select name="cmbBranchName" validation="sel.isrequired" id="branchID" size="1"  mandatory="true" 
					caption="Branch Name" sort="false" default="${empCustomVO.bankBranchId}" style="width:240px">
	          	<hdiits:option value="Select"  selected="true">-------------------Select-------------------</hdiits:option>
	          		<c:if test="${bankBranchList ne null}">
	          		<c:forEach items="${bankBranchList}" var="bankBranchList">
	          		  <hdiits:option value="${bankBranchList.branchId}" selected="true"> ${bankBranchList.branchName} </hdiits:option>
	          		</c:forEach>
	          		</c:if>
	         	</hdiits:select>
         	</td>		
		</tr>
		<tr>			
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="ACC.TYPE" bundle="${commonLables}"/></b></td>			
			<td width="22%" align="left">
				<hdiits:select name="cmbAccType" id="cmbAccType" size ="1" captionid="accType" caption="Acc Type" 
				validation="sel.isrequired"  sort="false" mandatory="true" default="${empCustomVO.bankAccType}" style="width:240px"> 
				<hdiits:option value="select"  selected="true">-------------------Select------------------- </hdiits:option>
					<c:forEach items="${resValue.accountTypeList}" var="list">
						<c:choose>
							<c:when test="${list.lookupId eq actionList.bankAcctType}">
								<hdiits:option value="${list.lookupId}" selected="true"> ${list.lookupDesc} </hdiits:option>
							</c:when>
							<c:otherwise>
								<c:if test="${sAccType eq list.lookupId}">
									<hdiits:option value="${list.lookupId}" selected="true"> ${list.lookupDesc} </hdiits:option>
								</c:if>
								<c:if test="${sAccType ne list.lookupId}">
									<hdiits:option value="${list.lookupId}" > ${list.lookupDesc} </hdiits:option>
								</c:if>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</hdiits:select>
			</td>		
			<td width="4%" align="center"></td>
			<td width="22%" align="left"><b><fmt:message key="ACC.NO" bundle="${commonLables}"/></b></td>			
			<td width="22%" align="left">
			<hdiits:text
				name="txtAccNo" captionid="ACC.NO" bundle="${commonLables}"
				 size="30" maxlength="19" default="${empCustomVO.bankAccNo}" mandatory="true" validation="txt.isrequired" />
			</td>		
		</tr>
	
		<tr>			
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="ACC.ST.DATE" bundle="${commonLables}"/></b></td>			
			<td width="22%" align="left">
				<c:choose>
				<c:when test="${flag ne 'insert'}">
					<hdiits:dateTime name="txtStartDate" mandatory="true" validation="txt.isdt,txt.isrequired" captionid="ACC.ST.DATE" bundle="${commonLables}" />	 
				</c:when>
				<c:otherwise>
					<hdiits:dateTime name="txtStartDate" mandatory="true"  validation="txt.isdt,txt.isrequired" captionid="ACC.ST.DATE" bundle="${commonLables}" />	 		
				</c:otherwise>
				</c:choose>
			</td>		
			<td width="4%" align="center"></td>
			<td width="22%" align="left"></td>			
			<td width="22%" align="left"></td>		
		</tr>
	<!-- Bank Details Ended -->
</table>
</hdiits:fieldGroup>
<hdiits:fieldGroup titleCaptionId="pay.postDetails" bundle="${commonLables}" > 
<table align="center" cellspacing="2" cellpadding="2" border="0"  width="100%">
	<!-- Post Details Started -->	
		
		<c:if test="${editFlag eq 'Y'}">
			<tr>			
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="pay.changePost" bundle="${commonLables}"/></b></td>			
			<td width="22%" align="left">
				<hdiits:checkbox name="changePostCheck" id="changePostCheck" value="Y" onclick="enablePostSelection()" />
			</td>		
			<td width="4%" align="center"></td>
			<td width="22%" align="left"><b><fmt:message key="pay.prevPostEndDate" bundle="${commonLables}"/></b></td>			
			<td width="22%" align="left"><hdiits:dateTime name="prevPostEndDate" validation="txt.isdt,txt.isrequired" captionid="pay.prevPostEndDate" bundle="${commonLables}" /></td>		
			</tr>
		</c:if>
		<tr>			
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="DSGN" bundle="${userPostMapping}"/></b></td>			
			<td width="22%" align="left">
				<hdiits:select captionid="DSGN" bundle="${userPostMapping}" name="selDesignation" id="selDesignation" sort="false" style="width:240px"
						caption="Designation" validation="sel.isrequired" onchange="GetPostfromDesg()" mandatory="true" default="${empCustomVO.dsgnId}">
					<hdiits:option value="0" selected="true">
						<hdiits:caption captionid="SELECT" bundle="${userPostMapping}" />
					</hdiits:option>
					<c:forEach var="orgDesignationMst" items="${arDesignationVO}">
						<hdiits:option value="${orgDesignationMst.dsgnCode}">${orgDesignationMst.dsgnName}</hdiits:option>
					</c:forEach>
				</hdiits:select>
			</td>		
			<td width="4%" align="center"></td>
			<td width="22%" align="left"><b><fmt:message key="POST" bundle="${userPostMapping}"/></b></td>			
			<td width="22%" align="left">
			<c:if test="${editFlag eq 'Y'}">
				<hdiits:select captionid="POST" bundle="${userPostMapping}" default="${empCustomVO.postId}" name="selPost" id="selPost" sort="false" onchange="getSelectedPostDtls()" 
				style="width:240px" caption="Post" validation="sel.isrequired" readonly="true" >
					<hdiits:option value="0"  selected="true"><hdiits:caption captionid="SELECT" bundle="${userPostMapping}" /></hdiits:option>
				</hdiits:select>
			</c:if>
			<c:if test="${editFlag ne 'Y'}">
				<hdiits:select captionid="POST" bundle="${userPostMapping}" default="${empCustomVO.postId}" name="selPost" id="selPost" sort="false" onchange="getSelectedPostDtls()" style="width:240px"
						caption="Post" validation="sel.isrequired" >
					<hdiits:option value="0"  selected="true"><hdiits:caption captionid="SELECT" bundle="${userPostMapping}" /></hdiits:option>
				</hdiits:select>
			</c:if>
			</td>		
		</tr>
		
		<tr>			
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="PSRPOST" bundle="${adminCreatePostLabel}"/></b></td>			
			<td width="22%" align="left"><hdiits:number  id = "psr" name="psr" caption="Psr No."  bundle="${adminCreatePostLabel}"  size="2" default="${empCustomVO.psrNo}" validation="txt.isrequired" onblur="chkbillHeadunique();" maxlength="6" mandatory="true" /></td>		
			<td width="4%" align="center"></td>
			<td width="22%" align="left"><b><fmt:message key="admin.PostShortName" bundle="${adminCreatePostLabel}"/></b></td>			
			<td width="22%" align="left"><hdiits:text name="postShrtNametxt" id="postShrtNametxt" captionid="admin.PostShortName"  default="${empCustomVO.postShrtName}" style="width:180px" bundle="${adminCreatePostLabel}" mandatory="true" validation="txt.isrequired" maxlength="15" onblur="setPostNameText(this)"></hdiits:text></td>					
		</tr>
		
		<tr>			
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="admin.PostName" bundle="${adminCreatePostLabel}"/></b></td>			
			<td width="22%" align="left"><hdiits:text name="postNameTxt" id="postNameTxt" captionid="admin.PostName" style="width:222px"  bundle="${adminCreatePostLabel}" default="${empCustomVO.postName}"  validation="txt.isrequired" mandatory="true"></hdiits:text></td>					
			<td width="4%" align="center"></td>
			<td width="22%" align="left"><b><fmt:message key="admin.PostType" bundle="${adminCreatePostLabel}"/></b></td>			
			<td width="22%" align="left">
				<hdiits:select name="postTypeCmb" id="postTypeCmb" captionid="admin.PostType" style="width:240px" default="${empCustomVO.postType}" bundle="${adminCreatePostLabel}" mandatory="true" validation="sel.isrequired" sort="false">
					<hdiits:option value=""  selected="true">
					<hdiits:caption captionid="admin.select" bundle="${adminCreatePostLabel}"></hdiits:caption></hdiits:option>
					<c:forEach items="${postTypeList}" var="postType">
						<hdiits:option value="${postType.lookupName}">
						<c:out value="${postType.lookupDesc}" /></hdiits:option>
					</c:forEach>
					</hdiits:select>
			</td>		
		</tr>
		<tr>			
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="admin.Branch" bundle="${adminCreatePostLabel}"/></b></td>			
			<td width="22%" align="left">
				<hdiits:select name="branchCmb" id="branchCmb" captionid="admin.Branch" style="width:240px" sort="false" bundle="${adminCreatePostLabel}" default="${empCustomVO.postBranchId}" mandatory="true" validation="sel.isrequired">
					<hdiits:option value="" ><hdiits:caption captionid="admin.select" bundle="${adminCreatePostLabel}"></hdiits:caption></hdiits:option>
					<c:forEach items="${branchList}" var="branch">
					<c:choose>
							<c:when test="${branch.branchCode eq selOfficeBranch}">
							 	<hdiits:option value="${branch.branchCode}" selected="true">${branch.branchName}</hdiits:option>
							</c:when>
							<c:otherwise>
								<hdiits:option value="${branch.branchCode}">${branch.branchName}</hdiits:option>
							</c:otherwise>
					</c:choose>
					</c:forEach>
				</hdiits:select>
			</td>		
			<td width="4%" align="center"></td>
			<c:if test="${location_Id eq cash2LocationCode}" >
				<td width="22%" align="left"><b><fmt:message key="admin.Office" bundle="${adminCreatePostLabel}"/></b></td>			
				<td width="22%" align="left">
					<hdiits:select name="officeCmb" id="officeCmb" captionid="admin.Office"  sort="false"  default="${empCustomVO.officeId}" bundle="${adminCreatePostLabel}" mandatory="true" validation="sel.isrequired" style="width:240px">
						<hdiits:option value=""  selected="true"><hdiits:caption
							captionid="admin.select" bundle="${adminCreatePostLabel}"></hdiits:caption></hdiits:option>
						<c:forEach items="${officeList}" var="office">
							<c:choose>
			     			<c:when test="${office.officeId eq empCustomVO.officeId}">
								<option value="<c:out value="${office.officeId}"/>" selected="true">
								<c:out value="${office.officeId}" /><c:out value="," /><c:out value="${office.officeName}" /></option>
							</c:when>
							<c:otherwise>
								<option value="<c:out value="${office.officeId}"/>">
								<c:out value="${office.officeId}" /><c:out value="," /><c:out value="${office.officeName}" /></option>
							</c:otherwise>
							</c:choose>
						</c:forEach>
					</hdiits:select>
				</td>		
			</c:if>
			<c:if test="${location_Id ne cash2LocationCode}" >			
				<td width="22%" align="left"></td>			
				<td width="22%" align="left"></td>		
			</c:if>
		</tr>
		<c:if test="${location_Id eq cash2LocationCode}" >
		<tr>			
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="admin.previousPost" bundle="${adminCreatePostLabel}"/></b></td>			
			<td width="22%" align="left">
				<hdiits:select name="previousPostCmb" id="previousPostCmb" onchange="fn_getRemarks(this)"  sort="false"   default="${empCustomVO.parentPostId}"  captionid="admin.previousPost" style="width:240px" bundle="${adminCreatePostLabel}">
				<hdiits:option value=""  selected="true"><hdiits:caption
						captionid="admin.select" bundle="${adminCreatePostLabel}"></hdiits:caption></hdiits:option>
					<c:forEach items="${prevPostList}" var="previousPost">
						<option value="<c:out value="${previousPost.orgPostMst.postId}"/>">
						<c:out value="${previousPost.postName}" /></option>
					</c:forEach>
					</hdiits:select>
			</td>		
			<td width="4%" align="center"></td>
			<td width="22%" align="left"><b><fmt:message key="admin.remarks" bundle="${adminCreatePostLabel}"/></b></td>			
			<td width="22%" align="left"><hdiits:textarea name="remarksTxt" id="remarksTxt" default="${empCustomVO.remarks}" captionid="admin.remarks" style="width:180px; height:40px;" bundle="${adminCreatePostLabel}" condition="previousPostSelected()" validation="txt.isrequired"></hdiits:textarea></td>		
		</tr>
		</c:if>
		<tr>			
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="pay.startDate" bundle="${commonLables}"/></b></td>			
			<td width="22%" align="left"><hdiits:dateTime name="startDate" captionid="admin.startDate" bundle="${adminCreatePostLabel}" mandatory="true" validation="txt.isdt,txt.isrequired" onblur="checkPostStartDate()" ></hdiits:dateTime></td>		
			<td width="4%" align="center"></td>
			<td width="22%" align="left"><b><fmt:message key="pay.endDate" bundle="${commonLables}"/></b></td>			
			<td width="22%" align="left"><hdiits:dateTime name="endDate"  captionid="admin.endDate" bundle="${adminCreatePostLabel}" ></hdiits:dateTime></td>		
		</tr>
		<tr>			
			<td width="22%" align="left"></td>			
			<td width="22%" align="left">
										
			</td>		
			<td width="4%" align="center"></td>
			<td width="22%" align="left"></td>			
			<td width="22%" align="left"></td>		
		</tr>
	<!-- Post Details Ended -->
</table>
</hdiits:fieldGroup>

<hdiits:fieldGroup titleCaptionId="pay.billDetails" bundle="${commonLables}" > 
<table align="center" cellspacing="2" cellpadding="2" border="0"  width="100%">	
	<!-- Bill Mapping Details Started -->
		<tr>			
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="PR.BILLNO" bundle="${commonLables}"/></b></td>
				<td width="22%" align="left">
				<hdiits:select name="billNo"
					size="1" id="billNo" sort="false" caption="BillNo"
					captionid="billNo" validation="sel.isrequired" mandatory="true"
					default="${empCustomVO.billSubheadId}"
					onchange="getOrderNamesList();">
					<hdiits:option value="0" selected="true"> --Select-- </hdiits:option>
					<c:forEach var="billList" items="${billNoList}">
						<hdiits:option value="${billList.billHeadId}">
							<c:out value="${billList.billId}">
							</c:out>
						</hdiits:option>
					</c:forEach>
				</hdiits:select></td>
				<td width="4%" align="center"></td>
			<td width="22%" align="left"><b><fmt:message key="OM.orderName" bundle="${commonLables}"/></b></td>			
			<td width="22%" align="left">
				<hdiits:select name="order" style="width:240px"  sort="false" default="${empCustomVO.sanctionOrderId}" captionid="OM.orderName" bundle="${commonLables}"  size ="1"  onchange="getOrderDetails()">
					<hdiits:option value="0"  selected="true">-------------Select-------------</hdiits:option>		
					<c:forEach var="orderList" items="${sanctOrderList}">
		         		<hdiits:option value="${orderList.orderHeadId}"><c:out value="${orderList.orderName}"> ${orderList.orderHeadId}</c:out></hdiits:option>
		         	</c:forEach>						
				</hdiits:select>
			</td>		
		</tr>
		
		<tr >
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="OM.orderName" bundle="${commonLables}"/></b></td>
			<td width="22%" align="left"><hdiits:text id = "txtOrderName" name="txtOrderName" caption="Order Name" validation="" maxlength="50" size="30"
			onblur="checkAvailability()" default="${lStrOrderName}"  onkeypress="if(event.keyCode == 13) event.returnValue = false;"/></td>
			<td width="4%" align="center"></td>
			<td width="22%" align="left"><b><fmt:message key="ST.DATE" bundle="${commonLables}"/></b></td>
			<td width="22%" align="left"><hdiits:dateTime caption="ST.DATE"  name="txtOrderStartDate"  onblur="checkAvailability()" validation="txt.isdt" bundle="${commonLables}"/></td>
		</tr>		
		
		<tr>		
			<td width="22%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:message key="OM.endDate" bundle="${commonLables}"/></b></td>
			<td width="22%" align="left"><hdiits:dateTime caption="OM.endDate"  name="txtOrderEndDate" onblur="chkDate()" validation="txt.isdt" bundle="${commonLables}"/> </td>
			<td width="4%" align="center"></td>
			<td width="22%" align="left"></td>
			<td width="22%" align="left"></td>		
		</tr>		
		
	<!-- Bill Mapping Details Ended -->
	</table>
</hdiits:fieldGroup>

	<table align="center">	
		<tr>
			<td align="center">
				<c:if test="${empty empInfoDtls}">
					<br></br><hdiits:button name="Submit" type="button" captionid="SUBMIT" bundle="${empEditListCommonLables}" onclick="saveInfo()"></hdiits:button>
				</c:if>
				<c:if test="${not empty empInfoDtls}">
					<br></br><hdiits:button name="Update" type="button" captionid="UPDATE" bundle="${empEditListCommonLables}" onclick="saveInfo()"></hdiits:button>
				</c:if>
			</td>

			<td align="center" >
				<br></br><hdiits:button  name="reset" type="button"  style="display:none" captionid="RESET" bundle="${empEditListCommonLables}" onclick="resetForm()"></hdiits:button>
			</td>
			<td align="center">
				<br></br><hdiits:button name="Close" type="button" captionid="CLOSE" bundle="${empEditListCommonLables}" onclick="GoToPage()"></hdiits:button>
			</td>
		</tr>
	</table>

 </div>
 
<hdiits:hidden name="userId" id="userId" default="${selectedUserId}"></hdiits:hidden>

<script type="text/javascript"><!--
	initializetabcontent("maintab");
	document.frmNewEmpInfo.Salutation.focus();
	document.frmNewEmpInfo.txtStartDate.value='${firstDay}';
	if("${empCustomVO.bankAccStartDt}"!=null&&"${empCustomVO.bankAccStartDt}"!='')
	{
		document.frmNewEmpInfo.txtStartDate.value='${empCustomVO.bankAccStartDt}';		
	}
	else
	{
		document.frmNewEmpInfo.txtStartDate.value='${firstDay}';	
	}	
	document.frmNewEmpInfo.DOB.value='${empCustomVO.empDOB}';
	document.frmNewEmpInfo.DOJ.value='${empCustomVO.empDOJ}';
	document.frmNewEmpInfo.DOR.value='${empCustomVO.empDOR}';
	
	if("${lStrOrderStartDate}" != '')
	{
		document.frmNewEmpInfo.txtOrderStartDate.value='${lStrOrderStartDate}';
	}
	if("${lStrOrderEndDate}" != '')
	{
		document.frmNewEmpInfo.txtOrderEndDate.value='${lStrOrderEndDate}';
	}
	
	if("${empCustomVO.postStartDate}"!=null && "${empCustomVO.postStartDate}"!='')
	{
		document.frmNewEmpInfo.startDate.value='${empCustomVO.postStartDate}';
	}
	else
	{
		document.frmNewEmpInfo.startDate.value='${firstDay}';
	}
	
	document.frmNewEmpInfo.endDate.value='${empCustomVO.postEndDate}';

	if('${empCustomVO.bankAccNo}'==0)
		document.frmNewEmpInfo.txtAccNo.value='';
	
	var ctrlGender = document.frmNewEmpInfo.gender;	
	for (var i=0;i<2;i++)
	{	
		if(ctrlGender[i].value=='${empCustomVO.gender}')
		{
			ctrlGender[i].checked=true;
		}
	}
	
	if('${msg}'!=null && '${msg}'!='')
	{
		alert('${msg}' );				
		var url="./hrms.htm?actionFlag=getViewEmployeeList&edit=N";
		document.frmNewEmpInfo.action=url;
		document.frmNewEmpInfo.submit();
	}

--></script>
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' controlNames="Salutation,emp_first_name,emp_middle_name,emp_last_name,selClass,DOB,DOJ,EmpType,empEmail,cmbBankName,cmbBranchName,cmbAccType,txtAccNo,txtStartDate,selDesignation,psr,postNameTxt,postShrtNametxt,postTypeCmb,branchCmb,officeCmb,startDate,billNo,order" />	
</div>
</hdiits:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>