package com.example.demo.controller;

import com.example.demo.model.VendorDocument;
import com.example.demo.service.VendorDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendor-documents")
public class VendorDocumentController {

    @Autowired
    private VendorDocumentService vendorDocumentService;

    @PostMapping
    public ResponseEntity<VendorDocument> uploadDocument(@RequestBody VendorDocument document) {
        VendorDocument uploaded = vendorDocumentService.uploadDocument(document);
        return ResponseEntity.ok(uploaded);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorDocument> getDocument(@PathVariable Long id) {
        VendorDocument document = vendorDocumentService.getDocumentById(id);
        return ResponseEntity.ok(document);
    }
}