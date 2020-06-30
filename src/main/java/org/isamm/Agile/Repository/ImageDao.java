package org.isamm.Agile.Repository;


import java.util.List;
import java.util.Optional;

import org.isamm.Agile.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ImageDao extends JpaRepository<Image, Long> {
    //

    @Query("SELECT i "
            + "FROM User u "
            + "INNER JOIN u.image i "
            + "WHERE i.id = :id"
    )
    public Optional<Image> findByImage(@Param("id") Long id);
}
