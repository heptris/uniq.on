package com.ssafy.uniqon.service.s3;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ssafy.uniqon.domain.s3.AwsS3;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AwsS3Service {

    private final AmazonS3 amazonS3;

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public AwsS3 upload(MultipartFile multipartFile, String dirName) throws IOException {
        File file = convertMultipartFileToFile(multipartFile)
                .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File convert fail"));

        return upload(file, dirName);
    }

    public AwsS3 upload(File file, String dirName) {
        String key = randomFileName(file, dirName);
        String path = putS3(file, key);
        removeFile(file);

        return AwsS3
                .builder()
                .key(key)
                .path(path)
                .build();
    }

    private String randomFileName(File file, String dirName) {
        return dirName + "/" + UUID.randomUUID() + file.getName();
    }

    private String putS3(File uploadFile, String fileName) {
        amazonS3.putObject(new PutObjectRequest(bucket, fileName, uploadFile)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        return getS3(bucket, fileName);
    }

    private String getS3(String bucket, String fileName) {
        return amazonS3.getUrl(bucket, fileName).toString();
    }

    private void removeFile(File file) {
        file.delete();
        System.out.println("실행?");
    }

    public Optional<File> convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        File file = new File(System.getProperty("user.dir") + StringUtils.cleanPath(multipartFile.getOriginalFilename()));
        if (file.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(file)){
                fos.write(multipartFile.getBytes());
            } catch(IOException e){
                e.printStackTrace();
            }
            return Optional.of(file);
        }
        return Optional.empty();
    }

    public void remove(AwsS3 awsS3) {
        if (!amazonS3.doesObjectExist(bucket, awsS3.getKey())) {
            throw new AmazonS3Exception("Object " +awsS3.getKey()+ " does not exist!");
        }
        amazonS3.deleteObject(bucket, awsS3.getKey());
    }

    public String getThumbnailPath(String path) {
        return amazonS3Client.getUrl(bucket, path).toString();
    }

    public String pdfToImg(MultipartFile multipartFile) {
        // pdf to img
        File file = null;
        try {
            file = convertMultipartFileToFile(multipartFile).orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File convert fail"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        PDDocument document = null; // document 생성
        try {
            document = PDDocument.load(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        PDFRenderer renderer = new PDFRenderer(document); // PDF 렌더러 불러오기
        List<BufferedImage> bufferedImages = new ArrayList<BufferedImage>(); // 변환될 이미지 객체를 담을 List 선언
        int width = 0, height = 0; // 병합될 이미지 파일의 너비와 높이 값을 담을 변수
        int pageCount = document.getNumberOfPages();

        for(int page = 0; page<pageCount; page++) { // 한 페이지씩 꺼내와서
            BufferedImage bim = null; // 이미지로 변환
            try {
                bim = renderer.renderImage(page);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            bufferedImages.add(bim); // 그리고 이미지 리스트에 담음

            if(bim.getWidth() > width) width = bim.getWidth(); // 병합될 이미지의 최대 너비
            height += bim.getHeight(); // 병합될 이미지의 최대 높이
        }

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); // 병합될 이미지 객체 생성
        Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics(); // 그래픽 객체 생성

        graphics.setBackground(Color.WHITE); // 배경을 흰색으로 지정

        // 이미지 병합 과정
        int idx = 0;
        for(BufferedImage obj : bufferedImages) { // 이미지 List에서 이미지를 하나씩 꺼내와서
            if(idx == 0) height = 0; // 첫번째 이미지의 경우 높이를 0으로 설정
            graphics.drawImage(obj, 0, height, null); // 그래픽 객체에 꺼내온 이미지를 그려줌

            height += obj.getHeight(); // 높이값을 이미지의 높이만큼 더해줌
            idx++;
        }

        try {
            ImageIO.write(bufferedImage, "jpg", new File(System.getProperty("user.dir") + StringUtils.cleanPath(file.getName().substring(0,file.getName().length()-4) + ".jpg"))); // 마지막으로 병합된 이미지 생성
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        graphics.dispose(); // 그래픽 리소스 해제

        if (document != null) {
            try {
                document.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        File file2 = new File(System.getProperty("user.dir") + StringUtils.cleanPath(file.getName().substring(0,file.getName().length()-4) + ".jpg"));

        AwsS3 awsS3 = upload(file2, "startup");

        file.delete();

        return awsS3.getPath();
    }
}
