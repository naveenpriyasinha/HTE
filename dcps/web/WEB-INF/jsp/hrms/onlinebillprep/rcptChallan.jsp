<%@ include file="../../core/include.jsp" %>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   

<fmt:setLocale value='<%=(String)session.getAttribute("locale")%>'/>

<c:set var="resValue" scope="request" value="${result.resultValue}"></c:set>

<c:set var="chlnCardexNo" value="${resValue.challanCardex}"></c:set>

<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="<c:url value="script/common/tabcontent.js"/>"></script>

<head>

	<script type="text/javascript">
	
		function addChHeadDtls()
		{
			var row1 = document.getElementById('challanHeadTb1').insertRow();
			row1.className="odd";
			
			var col1 = row1.insertCell(0);
			var col2 = row1.insertCell(1);
			var col3 = row1.insertCell(2);
			var col4 = row1.insertCell(3);
			var col5 = row1.insertCell(4);
			
			col1.innerHTML = "<input type='hidden' name='hidRcptBudHdId' /><input type='text' name='chSubMajorHead' size='25%' maxlength='2'/> ";
			col2.innerHTML = "<input type='text' name='chMinorHead' size='25%' maxlength='3'/> ";
			col3.innerHTML = "<input type='text' name='chSubHead' size='25%' maxlength='2' /> ";
			col4.innerHTML = "<input type='text' name='chAmount' size='22%' onblur='getGrossTotalChallan()'/> ";			
			col5.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif"  onclick="deleteChHeadDtlsRow(this,\'challanHeadTb1\'),onblur=\'getGrossTotalChallan()\' \" />';
			
		}	
		
		
		
		function deleteChHeadDtlsRow(cellObj, tableId)
		{
			var rowId = showRowCell(cellObj);
			var headTable = document.getElementById(tableId);
			headTable.deleteRow(rowId);
			getGrossTotalChallan();		
		}
			
		function addChPartyDtls()
		{
			var row2 = document.getElementById('challanPartyTb2').insertRow();
				
			var cell21 = row2.insertCell(0);
			var cell22 = row2.insertCell(1);
			var cell23 = row2.insertCell(2);
			var cell24 = row2.insertCell(3);
			var cell25 = row2.insertCell(4);
			
			cell21.width="25%";
			cell22.className="odd";
			cell23.className="odd";
			cell24.className="odd";
			cell25.width="5%";
			cell25.width="20%";
			
			// 	var imageConst = '<img src="images/CalendarImages/DeleteIcon.gif" size=\'5%\' onclick="deleteChHeadDtlsRow(this,\'challanPartyTb2\')" />';														
			
			cell21.innerHTML = "";
			cell22.innerHTML = "<input type='hidden' name='hidChallanPartyId' /><input type='text' name='chPartyName' size='25%'/> ";
			cell23.innerHTML = "<input type='text' name='chPartyAmount' size='25%'/> ";
			cell24.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif" size="5%" onclick="deleteChPartyDtlsRow(this,\'challanPartyTb2\') \" />';																						
			cell25.innerHTML = "";
			
		}
		
		function deleteChPartyDtlsRow(cellObj1, tableId1)
		{
			var rowId1 = showRowCell(cellObj1);
			var partyTable1 = document.getElementById(tableId1);
			partyTable1.deleteRow(rowId1);				
		}
			
		
		function getGrossTotalChallan()
		{			
			var ltotal = 0;
			
				var x = document.getElementsByName("chAmount");
				
				for(var i = 0; i < x.length ; ++i)
				{
					if(!(x[i].value == ""))
					{
						ltotal += Number(x[i].value);
					}
				}
			
			var grosstot = 	document.getElementById("grsAmt");
				grosstot.innerHTML = ltotal;			
				
			if(ltotal > Math.round(parseFloat(document.getElementById("chChlnValue").value)))
			{
				alert("Gross Total should not exceed Challan Amount.");
			}				
		}
		
		function showMjrHdFrmEDP()
		{
			if(document.getElementById("id_txtRcptMjrHdCode1") !=  null)
			{
				document.getElementById("id_chMajorHead").value = document.getElementById("id_txtRcptMjrHdCode1").innerHTML;
				document.getElementById("chChlnValue").value = document.getElementById("txtRcptAmt1").value;
			}
		}
		
		function checkForNull()
		{		
			if(document.getElementById("id_chMajorHead").value == "")
			{
				alert("Please Enter Major Head");
				return false;
			}
			else			
			{
				if(document.getElementById("id_chChlnValue").value == "")
				{
					alert("Please Enter Challan Value");
					return false;
				}
				else
				{
					if(document.getElementById("chChlnDate").value == "")
					{
						alert("Please Enter Challan Date");
						return false;
					}
					else
					{						
							return true;						
					}
				}
			}		
		}
		
		
		
		
	</script>
	
	<style>
   	.tabstyle 
 	{
		border-width: 5px 1px 1px 1px; 
		border-color: #2065c0;
		border-style: solid ;
	}
	
	legend 
	{
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

<table width="100%">
	<tr>
		<td width="100%">
		
			<fieldset  style = "width:100%" class="tabstyle">
			<legend  id="headingMsg"><b>Challan Receipt Details</b></legend>

			<table width="100%">
				<tr>
					<td colspan="4">
						<input type="hidden" name="hidReceiptId" />	
					</td>					
				</tr>
				
				<tr align="center" class="datatableheader" width="100%" >			
				
					<td class="datatableheader" width="15%">
						Major Head	&nbsp;<label class="mandatoryindicator">*</label>	
					</td>
	
					<td class="datatableheader" width="20%">
						Challan Date	&nbsp;<label class="mandatoryindicator">*</label>			
					</td>
	
					<td class="datatableheader" width="15%">
						Cardex No			
					</td>
	
					<td class="datatableheader" width="15%">
						Scheme No			
					</td>
	
					<td class="datatableheader" width="10%">
						TC	&nbsp;<label class="mandatoryindicator">*</label>			
					</td>
	
					<td class="datatableheader" width="25%">
						Challan Value	&nbsp;<label class="mandatoryindicator">*</label>			
					</td>										
					
				</tr>
				
				<tr width="100%" align="center" class="odd">
					<td width="15%" class="Label">
						<input type="text"	name="chMajorHead"	id="id_chMajorHead"  maxlength="4"  onkeypress="digitFormat()"/>
					</td>
	
					<td width="20%" class="Label" align="left">
						<input type="text" name="chChlnDate"  id="chChlnDate" size="19%"/>
						<img src=images/CalendarImages/ico-calendar.gif   size="8%" onclick='window_open("chChlnDate",375,300)' >
					</td>
	
					<td width="15%" class="Label">
						<input type="text" name="chCardexNo"  value="${chlnCardexNo}" onkeypress="digitFormat()" />
					</td>
	
					<td width="15%" class="Label">
						<input type="text" name="chScheme"	maxlength="6"	onkeypress="digitFormat()" />
					</td>
	
					<td width="10%" class="Label">
						<select name="cmbChTC" >
			            	<option value="0" selected="selected" >No</option>
							<option value="1" >Yes</option>
						</select>
					</td>
	
					<td width="25%" class="Label">
						<input type="text" name="chChlnValue"	id="id_chChlnValue" onkeypress="digitFormat()" onblur="setNetAmtFrmChallan()"/>
					</td>
				</tr>
			</table>
		</td>
	</tr>	
</table>

<br><br>


<table width="100%" id="challanHeadTb">
	
	<tr>
		<td class="Label" align="left">			
			<hdiits:button type="button" name="btnChAddHeadDtls" value="Add Row" onclick="addChHeadDtls()" ></hdiits:button>
		</td>
	</tr>		
	
	<tr>
		<td width="100%">					
			
			<fieldset  style = "width:100%" class="tabstyle">
			<legend  id="headingMsg"><b>Head Details</b></legend>			

			<table width="100%">
				<tr>
					<td colspan="4">
						<input type="hidden" name="hidRcptBudHdId" />
					</td>
				</tr>
				
				<tr align="center" class="datatableheader">						
					<td  class="datatableheader"  width="25%">
						Sub-Major Head  &nbsp;<label class="mandatoryindicator">*</label>	
					</td>

					<td class="datatableheader" width="25%">
						Minor Head  &nbsp;<label class="mandatoryindicator">*</label>	
					</td>

					<td  class="datatableheader"  width="25%">
						Sub-Head  &nbsp;<label class="mandatoryindicator">*</label>	
					</td>

					<td  class="datatableheader"  width="22%">
						Amount  &nbsp;<label class="mandatoryindicator">*</label>	
					</td>
					
					<td width="3%">
					</td>	
				 </tr>
			</table>

			<table id="challanHeadTb1" width="100%">
				<tr align="center" class="odd">
					<td width="25%" class="Label">
						<input type="text"	id="chSubMajorHead" name="chSubMajorHead" size="25%" maxlength="2" onkeypress="digitFormat()" />
					</td>
		
					<td width="25%" class="Label">
						<input type="text" id="chMinorHead" name="chMinorHead" size="25%" maxlength="3" onkeypress="digitFormat()" />
					</td>
	
					<td width="25%" class="Label">
						<input type="text" id="chSubHead" name="chSubHead" size="25%" maxlength="2" onkeypress="digitFormat()" />
					</td>
	
					<td width="22%" class="Label">
						<input type="text" name="chAmount" id="chAmount" size="22%" onblur="getGrossTotalChallan()" onkeypress="digitFormat()"/>
					</td>
					
					<td width="3%">
						<img src="images/CalendarImages/DeleteIcon.gif"  onclick="deleteChHeadDtlsRow(this,'challanHeadTb1'),onblur='getGrossTotalChallan()'"/>							
					</td>
				</tr>
					
			</table>
			
			<table align="right" width="70%">
				<tr>
					<td class="Label" colspan="3" align="right" width="70%">
						<b> Gross Total :</b>
					</td>
					<td class="Label" align="left">
						<input type="hidden" name="id_grsAmt" />
						<b>&nbsp;&nbsp;&nbsp; <label id="grsAmt"></label> </b>
					</td>
					<td></td>					
				</tr>
			</table>
			</fieldset>
		</td>
	</tr>
</table>

<br>
	
<table width="100%">												
	<tr>
		<td class="Label" align="left">			
			<hdiits:button type="button" name="btnChAddPartyDtls" value="Add Row" onclick="addChPartyDtls()" ></hdiits:button>
		</td>
	</tr>

	<tr>
		<td width="100%">

			<fieldset  style = "width:100%" class="tabstyle">
			<legend  id="headingMsg"><b>Party Details</b></legend>
			
			<table width="100%">
							
				<tr>
					<td colspan="4"></td>
				</tr>				
			
				<tr align="center" >
					<td  width="25%">
					</td> 
	
					<td class="datatableheader" width="25%">
						Party Name
					</td>	
	
					<td class="datatableheader" width="25%" >
						Party Amount
					</td>
					
					<td width="5%" class="datatableheader">
					</td>	
	
					<td width="20%">
					</td>
				</tr>
			</tabel>

			<table id="challanPartyTb2" width="100%">
				<tr >
					<td width="25%" >
					</td>
					
					<td width="25%" class="odd">
						<input type="hidden" name="hidChallanPartyId" />
						<input type="text" name="chPartyName" size="25%"  /> 
					</td>
					
					<td width="25%" class="odd">
						<input type="text" name="chPartyAmount" size="25%" onkeypress="digitFormat()"/>
					</td>
					
					<td width="5%" class="odd">											
						<img src="images/CalendarImages/DeleteIcon.gif" size="5%" onclick="deleteChPartyDtlsRow(this,'challanPartyTb2')"/>														
					</td>
					
					<td width="20%">
					</td>						
				</tr>
			</table>
		
			</fieldset>
		</td>
	</tr>
</table>
<input type="hidden" name="hidValidateMajorDtls" id="id_hidValidateMajorDtls" />
<br><br>

</body>