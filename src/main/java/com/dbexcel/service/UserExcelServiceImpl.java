package com.dbexcel.service;

import com.dbexcel.entity.User;
import com.dbexcel.repo.UserRepo;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    @Override
    public void importUsersFromExcel(MultipartFile file) throws IOException{
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        for (int i = 1; i <= sheet.getLastRowNum(); i++){
            Row row = sheet.getRow(i);
            User user = new User();
            user.setId((int) row.getCell(0).getNumericCellValue());
            user.setName(row.getCell(1).getStringCellValue());
            user.setUserName(row.getCell(2).getStringCellValue());
            user.setAge((int) row.getCell(3).getNumericCellValue());
            userRepo.save(user);
        }
        workbook.close();
    }
}
