package alarm_system.service.main;

import java.util.Date;

//주의보, 경보 정보 DTO
public class dust_alert {
	private Long dust_alert_code;
	private Long dust_code;
	private String measuring_station_name;
	private int alert_level;
	private Date alert_time;
	
	public Long getDust_alert_code() {
		return dust_alert_code;
	}
	public void setDust_alert_code(Long dust_alert_code) {
		this.dust_alert_code=dust_alert_code;
	}
	
	public Long getDust_code() {
		return dust_code;
	}
	public void setDust_code(Long dust_code) {
		this.dust_code=dust_code;
	}
	
	
	public String getMeasuring_station_name() {
		return measuring_station_name;
	}
	public void setMeasuring_station_name(String measuring_station_name) {
		this.measuring_station_name=measuring_station_name;
	}
	
	public int getAlert_level() {
		return alert_level;
	}
	public void setAlert_level(int alert_level) {
		this.alert_level=alert_level;
	}
	
	public Date getAlert_time() {
		return alert_time;
	}
	public void setAlert_time(Date alert_time) {
		this.alert_time=alert_time;
	}
}
