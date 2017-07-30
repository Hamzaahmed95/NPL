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
import android.widget.TextView;

import java.util.List;


/**
 * Created by Hamza Ahmed on 31-Jan-17.
 */

/**
 * Created by Hamza Ahmed on 29-Jan-17.
 */
public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ViewHolder> {
    private List<ScoreCard> scoreList;
    private ScoreCard mScoreCard;
    private Context context;

    public ScoreAdapter(List<ScoreCard> mscoreList) {
        scoreList = mscoreList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_scorecard, viewGroup, false);
        context =view.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        Log.d("ScoreCard1: ",""+scoreList.get(i));
        ScoreCard ScoreCard = scoreList.get(i);
        Log.d("ScoreCard: ",""+ScoreCard.getId());
        viewHolder.bindDeals(ScoreCard);
    }


    @Override
    public int getItemCount() {
        return scoreList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView batsman;
        private TextView outType;
        private TextView outBy;
        private TextView runs;
        private TextView balls;
        public ViewHolder(View view) {
            super(view);
            batsman =(TextView)view.findViewById(R.id.batsman);
            outType =(TextView)view.findViewById(R.id.outType);
            outBy =(TextView)view.findViewById(R.id.outBy);
            runs =(TextView)view.findViewById(R.id.runs);
            balls =(TextView)view.findViewById(R.id.balls);
        }
        public void bindDeals(ScoreCard ScoreCard){
            mScoreCard=ScoreCard;
           batsman.setText(mScoreCard.getBatsman());
            outBy.setText(mScoreCard.getOutBy());
            outType.setText(mScoreCard.getOutType());
            runs.setText(mScoreCard.getRuns());
            balls.setText(mScoreCard.getBalls());


        }
    }
}