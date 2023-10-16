	var Row_One = 0;
    var Serial_No = 0;
    var Row_Num = 0;    
		function addRow()
	   	{
	   		
	   		Serial_No = Number(document.getElementById("sno").value);
	   		
	  		if(Serial_No == 0) {
		   	    Serial_No = 2;
		   	    Row_One = 1;
		   	    
		   	}else {
		   		if(Serial_No == 1)
		   		{
			   		Row_One = 1;
			   	}
			   	else
			   	{
			   		Row_One = Serial_No;
			   	}
			   				   	
		   		Serial_No = Number(Serial_No + 1);
		   	}
	  	   	
	  	   	Row_Num = Number(Serial_No)-1;	
	  	   	  
	    	var newRow = document.all("TABill").insertRow(Row_One);
	    	
	    	var Cell1 = newRow.insertCell();
	    	var Cell2 = newRow.insertCell();
	    	var Cell3 = newRow.insertCell();
	    	var Cell4 = newRow.insertCell();
	    	var Cell5 = newRow.insertCell();
	    	var Cell6 = newRow.insertCell();
	    	var Cell7 = newRow.insertCell();
	    	var Cell8 = newRow.insertCell();
	    	var Cell9 = newRow.insertCell();
	    	var Cell10 = newRow.insertCell();
	    	var Cell11 = newRow.insertCell();
	    	var Cell12 = newRow.insertCell();
	    	var Cell13 = newRow.insertCell();
	    	var Cell14 = newRow.insertCell();
	    	var Cell15 = newRow.insertCell();
	    	var Cell16 = newRow.insertCell();
	    	var Cell17 = newRow.insertCell();
			var Cell18 = newRow.insertCell();
			var Cell19 = newRow.insertCell();	    	    	
			
	    	//Cell1.innerHTML =  Serial_No ;
	    	Cell2.innerHTML = " <input type='text'  name='depStation' size='15' id='theValue' />"+"<input type='hidden' name='digi4_0_DEP_STATION'/>";
	    	Cell3.innerHTML = " <input type='text'  name='depDate["+Serial_No+"]' id='depDate["+Serial_No+"]' size='7' id='theValue' onblur='checkIsDate(this),checkDepArrDate("+Serial_No+")'/><img src='images/CalendarImages/ico-calendar.gif'   width='20' onclick='window_open(\"depDate["+Serial_No+"]\",375,300)'  />";
	    	Cell4.innerHTML = "<input type='text'   name='depHour' size='4' id='theValue' onkeypress='digitFormat()' onblur='HourFormat(this)'/>"+"<input type='hidden' name='digi4_0_DEP_HOUR'/>";
	    	Cell5.innerHTML = "<input type='text'   name='arrStation' size='15' id='theValue'/>"+"<input type='hidden' name='digi4_0_ARR_STATION'/>";
	    	Cell6.innerHTML = " <input type='text'  name='arrDate["+Serial_No+"]' id='arrDate["+Serial_No+"]' size='7' id='theValue' onblur='checkIsDate(this),checkDepArrDate("+Serial_No+")'/><img src='images/CalendarImages/ico-calendar.gif'   width='20' onclick='window_open(\"arrDate["+Serial_No+"]\",375,300)' />";	    	
	    	Cell7.innerHTML = "<input type='text'  name='arrHour' size='4' id='theValue' onkeypress='digitFormat()' onblur='HourFormat(this)'/>"+"<input type='hidden' name='digi4_0_ARR_HOUR'/>";	    	
	    	Cell8.innerHTML = "<input type='text'   name='trvlMode' size='7' id='theValue'/>"+"<input type='hidden' name='digi4_0_TRVL_MODE'/>";
	    	Cell9.innerHTML = "<input type='text'   name='fareClass' size='7' id='theValue'/>"+"<input type='hidden' name='digi4_0_FARE_CLASS'/>";
	    	Cell10.innerHTML = "<input type='text'  name='fareNumber' size='7' id='theValue' onkeypress='amountFormat(this)'/>"+"<input type='hidden' name='digi4_0_FARE_NUMBER'/>";
	    	Cell11.innerHTML = "<input type='text'  name='fareAmt' size='7' id='theValue' onkeypress='amountFormat(this)' onblur='getGrossRailwayFareAmount()' />"+"<input type='hidden' name='digi4_0_FARE_AMT'/>";
	    	Cell12.innerHTML = "<input type='text'   name='admsblOrdRate' size='7' id='theValue' onkeypress='amountFormat(this)'/>"+"<input type='hidden' name='digi4_0_ADMSBL_ORD_RATE'/>";
	    	Cell13.innerHTML = "<input type='text'   name='admsblOthRate' size='7' id='theValue' onkeypress='amountFormat(this)'/>"+"<input type='hidden' name='digi4_0_ADMSBL_OTH_RATE'/>";
	    	Cell14.innerHTML = "<input type='text'   name='admsblDa' size='7' id='theValue' onkeypress='amountFormat(this)' onblur='getTotalDaclimed()'/>"+"<input type='hidden' name='digi4_0_ADMSBL_DA'/>";
	    	Cell15.innerHTML = "<input type='text'   name='numOfDaysClmed' size='7' id='theValue' onkeypress='amountFormat(this)' onblur='getTotalDaclimed()'/>"+"<input type='hidden' name='digi4_0_NUM_OF_DAYS_CLMED'/>";
	    	Cell16.innerHTML = "<input type='text'   name='purpose' size='10' id='theValue' />"+"<input type='hidden' name='digi4_0_PURPOSE'/>";
	    	Cell17.innerHTML = "<input type='text'   name='totalAmt' size='8' id='theValue' onkeypress='amountFormat(this)' onblur='getGrossTrlTotal()'/>"+"<input type='hidden' name='digi4_0_TOTAL_AMT'/>";
	    	Cell18.innerHTML = "<input type='text'  name='remarks' size='12' id='theValue' />"+"<input type='hidden' name='digi4_0_REMARKS'/>";    	
	    	Cell19.innerHTML = "<img src='images/CalendarImages/DeleteIcon.gif' onclick='delRow(this),getGrossRailwayFareAmount(),getTotalDaclimed(),getGrossTrlTotal()' />";
	    	document.getElementById("sno").value = Serial_No;    		    	
		}
	    
	    function delRow(obj)
	    {
	    	var rowID = showRowCell(obj);
	    	var Row_One = document.getElementById("sno").value;
	    	
	    	Serial_No = 0;            
		    		    
	        if(Row_One >= 0)
			{			
				document.all("TABill").deleteRow(rowID); 
				Row_One = Row_One-1 ;
			}			
			document.getElementById("sno").value = Serial_No;
				       
	    }
	      
	     function shuffleTARows()
	     {
	     
	     	var k=1;
	     	var h = 0;
	     	var counter = document.getElementsByName("digi4_0_DEP_STATION").length; 
	     	
	     	for(var i=1; i < counter ; i++)
			{
				Serial_No = i + 1;
				
				var Cell1 = document.getElementById('TABill').cells(((k*19)));
				
				
				var Cell2 = document.getElementById('TABill').cells(((k*19)+1));
				var Cell4 = document.getElementById('TABill').cells(((k*19)+3));
           		var Cell5 = document.getElementById('TABill').cells(((k*19)+4));
           		var Cell7 = document.getElementById('TABill').cells(((k*19)+6));
           		var Cell8 = document.getElementById('TABill').cells(((k*19)+7));
				var Cell9 = document.getElementById('TABill').cells(((k*19)+8));
           		var Cell10 = document.getElementById('TABill').cells(((k*19)+9));
           		var Cell11 = document.getElementById('TABill').cells(((k*19)+10));
           		var Cell12 = document.getElementById('TABill').cells(((k*19)+11));
           		var Cell13 = document.getElementById('TABill').cells(((k*19)+12));
           		var Cell14 = document.getElementById('TABill').cells(((k*19)+13));
           		var Cell15 = document.getElementById('TABill').cells(((k*19)+14));
           		var Cell16 = document.getElementById('TABill').cells(((k*19)+15));
           		var Cell17 = document.getElementById('TABill').cells(((k*19)+16));
           		var Cell18 = document.getElementById('TABill').cells(((k*19)+17));
				
	    		Cell2.children[1].name = "digi4_"+h+"_DEP_STATION";
	    		Cell4.children[1].name = "digi4_"+h+"_DEP_HOUR";
           		Cell5.children[1].name = "digi4_"+h+"_ARR_STATION";
           		Cell7.children[1].name = "digi4_"+h+"_ARR_HOUR";
           		Cell8.children[1].name = "digi4_"+h+"_TRVL_MODE";
           		Cell9.children[1].name = "digi4_"+h+"_FARE_CLASS";
           		Cell10.children[1].name = "digi4_"+h+"_FARE_NUMBER";
           		Cell11.children[1].name = "digi4_"+h+"_FARE_AMT"
           		Cell12.children[1].name = "digi4_"+h+"_ADMSBL_ORD_RATE";
           		Cell13.children[1].name = "digi4_"+h+"_ADMSBL_OTH_RATE";
           		Cell14.children[1].name = "digi4_"+h+"_ADMSBL_DA";
           		Cell15.children[1].name = "digi4_"+h+"_NUM_OF_DAYS_CLMED";
           		Cell16.children[1].name = "digi4_"+h+"_PURPOSE";
           		Cell17.children[1].name = "digi4_"+h+"_TOTAL_AMT";
           		Cell18.children[1].name = "digi4_"+h+"_REMARKS";  
           		
           		Cell2.children[1].id = "digi4_"+h+"_DEP_STATION";
           		Cell4.children[1].id = "digi4_"+h+"_DEP_HOUR";
           		Cell5.children[1].id = "digi4_"+h+"_ARR_STATION";
           		Cell7.children[1].id = "digi4_"+h+"_ARR_HOUR";
           		Cell8.children[1].id = "digi4_"+h+"_TRVL_MODE";
           		Cell9.children[1].id = "digi4_"+h+"_FARE_CLASS";
           		Cell10.children[1].id = "digi4_"+h+"_FARE_NUMBER";
           		Cell11.children[1].id = "digi4_"+h+"_FARE_AMT"
           		Cell12.children[1].id = "digi4_"+h+"_ADMSBL_ORD_RATE";
           		Cell13.children[1].id = "digi4_"+h+"_ADMSBL_OTH_RATE";
           		Cell14.children[1].id = "digi4_"+h+"_ADMSBL_DA";
           		Cell15.children[1].id = "digi4_"+h+"_NUM_OF_DAYS_CLMED";
           		Cell16.children[1].id = "digi4_"+h+"_PURPOSE";
           		Cell17.children[1].id = "digi4_"+h+"_TOTAL_AMT";
           		Cell18.children[1].id = "digi4_"+h+"_REMARKS";  
           		k++;
				}
			     
	     
	     } 
	    function incSerialNo()
	    {
	       Serial_No = Serial_No + 1;
	    }
	    
	    function getGrossRailwayFareAmount()
		{
			var ltotal = 0;
			var x = document.getElementsByName("fareAmt");
			
			for(var i = 0; i < x.length ; ++i)
			{
				if(!(x[i].value == ""))
				{
					ltotal += Number(x[i].value);
				}
			}
			var TableCal = document.getElementById("rlwStmFare");
	    
	    	if(TableCal != null)
	    	{
	    		// TableCal.innerHTML = "<b> " + ltotal + " </b> ";
	    		TableCal.value = Math.round(ltotal);
	    	}
	    	
	    	var ltot = document.getElementById("lttlta");
	    	  		ltot.innerHTML = Math.round(ltotal);
	    	
	    	getTotalDaclimed();
		}
		
		function setTaDaGrossAmt(){
		
			var ltatot = document.getElementById("lttlta");
			var ldatot = document.getElementById("lttlda");
			var lgrosstot = document.getElementById("lttlgross");
			
				ltot = Number(ltatot.innerHTML) + Number(ldatot.innerHTML);
				lgrosstot.innerHTML = "<b> " + Math.round(ltot) + " </b> ";
				
			getGrossAmtTotal();
		}
		
		function getGrossTrlTotal()
		{
			var ltotal = 0;
			var x = document.getElementsByName("admsblDa");
			var y = document.getElementsByName("numOfDaysClmed");
			
			for(var i = 0; i < x.length ; ++i)
			{
				if(!(x[i].value == ""))
				{
					lDa = Number(x[i].value);
				}else{
					lDa = 0;
				}
				if(!(y[i].value == ""))
				{
					lDay = Number(y[i].value);
				}else{
					lDay = 0;
				}
				ltotal = ltotal + (lDa * lDay);
			}
			var ltot = document.getElementById("lttlda");
	    	  		ltot.innerHTML = Math.round(ltotal);
	    	  		
	    	setTaDaGrossAmt();
	    }
		
		function getTotalDaclimed()
		{
			var lDa = 0;
			var lDay = 0;
			var lfareAmt = 0;
			var ltotal = 0;
			var x = document.getElementsByName("admsblDa");
			var y = document.getElementsByName("numOfDaysClmed");
			var z = document.getElementsByName("fareAmt");			
			var totAmt = document.getElementsByName("totalAmt");
			
			for(var i = 0; i < x.length ; ++i)
			{
				if(!(x[i].value == ""))
				{
					lDa = Number(x[i].value);
				}else{
					lDa = 0;
				}
				if(!(y[i].value == ""))
				{
					lDay = Number(y[i].value);
				}else{
					lDay = 0;
				}
				if(!(z[i].value == ""))
				{
					lfareAmt = Number(z[i].value);
				}else{
					lfareAmt = 0;
				}
				
				ltotal = ltotal + (lDa * lDay);
				
				if(totAmt != null)
		    	{
		    		// TableCal.innerHTML = "<b> " + ltotal + " </b> ";
		    		totAmt[i].value = Math.round(lDa * lDay) + Math.round(lfareAmt);
		    	}
				getGrossTrlTotal();
			}
			var TableCal = document.getElementById("daysClaimed");
					
	    	if(TableCal != null)
	    	{
	    		// TableCal.innerHTML = "<b> " + ltotal + " </b> ";
	    		TableCal.value = Math.round(ltotal);
	    	}
	    	
	    	getGrossAmtTotal();	    	
	    	
		}
	    
		function HourFormat(temp)
		{	
			var valTime = new RegExp("^([0-1][0-9]|[2][0-3]):([0-5][0-9])$");
			
			if(temp.value != ""){
				if (temp.value.match(valTime) == null) {
					alert(TA_ALRT_VALIDHOUR);
					temp.value = "";
					temp.focus();
				}
			}
		}
		
		function getGrossAmtTotal(){
			var m_rlwStmFare, m_rdTrvlChrgs,m_daysClaimed,m_incExp,m_GrossAmt = 0;
									
			if(document.getElementById("rlwStmFare").value != null)
				m_rlwStmFare = Number(document.getElementById("rlwStmFare").value);
				
			if(document.getElementById("rdTrvlChrgs").value != null)
				m_rdTrvlChrgs = Number(document.getElementById("rdTrvlChrgs").value);
			
			if(document.getElementById("daysClaimed").value != null)
				m_daysClaimed = Number(document.getElementById("daysClaimed").value);
				
			if(document.getElementById("incExp").value != null)
				m_incExp = Number(document.getElementById("incExp").value);
					
			m_GrossAmt = m_rlwStmFare + m_rdTrvlChrgs + m_daysClaimed + m_incExp;
			
			document.getElementById("grossTot").value = Math.round(m_GrossAmt);
			
			getNetClaimedAmt();
		
		}
		
		function checkDepArrDate(SrNo){
			
			var m_depDate;
			var m_arrDate;
			if(SrNo != null && SrNo > 0){
				
				var m_depDate = "depDate["+SrNo+"]";
				var m_arrDate = "arrDate["+SrNo+"]";
			}
			
			if((document.getElementById(m_depDate).value != "") && (document.getElementById(m_arrDate).value != "")) {
			
				var chk_depDate = document.getElementById(m_depDate).value;
				var chk_arrDate = document.getElementById(m_arrDate).value;
				
				if(!isProper(chk_depDate,chk_arrDate))	{
				
					return this;
				}
			}
		}
		
		function checkIsDate(p_txtDate){
			
			var m_chkDate = p_txtDate.name;
			
			if(document.getElementById(m_chkDate).value != 0){
				if(!isDateTA(document.getElementById(m_chkDate).value)){
				
					document.getElementById(m_chkDate).focus();
					return this;
				}
			}
		}
				
		function amtInWord() {
			var amt = Number(document.getElementById('passedAmt').value);
			var amtInWord = document.getElementById('amtInWord');
			var wordData;
		
			if(amt != null ){
				wordData = getAmountInWords(amt);
				amtInWord.innerHTML = wordData;
			}
		}
		
		function setPassedForRs(){
			document.getElementById('passedAmt').value = document.getElementById('netAmt').value;
			amtInWord();
		}
		
		function getNetClaimedAmt(){
			var grossTot = 0;
			var dedct = 0;
			var netClaim = 0;
			
			if(document.getElementById("grossTot").value > 0)
				grossTot = Number(document.getElementById("grossTot").value);
								
			if(document.getElementById("dedTotal").value > 0)
				dedct = Number(document.getElementById("dedTotal").value);
			
			netClaim = Number(grossTot - dedct) ;
			
			document.getElementById("netAmt").value = Math.round(netClaim);
			document.getElementById("passedAmt").value = Math.round(netClaim);
			amtInWord();
		}
		
		function setDedTotal(){
			var taadv = 0;
			var pta = 0;
			var dedtot = 0;
			
			dedtot = Number(document.getElementById("taAdv").value) + Number(document.getElementById("pta").value);
			
			document.getElementById("dedTotal").value = Math.round(dedtot);
			getNetClaimedAmt();			
			
		}
				
		function validateTABillData()
		{
			var element = document.getElementsByName("empPay");
			
			for(var i = 0; i < element.length; ++i)
			{	
				var lStr = new String(element[i].value);
				lStr = lStr.trim();
				if(lStr == "")
				{
					alert(TA_ALRT_PAYNOTNULL);
					return false;
				}
			}
			
			element = document.getElementsByName("grossTotal");
			for(var i = 0; i < element.length; ++i)
			{	
				var lStr = new String(element[i].value);
				lStr = lStr.trim();
				if(lStr == "")
				{
					alert(TA_ALRT_CROSSTOTALNOTNULL);
					return false;
				}
			}
		
			element = document.getElementsByName("netAmt");
			for(var i = 0; i < element.length; ++i)
			{	
				var lStr = new String(element[i].value);
				lStr = lStr.trim();
				if(lStr == "")
				{
					alert(TA_ALRT_NETCLAIMNOTNULL);
					return false;
				}
			}
						
			element = document.getElementsByName("passedAmt");
			for(var i = 0; i < element.length; ++i)
			{	
				var lStr = new String(element[i].value);
				lStr = lStr.trim();
				if(lStr == "")
				{
					alert(TA_ALRT_PASSEDRSNOTNULL);
					return false;
				}
			}
			
			/// Check Expenditure and Gross Total
			var m_ChkBudgetNetTotal = (parseInt(document.getElementById("txtExpenditure").value));			
			var m_TaBillGrossTot = parseFloat(document.getElementById("grossTot").value);
						
			if(!(m_TaBillGrossTot == m_ChkBudgetNetTotal))
			{
				alert("The Expenditure and Gross Total must be same");
				return false;
			}
			
			/// Check Net Total and Net Claimed
			var m_ChkBudgetNetAmount = (parseInt(document.getElementById("txtExpenditure").value) 
							- parseInt(document.getElementById("txtRecovery").value))
							-(parseInt(document.getElementById("id_deductionA").value)
							+ parseInt(document.getElementById("id_deductionB").value));			
			var m_TaBillNetClaimAmt = parseFloat(document.getElementById("netAmt").value);
						
			if(!(m_TaBillNetClaimAmt == m_ChkBudgetNetAmount))
			{
				alert("The Net Total and Net Claimed must be same");
				return false;
			}			
										
			return true;
		}
		function digiSignBillSpecData()
		{
			
			shuffleTARows();
			var empPay = document.getElementById("empPay");
			var empPta = document.getElementById("empPta");
			var empCa = document.getElementById("empCa");
			var headQtr =document.getElementById("headQtr");
			
			generateDigiSign(empPay.value,"digi6_0_EMP_PAY");
			generateDigiSign(empPta.value,"digi6_0_EMP_PTA");
			generateDigiSign(empCa.value,"digi6_0_EMP_CA");
			generateDigiSign(headQtr.value,"digi6_0_HEAD_QTR");
			
			var rlwStmFare = document.getElementById("rlwStmFare");
			var rdTrvlChrgs = document.getElementById("rdTrvlChrgs");
			var daysClaimed=document.getElementById("daysClaimed");
			var incExp= document.getElementById("incExp");
			var grossTotal=document.getElementById("grossTot");
			var taAdv=document.getElementById("taAdv");
			var ptaDays =document.getElementById("ptaDays");
			var pta= document.getElementById("pta");
			var netAmt = document.getElementById("netAmt");
			var passedAmt =document.getElementById("passedAmt");
			
			generateDigiSign(rlwStmFare.value,"digi5_0_RLW_STM_FARE");
			generateDigiSign(rdTrvlChrgs.value,"digi5_0_RD_TRL_FARE");
			generateDigiSign(daysClaimed.value,"digi5_0_TTL_DA_CLMED");
			generateDigiSign(incExp.value,"digi5_0_INCDNTL_EXP");
			generateDigiSign(grossTotal.value,"digi5_0_GROSS_TOTAL");
			generateDigiSign(taAdv.value,"digi5_0_DED_TA_ADV");
			generateDigiSign(ptaDays.value,"digi5_0_DED_PTA_DAYS");
			generateDigiSign(pta.value,"digi5_0_DED_PTA");
			generateDigiSign(netAmt.value,"digi5_0_NET_CLAIMED");
			generateDigiSign(passedAmt.value,"digi5_0_BILL_PASSED_AMT");
					
			var element = document.getElementsByName("depStation");
			for(var i = 0; i < element.length; i++)
			{
				var lStr = new String(element[i].value);
				generateDigiSign(lStr,"digi4_"+i+"_DEP_STATION");
			}
			
			var element = document.getElementsByName("depHour");
			for(var i = 0; i < element.length; i++)
			{
				var lStr = new String(element[i].value);
				generateDigiSign(lStr,"digi4_"+i+"_DEP_HOUR");
			}
			
			var element = document.getElementsByName("arrStation");
			for(var i = 0; i < element.length; i++)
			{
				var lStr = new String(element[i].value);
				generateDigiSign(lStr,"digi4_"+i+"_ARR_STATION");
			}
			
			var element = document.getElementsByName("arrHour");
			for(var i = 0; i < element.length; i++)
			{
				var lStr = new String(element[i].value);
				generateDigiSign(lStr,"digi4_"+i+"_ARR_HOUR");
			}
			
			var element = document.getElementsByName("trvlMode");
			for(var i = 0; i < element.length; i++)
			{
				var lStr = new String(element[i].value);
				generateDigiSign(lStr,"digi4_"+i+"_TRVL_MODE");
			}
			
			var element = document.getElementsByName("fareClass");
			for(var i = 0; i < element.length; i++)
			{
				var lStr = new String(element[i].value);
				generateDigiSign(lStr,"digi4_"+i+"_FARE_CLASS");
			}
			
			var element = document.getElementsByName("fareNumber");
			for(var i = 0; i < element.length; i++)
			{
				var lStr = new String(element[i].value);
				generateDigiSign(lStr,"digi4_"+i+"_FARE_NUMBER");
			}
			
			var element = document.getElementsByName("fareAmt");
			for(var i = 0; i < element.length; i++)
			{
				var lStr = new String(element[i].value);
				generateDigiSign(lStr,"digi4_"+i+"_FARE_AMT");
			}
			
			var element = document.getElementsByName("admsblOrdRate");
			for(var i = 0; i < element.length; i++)
			{
				var lStr = new String(element[i].value);
				generateDigiSign(lStr,"digi4_"+i+"_ADMSBL_ORD_RATE");
			}
			
			var element = document.getElementsByName("admsblOthRate");
			for(var i = 0; i < element.length; i++)
			{
				var lStr = new String(element[i].value);
				generateDigiSign(lStr,"digi4_"+i+"_ADMSBL_OTH_RATE");
			}
			
			var element = document.getElementsByName("admsblDa");
			for(var i = 0; i < element.length; i++)
			{
				var lStr = new String(element[i].value);
				generateDigiSign(lStr,"digi4_"+i+"_ADMSBL_DA");
			}
			
			var element = document.getElementsByName("numOfDaysClmed");
			for(var i = 0; i < element.length; i++)
			{
				var lStr = new String(element[i].value);
				generateDigiSign(lStr,"digi4_"+i+"_NUM_OF_DAYS_CLMED");
			}
			
			var element = document.getElementsByName("purpose");
			for(var i = 0; i < element.length; i++)
			{
				var lStr = new String(element[i].value);
				generateDigiSign(lStr,"digi4_"+i+"_PURPOSE");
			}
			
			var element = document.getElementsByName("totalAmt");
			for(var i = 0; i < element.length; i++)
			{
				var lStr = new String(element[i].value);
				generateDigiSign(lStr,"digi4_"+i+"_TOTAL_AMT");
			}
			
			var element = document.getElementsByName("remarks");
			for(var i = 0; i < element.length; i++)
			{
				var lStr = new String(element[i].value);
				generateDigiSign(lStr,"digi4_"+i+"_REMARKS");
			}
					
		}
		
		function isDateTA(dtStr)
		{
			var daysInMonth = DaysArray(12)
			var pos1=dtStr.indexOf(dtCh)
			var pos2=dtStr.indexOf(dtCh,pos1+1)
			var strDay=dtStr.substring(0,pos1)
			var strMonth=dtStr.substring(pos1+1,pos2)
			var strYear=dtStr.substring(pos2+1)
			strYr=strYear
			if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
			if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
			for (var i = 1; i <= 3; i++) {
				if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
			}
			month=parseInt(strMonth)
			day=parseInt(strDay)
			year=parseInt(strYr)
			if (pos1==-1 || pos2==-1)
			{
				alert(SRCH_DTFORMAT);
				return false;
			}
			if (strMonth.length<1 || month<1 || month>12)
			{
				alert(SRCH_VALMNTH);
				return false;
			}
			if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month])
			{
				alert(SRCH_VALDAY);
				return false;
			}
			if (strYear.length != 4 || year==0 || year<minYear || year>maxYear)
			{
				alert(SRCH_VALDIGIT);
				return false;
			}
			if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false)
			{
				alert(SRCH_VALDT);
				return false;
			}
			return true;
		}
		function daysInFebruary (year)
		{
		   return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
		}
		function DaysArray(n) {
			for (var i = 1; i <= n; i++) {
				this[i] = 31
				if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
				if (i==2) {this[i] = 29}
	  	 	} 
	   		return this;
		}
		function isInteger(s){
			var i;
		    for (i = 0; i < s.length; i++){   
		        var c = s.charAt(i);
		        if (((c < "0") || (c > "9"))) return false;
		    }
		    return true;
		}
		function stripCharsInBag(s, bag){
			var i;
		    var returnString = "";
		    
		    for (i = 0; i < s.length; i++){   
		        var c = s.charAt(i);
		        if (bag.indexOf(c) == -1) returnString += c;
		    }
		    return returnString;
		}
		function isProper(frmStr,toStr)
		{
			if(frmStr.length > 0 && frmStr.length > 0)
			{
				var frmdaysInMonth = DaysArray(12)
				var frmpos1=frmStr.indexOf(dtCh)
				var frmpos2=frmStr.indexOf(dtCh,frmpos1+1)
				var frmDay=frmStr.substring(0,frmpos1)
				var frmMonth=frmStr.substring(frmpos1+1,frmpos2)
				var frmYear=frmStr.substring(frmpos2+1)
				
				var todaysInMonth = DaysArray(12)
				var topos1=toStr.indexOf(dtCh)
				var topos2=toStr.indexOf(dtCh,topos1+1)
				var toDay=toStr.substring(0,topos1)
				var toMonth=toStr.substring(topos1+1,topos2)
				var toYear=toStr.substring(topos2+1)
				
				if(frmYear > toYear)
				{
					alert(SRCH_PROYR);
					return false;
				} 
				if(frmYear == toYear) 
				{
					if(frmMonth > toMonth)
					{
						alert(SRCH_PROMNTH);
						return false;
					}
				} 
				if(frmMonth == toMonth)
				{
					if(frmDay > toDay)
					{
						alert(SRCH_PRODAY);
						return false;
					}
				}
				return true;	
			}		
		}