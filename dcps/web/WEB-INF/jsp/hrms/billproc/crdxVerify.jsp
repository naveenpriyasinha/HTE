<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setBundle basename="resources.billproc.billproc_en_US"
	var="billprocLabels" scope="application" />
<fmt:setBundle basename="resources.billproc.BillprocConstants"
	var="billprocConst" scope="application" />

<%@page import="com.tcs.sgv.core.valueobject.ResultObject"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.tcs.sgv.core.valueobject.FrmServiceMst"%>
<%@page import="com.tcs.sgv.common.valueobject.TrnBillRegister"%>

<script type="text/javascript"
	src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/validation.js"></script>
<script type="text/javascript" src="script/billproc/validation.js"></script>

<%@page import="com.tcs.sgv.acl.service.UserElements"%>
<html>
<head>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<%
	ResultObject result = (ResultObject) request.getAttribute("result");
	Map resultMap = (Map) result.getResultValue();
	UserElements userActions = (UserElements) resultMap
			.get("homePageUserElements");
	long permission = 0;
	int apprvFlag = 0;
	// permission is used for checking whether button is to be displayed or not.
	// permission = 1 is for showing and permission =2 is for hidden

	if (userActions != null) {
		if (userActions.getUserElements(120027) != null) {
			if (userActions.getUserElements(120027)
			.getPermissionObject() != null) {
		permission = userActions.getUserElements(120027)
				.getPermissionObject().getPermission();
			}
		}
		if (userActions.getUserElements(121060) != null) {
			apprvFlag = 1;
		}
	}

%>
<script type="text/javascript" src="script/billproc/cmnValidation.js"></script>
<script type="text/javascript" src="script/billproc/billMvmntTracking.js"></script>

<script type="text/javascript">
		
		
		
		

var fwdPage='-1';           // globel var for fowrward page to multiple user


