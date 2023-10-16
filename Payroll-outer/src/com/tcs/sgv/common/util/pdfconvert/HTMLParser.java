package com.tcs.sgv.common.util.pdfconvert;

/**
 *<p>Title			:	HTMLParser
 *<p>Description	:	This class parses the given HTML content amd returns xml file</p>
 *<p>Copyright		:	Copyright TCSL 2005</p>
 *<p>Company		:	TCSL - CRM Services</p>
 *@version			:	1.0
 *@author			:	S.Shanmuga Sundaram
 *@Modified by		:	Srinivasa Veerasenan.
 *
 */

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class HTMLParser {
	public static int CONTENT = 3;
	public static int END_TAG = 2;
	public static int SINGLE_TAG = 4;
	public static int START_TAG = 1;
	public static int COMMENT = 5;

	private String currentToken = null;
	private int currentTokenType;

	private String html;
	private InputStream input;
	private boolean isContentEnd;

	/**
	 * Description	:	This method returns the END tag enclosed as </xxx>
	 * @param name as String
	 * @return String
	 */

	public static String getEndTag(String name) {
		return "</" + name + ">".toLowerCase();
	}

	/**
	 * Description	:	This method returns the START tag enclosed as <xxx>
	 * @param name as String
	 * @return String
	 */

	public static String getTagName(String tag) {
		String name = null;
		if (tag.startsWith("</"))
			name = tag.substring(2, tag.length() - 1);
		else if (tag.startsWith("<"))
			name =
				tag.substring(
					1,
					tag.indexOf(" ") > 1 ? tag.indexOf(" ") : tag.length() - 1);
		return name;
	}

	/**
	 * Description	:	This method returns the current Token
	 * @return String
	 */
	public String getCurrentToken() {
		return currentToken;
	}

	/**
	 * Description	:	This method returns the token type to check for
	 * START/CONTENT/END tag
	 * @return int
	 */

	public int getTokenType() {
		return currentTokenType;
	}

	/**
	 * Description	:	This method parses and gives the next token.
	 * This also sets the current token type
	 * @return boolean
	 */

	public boolean next() throws IllegalStateException {
		if (input == null)
			throw new IllegalStateException("Stream not set");

		StringBuffer token = new StringBuffer();
		char c;
		try {
			int i = -1;

			if ((i = input.read()) != -1) {
				c = (char) i;

				if (c == '<' || isContentEnd) {
					token.setLength(0);
					if (isContentEnd) {
						token.append('<');
						if (c == '/')
							currentTokenType = END_TAG;
						else
							currentTokenType = START_TAG;
						token.append(c);
					} else {
						token.append(c);
						if (((i = input.read()) != -1)
							& ((c = (char) i) == '/')) {
							currentTokenType = END_TAG;
						} else {
							currentTokenType = START_TAG;
						}
						token.append(c);
					}

					while (c != '>' && i != -1) {
						i = input.read();
						c = (char) i;
						token.append(c);
					}
					if (token.charAt(token.length() - 2) == '/')
						currentTokenType = SINGLE_TAG;

					currentToken = token.toString();
					isContentEnd = false;
				} else {
					token.append(c);
					while (c != '<' && i != -1) {
						i = input.read();
						c = (char) i;
						if (c == '<')
							isContentEnd = true;
						else
							token.append(c);
					}
					currentTokenType = CONTENT;
					currentToken = token.toString();
				}

				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Description	:	This method sets the InputStream which is used to parse
	 * @param inputStream as String
	 * @see parse(String)
	 */

	public void parse(InputStream inputStream) {
		input = inputStream;
	}

	/**
	 * Description	:	This method sets the String to parse by calling
	 * parse(InputStream) method
	 * @param html as String
	 * @see parse(InputStream)
	 */

	public void parse(String html) {
		this.html = html;
		parse(new ByteArrayInputStream(html.getBytes()));
	}

}
