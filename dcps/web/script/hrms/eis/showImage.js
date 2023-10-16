//var t_id = setInterval(animate,20);
var pos=0;
var dir=2;
var len=0;
function animate()
{
var elem = document.getElementById('progress');
if(elem != null) {
if (pos==0) len += dir;
if (len>32 || pos>79) pos += dir;
if (pos>79) len -= dir;
if (pos>79 && len==0) pos=0;
elem.style.left = pos;
elem.style.width = len;
}
}

function remove_loading() {
this.clearInterval(t_id);
var targelem = document.getElementById('loader_container');
targelem.style.display='none';
targelem.style.visibility='hidden';
var t_id = setInterval(animate,60);
}



var offsetfrommouse=[15,25]; //image x,y offsets from cursor position in pixels. Enter 0,0 for no offset
var displayduration=0; //duration in seconds image should remain visible. 0 for always.

var defaultimageheight = 40;	// maximum image size.
var defaultimagewidth = 40;	// maximum image size.

var timer;

function gettrailobj(){
if (document.getElementById)
return document.getElementById("preview_div").style
}

function gettrailobjnostyle(){
if (document.getElementById)
return document.getElementById("preview_div")
}


function truebody(){
return (!window.opera && document.compatMode && document.compatMode!="BackCompat")? document.documentElement : document.body
}


function hidetrail(){	
	gettrailobj().display= "none";
	document.onmousemove=""
	gettrailobj().left="-500px"
	clearTimeout(timer);
}

function showtrail(imagename,title,width,height,attId,attSrno){
	i = imagename
	t = title
	w = width
	h = height
	a = attId
	s = attSrno
	x = event.clientX;
	y = event.clientY;
	timer = setTimeout("show('"+i+"',t,w,h,a,s,x,y);",200);
}
function show(imagename,title,width,height,attId,attSrno,xCordinate,yCordinate){
 
    var docwidth=document.all? truebody().scrollLeft+truebody().clientWidth : pageXOffset+window.innerWidth - offsetfrommouse[0];
	var docheight=document.all? Math.min(truebody().scrollHeight, truebody().clientHeight) : Math.min(window.innerHeight);
	
	if( (navigator.userAgent.indexOf("Konqueror")==-1  || navigator.userAgent.indexOf("Firefox")!=-1 || (navigator.userAgent.indexOf("Opera")==-1 && navigator.appVersion.indexOf("MSIE")!=-1)) && (docwidth>650 && docheight>500)) {
		( width == 0 ) ? width = defaultimagewidth: '';
		( height == 0 ) ? height = defaultimageheight: '';
			
		width+=30
		height+=55
		defaultimageheight = height
		defaultimagewidth = width
	
		document.onmousemove=followmouse; 
		
		gettrailobj().left=xCordinate + offsetfrommouse[0] +"px";
		gettrailobj().top=yCordinate + offsetfrommouse[1] +"px";

		newHTML = '<div style="width:'+ width +'px;height:'+ height +'px;border:0.3mm groove black;"><div id="loader_container"><div id="loader"><div align="center">Loading template preview...</div><div id="loader_bg"><div id="progress"> </div></div></div></div>';
		
    	newHTML = newHTML + '<div class="preview_temp_load"><img id="'+ imagename +'" style="width:'+ width +'px;height:'+ height +'px" onload="javascript:remove_loading();" border="0"></div>';
		newHTML = newHTML + '</div>'; 
		
		gettrailobjnostyle().innerHTML = newHTML;
		
		eval("document.getElementById('"+imagename+"').src= 'hdiits.htm?actionFlag=viewAttachment&attachmentId="+attId+"&attachmentSerialNumber="+attSrno+"'");
		
		gettrailobj().display="block";
	}
}

function followmouse(e){
	
	var xcoord=offsetfrommouse[0];
	var ycoord=offsetfrommouse[1];

	var docwidth=document.all? truebody().scrollLeft+truebody().clientWidth : pageXOffset+window.innerWidth-15;
	var docheight=document.all? Math.min(truebody().scrollHeight, truebody().clientHeight) : Math.min(window.innerHeight);

	if (typeof e != "undefined"){
		if (docwidth - e.pageX < defaultimagewidth + 2*offsetfrommouse[0]){
			xcoord = e.pageX - xcoord - defaultimagewidth; // Move to the left side of the cursor
		} else {
			xcoord += e.pageX;
		}
		if (docheight - e.pageY < defaultimageheight + 2*offsetfrommouse[1]){
			ycoord += e.pageY - Math.max(0,(2*offsetfrommouse[1] + defaultimageheight + e.pageY - docheight - truebody().scrollTop));
		} else {
			ycoord += e.pageY;
		}

	} else if (typeof window.event != "undefined"){
		if (docwidth - event.clientX < defaultimagewidth + 2*offsetfrommouse[0]){
			xcoord = event.clientX + truebody().scrollLeft - xcoord - defaultimagewidth; // Move to the left side of the cursor
		} else {
			xcoord += truebody().scrollLeft+event.clientX;
		}
		if (docheight - event.clientY < (defaultimageheight + 2*offsetfrommouse[1])){
			ycoord += event.clientY + truebody().scrollTop - Math.max(0,(2*offsetfrommouse[1] + defaultimageheight + event.clientY - docheight));
		} else {
			ycoord += truebody().scrollTop + event.clientY;
		}
	}
	gettrailobj().left=xcoord+"px";
	gettrailobj().top=ycoord+"px";
}