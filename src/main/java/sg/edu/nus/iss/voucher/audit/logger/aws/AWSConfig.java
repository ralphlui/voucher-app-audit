package sg.edu.nus.iss.voucher.audit.logger.aws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

@Configuration
public class AWSConfig {

  @Value("${cloud.aws.credentials.accessKey}")
  private String accessKey;

  @Value("${cloud.aws.credentials.secretKey}")
  private String secretKey;

  @Value("${spring.cloud.aws.region.static}")
  private String region;

  @Bean
  SqsAsyncClient sqsAsyncClient(){
    return SqsAsyncClient
      .builder()
      .region(Region.of(region))
      .credentialsProvider(StaticCredentialsProvider
        .create(AwsBasicCredentials.create(accessKey, secretKey)))
      .build();
 
  }

  @Bean
  public SqsTemplate sqsTemplate(SqsAsyncClient sqsAsyncClient){
      return SqsTemplate.builder().sqsAsyncClient(sqsAsyncClient).build();
  }
}
