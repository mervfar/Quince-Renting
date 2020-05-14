package com.quince.rentingapp.configuration.DeploymentPipeline;

import com.quince.rentingapp.service.CurrentUserService;
import com.sun.xml.ws.server.StatefulInstanceResolver;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;



@RestController
@RequiredArgsConstructor
@RequestMapping("/deployment")
public class DeploymentListener {

    @PostMapping
    public void tryDeployNewBuild(@RequestParam(name="secret") String deploymentToken) throws IOException, JSONException {
        System.out.println("TOKENİMİZ>>>"+deploymentToken);
        if (checkToken(deploymentToken)){
            runPipeLine();
        }
    }
    private boolean checkToken(String hash) throws IOException, JSONException {
            String filename = "./deploymentSecret.json";
            JSONObject jsonObject = parseJSONFile(filename);
           return hash.equalsIgnoreCase(jsonObject.getString("tokenHash256"));
    }
    private JSONObject parseJSONFile(String filename) throws JSONException, IOException {
        String content = new String(Files.readAllBytes(Paths.get(filename)));
        return new JSONObject(content);
    }
    private void runPipeLine() throws IOException {
        String[] cmd = { "sh", "/root/deneme.sh"};
        Runtime.getRuntime().exec(cmd);
    }
}
