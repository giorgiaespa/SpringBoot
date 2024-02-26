package com.ex8.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    CarRepository carRepository;

    @PostMapping("/new")
    public Car createCar (@RequestBody Car newCar) {
        return carRepository.saveAndFlush(newCar);
    }

    @GetMapping("")
    public List <Car> readAllCars (){
        return carRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Car> readCarById (@PathVariable long id) {
        if (carRepository.existsById(id)) {
            return carRepository.findById(id);
        }
        return Optional.empty();
    }

    @PatchMapping("/updateType/{id}")
    public Optional<Car> updatedCarType (@PathVariable long id, @RequestParam String newType) {
        Optional<Car> car = carRepository.findById(id);
        if (car.isPresent()){
            car.get().setType(newType);
            carRepository.saveAndFlush(car.get());
            return car;
        }
        return Optional.empty();
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteById (@PathVariable long id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return HttpStatus.GONE;
        }
            return HttpStatus.CONFLICT;
    }

    @DeleteMapping("deleteAll")
    public void deleteAll (){
        carRepository.deleteAll();
    }


}
