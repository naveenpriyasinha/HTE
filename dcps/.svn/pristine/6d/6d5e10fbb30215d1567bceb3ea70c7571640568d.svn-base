<%
try {
%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="//WEB-INF/jsp/core/include.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.hr.sheetremarks.SheetRemarks_AlertMessages" var="commonLables1" scope="request"/>

<script type="text/javascript"   
	src="common/script/tagLibValidation.js">
</script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript"  
    src="script/common/commonfunctions.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>

<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
	
<fmt:setBundle basename="resources.hr.sheetremarks.SheetRemarks" var="commonLables" scope="request"/>
<script type="text/javascript">

function doSet(){
var form=document.forms[0];
	document.write(form.select1.selectedIndex);
	
	
}
<%
	int temp=0;
	int avg=0;
	
	
%>

</script>

<script><!--


var counter=0;

var number=0;
var array1=new Array(30);


function getInfo(q,count)
{
	    
	    var no=count;
	    temp=no;
	     
	  
	    if(q.value=='-1'){
		var z=document.getElementById("selectValue"+no);	
		var x=document.getElementById("Average");	
       	number=no;
       	array1[no]=0;
       	if(counter>0)
       	{
       	counter=counter-1;
       	}
       	if(counter==0)
       	{
       	counter=1;
       	}
       	
       	var sumAll=0;
        					
        					for(var i=0;i<30;i++)
        					{
        						sumAll=parseInt(sumAll)+parseInt(array1[i]);
        					
        					}
       	x.value=parseInt(sumAll)/(counter);
		z.value='';            	
		            	
		return;}
			try{   
    			xmlHttp=new XMLHttpRequest();    
	    	}
			catch (e)
			{    // Internet Explorer    
					try{
      					xmlHttp=new 
                        ActiveXObject("Msxml2.XMLHTTP");      
      				}
		    		catch (e){
		          		try
        		  		{
                	           	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
        		  		}
				      	catch (e)
				      	{
			           	   	  //alert("Your browser does not support AJAX!");
			           	   	  alert('<fmt:message  bundle="${commonLables}" key="HR.ESS.AJAX"/>');        
			            	  return false;        
			      		}
			 		}			 
        	}	     
        	
			var url = "hrms.htm?actionFlag=SheetRemarksAjax&flag=f&id="+q.value;    
			xmlHttp.open("POST", encodeURI(url) , true);			
			xmlHttp.onreadystatechange = processResponse;
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));
}

	
function processResponse()
{
	if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{        			
				
					
				
						var textId;						
		            	var z=document.getElementById("selectValue"+temp);	
		             	var x=document.getElementById("Average");	
		            	if(counter==0)
					{
						x.value=0;
						for(var i=0;i<30;i++)
						{
							array1[i]=0;
						}
					}
		            	                    		            			            	
				    	var xmlStr = xmlHttp.responseText;
				    	
				    	var XMLDoc=getDOMFromXML(xmlStr);   // xmlHttp.responseXML.documentElement;						    			    	
				    	
				    	var SubQuaStr = XMLDoc.getElementsByTagName("LookupDesc");
				    			    	 				    	     		     							
						for ( var i = 0 ; i < SubQuaStr.length ; i++ )
	     				{	     		     								
	     				    value=SubQuaStr[i].childNodes[0].text;	
	     				 
	     				 
	     				    
	     					   
	     					z.value=value; 
	     					if(array1[temp]==0)
        					{
        					counter=counter+1;
        					}
	     					array1[temp]=value;    	
        				
        					var sumAll=0;
        					
        					for(var i=0;i<30;i++)
        					{
        						sumAll=parseInt(sumAll)+parseInt(array1[i]);
        					
        					}
        				
		          			var tempValue=parseInt(sumAll)/(counter);
		          			
		          			var S = String(tempValue);
		          			//J = S.indexOf(".")
		          			var J=eval(S.charAt(4));
		          			//alert("J" +J);
		          			if(J!=null)
		          			{
		          			//alert("inside if1");
		          			if(J>=5)
		          			{
		          			//alert("inside if2");
		          			var A=S.charAt(0);
		          			var B=S.charAt(1);
		          			//alert("B" +B);
		          			var C=S.charAt(2);
		          			//alert("C" +C);
		          			var D=eval(S.charAt(3))+1;
		          			//alert("D" +D);
		          			tempValue=A+B+C+D;
		          			
		          			/*
		          			alert("A" +A);
		          			var B=parseInt(S.charAt(2));
		          			alert("B" +B);
		          			var C=parseInt(S.charAt(3));
		          			alert("C" +C);
		          			var b=0.1*parseInt(B);
		          			alert("b" +b);
		          			var c=parseInt(0.01*parseInt(C))+0.01;
		          			alert("c" +c);
		          			tempValue=parseInt(A)+parseInt(b)+parseInt(c);
		          			alert("end of if2");
		          			*/
		          			
		          			}
		          			if(J<5)
		          			var A=S.charAt(0);
		          			var B=S.charAt(1);
		          			var C=S.charAt(2);
		          			var D=eval(S.charAt(3));
		          			tempValue=A+B+C+D;
		          			} 
		          			
		          			x.value=tempValue;
		          			
		          			//x.value=number_format(2, round(tempValue,2))
		          			
		          		
		          			
	     						
	           			}
	           			
	           		

	           			// code to populate more fields
	           			var removeRow = XMLDoc.getElementsByTagName('RemoveRow');
	           			
	           			
	           			
				}
				else 
				{  			
					//alert("ERROR");	
					alert('<fmt:message  bundle="${commonLables}" key="HR.ESS.ERROR"/>');				
				}
			}
			
			
			
}





