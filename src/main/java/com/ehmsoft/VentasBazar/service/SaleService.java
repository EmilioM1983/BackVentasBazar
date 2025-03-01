package com.ehmsoft.VentasBazar.service;

import com.ehmsoft.VentasBazar.dao.IClientDao;
import com.ehmsoft.VentasBazar.dao.IProductDao;
import com.ehmsoft.VentasBazar.dao.ISaleDao;
import com.ehmsoft.VentasBazar.dto.ProductDto;
import com.ehmsoft.VentasBazar.dto.SaleRequestDto;
import com.ehmsoft.VentasBazar.model.Client;
import com.ehmsoft.VentasBazar.model.Product;
import com.ehmsoft.VentasBazar.model.Sale;
import com.ehmsoft.VentasBazar.responseDto.SaleResponseRest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Sale
 *
 * @author Emilio Mayer
 */
@Service
public class SaleService implements ISaleService {

    @Autowired
    private ISaleDao saleDao;
    @Autowired
    private IProductDao productDao;
    @Autowired
    private IClientDao clientDao;

    /**
     * search all products
     *
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<SaleResponseRest> searchSales() {
        SaleResponseRest response = new SaleResponseRest();

        try {
            List<Sale> listsales = saleDao.findAll();
            response.setMetadata("Respuesta ok", "00", "Ventas listados");
            response.getSaleResponse().setListSale(listsales);

        } catch (Exception e) {
            //Añado datos al header de la ResponseEntity
            response.setMetadata("Respuesta no ok", "-1", "Error al consultar");
            e.getStackTrace();

            //Retorno el ResponseEntity completo y su estado
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Search a sale by id
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<SaleResponseRest> searchSaleById(Long id) {
        SaleResponseRest response = new SaleResponseRest();
        Optional<Sale> saleFound = saleDao.findById(id);
        List<Sale> listSale = new ArrayList();

        try {
            if (saleFound.isPresent()) {
                listSale.add(saleFound.get());
                response.setMetadata("REspuesta ok", "00", "Venta encontrada");
                response.getSaleResponse().setListSale(listSale);
            } else {
                response.setMetadata("Respuesta no ok", "-1", "Venta no encontrada");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setMetadata("Respuesta no ok", "-1", "Error al consultar cliente");
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Save a sale
     *
     * @param sale
     * @return
     */
    @Override
    @Transactional
    public ResponseEntity<SaleResponseRest> saveSale(SaleRequestDto saleDto) {

        double total = 0;
        SaleResponseRest response = new SaleResponseRest();
        List<Product> listProducts = new ArrayList<>();
        List<Sale> listSale = new ArrayList();
        Sale sale = new Sale();

        if (sale.getClient() == null) {
            sale.setClient(new Client()); // Asegúrate de que el cliente esté inicializado
        }

        try {
            // Buscar el cliente en la BD
            Client client = clientDao.findById(saleDto.getClienteDto().getId_cliente())
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

            // Procesar productos
            for (ProductDto dto : saleDto.getListProductDto()) {
                Product product = productDao.findById(dto.getId_product())
                        .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

                // Verificar stock suficiente
                if (product.getStockAvailable() < 1) {
                    response.setMetadata("Respuesta no ok", "-1",
                                "No hay stock suficiente para el producto: " + product.getName());
                        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                }

                product.setStockAvailable(product.getStockAvailable() - 1);
                listProducts.add(product);
                total += product.getCost();
            }

            sale.getClient().setIdClient(saleDto.getClienteDto().getId_cliente());
            sale.setSaleDate(saleDto.getDate());
            sale.setListProduct(listProducts);

            

            // Asignar total de la venta
            sale.setTotal(total);

            // Guardar la venta en la base de datos
            Sale saleToSave = saleDao.save(sale);

            // Actualizar los productos con el nuevo stock en la BD
            if (saleToSave != null) {
                for (Product product : sale.getListProduct()) {
                    // Buscar el producto en la base de datos para actualizar el stock
                    Product productFromDB = productDao.findById(product.getId_product()).orElse(null);

                    if (productFromDB != null) {

                        productDao.save(productFromDB); // Guardamos el producto con el nuevo stock
                    } else {
                        // Si no encontramos el producto en la base de datos, podemos manejar el error
                        response.setMetadata("Respuesta no ok", "-1", "Producto no encontrado: " + product.getName());
                        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                    }
                }

                listSale.add(saleToSave);
                response.setMetadata("Respuesta ok", "00", "Venta registrada exitosamente");
            } else {
                response.setMetadata("Respuesta no ok", "-1", "Venta no registrada");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            response.setMetadata("Respuesta no Ok", "-1", "Error al consultar Venta");
            e.printStackTrace();  // Imprimir el stack trace para depuración
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
