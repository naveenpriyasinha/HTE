// For NomineeDtls.jsp

var navDisplay = true,count=0,v_age,familyMemeber=0,familyMemeberId,mappingArrCount=0,editRowId;
var showLinksId,count=0,globalFlagDeleteDisable=false,minorFlag=false,checkDraftOrPendingReq,dbRecord=false;
var parentId=0,addOrUpdate=0,addMe=false,draftArrCount=-1,deleteArrCount=0,deleteAppArrCount=0,familyMemeberCount=0;
var dbRowsIdCount=0,dbRowsSrNoCount=0,disableOtherNomineeFlag=true;
var dbRowsSrNo=new Array();
var dbRowsId=new Array();						
var mappingArr =new Array();
var draftArr = new Array();
var deleteArr = new Array();
var deleteAppArr= new Array();
var familyMemeberArr = new Array();
var displayFieldArrayInTable = new Array('nomn_nameCmb1','nomi_RelationCmb1','nomi_DOBCmb1','nomi_personAgeCmb1','minorHidden','nomi_shareCmb1');

function resetAllFlag()
{
	updateRow = null;
}

function addOrUpdateRecordForNominee(methodName, actionFlag, fieldArray) 
{	
	xmlHttp=GetXmlHttpObject();
	if (xmlHttp==null) 
	{
		alert(nomineeDtlsAlertMsgArr[4]);
		return;
	} 
	var reqBody = getRequestBody(fieldArray);
	var url='actionFlag=' + actionFlag + '&' + reqBody;
	methodName = methodName + "()";
	xmlHttp.onreadystatechange=function() { eval(methodName);}
	xmlHttp.open('POST', 'hdiits.htm', true);
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.setRequestHeader("Content-length", url.length);
	xmlHttp.setRequestHeader("Connection", "close");
	xmlHttp.send(url);
}	

function goToHomePage()
{
	document.frmNominee.action = "hrms.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
	document.frmNominee.submit();
}

function checkValidSharePercentage()
{
	if(workFlowEnabled==false)		
	{	
		var totalShare=0;
		var num = document.getElementById('nomineeAddDataTable').rows.length;

		for(var i=1;i<num;i++)
		{		
			trow=document.getElementById('nomineeAddDataTable').rows[i];
			totalShare=parseFloat(totalShare)+parseFloat(trow.cells[6].innerText);
		}

		if(parseFloat(totalShare)>100)
		{
			alert(nomineeDtlsAlertMsgArr[0]);
			return 'error';
		}

		if(parseFloat(totalShare)<=100)
		{
			document.getElementById('errorMessage').style.display='none';	
			var ans=messageDisplay();	
		}
		else
		{
			alert(nomineeDtlsAlertMsgArr[1]+ '  : ' +totalShare);
			getFieldGroupObj(document.getElementById("errorMessage"));
			return 'error';
		}
	}	
}	

function SubmitNominee()
{				
	var flag_Submit=true,totalShare=0;
	try
	{
		var num = document.getElementById('nomineeAddDataTable').rows.length;		

		for(var i=1;i<num;i++)
		{													
			try{						
				trow= document.getElementById('nomineeAddDataTable').rows[i];
				if(trow.cells[7].innerText.indexOf('/')!=-1)
				{																
					totalShare=parseFloat(totalShare)+parseFloat(trow.cells[6].innerText);	
				}
			}catch(e){break;}				
		}
	}catch(e){}
	if (workFlowEnabled)
	{
		try{			
			var num = document.getElementById('nomineeApproveDataTable').rows.length;											
			for(var i=1;i<num;i++)
			{
				try
				{						
					trow= document.getElementById('nomineeApproveDataTable').rows[i];
					if(trow.style.display=='' && trow.childNodes[7].style.display=='')
					{									
						var addShareColumnData = 0;
						addShareColumnData= parseFloat(trow.cells[6].innerText);							
						totalShare=parseFloat(totalShare)+parseFloat(addShareColumnData);
					}
				}catch(e)
				{						
				}				
			}
		}catch(e){}
	}

	if (workFlowEnabled)	
	{
		var countNow = document.getElementById("Share").value; 	
		totalShare=totalShare+parseFloat(countNow);
	}
	if(parseFloat(totalShare)<=100){flag_Submit=true;}	
	else{flag_Submit=false;}
	if(flag_Submit==true){
		document.getElementById('errorMessage').style.display='none';	
		var ans=messageDisplay();	
	}
	else
	{
		alert(nomineeDtlsAlertMsgArr[1]+ '  : ' +totalShare);
		getFieldGroupObj(document.getElementById("errorMessage"));
	}
}

function Save_Draft()
{
		var x1;					
		for (x1 in deleteAppArr)
		{
			var map =deleteAppArr[x1].toString();					
			var map1=map.substring(3, map.length);				
			if(map1.indexOf("encXML")!=-1)
			{
				var map2=map.substring(9, map.length);				
				deleteArr[deleteArrCount]=document.getElementById("encXML"+map2).value;
				deleteArrCount=deleteArrCount+1;
			}								
			for (x in deleteArr)
			{
				var xmlFileName = deleteArr[x].toString();
				if(xmlFileName.indexOf("$/$")!=-1)
				{
					rowIdArr=xmlFileName.split("$/$");
					deleteArr[x]=rowIdArr[0];
				}					
			}
			var num = document.getElementById('nomineeAddDataTable').rows.length;											
			for(var i=1;i<num;i++)
			{							
				trow=document.getElementById('nomineeAddDataTable').rows[i];
				rowId=trow.id;
				try {				
					var hField = rowId.substring(3, rowId.length);
					var xmlFileName = document.getElementById(hField).value;						
					if(xmlFileName.indexOf("$/$")!=-1)
					{
						rowIdArr=xmlFileName.split("$/$");
						document.getElementById(hField).value=rowIdArr[0];
					}							
				}catch(e){}
			} 
		}			
		document.frmNominee.action = "hrms.htm?actionFlag=SubmitNomineeDetails&draft=1&DeleteArr="+deleteArr+"&workFlowEnabled=true";
		document.frmNominee.submit();
}
function checkMeForGPF(str)
{			
	if(str=='GPF')
	{
		disableOtherNomineeFlag=false;
		document.frmNominee.nomiType[0].disabled=true;									
	}
}

var inc_pendingCount=0,inc_draft=0;
function increment(){inc_pendingCount=inc_pendingCount+1;return inc_pendingCount;}
function incrementDraft(){inc_draft=inc_draft+1;return inc_draft;}
function countRows(getCount){if(count<getCount){count=getCount;}}

/*function Age(birthDate)
{								
	if(birthDate!=null)
	{	
		var splitDate=birthDate.split("-");				
		var bday=parseInt(splitDate[1]);
		var bmo=(parseInt(splitDate[2])-1);				
		var byr=parseInt(splitDate[0]);				
		var byr;
		var age;								
		var now = new Date();
		tday=now.getUTCDate();
		tmo=(now.getUTCMonth());
		tyr=(now.getUTCFullYear());	
		if((tmo > bmo)||(tmo==bmo & tday>=bday)) {age=byr}				
		else  {age=byr+1}

		if(isNaN(tyr-age)==true){}
		else if((tyr-age)>150 || (tyr-age)<=-1 || bmo>12)			
		{				
			document.frmNominee.nomi_DOB.value='';
			alert(nomineeDtlsAlertMsgArr[3]);
		}
		else{return (tyr-age);}
	}
	else{document.getElementById('nomi_personAge').value='';}				
}

function Age1(birthDate)
{					
	if(birthDate!=null)
	{	
		v=Age(birthDate);	
		if(isNaN(v)==false){document.getElementById('nomi_personAge').value=v;}
	}
	else{document.getElementById('nomi_personAge').value='';}
}*/

function AddMemeber(addMe)
{			
	var addFlag= false;
	var x1;		
	for (x1 in familyMemeberArr)
	{
		var map = familyMemeberArr[x1].toString();													
		var v1 = map.split("/");								
		if(v1[0] == addMe)
		{
			addFlag=true;
		}
	}			
	if(addFlag==false)
	{
		familyMemeberArr[familyMemeberCount]=addMe;
		familyMemeberCount=parseInt(familyMemeberCount)+1;
	}	

}

/*function set_Age(birthDate)
{
		var splitDate=birthDate.split("/");				
		var byr=splitDate[2];
		var bmo=splitDate[1];
		var bday=splitDate[0];				
		v= Age(byr+'-'+bday+'-'+bmo);
		return v;
}*/

function showOtherText(RelationCombo,flag)
{			
	var id = RelationCombo.value;
	if(id == '')
	{
		return;
	}				
	if(id == 'fm_rel_Other')
	{
		if(flag==1){document.getElementById('other_nomi_relation').style.display='';}
		if(flag==2)
		{
			document.getElementById('other_gur_relation').style.display='';
		}
	}
	else
	{
		if(flag==1){document.getElementById('other_nomi_relation').style.display='none';}
		if(flag==2){document.getElementById('other_gur_relation').style.display='none';	}				
	}				
}	

function minorOrNot()
{
	var v1=document.forms[0].nomi_DOB.value;
	if(v1!='' && v1!=null)
	{								
		v1=countAge(v1,"nomi_personAge","value");
		if(isNaN(v1)==false)
		{
			if(v1<18)
			{
				document.getElementById("guardianTable").style.display='';minorFlag=true;
				getFieldGroupObj(document.getElementById("nomineeGuardianAddressTable"));
			}
			else{document.getElementById("guardianTable").style.display='none';minorFlag=false;}
		}
	}
}	

/*function makeReadOnly()
{
	document.forms[0].nomi_DOB.readOnly=true;			
}*/		

