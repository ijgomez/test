package org.example.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SpotifySyncClientTest {

	private SpotifySyncClient spotifyClient;
	
	@Before
	public void before() {
		this.spotifyClient = new SpotifySyncClient();
	}
	
	@Test
	public void testGetPlayLists() {
		
		SpotifySyncClient.getListOfUsersPlaylists_Sync();
		
		//fail("Not yet implemented");
	}

}
