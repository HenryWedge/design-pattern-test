package de.ppi.here.tcu.service;

import org.springframework.stereotype.Service;
import de.ppi.here.tcu.entity.Mandator;


/**
 * Erzeugt und persistiert die Infrastruktursaetze bei der Neuanlage von Mandanten. Dabei werden die
 * administrativen Auftragsarten vererbt.
 */
@Service
public class MandatorInfrastructureService {

    public void createMandatorInfrastructureWithOrderTypeInheritance(final Mandator businessObject,
        final Integer id) {
    }
}
