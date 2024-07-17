package com.logistic.service;

import com.logistic.model.Requestor;
import com.logistic.repository.RequestorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestorService {

    private final RequestorRepository requestorRepository;

    @Autowired
    public RequestorService(RequestorRepository requestorRepository) {
        this.requestorRepository = requestorRepository;
    }

    public void saveRequestor(Requestor requestor) {
        requestorRepository.save(requestor);
    }

    public Requestor getRequestorById(Long id) {
        return requestorRepository.findById(id).orElse(null);
    }

    public void deleteRequestor(Requestor requestor) {
        requestorRepository.delete(requestor);
    }

    public List<Requestor> getAllRequestors() {
        return requestorRepository.findAll();
    }
}
