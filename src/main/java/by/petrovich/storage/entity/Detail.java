package by.petrovich.storage.entity;

import java.util.StringJoiner;

public class Detail {
    private int id;
    private String name;

    public Detail(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Detail(String name) {
        this.name = name;
    }

    public Detail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Detail detail = (Detail) o;

        if (id != detail.id) return false;
        return name != null ? name.equals(detail.name) : detail.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Detail.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .toString();
    }
}
