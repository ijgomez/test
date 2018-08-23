/**
 * Copyright (c) 2008 Mark S. Kolich
 * http://mark.kolich.com
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
*/

package org.example.test.authenticate;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

/**
 * Used to handle HTTP authentication.
 *
 */
public class MyAuthenticator extends Authenticator {

	private String username; // Username needed for authentication.
	private String password; // Password needed for authentication.

	/**
	 * Create a new MyAuthenticator object.
	 * 
	 * @param username
	 * @param password
	 */
	public MyAuthenticator(String username, String password) {
		this.username = username;
		this.password = password;
	}

	/**
	 * This method is called when a password protected URL is accessed. The
	 * username and password is then automatically provided using the HTTP
	 * protocol.
	 */
	@Override
	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(this.username, this.password
				.toCharArray());
	}

	/**
	 * Set the username.
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Set the password.
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

} // end public class MyAuthenticator
