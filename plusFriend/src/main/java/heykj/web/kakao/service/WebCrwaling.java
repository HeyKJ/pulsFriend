package heykj.web.kakao.service;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import heykj.web.kakao.util.DateUtil;

@Service
public class WebCrwaling {

	private static final Logger logger = LoggerFactory.getLogger(WebCrwaling.class);
	
	public String get5NateRankingNews() throws IOException {
		String url = "https://news.nate.com/rank/interest?sc=all&p=day&date=" + DateUtil.getToday("yyyyMMdd");
		Document document = Jsoup.connect(url).get();
		Element newsElement = document.select(".postRankSubjectList").first();

		Elements titElements = newsElement.getElementsByClass("tit");
		StringBuffer sf = new StringBuffer();
		int rank = 1;
		
		for(Element titleElement : titElements) {
			sf.append("[" + (rank++) + "] " + titleElement.text() + "\n");
		}
		
		sf.append(url);
		return sf.toString();
	}

}
