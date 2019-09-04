package be.vdab.luigi.restclients;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
public class ECBKoersClientTest {
    @Autowired
    private ECBKoersClient client;
    @Test
    public void deKoersMoetPositiefZijn() {
        assertThat(client.getDollarKoers()).isPositive();
    }
}