function ShowMe(flag)
{
	document.getElementById('nomineeAddressTable').style.display='';			
	document.getElementById("guardianTable").style.display='none';	
	if(flag==1 && disableOtherNomineeFlag==true)
	{	
		document.forms[0].nomi_otherRelation.style.backgroundColour ='';
		document.getElementById('nomn_name').style.backgroundColor ='';
		document.forms[0].nomi_otherRelation.style.colour ='';				
		document.getElementById('nomn_name').style.color ='';
		document.forms[0].nomn_name.readOnly = false; 
		document.forms[0].nomi_otherRelation.readOnly  = false; 
		document.forms[0].nomi_DOB.disabled  = false; 
		document.forms[0].nomi_personAge.readOnly  = true; 
		document.getElementById('fmnameTab').style.display ='none';
		document.getElementById('fmNameTabCmb').style.display ='none';   				
		RelationCombo=document.getElementById('nomi_Relation');

		/*for(var i=0; i<RelationCombo.options.length;i++)
		{
		if(RelationCombo[i].value=='fm_rel_Other')
		{
			RelationCombo.options[i].selected=true;
		}										
		}*/
		document.getElementById('nomi_Relation').value='fm_rel_Other';
		makeEnableDisable("NomineeAddress",1);
		document.getElementById('other_nomi_relation').style.display='';
		familyMemeber=0;	
		familyMemeberId=0;		
		//makeReadOnly();
	}
	if(flag==2)
	{			
		document.forms[0].nomi_otherRelation.style.backgroundColour ='lightblue';								
		document.getElementById('nomn_name').style.backgroundColor ='lightblue';				
		document.forms[0].nomi_otherRelation.style.colour ='white';				
		document.getElementById('nomn_name').style.color ='black';
		document.getElementById('nomn_name').style.font.bold=true;

		document.getElementById('nomineeAddressTable').style.display='';   				
		document.getElementById('other_nomi_relation').style.display='none';
		RelationCombo=document.getElementById('nomi_Relation');				
		/*for(var i=0; i<RelationCombo.options.length;i++)
		  {
			if(RelationCombo.value=='Select')
			{
				RelationCombo.options[i].selected=true;
			}										
		}*/
		document.getElementById('nomi_Relation').value='Select';				
		makeEnableDisable("NomineeAddress",0);				
		document.forms[0].nomn_name.readOnly  = true;  
		document.forms[0].nomi_otherRelation.readOnly  = true; 
		document.forms[0].nomi_DOB.disabled  = true; 
		document.forms[0].nomi_personAge.readOnly  = true; 
		document.getElementById('fmnameTab').style.display='';
		document.getElementById('fmNameTabCmb').style.display='';	
	}						
	reset();			
}

function reset()
{			
	document.getElementById("gur_Relation").value='Select';  
	document.getElementById("firstNameCmb").value='Select';			
	document.forms[0].nomn_name.value='';
	document.forms[0].nomi_otherRelation.value='';
	document.forms[0].nomi_DOB.value='';
	document.forms[0].nomi_personAge.value=''; 
	document.forms[0].gur_first_name.value='';
	document.forms[0].gur_middle_name.value='';
	document.forms[0].gur_last_name.value='';
	document.forms[0].nomi_share.value='';
	document.forms[0].gur_otherRelation.value='';
	minorFlag=false;
	globalFlagDeleteDisable = false;
	document.frmNominee.Save_nominee.disabled=true;
	document.getElementById("guardianTable").style.display='none';
	resetAddress('GuardianAddress');
	resetAddress('NomineeAddress');
	closeAddress('NomineeAddress'); 
	closeAddress('GuardianAddress'); 
	document.frmNominee.Add_nominee.disabled=false;
	
	document.getElementById('errorBox').value='';		
	document.getElementById('errorMessage').style.display='none';	
}

function resetAllFields()
{
	resetAllFlag();				
	document.getElementById("gur_Relation").value='Select';  
	document.getElementById("nomi_Relation").value='Select';
	document.getElementById("firstNameCmb").value='Select';			
	document.forms[0].nomn_name.value='';
	document.forms[0].nomi_otherRelation.value='';
	document.forms[0].nomi_DOB.value='';
	document.forms[0].nomi_personAge.value=''; 
	document.forms[0].gur_first_name.value='';
	document.forms[0].gur_middle_name.value='';
	document.forms[0].gur_last_name.value='';
	document.forms[0].nomi_share.value='';
	document.forms[0].gur_otherRelation.value='';
	minorFlag=false;
	globalFlagDeleteDisable = false;
	document.frmNominee.Save_nominee.disabled=true;
	document.getElementById("guardianTable").style.display='none';
	resetAddress('GuardianAddress');
	resetAddress('NomineeAddress');
	closeAddress('NomineeAddress'); 
	closeAddress('GuardianAddress'); 
	document.frmNominee.Add_nominee.disabled=false;
	document.getElementById('errorBox').value='';		
	document.getElementById('errorMessage').style.display='none';
}

function showDtlsForRelation(relationCmb)
{
	try
	{   
		xmlHttp=new XMLHttpRequest();	    	    
	}
	catch (e)
	{    // Internet Explorer    
		try{
			xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");	      					   
		}
		catch (e){
			try
			{
				xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");   	                	             
			}
			catch (e)
			{
				alert(nomineeDtlsAlertMsgArr[4]);       
				return false;        
			}
		}
	}

	if(relationCmb.value!=null && relationCmb.value!='Select')
	{
		showProgressbar();
		var url = "hdiits.htm?actionFlag=getFamilyMemberDtls&name="+relationCmb.value+"&userId="+document.getElementById("userId").value+"&workFlowEnabled="+ workFlowEnabled;   //IFMS  
		xmlHttp.open("POST", encodeURI(url) , true);			
		xmlHttp.onreadystatechange = showFamilyMemberInfo;
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));
	}
	else
	{
		resetOnFamilySelect();
	}
}

function showFamilyMemberInfo()
{
	if (xmlHttp.readyState == 4) 
	{     
		if (xmlHttp.status == 200) 
		{
			var xmlStr = xmlHttp.responseText;

			var XMLDoc=getDOMFromXML(xmlStr); 			    	
			xmlFileName1=XMLDoc.getElementsByTagName('Address');
			xmlFileName=xmlFileName1[0].childNodes[0].text;			    						
			try
			{   
				xmlHttp=new XMLHttpRequest();	    	    
			}
			catch (e)
			{    // Internet Explorer    
				try{
					xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");	      					   
				}
				catch (e){
					try
					{
						xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");   	                	             
					}
					catch (e)
					{
						alert(nomineeDtlsAlertMsgArr[4]);       
						return false;        
					}
				}			 
			}	
			var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + xmlFileName;   
			xmlHttp.open("POST", encodeURI(url) , true);			
			xmlHttp.onreadystatechange = showFamily;
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));
		}
	}
}	

function showFamily()			    				    	
{
	if (xmlHttp.readyState == 4) 
	{     		
		if (xmlHttp.status == 200) 
		{						
			var xmlStr = xmlHttp.responseText;
			var XMLDoc=getDOMFromXML(xmlStr);
			document.getElementById("nomi_share").value='';
			var relation = getXPathValueFromDOM(XMLDoc,'fmFirstName'); 
			document.getElementById("nomn_name").value=relation;
			familyMemeberId=getXPathValueFromDOM(XMLDoc,'memberId'); 			    	
			var relation = getXPathValueFromDOM(XMLDoc,'cmnLookupMstByFmRelation/lookupName'); 		
			RelationCombo=document.getElementById("nomi_Relation");
			for(var i=0; i<RelationCombo.options.length;i++)
			{
				if(RelationCombo.options[i].value==relation)
				{
					RelationCombo.options[i].selected=true;
				}										
			}			    			    		     		     										    	
			RelationCombo=document.getElementById("nomi_Relation");
			var id = RelationCombo.value;			    	
			if(id=='fm_rel_Other'){document.getElementById("other_nomi_relation").style.display='';}
			else{document.getElementById("other_nomi_relation").style.display='none';}			    				    	
			var str = getXPathValueFromDOM(XMLDoc,'fmDateOfBirth'); 
			if(str.indexOf("-")!=-1)
			{		
				str=str.substring(0,10);		
				var splitDate=str.split("-");				
				var byr=splitDate[0];
				var bmo=splitDate[1];
				var bday=splitDate[2];				
				str= bday+'/'+bmo+'/'+byr;
			}				    	
			document.forms[0].nomi_DOB.value=str;
			minorOrNot();
			var other_relation = getXPathValueFromDOM(XMLDoc,'fmRelationOther'); 	
			var v=other_relation;
			if(v==null || v=='' || v=='null')
			{
				document.getElementById("other_nomi_relation").style.display='none';
			}
			else{document.getElementById("nomi_otherRelation").value=v;document.getElementById("other_nomi_relation").style.display='';}	

			document.frmNominee.Save_nominee.disabled=true;
			document.forms[0].nomi_Relation.readOnly=false;
			
			getFieldGroupObj(document.getElementById("countryStateTableNomineeAddress"));//For Address
			makeEnableDisable("NomineeAddress",1);
			var addrXPath = 'cmnAddressMst';
			editAddress('NomineeAddress',XMLDoc,addrXPath);
			makeEnableDisable("NomineeAddress",0);		    					    			    						    	
		}							
	}
	hideProgressbar();
}

