package com.alwi.navigationbar.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.alwi.navigationbar.Model.ModelUser;
import com.alwi.navigationbar.R;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> implements Filterable{
    Context context;

    private ArrayList<ModelUser> mArrayList;
    private ArrayList<ModelUser> getDataAdapter;

    public UserAdapter(ArrayList<ModelUser> arrayList, Context mContext ) {
        super();
        this.mArrayList = arrayList;
        this.getDataAdapter = arrayList;
        this.context = mContext;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_data_user_row_item, parent, false);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        ModelUser getDataAdapter1 =  getDataAdapter.get(position);

        holder.NamaUser.setText("Nama User : "+getDataAdapter1.getNamaUser());
        holder.Username.setText("Nama User : "+getDataAdapter1.getUsername());

       /* holder.bJawab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context, JawabTugasActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("idtugas",  getDataAdapter.get(position).getIDTugas() );
                context.startActivity(i);

            }
        });*/

    }

    @Override
    public int getItemCount() {
        return getDataAdapter.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    getDataAdapter = mArrayList;

                } else {

                    ArrayList<ModelUser> filteredList = new ArrayList<>();

                    for (ModelUser androidVersion : mArrayList) {

                        if (
                                androidVersion.getNamaUser().toLowerCase().contains(charString)
                        )
                        {

                            filteredList.add(androidVersion);
                        }
                    }

                    getDataAdapter = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = getDataAdapter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                getDataAdapter = (ArrayList<ModelUser>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView NamaUser, Username;


        public MyViewHolder(View view) {
            super(view);

            NamaUser           = (TextView) itemView.findViewById(R.id.txtNamaUser);
            Username           = (TextView) itemView.findViewById(R.id.txtusername);

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position= getAdapterPosition();
                    System.out.println("Datany adapter : " + getDataAdapter.get(position).getSoalTugas());
                    Intent intent = new Intent(context, DetailMateriActivity.class);
                    String id = getDataAdapter.get(position).getIDTugas();
                    intent.putExtra("id", id);
                    context.startActivity(intent);
                }
            });*/

            context = itemView.getContext();

        }

    }
}
