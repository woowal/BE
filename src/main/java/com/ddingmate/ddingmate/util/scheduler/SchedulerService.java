package com.ddingmate.ddingmate.util.scheduler;

import com.ddingmate.ddingmate.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchedulerService {

    private final PostService postService;

    @Scheduled(cron = "0 0 0 * * *")
    public void deleteExpiredPosts() {
        postService.deleteExpiredPosts();
    }
}
