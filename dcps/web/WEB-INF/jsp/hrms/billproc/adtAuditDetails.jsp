
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!-- For Taglib -->
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>  
<fmt:setBundle basename="resources.billproc.billproc_en_US" var="billprocLabels" scope="application"/>


<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>AUDIT BRANCH : Audit Details</title>
		<script language="javascript">
			function showForward(url)
			{
				url="ifms.htm?viewName=cmnForward";
				window.open(url,"_blank","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,top=100,left=150,width=550,height=400"); 			
			}
			function showBill(url)
			{
				window.open(url,"_blank","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,top=100,left=150,width=742,height=500"); 
			}
		</script>
	</head>

	<body>
<!-- 	<hdiits:form name="frmAdtAuditDetails" validate="true">	  -->
	<hdiits:table align="center" width="90%">
		<hdiits:tr>
			<hdiits:td>
				<hdiits:table align="center" width="90%">
					<hdiits:tr>
						<hdiits:td align="center">
							<b>Treasury > Bill Processing > Audit Branch > Audit Details</b>
						</hdiits:td>
					</hdiits:tr>		
			
				<hdiits:tr><hdiits:td><br></hdiits:td></hdiits:tr>
				<hdiits:tr><hdiits:td><br></hdiits:td></hdiits:tr>			
					<hdiits:tr>
						<hdiits:td colspan="10" align="center">
							<b>AUDIT DETAILS</b>
						</hdiits:td>
					</hdiits:tr>			
				<hdiits:tr><hdiits:td><br></hdiits:td></hdiits:tr>
			</hdiits:table>
		</hdiits:td>
	</hdiits:tr>

		<hdiits:tr>
			<hdiits:td>
	
				<hdiits:table width="90%" align="center">
					<hdiits:tr>
						<hdiits:td align="left">
							BILL DETAILS
						</hdiits:td>
					</hdiits:tr>

					<hdiits:tr>
 						<hdiits:td>
		   					<fmt:message key="CMN.BILL_NO" bundle="${billprocLabels}"></fmt:message>		   					
   						</hdiits:td>
						<hdiits:td>
 							<fmt:message key="CMN.BILL_AMOUNT" bundle="${billprocLabels}"></fmt:message>
		   				</hdiits:td>	
   						<hdiits:td> 
	   						<fmt:message key="CMN.BILL_TYPE" bundle="${billprocLabels}"></fmt:message>
		   				</hdiits:td>
   						<hdiits:td> 
   							<fmt:message key="CMN.BILL_DATE" bundle="${billprocLabels}"></fmt:message>
		   				</hdiits:td>	      
   						<hdiits:td> 
   							<fmt:message key="CMN.CARDEX_NO" bundle="${billprocLabels}"></fmt:message>
		   				</hdiits:td>	
   						<hdiits:td>
		   					 <fmt:message key="CMN.DDO_NAME" bundle="${billprocLabels}"></fmt:message>
   						</hdiits:td>	
   						<hdiits:td> 
			   					<fmt:message key="CMN.MAJOR_HEAD" bundle="${billprocLabels}"></fmt:message>
   						</hdiits:td>	
		   				<hdiits:td>
   							 <fmt:message key="CMN.TOKEN_NO" bundle="${billprocLabels}"></fmt:message>
   						</hdiits:td>	
		   		    </hdiits:tr>
   					<hdiits:tr> 
   						<hdiits:td>
   							<hdiits:a href="javascript:void(0)" onclick="javascript:showBill('ifms.htm?viewName=adtBillView')">
		   						BIL001
		   					</hdiits:a>
   						</hdiits:td>
	   					<hdiits:td>85777.00</hdiits:td>
   						<hdiits:td>Medical Reimbursement</hdiits:td>	
   						<hdiits:td>19/09/2006</hdiits:td>
   						<hdiits:td>CDX1</hdiits:td>	      
   						<hdiits:td>Sanjeev Shah</hdiits:td>	
   						<hdiits:td>2772</hdiits:td>	
	   					<hdiits:td>TKN111</hdiits:td>	
   					</hdiits:tr>
 				</hdiits:table>
   			
   		</hdiits:td>
   	</hdiits:tr>	
	<hdiits:tr><hdiits:td><br></hdiits:td></hdiits:tr>
	<hdiits:tr>
 		<hdiits:td>
			<hdiits:table align="center" width="90%">
   				<hdiits:tr>
		   			<hdiits:td>
		   			
   						PREVIOUS AUDITOR's REMARKS
		   			</hdiits:td>
   				</hdiits:tr>
   			
   				<hdiits:tr>
		   			<hdiits:td>
   						<fmt:message key="CMN.AUDITOR_NAME" bundle="${billprocLabels}"></fmt:message>				
		   			</hdiits:td>
   					<hdiits:td>
		   				Objection Information
		   			</hdiits:td>
   				</hdiits:tr>
	   				
		   		<hdiits:tr>
		   			<hdiits:td>
   						R.K. Joshi
		   			</hdiits:td>
   					<hdiits:td>
   						<hdiits:a href="javascript:void(0)" onclick="javascript:showBill('ifms.htm?viewName=adtObjectionDetails')">Details of Objection</hdiits:a>
		   			</hdiits:td>
   				</hdiits:tr>
		 	</hdiits:table>
		 </hdiits:td>
		</hdiits:tr>
	<hdiits:tr>
		<hdiits:td>&nbsp;</hdiits:td>
 	</hdiits:tr>

 	<hdiits:tr>		 	
 		<hdiits:td>	 	 			
 			<hdiits:table align="center" width="90%">
			 	<hdiits:tr>
			 		<hdiits:td>
			 			AUDIT DETAILS
 					</hdiits:td>		 		
			 	</hdiits:tr>
							
			 	<hdiits:tr>
			 		<hdiits:td>
			 			<fmt:message key="CMN.AUDITOR_NAME" bundle="${billprocLabels}"></fmt:message>				
			 		</hdiits:td>
					 		<hdiits:td>
					 			:&nbsp; N.P. AMIN
					 		</hdiits:td>
					 	</hdiits:tr>
						<hdiits:tr>
					 		<hdiits:td>
					 			<fmt:message key="ADT.AUDITOR_CODE" bundle="${billprocLabels}"></fmt:message>				
					 		</hdiits:td>
					 		<hdiits:td>
					 			:&nbsp;AUD101
					 		</hdiits:td>
					 	</hdiits:tr>
					 		<hdiits:tr>
					 		<hdiits:td>
					 			<fmt:message key="CMN.REMARKS" bundle="${billprocLabels}"></fmt:message>
					 		</hdiits:td>
					 		<hdiits:td colspan="2">
					 			<hdiits:textarea name="txtareaRemarks" rows="6" cols="60"></hdiits:textarea>
					 		</hdiits:td>
					 	</hdiits:tr>
					 	<hdiits:tr>
					 		<hdiits:td>
						 		<fmt:message key="ADT.OBJECTION" bundle="${billprocLabels}"></fmt:message>
						 	</hdiits:td>
						 	<hdiits:td>
						 		<hdiits:a href="#"><hdiits:image source="common/images/Details.gif" />
						 		</hdiits:a>
						 	</hdiits:td>
						 </hdiits:tr>
						 <hdiits:tr>
						 	 <hdiits:td>
								<fmt:message key="ADT.ATTACH_REMARK_FILE" bundle="${billprocLabels}"></fmt:message>
 							 </hdiits:td> 							 
							 <hdiits:td>
								:&nbsp;<hdiits:a href="#"> Add Attachment</hdiits:a>
							 </hdiits:td>	
						</hdiits:tr>				
					
				 	 </hdiits:table>
				 </hdiits:td>
			</hdiits:tr>
			<hdiits:tr>
				<hdiits:td>&nbsp;</hdiits:td>
			</hdiits:tr>
			<hdiits:tr>
			 	<hdiits:td>
			 		<center>
			 		<hdiits:button name="btnForward" type="button" value="Forward" onclick="javascript:showForward();"/>
			 		<hdiits:button name="btnClose" type="button" value="Close" onclick="window.close();" />
			 		</center>
				</hdiits:td>
			</hdiits:tr>
			<hdiits:tr>
				<hdiits:td>&nbsp;</hdiits:td>
			</hdiits:tr>
			<hdiits:tr>
				<hdiits:td>&nbsp;</hdiits:td>
			</hdiits:tr>		
		</hdiits:table>
<!-- 		</hdiits:form>  -->
	</body>
</html>