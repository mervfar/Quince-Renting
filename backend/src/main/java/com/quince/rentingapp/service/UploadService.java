package com.quince.rentingapp.service;

import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.quince.rentingapp.configuration.firebase.FirebaseFactory;
import com.quince.rentingapp.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class UploadService {
    private Bucket storageBucket= FirebaseFactory.getInstance().getStorage();
    private final CurrentUserService currentUser;

    public String uploadFile(MultipartFile file) throws IOException {
            User user=currentUser.getCurrentUser();
            String username=user.getUsername();
            Date now = new Date();
            String path = username+'#'+(now.getTime());
            String CONTENT_TYPE = file.getContentType();
            Blob blob=storageBucket.create(path, file.getBytes(), CONTENT_TYPE);
            storageBucket.getStorage().updateAcl(blob.getBlobId(), Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));
            return blob.getMediaLink();
        }

}
