package com.auction.service;

import com.auction.exceprion.AMSClientException;
import com.auction.exceprion.AMSServiceException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Helga on 29.11.2017.
 */
@Component
public interface StorageService {
    /**
     * This method for construct file path and upload image to ams s3
     *
     * @param multipartFile
     * @return
     * @throws AMSClientException
     * @throws AMSServiceException
     */
    String upload(MultipartFile multipartFile) throws AMSServiceException, AMSClientException;

    /**
     * This method for set lot id
     *
     * @param lotId
     */
    void setLotId(Long lotId);
}
