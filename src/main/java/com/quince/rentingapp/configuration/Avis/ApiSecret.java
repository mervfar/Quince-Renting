package com.quince.rentingapp.configuration.Avis;

import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class ApiSecret {
    public String getClient_id() throws IOException, JSONException {
        String filename = "./deploymentSecret.json";
        JSONObject jsonObject = parseJSONFile(filename);
        return jsonObject.getString("client_id");
    }
    public String getClient_secret() throws IOException, JSONException {
        String filename = "./deploymentSecret.json";
        JSONObject jsonObject = parseJSONFile(filename);
        return jsonObject.getString("client_secret");
    }
    private JSONObject parseJSONFile(String filename) throws JSONException, IOException {
        String content = new String(Files.readAllBytes(Paths.get(filename)));
        return new JSONObject(content);
    }
}
