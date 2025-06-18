package com.project.DocumentMIS.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.project.DocumentMIS.Departments.Department;
import com.project.DocumentMIS.Departments.DepartmentRepository;
import com.project.DocumentMIS.Documents.Document;
import com.project.DocumentMIS.sendDocuments.ReportDTO;
import com.project.DocumentMIS.sendDocuments.SendDoc;
import com.project.DocumentMIS.sendDocuments.SendDocResponseDTO;
import com.project.DocumentMIS.sendDocuments.SendDocumentsRepository;
import com.project.DocumentMIS.sendDocuments.receiverDepartmentDTO;
import com.project.DocumentMIS.sendDocuments.senderDepartmentDTO;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.export.SimpleXlsxExporterConfiguration;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@Service
public class ReportService {
   @Autowired
   DepartmentRepository departmentrRepository;

   @Autowired
   SendDocumentsRepository sendDocumentsRepository;

   @Autowired
   private ModelMapper modelMapper;

   @Value("${backup.path}")
   private String backupPath;

   public String exportReport(String reportFormat) throws FileNotFoundException, JRException{


       List<SendDoc> sendDocs = sendDocumentsRepository.findAll();


       File file = ResourceUtils.getFile("classpath:DocReport.jrxml");
       JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
       JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(mapEntityToDTO(sendDocs));
       Map<String, Object> parameter = new HashMap<>();
       parameter.put("Ezatullah", "Shahin");
       parameter.put(JRParameter.REPORT_LOCALE, new Locale("ar", "SA")); // Arabic (Saudi Arabia)

       JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, dataSource);
       jasperPrint.setProperty("net.sf.jasperreports.export.character.encoding", "UTF-8");
        JasperExportManager.exportReportToPdfFile(jasperPrint,backupPath+"depaertment.pdf");
       // String excelFilePath = backupPath+"depaertment.xlsx";
       // try {
       //     exportToExcel(jasperPrint, excelFilePath);
       // } catch (JRException | IOException e) {
       //     e.printStackTrace();
       // }

       return "Report exported successfully to Excel.";
   }


//    private void exportToExcel(JasperPrint jasperPrint, String filePath) throws JRException, FileNotFoundException, IOException {
//        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
//            // Set up Excel exporter
//            SimpleXlsxExporterConfiguration exporterConfig = new SimpleXlsxExporterConfiguration();
//            SimpleXlsxReportConfiguration reportConfig = new SimpleXlsxReportConfiguration();

//            // Configure the Excel exporter


//            // Export to Excel
//            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
//        }
//    }

   private List<ReportDTO> mapEntityToDTO(List<SendDoc> sentDocumentByDepartments) {
       List<ReportDTO> sendDocResponseDTOS= sentDocumentByDepartments.stream()
               .map(doc -> {
                   ReportDTO map = new ReportDTO();
                //    modelMapper.map(doc, ReportDTO.class);

                   if (doc.getReceiverDepartment() != null)
                       map.setReceiverDepartment("doc.getReceiverDepartment().getDepName()");
                   if (doc.getDocumentId() != null) {
                       map.setDocNumber("doc.getDocumentId().getDocNumber()");
                       map.setSubject("doc.getDocumentId().getSubject()");
                   }
                   map.setTargetOrganResponse("doc.getTargetOrganResponse()");
                   return map;
               })
               .collect(Collectors.toList());
       return sendDocResponseDTOS;
   }


}
