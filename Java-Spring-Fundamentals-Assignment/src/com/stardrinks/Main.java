package com.stardrinks;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args)
    {
    	ApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);
    	ShopUI storefront = appContext.getBean(ShopUI.class);
        storefront.start();
//      appContext.close(); Use AnnotationConfigApplicationContext (extends Closable) instead of ApplicationContext
    }
}