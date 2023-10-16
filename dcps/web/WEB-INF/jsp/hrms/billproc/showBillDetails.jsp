<%@page import="com.tcs.sgv.common.utils.DateUtility,com.tcs.sgv.common.valueobject.CmnLookupMst,java.util.List,java.util.Iterator"%>
<%@page import="com.tcs.sgv.core.valueobject.ResultObject,java.util.Map"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>  


<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>


<fmt:setLocale value='<%=(String)session.getAttribute("locale")%>'/>
<fmt:setBundle basename="resources.app.diary.DiaryLables" var="diaryLables" scope="request"/>
<fmt:setBundle basename="resources.billproc.billproc_en_US" var="billprocLabels" scope="application"/>
 

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>COUNTER BRANCH : Inward Physical Bills </title>
		<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
		
		
	<script type="text/javascript"  
         src="script/common/commonfunctions.js">
		</script>
	<script  type="text/javascript" 
      src="script/common/CalendarPopup.js">
</script>
		
		<script> 
			
			function loadcalendar(name,img)
		      {
			   alert('came in load calender' );
			   var cal1 = new CalendarPopup();
			   alert('Calendar instantiated' );
			   cal1.select(name,img,'dd/MM/yyyy'); 
			   return false;
			   
		      }
		
			function showExempt()
			{
				if(document.frmCntrInwPhyBills.radExempted[0].checked)
				{
					document.getElementById('divExempted').style.display='block';
				}
				else
				{
					if(document.frmCntrInwPhyBills.radExempted[1].checked)
					{
						document.getElementById('divExempted').style.display='none';
					}
				}
			}
			
			function autoFill()
			{
				var cardexCtrl = document.frmCntrInwPhyBills.txtCardexNo;
				var CardexNo = cardexCtrl.value;
				if(CardexNo.length < 1)
				{
					alert("Please Enter Cardex No.");
					cardexCtrl.focus();
					return false;
				}
				else
				{	
					return true;					
				}
				return true;
			}	
			function showCtrl()
			{
				if(document.frmCntrInwPhyBills.cmbTCCtgry.value=='2')
				{
					document.getElementById('divTC').style.display='block';
				}
				else
				{
					{
						document.getElementById('divTC').style.display='none';
					}
				}
			}

			function ForwardBill()
			{
				document.forms[0].action ="ifms.htm?actionFlag=createBillMovement";
				document.forms[0].submit();
			}
			</script>
	</head>
	
	<%
	ResultObject res = (ResultObject)request.getAttribute("result");
	Map mape = (Map)res.getResultValue();
	System.out.println(" \n\n in show bills details page --------- " + mape.get("ShowBillVO"));
	
					%>
	  
	<body onLoad="hideTC();">
