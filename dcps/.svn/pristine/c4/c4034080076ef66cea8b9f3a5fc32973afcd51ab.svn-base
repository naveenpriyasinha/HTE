<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fmt:setBundle	basename="resources.PayBillEmployee.PayBillEmployeeLables_en_us" var="pensionLabels" scope="request" />
<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels"
	var="pensionLabels" scope="request" />
<script type="text/javascript"	src="script/common/val.js"></script>

<hdiits:form name="frmEmpPayDetail" encType="multipart/form-data"
	validate="true" method="post">
<table id="tbl1" width="80%" align="center">	
<tr>
<td>
	<table id="tblStudDet" width="100%" align="center" cellpadding="2px" cellspacing="2px">
		<tr>
			<td width="20%" align="left"><fmt:message key="PPMT.LASTPAY"
				bundle="${pensionLabels}"></fmt:message></td>
			<td width="30%"> <input type="text" name='txtLastPay' id="txtLastPay" style="text-align: left" onKeyPress="" />
			</td>	
			<td width="20%" align="left"><fmt:message key="PPMT.GROSSPENSION"
				bundle="${pensionLabels}"></fmt:message></td>
			<td width="30%"> <input type="text" name='txtGrossPension' id="txtGrossPension" style="text-align: left" onKeyPress="" />
			</td>	
			
		</tr>
		<tr>		
			<td width="20%" align="left"><fmt:message key="PPMT.ORIGINAL"
				bundle="${pensionLabels}"></fmt:message></td>
			<td width="30%"> <input type="text" name='txtOriginal' id="txtOriginal" style="text-align: left" onKeyPress="" />
			</td>	
			<td width="20%" align="left"><fmt:message key="PPMT.RECOVERY"
				bundle="${pensionLabels}"></fmt:message></td>
			<td width="30%"> <input type="text" name='txtRecovery' id="txtRecovery" style="text-align: left" onKeyPress="" />
			</td>	
			
		</tr>
		<tr>				
			<td width="20%" align="left"><fmt:message key="PPMT.DEARNESSPENSION"
				bundle="${pensionLabels}"></fmt:message></td>
			<td width="30%"> <input type="text" name='txtDearnessPension' id="txtDearnessPension" style="text-align: left" onKeyPress="" />
			</td>
			<td width="20%" align="left"><fmt:message key="PPMT.NETPENSION"
				bundle="${pensionLabels}"></fmt:message></td>
			<td width="30%"> <input type="text" name='txtNetPension' id="txtNetPension" style="text-align: left"  />
			</td>	
		</tr>
		
		<tr>		
			<td width="20%" align="left"><fmt:message key="PPMT.PERSONALPENSION"
				bundle="${pensionLabels}"></fmt:message></td>
			<td width="30%"> <input type="text" name="txtPresonalPension" id="txtPersonalPension"
					style="text-align: left" onKeyPress="" />			
			</td>	
			
		</tr>
		<tr>		
			<td width="20%" align="left"><fmt:message key="PPMT.REDUCEDPENSION"
				bundle="${pensionLabels}"></fmt:message></td>
			<td width="30%"> <input type="text" name="txtReducedPension" id="txtReducedPension"
					style="text-align: left" onKeyPress="" />			
			</td>	
			
		</tr>
		
	</table>

	<table id="tbl2" width="100%">
	    <tr>
	        <td>
	             <fieldset class="tabstyle"><legend> 
	             <fmt:message key="PPMT.PROVISIONALPENSION" bundle="${pensionLabels}"></fmt:message> </legend>
	             <table width="100%">
	                 <tr>
	                     <td width="20%" align="left"><fmt:message key="PPMT.PROVISIONALPENSION"
				                         bundle="${pensionLabels}"></fmt:message>
				         </td>
				         <td width="80%">
				              <input type="radio" id="radioProPension" name="radioProPension" value="Yes"/>
				              <fmt:message key="PPMT.YES" bundle="${pensionLabels}"></fmt:message>
				              <input type="radio" id="radioProPension" name="radioProPension" value="No"/>
				              <fmt:message key="PPMT.NO" bundle="${pensionLabels}"></fmt:message> 
				         </td>
	                 </tr>
	             </table>
	             <table width="100%">    
	                 <tr>
	                     <td width="20%" align="left"><fmt:message key="PPMT.PROVISIONALPENSIONAMOUNT"
				                         bundle="${pensionLabels}"></fmt:message></td>
			             <td width="30%"> <input type="text" name='txtProvPenAmount' id="txtProvPenAmount" style="text-align: left" />
			             </td>	
			             <td width="20%" align="left"><fmt:message key="PPMT.SANCTIONAUTHORITY"
				                         bundle="${pensionLabels}"></fmt:message></td>
			             <td width="30%"> <input type="text" name='txtSanctionAuthPP' id="txtSanctionAuthPP" style="text-align: left" />
			             </td>
	                 </tr>    
	                 <tr>
	                     <td width="20%" align="left"><fmt:message key="PPMT.FROM"
				                         bundle="${pensionLabels}"></fmt:message>
				             <input type="text" name='txtFrom' id="txtFrom" style="text-align: left" size="15" Readonly/>
				             <img src='images/CalendarImages/ico-calendar.gif'
					                onClick='window_open("txtFrom",375,570)'style="cursor: pointer;" ${disabled}/>
				         </td>                
			             <td width="30%">
			              <fmt:message key="PPMT.TO" bundle="${pensionLabels}"></fmt:message> 
			              <input type="text" name='txtTo' id="txtTo" style="text-align: left" size="15" Readonly/>
				             <img src='images/CalendarImages/ico-calendar.gif'
					                onClick='window_open("txtTo",375,570)'style="cursor: pointer;" ${disabled}/>
			             </td>	
			             <td width="20%" align="left"><fmt:message key="PPMT.AUTHORITYNO"
				                         bundle="${pensionLabels}"></fmt:message></td>
			             <td width="30%"> <input type="text" name='txtAuthorityNoPP' id="txtAuthorityNoPP" style="text-align: left" />
			             </td>	
	                 </tr>   
	                 <tr>
	                     <td width="20%" align="left"><fmt:message key="PPMT.TOTALAMOUNTPAID"
				                         bundle="${pensionLabels}"></fmt:message></td>
			             <td width="30%"> <input type="text" name='txtTotalAmtPaid' id="txtTotalAmtPaid" style="text-align: left" />
			             </td>	
			             <td width="20%" align="left"><fmt:message key="PPMT.AUTHORITYDATE"
				                         bundle="${pensionLabels}"></fmt:message></td>
			             <td width="30%"> <input type="text" name='txtAuthorityDatePP' id="txtAuthorityDatePP" style="text-align: left" Readonly/>
			             <img src='images/CalendarImages/ico-calendar.gif'
					                onClick='window_open("txtAuthorityDatePP",375,570)'style="cursor: pointer;" ${disabled}/>
			             </td>	
	                 </tr>    
	             </table>	 
	             
	             <fieldset class="tabstyle"><legend> 
	             <fmt:message key="PPMT.VOUCHERDETAILS" bundle="${pensionLabels}"></fmt:message> </legend>
	             <table width="100%">
	             <tr>
	             <td width="80%">
	                 <table id="tblInstDtls" align="left" width="100%" cellspacing="0" border="1">
				
			    		<tr class="datatableheader">
						   <td width="30%" class="pensionLabels"><fmt:message key="PPMT.MONTH" bundle="${pensionLabels}" ></fmt:message></td>	
						   <td width="30%" class="pensionLabels"><fmt:message key="PPMT.VOUCHERNO" bundle="${pensionLabels}"></fmt:message></td>
						   <td width="30%" class="pensionLabels"><fmt:message key="PPMT.VOUCHERDATE" bundle="${pensionLabels}"></fmt:message></td>
						   <td width="10%" class="pensionLabels"></td>						   						   
					    </tr>
					    
				     </table>
				 </td>
				 <td width="20%" valign="top">
				 <hdiits:button name="InstDtlsAddRow" type="button" captionid="PPMT.ADDROW" bundle="${pensionLabels}" onclick="Table_One_AddRow();"  />
				 </td>
				 </tr>
				 </table>    	
	             </fieldset>            	                
	             </fieldset>
	        </td>
	    </tr>
	</table>
	
	<table id="tbl3" width="100%">
	    <tr>
	        <td>
	             <fieldset class="tabstyle"><legend> 
	             <fmt:message key="PPMT.PROVISIONALGRATUITY" bundle="${pensionLabels}"></fmt:message> </legend>
	             <table width="100%">
	                 <tr>
	                     <td width="20%" align="left"><fmt:message key="PPMT.PROVISIONALGRATUITY"
				                         bundle="${pensionLabels}"></fmt:message>
				         </td>
				         <td width="80%">
				              <input type="radio" id="radioProGratuity" name="radioProGratuity" value="Yes"/>
				              <fmt:message key="PPMT.YES" bundle="${pensionLabels}"></fmt:message>
				              <input type="radio" id="radioProGratuity" name="radioProGratuity" value="No"/>
				              <fmt:message key="PPMT.NO" bundle="${pensionLabels}"></fmt:message> 
				         </td>
	                 </tr>
	             </table>
	             <table width="100%">    
	                 <tr>
	                     <td width="20%" align="left"><fmt:message key="PPMT.GRATUITYAMOUNT"
				                         bundle="${pensionLabels}"></fmt:message></td>
			             <td width="30%"> <input type="text" name='txtGratuityAmount' id="txtGratuityAmount" style="text-align: left" />
			             </td>	
			             <td width="20%" align="left"><fmt:message key="PPMT.SANCTIONAUTHORITY"
				                         bundle="${pensionLabels}"></fmt:message></td>
			             <td width="30%"> <input type="text" name='txtSanctionAuthPG' id="txtSanctionAuthPG" style="text-align: left" />
			             </td>	
	                 </tr>    
	                 <tr>
	                     <td width="20%" align="left"><fmt:message key="PPMT.ACTUALAMOUNTPAID"
				                         bundle="${pensionLabels}"></fmt:message></td>
			             <td width="30%"> <input type="text" name='txtActualAmountPaid' id="txtActualAmountPaid" style="text-align: left" />
			             </td>	
			             <td width="20%" align="left"><fmt:message key="PPMT.AUTHORITYNO"
				                         bundle="${pensionLabels}"></fmt:message></td>
			             <td width="30%"> <input type="text" name='txtAuthorityNoPG' id="txtAuthorityNoPG" style="text-align: left" />
			             </td>	
	                 </tr>   
	                 <tr>
	                     <td width="20%" align="left"><fmt:message key="PPMT.PAYMENTDATE"
				                         bundle="${pensionLabels}"></fmt:message></td>
			             <td width="30%"> <input type="text" name='txtPaymentDate' id="txtPaymentDate" style="text-align: left" Readonly/>
			             <img src='images/CalendarImages/ico-calendar.gif'
					                onClick='window_open("txtPaymentDate",375,570)'style="cursor: pointer;" ${disabled}/>
			             </td>	
			             <td width="20%" align="left"><fmt:message key="PPMT.AUTHORITYDATE"
				                         bundle="${pensionLabels}"></fmt:message></td>
			             <td width="30%"> <input type="text" name='txtAuthorityDatePG' id="txtAuthorityDatePG" style="text-align: left" Readonly/>
			             <img src='images/CalendarImages/ico-calendar.gif'
					                onClick='window_open("txtAuthorityDatePG",375,570)'style="cursor: pointer;" ${disabled}/>
			             </td>	
	                 </tr>    
	             </table>    
	             
	             <fieldset class="tabstyle"><legend> 
	             <fmt:message key="PPMT.VOUCHERDETAILS" bundle="${pensionLabels}"></fmt:message> </legend>
	             <table width="100%">
	                 <tr>
	                     <td width="20%" align="left"><fmt:message key="PPMT.VOUCHERNO"
				                         bundle="${pensionLabels}"></fmt:message>
				         </td>
				         <td width="30%"> <input type="text" name='txtVoucherNo' id="txtVoucherNo" style="text-align: left" />
			             </td>
			             <td width="50%">
			             </td>
				     </tr>
				     <tr>
	                     <td width="20%" align="left"><fmt:message key="PPMT.VOUCHERDATE"
				                         bundle="${pensionLabels}"></fmt:message>
				         </td>
				         <td width="30%"> <input type="text" name='txtVoucherDate' id="txtVoucherDate" style="text-align: left" />
			             </td>
			             <td width="50%">
			             </td>
				     </tr>
				 </table>
				 </fieldset>                         
	             </fieldset>
	        </td>
	    </tr>
	</table>	

	<table id="tbl4" width="100%">		
	  <tr>		
	      <td width="50%">			 
	         <fieldset class="tabstyle"><legend> 
	         <fmt:message key="PPMT.BANKDETAILS" bundle="${pensionLabels}"></fmt:message> </legend>
		     <table width="100%">
			    <tr>   
			  	    <td width="40%" align="left"><fmt:message key="PPMT.IFSCCODE"
				      bundle="${pensionLabels}"></fmt:message></td>
			        <td width="60%"> <input type="text" name="txtIfscCode" id="txtIfscCode"
					style="text-align: left" onKeyPress="" />			
			        </td>	
		       </tr>
		       <tr>
			       <td width="40%" align="left"><fmt:message key="PPMT.BRANCHCODE"
				    bundle="${pensionLabels}"></fmt:message></td>
			       <td width="60%"> <input type="text" name="txtBranchCode" id="txtBranchCode"
					style="text-align: left" onKeyPress="" />			
			       </td>	
		      </tr>
		      <tr>
			      <td width="40%" align="left"><fmt:message key="PPMT.ACCOUNTNUMBER"
				      bundle="${pensionLabels}"></fmt:message></td>
			      <td width="60%"> <input type="text" name="txtAccountNumber" id="txtAccountNumber"
					style="text-align: left" onKeyPress="" />			
			      </td>	
		     </tr>
		    </table>
		    </fieldset>	
		  </td>
		  	
		  <td width="50%" valign="top">
		     <fieldset class="tabstyle"><legend> 
	                  <fmt:message key="PPMT.CHARGEDVOTED" bundle="${pensionLabels}"></fmt:message> </legend>
			   <table width="50%">
				  <tr>
				  	  <td width="50%">
				  	  </td>	
				      <td width="50%">
				      <input type="radio" id="radioChargedVoted" name="radioChargedVoted" value="Charged"/>
				      <fmt:message key="PPMT.CHARGED" bundle="${pensionLabels}"></fmt:message>
				      </td>
				 </tr>
				 <tr>
				     <td width="50%">
				  	 </td>
				     <td width="50%">
				     <input type="radio" id="radioChargedVoted" name="radioChargedVoted" value="Voted"/>
				     <fmt:message key="PPMT.VOTED" bundle="${pensionLabels}"></fmt:message>
				     </td>
				 </tr>
			   </table>
			   </fieldset>	
		  </td>
	  </tr>
    </table>

</td>
</tr>    	
</table>			
</hdiits:form>