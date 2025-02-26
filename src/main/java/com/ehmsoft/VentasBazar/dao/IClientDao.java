package com.ehmsoft.VentasBazar.dao;

import com.ehmsoft.VentasBazar.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface ClientDAO
 * @author Emilio Mayer
 */
public interface IClientDao extends JpaRepository<Client, Long>{
    
}
