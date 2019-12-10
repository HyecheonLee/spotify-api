package com.hyecheon.spotifyapi.service;

import com.hyecheon.spotifyapi.domain.Artist;
import com.hyecheon.spotifyapi.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ArtistService {
    private final ArtistRepository artistRepository;

    public List<Artist> getArtists() {
        return artistRepository.findAll();
    }
}
