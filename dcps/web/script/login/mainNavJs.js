
if(navigator.userAgent.toLowerCase().indexOf('edg') >1){
	document.addEventListener("onload",mladdevents);
	}
else if(navigator.userAgent.toLowerCase().indexOf('chrome') >1){
	document.addEventListener("onload",mladdevents,true );
    
}else{
	document.attachEvent("onload",mladdevents);
}

//window.attachEvent("onload",mladdevents,false);
var mouseUsed=false;
var escPressed= false;
		function mladdevents(){
			var Iframe;
				if(window.mlrunShim == true){
					Iframe = document.createElement("iframe");
					Iframe.setAttribute("src","about:blank");
					Iframe.setAttribute("scrolling","no");
					Iframe.setAttribute("frameBorder","0");
					Iframe.style.zIndex = "2";
					Iframe.style.filter = 'alpha(opacity=0)';
					Iframe.setAttribute("style","position:absolute;");
				}
				var effects_a = new Array();
				var divs = document.getElementsByTagName('div');
				for(var j=0;j<divs.length;j++){
					if(divs[j].className.indexOf('mlmenu') != -1){
						var lis = divs[j].getElementsByTagName('li');
						for(var i =0;i<lis.length;i++){
						
							lis[i].onmouseover = mlover1;
							lis[i].onkeydown = mlover2;   //changed by varalika
							
							lis[i].onmouseout = mloutSetTimeout;
							//lis[i].onkeypress = mloutSetTimeout;
							lis[i].ondblclick = mlout;
							lis[i].onhelp = mloutEsc;
							
							if(window.mlrunShim == true){
								lis[i].appendChild(Iframe.cloneNode(false));
							}
							if(lis[i].getElementsByTagName('ul').length > 0){
								lis[i].className += ' haschild';
								lis[i].style.zIndex = '10000';
								if(divs[j].className.indexOf('arrow') != -1){
									if(divs[j].className.indexOf('vertical') != -1 || lis[i].parentNode.parentNode.nodeName != 'DIV'){
										lis[i].getElementsByTagName('a')[0].innerHTML += '<span class="vert">&nbsp;</span>';
										lis[i].getElementsByTagName('a')[0].className += ' vert vertNext';
									}
									else{
										lis[i].getElementsByTagName('a')[0].innerHTML += '<span class="horz">&nbsp;</span>';
										lis[i].getElementsByTagName('a')[0].className += ' horz';
									}
								}
								else if(divs[j].className.indexOf('plus') != -1){
									lis[i].getElementsByTagName('a')[0].innerHTML += '<span class="plus">+</span>';
								}
							}
							else{
								if(divs[j].className.indexOf('arrow') != -1){
									//This accounts for a wierd IE-specific bug in horizontal menus. CSS will set visibility: hidden;. This keeps the menu level(in IE)
									lis[i].getElementsByTagName('a')[0].innerHTML += '<span class="noshow">&darr;</span>';
								}
							}
							var uls = lis[i].getElementsByTagName('ul');
							for(var k=0;k<uls.length;k++){
								var found = 'no';
								for(var z=0;z<effects_a.length;z++){
									if(effects_a[z] == uls[k]){
										found = 'yes';
									}
								}
								if(found == 'no'){
									effects_a[effects_a.length] = uls[k];
									uls[k].style.zIndex = '100';
									mlEffectLoad(uls[k]);
								}
							}
						}
					}
				}
			}
			function mloutEsc(e){
				//alert('in mloutEsc')
				if(!e){
					var the_e = window.event;
				}
				else{
					var the_e = e;
				}
				//alert('if else workde')
				// var reltg = (the_e.relatedTarget) ? the_e.relatedTarget : the_e.toElement;
				var reltg = true;
				//alert('got the reltg'+reltg);
				if(reltg){
					var under = ancestor(reltg,this);
					//alert('fetched the ancestor')
					if(under === false && reltg != this){
						window.mlLast = this;
						var parent = this.parentNode;
						while(parent.parentNode && parent.className.indexOf('mlmenu') == -1){
							parent = parent.parentNode;
						}
						if(parent.className.indexOf('delay') != -1){
							window.mlTimeout = setTimeout(function(){mlout()},500);
						}
						else{
							escPressed = true;
							mlout();
						}
					}
				}
			}
			
			function mloutSetTimeout(e){
				//alert('in mloutSetTimeout')
			 	if(mouseUsed==true)
			 	{
			 	resetLastSelectedLI();
			 	}
				if(!e){
					var the_e = window.event;
				}
				else{
					var the_e = e;
				}
				//alert('if else workde')
				 var reltg = (the_e.relatedTarget) ? the_e.relatedTarget : the_e.toElement;
				//var reltg = true;
				//alert('got the reltg'+reltg);
				if(reltg){
					var under = ancestor(reltg,this);
					//alert('fetched the ancestor')
					if(under === false && reltg != this){
						window.mlLast = this;
						var parent = this.parentNode;
						while(parent.parentNode && parent.className.indexOf('mlmenu') == -1){
							parent = parent.parentNode;
						}
						if(parent.className.indexOf('delay') != -1){
							window.mlTimeout = setTimeout(function(){mlout()},500);
						}
						else{
							mlout();
						}
					}
				}
			}
			function mlout(e)
			{
			//alert('in mlout :: ' + window.mlLast.innerHTML)
			if(window.mlLast==null)
			return false;
			
			
			if(!e){
					var the_e = window.event;
				}
				else{
					var the_e = e;
				}
			
			var lastSelectedLI = null;
			lastSelectedLI = getLastSelectedLI();
					
			if (mouseUsed == false && this.innerHTML == undefined && escPressed == false)
			{
				return false;
			}
			
			if ( lastSelectedLI != null)
			{
				if ( lastSelectedLI.parentNode.parentNode.id != 'mlmenu'  )
				{
					if ( isSelectedLI(lastSelectedLI.parentNode.parentNode ) == true)
					{
						//alert ('going to reste');
						resetLastSelectedLI();
						//lastSelectedLI.parentNode.parentNode.parentNode.parentNode.fireEvent("onkeydown");
						the_e.cancelBubble = true;
						//alert ('cancel');
						window.mlLast=lastSelectedLI.parentNode.parentNode;
						lastSelectedLI.fireEvent("ondblclick");
						return false;
					}
				
				}
			
			}
				var uls = window.mlLast.getElementsByTagName('ul');
				
				var aNode = window.mlLast.childNodes[0];
				var id = aNode.id;				
				//var textNode = aNode.childNodes[0];
				//var textValue = textNode.nodeValue;
				
				var sib;
				for(var i=0;i<uls.length;i++)
				{					
					mlEffectOut(uls[i]);
					if(window.mlrunShim == true){
						sib = uls[i];							
						while(sib.nextSibling && sib.nodeName != 'IFRAME')
						{
								sib = sib.nextSibling	
						}
						sib.style.display = 'none';
					}
				}
				if(id == 'toolsId')
				{
					//document.getElementById("hdrMenuDiv").style.visibility = 'hidden';
					var rootObject = document.getElementById("hdrMenuDiv");
					mlEffectOut(rootObject);
				}
				window.lastover = null;				
			}
			
			
			function closeMenuOnMouse()
			{
				 // alert('came here');
					//alert('remove')
				resetLastSelectedLI();
				
				if(getLastSelectedLI() != null && mouseUsed == false)
				{
					var lastSelectedLi = getLastSelectedLI();
					if(lastSelectedLi.parentNode.parentNode.id == 'mlmenu' )
					{
						lastSelectedLi.getElementsByTagName('a')[0].className='parentMenu horz';
					}
					else
					{
						lastSelectedLi.getElementsByTagName('a')[0].className = 'vert';
					}
					// alert('CALLED ondblclick')
					lastSelectedLi.fireEvent("onhelp");
					menuHotKeyPress = false;
				}
			}
			function closeMenuOnF8()
			{
				
				if(getLastSelectedLI() != null)
				{
				
					var lastSelectedLi = getLastSelectedLI();
				
					if(lastSelectedLi.parentNode.parentNode.id == 'mlmenu' )
					{
						
						lastSelectedLi.getElementsByTagName('a')[0].className='parentMenu horz';
					}
					else
					{	
					
						lastSelectedLi.getElementsByTagName('a')[0].className = 'vert';
					}
					// alert('CALLED ondblclick')
					lastSelectedLi.fireEvent("onhelp");
					menuHotKeyPress = false;
				}
			}
			
			function mlover1(e)
			{
				
				var lastSelectedLi = getLastSelectedLI();
				
				if ( lastSelectedLi != null &&  lastSelectedLi.parentNode.parentNode.id != 'mlmenu' )
				{
					//alert ('copmign');
					var found = false;
					
					if ( lastSelectedLi == this)
					{
						found = true;
					}
					
					if (found == false)
					{
					
						var curObj = lastSelectedLi.nextSibling;
						while (true)
						{
							if ( curObj != null)
							{
								if ( curObj == this)
								{
									//alert ('found in next');
									found = true;
									break;
								}
							}
							else
							{
								break;
							}
							curObj = curObj.nextSibling;
						}
					
					}
						
					
					if (found == false)
					{
						var curObj = lastSelectedLi.previousSibling;
						while (true)
						{
							if ( curObj != null)
							{
								//if(this.getElementsByTagName('a')[0].className.indexOf('vertHover') != -1)
								if ( curObj == this)
								{
									//alert ('found in pre');
									found = true;
									break;
								}
							}
							else
							{
								break;
							}
							curObj = curObj.previousSibling;
						}
					}
					
					if (found == true)
					{
						//alert ("relset last selected LI");
						resetLastSelectedLI();
						lastSelectedLi = null;
					}
					
				}
				
				
				
				if(mouseUsed == false && lastSelectedLi != null)
				{
					//alert ("comign to resaet for keyboerad opend menu");
					//var lastSelectedLi = getLastSelectedLI();
					if(lastSelectedLi.parentNode.parentNode.id == 'mlmenu' )
					{
						lastSelectedLi.getElementsByTagName('a')[0].className='parentMenu horz';
					}
					else
					{	
						lastSelectedLi.getElementsByTagName('a')[0].className = 'vert';
					}
			
					// alert('CALLED ondblclick')
					lastSelectedLi.fireEvent("onhelp");
				}
				
			// menuHotKeyPress = false;
			mouseUsed=true;
			
			if(!e){
					var the_e = window.event;
				}
				else{
					var the_e = e;
				}
			
				//alert ('F8 in mlOver');
						
				the_e.cancelBubble = true;
				if(the_e.stopPropagation){
					the_e.stopPropagation();
				}
				clearTimeout(window.mlTimeout);
				if(window.mlLast && window.mlLast != this && ancestor(this,window.mlLast) == false){
					mlout();
				}
				else{
					window.mlLast = null;
				}
				var reltg = (the_e.relatedTarget) ? the_e.relatedTarget : the_e.fromElement;
				var ob = this.getElementsByTagName('ul');
				
				
				
				var under = ancestor(reltg,this);
				//alert ('Tag Name' + this.innerHTML);
				
				//noshow alert(this.getElementsByTagName('span')[0].className)
				
				if(this.getElementsByTagName('span')[0].className.indexOf('noshow') != -1)
				{
					this.getElementsByTagName('a')[0].className = 'vertHover';
				}
				else
				{
					this.getElementsByTagName('a')[0].className = 'vertHover hoverNext';
				}
				
				if(ob[0] && under == false){
					if(window.lastover != ob[0]){
						if(window.mlrunShim == true){
							var sib = ob[0];
							while(sib.nextSibling && sib.nodeName != 'IFRAME'){
								sib = sib.nextSibling	
							}
							ob[0].style.display = 'block';
							//var lastSelectedLI = getLastSelectedLI();
							//alert ('Tag Name' + this.innerHTML);
							//this.getElementsByTagName('a')[0].className = 'vertHover';
							//ob[0].getElementsByTagName('a')[0].className = 'vertHover';
							sib.style.top = ob[0].offsetTop+'px';
							sib.style.left = ob[0].offsetLeft-2+'px';
							sib.style.width = ob[0].offsetWidth+'px';
							if(ob[0].offsetHeight >= 2)
							{
								sib.style.height = ob[0].offsetHeight-2+'px';
							}
							sib.style.border = '1px solid red';
							sib.style.display = 'block';
							
							//alert ('hello');
						}
						mlEffectOver(ob[0],this);
						window.lastover = ob[0];
					}
				}
				
		//	alert('in mlover1');
			}
			function mlover2(e){
			if(mouseUsed== true)
			{
			// resetLastSelectedLI();
			mouseUsed=false;
			}
				if(!e){
					var the_e = window.event;
				}
				else{
					var the_e = e;
				}
			
				//alert ('F8 in mlOver');
						
				the_e.cancelBubble = true;
				if(the_e.stopPropagation){
					the_e.stopPropagation();
				}
				clearTimeout(window.mlTimeout);
				if(window.mlLast && window.mlLast != this && ancestor(this,window.mlLast) == false){
					mlout();
				}
				else{
					window.mlLast = null;
				}
				var reltg = (the_e.relatedTarget) ? the_e.relatedTarget : the_e.fromElement;
				var ob = this.getElementsByTagName('ul');
				var under = ancestor(reltg,this);
				if(ob[0] && under == false){
					if(window.lastover != ob[0]){
						if(window.mlrunShim == true){
							var sib = ob[0];
							while(sib.nextSibling && sib.nodeName != 'IFRAME'){
								sib = sib.nextSibling
							}
							ob[0].style.display = 'block';
							if(ob[0].getElementsByTagName('span')[0].className.indexOf('noshow') != -1)
							{
								ob[0].getElementsByTagName('a')[0].className = 'vertHover';
							}
							else
							{
								ob[0].getElementsByTagName('a')[0].className = 'vertHover hoverNext';
							}
							sib.style.top = ob[0].offsetTop+'px';
							sib.style.left = ob[0].offsetLeft-2+'px';
							sib.style.width = ob[0].offsetWidth+'px';
							if(ob[0].offsetHeight >= 2)
							{
								sib.style.height = ob[0].offsetHeight-2+'px';
							}
							sib.style.border = '1px solid red';
							sib.style.display = 'block';
							//alert ('hello');
						}
						mlEffectOver(ob[0],this);
						window.lastover = ob[0];
					}
				}
				
			}
			function mlSetOpacity(ob,level){
				if(ob){
					//level is between 0 and 10
					//need to convert to decimal for standard
					var standard = level/10;
					//need to convert to 0-100 scale for IE filter
					var ie = level*10;
					ob.style.opacity = standard;
					ob.style.filter = "alpha(opacity="+ie+")"
				}
			}
			function mlIncreaseOpacity(ob){
					var current = ob.style.opacity;
					if(lastob == ob && lastop == current){
						//mlout has not interfered
						current = current *10;
						var upone = current +1;
						mlSetOpacity(ob,upone);
						lastob = ob;
						lastop = upone/10;
					}
			}
			function mlIncreaseHeight(ob){
				var current = parseInt(ob.style.height);
				var newh = current + 1;
				ob.style.height = newh+'px';
			}
			function mlIncreaseWidth(ob){
				var current = parseInt(ob.style.width);
				var newh = current + 1;
				ob.style.width = newh+'px';
			}
			function mlBlink(ob){
				var newb = '1px solid red';
				var old = '';
				if(ob.style.border==old){
					ob.style.border=newb;
				}
				else{
					ob.style.border=old;
					ob.style.borderTop = '1px solid';
				}
			}
			function mlShake(ob){
				var newp = '5px';
				var old = '';
				if(ob.style.paddingLeft==old){
					ob.style.paddingLeft=newp;
				}
				else{
					ob.style.paddingLeft=old;
				}
			}
			function mlEffectOver(ob,parent){
				switch(ob.className){
					case 'fade':
						ob.style.display = 'block';
						if(ob.style.opacity == 0){
							lastob = ob
							lastop = 0;
							for(var i = 1;i<=10;i++){
								setTimeout(function(){mlIncreaseOpacity(ob)},i*50);
							}
							setTimeout(function(){ob.style.filter = ''},500);
						}
						break;
					case 'blink':
						ob.style.display = 'block';
						for(var i=0;i<10;i++){
							setTimeout(function(){mlBlink(ob)},i*50);
						}
						break;
					case 'shake':
						ob.style.display = 'block';
						for(var i=0;i<10;i++){
							setTimeout(function(){mlShake(ob)},i*50);
						}
						break;
					case 'blindv':
						ob.style.display = 'block';
						if(ob.offsetHeight){
							var height = ob.offsetHeight
							ob.style.height = '0px';
							ob.style.overflow = 'hidden';
							for(var i=0;i<height;i++){
								setTimeout(function(){mlIncreaseHeight(ob)},i*3);
							}
							setTimeout(function(){ob.style.overflow='visible';},height*3)
						}
						break;
					case 'blindh':
						ob.style.display = 'block';
						if(ob.offsetWidth){
							var width = ob.offsetWidth;
							ob.style.width = '0px';
							ob.style.overflow = 'hidden';
							for(var i=0;i<width;i++){
								setTimeout(function(){mlIncreaseWidth(ob)},i*3);
							}
							setTimeout(function(){ob.style.overflow='visible';},width*3)
						}
						break;
					default:
						ob.style.display = 'block';
						break;
				}
			}
			function mlEffectOut(ob){
				switch(ob.className){
					case 'fade':
						mlSetOpacity(ob,0);
						ob.style.display = 'none';
						break;
					case 'blink':
						ob.style.border = '';
						ob.style.display = 'none';
						break;
					case 'shake':
						ob.style.paddingLeft = '';
						ob.style.display = 'none';
						break;
					default:
						ob.style.display = 'none';
						break;
				}
			}
			function mlEffectLoad(ob){
				var parent = ob.parentNode;
				while(parent.parentNode && parent.className.indexOf('mlmenu') == -1){
					parent = parent.parentNode;
				}
				if(parent.className.indexOf('fade') != -1){
						ob.style.display = 'none';
						ob.className = 'fade';
						mlSetOpacity(ob,0);
				}
				else if(parent.className.indexOf('blink') != -1){
					ob.className = 'blink';
					ob.style.display = 'none';
				}
				else if(parent.className.indexOf('shake') != -1){
					ob.className = 'shake';
					ob.style.display = 'none';
				}
				else if(parent.className.indexOf('blindv') != -1){
					ob.className = 'blindv';
					ob.style.display = 'none';
				}
				else if(parent.className.indexOf('blindh') != -1){
					ob.className = 'blindh';
					ob.style.display = 'none';
				}
				else{
					ob.className = 'none';
					ob.style.display = 'none';
				}
			}
			function ancestor(child, parent){
				//alert('in ancestor')
				if(child==null)return false;//Saves checking elsewhere
				//This is a fix for a Firefox bug *gasp*
				//Aparantly causes a bug in Opera!
				//I see no choice but a browser detect. *sigh* I didn't want to have to do this.
				if(navigator.userAgent.indexOf('Gecko') != -1 && navigator.userAgent.indexOf('Opera') == -1){
					//This should only be run by Gecko based browsers. this code should be fine in everything but Opera so forge away browsers.
					var allc = parent.getElementsByTagName('*');
					for(var i= 0;i<allc.length;i++){
						if(allc[i] == child){
							return true;
						}
					}
				}
				else{
					//http://www.dynamicdrive.com/forums/showthread.php?t=12341 Thanks Twey!
					for(; child.parentNode; child = child.parentNode){
						if(child.parentNode === parent) return true;
					}
				}
				return false;
			}