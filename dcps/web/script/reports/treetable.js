function toggleRowsRpt(elm) 
{
 	var rows = document.getElementsByTagName("TR");
	//elm.style.backgroundImage = "url(expand.gif)";
	
	
	if(imagePath == null)
	{
		elm.getElementsByTagName('font')[0].innerText = " + ";
		//elm.innerText = " + ";
	}
	else elm.getElementsByTagName('img')[0].src = imagePath + "/tree_expand.gif";
 
	var newDisplay = "none";
	var thisID = elm.parentNode.parentNode.parentNode.id + "-";
 
 	// Are we expanding or contracting? If the first child is hidden, we expand
  	for (var i = 0; i < rows.length; i++) 
  	{
   		var r = rows[i];
   		if (matchStartRpt(r.id, thisID, true)) 
   		{
		    if (r.style.display == "none") 
		    {
			     if (document.all) newDisplay = "block"; //IE4+ specific code
			     else newDisplay = "table-row"; //Netscape and Mozilla
			     //elm.style.backgroundImage = "url(collapse.gif)";
			     if(imagePath == null)
				{
						elm.getElementsByTagName('font')[0].innerText = " - ";
//					elm.innerText = " - ";
				}
			    else elm.getElementsByTagName('img')[0].src = imagePath + "/tree_collapse.gif";
    		}
    	break;
   		}
 	}
 
	 // When expanding, only expand one level.  Collapse all desendants.
	 var matchDirectChildrenOnly = (newDisplay != "none");

	for (var j = 0; j < rows.length; j++) 
	{
	   var s = rows[j];
	   if (matchStartRpt(s.id, thisID, matchDirectChildrenOnly)) 
	   {
    		s.style.display = newDisplay;
	 	    var cell = s.getElementsByTagName("TD")[0];
	    	var tier = cell.getElementsByTagName("DIV")[0];
		    var folder = tier.getElementsByTagName("A")[0];

		    if (folder.getAttribute("onclick") != null) 
		    {
		      //folder.style.backgroundImage = "url(expand.gif)";
			    if(imagePath == null)
				{
						folder.getElementsByTagName('font')[0].innerText = " + ";
//					folder.innerText = " + ";
				}
			    else folder.getElementsByTagName('img')[0].src = imagePath + "/tree_expand.gif";
     		}
   		}
 	}
 	
}

function matchStartRpt(target, pattern, matchDirectChildrenOnly) 
{
	var pos = target.indexOf(pattern);
	if (pos != 0) return false;
	if (!matchDirectChildrenOnly) return true;
	if (target.slice(pos + pattern.length, target.length).indexOf("-") >= 0) return false;
	return true;
}

function collapseAllRowsRpt() 
{

	var rows = document.getElementsByTagName("TR");
	for (var j = 0; j < rows.length; j++) 
	{
		var r = rows[j];
		if (r.id.indexOf("-") >= 0) 
		{
		     r.style.display = "none";    
   		}
 	}
}

function expandAllRowsRpt() 
{
	var rows = document.getElementsByTagName("TR");
	for (var j = 0; j < rows.length; j++) 
	{
		var r = rows[j];
		if (r.id.indexOf("-") >= 0) 
		{
		     r.style.display = "block";    
   		}
   		
   		try{
		     var imageSrc = r.getElementsByTagName('img')[0].src;
		     var match = imageSrc.search(/tree_expand.gif/);
		     if(match != -1)
         	 {
         	 	r.getElementsByTagName('img')[0].src = imagePath + "/tree_collapse.gif";
         	 }
       }catch(e){}
       
 	}
}