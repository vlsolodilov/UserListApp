package com.solodilov.userlistapp.presentation.userlist;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.solodilov.userlistapp.App;
import com.solodilov.userlistapp.databinding.FragmentUserListBinding;
import com.solodilov.userlistapp.domain.entity.User;
import com.solodilov.userlistapp.presentation.common.Response;

import java.util.List;

import javax.inject.Inject;

public class UserListFragment extends Fragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private UserListViewModel viewModel;

    private FragmentUserListBinding binding;

    private UserAdapter userAdapter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((App) requireActivity().getApplicationContext()).appComponent.inject(this);
        viewModel = new ViewModelProvider(this, viewModelFactory).get(UserListViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentUserListBinding.inflate(inflater, container, false);

        initViews();
        observeViewModel();

        return binding.getRoot();
    }

    private void initViews() {
        viewModel.getUserList();
        userAdapter = new UserAdapter();
        binding.userList.setAdapter(userAdapter);
        binding.errorLayout.tryButton.setOnClickListener(view -> viewModel.getUserList());
    }

    private void observeViewModel() {
        viewModel.response().observe(getViewLifecycleOwner(), this::processResponse);
    }

    private void processResponse(Response response) {
        switch (response.status) {
            case LOADING:
                renderLoadingState();
                break;

            case SUCCESS:
                renderDataState(response.data);
                break;

            case ERROR:
                renderErrorState(response.error);
                break;
        }
    }

    private void renderLoadingState() {
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.userList.setVisibility(View.GONE);
        binding.errorLayout.getRoot().setVisibility(View.GONE);
    }

    private void renderDataState(List<User> userList) {
        if (userAdapter != null) userAdapter.setData(userList);

        binding.progressBar.setVisibility(View.GONE);
        binding.userList.setVisibility(View.VISIBLE);
        binding.errorLayout.getRoot().setVisibility(View.GONE);
    }

    private void renderErrorState(Throwable throwable) {
        Log.e("TAG", "renderErrorState: ", throwable);
        binding.progressBar.setVisibility(View.GONE);
        binding.userList.setVisibility(View.GONE);
        binding.errorLayout.getRoot().setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        userAdapter = null;
        binding = null;
        super.onDestroyView();
    }
}
