package nature;

public class Nature {
    private int id;
    private String nature;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public Nature(int id, String nature) {
        this.id = id;
        this.nature = nature;
    }

    public Nature(String nature) {
        this.nature = nature;
    }

    public Nature() {}
}
