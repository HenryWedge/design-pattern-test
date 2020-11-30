package de.ppi.here.demo.service;

import org.springframework.stereotype.Service;
import de.ppi.here.demo.bo.BusinessObject;
import de.ppi.here.demo.results.OperationResult;

@Service
public interface MasterDataAdministrationService <T extends BusinessObject>{

    OperationResult insert(T businessObject);

}
