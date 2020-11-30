package de.ppi.here.demo.validation;

import org.springframework.core.GenericTypeResolver;

public abstract class AbstractValidator<B, C extends ValidationContext> implements BaseValidator<B, C> {
    private final AnnotatedFieldConstraintsValidator annotatedFieldConstraintsValidator;

    /**
     * Erzeugt eine neue Instanz.
     */
    public AbstractValidator() {
        @SuppressWarnings("unchecked") final Class<B> genericType =
                (Class<B>) GenericTypeResolver.resolveTypeArguments(getClass(), AbstractValidator.class)[0];
        annotatedFieldConstraintsValidator =
                new AnnotatedFieldConstraintsValidator(getDefaultPropertyContext(), genericType);
    }

    protected final void validateWithoutCrossBeanChecks(B bean, C validationContext,
                                                        ValidationResultCollector resultCollector) throws ConstraintViolationException {
        initValidationContext(validationContext);
        internalValidateMandatoryFields(bean, validationContext, resultCollector);
        resultCollector.checkConstraintViolations();
        validateAnnotatedFieldConstraints(bean, validationContext, resultCollector);
        validateOtherFieldConstraints(bean, validationContext, resultCollector);
        resultCollector.checkConstraintViolations();
        internalValidateCrossFieldConstraints(bean, validationContext, resultCollector);
        resultCollector.checkConstraintViolations();
    }

    @Override
    public final void validateMandatoryFieldConstraints(B bean, C validationContext,
                                                        ValidationResultCollector resultCollector) {
        initValidationContext(validationContext);
        internalValidateMandatoryFields(bean, validationContext, resultCollector);
    }

    /**
     * Interne Methode zur Durchfuehrung der Pflichtfeld-Prüfungen.
     *
     * @param bean              Die zu validierende Bean.
     * @param validationContext ValidationContext
     * @param resultCollector   Validation-Ergebnis
     */
    protected abstract void internalValidateMandatoryFields(B bean, C validationContext,
                                                            ValidationResultCollector resultCollector);

    @Override
    public final void validateSingleFieldConstraints(B bean, C validationContext,
                                                     ValidationResultCollector resultCollector) {
        initValidationContext(validationContext);
        validateAnnotatedFieldConstraints(bean, validationContext, resultCollector);
        validateOtherFieldConstraints(bean, validationContext, resultCollector);
    }

    /**
     * Fuehrt die Einzelfeld-Prüfungen (außer Pflichtfeld-Prüfungen) durch, die durch das Bean-Validation-Framework
     * abgedeckt werden.
     *
     * @param bean              Die zu validierende Bean.
     * @param validationContext ValidationContext
     * @param resultCollector   Validation-Ergebnis
     */
    protected abstract void validateAnnotatedFieldConstraints(B bean, C validationContext,
                                                              ValidationResultCollector resultCollector);

    /**
     * Fuehrt die Einzelfeld-Prüfungen durch, die nicht durch das Bean-Validation-Framework abgedeckt werden.
     *
     * @param bean              Die zu validierende Bean.
     * @param validationContext ValidationContext
     * @param resultCollector   Validation-Ergebnis
     */
    @SuppressWarnings("unused")
    protected void validateOtherFieldConstraints(B bean, C validationContext,
                                                 ValidationResultCollector resultCollector) {
    }

    @Override
    public final void validateCrossFieldConstraints(B bean, C validationContext,
                                                    ValidationResultCollector resultCollector) {
        initValidationContext(validationContext);
        internalValidateCrossFieldConstraints(bean, validationContext, resultCollector);
    }

    /**
     * Führt die feldübergreifenden Prüfungen durch.
     *
     * @param bean              Die zu validierende Bean.
     * @param validationContext ValidationContext
     * @param resultCollector   Validation-Ergebnis
     */
    @SuppressWarnings("unused")
    protected void internalValidateCrossFieldConstraints(B bean, C validationContext,
                                                         ValidationResultCollector resultCollector) {
    }

    /**
     * Liefert den DefaultContext fuer die Ermittlung der FieldlabelMessageId fuer die Properties.
     *
     * @return DefaultContext
     */
    protected abstract FieldlabelMessageGroup getDefaultPropertyContext();

    /**
     * Liefert die FieldlabelMessageId fuer eine Property.
     *
     * @param property          Property
     * @param validationContext ValidationContext
     * @return FieldlabelMessageId
     */
    protected final FieldlabelMessageId getFieldlabelMessageIdForProperty(String property,
                                                                          ValidationContext validationContext) {
        if (validationContext.getDefaultPropertyContext() == null) {
            validationContext.setDefaultPropertyContext(getDefaultPropertyContext());
        }
        return FieldlabelPropertyUtility.getFieldlabelMessageIdFromProperty(property, validationContext);
    }

    /**
     * Setzt die Default-Werte, die nicht über die Maske bzw. das Importformat gesetzt werden können.
     *
     * @param validationContext ValidationContext
     */
    public final void initValidationContext(ValidationContext validationContext) {
        if (validationContext.getPropertyContext() != getDefaultPropertyContext()) {
            validationContext.setDefaultPropertyContext(getDefaultPropertyContext());
        }
    }

    /**
     * Liefert den Validierer für per Annotation definierte Constraints.
     *
     * @return annotatedFieldConstraintsValidator.
     */
    protected final AnnotatedFieldConstraintsValidator getAnnotatedFieldConstraintsValidator() {
        return annotatedFieldConstraintsValidator;
    }

    /**
     * Prüft, dass der übergebene Inhalt nicht leer ist.
     *
     * @param violations        Aktuelle Fehlerliste, vielleicht mal intern in einem speziellen Validator verwalten?
     * @param validationContext ValidationContext, kann vielleicht in Zukunft entfallen.
     * @param content           Inhalt des Feldes
     * @param fieldname         Pflichtfeldname für Fehlertext
     */
    protected final void notBlank(final ValidationResultCollector violations,
                                  CommonValidationContext validationContext, final String content, final String fieldname) {
        if (content.equals("")) {
            violations.add(new SingleFieldConstraintViolation(fieldname, null,
                    fieldname + validationContext));
        }
    }

}

