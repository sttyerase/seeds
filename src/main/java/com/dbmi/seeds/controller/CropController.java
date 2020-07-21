package com.dbmi.seeds.controller;

import com.dbmi.seeds.model.Crop;
import com.dbmi.seeds.model.CropRepository;

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
public class CropController {
    @Autowired
    private CropRepository cropRepository;

    @GetMapping("/crops/all")
    public Iterable<Crop> getAllCrops() {
        return cropRepository.findAll();
    } // GETALLCROPS()

    @GetMapping("/crops/{cropId}")
    public ResponseEntity<Crop> getCropsById(@PathVariable(value = "cropId") Long cropId)
            throws ResourceNotFoundException {
        Crop myCrop =
                cropRepository
                        .findById(cropId)
                        .orElseThrow(() -> new ResourceNotFoundException("Crop information not found for id: " + cropId));
        ResponseEntity<Crop> theRE = new ResponseEntity<Crop>(myCrop,HttpStatus.OK);
        return theRE;
    } // GETCROPSBYID(LONG)

    @PostMapping("/crops/new")
    public Crop createCrop(@Valid @RequestBody Crop crop) {
        return cropRepository.save(crop);
    } // CREATECROP(crop)

    @PutMapping("/crops/{id}")
    public ResponseEntity<Crop> updateCrop(
            @PathVariable(value = "id") Long cropId, @Valid @RequestBody Crop cropDetails)
            throws ResourceNotFoundException {
        Crop crop =
                cropRepository
                        .findById(cropId)
                        .orElseThrow(() -> new ResourceNotFoundException("Crop record not found for id: " + cropId));
        crop.setCropName(cropDetails.getCropName());
        crop.setCropDescription(cropDetails.getCropDescription());
        crop.setCropId(cropDetails.getCropId());
        crop.setCropICCCode(cropDetails.getCropICCCode());
        final Crop updatedCrop = cropRepository.save(crop);
        return ResponseEntity.ok(updatedCrop);
    } // UPDATECROP(@PATHVARIABLE)

    @DeleteMapping("/crops/delete/{cropId}")
    public Map<String, Boolean> deleteCrop(@PathVariable(value = "cropId") Long cropId) throws Exception {
        Crop crop =
                cropRepository
                        .findById(cropId)
                        .orElseThrow(() -> new ResourceNotFoundException("Crop record not found for id: " + cropId));
        cropRepository.delete(crop);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    } // DELETECROP(@PATHVARIABLE)

    @GetMapping("/")
    public ResponseEntity<String> getHome() throws Exception{
        return ResponseEntity.ok("We are at home page.");
    } // GETHOME()

    @GetMapping("/error")
    public ResponseEntity<String> getError() throws Exception{
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("500 Internal server error.");
    } // GETERROR()

} // CLASS