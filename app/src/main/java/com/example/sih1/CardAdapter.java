package com.example.sih1;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CardAdapter extends ArrayAdapter<Card>
{
    public CardAdapter(Activity context, ArrayList<Card> cards) {

        super(context, 0, cards);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }


        Card currentAndroidFlavor = getItem(position);


        TextView descTv = (TextView) listItemView.findViewById(R.id.desc);


        descTv.setText(currentAndroidFlavor.getDescription());


        TextView tim = (TextView) listItemView.findViewById(R.id.time);


        tim.setText(currentAndroidFlavor.getDate());

        TextView type = listItemView.findViewById(R.id.type);

        ImageView iconView = (ImageView) listItemView.findViewById(R.id.imageView);


        type.setText(getType(currentAndroidFlavor.getType()));
        iconView.setBackgroundResource(getPi(currentAndroidFlavor.getType()));


        return listItemView;
    }

    String getType(int no)
    {
        switch (no)
        {
            case 1 : return "MAINTENANCE" ;
            case 2 : return "JANITOR" ;
            default: return "" ;
        }
    }

    int getPi(int no)
    {
        switch (no)
        {
            case 1 : return (R.drawable.maintainence);
            case 2 : return (R.drawable.janitor) ;
            default: return 0 ;
        }
    }

}
