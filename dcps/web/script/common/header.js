function getposOffset(what, offsettype)
	{
		var totaloffset=(offsettype=="left")? what.offsetLeft : what.offsetTop;
		var parentEl=what.offsetParent;
		while (parentEl!=null)
		{
			totaloffset=(offsettype=="left")? totaloffset+parentEl.offsetLeft : totaloffset+parentEl.offsetTop;
			parentEl=parentEl.offsetParent;
		}
		return totaloffset;
	}

	function changeToolsMenuDisplayStatus(e)
	{
		if(e.type=="mouseover")
		{//show tools menu
		
			var element = document.getElementById("hdrMenuDiv");
			element.style.display='block';			
			//element.style.visibility="visible";
			pElement = document.getElementById("settingsId");
			
			var x = getposOffset(pElement, "left");
			var y = getposOffset(document.getElementById("nav"), "top");

			element.style.left=x;
			element.style.top= y-1;			
		}		
		else if(e.type=="mouseout")
		{//hide tools menu element if mouse focus is out of settings element and not on tools element
			
			var toEle = (e.relatedTarget)? e.relatedTarget : e.toElement ;
			var toEleParent = toEle;
			
			while(toEleParent.parentNode && toEleParent.nodeName != 'a' && toEleParent.nodeName != 'A')
			{
				toEleParent = toEleParent.parentNode;
			}

			var hide = true;

			if(toEleParent.nodeName == 'a' || toEleParent.nodeName == 'A')
			{
				//var textVal = toEleParent.childNodes[0].nodeValue;
				var nodeId = toEleParent.id;				

				if(nodeId == 'toolsId')
				{
					
					hide = false;//sets hide flag false if mouse focus is out of settings element and on tools element					
				}								
			}	

			if(hide)
			{
				var element = document.getElementById("hdrMenuDiv");
			    element.style.display='none';		
			}
		}
		
		
	}
	
	
	function closeTheApplication(ctx)
	{
		showProgressbar('Signing out...<br>Please wait...');
		ajaxfunctionForLogout(ctx);	
	}