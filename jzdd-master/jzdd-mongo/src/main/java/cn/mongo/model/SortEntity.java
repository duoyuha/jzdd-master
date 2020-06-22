package cn.mongo.model;

/**
 * Created by sunkuiwei on 2017/10/31.
 */
public class SortEntity {

    private  String  sortkey;

    private  String  sortvalue;

    public SortEntity() {
    }

    public SortEntity(String sortkey, String sortvalue) {
        this.sortkey = sortkey;
        this.sortvalue = sortvalue;
    }

    public String getSortkey() {
        return sortkey;
    }

    public void setSortkey(String sortkey) {
        this.sortkey = sortkey;
    }

    public String getSortvalue() {
        return sortvalue;
    }

    public void setSortvalue(String sortvalue) {
        this.sortvalue = sortvalue;
    }
}
