package org.example.test;

import java.io.IOException;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.requests.data.playlists.GetListOfUsersPlaylistsRequest;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistsTracksRequest;

public class SpotifySyncClient {

	// private static final String accessToken =
	// "BQBo5-qmnD7NG7PVGlWHKDeobNmLg17-tbXrQptsmHFGVcRK0aBL3VMgF0M7N4Bd-NDdUcvao6kAwNdm_gMVHGNL7N1DZtnrDPR_mjtYbdvg5xjZfc6kmCprCU4taA69qWa3fWUQNP_Qk57tZrjDH94";
	private static final String accessToken = "BQAy2z5ebakLEKbsGKWa93i2AufMXjXYO6-XPp26XXhZ3mOtoBrUPk8gwgKR96mJcMz2kEZDm5jhb5z_zGMm5vLJCOu-84sdlCGJNpV-s8wrB8fF8fI57U6iORSiJOuc5k4aa0p89SF1o4_ijP0CNIx-MHeEHURDig_Xoh-yHld-p9_TDw_X";
	private static final String userId = "myuser";
	  private static final String playlistId = "3AGOiaoRXMSjswCLtuNqv5";

	private static final SpotifyApi spotifyApi = new SpotifyApi.Builder().setAccessToken(accessToken).build();
	private static final GetListOfUsersPlaylistsRequest getListOfUsersPlaylistsRequest = spotifyApi
			.getListOfUsersPlaylists(userId)
//	          .limit(10)
//	          .offset(0)
			.build();
	
	private static final GetPlaylistsTracksRequest getPlaylistsTracksRequest = spotifyApi
	          .getPlaylistsTracks(playlistId)
//	          .fields("description")
//	          .limit(10)
//	          .offset(0)
//	          .market(CountryCode.SE)
	          .build();

	public static PlaylistSimplified[] getListOfUsersPlaylists_Sync() {
		try {
			final Paging<PlaylistSimplified> playlistSimplifiedPaging = getListOfUsersPlaylistsRequest.execute();

			System.out.println("Total: " + playlistSimplifiedPaging.getTotal());

			return playlistSimplifiedPaging.getItems();

		} catch (IOException | SpotifyWebApiException e) {
			System.out.println("Error: " + e.getMessage());
		}

		return null;
	}
	
	public static PlaylistTrack[] getPlaylistsTracks_Sync(String playlist_id) {
	    try {
	      //final Paging<PlaylistTrack> playlistTrackPaging = getPlaylistsTracksRequest.execute();
	      final Paging<PlaylistTrack> playlistTrackPaging = spotifyApi.getPlaylistsTracks(playlist_id).build().execute();

	      System.out.println("Total: " + playlistTrackPaging.getTotal());
	      
	      return playlistTrackPaging.getItems();
	      
	    } catch (IOException | SpotifyWebApiException e) {
	      System.out.println("Error: " + e.getMessage());
	    }
	    
	    return null;
	  }

}
