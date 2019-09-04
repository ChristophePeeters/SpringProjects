package be.vdab.retrovideo.services;

import be.vdab.retrovideo.domain.Reservatie;
import be.vdab.retrovideo.repositories.ReservatieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultReservatieService implements ReservatieService {
    private final ReservatieRepository reservatieRepository;


    public DefaultReservatieService(ReservatieRepository reservatieRepository) {
        this.reservatieRepository = reservatieRepository;

    }

    @Override
    public int reserveren(Reservatie r) {
        return reservatieRepository.reserveren(r);
    }

    @Override
    public List<Reservatie> findAll() {
        return null;
    }


}
