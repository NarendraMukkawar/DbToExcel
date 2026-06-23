package com.dbexcel.service;

import com.dbexcel.entity.User;
import com.dbexcel.repo.UserRepo;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

@Service
public class UserReportServiceImpl implements UserReportService {

    private final UserRepo repo;

    public UserReportServiceImpl(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public byte[] generateReport() throws Exception {

        List<User> users = repo.findAll();
        //InputStream reportStream = getClass().getResourceAsStream("/reports/users.jrxml");
        ClassPathResource resource = new ClassPathResource("reports/users.jrxml");
        System.out.println(resource.exists());
        InputStream reportStream = resource.getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(users);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), dataSource);
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
