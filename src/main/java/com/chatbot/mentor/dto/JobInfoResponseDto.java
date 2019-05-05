package com.chatbot.mentor.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

/**
 * @author junho.park
 */
@Getter
@Builder
public class JobInfoResponseDto {
    @NonNull private String equalEmployment;
    @NonNull private String job;
    @NonNull private String possibility;
    @NonNull private String prospect;
    @NonNull private String salary;
    @NonNull private String profession;
    @NonNull private String summary;
    private String similarJob;
}
