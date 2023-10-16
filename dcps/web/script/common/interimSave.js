// declaration of global variables

	
/** Arrays Which are populated to store different kind of form elements names*/
var ajaxCallingArray=new Array();
var afterAjaxCallingArray=new Array();	
var multipleAddArray=new Array();
var attachmentNameArray=new Array();
var addressArray=new Array();
var globalVariableArray=new Array();
var multiAddValueArray=new Array();
var multipleAttachmentArray=new Array();
var orderOfExecutionArray=new Array();
var elementNameValueArray=new Array();	
var elementNameAttachmentArray=new Array();	


/**Global variables to store various flags and other information like url*/
var interimSaveURL;
var isInterimSave=false;
var createOrUpdate=false;
var orderNew=new Array();
var xmlDom;	
var ajaxIncrement=0;
var ajaxIncrementRow=0;
var firstRow=new Array();
var ajaxArrayKey;
var savedFromTab=false;

//functions to populate global arrays depending on element names passed.This will help in deciding the rendering of the page 
function addToAjaxCallingArray(key,array)
{
	orderNew[key]=array;
	ajaxCallingArray=ajaxCallingArray.concat(array);
}

function addToAfterAjaxCallingArray(key,array)
{
	orderNew[key]=array;
	afterAjaxCallingArray=afterAjaxCallingArray.concat(array);
}

function addToAttachmentArray(key,array)
{
	orderNew[key]=array;
	attachmentNameArray=attachmentNameArray.concat(array);
}

function addToAddressArray(key,array)
{
	orderNew[key]=array;
	addressArray=addressArray.concat(array);
}

function addToMultipleAddArray(key,array)
{
	orderNew[key]=array;
	multipleAddArray=multipleAddArray.concat(array);
}

function addToGlobalVariableArray(array)
{
	globalVariableArray=array;
	orderOfExecutionArray=orderOfExecutionArray.concat('globalVariableArray');
	orderNew['globalVariableArray']=array;
}

function addToOrderOfExecution(array)
{
	orderOfExecutionArray=orderOfExecutionArray.concat(array);
}


/** function to get url containing name-value pairs of form elements */
function getUrl()
{
    var totalElements= document.forms[0].elements.length
    var uri='';
    
	for(var i=0;i<totalElements;i++)
	{
		if(document.forms[0].elements[i].type=="checkbox" ||document.forms[0].elements[i].type=="radio")
	    {
		   if(document.forms[0].elements[i].checked==1)
		   {
			   uri= uri+'&'+document.forms[0].elements[i].name+'='+encodeURIComponent(document.forms[0].elements[i].value);
		   }
	    }
	    else
	    {
		   uri= uri+'&'+document.forms[0].elements[i].name+'='+encodeURIComponent(document.forms[0].elements[i].value);
	    }
    }
    return uri;
}	
			
/** this function is called when interim save is triggered by next and previous buttons on the tabNavigation.jsp */
function saveDataFromTab()
{
	savedFromTab=true;
	saveFormData();
}

/**function called on click of 'Save As Drafts' button.creates the view url and
				 handles the  contents of multiple add and attachments*/
function saveFormData()
{
	showProgressbar();
	var requestUrl=document.URL;			// fetches the url which loads a page
	var viewURL;
	
	if(requestUrl.indexOf("srNo")!=-1)			 // makes the url to store as the Page load url
	{
		viewURL=requestUrl.substring(requestUrl.indexOf("?actionFlag")+12,requestUrl.indexOf("srNo")-1);
	}
	else
	{
		viewURL=requestUrl.substring(requestUrl.indexOf("?actionFlag")+12);
	}
	
	interimSaveURL=viewURL;		
	
	//multiple add innerHTML encoded 
	for(var i=0;i<multipleAddArray.length;i++)
	{
		var multiAddValue=document.getElementById(multipleAddArray[i]).innerHTML;
		//alert('multi add enc '+multiAddValue);
		multiAddValueArray[multipleAddArray[i]]=encodeBase64(multiAddValue);
	}
	
	//attachment component checked for attachments.added only if attachment exists 
	for(var i=0,j=0;i<attachmentNameArray.length;i++)
	{
		var attachmentName=attachmentNameArray[j];
		var attachmentTableName="myTable"+attachmentName;
		if(document.getElementById(attachmentTableName).rows.length>1)
		{
			multipleAttachmentArray[j]=attachmentName;
			j++;
		}
	}
	
	// function call to get url
	saveFormDataAjax();
}	

/* function to create the request containing the url with name-value pairs of the form.
	fires ajax call to save the data.*/
