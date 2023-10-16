<%@ include file="../core/include.jsp" %>
<%@page import="com.tcs.sgv.common.utils.DateUtility,java.util.ArrayList,com.tcs.sgv.common.valueobject.CmnLookupMst,java.util.List,java.util.Iterator"%>
<%@page import="java.util.Date,java.util.HashMap, com.tcs.sgv.core.valueobject.ResultObject,java.util.Map"%>
<%@page import=" com.tcs.sgv.lcm.valueobject.TrnLcChequePosting,com.tcs.sgv.lcm.valueobject.TrnLcBudgetPosting"%>
<%@page import=" com.tcs.sgv.lcm.valueobject.LcDivisionInformationVO"%>
<%@page import=" com.tcs.sgv.lcm.valueobject.TrnLcDtlPosting,java.util.StringTokenizer"%>
<%@page import=" com.tcs.sgv.lcm.valueobject.TrnLcDeductionPosting,com.tcs.sgv.apps.common.valuebeans.ComboValuesVO"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<fmt:setBundle basename="resources.lcm.lcexp_en_US" var="lcexpLabels" scope="application"/>
<fmt:setBundle basename="resources.lcm.LcmConstants" var="lcconstants" scope="application"/>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>    
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<%@page import="com.tcs.sgv.apps.valuebeans.budget.BudExpEstDmdVO"%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.ResourceBundle"%>
 <%  Locale userLocale = null;
     if ((session.getAttribute("LocaleID")!= null) && (!(session.getAttribute("LocaleID").equals("null"))))
     {
        userLocale = (Locale) session.getAttribute("LocaleID") ;

     }
     else
     {

        userLocale = request.getLocale();
     }
     ResourceBundle lcStringsBundle = ResourceBundle.getBundle("/resources/lcm/lcexp_en_US", userLocale); 
     
     ResourceBundle lcConstants = ResourceBundle.getBundle("/resources/lcm/LcmConstants",userLocale);  

    
     HashMap lMapDemMjrHd = new HashMap();
     HashMap lMapSbMjrHd = new HashMap();
     HashMap lMapMinHd = new HashMap();
     HashMap lMapSbHd = new HashMap();
     ArrayList lArrDemList = new ArrayList();

     
     Iterator lItDemands = null;
     Iterator lItSuMjrHd = null;
     Iterator lItMinHd= null;
     Iterator lItSbHd= null;
     if(request.getAttribute("MjrHdMap")!=null)
     {
    	
    	 lMapDemMjrHd=(HashMap)request.getAttribute("MjrHdMap");
    	
    	 lItDemands = lMapDemMjrHd.keySet().iterator();
     }
     if(request.getAttribute("demandlist")!=null)
     {
    	 lArrDemList=(ArrayList) request.getAttribute("demandlist");
    	 
     }

     if(request.getAttribute("SubMjrHd")!=null)
     {
    	
    	 lMapSbMjrHd=(HashMap)request.getAttribute("SubMjrHd");
    	
    	 lItSuMjrHd = lMapSbMjrHd.keySet().iterator();
    	
     }
     
     if(request.getAttribute("MinHd")!=null)
     {
    	
    	 lMapMinHd=(HashMap)request.getAttribute("MinHd");
    	
    	 lItMinHd = lMapMinHd.keySet().iterator();
    	
     }
     if(request.getAttribute("SubHd")!=null)
     {
    	
    	 lMapSbHd=(HashMap)request.getAttribute("SubHd");
    	
    	 lItSbHd = lMapSbHd.keySet().iterator();
    	
     }

     
  %>   
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>LC Advice Received </title>
		<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
		<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
		<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
		<script type="text/javascript"	src="<c:url value="script/common/tabcontent.js"/>"></script>
		<script type="text/javascript"  src="script/common/commonfunctions.js"></script>
		<script  type="text/javascript"  src="script/common/CalendarPopup.js"></script>
		<script  type="text/javascript"  src="script/common/tagLibValidation.js"></script>
		<script  type="text/javascript"  src="script/common/LCCommonFunction.js"></script>

		<c:set var="resultObj" value="${result}" > </c:set>	
		
		<c:set var="lArrBudType" value="${resValue.lArrBudType}"> </c:set>
		
		<script language="JavaScript"><!--
		
		<%
     		  TrnLcDtlPosting lcDtlPostingVo=null;  
			  TrnLcChequePosting lcChqPostingVo=null;  
			  LcDivisionInformationVO divInformationVO =null;
			  TrnLcDeductionPosting lcDedPostingVo=null;
			  ComboValuesVO comboVo=null;
			  
			  ArrayList lArrChqPosting=null;
			  ArrayList lArrBudPosting=null;
			  ArrayList lArrDedPosting=null;
			  ArrayList lArrMonths=null;
			  
			  if(request.getAttribute("lArrChqPosting") != null)
			  {
				  lArrChqPosting=(ArrayList)request.getAttribute("lArrChqPosting");
			      System.out.println("-----SIZE OF lArrChqPosting in UPDATE JSP-------"+lArrChqPosting.size());
				  request.setAttribute("lArrChqPosting",lArrChqPosting);
			 }	
			  
			  if(request.getAttribute("divInformationVO") != null)
			  {
				  divInformationVO=(LcDivisionInformationVO)request.getAttribute("divInformationVO");
				  System.out.println("----IN UPDATE JSP----divInformationVO----"+divInformationVO.toString());
			  }
			  
			  if(request.getAttribute("lcDtlPostingVo") != null)
			  {
				  lcDtlPostingVo=(TrnLcDtlPosting)request.getAttribute("lcDtlPostingVo");
				  System.out.println("----IN UPDATE JSP----lcDtlPostingVo----"+lcDtlPostingVo.toString());
			  }	
			  if(request.getAttribute("lArrBudPosting") != null)
			  {
				  lArrBudPosting=(ArrayList)request.getAttribute("lArrBudPosting");
				  System.out.println("----IN UPDATE JSP---- size of lArrBudPosting ----"+lArrBudPosting.size());
			  }	
			  if(request.getAttribute("lArrDedPosting") != null)
			  {
				  lArrDedPosting=(ArrayList)request.getAttribute("lArrDedPosting");
				  System.out.println("----IN UPDATE JSP---- size of lArrDedPosting ----"+lArrDedPosting.size());
			  }	
			  if(request.getAttribute("lArrMonths") != null)
			  {
				  lArrMonths=(ArrayList)request.getAttribute("lArrMonths");
				  System.out.println("----IN UPDATE JSP---- size of lArrMonths ----"+lArrMonths.size());
			  }	
			  
			 
			  %>
		
		
		
		
		var chkRowNo='<%=lArrChqPosting.size()%>';
		var budRowNo='<%=lArrBudPosting.size()%>';
		var dedRowNo='<%=lArrDedPosting.size()%>';
		var classArr=new Array();
		var fundArr=new Array();
		var budArr=new Array();
		var demandArr=new Array();
		
		
		
		classArr[0]='<%=lcConstants.getString("LC.CLASS1") %>';
		classArr[1]='<%=lcConstants.getString("LC.CLASS2") %>';		
	
		
		fundArr[0]='<%=lcConstants.getString("LC.FUND3") %>';
		fundArr[1]='<%=lcConstants.getString("LC.FUND4") %>';
		fundArr[2]='<%=lcConstants.getString("LC.FUND5") %>';
		
		budArr[0]='<%=lcConstants.getString("LC.BUD1") %>';
		budArr[1]='<%=lcConstants.getString("LC.BUD2") %>';
		budArr[2]='<%=lcConstants.getString("LC.BUD3") %>';
		budArr[3]='<%=lcConstants.getString("LC.BUD4") %>';
		budArr[4]='<%=lcConstants.getString("LC.BUD5") %>';
		budArr[5]='<%=lcConstants.getString("LC.BUD6") %>';
		
		<%

//------------------------------for demand and major head-------------------------------		
		int lIDmdCntr=0;
		String lStrTmp="";
		for(lIDmdCntr=0;lIDmdCntr<lArrDemList.size();lIDmdCntr++)
		{	
			lStrTmp=(String)lArrDemList.get(lIDmdCntr);
			
			%>
			demandArr[<%=lIDmdCntr%>]="<%=lStrTmp %>";
			<%
			
			
		}

		
		 %>
		 
			 var mjrHdArr = new Array(); 
		 <%
		 lItDemands = lMapDemMjrHd.keySet().iterator();
		 int lIMArrCntr = 0;
	
		 for(lIMArrCntr=0;lIMArrCntr<lArrDemList.size();lIMArrCntr++)
		 {%>
			mjrHdArr[<%=lIMArrCntr%>]=new Array();
			<%
			 ArrayList lArrDmdWiseMjr = new ArrayList();
			lArrDmdWiseMjr = (ArrayList)lMapDemMjrHd.get((String)lArrDemList.get(lIMArrCntr));
			for(int liCntr=0;liCntr<lArrDmdWiseMjr.size();liCntr++)
			{
			%>
		    	mjrHdArr[<%=lIMArrCntr%>][<%=liCntr%>]="<%=lArrDmdWiseMjr.get(liCntr)%>";
		    <% 
			}
			
		 }

	
		 
//-------------------------------------------------for sub major heads--------------------------------------------
		 %>
			 var subMjrHdArr = new Array(); 
			 var keyArr = new Array();
		 <%
		
		 int counter=0;
		 lItSuMjrHd = lMapSbMjrHd.keySet().iterator();
		 while(lItSuMjrHd.hasNext())
			{	
				lStrTmp=(String)lItSuMjrHd.next();

				%>
				keyArr[<%=counter%>]="<%=lStrTmp %>";
				<%
				counter++;
			}
		 

		 lItSuMjrHd = lMapSbMjrHd.keySet().iterator();
    	
		 int lISbMArrCntr = 0;
		 int liSbCntr=0;

		 while(lItSuMjrHd.hasNext())
		 {

		 %>
			subMjrHdArr[<%=lISbMArrCntr%>]=new Array();
			<%
			 ArrayList lArrSbMjr = new ArrayList();
			 String tmp = (String)lItSuMjrHd.next();

			 if(lMapSbMjrHd.containsKey(tmp))
			{	
				lArrSbMjr=(ArrayList)lMapSbMjrHd.get(tmp);

			}

			 for(liSbCntr=0;liSbCntr<lArrSbMjr.size();liSbCntr++)
			{

			%>
			    subMjrHdArr[<%=lISbMArrCntr%>][<%=liSbCntr%>]="<%=lArrSbMjr.get(liSbCntr)%>";
		    <% 
			}
			lISbMArrCntr++;
		 }
		%>
			

//------------------------------------for minor heads---------------------------------------			
			 var minHdArr = new Array(); 
			 var minKeyArr = new Array();
		 <%
	
		 counter=0;
    	 lItMinHd = lMapMinHd.keySet().iterator();
    
    	 while(lItMinHd.hasNext())
			{	
				lStrTmp=(String)lItMinHd.next();
	
				%>
				minKeyArr[<%=counter%>]="<%=lStrTmp %>";
				<%
				counter++;
			}
    
		 
		 lItMinHd = lMapMinHd.keySet().iterator();
    	
		 int minCntr = 0;
		 int liForLpCntr=0;
	
		 while(lItMinHd.hasNext())
		 {
	
		 %>
			minHdArr[<%=minCntr%>]=new Array();
			<%
			 ArrayList lArrMinHd = new ArrayList();
			 String tmp = (String)lItMinHd.next();
	
			 if(lMapMinHd.containsKey(tmp))
			{	
	
				 lArrMinHd=(ArrayList)lMapMinHd.get(tmp);
			}
			for(liForLpCntr=0;liForLpCntr<lArrMinHd.size();liForLpCntr++)
			{
	
			%>
			    minHdArr[<%=minCntr%>][<%=liForLpCntr%>]="<%=lArrMinHd.get(liForLpCntr)%>";
		    <% 
			}
			minCntr++;
		 }
	
		 %>	

			
			
//-----------------------------------for sub heads------------------------------------------
//   	 lMapSbHd=(HashMap)request.getAttribute("SubHd");
//    	
//    	 lItSbHd = lMapSbHd.keySet().iterator();
	
		 var sbHdArr = new Array(); 
			 var sbKeyArr = new Array();



		 <%
	
		 counter=0;
		 lItSbHd = lMapSbHd.keySet().iterator();
	
		 while(lItSbHd.hasNext())
			{	
	
			 lStrTmp=(String)lItSbHd.next();
			 
				%>
				sbKeyArr[<%=counter%>]="<%=lStrTmp %>";
				<%
				counter++;
			}
		 
	
		 lItSbHd = lMapSbHd.keySet().iterator();
	
		   minCntr = 0;
		   liForLpCntr=0;

		 while(lItSbHd.hasNext())
		 {

		 %>
			sbHdArr[<%=minCntr%>]=new Array();
			<%
			 ArrayList lArrMinHd = new ArrayList();
			 String tmp = (String)lItSbHd.next();

			 if(lMapSbHd.containsKey(tmp))
			{	

				 lArrMinHd=(ArrayList)lMapSbHd.get(tmp);
			}
			for(liForLpCntr=0;liForLpCntr<lArrMinHd.size();liForLpCntr++)
			{

			%>
			    sbHdArr[<%=minCntr%>][<%=liForLpCntr%>]="<%=lArrMinHd.get(liForLpCntr)%>";
		    <% 
			}
			minCntr++;
		 }
		%>	


