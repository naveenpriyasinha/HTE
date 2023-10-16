/* Added By Divyesh Jariwala to remove "Invalid Argument" error on Logout when any of the AJAX Tag has been used in screen. */

function setAjaxWindowName()
{
	glbWindowName = window.name;
}	

function setOriginalWindowName()
{
	window.name = glbWindowName;
}