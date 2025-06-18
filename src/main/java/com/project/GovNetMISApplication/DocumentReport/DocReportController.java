package com.project.GovNetMISApplication.DocumentReport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/DocumentReport")
public class DocReportController {
    @Autowired
    private final DocReportService docReportService;
    private final ModelMapper mapper;

    public DocReportController(DocReportService docReportService, ModelMapper mapper) {
        this.docReportService = docReportService;
        this.mapper = mapper;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addDocumentReport(@RequestPart("docReportRequestDTO") DocReportRequestDTO docReportRequestDTO,
                                                    @RequestPart(value = "docReportFile", required = false) MultipartFile docReportFile) throws IOException {
        System.out.println("Report title is: "+docReportRequestDTO.getReportTitle());
        docReportService.addDocReport(docReportRequestDTO,docReportFile);
        return ResponseEntity.ok("document Report added successfully");
    }
    @GetMapping("/getDocReportById/{id}")
    public Optional<DocReportResponseDTO> getDocumentReportById(@PathVariable Long id){
        return docReportService.getDocReportById(id);
    }
    @GetMapping("/getReportBySendDocId/{sendDocid}")
    public List<DocReportResponseDTO> getAllDocReportOfSpecificSendDoc(@PathVariable Long sendDocid){
        return docReportService.getAllDocReportBySendDocId(sendDocid);
    }

}