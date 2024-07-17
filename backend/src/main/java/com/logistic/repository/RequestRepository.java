package com.logistic.repository;

import com.logistic.model.Request;
import com.logistic.model.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByStatus(RequestStatus status);
    List<Request> findByRequestorId(Long requestorId);
    List<Request> findByAssignedToId(Long userId);
}