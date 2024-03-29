package be.vdab.frituurFrida.controllers;

import be.vdab.frituurFrida.domain.Saus;
import be.vdab.frituurFrida.services.SausService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SausControllerTest {
    @Mock
    private SausService sausService;
    private SausController controller;

    @Before
    public void before() {
        when(sausService.findById(3)).thenReturn(Optional.of(new Saus(3L, "", null)));
        controller = new SausController(sausService);
    }

    @Test
    public void sauzenGebruiktDeThymeleafPaginaSauzen() {
        assertThat(controller.sauzen().getViewName()).isEqualTo("sauzen");
    }

    @Test
    public void sauzenGeeftSauzenDoorAanDeThymeleafPagina() {
        assertThat(controller.sauzen().getModel().get("sauzen")).isInstanceOf(List.class);
    }

    @Test
    public void sausGebruiktDeThymeleafPaginaSaus() {
        assertThat(controller.saus(1).getViewName()).isEqualTo("saus");
    }

    @Test
    public void sausGeeftGevondenSausDoorAanDeThymeleafPagina() {
        Saus saus = (Saus) controller.saus(1).getModel().get("saus");
        assertThat(saus.getNummer()).isEqualTo(1);
    }

    @Test
    public void sausGeeftOnbestaandeSausNietDoorAanDeThymeleafPagina() {
        assertThat(controller.saus(-1).getModel()).doesNotContainKeys("saus");
    }
}
