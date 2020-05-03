package com.dbmi.seeds.controller;

import com.dbmi.seeds.model.Crop;
import com.dbmi.seeds.model.CropRepository;

import com.dbmi.seeds.model.CropRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/seedinspection")
public class CropController {
    @Autowired
    private CropRepository cropRepository;

    CropController(CropRepository cropRepository) {
        this.cropRepository = cropRepository;
    }

    /**
     * Get all crops list.
     *
     * @return the list
     */
    @GetMapping("/crops")
    public List<Crop> getAllCrops() {
        return cropRepository.findAll();
    } // GETALLCROPS()

    /**
     * Gets crops by id.
     *
     * @param cropId the crop id
     * @return the crops by id
     * @throws ResourceNotFoundException the resource not found exception
     */
    @GetMapping("/crops/{cropId}")
    public ResponseEntity<Crop> getCropsById(@PathVariable(value = "cropId") Long cropId)
            throws ResourceNotFoundException {
        Crop crop =
                cropRepository
                        .findById(cropId)
                        .orElseThrow(() -> new ResourceNotFoundException("Crop not found on :: " + cropId));
        return ResponseEntity.ok().body(crop);
    } // GETCROPSBYID(LONG)

    /**
     * Create crop crop.
     *
     * @param crop the crop
     * @return the crop
     */
    @PostMapping("/crops/new")
    public Crop createCrop(@Valid @RequestBody Crop crop) {
        return cropRepository.save(crop);
    } // CREATECROP(crop)

    /**
     * Update crop response entity.
     *
     * @param cropId the crop id
     * @param cropDetails the crop details
     * @return the response entity
     * @throws ResourceNotFoundException the resource not found exception
     */
    @PutMapping("/crops/{id}")
    public ResponseEntity<Crop> updateCrop(
            @PathVariable(value = "id") Long cropId, @Valid @RequestBody Crop cropDetails)
            throws ResourceNotFoundException {
        Crop crop =
                cropRepository
                        .findById(cropId)
                        .orElseThrow(() -> new ResourceNotFoundException("Crop not found on :: " + cropId));
        crop.setCropName(cropDetails.getCropName());
        crop.setCropDescription(cropDetails.getCropDescription());
        crop.setCropId(cropDetails.getCropId());
        final Crop updatedCrop = cropRepository.save(crop);
        return ResponseEntity.ok(updatedCrop);
    } // UPDATECROP(@PATHVARIABLE)

    /**
     * Delete crop map.
     *
     * @param cropId the crop id
     * @return the map
     * @throws Exception the exception
     */
    @DeleteMapping("/crops/delete/{id}")
    public Map<String, Boolean> deleteCrop(@PathVariable(value = "id") Long cropId) throws Exception {
        Crop crop =
                cropRepository
                        .findById(cropId)
                        .orElseThrow(() -> new ResourceNotFoundException("Crop not found on :: " + cropId));
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
    } // GETHOME()

} // CLASS