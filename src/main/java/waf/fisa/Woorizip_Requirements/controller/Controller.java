package waf.fisa.Woorizip_Requirements.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import waf.fisa.Woorizip_Requirements.dto.RequirementReqDto;
import waf.fisa.Woorizip_Requirements.dto.RequirementRespDto;
import waf.fisa.Woorizip_Requirements.service.Service;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/requirement")
public class Controller {

    private Service service;

    @Autowired
    public Controller(Service service) {
        this.service = service;
    }

    @GetMapping("/isRegistered")
    public ResponseEntity<Boolean> isRegistered(@RequestParam String accountId) {
        log.info("Received request to check if accountId is registered: {}", accountId);
        Boolean isRegistered = service.isRegistered(accountId);
        return ResponseEntity.ok(isRegistered);
    }

    @PostMapping("/save")
    public ResponseEntity<RequirementRespDto> save(@RequestBody RequirementReqDto requirementReqDto) {
        log.info("Received request to save requirement: {}", requirementReqDto);
        RequirementRespDto saved = service.save(requirementReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/readMine")
    public ResponseEntity<RequirementRespDto> readMine(@RequestParam String accountId) {
        log.info("Received request to read requirement for accountId: {}", accountId);
        RequirementRespDto mine = service.readMine(accountId);
        return ResponseEntity.ok(mine);
    }

    @GetMapping("/readAll")
    public ResponseEntity<List<RequirementRespDto>> readAll() {
        log.info("Received request to read all requirements");
        List<RequirementRespDto> requirements = service.readAll();
        return ResponseEntity.ok(requirements);
    }

    @PostMapping("/readFilter")
    public ResponseEntity<List<RequirementRespDto>> readFilter(@RequestBody RequirementReqDto requirementReqDto) {
        log.info("Received request to filter requirements with: {}", requirementReqDto);
        List<RequirementRespDto> filtered = service.readFilter(requirementReqDto);
        return ResponseEntity.ok(filtered);
    }

    @PostMapping("/update")
    public ResponseEntity<RequirementRespDto> update(@RequestBody RequirementReqDto requirementReqDto) {
        log.info("Received request to update requirement: {}", requirementReqDto);
        RequirementRespDto updated = service.update(requirementReqDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> delete(@RequestParam String id) {
        log.info("Received request to delete requirement with id: {}", id);
        boolean deleted = service.delete(id);
        if (deleted) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
    }
}
