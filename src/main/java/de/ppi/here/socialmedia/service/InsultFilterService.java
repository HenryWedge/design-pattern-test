package de.ppi.here.socialmedia.service;

import org.springframework.stereotype.Service;

@Service
public interface InsultFilterService {

    String filterInsult(String content);

}
