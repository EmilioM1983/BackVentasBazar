package com.ehmsoft.VentasBazar.controller;

import com.ehmsoft.VentasBazar.model.Client;
import com.ehmsoft.VentasBazar.responseDto.ClientResponseRest;
import com.ehmsoft.VentasBazar.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller of Client
 * @author Emilio Mayer
 */

@CrossOrigin(origins = {"http://localHost:4200"})
@RestController
@RequestMapping("/api/v1")
public class ClientRestController {
    @Autowired
    private IClientService clientService;
    
    @GetMapping("/clients")
    public ResponseEntity<ClientResponseRest> getAllClients(){
        return clientService.searchClient();
    }
    
    @GetMapping("/clients/{id}")
    public ResponseEntity<ClientResponseRest> getClient(@PathVariable Long id){
        return clientService.searchClientById(id);
    }
    
    @PostMapping("/clients")
    public ResponseEntity<ClientResponseRest> saveClient(@RequestBody Client client){
        return clientService.saveClient(client);
    }
    
    @PutMapping("/clients/{id}")
    public ResponseEntity<ClientResponseRest> UpdateClient(@RequestBody Client client, @PathVariable Long id){
        return clientService.updateClient(client, id);
    }
    
    @DeleteMapping("/clients/{id}")
    public ResponseEntity<ClientResponseRest> deleteClient(@PathVariable Long id){
        return clientService.deleteClientById(id);
    }
}
