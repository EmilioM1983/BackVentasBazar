package com.ehmsoft.VentasBazar.dao;

import com.ehmsoft.VentasBazar.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface Product DAO
 * @author emaye
 */
@Repository
public interface IProductDao extends JpaRepository<Product, Long>{
    
}
