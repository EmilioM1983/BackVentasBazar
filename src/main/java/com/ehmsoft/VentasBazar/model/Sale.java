package com.ehmsoft.VentasBazar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
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
    @ManyToOne
    @JoinColumn(name = "id_product", 
                referencedColumnName = "id_product")
    private Product product;
    @OneToOne
    @JoinColumn(name = "id_client",
                referencedColumnName = "idClient")
    private Client client;
}
