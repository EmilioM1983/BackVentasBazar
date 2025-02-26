package com.ehmsoft.VentasBazar.service;

import com.ehmsoft.VentasBazar.model.Client;
import com.ehmsoft.VentasBazar.responseDto.ClientResponseRest;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Emilio Mayer
 */
public interface IClientService {
    /**
     * Service search all client
     * @return 
     */
    public ResponseEntity<ClientResponseRest> searchClient();
    
    /**
     * service search client by id
     * @param id
     * @return 
     */
    public ResponseEntity<ClientResponseRest> searchClientById(Long id);
    
    /**
     * Service save client
     * @param client
     * @return 
     */
    public ResponseEntity<ClientResponseRest> saveClient(Client client);
    
    /**
     * Update client by id
     * @param client
     * @param id
     * @return 
     */
    public ResponseEntity<ClientResponseRest> updateClient(Client client, Long id);
    
    /**
     * Delete client by id 
     * @param id
     * @return 
     */
    public ResponseEntity<ClientResponseRest> deleteClientById(Long id);
}
