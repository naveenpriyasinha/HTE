function filterDesig()
{
	var desig = document.getElementById("cmbDesig").value;
	
	var url = "ifms.htm?actionFlag=loadDdoEmpDeselction&Designation="+desig;
	
	self.location.href = url; 
}
function dcpsDDOEmpDeselect()
{
	showProgressbar();
	var Emp_Id=" ";
	var DatesOfDeselection = "" ;
	var ReasonForDeselection = "" ;
	var Remarks = "" ;
	var orderNo = "" ;
	var orderDate = "" ;
	var totalEmployees = document.getElementById("hdnCounter").value ;
	var finalSelectedEmployee=0;
	var lBlDDOAsstOrNot = false;
	var lIntIndexOfDDOAsst;
	var hidName;
	
	var reasonForDeselectionValdn = "";
	var remarksForDeselectionValdn = "";
 	
	for(var k=1;k<=totalEmployees;k++)
	{
		if(document.getElementById("checkbox"+k).checked)
		{  
			if(!chkEmpty(document.getElementById("txtDateOfDeselection"+k),'Date Of Relieving') || 
					!chkEmpty(document.getElementById("cmbReason"+k),'Reason for Relief') ||
					!chkEmpty(document.getElementById("txtAuthorityLetterNo"+k),'Order No') ||
					!chkEmpty(document.getElementById("txtAuthorityLetterDate"+k),'Order Date')
					
			)
					//|| !chkEmpty(document.getElementById("txtRemarks"+k),'Remarks/Deputation Location'))
			{
				hideProgressbar();
				return false;
			}
			
			reasonForDeselectionValdn = document.getElementById("cmbReason"+k).value.trim();
			remarksForDeselectionValdn = document.getElementById("txtRemarks"+k).value.trim();
				
			if(reasonForDeselectionValdn == 700053)
				{
					if(remarksForDeselectionValdn == "")
						{
							hidName = document.getElementById("hidName"+k).value.trim();
							alert('Please enter deputation location for '+ hidName);
							hideProgressbar();
							return false;
						}
				}
			
			finalSelectedEmployee = k ;	
		}
	}

	if(finalSelectedEmployee == 0)
	{
		alert('Please select at least one employeee for deselection');
		hideProgressbar();
		return false; 
	}
	
	for(var k=1;k<=totalEmployees;k++)
	{
		if(document.getElementById("checkbox"+k).checked)
		{
			if(document.getElementById("hidDdoAsstOrNotStatus"+k) != null)
				{
				if(document.getElementById("hidDdoAsstOrNotStatus"+k).value != null)
					{
						if(document.getElementById("hidDdoAsstOrNotStatus"+k).value.trim() == 1)
							{
								lBlDDOAsstOrNot = true;
								lIntIndexOfDDOAsst = k;
							}
					}
				}
		}
	}
	
	if(lBlDDOAsstOrNot)
		{
			hidName = document.getElementById("hidName"+lIntIndexOfDDOAsst).value.trim();
			alert('Employee ' + hidName + ' is a DDO Assistant. He/She cannot be deselected.');
			hideProgressbar();
			return false;
		}
	
	
	for(var i=1;i<=totalEmployees;i++)
	{
		if(document.getElementById("checkbox"+i).checked)
		{
			if(i==finalSelectedEmployee)
			{
				Emp_Id = Emp_Id + document.getElementById("checkbox"+i).value;
				DatesOfDeselection = DatesOfDeselection + document.getElementById("txtDateOfDeselection"+i).value.trim();
				ReasonForDeselection = ReasonForDeselection +  document.getElementById("cmbReason"+i).value.trim();
				Remarks = Remarks + document.getElementById("txtRemarks"+i).value.trim();
				orderNo = orderNo +  document.getElementById("txtAuthorityLetterNo"+i).value.trim();
				orderDate = orderDate + document.getElementById("txtAuthorityLetterDate"+i).value.trim();
			}
			else
			{
				Emp_Id = Emp_Id + document.getElementById("checkbox"+i).value.trim() + "~";
				DatesOfDeselection = DatesOfDeselection + document.getElementById("txtDateOfDeselection"+i).value.trim()+ "~";
				ReasonForDeselection = ReasonForDeselection +  document.getElementById("cmbReason"+i).value.trim()+ "~";
				Remarks = Remarks + document.getElementById("txtRemarks"+i).value.trim()+ "~";
				orderNo = orderNo +  document.getElementById("txtAuthorityLetterNo"+i).value.trim() + "~";
				orderDate = orderDate + document.getElementById("txtAuthorityLetterDate"+i).value.trim() + "~";
			}
		}
	}	

	uri = "ifms.htm?actionFlag=ddoEmpDeselect&Emp_Id="+Emp_Id+"&DatesOfDeselection="+DatesOfDeselection
		+"&ReasonForDeselection="+ReasonForDeselection+"&Remarks="+Remarks+"&orderNo="+orderNo+"&orderDate="+orderDate;
	
	xmlHttp=GetXmlHttpObject();

	if (xmlHttp==null)
	{
		alert ("Your browser does not support AJAX!");
		hideProgressbar();
		return;
	} 

	xmlHttp.onreadystatechange=function()
	{
		if (xmlHttp.readyState==4)
		{ 
			if(xmlHttp.status==200)
			{
				XMLDoc = xmlHttp.responseXML.documentElement;
				var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
				
				var successFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
				var successFlagForArrearsApproval = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;
											
					if (successFlag=='true') {
						
						if(successFlagForArrearsApproval == 'true')
						{
							alert("Employees are deselected.");
							//self.location.reload(true);
							hideProgressbar();
							var desig = document.getElementById("cmbDesig").value;
							self.location.href = "ifms.htm?actionFlag=loadDdoEmpDeselction&elementId=700033&Designation="+desig;
						}
						else
						{
							alert('The arrears of some of the selected employees have not been approved for this year. Please deselect those employees.');
							hideProgressbar();
						}
					}
					else
						{
							hideProgressbar();
						}
			}
		}
		
	};
	xmlHttp.open("POST",uri,false);
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(uri);
}
function showEmpSelectionList()
{
	var txtSevaarthId = document.getElementById("txtSevaarthId").value;
	var txtEmployeeName = document.getElementById("txtEmployeeName").value;
	if(txtSevaarthId == "" && txtEmployeeName == "" )
		{
			alert('Please enter search criteria');
			return;
		}
	var url ="ifms.htm?actionFlag=loadDdoEmpSelection&elementId=700032&txtSevaarthId="+txtSevaarthId+"&txtEmployeeName="+txtEmployeeName+"&requestForSearch=Yes";
	showProgressbar('Please Wait<br>Your request is in progress...');
	document.DDOEmpSelect.action = url ;
	enableAjaxSubmit(true);
	document.DDOEmpSelect.submit();
}

