package com.logistic.service;

import com.logistic.model.Request;
import com.logistic.model.RequestStatus;
import com.logistic.model.User;
import com.logistic.repository.RequestRepository;
import com.logistic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    private final RequestRepository requestRepository;
    private final UserRepository userRepository;

    @Autowired
    public RequestService(RequestRepository requestRepository, UserRepository userRepository) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
    }

    public Request createRequest(Request request, Long requestorId) {
        User requestor = userRepository.findById(requestorId)
                .orElseThrow(() -> new RuntimeException("Requestor not found"));
        request.setRequestor(requestor);
        request.setStatus(RequestStatus.NEW);
        request.setCreatedAt(LocalDateTime.now());
        return requestRepository.save(request);
    }

    public Request updateRequestStatus(Long requestId, RequestStatus newStatus) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        request.setStatus(newStatus);
        return requestRepository.save(request);
    }

    public Request assignRequest(Long requestId, Long assigneeId) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        User assignee = userRepository.findById(assigneeId)
                .orElseThrow(() -> new RuntimeException("Assignee not found"));
        request.setAssignedTo(assignee);
        return requestRepository.save(request);
    }

    public List<Request> getRequestsByStatus(RequestStatus status) {
        return requestRepository.findByStatus(status);
    }

    public List<Request> getRequestsByRequestor(Long requestorId) {
        return requestRepository.findByRequestorId(requestorId);
    }

    public Optional<Request> getRequestById(Long requestId) {
        return requestRepository.findById(requestId);
    }
}