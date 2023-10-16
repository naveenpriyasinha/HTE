//TODO: Need to use the Gujarati language month name if possible
var montharray=new Array("January","February","March","April","May","June","July","August","September","October","November","December");
var dbdate=new Date();//TODO: uncomment after we get the db date time
var intSet = false;

function setCurrentDateTime(currentDateTime)
{
	dbdate = new Date(currentDateTime);
	
	// TODO: Following approach has one issue that if there is an alert
	// thne the clock stops. Thne when alert is gone the clock starts
	// from the time it stopped.
	//
	// start timer to show the current time on the toolbar
	// if( !intset )
	// setInterval("showtime()", 1000);

	// Show date only
	showdate();
}

function padstring(what){
	var output=(what.toString().length==1)? "0"+what : what;
	return output;
}

function showtime(){
	dbdate.setSeconds(dbdate.getSeconds()+1);
	var datestring=montharray[dbdate.getMonth()]+" "+padstring(dbdate.getDate())+", "+dbdate.getFullYear();
	var timestring=padstring(dbdate.getHours())+":"+padstring(dbdate.getMinutes())+":"+padstring(dbdate.getSeconds());
	document.getElementById("dbdate").innerHTML=datestring+" "+timestring;
}

function showdate(){
	dbdate.setSeconds(dbdate.getSeconds()+1);
	var datestring=montharray[dbdate.getMonth()]+" "+padstring(dbdate.getDate())+", "+dbdate.getFullYear();
	document.getElementById("dbdate").innerHTML=datestring;
}
