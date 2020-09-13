package com.dbmi.seeds.controller;

import com.dbmi.seeds.model.Producer;
import com.dbmi.seeds.model.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/seedinspection")
public class ProducerController {
    private ProducerRepository producerRepository;

    @Autowired
    ProducerController(ProducerRepository myRepo){
        super();
        this.producerRepository = myRepo;
    } // DEFAULT CONSTRUCTOR

    // GET METHODS
    @GetMapping("/producers/rowcount")
    public ResponseEntity<Long> getRowCount() {
        Long tableRows =  producerRepository.count();
        return ResponseEntity.ok(tableRows);
    } // GETHOME()

    @GetMapping("/producers/find/all")
    public Iterable<Producer> findAllProducers() {
        return producerRepository.findAll();
    } // FINDALLPRODUCERS()

    @GetMapping("/producers/find/id/{producerId}")
    public ResponseEntity<Producer> getProducerById(@PathVariable(value = "producerId") Long producerId)
            throws ResourceNotFoundException {
        Producer myProducer =
                producerRepository
                        .findById(producerId)
                        .orElseThrow(() -> new ResourceNotFoundException("Producer information not found for id: " + producerId));
        return new ResponseEntity<Producer>(myProducer,HttpStatus.OK);
    } // FINDPRODUCERSBYID(LONG)

    @GetMapping("/producers/find/name/{producerShortName}")
    public ResponseEntity<Producer> getProducerByName(@PathVariable(value = "producerShortName") String producerShortName)
            throws ResourceNotFoundException {
        Producer myProducer;
        Optional<Producer> producerOptional = producerRepository.findByProducerShortName(producerShortName);
        if(producerOptional.isPresent()){
            myProducer = producerOptional.get();
        } else {
            throw new ResourceNotFoundException("Unable to locate producer: " + producerShortName);
        } // IF-ELSE
        return new ResponseEntity<Producer>(myProducer,HttpStatus.OK);
    } // FINDPRODUCERBYNAME(STRING)

    // POST METHODS
    @PostMapping("/producers/new")
    public Producer createProducer(@Valid @RequestBody Producer producer) {
        return producerRepository.save(producer);
    } // CREATEPRODUCER(producer)

    // PUT METHODS
    @PutMapping("/producers/update/{producerId}")
    public ResponseEntity<Producer> updateProducer(
            @PathVariable(value = "producerId") Long producerId, @Valid @RequestBody Producer producerDetails)
            throws ResourceNotFoundException {
        Producer producer =
                producerRepository
                        .findById(producerId)
                        .orElseThrow(() -> new ResourceNotFoundException("Producer record not found for id: " + producerId));
        producer.setProducerId(producerDetails.getProducerId());
        producer.setProducerName(producerDetails.getProducerName());
        producer.setProducerShortName(producerDetails.getProducerShortName());
        producer.setProducerAddress1(producerDetails.getProducerAddress1());
        producer.setProducerAddress2(producerDetails.getProducerAddress2());
        producer.setProducerCity(producerDetails.getProducerCity());
        producer.setProducerState(producerDetails.getProducerState());
        producer.setProducerZip(producerDetails.getProducerZip());
        final Producer updatedProducer = producerRepository.save(producer);
        return ResponseEntity.ok(updatedProducer);
    } // UPDATEPRODUCER(@PATHVARIABLE)

    // DELETE METHODS
    @DeleteMapping("/producers/delete/{producerId}")
    public Map<String, Boolean> deleteProducer(@PathVariable(value = "producerId") Long producerId) throws Exception {
        Producer producer =
                producerRepository
                        .findById(producerId)
                        .orElseThrow(() -> new ResourceNotFoundException("Producer record not found for id: " + producerId));
        producerRepository.delete(producer);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    } // DELETEPRODUCER(@PATHVARIABLE)

} // CLASS
