package com.db.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.db.dbmanager.TableCreator;
import com.example.dbthemeparkapp.R;

public class InfoTableFragment extends Fragment {

	public InfoTableFragment() {}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.fragment_test_3, container, false);
		
		TableCreator dbh = new TableCreator(getActivity(), R.id.table4, "id", "text", "date");
		dbh.execute("info.php");
		
		return rootView;
	}
}
