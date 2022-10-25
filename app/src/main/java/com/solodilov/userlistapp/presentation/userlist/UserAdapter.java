package com.solodilov.userlistapp.presentation.userlist;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.solodilov.userlistapp.databinding.ItemUserBinding;
import com.solodilov.userlistapp.domain.entity.User;

import java.util.LinkedList;
import java.util.List;

public class UserAdapter extends
        RecyclerView.Adapter< UserAdapter.ViewHolder> {

    private final List<User> items = new LinkedList<>();

    public void setData(List<User> data) {
        items.clear();
        items.addAll(data);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(ItemUserBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final User user = items.get(position);
        holder.bind(user);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ItemUserBinding binding;

        public ViewHolder(ItemUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(User user) {
            binding.userName.setText(user.getName());
            binding.userUid.setText(user.getUid());
        }
    }
}