<hdiits:form name="frmCntrInwPhyBills" validate="true">

    	<div id="statusBar" style="width:50%; background-color: #BADCFD ;display:none;font: bold 10px Arial"></div>		
		<div id="progressImage" style="display:none"></div>
		<%
		System.out.println(" \n\n   1....................... " ); 
		%>
		<hdiits:table align="center" width="90%">
			<hdiits:tr>
				<hdiits:td>
			
					<hdiits:table align="center" width="90%">
						<hdiits:tr>
							<hdiits:td align="center">
								Treasury > Bill Processing > Counter Branch > Inward Physical Bills					
							</hdiits:td>
						</hdiits:tr>
					</hdiits:table>			
				</hdiits:td>
			</hdiits:tr>
			
			<hdiits:tr>
				<hdiits:td>			
					<hdiits:table width="90%" align="center" >		
					<hdiits:tr><hdiits:td><br></hdiits:td></hdiits:tr>
				<hdiits:tr><hdiits:td ><br></hdiits:td></hdiits:tr>					
						<hdiits:tr >
							<td colspan="4" align="center">
							<b>
								INWARD PHYSICAL BILLS
							</b>
							</td>
						</hdiits:tr>	
						<hdiits:tr><hdiits:td><br></hdiits:td></hdiits:tr>						
						<hdiits:tr>
							<hdiits:td align="left" colspan="4">
							<b>
								&nbsp;DDO INFORMATION
							</b>
							</hdiits:td>
						</hdiits:tr>

						<hdiits:tr>
						<% 	System.out.println(" \n\n  12....................... " );  %>		
							<hdiits:td align="left" width="25%"> 
								<fmt:message key="CMN.CARDEX_NO" bundle="${billprocLabels}">
								</fmt:message>
								<font color="red">*</font>
							</hdiits:td>
							<hdiits:td align="left">
							<% 	System.out.println(" \n\n  before text.......... " );  %>	
								:&nbsp;<hdiits:text  id="CardexNo" name="txtCardexNo" ></hdiits:text>

							<hdiits:button name="Get Values" value="Get Data" id="DDOData" type="button"/>
							</hdiits:td>
							<% 	System.out.println(" \n\n  afetr button text.......... " );  %>	
							<hdiits:td align="left" width="25%"> 
								<fmt:message key="CNTR.DDO_NO" bundle="${billprocLabels}">
								</fmt:message>
								<font color="red">*</font>
							</hdiits:td>
							<hdiits:td align="left">
								:&nbsp;<hdiits:text id="DDONo" name="txtDDONo" readonly="true"></hdiits:text>
					
							</hdiits:td>
						</hdiits:tr>
				
						<hdiits:tr>						
							<hdiits:td align="left" width="25%"> 
								<fmt:message key="CMN.DDO_NAME" bundle="${billprocLabels}">
								</fmt:message>
								<font color="red">*</font>
							</hdiits:td>
							<hdiits:td align="left">
								:&nbsp;<hdiits:text name="txtDDOName" id="DDOName" readonly="true"></hdiits:text>
							</hdiits:td>
							<hdiits:td width="25%" align="left">
								<fmt:message key="CNTR.DEPARTMENT" bundle="${billprocLabels}"></fmt:message>
								<font color="red">*</font>
							</hdiits:td>
							<hdiits:td align="left">
								:&nbsp;<hdiits:text name="txtDepartment" id="DDODept" readonly="true"></hdiits:text>
							</hdiits:td>
						</hdiits:tr>
				
						<hdiits:tr>
							<hdiits:td colspan="4" align="left"> &nbsp;
							</hdiits:td>
						</hdiits:tr>
							<% 	System.out.println(" \n\n  2....................... " );  %>		
						<hdiits:tr>
							<hdiits:td align="left" colspan="4">
							<b>
								&nbsp;BILL INFORMATION
							</b>
							</hdiits:td>
						</hdiits:tr>	
						<hdiits:tr>
							<hdiits:td width="25%" align="left">
								<fmt:message key="CMN.BILL_CONTROL_NO" bundle="${billprocLabels}">
								</fmt:message>
								<font color="red">*</font>								
							</hdiits:td>
							<hdiits:td align="left">
								:&nbsp;<hdiits:text name="txtBillNo" readonly="true"></hdiits:text>
							</hdiits:td>
							<hdiits:td width="25%" align="left"> 
								<fmt:message key="CMN.TOKEN_NO" bundle="${billprocLabels}">						
								</fmt:message>
								<font color="red">*</font>								
							</hdiits:td>
							<hdiits:td align="left">
								:&nbsp;<hdiits:text name="txtTokenNo"></hdiits:text>
							</hdiits:td>
						</hdiits:tr>
								<% 	System.out.println(" \n\n  3....................... " );  %>
						<hdiits:tr>
							<hdiits:td width="25%" align="left">
								<fmt:message key="CMN.BILL_DATE" bundle="${billprocLabels}">							
								</fmt:message>
								<font color="red">*</font>								
						</hdiits:td>
						<hdiits:td align="left">
							<hdiits:dateTime name="txtBillDate" captionid="dateTime" bundle="${diaryLables}" default="2007-01-01 00:00:00" />
						</hdiits:td>

							<hdiits:td align="left" width="25%"> 
								<fmt:message key="CMN.BILL_TYPE" bundle="${billprocLabels}">						
								</fmt:message>
								<font color="red">*</font>								
							</hdiits:td>
							<hdiits:td align="left">
								:&nbsp;<hdiits:select name="cmbBillType">
									<hdiits:option value="0" >------Select------</hdiits:option>
									<hdiits:option value="1" >Medical Reimbursement</hdiits:option>
									<hdiits:option value="2" >Canteen Bill</hdiits:option>
									<hdiits:option value="3" >NewsPaper Bill</hdiits:option>
									<hdiits:option value="4" >Travelling Expenses</hdiits:option>
								</hdiits:select>
							</hdiits:td>
					</hdiits:tr>
	
					<hdiits:tr>
						<hdiits:td align="left">
							<fmt:message key="CNTR.GROSS_AMOUNT" bundle="${billprocLabels}"></fmt:message>
						</hdiits:td>
						<hdiits:td align="left">
							:&nbsp;<hdiits:text name="txtGrossAmt"></hdiits:text>
						</hdiits:td>
						<hdiits:td width="20%" align="left"> 
							<fmt:message key="CNTR.NET_AMOUNT" bundle="${billprocLabels}"></fmt:message>
						</hdiits:td>
						<hdiits:td align="left">
							:&nbsp;<hdiits:text name="txtNetAmt"></hdiits:text>
						</hdiits:td>
					</hdiits:tr>
					
					<hdiits:tr>
						<hdiits:td width="25%" align="left">
							<fmt:message key="CMN.MAJOR_HEAD" bundle="${billprocLabels}">
							</fmt:message>
							<font color="red">*</font>
						</hdiits:td>
						<hdiits:td align="left">
							:&nbsp;<hdiits:select name="cmbMajorHead">
									<hdiits:option value="0" >------Select------</hdiits:option>
									<hdiits:option value="1" >Major Head 1</hdiits:option>
									<hdiits:option value="2" >Major Head 2</hdiits:option>
									<hdiits:option value="3" >Major Head 3</hdiits:option>
							</hdiits:select>
						</hdiits:td>
						<hdiits:td align="left">
							<fmt:message key="CNTR.GO/NGO" bundle="${billprocLabels}"></fmt:message>
						</hdiits:td>
						<hdiits:td align="left">
						:&nbsp;<hdiits:select name="cmbGONGO">
								<hdiits:option value="0" >------Select------</hdiits:option>
								<hdiits:option value="1" >IPS/IAS/IFS</hdiits:option>								
								<hdiits:option value="2" >GO</hdiits:option>
								<hdiits:option value="3" >NGO</hdiits:option>
							</hdiits:select>
						</hdiits:td>
					</hdiits:tr>
					
					<hdiits:tr>
						<hdiits:td align="left">
							<fmt:message key="CMN.BILL_CATEGORY" bundle="${billprocLabels}"></fmt:message>
								<font color="red">*</font>
						</hdiits:td>
						<hdiits:td align="left">		
							:&nbsp;<hdiits:select name="cmbTCCtgry"   onchange="javascript: showCtrl();">
									<hdiits:option value="0" >------Select------</hdiits:option>
									<hdiits:option value="1" selected="true">Regular</hdiits:option>
									<hdiits:option value="2" >TC</hdiits:option>
									<hdiits:option value="3" >Nil</hdiits:option>
							</hdiits:select>
						</hdiits:td>
						<hdiits:td align="left">
							<fmt:message key="CNTR.ATTACH_SCANNED_BILL" bundle="${billprocLabels}">
							</fmt:message>
						</hdiits:td>
						<hdiits:td>
						 		<hdiits:a href="ifms.htm?viewName=cmnAttach"><hdiits:image source="common/images/Details.gif" />
						 		</hdiits:a>
					 	</hdiits:td>
					</hdiits:tr>
					
					<hdiits:tr>
						<hdiits:td colspan="2">
							<fmt:message key="CNTR.EXEMPTED" bundle="${billprocLabels}">
							</fmt:message>
							<hdiits:radio name="radExempted" value="yes" onclick="javascript:showExempt(this);"/>Yes							
							<hdiits:radio name="radExempted" value="no" onclick="javascript:showExempt(this);" default="true"/>No
						</hdiits:td>
						<hdiits:td colspan="2">
							<hdiits:table id="divExempted" align="center" width="100%">
								<hdiits:tr>
									<hdiits:td>
										Bill Code
									</hdiits:td>
									<hdiits:td align="left">
									 <%--
									 	System.out.println("---------------------------------------> Execution of BilCodeLIst");
									 	Map map =  (Map)((ResultObject)request.getAttribute("result")).getResultValue();									 	
									 	List list = (List)map.get("BillCodeList");
									 	System.out.println("---------------------------------------> Execution of BilCodeLIst SIZE " + list.size());
									 	Iterator it = list.iterator();
									 	
									 	while(it.hasNext()){
									 		System.out.println("inside while");
									 		try {
										 		Object[] cmn = (Object[])it.next();
										 		System.out.println("after type casting");
										 		System.out.println("====="+cmn[0] + "======="+ cmn[1]);
									 		} catch(Exception ex){
									 			ex.printStackTrace();
									 		}
									 		System.out.println("after retreiving value");
									 	}
									 --%>
								:&nbsp; 
								<hdiits:select name="cmbBillCode">
								 
								</hdiits:select>
									</hdiits:td>
								</hdiits:tr>
							</hdiits:table>
						</hdiits:td>
					</hdiits:tr>
					
					<hdiits:tr>
						<hdiits:td colspan="5">
							<hdiits:table id="divTC" align="center" width="100%">
								<hdiits:tr><hdiits:td><br></hdiits:td></hdiits:tr>
								<hdiits:tr>
									<hdiits:td align="left" colspan="4">
										&nbsp;TRANSFER CREDIT INFORMATION
									</hdiits:td>
								</hdiits:tr>

								<hdiits:tr>
									<hdiits:td align="left">
										<fmt:message key="CNTR.PREVIOUS_BILL_NO" bundle="${billprocLabels}"></fmt:message>
										<font color="red">*</font>
									</hdiits:td>
									<hdiits:td align="left">
										:&nbsp;<hdiits:text name="txtPrevBillNo"></hdiits:text>						
									</hdiits:td>
									<hdiits:td width="20%" align="left"> 
										<fmt:message key="CNTR.CHALLAN_NO" bundle="${billprocLabels}"></fmt:message>
										<font color="red">*</font>
									</hdiits:td>
									<hdiits:td align="left">
										:&nbsp;<hdiits:text name="txtChallanNo"></hdiits:text>
									</hdiits:td>
								</hdiits:tr>
							</hdiits:table>
						</hdiits:td>
					</hdiits:tr>
		
					<hdiits:tr>
						<hdiits:td colspan="4" align="left"> &nbsp;
						</hdiits:td>
					</hdiits:tr>
					
					<hdiits:tr>
						<hdiits:td align="left" colspan="4">
							&nbsp;TREASURY AND OTHER INFORMATION
						</hdiits:td>
					</hdiits:tr>
					
					<hdiits:tr>
						<hdiits:td width="15%" align="left">
							<fmt:message key="CMN.AUDITOR_NAME" bundle="${billprocLabels}">
							</fmt:message>
								<font color="red">*</font>							
						</hdiits:td>
						<hdiits:td align="left">
							:&nbsp;<hdiits:select name="cmbAuditorName">
									<hdiits:option value="0" >------Select------</hdiits:option>
									<hdiits:option value="1" >K.C. Patel</hdiits:option>
									<hdiits:option value="2" >R.B. Joshi</hdiits:option>
									<hdiits:option value="3" >P.K. Parikh</hdiits:option>
							</hdiits:select>
						</hdiits:td>
					</hdiits:tr>				
				
					<hdiits:tr>
						<hdiits:td align="left">
							<fmt:message key="CMN.REMARKS" bundle="${billprocLabels}"></fmt:message>
						</hdiits:td>
						<hdiits:td align="left">
							<hdiits:textarea name="txtareaRemarks" rows="7" cols="35">
							</hdiits:textarea>
						</hdiits:td>							
					</hdiits:tr>				
				</hdiits:table>
			</hdiits:td>
		</hdiits:tr>

		<hdiits:tr>
			<hdiits:td>
			</hdiits:td>
	</hdiits:tr>
	
	<hdiits:tr>
		<hdiits:td>			
			<hdiits:table align="center">
				<hdiits:tr>
					<hdiits:td align="center">
						<hdiits:button name="btnSave" value="Save" type="button"  onclick="insertdt()" />&nbsp;   
						<hdiits:button name="btnSaveNew" value="Save and New" type="button" />&nbsp;   
						<hdiits:button name="btnForward" value="Forward" type="button" />&nbsp;
   						<hdiits:button type="button" name="btnClose" value="Close" onclick="window.location.href='index.jsp'"></hdiits:button>
					</hdiits:td>
				</hdiits:tr>
			</hdiits:table>
			</hdiits:td>
		</hdiits:tr>
	</hdiits:table>

		<ajax:updateField baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getDDOData" 
		action="DDOData"  source="CardexNo" target="DDONo,DDOName,DDODept" parameters="CardexNo={CardexNo}"  ></ajax:updateField>

<input type="hidden"  name="actionFlag" value="insInwPhyBills" id="actionFlag">


	<script language="javascript">
	if(document.frmCntrInwPhyBills.radExempted.value!='Yes')
	{
		document.getElementById('divExempted').style.display='none';
	}
	if(document.frmCntrInwPhyBills.cmbTCCtgry.value!='TC')
	{
		document.getElementById('divTC').style.display='none';
	}
			</script>
			</hdiits:form>
			</body>
</html>