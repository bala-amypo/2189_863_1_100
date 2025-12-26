package com.example.demo.controller;

import com.example.demo.model.DocumentType;
import com.example.demo.service.DocumentTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/document-types")
public class DocumentTypeController {

    private final DocumentTypeService documentTypeService;

    public DocumentTypeController(DocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }

    @PostMapping
    public ResponseEntity<DocumentType> create(@RequestBody DocumentType type) {
        return ResponseEntity.ok(documentTypeService.create(type));
    }

    @GetMapping
    public ResponseEntity<List<DocumentType>> getAll() {
        return ResponseEntity.ok(documentTypeService.getAll());
    }
}
