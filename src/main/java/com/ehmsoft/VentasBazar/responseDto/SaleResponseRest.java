package com.ehmsoft.VentasBazar.responseDto;

import lombok.Getter;
import lombok.Setter;

/**
 * Union Headder & Body of SaleResonseRest
 * @author Emilio Mayer
 */
@Getter
@Setter
public class SaleResponseRest extends ResponseRest{
    private SaleResponse saleResponse = new SaleResponse();
}
