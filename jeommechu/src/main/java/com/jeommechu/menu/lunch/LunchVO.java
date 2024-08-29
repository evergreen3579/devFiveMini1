package com.jeommechu.menu.lunch;

import java.util.Objects;

public class LunchVO {
    private int id;
    private String menu;

    // 기본 생성자
    public LunchVO() {
    }

    // 매개변수 있는 생성자
    public LunchVO(int id, String menu) {
        this.id = id;
        this.menu = menu;
    }

    // id에 대한 getter와 setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // menu에 대한 getter와 setter
    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    // toString() 메소드 오버라이드
    @Override
    public String toString() {
        return "LunchVO{" +
                "id=" + id +
                ", menu='" + menu + '\'' +
                '}';
    }

    // equals() 메소드 오버라이드
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LunchVO lunchVO = (LunchVO) o;
        return id == lunchVO.id && Objects.equals(menu, lunchVO.menu);
    }

    // hashCode() 메소드 오버라이드
    @Override
    public int hashCode() {
        return Objects.hash(id, menu);
    }
}
