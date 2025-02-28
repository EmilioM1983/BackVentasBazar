
package com.ehmsoft.VentasBazar.controller;

import com.ehmsoft.VentasBazar.model.Sale;
import com.ehmsoft.VentasBazar.responseDto.SaleResponseRest;
import com.ehmsoft.VentasBazar.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Emilio Mayer
 */
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = {"http://localHost:4200"})
public class SaleRestController {
    @Autowired
    private ISaleService saleService;
    
    /**
     * EndPoint Search all Sales
     * @return 
     */
    @GetMapping("/sale")
    public ResponseEntity<SaleResponseRest> searchAllSales(){
        return saleService.searchSales();
    }
    
    @GetMapping("/sale/{id}")
    public ResponseEntity<SaleResponseRest> searchByIdSales(Long id){
        return saleService.searchSaleById(id);
    }
    
    @PostMapping("/sale")
    public ResponseEntity<SaleResponseRest> saveSale(@RequestBody Sale sale){
        return saleService.saveSale(sale);
    }
}
