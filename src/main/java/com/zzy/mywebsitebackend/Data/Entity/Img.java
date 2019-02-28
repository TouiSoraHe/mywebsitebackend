package com.zzy.mywebsitebackend.Data.Entity;

import java.util.Objects;

public class Img {
    private Integer id;

    private String small;

    private String medium;

    private String large;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small == null ? null : small.trim();
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium == null ? null : medium.trim();
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large == null ? null : large.trim();
    }

    @Override
    public String toString() {
        return "Img{" +
                "id=" + id +
                ", small='" + small + '\'' +
                ", medium='" + medium + '\'' +
                ", large='" + large + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Img img = (Img) o;
        return Objects.equals(id, img.id) &&
                Objects.equals(small, img.small) &&
                Objects.equals(medium, img.medium) &&
                Objects.equals(large, img.large);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, small, medium, large);
    }
}