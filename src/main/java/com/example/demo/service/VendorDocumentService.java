package com.example.demo.service;

import com.example.demo.model.VendorDocument;

import java.util.List;

public interface VendorDocumentService {
    VendorDocument upload(VendorDocument document);
    VendorDocument getById(Long id);
    List<VendorDocument> getAll();
}
