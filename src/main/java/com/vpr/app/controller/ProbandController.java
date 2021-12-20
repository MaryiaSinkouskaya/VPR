package com.vpr.app.controller;

import com.vpr.app.entity.Address;
import com.vpr.app.entity.Proband;
import com.vpr.app.service.ProbandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/proband")
public class ProbandController {
    private final ProbandService probandService;

    @RequestMapping(value = "/{id}", method = GET)
    public Proband getProbandById(@PathVariable(name = "id") long id) {
        return probandService.findById(id);
    }

    @RequestMapping(method = GET)
    public List<Proband> getProbands() {
        return probandService.findAll();
    }

    @RequestMapping(method = POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Proband createProband(@RequestBody Proband proband) {
        return probandService.create(proband);
    }

    @RequestMapping(method = PUT)
    public Proband updateProband(@RequestBody Proband proband) {
        return probandService.update(proband);
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    public void deleteProbandById(@PathVariable(name = "id") long id) {
        probandService.delete(id);
    }

}
