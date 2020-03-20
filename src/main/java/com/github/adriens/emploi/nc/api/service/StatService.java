package com.github.adriens.emploi.nc.api.service;

import com.github.adriens.emploi.nc.sdk.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class StatService {

    private final Logger log = LoggerFactory.getLogger(StatService.class);
    public static Stat getStats() throws IOException {
        return Stat.getStats();
    }
}
