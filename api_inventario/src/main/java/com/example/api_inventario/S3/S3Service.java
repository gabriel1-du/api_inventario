package com.example.api_inventario.S3;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.HeadBucketRequest;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.S3Client;


@Service 
@Data 
public class S3Service {

    private S3Client s3;

    public S3Service(S3Client s3) {
        this.s3 = s3;
        
    }

    //variables de conexion a s3
    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    @Value("${aws.region}")
    private String region;

    @Value("${aws.s3.endpoint:#{null}}")
    private String endpoint;

    

    @PostConstruct
    public void inicializarBucket() {
        try {
            // Intenta buscar el bucket
            s3.headBucket(HeadBucketRequest.builder().bucket(bucketName).build());
        } catch (S3Exception e) {
            // Si da error 404 (No encontrado) o 403 (Denegado), lo creamos
            if (e.statusCode() == 404 || e.statusCode() == 403) {
                s3.createBucket(CreateBucketRequest.builder().bucket(bucketName).build());
                System.out.println(" Bucket '" + bucketName + "' creado exitosamente en SeaweedFS.");
            } else {
                throw e;
            }
        }
    }

    public String subirPortada(MultipartFile archivo) throws IOException {
        // Generar un nombre único para evitar sobreescribir imágenes
        
        String nombreLimpio = archivo.getOriginalFilename() != null 
                ? archivo.getOriginalFilename().replaceAll("\\s+", "_") 
                : "imagen.jpg";

        String nombreArchivo = UUID.randomUUID().toString() + "-" + nombreLimpio;

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(nombreArchivo)
                .contentType(archivo.getContentType())
                .acl(ObjectCannedACL.PUBLIC_READ)
                .build();

        // Subir archivo a S3 / SeaweedFS
        s3.putObject(putObjectRequest, 
                RequestBody.fromInputStream(archivo.getInputStream(), archivo.getSize()));

        // Devolver el nombre o la ruta relativa para guardarla en tu MySQL
        if (endpoint != null && !endpoint.isBlank()) {
            // Si algún día vuelves a usar SeaweedFS en local
            return endpoint + "/" + bucketName + "/" + nombreArchivo;
        } else {
            // Enlace público oficial de AWS S3
            return String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, region, nombreArchivo);
        }
    }
    
}
