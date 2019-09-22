package org.example.test;

import org.apache.directory.server.annotations.CreateLdapServer;
import org.apache.directory.server.annotations.CreateTransport;
import org.apache.directory.server.core.annotations.ApplyLdifFiles;
import org.apache.directory.server.core.annotations.CreateDS;
import org.apache.directory.server.core.annotations.CreatePartition;
import org.apache.directory.server.core.integ.CreateLdapServerRule;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * http://directory.apache.org/apacheds/advanced-ug/7-embedding-apacheds.html
 * https://cwiki.apache.org/confluence/display/DIRxSRVx10/Using+ApacheDS+for+unit+tests
 * @author ijgomez
 *
 */
@CreateDS(name = "myDS", partitions = { @CreatePartition(name = "test", suffix = "dc=myorg,dc=com") })
@CreateLdapServer(transports = { @CreateTransport(protocol = "LDAP", address = "localhost") })
@ApplyLdifFiles({ "users-import.ldif" })
public class TestLdap {

	@ClassRule
	public static CreateLdapServerRule serverRule = new CreateLdapServerRule();

	@Test
	public void test() {
		// do whatever you need with `ldapServer`
		//do whatever you need with `serverRule.getLdapServer()`
//		LdapContext ctx = (LdapContext) ServerIntegrationUtils.getWiredContext(ldapServer, null)
//				.lookup("ou=Users,dc=example,dc=com");
// 
//		// we want a sorted result, based on the canonical name
//		ctx.setRequestControls(new Control[] { new SortControl("cn", Control.CRITICAL) });
// 
//		NamingEnumeration<SearchResult> res = ctx.search("", "(objectClass=person)", new SearchControls());
//		assertThat(res.hasMore(), equalTo(true));
// 
//		assertThat(res.next().getName(), equalTo("cn=John Steinbeck"));
//		assertThat(res.next().getName(), equalTo("cn=Micha Kops"));
//		assertThat(res.next().getName(), equalTo("cn=Santa Claus"));
	}

}
