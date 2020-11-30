package de.ppi.here.tcu.service;

import org.springframework.stereotype.Service;
import de.ppi.here.tcu.entity.Entity;

@Service
public interface DatabaseService {

    void checkConsistency(Entity entity);
}
