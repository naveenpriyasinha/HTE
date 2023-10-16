
<script type="text/javascript">

function alphanumeric(alphane)
{

	var numaric = alphane;
	var no=0;
	var ch=0;
	for(var j=0; j<numaric.length; j++)
		{
		  var alphaa = numaric.charAt(j);
		  var hh = alphaa.charCodeAt(0);
		  if(hh > 47 && hh<58) 
		  {
		  	no=1;
		  }
		  else if((hh > 64 && hh<91) || (hh > 96 && hh<123))
		  {
		  	ch=1;
		  }
		  else if( alphaa=='/' || alphaa=='-')
		  {
		  }
		  
		  else	
   		  {
			return false;
		  }
		}
	if(ch==0 || no==0)
	{
		return false;
	}

 return true;
}


function showAccNo()
{
	document.getElementById("accNo").style.display="";
}

function hideAccNo()
{
	document.getElementById("accNo").style.display="none";
}


function submitData()
{
var validData=true;
var fwdApp=parent.window.document.getElementById('wfAction').value;

if(fwdApp=='Approve')
{

if("${newAcc.isAppByRajkot}" !='P')
{
	alert("Request has already been saved");
	//return false;
}

if("${newAcc.isAppByRajkot}" =='P')
{
	var accNo=document.forms.frmBF1.accNo.value;
	if(accNo!="" && !alphanumeric(accNo))
	{
		document.getElementById("case0").style.display='none';
		document.getElementById("case1").style.display='none';
		document.getElementById("case2").style.display='none';
		document.getElementById("case3").style.display='none';
		document.getElementById("case4").style.display='none';
		document.getElementById("case5").style.display='block';
		document.getElementById("case6").style.display='none';
		document.getElementById("ifSamAaccNo").style.display='none'; 
		validData=false;
		return false;
	}
	
	if (document.forms.frmBF1.agResponse.value=="Select")
	{
		document.getElementById("case0").style.display='block';
		document.getElementById("case1").style.display='none';
		document.getElementById("case2").style.display='none';
		document.getElementById("case3").style.display='none';
		document.getElementById("case4").style.display='none';
		document.getElementById("case5").style.display='none';
		document.getElementById("case6").style.display='none';
		document.getElementById("ifSamAaccNo").style.display='none'; 
		validData=false;
		return false;
	}
	if(""==document.forms.frmBF1.orderNo.value.length)
	{
		document.getElementById("case0").style.display='none';
		document.getElementById("case1").style.display='none';
		document.getElementById("case2").style.display='block';
		document.getElementById("case3").style.display='none';
		document.getElementById("case4").style.display='none';
		document.getElementById("case5").style.display='none';
		document.getElementById("case6").style.display='none';
		document.getElementById("ifSamAaccNo").style.display='none'; 
		validData=false;
		return false;
	}
	if(document.forms.frmBF1.orderNo.value.length!="")
	{
		var orderNum=document.forms.frmBF1.orderNo.value;
		var orderNumLength=document.forms.frmBF1.orderNo.value.length;
		var i=0;
		while ((i < orderNumLength) && (orderNum.charAt(i) != "&" && orderNum.charAt(i) != "@" &&  orderNum.charAt(i) != "_" && orderNum.charAt(i) != "." && orderNum.charAt(i) != ":" && orderNum.charAt(i) != "?" && orderNum.charAt(i) != ">" && orderNum.charAt(i) != "<" && orderNum.charAt(i)!= "~" && orderNum.charAt(i)!=  "^" && orderNum.charAt(i)!= "#" && orderNum.charAt(i)!= "`" && orderNum.charAt(i)!="*" &&  orderNum.charAt(i)!= "'" && orderNum.charAt(i)!="|" && orderNum.charAt(i)!= "$" ))
		{
			i++;
		}
		if ((i >= orderNumLength) || (orderNum.charAt(i) != "&" && orderNum.charAt(i) != "@" && orderNum.charAt(i) != "_" && orderNum.charAt(i) != "." && orderNum.charAt(i) != ":" && orderNum.charAt(i) != "?" && orderNum.charAt(i) != ">" && orderNum.charAt(i) != "<" && orderNum.charAt(i)!= "~" && orderNum.charAt(i)!=  "^" && orderNum.charAt(i)!= "#" && orderNum.charAt(i)!= "`" && orderNum.charAt(i)!="*" &&  orderNum.charAt(i)!= "'" && orderNum.charAt(i)!="|" && orderNum.charAt(i)!= "$" ))
		{
			validData=true;
		}

		else
		{
			document.getElementById("case0").style.display='none';
			document.getElementById("case1").style.display='none';
			document.getElementById("case2").style.display='none';
			document.getElementById("case3").style.display='none';
			document.getElementById("case4").style.display='none';
			document.getElementById("case5").style.display='none';
			document.getElementById("case6").style.display='block';
			document.getElementById("ifSamAaccNo").style.display='none'; 
			validData=false;
			return false;
		}
	}
	
	if(document.forms.frmBF1.agResponse.value=='Y')
		{
		if(""==document.forms.frmBF1.accNo.value.length)
			{
				document.getElementById("case0").style.display='none';
				document.getElementById("case1").style.display='block';
				document.getElementById("case2").style.display='none';
				document.getElementById("case3").style.display='none';
				document.getElementById("case4").style.display='none';
				document.getElementById("case5").style.display='none';
				document.getElementById("case6").style.display='none';
				document.getElementById("ifSamAaccNo").style.display='none'; 
				validData=false;
				return false;
			}
			if(document.forms.frmBF1.accNo.value.length!="")
			{
				var accountNum=document.forms.frmBF1.accNo.value;
				var accountNumLength=document.forms.frmBF1.accNo.value.length;
				var i=0;
				while ((i < accountNumLength) && (accountNum.charAt(i) != "&" && accountNum.charAt(i) != "@" &&  accountNum.charAt(i) != "_" && accountNum.charAt(i) != "." && accountNum.charAt(i) != ":" && accountNum.charAt(i) != "?" && accountNum.charAt(i) != ">" && accountNum.charAt(i) != "<" && accountNum.charAt(i)!= "~" && accountNum.charAt(i)!=  "^" && accountNum.charAt(i)!= "#" && accountNum.charAt(i)!= "`" && accountNum.charAt(i)!="*" &&  accountNum.charAt(i)!= "'" && accountNum.charAt(i)!="|" && accountNum.charAt(i)!= "$" ))
				{
					i++
				}
	
				if ((i >= accountNumLength) || (accountNum.charAt(i) != "&" && accountNum.charAt(i) != "@" && accountNum.charAt(i) != "_" && accountNum.charAt(i) != "." && accountNum.charAt(i) != ":" && accountNum.charAt(i) != "?" && accountNum.charAt(i) != ">" && accountNum.charAt(i) != "<" && accountNum.charAt(i)!= "~" && accountNum.charAt(i)!=  "^" && accountNum.charAt(i)!= "#" && accountNum.charAt(i)!= "`" && accountNum.charAt(i)!="*" &&  accountNum.charAt(i)!= "'" && accountNum.charAt(i)!="|" && accountNum.charAt(i)!= "$" ))
				{
					validData=true;
				}

				else
				{
					document.getElementById("case0").style.display='none';
					document.getElementById("case1").style.display='none';
					document.getElementById("case2").style.display='none';
					document.getElementById("case3").style.display='none';
					document.getElementById("case4").style.display='none';
					document.getElementById("case5").style.display='block';
					document.getElementById("case6").style.display='none';
					document.getElementById("ifSamAaccNo").style.display='none'; 
					validData=false;
					return false;
				}
			}
		}

	
		if(""==document.forms.frmBF1.retOrderDate.value.length)
		{
			document.getElementById("case0").style.display='none';
			document.getElementById("case1").style.display='none';
			document.getElementById("case2").style.display='none';
			document.getElementById("case3").style.display='block';
			document.getElementById("case4").style.display='none';
			document.getElementById("case5").style.display='none';
			document.getElementById("case6").style.display='none';
			document.getElementById("ifSamAaccNo").style.display='none'; 
			validData=false;
			return false;
		}
	
	var lCurrDat=document.getElementById("srvdate").innerHTML;
	var retOrdDate=document.forms.frmBF1.retOrderDate.value;
	lCurrDat1=lCurrDat.split("/");
	startDate = new Date(lCurrDat1[2],lCurrDat1[1]-1,lCurrDat1[0]); 
	lstartDate = new Date(startDate.valueOf()); 
	lretOrdDate=retOrdDate.split("/");
	endDate = new Date(lretOrdDate[2],lretOrdDate[1]-1,lretOrdDate[0]);

	lendDate = new Date(endDate.valueOf()); 
	if(lendDate>lstartDate)
	{
		document.getElementById("case0").style.display='none';
		document.getElementById("case1").style.display='none';
		document.getElementById("case2").style.display='none';
		document.getElementById("case3").style.display='none';
		document.getElementById("case4").style.display='block';
		document.getElementById("case5").style.display='none';
		document.getElementById("case6").style.display='none';
		document.getElementById("ifSamAaccNo").style.display='none'; 
		validData=false;
		return;
	}
 }
 
 }//end fwdApp=approve
return true;
}	


