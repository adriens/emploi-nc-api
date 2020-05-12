package com.github.adriens.emploi.nc.api.controller;

import com.github.adriens.emploi.nc.api.service.EmploiService;
import com.github.adriens.emploi.nc.api.service.EmployeurService;
import com.github.adriens.emploi.nc.api.service.StatService;
import com.github.adriens.emploi.nc.sdk.Emploi;
import com.github.adriens.emploi.nc.sdk.Employeur;
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
import org.springframework.web.servlet.view.RedirectView;


import java.io.IOException;
import java.util.ArrayList;

@RestController
public class EmploiNCController {
    @Autowired
    private EmploiService emploiNCService;
    @Autowired
    private EmployeurService employeurService;

    private final Logger log = LoggerFactory.getLogger(EmploiNCController.class);

    @GetMapping("/")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public RedirectView getDefault() throws Exception {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("https://spring.io/projects/spring-restdocs");
        return redirectView;
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

    @GetMapping("/employeurs")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ArrayList<Employeur> getInfoALLEmployeur() throws Exception {
        try{
            return employeurService.getAllEmployeurs();
        }
        catch(IOException ex){
            log.error("Impossible de récupérer les employeurs."+ex);
            throw ex;
        }
    }


    @GetMapping("/employeurs/{name}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public Employeur getInfoEmployeurByName(@PathVariable String name) throws Exception {
        try{
            return employeurService.getInfoEmployeurByName(name);
        }
        catch(IOException ex){
            log.error("Impossible de récupérer les stats."+ex);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
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

    @GetMapping("/emploi/previous/{nb}/{numeroOffre}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ArrayList<Emploi> getPreviousXOffers(@PathVariable Integer nb,@PathVariable Integer numeroOffre) throws Exception {
        try{
            return emploiNCService.getPreviousXOffers(numeroOffre,nb);
        }
        catch(IOException ex){
            log.error("Impossible de récupérer les offres précédentes."+ex);
            throw ex;
        }
    }

    @GetMapping("/emploi/next/{nb}/{numeroOffre}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ArrayList<Emploi> getNextXOffers(@PathVariable Integer nb,@PathVariable Integer numeroOffre) throws Exception {
        try{
            return emploiNCService.getNextXOffers(numeroOffre,nb);
        }
        catch(IOException ex){
            log.error("Impossible de récupérer les offres suivantes."+ex);
            throw ex;
        }
    }
    @GetMapping("/search/{nombreMaxOffres}/{MotsClès}/{commune}/{contrat}/{dateDebut}/{dateFin}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ArrayList<Emploi> getsearchs(@PathVariable String nombreMaxOffres,@PathVariable String MotsClès,
                             @PathVariable String commune,@PathVariable String contrat,
                             @PathVariable String dateDebut,@PathVariable String dateFin) throws Exception {
        try{
            return emploiNCService.getSearchInfoEmploi(nombreMaxOffres,MotsClès,commune,contrat,dateDebut,dateFin);
        }
        catch(IOException ex){
            log.error("Impossible de récupérer la Recherche avec comme paramètre : nombreMaxOffres<"+nombreMaxOffres+">."+"MotsClès<"+MotsClès+">"+"commune<"+commune+">"+"contrat<"+contrat+">"
                    +"dateDebut<"+dateDebut+">"+"dateFin<"+dateFin+">"+ex);
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
