package com.example.productapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncAuditService {
    private static final Logger log = LoggerFactory.getLogger(AsyncAuditService.class);

    @Async
    public void record(String action, Long productId, String username) {
        log.info("AUDIT action={} productId={} username={}", action, productId, username);
    }
}
