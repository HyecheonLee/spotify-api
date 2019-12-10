package com.hyecheon.spotifyapi.repository;

import com.hyecheon.spotifyapi.domain.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, String> {
}
