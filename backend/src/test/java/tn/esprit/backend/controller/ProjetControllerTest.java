package tn.esprit.backend.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.backend.entity.Projet;
import tn.esprit.backend.service.IProjetService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjetControllerTest {

    @Mock
    private IProjetService projetService;

    @InjectMocks
    private ProjetController controller;

    @Test
    void addProjet_delegueAuService() {
        Projet p = new Projet();
        when(projetService.addProjet(p)).thenReturn(p);

        assertSame(p, controller.addProjet(p));
        verify(projetService).addProjet(p);
    }

    @Test
    void updateProjet_delegueAuService() {
        Projet p = new Projet();
        when(projetService.updateProjet(p)).thenReturn(p);

        assertSame(p, controller.updateProjet(p));
        verify(projetService).updateProjet(p);
    }

    @Test
    void deleteProjet_delegueAuService() {
        controller.deleteProjet(1L);

        verify(projetService).deleteProjet(1L);
    }

    @Test
    void getProjetById_delegueAuService() {
        Projet p = new Projet();
        when(projetService.getProjetById(1L)).thenReturn(p);

        assertSame(p, controller.getProjetById(1L));
    }

    @Test
    void getAllProjets_delegueAuService() {
        when(projetService.getAllProjets()).thenReturn(List.of(new Projet(), new Projet()));

        assertEquals(2, controller.getAllProjets().size());
    }
}
