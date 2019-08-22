package be.vdab.frituurFrida.services;

import be.vdab.frituurFrida.domain.Saus;

import java.util.List;
import java.util.Optional;

public interface SausService {
    List<Saus> findAll();

    List<Saus> findByNaamBegintMet(char letter);

    Optional<Saus> findById(long id);
}
