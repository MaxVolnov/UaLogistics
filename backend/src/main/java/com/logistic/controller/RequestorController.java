package com.logistic.controller;

import com.logistic.model.Requestor;
import com.logistic.service.RequestorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@Controller
@RequestMapping("/requestors")
public class RequestorController {
    private static final Logger logger = LogManager.getLogger(RequestorController.class);

    private final RequestorService requestorService;

    @Autowired
    public RequestorController(RequestorService requestorService) {
        this.requestorService = requestorService;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void createRequestor(@RequestBody Requestor requestor) {
        requestorService.saveRequestor(requestor);
    }

    @GetMapping("/")
    public String getRequestors(Model model) {
        logger.info("Getting all requestors...");
        List<Requestor> requestors = requestorService.getAllRequestors();
        logger.info("Found {} requestors.", requestors.size());
        model.addAttribute("requestors", requestors);
        return "requestor/requestor-list";
    }


    @GetMapping("/{id}")
    public String getRequestorById(@PathVariable String id, Model model) {
        try {
            Long requestId = Long.parseLong(id);
            Requestor requestor = requestorService.getRequestorById(requestId);
            model.addAttribute("requestor", requestor);
            return "requestor";
        } catch (NumberFormatException ex) {
            logger.warn("Invalid requestor id: {}", id);
            return "redirect:/requestors/";
        }
    }
    @GetMapping("/new")
    public String showNewRequestorForm(Model model) {
        model.addAttribute("requestor", new Requestor());
        return "requestor/new-requestor-form";
    }

    @PostMapping("/new")
    public String addNewRequestor(@ModelAttribute("requestor") Requestor requestor) {
        requestorService.saveRequestor(requestor);
        return "redirect:/requestors/";
    }

    @GetMapping("/edit/{id}")
    public String showEditRequestorForm(@PathVariable String id, Model model) {
        try {
            Long requestId = Long.parseLong(id);
            Requestor requestor = requestorService.getRequestorById(requestId);
            if (requestor == null) {
                logger.warn("Requestor not found with id: {}", id);
                return "redirect:/requestors/";
            }
            model.addAttribute("requestor", requestor);
            return "requestor/edit-requestor-form";
        } catch (NumberFormatException ex) {
            logger.warn("Invalid requestor id: {}", id);
            return "redirect:/requestors/";
        } catch (Exception e) {
            logger.error("Error occurred while trying to get requestor by id: {}", id, e);
            model.addAttribute("error", "An error occurred while trying to get requestor with id " + id);
            return "error";
        }
    }


    @PostMapping("/edit/{id}")
    public String editRequestor(@PathVariable Long id, @ModelAttribute("requestor") Requestor requestor) {
        Requestor existingRequestor = requestorService.getRequestorById(id);
        if (existingRequestor != null) {
            requestor.setId(id);
            requestorService.saveRequestor(requestor);
        }
        return "redirect:/requestors/";
    }


    @GetMapping("/delete/{id}")
    public String deleteRequestor(@PathVariable Long id) {
        Requestor existingRequestor = requestorService.getRequestorById(id);
        if (existingRequestor != null) {
            requestorService.deleteRequestor(existingRequestor);
        }
        return "redirect:/requestors/";
    }



}