var a=new Array(30);
var b=new Array(30);
function checkval(total)
{
//var total=tot-1;

var evalu = document.getElementById("Evaluation");
var repr = document.getElementById("Representation");
var sugg = document.getElementById("Suggestion");

if(eval(sugg.selectedIndex) == 0)
{
//alert("it is mandatory to fill all fields");
  alert('<fmt:message  bundle="${commonLables}" key="HR.ESS.SUGG"/>');

//document.getElementById('Suggestion').select();
setFocusSelection('Suggestion');
document.getElementById('Suggestion').focus();

return;
}
else if((evalu.value)=='')
{
alert('<fmt:message  bundle="${commonLables}" key="HR.ESS.ab"/>');
document.getElementById('Evaluation').select();
setFocusSelection('Evaluation');
document.getElementById('Evaluation').focus();
return;
}
else if((repr.value)=='' )
{
alert('<fmt:message  bundle="${commonLables}" key="HR.ESS.ab"/>');
document.getElementById('Representation').select();
setFocusSelection('Representation');
document.getElementById('Representation').focus();
return;
}

for(var i=0;i<total;i++)
{

a[i]= document.getElementById("select"+i);
b[i]=document.getElementById("txtarea"+i)

if((eval(a[i].selectedIndex) == 0))
{
//alert("it is mandatory to fill all textareas or selections ");
  alert('<fmt:message  bundle="${commonLables}" key="HR.ESS.SUGG"/>');
//document.getElementById("select"+i).select();
setFocusSelection("select"+i);
document.getElementById("select"+i).focus();
//document.getElementById("txtarea"+i).select();
//document.getElementById("txtarea"+i).focus();

return;
}
else if(((b[i].value) == '') )
{
alert('<fmt:message  bundle="${commonLables}" key="HR.ESS.ab"/>');
document.getElementById("txtarea"+i).select();
setFocusSelection("txtarea"+i);
document.getElementById("txtarea"+i).focus();
return;
}

}
	document.sheetremarkentry.method="POST";

	document.sheetremarkentry.action="./hrms.htm?actionFlag=SheetRemarksSubmit";
	showProgressbar('Submitting Request...<br>Please wait...');
	document.sheetremarkentry.submit();

}

function submit3()
{
	//alert("inside submit function");
	document.sheetremarkentry.method="POST";
	document.sheetremarkentry.action="./hrms.htm?actionFlag=SheetRemarksEmpSearch";
	showProgressbar('Opening Search Page...<br>Please wait...');
	document.sheetremarkentry.submit();
}

function submit4()
{
	//alert("inside submit function");
	document.sheetremarkentry.method="POST";

// history.go(-1);
	document.sheetremarkentry.action="./hrms.htm?actionFlag=SheetRemarksEmpSearch";
	showProgressbar('Opening Search Page...<br>Please wait...');
	document.sheetremarkentry.submit();
}

function specialChar(txtarea)
{
	
	var len,str,str1,i;
	len=txtarea.value.length;
	str=txtarea.value;
	
	//add or remove characters into this string to be checked 
	str1="!@#$%^&*()<>?/~`-=+{}[]|\:;',."
	for(i=0;i<len;i++)
	{
		
		if((str1.indexOf(str.charAt(i)))!=-1)
		{
		
		
		alert("Special characters are not allowed");
			//alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.SpecialCharsNotAllowed"/>');
			setFocusSelection(txtarea.name);
			txtarea.focus();
			return;
		}
		
	}

	return;
}