function AddNominee()
{

	if(document.frmNominee.nomiType[1].status==true && document.frmNominee.firstNameCmb.value=='Select')
	{
		alert(nomineeDtlsAlertMsgArr[5]);
		return;
	}		
	if(document.frmNominee.nomiType[0].status==false && document.frmNominee.nomiType[1].status==false)
	{
		alert(nomineeDtlsAlertMsgArr[6]);
	}
	else			
	{	
		familyMemeber=familyMemeberId;
		var chkalreadyAddorNotFlag=false;
		var x1,memberFlag=false;		
		for (x1 in familyMemeberArr)
		{
			var map = familyMemeberArr[x1].toString();													
			var v1 = map.split("/");								
			if(v1[0] == familyMemeber)
			{
				chkalreadyAddorNotFlag=true;				
			}
		}
		if(chkalreadyAddorNotFlag==false)
		{
			document.frmNominee.btnSubmit.disabled=false;	
			if(workFlowEnabled)document.frmNominee.btnSave_draft.disabled=false;//IFMS
			var addrName1 ='GuardianAddress';			
			var addrLookUpName1= 'Permanent Address';			
			var addressArray1 = addressParameters(addrName1, addrLookUpName1);
			var addrName ='NomineeAddress';			
			var draftFlag=0;
			var addrLookUpName= 'Permanent Address';			
			var addressArray = addressParameters(addrName, addrLookUpName);	
			addressArray=addressArray.concat(addressArray1);	
			RelationCombo=document.getElementById('nomi_Relation');			
			document.getElementById("nomi_Relation_id").value=RelationCombo.value;				
			var totalArray=new Array('nomn_name','nomi_share','nomi_Relation_id','nomi_otherRelation','nomi_DOB','gur_first_name','gur_middle_name','gur_last_name','gur_Relation','gur_otherRelation','benefitType');
			var returnValue= validateNominationForm();

			if(!workFlowEnabled && returnValue!='error')
			{
				var addrFlag=true;
				if(isAddressClosed(addrName)== false && document.getElementById('nomi_Relation').value=='fm_rel_Other')				
					addrFlag =  validateSpecificFormFields(addressArray);		

				if(addrFlag && isAddressClosed(addrName1)== false)
					addrFlag =  validateSpecificFormFields(addressArray1);	
				if(!addrFlag)		
					returnValue='error';
			}

			if(returnValue=='error'){}
			else 
			{
				var minorValue=0;
				if(document.getElementById("nomi_personAge").value < 18 && document.getElementById("nomi_personAge").value != ""){minorValue=1;}
				else {minorValue=0;}             // 0 = Not minor and 1 = minor person																	
				{
					draftFlag==0;                  // OK Record
					totalArray = totalArray.concat(addressArray);		
					addOrUpdate=0;	
					addOrUpdateRecordForNominee('addRecord','addOrEditNomineeVOGEN&workFlowEnabled='+ workFlowEnabled +'&minor='+minorValue+'&parentId='+0+'&Family='+familyMemeber,totalArray); //IFMS		
				}
			}
		}
		else
		{
			alert(nomineeDtlsAlertMsgArr[7]);
		}				
	}					
}

function SaveNominee()
{	

	if(document.frmNominee.nomiType[1].status==true && document.frmNominee.firstNameCmb.value=='Select')
	{
		alert(nomineeDtlsAlertMsgArr[5]);
		return;
	}		
	if(document.frmNominee.nomiType[0].status==false && document.frmNominee.nomiType[1].status==false)
	{
		alert(nomineeDtlsAlertMsgArr[6]);
	}
	else
	{	
		var addrName1 ='GuardianAddress';			
		var addrLookUpName1= 'Permanent Address';			
		var addressArray1 = addressParameters(addrName1, addrLookUpName1);
		var addrName ='NomineeAddress';			
		var draftFlag=0;
		var addrLookUpName= 'Permanent Address';			
		var addressArray = addressParameters(addrName, addrLookUpName);	
		RelationCombo=document.getElementById('nomi_Relation');			
		document.getElementById("nomi_Relation_id").value=RelationCombo.options[RelationCombo.selectedIndex].value;					
		var nomineeArray =new Array('nomn_name','nomi_share','nomi_Relation_id','nomi_otherRelation','nomi_DOB','gur_first_name','gur_middle_name','gur_last_name','gur_Relation','gur_otherRelation','benefitType');
		var returnValue= validateNominationForm();	

		if(!workFlowEnabled && returnValue!='error')
		{
			var addrFlag=true;
			if(isAddressClosed(addrName)== false && document.getElementById('nomi_Relation').value=='fm_rel_Other')				
				addrFlag =  validateSpecificFormFields(addressArray);		

			if(addrFlag && isAddressClosed(addrName1)== false)
				addrFlag =  validateSpecificFormFields(addressArray1);	
			if(!addrFlag)		
				returnValue='error';
		}

		var minorValue=0;			
		if(document.getElementById("nomi_personAge").value < 18 && document.getElementById("nomi_personAge").value != ""){minorValue=1;}
		else {minorValue=0;}             // 0 = Not minor and 1 = minor person							  			  	
		if(returnValue==true)
		{
			if(draftArrCount != -1)
			{						  		
				var x;
				for (x in draftArr)
				{																	
					if(draftArr[x] == editRowId)
					{																		 
						draftArr.splice(x,1);							
						if(draftArrCount==0){draftArrCount=-1;}
						else {draftArrCount = draftArrCount-1;}
					}
				}										  		
			}					
			var num=document.getElementById('srNo').value;

			totalArray = nomineeArray.concat(addressArray);	
			totalArray = totalArray.concat(addressArray1);
			addOrUpdateRecordForNominee('updateRecord','addOrEditNomineeVOGEN&workFlowEnabled='+ workFlowEnabled +'&minor='+minorValue+'&parentId='+num+'&Family='+familyMemeber,totalArray);//IFMS	
		}
		else if(returnValue=='error'){}
	}
}

function addRecord() 
{   		  	    
	if (xmlHttp.readyState == 4)
	{		  		
		if(xmlHttp.status == 200)
		{	
			updateRow = null;			  				  									  					  		
			document.getElementById('nomineeAddDataTable').style.display='';
			count=parseInt(count)+1;			  		
			if(document.getElementById("nomi_personAge").value < 18)
			{
				document.getElementById('minorHidden').value=nomineeDtlsAlertMsgArr[33];
			}
			else 
			{
				document.getElementById('minorHidden').value=nomineeDtlsAlertMsgArr[34];
			}			  		
			var displayFieldArray = new Array('nomn_name','nomi_Relation','nomi_DOB','nomi_personAge','nomi_share');
			eprofileMyCmbFieldValue(displayFieldArray);
			var returnRowId=addDataInTable('nomineeAddDataTable', 'encXML', displayFieldArrayInTable,'editRecord','deleteEmpNomineeRecord','',count);
			/**Adding a value into the Family Memeber Arr*/
			if(familyMemeber!=0)
			{
				familyMemeberArr[familyMemeberCount]=familyMemeber+"/"+returnRowId;
				familyMemeberCount=parseInt(familyMemeberCount)+1;
			}										
			/*Adding a value into the Family Memeber Arr*/
			RelationCombo=document.getElementById('nomi_Relation');
			if(document.frmNominee.nomiType[1].status==true){
				RelationCombo=document.getElementById('nomi_Relation');
				for(var i=0; i<RelationCombo.options.length;i++)
				{
					if(RelationCombo.value=='Select')
					{
						RelationCombo.options[i].selected=true;
					}										
				}
			}
			else
			{
				RelationCombo=document.getElementById('nomi_Relation');
				for(var i=0; i<RelationCombo.options.length;i++)
				{
					if(RelationCombo[i].value=='fm_rel_Other')
					{
						RelationCombo.options[i].selected=true;
					}										
				}
			} 
			document.getElementById("nomi_Relation").readOnly=false;
			showCurrentFieldGroupTab();
			reset();
		}
	}			   	
}

function updateRecord()
{
	if (xmlHttp.readyState == 4)
	{		  		
		if(xmlHttp.status == 200)
		{		  			
			var displayFieldArray = new Array('nomn_name','nomi_Relation','nomi_DOB','nomi_personAge','minorHidden','nomi_share');			
			if(dbRecord==true && addMe==true)			
			{
				updateRow = null;
				document.getElementById('nomineeAddDataTable').style.display='';
				count=parseInt(count)+1;
				if(document.getElementById("nomi_personAge").value < 18)
				{
					document.getElementById('minorHidden').value=nomineeDtlsAlertMsgArr[33];
				}
				else 
				{
					document.getElementById('minorHidden').value=nomineeDtlsAlertMsgArr[34];
				}			  		
				var displayFieldArray = new Array('nomn_name','nomi_Relation','nomi_DOB','nomi_personAge','nomi_share');
				eprofileMyCmbFieldValue(displayFieldArray);
				var returnRowId=addDataInTable('nomineeAddDataTable', 'encXML', displayFieldArrayInTable,'editRecord','deleteEmpNomineeRecord','',count);					
				trow=document.getElementById(editRowId);
				try{												
					trow.childNodes[8].style.display='none';
				}catch(e){trow.childNodes[7].style.display='none';}
				/*For Mapping Purpose Between */
				var map1 =editRowId;
				var map2 ='/'+returnRowId;				
				var map = map1.concat(map2);
				mappingArr[mappingArrCount]=map;
				mappingArrCount= mappingArrCount+1;
				/*End of Mapping a Code*/
				/*Family member*/
				if(familyMemeber!=0 && isNaN(familyMemeber)==false)
				{
					familyMemeberArr[familyMemeberCount]=familyMemeber+"/"+returnRowId;
					familyMemeberCount=parseInt(familyMemeberCount)+1;
				}
				resetAllFlag();
				/*End of Family Memeber*/
			}
			else
			{
				if(Number(document.getElementById("nomi_personAge").value) < 18)
				{
					document.getElementById('minorHidden').value=nomineeDtlsAlertMsgArr[33];
				}
				else 
				{
					document.getElementById('minorHidden').value=nomineeDtlsAlertMsgArr[34];
				}
				updateDataInTable('encXML', displayFieldArray);	
			}			
			RelationCombo=document.getElementById('nomi_Relation');
			if(document.frmNominee.nomiType[1].status==true)
			{
				RelationCombo=document.getElementById('nomi_Relation');
				for(var i=0; i<RelationCombo.options.length;i++)
				{
					if(RelationCombo.value=='Select')
					{
						RelationCombo.options[i].selected=true;
					}										
				}						
			}
			else
			{
				RelationCombo=document.getElementById('nomi_Relation');
				for(var i=0; i<RelationCombo.options.length;i++)
				{
					if(RelationCombo.value=='Select')
					{
						RelationCombo.options[i].selected=true;
					}										
				}
			}
			showCurrentFieldGroupTab();
			reset();
			globalFlagDeleteDisable = false;
			document.frmNominee.btnSubmit.disabled=false;	
			if(workFlowEnabled)document.frmNominee.btnSave_draft.disabled=false;//IFMS
			document.frmNominee.Save_nominee.disabled=true;	
			document.frmNominee.Add_nominee.disabled=false;	
		}
	}								
}

