package alarm_system.service.main;

//직접 db에 접근하기 위한 맵퍼를 정의한 인터페이스이다
public interface Mapper {
	public void insert_dust_data(dust_data data);
	public void insert_dust_alert(dust_alert data);
	public dust_data get_dust_data(dust_data data);
	public void insert_station_inspection(station_inspection inspection);
}
