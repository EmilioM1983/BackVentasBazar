package com.ehmsoft.VentasBazar.service;

import com.ehmsoft.VentasBazar.model.Product;
import com.ehmsoft.VentasBazar.responseDto.ProductResponseRest;
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
 *
 * @author Emilio Mayer
 */
@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductDao productDao;

    /**
     * Search all products
     *
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ProductResponseRest> searchProduct() {
        ProductResponseRest response = new ProductResponseRest();

        try {
            List<Product> listProduct = productDao.findAll();

            response.setMetadata("Respuesta ok", "00", "Listar clientes exitosa");
            response.getProductResponse().setListProduct(listProduct);

        } catch (Exception e) {
            response.setMetadata("Respuesta no ok", "-1", "Error al consultar clientes");
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Search a product
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ProductResponseRest> searchProductById(Long id) {
        ProductResponseRest response = new ProductResponseRest();
        List<Product> listProduct = new ArrayList();

        try {
            Optional<Product> productSearch = productDao.findById(id);
            if (productSearch.isPresent()) {

                listProduct.add(productSearch.get());
                response.setMetadata("Respuesta ok", "00", "Producto encontrado");
                response.getProductResponse().setListProduct(listProduct);

            } else {
                response.setMetadata("Respuesta no ok", "-1", "Error al buscar producto");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            response.setMetadata("Respuesta no ok", "-1", "Error al consultar producto");
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Save a product
     *
     * @param product
     * @return
     */
    @Override
    @Transactional
    public ResponseEntity<ProductResponseRest> saveProduct(Product product) {
        ProductResponseRest response = new ProductResponseRest();
        List<Product> listProduct = new ArrayList();
        //List<Sale> listSale = new ArrayList();
        //listSale.add(new Sale());
        //product.setListSale(listSale);
        try {
            Product productToSave = productDao.save(product);
            if (productToSave != null) {
                listProduct.add(productToSave);
                response.setMetadata("Respuesta ok", "00", "Producto guardado exitosamente");
                response.getProductResponse().setListProduct(listProduct);

            } else {
                response.setMetadata("Respuesta no ok", "-1", "Error guardar producto");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            response.setMetadata("Respuesta no ok", "-1", "Error al consultar producto");
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Update a product
     * @param product
     * @param id
     * @return 
     */
    @Override
    @Transactional
    public ResponseEntity<ProductResponseRest> updateProduct(Product product, Long id) {
        ProductResponseRest response = new ProductResponseRest();
        List<Product> listProduct = new ArrayList();

        try {
            Optional<Product> productToUpdate = productDao.findById(id);
            if (productToUpdate.isPresent()) {

                productToUpdate.get().setName(product.getName());
                productToUpdate.get().setBrand(product.getBrand());
                productToUpdate.get().setCost(product.getCost());
                productToUpdate.get().setStockAvailable(product.getStockAvailable());
                //productToUpdate.get().setListSale(product.getListSale());

                Product productUp = productDao.save(productToUpdate.get());

                if (productUp != null) {
                    listProduct.add(productUp);
                    response.setMetadata("Respuesta ok", "00", "Producto modificado exitosamente");
                    response.getProductResponse().setListProduct(listProduct);

                }
            } else {
                response.setMetadata("Respuesta no ok", "-1", "Error al modificar producto");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            response.setMetadata("Respuesta no ok", "-1", "Error al consultar producto");
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Delete a product
     * @param id
     * @return 
     */
    @Override
    public ResponseEntity<ProductResponseRest> deleteProductById(Long id) {
        ProductResponseRest response = new ProductResponseRest();
        List<Product> listProduct = new ArrayList();

        try {
            Optional<Product> productTdDelete = productDao.findById(id);
            if (productTdDelete.isPresent()) {

                listProduct.add(productTdDelete.get());
                response.setMetadata("Respuesta ok", "00", "Producto eliminado exitosamente");
                response.getProductResponse().setListProduct(listProduct);
                productDao.deleteById(id);

            } else {
                response.setMetadata("Respuesta no ok", "-1", "Error al eliminar producto");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            response.setMetadata("Respuesta no ok", "-1", "Error al consultar producto");
            e.getStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}


