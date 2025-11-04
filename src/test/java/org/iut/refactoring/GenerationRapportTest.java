package org.iut.refactoring;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class GenerationRapportTest {

    private GestionPersonnel gp;

    @BeforeEach
    void setup() {
        gp = new GestionPersonnel();

        gp.ajouteSalarie("DEVELOPPEUR", "Alice", 50000, 6, "IT");
        gp.ajouteSalarie("CHEF DE PROJET", "Bob", 60000, 4, "RH");
        gp.ajouteSalarie("STAGIAIRE", "Charlie", 20000, 0, "IT");
    }

    private String captureOutput(Runnable action) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(out));
        action.run();
        System.setOut(original);
        return out.toString();
    }

    @Test
    void testRapportSalaireIT() {
        String output = captureOutput(() -> gp.generationRapport("SALAIRE", "IT"));
        assertTrue(output.contains("Alice"));
        assertTrue(output.contains("Charlie"));
        assertFalse(output.contains("Bob"));
    }

    @Test
    void testRapportExperienceRH() {
        String output = captureOutput(() -> gp.generationRapport("EXPERIENCE", "RH"));
        assertTrue(output.contains("Bob"));
        assertFalse(output.contains("Alice"));
    }

    @Test
    void testRapportDivision() {
        String output = captureOutput(() -> gp.generationRapport("DIVISION", null));
        assertTrue(output.contains("IT"));
        assertTrue(output.contains("RH"));
    }
}

