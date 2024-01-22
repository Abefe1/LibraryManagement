package com.example.LibraryManagement.appUtils;

import java.util.Random;

public class AppUtils {

    public static String otpGenerator(){

        StringBuilder otp= new StringBuilder();
        Random random=new Random();
        int counter=0;
        while (counter<6){
            otp.append(random.nextInt(10));
            counter++;
        }
        return otp.toString();
    }

    public static void main(String[] args) {
        System.out.println(otpGenerator());
    }
}
