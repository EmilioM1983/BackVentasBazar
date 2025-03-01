package com.ehmsoft.VentasBazar.responseDto;

import com.ehmsoft.VentasBazar.model.Sale;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * Body Response Sale
 * @author emaye
 */
@Data
public class SaleResponse {
    private List<Sale> listSale = new ArrayList();
}
