package tn.esprit.backend;

import org.junit.jupiter.api.Test;
import tn.esprit.backend.entity.Equipe;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BackendApplicationTests {

    @Test
    void testEquipeCreation() {
        Equipe equipe = new Equipe();

        equipe.setNom("Equipe DevOps");
        equipe.setSpecialite("DevOps");

        assertEquals("Equipe DevOps", equipe.getNom());
        assertEquals("DevOps", equipe.getSpecialite());
    }

    @Test
    void testEquipeSettersAndGetters() {
        Equipe equipe = new Equipe();

        equipe.setId(1L);
        equipe.setNom("Equipe Backend");
        equipe.setSpecialite("Java");

        assertEquals(1L, equipe.getId());
        assertEquals("Equipe Backend", equipe.getNom());
        assertEquals("Java", equipe.getSpecialite());
    }

    @Test
    void testEquipeBuilder() {
        Equipe equipe = Equipe.builder()
                .id(2L)
                .nom("Equipe Frontend")
                .specialite("Angular")
                .build();

        assertEquals(2L, equipe.getId());
        assertEquals("Equipe Frontend", equipe.getNom());
        assertEquals("Angular", equipe.getSpecialite());
    }
}
