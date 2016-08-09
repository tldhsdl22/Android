package com.example.memberproject;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 한국정보기술 on 2016-08-09.
 */
public class ListAdapter extends ArrayAdapter<MemberVO> {
    Context context;
    int resource;
    List<MemberVO> objects;
    LayoutInflater inflater;

    public ListAdapter(Context context, int resource, List<MemberVO> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); // xml파일을 레이아웃 서비스를 가능하게 만들어주는 객체
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) { // 매개변수 (위치, 리스트의 한 항목을 나타내는 뷰, 전체를 출력하는 리스트뷰)
        ViewHolder holder;

        if(convertView == null) {
            convertView = inflater.inflate(resource, null); // resource가 레이아웃 서비스를 가능하게 만들어짐

            holder = new ViewHolder();

            // 한 항목을 나타내는 뷰의 각 컨트롤 객체를 참조
            holder.memberId = (TextView)convertView.findViewById(R.id.memberId);
            holder.memberName = (TextView)convertView.findViewById(R.id.memberName);
            holder.memberAge = (TextView)convertView.findViewById(R.id.memberAge);

            convertView.setTag(holder);
            System.out.println("convertViews Null " + position);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
            System.out.println("convertViews Not Null " + position);
        }
        // 데이터를 읽어와서 각 뷰에 출력을 위한 셋팅
        MemberVO member = objects.get(position);

        holder.memberId.setText(member.getId());
        holder.memberName.setText(member.getName());
        holder.memberAge.setText(String.valueOf(member.getAge()));

        return convertView;
    }

    @Override
    public void remove(MemberVO object) {
        super.remove(object);
    }
    class ViewHolder {
        TextView memberId;
        TextView memberName;
        TextView memberAge;
    }
}
