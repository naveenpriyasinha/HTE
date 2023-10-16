function SaveDdoData()
{
	if(validDdoInfo() == true)
	{
		uri = "ifms.htm?actionFlag=addDdoInfo";
		saveDDODetailsUsingAjx(uri);
	}
}

function validDdoInfo()
{
	if(chkEmpty(document.getElementById("cmbDdoCode"),'Ddo Code')==false)
	{
		return false;
	}
	if(chkEmpty(document.getElementById("cmbAdminDept"),'Admin Department')==false)
	{
		return false;
	}
	if(chkEmpty(document.getElementById("cmbFieldDept"),'Field Department')==false)
	{
		return false;
	}
	return true;
}

function saveDDODetailsUsingAjx(uri)
{
   var url = runForm(0); 
   url = url + "&UserType=SRKA";
   
   var myAjax = new Ajax.Request(uri,
	       {
	        method: 'post',
	        asynchronous: false,
	        parameters:url,
	        onSuccess: function(myAjax) {
	   			ddocaseStateChanged(myAjax);
			},
	        onFailure: function(){ alert('Something went wrong...');} 
	          } );
}

function ddocaseStateChanged(myAjax) 
{ 
			XMLDoc = myAjax.responseXML.documentElement;
			var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');				
			var test_Id = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
			var ddoExistsOrNot = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;
			
			if(test_Id)
			{
				if(ddoExistsOrNot == 'true')
				{
					alert("This DDO is already added.");
				}
				if(ddoExistsOrNot == 'false')
				{
					alert("Record Saved Successfully.");
					self.location.reload(true);
				}
			}	
}