function showAllForSelection()
{
	var url = "ifms.htm?actionFlag=loadDdoEmpSelection&elementId=700032";
	self.location.href = url ;
}

function showEmpDeSelectionList()
{
	var txtSevaarthId =  document.getElementById("txtSevaarthId").value.trim();
	var txtEmployeeName = document.getElementById("txtEmployeeName").value.trim();
	if(txtSevaarthId == "" && txtEmployeeName == "")
		{
			alert('Please enter search criteria');
			return;
		}
	var url ="ifms.htm?actionFlag=loadDdoEmpDeselction&Designation=-2&elementId=700033&txtSevaarthId="+txtSevaarthId+"&txtEmployeeName="+txtEmployeeName+"&fromSearch=Yes";
	showProgressbar('Please Wait<br>Your request is in progress...');
	document.DDOEmpDeselect.action = url ;
	enableAjaxSubmit(true);
	document.DDOEmpDeselect.submit();
}

function showAllForDeSelection()
{
	var url = "ifms.htm?actionFlag=loadDdoEmpDeselction&Designation=-2&elementId=700033";
	self.location.href = url ;
}

function dcpsDDOEmpSelect()
{
	alert("Welcmeeee");
	showProgressbar();
	var ddoCode = document.getElementById("ddoCode").value;
	var Emp_Id=" ";
	var DatesOfSelection = "" ;
	var totalEmployees = document.getElementById("hdnCounter").value ;
	var finalSelectedEmployee=0;
	var offices = "";
	var posts = "";
	var billGroups = "";

	for(var k=1;k<=totalEmployees;k++)
	{
		if(document.getElementById("checkbox"+k).checked)
		{  
			if(!chkEmpty(document.getElementById("txtDateOfSelection"+k),'Date Of Selection'))
			{
				hideProgressbar();
				return false;
			}
			if(document.getElementById("cmbOffice"+k).value == -1)
			{
				alert('Please select office for ' + document.getElementById("hidName"+k).value);
				hideProgressbar();
				return false;
			}
			
			if(document.getElementById("cmbPost"+k).value == -1)
			{
				alert('Please select post for ' + document.getElementById("hidName"+k).value);
				hideProgressbar();
				return false;
			}
			
			if(document.getElementById("cmbBillGroup"+k) != null)
				{
					if(document.getElementById("cmbBillGroup"+k).style.display == 'inline')
						{
							if(document.getElementById("cmbBillGroup"+k).value == -1)
								{
									alert('Please select new bill group for ' + document.getElementById("hidName"+k).value);
									hideProgressbar();
									return false;
								}
						}
				}
			
			finalSelectedEmployee = k ;	
		}
	}

	if(finalSelectedEmployee == 0)
	{
		alert('Please select at least one employeee for selection');
		hideProgressbar();
		return false; 
	}
	
	lArrPosts = new Array();
	
	for(var k=1;k<=totalEmployees;k++)
	{
		if(document.getElementById("checkbox"+k).checked)
		{  
			lArrPosts.push(document.getElementById("cmbPost"+k).value);
		}
	}
	
	if(arrHasDupes(lArrPosts))
		{
			alert('You can not select same post for different employees. Please select different posts');
			hideProgressbar();
			return false;
		}
	
	for(var i=1;i<=totalEmployees;i++)
	{
		if(document.getElementById("checkbox"+i).checked)
		{
			if(i==finalSelectedEmployee)
			{
				Emp_Id = Emp_Id + document.getElementById("checkbox"+i).value;
				DatesOfSelection = DatesOfSelection + document.getElementById("txtDateOfSelection"+i).value;
				offices = offices + document.getElementById("cmbOffice"+i).value;
				posts = posts + document.getElementById("cmbPost"+i).value;
				if(document.getElementById("cmbBillGroup"+i).style.display != 'none')
					{	
						billGroups = billGroups + document.getElementById("cmbBillGroup"+i).value;
					}
				else
					{
						billGroups = billGroups + document.getElementById("hidbillGroupId"+i).value;
					}
			}
			else
			{
				Emp_Id = Emp_Id + document.getElementById("checkbox"+i).value + "~";
				DatesOfSelection = DatesOfSelection + document.getElementById("txtDateOfSelection"+i).value+ "~";
				offices = offices + document.getElementById("cmbOffice"+i).value + "~";
				posts = posts + document.getElementById("cmbPost"+i).value + "~";
				if(document.getElementById("cmbBillGroup"+i).style.display != 'none')
					{
						billGroups = billGroups + document.getElementById("cmbBillGroup"+i).value + "~";
					}
				else
					{
						billGroups = billGroups + document.getElementById("hidbillGroupId"+i).value + "~";
					}
			}
		}
	}	
	
	uri = "ifms.htm?actionFlag=ddoEmpSelect&Emp_Id="+Emp_Id+"&ddoCode="+ddoCode+"&DatesOfSelection="+DatesOfSelection+"&offices="+offices+"&posts="+posts+"&billGroups="+billGroups;
	
	xmlHttp=GetXmlHttpObject();

	if (xmlHttp==null)
	{
		alert ("Your browser does not support AJAX!");
		hideProgressbar();
		return;
	} 

	xmlHttp.onreadystatechange=function()
	{
		if (xmlHttp.readyState==4)
		{ 
			if(xmlHttp.status==200)
			{
				XMLDoc = xmlHttp.responseXML.documentElement;
				var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
				var successFlag = XmlHiddenValues[0].childNodes[0].text;
				if (successFlag=='true')
				{
					alert("Employees are selected.");
					hideProgressbar();
					//self.location.reload(true);
					self.location.href="ifms.htm?actionFlag=loadDdoEmpSelection&elementId=700032";
				}
			}
		}
	};
	xmlHttp.open("POST",uri,false);
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(uri);
}


