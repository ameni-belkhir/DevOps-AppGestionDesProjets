package tn.esprit.backend.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.backend.entity.Equipe;
import tn.esprit.backend.service.IEquipeService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EquipeControllerTest {

    @Mock
    private IEquipeService equipeService;

    @InjectMocks
    private EquipeController controller;

    @Test
    void addEquipe_delegueAuService() {
        Equipe e = new Equipe();
        when(equipeService.addEquipe(e)).thenReturn(e);

        assertSame(e, controller.addEquipe(e));
        verify(equipeService).addEquipe(e);
    }

    @Test
    void updateEquipe_delegueAuService() {
        Equipe e = new Equipe();
        when(equipeService.updateEquipe(e)).thenReturn(e);

        assertSame(e, controller.updateEquipe(e));
        verify(equipeService).updateEquipe(e);
    }

    @Test
    void deleteEquipe_delegueAuService() {
        controller.deleteEquipe(1L);

        verify(equipeService).deleteEquipe(1L);
    }

    @Test
    void getEquipeById_delegueAuService() {
        Equipe e = new Equipe();
        when(equipeService.getEquipeById(1L)).thenReturn(e);

        assertSame(e, controller.getEquipeById(1L));
    }

    @Test
    void getAllEquipes_delegueAuService() {
        when(equipeService.getAllEquipes()).thenReturn(List.of(new Equipe()));

        assertEquals(1, controller.getAllEquipes().size());
    }

    @Test
    void getEquipesByEntreprise_delegueAuService() {
        when(equipeService.getEquipesByEntreprise(5L)).thenReturn(List.of(new Equipe(), new Equipe()));

        assertEquals(2, controller.getEquipesByEntreprise(5L).size());
        verify(equipeService).getEquipesByEntreprise(5L);
    }

    @Test
    void assignEquipeToEntreprise_delegueAuService() {
        Equipe e = new Equipe();
        when(equipeService.assignEquipeToEntreprise(1L, 2L)).thenReturn(e);

        assertSame(e, controller.assignEquipeToEntreprise(1L, 2L));
    }

    @Test
    void assignEquipeToProjet_delegueAuService() {
        Equipe e = new Equipe();
        when(equipeService.assignEquipeToProjet(1L, 3L)).thenReturn(e);

        assertSame(e, controller.assignEquipeToProjet(1L, 3L));
    }
}
