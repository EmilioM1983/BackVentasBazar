package com.ehmsoft.VentasBazar.responseDto;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Emilio Mayer
 */
@Getter
@Setter
public class ClientResponseRest extends ResponseRest{
    private ClientResponse clientResponse = new ClientResponse();
}