//--------------------------------------------------------------------------------------------						


			function loadcalendar(name,img)
			{
				   alert('came in load calender' );
				   var cal1 = new CalendarPopup();
				   alert('Calendar instantiated' );
				   cal1.select(name,img,'dd/MM/yyyy'); 
				   return false;
				   
			 }
			 
			 function validDtlData()
			 {
				
				 var adviceNo = document.getElementById("txtAdviceNo"); 
				 var divisionCode = document.getElementById("txtDivisionCode");
				 var adviceDt = document.getElementById("txtAdviceDate");
				 var drwOff = document.getElementById("txtDrwOff");
				 var divOff = document.getElementById("txtDivOff");
				 var tsryOff = document.getElementById("txtTsryOff");
				 var bank = document.getElementById("id_bank");
				 var month= document.getElementById("id_month");
				 var divName = document.getElementById("txtDivName");
				 
				 if(adviceNo.value == '')
				 {
				 	alert('Please Enter Advice No.');
				 	document.forms[0].txtAdviceNo.select();
				 	return false;
				 }
				 if( adviceDt.value == '')
				 {
				 	alert('Please Enter Advice Date');
				 	return false;
				 }				 
				 if( divisionCode.value == '')
				 {
				 	alert('Please Enter Division Code');
				 	document.forms[0].txtDivisionCode.select();
				 	return false;
				 }
				 if(divName.value == '-')
				 {
				 	alert('Please Enter Valid Division Code');
				 	document.forms[0].txtDivisionCode.select();
				 	return false;
				 }
				 if( drwOff.value == '')
				 {
				 	alert('Please Enter Drawing Officer Name');
				 	document.forms[0].txtDrwOff.select();
				 	return false;
				 }
				 if( divOff.value == '')
				 {
				 	alert('Please Enter Division Officer Name');
				 	document.forms[0].txtDivOff.select();
				 	return false;
				 }
				 if( tsryOff.value == '')
				 {
				 	alert('Please Enter Treasury Officer Name');
				 	document.forms[0].txtTsryOff.focus();
				 	return false;
				 }
				 
				 if( eval(bank.value) == -1)
				 {
				 	alert('Please Select Bank');
				 	bank.focus();				 	
				 	return false;
				 }
				
				
				 return true;				 
			 }
			 
			 function validChqData()
			 {
		 	    for(var i=0;i<chkRowNo;i++)
			    {
			        chkDt=(eval('document.forms[0].chkDt'+i));
			        chkNo=(eval('document.forms[0].chkNo'+i));
			        chkAmt=(eval('document.forms[0].chkAmt'+i));
			        chkPartyNm=(eval('document.forms[0].chkPartyNm'+i));
			        
			        if(chkDt != null && chkNo != null && chkAmt !=null && chkPartyNm != null)
			        {				        
			            if(chkDt.value.length>0 || chkNo.value.length>0 || chkAmt.value > 0 || chkPartyNm.value.length>0 )
			            {
			                  if(chkDt.value.length<1)
			                  {
			                      alert('Please Enter Cheque Date');
			                      chkDt.focus();
			                      return false;
			                  }
			                  if(chkNo.value.length<1)
			                  {
			                      alert('Please Enter Cheque No');
			                      chkNo.focus();
			                      return false;
			                  }
			                  else if(chkAmt.value.length<1 || chkAmt.value==0)
			                  {
			                     alert('Please Enter Cheque Amount');
			                     chkAmt.focus();
			                     return false;
			                  }
			                  else if(chkPartyNm.value.length<1)
			                  {
			                     alert('Please Enter Party Name');
			                     chkPartyNm.focus();
			                     return false;
			                  }
			              }
		            }
			    } 
		 	   return true;
			 }	
			 
			 function validBudData()
			 {
			 	for( i=0;i<budRowNo;i++)
			    {     
			      	var classExp=(eval('document.forms[0].cmbClassOfExp'+i));
			        var fund=(eval('document.forms[0].cmbFund'+i));
			        var DrawingOfficer=(eval('document.forms[0].txtDrwOff'+i));
			        var DemandNo=(eval('document.forms[0].cmbDemandNo'+i));
			        var budType=(eval('document.forms[0].cmbBudgetType'+i));
			        var schemeNo=(eval('document.forms[0].txtSchemeNo'+i));
			        var major=(eval('document.forms[0].cmbMjrHd'+i));
			        var subMjrHd=(eval('document.forms[0].cmbSubMjrHd'+i));
			        var minHd=(eval('document.forms[0].cmbMinHd'+i));
			        var subHd=(eval('document.forms[0].cmbSubHd'+i));
			        var amt = (eval('document.forms[0].txtExpAmt'+i));
			       
			        if(classExp != null && fund != null && DrawingOfficer != null && DemandNo!= null &&  budType != null && schemeNo != null && major != null && subMjrHd != null && minHd != null && subHd != null && amt != null)
			        {
			              if(classExp.selectedIndex >0 || fund.selectedIndex>0 ||  DrawingOfficer.value.length >0 || DemandNo.selectedIndex >0 || budType.selectedIndex >0 || schemeNo.value.length >0 || major.selectedIndex >0 || subMjrHd.selectedIndex >0 || minHd.selectedIndex >0 || subHd.selectedIndex >0 || amt.value > 0)
			              {
			                  
			                 if(classExp.selectedIndex==0)
			                  {
			                     alert('Please Enter Class Of Expenditure');
			                     classExp.focus();
			                     return false;
			                  }
			                  if(fund.selectedIndex==0)
			                  {
			                      alert('Please Enter Fund Type');
			                      fund.focus();
			                      return false;
			                  }
			                  if(DrawingOfficer.value.length<1)
			                  {
			                      alert('Please Enter Drawing Officer Detail');
			                      DrawingOfficer.focus();
			                      return false;
			                  }
			                   if(DemandNo.selectedIndex==0)
			                  {
			                      alert('Please Enter Demand No.');
			                      DemandNo.focus();
			                      return false;
			                  }
			                   if(budType.selectedIndex==0)
			                  {
			                      alert('Please Enter Budget Type');
			                      budType.focus();
			                      return false;
			                  }
			                   if(schemeNo.value.length<1)
			                  {
			                      alert('Please Enter Scheme No.');
			                      schemeNo.focus();
			                      return false;
			                  }
			                 if(major.selectedIndex==0)
			                  {
			                      
			                      alert('Please Enter Major Head');
			                      major.focus();
			                      return false;
			                  }
			                  if(subMjrHd.selectedIndex==0)
			                  {
			                  
			                      alert('Please Enter Sub Major Head');
			                      subMjrHd.focus();
			                      return false;
			                  }
			                  if(minHd.selectedIndex==0)
			                  {
			                      alert('Please Enter Minor Head');
			                      minHd.focus();
			                      return false;
			                  }
			                  if(subHd.selectedIndex==0)
			                  {
			                      alert('Please Enter Sub Head');
			                      subHd.focus();
			                      return false;
			                  }
			                  if(amt.value.length<1 && amt.value==0)
			                  {
			                      alert('Please Enter Expenditure Amount');
			                      amt.select();
			                      return false;
			                  }
			              }			                 
			            
			        }
			    }
			 	return true;
			 }				 
			 
			 function checkTotalAmt()
			 {
			    var chqTotal=eval('document.forms[0].txtChqTotal.value');
			    var netTotal=eval('document.forms[0].txtNetTotal.value');
			    if(chqTotal==netTotal)
			       return true;
			    else
			    {
			       alert('Total Cheque Amount should be equal to Net Total');
			       return false;
			    }   
			    
			 }
			 
			 function saveDtlPosting()
			 {			 	
			 	if(validDtlData())
			 	{
				 	if(validChqData())
				 	{
					 	if(validBudData())
					 	{
						 	if(checkTotalAmt())
						 	{
							 	document.forms[0].totalChqRow.value= chkRowNo;	
							 	document.forms[0].totalBudRow.value= budRowNo;	
							 	//alert(chkRowNo+'  '+budRowNo);
							 	var contextPath = '<%=request.getContextPath()%>';
								newURL = contextPath +'/ifms.htm?actionFlag=updateDtlPosting' ;
						 		document.forms[0].action=newURL;
						 		if(openingBalCheck())
								{
							 		if(confirm('Do You Want To Save LC Advice Data'))
									{               						 	
               						 	showProgressbar();
							 			document.forms[0].btnSubmit.disabled=true;
							 			document.forms[0].btnClose.disabled=true;
							 			document.forms[0].submit();
							 		}
							 	}	
						 	}
					 	}	
				 	}
			 	}	
			 }
			 
			 function openingBalCheck()
			{
			   var openingBal = eval(document.getElementById("txtOpeningLcBal").value);
			   if(openingBal < 0)
				{
					alert('Opening Balance is negetive for this Division..');	
					return true;		
				}
				return true;
			}
			
			  function addDeduction(obj)
			  {			 	
			 	if(isNumericWD(obj))
				{
				 	var expTotal=0;	
				 	var zero =obj.value;
					if(zero.indexOf('0') == 0 && zero.length > 1 && zero.indexOf('.') != 1)
					{
					    alert('Please Enter Valid Number');
					    obj.focus();
					    obj.value=0;					    
					    return false;
					}				
					for(i=1;i<=dedRowNo;i++)
					{
						var expFld = document.getElementById('txtDedAmt'+i);
						if(expFld != null)	
					    {
						 expTotal += eval(expFld.value);
						}
					}		
					document.forms[0].txtDedTotal.value=eval(expTotal);
				 	
				 	var expTotal=eval('document.forms[0].txtExpTotal.value');
				 	document.forms[0].txtNetTotal.value=eval(expTotal)-eval('document.forms[0].txtDedTotal.value');
				 	return true;
				 }
				 return false;
			  }
			  
			  function addExpAmt(obj)
			  {
				if(isNumericWD(obj))
				{
					var expTotal=0;
					var zero =obj.value;
					if(zero.indexOf('0') == 0 && zero.length > 1)
					{
					    alert('Please Enter Valid Number');
					    obj.focus();
					    obj.select();					    
					    return false;
					}
					for(i=0;i<budRowNo;i++)
					{
						var expFld = document.getElementById('txtExpAmt'+i);			
						if(expFld != null)	
					    {
						 expTotal += eval(expFld.value);
						}
					}
					document.forms[0].txtExpTotal.value=eval(expTotal);
					
					var openLc=eval('document.forms[0].txtOpeningLcBal.value');
					document.forms[0].txtLcBalance.value=eval(openLc)-eval('document.forms[0].txtExpTotal.value');
					document.forms[0].txtNetTotal.value=eval('document.forms[0].txtExpTotal.value')-eval('document.forms[0].txtDedTotal.value');
				}
				return false;
			 }
			 function addChqAmt(obj)
     		 {
     			if(isNumericWD(obj))
				{
	     			var expTot=0;
	     			var zero =obj.value;
					if(zero.indexOf('0') == 0  && zero.length > 1)
					{
					    alert('Please Enter Valid Number');
					    obj.focus();
					    obj.select();					    
					    return false;
					}		
					for(i=0;i<chkRowNo;i++)
					{
						var expFld = document.getElementById('chkAmt'+i);	
						var chqCancelDt=document.getElementById('LcChqCancelDt'+i);
						//alert(chqCancelDt);
						if(chqCancelDt != null)
						{
							if(chqCancelDt.value == 'null' || chqCancelDt.value == '0' )
							{
								// alert('Inside '+chqCancelDt);
								 if(expFld != null )	
							     {
								    expTot += eval(expFld.value);
							     }	
							}
						}
					}
					document.forms[0].txtChqTotal.value=eval(expTot);
				}
				return false;
     		}
     		
     		function onLoadCall()
     		{
     		    for(i=0;i<chkRowNo+1;i++)
     		    {
     		       var temp = eval('document.forms[0].chkAmt'+i);  
			       var tot=0;
			       var i;
			       if(temp)
			       {			         
			            tot=eval(tot)+eval(temp.value);
			       }     		    	
     		    } 
     		    document.forms[0].txtChqTotal.value=eval(tot);    		
     		}
			
     
			function addChqRow()
			{
			  // Code for add dynamic row in Cheque Details table
			  //==========================================================
				
				 
				  var tbody = document.getElementById('ChequePostingTbl').getElementsByTagName('tbody')[0]; 
				  var row = document.createElement('TR'); 
				
				  var cell1 = document.createElement('TD'); 
				  var cell2 = document.createElement('TD'); 
				  var cell3 = document.createElement('TD'); 
				  var cell4 = document.createElement('TD'); 
				  var cell5 = document.createElement('TD'); 
				  
				   
				  var temp = "chkDt"+chkRowNo;
				 
				  cell1.innerHTML = "<input type=\"checkbox\"  name=\"chk"+chkRowNo+"\" value=chkRowNo\">"; 
				  cell2.innerHTML = "<input maxlength=\"10\" type=\"text\" class=\"TextCss\" size=\"11\" name=\"chkDt"+chkRowNo+"\" onblur=chkDateFormat(this)> <img src=\"images/CalendarImages/ico-calendar.gif\" width=\"20\" onClick=\"window_open('"+temp+"','375','570')\" ALT ='Calendar' align='absmiddle'>"; 
				  cell3.innerHTML = "<input type=\"text\" maxlength=\"8\" class=\"TextCss\" size=\"25\" name=\"chkNo"+chkRowNo+"\" onblur=isNumeric(this);sendRequest(this) >"; 
				  cell4.innerHTML = "<input type=\"text\" style=\"text-align:right\" maxlength=\"19\" class=\"TextCss\" size=\"25\" value=\"0\" name=\"chkAmt"+chkRowNo+"\" onblur=addChqAmt(this)>"; 
				  cell5.innerHTML = "<input type=\"text\" class=\"TextCss\" maxlength=\"100\" size=\"60\" name=\"chkPartyNm"+chkRowNo+"\" onblur=isAlphaNumeric(this)>"; 
				  cell5.innerHTML += "<input type=\"hidden\" class=\"TextCss\" value=\"0\" size=\"10\" name=\"LcChqId"+chkRowNo+"\" >"; 
				   cell5.innerHTML += "<input type=\"hidden\" class=\"TextCss\" value=\"0\" size=\"10\" name=\"LcChqCancelDt"+chkRowNo+"\" >"; 
				   
				  cell1.width="2%";
				  cell2.width="15%";
				  cell3.width="20%";
				  cell4.width="20%";
				  cell5.width="43%";
				  
				  row.appendChild(cell1); 
				  row.appendChild(cell2); 
				  row.appendChild(cell3); 
				  row.appendChild(cell4); 
				  row.appendChild(cell5); 
				  
				   
				  tbody.appendChild(row); 
				  
				  chkRowNo++;  
			}	
			
			
			function deleteChkRow()
			{
			    var j=0;
			    var count=0;
			    var flag=0;
			    var RowNotemp=chkRowNo;
			     
			    for( i=1;i<=RowNotemp;i++,j++)
			    {        
			       var temp = eval('document.forms[0].chk'+j);
			       var chqId=eval('document.forms[0].LcChqId'+j)
			       if(temp==null || temp==' ')
			            count++;
			       if(temp)
			       {
			           if(temp.checked && chqId.value==0)
			           {
			              		              
			               //code for showing total amt in lc amt field
			                var tmp=i-count;
						  	var rr= eval('document.forms[0].chkAmt'+j);	
						    //alert(rr.value);
						    document.forms[0].txtChqTotal.value=eval('document.forms[0].txtChqTotal.value')-eval(rr.value);
			              
			              ChequePostingTbl.deleteRow(i-count);		             
			              RowNotemp=RowNotemp-1;
			              i=i-1; 
			           }
			           else if(temp.checked && chqId.value != 0)
			           {
			           		flag=1;
			           }
			      }
			    } 
			    if(flag==1)
			      alert('Please click on Submit button to delete Remaining Rows.');	
			}
			
			function clearChqRow()
			{
				var j=0;
			    var count=0;
			    var flag=0;
			    var RowNotemp=chkRowNo;
			     
			    for( i=1;i<=RowNotemp;i++,j++)
			    {        
			       var temp = eval('document.forms[0].chk'+j);
			       var chqId=eval('document.forms[0].LcChqId'+j)			     
			       if(temp)
			       {
			           if(temp.checked && chqId.value==0)
			           {
			              		              
			               //code for showing total amt in lc amt field
			                var tmp=i-count;
						  	var rr= eval('document.forms[0].chkAmt'+j);	
						    //alert(rr.value);
						    document.forms[0].txtChqTotal.value=eval('document.forms[0].txtChqTotal.value')-eval(rr.value);
			              
			                var chqDt= eval('document.forms[0].chkDt'+j);
			                var chqNo= eval('document.forms[0].chkNo'+j);
			                var chqAmt= eval('document.forms[0].chkAmt'+j);
			                var partyNm= eval('document.forms[0].chkPartyNm'+j);
			                chqDt.value="";
			                chqNo.value="";
			                chqAmt.value=0;
			                partyNm.value="";
			                temp.checked=false;
			           }			          
			      }
			    } 
			}
			
			
			function addBudgetRow()
			{
			  
			  
			  var tbody = document.getElementById('DetailPostingTbl').getElementsByTagName('tbody')[0]; 
			  //table id is : DetailPostingTbl
			  
			  
			  var row = document.createElement('TR'); 
			
			  var cell1 = document.createElement('TD'); 
			  
			  var cell2 = document.createElement('TD'); 
			  var cell3 = document.createElement('TD'); 
			  var cell4 = document.createElement('TD'); 
			  var cell5 = document.createElement('TD'); 
			  var cell6 = document.createElement('TD'); 
			  var cell7 = document.createElement('TD'); 
			  var cell8 = document.createElement('TD'); 
			  var cell9 = document.createElement('TD'); 
			  var cell10 = document.createElement('TD'); 
			  var cell11 = document.createElement('TD'); 
			  var cell12 = document.createElement('TD'); 
			  var cell13 = document.createElement('TD'); 
			  var cell14 = document.createElement('TD'); 
			  
			  
			  cell1.innerHTML = "<input type=\"checkbox\" name=\"chkDtlPost"+budRowNo+"\" value=budRowNo\">"; 
			  cell2.innerHTML = "<select style=\"width:62px\" name=\"cmbClassOfExp"+budRowNo+"\"><option value=-1>--Select--</option></select>";
			  cell3.innerHTML = "<select style=\"width:50px\" name=\"cmbFund"+budRowNo+"\"><option value=-1>--Select--</option></select>";
			  cell4.innerHTML = "<input type=\"text\" class=\"TextCss\" size=\"7\" name=\"txtDrwOff"+budRowNo+"\"  onblur=isAlphaNumeric(this) >";
			  cell5.innerHTML = "<select style=\"width:60px\" onchange=\"getMajorHd(this)\"  name=\"cmbDemandNo"+budRowNo+"\"><option value=-1>--Select--</option></select>";
			  cell6.innerHTML = "<select style=\"width:60px\" name=\"cmbBudgetType"+budRowNo+"\"><option value=-1>--Select--</option></select>";
			  cell7.innerHTML = "<input type=\"text\" class=\"TextCss\" size=\"3\" maxlength=\"7\" name=\"txtSchemeNo"+budRowNo+"\" onblur=isNumeric(this);validScheme(this) >";
			  cell8.innerHTML = "<select style=\"width:60px\" onchange=\"getSbMjrHd(this)\" name=\"cmbMjrHd"+budRowNo+"\"><option value=-1>--Select--</option></select>";
			  cell9.innerHTML = "<select style=\"width:80px\" onchange=\"getMinHd(this)\" name=\"cmbSubMjrHd"+budRowNo+"\"><option value=-1>--Select--</option></select>";
			  cell10.innerHTML = "<select style=\"width:60px\" onchange=\"getSubHd(this)\" name=\"cmbMinHd"+budRowNo+"\"><option value=-1>--Select--</option></select>";
			  cell11.innerHTML = "<select style=\"width:60px\" name=\"cmbSubHd"+budRowNo+"\"><option value=-1>--Select--</option></select>";
			  cell12.innerHTML = "<input type=\"text\" class=\"TextCss\" size=\"2\" value=\"00\" name=\"txtDtlHd"+budRowNo+"\" onblur=isNumeric(this) >";
			  cell13.innerHTML = "<input type=\"text\" class=\"TextCss\" size=\"2\" value=\"0\" name=\"txtObjHd"+budRowNo+"\" onblur=isNumeric(this) >";
			  cell14.innerHTML = "<input type=\"text\" style=\"text-align:right\" maxlength=\"19\" class=\"TextCss\" size=\"11\" value=\"0\" name=\"txtExpAmt"+budRowNo+"\" onblur=addExpAmt(this) >";
			  cell14.innerHTML += "<input type=\"hidden\" class=\"TextCss\" size=\"6\" value=\"0\" name=\"LcBudId"+budRowNo+"\"  >";
			  
			  
			  row.appendChild(cell1); 
			  row.appendChild(cell2); 
			  row.appendChild(cell3); 
			  row.appendChild(cell4); 
			  row.appendChild(cell5); 
			  row.appendChild(cell6); 
			  row.appendChild(cell7); 
			  row.appendChild(cell8); 
			  row.appendChild(cell9); 
			  row.appendChild(cell10); 
			  row.appendChild(cell11); 
			  row.appendChild(cell12); 
			  row.appendChild(cell13); 
			  row.appendChild(cell14); 
			  
			
			  tbody.appendChild(row); 
			  fillData(budRowNo);
			  budRowNo++;  
			}
			
			function fillData(budRowNo)
			{
				if(document.getElementById('cmbClassOfExp'+budRowNo)!=null)
				  {
				    classCombo = document.getElementById('cmbClassOfExp'+budRowNo);
				    var classComboLength = classCombo.options.length;
				    for(i=0;i<classArr.length;i++)
				    {
				      classCombo.options[classComboLength++]=new Option(classArr[i],classArr[i]);
				    }
				  }

				if(document.getElementById('cmbFund'+budRowNo)!=null)
				  {
				    fundCombo = document.getElementById('cmbFund'+budRowNo);
				    var fundComboLength = fundCombo.options.length;
				    for(i=0;i<fundArr.length;i++)
				    {
				      fundCombo.options[fundComboLength++]=new Option(fundArr[i],fundArr[i]);
				    }
				  }

				if(document.getElementById('cmbBudgetType'+budRowNo)!=null)
				  {
				    budCombo = document.getElementById('cmbBudgetType'+budRowNo);
				    var budComboLength = budCombo.options.length;
				    for(i=0;i<budArr.length;i++)
				    {
				      budCombo.options[budComboLength++]=new Option(budArr[i],budArr[i]);
				    }
				  }				
				  
				  if(document.getElementById('cmbDemandNo'+budRowNo)!=null)
				  {
				    demandCombo = document.getElementById('cmbDemandNo'+budRowNo);
				    var demandComboLength = demandCombo.options.length;
				    for(i=0;i<demandArr.length;i++)
				    {
				      demandCombo.options[demandComboLength++]=new Option(demandArr[i],demandArr[i]);
				    }
				  }		  				  
			}
			
			
			function deleteBudgetRow()
			{
			    
			    var j=0;
			    var count=0;
			    var flag=0;
			    var budRowNoTmp=budRowNo;
			   
			    for( i=1;i<=budRowNoTmp;i++,j++)
			    {        
			       var temp = eval('document.forms[0].chkDtlPost'+j);	
			       var budId = eval('document.forms[0].LcBudId'+j);	       
			       if(temp==null || temp==' ')
			            count++;
			       if(temp)
			       {
			         
			           if(temp.checked && budId.value==0)
			           {
			               var expAmt = eval('document.forms[0].txtExpAmt'+j);
			               document.forms[0].txtExpTotal.value=eval(document.forms[0].txtExpTotal.value)-eval(expAmt.value);
			               var openLc=eval('document.forms[0].txtOpeningLcBal.value');
					       document.forms[0].txtLcBalance.value=eval(openLc)-eval('document.forms[0].txtExpTotal.value');
					       document.forms[0].txtNetTotal.value=eval('document.forms[0].txtExpTotal.value')-eval('document.forms[0].txtDedTotal.value');
			             
			               DetailPostingTbl.deleteRow(i-count);
			               budRowNoTmp=budRowNoTmp-1;
			               i=i-1;  
			           }
			           else if(temp.checked && budId.value != 0)
			           {
			           		flag=1;
			           }
			       }
			    }
			    if(flag==1)
			      alert('Please click on Submit button to delete Remaining Rows.');	
			}
			
			function clearBudRow()
			{
				var j=0;
			    var count=0;
			    var budRowNoTmp=budRowNo;
			   
			    for( i=1;i<=budRowNoTmp;i++,j++)
			    {        
			       var temp = eval('document.forms[0].chkDtlPost'+j);	
			       var budId = eval('document.forms[0].LcBudId'+j);	       
			       if(temp==null || temp==' ')
			            count++;
			       if(temp)
			       {
			         
			           if(temp.checked && budId.value==0)
			           {
			               var expAmt = eval('document.forms[0].txtExpAmt'+j);
			               document.forms[0].txtExpTotal.value=eval(document.forms[0].txtExpTotal.value)-eval(expAmt.value);
			               var openLc=eval('document.forms[0].txtOpeningLcBal.value');
					       document.forms[0].txtLcBalance.value=eval(openLc)-eval('document.forms[0].txtExpTotal.value');
					       document.forms[0].txtNetTotal.value=eval('document.forms[0].txtExpTotal.value')-eval('document.forms[0].txtDedTotal.value');
			             
			               var classOfExp=eval('document.forms[0].cmbClassOfExp'+j);
			               var fund=eval('document.forms[0].cmbFund'+j);
			               var drwOff=eval('document.forms[0].txtDrwOff'+j);
			               var demandNo=eval('document.forms[0].cmbDemandNo'+j);
			               var budgetType=eval('document.forms[0].cmbBudgetType'+j);
			               var schemeNo=eval('document.forms[0].txtSchemeNo'+j);
			               var mjrHd=eval('document.forms[0].cmbMjrHd'+j);
			               var subMjrHd=eval('document.forms[0].cmbSubMjrHd'+j);
			               var minHd=eval('document.forms[0].cmbMinHd'+j);
			               var subHd=eval('document.forms[0].cmbSubHd'+j);
			               var dtlHd=eval('document.forms[0].txtDtlHd'+j);
			               var objHd=eval('document.forms[0].txtObjHd'+j);
			               var expAmt=eval('document.forms[0].txtExpAmt'+j);
			               
			               classOfExp.value= -1;
			               fund.value= -1;
			               classOfExp.value= -1;
			               drwOff.value="";
			               demandNo.value= -1;
			               budgetType.value= -1;
			               schemeNo.value="";
			               mjrHd.value= -1;
			               subMjrHd.value= -1;
			               minHd.value= -1;
			               subHd.value= -1;
			               dtlHd.value= 00;
			               objHd.value=0;
			               expAmt.value=0;
			               
			               temp.checked=false;
			           }
			      }
			    }
			}
			
			function closeWindow()
			{
				
				var contextPath = '<%=request.getContextPath()%>';			
				var newURL = contextPath +'/ifms.htm?viewName=homePage&theme=ifmsblue' ;
		 	    document.forms[0].action=newURL;
		 		if(confirm('Do You Want to Close This Window ?'))
		 		{
		 		  self.close();
		 		} 	
			}
			
			 function validScheme(obj)
			 {
			 	var schemNo = obj.value;
			 	if(schemNo!="")
			 	{
				 	if(schemNo.length != 7)
				 	{
				 		alert("Plesae Enter 7 Digit Scheme No.");
				 		obj.focus();
				 		obj.select();
				 		obj.value = "";
				 		return false;
				 	}
				 	return true;
				}
			 }
			
			function chkDateFormat(dtObj)
			{
				var dtVal = dtObj.value;
				if(dtVal != '')
					{
						
						if(isValidDtChars(dtObj))
						{			
								if(eval(dtVal.length) != 10)
								{
									alert('Please Enter Date In Given Format');
									dtObj.focus();
									dtObj.select();
									return false;
								}
								if(dtVal.indexOf('/')!=2)
								{
									
									alert('Please Enter Date In Given Format');
									dtObj.focus();
									dtObj.select();
									return false;
								}
								var restDt=dtVal.substr(3,10);
							
								if(restDt.indexOf('/')!=2)
								{
									
									alert('Please Enter Date In Given Format');
									dtObj.focus();
									dtObj.select();
									return false;
								}
								return true;
						
						}
						else
						{
							alert('Please Enter Date In Given Format');
							dtObj.focus();
							dtObj.select();
							return false;
						}
					}
					else
					{
						alert('Please Enter Date In Given Format');
						dtObj.focus();
						dtObj.select();
						return false;
					}
		}
		
		function isValidDtChars(obj)
		{
			
			var iChars = "0123456789/";   
			var fieldVal = obj.value;
			
			for (var i = 0; i < fieldVal.length; i++) 
			{
			  if (iChars.indexOf(fieldVal.charAt(i)) == -1) 
			  { 
				obj.focus();  	   
				obj.select();
			    return false;
			  }
			  
			}   
				return true;
			
		}
			
			
			function getMajorHd(obj)
			{
			
				var mjrLstCntr=0;
				var cntr=0;
				var demandVal = obj.value;
				var dmdCmbName = obj.name;
				var cmbNo;
			
				
				if(demandVal != -1)
				{

					for (mjrLstCntr=0;mjrLstCntr<demandArr.length;mjrLstCntr++)
					{
						if(demandArr[mjrLstCntr]==demandVal)
						 {
						  
						  break;
						 }
					}
				
					//mjrHdArr
					var mjrHdArr1 = new Array();
					mjrHdArr1 = mjrHdArr[mjrLstCntr];
					
					cmbNo=dmdCmbName.substring(11,13);
					var mjrCmbName = 'cmbMjrHd'+cmbNo;
					var mjrCmb = document.getElementById(mjrCmbName);
					var mjrOptLen = mjrCmb.options.length;
					
						for(cntr=mjrOptLen ;cntr>=0;cntr--)
						{
							mjrCmb.remove(cntr);
						}	
						mjrOptLen=0;
					
					mjrCmb.options[mjrOptLen]= new Option("--Select--",-1);
					for(cntr=0;cntr<mjrHdArr1.length;cntr++)
					{
						mjrOptLen++;
						mjrCmb.options[mjrOptLen]= new Option(mjrHdArr1[cntr],mjrHdArr1[cntr]);
					}
				}
				else
				{
							
					cmbNo=dmdCmbName.substring(11,13);
					var mjrCmbName = 'cmbMjrHd'+cmbNo;
					var mjrCmb = document.getElementById(mjrCmbName);
					var mjrOptLen = mjrCmb.options.length;

					for(cntr=mjrOptLen ;cntr>=0;cntr--)
					{
						mjrCmb.remove(cntr);
					}
					mjrOptLen=0;
					mjrCmb.options[mjrOptLen]= new Option("--Select--",-1);
					
				}
			}
		
			
			
			function getSbMjrHd(obj)
			{
				var mjrHdNm = obj.name;
				var mjrHdVal = obj.value;
				var cmbNo = mjrHdNm.substring(8,10);
				var arrSb = new Array();
				var dmdCmbNm = 'cmbDemandNo'+cmbNo;
				var cntr=0;
				var dmdVal = document.getElementById(dmdCmbNm).value;
				var key = dmdVal+mjrHdVal;
				var cmbSbNm = 'cmbSubMjrHd'+cmbNo;
				var cmbSb = document.getElementById(cmbSbNm);
				var cmbLen = cmbSb.options.length;
				
				if(dmdVal !=-1 && mjrHdVal != -1)
				{
					
					for(cntr=0;cntr<keyArr.length;cntr++)
					{
						if(keyArr[cntr]==key)
						{
							
							break;
						}
					}
					arrSb=subMjrHdArr[cntr];
					
						for(cntr=cmbLen-1 ;cntr>=1;cntr--)
						{
							cmbSb.remove(cntr);
						}	
					cmbLen=0;
					cmbSb.options[cmbLen]= new Option("--Select--",-1);
					for(cntr=0;cntr<arrSb.length;cntr++)
					{
						cmbLen++;
						cmbSb.options[cmbLen]= new Option(arrSb[cntr],arrSb[cntr]);
					}
					
				}
				else
				{
					for(cntr=cmbLen ;cntr>=0;cntr--)
					{
						cmbSb.remove(cntr);
					}
					cmbLen=0;
					cmbSb.options[cmbLen]= new Option("--Select--",-1);
				}
			}
			
			function getSubHd(obj)
			{	
		
				var minHdNm = obj.name;
				var minHdVal = obj.value;
				var cmbNo = minHdNm.substring(8,10);
				var arrSb = new Array();
			
				var dmdCmbNm = 'cmbDemandNo'+cmbNo;
				var mjrCmbNm = 'cmbMjrHd'+cmbNo;
				var sbMjrHd= 'cmbSubMjrHd'+cmbNo;
				var cntr1=0;
				var cntr=0;
				var dmdVal = document.getElementById(dmdCmbNm).value;
				mjrHdVal=document.getElementById(mjrCmbNm).value;
				var sbMjrHdVal = document.getElementById(sbMjrHd).value;
				var key = dmdVal+mjrHdVal+sbMjrHdVal+minHdVal;
				var cmbSbNm = 'cmbSubHd'+cmbNo;
				var cmbSb = document.getElementById(cmbSbNm);
				var cmbLen = cmbSb.options.length;
				
				if(dmdVal !=-1 && mjrHdVal != -1 && sbMjrHdVal!=-1 && minHdVal!=-1 )
				{
					
					for(cntr1=0; cntr1< sbKeyArr.length ; cntr1++)
					{
						if(sbKeyArr[cntr1]==key)
						{
							
							break;
						}
					}
					
					arrSb=sbHdArr[cntr1];
					
					
						for(cntr1=cmbLen-1 ;cntr1>=1;cntr1--)
						{
							cmbSb.remove(cntr1);
						}	
					cmbLen=0;
					cmbSb.options[cmbLen]= new Option("--Select--",-1);
				
					for(cntr1=0;cntr1<arrSb.length;cntr1++)
					{
						cmbLen++;
						cmbSb.options[cmbLen]= new Option(arrSb[cntr1],arrSb[cntr1]);
					}
					
				}
				else
				{
					
					for(cntr=cmbLen ;cntr>=0;cntr--)
					{
						cmbSb.remove(cntr);
					}
					cmbLen=0;
					cmbSb.options[cmbLen]= new Option("--Select--",-1);
				}
	
			
			}
			
			function getMinHd(obj)
			{
				var sbmjrHdNm = obj.name;
				var sbmjrHdVal = obj.value;
				var cmbNo = sbmjrHdNm.substring(11,13);
				var arrSb = new Array();
				
				var dmdCmbNm = 'cmbDemandNo'+cmbNo;
				var mjrCmbNm = 'cmbMjrHd'+cmbNo;
				var cntr=0;
				var dmdVal = document.getElementById(dmdCmbNm).value;
				mjrHdVal=document.getElementById(mjrCmbNm).value;
				var key = dmdVal+mjrHdVal+sbmjrHdVal;
				var cmbSbNm = 'cmbMinHd'+cmbNo;
				var cmbSb = document.getElementById(cmbSbNm);
				var cmbLen = cmbSb.options.length;
				
				if(dmdVal !=-1 && mjrHdVal != -1 && sbmjrHdVal !=-1 )
				{
					for(cntr=0;cntr<minKeyArr.length;cntr++)
					if(minKeyArr[cntr]==key)
					{
						break;
					}
					arrSb=minHdArr[cntr];
					
					
						for(cntr=cmbLen-1 ;cntr>=1;cntr--)
						{
							cmbSb.remove(cntr);
						}	
					cmbLen=0;
					cmbSb.options[cmbLen]= new Option("--Select--",-1);
					for(cntr=0;cntr<arrSb.length;cntr++)
					{
						cmbLen++;
						cmbSb.options[cmbLen]= new Option(arrSb[cntr],arrSb[cntr]);
					}
					
				}
				else
				{
					for(cntr=cmbLen ;cntr>=0;cntr--)
					{
						cmbSb.remove(cntr);
					}
					cmbLen=0;
					cmbSb.options[cmbLen]= new Option("--Select--",-1);
				}
				
			}
			
	//---------------- AJAX CODE FOR CHECK CHEQUE VALIDITY---START--------------------------------
			
	function createXMLHttpRequest() 
	{ 
		var ua; 
			
		if(window.XMLHttpRequest) 
		{ 
			ua = new XMLHttpRequest(); 
		} 
		else if(window.ActiveXObject) 
		{ 
			ua = new ActiveXObject("Microsoft.XMLHTTP"); 
		} 

		return ua; 
	} 
    
 	function sendRequest(obj) 
	{ 
		if(isChqNoRepeated(obj))
		{
			req = createXMLHttpRequest();
			if(req != null)
			{
				var baseUrl = "${pageContext.request.contextPath}/ifms.htm?actionFlag=validateChqNo";
				baseUrl += "&txtDivisionCode=" + document.getElementById("txtDivisionShrtNm").value;		
				baseUrl += "&txtChqValidate=" + obj.value;
				
				document.forms[0].txtChqValidate.value=obj.name;
				
				req.open("post", baseUrl, false); 
				req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
				req.onreadystatechange = handleResponse; 
				req.send(baseUrl);
			}
			else
			{
				alert ("Your browser does not support AJAX!");
			} 
		}	
	} 
    
    function isChqNoRepeated(obj)
    {
    	//alert(obj.value);   
    	var cntr=0; 	
    	for(i=0; i<chkRowNo; i++)
    	{
    		// alert('1');
    		var oldChqNo=eval('document.getElementById("chkNo"+i)');	
    	  //   alert(oldChqNo);
    		if(oldChqNo != null && oldChqNo.value != '')
    		{
	    		if(oldChqNo.value == obj.value)
	    		{
	    		  cntr++;
	    		}
	    	}	
    	}
    	// alert('Cntr'+cntr);
    	if(cntr > 1)
    	{
    	  alert('Cheque No Already Exist !!');
    	  obj.select();
    	  return false;
    	}
    	return true;
    }
    
	function handleResponse() 
	{ 
		if(req.readyState == 4)
		{ 
			if(req.status == 200)
			{	
			
				var XMLDoc = req.responseXML.documentElement;
				var XMLEntries = XMLDoc.getElementsByTagName('chqVerify');	
				
				var chqNoVerifyResult=XMLEntries[0].text;
				//alert("---"+chqNoVerifyResult);
				var resName=document.forms[0].txtChqValidate.value;
				var resObj=document.getElementById(resName);
				
				if(chqNoVerifyResult=='InvalidSeries')
				{
				   alert("Cheque No. does not belong to issued Cheque Series for given Division");
				   resObj.value="";
				   resObj.focus();
				}  
				if(chqNoVerifyResult=='AlreadyIssued')
				{
				   alert("Cheque No. Already Issued for given Division");
				    resObj.value="";
				   resObj.focus();
				}   
				if(chqNoVerifyResult=='invalidDivCode')
				{
				   alert("Please Enter Division Code ");
				    resObj.value="";
				   document.forms[0].txtDivisionCode.focus();
				}
				
			}						
		}			
	}
	//---------------- AJAX CODE FOR CHECK CHEQUE VALIDITY ----END-----------------------------------
			
		--></script>	
		
		<style >
		.tabstyle {
			border-width: 5px 1px 1px 1px; 
			border-color: #2065c0;
			border-style: solid ;
			}
		
	     legend {
			padding-left:5px;
			padding-right:5px;
			font-weight:normal; 
			font-family:verdana;
				
			border-width: 0px 0px 1px 0px; 
			border-color: #2065c0;
			border-style: solid ;
			}
		
		</style>
			
	</head>
	
	
