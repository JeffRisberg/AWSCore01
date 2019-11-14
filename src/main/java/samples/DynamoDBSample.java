package samples;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.ListTablesRequest;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;

import java.io.IOException;
import java.util.List;

/**
 * This sample demonstrates how to make basic requests to Amazon DyanamoDB using
 * the SDK for Java.
 * <p>
 * <b>Important:</b> Be sure to fill in your AWS access credentials in
 * ~/.aws/credentials (C:\Users\USER_NAME\.aws\credentials for Windows
 * users) before you try to run this sample.
 */
public class DynamoDBSample {

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
    final AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.standard()
      .withCredentials(new ProfileCredentialsProvider("default"))
      .withRegion(region)
      .build();

    System.out.println("Your DynamoDB tables:\n");

    ListTablesRequest request;

    boolean more_tables = true;
    String last_name = null;

    while (more_tables) {
      try {
        if (last_name == null) {
          request = new ListTablesRequest().withLimit(10);
        } else {
          request = new ListTablesRequest()
            .withLimit(10)
            .withExclusiveStartTableName(last_name);
        }

        ListTablesResult table_list = ddb.listTables(request);
        List<String> table_names = table_list.getTableNames();

        if (table_names.size() > 0) {
          for (String cur_name : table_names) {
            System.out.format("* %s\n", cur_name);
          }
        } else {
          System.out.println("No tables found!");
          System.exit(0);
        }

        last_name = table_list.getLastEvaluatedTableName();
        if (last_name == null) {
          more_tables = false;
        }

      } catch (AmazonServiceException e) {
        System.err.println(e.getErrorMessage());
        System.exit(1);
      }
    }
    System.out.println("\nDone!");
  }
}
