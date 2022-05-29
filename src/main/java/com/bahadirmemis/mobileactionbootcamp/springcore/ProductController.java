package com.bahadirmemis.mobileactionbootcamp.springcore;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductDao productDao;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){

        List<Product> productList = productDao.findAll();

        return ResponseEntity.ok(productList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id){

        Product myProduct = productDao.findById(id).orElseThrow();

        return ResponseEntity.ok(myProduct);
    }

    @PostMapping
    public ResponseEntity<Product> saveProdcut(@RequestBody Product product){

        product = productDao.save(product);

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        productDao.deleteById(id);
    }

    @PutMapping
    public ResponseEntity<Product> updateProdcut(@RequestBody Product product){

        if (product.getId() == null){
            throw new RuntimeException("Product id cannot be empty!");
        }

        boolean isExist = productDao.existsById(product.getId());
        if (!isExist){
            throw new RuntimeException("Product does not exist!");
        }

        product = productDao.save(product);

        return ResponseEntity.ok(product);
    }

    @PatchMapping("/passive/{id}")
    public ResponseEntity makePassive(@PathVariable Long id){

        Product product = productDao.findById(id).orElseThrow();

        product.setIsActive(Boolean.FALSE);

        product = productDao.save(product);

        return ResponseEntity.ok(product);
    }
}
