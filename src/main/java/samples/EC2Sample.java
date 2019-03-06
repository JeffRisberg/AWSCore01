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

import java.io.IOException;

/**
 * EC2 Management
 * <p>
 * <b>Important:</b> Be sure to fill in your AWS access credentials in
 * ~/.aws/credentials before you try to run this sample.
 */
public class EC2Sample {

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
        final AmazonEC2 ec2 = AmazonEC2ClientBuilder.standard()
                .withCredentials(new ProfileCredentialsProvider("default"))
                .withRegion(region)
                .build();

        System.out.println("===========================================");
        System.out.println("Getting Started with Amazon EC2");
        System.out.println("===========================================\n");

        boolean done = false;

        try {
            DescribeInstancesRequest request = new DescribeInstancesRequest();
            while (!done) {
                DescribeInstancesResult response = ec2.describeInstances(request);

                for (Reservation reservation : response.getReservations()) {
                    for (Instance instance : reservation.getInstances()) {
                        System.out.printf(
                                "Found instance with id %s, " +
                                        "AMI %s, " +
                                        "type %s, " +
                                        "state %s " +
                                        "and monitoring state %s",
                                instance.getInstanceId(),
                                instance.getImageId(),
                                instance.getInstanceType(),
                                instance.getState().getName(),
                                instance.getMonitoring().getState());
                    }
                }

                request.setNextToken(response.getNextToken());

                if (response.getNextToken() == null) {
                    done = true;
                }
            }
        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which means your request made it "
                    + "to Amazon EC2, but was rejected with an error response for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with EC2, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
    }
}