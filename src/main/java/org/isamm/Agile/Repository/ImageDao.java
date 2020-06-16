package org.isamm.Agile.Repository;


import java.util.Optional;

import org.isamm.Agile.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageDao extends JpaRepository<Image, Long> {
    //Optional<Image> findByName(String name);
}
