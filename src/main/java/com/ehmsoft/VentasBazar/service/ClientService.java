package com.ehmsoft.VentasBazar.service;

import com.ehmsoft.VentasBazar.dao.IClientDao;
import com.ehmsoft.VentasBazar.model.Client;
import com.ehmsoft.VentasBazar.responseDto.ClientResponseRest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Client Service Implementation 
 * @author Emilio Mayer
 */
@Service
public class ClientService implements IClientService{

    @Autowired
    private IClientDao clientDao;
    
    /**
     * Search all client
     * @return 
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ClientResponseRest> searchClient() {
        //Declaro una variable del tipo ClientResponseRest
        ClientResponseRest response = new ClientResponseRest();
        
        try {
            //Busco Todos los clientes y los almaceno en una lista
            List<Client> listClient = (List<Client>) clientDao.findAll();
            
            //Añado la lista al cuerpo del ResponseEntity 
            response.getClientResponse().setListClient(listClient);
            
            //Añado datos al header de la ResponseEntity
            response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");

        } catch (Exception e) {
            //Añado datos al header de la ResponseEntity
            response.setMetadata("Respuesta no ok", "-1", "Error al consultar");
            e.getStackTrace();
            
            //Retorno el ResponseEntity completo y su estado
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //Retorno el ResponseEntity completo y su estado
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Search a client by id
     * @param id
     * @return 
     */
    @Override
    @Transactional (readOnly = true)
    public ResponseEntity<ClientResponseRest> searchClientById(Long id) {
        //Declaro una variable del tipo ClientResponseRest
        ClientResponseRest response = new ClientResponseRest();
        List<Client> listClient = new ArrayList();
        
        
        try {
            Optional<Client> clientFonud = clientDao.findById(id);
            
            if (clientFonud.isPresent()) {
                response.setMetadata("Respoesta ok", "00", "Cliente encontrado");
                listClient.add(clientFonud.get());
                response.getClientResponse().setListClient(listClient);
            }else {

                response.setMetadata("Respuesta no ok", "-1", "Cliente no encontrada");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setMetadata("Respuesta no ok", "-1", "Error al consultar cliente");
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    
    /**
     * Save a client
     * @param client
     * @return 
     */
    @Override
    @Transactional 
    public ResponseEntity<ClientResponseRest> saveClient(Client client) {
        //Declaro una variable del tipo ClientResponseRest
        ClientResponseRest response = new ClientResponseRest();
        List<Client>listClient = new ArrayList();
        
        try {
            
            Client clientSave = clientDao.save(client);
            
            if (clientSave!=null) {
                
                response.setMetadata("Respuesta ok", "00", "Cliente nuevo registrado");
                listClient.add(client);
                response.getClientResponse().setListClient(listClient);
                
            }else {

                response.setMetadata("Respuesta no ok", "-1", "Cliente no registrado");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

            }
            
        } catch (Exception e) {
            response.setMetadata("Respuesta no Ok", "-1", "Error al intentar guaradar cliente");
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Update a client by id
     * @param client
     * @param id
     * @return 
     */
    @Override
    @Transactional 
    public ResponseEntity<ClientResponseRest> updateClient(Client client, Long id){
         //Declaro una variable del tipo ClientResponseRest
        ClientResponseRest response = new ClientResponseRest();
        List<Client> listClient = new ArrayList();
        
        
        try {
            Optional<Client> clientFonud = clientDao.findById(id);
            
            if (clientFonud.isPresent()) {
                
                clientFonud.get().setName(client.getName());
                clientFonud.get().setLastName(client.getLastName());
                clientFonud.get().setPhone(client.getPhone());
                clientFonud.get().setEmail(client.getEmail());
                
                Client clientUpdate = clientDao.save(clientFonud.get());
                
                if (clientUpdate!=null) {
                    
                    response.setMetadata("Respuesta ok", "00", "Cliente actualizado");
                    listClient.add(clientUpdate);
                    response.getClientResponse().setListClient(listClient);
                }
               
            }else {

                response.setMetadata("Respuesta no ok", "-1", "Cliente no actualizado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setMetadata("Respuesta no ok", "-1", "Error al intentar actualizar cliente");
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return new ResponseEntity<>(response,HttpStatus.OK);
    
    }

    /**
     * delete a client by id
     * @param id
     * @return 
     */
    @Override
    @Transactional 
    public ResponseEntity<ClientResponseRest> deleteClientById(Long id) {
         //Declaro una variable del tipo ClientResponseRest
        ClientResponseRest response = new ClientResponseRest();
        List<Client>listClient = new ArrayList();
        Optional<Client> clientDelete = clientDao.findById(id);
        
        try {
                     
            if (clientDelete.isPresent()) {
                
                
                listClient.add(clientDelete.get());
                response.getClientResponse().setListClient(listClient);
                clientDao.deleteById(id);
                
                response.setMetadata("Respuesta ok", "00", "Cliente eliminado");
            
            }
            
        } catch (Exception e) {
            response.setMetadata("Respuesta no Ok", "-1", "Error al intentar eliminar cliente");
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}
