package com.ehmsoft.VentasBazar.service;

import com.ehmsoft.VentasBazar.dao.IClientDao;
import com.ehmsoft.VentasBazar.dao.ISaleDao;
import com.ehmsoft.VentasBazar.dto.ProductDto;
import com.ehmsoft.VentasBazar.dto.SaleRequestDto;
import com.ehmsoft.VentasBazar.model.Client;
import com.ehmsoft.VentasBazar.model.Product;
import com.ehmsoft.VentasBazar.model.Sale;
import com.ehmsoft.VentasBazar.responseDto.SaleResponseRest;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ehmsoft.VentasBazar.dao.IProductDao;

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
     * @param saleDto
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

        try {
            // Buscar el cliente en la BD
            Client client = clientDao.findById(saleDto.getClienteDto().getId_cliente())
                    .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));

            // Procesar productos
            for (ProductDto dto : saleDto.getListProductDto()) {
                Product product = productDao.findById(dto.getId_product())
                        .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));

                // Verificar stock suficiente
                if (product.getStockAvailable() < 1) {
                    response.setMetadata("Respuesta no ok", "-1",
                            "No hay stock suficiente para el producto: " + product.getName());
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                }

                // Actualizar stock y agregar a la lista
                product.setStockAvailable(product.getStockAvailable() - 1);
                listProducts.add(product);
                total += product.getCost();
            }

            // Crear y guardar la venta
            sale.setClient(client);
            sale.setSaleDate(saleDto.getDate());
            sale.setListProduct(listProducts);
            sale.setTotal(total);

            Sale saleToSave = saleDao.save(sale);

            // Guardar productos actualizados
            productDao.saveAll(listProducts);

            if (saleToSave != null) {
                response.setMetadata("Respuesta ok", "00", "Venta registrada exitosamente");
                response.getSaleResponse().setListSale(listSale);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setMetadata("Respuesta no ok", "-1", "Error al intentar guardar venta");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            response.setMetadata("Respuesta no Ok", "-1", "Error al consultar Venta");
            e.printStackTrace();  // Imprimir el stack trace para depuración
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
