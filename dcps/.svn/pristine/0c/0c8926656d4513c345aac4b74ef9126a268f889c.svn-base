if(arrMulti==null)
{
	//	alert("arrMulti : "+arrMulti);
	var arrMulti= new Array();
}
var rowToUpdate;
var updateRow = null;
var flagForUpdatedVO = false;
var counter = 1;
function addDataInTableMRB(tableId, hiddenField, displayFieldArray, editMethodName, deleteMethodName, viewMethodName) 
	{
//		alert ("addDataInTable called...");
		if(deleteMethodName == undefined) 
		{
			deleteMethodName = '';
		}
		if(editMethodName == undefined) 
		{
			editMethodName = '';
		}
		if(viewMethodName == undefined) 
		{
			viewMethodName = '';
		}
		
		document.getElementById(tableId).style.display='';
		var trow=document.getElementById(tableId).insertRow();
		trow.id = 'row' + hiddenField + counter;
		trow.insertCell(0).innerHTML = "<INPUT type='hidden' name='" + hiddenField + "' id='" + hiddenField + counter + "' value='" + xmlHttp.responseText + "'/>";				
		trow.cells[0].style.display = 'none';
		
		var len = displayFieldArray.length;
		for(var i = 0; i < len; i++)
		{
			var field = document.getElementById(displayFieldArray[i]);
			//alert(field.type);
			if(field.type == 'select-one')
			{
				/* Code added by Tarun Trehan to check "Select" value in drop down
				for multiple add case. */
				if(field.options[field.selectedIndex].value == '-1')
				{
					trow.insertCell(i+1).innerHTML = "";
				}
				else
				{
					trow.insertCell(i+1).innerHTML = field.options[field.selectedIndex].text;						
				}
			}		
			else if(field.type == 'radio')
			{
				var radio=document.getElementsByName(displayFieldArray[i]);
				for(var j = 0; j < radio.length; j++)
				{
					if(radio[j].checked)
					{
						trow.insertCell(i+1).innerHTML = radio[j].value;
					}
				}
					
			}
			else if(field.type == 'select-multiple')
			{
				var radio=document.getElementsByName(displayFieldArray[i]);
				//alert(field.type);
				//alert(field.options.length);
				var displaystring = "";
				for(var j = 0; j < field.options.length; j++)
				{
					if(field.options[j].selected && field.options[j].value !='-1' )
					{
						//alert(j);
						if(displaystring == "")
						{
						displaystring=field.options[j].text;
						}
						else
						{
						displaystring+= " / " + field.options[j].text;
						}
					}
				}
				trow.insertCell(i+1).innerHTML = displaystring;		
			}		
			else
			{
				trow.insertCell(i+1).innerHTML = field.value;	
			}
		}	
		var editCap = "";
		var delCap  = "";
		var viewCap = "";
		try 
		{
			editCap = cmnLblArray[0];
			delCap  = cmnLblArray[1];
			viewCap = cmnLblArray[4];
		}
		catch (e)
		{
//			alert ("EXCEPTION THROWN ");
			editCap = "Edit";
			delCap  = "Delete";
			viewCap = "View";
		}
		
		trow.insertCell(len + 1).innerHTML ="<input type='number' name='MedAmnt" + counter + "' id='MedAmnt" + counter + "'style='text-align: right' >";	
		
		if(editMethodName!='' && deleteMethodName!='' && viewMethodName!='') 		
			trow.insertCell(len + 2).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + 
												 "')>"+viewCap+"</a> / <a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id +
												 "')>"+editCap +"</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>"+delCap+"</a>";			
		
		else if(editMethodName!='' && deleteMethodName!='' && viewMethodName=='')
			trow.insertCell(len + 2).innerHTML = "<a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id +
												 "')>"+editCap +"</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>"+delCap+"</a>";										
												 
		else if(editMethodName!='' && deleteMethodName=='' && viewMethodName!='')											 
			trow.insertCell(len + 2).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + 
												 "')>"+viewCap+"</a> / <a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id +  "')>"+editCap+"</a>";
												 
		else if(editMethodName=='' && deleteMethodName!='' && viewMethodName!='')											 											 
			trow.insertCell(len + 2).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + 
												 "')>"+viewCap+"</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>"+delCap+"</a>";			
		
		else if(editMethodName=='' && deleteMethodName!='' && viewMethodName=='')
			trow.insertCell(len + 2).innerHTML = "<a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>"+delCap+"</a>";
			
		else if(editMethodName!='' && deleteMethodName=='' && viewMethodName=='')
			trow.insertCell(len + 2).innerHTML = "<a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id + "')>Edit</a>";		
			
		else if(editMethodName=='' && deleteMethodName=='' && viewMethodName!='')		
			trow.insertCell(len + 2).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id +  "')>"+viewCap+"</a>";
			
			
		
		counter++;	
		
		return trow.id;
	}
	
	
	
	function addDataInTableBill(tableId, hiddenField, displayFieldArray, editMethodName, deleteMethodName, viewMethodName) 
	{
//		alert ("addDataInTable called...");
		if(deleteMethodName == undefined) 
		{
			deleteMethodName = '';
		}
		if(editMethodName == undefined) 
		{
			editMethodName = '';
		}
		if(viewMethodName == undefined) 
		{
			viewMethodName = '';
		}
		
		document.getElementById(tableId).style.display='';
		var trow=document.getElementById(tableId).insertRow();
		trow.id = 'row' + hiddenField + counter;
		trow.insertCell(0).innerHTML = "<INPUT type='hidden' name='" + hiddenField + "' id='" + hiddenField + counter + "' value='" + xmlHttp.responseText + "'/>";				
		trow.cells[0].style.display = 'none';
		
		var len = displayFieldArray.length;
		for(var i = 0; i < len; i++)
		{
			var field = document.getElementById(displayFieldArray[i]);
			//alert(field.type);
			if(field.type == 'select-one')
			{
				/* Code added by Tarun Trehan to check "Select" value in drop down
				for multiple add case. */
				if(field.options[field.selectedIndex].value == '-1')
				{
					trow.insertCell(i+1).innerHTML = "";
				}
				else
				{
					trow.insertCell(i+1).innerHTML = field.options[field.selectedIndex].text;						
				}
			}		
			else if(field.type == 'radio')
			{
				var radio=document.getElementsByName(displayFieldArray[i]);
				for(var j = 0; j < radio.length; j++)
				{
					if(radio[j].checked)
					{
						trow.insertCell(i+1).innerHTML = radio[j].value;
					}
				}
					
			}
			else if(field.type == 'select-multiple')
			{
				var radio=document.getElementsByName(displayFieldArray[i]);
				//alert(field.type);
				//alert(field.options.length);
				var displaystring = "";
				for(var j = 0; j < field.options.length; j++)
				{
					if(field.options[j].selected && field.options[j].value !='-1' )
					{
						//alert(j);
						if(displaystring == "")
						{
						displaystring=field.options[j].text;
						}
						else
						{
						displaystring+= " / " + field.options[j].text;
						}
					}
				}
				trow.insertCell(i+1).innerHTML = displaystring;		
			}		
			else
			{
				trow.insertCell(i+1).innerHTML = field.value;	
			}
		}	
		var editCap = "";
		var delCap  = "";
		var viewCap = "";
		try 
		{
			editCap = cmnLblArray[0];
			delCap  = cmnLblArray[1];
			viewCap = cmnLblArray[4];
		}
		catch (e)
		{
//			alert ("EXCEPTION THROWN ");
			editCap = "Edit";
			delCap  = "Delete";
			viewCap = "View";
		}
		
		var XmlPath=xmlHttp.responseText;
		//alert(XmlPath);
		var rowID=trow.id;
		trow.insertCell(len+1).innerHTML ="<a href=javascript:void('MedDtls')  onclick=javascript:SearchMedicine('"+rowID+"','"+XmlPath+"')>"+MediDetails+"</a>";
		
		trow.insertCell(len + 2).innerHTML ="<input type='text' name='MedAmnt" + counter + "' id='MedAmnt" + counter + "' readonly='true'>";	
		if(editMethodName!='' && deleteMethodName!='' && viewMethodName!='') 		
			trow.insertCell(len + 3).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + 
												 "')>"+viewCap+"</a> / <a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id +
												 "')>"+editCap +"</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>"+delCap+"</a>";			
		
		else if(editMethodName!='' && deleteMethodName!='' && viewMethodName=='')
			trow.insertCell(len + 3).innerHTML = "<a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id +
												 "')>"+editCap +"</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>"+delCap+"</a>";										
												 
		else if(editMethodName!='' && deleteMethodName=='' && viewMethodName!='')											 
			trow.insertCell(len + 3).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + 
												 "')>"+viewCap+"</a> / <a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id +  "')>"+editCap+"</a>";
												 
		else if(editMethodName=='' && deleteMethodName!='' && viewMethodName!='')											 											 
			trow.insertCell(len + 3).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + 
												 "')>"+viewCap+"</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>"+delCap+"</a>";			
		
		else if(editMethodName=='' && deleteMethodName!='' && viewMethodName=='')
			trow.insertCell(len + 3).innerHTML = "<a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>"+delCap+"</a>";
			
		else if(editMethodName!='' && deleteMethodName=='' && viewMethodName=='')
			trow.insertCell(len + 3).innerHTML = "<a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id + "')>Edit</a>";		
			
		else if(editMethodName=='' && deleteMethodName=='' && viewMethodName!='')		
			trow.insertCell(len + 3).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id +  "')>"+viewCap+"</a>";
			
		counter++;	
		
		return trow.id;
	}
	
	var CounterBill=1;
	
	function addDataInTableBillSecnd(tableId, hiddenField, displayFieldArray,DisplayArr, editMethodName, deleteMethodName, viewMethodName,BillDataComingFromDB) 
	{
		//alert ("addDataInTable called...");
		
		if(deleteMethodName == undefined) 
		{
			deleteMethodName = '';
		}
		if(editMethodName == undefined) 
		{
			editMethodName = '';
		}
		if(viewMethodName == undefined) 
		{
			viewMethodName = '';
		}
		
		document.getElementById(tableId).style.display='';
		var trow=document.getElementById(tableId).insertRow();
		trow.id = 'row' + hiddenField + CounterBill;
		
		if(BillDataComingFromDB==true)
		{
			trow.insertCell(0).innerHTML = "<INPUT type='hidden' name='" + hiddenField + "' id='" + hiddenField + CounterBill + "' value='" + xmlHttp.responseText + "_U'/>";				
		}
		else
		{
			trow.insertCell(0).innerHTML = "<INPUT type='hidden' name='" + hiddenField + "' id='" + hiddenField + CounterBill + "' value='" + xmlHttp.responseText + "'/>";				
		}
		trow.cells[0].style.display = 'none';
		
		var len = displayFieldArray.length;
		for(var i = 0; i < len; i++)
		{
			var field = document.getElementById(displayFieldArray[i]);
			//alert(field.type);
			if(field.type == 'select-one')
			{
				/* Code added by Tarun Trehan to check "Select" value in drop down
				for multiple add case. */
				if(field.options[field.selectedIndex].value == '-1')
				{
					trow.insertCell(i+1).innerHTML = "";
				}
				else
				{
					trow.insertCell(i+1).innerHTML = field.options[field.selectedIndex].text;						
				}
			}		
			else if(field.type == 'radio')
			{
				var radio=document.getElementsByName(displayFieldArray[i]);
				for(var j = 0; j < radio.length; j++)
				{
					if(radio[j].checked)
					{
						trow.insertCell(i+1).innerHTML = radio[j].value;
					}
				}
					
			}
			else if(field.type == 'select-multiple')
			{
				var radio=document.getElementsByName(displayFieldArray[i]);
				//alert(field.type);
				//alert(field.options.length);
				var displaystring = "";
				for(var j = 0; j < field.options.length; j++)
				{
					if(field.options[j].selected && field.options[j].value !='-1' )
					{
						//alert(j);
						if(displaystring == "")
						{
						displaystring=field.options[j].text;
						}
						else
						{
						displaystring+= " / " + field.options[j].text;
						}
					}
				}
				trow.insertCell(i+1).innerHTML = displaystring;		
			}		
			else
			{
				trow.insertCell(i+1).innerHTML = field.value;	
			}
		}	
		var editCap = "";
		var delCap  = "";
		var viewCap = "";
		try 
		{
			editCap = cmnLblArray[0];
			delCap  = cmnLblArray[1];
			viewCap = cmnLblArray[4];
		}
		catch (e)
		{
//			alert ("EXCEPTION THROWN ");
			editCap = "Edit";
			delCap  = "Delete";
			viewCap = "View";
		}
		
		var XmlPath=xmlHttp.responseText;
		//alert(XmlPath);
		var rowID=trow.id;
		trow.insertCell(len+1).innerHTML ="<a href=javascript:void('MedDtls')  onclick=javascript:SearchMedicine('"+rowID+"','"+XmlPath+"')>"+MediDetails+"</a>";
		
		trow.insertCell(len+2).innerHTML=document.getElementById(DisplayArr[0]).value;
		
		//alert("coimign here"+XmlPath);
		xmlHttp=GetXmlHttpObject();		
		var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + XmlPath;	
		xmlHttp.onreadystatechange = function()
		{
			if(xmlHttp.readyState == 4) 
			{
				if (xmlHttp.readyState == 4) 
					{ 				  		
						var decXML = xmlHttp.responseText;
						//alert("OldXMl Path---->"+decXML);
						var xmlDOM = getDOMFromXML(decXML);	
						//alert(getXPathValueFromDOM(xmlDOM, 'MedicineXMlPath'));
						if(getXPathValueFromDOM(xmlDOM, 'MedicineXMlPath')!="NoPath")
						{
							var MediXMLPath=getXPathValueFromDOM(xmlDOM, 'MedicineXMlPath');
							
							if(MediXMLPath!=null)
							{						
								var SeparPath=MediXMLPath.split(",");
							
								if(CounterBill==0)
								{
									trow.insertCell(len + 3).innerHTML="<table id='TabInsideTab"+CounterBill+"' name='TabInsideTab"+CounterBill+
									"' style='border-collapse: collapse' border='1' align='center'  borderColor='BLACK' width='100%'><tr><td width='20%' align='middle' class='fieldLabel' bgcolor='#C9DFFF'><b>"+MedicineName1+
									"</b></td><td width='20%' align='middle' class='fieldLabel' bgcolor='#C9DFFF'><b>"+MedicineType1+"</b></td><td align='middle' width='20%' class='fieldLabel' bgcolor='#C9DFFF'><b>"+Medicinewieght1+
									"</b></td><td align='middle' width='20%' class='fieldLabel' bgcolor='#C9DFFF'><b>"+Medicinewieght1+"</b></td><td align='middle' width='20%' class='fieldLabel' bgcolor='#C9DFFF'><b>"+MedicineAmnt1+"</b></td></tr></table>" ;	
								}
								else
								{
									trow.insertCell(len + 3).innerHTML="<table id='TabInsideTab"+CounterBill+"' name='TabInsideTab"+CounterBill+
									"' style='border-collapse: collapse' border='1' align='center'  borderColor='BLACK' width='100%'><tr><td width='20%'></td><td width='20%'></td><td width='20%'></td><td width='20%'></td><td width='20%'></td></tr></table>" ;	
							
								}
								for(var i=0;i<SeparPath.length;i++)
								{
								
									xmlHttp1=GetXmlHttpObject();
									if(SeparPath[i].substring(SeparPath[i].length-2,SeparPath[i].length)!="_D")
									{		
										var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + SeparPath[i];	
										xmlHttp1.onreadystatechange = function()
										{
											if(xmlHttp1.readyState == 4) 
											{
												if (xmlHttp1.readyState == 4) 
												{ 				  		
													var decXML1 = xmlHttp1.responseText;
													//alert("OldXMl Path---->"+decXML1);
													var xmlDOM1 = getDOMFromXML(decXML1);	
													//alert();
													//alert(getXPathValueFromDOM(xmlDOM1, 'MedicineName'));
													//alert(getXPathValueFromDOM(xmlDOM1, 'MedicineType'));
													//alert(getXPathValueFromDOM(xmlDOM1, 'MedicineCat'));
													//alert(getXPathValueFromDOM(xmlDOM1, 'MedicineWt'));
													if(getXPathValueFromDOM(xmlDOM1, 'MedicineName')!=null || getXPathValueFromDOM(xmlDOM1, 'MedicineType')!=null || getXPathValueFromDOM(xmlDOM1, 'MedicineCat')!=null || getXPathValueFromDOM(xmlDOM1, 'MedicineWt')!=null)
													{
														MedicineName=getXPathValueFromDOM(xmlDOM1, 'MedicineName');
														MedicineType=getXPathValueFromDOM(xmlDOM1, 'MedicineType');
														MedicineCat=getXPathValueFromDOM(xmlDOM1, 'MedicineCat');
														MedicineWt=getXPathValueFromDOM(xmlDOM1, 'MedicineWt');
														MedicineAmnt=getXPathValueFromDOM(xmlDOM1, 'MedicineAmnt');
													}
													else
													{
														MedicineName=getXPathValueFromDOM(xmlDOM1, 'OtherMedicineName');
														MedicineType=getXPathValueFromDOM(xmlDOM1, 'OtherMedicineType');
														MedicineCat=getXPathValueFromDOM(xmlDOM1, 'OtherMedicineCat');
														MedicineWt=getXPathValueFromDOM(xmlDOM1, 'OtherMedicineWt');
														MedicineAmnt=getXPathValueFromDOM(xmlDOM1, 'MedicineAmnt');
													}
													var scndTRow=document.getElementById("TabInsideTab"+CounterBill).insertRow();
													scndTRow.insertCell(0).innerHTML=MedicineName;
													scndTRow.insertCell(1).innerHTML=MedicineType;
													scndTRow.insertCell(2).innerHTML=MedicineCat;
													scndTRow.insertCell(3).innerHTML=MedicineWt;
													scndTRow.insertCell(4).innerHTML=MedicineAmnt;
						
												}
											}
										}
									}
									xmlHttp1.open("POST",encodeURI(url),false);
									xmlHttp1.send(null);	
								}	
							}
						}
						else
						{
							trow.insertCell(len + 3).innerHTML="<table id='TabInsideTab"+CounterBill+"' name='TabInsideTab"+CounterBill+"' width='50%'/>" 	
							var scndTRow=document.getElementById("TabInsideTab"+CounterBill).insertRow();
							scndTRow.insertCell(0).innerHTML=NOMedicineRec;
						}
						
					}
				}
		}
		xmlHttp.open("POST",encodeURI(url),false);
		xmlHttp.send(null);		
		
		if(editMethodName!='' && deleteMethodName!='' && viewMethodName!='') 		
			trow.insertCell(len + 4).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + 
												 "')>"+viewCap+"</a> / <a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id +
												 "')>"+editCap +"</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>"+delCap+"</a>";			
		
		else if(editMethodName!='' && deleteMethodName!='' && viewMethodName=='')
			trow.insertCell(len + 4).innerHTML = "<a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id +
												 "')>"+editCap +"</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>"+delCap+"</a>";										
												 
		else if(editMethodName!='' && deleteMethodName=='' && viewMethodName!='')											 
			trow.insertCell(len + 4).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + 
												 "')>"+viewCap+"</a> / <a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id +  "')>"+editCap+"</a>";
												 
		else if(editMethodName=='' && deleteMethodName!='' && viewMethodName!='')											 											 
			trow.insertCell(len + 4).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + 
												 "')>"+viewCap+"</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>"+delCap+"</a>";			
		
		else if(editMethodName=='' && deleteMethodName!='' && viewMethodName=='')
			trow.insertCell(len + 4).innerHTML = "<a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>"+delCap+"</a>";
			
		else if(editMethodName!='' && deleteMethodName=='' && viewMethodName=='')
			trow.insertCell(len + 4).innerHTML = "<a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id + "')>Edit</a>";		
			
		else if(editMethodName=='' && deleteMethodName=='' && viewMethodName!='')		
			trow.insertCell(len + 4).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id +  "')>"+viewCap+"</a>";
			
		
		
		CounterBill++;	
		
		return trow.id;
	}
	