function getVacantPostsInOffice(counter)
{
	var cmbOffice  = document.getElementById("cmbOffice"+counter).value;
	if(cmbOffice != -1)
	{
		var uri="ifms.htm?actionFlag=getVacantPostsInOffice&cmbOffice="+cmbOffice;
		
		xmlHttp=GetXmlHttpObject();

	   if (xmlHttp==null)
	   {
		   alert ("Your browser does not support AJAX!");
		   return;
	   }  
	   
	  // alert('comes before showProgressbar');
	   showProgressbar();
	   
	   xmlHttp.onreadystatechange=function()
	   {
		   if (xmlHttp.readyState==4)
			{ 
				if(xmlHttp.status==200)
				{
					hideProgressbar();
					XMLDoc = xmlHttp.responseXML.documentElement;
					var XmlHiddenValues = XMLDoc.getElementsByTagName('item');
					document.getElementById('cmbPost'+counter).options.length = 0;
					
					var optnStart = document.createElement("OPTION");
					optnStart.value = "-1";
					optnStart.text = "--Select--";
					document.getElementById("cmbPost"+counter).options.add(optnStart);
					
					for(var j = 0;j<XmlHiddenValues.length;j++)
					{
						var postName =  XmlHiddenValues[j].childNodes[0].firstChild.nodeValue;
						var postCode =  XmlHiddenValues[j].childNodes[1].firstChild.nodeValue;
						var optn = document.createElement("OPTION");
						optn.value = postCode;
						optn.text = postName;
						document.getElementById("cmbPost"+counter).options.add(optn);
					}
					
					if(document.getElementById("txtBillGroup"+counter) != null)
						{
							document.getElementById("txtBillGroup"+counter).value = "";
						}
					if(document.getElementById("cmbBillGroup"+counter) != null)
						{
							document.getElementById("cmbBillGroup"+counter).value = -1;
						}
					document.getElementById("txtGROrder"+counter).value = "";
					
				}
			}
	   };
	   xmlHttp.open("POST",uri,true);
	   xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	   xmlHttp.send(uri);
	}
}

