package com.project.DocumentMIS.APIsForMemberOfCommittee;

import com.project.DocumentMIS.Documents.Enums.DocumentType;
import com.project.DocumentMIS.sendDocuments.Enums.documentStatus;
import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
//    Long totalFarmans;
//    Long totalFarmansInTODO;
//    Long totalFarmansInProgress;
//    Long totalFarmansDONE;
//    Long totalFarmansIN_COMPLETE;
//    Long totalFarmansVIOLATION;
////About Musawwibaat
//    Long totalMusawwibaat;
//    Long totalMusawwibaatInTODO;
//    Long totalMusawwibaatInProgress;
//    Long totalMusawwibaatDONE;
//    Long totalMusawwibaatIN_COMPLETE;
//    Long totalMusawwibaatVIOLATION;
//// About Hidayaat
//    Long totalHidayat;
//    Long totalHidayatInTODO;
//    Long totalHidayatInProgress;
//    Long totalHidayatDONE;
//    Long totalHidayatIN_COMPLETE;
//    Long totalHidayatVIOLATION;
//
//// About Ahkaam
//    Long totalAhkaam;
//    Long totalAhkaamInTODO;
//    Long totalAhkaamInProgress;
//    Long totalAhkaamDONE;
//    Long totalAhkaamIN_COMPLETE;
//    Long totalAhkaamVIOLATION;
    private Map<DocumentType,Long> countSentDocumentsByType=new HashMap<>();
    private Map<DocumentType, Map<documentStatus,Long>> countSentDocumentsByTypeAndStatus=new HashMap<>();
    private Map<DocumentType,Long> countReceivedDocumentsByType=new HashMap<>();
    private Map<DocumentType, Map<documentStatus,Long>> countReceivedDocumentsByTypeAndStatus=new HashMap<>();
}