var CounterBillDB=1;
function addDBDataInTableBillSecnd(tableId,hiddenField,dispFieldA,DisplayArr,
						xmlFilePath,editMethodName,deleteMethodName,viewMethodName)
	{
		//	alert ('addDBDataInTable called....');
		if(deleteMethodName == undefined) 
		{
			deleteMethodName = '';
		}
		if(editMethodName 	== undefined) 
		{
			editMethodName 	 = '';
		}
		if(viewMethodName 	== undefined) 
		{
			viewMethodName 	 = '';
		}
		document.getElementById(tableId).style.display='';
		
		
		var trow=document.getElementById(tableId).insertRow();
		trow.id = 'row' + hiddenField + CounterBill;
	
		trow.insertCell(0).innerHTML = "<INPUT type='hidden' name='" + hiddenField + "' id='" + hiddenField + CounterBill + "' value='" +xmlFilePath  + "_N' />";
		trow.cells[0].style.display = 'none';
		var len = dispFieldA.length;
		for(var i = 0; i < len; i++) 
		{
			trow.insertCell(i+1).innerHTML = dispFieldA[i];	
		}
		var editCap = "";
		var delCap  = "";
		var viewCap = "";
		try 
		{
			editCap = cmnLblArray[0];
			delCap  = cmnLblArray[1];
			viewCap = cmnLblArray[4];
		}
		catch (e)
		{
//			alert ("EXCEPTION THROWN ");
			editCap = "Edit";
			delCap  = "Delete";
			viewCap = "View";
		}
		var MedicineDetails="Medicine Details";
		trow.insertCell(len+1).innerHTML ="<a href=javascript:void('MedDtls')  onclick=javascript:SearchMedicine('"+trow.id+"','"+xmlFilePath+"')>"+MediDetails+"</a>";
		
		trow.insertCell(len+2).innerHTML=DisplayArr[0];
			
		xmlHttp=GetXmlHttpObject();		
		var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + xmlFilePath;	
		xmlHttp.onreadystatechange = function()
		{
			if(xmlHttp.readyState == 4) 
			{
				if (xmlHttp.readyState == 4) 
					{ 				  		
						var decXML = xmlHttp.responseText;
						//alert("OldXMl Path---->"+decXML);
						var xmlDOM = getDOMFromXML(decXML);	
						//alert(getXPathValueFromDOM(xmlDOM, 'MedicineXMlPath'));
						if(getXPathValueFromDOM(xmlDOM, 'MedicineXMlPath')!="NoPath")
						{
							var MediXMLPath=getXPathValueFromDOM(xmlDOM, 'MedicineXMlPath');
							//alert("inside if"+MediXMLPath);
							var SeparPath=MediXMLPath.split(",");
							
							if(CounterBill==0)
							{
								trow.insertCell(len + 3).innerHTML="<table id='TabInsideTab"+CounterBill+"' name='TabInsideTab"+CounterBill+
								"' style='border-collapse: collapse' border='1' align='center'  borderColor='BLACK' width='100%'><tr><td width='20%' align='middle' class='fieldLabel' bgcolor='#C9DFFF'><b>"+MedicineName1+
								"</b></td><td width='20%' align='middle' class='fieldLabel' bgcolor='#C9DFFF'><b>"+MedicineType1+"</b></td><td align='middle' width='20%' class='fieldLabel' bgcolor='#C9DFFF'><b>"+Medicinewieght1+
								"</b></td><td align='middle' width='20%' class='fieldLabel' bgcolor='#C9DFFF'><b>"+Medicinewieght1+"</b></td><td align='middle' width='20%' class='fieldLabel' bgcolor='#C9DFFF'><b>"+MedicineAmnt1+"</b></td></tr></table>" ;								}
							else
							{
								trow.insertCell(len + 3).innerHTML="<table id='TabInsideTab"+CounterBill+"' name='TabInsideTab"+CounterBill+
								"' style='border-collapse: collapse' border='1' align='center'  borderColor='BLACK' width='100%'><tr><td width='20%'></td><td width='20%'></td><td width='20%'></td><td width='20%'></td><td width='20%'></td></tr></table>" ;	
							
							}
							
							for(var i=0;i<SeparPath.length;i++)
							{
								
								xmlHttp1=GetXmlHttpObject();		
								var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + SeparPath[i];	
								xmlHttp1.onreadystatechange = function()
								{
									if(xmlHttp1.readyState == 4) 
									{
										if (xmlHttp1.readyState == 4) 
										{ 				  		
											var decXML1 = xmlHttp1.responseText;
											//alert("OldXMl Path---->"+decXML1);
											var xmlDOM1 = getDOMFromXML(decXML1);	
											//alert();
											
											if(getXPathValueFromDOM(xmlDOM1, 'MedicineName')!=null || getXPathValueFromDOM(xmlDOM1, 'MedicineType')!=null || getXPathValueFromDOM(xmlDOM1, 'MedicineCat')!=null || getXPathValueFromDOM(xmlDOM1, 'MedicineWt')!=null)
											{
												MedicineName=getXPathValueFromDOM(xmlDOM1, 'MedicineName');
												MedicineType=getXPathValueFromDOM(xmlDOM1, 'MedicineType');
												MedicineCat=getXPathValueFromDOM(xmlDOM1, 'MedicineCat');
												MedicineWt=getXPathValueFromDOM(xmlDOM1, 'MedicineWt');
												MedicineAmnt=getXPathValueFromDOM(xmlDOM1, 'MedicineAmnt');
											}
											else
											{
												MedicineName=getXPathValueFromDOM(xmlDOM1, 'OtherMedicineName');
												MedicineType=getXPathValueFromDOM(xmlDOM1, 'OtherMedicineType');
												MedicineCat=getXPathValueFromDOM(xmlDOM1, 'OtherMedicineCat');
												MedicineWt=getXPathValueFromDOM(xmlDOM1, 'OtherMedicineWt');
												MedicineAmnt=getXPathValueFromDOM(xmlDOM1, 'MedicineAmnt');
											}
											var scndTRow=document.getElementById("TabInsideTab"+CounterBill).insertRow();
											scndTRow.insertCell(0).innerHTML=MedicineName;
											scndTRow.insertCell(1).innerHTML=MedicineType;
											scndTRow.insertCell(2).innerHTML=MedicineCat;
											scndTRow.insertCell(3).innerHTML=MedicineWt;
											scndTRow.insertCell(4).innerHTML=MedicineAmnt;
						
										}
									}
								}
								xmlHttp1.open("POST",encodeURI(url),false);
								xmlHttp1.send(null);	
							}
							
						}
						else
						{
							trow.insertCell(len + 3).innerHTML="<table id='TabInsideTab"+CounterBill+"' name='TabInsideTab"+CounterBill+"' width='50%'/>" 	
							var scndTRow=document.getElementById("TabInsideTab"+CounterBill).insertRow();
							scndTRow.insertCell(0).innerHTML=NOMedicineRec;
						}
						
					}
				}
		}
		xmlHttp.open("POST",encodeURI(url),false);
		xmlHttp.send(null);		
			
			
		if(editMethodName!='' && deleteMethodName!='' && viewMethodName!='') 		
		trow.insertCell(len + 4).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + 
											 "')>"+viewCap+"</a> / <a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id +
											 "')>"+editCap+"</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>"+delCap+"</a>";			
		
		else if(editMethodName!='' && deleteMethodName!='' && viewMethodName=='')
			trow.insertCell(len + 4).innerHTML = "<a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id +
												 "')>"+editCap+"</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>"+delCap+"</a>";										
												 
		else if(editMethodName!='' && deleteMethodName=='' && viewMethodName!='')											 
			trow.insertCell(len + 4).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + 
												 "')>"+viewCap+"</a> / <a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id +  "')>"+editCap+"</a>";
												 
		else if(editMethodName=='' && deleteMethodName!='' && viewMethodName!='')											 											 
			trow.insertCell(len + 4).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + 
												 "')>"+viewCap+"</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>"+delCap+"</a>";			
		
		else if(editMethodName=='' && deleteMethodName!='' && viewMethodName=='')
			trow.insertCell(len + 4).innerHTML = "<a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>"+delCap+"</a>";
			
		else if(editMethodName!='' && deleteMethodName=='' && viewMethodName=='')
			trow.insertCell(len + 4).innerHTML = "<a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id + "')>"+editCap+"</a>";		
			
		else if(editMethodName=='' && deleteMethodName=='' && viewMethodName!='')		
			trow.insertCell(len + 4).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id +  "')>"+viewCap+"</a>";
		CounterBill++;
		
		return trow.id;
	}
	
	function addOrUpdateRecordMRB(methodName, actionFlag, fieldArray, progressBarFlag) 
	{	
		xmlHttp=GetXmlHttpObject();
		if (xmlHttp==null) {
		  alert ("Your browser does not support AJAX!");
		  return;
		} 
		if(progressBarFlag != false)
		{
			showProgressbar("Please Wait...");	
		}
				
		var reqBody = getRequestBody(fieldArray);	
		var url='hdiits.htm?actionFlag=' + actionFlag + '&' + reqBody;
		methodName = methodName + "()";
		
		xmlHttp.onreadystatechange=function() {
			if(xmlHttp.readyState == 4) {
				eval(methodName);
				if(progressBarFlag != false)
				{
					hideProgressbar();
				}
			}	
		}
	
		xmlHttp.open("POST",encodeURI(url),false);
		xmlHttp.send(null);
	}	
	
	
	
	function addDataInTableForMed2(tableId, hiddenField, displayFieldArray, editMethodName, deleteMethodName, viewMethodName,MedXMlPath)
 {
 	if(deleteMethodName == undefined) 
		{
			deleteMethodName = '';
		}
		if(editMethodName == undefined) 
		{
			editMethodName = '';
		}
		if(viewMethodName == undefined) 
		{
			viewMethodName = '';
		}
		
		document.getElementById(tableId).style.display='';
		var trow=document.getElementById(tableId).insertRow();
		trow.id = 'row' + hiddenField + counter;
		trow.insertCell(0).innerHTML = "<INPUT type='hidden' name='" + hiddenField + "' id='" + hiddenField + counter + "' value='" + MedXMlPath + "'/>";				
		trow.cells[0].style.display = 'none';
		
		var len = displayFieldArray.length;
		for(var i = 0; i < len; i++)
		{
			var field = document.getElementById(displayFieldArray[i]);
			//alert(field.type);
			if(field.type == 'select-one')
			{
				/* Code added by Tarun Trehan to check "Select" value in drop down
				for multiple add case. */
				if(field.options[field.selectedIndex].value == '-1')
				{
					trow.insertCell(i+1).innerHTML = "";
				}
				else
				{
					trow.insertCell(i+1).innerHTML = field.options[field.selectedIndex].text;						
				}
			}		
			else if(field.type == 'radio')
			{
				var radio=document.getElementsByName(displayFieldArray[i]);
				for(var j = 0; j < radio.length; j++)
				{
					if(radio[j].checked)
					{
						trow.insertCell(i+1).innerHTML = radio[j].value;
					}
				}
					
			}
			else if(field.type == 'select-multiple')
			{
				var radio=document.getElementsByName(displayFieldArray[i]);
				//alert(field.type);
				//alert(field.options.length);
				var displaystring = "";
				for(var j = 0; j < field.options.length; j++)
				{
					if(field.options[j].selected && field.options[j].value !='-1' )
					{
						//alert(j);
						if(displaystring == "")
						{
						displaystring=field.options[j].text;
						}
						else
						{
						displaystring+= " / " + field.options[j].text;
						}
					}
				}
				trow.insertCell(i+1).innerHTML = displaystring;		
			}		
			else
			{
				trow.insertCell(i+1).innerHTML = field.value;	
			}
		}	
		var editCap = "";
		var delCap  = "";
		var viewCap = "";
		try 
		{
			editCap = cmnLblArray[0];
			delCap  = cmnLblArray[1];
			viewCap = cmnLblArray[4];
		}
		catch (e)
		{
//			alert ("EXCEPTION THROWN ");
			editCap = "Edit";
			delCap  = "Delete";
			viewCap = "View";
		}
		
		if(editMethodName!='' && deleteMethodName!='' && viewMethodName!='') 		
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + 
												 "')>"+viewCap+"</a> / <a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id +
												 "')>"+editCap +"</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>"+delCap+"</a>";			
		
		else if(editMethodName!='' && deleteMethodName!='' && viewMethodName=='')
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id +
												 "')>"+editCap +"</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>"+delCap+"</a>";										
												 
		else if(editMethodName!='' && deleteMethodName=='' && viewMethodName!='')											 
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + 
												 "')>"+viewCap+"</a> / <a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id +  "')>"+editCap+"</a>";
												 
		else if(editMethodName=='' && deleteMethodName!='' && viewMethodName!='')											 											 
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + 
												 "')>"+viewCap+"</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>"+delCap+"</a>";			
		
		else if(editMethodName=='' && deleteMethodName!='' && viewMethodName=='')
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>"+delCap+"</a>";
			
		else if(editMethodName!='' && deleteMethodName=='' && viewMethodName=='')
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id + "')>Edit</a>";		
			
		else if(editMethodName=='' && deleteMethodName=='' && viewMethodName!='')		
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id +  "')>"+viewCap+"</a>";
			
		counter++;	
		
		return trow.id;
 }
 
 
 function sendAjaxRequestForEditBill(rowId, methodName, progressBarFlag) 
	{
			//alert('sendAjaxRequestForEdit called....');
		var editNtCap = "";
		try
		{
			editNtCap = cmnLblArray[5];	
		}
		catch(e)
		{
			editNtCap = "You can not edit this record, Because you have open one record for update."
		}
		if(updateRow != null)
		{
			alert (editNtCap);
			return ;
		}
		if(progressBarFlag != false)
		{
			showProgressbar("Please Wait...");
		}
		
		updateRow = rowId;
		var hField = rowId.substring(3, rowId.length);
		//	alert(hField);
		var xmlFileName = document.getElementById(hField).value;
		//	alert('xmlFileName'+xmlFileName);
		if(isAllreadyAddedVOFileName(xmlFileName))
		{
			flagForUpdatedVO = true;		
		}
		xmlHttp=GetXmlHttpObject();
		if (xmlHttp==null) 
		{
			if(progressBarFlag != false)
			{
			  hideProgressbar();
			}
		  alert ("Your browser does not support AJAX!");
		  return;
		} 		
		
		var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + xmlFileName;
		methodName = methodName + "()";
		
		xmlHttp.onreadystatechange = function() {
			if(xmlHttp.readyState == 4) {
				eval(methodName);
				if(progressBarFlag != false)
				{
					hideProgressbar();
				}
			 }
		 }
		
		xmlHttp.open("POST",encodeURI(url),true);
		xmlHttp.send(null);	
	}
 
 
 function updateDataInTableBill(hiddenField, displayFieldArray) 
	{
		//	alert ("updateDataInTable called...");
		var trow = document.getElementById(updateRow);
		//	var hFieldId = updateRow.substring(updateRow.length-1, updateRow.length);
		var hFieldId = updateRow.substring(3, updateRow.length);
		//	MAKING IT NULL BECAUSE IT WILL USED TO IDENTIFY IF
		//	ANY OTHER MULTIPLE EDIT IS OPEN IN EDIT MODE.
		//  CHANGE BY 202414
		//	alert ("xmlHttp.responseText "+xmlHttp.responseText);
		if(flagForUpdatedVO)
		{
			//	alert ("_U append to XML response..");
			//	HERE ADDED "_U" TO INDICATE THIS RECORD IS UPDATED BY USER. 
			document.getElementById(hFieldId).value = xmlHttp.responseText +"_U";
			flagForUpdatedVO = false;
		}
		else
		{
			document.getElementById(hFieldId).value = xmlHttp.responseText;
		}
		
		//trow.cells[0].innerHTML = "<INPUT type='hidden' name='" + hiddenField + "' id='" + hFieldId + "' value='" + xmlHttp.responseText + "'/>";					
		trow.cells[0].style.display = 'none';
		var len = displayFieldArray.length;
		for(var i = 0; i < len; i++)
		{
			var field = document.getElementById(displayFieldArray[i]);
			//	alert(field.type);
			if(field.type == 'select-one')
			{
				/* Code added by Tarun Trehan to check "Select" value in drop down
				for multiple add case. */
				if(field.options[field.selectedIndex].value == '-1')
				{
					trow.cells[i+1].innerHTML = "";
				}
				else
				{
					trow.cells[i+1].innerHTML = field.options[field.selectedIndex].text;
				}
				
			}
			else if(field.type == 'radio')
			{
				var radio=document.getElementsByName(displayFieldArray[i]);
				for(var j = 0; j < radio.length; j++)
				{
					if(radio[j].checked)
					{
						trow.cells[i+1].innerHTML = radio[j].value;
					}
				}
			}
			else if(field.type == 'select-multiple')
			{
				var radio=document.getElementsByName(displayFieldArray[i]);
				//alert(field.type);
				//alert(field.options.length);
				var displaystring = "";
				for(var j = 0; j < field.options.length; j++)
				{
					if(field.options[j].selected && field.options[j].value != '-1' )
					{
						if(displaystring == "")
						{
							displaystring=field.options[j].text;
						}
						else
						{
							displaystring+= " / " + field.options[j].text;
						}
					}
				}
				trow.cells[i+1].innerHTML = displaystring;		
			}				
			
			else
			{
				trow.cells[i+1].innerHTML = field.value;	
			}
		}
		var MedicineDetails="Medicine Details";
		var XmlPath=xmlHttp.responseText;
		trow.cells[len+1].innerHTML = "<a href=javascript:void('MedDtls')  onclick=javascript:SearchMedicine('"+updateRow+"','"+XmlPath+"')>"+MedicineDetails+"</a>";
		updateRow = null;
	}
 
 var MedicineName1="";
 var MedicineType1="";
 var MedicineCat1="";
 var Medicinewieght1="";
 var MedicineAmnt1="";
 var MediDetails="";
 var NOMedicineRec="";
 function SendingMediCapsInJs(MediName,MediType,MediCat,MediWeigth,MediAmnt,MediDt,NoMediRcrd)
 {
 	
 	MedicineName1=MediName;
 	MedicineType1=MediType;
 	MedicineCat1=MediCat;
 	Medicinewieght1=MediWeigth;
 	MedicineAmnt1=MediAmnt;
 	MediDetails=MediDt;
 	NOMedicineRec=NoMediRcrd;
 }
 