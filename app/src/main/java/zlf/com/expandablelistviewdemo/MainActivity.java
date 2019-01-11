package zlf.com.expandablelistviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private ExpandableListView m_elv;
    private List<String> father_List;// 父层数据
//    private List<String> father_Lists;// 父层数据
    private List<List<ContentEntity>> list_Son;// 子层数据
    private int[] img = {R.drawable.ic_launcher, R.drawable.ic_launcher};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_elv = (ExpandableListView) findViewById(R.id.elv);
        list_Son = new ArrayList<>();
        ContentEntity contentEntity01 = new ContentEntity();
        contentEntity01.setName("张飞");
        contentEntity01.setInfo("车骑将军");
        contentEntity01.setTime("公元184年（中平元年）");
        contentEntity01.setImage(img[0]);
        contentEntity01.setType(1);

        ContentEntity contentEntity02 = new ContentEntity();
        contentEntity02.setName("武松");
        contentEntity02.setInfo("行者");
        contentEntity02.setTime("公元1085年");
        contentEntity02.setSchoolName("武松打虎");
        contentEntity02.setType(3);

        ContentEntity contentEntity03 = new ContentEntity();
        contentEntity03.setName("孙悟空");
        contentEntity03.setInfo("美猴王");
        contentEntity03.setTime("娑婆世界东胜神洲傲来国花果山");
        contentEntity03.setImage(img[1]);
        contentEntity03.setType(1);

        ContentEntity contentEntity04 = new ContentEntity();
        contentEntity04.setName("贾宝玉");
        contentEntity04.setInfo("宝二爷，怡红公子，绛洞花主");
        contentEntity04.setTime("康熙四十五年丙戌");
        contentEntity04.setSchoolName("大观园女儿国中唯一的男性居民");
        contentEntity04.setType(3);

        List<ContentEntity> son_List01 = new ArrayList<>();
        son_List01.add(contentEntity01);
        son_List01.add(contentEntity02);
        List<ContentEntity> son_List02 = new ArrayList<>();
        son_List02.add(contentEntity02);
        List<ContentEntity> son_List03 = new ArrayList<>();
        son_List03.add(contentEntity03);
        List<ContentEntity> son_List04 = new ArrayList<>();
        son_List04.add(contentEntity04);
// 添加子目录数据
        list_Son.add(son_List01);
        list_Son.add(son_List02);
        list_Son.add(son_List03);
        list_Son.add(son_List04);
// 添加父目录数据
        father_List = new ArrayList<>();
        father_List.add("三国");
        father_List.add("水浒");
        father_List.add("西游");
        father_List.add("红楼梦");
// 数据适配
        ContentAdapter adapter = new ContentAdapter(this, father_List, list_Son);
        m_elv.setAdapter(adapter);
    }
}
