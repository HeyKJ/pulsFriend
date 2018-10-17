package heykj.web.kakao.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public abstract class DateUtil {
	
    public static String getToday(String pattern){
    	SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    	return sdf.format(new Date());
    }
    
    public static String convertDateFormat(String date, String orgPattern, String pattern) throws ParseException{
    	SimpleDateFormat sdf = new SimpleDateFormat(orgPattern);
    	Date _date = sdf.parse(date);
    	SimpleDateFormat sdf2 = new SimpleDateFormat(pattern);
    	return sdf2.format(_date);
    }
    
    /**
     * @param date
     * @param pattern
     * @return
     * 해당 날짜 요일 구하기
     */
    public static String getDateDay(String date, String pattern){
    	String day = "";
    	
    	try{
    		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        	Date ndate = sdf.parse(date);
        	
        	Calendar cal = Calendar.getInstance();
        	cal.setTime(ndate);
        	
        	int dayNum = cal.get(Calendar.DAY_OF_WEEK);
        	
        	switch(dayNum){
		        case 1:
		            day = "일";
		            break ;
		        case 2:
		            day = "월";
		            break ;
		        case 3:
		            day = "화";
		            break ;
		        case 4:
		            day = "수";
		            break ;
		        case 5:
		            day = "목";
		            break ;
		        case 6:
		            day = "금";
		            break ;
		        case 7:
		            day = "토";
		            break ;
		    }
        	
        	return day;

    	}catch(Exception e){
    		e.printStackTrace();
    		return "";
    	}
    
    }
  
    /**
     * @author 김준혁
     * @param start_date
     * @param pattern
     * @return
     * 시작일로부터 현재까지 주차 반환
     */
    public static String getWeeks(String start_date, String pattern){
    	try{
    		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    		Date today = sdf.parse(DateUtil.getToday(pattern));
    		Date _start_date = sdf.parse(start_date);
    		
    		long date_diff = (today.getTime() - _start_date.getTime()) / (24*60*60*1000);
			date_diff = (date_diff / 7) + 1;
			
			return String.valueOf(date_diff);
    		
    	}catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    /**
     * @author 김준혁
     * @param startDate 현재 시간과 비교할 시작 날짜
     * @param endDate 현재 시간과 비교할 종료 날짜
     * @param pattern
     * @return
     * 현재 시간이 특정 시간대에 속하는지 검사
     */
    public static boolean validateSpecificTimeZone(String today, String startDate, String endDate, String pattern){
    	try {
    		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    		Date _today = sdf.parse(today);
    		Date start_date = sdf.parse(startDate);
    		Date end_date = sdf.parse(endDate);
    		
    		if(_today.compareTo(start_date) >= 0 && _today.compareTo(end_date) < 0){
    			return true;
    		
    		}else{
    			return false;
    		}

		} catch (Exception e) {
			return false;
		}
    }
    
  /**
   * @author 김준혁
   * @param pattern
   * @param date1
   * @param date2
   * @return
   * 날짜 비교
 * @throws ParseException 
   */
  public static int compareDate(String pattern, String date1, String date2) throws ParseException {
	  SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	  Date _date1 = sdf.parse(date1);
	  Date _date2 = sdf.parse(date2);
	  return _date1.compareTo(_date2);
  }
  
  /**
   * @author 김준혁
   * @param field Calendar.YEAR, Calendar.MONTH, Calendar.DATE, Calendar.HOUR, Calendar.MINUTE, Calendar.SECOND, Calendar.MILLISECOND
   * @param amount 더하거나 뺄 수
   * @param pattern 결과 날짜 패턴
   * @return
   * 오늘 날짜 기준으로 원하는 날짜 구하기
   */
  public static String getDateBasedOnToday(int field, int amount, String pattern) {
	  Calendar calendar = new GregorianCalendar();
	  calendar.add(field, amount);
	  SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	  return simpleDateFormat.format(calendar.getTime());
  }
  
  /**
   * @author 김준혁
   * @param field Calendar.YEAR, Calendar.MONTH, Calendar.DATE, Calendar.HOUR, Calendar.MINUTE, Calendar.SECOND, Calendar.MILLISECOND
   * @param amount 더하거나 뺄 수
   * @param specificDate 기준이 되는 날짜
   * @param pattern
   * @return
   * @throws ParseException
   * 특정 날짜 기준으로 원하는 날짜 구하기
   */
  public static String getDateBaseOnSpecificDate(int field, int amount, String specificDate, String pattern) throws ParseException {
	 SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	 Calendar calendar = new GregorianCalendar();
	 calendar.setTime(sdf.parse(specificDate));
	 calendar.add(field, amount);
	 return sdf.format(calendar.getTime());
  }
  
  /**
   * @author 김준혁
   * @param value Calendar.MONDAY ... Calendar.SUNDAY
   * @param pattern
   * @return
   * 요일을 이용해 이번주 특정 날짜 구하기
   */
  @SuppressWarnings("static-access")
public static String getDateThisWeek(int value, String pattern) {
	  Calendar calendar = new GregorianCalendar();
	  if(calendar.get(calendar.DAY_OF_WEEK) == 1) {
		 calendar.add(Calendar.DATE, -1); 
	  }
	  
	  if(Calendar.SUNDAY == value) {
		  calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		  calendar.add(Calendar.DATE, 1);
		  
	  }else {
		  calendar.set(Calendar.DAY_OF_WEEK, value); 
	  }
	  
	  SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	  return simpleDateFormat.format(calendar.getTime());
  }
}
