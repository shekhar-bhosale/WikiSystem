package com.proofpoint.wikisystem.exceptions;

public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException(final String customerFacingErrorMessage) {
        super(customerFacingErrorMessage);
    }
}
