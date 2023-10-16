<%
	for(int i=65;i<91;i++)
	{
%>
<a onmouseover="style.cursor='hand'" onclick="funcToCallForParentFrame('<%=(char)i%>')"><u><font color="blue"><%=(char)i%></font></u>&nbsp;&nbsp;&nbsp;</a>
<%
	}
%>
<script>
function funcToCallForParentFrame(tagValue)
{
	parent.frames.fetchLocationByStartTag(tagValue);
}	

</script>