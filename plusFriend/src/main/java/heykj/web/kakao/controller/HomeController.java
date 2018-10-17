package heykj.web.kakao.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import heykj.web.kakao.service.ImageService;
import heykj.web.kakao.service.UserMessageHandler;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private UserMessageHandler userMessageHandler;
	@Autowired
	private ImageService imageService;

	@RequestMapping(value ="/keyboard", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> keyboard() {
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("type", "text");
		return resultMap;
	}
	
	@RequestMapping(value ="/message", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
	public @ResponseBody Map<String, Object> message(@RequestBody Map<String, Object> pMap) {
		logger.info(pMap.toString());
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("message", userMessageHandler.handleUserMessage((String) pMap.get("type"), (String)pMap.get("content")));
		return resultMap;
	}
	
	@RequestMapping(value = "/friend", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
	public void insertFriend(@RequestBody Map<String, Object> pMap) {
		logger.info(pMap.get("user_key") + "이(가) 친구 추가");
	}

	@RequestMapping(value = "/friend", method = RequestMethod.DELETE, produces = {"application/json;charset=utf-8"})
	public void deleteFriend(@RequestBody Map<String, Object> pMap) {
		logger.info(pMap.get("user_key") + "이(가) 친구 삭제");
	}
	
	@RequestMapping(value ="/image/{name}/{ext}", method = RequestMethod.GET)
	public void image(@PathVariable String name, @PathVariable String ext, HttpServletResponse response) throws IOException {
		logger.info(name + "." + ext);
		ImageIO.write(imageService.getImage(name + "." + ext), "png", response.getOutputStream());
	}
	
}
