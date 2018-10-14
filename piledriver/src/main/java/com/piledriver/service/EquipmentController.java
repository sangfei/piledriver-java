package com.piledriver.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

import com.piledriver.service.bean.Equipment;
import com.piledriver.service.dao.EquipmentDao;
import com.piledriver.service.dao.ImageDao;

import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;

@Controller
@RestController
@RequestMapping("/api/v1")
public class EquipmentController {

	@Autowired
	private EquipmentDao equipmentDao;

	@Autowired
	private ImageDao imageDao;

	@RequestMapping(value = "/equipment", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CrossOrigin
	public ResponseEntity<Integer> addEquipment(@RequestParam("name") String name, @RequestParam("brand") String brand,
			@RequestParam("diameter") int diameter, @RequestParam("ownerid") int ownerid,
			@RequestParam("model") String model, @RequestParam("img") MultipartFile img) {
		System.out.println("add Equipment of " + name);
		int ret = 0;
		try {
			Equipment eqm = equipmentDao.findByName(name);
			// 如果有值说明名称重复
			if (eqm != null && eqm.getId() != 0) {
				return new ResponseEntity<Integer>(-2, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			ret = equipmentDao.insertEquipment( name,  brand,  diameter,  ownerid,  model);
			eqm = equipmentDao.findByName(name);
			String imageId = "E" + String.valueOf(eqm.getId());
			return saveImage(img, imageId, ret);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Integer>(1, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/list/equipment", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CrossOrigin
	public ResponseEntity<List<Equipment>> getEquipment() {
		System.out.println("get Equipment ");
		List<Equipment> plist = new ArrayList<Equipment>();
		try {
			plist = (List<Equipment>) equipmentDao.findAll();
			System.out.println("get Equipment "+plist);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Equipment>>(plist, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Equipment>>(plist, HttpStatus.OK);
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