function editRecord(rowId) 
{	
	updateRow = null;	
	familyMemeber=0;
	familyMemeber=familyMemeberId;
	familyMemeberId=0;	
	var saveToAddMissingData='',hField='';	
	try 
	{				
		hField = rowId.substring(3, rowId.length);
		var xmlFileName = document.getElementById(hField).value;
		saveToAddMissingData=xmlFileName;
		rowIdArr=xmlFileName.split("$/$");
		document.getElementById(hField).value=rowIdArr[0];								

	}catch(e){}		
	editRowId=rowId;

	if(familyMemeber!=0 && isNaN(familyMemeber)==false)
	{	
		var addFamilyMemberFlag=false;
		try {

			for (x1 in familyMemeberArr)
			{
				var map = familyMemeberArr[x1].toString();
				var v1 = map.split("/");								
				if(v1[1] == rowId)
				{
					addFamilyMemberFlag=true;									
				}				
			}
		}catch(e){}
	}

	var x1,memberFlag=false;
	for (x1 in familyMemeberArr)
	{
		var map = familyMemeberArr[x1].toString();

		var v1 = map.split("/");	

		if(v1[1] == rowId)
		{
			familyMemeber=v1[0];					
			memberFlag=true;									
		}
	}

	if(memberFlag==true)
	{				
		document.frmNominee.nomiType[1].status=true;	
		ShowMe(2);
		document.frmNominee.Add_nominee.disabled=true;
	}
	else
	{
		document.frmNominee.nomiType[0].status=true;
		ShowMe(1);				
	}	

	document.frmNominee.Save_nominee.disabled=false;
	var x,flag=0;
	for(x in dbRowsId)
	{
		var ojbOfdbRowsId = dbRowsId[x].toString();				
		if(rowId==ojbOfdbRowsId)
		{					
			flag=1;
		}		
	}
	if(flag==1){dbRecord=true;addMe=true;}			
	else{dbRecord=false;addMe=false;}
	if(dbRecord==false)
	{
		var x1;
		for (x1 in mappingArr)
		{
			var map =mappingArr[x1].toString();													
			var v1=map.split("/");				
			if(v1[1] == rowId)
			{
				dbRecord=true;
				editRowId=v1[0];
				addMe=false;					
			}
		}
	}

	sendAjaxRequestForEdit(rowId, 'populateForm');				

	if(saveToAddMissingData!='' && hField!='')
	{
		document.getElementById(hField).value=saveToAddMissingData;							
	}
	document.frmNominee.Add_nominee.disabled=true; 
	document.frmNominee.Save_nominee.disabled=false;
	globalFlagDeleteDisable = true;				
}

function populateForm()
{
	if (xmlHttp.readyState == 4) 
	{	
		if(xmlHttp.status == 200)
		{		  		 				  
			var decXML = xmlHttp.responseText;
			var xmlDOM = getDOMFromXML(decXML);																				
			var str;							
			try
			{
				familyMemeberId=getXPathValueFromDOM(xmlDOM,'memberId');  
				document.getElementById('srNo').value=getXPathValueFromDOM(xmlDOM,'memberId');
				if(document.getElementById('srNo').value=='null' || document.getElementById('srNo').value==null || document.getElementById('srNo').value=='')
				{
					var v=getXPathValueFromDOM(xmlDOM, 'rowNumber');
					if(v=='null' || v==null || v=='')  {v=0;}
					document.getElementById('srNo').value=v;
				}						
			}catch(e){try{document.getElementById('srNo').value=getXPathValueFromDOM(xmlDOM, 'rowNumber');}catch(e){document.getElementById('srNo').value=0;}}					
			
			try {
				str=getXPathValueFromDOM(xmlDOM, 'nomnName');

				if(str==null || str==''){document.forms[0].nomn_name.value='';}
				else
				{
					document.forms[0].nomn_name.value=str;												
					document.forms[0].firstNameCmb.value=str;

				}
			}catch(e){}

			try 
			{
				str=getXPathValueFromDOM(xmlDOM, 'nomnOtherRelation');
				if(str==null  || str==''){str='';}
				document.forms[0].nomi_otherRelation.value=str;
			}catch(e){}

			str=getXPathValueFromDOM(xmlDOM,'nomnSharePercent');
			if(str==null  || str==''){str=0;}
			document.forms[0].nomi_share.value=str;

			try 
			{
				str=getXPathValueFromDOM(xmlDOM,'nomnDob');					
				if(str==null  || str==''){str='';}
				else{					
					if(str.indexOf("-")!=-1)
					{		
						str=str.substring(0,10);		
						var splitDate=str.split("-");				
						var byr=splitDate[0];
						var bmo=splitDate[1];
						var bday=splitDate[2];				
						str= bday+'/'+bmo+'/'+byr;
					}
				}
				document.forms[0].nomi_DOB.value=str;
				minorOrNot();
			}catch(e){}
			
			str=getXPathValueFromDOM(xmlDOM, 'guardianFirstname');
			if(str==null  || str==''){str='';}
			document.forms[0].gur_first_name.value=str;

			str=getXPathValueFromDOM(xmlDOM, 'guardianLastname');
			if(str==null  || str==''){str='';}
			document.forms[0].gur_last_name.value=str;					

			str=getXPathValueFromDOM(xmlDOM, 'guardianMiddlename');
			if(str==null  || str==''){str='';}
			document.forms[0].gur_middle_name.value=str;

			str=getXPathValueFromDOM(xmlDOM, 'guardianRelationOther');
			if(str==null  || str==''){str='';}
			document.forms[0].gur_otherRelation.value=str;

			str=getXPathValueFromDOM(xmlDOM, 'cmnLookupMstByNomnRelation/lookupName');	
			if(str==-1){str='Select';}							
			RelationCombo=document.getElementById('nomi_Relation');
			for(var i=0; i<RelationCombo.options.length;i++)
			{
				if(RelationCombo.options[i].value==str)
				{
					RelationCombo.options[i].selected=true;
				}										
			}			

			str=getXPathValueFromDOM(xmlDOM, 'cmnLookupMstByGuardianRelation/lookupName');//Address
			if(str==-1){str='Select';}	
			document.getElementById("gur_Relation").value=str;													

			getFieldGroupObj(document.getElementById("countryStateTableNomineeAddress"));
			makeEnableDisable("NomineeAddress",1);
			var addrXPath = 'cmnAddressMstByNomnAddress';
			editAddress('NomineeAddress',xmlDOM,addrXPath);			    		
			if(document.frmNominee.nomiType[1].status==true){makeEnableDisable("NomineeAddress",0);}

			getFieldGroupObj(document.getElementById("countryStateTableGuardianAddress"));//Address
			var addrXPath = 'cmnAddressMstByGuardianAddress';
			editAddress('GuardianAddress',xmlDOM,addrXPath);					

			RelationCombo=document.getElementById('nomi_Relation');
			var id = RelationCombo.value;
			if(id=='fm_rel_Other'){document.getElementById("other_nomi_relation").style.display='';}
			else{document.getElementById('other_nomi_relation').style.display='none';}

			try 
			{					
				RelationCombo=document.getElementById('gur_Relation');					
				var id = RelationCombo.value;				    											
				if(id=='fm_rel_Other'){document.getElementById("other_gur_relation").style.display='';}						
				else{document.getElementById('other_gur_relation').style.display='none';}	
			}catch(e){}			
		}
	}	
}

