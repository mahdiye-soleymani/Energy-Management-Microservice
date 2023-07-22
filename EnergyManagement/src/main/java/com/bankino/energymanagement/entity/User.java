package com.bankino.energymanagement.entity;


import com.bankino.energymanagement.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;


@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false)
    private int neighborhood;

    @Column(nullable = false)
    private String city;


    public UserDTO toUserDto() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(this.id);
        userDTO.setName(this.name);
        userDTO.setCity(this.city);
        userDTO.setNeighborhood(this.neighborhood);
        return userDTO;
    }
}

