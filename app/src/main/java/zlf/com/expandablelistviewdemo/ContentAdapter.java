package zlf.com.expandablelistviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ContentAdapter extends BaseExpandableListAdapter {
    private List<List<ContentEntity>> son_List;
    private List<String> father_List;
    private Context context;
    private final int TYPE_1 = 0;
    private final int TYPE_2 = 1;

    public ContentAdapter(Context context, List<String> father_List, List<List<ContentEntity>> list_Son) {
        super();
        this.context = context;
        this.father_List = father_List;
        this.son_List = list_Son;
    }

    @Override
    public int getGroupCount() {
        return father_List != null ? father_List.size() : 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return son_List != null ? son_List.get(groupPosition).size() : 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return father_List.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return son_List.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public int getChildTypeCount() {
        return 2;
    }

    @Override
    public int getChildType(int groupPosition, int childPosition) {
        int type = son_List.get(groupPosition).get(childPosition).getType();
        if (type == 1) {
            return TYPE_1;
        }
        return TYPE_2;
    }

    // 父层控件视图加载
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolderFather father_vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.father_item, null);
            father_vh = new ViewHolderFather();
            father_vh.tvFather = (TextView) convertView.findViewById(R.id.tv_father);
            convertView.setTag(father_vh);
        } else {
            father_vh = (ViewHolderFather) convertView.getTag();
        }
        father_vh.tvFather.setText(father_List.get(groupPosition));
        return convertView;
    }

    // 子层控件视图加载
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
                             ViewGroup parent) {
        ContentEntity contentEntity = son_List.get(groupPosition).get(childPosition);
        int type = getChildType(groupPosition, childPosition);
        switch (type) {
            case TYPE_1:
                ViewHolderSon01 son01;
                if (convertView == null) {
                    convertView = LayoutInflater.from(context).inflate(R.layout.son_item01, null);
                    son01 = new ViewHolderSon01();
                    son01.tv_info01 = convertView.findViewById(R.id.tv_info01);
                    son01.tv_name01 = convertView.findViewById(R.id.tv_name01);
                    son01.tv_time01 = convertView.findViewById(R.id.tv_time01);
                    son01.iv_pic = convertView.findViewById(R.id.iv_pic);
                    convertView.setTag(son01);
                } else {
                    son01 = (ViewHolderSon01) convertView.getTag();
                }
                son01.tv_time01.setText(contentEntity.getTime());
                son01.tv_name01.setText(contentEntity.getName());
                son01.tv_info01.setText(contentEntity.getInfo());
                son01.iv_pic.setImageResource(contentEntity.getImage());
                break;
            case TYPE_2:
                ViewHolderSon03 son03;
                if (convertView == null) {
                    convertView = LayoutInflater.from(context).inflate(R.layout.son_item02, null);
                    son03 = new ViewHolderSon03();
                    son03.tv_info03 = convertView.findViewById(R.id.tv_info03);
                    son03.tv_name03 = convertView.findViewById(R.id.tv_name03);
                    son03.tv_time03 = convertView.findViewById(R.id.tv_time03);
                    son03.tv_scname03 = convertView.findViewById(R.id.tv_scname03);
                    convertView.setTag(son03);
                } else {
                    son03 = (ViewHolderSon03) convertView.getTag();
                }
                son03.tv_time03.setText(contentEntity.getTime());
                son03.tv_name03.setText(contentEntity.getName());
                son03.tv_info03.setText(contentEntity.getInfo());
                son03.tv_scname03.setText(contentEntity.getSchoolName());
                break;
            default:
                break;
        }
        return convertView;
    }

    private class ViewHolderFather {
        TextView tvFather;
    }

    private class ViewHolderSon01 {
        TextView tv_info01, tv_time01, tv_name01;
        ImageView iv_pic;
    }

    private class ViewHolderSon03 {
        TextView tv_info03, tv_time03, tv_name03, tv_scname03;
    }
}