package com.bankino.energymanagement.controller;

import com.bankino.energymanagement.dto.EnergyMeterConsumptionDTO;
import com.bankino.energymanagement.dto.EnergyMeterDTO;
import com.bankino.energymanagement.entity.EnergyMeter;
import com.bankino.energymanagement.service.EnergyMeterService;
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
@RequestMapping("/energy-meters")
@Validated
public class EnergyMeterController {

    private final EnergyMeterService energyMeterService;

    @Autowired
    public EnergyMeterController(EnergyMeterService energyMeterService) {
        this.energyMeterService = energyMeterService;
    }

    @ApiOperation("Create a new energy meter")
    @ApiResponse(code = 201, message = "Energy meter created successfully")
    @PostMapping("/create-energy-meter")
    public ResponseEntity<EnergyMeter> createEnergyMeter(@Valid @RequestBody EnergyMeterDTO energyMeterDTO) {
        EnergyMeter energyMeter = energyMeterService.saveEnergyMeter(energyMeterDTO);
        return new ResponseEntity<>(energyMeter, HttpStatus.CREATED);
    }

    @ApiOperation("Get all energy meters")
    @ApiResponse(code = 200, message = "Successfully retrieved energy meters")
    @GetMapping("/get-all-energy-meters")
    public ResponseEntity<List<EnergyMeter>> getAllEnergyMeters() {
        List<EnergyMeter> energyMeters = energyMeterService.getAllEnergyMeters();
        return new ResponseEntity<>(energyMeters, HttpStatus.OK);
    }

    @ApiOperation("Get an energy meter by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved energy meter"),
            @ApiResponse(code = 404, message = "Energy meter not found")
    })
    @GetMapping("/{energyMeterId}")
    public ResponseEntity<EnergyMeter> getEnergyMeterById(@PathVariable Long energyMeterId) {
        EnergyMeter energyMeter = energyMeterService.getEnergyMeterById(energyMeterId);
        if (energyMeter != null) {
            return new ResponseEntity<>(energyMeter, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("Delete an energy meter by ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Energy meter deleted successfully"),
            @ApiResponse(code = 404, message = "Energy meter not found")
    })
    @DeleteMapping("/delete/{energyMeterId}")
    public ResponseEntity<Void> deleteEnergyMeter(@PathVariable Long energyMeterId) {
        energyMeterService.deleteEnergyMeter(energyMeterId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation("Update an energy meter by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Energy meter updated successfully"),
            @ApiResponse(code = 404, message = "Energy meter not found")
    })
    @PutMapping("/update/{energyMeterId}")
    public ResponseEntity<EnergyMeter> updateEnergyMeter(@PathVariable Long energyMeterId, @Valid @RequestBody EnergyMeterDTO energyMeterDTO) {
        EnergyMeter updatedEnergyMeter = energyMeterService.updateEnergyMeter(energyMeterId, energyMeterDTO);
        if (updatedEnergyMeter != null) {
            return new ResponseEntity<>(updatedEnergyMeter, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("Get cost per unit of energy meter consumption")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved cost per unit"),
            @ApiResponse(code = 404, message = "Energy meter consumption not found")
    })
    @GetMapping("/cost-per-unit")
    public ResponseEntity<EnergyMeterConsumptionDTO> getCostPerUnit() {
        EnergyMeterConsumptionDTO energyMeterConsumptionDTO = energyMeterService.getEnergyMeterConsumptionByMaxConsumptionDate();
        if (energyMeterConsumptionDTO != null) {
            return new ResponseEntity<>(energyMeterConsumptionDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
