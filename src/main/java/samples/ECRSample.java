package samples;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ecr.AmazonECR;
import com.amazonaws.services.ecr.AmazonECRClientBuilder;
import com.amazonaws.services.ecr.model.*;

import java.io.IOException;
import java.util.List;

/**
 * ECR Management
 * <p>
 * <b>Important:</b> Be sure to fill in your AWS access credentials in
 * ~/.aws/credentials before you try to run this sample.
 */
public class ECRSample {

  public static void main(String[] args) throws IOException {
    /*
     * Create your credentials file at ~/.aws/credentials (C:\Users\USER_NAME\.aws\credentials for Windows users)
     * and save the following lines after replacing the underlined values with your own.
     *
     * [default]
     * aws_access_key_id = YOUR_ACCESS_KEY_ID
     * aws_secret_access_key = YOUR_SECRET_ACCESS_KEY
     */

    final String region = "us-west-2";
    final AmazonECR client = AmazonECRClientBuilder.standard()
      .withCredentials(new ProfileCredentialsProvider("default"))
      .withRegion(region)
      .build();

    GetAuthorizationTokenRequest request = new GetAuthorizationTokenRequest();
    GetAuthorizationTokenResult response = client.getAuthorizationToken(request);

    ListImagesRequest listImagesRequest = new ListImagesRequest();
    listImagesRequest.setRepositoryName("alpha");

    ListImagesResult listImagesResult = client.listImages(listImagesRequest);

    List<ImageIdentifier> imageIds = listImagesResult.getImageIds();

    for (ImageIdentifier imageId : imageIds) {
      System.out.println(imageId.toString());
    }
  }
}
