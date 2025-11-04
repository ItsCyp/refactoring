package org.iut.refactoring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RapportService {

    public String generate(TypeRapport type, List<Employe> employes, String filtre) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== RAPPORT: ").append(type).append(" ===\n");

        switch (type) {
            case SALAIRE:
                for (Employe emp : employes) {
                    if (filtre == null || filtre.isEmpty() || emp.division.equals(filtre)) {
                        double salaire = SalaryCalculator.calculate(emp);
                        sb.append(emp.name).append(": ").append(salaire).append(" €\n");
                    }
                }
                break;

            case EXPERIENCE:
                for (Employe emp : employes) {
                    if (filtre == null || filtre.isEmpty() || emp.division.equals(filtre)) {
                        sb.append(emp.name).append(": ").append(emp.experience).append(" années\n");
                    }
                }
                break;

            case DIVISION:
                Map<String, Integer> compteur = new HashMap<>();
                for (Employe emp : employes) {
                    compteur.put(emp.division, compteur.getOrDefault(emp.division, 0) + 1);
                }
                for (var entry : compteur.entrySet()) {
                    sb.append(entry.getKey()).append(": ").append(entry.getValue()).append(" employés\n");
                }
                break;
        }

        return sb.toString();
    }
}
