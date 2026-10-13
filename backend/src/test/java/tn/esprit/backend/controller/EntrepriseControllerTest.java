package tn.esprit.backend.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.backend.entity.Entreprise;
import tn.esprit.backend.service.IEntrepriseService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EntrepriseControllerTest {

    @Mock
    private IEntrepriseService entrepriseService;

    @InjectMocks
    private EntrepriseController controller;

    @Test
    void addEntreprise_delegueAuService() {
        Entreprise e = new Entreprise();
        when(entrepriseService.addEntreprise(e)).thenReturn(e);

        assertSame(e, controller.addEntreprise(e));
        verify(entrepriseService).addEntreprise(e);
    }

    @Test
    void updateEntreprise_delegueAuService() {
        Entreprise e = new Entreprise();
        when(entrepriseService.updateEntreprise(e)).thenReturn(e);

        assertSame(e, controller.updateEntreprise(e));
        verify(entrepriseService).updateEntreprise(e);
    }

    @Test
    void deleteEntreprise_delegueAuService() {
        controller.deleteEntreprise(1L);

        verify(entrepriseService).deleteEntreprise(1L);
    }

    @Test
    void getEntrepriseById_delegueAuService() {
        Entreprise e = new Entreprise();
        when(entrepriseService.getEntrepriseById(1L)).thenReturn(e);

        assertSame(e, controller.getEntrepriseById(1L));
    }

    @Test
    void getAllEntreprises_delegueAuService() {
        when(entrepriseService.getAllEntreprises()).thenReturn(List.of(new Entreprise()));

        assertEquals(1, controller.getAllEntreprises().size());
    }
}
