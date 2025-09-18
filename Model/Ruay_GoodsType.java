package RuayRuayMain.Model;

public class Ruay_GoodsType {
    private int typeId;
    private String typeName;

    public Ruay_GoodsType(int typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public int getTypeId() {
        return typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    @Override
    public String toString() {
        return " "+ typeId+" "+typeName + " " ;  // This ensures the combo box shows type_name
    }
}

