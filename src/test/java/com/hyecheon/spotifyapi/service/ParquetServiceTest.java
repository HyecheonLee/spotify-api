package com.hyecheon.spotifyapi.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ParquetServiceTest {

    @Autowired
    private ParquetService parquetService;

    @Test
    void writeParquet() {
        parquetService.writeParquet();
    }
}