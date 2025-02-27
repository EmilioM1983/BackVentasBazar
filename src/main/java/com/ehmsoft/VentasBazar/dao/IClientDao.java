package com.ehmsoft.VentasBazar.dao;

import com.ehmsoft.VentasBazar.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface ClientDAO
 * @author Emilio Mayer
 */
@Repository
public interface IClientDao extends JpaRepository<Client, Long>{
    
}
