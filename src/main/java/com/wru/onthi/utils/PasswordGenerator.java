package com.wru.onthi.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
        String pass= encoder.encode("123456");
        System.out.println(pass );

//        Date date= new Date();
//        long time= date.getTime();
//        System.out.println("time stamp"+time);

//        long start = System.currentTimeMillis();
//        int n = 100000000;
//        int[] arr = new int[n+1];
//        Arrays.fill(arr, 1);
//        arr[0] = 0;
//        arr[1] = 0;


//        int result=0;
//        for(int i = 2; i<=n; i++){
//            if (arr[i] == 1){
//                for (int j=2*i; j<=n; j+=i){
//                    arr[j]=0;
//                }
//                result++;
//            }
//        }

//
//        Set<Integer> set = new HashSet<>();
//        set.add(2);
//        int q = n;
//        for (int i=3; i<=q; i++){
//
//            boolean mark = true;
//
//            for (int j : set){
//                if (i%j==0){
//                    mark = false;
//                    break;
//                }
//            }
//            if (mark)
//                set.add(i);
//        }


//        System.out.println("result: " + result +  " - time:" + (System.currentTimeMillis()-start));

    }
}
