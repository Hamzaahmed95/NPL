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

public class ScoringAdapter2 extends ArrayAdapter<FullScoringClass2> {

    String Name;
    FirebaseUser user;
    private  TextView messageTextView;

    public ScoringAdapter2(Context context, int resource, List<FullScoringClass2> objects, String name) {
        super(context, resource, objects);
        Name = name;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.table_layout_recylerview2, parent, false);
        }

        TextView bowler= (TextView)convertView.findViewById(R.id.Bowler);
        TextView overs= (TextView)convertView.findViewById(R.id.Over);
        TextView maiden= (TextView)convertView.findViewById(R.id.Maiden);
        TextView Eco= (TextView)convertView.findViewById(R.id.Economy);
        TextView wicket= (TextView)convertView.findViewById(R.id.Wicket);
        TextView Runs= (TextView)convertView.findViewById(R.id.Runs1);
        TextView zeros= (TextView)convertView.findViewById(R.id.Z0);
        TextView wide= (TextView)convertView.findViewById(R.id.Wd);
        TextView noball= (TextView)convertView.findViewById(R.id.Nb);

        

        FullScoringClass2 fullScoringClass2 = getItem(position);

        System.out.println("bowlers: "+fullScoringClass2.getBowler());
        bowler.setText(fullScoringClass2.getBowler());
        overs.setText(String.valueOf(fullScoringClass2.getOvers()));
        maiden.setText(String.valueOf(fullScoringClass2.getMaiden()));
        Eco.setText(String.valueOf(fullScoringClass2.getEconomy()));
        Runs.setText(String.valueOf(fullScoringClass2.getRuns()));
        zeros.setText(String.valueOf(fullScoringClass2.getZ0()));
        wicket.setText(String.valueOf(fullScoringClass2.getWicket()));
        wide.setText(String.valueOf(fullScoringClass2.getWd()));
        noball.setText(String.valueOf(fullScoringClass2.getNb()));



        return convertView;
    }

}
