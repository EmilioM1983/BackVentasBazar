
package com.ehmsoft.VentasBazar.service;

import com.ehmsoft.VentasBazar.model.Product;
import com.ehmsoft.VentasBazar.responseDto.ProductResponseRest;
import org.springframework.http.ResponseEntity;

/**
 * Service Product
 * @author Emilio Mayer
 */
public interface IProductService {
    /**
     * Search all products
     * @return 
     */
    public ResponseEntity<ProductResponseRest> searchProduct();
    
    /**
     * Search product by id
     * @param id
     * @return 
     */
    public ResponseEntity<ProductResponseRest> searchProductById(Long id);
    
    /**
     * Save a product
     * @return 
     */
    public ResponseEntity<ProductResponseRest> saveProduct();
    
    /**
     * Update a product
     * @param product
     * @param id
     * @return 
     */
    public ResponseEntity<ProductResponseRest> updateProduct(Product product, Long id);
    
    /**
     * Delete a product by id
     * @param id
     * @return 
     */
    public ResponseEntity<ProductResponseRest> deleteProductById(Long id);
}