function saveFormDataAjax()
{ 
	var url='';
    xmlHttp=GetXmlHttpObject();
    if (xmlHttp==null)
	{
	    alert ("Your browser does not support AJAX!");
	    return;
	} 
	
	url=getUrl(); 				// gets the url containing name value pairs for the page
    url=url+"&isInterimSave="+createOrUpdate;
	
	//append multiple add related data to request
	for(var i=0;i<multipleAddArray.length;i++)
	{
	// fetch the data related to multiple add table from the div id passed
	   var multipleAddTable=document.getElementById(multipleAddArray[i]).firstChild; 
	   var noOfRows=multipleAddTable.rows.length;
	   var tableId=multipleAddTable.id;

	   var attachmentNamesArray=multipleAddArray[i].split('_ATT_'); // get attachments related to multiple add
	   if(attachmentNamesArray.length>1)
	   {
	   		 for(var j=1;j<attachmentNamesArray.length;j++)	/** for every attachment, pass the table id,no of rows and attachment name*/
	   		 {
	   		 	url=url+"&ISMAV_MAA_"+tableId+"_MAA_"+noOfRows+"_MAA_"+attachmentNamesArray[j]+"="+"_MAA_";
	   		 }
	   		 
		}
	   url=url+"&ISMAV_DATA"+multipleAddArray[i]+"="+multiAddValueArray[multipleAddArray[i]];
	}

	// append attachment names to request
	for(var j=0;j<multipleAttachmentArray.length;j++)
	{
		var attachmentName=multipleAttachmentArray[j];
		var attachmentId='';
		var attachmentTableName="myTable"+attachmentName;
		var noOfCells=document.getElementsByName(attachmentTableName)[0].rows[0].cells.length;
		if(document.getElementsByName(attachmentTableName)[0].rows[0].cells[noOfCells-1].firstChild.id)
		{
		//	alert('attachment Present '+document.getElementsByName(attachmentTableName)[0].rows[0].cells[noOfCells-1].firstChild.value);
			attachmentId=document.getElementsByName(attachmentTableName)[0].rows[0].cells[noOfCells-1].firstChild.value;
		}
		else
		{
			//alert('no attachment present');
		}
		url=url+"&interimSaveAttachment"+attachmentName+"="+attachmentId;
	}
	
	// append the view url for the page to the request
	var interimSaveURLEncoded=encodeURIComponent(interimSaveURL);
	url=url+"&interimSaveUrl="+interimSaveURLEncoded;
	
	var interimSaveActionFlag='interimSave';
	var uri='ifms.htm?actionFlag='+interimSaveActionFlag;
	url=uri + url;           
 //alert('url '+url);
  
	xmlHttp.onreadystatechange=afterGeneratingXml;
	
	xmlHttp.open("POST",uri,true);
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(url);
}
// end of function saveFormDataAjax				

// function called after getting response from save drafts Ajax call
function afterGeneratingXml() 
{ 
	if (xmlHttp.readyState==4)
	{ 
		var XMLDocForCurrentStatus = xmlHttp.responseXML.documentElement;
		var xmlRecordSrNo=XMLDocForCurrentStatus.getElementsByTagName('xmlRecordNo');	// the sr no generated for interim save record
		document.getElementById('xmlRecordSrNo').value=xmlRecordSrNo[0].text;
		
		/** fethes all the attachments that were saved and puts the attachment ids in hidden fields
			so as to access the same when the document is saved again or is submitted*/
		//alert('length '+attachmentNameArray.length);
		for(var i=0;i<attachmentNameArray.length;i++)
		{
			var attachmentName=attachmentNameArray[i];
			//alert('attachmentname '+attachmentName);
			var attachmentTableName="myTable"+attachmentName;
			
			var noOfRows=document.getElementById(attachmentTableName).rows.length;
			if(noOfRows > 1)
			{
				var attachmentTableEle=document.getElementsByName(attachmentTableName)[0];
				for(var i=noOfRows-1;i>=1;i--)
				{
					attachmentTableEle.deleteRow(i);
				}
				//alert('no of rows '+document.getElementById(attachmentTableName).rows.length );
				var attachmentIdTag=XMLDocForCurrentStatus.getElementsByTagName(attachmentName);
				var attachmentId=attachmentIdTag[0].text;
				//alert('attachment id from doc '+ attachmentIdTag[0].text);
				var attachmentTableEle=document.getElementsByName(attachmentTableName)[0];
				var noOfCells=attachmentTableEle.rows[0].cells.length;
				if(!attachmentTableEle.rows[0].cells[noOfCells-1].firstChild.id)
				{
					attachmentTableEle.rows[0].insertCell(noOfCells).innerHTML = "<INPUT type='hidden' id='attchmentIdCell' name='" +attachmentName+ "' value='" + attachmentIdTag[0].text+ "' />";	
					//alert('no id '+ attachmentTableEle.rows[0].cells[noOfCells].innerHTML);
					attachmentTableEle.rows[0].cells[noOfCells].style.display='none';
				}
				solveAttachmentProblemTemp(attachmentId,attachmentName);
			}
		}
			//alert(multipleAddArray.length);
	   /** fethes all the attachments that were saved in multiple add and puts the attachment ids in hidden fields
			so as to access the same when the document is saved again or is submitted*/
	   for(var i=0;i<multipleAddArray.length;i++)
	   {
		// fetch the data related to multiple add from the div id passed
		   var multipleAddTable=document.getElementById(multipleAddArray[i]).firstChild;
		   var noOfRows=multipleAddTable.rows.length;
		   var tableId=multipleAddTable.id;
		   var attachmentNamesArray=multipleAddArray[i].split('_ATT_');
		   if(attachmentNamesArray.length>1)
		   {
		   		 for(var j=1;j<noOfRows;j++)
		   		 {
		   		 	var attachmentIds='';
		   		 	
		   		 	for(var k=1;k<attachmentNamesArray.length;k++)
		   		 	{
		   		 		var attachmentKey=tableId+"_"+j+"_"+attachmentNamesArray[k];
		   		 	 	//alert('attch key '+attachmentKey);
		   		 	 	var attachmentId='';
		   		 	 	//alert(XMLDocForCurrentStatus.getElementsByTagName(attachmentKey)[0]);
		   		 	 	if(XMLDocForCurrentStatus.getElementsByTagName(attachmentKey)[0])
		   		 	 	{
		   		 	 	 	attachmentId=XMLDocForCurrentStatus.getElementsByTagName(attachmentKey)[0].text;
		   		 	 	}
						if(attachmentId!='')
		   			 	{
		   			 		attachmentIds=attachmentIds+attachmentId;
						}
						if(k!=attachmentNamesArray.length-1)
						{
							attachmentIds=attachmentIds+",";
						}
						//alert('attachment key '+attachmentIds);  
		   		 	}
		   		   // alert('attachment key out '+attachmentIds); 
		   		 	var tableRow=document.getElementById(tableId).rows[j];
		   		 	var hiddenfield=(tableRow.id).substring((tableRow.id).indexOf('row')+3);
		   		 	var lastCellIdLogical='attachment'+hiddenfield;
		   	 	
		   		 	if(document.getElementsByName(lastCellIdLogical)[0])
		   		 	{
		   		 	//	alert('already present');
		   		 	}
		   		 	else
		   		 	{
		   		 		document.getElementById(multipleAddArray[i]).innerHTML+="<INPUT type='hidden' name='attachment" + hiddenfield + "' id='attachment" + hiddenfield + counter +"' value='" + attachmentIds + "'/>";	;
		   		 	}
		   		 	
		   		 }
		   }
		  
	   }	
		createOrUpdate=true;	
		if(savedFromTab==false)
		{
			alert('Draft has been saved and moved to drafts folder');
		}
		else
		{
			savedFromTab=false;
		}
		hideProgressbar();
    }
    
}
// end of function 	afterGeneratingXml			
	
