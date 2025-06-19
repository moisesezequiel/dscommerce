package com.devsuperior.dscommerce.controllers;

import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value ="/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping(value = "/{id}")
    public ProductDTO findById(@PathVariable Long id ){
        return  service.findById(id);
    }

    @GetMapping                                        //exemplos de query params (postman)
    public Page<ProductDTO> findAll(Pageable pageable){//products?size=12&page=1&sort=name,desc
        return service.findAll(pageable);
    }


    @PostMapping
    public ProductDTO insert(@RequestBody ProductDTO dto){
        return  service.insert(dto);
    }
}
