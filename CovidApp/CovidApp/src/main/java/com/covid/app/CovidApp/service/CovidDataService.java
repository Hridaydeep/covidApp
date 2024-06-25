package com.covid.app.CovidApp.service;

import com.covid.app.CovidApp.utility.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class CovidDataService {

    @Autowired
    RestTemplate restTemplate;

    public Integer getCovidData(String cityName){
        Map<String, String> stateCityMap= new HashMap<>();
        stateCityMap.put("Agra","Uttar Pradesh");
        stateCityMap.put("Rampur","Uttar Pradesh");
        stateCityMap.put("Noida","Uttar Pradesh");
        stateCityMap.put("Hapur","Uttar Pradesh");
        stateCityMap.put("Dhemaji","Assam");
        stateCityMap.put("Kamrup Metropolitan","Assam");
        stateCityMap.put("Golaghat","Assam");

        Map<String, Object> map = restTemplate.getForObject(Util.COVID_DATA_API, Map.class);//link and data type it want to be received

        Map<String, Object> cityObjectMap = (Map<String, Object>) map.get(stateCityMap.get(cityName));
        Map<String, Object> districtData = (Map<String, Object>) cityObjectMap.get("districtData");
        Map<String, Object> city = (Map<String, Object>) districtData.get(cityName);
        Integer active= (Integer) city.get("active");

        return active;
    }
}
