package com.dbexcel.service;

import com.dbexcel.entity.User;
import com.dbexcel.repo.UserRepo;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class UserExcelServiceImpl implements UserExcelService {

    private final UserRepo userRepo;

    public UserExcelServiceImpl(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public ByteArrayInputStream getUsersInExcel() throws IOException {

        List<User> users = userRepo.findAll();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Users");

        // Header Row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Id");
        headerRow.createCell(1).setCellValue("Name");
        headerRow.createCell(2).setCellValue("Username");
        headerRow.createCell(3).setCellValue("Age");

        int rowNum = 1;

        for (User user : users) {

            Row row = sheet.createRow(rowNum++);

            row.createCell(0).setCellValue(user.getId());
            row.createCell(1).setCellValue(user.getName());
            row.createCell(2).setCellValue(user.getUserName());
            row.createCell(3).setCellValue(user.getAge());
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        workbook.write(out);
        workbook.close();

        return new ByteArrayInputStream(out.toByteArray());
    }
}
