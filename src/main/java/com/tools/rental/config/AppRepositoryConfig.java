package com.tools.rental.config;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.tools.rental.admin.tool.ToolDetailsBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class AppRepositoryConfig {
    private static final Type REVIEW_TYPE = new TypeToken<List<ToolDetailsBean>>() {
    }.getType();

    @Bean
    public Map<String, ToolDetailsBean> toolDetailsBeansMap() {

        Map<String, ToolDetailsBean> mapToolDetails = new HashMap<>();

        Gson gson = new Gson();
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader("src/main/resources/ToolRental_DB.json"));

            List<ToolDetailsBean> data = gson.fromJson(reader, REVIEW_TYPE); // contains the whole reviews list
            if (data != null) {
                for (ToolDetailsBean bean : data) {
                    System.out.println("Tool Code: " + bean.getToolCode());
                    mapToolDetails.put(String.valueOf(bean.getToolCode()), bean);
                }

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return mapToolDetails;
    }

}
