package com.auction.service.impl;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.auction.exceprion.AMSClientException;
import com.auction.exceprion.AMSServiceException;
import com.auction.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Locale;

/**
 * Created by Helga on 03.12.2017.
 */

@Component
public class UploadImageAmsServiceImpl implements StorageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadImageAmsServiceImpl.class);

    @Autowired
    private MessageSource messageSource;

    @Value("${aws.accessKeyId}")
    private String accessKeyId;

    @Value("${aws.secretKey}")
    private String secretKey;

    @Value("${bucket.name}")
    private String bucketName;

    @Value("${photo.path.dir}")
    private String pathName;

    private Long lotId;

    /**
     * This method for construct file path and upload image to ams s3
     *
     * @param multipartFile
     * @return
     * @throws AMSClientException
     * @throws AMSServiceException
     */
    public String upload(MultipartFile multipartFile) throws AMSClientException, AMSServiceException {

        AWSCredentials credentials = new BasicAWSCredentials(accessKeyId, secretKey);

        String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String keyName = keyName(filename);
        AmazonS3 s3client = new AmazonS3Client(credentials);
        try {

            LOGGER.info(messageSource.getMessage("upload.object", null, Locale.ENGLISH));

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(multipartFile.getContentType());

            s3client.putObject(new PutObjectRequest(
                    bucketName, keyName, multipartFile.getInputStream(), objectMetadata));

        } catch (AmazonServiceException ase) {
            LOGGER.error(messageSource.getMessage("aws.service.error", null, Locale.ENGLISH));
            throw new AMSServiceException(ase.getMessage(), ase);

        } catch (AmazonClientException ace) {
            LOGGER.error(messageSource.getMessage("aws.client.error", null, Locale.ENGLISH));
            throw new AMSClientException(ace.getMessage(), ace);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return keyName;
    }

    /**
     * This method for get lot id
     *
     * @return
     */
    public Long getLotId() {
        return lotId;
    }

    /**
     * This method for set lot id
     *
     * @param lotId
     */
    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }

    /**
     * This method for construct file path
     *
     * @param filename
     * @return
     */
    private String keyName(String filename) {
        String[] fileExpansion = filename.split("\\.");
        String keyName = pathName + getLotId() + "/lot-" + getLotId() + "." + fileExpansion[1];
        return keyName;
    }
}