--></script>
<fmt:setBundle basename="resources.hr.EssCommonLables" var="lables"
	scope="request" />



<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="elementOrderList" value="${resValue.elementOrderList}"></c:set>	
<c:set var="labelValue" value="${resValue.elementLabelList}"></c:set>
<c:set var="codeofelementLabelList" value="${resValue.codeofelementLabelList}"></c:set>
<c:set var="remarkValue" value="${resValue.remarkOption}"></c:set>	
<c:set var="suggestionList" value="${resValue.suggestionList}"></c:set>	
<c:set var="selectedUserId" value="${resValue.selectedUserId}"></c:set>
<c:set var="year" value="${resValue.year}"></c:set>
<c:set var="year1" value="${resValue.year2}"></c:set>

<c:set var="EmpDetVO" value="${resValue.empInfo}" />
<c:set var="EmpDetVO1" value="${resValue.empInfo1}" />


<c:set var="recordFlag" value="${resValue.recordFlag}"></c:set>

<c:set var="trnDataList" value="${resValue.trnDataList}"></c:set>
<c:set var="suggestion" value="${resValue.suggestion}"></c:set>
<c:set var="seqList" value="${resValue.seqList}"></c:set>
<c:set var="selectlookupList" value="${resValue.selectlookupList}"></c:set>
<c:set var="reportFlag" value="${resValue.reportFlag}"></c:set>


<c:set var="labelValue1" value="${resValue.elementLabelprevList}"></c:set>
<c:set var="suggestionPrev" value="${resValue.suggestionPrev}"></c:set>
<c:set var="prevselectlookupList" value="${resValue.prevselectlookupList}"></c:set>
<c:set var="seqPrevDataList" value="${resValue.seqPrevDataList}"></c:set>
<c:set var="trnPrevDataList" value="${resValue.trnPrevDataList}"></c:set>
<c:set var="prevRecord" value="${resValue.prevRecord}"></c:set>

<c:set var="previousYearFlag" value="${resValue.previousYearFlag}"></c:set>




<fmt:formatDate value="${resValue.sysdate}" pattern="dd/MM/yyyy"
	dateStyle="medium" var="date" />
<fmt:formatDate value="${resValue.sysdate}" pattern="HH:mm"
	dateStyle="medium" var="time" />
<c:set var="date" value="${date}" ></c:set>
<c:set var="time" value="${time}" ></c:set>	





<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1">
		<b> 
			<hdiits:caption captionid="HR.ESS.Sheet" bundle="${commonLables}" id="Sheet" />
		</b>
		</a>
		</li>
	</ul>
</div>
<hdiits:form name="sheetremarkentry" validate="true"   encType="multipart/form-data" >
<div class="halftabcontentstyle">
	 <div id="tcontent1" class="tabcontent" tabno="0">

<table width="100%">
<hdiits:hidden name="reportFlag" id="reportFlag" default="${reportFlag}"></hdiits:hidden>
			<tr>
			<td class="fieldLabel" colspan="4"><%@ include
				file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%></td>
		</tr>
</table>  



	
	<c:if test="${previousYearFlag eq 0 }">	
	<c:set var="yearLookup" value="${resValue.yearLookup}"></c:set>	
		
<hdiits:fieldGroup bundle="${commonLables}"  id="PreviousYearDetails" expandable="true" collapseOnLoad="true" titleCaptionId="HR.ESS.SheetRemarksPrevious">

<table>
	<tr colspan="4">
		
		
		<td>
			<hdiits:caption captionid="HR.ESS.Year" bundle="${commonLables}" id="Year" />:
		</td>
		
		<td>
			<c:out value="${yearLookup.lookupDesc}"></c:out>
		</td>
		<td width="10%">
		</td>
	</tr>
</table>


