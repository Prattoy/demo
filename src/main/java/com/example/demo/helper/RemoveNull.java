package com.example.demo.helper;

import org.springframework.stereotype.Component;

@Component
public class RemoveNull {
    public String nullRemove(String a){
        if(a == null){
            a = "";
        }else if(a == "null"){
            a = "";
        }

        return a;
    }
}
