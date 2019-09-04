package be.vdab.retrovideo.services;

import be.vdab.retrovideo.domain.Klant;
import be.vdab.retrovideo.repositories.KlantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultKlantService implements KlantService {
    private final KlantRepository klantRepository;

    DefaultKlantService(KlantRepository klantRepository) {
        this.klantRepository = klantRepository;
    }

    @Override
    public List<Klant> findAll() {
        return klantRepository.findAll();
    }

    @Override
    public List<Klant> findName(String naam) {
        return klantRepository.findName(naam);
    }

    @Override
    public Klant findById(int id) {
        return klantRepository.findById(id);
    }
}
