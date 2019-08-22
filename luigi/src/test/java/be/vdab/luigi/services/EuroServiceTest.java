package be.vdab.luigi.services;

import be.vdab.luigi.restclients.KoersClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EuroServiceTest {
    @Mock
    private KoersClient koersClient;
    private DefaultEuroService euroService;

    @Before
    public void before() {
        when(koersClient.getDollarKoers()).thenReturn(BigDecimal.valueOf(1.5));
        euroService = new DefaultEuroService(koersClient);
    }

    @Test
    public void naarDollar() {
        assertThat(euroService.naarDollar(BigDecimal.valueOf(2)))
                .isEqualByComparingTo("3");
        verify(koersClient).getDollarKoers();
    }
}