package com.lucsan.car.service;

import com.lucsan.car.dto.EntryDTO;
import com.lucsan.car.dto.OutputDTO;
import com.lucsan.car.entity.Car;
import com.lucsan.car.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public OutputDTO saveCar(EntryDTO entryDTO) {

        Car car = new Car();
        car.setName(entryDTO.getName());
        car.setBrand(entryDTO.getBrand());
        car.setColor(entryDTO.getColor());
        car.setFabricationYear(entryDTO.getFabricationYear());


        Car savedCar = carRepository.save(car);


        OutputDTO outputDTO = new OutputDTO();
        outputDTO.setIdChassi(savedCar.getIdChassi());
        outputDTO.setName(savedCar.getName());
        outputDTO.setBrand(savedCar.getBrand());
        outputDTO.setColor(savedCar.getColor());
        outputDTO.setFabricationYear(savedCar.getFabricationYear());

        return outputDTO;
    }

    @Override
    public OutputDTO getCarByIdChassi(Long idChassi) {
        Car car = carRepository.findById(idChassi).orElse(null);


        OutputDTO outputDTO = new OutputDTO();
        outputDTO.setIdChassi(car.getIdChassi());
        outputDTO.setName(car.getName());
        outputDTO.setBrand(car.getBrand());
        outputDTO.setColor(car.getColor());
        outputDTO.setFabricationYear(car.getFabricationYear());

        return outputDTO;
    }



    private boolean isValidBrand(String brand) {
        return brand != null && (brand.equalsIgnoreCase("Ford")
                || brand.equalsIgnoreCase("Chevrolet")
                || brand.equalsIgnoreCase("BMW")
                || brand.equalsIgnoreCase("Volvo"));
    }
}
