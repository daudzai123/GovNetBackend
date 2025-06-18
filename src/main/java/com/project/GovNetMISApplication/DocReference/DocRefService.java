package com.project.GovNetMISApplication.DocReference;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.GovNetMISApplication.ActivitiesLog.ActivityLogService;
import com.project.GovNetMISApplication.Exceptions.MyNotFoundException;

@Service
public class DocRefService {

    @Autowired
    private DocRefRepository docRefRepository;
    @Autowired
    private ActivityLogService activityLogService;

    public void addReference(DocReference reference) {
        docRefRepository.save(reference);
        // activityLogService.logActivity("Document Reference",reference.getId(),"Created");

    }

    public void updateDeparment(DocReference docReference, Long id) {
        DocReference docReference2 = docRefRepository.findById(id)
                .orElseThrow(() -> new MyNotFoundException("The Reference with this Id: " + id + " Not found!"));
        if (docReference.getRefName() != null) {
            docReference2.setRefName(docReference.getRefName());
        }
        if (docReference.getDescription() != null) {
            docReference2.setDescription(docReference.getDescription());
        }

        docRefRepository.save(docReference2);
        // activityLogService.logActivity("Department",docReference2.getId(),"Updated");
    }

    public Page<DocReference> getallReference(Pageable pageable) {  
        Page<DocReference> docRefList = docRefRepository.findAll(pageable);
        return docRefList;
    }

    public List<DocReference> getallwithoutpage() {
        return docRefRepository.findAll();

    }

    public boolean deleteRef(Long id) {
        DocReference ref = docRefRepository.findById(id).orElseThrow(() ->new MyNotFoundException("the Reference not found"));
        if (ref != null) {
            docRefRepository.delete(ref);
            return true; 
        }
        return false;
         
    }

    public DocReference findsingleRef(Long id) {
        return docRefRepository.findById(id).orElseThrow(() ->new MyNotFoundException("the Reference not found"));
    }
}