function deleteEmpNomineeRecord(rowId,flag) 
{														
	if(globalFlagDeleteDisable == false)
	{
		var doNotAddNewMember=false;
		if(flag==1)
		{			
			doNotAddNewMember=true;
			document.frmNominee.btnSubmit.disabled=false;	
			document.frmNominee.btnSave_draft.disabled=false;

			document.getElementById("nomineeAddDataTable").style.display='';
			var i=0;								
			var trow=document.getElementById(rowId);							
			var id = trow.id;																							
			document.getElementById("nomineeApproveDataTable").style.display='';
			var trow1=document.getElementById("nomineeAddDataTable").insertRow();
			trow1.id="deleteAppArrCount"+id;
			deleteAppArr[deleteAppArrCount]=id;
			deleteAppArrCount=deleteAppArrCount+1;

			trow1.insertCell(0).innerHTML = "<INPUT type='hidden' name='aERB' id='aERB' value=''/>";				
			trow1.cells[0].style.display = 'none';
			trow1.insertCell(i+1).innerHTML="<font color='blue'>"+trow.cells[i+1].innerText+"</font>";
			trow1.insertCell(i+2).innerHTML="<font color='blue'>"+trow.cells[i+2].innerText+"</font>";
			trow1.insertCell(i+3).innerHTML="<font color='blue'>"+trow.cells[i+3].innerText+"</font>";
			trow1.insertCell(i+4).innerHTML="<font color='blue'>"+trow.cells[i+4].innerText+"</font>";
			trow1.insertCell(i+5).innerHTML="<font color='blue'>"+trow.cells[i+5].innerText+"</font>";
			trow1.insertCell(i+6).innerHTML="<font color='blue'>"+trow.cells[i+6].innerText+"</font>";
			trow1.insertCell(i+7).innerHTML="<a href=javascript:void('Reload') onclick=javascript:Reload('"+id+"')>Restore</a>";
			document.getElementById(rowId).style.display='none';

			var flag=1,srno=1;	
			var num = document.getElementById('nomineeApproveDataTable').rows.length;								
			for(var i=1;i<num;i++)
			{
				trow= document.getElementById('nomineeApproveDataTable').rows[i];																																		
				try{															
					if(trow.style.display=='')
					{								
						flag=0;
					}
				}catch(e){break;}							
			}
			if(flag==1){document.getElementById("nomineeApproveDataTable").style.display='none';}
		}
		else 
		{									
			answer = deleteRecord(rowId);		
			if(answer==true)
			{
				if(document.getElementById("nomineeAddDataTable").rows.length==1)
				{
					document.getElementById("nomineeAddDataTable").style.display='none';
				}						

				globalFlagDeleteDisable = false;	
				/***Mapping Array Code for **/	

				var x1;
				var dbFalg=0;
				for (x1 in mappingArr)
				{
					var map =mappingArr[x1].toString();									
					var v1=map.split("/");
					if(v1[1] == rowId)
					{								
						var x2,showFlag=0,showme;
						var x ;
						for(x in dbRowsSrNo)
						{
							var getSrNo = dbRowsSrNo[x].toString();		
							var srNoObj=getSrNo.split('/');										
							if(v1[0] == srNoObj[0])
							{
								trow= document.getElementById(srNoObj[1]);
								try {						
									trow.childNodes[8].style.display='';
								}catch(e){trow.childNodes[7].style.display='';}
								mappingArr.splice(x1,1);			
								dbFalg=1;																
							}
						}
						if(dbFalg==0)
						{
							trow= document.getElementById(v1[0]);																								
							try {						
								trow.childNodes[8].style.display='';
							}catch(e){trow.childNodes[7].style.display='';}
							mappingArr.splice(x1,1);
						}								
					}
				}																							
				if(draftArrCount != -1)
				{						  		
					var x;
					for (x in draftArr)
					{																	
						if(draftArr[x] == rowId)
						{										 
							draftArr.splice(x,1);							
							if(draftArrCount==0){draftArrCount=-1;}
							else {draftArrCount = draftArrCount-1;}
						}
					}										  		
				}	
				if(doNotAddNewMember==false)
				{
					var x1;							  	
					for (x1 in familyMemeberArr)
					{
						var map =familyMemeberArr[x1].toString();
						var v1=map.split("/");													
						if(v1[1] == rowId)
						{	
							familyMemeberArr.splice(x1,1);															
							var x2;						
							for (x2 in familyMemeberArr)
							{
								var map1 =familyMemeberArr[x2].toString();
								if(v1[0]==map1)
								{
									familyMemeberArr.splice(x2,1);
								}
							}								
						}
					}						
				}
			}
		}
	}			
	else
	{
		alert(nomineeDtlsAlertMsgArr[8]);
	}				
}

function deleteNomineeDBRecord(id)
{
	document.frmNominee.btnSubmit.disabled=false;	
	if(workFlowEnabled)
	{ 
		document.frmNominee.btnSave_draft.disabled=false;
		var i=1;		
		trow= document.getElementById(id);								
		deleteAppArr[deleteAppArrCount]=id;
		deleteAppArrCount=deleteAppArrCount+1;				
		document.getElementById("nomineeAddDataTable").style.display='';
		var trow1=document.getElementById("nomineeAddDataTable").insertRow();
		trow1.id="deleteAppArrCount"+id;
		trow1.insertCell(0).innerHTML = "<INPUT type='hidden' name='aERB' id='aERB' value=''/>";				
		trow1.cells[0].style.display = 'none';
		trow1.insertCell(i).innerHTML="<font color='blue'>"+trow.cells[i].innerText+"</font>";
		trow1.insertCell(i+1).innerHTML="<font color='blue'>"+trow.cells[i+1].innerText+"</font>";
		trow1.insertCell(i+2).innerHTML="<font color='blue'>"+trow.cells[i+2].innerText+"</font>";
		trow1.insertCell(i+3).innerHTML="<font color='blue'>"+trow.cells[i+3].innerText+"</font>";
		trow1.insertCell(i+4).innerHTML="<font color='blue'>"+trow.cells[i+4].innerText+"</font>";
		trow1.insertCell(i+5).innerHTML="<font color='blue'>"+trow.cells[i+5].innerText+"</font>";
		trow1.insertCell(i+6).innerHTML="<a href=javascript:void('Reload') onclick=javascript:Reload('"+id+"')>Delete</a>";
		trow.cells[7].style.display = 'none';
	}
	else{  //IFMS

		var flag = deleteDBRecord(id);	

		if(flag)
		{					
			trow=document.getElementById(id);
			trow.cells[6].innerText='0';
		}
	}
}

function Reload(id)
{									
	try 
	{
		var delRow = document.getElementById("deleteAppArrCount"+id);	
		trow= document.getElementById(id);
		trow.childNodes[7].style.display='';		
		delRow.parentNode.deleteRow(delRow.rowIndex);										
		var x1;			
		for (x1 in deleteAppArr)
		{
			var map =deleteAppArr[x1].toString();										
			if(map == id)
			{				
				deleteAppArr.splice(x1,1);
				if(deleteAppArrCount==0){}
				else{deleteAppArrCount=deleteAppArrCount-1;}
			}
		}						
		/***Mapping Array Code for **/						
		var x1;		
		for (x1 in mappingArr)
		{
			var map =mappingArr[x1].toString();									
			var v1=map.split("/");	
			if(v1[1] == id)
			{
				trow= document.getElementById(id);									
				trow.style.display='';						
				try {						
					trow.childNodes[7].style.display='';
				}catch(e){trow.childNodes[7].style.display='';}
				mappingArr.splice(x1,1);
			}
		}
	}catch(e)
	{
		var delRow=document.getElementById(id);	
		delRow.parentNode.deleteRow(delRow.rowIndex);			
		var x1,checkMe=0;														
		for (x1 in mappingArr)
		{
			var map =mappingArr[x1].toString();									
			var v1=map.split("/");	
			if(v1[1] == id)
			{								
				var x2,showFlag=0,showme;
				var x 
				for(x in dbRowsSrNo)
				{
					var getSrNo = dbRowsSrNo[x].toString();		
					var srNoObj=getSrNo.split('/');										
					if(v1[0] == srNoObj[0])
					{
						trow= document.getElementById(srNoObj[1]);																								
						trow.childNodes[7].style.display='';
						mappingArr.splice(x1,1);																			
					}
				}
			}
		}
	}
	document.getElementById('nomineeApproveDataTable').style.display='';					
	if(document.getElementById('nomineeAddDataTable').rows.length==1) 
	{
		document.getElementById('nomineeAddDataTable').style.display='none';					
	}																					
	if(draftArrCount != -1)
	{						  		
		var x;
		for (x in draftArr)
		{																	
			if(draftArr[x] == id)
			{										 
				draftArr.splice(x,1);							
				if(draftArrCount==0){draftArrCount=-1;}
				else {draftArrCount = draftArrCount-1;}
			}
		}										  		
	}			
}

/************Pending Nominee Details*************/
function viewPendingRequestDtls(reqId)
{
	try{   
		xmlHttp=new XMLHttpRequest();	    	    
	}
	catch (e)
	{    // Internet Explorer    
		try{
			xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");	      					   
		}
		catch (e){
			try
			{
				xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");   	                	             
			}
			catch (e)
			{
				alert(nomineeDtlsAlertMsgArr[4]);       
				return false;        
			}
		}			 
	}	
	checkDraftOrPendingReq=false;
	var url = "hdiits.htm?actionFlag=getNomineePendingRecordForView&reqId="+reqId;    
	xmlHttp.open("POST", encodeURI(url) , true);			
	xmlHttp.onreadystatechange = pendingRequestResponse;
	xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
	xmlHttp.send(encodeURIComponent(null));
}
function pendingRequestResponse()
{
	if (xmlHttp.readyState == 4) 
	{     
		if (xmlHttp.status == 200) 
		{  							
			if(checkDraftOrPendingReq==false) {delete_pendingReqRows(1);document.getElementById('pendingDataTable').style.display='';}
			else if(checkDraftOrPendingReq==true)	{delete_pendingReqRows(2);document.getElementById('draftDataTable').style.display='';}																	

			var xmlStr = xmlHttp.responseText;
			var XMLDoc=getDOMFromXML(xmlStr);			    	
			var name=XMLDoc.getElementsByTagName('Name'); 
			var share=XMLDoc.getElementsByTagName('Share');
			var minor=XMLDoc.getElementsByTagName('Minor');
			var dob = XMLDoc.getElementsByTagName('DOB');
			var relation=XMLDoc.getElementsByTagName('Relation');
			var req=XMLDoc.getElementsByTagName('Request');			    			
			var j=0,k=1;
			for ( var i = 0 ; i < name.length ; i++ )
			{     	
				var name1=name[i].childNodes[0].text;	     					     					
				var share1=share[i].childNodes[0].text;
				var minor1=minor[i].childNodes[0].text;
				var dob1=dob[i].childNodes[0].text;     					
				var relation1=relation[i].childNodes[0].text;     					
				var req1=req[i].childNodes[0].text;
				if(minor1=='N'){minor1='No';}
				else if(minor1=='Y'){minor1='Yes';}
				else {minor1='-';}

				if(name1=='' || name1=='null'){name1='-';}
				if(share1=='' || share1=='null'){share1='-';} 					

				if(checkDraftOrPendingReq==false)
				{
					trow= document.getElementById('pendingDataTable').insertRow();	
					trow.id= 'pendingDtlsRows';					    		
				}
				else if(checkDraftOrPendingReq==true)
				{
					trow= document.getElementById('draftDataTable').insertRow();
					trow.id= 'draftDtlsRows';
				}				    							
				trow.insertCell(j+0).innerHTML=name1;
				trow.insertCell(j+1).innerHTML=relation1;
				if(dob1!='null')
				{
					trow.insertCell(j+2).innerHTML=dob1;	   					
					trow.insertCell(j+3).innerHTML=countAge(dob1);
				}
				else
				{
					trow.insertCell(j+2).innerHTML="-";	   					
					trow.insertCell(j+3).innerHTML="-";
				}
				trow.insertCell(j+4).innerHTML=minor1;
				trow.insertCell(j+5).innerHTML=share1;
				if(req1=='U') {trow.insertCell(j+6).innerHTML=nomineeDtlsAlertMsgArr[35];}
				else if(req1=='D') {trow.insertCell(j+6).innerHTML=nomineeDtlsAlertMsgArr[36];}
				else {trow.insertCell(j+6).innerHTML=nomineeDtlsAlertMsgArr[37];}	
				if(checkDraftOrPendingReq==false)
					trow.insertCell(j+7).innerHTML="<a href=javascript:void('Hide') onclick=javascript:eprofileHideObj('pendingDataTable')>"+nomineeDtlsAlertMsgArr[38]+"</a>"; 
				else
					trow.insertCell(j+7).innerHTML="<a href=javascript:void('Hide') onclick=javascript:eprofileHideObj('draftDataTable')>"+nomineeDtlsAlertMsgArr[38]+"</a>"; 
				j=0;
			}
		}
	}
}