<table id="previousYear" class="tabtable">

	<c:if test="${prevRecord eq 1 }">

            	<c:set var="y" value= "0"/>
            	
			<c:forEach var="labels1" items="${labelValue1}" varStatus="x">
			<tr colspan="5">
		<c:set var="prevselect" value= "${prevselectlookupList[x.index]}"/>
		<c:set var="prevtrn" value="${trnPrevDataList[x.index]}"/>
		<c:set var="z" value= "${codeofelementLabelList[x.index]}"/>
		
	
  				<td width="10%">
  					<c:out value="${labels1}"/>: 
  				
  				</td>
  				
    			<td width="10%">
    				<c:out value="${prevselect.lookupDesc}"/>
    				<c:set var="count" value="${labelsCount.count}"/>
 				 	
				</td>

				
				<td width="10%">
				<c:out value="${prevtrn.ratingValue}"/>
				</td>

				<td width="10%">

					<hdiits:caption captionid="HR.ESS.Remarks" bundle="${commonLables}" id="Remarks" />:
				</td>

				<td width="10%">

						
					<c:out value="${prevtrn.sheetrFieldTxtarea}"/>
					
					<hdiits:hidden name="Code${y}" default="${z}"/>

				</td>
				</tr>
			<c:set var="y" value="${y+1}"/>
			

			</c:forEach>
			
			<tr colspan="2">
      
      		<td width="10%"><hdiits:caption captionid="HR.ESS.Evaluationwork" bundle="${commonLables}" id="Evaluation" />: </td>
     			<td>
      		 <c:out value="${seqPrevDataList.evaluationOfWork}"></c:out>
     	</td>
      		</tr>
      
     		 <tr colspan="2">
      
      		<td width="10%"><hdiits:caption captionid="HR.ESS.Representation" bundle="${commonLables}" id="Representation" />: </td>
      		<td>
      		<c:out value="${seqPrevDataList.representation}"></c:out>
     
      		</td>
      		</tr>
      
      
      <tr colspan="4">
      
      <td width="10%"></td>
      <td width="10%"></td>
       
       <td width="10%" align="center"><hdiits:caption captionid="HR.ESS.Average" bundle="${commonLables}" id="Average" />: </td>
     
       
      <td align="center">
      <c:out value="${seqPrevDataList.averageRating}"></c:out>
      </td>
       
      
      </tr>
			
      
      
      <tr colspan="2"><td width="10%"><hdiits:caption captionid="HR.ESS.Specific" bundle="${commonLables}" id="Specific" />: </td><td>
       <c:out value="${suggestionPrev.lookupDesc}"></c:out>
       </td>
      </tr>
	</c:if>	
			
	<c:if test="${prevRecord eq 0 }">
		<tr>
			<hdiits:caption captionid="HR.ESS.NORecord" bundle="${commonLables}" id="NORecord" />
		</tr>
	</c:if>
</table>
	
</hdiits:fieldGroup>	
	</c:if>	
		


<c:if test="${recordFlag eq 0}">
<hdiits:fieldGroup bundle="${commonLables}"  id="SheetRemarksGrp" expandable="true" titleCaptionId="HR.ESS.SheetRemarks">
<table class="tabtable">

		<tr colspan="2">

		<td width="10%">
			<hdiits:caption captionid="HR.ESS.Year" bundle="${commonLables}" id="Year" />:
			<c:out value="${year1}"/>
		</td>
		
		
		<td width="10%" >
			<hdiits:caption captionid="HR.ESS.Date" bundle="${commonLables}" id="Date" />:<c:out value="${date}"/>
		</td>
		
		</tr>
		
</table>		

