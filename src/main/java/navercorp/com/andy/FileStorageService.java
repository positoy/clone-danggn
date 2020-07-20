package navercorp.com.andy;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import navercorp.com.andy.naver.ProfileAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService{

    final static Logger logger = LoggerFactory.getLogger(FileStorageService.class);

    URL classpath = ClassLoader.getSystemResource("");
    Path img_goods_directory;
    Path img_user_directory;

    public FileStorageService() {
        img_goods_directory = Paths.get(classpath.getFile() + "static/img/goods");
        img_user_directory = Paths.get(classpath.getFile() + "static/img/user");

        logger.info(img_goods_directory.toString());
        logger.info(img_user_directory.toString());

        File goodsDirectory = new File(img_goods_directory.toString());
        File userDirectory = new File(img_user_directory.toString());

        if (!goodsDirectory.exists())
            goodsDirectory.mkdir();

        if (!userDirectory.exists())
            userDirectory.mkdir();
    }

    public String generateFileName(MultipartFile file, String timestamp, String userid) {
        String filename = StringUtils.cleanPath(timestamp + "_" + userid + "_" + file.getOriginalFilename());
        logger.info("filename : " + filename);
        return filename;
    }

    public String generateGoodsDefaultImgPath() {
        return "/img/goods/000_empty.png";
    }

    public String generateGoodsImgPath(String filename) {
        return "/img/goods/" + filename;
    }

    public String generateUserImgPath(String filename) {
        return "/img/user/" + filename;
    }

    public void storeGoodsImg(MultipartFile file, String filename) {
        try {
            if (file.isEmpty())
                throw new Exception("Failed to store empty file " + filename);

            if (filename.contains(".."))
                throw new Exception("Cannot store file with relative path outside current directory " + filename);

            try (InputStream inputStream = file.getInputStream()) {
                long size = Files.copy(inputStream, img_goods_directory.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (Exception e) {
            logger.info("Failed to store file " + e.getMessage());
        }
    }

    public void storeUserImg(MultipartFile file, String filename) {
        try {
            if (file.isEmpty())
                throw new Exception("Failed to store empty file " + filename);

            if (filename.contains(".."))
                throw new Exception("Cannot store file with relative path outside current directory " + filename);

            try (InputStream inputStream = file.getInputStream()) {
                long size = Files.copy(inputStream, img_goods_directory.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (Exception e) {
            logger.info("Failed to store file " + e.getMessage());
        }
    }
}