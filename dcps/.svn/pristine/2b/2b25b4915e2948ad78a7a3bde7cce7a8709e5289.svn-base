<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.eis.eisLables_en_US" var="commonLables" scope="request"/>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/Address.js"/>">
</script>

<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
	
<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="monthList" value="${resValue.monthList}" ></c:set>
<c:set var="yearList" value="${resValue.yearList}" ></c:set>
<script>


function generateledgerform()
{
if(document.frmledgerformPara.month.value=="-1")
{
alert('Please select month');
document.frmledgerformPara.month.focus();
return false;
}
if(document.frmledgerformPara.year.value=="-1")
{
alert('Please select Year');
document.frmledgerformPara.year.focus();
return false;
}
// window.open("hrms.htm?actionFlag=generateledgerform","ledgerform","status=yes, toolbar=yes,resizable=1,height=400, width=600,scrollbars=1");
  document.frmledgerformPara.submit();
  
}

function beforeSubmit()
{	        
	var year = document.frmledgerformPara.year.value;
	var month = document.frmledgerformPara.month.value; 
	var billNo= document.frmledgerformPara.billNo.value
	var url = "./hrms.htm?actionFlag=generateledgerform&month="+month+"&year="+year+"&billNo="+billNo;
        window.open(url,"generateledgerform","toolbar=yes, location=no, directories=no,status=yes, menubar=no, scrollbars=yes, resizable=yes, resize = no, width = 800, height = 550, copyhistory=no, top=80,left=80");
}

 function getbillNos()
{
	//var deptId = document.frmledgerformPara.selDept.value;
	xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  var givenMonth=document.frmledgerformPara.month.value; 
		  var givenYear=document.frmledgerformPara.year.value; 
		  if(givenMonth!=''&&givenYear!='')
		  {
		  var url; 
		  var uri='';
		  url= uri+'&givenMonth='+givenMonth+'&givenYear='+givenYear;
		  var actionf="getPayBillNos";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
  			//alert(' ' + url);	  		  		  
			xmlHttp.onreadystatechange=find_billNo;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);	
		  }	
}

function find_billNo()
{

if (xmlHttp.readyState==complete_state)
 { 						
					var XMLDoc=xmlHttp.responseXML.documentElement;	
					var billNo1 = document.getElementById("billNo");
                    if(XMLDoc==null)
                    {
                      window.status = 'No Records Found.';
                     }
                    else
                    {
                        window.status='';
               			var v=document.getElementById("billNo").length;
						for(i=1;i<v;i++)
						{
								lgth = document.getElementById("billNo").options.length -1;
								document.getElementById("billNo").options[lgth] = null;
						}
               			
                        var namesEntries = XMLDoc.getElementsByTagName('billList-mapping');
 	           			for ( var i = 0 ; i < namesEntries.length ; i++ )
	     				{
	     				    val=namesEntries[i].childNodes[0].text;    
	     				    text = namesEntries[i].childNodes[1].text ;  
	     				    var y = document.createElement('option')   
	     			        y.value=val;
	     			        y.text=text;
	     			        
 	                        try
 	   				        {	      				    					
                               billNo1.add(y,null);
	           		        }
	           		
	 						 catch(ex)
	   						 {
	   			 	  		    billNo1.add(y); 
	   						 }
	   		          }
	   		         }
  }
}
</script>


<hdiits:form name="frmledgerformPara" validate="true" method="POST"
	action="javascript:beforeSubmit()" encType="multipart/form-data">

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption captionid="Ledger Form" caption="Ledger Form Parameters"/> </b></a></li>
	</ul>
	</div>
	
	<div class="halftabcontentstyle">
	<div id="tcontent1" class="halftabcontent" tabno="0">
	<div align="center">
 <table>
 	<tr> <td colspan="2">&nbsp;</td></tr>
	
 
   	 <tr>	
	<TD> <b><hdiits:caption caption="Month" captionid="Month"/></b></TD>
	<td>
	<hdiits:select name="month" size="1" sort="false" >
	<hdiits:option value="-1"> --Select-- </hdiits:option>
	<c:forEach items="${monthList}" var="month">
	<hdiits:option value="${month.lookupShortName}"> ${month.lookupDesc} </hdiits:option>
	</c:forEach>
	</hdiits:select>
	</td>
   <td> </td>
	<TD> <b><hdiits:caption caption="Year" captionid="Year" /></b></TD>
	<td>
		<hdiits:select name="year" size="1" sort="false" onchange="getbillNos();">
	<hdiits:option value="-1"> --Select-- </hdiits:option>
	<c:forEach items="${yearList}" var="yearList">
	<hdiits:option value="${yearList.lookupShortName}"> ${yearList.lookupDesc} </hdiits:option>
	</c:forEach>
	</hdiits:select>
	</td>
				<TD> <b>Bill No</b>
			 <hdiits:select name="billNo" validation="sel.isrequired" id="billNo" size="1" 
			caption="Bill No" sort="false" >
			<hdiits:option value="" selected="true">-------Selected--------</hdiits:option>
			</hdiits:select>		       
			</TD>
	</tr>
	
	
   </table>
   <br> 
   <table>
   
	
	<tr> <td> </td></tr>
	<tr> <td> </td></tr>
	<tr>
	<td align="center" colspan="3">
	<hdiits:button name="btn1" value="Generate ledgerform" caption="Generate Ledger Form" 
	id="btnSubmit1" captionid="Generate ledgerform" onclick="generateledgerform()" 
	   type="button" />
	  </td>
	  </tr>	
	</table> 
    <br>
 </div>
 
	
	</div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