<table class="tabtable">
					<c:set var="y" value= "0"/>
					<c:set var="tabCount" value= "1"/>
					
			<tr colspan="4">
			<td width="25%"></td>
			<td width="25%"></td>
			<td width="25%"></td>
			<td width="25%">

			<hdiits:caption captionid="HR.ESS.Remarks" bundle="${commonLables}" id="Remarks" />:
			</td>
			
		
			
			
			
			</tr>		
					
					
			<c:forEach var="labels" items="${labelValue}" varStatus="x">
			<c:set var="z" value="${codeofelementLabelList[x.index]}"/>
			<tr colspan="4">
			
		
	
  				<td width="25%"><c:out value="${labels}"/>: </td>
    			<td width="25%">
    			<c:set var="count" value="${labelsCount.count}"/>
 				 <hdiits:select caption="${labels}" name="select${y}" id="select${y}" mandatory="true" validation="sel.isrequired" sort="false" onchange="getInfo(this,${y});" tabindex="${tabCount}"> 
				<option value="-1"><fmt:message key="HR.ESS.Select" bundle="${commonLables}"/></option>
			
			
				<c:forEach var="remark" items="${remarkValue}" varStatus="x">
			
				<option value="<c:out value="${remark.orderNo}" />">
			
			
					<c:out value="${remark.lookupDesc}"/>
			
				</option>
				
				</c:forEach>
			
				
			</hdiits:select>
			<c:set var="tabCount" value="${tabCount+1}"/>	
			</td>

			<td>
				<hdiits:text maxlength="10" size="5" id="selectValue${y}"  name="selectValue${y}"  validation="txt.isrequired" caption="${labels}"  readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"/>
			</td>

			
			<td>

			<hdiits:textarea mandatory="true" rows="2" cols="50"  
                                    name="txtarea${y}" tabindex="${tabCount}" id="txtarea${y}" 
                                    validation ="txt.isrequired"   captionid="HR.ESS.Remarks" onblur="specialChar(this);"
                                    bundle="${commonLables}"/>

			<hdiits:hidden name="Code${y}" default="${z}"/>


			</td>
			</tr>
			<c:set var="y" value="${y+1}"/>
			<c:set var="tabCount" value="${tabCount+1}"/>
		

			</c:forEach>



				
     	 <hdiits:hidden name="Counter" default="${y}"/>
     		<hdiits:hidden name="selectedUserId" default="${selectedUserId}"/>
       	<hdiits:hidden name="Year" default="${year}"/>
     
      	 <tr colspan="2">
      
      		<td width="25%"><hdiits:caption captionid="HR.ESS.Evaluationwork" bundle="${commonLables}" id="Evaluation" />: </td>
     
      		<td width="25%"><hdiits:textarea mandatory="true" rows="2" cols="50"  
                                    name="Evaluation" tabindex="${tabCount+1}" id="Evaluation" 
                                    validation ="txt.isrequired"   captionid="HR.ESS.Evaluationwork" 
                                     bundle="${commonLables}" onblur="specialChar(this);" />
     	 </td>
     	 			<c:set var="tabCount" value="${tabCount+1}"/>
      		</tr>
      
     		 <tr colspan="2">
      
      		<td width="25%"><hdiits:caption captionid="HR.ESS.Representation" bundle="${commonLables}" id="Representation" />: </td>
     
      		<td width="25%"><hdiits:textarea mandatory="true" rows="2" cols="50"  
                                    name="Representation" tabindex="${tabCount}" id="Representation" 
                                    validation ="txt.isrequired"   captionid="HR.ESS.Representation" 
                                     bundle="${commonLables}" onblur="specialChar(this);" />
      		</td>
      					<c:set var="tabCount" value="${tabCount+1}"/>
      		</tr>
      
      
      <tr colspan="4">
      
      
      
       
       <td width="25%"><hdiits:caption captionid="HR.ESS.Average" bundle="${commonLables}" id="Average" />: </td>
     
       
      <td width="25%">
       <hdiits:text maxlength="10" size="10" id="Average"  name="Average"  validation="txt.isrequired" captionid="HR.ESS.Average"  bundle="${commonLables}" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;" />
      
      </td>
      <td width="25%"></td>
       <td width="25%"></td>
      
      </tr>
      
      
      <tr colspan="2"><td width="25%"><hdiits:caption captionid="HR.ESS.Specific" bundle="${commonLables}" id="Specific" />: </td><td>
       <hdiits:select captionid="HR.ESS.Specific" bundle="${commonLables}" name="Suggestion" id="Suggestion" mandatory="true" validation="sel.isrequired" sort="true" tabindex="${tabCount}"> 
       				<option value="-1"><fmt:message key="HR.ESS.Select" bundle="${commonLables}"/></option>
       				<c:forEach var="list" items="${suggestionList}">
       			
       					<option value="<c:out value="${list.orderNo}"/>">
       					<c:out value="${list.lookupDesc}"/>
       					</option>
       				
       				</c:forEach>
       
       </hdiits:select>
			<c:set var="tabCount" value="${tabCount+1}"/>
       </td>
      </tr>
    </table>
	
</hdiits:fieldGroup>
	<table align="center">

		<tr colspan="2">
			<td>
				<hdiits:button captionid="HR.ESS.Submit" bundle="${commonLables}" name="Submit" type="button" onclick="checkval(${y})" tabindex="${tabCount+1}" />
			</td>
		 
		 <c:if test="${reportFlag eq 0}">
			<td>
				<hdiits:button captionid="HR.ESS.Close" bundle="${commonLables}" name="Close" type="button" onclick="submit4();" tabindex="${tabCount+2}"/>
			</td>
	 	 </c:if>
	 

  		 <c:if test="${reportFlag eq 1}">
			<td>
				<hdiits:button captionid="HR.ESS.Close" bundle="${commonLables}" name="Close" type="button" onclick="window.close();" />
			</td>
		 </c:if>
		</tr>	
	</table>
