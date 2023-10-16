function show_img()
{
	document.getElementById('progressImage').style.display='';
}
function hide_img()
{
	document.getElementById('progressImage').style.display='none';
}
function enable_div()
{
	document.getElementById('statusBar').style.display='';
}
function blank()
{
	var totalElements= document.forms[0].elements.length
	for(i=0;i<totalElements;i++)
	{
		
		if(document.forms[0].elements[i].type=="text" || document.forms[0].elements[i].type=="textarea" ||  document.forms[0].elements[i].type=="file")
		{
						
						document.forms[0].reset()
		}	
	}
}
function enable()
{
	var totalElements= document.forms[0].elements.length
	for(i=0;i<totalElements;i++)
	{
		document.forms[0].elements[i].disabled=false
	}
}
function disable()
{
	var totalElements= document.forms[0].elements.length
	for(i=0;i<totalElements;i++)
	{
			document.forms[0].elements[i].disabled=true
	}
}
function run(){

var totalElements= document.forms[0].elements.length
  var uri='';
  for(i=0;i<totalElements;i++)
  {
  
   if(document.forms[0].elements[i].type=="checkbox" ||document.forms[0].elements[i].type=="radio")
  	{
  		if(document.forms[0].elements[i].checked==1)
  		{
			   uri= uri+'&'+document.forms[0].elements[i].name+'='+document.forms[0].elements[i].value;
		}
	}
	else if(document.forms[0].elements[i].type=="select-multiple")
	{
		var NewUserEntriesval=document.getElementById("NewUserEntries");	
			
		var strarray=new Array();

		var NewUserEntries=document.getElementById("NewUserEntries");
				for(var k=0;k<NewUserEntries.length;k++)
				{
					strarray[k]=document.forms[0].NewUserEntries.options[k].value;
				}
				uri= uri+'&'+document.forms[0].elements[i].name+'='+strarray;
			
		
	}
	else
	{
     		   uri= uri+'&'+document.forms[0].elements[i].name+'='+document.forms[0].elements[i].value;
    }
  }

  return uri;
 }

//The xmlHttp readyState is numbered 4 in complete state
var complete_state=4
var xmlHttp
var ImageName='<img src="common/images/ajax-loader.gif">';
function insertdt()
{ 
	xmlHttp=GetXmlHttpObject();
	if (xmlHttp==null)
	{
	  alert ("Your browser does not support AJAX!");
	  return;
	} 
	  
	var url=run(); 
	var actionf=document.forms[0].actionFlag.value
	var uri='hdiits.htm?actionFlag='+actionf;
	url=uri + url;           
	
	xmlHttp.onreadystatechange=stateChanged;
	xmlHttp.open("POST",encodeURI(url),true);
	xmlHttp.send(null);
	disable();
	document.getElementById("progressImage").innerHTML= ImageName
	show_img();		
}

function stateChanged() 
{ 
	
	if (xmlHttp.readyState==complete_state)
	{ 
		enable();
		hide_img();
		enable_div();
		document.getElementById("statusBar").innerHTML=xmlHttp.responseText;
		blank();
	}
}

function GetXmlHttpObject()
{
var xmlHttp=null;
try
  {
  xmlHttp=new XMLHttpRequest();
  }
catch (e)
  {
  try
    {
    xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
    }
  catch (e)
    {
    xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
  }
return xmlHttp;

}