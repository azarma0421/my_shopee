package com.JamesCode.my_shopee.controller;

import com.JamesCode.my_shopee.service.CommonFUNC;
import com.JamesCode.my_shopee.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class SetController {

    private SettingService settingService;

    private CommonFUNC commonFUNC;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Autowired
    public SetController(SettingService thesettingService,CommonFUNC thecommonFUNC){
        settingService = thesettingService;
        commonFUNC = thecommonFUNC;
    }

    @PostMapping("/SettingPage")
    public String showLoginPage(){
        return "Setting";
    }

    @GetMapping("/SettingPage/Member")
    @ResponseBody
    public List<Map<String, Object>> getMember(
            @RequestParam(value = "json", required = false) String jsonParam)
            throws IOException {

        Map<String, Object> jsonData = commonFUNC.json2Map(jsonParam);

        List<Map<String, Object>> result_list = settingService.get_SetMember(jsonData);
        System.out.println(result_list);
        return result_list;
    }

    @PutMapping("/SettingPage/Member")
    @ResponseBody
    public List<Map<String, Object>> updateMember(
            @RequestParam(value = "json") String jsonParam)
            throws IOException {

        Map<String, Object> jsonData = commonFUNC.json2Map(jsonParam);

        List<Map<String, Object>> result_list = settingService.update_SetMember(jsonData);
        System.out.println(result_list);
        return result_list;
    }

    @GetMapping("/SettingPage/Find_P")
    @ResponseBody
    public List<Map<String, Object>> Find_Data(
            @RequestParam(value = "json", required = false) String jsonParam)
            throws IOException {

        Map<String, Object> jsonData = commonFUNC.json2Map(jsonParam);

        List<Map<String, Object>> result_list = settingService.get_ColData(jsonData);
        System.out.println(result_list);
        return result_list;
    }

    @PostMapping("/SettingPage/Product")
    @ResponseBody
    public List<Map<String, Object>> addProduct(
            @RequestParam("file") MultipartFile file,
            @RequestParam("jsonData") String jsonParam) throws IOException {

        String originalFilename = "";
        byte[] fileContent = new byte[0];
        List<Map<String,Object>> result_list = new ArrayList<>();

        if (!file.isEmpty()) {
            Map<String, Object> jsonData = commonFUNC.json2Map(jsonParam);
            result_list = settingService.add_SetProduct(jsonData);

            originalFilename = result_list.get(0).get("name").toString() + ".jpg";
            originalFilename = jsonData.get("Category").toString() + "/" + originalFilename;

            Path filePath = Paths.get(uploadDir, originalFilename);
            Files.write(filePath, file.getBytes());

            File newfile = new File(filePath.toUri());
            if (newfile.exists()) {
                ImageResizer(newfile);
            }else{
                System.out.println("[ERROR] File not found.");
            }
        }else{
            System.out.println("[ERR] File not found !");
            return null;
        }

        System.out.println(result_list);
        return result_list;
    }

    public File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(multipartFile.getBytes());
        }
        return file;
    }

    public static void ImageResizer(File inputFile) {
        try {
            // Load the JPG image
            BufferedImage originalImage = ImageIO.read(inputFile);

            // Define the new width and height
            int newWidth = 800; // New width
            int newHeight = 800; // New height

            // Create a new BufferedImage with the desired dimensions
            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

            // Use Graphics2D to perform the resizing
            Graphics2D g2d = resizedImage.createGraphics();
            g2d.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
            g2d.dispose();

            // Save the resized image to a file
            ImageIO.write(resizedImage, "jpg", inputFile);

            System.out.println("Image resized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/SettingPage/Product")
    @ResponseBody
    public List<Map<String, Object>> getProduct(
            @RequestParam(value = "json", required = false) String jsonParam)
            throws IOException {
        Map<String, Object> jsonData = commonFUNC.json2Map(jsonParam);
        List<Map<String, Object>> result_list = settingService.get_SetProduct(jsonData);
        return result_list;
    }

    @PutMapping("/SettingPage/Product")
    @ResponseBody
    public List<Map<String, Object>> updateProduct(
            @RequestParam(name = "file", required = false) MultipartFile file,
            @RequestParam(value = "json") String jsonParam) throws IOException {
        Map<String, Object> jsonData = commonFUNC.json2Map(jsonParam);
        List<Map<String,Object>> result_list = new ArrayList<>();

        // 取代舊圖
        if ( file != null && !file.isEmpty()) {
            String src = jsonData.get("src").toString();
            int index = src.indexOf("/",1);
            src = src.substring(index);
            Path filePath = Paths.get(uploadDir, src);
            Files.write(filePath, file.getBytes());
        }else{
        }

        result_list = settingService.update_SetProduct(jsonData);
        System.out.println(result_list);
        return null;
    }

    @DeleteMapping("/SettingPage/Product")
    @ResponseBody
    public List<Map<String, Object>> deleteProduct(
            @RequestParam(value = "json", required = false) String jsonParam)
            throws IOException {

        Map<String, Object> jsonData = commonFUNC.json2Map(jsonParam);

        List<Map<String, Object>> result_list = settingService.del_SetProduct(jsonData);

        String src = result_list.get(0).get("SRC").toString();
        int index = src.indexOf("/",1);
        src = src.substring(index);
        Path filePath = Paths.get(uploadDir, src);
        File fileToDelete = new File(filePath.toUri());

        if (fileToDelete.exists()) {
            // Delete the file
            if (fileToDelete.delete()) {
                System.out.println("File deleted successfully.");
            } else {
                System.err.println("Failed to delete the file.");
            }
        } else {
            System.err.println("File does not exist.");
        }
        return null;
    }

    @GetMapping("/SettingPage/Record")
    @ResponseBody
    public List<Map<String, Object>> getRecords(
            @RequestParam(value = "json", required = false) String jsonParam)
            throws IOException {
        Map<String, Object> jsonData = commonFUNC.json2Map(jsonParam);
        List<Map<String, Object>> result_list = settingService.get_SetRecords(jsonData);
        return result_list;
    }

    @PutMapping("/SettingPage/Record")
    @ResponseBody
    public List<Map<String, Object>> updateRecords(
            @RequestParam(value = "json", required = false) String jsonParam)
            throws IOException {
        Map<String, Object> jsonData = commonFUNC.json2Map(jsonParam);
        List<Map<String, Object>> result_list = settingService.update_SetR_status(jsonData);
        return result_list;
    }
}
