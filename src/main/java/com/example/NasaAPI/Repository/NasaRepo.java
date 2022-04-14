package com.example.NasaAPI.Repository;


import com.example.NasaAPI.Domain.ListPhoto;
import com.example.NasaAPI.Service.DateService;
import com.example.NasaAPI.Service.DownloadService;
import com.example.NasaAPI.Service.PhotoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import java.util.Set;


@RestController
@RequestMapping("/api")

public class NasaRepo {

    @Autowired
    private DateService dateService;

    @Autowired
    private PhotoService photoService;

    private final Logger LOGGER = LoggerFactory
            .getLogger(NasaRepo.class);

    private final String FILE_PATH = System.getProperty("user.home")
            + "/Downloads/imageDates.txt";

    Set<String> dates;

    @GetMapping("/photo")
    public void downloadAndDisplayPhoto() {
        dates = dateService.readDateFile(FILE_PATH);
        processRequest(dates, true, true);
    }


    @GetMapping("/photo/download")
    public void downloadPhoto() {
        dates = dateService.readDateFile(FILE_PATH);
        processRequest(dates, true, false);
    }


    @GetMapping("/photo/display")
    public void displayPhoto() {
        dates = dateService.readDateFile(FILE_PATH);
        processRequest(dates, false, true);
    }

    private void processRequest(Set<String> dates, boolean download,
                                boolean display) {
        for (String date : dates) {
            LOGGER.debug("Processing request for photo taken on {}", date);
            ListPhoto photo = photoService.getPhoto(date);
            LOGGER.debug("Photo selected :: {}", photo);

        }
    }
}

