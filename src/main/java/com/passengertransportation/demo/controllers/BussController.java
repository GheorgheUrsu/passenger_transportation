package com.passengertransportation.demo.controllers;

import com.passengertransportation.demo.dto.BussDTO;
import com.passengertransportation.demo.service.BussService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/busses")
@Api(value = "busses", description = "CRUD OPERATIONS FOR BUSSES", tags = "BUSSES")
public class BussController {

    private final BussService bussService;

    public BussController(BussService bussService) {
        this.bussService = bussService;
    }


    @GetMapping
    @ApiOperation(value = "GET ALL BUSSES", notes = "\n" +
            "This function get all busses")
    @ResponseStatus(HttpStatus.OK)
    public List<BussDTO> getAllBusses() {
        return bussService.getAllBusses();
    }

    @GetMapping("/{bussID}")
    @ApiOperation(value = "GET BUSS BY ID", notes = "\n" +
            "This function get a buss by id")
    @ResponseStatus(HttpStatus.OK)
    public BussDTO getBussByID(@PathVariable Long bussID) {
        return bussService.getBussByID(bussID);
    }

    @DeleteMapping("/{bussID}")
    @ApiOperation(value = "DELETE BUSS BY ID", notes = "\n" +
            "This function deletes a bus by id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public BussDTO deleteBussByID(@PathVariable Long bussID){
        return bussService.deleteBussByID(bussID);
    }

    @PostMapping
    @ApiOperation(value = "CREATE BUSS", notes = "\n" +
            "This function creates a buss")
    @ResponseStatus(HttpStatus.CREATED)
    public BussDTO createBuss(@RequestBody BussDTO bussDTO){
        return bussService.createBuss(bussDTO);
    }

    @PutMapping("/{bussID}")
    @ApiOperation(value = "UPDATE BUSS", notes = "\n" +
            "This function creates a updates a buss")
    @ResponseStatus(HttpStatus.CREATED)
    public BussDTO update(@RequestBody BussDTO bussDTO, @PathVariable Long bussID){
        return bussService.update(bussDTO, bussID);
    }

}
