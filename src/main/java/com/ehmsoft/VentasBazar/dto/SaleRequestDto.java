package com.ehmsoft.VentasBazar.dto;


import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Emilio Mayer
 */
@Getter
@Setter
public class SaleRequestDto {
    private ClienteDto clienteDto;
    private LocalDate date;   
    private List<ProductDto> listProductDto;
}
