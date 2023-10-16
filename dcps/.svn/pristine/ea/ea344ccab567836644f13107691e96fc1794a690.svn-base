 var dises=''; 
 var disescat='';
 
 
 
 
function changebg(c)
{
	c.style.backgroundColor="" ;

}
function DDMMYYYY(date){
var arr=date.split("/");
return arr[2]+"/"+arr[1]+"/"+arr[0];
}

var editRowId;	  	
function loadcalendar(name,img)
{
	var cal1 = new CalendarPopup();
	cal1.select(name,img,'dd/MM/yyyy'); return false;
}



/* 	Start Added for Disease Details 
	
*/

var CounterForPressAdd1=0;
var GlobalUpdateFlag=false;

/* 	For validating Diseases for adding  	
*/
function validateAddDisease()
{
	
 	var officeTypeArray = new Array('dependent','DiseaseType','DiseaseCat');
	var statusOfficeTypeValidation =  validateSpecificFormFields(officeTypeArray);
	if(statusOfficeTypeValidation)
	{
		if(GlobalUpdateFlag==false)
		{
			insRowDeseaseTab();
		}
		else
		{
			startupAjaxFormValidationDisease();
		}
	}
}
/* Add Starts From here*/
function insRowDeseaseTab()		
{
	    var Remarks=document.getElementById("textarea").value;
	    
	    if(Remarks.length==0)
	    {
	    	document.getElementById("textarea").value= 'No Remarks';
	    	addOrUpdateRecord('addRecordDisease', 'addDiseaseDtls',new Array('DiseaseType','DiseaseCat','DiseaseRemarks'));
	   		CounterForPressAdd1++;
	    }
		else
		{
			addOrUpdateRecord('addRecordDisease', 'addDiseaseDtls',new Array('DiseaseType','DiseaseCat','DiseaseRemarks'));
			CounterForPressAdd1++;
	 	}
}

/*Reseting data after adding and updating both*/
function resetDesesData()
{
	document.getElementById("dstpe").value= 'Select';
	document.getElementById("disCat").value= 'Select';
	document.getElementById("otherdses").value= '';
	document.MRBFRM.DiseaseRemarks.value= '';	
	
}
function addRecordDisease()
{
		if (xmlHttp.readyState == 4) 
			{
			   var DiseaseType =document.getElementById("dstpe").value;
				if(DiseaseType=='2')
				{
				 	var displayFieldArray = new Array('DiseaseType','DiseaseCat','DiseaseRemarks');
			     	addDataInTable('DiseaseDtlsTab', 'encXMLDisease', displayFieldArray,'editRecordDesease','deleteRecordDis');
			     	resetDesesData();
				}
				else 
				{
			    	var displayFieldArray = new Array('DiseaseType','DiseaseCat','DiseaseRemarks');
			     	addDataInTable('DiseaseDtlsTab', 'encXMLDisease', displayFieldArray,'editRecordDesease','deleteRecordDis');
			     	resetDesesData();
				}
			}		
	
}

/*Editing Record and Checking that whether
 its coming from Database or not
 */
 
var DataComingFrmDBReq=false;		
function editRecordDesease(rowId) 
{						
		    updateRow=null;
			editRowId=rowId;	
			document.MRBFRM.Savee.disabled=false;			
			document.MRBFRM.AddDesease.disabled=true;																				
			sendAjaxRequestForEdit(rowId, 'populateFormDesease');	
			globalFlagDeleteDisable = true;	
		
}
function editDBRecordDesease(rowId) 
{						
		    DataComingFrmDBReq=true;
		    //alert(DataComingFrmDBReq);
		    updateRow=null;
			editRowId=rowId;	
			document.MRBFRM.Savee.disabled=false;			
			document.MRBFRM.AddDesease.disabled=true;																				
			sendAjaxRequestForEdit(rowId, 'populateFormDesease');	
			globalFlagDeleteDisable = true;	
		
}

/*Populating values for Editing */

function populateFormDesease() 
{					
			  GlobalUpdateFlag=true;
			  if (xmlHttp.readyState == 4) 
			  { 				  		
			  		var decXML = xmlHttp.responseText;		  					  	
					var xmlDOM = getDOMFromXML(decXML);	
												 						
					document.MRBFRM.DiseaseType.value = getXPathValueFromDOM(xmlDOM, 'parentDisId');   	
					//alert(getXPathValueFromDOM(xmlDOM, 'diseaseDtlPK'));
					if(getXPathValueFromDOM(xmlDOM, 'diseaseDtlPK')!=0)
					{
						document.MRBFRM.DiseasePK.value=getXPathValueFromDOM(xmlDOM, 'diseaseDtlPK'); 
					}
			 		dises = getXPathValueFromDOM(xmlDOM, 'diseaseId');
			 		
			 		document.getElementById("hidn").value=dises;
			 	
			 		getInfo(document.MRBFRM.DiseaseType);
			 		if(getXPathValueFromDOM(xmlDOM, 'DiseaseRemarks')=='No Remarks') 
			 		{
			 			document.MRBFRM.DiseaseRemarks.value="";
			 		}
			 		else
			 		{
						document.MRBFRM.DiseaseRemarks.value = getXPathValueFromDOM(xmlDOM, 'DiseaseRemarks');															 
					}
			 }			  
}

/*after editing Updation of Data 
taking Place here*/

function startupAjaxFormValidationDisease()
{
	  	if(DataComingFrmDBReq==false)
		{
	  		addOrUpdateRecord('updateDiseaseRecord', 'addDiseaseDtls',new Array('DiseaseType','DiseaseCat','DiseaseRemarks'));							
	  	}
	  	else
	  	{
	  		addOrUpdateRecord('updateDiseaseRecord', 'addDiseaseDtls',new Array('DiseaseType','DiseaseCat','DiseaseRemarks','DiseasePK','MRBID'));							
		}										
			
}	
function updateDiseaseRecord() 
{		
				  if (xmlHttp.readyState == 4) 
				  { 				
				  		//document.getElementById('srNo').value=updateSrNo;				  		
						var displayFieldArray = new Array('DiseaseType','DiseaseCat','DiseaseRemarks');
						
						if(DataComingFrmDBReq==false)
						{
							updateDataInTable('encXMLDisease', displayFieldArray,0);									
						}
						else
						{
							//alert("updateDataInTable");
							updateDataInTable('encXMLDiseaseFrmDB', displayFieldArray,0);	
						}
						globalFlagDeleteDisable = false;
						document.MRBFRM.Savee.disabled=true;	
						document.MRBFRM.AddDesease.disabled=false;
						resetDesesData();						
				}
				GlobalUpdateFlag=false;
				DataComingFrmDBReq=false;
}

/*deleting Existing Record*/
function deleteRecordDis(rowId)
{
            var answer = confirm("Are you sure you want to delete this record?");
            if(answer){
            trow=document.getElementById(rowId);
			trow.parentNode.deleteRow(trow.rowIndex);
		    document.MRBFRM.Savee.disabled=true;			
			document.MRBFRM.AddDesease.disabled=false;	
			CounterForPressAdd1--;	
			}
}	
/* End Disease Addition,Edit and Update All Details */


/* Start Added for TreatMent Details */
var CounterForPressAdd2=0;
var GlobalUpdateFlagTrtmnt=false;

/*Validation Starts For Treatment here*/

function validateAddTreatment()
{
	
 	var officeTypeArray = new Array('HosName','TrtmntStart','TrtmntEnd');
	var statusOfficeTypeValidation =  validateSpecificFormFields(officeTypeArray);
	if(statusOfficeTypeValidation)
	{
		if(GlobalUpdateFlagTrtmnt==false)
		{
			insRowTrtmntTab();
		}
		else
		{
			startupAjaxFormValidationHsptl();
		}
	}
}

/*Data Insertion Starts From here*/

function insRowTrtmntTab()		
{
		addOrUpdateRecord('addRecordTrtmnt', 'addTrtmntDtls',new Array('HosName','HosType','HosAdress','TrtMntType'));	
		CounterForPressAdd2++;
}
function addRecordTrtmnt()
{
		if (xmlHttp.readyState == 4) 
			{ 
			    var displayFieldArray = new Array('HosName','HosType','HosAdress','TrtMntType');
			     addDataInTable('TrtMntDtlsTab', 'encXMLTrtmnt', displayFieldArray,'editRecordTrtmnt','deleteRecordTrmnt');
				  document.getElementById("hosnam").value='Select';
			     document.getElementById("HosType").value='';
			     document.getElementById("HosAdress").value='';
			}		
	
}
var DataComingFrmDBTrtmnt=false;		

