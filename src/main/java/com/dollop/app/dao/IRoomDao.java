package com.dollop.app.dao;

import java.util.List;

import com.dollop.app.bean.Room;
import com.dollop.app.bean.Student;

public interface IRoomDao {
	public Boolean allocateRoom(Integer studentId,Integer roomId);  
    public List<Room> viewAllRooms();  
    public String getRoomStatus(Integer roomId);  
    public Boolean changeRoomStatus(Integer roomId, String newStatus);
    public Room getStudentRoom(Integer studentId);  
    public Boolean vacateRoom(Integer roomId);   
    public Boolean addRoom(Room room);   
    public Boolean deleteRoom(Integer roomId);
    public List<Room> getAllRoomStatus();
    
    
    public Boolean isRoomExists(Integer roomNumber);
    public Room getRoomByRoomNumber(Integer roomNumber); 
    public Room getRoomByRoomId(Integer roomId); 
    public List<Room> searchRoom(String search);
    public Boolean updateRoom(Room roon, Integer roomId);
    public Integer countTotalRoom();
    public Integer countOccupiedRoom();
	Integer countVacantRoom();
	Integer countSingleRoom();
	public Integer countDoubleRoom();
	public Integer countTripleRoom();
     
}
