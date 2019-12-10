package com.hyecheon.spotifyapi.spotify;

import com.wrapper.spotify.model_objects.specification.Track;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class SpotifyRequestTest {
    @Autowired
    SpotifyRequest spotifyRequest;

    @Test
    void headerTest() {
        spotifyRequest.authorizationCodeSync();
        final Track[] topTracks = spotifyRequest.getTopTracks("00FQb4jTyendYWaN8pK0wa");
        System.out.println(Arrays.toString(topTracks));
    }
}