<%
try 
{
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.*,org.apache.commons.logging.Log,org.apache.commons.logging.LogFactory"%>
<%@page import="com.tcs.sgv.eis.valueobject.PayslipBasicDetailsVO"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page import="java.util.*"%>

<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript" src="<c:url value="script/common/tabcontent.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/commonUtils.js"/>"></script>
<script type="text/javascript" src="common/script/commonfunctions.js"></script>
<c:set var="resultObj" value="${result}" ></c:set>
<c:set var="resValue" value="${resultObj.resultValue}" ></c:set>
<c:set var="deduceList" value="${resValue.deduceList}"></c:set>
<c:set var="allowList" value="${resValue.allowList}"></c:set>
<c:set var="sumdeduce" value="${resValue.sumdeduce}"></c:set>
<c:set var="sumallow" value="${resValue.sumallow}"></c:set>
<c:set var="totalsum" value="${resValue.totalsum}"></c:set>
<c:set var="voucherNumber" value="${resValue.voucherNumber}"></c:set>
<c:set var="voucherDate" value="${resValue.voucherDate}"></c:set>
<c:set var="payslipCompoMap" value="${resValue.payslipCompoMap}"></c:set>
<c:set var="size" value="${resValue.size}"></c:set>
<script type="text/javascript">
function GoToPage() 
{
	window.close();
}

function printFunction()
{
	document.getElementById("buttons").style.display="none";
	document.getElementById("tabmenu").style.display="none";
	window.print();
	document.getElementById("buttons").style.display="block";
	document.getElementById("tabmenu").style.display="block";
}

function saveFileFunction()
{
 	document.getElementById('buttons').style.display="none"; // hide
	document.execCommand("SaveAs");
	document.getElementById('buttons').style.display="block"; // show 
}
</script>

<hdiits:form action="hrms.htm?actionFlag=getpayslip&elementId=300711" method="Post" name="GeneratePaySlip" validate="true" >
<div id="tabmenu" style="display: block;">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1">PaySlip</a></li>
	</ul>
</div>
<div id="tcontent1" style="background-color: #E8E3E3;" >
<% 	long empId=0;
	Map lst=new HashMap();
	Map payslipMap=(HashMap)pageContext.getAttribute("payslipCompoMap");
	List allowancesList=new ArrayList();
	List deductionsList=new ArrayList();
	List nonGovernmetList=new ArrayList();
	Set keySet = payslipMap.keySet();
	Iterator keyIt = keySet.iterator();
	int i=0;
	long allowTotal=0;
	long deducTotal=0;
	long ngTotal=0;
	String netTotalInWords = "";
	long netTotal=0;
	while(keyIt.hasNext()) 
	{
		Object key = keyIt.next();
		empId=Long.parseLong(key.toString());
		lst =(HashMap)payslipMap.get(empId);
		allowancesList=(ArrayList)lst.get("allowanceList");
		deductionsList=(ArrayList)lst.get("deductionList");
		nonGovernmetList=(ArrayList)lst.get("nonGovernmetList");
		allowTotal=(Long)lst.get("allowTotal");
		deducTotal=(Long)lst.get("deducTotal");
		netTotal=(Long)lst.get("netPay");
		ngTotal=(Long)lst.get("ngTotal");
		netTotalInWords = lst.get("netPayInWords").toString();
		PayslipBasicDetailsVO basicVO=(PayslipBasicDetailsVO)lst.get("payslipBasicVO");
		pageContext.setAttribute("allowLst",allowancesList);
		pageContext.setAttribute("deducLst",deductionsList);
		pageContext.setAttribute("nonGovernmetList",nonGovernmetList);
		pageContext.setAttribute("allowTotal",allowTotal);
		pageContext.setAttribute("deducTotal",deducTotal);
		pageContext.setAttribute("ngTotal",ngTotal);
		pageContext.setAttribute("basicVO",basicVO);
		pageContext.setAttribute("netTotal",netTotal);
		pageContext.setAttribute("netTotalWord",netTotalInWords);
		if(deductionsList.size()>=1 && allowancesList.size() <= 12 )
		{
			if(i > 0)
			{
%>
				<table cellpadding="0" cellspacing="0"  align="CENTER" width="100%">
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td style="border-color:#353732;border-bottom: 1px solid;" width="100%">&nbsp;</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
				</table>
<%			
			}
			i++;
%>
			<table cellpadding="0" cellspacing="0"  align="CENTER" width="100%">
				<tr>
     				<td align="left"  width="100%"><c:out value="${basicVO.office}" /></td>
    			</tr>     
    			<tr>
    				<td width="100%">
    					<table width="100%" cellpadding="0" cellspacing="0">
    						<tr>
    							<td align="left"  width="45%" >
									<label for="Employee Name" >Employee Name:</label> <b><c:out value="${basicVO.employeeName}"  /></b>
								</td>
								<td width="33%">
									<label for="Designation">Designation:</label><c:out value="${basicVO.designation}" />
								</td>
								<td width="22%">
									<label for="Salary Month">Salary Month:</label><c:out value="${basicVO.salaryMonth}" />			
								</td>
    						</tr>
    					</table>
    				</td>
				</tr>
				<tr>
					<td width="100%">
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td width="45%">
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td width="60%">
												<label for="gpf">GPF/DCPS AC.No:</label> <c:out value="${basicVO.gpfAccNo}" />
											</td>
											<td width="40%">
												<label for="bankAccNo">Bank A/c No :</label>
											<%
												if(basicVO.getBankAccNo() != "null")
												{
											%>
													<c:out value="${basicVO.bankAccNo}" />
											<%		
												}
												else
												{
											%>
													<c:out value="-" />
											<%
												}
											%>
											</td>								
										</tr>
									</table>
								</td>
								<td width="33%">
									<label for="PayBand">Pay Band :</label><c:out value="${basicVO.payBand}" />
								</td>
								<td width="22%">
									<label for="PayInPB+GP">Pay In PB+GP :</label><c:out value="${basicVO.payInPaybandPlusGp}" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<table cellpadding="0" cellspacing="0" border="0" frame="box" align="CENTER" width="100%" style="border-color:#353732;border: 1px solid;" >
				<tr>
					<td align="center"  width="24%" >
						<label for="Emoluments">Emoluments</label>
					</td>
					<td align="center"  width="40%"  style="border-left: 1px solid;"  >
						<label for="govt">Govt. Recoveries</label>
					</td>
					<td align="center"  width="36%"   style="border-left: 1px solid;" >
						<label for="govt">Non Govt. Recoveries</label>
					</td>
				</tr>
			</table>
			<table cellpadding="0" cellspacing="0" border="0" frame="box" align="CENTER" width="100%" style="border-color:#353732;border-left: 1px solid;border-right: 1px solid;border-bottom: 1px solid;" >	
				<tr>
					<td align="center" width="14%" >
						<label for="Particulars">Particulars</label>
					</td>
					<td align="center"  width="10%" style="border-left: 1px solid;border-right: 1px solid;">
						<label for="amnt">Amount(Rs.)</label>	
					</td>
					<td align="center" width="20%">
						<label for="Particulars">Particulars</label>
					</td>
					<td align="center"  width="10%" style="border-left: 1px solid;border-right: 1px solid;">
						<label for="amnt">Amount(Rs.)</label>
					</td>
					<td align="center"  width="10%" style="border-right: 1px solid;">
						<label for="Inst">Inst. No.</label>
					</td>
					<td align="center" width="20%" style="border-right: 1px solid;">
						<label for="Particulars">Particulars</label>
					</td>
					<td align="center"  width="10%">
						<label for="ammount">Amount(Rs.)</label>
					</td>
	 				<td align="center"  width="6%" style="border-left: 1px solid;">
						<label for="IN">Inst. No.</label>
					</td>  
				</tr>
			</table>
			<table cellpadding="0" cellspacing="0" border="0" frame="box" width="100%" style="border-color:#353732 ;" align="CENTER" >
				<tr height="100%" vAlign="top">
					<td width="24%" height="100%" style="border-left: 1px solid;border-color:#353732;">
						<table cellpadding="0" cellspacing="0" border="0" frame="box"  width="100%" align="CENTER" >
							<c:forEach var="object" items="${allowLst}" varStatus="dExpcounter" >
								<c:if test="${object.amount gt 0}">
									<tr>   
 										<td width="14%">&nbsp;&nbsp;<c:out value="${object.displayName}"/>&nbsp;</td>	
  										<td width="10%" align="right">
  											&nbsp;&nbsp;<c:set var="amt" value="${object.amount}"/>
 										<%
 											double amount=Double.parseDouble(pageContext.getAttribute("amt").toString());
 											pageContext.setAttribute("newValue", Math.round(amount)); 
 										%>
 											<c:out  value="${newValue}"/>&nbsp;&nbsp;    
 										</td>
  									</tr>
  								</c:if>
   							</c:forEach>
						</table>
					</td>
					<td width="40%" style="border-left: 1px solid;border-color:#353732;border-right: 1px solid;">
						<table cellpadding="0" cellspacing="0" border="0" frame="box"  width="100%"  align="CENTER">
							<c:forEach var="object" items="${deducLst}" varStatus="dExpcounter" >
								<c:if test="${object.amount gt 0}">
									<tr valign="top">   
  				 						<td width="20%" >
  				 							&nbsp;&nbsp;&nbsp;<c:out value="${object.displayName}"/>
   										</td>	
   										<td width="10%" align="right">
				    						&nbsp;<c:set var="deducDouble" value="${object.amount}"/>
    									<%
    										double deducAmount=Double.parseDouble(pageContext.getAttribute("deducDouble").toString());
    										pageContext.setAttribute("newDouble", Math.round(deducAmount)); 
    									%>
    										<c:out  value="${newDouble}"/>&nbsp;&nbsp;
   										</td>
   										<td width="10%" align="center">
    										<c:out value="${object.installMent}"/>
    									</td>
   									</tr>
								</c:if>
 							</c:forEach>	
						</table>
					</td>
					<td width="36%" style="border-right: 1px solid;border-color:#353732;">
						<table cellpadding="0" cellspacing="0" border="0" frame="box" width="100%"  align="CENTER">
							<c:forEach var="object" items="${nonGovernmetList}" varStatus="dExpcounter" >
								<c:if test="${object.amount gt 0}">
									<tr>
										<td width="20%" align="left">
											&nbsp;&nbsp;&nbsp;<c:out value="${object.displayName}"/>&nbsp;
										</td>
										<td width="10%" align="right">	
											&nbsp;&nbsp;<c:set var="nonGovDouble" value="${object.amount}"/>
										<%
    										double nonGovern=Double.parseDouble(pageContext.getAttribute("nonGovDouble").toString());
    										pageContext.setAttribute("newNonGovern", Math.round(nonGovern)); 
   										%>
											<c:out value="${newNonGovern}"/>&nbsp;&nbsp;
										</td>
				 						<td width="6%" align="right" >	
											&nbsp;
										</td>  
									</tr>
								</c:if>
							</c:forEach>
						</table>
					</td>
				</tr>
			</table>
			
			<table cellpadding="0" cellspacing="0" border="0" frame="box" align="CENTER" width="100%" style="border-color:#353732 ;border: 1px solid;" >
				<tr>
					<td align="left"  width="14%">
						<p><label for="TEmolument">&nbsp;&nbsp;Total Emolument</label></p>
					</td>
					<td align="right"  width="10%" style="border-left: 1px solid;border-right: 1px solid;">
						<p><c:out value="${allowTotal}"/>&nbsp;&nbsp;</p>
					</td>
					<td align="left"  width="20%" >
						<p><label for="govt">&nbsp;&nbsp;&nbsp;Total Govt. Recoveries</label></p>
					</td>
					<td align="right"  width="10%" style="border-left: 1px solid;border-right: 1px solid;">
						<p><c:out value="${deducTotal}"/>&nbsp;&nbsp;</p>
					</td>
					<td align="left"  width="10%" style="border-right: 1px solid;"></td>
					<td align="left"  width="20%" style="border-right: 1px solid;">
						<p><label for="NG">&nbsp;&nbsp;&nbsp;Total NG Recoveries</label></p>
					</td>
					<td align="right"  width="10%">
						<p><c:out value="${ngTotal}"/>&nbsp;&nbsp;</p>
					</td>
					<td align="left"  width="6%" style="border-left: 1px solid;">&nbsp;</td>	
				</tr>
			</table>
			<table cellpadding="0" cellspacing="0" border="0" frame="box" align="CENTER" width="100%" style="border-color:#353732 ;border-left: 1px solid;border-right: 1px solid;" >
				<tr>
					<td width="64%">
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td align="left"  width="100%" style="border-bottom: 1px solid;border-color:#353732 ;border-right: 1px solid;">
									<p><label for="NetPay">Net Pay:-</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<c:out value="${netTotal} ${netTotalWord}"/>
								</td>
							</tr>
							<tr>
								<td align="left"  width="100%" style="border-bottom: 1px solid;border-color:#353732 ;border-right: 1px solid;">
									Voucher No: <c:out value="${voucherNumber}"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									Voucher Date: <c:out value="${voucherDate}"/>
								</td>
							</tr>
						</table>
					</td>
					<td width="36%" style="border-bottom: 1px solid; border-color: #353732" align="center">
						<c:out value="${basicVO.office}" /><br>
						 <%java.util.Date date = new java.util.Date();
								pageContext.setAttribute("date", date);%>
							<b>Verification Time:-<fmt:formatDate value="${date}" type="both" timeStyle="long" dateStyle="long" /></b>
					</td>
				</tr>
			</table>	
			

<%
		} 
%>
<%
		if(i%2==0 && (deductionsList.size()>=5 && allowancesList.size() <= 12))
		{
%>
			<DIV style="page-break-after:always"></DIV> <!-- for page break in printing. -->
<% 
		}
	}
%>
<div id="tcontent1" style="background-color: #E8E3E3;" >
<% 	
	empId=0;
	lst=null;
	keyIt = keySet.iterator();
	allowTotal=0;
	deducTotal=0;
	ngTotal=0;
	netTotal=0;
	netTotalInWords="";
	while(keyIt.hasNext()) 
	{
		Object key = keyIt.next();
		empId=Long.parseLong(key.toString());
		lst =(HashMap)payslipMap.get(empId);
		allowancesList=(ArrayList)lst.get("allowanceList");
		deductionsList=(ArrayList)lst.get("deductionList");
		nonGovernmetList=(ArrayList)lst.get("nonGovernmetList");
		allowTotal=(Long)lst.get("allowTotal");
		deducTotal=(Long)lst.get("deducTotal");
		netTotal=(Long)lst.get("netPay");
		ngTotal=(Long)lst.get("ngTotal");
		netTotalInWords = lst.get("netPayInWords").toString();
		PayslipBasicDetailsVO basicVO=(PayslipBasicDetailsVO)lst.get("payslipBasicVO");
		pageContext.setAttribute("allowLst",allowancesList);
		pageContext.setAttribute("deducLst",deductionsList);
		pageContext.setAttribute("nonGovernmetList",nonGovernmetList);
		pageContext.setAttribute("allowTotal",allowTotal);
		pageContext.setAttribute("deducTotal",deducTotal);
		pageContext.setAttribute("ngTotal",ngTotal);
		pageContext.setAttribute("basicVO",basicVO);
		pageContext.setAttribute("netTotal",netTotal);
		pageContext.setAttribute("netTotalWord",netTotalInWords);
		if(deductionsList.size()>35 || allowancesList.size() > 12)
		{
			if(i>0)
			{
%>
				<table cellpadding="0" cellspacing="0"  align="CENTER" width="100%">
					<tr>
						<td>
							&nbsp;
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;
						</td>
					</tr>
					<tr>
						<td style="border-color:#353732;border-bottom: 1px solid;" width="100%">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;
						</td>
					</tr>
				</table>
<%
			} 
			i++;
%>
			<table cellpadding="0" cellspacing="0"  align="CENTER" width="100%">
				<tr>
			     	<td align="left"  width="100%">
			     		<c:out value="${basicVO.office}" />
			     		
			     	</td>
			    </tr>     
			    <tr>
			    	<td width="100%">
			    		<table width="100%" cellpadding="0" cellspacing="0">
			    			<tr>
			    				<td align="left"  width="45%" >
									<label for="Employee Name" >Employee Name:</label><b><c:out value="${basicVO.employeeName}"  /></b>
								</td>
								<td width="33%">
									<label for="Designation">Designation:</label><c:out value="${basicVO.designation}" />
								</td>
								<td width="22%">
									<label for="Salary Month">Salary Month:</label><c:out value="${basicVO.salaryMonth}" />			
								</td>
			    			</tr>
			    		</table>
			    	</td>
				</tr>
				<tr>
					<td width="100%">
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td width="45%">
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td width="60%">
												<label for="gpf">GPF/DCPS AC.No:</label> <c:out value="${basicVO.gpfAccNo}" />
											</td>
											<td width="40%">
												<label for="bankAccNo">Bank A/c No :</label>
												<%
													if(basicVO.getBankAccNo() != "null")
													{
												%>
													<c:out value="${basicVO.bankAccNo}" />
												<%		
													}
													else
													{
												%>
													<c:out value="-" />
												<%
													}
												%>
											</td>								
										</tr>
									</table>
								</td>
								<td width="33%">
									<label for="PayBand">Pay Band :</label><c:out value="${basicVO.payBand}" />
								</td>
								<td width="22%">
									<label for="PayInPB+GP">Pay In PB+GP :</label><c:out value="${basicVO.payInPaybandPlusGp}" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<table cellpadding="0" cellspacing="0" border="0" frame="box" align="CENTER" width="100%" style="border-color:#353732;border: 1px solid;" >
				<tr>
					<td align="center"  width="24%" >
						<label for="Emoluments">Emoluments</label>
					</td>
					<td align="center"  width="40%"  style="border-left: 1px solid;"  >
						<label for="govt">Govt. Recoveries</label>
						
					</td>
					<td align="center"  width="36%"   style="border-left: 1px solid;" >
						<label for="govt">Non Govt. Recoveries</label>
							
					</td>
				</tr>
			</table>
			<table cellpadding="0" cellspacing="0" border="0" frame="box" align="CENTER" width="100%" style="border-color:#353732;border-left: 1px solid;border-right: 1px solid;border-bottom: 1px solid;" >	
				<tr>
					<td align="center" width="14%" >
						<label for="Particulars">Particulars</label>
					</td>
					<td align="center"  width="10%" style="border-left: 1px solid;border-right: 1px solid;">
						<label for="amnt">Amount(Rs.)</label>	
					</td>
				
					<td align="center" width="20%">
						<label for="Particulars">Particulars</label>
					</td>
					<td align="center"  width="10%" style="border-left: 1px solid;border-right: 1px solid;">
						<label for="amnt">Amount(Rs.)</label>
					</td>
					<td align="center"  width="10%" style="border-right: 1px solid;">
						<label for="Inst">Inst. No.</label>
					</td>
					<td align="center" width="20%" style="border-right: 1px solid;">
						<label for="Particulars">Particulars</label>
					</td>
					<td align="center"  width="10%">
						<label for="ammount">Amount(Rs.)</label>
					</td>
				 	<td align="center"  width="6%" style="border-left: 1px solid;">
						<label for="IN">Inst. No.</label>
					</td>  
				</tr>
			</table>
			<table cellpadding="0" cellspacing="0" border="0" frame="box" width="100%" style="border-color:#353732 ;" align="CENTER" >
				<tr height="100%" vAlign="top">
					<td width="24%" height="100%" style="border-left: 1px solid;border-color:#353732;">
						<table cellpadding="0" cellspacing="0" border="0" frame="box"  width="100%" align="CENTER" >
								<c:forEach var="object" items="${allowLst}" varStatus="dExpcounter" >
								<c:if test="${object.amount gt 0}">
									<tr>   
			 							<td width="14%">
			 								&nbsp;&nbsp;<c:out value="${object.displayName}"/>&nbsp;
			 							</td>	
			  							<td width="10%" align="right">&nbsp;&nbsp;
			  								<c:set var="amt" value="${object.amount}"/>
			 								<%
			 									double amount=Double.parseDouble(pageContext.getAttribute("amt").toString());
			 									pageContext.setAttribute("newValue", Math.round(amount)); 
			 								%>
			 								<c:out  value="${newValue}"/>&nbsp;&nbsp;    
			 							</td>
			  						</tr>
			  					</c:if>
			   					</c:forEach>
						</table>
					</td>
					<td width="40%" style="border-left: 1px solid;border-color:#353732;border-right: 1px solid;">
						<table cellpadding="0" cellspacing="0" border="0" frame="box"  width="100%"  align="CENTER">
								<c:forEach var="object" items="${deducLst}" varStatus="dExpcounter" >
									<c:if test="${object.amount gt 0}">
										<tr valign="top">   
			  				 				<td width="20%" >
			  				 					&nbsp;&nbsp;&nbsp;<c:out value="${object.displayName}"/>
			   								</td>	
			   								<td width="10%" align="right">
							    				&nbsp;<c:set var="deducDouble" value="${object.amount}"/>
			    								<%
			    									double deducAmount=Double.parseDouble(pageContext.getAttribute("deducDouble").toString());
			    									pageContext.setAttribute("newDouble", Math.round(deducAmount)); 
			    								%>
			    								<c:out  value="${newDouble}"/>&nbsp;&nbsp;
			   								</td>
			   								<td width="10%" align="center">
			    								<c:out value="${object.installMent}"/>
			    							</td>
			   							</tr>
									</c:if>
			 					</c:forEach>	
						</table>
					</td>
					<td width="36%" style="border-right: 1px solid;border-color:#353732;">
						<table cellpadding="0" cellspacing="0" border="0" frame="box" width="100%"  align="CENTER">
							<c:forEach var="object" items="${nonGovernmetList}" varStatus="dExpcounter" >
							<c:if test="${object.amount gt 0}">
								<tr>
									<td width="20%" align="left">
										&nbsp;&nbsp;&nbsp;<c:out value="${object.displayName}"/>&nbsp;
									</td>
									<td width="10%" align="right">	
										&nbsp;&nbsp;<c:set var="nonGovDouble" value="${object.amount}"/>
										<%
			    							double nonGovern=Double.parseDouble(pageContext.getAttribute("nonGovDouble").toString());
			    							pageContext.setAttribute("newNonGovern", Math.round(nonGovern)); 
			   							%>
										<c:out value="${newNonGovern}"/>&nbsp;&nbsp;
									</td>
							 		<td width="6%" align="right" >	
										&nbsp;
									</td>  
								</tr>
								</c:if>
							</c:forEach>
						</table>
					</td>
				</tr>
			</table>
			<table cellpadding="0" cellspacing="0" border="0" frame="box" align="CENTER" width="100%" style="border-color:#353732 ;border: 1px solid;" >
				<tr>
					<td align="left"  width="14%">
						<p><label for="TEmolument">&nbsp;&nbsp;Total Emolument</label>
						</p>
					</td>
					<td align="right"  width="10%" style="border-left: 1px solid;border-right: 1px solid;">
						<p><c:out value="${allowTotal}"/>&nbsp;&nbsp;</p>
					</td>
					
					<td align="left"  width="20%" >
						<p><label for="govt">&nbsp;&nbsp;&nbsp;Total Govt. Recoveries</label>
						</p>
					</td>
					<td align="right"  width="10%" style="border-left: 1px solid;border-right: 1px solid;">
						<p><c:out value="${deducTotal}"/>&nbsp;&nbsp;</p>
					</td>
					<td align="left"  width="10%" style="border-right: 1px solid;">
					</td>
					
					<td align="left"  width="20%" style="border-right: 1px solid;">
						<p><label for="NG">&nbsp;&nbsp;&nbsp;Total NG Recoveries</label>
						</p>
					</td>
					<td align="right"  width="10%">
						<p><c:out value="${ngTotal}"/>&nbsp;&nbsp;</p>
					</td>
					<td align="left"  width="6%" style="border-left: 1px solid;">
						&nbsp;
					</td>	
				</tr>
			</table>	
			<table cellpadding="0" cellspacing="0" border="0" frame="box" align="CENTER" width="100%" style="border-color:#353732 ;border-left: 1px solid;border-right: 1px solid;" >
				<tr>
					<td width="64%">
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr >
								<td align="left"  width="100%" style="border-bottom: 1px solid;border-color:#353732 ;border-right: 1px solid;">
									<p><label for="NetPay">Net Pay:-</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<c:out value="${netTotal} ${netTotalWord}"/>
								</td>
							</tr>
							<tr>
								<td align="left"  width="100%" style="border-bottom: 1px solid;border-color:#353732 ;border-right: 1px solid;">
									Voucher No: <c:out value="${voucherNumber}"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									Voucher Date: <c:out value="${voucherDate}"/>
								</td>
							</tr>
						</table>
					</td>
					<td width="36%" style="border-bottom: 1px solid; border-color: #353732" align="center">
						
						 
							<c:out value="${basicVO.office}" /><br/>
								<b>Verification Time:-<fmt:formatDate value="${date}" type="both" timeStyle="long" dateStyle="long" /></b>
					</td>
				</tr>
			</table>
<%
		} 
%>
		<DIV style="page-break-after:always"></DIV> <!-- for page break in printing. -->
<% 
	}
%>
  
<script type="text/javascript">
	window.FILL_COMBO_BOX_TAB_WISE = false;
	initializetabcontent("maintab")
</script>	
</div>
		
<table width="100%">
	<tr id="buttons" style="display:true">
		<td align="center">
			<hdiits:button type="button" value="Print" name="printButton" onclick="printFunction();" />
			<hdiits:button name="Back" type="button"  caption="Close" onclick="GoToPage();" />
			<hdiits:button type="button" value="Save" name="saveFile" onclick="saveFileFunction();"/>
		</td>
	</tr>
</table>
</hdiits:form>

<%
} 
catch (Exception e) 
{
	Log logger = LogFactory.getLog(PayslipBasicDetailsVO.class);
	logger.error(e);
}
%>