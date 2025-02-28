package com.ehmsoft.VentasBazar.controller;

import com.ehmsoft.VentasBazar.model.Product;
import com.ehmsoft.VentasBazar.responseDto.ProductResponseRest;
import com.ehmsoft.VentasBazar.service.IProductService;
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
 *
 * @author Emilio Mayer
 */
@CrossOrigin(origins = {"http://localHost:4200"})
@RestController
@RequestMapping("/api/v1")
public class ProductRestController {
    @Autowired
    private IProductService productService;
    
    @GetMapping("/product")
    public ResponseEntity<ProductResponseRest> getAllClients(){
        return productService.searchProduct();
    }
    
    @GetMapping("/product/{id}")
    public ResponseEntity<ProductResponseRest> getClient(@PathVariable Long id){
        return productService.searchProductById(id);
    }
    
    @PostMapping("/product")
    public ResponseEntity<ProductResponseRest> saveClient(@RequestBody Product product){
        return productService.saveProduct(product);
    }
    
    @PutMapping("/product/{id}")
    public ResponseEntity<ProductResponseRest> UpdateClient(@RequestBody Product product, @PathVariable Long id){
        return productService.updateProduct(product, id);
    }
    
    @DeleteMapping("/product/{id}")
    public ResponseEntity<ProductResponseRest> deleteClient(@PathVariable Long id){
        return productService.deleteProductById(id);
    }
}
