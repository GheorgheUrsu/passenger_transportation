package com.passengertransportation.demo.controllers;

import com.passengertransportation.demo.dto.PassengerDTO;
import com.passengertransportation.demo.model.Passenger;
import com.passengertransportation.demo.service.PassengerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/passengers")
public class PassengerController {

    private final PassengerService passengerService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "GET ALL PASSENGERS", notes = "\n" +
            "This operation get all passenger")
    public List<PassengerDTO> getAllPassengers() {
        return passengerService.getAllPassengers();
    }

    @DeleteMapping("/{passengerID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "DELETE PASSENGER BY ID", notes = "\n" +
            "This operations deletes passenger with specific ID")
    public PassengerDTO deletePassenger(@PathVariable Long passengerID) {
        return passengerService.deletePassengerById(passengerID);
    }

    @PutMapping("/{passengerID}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "UPDATE PASSENGER", notes = "\n" +
            "This operation updates a passenger")
    public PassengerDTO updatePassenger(@PathVariable Long passengerID, @RequestBody PassengerDTO passengerDTO){
        return passengerService.updatePassenger(passengerID, passengerDTO);
    }

    @GetMapping("/{passengerID}")
    @ResponseStatus(HttpStatus.OK)
    public PassengerDTO getPassengerByID(@PathVariable Long passengerID){
        return passengerService.findPassengerByID(passengerID);
    }
}
