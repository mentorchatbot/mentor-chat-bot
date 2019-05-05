package com.chatbot.mentor.util;

import com.chatbot.mentor.dto.JobInfoResponseDto;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.experimental.UtilityClass;

import java.util.List;

/**
 * @author junho.park
 */
@UtilityClass
public class MentorUtil {
    public static List<JobInfoResponseDto> parseJsonArrayToJobInfoList(List<JobInfoResponseDto> list, JsonArray jsonArray) {
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject object = (JsonObject) jsonArray.get(i);
            String equalEmployment = object.get("equalemployment").getAsString();
            String job = object.get("job").getAsString();
            String possibility = object.get("possibility").getAsString();
            String prospect = object.get("prospect").getAsString();
            String salary = object.get("salery").getAsString();         // api 본래의 오타
            String profession = object.get("profession").getAsString();
            String summary = object.get("summary").getAsString();
            String similarJob = object.get("similarJob").getAsString();

            JobInfoResponseDto jobInfoResponseDto = JobInfoResponseDto.builder()
                    .job(job)
                    .similarJob(similarJob)
                    .equalEmployment(equalEmployment)
                    .possibility(possibility)
                    .profession(profession)
                    .prospect(prospect)
                    .salary(salary)
                    .summary(summary)
                    .build();
            list.add(jobInfoResponseDto);
        }

        return list;
    }
}