/* function to populate the attachment at the time when the doc is saved */
function solveAttachmentProblemTemp(attachmentId,attachmentName)
{
	//alert('in solve attachment temp');
	var attachmentNameInXml='interimSaveAttachment'+attachmentName;
	var attachmentTableName='myTable'+attachmentNameArray[i];
	//alert('attachment id from doc '+attachmentId);
	xmlHttp=GetXmlHttpObject();
	var url='ifms.htm?actionFlag=interimSaveAttachmentPopulate';
	url=url+"&"+attachmentNameInXml+"="+attachmentId;
	//alert('attach url '+url);
	xmlHttp.onreadystatechange=function()
	{
		if (xmlHttp.readyState == 4) 
		{     
			if (xmlHttp.status == 200) 
			{
				//alert("i am back");
				var XMLDoc=populateAttachmentForEdit();	
				return true;
			}
		}
	}
	xmlHttp.open("POST",encodeURI(url),false);
	xmlHttp.send(null);
}
	
// function called to load the saved data from XML to the page
function loadSavedDataFromFile()
{		
	var xmlRecordSrNo=document.getElementById('xmlRecordSrNo').value;		// picks value of record to be populated
	//alert('xmlRecordSrNo '+xmlRecordSrNo);
	var isAjax=false;
	var isAtt=false;
	var isMul=false;
	var isAdd=false;
	isInterimSave=true;  
	createOrUpdate=true;                       						 // sets the interim save flag to true.
	var isAjaxElement=false;	
	var orderIterator=0;
	if(xmlRecordSrNo!="")							// checks if the page is opened from drafts or as fresh page 
	{
		var xmlHttp = GetXmlHttpObject();
		var url = "./ifms.htm?actionFlag=loadFormDataFromXml&xmlRecordSrNo="+xmlRecordSrNo;
		//alert(url);
		xmlHttp.onreadystatechange = function()
		{
			if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{
					var xmlFileContents=xmlHttp.responseText;
		        	//alert('xmlFileContents '+xmlFileContents);
					if(xmlFileContents!='fileDeleted')				// checks if the XML file has been deleted
					{							
						xmlDom =getDOMFromXML(xmlFileContents);
						var entries = xmlDom.getElementsByTagName('entry');	
						var propName;
						var propvalue;
						
						/** populates all the fields from XML into an array
							and all attachment id's to attachment array*/
   						for (var i = 0; i < entries.length; i++) 
                        {    			
							propName = entries[i].childNodes[0].text;
							propvalue= entries[i].childNodes[1].text;
							if(propName.indexOf('interimSaveAttachment')!=-1)
							{
							  //alert('propname '+ propName+' propval '+ propvalue);
							  elementNameAttachmentArray[propName]=propvalue;
							}
							else
							{
								elementNameValueArray[propName]=propvalue;
							}
					    }
					 
						var elemArray =document.forms[0].elements;
						
						/** to iterate all form elements */
						for (var i = 0; i < elemArray.length; i++) 
			            {
						   	 var element = elemArray[i];
						  	 if(element.name==undefined)
						   	 {
						   	 	continue;
						   	 }
							 if(elementNameValueArray[element.name])
							 {
							 	 isAjaxElement=false;
							 	 /** Eliminates elements NOT to be executed in 1st cycle*/
								 for(var ajaxsync=0;ajaxsync<orderOfExecutionArray.length;ajaxsync++)
								 {
									var innerArrayName=orderOfExecutionArray[ajaxsync];
									var innerArray=orderNew[innerArrayName];
									for(var ajaxInner=0;ajaxInner<innerArray.length;ajaxInner++)
									{
										var elementName=element.name;
										if(elementName==innerArray[ajaxInner])
										{
											isAjaxElement=true;
										}
										else
										if(elementName=='attachmentName')
										{
											isAjaxElement=true;
										}
										else
										if(innerArray[ajaxInner].indexOf('ISA_')!=-1)
										{
										
											var addrNameForAddressElement=innerArray[ajaxInner].substring(4);
											if(elementName.indexOf(addrNameForAddressElement)!=-1)
											{
												isAjaxElement=true;
											}
										}
									}
						  		}
						  		/** if the element is not a special element*/
						  		if(isAjaxElement==false)
								{   
									executeCallingFunctions(element);
								}
							}	
						}
						
						/** code to handle 2nd cycle of execution.executes all elements left in 1st cycle*/
						for(orderIterator=0;orderIterator<orderOfExecutionArray.length;orderIterator++)
						{
							var temp=orderNew[orderOfExecutionArray[orderIterator]];
						
							//identifies the type of chain and calles the corresponding function
							for(var y=0;y<ajaxCallingArray.length;y++)
							{
								if(ajaxCallingArray[y]==temp[0])
								{	
									isAjax=true;
								}
							}
							
							for(var y=0;y<attachmentNameArray.length;y++)
							{
								if(attachmentNameArray[y]==temp[0])
								{	
									isAtt=true;
								}
							}
							
							for(var y=0;y<multipleAddArray.length;y++)
							{
								if(multipleAddArray[y]==temp[0])
								{	
									isMul=true;
								}
							}
							
							for(var y=0;y<addressArray.length;y++)
							{
								if(addressArray[y]==temp[0])
								{	
									isAdd=true;
								}
							}
							
							if(isAjax==true)
							{
								solveAjaxProblem(temp,orderOfExecutionArray[orderIterator]);
							}
							
							if(isAtt==true)
							{
								solveAttachmentProblem();
							}
							
							if(isMul==true)
							{
								solveMultipleAddProblem();
							}
							
							if(isAdd==true)
							{
								solveAddressProblem();
							}
							
							// sets all flags to false
							isAjax=false;
							isAtt=false;
							isMul=false;
							isAdd=false;
						}
						
						//calls custom function defined by developer after execution completes
						if(orderIterator==orderOfExecutionArray.length)
						{	
							 customInterimSaveFunction();
						}
					}
					else							// if the XML file is not found on the server
					{
						alert('Can not load Form data.File has been tempered or Deleted.');
					}
				}
			}
		}
		xmlHttp.open("POST", encodeURI(url) , true);    
		xmlHttp.send(null);
	}
	else								// if no record exists
	{	
		isInterimSave=false;
		createOrUpdate=false;
	//	alert('no value for serial no');
	}
}
// function loadSavedDataFromFile ends here


