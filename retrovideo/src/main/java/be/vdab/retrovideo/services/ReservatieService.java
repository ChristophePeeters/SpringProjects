package be.vdab.retrovideo.services;

import be.vdab.retrovideo.domain.Reservatie;

import java.util.List;

public interface ReservatieService {
    int reserveren(Reservatie r);
    List<Reservatie> findAll();
}
