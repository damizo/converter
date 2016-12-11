package com.app.aspect;


import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by Damian on 2016-11-19.
 */
@Aspect
@Component
public class ImageAspect {

    private Logger log = Logger.getLogger(getClass());

    @Before("execution(* com.app.controller.ImageController.convertImage(..))")
    public void beforeConvert(JoinPoint joinPoint) {
        log.info("Converting has been started!");
    }

    @After("execution(* com.app.controller.ImageController.convertImage(..))")
    public void afterConvert(JoinPoint joinPoint) {
        log.info("File has been converted!");
    }

    @After("execution(* com.app.controller.ImageController.deleteAll())")
    public void afterDelete(JoinPoint joinPoint) {
        log.info("All images has been deleted");
    }

}