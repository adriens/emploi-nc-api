package com.github.adriens.emploi.nc.api.service;

import com.github.adriens.emploi.nc.sdk.Emploi;
import com.github.adriens.emploi.nc.sdk.Emplois;
import com.github.adriens.emploi.nc.sdk.Employeur;
import com.github.adriens.emploi.nc.sdk.Employeurs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class EmployeurService {

    private final Logger log = LoggerFactory.getLogger(EmploiService.class);
    public static final int MAX_EMPLOYEUR = 200;

    public Employeur getInfoEmployeurByNumEmploi(Integer numero) throws IOException {
        return Emplois.getInfoEmployeurByNumEmploi(numero);
    }

    public Employeur getInfoEmployeurById(String id) throws IOException {
        return Employeurs.getInfoEmployeurById(id);
    }
}
