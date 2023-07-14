package com.lucsan.car.service;

import com.lucsan.car.dto.EntryDTO;
import com.lucsan.car.dto.OutputDTO;

public interface CarService {
    OutputDTO saveCar(EntryDTO entryDTO);

    OutputDTO getCarByIdChassi(Long idChassi);
}
