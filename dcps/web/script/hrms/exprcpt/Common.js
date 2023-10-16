function computeSum(counter,edpCode,rowNo,edpType)
	{	
		var aAmount=0;
		
		var _edpTypeExp="EXP";
		var _edpTypeRec="REC";
		var _edpTypeRpt="RCP";				
		if(rowNo!='-')
		{
			if(edpType==_edpTypeExp)
			{
				aAmount=document.getElementById("id_txtAmt"+rowNo).value
				if(aAmount.length==0 || aAmount==null)
				{
					document.getElementById("id_txtAmt"+rowNo).value=0;
				}
				array[counter]['amount']=document.getElementById("id_txtAmt"+rowNo).value;
			}
			if(edpType==_edpTypeRec)
			{
				aAmount=document.getElementById("id_txtRecAmt"+rowNo).value
				if(aAmount.length==0 || aAmount==null)
				{
					document.getElementById("id_txtRecAmt"+rowNo).value=0;
				}
				array[counter]['amount']=document.getElementById("id_txtRecAmt"+rowNo).value;
			}
			if(edpType==_edpTypeRpt)
			{
				aAmount=document.getElementById("id_txtRcptAmt"+rowNo).value
				if(aAmount.length==0 || aAmount==null)
				{
					document.getElementById("id_txtRcptAmt"+rowNo).value=0;
				}
				array[counter]['amount']=document.getElementById("id_txtRcptAmt"+rowNo).value;
			}
		}
		else
		{
			if(edpType==_edpTypeRec)
			{
				aAmount=document.getElementById("id_txtRecAmt"+edpCode).value
				if(aAmount.length==0 || aAmount==null)
				{
					document.getElementById("id_txtRecAmt"+edpCode).value=0;
				}
				array[counter]['amount']=document.getElementById("id_txtRecAmt"+edpCode).value;
			}
			else
			{
				aAmount=document.getElementById("id_txtAmt"+edpCode).value
				if(aAmount.length==0 || aAmount==null)
				{
					document.getElementById("id_txtAmt"+edpCode).value=0;
				}
				array[counter]['amount']=document.getElementById("id_txtAmt"+edpCode).value;
			}
		}
		var amountA=0,amountB=0,amountO=0,amountRec=0,amountExp=0;
		
		for(i=0;i<array.length;i++)
		{
		
			if(array[i]['expRcpRev']=="Expenditure")
			{
				amountExp=parseFloat(amountExp)+parseFloat(array[i]['amount'])
				document.forms[0].txtExpenditure.value=amountExp;
			}
			if(array[i]['expRcpRev']=="Recovery")
			{
				amountRec=parseFloat(amountRec)+parseFloat(array[i]['amount'])
				document.forms[0].txtRecovery.value=amountRec;
			}
			if(array[i]['expRcpRev']=="Receipt")
			{
				if(array[i]['category']=='A')
				{
					amountA=parseFloat(amountA)+parseFloat(array[i]['amount'])
					document.forms[0].DeductionA.value=amountA
				}
				if(array[i]['category']=='B')
				{
					amountB=parseFloat(amountB)+parseFloat(array[i]['amount'])
					document.forms[0].DeductionB.value=amountB
				}
			}
		}
	}	
function computeSumAftDelete()
	{	
		var amountA=0,amountB=0,amountO=0,amountRec=0,amountExp=0;
		for(i=0;i<array.length;i++)
		{
			if(array[i]['expRcpRev']=="Expenditure")
			{
				amountExp=parseFloat(amountExp)+parseFloat(array[i]['amount'])
				document.forms[0].txtExpenditure.value=amountExp;
			}
			if(array[i]['expRcpRev']=="Recovery")
			{
				amountRec=parseFloat(amountRec)+parseFloat(array[i]['amount'])
				document.forms[0].txtRecovery.value=amountRec;
			}
			if(array[i]['expRcpRev']=="Receipt")
			{
				if(array[i]['category']=='A')
				{
					amountA=parseFloat(amountA)+parseFloat(array[i]['amount'])
					document.forms[0].DeductionA.value=amountA
				}
				if(array[i]['category']=='B')
				{
					amountB=parseFloat(amountB)+parseFloat(array[i]['amount'])
					document.forms[0].DeductionB.value=amountB
				}
			}
		}
	}	
