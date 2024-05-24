package alarm_system.service.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.ibatis.session.SqlSession;


//경보 단계 여부 분석 서비스 이다.
public class dust_analysis_service {
	
	public final int PM_10_LEVEL_4=150;
	public final int PM_10_LEVEL_2=300;
	
	public final int PM_25_LEVEL_3=75;
	public final int PM_25_LEVEL_1=150;
	public static int index=1;
	//일정 수치가 3번 이상 나타날시 해당 데이터를 db에 insert한다.
	//미세먼지, 초미세 먼지 주의보 경보 따로 구현
	
	//입력받은 데이터를 기준으로 select를 수행한다
	
	public void dust_analysis_2(String time, String position, String position_code, int PM10, int PM25) {
		
		SqlSession session=databasesetting.getSession();
		try {
		Mapper mpr=session.getMapper(Mapper.class);

		int answer_10=0;
		int answer_25=0;
		
		//문자열로 된 시간 데이터를 Date 형으로 변환한다.
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH");
        Date dusttime=new Date();
        try {
        	if(time.endsWith(" 24")) {
        		time=time.replace(" 24", " 00");
        		dusttime=dateFormat.parse(time);
        		Calendar cal = Calendar.getInstance();
        		cal.setTime(dusttime);
                cal.add(Calendar.DAY_OF_MONTH, 1);
                dusttime=cal.getTime();
        	}
        	else {
        		dusttime=dateFormat.parse(time);
        	}
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        //직접 데이터를 분석해서 경보또는 주의보 수치를 넘었는지 여부를 판단하고 db에 해당 데이터를 insert한다.
		answer_10=dust_10_analysis(PM10);
		answer_25=dust_25_analysis(PM25);
		dust_data dust=new dust_data();
		dust.setAlert_time(dusttime);
		dust.setAlert_10_level(answer_10);
		dust.setAlert_25_level(answer_25);
		dust.setMeasuring_station_code(position_code);
		dust.setMeasuring_station_name(position);
		mpr.insert_dust_data(dust);
		session.commit();
		
		dust_data data=mpr.get_dust_data(dust);
		
		Date alerttime=dusttime;
		//1시간전, 2시간전 데이터를 가져온다.
		Calendar cal = Calendar.getInstance();	
    	cal.setTime(dusttime);
    		
    	cal.add(Calendar.HOUR_OF_DAY, -1);
    	dusttime=cal.getTime();
		dust.setAlert_time(dusttime);
		dust_data data_1=mpr.get_dust_data(dust);

    	cal.add(Calendar.HOUR_OF_DAY, -1);
    	dusttime=cal.getTime();
		dust.setAlert_time(dusttime);
		dust_data data_2=mpr.get_dust_data(dust);
		
		//2시간전, 1시간전, 현재의 측정 데이터가 존재하면 경보, 주의보 여부를 판단한다.
			if((data_2!=null&&(data_2.getAlert_10_level()!=0||data_2.getAlert_25_level()!=0))&&
					(data_1!=null&&(data_1.getAlert_10_level()!=0||data_1.getAlert_25_level()!=0))&&
					(data!=null&&(data.getAlert_10_level()!=0||data.getAlert_25_level()!=0))) {
				
				//3개의 미세먼지 데이터 수준과 초미세먼지 데이터 수준을 분석하여서 경보나 주의보 발생여부를 판단한다.
				int answer_25_1=data_1.getAlert_25_level();
				int answer_25_2=data_2.getAlert_25_level();
				int answer_10_1=data_1.getAlert_10_level();
				int answer_10_2=data_2.getAlert_10_level();
				int alert_25=max_alert_level(answer_25_2,answer_25_1,answer_25);
				int alert_10=max_alert_level(answer_10_2, answer_10_1, answer_10);
				
				//경보 레벨중 가장 높은 수준인 것을 판단해서 경보를 db에 insert한다.
				if(alert_25!=0||alert_10!=0) {
					if(alert_25==0) {
						alert_25=5;
					}else if(alert_10==0) {
						alert_10=5;
					}
					int alert=Math.min(alert_25, alert_10);
					dust_alert alt=new dust_alert();
					alt.setAlert_level(alert);
					alt.setAlert_time(alerttime);
					alt.setDust_code(data.getCode());
					alt.setMeasuring_station_name(dust.getMeasuring_station_name());
					mpr.insert_dust_alert(alt);
					session.commit();
				}
			}
		}finally {
			 session.close();
		}
	}
	
	
	//일정 수치 이상일시 미세, 초미세 주의보인지 경보인지 알려준다.
	public int dust_10_analysis(int PM10) {
		int dust_answer=0;
		if(PM10>=PM_10_LEVEL_2) {
			//level 2
			dust_answer=2;
		}
		else if(PM10<PM_10_LEVEL_2&&PM10>=PM_10_LEVEL_4) {
			//level 4
			dust_answer=4;
		}
		return dust_answer;
	}
	public int dust_25_analysis(int PM25){
		int dust_answer=0;
		if(PM25>=PM_25_LEVEL_1) {
			//level 1
			dust_answer=1;
		}
		else if(PM25<PM_25_LEVEL_1&&PM25>=PM_25_LEVEL_3) {
			//level 3
			dust_answer=3;
		}
		return dust_answer;
	}
	
	//3개의 경고 레벨을 받아서 가장 최댓값을 반환한다.(2시간전, 1시간전, 현재)
	public int max_alert_level(int a3,int a2,int a1) {
			return Math.max(Math.max(a1, a2), a3);
	}

}

