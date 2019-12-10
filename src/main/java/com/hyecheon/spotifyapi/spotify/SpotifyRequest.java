package com.hyecheon.spotifyapi.spotify;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Slf4j
@RequiredArgsConstructor
@Component
public class SpotifyRequest {

    @Value("${spotify.client.id}")
    private String clientId;
    @Value("${spotify.client.secret}")
    private String clientSecret;
    private final URI redirectUri = SpotifyHttpManager.makeUri("https://example.com/spotify-redirect");
    private SpotifyApi spotifyApi;
    private ClientCredentialsRequest clientCredentialsRequest;

    private LocalDateTime timeOfAccessTokenFired = LocalDateTime.now();
    private Integer expires = 0;

    @PostConstruct
    public void init() {
        spotifyApi = new SpotifyApi.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .setRedirectUri(redirectUri)
                .build();
        clientCredentialsRequest = spotifyApi.clientCredentials().build();
    }

    public void authorizationCodeSync() {
        try {
            timeOfAccessTokenFired = LocalDateTime.now();
            var clientCredentials = clientCredentialsRequest.execute();
            expires = clientCredentials.getExpiresIn();
            spotifyApi.setAccessToken(clientCredentials.getAccessToken());
            log.info("Expires in: {}", clientCredentials.getExpiresIn());
        } catch (IOException | SpotifyWebApiException e) {
            log.error("Error: {}", e.getMessage());
        }
    }

    public Track[] getTopTracks(String artistId) {
        try {
            checkExpired();
            return spotifyApi.getArtistsTopTracks(artistId, CountryCode.KR).build().execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void checkExpired() {
        if (LocalDateTime.now().isAfter(timeOfAccessTokenFired.plusSeconds(expires))) {
            authorizationCodeSync();
        }
    }
}
