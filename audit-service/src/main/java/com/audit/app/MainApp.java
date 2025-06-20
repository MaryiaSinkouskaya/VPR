package com.audit.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class MainApp {
  public static void main(String[] args) {
    SpringApplication.run(MainApp.class, args);
  }
}
