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

    public Emploi getInfoEmploiByNumero(int numero) throws IOException {
        return Emplois.getInfoEmploiByNumero(numero);
    }
}