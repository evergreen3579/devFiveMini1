package com.jeommechu.menu.lunch;

import com.jeommechu.menu.common.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LunchDAO {

    // 데이터베이스 연결을 위한 메소드
    private Connection getConnection() throws SQLException {
        return JDBCUtil.getConnection();
    }

    /**
     * 새로운 점심 메뉴를 추가하는 메소드
     *
     * @param lunchVO 추가할 점심 메뉴
     * @throws SQLException SQL 예외 발생 시
     */
    public void addLunch(LunchVO lunchVO) throws SQLException {
        String sql = "INSERT INTO lunch (menu) VALUES (?)";
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            if (lunchVO.getMenu() != null) {
                stmt.setString(1, lunchVO.getMenu());
                stmt.executeUpdate();
            } else {
                throw new IllegalArgumentException("Menu cannot be null");
            }
        }
    }

    /**
     * 전체 점심 메뉴 정보를 조회하는 메소드
     *
     * @return 점심 메뉴 목록
     * @throws SQLException SQL 예외 발생 시
     */
    public List<LunchVO> getAllLunches() throws SQLException {
        List<LunchVO> lunches = new ArrayList<>();
        String sql = "SELECT * FROM lunch";
        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String menu = rs.getString("menu");
                LunchVO lunchVO = new LunchVO(id, menu);
                lunches.add(lunchVO);
            }
        }
        return lunches;
    }

    /**
     * 모든 점심 메뉴 정보를 삭제하는 메소드
     *
     * @throws SQLException SQL 예외 발생 시
     */
    public void deleteAllLunches() throws SQLException {
        String sql = "DELETE FROM lunch";
        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }

    /**
     * 특정 메뉴가 존재하는지 확인하는 메소드
     *
     * @param menu 확인할 메뉴 이름
     * @return 메뉴 존재 여부
     * @throws SQLException SQL 예외 발생 시
     */
    public boolean isMenuExists(String menu) throws SQLException {
        String sql = "SELECT COUNT(*) FROM lunch WHERE menu = ?";
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, menu);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
                return false;
            }
        }
    }

    /**
     * 모든 메뉴 이름만을 조회하는 메소드
     *
     * @return 메뉴 이름 목록
     * @throws SQLException SQL 예외 발생 시
     */
    public List<String> getAllMenuItems() throws SQLException {
        List<String> menuItems = new ArrayList<>();
        String sql = "SELECT menu FROM lunch";
        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                menuItems.add(rs.getString("menu"));
            }
        }
        return menuItems;
    }
    
    
 // Lunch 테이블에 항목을 추가하는 메서드
    public boolean addToLunch(String foodlistNum, String memberId) {
        String sql = "INSERT INTO Lunch (foodlist_num, member_id) VALUES (?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean isAdded = false;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, foodlistNum);
            stmt.setString(2, memberId);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                isAdded = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }

        return isAdded;
    }
}