/***delete previously opend pending rows ***/
function delete_pendingReqRows(flag)
{
	for(i=0;i<document.frmNominee.elements.length;i++)
	{   	            					
		if(document.getElementById('pendingDtlsRows') && flag==1)
		{
			var delRow = document.getElementById('pendingDtlsRows');						
			delRow.parentNode.deleteRow(delRow.rowIndex);													
		}
		if(document.getElementById('draftDtlsRows') && flag==2)
		{
			var delRow = document.getElementById('draftDtlsRows');						
			delRow.parentNode.deleteRow(delRow.rowIndex);
		}
	}
}
/****end of delete_pendingReqRows**/
/***********End of Nominee pending Script************/
/***   Draft Dtls Script***/
function viewDraftRequestDtls(reqId)
{
	try{   
		xmlHttp=new XMLHttpRequest();	    	    
	}
	catch (e)
	{    // Internet Explorer    
		try{
			xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");	      					   
		}
		catch (e){
			try
			{
				xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");   	                	             
			}
			catch (e)
			{
				alert(nomineeDtlsAlertMsgArr[4]);       
				return false;        
			}
		}			 
	}	
	checkDraftOrPendingReq=true;
	var url = "hdiits.htm?actionFlag=getNomineeDraftRecord&flag=view&reqId="+reqId;    
	xmlHttp.open("POST", encodeURI(url) , true);			
	xmlHttp.onreadystatechange = pendingRequestResponse;
	xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
	xmlHttp.send(encodeURIComponent(null));
}	
function deleteDraftRequestDtls(reqId)
{
	if(confirm('Are you Sure Do You want to Delete Request?')==true)
	{
		try{   
			xmlHttp=new XMLHttpRequest();	    	    
		}
		catch (e)
		{    // Internet Explorer    
			try{
				xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");	      					   
			}
			catch (e){
				try
				{
					xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");   	                	             
				}
				catch (e)
				{
					alert(nomineeDtlsAlertMsgArr[4]);       
					return false;        
				}
			}			 
		}				
		var url = "hdiits.htm?actionFlag=getNomineeDraftRecord&flag=delete&reqId="+reqId; 
		xmlHttp.open("POST", encodeURI(url) , true);			
		xmlHttp.onreadystatechange=deleteDraftresponse; 
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));
	}
}		

function deleteDraftresponse()
{
	if (xmlHttp.readyState == 4) 
	{     
		if (xmlHttp.status == 200) 
		{
			var xmlStr = xmlHttp.responseText;					    	
			var XMLDoc=getDOMFromXML(xmlStr);   // xmlHttp.responseXML.documentElement;						    			    					    					    							    	
			var SrNo = XMLDoc.getElementsByTagName('DeleteDraft');	
			var link = XMLDoc.getElementsByTagName('Link');				    	
			var fm= XMLDoc.getElementsByTagName('FamilyMem');
			for (var i = 0 ; i < link.length ; i++ )
			{					
				var showlink= link[i].childNodes[0].text;
				if(showlink!='null'  && showlink!='')
				{					
					var check = 'rowencXML'+showlink;
					var x1,deleteFlag=0;
					for (x1 in mappingArr)
					{
						var map =mappingArr[x1].toString();													
						var v1=map.split("/");				
						if(v1[0] == check)
						{
							deleteFlag=1;						
						}
					}
					if(deleteFlag==0)
					{
						for(x in dbRowsSrNo)
						{
							var getSrNo = dbRowsSrNo[x].toString();		
							var srNoObj=getSrNo.split('/');										
							if(showlink == srNoObj[0])
							{
								trow= document.getElementById(srNoObj[1]);																								
								try {						
									trow.childNodes[8].style.display='';
								}catch(e){trow.childNodes[7].style.display='';}	
							}
						}
					}							
				}
			}
			/*Nominee Member Arr*/     				
			for ( var i = 0 ; i < fm.length ; i++ )
			{			
				var showlink= fm[i].childNodes[0].text;
				var x1;								
				for (x1 in familyMemeberArr)
				{
					var map =familyMemeberArr[x1].toString();	
					var v1=map.split("/");																				
					if(v1[0] == showlink)
					{				
						familyMemeberArr.splice(x1,1);
					}
				}
			}
			/*Nominee Member Arr*/
			var deleteId= SrNo[0].childNodes[0].text;	
			var delRow = document.getElementById(deleteId);						
			delRow.parentNode.deleteRow(delRow.rowIndex);
			if(document.getElementById("RequestTable").rows.length==1)
			{
				document.getElementById("RequestTable").style.display='none';
			}
			else
			{
				var srno=1;	
				var num = document.getElementById('RequestTable').rows.length;								
				for(var i=1;i<num;i++)
				{
					trow= document.getElementById('RequestTable').rows[i];																																		
					try{
						if(trow.style.display=='')
						{
							trow.childNodes[0].innerHTML=srno;								
							srno=srno+1;
						}
					}catch(e){break;}							
				}
			}
			document.getElementById('draftDataTable').style.display='none';
		}
	}
}

function openDraftRequestDtls(reqId)
{
	openReqId = reqId;
	try{   
		xmlHttp=new XMLHttpRequest();	    	    
	}
	catch (e)
	{    // Internet Explorer    
		try{
			xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");	      					   
		}
		catch (e){
			try
			{
				xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");   	                	             
			}
			catch (e)
			{
				alert(nomineeDtlsAlertMsgArr[4]);       
				return false;        
			}
		}			 
	}					
	var url = "hdiits.htm?actionFlag=getNomineeDraftRecord&flag=open&reqId="+reqId; 
	xmlHttp.open("POST", encodeURI(url) , true);						
	xmlHttp.onreadystatechange=openFamilyDraftRequest; 
	xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
	xmlHttp.send(encodeURIComponent(null));
}	

