package com.app.service;

import com.app.model.entity.BaseEntityFile;
import com.app.repository.BaseRepository;
import com.app.utils.CryptoUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by dami on 2016-11-28.
 */
@Service
public abstract class FileService<T extends BaseEntityFile, E extends BaseRepository<T>> {

    @Autowired
    private E e;

     public T saveAndGetToken(T t) throws IOException {
        t.setToken(CryptoUtils.generateRandomToken());

        File file = prepareFile(t);
        String newName = getNewName(t);
        byte[] bytes = generateNewFile(t, file, newName);

        t.setNewFile(bytes);
        e.save(t);
        return t;
    };

    protected File prepareFile(T t) throws IOException {
        byte[] decodedBytes = Base64.decodeBase64(t.getEncodedContent());

        File file = new File(com.app.utils.StringUtils.PATH + t.getName());
        if (!file.exists()) {
            file.createNewFile();
        }

        writeByteArrayToFile(file, decodedBytes);
        return file;
    };

    protected void writeByteArrayToFile(File file, byte[] bytes) throws IOException {
        BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(file));
        writer.write(bytes);
        writer.flush();
        writer.close();
    }

    public void deleteAll(){
        e.deleteAll();
    }

    public Collection<T> findAll(){
        return e.findAll();
    }

    public T findByToken(String token){
        return e.findByToken(token);
    }

    protected abstract byte[] generateNewFile(T t, File file, String newName) throws IOException;

    protected  String getNewName(BaseEntityFile baseEntityFile){
        String result = null;
        if(baseEntityFile.getNewExtension() != null) {
            result = baseEntityFile.getName().split("\\.")[0] + "." + baseEntityFile.getNewExtension().toLowerCase();
        } else {
            result = baseEntityFile.getName();
        }
        return result;
    };

    protected abstract HttpEntity<byte[]> findByTokenAndGetConvertedFile(String token) throws IOException;
}
