package com.lucsan.car.controller;

import java.util.Arrays;
import java.util.List;
import com.lucsan.car.dto.EntryDTO;
import com.lucsan.car.dto.OutputDTO;
import com.lucsan.car.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/post")
    public ResponseEntity<?> saveCar(@RequestBody EntryDTO entryDTO) {
        try {
            validateEntryDTO(entryDTO);
            OutputDTO savedCar = carService.saveCar(entryDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCar);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    private void validateEntryDTO(EntryDTO entryDTO) {
        if (isNullOrEmpty(entryDTO.getName())) {
            throw new IllegalArgumentException("Name is required");
        }
        if (isNullOrEmpty(entryDTO.getBrand())) {
            throw new IllegalArgumentException("Brand is required");
        }
        if (isNullOrEmpty(entryDTO.getColor())) {
            throw new IllegalArgumentException("Color is required");
        }
        if (isNullOrEmpty(entryDTO.getFabricationYear())) {
            throw new IllegalArgumentException("Fabrication Year is required");
        }

        if (!isValidBrand(entryDTO.getBrand())) {
            throw new IllegalArgumentException("Car brand not supported");
        }
    }

    private boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    private boolean isValidBrand(String brand) {
        List<String> acceptedBrands = Arrays.asList("Ford", "Chevrolet", "BMW", "Volvo");
        return acceptedBrands.contains(brand);
    }

    @GetMapping("/get/{idChassi}")
    public ResponseEntity<OutputDTO> getCarByIdChassi(@PathVariable Long idChassi) {
        OutputDTO car = carService.getCarByIdChassi(idChassi);
        return ResponseEntity.ok(car);
    }
}
