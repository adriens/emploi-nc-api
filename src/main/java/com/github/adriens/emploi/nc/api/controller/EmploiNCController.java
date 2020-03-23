package com.github.adriens.emploi.nc.api.controller;

import com.github.adriens.emploi.nc.api.service.EmploiService;
import com.github.adriens.emploi.nc.api.service.EmployeurService;
import com.github.adriens.emploi.nc.api.service.StatService;
import com.github.adriens.emploi.nc.sdk.Emploi;
import com.github.adriens.emploi.nc.sdk.Employeur;
import com.github.adriens.emploi.nc.sdk.Employeurs;
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
    private EmploiService emploiNCService;
    @Autowired
    private EmployeurService employeurService;

    private final Logger log = LoggerFactory.getLogger(EmploiNCController.class);

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

    @GetMapping("/employeurs/{id}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public Employeur getStats(@PathVariable String id) throws Exception {
        try{
            return employeurService.getInfoEmployeurById(id);
        }
        catch(IOException ex){
            log.error("Impossible de récupérer les stats."+ex);
            throw ex;
        }
    }

    @GetMapping("/emploi/{numero}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public Emploi getInfoEmploiByNumero(@PathVariable Integer numero) throws Exception {
        try{
            return emploiNCService.getInfoEmploiByNumero(numero);
        }
        catch(IOException ex){
            log.error("Impossible de récupérer l'offre <"+numero+">."+ex);
            throw ex;
        }
    }

    @GetMapping("/emploi/{numero}/employeur")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public Employeur getInfoEmployeurByNumEmploi(@PathVariable Integer numero) throws Exception {
        try{
            return employeurService.getInfoEmployeurByNumEmploi(numero);
        }
        catch(IOException ex){
            log.error("Impossible de récupérer l'employeur de cette l'offre <"+numero+">."+ex);
            throw ex;
        }
    }

    @GetMapping("/emploi/latest/{number}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ArrayList<Emploi> getLatestEmploi(@PathVariable Integer number) throws Exception {
        try{
            if ( number < EmploiService.MAX_LATEST ) {
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
            return emploiNCService.getLatestEmploi(EmploiService.DEFAULT_LATEST);
        }
        catch(IOException ex){
            log.error("Impossible de récupérer les derniers emplois."+ex);
            throw ex;
        }
    }


}
