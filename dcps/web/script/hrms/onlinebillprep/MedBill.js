	
	var Row_One = 0;
	var Row_Two = 0;

	function Table_One_AddRow()
	{
  		++Row_One;
  			  		
		var newRow = document.all("TableOne").insertRow(Row_One);
	   	
	   	var Cell1 = newRow.insertCell();
	   	var Cell2 = newRow.insertCell();
	   	var Cell3 = newRow.insertCell();
	   	var Cell4 = newRow.insertCell();
	   	var Cell5 = newRow.insertCell();
	   	var Cell6 = newRow.insertCell();
	   	var Cell7 = newRow.insertCell();	   
	   		  		
	   	Cell1.innerHTML = '<input type="text" name="txtPatientName" class="texttag"/>'+'<input type="hidden" name="digi4_0_PTNT_NAME" id="digi4_0_PTNT_NAME" value=""/> ';
	   	Cell2.innerHTML = '<input type="text" name="txtRelation" class="texttag" />'+'<input type="hidden" name="digi4_0_RLTNSHP" id="digi4_0_RLTNSHP" value=""/> ';
	   	Cell3.innerHTML = '<input type="text" name="txtIncumbentOne" value="' + document.getElementById("txtEmpName").value + '" class="texttag" readonly /> ';
	   	Cell4.innerHTML = '<input type="text" name="txtTreatmentTime" class="texttag" />'+'<input type="hidden" name="digi4_0_TRTMT_TIME" id="digi4_0_TRTMT_TIME" value=""/>';
	   	Cell5.innerHTML = '<input id="AmtOne"0 type="text" name="txtAmtOne" class="texttag" onkeypress="amountFormat(this)" onblur="setValidAmountFormat(this)" />'+'<input type="hidden" name="digi4_0_CLAIM_AMT" id ="digi4_0_CLAIM_AMT" value=""/> ';
	   	Cell6.innerHTML = '<input type="text" name="txtRemarks" class="texttag" />'+'<input type="hidden" name="digi4_0_REMARKS" id="digi4_0_REMARKS" value=""/> ';
	   	Cell7.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveMedTableRow(this, \'TableOne\')" /> ';
	}	
    function Table_Two_AddRow()
  	{
  		++Row_Two;
  		
  		var newRow = document.all("TableTwo").insertRow(Row_Two + 1);
   	
	   	var Cell1 = newRow.insertCell();
	   	var Cell2 = newRow.insertCell();
	   	var Cell3 = newRow.insertCell();
	   	var Cell4 = newRow.insertCell();
	   	var Cell5 = newRow.insertCell();
	   		    		    	
	   	Cell1.innerHTML = '<input type="text" name="txtEstablishmentTwo" class="texttag" value="' + document.getElementById("txtEmpEstblsmnt").value + '" readonly /> <input type="hidden" name="digi5_0_ SCTN_ESTBLSHMNT"  /> ';
	   	Cell2.innerHTML = '<input type="text" name="txtIncumbentTwo" value="' + document.getElementById("txtEmpName").value + '" class="texttag" readonly />';
	   	Cell3.innerHTML = '<input type="text" name="txtClaimPeriod" class="texttag" /><input type="hidden" name="digi5_0_TRTMT_TIME"/> ';
	   	Cell4.innerHTML = '<input id="AmtTwo" type="text" name="txtAmtTwo" class="texttag" onkeypress="NumberFormet()" onblur="getGrossTotal()" /> <input type="hidden" name="digi5_0_CLAIM_AMT" /> ';
	   	Cell5.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveMedTableRow(this, \'TableTwo\'),getGrossTotal()" value="" /> ';
	}
	function RemoveMedTableRow(obj, tblId)
	{	   	 	
		var rowID = showRowCell(obj);            
	    var tbl = document.getElementById(tblId);    
	    tbl.deleteRow(rowID); 
	   	if(tblId == 'TableOne')
	    {
	    	Row_One = Row_One - 1; 
	    }
	    else if(tblId == 'TableTwo')
	    {
    	 	Row_Two = Row_Two - 1;	
    	}	        
	}
	function shuffleTabOneRows()
	{
	   var j = 0;
	   var k = 1;
	   var counter = document.getElementsByName("digi4_0_PTNT_NAME").length;	
	   
	   for(var i=1; i <= counter; i++)
	    {
	    	var Cell1 = document.getElementById('TableOne').cells(((k*7)+0));
			var Cell2 = document.getElementById('TableOne').cells(((k*7)+1));
	        var Cell3 = document.getElementById('TableOne').cells(((k*7)+2));
	        var Cell4 = document.getElementById('TableOne').cells(((k*7)+3));
	        var Cell5 = document.getElementById('TableOne').cells(((k*7)+4));
	        var Cell6 = document.getElementById('TableOne').cells(((k*7)+5));
	        var Cell7 = document.getElementById('TableOne').cells(((k*7)+6));
	    	
	    	Cell1.children[1].name = "digi4_"+j+"_PTNT_NAME";
	        Cell2.children[1].name = "digi4_"+j+"_RLTNSHP";
	        Cell4.children[1].name = "digi4_"+j+"_TRTMT_TIME";
	        Cell5.children[1].name = "digi4_"+j+"_CLAIM_AMT";
	        Cell6.children[1].name = "digi4_"+j+"_REMARKS";
	          	            		
	        Cell1.children[1].id = "digi4_"+j+"_PTNT_NAME";
	        Cell2.children[1].id = "digi4_"+j+"_RLTNSHP";
	        Cell4.children[1].id = "digi4_"+j+"_TRTMT_TIME";
        	Cell5.children[1].id = "digi4_"+j+"_CLAIM_AMT";
          	Cell6.children[1].id = "digi4_"+j+"_REMARKS";
          	k++;	    		
    		j++;
    	}	    	
	}
	function shuffleTabTwoRows()
	{
		 
    	var j = 0;
    	var k = 1;
    	var counter = document.getElementsByName("digi5_0_SCTN_ESTBLSHMNT").length;
    	
    	for(var i=1; i <= counter ; i++)
    	{
    		var Cell1 = document.getElementById('TableTwo').cells(((k*5)+1));
			var Cell2 = document.getElementById('TableTwo').cells(((k*5)+2));
       		var Cell3 = document.getElementById('TableTwo').cells(((k*5)+3));
       		var Cell4 = document.getElementById('TableTwo').cells(((k*5)+4));
       		var Cell5 = document.getElementById('TableTwo').cells(((k*5)+5));
          	           		
    		Cell1.children[1].name = "digi5_"+j+"_SCTN_ESTBLSHMNT";
       		Cell3.children[1].name = "digi5_"+j+"_TRTMT_TIME";
       		Cell4.children[1].name = "digi5_"+j+"_CLAIM_AMT";
       		           		            		
       		Cell1.children[1].id = "digi5_"+j+"_SCTN_ESTBLSHMNT";
       		Cell3.children[1].id = "digi5_"+j+"_TRTMT_TIME";
       		Cell4.children[1].id = "digi5_"+j+"_CLAIM_AMT";
       		
       		k++;	    		
    		j++;
    	}        
	}
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
	function getGrossTotal()
	{
		var ltotal = 0;
		var x = document.getElementsByName("txtAmtTwo");
		
		for(var i = 0; i < x.length ; ++i)
		{
			if(!(x[i].value == ""))
			{
				ltotal += parseInt(x[i].value);
				x[i].value = parseInt(x[i].value) + ".00";
			}
		}
		
		document.getElementById("MedBillGrossTotal").innerHTML = "<b> " + Math.format(ltotal) + " </b> ";
		document.getElementById("txtBillPassedAmt").value = parseInt(Math.round(ltotal));
		document.getElementById("BillPassAmtInWord").innerHTML = "<b> " + getAmountInWords(parseInt(Math.round(ltotal))) + " </b> ";
	}	
	function digiSignBillSpecData()
	{
		shuffleTabOneRows();
		var element = document.getElementsByName("txtPatientName");
		for(var i = 0; i < element.length; i++)
		{
			var lStr = new String(element[i].value);
			generateDigiSign(lStr,"digi4_"+i+"_PTNT_NAME");
		}
		var element = document.getElementsByName("txtRelation");
		for(var i = 0; i < element.length; i++)
		{
			var lStr = new String(element[i].value);
			generateDigiSign(lStr,"digi4_"+i+"_RLTNSHP");
		}
		var element = document.getElementsByName("txtTreatmentTime");
		for(var i = 0; i < element.length;i++)
		{
			var lStr = new String(element[i].value);
			generateDigiSign(lStr,"digi4_"+i+"_TRTMT_TIME");
		}
		var element = document.getElementsByName("txtAmtOne");
		for(var i = 0; i < element.length; i++)
		{
			var lStr = new String(element[i].value);
			generateDigiSign(lStr,"digi4_"+i+"_CLAIM_AMT");
		}
		
		var element = document.getElementsByName("txtRemarks");
		for(var i = 0; i < element.length; i++)
		{
			var lStr = new String(element[i].value);
			generateDigiSign(lStr,"digi4_"+i+"_REMARKS");
		}
		
		shuffleTabTwoRows();
		
		var element = document.getElementsByName("txtEstablishmentTwo");
		for(var i = 0; i < element.length; i++)
		{
			var lStr = new String(element[i].value);
			generateDigiSign(lStr,"digi5_"+i+"_SCTN_ESTBLSHMNT");
		}
		var element = document.getElementsByName("txtClaimPeriod");
		for(var i = 0; i < element.length; i++)
		{
			var lStr = new String(element[i].value);
			generateDigiSign(lStr,"digi5_"+i+"_TRTMT_TIME");
		}
		var element = document.getElementsByName("txtAmtTwo");
		for(var i = 0; i < element.length; i++)
		{
			var lStr = new String(element[i].value);
			generateDigiSign(lStr,"digi5_"+i+"_CLAIM_AMT");
		}
		
		generateDigiSign(document.forms[0].txtBillPassedAmt.value,"digi6_0_BILL_PASSED_AMT");
	}
	
	function validateMedBillData()
	{
		var element = document.getElementsByName("txtPatientName");
		var lFltTotalClaimAmt = 0.0;
		var lFltTotalAmountTwo = 0.0
		
		for(var i = 0; i < element.length; ++i)
		{	
			var lStr = new String(element[i].value);
			lStr = lStr.trim();
			if(lStr == "")
			{
				alert(MED_TXTPTNTNAME);
				return false;					
			}
		}
		element = document.getElementsByName("txtRelation");
		for(var i = 0; i < element.length; ++i)
		{	
			var lStr = new String(element[i].value);
			lStr = lStr.trim();
			if(lStr == "")
			{
				alert(MED_TXTRLT);
				return false;
			}
		}
		element = document.getElementsByName("txtIncumbentOne");
		for(var i = 0; i < element.length; ++i)
		{	
			var lStr = new String(element[i].value);
			lStr = lStr.trim();
			if(lStr == "")
			{
				alert(MED_TXTINCNAME);
				return false;
			}
		}
		element = document.getElementsByName("txtTreatmentTime");
		for(var i = 0; i < element.length; ++i)
		{	
			var lStr = new String(element[i].value);
			lStr = lStr.trim();
			if(lStr == "")
			{
				alert(MED_TXTTRMTPERIOD);
				return false;
			}
		}
		element = document.getElementsByName("txtAmtOne");
		for(var i = 0; i < element.length; ++i)
		{	
			var lStr = new String(element[i].value);
			lStr = lStr.trim();
			if(lStr == "")
			{
				alert(MED_TXTCLAIMAMT);
				return false;
			}
			else
			{
				lFltTotalClaimAmt += parseFloat(lStr);
			}
		}
		element = document.getElementsByName("txtEstablishmentTwo");
		for(var i = 0; i < element.length; ++i)
		{	
			var lStr = new String(element[i].value);
			lStr = lStr.trim();
			if(lStr == "")
			{
				alert(MED_TXTSECESTB);
				return false;
			}
		}
		element = document.getElementsByName("txtIncumbentTwo");
		for(var i = 0; i < element.length; ++i)
		{	
			var lStr = new String(element[i].value);
			lStr = lStr.trim();
			if(lStr == "")
			{
				alert(MED_TXTINCNAME);
				return false;
			}
		}
		element = document.getElementsByName("txtClaimPeriod");
		for(var i = 0; i < element.length; ++i)
		{	
			var lStr = new String(element[i].value);
			lStr = lStr.trim();
			if(lStr == "")
			{
				alert(MED_TXTCLAIMPERIOD);
				return false;
			}
		}
		element = document.getElementsByName("txtAmtTwo");
		for(var i = 0; i < element.length; ++i)
		{	
			var lStr = new String(element[i].value);
			lStr = lStr.trim();
			if(lStr == "")
			{
				alert(MED_TXTAMT);
				return false;
			}
			else
			{
				lFltTotalAmountTwo += parseFloat(lStr);
			}
		}	
		if(parseInt(document.getElementById("txtExpenditure").value) != Math.round(lFltTotalClaimAmt))
		{
			alert(MED_GRSAMTANDCLMAMT);
			return false;
		}	
		var lIntNetAmount = (parseInt(document.getElementById("txtExpenditure").value) 
							- parseInt(document.getElementById("txtRecovery").value))
							-(parseInt(document.getElementById("id_deductionA").value)
							+ parseInt(document.getElementById("id_deductionB").value));
		
		if(Math.round(lFltTotalAmountTwo) != lIntNetAmount)
		{
			alert(MED_NETAMTANDBILLPASSRS);
			return false;
		}			
			
		return true;
	}	