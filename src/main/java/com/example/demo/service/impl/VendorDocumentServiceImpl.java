package com.example.demo.service.impl;

import com.example.demo.model.VendorDocument;
import com.example.demo.model.Vendor;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ValidationException;
import com.example.demo.model.DocumentType;
import com.example.demo.repository.VendorDocumentRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.repository.DocumentTypeRepository;
import com.example.demo.service.VendorDocumentService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class VendorDocumentServiceImpl implements VendorDocumentService {

    private final VendorDocumentRepository repo;

    public VendorDocumentServiceImpl(VendorDocumentRepository repo) {
        this.repo = repo;
    }

    @Override
    public VendorDocument upload(VendorDocument doc) {
        if (doc.getExpiryDate().isBefore(LocalDate.now())) {
            throw new ValidationException("Document expired");
        }
        return repo.save(doc);
    }

    @Override
    public List<VendorDocument> findExpired() {
        return repo.findByExpiryDateBefore(LocalDate.now());
    }
}