function chkDtLaterThanDeSelectionDt(dateCntrl,counter)
{
	var selectionDate = dateCntrl.value;
	if(selectionDate != "")
	{
		var dcpsEmpId = document.getElementById("checkbox"+counter).value;
		var uri="ifms.htm?actionFlag=chkDtLaterThanDeSelectionDt";
		var url="selectionDate="+selectionDate+"&dcpsEmpId="+dcpsEmpId;
		
		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function(myAjax) {
						dataStChangedForChkDtLaterThanDeSelectionDt(myAjax,counter);
					},
			        onFailure: function(){ alert('Something went wrong...');} 
			          } );
	}
}

function dataStChangedForChkDtLaterThanDeSelectionDt(myAjax,counter)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lBlFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	var deselectionDate = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;
	if(lBlFlag == 'false')
	{
		alert('Selection date must be after Deselection date - ' + deselectionDate + ' .');
		document.getElementById("txtDateOfSelection"+counter).value = "";
		return;
	}
	else
	{
		return;
	}
}

function getBGAndGRForPost(rowNum)
{
	var postId = document.getElementById("cmbPost"+rowNum).value;
	if(postId != -1)
		{
			var uri="ifms.htm?actionFlag=getBGAndGRForPost";
			var url="postId="+postId;
			
			var myAjax = new Ajax.Request(uri,
				       {
				        method: 'post',
				        asynchronous: false,
				        parameters:url,
				        onSuccess: function(myAjax) {
							getDataStateChangedForGetBGAndGRForPost(myAjax,rowNum);
						},
				        onFailure: function(){ alert('Something went wrong...');} 
				          } );
		}
}

function getDataStateChangedForGetBGAndGRForPost(myAjax,rowNum)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	
	var hidbillGroupId = "";
	var billGroupDesc = "";
	if(XmlHiddenValues[0].childNodes[0].firstChild != null)
		{
			hidbillGroupId = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
			document.getElementById("hidbillGroupId"+rowNum).value = hidbillGroupId;
		}
	if(XmlHiddenValues[0].childNodes[1].firstChild != null)
		{
			billGroupDesc = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;	
			document.getElementById("txtBillGroup"+rowNum).value = billGroupDesc;
			document.getElementById("txtBillGroup"+rowNum).readOnly = true;
			document.getElementById("cmbBillGroup"+rowNum).style.display = 'none';
			document.getElementById("labelBillGroup").style.display = 'none';
			document.getElementById("txtBillGroup"+rowNum).style.display = 'inline';
		}
	else
		{
			document.getElementById("cmbBillGroup"+rowNum).style.display = 'inline';
			document.getElementById("labelBillGroup").style.display = '';
			document.getElementById("txtBillGroup"+rowNum).style.display = 'none';
		}
	
	var hidGROrderId = "";
	var txtGROrder = "";
	if(XmlHiddenValues[0].childNodes[3].firstChild != null)
		{
			txtGROrder = XmlHiddenValues[0].childNodes[3].firstChild.nodeValue;
			document.getElementById("txtGROrder"+rowNum).value = txtGROrder;
			document.getElementById("txtGROrder"+rowNum).readOnly = true;
		}
	else
		{
			document.getElementById("txtGROrder"+rowNum).value = "";
		}
	
	if(XmlHiddenValues[0].childNodes[2].firstChild != null)
		{
			
			hidGROrderId = XmlHiddenValues[0].childNodes[2].firstChild.nodeValue;
			document.getElementById("hidGROrderId"+rowNum).value = hidGROrderId;
		}
	else
		{
			document.getElementById("hidGROrderId"+rowNum).value = "";
		}
}


function arrHasDupes(A) {                          // finds any duplicate array elements using the fewest possible comparison
	var i, j, n;
	n=A.length;
                                                     // to ensure the fewest possible comparisons
	for (i=0; i<n; i++) {                        // outer loop uses each item i at 0 through n
		for (j=i+1; j<n; j++) {              // inner loop only compares items j at i+1 to n
			if (A[i]==A[j]) return true;
	}	}
	return false;
}


function ChangeEditablityOfDeputationLocation(counter)
{
	var reason = document.getElementById("cmbReason"+counter).value.trim();
	if(reason == 700053)
		{
			document.getElementById("txtRemarks"+counter).readOnly = false;
			document.getElementById("labelForLocation"+counter).style.display = 'inline' ;
		}
	else
		{
			document.getElementById("txtRemarks"+counter).readOnly = true;
			document.getElementById("labelForLocation"+counter).style.display = 'none' ;
		}
}
