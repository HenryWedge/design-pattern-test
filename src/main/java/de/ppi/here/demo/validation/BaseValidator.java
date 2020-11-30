package de.ppi.here.demo.validation;

/**
 * Interface für Validatoren, deren Validierungsschritte einzeln angesprochen werden dürfen. Dieses Interface
 * sollte nur von anderen Validatoren benutzt werden, da man ein tieferes Verständnis für den Standard-Ablauf einer
 * Validierung braucht.
 *
 * @author PPI AG Informationstechnologie
 * @version $Revision$
 * @param <B> Type der zu validierenden Bean
 * @param <C> Type des ValidationContext
 */
public interface BaseValidator<B, C extends ValidationContext> {

    /**
     * Fuehrt die Pflichtfeld-Prüfungen durch.
     *
     * @param bean Die zu validierende Bean.
     * @param validationContext ValidationContext
     * @param resultCollector Sammler für die Validierungs-Ergebnisse
     */
    public void validateMandatoryFieldConstraints(B bean, C validationContext,
                                                  ValidationResultCollector resultCollector);


    /**
     * Fuehrt die Einzelfeld-Prüfungen mit Ausnahme der Pflichtfeldprüfungen durch.
     *
     * @param bean Die zu validierende Bean.
     * @param validationContext ValidationContext
     * @param resultCollector Sammler für die Validierungs-Ergebnisse
     */
    public void validateSingleFieldConstraints(B bean, C validationContext,
                                               ValidationResultCollector resultCollector);


    /**
     * Fuehrt die feldübergreifenden Prüfungen durch.
     *
     * @param bean Die zu validierende Bean.
     * @param validationContext ValidationContext
     * @param resultCollector Sammler für die Validierungs-Ergebnisse
     */
    public void validateCrossFieldConstraints(B bean, C validationContext,
                                              ValidationResultCollector resultCollector);


    /**
     * Fuehrt die bean-übergreifenden Prüfungen einschliesslich der ForeignKey-Prüfungen durch.
     *
     * @param bean Die zu validierende Bean.
     * @param validationContext ValidationContext
     * @param resultCollector Sammler für die Validierungs-Ergebnisse
     */
    public void validateCrossBeanConstraints(B bean, C validationContext,
                                             ValidationResultCollector resultCollector);
}

