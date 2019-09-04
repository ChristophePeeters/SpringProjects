package be.vdab.frituurFrida.services;

import be.vdab.frituurFrida.domain.Saus;
import be.vdab.frituurFrida.repositiories.SausRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// enkele imports
@Service
class DefaultSausService implements SausService {
    private final SausRepository sausRepository;

    DefaultSausService(@Qualifier("properties") SausRepository sausRepository) {
        this.sausRepository = sausRepository;
    }

    @Override
    public List<Saus> findAll() {
        return sausRepository.findAll();
    }

    @Override
    public List<Saus> findByNaamBegintMet(char letter) {
        return sausRepository.findAll().stream()
                .filter(saus -> saus.getNaam().charAt(0) == letter)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Saus> findById(long nr) {
        return sausRepository.findAll().stream()
                .filter(saus -> saus.getNummer() == nr).findFirst();
    }
}