package soo.md.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.extern.log4j.Log4j;
import soo.md.fileset.Path;

@Log4j
@Service
public class DragdropServiceImpl implements DragdropService {
   private Map<String, List<Object>> map;
   private MultipartHttpServletRequest multipartRequest;
   private String filestore = Path.FILE_STORE;
   
   @Override
   public Map<String, List<Object>> getUpdateFileName() {
      upload();
      return map;
   }
   @Override
   public MultipartHttpServletRequest getMultipartRequest() {
      return multipartRequest;
   }
   @Override
   public void setMultipartRequest(MultipartHttpServletRequest multipartRequest) {
      this.multipartRequest = multipartRequest;
   }

   private void upload() {
      map = new Hashtable<String, List<Object>>();
      
      Iterator<String> itr = multipartRequest.getFileNames();
      List<Object> ofnames = new ArrayList<Object>();
      List<Object> savefnames = new ArrayList<Object>();
      List<Object> fsizes = new ArrayList<Object>();
       StringBuilder sb = null;
       
       while(itr.hasNext()) {
          sb = new StringBuilder();
          MultipartFile mpf = multipartRequest.getFile(itr.next());
          String ofname = mpf.getOriginalFilename();
          String savefname = sb.append(new SimpleDateFormat("yyyyMMddhhmmss").format(System.currentTimeMillis()))
                .append(UUID.randomUUID().toString())
                .append(ofname.substring(ofname.lastIndexOf("."))).toString();
          long fsize = mpf.getSize();
          String fileFullPath = filestore + savefname;
          
          try {
             mpf.transferTo(new File(fileFullPath));
             ofnames.add(ofname);
             savefnames.add(savefname);
             fsizes.add(fsize);
          }catch(IOException ie) {
             log.info("DragdropServiceImpl upload() ie: " + ie);
          }
       }
      
       map.put("ofnames", ofnames);
       map.put("savefnames", savefnames);
       map.put("fsizes", fsizes);
   }
}