package com.ehmsoft.VentasBazar.responseDto;

import lombok.Getter;
import lombok.Setter;

/**
 *Union header & Body
 * @author Emilio Mayer
 */
@Getter
@Setter
public class ProductResponseRest extends ResponseRest{
    private ProductResponse productResponse = new ProductResponse();
}
