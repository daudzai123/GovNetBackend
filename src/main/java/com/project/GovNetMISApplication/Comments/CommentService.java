package com.project.GovNetMISApplication.Comments;

import com.project.GovNetMISApplication.ActivitiesLog.ActivityLog;
import com.project.GovNetMISApplication.ActivitiesLog.ActivityLogService;
import com.project.GovNetMISApplication.Departments.Department;
import com.project.GovNetMISApplication.Documents.DocumentService;
import com.project.GovNetMISApplication.Exceptions.MyNotFoundException;
import com.project.GovNetMISApplication.Exceptions.UnauthorizedException;
import com.project.GovNetMISApplication.Notifications.Notification;
import com.project.GovNetMISApplication.Notifications.NotificationRepository;
import com.project.GovNetMISApplication.sendDocuments.SendDoc;
import com.project.GovNetMISApplication.sendDocuments.SendDocumentService;
import com.project.GovNetMISApplication.user.CurrentUserInfoService;
import com.project.GovNetMISApplication.user.Users;
import com.project.GovNetMISApplication.user.Enum.userTypes;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    private final CurrentUserInfoService currentUserInfoService;
    private final SendDocumentService sendDocumentService;
    private final DocumentService documentService;
    @Autowired
    private ActivityLogService activityLogService;

    @Autowired
    NotificationRepository notificationRepository;

    public CommentService(CommentRepository commentRepository, ModelMapper modelMapper, CurrentUserInfoService currentUserInfoService, SendDocumentService sendDocumentService, DocumentService documentService) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
        this.currentUserInfoService = currentUserInfoService;
        this.sendDocumentService = sendDocumentService;
        this.documentService = documentService;
    }

    // method to add comment
    public void addComment(CommentRequestDTO commentRequestDTO) {
        SendDoc sendDocById = sendDocumentService.getSendDocumentById(commentRequestDTO.getSendDocId());
        List<Department> currentUserDepartments = currentUserInfoService.getCurrentUserDepartments();
        Department senderDepartment = sendDocById.getSenderDepartment();
        Department receiverDepartments = sendDocById.getReceiverDepartment();
        if (!(currentUserDepartments.contains(senderDepartment) || currentUserDepartments.contains(receiverDepartments))) {
            throw new UnauthorizedException("Unauthorized to add comment");
        }
        else {
            Comment map = modelMapper.map(commentRequestDTO, Comment.class);
            map.setCommentDateTime(LocalDateTime.now());
            map.setUserId(currentUserInfoService.getCurrentUser());
            SendDoc sendDoc = sendDocumentService.getSendDocumentById(commentRequestDTO.getSendDocId());
            Long docId = sendDoc.getDocumentId().getDocId();
            map.setSendDocId(sendDoc);
            map.setDocumentId(documentService.getDocumentById(docId));
            if (commentRequestDTO.getParentCommentId() != null) {

                map.setParentCommentId(this.getCommentById(commentRequestDTO.getParentCommentId()));
            }
            Comment added = commentRepository.save(map);
            List<Department> departmentList=new ArrayList<>();
            Department senderDepartment1 = added.getSendDocId().getSenderDepartment();
            Department receiverDepartment = added.getSendDocId().getReceiverDepartment();
            departmentList.add(senderDepartment1);
            departmentList.add(receiverDepartment);
            Map<String,Object> details=new HashMap<>();
            details.put("commenter user ",added.getUserId().getFirstName());
            if (added.getUserId().getDepartment().size()==1 && !(added.getUserId().getUserType().equals("MEMBER_OF_COMMITTEE"))) {
                details.put("commenter department ", added.getUserId().getDepartment().get(0).getDepName());
            }
            if (added.getUserId().getDepartment().size()>1 || added.getUserId().getUserType().equals("MEMBER_OF_COMMITTEE")){
                details.put("commenter department ","committee member");
            }
            details.put("comment text ",added.getCommentText());
            ActivityLog activityLog=new ActivityLog();
            String content = activityLog.MapToString(details);
            activityLogService.logsActivity("Comment",map.getCommentId(),"Created",departmentList,content);
            for(Users user : receiverDepartments.getActiveUsers()){
                boolean hasCommnetViewAuthority = user.getAuthorities().stream()
                            .anyMatch(authority -> authority.getAuthority().equals("ROLE_comment_view"));

                if (user.getId()!= currentUserInfoService.getCurrentUser().getId() && hasCommnetViewAuthority) {
                    Notification notification = new Notification(added, user,"/incoming-documents/");
                    notificationRepository.save(notification);
                }
                
            }
            for (Users user : sendDoc.getSenderDepartment().getActiveUsers()) {
                boolean hasCommnetViewAuthority = user.getAuthorities().stream()
                            .anyMatch(authority -> authority.getAuthority().equals("ROLE_comment_view"));

                if (user.getId()!= currentUserInfoService.getCurrentUser().getId() && hasCommnetViewAuthority) {
                    Notification notification = new Notification(added, user,"/outgoing-documents/");
                    notificationRepository.save(notification);
                }
            }

        }
    }

    // Method to get document by id
    public Comment getCommentById(Long id){
        var byId = commentRepository.findById(id).orElseThrow(()->new MyNotFoundException("comment with id "+id+" not found"));
        return byId;
    }

    public List<CommentResponseDTO> getCommentsBySendDocumentId(Long sendDocId) {
        List<Comment> allCommentsBysendDocId = commentRepository.findAllBySendDocId(sendDocId);
        List<CommentResponseDTO> collect = allCommentsBysendDocId.stream().map(comment -> {
            CommentResponseDTO commentResponseDTO = mapEntityToDTO(comment);
            return commentResponseDTO;
        }).collect(Collectors.toList());
        return collect;
    }
    // public List<CommentResponseDTO> getCommentsBySendDocumentId(Long sendDocId) {
    //     List<Comment> allCommentsBysendDocId = commentRepository.findAllBySendDocIdSendDocId(sendDocId);
    //     List<CommentResponseDTO> collect = allCommentsBysendDocId.stream().map(comment -> {
    //         CommentResponseDTO commentResponseDTO = mapEntityToDTO(comment);
    //         return commentResponseDTO;
    //     }).collect(Collectors.toList());
    //     return collect;
    // }
    public List<CommentResponseDTO> getCommentsByParentCommentId(Long parentCommentId){
        List<Comment> allByParentCommentId = commentRepository.findAllByParentCommentIdCommentId(parentCommentId);
        List<CommentResponseDTO> collect = allByParentCommentId.stream().map(comment -> {
            CommentResponseDTO commentResponseDTO = mapEntityToDTO(comment);
            return commentResponseDTO;
        }).collect(Collectors.toList());
        return collect;
    }
    private CommentResponseDTO mapEntityToDTO(Comment comment){
        CommentResponseDTO map = modelMapper.map(comment, CommentResponseDTO.class);
        Users user = comment.getUserId();
        map.setCommenterName(user.getFirstName());

        if (comment.getParentCommentId() !=null) {
            map.setParentText(comment.getParentCommentId().getCommentText());
            map.setParent_commenter(comment.getParentCommentId().getUserId().getFirstName());
        }
        if (user.getUserType() == userTypes.MEMBER_OF_COMMITTEE) {
            map.setCommenterDepartment("کمیته تعقیب");
        }else{
            map.setCommenterDepartment(user.getDepartment().get(0).getDepName());
        }
        map.setDepName(user.getDepartment().get(0).getDepName());
        // String collect = comment.getSendDocId().getReceiverDepartment().getDepName();
        // String depName=null;
        // if (collect != null){
        //     depName=collect;
        // }
        // List<String> collect1 = comment.getUserId().getDepartment().stream().map(commenterDep -> {
        //     String depName1 = commenterDep.getDepName();
        //     return depName1;
        // }).collect(Collectors.toList());
        // if (collect1.size()==1){
        //     map.setCommenterDepartment(collect1.get(0));
        // }
        // else {
        //     map.setCommenterDepartment(comment.getUserId().getUserType().toString());
        // }
        // map.setDepName(depName);
        return map;
    }
}
