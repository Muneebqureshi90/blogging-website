package com.example.blog.application.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDto {

    private Integer cid;
    @NotBlank(message = "Please FIll This")
    @Size(min = 4,message = "Minium Title Should Be 4")
    private String categoryTitle;
    @NotBlank(message = "Please FIll This")
    @Size(min = 15,message = "Minium Description Should Be 15")
    private String categoryDescription;

}
