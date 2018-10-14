package com.piledriver.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.piledriver.service.bean.ProjectInfo;
import com.piledriver.service.dao.ImageDao;
import com.piledriver.service.dao.ProjectDao;

import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;

@Controller
@RestController
@RequestMapping("/api/v1")
public class ProjectController {

	@Autowired
	private ProjectDao projectDao;

	@Autowired
	private ImageDao imageDao;

	@RequestMapping(value = "/project", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CrossOrigin
	public ResponseEntity<List<ProjectInfo>> getProject() {
		System.out.println("getAllProject");
		List<ProjectInfo> plist = new ArrayList<ProjectInfo>();
		try {
			plist = (List<ProjectInfo>) projectDao.findAll();
			System.out.println("plist:" + plist);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ProjectInfo>>(plist, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<ProjectInfo>>(plist, HttpStatus.OK);
	}

	@RequestMapping(value = "/project", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Integer> deleteProject(@RequestParam("id") int id) {
		System.out.println("deleteProject which id=" + id);
 		try {
			projectDao.deleteProject(id);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Integer>(1, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return new ResponseEntity<Integer>(0, HttpStatus.OK);
	}

	@Transactional
	@RequestMapping(value = "/project", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ResponseEntity<Integer> addProject(HttpServletRequest request, @RequestParam("name") String name,
			@RequestParam("desc") String desc, @RequestParam("img") MultipartFile img, @RequestParam("partya") String partya)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		int ret = -1;
		System.out.println("add project name:" + name + " desc:" + desc+ " partya:" + partya);
		if (!(name == null || "".equals(name))) {
			try {
				ProjectInfo project = projectDao.findByName(name);
				// 如果有值说明名称重复
				if (project != null && project.getId() != 0) {
					return new ResponseEntity<Integer>(-2, HttpStatus.INTERNAL_SERVER_ERROR);
				}
				projectDao.insertProject(name, desc, partya);

				project = projectDao.findByName(name);

				String imageId = "P" + String.valueOf(project.getId());
				return saveImage(img, imageId, ret);

			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Integer>(0, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<Integer>(0, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	private ResponseEntity<Integer> saveImage(MultipartFile img, String imageId, int ret) {
		List<String> fileTypes = new ArrayList<String>();
		fileTypes.add("jpg");
		fileTypes.add("jpeg");
		fileTypes.add("bmp");
		fileTypes.add("png");
		String fileName = img.getOriginalFilename();
		if (!(fileName == null || "".equals(fileName))) {
			String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
			if (fileTypes.contains(extensionName)) {
				try {
					byte[] content = decompressPicByte(img.getBytes());
					System.out.println("content " + content);
					ret = imageDao.saveImage(content, imageId);
					System.out.println("return " + ret);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			return new ResponseEntity<Integer>(0, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Integer>(ret, HttpStatus.OK);
	}
	
	/**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉图片字节码压缩
     *
     * @param picByte 图片的字节码
     * @return 压缩后的图片字节码
	 * @throws IOException 
     */
    private static byte[] decompressPicByte(byte[] picByte) throws IOException {
        ByteArrayInputStream intputStream = new ByteArrayInputStream(picByte);
        
        Builder<? extends InputStream> builder = Thumbnails.of(createThumbnails(intputStream,1400,1600)).scale(0.25);
        try {
            BufferedImage bufferedImage = builder.asBufferedImage();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpeg", baos);
            byte[] byteArray = baos.toByteArray();
            return byteArray;
        } catch (IOException e) {
        }
        return picByte;

    }
    
    /** 
     * 生成缩略图 
     *  
     * @param in 
     * @param width 
     * @param height 
     * @return 
     * @throws IOException 
     */  
    public static InputStream createThumbnails(InputStream in, int width, int height) throws IOException  
    {  
  
        ByteArrayOutputStream os = new ByteArrayOutputStream();  
        Thumbnailator.createThumbnail(in, os, width, height);  
        InputStream is = new ByteArrayInputStream(os.toByteArray());  
        in.close();  
        os.close();  
        return is;  
    }


}
