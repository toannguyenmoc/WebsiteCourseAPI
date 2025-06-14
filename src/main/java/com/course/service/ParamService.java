package com.course.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class ParamService {

    @Autowired
    HttpServletRequest request;

    public String getString(String name, String defaultValue) {
        String value = request.getParameter(name);
        return (value != null && !value.isEmpty()) ? value : defaultValue;
    }

    public int getInt(String name, int defaultValue) {
        try {
            String value = request.getParameter(name);
            return (value != null) ? Integer.parseInt(value) : defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public double getDouble(String name, double defaultValue) {
        try {
            String value = request.getParameter(name);
            return (value != null) ? Double.parseDouble(value) : defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public boolean getBoolean(String name, boolean defaultValue) {
        String value = request.getParameter(name);
        if (value != null) {
            return Boolean.parseBoolean(value);
        }
        return defaultValue;
    }

    public Date getDate(String name, String pattern) {
        String value = request.getParameter(name);
        if (value != null && !value.isEmpty()) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                return sdf.parse(value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public File saveFile(MultipartFile file, String path) {
        if (file == null || file.isEmpty()) {
            return null;
        }

        try {
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String originalFileName = file.getOriginalFilename();
            String extension = "";

            int dotIndex = originalFileName.lastIndexOf(".");
            if (dotIndex != -1) {
                extension = originalFileName.substring(dotIndex);
                originalFileName = originalFileName.substring(0, dotIndex);
            }

            String uniqueFileName = originalFileName + "_" + System.currentTimeMillis() + extension;
            File savedFile = new File(dir, uniqueFileName);

            file.transferTo(savedFile);

            return savedFile;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
