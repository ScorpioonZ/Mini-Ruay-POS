package RuayRuayMain.Model;

public class Ruay_Goods {
     int good_Id;
     String goods_name;
     int goods_type_id;
     String goods_type;
     double price;
     int amount;
     String imgPath;

    // Constructor
    public Ruay_Goods(int goodId, String goods_name,int goods_type_id, String goods_type,double price,int amount,String imgPath) {
        this.good_Id = goodId;
        this.goods_name = goods_name;
        this.goods_type_id = goods_type_id;
        this.goods_type = goods_type;
        this.price = price;
        this.amount = amount;
        this.imgPath = imgPath;
    }

    // Override toString() for better output representation
    @Override
    public String toString() {
        return "Good ID: " + good_Id + ", name: " + goods_name + " Goods_type: " + goods_type_id +"Goods_type_name: "+ String.valueOf(goods_type)+" goods_price: "+price +" Amount: " + amount;
    }

    public int getGoodId() {
        return good_Id; 
    }
    
    public String getgoodsname() {
        return goods_name; 
    }

    public String getGoodsType() {
        return goods_type; 
    }

    public double getGoodsprice() {
        return price; 
    }

    public int getAmount() {
		return amount;
    }
    public int getGoodstpye_id() {
    	return goods_type_id;
    }
    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
