package com.chatbot.mentor.controller;

import com.chatbot.mentor.dto.JobInfoResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author junho.park
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWebMvc
public class ExternalApiControllerTest {
    private static final int SAMPLE_JOB_CODE = 100041;
    @Mock
    private ExternalApiController externalApiController;

    private MockMvc mvc;
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(externalApiController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void externalApiCallTest() throws Exception {
        List<JobInfoResponseDto> list = new ArrayList<>();
        list.add(JobInfoResponseDto.builder()
                .job("국회의원")
                .profession("관리직(임원·부서장)")
                .equalEmployment("보통이상")
                .possibility("보통이상")
                .prospect("보통미만")
                .salary("4000만원 이상")
                .summary("국회의원은 무슨무슨 일을 한다.")
                .build());
        list.add(JobInfoResponseDto.builder()
                .job("교장")
                .profession("관리직(임원·부서장)")
                .equalEmployment("매우좋음")
                .possibility("보통이상")
                .prospect("보통미만")
                .salary("4000만원 이상")
                .summary("교장은 무슨무슨 일을 한다.")
                .build());

        ResponseEntity<List<JobInfoResponseDto>> responseEntity = new ResponseEntity<>(list, HttpStatus.OK);
        given(externalApiController.jobInfo(SAMPLE_JOB_CODE)).willReturn(responseEntity);

        mvc.perform(post("/jobInfo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(SAMPLE_JOB_CODE)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].job", is("국회의원")))
                .andExpect(jsonPath("$[0].profession", is("관리직(임원·부서장)")))
                .andExpect(jsonPath("$[1].job", is("교장")))
                .andExpect(jsonPath("$[1].profession", is("관리직(임원·부서장)")));
    }
}