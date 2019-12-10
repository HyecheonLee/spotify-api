package com.hyecheon.spotifyapi.repository;

import com.hyecheon.spotifyapi.domain.ArtistGenre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistGenreRepository extends JpaRepository<ArtistGenre, Long> {
}
