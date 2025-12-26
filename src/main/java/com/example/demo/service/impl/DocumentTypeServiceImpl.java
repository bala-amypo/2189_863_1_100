package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ValidationException;
import com.example.demo.model.DocumentType;
import com.example.demo.repository.DocumentTypeRepository;
import com.example.demo.service.DocumentTypeService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DocumentTypeServiceImpl implements DocumentTypeService {

    private final DocumentTypeRepository repo;

    public DocumentTypeServiceImpl(DocumentTypeRepository repo) {
        this.repo = repo;
    }

    @Override
    public DocumentType save(DocumentType type) {
        return repo.save(type);
    }

    @Override
    public List<DocumentType> findRequired() {
        return repo.findByRequiredTrue();
    }
}