</c:if>
			
<c:if test="${recordFlag eq 1}">
		
		<!--  
		
		 <table class="tabtable">
		<tr>
		<td width="100%">

					<hdiits:caption captionid="HR.ESS.AlreadyEntered" bundle="${commonLables}" id="AlreadyEntered" />:
				</td>
		</tr>		
		</table>		
			
		-->
<hdiits:fieldGroup bundle="${commonLables}"  id="SheetRemarksGrp" expandable="true" titleCaptionId="HR.ESS.SheetRemarks">
<table cellpadding="tabtable" width="100%">
       <tr><hdiits:caption captionid="HR.ESS.Date" bundle="${commonLables}" id="Date" />:<c:out value="${date}"/></tr>
		<c:out value="Data for selected user for given year is already entered"></c:out>
</table>
	        <table class="tabtable">
            	<c:set var="y" value= "0"/>
            	
			<c:forEach var="labels" items="${labelValue}" varStatus="x">
			<tr colspan="7">
		<c:set var="select" value= "${selectlookupList[x.index]}"/>
		<c:set var="trn" value="${trnDataList[x.index]}"/>
		<c:set var="z" value= "${codeofelementLabelList[x.index]}"/>
		
	
  				<td width="10%">
  					<c:out value="${labels}"/>: 
  				
  				</td>
  				<td width="10%">
  				<hdiits:caption captionid="HR.ESS.Rating" bundle="${commonLables}" id="Rating" />:
			
  				</td>
    			<td width="10%">
    				<c:out value="${select.lookupDesc}"/>
    				<c:set var="count" value="${labelsCount.count}"/>
 				 	
				</td>

				<td width="10%">
	
					<hdiits:caption captionid="HR.ESS.RatingValue" bundle="${commonLables}" id="RatingValue" />:
			
				</td>
				<td width="10%">
				<c:out value="${trn.ratingValue}"/>
				</td>

				<td width="10%">

					<hdiits:caption captionid="HR.ESS.Remarks" bundle="${commonLables}" id="Remarks" />:
				</td>

				<td width="10%">

						
					<c:out value="${trn.sheetrFieldTxtarea}"/>
					<hdiits:hidden name="Code${y}" default="${z}"/>
				

				</td>
				</tr>
				
			<c:set var="y" value="${y+1}"/>
			

			</c:forEach>
			 <tr colspan="2">
      
      		<td width="10%"><hdiits:caption captionid="HR.ESS.Evaluationwork" bundle="${commonLables}" id="Evaluation" />: </td>
     			<td>
      		 <c:out value="${seqList.evaluationOfWork}"></c:out>
     	</td>
      		</tr>
      
     		 <tr colspan="2">
      
      		<td width="10%"><hdiits:caption captionid="HR.ESS.Representation" bundle="${commonLables}" id="Representation" />: </td>
      		<td>
      		<c:out value="${seqList.representation}"></c:out>
     
      		</td>
      		</tr>
      
      
      <tr colspan="4">
      
      <td width="10%"></td>
      <td width="10%"></td>
       
       <td width="10%" align="center"><hdiits:caption captionid="HR.ESS.Average" bundle="${commonLables}" id="Average" />: </td>
     
       
      <td align="center">
      <c:out value="${seqList.averageRating}"></c:out>
      </td>
       
      
      </tr>
      
      
      <tr colspan="2"><td width="10%"><hdiits:caption captionid="HR.ESS.Specific" bundle="${commonLables}" id="Specific" />: </td><td>
       <c:out value="${suggestion.lookupDesc}"></c:out>
       </td>
      </tr>
			
		<table align="center">

		  <tr colspan="2">
			<td>
				<hdiits:formSubmitButton captionid="HR.ESS.BackToSearch" bundle="${commonLables}" name="BackToSearch" type="button"  onclick="submit3()"/>
			</td>
			<td>
				<hdiits:formSubmitButton captionid="HR.ESS.Close" bundle="${commonLables}" name="Close" type="button"  />
			</td>
		  </tr>
		</table>
					
	</table>
	</hdiits:fieldGroup>
	</c:if>

	 <hdiits:validate controlNames="test" locale='<%=(String)session.getAttribute("locale")%>'/>
	</div>
	</div>
</hdiits:form>

<script type="text/javascript">
		initializetabcontent("maintab")
</script>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>