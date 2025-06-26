package com.example.budgetbuddytravel.utils;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.budgetbuddytravel.R;


public class SuggestionFragment extends Fragment {

    private TextView suggestionText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_suggestion, container, false);
        suggestionText = view.findViewById(R.id.suggestion_text);

        afficherSuggestions();

        return view;
    }

    private void afficherSuggestions() {
        String suggestions = "- Italie (vol + hôtel, 5 jours, 800 €)\n"
                + "- Grèce (vol + hôtel, 7 jours, 950 €)\n"
                + "- Espagne (location voiture + hôtel, 4 jours, 650 €)";
        suggestionText.setText(suggestions);
    }
}
