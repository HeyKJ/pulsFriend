package heykj.web.kakao.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMessageHandler {

	private static final Logger logger = LoggerFactory.getLogger(UserMessageHandler.class);
	
	@Autowired
	private WebCrwaling webCrwaling;
	
	public Map<String, Object> handleUserMessage(String type, String content) {
		return handleTextData(content);
	}
	
	private Map<String, Object> handleTextData(String content) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		logger.info(content);

		String responseContent = "'도움말'이라고 입력해보세요.";
		
		try {
			if("도움말".equals(content))
				responseContent = "-'랭킹뉴스'라고 입력해보세요! 종합 1-5위까지의 뉴스 제목을 알려줍니다.\n-'웃고싶다'라고 입력해보세요! 웃으세요!";
			else if("랭킹뉴스".equals(content))
				responseContent = webCrwaling.get5NateRankingNews();
			else if("웃고싶다".equals(content)) {
				responseContent = "작품명 : 돌하르방도 이가 썪나요?";
				
				Map<String, Object> photoMap = new HashMap<String, Object>();
				photoMap.put("url", "http://ec2-18-191-151-205.us-east-2.compute.amazonaws.com:8080/image/0/jpg");
				photoMap.put("width", 544);
				photoMap.put("height", 960);
				resultMap.put("photo", photoMap);
			}

		} catch (Exception e) {
			responseContent = "오류 발생";
			e.printStackTrace();
		}

		resultMap.put("text", responseContent);
		return resultMap;
	}
	
}
