package com.example.api_inventario.Config;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
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

    @Bean
    public S3Client s3Client() {
        S3ClientBuilder builder = S3Client.builder()
                .region(Region.of(region));

        // Si la variable endpoint tiene valor (Desarrollo con SeaweedFS)
        if (endpoint != null && !endpoint.isBlank()) {
            builder.endpointOverride(URI.create(endpoint))
                   .credentialsProvider(StaticCredentialsProvider.create(
                           AwsBasicCredentials.create("local", "local")))
                   .forcePathStyle(true); // Obligatorio para emuladores S3 locales
        } else {
            // Si la variable está vacía (Producción en AWS EC2)
            // DefaultCredentialsProvider toma los permisos del IAM Role automáticamente
            builder.credentialsProvider(DefaultCredentialsProvider.create());
        }

        return builder.build();
    }

}
