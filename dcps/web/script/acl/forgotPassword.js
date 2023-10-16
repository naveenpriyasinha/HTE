function getUserIdFromUserName(ctx)
{
	showProgressbar();
	var userName = document.getElementById('User_name').value;
	document.getElementById('userName').value = userName;
	ajaxCallURL = ctx+'/hdiits.htm?actionFlag=getUserFromUserName&userName='+userName;
	var url_ps = ajaxCallURL;
    var myAjax = new Ajax.Request(url_ps,{
				method: 'get',
				asynchronous: true,
				onSuccess: function(transport){
					if (200 == transport.status) 
					{
						populateData(transport);
					}
		  }// transport 
		});
    hideProgressbar();
}
function populateData(transport)
{
	var XMLDoc=transport.responseXML.documentElement;
	var entries = XMLDoc.getElementsByTagName('element');
	if( entries.length != 0 )
	{
		var userid = entries[0].childNodes[0].text;
		if(userid != 0)
		{
			showHideControls(1);
			var SecretQueFromDB = entries[0].childNodes[1].text;
			var SecretAnsFromDB = entries[0].childNodes[2].text;
			document.getElementById('userId').value = userid;
			document.getElementById('SecretAnsFromDB').value = SecretAnsFromDB;
			document.getElementById('txtSecretQue').value = SecretQueFromDB;
			document.getElementById('secret_que').size = SecretQueFromDB.length;
			document.frmForgotPassword.secret_que.value = SecretQueFromDB;
			document.getElementById('errorUserMsgTd').innerHTML = "";
		}
		else
		{
			showHideControls(0);
			document.frmForgotPassword.secret_que.value = '';
			document.frmForgotPassword.Birth_Date.value = '';
			document.getElementById('txtSecretAns').value = '';
			document.getElementById('errorUserMsgTd').innerHTML = InvalidUserNameMsg;
		}
	}
	else
	{
		document.getElementById('User_name').value = '';
	}
	document.getElementById('secertInfoDiv').style.display='';
	hideProgressbar();
}

function showHideControls(show)
{
	if(show)
	{
		document.getElementById('Birth_Date_TR').style.display = '';
		document.getElementById('SECRET_QUE_TR').style.display = '';
		document.getElementById('SECRET_ANS_TR').style.display = '';
		document.getElementById('Note_TR').style.display = '';
	}
	else
	{
		document.getElementById('Birth_Date_TR').style.display = 'none';
		document.getElementById('SECRET_QUE_TR').style.display = 'none';
		document.getElementById('SECRET_ANS_TR').style.display = 'none';
		document.getElementById('Note_TR').style.display = 'none';
	}	
}