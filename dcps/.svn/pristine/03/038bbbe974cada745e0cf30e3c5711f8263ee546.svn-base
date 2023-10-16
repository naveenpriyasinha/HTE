function openDrillDownReport(url)
{
	var dt = new Date();
	wndName = "hdiits" + dt.getMilliseconds();
	var childWindow = window.open(url,wndName,'width=610, height=280, top=0, left=0, resizable=no, titlebar=no, menubar=no, scrollbars=yes, toolbar=no');
	childWindow.moveTo( 0, 0 );
    childWindow.resizeTo( screen.availWidth, screen.availHeight );
    childWindow.focus();    
    
    win = top;
	win.opener = top;	
	win.close ();	
}