package samples;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.comprehend.AmazonComprehend;
import com.amazonaws.services.comprehend.AmazonComprehendClientBuilder;
import com.amazonaws.services.comprehend.model.*;
import java.io.IOException;

/**
 * This sample demonstrates how to make basic requests to Amazon Comprehend using the AWS SDK for
 * Java.
 */
public class ComprehendExample {

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
    String text1 = "It is raining today in Seattle";
    String text2 = "I'm having trouble with VPN on mac";
    String text3 = "I need to reset my vpn password";
    String text4 = "the visuals were award-winning";

    // Create credentials using a provider chain. For more information, see
    // https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/credentials.html
    AWSCredentialsProvider awsCreds = DefaultAWSCredentialsProviderChain.getInstance();

    System.out.println("===========================================");
    System.out.println("Getting Started with Amazon Comprehend");
    System.out.println("===========================================\n");

    try {
      AmazonComprehend comprehendClient =
          AmazonComprehendClientBuilder.standard()
              .withCredentials(awsCreds)
              .withRegion("us-west-2")
              .build();

      // Call DetectSentiment API
      System.out.println("Calling DetectSentiment");

      DetectSentimentRequest detectSentimentRequest;
      DetectSentimentResult detectSentimentResult;

      System.out.println(text1);
      detectSentimentRequest = new DetectSentimentRequest().withText(text1).withLanguageCode("en");
      detectSentimentResult = comprehendClient.detectSentiment(detectSentimentRequest);
      printDetectedSentiment(detectSentimentResult);

      System.out.println(text2);
      detectSentimentRequest = new DetectSentimentRequest().withText(text2).withLanguageCode("en");
      detectSentimentResult = comprehendClient.detectSentiment(detectSentimentRequest);
      printDetectedSentiment(detectSentimentResult);

      System.out.println(text3);
      detectSentimentRequest = new DetectSentimentRequest().withText(text3).withLanguageCode("en");
      detectSentimentResult = comprehendClient.detectSentiment(detectSentimentRequest);
      printDetectedSentiment(detectSentimentResult);

      System.out.println(text4);
      detectSentimentRequest = new DetectSentimentRequest().withText(text4).withLanguageCode("en");
      detectSentimentResult = comprehendClient.detectSentiment(detectSentimentRequest);
      printDetectedSentiment(detectSentimentResult);

      System.out.println("End of DetectSentiment\n");

      // Call DetectEntities API
      System.out.println("Calling DetectEntities");
      DetectEntitiesRequest detectEntitiesRequest;
      DetectEntitiesResult detectEntitiesResult;

      System.out.println(text1);
      detectEntitiesRequest = new DetectEntitiesRequest().withText(text1).withLanguageCode("en");
      detectEntitiesResult = comprehendClient.detectEntities(detectEntitiesRequest);
      detectEntitiesResult.getEntities().forEach(System.out::println);

      System.out.println(text2);
      detectEntitiesRequest = new DetectEntitiesRequest().withText(text2).withLanguageCode("en");
      detectEntitiesResult = comprehendClient.detectEntities(detectEntitiesRequest);
      detectEntitiesResult.getEntities().forEach(System.out::println);

      System.out.println(text3);
      detectEntitiesRequest = new DetectEntitiesRequest().withText(text3).withLanguageCode("en");
      detectEntitiesResult = comprehendClient.detectEntities(detectEntitiesRequest);
      detectEntitiesResult.getEntities().forEach(System.out::println);

      System.out.println("End of DetectEntities");

      // Call DetectKeyPhrases API
      System.out.println("Calling DetectKeyPhrases");
      DetectKeyPhrasesRequest detectKeyPhrasesRequest;
      DetectKeyPhrasesResult detectKeyPhrasesResult;

      System.out.println(text1);
      detectKeyPhrasesRequest = new DetectKeyPhrasesRequest().withText(text1).withLanguageCode("en");
      detectKeyPhrasesResult = comprehendClient.detectKeyPhrases(detectKeyPhrasesRequest);
      detectKeyPhrasesResult.getKeyPhrases().forEach(System.out::println);

      System.out.println(text2);
      detectKeyPhrasesRequest = new DetectKeyPhrasesRequest().withText(text2).withLanguageCode("en");
      detectKeyPhrasesResult = comprehendClient.detectKeyPhrases(detectKeyPhrasesRequest);
      detectKeyPhrasesResult.getKeyPhrases().forEach(System.out::println);

      System.out.println(text3);
      detectKeyPhrasesRequest = new DetectKeyPhrasesRequest().withText(text3).withLanguageCode("en");
      detectKeyPhrasesResult = comprehendClient.detectKeyPhrases(detectKeyPhrasesRequest);
      detectKeyPhrasesResult.getKeyPhrases().forEach(System.out::println);

      System.out.println("End of DetectKeyPhrases");

      System.out.println("Done");
    } catch (AmazonServiceException ase) {
      System.out.println(
          "Caught an AmazonServiceException, which means your request made it "
              + "to Amazon Comprehend, but was rejected with an error response for some reason.");
      System.out.println("Error Message:    " + ase.getMessage());
      System.out.println("HTTP Status Code: " + ase.getStatusCode());
      System.out.println("AWS Error Code:   " + ase.getErrorCode());
      System.out.println("Error Type:       " + ase.getErrorType());
      System.out.println("Request ID:       " + ase.getRequestId());
    } catch (AmazonClientException ace) {
      System.out.println(
          "Caught an AmazonClientException, which means the client encountered "
              + "a serious internal problem while trying to communicate with Amazon Comprehend, "
              + "such as not being able to access the network.");
      System.out.println("Error Message: " + ace.getMessage());
    }
  }

  private static void printDetectedSentiment(DetectSentimentResult detectSentimentResult) {
    System.out.println(detectSentimentResult);
  }
}
