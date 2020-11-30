package de.ppi.here.tcu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import de.ppi.here.tcu.entity.Bank;
import de.ppi.here.tcu.composite.precondition.PreconditionNotFulfilledException;
import de.ppi.here.tcu.result.DuplicateEntityException;
import de.ppi.here.tcu.adminservice.inherit.BankAdministrationService;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        //SpringApplication.run(Main.class, args);
        System.out.println("mand42".toUpperCase());
    }
}
