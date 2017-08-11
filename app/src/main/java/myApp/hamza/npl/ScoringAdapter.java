package myApp.hamza.npl;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

/**
 * Created by Hamza Ahmed on 14-Jul-17.
 */

public class ScoringAdapter extends ArrayAdapter<FullScoringClass> {

    String Name;
    FirebaseUser user;
    private  TextView messageTextView;

    public ScoringAdapter(Context context, int resource, List<FullScoringClass> objects, String name) {
        super(context, resource, objects);
        Name = name;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.table_layout_recylerview, parent, false);
        }

        TextView Batsman = (TextView) convertView.findViewById(R.id.Batsman);
        TextView Type = (TextView) convertView.findViewById(R.id.Type1);
        TextView R122 = (TextView) convertView.findViewById(R.id.R1);
        TextView B = (TextView) convertView.findViewById(R.id.B);
        TextView F4 = (TextView) convertView.findViewById(R.id.f4);
        TextView S6 = (TextView) convertView.findViewById(R.id.s6);
        TextView SS = (TextView) convertView.findViewById(R.id.SS);

        FullScoringClass fullScoringClass = getItem(position);
        System.out.println("here->"+fullScoringClass.getRuns());
        Batsman.setText(fullScoringClass.getBatsman());
        Type.setText(fullScoringClass.getType());
        R122.setText(String.valueOf(fullScoringClass.getRuns()));
        B.setText(String.valueOf(fullScoringClass.getB()));
        F4.setText(String.valueOf(fullScoringClass.getF4()));
        S6.setText(String.valueOf(fullScoringClass.getS6()));
        SS.setText(String.valueOf(fullScoringClass.getSS()));

        return convertView;
    }

}