<body>
<hdiits:form name="frmAdviceReceived" validate="true" method="post"  >

<fieldset  style = "width:965px" class="tabstyle">
	<legend  id="headingMsg"><fmt:message key="LC.LCHEAD" bundle="${lcexpLabels}"></fmt:message></legend>
	
<table width="100%" align="center" class="TableBorderLTRBN" border="0" cellspacing="0" cellpadding="1">
	
	<hdiits:tr><hdiits:td><br></hdiits:td></hdiits:tr>
    <tr>
    	<td>&nbsp;&nbsp;&nbsp;<fmt:message key="LC.ADVICENO" bundle="${lcexpLabels}"></fmt:message>    	
	    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;
	    <input readonly type="text" maxlength="6" id="txtAdviceNo" name="txtAdviceNo" value="<%=lcDtlPostingVo.getAdviceNo()%>"  class="TextCss"  size="15" onblur="isNumeric(this)" /> *
    	</td>
        
        <td align="right">
   			&nbsp;&nbsp;&nbsp;<fmt:message key="LC.ADVICEDT" bundle="${lcexpLabels}"></fmt:message>   			
   			
			<% 
		     String dateTime = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
			 request.setAttribute("dateTime",dateTime);
											
			%>								
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;
			<% 
			String lStrAdvDt = lcDtlPostingVo.getAdviceDt().toString();			
			StringTokenizer strToken =null;
			
			strToken=new StringTokenizer(lStrAdvDt,"-");
			String yyyy=strToken.nextToken();
			String mm=strToken.nextToken();
			String dd=strToken.nextToken();
			lStrAdvDt=dd+"/"+mm+"/"+yyyy; 
			%>
			<input readonly type="text" name="txtAdviceDate" id="txtAdviceDate" value="<%=lStrAdvDt%>"  maxlength="10"  class="TextCss"  size="12"  /><img src=images/CalendarImages/ico-calendar.gif width=20 onClick=window_open("txtAdviceDate",375,570) >
			* &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;		
		</td>
	</tr>
	<tr>
    	<td>&nbsp;&nbsp;&nbsp;<fmt:message key="LC.DIVISION_CODE" bundle="${lcexpLabels}"></fmt:message>    	
	    &nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;
	    <input readonly type="text" maxlength="7" name="txtDivisionShrtNm" id="txtDivisionShrtNm" value="<%=divInformationVO.getLc_order_id()%>"  class="TextCss"  size="15"  /> *
	    <input type="hidden" maxlength="7" name="txtDivisionCode" id="txtDivisionCode" value="<%=divInformationVO.getDivisionId()%>"  class="TextCss"  size="15" onblur="isAlphaNumeric(this)" />
    	<input type="hidden" name="txtDivisionId" id="txtDivisionId" value="<%=lcDtlPostingVo.getDivisionCode()%>"  class="TextCss"  size="15"  />
    	<input type="hidden" name="txtLcOrderId" id="txtLcOrderId" class="TextCss"  size="15" />
    	<input type="hidden" name="totalChqRow" id="totalChqRow" class="TextCss"  size="15"  />
    	<input type="hidden" name="totalBudRow" id="totalBudRow" class="TextCss"  size="15"  />
    	<input type="hidden" name="dedTotalRow" id="dedTotalRow" value="10" class="TextCss"  size="15"  />
    	<input type="hidden" name="txtLcExpId" id="txtLcExpId" value="<%=lcDtlPostingVo.getLcExpId()%>" class="TextCss"  size="15"  />
    	<input type="hidden" name="txtAdvApproved" id="txtAdvApproved" value="<%=lcDtlPostingVo.getAdviceApproved()%>" class="TextCss"  size="15"  />
    	<input type=hidden name="hidDistCode" id="hidDistCode" value="<%=divInformationVO.getDistrictCode() %>">
		<input type=hidden name="hidDeptCode" id="hidDeptCode" value="<%=divInformationVO.getDepartmentCode() %>">
		<input type="hidden" name="txtChqValidate" id="txtChqValidate"  class="TextCss"  size="1"  />
    	</td>
    	<td align="right">&nbsp;&nbsp;&nbsp;<fmt:message key="LC.DIVISION_NM" bundle="${lcexpLabels}"></fmt:message>    	
	    &nbsp;:&nbsp;&nbsp;
	    <input readonly type="text" name="txtDivName" id="txtDivName" value="<%=divInformationVO.getDivision_name() %> "  class="TextCss"  size="35"  /> *
    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	</td>
	
	</tr>
    <tr>
    	<td>&nbsp;&nbsp;&nbsp;<fmt:message key="LC.DEPT" bundle="${lcexpLabels}"></fmt:message>    	
	    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;
	    
	    <input readonly type="text" name="txtDeptName" id="txtDeptName" value="<%=divInformationVO.getDepartment_name()%>"  class="TextCss"  size="25"  /> *
    	</td>
    	<td align="right">&nbsp;&nbsp;&nbsp;<fmt:message key="LC.DISTRICT" bundle="${lcexpLabels}"></fmt:message>    	
	    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;
	    <input readonly  type="text" name="txtDistName" id="txtDistName" value="<%=divInformationVO.getDistrict_name()%>"  class="TextCss"  size="15"  /> *
    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	</td>
	
	</tr> 
	<tr>
        
        <td align="left">
   			&nbsp;&nbsp;&nbsp;<fmt:message key="LC.VALID_FROM" bundle="${lcexpLabels}"></fmt:message>
   			&nbsp;
			<%
			dateTime = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
			 request.setAttribute("dateTime",dateTime);
											
			%>								
			&nbsp;:&nbsp;&nbsp;
			<% 
			 String lStrLcValidFrmDt = lcDtlPostingVo.getLcValidFrm().toString();
			
			 strToken=new StringTokenizer(lStrLcValidFrmDt,"-");
			 yyyy=strToken.nextToken();
			 mm=strToken.nextToken();
			 dd=strToken.nextToken();
			 lStrLcValidFrmDt=dd+"/"+mm+"/"+yyyy; 
			%>
			<input readonly type="text" name="txtLcValidFrom" id="txtLcValidFrom" value="<%=lStrLcValidFrmDt%>"  maxlength="10"  class="TextCss"  size="12"  /><img src=images/CalendarImages/ico-calendar.gif width=20 onClick=window_open("txtLcValidFrom",375,570) >
		    * 
		</td>
	</tr> 
	<tr align="center">
    	<td colspan="2"><fmt:message key="LC.OPENING_BAL" bundle="${lcexpLabels}"></fmt:message>	   
	    &nbsp;:&nbsp;&nbsp;
	    <input readonly type="text" maxlength="16" style="text-align:right" name="txtOpeningLcBal" id="txtOpeningLcBal" value="<%=lcDtlPostingVo.getOpeningLc()%>" class="TextCss"  size="15"  /> *
    	</td>
    	
    <hdiits:tr><hdiits:td ><br></hdiits:td></hdiits:tr>	
	
	</tr>
    <tr align="left">
		<td colspan="6">
				<hdiits:button type="button" name="btnAddRow" value="Add Row" style="width:80px"  onclick="addChqRow()" ></hdiits:button>
				<hdiits:button type="button" name="btnDeleteRow" value="Delete Row" style="width:80px" onclick="deleteChkRow()"></hdiits:button>
		</td>
	</tr>
	
	<table width="100%" align="center" id="ChequePostingTbl" class="TableBorderLTRBN" border="1" cellspacing="0" cellpadding="1">
			  <tbody>
				<tr class="TableHeaderBG">
					<td align="center" width="2%"  >
						<input disabled type="checkbox" >
					</td>
					<td align="center" width="15%" >
						<fmt:message key="LC.CHQ_DT" bundle="${lcexpLabels}"></fmt:message>
						(DD/MM/YYYY)						
					</td>
					<td align="center" width="20%" >
						<fmt:message key="LC.CHQ_NO" bundle="${lcexpLabels}"></fmt:message>
					</td>
					<td align="center" width="20%" >
						<fmt:message key="LC.CHQ_AMT" bundle="${lcexpLabels}"></fmt:message>
					</td>
					<td align="center" width="43%" >
						<fmt:message key="LC.PARTY_NAME" bundle="${lcexpLabels}"></fmt:message>
					</td>					
				 </tr>					
		 	    
					 <%
				        for(int i=0;i<lArrChqPosting.size();i++)
				        {
				        	lcChqPostingVo=(TrnLcChequePosting)lArrChqPosting.get(i);
				        	String lStrchkCancelDt ="";				        	
				        	if(lcChqPostingVo.getChqCancelDt() != null)
				        		lStrchkCancelDt=lcChqPostingVo.getChqCancelDt().toString();
				        	System.out.println("---------CANCEL DATE in JSP--------------"+lStrchkCancelDt);
				        	if(lStrchkCancelDt.equals(""))
				        	{
				      %>
				         <tr>
				            <td width="2%"><input type="checkbox" name="chk<%=i%>"  class="TextCss"  value="<%=i%>" /> 
				            <td align="left">				   			
					   			
								<%
							     dateTime = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
								 request.setAttribute("dateTime",dateTime);
																
								%>								
								
								<% 
									 String lStrchkDtDt = lcChqPostingVo.getChequeDt().toString();
									
									 strToken=new StringTokenizer(lStrchkDtDt,"-");
									 yyyy=strToken.nextToken();
									 mm=strToken.nextToken();
									 dd=strToken.nextToken();
									 lStrchkDtDt=dd+"/"+mm+"/"+yyyy; 
								%>
								<input type="text" maxlength=\"10\" name="chkDt<%=i%>" value="<%=lStrchkDtDt%>" maxlength="10"  class="TextCss"  size="12" onblur=chkDateFormat(this) /><img src=images/CalendarImages/ico-calendar.gif width=20 onClick=window_open("chkDt<%=i%>",375,570) >
							</td>
				            <td width="20%" >
				            <input type="text"  maxlength="8" id="chkNo<%=i%>" name="chkNo<%=i%>" value="<%=lcChqPostingVo.getChequeNo()%>" class="TextCss"  size="25" onblur="isNumeric(this);sendRequest(this)" />
				            <td width="20%">
				            <input type="text" maxlength="19" id="chkAmt<%=i%>" name="chkAmt<%=i%>" value="<%=lcChqPostingVo.getChequeAmt()%>" class="TextCss" style="text-align:right" size="25" onblur="addChqAmt(this)" />
				            <td width="43%">
				            <input type="text" maxlength="62" id="chkPartyNm<%=i%>" name="chkPartyNm<%=i%>" value="<%=lcChqPostingVo.getPartyName()%>" class="TextCss" size="60" onblur="isAlphaNumeric(this)" />				            
				         </tr>
				         <input type="hidden"  id="LcChqId<%=i%>" name="LcChqId<%=i%>" value="<%=lcChqPostingVo.getLcChqId()%>" class="TextCss" size="15"  />
				         <input type="hidden"  id="LcChqCancelDt<%=i%>" name="LcChqCancelDt<%=i%>" value="<%=lcChqPostingVo.getChqCancelDt()%>" class="TextCss" size="15"  />
				      <%
				       
				        	}
				        	else
				        	{
				       %>
						         <tr>
						            <td disabled width="2%" ><input type="checkbox" name="chk<%=i%>"  class="TextCss"  value="<%=i%>" /> 
						            <td align="left" >				   			
							   			
										<%
									     dateTime = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
										 request.setAttribute("dateTime",dateTime);
																		
										%>								
										
										<% 
											 String lStrchkDtDt = lcChqPostingVo.getChequeDt().toString();
											
											 strToken=new StringTokenizer(lStrchkDtDt,"-");
											 yyyy=strToken.nextToken();
											 mm=strToken.nextToken();
											 dd=strToken.nextToken();
											 lStrchkDtDt=dd+"/"+mm+"/"+yyyy; 
										%>
										<input disabled type="text" maxlength=\"10\" name="chkDt<%=i%>" value="<%=lStrchkDtDt%>" maxlength="10"  class="TextCss"  size="12" onblur=chkDateFormat(this) /><img src=images/CalendarImages/ico-calendar.gif width=20 onClick=window_open("chkDt<%=i%>",375,570) >
									</td>
						            <td width="20%">
						            <input disabled  type="text"  maxlength="8" id="chkNo<%=i%>" name="chkNo<%=i%>" value="<%=lcChqPostingVo.getChequeNo()%>" class="TextCss"  size="25" onblur="isNumeric(this)" />
						            <td width="20%">
						            <input disabled type="text" maxlength="19" id="chkAmt<%=i%>" name="chkAmt<%=i%>" value="<%=lcChqPostingVo.getChequeAmt()%>" class="TextCss" style="text-align:right" size="25" onblur="addChqAmt(this)" />
						            <td width="43%">
						            <input disabled type="text" maxlength="50" id="chkPartyNm<%=i%>" name="chkPartyNm<%=i%>" value="<%=lcChqPostingVo.getPartyName()%>" class="TextCss" size="60" onblur="isAlphaNumeric(this)" />				            
						         </tr>
						         <input type="hidden"  id="LcChqId<%=i%>" name="LcChqId<%=i%>" value="<%=lcChqPostingVo.getLcChqId()%>" class="TextCss" size="15"  />
						         <input type="hidden"  id="LcChqCancelDt<%=i%>" name="LcChqCancelDt<%=i%>" value="<%=lcChqPostingVo.getChqCancelDt()%>" class="TextCss" size="15"  />
						      <%
						       	
				        	}
				        }
				      %>
					
			  </tbody>				
				<tr >					
					<td align="left" >						
					</td>					
					<td align="left" >						
					</td>
					<td align="right" >
						Total :
					</td>
					<td align="left" >
						
						<input readonly type="text" id="txtChqTotal" value="0" name="txtChqTotal" style="text-align:right" class="TextCss"  size="25"  />
					</td>
				</tr>		    		    
			    		
				
			<hdiits:tr><hdiits:td></hdiits:td></hdiits:tr>			
      </table>
      <table width="100%" align="center" class="TableBorderLTRBN" border="0" cellspacing="0" cellpadding="1">
        <tr align="left">
				   <td colspan="6">
						<hdiits:button type="button" name="btnClrChq" value="Clear" style="width:80px" onclick="clearChqRow()"></hdiits:button>
				   </td>
		</tr> 	
        <tr>
	    	<td>&nbsp;&nbsp;&nbsp;<fmt:message key="LC.LC_BALANCE" bundle="${lcexpLabels}"></fmt:message>
		    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;
		    <input readonly type="text" value="<%=lcDtlPostingVo.getClosingLc()%>" id="txtLcBalance" style="text-align:right" name="txtLcBalance" value="0" class="TextCss"  size="15"  />
	    	</td>	    	
	    	<td align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    	<fmt:message key="LC.DRW_OFF" bundle="${lcexpLabels}"></fmt:message>	    	
		    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;
		    <input type="text" maxlength="50" id="txtDrwOff" name="txtDrwOff" value="<%=lcDtlPostingVo.getDrwOff()%>" class="TextCss"  size="15" onblur="isAlphaNumeric(this)" /> *
    	     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	    </td>	
	   </tr> 
	   <tr>
	    	<td>&nbsp;&nbsp;&nbsp;<fmt:message key="LC.DIV_OFF" bundle="${lcexpLabels}"></fmt:message>	    	
		    &nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;
		    <input type="text" name="txtDivOff" maxlength="50" id="txtDivOff" value="<%=lcDtlPostingVo.getDivOff()%>" class="TextCss"  size="15" onblur="isAlphaNumeric(this)" /> *
	    	</td >
	    	<td align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    	<fmt:message key="LC.TSRY_OFF" bundle="${lcexpLabels}"></fmt:message>	    	
		    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;
		    <input type="text" id="txtTsryOff" maxlength="50" name="txtTsryOff" value="<%=lcDtlPostingVo.getTsryOff()%>" class="TextCss"  size="15" onblur="isAlphaNumeric(this)" /> *
    	    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	    </td>	
	   </tr> 
	   <tr>
	   <td></td>
	   <td align="right">
		   <fmt:message key="LC.BANK" bundle="${lcexpLabels}"></fmt:message>		  
		   	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;
		   	<select name="id_Bank" id="id_Bank"  style="width:125px">
				<option value="-1">--Select--</option>
				<%
				   if(lcConstants.getString("LC.SBI.CODE").equals(lcDtlPostingVo.getBankCode().toString()))
				   {
				%>
				<option selected value='<%=lcConstants.getString("LC.SBI.CODE")%>' > <fmt:message key="LC.SBI.NAME" bundle="${lcexpLabels}"> </fmt:message></option>
				<option value='<%=lcConstants.getString("LC.SBS.CODE")%>' > <fmt:message key="LC.SBS.NAME" bundle="${lcexpLabels}"> </fmt:message></option>
				<option value='<%=lcConstants.getString("LC.BOB.CODE")%>' > <fmt:message key="LC.BOB.NAME" bundle="${lcexpLabels}"> </fmt:message></option>
			    <%
				   }
				   else if(lcConstants.getString("LC.SBS.CODE").equals(lcDtlPostingVo.getBankCode().toString()))
				   {
			    %>
			    <option value='<%=lcConstants.getString("LC.SBI.CODE")%>' > <fmt:message key="LC.SBI.NAME" bundle="${lcexpLabels}"> </fmt:message></option>
				<option selected value='<%=lcConstants.getString("LC.SBS.CODE")%>' > <fmt:message key="LC.SBS.NAME" bundle="${lcexpLabels}"> </fmt:message></option>
				<option value='<%=lcConstants.getString("LC.BOB.CODE")%>' > <fmt:message key="LC.BOB.NAME" bundle="${lcexpLabels}"> </fmt:message></option>
			   <%
				   }
				   else if(lcConstants.getString("LC.BOB.CODE").equals(lcDtlPostingVo.getBankCode().toString()))
				   {
			    %>
			    <option value='<%=lcConstants.getString("LC.SBI.CODE")%>' > <fmt:message key="LC.SBI.NAME" bundle="${lcexpLabels}"> </fmt:message></option>
				<option  value='<%=lcConstants.getString("LC.SBS.CODE")%>' > <fmt:message key="LC.SBS.NAME" bundle="${lcexpLabels}"> </fmt:message></option>
				<option selected value='<%=lcConstants.getString("LC.BOB.CODE")%>' > <fmt:message key="LC.BOB.NAME" bundle="${lcexpLabels}"> </fmt:message></option>
			   <%
				   }	
			   %>
			</select> *
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	   </td>
	   </tr>
	   <hdiits:tr><hdiits:td><br></hdiits:td></hdiits:tr>	
	   <tr class="TableHeaderBG">
		<td align="center" colspan="6">
			<fmt:message key="LC.DTL_POSTING" bundle="${lcexpLabels}"></fmt:message>
		</td>
	   </tr>
	   <hdiits:tr><hdiits:td><br></hdiits:td></hdiits:tr>
	   
	   <tr>
	   <td align="left">
		   &nbsp;&nbsp;&nbsp;<fmt:message key="LC.MONTH" bundle="${lcexpLabels}"></fmt:message>		   
		   	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;
		   	<select name="id_month" id="id_month"  style="width:125px">
		   	<option value="-1">--Select--</option>
		    <%
		       for(int i=0;i<lArrMonths.size();i++)
		       {
		    	   comboVo=(ComboValuesVO)lArrMonths.get(i);
		    	   if((lcDtlPostingVo.getMonthCode().toString()).equalsIgnoreCase(comboVo.getId()))
		    	   {
		    %>
		    	        <option selected value="<%=comboVo.getId()%>"><%=comboVo.getDesc()%></option>
		    <%   
		    	   }
		    	   else
		    	   {
		    %>	
		    	       <option  value="<%=comboVo.getId()%>"><%=comboVo.getDesc()%></option>    
		    <%	   }
		    	}
		    %>	
		    	
		    <select> *
			
	   </td>
	   </tr>  
       <hdiits:tr><hdiits:td><br></hdiits:td></hdiits:tr>	     
       	<tr align="left">
		<td colspan="6">
				<hdiits:button type="button" name="btnAddRowDtl" value="Add Row" style="width:80px"  onclick="addBudgetRow()" ></hdiits:button>
				<hdiits:button type="button" name="btnDeleteRowDtl" value="Delete Row" style="width:80px" onclick="deleteBudgetRow()"></hdiits:button>
		</td>
	</tr>		
     </table>
	 
	 <table width="100%" align="center" id="DetailPostingTbl" class="TableBorderLTRBN" border="1" cellspacing="0" cellpadding="1">
			  <tbody>
				<tr class="TableHeaderBG" rowspan="2">
					<td align="center" >
						<input disabled type="checkbox" >
					</td>
					<td align="center" >
						<fmt:message key="LC.CLASS_OF_EXP" bundle="${lcexpLabels}"></fmt:message>
					</td>
					<td align="center" >
						<fmt:message key="LC.FUND" bundle="${lcexpLabels}"></fmt:message>
					</td>
					<td align="center" >
						<fmt:message key="LC.DRW_OFF" bundle="${lcexpLabels}"></fmt:message>
					</td>
					<td align="center" >
						<fmt:message key="LC.DEMAND_NO" bundle="${lcexpLabels}"></fmt:message>
					</td>
					<td align="center" >
						<fmt:message key="LC.BUD_TYPE" bundle="${lcexpLabels}"></fmt:message>
					</td>
					<td align="center" >
						<fmt:message key="LC.SCHEME_NO" bundle="${lcexpLabels}"></fmt:message>
					</td>
					<td align="center" >
						<fmt:message key="LC.MJR_HD" bundle="${lcexpLabels}"></fmt:message>
					</td>
					<td align="center" >
						<fmt:message key="LC.SUB_MJR_HD" bundle="${lcexpLabels}"></fmt:message>
					</td>
					<td align="center" >
						<fmt:message key="LC.MINOR_HD" bundle="${lcexpLabels}"></fmt:message>
					</td>
					<td align="center" >
						<fmt:message key="LC.SUB_HD" bundle="${lcexpLabels}"></fmt:message>
					</td>
					<td align="center" >
						<fmt:message key="LC.DTL_HD" bundle="${lcexpLabels}"></fmt:message>
					</td>
					<td align="center" >
						<fmt:message key="LC.OBJ_HD" bundle="${lcexpLabels}"></fmt:message>
					</td>
					<td align="center" >
						<fmt:message key="LC.EXP_AMT" bundle="${lcexpLabels}"></fmt:message>
					</td>					
				 </tr>				
		 	    
					 <%
					 System.out.println("====================before table generation===============");
						TrnLcBudgetPosting lcBudPostingVo= null;
				        for(int i=0;i<lArrBudPosting.size();i++)
				        {
				        	System.out.println("====================in for loop changed ==================");
				        	 lcBudPostingVo= (TrnLcBudgetPosting)lArrBudPosting.get(i);
				        	 System.out.println("+===================1=================");
				        	 
				        	 String lStrClass = lcBudPostingVo.getClassOfExp().toString();
				        	 String lStrFund = lcBudPostingVo.getFund().toString();
				        	 String lStrDrwOff = lcBudPostingVo.getDrwOff();
				        	 String lStrDemandNo = lcBudPostingVo.getDemandNo();
				        	 String lStrBudTyp = lcBudPostingVo.getBudgetType().toString();
				        	 String lStrScheme = lcBudPostingVo.getSchemeNo().toString();
				        	 String lStrMjrHd  = lcBudPostingVo.getMjrHd().toString();
				        	 String lStrSbMjrHd= lcBudPostingVo.getSubMjrHd().toString();
				        	 String lStrMinHd = lcBudPostingVo.getMinHd().toString();
				        	 String lStrSbHd  = lcBudPostingVo.getSubHd().toString();
				        	 String lStrDtlHd = lcBudPostingVo.getDtlHd().toString();
				        	 String lStrObjHd = lcBudPostingVo.getObjHd().toString();
				        	 String lStrExp = lcBudPostingVo.getExpAmt().toString();
				        	
					      	 System.out.println("===================== lStrClass :: "+lStrClass);
				        	 System.out.println("===================== lStrFund :: "+lStrFund);
				        	 System.out.println("===================== lStrDrwOff :: "+lStrDrwOff);
				        	 System.out.println("===================== lStrDemand :: "+lStrDemandNo);
				        	 System.out.println("===================== lStrBudTyp :: "+lStrBudTyp);
				        	 System.out.println("===================== lStrScheme :: "+lStrScheme);
				        	 System.out.println("===================== lStrMjrHd :: "+lStrMjrHd);
				        	 System.out.println("===================== lStrSbMjrHd :: "+lStrSbMjrHd);
				        	 System.out.println("===================== lStrMinHd :: "+lStrMinHd);
				        	 System.out.println("===================== lStrSbHd :: "+lStrSbHd);
				        	 System.out.println("===================== lStrDtlHd :: "+lStrDtlHd);
				        	 System.out.println("===================== lStrObjHd :: "+lStrObjHd);
				        	 System.out.println("===================== lStrExp :: "+lStrExp);
				        	 System.out.println("===================== LCBuDId :: "+lcBudPostingVo.getLcBudId());
				    		%>
				         <tr>
				            <td><input type="checkbox" name="chkDtlPost<%=i%>"  class="TextCss"  size="2" value="<%=i%>" />				            
				            <td><!--<input type="text"  name="txtClassOfExp id="txtClassOfExp" class="TextCss"  size="2"  onblur="isNumeric(this)"/> -->
				            <select name="cmbClassOfExp<%=i%>" id="cmbClassOfExp<%=i%>"  style="width:62px">
							<option value="-1">--Select--</option>
				                    
				               	<%if(lcConstants.getString("LC.CLASS1").equals(lStrClass))
				               	{
				               	%>
				               		<option selected value='<%=lcConstants.getString("LC.CLASS1")%>'><fmt:message key="LC.CLASS1" bundle="${lcconstants}"/></option>	
									<option value='<%=lcConstants.getString("LC.CLASS2")%>'><fmt:message key="LC.CLASS2" bundle="${lcconstants}"/></option>
				               	<% 
				               	}
				               	else
				               	{
				               	%>
									<option value='<%=lcConstants.getString("LC.CLASS1")%>'><fmt:message key="LC.CLASS1" bundle="${lcconstants}"/></option>	
									<option selected  value='<%=lcConstants.getString("LC.CLASS2")%>'><fmt:message key="LC.CLASS2" bundle="${lcconstants}"/></option>
				               		
				               	<%
				               	}
				               	%>
				               
		
			                </select>
			                
				            <td><!--<input type="text"  name="txtFund" class="TextCss"  size="2" onblur="isNumeric(this)" /> -->
				            <select name="cmbFund<%=i%>" id="cmbFund<%=i%>"  style="width:50px">
				  			
				            <option value="-1">--Select--</option>
				            <%
				            	if(lcConstants.getString("LC.FUND3").equals(lStrFund))
				            	{
				            %>
				               <option selected value='<%=lcConstants.getString("LC.FUND3")%>' > <fmt:message key="LC.FUND3" bundle="${lcconstants}"/></option>	
				               <option value='<%=lcConstants.getString("LC.FUND4")%>' > <fmt:message key="LC.FUND4" bundle="${lcconstants}"/></option>	
				               <option value='<%=lcConstants.getString("LC.FUND5")%>' > <fmt:message key="LC.FUND5" bundle="${lcconstants}"/></option>	
				               	
				             <%
				            	}
				            	else if(lcConstants.getString("LC.FUND4").equals(lStrFund))
				            	{
				             %>	
				               <option  value='<%=lcConstants.getString("LC.FUND3")%>' > <fmt:message key="LC.FUND3" bundle="${lcconstants}"/></option>	
				               <option  selected value='<%=lcConstants.getString("LC.FUND4")%>' > <fmt:message key="LC.FUND4" bundle="${lcconstants}"/></option>	
				               <option value='<%=lcConstants.getString("LC.FUND5")%>' > <fmt:message key="LC.FUND5" bundle="${lcconstants}"/></option>	
				               	
				             <%
				            	}
				            	else
				            	{
				            		
				             %>		
				                 <option  value='<%=lcConstants.getString("LC.FUND3")%>' > <fmt:message key="LC.FUND3" bundle="${lcconstants}"/></option>	
				               <option  value='<%=lcConstants.getString("LC.FUND4")%>' > <fmt:message key="LC.FUND4" bundle="${lcconstants}"/></option>	
				               <option selected value='<%=lcConstants.getString("LC.FUND5")%>' > <fmt:message key="LC.FUND5" bundle="${lcconstants}"/></option>	
				               	           							
			                <%
				            	}
			                %>
			                </select>
			                
				            <td><input type="text"  name="txtDrwOff<%=i%>" id="txtDrwOff<%=i%>" value="<%=lStrDrwOff%>" class="TextCss" size="7"  onblur="isAlphaNumeric(this)"/>
				            
				            <td><!--<input type="text"  name="txtDemandNo" id="txtDemandNo" class="TextCss" size="2" onblur="isNumeric(this)" /> --> 
				            <select name="cmbDemandNo<%=i%>" id="cmbDemandNo<%=i%>" onchange="getMajorHd(this)" style="width:60px">
				               <option value="-1">--Select--</option>
				               <%
				              
				               for(int li=0;li<lArrDemList.size();li++)
					          	 {
					            	   
				            	     String lStrDemand =(String) lArrDemList.get(li);
										if(lStrDemand.equals(lStrDemandNo))
										{
					            	   %>
										<option selected value='<%=lStrDemand%>' ><%=lStrDemand%></option>	
					            	   <%
										}
										else
										{
									   %>
										<option  value='<%=lStrDemand%>' ><%=lStrDemand%></option>		
								<%
										}
								}
					            %>
				              
			                </select>
				            <td><!--<input type="text" id="txtBudgetType"  name="txtBudgetType" class="TextCss" size="2" onblur="isNumeric(this)" /> --> 
				            <select name="cmbBudgetType<%=i%>" id="cmbBudgetType<%=i%>"  style="width:60px">
				               <option value="-1">--Select--</option>
				               <%
				               if(lStrBudTyp.equals(lcConstants.getString("LC.BUD1")))
				               {
				               %>
				               <option selected value='<%=lcConstants.getString("LC.BUD1")%>' > <%=lcConstants.getString("LC.BUD1")%></option>	
				               <option value='<%=lcConstants.getString("LC.BUD2")%>' > <%=lcConstants.getString("LC.BUD2")%></option>	
				               <option value='<%=lcConstants.getString("LC.BUD3")%>' > <%=lcConstants.getString("LC.BUD3")%></option>	
				               <option value='<%=lcConstants.getString("LC.BUD4")%>' > <%=lcConstants.getString("LC.BUD4")%></option>	
				               <option value='<%=lcConstants.getString("LC.BUD5")%>' > <%=lcConstants.getString("LC.BUD5")%></option>	
				               <option value='<%=lcConstants.getString("LC.BUD6")%>' > <%=lcConstants.getString("LC.BUD6")%></option>								
			                	<%
				               }
				               else if(lStrBudTyp.equals(lcConstants.getString("LC.BUD2")))
				               {
		                
				             %>
			                   <option  value='<%=lcConstants.getString("LC.BUD1")%>' > <%=lcConstants.getString("LC.BUD1")%></option>	
				               <option selected value='<%=lcConstants.getString("LC.BUD2")%>' > <%=lcConstants.getString("LC.BUD2")%></option>	
				               <option value='<%=lcConstants.getString("LC.BUD3")%>' > <%=lcConstants.getString("LC.BUD3")%></option>	
				               <option value='<%=lcConstants.getString("LC.BUD4")%>' > <%=lcConstants.getString("LC.BUD4")%></option>	
				               <option value='<%=lcConstants.getString("LC.BUD5")%>' > <%=lcConstants.getString("LC.BUD5")%></option>	
				               <option value='<%=lcConstants.getString("LC.BUD6")%>' > <%=lcConstants.getString("LC.BUD6")%></option>	
			                <%
			                }
				               else if(lStrBudTyp.equals(lcConstants.getString("LC.BUD3")))
				               {
		                
				             %>
			                   <option  value='<%=lcConstants.getString("LC.BUD1")%>' > <%=lcConstants.getString("LC.BUD1")%></option>	
				               <option  value='<%=lcConstants.getString("LC.BUD2")%>' > <%=lcConstants.getString("LC.BUD2")%></option>	
				               <option selected value='<%=lcConstants.getString("LC.BUD3")%>' > <%=lcConstants.getString("LC.BUD3")%></option>	
				               <option value='<%=lcConstants.getString("LC.BUD4")%>' > <%=lcConstants.getString("LC.BUD4")%></option>	
				               <option value='<%=lcConstants.getString("LC.BUD5")%>' > <%=lcConstants.getString("LC.BUD5")%></option>	
				               <option value='<%=lcConstants.getString("LC.BUD6")%>' > <%=lcConstants.getString("LC.BUD6")%></option>	
			                <%
			                }
				               
				               else if(lStrBudTyp.equals(lcConstants.getString("LC.BUD4")))
				               {
		                
				             %>
			                   <option  value='<%=lcConstants.getString("LC.BUD1")%>' > <%=lcConstants.getString("LC.BUD1")%></option>	
				               <option  value='<%=lcConstants.getString("LC.BUD2")%>' > <%=lcConstants.getString("LC.BUD2")%></option>	
				               <option value='<%=lcConstants.getString("LC.BUD3")%>' > <%=lcConstants.getString("LC.BUD3")%></option>	
				               <option selected value='<%=lcConstants.getString("LC.BUD4")%>' > <%=lcConstants.getString("LC.BUD4")%></option>	
				               <option value='<%=lcConstants.getString("LC.BUD5")%>' > <%=lcConstants.getString("LC.BUD5")%></option>	
				               <option value='<%=lcConstants.getString("LC.BUD6")%>' > <%=lcConstants.getString("LC.BUD6")%></option>	
			                <%
			                }
				               else if(lStrBudTyp.equals(lcConstants.getString("LC.BUD5")))
				               {
		                
				             %>
			                   <option  value='<%=lcConstants.getString("LC.BUD1")%>' > <%=lcConstants.getString("LC.BUD1")%></option>	
				               <option  value='<%=lcConstants.getString("LC.BUD2")%>' > <%=lcConstants.getString("LC.BUD2")%></option>	
				               <option value='<%=lcConstants.getString("LC.BUD3")%>' > <%=lcConstants.getString("LC.BUD3")%></option>	
				               <option value='<%=lcConstants.getString("LC.BUD4")%>' > <%=lcConstants.getString("LC.BUD4")%></option>	
				               <option selected value='<%=lcConstants.getString("LC.BUD5")%>' > <%=lcConstants.getString("LC.BUD5")%></option>	
				               <option value='<%=lcConstants.getString("LC.BUD6")%>' > <%=lcConstants.getString("LC.BUD6")%></option>	
			                <%
			                }
				               else 
				               {
		                
				             %>
			                   <option  value='<%=lcConstants.getString("LC.BUD1")%>' > <%=lcConstants.getString("LC.BUD1")%></option>	
				               <option  value='<%=lcConstants.getString("LC.BUD2")%>' > <%=lcConstants.getString("LC.BUD2")%></option>	
				               <option value='<%=lcConstants.getString("LC.BUD3")%>' > <%=lcConstants.getString("LC.BUD3")%></option>	
				               <option value='<%=lcConstants.getString("LC.BUD4")%>' > <%=lcConstants.getString("LC.BUD4")%></option>	
				               <option value='<%=lcConstants.getString("LC.BUD5")%>' > <%=lcConstants.getString("LC.BUD5")%></option>	
				               <option selected value='<%=lcConstants.getString("LC.BUD6")%>' > <%=lcConstants.getString("LC.BUD6")%></option>	
			                <%
			                }
			                %>
			                </select>
				            <td>
				            <input type="text"  name="txtSchemeNo<%=i%>" value="<%=lStrScheme %>" id="txtSchemeNo<%=i%>" maxlength="7" class="TextCss" size="4" onblur="isNumeric(this);validScheme(this)" />  
				           
				            <td><!--<input type="text"  name="txtMjrHd class="TextCss" size="3"  onblur="isNumeric(this);isValidLength(this,4)"/>  -->
				            <select name="cmbMjrHd<%=i%>" onchange="getSbMjrHd(this)"  id="cmbMjrHd<%=i%>"  style="width:60px">
				               <option value="-1">--Select--</option>	
				               <option selected value="<%=lStrMjrHd%>" ><%=lStrMjrHd%></option>	
				               
				              				
			                </select>
				            <td><!--<input type="text"  name="txtSubMjrHd class="TextCss" size="2" onblur="isNumeric(this);isValidLength(this,2)" /> -->
				            <select name="cmbSubMjrHd<%=i%>" id="cmbSubMjrHd<%=i%>"  onchange="getMinHd(this)" style="width:80px">
				               <option value="-1">--Select--</option>	
				                <option selected value="<%=lStrSbMjrHd%>" ><%=lStrSbMjrHd%></option>							
			                </select>
				            <td><!--<input type="text"  name="txtMinHd class="TextCss" size="2"  onblur="isNumeric(this);isValidLength(this,3)"/>   -->
				            <select name="cmbMinHd<%=i%>" id="cmbMinHd<%=i%>"  onchange="getSubHd(this)" style="width:60px">
				               <option value="-1">--Select--</option>		
				                <option selected value="<%=lStrMinHd%>" ><%=lStrMinHd%></option>						
			                </select>
				            <td><!--<input type="text"  name="txtSubHd class="TextCss" size="2"  onblur="isNumeric(this);isValidLength(this,2)"/>  -->
				            <select name="cmbSubHd<%=i%>" id="cmbSubHd<%=i%>"  style="width:60px" >
				               <option value="-1">--Select--</option>		
				                <option selected value="<%=lStrSbHd%>" ><%=lStrSbHd%></option>						
			                </select>
				            <td><input type="text"  name="txtDtlHd<%=i%>" value="<%=lStrDtlHd%>" id="txtDtlHd<%=i%>" class="TextCss" size="2"  onblur="isNumeric(this)"/>
				            <td><input type="text"  name="txtObjHd<%=i%>" value="<%=lStrObjHd%>" id="txtObjHd<%=i%>" class="TextCss" size="2" onblur="isNumeric(this)" />
				            <td><input type="text" maxlength="19" name="txtExpAmt<%=i%>" value="<%=lStrExp%>" id="txtExpAmt<%=i%>" class="TextCss" style="text-align:right" size="11" onblur="addExpAmt(this)"/>
				            <td><input type="hidden"  id="LcBudId<%=i%>" name="LcBudId<%=i%>" value="<%=lcBudPostingVo.getLcBudId()%>" class="TextCss" size="15"  />
				         </tr>
				      <%
				        }
				      %>
					
					
			  
			  </tbody>				
				<tr >					
					<td align="left" >						
					</td>
					<td align="left" >						
					</td>
					<td align="left" >						
					</td>
					<td align="left" >						
					</td>
					<td align="left" >						
					</td>
					<td align="left" >						
					</td>
					<td align="left" >						
					</td>
					<td align="left" >						
					</td>
					<td align="left" >						
					</td>
					<td align="left" >						
					</td>
					<td align="left" >						
					</td>
					<td align="left" >						
					</td>
					<td align="right" >
						Total :
					</td>
					<td align="left" >
						<input readonly type="text" name="txtExpTotal" value="0" style="text-align:right" class="TextCss"  size="11"  />
					</td>
				</tr>		    		    
			   </table>
		<table width="100%" align="center" class="TableBorderLTRBN" border="0" cellspacing="0" cellpadding="1">
        <tr align="left">
				   <td colspan="6">
						<hdiits:button type="button" name="btnClrDtlPost" value="Clear" style="width:80px" onclick="clearBudRow()"></hdiits:button>
				   </td>
		</tr> 	
	    <hdiits:tr><hdiits:td><br></hdiits:td></hdiits:tr>	
	    <tr class="TableHeaderBG">
		<td align="center" colspan="6">
			<fmt:message key="LC.DEDUCTION" bundle="${lcexpLabels}"></fmt:message>
		</td>
	   </tr>	
	   <hdiits:tr><hdiits:td><br></hdiits:td></hdiits:tr>		
       </table>
       <table width="100%" align="center" id="ReceiptJottingTbl" class="TableBorderLTRBN" border="1" cellspacing="0" cellpadding="1">
       <tbody>
       <script>
         <%
         	String lStrDedAmt[]=new String[10];
            String lStrDedId[]=new String[10];
            for(int i=0;i<lArrDedPosting.size();i++)
         	{
         		lcDedPostingVo=(TrnLcDeductionPosting)lArrDedPosting.get(i);
         		lStrDedAmt[i]=lcDedPostingVo.getAmount().toString();
         		lStrDedId[i]=lcDedPostingVo.getLcDedId().toString();
         		System.out.println("--------dedArr----------"+lStrDedAmt[i]);
         		System.out.println("--------dedArr ID----------"+lStrDedId[i]);
         	}
         %>
       
	       var tbody = document.getElementById('ReceiptJottingTbl').getElementsByTagName('tbody')[0]; 
			  var row1 = document.createElement('TR'); 

				row1.className='TableHeaderBG';
			

				

					 var cellName = 'cell1';
					 cellName = document.createElement('TD');
					
					 cellName.innerHTML = '<%=lcStringsBundle.getString("LC.DEDUCTION1")%>';
					 row1.appendChild(cellName); 
					 
					 	 var cellName = 'cell2';
					 cellName = document.createElement('TD');
					
					 cellName.innerHTML = '<%=lcStringsBundle.getString("LC.DEDUCTION2")%>';
					 row1.appendChild(cellName); 
					 
					 	 var cellName = 'cell3';
					 cellName = document.createElement('TD');
					
					 cellName.innerHTML = '<%=lcStringsBundle.getString("LC.DEDUCTION3")%>';
					 row1.appendChild(cellName); 
					 
					 	 var cellName = 'cell4';
					 cellName = document.createElement('TD');
					
					 cellName.innerHTML = '<%=lcStringsBundle.getString("LC.DEDUCTION4")%>';
					 row1.appendChild(cellName); 
					 
					 	 var cellName = 'cell5';
					 cellName = document.createElement('TD');
					
					 cellName.innerHTML = '<%=lcStringsBundle.getString("LC.DEDUCTION5")%>';
					 row1.appendChild(cellName); 
					 
					 	 var cellName = 'cell6';
					 cellName = document.createElement('TD');
					
					 cellName.innerHTML = '<%=lcStringsBundle.getString("LC.DEDUCTION6")%>';
					 row1.appendChild(cellName); 
					 
					 	 var cellName = 'cell7';
					 cellName = document.createElement('TD');
					
					 cellName.innerHTML = '<%=lcStringsBundle.getString("LC.DEDUCTION7")%>';
					 row1.appendChild(cellName); 
					 
					 	 var cellName = 'cell8';
					 cellName = document.createElement('TD');
					
					 cellName.innerHTML = '<%=lcStringsBundle.getString("LC.DEDUCTION8")%>';
					 row1.appendChild(cellName); 
					 
					 	 var cellName = 'cell9';
					 cellName = document.createElement('TD');
					
					 cellName.innerHTML = '<%=lcStringsBundle.getString("LC.DEDUCTION9")%>';
					 row1.appendChild(cellName); 
					 
					 	 var cellName = 'cell10';
					 cellName = document.createElement('TD');
					
					 cellName.innerHTML = '<%=lcStringsBundle.getString("LC.DEDUCTION10")%>';
					 row1.appendChild(cellName); 
					 

				var totalHeader = document.createElement('TD');
				totalHeader.innerHTML = "Total";
				row1.appendChild(totalHeader);
				tbody.appendChild(row1); 
				
				
				var row2 = document.createElement('TR');
					
					 var cellName = 'cell1';
					 var hidName = 'txtDedCode1';
					 cellName = document.createElement('TD');
					 var txtNm = 'txtDedAmt1';
					 
					 var hdValue = '<%=lcStringsBundle.getString("LC.DEDUCTIONCODE1")%>';
					 cellName.innerHTML = '<input type="text" value="<%=lStrDedAmt[0]%>" class="TextCss" style="text-align:right" size="8" name="'+txtNm+'" onblur="addDeduction(this)" >';
					 cellName.innerHTML += '<input type="hidden" name="'+hidName+'" value="'+hdValue+'">';
					 cellName.innerHTML += '<input type="hidden" name="txtDedId1" value="<%=lStrDedId[0]%>" >';
					 row2.appendChild(cellName); 
					 
					  cellName = 'cell2';
					  hidName = 'txtDedCode2';
					 cellName = document.createElement('TD');
					 txtNm = 'txtDedAmt2';
					
					 hdValue = '<%=lcStringsBundle.getString("LC.DEDUCTIONCODE2")%>';
					 cellName.innerHTML = '<input type="text" value="<%=lStrDedAmt[1]%>" class="TextCss" size="8" style="text-align:right" name="'+txtNm+'" onblur="addDeduction(this)" >';
					 cellName.innerHTML += '<input type="hidden" name="'+hidName+'" value="'+hdValue+'">';
					 cellName.innerHTML += '<input type="hidden" name="txtDedId2" value="<%=lStrDedId[1]%>" >';
					 row2.appendChild(cellName);
					 
					 cellName = 'cell3';
					 hidName = 'txtDedCode3';
					 cellName = document.createElement('TD');
					 txtNm = 'txtDedAmt3';
					
					 hdValue = '<%=lcStringsBundle.getString("LC.DEDUCTIONCODE3")%>';
					 cellName.innerHTML = '<input type="text" value="<%=lStrDedAmt[2]%>" class="TextCss" size="8" style="text-align:right" name="'+txtNm+'" onblur="addDeduction(this)" >';
					 cellName.innerHTML += '<input type="hidden" name="'+hidName+'" value="'+hdValue+'">';
					 cellName.innerHTML += '<input type="hidden" name="txtDedId3" value="<%=lStrDedId[2]%>" >';
					 row2.appendChild(cellName);
					 
					 cellName = 'cell4';
					 hidName = 'txtDedCode4';
					 cellName = document.createElement('TD');
					 var txtNm = 'txtDedAmt4';
					
					 var hdValue = '<%=lcStringsBundle.getString("LC.DEDUCTIONCODE4")%>';
					 cellName.innerHTML = '<input type="text" value="<%=lStrDedAmt[3]%>" class="TextCss" size="8" style="text-align:right" name="'+txtNm+'" onblur="addDeduction(this)" >';
					 cellName.innerHTML += '<input type="hidden" name="'+hidName+'" value="'+hdValue+'">';
					 cellName.innerHTML += '<input type="hidden" name="txtDedId4" value="<%=lStrDedId[3]%>" >';
					 row2.appendChild(cellName);
					 
					 cellName = 'cell5';
					 hidName = 'txtDedCode5';
					 cellName = document.createElement('TD');
					 var txtNm = 'txtDedAmt5';
					
					 var hdValue = '<%=lcStringsBundle.getString("LC.DEDUCTIONCODE5")%>';
					 cellName.innerHTML = '<input type="text" value="<%=lStrDedAmt[4]%>" class="TextCss" size="8" style="text-align:right" name="'+txtNm+'" onblur="addDeduction(this)" >';
					 cellName.innerHTML += '<input type="hidden" name="'+hidName+'" value="'+hdValue+'">';
					 cellName.innerHTML += '<input type="hidden" name="txtDedId5" value="<%=lStrDedId[4]%>" >';
					 row2.appendChild(cellName);
					 
					  cellName = 'cell6';
					  hidName = 'txtDedCode6';
					 cellName = document.createElement('TD');
					 txtNm = 'txtDedAmt6';
					
					 hdValue = '<%=lcStringsBundle.getString("LC.DEDUCTIONCODE6")%>';
					 cellName.innerHTML = '<input type="text" value="<%=lStrDedAmt[5]%>" class="TextCss" size="8" style="text-align:right" name="'+txtNm+'" onblur="addDeduction(this)" >';
					 cellName.innerHTML += '<input type="hidden" name="'+hidName+'" value="'+hdValue+'">';
					 cellName.innerHTML += '<input type="hidden" name="txtDedId6" value="<%=lStrDedId[5]%>">';
					 row2.appendChild(cellName);
					 
					 cellName = 'cell7';
					 hidName = 'txtDedCode7';
					 cellName = document.createElement('TD');
					 txtNm = 'txtDedAmt7';
					
					 hdValue = '<%=lcStringsBundle.getString("LC.DEDUCTIONCODE7")%>';
					 cellName.innerHTML = '<input type="text" value="<%=lStrDedAmt[6]%>" class="TextCss" size="8" style="text-align:right" name="'+txtNm+'" onblur="addDeduction(this)" >';
					 cellName.innerHTML += '<input type="hidden" name="'+hidName+'" value="'+hdValue+'">';
					 cellName.innerHTML += '<input type="hidden" name="txtDedId7" value="<%=lStrDedId[6]%>" >';
					 row2.appendChild(cellName);
					 
					 cellName = 'cell8';
					 hidName = 'txtDedCode8';
					 cellName = document.createElement('TD');
					 txtNm = 'txtDedAmt8';
					
					 hdValue = '<%=lcStringsBundle.getString("LC.DEDUCTIONCODE8")%>';
					 cellName.innerHTML = '<input type="text" value="<%=lStrDedAmt[7]%>" class="TextCss" size="8" style="text-align:right" name="'+txtNm+'" onblur="addDeduction(this)" >';
					 cellName.innerHTML += '<input type="hidden" name="'+hidName+'" value="'+hdValue+'">';
					 cellName.innerHTML += '<input type="hidden" name="txtDedId8" value="<%=lStrDedId[7]%>" >';
					 row2.appendChild(cellName);
					 
					 cellName = 'cell9';
					 hidName = 'txtDedCode9';
					 cellName = document.createElement('TD');
					 txtNm = 'txtDedAmt9';
					 
					 hdValue = '<%=lcStringsBundle.getString("LC.DEDUCTIONCODE9")%>';
					 cellName.innerHTML = '<input type="text" value="<%=lStrDedAmt[8]%>" class="TextCss" size="8" style="text-align:right" name="'+txtNm+'" onblur="addDeduction(this)" >';
					 cellName.innerHTML += '<input type="hidden" name="'+hidName+'" value="'+hdValue+'">';
					 cellName.innerHTML += '<input type="hidden" name="txtDedId9" value="<%=lStrDedId[8]%>" >';
					 row2.appendChild(cellName);
					 
					 cellName = 'cell10';
					 hidName = 'txtDedCode10';
					 cellName = document.createElement('TD');
					 txtNm = 'txtDedAmt10';
					
					 hdValue = '<%=lcStringsBundle.getString("LC.DEDUCTIONCODE10")%>';
					 cellName.innerHTML = '<input type="text" value="<%=lStrDedAmt[9]%>" class="TextCss" size="8" style="text-align:right" name="'+txtNm+'" onblur="addDeduction(this)" >';
					 cellName.innerHTML += '<input type="hidden" name="'+hidName+'" value="'+hdValue+'">';
					 cellName.innerHTML += '<input type="hidden" name="txtDedId10" value="<%=lStrDedId[9]%>" >';
					 row2.appendChild(cellName);
				
			
				var totalText = document.createElement('TD');
				var hdArrSize = document.createElement('TD');
				hdArrSize.innerHTML = '<input type="hidden" name="dedTotalRow" value="10">';
				totalText.innerHTML = '<input readonly type="text" class="TextCss" size="8" style="text-align:right" value="0" name="txtDedTotal" >';
				row2.appendChild(totalText);
				row2.appendChild(hdArrSize);
				tbody.appendChild(row2); 
				
			

       </script>
       </tbody>
       </table>
	   
	    <table width="100%" align="center" class="TableBorderLTRBN" border="0" cellspacing="0" cellpadding="1">
        <tr align="right">
		<td>
		   <fmt:message key="LC.NET_TOTAL" bundle="${lcexpLabels}"></fmt:message>		  
		   	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;
		   	<input readonly type="text" name="txtNetTotal" style="text-align:right" value="0" class="TextCss"  size="8"  /> *
			
	   </td>
		</tr> 	
	    <hdiits:tr><hdiits:td><br></hdiits:td></hdiits:tr>
	    <hdiits:tr><hdiits:td><br></hdiits:td></hdiits:tr>	
	   <tr align="center">
		  <td colspan="6">
				<hdiits:button type="button" name="btnSubmit" value="Submit" style="width:80px" onclick="saveDtlPosting()"></hdiits:button>
				<hdiits:button type="button" name="btnClose" value="Close" style="width:80px" onclick="closeWindow()"></hdiits:button>
		  </td>
	   </tr>	 	
       </table>
	</table>  
	</fieldset>
