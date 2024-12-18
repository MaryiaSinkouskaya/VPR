package com.vpr.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class VPRApp {
  public static void main(String[] args) {
    SpringApplication.run(VPRApp.class, args);
  }
}