/*Editing Record For Both Database as well as New*/

function editRecordTrtmnt(rowId) 
{						
			updateRow=null;
			editRowId=rowId;	
			document.MRBFRM.Addhsptl.disabled=true;			
			document.MRBFRM.Savehsptl.disabled=false;																				
			sendAjaxRequestForEdit(rowId, 'populateFormTrtmnt');
			globalFlagDeleteDisable = true;				
}
function editDBRecordTrtmnt(rowId) 
{						
			DataComingFrmDBTrtmnt=true;
			updateRow=null;
			editRowId=rowId;	
			document.MRBFRM.Addhsptl.disabled=true;			
			document.MRBFRM.Savehsptl.disabled=false;																				
			sendAjaxRequestForEdit(rowId, 'populateFormTrtmnt');
			globalFlagDeleteDisable = true;				
}

/*Populating Data For Editing*/

function populateFormTrtmnt() 
{					
			  GlobalUpdateFlagTrtmnt=true;
			  if (xmlHttp.readyState == 4) 
			  { 				  		
			  		var decXML = xmlHttp.responseText;			  					  	
					var xmlDOM = getDOMFromXML(decXML);							 						
					document.MRBFRM.HosName.value = getXPathValueFromDOM(xmlDOM, 'hospitalId');   		    					
					document.MRBFRM.TrtMntType.value=getXPathValueFromDOM(xmlDOM, 'TrtmentType');
					if(getXPathValueFromDOM(xmlDOM, 'TrtmentId')!=0)
					{
						document.MRBFRM.TrtmntPK.value =getXPathValueFromDOM(xmlDOM, 'TrtmentId');   
					}
					getHsptlDtls();															 	
			 }			  
}

/*after editing Updation of Data 
taking Place here*/

function startupAjaxFormValidationHsptl()
{          
		if(DataComingFrmDBTrtmnt==false)
		{
			addOrUpdateRecord('updateHsptlRecord', 'addTrtmntDtls',new Array('HosName','HosType','HosAdress','TrtMntType'));							
		}
		else
		{						
			addOrUpdateRecord('updateHsptlRecord', 'addTrtmntDtls',new Array('HosName','HosType','HosAdress','TrtMntType','TrtmntPK','MRBID'));							
		}										
}
function updateHsptlRecord() 
{		
		if (xmlHttp.readyState == 4) 
		{ 						  		
			var displayFieldArray = new Array('HosName','HosType','HosAdress','TrtMntType');
			if(DataComingFrmDBTrtmnt==false)
			{
				updateDataInTable('encXMLTrtmnt', displayFieldArray,0);									
			}
			else
			{
			updateDataInTable('encXMLTrtmntFrmDB', displayFieldArray,0);		
			}
			globalFlagDeleteDisable = false;
			document.MRBFRM.Savehsptl.disabled=true;	
			document.MRBFRM.Addhsptl.disabled=false;
			document.getElementById("hosnam").value='Select';
			document.getElementById("HosType").value='';
			document.getElementById("HosAdress").value='';					
		} 
}

/*Deleting Existing Records*/
function deleteRecordTrmnt(rowId)
{
            var answer = confirm("Are you sure you want to delete this record?");
            if(answer)
            {
            	trow=document.getElementById(rowId);
				trow.parentNode.deleteRow(trow.rowIndex);
		   		document.MRBFRM.Addhsptl.disabled=false;			
				document.MRBFRM.Savehsptl.disabled=true;	
				document.getElementById("hosnam").value='';
		   		document.getElementById("HosAdress").value='';
		    	document.getElementById("HosType").value='';			
		    	CounterForPressAdd2--;
			}
}
/* End Disease Addition,Edit and Update All Details */
/* End TreatMent Details */


var XMLFILEPATHS="";
var gettingRowId="";


/* End Medicine Details */
function dateWithin(beginDate,endDate,checkDate) {
	var b,e,c;
	b = Date.parse(beginDate);
	e = Date.parse(endDate);
	c = Date.parse(checkDate);
	if((c <= e && c >= b)) {
		return true;
		//alert("date is ok");
	}
	return false;
}


var CommonCounterForDateCheck=1;

/* Start Added for Bill Details */
/*Validation Starts For Bill here*/
var GlobalUpdateFlagBill=false;
function validateAddBill(BillAlert)
{
	
 	var officeTypeArray = new Array('TrtmntStart','TrtmntEnd','BillNo','BillDate','ChemistName');
	var statusOfficeTypeValidation =  validateSpecificFormFields(officeTypeArray);
	if(statusOfficeTypeValidation)
	{
		if(GlobalUpdateFlagBill==false)
		{
			insRowBillTab(BillAlert);
		}
		else
		{
			startupAjaxFormValidationBill(BillAlert);
		}
	}
}

/* Add Starts From here*/
var CounterForPressAdd3=0;
function insRowBillTab(BillAlert)		
{
		//if(document.MRBFRM.Savehsptl.disabled)
		//{
			var BillNo= document.getElementById("BillNo").value;
		  
		   	var BillDate= document.getElementById("BillDate").value;
		 	
		   	var GetSeparBillDate=BillDate.split("/");
		   	var TrtmntstrDate=document.getElementById("TrtmntStart").value;
		   	var GetSeparStartDate=TrtmntstrDate.split("/");
		 
		   	var TrtmntendDate=document.getElementById("TrtmntEnd").value;
		   	var GetSeparEndDate=TrtmntendDate.split("/");
		   	
		   	if(compareDate(TrtmntstrDate,BillDate)>=0 && compareDate(TrtmntendDate,BillDate)<=0)
		   	{
	       		var ChemistName= document.getElementById("ChemistName").value;
	       		document.getElementById("MedXMlPath").value="NoPath";
	      		var MedXMlPath=document.getElementById("MedXMlPath").value;
	      		
  					document.getElementById("BillDate").style.backgroundColor='' ;
   					document.getElementById("ChemistName").style.backgroundColor='' ;
  					document.getElementById("BillNo").style.backgroundColor='' ;
  					document.getElementById("h6").value="";
 					addOrUpdateRecord('addRecordBill', 'addBillDtls',new Array('BillNo','BillDate','ChemistName','MedXMlPath'));
					CounterForPressAdd3++;
					CommonCounterForDateCheck++;
		   	}
		   	else
		   	{
		   		//BillAlert();
		   		alert(BillAlert);
		   	}
		   	
		//}
		//else
		//{
	 	//	alert('<fmt:message  bundle="${cmnLables}" key="mrb.alert15"/>');	
		//}	
}
function addRecordBill()
{
		if (xmlHttp.readyState == 4) 
			{ 
				var decXMl=xmlHttp.responseText;
			   
			    document.getElementById("MedicineAmt").value="-------Nil------";
			    var displayFieldArray = new Array('BillNo','BillDate','ChemistName');
			    var DisplayArr=new Array('MedicineAmt');
			    var abc=addDataInTableBillSecnd('BillDtlsTab', 'encXMLBill', displayFieldArray,DisplayArr,'editRecordBill','deleteBillRecord','');
				//alert("New Bill abc="+abc);
			    document.getElementById("BillNo").value='';
		        document.getElementById("BillDate").value='';
	            document.getElementById("ChemistName").value='';
				
			}
			
	
}	
/*deleting Existing Record*/
function deleteBillRecord(rowId)
{	
			trow=document.getElementById(rowId);
			var delAmt=	trow.cells[5].innerText;
			var answer = confirm("Are you sure you want to delete this record?");
			
			if(answer)
			{
			
				trow.parentNode.deleteRow(trow.rowIndex);
				if(document.MRBFRM.billTotal.value!='')
				{
					if(delAmt=="-------Nil------")
					{
						delAmt=0;
					}
					document.MRBFRM.billTotal.value=(parseFloat(document.MRBFRM.billTotal.value)-parseFloat(delAmt)).toFixed(2);
				}			
				
				if(document.MRBFRM.billTotal.value!=''&&document.MRBFRM.miscTotal.value!='')
				{
					document.MRBFRM.totAdmissible.value=(parseFloat(document.MRBFRM.billTotal.value)+parseFloat(document.MRBFRM.miscTotal.value)).toFixed(2);
				}
			    document.MRBFRM.AddBill.disabled=false;			
			    document.MRBFRM.SaveBill.disabled=true;	
			           
		  		document.getElementById("BillNo").value="";
		 	 	document.getElementById("BillDate").value="";
		 	 	document.getElementById("MedicineAmt").value="";
		 	 	CounterForPressAdd3--;
			}
}

