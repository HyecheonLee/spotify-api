package com.hyecheon.spotifyapi.repository;

import com.hyecheon.spotifyapi.domain.Artist;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class ArtistsRepositoryTest {
    @Autowired
    ArtistRepository artistRepository;

    @Test
    void findAll() {
        final List<Artist> artists = artistRepository.findAll();
        System.out.println(artists);
    }
}