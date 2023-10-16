//window.attachEvent("onload",fillAllComboBox);
	addWindowOnloadEvent (fillAllComboBox,true);
	function fillAllComboBox ()
	{
		//alert("fillAllComboBox called..." );
		var status = window.FILL_COMBO_BOX_TAB_WISE;
		// DEVELOPER WANT TO FILL COMBO BOX BY MANUALLY
		if(status == undefined)
		{
//			alert ("RETURN UNDEFINED");
			return;
		}
		// DEVELOPER  WANT TO FILL COMBO BOX BY TAB BY TAB 
		if(status == true)
		{
//			alert ("return true");
			return;
		}

		var selectEleList = document.getElementsByTagName('select');
		//alert("LENGTH :"+selectEleList.length );
		var comboIdA = new Array ();
		var lookupNameA = new Array ();

		for (var i = 0; i< selectEleList.length; i++)
		{
			//alert("select object : "+selectEleList[i]);
			//var id = selectEleList[i].getAttribute('name'); // even this will work
			var id = selectEleList[i].name;
			//alert("id : "+id);
			var lookupName = selectEleList[i].getAttribute('lookupName');
			//var lookupName = selectEleList[i].lookupName;
			//alert("lookupName : "+lookupName);
			if(lookupName != undefined)
			{
				if (lookupName.length > 0)
				{
					var lengthOfCombo = selectEleList[i].length;
					if(lengthOfCombo <= 1)
					{
						var ar = lookupName.split(',');
//						alert ("LENGTH :"+ar.length);
						for (var counter = 0;counter < ar.length ;counter ++)
						{
							comboIdA.push (id);
							lookupNameA.push (ar[counter]);
//							count = count + 1;
						}
					}	
				}
			}
		}
		//alert("comboIdA : "+comboIdA);
		populateComboBoxes(comboIdA,lookupNameA);
	}	

	function fillTabWiseComboBox()
	{
//		alert ("fillTabWiseComboBox called.11..");
		var divAllObj = document.getElementById('mainTab').getElementsByTagName("a");	
		var i = 0;
		for(i = 0;i < divAllObj.length ;i++ )
		{
			if(document.getElementById(divAllObj[i].rel).style.display == 'block')
			{
				break;
			}
		}
		var comboIdA = new Array ();
		var lookupNameA = new Array ();
		var currDivObj = document.getElementById(divAllObj[i].rel);
		if(currDivObj.filledAllCombo == "filled" )
		{
//			alert ("return .filled .");
			return;
		}
		else
		{
//			alert ("not filled....");
			currDivObj.filledAllCombo = 'filled' ;
		}
		var selectObjA = currDivObj.getElementsByTagName('select');
		
//		alert ("LENGTH :"+selectObjA.length);
		var count = 0;
		for (var i = 0; i< selectObjA.length; i++)
		{
			var id = selectObjA[i].name;
			//var id = selectObjA[i].getAttribute('name'); //even this will work
			var lookupName = selectObjA[i].getAttribute('lookupName');
			if(lookupName != undefined)
			{
				if(lookupName.length > 0)
				{
					var lengthOfCombo = selectObjA[i].length;
					if(lengthOfCombo <= 1)
					{
//						alert ("ADD IN TO ARRAY ");
						var ar = lookupName.split(',');
//						alert ("LENGTH :"+ar.length);
						for (var counter = 0;counter < ar.length ;counter ++)
						{
							comboIdA.push (id);
							lookupNameA.push (ar[counter]);
							count = count + 1 ;
						}
					}
				}
			}	
		}
		if(comboIdA.length == 0 )
		{
			return;
		}
		populateComboBoxes(comboIdA,lookupNameA);
	}
	function populateComboBoxes(nameOfcomboBoxA,lookupNameA)
	{
		//alert ("populateComboBoxesTemp called...");
		//alert ("nameOfcomboBoxA11 :"+nameOfcomboBoxA);
		//alert ("LENGTH OF nameOfcomboBoxA11 :"+nameOfcomboBoxA.length);
		if (nameOfcomboBoxA.length == 0)
		{
			return ;
		}
		var aJAXParaA = new Array ();
		for (var i =0 ;i<nameOfcomboBoxA.length ;i++)
		{
			 var temp = "cbN="+nameOfcomboBoxA[i];
			 aJAXParaA.push(temp);
		}		
		for (var i =0 ;i<lookupNameA.length ;i++)
		{
			 var temp = "lpN="+lookupNameA[i];
			 aJAXParaA.push(temp);
		}		
		var parameterForAJAX = aJAXParaA.join("&");


//		var url='hdiits.htm?actionFlag=CMN_populateComboBox&' + parameterForAJAX;
		var url='hdiits.htm';
		//alert("url : "+url);
		var parameterForAJAX = "actionFlag=CMN_populateComboBox&"+aJAXParaA.join("&");
		//alert("sending request");
		var myAjaxPCB = new Ajax.Request
			(url,
				{
				method: 'post',
				parameters: parameterForAJAX,
				onSuccess: function(transport)
					{
					addValuesInComboBox(transport); 
					}
				}
			);		
	}
	
	
	function addValuesInComboBox(transport)
	{
		//alert("response recieved");
//		if (transport.status == 200)
		{ 	
//			var aJAXResponse =xmlHttpForCmbBx.responseText;
//			alert("AJAX RESPONSE :"+aJAXResponse);
//			var XMLDoc=xmlHttpForCmbBx.responseXML.documentElement;
			var XMLDoc = transport.responseXML.documentElement;
			var comboBoxesXML = XMLDoc.getElementsByTagName('cmb');
//			alert ("comboBoxesXML LENGTH :"+comboBoxesXML.length);
			for(var i =0;i<comboBoxesXML.length;i++)
			{
				var temp = comboBoxesXML[i].getElementsByTagName('cmbN');
				var comboBoxName = temp[0].firstChild.nodeValue;
//				alert ("comboBoxName :"+comboBoxName);
				var comboBoxObj = document.getElementById(comboBoxName);
				if (comboBoxObj == null)
				{
					continue ; 
				}
//				alert (" comboBoxObj.length 11:"+comboBoxObj.length );
//				if (comboBoxObj.length > 1)
//				{
//					continue ;
//				}
				var defaultPresent = false;
				var defaultVal = comboBoxObj.getAttribute('defaultSelect') ;
//				alert ("default Value :"+defaultVal);
				if(defaultVal != undefined)
				{
					if(defaultVal.length > 0)
					{
						defaultPresent = true;
					}
				}
		    	var entries = comboBoxesXML[i].getElementsByTagName('lkup');	
//				alert ("entries.length :"+entries.length);
				for ( var j = 0 ; j < entries.length ; j++ )
		     	{
		     		var value=entries[j].childNodes[0].firstChild.nodeValue;   
		     	    var text=entries[j].childNodes[1].firstChild.nodeValue;
//		     	    alert("TEXT :"+text);
//		     	    alert("VALUE :"+value);
		     	    var optObj = document.createElement('option');
			 		optObj.text = text;
					optObj.value = value;
		     	    try 
		     	    {
						comboBoxObj.add(optObj,null);
		     	    }
		     	    catch(e)
		     	    {
		     	    	comboBoxObj.add(optObj);
		     	    }
				}//  inner for loop end
				if (defaultPresent)
				{
					setDefaultOption (comboBoxName,defaultVal)							
					if(comboBoxObj.onchange != null)
					{
//						alert ("going to getOnChangeFunName");
						var onChangeVal = ""+comboBoxObj.onchange ;
						executeOnChangeFunc(onChangeVal,comboBoxObj);
					}
				}			
			}// outer for loop end 
		}	
	}
	function setDefaultOption(selectBoxId, defaultValue)
	{
		var lstLength = document.getElementById(selectBoxId).options.length;
		for(var dynCounter = 0; dynCounter < lstLength; dynCounter++)
		{
			if(document.getElementById(selectBoxId).options[dynCounter].value == defaultValue)
			{
			 	document.getElementById(selectBoxId).options[dynCounter].selected = "true";
			 	document.getElementById(selectBoxId).selectedIndex = dynCounter ;
				break;
			}
		}
	}

	function executeOnChangeFunc(onChangeFuncName,comboBoxObj)
	{
		var func = onChangeFuncName.substring(onChangeFuncName.indexOf("{")+1,onChangeFuncName.indexOf("}"));
		var funcParameter=func.substring(func.indexOf("(")+1,func.indexOf(")"))
		var obj= new Object();
		if(funcParameter!="")
		{
			if(funcParameter.indexOf("value")!=-1)
			{
				obj="document.getElementsByName('"+comboBoxObj.name+"')[0].value";
				func=func.replace(funcParameter,obj);
			}
			else
			{
				obj="document.getElementsByName('"+comboBoxObj.name+"')[0]";
				func=func.replace(funcParameter,obj);
		    }
		}
		eval(func);
	}
	function getDOMFromXML(xmlString)
	{	
		// code for IE
		var doc;
		if (window.ActiveXObject)
		{
			
			doc=new ActiveXObject("Microsoft.XMLDOM");
			doc.async="false";
			doc.loadXML(xmlString);
		}
		// code for Mozilla, Firefox, Opera, etc.
		else
		{
			var parser=new DOMParser();
			doc=parser.parseFromString(xmlString,"text/xml");
		}
//		alert ("ERROR 	:"+doc.parseError.errorCode);
//		alert ("REASON 	:"+doc.parseError.reason);
//		alert ("ERROR LINE :"+doc.parseError.line);
		
	return doc.documentElement;		
	}


