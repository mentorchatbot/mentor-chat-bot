package com.chatbot.mentor.service;

import com.chatbot.mentor.dto.JobInfoResponseDto;
import com.chatbot.mentor.util.MentorUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author junho.park
 */
@Service
public class ExternalApiService {
    private static final String API_ADDRESS = "https://www.career.go.kr/cnet/openapi/getOpenApi?apiKey=ca92633df07cb8e4739e66be3f2a1e66&svcType=api&svcCode=JOB&contentType=json&gubun=job_dic_list&category=";
    private String responseString;

    public List<JobInfoResponseDto> jobApiCall(int jobCode) {
        responseString = getJsonArrayFromCareerNet(jobCode);
        return parseJsonStringToJobInfoResponseDto(responseString);
    }

    private String getJsonArrayFromCareerNet(int jobCode) {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = URI.create(API_ADDRESS + jobCode);
        return restTemplate.getForObject(uri, String.class);
    }

    private List<JobInfoResponseDto> parseJsonStringToJobInfoResponseDto(String responseString) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(responseString);
        JsonObject jsonObjectDataSearch = (JsonObject) jsonObject.get("dataSearch");
        JsonArray jsonArray = (JsonArray) jsonObjectDataSearch.get("content");

        List<JobInfoResponseDto> list = new ArrayList<>();
        return MentorUtil.parseJsonArrayToJobInfoList(list, jsonArray);
    }
}

