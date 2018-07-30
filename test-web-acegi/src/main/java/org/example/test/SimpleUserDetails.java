/*
 * Copyright 2004 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.example.test;

import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;

/**
 * Simple UserDetails implementation
 * Only user to demonstrate basic autentication.
 *
 * @author Jacob von Eyben
 * @version $Id: $
 * @since 1.0
 */
public class SimpleUserDetails implements UserDetails {

	private static final long serialVersionUID = -3601425049711647899L;

	private String username;
  
	private String password;

  public SimpleUserDetails(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public GrantedAuthority[] getAuthorities() {
    GrantedAuthority grandedAuth = new GrantedAuthorityImpl("USER");
    return new GrantedAuthority[] {grandedAuth};
  }

  public String getPassword() {
    return password;
  }

  public String getUsername() {
    return username;
  }

  public boolean isAccountNonExpired() {
    return true;
  }

  public boolean isAccountNonLocked() {
    return true;
  }

  public boolean isCredentialsNonExpired() {
    return true;
  }

  public boolean isEnabled() {
    return true;
  }
}