/*Editing Record For Both Database as well as New*/

var DataComingFrmDBBill=false;		
function editRecordBill(rowId) 
{						
			updateRow=null;
	        editRowId=rowId;	
			document.MRBFRM.AddBill.disabled=true;			
			document.MRBFRM.SaveBill.disabled=false;	
			sendAjaxRequestForEditBill(rowId, 'populateFormBill');	
			globalFlagDeleteDisable = true;				
}
function editDBRecordBill(rowId) 
{						
			DataComingFrmDBBill=true;
			updateRow=null;
	        editRowId=rowId;	
			document.MRBFRM.AddBill.disabled=true;			
			document.MRBFRM.SaveBill.disabled=false;	
			sendAjaxRequestForEditBill(rowId, 'populateFormBill');	
			globalFlagDeleteDisable = true;				
}

/*Populating Data For Editing*/

function populateFormBill() 
{					
			  GlobalUpdateFlagBill=true;
			  if (xmlHttp.readyState == 4) 
			  { 				  		
			  		var decXML = xmlHttp.responseText;  					  	
					var xmlDOM = getDOMFromXML(decXML);							 						
					document.MRBFRM.BillNo.value = getXPathValueFromDOM(xmlDOM, 'BillNo');
					if(getXPathValueFromDOM(xmlDOM, 'BillId')!=0)
					{
						document.MRBFRM.BillPK.value = getXPathValueFromDOM(xmlDOM, 'BillId');
					}
					var date =	getXPathValueFromDOM(xmlDOM, 'BillDate');	
				    datearr =date.split(' ');
				    newdate=datearr[0].split('-');
				    var formattedate=newdate[2]+"/"+newdate[1]+"/"+newdate[0];
					
					document.MRBFRM.BillDate.value =formattedate; 
					document.MRBFRM.ChemistName.value = getXPathValueFromDOM(xmlDOM, 'ChemistName');
					document.MRBFRM.h6.value =	getXPathValueFromDOM(xmlDOM, 'MedicineXMlPath');
					document.MRBFRM.h5.value =	getXPathValueFromDOM(xmlDOM, 'MediIdCombintn');
					document.MRBFRM.h4.value = getXPathValueFromDOM(xmlDOM, 'BillAmnt');
			 }			  
}


/*after editing Updation of Data 
taking Place here*/

function  startupAjaxFormValidationBill(BillAlert)
{              
    var BillDate= document.getElementById("BillDate").value;
	var GetSeparBillDate=BillDate.split("/");
	var TrtmntstrDate=document.getElementById("TrtmntStart").value;
	var GetSeparStartDate=TrtmntstrDate.split("/");
	
	var TrtmntendDate=document.getElementById("TrtmntEnd").value;
	var GetSeparEndDate=TrtmntendDate.split("/");
		   	
	if(compareDate(TrtmntstrDate,BillDate)>=0 && compareDate(TrtmntendDate,BillDate)<=0)
	{
    	if(DataComingFrmDBBill==false)
		{
			addOrUpdateRecord('updateBillRecord','addBillDtls',new Array('BillNo','BillDate','ChemistName','h5','h6','h4'));	
		}
		else
		{
			addOrUpdateRecord('updateBillRecord','addBillDtls',new Array('BillNo','BillDate','ChemistName','BillPK','MRBID','h5','h6','h4'));	
       	 	document.getElementById("BillDate").style.backgroundColor='' ;
        	document.getElementById("ChemistName").style.backgroundColor='' ;
        	document.getElementById("BillNo").style.backgroundColor='' ;				
    	}
    }
    else
    {
    	alert(BillAlert);
    }
}

function updateBillRecord()
 {		         
				  if (xmlHttp.readyState == 4) 
				  { 				
				  		//alert(xmlHttp.responseText);
						globalFlagDeleteDisable = false;
						document.MRBFRM.SaveBill.disabled=true;	
						document.MRBFRM.AddBill.disabled=false;
						var displayFieldArray = new Array('BillNo','BillDate','ChemistName');
						 if(DataComingFrmDBBill==false)
						 { 
						 	var xyz=updateDataInTableBill('encXMLBill', displayFieldArray);		
						 	
						 }
						 else
						 {
						 	var xyz=updateDataInTableBill('encXMLBillFrmDB', displayFieldArray);
						 	
						 }
					    document.getElementById("BillNo").value='';
			            document.getElementById("BillDate").value='';
			            document.getElementById("ChemistName").value='';		
			           		
				} 
			}

/* End Bill Details */

/* Start Added for Misc Details */
var CounterForPressAdd5=0;
var GlobalUpdateFlagMisc=false;
/*Validating before Add in Misc*/
function validateAddMisc()
{
	
 	var officeTypeArray = new Array('MiscAmtRemarks','MiscAmt');
	var statusOfficeTypeValidation =  validateSpecificFormFields(officeTypeArray);
	if(statusOfficeTypeValidation)
	{
		if(GlobalUpdateFlagMisc==false)
		{
			if(IfnotClickAdd==true)
			{
				document.MRBFRM.miscTotal.value=0.0;
			}
			IfnotClickAdd=false;
			insRowMiscTab();
		}
		else
		{
			startupAjaxFormValidationMisc();
		}
	}
}
/*Add Starts here*/
function insRowMiscTab()		
{
	
		if(document.MRBFRM.SaveMisc.disabled)
		{
		   var MiscAmt= document.getElementById("MiscAmt").value;
		   var MiscAmtRemarks= document.getElementById("MiscAmtRemarks").value;
		   if(document.MRBFRM.billTotal.value!=''&& document.MRBFRM.miscTotal.value!='')
			{
				document.MRBFRM.totAdmissible.value=(parseFloat(document.MRBFRM.billTotal.value)+parseFloat(document.MRBFRM.miscTotal.value)).toFixed(2); 
			}
		   
			addOrUpdateRecord('addRecordMisc', 'addMiscDtls',new Array('MiscAmtRemarks','MiscAmt'));
			CounterForPressAdd5++;
		}
}
function addRecordMisc()
{
		if (xmlHttp.readyState == 4) 
			{ 
			    var displayFieldArray = new Array('MiscAmtRemarks','MiscAmt');
			     addDataInTable('MiscDtlsTab', 'encXMLMisc', displayFieldArray,'editRecordMisc','deleteMiscRecord');
			    if(document.MRBFRM.miscTotal.value!='')
				{
					document.MRBFRM.miscTotal.value=(parseFloat(document.MRBFRM.miscTotal.value)+parseFloat(document.MRBFRM.MiscAmt.value)).toFixed(2);
					//alert(document.MRBFRM.miscTotal.value);
				}
				else
				{
					document.MRBFRM.miscTotal.value=(parseFloat(document.MRBFRM.MiscAmt.value)).toFixed(2);
				}
				if(document.MRBFRM.billTotal.value!=''&&document.MRBFRM.miscTotal.value!='')
				{
					document.MRBFRM.totAdmissible.value=(parseFloat(document.MRBFRM.billTotal.value)+parseFloat(document.MRBFRM.miscTotal.value)).toFixed(2);
				}
				 document.getElementById("MiscAmt").value='';
		         document.getElementById("MiscAmtRemarks").value='';
			}	
}	

/*Editing Data Coming From database as well as new*/

var DataComingFrmDBMisc=false;			
function editRecordMisc(rowId) 
{		
	updateRow=null;
	editRowId=rowId;	
	document.MRBFRM.AddMisc.disabled=true;			  
	document.MRBFRM.SaveMisc.disabled=false;		
	sendAjaxRequestForEdit(rowId, 'populateFormMisc');	
	globalFlagDeleteDisable = true;				
}
function editDBRecordMisc(rowId) 
{		
	DataComingFrmDBMisc=true;
	updateRow=null;
	editRowId=rowId;	
	document.MRBFRM.AddMisc.disabled=true;			  
	document.MRBFRM.SaveMisc.disabled=false;		
	sendAjaxRequestForEdit(rowId, 'populateFormMisc');	
	globalFlagDeleteDisable = true;				
}

