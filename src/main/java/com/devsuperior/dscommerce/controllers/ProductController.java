package com.devsuperior.dscommerce.controllers;

import com.devsuperior.dscommerce.dto.CustomError;
import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.services.ProductService;
import com.devsuperior.dscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.Instant;

@RestController
@RequestMapping(value ="/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id ){
            ProductDTO dto =  service.findById(id);
            return ResponseEntity.ok(dto);
    }

    @GetMapping                                        //exemplos de query params (postman)
    public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable){//products?size=12&page=1&sort=name,desc
        Page<ProductDTO> dto = service.findAll(pageable);
        return ResponseEntity.ok(dto);
    }


    @PostMapping
    public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO dto){
        dto =service.insert(dto);
        // Boa prática para retornar code 201 de recurso criado e retornar o link do novo produto
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id  ,@RequestBody ProductDTO dto){
        dto =service.update(id,dto);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
