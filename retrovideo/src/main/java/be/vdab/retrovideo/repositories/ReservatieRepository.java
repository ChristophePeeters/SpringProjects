package be.vdab.retrovideo.repositories;

import be.vdab.retrovideo.domain.Reservatie;

import java.util.List;

public interface ReservatieRepository {
    int reserveren(Reservatie r);
    List<Reservatie> findAll();
}
