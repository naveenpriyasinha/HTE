	
/* isFocusOnMainMenu : Function is used to check whether Main Menu is currently focused */
function isFocusOnMainMenu()
{
	//alert ('in isFocusOnMainMenu');
	var objMenuDiv = document.getElementById('mlmenu');
	var lis = objMenuDiv.getElementsByTagName('li');
	var selectedLI = null;
	selectedLI = getLastSelectedLI();
	
	/*
	//for(var i =0;i<lis.length;i++)
	for(var i =( lis.length-1); i>=0;i--)
	{
		var as = objMenuDiv.getElementsByTagName('a');
		//alert(lis[i].getElementsByTagName('a')[0].className);			
		if (lis[i].getElementsByTagName('a')[0].className.indexOf('vertHover') != -1)
		{
			selectedLI = lis[i];
			break;
		}
	}
	*/
	
	if ( selectedLI == null)
	{
		//alert (' selected LI not found');
		return false;
	}
	if ( selectedLI.parentNode.parentNode.id == 'mlmenu')
	{
		return true;
	}
	
	return false;
}

	

//To disable backspace,F3,F5 and F11 key on page --- starts
var keyPreview = 0;

var menuHotKeyPress = false;   // When menu get Activeated thru KB it will be set true, and when focus lost it will be set to false
var ajaxProgressBar = false;	// for menu disable when the progress bar is working

// Jignesh Sakhia's code starts
function setKeyPreview()
{

	var chkBox = document.getElementById('chkKeyPreview');
	if(chkBox.checked)
		keyPreview =1;
	else
		keyPreview =0;

		alert(chkBox.checked);
}

//Jignesh Sakhia's code ends

// the following code resets the focus(color)while hovering over with mouse or keyboard while navigating

function resetLastSelectedLI()
{	
	var selectedLI = null;
	selectedLI = getLastSelectedLI();
	//alert(selectedLI.innerHTML)
	if (selectedLI != null)
	{
		if(selectedLI.parentNode.parentNode.id == 'mlmenu' )
		{
			selectedLI.getElementsByTagName('a')[0].className='parentMenu horz';
		}
		else
		{
			if(selectedLI.getElementsByTagName('span')[0].className.indexOf('noshow') != -1)
			{
				selectedLI.getElementsByTagName('a')[0].className='vert';
			}
			else
			{
				selectedLI.getElementsByTagName('a')[0].className='vert vertNext';
			}
		}
		
	}
}

//the following code gives whether given LI is selected or not
function isSelectedLI(lis)
{
	// alert('lastSelectedLi')
	if (lis.getElementsByTagName('a')[0].className.indexOf('vertHover') != -1)
	{
		return true;
	}
	return false;
}


// the following code gives us the Last Selected LI
function getLastSelectedLI()
{
	// alert('lastSelectedLi')
	var objMenuDiv = document.getElementById('mlmenu');
	if (objMenuDiv == null)
	{
		return null;
	}
	var lis = objMenuDiv.getElementsByTagName('li');
	var selectedLI = null;
	if (lis[0] != null)
	{
		//for(var i =0;i<lis.length;i++)
		for(var i =( lis.length-1); i>=0;i--)
		{
			if (lis[i].getElementsByTagName('a')[0].className.indexOf('vertHover') != -1)
			{
				selectedLI = lis[i];
				break;
			}
		}
		// selectedLI.getElementsByTagName('a')[0].className='vert';
	}
	return selectedLI;
}