/*Populating Data For updation*/
function populateFormMisc() 
{					
			  GlobalUpdateFlagMisc=true;
			  if (xmlHttp.readyState == 4) 
			  { 				  					  	 
			  		var decXML = xmlHttp.responseText;
					var xmlDOM = getDOMFromXML(decXML);	
					document.MRBFRM.MiscAmtRemarks.value = getXPathValueFromDOM(xmlDOM, 'remarks');    		    					
					document.MRBFRM.MiscAmt.value = getXPathValueFromDOM(xmlDOM, 'amount');
					if(getXPathValueFromDOM(xmlDOM, 'miscId')!=0)
					{
						document.MRBFRM.MiscPK.value = getXPathValueFromDOM(xmlDOM, 'miscId');
					}												 
				
					if(document.MRBFRM.miscTotal.value!='')
					{
						document.MRBFRM.miscTotal.value=(parseFloat(document.MRBFRM.miscTotal.value)-parseInt(getXPathValueFromDOM(xmlDOM, 'amount'))).toFixed(2)	;
						document.MRBFRM.totAdmissible.value=(parseFloat(document.MRBFRM.billTotal.value)+parseFloat(document.MRBFRM.miscTotal.value)).toFixed(2);
					}
					else
					{
						document.MRBFRM.miscTotal.value=(parseFloat(document.MRBFRM.MiscAmt.value)).toFixed(2);
				    	document.MRBFRM.totAdmissible.value=(parseFloat(document.MRBFRM.billTotal.value)).toFixed(2);		
					}
			 }			  
}

/*updating Data After Editing*/
function  startupAjaxFormValidationMisc()
{   					
				if(DataComingFrmDBMisc==false)
				{
					addOrUpdateRecord('updateMiscRecord','addMiscDtls',new Array('MiscAmtRemarks','MiscAmt'));
				}
				else
				{
					addOrUpdateRecord('updateMiscRecord','addMiscDtls',new Array('MiscAmtRemarks','MiscAmt','MiscPK','MRBID'));
				} 
				document.MRBFRM.miscTotal.value=(parseFloat(document.MRBFRM.miscTotal.value)+parseFloat(document.MRBFRM.MiscAmt.value)).toFixed(2);
				document.MRBFRM.totAdmissible.value=(parseFloat(document.MRBFRM.billTotal.value)+parseFloat(document.MRBFRM.miscTotal.value)).toFixed(2);
}

function updateMiscRecord()
{		
				  if (xmlHttp.readyState == 4) 
				  { 						  		
						var displayFieldArray = new Array('MiscAmtRemarks','MiscAmt');
						if(DataComingFrmDBMisc==false)
						{
							updateDataInTable('encXMLMisc', displayFieldArray,0);
						}
						else
						{
							updateDataInTable('encXMLMiscFrmDB', displayFieldArray,0);
						}									
						globalFlagDeleteDisable = false;
						document.MRBFRM.SaveMisc.disabled=true;	
						document.MRBFRM.AddMisc.disabled=false;
					    document.getElementById("MiscAmtRemarks").value='';
			            document.getElementById("MiscAmt").value='';	
				} 
}

/*Deleting Data From Table*/

function deleteMiscRecord(rowId)
{
			trow=document.getElementById(rowId);
			var delAmt=	trow.cells[2].innerText;
			
	        var answer = confirm("Are you sure you want to delete this record?");
		    if(answer)
			{
				trow.parentNode.deleteRow(trow.rowIndex);
				if(document.MRBFRM.billTotal.value!='' && document.MRBFRM.miscTotal.value!='')
				{

				    document.MRBFRM.miscTotal.value=(parseFloat(document.MRBFRM.miscTotal.value)-parseFloat(delAmt)).toFixed(2);
					document.MRBFRM.totAdmissible.value=(parseFloat(document.MRBFRM.billTotal.value)+parseFloat(document.MRBFRM.miscTotal.value)).toFixed(2);
				}

		        document.MRBFRM.AddMisc.disabled=false;			  
			    document.MRBFRM.SaveMisc.disabled=true;	
				document.getElementById("MiscAmt").value="";
		        document.getElementById("MiscAmtRemarks").value="";
		        CounterForPressAdd5--;
		}

}	


/* End Misc Details */


function deleteEmpEducationRecord(rowId)
{					
					//alert('899889');			 	
				if(globalFlagDeleteDisable == false)
				{																				
						var answer = deleteRecord(rowId);								
				}
			else
			{
				alert('You are in editing mode');
			}																				
}

function getHsptlDtls()
{
	var hsptlId=document.MRBFRM.HosName.value;
	//alert(hsptlId);
	if(hsptlId!="Select")
	{
		document.MRBFRM.HosAdress.value=eval('document.MRBFRM.HosAdress'+hsptlId).value;
		document.MRBFRM.HosType.value=eval('document.MRBFRM.HosType'+hsptlId).value;
	}
	else
	{
		document.MRBFRM.HosAdress.value="";
		document.MRBFRM.HosType.value="";
	}
}
/* For populating disease Cat*/
function getdepnInfo(q)
{
	    
	
	if(q.value=='Select'||q.value=='Selectt'){
			//Remove existing elements
   document.getElementById("depdtl").style.display="none";
 
  
			return;
		}
			try{   
    			xmlHttp=new XMLHttpRequest();    
	    	}
			catch (e)
			{    // Internet Explorer    
					try{
      					xmlHttp=new 
                        ActiveXObject("Msxml2.XMLHTTP");      
      				}
		    		catch (e){
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
        	document.getElementById("depdtl").style.display="";
        	var DepId="";
        	if(q.value==undefined)
        	{
        		DepId=q.text;
        	}
        	else
        	{
        		DepId=q.value;
        	}
        	
			var url = "hrms.htm?actionFlag=getMRBReqPage&flag=getdepndtl&depid="+DepId;    
			xmlHttp.open("POST", encodeURI(url) , false);			
			xmlHttp.onreadystatechange = processResponse1;
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));
   //   /      otherdses();
}
function getInfo(q)
{	     
	if(q.value=='Select')
	{
		var z=document.getElementById('disCat');								
		for (var i=z.length;i>=0;i--)
	    {	     				     					
			z.remove(z.value);
			z.remove(i);
	    }	 
	     				
	    var y=document.createElement('option');
		y.text='[Select]';
		y.value='Select';
		try
		{
			z.add(y,null); 	    						
		}
		catch(ex)
		{	   			 
			z.add(y);	   			 				 
		}    	
		            	
		return;
		}
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
		var url = "hrms.htm?actionFlag=getMRBReqPage&flag=getDisCat&disid="+q.value;    
		xmlHttp.open("POST", encodeURI(url) , true);			
		xmlHttp.onreadystatechange = processResponse;
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));
   //   /      otherdses();
}
function processResponse()
{
	if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{          				
						var textId;						
		            	var z=document.getElementById('disCat');	
		            	

				    	var xmlStr = xmlHttp.responseText;
				    	
				    	var XMLDoc=getDOMFromXML(xmlStr);   				    			    	
				    	
				    	var disCatId = XMLDoc.getElementsByTagName('DisCatId');
				    	var disCatDesc = XMLDoc.getElementsByTagName('DisCatDisc');
				    	
		    	       	
		    	       	
		    	       	//Remove existing elements							
					   	for (var i=z.length;i>=0;i--)
	     				{	     				     					
							z.remove(z.value);
							z.remove(i);
	     				}	     		     							
		    	       	 
		    	       	var y=document.createElement('option');
		   				y.text='[Select]';
						y.value='Select';
		   				try
						{
							z.add(y,null); 	    						
						}
						catch(ex)
						{	   			 
			 				z.add(y);	   			 				 
						}
		    	       	   	 				    	     		     							
						for ( var i = 0 ; i < disCatId.length ; i++ )
	     				{	     		     								
	     				    id=disCatId[i].childNodes[0].text;	     				    
	     				    
	     					value=disCatDesc[i].childNodes[0].text;	     
	     					var y=document.createElement('option');
			   				y.text=value;
							y.value=id;
							try
							{
								z.add(y,null); 	    						
							}
							catch(ex)
							{	   			 
				 				z.add(y);	   			 				 
							}

	           			}
	           		
                 if(dises!=''){document.getElementById('disCat').value=dises;dises='';}

				}
				else 
				{  			
					alert("ERROR");					
				}
			}
}

