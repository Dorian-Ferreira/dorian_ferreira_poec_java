package org.dorianferreira;

import org.dorianferreira.entity.digimon.Digimon;
import org.dorianferreira.service.DigimonApiService;
import org.dorianferreira.service.Dump;

public class MainDigimon {
    public static void main(String[] args) {
        DigimonApiService apiService = new DigimonApiService();

        System.out.println("Tous les digimons : ");
        for (Digimon d : apiService.getDigimons()) {
            Dump.dump(d);
        }
    }
}