package com.mealkit.controller;

import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class newsController {

    @Autowired
    newsApi naver = new newsApi();

    @GetMapping("/newsApi")
    public ResponseEntity getNews(ModelMap modelMap) throws Exception {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(naver.news());
        JSONObject jsonObj = (JSONObject) obj;

        modelMap.addAttribute("jsonObj", jsonObj);
        System.out.println(jsonObj);

        return ResponseEntity.ok(jsonObj);
    }
}
