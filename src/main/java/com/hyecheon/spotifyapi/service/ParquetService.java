package com.hyecheon.spotifyapi.service;

import com.hyecheon.spotifyapi.domain.Artist;
import com.hyecheon.spotifyapi.spotify.SpotifyRequest;
import com.wrapper.spotify.model_objects.specification.Track;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.avro.reflect.ReflectData;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.avro.AvroParquetWriter;
import org.apache.parquet.hadoop.ParquetWriter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.apache.parquet.hadoop.ParquetFileWriter.Mode.OVERWRITE;
import static org.apache.parquet.hadoop.metadata.CompressionCodecName.SNAPPY;

@RequiredArgsConstructor
@Service
public class ParquetService {
    private final SpotifyRequest spotifyRequest;
    private final ArtistService artistService;

    public void writeParquet() {
        final LocalDate now = LocalDate.now();
        final String date = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Path dataFile = new Path(date + "/top-tracks.parquet");
        // Write as Parquet file.
        try (ParquetWriter<TopTrack> writer = AvroParquetWriter.<TopTrack>builder(dataFile)
                .withSchema(ReflectData.AllowNull.get().getSchema(TopTrack.class))
                .withDataModel(ReflectData.get())
                .withConf(new Configuration())
                .withCompressionCodec(SNAPPY)
                .withWriteMode(OVERWRITE)
                .build()) {
            final List<Artist> artists = artistService.getArtists();
            for (Artist artist : artists.subList(0, 10)) {
                final String artistId = artist.getId();
                final Track[] tracks = spotifyRequest.getTopTracks(artistId);
                for (Track track : tracks) {
                    final TopTrack topTrack = transTopTrack(track);
                    topTrack.setArtistId(artistId);
                    writer.write(topTrack);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TopTrack transTopTrack(Track track) {
        return TopTrack.builder()
                .id(track.getId())
                .name(track.getName())
                .externalUrl(track.getExternalUrls().get("spotify"))
                .popularity(track.getPopularity())
                .build();
    }

    @Builder
    @Data
    static class TopTrack {
        private String id;
        private String artistId;
        private String name;
        private Integer popularity;
        private String externalUrl;
    }
}
