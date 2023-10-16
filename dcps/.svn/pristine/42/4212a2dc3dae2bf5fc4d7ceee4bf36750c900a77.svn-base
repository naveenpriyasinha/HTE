var pensionerDtlId="";
var pensionerId="";
var pnsnRqstId="";
var gRowNumList="";
var approvalStatus="";
function getPensionerDtlId()
{
	var rowNum;
	var flag=0;
	var arrChkBox=document.getElementsByName("chkbxPesnionerNo");

	if(arrChkBox!=null)
	{
		if(arrChkBox.length > 0)
		{
			for(var i=0;i<arrChkBox.length;i++)
			{
				if(arrChkBox[i].checked == true)
				{
					var rowId=arrChkBox[i].id;
					rowNum=rowId.substring(rowId.indexOf("_")+1);
					if(flag==0)
					{
						flag=1;
						if(document.getElementById("hdnPnsnrDtlsId"+rowNum) != null)
						{
							pensionerDtlId=document.getElementById("hdnPnsnrDtlsId"+rowNum).value;
						}
						if(document.getElementById("hdnpnsnrqstid"+rowNum) != null)
						{
							pnsnRqstId=document.getElementById("hdnpnsnrqstid"+rowNum).value;
						}
						pensionerId=document.getElementById("hdnPensionerId"+rowNum).value;
						gRowNumList = rowNum;
					}
					else
					{
						if(document.getElementById("hdnPnsnrDtlsId"+rowNum) != null)
						{
							pensionerDtlId=pensionerDtlId+"~"+document.getElementById("hdnPnsnrDtlsId"+rowNum).value;
						}
						if(document.getElementById("hdnpnsnrqstid"+rowNum) != null)
						{
							pnsnRqstId=pnsnRqstId+"~"+document.getElementById("hdnpnsnrqstid"+rowNum).value;
						}
					    pensionerId=pensionerId+"~"+document.getElementById("hdnPensionerId"+rowNum).value;
					    gRowNumList = gRowNumList+"~"+rowNum;
					}
					
				}
			}
		}
	}
					
}
function forwCaseUsingAJAX(showCaseFor)
{
	pensionerDtlId="";
	pensionerId="";
	pnsnRqstId="";
	gRowNumList="";
	getPensionerDtlId();	
	if(pnsnRqstId.length > 0)
	{
		showProgressbar();
		var uri="ifms.htm?actionFlag=forwardPensionPaymentCase";
		var url = "pnsnRqstId="+pnsnRqstId+"&pensionerDtlId="+pensionerDtlId+"&showCaseFor="+showCaseFor;
		if(approvalStatus.length > 0)
		   {	   
			  url = url+"&approvalStatus="+approvalStatus;
		   }
		
		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters: url,
			        onSuccess: function(myAjax) {
						getDataStateChangedForForwCase(myAjax,showCaseFor);
					},
			        onFailure: function(){ alert('Something went wrong...')} 
			          } );
		
		
	}else
	{
		alert("Please Select Atleast One Case To Forward");
		
	}
}
function forwCaseUsingAJAXForVoucher(showCaseFor,lpnsnRqstId,lpensionerDtlId)
{
	var uri="ifms.htm?actionFlag=forwardPensionPaymentCase";
	var url = "pnsnRqstId="+lpnsnRqstId+"&pensionerDtlId="+lpensionerDtlId+"&showCaseFor="+showCaseFor;
	
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters: url,
		        onSuccess: function(myAjax) {
						getDataStateChangedForVoucher(myAjax,showCaseFor);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
	
	
	
}
function getDataStateChangedForVoucher(myAjax,showCaseFor)
{
	XMLDoc =  myAjax.responseXML.documentElement;
	  var XmlHiddenValues = XMLDoc.getElementsByTagName('MESSAGE');
	  if(XmlHiddenValues.length > 0)
	  {
		  alert(XmlHiddenValues[0].childNodes[0].nodeValue);
		  window.opener.location.reload();
		  window.self.close();
		 // window.location.href = "ifms.htm?actionFlag=getOLPenCases&showCaseFor="+showCaseFor;
	  }
	  else
	  {
		  alert("Some Problem Occurred During Forward.Please Try Again");	
		  hideProgressbar();
	  }
}
function getDataStateChangedForForwCase(myAjax,showCaseFor)
{
	  XMLDoc =  myAjax.responseXML.documentElement;
	  var XmlHiddenValues = XMLDoc.getElementsByTagName('MESSAGE');
	  if(XmlHiddenValues.length > 0)
	  {
		  alert(XmlHiddenValues[0].childNodes[0].nodeValue);
		  window.location.href = "ifms.htm?actionFlag=getOLPenCases&showCaseFor="+showCaseFor+"&elementId="+document.getElementById("txtElementCode").value;
	  }
	  else
	  {
		  alert("Some Problem Occurred During Forward.Please Try Again");	
		  hideProgressbar();
	  }
}
function forwardCaseUsingAjax(showCaseFor)
{
	forwCaseUsingAJAX(showCaseFor);
//	pensionerDtlId="";
//	pensionerId="";
//	pnsnRqstId="";
//	gRowNumList="";
//	getPensionerDtlId();	
//	if(pnsnRqstId.length > 0)
//	{
//		showProgressbar();
//		var uri="ifms.htm?actionFlag=forwardPensionPaymentCase";
//		var url = "pnsnRqstId="+pnsnRqstId+"&pensionerDtlId="+pensionerDtlId+"&showCaseFor="+showCaseFor;
//		xmlHttp=GetXmlHttpObject();
//	
//		   if (xmlHttp==null)
//		   {
//		      return;
//		   }
//		   //-----For View Modified Cases of ATO----//
//		   if(approvalStatus.length > 0)
//		   {	   
//			  url = url+"&approvalStatus="+approvalStatus;
//		   }
//		   xmlHttp.onreadystatechange=function(){
//			   if (xmlHttp.readyState==complete_state)
//			   { 
//				  var XMLDoc=xmlHttp.responseXML.documentElement;
//				  var XmlHiddenValues = XMLDoc.getElementsByTagName('MESSAGE');
//				  if(XmlHiddenValues != null && XmlHiddenValues.length > 0)
//				  {
//					  alert(XmlHiddenValues[0].text);
//					  window.location.href = "ifms.htm?actionFlag=getOLPenCases&showCaseFor="+showCaseFor;
//				  }
//				  else
//				  {
//					  alert("Some Problem Occurred During Forward.Please Try Again");	
//					  hideProgressbar();
//				  }
//			   }
//		   };
//		   xmlHttp.open("POST",uri,false);
//		   xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
//		   xmlHttp.send(url);
//	}
//	else
//	{
//		alert("Please Select Atleast One Case To Forward");
//		
//	}	
}

function forwardModifiedCaseToAto(showCaseFor)
{
	pensionerDtlId="";
	pensionerId="";
	pnsnRqstId="";
	gRowNumList="";
	getPensionerDtlId();
	if(gRowNumList.length > 0)
	{
		var lArrRowNums = gRowNumList.split("~");
		for(var cnt = 0;cnt<lArrRowNums.length;cnt++)
		{
			var lCaseStatus = document.getElementById("txtCaseStatus"+lArrRowNums[cnt]).innerHTML;
			if(lCaseStatus != caseStatusModified)
			{
				alert("Only Modified Cases Can Be Sent To ATO For Approval");
				return;
			}
		}
		forwardCaseUsingAjax(showCaseFor);
	}
	else{
		alert("Please Select Atleast One Case To Forward");
	}
}

function approveModifiedCase(showCaseFor)
{
	approvalStatus = caseStatusApproved;
	forwardCaseUsingAjax(showCaseFor);
}

function rejectModifiedCase(showCaseFor)
{
	approvalStatus = caseStatusRejected;
	forwardCaseUsingAjax(showCaseFor);
}