//function to populate address components 
function solveAddressProblem()
{
	for(var i=0;i<addressArray.length;i++)
	{
		populateAddressInterimSave(addressArray[i].substring(4));	
	}
}

// function to execute ajax calling elements synchronously
function solveAjaxProblem(array,arrayKey)
{
	firstRow=array;
	ajaxArrayKey=arrayKey;
	//alert('ajaxIncrement '+ajaxArrayKey);
	if(ajaxIncrement>=firstRow.length)
	{
		//alert('end of array');
		if(ajaxCallingArray.length==0)
		{
			//alert('ajax end '+isInterimSave);
			isInterimSave=false;
		}
		//alert('isInterimSave '+isInterimSave);
		/* code to handle the after ajax chain,if exits for an ajax chain */
		var afterArrayName='AFTER_'+arrayKey;
		if(orderNew[afterArrayName])
		{
			afterAjaxCallingArrayElements=orderNew[afterArrayName];
			//alert(afterAjaxCallingArrayElements.length);
			for(var i=0;i<afterAjaxCallingArrayElements.length;i++)
			{
				var obj=document.getElementsByName(afterAjaxCallingArrayElements[i])[0];
				executeCallingFunctions(obj);
			}
		}
		return;
	}
	var firstElement=firstRow[ajaxIncrement];
	//alert('element '+firstElement);
	var indexOfElement=ajaxCallingArray.indexOf(firstElement);
	ajaxCallingArray.splice(indexOfElement,1);
	
	//alert('reduced ajax length  '+ ajaxCallingArray.length);
	
	//alert('firstElement value'+firstElement);
	//alert('elementNameValueArray[firstElement]'+elementNameValueArray[firstElement]);
	if(elementNameValueArray[firstElement])
	{
		var element=document.getElementsByName(firstElement)[0];
		executeCallingFunctions(element);
	}
	//to handle execution of funcitons passed by user
	else if(firstElement.indexOf('ISF_')!=-1)
	{
		var funcNameGet=firstElement.substring(firstElement.indexOf('ISF_')+4);
		//alert('funcNameGet '+funcNameGet);
		var funcName=funcNameGet+"()";
		eval(funcName);
	}
}
//function solveAjaxProblem ends here

