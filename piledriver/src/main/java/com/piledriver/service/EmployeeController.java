package com.piledriver.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.piledriver.service.bean.Employee;
import com.piledriver.service.dao.EmployeeDao;
import com.piledriver.service.dao.ImageDao;

import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;

@Controller
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeDao stuffDao;

	@Autowired
	private ImageDao imageDao;

	@RequestMapping(value = "/stuff", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CrossOrigin
	public ResponseEntity<Integer> addStuff(@RequestParam("name") String name, @RequestParam("sex") String sex,
			@RequestParam("phone") String phone, @RequestParam("birth") String birth,
			@RequestParam("title") String title, @RequestParam("img") MultipartFile img) {
		System.out.println("add stuff of " + name);
		int ret = 0;
		try {
			Employee person = stuffDao.findByPhone(phone);
			// 如果有值说明名称重复
			if (person != null && person.getId() != 0) {
				return new ResponseEntity<Integer>(-2, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			ret = stuffDao.insertStuff(name, sex, "123456", phone, Date.valueOf(birth), Integer.valueOf(title));
			person = stuffDao.findByPhone(phone);
			String imageId = "S" + String.valueOf(person.getId());
			return saveImage(img, imageId, ret);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Integer>(1, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/list/stuff", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CrossOrigin
	public ResponseEntity<List<Employee>> getStuff() {
		System.out.println("get stuff by ");
		List<Employee> plist = new ArrayList<Employee>();
		try {
			plist = (List<Employee>) stuffDao.findAll();

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Employee>>(plist, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Employee>>(plist, HttpStatus.OK);
	}

	@RequestMapping(value = "/stuff", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CrossOrigin
	public ResponseEntity<Employee> getStuffByPhone(@RequestParam("phone") String phone) {
		System.out.println("get stuff by " + phone);
		Employee stuff = new Employee();
		try {
			stuff = stuffDao.findByPhone(phone);
			System.out.println("get stuff by " + stuff);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Employee>(stuff, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (stuff.getName().equals("")) {
			return new ResponseEntity<Employee>(stuff, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Employee>(stuff, HttpStatus.OK);
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
