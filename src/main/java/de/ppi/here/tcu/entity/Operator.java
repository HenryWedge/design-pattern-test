package de.ppi.here.tcu.entity;

/**
 * Entitätsobjekt eines Betreibers
 */
public class Operator implements Entity {
    private String operatorId;

    private Integer techId;

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

    public Operator() {
    }

    public Operator(final String operatorId, final Integer techId, final String name, final String country,
                    final String zipCode, final String postOfficeCode, final String city, final String street,
                    final String houseNumber, final String salutation, final String firstName, final String lastName,
                    final String telephone, final String email, final String fax, final String department,
                    final Integer version) {
        this.operatorId = operatorId;
        this.techId = techId;
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
    }

    public String getOperatorId() {
        return operatorId;
    }

    public Integer getId() {
        return techId;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getPostOfficeCode() {
        return postOfficeCode;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getSalutation() {
        return salutation;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public String getFax() {
        return fax;
    }

    public String getDepartment() {
        return department;
    }

    public Integer getVersion() {
        return version;
    }

    public void setOperatorId(final String operatorId) {
        this.operatorId = operatorId;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public void setZipCode(final String zipCode) {
        this.zipCode = zipCode;
    }

    public void setPostOfficeCode(final String postOfficeCode) {
        this.postOfficeCode = postOfficeCode;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public void setStreet(final String street) {
        this.street = street;
    }

    public void setHouseNumber(final String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setSalutation(final String salutation) {
        this.salutation = salutation;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public void setTelephone(final String telephone) {
        this.telephone = telephone;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setFax(final String fax) {
        this.fax = fax;
    }

    public void setDepartment(final String department) {
        this.department = department;
    }

    public void setVersion(final Integer version) {
        this.version = version;
    }
}