function deleteRow(vRow,vCounter)
	{
		array[vCounter]['amount']=0;
		array[vCounter]['edpCode']="";
		computeSumAftDelete();
		var i=vRow.parentNode.parentNode.rowIndex
  		document.getElementById('ExpDetPostTbl').deleteRow(i)
	}
	function deleteRecRow(vRow,vCounter)
	{
		array[vCounter]['amount']=0;
		array[vCounter]['edpCode']="";
		computeSumAftDelete();
		var i=vRow.parentNode.parentNode.rowIndex
  		document.getElementById('recDetPostTbl').deleteRow(i)
	}
	function deleteRcptRow(vRow,vCounter)
	{
		array[vCounter]['amount']=0;
		array[vCounter]['edpCode']="";
		computeSumAftDelete();
		var i=vRow.parentNode.parentNode.rowIndex
  		document.getElementById('rcptDetPostTbl').deleteRow(i)
	}
	
	function addEdpRow()
	{
		  var tbody = document.getElementById('ExpDetPostTbl').getElementsByTagName('tbody')[0]; 
		  //table id is : DetailPostingTbl
		  edpRowNo++;
		  var rowType1="EXP"
		  var row = document.createElement('TR'); 
		  row.className="odd";
		  row.height="20";
		  var cell1 = document.createElement('TD'); 
		  var cell2 = document.createElement('TD'); 
		  cell2.id = "id_txtEdpDesc" + edpRowNo;
		  var cell5 = document.createElement('TD'); 
		  cell5.id = "id_txtBudCode" + edpRowNo;	
		  var cell6 = document.createElement('TD'); 
		
		  cell1.innerHTML = "<input type=\"hidden\" name=\"exprows\" value=\""+edpRowNo+"\"><input type=\"text\" size=\"6\" maxlength=\"4\" name=\"txtEdpCode"+edpRowNo+"\" id=\"id_txtEdpCode"+edpRowNo+"\"  onblur=getExpEdpDtls(this,"+edpRowNo+","+counter+")><input type=\"hidden\" size=\"6\" name=\"txtDedType"+edpRowNo+"\" id=\"id_txtDedType"+edpRowNo+"\" value=\"O\"><input type=\"hidden\" size=\"6\" name=\"txtAddDed"+edpRowNo+"\" id=\"id_txtAddDed"+edpRowNo+"\"> ";
		  cell2.innerHTML = ""; 
		  cell5.innerHTML = "";
//		  cell2.innerHTML = "<input type=\"text\" name=\"txtEdpDesc"+edpRowNo+"\" id=\"id_txtEdpDesc"+edpRowNo+"\">";
//		  cell5.innerHTML = "<input type=\"text\" size=\"6\" name=\"txtBudCode"+edpRowNo+"\" id=\"id_txtBudCode"+edpRowNo+"\">";
		  cell6.innerHTML = "<input type=\"text\" size=\"9\" value=\"0\" name=\"txtAmt"+edpRowNo+"\" id=\"id_txtAmt"+edpRowNo+"\" onblur=computeSumExtSum(this,"+edpRowNo+",'EXP',"+counter+") onkeypress=AmountFormet()><img src=images/CalendarImages/DeleteIcon.gif onclick=deleteRow(this,"+counter+")>";


		  row.appendChild(cell1); 
		  row.appendChild(cell2); 
		  row.appendChild(cell5); 
       	  row.appendChild(cell6); 
		  tbody.appendChild(row); 
		  var subArray=new Array();
          subArray['expRcpRev']='Expenditure';						
		  subArray['amount']=0;
		  array[counter]=subArray;
		  counter=counter+1;
		  document.getElementById("id_txtEdpCode"+edpRowNo).focus();
	}
	
	function addRecEdpRow()
	{
		  var tbody = document.getElementById('recDetPostTbl').getElementsByTagName('tbody')[0]; 
		  //table id is : DetailPostingTbl
		  recEdpRowNo++;

		  var row = document.createElement('TR'); 
		  row.className="odd";
		  row.height="20";
		  var cell1 = document.createElement('TD'); 
		  var cell2 = document.createElement('TD'); 
		  cell2.id = "id_txtRecEdpDesc" + recEdpRowNo;
		  var cell5 = document.createElement('TD'); 
		  cell5.id = "id_txtRecBudCode" + recEdpRowNo;
		  var cell6 = document.createElement('TD'); 
		
		  cell1.innerHTML = "<input type=\"hidden\" name=\"recRows\" value=\""+recEdpRowNo+"\"><input type=\"text\" size=\"6\" maxlength=\"4\" name=\"txtRecEdpCode"+recEdpRowNo+"\" id=\"id_txtRecEdpCode"+recEdpRowNo+"\"  onblur=getRecEdpDtls(this,"+recEdpRowNo+","+counter+")><input type=\"hidden\" size=\"6\" name=\"txtRecDedType"+recEdpRowNo+"\" id=\"id_txtRecDedType"+recEdpRowNo+"\" value=\"O\"> <input type=\"hidden\" size=\"6\" name=\"txtRecAddDed"+recEdpRowNo+"\" id=\"id_txtRecAddDed"+recEdpRowNo+"\"> ";
		  cell2.innerHTML = "";
		  cell5.innerHTML = "";
		 // cell2.innerHTML = "<input type=\"text\" name=\"txtRecEdpDesc"+recEdpRowNo+"\" id=\"id_txtRecEdpDesc"+recEdpRowNo+"\">";
		 // cell5.innerHTML = "<input type=\"text\" size=\"6\" name=\"txtRecBudCode"+recEdpRowNo+"\" id=\"id_txtRecBudCode"+recEdpRowNo+"\">";
		  cell6.innerHTML = "<input type=\"text\" size=\"9\" value=\"0\" name=\"txtRecAmt"+recEdpRowNo+"\" id=\"id_txtRecAmt"+recEdpRowNo+"\" onblur=computeSumExtSum(this,"+recEdpRowNo+",'REC',"+counter+") onkeypress=AmountFormet()><img src=images/CalendarImages/DeleteIcon.gif onclick=deleteRecRow(this,"+counter+")>";

		  row.appendChild(cell1); 
		  row.appendChild(cell2); 
		 // row.appendChild(cell3); 
		 // row.appendChild(cell4); 
		  row.appendChild(cell5); 
       	  row.appendChild(cell6); 
		  tbody.appendChild(row); 
		  var subArray=new Array();
          subArray['expRcpRev']='Recovery';						
		  subArray['amount']=0;
		  array[counter]=subArray;
		  counter=counter+1;
		  document.getElementById("id_txtRecEdpCode"+recEdpRowNo).focus();
	}
	
	function addRcptEdpRow()
	{
		  var tbody = document.getElementById('rcptDetPostTbl').getElementsByTagName('tbody')[0]; 
		  //table id is : DetailPostingTbl
		  rcptEdpRowNo++;

		  var row = document.createElement('TR'); 
		  row.className="odd";
		  row.height="20";
		  var cell1 = document.createElement('TD'); 
		  var cell2 = document.createElement('TD'); 
		  var cell3 = document.createElement('TD'); 
		   cell3.id = "id_txtRcptMjrHdCode" + rcptEdpRowNo;		  
		  var cell4 = document.createElement('TD'); 
		  cell4.id = "id_txtRcptSubMjrHdCode" + rcptEdpRowNo;
		  var cell5 = document.createElement('TD'); 
		  cell5.id = "id_txtRcptMinHdCode" + rcptEdpRowNo;
		  var cell6 = document.createElement('TD'); 
		  cell6.id = "id_txtRcptSubHdCode" + rcptEdpRowNo;
  		  var cell8 = document.createElement('TD'); 
		
		  cell1.innerHTML = "<input type=\"hidden\" name=\"rcptRows\" value=\""+rcptEdpRowNo+"\"><input type=\"text\" size=\"6\" maxlength=\"4\" name=\"txtRcptEdpCode"+rcptEdpRowNo+"\" id=\"id_txtRcptEdpCode"+rcptEdpRowNo+"\"  onblur=getRcptEdpDtls(this,"+rcptEdpRowNo+","+counter+")> ";
		  cell2.innerHTML = "<input type=\"text\" size=\"6\" name=\"txtRcptDedType"+rcptEdpRowNo+"\" id=\"id_txtRcptDedType"+rcptEdpRowNo+"\" onkeypress=isValidDedctionType()> ";
		  cell3.innerHTML = "";
  		  cell4.innerHTML = "";
  		  cell5.innerHTML = "";
  		  cell6.innerHTML = "";
		 // cell3.innerHTML = "<input type=\"text\" size=\"6\" name=\"txtRcptMjrHdCode"+rcptEdpRowNo+"\" id=\"id_txtRcptMjrHdCode"+rcptEdpRowNo+"\">";
  		 // cell4.innerHTML = "<input type=\"text\" size=\"6\" name=\"txtRcptSubMjrHdCode"+rcptEdpRowNo+"\" id=\"id_txtRcptSubMjrHdCode"+rcptEdpRowNo+"\">";
  		 // cell5.innerHTML = "<input type=\"text\" size=\"6\" name=\"txtRcptMinHdCode"+rcptEdpRowNo+"\" id=\"id_txtRcptMinHdCode"+rcptEdpRowNo+"\">";
  		  //cell6.innerHTML = "<input type=\"text\" size=\"6\" name=\"txtRcptSubHdCode"+rcptEdpRowNo+"\" id=\"id_txtRcptSubHdCode"+rcptEdpRowNo+"\">";
  		//  cell7.innerHTML = "<input type=\"text\" size=\"6\" name=\"txtRcptBudHdCode"+rcptEdpRowNo+"\" id=\"id_txtRcptBudHdCode"+rcptEdpRowNo+"\">";
		  cell8.innerHTML = "<input type=\"text\" size=\"7\" value=\"0\" name=\"txtRcptAmt"+rcptEdpRowNo+"\" id=\"id_txtRcptAmt"+rcptEdpRowNo+"\" onblur=computeSumExtSum(this,"+rcptEdpRowNo+",'RCP',"+counter+") onkeypress=AmountFormet()><img src=images/CalendarImages/DeleteIcon.gif onclick=deleteRcptRow(this,"+counter+")>";
		  row.appendChild(cell1); 
		  row.appendChild(cell2); 
		  row.appendChild(cell3); 
		  row.appendChild(cell4); 
		  row.appendChild(cell5); 
       	  row.appendChild(cell6); 
       	 // row.appendChild(cell7); 
		  row.appendChild(cell8); 
		  tbody.appendChild(row);
		  var subArray=new Array();
          subArray['expRcpRev']='Receipt';						
		  subArray['amount']=0;
		  array[counter]=subArray;
		  counter=counter+1; 
		  document.getElementById("id_txtRcptEdpCode"+rcptEdpRowNo).focus();
	}
	
	function isValidDedctionType()
	{
		if(window.event.keyCode == 97)
		{
			window.event.keyCode = 65;
			return;
		}
		if(window.event.keyCode == 98)
		{
			window.event.keyCode = 66;
			return;
		}	
		if(window.event.keyCode == 65 || window.event.keyCode == 66)
		{
			return;
		}
		window.event.keyCode = 0;
	}
	function AmountFormet()
	{
		if(!((window.event.keyCode > 47 && window.event.keyCode < 58) || window.event.keyCode == 46))
		{
			window.event.keyCode = 0;
		}
	} 

