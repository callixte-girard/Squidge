package fr.isep.c.projetandroidisep.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

import fr.isep.c.projetandroidisep.MainActivity;
import fr.isep.c.projetandroidisep.R;
import fr.isep.c.projetandroidisep.myClasses.ParseText;
import fr.isep.c.projetandroidisep.myCustomTypes.ListeCourses;
import fr.isep.c.projetandroidisep.myCustomTypes.Recipe;


public class Adapter_MyShoppingLists extends RecyclerView.Adapter
            <Adapter_MyShoppingLists
                .RecyclerViewHolder_MyShoppingLists>
{

    static class RecyclerViewHolder_MyShoppingLists extends RecyclerView.ViewHolder
        implements View.OnClickListener {


        private TextView lc_date_creation ;
        private CheckBox checkbox_delete_shopping_list ;


        RecyclerViewHolder_MyShoppingLists(View view)
        {
            super(view);

            lc_date_creation = view.findViewById(R.id.title);
            checkbox_delete_shopping_list = view.findViewById(R.id.checkbox);
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
    private ArrayList<ListeCourses> al ;


    public Adapter_MyShoppingLists(Context context, ArrayList<ListeCourses> al)
    {
        this.al = al;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder_MyShoppingLists onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_simple_checklist, viewGroup, false);

        return new RecyclerViewHolder_MyShoppingLists(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder_MyShoppingLists holder, final int i)
    {
        String clean_date_str = al.get(i).getDateCreation()
                .replace("_", " ");

        holder.lc_date_creation.setText(clean_date_str);
        holder.lc_date_creation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        Recipe rec = MainActivity.getFavoriteRecipes().get(i);
        boolean already_favorite = rec.alreadyExists(MainActivity.getFavoriteRecipes()) ;
        //Log.d(rec.getUrl(), String.valueOf(already_favorite));

        holder.checkbox_delete_shopping_list.setChecked(already_favorite);
        holder.checkbox_delete_shopping_list.setOnCheckedChangeListener
                (new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                ListeCourses lc = MainActivity.getMyShoppingLists().get(i);

                Log.d("is_checked",
                        lc.getDateCreation() + " | " + String.valueOf(isChecked));

                if (isChecked) {
                    //MainActivity.saveRecipeInFavorites(rec);

                } else {
                    // adds back recipe (revert deletion)
                    MainActivity.removeShoppingList(lc);
                }

                //notifyDataSetChanged();
            }
        });
    }



    public ListeCourses getShoppingListAtPosition(int position) {
        return al.get(position);
    }

    @Override
    public int getItemCount() {
        return (null != al ? al.size() : 0);
    }

}