function processResponse1()
	{
			if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{       
	          	    var xmlStr = xmlHttp.responseText;
				   
				    var XMLDoc=getDOMFromXML(xmlStr);   // xmlHttp.responseXML.documentElement;						    			    	
	          		var a = XMLDoc.getElementsByTagName('nam');
	          		
	          		 var b = XMLDoc.getElementsByTagName('dob');
	          		
				     var c = XMLDoc.getElementsByTagName('age');
				     var d = XMLDoc.getElementsByTagName('rel');
				     var e= XMLDoc.getElementsByTagName('depid');
					 var name=document.getElementById('name');
					 name.innerHTML=a[0].childNodes[0].text;
					 var date=document.getElementById('date');
					  //date.innerHTML=b[0].childNodes[0].text;
					 var dob=b[0].childNodes[0].text;
					 
					 dateArr=dob.split('-');
					
					 var DateOnly=dateArr[2].substring(0,2);
					
                     var formattedDate= DateOnly+'-'+dateArr[1]+'-'+dateArr[0] ;
                      date.innerHTML=formattedDate;
                     
				     var age=	 document.getElementById('age');
				     age.innerHTML=c[0].childNodes[0].text;
				     var rel=	 document.getElementById('rel');
				     rel.innerHTML=d[0].childNodes[0].text;
	             	document.getElementById('idmem').value=e[0].childNodes[0].text;
	          
	            	
	     }}
	}





/*
function SetTotalAmnt()
{

	if(CounterForPressAdd3==0 && document.MRBFRM.BillAmt.value!="")
	{
		
		if(document.MRBFRM.billTotal.value==0)
		{
			document.MRBFRM.billTotal.value=(parseFloat(document.MRBFRM.billTotal.value)+parseFloat(document.MRBFRM.BillAmt.value)).toFixed(2);
		}
		else
		{
			document.MRBFRM.billTotal.value=0;
			document.MRBFRM.billTotal.value=(parseFloat(document.MRBFRM.billTotal.value)+parseFloat(document.MRBFRM.BillAmt.value)).toFixed(2);
		}
	}
	else if(document.MRBFRM.BillAmt.value=="")
	{
		if(CounterForPressAdd3==0)
		{		
			document.MRBFRM.billTotal.value=0;
		}
	}
}

function setTotalBillAmnt()
{
	if(CounterForPressAdd4==0 && document.MRBFRM.MedAmt.value!="")
	{
		if(document.MRBFRM.medTotals.value==0)
		{
			document.MRBFRM.medTotals.value=(parseFloat(document.MRBFRM.medTotals.value)+parseFloat(document.MRBFRM.MedAmt.value)).toFixed(2);
		}
		else
		{
			document.MRBFRM.medTotals.value=0;
			document.MRBFRM.medTotals.value=(parseFloat(document.MRBFRM.medTotals.value)+parseFloat(document.MRBFRM.MedAmt.value)).toFixed(2);
		}
	}
	else if(document.MRBFRM.MedAmt.value=="")
	{
		if(CounterForPressAdd4==0 )
		{
			document.MRBFRM.medTotals.value=0;
		}
	}
}
*/
var IfnotClickAdd=false;
function AddTotalAdmmsbl()
{
	
	if(CounterForPressAdd5==0)
	{
		if(document.MRBFRM.miscTotal.value==0)
		{
			document.MRBFRM.miscTotal.value=(parseFloat(document.MRBFRM.miscTotal.value)+parseFloat(document.MRBFRM.MiscAmt.value)).toFixed(2)	;
		}
		else
		{	
			document.MRBFRM.miscTotal.value=0;
			document.MRBFRM.miscTotal.value=(parseFloat(document.MRBFRM.miscTotal.value)+parseFloat(document.MRBFRM.MiscAmt.value)).toFixed(2)	;
		}
		if(document.MRBFRM.totAdmissible.value==0)
		{
			document.MRBFRM.totAdmissible.value=(parseFloat(document.MRBFRM.billTotal.value)+parseFloat(document.MRBFRM.miscTotal.value)).toFixed(2);
		}
		else
		{
			document.MRBFRM.totAdmissible.value=0;
			document.MRBFRM.totAdmissible.value=(parseFloat(document.MRBFRM.billTotal.value)+parseFloat(document.MRBFRM.miscTotal.value)).toFixed(2);
		}
		IfnotClickAdd=true;
	}
	
}


var SaveDraftFlag=false;
function saveAsDraft(saveDraftAlert,EnterBillAmntAlert,ChangeDateALert,ChangeTrtmntDatesAlert)
{	
	SaveDraftFlag=true;
	validateSubmit(saveDraftAlert,EnterBillAmntAlert,ChangeDateALert,ChangeTrtmntDatesAlert);	
}


var SaveAsNewDraft="";
function saveAsNewDraft(saveDraftAlert,EnterBillAmntAlert,ChangeDateALert,ChangeTrtmntDatesAlert)
{
	SaveAsNewDraft="SaveAsNew";
	saveAsDraft(saveDraftAlert,EnterBillAmntAlert,ChangeDateALert,ChangeTrtmntDatesAlert);
}


function SubmitAsNewRequest(saveDraftAlert,EnterBillAmntAlert,ChangeDateALert,ChangeTrtmntDatesAlert)
{
	SaveAsNewDraft="SaveAsNew";
	SaveDraftFlag=false;
	validateSubmit(saveDraftAlert,EnterBillAmntAlert,ChangeDateALert,ChangeTrtmntDatesAlert);
}


var submitChanges="";
function SubmitWithChanges(saveDraftAlert,EnterBillAmntAlert,ChangeDateALert,ChangeTrtmntDatesAlert)
{
	
	submitChanges="yes";
	validateSubmit(saveDraftAlert,EnterBillAmntAlert,ChangeDateALert,ChangeTrtmntDatesAlert);
}

function SaveAgainWithSameId(saveDraftAlert,EnterBillAmntAlert,ChangeDateALert,ChangeTrtmntDatesAlert)
{
	SaveDraftFlag=true;
	submitChanges="yes";
	validateSubmit(saveDraftAlert,EnterBillAmntAlert,ChangeDateALert,ChangeTrtmntDatesAlert);
}


var AddAnotherCntr=0;
function FindInJs(BillCounterWhenMedAdded)
{
	AddAnotherCntr=BillCounterWhenMedAdded;
}


