package com.dollop.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dollop.app.bean.Room;
import com.dollop.app.bean.Student;
import com.dollop.app.dao.IRoomDao;
import com.dollop.app.utils.DbConnection;

public class RoomDaoImpl implements IRoomDao {
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private String sql = null;
	private ResultSet resultSet = null;

	public RoomDaoImpl() {
		super();
		this.connection = DbConnection.getConnection();
	}

	@Override
	public Boolean allocateRoom(Integer studentId, Integer roomId) {
		sql = "UPDATE students SET room_id = ? WHERE id = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, roomId);
			preparedStatement.setInt(2, studentId);
			int affectedRows = preparedStatement.executeUpdate();
			return affectedRows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public List<Room> viewAllRooms() {
		sql = "SELECT * FROM rooms";
		ArrayList<Room> arrayList = new ArrayList<Room>();
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Room room = new Room();
				room.setId(resultSet.getInt("id"));
				room.setRoomNumber(resultSet.getInt("room_number"));
				room.setCapacity(resultSet.getInt("capacity"));
				room.setPayment(resultSet.getDouble("payment"));
				room.setStatus(resultSet.getString("status"));
				arrayList.add(room);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	@Override
	public String getRoomStatus(Integer roomId) {
		sql = "SELECT status FROM rooms where id=?";
		String status = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, roomId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				status = resultSet.getString("status");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public Boolean changeRoomStatus(Integer roomId, String newStatus) {
		sql = "UPDATE rooms SET status = ? WHERE id = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, newStatus);
			preparedStatement.setInt(2, roomId);
			int affectedRows = preparedStatement.executeUpdate();
			return affectedRows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Room getStudentRoom(Integer studentId) {
		sql = "SELECT r.* FROM rooms r JOIN students s ON r.id = s.room_id WHERE s.id = ?";
		Room room = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, studentId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				room = new Room();
				room.setId(resultSet.getInt("id"));
				room.setRoomNumber(resultSet.getInt("room_number"));
				room.setCapacity(resultSet.getInt("capacity"));
				room.setStatus(resultSet.getString("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return room;
	}

	@Override
	public Boolean vacateRoom(Integer roomId) {
		sql = "UPDATE rooms SET status = 'vacant' WHERE id = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, roomId);
			return preparedStatement.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean addRoom(Room room) {
		sql = "INSERT INTO rooms (room_number, capacity, payment,status) VALUES (?, ?, ?,?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, room.getRoomNumber());
			preparedStatement.setInt(2, room.getCapacity());
			preparedStatement.setDouble(3, room.getPayment());
			preparedStatement.setString(4, room.getStatus());
			int affectedRows = preparedStatement.executeUpdate();
			if (affectedRows > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean deleteRoom(Integer roomId) {
		sql = "DELETE FROM rooms WHERE id = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, roomId);
			int affectedRows = preparedStatement.executeUpdate();
			return affectedRows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Room> getAllRoomStatus() {
		sql = "SELECT status FROM rooms";
		ArrayList<Room> arrayList = new ArrayList<Room>();
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Room room = new Room();
				room.setStatus(resultSet.getString("status"));
				arrayList.add(room);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	@Override
	public Room getRoomByRoomNumber(Integer roomNumber) {
		sql = "SELECT * FROM rooms where room_number=?";
		Room room = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, roomNumber);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				room = new Room();
				room.setId(resultSet.getInt("id"));
				room.setRoomNumber(resultSet.getInt("room_number"));
				room.setCapacity(resultSet.getInt("capacity"));
				room.setPayment(resultSet.getDouble("payment"));
				room.setStatus(resultSet.getString("status"));
			}
		} catch (SQLException  e) {
			e.printStackTrace();
		}
		System.out.println("ROOM => "+room);
		return room;
	}
	
	@Override
	public Boolean isRoomExists(Integer roomNumber) {
	    String sql = "SELECT COUNT(*) FROM rooms WHERE room_number=?";
	    Boolean roomExists = false;

	    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	        preparedStatement.setInt(1, roomNumber);
	        
	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            if (resultSet.next()) {
	                int count = resultSet.getInt(1);
	                roomExists = count > 0;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    System.out.println(roomExists);
	    return roomExists;
	}

	@Override
	public Room getRoomByRoomId(Integer roomId) {
		sql = "SELECT * FROM rooms where id=?";
		Room room = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, roomId);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				room = new Room();
				room.setId(resultSet.getInt("id"));
				room.setRoomNumber(resultSet.getInt("room_number"));
				room.setCapacity(resultSet.getInt("capacity"));
				room.setPayment(resultSet.getDouble("payment"));
				room.setStatus(resultSet.getString("status"));
			}
		} catch (SQLException  e) {
			e.printStackTrace();
		}
		return room;
	}

	

	@Override
	public Boolean updateRoom(Room room, Integer roomId) {
		sql = "UPDATE rooms SET room_number = ?, capacity = ?, payment = ?, status = ?  WHERE id = ?";
		Boolean result = false;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, room.getRoomNumber());
			preparedStatement.setInt(2, room.getCapacity());
			preparedStatement.setDouble(3, room.getPayment());
			preparedStatement.setString(4, room.getStatus());
			preparedStatement.setInt(5, roomId);
			result = preparedStatement.executeUpdate()>0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("UPDATE ROOM DAO IMPL => "+result);
		return result;
	}

	@Override
	public Integer countTotalRoom() {
		sql = "SELECT COUNT(*) FROM rooms" ;
		Integer count = 0;
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			 if (resultSet.next()) {
				 count = resultSet.getInt(1);
		     }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public Integer countOccupiedRoom() {
		sql = "SELECT COUNT(*) FROM rooms WHERE status = 'Occupied'" ;
		Integer count = 0;
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			 if (resultSet.next()) {
				 count = resultSet.getInt(1);
		     }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("OCCUPIED ROOMS => "+count);
		return count;
	}
	@Override
	public Integer countVacantRoom() {
		sql = "SELECT COUNT(*) FROM rooms WHERE status = 'Vacant'" ;
		Integer count = 0;
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			 if (resultSet.next()) {
				 count = resultSet.getInt(1);
		     }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Vacant ROOMS => "+count);
		return count;
	}

	@Override
	public List<Room> searchRoom(String search) {
	    sql = "SELECT * FROM rooms WHERE "
	            + "CAST(room_number AS CHAR) LIKE ? OR "
	            + "CAST(capacity AS CHAR) LIKE ? OR "
	            + "CAST(payment AS CHAR) LIKE ? OR "
	            + "LOWER(status) LIKE LOWER(?)";  // Case-insensitive search for status

	    List<Room> roomList = new ArrayList<>();
	    
	    try {
	    	preparedStatement = connection.prepareStatement(sql);
	        String searchParam = "%" + search + "%"; // Enable partial matching

	        // Set parameters for all fields
	        for (int i = 1; i <= 4; i++) {
	        	preparedStatement.setString(i, searchParam);
	        }

	        resultSet = preparedStatement.executeQuery();
	        while (resultSet.next()) {
	            Room room = new Room();
	            room.setId(resultSet.getInt("id"));
	            room.setRoomNumber(resultSet.getInt("room_number"));
	            room.setCapacity(resultSet.getInt("capacity"));
	            room.setPayment(resultSet.getDouble("payment"));
	            room.setStatus(resultSet.getString("status"));
	            roomList.add(room);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return roomList;
	}


	@Override
	public Integer countSingleRoom() {
		sql = "SELECT COUNT(*) FROM rooms WHERE capacity = 1" ;
		Integer count = 0;
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			 if (resultSet.next()) {
				 count = resultSet.getInt(1);
		     }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Single ROOMS => "+count);
		return count;
	}

	@Override
	public Integer countDoubleRoom() {
		sql = "SELECT COUNT(*) FROM rooms WHERE capacity = 2" ;
		Integer count = 0;
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			 if (resultSet.next()) {
				 count = resultSet.getInt(1);
		     }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Single ROOMS => "+count);
		return count;
	}

	@Override
	public Integer countTripleRoom() {
		sql = "SELECT COUNT(*) FROM rooms WHERE capacity = 3" ;
		Integer count = 0;
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			 if (resultSet.next()) {
				 count = resultSet.getInt(1);
		     }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Single ROOMS => "+count);
		return count;
	}

}
