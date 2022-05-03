public class BoxedItem implements Sellable, Transportable {
    private String description;
    private int price;
    private int weight;
    private boolean haz;
    private int height = 0;
    private int width = 0;
    private int depth = 0;

    public BoxedItem(String des, int p, int w, boolean h) {
        description = des;
        price = p;
        weight = w;
        haz = h;
    }


    @Override
    public String description() {
        return description;
    }

    @Override
    public int listPrice() {
        return price;
    }

    @Override
    public int lowestPrice() {
        return price / 2;
    }

    @Override
    public int weight() {
        return weight;
    }

    @Override
    public boolean isHazardous() {
        return haz;
    }

    public int insuredValue() {
        return price * 2;
    }

    public void setBox(int h, int w, int d) {
        height = h;
        weight = w;
        depth = d;
    }
}
