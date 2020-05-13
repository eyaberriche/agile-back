package org.isamm.Agile.Repository;

import org.isamm.Agile.model.ProductBacklog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProductBacklogDao extends JpaRepository<ProductBacklog, Long>{

}
