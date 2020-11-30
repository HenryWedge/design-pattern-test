package de.ppi.here.tcu.service;

import de.ppi.here.tcu.entity.Entity;

public interface DatabaseService {

    void checkConsistency(Entity entity);
}
