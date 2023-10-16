function noTableRowForMultipleAdd(tableName)
{
	var Rowlength = document.getElementById(tableName).rows.length;
	flag=false;
	noDeletedRow=1;

	for(var i = 1; i < Rowlength; i++)
	{
		if(document.getElementById(tableName).rows[i].style.display=='none')
			noDeletedRow++;
	}
	if(noDeletedRow==Rowlength)
		flag=true;
	return flag;
}