function openFamilyDraftRequest()
{
	if (xmlHttp.readyState == 4) 
	{     
		if (xmlHttp.status == 200) 
		{
			document.frmNominee.btnSubmit.disabled=false;	
			document.frmNominee.btnSave_draft.disabled=false;
			var xmlStr = xmlHttp.responseText;						
			var XMLDoc=getDOMFromXML(xmlStr);
			var name=XMLDoc.getElementsByTagName('Name'); 
			var share=XMLDoc.getElementsByTagName('Share');
			var minor=XMLDoc.getElementsByTagName('Minor');
			var dob = XMLDoc.getElementsByTagName('DOB');
			var relation=XMLDoc.getElementsByTagName('Relation');
			var req=XMLDoc.getElementsByTagName('Request');			    	
			var xml=XMLDoc.getElementsByTagName('XMLFile');			    	
			var removeRow = XMLDoc.getElementsByTagName('RemoveRow');
			var SrNo = XMLDoc.getElementsByTagName('DeleteDraft');
			var fm	 = XMLDoc.getElementsByTagName('FamilyMemeber');
			document.getElementById('nomineeAddDataTable').style.display='';			    				    				    				    				    				    	
			var j=0,k=1;
			var addFlag=0,valK=0;
			for ( var i = 0 ; i < name.length ; i++ )
			{
				var name1=name[i].childNodes[0].text;	     					
				var share1=share[i].childNodes[0].text;     					
				var countNow=document.getElementById("Share").value;       					
				countNow=parseFloat(countNow)-parseFloat(share1);
				if(countNow<0){countNow=0;}
				document.getElementById("Share").value=countNow;				    				    			    			    	
				var minor1=minor[i].childNodes[0].text;
				var dob1=dob[i].childNodes[0].text;
				var relation1=relation[i].childNodes[0].text;
				var req1=req[i].childNodes[0].text;
				var xml1=xml[i].childNodes[0].text;
				var fm1= fm[i].childNodes[0].text;

				count=parseInt(count)+1; 		
				deleteArr[deleteArrCount]=xml1;
				deleteArrCount=deleteArrCount+1;
				if(minor1=='N'){minor1='No';}
				else if(minor1=='Y'){minor1='Yes';}
				var age1=countAge(dob1);
				if(age1=='undefined' || age1==undefined){age1='0';}
				if(age1=='null' || age1=='' || age1==null)
				{												
					age1="0";
				}
				var displayFieldArray= new Array(name1,relation1,dob1,age1,minor1,share1);
				displayFieldArray=removeNull(displayFieldArray);
				if(req1=='D') 
				{	 																								
					var showDraftLinkId=addDBDataInTable('nomineeAddDataTable','encXML',displayFieldArray,xml1,'','Reload','');
					var linkId=1;
					trow=document.getElementById(showDraftLinkId);
					trow.cells[linkId].innerHTML ="<font color='blue'>"+trow.cells[linkId].innerText+"</font>";
					trow.cells[linkId+1].innerHTML ="<font color='blue'>"+trow.cells[linkId+1].innerText+"</font>";
					trow.cells[linkId+2].innerHTML ="<font color='blue'>"+trow.cells[linkId+2].innerText+"</font>";
					trow.cells[linkId+3].innerHTML ="<font color='blue'>"+trow.cells[linkId+3].innerText+"</font>";
					trow.cells[linkId+4].innerHTML ="<font color='blue'>"+trow.cells[linkId+4].innerText+"</font>";
					trow.cells[linkId+5].innerHTML ="<font color='blue'>"+trow.cells[linkId+5].innerText+"</font>";							
				}
				else
				{
					var showDraftLinkId=addDBDataInTable('nomineeAddDataTable','encXML',displayFieldArray,xml1,'editRecord', 'deleteEmpNomineeRecord','');
				}
				/*familyMemeberArr Dtls */						
				var x1,memFlag=false;						
				for (x1 in familyMemeberArr)
				{
					var map = familyMemeberArr[x1].toString();
					map	= map.split('/');						
					if(map[0] == fm1 && fm1!=0)
					{										
						memFlag=true;
					}				
				}						
				if(memFlag==true)
				{
					familyMemeberArr[familyMemeberCount]=fm1+"/"+showDraftLinkId;
					familyMemeberCount=parseInt(familyMemeberCount)+1;	
				}
				/*End of familyMemeberArr */
				if(req1=='null' || req1=='N')
				{
					draftArrCount= draftArrCount+1;
					draftArr[draftArrCount] = showDraftLinkId;		  					   					
				}	   					    					
				else
				{			   					
					for(var k=valK;k< removeRow.length ; k++ )	
					{
						if(addFlag==0)
						{
							var remove = removeRow[k].childNodes[0].text;  	
							if(remove!='null' )
							{	
								valK++;	   							
								var map1 =remove;
								var map2 ='/'+showDraftLinkId;
								var map = map1.concat(map2);
								mappingArr[mappingArrCount]=map;										
								addFlag=1;
								mappingArrCount= mappingArrCount+1;
							}
						}
					}														
				}						
				addFlag=0;	
			}

			var deleteId= SrNo[0].childNodes[0].text;	
			var delRow = document.getElementById(deleteId);						
			delRow.style.display='none';

			var flag=1;																	
			for(var i=1;i<document.getElementById('RequestTable').rows.length;i++)
			{
				trow= document.getElementById('RequestTable').rows[i];																																		
				try{
					if(trow.style.display=='')
					{																																										
						flag=0;
					}
				}catch(e){break;}									
			} 					
			if(flag==1)
			{
				document.getElementById("RequestTable").style.display='none';
			}
			else
			{
				var srno=1;	
				var num = document.getElementById('RequestTable').rows.length;								
				for(var i=1;i<num;i++)
				{
					trow= document.getElementById('RequestTable').rows[i];																																		
					try{															
						if(trow.style.display=='')
						{
							trow.childNodes[0].innerHTML=srno;																																											
							srno=parseInt(srno)+1;
						}
					}catch(e){break;}							
				}
			}
			document.getElementById('draftDataTable').style.display='none';
			showCurrentFieldGroupTab();
		}
		
	}
}
function removeNull(displayFieldArray)
{			
	for(i in  displayFieldArray)
	{								
		if(displayFieldArray[i]=='null' || displayFieldArray[i]=='' || displayFieldArray[i]==null || displayFieldArray[i]==nomineeDtlsAlertMsgArr[39])
		{												
			displayFieldArray[i]='-';
		}
	}
	return displayFieldArray;
}
/******End of Draft Dtls****************/		
/******Validate Nominee Form **********/

function validate(fieldValue,comp_type)
{
	if(comp_type==0)  // text field
	{
		if(fieldValue==''){return false;}
	}
	else if(comp_type==1)  //combo type
	{				
		if(fieldValue==''){return false;}
		if(fieldValue=='Select'){return false;}
		if(fieldValue=='select'){return false;}
	}
	return true;
}


function validateNominationForm()
{
	var returnValue = true,chkValue=true;
	var fieldValue;
	var rule_num="qwertyuiopasdfghjklzxcvbnmASDFGHJKLPOIUYTREWQZXCVBNM";
	var name_rule_num="qwertyuiopasdfghjklzxcvbnmASDFGHJKLPOIUYTREWQZXCVBNM ";
	first_name=document.getElementById('gur_first_name').value;
	middle_name=document.getElementById('gur_middle_name').value;
	last_name=document.getElementById('gur_last_name').value;
	nomiName = document.getElementById('nomn_name').value;	
	nomiDob = document.getElementById('nomi_DOB').value;			//IFMS


	if(chkValue!='error')	
	{
		if(nomiName!="" && document.frmNominee.nomiType[0].status==true)
		{
			for(var i=0;i<nomiName.length;i++)
			{
				var cchar=nomiName.charAt(i);
				if(name_rule_num.indexOf(cchar)==-1)
				{
					alert(nomineeDtlsAlertMsgArr[9]); 
					return 'error';
				}
			} 
		}
	}
	if(!workFlowEnabled)
	{
		if(chkValue!='error')	
		{
			if(nomiName=="" && document.frmNominee.nomiType[0].status==true)								//IFMS
			{
				alert(nomineeDtlsAlertMsgArr[10]);
				return 'error';
			}
		} 
	}
	if(chkValue!='error')	
	{
		var fieldValue=document.forms[0].nomi_share.value;				
		if(fieldValue=='' || fieldValue==null)
		{
			alert(nomineeDtlsAlertMsgArr[11]);
			setFocusSelection("nomi_share");
			chkValue = 'error';
		}
		else
		{
			for(var i=0;i<fieldValue.length;i++)
			{
				var keyId = fieldValue.charCodeAt(i);									
				if(keyId >= 48 && keyId <= 57 || keyId==46) {}
				else 
				{
					alert(nomineeDtlsAlertMsgArr[11]);
					setFocusSelection("nomi_share");
					chkValue = 'error';
					break;
				}
			}
		}
	}
	if(chkValue!='error')	
	{ 
		RelationCombo=document.getElementById('nomi_Relation');													
		fieldValue=RelationCombo.value;	
		if(fieldValue=='fm_rel_Other')
		{
			var v =  document.forms[0].nomi_otherRelation.value;
			if(v=='' || v==null)
			{
				alert(nomineeDtlsAlertMsgArr[12]);
				chkValue='error';	
			}				
		}
	}
	if(!workFlowEnabled)
	{
		if(chkValue!='error')	
		{   
			if(nomiDob=="" && document.frmNominee.nomiType[0].status==true)				//IFMS
			{
				alert(nomineeDtlsAlertMsgArr[13]);
				return 'error';
			}
		}    
	}
	if(chkValue!='error')	
	{
		var fieldValue=document.forms[0].nomi_share.value;				
		if(fieldValue=='' || fieldValue==null)
		{
			alert(nomineeDtlsAlertMsgArr[11]);
			setFocusSelection("nomi_share");
			chkValue = 'error';
		}
		else
		{
			for(var i=0;i<fieldValue.length;i++)
			{
				var keyId = fieldValue.charCodeAt(i);									
				if(keyId >= 48 && keyId <= 57 || keyId==46) {}
				else 
				{
					alert(nomineeDtlsAlertMsgArr[11]);
					setFocusSelection("nomi_share");
					chkValue = 'error';
					break;
				}
			}
		}
	}
	if(chkValue!='error')	
	{
		if(minorFlag==true)
		{
			if(first_name!="")
			{
				for(var i=0;i<first_name.length;i++)
				{
					var cchar=first_name.charAt(i);
					if(rule_num.indexOf(cchar)==-1)
					{
						alert(nomineeDtlsAlertMsgArr[14]);
						return 'error';
					}
				} 
			}
			else 																		//IFMS
			{
				if(!workFlowEnabled)
				{
					alert(nomineeDtlsAlertMsgArr[15]);
					return 'error';
				}
			}
			if(last_name!="")
			{
				for(var i=0;i<last_name.length;i++)
				{
					var cchar=last_name.charAt(i);
					if(rule_num.indexOf(cchar)==-1)
					{
						alert(nomineeDtlsAlertMsgArr[16]);
						return 'error';
					}
				} 
			}
			else																	//IFMS
			{
				if(!workFlowEnabled)
				{
					alert(nomineeDtlsAlertMsgArr[17]);
					return 'error';
				}
			}			    

			if(middle_name!="")
			{
				for(var i=0;i<middle_name.length;i++)
				{
					var cchar=middle_name.charAt(i);
					if(rule_num.indexOf(cchar)==-1)
					{
						alert(nomineeDtlsAlertMsgArr[18]);
						return 'error';
					}
				} 
			}

			RelationCombo=document.getElementById('gur_Relation');													
			fieldValue=RelationCombo.value;	
			if(fieldValue=='fm_rel_Other')
			{
				var v = document.forms[0].gur_otherRelation.value;
				if(v=='' || v==null)
				{
					alert(nomineeDtlsAlertMsgArr[19]);
					chkValue='error';	
				}
			}
			else 																			//IFMS
			{
				if(!workFlowEnabled)
				{
					if(fieldValue==null || fieldValue=='Select')
					{
						alert(nomineeDtlsAlertMsgArr[20]);
						chkValue='error';
					}
				}
			}

		}
	}
	if(!workFlowEnabled)
	{
		//Start IFMS
		if(chkValue!='error')	
		{
			if(document.frmNominee.nomiType[0].status==true)   
			{
				if(document.getElementById("nomi_personAge").value < 18)				
				{
					var addrName ='NomineeAddress';
					if(checkAreaNameValidation(addrName))
					{	
						alert(nomineeDtlsAlertMsgArr[21]);
						return 'error';
					}
					var addrName1='GuardianAddress';
					if(checkAreaNameValidation(addrName1)) 
					{	
						alert(nomineeDtlsAlertMsgArr[22]);
						return 'error';
					}
				}
				else{
					var addrName ='NomineeAddress';
					if(checkAreaNameValidation(addrName))
					{	
						alert(nomineeDtlsAlertMsgArr[21]);
						return 'error';
					}	
				}
			}
		}
		if(chkValue!='error')	
		{
			if(document.frmNominee.nomiType[1].status==true)
			{
				if(document.getElementById("nomi_personAge").value < 18)				
				{
					var addrName1='GuardianAddress';
					if(checkAreaNameValidation(addrName1)) 
					{	
						alert(nomineeDtlsAlertMsgArr[22]);
						return 'error';
					}
				}
			}
		}
	}
	//Ends IFMS

	if(chkValue != 'error')
	{
		var valueFlag=checkForDot(fieldValue);			
		if(valueFlag==false)
		{
			alert(nomineeDtlsAlertMsgArr[11]);
			setFocusSelection("nomi_share");
			chkValue='error';					
		}	
	}

	return chkValue;
}		
function checkForDot(fieldValue)
{
	var valueFlag=true,dotCount=0;
	for(var i=0;i<fieldValue.length;i++)
	{
		var keyId = fieldValue.charCodeAt(i);									
		if(keyId==46) {dotCount=parseInt(dotCount)+1;}
	}
	if(dotCount>=2){valueFlag=false;}
	return valueFlag;
}
/*Message Showing on Submit*/
function messageDisplay()
{
	var str='',fir=true,countRows=0;
	var num = document.getElementById('nomineeAddDataTable').rows.length;
	for(var i=1;i<num;i++)
	{							
		trow=document.getElementById('nomineeAddDataTable').rows[i];
		rowId=trow.id;
		try 
		{				
			var hField = rowId.substring(3, rowId.length);
			var xmlFileName = document.getElementById(hField).value;									
			if(xmlFileName.indexOf("$/$")!=-1)
			{
				rowIdArr=xmlFileName.split("$/$");												
				if(fir==false)
				{
					str=str+"\n\n";												
				}
				else{fir=false;}
				countRows=countRows+3;
				str=str+'Row No '+i+' : ';	
				validationArray = rowIdArr[1].split(",");
				for (itr=0;itr<validationArray.length;itr++)
				{
					if (validationArray[itr] != '')
					{
						if (validationArray[itr] == 'eis.nominee_name')
							str = str + nomineeDtlsAlertMsgArr[23];
						else if (validationArray[itr] == 'eis.DATE_OF_BIRTH')
							str = str + nomineeDtlsAlertMsgArr[24];
						else if (validationArray[itr] == 'eis.GuardianFName')
							str = str + nomineeDtlsAlertMsgArr[25];
						else if (validationArray[itr] == 'eis.GuardianLName')
							str = str + nomineeDtlsAlertMsgArr[26];
						else if (validationArray[itr] == 'eis.NomineeGuardianRel')
							str = str + nomineeDtlsAlertMsgArr[27];
						else if (validationArray[itr] == 'eis.GuardianAddress')
							str = str + nomineeDtlsAlertMsgArr[28];
						else if (validationArray[itr] == 'eis.NomineeAddress')	
							str = str + nomineeDtlsAlertMsgArr[29];
						else if (validationArray[itr] == 'eis.NomineeRelation')
							str = str + nomineeDtlsAlertMsgArr[30];

						if (validationArray.length-2 != itr)
							str = str + " ,";	
					}
				}
				if (validationArray.length > 0)
				{
					if(validationArray.length>2)
					{
						str=str.substring(0,str.length-1);
					}
					str = str +" " + nomineeDtlsAlertMsgArr[31] + ".";
					getFieldGroupObj(document.getElementById("errorMessage"));
				}
			}										
		}catch(e){}	
		addLine=true;												
	}
	if(str=='')
	{
		if(confirm(nomineeDtlsAlertMsgArr[32])==true) 
		{
			var x1;					
			for (x1 in deleteAppArr)
			{
				var map =deleteAppArr[x1].toString();					
				var map1=map.substring(3, map.length);
				if(map1.indexOf("encXML")!=-1)
				{
					var map2=map.substring(9, map.length);				
					deleteArr[deleteArrCount]=document.getElementById("encXML"+map2).value;
					deleteArrCount=deleteArrCount+1;
				}								
			}
			for (x in deleteArr)
			{
				var xmlFileName = deleteArr[x].toString();
				if(xmlFileName.indexOf("$/$")!=-1)
				{
					rowIdArr=xmlFileName.split("$/$");
					deleteArr[x]=rowIdArr[0];
				}					
			}		
			document.frmNominee.action = "hrms.htm?actionFlag=SubmitNomineeDetails&workFlowEnabled="+ workFlowEnabled +"&draft=0&DeleteArr="+deleteArr+"&userId="+document.getElementById("userId").value; //IFMS  
			document.frmNominee.submit();	
		}   				
	}			
	else 
	{				
		document.getElementById('errorBox').rows=countRows;
		document.getElementById('errorMessage').style.display='';				
		document.getElementById('errorBox').value=str;								
		return false;
	}			
}
/*End of Message*/
/***End of Validate Nominee Form ****/	

