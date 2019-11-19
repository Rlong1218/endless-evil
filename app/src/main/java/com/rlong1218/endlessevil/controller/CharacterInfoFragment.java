package com.rlong1218.endlessevil.controller;


import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProviders;
import com.rlong1218.endlessevil.R;
import com.rlong1218.endlessevil.viewmodel.CharacterInfoViewModel;

/**
 * A simple {@link Fragment} subclass. Use the {@link CharacterInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CharacterInfoFragment extends DialogFragment {

  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ID_KEY = "id";

  private View view;
  private CharacterInfoViewModel viewModel;
  private ImageView characterSprite;
  private TextView totalKills;
  private TextView gamesPlayed;
  private TextView currentUpgrades;
  //TODO add fields for view objects in layout

  public static CharacterInfoFragment newInstance(long id) {
    CharacterInfoFragment fragment = new CharacterInfoFragment();
    Bundle args = new Bundle();
    args.putLong(ID_KEY, id);
    fragment.setArguments(args);
    return fragment;
  }

  @NonNull
  @Override
  public AlertDialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    view = getActivity().getLayoutInflater().inflate(R.layout.fragment_character_info, null);
    // TODO get references to view objects in layout
    characterSprite = view.findViewById(R.id.sprite);

    return new AlertDialog.Builder(getContext())
        .setTitle("Character Details")
        .setView(view)
        .setNegativeButton("Cancel", (dialog, button) -> {})
        .setPositiveButton("Select", (dialog, button) -> {
          // TODO send selected character back to activity
        })
        .create();

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return view;

  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = ViewModelProviders.of(this).get(CharacterInfoViewModel.class);
    viewModel.setId(getArguments().getLong(ID_KEY));
    viewModel.getCharacter().observe(this, (character) -> {
      //TODO populate fields with character info
      String image = character.getImage();
      if (image != null && !image.isEmpty()) {
        Resources res = getResources();
        String pkg = getActivity().getPackageName();
        int id = res.getIdentifier(image, "drawable", pkg);
        characterSprite.setImageResource(id);
      }
    });
  }
}
