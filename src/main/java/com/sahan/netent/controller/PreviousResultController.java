package com.sahan.netent.controller;

import com.sahan.netent.model.PreviousResult;
import com.sahan.netent.service.PreviousResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/resultController")
public class PreviousResultController {

    private final String APPLICATION_JSON = "application/json";
    private final PreviousResultService previousResultService;

    @Autowired
    public PreviousResultController(PreviousResultService previousResultService) {
        this.previousResultService = previousResultService;
    }

    @RequestMapping(value = "/result", method = RequestMethod.GET, produces = {APPLICATION_JSON})
    public PreviousResult findPreviousResult(@RequestParam("uniqueId") int id) {
        return previousResultService.findPreviousResultByUniqueId(id);
    }
}
