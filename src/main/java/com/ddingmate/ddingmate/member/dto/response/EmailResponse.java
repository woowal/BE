package com.ddingmate.ddingmate.member.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmailResponse {

    private String to;
    private String authKey;
    private boolean enable;

    public static EmailResponse of(String to, String authKey, boolean enable) {
        return EmailResponse.builder()
                .to(to)
                .authKey(authKey)
                .enable(enable)
                .build();
    }

}