function openAppWindow(actionFlag)//IFMS
{
	var userId = document.getElementById("userId").value;
	var href = "hrms.htm?actionFlag="+  actionFlag + "&userId="+ userId ;
	objChildWindow = window.open(href,"Country","toolbar=no, location=no, directories=no,status=yes, menubar=no, scrollbars=yes, resizable=yes, top=10, left=10, width=980, height=660, copyhistory=no");			
}


function goToPurposePage(workFlowFlag)
{
	document.frmNominee.action = "hrms.htm?actionFlag=NomineeDetails&flag=getComboDetails&workFlowEnabled="+workFlowFlag;
	document.frmNominee.submit();
}

function resetOnFamilySelect()
{
	document.getElementById("gur_Relation").value='Select';  
	document.getElementById("nomi_Relation").value='Select';
	document.getElementById("firstNameCmb").value='Select';			
	document.forms[0].nomn_name.value='';
	document.forms[0].nomi_otherRelation.value='';
	document.forms[0].nomi_DOB.value='';
	document.forms[0].nomi_personAge.value=''; 
	document.forms[0].gur_first_name.value='';
	document.forms[0].gur_middle_name.value='';
	document.forms[0].gur_last_name.value='';
	document.forms[0].nomi_share.value='';
	document.forms[0].gur_otherRelation.value='';
	document.getElementById("guardianTable").style.display='none';
	resetAddress('NomineeAddress'); 
	closeAddress('NomineeAddress'); 
	resetAddress('GuardianAddress');
	closeAddress('GuardianAddress'); 
}

// For PuposeofNomination.jsp

function showNextPage()
{				
	var purCmb= document.getElementById('purposeCmb').value;
	if(purCmb=='Select'){}
	else 
	{
		showProgressbar();
		document.frmPurposeofNomn.action = "hrms.htm?actionFlag=ShowNomineeDetailsPage&purposeValue="+purCmb+"&userId="+ document.getElementById("userId").value+"&workFlowEnabled="+workFlowEnabled;
		document.frmPurposeofNomn.submit();	
	}
}

function goToHomePageOfPurpseNomi()
{
	if(workFlowEnabled)
	{
		document.frmPurposeofNomn.action = "hrms.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
		document.frmPurposeofNomn.submit();
	}
	else
	{
		window.close();
	}
}

function showCurrentFieldGroupTab()
{
	getFieldGroupObj(document.getElementById("nomineeAddDataTable"));
}

/************************** For ShowNomineeDtls.jsp**************************/

var navDisplay = true,disabledFlag=false;
var arr = new Array();
/*function SubmitData(reqId,empId,flag)
{	

	var j=0;
	for (var i = 0; i < document.showNomineeDtls.elements.length; i++)
	{						
		if(document.showNomineeDtls.elements[i].type=="checkbox" )
		{					
			var user_input = document.showNomineeDtls.elements[i].value;
			if(document.showNomineeDtls.elements[i].checked==true)
			{
				arr[j]=user_input;
				j=parseInt(j)+1;
			}
		}
	}	
	if(flag==1)
	{			
		document.showNomineeDtls.action="hrms.htm?actionFlag=SubmitNomineeDtlsForApprove&reqId="+reqId+"&empId="+empId+"&memberId="+arr+"&flag=submit";		
		document.showNomineeDtls.submit();
	}
	else
	{
		document.showNomineeDtls.action="hrms.htm?actionFlag=SubmitNomineeDtlsForApprove&reqId="+reqId+"&empId="+empId+"&memberId="+arr+"&flag=forward";				
		document.showNomineeDtls.submit();
	}			
}	
function set_Age_ShowNominee(birthDate)
{
		var splitDate=birthDate.split("/");				
		var byr=splitDate[2];
		var bmo=splitDate[1];
		var bday=splitDate[0];				
		v= Age_ShowNominee(byr+'-'+bday+'-'+bmo);
		return v;
}*/

function callMe(action)
{
	if(action=='A' || action=='R')
	{
		disabledFlag=true;
	}
}	

function disabledMe()
{
	if(!disabledFlag)
	{
		for (var i = 0; i < document.showNomineeDtls.elements.length; i++)
		{						
			if(document.showNomineeDtls.elements[i].type=="checkbox" )
			{					
				document.showNomineeDtls.elements[i].disabled=false;
			}
		}
	}
}

function Back()
{
	document.showNomineeDtls.action="hrms.htm?actionFlag=ApproveNomineeDtls";
	document.showNomineeDtls.submit();
}

function submitHiddenData() 
{	
	var j=0;
	for (var i = 0; i < document.showNomineeDtls.elements.length; i++)
	{						
		if(document.showNomineeDtls.elements[i].type=="checkbox" )
		{					
			var user_input = document.showNomineeDtls.elements[i].value;
			if(document.showNomineeDtls.elements[i].checked==true)
			{
				arr[j]=user_input;
				j=parseInt(j)+1;
			}
		}
	}
	document.getElementById("approveDtls").value=arr;
	var uri = "hrms.htm?actionFlag=";
	var url = uri + "FwdNomineeDtlsForApproval&approveDtls="+arr;   
	document.showNomineeDtls.action = url;
	document.showNomineeDtls.submit();
}