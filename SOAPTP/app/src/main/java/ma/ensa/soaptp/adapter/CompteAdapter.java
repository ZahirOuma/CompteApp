package ma.ensa.soaptp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

import ma.ensa.soaptp.R;
import ma.ensa.soaptp.beans.Compte;

public class CompteAdapter extends RecyclerView.Adapter<CompteAdapter.CompteViewHolder> {

    private List<Compte> comptes = new ArrayList<>();
    private OnEditClickListener onEditClick;
    private OnDeleteClickListener onDeleteClick;

    public interface OnEditClickListener {
        void onEditClick(Compte compte);
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(Compte compte);
    }

    public void setOnEditClick(OnEditClickListener listener) {
        this.onEditClick = listener;
    }

    public void setOnDeleteClick(OnDeleteClickListener listener) {
        this.onDeleteClick = listener;
    }

    public void updateComptes(List<Compte> newComptes) {
        comptes.clear();
        comptes.addAll(newComptes);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CompteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_account, parent, false);
        return new CompteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompteViewHolder holder, int position) {
        holder.bind(comptes.get(position));
    }

    @Override
    public int getItemCount() {
        return comptes.size();
    }

    public void removeCompte(Compte compte) {
        int position = comptes.indexOf(compte);
        if (position >= 0) {
            comptes.remove(position);
            notifyItemRemoved(position);
        }
    }

    class CompteViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvSolde;
        private final Chip tvType;
        private final MaterialButton btnEdit;
        private final MaterialButton btnDelete;

        public CompteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSolde = itemView.findViewById(R.id.tvSolde);
            tvType = itemView.findViewById(R.id.tvType);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }

        public void bind(Compte compte) {
            tvSolde.setText(compte.getSolde() + " DH");
            tvType.setText(compte.getType().name());

            btnEdit.setOnClickListener(v -> {
                if (onEditClick != null) {
                    onEditClick.onEditClick(compte);
                }
            });

            btnDelete.setOnClickListener(v -> {
                if (onDeleteClick != null) {
                    onDeleteClick.onDeleteClick(compte);
                }
            });
        }
    }
}
