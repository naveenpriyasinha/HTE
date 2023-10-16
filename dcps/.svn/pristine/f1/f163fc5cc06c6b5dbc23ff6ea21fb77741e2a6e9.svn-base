
<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<fmt:setBundle basename="resources.rcptacct.rcptacct_en_US" var="rcptacctLabels" scope="request"/>

<c:set var="resultObj" value="${result}"> </c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>		

<script> keyPreview =1 ;</script>     
<script language="javascript">
function formatAmount(ele)
{
	if(ele.value == "")
		return;
	var amt = ele.value;
	var finalamt = "";
	for(var i = 0; i< amt.length ; i++)
	{
		if(amt.charAt(i) != ",")
		{
			finalamt = finalamt + amt.charAt(i);
		}
	}
	ele.value = finalamt;
}

function search(cmbSearch)
{
		
			var cmbSearch2 = document.getElementById('cmbSearch2'); 
		/*	if(document.forms[0].cmbSearch.value==2)
			{
				var dt=document.forms[0].txtSearch
				if (isDate(dt.value)==false)
				{
					dt.value='';
					dt.focus()
					return;
				}
			}*/
			if(document.forms[0].txtSearch.value=="" && document.forms[0].cmbSearch.value==0)
				alert("Enter search value & select criteria");
			else if(document.forms[0].txtSearch.value=="")
				alert("Please enter search value");
			else if(document.forms[0].cmbSearch.value==0)
				alert("Select criteria for search");
			else
			{
				if(document.forms[0].cmbSearch.value == 2)
				{
					formatAmount(document.getElementById('id_txtSearch'));
					var amt = parseFloat(document.getElementById('id_txtSearch').value); 
					if(isNaN(amt))
					{
						alert('Invalid Amount');
						return;
					}
				}
			}

			var search=(cmbSearch.options[cmbSearch.selectedIndex].text);
			
			var viewName = document.getElementById("viewPage").value;
			var search2 = "";
		
			var posted = document.getElementById('postedSearch').value;
			
			if(document.forms[0].txtSearch.value !="" && document.forms[0].cmbSearch.value != 0)
			{
			document.forms[0].action ='ifms.htm?actionFlag=searchReceipt&searchby='+search+'&searchby2='+search2+'&posted='+posted;
			document.forms[0].submit();
			}
}

</script>



	
<input type="hidden" name="viewPage" value="<%=request.getParameter("viewName")%>" >
<input type="hidden" name="postedSearch" value="<%=request.getParameter("postedSearch")%>" >
<table align="center" width="90%">
			<tr>
				<td width="65%">
				</td>
				<td width="8%" align="right">
					Search :
				</td>
				<td >
						<select name="cmbSearch" id="id_cmbSearch" tabindex="1">
							<option value="0">-----Select-----</option>
							<option value="1">
								<fmt:message key="CMN.MAJORHEAD" bundle="${rcptacctLabels}"></fmt:message>
							</option>
							<option value="2">
								<fmt:message key="CDP.AMOUNT" bundle="${rcptacctLabels}"></fmt:message>
							</option>
							
					</select>
					<c:choose>
						<c:when test="${resValue.searchBy!=null}">
							<script type="text/javascript">
								
								
								var cmb = document.getElementById("id_cmbSearch");
								for(i = 0 ; i < cmb.options.length;i++)
								{
									if(cmb.options[i].text == '${resValue.searchBy}')
									{
										cmb.value = cmb.options[i].value;
									}
									
								}
							</script>
						</c:when>
						<c:otherwise>
							<script type="text/javascript">
								document.getElementById("id_cmbSearch").value = "2";
							</script>
						</c:otherwise>
					</c:choose>
				</td>
				<td width="3%">
				</td>
				<td width="18%">
					<input type="text" name="txtSearch" id="id_txtSearch" size="15" onkeypress="restrictKey();" tabindex="1"/>
				</td>
				
				<c:choose>
					<c:when test="${resValue.searchVal!=null}">
						<script type="text/javascript">
							document.getElementById("id_txtSearch").value = '${resValue.searchVal}';
						</script>
					</c:when>
					<c:otherwise>
						<script type="text/javascript">
							document.getElementById("id_txtSearch").value = "";
						</script>
					</c:otherwise>
				</c:choose>
				
				
						<td width="8%" align="right" id="searchTD1" style="display: none;">
							AND :
						</td>
						<td id="searchTD2" style="display: none;">
								<select name="cmbSearch2" id="id_cmbSearch2" tabindex="1">
									<option value="0">-----Select-----</option>
								
									<option value="1">
										<fmt:message key="CMN.MAJORHEAD" bundle="${rcptacctLabels}"></fmt:message>
									</option>
									<option value="2">
										<fmt:message key="CDP.AMOUNT" bundle="${rcptacctLabels}"></fmt:message>
									</option>
								
							</select>
							<c:choose>
								<c:when test="${resValue.searchBy!=null}">
									<script type="text/javascript">
										
										
										var cmb = document.getElementById("id_cmbSearch2");
										for(i = 0 ; i < cmb.options.length;i++)
										{
											if(cmb.options[i].text == '${resValue.searchBy}')
											{
												cmb.value = cmb.options[i].value;
											}
											
										}
									</script>
								</c:when>
								<c:otherwise>
									<script type="text/javascript">
										document.getElementById("id_cmbSearch2").value = "2";
									</script>
								</c:otherwise>
							</c:choose>
						</td>
						<td width="3%">
						</td>
						<td width="18%" id="searchTD3" style="display: none;">
							<input type="text" name="txtSearch2" id="id_txtSearch2" size="15" onkeypress="restrictKey();" tabindex="1"/>
						</td>
						
						<c:choose>
							<c:when test="${resValue.searchVal!=null}">
								<script type="text/javascript">
									document.getElementById("id_txtSearch2").value = '${resValue.searchVal}';
								</script>
							</c:when>
							<c:otherwise>
								<script type="text/javascript">
									document.getElementById("id_txtSearch2").value = "";
								</script>
							</c:otherwise>
						</c:choose>
				
				
				
				
				<td width="20%">
					<input type="button" class="searchButton" name="btnSearch" onclick="search(cmbSearch)") tabindex="1"/>
					<%--  <img src="common/images/search.gif" align="right" height="16" width="16" onclick="search(cmbSearch)"/> --%>
				</td>
				</tr>
			</table>
	
	
<script type="text/javascript">

	var viewName = document.getElementById("viewPage").value;

function restrictKey()
{
	if(window.event.keyCode == 44)
	{
		window.event.keyCode=0;
	}
}

</script>
<script type="text/javascript">
//	document.getElementById('id_cmbSearch').value = "7";
	document.getElementById('id_cmbSearch').focus();
</script>