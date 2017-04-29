package samples;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.redshift.AmazonRedshiftClient;
import com.amazonaws.services.redshift.model.Cluster;
import com.amazonaws.services.redshift.model.CreateClusterRequest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author jeff
 * @since 4/28/17
 */
public class RedShiftClusterCreate {

    //Redshift driver: "jdbc:redshift://x.y.us-west-2.redshift.amazonaws.com:5439/dev";
    //or "jdbc:postgresql://x.y.us-west-2.redshift.amazonaws.com:5439/dev";
    static final String dbURL = "jdbc:redshift://x.y.us-west-2.redshift.amazonaws.com:5439/dev";
    static final String MasterUsername = "masteruser";
    static final String MasterUserPassword = "12345678Aa";

    public static void main(String[] args) throws IOException {
        /*
         * Create your credentials file at ~/.aws/credentials (C:\Users\USER_NAME\.aws\credentials for Windows users)
         * and save the following lines after replacing the underlined values with your own.
         *
         * [default]
         * aws_access_key_id = YOUR_ACCESS_KEY_ID
         * aws_secret_access_key = YOUR_SECRET_ACCESS_KEY
         */

        ProfileCredentialsProvider awsCreds = new ProfileCredentialsProvider();

        AmazonRedshiftClient client = new AmazonRedshiftClient(awsCreds);
        client.setEndpoint("https://redshift.us-west-2.amazonaws.com/");

        CreateClusterRequest request = new CreateClusterRequest()
                .withClusterIdentifier("exampleclusterusingjava")
                .withMasterUsername(MasterUsername)
                .withMasterUserPassword(MasterUserPassword)
                .withNodeType("ds1.xlarge")
                .withNumberOfNodes(2);

        Cluster createResponse = client.createCluster(request);
        System.out.println("Created cluster " + createResponse.getClusterIdentifier());
    }
}