/*assign right as tab if the focus is on the content page */
function assignRightAsTab(){
					objDivContent  = document.getElementById("content");
					//var obj = document.getElementById(event.srcElement.id ) ;
					var obj = event.srcElement;
					while (true)
					{
						obj = obj.parentNode;
						if ( obj == null ) 
						{
							return;
						}
						
						if ( obj == objDivContent)
						{
							event.keyCode=9;
							return;
						}
						
					}
}
// when the mouse is clicked this method removes the focus from the menu  and closes it if keyboard was used
document.onclick = function()
{
	var lastSelectedLI = null;
	lastSelectedLI = getLastSelectedLI();
	
	if (lastSelectedLI != null)
	{
		lastSelectedLI.fireEvent("onhelp");
		resetLastSelectedLI();
		menuHotKeyPress = false;
	}
}
// Foll. function is called to replace "'" character with "`" - START - Jay
document.onkeypress = function()
{ 
	if(event.keyCode == 39)
	 event.keyCode = 96;
}
// Above function is called to replace "'" character with "`" - END - Jay
// this method specifies the entire F8,up,down,left,right keys and Esc and enter keys Jignesh Sakhia's code has also been integrated
document.onkeydown = function()
{ 
	if(ajaxProgressBar)  // Disable Menu a time of Progress Bar.
	{
		return;
	}
	/** Jignesh Sakhiya Code Start.....*/ 
	
	//the following code disables the F3,F5 and F11
	
	if ( 114 == event.keyCode || 116 == event.keyCode || 122 == event.keyCode)
	{
		event.keyCode = 0;
		return false;
	}		

	try
	{ 
		// if no LI is not selected then follow this code else let them function as per Jignesh Sakhia's Code
		if(getLastSelectedLI() == null)
		{ 
			if(event.keyCode == 115 )
			{
				try
				{
					miscFunction();
					event.keyCode = 0;
					return false;
				}catch(e)
				{}
			}
			if(event.keyCode == 113 || event.keyCode == 45 )
			{
				try
				{
					saveData();
				}catch(e)
				{
				
				}
			}
			if(event.keyCode == 33)
			{
				processPageUP();
			}
			if(event.keyCode == 34)
			{
				processPgeDOWN();
			}
			if(event.keyCode == 36)
			{
				processHOME();
			}
			if(event.keyCode == 35)
			{
				processEND();
			}
			if(event.keyCode == 120)
			{
				processF9();
			}
			// Process for F7(118) and F12(123) done by TBP Team - START
			
			// TO OPEN POP UP FOR BILL ON KEY PRESS OF F12 USING SEARCH OF TOKEN NO.
			if(event.keyCode == 123){
				try{
					processF12();
				}catch(e){}
			}
			//END
			
			// TO IMPLEMENT FUNCTIONALITY OF FORWARD BUTTON ON F7 BUTTON
			if(event.keyCode == 118){
				try{
					processF7();
				}catch(e){}
			}
			// END
			
			
			// if enter is pressed then let it work as tab if the element is not a button			
			if ((event.keyCode == 13 && (event.srcElement.type != 'button' && event.srcElement.type != 'submit' ) ))
			{
				event.keyCode = 9;	
			}
			
			// wiht left key the focus goes to the first element in the form 
			
			if(event.keyCode ==37)
			{
					var f =  document.forms[0];
					var c = event.srcElement.tabIndex-1;
					if(c==0) return;
					for(i=0;i<f.elements.length;i++)
					{
						if (f.elements[i].tabIndex== c )
						{		
							try
							{
								f.elements[i].focus();
								return;
							}catch(e1)
							{
								c--;
								i=0;
							}
						}
					}
			 }
			 
			 // right key functions as tab
			if(event.keyCode ==39)
			{
				/*assign right as tab if the focus is on the content page */
				assignRightAsTab();
					/*objDivContent  = document.getElementById("content");
					//var obj = document.getElementById(event.srcElement.id ) ;
					var obj = event.srcElement;
					while (true)
					{
						obj = obj.parentNode;
						if ( obj == null ) 
						{
							return;
						}
						
						if ( obj == objDivContent)
						{
							event.keyCode=9;
							return;
						}
						
					}*/
			}
			// for enter to work properly if enter alone is pressed e.g. add a row when enter is clicked
				
			if (event.keyCode == 13)
			{
				event.keyCode=0;
			}
			if(event.keyCode == 39) 		
		        event.keyCode = 96;
		}	
	}
	catch(e){}
		
	
	/** Jignesh Sakhiya Code End..... **/


	// KeyBoard Menu Navigation Cade Start.... owned Sagar.
	
	var objMenuDiv = document.getElementById('mlmenu');
	
	if(objMenuDiv != null)
	{
		// if the pressed key is up, down, left, right ,Esc and Enter
		if(event.keyCode == 13 || event.keyCode == 119 || event.keyCode == 27 || event.keyCode == 37 || event.keyCode == 38 || event.keyCode == 39 || event.keyCode == 40)
		{
			
			if(event.keyCode == 119) // F8 Focus the Main Menu
			{
				document.getElementById("header").focus(); // For move a focus of selected form element.
				var lis = objMenuDiv.getElementsByTagName('li');
				if(mouseUsed==true)
				{
					// if the menu is opened by mouse then close it before opening the menu thru keyboard
					closeMenuOnF8();
					
					if (lis[0] != null )
					{  
						// if element is present then display the menu on keydown event is specified in mainNavJs.js
						lis[0].fireEvent("onkeydown");
						
						// if the menu/ submenu has child then use one CSS else use the other one
						
						if(lis[0].getElementsByTagName('span')[0].className.indexOf('noshow') != -1)
						{
							lis[0].getElementsByTagName('a')[0].className = 'vertHover';
						}
						else
						{
							lis[0].getElementsByTagName('a')[0].className = 'vertHover hoverNext';
						}
						
						//remove the previous focus 
						resetLastSelectedLI();
						
						// specify whether F8 has been pressed or not
						menuHotKeyPress = true;
					}
				}
				else
				{	// if mouse has been used check that and open menu using F8 alone
					if (lis[0] != null && menuHotKeyPress == false)
					{
						lis[0].fireEvent("onkeydown");
						if(lis[0].getElementsByTagName('span')[0].className.indexOf('noshow') != -1)
						{
							lis[0].getElementsByTagName('a')[0].className = 'vertHover';
						}
						else
						{
							lis[0].getElementsByTagName('a')[0].className = 'vertHover hoverNext';
						}
						resetLastSelectedLI();
						
						menuHotKeyPress = true;
					
					}
				}
				mouseUsed= false;
			}
				
			// if enter is pressed on the menu item open the form/report related to it
			if(event.keyCode == 13 && getLastSelectedLI() != null ) // Enter Key
				{
					var lastSelectedLi = getLastSelectedLI();
					lastSelectedLi.getElementsByTagName('a')[0].fireEvent("onclick");			
					return false;
				}
			
			// close the entire menu when  Esc is pressed
			if(event.keyCode == 27) // escape 
			{
				if(getLastSelectedLI() != null)
				{
					var lastSelectedLi = getLastSelectedLI();
					
					if(lastSelectedLi.parentNode.parentNode.id == 'mlmenu' )
					{
						lastSelectedLi.getElementsByTagName('a')[0].className='parentMenu horz';
					}
					else
					{
						if(lastSelectedLi.getElementsByTagName('span')[0].className.indexOf('noshow') != -1)
						{
							lastSelectedLi.getElementsByTagName('a')[0].className = 'vert';
						}
						else
						{
							lastSelectedLi.getElementsByTagName('a')[0].className='vert vertNext';
						}
					}			
					// close all the opened menus and submenu
					lastSelectedLi.fireEvent("onhelp");
					menuHotKeyPress = false;
					
					setFocusOnFirstElementOfForm();
					
				}
				
				
				
			}
		
			if ( event.keyCode != 27 )
				escPressed = false;

			// on right for navigation through the menus
			if(event.keyCode == 39) //Right
			{
				var lis = objMenuDiv.getElementsByTagName('li');
				
				if (lis == null)
				{
					alert("error Lis Found null");
				}
				
				var selectedLI = null;
				// traversing thru the values in LI's backward to get the selected LI
				for(var i =( lis.length-1); i>=0;i--)
				{
					var as = objMenuDiv.getElementsByTagName('a');
					//alert(lis[i].getElementsByTagName('a')[0].className);			
					
					if (lis[i].getElementsByTagName('a')[0].className.indexOf('vertHover') != -1 )
					{
						selectedLI = lis[i];
						//alert ('selected found in right');
						break;
					}
				}
				
				// if the SelectedLi is got the remove the focus from it
				if (selectedLI != null) // IF there more item down then go
				{
					// if the focus is on main menu and the sibling is present the move right in the main menu
					if ( isFocusOnMainMenu() == true)  // in main menu
					{
						if(selectedLI.nextSibling != null)
						{
							// set the focus according to the children available
							if(selectedLI.nextSibling.getElementsByTagName('span')[0].className.indexOf('noshow') != -1)
							{
								selectedLI.nextSibling.getElementsByTagName('a')[0].className='vertHover';	
							}
							else
							{
								selectedLI.nextSibling.getElementsByTagName('a')[0].className = 'vertHover hoverNext';
							}
							// when the focus is shifted and the menu should also open 
							selectedLI.nextSibling.fireEvent("onkeydown");
							resetLastSelectedLI();  // Un Highlight priorly selected element of the Menu
							
							// set the focus according to the condition
							
							if(selectedLI.getElementsByTagName('span')[0].className.indexOf('noshow') != -1)
							{
								selectedLI.getElementsByTagName('a')[0].className='vert';
							}
							else
							{
								selectedLI.getElementsByTagName('a')[0].className='vert vertNext';
							}
							
							/* Closing the previos Menu Items */
							window.mlLast=selectedLI;
							selectedLI.fireEvent("ondblclick");
						}
						else // Cyclic : if the last parent menu is available then move to the first menu 
						{	
							// if only one menu is present donot move to right
							if(selectedLI == lis[0])
								return false;
							
							//set the focus
							if(lis[0].getElementsByTagName('span')[0].className.indexOf('noshow') != -1)
							{
								lis[0].getElementsByTagName('a')[0].className='vertHover';
							}
							else
							{
								lis[0].getElementsByTagName('a')[0].className = 'vertHover hoverNext';
							}
							
							//display the submenu of the first menu
							lis[0].fireEvent("onkeydown");
												
							if(selectedLI.getElementsByTagName('span')[0].className.indexOf('noshow') != -1)
							{
								selectedLI.getElementsByTagName('a')[0].className='vert';
							}
							else
							{
								selectedLI.getElementsByTagName('a')[0].className='vert vertNext';
							}
							resetLastSelectedLI(); // Un Highlight first element of the Menu
							
							/* Closing the previous Menu Items */
							window.mlLast=selectedLI;
							selectedLI.fireEvent("ondblclick");
						}				
					}
					else
					{
						var childs = selectedLI.getElementsByTagName('ul');
						if ( childs[0] == null)
						{
							return;
						}
						// show the submenu of the menu opened
						selectedLI.fireEvent("onkeydown");	
						
					}
					// set focus accordingly
					if(selectedLI.parentNode.parentNode.id == 'mlmenu' )
					{
						selectedLI.getElementsByTagName('a')[0].className='parentMenu horz';
					}
					else
					{
						if(selectedLI.getElementsByTagName('span')[0].className.indexOf('noshow') != -1)
						{
							selectedLI.getElementsByTagName('a')[0].className='vert';
						}
						else
						{
							selectedLI.getElementsByTagName('a')[0].className='vert vertNext';
						}
					}			
				}
				mouseUsed= false;
			}
			
			if(event.keyCode == 37) // left
			{
				var lis = objMenuDiv.getElementsByTagName('li');
				if (lis == null)
				{
					alert("error Lis Foud null");
				}
				var selectedLI = null;
				
				for(var i =( lis.length-1); i>=0;i--)
				{
					var as = objMenuDiv.getElementsByTagName('a');
					if (lis[i].getElementsByTagName('a')[0].className.indexOf('vertHover') != -1 )
					{
						selectedLI = lis[i];
						break;
					}
				}
				
				if (selectedLI != null) // IF there more item down then go
				{
					if ( isFocusOnMainMenu() == true ) // in main menu
					{
						if(selectedLI.previousSibling != null)
						{
							if(selectedLI.previousSibling.getElementsByTagName('span')[0].className.indexOf('noshow') != -1)
							{
								selectedLI.previousSibling.getElementsByTagName('a')[0].className='vertHover';	
							}
							else
							{
								selectedLI.previousSibling.getElementsByTagName('a')[0].className = 'vertHover hoverNext';
							}
							
							selectedLI.previousSibling.fireEvent("onkeydown");
							
							if(selectedLI.getElementsByTagName('span')[0].className.indexOf('noshow') != -1)
							{
								selectedLI.getElementsByTagName('a')[0].className='vert';
							}
							else
							{
								selectedLI.getElementsByTagName('a')[0].className='vert vertNext';
							}
							
							resetLastSelectedLI(); // Un Highlight first element of the Menu
												
							/* Closing the previos Menu Items */
							window.mlLast=selectedLI;
							selectedLI.fireEvent("ondblclick");
						}
						else // Cyclic
						{
						
							if(lis[lis.length-1].parentNode.parentNode == lis[0])
								return false;
							
							if(lis[lis.length-1].parentNode.parentNode.getElementsByTagName('span')[0].className.indexOf('noshow') != -1)
							{
								lis[lis.length-1].parentNode.parentNode.getElementsByTagName('a')[0].className='vertHover';
							}
							else
							{
								lis[lis.length-1].parentNode.parentNode.getElementsByTagName('a')[0].className = 'vertHover hoverNext';
							}
							
							if(lis[0].getElementsByTagName('span')[0].className.indexOf('noshow') != -1)
							{
								lis[0].getElementsByTagName('a')[0].className='vert';
							}
							else
							{
								lis[0].getElementsByTagName('a')[0].className='vert vertNext';
							}
							
							lis[lis.length-1].parentNode.parentNode.fireEvent("onkeydown");
							resetLastSelectedLI();
							
							/* Closing the previos Menu Items */
							window.mlLast=selectedLI;
							lis[0].fireEvent("ondblclick");					
							
						}
						
					}
					else
					{
						window.mlLast=selectedLI.parentNode.parentNode;
						if(selectedLI.parentNode.parentNode.getElementsByTagName('span')[0].className.indexOf('noshow') != -1)
						{
							selectedLI.parentNode.parentNode.getElementsByTagName('a')[0].className='vertHover';	
						}
						else
						{
							selectedLI.parentNode.parentNode.getElementsByTagName('a')[0].className = 'vertHover hoverNext';
						}
					
						selectedLI.parentNode.parentNode.fireEvent("ondblclick");			
					}
					
					if(selectedLI.parentNode.parentNode.id == 'mlmenu' )
					{
						selectedLI.getElementsByTagName('a')[0].className='parentMenu horz';
					}
					else
					{
						if(selectedLI.getElementsByTagName('span')[0].className.indexOf('noshow') != -1)
						{
							selectedLI.getElementsByTagName('a')[0].className='vert';
						}
						else
						{
							selectedLI.getElementsByTagName('a')[0].className='vert vertNext';
						}
					}
					
				}
				mouseUsed= false;
			}

			if(event.keyCode == 40 && getLastSelectedLI() != null)       //Down
			{
				// starts :for the menu to be seen when mouse and keyboard both are there and element is there behind
				event.cancelBubble = true;
				if(event.stopPropagation){
					event.stopPropagation();
				}
				// ends
				
				var lis = objMenuDiv.getElementsByTagName('li');
				if (lis == null)
				{
					alert("error Lis Foud null");
				}
				
				var selectedLI = null;
				
				for(var i =( lis.length-1); i>=0;i--)
				{
					if (lis[i].getElementsByTagName('a')[0].className.indexOf('vertHover') != -1 )
					{
						selectedLI = lis[i];
						break;
					}
				}
				
				if ( selectedLI == null)
				return;
				
				if ( isFocusOnMainMenu() == true ) // in main menu
					{
						selectedLI.fireEvent("onkeydown");
						var lis1 = selectedLI.getElementsByTagName('li');
						if (lis1 == null)
						{
							alert("error Lis1 Foud null");
						}
						else
						{
							if(lis1[0].getElementsByTagName('span')[0].className.indexOf('noshow') != -1)
							{
								lis1[0].getElementsByTagName('a')[0].className='vertHover';
							}
							else
							{
								lis1[0].getElementsByTagName('a')[0].className = 'vertHover hoverNext';
							}
						}
						
					}
					else // not in main menu
					{
						if ( selectedLI.nextSibling != null)
						{
							// set focus if the next sibling is present
							if(selectedLI.nextSibling.getElementsByTagName('span')[0].className.indexOf('noshow') != -1)
							{
								selectedLI.nextSibling.getElementsByTagName('a')[0].className='vertHover';
							}
							else
							{
								selectedLI.nextSibling.getElementsByTagName('a')[0].className = 'vertHover hoverNext';
							}
						}
						else
						{
							// cyclic when submenu of mlmenu
							if(selectedLI.parentNode.parentNode.parentNode.parentNode.id == 'mlmenu' )
							{
								if(selectedLI.getElementsByTagName('span')[0].className.indexOf('noshow') != -1)
								{
									selectedLI.getElementsByTagName('a')[0].className='vert';
								}
								else
								{
									selectedLI.getElementsByTagName('a')[0].className='vert vertNext';
								}
								
								if(selectedLI.parentNode.parentNode.getElementsByTagName('span')[0].className.indexOf('noshow') != -1)
								{
									selectedLI.parentNode.parentNode.getElementsByTagName('a')[0].className='vertHover';
								}
								else
								{
									selectedLI.parentNode.parentNode.getElementsByTagName('a')[0].className = 'vertHover hoverNext';
								}	
							}
							else
							{
								// cyclic when submenu of the menu opened by mlmenu
								if(selectedLI.getElementsByTagName('span')[0].className.indexOf('noshow') != -1)
								{
									selectedLI.getElementsByTagName('a')[0].className='vert';
								}
								else
								{
									selectedLI.getElementsByTagName('a')[0].className='vert vertNext';
								}
								
								if(selectedLI.parentNode.getElementsByTagName('span')[0].className.indexOf('noshow') != -1)
								{
									selectedLI.parentNode.getElementsByTagName('a')[0].className='vertHover';
								}
								else
								{
									selectedLI.parentNode.getElementsByTagName('a')[0].className = 'vertHover hoverNext';
								}
								
								//Confirm:: to stop down when no more values present in menu when a form is opened
								if (selectedLI.parentNode.getElementsByTagName('ul').length <= 1)
								{
									return false;
								}
							}
							// let the down function work when submenu has an alone value in the menu displayed
							if (selectedLI.parentNode.getElementsByTagName('li').length <= 1)
							{
								return false;
							}
						}
					}
					// focus to be set Accordingly
					if(selectedLI.parentNode.parentNode.id == 'mlmenu' )
					{
						selectedLI.getElementsByTagName('a')[0].className='parentMenu horz';
					}
					else
					{
						if(selectedLI.getElementsByTagName('span')[0].className.indexOf('noshow') != -1)
						{
							selectedLI.getElementsByTagName('a')[0].className='vert';
						}
						else
						{
							selectedLI.getElementsByTagName('a')[0].className='vert vertNext';
						}
					}
					
				mouseUsed= false;
				return false;
				
			} // end of down
			
			// for the up key
			if(event.keyCode == 38 && getLastSelectedLI() != null ) // up
			{	
			// starts :for the menu to be seen when mouse and keyboard both are there and element is there behind
				event.cancelBubble = true;
				if(event.stopPropagation){
					event.stopPropagation();
				}
				
			// ends
			
				if ( isFocusOnMainMenu() == true ) // in main menu
				{
					return false;
				}
				else
				{
					if(getLastSelectedLI() != null)
					{
						var lastSelectdLi = getLastSelectedLI();
			
						if(lastSelectdLi.previousSibling != null )
						{ 
						// set focus to the previous sibling
							if(lastSelectdLi.previousSibling.getElementsByTagName('span')[0].className.indexOf('noshow') != -1)
							{
								lastSelectdLi.previousSibling.getElementsByTagName('a')[0].className='vertHover';	
							}
							else
							{
								lastSelectdLi.previousSibling.getElementsByTagName('a')[0].className = 'vertHover hoverNext';
							}
						}
						else
						{	// set focus accordingly
							if(lastSelectdLi.parentNode.parentNode.parentNode.parentNode.id == 'mlmenu' )
							{
								if(lastSelectdLi.getElementsByTagName('span')[0].className.indexOf('noshow') != -1)
								{
									lastSelectdLi.getElementsByTagName('a')[0].className='vert';
								}
								else
								{
									lastSelectdLi.getElementsByTagName('a')[0].className='vert vertNext';
								}
								
								if(lastSelectdLi.parentNode.parentNode.getElementsByTagName('span')[0].className.indexOf('noshow') != -1)
								{
									lastSelectdLi.parentNode.parentNode.getElementsByTagName('a')[0].className='vertHover';
								}
								else
								{
									lastSelectdLi.parentNode.parentNode.getElementsByTagName('a')[0].className = 'vertHover hoverNext';
								}
								// if on the parent menu then return false 
								return false;
							}
							else
							{	// remove focus from the prior selected menu
								if(lastSelectdLi.getElementsByTagName('span')[0].className.indexOf('noshow') != -1)
								{
									lastSelectdLi.getElementsByTagName('a')[0].className='vert';
								}
								else
								{
									lastSelectdLi.getElementsByTagName('a')[0].className='vert vertNext';
								}
								
								if(lastSelectdLi.parentNode.lastChild.getElementsByTagName('span')[0].className.indexOf('noshow') != -1)
								{
									lastSelectdLi.parentNode.lastChild.getElementsByTagName('a')[0].className='vertHover';
								}
								else
								{
									lastSelectdLi.parentNode.lastChild.getElementsByTagName('a')[0].className = 'vertHover hoverNext';
								}
								
								//Confirm:: to stop up when no more values present in menu when a form is opened
								if (lastSelectdLi.parentNode.getElementsByTagName('ul').length <= 1)
								{
									return false;
								}
							}
							
							// let the down function work when submenu has an alone value in the menu displayed
							if (lastSelectdLi.parentNode.getElementsByTagName('li').length <= 1)
							{
								return false;
							}
											
						}
						// set the focus accordingly
						if(lastSelectdLi.getElementsByTagName('span')[0].className.indexOf('noshow') != -1)
						{
							lastSelectdLi.getElementsByTagName('a')[0].className='vert';
						}
						else
						{
							lastSelectdLi.getElementsByTagName('a')[0].className='vert vertNext';
						}				
					}	
				}
				
			mouseUsed= false;
			return false;		
			}
		} //  if(event.keyCode == 13 || event.keyCode == 119 || event.keyCode == 27 || event.keyCode == 37 || event.keyCode == 38 || event.keyCode == 39 || event.keyCode == 40)
	}	
}
		
function setFocusOnFirstElementOfForm()
{
	if(document.forms.length > 0)
	{
		for(var i=0;i<document.forms[0].elements.length; i++)
		{
			if(document.forms[0].elements[i].type != 'hidden' )
			{
				if(document.forms[0].elements[i].type == 'text' 
				   || document.forms[0].elements[i].type == 'select-one'
				   || document.forms[0].elements[i].type == 'select-multiple' 
				   || document.forms[0].elements[i].type == 'button' 
				   || document.forms[0].elements[i].type == 'submit' 
				   || document.forms[0].elements[i].type == 'reset' 
				   || document.forms[0].elements[i].type == 'radio' 
				   || document.forms[0].elements[i].type == 'checkbox' 
				   || document.forms[0].elements[i].type == 'password' 
				   || document.forms[0].elements[i].type == 'file'
				   || document.forms[0].elements[i].type == 'image')
				{
					try
					{
						document.forms[0].elements[i].focus();
						return true;
					}
					catch(e1)
					{
						continue;
					}
				}
			}
		}					
	}	
}

// KeyBoard Menu Navigation Cade End.... owned Sagar.