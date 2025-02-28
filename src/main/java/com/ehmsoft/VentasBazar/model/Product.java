
package com.ehmsoft.VentasBazar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;


/**
 *
 * @author Emilio Mayer
 */

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_product;
    private String name;
    private String brand;
    private Double cost;
    private Double stockAvailable;
    @ManyToMany(mappedBy = "listProduct")
    @JsonIgnore
    private List<Sale> listSale;

    public Product() {
    }
    
}
