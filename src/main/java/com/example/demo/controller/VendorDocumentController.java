package com.example.demo.controller;

import com.example.demo.model.VendorDocument;
import com.example.demo.service.VendorDocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendor-documents")
public class VendorDocumentController {

    private final VendorDocumentService vendorDocumentService;

    public VendorDocumentController(VendorDocumentService vendorDocumentService) {
        this.vendorDocumentService = vendorDocumentService;
    }

    @PostMapping
    public ResponseEntity<VendorDocument> upload(@RequestBody VendorDocument document) {
        return ResponseEntity.ok(vendorDocumentService.upload(document));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorDocument> get(@PathVariable Long id) {
        return ResponseEntity.ok(vendorDocumentService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<VendorDocument>> getAll() {
        return ResponseEntity.ok(vendorDocumentService.getAll());
    }
}
