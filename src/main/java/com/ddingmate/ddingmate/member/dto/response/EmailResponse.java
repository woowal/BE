package com.ddingmate.ddingmate.member.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmailResponse {

    private String to;
    private String message;

    public static EmailResponse of(String to, String message) {
        return EmailResponse.builder()
                .to(to)
                .message(message)
                .build();
    }

}
