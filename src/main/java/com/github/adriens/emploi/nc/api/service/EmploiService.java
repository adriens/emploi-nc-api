package com.github.adriens.emploi.nc.api.service;

import com.github.adriens.emploi.nc.sdk.Emploi;
import com.github.adriens.emploi.nc.sdk.Emplois;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class EmploiService {

    private final Logger log = LoggerFactory.getLogger(EmploiService.class);
    public static final int DEFAULT_LATEST = 25;
    public static final int MAX_LATEST = 100;

    public ArrayList<Emploi> getLatestEmploi(int numberLatest) throws IOException {
        return Emplois.getLatestEmploi(numberLatest);
    }
    public ArrayList<Emploi> getNextXOffers(int numeroOffre,int XLast) throws IOException {
        return Emplois.getNextXOfferNumEmploi(numeroOffre,XLast);
    }
    public ArrayList<Emploi> getPreviousXOffers(int numeroOffre,int XLast) throws IOException {
        return Emplois.getPreviousXOfferNumEmploi(numeroOffre,XLast);
    }
    public ArrayList<Emploi> getSearchInfoEmploi(String nombreMaxOffres,String MotsClès,String commune,String contrat,String dateDebut,String dateFin) throws IOException {
        if ( MotsClès.equals("none") ) MotsClès = "";
        if ( commune.equals("none") ) commune = "";
        if ( contrat.equals("none") ) contrat = "";
        if ( dateDebut.equals("none") ) {
            dateDebut = "";
        }else{
            dateDebut = new StringBuilder(dateDebut).insert(2, "/").toString();
            dateDebut = new StringBuilder(dateDebut).insert(dateDebut.length()-4, "/").toString();
        }
        if ( dateFin.equals("none") ) {
            dateFin = "";
        }else{
            dateFin = new StringBuilder(dateFin).insert(2, "/").toString();
            dateFin = new StringBuilder(dateFin).insert(dateFin.length()-4, "/").toString();
        }


        return Emplois.getSearchInfoEmploi(nombreMaxOffres,MotsClès,commune,contrat,dateDebut,dateFin);
    }

    public Emploi getInfoEmploiByNumero(int numero) throws IOException {
        return Emplois.getInfoEmploiByNumero(numero);
    }
}