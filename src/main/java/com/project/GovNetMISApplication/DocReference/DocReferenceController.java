package com.project.GovNetMISApplication.DocReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reference")
public class DocReferenceController {

    @Autowired
    private DocRefService docRefService;

    @PostMapping("/add")
    public String addDepartment(@RequestBody DocReference reference) {
        docRefService.addReference(reference);
        return "Reference added successfully";
    }

    @PostMapping("/update/{id}")
    public String updateReference(@RequestBody DocReference department1, @PathVariable Long id) {
        docRefService.updateDeparment(department1, id);
        return "Reference Updated successfully";
    }

    @GetMapping("/getById/{id}")
    public DocReference getSingleDepartment(@PathVariable Long id) {
        return docRefService.findsingleRef(id);
    }
    @DeleteMapping("/getById/{id}")
    public String deleteReference(@PathVariable Long id) {
         if (docRefService.deleteRef(id)) {
          return "the reference deleted";  
         }
         return "the reference not deleted";
    }

    @GetMapping("/all")
    public Page<DocReference> getAllDepartments(Pageable pageable) {

        return docRefService.getallReference(pageable);
    }
    
    @GetMapping("/allwithoutpage")
    public List<DocReference> allList(Pageable pageable) {

        return docRefService.getallwithoutpage();

    }
}
