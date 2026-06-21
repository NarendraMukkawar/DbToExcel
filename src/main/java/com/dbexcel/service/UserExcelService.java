package com.dbexcel.service;

import com.dbexcel.entity.User;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public interface UserExcelService {

    //List<User> getUsersInExcel();
    ByteArrayInputStream getUsersInExcel() throws IOException;
}
