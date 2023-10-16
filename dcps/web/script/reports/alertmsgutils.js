// prepares alert message and returns the message
function getAlertMessage( msg, flds )
{
    var alertMsg = msg;
    var i = 0;
    //alert( alertMsg.indexOf( '%' ) );
    while( alertMsg.indexOf( '%' ) >= 0 )
    {
        //alert( alertMsg );
        //alert( flds[i] );
        alertMsg = replaceString( alertMsg, "%", "'" + flds[i] + "'" );
        i += 1;
        if( i > flds.length )
        {
            break;
        }
    }
    //alert( alertMsg );
    return alertMsg;
}

// extract front part of string prior to searchString
function getFront(mainStr,searchStr)
{
    foundOffset = mainStr.indexOf(searchStr)
    if (foundOffset == -1) {
        return null
    }
    return mainStr.substring(0,foundOffset)
}

// extract back end of string after searchString
function getEnd(mainStr,searchStr) 
{
    foundOffset = mainStr.indexOf(searchStr)
    if (foundOffset == -1) {
        return null
    }
    return mainStr.substring(foundOffset+searchStr.length,mainStr.length)
}

// insert insertString immediately before searchString
function insertString(mainStr,searchStr,insertStr)
{
    var front = getFront(mainStr,searchStr)
    var end = getEnd(mainStr,searchStr)
    if (front != null && end != null) {
        return front + insertStr + searchStr + end
    }
    return null
}

// remove deleteString
function deleteString(mainStr,deleteStr)
{
    return replaceString(mainStr,deleteStr,"")
}

// replace searchString with replaceString
function replaceString(mainStr,searchStr,replaceStr)
{
    var front = getFront(mainStr,searchStr)
    var end = getEnd(mainStr,searchStr)
    if (front != null && end != null) {
        return front + replaceStr + end
    }
    return null
}