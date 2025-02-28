package com.ehmsoft.VentasBazar.service;

import com.ehmsoft.VentasBazar.model.Sale;
import com.ehmsoft.VentasBazar.responseDto.SaleResponseRest;
import org.springframework.http.ResponseEntity;

/**
 * Service of Sale
 * @author Emilio Mayer
 */
public interface ISaleService {
    /**
     * Service search all Sale
     * @return 
     */
    public ResponseEntity<SaleResponseRest> searchSales();
    
    /**
     * service search sale by id
     * @param id
     * @return 
     */
    public ResponseEntity<SaleResponseRest> searchSaleById(Long id);
    
    /**
     * Service save sale
     * @param sale
     * @return 
     */
    public ResponseEntity<SaleResponseRest> saveSale(Sale sale);
}
