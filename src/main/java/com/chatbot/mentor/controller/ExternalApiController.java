package com.chatbot.mentor.controller;

import com.chatbot.mentor.dto.JobInfoResponseDto;
import com.chatbot.mentor.service.ExternalApiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author junho.park
 */
@RestController
public class ExternalApiController {
    private final ExternalApiService externalApiService;

    public ExternalApiController(ExternalApiService externalApiService) {
        this.externalApiService = externalApiService;
    }

    @PostMapping("/jobInfo")
    public ResponseEntity<List<JobInfoResponseDto>> jobInfo(@RequestBody int jobCode) {
        return new ResponseEntity<>(externalApiService.jobApiCall(jobCode), HttpStatus.OK);
    }
}
