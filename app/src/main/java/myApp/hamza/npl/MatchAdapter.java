package myApp.hamza.npl;

/**
 * Created by Hamza Ahmed on 13-Jul-17.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


/**
 * Created by Hamza Ahmed on 31-Jan-17.
 */

/**
 * Created by Hamza Ahmed on 29-Jan-17.
 */
public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ViewHolder> {
    private List<Match> scoreList;
    private Match mMatch;
    private Context context;

    public MatchAdapter(List<Match> mscoreList) {
        scoreList = mscoreList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_of_matches, viewGroup, false);
        context =view.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        Log.d("Match1: ",""+scoreList.get(i));
        Match Match = scoreList.get(i);
        Log.d("Match: ",""+Match.getId());
        viewHolder.bindDeals(Match);
    }


    @Override
    public int getItemCount() {
        return scoreList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView team1;
        private ImageView team2;
        private TextView MatchDate;
        private TextView MatchResult;
        private TextView MatchNo;
        private TextView MatchVenue;
        public ViewHolder(View view) {
            super(view);
            team1 =(ImageView) view.findViewById(R.id.image1);
            team2 =(ImageView) view.findViewById(R.id.image2);
            MatchDate =(TextView)view.findViewById(R.id.MatchDate);
            MatchResult =(TextView)view.findViewById(R.id.matchResult);
            MatchNo =(TextView)view.findViewById(R.id.MatchNo);
            MatchVenue =(TextView)view.findViewById(R.id.Venue);
        }
        public void bindDeals(Match Match){
            mMatch=Match;
            team1.setScaleType(ImageView.ScaleType.CENTER_CROP);
            team1.setImageResource(mMatch.getImage1Id());
            team2.setScaleType(ImageView.ScaleType.CENTER_CROP);
            team2.setImageResource(mMatch.getImage2Id());
            MatchDate.setText(mMatch.getMatchDate());
            MatchResult.setText(mMatch.getMatchResult());
            MatchNo.setText(mMatch.getMatchNo());
            MatchVenue.setText(mMatch.getVenue());
        }
    }
}