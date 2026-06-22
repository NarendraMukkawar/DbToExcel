package com.dbexcel.service;

import com.dbexcel.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public interface UserExcelService {

    //List<User> getUsersInExcel();
    ByteArrayInputStream getUsersInExcel() throws IOException;
    void importUsersFromExcel(MultipartFile file)
            throws IOException;
}
