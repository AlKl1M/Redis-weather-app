package com.alkl1m.weatherapp.service.impl;

import com.alkl1m.weatherapp.dto.web.GeoResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.Assert.assertEquals;

@Testcontainers
@SpringBootTest
@ActiveProfiles("dev")
class GeoServiceImplTest {

    @Autowired
    private GeoServiceImpl geoService;

    @Test
    void testGetGeoResponse_withValidData_returnsValidResponse() {
        List<GeoResponse> geoResponses = geoService.getGeoData("Moscow", 1);
        GeoResponse theMostSuitable = geoResponses.getFirst();

        assertEquals("Moscow", theMostSuitable.name());
        assertEquals("55.7504461", String.valueOf(theMostSuitable.lat()));
        assertEquals("37.6174943", String.valueOf(theMostSuitable.lon()));
    }

}
