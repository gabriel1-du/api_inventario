package com.example.api_inventario.S3;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import lombok.Data;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.S3Client;


@Service 
@Data 
public class S3Service {

    private S3Client s3;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    public String subirPortada(MultipartFile archivo) throws IOException {
        // Generar un nombre único para evitar sobreescribir imágenes
        String nombreArchivo = UUID.randomUUID().toString() + "-" + archivo.getOriginalFilename();

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(nombreArchivo)
                .contentType(archivo.getContentType())
                .build();

        // Subir archivo a S3 / SeaweedFS
        s3.putObject(putObjectRequest, 
                RequestBody.fromInputStream(archivo.getInputStream(), archivo.getSize()));

        // Devolver el nombre o la ruta relativa para guardarla en tu MySQL
        return nombreArchivo;
    }



}