// function to populate values into elements
function executeCallingFunctions(element)
{
	if(element.type=='radio' || element.type=='checkbox')
	{
	   	var eleVal=elementNameValueArray[element.name];
		var radiogrp=document.getElementsByName(element.name);
		for(var j = 0; j < radiogrp.length; j++)
		{
			if(radiogrp[j].value==eleVal)
			{	
				radiogrp[j].checked=true;
				if(radiogrp[j].onclick!=null)
				{
					radiogrp[j].onclick();
				}
			}
			else
			{
				//alert("in else radio value incorrect");
	    	}
		 }
	}
    else if(element.type=='select-one')
	{
	    var eleVal=elementNameValueArray[element.name];
		element.value=eleVal;
		//alert(element.onchange);
		if(element.onchange!=null)
		{
			var onChangeFunc=""+element.onchange;
			var func = onChangeFunc.substring(onChangeFunc.indexOf("{")+1,onChangeFunc.indexOf("}"));
			var funcParameter=func.substring(func.indexOf("(")+1,func.indexOf(")"))
			//	alert('funcParameter'+funcParameter);
			var obj= new Object();
			if(funcParameter!="")
			{
				if(funcParameter.indexOf("value")!=-1)
				{
					obj="document.getElementsByName('"+element.name+"')[0].value";
					func=func.replace(funcParameter,obj);
				}
				else
				{
					 obj="document.getElementsByName('"+element.name+"')[0]";
					 func=func.replace(funcParameter,obj);
			    }
				
				//alert('func '+func);
			}
			else
			{
				//	alert('no onchange parameters');
			}
			eval(func);
		}
	}	
	else
	{
		//alert('element.name '+element.name+' val '+elementNameValueArray[element.name] );
		var eleVal=elementNameValueArray[element.name];
		element.value=eleVal;
    }
}


// function to handle multiple add components.also handles attachment component in multiple add
function solveMultipleAddProblem()
{
	for(var i=0;i<multipleAddArray.length;i++)
	{
		var elementName='ISMAV_DATA'+multipleAddArray[i];
	//	alert('element name '+elementName);
		var multiAddValEncoded=elementNameValueArray[elementName];
	//	alert('multiAddValEncoded '+multiAddValEncoded);
		var multiAddVal=decodeBase64(multiAddValEncoded);
	//	alert('multiAddVal '+multiAddVal);
		document.getElementById(multipleAddArray[i]).style.display='';
		document.getElementById(multipleAddArray[i]).innerHTML=""+multiAddVal;
	//	alert('inner '+document.getElementById(multipleAddArray[i]).innerHTML);
		solveMultipleAddAttachmentProblem(multipleAddArray[i]);
	}
}

/**function to populate attachment ids in multiple add after the page is loaded from drafts folder */
function solveMultipleAddAttachmentProblem(divName)
{
//	alert(divName);
	var multipleAddTable=document.getElementById(divName).firstChild;
	var noOfRows=multipleAddTable.rows.length;
	var tableId=multipleAddTable.id;
	var attachmentNamesArray=divName.split('_ATT_');
	if(attachmentNamesArray.length>1)
	{
		 for(var j=1;j<noOfRows;j++)
		 {
			var attachmentIds='';;
		   	for(var k=1;k<attachmentNamesArray.length;k++)
		   	{
		   		var attachmentKey=tableId+"_"+j+"_"+attachmentNamesArray[k];
		   //	 	alert('attch key '+attachmentKey);
		   	 	var attachmentId='';
		   	// 	alert('alert '+elementNameValueArray[attachmentKey]);
		   	 	if(elementNameValueArray[attachmentKey])
		   	 	{
		   	 	 	attachmentId=elementNameValueArray[attachmentKey];
		   	 	}
				if(attachmentId!='')
		   		{
		   			attachmentIds=attachmentIds+attachmentId;
				}
				if(k!=attachmentNamesArray.length-1)
				{
					attachmentIds=attachmentIds+",";
				}
				//alert('attachment key '+attachmentIds);  
		   	}
		 //  	alert('attachment key out '+attachmentIds); 
		   	var tableRow=document.getElementById(tableId).rows[j];
		   	var hiddenfield=(tableRow.id).substring((tableRow.id).indexOf('row')+3);
		//   	alert('hidden '+hiddenfield);
		  	 		
		   	var lastCellIdLogical='attachment'+hiddenfield;
		   	if(document.getElementsByName(lastCellIdLogical)[0])
		   	{
		//   		alert('already present');
		   	}
		   	else
		   	{
		   		document.getElementById(divName).innerHTML+="<INPUT type='hidden' name='attachment" + hiddenfield + "' id='attachment" + hiddenfield + counter +"' value='" + attachmentIds + "'/>";	;
		  
		   	}
		 }
	 }
}

// function to handle normal attachment components on the page
function solveAttachmentProblem()
{
	//alert('in solve attachment');
	for(var i=0;i<attachmentNameArray.length;i++)
	{
		var attachmentNameInXml='interimSaveAttachment'+attachmentNameArray[i];
		//alert(elementNameAttachmentArray[attachmentNameInXml]);
		if(elementNameAttachmentArray[attachmentNameInXml]!=undefined)
		{
			var attachmentTableName='myTable'+attachmentNameArray[i];
			//alert('attachment id from doc '+ elementNameAttachmentArray[attachmentNameInXml]);
			var attachmentTableEle=document.getElementsByName(attachmentTableName)[0];
			var noOfCells=attachmentTableEle.rows[0].cells.length;
		//	alert('no of cells before'+noOfCells);
			//alert(attachmentTableEle.rows[0].cells[noOfCells-1].firstChild.innerHTML);
			attachmentTableEle.rows[0].insertCell(noOfCells).innerHTML = "<INPUT type='hidden' id='attchmentIdCell' name='" +attachmentNameArray[i]+ "' value='" + elementNameAttachmentArray[attachmentNameInXml]+ "' />";	
			//alert('no id '+ attachmentTableEle.rows[0].cells[noOfCells].innerHTML);
			attachmentTableEle.rows[0].cells[noOfCells].style.display='none';
			
			xmlHttp=GetXmlHttpObject();
			var url='ifms.htm?actionFlag=interimSaveAttachmentPopulate';
			url=url+"&"+attachmentNameInXml+"="+elementNameAttachmentArray[attachmentNameInXml];
	     //	alert('attach url '+url);
			xmlHttp.onreadystatechange=function()
			{
					if (xmlHttp.readyState == 4) 
					{     
						if (xmlHttp.status == 200) 
						{
							//alert("i am back");
							var XMLDoc=populateAttachmentForEdit();	
						}
					}
			}
			xmlHttp.open("POST",encodeURI(url),false);
			xmlHttp.send(null);
		}
	}
}