/*     function showBill(url)
			{
			//window.open(url,"_blank","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,top=40,left=40,width=950,height=650"); 				
				window.open(url);
			}
*/			 function chking()
			 {
			 alert("Select Any Check Box");
			 return false;
			 }
			 
			 function  forwardData()
			 {
				var indx=0;
				var flag = 0;
		    	for(i=0;i < document.rm_accvousfrom.elements.length;i++)
				{
					if(document.rm_accvousfrom.elements[i].type=="checkbox" && document.rm_accvousfrom.elements[i].name != 'chkSelect')
					{
						if(document.rm_accvousfrom.elements[i].checked)
						{
							BillNo=document.rm_accvousfrom.elements[i].value; 
							arr= new Array();
							arr=BillNo.split("~");		
							verifyYN = eval('document.rm_accvousfrom.CmbCdxVerify_'+arr[0]);
							if(verifyYN.value=='<fmt:message  key="STATUS.CardexNotVerified" bundle="${billprocConst}" />')
							{
								flag = 1;								
							}
							indx++;
						} 
					}
				}
	    	
		    	if(indx==0)
		    	{
	    			alert("Select atleast one checkbox to Forward");
	    		 	return false;
	    		}
				if(flag ==1)
				{
					if(!confirm('You have not Verified all selected Bills.Do you still want to proceed?'))
					{
						return false;
					}
				}
			 	document.rm_accvousfrom.parentUrl1.value='';
			 	document.rm_accvousfrom.action="ifms.htm?actionFlag=updateAcceptBills&statusFlag="+'<%=request.getParameter("statusFlag")%>'+"&updStatusFlag="+'<%=request.getParameter("updStatusFlag")%>';
				document.rm_accvousfrom.submit();	
			 }
			 
			 function recFlg()
			 {
				var indx=0;
		    	for(i=0;i < document.rm_accvousfrom.elements.length;i++)
				{
					if(document.rm_accvousfrom.elements[i].type=="checkbox" && document.rm_accvousfrom.elements[i].name != 'chkSelect')
					{
						if(document.rm_accvousfrom.elements[i].checked)
						{
							indx++;
						} 
					}
				}
	    	
		    	if(indx==0)
		    	{
	    			alert("Select atleast one checkbox to Receive");
	    		 	return false;
	    		}
			 	
			 	
			 	document.rm_accvousfrom.parentUrl1.value='';
				document.rm_accvousfrom.action="ifms.htm?actionFlag=updateAcceptBills&recFlag=0&statusFlag="+'<%=request.getParameter("statusFlag")%>'+"&updStatusFlag="+'<%=request.getParameter("updStatusFlag")%>';
				document.rm_accvousfrom.submit();			 
			 }
			 
			
			
			function displ()
			{
				document.rm_accvousfrom.parentUrl1.value='';
				var updStatusFlag = '<%=request.getParameter("updStatusFlag")%>';
				if(fwdPage == 'Approve')
				{
					//updStatusFlag = 'BAPPRV_AUD&actionVal=APPROVE';
					updStatusFlag = updStatusFlag + '&actionVal=APPROVE';
				}
				if(fwdPage == 'Reject')
				{
				//	updStatusFlag = 'BRJCT_AUD&actionVal=REJECT';
					updStatusFlag = updStatusFlag + '&actionVal=REJECT';
				}
				document.rm_accvousfrom.returnCase.value="";
				document.rm_accvousfrom.action="ifms.htm?actionFlag=updateAcceptBills&recFlag="+'<%=request.getParameter("recFlag")%>'+"&statusFlag="+'<%=request.getParameter("statusFlag")%>'+"&updStatusFlag="+updStatusFlag;
				document.rm_accvousfrom.submit();	
			}
			
			function returnBill()
			{
				var indx=0;
				for(i=0;i < document.rm_accvousfrom.elements.length;i++)
				{
					if(document.rm_accvousfrom.elements[i].type=="checkbox" && document.rm_accvousfrom.elements[i].name != 'chkSelect')
					{
						if(document.rm_accvousfrom.elements[i].checked)
						{
							indx++;
						} 
					}
				}
	    	
		    	if(indx==0)
		    	{
	    			alert("Select atleast one checkbox");
	    		 	return false;
	    		}
				document.rm_accvousfrom.parentUrl1.value = "";
				document.rm_accvousfrom.toPost.value="-1";
				document.rm_accvousfrom.toUser.value="";
				document.rm_accvousfrom.toLevel.value="";
				document.rm_accvousfrom.returnCase.value="1";
				document.rm_accvousfrom.action='ifms.htm?actionFlag=updateAcceptBills&recFlag=1&statusFlag='+'<%=request.getParameter("statusFlag")%>'+'&updStatusFlag='+'<%=request.getParameter("updStatusFlag")%>';
				document.rm_accvousfrom.submit();	
			
			}
			function test(type)
			{
				var fwd;
				var indx=0;
				var tcType;
				fwdPage=type;
		    	for(i=0;i < document.rm_accvousfrom.elements.length;i++)
				{
					if(document.rm_accvousfrom.elements[i].type=="checkbox" && document.rm_accvousfrom.elements[i].name != 'chkSelect')
					{
						if(document.rm_accvousfrom.elements[i].checked)
						{
							BillNo=document.rm_accvousfrom.elements[i].value; 
							arr= new Array();
					        arr=BillNo.split("~");		
					        tcType = eval('document.rm_accvousfrom.tcBill_'+arr[0]);
							indx++;
							break;
						} 
					}
				}
	    	
		    	if(indx==0)
		    	{
	    			alert("Select atleast one checkbox");
	    		 	return false;
	    		}
	    		var incI=0;
	    		var sumTcVal=0;
	    		var initilVal=0;
	    		var chkFlg=0;
	    		if(type=='Approve' || type=='Forward') 
	    		{
	    			
	    			for(i=0;i<document.rm_accvousfrom.elements.length;i++)
			    	{
			        	if(document.rm_accvousfrom.elements[i].type=="checkbox" && document.rm_accvousfrom.elements[i].name != 'chkSelect')
			        	{
			        		if(document.rm_accvousfrom.elements[i].checked == true)
			          		{
					        	BillNo=document.rm_accvousfrom.elements[i].value;  	
					        	arr= new Array();
					        	arr=BillNo.split("~");		
					        	var tcType1= eval('document.rm_accvousfrom.tcBill_'+arr[0]);
					        	if(tcType1.value!=tcType.value)
					        	{
					        		chkFlg=1;
					        		break;
					        	}
							}
				        }
				    }
				   
				  if(chkFlg==1)
				  {
				   	alert('Select bills with same Category');
				  	return false;	
				  }
				 }
/*		Added by Hiral   */				
				<% if(apprvFlag>0){
					%>
					if(type=='Forward')
					{
						for(i=0;i<document.rm_accvousfrom.elements.length;i++)
				    	{
				        	if(document.rm_accvousfrom.elements[i].type=="checkbox" && document.rm_accvousfrom.elements[i].name != 'chkSelect')
				        	{
				        		if(document.rm_accvousfrom.elements[i].checked == true)
				          		{
						        	BillNo=document.rm_accvousfrom.elements[i].value;  	
						        	arr= new Array();
						        	arr=BillNo.split("~");
						        	if(arr[9] == 1 && arr[8]=='')
						        	{
						        		alert('Please Open the bill and set Auditor Net Amount before forwarding, with Token No. : ' +arr[1]);
						        		return false;
						        	}						        	
								}
					        }
						}	
					}
				<%}%>
				
				<%if(apprvFlag==0){%>
					if(type=='Approve')
					{
						for(i=0;i<document.rm_accvousfrom.elements.length;i++)
				    	{
				        	if(document.rm_accvousfrom.elements[i].type=="checkbox" && document.rm_accvousfrom.elements[i].name != 'chkSelect')
				        	{
				        		if(document.rm_accvousfrom.elements[i].checked == true)
				          		{
						        	BillNo=document.rm_accvousfrom.elements[i].value;  	
						        	arr= new Array();
						        	arr=BillNo.split("~");
						        	if(arr[9] ==1 && (arr[8]!=arr[7]))
						        	{
						        		alert('Auditor Amount mismatched with Net Amount.\nYou cannot approve the bill with token No. : ' +arr[1]);
						        		return false;
						        	}
								}
					        }
						}	
					}
				<%}%>
				<%if(apprvFlag==1){%>
					if(type=='Approve')
					{
						for(i=0;i<document.rm_accvousfrom.elements.length;i++)
				    	{
				        	if(document.rm_accvousfrom.elements[i].type=="checkbox" && document.rm_accvousfrom.elements[i].name != 'chkSelect')
				        	{
				        		if(document.rm_accvousfrom.elements[i].checked == true)
				          		{
						        	BillNo=document.rm_accvousfrom.elements[i].value;  	
						        	arr= new Array();
						        	arr=BillNo.split("~");
						        	if(arr[9] == 1 && (arr[8]!=arr[7]))
						        	{
						        		alert('Auditor Amount mismatched with Net Amount.\nPlease make necessary changes after opening the bill with token No. : ' +arr[1]);
						        		return false;
						        	}
								}
					        }
						}	
					}
				<%}%>
/*		End : Added by Hiral   */				
				var BillNo="-1";
				var arr;
				for(i=0;i<document.rm_accvousfrom.elements.length;i++)
			    {

			        if(document.rm_accvousfrom.elements[i].type=="checkbox" && document.rm_accvousfrom.elements[i].name != 'chkSelect')
			        {	
			          if(document.rm_accvousfrom.elements[i].checked == true)
			          {
			        	BillNo=document.rm_accvousfrom.elements[i].value;  	
			        	arr= new Array();
			        	arr=BillNo.split("~");			        	
			            break;
			          }
			        }
			    }
			    
			    
			    
			    var arr1;
			    for(i=0;i<document.rm_accvousfrom.elements.length;i++)
			    {

			        if(document.rm_accvousfrom.elements[i].type=="checkbox" && document.rm_accvousfrom.elements[i].name != 'chkSelect')
			        {	
			          if(document.rm_accvousfrom.elements[i].checked == true)
			          {
			        	BillNo=document.rm_accvousfrom.elements[i].value;  	
			        	arr1= new Array();
			        	arr1=BillNo.split("~");		        	
			           	if(type == 'Approve')
						{	
							if(arr1 != null && arr1.length >0 )
							{
								var objectionCount = arr1[5];
								if(parseInt(objectionCount) >0)
								{
									if(!confirm('You have raised some objections. Do you still want to approve ?'))
									{
										return false;										
									}
									else
									{
										break;
									}
								}
							}
						}
						if(type=='Reject')
						{
							if(arr1 != null && arr1.length >0 )
							{
								var objectionCount = arr1[5];
								if(parseInt(objectionCount) <=0)
								{
									alert('You have not raised any objections on bill. Please raise some objection and then go for Rejection.')									
									return false;									
								}
							}
						}
			          }
			        }
			    }
				
				var updStatusFlag = '<%=request.getParameter("updStatusFlag")%>';
				if(type == 'Approve')
				{
					updStatusFlag = 'BAPPRV_AUD&actionVal=APPROVE';						
				}
				if(type=='Reject')
				{
					updStatusFlag = 'BRJCT_AUD&actionVal=REJECT';
					if(confirm('Do You Want Print the Objection'))
					{
						var arrList=new Array();
						var arrLst;
						var lstCont=0;
						for(i=0;i<document.rm_accvousfrom.elements.length;i++)
				    	{
	
					        if(document.rm_accvousfrom.elements[i].type=="checkbox" && document.rm_accvousfrom.elements[i].name != 'chkSelect')
					        {	
					          if(document.rm_accvousfrom.elements[i].checked == true)
					          {
					        	BillNo=document.rm_accvousfrom.elements[i].value;  	
					        	arrLst= new Array();
					        	arrLst=BillNo.split("~");			        	
								arrList[lstCont]=arrLst[0];
								lstCont=lstCont+1;
					          }
					        }
				    	}
						showRemarks('ifms.htm?actionFlag=getAllObjectionData&pageFlg=rj&billLst='+arrList);
					}					
				}
				if(type=='Peer')
				{
					updStatusFlag = updStatusFlag + '&sendTo=P';
				}
				if(type=='Forward')
				{
					updStatusFlag = updStatusFlag + '&sendTo=H';
				}
				
				
				
				
				
				
				url = 'ifms.htm?actionFlag=getHyrUsers&recFlag=1&statusFlag='+'<%=request.getParameter("statusFlag")%>'+'&updStatusFlag='+updStatusFlag+'&BillNo='+arr[0];
				window.open(url,'_blank','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,top=100,left=150,width=500,height=400');
			}
			
			
			
	 		function rejectBill(fwd)
			{
				var indx=0;
		    	for(i=0;i < document.rm_accvousfrom.elements.length;i++)
				{
					if(document.rm_accvousfrom.elements[i].type=="checkbox" && document.rm_accvousfrom.elements[i].name != 'chkSelect')
					{
						if(document.rm_accvousfrom.elements[i].checked)
						{
							indx++;
						} 
					}
				}	    	
		    	if(indx==0)
		    	{
	    			alert("Select Atlist One Checkbox To Reject");
	    		 	return false;
	    		}			
				document.rm_accvousfrom.parentUrl1.value='';
				fwdPage=fwd;
				window.open('ifms.htm?actionFlag=getHyrUsers&BillNo=-1&actionVal=REJECT&page='+fwd,'_blank','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,top=100,left=150,width=500,height=400');
			}
	 		
  			
  			
