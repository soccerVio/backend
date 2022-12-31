package soccervio.back.utils;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Component
public class ImageUtil {

    private final Environment environment;

    public ImageUtil(Environment environment) {
        this.environment = environment;
    }

    public String namingFile(MultipartFile image) {
        String originalImageName =
                StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
        int dotIndex = originalImageName.lastIndexOf('.');
        String imageExtension = originalImageName.substring(dotIndex);
        return UUID.randomUUID() + imageExtension;
    }

    public String saveImage(MultipartFile image) throws IOException {
        String uploadDir = environment.getProperty("images.uploads");
        String newPhotoVideoName = namingFile(image);
        String newImagePath = environment.getProperty("app.root.backend") +
                File.separator + "uploads" + File.separator + newPhotoVideoName;

        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = image.getInputStream()) {
            Path imagePath = uploadPath.resolve(newPhotoVideoName);
            Files.copy(inputStream, imagePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("Could not save file");
        }

        return newImagePath;
    }

    public void deleteImage(String uploadDir, String imageName) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        Path filePath = uploadPath.resolve(imageName);
        Files.deleteIfExists(filePath);
    }
}
