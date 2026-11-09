package com.example.api_inventario.Config;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3ClientBuilder;

@Configuration 
public class S3Config {
    
    @Value("${aws.region}")
    private String region;

    @Value("${aws.s3.endpoint:#{null}}")
    private String endpoint;

    @Value("${AWS_ACCESS_KEY_ID:#{null}}")
    private String accessKey;

    @Value("${AWS_SECRET_ACCESS_KEY:#{null}}")
    private String secretKey;

    @Value("${AWS_SESSION_TOKEN:#{null}}")
    private String sessionToken;

    @Bean
    public S3Client s3Client() {
        S3ClientBuilder builder = S3Client.builder()
                .region(Region.of(region));

        //Si hay endpoint local configurado (SeaweedFS)
        if (endpoint != null && !endpoint.isBlank()) {
            builder.endpointOverride(URI.create(endpoint))
                   .credentialsProvider(StaticCredentialsProvider.create(
                           AwsBasicCredentials.create("local", "local")))
                   .forcePathStyle(true);
        } 
        // Si están las credenciales de Voclabs en el archivo .env
        else if (accessKey != null && !accessKey.isBlank() && sessionToken != null && !sessionToken.isBlank()) {
            builder.credentialsProvider(StaticCredentialsProvider.create(
                    AwsSessionCredentials.create(accessKey.trim(), secretKey.trim(), sessionToken.trim())));
        } 
        // Si corre en servidor AWS EC2 con IAM Role (sin .env)
        else {
            builder.credentialsProvider(DefaultCredentialsProvider.create());
        }

        return builder.build();
    }

}
