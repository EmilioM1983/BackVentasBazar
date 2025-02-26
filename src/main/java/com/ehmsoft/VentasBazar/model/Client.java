package com.ehmsoft.VentasBazar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 *
 * @author Emilio Mayer
 */

@Data
@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;
    private String name;
    private String lastName;
    private String email;
    private String phone;}
