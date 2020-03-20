package com.github.adriens.emploi.nc.api.controller;

import com.github.adriens.emploi.nc.api.service.EmploiNCService;
import com.github.adriens.emploi.nc.api.service.StatService;
import com.github.adriens.emploi.nc.sdk.Emploi;
import com.github.adriens.emploi.nc.sdk.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import java.io.IOException;
import java.util.ArrayList;

@RestController
public class EmploiNCController {
    @Autowired
    private EmploiNCService emploiNCService;

    private final Logger log = LoggerFactory.getLogger(EmploiNCController.class);

    @GetMapping("/emploi/latest/{number}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ArrayList<Emploi> getLatestEmploi(@PathVariable Integer number) throws Exception {
        try{
            if ( number < EmploiNCService.MAX_LATEST ) {
                return emploiNCService.getLatestEmploi(number);
            }else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }
        catch(IOException ex){
            log.error("Impossible de récupérer les derniers emplois."+ex);
            throw ex;
        }
    }

    @GetMapping("/emploi/latest")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ArrayList<Emploi> getLatestEmploi() throws Exception {
        try{
            return emploiNCService.getLatestEmploi(EmploiNCService.DEFAULT_LATEST);
        }
        catch(IOException ex){
            log.error("Impossible de récupérer les derniers emplois."+ex);
            throw ex;
        }
    }

    @GetMapping("/stats")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public Stat getStats() throws Exception {
        try{
            return StatService.getStats();
        }
        catch(IOException ex){
            log.error("Impossible de récupérer les stats."+ex);
            throw ex;
        }
    }
}
