package fr.tordesillas.ccexpert.view;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import fr.tordesillas.ccexpert.R;
import fr.tordesillas.ccexpert.model.HeroRoll;

public class RollAdapter extends RecyclerView.Adapter<RollAdapter.ViewHolder> {
    private List<HeroRoll> heroes;
    private Context context;

    public RollAdapter(List<HeroRoll> heroes, Context context) {
        this.heroes = heroes;
        this.context = context;
    }

    @NonNull
    @Override
    public RollAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View contactView = LayoutInflater.from(parent.getContext()).inflate(R.layout.roll_littlecard, parent, false);

        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull RollAdapter.ViewHolder viewHolder, int position) {
        HeroRoll hero = heroes.get(position);

        viewHolder.heroCount.setText("x" + hero.getOccurrences());

        String pic = hero.getPicture() + "_card";
        viewHolder.imageHero.setImageResource(context.getResources().getIdentifier(pic, "drawable", context.getPackageName()));
    }

    @Override
    public int getItemCount() {
        return heroes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView heroCount;
        public ImageView imageHero;

        ViewHolder(View itemView) {
            super(itemView);

            imageHero = itemView.findViewById(R.id.imageHero);
            heroCount = itemView.findViewById(R.id.heroCount);
        }
    }
}
