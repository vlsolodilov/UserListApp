package com.solodilov.userlistapp.presentation.login;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.Snackbar;
import com.solodilov.userlistapp.App;
import com.solodilov.userlistapp.R;
import com.solodilov.userlistapp.databinding.FragmentLoginBinding;
import com.solodilov.userlistapp.domain.entity.User;
import com.solodilov.userlistapp.presentation.common.IdentifierDevice;
import com.solodilov.userlistapp.presentation.common.Response;

import java.util.List;

import javax.inject.Inject;

public class LoginFragment extends Fragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private LoginViewModel viewModel;

    private FragmentLoginBinding binding;

    private int spinnerPosition = 0;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((App) requireActivity().getApplicationContext()).appComponent.inject(this);
        viewModel = new ViewModelProvider(this, viewModelFactory).get(LoginViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);

        if (savedInstanceState != null) {
            spinnerPosition = savedInstanceState.getInt("spinnerPosition");
        }

        initViews();
        observeViewModel();

        return binding.getRoot();
    }

    private void initViews() {
        viewModel.getUserList(getImei());
        binding.errorLayout.tryButton.setOnClickListener(view -> viewModel.getUserList(getImei()));
    }

    private void observeViewModel() {
        viewModel.response().observe(getViewLifecycleOwner(), this::processResponse);
        viewModel.loginAvailable().observe(getViewLifecycleOwner(), this::toggleLogin);
        viewModel.loginSuccessEvent.observe(getViewLifecycleOwner(), unused -> startUserListFragment());
        viewModel.loginErrorEvent.observe(getViewLifecycleOwner(), unused -> showLoginFailed());
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
        binding.loginLayout.setVisibility(View.GONE);
        binding.errorLayout.getRoot().setVisibility(View.GONE);
    }

    private void renderDataState(List<User> userList) {
        binding.progressBar.setVisibility(View.GONE);
        binding.loginLayout.setVisibility(View.VISIBLE);
        binding.errorLayout.getRoot().setVisibility(View.GONE);

        setUpSpinner(userList);

        binding.signInButton.setOnClickListener(view -> {
            User user = (User) binding.userSpinner.getSelectedItem();
            viewModel.login(
                    getImei(),
                    user.getName(),
                    user.getUid(),
                    binding.password.getText().toString()
            );
        });
    }

    private void renderErrorState(Throwable throwable) {
        Log.e("TAG", "renderErrorState: ", throwable);
        binding.progressBar.setVisibility(View.GONE);
        binding.loginLayout.setVisibility(View.GONE);
        binding.errorLayout.getRoot().setVisibility(View.VISIBLE);
    }

    private String getImei() {
        return IdentifierDevice.getImei(requireContext());
    }

    private void setUpSpinner(List<User> userList) {
        ArrayAdapter<User> userAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                userList
        );
        userAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.userSpinner.setAdapter(userAdapter);
        binding.userSpinner.setSelection(spinnerPosition);
    }

    private void toggleLogin(Boolean enable) {
        binding.signInButton.setEnabled(enable);
        int visible = enable ? View.GONE : View.VISIBLE;
        binding.progressBar.setVisibility(visible);
    }

    private void startUserListFragment() {
        Navigation.findNavController(binding.getRoot())
                .navigate(R.id.action_login_fragment_to_user_list_fragment);
    }

    private void showLoginFailed() {
        Snackbar.make(binding.getRoot(), R.string.login_failed, Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("spinnerPosition", binding.userSpinner.getSelectedItemPosition());
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}
