package com.ehmsoft.VentasBazar.dao;

import com.ehmsoft.VentasBazar.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface Product DAO
 * @author emaye
 */
public interface IproductDao extends JpaRepository<Product, Long>{
    
}
