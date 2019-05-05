package com.chatbot.mentor.domain;

import lombok.*;

/**
 * @author junho.park
 */
@Getter
@Builder
public class JobInfo {
    @NonNull private String equalEmployment;
    @NonNull private String job;
    @NonNull private String possibility;
    @NonNull private String prospect;
    @NonNull private String salary;
    @NonNull private String profession;
    @NonNull private String summary;
    private String similarJob;
}
