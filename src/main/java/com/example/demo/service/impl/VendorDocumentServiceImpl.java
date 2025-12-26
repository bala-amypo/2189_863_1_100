package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.VendorDocument;
import com.example.demo.repository.VendorDocumentRepository;
import com.example.demo.service.VendorDocumentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VendorDocumentServiceImpl implements VendorDocumentService {

    private final VendorDocumentRepository repository;

    public VendorDocumentServiceImpl(VendorDocumentRepository repository) {
        this.repository = repository;
    }

    @Override
    public VendorDocument upload(VendorDocument document) {
        if (document.getExpiryDate() != null &&
                document.getExpiryDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Document expired");
        }
        return repository.save(document);
    }

    @Override
    public VendorDocument getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found"));
    }

    @Override
    public List<VendorDocument> getAll() {
        return repository.findAll();
    }
}