var ForBillAmntNull=false;
var CanSubmitFinally=true;
function validateSubmit(saveDraftAlert,EnterBillAmntAlert,ChangeDateALert,ChangeTrtmntDatesAlert)
{
	var officeTypeArrayfortab2;
	var officeTypeArray;
	var officeTypeArray1;
	var officeTypeArray2;
	var officeTypeArray3;
	var officeTypeArray4;
	var officeTypeArray5;
	var statusOfficeTypeValidationfortab2;
	var statusOfficeTypeValidation;
	var statusOfficeTypeValidation1;
	var statusOfficeTypeValidation2;
	var statusOfficeTypeValidation3;
	var statusOfficeTypeValidation4;
	var statusOfficeTypeValidation5;
	
	if(CounterForPressAdd1==0 && CounterForPressAdd2==0 && CounterForPressAdd3==0 &&  CounterForPressAdd5==0)
	{
		officeTypeArray = new Array('dependent','DiseaseType','DiseaseCat','HosName','TrtmntStart','TrtmntEnd','BillNo','BillDate','ChemistName','MiscAmtRemarks','MiscAmt');
		statusOfficeTypeValidation =  validateSpecificFormFields(officeTypeArray);
	}
 	else if(CounterForPressAdd1==0 || CounterForPressAdd2==0 || CounterForPressAdd3==0 || CounterForPressAdd5==0)
 	{
 		if(CounterForPressAdd1==0)
 		{
 			officeTypeArray1 = new Array('dependent','DiseaseType','DiseaseCat');
 			statusOfficeTypeValidation1= validateSpecificFormFields(officeTypeArray1);	
 		}
 		else
 		{
 			statusOfficeTypeValidation1=true;
 		}
 		if(CounterForPressAdd2==0 && statusOfficeTypeValidation1==true)
 		{
 			officeTypeArray2= new Array('HosName','TrtmntStart','TrtmntEnd');
 			statusOfficeTypeValidation2= validateSpecificFormFields(officeTypeArray2);	
 		}
 		else if(statusOfficeTypeValidation1==true)
 		{
 			statusOfficeTypeValidation2=true;
 		}
 		if(CounterForPressAdd3==0 && statusOfficeTypeValidation2==true)
 		{
 			officeTypeArray3 = new Array('BillNo','BillDate','ChemistName');
 			statusOfficeTypeValidation3= validateSpecificFormFields(officeTypeArray3);
 			
 		}
 		else if(statusOfficeTypeValidation2==true)
 		{
 			statusOfficeTypeValidation3=true;
 		}
 		
 		if(CounterForPressAdd5==0 && statusOfficeTypeValidation3==true)
 		{
 			officeTypeArray5 = new Array('MiscAmtRemarks','MiscAmt');
 			statusOfficeTypeValidation5= validateSpecificFormFields(officeTypeArray5);
 		}
 		else
 		{
 			statusOfficeTypeValidation5=true;
 		}
 	}
 	else if(CounterForPressAdd1!=0 && CounterForPressAdd2!=0 && CounterForPressAdd3!=0 && CounterForPressAdd5!=0)
 	{
 		statusOfficeTypeValidation=true;
 	}
	if(statusOfficeTypeValidation1==true && statusOfficeTypeValidation2==true && statusOfficeTypeValidation3==true && statusOfficeTypeValidation5==true)
	{
			if(confirm(saveDraftAlert)==true)    
		    {	

			 var checksubmitPending=true;
			if(document.MRBFRM.TrtmntStrdt.value!="" && document.MRBFRM.TrtmntEnddt.value!="")
			{
				if(compareDate(document.MRBFRM.TrtmntStrdt.value,document.MRBFRM.TrtmntStart.value)==0 && compareDate(document.MRBFRM.TrtmntEnd.value,document.MRBFRM.TrtmntEnddt.value)==0)
			  	{
			  		checksubmitPending=false;
			  	}
			}
			   
			if(checksubmitPending==true)
			{
				var AllXMLPaths="";
				//alert("CommonCounterForDateCheck"+CommonCounterForDateCheck);					
				for(var i=0;i<parseInt(CommonCounterForDateCheck)+parseInt(AddAnotherCntr);i++)
				{
					if(document.getElementById("encXMLBillFrmDB"+i)!=null)
					{
						var XMLFilePath=document.getElementById("encXMLBillFrmDB"+i).value;
						XMLFilePath=XMLFilePath.substring(0,XMLFilePath.length-2);
						//alert(XMLFilePath);
						AllXMLPaths=AllXMLPaths+","+XMLFilePath;	
					}
					if(document.getElementById("encXMLBill"+i)!=null)
					{
						var XMLFilePath=document.getElementById("encXMLBill"+i).value;
						
						AllXMLPaths=AllXMLPaths+","+XMLFilePath;
					}
				}	
				if(AllXMLPaths.length>1)
				{
					AllXMLPaths=AllXMLPaths.substring(1);
				}	
				
				var SeparAllPath=AllXMLPaths.split(",");
				for(var j=0;j<SeparAllPath.length;j++)
				{
					xmlHttp=GetXmlHttpObject();		
					var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + SeparAllPath[j];	
					xmlHttp.onreadystatechange = function()
					{
						if(xmlHttp.readyState == 4) 
						{
							if(xmlHttp.readyState == 4) 
							{ 				  		
								var decXML = xmlHttp.responseText;
								//alert(decXML);
								var xmlDOM = getDOMFromXML(decXML);	
								var GetDate=getXPathValueFromDOM(xmlDOM, 'BillDate');
								var GetAmnt=getXPathValueFromDOM(xmlDOM, 'BillAmnt');
								
								var datearr1 =GetDate.split(' ');
				    			var newdate1=datearr1[0].split('-');
				    			var formattedate1=newdate1[2]+"/"+newdate1[1]+"/"+newdate1[0];
				    			//alert(formattedate1);
				    			if(compareDate(document.MRBFRM.TrtmntStart.value,formattedate1)>=0 && compareDate(document.MRBFRM.TrtmntEnd.value,formattedate1)<=0)
		   						{
		   							//alert("inside if ");
		   							
		   							if(GetAmnt==0.0)
		   							{
		   								ForBillAmntNull=true;
		   							}
		   						}
		   						else
		   						{
		   							CanSubmitFinally=false;
		   							
		   						}
							}
						}
					}
					xmlHttp.open("POST",encodeURI(url),false);
					xmlHttp.send(null);	
				}
				if(CanSubmitFinally==true)
				{
					if(ForBillAmntNull==false)
					{
						if(SaveAsNewDraft!="SaveAsNew")
			  			{
			   				if(submitChanges!="yes")
			   				{
			   					document.MRBFRM.depntid.style.backgroundColor="";
			   					document.MRBFRM.action = "hrms.htm?actionFlag=saveMRBReqDtls&SaveDraftFlag="+SaveDraftFlag;
			   					document.MRBFRM.submit();
			   				}
			   				else
			   				{
			   					document.MRBFRM.depntid.style.backgroundColor="";
			   					document.MRBFRM.action = "hrms.htm?actionFlag=saveMRBReqDtls&SaveDraftFlag="+SaveDraftFlag+"&submitChanges="+submitChanges;
			   					document.MRBFRM.submit();
			   				}
			   			}
			   			else
			   			{
			   				document.MRBFRM.depntid.style.backgroundColor="";
			   				document.MRBFRM.action = "hrms.htm?actionFlag=saveMRBReqDtls&SaveDraftFlag="+SaveDraftFlag+"&SaveAsNewDraft="+SaveAsNewDraft;
			   				document.MRBFRM.submit();
			   			}
			   		}
			   		else
			   		{
			   			alert(EnterBillAmntAlert);
			   			ForBillAmntNull=false;
			   		}
				}
				else
				{
					alert(ChangeDateALert);
					CanSubmitFinally=true;
				}
			}
			else
			{
				alert(ChangeTrtmntDatesAlert);
			}
		}
		else
		{
			submitChanges="";
			SaveAsNewDraft="";
			SaveDraftFlag=false;
		}
	}
	else if(statusOfficeTypeValidation)
	{
		if(confirm(saveDraftAlert)==true)    
		{	  
			var checksubmitPending=true;
			if(document.MRBFRM.TrtmntStrdt.value!="" && document.MRBFRM.TrtmntEnddt.value!="")
			{
				if(compareDate(document.MRBFRM.TrtmntStrdt.value,document.MRBFRM.TrtmntStart.value)==0 && compareDate(document.MRBFRM.TrtmntEnd.value,document.MRBFRM.TrtmntEnddt.value)==0)
			  	{
			  		checksubmitPending=false;
			  	}
			}
			   
			if(checksubmitPending==true)
			{
				var AllXMLPaths="";
				//alert("CommonCounterForDateCheck"+CommonCounterForDateCheck);					
				for(var i=0;i<parseInt(CommonCounterForDateCheck)+parseInt(AddAnotherCntr);i++)
				{
					if(document.getElementById("encXMLBillFrmDB"+i)!=null)
					{
						var XMLFilePath=document.getElementById("encXMLBillFrmDB"+i).value;
						XMLFilePath=XMLFilePath.substring(0,XMLFilePath.length-2);
						//alert(XMLFilePath);
						AllXMLPaths=AllXMLPaths+","+XMLFilePath;	
					}
					if(document.getElementById("encXMLBill"+i)!=null)
					{
						var XMLFilePath=document.getElementById("encXMLBill"+i).value;
						//alert(XMLFilePath);
						AllXMLPaths=AllXMLPaths+","+XMLFilePath;
					}
				}	
				if(AllXMLPaths.length>1)
				{
					AllXMLPaths=AllXMLPaths.substring(1);
				}	
			
				var SeparAllPath=AllXMLPaths.split(",");
				for(var j=0;j<SeparAllPath.length;j++)
				{
					xmlHttp=GetXmlHttpObject();		
					var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + SeparAllPath[j];	
					xmlHttp.onreadystatechange = function()
					{
						if(xmlHttp.readyState == 4) 
						{
							if(xmlHttp.readyState == 4) 
							{ 				  		
								var decXML = xmlHttp.responseText;
								//alert(decXML);
								var xmlDOM = getDOMFromXML(decXML);	
								var GetDate=getXPathValueFromDOM(xmlDOM, 'BillDate');
								var GetAmnt=getXPathValueFromDOM(xmlDOM, 'BillAmnt');
								
								var datearr1 =GetDate.split(' ');
				    			var newdate1=datearr1[0].split('-');
				    			var formattedate1=newdate1[2]+"/"+newdate1[1]+"/"+newdate1[0];
				    			//alert(formattedate1);
				    			if(compareDate(document.MRBFRM.TrtmntStart.value,formattedate1)>=0 && compareDate(document.MRBFRM.TrtmntEnd.value,formattedate1)<=0)
		   						{
		   							
		   							if(GetAmnt==0.0)
		   							{
		   								ForBillAmntNull=true;
		   							}
		   						}
		   						else
		   						{
		   							CanSubmitFinally=false;
		   							
		   						}
							}
						}
					}
					xmlHttp.open("POST",encodeURI(url),false);
					xmlHttp.send(null);	
				}
				if(CanSubmitFinally==true)
				{
					if(ForBillAmntNull==false)
					{
						if(SaveAsNewDraft!="SaveAsNew")
			  			{
			   				if(submitChanges!="yes")
			   				{
			   					document.MRBFRM.depntid.style.backgroundColor="";
			   					document.MRBFRM.action = "hrms.htm?actionFlag=saveMRBReqDtls&SaveDraftFlag="+SaveDraftFlag;
			   					document.MRBFRM.submit();
			   				}
			   				else
			   				{
			   					document.MRBFRM.depntid.style.backgroundColor="";
			   					document.MRBFRM.action = "hrms.htm?actionFlag=saveMRBReqDtls&SaveDraftFlag="+SaveDraftFlag+"&submitChanges="+submitChanges;
			   					document.MRBFRM.submit();
			   				}
			   			}
			   			else
			   			{
			   				document.MRBFRM.depntid.style.backgroundColor="";
			   				document.MRBFRM.action = "hrms.htm?actionFlag=saveMRBReqDtls&SaveDraftFlag="+SaveDraftFlag+"&SaveAsNewDraft="+SaveAsNewDraft;
			   				document.MRBFRM.submit();
			   			}
			   		}
			   		else
			   		{
			   			alert(EnterBillAmntAlert);
			   			ForBillAmntNull=false;
			   		}
				}
				else
				{
					alert(ChangeDateALert);
					CanSubmitFinally=true;
				}
			}
			else
			{
				alert(ChangeTrtmntDatesAlert);
			}
		}
	}
	else
	{
			submitChanges="";
			SaveAsNewDraft="";
			SaveDraftFlag=false;
	}

}
function showSecond(l){
	var id=l.value;
	if (id='tcontent2')
	{
		document.getElementById('tcontent1').style.display="none";
		document.getElementById('tcontent2').style.display="";
	}
}

