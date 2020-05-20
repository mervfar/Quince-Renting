package com.quince.rentingapp.service;

import com.google.api.client.util.Lists;
import com.quince.rentingapp.configuration.Avis.ApiSecret;
import com.quince.rentingapp.configuration.Avis.ApiToken;
import com.quince.rentingapp.domain.car.CarViewDTO;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.List;

import static java.lang.Integer.parseInt;

@Service
@RequiredArgsConstructor
public class AvisService {
    final ApiSecret secret =new ApiSecret();
    final String BASE_URL="https://stage.abgapiservices.com";
    final String TOKEN_ENDPOINT="/oauth/token/v1";
    final String LOCATION_ENDPOINT="/cars/locations/v1";
    final String AVAILABLE_CAR_ENDPOINT="/cars/catalog/v1/vehicles";
    ApiToken apiToken=new ApiToken();

    private void getToken() throws IOException, JSONException {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("client_id", secret.getClient_id());
        headers.add("client_secret", secret.getClient_secret());
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(BASE_URL+TOKEN_ENDPOINT);
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<ApiToken> responseEntity = rest.exchange(builder.toUriString(), HttpMethod.GET, requestEntity, ApiToken.class);
        HttpStatus status = responseEntity.getStatusCode();
        System.out.println("Response status: " + status);
        apiToken= responseEntity.getBody();
        apiToken.setTimeOnSet(System.currentTimeMillis() / 1000L);
        System.out.println(apiToken);
    }

    public String getLoc(String keyword) throws JSONException, IOException {
        checkToken();
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("client_id", secret.getClient_id());
        headers.add("Authorization", apiToken.getBearer());
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(BASE_URL+LOCATION_ENDPOINT)
                .queryParam("keyword", keyword);
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<String> responseEntity = rest.exchange(builder.toUriString(), HttpMethod.GET, requestEntity, String.class);
        HttpStatus status = responseEntity.getStatusCode();
        String response = responseEntity.getBody();
        System.out.println("Response status: " + status);
        JSONObject jsonObject = new JSONObject(response);
        JSONArray locations=jsonObject.getJSONArray("locations");
        return locations.toString();
    }
    public List<CarViewDTO> getAvailableCars(
            String brand,String pickup_location,
            String pickup_date,String dropoff_date,
            String dropoff_location,String country_code ) throws IOException, JSONException {
        checkToken();
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("client_id", secret.getClient_id());
        headers.add("Authorization", apiToken.getBearer());
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(BASE_URL+AVAILABLE_CAR_ENDPOINT)
                .queryParam("brand", brand)
                .queryParam("pickup_location", pickup_location)
                .queryParam("pickup_date", pickup_date)
                .queryParam("dropoff_date", dropoff_date)
                .queryParam("dropoff_location", dropoff_location)
                .queryParam("country_code", country_code);
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<String> responseEntity = rest.exchange(builder.toUriString(), HttpMethod.GET, requestEntity, String.class);
        HttpStatus status = responseEntity.getStatusCode();
        String response = responseEntity.getBody();
        System.out.println("Response status: " + status);
        JSONObject jsonObject = new JSONObject(response);
        JSONArray vehicles =jsonObject.getJSONArray("vehicles");
        List<CarViewDTO> carViewDTOList=Lists.newArrayList();
        for(int i=0;i<vehicles.length();i++){
            CarViewDTO tempCar=new CarViewDTO();
            tempCar.setCarBody(vehicles.getJSONObject(i).getJSONObject("capacity").getString("seats")+" Kişilik");
            tempCar.setName(vehicles.getJSONObject(i).getJSONObject("category").getString("name"));
            tempCar.setBrand(vehicles.getJSONObject(i).getJSONObject("category").getString("make"));
            tempCar.setSeries(vehicles.getJSONObject(i).getJSONObject("category").getString("model"));
            tempCar.setImageUrl(vehicles.getJSONObject(i).getJSONObject("category").getString("image_url"));
            tempCar.setGear(vehicles.getJSONObject(i).getJSONObject("category").getString("vehicle_transmission"));
            tempCar.setPrice(vehicles.getJSONObject(i).getJSONObject("rate_totals").getJSONObject("pay_later").getString("reservation_total")+" "+
                    vehicles.getJSONObject(i).getJSONObject("rate_totals").getJSONObject("rate").getString("currency")  );
            carViewDTOList.add(tempCar);
        }
        return carViewDTOList;
    }

    private void checkToken() throws IOException, JSONException {
        if (apiToken.getAccess_token()==null){
            getToken();
        }else if (apiToken.isExpired()){
            getToken();
        }
    }
}
