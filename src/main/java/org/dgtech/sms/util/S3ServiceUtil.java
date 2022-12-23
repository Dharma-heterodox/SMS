package org.dgtech.sms.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Component
@ConfigurationProperties(prefix="aws")
public final class S3ServiceUtil {
	
	
	
	private static AmazonS3 s3Client=null;
	
	@Value("${aws.s3.bucketname}")
	private String bucketMSMS; 
	
	public final String fileSuffix="/";
	
	final String urlSeperator="|";
	
	@Value("${aws.s3.bucketurl}")
	private String s3BucketUrl;
	
	private static Logger logger = LoggerFactory.getLogger(S3ServiceUtil.class);
	
	public S3ServiceUtil(@Value("${aws.s3.accessid}")String accesskey,@Value("${aws.s3.secretkey}")String secretkey) {
		try {
			if(s3Client==null) {
				AWSCredentials credentials=new BasicAWSCredentials(accesskey, secretkey);
				s3Client=AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).
						withRegion(Regions.AP_SOUTHEAST_1).build();
				logger.debug("S3 Connection Estabilesd :: "+s3Client.getRegionName());
			}
		}catch(Exception ex) {
			logger.debug("Exceptoin in S3 connection :"+ ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	
	public String putHWObjectinS3(Long studentId,MultipartFile file)throws Exception{
		String key=studentId+fileSuffix+UUID.randomUUID().toString();
		try {
			logger.debug("Put Object in S3 : "+studentId);
			ObjectMetadata metadata=new ObjectMetadata();
			metadata.setContentType(file.getContentType());
			s3Client.putObject(bucketMSMS, key, file.getInputStream(),metadata);
			logger.debug("Home work saved for : "+studentId);
		}catch(Exception ex) {
			logger.error("Error while saving Home work : "+ex.getMessage());
			throw new Exception(ex);
		}finally {
			file.getInputStream().close();
		}
		return key;
	}
	
	public String createHWByTeachers(Long teacherId,MultipartFile file)throws Exception{
		String key=file.getOriginalFilename();
		try {
			logger.debug("Put HW Object in S3 : "+teacherId);
			ObjectMetadata metadata=new ObjectMetadata();
			metadata.setContentType(file.getContentType());
			s3Client.putObject(bucketMSMS, key, file.getInputStream(),metadata);
			logger.debug("Home work saved for : "+teacherId);
		}catch(Exception ex) {
			logger.error("Error saving Home work : "+ex.getMessage());
			throw new Exception(ex);
		}finally {
			file.getInputStream().close();
		}
		return key;
	}
	
//	private Bucket createBucket(String sectionId)throws Exception{
//		String bucketName= morningStar+separator+sectionId;
//		if(s3Client.doesBucketExist(bucketName)) {
//			return s3Client.createBucket(bucketName);
//		}
//	}
	
	
//	public S3ObjectInputStream getS3HWObject(Long subjectId,String homeworkDate,Long studentId)throws Exception{
//		String bucketName= morningStar+separator+studentId;
//		Bucket bucket=null;
//		S3Object s3Object=null;
//		
//		String key=subjectId+fileSuffix+homeworkDate+fileExt;
//		try {
//			logger.debug("Retrieve S3 HW object for : "+studentId);
//			s3Object=s3Client.getObject(new GetObjectRequest(bucketName,key));
////			ResponseHeaderOverrides headerOverrides=new ResponseHeaderOverrides().withCacheControl("No-cache")
////					.withContentDisposition("attachment; filename="+studentId+fileExt); 
////			GetObjectRequest getObjectRequestHeaderOverride = new GetObjectRequest(bucketName, key)
////                    .withResponseHeaders(headerOverrides);
////			s3Object = s3Client.getObject(getObjectRequestHeaderOverride);
//			s3Object.getObjectContent();
//		}catch(Exception ex) {
//			logger.debug("Error while fetching home work");
//			logger.error("Error while fetching home work : "+ex.getMessage());
//		}finally {
//			s3Object.close();
//		}
//		
//		return s3Object.getObjectContent();
//	}
	
	public List<String> makeURL(String url)throws Exception{
		String[] urls=null;
		List<String> urlList=new ArrayList<String>();
		if(url!=null && url.contains(urlSeperator)) {
			urls=url.split("\\"+urlSeperator);
			for(int i=0;i<urls.length;i++) {
				urlList.add(s3BucketUrl+urls[i]);
			}
		}else {
			urlList.add(s3BucketUrl+url);
		}
		return urlList;
	}
	
	
	

}
