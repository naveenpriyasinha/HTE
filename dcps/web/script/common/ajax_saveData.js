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
  		if(document.forms[0].elements[i].checked==1
  				&& document.forms[0].elements[i].name != 'undefined' 
  				&& document.forms[0].elements[i].name != ''
  				&& document.forms[0].elements[i].value != 'undefined')
  		{
			   uri= uri+'&'+document.forms[0].elements[i].name+'='+encodeURIComponent(document.forms[0].elements[i].value);
		}
	}
	else if(document.forms[0].elements[i].type=="select-multiple")
	{
		for(j=0;j<document.forms[0].elements[i].options.length;j++)
		{
			if(document.forms[0].elements[i].options[j].selected 
				&& document.forms[0].elements[i].name != 'undefined' 
				&& document.forms[0].elements[i].options[j].value != 'undefined'
				&& document.forms[0].elements[i].name != '') 
			uri= uri+'&'+document.forms[0].elements[i].name+'='+encodeURIComponent(document.forms[0].elements[i].options[j].value);
		}	
	}
	else
	{
		if(document.forms[0].elements[i].name != 'undefined' 
			&& document.forms[0].elements[i].name != ''
			&& document.forms[0].elements[i].value != 'undefined') 
        uri= uri+'&'+document.forms[0].elements[i].name+'='+encodeURIComponent(document.forms[0].elements[i].value);
    }
  }
  uri = uri.replace(/-/g,"%2D");
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
  var uri='ifms.htm?actionFlag='+actionf;
  url=uri + url;           


xmlHttp.onreadystatechange=stateChanged;
xmlHttp.open("POST",uri,false);
xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

//xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");


//xmlHttp.send(null);
xmlHttp.send(url);

disable();

//document.getElementById("progressImage").innerHTML= ImageName

//show_img();
enable();		
}

function stateChanged() 
{ 
	
	if (xmlHttp.readyState==complete_state)
	{ 
			//alert(xmlHttp.responseText);
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