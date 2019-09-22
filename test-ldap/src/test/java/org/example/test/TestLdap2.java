package org.example.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.zapodot.junit.ldap.EmbeddedLdapRule;
import org.zapodot.junit.ldap.EmbeddedLdapRuleBuilder;

import com.unboundid.ldap.sdk.LDAPInterface;
import com.unboundid.ldap.sdk.SearchResult;
import com.unboundid.ldap.sdk.SearchResultEntry;
import com.unboundid.ldap.sdk.SearchScope;


public class TestLdap2 {
	
	public static final String DOMAIN_DSN = "dc=example,dc=com";
	
	@Rule
	public EmbeddedLdapRule embeddedLdapRule = EmbeddedLdapRuleBuilder.newInstance().usingDomainDsn(DOMAIN_DSN)
			.importingLdifs("users-import.ldif").build();
 
	@Test
	public void shouldFindAllPersons() throws Exception {
		final LDAPInterface ldapConnection = embeddedLdapRule.ldapConnection();
		final SearchResult searchResult = ldapConnection.search(DOMAIN_DSN, SearchScope.SUB, "(objectClass=person)");
		assertThat(3, equalTo(searchResult.getEntryCount()));
		List<SearchResultEntry> searchEntries = searchResult.getSearchEntries();
		assertThat(searchEntries.get(0).getAttribute("cn").getValue(), equalTo("John Steinbeck"));
		assertThat(searchEntries.get(1).getAttribute("cn").getValue(), equalTo("Micha Kops"));
		assertThat(searchEntries.get(2).getAttribute("cn").getValue(), equalTo("Santa Claus"));
	}
 
	@Test
	public void shouldFindExactPerson() throws Exception {
		final LDAPInterface ldapConnection = embeddedLdapRule.ldapConnection();
		final SearchResult searchResult = ldapConnection.search("cn=Santa Claus,ou=Users,dc=example,dc=com",
				SearchScope.SUB, "(objectClass=person)");
		assertThat(1, equalTo(searchResult.getEntryCount()));
		assertThat(searchResult.getSearchEntries().get(0).getAttribute("cn").getValue(), equalTo("Santa Claus"));
	}

}
