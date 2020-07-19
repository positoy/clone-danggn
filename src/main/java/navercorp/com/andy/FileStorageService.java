package navercorp.com.andy;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService{

    private final Path rootLocation = Paths.get("/Users/positoy/IdeaProjects/clone-danggn/src/main/resources/static/img/goods");
    File rootDirectory;

    public FileStorageService() {
        System.out.println(rootLocation.toString());
        rootDirectory = new File(rootLocation.toString());
        if (!rootDirectory.exists()) {
            rootDirectory.mkdir();
        }
    }

    public void store(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        System.out.println("filename : " + filename);
        try {
            if (file.isEmpty())
                throw new Exception("Failed to store empty file " + filename);

            if (filename.contains(".."))
                throw new Exception("Cannot store file with relative path outside current directory " + filename);

            try (InputStream inputStream = file.getInputStream()) {
                long size = Files.copy(inputStream, rootLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (Exception e) {
            System.out.println("Failed to store file " + e.getMessage());
        }
    }
}