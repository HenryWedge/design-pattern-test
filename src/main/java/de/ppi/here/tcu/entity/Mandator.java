package de.ppi.here.tcu.entity;

import java.util.ArrayList;
import java.util.List;
import de.ppi.here.tcu.service.AddInfoField;

/**
 * Entitätsobjekt eines Mandanten
 */
public class Mandator implements Entity {

    private Integer techId;

    private String operatorId;

    private String mandatorId;

    private String name;

    private String country;

    private String zipCode;

    private String postOfficeCode;

    private String city;

    private String street;

    private String houseNumber;

    private String salutation;

    private String firstName;

    private String lastName;

    private String telephone;

    private String email;

    private String fax;

    private String department;

    private Integer version;

    private List<AddInfoField> addInfoFields;

    public Mandator() {
    }


    public Mandator(final String operatorId, final String mandatorId, final Integer techId, final String name,
        final String country, final String zipCode, final String postOfficeCode, final String city,
        final String street, final String houseNumber, final String salutation, final String firstName,
        final String lastName, final String telephone, final String email, final String fax,
        final String department, final Integer version) {
        this.techId = techId;
        this.operatorId = operatorId;
        this.mandatorId = mandatorId;
        this.name = name;
        this.country = country;
        this.zipCode = zipCode;
        this.postOfficeCode = postOfficeCode;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.salutation = salutation;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.email = email;
        this.fax = fax;
        this.department = department;
        this.version = version;
        this.addInfoFields = new ArrayList<>();
    }


    public Integer getTechId() {
        return techId;
    }

    public void setTechId(final Integer techId) {
        this.techId = techId;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(final String operatorId) {
        this.operatorId = operatorId;
    }

    public String getMandatorId() {
        return mandatorId;
    }

    public void setMandatorId(final String mandatorId) {
        this.mandatorId = mandatorId;
    }

    public Integer getId() {
        return techId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(final String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPostOfficeCode() {
        return postOfficeCode;
    }

    public void setPostOfficeCode(final String postOfficeCode) {
        this.postOfficeCode = postOfficeCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(final String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(final String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(final String salutation) {
        this.salutation = salutation;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(final String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(final String fax) {
        this.fax = fax;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(final String department) {
        this.department = department;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(final Integer version) {
        this.version = version;
    }

    public List<AddInfoField> getAddInfoFieldList() {
        return addInfoFields;
    }

    public void addAddInfoField(final AddInfoField addInfoField) {
        addInfoFields.add(addInfoField);
    }
}
