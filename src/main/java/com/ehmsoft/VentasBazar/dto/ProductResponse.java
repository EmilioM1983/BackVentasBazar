package com.ehmsoft.VentasBazar.responseDto;

import com.ehmsoft.VentasBazar.model.Product;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Emilio Mayer
 */

//Body of Response Product
@Data
public class ProductResponse{
    private List<Product> listProduct= new ArrayList();
}
