package com.ehmsoft.VentasBazar.responseDto;

import com.ehmsoft.VentasBazar.model.Client;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 *
 * @author emaye
 */
//Body ResponseEntity
@Data
public class ClientResponse {
    private List<Client> listClient = new ArrayList();
}
