
<%
try {
%>
<%@ include file="/WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript" src="<c:url value="/script/leave/leavevalidation.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/ess/leave/leavecommon.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/hr/retirementbenefits/RetirementBenefitsCommon.js"/>"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>


<fmt:setBundle basename="resources.ess.leave.AlertMessages" var="alertLables" scope="request" />
<fmt:setBundle basename="resources.ess.leave.LeaveCaption" var="LeaveCaption" scope="request" />

<style type="text/css">    
            .pg-normal {
                color: black;
                font-weight: normal;
                text-decoration: none;    
                cursor: pointer;    
            }
            .pg-selected {
                color: black;
                font-weight: bold;        
                text-decoration: underline;
                cursor: pointer;
            }
</style>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="UserBalDtlList" value="${resValue.UserBalDtlList}" />
<c:set var="userNameList" value="${resValue.userNameList}" />
<c:set var="userIdList" value="${resValue.userIdList}" />
<c:set var="newOrExistingUserList"
	value="${resValue.newOrExistingUserList}" />
<c:set var="operationId" value="${resValue.operationId}" />
<c:set var="currentDate" value="${resValue.currentDate}" />
<c:set var="leaveCodeList" value="${resValue.leaveCodeList}" />
<c:set var="userLeaveBalInfoList"
	value="${resValue.userLeaveBalInfoList}" />
<c:set var="maxELBal" value="${resValue.maxELBal}" />
	<c:set var="elCredit" value="${resValue.elCredit}"/>		
		<c:set var="hplCredit" value="${resValue.hplCredit}"/>

<script>


var color="#C6AEC7";
function resetPage()
{
	if(confirm('<fmt:message  bundle="${alertLables}" key="HRMS.RESETALLCONFIRMATION"/>'))
	{
		window.location.reload();
	}
}

function resetAll(counterForNew,leaveCodeList)
{
	if(confirm('<fmt:message  bundle="${alertLables}" key="HRMS.RESETALLCONFIRMATION"/>'))
	{
		var leaveCodeArr=new Array();
		leaveCodeArr=leaveCodeList.split("~");
		
		var counterNewArr=new Array();
		counterNewArr=counterForNew.split("~");
		
		for(var k=0;k<leaveCodeArr.length - 1;k++)
		{
			for(var r=0;r<counterNewArr.length - 1;r++)
			{
				document.getElementById('LT_'+leaveCodeArr[k]+'_'+counterNewArr[r]).value='';
				document.getElementById('Chk_'+leaveCodeArr[k]+'_'+counterNewArr[r]).checked =false;
			}
			document.getElementById('ColumnCode'+leaveCodeArr[k]).value=leaveCodeArr[k];
			document.getElementById('add'+leaveCodeArr[k]).value='';		
			document.getElementById('Chk_'+leaveCodeArr[k]).checked =false;
		}
		document.getElementById('frmSubmitButton').disabled=true;
	}
}


function resetChecked(counterForNew,leaveCodeList)
{
	if(confirm('<fmt:message  bundle="${alertLables}" key="HRMS.RESETCONFIRMATION"/>'))
	{
		var leaveCodeArr=new Array();
		leaveCodeArr=leaveCodeList.split("~");
		
		var counterNewArr=new Array();
		counterNewArr=counterForNew.split("~");
		
		var submitBtnCounter=0;
		for(var k=0;k<leaveCodeArr.length - 1;k++)
		{
			if(document.getElementById('Chk_'+leaveCodeArr[k]).checked == true)
			{
				for(var r=0;r<counterNewArr.length - 1;r++)
				{
					if( document.getElementById('operationId').value == 1 )	
					{
						document.getElementById('TRBAL_'+leaveCodeArr[k]+'_'+counterNewArr[r]).value='';
						document.getElementById('LT_'+leaveCodeArr[k]+'_'+counterNewArr[r]).value='';
					}	
					else
					{
						document.getElementById('LT_'+leaveCodeArr[k]+'_'+counterNewArr[r]).value=document.getElementById('HIDDEN_LT_'+leaveCodeArr[k]+'_'+counterNewArr[r]).value;
						document.getElementById('TRBAL_'+leaveCodeArr[k]+'_'+counterNewArr[r]).value=document.getElementById('HIDDEN_TRBAL_'+leaveCodeArr[k]+'_'+counterNewArr[r]).value;
					}
					document.getElementById('Chk_'+leaveCodeArr[k]+'_'+counterNewArr[r]).checked =false;
					document.getElementById('LT_'+leaveCodeArr[k]+'_'+counterNewArr[r]).style.backgroundColor='white';
				}
				document.getElementById('ColumnCode'+leaveCodeArr[k]).value=leaveCodeArr[k];
				document.getElementById('add'+leaveCodeArr[k]).value='';
				document.getElementById('add'+leaveCodeArr[k]).style.backgroundColor='white';		
				document.getElementById('Chk_'+leaveCodeArr[k]).checked =false;
			}
			else
			{
				for(var r=0;r<counterNewArr.length - 1;r++)
				{
					if(document.getElementById('Chk_'+leaveCodeArr[k]+'_'+counterNewArr[r]).checked == true)
					{
						if( document.getElementById('operationId').value == 1 )	
						{
							document.getElementById('TRBAL_'+leaveCodeArr[k]+'_'+counterNewArr[r]).value='';
							document.getElementById('LT_'+leaveCodeArr[k]+'_'+counterNewArr[r]).value='';
						}	
						else
						{
							document.getElementById('LT_'+leaveCodeArr[k]+'_'+counterNewArr[r]).value=document.getElementById('HIDDEN_LT_'+leaveCodeArr[k]+'_'+counterNewArr[r]).value;
							document.getElementById('TRBAL_'+leaveCodeArr[k]+'_'+counterNewArr[r]).value=document.getElementById('HIDDEN_TRBAL_'+leaveCodeArr[k]+'_'+counterNewArr[r]).value;
						}
						document.getElementById('LT_'+leaveCodeArr[k]+'_'+counterNewArr[r]).style.backgroundColor='white';
						document.getElementById('Chk_'+leaveCodeArr[k]+'_'+counterNewArr[r]).checked=false;
						document.getElementById('ColumnCode'+leaveCodeArr[k]).value=eval(document.getElementById('ColumnCode'+leaveCodeArr[k]).value) - 1;
					}
				}
			}
			
			if(document.getElementById('ColumnCode'+leaveCodeArr[k]).value == leaveCodeArr[k])
			{
				submitBtnCounter=submitBtnCounter+1;
			}
		}
		if( submitBtnCounter == eval(leaveCodeArr.length - 1) )
		{
			if(document.getElementById('operationId').value==1)
				document.getElementById('frmSubmitButton').disabled=true;
		}
	}	
}


function checkAll(name,counterForNew)
{
	var counterNewArr=new Array();
	counterNewArr=counterForNew.split("~");

	if(document.getElementById(name).checked == true)
	{
	
		for(var j=0;j<counterNewArr.length - 1;j++)
	{		
			if(document.getElementById(name+"_"+counterNewArr[j])!= null){
			document.getElementById(name+"_"+counterNewArr[j]).checked=true;
			}
			
	}
	}
	else
	{
		for(var j=0;j<counterNewArr.length - 1;j++)
			if(document.getElementById(name+"_"+counterNewArr[j])!= null){
			document.getElementById(name+"_"+counterNewArr[j]).checked=false;
			}
			
	}
}

function checkMain(name)
{
	if(document.getElementById(name).checked)
	{
		document.getElementById(name).checked=false
	}
}

function validateFloat(component){

	if(component.value !='')
	{
		if(isNaN(component.value)){
			alert('<fmt:message key="HRMS.entValidNo" bundle="${alertLables}"/>');
			component.focus();
			return false;
		}
		else{
			return true;	
		}
	}
	
}

var prevLeaveBalValue;
var prevLeaveBalValueCounter=0;

function getPrevLeaveBalValue(component)
{
	if(prevLeaveBalValueCounter == 0)
	{
		prevLeaveBalValue=component.value;
		prevLeaveBalValueCounter++;
	}
}

function validateFloatNewUser(component,leaveCode)
{
	var ArrayListComp=new Array();
	ArrayListComp=component.name.split("_");
	
	if(isNaN(component.value)){
			alert('<fmt:message key="HRMS.entValidNo" bundle="${alertLables}"/>');
			component.select();
			component.focus();
			return false;
	}
	else if( component.value < 0 )
	{
			alert('<fmt:message key="HRMS.negValnotAllow" bundle="${alertLables}"/>');
			component.select();
			component.focus();
			return false;
	
	}
	else if( eval(component.value) > eval(document.getElementById('maxElBal').value) && leaveCode==eval(document.getElementById('ELCODE').value) )
	{
		alert('<fmt:message key="HRMS.digitLengthELExceed" bundle="${alertLables}"/> '+document.getElementById('maxELBal').value);
		component.select();
		component.focus();
		return false;
	}
	else if( eval(component.value) > 999 )
	{
	alert('<fmt:message key="HRMS.entValidNo" bundle="${alertLables}"/>');
		component.select();
		component.focus();
		return false;
	}
	else if(component.value == '')
	{
		if(document.getElementById('ColumnCode'+leaveCode).value > leaveCode)
		{
			document.getElementById('ColumnCode'+leaveCode).value=eval(document.getElementById('ColumnCode'+leaveCode).value)-1;
		}
	}
	else
	{
		if( document.getElementById('operationId').value==2 )	
		{
			if( component.value <= document.getElementById('maxElBal').value && leaveCode==eval(document.getElementById('ELCODE').value) )
			{
				document.getElementById('TRBAL_'+leaveCode+"_"+ArrayListComp[2]).value=document.getElementById('HIDDEN_TRBAL_'+leaveCode+"_"+ArrayListComp[2]).value;
				component.style.backgroundColor='white';
			}
			if( prevLeaveBalValueCounter == '' )
			{
				document.getElementById('ColumnCode'+leaveCode).value=eval(document.getElementById('ColumnCode'+leaveCode).value)+1;
			}
			prevLeaveBalValue=0;
			prevLeaveBalValueCounter=0;
		}
		document.getElementById('frmSubmitButton').disabled=false;
		return true;
	}
	var submitBtnArr=new Array();
	var submitBtnCounter=0;
	submitBtnArr=document.getElementById('leaveCodeArray').value.split("~");
	for(var k=0;k<submitBtnArr.length - 1;k++)
	{
		if(document.getElementById('ColumnCode'+submitBtnArr[k]).value == submitBtnArr[k])
		{
			submitBtnCounter=submitBtnCounter+1;
		}
		if(document.getElementById('ColumnCode'+leaveCode).value == leaveCode)
		{
			document.getElementById('add'+leaveCode).value='';
		}
	}
	if( submitBtnCounter == eval(submitBtnArr.length - 1) )
	{
		document.getElementById('frmSubmitButton').disabled=true;
	}
}



function modifyLeave(codeAddOrSub)
{

	var msg;
	var leaveCodeArr=new Array();
	var errorPageNoPrev = 0;
	leaveCodeArr=(document.getElementById('leaveCodeArray').value).split("~");
	for(var q=0;q<leaveCodeArr.length - 1;q++)
	{
		if(document.getElementById('add'+leaveCodeArr[q]).value != '')
		{
			msg=addBalToAll(document.getElementById('add'+leaveCodeArr[q]).value,document.getElementById('counterForUser').value,leaveCodeArr[q],codeAddOrSub);
		}
	}

	
	///For sorting
	if(document.getElementById('operationId').value==2)
	{
		var errorString='';
		if( eval(errorArray.length) != 0)
		{
			errorArray.sort(dmyOrdA);
			var prevVar=-1;
			for(var w=0;w<errorArray.length;w++)
			{
				if( prevVar!=errorArray[w] )
				{
					errorString=errorArray[w]+","+errorString;
				}
				prevVar=errorArray[w];
			}
		
		
			var disArray=new Array();
			disArray=errorString.split(",");
			var disArrayLength=eval(eval(disArray.length) - 2);
			
			var displayErrorString='<fmt:message key="HRMS.ruleVMsg" bundle="${alertLables}" />';
			displayErrorString=displayErrorString+disArray[disArrayLength];
			
			for(z=eval(disArrayLength)-1;z >= 0;z--)
			{
				displayErrorString=displayErrorString+","+disArray[z];
			}
			if( elBalExceed==1 && leaveExceed==1 )
			{
				alert('<fmt:message key="HRMS.digitLengthAndELExceedBal" bundle="${alertLables}" />'+document.getElementById('maxELBal').value);
			}
			else if( elBalExceed==1 && leaveExceed==0 )
			{
				alert('<fmt:message key="HRMS.digitLengthELRule" bundle="${alertLables}" />'+document.getElementById('maxELBal').value);
			}
			else if( elBalExceed==0 && leaveExceed==1 )
			{
				alert('<fmt:message key="HRMS.digitLength" bundle="${alertLables}" />');
			}
			document.getElementById('errorMsgSpan').innerHTML=displayErrorString;
		}
		else
		{
			document.getElementById('errorMsgSpan').innerHTML='';
		}
		
		errorArray=new Array();
		columnCounter=0;
		sortVar=0;x=0;
		elBalExceed=0;
		leaveBalExceed=0;
	}
	/*if(msg != null)
	{
		alert(msg);
	}*/
}

//DateComparator for sorting array of dateObject
function dmyOrdA(a, b){
if (a>b) return 1;
if (a <b) return -1;
return 0; }



var errorArrayFinal=new Array();
var errorArray=new Array();
var sortVar=0;
var columnCounter=0;
var x=0;
var leaveExceed=0;
var elBalExceed=0;

function elCodeCheck(retirementDate,code)
{
	var monDiff = 1;
	if((code==document.getElementById('leaveTypeHPLcode').value || code==document.getElementById('ELCODE').value) && retirementDate!='')
	{
		var cDate= document.getElementById('currDate').value;
		var splitDate=cDate.split("/");							
		var cday=parseInt(splitDate[0],10);
		var cmo=(parseInt(splitDate[1],10));
		var cyr=parseInt(splitDate[2]);
		
		var splitDate=retirementDate.split("/");							
		var rday=parseInt(splitDate[0],10);
		var rmo=(parseInt(splitDate[1],10));		
		var ryr=parseInt(splitDate[2]);
		
		monDiff=rmo;				
		if(ryr==cyr)
		{		
			if(cmo<=6 && rmo>6)
			{
				return -1;
			}
			if(rmo>6)			
			{
				monDiff=rmo-6;
			}						
			if(document.getElementById("leaveTypeHPLcode").value==code)
				{
					return eval(Math.round(monDiff*eval(document.getElementById("hplMulFactor").value)*10)/10);
				}
			else
				{
					return eval(Math.round(monDiff*eval(document.getElementById("elMulFactor").value)*10)/10);
				}
		}
	}
	else
	{
		return -1;
	}	
	return -1;
}
function addBalToAll(compValue,counterForNew,code,codeAddOrSub)
{
	var colorCounter=0;
	var counterNewArr=new Array();
	var errorPageNoStr = '';
	counterNewArr=counterForNew.split("~");
	if( (eval(compValue)> eval(document.getElementById('maxElBal').value)) && (eval(document.getElementById('ELCODE').value) == code) )
	{
		alert('<fmt:message key="HRMS.digitLengthELExceed" bundle="${alertLables}"/> '+document.getElementById('maxELBal').value);
		document.getElementById('add'+code).focus();
		return null;
	}
	if( !isNaN(compValue) ) 
	{
	if(document.getElementById('Chk_'+code)!=null){
		if(document.getElementById('Chk_'+code).checked)
		{
			if(document.getElementById('add'+code).value != '')
			{
				for(var j=0;j<eval(counterNewArr.length) - 1;j++)
				{
					if(document.getElementById('operationId').value == 2)
					{
					if(document.getElementById('LT_'+code+"_"+counterNewArr[j])!=null)
					{
						if(document.getElementById('LT_'+code+"_"+counterNewArr[j]).value=='')
						{													
							var updateElVal = elCodeCheck(document.getElementById('RETDATE_'+counterNewArr[j]).value,code);
							if(updateElVal!=-1){								
								document.getElementById('LT_'+code+"_"+counterNewArr[j]).style.backgroundColor=color;
								colorCounter=1;
								document.getElementById('LT_'+code+"_"+counterNewArr[j]).value=eval(updateElVal);
							}
							else{
								document.getElementById('LT_'+code+"_"+counterNewArr[j]).value=eval(compValue);
							}
							if( eval(document.getElementById('ELCODE').value) == code )
							{
								if( eval(document.getElementById('LT_'+code+"_"+counterNewArr[j]).value) >= eval(document.getElementById('maxELBal').value) )
								{
									document.getElementById('TRBAL_'+code+"_"+counterNewArr[j]).value= eval(document.getElementById('TRBAL_'+code+"_"+counterNewArr[j]).value) + eval( eval(document.getElementById('LT_'+code+"_"+counterNewArr[j]).value) - eval(document.getElementById('maxELBal').value) );
									document.getElementById('LT_'+code+"_"+counterNewArr[j]).value=eval(document.getElementById('maxELBal').value);
									document.getElementById('LT_'+code+"_"+counterNewArr[j]).style.backgroundColor='#ABE9F9';
									elBalExceed=1;
									colorCounter=1;
									columnCounter++;
	
									//For Error At Particular Page
									var errorCeil=eval(eval(counterNewArr[j])/eval(10));
									errorCeil=Math.ceil(eval(errorCeil));
									if(sortVar != errorCeil)
									{
											sortVar=errorCeil;
											errorArray[x]=errorCeil;
											x++;
									}
									
								}
							}
						}
						else
						{
							var updateElVal = elCodeCheck(document.getElementById('RETDATE_'+counterNewArr[j]).value,code);
							if(updateElVal!=-1){								
								document.getElementById('LT_'+code+"_"+counterNewArr[j]).style.backgroundColor=color;
								colorCounter=1;
								document.getElementById('LT_'+code+"_"+counterNewArr[j]).value=Math.round(eval(eval(document.getElementById('LT_'+code+"_"+counterNewArr[j]).value)+eval(updateElVal))*10)/10;
							}
							else{
								document.getElementById('LT_'+code+"_"+counterNewArr[j]).value=eval(eval(document.getElementById('LT_'+code+"_"+counterNewArr[j]).value)+eval(compValue));
							}
							if( eval(document.getElementById('ELCODE').value) == code )
							{
								if( eval(document.getElementById('LT_'+code+"_"+counterNewArr[j]).value) >= eval(document.getElementById('maxELBal').value) )
								{
									document.getElementById('TRBAL_'+code+"_"+counterNewArr[j]).value= eval(document.getElementById('TRBAL_'+code+"_"+counterNewArr[j]).value) + eval( eval(document.getElementById('LT_'+code+"_"+counterNewArr[j]).value) - eval(document.getElementById('maxELBal').value) );
									document.getElementById('LT_'+code+"_"+counterNewArr[j]).value=eval(document.getElementById('maxELBal').value);
									document.getElementById('LT_'+code+"_"+counterNewArr[j]).style.backgroundColor='#ABE9F9';
									elBalExceed=1;
									colorCounter=1;
									columnCounter++;
	
									//For Error At Particular Page
									var errorCeil=eval(eval(counterNewArr[j])/eval(10));
									errorCeil=Math.ceil(eval(errorCeil));
									if(sortVar != errorCeil)
									{
											sortVar=errorCeil;
											errorArray[x]=errorCeil;
											x++;
									}
									
								}
							}
						}
					}
						else
						{ 
							if(codeAddOrSub == 1)
							{
								var updateElVal =-1;
								if(document.getElementById('RETDATE_'+counterNewArr[j])!=null){								
									updateElVal = elCodeCheck(document.getElementById('RETDATE_'+counterNewArr[j]).value,code);
								}
								if(updateElVal!=-1){									
									if(document.getElementById('LT_'+code+"_"+counterNewArr[j])!=null){
										document.getElementById('LT_'+code+"_"+counterNewArr[j]).style.backgroundColor=color;
										colorCounter=1;
										document.getElementById('LT_'+code+"_"+counterNewArr[j]).value=Math.round(eval(eval(document.getElementById('LT_'+code+"_"+counterNewArr[j]).value)+eval(updateElVal))*10)/10;
									}
								}
								else{
									if(document.getElementById('LT_'+code+"_"+counterNewArr[j])!=null){
									document.getElementById('LT_'+code+"_"+counterNewArr[j]).value=eval(eval(document.getElementById('LT_'+code+"_"+counterNewArr[j]).value)+eval(compValue));
									}
								}								
								if( eval(document.getElementById('ELCODE').value) == code )
								{
									if( eval(document.getElementById('LT_'+code+"_"+counterNewArr[j]).value) >= eval(document.getElementById('maxELBal').value) )
									{
										/*for(var temp=j;temp >= 0;temp--)
										{
											document.getElementById('LT_'+code+"_"+counterNewArr[temp]).value=eval(eval(document.getElementById('LT_'+code+"_"+counterNewArr[temp]).value)-eval(compValue));
										}*/
										document.getElementById('TRBAL_'+code+"_"+counterNewArr[j]).value= eval(document.getElementById('TRBAL_'+code+"_"+counterNewArr[j]).value) + eval( eval(document.getElementById('LT_'+code+"_"+counterNewArr[j]).value) - eval(document.getElementById('maxELBal').value) );
										document.getElementById('LT_'+code+"_"+counterNewArr[j]).value=eval(document.getElementById('maxELBal').value);
										//document.getElementById('add'+code).style.backgroundColor='#FBAC27';
										document.getElementById('LT_'+code+"_"+counterNewArr[j]).style.backgroundColor='#ABE9F9';
										/*document.getElementById('add'+code).select();
										document.getElementById('add'+code).focus();*/
										//return null;
										elBalExceed=1;
										colorCounter=1;
										columnCounter++;

										//For Error At Particular Page
										var errorCeil=eval(eval(counterNewArr[j])/eval(10));
										errorCeil=Math.ceil(eval(errorCeil));
										if(sortVar != errorCeil)
										{
												sortVar=errorCeil;
												errorArray[x]=errorCeil;
												x++;
										}
										
									}
								}
								
								else if( eval(document.getElementById('LT_'+code+"_"+counterNewArr[j]).value) >= 1000  )
								{ 	
									document.getElementById('LT_'+code+"_"+counterNewArr[j]).value=eval(eval(document.getElementById('LT_'+code+"_"+counterNewArr[j]).value)-eval(compValue));
									document.getElementById('add'+code).style.backgroundColor='#FBAC27';
									document.getElementById('LT_'+code+"_"+counterNewArr[j]).style.backgroundColor='#FBAC27';
									leaveExceed=1;
									colorCounter=1;
									columnCounter++;

									//For Error At Particular Page
									var errorCeil=eval(eval(counterNewArr[j])/eval(10));
									errorCeil=Math.ceil(eval(errorCeil));
									if(sortVar != errorCeil)
									{
											sortVar=errorCeil;
											errorArray[x]=errorCeil;
											x++;
									}	
								}
								
							}
							else
							{							
								var updateElVal = elCodeCheck(document.getElementById('RETDATE_'+counterNewArr[j]).value,code);
								if(updateElVal!=-1){
									document.getElementById('LT_'+code+"_"+counterNewArr[j]).style.backgroundColor=color;
									colorCounter=1;
									document.getElementById('LT_'+code+"_"+counterNewArr[j]).value=Math.round(eval(eval(document.getElementById('LT_'+code+"_"+counterNewArr[j]).value)-eval(updateElVal))*10)/10;
								}
								else{
									document.getElementById('LT_'+code+"_"+counterNewArr[j]).value=eval(eval(document.getElementById('LT_'+code+"_"+counterNewArr[j]).value)-eval(compValue));
								}								
								if( eval(document.getElementById('LT_'+code+"_"+counterNewArr[j]).value) < 0 )
								{
									for(var temp=j;temp >= 0;temp--)
									{
										document.getElementById('LT_'+code+"_"+counterNewArr[temp]).value=eval(eval(document.getElementById('LT_'+code+"_"+counterNewArr[temp]).value)+eval(compValue));
									}
									document.getElementById('add'+code).style.backgroundColor='#FBAC27';
									document.getElementById('LT_'+code+"_"+counterNewArr[j]).style.backgroundColor='#FBAC27';
									document.getElementById('add'+code).select();
									document.getElementById('add'+code).focus();
									return '<fmt:message key="HRMS.digitLengthN" bundle="${alertLables}"/>';
								}
							}
						}
						if( eval(document.getElementById('ELCODE').value) != code && colorCounter==0 )
						{
							document.getElementById('add'+code).style.backgroundColor='white';
							document.getElementById('LT_'+code+"_"+counterNewArr[j]).style.backgroundColor='white';
						}
					}
					else
					{
						if( eval(compValue) > eval(document.getElementById('maxElBal').value) && code==eval(document.getElementById('ELCODE').value) )
						{
							alert('<fmt:message key="HRMS.digitLengthELExceed" bundle="${alertLables}"/> '+document.getElementById('maxELBal').value);
							document.getElementById('add'+code).focus();
							return null;
						}
						else
						{									
							var updateElVal = elCodeCheck(document.getElementById('RETDATE_'+counterNewArr[j]).value,code);
							if(updateElVal!=-1)
							{
								document.getElementById('LT_'+code+"_"+counterNewArr[j]).style.backgroundColor=color;
								colorCounter=1;
								document.getElementById('LT_'+code+"_"+counterNewArr[j]).value=updateElVal;
							}
							else{
								document.getElementById('LT_'+code+"_"+counterNewArr[j]).value=compValue;
							}
						}
						
					}
					
				}
				
				j=eval(j)-eval(columnCounter);
				document.getElementById('frmSubmitButton').disabled=false;
				document.getElementById('ColumnCode'+code).value=eval(code)+j;
				if(colorCounter == 0)
				{
					errorArray=new Array();
					columnCounter=0;
					sortVar=0;x=0;
					return null;
				}
				else
				{
					return '<fmt:message key="HRMS.digitLength" bundle="${alertLables}"/>';
				}
				
			}
			else
			{
				return ;
			}
	
		}
		if(document.getElementById('Chk_'+code).checked == false)
		{
			var submitBtnCounter=0;
			if(document.getElementById('add'+code).value != '')
			{
				for(var k=0;k<counterNewArr.length - 1;k++)
				{
				if(document.getElementById('Chk_'+code+"_"+counterNewArr[k])!=null){
					if(document.getElementById('Chk_'+code+"_"+counterNewArr[k]).checked == false)
					{
						submitBtnCounter=submitBtnCounter+1;
					}
				}
				}
				if( submitBtnCounter == eval(counterNewArr.length - 1) )
				{
					for(var l=0;l<counterNewArr.length - 1;l++)
					{
						if(document.getElementById('operationId').value == 2)
						{							
							if(document.getElementById('LT_'+code+"_"+counterNewArr[l]).value=='')
							{
								var updateElVal = elCodeCheck(document.getElementById('RETDATE_'+counterNewArr[l]).value,code);
								if(updateElVal!=-1){
									document.getElementById('LT_'+code+"_"+counterNewArr[l]).style.backgroundColor=color;
									colorCounter=1;
									document.getElementById('LT_'+code+"_"+counterNewArr[l]).value=eval(updateElVal);
								}
								else{
									document.getElementById('LT_'+code+"_"+counterNewArr[l]).value=eval(compValue);
								}																
								if( eval(document.getElementById('ELCODE').value) == code )
								{
									if( eval(document.getElementById('LT_'+code+"_"+counterNewArr[l]).value) >= eval(document.getElementById('maxELBal').value) )
									{
										/*for(var temp=l;temp >= 0;temp--)
										{
											document.getElementById('LT_'+code+"_"+counterNewArr[temp]).value=eval(eval(document.getElementById('LT_'+code+"_"+counterNewArr[temp]).value)-eval(compValue));
										}*/
										document.getElementById('TRBAL_'+code+"_"+counterNewArr[l]).value= eval(document.getElementById('TRBAL_'+code+"_"+counterNewArr[l]).value) + eval( eval(document.getElementById('LT_'+code+"_"+counterNewArr[l]).value) - eval(document.getElementById('maxELBal').value) );
										document.getElementById('LT_'+code+"_"+counterNewArr[l]).value=eval(document.getElementById('maxELBal').value);
										//document.getElementById('add'+code).style.backgroundColor='#FBAC27';
										document.getElementById('LT_'+code+"_"+counterNewArr[l]).style.backgroundColor='#ABE9F9';
										/*document.getElementById('add'+code).select();
										document.getElementById('add'+code).focus();*/
										//return null;
										elBalExceed=1;
										colorCounter=1;
										columnCounter++;
		
										//For Error At Particular Page
										var errorCeil=eval(eval(counterNewArr[l])/eval(10));
										errorCeil=Math.ceil(eval(errorCeil));
										if(sortVar != errorCeil)
										{
												sortVar=errorCeil;
												errorArray[x]=errorCeil;
												x++;
										}
										
									}
								}
							}
							else
							{
								if(codeAddOrSub == 1)
								{									
									var updateElVal=-1;
									if(document.getElementById('RETDATE_'+counterNewArr[l])!=null){
									 	updateElVal = elCodeCheck(document.getElementById('RETDATE_'+counterNewArr[l]).value,code);
									}
									if(updateElVal!=-1){										
										document.getElementById('LT_'+code+"_"+counterNewArr[l]).style.backgroundColor=color;										
										document.getElementById('LT_'+code+"_"+counterNewArr[l]).value=Math.round(eval(eval(document.getElementById('LT_'+code+"_"+counterNewArr[l]).value)+eval(updateElVal))*10)/10;										
										colorCounter=1;
									}
									else{
										document.getElementById('LT_'+code+"_"+counterNewArr[l]).value=eval(eval(document.getElementById('LT_'+code+"_"+counterNewArr[l]).value)+eval(compValue));
									}									
									if( eval(document.getElementById('ELCODE').value) == code )
									{
										if( eval(document.getElementById('LT_'+code+"_"+counterNewArr[l]).value) >= eval(document.getElementById('maxELBal').value) )
										{	
											/*for(var temp=l;temp >= 0;temp--)
											{
												document.getElementById('LT_'+code+"_"+counterNewArr[temp]).value=eval(eval(document.getElementById('LT_'+code+"_"+counterNewArr[temp]).value)-eval(compValue));
											}*/
											document.getElementById('TRBAL_'+code+"_"+counterNewArr[l]).value= eval(document.getElementById('TRBAL_'+code+"_"+counterNewArr[l]).value) + eval( eval(document.getElementById('LT_'+code+"_"+counterNewArr[l]).value) - eval(document.getElementById('maxELBal').value) );
											document.getElementById('LT_'+code+"_"+counterNewArr[l]).value=eval(document.getElementById('maxELBal').value);
											//document.getElementById('add'+code).style.backgroundColor='#FBAC27';
											document.getElementById('LT_'+code+"_"+counterNewArr[l]).style.backgroundColor='#ABE9F9';
											//document.getElementById('add'+code).select();
											//document.getElementById('add'+code).focus();
											//return null;
											elBalExceed=1;
											colorCounter=1;
											columnCounter++;
	
											//For Error At Particular Page
											var errorCeil=eval(eval(counterNewArr[l])/eval(10));
											errorCeil=Math.ceil(eval(errorCeil));
											if(sortVar != errorCeil)
											{
													sortVar=errorCeil;
													errorArray[x]=errorCeil;
													x++;
											}
											
											
										}
									}
									else if( eval(document.getElementById('LT_'+code+"_"+counterNewArr[l]).value) >= 1000  )
									{
										document.getElementById('LT_'+code+"_"+counterNewArr[l]).value=eval(eval(document.getElementById('LT_'+code+"_"+counterNewArr[l]).value)-eval(compValue));
										document.getElementById('add'+code).style.backgroundColor='#FBAC27';
										document.getElementById('LT_'+code+"_"+counterNewArr[l]).style.backgroundColor='#FBAC27';
										leaveExceed=1;
										colorCounter=1;
										columnCounter++;

										//For Error At Particular Page
										var errorCeil=eval(eval(counterNewArr[l])/eval(10));
										errorCeil=Math.ceil(eval(errorCeil));
										if(sortVar != errorCeil)
										{
												sortVar=errorCeil;
												errorArray[x]=errorCeil;
												x++;
										}	
									}
								}
								else
								{
									var updateElVal = elCodeCheck(document.getElementById('RETDATE_'+counterNewArr[l]).value,code);
									if(updateElVal!=-1){
										document.getElementById('LT_'+code+"_"+counterNewArr[l]).style.backgroundColor=color;
										colorCounter=1;
										document.getElementById('LT_'+code+"_"+counterNewArr[l]).value=Math.round(eval(eval(document.getElementById('LT_'+code+"_"+counterNewArr[l]).value)-eval(updateElVal))*10)/10;
									}
									else{
										document.getElementById('LT_'+code+"_"+counterNewArr[l]).value=eval(eval(document.getElementById('LT_'+code+"_"+counterNewArr[l]).value)-eval(compValue));
									}									
									if( eval(document.getElementById('LT_'+code+"_"+counterNewArr[l]).value) < 0 )
									{
										//alert('<fmt:message key="HRMS.digitLengthN" bundle="${alertLables}"/>');
										for(var temp=l;temp >= 0;temp--)
										{
											document.getElementById('LT_'+code+"_"+counterNewArr[temp]).value=eval(eval(document.getElementById('LT_'+code+"_"+counterNewArr[temp]).value)+eval(compValue));
										}
										document.getElementById('add'+code).style.backgroundColor='#FBAC27';
										document.getElementById('LT_'+code+"_"+counterNewArr[l]).style.backgroundColor='#FBAC27';
										document.getElementById('add'+code).select();
										document.getElementById('add'+code).focus();
										return '<fmt:message key="HRMS.digitLengthN" bundle="${alertLables}"/>';
									}
								}
								if( colorCounter!=1 )
								{
									document.getElementById('add'+code).style.backgroundColor='white';
									document.getElementById('LT_'+code+"_"+counterNewArr[l]).style.backgroundColor='white';
								}
							}	
						}
						else
						{
							if( eval(compValue) > eval(document.getElementById('maxElBal').value) && code==eval(document.getElementById('ELCODE').value) )
							{
								alert('<fmt:message key="HRMS.digitLengthELExceed" bundle="${alertLables}"/> '+document.getElementById('maxELBal').value);
								document.getElementById('add'+code).focus();
								return null;
							}
							else
							{
								var updateElVal = elCodeCheck(document.getElementById('RETDATE_'+counterNewArr[l]).value,code);
								if(updateElVal!=-1){
									document.getElementById('LT_'+code+"_"+counterNewArr[l]).style.backgroundColor=color;
									colorCounter=1;
									document.getElementById('LT_'+code+"_"+counterNewArr[l]).value=updateElVal;
								}
								else{
									document.getElementById('LT_'+code+"_"+counterNewArr[l]).value=compValue;
								}								
							}
						}
					}

					l=eval(l)-eval(columnCounter);
					document.getElementById('frmSubmitButton').disabled=false;
					document.getElementById('ColumnCode'+code).value=eval(code)+l;
					if(colorCounter == 0)
					{
						errorArray=new Array();
						columnCounter=0;
						sortVar=0;x=0;
						return null;
					}
					else
					{
						return '<fmt:message key="HRMS.digitLength" bundle="${alertLables}"/>';
					}
				}
			}
		}
		if(document.getElementById('Chk_'+code).checked == false)
		{
			if(document.getElementById('add'+code).value != '')
			{
				for(var j=0;j<counterNewArr.length - 1;j++)
				{
				if(document.getElementById('Chk_'+code+'_'+counterNewArr[j])!=null){
					if(document.getElementById('Chk_'+code+'_'+counterNewArr[j]).checked)
					{
						if(document.getElementById('LT_'+code+'_'+counterNewArr[j]).value == '')
						{
							if(document.getElementById('operationId').value==2)
							{
								var updateElVal = elCodeCheck(document.getElementById('RETDATE_'+counterNewArr[l]).value,code);
								if(updateElVal!=-1){
									document.getElementById('LT_'+code+"_"+counterNewArr[j]).style.backgroundColor=color;
									colorCounter=1;
									document.getElementById('LT_'+code+"_"+counterNewArr[j]).value=eval(updateElVal);
								}
								else{
									document.getElementById('LT_'+code+"_"+counterNewArr[j]).value=eval(compValue);
								}
								//document.getElementById('LT_'+code+"_"+counterNewArr[j]).value=eval(compValue));
								if( eval(document.getElementById('ELCODE').value) == code )
								{									
									if( eval(document.getElementById('LT_'+code+"_"+counterNewArr[j]).value) >= eval(document.getElementById('maxELBal').value) )
									{
										document.getElementById('TRBAL_'+code+"_"+counterNewArr[j]).value= eval(document.getElementById('TRBAL_'+code+"_"+counterNewArr[j]).value) + eval( eval(document.getElementById('LT_'+code+"_"+counterNewArr[j]).value) - eval(document.getElementById('maxELBal').value) );
										document.getElementById('LT_'+code+"_"+counterNewArr[j]).value=eval(document.getElementById('maxELBal').value);
										//document.getElementById('add'+code).style.backgroundColor='#FBAC27';
										document.getElementById('LT_'+code+"_"+counterNewArr[j]).style.backgroundColor='#ABE9F9';
										//document.getElementById('add'+code).select();
										//document.getElementById('add'+code).focus();
										//return '<fmt:message key="HRMS.digitLengthEL" bundle="${alertLables}"/>';
										elBalExceed=1;
										colorCounter=1;
										columnCounter++;

										//For Error At Particular Page
										var errorCeil=eval(eval(counterNewArr[j])/eval(10));
										errorCeil=Math.ceil(eval(errorCeil));
										if(sortVar != errorCeil)
										{
												sortVar=errorCeil;
												errorArray[x]=errorCeil;
												x++;
										}
										
									}
								}
							}
							else
							{
								if( eval(compValue) > eval(document.getElementById('maxElBal').value) && code==eval(document.getElementById('ELCODE').value) )
								{
									alert('<fmt:message key="HRMS.digitLengthELExceed" bundle="${alertLables}"/> '+document.getElementById('maxELBal').value);
									document.getElementById('add'+code).focus();
									return null;
								}
								else
								{
									var updateElVal = elCodeCheck(document.getElementById('RETDATE_'+counterNewArr[j]).value,code);
									if(updateElVal!=-1){
										document.getElementById('LT_'+code+"_"+counterNewArr[j]).style.backgroundColor=color;
										colorCounter=1;
										document.getElementById('LT_'+code+"_"+counterNewArr[j]).value=eval(updateElVal);
									}
									else{
										document.getElementById('LT_'+code+"_"+counterNewArr[j]).value=compValue;
									}									
								}
								//document.getElementById('LT_'+code+"_"+counterNewArr[j]).value=compValue;	
								document.getElementById('ColumnCode'+code).value=eval(document.getElementById('ColumnCode'+code).value)+1;
								document.getElementById('frmSubmitButton').disabled=false;
							}
						}
						else
						{
							if(document.getElementById('operationId').value == 2)
							{
								if(codeAddOrSub == 1)
								{
									var updateElVal = -1;
								
									if(document.getElementById('RETDATE_'+counterNewArr[j])!=null){
										updateElVal = elCodeCheck(document.getElementById('RETDATE_'+counterNewArr[j]).value,code);
									}
								
									
									if(updateElVal!=-1){
										document.getElementById('LT_'+code+"_"+counterNewArr[j]).style.backgroundColor=color;
										colorCounter=1;
										document.getElementById('LT_'+code+"_"+counterNewArr[j]).value=Math.round(eval(eval(document.getElementById('LT_'+code+"_"+counterNewArr[j]).value)+eval(updateElVal))*10)/10;
									}
									else{
										document.getElementById('LT_'+code+"_"+counterNewArr[j]).value=eval(eval(document.getElementById('LT_'+code+"_"+counterNewArr[j]).value)+eval(compValue));
									}									
									if( eval(document.getElementById('ELCODE').value) == code )
									{
										if( eval(document.getElementById('LT_'+code+"_"+counterNewArr[j]).value) >= eval(document.getElementById('maxELBal').value) )
										{
											document.getElementById('TRBAL_'+code+"_"+counterNewArr[j]).value= eval(document.getElementById('TRBAL_'+code+"_"+counterNewArr[j]).value) + eval( eval(document.getElementById('LT_'+code+"_"+counterNewArr[j]).value) - eval(document.getElementById('maxELBal').value) );
											document.getElementById('LT_'+code+"_"+counterNewArr[j]).value=eval(document.getElementById('maxELBal').value);
											//document.getElementById('add'+code).style.backgroundColor='#FBAC27';
											document.getElementById('LT_'+code+"_"+counterNewArr[j]).style.backgroundColor='#ABE9F9';
											//document.getElementById('add'+code).select();
											//document.getElementById('add'+code).focus();
											//return '<fmt:message key="HRMS.digitLengthEL" bundle="${alertLables}"/>';
											elBalExceed=1;
											colorCounter=1;
											columnCounter++;
	
											//For Error At Particular Page
											var errorCeil=eval(eval(counterNewArr[j])/eval(10));
											errorCeil=Math.ceil(eval(errorCeil));
											if(sortVar != errorCeil)
											{
													sortVar=errorCeil;
													errorArray[x]=errorCeil;
													x++;
											}	
										}
									}
									else if( eval(document.getElementById('LT_'+code+"_"+counterNewArr[j]).value) >= 1000 )
									{
										//alert('<fmt:message key="HRMS.digitLength" bundle="${alertLables}"/>');
										var updateElVal = elCodeCheck(document.getElementById('RETDATE_'+counterNewArr[l]).value,code);
										if(updateElVal!=-1){
											document.getElementById('LT_'+code+"_"+counterNewArr[j]).style.backgroundColor=color;
											colorCounter=1;
											document.getElementById('LT_'+code+"_"+counterNewArr[j]).value=Math.round(eval(eval(document.getElementById('LT_'+code+"_"+counterNewArr[j]).value)-eval(updateElVal))*10)/10;
										}
										else{
											document.getElementById('LT_'+code+"_"+counterNewArr[j]).value=eval(eval(document.getElementById('LT_'+code+"_"+counterNewArr[j]).value)-eval(compValue));
										}										
										document.getElementById('LT_'+code+"_"+counterNewArr[j]).style.backgroundColor='#FBAC27';
										document.getElementById('add'+code).style.backgroundColor='#FBAC27';
										//document.getElementById('add'+code).select();
										//document.getElementById('add'+code).focus();
										//return '<fmt:message key="HRMS.digitLength" bundle="${alertLables}"/>';
										leaveExceed=1;
										colorCounter=1;
										columnCounter++;

										//For Error At Particular Page
										var errorCeil=eval(eval(counterNewArr[j])/eval(10));
										errorCeil=Math.ceil(eval(errorCeil));
										if(sortVar != errorCeil)
										{
											sortVar=errorCeil;
											errorArray[x]=errorCeil;
											x++;
										}
										
									}
								}
								else
								{
									var updateElVal = elCodeCheck(document.getElementById('RETDATE_'+counterNewArr[j]).value,code);
									if(updateElVal!=-1){
										document.getElementById('LT_'+code+"_"+counterNewArr[j]).style.backgroundColor=color;
										colorCounter=1;
										document.getElementById('LT_'+code+"_"+counterNewArr[j]).value=math.round(eval(eval(document.getElementById('LT_'+code+"_"+counterNewArr[j]).value)-eval(updateElVal))*10)/10;
									}
									else{
										document.getElementById('LT_'+code+"_"+counterNewArr[j]).value=eval(eval(document.getElementById('LT_'+code+"_"+counterNewArr[j]).value)-eval(compValue));
									}									
									if( eval(document.getElementById('LT_'+code+"_"+counterNewArr[j]).value) < 0 )
									{
										///alert('<fmt:message key="HRMS.digitLengthN" bundle="${alertLables}"/>');
										document.getElementById('LT_'+code+"_"+counterNewArr[j]).value=eval(eval(document.getElementById('LT_'+code+"_"+counterNewArr[j]).value)+eval(compValue));
										document.getElementById('LT_'+code+"_"+counterNewArr[j]).style.backgroundColor='#FBAC27';
										document.getElementById('add'+code).style.backgroundColor='#FBAC27';
										document.getElementById('add'+code).select();
										document.getElementById('add'+code).focus();
										return '<fmt:message key="HRMS.digitLengthN" bundle="${alertLables}"/>';
									}
								}				
								if( colorCounter!=1 )
								{
									document.getElementById('LT_'+code+"_"+counterNewArr[j]).style.backgroundColor='white';
									document.getElementById('add'+code).style.backgroundColor='white';
								}
								
							}	
							else
							{
								if( eval(compValue) > eval(document.getElementById('maxElBal').value) && code==eval(document.getElementById('ELCODE').value) )
								{
									alert('<fmt:message key="HRMS.digitLengthELExceed" bundle="${alertLables}"/> '+document.getElementById('maxELBal').value);
									document.getElementById('add'+code).focus();
									return null;
								}
								else
								{
									var updateElVal = elCodeCheck(document.getElementById('RETDATE_'+counterNewArr[j]).value,code);
									if(updateElVal!=-1){
										document.getElementById('LT_'+code+"_"+counterNewArr[j]).style.backgroundColor=color;
										colorCounter=1;
										document.getElementById('LT_'+code+"_"+counterNewArr[j]).value=updateElVal;
									}
									else{
										document.getElementById('LT_'+code+"_"+counterNewArr[j]).value=compValue;
									}
								}
								//document.getElementById('LT_'+code+"_"+counterNewArr[j]).value=compValue;
								document.getElementById('LT_'+code+"_"+counterNewArr[j]).style.backgroundColor='white';
								document.getElementById('add'+code).style.backgroundColor='white';
							}
						}
					}
      					}
				}
			}
			else
			{
				
				return;
			}
		}
	}
	}
	else
	{
		alert('<fmt:message key="HRMS.entValidNo" bundle="${alertLables}"/>');
		document.getElementById('add'+code).focus();
		return false;	
	}
}


</script>


<hdiits:form name="frmAdminLeaveMemberDtl" validate="true" method="post"
	encType="multipart/form-data"
	action="hdiits.htm?actionFlag=insertLeaveBalForNewUser">
	
	
	<span id="errorMsgSpan">
	</span>

	<table id="adminLeaveDtlTable" width="100%" align="center"
		style="border-collapse: collapse;" borderColor="BLACK" border="1">
		<fmt:formatDate var="currentFormatedDate" value="${currentDate}" pattern="dd/MM/yyyy" />
		<hdiits:hidden name="currDate" id="currDate" default="${currentFormatedDate}"></hdiits:hidden>
		<hdiits:hidden name="elMulFactor" id="elMulFactor" default="${elCredit}"/>		
		<hdiits:hidden name="hplMulFactor" id="hplMulFactor" default="${hplCredit}"/>
		<hdiits:hidden name="leaveTypeHPLcode" id="leaveTypeHPLcode" default="7"/>
		<hdiits:hidden name="maxELBal" id="maxELBal" default="${maxELBal}" />
		<hdiits:hidden name="leaveCodeArray" id="leaveCodeArray" />
		<c:set value="0" var="leaveCodeCounter" />
		<tr align="center">
			<td align="center" bgcolor="#C9DFFF" width="4%"><b><hdiits:caption
				captionid="HRMS.SrNo" bundle="${LeaveCaption}" /></b></td>
			<td align="center" bgcolor="#C9DFFF" width="14%"><b><hdiits:caption
				captionid="HRMS.Name" bundle="${LeaveCaption}" /></b></td>
			<c:forEach var="hrEssLeaveMst" items="${leaveCodeList}"
				varStatus="x">
				<hdiits:hidden id="ColumnCodeCounter${hrEssLeaveMst.leavecode}"
					name="ColumnCodeCounter${hrEssLeaveMst.leavecode}"
					default="${hrEssLeaveMst.leavecode}" />
				<td align="center" bgcolor="#C9DFFF"><hdiits:checkbox
					name='Chk_${hrEssLeaveMst.leavecode}'
					id='Chk_${hrEssLeaveMst.leavecode}' value="0"
					onclick="checkAll('Chk_${hrEssLeaveMst.leavecode}',document.getElementById('counterForUser').value);" />
				</td>
				<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption
					captionid="HRMS.${hrEssLeaveMst.shortname}"
					bundle="${LeaveCaption}" /></b></td>

				<c:if test="${operationId == 2}">
					<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption
						captionid="HRMS.updatedDate" bundle="${LeaveCaption}" /></b></td>
				</c:if>
			</c:forEach>

			<c:if test="${operationId == 1}">
				<td align="center" bgcolor="#C9DFFF" width="13.5%"><b><hdiits:caption
					captionid="HRMS.crtDate" bundle="${LeaveCaption}" /></b></td>
				<td align="center" bgcolor="#C9DFFF" width="13.5%"><b><hdiits:caption
					captionid="HRMS.lastModified" bundle="${LeaveCaption}" /></b></td>
			</c:if>
			<td align="center" bgcolor="#C9DFFF"><b>	<hdiits:caption captionid="HRMS.retireDate" bundle="${LeaveCaption}"/></b></td>
		</tr>
		<c:if test="${operationId == 1}">
			<hdiits:hidden id="operationId" name="operationId"
				default="${operationId}" />
			<c:set value="1" var="counter" />
			<hdiits:hidden id="counter" name="counter" default="1" />
			<hdiits:hidden id="counterForUser" name="counterForUser" />


			<c:forEach var="userIdListInJsp" items="${userIdList}">

				<tr>
					<td align="center">${counter}</td>
					<td align="center"><c:set value="1" var="nameCounter" /> 
					<c:forEach
						var="userName" items="${userNameList}">

						<c:if test="${nameCounter == counter}">
			 					${userName}
			 				</c:if>
						<c:set value="${nameCounter+1}" var="nameCounter" />
					</c:forEach></td>


					<c:set value="1" var="balCounter" />
					<c:forEach var="codeForNewOrExistingUser" items="${newOrExistingUserList}">
						<c:if test="${balCounter == counter}">
						  <c:if test="${userIdListInJsp.retirementDate gt currentDate || empty userIdListInJsp.retirementDate}">
							<c:if test="${codeForNewOrExistingUser == 0}">
								<c:forEach var="hrEssLeaveMst" items="${leaveCodeList}" varStatus="x">
								<td align="center">
									<hdiits:checkbox name='Chk_${hrEssLeaveMst.leavecode}_${counter}'
									id='Chk_${hrEssLeaveMst.leavecode}_${counter}'
									onclick="checkMain('Chk_${hrEssLeaveMst.leavecode}');"
									value="0" />
								</td>
								<td align="center">
									<hdiits:number floatAllowed="false" name='LT_${hrEssLeaveMst.leavecode}_${counter}'
									id='LT_${hrEssLeaveMst.leavecode}_${counter}' caption="enter value" maxlength="3" size="2"
									onblur="validateFloatNewUser(this,${hrEssLeaveMst.leavecode});" />

									<hdiits:hidden name='TRBAL_${hrEssLeaveMst.leavecode}_${counter}'
									id='TRBAL_${hrEssLeaveMst.leavecode}_${counter}'
									default="0.0" />
								</td>

								</c:forEach>

								<td align="center">
									<fmt:formatDate var="currentFormatedDate" value="${currentDate}"
									pattern="dd/MM/yyyy" /> ${currentFormatedDate}
								</td>

								<td align="center">
									------
								</td>								
								<hdiits:hidden name='userId${counter}' id='userId${counter}'
								default="${userIdListInJsp.userID}" />
								<script>
										document.getElementById('counterForUser').value=document.getElementById('counter').value+"~"+document.getElementById('counterForUser').value;
								</script>
								<td>
									<fmt:formatDate var="retirementDate"
									value="${userIdListInJsp.retirementDate}"
									pattern="dd/MM/yyyy" />
			 						${retirementDate}
									<hdiits:hidden name='RETDATE_${counter}'
									id='RETDATE_${counter}'
									default="${retirementDate}" />
								</td>
							</c:if>
						  </c:if>

						<c:if test="${codeForNewOrExistingUser == 1 && (userIdListInJsp.retirementDate gt currentDate || empty userIdListInJsp.retirementDate)}">
							<td colspan="20" align="center">
								<hdiits:caption	captionid="HRMS.leaveBalExist" bundle="${LeaveCaption}" />
							</td>
						</c:if>
							
						  <c:if test="${userIdListInJsp.retirementDate lt currentDate}">
							<td colspan="20" align="center">
									<hdiits:caption captionid="HRMS.retiredUser" bundle="${LeaveCaption}"/>
							</td>
						  </c:if>	
					</c:if>
					<c:set value="${balCounter+1}" var="balCounter" />
				</c:forEach>
			</tr>

				<c:set value="${counter+1}" var="counter" />
				<script>
			 		document.getElementById('counter').value=eval(eval(document.getElementById('counter').value)+eval(1));
			 	</script>
			</c:forEach>
			<c:forEach var="hrEssLeaveMst" items="${leaveCodeList}">
				<hdiits:hidden name='ColumnCode${hrEssLeaveMst.leavecode}'
					id='ColumnCode${hrEssLeaveMst.leavecode}'
					default="${hrEssLeaveMst.leavecode}" />
				<script>
					document.getElementById('leaveCodeArray').value=${hrEssLeaveMst.leavecode}+"~"+document.getElementById('leaveCodeArray').value;
				</script>
			</c:forEach>

		</c:if>

		<!--  Added for Updating The Existing One -->
		<c:if test="${operationId == 2}">
			<hdiits:hidden id="operationId" name="operationId"
				default="${operationId}" />
			<c:set value="1" var="counter" />
			<hdiits:hidden id="counter" name="counter" default="1" />
			<hdiits:hidden id="counterForUser" name="counterForUser" />
		
			<c:forEach var="userIdListInJsp" items="${userLeaveBalInfoList}">
				<tr>
					<td align="center">${counter}</td>
					<td align="center"><c:set value="1" var="nameCounter" /> <c:forEach
						var="userName" items="${userNameList}">
						<c:if test="${nameCounter == counter}">
			 					${userName}
			 				</c:if>
						<c:set value="${nameCounter+1}" var="nameCounter" />
					</c:forEach></td>
					<c:set value="1" var="upBalCounter" />
					<c:set value="0" var="checking" />
				
				
					<c:forEach var="leaveTypeCustomVOList"
						items="${userIdListInJsp.leaveTypeCustomVO}">
				<c:if test="${userIdListInJsp.retirementDate gt currentDate}">
						<c:if test="${not empty leaveTypeCustomVOList}">
							<td align="center">
								<hdiits:checkbox name='Chk_${leaveTypeCustomVOList.leaveCode}_${counter}'
								id='Chk_${leaveTypeCustomVOList.leaveCode}_${counter}'
								onclick="checkMain('Chk_${leaveTypeCustomVOList.leaveCode}');" value="0" />
							</td>
							<td align="center">
								<hdiits:number floatAllowed="false" name='LT_${leaveTypeCustomVOList.leaveCode}_${counter}'
								id='LT_${leaveTypeCustomVOList.leaveCode}_${counter}' maxlength="5" size="2"
								default="${leaveTypeCustomVOList.leaveBal}" mandatory="false"
								onblur="validateFloatNewUser(this,${leaveTypeCustomVOList.leaveCode});" />

							<script>
							 	document.getElementById('LT_'+${leaveTypeCustomVOList.leaveCode}+"_"+${counter}).setAttribute("onkeydown",function (){getPrevLeaveBalValue(this)});
							 </script> 
								 <hdiits:hidden	name='HIDDEN_LT_${leaveTypeCustomVOList.leaveCode}_${counter}'
								 id='LT_${leaveTypeCustomVOList.leaveCode}_${counter}' default="${leaveTypeCustomVOList.leaveBal}" /> 
								 <hdiits:hidden name='TRBAL_${leaveTypeCustomVOList.leaveCode}_${counter}'
								 id='TRBAL_${leaveTypeCustomVOList.leaveCode}_${counter}' default="${leaveTypeCustomVOList.transBal}" /> 
								 <hdiits:hidden name='HIDDEN_TRBAL_${leaveTypeCustomVOList.leaveCode}_${counter}'
								 id='HIDDEN_TRBAL_${leaveTypeCustomVOList.leaveCode}_${counter}' default="${leaveTypeCustomVOList.transBal}" />
							 </td>
							<td align="center" width="8%"><c:if
								test="${not empty leaveTypeCustomVOList.leaveBal}">

								<fmt:formatDate var="lastModifiedDate"
									value="${leaveTypeCustomVOList.lastModifiedDate}"
									pattern="dd/MM/yyyy" />
								<c:if test="${empty lastModifiedDate}">
									<fmt:formatDate var="createdDate"
										value="${leaveTypeCustomVOList.createdDate}"
										pattern="dd/MM/yyyy" />
								 		${createdDate}
							 		</c:if>
								<c:if test="${not empty lastModifiedDate}">	
								 		${lastModifiedDate}
									</c:if>
							</c:if> <c:if test="${empty leaveTypeCustomVOList.leaveBal}">
								<fmt:formatDate var="currentFormatedDate" value="${currentDate}"
									pattern="dd/MM/yyyy" />
							 		${currentFormatedDate}
								</c:if></td>
							<script>
								document.getElementById('ColumnCodeCounter'+${leaveTypeCustomVOList.leaveCode}).value=eval(document.getElementById('ColumnCodeCounter'+${leaveTypeCustomVOList.leaveCode}).value)+1;
							</script>
						</c:if>
					  </c:if>


						<c:set value="1" var="checking" />
						<c:set value="${leaveCodeCounter+1}" var="leaveCodeCounter" />
					</c:forEach>
				
					<c:if test="${checking == 1 && userIdListInJsp.retirementDate gt currentDate}">	
					<td align="center"><c:set value="1" var="nameCounter" /> 
					<c:forEach
						var="userName" items="${userIdList}">

						<c:if test="${nameCounter == counter}">
							<fmt:formatDate var="retirementDate"
									value="${userName.retirementDate}"
									pattern="dd/MM/yyyy" />
			 					${retirementDate}
			 				<hdiits:hidden name='RETDATE_${counter}'
									id='RETDATE_${counter}'
									default="${retirementDate}" />	
			 				</c:if>
						<c:set value="${nameCounter+1}" var="nameCounter" />
					</c:forEach></td>
					</c:if>
					<c:if test="${checking == 1 && userIdListInJsp.retirementDate gt currentDate}">
						<hdiits:hidden name='userId${counter}' id='userId${counter}'
							default="${userIdListInJsp.userID}" />
						<script>
							document.getElementById('counterForUser').value=document.getElementById('counter').value+"~"+document.getElementById('counterForUser').value;
						</script>
					</c:if>
					<c:if test="${checking == 0 && userIdListInJsp.retirementDate gt currentDate}">
						<td align="center" colspan="20"><hdiits:caption
							captionid="HRMS.leaveBalNotExist" bundle="${LeaveCaption}" /></td>
					</c:if>
						
					<c:if test="${userIdListInJsp.retirementDate lt currentDate}">
						<td colspan="20" align="center">
							<hdiits:caption captionid="HRMS.retiredUser" bundle="${LeaveCaption}"/>
						</td>
				  	</c:if>	

				</tr>
				<c:set value="${counter+1}" var="counter" />
				<script>
			 		document.getElementById('counter').value=eval(eval(document.getElementById('counter').value)+eval(1));
			 	</script>
				<c:forEach var="hrEssLeaveMst" items="${leaveCodeList}">
					<hdiits:hidden name='ColumnCode${hrEssLeaveMst.leavecode}'
						id='ColumnCode${hrEssLeaveMst.leavecode}'
						default="${hrEssLeaveMst.leavecode}" />
					<script>
					document.getElementById('ColumnCode'+${hrEssLeaveMst.leavecode}).value=document.getElementById('ColumnCodeCounter'+${hrEssLeaveMst.leavecode}).value;
				</script>
				</c:forEach>
				<!-- END of Update Existing -->

			</c:forEach>

			<c:forEach var="hrEssLeaveMst" items="${leaveCodeList}">
				<hdiits:hidden name='ColumnCode${hrEssLeaveMst.leavecode}'
					id='ColumnCode${hrEssLeaveMst.leavecode}'
					default="${hrEssLeaveMst.leavecode}" />
				<script>
				document.getElementById('leaveCodeArray').value=${hrEssLeaveMst.leavecode}+"~"+document.getElementById('leaveCodeArray').value;
			</script>
			</c:forEach>
		</c:if>

	</table>
	<div id="pageNavPosition"></div>
	<script>
	var pager=new Pager('adminLeaveDtlTable', 10,this); 
	        pager.init();
	        pager.showPageNav('pager', 'pageNavPosition'); 
	        pager.showPage(1);					
	
	</script>

	<table id="addBalTable" width="100%" align="center"
		style="border-collapse: collapse;" borderColor="BLACK" border="1">

		<tr align="center">
			<td align="center" width="100%"><hdiits:formSubmitButton
				name="frmSubmitButton" type="button" id="frmSubmitButton"
				captionid="HRMS.Submit" bundle="${LeaveCaption}" readonly="false" />
			<script>
	 			 	if(${operationId} == 1)
	 			 		document.getElementById('frmSubmitButton').disabled=true;
	 			 	else
	 			 		document.getElementById('frmSubmitButton').disabled=false;
	 		</script> 
	 			<hdiits:button type="button" name="resetCheckedBtn"
				captionid="HRMS.reset" bundle="${LeaveCaption}"
				onclick="resetChecked(document.getElementById('counterForUser').value,document.getElementById('leaveCodeArray').value);" />

			<c:if test="${operationId == 1}">
				<hdiits:button name="resetAllBtn" type="button"
					captionid="HRMS.resetAll" bundle="${LeaveCaption}"
					onclick="resetAll(document.getElementById('counterForUser').value,document.getElementById('leaveCodeArray').value);" />
			</c:if> <c:if test="${operationId == 2}">
				<hdiits:button name="resetAllBtn" type="button"
					captionid="HRMS.resetAll" bundle="${LeaveCaption}"
					onclick="resetPage();" />
			</c:if>
				<hdiits:button name="back" type="button"
					captionid="HRMS.back" bundle="${LeaveCaption}"
					onclick="goBack();" />
			</td>
		</tr>

	</table>



	<br />

	<table id="addBalTable" width="100%" align="center"
		style="border-collapse: collapse; " borderColor="BLACK" border="1">

		<tr align="center" colspan="10">

			<td align="center" colspan="2" width="18%"><hdiits:caption
				captionid="HRMS.addBal" bundle="${LeaveCaption}" /></td>

			<c:forEach var="hrEssLeaveMst" items="${leaveCodeList}">
				<td align="center" colspan="1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<c:if test="${hrEssLeaveMst.leavecode == 5 }">
					<hdiits:hidden name="ELCODE" id="ELCODE"
						default="${hrEssLeaveMst.leavecode}" />
				</c:if>
				<td align="center" width="12%" colspan="1"><hdiits:caption
					captionid="HRMS.${hrEssLeaveMst.shortname}"
					bundle="${LeaveCaption}" />&nbsp;&nbsp;&nbsp; <c:if
					test="${operationId==1}">
					<hdiits:number floatAllowed="false"
						name='add${hrEssLeaveMst.leavecode}'
						id='add${hrEssLeaveMst.leavecode}' maxlength="3" size="4"
						onblur="addBalToAll(document.getElementById('add'+'${hrEssLeaveMst.leavecode}').value,document.getElementById('counterForUser').value,'${hrEssLeaveMst.leavecode}');" />
				</c:if> <c:if test="${operationId==2}">
					<hdiits:number floatAllowed="false"
						name='add${hrEssLeaveMst.leavecode}'
						id='add${hrEssLeaveMst.leavecode}' maxlength="3" size="4"
						onblur="validateFloat(this);" />
				</c:if></td>
			</c:forEach>

			<td align="center" width="27%" colspan="2"></td>

		</tr>
	</table>
	<br />
	<c:if test="${operationId == 2}">
		<table id="addBalButtonTable" width="100%" align="center"
			style="border-collapse: collapse; " borderColor="BLACK" border="1">
			<tr align="center">
				<td align="center"><hdiits:button type="button"
					name="addLeaveBalance" id="addLeaveBalance"
					onclick="modifyLeave(1);" captionid="HRMS.Add1"
					bundle="${LeaveCaption}" /></td>
				<!--<td align="left" >
		 			<hdiits:button type="button" name="subtractLeaveBalance" id="subtractLeaveBalance" onclick="modifyLeave(2);" captionid="HRMS.Subtract" bundle="${LeaveCaption}"/>
		 		</td>
		 		
		 	-->
			</tr>

		</table>
</c:if>
	<table id="msgTable">
		
		<tr>
		</tr>
		
		<tr>
		</tr>
		
		<tr>
		</tr>
		
		<tr>
			<td>
			<div style="width: 20px; background-color:#FBAC27; "> 
			</div>
			</td>
			<td>
			<hdiits:caption captionid="HRMS.digitLength" bundle="${LeaveCaption}"/>
			</td>
		</tr>
		
		<tr>
			<td>
				<div style="width: 20px; background-color:#ABE9F9; "> 
				</div>
			</td>
			<td>
				<hdiits:caption captionid="HRMS.digitLengthELRule" bundle="${LeaveCaption}"/>
				&nbsp;:&nbsp;&nbsp;${maxELBal}
			</td>
		
		</tr>
		<tr>
			<td>
				<div style="width: 20px; background-color:#C6AEC7; "> 
				</div>
			</td>
			<td>
				<hdiits:caption captionid="HRMS.retirementBalRule" bundle="${LeaveCaption}"/>
			</td>
		</tr>		
	</table>
	
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
