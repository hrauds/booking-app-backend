package com.bookservice.gametimebooking;

import com.bookservice.gametimebooking.service.GeocodingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GeocodingServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private GeocodingService geocodingService;

    @Test
    void testGetCoordinatesFail() throws JsonProcessingException {
        String mockAddress = "Mock Address";
        String mockResponse = "{\"results\":[]}";

        when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(String.class))).thenReturn(mockResponse);

        String result = geocodingService.getCoordinates(mockAddress);

        assertThat(result).isBlank();
    }
}
