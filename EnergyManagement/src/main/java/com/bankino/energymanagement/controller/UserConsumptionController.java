package com.bankino.energymanagement.controller;

import com.bankino.energymanagement.dto.UserConsumptionDTO;
import com.bankino.energymanagement.entity.UserConsumption;
import com.bankino.energymanagement.service.UserConsumptionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user-consumptions")
@Validated
public class UserConsumptionController {

    private final UserConsumptionService userConsumptionService;

    @Autowired
    public UserConsumptionController(UserConsumptionService userConsumptionService) {
        this.userConsumptionService = userConsumptionService;
    }

    @ApiOperation("Create a new user consumption")
    @ApiResponse(code = 201, message = "User consumption created successfully")
    @PostMapping("/create-user-consumption")
    public ResponseEntity<UserConsumption> createUserConsumption(@Valid @RequestBody UserConsumptionDTO userConsumptionDTO) {

        UserConsumption userConsumption = userConsumptionService.saveUserConsumption(userConsumptionDTO);
        if (userConsumption != null) {
            return new ResponseEntity<>(userConsumption, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation("Get all user consumptions")
    @ApiResponse(code = 200, message = "Successfully retrieved user consumptions")
    @GetMapping("/get-all-user-consumptions")
    public ResponseEntity<List<UserConsumption>> getAllUserConsumptions() {
        List<UserConsumption> userConsumptions = userConsumptionService.getAllUserConsumptions();
        return new ResponseEntity<>(userConsumptions, HttpStatus.OK);
    }

    @ApiOperation("Get a user consumption by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved user consumption"),
            @ApiResponse(code = 404, message = "User consumption not found")
    })
    @GetMapping("/{userConsumptionId}")
    public ResponseEntity<UserConsumption> getUserConsumptionById(@PathVariable Long userConsumptionId) {
        UserConsumption userConsumption = userConsumptionService.getUserConsumptionById(userConsumptionId);
        if (userConsumption != null) {
            return new ResponseEntity<>(userConsumption, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
