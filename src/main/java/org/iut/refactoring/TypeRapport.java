package org.iut.refactoring;

public enum TypeRapport {
    SALAIRE,
    EXPERIENCE,
    DIVISION;

    public static TypeRapport fromString(String value) {
        return TypeRapport.valueOf(value.toUpperCase());
    }
}