function callOnSelect(){
var validData=true;
	if(document.forms.frmBF1.agResponse.value=="Y")
	{
		document.getElementById("accountNo").style.display=""; 
	}
	if(document.forms.frmBF1.agResponse.value=="N")
	{
		document.getElementById("accountNo").style.display="none"; 
	}
}



</script>


<hdiits:fieldGroup titleCaptionId="HRMS.ResponseAgRajkot" bundle="${gpfLables}"  expandable="true" collapseOnLoad="false" id="masterScreenGPFAcc">
<table width=100%>
	<input type="hidden" name="userID"	value="${newAcc.orgUserMstByUserId.userId}" />
	<tr>
		<td colspan="1">
		<table border="0" width="50%">
			<tr>
				<td width="25%">
				<hdiits:caption captionid="GPF.agResponse" bundle="${gpfLables}"/>
				</td>
				<c:if test="${newAcc.isAppByRajkot eq 'P' }">
					<td width="25%">
					<hdiits:select name="agResponse" mandatory="true" id="agResponse" onchange="callOnSelect()">
						<option id="a" value="Select">
						<fmt:message key="GPF.select" />
						</option>
						<c:forEach var="name" items="${agResponse}">
							<option id="opt" name="opt"	value="<c:out value="${name.lookupShortName}"/>">
							<c:out	value="${name.lookupDesc}" />
							</option>
						</c:forEach>
					</hdiits:select>
					</td>
				</c:if>

				<c:if test="${newAcc.isAppByRajkot=='Y' }">
					<td>
					<p id="agResponseSavedY">
					<hdiits:caption captionid="GPF.approveAg" bundle="${gpfLables}"/>
					</p>
					</td>
				</c:if>
				<c:if test="${newAcc.isAppByRajkot=='N' }">
					<td>
					<p id="agResponseSavedN">
					<hdiits:caption captionid="GPF.rejectAg" bundle="${gpfLables}"/>
					</p>
					</td>
				</c:if>
			</tr>

			<tr>
				<td align="left">
				<hdiits:caption captionid="HRMS.orderNo" bundle="${gpfLables}"/>
				</td>
				<c:if test="${newAcc.orderNo eq null }">
					<td><hdiits:text name="orderNo" id="orderNo" mandatory="true"
						captionid="HRMS.orderNo" bundle="${gpfLables}" maxlength="19" />
					</td>
				</c:if>
				<c:if test="${newAcc.orderNo ne null }">
					<td><c:out value="${newAcc.orderNo}"></c:out></td>
				</c:if>
			</tr>

			<tr id="accountNo">
				<c:if test="${newAcc.isAppByRajkot eq 'P' and accNo eq null }">
					<td align="left">
					<hdiits:caption captionid="HRMS.AccountNo" bundle="${gpfLables}"/>
					</td>
				</c:if>
				<c:if test="${newAcc.isAppByRajkot eq 'P' and accNo eq null }">
					<td><hdiits:text name="accNo" id="accNo" maxlength="19"
						onblur="chkAccountNo()" captionid="HRMS.AccountNo"
						mandatory="true" bundle="${gpfLables}" /></td>
				</c:if>
				<c:if test="${newAcc.isAppByRajkot=='Y'}">

					<td align="left">
					<hdiits:caption captionid="HRMS.AccountNo" bundle="${gpfLables}"/>
					</td>
				</c:if>

				<c:if test="${newAcc.isAppByRajkot eq 'Y' }">
					<td><c:out value="${accNo}"></c:out></td>
				</c:if>

			</tr>


			<tr>
				<td align="left">
				<hdiits:caption captionid="GPF.orderDate" bundle="${gpfLables}"/>
				</td>
				<c:if test="${newAcc.retOrderDate eq null }">
					<td><hdiits:dateTime name="retOrderDate"
						captionid="GPF.orderDate" bundle="${gpfLables}" mandatory="true" />
					</td>
				</c:if>

				<c:if test="${newAcc.retOrderDate ne null }">
					<td>
					<fmt:formatDate value="${newAcc.retOrderDate}"	pattern="dd/MM/yyyy" />
					</td>
				</c:if>


			</tr>
		</table>
	</td>
</tr>

</table>
</hdiits:fieldGroup>
<script type="text/javascript">
		initializetabcontent("maintab")
		
		if("${cancelType}"=="true" || "${isAppByRajkot}"=='C')
		{

			document.getElementById("masterScreenGPFAcc").style.display='none';

		}
		</script>

