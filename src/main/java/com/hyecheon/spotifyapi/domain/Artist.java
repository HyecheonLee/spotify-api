package com.hyecheon.spotifyapi.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "artists")
@ToString(exclude = {"artistGenres"})
public class Artist {
    @Id
    private String id;
    private String name;
    private int followers;
    private int popularity;
    private String url;
    private String imageUrl;
    @OneToMany(mappedBy = "artist", fetch = FetchType.LAZY)
    private List<ArtistGenre> artistGenres = new ArrayList<>();
}
