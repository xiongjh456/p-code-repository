package com.dxs.delay.retry.queue.controller;

import com.dxs.common.utils.SysResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/queue")
@RestController
public class QueueController {

    @PostMapping("/delay-retry")
    public SysResult<Object> delayRetry() {

    }
}
