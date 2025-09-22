package com.devsuperior.dscommerce.dto;

import java.util.ArrayList;
import java.util.List;

import com.devsuperior.dscommerce.entities.Category;
import com.devsuperior.dscommerce.entities.Product;
import jakarta.validation.constraints.*;

public class ProductDTO {

    private Long id;

    @Size(min = 3 , max = 80, message = "Nome precisa ter de 3 a 60 caracteres")
    @NotBlank(message = "Campo requerido")
    private String name;

    @Size(min = 10 ,message = "Descrição precisa ter no minimo 10 caracteres")
    @NotBlank(message = "Campo requerido")
    private String description;

    @NotNull(message = "Campo requerido")
    @Positive(message = "O preco deve ser positivo")
    private Double price;

    private String imgUrl;
    
    @NotEmpty(message = "Deve ter pelo menos 1 categoria")
    private List<CategoryDTO> categories = new ArrayList<>();


    public ProductDTO() {
    }

    public ProductDTO(Long id) {
    }

    public ProductDTO(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public ProductDTO(Product entity) {
        id = entity.getId();
        name = entity.getName();
        description = entity.getDescription();
        price = entity.getPrice();
        imgUrl = entity.getImgUrl();
        
        for(Category cat : entity.getCategories()) {
        	categories.add(new CategoryDTO(cat));
        }
    }


    public void setCategories(List<CategoryDTO> categories) {
		this.categories = categories;
	}

	public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

	public List<CategoryDTO> getCategories() {
		return categories;
	}
    
    
}
