package com.tcs.sgv.common.util.pdfconvert;

/**
 *<p>Title			:	HTMLNormalizer
 *<p>Description	:	This class parses the given HTML content amd returns xml file</p>
 *<p>Copyright		:	Copyright TCSL 2005</p>
 *<p>Company		:	TCSL - CRM Services</p>
 *@version			:	1.0
 *@author			:	S.Shanmuga Sundaram,Srinivasa Veerasenan.
 *
 */

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class HTMLNormalizer {

	/**
	* Description	:	This method returns the normalized tag for the given token.
	* It adds the closing tag for the single tags like <img/>
	* @param token as String
	* @return String
	*/

	public static String getNormalizedTag(String token) {
		String tag = null;
		if (token.endsWith("/>")) {
			tag =
				"<"
					+ token.substring(1, token.length() - 2)
					+ "></"
					+ HTMLParser.getTagName(token)
					+ ">";
		}
		return tag.toLowerCase();
	}

	/**
	 * Description	:	This method parses the HTMLfile
	 * @param data as String
	 * @return String
	 */

	public String doParseHTML(String data) throws Exception {

		this.setInputString(data);
		return normalize();
	}

	/**
	 * Description	:	This method parses the HTMLfile
	 * @param inStream as InputStream
	 * @return String
	 */

	public String doParseHTML(InputStream inStream) throws Exception {

		this.setInputStream(inStream);
		return normalize();
	}
	//	contains the NON-PDF tags
	private ArrayList nonPdfTags = new ArrayList();
	private HTMLParser parser = null;

	// contains the self closing tags
	private ArrayList selfClosingList = new ArrayList();

	private StringBuffer styleData = new StringBuffer();
	//to store the style data
	private boolean styleFlag = false; //to check the style tag

	/**
	 * General Constructor
	 *
	 */
	public HTMLNormalizer() {

		parser = new HTMLParser();

		//add the self closing tags
		selfClosingList.add("br");
		selfClosingList.add("img");
		selfClosingList.add("a");

		//		selfClosingList.add("area");

		//add the non PDF tags
		nonPdfTags.add("form");
		nonPdfTags.add("input");
		nonPdfTags.add("hr");
		nonPdfTags.add("script");
		nonPdfTags.add("style");

	}

	/**
	 * Description	:	This method extracts the body content for the HTML page
	 * @param strHTML as String
	 * @return String
	 */

	private String extractBody(String strHTML) {

		String content = null;

		int bodyIndex = strHTML.indexOf(PDFConstants.HEAD_END_TAG);

		if (bodyIndex > -1) {
			int start = bodyIndex + PDFConstants.HEAD_END_TAG_LEN;
			content = strHTML.substring(start, strHTML.length());
		}

		return content;
	}

	/**
	* Description	:	This method handles the attributes present in a tag and encloses
	* them inside double quotes
	* @param tag as String
	* @return String
	*/

	private String handleAttributes(String tag) {

		String parsedTag = "";
		HashMap attrMap = new HashMap();

		// this boolean variable is added to check HTML <TD> tags contains NOWRAP attribute, if it is there then nowrap=\"nowrap\" is appended in the tag instead of NOWRAP.
		boolean booNowrapExists = false;

		tag = tag.trim();

		if (tag.indexOf("nowrap") > 0) {
			String tempNowrap = "";
			booNowrapExists = true;
			// here NOWRAP is removed from tag...
			tempNowrap = tag.substring(0, tag.indexOf("nowrap"));
			tempNowrap
				+= tag.substring((tag.indexOf("nowrap") + 6), tag.length());
			tag = tempNowrap;
		}

		if (tag.indexOf(">") > -1)
			tag = tag.substring(0, tag.indexOf(">"));

		// the below logic is modified to tokenize stlye tag attribute properly.
		int styleIndex = tag.indexOf("style=\"");
		if (styleIndex > 0) {

			//7 is added for 'style=\"'
			StringBuffer tagBuffer =
				new StringBuffer(tag.substring(0, styleIndex + 7));
			String styleStr = tag.substring(styleIndex + 7);

			StringTokenizer styleToken = new StringTokenizer(styleStr, " ");

			while (styleToken.hasMoreTokens()) {

				tagBuffer.append(styleToken.nextToken());

			} // end of while loop

			tag = tagBuffer.toString();

		} // if loop checking style attribute

		try {
			// handle the <AREA> tag..........
			if (tag.indexOf("<area") > -1) {
				if (tag.indexOf(">") > -1)
					tag = tag.substring(0, tag.indexOf(">"));

				// this is to handle "title" attribute in <AREA tag.........

				if (tag.indexOf("title=") > 0) {
					//6 is added for 'title='
					char tokenizer = tag.charAt(tag.indexOf("title=") + 6);
					if (tokenizer == '"') {
						int one = tag.indexOf("title=");
						String first = tag.substring(0, one);
						String second = tag.substring(one);
						int two = tag.indexOf("\"", one);
						int three = tag.indexOf("\"", (two + 1));
						String altVariable = tag.substring(one, three + 1);
						String attrName = "title";
						String attrValue =
							altVariable.substring(
								(altVariable.indexOf("\"") + 1),
								(altVariable.length() - 1));
						tag = tag.substring(0, one) + tag.substring(three + 1);

						attrMap.put(attrName, attrValue);
					}
				}
			}

			// handle "alt" attribute (which may have spaces in their values) in <IMG> tag .....

			if (tag.indexOf("alt=") > 0) {
				//4 is added for 'alt='
				char tokenizer = tag.charAt(tag.indexOf("alt=") + 4);
				if (tokenizer == '"') {
					int one = tag.indexOf("alt=");
					String first = tag.substring(0, one);
					String second = tag.substring(one);
					int two = tag.indexOf("\"", one);
					int three = tag.indexOf("\"", (two + 1));
					String altVariable = tag.substring(one, three + 1);
					String attrName = "alt";
					String attrValue =
						altVariable.substring(
							(altVariable.indexOf("\"") + 1),
							(altVariable.length() - 1));
					tag = tag.substring(0, one) + tag.substring(three + 2);

					attrMap.put(attrName, attrValue);
				}
			} // handle "alt" attribute (which may have spaces in their values) in <IMG> tag ends here...
			// this is to handle "href" attribute in <A > tag.........
			else if (tag.indexOf("href=") > 0) {
				//5 is added for 'href='
				char tokenizer = tag.charAt(tag.indexOf("href=") + 5);

				if (tokenizer == '"') {
					int one = tag.indexOf("href=");
					String first = tag.substring(0, one);
					String second = tag.substring(one);
					int two = tag.indexOf("\"", one);
					int three = tag.indexOf("\"", (two + 1));
					String altVariable = tag.substring(one, three + 1);
					String attrName = "href";
					String attrValue =
						altVariable.substring(
							(altVariable.indexOf("\"") + 1),
							(altVariable.length() - 1));
					tag = tag.substring(0, one) + tag.substring(three + 1);

					attrMap.put(attrName, attrValue);
				}
			}

		} catch (Exception e) {
			//System.out.println(				"An exception occured while handling AREA tag or ALT attribute or href attribute in handleAttributes method is :: "					+ e);
		}

		StringTokenizer stk = new StringTokenizer(tag, " ");
		parsedTag = stk.nextToken();

		while (stk.hasMoreTokens()) {

			String token = stk.nextToken();
			int index = token.indexOf("=");
			String attrName = null;
			String attrValue = null;

			if (index > -1) {

				attrName = token.substring(0, index).trim();
				attrValue = token.substring(index + 1).trim();

				if (attrValue.startsWith("\"")) {
					attrValue = attrValue.substring(1);
				} //attrValue startsWith "

				if (attrValue.endsWith("\"") || attrValue.endsWith(">")) {
					attrValue = attrValue.substring(0, attrValue.length() - 1);
				} //attrValue endsWith " or >

			} // token contains =
			else {
				attrName = token;
			}

			attrMap.put(attrName, attrValue);

		} // while loop

		if (attrMap.containsKey("class")) {

			handleClass((String) attrMap.get("class"), attrMap);
		} else if (attrMap.containsKey("style")) {
			handleStyle((String) attrMap.get("style"), attrMap);
		}

		//remove the class and style keys
		attrMap.remove("class");
		attrMap.remove("style");

		//set the attribute values
		Set keys = (Set) attrMap.keySet();
		Iterator keyIterator = keys.iterator();

		while (keyIterator.hasNext()) {
			String element = (String) keyIterator.next();
			parsedTag += " " + element + "=\"" + attrMap.get(element) + "\" ";
		} // iterating over attrMap ends here

		// If 'NOWRAP' finds in the HTML content's <TD> tag, then this 'NOWRAP' is replaced by nowrap=\"nowrap\" attribute.
		if (booNowrapExists) {
			parsedTag += " nowrap = \"nowrap\" >";
		} else {
			parsedTag += ">";
		}

		return parsedTag;
	}

	/**
		* Description	:This method extracts the style attribute which would be referencing
		* the inline stylesheets. The outline style information needs to be handled
		* @param attrValue as String
		* @param attrMap as HashMap
		* @return String
		*/
	private void handleClass(String attrValue, HashMap attrMap) {

		String parsedData = "";
		String styleContent = styleData.toString().trim().toLowerCase();

		String styleClass = "." + attrValue;

		//Start :: check for Inline Style Sheet
		if (styleContent.indexOf(styleClass) > -1) {

			int firstIndex = styleContent.indexOf(styleClass);
			int secondIndex = styleContent.indexOf("}", firstIndex);
			int startIndex = styleContent.indexOf("{", firstIndex);

			parsedData =
				styleContent.substring(startIndex + 1, secondIndex).trim();

			//remove carriage return characters
			parsedData = parsedData.replace('\r', ' ');
			parsedData = parsedData.replace('\t', ' ');
			parsedData = parsedData.replace('\n', ' ');

		}
		//End   :: check for Inline Style Sheet

		//Start :: check for Outline Style Sheet
		//End   :: check for Outline Style Sheet

		handleStyle(parsedData, attrMap);

	}

	/**
	* Description	:	This method extracts the style attribute
	* The style attributes will always be having a space before the value. So we
	* need to remove the extra spaces
	* @param attrValue as String
	* @param attrMap as HashMap
	* @return String
	*/
	private void handleStyle(String attrValue, HashMap attrMap) {

		String parsedData = null;
		int cnt = 0;
		char c0;

		StringBuffer attrBuffer = new StringBuffer(attrValue);

		while (cnt < attrBuffer.length()) {

			c0 = attrBuffer.charAt(cnt);

			if (c0 == ' ') {

				attrBuffer.deleteCharAt(cnt);
			}

			cnt++;

		}

		parsedData = attrBuffer.toString();
		StringTokenizer stk = new StringTokenizer(parsedData, ";");

		while (stk.hasMoreTokens()) {

			String token = stk.nextToken().trim();

			int index = token.indexOf(":");
			String name = null;
			String value = null;

			if (index > -1) {

				name = token.substring(0, index).trim().toLowerCase();
				value = token.substring(index + 1).trim();
				if (value.startsWith("\"")) {
					value = value.substring(1);
				} //attrValue startsWith "

				if (value.endsWith("\"") || value.endsWith(">")) {
					value = value.substring(0, value.length() - 1);
				} //attrValue endsWith " or >

			} // token contains =
			attrMap.put(name, value);

		} // end of stk.hasMoreTokens() while loop

	}

	/**
	 * Description	:	This method parses the given HTML using HTMLParser class and
	 * also normalizes the HTML content
	 * @return String
	 * @throws Exception
	 */

	private String normalize() throws Exception {
		try {
			Stack stack = new Stack();
			StringBuffer buffer = new StringBuffer();
			int tokenType = -1;
			String token = null;

			//For Dealing Title
			String strTitle = PDFConstants.XSL_HEADER_VALUE;
			//Default Header value
			boolean titleFlag = false;

			//Used to define the Column numbers when declaring the table
			int noOfTds = 0;
			int colsPos = 0;
			Stack colStack = new Stack(); //For handling inner tables

			while (parser.next()) {
				tokenType = parser.getTokenType();
				token = parser.getCurrentToken();

				if (tokenType == HTMLParser.START_TAG) {

					String currentTag = HTMLParser.getTagName(token);

					if (currentTag.equalsIgnoreCase("style")
						|| currentTag.equalsIgnoreCase("script")) {
						styleFlag = true;
					}

					//Extract the title from the Page
					if (currentTag.equalsIgnoreCase("title")) {
						titleFlag = true;
					} // end of Title extraction

					if (!nonPdfTags.contains(currentTag.toLowerCase())) {

						String parsedTag =
							handleAttributes(token.toLowerCase());

						//This piece of code adds the 'cols' attribute to the table attributes
						if (currentTag.equalsIgnoreCase("table")
							&& parsedTag.indexOf("cols") == -1) {

							StringBuffer textBuffer =
								new StringBuffer(parsedTag);

							//	START : For handling inner tables
							if (noOfTds > 0) {
								colStack.push(new Integer(noOfTds));
								colStack.push(new Integer(colsPos));
							}
							//	END : For handling inner tables

							noOfTds = 0;
							int len = textBuffer.length() - 1;

							textBuffer.insert(len, " cols=\"0\" ");
							colsPos = buffer.length() + len + 7;

							parsedTag = textBuffer.toString();
						} // end of table checking

						if (currentTag.equalsIgnoreCase("td")) {

							String tempToken = parsedTag.toLowerCase();

							if (tempToken.indexOf("colspan=\"") > -1) {

								int index1 = tempToken.indexOf("colspan=\"");
								int index2 =
									tempToken.indexOf("\"", index1 + 10);
								String col =
									tempToken.substring(index1 + 9, index2);

								try {
									noOfTds += Integer.parseInt(col);
								} catch (NumberFormatException nfEx) {
									noOfTds++;
								}

							} else {
								noOfTds++;
							} // end of if loop checking the colspan

							tempToken = null;
						} // for td if condition
						//make the noOfTds as zero since a new row has began
						if (currentTag.equalsIgnoreCase("tr")
							|| currentTag.equalsIgnoreCase("thead")) {
							noOfTds = 0;
						}

						buffer.append(parsedTag);
						stack.push(currentTag);

					} // end of non pdf tags if check

				} else if (tokenType == HTMLParser.CONTENT) {

					if (styleFlag) {
						styleData.append(token);
					} else
						buffer.append(token);

					//Handle Title
					if (titleFlag) {
						strTitle = token;
						titleFlag = false;
					}

				} else if (tokenType == HTMLParser.END_TAG) {

					String tagName = HTMLParser.getTagName(token);

					if (tagName.equalsIgnoreCase("script")
						|| tagName.equalsIgnoreCase("style")) {
						styleFlag = false;
					} else if (tagName.equalsIgnoreCase("body")) {
						buffer.append("<title>" + strTitle + "</title>");
					} // end of Body If check

					if (tagName.equalsIgnoreCase((String) stack.peek())) {

						//For calculating the no of columns in a table
						if (tagName.equalsIgnoreCase("table")) {

							buffer.deleteCharAt(colsPos);
							buffer.insert(colsPos, String.valueOf(noOfTds));

							//	START : For handling inner tables
							if (colStack.size() > 0) {
								colsPos = ((Integer) colStack.pop()).intValue();
								noOfTds = ((Integer) colStack.pop()).intValue();
							} else
								noOfTds = 0;
							//	END : For handling inner tables
						}

						buffer.append(token.toLowerCase());
						stack.pop();
					} else {
						if (stack.contains(tagName)) {

							String tag =
								HTMLParser.getEndTag((String) stack.pop());
							buffer.append(tag.toLowerCase());
							while (stack.size() > 0
								&& !tagName.equalsIgnoreCase(
									(String) stack.peek())) {
								String currentTag =
									HTMLParser.getEndTag((String) stack.pop());
								buffer.append(currentTag.toLowerCase());
							}
							buffer.append(token.toLowerCase());
							stack.pop();
						}
					}
				} else if (tokenType == HTMLParser.SINGLE_TAG) {
					buffer.append(HTMLNormalizer.getNormalizedTag(token));
				}
			}
			while (stack.size() > 0) {
				buffer.append(HTMLParser.getEndTag((String) stack.pop()));
			}

			String strHTML = extractBody(buffer.toString());

			StringBuffer xmlData = new StringBuffer();
			xmlData.append(PDFConstants.XML_HEADER + "\n");
			xmlData.append(PDFConstants.ENTITY_REF + "\n");
			xmlData.append(strHTML);

			return xmlData.toString();
		} catch (Exception e) {
			//System.out.println(				"Exception in normalize is ************     " + e);
			return "";
		}
	}

	/**
	 * Description	:	This method sets the InputStream to parse
	 * @param stream as InputStream
	 */

	private void setInputStream(InputStream stream) {
		parser.parse(stream);
	}

	/**
	 * Description	:	This method sets the InputStream to parse
	 * @param strHTML as String
	 */

	private void setInputString(String strHTML) {
		try {

			parser.parse(strHTML);
		} catch (Exception e) {
			//System.out.println(				"Exception is occurred while parsing the string value in HTMLNormalizer ************     "					+ e);
		}
	}

}
