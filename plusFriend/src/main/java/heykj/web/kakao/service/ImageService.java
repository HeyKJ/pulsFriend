package heykj.web.kakao.service;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

@Service
public class ImageService {
	
//	private static final String PATH = "C:\\Users\\김준혁\\Documents\\카카오톡 받은 파일\\"; 
	private static final String PATH = "/usr/share/tomcat8/webapps/img/"; 
	
	public BufferedImage getImage(String fileName) {
		try {
			return ImageIO.read(new File(PATH + fileName));

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
