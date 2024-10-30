package com.example.lista2;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;

public class FragmentBDirections {
  private FragmentBDirections() {
  }

  @NonNull
  public static NavDirections toFragmentA() {
    return new ActionOnlyNavDirections(R.id.to_fragmentA);
  }
}
