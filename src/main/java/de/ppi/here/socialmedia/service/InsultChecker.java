package de.ppi.here.socialmedia.service;

import org.springframework.stereotype.Service;

@Service
public interface InsultChecker  {

    boolean isContentIncludingInsult(String content);
}

