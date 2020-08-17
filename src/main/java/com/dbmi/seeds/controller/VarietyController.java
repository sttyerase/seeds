package com.dbmi.seeds.controller;

import com.dbmi.seeds.model.Variety;
import com.dbmi.seeds.model.VarietyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/seedinspection")
public class VarietyController {
    @Autowired
    private VarietyRepository varietyRepository;

    // GET METHODS
    @GetMapping("/varieties/rowcount")

    public ResponseEntity<Long> getRowCount() {
        Long tableRows =  varietyRepository.count();
        return ResponseEntity.ok(tableRows);
    } // GETHOME()

    @GetMapping("/varieties/all")
    public Iterable<Variety> findAllVarieties() {
        return varietyRepository.findAll();
    } // FINDALLVARIETIES()

    @GetMapping("/varieties/id/{varietyId}")
    public ResponseEntity<Variety> getVarietiesById(@PathVariable(value = "varietyId") Long varietyId)
            throws ResourceNotFoundException {
        Variety myVariety =
                varietyRepository
                        .findById(varietyId)
                        .orElseThrow(() -> new ResourceNotFoundException("Variety information not found for id: " + varietyId));
        return new ResponseEntity<Variety>(myVariety,HttpStatus.OK);
    } // FINDVARIETIESBYID(LONG)

    // POST METHODS
    @PostMapping("/varieties/new")
    public Variety createVariety(@Valid @RequestBody Variety variety) {
        return varietyRepository.save(variety);
    } // CREATEVARIETY(variety)

    // PUT METHODS
    @PutMapping("/varieties/update/{varietyId}")
    public ResponseEntity<Variety> updateVariety(
            @PathVariable(value = "varietyId") Long varietyId, @Valid @RequestBody Variety varietyDetails)
            throws ResourceNotFoundException {
        Variety variety =
                varietyRepository
                        .findById(varietyId)
                        .orElseThrow(() -> new ResourceNotFoundException("Variety record not found for id: " + varietyId));
        variety.setVarietyName(varietyDetails.getVarietyName());
        variety.setVarietyDescription(varietyDetails.getVarietyDescription());
        variety.setVarietyId(varietyDetails.getVarietyId());
        final Variety updatedVariety = varietyRepository.save(variety);
        return ResponseEntity.ok(updatedVariety);
    } // UPDATEVARIETY(@PATHVARIABLE)

    // DELETE METHODS
    @DeleteMapping("/varieties/delete/{varietyId}")
    public Map<String, Boolean> deleteVariety(@PathVariable(value = "varietyId") Long varietyId) throws Exception {
        Variety variety =
                varietyRepository
                        .findById(varietyId)
                        .orElseThrow(() -> new ResourceNotFoundException("Variety record not found for id: " + varietyId));
        varietyRepository.delete(variety);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    } // DELETEVARIETY(@PATHVARIABLE)

} // CLASS