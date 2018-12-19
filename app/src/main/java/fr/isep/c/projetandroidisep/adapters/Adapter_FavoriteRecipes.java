package fr.isep.c.projetandroidisep.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import fr.isep.c.projetandroidisep.MainActivity;
import fr.isep.c.projetandroidisep.R;
import fr.isep.c.projetandroidisep.myCustomTypes.Recipe;


public class Adapter_FavoriteRecipes extends RecyclerView.Adapter
            <Adapter_FavoriteRecipes.RecyclerViewHolder_FavoriteRecipes>
{

    static class RecyclerViewHolder_FavoriteRecipes extends RecyclerView.ViewHolder
        implements View.OnClickListener {

        private TextView recipe_name, recipe_duration, recipe_rating ;
        private ImageView recipe_img ;
        private CheckBox checkbox_delete_from_favorites ;


        RecyclerViewHolder_FavoriteRecipes(View view)
        {
            super(view);

            recipe_name = view.findViewById(R.id.title);
            recipe_img = view.findViewById(R.id.recipe_img);
            recipe_duration = view.findViewById(R.id.recipe_duration);
            recipe_rating = view.findViewById(R.id.recipe_rating);
            checkbox_delete_from_favorites = view.findViewById(R.id.checkbox);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.checkbox:
                    Log.d("test", "my_recipes");
            }
        }
    }

    private Context context ;
    private ArrayList<Recipe> al ;


    public Adapter_FavoriteRecipes(Context context, ArrayList<Recipe> al)
    {
        this.al = al;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder_FavoriteRecipes onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_recipe, viewGroup, false);
        
        return new RecyclerViewHolder_FavoriteRecipes(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder_FavoriteRecipes holder, final int i)
    {
        holder.recipe_name.setText(al.get(i).getName());
        holder.recipe_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        
        holder.recipe_duration.setText("00 min");

        //holder.recipe_rating.setText("0/0");

        Recipe rec = MainActivity.getFavoriteRecipes().get(i);
        boolean already_favorite = rec.alreadyExists(MainActivity.getFavoriteRecipes()) ;
        //Log.d(rec.getUrl(), String.valueOf(already_favorite));

        holder.checkbox_delete_from_favorites.setChecked(already_favorite);
        holder.checkbox_delete_from_favorites.setOnCheckedChangeListener
                (new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                Recipe rec = MainActivity.getFavoriteRecipes().get(i);

                Log.d("is_checked",
                        rec.getName() + " | " + String.valueOf(isChecked));

                if (isChecked) {
                    //MainActivity.saveRecipeInFavorites(rec);

                } else {
                    // adds back recipe (revert deletion)
                    MainActivity.removeRecipeFromFavorites(rec);
                }

                //notifyDataSetChanged();
            }
        });
    }


    @Override
    public int getItemCount() {
        return (null != al ? al.size() : 0);
    }

}
