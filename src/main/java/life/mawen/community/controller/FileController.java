package life.mawen.community.controller;

import life.mawen.community.dto.FileDTO;
import life.mawen.community.provider.AliCloudProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
@Slf4j
public class FileController {

    @Autowired
    private AliCloudProvider aliCloudProvider;


    @RequestMapping("/file/upload")
    @ResponseBody
    public FileDTO testUpload(@RequestParam("editormd-image-file")MultipartFile file, Model model){
        FileDTO fileDTO = new FileDTO();
        String filename = file.getOriginalFilename();
        System.out.println(filename);
        try {
            if(file != null){
                if(!"".equals(filename.trim())){
                    File newFile = new File(filename);
                    FileOutputStream outputStream = new FileOutputStream(newFile);
                    outputStream.write(file.getBytes());
                    outputStream.close();
                    file.transferTo(newFile);
                    String url = aliCloudProvider.upload(newFile);
                    fileDTO.setUrl(url);
                    fileDTO.setSuccess(1);
                    fileDTO.setMessage("上传成功");
                }
            }
        } catch (FileNotFoundException e) {
            fileDTO.setSuccess(0);
            fileDTO.setMessage("上传失败");
            e.printStackTrace();
        } catch (IOException e) {
            fileDTO.setSuccess(0);
            fileDTO.setMessage("上传失败");
            e.printStackTrace();
        }
        return fileDTO;
    }
}
