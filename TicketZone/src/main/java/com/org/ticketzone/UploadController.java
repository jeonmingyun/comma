package com.org.ticketzone;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.org.ticketzone.domain.AttachFileDTO;
import com.org.ticketzone.domain.InqAttachVO;
import com.org.ticketzone.domain.NoticeAttachVO;
import com.org.ticketzone.service.InqAttachService;
import com.org.ticketzone.service.NoticeAttachService;

import lombok.AllArgsConstructor;

   

@AllArgsConstructor
@Controller
public class UploadController {
   private NoticeAttachService noticeAttachService;
   private InqAttachService inqAttachService;
   // 날짜별 폴더생성(2019/04/10)
   private String getFolder() {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      Date date = new Date();
      String str = sdf.format(date);

      return str.replace("-", File.separator);
   }

   // 이미지 파일판단
   private boolean checkImageType(File file) {
      try {
         String contentType = Files.probeContentType(file.toPath());

         return contentType.startsWith("image");
      } catch (IOException e) {
         e.printStackTrace();
      }
      return false;
   }

   // 코스

   @RequestMapping(value = "/cos", method = RequestMethod.GET)
   public String cos(Model model) throws JsonProcessingException {

      return "cos";
   }

   @GetMapping("/uploadForm")
   public void uploadForm() {
      System.out.println("upload form");

   }

   @PostMapping("/uploadFormAction")
   public void uploadFormPost(MultipartFile[] uploadFile, Model model) {

      String uploadFolder = "/var/lib/apache-tomcat-9.0.20/webapps/ROOT/resources/img";

      for (MultipartFile multipartFile : uploadFile) {
         System.out.println("------------------------------------");
         System.out.println("Upload File Name: " + multipartFile.getOriginalFilename());
         System.out.println("Upload File Size: " + multipartFile.getSize());

         File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
         try {
            multipartFile.transferTo(saveFile);
         } catch (Exception e) {
            System.out.println(e.getMessage());
         }
      }
   }

   @GetMapping("/uploadAjax")
   public void uploadAjax() {
      System.out.println("upload ajax");
   }

   @PostMapping(value = "/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
   @ResponseBody
   public ResponseEntity<List<AttachFileDTO>> uploadAjaxPost(MultipartFile[] uploadFile) {

      List<AttachFileDTO> list = new ArrayList<>();
      System.out.println("update ajax post........");
      String uploadFolder = "/var/lib/apache-tomcat-9.0.20/webapps/ROOT/resources/img";
      String uploadFolderPath = getFolder();
      // make folder ---------------
      File uploadPath = new File(uploadFolder, uploadFolderPath); // 파일경로

      System.out.println("upload path: " + uploadPath);

      if (uploadPath.exists() == false) { // 경로에 폴더가없으면 폴더생성
         uploadPath.mkdirs();
      }
      // make yyyy/mm/dd folder
      for (MultipartFile multipartFile : uploadFile) {

         AttachFileDTO attachDTO = new AttachFileDTO();
//         System.out.println("Upload File Name: " + multipartFile.getOriginalFilename());
//         System.out.println("Upload File Size: " + multipartFile.getSize());

         String uploadFileName = multipartFile.getOriginalFilename();

         uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("/") + 1);
//         System.out.println("only file name: " + uploadFileName);
         attachDTO.setFileName(uploadFileName);
//         File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());

         UUID uuid = UUID.randomUUID();
         uploadFileName = uuid.toString() + "_" + uploadFileName;

         try {
            File saveFile = new File(uploadPath, uploadFileName);
            multipartFile.transferTo(saveFile);

            attachDTO.setUuid(uuid.toString());
            attachDTO.setUploadPath(uploadFolderPath);

            // 이미지 파일 체크
//            if (checkImageType(saveFile)) {
//
//               attachDTO.setImage(true);
//
//               FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
//               Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
//
//               thumbnail.close();
//            }
            list.add(attachDTO);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      System.out.println(list);
      return new ResponseEntity<>(list, HttpStatus.OK);
   }

   // 다운로드
   @GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
   @ResponseBody
   public ResponseEntity<Resource> downloadFile(@RequestHeader("User-Agent") String userAgent, String fileName) {
      System.out.println("download file: " + fileName);

      Resource resource = new FileSystemResource("/var/lib/apache-tomcat-9.0.20/webapps/ROOT/resources/img/" + fileName);
      System.out.println("resource: " + resource);

      if (resource.exists() == false) {
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }

      String resourceName = resource.getFilename();
      String resourceOriginalName = resourceName.substring(resourceName.indexOf("_") + 1);
      HttpHeaders headers = new HttpHeaders();
      try {
         String downloadName = null;

         if (userAgent.contains("Trident")) {
            System.out.println("IE browser");
            downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8").replaceAll("/", " ");
         } else if (userAgent.contains("Edge")) {
            System.out.println("Edge");
            downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8");
         } else {
            System.out.println("Chrome browser");
            downloadName = new String(resourceOriginalName.getBytes("UTF-8"), "ISO-8859-1");
         }
         System.out.println("downloadName: " + downloadName);
         headers.add("Content-Disposition", "attachment; filename=" + downloadName);
      } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
      }
      return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
   }

   // 파일삭제
   @PostMapping("/deleteFile")
   @ResponseBody
   public ResponseEntity<String> deleteFile(String fileName, NoticeAttachVO vo, InqAttachVO inq) {
      int notice_no = vo.getNotice_no();
      int board_no = inq.getBoard_no();
      System.out.println("deleteFile: " + fileName);
      System.out.println(notice_no);
      File file;

      try {
         file = new File("/var/lib/apache-tomcat-9.0.20/webapps/ROOT/resources/img/" + URLDecoder.decode(fileName, "UTF-8"));
         file.delete();
         
            String largeFileName = file.getAbsolutePath().replace("s_", "");
            System.out.println("largeFileName: " + largeFileName);
            file = new File(largeFileName);
            file.delete();
            if(vo.getUuid() != null) {
               noticeAttachService.delete(notice_no);
            } else {
               inqAttachService.Inq_delete(board_no);
            }
         
      } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<String>("deleted", HttpStatus.OK);

   }
}