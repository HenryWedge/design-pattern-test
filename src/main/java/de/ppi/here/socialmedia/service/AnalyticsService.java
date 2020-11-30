package de.ppi.here.socialmedia.service;

import org.springframework.stereotype.Service;

@Service
public interface AnalyticsService {

    void sendContentToAnalyticsCompany(String content);
}
