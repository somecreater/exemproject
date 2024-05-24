package alarm_system.service.main;

import java.util.Date;

//주의보, 경보 단계 조건 확인용 DTO
public class dust_data {
	private Long code;
	private String measuring_station_name;
	private String measuring_station_code;
	private int alert_10_level;
	private int alert_25_level;
	private Date alert_time;
	
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code=code;
	}
	
	public String getMeasuring_station_name() {
		return measuring_station_name;
	}
	public void setMeasuring_station_name(String measuring_station_name) {
		this.measuring_station_name=measuring_station_name;
	}
	
	public String getMeasuring_station_code() {
		return measuring_station_code;
	}
	public void setMeasuring_station_code(String measuring_station_code) {
		this.measuring_station_code=measuring_station_code;
	}
	
	public int getAlert_10_level() {
		return alert_10_level;
	}
	public void setAlert_10_level(int alert_10_level) {
		this.alert_10_level=alert_10_level;
	}
	
	public int getAlert_25_level() {
		return alert_25_level;
	}
	public void setAlert_25_level(int alert_25_level) {
		this.alert_25_level=alert_25_level;
	}
	
	public Date getAlert_time() {
		return alert_time;
	}
	public void setAlert_time(Date alert_time) {
		this.alert_time=alert_time;
	}
}
