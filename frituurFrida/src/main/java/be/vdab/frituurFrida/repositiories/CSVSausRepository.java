package be.vdab.frituurFrida.repositiories;

import be.vdab.frituurFrida.domain.Saus;
import be.vdab.frituurFrida.exceptions.SausRepositoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Qualifier("CSV")
@Component
public class CSVSausRepository implements SausRepository {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Path pad;
    CSVSausRepository(@Value("${CSVSausenPad}") Path pad) {
        this.pad = pad;
    }
    @Override
    public List<Saus> findAll() {
        List<Saus> sauzen = new ArrayList<>();
        try {
            return Files.lines(pad)
                    .filter(regel -> !regel.isEmpty())
                    .map(regel -> maakSaus(regel))
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            String fout = "Fout bij lezen " + pad;
            logger.error(fout, ex);
            throw new SausRepositoryException(fout);
        }
    }

    private Saus maakSaus(String regel) {
        String[] onderdelen = regel.split(",");
        if (onderdelen.length < 2) {
            String fout = pad + ":" + regel + " bevat minder dan 2 onderdelen";
            logger.error(fout);
            throw new SausRepositoryException(fout);
        }
        try {
            String[] ingredienten = new String[onderdelen.length - 2];
            for (int index = 2; index < onderdelen.length; index++) {
                ingredienten[index - 2] = onderdelen[index];
            }
            return new Saus(Long.parseLong(onderdelen[0]), onderdelen[1], ingredienten);
        } catch (NumberFormatException ex) {
            String fout = pad + ":" + regel + " bevat verkeerde id";
            logger.error(fout, ex);
            throw new SausRepositoryException(fout);
        }
    }
}
