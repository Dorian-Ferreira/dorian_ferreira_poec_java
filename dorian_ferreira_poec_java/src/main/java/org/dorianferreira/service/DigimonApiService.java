package org.dorianferreira.service;

import org.dorianferreira.entity.digimon.Digimon;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;

public class DigimonApiService {
    private HttpClientService httpClientService = new HttpClientService();

    public List<Digimon> getDigimons() {
        String url = "https://digimon-api.vercel.app/api/digimon";
        String json = httpClientService.get(url);
        if (json.isEmpty()) {
            return null;
        }
        JSONTokener tokener = new JSONTokener(json);
        JSONArray object = new JSONArray(tokener);

        List<Digimon> digimons = new ArrayList<>();

        for (int i = 0; i < object.length(); i++) {
            JSONObject jDigimon = object.getJSONObject(i);

            Digimon digimon = new Digimon();
            digimon.setName(jDigimon.getString("name"));
            digimon.setImage(jDigimon.getString("img"));
            digimon.setLevel(jDigimon.getString("level"));
            digimons.add(digimon);
        }

        return digimons;
    }
}