// function to populate address component. depends on editAddress function of address.js
 function populateAddressInterimSave(addrName)
 {
    editFlag=true;
    var addressParams = new Array(); 
    var area; 
    var cityCode;
    var socBuildName;
    var street;
    var districtCode;
    var faliyu;
    var talukaCode;
    var villageCode;
    var country;
    var state;
    try
    {
		addressParams[26]="cmbCountry"+addrName;
			
		country	= elementNameValueArray[addressParams[26]];	
		//alert('country '+country);
		
		var countryArr	=  document.getElementsByName(addressParams[26]);
		
		if(countryArr!= null)
		{
		    if(countryArr[0]!= null)
		    {
			     if(country!= null)
			     {
			    	countryArr[0].value=country;
			    	//executeCallingFunctions(countryArr[0]);
			     }
		    }
		} 
		
		if(countryArr[0].value==1)
		{
			addressParams[27]="cmbState"+addrName;
			
		}
		else
		{
			addressParams[27]="txtState"+addrName;	
		//	document.getElementsByName('cmbState'+addrName)[0].style.display='none';
		//	document.getElementsByName('tdCmbState'+addrName)[0].style.display='none';
			document.getElementsByName('tdTxtState'+addrName)[0].style.display='';
			document.getElementsByName(addressParams[27])[0].style.display='';
		}
		state = elementNameValueArray[addressParams[27]];	
		
		var stateArr	=  document.getElementsByName(addressParams[27]);
		if(stateArr!= null)
		{
		    if(stateArr[0]!= null)
		    {
		     if(state!= null)
		     {
		    	stateArr[0].value=state;
		     }
		     else
		     {
		     	stateArr[0].value="";
		     }
		    }
		} 

		addressParams[0]="rdoAddress"+addrName;
		//alert('a '+addressParams[0]);
					
		var rdoAddressArr = document.getElementsByName(addressParams[0]);
	    var cityOrVillage=elementNameValueArray[addressParams[0]];
	   // alert(cityOrVillage);
		var contextPath=document.getElementById('addrContextPath'+addrName).value;
	    if(cityOrVillage=='City')
	    {
	    	rdoAddressArr[2].readOnly=true;
			addressParams[1]="txtCityHouseName"+addrName;
			addressParams[2]="txtSocietyName"+addrName;
			addressParams[3]="txtStreet"+addrName;
			addressParams[4]="txtArea"+addrName;
			addressParams[5]="cmbCity"+addrName;
			addressParams[6]="txtareaCityOtherDetails"+addrName;
			addressParams[7]="cmbCityLandmark"+addrName;
			addressParams[8]="txtCityPincode"+addrName;
			area = elementNameValueArray[addressParams[4]];
			socBuildName= elementNameValueArray[addressParams[2]];
			street   = elementNameValueArray[addressParams[3]];
			var houseName   = elementNameValueArray[addressParams[1]];
			cityCode=elementNameValueArray[addressParams[5]];
			var impLandmark = elementNameValueArray[addressParams[7]];
			var otherDetails= elementNameValueArray[addressParams[6]];
			var pincode     = elementNameValueArray[addressParams[8]];	
			
			 var cityHouseNameArr   = document.getElementsByName(addressParams[1]);
		     var socNameArr         = document.getElementsByName(addressParams[2]);
			 var streetArr          = document.getElementsByName(addressParams[3]);
			 var areaArr            = document.getElementsByName(addressParams[4]);
			 var cityArr            = document.getElementsByName(addressParams[5]);
			 var cityOdArr          = document.getElementsByName(addressParams[6]);
			 var cityLandmarkArr    = document.getElementsByName(addressParams[7]);
			 var cityPincodeArr     = document.getElementsByName(addressParams[8]);
			
			 
				rdoAddressArr[0].click();
				
			
				if(cityHouseNameArr!= null)
				 {
				    if(cityHouseNameArr[0]!= null)
				    {
				     if(houseName!= null)
				     {
				    cityHouseNameArr[0].value=houseName;
				     }
				     else
				     {
				     cityHouseNameArr[0].value="";
				     }
				    }
				 } 
			    if(socNameArr!= null)
					 {
					   if(socNameArr[0]!=null)
					   {
					      if(socBuildName!=null)
					      {
					    socNameArr[0].value=socBuildName;
					      }
					      else
					      {
					    socNameArr[0].value="";  
					      }
					    }
		 		    }
			    if(streetArr!= null)
				 {
				   if(streetArr[0]!= null)
				   {
				     if(street!= null)
				     {
				    streetArr[0].value=street;
				     }
				     else
				     {
				   streetArr[0].value="";  
				     }
				    }
				 }
			   if(areaArr!= null)
				 {
				    if(areaArr[0]!= null)
				    {
				      if(area!=null)
				      {
				    areaArr[0].value=area;
				      }
				      else
				      {
				    areaArr[0].value="";  
				      }
				    }
				 }
			     if(cityArr!= null)
		 			{
					   if(cityArr[0]!= null)
					   {
					   var options = cityArr[0].options;
					   if(options.length == 1)
					  {
					     populateFirstLevel('cmbCity'+addrName,'getAllCity',contextPath,'districtCode','',addrName);
					 
					   }
					     for(var c=0;c<options.length;c++)
					     {
					        if(options[c].value == cityCode)
					        {
					         options[c].selected="selected";
					        }
					     }
					   }
		 			} 	 
				  if(cityOdArr!= null)
				 {
				    if(cityOdArr[0]!= null)
				    {
				       if(otherDetails!= null)
				       {
				    cityOdArr[0].value=otherDetails;
				       }
				       else
				       {
				    cityOdArr[0].value="";   
				       }
				    }
				 } 
				   if(cityPincodeArr!= null)
		 		{
				    if(cityPincodeArr[0]!= null)
				    {
				      if(pincode!=null)
				      {
				    cityPincodeArr[0].value=pincode;
				      }
				      else
				      {
				    cityPincodeArr[0].value="";  
				      }
				    }
		 		}
		 		   if(cityLandmarkArr!= null)
		 			{
					    if(cityLandmarkArr[0]!= null)
					    {
					   var options = cityLandmarkArr[0].options;
					      if(options.length == 1)
					      {
					      populateLandmarks('cmbCityLandmark'+addrName,'getAllLandmark',contextPath,'landmarkType','CityLandmarks',cityCode,addrName);
					      }
					     for(var cl=0;cl<options.length;cl++)
					     {
					        if(options[cl].value == impLandmark)
					        {
					         options[cl].selected="selected";
					        }
					     }
					    }
		       }
	 }
	 else if(cityOrVillage=='Village')
	 {
	 		rdoAddressArr[2].readOnly=true;
	 		addressParams[9]="txtVillageHouseName"+addrName;
			addressParams[10]="txtFaliyu"+addrName;
			addressParams[11]="cmbVillage"+addrName;
			addressParams[12]="cmbTaluka"+addrName;
			addressParams[13]="cmbDistrict"+addrName;
			addressParams[14]="cmbVillageLandmark"+addrName;
			addressParams[15]="txtareaVillageOtherDetails"+addrName;
			addressParams[16]="txtVillagePincode"+addrName;
			var houseName = elementNameValueArray[addressParams[9]];
			var faliyu = elementNameValueArray[addressParams[10]];
			var talukaCode   = elementNameValueArray[addressParams[12]];
			var impLandmark = elementNameValueArray[addressParams[14]];
			var otherDetails= elementNameValueArray[addressParams[15]];
			var pincode     = elementNameValueArray[addressParams[16]];	
			var	villageCode	= elementNameValueArray[addressParams[11]];	
		    var districtCode= elementNameValueArray[addressParams[13]];		
			 var villageHouseNameArr= document.getElementsByName(addressParams[9]);
			 var faliyuArr          = document.getElementsByName(addressParams[10]); 
			 var villageArr         = document.getElementsByName(addressParams[11]); 
			 var talukaArr          = document.getElementsByName(addressParams[12]); 
			 var districtArr        = document.getElementsByName(addressParams[13]); 
			 var villageLandmarkArr = document.getElementsByName(addressParams[14]); 
			 var villageOdArr       = document.getElementsByName(addressParams[15]); 
			 var villagePincodeArr  = document.getElementsByName(addressParams[16]);
			rdoAddressArr[1].click();
				  if(villageHouseNameArr!= null)
				 {
				   if(villageHouseNameArr[0]!= null)
				   {
				      if(houseName!= null)
				       {
				    villageHouseNameArr[0].value=houseName;
				       }
				       else
				       {
				    villageHouseNameArr[0].value="";   
				       }
				    }
				 }
				  if(faliyuArr!= null)
				 {
				   
				   if(faliyuArr[0]!= null)
				   {
				     if(faliyu!= null)
				     {
				     faliyuArr[0].value=faliyu;
				     }
				     else
				     {
				     faliyuArr[0].value="";
				     }
				    }
				 }
		 		if(districtArr!= null)
		 		{
				    if(districtArr[0]!= null)
				    {
				    var options = districtArr[0].options;
				        if(options.length == 1)
				        {
				        populateFirstLevel('cmbDistrict'+addrName,'getAllDistrict',contextPath,'stateId','',addrName);
				        }
					       for(var d=0;d<options.length;d++)
						     {
						        if(options[d].value == districtCode)
						        {
						         options[d].selected="selected";
						        }
						     }
				    }
		 		}
		   		 if(talukaArr!= null)
					 {
					    if(talukaArr[0]!= null)
					    {
					     var options = talukaArr[0].options;
					    //  if(options.length == 1)
					    //  {
					       populateNextLevel('cmbDistrict'+addrName,'cmbTaluka'+addrName,'getAllTaluka',contextPath,'districtCode',addrName)
					  //    }
						       for(var t=0;t<options.length;t++)
						     {
						        if(options[t].value == talukaCode)
						        {
						         options[t].selected="selected";
						        }
						     }
					     }
					 }
		     
		       if(villageArr!= null)
				 {
				    if(villageArr[0]!= null)
				    {
				    var options = villageArr[0].options;
				      // if(options.length == 1)
					  //    {
					       populateNextLevel('cmbTaluka'+addrName,'cmbVillage'+addrName,'getAllVillage',contextPath,'talukaCode',addrName)
					  //    }
				       for(var v=0;v<options.length;v++)
					     {
					        if(options[v].value == villageCode)
					        {
					         options[v].selected="selected";
					        }
					     }
				    }
				 }
				 if(villageLandmarkArr!= null)
				 {
				    if(villageLandmarkArr[0]!= null)
				    {
				    var options = villageLandmarkArr[0].options;
				       if(options.length == 1)
					      {
					      populateLandmarks('cmbVillageLandmark'+addrName,'getAllLandmark',contextPath,'landmarkType','VillageLandmarks');
					      }
					     for(var vl=0;vl<options.length;vl++)
						     {
						        if(options[vl].value == impLandmark)
						        {
						         options[vl].selected="selected";
						        }
						     }
				    }
				 }
				    if(villageOdArr!= null)
				 {
				   if(villageOdArr[0]!= null)
				   {
				     if(otherDetails!=null)
				     {
				    villageOdArr[0].value=otherDetails;
				     }
				     else
				     {
				    villageOdArr[0].value=""; 
				     }
				    }
				 }
				     if(villagePincodeArr!= null)
				 {
				    if(villagePincodeArr[0]!= null)
				    {
				     if(pincode!= null)
				     {
				    villagePincodeArr[0].value=pincode;
				     }
				     else
				     {
				    villagePincodeArr[0].value=""; 
				     }
				    }
				 }
	}
	else if(cityOrVillage=='OtherRadioAddress')
	{
		rdoAddressArr[0].disabled=true;
		rdoAddressArr[1].disabled=true;
		rdoAddressArr[2].disabled=false;
		addressParams[17]="txtOtherHouseName"+addrName;
		addressParams[18]="txtOtherSocietyName"+addrName;
		addressParams[19]="txtOtherArea"+addrName;
		addressParams[21]="txtOtherDistrict"+addrName;
		addressParams[22]="txtOtherTaluka"+addrName;
		addressParams[23]="txtOtherCityVillage"+addrName;
		addressParams[24]="txtOtherPincode"+addrName;
		var area             = elementNameValueArray[addressParams[19]];
		var socBuildName     = elementNameValueArray[addressParams[18]];
		var houseName		 = elementNameValueArray[addressParams[17]];
		var districtName     = elementNameValueArray[addressParams[21]];
		var talukaName       = elementNameValueArray[addressParams[22]];
		var cityVillage		 = elementNameValueArray[addressParams[23]];
		var pincode 		 = elementNameValueArray[addressParams[24]];
		var otherHouseNameArr   = document.getElementsByName(addressParams[17]); 
		var otherSocietyNameArr = document.getElementsByName(addressParams[18]); 
		var otherAreaArr        = document.getElementsByName(addressParams[19]); 
 		var txtdistrictArr      = document.getElementsByName(addressParams[21]); 
 		var otherTalukaArr      = document.getElementsByName(addressParams[22]); 
 		var otherCVArr          = document.getElementsByName(addressParams[23]); 
 		var otherPincodeArr     = document.getElementsByName(addressParams[24]); 
 		
 	    rdoAddressArr[2].click();
        if(otherHouseNameArr!= null)
		{
		   if(otherHouseNameArr[0]!= null)
		   {
		      if(houseName!= null)
		      {
		 		   otherHouseNameArr[0].value=houseName;
		      }
		      else
		      {
		    	   otherHouseNameArr[0].value="";   
		      }
		    }
		} 
		if(otherSocietyNameArr!= null)
		{
		   if(otherSocietyNameArr[0]!= null)
		   {
		      if(socBuildName!= null)
		      {
		 		   otherSocietyNameArr[0].value=socBuildName;
		      }
		      else
		      {
		    	   otherSocietyNameArr[0].value="";   
		      }
		    }
		} 
		if(otherAreaArr!= null)
		{
		   if(otherAreaArr[0]!= null)
		   {
		      if(area!= null)
		      {
		 		   otherAreaArr[0].value=area;
		      }
		      else
		      {
		    	   otherAreaArr[0].value="";   
		      }
		    }
		} 
		if(txtdistrictArr!= null)
		{
		   if(txtdistrictArr[0]!= null)
		   {
		      if(districtName!= null)
		      {
		 		   txtdistrictArr[0].value=districtName;
		      }
		      else
		      {
		    	   txtdistrictArr[0].value="";   
		      }
		    }
		}
		if(otherTalukaArr!= null)
		{
		   if(otherTalukaArr[0]!= null)
		   {
		      if(talukaName!= null)
		      {
		 		   otherTalukaArr[0].value=talukaName;
		      }
		      else
		      {
		    	   otherTalukaArr[0].value="";   
		      }
		    }
		}
		if(otherCVArr!= null)
		{
		   if(otherCVArr[0]!= null)
		   {
		      if(cityVillage!= null)
		      {
		 		   otherCVArr[0].value=cityVillage;
		      }
		      else
		      {
		    	   otherCVArr[0].value="";   
		      }
		    }
		}
		if(otherPincodeArr!= null)
		{
		   if(otherPincodeArr[0]!= null)
		   {
		      if(pincode!= null)
		      {
		 		   otherPincodeArr[0].value=pincode;
		      }
		      else
		      {
		    	   otherPincodeArr[0].value="";   
		      }
		    }
		}
	}
	else
	{
		closeAddress(addrName);
	}
   }catch(ex)
   {

   }finally
   {
   editFlag=false;
   }	 
}
 
 
 // function called after the response function of any ajax call in ajax calling chain
 function afterReadyStateChanged()
 {
 	
 	 if(isInterimSave==true)
				{
					ajaxIncrement++;
				  	solveAjaxProblem(firstRow,ajaxArrayKey);
				}
 }
 