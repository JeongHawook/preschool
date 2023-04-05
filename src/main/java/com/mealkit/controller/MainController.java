package com.mealkit.controller;

import com.mealkit.domain.DTO.MainDto;
import com.mealkit.repository.AdminPostRepository;
import com.mealkit.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.json.JSONObject;
import org.json.JSONException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Base64;
import java.util.List;

@Slf4j
@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminPostRepository adminPostRepository;


    @GetMapping("/")
    public String root(){
        return "index";
    }

    @GetMapping("/signup")
    public String signup(){
        return "signUp";
    }

    @GetMapping("/news")
    public String news(){
        return "reMains/news";
    }
    @GetMapping("/counsel")
    public String counsel(){
        return "reMains/counsel";
    }
    //여기서부터 아이디 관련 Controller 새로 만들것.
    @GetMapping("/findUser")
    public String find(){
        return "Find/findUser";
    }

    @GetMapping("/findPw")
        public String findPw(){
            return "Find/findPw";
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public String check(
            @RequestParam(value = "accessToken")String accessToken, ModelMap map) throws JSONException {
      //  ModelMap map = new ModelMap();

        String[] parts = accessToken.split("\\.");
        JSONObject header = new JSONObject(decode(parts[0]));
        JSONObject payload = new JSONObject(decode(parts[1]));
        String signature = decode(parts[2]);
        log.info("check" + header + payload + signature);
        String userName = payload.getString("sub");
        String userId= payload.getString("id");
        System.out.println(userName);
        if(userRepository.existsByUserName(userName)) {
            map.addAttribute("userName", userName);
            map.addAttribute("userId", userId);
        }

        return "/header :: #loginStatus";
    }
    private static String decode(String encodedString) {
        return new String(Base64.getUrlDecoder().decode(encodedString));
    }

@GetMapping("/main")
    public String mainPage(ModelMap modelMap) {

List<MainDto> adminPost =userRepository.adminResult();
    List<MainDto> userPost =userRepository.userResult();

    modelMap.addAttribute("adminPost", adminPost);
    modelMap.addAttribute("userPost", userPost);

    System.out.println("가지고 왔나 확인 : " + adminPost);

    return "reMains/main";

}




}


