package z.j.j.androidstudy.bean;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;

import demo.Pinyin4jAppletDemo;
import z.j.j.androidstudy.utils.PinyinUtils;

/**
 * Created by j on 2019/1/29 0029.
 */

public class Person {
    private String name;
    private String pingyin;


    public Person(String name){
        this.name=name;
        this.pingyin=   PinyinUtils.toHanyuPinyin(name, HanyuPinyinCaseType.UPPERCASE);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPingyin() {
        return pingyin;
    }

    public void setPingyin(String pingyin) {
        this.pingyin = pingyin;
    }
}
