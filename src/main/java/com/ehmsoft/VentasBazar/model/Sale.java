package com.ehmsoft.VentasBazar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Emilio Mayer
 */
@Entity
@Data
@Table(name = "sale")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSale;
    private LocalDate saleDate;
    private Double total;
    @ManyToMany
    @JoinTable(name = "rel_product_sale", 
                joinColumns= @JoinColumn(name="FK_product", nullable= false),
                inverseJoinColumns= @JoinColumn(name="FK_sale", nullable= false)
            )
    private List<Product> listProduct;
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "idClient")
    private Client client;
}
