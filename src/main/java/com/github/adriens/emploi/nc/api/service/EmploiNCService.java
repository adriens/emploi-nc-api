package com.github.adriens.emploi.nc.api.service;

import com.github.adriens.emploi.nc.sdk.Emploi;
import com.github.adriens.emploi.nc.sdk.Emplois;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class EmploiNCService {

    private final Logger log = LoggerFactory.getLogger(EmploiNCService.class);
    public static final int DEFAULT_LATEST = 10;
    public static final int MAX_LATEST = 100;

    public ArrayList<Emploi> getLatestEmploi(int numberLatest) throws IOException {
        return Emplois.getLatestEmploi(numberLatest);
    }
}
