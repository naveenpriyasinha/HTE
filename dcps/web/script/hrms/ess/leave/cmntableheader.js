
function generateTableHeader(showSanctionCol,tbody,isSelectAll_chkbox) 
{
			var row = document.createElement("TR"); 
			if(isSelectAll_chkbox)
			{
			var cell1 = document.createElement("TD"); 
			cell1.setAttribute("align","center");
			}
			var cell2 = document.createElement("TD"); 
			var cell3 = document.createElement("TD"); 
			var cell4 = document.createElement("TD"); 
			var cell5 = document.createElement("TD"); 
			var cell6 = document.createElement("TD"); 
			var cell7 = document.createElement("TD"); 
			var cell8 = document.createElement("TD"); 
			var cell9 = document.createElement("TD"); 
if(isSelectAll_chkbox)
			{
			var inp1 =  document.createElement("INPUT"); 
			inp1.setAttribute("type","checkbox"); 
			inp1.setAttribute("id","checkAll"); 
			inp1.setAttribute("name","chk"); 
			inp1.setAttribute("onclick",checkAll); 
			cell1.appendChild(inp1); 
	//		cell1.setAttribute("class","datatableheader"); 
			row.appendChild(cell1);
}
			label=document.createElement("label")
			label.setAttribute("class","captionTag");
			label.innerHTML=labels[12];
			cell2.appendChild(label);
			cell2.setAttribute("align","center"); 					
			//cell2.setAttribute("class","datatableheader"); 					
			label=document.createElement("label");
			label.setAttribute("class","captionTag");
			label.innerHTML=labels[13];
			cell3.setAttribute("align","center"); 					
			cell3.appendChild(label);
			//cell3.setAttribute("class","datatableheader"); 					

			label=document.createElement("label");
			label.setAttribute("class","captionTag");
			label.innerHTML=labels[2];
			cell4.setAttribute("align","center"); 					
			cell4.appendChild(label);


			label=document.createElement("label");
			label.setAttribute("class","captionTag");
			label.innerHTML=labels[3];
			cell5.setAttribute("align","center"); 					
			cell5.appendChild(label);


			label=document.createElement("label");
			label.setAttribute("class","captionTag");
			label.innerHTML=labels[15];
			cell6.setAttribute("align","center"); 					
			cell6.appendChild(label);





			
			
			row.appendChild(cell2);
			row.appendChild(cell3);
			row.appendChild(cell4);
			row.appendChild(cell5);
			row.appendChild(cell6);

		if(showSanctionCol==1){
			
			label=document.createElement("label");
			label.setAttribute("class","captionTag");
			label.innerHTML=labels[0];
			cell7.setAttribute("align","center"); 					
			cell7.appendChild(label);

			
			label=document.createElement("label");
			label.setAttribute("class","captionTag");
			label.innerHTML=labels[1];
			cell8.setAttribute("align","center"); 					
			cell8.appendChild(label);

				
			label=document.createElement("label");
			label.setAttribute("class","captionTag");
			label.innerHTML=labels[14];
			cell9.setAttribute("align","center"); 					
			cell9.appendChild(label);

	
		
			row.appendChild(cell7);
			row.appendChild(cell8);
			row.appendChild(cell9);
}
			row.bgColor='#C9DFFF';
			//row.setAttribute("class","datatableheader");
			tbody.appendChild(row);
		
		}

		