var MRbTrtind1="";
var MrbTrtOut2="";
function LoadAllinfo(q,MRbTrtind,MrbTrtOut)
{
	var MRBId=q;
	MRbTrtind1=MRbTrtind;
	MrbTrtOut2=MrbTrtOut;
	//alert("undefine nai hona chahiye"+MRBId);
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
		var url = "hrms.htm?actionFlag=getExistingReq&MRBId="+MRBId;    
		xmlHttp.open("POST", encodeURI(url) , true);			
		xmlHttp.onreadystatechange = processResponse2;
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));
	
}
function processResponse2()
{
	if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{       
	          	     var xmlStr = xmlHttp.responseText;
	          	     //alert(xmlStr);
					 var XMLDoc=getDOMFromXML(xmlStr); 
					 
					var diseaseId=XMLDoc.getElementsByTagName('diseaseId');
					 var diseaseDesc=XMLDoc.getElementsByTagName('diseaseDesc');
					  var ParentdisId=XMLDoc.getElementsByTagName('ParentdisId');
					   var ParentdisDesc=XMLDoc.getElementsByTagName('ParentdisDesc');
					    var Remarks=XMLDoc.getElementsByTagName('Remarks');
					     var DiseaseXML=XMLDoc.getElementsByTagName('DiseaseXML');
					      var HospitalId=XMLDoc.getElementsByTagName('HospitalId');
					       var HospitalDesc=XMLDoc.getElementsByTagName('HospitalDesc');
					        var Hospitaltype=XMLDoc.getElementsByTagName('Hospitaltype');
					         var HospitalAddr=XMLDoc.getElementsByTagName('HospitalAddr');
					          var TrtmntStrDate=XMLDoc.getElementsByTagName('TrtmntStrDate');
					           var TrtmntEndDate=XMLDoc.getElementsByTagName('TrtmntEndDate');
					            var TrtmntXML=XMLDoc.getElementsByTagName('TrtmntXML');
					             var MiscRemarks=XMLDoc.getElementsByTagName('MiscRemarks');
					              var MiscAmnt=XMLDoc.getElementsByTagName('MiscAmnt');
					               var MiscXML=XMLDoc.getElementsByTagName('MiscXML');
					                var medAmt=XMLDoc.getElementsByTagName('medAmt');
					                 var medId=XMLDoc.getElementsByTagName('medId');
					                  var medName=XMLDoc.getElementsByTagName('medName');
					                 var medType=XMLDoc.getElementsByTagName('medType');
					                var medCategory=XMLDoc.getElementsByTagName('medCategory');
					               var medWeight=XMLDoc.getElementsByTagName('medWeight');
					              var MediXML=XMLDoc.getElementsByTagName('MediXML');
					             var billNo=XMLDoc.getElementsByTagName('billNo');
					            var billDate=XMLDoc.getElementsByTagName('billDate');
					           var billAmt=XMLDoc.getElementsByTagName('billAmt');
					          var chemistName=XMLDoc.getElementsByTagName('chemistName');
					         var XMLMediPath=XMLDoc.getElementsByTagName('XMLMediPath');
					        var BillXMl=XMLDoc.getElementsByTagName('BillXMl');
					       var TrtmentType=XMLDoc.getElementsByTagName('TrtmentType');
						  var BillId=XMLDoc.getElementsByTagName('billId');
						 var TotalBillAmnt=XMLDoc.getElementsByTagName('TotalBillAmnt');
						var DependentId=XMLDoc.getElementsByTagName('DependentId');
					   var DependName=XMLDoc.getElementsByTagName('DependName');
						
						var TrtmntStrDate1=TrtmntStrDate[0].childNodes[0].text; 
				    	var datearr =TrtmntStrDate1.split(' ');
				    	var newdate=datearr[0].split('-');
				    	var formattedate=newdate[2]+"/"+newdate[1]+"/"+newdate[0];
						document.MRBFRM.TrtmntStart.value=formattedate;
						
					   
					   
					    var TrtmntEndDate1=TrtmntEndDate[0].childNodes[0].text;
						datearr1 =TrtmntEndDate1.split(' ');
				    	newdate1=datearr1[0].split('-');
				    	var formattedate1=newdate1[2]+"/"+newdate1[1]+"/"+newdate1[0];
						document.MRBFRM.TrtmntEnd.value=formattedate1;
						
						var ReqTypes=document.getElementById('ReqType').value;
						//alert(ReqTypes);
						if(ReqTypes=="PendingReq")
						{
							document.MRBFRM.TrtmntStrdt.value=formattedate;
							document.MRBFRM.TrtmntEnddt.value=formattedate1;
						}
						//alert(DependentId.length);
						if(DependentId.length>0)
						{
							
							//var DependName1=DependName[0].childNodes[0].text;
							var DependentId1=DependentId[0].childNodes[0].text;
							//alert(DependentId1);
							if(DependentId1!=-1)
							{
								document.MRBFRM.dependent.value=DependentId1;
								document.MRBFRM.idmem.value=DependentId1;
								document.getElementById("depdtl").style.display="";
								getdepnInfo(DependentId[0].childNodes[0]);
							}
							else
							{
								var Self="Select";
								document.MRBFRM.dependent.value=Self;
							}
						}
						
					if(diseaseId.length>0)
					{
						for(var i=0;i<diseaseId.length;i++)
						{
							var diseaseId1=diseaseId[i].childNodes[0].text;
							var diseaseDesc1=diseaseDesc[i].childNodes[0].text;
							var ParentdisId1=ParentdisId[i].childNodes[0].text;
							var ParentdisDesc1=ParentdisDesc[i].childNodes[0].text;
							var Remarks1=Remarks[i].childNodes[0].text;
							var DiseaseXML1=DiseaseXML[i].childNodes[0].text;
							
							addDBDataInTable('DiseaseDtlsTab','encXMLDiseaseFrmDB',new Array(ParentdisDesc1,diseaseDesc1,Remarks1),DiseaseXML1,'editDBRecordDesease','MRBdeleteDBRecordDis','');
							CounterForPressAdd1++;
						}
					}
					      
					if(HospitalId.length>0)      
					{
						for(var i=0;i<HospitalId.length;i++)
						{
							var HospitalId1=HospitalId[i].childNodes[0].text;
							var HospitalDesc1=HospitalDesc[i].childNodes[0].text;
							var Hospitaltype1=Hospitaltype[i].childNodes[0].text;
							var HospitalAddr1=HospitalAddr[i].childNodes[0].text;
							var TrtmntXML1=TrtmntXML[i].childNodes[0].text;
							var TrtmentType1=TrtmentType[i].childNodes[0].text;
							if(TrtmentType1=="Indoor")
							{
								TrtmentType1=MRbTrtind1;
							}
							else
							{
								TrtmentType1=MrbTrtOut2;
							}
							addDBDataInTable('TrtMntDtlsTab','encXMLTrtmntFrmDB',new Array(HospitalDesc1,Hospitaltype1,HospitalAddr1,TrtmentType1),TrtmntXML1,'editDBRecordTrtmnt','MRBdeleteDBRecordTrtmnt','');
							CounterForPressAdd2++;
						}
					}
					var totalMiscAmnt=0;
					if(MiscRemarks.length>0)      
					{
						for(var i=0;i<MiscRemarks.length;i++)
						{
							var MiscRemarks1=MiscRemarks[i].childNodes[0].text;
							var MiscAmnt1=MiscAmnt[i].childNodes[0].text;
							var MiscXML1=MiscXML[i].childNodes[0].text;
							
							addDBDataInTable('MiscDtlsTab','encXMLMiscFrmDB',new Array(MiscRemarks1,MiscAmnt1),MiscXML1,'editDBRecordMisc','deleteDBMiscRecord','');
							CounterForPressAdd5++;
							totalMiscAmnt=(parseFloat(MiscAmnt1)+parseFloat(totalMiscAmnt)).toFixed(2);
						}
					}
					document.MRBFRM.miscTotal.value=totalMiscAmnt;
					var totalBillAmnt=0;
					if(BillId.length>0)      
					{
						for(var i=0;i<BillId.length;i++)
						{
							var billNo1=billNo[i].childNodes[0].text;
							var billDate1=billDate[i].childNodes[0].text;
							var billAmt1=billAmt[i].childNodes[0].text;
							var chemistName1=chemistName[i].childNodes[0].text;
							var XMLMediPath1=XMLMediPath[i].childNodes[0].text;
							var medAmt1=medAmt[i].childNodes[0].text;
							var BillXMl1=BillXMl[i].childNodes[0].text;
							var BillId1=BillId[i].childNodes[0].text;
							
							
				    		var datearr1 =billDate1.split(' ');
				    		var newdate1=datearr1[0].split('-');
				    		var formattedate1=newdate1[2]+"/"+newdate1[1]+"/"+newdate1[0];
							
							
							
							var pqr=addDBDataInTableBillSecnd('BillDtlsTab','encXMLBillFrmDB',new Array(billNo1,formattedate1,chemistName1),new Array(billAmt1),BillXMl1,'editDBRecordBill','deleteDBRecordBill','');
							//alert("Add pqr coming from DB="+pqr);
							totalBillAmnt=(parseFloat(billAmt1)+parseFloat(totalBillAmnt)).toFixed(2);
							//alert(totalBillAmnt);
							CounterForPressAdd3++;
							CommonCounterForDateCheck++;
						}
					}
					document.MRBFRM.billTotal.value=totalBillAmnt;
					document.MRBFRM.totAdmissible.value=(parseFloat(document.MRBFRM.billTotal.value)+parseFloat(document.MRBFRM.miscTotal.value)).toFixed(2);
				}
			}
}


