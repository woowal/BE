package com.ddingmate.ddingmate.mark.controller;

import com.ddingmate.ddingmate.mark.dto.request.MarkCreateRequest;
import com.ddingmate.ddingmate.mark.dto.request.MarkDeleteRequest;
import com.ddingmate.ddingmate.mark.service.MarkService;
import com.ddingmate.ddingmate.util.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mark")
@RequiredArgsConstructor
public class MarkController {

    private final MarkService markService;

    @PostMapping
    public ApiResponse<Void> createLike(@RequestBody MarkCreateRequest markCreateRequest) {
        markService.createMark(markCreateRequest);

        return ApiResponse.ok();
    }

    @DeleteMapping
    public ApiResponse<Void> deleteLike(@RequestBody MarkDeleteRequest markDeleteRequest) {
        markService.deleteMark(markDeleteRequest);

        return ApiResponse.ok();
    }
}
