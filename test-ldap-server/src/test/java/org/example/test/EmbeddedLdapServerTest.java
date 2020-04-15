package org.example.test;

import static org.junit.Assert.*;

import org.apache.directory.server.core.api.DirectoryService;
import org.apache.directory.server.core.api.changelog.ChangeLog;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EmbeddedLdapServerTest {

	private EmbeddedLdapServer embeddedLdapServer;
	
	@Before
	public void beforeTest() throws Exception {
		this.embeddedLdapServer = new EmbeddedLdapServer();
		this.embeddedLdapServer.init();
	}
	
	@After
	public void afterTest() throws Exception {
		this.embeddedLdapServer.destroy();
	}
	
	@Test
	public void testInitialization() {
		
		assertNotNull(this.embeddedLdapServer.getLdapServer());
		
		DirectoryService directoryService = this.embeddedLdapServer.getDirectoryService();
		
		assertNotNull(directoryService);
		assertNotNull(directoryService.getSchemaPartition());
		assertNotNull(directoryService.getSystemPartition());
		assertNotNull(directoryService.getSchemaManager());
		assertNotNull(directoryService.getDnFactory());
		
		ChangeLog changeLog = directoryService.getChangeLog();
		
		assertNotNull(changeLog);

		assertFalse(changeLog.isEnabled());
		assertTrue(directoryService.isStarted());
		assertTrue(this.embeddedLdapServer.getLdapServer().isStarted());
		
	}

}
