package org.isamm.Agile.Service.Entreprise;

import org.isamm.Agile.model.Entreprise;

import java.util.List;

public interface EntrepriseService {
    public Entreprise saveEntreprise(Entreprise entreprise);
    public List<Entreprise> getAllEntreprises();
    public Entreprise updateEntreprise(Entreprise entreprise);
    public boolean checkIfnameExists(String name);
    public boolean checkIfemailExists(String email);
    public void deleteEntreprise(Long id);
}
