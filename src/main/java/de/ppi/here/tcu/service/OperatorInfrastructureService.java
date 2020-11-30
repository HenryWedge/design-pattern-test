package de.ppi.here.tcu.service;

import org.springframework.stereotype.Service;
import de.ppi.here.tcu.entity.Operator;
import de.ppi.here.tcu.result.DialogUserIdInformation;


/**
 * Erzeugt und persistiert die Infrastruktursaetze bei der Neuanlage von Mandanten. Dabei werden die
 * administrativen Auftragsarten vererbt.
 */
@Service
public class OperatorInfrastructureService {
    public void createOperatorInfrastructure(final Operator businessObject, final Integer id,
        final DialogUserIdInformation dialogUserIdInformation) {

    }
}
