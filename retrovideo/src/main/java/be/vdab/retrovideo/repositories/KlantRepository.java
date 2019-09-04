package be.vdab.retrovideo.repositories;

import be.vdab.retrovideo.domain.Klant;

import java.util.List;

public interface KlantRepository {
    List<Klant> findAll();
    List<Klant> findName(String naam);
    Klant findById(int id);
}
