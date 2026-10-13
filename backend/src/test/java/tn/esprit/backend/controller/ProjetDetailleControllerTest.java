package tn.esprit.backend.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.backend.entity.ProjetDetaille;
import tn.esprit.backend.service.IProjetDetailleService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjetDetailleControllerTest {

    @Mock
    private IProjetDetailleService projetDetailleService;

    @InjectMocks
    private ProjetDetailleController controller;

    @Test
    void addProjetDetaille_delegueAuService() {
        ProjetDetaille pd = new ProjetDetaille();
        when(projetDetailleService.addProjetDetaille(pd)).thenReturn(pd);

        assertSame(pd, controller.addProjetDetaille(pd));
        verify(projetDetailleService).addProjetDetaille(pd);
    }

    @Test
    void updateProjetDetaille_delegueAuService() {
        ProjetDetaille pd = new ProjetDetaille();
        when(projetDetailleService.updateProjetDetaille(pd)).thenReturn(pd);

        assertSame(pd, controller.updateProjetDetaille(pd));
        verify(projetDetailleService).updateProjetDetaille(pd);
    }

    @Test
    void deleteProjetDetaille_delegueAuService() {
        controller.deleteProjetDetaille(1L);

        verify(projetDetailleService).deleteProjetDetaille(1L);
    }

    @Test
    void getProjetDetailleById_delegueAuService() {
        ProjetDetaille pd = new ProjetDetaille();
        when(projetDetailleService.getProjetDetailleById(1L)).thenReturn(pd);

        assertSame(pd, controller.getProjetDetailleById(1L));
    }

    @Test
    void getAllProjetsDetailles_delegueAuService() {
        when(projetDetailleService.getAllProjetsDetailles()).thenReturn(List.of(new ProjetDetaille()));

        assertEquals(1, controller.getAllProjetsDetailles().size());
    }

    @Test
    void getProjetDetaillesByProjet_delegueAuService() {
        when(projetDetailleService.getProjetDetaillesByProjet(4L))
                .thenReturn(List.of(new ProjetDetaille(), new ProjetDetaille()));

        assertEquals(2, controller.getProjetDetaillesByProjet(4L).size());
        verify(projetDetailleService).getProjetDetaillesByProjet(4L);
    }

    @Test
    void assignProjetDetailleToProjet_delegueAuService() {
        ProjetDetaille pd = new ProjetDetaille();
        when(projetDetailleService.assignProjetDetailleToProjet(1L, 2L)).thenReturn(pd);

        assertSame(pd, controller.assignProjetDetailleToProjet(1L, 2L));
    }
}
