package com.app.controller;

import com.app.model.dto.ImageDTO;
import com.app.model.entity.Image;
import com.app.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Damian on 2016-11-19.
 */
@CrossOrigin
@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody Image convertImage(@RequestBody Image image) throws IOException {
        return imageService.saveAndGetToken(image);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteAll(){
        imageService.deleteAll();
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Image> getAll(){
       return imageService.findAll();
    }

    @RequestMapping(value = "/{token}", method = RequestMethod.GET)
    public Image getByToken(@PathVariable(name = "token") String token){
        return imageService.findByToken(token);
    }

    @RequestMapping(value = "/get_converted_file/{token}", method = RequestMethod.GET)
    public @ResponseBody HttpEntity<byte[]> getConvertedImage(@PathVariable String token) throws IOException {
       return imageService.findByTokenAndGetConvertedFile(token);
    }
}
