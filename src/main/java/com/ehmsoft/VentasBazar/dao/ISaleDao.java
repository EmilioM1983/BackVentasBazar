
package com.ehmsoft.VentasBazar.dao;

import com.ehmsoft.VentasBazar.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface SaleDAO 
 * @author Emilio Mayer
 */
public interface ISaleDao extends JpaRepository<Sale, Long>{
    
}
