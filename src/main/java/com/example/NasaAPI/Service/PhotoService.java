package com.example.NasaAPI.Service;
import com.example.NasaAPI.Domain.ListPhoto;
import java.util.Arrays;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


import com.example.NasaAPI.Domain.Photos;

@Service
public class PhotoService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;



    private static final String URI = "https://api.nasa.gov/planetary/apod?api_key=isOwDIZLa6CLuNLYqc1q3WjG90yciNHxywRzkcdV";
    private static final String NAVCAM = "NAVCAM";
    private static final String DEMO_KEY = "DEMO_KEY";
    private static final Logger LOGGER = LoggerFactory
            .getLogger(PhotoService.class);

    private HttpHeaders headers;
    private HttpEntity<String> entity;
    private UriComponentsBuilder builder;
    private ListPhoto listPhoto;

    public PhotoService() {
        headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        entity = new HttpEntity<>(headers);
    }

    public ListPhoto getPhoto(String date) {
        listPhoto = getAllPhotos(date);
        Photos photo = null;
        if (listPhoto != null && listPhoto.getPhotos() != null) {
            Photos[] photos = listPhoto.getPhotos();
            if (photos.length == 0) {
                throw new IllegalArgumentException(
                        "No photos available for the requested date " + date
                                + ". You may want to try other dates.");
            }
            photo = photos[listPhoto.nextInt(photos.length)];
            LOGGER.debug("Photo picked :: {}", listPhoto);
        }
        return listPhoto;
    }

    public ListPhoto getAllPhotos(String date) {
        builder = UriComponentsBuilder.fromHttpUrl(URI)
                .queryParam("earth_date", date).queryParam("camera", NAVCAM)
                .queryParam("api_key", DEMO_KEY);
        String uriString = builder.toUriString();
        LOGGER.debug("Fetching photos from URI :: {}", uriString);
        ResponseEntity<String> result = restTemplate.exchange(uriString,
                HttpMethod.GET, entity, String.class);
        if (result != null) {
            LOGGER.debug("Response body :: {}", result.getBody());
            try {
                listPhoto = objectMapper.readValue(result.getBody(),
                        ListPhoto.class);
                LOGGER.debug("PhotoList :: {}", listPhoto);
            } catch (Exception e) {
                LOGGER.debug("Exception :: {}", e);
            }
        }
        return listPhoto;
    }


}
