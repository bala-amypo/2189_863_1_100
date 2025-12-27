package com.example.demo.controller;

import com.example.demo.model.VendorDocument;
import com.example.demo.service.VendorDocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/vendor-documents")
public class VendorDocumentController {
    
    private final VendorDocumentService vendorDocumentService;
    
    public VendorDocumentController(VendorDocumentService vendorDocumentService) {
        this.vendorDocumentService = vendorDocumentService;
    }
    
    @PostMapping("/vendor/{vendorId}/type/{typeId}")
    public ResponseEntity<VendorDocument> uploadDocument(
            @PathVariable Long vendorId,
            @PathVariable Long typeId,
            @RequestBody VendorDocument document) {
        VendorDocument uploaded = vendorDocumentService.uploadDocument(vendorId, typeId, document);
        return ResponseEntity.ok(uploaded);
    }
    
    @GetMapping("/vendor/{vendorId}")
    public ResponseEntity<List<VendorDocument>> getVendorDocuments(@PathVariable Long vendorId) {
        List<VendorDocument> documents = vendorDocumentService.getDocumentsForVendor(vendorId);
        return ResponseEntity.ok(documents);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<VendorDocument> getDocument(@PathVariable Long id) {
        VendorDocument document = vendorDocumentService.getDocument(id);
        return ResponseEntity.ok(document);
    }
}