function deleteDBBillRecord(rowId)
	{
		
		var delCap = "";
		var delNtAlllowed = "";
		try
		{
			delCap = cmnLblArray[2];
			delNtAlllowed = cmnLblArray[3];
		}
		catch (e)
		{
			delNtAlllowed = "You can't delete record,\n Because you have open one record for update";
			delCap = "Are you sure you want to delete this record ?";
		}
		
		
		if(updateRow != null)
		{
			alert (delNtAlllowed);
			return false;
		}
		var answer = confirm(delCap);
		if(answer)
		{
			
			var hField = rowId.substring(3, rowId.length);
		var xmlFileName = document.getElementById(hField).value;
		xmlHttp=GetXmlHttpObject();		
		var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + xmlFileName;	
		xmlHttp.onreadystatechange = function()
		{
			if(xmlHttp.readyState == 4) 
			{
				if (xmlHttp.readyState == 4) 
					{ 				  		
						var decXML = xmlHttp.responseText;
						//alert("Bill XML Path"+decXML);
						var xmlDOM = getDOMFromXML(decXML);	
						//alert(getXPathValueFromDOM(xmlDOM, 'BillAmnt'));
						if(getXPathValueFromDOM(xmlDOM, 'BillAmnt')!=null)
						{
							var IndivdualBillAmnt=getXPathValueFromDOM(xmlDOM, 'BillAmnt');
							document.MRBFRM.billTotal.value=(parseFloat(document.MRBFRM.billTotal.value)-parseFloat(IndivdualBillAmnt)).toFixed(2);
							if(document.MRBFRM.billTotal.value!=''&& document.MRBFRM.miscTotal.value!='')
							{
									document.MRBFRM.totAdmissible.value=(parseFloat(document.MRBFRM.billTotal.value)+parseFloat(document.MRBFRM.miscTotal.value)).toFixed(2); 
							}
						}
						else
						{
							var IndividualMiscAmnt=getXPathValueFromDOM(xmlDOM, 'amount');
							document.MRBFRM.miscTotal.value=(parseFloat(document.MRBFRM.miscTotal.value)-parseFloat(IndividualMiscAmnt)).toFixed(2);
							if(document.MRBFRM.billTotal.value!=''&& document.MRBFRM.miscTotal.value!='')
							{
									document.MRBFRM.totAdmissible.value=(parseFloat(document.MRBFRM.billTotal.value)+parseFloat(document.MRBFRM.miscTotal.value)).toFixed(2); 
							}
						}	
					}
			}
		}
		xmlHttp.open("POST",encodeURI(url),false);
		xmlHttp.send(null);	
			
			var delRow 	= document.getElementById(rowId);
			var content = delRow.cells[0].innerHTML;
			showAlert(content);
			content = content.replace(".xml_N",".xml_D");
			content = content.replace(".xml_U",".xml_D");
			delRow.cells[0].innerHTML = content;					
			delRow.style.display='none';
		}
		if(answer)
		{
		
		}
	return answer;	
	}

function MRBdeleteDBRecordDis(rowId)
{
	CounterForPressAdd1--;
	MRBdeleteDBRecord(rowId);
}
function MRBdeleteDBRecordTrtmnt(rowId)
{
	CounterForPressAdd2--;
	MRBdeleteDBRecord(rowId);
}
function deleteDBMiscRecord(rowId)
{
	CounterForPressAdd5--;
	deleteDBBillRecord(rowId);
}
function deleteDBRecordBill(rowId)
{
	CounterForPressAdd3--;
	deleteDBBillRecord(rowId);
}
function MRBdeleteDBRecord(rowId)
	{
		var delCap = "";
		var delNtAlllowed = "";
		try
		{
			delCap = cmnLblArray[2];
			delNtAlllowed = cmnLblArray[3];
		}
		catch (e)
		{
			delNtAlllowed = "You can't delete record,\n Because you have open one record for update";
			delCap = "Are you sure you want to delete this record ?";
		}
		if(updateRow != null)
		{
			alert (delNtAlllowed);
			return false;
		}
		var answer = confirm(delCap);
		if(answer)
		{
			var delRow 	= document.getElementById(rowId);
			var content = delRow.cells[0].innerHTML;
			showAlert(content);
			content = content.replace(".xml_N",".xml_D");
			content = content.replace(".xml_U",".xml_D");
			delRow.cells[0].innerHTML = content;					
			delRow.style.display='none';
		}
	return answer;	
	}

function gettingValuesInJS(abc)
{
	//alert(abc);
}