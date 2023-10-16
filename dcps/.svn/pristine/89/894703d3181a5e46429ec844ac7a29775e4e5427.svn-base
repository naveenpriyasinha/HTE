<%@ include file="../core/include.jsp" %>

<head>
	<script>
		function getBook()
		{
		  if(document.forms[0].cmbBPNDescription.value == 0)
		  {
		    	alert("Please select the module");
		  }
		  else
		  {
		  		if (document.forms[0].cmbLanguage.value == '1')
		  		{
		  			var URL = contextPath+'/usermanual/' + document.forms[0].cmbBPNDescription.value + '.pdf';
			  		window.open(URL ,"filee_window","toolbar=no, location=no, directories=no,status=yes, menubar=yes, scrollbars=yes, resizable=yes, copyhistory=no,height=700,width=1010, top=0,left=0");
			  	}
			  	else
			  	{
			  		var URL = contextPath+'/usermanual/gujarati/' + document.forms[0].cmbBPNDescription.value + '.pdf';
			  		window.open(URL ,"filee_window","toolbar=no, location=no, directories=no,status=yes, menubar=yes, scrollbars=yes, resizable=yes, copyhistory=no,height=700,width=1010, top=0,left=0");
			  	}  
		  }    
		}
	</script>
</head>
  
<body>
<form name="FrmFinalAmtSearch" method="post">

	<fieldset class="tabstyle">
	<legend id="headingMsg" class="HLabel"> User Manuals </legend> <br />
		<table align="center" width="100%">
			<tr>
		    	<td align="left" class="Label">
		    		Language :
		    	</td>
		    	<td align="left" class="Label">
		        	<select class="ListBoxGUI" name="cmbLanguage">
		        	<option  value="1">English</option>                                               
		            <option  value="2">Gujarati</option>
		            </select>
		        	<font color="#FF0000"> * </font>
		        </td>
			<tr>
		    	<td align="left" class="Label">
		    		Module Name :
		    	</td>
		    	<td align="left" class="Label">
		        	<select class="ListBoxGUI" name="cmbBPNDescription">
		        		<option value="0">---Select---</option>
		        		<option  value="UM_TBP">Bill Processing (Treasury/PAO)</option> 
		        		<option  value="UM_CI">Cheque Inventory</option>                                               
		                <option  value="UM_DPPF_GPF">GPF Accounting</option>                                               
		                <option  value="UM_DPPF_HBAMCA">HBA/MCA Accounting</option>                                               
		                <option  value="UM_DPPF_Pension">DPPF - Pension Processing</option>                                               
		                <option  value="UM_DPPF_NewPension">DPPF - New Pension Scheme</option>
		                <option  value="UM_EAP-RAM">Expenditure and Receipt Accounting</option>                                               
		                <option  value="UM_EMD">EMD (Earnest Money Deposit)</option>                                               
		                <option  value="UM_EXI">External Interface (Interfacing with AG and Banks)</option>
		                <option  value="UM_GRANT_FROM_FD">Grant-From FD To Departments</option> 
		                <option  value="UM_GRANT_FROM_DEPT">Grant-From Department To CO/DDO/Department</option> 
		                <option  value="UM_GRANT_FROM_CO">Grant-From Controlling Officer To CO/DDO</option> 
		                <option  value="UM_LCA">LC (Letter of Credit)</option>
		                <option  value="UM_OBP">Online Bill Processing</option>
		                <option  value="UM_PayFixation">Pay Fixation</option>                                                                                                                                                                                            
		                <option  value="UM_PDPLA">PDPLA (Personal Deposit Personal Ledger Accounts)</option>
		                <option  value="UM_PPO">Pension Payment</option>
		                <option  value="UM_STAMP">Stamp</option>                                                                                              
		                                                          
		                        
		        	</select>
		        	<font color="#FF0000"> * </font>
		        </td>
			</tr>
			<tr>
		 		<td align="center" colspan="2" class="Label">
		    		<input type="button" value="Download" name="btnDownload" onClick="getBook()" class="bigbutton">
		  		</td>
		  	</tr>
		</table> <br />
	</fieldset>

</form>
</body>
