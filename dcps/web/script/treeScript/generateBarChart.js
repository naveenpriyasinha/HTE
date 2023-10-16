		function Graph(size)
		{
		    this.parent = document.body;
		    this.adjustMin = false;
		    this.fontFamily = 'Arial';
		    this.titleColor = 'black';
		    this.yCaption = '';
		    this.yCaptionColor = 'black';
		    this.xCaption = '';
		    this.xCaptionColor = 'black';
		    this.barColor = '#009900';
		    this.barColor1 = '#990000';
		    this.xValues = new Array(size);
		    this.draw = DrawGraphH;
		}
		
		function change(bar)
		{
			bar.className="bar2";	
		}
		
		function retain(bar)
		{
			bar.className="bar";
		}
		
		// Draw horizontal graph
		function DrawGraphH()
		{
			bg = this;
		    var i, h, v, step, d=document;
		    var he, heTop, heAxis, heX;
		    var yMin, yMax, yStep, ratio;
		    
			bg.xWidth = 60;
		    // find the biggest value
		    yMax = -1;
		    for (i=0; i < bg.xValues.length; i++)
		    {
		        if (bg.xValues[i][0] > yMax)   
		        {
		        	yMax = bg.xValues[i][0];
		        }
		    }
		
		    // find the smallest value
		    if (!bg.adjustMin)
		    {
		        yMin = 0;
		    }
		    else
		    {
		        yMin = yMax;
		        for (i=0; i < bg.xValues.length; i++)
		        {
		            if (bg.xValues[i][0] < yMin)   
		            {
		            	yMin = bg.xValues[i][0];
		            }
		        }
		    }
		
		    // Calculate scale values
		    var n = Math.pow(10,Math.floor(Math.log(yMax) / Math.LN10));
		    var np = n / 10;
		    var nn = n * 10;
		
		    if (n > 1)
		    {
		        if ((yMax % n) == 0)
		        {
		            yMax += n;
		        }
		
		        yMin = Math.floor(yMin / n) * n;
		        yMax = Math.ceil(yMax / n) * n;
		    }
		    else
		    {
		        if ((yMax % n) == 0)
		        {
		            yMax += n;
		        }
		    }
		
		    ratio = 300 / (yMax - yMin); // 300 = y axis length in pixels
		
		    yStep = (yMax - yMin) / 8;
		    if(yStep <= np)
		    {
		    	yStep = np;
		    }
		    else if(yStep <= (2 * np)) 
		    {
		    	yStep = 2 * np;
		    }
		    else if(yStep <= (5 * np)) 
		    {
		    	yStep = 5 * np;
		    }
		    else                        
		    {
		    	yStep = n;
		    }
		
		    step=Math.round(yStep*ratio);  // scale step in pixels
		    heTop = 10;
		    he = step * ((yMax - yMin) / yStep); // total graph height in pixels
		    heAxis = 5;
		    heX = 50;
		
		    // Build style
		    d.writeln('<style>');
		    d.writeln('.graph { font-family: ' + bg.fontFamily + '; }');
		    d.writeln('.ycaption { font-weight:bolder; font-size: 10pt; writing-mode:tb-rl; }');
		    d.writeln('.xcaption { font-weight:bolder; font-size: 10pt; }');
		    d.writeln('.yvalue { font-size: 10pt; top=8; }');
	        d.writeln('.xvalue { font-size: 8pt; }');
		    d.writeln('.xyvalue { font-size: 8pt; }');
		    d.writeln('.bar { background-color: ' + bg.barColor + ';');
		    d.writeln('border-top-style: inset;'); 
	    	d.writeln('border-top-color: #00ff33;'); 
	    	d.writeln('border-top-width: 7px;'); 
	    	d.writeln('border-right-style: inset;'); 
	    	d.writeln('border-right-color: #00ff33;'); 
	    	d.writeln('border-right-width: 7px;');
	    	d.writeln('cursor: pointer;'); 
		    d.writeln('}');
		    d.writeln('.bar2 { background-color: ' + bg.barColor1 + ';');
		    d.writeln('border-top-style: inset;'); 
	    	d.writeln('border-top-color: #ff0033;'); 
	    	d.writeln('border-top-width: 7px;'); 
	    	d.writeln('border-right-style: inset;'); 
	    	d.writeln('border-right-color: #ff0033;'); 
	    	d.writeln('border-right-width: 7px;');
	    	d.writeln('cursor: pointer;'); 
		    d.writeln('}');
		    d.writeln('.datatable {}');
		    d.writeln('.gridtable {border-left:0px; border-right:0px; border-top:0px; border-bottom:0px;}');
		    d.writeln('</style>');
		
		    // Title
		    d.writeln('<table id=wrapper class=graph>');
//		    d.writeln('<tr><td colspan=' + (bg.xValues.length + 3) + ' align=center><h2>' + bg.title + '</h2></td></tr>');
		
		    // Y Caption
		    d.writeln('<tr><td>');
		    d.writeln('<table id=graph >');
		    d.writeln('<tr>');
		    d.writeln('<td valign=middle nowrap height=' + (heTop + he + heAxis + heX) + '>');
		    d.writeln('<SPAN class=ycaption><br>' + bg.yCaption + '</SPAN>');
		    d.writeln('</td>');
		
		    // Scale
		    d.writeln('<td valign=top id=scalewrapper > ');
		    d.writeln('<table class=yvalue id=scale>');
		
		    h=heTop;
		    for (i=yMax; i >= yMin; i-=yStep)
		    {
		        d.writeln('<tr><td height=' + h + ' valign=bottom align=right>' + i + '</td></tr>');
		        h=step;
		    }
		
		    d.writeln('</table>');
		    d.writeln('</td>');
		    // Plot Bars
		    for (var x=0; x < bg.xValues.length; x++)
		    {
		        v=bg.xValues[x][0];
		        d.writeln('<td id=abc_'+x+' valign=bottom >');
		
		        // Bar
		        h = Math.ceil((yMax-v)*ratio);
		        if (h > he) 
		        	h = he;
		        	
		        d.writeln('<table class=datatable>');
		        d.writeln('<tr><td><table align=center ><tr><td valign=bottom height=' + (heTop + h) + ' class=xyvalue style="border-left:0px; border-right:0px; border-top:0px; border-bottom:0px;"><a href='+bg.xValues[x][2]+'><b>' + v + '</b></a></td></tr></table></td></tr>');
		        h = he - h;
		        d.writeln('<tr><td height=' + h + ' width=100>');
		        d.write('<table width=50% align=center class=bar id=bar onmouseover="change(this)" onmouseout="retain(this)">');
		        d.writeln('<tr id="v"><a href='+bg.xValues[x][2]+'><td height=' + h + ' ></td></a></tr></table></td></tr>');
		        d.writeln('<tr><td height=' + heAxis + '></td></tr>');
				d.writeln('<tr><td><table height='+ heX +' width=100 id=xyz align=center><tr><td style="border-left:0px; border-right:0px; border-top:0px; border-bottom:0px;"><b><center>'+bg.xValues[x][1]+'</center></b></td></tr></table></td></tr>');
		        d.writeln('</table>')
		        d.writeln('</td>');
		    }
		
		    // X Caption
		    d.writeln('</tr>');
		    d.writeln('<tr>');
		    d.writeln('<td colspan=3 ></td>');
		    d.writeln('</tr>');
		    d.writeln('</table>');
		    d.writeln('</td></tr>');
//		    d.writeln('<tr><td colspan=' + (bg.xValues.length + 3) + ' align=center><h2>' + bg.xCaption + '</h2></td>');
			d.writeln('</table>');				
		    // Stablish parenthood
		    bg.parent.appendChild(d.getElementById('wrapper'));
		}