</hdiits:form>

</body>
<script>
		var expChqTotal=0;
		var j=0;
		 <%
				        double chqTotal=0;
		 				for(int i=0;i<lArrChqPosting.size();i++)
				        {
				        	lcChqPostingVo=(TrnLcChequePosting)lArrChqPosting.get(i);
				        	String lStrchkCancelDt ="";				        	
				        	if(lcChqPostingVo.getChqCancelDt() != null)
				        		lStrchkCancelDt=lcChqPostingVo.getChqCancelDt().toString();
				        	System.out.println("---------CANCEL DATE JSP--------------"+lStrchkCancelDt);
				        	if(lStrchkCancelDt.equals(""))
				        	{
          						chqTotal += Double.parseDouble(lcChqPostingVo.getChequeAmt().toString());
				        	}
				        }
		 %>
		 
		 document.forms[0].txtChqTotal.value=eval('<%=chqTotal%>');
		 
		var expBudTotal=0;
		for(i=0;i<budRowNo;i++)
		{
			var expFld = document.getElementById('txtExpAmt'+i);			
			 expBudTotal += eval(expFld.value);
		}
		document.forms[0].txtExpTotal.value=eval(expBudTotal);
		var expDedTotal=0;		
		for(i=1;i<=dedRowNo;i++)
		{
			var expFld = document.getElementById('txtDedAmt'+i);
			 expDedTotal += eval(expFld.value);
		}		
		document.forms[0].txtDedTotal.value=eval(expDedTotal);
		
		document.forms[0].txtNetTotal.value=eval(expBudTotal)-eval(expDedTotal);		
		
</script>

<ajax:updateField baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getDivInformation" 
 	action="txtDivisionCode"  source="txtDivisionCode" 
 	target="txtDivisionId,txtLcOrderId,txtLcValidFrom,txtOpeningLcBal,txtDivName,txtDeptName,txtDistName" 
  	parameters="txtDivisionCode={txtDivisionCode}" eventType="blur" >
</ajax:updateField>







</html>
	