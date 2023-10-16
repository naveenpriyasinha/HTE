<html>
<head>
<Title>HDIITS</Title>
<style type="text/css">
<!--
.BlkLinks {  color: #000000}
.style1 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: x-small;
}
.style2 {color: #004b68}
-->
</style>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>
 
<% String serverUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath(); %>
<link rel="stylesheet"  >

<body bgcolor="#FFFFFF" vlink="a41c2b" alink="a41c2b" leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
<table width="627" border="0" align="center" cellpadding="10" cellspacing="0">
  <tr>
    <td width="607" height="186" align="center" valign="top"><br>
        <table border="0" cellspacing="1" cellpadding="4" bgcolor="#D6CFB1">
          <tr bgcolor="#FFFFFF">
            <td width="28" height="50" align="center" valign="middle" bgcolor="#efefe7"><font size="2" face="Arial, Helvetica, sans-serif"><img src="images/loginImg/error.gif" width="28" height="29"></font></td>
            <td width="71" height="50" align="center" bgcolor="#ffffff"><b><font size="2" face="Arial, Helvetica, sans-serif" color="#000000">Error</font></b></td>
            <td width="485" height="50" valign="middle"><p><font size="2" face="Arial, Helvetica, sans-serif"><font size="2">Your Session has expired </font> </font></p></td>
          </tr>
          <tr bgcolor="#FFFFFF">
            <td width="28" height="94" align="center" valign="middle" bgcolor="#efefe7"><b><font size="2" face="Arial, Helvetica, sans-serif"><img src="images/loginImg/suggest.gif" width="28" height="29"></font></b></td>
            <td width="71" height="94" align="center" bgcolor="#ffffff"><b><font size="2" face="Arial, Helvetica, sans-serif" color="#000000">Suggestion</font></b></td>
            <td height="94" bgcolor="#FFFFFF">            <div align="center">
              <table width="462" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td width="8" height="86" align="center" valign="top">&nbsp;</td>
                  <td width="454" height="86" align="left" valign="top"><div align="justify">
                    <table width="521" border="0" cellspacing="0" cellpadding="0">
                      <tr valign="top">
                        <td width="14"><img src="images/loginImg/blackbullet.gif" width="13" height="15"></td>
                        <td width="507"><span class="style1"> </span>
                          <p align="justify" class="style1"> For&nbsp;security reasons, we have disabled double clicks and Back, Forward and Refresh tabs of the browser. Also,&nbsp;the&nbsp;session will expire automatically, if the browser window is idle for a long time. <br>
                            <br>
                          </p>                          </td>
                      </tr>
                      <tr valign="top">
                        <td width="14" height="3"></td>
                        <td width="507" height="3"></td>
                      </tr>
                      <tr valign="top">
                        <td><img src="images/loginImg/blackbullet.gif" width="13" height="15"></td>
                        <td><p align="justify" class="style1"> If the problem persists, please try again after clearing the <strong>Temporary Files </strong>from your web browser. <br>
                          <br>
                        </p>                          </td>
                      </tr>
                      
                      <tr valign="top">
                        <td width="14" height="3"></td>
                        <td width="507" height="3"></td>
                      </tr>
                      <tr>
                        <td><img src="images/loginImg/blackbullet.gif" width="13" height="15"></td>
                        <td><div align="justify"> <a href='<%= serverUrl %>' class="style2 style1">Click here to go to Login Page </a> </div></td>
                      </tr>
                    </table>
                  
                  </div></td>
                </tr>
              </table>
            </div></td>
          </tr>
    </table></td>
  </tr>
</table>
</body>
</html>

