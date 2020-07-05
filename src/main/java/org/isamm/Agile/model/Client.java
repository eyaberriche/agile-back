package org.isamm.Agile.model;

import lombok.AllArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;



public class Client extends User {
    @ManyToOne(fetch = FetchType.EAGER)
    private Entreprise entreprise;

    public Client(Entreprise entreprise) {
        this.entreprise = entreprise;
    }
}