</script>
</head>
<%@page import="java.util.ResourceBundle"%>
<%
			ResourceBundle buttonBundle = ResourceBundle
			.getBundle("resources/billproc/billproc_en_US");
%>

<body>

<hdiits:form name="rm_accvousfrom" validate="true" method="post">
	<%
		String queryString = (String) request.getQueryString();
		if (queryString != null) {
			queryString = queryString.replaceAll("&", "*");
		}
	%>

	<input type="hidden" name="toPost" value="-1">
	<input type="hidden" name="toUser" value="-1">
	<input type="hidden" name="toLevel" value="-1">
	<input type="hidden" name="SendTo" value="-1">
	<input type="hidden" name="actionVal" value="">
	<input type="hidden" name="returnCase">
	<input type="hidden" name="parentUrl1"
		value="ifms.htm#<%=queryString%>">
	<input type="hidden" name="recFlag"
		value="<%=request.getParameter("recFlag").toString()%>">


	<%
	int i = 1;
	%>
	<c:set var="resultObj" value="${result}">
	</c:set>

	<c:set var="resValue" value="${resultObj.resultValue}">
	</c:set>
	<c:set var="billList" value="${resValue.billList}">
	</c:set>
	<c:set var="HeaderName" value="${resValue.HeaderName}">
	</c:set>
	<c:set var="page" value="${resValue.page}">
	</c:set>
	<c:set var="postId" value="${resValue.postId}">
	</c:set>
	<c:set var="recFlag" value="${resValue.recFlag}">
	</c:set>

	<table border="1" cellspacing="0" cellpadding="0" align="center"
		width="100%">

		<tr class="datatableheader">
			<td align="center"><c:out value="${HeaderName}" /></td>
			<!-- | <c:out value="${postId}"/> | <c:out value="${page}"/> | <c:out value="${recFlag}"/> -->
		</tr>
		<tr>
			<td>
			<div id="statusBar"
				style="width:50%; background-color: #BADCFD ;display:none;font: bold 10px Arial"></div>
			<div id="progressImage" style="display:none"></div>
			</td>
		</tr>
		<tr>
			<td>
			<table align="center" width="100%">
				<tr>
					<td height="20"><br>
					</td>
				</tr>
				<tr>
					<td>
					<table align="center" width="90%">
						<tr>
							<td width="60%"></td>
							<td width="8%" align="right"><fmt:message
								key="CHQCOMMON.SEARCH" bundle="${billprocLabels}"></fmt:message>
							:</td>
							<td><input type="text" name="txtSearch" size="15"
								onkeypress="javascript:onSearch(event,'<%=request.getParameter("statusFlag")%>','<%=request.getParameter("recFlag")%>','<%=request.getParameter("updStatusFlag")%>');"
								value="<% if(request.getParameter("txtSearch")!= null) { out.print(request.getParameter("txtSearch")); }  %>">
							<div id="dtpicker" style="display:none"><img
								src="images/CalendarImages/ico-calendar.gif" width="20"
								onClick=window_open("txtSearch",375,570) ></div>

							</td>
							<td width="*"><select name="cmbSearch" id="id_cmbSearch"
								onchange="showDtPic()">
								<option value="0">-----Select-----</option>

								<option value="p.billCntrlNo"
									<% if(request.getParameter("cmbSearch")!= null && request.getParameter("cmbSearch").equals("p.billCntrlNo")) { out.print("selected"); }  %>>
								<fmt:message key="CMN.BILL_CONTROL_NO"
									bundle="${billprocLabels}"></fmt:message></option>
								<option value="p.tokenNum"
									<% if(request.getParameter("cmbSearch")!= null && request.getParameter("cmbSearch").equals("p.tokenNum")) { out.print("selected"); }  %>>
								<fmt:message key="CMN.TOKEN_NO" bundle="${billprocLabels}"></fmt:message>
								</option>
								<option value="mb.subjectDesc"
									<% if(request.getParameter("cmbSearch")!= null && request.getParameter("cmbSearch").equals("mb.subjectDesc")) { out.print("selected"); }  %>>
								<fmt:message key="CMN.BILL_TYPE" bundle="${billprocLabels}"></fmt:message>
								</option>
								<option value="p.inwardDt"
									<% if(request.getParameter("cmbSearch")!= null && request.getParameter("cmbSearch").equals("p.inwardDt")) { out.print("selected"); }  %>>
								<fmt:message key="CMN.BILL_DATE" bundle="${billprocLabels}"></fmt:message>
								</option>
								<option value="odm.ddoNo"
									<% if(request.getParameter("cmbSearch")!= null && request.getParameter("cmbSearch").equals("odm.ddoNo")) { out.print("selected"); }  %>>
								<fmt:message key="CNTR.DDO_NO" bundle="${billprocLabels}"></fmt:message>
								</option>
								<option value="odm.ddoName"
									<% if(request.getParameter("cmbSearch")!= null && request.getParameter("cmbSearch").equals("odm.ddoName")) { out.print("selected"); }  %>>
								<fmt:message key="CMN.DDO_NAME" bundle="${billprocLabels}"></fmt:message>
								</option>
								<option value="p.budmjrHd"
									<% if(request.getParameter("cmbSearch")!= null && request.getParameter("cmbSearch").equals("p.budmjrHd")) { out.print("selected"); }  %>>
								<fmt:message key="CMN.MAJOR_HEAD" bundle="${billprocLabels}"></fmt:message>
								</option>

								<option value="clm.lookupName"
									<% if(request.getParameter("cmbSearch")!= null && request.getParameter("cmbSearch").equals("clm.lookupName")) { out.print("selected"); }  %>>
								<fmt:message key="CMN.BILL_CATEGORY" bundle="${billprocLabels}"></fmt:message>
								</option>
							</select></td>
							<td width="2%"><a href="#" onclick="javascript:searching();"><img
								src="common/images/search.gif" align="right" height="16"
								width="16" /></a></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td height="20"><br>
					</td>
				</tr>

				<tr>
					<td>
					<%
					int j = 0;
					%> <display:table list="${billList}" pagesize="25"
						requestURI="ifms.htm?actionFlag=getHyrUsers&page=savedBillsUpdate"
						id="VO" excludedParams="ajax" varTotals="totals" partialList=""
						style="width:100%">
						<display:setProperty name="paging.banner.placement" value="bottom" />
						<display:column class="oddcentre"
							title="<input name='chkSelect' type='checkbox' onclick='checkUncheckAll(this);'/>"
							headerClass="datatableheader">
							<input name="chkbox"
								value="${VO.billNo}~${VO.tokenNum}~${VO.budmjrHd}~${VO.cardexNo}~${VO.lookupName}~${VO.objCount}~${VO.ddoNO}~${VO.billNetAmount}~${VO.audNetAmount}~${VO.phyBill}"
								type="checkbox">
						</display:column>


						<%
									if (request.getParameter("recFlag") != null
									&& request.getParameter("recFlag").equals("1")) {
						%>
						<display:column class="oddcentre" titleKey="CMN.BILL_CONTROL_NO"
							sortable="true" headerClass="datatableheader" style="width:20%">
							<div align=center><c:choose>
								<c:when test="${VO.phyBill==1}">
									<c:set var="URLLink"
										value="ifms.htm?actionFlag=showBill&sessionFlag=1&BillNo=${VO.billNo}"></c:set>
									<a href="javascript:void(0)"
										onclick="javascript:showBill('${URLLink}')"> <c:out
										value="${VO.billCntrlNo}" /> </a>
								</c:when>
								<c:otherwise>
									<c:set var="URLLink"
										value="ifms.htm?actionFlag=getBillData&billNo=${VO.billNo}&billStatus=BSNT_TO"></c:set>
									<a href="javascript:void(0)"
										onclick="javascript:showBills('${VO.billNo}','<%=request.getParameter("statusFlag")%>')">
									<c:out value="${VO.billCntrlNo}" /> </a>
								</c:otherwise>
							</c:choose></div>
						</display:column>
						<%
						} else {
						%>
						<display:column class="oddcentre" titleKey="CMN.BILL_CONTROL_NO"
							sortable="true" headerClass="datatableheader" style="width:20%">
							<div align=center><c:out value="${VO.billCntrlNo}" /></div>
						</display:column>
						<%
						}
						%>

						<display:column property="tokenNum" class="oddcentre"
							titleKey="CMN.TOKEN_NO" sortable="true"
							headerClass="datatableheader" style="text-align:center" />
						<display:column property="refNum" class="oddcentre"
							titleKey="CMN.RefNumber" sortable="true"
							headerClass="datatableheader" style="text-align:center" />
						<display:column property="billGrossAmount" class="oddcentre"
							titleKey="CMN.BILL_AMOUNT" sortable="true"
							headerClass="datatableheader" style="text-align:center" />
						<display:column property="subjectDesc" class="oddcentre"
							titleKey="CMN.BILL_TYPE" sortable="true"
							headerClass="datatableheader" style="width:20%" />
						<display:column property="inwardDt" class="oddcentre"
							format="{0,date,dd/MM/yyyy}" titleKey="CMN.BILL_DATE"
							sortable="true" headerClass="datatableheader" />
						<display:column property="ddoNO" class="oddcentre"
							titleKey='CNTR.DDO_NO' sortable="true"
							headerClass="datatableheader" />
						<display:column class="oddcentre" titleKey="CMN.DDO_NAME_CARDEX"
							sortable="true" headerClass="datatableheader" style="width:20%">
							<c:out value="${VO.ddoName}" />[<c:out value="${VO.cardexNo}" />]
				    </display:column>

						<display:column property="budmjrHd" class="oddcentre"
							titleKey="CMN.MAJOR_HEAD" sortable="true"
							headerClass="datatableheader" />
						<display:column class="oddcentre" titleKey="CMN.AUDITOR_NAME"
							headerClass="datatableheader" sortable="true" style="width:15%">
							<c:out value="${VO.audName}" />
							<input type="hidden" name="cmb_<c:out value="${VO.billNo}"/>"
								value="<c:out value="${VO.audPostId}"/>">
						</display:column>
						<display:column class="oddcentre" titleKey="CMN.BILL_CATEGORY"
							headerClass="datatableheader" sortable="true">
							<c:out value="${VO.lookupName}" />
							<input type="hidden" name="tcBill_<c:out value="${VO.billNo}"/>"
								value="<c:out value="${VO.tcBill}"/>">
						</display:column>
						<%
									if (request.getParameter("statusFlag").equals("BCRDX")
									&& request.getParameter("recFlag") != null
									&& request.getParameter("recFlag").equals("1")) {
						%>
						<display:column class="oddcentre" titleKey="CMN.CARDEXVERIFIED"
							headerClass="datatableheader" sortable="true">
							<select name="CmbCdxVerify_<c:out value="${VO.billNo}"/>">
								<option
									value="<fmt:message key="STATUS.CardexVerified" bundle="${billprocConst}" />">
								<fmt:message key="CMN.YES" bundle="${billprocLabels}" /></option>
								<option selected="selected"
									value="<fmt:message  key="STATUS.CardexNotVerified" bundle="${billprocConst}" />">
								<fmt:message key="CMN.NO" bundle="${billprocLabels}" /></option>
							</select>
						</display:column>
						<display:column class="oddcentre" title='Signature'
							sortable="true" headerClass="datatableheader"
							style="text-align:center">
							<a href="javascript:openDigiPage('<c:out value="${VO.ddoNO}"/>')"><img
								src="images/validator_file.gif" width="20"></a>
						</display:column>
						<%
						}
						%>



						<%
						j++;
						%>
						<display:footer media="html">
						</display:footer>

					</display:table> <hdiits:table bordercolor="green" align="center">


						<c:choose>
							<c:when test="${recFlag=='1'}">
								<hdiits:tr>
									<hdiits:td align="center" colspan="10">
										<hdiits:button name="btnRtn" type="button"
											value='<%=buttonBundle.getString("COMMON.RETURN")%>'
											onclick="returnBill()" />&nbsp;
					   			<hdiits:button name="sendToPeer" type="button"
											value='<%=buttonBundle.getString("COMMON.SENDTOPEER")%>'
											onclick="test('Peer')" />&nbsp;
					   		  <%
					   		  if (request.getParameter("statusFlag").equals("BCRDX")) {
					   		  %>
										<hdiits:button name="cardexFwd" type="button"
											classcss="bigbutton"
											value='<%=buttonBundle.getString("COMMON.VERIFYANDFORWARD")%>'
											onclick="forwardData()" />
										<%
													} else if (request.getParameter("statusFlag").equals("BAPPRV_AUD")
													&& request.getParameter("recFlag").equals("1")) {
										%>
										<hdiits:button name="btnCheqPrep" classcss="bigbutton"
											type="button"
											value='<%=buttonBundle.getString("COMMON.PREPARECHQ")%>'
											onclick="javascript:showChqPrepare('ifms.htm?actionFlag=chqWriteChq')" />
										<%
													} else if (request.getParameter("statusFlag").equals("BRJCT_AUD")
													&& request.getParameter("recFlag").equals("1")) {
										%>
										<hdiits:button name="btnBillDisp" type="button"
											value='<%=buttonBundle.getString("COMMON.DISPATCH")%>'
											onclick="javascript:tokenRelese()" />
										<%
												} else {
												if (userActions != null) {
													if (userActions.getUserElements(121059) == null) {
										%>
										<hdiits:button name="cardexFwd" type="button"
											value='<%=buttonBundle.getString("COMMON.FORWARD")%>'
											onclick="test('Forward')" />

										<%
												}
												} else {
										%>
										<hdiits:button name="cardexFwd" type="button"
											value='<%=buttonBundle.getString("COMMON.FORWARD")%>'
											onclick="test('Forward')" />
										<%
												}
												if (userActions != null
												&& userActions.getUserElements(120027) != null) {
										%>
										<hdiits:button type="button"
											userObject="<%=userActions.getUserElements(120027)%>"
											name="btnApprove"
											value='<%=buttonBundle.getString("COMMON.APPROVE")%>'
											onclick="test('Approve')"></hdiits:button>
										<hdiits:button type="button"
											userObject="<%=userActions.getUserElements(120027)%>"
											name="btnReject"
											value='<%=buttonBundle.getString("COMMON.REJECT")%>'
											onclick="test('Reject')"></hdiits:button>
										<%
											}
											}
										%>
									</hdiits:td>
								</hdiits:tr>
							</c:when>
							<c:otherwise>
								<hdiits:tr>
									<hdiits:td align="center" colspan="10">
										<hdiits:button name="receiveBtn" type="button"
											value='<%=buttonBundle.getString("COMMON.RECEIVE")%>'
											onclick="recFlg()" />
									</hdiits:td>
								</hdiits:tr>
							</c:otherwise>
						</c:choose>


					</hdiits:table></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<script>
	  	if(document.rm_accvousfrom.txtSearch.value!=null && document.rm_accvousfrom.cmbSearch.value=="p.inwardDt")
  				{
  					document.getElementById("dtpicker").style.display="inline";
  				}
	  	</script>
</hdiits:form>

</body>
</html>


