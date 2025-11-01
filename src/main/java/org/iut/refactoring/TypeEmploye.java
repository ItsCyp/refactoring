package org.iut.refactoring;

public enum TypeEmploye {
    DEVELOPPEUR("DEVELOPPEUR"),
    CHEF_DE_PROJET("CHEF DE PROJET"),
    STAGIAIRE("STAGIAIRE");

    private final String label;

    TypeEmploye(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static TypeEmploye fromString(String value) {
        return switch (value.toUpperCase()) {
            case "DEVELOPPEUR" -> DEVELOPPEUR;
            case "CHEF DE PROJET" -> CHEF_DE_PROJET;
            case "STAGIAIRE" -> STAGIAIRE;
            default -> throw new IllegalArgumentException("Type inconnu: " + value);
        };
    }
}
