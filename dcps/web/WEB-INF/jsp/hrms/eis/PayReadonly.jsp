<%try{ %>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ page import="java.util.Map" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<html>
<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>

<c:set var="row" value="2" ></c:set>
<style type="text/css">
.Label5{font-family:Arial, Helvetica, sans-serif;text-decoration: none;font-size:12px;font-weight:normal;color:Black; border-bottom:1px solid  #000000; border-right:1px solid  #000000;border-top:0px solid #000000;border-left:0px solid #000000;}
.frmlrbBorder{border-bottom:1px solid  #000000; border-right:1px solid  #000000;border-top:0px solid #000000;border-left:1px solid #000000;}

.frmlftBorder{border-left:1px solid  #000000;}
.TopBorder{border-top:1px solid #000000;}
.BottomBorder{border-bottom:1px solid #000000;}
.RightBorder{border-right:1px solid #000000;}
.TopBottomBorder{border-top:1px solid #000000;border-bottom:1px solid #000000;}
</style>

<table cellpadding="0" cellspacing="0" border="0"  frame="box" align="CENTER" width="100%">
    
    <tr>
    	<td>
    	 <table>
				     <tr>
				     <td class="TopBottomBorder">
				    	 Under Rupees (In words) :
				     </td>
				     </tr>
				     
				     <tr>
				     <td>
				     Net Amount Required for Payment
				     </td>
				     </tr>
				     
				     <tr>
				     <td>
				     (Brought forward from First Page)
				     </td>
				     </tr>
				     
				     
				     <tR>
							<td></td>
							</tR>
							<tR>
							<td></td>
							</tR>
							
							
							
				     <tr>
				     <td>
				     In Cash Rs : (in words) Rupees 
				     </td>
				     </tr>
				     
				     <tr>
				     <td>
				     By RTR/Bank Draft Rs : (in words) Rupees 
				     </td>
				     </tr>
				     
				     
				     <tr>
				     <td>
				     By Adjustment(AG.) Rs :
				     </td>
				     </tr>
				     
				     
				     <tr>
				     <td>
				     By Adjustment(TRY.) Rs :
				     </td>
				     </tr>
				     
				     <tr>
				     <td>
				     By Adjustment(Total) Rs : 
				     </td>
				     </tr>
				     
				     <tR>
							<td></td>
							</tR>
							<tR>
							<td></td>
							</tR>
							
							
				     <tr>
				     <td>
				     Received contents and certified that i have satisfied myself that all emoluments included in bills drawn 1 month/2 month/3 *
				     </td>
				     </tr>
				     
				     <tr>
				     <td>
				     previous to this date with the exception of those detaild below (of which the total has been refunded by deduction from this 
				     </td>
				     </tr>
				     
				      <tr>
				     <td>
				     bill) have been disbursed to the proper and their acquittances have taken and filled in my office with receipt stamps dully 
				     </td>
				     </tr>
				     
				      <tr>
				     <td>
				    cancelled for every payment in excess of Rs. 500.
				     </td>
				     </tr>
				     
				      <tr>
				     <td>
				    *One line to be used and the other scored out.
				     </td>
				     </tr>
     		</table>
     		</td>
     		</tr>
    
     	<tr>
     		<td>
     			<table class="groupTable" border="1" bordercolor="black" datapagesize="5">
     				<tr>
					<td>Section of Establishment</td>
					<td width="10%">Name</td>
					<td>Period</td>
					<td>Rupees</td>
					</tr>
					<tr>
					<td>&nbsp;</td>
					<td> &nbsp;</td>
					<td> &nbsp;</td>
					<td> &nbsp;</td>
					</tr>
					<tr>
					<td>&nbsp; </td>
					<td> &nbsp;</td>
					<td>&nbsp; </td>
					<td> &nbsp;</td>
					</tr>
					<tr>
					<td> &nbsp;</td>
					<td> &nbsp;</td>
					<td> &nbsp;</td>
					<td>&nbsp; </td>
					</tr>
				</table>
			</td>
			<td width="50%">
				<table width="50%">
					<tr>
						Finacial Year		:
					</tr>
					<tr>
						Sacntioned Grats	:
					</tr>
					<tr>
						Expen. Incu. this Bill 	:
					</tr>
					<tr>
						Amount of Bill 		:
					</tr>
					<tr>
						Balance Amount 		:
					</tr>	
				</table>
			
			</td>
		</tr>
		<tr>
			<td>
				<table class="groupTable" border="0" bordercolor="black">
			 		<tr>
			     		<td class="TopBorder">
			    			FOR USE OF TREASURY	
			     		</td>
			    	</tr>
			    	<tr>
			     		<td>
			    			Pay Rs. 		(In words) Rupees:
			     		</td>
			    	</tr>
				</table>
				<table>
					<tr>
						<td width="30%">
							Paid by transfer credit Rs :
						</td>
						<td width="20%">
							Rupees
						</td>
					</tr>
					<tr>
			     		<td>
			    			As detailed below :-
			     		</td>
			    	</tr>
			    	<tr>
			     		<td>
			    			0021 Taxes Income 
			     		</td>
			     		<td class="TopBorder">
			    			&nbsp;
			     		</td>
			    	</tr>
			    	<tr>
			     		<td>
			    			0028 Tax on Employment(Profession Tax)	
			     		</td>
			     		<td class="TopBorder">
			    			&nbsp;
			     		</td>
			    	</tr>
			    	<tr>
			     		<td>
			    			0216 Housing	
			     		</td>
			     		<td class="TopBorder">
			    			&nbsp;
			     		</td>
			    	</tr>
			    	<tr>
			     		<td>
			    			6216 Loan for Housing		 
			     		</td>
			     		<td class="TopBorder">
			    			&nbsp;
			     		</td>
			    	</tr>
			    	<tr>
			     		<td>
			    			8011 Insurance and Pension Fund(M.S.L.I)
			     		</td>
			     		<td class="TopBorder">
			    			&nbsp;
			     		</td>
			    	</tr>
			    	<tr>
			     		<td>
			    			8011 Insurance and Pension Fund(GIS,1982)
			     		</td>
			     		<td class="TopBorder">
			    			&nbsp;
			     		</td>
			    	</tr>
			    	<tr>
			     		<td>
			    			8711 Adjusting Account with P & T - P.L.I
			     		</td>
			     		<td class="TopBorder">
			    			&nbsp;
			     		</td>
			    	</tr>
			    	<tr>
			     		<td>
			    			Total
			     		</td>
			     		<td class="TopBorder">
			    			&nbsp;
			     		</td>
			    	</tr>
				</table>
			</td>
			<td>
				
				<table class="groupTable" border="0" bordercolor="black">
			 		<tr>
			     		<td class="TopBorder">
			    			Drawing And Disbursing officer	:
			     		</td>
			    	</tr>
			    	<tr>
			     		<td>
			    			Date : 		Section officer,Mumbai :
			     		</td>
			    	</tr>
				</table>
				<table width="50%" class="groupTable" border="0" bordercolor="black">
						<tr>
							Paid by Transfer :
						</tr>
						<tr>
							Accountant 	 :
						</tr>
				</table>
				<table width="50%" class="groupTable" border="0" bordercolor="black">
					<tr>
						Cheque No :
					</tr>
					<tr>
						Drawn on :
					</tr>
					<tr>
						A.T.O 	  :
					</tr>
				</table>
				<table width="50%" class="groupTable" border="0" bordercolor="black">
					<tr>
						Cheque delivery on :
					</tr>
					<tr>
						Treasury Clerck
					</tr>
				</table>
				<table width="50%" class="groupTable" border="0" bordercolor="black">
					<tr>
						For Autit Office
					</tr>
					<tr>
						Admitted Rs :
					</tr>
					<tr>
						Disallowed Rs :
					</tr>
					<tr>
						Objected Rs :
					</tr>
				</table>
				<table width="50%" class="groupTable" border="0" bordercolor="black">
					<tr>
						Reasons fo Objection :
					</tr>
					<tr>
						Auditor/Secion officer/Accountants Officer
					</tr>
					
				</table>
			</td>
			
			
			
		</tr>	     
</table>
     						





</html>
<%}catch(Exception e)
{
e.printStackTrace();	
}%>