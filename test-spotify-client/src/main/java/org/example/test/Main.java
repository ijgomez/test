package org.example.test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.model_objects.specification.Track;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 try {
			String csvFile = "target/export.csv";
			    FileWriter writer = new FileWriter(csvFile);
			    
			
			PlaylistSimplified[] list = SpotifySyncClient.getListOfUsersPlaylists_Sync();
			
			CSVUtil.writeLine(writer, Arrays.asList(new String[] {"PLAYLIST", "TRACK", "ALBUM", "ARTIST"}), ';', '"');
			for (PlaylistSimplified playlist : list) {
				PlaylistTrack[] playlistsTracks_Sync = SpotifySyncClient.getPlaylistsTracks_Sync(playlist.getId());
				
				
				for (PlaylistTrack playlistTrack : playlistsTracks_Sync) {
					
					Track track = playlistTrack.getTrack();
					AlbumSimplified album = track.getAlbum();
					
					 CSVUtil.writeLine(writer, Arrays.asList(new String[] {playlist.getName(), track.getName(), album.getName(), toString(album.getArtists())}), ';', '"');
					
					//System.out.println(playlist.getName() + "; " + track.getName() + "; " + album.getName() + "; " + toString(album.getArtists()));
					
				}
			}
			
			
			 writer.flush();
		        writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static String toString(ArtistSimplified[] artists) {
		List<String> as;
		
		as = new ArrayList<>();
		for (ArtistSimplified artistSimplified : artists) {
			as.add(artistSimplified.getName());
		}
		return as.toString().substring(1, as.toString().length() - 1);
	}

}
