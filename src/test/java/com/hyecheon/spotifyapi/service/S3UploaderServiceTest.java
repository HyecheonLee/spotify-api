package com.hyecheon.spotifyapi.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
class S3UploaderServiceTest {

    @Autowired
    private S3UploaderService s3UploaderService;

    @Test
    void uploadTest() {
        final File file = new File("C:\\Users\\hyecheon\\IdeaProjects\\spotify-api\\2019-12-10\\top-tracks.parquet");
        final String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        final String time = LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss"));

    }
}