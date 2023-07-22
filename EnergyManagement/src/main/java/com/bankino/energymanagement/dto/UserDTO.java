package com.bankino.energymanagement.dto;

import com.bankino.energymanagement.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class UserDTO {

    private Long id;

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 3, max = 30, message = "Name must be between 3 and 30 characters")
    private String name;

    @NotBlank(message = "Neighborhood cannot be empty")
    @Min(value = 1, message = "Neighborhood must be a positive number")
    private Integer neighborhood;

    @NotBlank(message = "City cannot be empty")
    private String city;



    public User toUserEntity() {
        User user = new User();
        user.setId(this.id);
        user.setName(this.name);
        user.setCity(this.city);
        user.setNeighborhood(this.neighborhood);
        return user;
    }
}

